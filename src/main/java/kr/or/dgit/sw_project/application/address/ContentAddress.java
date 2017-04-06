package kr.or.dgit.sw_project.application.address;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import erp_myframework.ComboPanel;
import kr.or.dgit.sw_project.dto.Category;
import kr.or.dgit.sw_project.dto.Client;
import kr.or.dgit.sw_project.dto.Software;
import kr.or.dgit.sw_project.service.CategoryService;
import kr.or.dgit.sw_project.service.ClientService;
import kr.or.dgit.sw_project.service.SoftwareService;
import erp_myframework.TextFiledPanel;

public class ContentAddress extends JPanel{
	
	
	private ComboPanel<String> tfpSiGunGu;
	private ComboPanel<String> tfpSiDo;
	private TextFiledPanel tfpDoro;

	public ContentAddress() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {300, 50};
		gridBagLayout.rowHeights = new int[]{10, 30, 30, 30, 10};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		tfpSiDo = new ComboPanel();
		Vector<String> cbSiDo= new Vector<>();
		cbSiDo.add("선택해주세요");
		tfpSiDo.setComboData(cbSiDo);
		tfpSiDo.setTitle("도/특별/광역시");
		GridBagConstraints gbc_tfpSiDo = new GridBagConstraints();
		gbc_tfpSiDo.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpSiDo.insets = new Insets(0, 0, 5, 5);
		gbc_tfpSiDo.gridx = 0;
		gbc_tfpSiDo.gridy = 1;
		add(tfpSiDo, gbc_tfpSiDo);
		

		tfpSiGunGu = new ComboPanel();
		tfpSiGunGu.setTitle("시/군/구");
		Vector<String> cbSiGunGu = new Vector<>();
		cbSiGunGu.add("선택해주세요");
		tfpSiGunGu.setComboData(cbSiGunGu);
		GridBagConstraints gbc_tfpSiGunGu = new GridBagConstraints();
		gbc_tfpSiGunGu.insets = new Insets(0, 0, 5, 5);
		gbc_tfpSiGunGu.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpSiGunGu.gridx = 0;
		gbc_tfpSiGunGu.gridy = 2;
		add(tfpSiGunGu, gbc_tfpSiGunGu);
		
		tfpDoro = new TextFiledPanel();
		tfpDoro.setTitle("도로명");
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 3;
		add(tfpDoro, gbc_panel);
	}

	public void clear() {
		tfpSiGunGu.setSelectedItem(0);
		tfpSiDo.setSelectedItem(0);
		tfpDoro.setTfValue("");
	}
}
