package kr.or.dgit.sw_project.application.showlist;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import javax.swing.JLabel;
import erp_myframework.DatePanel;

public class ContentList extends JPanel {
	private JButton btnGroupAllFind;
	private JButton btnSwAllFind;
	private JButton btnClntAllFind;
	private List<Client> listClnt;
	private List<Software> listSw;
	private List<Category> listCa; 
	private ComboPanel<String> tfpSwName;
	private ComboPanel<String> tfpClntName;
	private ComboPanel<String> tfpGroup;
	private DatePanel panel;
	private DatePanel panel_1;
	
	public ContentList() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {100 ,300, 50, 200};
		gridBagLayout.rowHeights = new int[]{10, 30, 30, 30, 30, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		tfpSwName = new ComboPanel();
		tfpSwName.setTitle("품목명");
		GridBagConstraints gbc_tfpSwName = new GridBagConstraints();
		gbc_tfpSwName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpSwName.insets = new Insets(0, 0, 5, 5);
		gbc_tfpSwName.gridx = 1;
		gbc_tfpSwName.gridy = 1;
		add(tfpSwName, gbc_tfpSwName);
		
		btnSwAllFind = new JButton("전체");
		GridBagConstraints gbc_btnSwAllFind = new GridBagConstraints();
		gbc_btnSwAllFind.insets = new Insets(0, 0, 5, 5);
		gbc_btnSwAllFind.gridx = 2;
		gbc_btnSwAllFind.gridy = 1;
		add(btnSwAllFind, gbc_btnSwAllFind);
		
		
		tfpClntName = new ComboPanel();
		tfpClntName.setTitle("고객상호");
		GridBagConstraints gbc_tfpClntName = new GridBagConstraints();
		gbc_tfpClntName.insets = new Insets(0, 0, 5, 5);
		gbc_tfpClntName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpClntName.gridx = 1;
		gbc_tfpClntName.gridy = 2;
		add(tfpClntName, gbc_tfpClntName);
		
		btnClntAllFind = new JButton("전체");
		GridBagConstraints gbc_btnClntAllFind = new GridBagConstraints();
		gbc_btnClntAllFind.insets = new Insets(0, 0, 5, 5);
		gbc_btnClntAllFind.gridx = 2;
		gbc_btnClntAllFind.gridy = 2;
		add(btnClntAllFind, gbc_btnClntAllFind);
		
		tfpGroup = new ComboPanel();
		tfpGroup.setTitle("그룹별");
		GridBagConstraints gbc_tfpGroup = new GridBagConstraints();
		gbc_tfpGroup.insets = new Insets(0, 0, 5, 5);
		gbc_tfpGroup.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpGroup.gridx = 1;
		gbc_tfpGroup.gridy = 3;
		add(tfpGroup, gbc_tfpGroup);
		
		btnGroupAllFind = new JButton("전체");
		GridBagConstraints gbc_btnGroupAllFind = new GridBagConstraints();
		gbc_btnGroupAllFind.insets = new Insets(0, 0, 5, 5);
		gbc_btnGroupAllFind.gridx = 2;
		gbc_btnGroupAllFind.gridy = 3;
		add(btnGroupAllFind, gbc_btnGroupAllFind);
		
		panel = new DatePanel();
		panel.setTitle("날짜별 검색");
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 4;
		add(panel, gbc_panel);
		
		panel_1 = new DatePanel();
		panel_1.setTitle("~");
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 5;
		add(panel_1, gbc_panel_1);
		setCategoryComboData();
		setClntComboData();
		setSwComboData();
	}

	public void setClntComboData(){
		tfpClntName.getTf().removeAllItems();
		listClnt = ClientService.getInstance().selectClientByAll();
		Vector<String> comboitemCl = new Vector<>();
		comboitemCl.removeAllElements();
		comboitemCl.add("선택해주세요");
		for(int i=0;i<listClnt.size();i++){
			comboitemCl.add(listClnt.get(i).toCombobox());
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
			comboitemSw.add(listSw.get(i).toReportCombobox());
		}
		tfpSwName.setComboData(comboitemSw);
	}
	
	public void setCategoryComboData(){
		tfpGroup.getTf().removeAllItems();
		listCa = CategoryService.getInstance().selectCategoryByAll();
		Vector<String> comboitemCa = new Vector<>();
		comboitemCa.removeAllElements();
		comboitemCa.add("선택해주세요");
		for(int i=0;i<listCa.size();i++){
			comboitemCa.add(listCa.get(i).toCombobox());
		}
		tfpGroup.setComboData(comboitemCa);
		
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

	public ComboPanel<String> getTfpSwName() {
		return tfpSwName;
	}

	public ComboPanel<String> getTfpClntName() {
		return tfpClntName;
	}

	public ComboPanel<String> getTfpGroup() {
		return tfpGroup;
	}
	


	
	
	
}
