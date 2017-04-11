package kr.or.dgit.sw_project.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.dto.ViewClientSale;
import kr.or.dgit.sw_project.dto.ViewOrderDateSale;

public class ViewOrderDateSaleMapperImpl implements ViewOrderDateSaleMapper{
	private SqlSession sqlSession;
	private static final Log log = LogFactory.getLog(ViewOrderDateSaleMapperImpl.class);
	private String nameSpace = "kr.or.dgit.sw_project.dao.ViewOrderDateSaleMapper.";
	
	public ViewOrderDateSaleMapperImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<ViewOrderDateSale> selectViewOrderDateSaleThisYear(Map<String, Object> param) {
		log.debug("selectViewOrderDateSaleThisYear()");
		return sqlSession.selectList(nameSpace + "selectViewOrderDateSaleThisYear",param);
	}

}
