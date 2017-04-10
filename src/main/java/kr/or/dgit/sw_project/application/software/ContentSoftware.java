package kr.or.dgit.sw_project.application.software;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import erp_myframework.ComboPanel;
import erp_myframework.TextFieldPanel;
import kr.or.dgit.sw_project.dto.Category;
import kr.or.dgit.sw_project.dto.JoinFromSoftware;
import kr.or.dgit.sw_project.dto.Software;
import kr.or.dgit.sw_project.service.CategoryService;
import kr.or.dgit.sw_project.service.JoinFromSoftwareService;
import kr.or.dgit.sw_project.service.SoftwareService;

@SuppressWarnings("serial")
public class ContentSoftware extends JPanel implements MouseListener {
	private JLabel lblImage;
	private TextFieldPanel tfpSWCode;
	private TextFieldPanel tfpSWName;
	private TextFieldPanel tfpSwPrice;
	private ComboPanel tfpGroupName;
	private JComboBox tfpGroupNameBox;

	public ContentSoftware() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {300, 70};
		gridBagLayout.rowHeights = new int[]{160, 30, 30, 30, 20};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		tfpSWCode = new TextFieldPanel();
		tfpSWCode.setTitle("제품번호");
		tfpSWCode.getTf().setFocusable(false);
		tfpSWCode.getTf().setEditable(false);
		GridBagConstraints gbc_tfpSWCode = new GridBagConstraints(); 
		gbc_tfpSWCode.fill = GridBagConstraints.HORIZONTAL; 
		gbc_tfpSWCode.insets = new Insets(0, 0, 5, 5);
		gbc_tfpSWCode.gridx = 1;
		gbc_tfpSWCode.gridy = 1;
		add(tfpSWCode, gbc_tfpSWCode);
		GridBagConstraints gbc_tfptfpSWCode = new GridBagConstraints(); 
		gbc_tfptfpSWCode.fill = GridBagConstraints.HORIZONTAL; 
		gbc_tfptfpSWCode.insets = new Insets(0, 0, 5, 5);
		gbc_tfptfpSWCode.gridx = 0;
		gbc_tfptfpSWCode.gridy = 1;
		add(tfpSWCode, gbc_tfptfpSWCode);
		
		tfpSWName = new TextFieldPanel();
		tfpSWName.setTitle("제품명");
		GridBagConstraints gbc_tfpSWName = new GridBagConstraints();
		gbc_tfpSWName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpSWName.insets = new Insets(0, 0, 5, 5);
		gbc_tfpSWName.gridx = 0;
		gbc_tfpSWName.gridy = 2;
		add(tfpSWName, gbc_tfpSWName);
		
		tfpSwPrice = new TextFieldPanel();
		tfpSwPrice.setTitle("판매가격");
		GridBagConstraints gbc_tfpSwPrice = new GridBagConstraints();
		gbc_tfpSwPrice.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpSwPrice.insets = new Insets(0, 0, 0, 5);
		gbc_tfpSwPrice.gridx = 1;
		gbc_tfpSwPrice.gridy = 3;
		add(tfpSwPrice, gbc_tfpSwPrice);
		GridBagConstraints gbc_tfptfpSwPrice = new GridBagConstraints();
		gbc_tfptfpSwPrice.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfptfpSwPrice.insets = new Insets(0, 0, 0, 5);
		gbc_tfptfpSwPrice.gridx = 0;
		gbc_tfptfpSwPrice.gridy = 3;
		add(tfpSwPrice, gbc_tfptfpSwPrice);
		
		tfpGroupName = new ComboPanel();
		tfpGroupName.setTitle("분류");
		GridBagConstraints gbc_tfpGroupName = new GridBagConstraints();
		gbc_tfpGroupName.insets = new Insets(0, 0, 5, 5);
		gbc_tfpGroupName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpGroupName.gridx = 0;
		gbc_tfpGroupName.gridy = 4;
		add(tfpGroupName, gbc_tfpGroupName);

		lblImage = new JLabel("");
		lblImage.setPreferredSize(new Dimension(130, 80));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.addMouseListener(this);
		lblImage.setIcon(new ImageIcon("sw_project/src/main/resources/softwareimage/DGIT_Logo.png"));

