package kr.or.dgit.sw_project.dao;

import java.util.List;

import kr.or.dgit.sw_project.dto.ViewCategorySale;

public interface ViewCategorySaleMapper {
	List<ViewCategorySale> selectViewCategoryAll();
	ViewCategorySale selectViewCategoryByNo(ViewCategorySale viewCategorySale);
	
}
