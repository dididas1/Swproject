package kr.or.dgit.sw_project.dao;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.dto.SaleDetail;

public class SaleDetailMapperImpl implements SaleDetailMapper{
	private SqlSession sqlSession;
	private static final Log log = LogFactory.getLog(SaleDetailMapperImpl.class);
	private String nameSpace = "kr.or.dgit.sw_project.dao.SaleDetailMapper.";

	public SaleDetailMapperImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<SaleDetail> selectSaleDetailByAll() {
		log.debug("selectSaleDetailByAll()");
		return sqlSession.selectList(nameSpace + "selectSaleDetailByAll");
	}
}
