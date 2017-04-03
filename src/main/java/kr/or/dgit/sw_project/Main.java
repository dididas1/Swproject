package kr.or.dgit.sw_project;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import kr.or.dgit.sw_project.connection.SaleMapperImpl;
import kr.or.dgit.sw_project.dao.SaleMapper;
import kr.or.dgit.sw_project.dto.Category;
import kr.or.dgit.sw_project.dto.Client;
import kr.or.dgit.sw_project.dto.JoinData;
import kr.or.dgit.sw_project.dto.Sale;
import kr.or.dgit.sw_project.dto.Software;
import kr.or.dgit.sw_project.service.JoinDataService;
import kr.or.dgit.sw_project.service.SaleService;
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
			SaleService sv = new SaleService();
			List<Sale> list  = sv.clientSoftwareReport();
			list = sv.softwareSaleReport();
			list= sv.categorySaleReport();
			list= sv.viewBillList();
			HashMap<String, String> hash= new HashMap<>();
			hash.put("order_date", "2010-10-04");
			hash.put("order_date2", "2017-10-01");
			list = sv.daySoftwareSaleReport(hash);
		}
		


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
