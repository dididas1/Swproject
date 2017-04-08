package kr.or.dgit.sw_project.dao;

import java.util.List;

import kr.or.dgit.sw_project.dto.Software;

public interface SoftwareMapper {
	List<Software> selectSoftwareByAll();
	Software selectByNoSoftware(Software software);
}
