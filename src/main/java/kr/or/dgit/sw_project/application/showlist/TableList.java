package kr.or.dgit.sw_project.application.showlist;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kr.or.dgit.sw_project.dto.Sale;
import kr.or.dgit.sw_project.service.SaleService;

public class TableList extends JPanel{
	private static final TableList instence= new TableList();
	
	public static TableList getInstence() {
		return instence;
	}
	
	private JTable table;
	public TableList() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
	}	
	/*public void setTableData(){
		table.setModel(new DefaultTableModel(getRowdata(), getColumm()));
	}*/
	
	public Object[] getColumm() {
		return new String[]{"고객상호명","품목명","주문수량","입금여부","단가","매출금","미수금"};
		
	}
	/*public Object[][] getRowdata() {
		List<Sale> list= SaleService.getInstence().clientSoftwareReport();
		Object[][] datas= new Object[list.size()][];
		for(int i=0;i<datas.length;i++){
			datas[i]=list.get(i);
		}
		return datas;
	}*/
}
