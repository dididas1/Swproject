package kr.or.digt.sw_project.utill;

public class Config {
	public static final String DB_NAME = "sw_project";
	public static final String USER   = "root";
	public static final String PWD    = "rootroot";
	public static final String URL    = "jdbc:mysql://localhost:3306/";
	public static final String DRIVER = "com.mysql.jdbc.Driver";

	public static final String[] TABLE_NAME = {"client","sale","software","supply_company","post"};
	public static final String IMPORT_DIR = System.getProperty("user.dir")+ "\\DataFiles\\";


	public static final String[] CREATE_SQL_TABLE={
			//공급회사
			"CREATE TABLE supply_company ( "
			+ " compNo   VARCHAR(6)  NOT NULL,"
			+ " compName VARCHAR(20) NOT NULL, "
			+ " compAddr VARCHAR(50) NULL, "
			+ " compTel  VARCHAR(15) NULL, "
			+ "  PRIMARY KEY (compNo));",
			// 고객현황
			"CREATE TABLE client  "
			+ "(  clntNo   VARCHAR(6)  NOT NULL,   "
			+ "clntName VARCHAR(20) NOT NULL,   "
			+ "clntAddr VARCHAR(50) NULL,   "
			+ "clntTel  VARCHAR(15) NULL,   "
			+ "PRIMARY KEY (clntNo));",
			// 소프트웨어
			"CREATE TABLE software ("
			+ "	swNo VARCHAR(6)  NOT NULL,  "
			+ " swGroup VARCHAR(10) NULL, "
			+ " swName  VARCHAR(50) NOT NULL,"
			+ " swSupplyPrice INTEGER  NOT NULL,"
			+ " swPrice       INTEGER     NOT NULL, "
			+ " supplyComp    VARCHAR(6)  NOT NULL,	 "
			+ "PRIMARY KEY (swNo), "
			+ "FOREIGN KEY (supplyComp) REFERENCES supply_company (compNo)	 "
			+ "ON UPDATE CASCADE);",
			//판매현황
			"CREATE TABLE sale "
			+ "(   saleNo        VARCHAR(6)  NOT NULL,   "
			+ " clntNo        VARCHAR(6)  NOT NULL,  "
			+ " swNo          VARCHAR(6)  NOT NULL,  "
			+ " sellingAmount INTEGER NOT NULL,  "
			+ " isDeposit     BOOLEAN NOT NULL,  "
			+ " orderDate     DATE    NOT NULL,  "
			+ " PRIMARY KEY (saleNo),  "
			+ " FOREIGN KEY (clntNo) REFERENCES client (clntNo)     "
			+ " ON UPDATE CASCADE,  "
			+ " FOREIGN KEY (swNo)   REFERENCES software (swNo)     "
			+ " ON UPDATE CASCADE );",
			//우편번호검색
			"CREATE TABLE if not exists post ("
					+ "zipcode   CHAR(5)     NULL,	"
					+ "sido      VARCHAR(20) NULL,	"
					+ "sigungu   VARCHAR(20) NULL,	"
					+ "doro      VARCHAR(80) NULL,	"
					+ "building1 int(5)     NULL,	"
					+ "building2 int(5)     NULL);"
		};


		public static final String[] CREATE_INDEX={
			"CREATE INDEX idx_post_sido On post(sido)",
			"CREATE INDEX idx_post_doro ON post(doro)"};
		
		
}
