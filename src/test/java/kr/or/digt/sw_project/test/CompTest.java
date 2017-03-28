package kr.or.digt.sw_project.test;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.dgit.sw_project.dto.SupplyCompany;
import kr.or.dgit.sw_project.service.SupplyCompanyService;

public class CompTest {
	private static SupplyCompanyService supplyCompany;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		supplyCompany=  new SupplyCompanyService();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		supplyCompany = null;
	}

	@Test
	public void testall() {
		List<SupplyCompany> list  = supplyCompany.selectAllCompany();
		
		
	}

}
