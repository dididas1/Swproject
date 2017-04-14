package kr.or.dgit.sw_project.initsetting;

public class Config {
	public static final String DB_NAME ="mysql";
	public static final String MY_DB_NAME ="sw_project";
	public static final String USER   = "root";
	public static final String PWD    = "rootroot";
	public static final String URL    = "jdbc:mysql://localhost:3306/";
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	
	public static final String[] TABLE_NAME = {"client","category","software","supply_company","delivery","sale","members","address"};
	
	public static final String[] CREATE_SQL_TABLE={
			//거래회사
			"CREATE TABLE client (   "
			+ "clnt_code    VARCHAR(6)  NOT NULL,    "
			+ "clnt_name    VARCHAR(20) NOT NULL,   "
			+ "clnt_addr    VARCHAR(50) NULL,   "
			+ "clnt_tel     VARCHAR(15) NULL,   "
			+ "clnt_isExist BOOLEAN     NOT NULL,   "
			+ "PRIMARY KEY (clnt_code) ); "	,
			//공급회사
			"CREATE TABLE supply_company (   "
			+ "comp_code    VARCHAR(6)  NOT NULL,   "
			+ "comp_name    VARCHAR(20) NOT NULL,   "
			+ "comp_addr    VARCHAR(50) NULL,   "
			+ "comp_tel     VARCHAR(15) NULL,   "
			+ "comp_isExist BOOLEAN     NOT NULL,     "
			+ "PRIMARY KEY (comp_code)  );  ",
			// 분류
			"CREATE TABLE category (   "
			+ "group_code VARCHAR(2)  NOT NULL,      "
			+ "group_name VARCHAR(20) NOT NULL,   "
			+ "PRIMARY KEY (group_code) );"	,
			
			//소프트웨어
			"CREATE TABLE software (   "
			+ "sw_code    VARCHAR(6)  NOT NULL,   "
			+ "group_code VARCHAR(6)  NOT NULL,  "
			+ " sw_name    VARCHAR(50) NOT NULL,  "
			+ " sale_price INTEGER     NOT NULL,   "
			+ "sw_inven   INTEGER     NOT NULL,     "
			+ "	sw_img	   BLOB			NOT NULL, "
			+ "sw_issale  BOOLEAN     NOT NULL,    "
			+ "PRIMARY KEY (sw_code),     FOREIGN KEY (group_code)      "
			+ " REFERENCES category(group_code)        ON UPDATE CASCADE  );    ",
			
			// 납품
			"CREATE TABLE delivery (   "
			+ "del_code      VARCHAR(6) NOT NULL,   "
			+ "comp_code      VARCHAR(6) NOT NULL,   "
			+ "sw_code        VARCHAR(6) NOT NULL,   "
			+ "supply_price  INTEGER      NOT NULL,   "
			+ "supply_amount INTEGER    NOT NULL,   "
			+ "order_date      DATE       NOT NULL,    "
			+ "del_isExist   BOOLEAN    NOT NULL,    "
			+ "PRIMARY KEY (del_code),    "
			+ "FOREIGN KEY (comp_code)       REFERENCES supply_company (comp_code)       ON UPDATE CASCADE,    "
			+ "FOREIGN KEY (sw_code)        REFERENCES software (sw_code)       ON UPDATE CASCADE  ); "	,
			// 거래내역
			" CREATE TABLE sale (   sale_code    VARCHAR(6) NOT NULL,   "
			+ "clnt_code    VARCHAR(6) NOT NULL,   "
			+ "sw_code      VARCHAR(6) NOT NULL,   "
			+ "sale_amount  INTEGER    NOT NULL,  "
			+ " isdeposit    BOOLEAN    NOT NULL,     "
			+ "order_date   DATE       NOT NULL,     "
			+ "supply_price INTEGER    NOT NULL,   "
			+ " sale_price   INTEGER    NOT NULL,     "
			+ "sale_isExist BOOLEAN    NOT NULL,   "
			+ "PRIMARY KEY (sale_code),   FOREIGN KEY (clnt_code)       REFERENCES client (clnt_code)      ON UPDATE CASCADE,  "
			+ " FOREIGN KEY (sw_code)       REFERENCES software (sw_code)        ON UPDATE CASCADE );  ",
			
			//로그인테이블
			"CREATE TABLE members(   "
			+ "mem_id         VARCHAR(20) NOT NULL,   "
			+ "mem_name      VARCHAR(20) NOT NULL,   "
			+ "mem_password   VARCHAR(200) NOT NULL,   "
			+ "mem_mail      VARCHAR(50) NOT NULL,   "
			+ "mem_permission VARCHAR(10) NOT NULL,   "
			+ "mem_isExist    BOOLEAN     NOT NULL,   "
			+ "PRIMARY KEY (mem_id)); "
			
			,			
			//주소테이블 생성
			
			"CREATE TABLE if not exists address ("
			+ "zipcode   CHAR(5)     NULL,	"
			+ "sido      VARCHAR(20) NULL,	"
			+ "sigungu   VARCHAR(20) NULL,	"
			+ "doro      VARCHAR(80) NULL,	"
			+ "building1 int(5)     NULL,	"
			+ "building2 int(5)     NULL)"

	};
	
