package kr.or.dgit.sw_project.application.address;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kr.or.dgit.sw_project.dto.Address;

public class TableAddress extends JPanel {
	private JTable table;
	private List<Address> list;
		
	public TableAddress() {
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
		return new String[]{"우편 번호","시도","도로명"};
	}

	private Object[][] getRowDate() {  //로우데이터 입력
		Object[][] datas = new Object[list.size()][];
		for (int i = 0; i < datas.length; i++) {
			datas[i] = list.get(i).toArray();
		}
		return datas;
	}

	/*****************************************************************/
	
	public JTable getTable() {
		return table;
	}
	
	public void setList(List<Address> list) { //뷰에서 주소값받아옴 (그조는 안쓸듯?)
		this.list = list;
	}

	public List<Address> getList(){  //뷰에서 주소값받아옴 (그조는 안쓸듯?)
		return list;
	}
}
