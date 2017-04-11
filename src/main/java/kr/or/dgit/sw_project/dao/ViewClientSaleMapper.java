package kr.or.dgit.sw_project.dao;

import java.util.List;

import kr.or.dgit.sw_project.dto.ViewClientSale;

public interface ViewClientSaleMapper {
	List<ViewClientSale> selectViewClientSaleAll();
	List<ViewClientSale> selectViewClientSaleClntName(ViewClientSale viewClientSale);
} 
