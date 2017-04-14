package kr.or.dgit.sw_project.application.address;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import kr.or.dgit.sw_project.application.client.ContentClient;
import kr.or.dgit.sw_project.application.supplycompany.ContentSupplyCompany;
import kr.or.dgit.sw_project.dto.Address;
import kr.or.dgit.sw_project.service.AddrService;

public class ViewAddress extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JButton btnSearch;
	private JButton btnCancle;
	private JButton btnDelete;
	private ContentAddress pContent;
	private TableAddress pTable;
	private ContentClient contentClient;
	private ContentSupplyCompany contentSupplyCompany;
	
	private List<Address> list;
	
	public ViewAddress() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(500, 100, 500, 400);
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

		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.HORIZONTAL;
		gbc_label.insets = new Insets(20, 50, 0, 10);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		gbc_label.gridwidth = 5;
		getContentPane().add(label, gbc_label);
		
		ImageIcon icon = new ImageIcon(System.getProperty("user.dir")+"/build/resources/main/softwareimage/zipcode.png");
		label.setIcon(icon);	
		
		pContent = new ContentAddress();
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
		gbl_pButton.columnWidths = new int[] {100, 100};
		gbl_pButton.rowHeights = new int[]{55, 0};
		gbl_pButton.columnWeights = new double[]{0.0, 0.0};
		gbl_pButton.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		pButton.setLayout(gbl_pButton);

		btnSearch = new JButton("검색");
		btnSearch.addActionListener(this);
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSearch.insets = new Insets(0, 0, 0, 0);
		gbc_btnSearch.gridx = 0;
		gbc_btnSearch.gridy = 0;
		pButton.add(btnSearch, gbc_btnSearch);

		btnCancle = new JButton("취소");
		btnCancle.addActionListener(this);
		GridBagConstraints gbc_btnCancle = new GridBagConstraints();
		gbc_btnCancle.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancle.insets = new Insets(0, 0, 0, 0);
		gbc_btnCancle.gridx = 1;
		gbc_btnCancle.gridy = 0;
		pButton.add(btnCancle, gbc_btnCancle);

		pTable = new TableAddress();
		GridBagConstraints gbc_pTable = new GridBagConstraints();
		gbc_pTable.fill = GridBagConstraints.BOTH;
		gbc_pTable.gridx = 0;
		gbc_pTable.gridy = 3;
		getContentPane().add(pTable, gbc_pTable);
		pTable.getTable().addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				//창여러개의 창에서 주소를 쓸경우 입력되어질창 판단
				if(!(contentClient==null)){
					ClntSetAddr(getSelectedObject());
					setVisible(false);
				}
				else if(!(contentSupplyCompany==null)){
					CompSetAddr(getSelectedObject());
					setVisible(false);
				}
				super.mousePressed(e);
			}
		});

		list=Collections.EMPTY_LIST;
		pTable.setList(list);
		pTable.setTableData();
	}
	
	// 클릭된 데이터값넘김
	public String[] getSelectedObject() {
		int selectedidx= pTable.getTable().getSelectedRow();
		if(selectedidx==-1)return null;
		String zipcode= pTable.getTable().getValueAt(selectedidx, 1).toString();
		String sido= pTable.getTable().getValueAt(selectedidx, 2).toString();
		return new String[]{zipcode,sido};
	}
	//클라이언트 텍스트필드에 세팅
	public void ClntSetAddr(String[] addr){
		contentClient.getTfpClientAddr().setTfValue(addr[0]+addr[1]);
	}
	//공급회사 텍스트필드에 세팅
	public void CompSetAddr(String[] addr){
		contentSupplyCompany.getTfpSupplyCompanyAd().setTfValue(addr[0]);
		contentSupplyCompany.getTfadr().setTfValue(addr[1]);
	}
	
	//클라이언트 주소값받아옴
	public void setClntDao(ContentClient contentClient){
		this.contentClient= contentClient;
	}
	
	//공급회사 주소값받아옴
	public void setCompDao(ContentSupplyCompany contentSupplyCompany){
		this.contentSupplyCompany= contentSupplyCompany;
	}
	
	/*************************** actionPerformed ***************************/  

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSearch) {
			btnSearchActionPerformed(e);
		}
		
		if (e.getSource() == btnCancle) {
			btnCancleActionPerformed(e);
		}
	}

	private void btnSearchActionPerformed(ActionEvent e) { // 버튼누르면 주소검색
		list = AddrService.getInstance().searchSido(new Address((String) pContent.getTfpSiDo().getSelectItem(),pContent.getTfpDoro().getTfValue()));
		pTable.setList(list);
		pTable.setTableData();
	}

	private void btnCancleActionPerformed(ActionEvent e) { //취소버튼
		pContent.clear();
	}
	/***********************************************************************/
}