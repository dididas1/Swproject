package kr.or.dgit.sw_project;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaLookAndFeel;
import erp_myframework.TextFieldPanel;
import kr.or.dgit.sw_project.application.chart.ChartRootLayout;
import kr.or.dgit.sw_project.application.membership.ViewMemberShip;
import kr.or.dgit.sw_project.dto.Members;
import kr.or.dgit.sw_project.service.MemberShipService;

@SuppressWarnings("serial")
public class MainApp extends JFrame implements ActionListener {

	public static String permission;
	
	private JPanel contentPane;
	private JButton btnLogIn;
	private TextFieldPanel panelID;
	private TextFieldPanel panelPassword;
	private JButton btnSignIn;
	private JButton btnExit;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {  
					try {
						UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
						SyntheticaLookAndFeel.setFont("Gulim", 12);
						MainApp frame = new MainApp();
						frame.setVisible(true);
					} catch (UnsupportedLookAndFeelException | ParseException e) {
						e.printStackTrace();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(80, 80, 80, 120));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		panelID = new TextFieldPanel();
		panelID.setTitle("ID");
		panelID.setTfValue("dididas");
		GridBagConstraints gbc_panelID = new GridBagConstraints();
		gbc_panelID.insets = new Insets(0, 0, 5, 0);
		gbc_panelID.fill = GridBagConstraints.BOTH;
		gbc_panelID.gridx = 0;
		gbc_panelID.gridy = 0;
		contentPane.add(panelID, gbc_panelID);
		
		panelPassword = new TextFieldPanel();
		panelPassword.setTitle("Password");
		panelPassword.setTfValue("1234");
		GridBagConstraints gbc_panelPassword = new GridBagConstraints();
		gbc_panelPassword.insets = new Insets(0, 0, 5, 0);
		gbc_panelPassword.fill = GridBagConstraints.BOTH;
		gbc_panelPassword.gridx = 0;
		gbc_panelPassword.gridy = 1;
		contentPane.add(panelPassword, gbc_panelPassword);
		
		JPanel panelButton = new JPanel();
		GridBagConstraints gbc_panelButton = new GridBagConstraints();
		gbc_panelButton.fill = GridBagConstraints.BOTH;
		gbc_panelButton.gridx = 0;
		gbc_panelButton.gridy = 2;
		contentPane.add(panelButton, gbc_panelButton);
		panelButton.setLayout(new GridLayout(1, 0, 0, 0));
		
		btnLogIn = new JButton("Log-In");
		btnLogIn.addActionListener(this);
			
		panelButton.add(btnLogIn);
		
		btnSignIn = new JButton("Sign-In");
		btnSignIn.addActionListener(this);
		panelButton.add(btnSignIn);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(this);
		panelButton.add(btnExit);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnExit) {
			actionPerformedBtnExit(e);
		}
		if (e.getSource() == btnSignIn) {
			actionPerformedBtnSignIn(e);
		}
		if (e.getSource() == btnLogIn) {
			actionPerformedBtnLogIn(e);
		}
	}
	
	protected void actionPerformedBtnLogIn(ActionEvent e) {
		Members user = MemberShipService.getInstance().selectMembersForLogIn(new Members(panelID.getTfValue(),panelPassword.getTfValue()));
		if(user!=null){
			permission = user.getMemPermission();
			System.out.println("Permission: "+permission);
			MainTab tabbedSale = new MainTab();
			dispose();
		}else{
			MainTab tabbedSale = new MainTab();
			JOptionPane.showMessageDialog(null, "회원 정보가 존재하지 않습니다.");
		}
	}
	
	protected void actionPerformedBtnSignIn(ActionEvent e) {
		ViewMemberShip viewMemberShip = new ViewMemberShip();
	}
	
	protected void actionPerformedBtnExit(ActionEvent e) {
		ChartRootLayout chartRootLayout = new ChartRootLayout();
		chartRootLayout.showChart();
	}
}