	public static final String[] CREATE_VIEW={
			//sale계산뷰
			" CREATE VIEW view_sale_detail AS   "
			+ "SELECT sale_code, "
			+ "/*총판매금액*/   sale_price * sale_amount AS total_sale_price, "
			+ "/*총공급금액*/   supply_price * sale_amount AS total_supply_price,"
			+ " /*마진액*/      (sale_price * sale_amount) - (supply_price*sale_amount) AS margin,  "
			+ "/*세금*/      (sale_price * sale_amount) * 0.1 AS tax, "
			+ "/*총납품금액*/   ((sale_price * sale_amount) * 0.1) + (sale_price * sale_amount) AS tax_saleprice, "
			+ "/*미수금*/      (sale_price * sale_amount) * !isDeposit AS receivablePrice    "
			+ "FROM sale ;",
			
			//소프트웨어별 판매현황조회
			
			"create view view_sale_by_software as "
			+ "SELECT  distinct s.sale_code,sw.sw_code,sw.sw_name, c.group_name , su.comp_name,s.sale_isExist,"
			+ "/*공급금액*/ (vs.total_supply_price) total_supply_price,"
			+ "/*판매금액*/ (vs.total_sale_price) total_sale_price,"
			+ "/*판매이윤*/ (vs.margin) margin   "
			+ "FROM sale s   join view_sale_detail vs on s.sale_code= vs.sale_code   "
			+ "join software sw on s.sw_code= sw.sw_code   "
			+ "join category c on c.group_code= sw.group_code   "
			+ "join delivery d on s.sw_code= d.sw_code   "
			+ "join supply_company su on d.comp_code= su.comp_code;   ",			
			
			//고객별 판매현황조회 수정
			"CREATE VIEW view_sale_by_client AS "
			+ "SELECT distinct s.sale_code,cl.clnt_code, cl.clnt_name, sw.sw_code, sw.sw_name, s.sale_amount, s.isdeposit, s.sale_price,s.sale_isExist,"
			+ "/*매출금*/	sd.total_sale_price,"
			+ "/*미수금*/	sd.receivablePrice	"
			+ "FROM client cl JOIN sale s ON cl.clnt_code = s.clnt_code				   "
			+ "JOIN software sw ON s.sw_code = sw.sw_code				  "
			+ " JOIN view_sale_detail sd ON sd.sale_code = s.sale_code;	",
			
			
			
			//기간별판매현황조회
			"	CREATE VIEW view_sale_by_orderdate AS 			"
			+ "SELECT s.order_date, s.sale_code, cl.clnt_code, "
			+ "cl.clnt_name, sw.sw_code, sw.sw_name,sw.sale_price, s.sale_amount, s.isdeposit,vd.total_sale_price,s.sale_isExist		 "
			+ "FROM sale s JOIN client cl ON s.clnt_code = cl.clnt_code  		     "
			+ "JOIN view_sale_detail vd on s.sale_code= vd.sale_code			 "
			+ "JOIN software sw ON s.sw_code = sw.sw_code; 		 "			,
			
			
			//카테고리별판매현황조회
			
			"CREATE VIEW view_sale_by_category AS "
			+ "SELECT c.group_code, c.group_name, s.sale_isExist,"
			+ "/*총판매가격*/ SUM(sd.total_sale_price) c_salePrice,"
			+ "/*총판매수량*/ SUM(s.sale_amount) c_amount	"
			+ "FROM category c JOIN software sw ON c.group_code= sw.group_code					"
			+ "JOIN sale s ON sw.sw_code=s.sw_code					"
			+ "JOIN view_sale_detail sd ON sd.sale_code = s.sale_code	"
			+ "GROUP BY c.group_code",
			
			//SW 전체판매현황 보고서
			
			"CREATE VIEW view_sale_report AS "
			+ "SELECT s.order_date, c.group_name, sw.sw_code, sw.sw_name, s.sale_code, sale_amount, s.sale_isExist,"
			+ "/*총 판매금액*/ sd.total_sale_price	"
			+ "FROM sale s JOIN software sw ON s.sw_code=sw.sw_code            	"
			+ "JOIN category c ON sw.group_code= c.group_code            	"
			+ "JOIN view_sale_detail sd ON sd.sale_code = s.sale_code	"
			+ "ORDER BY s.order_date DESC" ,
			
			//거래명세서
			
			" CREATE VIEW view_bill_list AS "
			+ "SELECT DISTINCT s.sale_code, sc.comp_code, sc.comp_name, s.order_date, c.clnt_code, c.clnt_name,	sw.sw_code, sw.sw_name, s.sale_price, s.sale_amount, s.sale_isExist,"
			+ "/*총판매금액*/  sd.total_sale_price,"
			+ "/*세금*/        sd.tax, "
			+ " /*총납품금액*/  sd.tax_saleprice "
			+ "FROM supply_company sc JOIN delivery del ON sc.comp_code = del.comp_code	                       "
			+ "JOIN software sw ON del.sw_code = sw.sw_code	                      "
			+ "JOIN sale s ON sw.sw_code = s.sw_code                     	  "
			+ "JOIN client c ON s.clnt_code = c.clnt_code                    	   "
			+ "JOIN view_sale_detail sd ON sd.sale_code = s.sale_code;                       ",
			
			//그래프출력
			
			"CREATE VIEW view_sale_graph AS "
			+ "SELECT c.clnt_code, c.clnt_name, SUM(sale_amount) sale_amount	"
			+ "FROM sale s JOIN client c ON s.clnt_code=c.clnt_code "
			+ "GROUP BY c.clnt_name; "
			
	
	
			
	};
	
