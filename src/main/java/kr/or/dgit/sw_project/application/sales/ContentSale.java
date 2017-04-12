package kr.or.dgit.sw_project.application.sales;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import erp_myframework.ComboPanel;
import erp_myframework.DatePanel;
import erp_myframework.RadioPanel;
import erp_myframework.TextFieldPanel;
import kr.or.dgit.sw_project.application.delivery.ViewDelivery;
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
	private RadioPanel tfpIsExist;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일");

	private List<Software> listSw;
	private List<Client> listCl;
	
	private ViewDelivery viewDelivery;
	private DatePanel dpOrderDate;
	
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
		GridBagConstraints gbc_tfpClntName = new GridBagConstraints();
		gbc_tfpClntName.insets = new Insets(0, 0, 0, 0);
		gbc_tfpClntName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpClntName.gridx = 1;
		gbc_tfpClntName.gridy = 2;
		add(tfpClntName, gbc_tfpClntName);

		dpOrderDate = new DatePanel();
		dpOrderDate.setTitle("주문날짜");
		GridBagConstraints gbc_tfpOrderDate = new GridBagConstraints();
		gbc_tfpOrderDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpOrderDate.insets = new Insets(0, 0, 0, 0);
		gbc_tfpOrderDate.gridx = 0;
		gbc_tfpOrderDate.gridy = 3;
		add(dpOrderDate, gbc_tfpOrderDate);

		tfpIsExist = new RadioPanel();
		tfpIsExist.setTitle("입금확인");
		tfpIsExist.setRaidoItems("입금","미입금");
		tfpIsExist.setSelectedItem(0);
		GridBagConstraints gbc_tfpIsExist = new GridBagConstraints();
		gbc_tfpIsExist.insets = new Insets(0, 0, 0, 0);
		gbc_tfpIsExist.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpIsExist.gridx = 1;
		gbc_tfpIsExist.gridy = 3;
		add(tfpIsExist, gbc_tfpIsExist);
		setClntComboData();
		setSwComboData();
		initSetting();
		
		//viewDelivery.setContentSale(ContentSale.this);
	}


	public void initSetting(){ //코드 자동세팅 다른필드 초기화
		List<Sale> list =SaleService.getInstance().selectSaleByAll();
		tfpSaleCode.setTfValue(String.format("SL%03d", list.size()+1));
		tfpSwName.setSelectedItem(0);
		tfpSaleAmount.setTfValue("");
		tfpClntName.setSelectedItem(0);
		dpOrderDate.getDateCombobox().setDate(new Date());
		tfpSaleAmount.requestFocus();
		tfpIsExist.setSelectedItem(0);
	}
	
	public Sale getObject(){  //개짱나서 디지는줄 겟오브젝트
		boolean isDeposit;
		String saleCode = tfpSaleCode.getTfValue();
		System.out.println(listCl.get(tfpClntName.getSelectedIndex()-1).getClntCode());
		String client = listCl.get(tfpClntName.getSelectedIndex()-1).getClntCode();
		String software = listSw.get(tfpSwName.getSelectedIndex()-1).getSwCode();
		int saleAmount = Integer.parseInt(tfpSaleAmount.getTfValue());
		String orderDate = dpOrderDate.getTfDate();
		int supplyPrice = DeliveryService.getInstance().getSuppyPrice(new Delivery(new Software(software))).getSupplyPrice();
		int salePrice =  listSw.get(tfpSwName.getSelectedIndex()-1).getSalePrice();
		if(tfpIsExist.getSelectedItem().equals("입금")){
			isDeposit=true;
		}else{
			isDeposit=false;
		}
		Client cli= new Client();
		cli.setClntCode(client);
		return new Sale(saleCode, cli, new Software(software), saleAmount, isDeposit ,orderDate, supplyPrice, salePrice);
	}


	public void setSaleContent(JoinFromSale joinFromSale){ //text필드에 값세팅
		System.out.println(joinFromSale.toString());
		tfpSaleCode.setTfValue(joinFromSale.getSale().getSaleCode());
		tfpSwName.setSelectedItem(joinFromSale.getSoftware().getSwName()+ String.format(" (재고: %s)", joinFromSale.getSoftware().getSwInven()));
		tfpClntName.setSelectedItem(joinFromSale.getClient().getClntName());
		tfpSaleAmount.setTfValue(String.valueOf(joinFromSale.getSale().getSaleAmount()));
		dpOrderDate.setTfDate(joinFromSale.getSale().getOrderDate());
		if(joinFromSale.getSale().isDeposit()){
			tfpIsExist.setSelectedItem(0);
		}else{
			tfpIsExist.setSelectedItem(1);
		}
	}
	
	public void setClntComboData(){
		tfpClntName.getTf().removeAllItems();
		listCl = ClientService.getInstance().selectClientByAll();
		System.out.println(listCl);
		Vector<String> comboitemCl = new Vector<>();
		comboitemCl.removeAllElements();
		comboitemCl.add("선택해주세요");
		for(int i=0;i<listCl.size();i++){
			comboitemCl.add(listCl.get(i).toCombobox());
		}
		tfpClntName.setComboData(comboitemCl);
		
	}
	
	public void setSwComboData(){
		tfpSwName.getTf().removeAllItems();
		listSw = SoftwareService.getInstance().selectSoftwareByAll();
		Vector<String> comboitemSw = new Vector<>();
		comboitemSw.removeAllElements();
		comboitemSw.add("선택해주세요");
		for(int i=0;i<listSw.size();i++){
			comboitemSw.add(listSw.get(i).toCombobox());
		}
		tfpSwName.setComboData(comboitemSw);
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
	
	public boolean isPatternCheck(){// 판매가란에 숫자만(9자리 미만)입력 하였는지 체크
		boolean isPtenCh = false;
		if(Pattern.matches("^[0-9]{1,9}$", tfpSaleAmount.getTfValue())==false){
			isPtenCh = true;
		}
		return isPtenCh;
	}

	public TextFieldPanel getTfpSaleCode() {
		return tfpSaleCode;
	}

	public TextFieldPanel getTfpSaleAmount() {
		return tfpSaleAmount;
	}

	public ComboPanel<String> getTfpSwName() {
		return tfpSwName;
	}

	public ComboPanel<String> getTfpClntName() {
		return tfpClntName;
	}

	

	public DatePanel getDpOrderDate() {
		return dpOrderDate;
	}


	public RadioPanel getTfpIsExist() {
		return tfpIsExist;
	}
}
