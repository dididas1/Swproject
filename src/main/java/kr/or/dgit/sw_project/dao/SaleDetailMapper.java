package kr.or.dgit.sw_project.dao;

import java.util.List;

import kr.or.dgit.sw_project.dto.SaleDetail;

public interface SaleDetailMapper {
	List<SaleDetail> selectSaleDetailByAll();
}