	public static final String[] CRETE_TRIGGER={      
			// 납품 테이블 입력시 수량조정

			"CREATE TRIGGER tri_software_after_insert_delivery	"
			+ "AFTER INSERT ON delivery 	"
			+ "FOR EACH ROW BEGIN	"
			+ "IF NEW.del_isExist = true THEN 		"
			+ "UPDATE software SET sw_inven = sw_inven + NEW.supply_amount		"
			+ "WHERE sw_code = NEW.sw_code;    "
			+ "END IF;"
			+ "END",
		
			
			// 납품테이블 업데이트시 수량조정
			
			"CREATE TRIGGER tri_software_after_update_delivery   "
			+ " AFTER update ON delivery     "
			+ "FOR EACH ROW BEGIN 	"
			+ "IF NEW.del_isExist = FALSE THEN		"
			+ "UPDATE software SET sw_inven = sw_inven - NEW.supply_amount		"
			+ "WHERE sw_code= NEW.sw_code;"
			+ "	END IF;"
			+ "END ",
			
			// 판메테이블 입력시 수량조절
			
			"CREATE TRIGGER tri_software_after_insert_sale    "
			+ "AFTER insert    ON sale    "
			+ "FOR EACH ROW BEGIN	"
			+ "IF NEW.sale_isExist = TRUE THEN		"
			+ "UPDATE software SET sw_inven = sw_inven - NEW.sale_amount		"
			+ "WHERE sw_code= NEW.sw_code;	"
			+ "END IF ;"
			+ "END",
			
			//판매테이블 업데이트시 수량조절
			
			"CREATE TRIGGER tri_software_after_update_sale	"
			+ "AFTER UPDATE ON sale 	"
			+ "FOR EACH ROW BEGIN	IF NEW.sale_isExist = FALSE THEN		"
			+ "UPDATE software SET sw_inven = sw_inven + NEW.sale_amount		"
			+ "WHERE sw_code = NEW.sw_code;   "
			+ " END IF;"
			+ "END "	
			
	};
						
	public static final String EXPORT_IMPORT_DIR = System.getProperty("user.dir")+ "\\BackupFiles\\";
	
	public static final String ADDRESS_IMPORT_DIR = System.getProperty("user.dir")+ "\\AddrDataFiles\\";
	
	public static final String EXPORT_IMAGES_DIR = System.getProperty("user.dir")+ "\\BackupImages\\";
	
	public static final String CREATE_ADMIN= 
			"INSERT INTO members(mem_id, mem_name, mem_password, mem_mail, mem_permission, mem_isExist)   "
			+ " VALUES('admin','admin',password('1234'),'dididas@naver.com', 'manager', TRUE);";
	
	public static final String[] CREATE_INDEX={
			"CREATE INDEX idx_post_sido On address(sido)",
			"CREATE INDEX idx_post_doro ON address(doro)"};
		
}
