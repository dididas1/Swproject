package kr.or.dgit.sw_project.dao;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.dto.SupplyCompany;

public class SupplyCompMapperImpl implements SupplyCompMapper{
	private SqlSession sqlSession;
	private static final Log log = LogFactory.getLog(SupplyCompMapperImpl.class);
	private String nameSpace = "kr.or.dgit.sw_project.dao.SupplyCompMapper.";
	
	public SupplyCompMapperImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<SupplyCompany> selectSupplyCompByAll() {
		log.debug("selectSupplyCompByAll()");
		return sqlSession.selectList(nameSpace + "selectSupplyCompByAll");
	}

	@Override
	public SupplyCompany selectCompByNo(SupplyCompany supplyCompany) {
		log.debug("selectCompByNo()");
		return sqlSession.selectOne(nameSpace + "selectCompByNo", supplyCompany);
	}

	@Override
	public int insertCompItem(SupplyCompany supplyCompany) {
		log.debug("insertCompItem()");
		return sqlSession.insert(nameSpace + "insertCompItem",supplyCompany);
	}

	@Override
	public int updateCompItem(SupplyCompany supplyCompany) {
		log.debug("updateCompItem()");
		return sqlSession.update(nameSpace + "updateCompItem",supplyCompany);
	}

	@Override
	public int existCompItem(SupplyCompany supplyCompany) {
		log.debug("existCompItem()");
		return sqlSession.update(nameSpace + "existCompItem",supplyCompany);
	}
}
