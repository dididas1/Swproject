package kr.or.dgit.sw_project.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.dao.CategoryMapper;
import kr.or.dgit.sw_project.dao.CategoryMapperImpl;
import kr.or.dgit.sw_project.dto.Category;
import kr.or.dgit.sw_project.util.MyBatisSqlSessionFactory;
public class CategoryService {
	public static final CategoryService instance = new CategoryService();
	private CategoryService() {}
	public static CategoryService getInstance(){
		return instance;
	}
	
	public List<Category> selectCategoryByAll() {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			CategoryMapper categoryMapper = new CategoryMapperImpl(sqlSession);
			return categoryMapper.selectCategoryByAll();
		} 
	}
	
	public Category selectCategoryByNo(Category category) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			CategoryMapper categoryMapper = new CategoryMapperImpl(sqlSession);
			return categoryMapper.selectCategoryByNo(category);
		} 
	}

	public int insertCategoryItem(Category category) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			CategoryMapper categoryMapper = new CategoryMapperImpl(sqlSession);
			int res = categoryMapper.insertCategoryItem(category);
			sqlSession.commit();
			return res;
		} 
	}
	
	public int updateCategoryItem(Map<String, Object> category) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			CategoryMapper categoryMapper = new CategoryMapperImpl(sqlSession);
			int res = categoryMapper.updateCategoryItem(category);
			sqlSession.commit();
			return res;
		} 
	}

	public int deleteCategoryItem(Category category) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			CategoryMapper categoryMapper = new CategoryMapperImpl(sqlSession);
			int res = categoryMapper.deleteCategoryItem(category);
			sqlSession.commit();
			return res;
		} 
	}
}
