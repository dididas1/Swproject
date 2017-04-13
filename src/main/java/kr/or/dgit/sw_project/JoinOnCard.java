package kr.or.dgit.sw_project;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import erp_myframework.TextFieldPanel;
import kr.or.dgit.sw_project.dto.Members;
import kr.or.dgit.sw_project.service.MemberShipService;

public class JoinOnCard extends JFrame implements KeyListener {

	private JPanel contentPane;
	private TextFieldPanel tfpBarcode;
	private MainApp mainApp;

	public JoinOnCard() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(830, 450, 250, 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 200, 30};
		gbl_contentPane.rowHeights = new int[]{100, 11, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		tfpBarcode = new TextFieldPanel();
		tfpBarcode.getTf().addKeyListener(this);
		
		JLabel label = new JLabel("바코드를 입력해 주세요");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(25, 60, 0, 20);
		gbc_label.gridx = 1;
		gbc_label.gridy = 0;
		contentPane.add(label, gbc_label);
		GridBagConstraints gbc_tfpBarcode = new GridBagConstraints();
		gbc_tfpBarcode.fill = GridBagConstraints.BOTH;
		gbc_tfpBarcode.gridx = 1;
		gbc_tfpBarcode.gridy = 1;
		contentPane.add(tfpBarcode, gbc_tfpBarcode);
		
		tfpBarcode.getTf().requestFocus();
		setResizable(false);
		setVisible(true);
	}
	public void keyPressed(KeyEvent e) {
		String s = e.getKeyText(e.getKeyCode()); 
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			Members user = MemberShipService.getInstance().selectMembersByID(
					new Members(tfpBarcode.getTfValue()));
			if(user!=null){
				MainApp.permission = user.getMemPermission();
				MainTab tabbedSale = new MainTab();
				tabbedSale.setMainApp(mainApp);
				tfpBarcode.setTfValue("");
				dispose();
			}else{
				JOptionPane.showMessageDialog(null, "회원 정보가 존재하지 않습니다.");
				tfpBarcode.setTfValue("");
			}
		}
	}
	public void keyReleased(KeyEvent e) {}
	protected void keyTypedPanelTf(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}