
package kr.or.dgit.sw_project.application.delivery;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import kr.or.dgit.sw_project.dto.Delivery;
import kr.or.dgit.sw_project.dto.JoinFromSale;
import kr.or.dgit.sw_project.dto.Sale;
import kr.or.dgit.sw_project.dto.SupplyCompany;
import kr.or.dgit.sw_project.service.DeliveryService;
import kr.or.dgit.sw_project.service.SaleService;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionEvent;

public class ViewDelivery extends JPanel implements ActionListener{
	
	private JButton btnInsert;
	private TableDelivery pTable;
	private ContentDelivery pContent;
	private JButton btnDelete;
	private JButton btnCancle;
	private List<Delivery> list;
	public ViewDelivery() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0}; //각 열의 최소 넓이  
		gridBagLayout.rowHeights = new int[]{0, 0, 0}; //각 행의 최소 넓이
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE}; //각 열의 가중치
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0}; //각 행의 가중치
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
		
		pContent = new ContentDelivery();
		GridBagConstraints gbc_pContent = new GridBagConstraints();
		gbc_pContent.insets = new Insets(10, 10, 10, 10);
		gbc_pContent.fill = GridBagConstraints.NONE;
		gbc_pContent.gridx = 0;
		gbc_pContent.gridy = 1;
		add(pContent, gbc_pContent);
		
		JPanel pButton = new JPanel();
		GridBagConstraints gbc_pButton = new GridBagConstraints();
		gbc_pButton.insets = new Insets(0, 0, 0, 0);
		gbc_pButton.fill = GridBagConstraints.NONE;
		gbc_pButton.gridx = 0;
		gbc_pButton.gridy = 2;
		add(pButton, gbc_pButton);
		
		GridBagLayout gbl_pButton = new GridBagLayout();
		gbl_pButton.columnWidths = new int[] {100, 100, 100};
		gbl_pButton.rowHeights = new int[]{55, 0};
		gbl_pButton.columnWeights = new double[]{0.0, 0.0, 0.0};
		gbl_pButton.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		pButton.setLayout(gbl_pButton);
		
		btnInsert = new JButton("입력");
		btnInsert.addActionListener(this);
		GridBagConstraints gbc_btnInsert = new GridBagConstraints();
		gbc_btnInsert.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnInsert.insets = new Insets(0, 0, 0, 0);
		gbc_btnInsert.gridx = 0;
		gbc_btnInsert.gridy = 0;
		pButton.add(btnInsert, gbc_btnInsert);
		
		btnCancle = new JButton("취소");
		btnCancle.addActionListener(this);
		GridBagConstraints gbc_btnCancle = new GridBagConstraints();
		gbc_btnCancle.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancle.insets = new Insets(0, 0, 0, 0);
		gbc_btnCancle.gridx = 1;
		gbc_btnCancle.gridy = 0;
		pButton.add(btnCancle, gbc_btnCancle);
		
		btnDelete = new JButton("삭제");
		btnDelete.addActionListener(this);
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDelete.gridx = 2;
		gbc_btnDelete.gridy = 0;
		pButton.add(btnDelete, gbc_btnDelete);
		
		pTable = new TableDelivery();
		GridBagConstraints gbc_pTable = new GridBagConstraints();
		gbc_pTable.fill = GridBagConstraints.BOTH;
		gbc_pTable.gridx = 0;
		gbc_pTable.gridy = 3;
		add(pTable, gbc_pTable);
		
		pTable.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { //table 클릭시 
				//Object[] deliveryObj = getTableData();
				tryAgain();
				//pContent.setObject(deliveryObj);
				btnDelete.setEnabled(true);				
				super.mouseClicked(e);
			}			

		});
		list = DeliveryService.getInstance().selectDeliveryByAll();
		pTable.setDeliveryList(list);
		pTable.loadData();
		setVisible(true);
	}
	public void tryAgain(){
		String selectedCode = (String) pTable.getTable().getValueAt(pTable.getTable().getSelectedRow(), 0);		
		int selectedIdx = 0;
		for(int i=0; i<list.size(); i++){
			if(list.get(i).getDelCode().equals(selectedCode)){
				selectedIdx=i;
				break;
			}
		}
		
			Delivery delivery = list.get(selectedIdx);
			pContent.setDeliveryContent(delivery);
			btnDelete.setEnabled(true);			
		
	}
	private Object[] getTableData() {//each data in the table클릭시 값 넘겨줌
		int cnt = pTable.getTable().getColumnCount();
		System.out.println(cnt);
		int tableRowData = pTable.getTable().getSelectedRow();
		System.out.println(tableRowData);
		Object[] obj = new Object[cnt];
		for(int i=0; i<cnt; i++){
			obj[i] = pTable.getTable().getValueAt(tableRowData, i);
		}		
		return obj;
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnCancle) {
			actionPerformedBtnCancle(arg0);
		}
		if (arg0.getSource() == btnDelete) {
			actionPerformedBtnDelete(arg0);
		}
		if (arg0.getSource() == btnInsert) {
			actionPerformedBtnInsert(arg0);
		}
	}
	
	protected void actionPerformedBtnInsert(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("입력")){
			if(pContent.isEmptyCheck()||pContent.getTfpCompName().getSelectedIndex()==0
					||pContent.getTfpDeSwName().getSelectedIndex()==0){
				JOptionPane.showMessageDialog(null, "입력해야될 값이 있습니다. 확인하세요");
				return;
			}else{
				DeliveryService.getInstance().insertDeliveryItems(pContent.getObject());
				list = DeliveryService.getInstance().selectDeliveryByAll(); 
				pTable.setDeliveryList(list);
				pTable.loadData();
				pContent.resetField();
				pContent.setComboSoftware();
				return;
			}
			
		}/*else if(arg0.getActionCommand().equals("수정")){
			if(JOptionPane.showConfirmDialog(null, "정말 수정하시겠습니까?")==JOptionPane.YES_OPTION){
				DeliveryService.getInstance().UpdateItems(pContent.getObject());
				pTable.loadData();
			}else{
				JOptionPane.showMessageDialog(null, "빠이염");
				pContent.resetField();
				btnInsert.setText("입력");
			}
			
		}*/
		
	}
	protected void actionPerformedBtnDelete(ActionEvent arg0) {
		if(JOptionPane.showConfirmDialog(null, "삭제하겠습니까?")==JOptionPane.YES_OPTION){
			DeliveryService.getInstance().existDeliveryItem(new Delivery(pContent.getTfpDelCode().getTfValue()));
			list = DeliveryService.getInstance().selectDeliveryByAll(); 
			pTable.setDeliveryList(list);
			pTable.loadData();
			pContent.resetField();
			pContent.setComboSoftware();	
			
		}else{
				JOptionPane.showMessageDialog(null, "취소되었습니다");
			
		}
	}
	protected void actionPerformedBtnCancle(ActionEvent arg0) {
		pContent.resetField(); //필드초기화
		btnInsert.setText("입력");
		btnInsert.setEnabled(true);
	}
}