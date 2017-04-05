package kr.or.dgit.sw_project.service;

import java.util.List;

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
			return categoryMapper.insertCategoryItem(category);
		} 
	}
	
	public int updateCategoryItem(Category category) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			CategoryMapper categoryMapper = new CategoryMapperImpl(sqlSession);
			return categoryMapper.updateCategoryItem(category);
		} 
	}

	public int deleteCategoryItem(Category category) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			CategoryMapper categoryMapper = new CategoryMapperImpl(sqlSession);
			return categoryMapper.deleteCategoryItem(category);
		} 
	}
}
