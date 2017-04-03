package kr.or.dgit.sw_project.application.client;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import erp_myframework.TextFiledPanel;

public class ContentClient extends JPanel {
	private JTextField textField;
	public ContentClient() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {300, 50, 50};
		gridBagLayout.rowHeights = new int[] {30, 30, 30, 30, 30, 30};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		JLabel label = new JLabel("거래회사 관리");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("굴림", Font.BOLD, 14));
		
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.HORIZONTAL;
		gbc_label.insets = new Insets(10, 10, 10, 10);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		gbc_label.gridwidth = 2;
		add(label, gbc_label);
		
		TextFiledPanel tfpClientCode = new TextFiledPanel();
		tfpClientCode.setTitle("고객사 번호");
		GridBagConstraints gbc_tfptfpClientCode = new GridBagConstraints(); 
		gbc_tfptfpClientCode.fill = GridBagConstraints.HORIZONTAL; 
		gbc_tfptfpClientCode.insets = new Insets(0, 0, 5, 5);
		gbc_tfptfpClientCode.gridx = 0;
		gbc_tfptfpClientCode.gridy = 1;
		add(tfpClientCode, gbc_tfptfpClientCode);
		
		
		TextFiledPanel tfpClientName = new TextFiledPanel();
		tfpClientName.setTitle("고객사 이름");
		GridBagConstraints gbc_tfptfpClientName = new GridBagConstraints();
		gbc_tfptfpClientName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfptfpClientName.insets = new Insets(0, 0, 5, 5);
		gbc_tfptfpClientName.gridx = 0;
		gbc_tfptfpClientName.gridy = 2;
		add(tfpClientName, gbc_tfptfpClientName);
		
		
		TextFiledPanel tfpCleintTel = new TextFiledPanel();
		tfpCleintTel.setTitle("  전 화 번 호");
		GridBagConstraints gbc_tfptfpCleintTel = new GridBagConstraints();
		gbc_tfptfpCleintTel.insets = new Insets(0, 0, 5, 5);
		gbc_tfptfpCleintTel.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfptfpCleintTel.gridx = 0;
		gbc_tfptfpCleintTel.gridy = 3;
		add(tfpCleintTel, gbc_tfptfpCleintTel);
		
		TextFiledPanel tfpClientAddr = new TextFiledPanel();
		tfpClientAddr.setTitle("주          소");
		GridBagConstraints gbc_tfptfpClientAddr = new GridBagConstraints();
		gbc_tfptfpClientAddr.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfptfpClientAddr.insets = new Insets(0, 0, 5, 5);
		gbc_tfptfpClientAddr.gridx = 0;
		gbc_tfptfpClientAddr.gridy = 4;
		add(tfpClientAddr, gbc_tfptfpClientAddr);
		
		JButton button = new JButton("우편번호검색");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 1;
		gbc_button.gridy = 4;
		add(button, gbc_button);
		
		TextFiledPanel tfadr= new TextFiledPanel();
		tfadr.setTitle("");
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 5;
		add(tfadr, gbc_textField);
		
		
	}
}
