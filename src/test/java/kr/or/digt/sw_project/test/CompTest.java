package kr.or.digt.sw_project.test;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.digt.sw_project.dto.Supply_company;
import kr.or.digt.sw_project.service.Supply_companyService;

public class CompTest {
	private static Supply_companyService supply_company;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		supply_company=  new Supply_companyService();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		supply_company = null;
	}

	@Test
	public void testall() {
		List<Supply_company> list  = supply_company.selectAllCompany();
		
		
	}

}
