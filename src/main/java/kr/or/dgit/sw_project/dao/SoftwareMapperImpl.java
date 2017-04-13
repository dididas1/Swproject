package kr.or.dgit.sw_project.dao;

import java.util.List;
import java.util.Map;

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

	@Override
	public int insertSoftwareItem(Map<String, Object> item) {
		log.debug("insertSoftwareItem()");
		return sqlSession.insert(nameSpace + "insertSoftwareItem", item);
	}

	@Override
	public int updateSoftwareItem(Map<String, Object> item) {
		log.debug("updateSoftwareItem()");
		return sqlSession.update(nameSpace + "updateSoftwareItem", item);
	}
	
	@Override
	public int swCodeReset(Map<String, Object> item) {
		log.debug("swCodeReset()");
		return sqlSession.update(nameSpace + "swCodeReset", item);
	}

	@Override
	public int deleteSoftwareItem(Software item) {
		log.debug("deleteSoftwareItem()");
		return sqlSession.delete(nameSpace + "deleteSoftwareItem", item);
	}

	@Override
	public Software selectByNoSoftware(Software software) {
		log.debug("selectByNoSoftware()");
		return sqlSession.selectOne(nameSpace + "selectByNoSoftware", software);
	}

	@Override
	public int existSoftwareItem(Software software) {
		log.debug("ExistSoftwareItem()");
		return sqlSession.update(nameSpace + "ExistSoftwareItem", software);
	}
	
	@Override
	public List<Software> selectSoftwareByImg() {
		log.debug("selectSoftwareByImg()");
		return sqlSession.selectList(nameSpace + "selectSoftwareByImg");
	}

}
