package kr.or.dgit.sw_project.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.dao.ViewOrderDateSaleMapper;
import kr.or.dgit.sw_project.dao.ViewOrderDateSaleMapperImpl;
import kr.or.dgit.sw_project.dto.ViewOrderDateSale;
import kr.or.dgit.sw_project.util.MyBatisSqlSessionFactory;

public class ViewOrderDateSaleService {
	public static final ViewOrderDateSaleService instence = new ViewOrderDateSaleService();
	
	
	private ViewOrderDateSaleService() {}


	public static ViewOrderDateSaleService getInstence() {
		return instence;
	}
	
	public List<ViewOrderDateSale> selectViewOrderDateAll() {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			ViewOrderDateSaleMapper viewOrderDateSaleMapper = new ViewOrderDateSaleMapperImpl(sqlSession);
			return viewOrderDateSaleMapper.selectViewOrderDateAll();
		} 
	}
	
	public List<ViewOrderDateSale> selectViewOrderDateSaleThisYear(Map<String, Object> param) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			ViewOrderDateSaleMapper viewOrderDateSaleMapper = new ViewOrderDateSaleMapperImpl(sqlSession);
			return viewOrderDateSaleMapper.selectViewOrderDateSale(param);
		} 
	}
}
