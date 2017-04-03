package kr.or.dgit.sw_project.application.sales;

import java.awt.BorderLayout;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TableSale extends JPanel {
	private JTable table;
	public TableSale() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		Object[] col={"주문번호","제품이름","주문수량","고객상호","주문일자"};
		Object[][] row={{"SL001","스타크래프트 리마스터",1000,"폐인pc방",new Date()},
						{"SL002","바람의 전설",2000,"폐인pc방",new Date()},
						{"SL003","니이모를 찾아서",1000,"폐인pc방",new Date()},
						{"SL004","오버워치",2000,"폐인pc방",new Date()},
						{"SL005","스타크래프트 리마스터",1000,"폐인pc방",new Date()},
						{"SL006","오버워치",2000,"폐인pc방",new Date()},
						{"SL007","스타크래프트 리마스터",1000,"폐인pc방",new Date()},
						{"SL008","오버워치",2000,"폐인pc방",new Date()},
						{"SL009","스타크래프트 리마스터",1000,"폐인pc방",new Date()},
						{"SL010","오버워치",2000,"폐인pc방",new Date()},
						{"SL011","스타크래프트 리마스터",1000,"폐인pc방",new Date()},
						{"SL012","오버워치",2000,"폐인pc방",new Date()},
						{"SL013","스타크래프트 리마스터",1000,"폐인pc방",new Date()},
						{"SL014","오버워치",2000,"폐인pc방",new Date()}};
		table.setModel(new DefaultTableModel(row, col));
	}	
}
