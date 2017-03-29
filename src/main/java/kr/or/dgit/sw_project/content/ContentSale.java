package kr.or.dgit.sw_project.content;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import erp_myframework.CheckBoxPanel;
import erp_myframework.ComboPanel;
import erp_myframework.RadioPanel;
import erp_myframework.TextFiledPanel;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import java.awt.Font;

public class ContentSale extends JPanel {
	private static final String pattern = "dd-MMM-yy";
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public ContentSale() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {200, 200,50};
		gridBagLayout.rowHeights = new int[]{0, 50, 50, 50};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		JLabel label = new JLabel("소프트웨어 출고");
		label.setFont(new Font("굴림", Font.PLAIN, 18));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.HORIZONTAL;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 0;
		add(label, gbc_label);
		
		TextFiledPanel tfpSaleCode = new TextFiledPanel();
		tfpSaleCode.setTitle("주문번호");
		GridBagConstraints gbc_tfpSaleCode = new GridBagConstraints(); 
		gbc_tfpSaleCode.fill = GridBagConstraints.HORIZONTAL; 
		gbc_tfpSaleCode.insets = new Insets(0, 0, 5, 5);
		gbc_tfpSaleCode.gridx = 0;
		gbc_tfpSaleCode.gridy = 1;
		add(tfpSaleCode, gbc_tfpSaleCode);
		
		ComboPanel tfpSwName = new ComboPanel();
		tfpSwName.setTitle("품목명");
		GridBagConstraints gbc_tfpSwName = new GridBagConstraints();
		gbc_tfpSwName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpSwName.insets = new Insets(0, 0, 5, 5);
		gbc_tfpSwName.gridx = 1;
		gbc_tfpSwName.gridy = 1;
		add(tfpSwName, gbc_tfpSwName);
		
		TextFiledPanel tfpSaleAmount = new TextFiledPanel();
		tfpSaleAmount.setTitle("주문수량");
		GridBagConstraints gbc_tfpSaleAmount = new GridBagConstraints();
		gbc_tfpSaleAmount.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpSaleAmount.insets = new Insets(0, 0, 5, 5);
		gbc_tfpSaleAmount.gridx = 0;
		gbc_tfpSaleAmount.gridy = 2;
		add(tfpSaleAmount, gbc_tfpSaleAmount);
		
		ComboPanel tfpClntName = new ComboPanel();
		tfpClntName.setTitle("고객상호");
		GridBagConstraints gbc_tfpClntName = new GridBagConstraints();
		gbc_tfpClntName.insets = new Insets(0, 0, 5, 5);
		gbc_tfpClntName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpClntName.gridx = 1;
		gbc_tfpClntName.gridy = 2;
		add(tfpClntName, gbc_tfpClntName);
		
		TextFiledPanel tfpOrderDate = new TextFiledPanel();
		tfpOrderDate.setTitle("주문일자");
		GridBagConstraints gbc_tfpOrderDate = new GridBagConstraints();
		gbc_tfpOrderDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpOrderDate.insets = new Insets(0, 0, 0, 5);
		gbc_tfpOrderDate.gridx = 0;
		gbc_tfpOrderDate.gridy = 3;
		add(tfpOrderDate, gbc_tfpOrderDate);
		
		RadioPanel tfpIsExist = new RadioPanel();
		tfpIsExist.setTitle("입금여부");
		tfpIsExist.setRaidoItems("입금","미입금");
		GridBagConstraints gbc_tfpIsExist = new GridBagConstraints();
		gbc_tfpIsExist.insets = new Insets(0, 0, 0, 5);
		gbc_tfpIsExist.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpIsExist.gridx = 1;
		gbc_tfpIsExist.gridy = 3;
		add(tfpIsExist, gbc_tfpIsExist);
	}
}
