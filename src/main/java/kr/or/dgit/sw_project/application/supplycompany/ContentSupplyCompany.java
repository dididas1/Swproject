package kr.or.dgit.sw_project.application.supplycompany;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import erp_myframework.TextFieldPanel;
import kr.or.dgit.sw_project.application.address.ViewAddress;
import kr.or.dgit.sw_project.dto.SupplyCompany;
import kr.or.dgit.sw_project.service.SupplyCompService;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ContentSupplyCompany extends JPanel implements ActionListener {
	private JTextField textField;
	private TextFieldPanel tfpSupplyCompanyName;
	private TextFieldPanel tfpSupplyCompanyCode;
	private TextFieldPanel tfpSupplyCompanyAd;
	private TextFieldPanel tfpSupplyCompanyTel;
	private JButton button;
	private TextFieldPanel tfadr;
	private ViewAddress viewAddress = new ViewAddress();
	
	public ContentSupplyCompany() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {300, 50, 50};
		gridBagLayout.rowHeights = new int[] {30, 30, 30, 30, 30, 30, 20};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		tfpSupplyCompanyCode = new TextFieldPanel();
		tfpSupplyCompanyCode.setTitle("회사번호");
		tfpSupplyCompanyCode.getTf().setEditable(false);
		GridBagConstraints gbc_tfpSupplyCompanyCode = new GridBagConstraints(); 
		gbc_tfpSupplyCompanyCode.fill = GridBagConstraints.HORIZONTAL; 
		gbc_tfpSupplyCompanyCode.insets = new Insets(0, 0, 5, 5);
		gbc_tfpSupplyCompanyCode.gridx = 0;
		gbc_tfpSupplyCompanyCode.gridy = 1;
		add(tfpSupplyCompanyCode, gbc_tfpSupplyCompanyCode);
		
		tfpSupplyCompanyName = new TextFieldPanel();
		tfpSupplyCompanyName.setTitle("회사명");
		GridBagConstraints gbc_tfpSupplyCompanyName = new GridBagConstraints();
		gbc_tfpSupplyCompanyName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpSupplyCompanyName.insets = new Insets(0, 0, 5, 5);
		gbc_tfpSupplyCompanyName.gridx = 0;
		gbc_tfpSupplyCompanyName.gridy = 2;
		add(tfpSupplyCompanyName, gbc_tfpSupplyCompanyName);
		
		tfpSupplyCompanyTel = new TextFieldPanel();
		tfpSupplyCompanyTel.setTitle("전화번호");
		GridBagConstraints gbc_tfpSupplyCompanyTel = new GridBagConstraints();
		gbc_tfpSupplyCompanyTel.insets = new Insets(0, 0, 5, 5);
		gbc_tfpSupplyCompanyTel.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpSupplyCompanyTel.gridx = 0;
		gbc_tfpSupplyCompanyTel.gridy = 3;
		add(tfpSupplyCompanyTel, gbc_tfpSupplyCompanyTel);
		
		tfpSupplyCompanyAd = new TextFieldPanel();
		tfpSupplyCompanyAd.setTitle("주소");
		GridBagConstraints gbc_tfpSupplyCompanyAd = new GridBagConstraints();
		gbc_tfpSupplyCompanyAd.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfpSupplyCompanyAd.insets = new Insets(0, 0, 5, 5);
		gbc_tfpSupplyCompanyAd.gridx = 0;
		gbc_tfpSupplyCompanyAd.gridy = 4;
		add(tfpSupplyCompanyAd, gbc_tfpSupplyCompanyAd);
		
		button = new JButton("우편번호검색");
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
	
	public TextFieldPanel getTfpSupplyCompanyAd() {
		return tfpSupplyCompanyAd;
	}

	public TextFieldPanel getTfadr() {
		return tfadr;
	}

	public void initSetting(){ //코드 자동세팅 다른필드 초기화
		List<SupplyCompany> list =SupplyCompService.getInstance().selectSupplyCompByAll();
		tfpSupplyCompanyCode.setTfValue(String.format("SC%03d", list.size()+1));
		tfpSupplyCompanyName.setTfValue("");
		tfpSupplyCompanyAd.setTfValue("");
		tfpSupplyCompanyTel.setTfValue("");
		tfpSupplyCompanyName.requestFocus();
	}
	public SupplyCompany getObject(){ //text필드 값받아옴 address수정필요
		String compCode = tfpSupplyCompanyCode.getTfValue();
		String compName = tfpSupplyCompanyName.getTfValue();
		String address = tfpSupplyCompanyAd.getTfValue()+ tfadr.getTfValue();
		String compTel = tfpSupplyCompanyTel.getTfValue();
		return new SupplyCompany(compCode, compName, address, compTel);
	}
	
	public void setObject(SupplyCompany supplyCompany){ //text필드에 값세팅
		tfpSupplyCompanyCode.setTfValue(supplyCompany.getCompCode());
		tfpSupplyCompanyName.setTfValue(supplyCompany.getCompName());
		tfpSupplyCompanyAd.setTfValue(supplyCompany.getAddress());
		tfpSupplyCompanyTel.setTfValue(supplyCompany.getCompTel());
	}
	
	public boolean isEmptyCheck(){ // 빈공간체크
		for(Component c: getComponents()){
			if(c instanceof TextFieldPanel){
				TextFieldPanel tfp= (TextFieldPanel) c;
				if(tfp.isEmptyCheck()){
					return true;
				}
			}
		}return false;
		
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			buttonActionPerformed(e);
		}
	}
	//어드레스창열기
	protected void buttonActionPerformed(ActionEvent e) {
		viewAddress.setCompDao(this);
		viewAddress.setVisible(true);
	}
}
