select * from view_client_sale;

create view view_sale_by_software as
SELECT  distinct s.sale_code,sw.sw_code,sw.sw_name, c.group_name , su.comp_name,s.sale_isExist,
/*공급금액*/ (vs.total_supply_price) total_supply_price,
/*판매금액*/ (vs.total_sale_price) total_price,
/*판매이윤*/ (vs.margin) margin
   FROM sale s 
   join view_sale_detail vs on s.sale_code= vs.sale_code 
   join software sw on s.sw_code= sw.sw_code
   join category c on c.group_code= sw.group_code 
   join delivery d on s.sw_code= d.sw_code 
   join supply_company su on d.comp_code= su.comp_code;
   
   
				
				
				#### 고객별 판매현황조회 ####
-- 고객상호명 품목명 주문수량 입금여부 단가 매출금 미수금
CREATE VIEW view_client_sale AS 
SELECT cl.clnt_name, sw.sw_code, sw.sw_name, s.sale_amount, s.isdeposit, s.sale_price,
/*매출금*/	sd.total_sale_price, 
/*미수금*/	sd.receivablePrice
	FROM client cl JOIN sale s ON cl.clnt_code = s.clnt_code 
				   JOIN software sw ON s.sw_code = sw.sw_code
				   JOIN view_sale_detail sd ON sd.sale_code = s.sale_code;
               
  #### 날짜별 판매현황조회 ####
-- 날짜 주문번호 상호 품명 수량 입금여부 
CREATE VIEW view_sale_by_orderdate AS
SELECT s.order_date, s.sale_code, cl.clnt_code, cl.clnt_name, sw.sw_code, sw.sw_name, s.sale_amount, s.isdeposit
	FROM sale s JOIN client cl ON s.clnt_code = cl.clnt_code 
   				JOIN software sw ON s.sw_code = sw.sw_code;
   
		
   				#### 카테고리별 판매현황조회 ####
-- 그룹이름 총판매가격 총판매수량

CREATE VIEW view_sale_by_category AS
SELECT c.group_code, c.group_name,
/*총판매가격*/ SUM(sd.total_sale_price) c_salePrice, 
/*총판매수량*/ SUM(s.sale_amount) c_amount
	FROM category c JOIN software sw ON c.group_code= sw.group_code 
					JOIN sale s ON sw.sw_code=s.sw_code
					JOIN view_sale_detail sd ON sd.sale_code = s.sale_code
	GROUP BY c.group_code;
   				
	
	INSERT INTO client(clnt_code, clnt_name, clnt_addr, clnt_tel, clnt_isExist) VALUES
	("CL001", "재밌는 게임방",	   "서울시 동대문구 연희동",  "02-111-1111", TRUE),
	("CL002", "좋은 게임방",       "서울시 관악구 봉천동",    "02-222-2222", FALSE),
	("CL003", "친구 게임방",	   "천안시 동남구 신부동",    "041-333-3333",FALSE),
	("CL004", "충청남도 교육청",   "대전광역시 중구 과례2길", "042-444-4444",FALSE),
	("CL005", "대전광역시 교육청", "대전광역시 서구 향촌길",  "042-555-5555",FALSE),
	("CL006", "아산시스템",		   "충청남도 아산시 배방면",  "041-777-7777",FALSE);

-- 공급회사 샘플데이터 입력
INSERT INTO supply_company(comp_code, comp_name, comp_addr, comp_tel, comp_isExist) VALUES
	("SC001", "알럽소프트",   "경기도 부천시 계산동",            "02-930-4551", TRUE),
	("SC002", "인디넷",       "경기도 수원시 제포동",            "032-579-4512",FALSE),
	("SC003", "참빛소프트",   "경기도 파주군 은빛아파트",        "032-501-4503",FALSE),
	("SC004", "소프트마켓",   "서울특별시 관진구 자양동",        "02-802-4564", FALSE),
	("SC005", "크라이스",     "경기도 고양시 대자동 서영아파트", "032-659-3215",FALSE),
	("SC006", "프로그램캠프", "경기도 부천시 오정구",            "032-659-3215",TRUE);

