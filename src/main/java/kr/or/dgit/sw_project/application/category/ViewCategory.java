package kr.or.dgit.sw_project.application.category;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import kr.or.dgit.sw_project.dto.Category;
import kr.or.dgit.sw_project.service.CategoryService;

@SuppressWarnings("serial")
public class ViewCategory extends JPanel implements ActionListener{
	private JPanel contentPane;
	private ContentCategory pContent;
	private JPanel pButton;
	private TableCategory pTable;
	private JButton btnInsert;
	private JButton btnCancle;
	private JButton btnDelete;
	public ViewCategory() {
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
		
		ImageIcon icon = new ImageIcon(System.getProperty("user.dir")+"/build/resources/main/softwareimage/category.png");
		label.setIcon(icon);	

		pContent = new ContentCategory();
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
		btnDelete.setEnabled(false);
		gbc_btnDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDelete.gridx = 2;
		gbc_btnDelete.gridy = 0;
		pButton.add(btnDelete, gbc_btnDelete);

		pTable = new TableCategory();
		GridBagConstraints gbc_pTable = new GridBagConstraints();
		gbc_pTable.fill = GridBagConstraints.BOTH;
		gbc_pTable.gridx = 0;
		gbc_pTable.gridy = 3;
		add(pTable, gbc_pTable);
		pTable.getTable().addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) { //테이블 클릭시 작동
				Category su = getCategoryDataObject();
				pContent.setObject(su);
				btnDelete.setEnabled(true);
				btnInsert.setText("수정");
				super.mouseClicked(e);
			}

		});
		
		pTable.setTableData();
		setVisible(true);
	}

	public Category getCategoryDataObject() { //클릭된 인덱스의 코드를 받아와 클라이언트 넘버검색후 리턴
		int selectedidx= pTable.getTable().getSelectedRow();
		if(selectedidx==-1)return null;
		String no=(String) pTable.getTable().getValueAt(selectedidx, 0);
		Category su = CategoryService.getInstance().selectCategoryByNo(new Category(no));
		return su;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancle) {
			btnCancleActionPerformed(e);
		}
		if (e.getSource() == btnDelete) {
			btnDeleteActionPerformed(e);
		}
		if (e.getSource() == btnInsert) {
			btnInsertActionPerformed(e);
		}
	}
	
	public boolean isKey(int key){// 예외처리(공백, 정규표현식, 중복)
		boolean isKey = true;
		Category su = getCategoryDataObject();
		if(pContent.isEmptyCheck()){
			JOptionPane.showMessageDialog(null, "공란이 있습니다");
		}else if(pContent.isPatternCheck()){
			JOptionPane.showMessageDialog(null, "입력양식에 맞지않습니다.(분류코드:영문대문자 2자리까지 입력가능, 분류명:한글만 입력가능)");
		}else if(pContent.isOverlapNameAndCodeCheck(su, key) == false){
			isKey = false;
		}else if(pContent.isOverlapNameAndCodeCheck(su, key)){
			JOptionPane.showMessageDialog(null, "중복된 분류명 또는 코드명이 있습니다");
		}else{
			isKey = false;
		}
		return isKey;
	}
	
	protected void btnInsertActionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("입력")){
			if(isKey(0)){}
			else{
				int ok=JOptionPane.showConfirmDialog(null, "입력하시겠습니까?");
				if(ok==0){
					CategoryService.getInstance().insertCategoryItem(pContent.getObject());
					pTable.setTableData();
					pContent.initSetting();
				}else{
					JOptionPane.showMessageDialog(null, "취소되었습니다");
				}
			}

		}else if(e.getActionCommand().equals("수정")){ //수정으로 변경
			if(isKey(1)){}
			else{
				int ok=JOptionPane.showConfirmDialog(null, "수정하시겠습니까?");
				if(ok==0){
					Category su = getCategoryDataObject();
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("groupCode1",pContent.getCategoryCode());
					param.put("groupCode2",su.getGroupCode());
					param.put("groupName",pContent.getCategoryName());
					CategoryService.getInstance().updateCategoryItem(param);
					btnInsert.setText("입력");
					pTable.setTableData();
					pContent.initSetting();
					btnDelete.setEnabled(false);
				}else{
					JOptionPane.showMessageDialog(null, "취소되었습니다");
				}
			}
		}
	}

	protected void btnDeleteActionPerformed(ActionEvent e) { //삭제구현
		int ok=JOptionPane.showConfirmDialog(null, "삭제하겠습니까?");
		if(ok==0){
			CategoryService.getInstance().deleteCategoryItem(pContent.getObject());
			pTable.setTableData();
			pContent.initSetting();
			btnInsert.setText("입력");
		}else{
			JOptionPane.showMessageDialog(null, "취소되었습니다");
			btnInsert.setText("입력");
		}
	}
	protected void btnCancleActionPerformed(ActionEvent e) { //취소버튼
		pContent.initSetting();
		btnInsert.setText("입력");
		btnDelete.setEnabled(false);
	}

	public ContentCategory getContentCategory() {
		return pContent;
	}

	public TableCategory getTableCategory() {
		return pTable;
	}
}