package kr.or.dgit.sw_project;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import kr.or.dgit.sw_project.dto.Category;
import kr.or.dgit.sw_project.dto.Client;
import kr.or.dgit.sw_project.dto.JoinData;
import kr.or.dgit.sw_project.dto.Software;
import kr.or.dgit.sw_project.service.JoinDataService;

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
					Main frame = new Main();
					frame.setBounds(300, 300, 800, 800);
					
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
			JoinDataService js= new JoinDataService();
			Software sw= new Software();
			List<JoinData> list  = js.softwareFind(sw);
			Client cl = new Client();
			list= js.clinetSoftFind(cl);
			list= js.daySoftwareSaleReport(new Date(2017-04-01));
			Category ca= new Category();
			list= js.categorySaleReport(ca);
			list= js.viewBillList();
			list= js.clinetsaleGraph();
		
		}
		


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
