package kr.or.dgit.sw_project.application.sales;

import java.awt.Component;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import kr.or.dgit.sw_project.dto.Client;
import kr.or.dgit.sw_project.dto.JoinFromSale;
import kr.or.dgit.sw_project.dto.JoinFromSoftware;
import kr.or.dgit.sw_project.dto.Sale;
import kr.or.dgit.sw_project.dto.Software;
import kr.or.dgit.sw_project.service.ClientService;
import kr.or.dgit.sw_project.service.JoinFromSaleService;
import kr.or.dgit.sw_project.service.SaleService;
import kr.or.dgit.sw_project.service.SoftwareService;

public class ViewSale extends JPanel implements ActionListener{
	private ContentSale pContent;
	private JPanel pButton;
	private TableSale pTable;
	private JButton btnInsert;
	private JButton btnCancle;
	private JButton btnDelete;

	private List<JoinFromSale> list;

	public ViewSale() {
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

		ImageIcon icon = new ImageIcon(System.getProperty("user.dir")+"/build/resources/main/softwareimage/sale.png");
		label.setIcon(icon);	
		
		pContent = new ContentSale();
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

		pTable = new TableSale();
		GridBagConstraints gbc_pTable = new GridBagConstraints();
		gbc_pTable.fill = GridBagConstraints.BOTH;
		gbc_pTable.gridx = 0;
		gbc_pTable.gridy = 3;
		add(pTable, gbc_pTable);
		pTable.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				selectedRow();
				pContent.getTfpSwName().getTf().setEnabled(false);
				pContent.getTfpClntName().getTf().setEnabled(false);
				pContent.getTfpSaleAmount().getTf().setEditable(false);
				pContent.getDpOrderDate().getDateCombobox().setEditable(false);
				super.mousePressed(e);
			}

		});
		setTable();
		setVisible(true);
	}

	private void selectedRow() {
		String selectedCode = (String) pTable.getTable().getValueAt(pTable.getTable().getSelectedRow(), 0);
		int selectedIdx = 0;
		System.out.println(list.toString());
		for(int i=0; i<list.size(); i++){
			if(list.get(i).getSale().getSaleCode().equals(selectedCode)){
				selectedIdx=i;
				System.out.println(selectedIdx);
				break;
			}
		}
		
		JoinFromSale sale = list.get(selectedIdx);
		pContent.setSaleContent(sale);
		btnDelete.setEnabled(true);
		btnInsert.setText("수정");

	}

	public void contentAble(){
		pContent.getTfpSwName().getTf().setEnabled(true);
		pContent.getTfpClntName().getTf().setEnabled(true);
		pContent.getTfpSaleAmount().getTf().setEditable(true);
		pContent.getDpOrderDate().getDateCombobox().setEditable(true);
	}


	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancle) {
			actionPerformedBtnCancle(e);
		}
		if (e.getSource() == btnInsert) {
			btnInsertActionPerformed(e);
		}

		if (e.getSource() == btnDelete) {
			btnDeleteActionPerformed(e);
		}
	}
	/*************************** ActionListener ***************************/
	private void btnInsertActionPerformed(ActionEvent e) { //입력 수정 테이블 인덱스 클릭시 수정으로 변함
		if(e.getActionCommand().equals("입력")){
			if(pContent.isEmptyCheck() || pContent.getTfpSwName().getSelectedIndex()==0 || pContent.getTfpClntName().getSelectedIndex()==0){
				JOptionPane.showMessageDialog(null, "입력되지 않은정보가 있습니다");
				return;
			}else{
				if(isRegKey()){
					}else{
					if(JOptionPane.showConfirmDialog(null, "입력하시겠습니까?")==JOptionPane.YES_OPTION){
						List<Software> listSw=SoftwareService.getInstance().selectSoftwareByAll();
						if(Integer.parseInt(pContent.getTfpSaleAmount().getTfValue())>listSw.get(pContent.getTfpSwName().getSelectedIndex()-1).getSwInven()){
							JOptionPane.showMessageDialog(null, "재고가부족합니다");
							return;
						}else{
							SaleService.getInstance().insertSaleItem(pContent.getObject());
							setTable();
							pContent.initSetting();
							pContent.setSwComboData();
							return;
					}
				}
				

				}
			}
		}else if(e.getActionCommand().equals("수정")){ //수정으로 변경
			if(JOptionPane.showConfirmDialog(null, "수정하시겠습니까?")==JOptionPane.YES_OPTION){
				SaleService.getInstance().updateIsdeposit(pContent.getObject());
				setTable();
				btnInsert.setText("입력");
				pContent.setSwComboData();
				pContent.initSetting();
				contentAble();
			}else{
				JOptionPane.showMessageDialog(null, "취소되었습니다");
				pContent.initSetting();
				btnInsert.setText("입력");
				btnDelete.setEnabled(false);
				contentAble();
			}
		}
	}

	private void btnDeleteActionPerformed(ActionEvent e) { //삭제구현
		if(JOptionPane.showConfirmDialog(null, "삭제하겠습니까?")==JOptionPane.YES_OPTION){
			SaleService.getInstance().existSaleItem(new Sale(pContent.getTfpSaleCode().getTfValue()));
			setTable();
			pContent.initSetting();
			pContent.setSwComboData();
			btnInsert.setText("입력");
			btnDelete.setEnabled(false);
			contentAble();
		}else{
			JOptionPane.showMessageDialog(null, "취소되었습니다");

		}
	}

	private void actionPerformedBtnCancle(ActionEvent e) { //취소버튼
		pContent.initSetting();
		btnInsert.setText("입력");
		btnInsert.setEnabled(true);
		btnDelete.setEnabled(false);
		contentAble();

	}
	/***********************************************************************/

	/*************************** Get Data ***************************/  
	public void setTable(){ //Table 로드
		getDataFromDB();
		pTable.setList(list);
		pTable.setTableData();
		pContent.initSetting();
	}

	public boolean isRegKey(){// 예외처리(공백, 정규표현식)
		boolean isRegKey = true;
		if(pContent.isPatternCheck()){
			JOptionPane.showMessageDialog(null, "판매가격은 숫자만 입력 가능합니다.(9자리 미만)");
		}else{
			isRegKey = false;
		}
		return isRegKey;
	}

	private void getDataFromDB(){ //list에 데이터베이스에서 가져온 값을 입력
		list = JoinFromSaleService.getInstance().selectJoinFromSaleByAll();
	}

	public ContentSale getContentSale() {
		return pContent;
	}

	public TableSale getTableSale() {
		return pTable;
	}

}
