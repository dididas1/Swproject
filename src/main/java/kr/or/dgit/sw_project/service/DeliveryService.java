package kr.or.dgit.sw_project.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.dao.DeliveryMapper;
import kr.or.dgit.sw_project.dao.DeliveryMapperImpl;
import kr.or.dgit.sw_project.dao.SupplyCompMapper;
import kr.or.dgit.sw_project.dao.SupplyCompMapperImpl;
import kr.or.dgit.sw_project.dto.Delivery;
import kr.or.dgit.sw_project.dto.SupplyCompany;
import kr.or.dgit.sw_project.util.MyBatisSqlSessionFactory;
public class DeliveryService {
	public static final DeliveryService instance = new DeliveryService();
	private DeliveryService() {}
	public static DeliveryService getInstance(){
		return instance;
	}
	
	public List<Delivery> selectDeliveryByAll() {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			DeliveryMapper deliveryMapper = new DeliveryMapperImpl(sqlSession);
			return deliveryMapper.selectDeliveryByAll();
		} 
	}
	public int insertDeliveryItems(Delivery delivery){//db입력
		try(SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();){
			DeliveryMapper deliveryMapper = new DeliveryMapperImpl(sqlSession);
			int res = deliveryMapper.insertDeliveryItems(delivery);
			sqlSession.commit();
			return res;
		}
		
	}
	public int UpdateItems(Delivery delivery) {//db table값수정
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			DeliveryMapper deliveryMapper = new DeliveryMapperImpl(sqlSession);
			int res= deliveryMapper.UpdateItems(delivery);
			sqlSession.commit();
			return res;
		} 
	}
	public int existDeliveryItem(Delivery delivery) {//논리삭제 db에서 exist칼럼 true로 변경 
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			DeliveryMapper deliveryMapper = new DeliveryMapperImpl(sqlSession);
			int res= deliveryMapper.existDeliveryItem(delivery);
			sqlSession.commit();
			return res;
		} 
	}
	public String lastDeliveryCode() {//필요없어짐
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			DeliveryMapper deliveryMapper = new DeliveryMapperImpl(sqlSession);
			return deliveryMapper.lastDeliveryCode();
		} 
	}
	
	public Delivery getSuppyPrice(Delivery delivery) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			DeliveryMapper deliveryMapper = new DeliveryMapperImpl(sqlSession);
			return deliveryMapper.getSuppyPrice(delivery);
		} 
	}
	
}