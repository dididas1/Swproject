package kr.or.digt.sw_project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JTabbedPane;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import java.awt.Font;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1143, 696);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("입력");
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("파일");
		menuBar.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("종료");
		menuBar.add(mnNewMenu_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 1117, 52);
		contentPane.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JButton btnInsert = new JButton("등록메뉴");
		btnInsert.setFont(new Font("굴림", Font.PLAIN, 16));
		panel.add(btnInsert);
		
		JButton btnSoftware = new JButton("입고 출고메뉴");
		btnSoftware.setFont(new Font("굴림", Font.PLAIN, 16));
		panel.add(btnSoftware);
		
		JButton btnFind = new JButton("조회메뉴");
		btnFind.setFont(new Font("굴림", Font.PLAIN, 16));
		panel.add(btnFind);
		
		JButton btnReport = new JButton("보고서");
		btnReport.setFont(new Font("굴림", Font.PLAIN, 16));
		panel.add(btnReport);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 56, 1117, 575);
		contentPane.add(tabbedPane);
	}

}