-- 분류 입력
INSERT INTO category(group_code, group_name) VALUES
	("GM", "게임"),
	("OF", "사무"),
	("GR", "그래픽"),
	("ED", "교육");
	
-- 소프트웨어 샘플데이터 입력
INSERT INTO software(sw_code,group_code,sw_name,sale_price,sw_inven,sw_issale,sw_img) VALUES
	("SW001","GM", "바람의제국",40000,   2000,  true,"dgit"),
	("SW002","OF", "국제무역", 	48000,   500,   false,"dgit"),
	("SW003","GM", "FIFA2015",	40500, 	 1000 , false,"dgit"),
	("SW004","GM", "삼국지",	48000, 	 400,   false,"dgit"),
	("SW005","GM", "아마겟돈",	50750, 	 1000,  false,"dgit"),
	("SW006","OF", "한컴오피스",1918000, 2000,  false,"dgit"),
	("SW007","GR", "포토샵",	1519000, 400,   false,"dgit"),
	("SW008","ED", "오토캐드",	978000,	 2,	    false,"dgit"),
	("SW009","GM", "인디자인", 	218040,	 4000,  false,"dgit"),
	("SW010","OF", "windows10",	333450,	 40000, true,"dgit");
 
-- 납품현황입력
INSERT INTO delivery(del_code, comp_code, sw_code, supply_price, supply_amount, order_date, del_isExist) VALUES
	("DL001", "SC001", "SW001", 20000, 100, now(), TRUE),
	("DL002", "SC002", "SW002", 30000, 200, now(), FALSE),
	("DL003", "SC003", "SW003", 30000, 100, now(), FALSE),
	("DL004", "SC004", "SW004", 17000, 150, now(), FALSE),
	("DL005", "SC005", "SW005", 25000, 200, now(), TRUE),
	("DL006", "SC006", "SW006", 2000,  100, now(), FALSE),
	("DL007", "SC001", "SW007", 5000,  200, now(), FALSE),
	("DL008", "SC002", "SW008", 30000, 100, now(), FALSE),
	("DL009", "SC003", "SW009", 17000, 150, now(), FALSE),
	("DL010", "SC004", "SW010", 25000, 200, now(), FALSE),
	("DL011", "SC001", "SW001", 25000, 200, now(), TRUE);

-- 거래내역 샘플데이터 입력
INSERT INTO sale(sale_code, clnt_code, sw_code, sale_amount, 
				isdeposit, order_date, supply_price, sale_price, sale_isExist) VALUES  
	("SL001","CL001","SW001",25, TRUE, "2009-12-13", 25000  , 40000,   TRUE),
	("SL002","CL003","SW002",25, TRUE, "2010-09-13", 30000  , 48000,   FALSE),
	("SL003","CL002","SW003",20, TRUE, "2010-09-11", 27000  , 40500,   FALSE),
	("SL004","CL001","SW004",25, TRUE, "2010-10-02", 32000  , 48000,   FALSE),
	("SL005","CL004","SW005",250,FALSE,"2010-10-02", 35000  , 50750,   FALSE),
	("SL006","CL006","SW006",2,  FALSE,"2010-10-02", 1370000, 1918000, TRUE),
	("SL007","CL003","SW007",20, TRUE, "2010-10-04", 25000  , 40000,   FALSE),
	("SL008","CL005","SW008",20, TRUE, "2010-10-04", 30000  , 48000,   FALSE),
	("SL009","CL006","SW009",2,  TRUE, "2010-10-04", 32000  , 48000,   FALSE),
	("SL010","CL004","SW010",320,TRUE, "2010-10-04", 980000 , 1519000, FALSE),
	("SL011","CL004","SW001",100,TRUE, "2010-10-04", 25000  , 40000,   TRUE);


				   