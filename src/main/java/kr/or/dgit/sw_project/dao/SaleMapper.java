package kr.or.dgit.sw_project.dao;

import java.util.List;

import kr.or.dgit.sw_project.dto.Sale;

public interface SaleMapper {
	List<Sale> selectSaleByAll();
	int insertSaleItem(Sale sale);
    int updateSaleItem(Sale sale);
    int existSaleItem(Sale sale);
    int updateIsdeposit(Sale sale);
}
