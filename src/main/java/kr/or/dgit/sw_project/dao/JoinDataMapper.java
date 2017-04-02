package kr.or.dgit.sw_project.dao;

import java.util.Date;
import java.util.List;

import kr.or.dgit.sw_project.dto.Category;
import kr.or.dgit.sw_project.dto.Client;
import kr.or.dgit.sw_project.dto.JoinData;
import kr.or.dgit.sw_project.dto.Software;

public interface JoinDataMapper {
	List<JoinData> softwareSaleReport(Software software);
	List<JoinData> clinetSoftReport(Client clinet);
	List<JoinData> daySoftwareSaleReport(Date date);
	List<JoinData> categorySaleReport(Category category);
	List<JoinData> viewBillList();
	List<JoinData> clinetsaleGraph();
}
