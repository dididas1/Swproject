package kr.or.dgit.sw_project.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.dao.SoftwareMapper;
import kr.or.dgit.sw_project.dao.SoftwareMapperImpl;
import kr.or.dgit.sw_project.dto.Software;
import kr.or.dgit.sw_project.util.MyBatisSqlSessionFactory;
public class SoftwareService {
	public static final SoftwareService instance = new SoftwareService();
	private SoftwareService() {}
	public static SoftwareService getInstance(){
		return instance;
	}
	
	public List<Software> selectSoftwareByAll() {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			SoftwareMapper softwareMapper = new SoftwareMapperImpl(sqlSession);
			return softwareMapper.selectSoftwareByAll();
		} 
	}
	
	public List<Software> selectSoftwareByImg() {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			SoftwareMapper softwareMapper = new SoftwareMapperImpl(sqlSession);
			return softwareMapper.selectSoftwareByImg();
		} 
	}
	
	public int insertSoftwareItem(Map<String, Object> item) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			SoftwareMapper softwareMapper = new SoftwareMapperImpl(sqlSession);
			int res = softwareMapper.insertSoftwareItem(item);
			sqlSession.commit();
			return res;
		} 
	}
	
	public int updateSoftwareItem(Map<String, Object> item) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			SoftwareMapper softwareMapper = new SoftwareMapperImpl(sqlSession);
			int res = softwareMapper.updateSoftwareItem(item);
			sqlSession.commit();
			return res;
		} 
	}
	
	public int swCodeReset(Map<String, Object> item) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			SoftwareMapper softwareMapper = new SoftwareMapperImpl(sqlSession);
			int res = softwareMapper.swCodeReset(item);
			sqlSession.commit();
			return res;
		} 
	}
	
	public int deleteSoftwareItem(Software item) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			SoftwareMapper softwareMapper = new SoftwareMapperImpl(sqlSession);
			int res = softwareMapper.deleteSoftwareItem(item);
			sqlSession.commit();
			return res;
		} 
	}
	
	public int existSoftwareItem(Software software) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			SoftwareMapper softwareMapper = new SoftwareMapperImpl(sqlSession);
			int res = softwareMapper.existSoftwareItem(software);
			sqlSession.commit();
			return res;
		} 
	}
}
