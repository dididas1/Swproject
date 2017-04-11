package kr.or.dgit.sw_project;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import kr.or.dgit.sw_project.application.category.ViewCategory;
import kr.or.dgit.sw_project.application.chart.ViewChart;
import kr.or.dgit.sw_project.application.client.ViewClient;
import kr.or.dgit.sw_project.application.delivery.ViewDelivery;
import kr.or.dgit.sw_project.application.sales.ViewSale;
import kr.or.dgit.sw_project.application.showlist.ViewList;
import kr.or.dgit.sw_project.application.software.ViewSoftware;
import kr.or.dgit.sw_project.application.supplycompany.ViewSupplyCompany;
import kr.or.dgit.sw_project.initsettingservice.InitSettingService;

public class MainTab extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnSupplyComp;
	private JButton btnSoftWare;
	private JButton btnClient;
	private JPanel pButton;
	private JButton btnChart;
	private JButton btnReport;
	private JButton btnCategory;

	private ViewSale viewSale;
	private ViewDelivery viewDelivery;
	private ViewList viewList;
	private JMenuItem mntmInit;
	private JMenuItem mntmBackup;
	private JMenuItem mntmRestore;
	private JMenuItem mntmHelp;
	private InitSettingService fileSetting = new InitSettingService();

	public MainTab() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 800);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("파일");
		menuBar.add(mnFile);

		mntmInit = new JMenuItem("초기화");
		mntmInit.addActionListener(this);
		mnFile.add(mntmInit);

		mntmBackup = new JMenuItem("백업");
		mntmBackup.addActionListener(this);
		mnFile.add(mntmBackup);

		mntmRestore = new JMenuItem("복원");
		mntmRestore.addActionListener(this);
		mnFile.add(mntmRestore);

		JMenuItem mntmExit = new JMenuItem("종료");
		mnFile.add(mntmExit);

		JMenu mnHelp = new JMenu("도움말");
		menuBar.add(mnHelp);

		mntmHelp = new JMenuItem("AboutProject");
		mntmHelp.addActionListener(this);
		mnHelp.add(mntmHelp);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		tabbedPane.add("주문 관리",viewSale = new ViewSale());
		tabbedPane.add("납품 관리",viewDelivery = new ViewDelivery());
		tabbedPane.add("거래내역 확인",viewList = new ViewList());
		//new ViewChart();
		tabbedPane.add("판매 현황 차트", new ViewChart());

		JPanel pButton = new JPanel();
		contentPane.add(pButton, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));


		btnSupplyComp = new JButton("공급사 관리");
		btnSupplyComp.addActionListener(this);
		pButton.add(btnSupplyComp);

		btnSoftWare = new JButton("S/W 관리");
		btnSoftWare.addActionListener(this);
		pButton.add(btnSoftWare);

		btnCategory = new JButton("S/W 분류 관리");
		btnCategory.addActionListener(this);
		pButton.add(btnCategory);

		btnClient = new JButton("고객 관리");
		btnClient.addActionListener(this);
		pButton.add(btnClient);

		btnChart = new JButton("통계차트");

		btnReport = new JButton("보고서");


		JButton btnChart = new JButton("통계차트");
		pButton.add(btnChart);

		JButton btnReport = new JButton("보고서");
		pButton.add(btnReport);

		if(MainApp.permission.equals("personnel")){
			btnChart.setEnabled(false);
			btnReport.setEnabled(false);
			btnSupplyComp.setEnabled(false);
			btnSoftWare.setEnabled(false);
			btnClient.setEnabled(false);
		}
		setVisible(true);

		viewDelivery.setMainTab(MainTab.this);
	}

	public void refresh(){
		viewSale.getContent().setSwComboData();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmHelp) {
			mntmHelpActionPerformed(e);
		}
		if (e.getSource() == mntmRestore) {
			mntmRestoreActionPerformed(e);
		}
		if (e.getSource() == mntmBackup) {
			mntmBackupActionPerformed(e);
		}
		if (e.getSource() == mntmInit) {
			mntmInitActionPerformed(e);
		}
		if (e.getSource() == btnClient) {
			actionPerformedBtnClient(e);
		}
		if (e.getSource() == btnSoftWare) {
			actionPerformedBtnSoftWare(e);
		}
		if (e.getSource() == btnCategory) {
			actionPerformedBtnCategory(e);
		}
		if (e.getSource() == btnSupplyComp) {
			actionPerformedBtnSupplyComp(e);
		}
	}

	protected void actionPerformedBtnSupplyComp(ActionEvent e) {
		ViewSupplyCompany viewSupplyCompany = new ViewSupplyCompany(); 
	}

	protected void actionPerformedBtnSoftWare(ActionEvent e) {
		ViewSoftware viewSoftware = new ViewSoftware();
	}

	protected void actionPerformedBtnCategory(ActionEvent e) {
		ViewCategory viewCategory = new ViewCategory();
	}

	protected void actionPerformedBtnClient(ActionEvent e) {
		ViewClient viewclient = new ViewClient();
	}

	protected void mntmInitActionPerformed(ActionEvent e) {
		if(JOptionPane.showConfirmDialog(null, "모든데이터가 삭제됩니다 계속하시겠습니까?")==JOptionPane.YES_OPTION){
			fileSetting.initSetting(0, 1);
		}
	}
	protected void mntmBackupActionPerformed(ActionEvent e) {
		if(JOptionPane.showConfirmDialog(null, "백업하시겠습니까?")==JOptionPane.YES_OPTION){
			fileSetting.initSetting(0, 0);
		}
	}
	protected void mntmRestoreActionPerformed(ActionEvent e) {
		if(JOptionPane.showConfirmDialog(null, "이전데이터가 삭제되고 저장된데이터가 입력됩니다 계속하시겠습니까?")==JOptionPane.YES_OPTION){
			fileSetting.initSetting(1, 1);
		}

	}
	protected void mntmHelpActionPerformed(ActionEvent e) {

	}
}

