package kr.or.dgit.sw_project.connection;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.connection.CategoryMapperImpl;
import kr.or.dgit.sw_project.dto.Category;
import kr.or.dgit.sw_project.dao.CategoryMapper;

public class CategoryMapperImpl implements CategoryMapper {
	private static final Log log = LogFactory.getLog(CategoryMapperImpl.class);
	private SqlSession sqlsession;
	private String namespace ="kr.or.dgit.sw_project.dao.CategoryMapper.";
	
	public CategoryMapperImpl(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}

	@Override
	public List<Category> selectAllCategory() {
		log.debug("selectAllCategory()");
		return sqlsession.selectList(namespace +"selectAllCategory");
	}

	@Override
	public Category selectByNoCategory(Category category) {
		log.debug("selectByNoCategory()");
		return sqlsession.selectOne(namespace +"selectByNoCategory", category);
	}

	@Override
	public int insertRowCategory(Category category) {
		log.debug("insertRowCategory()");
		return sqlsession.update(namespace +"insertRowCategory", category);
	}
	
	@Override
	public int updateRowCategory(Map<String, String> category) {
		log.debug("updateRowCategory()");
		return sqlsession.update(namespace +"updateRowCategory", category);
	}

	@Override
	public int deleteRowCategory(Category category) {
		log.debug("deleteRowCategory()");
		return sqlsession.delete(namespace +"deleteRowCategory", category);
	}
}
