package kr.or.dgit.sw_project.service;

import java.util.List;

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
