package kr.or.dgit.sw_project.dao;

import java.util.List;

import kr.or.dgit.sw_project.dto.Sale;

public interface SaleMapper {
	List<Sale> allSelectSale();
	int insertSale(Sale sale);
	int deleteSale(Sale sale);
	Sale CodeSelectSaleItem(Sale sale);
}
