package kr.or.dgit.sw_project.application.showlist;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kr.or.dgit.sw_project.dto.Client;
import kr.or.dgit.sw_project.dto.JoinFromSale;
import kr.or.dgit.sw_project.dto.JoinFromSoftware;
import kr.or.dgit.sw_project.dto.Sale;
import kr.or.dgit.sw_project.service.JoinFromSoftwareService;
import kr.or.dgit.sw_project.service.SaleService;

public class TableList extends JPanel{
	private static final TableList instence= new TableList();
	
	List<JoinFromSale> list;
	
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
	
	
	/*************************** load Table ***************************/  
	public void setTableData(){ //테이블 데이터입력
		table.setModel(new DefaultTableModel(getRowDate(), getColumm()));
	}
	
	public Object[] getColumm() {
		return new String[]{"판매코드","품목명 ","분류 ","공급회사명 ","공급금액 ","판매금액 ","판매이윤"};
		
	}

	private Object[][] getRowDate() { //테이블 로우값입력 isExist가 true인 항목에대해서만 값받아옴
		List<JoinFromSale> listForTable = new ArrayList<JoinFromSale>(list);
		for(int i =0; i<list.size(); i++)
			System.out.println(list.get(i).toString());
			
		
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
