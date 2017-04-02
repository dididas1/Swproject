package kr.or.dgit.sw_project.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.connection.JoinDataMapperImpl;
import kr.or.dgit.sw_project.connection.MybatisSqlSessionFactory;
import kr.or.dgit.sw_project.dto.Category;
import kr.or.dgit.sw_project.dto.Client;
import kr.or.dgit.sw_project.dto.JoinData;
import kr.or.dgit.sw_project.dto.Software;

public class JoinDataService {
	//소프트별 판매현황조회
	public List<JoinData> softwareFind(Software software){
		try(SqlSession sqlsession = MybatisSqlSessionFactory.opensesstion();){
			JoinDataMapperImpl joinImpl= new JoinDataMapperImpl(sqlsession);
			return joinImpl.softwareSaleReport(software);
		}
	}
	// 고객사별 판매현황조회
	public List<JoinData> clinetSoftFind(Client clinet){
		try(SqlSession sqlsession = MybatisSqlSessionFactory.opensesstion();){
			JoinDataMapperImpl joinImpl= new JoinDataMapperImpl(sqlsession);
			return joinImpl.clinetSoftReport(clinet);
		}
	}
	// 기간별 판매현황조회 -- 수정필요 x~x안됨
	public List<JoinData> daySoftwareSaleReport(Date date){
		try(SqlSession sqlsession = MybatisSqlSessionFactory.opensesstion();){
			JoinDataMapperImpl joinImpl= new JoinDataMapperImpl(sqlsession);
			return joinImpl.daySoftwareSaleReport(date);
		}
	}

	//분류별 판매현황조회
	public List<JoinData> categorySaleReport(Category category){
		try(SqlSession sqlsession = MybatisSqlSessionFactory.opensesstion();){
			JoinDataMapperImpl joinImpl= new JoinDataMapperImpl(sqlsession);
			return joinImpl.categorySaleReport(category);

		}

	}
	
	//거래명세서
	public List<JoinData> viewBillList(){
		try(SqlSession sqlsession = MybatisSqlSessionFactory.opensesstion();){
			JoinDataMapperImpl joinImpl= new JoinDataMapperImpl(sqlsession);
			return joinImpl.viewBillList();

		}
	}
	
	//그래프출력
	public List<JoinData> clinetsaleGraph(){
		try(SqlSession sqlsession = MybatisSqlSessionFactory.opensesstion();){
			JoinDataMapperImpl joinImpl= new JoinDataMapperImpl(sqlsession);
			return joinImpl.clinetsaleGraph();
	}
	}
	
}
