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
import kr.or.dgit.sw_project.dto.Delivery;
import kr.or.dgit.sw_project.dto.SupplyCompany;
import kr.or.dgit.sw_project.service.DeliveryService;
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
      gridBagLayout.columnWidths = new int[] {450, 50, 50};
      gridBagLayout.rowHeights = new int[] {10, 30, 30, 30, 30, 30, 10};
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
      
      
      resetField();
      
      
   }
   
   public TextFieldPanel getTfpSupplyCompanyAd() {
      return tfpSupplyCompanyAd;
   }

   public TextFieldPanel getTfadr() {
      return tfadr;
   }
   

   public TextFieldPanel getTfpSupplyCompanyCode() {
      return tfpSupplyCompanyCode;
   }

   public void resetField(){ //필드 초기화
      setDeliveryCode();            
      tfpSupplyCompanyName.setTfValue("");
      tfpSupplyCompanyAd.setTfValue("");
      tfpSupplyCompanyTel.setTfValue("");
      tfadr.setTfValue("");
      tfpSupplyCompanyName.requestFocus();
   }
   public void setDeliveryCode() {//맨 마지막 코드 다음꺼로 세팅
      
      List<SupplyCompany> list =SupplyCompService.getInstance().selectSupplyCompByAll();
      if(list.size()==0){
         tfpSupplyCompanyCode.setTfValue("SC001");
      }else{         
         tfpSupplyCompanyCode.setTfValue(String.format(getSupplyCompanyCode(), list.size()+1));
         tfpSupplyCompanyCode.getTf().setFocusable(false);
      }
   
   }
   private String getSupplyCompanyCode() {      //ref coffee
      return "SC%03d";
   }
   public SupplyCompany getObject(){ //fu....입력값들
      String compCode = tfpSupplyCompanyCode.getTfValue();
      String compName = tfpSupplyCompanyName.getTfValue();
      String address = tfpSupplyCompanyAd.getTfValue()+" "+tfadr.getTfValue();
      String compTel = tfpSupplyCompanyTel.getTfValue();
      return new SupplyCompany(compCode, compName, address, compTel);
   }
   
   
   public void setObject(Object[] supplyCompanyObj) {//테이블의 값들 필드에 들고오기
      // TODO Auto-generated method stub
      tfpSupplyCompanyCode.setTfValue(String.valueOf(supplyCompanyObj[0]));         
      tfpSupplyCompanyName.setTfValue(String.valueOf(supplyCompanyObj[1]));   
      tfpSupplyCompanyAd.setTfValue(String.valueOf(supplyCompanyObj[2]));   
      tfpSupplyCompanyTel.setTfValue(String.valueOf(supplyCompanyObj[3]));   
   }
   public boolean isPhoneNumberCheck(){//전화번호 체크
      if(!tfpSupplyCompanyTel.getTfValue().trim().matches(getRegularPhone())){
         return true;
      }
      return false;
   }
   public String getRegularPhone(){
      return "^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$";
   }
   public boolean isEmptyCheck(){ // 공백체크
      for(Component c: getComponents()){
         if(c instanceof TextFieldPanel){
            TextFieldPanel tfp= (TextFieldPanel) c;
            if(tfp.isEmptyCheck()){
               return true;
            }
         }
      }
      return false;      
   }
   
   public void actionPerformed(ActionEvent e) {//주소검색
      if (e.getSource() == button) {
         buttonActionPerformed(e);
      }
   }
   
   protected void buttonActionPerformed(ActionEvent e) {//주소검색
      viewAddress.setCompDao(this);
      viewAddress.setVisible(true);
   }
}