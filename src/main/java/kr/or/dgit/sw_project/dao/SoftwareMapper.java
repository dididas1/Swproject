package kr.or.dgit.sw_project.dao;

import java.util.List;

import kr.or.dgit.sw_project.dto.Software;

public interface SoftwareMapper {
	//소프트웨어 전체목록
	List<Software> selectAllSw();
	//소프트웨어번호당 목록
	Software selectByNoSw(Software software);
	//소프트웨어 등록
	int insertRowSw(Software software);
	//소프트웨어 수정
	int updateRowSw(Software software);
	//판매중지된 소프트웨어
	int saleStopSw(Software software);
	
	List<Software> allList();

}
