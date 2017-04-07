package kr.or.dgit.sw_project.application.software;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kr.or.dgit.sw_project.dto.JoinFromSoftware;
import kr.or.dgit.sw_project.service.JoinFromSoftwareService;

@SuppressWarnings("serial")
public class TableSoftware extends JPanel {
	private JTable table;
	public TableSoftware() {
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
		return new String[]{"품목번호","분류명","품목명","공급가격","판매가격"};
		
	}
	
	public Object[][] getRowdata() {
		List<JoinFromSoftware> list= JoinFromSoftwareService.getInstance().selectJoinFromSoftwareByAll();
		Object[][] datas= new Object[list.size()][];
		for(int i=0;i<datas.length;i++){
			datas[i]=list.get(i).toSoftLists();
		}
		return datas;
	}
}
