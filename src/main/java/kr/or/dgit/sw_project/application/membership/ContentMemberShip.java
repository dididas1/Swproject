package kr.or.dgit.sw_project.application.membership;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import erp_myframework.PasswordPanel;
import erp_myframework.TextFieldPanel;
import kr.or.dgit.sw_project.dto.Members;
import kr.or.dgit.sw_project.service.MemberShipService;

public class ContentMemberShip extends JPanel implements KeyListener {
	private TextFieldPanel tfpMemberID;
	private PasswordPanel tfpPassword;
	private PasswordPanel tfpCheckPassword;
	private TextFieldPanel tfpMemberName;
	private TextFieldPanel tfpEmail;
	private JLabel lblShowMessage;
	private ViewMemberShip viewMemberShip;
	
	private List<Members> list;
	
	public ContentMemberShip() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {300, 50};
		gridBagLayout.rowHeights = new int[] {10, 30, 30, 30, 30, 30, 10};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		tfpMemberID = new TextFieldPanel();
		tfpMemberID.getTf().addKeyListener(this);
		tfpMemberID.setTitle("아이디");
		GridBagConstraints gbc_tfpMemberID = new GridBagConstraints(); 
		gbc_tfpMemberID.fill = GridBagConstraints.HORIZONTAL; 
		gbc_tfpMemberID.insets = new Insets(0, 0, 5, 5);
		gbc_tfpMemberID.gridx = 0;
		gbc_tfpMemberID.gridy = 1;
		add(tfpMemberID, gbc_tfpMemberID);
		
		tfpMemberName = new TextFieldPanel();
		tfpMemberName.getTf().addKeyListener(this);
		tfpMemberName.setTitle("이름");
		GridBagConstraints gbc_tfpMemberName = new GridBagConstraints();
		gbc_tfpMemberName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpMemberName.insets = new Insets(0, 0, 5, 5);
		gbc_tfpMemberName.gridx = 0;
		gbc_tfpMemberName.gridy = 2;
		add(tfpMemberName, gbc_tfpMemberName);
		
		tfpPassword = new PasswordPanel();
		tfpPassword.getPw().addKeyListener(this);
		tfpPassword.setTitle("비밀번호");
		GridBagConstraints gbc_tfpPassword = new GridBagConstraints();
		gbc_tfpPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpPassword.insets = new Insets(0, 0, 5, 5);
		gbc_tfpPassword.gridx = 0;
		gbc_tfpPassword.gridy = 3;
		add(tfpPassword, gbc_tfpPassword);
		
		tfpCheckPassword = new PasswordPanel();
		tfpCheckPassword.getPw().addKeyListener(this);
		tfpCheckPassword.setTitle("비밀번호 확인");
		GridBagConstraints gbc_tfpCheckPassword = new GridBagConstraints();
		gbc_tfpCheckPassword.insets = new Insets(0, 0, 5, 5);
		gbc_tfpCheckPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpCheckPassword.gridx = 0;
		gbc_tfpCheckPassword.gridy = 4;
		add(tfpCheckPassword, gbc_tfpCheckPassword);
		
		tfpEmail= new TextFieldPanel();
		tfpEmail.getTf().addKeyListener(this);
		tfpEmail.setTitle("E-Mail");
		GridBagConstraints gbc_tfpEmail = new GridBagConstraints();
		gbc_tfpEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpEmail.insets = new Insets(0, 0, 5, 5);
		gbc_tfpEmail.gridx = 0;
		gbc_tfpEmail.gridy = 5;
		add(tfpEmail, gbc_tfpEmail);
		
		lblShowMessage = new JLabel("");
		lblShowMessage.setForeground(Color.RED);
		GridBagConstraints gbc_lblShowMessage = new GridBagConstraints();
		gbc_lblShowMessage.insets = new Insets(0, 0, 5, 5);
		gbc_lblShowMessage.gridx = 0;
		gbc_lblShowMessage.gridy = 6;
		add(lblShowMessage, gbc_lblShowMessage);
		
		list = MemberShipService.getInstance().selectMembersByAll();
	}
	
	public void clear(){
		tfpMemberID.setTfValue("");
		tfpPassword.setPwValue("");
		tfpCheckPassword.setPwValue("");
		tfpMemberName.setTfValue("");
		tfpEmail.setTfValue("");
		tfpMemberID.requestFocus();
	}
	
	public Members getObject(){ //text필드 값받아옴 
		String memId = tfpMemberID.getTfValue();
		String memName = String.valueOf(tfpPassword.getPwValue());
		String memPassword = String.valueOf(tfpCheckPassword.getPwValue());
		String memMail = tfpMemberName.getTfValue();
		return new Members(memId, memName, memPassword, memMail);
	}
	
	public boolean isEmptyCheck(){ //빈공간체크
		for(Component c : getComponents()){
			if(c instanceof TextFieldPanel){
				TextFieldPanel tfp = (TextFieldPanel) c;
				if(tfp.isEmptyCheckWithOutFocus()){
					return true;
				}
			}
		}return false;
	}

	public boolean isPasswordEquals() { //password가 일치하는지 확인
		if(String.valueOf(tfpPassword.getPwValue()).equals(String.valueOf(tfpCheckPassword.getPwValue())))
			return true;
		return false;
	}

	public boolean isIdDuplication(){ //ID 중복확인
		for(int i=0; i<list.size(); i++){
			System.out.println("ID LIST: "+list.get(i).getMemId());
			System.out.println("TFP ID: "+tfpMemberID.getTfValue());
			
			if(list.get(i).getMemId().equals(tfpMemberID.getTfValue())){
				return true;
			}
		}
		return false;
	}
	
	/***************************  FocusListener  ***************************/	
	private void checkValue(KeyEvent e) { //입력값 확인
		String message = "";
		if(isIdDuplication()){
			message += "이미 존재하는 ID입니다.";
			viewMemberShip.getBtnInsert().setEnabled(false);
		}else if(!isPasswordEquals()){
			message += "비밀번호가 일치하지 않습니다.";
			viewMemberShip.getBtnInsert().setEnabled(false);
		}else if(isEmptyCheck()){
			message += "공란이 있습니다.";
			viewMemberShip.getBtnInsert().setEnabled(false);
		}else{
			message = "";
			viewMemberShip.getBtnInsert().setEnabled(true);
		}
		lblShowMessage.setText(message);
	}
	
	public void keyPressed(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {
		checkValue(e);
	}
	/***********************************************************************/

	public ViewMemberShip getViewMemberShip() {
		return viewMemberShip;
	}

	public void setViewMemberShip(ViewMemberShip viewMemberShip) {
		this.viewMemberShip = viewMemberShip;
	}
}