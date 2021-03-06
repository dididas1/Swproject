package kr.or.dgit.sw_project.application.membership;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import erp_myframework.TextFieldPanel;
import kr.or.dgit.sw_project.dto.Members;

public class ContentMemberShip extends JPanel {
	private TextFieldPanel tfpMemberID;
	private TextFieldPanel tfpPassword;
	private TextFieldPanel tfpCheckPassword;
	private TextFieldPanel tfpMemberName;
	private TextFieldPanel tfpEmail;
	
	public ContentMemberShip() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {300, 50};
		gridBagLayout.rowHeights = new int[] {30, 30, 30, 30, 30};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		tfpMemberID = new TextFieldPanel();
		tfpMemberID.setTitle("아이디");
		GridBagConstraints gbc_tfpMemberID = new GridBagConstraints(); 
		gbc_tfpMemberID.fill = GridBagConstraints.HORIZONTAL; 
		gbc_tfpMemberID.insets = new Insets(0, 0, 5, 5);
		gbc_tfpMemberID.gridx = 0;
		gbc_tfpMemberID.gridy = 0;
		add(tfpMemberID, gbc_tfpMemberID);
		
		tfpMemberName = new TextFieldPanel();
		tfpMemberName.setTitle("이름");
		GridBagConstraints gbc_tfpMemberName = new GridBagConstraints();
		gbc_tfpMemberName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpMemberName.insets = new Insets(0, 0, 5, 5);
		gbc_tfpMemberName.gridx = 0;
		gbc_tfpMemberName.gridy = 1;
		add(tfpMemberName, gbc_tfpMemberName);
		
		tfpPassword = new TextFieldPanel();
		tfpPassword.setTitle("비밀번호");
		GridBagConstraints gbc_tfpPassword = new GridBagConstraints();
		gbc_tfpPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpPassword.insets = new Insets(0, 0, 5, 5);
		gbc_tfpPassword.gridx = 0;
		gbc_tfpPassword.gridy = 2;
		add(tfpPassword, gbc_tfpPassword);
		
		tfpCheckPassword = new TextFieldPanel();
		tfpCheckPassword.setTitle("비밀번호 확인");
		GridBagConstraints gbc_tfpCheckPassword = new GridBagConstraints();
		gbc_tfpCheckPassword.insets = new Insets(0, 0, 5, 5);
		gbc_tfpCheckPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpCheckPassword.gridx = 0;
		gbc_tfpCheckPassword.gridy = 3;
		add(tfpCheckPassword, gbc_tfpCheckPassword);
		
		tfpEmail= new TextFieldPanel();
		tfpEmail.setTitle("E-Mail");
		GridBagConstraints gbc_tfpEmail = new GridBagConstraints();
		gbc_tfpEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpEmail.insets = new Insets(0, 0, 5, 5);
		gbc_tfpEmail.gridx = 0;
		gbc_tfpEmail.gridy = 4;
		add(tfpEmail, gbc_tfpEmail);
	}
	
	public void clear(){
		tfpMemberID.setTfValue("");
		tfpPassword.setTfValue("");
		tfpCheckPassword.setTfValue("");
		tfpMemberName.setTfValue("");
		tfpEmail.setTfValue("");
		tfpMemberID.requestFocus();
	}
	
	public Members getObject(){ //text필드 값받아옴 
		String memId = tfpMemberID.getTfValue();
		String memName = tfpPassword.getTfValue();
		String memPassword = tfpCheckPassword.getTfValue();
		String memMail = tfpMemberName.getTfValue();
		return new Members(memId, memName, memPassword, memMail);
	}
	
	public boolean isEmptyCheck(){ //빈공간체크
		for(Component c : getComponents()){
			if(c instanceof TextFieldPanel){
				TextFieldPanel tfp = (TextFieldPanel) c;
				if(tfp.isEmptyCheck()){
					return true;
				}
			}
		}return false;
	}
}