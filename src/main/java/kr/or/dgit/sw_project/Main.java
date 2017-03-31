package kr.or.dgit.sw_project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnsale;
	private JTabbedPane tabbedPane;
	private JButton btnInsert;
	private JButton btnInquiry;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setBounds(300, 300, 800, 800);
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("New menu");
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("New menu");
		menuBar.add(mnNewMenu_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		contentPane.add(panel, BorderLayout.NORTH);
		
		btnsale = new JButton("소프트웨어 입출고");
		btnsale.addActionListener(this);
		btnsale.setHorizontalAlignment(SwingConstants.LEADING);
		panel.add(btnsale);
		
		btnInsert = new JButton("등록메뉴");
		btnInsert.addActionListener(this);
		panel.add(btnInsert);
		
		btnInquiry = new JButton("조회메뉴");
		btnInquiry.addActionListener(this);
		panel.add(btnInquiry);
		
		btnsale.setEnabled(false);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnInquiry) {
			btnInquiryActionPerformed(e);
		}
		if (e.getSource() == btnInsert) {
			btnInsertActionPerformed(e);
		}
		if (e.getSource() == btnsale) {
			btnsaleActionPerformed(e);
		}
	}
	protected void btnsaleActionPerformed(ActionEvent e) {
		tabbedPane.removeAll();
		btnInquiry.setEnabled(true);
		btnsale.setEnabled(false);
	}
	protected void btnInsertActionPerformed(ActionEvent e) {
		
		
		
	}
	protected void btnInquiryActionPerformed(ActionEvent e) {
		tabbedPane.removeAll();
		btnsale.setEnabled(true);
		btnInquiry.setEnabled(false);
	}
}
