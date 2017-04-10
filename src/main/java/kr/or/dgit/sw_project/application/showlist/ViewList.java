package kr.or.dgit.sw_project.application.showlist;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import kr.or.dgit.sw_project.dto.JoinFromSale;
import kr.or.dgit.sw_project.service.ClientService;
import kr.or.dgit.sw_project.service.JoinFromSaleService;

public class ViewList extends JPanel implements ActionListener {
	private ContentList pContent;
	private TableList pTable;

	List<JoinFromSale> list;
	public ViewList() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0}; //각 열의 최소 넓이  
		gridBagLayout.rowHeights = new int[]{0, 0}; //각 행의 최소 넓이
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE}; //각 열의 가중치
		gridBagLayout.rowWeights = new double[]{0.0, 0.0}; //각 행의 가중치
		setLayout(gridBagLayout);
		
		JLabel label = new JLabel("소프트웨어 관리");
		label.setEnabled(false);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("\uC778\uD130\uD30C\uD06C\uACE0\uB515 B", label.getFont().getStyle(), label.getFont().getSize() + 5));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.HORIZONTAL;
		gbc_label.insets = new Insets(20, 50, 0, 10);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		gbc_label.gridwidth = 5;
		add(label, gbc_label);
		
		pContent = new ContentList();
		pContent.getBtnSwAllFind().addActionListener(this);
		
		GridBagConstraints gbc_pContent = new GridBagConstraints();
		gbc_pContent.insets = new Insets(10, 10, 30, 10);
		gbc_pContent.fill = GridBagConstraints.NONE;
		gbc_pContent.gridx = 0;
		gbc_pContent.gridy = 1;
		add(pContent, gbc_pContent);
		
		pTable = TableList.getInstence();
		GridBagConstraints gbc_pTable = new GridBagConstraints();
		gbc_pTable.insets = new Insets(0, 0, 0, 0);
		gbc_pTable.fill = GridBagConstraints.BOTH;
		gbc_pTable.gridx = 0;
		gbc_pTable.gridy = 2;
		add(pTable, gbc_pTable);
		
		
		setVisible(true);
		
	
	}

	
	
	

	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == pContent.getBtnSwAllFind()) {
			pContentBtnSwAllFindActionPerformed(e);
		}
	}
	protected void pContentBtnSwAllFindActionPerformed(ActionEvent e) {
		getDataFromDB();
		pTable.setList(list);
		pTable.setTableData();
		
	}
	
	
	/*************************** Get Data ***************************/  
	private void setTable(){ //Table 로드
		getDataFromDB();
		pTable.setList(list);
		pTable.setTableData();
	}
	
	private void getDataFromDB(){ //list에 데이터베이스에서 가져온 값을 입력
		list = JoinFromSaleService.getInstance().selectJoinFromSaleByAll();
	}
	/****************************************************************/
}
