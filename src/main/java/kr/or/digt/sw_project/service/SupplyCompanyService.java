package kr.or.digt.sw_project.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.digt.sw_project.connection.MybatisSqlSessionFactory;
import kr.or.digt.sw_project.connection.SupplyCompanyMapperImpl;
import kr.or.digt.sw_project.dto.SupplyCompany;

public class SupplyCompanyService {
	
	
	public List<SupplyCompany> selectAllCompany(){
		try(SqlSession sqlsession = MybatisSqlSessionFactory.opensesstion()){
			SupplyCompanyMapperImpl comp = new SupplyCompanyMapperImpl(sqlsession);
			return comp.selectAllCompany();
		}

	}
}
