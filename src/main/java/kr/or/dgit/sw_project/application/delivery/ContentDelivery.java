package kr.or.dgit.sw_project.application.delivery;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import erp_myframework.ComboPanel;
import erp_myframework.TextFieldPanel;

import kr.or.dgit.sw_project.dto.Delivery;
import kr.or.dgit.sw_project.dto.Software;
import kr.or.dgit.sw_project.dto.SupplyCompany;
import kr.or.dgit.sw_project.service.DeliveryService;
import kr.or.dgit.sw_project.service.SoftwareService;
import kr.or.dgit.sw_project.service.SupplyCompService;

public class ContentDelivery extends JPanel {
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private TextFieldPanel tfpDelCode; //납품번호
	private ComboPanel<String> tfpDeSwName; //소프트웨어이름
	private TextFieldPanel tfpDelAmount;  //납품수량
	private ComboPanel<String> tfpCompName; //납품회사이름
	private TextFieldPanel tfpDelOrderDate; //납품일자
	private TextFieldPanel tfpSupplyAmount; //공급가격
	private List<SupplyCompany> compList;
	private List<Software> swList;
	
	

	public ContentDelivery() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {300, 300, 50};
		gridBagLayout.rowHeights = new int[]{10, 30, 30, 30, 10};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		tfpDelCode = new TextFieldPanel();
		tfpDelCode.setTitle("납품번호");
		GridBagConstraints gbc_tfpDelCode = new GridBagConstraints(); 
		gbc_tfpDelCode.fill = GridBagConstraints.HORIZONTAL; 
		gbc_tfpDelCode.insets = new Insets(0, 0, 0, 0);
		gbc_tfpDelCode.gridx = 0;
		gbc_tfpDelCode.gridy = 1;
		add(tfpDelCode, gbc_tfpDelCode);	
		
	      
	      
	      
	    tfpDeSwName = new ComboPanel<>();
		tfpDeSwName.setTitle("품목명");
		swList = SoftwareService.getInstance().selectSoftwareByAll();
		Vector<String> swCom = new Vector<>();
		for(int i=0;i<swList.size();i++){
			swCom.add(swList.get(i).toCombobox());
		}
		tfpDeSwName.setComboData(swCom);
		GridBagConstraints gbc_tfpDeSwName = new GridBagConstraints();
		gbc_tfpDeSwName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpDeSwName.insets = new Insets(0, 0, 0, 0);
		gbc_tfpDeSwName.gridx = 1;
		gbc_tfpDeSwName.gridy = 1;
		add(tfpDeSwName, gbc_tfpDeSwName);
		
		//setComboSoftware();
		
	      
		
		tfpDelAmount = new TextFieldPanel();
		tfpDelAmount.setTitle("납품수량");
		GridBagConstraints gbc_tfpDelAmount = new GridBagConstraints();
		gbc_tfpDelAmount.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpDelAmount.insets = new Insets(0, 0, 0, 0);
		gbc_tfpDelAmount.gridx = 0;
		gbc_tfpDelAmount.gridy = 2;
		add(tfpDelAmount, gbc_tfpDelAmount);
		
		
		
		tfpCompName = new ComboPanel<>();
		tfpCompName.setTitle("납품회사");
		compList = SupplyCompService.getInstance().selectSupplyCompByAll();
		Vector<String> supCom = new Vector<>();		
		for(int i=0;i<compList.size();i++){
			supCom.add(compList.get(i).toCombobox());
		}
		tfpCompName.setComboData(supCom);
		GridBagConstraints gbc_tfpCompName = new GridBagConstraints();
		gbc_tfpCompName.insets = new Insets(0, 0, 0, 0);
		gbc_tfpCompName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpCompName.gridx = 1;
		gbc_tfpCompName.gridy = 2;
		add(tfpCompName, gbc_tfpCompName);
		
		//setComboSupplyCompany();
		
		
		tfpDelOrderDate = new TextFieldPanel();
		tfpDelOrderDate.setTitle("납품일자");
		GridBagConstraints gbc_tfpDelOrderDate = new GridBagConstraints();
		gbc_tfpDelOrderDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpDelOrderDate.insets = new Insets(0, 0, 0, 0);
		gbc_tfpDelOrderDate.gridx = 0;
		gbc_tfpDelOrderDate.gridy = 3;
		add(tfpDelOrderDate, gbc_tfpDelOrderDate);
		
