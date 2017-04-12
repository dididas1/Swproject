package kr.or.dgit.sw_project.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.dao.ViewSofrwareSaleMapper;
import kr.or.dgit.sw_project.dao.ViewSofrwareSaleMapperImpl;
import kr.or.dgit.sw_project.dto.ViewSofrwareSale;
import kr.or.dgit.sw_project.util.MyBatisSqlSessionFactory;

public class ViewSoftwareSaleService {
	
	public static final ViewSoftwareSaleService instence = new ViewSoftwareSaleService();
	
	private ViewSoftwareSaleService() {}

		


	public static ViewSoftwareSaleService getInstence() {
		return instence;
	}




	public List<ViewSofrwareSale> selectViewSofrwareSaleAll() {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			ViewSofrwareSaleMapper viewSofrwareSaleMapper = new ViewSofrwareSaleMapperImpl(sqlSession);
			return viewSofrwareSaleMapper.selectViewSofrwareSaleAll();
		} 
	}
	
	public List<ViewSofrwareSale> selectViewsoftwareSaleBySwName(ViewSofrwareSale viewSofrwareSale) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			ViewSofrwareSaleMapper viewSofrwareSaleMapper = new ViewSofrwareSaleMapperImpl(sqlSession);
			return viewSofrwareSaleMapper.selectViewsoftwareSaleBySwName(viewSofrwareSale);
		} 
	}
}
