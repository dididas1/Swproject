package kr.or.dgit.sw_project.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.dao.ViewCategorySaleMapper;
import kr.or.dgit.sw_project.dao.ViewCategorySaleMapperImpl;
import kr.or.dgit.sw_project.dto.ViewCategorySale;
import kr.or.dgit.sw_project.util.MyBatisSqlSessionFactory;

public class ViewCategorySaleService {
	public static final ViewCategorySaleService insetence= new ViewCategorySaleService();
	
	private ViewCategorySaleService() {}

	public static ViewCategorySaleService getInsetence() {
		return insetence;
	}

	public List<ViewCategorySale> selectViewCategoryAll() {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			ViewCategorySaleMapper viewCategorySaleMapper = new ViewCategorySaleMapperImpl(sqlSession);
			return viewCategorySaleMapper.selectViewCategoryAll();
		} 
	}

	public ViewCategorySale selectViewCategoryByNo(ViewCategorySale viewCategorySale) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			ViewCategorySaleMapper viewCategorySaleMapper = new ViewCategorySaleMapperImpl(sqlSession);
			return viewCategorySaleMapper.selectViewCategoryByNo(viewCategorySale);
		} 
	}
	
	
}
