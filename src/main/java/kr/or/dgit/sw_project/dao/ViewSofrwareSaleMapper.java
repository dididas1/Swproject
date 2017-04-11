package kr.or.dgit.sw_project.dao;

import java.util.List;

import kr.or.dgit.sw_project.dto.ViewSofrwareSale;

public interface ViewSofrwareSaleMapper {
	 List<ViewSofrwareSale> selectViewSofrwareSaleAll();
	 List<ViewSofrwareSale> selectViewsoftwareSaleBySwName(ViewSofrwareSale viewSofrwareSale);
}
