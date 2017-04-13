select * from sale;
select * from software;
SELECT c.group_code, c.group_name,
/*총판매가격*/ (sd.total_sale_price) c_salePrice, 
/*총판매수량*/ (s.sale_amount) c_amount
	FROM category c JOIN software sw ON c.group_code= sw.group_code 
					JOIN sale s ON sw.sw_code=s.sw_code
					JOIN view_sale_detail sd ON sd.sale_code = s.sale_code
	GROUP BY c.group_code;
	select * from software;
		SELECT * FROM software sw
				  left outer JOIN category cat ON sw.group_code = cat.group_code
				  left outer JOIN sale s ON sw.sw_code = s.sw_code
				  left outer JOIN client cl ON cl.clnt_code = s.clnt_code
				  group by sw.sw_code;
select * from address;
select * from client;
select * from sale;

SELECT * FROM software sw
			 JOIN category cat ON sw.group_code = cat.group_code
			 JOIN sale s ON sw.sw_code = s.sw_code
			 JOIN client cl ON cl.clnt_code = s.clnt_code
			 join view_sale_detail vd on s.sale_code= vd.sale_code
                group by s.sale_code;
=======
USE sw_erp;
-- 공급회사
CREATE TABLE supply_company (
	compNo   VARCHAR(6)  NOT NULL,
	compName VARCHAR(20) NOT NULL,
	compAddr VARCHAR(50) NULL,
	compTel  VARCHAR(15) NULL,
	PRIMARY KEY (compNo)
);
select * from sale;
-- 고객현황
CREATE TABLE client (
	clntNo   VARCHAR(6)  NOT NULL,
	clntName VARCHAR(20) NOT NULL,
	clntAddr VARCHAR(50) NULL,
	clntTel  VARCHAR(15) NULL,
	PRIMARY KEY (clntNo)
);

-- 소프트웨어
CREATE TABLE software (
	swNo          VARCHAR(6)  NOT NULL, 
	swGroup       VARCHAR(10) NULL, 
	swName        VARCHAR(50) NOT NULL, 
	swSupplyPrice INTEGER     NOT NULL, 
	swPrice       INTEGER     NOT NULL, 
	supplyComp    VARCHAR(6)  NOT NULL, 
	PRIMARY KEY (swNo), 
	FOREIGN KEY (supplyComp) REFERENCES supply_company (compNo) 
		ON UPDATE CASCADE
);


-- 판매현황
CREATE TABLE sale (
	saleNo        VARCHAR(6)  NOT NULL,
	clntNo        VARCHAR(6)  NOT NULL,
	swNo          VARCHAR(6)  NOT NULL,
	sellingAmount INTEGER NOT NULL,
	isDeposit     BOOLEAN NOT NULL,
	orderDate     DATE    NOT NULL,
	PRIMARY KEY (saleNo),
	FOREIGN KEY (clntNo) REFERENCES client (clntNo)
		ON UPDATE CASCADE, 
	FOREIGN KEY (swNo)   REFERENCES software (swNo)
		ON UPDATE CASCADE
);

DROP TABLE sale;
DROP TABLE software;
DROP TABLE supply_company;
DROP TABLE client;

-- compNo, compName, compAddr, compTel
-- 공급회사 샘플데이터 입력
INSERT INTO supply_company (compNo, compName, compAddr, compTel) VALUES
	("SC001", "알럽소프트",   "경기도 부천시 계산동", 			"02-930-4551"),
	("SC002", "인디넷", 	  "경기도 수원시 제포동", 			"032-579-4512"),
	("SC003", "참빛소프트",   "경기도 파주군 은빛아파트", 		"032-501-4503"),
	("SC004", "소프트마켓",   "서울특별시 관진구 자양동", 		"02-802-4564"),
	("SC005", "크라이스", 	  "경기도 고양시 대자동 서영아파트","032-659-3215"),
	("SC006", "프로그램캠프", "경기도 부천시 오정구", 			"032-659-3215");

-- swNo, swGroup, swName, swSupplyPrice, swPrice, supplyComp    
-- 소프트웨어 샘플데이터 입력
INSERT INTO software (swNo, swGroup, swName, swSupplyPrice, swPrice, supplyComp) VALUES
	("SW001","게임",  "바람의 제국", 25000  , 40000,  "SC001"),
	("SW002","사무",  "국제무역",	 30000  , 48000,  "SC002"),
	("SW003","게임",  "FIFA2015",	 27000  , 40500,  "SC003"),
	("SW004","게임",  "삼국지",		 32000  , 48000,  "SC004"),
	("SW005","게임",  "아마겟돈",	 35000  , 50750,  "SC005"),
	("SW006","사무",  "한컴오피스",	 1370000, 1918000,"SC006"),
	("SW007","그래픽","포토샵",		 980000 , 1519000,"SC003"),
	("SW008","그래픽","오토캐드2015",2340000, 3978000,"SC004"),
	("SW009","그래픽","인디자인",	 1380000, 2180400,"SC001"),
	("SW010","사무",  "Window10",	 2470000, 3334500,"SC002");

	
