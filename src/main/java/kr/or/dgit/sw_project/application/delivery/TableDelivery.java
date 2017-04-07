package kr.or.dgit.sw_project.application.delivery;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kr.or.dgit.sw_project.dto.Delivery;
import kr.or.dgit.sw_project.service.DeliveryService;

import java.awt.BorderLayout;
import java.util.List;

public class TableDelivery extends JPanel {
	private JTable table;
	
	
	public TableDelivery() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		loadData();
	}


	private void loadData() {
		// TODO Auto-generated method stub
		table.setModel(new DefaultTableModel(getRowData(),getColumn()));
	}	
	protected Object[][] getRowData(){
		List<Delivery> delivery = DeliveryService.getInstance().selectDeliveryByAll();
		Object[][] datas = new Object[delivery.size()][];
		for(int i=0; i<datas.length; i++){
			datas[i] = delivery.get(i).toArray();
		}
		return datas;
	}
	protected String[] getColumn(){
		return new String[]{"납품번호","공급회사번호","품목번호","공급가격","공급수량","납품날짜"};
	}
}
