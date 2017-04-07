package kr.or.dgit.sw_project.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.dto.Category;

public class CategoryMapperImpl implements CategoryMapper{
	private SqlSession sqlSession;
	private static final Log log = LogFactory.getLog(CategoryMapperImpl.class);
	private String nameSpace = "kr.or.dgit.sw_project.dao.CategoryMapper.";

	public CategoryMapperImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<Category> selectCategoryByAll() {
		log.debug("selectCategoryByAll()");
		return sqlSession.selectList(nameSpace + "selectCategoryByAll");
	}

	@Override
	public Category selectCategoryByNo(Category category) {
		log.debug("selectCategoryByNo()");
		return sqlSession.selectOne(nameSpace + "selectCategoryByNo",category);
	}

	@Override
	public int insertCategoryItem(Category category) {
		log.debug("insertCategoryItem()");
		return sqlSession.insert(nameSpace + "insertCategoryItem",category);
	}

	@Override
	public int updateCategoryItem(Map<String, Object> category) {
		log.debug("updateCategoryItem()");
		return sqlSession.update(nameSpace + "updateCategoryItem",category);
	}

	@Override
	public int deleteCategoryItem(Category category) {
		log.debug("deleteCategoryItem()");
		return sqlSession.update(nameSpace + "deleteCategoryItem",category);
	}
}
