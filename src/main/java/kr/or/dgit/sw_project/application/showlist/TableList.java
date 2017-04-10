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

public class TableList extends JPanel{
	
	private JTable table;
	
	private List<ViewCategorySale> listCategory;
	private List<ViewClientSale> listClient;
	
	
	
	public TableList() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
	}	
	public void setTableDataForCategori(){ 
		table.setModel(new DefaultTableModel(getRowDateForCategori(), getColummForCategori()));
	}
	
	public void setTableDataForClient(){
		table.setModel(new DefaultTableModel(getRowDateForClient(), getColummForClient()));
	}
	public void setTableDataCategoriOne(ViewCategorySale viewCategorySale){
		table.setModel(new DefaultTableModel(new Object[][]	{{viewCategorySale.getCategory().getGroupCode(),
			viewCategorySale.getCategory().getGroupName(),
			viewCategorySale.getcSalePrice(),
			viewCategorySale.getcAmount()}}, getColummForCategori()));
	}
	
	public Object[] getColummForCategori() {
		return new String[]{"그룹코드","그룹명 ","총판매액","총판매갯수"};
		
	}
	public Object[] getColummForClient() {
		return new String[]{"고객상호명","풍목명 ","주문수량","단가","매출금","입금여부","미수금"};
		
	}
	
	
	private Object[][] getRowDateForCategori() { 
		List<ViewCategorySale> listForTable = new ArrayList<ViewCategorySale>(listCategory);
		Object[][] datas = new Object[listForTable.size()][];
		for (int i = 0; i < datas.length; i++) {
			datas[i] = listForTable.get(i).toArrayForTable();
		}
		return datas;
	}
	
	
	private Object[][] getRowDateForClient() { 
		int totalPrice=0;
		int ReceivablePrice=0;
		List<ViewClientSale> listForTable = new ArrayList<ViewClientSale>(listClient);
		Object[][] datas = new Object[listForTable.size()+1][];
		for (int i = 0; i < datas.length-1; i++) {
			datas[i] = listForTable.get(i).toArrayForTable();
			totalPrice+=listForTable.get(i).getSale().getSaleDetail().getTotalSalePrice();
			ReceivablePrice+=listForTable.get(i).getSale().getSaleDetail().getReceivablePrice();
		}
			datas[datas.length-1]=new Object[]{"","","","총합계",String.format("%,d", totalPrice),"미수금합계",String.format("%,d", ReceivablePrice)};
		return datas;
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
	
	public List<ViewCategorySale> getCategryList(){
		return listCategory;
	}
	
	public List<ViewClientSale> getClientList(){
		return listClient;
	}
	
	
}
