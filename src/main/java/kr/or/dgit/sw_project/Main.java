package kr.or.dgit.sw_project;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import kr.or.dgit.sw_project.dto.Client;
import kr.or.dgit.sw_project.dto.Sale;
import kr.or.dgit.sw_project.dto.Software;
import kr.or.dgit.sw_project.service.ClientService;
import kr.or.dgit.sw_project.service.SoftwareService;

public class Main extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnsale;
	private JButton btnInsert;
	private JButton btnInquiry;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					/*MainApp frame= new MainApp();
					frame.setVisible(true);
					*/
					Main main= new Main();
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
		SoftwareService sv = new SoftwareService();
		List<Software> list= sv.allList();
		
		}
		


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
