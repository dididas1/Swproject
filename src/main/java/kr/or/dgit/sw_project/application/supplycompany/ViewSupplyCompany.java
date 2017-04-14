package kr.or.dgit.sw_project.application.supplycompany;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import kr.or.dgit.sw_project.dto.Client;
import kr.or.dgit.sw_project.dto.SupplyCompany;
import kr.or.dgit.sw_project.service.ClientService;
import kr.or.dgit.sw_project.service.SupplyCompService;

public class ViewSupplyCompany extends JPanel implements ActionListener{
	private ContentSupplyCompany pContent;
	private JPanel pButton;
	private TableSupplyCompany pTable;
	private JButton btnInsert;
	private JButton btnCancle;
	private JButton btnDelete;

	private List<SupplyCompany> list;

	public ViewSupplyCompany() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0}; //각 열의 최소 넓이  
		gridBagLayout.rowHeights = new int[]{0, 0, 0}; //각 행의 최소 넓이
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE}; //각 열의 가중치
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0}; //각 행의 가중치
		setLayout(gridBagLayout);

		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.HORIZONTAL;
		gbc_label.insets = new Insets(20, 50, 0, 10);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		gbc_label.gridwidth = 5;
		add(label, gbc_label);
		
		ImageIcon icon = new ImageIcon(System.getProperty("user.dir")+"/build/resources/main/softwareimage/supply.png");
		label.setIcon(icon);	
		
		pContent = new ContentSupplyCompany();
		GridBagConstraints gbc_pContent = new GridBagConstraints();
		gbc_pContent.insets = new Insets(10, 10, 10, 10);
		gbc_pContent.fill = GridBagConstraints.NONE;
		gbc_pContent.gridx = 0;
		gbc_pContent.gridy = 1;
		add(pContent, gbc_pContent);

		pButton = new JPanel();
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

		pTable = new TableSupplyCompany();
		GridBagConstraints gbc_pTable = new GridBagConstraints();
		gbc_pTable.fill = GridBagConstraints.BOTH;
		gbc_pTable.gridx = 0;
		gbc_pTable.gridy = 3;
		add(pTable, gbc_pTable);
		pTable.getTable().addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) { //테이블 클릭하면 동작            
				Object[] supplyCompanyObj = getTableData();            
				pContent.setObject(supplyCompanyObj);
				btnDelete.setEnabled(true);
				btnInsert.setText("수정");
				super.mouseClicked(e);
			}

		});
		setTable();
		setVisible(true);
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


	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancle) {//취소버튼
			btnCancleActionPerformed(e);
		}
		if (e.getSource() == btnDelete) {//삭제버튼
			btnDeleteActionPerformed(e);
		}
		if (e.getSource() == btnInsert) {//입력버튼(테이블 클릭시 수정으로 바뀜)
			btnInsertActionPerformed(e);
		}
	}
	private void btnInsertActionPerformed(ActionEvent e) { //입력
		if(e.getActionCommand().equals("입력")){
			if(pContent.isEmptyCheck()){//공백 체크
				JOptionPane.showMessageDialog(null, "입력해야될 값이 있습니다. 확인하세요");
			}else if(pContent.isPhoneNumberCheck()){
				JOptionPane.showMessageDialog(null, "전화번호를 다시 확인해주세요");
			}else{
				if(JOptionPane.showConfirmDialog(null, "입력하시겠습니까?")==JOptionPane.YES_OPTION){
					SupplyCompService.getInstance().insertCompItem(pContent.getObject());
					list = SupplyCompService.getInstance().selectSupplyCompByAll();
					pTable.setSupplyList(list);
					pTable.setTableData();
					pContent.resetField();

				}
			}
		}else if(e.getActionCommand().equals("수정")){ //수정         
			if(pContent.isPhoneNumberCheck()){//정규식 사용해 숫자 입력되있는지 체크
				JOptionPane.showMessageDialog(null, "전화번호를 다시 확인해주세요");
			}else{
				if(JOptionPane.showConfirmDialog(null, "정말 수정하시겠습니까?")==JOptionPane.YES_OPTION){
					SupplyCompService.getInstance().updateCompItem(pContent.getObject());   
					list = SupplyCompService.getInstance().selectSupplyCompByAll();
					pTable.setSupplyList(list);
					pTable.setTableData();
					btnInsert.setText("입력");
					pContent.resetField();//필드초기화
				}else{
					JOptionPane.showMessageDialog(null, "취소되었습니당.");
					pContent.resetField();//필드초기화
					btnInsert.setText("입력");
					btnDelete.setEnabled(false);
				}
			}         
		}
	}

	private void btnDeleteActionPerformed(ActionEvent e) { //실제 db의 값을 삭제하는것이 아닌 논리삭제
		//System.out.println("=========");
		if(JOptionPane.showConfirmDialog(null, "삭제하겠습니까?")==JOptionPane.YES_OPTION){
			SupplyCompService.getInstance().existCompItem(new SupplyCompany(pContent.getTfpSupplyCompanyCode().getTfValue()));
			list=SupplyCompService.getInstance().selectSupplyCompByAll();
			pTable.setSupplyList(list);
			pTable.setTableData();
			pContent.resetField();
			btnInsert.setText("입력");
			btnDelete.setEnabled(false);
		}else{
			JOptionPane.showMessageDialog(null, "취소되었습니다");         
		}
	}

	private void btnCancleActionPerformed(ActionEvent e) { //취소버튼
		pContent.resetField();//필드초기화
		btnInsert.setText("입력");
		btnDelete.setEnabled(false);
	}
	public TableSupplyCompany getpTable() {
		return pTable;
	}
	public ContentSupplyCompany getpContentSupplyCompany() {
		return pContent;
	}

	public void setTable(){
		list = SupplyCompService.getInstance().selectSupplyCompByAll();//supplyCompany테이블 모든값 가지고옴
		pTable.setSupplyList(list);
		pTable.setTableData();//table에 db에 있는 row,colum들 보여주기
	}
	



}