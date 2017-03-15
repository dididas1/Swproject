package kr.or.digt.sw_project.connection;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.digt.sw_project.dao.Supply_companyMapper;
import kr.or.digt.sw_project.dto.Supply_company;

public class Supply_companyMapperImpl implements Supply_companyMapper {
	private static final Log log = LogFactory.getLog(Supply_companyMapperImpl.class);
	private SqlSession sqlsession;
	private String namespace ="kr.or.digt.sw_project.dao.Supply_companyMapper";
	
	public Supply_companyMapperImpl(SqlSession sqlsession) {
		super();
		this.sqlsession = sqlsession;
	}

	@Override
	public List<Supply_company> selectAllCompany() {
			log.debug("searchCourse()");
			return sqlsession.selectList(namespace +".selectAllCompany");
		}
	}



