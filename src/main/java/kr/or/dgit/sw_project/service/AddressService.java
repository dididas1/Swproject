package kr.or.dgit.sw_project.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.connection.AddressImpl;
import kr.or.dgit.sw_project.connection.MybatisSqlSessionFactory;
import kr.or.dgit.sw_project.dto.Address;

public class AddressService {
	public static final AddressService instence= new AddressService();
	
	
	public static AddressService getInstence() {
		return instence;
	}


	/*public List<Address> serchSido(Address address){
		try(SqlSession sqlsession = MybatisSqlSessionFactory.opensesstion();){
			AddressImpl postimpl= new AddressImpl(sqlsession);
			return postimpl.serchSido(address);
		}
	}*/
}
