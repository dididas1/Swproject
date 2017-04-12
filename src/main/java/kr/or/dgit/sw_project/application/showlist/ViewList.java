package kr.or.dgit.sw_project.application.showlist;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import kr.or.dgit.sw_project.dto.Client;
import kr.or.dgit.sw_project.dto.Software;
import kr.or.dgit.sw_project.dto.ViewCategorySale;
import kr.or.dgit.sw_project.dto.ViewClientSale;
import kr.or.dgit.sw_project.dto.ViewSofrwareSale;
import kr.or.dgit.sw_project.service.ViewCategorySaleService;
import kr.or.dgit.sw_project.service.ViewClientSaleService;
import kr.or.dgit.sw_project.service.ViewSoftwareSaleService;
import java.awt.BorderLayout;

public class ViewList extends JPanel implements ActionListener, ItemListener {
	private ContentList pContent;
	private TableList pTable;

	private List<ViewCategorySale> listCategory;
	private List<ViewClientSale> listClinet;
	private List<ViewSofrwareSale> listSoftware;
	private JLabel lblTotalLable;

	public ViewList() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0}; //각 열의 최소 넓이  
		gridBagLayout.rowHeights = new int[]{0, 0, 0}; //각 행의 최소 넓이
		gridBagLayout.columnWeights = new double[]{1.0, 0.0}; //각 열의 가중치
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0}; //각 행의 가중치
		setLayout(gridBagLayout);
		
		JLabel label = new JLabel("소프트웨어 관리");
		label.setEnabled(false);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("\uC778\uD130\uD30C\uD06C\uACE0\uB515 B", label.getFont().getStyle(), label.getFont().getSize() + 5));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.HORIZONTAL;
		gbc_label.insets = new Insets(20, 50, 0, 10);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		gbc_label.gridwidth = 5;
		add(label, gbc_label);

		pContent = new ContentList();
		pContent.getBtnDate().addActionListener(this);
		pContent.getTfpSwName().getTf().addItemListener(this);
		pContent.getTfpClntName().getTf().addItemListener(this);
		pContent.getTfpGroup().getTf().addItemListener(this);
		
		pContent.getBtnGroupAllFind().addActionListener(this);
		pContent.getBtnSwAllFind().addActionListener(this);
		pContent.getBtnClntAllFind().addActionListener(this);
		
		GridBagConstraints gbc_pContent = new GridBagConstraints();
		gbc_pContent.insets = new Insets(10, 10, 30, 10);
		gbc_pContent.fill = GridBagConstraints.NONE;
		gbc_pContent.gridx = 0;
		gbc_pContent.gridy = 1;
		add(pContent, gbc_pContent);

		pTable = new TableList();
		GridBagConstraints gbc_pTable = new GridBagConstraints();
		gbc_pTable.insets = new Insets(0, 0, 0, 0);
		gbc_pTable.fill = GridBagConstraints.BOTH;
		gbc_pTable.gridx = 0;
		gbc_pTable.gridy = 2;
		add(pTable, gbc_pTable);
		
		lblTotalLable = new JLabel("");
		lblTotalLable.setHorizontalAlignment(SwingConstants.RIGHT);
		pTable.add(lblTotalLable, BorderLayout.SOUTH);


		setVisible(true);
		pTable.setViewList(this);
	}
	

	public JLabel getLblNewLabel() {
		return lblTotalLable;
	}


	public void setTotalLable(int[] total){
		lblTotalLable.setText(String.format("총합계 : %,d %,d", String.valueOf(total[0])+String.valueOf(total[1])));
	}


	/*************************** Get Data ***************************/  
	private void setTable(){ //Table 로드
		getDataFromDBCategory();
		pTable.setCategryList(listCategory);
		pTable.setTableDataForCategori();
	}

	private void getDataFromDBCategory(){ //list에 데이터베이스에서 가져온 값을 입력 카테고리
		listCategory = ViewCategorySaleService.getInsetence().selectViewCategoryAll();
	}

	private void getDataFromDBClinet(){ //list에 데이터베이스에서 가져온 값을 입력 클라이언트
		listClinet = ViewClientSaleService.getInsetence().selectViewClientSaleAll();

	}

	private void getDataFromDBSoftware(){ //list에 데이터베이스에서 가져온 값을 입력 클라이언트
		listSoftware = ViewSoftwareSaleService.getInstence().selectViewSofrwareSaleAll();
	}

	/****************************************************************/



	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == pContent.getBtnDate()) {
			actionPerformedPContentBtnDate(e);
		}
		if (e.getSource() == pContent.getBtnGroupAllFind()) {
			actionPerformedPContentBtnGroupAllFind(e);
		}
		if (e.getSource() == pContent.getBtnSwAllFind()) {
			actionPerformedPContentBtnSwAllFind(e);
		}

		if (e.getSource() == pContent.getBtnClntAllFind()) {
			actionPerformedPContentBtnClntAllFind(e);
		}
	}
	protected void actionPerformedPContentBtnClntAllFind(ActionEvent e) { //공급사 전체검색
		getDataFromDBClinet();
		pTable.setClientList(listClinet);
		pTable.setTableDataForClient();
		pContent.getTfpClntName().setSelectedItem(0);

	}

	protected void actionPerformedPContentBtnSwAllFind(ActionEvent e) {
		getDataFromDBSoftware();
		pTable.setSoftwareList(listSoftware);
		pTable.setTableDataForSoftware();;
		pContent.getTfpSwName().setSelectedItem(0);

	}
	protected void actionPerformedPContentBtnGroupAllFind(ActionEvent e) { //카테고리 전체검색
		getDataFromDBCategory();
		pTable.setCategryList(listCategory);
		pTable.setTableDataForCategori();
		pContent.getTfpGroup().setSelectedItem(0);
	}


	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == pContent.getTfpSwName().getTf()) {
			itemStateChangedPContentTfpSwNameTf(e);
		}
		if (e.getSource() == pContent.getTfpClntName().getTf()) {
			pContentTfpClntNameTfItemStateChanged(e);
		}
		if (e.getSource() == pContent.getTfpGroup().getTf()) {
			pContentTfpGroupTfItemStateChanged(e);
		}
	}


	protected void pContentTfpGroupTfItemStateChanged(ItemEvent e) { // 카테고리 콤보박스선택
		if(pContent.getTfpGroup().getSelectedIndex()!=0){
			getDataFromDBCategory();
			pTable.setCategryList(listCategory);
			pTable.setTableDataCategoriOne(listCategory.get(pContent.getTfpGroup().getSelectedIndex()-1));
			pContent.getTfpClntName().setSelectedItem(0);
			pContent.getTfpSwName().setSelectedItem(0);
			lblTotalLable.setText("   ");
		}
	}

	protected void pContentTfpClntNameTfItemStateChanged(ItemEvent e) { // 클라이언트 콤보박스 선택
		if(pContent.getTfpClntName().getSelectedIndex()!=0){
			ViewClientSale viewClientSale = new ViewClientSale();
			viewClientSale.setClient(new Client(null,(String) pContent.getTfpClntName().getSelectItem()));
			listClinet = ViewClientSaleService.getInsetence().selectViewClientSaleClntName(viewClientSale);
			pTable.setClientList(listClinet);
			pTable.setTableDataForClient();
			pContent.getTfpGroup().setSelectedItem(0);
			pContent.getTfpSwName().setSelectedItem(0);
		}
	}

	protected void itemStateChangedPContentTfpSwNameTf(ItemEvent e) { // 소프트웨어 콤보박스 선택
		if(pContent.getTfpSwName().getSelectedIndex()!=0){
			ViewSofrwareSale viewSofrwareSale = new ViewSofrwareSale();
			viewSofrwareSale.setSoftware(new Software(null, (String) pContent.getTfpSwName().getSelectItem()));
			listSoftware = ViewSoftwareSaleService.getInstence().selectViewsoftwareSaleBySwName(viewSofrwareSale);
			pTable.setSoftwareList(listSoftware);
			pTable.setTableDataForSoftware();
			pContent.getTfpClntName().setSelectedItem(0);
			pContent.getTfpGroup().setSelectedItem(0);
		}
	}
	protected void actionPerformedPContentBtnDate(ActionEvent e) {
	}
}
