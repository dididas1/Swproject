package kr.or.dgit.sw_project;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MainApp extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnLogIn;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					/*MainApp frame= new MainApp();
					frame.setVisible(true);*/
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
		
		TextFieldPanel panelID = new TextFieldPanel();
		panelID.setTitle("ID");
		GridBagConstraints gbc_panelID = new GridBagConstraints();
		gbc_panelID.insets = new Insets(0, 0, 5, 0);
		gbc_panelID.fill = GridBagConstraints.BOTH;
		gbc_panelID.gridx = 0;
		gbc_panelID.gridy = 0;
		contentPane.add(panelID, gbc_panelID);
		
		TextFieldPanel panelPassword = new TextFieldPanel();
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
		
		btnLogIn = new JButton("Log-In");
		btnLogIn.addActionListener(this);
			
		panelButton.add(btnLogIn);
		
		JButton btnCancel = new JButton("Cancel");
		panelButton.add(btnCancel);
		
		JButton btnExit = new JButton("Exit");
		panelButton.add(btnExit);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLogIn) {
			btnLogInActionPerformed(e);
		}
	}
	protected void btnLogInActionPerformed(ActionEvent e) {
		MainTab tabbedSale = new MainTab();
		dispose();
	}
}

