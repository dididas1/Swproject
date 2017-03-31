package kr.or.dgit.sw_project.dao;

import java.util.List;

import kr.or.dgit.sw_project.dto.Address;

public interface AddressMapper {
	List<Address> serchSido(Address param);
}
