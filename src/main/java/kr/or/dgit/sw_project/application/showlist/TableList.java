package kr.or.dgit.sw_project.application.showlist;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import kr.or.dgit.sw_project.dto.JoinFromSale;
import kr.or.dgit.sw_project.dto.ViewCategorySale;
import kr.or.dgit.sw_project.dto.ViewClientSale;
import kr.or.dgit.sw_project.dto.ViewOrderDateSale;
import kr.or.dgit.sw_project.dto.ViewSofrwareSale;
import kr.or.dgit.sw_project.service.ViewClientSaleService;

public class TableList extends JPanel{

	private JTable table;
	private JLabel lblTotalLable;
	private List<ViewCategorySale> listCategory;
	private List<ViewClientSale> listClient;
	private List<ViewSofrwareSale> listSoftware;
	private List<ViewOrderDateSale> listDate;
	private ViewCategorySale viewCategorySale;



	public TableList() {
		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setViewportView(table);

		lblTotalLable = new JLabel("    ");
		lblTotalLable.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTotalLable, BorderLayout.SOUTH);


	}	
	public void setTableDataForCategori(){  //카테고리 테이블에 입력
		table.setModel(new DefaultTableModel(getRowDateForCategori(), getColummForCategori()));
	}

	public void setTableDataForClient(){ //클라이언트 테이블에 입력
		table.setModel(new DefaultTableModel(getRowDateForClient(), getColummForClient()));
	}

	public void setTableDataForSoftware(){ //소프트웨어 테이블에 입력
		table.setModel(new DefaultTableModel(getRowDateForSoftware(), getColummForSoftware()));
	}

	public void setTableDataForDate(){ //날짜 테이블에 입력
		table.setModel(new DefaultTableModel(getRowDateForDate(), getColummForDate()));
	}

	public void setTableDataCategoriOne(ViewCategorySale viewCategorySale){ //카테고리 선택... 테이블에 입력
		if(!(viewCategorySale==null)){
			table.setModel(new DefaultTableModel(new Object[][]{{viewCategorySale.getCategory().getGroupCode(),viewCategorySale.getCategory().getGroupName(),
				viewCategorySale.getcSalePrice(),viewCategorySale.getcAmount()
				}}, getColummForCategori()));
		}
		lblTotalLable.setText("  ");
	}

	public Object[] getColummForCategori() { //카테고리 Columm
		return new String[]{"그룹코드","그룹명 ","총판매액","총판매갯수"};

	}
	public Object[] getColummForClient() { //클라이언트 Columm
		return new String[]{"고객상호명","풍목명 ","주문수량","단가","매출금","입금여부","미수금"};

	}

	public Object[] getColummForSoftware() { //소프트웨어 Columm
		return new String[]{"판매코드","품목명 ","분류","공급회사명","판매금액","공급금액","판매이윤"};

	}


	public Object[] getColummForDate() { //날짜 Columm
		return new String[]{"날짜","판매코드 ","고객상호명","품목명","판매가격","판매갯수","입금여부","판매금액"};

	}



	private Object[][] getRowDateForCategori() {  //카테고리 row
		int totalPrice =0;
		int totalAmount =0;
		List<ViewCategorySale> listForTable = new ArrayList<ViewCategorySale>(listCategory);
		Object[][] datas = new Object[listForTable.size()][];

		for (int i = 0; i < datas.length; i++) {
			datas[i] = listForTable.get(i).toArrayForTable();
			totalPrice+=listForTable.get(i).getcSalePrice();
			totalAmount+=listForTable.get(i).getcAmount();
		}
		setGroupTotalLable(totalPrice, totalAmount);
		return datas;
	}

	//검색안됨
	private Object[][] getRowDateForClient() {  //클라이언트 row 합계까지
		int totalPrice=0;
		int receivablePrice=0;
		List<ViewClientSale> listForTable = new ArrayList<ViewClientSale>(listClient); 
		
		for (int i = listForTable.size()-1; i >= 0; i--) {
			if(!listForTable.get(i).getSale().isSaleIsExist()){
				listForTable.remove(i);
			}
		}

		Object[][] datas = new Object[listForTable.size()][];
		
		for (int i = 0; i < datas.length; i++) {

			datas[i] = listForTable.get(i).toArrayForTable();
			totalPrice+=listForTable.get(i).getSale().getSaleDetail().getTotalSalePrice();
			receivablePrice+=listForTable.get(i).getSale().getSaleDetail().getReceivablePrice();
		}
		setClntTotalLable(totalPrice,receivablePrice);
		return datas;
	}

	private Object[][] getRowDateForSoftware() {  //카테고리 row
		int totalPrice=0;
		int totalSupplyPrice=0;
		int margin=0;
		List<ViewSofrwareSale> listForTable = new ArrayList<ViewSofrwareSale>(listSoftware);
		
		for (int i = listForTable.size()-1; i >= 0; i--) {
			if(!listForTable.get(i).getSale().isSaleIsExist()){
				listForTable.remove(i);
			}
		}
		
		Object[][] datas = new Object[listForTable.size()][];
		
		for (int i = 0; i < datas.length; i++) {
			System.out.println(i);
			datas[i] = listForTable.get(i).toArrayForTable();
			totalPrice+=listForTable.get(i).getSale().getSaleDetail().getTotalSalePrice();
			totalSupplyPrice+=listForTable.get(i).getSale().getSaleDetail().getTotalSupplyPrice();
			margin+= listForTable.get(i).getSale().getSaleDetail().getMargin();
		}
		setSwTotalLable(totalPrice, totalSupplyPrice, margin);
		return datas;
	}

	private Object[][] getRowDateForDate() {  //날짜 row 합계까지
		int totalPrice=0;
		int totalAmount=0;
		List<ViewOrderDateSale> listForTable = new ArrayList<ViewOrderDateSale>(listDate);
		
		for (int i = listForTable.size()-1; i >= 0; i--) {
			if(!listForTable.get(i).getSale().isSaleIsExist()){
				listForTable.remove(i);
			}
		}

		Object[][] datas = new Object[listForTable.size()][];
		
		for (int i = 0; i < datas.length; i++) {
				datas[i] = listForTable.get(i).toArrayForTable();
				totalPrice+=listForTable.get(i).getSale().getSaleDetail().getTotalSalePrice();
				totalAmount+=listForTable.get(i).getSale().getSaleAmount();
		}
		setDateTotalLable(totalPrice,totalAmount);
		return datas;
	}

	public void setClntTotalLable(int price,int total){ // 총합라벨세팅 세팅
		lblTotalLable.setText(String.format("총판매금액 : %,d   총미수금: %,d", price,total));
	}

	public void setSwTotalLable(int totalPrice,int totalSupplyPrice,int margin){ // 총합라벨세팅 세팅
		lblTotalLable.setText(String.format("총판매금액 : %,d   총공급금액: %,d   총판매이윤: %,d", totalPrice,totalSupplyPrice,margin));
	}	

	public void setGroupTotalLable(int totalPrice ,int totalAmount){ // 총합라벨세팅 세팅
		lblTotalLable.setText(String.format("총판매금액 : %,d   총판매갯수: %,d "  , totalPrice,totalAmount));
	}	
	public void setDateTotalLable(int totalPrice,int totalAmount){ //총합라벨세팅
		lblTotalLable.setText(String.format("총판매금액 : %,d   총판매갯수: %,d "  , totalPrice,totalAmount));
	}
	public JTable getTable() {
		return table;
	}

	public void setCategryList(List<ViewCategorySale> listCategory) {
		this.listCategory = listCategory;
	}

	public void setClientList(List<ViewClientSale> listClient) {
		this.listClient = listClient;
	}

	public void setSoftwareList(List<ViewSofrwareSale> listSoftware) {
		this.listSoftware = listSoftware;
	}

	public void setDateList(List<ViewOrderDateSale> listDate) {
		this.listDate = listDate;
	}

	public List<ViewCategorySale> getCategryList(){
		return listCategory;
	}

	public List<ViewClientSale> getClientList(){
		return listClient;
	}

	public List<ViewSofrwareSale> getSoftwareList(){
		return listSoftware;
	}

	public List<ViewOrderDateSale> getDateList(){
		return listDate;
	}
	public void setcategoryOne(ViewCategorySale viewCategorySale) {
		this.viewCategorySale= viewCategorySale;
		
	}

}
