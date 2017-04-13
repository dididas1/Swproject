package kr.or.dgit.sw_project.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.dao.JoinFromSoftwareMapper;
import kr.or.dgit.sw_project.dao.JoinFromSoftwareMapperImpl;
import kr.or.dgit.sw_project.dto.JoinFromSoftware;
import kr.or.dgit.sw_project.util.MyBatisSqlSessionFactory;
public class JoinFromSoftwareService {
	public static final JoinFromSoftwareService instance = new JoinFromSoftwareService();
	private JoinFromSoftwareService() {}
	public static JoinFromSoftwareService getInstance(){
		return instance;
	}
	
	public List<JoinFromSoftware> selectJoinFromSoftwareByAll() {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			JoinFromSoftwareMapper joinFromSoftwareMapper = new JoinFromSoftwareMapperImpl(sqlSession);
			return joinFromSoftwareMapper.selectJoinFromSoftwareByAll();
		} 
	}
	
	public List<JoinFromSoftware> selectJoinFromSoftwareByAllOrderByClntName() {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			JoinFromSoftwareMapper joinFromSoftwareMapper = new JoinFromSoftwareMapperImpl(sqlSession);
			return joinFromSoftwareMapper.selectJoinFromSoftwareByAllOrderByClntName();
		} 
	}
	
	public JoinFromSoftware selectJoinFromSoftwareByCode(String swCode) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			JoinFromSoftwareMapper joinFromSoftwareMapper = new JoinFromSoftwareMapperImpl(sqlSession);
			return joinFromSoftwareMapper.selectJoinFromSoftwareByCode(swCode);
		} 
	}
}
