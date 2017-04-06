package kr.or.dgit.sw_project.application.showlist;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kr.or.dgit.sw_project.dto.JoinFromSoftware;
import kr.or.dgit.sw_project.dto.Sale;
import kr.or.dgit.sw_project.service.JoinFromSoftwareService;
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
	public void setTableData(int s){
		table.setModel(new DefaultTableModel(getRowdata(s), getColumm()));
	}
	
	public Object[] getColumm() {
		return new String[]{"판매코드","품목명 ","분류 ","공급회사명 ","공급금액 ","판매금액 ","판매이윤"};
		
	}
	public Object[][] getRowdata(int s) {
		List<JoinFromSoftware> list= JoinFromSoftwareService.getInstance().selectJoinFromSoftwareByAll();
		Object[][] datas= new Object[list.size()][];
		for(int i=0;i<datas.length;i++){
			if(s==1){
				datas[i]=list.get(i).toClntReport();
			}else if(s==2){
				datas[i]=list.get(i).toSoftReport();
			}
		}
		return datas;
	}
	

}
