package kr.or.dgit.sw_project.application.delivery;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kr.or.dgit.sw_project.dto.Delivery;
import kr.or.dgit.sw_project.service.DeliveryService;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

public class TableDelivery extends JPanel {
   private JTable table;
   private List<Delivery> deliveryList;
   
   public JTable getTable() {
      return table;
   }


   public TableDelivery() {
      setLayout(new BorderLayout(0, 0));
      
      JScrollPane scrollPane = new JScrollPane();
      add(scrollPane, BorderLayout.CENTER);
      
      table = new JTable();
      scrollPane.setViewportView(table);
      
      //loadData();
   }


   public void loadData() {//set table
      // TODO Auto-generated method stub
      table.setModel(new DefaultTableModel(getRowData(),getColumn()));
   }   
   protected Object[][] getRowData(){//table의 row isDelIsExist가 true인것만
      List<Delivery> delivery = new ArrayList<Delivery>(deliveryList);
      for(int i =0; i<deliveryList.size(); i++)
         System.out.println(deliveryList.get(i).toString());
      
      for (int i = delivery.size()-1; i >= 0; i--) {
         if (!delivery.get(i).isDelIsExist()) {
            delivery.remove(i);
         }
      }
      Object[][] datas = new Object[delivery.size()][];      
      for(int i=0; i<datas.length; i++){         
            datas[i] = delivery.get(i).toArray();         
      }
      return datas;
   }
   
   public List<Delivery> getDeliveryList() {
      return deliveryList;
   }


   public void setDeliveryList(List<Delivery> deliveryList) {
      this.deliveryList = deliveryList;
   }


   protected String[] getColumn(){//table column
      return new String[]{"납품번호","납품회사","품목명","공급가격","납품수량","납품일자"};
   }
}