package kr.or.dgit.sw_project.service;

import java.util.List;

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
}
