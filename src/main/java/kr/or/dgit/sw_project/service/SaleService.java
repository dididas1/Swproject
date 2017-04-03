package kr.or.dgit.sw_project.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.connection.AddressImpl;
import kr.or.dgit.sw_project.connection.MybatisSqlSessionFactory;
import kr.or.dgit.sw_project.connection.SaleMapperImpl;
import kr.or.dgit.sw_project.dto.Address;
import kr.or.dgit.sw_project.dto.Sale;

public class SaleService {
	private static final SaleService instence= new SaleService();

	public static SaleService getInstence() {
		return instence;
	}
	
	public List<Sale> daySoftwareSaleReport(Map<String, String> param) {
		try(SqlSession sqlsession = MybatisSqlSessionFactory.opensesstion();){
			SaleMapperImpl saleimpl= new SaleMapperImpl(sqlsession);
			return saleimpl.daySoftwareSaleReport(param);
		}
		
	}
	
	public List<Sale> viewBillList(){
		try(SqlSession sqlsession = MybatisSqlSessionFactory.opensesstion();){
			SaleMapperImpl saleimpl= new SaleMapperImpl(sqlsession);
			return saleimpl.viewBillList();
		}
		
	}
	
	public List<Sale> categorySaleReport() {
		try(SqlSession sqlsession = MybatisSqlSessionFactory.opensesstion();){
			SaleMapperImpl saleimpl= new SaleMapperImpl(sqlsession);
			return saleimpl.categorySaleReport();
		}
		
	}
	public List<Sale> softwareSaleReport(){
		try(SqlSession sqlsession = MybatisSqlSessionFactory.opensesstion();){
			SaleMapperImpl saleimpl= new SaleMapperImpl(sqlsession);
			return saleimpl.softwareSaleReport();
		}
		
	}
	
	
	public List<Sale> clientSoftwareReport(){
		try(SqlSession sqlsession = MybatisSqlSessionFactory.opensesstion();){
			SaleMapperImpl saleimpl= new SaleMapperImpl(sqlsession);
			return saleimpl.clientSoftwareReport();
		}
	}
	
	public List<Sale> allSelectSale(){
		try(SqlSession sqlsession = MybatisSqlSessionFactory.opensesstion();){
			SaleMapperImpl saleimpl= new SaleMapperImpl(sqlsession);
			return saleimpl.allSelectSale();
		}
	}
	
	public int insertSale(Sale sale){
		try(SqlSession sqlsession = MybatisSqlSessionFactory.opensesstion();){
			SaleMapperImpl saleimpl= new SaleMapperImpl(sqlsession);
			int res= saleimpl.insertSale(sale);
			 sqlsession.commit();
			 return res;
		}
	}
	
	public int deleteSale(Sale sale) {
		try(SqlSession sqlsession = MybatisSqlSessionFactory.opensesstion();){
			SaleMapperImpl saleimpl= new SaleMapperImpl(sqlsession);
			int res= saleimpl.deleteSale(sale);
			 sqlsession.commit();
			 return res;
		}
	}
	
	public Sale CodeSelectSaleItem(Sale sale) {
		try(SqlSession sqlsession = MybatisSqlSessionFactory.opensesstion();){
			SaleMapperImpl saleimpl= new SaleMapperImpl(sqlsession);
			return saleimpl.CodeSelectSaleItem(sale);
		}
	}
}
