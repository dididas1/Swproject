package kr.or.dgit.sw_project.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.dao.AddrMapper;
import kr.or.dgit.sw_project.dao.AddrMapperImpl;
import kr.or.dgit.sw_project.dto.Address;
import kr.or.dgit.sw_project.util.MyBatisSqlSessionFactory;

public class AddrService {
	//private AddrService() {}
	public static final AddrService instance = new AddrService();
	public static AddrService getInstance() {
		return instance;
	}

	public List<Address> searchSido(Address address){
		try(SqlSession sqlsession = MyBatisSqlSessionFactory.openSession();){
			AddrMapper addrMapper = new AddrMapperImpl(sqlsession);
			return addrMapper.searchSido(address);
		}
	}
	
	public List<Address> selectSigungu(Address address) {
		try(SqlSession sqlsession = MyBatisSqlSessionFactory.openSession();){
			AddrMapper addrMapper = new AddrMapperImpl(sqlsession);
			return addrMapper.selectSigungu(address);
		}
	}
}