		lblImage.setIcon(new ImageIcon(ContentSoftware.class.getResource("../../../../../../../../resources/main/softwareimage/DGIT_Logo.png")));
		GridBagConstraints gbc_lblImage = new GridBagConstraints();
		gbc_lblImage.fill = GridBagConstraints.BOTH;
		gbc_lblImage.gridx = 0;
		gbc_lblImage.gridy = 0;
		gbc_lblImage.insets = new Insets(15, 70, 15, 0);
		add(lblImage, gbc_lblImage);
	}
	
	public boolean isPatternCheck(){// 판매가란에 숫자만(9자리 미만)입력 하였는지 체크
		boolean isPtenCh = false;
		if(Pattern.matches("^[0-9]{1,9}$", tfpSwPrice.getTfValue())==false){
			isPtenCh = true;
		}
		return isPtenCh;
	}
	
	public boolean isWsCheck(){// 공백 체크
		boolean isWsCheck = false;
		if(tfpSWName.getTfValue().equals("") ||
		   tfpSwPrice.getTfValue().equals("")||
		   tfpGroupName.getSelectItem()==null){
		   isWsCheck = true;
		}
		return isWsCheck;
	}

	public void getSwCode(){//소프트웨어 전체 목록, 제품코드를 SW000 으로 자동 내림차순
		List<JoinFromSoftware> list = JoinFromSoftwareService.getInstance().selectJoinFromSoftwareByAll();
		String value = String.format("SW%03d", list.size()+1);
		tfpSWCode.setTfValue(value);
	}
	
	public void setComboBox(){ // 분류 목록에 있는 분류명을 가져와 콤보박스에 삽입
		List<Category> list = CategoryService.getInstance().selectCategoryByAll();
		String[][] getComboObj = new String[list.size()][];
		String[] comboObj = new String[list.size()];
		Vector<String> setComboObj = new Vector<>();
		
		for(int i=0 ; i<list.size() ; i++){
			getComboObj[i] = list.get(i).toArray();
			comboObj[i] = getComboObj[i][1];
			setComboObj.add(comboObj[i]);
		}
		tfpGroupName.setComboData(setComboObj);
	}
	
	public Software getSoftwareCode(){ //제품코드 텍스트에 있는 값을 가져와 리턴
		String swCode = tfpSWCode.getTfValue();
		return new Software(swCode);
	}
	
	public void initSetting(){ //초기화
		tfpSWName.requestFocus();
		tfpSWName.setTfValue("");
		tfpSwPrice.setTfValue("");
		tfpGroupName.setSelectedItem(null);
	}
	
	public void setObject(Object[] swObj){ //클릭된 테이블의 인덱스에 있는 컬럼들을 가져와 각각의 입력목록에 삽입
		tfpSWCode.setTfValue(String.valueOf(swObj[0]));
		tfpGroupName.setSelectedItem(swObj[1]);
		tfpSWName.setTfValue(String.valueOf(swObj[2]));
		tfpSwPrice.setTfValue(String.valueOf(swObj[4]));
	}
	
	public void insertObject(){// 하나의 소프트웨어를 등록
		Map<String, Object> insertSoftware = new HashMap<String, Object>();
		insertSoftware.put("swCode", tfpSWCode.getTfValue());
		insertSoftware.put("groupName", tfpGroupName.getSelectItem());
		insertSoftware.put("swName", tfpSWName.getTfValue());
		insertSoftware.put("salePrice", tfpSwPrice.getTfValue());
		insertSoftware.put("swInven", 0);
		insertSoftware.put("swImg", "IMG");
		insertSoftware.put("swIsSale", false);
		SoftwareService.getInstance().insertSoftwareItem(insertSoftware);
	}
	
	public void updateObject(){// 하나의 소프트웨어를 수정
		Map<String, Object> updateSoftware = new HashMap<String, Object>();
		updateSoftware.put("swCode", tfpSWCode.getTfValue());
		updateSoftware.put("groupName", tfpGroupName.getSelectItem());
		updateSoftware.put("swName", tfpSWName.getTfValue());
		updateSoftware.put("salePrice", tfpSwPrice.getTfValue());
		SoftwareService.getInstance().updateSoftwareItem(updateSoftware);
	}
	
	public void swCodeReset(Object[] swCodes){// swCode의 내림차순패턴이 하나 비었을때 앞당김
		Map<String, Object> swCodeReset = new HashMap<String, Object>();
		for(int i=1 ; i<=swCodes.length ; i++){
			if(!(String.format("SW%03d", i).equals(swCodes[i-1]))){
				swCodeReset.put("resetCode", String.format("SW%03d", i+1));
				swCodeReset.put("swCode", String.format("SW%03d", i+1));
				SoftwareService.getInstance().swCodeReset(swCodeReset);
			}
		}
	}
	
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == lblImage) {
			mousePressedLblImage(e);
		}
	}
	public void mouseReleased(MouseEvent e) {
	}
	protected void mousePressedLblImage(MouseEvent e) {
		JOptionPane.showMessageDialog(null, "파일추저지롱~");
	}
}
