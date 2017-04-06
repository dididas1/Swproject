package kr.or.dgit.sw_project.application.showlist;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import erp_myframework.ComboPanel;
import javafx.scene.control.ComboBox;
import javafx.scene.input.ScrollEvent.VerticalTextScrollUnits;
import kr.or.dgit.sw_project.dto.Category;
import kr.or.dgit.sw_project.dto.Client;
import kr.or.dgit.sw_project.dto.Software;
import kr.or.dgit.sw_project.service.CategoryService;
import kr.or.dgit.sw_project.service.ClientService;
import kr.or.dgit.sw_project.service.SoftwareService;

public class ContentList extends JPanel {
	private JButton btnGroupAllFind;
	private JButton btnSwAllFind;
	private JButton btnClntAllFind;
	
	
	public ContentList() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {100 ,300, 50, 200};
		gridBagLayout.rowHeights = new int[]{10, 30, 30, 30, 10};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		ComboPanel<String> tfpSwName = new ComboPanel();
		List<Software> list=  SoftwareService.getInstance().selectSoftwareByAll();
		tfpSwName.setTitle("품목명");
	
		GridBagConstraints gbc_tfpSwName = new GridBagConstraints();
		gbc_tfpSwName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpSwName.insets = new Insets(0, 0, 0, 0);
		gbc_tfpSwName.gridx = 1;
		gbc_tfpSwName.gridy = 1;
		add(tfpSwName, gbc_tfpSwName);
		
		btnSwAllFind = new JButton("전체");
		GridBagConstraints gbc_btnSwAllFind = new GridBagConstraints();
		gbc_btnSwAllFind.insets = new Insets(0, 0, 0, 0);
		gbc_btnSwAllFind.gridx = 2;
		gbc_btnSwAllFind.gridy = 1;
		add(btnSwAllFind, gbc_btnSwAllFind);
		
		
		ComboPanel<String> tfpClntName = new ComboPanel();
		tfpClntName.setTitle("고객상호");
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
		
		btnClntAllFind = new JButton("전체");
		GridBagConstraints gbc_btnClntAllFind = new GridBagConstraints();
		gbc_btnClntAllFind.insets = new Insets(0, 0, 0, 0);
		gbc_btnClntAllFind.gridx = 2;
		gbc_btnClntAllFind.gridy = 2;
		add(btnClntAllFind, gbc_btnClntAllFind);
		
		ComboPanel<String> tfpGroup = new ComboPanel();
		tfpGroup.setTitle("그룹별");
		List<Category> listGr= CategoryService.getInstance().selectCategoryByAll();
		Vector<String> comboitemGr = new Vector<>();
		comboitemGr.add("선택해주세요");
		for(int i=0;i<listGr.size();i++){
				comboitemGr.add(listGr.get(i).toCombobox());
		}
		tfpGroup.setComboData(comboitemGr);
		GridBagConstraints gbc_tfpGroup = new GridBagConstraints();
		gbc_tfpGroup.insets = new Insets(0, 0, 0, 0);
		gbc_tfpGroup.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpGroup.gridx = 1;
		gbc_tfpGroup.gridy = 3;
		add(tfpGroup, gbc_tfpGroup);
		
		btnGroupAllFind = new JButton("전체");
		GridBagConstraints gbc_btnGroupAllFind = new GridBagConstraints();
		gbc_btnGroupAllFind.insets = new Insets(0, 0, 0, 0);
		gbc_btnGroupAllFind.gridx = 2;
		gbc_btnGroupAllFind.gridy = 3;
		add(btnGroupAllFind, gbc_btnGroupAllFind);
	}
	
	


	public JButton getBtnGroupAllFind() {
		return btnGroupAllFind;
	}



	public JButton getBtnSwAllFind() {
		return btnSwAllFind;
	}



	public JButton getBtnClntAllFind() {
		return btnClntAllFind;
	}



	
	
	
}
