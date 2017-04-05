package kr.or.dgit.sw_project.dao;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.dto.Client;

public class ClientMapperImpl implements ClientMapper{
	private SqlSession sqlSession;
	private static final Log log = LogFactory.getLog(ClientMapperImpl.class);
	private String nameSpace = "kr.or.dgit.sw_project.dao.ClientMapper.";

	public ClientMapperImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<Client> selectClientByAll() {
		log.debug("selectClientByAll()");
		return sqlSession.selectList(nameSpace + "selectClientByAll");
	}

	@Override
	public Client selectByNoClnt(Client client) {
		log.debug("selectByNoClnt()");
		return sqlSession.selectOne(nameSpace + "selectByNoClnt",client);
	}

	@Override
	public int insetClntItem(Client client) {
		log.debug("insetClntItem()");
		return sqlSession.insert(nameSpace + "insetClntItem",client);
	}

	@Override
	public int updateClntItem(Client client) {
		log.debug("updateClntItem()");
		return sqlSession.update(nameSpace + "updateClntItem",client);
	}

	@Override
	public int existClntItem(Client client) {
		log.debug("existClntItem()");
		return sqlSession.update(nameSpace + "existClntItem",client);
	}
}
