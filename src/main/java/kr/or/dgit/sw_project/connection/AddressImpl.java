package kr.or.dgit.sw_project.connection;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.dao.AddressMapper;
import kr.or.dgit.sw_project.dto.Address;
import kr.or.dgit.sw_project.dto.Client;

public class AddressImpl implements AddressMapper{
	private static final Log log = LogFactory.getLog(Address.class);
	private SqlSession sqlsession;
	private String namespace ="kr.or.dgit.sw_project.dao.AddressMapper.";
	
	public AddressImpl(SqlSession sqlsession) {
		super();
		this.sqlsession = sqlsession;
	}

	@Override
	public List<Client> selectAllClntAddr() {
		log.debug("selectAllClntAddr()");
		return sqlsession.selectList(namespace + "selectAllClntAddr");
	}

	@Override
	public Client selectByNoClntAddr(Client clntAddr) {
		log.debug("selectByNoClntAddr()");
		return sqlsession.selectOne(namespace + "selectByNoClntAddr", clntAddr);
	}
	


	@Override
	public int deleteRowClntAddr(Client clntAddr) {
		log.debug("deleteRowClntAddr()");
		return sqlsession.delete(namespace + "deleteRowClntAddr", clntAddr);
	}

	@Override
	public int insertRowClntAddr(Map<String, Object> clntAddr) {
		log.debug("insertRowClntAddr()");
		return sqlsession.insert(namespace + "insertRowClntAddr", clntAddr);
	}

	@Override
	public int updateRowClntAddr(Map<String, Object> clntAddr) {
		log.debug("updateRowClntAddr()");
		return sqlsession.update(namespace + "updateRowClntAddr", clntAddr);
	}

}
