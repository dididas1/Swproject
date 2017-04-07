package kr.or.dgit.sw_project;

import java.awt.EventQueue;
import java.text.ParseException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaLookAndFeel;
import kr.or.dgit.sw_project.application.address.ViewAddress;

public class TestMain {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {  
					try {
						UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
						SyntheticaLookAndFeel.setFont("Gulim", 12);
						////////////////////////////////////
						
						ViewAddress viewAddress = new ViewAddress();
						
						////////////////////////////////////						
					} catch (UnsupportedLookAndFeelException | ParseException e) {
						e.printStackTrace();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
