package kr.or.dgit.sw_project.application.showlist;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import kr.or.dgit.sw_project.dto.Category;
import kr.or.dgit.sw_project.dto.Client;
import kr.or.dgit.sw_project.dto.Software;
import kr.or.dgit.sw_project.dto.ViewCategorySale;
import kr.or.dgit.sw_project.dto.ViewClientSale;
import kr.or.dgit.sw_project.dto.ViewOrderDateSale;
import kr.or.dgit.sw_project.dto.ViewSofrwareSale;
import kr.or.dgit.sw_project.service.ViewCategorySaleService;
import kr.or.dgit.sw_project.service.ViewClientSaleService;
import kr.or.dgit.sw_project.service.ViewOrderDateSaleService;
import kr.or.dgit.sw_project.service.ViewSoftwareSaleService;

public class ViewList extends JFrame implements ActionListener, ItemListener {
	private ContentList pContent;
	private TableList pTable;

	private List<ViewCategorySale> listCategory;
	private List<ViewClientSale> listClinet;
	private List<ViewSofrwareSale> listSoftware;
	private List<ViewOrderDateSale> listDate;
	private JPanel contentPane;

	public ViewList() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(0, 0, 1200, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0}; //각 열의 최소 넓이  
		gridBagLayout.rowHeights = new int[]{0, 0, 0}; //각 행의 최소 넓이
		gridBagLayout.columnWeights = new double[]{1.0, 0.0}; //각 열의 가중치
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0}; //각 행의 가중치
		getContentPane().setLayout(gridBagLayout);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.HORIZONTAL;
		gbc_label.insets = new Insets(20, 50, 0, 10);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		gbc_label.gridwidth = 5;
		contentPane.add(label, gbc_label);

		ImageIcon icon = new ImageIcon(System.getProperty("user.dir")+"/build/resources/main/softwareimage/list.png");
		label.setIcon(icon);	
		
		pContent = new ContentList();
		pContent.getBtnDaySearch().addActionListener(this);
		pContent.getTfpSwName().getTf().addItemListener(this);
		pContent.getTfpClntName().getTf().addItemListener(this);
		pContent.getTfpGroup().getTf().addItemListener(this);
		
		pContent.getBtnGroupAllFind().addActionListener(this);
		pContent.getBtnSwAllFind().addActionListener(this);
		pContent.getBtnClntAllFind().addActionListener(this);
		pContent.getBtnDateAllFind().addActionListener(this);
		
		GridBagConstraints gbc_pContent = new GridBagConstraints();
		gbc_pContent.insets = new Insets(10, 10, 30, 10);
		gbc_pContent.fill = GridBagConstraints.NONE;
		gbc_pContent.gridx = 0;
		gbc_pContent.gridy = 1;
		contentPane.add(pContent, gbc_pContent);

		pTable = new TableList();
		GridBagConstraints gbc_pTable = new GridBagConstraints();
		gbc_pTable.insets = new Insets(0, 0, 0, 0);
		gbc_pTable.fill = GridBagConstraints.BOTH;
		gbc_pTable.gridx = 0;
		gbc_pTable.gridy = 2;
		contentPane.add(pTable, gbc_pTable);
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
	
	private void getDataFromDBDateAll(){ //list에 데이터베이스에서 가져온 값을 입력 날짜별
		listDate = ViewOrderDateSaleService.getInstence().selectViewOrderDateAll();
	}
	
	private void getDataFromDBDate(Map<String, Object> param){ //list에 데이터베이스에서 가져온 값을 입력 클라이언트
		listDate = ViewOrderDateSaleService.getInstence().selectViewOrderDateSaleThisYear(param);
	}

	/****************************************************************/
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == pContent.getBtnDaySearch()) {
			pContentBtnDaySearchActionPerformed(e);
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
		
		if (e.getSource() == pContent.getBtnDateAllFind()) {
			actionPerformedPContentBtnDateAllFind(e);
		}
	}
	
	private void actionPerformedPContentBtnDateAllFind(ActionEvent e) { //날짜 전체검색
		getDataFromDBDateAll();
		pTable.setDateList(listDate);
		pTable.setTableDataForDate();
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
	

	protected void pContentBtnDaySearchActionPerformed(ActionEvent e) { //날짜검색
		Map<String, Object> param= new HashMap<>();
		param.put("startDate", pContent.getTfpDateFirst().getTfDate());
		param.put("endDate", pContent.getTfpDateSecond().getTfDate());
		getDataFromDBDate(param);
		pTable.setDateList(listDate);
		pTable.setTableDataForDate();
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
			ViewCategorySale viewCategorySale = new ViewCategorySale();
			viewCategorySale= ViewCategorySaleService.getInsetence().selectViewCategoryByNo(new ViewCategorySale(new Category(null,(String) pContent.getTfpGroup().getSelectItem())));
			pTable.setcategoryOne(viewCategorySale);
			pTable.setTableDataCategoriOne(viewCategorySale);
			pContent.getTfpClntName().setSelectedItem(0);
			pContent.getTfpSwName().setSelectedItem(0);
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

	public ContentList getContentList() {
		return pContent;
	}
	
	/***********************/
	
	/***********************/
}
