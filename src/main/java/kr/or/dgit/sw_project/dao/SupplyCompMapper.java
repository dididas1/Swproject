package kr.or.dgit.sw_project.dao;

import java.util.List;

import kr.or.dgit.sw_project.dto.SupplyCompany;

public interface SupplyCompMapper {
	List<SupplyCompany> selectSupplyCompByAll();
	SupplyCompany selectCompByNo(SupplyCompany supplyCompany);
	int insertCompItem (SupplyCompany supplyCompany);
	int updateCompItem (SupplyCompany supplyCompany);
	int existCompItem (SupplyCompany supplyCompany);
}
