package kr.or.dgit.sw_project.dao;

import java.util.List;
import java.util.Map;

import kr.or.dgit.sw_project.dto.ViewOrderDateSale;

public interface ViewOrderDateSaleMapper {
	List<ViewOrderDateSale> selectViewOrderDateSaleThisYear(Map<String, Object> param);
}
