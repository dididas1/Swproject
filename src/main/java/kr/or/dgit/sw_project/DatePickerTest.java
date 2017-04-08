package kr.or.dgit.sw_project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaLookAndFeel;

public class DatePickerTest {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					try {
						UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
						SyntheticaLookAndFeel.setFont("gulim", 12);
						DatePickerTest window = new DatePickerTest();
						window.frame.setVisible(true);
					} catch (UnsupportedLookAndFeelException | ParseException e) {
						e.printStackTrace();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DatePickerTest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일");
		
		/*DateComboBox dc = new DateComboBox();
		dc.setDateFormat(sdf);
		
		DateComboBoxPopup p = dc.getPopup();
		p.getMonthView().setLocale(Locale.ENGLISH);
		System.out.println(dc.getFont());
		panel.add(dc);*/
	}
}
