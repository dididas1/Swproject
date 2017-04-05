package kr.or.dgit.sw_project.dao;

import java.util.List;

import kr.or.dgit.sw_project.dto.Category;

public interface CategoryMapper {
	List<Category> selectCategoryByAll();
	Category selectCategoryByNo(Category category);
	int insertCategoryItem(Category category);
	int updateCategoryItem(Category category);
	int deleteCategoryItem(Category category);
}