-- clntNo, clntName, clntAddr, clntTel
-- 고객 현황 샘플데이터 입력
INSERT INTO client (clntNo, clntName, clntAddr, clntTel) VALUES
	("CL001", "재밌는 게임방", 	   "서울시 동대문구 연희동",  "02-111-1111"),
	("CL002", "좋은 게임방", 	   "서울시 관악구 봉천동",    "02-222-2222"),
	("CL003", "친구 게임방", 	   "천안시 동남구 신부동",    "041-333-3333"),
	("CL004", "충청남도 교육청",   "대전광역시 중구 과례2길", "042-444-4444"),
	("CL005", "대전광역시 교육청", "대전광역시 서구 향촌길",  "042-555-5555"),
	("CL006", "아산시스템", 	   "충청남도 아산시 배방면",  "041-777-7777");


-- saleNo, clntNo, swNo, sellingAmount, isDeposit, orderDate
-- 판매 현황 샘플데이터 입력
INSERT INTO sale (saleNo, clntNo, swNo, sellingAmount, isDeposit, orderDate) VALUES
	("SL001","CL001","SW001",25, true, "2009-12-13"),
	("SL002","CL003","SW005",25, true, "2010-09-13"),
	("SL003","CL002","SW004",20, true, "2010-09-11"),
	("SL004","CL001","SW004",25, true, "2010-10-02"),
	("SL005","CL004","SW009",250,false,"2010-10-02"),
	("SL006","CL006","SW009",2,  false,"2010-10-02"),
	("SL007","CL003","SW001",20, true, "2010-10-04"),
	("SL008","CL005","SW007",20, true, "2010-10-04"),
	("SL009","CL006","SW007",2,  true, "2010-10-04"),
	("SL010","CL004","SW006",320,true, "2010-10-04");

-- 공급회사 테이블
SELECT compNo, compName, compAddr, compTel FROM supply_company; 

-- 소프트웨어 테이블
SELECT swNo, swGroup, swName, swSupplyPrice, swPrice, compName FROM software, supply_company
	WHERE supplyComp=compNo ORDER BY swNo; 

-- 고객현황 테이블
SELECT clntNo, clntName, clntAddr, clntTel FROM client;

-- 판매현황 테이블
-- SELECT saleNo, clntName, swName, sellingAmount, isDeposit, orderDate FROM sale s, client c, software sw
-- 	WHERE s.clntNo = c.clntNo AND s.swNo = sw.swNo ORDER BY saleNo;

-- 판매현황 테이블
SELECT saleNo, clntName, swName, sellingAmount, isDeposit, orderDate 
FROM sale s JOIN client c ON s.clntNo = c.clntNo
			JOIN software sw ON s.swNo = sw.swNo 
			ORDER BY saleNo; 

-- 고객별 판매현황 조회
SELECT clntName, swName, sellingAmount, isDeposit, swPrice, 
		@saleprice := sellingAmount*swPrice salePrice, 
		@receivable := @saleprice*!isDeposit receivablePrice
	FROM sale s, client c, software sw
	WHERE s.clntNo = c.clntNo AND s.swNo = sw.swNo ORDER BY salePrice DESC;
	
-- S/W별 판매현황 조회 프로그램
-- 날짜별 판매현황 조회

SELECT * FROM sale;
SELECT * FROM software;
SELECT * FROM supply_company;
SELECT * FROM client;


/* * * * * * * * * * * * * * * * * * * TRIGGER * * * * * * * * * * * * * * * * * * */

DROP TABLE sale_detail;
DROP TRIGGER tri_sale_insert_after;

CREATE TABLE sale_detail(
   saleNo 		VARCHAR(6),
   sellingPrice INTEGER,
   supplyPrice 	INTEGER,
   salePrice 	INTEGER,
   outstanding 	INTEGER,
   tax 			INTEGER,
   totalPrice 	INTEGER
);

CREATE TRIGGER tri_sale_insert_after
AFTER INSERT ON sale
FOR EACH ROW 
BEGIN SET
	/*판매 금액*/	@sellingPrice	= (SELECT swPrice FROM software s WHERE s.swNo=NEW.swNo) * NEW.sellingAmount,
	/*공급 금액*/	@supplyPrice	= (SELECT swSupplyPrice FROM software s WHERE s.swNo=NEW.swNo) * NEW.sellingAmount,
	/*매출금*/		@salePrice 		= @sellingPrice - @supplyPrice,
	/*미수금*/		@outstanding 	= @sellingPrice * NEW.isDeposit,
	/*세금*/		@tax 			= @sellingPrice * 0.1,
	/*총 납부금액*/	@totalPrice 	= @sellingPrice + @tax
	INSERT INTO sale_detail VALUES (NEW.saleNo, @sellingPrice, @supplyPrice, @salePrice, @outstanding, @tax, @totalPrice);
END ;

sale은 거래내역.
거래 후에 sale에 데이터가 입력됨.
sale(sale_detail)에 데이터가 입력된 후에는 software의 값(단가, 공급금액)이 변할 때
	->	sale(sale_detail)의 데이터가 변하지 않아야함.
	
 select * from view_sale_by_orderdate;
 select * from supply_company;
>>>>>>> refs/remotes/origin/luuzun_addImg
