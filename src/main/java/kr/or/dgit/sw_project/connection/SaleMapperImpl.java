package kr.or.dgit.sw_project.connection;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.dao.SaleMapper;
import kr.or.dgit.sw_project.dto.Address;
import kr.or.dgit.sw_project.dto.Sale;

public class SaleMapperImpl implements SaleMapper {
	private static final Log log = LogFactory.getLog(Address.class);
	private SqlSession sqlsession;
	private String namespace ="kr.or.dgit.sw_project.dao.SaleMapper.";
	
	public SaleMapperImpl(SqlSession sqlsession) {
		super();
		this.sqlsession = sqlsession;
	}

	@Override
	public List<Sale> allSelectSale() {
		log.debug("serchSido()");
		return sqlsession.selectList(namespace + "allSelectSale");
	}

	@Override
	public int insertSale(Sale sale) {
		log.debug("insertSale()");
		return sqlsession.insert(namespace + "insertSale",sale);
	}
	
	@Override
	public int deleteSale(Sale sale) {
		log.debug("deleteSale()");
		return sqlsession.update(namespace + "deleteSale",sale);
	}

	@Override
	public Sale CodeSelectSaleItem(Sale sale) {
		log.debug("CodeSelectSaleItem()");
		return sqlsession.selectOne(namespace + "CodeSelectSaleItem",sale);
	}
	
	@Override
	public List<Sale> clientSoftwareReport() {
		log.debug("clientSoftwareReport()");
		return sqlsession.selectList(namespace + "clientSoftwareReport");
	}

	@Override
	public List<Sale> softwareSaleReport() {
		log.debug("softwareSaleReport()");
		return sqlsession.selectList(namespace + "softwareSaleReport");
	}

	@Override
	public List<Sale> categorySaleReport() {
		log.debug("categorySaleReport()");
		return sqlsession.selectList(namespace + "categorySaleReport");
	}

	@Override
	public List<Sale> viewBillList() {
		log.debug("viewBillList()");
		return sqlsession.selectList(namespace + "viewBillList");
	}

	@Override
	public List<Sale> daySoftwareSaleReport(Map<String, String> param) {
		log.debug("daySoftwareSaleReport()");
		return sqlsession.selectList(namespace + "daySoftwareSaleReport",param);
	}

}
