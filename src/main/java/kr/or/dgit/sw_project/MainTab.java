package kr.or.dgit.sw_project;


import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import kr.or.dgit.sw_project.application.category.ViewCategory;
import kr.or.dgit.sw_project.application.chart.SaleChartController;
import kr.or.dgit.sw_project.application.chart.ViewChart;
import kr.or.dgit.sw_project.application.client.ViewClient;
import kr.or.dgit.sw_project.application.delivery.ViewDelivery;
import kr.or.dgit.sw_project.application.excel.GenerateExcel;
import kr.or.dgit.sw_project.application.sales.ViewSale;
import kr.or.dgit.sw_project.application.showlist.ViewList;
import kr.or.dgit.sw_project.application.software.ViewSoftware;
import kr.or.dgit.sw_project.application.supplycompany.ViewSupplyCompany;
import kr.or.dgit.sw_project.initsetting.InitSettingService;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MainTab extends JFrame implements ActionListener, ChangeListener {
	private JMenuItem mntmInit;
	private JMenuItem mntmBackup;
	private JMenuItem mntmRestore;

	private JPanel contentPane;
	private JPanel pButton;

	private JButton btnChart;
	private JButton btnReport;
	private JButton btnShowList;

	private ViewSale viewSale;
	private ViewDelivery viewDelivery;
	private ViewClient viewClient;
	private ViewSupplyCompany viewSupplyCompany;
	private ViewSoftware viewSoftware;

	private ViewCategory viewCategory;
	private InitSettingService fileSetting = new InitSettingService();
	private JTabbedPane tabbedPane;
	private ViewList viewList = new ViewList();
	private ViewChart viewChart;
	private JButton btnLogout;
	private JLabel lblNewLabel;
	private MainApp mainApp;
	private JMenuItem mntmExit;
	
	public MainTab() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1200, 900);

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

		mntmExit = new JMenuItem("종료");
		mntmExit.addActionListener(this);
		mnFile.add(mntmExit);

		JMenu mnHelp = new JMenu("도움말");
		menuBar.add(mnHelp);

		JMenuItem mntmHelp = new JMenuItem("AboutProject");
		mnHelp.add(mntmHelp);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(this);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		tabbedPane.add("주문 관리",viewSale = new ViewSale());
		tabbedPane.add("납품 관리",viewDelivery = new ViewDelivery());
		tabbedPane.add("소프트웨어 관리",viewSoftware = new ViewSoftware());
		tabbedPane.add("고객 관리",viewClient = new ViewClient());
		tabbedPane.add("공급사 관리",viewSupplyCompany = new ViewSupplyCompany());
		tabbedPane.add("S/W분류 관리",viewCategory = new ViewCategory());
		
		JPanel pButton = new JPanel();
		contentPane.add(pButton, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));

		btnShowList = new JButton("거래내역 확인");
		btnShowList.addActionListener(this);
		pButton.add(btnShowList);

		btnChart = new JButton("판매 현황 차트");
		btnChart.addActionListener(this);
		pButton.add(btnChart);

		btnReport = new JButton("엑셀파일 생성");
		btnReport.addActionListener(this);
		pButton.add(btnReport);
		
		btnLogout = new JButton("로그아웃");
		btnLogout.addActionListener(this);
		pButton.add(btnLogout);

		if(MainApp.permission.equals("personnel")){
			btnChart.setEnabled(false);
			btnReport.setEnabled(false);
		}
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmExit) {
			actionPerformedMntmExit(e);
		}
		if (e.getSource() == btnLogout) {
			actionPerformedBtnLogout(e);
		}
		if (e.getSource() == btnChart) {
			actionPerformedBtnChart(e);
		}
		if (e.getSource() == btnShowList) {
			actionPerformedBtnShowList(e);
		}
		if (e.getSource() == mntmRestore) {
			actionPerformedMntmRestore(e);
		}
		if (e.getSource() == mntmBackup) {
			actionPerformedMntmBackup(e);
		}
		if (e.getSource() == mntmInit) {
			actionPerformedMntmInit(e);
		}
		if (e.getSource() == btnReport) {
			actionPerformedBtnReport(e);
		}
	}

	protected void actionPerformedBtnShowList(ActionEvent e) {
		viewList.setVisible(false);
		viewList.setVisible(true);
		viewList.getContentList().setCategoryComboData();
		viewList.getContentList().setClntComboData();
		viewList.getContentList().setSwComboData();
	}

	protected void actionPerformedBtnSupplyComp(ActionEvent e) {
		viewSupplyCompany = new ViewSupplyCompany(); 
	}

	protected void actionPerformedBtnSoftWare(ActionEvent e) {
		viewSoftware = new ViewSoftware();
	}

	protected void actionPerformedBtnCategory(ActionEvent e) {
		viewCategory = new ViewCategory();
	}

	protected void actionPerformedBtnClient(ActionEvent e) {
		viewClient = new ViewClient();
	}

	protected void actionPerformedBtnChart(ActionEvent e) { //chart 
		viewChart= new ViewChart();
		viewChart.setVisible(false);
		viewChart.setVisible(true);
	}

	protected void actionPerformedBtnReport(ActionEvent e) {
		String path = "D:\\Chart.xls";
		GenerateExcel generateExcel = new GenerateExcel(path); 
		generateExcel.CreateExcel();
		JOptionPane.showMessageDialog(null, path+"파일이 생성되었습니다.");
		try {
			Desktop.getDesktop().edit(new File("D:\\Chart.xls"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	protected void actionPerformedMntmInit(ActionEvent e) {
		fileSetting.initSetting(0, 1);
		allRefresh();
	}
	protected void actionPerformedMntmBackup(ActionEvent e) {
		fileSetting.initSetting(0, 0);
	}
	protected void actionPerformedMntmRestore(ActionEvent e) {
		fileSetting.initSetting(1, 1);
		allRefresh();
	}
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == tabbedPane) {
			// 선택된 탭의 idx를 넘겨줌
			stateChangedThis(tabbedPane.getSelectedIndex());
		}
	}
	private void stateChangedThis(int idx) { //텝바뀔때마다 리스트갱신
		if (tabbedPane.getTitleAt(idx).equals("주문 관리")) {
			viewSale.getContentSale().setClntComboData();
			viewSale.getContentSale().setSwComboData();
			viewSale.getTableSale().setTableData();
		} else if (tabbedPane.getTitleAt(idx).equals("납품 관리")) {
			viewDelivery.getContentDelivery().setComboSoftware();
			viewDelivery.getContentDelivery().setComboSupplyCompany();
			viewDelivery.getpTableDelivery().loadData();
		} else if (tabbedPane.getTitleAt(idx).equals("소프트웨어 관리")) {
			viewSoftware.getpContentSoftware().setComboBox();
			viewSoftware.getpTableSoftware().setTableData();
		}
	}
	public void allRefresh(){
		viewCategory.getTableCategory().setTableData();
		viewClient.setTable();
		viewClient.getContentClient().initSetting();
		viewSupplyCompany.setTable();
		viewSupplyCompany.getpContentSupplyCompany().setDeliveryCode();
		viewDelivery.setTable();
		viewDelivery.getContentDelivery().setDeliveryCode();
		viewSale.getContentSale().initSetting();
		viewSale.setTable();
		viewSoftware.getpTableSoftware().setTableData();
		viewSoftware.getpContentSoftware().initSetting();
		viewSoftware.getpContentSoftware().getSwCode();
		
	}
	
	protected void tabbedPaneStateChanged(ChangeEvent e) {
	}
	
	protected void actionPerformedBtnLogout(ActionEvent e) {
		dispose();
		mainApp.showMainApp();
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	protected void actionPerformedMntmExit(ActionEvent e) {
		System.exit(0);
	}
}

