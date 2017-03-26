package kr.or.digt.sw_project.connection;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.digt.sw_project.dao.SupplyCompanyMapper;
import kr.or.digt.sw_project.dto.SupplyCompany;

public class SupplyCompanyMapperImpl implements SupplyCompanyMapper {
	private static final Log log = LogFactory.getLog(SupplyCompanyMapperImpl.class);
	private SqlSession sqlsession;
	private String namespace ="kr.or.digt.sw_project.dao.Supply_companyMapper";
	
	public SupplyCompanyMapperImpl(SqlSession sqlsession) {
		super();
		this.sqlsession = sqlsession;
	}

	@Override
	public List<SupplyCompany> selectAllCompany() {
			log.debug("searchCourse()");
			return sqlsession.selectList(namespace +".selectAllCompany");
		}
	}



