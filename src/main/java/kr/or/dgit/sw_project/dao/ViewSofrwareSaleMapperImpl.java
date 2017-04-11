package kr.or.dgit.sw_project.dao;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.dto.ViewSofrwareSale;

public class ViewSofrwareSaleMapperImpl implements ViewSofrwareSaleMapper{
	private SqlSession sqlSession;
	private static final Log log = LogFactory.getLog(ViewSofrwareSaleMapperImpl.class);
	private String nameSpace = "kr.or.dgit.sw_project.dao.ViewSofrwareSaleMapper.";
	
	public ViewSofrwareSaleMapperImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<ViewSofrwareSale> selectViewSofrwareSaleAll() {
		log.debug("selectViewSofrwareSaleAll()");
		return sqlSession.selectList(nameSpace + "selectViewSofrwareSaleAll");
	}

	@Override
	public List<ViewSofrwareSale> selectViewsoftwareSaleBySwName(ViewSofrwareSale viewSofrwareSale) {
		log.debug("selectViewsoftwareSaleBySwName()");
		return sqlSession.selectList(nameSpace + "selectViewsoftwareSaleBySwName", viewSofrwareSale);
	}

}
