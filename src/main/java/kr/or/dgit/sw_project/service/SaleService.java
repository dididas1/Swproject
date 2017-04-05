package kr.or.dgit.sw_project.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.dao.SaleMapper;
import kr.or.dgit.sw_project.dao.SaleMapperImpl;
import kr.or.dgit.sw_project.dto.Sale;
import kr.or.dgit.sw_project.util.MyBatisSqlSessionFactory;
public class SaleService {
	public static final SaleService instance = new SaleService();
	private SaleService() {}
	public static SaleService getInstance(){
		return instance;
	}
	
	public List<Sale> selectSaleByAll() {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			SaleMapper saleMapper = new SaleMapperImpl(sqlSession);
			return saleMapper.selectSaleByAll();
		} 
	}
}
