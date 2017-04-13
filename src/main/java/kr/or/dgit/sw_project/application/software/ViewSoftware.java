package kr.or.dgit.sw_project.application.software;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import kr.or.dgit.sw_project.dto.Category;
import kr.or.dgit.sw_project.dto.Software;
import kr.or.dgit.sw_project.service.CategoryService;
import kr.or.dgit.sw_project.service.SoftwareService;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ViewSoftware extends JPanel implements ActionListener {
	private ContentSoftware pContent;
	private TableSoftware pTable;
	private JButton btnInsert;
	private JButton btnCancle;
	private JButton btnDelete;

	public ViewSoftware() {
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
		
		ImageIcon icon = new ImageIcon(System.getProperty("user.dir")+"/build/resources/main/softwareimage/software.png");
		label.setIcon(icon);	
		

		pContent = new ContentSoftware();
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
		btnDelete.setEnabled(false);
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDelete.gridx = 2;
		gbc_btnDelete.gridy = 0;
		pButton.add(btnDelete, gbc_btnDelete);

		pTable = new TableSoftware();
		GridBagConstraints gbc_pTable = new GridBagConstraints();
		gbc_pTable.fill = GridBagConstraints.BOTH;
		gbc_pTable.gridx = 0;
		gbc_pTable.gridy = 3;
		add(pTable, gbc_pTable);
		pTable.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) { //테이블 클릭시 작동
				Object[] swObj = getSoftwareDataObject();
				btnDelete.setEnabled(true);
				btnInsert.setText("수정");
				pContent.setObject(swObj);
				super.mouseClicked(e);
			}
		});

		pContent.getSwCode();
		pContent.setComboBox();
		pTable.setTableData();
		setVisible(true);
	}

	public boolean isRegKey(){ //예외처리(공백, 정규표현식)
		boolean isRegKey = true;
		if(pContent.isWsCheck()==1){
			JOptionPane.showMessageDialog(null, "공백이 있습니다");
		}else if(pContent.isWsCheck()==2){
			JOptionPane.showMessageDialog(null, "분류를 선택하지 않았습니다.");
		}else if(pContent.isPatternCheck()){
			JOptionPane.showMessageDialog(null, "판매가격은 숫자만 입력 가능합니다.(9자리 미만)");
		}else{
			isRegKey = false;
		}
		return isRegKey;
	}

	public Object[] getSoftwareDataObject() { //클릭된 인덱스의 값을 받아와 오브젝트 배열에 넣고 리턴
		int cCnt = pTable.getTable().getColumnCount();
		int selIns = pTable.getTable().getSelectedRow();
		Object[] softwareObj = new Object[cCnt];
		for(int i=0 ; i<cCnt ; i++){
			softwareObj[i] = pTable.getTable().getValueAt(selIns, i);
		}
		return softwareObj;
	}

	public void setSwCodes() { //테이블에 있는 모든 제품코드를 오브젝트 배열에 넣고 리턴
		int swcCnt = pTable.getTable().getRowCount();
		Object[] swCodes = new Object[swcCnt];
		for(int i=0 ; i<swcCnt ; i++){
			swCodes[i] = pTable.getTable().getValueAt(i, 0);
		}
		pContent.swCodeReset(swCodes);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnDelete) {
			actionPerformedBtnDelete(e);
		}
		if (e.getSource() == btnCancle) {
			actionPerformedBtnCancle(e);
		}
		if (e.getSource() == btnInsert) {
			actionPerformedBtnInsert(e);
		}
	}
	
	protected void actionPerformedBtnInsert(ActionEvent e) {
		if(isRegKey()){}
		else{
			if(e.getActionCommand().equals("입력")){
				int ok=JOptionPane.showConfirmDialog(null, "입력하시겠습니까?");
				if(ok==0){
					pContent.insertObject();
					pTable.setTableData();
					System.out.println("입력완료");
					pContent.initSetting();
					pContent.getSwCode();
				}else{
					JOptionPane.showMessageDialog(null, "취소되었습니다");
				}
			}else if(e.getActionCommand().equals("수정")){ //수정으로 변경
				int ok=JOptionPane.showConfirmDialog(null, "수정하시겠습니까?");
				if(ok==0){
					pContent.updateObject();
					btnInsert.setText("입력");
					pTable.setTableData();
					pContent.initSetting();
					btnDelete.setEnabled(false);
					pContent.getSwCode();
				}else{
					JOptionPane.showMessageDialog(null, "취소되었습니다");
				}
			}
		}
	}
	
	protected void actionPerformedBtnDelete(ActionEvent e) {
		int ok=JOptionPane.showConfirmDialog(null, "삭제하겠습니까?");
		if(ok==0){
			SoftwareService.getInstance().existSoftwareItem(pContent.getSoftwareCode());
			pTable.setTableData();
			setSwCodes();
			pTable.setTableData();
			pContent.initSetting();
			btnInsert.setText("입력");
			btnDelete.setEnabled(false);
			pContent.getSwCode();
		}else{
			JOptionPane.showMessageDialog(null, "취소되었습니다");
		}
	}
	
	protected void actionPerformedBtnCancle(ActionEvent e) {
		pContent.initSetting();
		btnInsert.setText("입력");
		btnDelete.setEnabled(false);
		pContent.getSwCode();
	}

	public ContentSoftware getpContentSoftware() {
		return pContent;
	}

	public TableSoftware getpTableSoftware() {
		return pTable;
	}
	
	
}
