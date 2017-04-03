package kr.or.dgit.sw_project.dao;

import java.util.List;
import java.util.Map;

import kr.or.dgit.sw_project.dto.Address;
import kr.or.dgit.sw_project.dto.Client;

public interface AddressMapper {
	//거래회사주소 전체목록
	List<Client> selectAllClntAddr();
	//거래회사번호당 주소목록
	Client selectByNoClntAddr(Client clntAddr);
	//검색한 시도를 주소로 가지고있는 거래회사번호목록
	//거래회사주소 삭제
	int deleteRowClntAddr(Client clntAddr);
	//거래회사주소 추가
	int insertRowClntAddr(Map<String, Object> clntAddr);
	//거래회사주소 수정
	int updateRowClntAddr(Map<String, Object> clntAddr);
}
