package kr.or.dgit.sw_project;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaLookAndFeel;
import erp_myframework.PasswordPanel;
import erp_myframework.TextFieldPanel;
import kr.or.dgit.sw_project.application.membership.ViewMemberShip;
import kr.or.dgit.sw_project.dto.Members;
import kr.or.dgit.sw_project.initsetting.InitSettingService;
import kr.or.dgit.sw_project.service.MemberShipService;

@SuppressWarnings("serial")
public class MainApp extends JFrame implements ActionListener {

	public static String permission;
	
	private JPanel contentPane;
	private JButton btnLogIn;
	private TextFieldPanel panelID;
	private PasswordPanel panelPassword;
	private JButton btnSignIn;
	private JButton btnBarCode;
	private static MainApp frame;
	private JButton btnNewButton;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {  
					try {
						UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
						SyntheticaLookAndFeel.setFont("Gulim", 12);
						frame = new MainApp();
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
		ImageIcon icon = new ImageIcon(System.getProperty("user.dir")+"/build/resources/main/softwareimage/login.png");

		//1920, 1080 
		setBounds(750, 350, 550, 270);
		contentPane = new JPanel(){
            public void paintComponent(Graphics g) {
                //  Approach 1: Dispaly image at at full size
                g.drawImage(icon.getImage(), 0, 0, null);
                //  Approach 2: Scale image to size of component
                // Dimension d = getSize();
                // g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                // Approach 3: Fix the image position in the scroll pane
                // Point p = scrollPane.getViewport().getViewPosition();
                // g.drawImage(icon.getImage(), p.x, p.y, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
		contentPane.setBorder(new EmptyBorder(100, 80, 30, 120));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		panelID = new TextFieldPanel();
		panelID.setTitle("ID");
		GridBagConstraints gbc_panelID = new GridBagConstraints();
		gbc_panelID.insets = new Insets(0, 0, 5, 0);
		gbc_panelID.fill = GridBagConstraints.BOTH;
		gbc_panelID.gridx = 0;
		gbc_panelID.gridy = 0;
		contentPane.add(panelID, gbc_panelID);
		
		panelPassword = new PasswordPanel();
		panelPassword.setTitle("Password");
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
		
		btnLogIn = new JButton("로그인");
		btnLogIn.addActionListener(this);
		panelButton.add(btnLogIn);
		
		btnSignIn = new JButton("회원가입");
		btnSignIn.addActionListener(this);
		panelButton.add(btnSignIn);
		
		btnBarCode = new JButton("사원증");
		btnBarCode.addActionListener(this);
		panelButton.add(btnBarCode);
		
		btnNewButton = new JButton("초기화");
		btnNewButton.addActionListener(this);
		panelButton.add(btnNewButton);
	}
	
	public void showMainApp(){
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewButton) {
			actionPerformedBtnNewButton(e);
		}
		if (e.getSource() == btnBarCode) {
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
		Members user = MemberShipService.getInstance().selectMembersForLogIn(
				new Members(panelID.getTfValue(),String.valueOf(panelPassword.getPwValue())));
		if(user!=null){
			permission = user.getMemPermission();
			System.out.println("Permission: "+permission);
			MainTab tabbedSale = new MainTab();
			tabbedSale.setMainApp(MainApp.this);
			panelPassword.setPwValue("");
			dispose();
		}else{
			JOptionPane.showMessageDialog(null, "회원 정보가 존재하지 않습니다.");
		}
	}
	
	protected void actionPerformedBtnSignIn(ActionEvent e) {
		ViewMemberShip viewMemberShip = new ViewMemberShip();
	}
	
	protected void actionPerformedBtnExit(ActionEvent e) {
		JoinOnCard joinOnCard = new JoinOnCard();
		panelPassword.setPwValue("");
		joinOnCard.setMainApp(MainApp.this);
		dispose();
	}
	protected void actionPerformedBtnNewButton(ActionEvent e) {
		String str = JOptionPane.showInputDialog("비밀번호를 입력해 주세요");
		if(str.equals("1234")){
			InitSettingService fileSetting = new InitSettingService();
			fileSetting.initSetting(0, 1);
		}else{
			JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다");
		}
	}
}