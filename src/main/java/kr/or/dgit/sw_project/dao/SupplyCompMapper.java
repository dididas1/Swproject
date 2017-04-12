package kr.or.dgit.sw_project.dao;

import java.util.List;

import kr.or.dgit.sw_project.dto.SupplyCompany;

public interface SupplyCompMapper {
	List<SupplyCompany> selectSupplyCompByAll();
	SupplyCompany selectCompByNo(SupplyCompany supplyCompany);//code통한 select
	int insertCompItem (SupplyCompany supplyCompany);//db table입력
	int updateCompItem (SupplyCompany supplyCompany);//db table 값수정
	int existCompItem (SupplyCompany supplyCompany);//논리삭제 db에서 exist칼럼 true로 변경 
}
