package kr.or.dgit.sw_project.application.category;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import erp_myframework.TextFieldPanel;
import kr.or.dgit.sw_project.dto.Category;
import kr.or.dgit.sw_project.service.CategoryService;

@SuppressWarnings("serial")
public class ContentCategory extends JPanel {
	private TextFieldPanel tfpCategoryName;
	private TextFieldPanel tfpCategoryCode;
	public ContentCategory() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {300, 50, 50};
		gridBagLayout.rowHeights = new int[] {10, 30, 30, 10};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		tfpCategoryCode = new TextFieldPanel();
		tfpCategoryCode.setTitle("분류코드");
		GridBagConstraints gbc_tfpCategoryCode = new GridBagConstraints(); 
		gbc_tfpCategoryCode.fill = GridBagConstraints.HORIZONTAL; 
		gbc_tfpCategoryCode.insets = new Insets(0, 0, 5, 5);
		gbc_tfpCategoryCode.gridx = 0;
		gbc_tfpCategoryCode.gridy = 1;
		add(tfpCategoryCode, gbc_tfpCategoryCode);
		
		tfpCategoryName = new TextFieldPanel();
		tfpCategoryName.setTitle("분 류 명");
		GridBagConstraints gbc_tfpCategoryName = new GridBagConstraints();
		gbc_tfpCategoryName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpCategoryName.insets = new Insets(0, 0, 5, 5);
		gbc_tfpCategoryName.gridx = 0;
		gbc_tfpCategoryName.gridy = 2;
		add(tfpCategoryName, gbc_tfpCategoryName);
		
		initSetting();
		setVisible(true);
	}
	
	public void initSetting(){ //초기화
		tfpCategoryName.requestFocus();
		tfpCategoryCode.setTfValue("");
		tfpCategoryName.setTfValue("");
	}
	
	public String getCategoryCode(){ //text필드 값받아옴
		String groupCode = tfpCategoryCode.getTfValue();
		return groupCode;
	}
	
	public String getCategoryName(){ //text필드 값받아옴
		String groupName = tfpCategoryName.getTfValue();
		return groupName;
	}
	
	public Category getObject(){ //text필드 값받아옴
		String groupCode = tfpCategoryCode.getTfValue();
		String groupName = tfpCategoryName.getTfValue();
		return new Category(groupCode, groupName);
	}
	
	public void setObject(Category su){ //text필드에 값세팅
		tfpCategoryCode.setTfValue(su.getGroupCode());
		tfpCategoryName.setTfValue(su.getGroupName());
	}
	
	public boolean isEmptyCheck(){ // 빈공간체크
		for(Component c: getComponents()){
			if(c instanceof TextFieldPanel){
				TextFieldPanel tfp= (TextFieldPanel) c;
				if(tfp.isEmptyCheck()){
					return true;
				}
			}
		}return false;	
	}
	
	public boolean isPatternCheck(){// 분류코드:영문,대문자,입력갯수(2)체크, 분류명:한글을 입력하였는지 체크
		boolean isPtenCh = false;
		if(Pattern.matches("^[A-Z]{2}$", tfpCategoryCode.getTfValue())==false ||
		   Pattern.matches("^[가-힣]+$", tfpCategoryName.getTfValue())==false){
			isPtenCh = true;
		}
		return isPtenCh;
	}
	
	public boolean isOverlapNameAndCodeCheck(Category su, int key){// 중복된 분류명과 코드명이 있는지 체크(수정시 자기자신을 제외한 나머지 항목을 비교하는 방법을 이용)
		List<Category> list= CategoryService.getInstance().selectCategoryByAll();
		String[][] listAllNameAndCode = new String[list.size()][];
		boolean isOvlapCh = false;
		int innerKey = 0;
		for(int i=0;i<listAllNameAndCode.length;i++){
			listAllNameAndCode[i] = list.get(i).toArray();
			if(key==1){
			if((listAllNameAndCode[i][0].equals(su.getGroupCode())||
			   listAllNameAndCode[i][1].equals(su.getGroupName()))){
				isOvlapCh = false;
				innerKey=1;
			}}
			if((listAllNameAndCode[i][0].equals(tfpCategoryCode.getTfValue())||
			   listAllNameAndCode[i][1].equals(tfpCategoryName.getTfValue())) && innerKey==0){
				isOvlapCh = true;
				break;
			}
			if(innerKey==1){
				innerKey=0;
			}
		}
		return isOvlapCh;
	}
}
