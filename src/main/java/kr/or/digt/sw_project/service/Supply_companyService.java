package kr.or.digt.sw_project.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.digt.sw_project.connection.MybatisSqlSessionFactory;
import kr.or.digt.sw_project.connection.Supply_companyMapperImpl;
import kr.or.digt.sw_project.dto.Supply_company;

public class Supply_companyService {
	
	
	public List<Supply_company> selectAllCompany(){
		try(SqlSession sqlsession = MybatisSqlSessionFactory.opensesstion()){
			Supply_companyMapperImpl comp = new Supply_companyMapperImpl(sqlsession);
			return comp.selectAllCompany();
		}

	}
}
