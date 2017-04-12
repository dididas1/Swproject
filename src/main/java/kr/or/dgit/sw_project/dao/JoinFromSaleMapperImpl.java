package kr.or.dgit.sw_project.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.dto.JoinFromSale;

public class JoinFromSaleMapperImpl implements JoinFromSaleMapper{
	private SqlSession sqlSession;
	private static final Log log = LogFactory.getLog(JoinFromSaleMapperImpl.class);
	private String nameSpace = "kr.or.dgit.sw_project.dao.JoinFromSaleMapper.";
	
	public JoinFromSaleMapperImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<JoinFromSale> selectJoinFromSaleByAll() {
		log.debug("selectJoinFromSaleByAll()");
		return sqlSession.selectList(nameSpace + "selectJoinFromSaleByAll");
	}

	@Override
	public List<JoinFromSale> selectJoinFromSaleByYear(Map<String, Object> date) {
		log.debug("selectJoinFromSaleByYear()");
		return sqlSession.selectList(nameSpace + "selectJoinFromSaleByYear", date);
	}

	@Override
	public List<JoinFromSale> selectJoinFromSaleByAllOrderByOrderDate() {
		log.debug("selectJoinFromSaleByAllOrderByOrderDate()");
		return sqlSession.selectList(nameSpace + "selectJoinFromSaleByAllOrderByOrderDate");
	}
}
