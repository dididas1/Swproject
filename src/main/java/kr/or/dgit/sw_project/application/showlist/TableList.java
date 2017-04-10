package kr.or.dgit.sw_project.application.showlist;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kr.or.dgit.sw_project.dto.Client;
import kr.or.dgit.sw_project.dto.ViewCategorySale;

public class TableList extends JPanel{
	List<ViewCategorySale> listCategory;
	private JTable table;
	
	
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
	
	public Object[] getColummForCategori() {
		return new String[]{"그룹코드","그룹명 ","총판매액","총판매갯수"};
		
	}
	
	

	private Object[][] getRowDateForCategori() { 
		List<ViewCategorySale> listForTable = new ArrayList<ViewCategorySale>(listCategory);
		Object[][] datas = new Object[listForTable.size()][];
		for (int i = 0; i < datas.length; i++) {
			datas[i] = listForTable.get(i).toArrayForTable();
		}
		return datas;
	}
	
	
	public JTable getTable() {
		return table;
	}
	
	public void setCategryList(List<ViewCategorySale> listCategory) {
		this.listCategory = listCategory;
	}

	public List<ViewCategorySale> getCategryList(){
		return listCategory;
	}

}
