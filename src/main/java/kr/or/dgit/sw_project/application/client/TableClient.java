package kr.or.dgit.sw_project.application.client;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kr.or.dgit.sw_project.dto.Client;
import kr.or.dgit.sw_project.service.ClientService;

public class TableClient extends JPanel {
	private JTable table;
	private List<Client> list;
	
	public TableClient() {
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
		return new String[]{"고객사 코드","고객사 이름","고객사 전화번호","고객사 주소"};
	}

	private Object[][] getRowDate() { //테이블 로우값입력 isExist가 true인 항목에대해서만 값받아옴
		List<Client> listForTable = new ArrayList<Client>(list);
		System.out.println("Table "+list.hashCode());
		System.out.println("=========list=============");
		for(int i =0; i<list.size()-1; i++)
			System.out.println(list.get(i).toString());
			
		System.out.println("=========listForTable=============");
		for(int i =0; i<listForTable.size()-1; i++)
			System.out.println(listForTable.get(i).toString());
		
		for (int i = listForTable.size()-1; i >= 0; i--) {
			if (!listForTable.get(i).isClntIsExist()) {
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
	
	public void setList(List<Client> list) {
		this.list = list;
	}

	public List<Client> getList(){
		return list;
	}
}
