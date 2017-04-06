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
import javax.swing.border.EtchedBorder;

import erp_myframework.TextFiledPanel;
import kr.or.dgit.sw_project.dto.Client;
import kr.or.dgit.sw_project.service.ClientService;

public class ContentClient extends JPanel {
	private JTextField textField;
	private TextFiledPanel tfpClientCode;
	private TextFiledPanel tfpClientName;
	private TextFiledPanel tfpClientTel;
	private TextFiledPanel tfpClientAddr;
	private JButton button;
	private TextFiledPanel tfadr;
	
	public ContentClient() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {300, 50, 50};
		gridBagLayout.rowHeights = new int[] {30, 30, 30, 30, 30, 30};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
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
		
		tfpClientTel = new TextFiledPanel();
		tfpClientTel.setTitle("전화번호");
		GridBagConstraints gbc_tfpClientTel = new GridBagConstraints();
		gbc_tfpClientTel.insets = new Insets(0, 0, 5, 5);
		gbc_tfpClientTel.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpClientTel.gridx = 0;
		gbc_tfpClientTel.gridy = 3;
		add(tfpClientTel, gbc_tfpClientTel);
		
		tfpClientAddr = new TextFiledPanel();
		tfpClientAddr.setTitle("주소");
		GridBagConstraints gbc_tfpClientAddr = new GridBagConstraints();
		gbc_tfpClientAddr.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpClientAddr.insets = new Insets(0, 0, 5, 5);
		gbc_tfpClientAddr.gridx = 0;
		gbc_tfpClientAddr.gridy = 4;
		add(tfpClientAddr, gbc_tfpClientAddr);
		
		button = new JButton("우편번호 검색");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 1;
		gbc_button.gridy = 4;
		add(button, gbc_button);
		
		tfadr= new TextFiledPanel();
		tfadr.setTitle("상세 주소");
		GridBagConstraints gbc_tfadr = new GridBagConstraints();
		gbc_tfadr.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfadr.insets = new Insets(0, 0, 5, 5);
		gbc_tfadr.gridx = 0;
		gbc_tfadr.gridy = 5;
		add(tfadr, gbc_tfadr);
		initSetting();
	}
	
	public void initSetting(){ //코드 자동세팅 다른필드 초기화
		List<Client> list = ClientService.getInstance().selectClientByAll();

		list.get(list.size() - 1).getClntCode();
		String value = String.format("CL%03d",
			Integer.parseInt(list.get(list.size() - 1).getClntCode().substring(2)) + 1);

		tfpClientCode.setTfValue(value);
		tfpClientCode.gettF().setFocusable(false);
		clear();
	}
	
	public void clear(){
		tfpClientName.setTfValue("");
		tfpClientAddr.setTfValue("");
		tfpClientTel.setTfValue("");
		tfpClientName.requestFocus();
	}
	
	public Client getObject(){ //text필드 값받아옴 address수정필요
		String clntCode = tfpClientCode.getTfValue();
		String clntName = tfpClientName.getTfValue();
		String clntAddr = tfpClientAddr.getTfValue();
		String clntTel = tfpClientTel.getTfValue();
		return new Client(clntCode, clntName, clntAddr, clntTel);
	}
	
	public void setContent(Client clinet){ //text필드에 값세팅
		tfpClientCode.setTfValue(clinet.getClntCode());
		tfpClientName.setTfValue(clinet.getClntName());
		tfpClientTel.setTfValue(clinet.getClntTel());
		tfpClientAddr.setTfValue(clinet.getClntAddr());
	}
	
	public boolean isEmptyCheck(){ //빈공간체크
		for(Component c : getComponents()){
			if(c instanceof TextFiledPanel){
				TextFiledPanel tfp = (TextFiledPanel) c;
				if(tfp.isEmpty()){
					return true;
				}
			}
		}return false;
	}
}