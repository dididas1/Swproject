package kr.or.dgit.sw_project.dao;

import java.util.List;

import kr.or.dgit.sw_project.dto.JoinFromSoftware;

public interface JoinFromSoftwareMapper {
	List<JoinFromSoftware> selectJoinFromSoftwareByAll();
	List<JoinFromSoftware> selectJoinFromSoftwareByAllOrderByClntName();
	JoinFromSoftware selectJoinFromSoftwareByCode(String swCode);
}
