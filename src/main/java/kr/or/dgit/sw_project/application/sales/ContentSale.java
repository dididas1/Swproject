package kr.or.dgit.sw_project.application.sales;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import erp_myframework.CheckBoxPanel;
import erp_myframework.ComboPanel;
import erp_myframework.TextFiledPanel;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;

public class ContentSale extends JPanel {
	public ContentSale() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {300, 300, 50};
		gridBagLayout.rowHeights = new int[]{10, 30, 30, 30, 10};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		TextFiledPanel tfpSaleCode = new TextFiledPanel();
		tfpSaleCode.setTitle("주문번호");
		GridBagConstraints gbc_tfpSaleCode = new GridBagConstraints(); 
		gbc_tfpSaleCode.fill = GridBagConstraints.HORIZONTAL; 
		gbc_tfpSaleCode.insets = new Insets(0, 0, 0, 0);
		gbc_tfpSaleCode.gridx = 0;
		gbc_tfpSaleCode.gridy = 1;
		add(tfpSaleCode, gbc_tfpSaleCode);
		
		ComboPanel tfpSwName = new ComboPanel();
		tfpSwName.setTitle("품목명");
		GridBagConstraints gbc_tfpSwName = new GridBagConstraints();
		gbc_tfpSwName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpSwName.insets = new Insets(0, 0, 0, 0);
		gbc_tfpSwName.gridx = 1;
		gbc_tfpSwName.gridy = 1;
		add(tfpSwName, gbc_tfpSwName);
		
		TextFiledPanel tfpSaleAmount = new TextFiledPanel();
		tfpSaleAmount.setTitle("주문수량");
		GridBagConstraints gbc_tfpSaleAmount = new GridBagConstraints();
		gbc_tfpSaleAmount.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpSaleAmount.insets = new Insets(0, 0, 0, 0);
		gbc_tfpSaleAmount.gridx = 0;
		gbc_tfpSaleAmount.gridy = 2;
		add(tfpSaleAmount, gbc_tfpSaleAmount);
		
		ComboPanel tfpClntName = new ComboPanel();
		tfpClntName.setTitle("고객상호명");
		GridBagConstraints gbc_tfpClntName = new GridBagConstraints();
		gbc_tfpClntName.insets = new Insets(0, 0, 0, 0);
		gbc_tfpClntName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpClntName.gridx = 1;
		gbc_tfpClntName.gridy = 2;
		add(tfpClntName, gbc_tfpClntName);
		
		TextFiledPanel tfpOrderDate = new TextFiledPanel();
		tfpOrderDate.setTitle("주문일자");
		GridBagConstraints gbc_tfpOrderDate = new GridBagConstraints();
		gbc_tfpOrderDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpOrderDate.insets = new Insets(0, 0, 0, 0);
		gbc_tfpOrderDate.gridx = 0;
		gbc_tfpOrderDate.gridy = 3;
		add(tfpOrderDate, gbc_tfpOrderDate);
		
		CheckBoxPanel tfpIsExist = new CheckBoxPanel();
		tfpIsExist.setTitle("입금여부");
		GridBagConstraints gbc_tfpIsExist = new GridBagConstraints();
		gbc_tfpIsExist.insets = new Insets(0, 0, 0, 0);
		gbc_tfpIsExist.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpIsExist.gridx = 1;
		gbc_tfpIsExist.gridy = 3;
		add(tfpIsExist, gbc_tfpIsExist);
	}
}
