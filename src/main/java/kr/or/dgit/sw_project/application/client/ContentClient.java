package kr.or.dgit.sw_project.application.client;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import erp_myframework.TextFieldPanel;
import kr.or.dgit.sw_project.application.address.ViewAddress;
import kr.or.dgit.sw_project.dto.Address;
import kr.or.dgit.sw_project.dto.Client;
import kr.or.dgit.sw_project.service.ClientService;

public class ContentClient extends JPanel implements ActionListener {
	private JTextField textField;
	private TextFieldPanel tfpClientCode;
	private TextFieldPanel tfpClientName;
	private TextFieldPanel tfpClientTel;
	private TextFieldPanel tfpClientAddr;
	private JButton button;
	private TextFieldPanel tfadr;
	private ViewAddress viewaddrss = new ViewAddress();
	
	public ContentClient() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {450, 50, 50};
		gridBagLayout.rowHeights = new int[] {10, 30, 30, 30, 30, 30, 10};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		tfpClientCode = new TextFieldPanel();
		tfpClientCode.setTitle("고객사 번호");
		tfpClientCode.getTf().setEditable(false);
		GridBagConstraints gbc_tfpClientCode = new GridBagConstraints(); 
		gbc_tfpClientCode.fill = GridBagConstraints.HORIZONTAL; 
		gbc_tfpClientCode.insets = new Insets(0, 0, 5, 5);
		gbc_tfpClientCode.gridx = 0;
		gbc_tfpClientCode.gridy = 1;
		add(tfpClientCode, gbc_tfpClientCode);
		
		tfpClientName = new TextFieldPanel();
		tfpClientName.setTitle("고객사 이름");
		GridBagConstraints gbc_tfpClientName = new GridBagConstraints();
		gbc_tfpClientName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpClientName.insets = new Insets(0, 0, 5, 5);
		gbc_tfpClientName.gridx = 0;
		gbc_tfpClientName.gridy = 2;
		add(tfpClientName, gbc_tfpClientName);
		
		
		tfpClientTel = new TextFieldPanel();
		tfpClientTel.setTitle("전화번호");
		GridBagConstraints gbc_tfpClientTel = new GridBagConstraints();
		gbc_tfpClientTel.insets = new Insets(0, 0, 5, 5);
		gbc_tfpClientTel.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpClientTel.gridx = 0;
		gbc_tfpClientTel.gridy = 3;
		add(tfpClientTel, gbc_tfpClientTel);
		
		tfpClientAddr = new TextFieldPanel();
		tfpClientAddr.setTitle("주소");
		GridBagConstraints gbc_tfpClientAddr = new GridBagConstraints();
		gbc_tfpClientAddr.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpClientAddr.insets = new Insets(0, 0, 5, 5);
		gbc_tfpClientAddr.gridx = 0;
		gbc_tfpClientAddr.gridy = 4;
		add(tfpClientAddr, gbc_tfpClientAddr);
		
		button = new JButton("도로명주소검색");
		button.addActionListener(this);
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 1;
		gbc_button.gridy = 4;
		add(button, gbc_button);
		
		tfadr= new TextFieldPanel();
		tfadr.setTitle("상세 주소");
		GridBagConstraints gbc_tfadr = new GridBagConstraints();
		gbc_tfadr.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfadr.insets = new Insets(0, 0, 5, 5);
		gbc_tfadr.gridx = 0;
		gbc_tfadr.gridy = 5;
		add(tfadr, gbc_tfadr);
		initSetting();
		
	}
	
	public TextFieldPanel getTfpClientAddr() {
		return tfpClientAddr;
	}


	public TextFieldPanel getTfadr() {
		return tfadr;
	}


	//???
	public void initSetting(){ //코드 자동세팅 다른필드 초기화
		List<Client> list = ClientService.getInstance().selectClientByAll();
		if(list.size()==0){
			tfpClientCode.setTfValue("CL001");
		}else{
			list.get(list.size()-1).getClntCode();
			String value = String.format("CL%03d",
			Integer.parseInt(list.get(list.size() - 1).getClntCode().substring(2)) + 1);

			tfpClientCode.setTfValue(value);
			tfpClientCode.getTf().setFocusable(false);
			clear();
			
		}
		
	}
	
	public void clear(){
		tfpClientName.setTfValue("");
		tfpClientAddr.setTfValue("");
		tfpClientTel.setTfValue("");
		tfadr.setTfValue("");
		tfpClientName.requestFocus();
	}
	
	public Client getObject(){ 
		String clntCode = tfpClientCode.getTfValue();
		String clntName = tfpClientName.getTfValue();
		String clntAddr = tfpClientAddr.getTfValue()+" "+tfadr.getTfValue();
		String clntTel = tfpClientTel.getTfValue();
		return new Client(clntCode, clntName, clntAddr, clntTel);
	}
	
	public void setContent(Client clinet){ //text필드에 값세팅
		tfpClientCode.setTfValue(clinet.getClntCode());
		tfpClientName.setTfValue(clinet.getClntName());
		tfpClientTel.setTfValue(clinet.getClntTel());
		String[] str= clinet.getClntAddr().split(" ");
		String addr="";
		tfpClientAddr.setTfValue(str[0]+" "+str[1]+" "+str[2]+" ");
		for(int i=3;i<str.length;i++){
			addr+=str[i];
		}
		tfadr.setTfValue(addr);
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
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			actionPerformedButton(e);
		}
	}
	//어드레스창 열기
	protected void actionPerformedButton(ActionEvent e) {
		viewaddrss.setClntDao(this);
		viewaddrss.setVisible(true);
	}
	
	public void tableCellAlignment(int align, int... idx) {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(align);

		
	}
}