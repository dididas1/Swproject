package kr.or.dgit.sw_project.connection;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.dao.SoftwareMapper;
import kr.or.dgit.sw_project.dto.Address;
import kr.or.dgit.sw_project.dto.Software;

public class SoftwareMapperImpl implements SoftwareMapper {
	private static final Log log = LogFactory.getLog(Address.class);
	private SqlSession sqlsession;
	private String namespace ="kr.or.dgit.sw_project.dao.SoftwareMapper.";
	
	public SoftwareMapperImpl(SqlSession sqlsession) {
		super();
		this.sqlsession = sqlsession;
	}

	

}
