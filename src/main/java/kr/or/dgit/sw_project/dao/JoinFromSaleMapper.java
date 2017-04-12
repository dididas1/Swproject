package kr.or.dgit.sw_project.dao;

import java.util.List;
import java.util.Map;

import kr.or.dgit.sw_project.dto.JoinFromSale;

public interface JoinFromSaleMapper {
	List<JoinFromSale> selectJoinFromSaleByAll();
	List<JoinFromSale> selectJoinFromSaleByYear(Map<String, Object> date);
	List<JoinFromSale> selectJoinFromSaleByAllOrderByOrderDate();
}
