package kr.or.dgit.sw_project.application.delivery;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Date;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import erp_myframework.ComboPanel;
import erp_myframework.TextFieldPanel;

import kr.or.dgit.sw_project.dto.Delivery;
import kr.or.dgit.sw_project.dto.Software;
import kr.or.dgit.sw_project.dto.SupplyCompany;

public class ContentDelivery extends JPanel {
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private TextFieldPanel tfpDelCode; //납품번호
	private ComboPanel tfpDeSwName; //소프트웨어이름
	private TextFieldPanel tfpDelAmount;  //납품수량
	private ComboPanel tfpCompName; //납품회사이름
	private TextFieldPanel tfpDelOrderDate; //납품일자
	private TextFieldPanel tfpSupplyAmount; //공급가격
	
	
	

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
		
		tfpDeSwName = new ComboPanel();
		
		
		tfpDeSwName.setTitle("품목명");
		GridBagConstraints gbc_tfpDeSwName = new GridBagConstraints();
		gbc_tfpDeSwName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpDeSwName.insets = new Insets(0, 0, 0, 0);
		gbc_tfpDeSwName.gridx = 1;
		gbc_tfpDeSwName.gridy = 1;
		add(tfpDeSwName, gbc_tfpDeSwName);
		
		tfpDelAmount = new TextFieldPanel();
		tfpDelAmount.setTitle("납품수량");
		GridBagConstraints gbc_tfpDelAmount = new GridBagConstraints();
		gbc_tfpDelAmount.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpDelAmount.insets = new Insets(0, 0, 0, 0);
		gbc_tfpDelAmount.gridx = 0;
		gbc_tfpDelAmount.gridy = 2;
		add(tfpDelAmount, gbc_tfpDelAmount);
		
		tfpCompName = new ComboPanel();
		tfpCompName.setTitle("납품회사");
		GridBagConstraints gbc_tfpCompName = new GridBagConstraints();
		gbc_tfpCompName.insets = new Insets(0, 0, 0, 0);
		gbc_tfpCompName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpCompName.gridx = 1;
		gbc_tfpCompName.gridy = 2;
		add(tfpCompName, gbc_tfpCompName);
		
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
	}
	
	public Delivery getObject(){
		String deliveryCode = tfpDelCode.getTfValue().trim();
		SupplyCompany supplyCompany = (SupplyCompany) tfpCompName.getSelectItem();
		Software softWare = (Software) tfpDeSwName.getSelectItem();
		int supplyPrice =  Integer.valueOf(tfpSupplyAmount.getTfValue().trim());
		int supplyAmount = Integer.valueOf(tfpDelAmount.getTfValue().trim());
		Date orderDate = new Date();
		return new Delivery(deliveryCode, supplyCompany, softWare, supplyPrice, supplyAmount, orderDate);
	}
	ddddddd
}