package kr.or.dgit.sw_project.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.dao.ViewClientSaleMapper;
import kr.or.dgit.sw_project.dao.ViewClientSaleMapperImpl;
import kr.or.dgit.sw_project.dto.ViewClientSale;
import kr.or.dgit.sw_project.util.MyBatisSqlSessionFactory;

public class ViewClientSaleService {
	
public static final ViewClientSaleService insetence= new ViewClientSaleService();
	
	private ViewClientSaleService() {}

	public static ViewClientSaleService getInsetence() {
		return insetence;
	}
	public List<ViewClientSale> selectViewClientSaleAll() {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			ViewClientSaleMapper viewClientSaleMapper = new ViewClientSaleMapperImpl(sqlSession);
			return viewClientSaleMapper.selectViewClientSaleAll();
		} 
	}
	
	public List<ViewClientSale> selectViewClientSaleClntName(ViewClientSale viewClientSale) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			ViewClientSaleMapper viewClientSaleMapper = new ViewClientSaleMapperImpl(sqlSession);
			return viewClientSaleMapper.selectViewClientSaleClntName(viewClientSale);
		} 
	}
}
