package kr.or.dgit.sw_project.dao;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.dto.ViewCategorySale;

public class ViewCategorySaleMapperImpl implements ViewCategorySaleMapper{
	private SqlSession sqlSession;
	private static final Log log = LogFactory.getLog(ViewCategorySaleMapperImpl.class);
	private String nameSpace = "kr.or.dgit.sw_project.dao.ViewCategorySaleMapper.";
	
	public ViewCategorySaleMapperImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	@Override
	public List<ViewCategorySale> selectViewCategoryAll() {
		log.debug("selectViewCategoryAll()");
		return sqlSession.selectList(nameSpace + "selectViewCategoryAll");
	}

	@Override
	public ViewCategorySale selectViewCategoryByNo(ViewCategorySale viewCategorySale) {
		log.debug("selectViewCategoryByNo()");
		return sqlSession.selectOne(nameSpace + "selectViewCategoryByNo",viewCategorySale);
	}

}
