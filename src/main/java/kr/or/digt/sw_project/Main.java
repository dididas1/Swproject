package kr.or.digt.sw_project;

import java.awt.EventQueue;

import javax.swing.JFrame;

import kr.or.digt.sw_project.service.InitSettingService;

public class Main extends JFrame {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					InitSettingService init = new InitSettingService();
					init.initSetting();
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
		
		
	}
	
	

}
