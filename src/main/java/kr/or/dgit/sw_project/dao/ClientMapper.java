package kr.or.dgit.sw_project.dao;

import java.util.List;

import kr.or.dgit.sw_project.dto.Client;

public interface ClientMapper {
	//거래회사 전체목록
	List<Client> selectAllClnt();
	//회사번호당 목록
	Client selectByNoClnt(Client client);
	//회사를 등록
	int insertRowClnt(Client client);
	//회사를 수정
	int updateRowClnt(Client client);
	//탈퇴한 회사
	int existClnt(Client client);
}
