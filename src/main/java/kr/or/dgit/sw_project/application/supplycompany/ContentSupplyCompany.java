package kr.or.dgit.sw_project.application.supplycompany;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import erp_myframework.TextFiledPanel;

public class ContentSupplyCompany extends JPanel {
	private JTextField textField;
	public ContentSupplyCompany() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {300, 50, 50};
		gridBagLayout.rowHeights = new int[] {30, 30, 30, 30, 30, 30, 20};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		TextFiledPanel tfpSupplyCompanyName = new TextFiledPanel();
		tfpSupplyCompanyName.setTitle("회사명");
		GridBagConstraints gbc_tfpSupplyCompanyName = new GridBagConstraints();
		gbc_tfpSupplyCompanyName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpSupplyCompanyName.insets = new Insets(0, 0, 5, 5);
		gbc_tfpSupplyCompanyName.gridx = 0;
		gbc_tfpSupplyCompanyName.gridy = 1;
		add(tfpSupplyCompanyName, gbc_tfpSupplyCompanyName);
		
		TextFiledPanel tfpSupplyCompanyCode = new TextFiledPanel();
		tfpSupplyCompanyCode.setTitle("회사번호");
		GridBagConstraints gbc_tfpSupplyCompanyCode = new GridBagConstraints(); 
		gbc_tfpSupplyCompanyCode.fill = GridBagConstraints.HORIZONTAL; 
		gbc_tfpSupplyCompanyCode.insets = new Insets(0, 0, 5, 5);
		gbc_tfpSupplyCompanyCode.gridx = 0;
		gbc_tfpSupplyCompanyCode.gridy = 2;
		add(tfpSupplyCompanyCode, gbc_tfpSupplyCompanyCode);
		
		TextFiledPanel tfpSupplyCompanyTel = new TextFiledPanel();
		tfpSupplyCompanyTel.setTitle("전화번호");
		GridBagConstraints gbc_tfpSupplyCompanyTel = new GridBagConstraints();
		gbc_tfpSupplyCompanyTel.insets = new Insets(0, 0, 5, 5);
		gbc_tfpSupplyCompanyTel.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpSupplyCompanyTel.gridx = 0;
		gbc_tfpSupplyCompanyTel.gridy = 3;
		add(tfpSupplyCompanyTel, gbc_tfpSupplyCompanyTel);
		
		TextFiledPanel tfpSupplyCompanyAd = new TextFiledPanel();
		tfpSupplyCompanyAd.setTitle("주소");
		GridBagConstraints gbc_tfpSupplyCompanyAd = new GridBagConstraints();
		gbc_tfpSupplyCompanyAd.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpSupplyCompanyAd.insets = new Insets(0, 0, 5, 5);
		gbc_tfpSupplyCompanyAd.gridx = 0;
		gbc_tfpSupplyCompanyAd.gridy = 4;
		add(tfpSupplyCompanyAd, gbc_tfpSupplyCompanyAd);
		
		JButton button = new JButton("우편번호검색");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 1;
		gbc_button.gridy = 4;
		add(button, gbc_button);
		
		TextFiledPanel tfadr= new TextFiledPanel();
		tfadr.setTitle("상세 주소");
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 5;
		add(tfadr, gbc_textField);
		
		
	}
}
