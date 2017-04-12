package kr.or.dgit.sw_project.application.category;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kr.or.dgit.sw_project.dto.Category;
import kr.or.dgit.sw_project.service.CategoryService;

@SuppressWarnings("serial")
public class TableCategory extends JPanel {
	private JTable table;
	public TableCategory() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}	
	
	public JTable getTable() {
		return table;
	}

	public void setTableData(){ //테이블 데이터입력
		table.setModel(new DefaultTableModel(getRowdata(), getColumm()));
		
	}
	
	public Object[] getColumm() { //컬럼값입력
		return new String[]{"분류코드","분류명"};
		
	}
	
	public Object[][] getRowdata() {
		List<Category> list= CategoryService.getInstance().selectCategoryByAll();
		Object[][] datas= new Object[list.size()][];
		for(int i=0;i<datas.length;i++){
			datas[i]=list.get(i).toArray();
		}
		return datas;
	}
}
