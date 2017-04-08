package kr.or.dgit.sw_project.application.sales;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import erp_myframework.CheckBoxPanel;
import erp_myframework.ComboPanel;
import erp_myframework.TextFieldPanel;
import kr.or.dgit.sw_project.dto.Sale;
import kr.or.dgit.sw_project.service.SaleService;

public class ContentSale extends JPanel {
	private TextFieldPanel tfpSaleCode;
	private ComboPanel tfpSwName;
	private TextFieldPanel tfpSaleAmount;
	private ComboPanel tfpClntName;
	private TextFieldPanel tfpOrderDate;
	private CheckBoxPanel tfpIsExist;
	public ContentSale() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {300, 300, 50};
		gridBagLayout.rowHeights = new int[]{10, 30, 30, 30, 10};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		tfpSaleCode = new TextFieldPanel();
		TextFieldPanel tfpSaleCode = new TextFieldPanel();
		tfpSaleCode.setTitle("주문번호");
		GridBagConstraints gbc_tfpSaleCode = new GridBagConstraints(); 
		gbc_tfpSaleCode.fill = GridBagConstraints.HORIZONTAL; 
		gbc_tfpSaleCode.insets = new Insets(0, 0, 0, 0);
		gbc_tfpSaleCode.gridx = 0;
		gbc_tfpSaleCode.gridy = 1;
		add(tfpSaleCode, gbc_tfpSaleCode);
		
		tfpSwName = new ComboPanel();
		tfpSwName.setTitle("품목명");
		GridBagConstraints gbc_tfpSwName = new GridBagConstraints();
		gbc_tfpSwName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpSwName.insets = new Insets(0, 0, 0, 0);
		gbc_tfpSwName.gridx = 1;
		gbc_tfpSwName.gridy = 1;
		add(tfpSwName, gbc_tfpSwName);
		
		tfpSaleAmount = new TextFieldPanel();
		TextFieldPanel tfpSaleAmount = new TextFieldPanel();
		tfpSaleAmount.setTitle("주문수량");
		GridBagConstraints gbc_tfpSaleAmount = new GridBagConstraints();
		gbc_tfpSaleAmount.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpSaleAmount.insets = new Insets(0, 0, 0, 0);
		gbc_tfpSaleAmount.gridx = 0;
		gbc_tfpSaleAmount.gridy = 2;
		add(tfpSaleAmount, gbc_tfpSaleAmount);
		
		tfpClntName = new ComboPanel();
		tfpClntName.setTitle("고객상호명");
		GridBagConstraints gbc_tfpClntName = new GridBagConstraints();
		gbc_tfpClntName.insets = new Insets(0, 0, 0, 0);
		gbc_tfpClntName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpClntName.gridx = 1;
		gbc_tfpClntName.gridy = 2;
		add(tfpClntName, gbc_tfpClntName);
		
		tfpOrderDate = new TextFieldPanel();
		TextFieldPanel tfpOrderDate = new TextFieldPanel();
		tfpOrderDate.setTitle("주문일자");
		GridBagConstraints gbc_tfpOrderDate = new GridBagConstraints();
		gbc_tfpOrderDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpOrderDate.insets = new Insets(0, 0, 0, 0);
		gbc_tfpOrderDate.gridx = 0;
		gbc_tfpOrderDate.gridy = 3;
		add(tfpOrderDate, gbc_tfpOrderDate);
		
		tfpIsExist = new CheckBoxPanel();
		tfpIsExist.setTitle("입금여부");
		GridBagConstraints gbc_tfpIsExist = new GridBagConstraints();
		gbc_tfpIsExist.insets = new Insets(0, 0, 0, 0);
		gbc_tfpIsExist.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpIsExist.gridx = 1;
		gbc_tfpIsExist.gridy = 3;
		add(tfpIsExist, gbc_tfpIsExist);
	}
	
	public void initSetting(){ //코드 자동세팅 다른필드 초기화
		List<Sale> list =SaleService.getInstance().selectSaleByAll();
		tfpSaleCode.setTfValue(String.format("CL%03d", list.size()+1));
		tfpSwName.setSelectedItem(0);
		tfpSaleAmount.setTfValue("");
		tfpClntName.setSelectedItem(0);
		tfpOrderDate.setTfValue("");
		tfpSaleAmount.requestFocus();
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
}
