package kr.or.digt.sw_project.utill;

public class Config {
	public static final String DB_NAME = "sw_project";
	public static final String USER   = "root";
	public static final String PWD    = "rootroot";
	public static final String URL    = "jdbc:mysql://localhost:3306/";
	public static final String DRIVER = "com.mysql.jdbc.Driver";

	public static final String[] TABLE_NAME = {"client","sale","software","supply_company","post"};
	public static final String IMPORT_DIR = System.getProperty("user.dir")+ "\\DataFiles\\";
	
	public static final String[] INSERT_ITEMS={
			"INSERT INTO supply_company VALUES   "
			+ "('SC001', '알럽소프트',   '경기도 부천시 계산동',          '02-930-4551'),   "
			+ "('SC002', '인디넷',      '경기도 수원시 제포동',         '032-579-4512'),   "
			+ "('SC003', '참빛소프트',   '경기도 파주군 은빛아파트',       '032-501-4503'),  "
			+ "('SC004', '소프트마켓',   '서울특별시 관진구 자양동',       '02-802-4564'),   "
			+ "('SC005', '크라이스',     '경기도 고양시 대자동 서영아파트','032-659-3215'),   "
			+ "('SC006', '프로그램캠프', '경기도 부천시 오정구',          '032-659-3215');",
			
			"INSERT INTO software VALUES   			"
			+ "('SW001','게임',  '바람의 제국', 25000  , 40000,  'SC001'),	"
			+ "('SW002','사무',  '국제무역',   30000  , 48000,  'SC002'),"
			+ "('SW003','게임',  'FIFA2015',    27000  , 40500,  'SC003'),   "
			+ "('SW004','게임',  '삼국지',       32000  , 48000,  'SC004'),   "
			+ "('SW005','게임',  '아마겟돈',    35000  , 50750,  'SC005'),   "
			+ "('SW006','사무',  '한컴오피스',    1370000, 1918000,'SC006'),   "
			+ "('SW007','그래픽','포토샵',       980000 , 1519000,'SC003'),   "
			+ "('SW008','그래픽','오토캐드2015',2340000, 3978000,'SC004'),   "
			+ "('SW009','그래픽','인디자인',    1380000, 2180400,'SC001'),   "
			+ "('SW010','사무',  'Window10',    2470000, 3334500,'SC002');",
			
			"INSERT INTO client VALUES   "
			+ "('CL001', '재밌는 게임방',       '서울시 동대문구 연희동',  '02-111-1111'),   "
			+ "('CL002', '좋은 게임방',       '서울시 관악구 봉천동',    '02-222-2222'),   "
			+ "('CL003', '친구 게임방',       '천안시 동남구 신부동',    '041-333-3333'),   "
			+ "('CL004', '충청남도 교육청',   '대전광역시 중구 과례2길', '042-444-4444'),   "
			+ "('CL005', '대전광역시 교육청', '대전광역시 서구 향촌길',  '042-555-5555'),   "
			+ "('CL006', '아산시스템',       '충청남도 아산시 배방면',  '041-777-7777');",
			
			
			"INSERT INTO sale VALUES			   "
			+ "('SL001','CL001','SW001',25, true, '2009-12-13'),   "
			+ "('SL002','CL003','SW005',25, true, '2010-09-13'),   "
			+ "('SL003','CL002','SW004',20, true, '2010-09-11'),   "
			+ "('SL004','CL001','SW004',25, true, '2010-10-02'),   "
			+ "('SL005','CL004','SW009',250,false,'2010-10-02'),   "
			+ "('SL006','CL006','SW009',2,  false,'2010-10-02'),   "
			+ "('SL007','CL003','SW001',20, true, '2010-10-04'),   "
			+ "('SL008','CL005','SW007',20, true, '2010-10-04'),  "
			+ "('SL009','CL006','SW007',2,  true, '2010-10-04'),   "
			+ "('SL010','CL004','SW006',320,true, '2010-10-04');"

			
			
	};
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
