package kr.or.dgit.sw_project.application.sales;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kr.or.dgit.sw_project.dto.JoinFromSale;

public class TableSale extends JPanel {
	private JTable table;
	private List<JoinFromSale> list;
	
	public TableSale() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}	
	/*************************** load Table ***************************/  
	public void setTableData(){ //테이블 데이터입력
		table.setModel(new DefaultTableModel(getRowDate(), getColumm()));
	}
	private Object[] getColumm() { //컬럼입력
		return new String[]{"판매코드","고객상호명","품목명","판매가격","주문갯수","총액","주문일자","입금여부"};
	}

	private Object[][] getRowDate() { //테이블 로우값입력 isExist가 true인 항목에대해서만 값받아옴
		List<JoinFromSale> listForTable = new ArrayList<JoinFromSale>(list);
			
		for (int i = listForTable.size()-1; i >= 0; i--) {
			if (!listForTable.get(i).getSale().isSaleIsExist()) {
				listForTable.remove(i);
			}
		}
	
		
		Object[][] datas = new Object[listForTable.size()][];
		for (int i = 0; i < datas.length; i++) {
			datas[i] = listForTable.get(i).toArrayForTable();
		}
		return datas;
	}

	/*****************************************************************/
	
	public JTable getTable() {
		return table;
	}
	
	
	public void setList(List<JoinFromSale> list) {
		this.list = list;
	}

	public List<JoinFromSale> getList(){
		return list;
	}
}
