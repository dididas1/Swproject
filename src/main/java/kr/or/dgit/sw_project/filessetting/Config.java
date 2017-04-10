package kr.or.dgit.sw_project.filessetting;

public class Config {
	public static final String DB_NAME = "sw_project";
	public static final String USER   = "root";
	public static final String PWD    = "rootroot";
	public static final String URL    = "jdbc:mysql://localhost:3306/";
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	
	public static final String[] TABLE_NAME = {"client","supply_company","category","software","delivery","sale","members"};
	
	public static final String[] CREATE_TABLES = {
			"CREATE TABLE client ("
			+"clnt_code    VARCHAR(6)  NOT NULL, " 
			+"clnt_name    VARCHAR(20) NOT NULL, "
			+"clnt_addr    VARCHAR(50) NULL, "
			+"clnt_tel     VARCHAR(15) NULL, "
			+"clnt_isExist BOOLEAN     NOT NULL, "
			+"PRIMARY KEY (clnt_code) "
			+")"
			,
			
			"CREATE TABLE supply_company ( "
			+"comp_code    VARCHAR(6)  NOT NULL, "
			+"comp_name    VARCHAR(20) NOT NULL, "
			+"comp_addr    VARCHAR(50) NULL, "
			+"comp_tel     VARCHAR(15) NULL, "
			+"comp_isExist BOOLEAN     NOT NULL, "
			+"PRIMARY KEY (comp_code) "
			+")"
			,
			
			"CREATE TABLE category ( "
			+"group_code VARCHAR(2)  NOT NULL, "   
			+"group_name VARCHAR(20) NOT NULL, "
			+"PRIMARY KEY (group_code) "
			+")"
			,
			
			"CREATE TABLE software ( "
			+"sw_code    VARCHAR(6)   NOT NULL, "
			+"group_code VARCHAR(6)   NOT NULL, "
			+"sw_name    VARCHAR(50)  NOT NULL, "
			+"sale_price INTEGER      NOT NULL, "
			+"sw_inven   INTEGER      NOT NULL, "
			+"sw_img	   VARCHAR(100) NOT NULL, "
			+"sw_issale  BOOLEAN      NOT NULL, "
			+"PRIMARY KEY (sw_code), "
			+"FOREIGN KEY (group_code) " 
			+"REFERENCES category(group_code) "
			+"ON UPDATE CASCADE "
			+")"
			,
			
			"CREATE TABLE delivery ( "
			+"del_code 	  VARCHAR(6) NOT NULL, "
			+"comp_code 	  VARCHAR(6) NOT NULL, "
			+"sw_code   	  VARCHAR(6) NOT NULL, "
			+"supply_price  INTEGER  	 NOT NULL, "
			+"supply_amount INTEGER	 NOT NULL, "
			+"order_date 	  DATE       NOT NULL, " 
			+"del_isExist   BOOLEAN	 NOT NULL, "
			+"PRIMARY KEY (del_code), "
			+"FOREIGN KEY (comp_code) "
			+"REFERENCES supply_company (comp_code) "
			+"ON UPDATE CASCADE, "
			+"FOREIGN KEY (sw_code) "   
			+"REFERENCES software (sw_code) "
			+"ON UPDATE CASCADE "
			+")"
			,
			
			"CREATE TABLE sale ( "
			+"sale_code    VARCHAR(6) NOT NULL, "
			+"clnt_code    VARCHAR(6) NOT NULL, "
			+"sw_code      VARCHAR(6) NOT NULL, "
			+"sale_amount  INTEGER    NOT NULL, "
			+"isdeposit    BOOLEAN    NOT NULL, "
			+"order_date   DATE       NOT NULL, "
			+"supply_price INTEGER    NOT NULL, "
			+"sale_price   INTEGER    NOT NULL, "
			+"sale_isExist BOOLEAN    NOT NULL, "
			+"PRIMARY KEY (sale_code), "
			+"FOREIGN KEY (clnt_code)  "
			+"REFERENCES client (clnt_code) "
			+"ON UPDATE CASCADE, "
			+"FOREIGN KEY (sw_code) " 
			+"REFERENCES software (sw_code) "
			+"ON UPDATE CASCADE "
			+")"
			,
			
			"CREATE TABLE members( "
			+"mem_id		VARCHAR(20) NOT NULL, "
			+"mem_name		VARCHAR(20) NOT NULL, "
			+"mem_password  VARCHAR(20) NOT NULL, "
			+"mem_mail	   	VARCHAR(50) NOT NULL, "
			+"mem_permission VARCHAR(10) NOT NULL, "
			+"mem_isExist    BOOLEAN     NOT NULL, "
			+"PRIMARY KEY (mem_id) "
			+")"
	};
	
	public static final String CREATE_VIEW =
			"CREATE VIEW view_sale_detail AS "
			+"SELECT sale_code, "
/*총판매금액*/	+"sale_price * sale_amount AS total_sale_price, "
/*총공급금액*/	+"supply_price * sale_amount AS total_supply_price, "
/*마진액*/	+"(sale_price * sale_amount)/*총공급금액*/ - (supply_price*sale_amount)/*총판매금액*/	 AS margin, " 	
/*세금*/		+"(sale_price * sale_amount) * 0.1 AS tax, "
/*총납품금액*/	+"((sale_price * sale_amount) * 0.1) + (sale_price * sale_amount) AS tax_saleprice, "
/*미수금*/	+"(sale_price * sale_amount) * !isDeposit AS receivablePrice "
			+"FROM sale";
						
	public static final String EXPORT_DIR = System.getProperty("user.dir")+ "\\BackupFiles\\";
	public static final String IMPORT_DIR = System.getProperty("user.dir")+ "\\DataFiles\\";
}
