package kr.or.dgit.sw_project.application.supplycompany;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kr.or.dgit.sw_project.dto.Client;
import kr.or.dgit.sw_project.dto.SupplyCompany;
import kr.or.dgit.sw_project.service.ClientService;
import kr.or.dgit.sw_project.service.SupplyCompService;

public class TableSupplyCompany extends JPanel {
	private JTable table;
	public TableSupplyCompany() {
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
		return new String[]{"공급사코드","공급사이름","공급사전화번호","공급사주소"};
		
	}
	public Object[][] getRowdata() { //테이블 로우값입력 isExist가 ture인 항목에대해서만 값받아옴
		List<SupplyCompany> list= SupplyCompService.getInstance().selectSupplyCompByAll();
		Object[][] datas= new Object[list.size()][];
		int index= 0;
		for(int i=0;i<datas.length;i++){
			if(list.get(i).isCompIsExist()){
				datas[index]=list.get(i).toArray();
				index++;
			}
		}
		return datas;
	}
}
