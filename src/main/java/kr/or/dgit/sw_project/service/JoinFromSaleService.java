package kr.or.dgit.sw_project.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.dao.JoinFromSaleMapper;
import kr.or.dgit.sw_project.dao.JoinFromSaleMapperImpl;
import kr.or.dgit.sw_project.dto.JoinFromSale;
import kr.or.dgit.sw_project.util.MyBatisSqlSessionFactory;
public class JoinFromSaleService {
	public static final JoinFromSaleService instance = new JoinFromSaleService();
	private JoinFromSaleService() {}
	public static JoinFromSaleService getInstance(){
		return instance;
	}
	
	public List<JoinFromSale> selectJoinFromSaleByAll() {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			JoinFromSaleMapper joinFromSaleMapper = new JoinFromSaleMapperImpl(sqlSession);
			return joinFromSaleMapper.selectJoinFromSaleByAll();
		} 
	}
	
	public List<JoinFromSale> selectJoinFromSaleByYear(Map<String, Object> date) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			JoinFromSaleMapper joinFromSaleMapper = new JoinFromSaleMapperImpl(sqlSession);
			return joinFromSaleMapper.selectJoinFromSaleByYear(date);
		} 
	}
	public List<JoinFromSale> selectJoinFromSaleByAllOrderByOrderDate() {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			JoinFromSaleMapper joinFromSaleMapper = new JoinFromSaleMapperImpl(sqlSession);
			return joinFromSaleMapper.selectJoinFromSaleByAllOrderByOrderDate();
		} 
	}
}
