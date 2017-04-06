package kr.or.dgit.sw_project.application.client;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import kr.or.dgit.sw_project.dto.Client;
import kr.or.dgit.sw_project.service.ClientService;

public class ViewClient extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JButton btnInsert;
	private JButton btnCancle;
	private JButton btnDelete;
	private ContentClient pContent;
	private TableClient pTable;
	
	private List<Client> list;
	
	public ViewClient() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 500, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0}; //각 열의 최소 넓이  
		gridBagLayout.rowHeights = new int[]{0, 0, 0}; //각 행의 최소 넓이
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE}; //각 열의 가중치
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0}; //각 행의 가중치
		getContentPane().setLayout(gridBagLayout);

		JLabel label = new JLabel("고객사 관리");
		label.setEnabled(false);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("\uC778\uD130\uD30C\uD06C\uACE0\uB515 B", label.getFont().getStyle(), label.getFont().getSize() + 5));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.HORIZONTAL;
		gbc_label.insets = new Insets(20, 50, 0, 10);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		gbc_label.gridwidth = 5;
		getContentPane().add(label, gbc_label);
		
		pContent = new ContentClient();
		GridBagConstraints gbc_pContent = new GridBagConstraints();
		gbc_pContent.insets = new Insets(10, 10, 10, 10);
		gbc_pContent.fill = GridBagConstraints.NONE;
		gbc_pContent.gridx = 0;
		gbc_pContent.gridy = 1;
		getContentPane().add(pContent, gbc_pContent);

		JPanel pButton = new JPanel();
		GridBagConstraints gbc_pButton = new GridBagConstraints();
		gbc_pButton.insets = new Insets(0, 0, 0, 0);
		gbc_pButton.fill = GridBagConstraints.NONE;
		gbc_pButton.gridx = 0;
		gbc_pButton.gridy = 2;
		getContentPane().add(pButton, gbc_pButton);

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

		pTable = new TableClient();
		GridBagConstraints gbc_pTable = new GridBagConstraints();
		gbc_pTable.fill = GridBagConstraints.BOTH;
		gbc_pTable.gridx = 0;
		gbc_pTable.gridy = 3;
		getContentPane().add(pTable, gbc_pTable);
		
		pTable.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) { //테이블 클릭시 작동
				selectedRow();
				super.mouseClicked(e);
			}
		});
		
		pTable.getTable().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) { //테이블 키보드 방향키 이동시 작동
				selectedRow();
			}
		});
		
		getDataFromDB();
		pTable.setList(list);
		pTable.setTableData();
		setVisible(true);
	}
	
	private void selectedRow() {
		String selectedCode = (String) pTable.getTable().getValueAt(pTable.getTable().getSelectedRow(), 0);
		
		int selectedIdx = 0;
		for(int i=0; i<list.size()-1; i++){
			if(list.get(i).getClntCode().equals(selectedCode)){
				selectedIdx=i;
				break;
			}
		}
		
		Client client = list.get(selectedIdx);
		pContent.setContent(client);
		btnDelete.setEnabled(true);
		btnInsert.setText("수정");
	}
	/*************************** actionPerformed ***************************/  

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnInsert) {
			btnInsertActionPerformed(e);
		}
		
		if (e.getSource() == btnCancle) {
			btnInsert.setText("입력");
			btnCancleActionPerformed(e);
		}
		
		if (e.getSource() == btnDelete) {
			btnDeleteActionPerformed(e);
		}
	}

	private void btnInsertActionPerformed(ActionEvent e) { //입력 수정 테이블 인덱스 클릭시 수정으로 변함
		if(e.getActionCommand().equals("입력")){
			if(pContent.isEmptyCheck()){
				JOptionPane.showMessageDialog(null, "공란이 있습니다");
			}else{
				if(JOptionPane.showConfirmDialog(null, "입력하시겠습니까?")==JOptionPane.YES_OPTION){
					ClientService.getInstance().insetClntItem(pContent.getObject());
					setTable();
					pContent.initSetting();
				}
			}
		}else if(e.getActionCommand().equals("수정")){ //수정으로 변경
			if(JOptionPane.showConfirmDialog(null, "수정하시겠습니까?")==JOptionPane.YES_OPTION){
				ClientService.getInstance().updateClntItem(pContent.getObject());
				setTable();
				btnInsert.setText("입력");
				pContent.initSetting();
			}
		}
	}

	private void btnDeleteActionPerformed(ActionEvent e) { //삭제구현
		if(JOptionPane.showConfirmDialog(null, "삭제하겠습니까?")==JOptionPane.YES_OPTION){
			ClientService.getInstance().existClntItem(pContent.getObject());
			setTable();
			pContent.initSetting();
		}
	}
	
	private void btnCancleActionPerformed(ActionEvent e) { //취소버튼
		pContent.initSetting();
	}
	/***********************************************************************/
	
	
	/*************************** Get Data ***************************/  
	private void setTable(){ //Table 로드
		getDataFromDB();
		pTable.setTableData();
	}
	
	private void getDataFromDB(){ //list에 데이터베이스에서 가져온 값을 입력
		list = ClientService.getInstance().selectClientByAll();
	}
	
	/*public Client getClntDataObject() { //클릭된 인덱스의 코드를 받아 clntCode 검색후 리턴
		int selectedidx= pTable.getTable().getSelectedRow();
		if(selectedidx==-1)
			return null;
		
		String no = (String) pTable.getTable().getValueAt(selectedidx, 0);
		Client client = ClientService.getInstance().selectByNoClnt(new Client(no));
		return client;
	}*/
	/****************************************************************/
}