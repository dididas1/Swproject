package kr.or.dgit.sw_project.dao;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.dto.Members;

public class MemberShipMapperImpl implements MemberShipMapper{
	private SqlSession sqlSession;
	private static final Log log = LogFactory.getLog(MemberShipMapperImpl.class);
	private String nameSpace = "kr.or.dgit.sw_project.dao.MemberShipMapper.";

	public MemberShipMapperImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<Members> selectMembersByAll() {
		log.debug("selectMembersByAll()");
		return sqlSession.selectList(nameSpace + "selectMembersByAll");
	}

	@Override
	public Members selectMembersByID(Members members) {
		log.debug("selectMembersByID()");
		return sqlSession.selectOne(nameSpace + "selectMembersByID",members);
	}

	@Override
	public int insetMembersItem(Members members) {
		log.debug("insetMembersItem()");
		return sqlSession.insert(nameSpace + "insetMembersItem",members);
	}

	@Override
	public int updateMembersItem(Members members) {
		log.debug("updateMembersItem()");
		return sqlSession.update(nameSpace + "updateMembersItem",members);
	}

	@Override
	public int existMembersItem(Members members) {
		log.debug("existMembersItem()");
		return sqlSession.update(nameSpace + "existMembersItem",members);
	}

	@Override
	public Members selectMembersForLogIn(Members members) {
		log.debug("selectMembersForLogIn()");
		return sqlSession.selectOne(nameSpace + "selectMembersForLogIn",members);
	}
}
