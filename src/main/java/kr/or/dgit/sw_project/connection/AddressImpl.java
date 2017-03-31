package kr.or.dgit.sw_project.connection;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.dao.AddressMapper;
import kr.or.dgit.sw_project.dto.Address;

public class AddressImpl implements AddressMapper{
	private static final Log log = LogFactory.getLog(Address.class);
	private SqlSession sqlsession;
	private String namespace ="kr.or.digt.post.dao.PostMapper.";
	
	public AddressImpl(SqlSession sqlsession) {
		super();
		this.sqlsession = sqlsession;
	}

	@Override
	public List<Address> serchSido(Address address) {
		log.debug("serchSido()");
		return sqlsession.selectList(namespace + "serchSido", address);
		
	}

}
