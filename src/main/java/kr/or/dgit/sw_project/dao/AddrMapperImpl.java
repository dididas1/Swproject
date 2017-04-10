package kr.or.dgit.sw_project.dao;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.dto.Address;

public class AddrMapperImpl implements AddrMapper{
	private SqlSession sqlSession;
	private static final Log log = LogFactory.getLog(AddrMapperImpl.class);
	private String nameSpace = "kr.or.dgit.sw_project.dao.AddrMapper.";
	
	public AddrMapperImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<Address> searchSido(Address address) {
		log.debug("searchSido()");
		return sqlSession.selectList(nameSpace + "searchSido", address);
	}

	@Override
	public List<Address> selectSigungu(Address address) {
		log.debug("selectSigungu()");
		return sqlSession.selectList(nameSpace + "selectSigungu",address);
	}
}
