package kr.or.dgit.sw_project.application.client;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kr.or.dgit.sw_project.dto.Client;
import kr.or.dgit.sw_project.service.ClientService;

public class TableClient extends JPanel {
	private static final TableClient instence= new TableClient();
	
	
	public static TableClient getInstence() {
		return instence;
	}
	private JTable table;
	public TableClient() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
	}	
	
	

	public JTable getTable() {
		return table;
	}



	public void setTableData(){  //테이블 데이터입력
		table.setModel(new DefaultTableModel(getRowdata(), getColumm()));
	}
	
	public Object[] getColumm() { //컬럼입력
		return new String[]{"고객사코드","고객사이름","고객사전화번호","고객사주소"};
		
	}
	public Object[][] getRowdata() { //테이블 로우값입력 isExist가 ture인 항목에대해서만 값받아옴
		List<Client> list= ClientService.getInstance().selectClientByAll();
		Object[][] datas= new Object[list.size()][];
		int index= 0;
		for(int i=0;i<datas.length;i++){
			if(list.get(i).isClntIsExist()){
				datas[index]=list.get(i).toArray();
				index++;
			}
		}
		return datas;
	}
}
