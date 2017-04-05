package kr.or.dgit.sw_project.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.dao.SaleDetailMapper;
import kr.or.dgit.sw_project.dao.SaleDetailMapperImpl;
import kr.or.dgit.sw_project.dto.SaleDetail;
import kr.or.dgit.sw_project.util.MyBatisSqlSessionFactory;
public class SaleDetailService {
	public static final SaleDetailService instance = new SaleDetailService();
	private SaleDetailService() {}
	public static SaleDetailService getInstance(){
		return instance;
	}
	
	public List<SaleDetail> selectSaleDetailByAll() {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			SaleDetailMapper saleDetailMapper = new SaleDetailMapperImpl(sqlSession);
			return saleDetailMapper.selectSaleDetailByAll();
		} 
	}
}
