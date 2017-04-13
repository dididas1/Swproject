package kr.or.dgit.sw_project.application.supplycompany;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kr.or.dgit.sw_project.dto.Client;
import kr.or.dgit.sw_project.dto.JoinFromSale;
import kr.or.dgit.sw_project.dto.SupplyCompany;
import kr.or.dgit.sw_project.service.ClientService;
import kr.or.dgit.sw_project.service.SupplyCompService;

public class TableSupplyCompany extends JPanel {
   private JTable table;
   private List<SupplyCompany> list;
   
   public TableSupplyCompany() {
      setLayout(new BorderLayout(0, 0));
      
      JScrollPane scrollPane = new JScrollPane();
      add(scrollPane, BorderLayout.CENTER);
      
      table = new JTable();
      scrollPane.setViewportView(table);
   }   
   



    
   public void setTableData(){ //set table
      table.setModel(new DefaultTableModel(getRowData(), getColumm()));
   }
   private Object[] getColumm() { //column
      return new String[]{"공급사코드","공급사명","주소","전화번호"};
   }

   private Object[][] getRowData() { //table row isCompIsExist가 true인 것만
      List<SupplyCompany> listForTable = new ArrayList<SupplyCompany>(list);
      for(int i =0; i<list.size(); i++)
         System.out.println(list.get(i).toString());         
      for (int i = listForTable.size()-1; i >= 0; i--) {
         if (!listForTable.get(i).isCompIsExist()) {
            listForTable.remove(i);
         }
      }         
      Object[][] datas = new Object[listForTable.size()][];
      for (int i = 0; i < datas.length; i++) {
         datas[i] = listForTable.get(i).toArrayForTable();
      }
      return datas;
   }   
   
   public JTable getTable() {
      return table;
   }
   
   
   public void setSupplyList(List<SupplyCompany> list) {
      this.list = list;
   }

   public List<SupplyCompany> getList(){
      return list;
   }
}