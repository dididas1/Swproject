package kr.or.dgit.sw_project.connection;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.dao.JoinDataMapper;
import kr.or.dgit.sw_project.dto.Category;
import kr.or.dgit.sw_project.dto.Client;
import kr.or.dgit.sw_project.dto.JoinData;
import kr.or.dgit.sw_project.dto.Software;

public class JoinDataMapperImpl implements JoinDataMapper {
	private static final Log log = LogFactory.getLog(JoinData.class);
	private SqlSession sqlsession;
	private String namespace ="kr.or.dgit.sw_project.dao.JoinDataMapper.";
	
	public JoinDataMapperImpl(SqlSession sqlsession) {
		super();
		this.sqlsession = sqlsession;
	}
	// 소프트별판매현황
	@Override
	public List<JoinData> softwareSaleReport(Software software) {
		log.debug("softwareSaleReport()");
		return sqlsession.selectList(namespace + "softwareSaleReport",software);
	}
	//고객사별 판매현황
	@Override
	public List<JoinData> clinetSoftReport(Client clinet) {
		log.debug("clinetSoftReport()");
		return sqlsession.selectList(namespace + "clinetSoftReport",clinet);
	}
	
	//기간별판매현황
	@Override
	public List<JoinData> daySoftwareSaleReport(Date date) {
		log.debug("daySoftwareSaleReport()");
		return sqlsession.selectList(namespace + "daySoftwareSaleReport",date);
	}
	
	//카테고리별 판매현황
	@Override
	public List<JoinData> categorySaleReport(Category category) {
		log.debug("categorySaleReport()");
		return sqlsession.selectList(namespace + "categorySaleReport",category);
	}
	
	//거래명세서
	@Override
	public List<JoinData> viewBillList() {
		log.debug("viewBillList()");
		return sqlsession.selectList(namespace + "viewBillList");
	}
	
	//그래프출력
	@Override
	public List<JoinData> clinetsaleGraph() {
		log.debug("clinetsaleGraph()");
		return sqlsession.selectList(namespace + "clinetsaleGraph");
	}

	
}
