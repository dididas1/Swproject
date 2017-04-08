package kr.or.dgit.sw_project.application.sales;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.lang.instrument.ClassFileTransformer;
import java.util.List;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;

import erp_myframework.CheckBoxPanel;
import erp_myframework.ComboPanel;
import erp_myframework.RadioPanel;
import erp_myframework.TextFieldPanel;
import kr.or.dgit.sw_project.dto.Client;
import kr.or.dgit.sw_project.dto.Delivery;
import kr.or.dgit.sw_project.dto.JoinFromSale;
import kr.or.dgit.sw_project.dto.Sale;
import kr.or.dgit.sw_project.dto.Software;
import kr.or.dgit.sw_project.service.ClientService;
import kr.or.dgit.sw_project.service.DeliveryService;
import kr.or.dgit.sw_project.service.SaleService;
import kr.or.dgit.sw_project.service.SoftwareService;

public class ContentSale extends JPanel {
	private TextFieldPanel tfpSaleCode;
	private ComboPanel<String> tfpSwName;
	private TextFieldPanel tfpSaleAmount;
	private ComboPanel<String> tfpClntName;
	private TextFieldPanel tfpOrderDate;
	private RadioPanel tfpIsExist;
	public ContentSale() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {300, 300, 50};
		gridBagLayout.rowHeights = new int[]{10, 30, 30, 30, 10};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		tfpSaleCode = new TextFieldPanel();
		tfpSaleCode.setTitle("주문번호");
		tfpSaleCode.getTf().setEditable(false);
		GridBagConstraints gbc_tfpSaleCode = new GridBagConstraints(); 
		gbc_tfpSaleCode.fill = GridBagConstraints.HORIZONTAL; 
		gbc_tfpSaleCode.insets = new Insets(0, 0, 0, 0);
		gbc_tfpSaleCode.gridx = 0;
		gbc_tfpSaleCode.gridy = 1;
		add(tfpSaleCode, gbc_tfpSaleCode);
		
		tfpSwName = new ComboPanel<>();
		tfpSwName.setTitle("품목명");
		List<Software> listSw= SoftwareService.getInstance().selectSoftwareByAll();
		Vector<String> comboitemSw = new Vector<>();
		comboitemSw.add("선택해주세요");
		for(int i=0;i<listSw.size();i++){
			comboitemSw.add(listSw.get(i).toCombobox());
		}
		tfpSwName.setComboData(comboitemSw);
		GridBagConstraints gbc_tfpSwName = new GridBagConstraints();
		gbc_tfpSwName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpSwName.insets = new Insets(0, 0, 0, 0);
		gbc_tfpSwName.gridx = 1;
		gbc_tfpSwName.gridy = 1;
		add(tfpSwName, gbc_tfpSwName);
		
		tfpSaleAmount = new TextFieldPanel();
		tfpSaleAmount.setTitle("주문수량");
		GridBagConstraints gbc_tfpSaleAmount = new GridBagConstraints();
		gbc_tfpSaleAmount.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpSaleAmount.insets = new Insets(0, 0, 0, 0);
		gbc_tfpSaleAmount.gridx = 0;
		gbc_tfpSaleAmount.gridy = 2;
		add(tfpSaleAmount, gbc_tfpSaleAmount);
		
		tfpClntName = new ComboPanel<>();
		tfpClntName.setTitle("고객상호명");
		List<Client> listCl= ClientService.getInstance().selectClientByAll();
		Vector<String> comboitemCl = new Vector<>();
		comboitemCl.add("선택해주세요");
		for(int i=0;i<listCl.size();i++){
			comboitemCl.add(listCl.get(i).toCombobox());
		}
		tfpClntName.setComboData(comboitemCl);
		GridBagConstraints gbc_tfpClntName = new GridBagConstraints();
		gbc_tfpClntName.insets = new Insets(0, 0, 0, 0);
		gbc_tfpClntName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpClntName.gridx = 1;
		gbc_tfpClntName.gridy = 2;
		add(tfpClntName, gbc_tfpClntName);
		
		tfpOrderDate = new TextFieldPanel();
		tfpOrderDate.setTitle("주문일자");
		GridBagConstraints gbc_tfpOrderDate = new GridBagConstraints();
		gbc_tfpOrderDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpOrderDate.insets = new Insets(0, 0, 0, 0);
		gbc_tfpOrderDate.gridx = 0;
		gbc_tfpOrderDate.gridy = 3;
		add(tfpOrderDate, gbc_tfpOrderDate);
		
		tfpIsExist = new RadioPanel();
		tfpIsExist.setRaidoItems("입금","미입금");
		GridBagConstraints gbc_tfpIsExist = new GridBagConstraints();
		gbc_tfpIsExist.insets = new Insets(0, 0, 0, 0);
		gbc_tfpIsExist.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpIsExist.gridx = 1;
		gbc_tfpIsExist.gridy = 3;
		add(tfpIsExist, gbc_tfpIsExist);
		initSetting();
	}
	
	
	public void initSetting(){ //코드 자동세팅 다른필드 초기화
		List<Sale> list =SaleService.getInstance().selectSaleByAll();
		tfpSaleCode.setTfValue(String.format("SL%03d", list.size()+1));
		tfpSwName.setSelectedItem(0);
		tfpSaleAmount.setTfValue("");
		tfpClntName.setSelectedItem(0);
		tfpOrderDate.setTfValue("");
		tfpSaleAmount.requestFocus();
	}
	public Sale getObject(){ 
		String saleCode = tfpSaleCode.getTfValue();
		String client = (String) tfpClntName.getSelectItem();
		String software = (String) tfpSwName.getSelectItem();
		int saleAmount = Integer.parseInt(tfpSaleAmount.getTfValue());
		String orderDate = tfpOrderDate.getTfValue();
		int supplyPrice = DeliveryService.getInstance().getSuppyPrice(new Delivery(new Software(software))).getSupplyPrice();
		int salePrice =  SoftwareService.getInstance().selectByNoSoftware(new Software(software)).getSalePrice();
		boolean isDeposit = false;
		if(tfpIsExist.getSelectedItem().equals("입금")){
					isDeposit=true;
		}else{
			isDeposit=false;
		}
		return new Sale(saleCode, new Client(client), new Software(software), saleAmount,isDeposit ,orderDate, supplyPrice, salePrice);
	}
	
	public void setSaleContent(JoinFromSale joinFromSale){ //text필드에 값세팅
		tfpSaleCode.setTfValue(joinFromSale.getSale().getSaleCode());
		tfpClntName.setSelectedItem(joinFromSale.getClient().getClntName());
		tfpSwName.setSelectedItem(joinFromSale.getSoftware().getSwName());
		tfpSaleAmount.setTfValue(String.valueOf(joinFromSale.getSale().getSaleAmount()));
		tfpOrderDate.setTfValue(joinFromSale.getSale().getOrderDate());
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
