package kr.or.dgit.sw_project.dao;

import java.util.List;
import java.util.Map;

import kr.or.dgit.sw_project.dto.Sale;

public interface SaleMapper {
	List<Sale> allSelectSale();
	int insertSale(Sale sale);
	int deleteSale(Sale sale);
	Sale CodeSelectSaleItem(Sale sale);
	List<Sale> clientSoftwareReport();
	List<Sale> softwareSaleReport();
	List<Sale> categorySaleReport();
	List<Sale> viewBillList();
	List<Sale> daySoftwareSaleReport(Map<String, String>  param);
	
}
