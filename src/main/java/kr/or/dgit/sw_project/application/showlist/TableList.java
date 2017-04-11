package kr.or.dgit.sw_project.application.showlist;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kr.or.dgit.sw_project.dto.ViewCategorySale;
import kr.or.dgit.sw_project.dto.ViewClientSale;
import kr.or.dgit.sw_project.dto.ViewSofrwareSale;

public class TableList extends JPanel{
	
	private JTable table;
	private ViewList viewList;
	
	private List<ViewCategorySale> listCategory;
	private List<ViewClientSale> listClient;
	private List<ViewSofrwareSale> listSoftware;
	
	
	
	public TableList() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
	}	
	public void setTableDataForCategori(){  //카테고리 테이블에 입력
		table.setModel(new DefaultTableModel(getRowDateForCategori(), getColummForCategori()));
	}
	
	public void setTableDataForClient(){ //클라이언트 테이블에 입력
		table.setModel(new DefaultTableModel(getRowDateForClient(), getColummForClient()));
	}
	
	public void setTableDataForSoftware(){ //클라이언트 테이블에 입력
		table.setModel(new DefaultTableModel(getRowDateForSoftware(), getColummForSoftware()));
	}
	
	public void setTableDataCategoriOne(ViewCategorySale viewCategorySale){ //카테고리 선택... 테이블에 입력
		table.setModel(new DefaultTableModel(new Object[][]	{{viewCategorySale.getCategory().getGroupCode(),
			viewCategorySale.getCategory().getGroupName(),
			viewCategorySale.getcSalePrice(),
			viewCategorySale.getcAmount()}}, getColummForCategori()));
	}
	
	public Object[] getColummForCategori() { //카테고리 Columm
		return new String[]{"그룹코드","그룹명 ","총판매액","총판매갯수"};
		
	}
	public Object[] getColummForClient() { //클라이언트 Columm
		return new String[]{"고객상호명","풍목명 ","주문수량","단가","매출금","입금여부","미수금"};
		
	}
	
	public Object[] getColummForSoftware() { //클라이언트 Columm
		return new String[]{"판매코드","품목명 ","분류","공급회사명","판매금액","공급금액","판매이윤"};
		
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
	
	
	private Object[][] getRowDateForClient() {  //클라이언트 row 합계까지
		int totalPrice=0;
		int receivablePrice=0;
		List<ViewClientSale> listForTable = new ArrayList<ViewClientSale>(listClient);
		Object[][] datas = new Object[listForTable.size()][];
		for (int i = 0; i < datas.length-1; i++) {
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
		Object[][] datas = new Object[listForTable.size()][];
		for (int i = 0; i < datas.length; i++) {
			datas[i] = listForTable.get(i).toArrayForTable();
			totalPrice+=listForTable.get(i).getSale().getSaleDetail().getTotalSalePrice();
			totalSupplyPrice+=listForTable.get(i).getSale().getSaleDetail().getTotalSupplyPrice();
			margin+= listForTable.get(i).getSale().getSaleDetail().getMargin();
		}
		setSwTotalLable(totalPrice, totalSupplyPrice, margin);
		return datas;
	}
	
	
	public void setClntTotalLable(int price,int total){ // 총합레이블에 세팅
		viewList.getLblNewLabel().setText(String.format("총판매금액 : %,d   총미수금: %,d", price,total));
	}
	
	public void setSwTotalLable(int totalPrice,int totalSupplyPrice,int margin){ // 총합레이블에 세팅
		viewList.getLblNewLabel().setText(String.format("총판매금액 : %,d   총공급금액: %,d   총판매이윤: %,d", totalPrice,totalSupplyPrice,margin));
	}	
	
	public void setGroupTotalLable(int totalPrice ,int totalAmount){ // 총합레이블에 세팅
		viewList.getLblNewLabel().setText(String.format("총판매금액 : %,d   총판매갯수: %,d "  , totalPrice,totalAmount));
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
	
	public List<ViewCategorySale> getCategryList(){
		return listCategory;
	}
	
	public List<ViewClientSale> getClientList(){
		return listClient;
	}
	
	public List<ViewSofrwareSale> getSoftwareList(){
		return listSoftware;
	}
	
	public void setViewList(ViewList viewList){
		this.viewList= viewList;
	}
	
}
