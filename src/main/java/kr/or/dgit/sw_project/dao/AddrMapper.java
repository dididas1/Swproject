package kr.or.dgit.sw_project.dao;

import java.util.List;

import kr.or.dgit.sw_project.dto.Address;

public interface AddrMapper {
	List<Address> searchSido(Address address);
	List<Address> selectSigungu(Address address);
	
}
