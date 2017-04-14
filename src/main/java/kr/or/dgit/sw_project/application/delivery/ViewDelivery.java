package kr.or.dgit.sw_project.application.delivery;

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

import kr.or.dgit.sw_project.MainTab;
import kr.or.dgit.sw_project.dto.Delivery;
import kr.or.dgit.sw_project.service.DeliveryService;

public class ViewDelivery extends JPanel implements ActionListener{

	private JButton btnInsert;
	private TableDelivery pTable;
	private ContentDelivery pContent;
	private JButton btnDelete;
	private JButton btnCancle;
	private List<Delivery> list;
	private MainTab mainTab;


	public ViewDelivery() {
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

		ImageIcon icon = new ImageIcon(System.getProperty("user.dir")+"/build/resources/main/softwareimage/delivery.png");
		label.setIcon(icon);	
		
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

		pContent.getTfpDeSwName().getTf().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { //품목명 comboPanel 클릭시 동작            
				pContent.setComboSoftware();      
				super.mouseClicked(e);
			}         

		});
		pContent.getTfpCompName().getTf().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { //공급회사 comboPanel 클릭시 동작         
				pContent.setComboSupplyCompany();      
				super.mouseClicked(e);
			}         

		});
		pTable.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) { //table 클릭시 동작

				showFieldFromTable();//table에서 선택된 것 필드에 띄우기            
				btnDelete.setEnabled(true);   
				btnInsert.setText("수정");   
				pContent.getTfpCompName().getTf().setEditable(false);//수정할수없도록
				pContent.getTfpDeSwName().getTf().setEditable(false);//수정할수없도록
				pContent.getTfpDelAmount().getTf().setEditable(false);//수정할수없도록
				pContent.getTfpSupplyAmount().getTf().setEditable(false);//수정할수없도록         
				super.mouseClicked(e);
			}         

		});
		setTable();
		setVisible(true);
	}
	public void showFieldFromTable(){//table 클릭시 필드에 선택된table 값 띄우기 (ref)
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
	/*private Object[] getTableData() {//each data in the table클릭시 값 넘겨줌
      int cnt = pTable.getTable().getColumnCount();
      System.out.println(cnt);
      int tableRowData = pTable.getTable().getSelectedRow();
      System.out.println(tableRowData);
      Object[] obj = new Object[cnt];
      for(int i=0; i<cnt; i++){
         obj[i] = pTable.getTable().getValueAt(tableRowData, i);
      }      
      return obj;
   }*/
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnCancle) {//취소
			actionPerformedBtnCancle(arg0);
		}
		if (arg0.getSource() == btnDelete) {//삭제
			actionPerformedBtnDelete(arg0);
		}
		if (arg0.getSource() == btnInsert) {//입력(테이블 클릭시 수정으로 바뀌지만 조작할수도있으니 보류)
			actionPerformedBtnInsert(arg0);
		}
	}

	protected void actionPerformedBtnInsert(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("입력")){
			if(pContent.isEmptyCheck()||pContent.getTfpCompName().getSelectedIndex()==0
					||pContent.getTfpDeSwName().getSelectedIndex()==0){//textfield와 combopanel emptycheck
				JOptionPane.showMessageDialog(null, "입력해야될 값이 있습니다. 확인하세요");            
			}else if(pContent.isNumberCheck()){
				JOptionPane.showMessageDialog(null, "숫자만 입력하세요(납품가격,납품수량)");
			}else{
				DeliveryService.getInstance().insertDeliveryItems(pContent.getObject());//마이바티스통해 db에 insert
				list = DeliveryService.getInstance().selectDeliveryByAll(); //delivery테이블 모든값
				pTable.setDeliveryList(list);
				pTable.loadData();//table에 db에 있는 row,colum들 보여주기
				pContent.resetField();//필드초기화
				pContent.setComboSoftware();//combopanel의 software에 바뀐재고를 위해      
			}

		}else if(arg0.getActionCommand().equals("수정")){   //조작할수 있으니 보류   ???
			JOptionPane.showMessageDialog(null, "수정할수없습니다");
			/*if(pContent.isEmptyCheck()||pContent.getTfpCompName().getSelectedIndex()==0
               ||pContent.getTfpDeSwName().getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null, "입력해야될 값이 있습니다. 확인하세요");
         }else if(pContent.isNumberCheck()){
            JOptionPane.showMessageDialog(null, "숫자만 입력하세요(납품가격,납품수량)");
         }else{
            if(JOptionPane.showConfirmDialog(null, "정말 수정하시겠습니까?")==JOptionPane.YES_OPTION){
               DeliveryService.getInstance().UpdateItems(pContent.getObject());
               list = DeliveryService.getInstance().selectDeliveryByAll();
               pTable.setDeliveryList(list);
               pTable.loadData();
               btnInsert.setText("입력");
               pContent.resetField();
               pContent.setComboSoftware();
            }else{
               JOptionPane.showMessageDialog(null, "취소되었습니다");
               pContent.resetField();
               btnInsert.setText("입력");
               btnDelete.setEnabled(false);
            }
         }         */
		}      
	}
	protected void actionPerformedBtnDelete(ActionEvent arg0) {//실제 db의 값을 삭제하는것이 아닌 논리삭제
		if(JOptionPane.showConfirmDialog(null, "삭제하겠습니까?")==JOptionPane.YES_OPTION){
			DeliveryService.getInstance().existDeliveryItem(new Delivery(pContent.getTfpDelCode().getTfValue()));
			list = DeliveryService.getInstance().selectDeliveryByAll(); 
			pTable.setDeliveryList(list);
			pTable.loadData();//테이블 세팅
			pContent.resetField();//필드초기화
			pContent.setComboSoftware();//combopanel의 software에 바뀐재고를 위해   

		}else{
			JOptionPane.showMessageDialog(null, "취소되었습니다");

		}
	}
	protected void actionPerformedBtnCancle(ActionEvent arg0) {//취소버튼클릭시
		pContent.resetField(); //필드초기화
		btnInsert.setText("입력");//수정버튼이 입력으로
		btnInsert.setEnabled(true);//입력버튼 다시 작동
		btnDelete.setEnabled(false);//삭제버튼은 x
		pContent.getTfpCompName().getTf().setEditable(true);//포함 아래것들은 다시 입력할수있도록 
		pContent.getTfpDeSwName().getTf().setEditable(true);
		pContent.getTfpDelAmount().getTf().setEditable(true);
		pContent.getTfpSupplyAmount().getTf().setEditable(true);

	}
	public void setMainTab(MainTab mainTab) {
		this.mainTab=mainTab;

	}
	public TableDelivery getpTableDelivery() {
		return pTable;
	}
	
	public ContentDelivery getContentDelivery() {
		return pContent;

	}

	public void setTable(){
		list = DeliveryService.getInstance().selectDeliveryByAll();//delivery테이블 모든값 가지고옴
		pTable.setDeliveryList(list);
		pTable.loadData();//table에 db에 있는 row,colum들 보여주기
	}


}