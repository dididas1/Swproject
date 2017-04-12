package kr.or.dgit.sw_project.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.dao.SupplyCompMapper;
import kr.or.dgit.sw_project.dao.SupplyCompMapperImpl;
import kr.or.dgit.sw_project.dto.SupplyCompany;
import kr.or.dgit.sw_project.util.MyBatisSqlSessionFactory;
public class SupplyCompService {
	public static final SupplyCompService instance = new SupplyCompService();
	public SupplyCompService() {}
	public static SupplyCompService getInstance(){
		return instance;
	}
	
	public List<SupplyCompany> selectSupplyCompByAll() {//supplyCompany 테이블 모든값
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			SupplyCompMapper supplyCompMapper = new SupplyCompMapperImpl(sqlSession);
			return supplyCompMapper.selectSupplyCompByAll();
		} 
	}
	

	public SupplyCompany selectCompByNo(SupplyCompany supplyCompany) {//code통해 검색
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			SupplyCompMapper supplyCompMapper = new SupplyCompMapperImpl(sqlSession);
			return supplyCompMapper.selectCompByNo(supplyCompany);
		} 
	}

	public int insertCompItem(SupplyCompany supplyCompany) {//db table에 입력
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			SupplyCompMapper supplyCompMapper = new SupplyCompMapperImpl(sqlSession);
			int res= supplyCompMapper.insertCompItem(supplyCompany);
			sqlSession.commit();
			return res;
		} 
	}

	public int updateCompItem(SupplyCompany supplyCompany) {//db table값수정
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			SupplyCompMapper supplyCompMapper = new SupplyCompMapperImpl(sqlSession);
			int res= supplyCompMapper.updateCompItem(supplyCompany);
			sqlSession.commit();
			return res;
		} 
	}

	public int existCompItem(SupplyCompany supplyCompany) {//논리삭제 db에서 exist칼럼 true로 변경 
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			SupplyCompMapper supplyCompMapper = new SupplyCompMapperImpl(sqlSession);
			int res= supplyCompMapper.existCompItem(supplyCompany);
			sqlSession.commit();
			return res;
		} 
	}
}