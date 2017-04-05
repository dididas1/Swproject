package kr.or.dgit.sw_project.dao;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.dto.Software;

public class SoftwareMapperImpl implements SoftwareMapper{
	private SqlSession sqlSession;
	private static final Log log = LogFactory.getLog(SoftwareMapperImpl.class);
	private String nameSpace = "kr.or.dgit.sw_project.dao.SoftwareMapper.";

	public SoftwareMapperImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<Software> selectSoftwareByAll() {
		log.debug("selectSoftwareByAll()");
		return sqlSession.selectList(nameSpace + "selectSoftwareByAll");
	}
}
