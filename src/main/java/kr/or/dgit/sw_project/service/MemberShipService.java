package kr.or.dgit.sw_project.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.dao.MemberShipMapper;
import kr.or.dgit.sw_project.dao.MemberShipMapperImpl;
import kr.or.dgit.sw_project.dto.Members;
import kr.or.dgit.sw_project.util.MyBatisSqlSessionFactory;
public class MemberShipService {
	public static final MemberShipService instance = new MemberShipService();
	private MemberShipService() {}
	public static MemberShipService getInstance(){
		return instance;
	}
	
	public List<Members> selectMembersByAll() {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			MemberShipMapper MemberShipMapper = new MemberShipMapperImpl(sqlSession);
			return MemberShipMapper.selectMembersByAll();
		} 
	}
	
	public Members selectMembersByID(Members members) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			MemberShipMapper MemberShipMapper = new MemberShipMapperImpl(sqlSession);
			return MemberShipMapper.selectMembersByID(members);
		} 
	}

	public int insertMembersItem(Members members) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			MemberShipMapper MemberShipMapper = new MemberShipMapperImpl(sqlSession);
			int res= MemberShipMapper.insetMembersItem(members);
			sqlSession.commit();
			return res;
		} 
	}

	public int updateMembersItem(Members members) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			MemberShipMapper MemberShipMapper = new MemberShipMapperImpl(sqlSession);
			int res= MemberShipMapper.updateMembersItem(members);
			sqlSession.commit();
			return res;
		} 
	}

	public int existMembersItem(Members members) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			MemberShipMapper MemberShipMapper = new MemberShipMapperImpl(sqlSession);
			int res= MemberShipMapper.existMembersItem(members);
			sqlSession.commit();
			return res;
		} 
	}
	
	public Members selectMembersForLogIn(Members members) {
		try (SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			MemberShipMapper MemberShipMapper = new MemberShipMapperImpl(sqlSession);
			return MemberShipMapper.selectMembersForLogIn(members);
		} 
	}
}
