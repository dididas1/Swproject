package kr.or.dgit.sw_project.dao;

import java.util.List;
import java.util.Map;

import kr.or.dgit.sw_project.dto.Category;

public interface CategoryMapper {
	//분류 전체목록
	List<Category> selectAllCategory();
	//분류번호당 목록
	Category selectByNoCategory(Category category);
	//분류 등록
	int insertRowCategory(Category category);
	//분류 수정
	int updateRowCategory(Map<String, String> category);
	//분류 삭제
	int deleteRowCategory(Category Category);
}
