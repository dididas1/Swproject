package kr.or.digt.sw_project.test;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.dgit.sw_project.dto.Sale;
import kr.or.dgit.sw_project.service.SaleService;

public class SaleTest {
	private static SaleService saleService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		saleService=  new SaleService();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		saleService = null;
	}

	@Test
	public void testall() {
		List<Sale> list =saleService.allSelectSale();
		
		
		
	}
}
