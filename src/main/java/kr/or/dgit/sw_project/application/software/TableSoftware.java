package kr.or.dgit.sw_project.application.software;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

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
		tableCellAlignment();
	}
	
	public Object[] getColumm() { //컬럼값입력
		return new String[]{"제품번호","분류명","제품명","판매가격","재고량"};
		
	}
	
	public Object[][] getRowdata() { //하나의 소프트웨어 목록을 가져와 테이블에 입력준비
		List<JoinFromSoftware> listForTable= JoinFromSoftwareService.getInstance().selectJoinFromSoftwareByAll();
		
		for(int i =0; i<listForTable.size(); i++)
			System.out.println(listForTable.get(i).toString());
			
		
		for (int i = listForTable.size()-1; i >= 0; i--) {
			if (!listForTable.get(i).getSoftware().isSwIsSale()) {
				listForTable.remove(i);
			}
		}
		
		Object[][] datas = new Object[listForTable.size()][];
		for (int i = 0; i < datas.length; i++) {
			datas[i] = listForTable.get(i).toSoftLists();
		}
		return datas;
	}

	
	
	public void tableCellAlignment(){// (제품코드, 분류명, 제품명 : 가운데정렬), (공급가격,판매가격 : 왼쪽정렬)
		DefaultTableCellRenderer dtcr1 = new DefaultTableCellRenderer();
		DefaultTableCellRenderer dtcr2 = new DefaultTableCellRenderer();
		dtcr1.setHorizontalAlignment(SwingConstants.TRAILING);
		dtcr2.setHorizontalAlignment(SwingConstants.CENTER);
		
		TableColumnModel tcm = table.getColumnModel();
		for(int i=0 ; i<tcm.getColumnCount() ; i++){
			if(i>2){
				tcm.getColumn(i).setCellRenderer(dtcr1);
			}else{
				tcm.getColumn(i).setCellRenderer(dtcr2);
			}
		}
	}
}