		tfpSupplyAmount = new TextFieldPanel();
		tfpSupplyAmount.setTitle("공급가격");
		GridBagConstraints gbc_tfpIsExist = new GridBagConstraints();
		gbc_tfpIsExist.insets = new Insets(0, 0, 0, 0);
		gbc_tfpIsExist.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpIsExist.gridx = 1;
		gbc_tfpIsExist.gridy = 3;
		add(tfpSupplyAmount, gbc_tfpIsExist);
		
		setDeliveryCode();
	}
	public void resetField(){//필드 초기화
		setDeliveryCode();
		tfpCompName.setSelectedItem(0);
		tfpDeSwName.setSelectedItem(0);
		tfpDelAmount.setTfValue("");
		tfpDelOrderDate.setTfValue("");
		tfpSupplyAmount.setTfValue("");
	}
	private void setDeliveryCode() {//맨 마지막 코드 다음꺼로 세팅
		//tfpDelCode.setTfValue(DeliveryService.getInstance().lastDeliveryCode());
		List<Delivery> list = DeliveryService.getInstance().selectDeliveryByAll();
		list.get(list.size() - 1).getDelCode();
		String value = String.format(getDeliveryCode(),	Integer.parseInt(list.get(list.size()-1).getDelCode().substring(2)) + 1);
		tfpDelCode.setTfValue(value);
		tfpDelCode.getTf().setFocusable(false);
	}
	private String getDeliveryCode() {		//ref coffee
		return "DL%03d";
	}
	private void setComboSupplyCompany() {//combopanel에 공급회사 띄우기
		List<SupplyCompany> list = SupplyCompService.getInstance().selectSupplyCompByAll();
		Vector<String> v = new Vector<>();		
		for(int i=0; i<list.size(); i++){
			v.add(list.get(i).toCombobox());
		}
		tfpCompName.setComboData(v);
		
	}
	public void setComboSoftware(){//combopanel에 소프트웨어 띄우기
		List<Software> list = SoftwareService.getInstance().selectSoftwareByAll();
		Vector<String> v = new Vector<>();		
		for(int i=0; i<list.size(); i++){
			v.add(list.get(i).toCombobox());
		}
		tfpDeSwName.setComboData(v);	
	}
	public Delivery getObject(){//fu....입력값들
		String deliveryCode = tfpDelCode.getTfValue();
		String supplyCompany = compList.get(tfpCompName.getSelectedIndex()).getCompCode();
		String softWare = swList.get(tfpDeSwName.getSelectedIndex()).getSwCode();
		int supplyPrice =  Integer.parseInt(tfpSupplyAmount.getTfValue());
		int supplyAmount = Integer.parseInt(tfpDelAmount.getTfValue());
		String orderDate = tfpDelOrderDate.getTfValue();
		return new Delivery(deliveryCode, new SupplyCompany(supplyCompany), new Software(softWare), supplyPrice, supplyAmount, orderDate);
	}
	public void setObject(Object[] deliveryObj) {//테이블의 값들 필드에 들고오기
		// TODO Auto-generated method stub
		tfpDelCode.setTfValue(String.valueOf(deliveryObj[0]));
		tfpCompName.setSelectedItem((String) deliveryObj[1]);
		tfpDeSwName.setSelectedItem((String) deliveryObj[2]);
		tfpSupplyAmount.setTfValue(String.valueOf(deliveryObj[3]));
		tfpDelAmount.setTfValue(String.valueOf(deliveryObj[4]));
		tfpDelOrderDate.setTfValue(String.valueOf(deliveryObj[5]));		
		
	}

	public TextFieldPanel getTfpDelCode() {
		return tfpDelCode;
	}

	public ComboPanel<String> getTfpDeSwName() {
		return tfpDeSwName;
	}

	public TextFieldPanel getTfpDelAmount() {
		return tfpDelAmount;
	}

	public ComboPanel<String> getTfpCompName() {
		return tfpCompName;
	}

	public TextFieldPanel getTfpDelOrderDate() {
		return tfpDelOrderDate;
	}

	public TextFieldPanel getTfpSupplyAmount() {
		return tfpSupplyAmount;
	}
	
	
}