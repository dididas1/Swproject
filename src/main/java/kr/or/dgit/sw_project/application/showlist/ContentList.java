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
import erp_myframework.DatePanel;
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
	private List<Client> listClnt;
	private List<Software> listSw;
	private List<Category> listCa; 
	private ComboPanel<String> tfpSwName;
	private ComboPanel<String> tfpClntName;
	private ComboPanel<String> tfpGroup;
	private DatePanel tfpDateFirst;
	private DatePanel tfpDateSecond;
	private JButton btnDaySearch;
	private JButton btnDateAllFind;
	
	public ContentList() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {100 ,300, 50, 200};
		gridBagLayout.rowHeights = new int[]{10, 30, 30, 30, 10};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
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
		setCategoryComboData();
		setClntComboData();
		setSwComboData();
		
		tfpDateFirst= new DatePanel();
		tfpDateFirst.setTitle("날짜");
		GridBagConstraints gbc_tfpDateFirst = new GridBagConstraints();
		gbc_tfpDateFirst.insets = new Insets(0, 0, 5, 5);
		gbc_tfpDateFirst.fill = GridBagConstraints.BOTH;
		gbc_tfpDateFirst.gridx = 1;
		gbc_tfpDateFirst.gridy = 4;
		add(tfpDateFirst, gbc_tfpDateFirst);
		
		btnDateAllFind = new JButton("전체");
		GridBagConstraints gbc_btnDateAllFind = new GridBagConstraints();
		gbc_btnDateAllFind.insets = new Insets(0, 0, 5, 5);
		gbc_btnDateAllFind.gridx = 2;
		gbc_btnDateAllFind.gridy = 4;
		add(btnDateAllFind, gbc_btnDateAllFind);
		
		tfpDateSecond = new DatePanel();
		tfpDateSecond.setTitle("~");
		GridBagConstraints gbc_tfpDateSecond = new GridBagConstraints();
		gbc_tfpDateSecond.anchor = GridBagConstraints.NORTH;
		gbc_tfpDateSecond.insets = new Insets(0, 0, 0, 5);
		gbc_tfpDateSecond.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpDateSecond.gridx = 1;
		gbc_tfpDateSecond.gridy = 5;
		add(tfpDateSecond, gbc_tfpDateSecond);
		
		btnDaySearch = new JButton("검색");
		GridBagConstraints gbc_btnDaySearch = new GridBagConstraints();
		gbc_btnDaySearch.insets = new Insets(0, 0, 0, 5);
		gbc_btnDaySearch.gridx = 2;
		gbc_btnDaySearch.gridy = 5;
		add(btnDaySearch, gbc_btnDaySearch);
		
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

	public JButton getBtnDateAllFind() {
		return btnDateAllFind;
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

	public DatePanel getTfpDateFirst() {
		return tfpDateFirst;
	}

	public DatePanel getTfpDateSecond() {
		return tfpDateSecond;
	}

	public JButton getBtnDaySearch() {
		return btnDaySearch;
	}
	
	

	
	
	
}
