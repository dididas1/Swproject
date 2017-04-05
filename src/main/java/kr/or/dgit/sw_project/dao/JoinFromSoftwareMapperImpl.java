package kr.or.dgit.sw_project.dao;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.dto.JoinFromSoftware;

public class JoinFromSoftwareMapperImpl implements JoinFromSoftwareMapper{
	private SqlSession sqlSession;
	private static final Log log = LogFactory.getLog(JoinFromSoftwareMapperImpl.class);
	private String nameSpace = "kr.or.dgit.sw_project.dao.JoinFromSoftwareMapper.";
	
	public JoinFromSoftwareMapperImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<JoinFromSoftware> selectJoinFromSoftwareByAll() {
		log.debug("selectJoinFromSoftwareByAll()");
		return sqlSession.selectList(nameSpace + "selectJoinFromSoftwareByAll");
	}
}
