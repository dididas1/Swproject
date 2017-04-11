package kr.or.dgit.sw_project.dao;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.dto.ViewClientSale;

public class ViewClientSaleMapperImpl implements ViewClientSaleMapper {
	private SqlSession sqlSession;
	private static final Log log = LogFactory.getLog(ViewCategorySaleMapperImpl.class);
	private String nameSpace = "kr.or.dgit.sw_project.dao.ViewClientSaleMapper.";
	
	public ViewClientSaleMapperImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<ViewClientSale> selectViewClientSaleAll() {
		log.debug("selectViewClientSaleAll()");
		return sqlSession.selectList(nameSpace + "selectViewClientSaleAll");
	}

	@Override
	public List<ViewClientSale> selectViewClientSaleClntName(ViewClientSale viewClientSale) {
		log.debug("selectViewClientSaleClntName()");
		return sqlSession.selectList(nameSpace + "selectViewClientSaleClntName" , viewClientSale);
	}

}
