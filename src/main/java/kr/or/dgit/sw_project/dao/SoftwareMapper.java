package kr.or.dgit.sw_project.dao;

import java.util.List;
import java.util.Map;

import kr.or.dgit.sw_project.dto.Software;

public interface SoftwareMapper {
	List<Software> selectSoftwareByAll();
	Software selectByNoSoftware(Software software);
	int insertSoftwareItem(Map<String, Object> item);
	int updateSoftwareItem(Map<String, Object> item);
	int swCodeReset(Map<String, Object> item);
	int deleteSoftwareItem(Software item);
	int existSoftwareItem (Software item);
	List<Software> selectSoftwareByImg();
	
}
