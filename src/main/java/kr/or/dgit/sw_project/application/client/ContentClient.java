package kr.or.dgit.sw_project.application.client;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import erp_myframework.TextFiledPanel;
import kr.or.dgit.sw_project.dto.Address;
import kr.or.dgit.sw_project.dto.Client;
import kr.or.dgit.sw_project.service.ClientService;

public class ContentClient extends JPanel {
	private JTextField textField;
	private TextFiledPanel tfpClientCode;
	private TextFiledPanel tfpClientName;
	private TextFiledPanel tfpCleintTel;
	private TextFiledPanel tfpClientAddr;
	private JButton button;
	private TextFiledPanel tfadr;
	public ContentClient() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {300, 50, 50};
		gridBagLayout.rowHeights = new int[] {30, 30, 30, 30, 30, 30};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		JLabel label = new JLabel("거래회사 관리");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("굴림", Font.BOLD, 14));
		
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.HORIZONTAL;
		gbc_label.insets = new Insets(10, 10, 10, 10);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		gbc_label.gridwidth = 2;
		add(label, gbc_label);
		
		tfpClientCode = new TextFiledPanel();
		tfpClientCode.setTitle("고객사 번호");
		tfpClientCode.gettF().setEditable(false);
		GridBagConstraints gbc_tfpClientCode = new GridBagConstraints(); 
		gbc_tfpClientCode.fill = GridBagConstraints.HORIZONTAL; 
		gbc_tfpClientCode.insets = new Insets(0, 0, 5, 5);
		gbc_tfpClientCode.gridx = 0;
		gbc_tfpClientCode.gridy = 1;
		add(tfpClientCode, gbc_tfpClientCode);
		
		
		tfpClientName = new TextFiledPanel();
		tfpClientName.setTitle("고객사 이름");
		GridBagConstraints gbc_tfpClientName = new GridBagConstraints();
		gbc_tfpClientName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpClientName.insets = new Insets(0, 0, 5, 5);
		gbc_tfpClientName.gridx = 0;
		gbc_tfpClientName.gridy = 2;
		add(tfpClientName, gbc_tfpClientName);
		
		
		tfpCleintTel = new TextFiledPanel();
		tfpCleintTel.setTitle("  전 화 번 호");
		GridBagConstraints gbc_tfpCleintTel = new GridBagConstraints();
		gbc_tfpCleintTel.insets = new Insets(0, 0, 5, 5);
		gbc_tfpCleintTel.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpCleintTel.gridx = 0;
		gbc_tfpCleintTel.gridy = 3;
		add(tfpCleintTel, gbc_tfpCleintTel);
		
		tfpClientAddr = new TextFiledPanel();
		tfpClientAddr.setTitle("주          소");
		GridBagConstraints gbc_tfpClientAddr = new GridBagConstraints();
		gbc_tfpClientAddr.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpClientAddr.insets = new Insets(0, 0, 5, 5);
		gbc_tfpClientAddr.gridx = 0;
		gbc_tfpClientAddr.gridy = 4;
		add(tfpClientAddr, gbc_tfpClientAddr);
		
		button = new JButton("우편번호검색");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 1;
		gbc_button.gridy = 4;
		add(button, gbc_button);
		
		tfadr= new TextFiledPanel();
		tfadr.setTitle("");
		GridBagConstraints gbc_tfadr = new GridBagConstraints();
		gbc_tfadr.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfadr.insets = new Insets(0, 0, 5, 5);
		gbc_tfadr.gridx = 0;
		gbc_tfadr.gridy = 5;
		add(tfadr, gbc_tfadr);
		initSetting();
		
		
	}
	public void initSetting(){ //코드 자동세팅 다른필드 초기화
		List<Client> list =ClientService.getInstance().selectClientByAll();
		tfpClientCode.setTfValue(String.format("CL%03d", list.size()+1));
		tfpClientName.setTfValue("");
		tfpClientAddr.setTfValue("");
		tfpCleintTel.setTfValue("");
		tfpClientName.requestFocus();
	}
	public Client getObject(){ //text필드 값받아옴 address수정필요
		String clntCode = tfpClientCode.getTfValue();
		String clntName = tfpClientName.getTfValue();
		String clntAddr = tfpClientAddr.getTfValue();
		String clntTel = tfpCleintTel.getTfValue();
		return new Client(clntCode, clntName,clntAddr, clntTel);
	}
	
	public void setObject(Client clinet){ //text필드에 값세팅
		tfpClientCode.setTfValue(clinet.getClntCode());
		tfpClientName.setTfValue(clinet.getClntName());
		tfpCleintTel.setTfValue(clinet.getClntTel());
		tfpClientAddr.setTfValue(clinet.getClntAddr());
	}
	
	public boolean isEmptyCheck(){ // 빈공간체크
		for(Component c: getComponents()){
			if(c instanceof TextFiledPanel){
				TextFiledPanel tfp= (TextFiledPanel) c;
				if(tfp.isEmpty()){
					return true;
				}
			}
		}return false;
		
	}
	
	
}
