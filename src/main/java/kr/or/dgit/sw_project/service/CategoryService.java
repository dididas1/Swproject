package kr.or.dgit.sw_project.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.connection.CategoryMapperImpl;
import kr.or.dgit.sw_project.connection.MybatisSqlSessionFactory;
import kr.or.dgit.sw_project.dto.Category;

public class CategoryService {
	private static final CategoryService instence= new CategoryService();
	
	
	public static CategoryService getInstence() {
		return instence;
	}

	public List<Category> selectAllCategory(){
		try(SqlSession sqlsession = MybatisSqlSessionFactory.opensesstion()){
			CategoryMapperImpl comp = new CategoryMapperImpl(sqlsession);
			return comp.selectAllCategory();
		}
	}
	
	public Category selectByNoCategory(Category category){
		try(SqlSession sqlsession = MybatisSqlSessionFactory.opensesstion()){
			CategoryMapperImpl comp = new CategoryMapperImpl(sqlsession);
			return comp.selectByNoCategory(category);
		}
	}
	
	public int insertRowCategory(Category category){
		try(SqlSession sqlsession = MybatisSqlSessionFactory.opensesstion()){
			CategoryMapperImpl comp = new CategoryMapperImpl(sqlsession);
			int res = comp.insertRowCategory(category);
			sqlsession.commit();
			return res;
		}
	}
	
	public int updateRowCategory(Map<String, String> category){
		try(SqlSession sqlsession = MybatisSqlSessionFactory.opensesstion()){
			CategoryMapperImpl comp = new CategoryMapperImpl(sqlsession);
			int res = comp.updateRowCategory(category);
			sqlsession.commit();
			return res;
		}
	}
	
	public int deleteRowCategory(Category category){
		try(SqlSession sqlsession = MybatisSqlSessionFactory.opensesstion()){
			CategoryMapperImpl comp = new CategoryMapperImpl(sqlsession);
			int res = comp.deleteRowCategory(category);
			sqlsession.commit();
			return res;
		}
	}
}
