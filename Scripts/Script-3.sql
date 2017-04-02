DROP DATABASE erp;
CREATE DATABASE erp;
USE erp;

-- 거래회사
-- clnt_code, clnt_name, clnt_addr, clnt_tel, clnt_isExist
CREATE TABLE client (
   clnt_code    VARCHAR(6)  NOT NULL, 
   clnt_name    VARCHAR(20) NOT NULL,
   clnt_addr    VARCHAR(50) NULL,
   clnt_tel     VARCHAR(15) NULL,
   clnt_isExist BOOLEAN     NOT NULL,
   PRIMARY KEY (clnt_code)
);

-- 공급회사
-- comp_code, comp_name, comp_addr, comp_tel, comp_isExist
CREATE TABLE supply_company (
   comp_code    VARCHAR(6)  NOT NULL,
   comp_name    VARCHAR(20) NOT NULL,
   comp_addr    VARCHAR(50) NULL,
   comp_tel     VARCHAR(15) NULL,
   comp_isExist BOOLEAN     NOT NULL,
   PRIMARY KEY (comp_code)
);

-- 분류
-- group_code, group_name
CREATE TABLE category (
   group_code VARCHAR(2)  NOT NULL,   
   group_name VARCHAR(20) NOT NULL,
   PRIMARY KEY (group_code)
);

-- 소프트웨어
-- sw_code, group_code, sw_name, sale_price, sw_inven, sw_issale  
CREATE TABLE software (
   sw_code    VARCHAR(6)  NOT NULL,
   group_code VARCHAR(6)  NOT NULL,
   sw_name    VARCHAR(50) NOT NULL,
   sale_price INTEGER     NOT NULL,
   sw_inven   INTEGER     NOT NULL,
   sw_issale  BOOLEAN     NOT NULL,
   PRIMARY KEY (sw_code),
   FOREIGN KEY (group_code) 
      REFERENCES category(group_code)
      ON UPDATE CASCADE
);

-- 납품
-- del_code, comp_code, sw_code, supply_price, supply_amount, order_date, del_isExist
CREATE TABLE delivery (
   del_code      VARCHAR(6) NOT NULL,
   comp_code      VARCHAR(6) NOT NULL,
   sw_code        VARCHAR(6) NOT NULL,
   supply_price  INTEGER      NOT NULL,
   supply_amount INTEGER    NOT NULL,
   order_date      DATE       NOT NULL, 
   del_isExist   BOOLEAN    NOT NULL,
   PRIMARY KEY (del_code),
   FOREIGN KEY (comp_code) 
      REFERENCES supply_company (comp_code)
      ON UPDATE CASCADE,
   FOREIGN KEY (sw_code)   
      REFERENCES software (sw_code)
      ON UPDATE CASCADE
);

-- 거래내역
-- sale_code, clnt_code, sw_code, sale_amount, isdeposit, order_date, supply_price, sale_price, sale_delete
CREATE TABLE sale (
   sale_code    VARCHAR(6) NOT NULL,
   clnt_code    VARCHAR(6) NOT NULL,
   sw_code      VARCHAR(6) NOT NULL,
   sale_amount  INTEGER    NOT NULL,
   isdeposit    BOOLEAN    NOT NULL,
   order_date   DATE       NOT NULL,
   supply_price INTEGER    NOT NULL,
   sale_price   INTEGER    NOT NULL, 
   sale_isExist BOOLEAN    NOT NULL,
   PRIMARY KEY (sale_code),
   FOREIGN KEY (clnt_code) 
      REFERENCES client (clnt_code)
      ON UPDATE CASCADE,
   FOREIGN KEY (sw_code) 
      REFERENCES software (sw_code)
      ON UPDATE CASCADE
);

#### Create View View_Sale_Detail####
CREATE VIEW view_sale_detail AS
   SELECT sale_code,
/*총판매금액*/   sale_price * sale_amount AS total_sale_price,
/*총공급금액*/   supply_price * sale_amount AS total_supply_price,
/*마진액*/      (sale_price * sale_amount) - (supply_price*sale_amount) AS margin,    
/*세금*/      (sale_price * sale_amount) * 0.1 AS tax,
/*총납품금액*/   ((sale_price * sale_amount) * 0.1) + (sale_price * sale_amount) AS tax_saleprice,
/*미수금*/      (sale_price * sale_amount) * !isDeposit AS receivablePrice
   FROM sale;
SELECT * FROM view_sale_detail;

   SELECT * FROM sale;
SELECT sale_code,
/*총판매금액*/   @total_saleprice   := sale_price * sale_amount total_sale_price,
/*총공급금액*/   @total_supplyprice := supply_price * sale_amount,
/*마진액*/      @margin            := @total_saleprice - @total_supplyprice,    
/*세금*/      @tax             := @total_saleprice * 0.1 tax,
/*총납품금액*/   @tax_saleprice       := @tax + @total_saleprice tax_saleprice,
/*미수금*/      @receivable       := @total_salePrice * !isDeposit receivablePrice
   FROM sale;

SHOW tables;

-- 거래회사 샘플데이터 입력
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
INSERT INTO software(sw_code,group_code,sw_name,sale_price,sw_inven,sw_issale) values
	("SW001","GM", "바람의제국",40000,   2000,  TRUE),
	("SW002","OF", "국제무역", 	48000,   500,   FALSE),
	("SW003","GM", "FIFA2015",	40500, 	 1000 , FALSE),
	("SW004","GM", "삼국지",	48000, 	 400,   FALSE),
	("SW005","GM", "아마겟돈",	50750, 	 1000,  FALSE),
	("SW006","OF", "한컴오피스",1918000, 2000,  FALSE),
	("SW007","GR", "포토샵",	1519000, 400,   FALSE),
	("SW008","ED", "오토캐드",	978000,	 2,	    FALSE),
	("SW009","GM", "인디자인", 	218040,	 4000,  FALSE),
	("SW010","OF", "windows10",	333450,	 40000, TRUE);
 delete from software where sw_code="sw004";
-- 납품현황입력
INSERT INTO delivery(del_code, comp_code, sw_code, supply_price, supply_amount, order_date, del_isExist) VALUES
	("DL003", "SC003", "SW003", 30000, 100, now(), TRUE),
	("DL004", "SC004", "SW004", 17000, 150, now(), FALSE),
	("DL005", "SC005", "SW005", 25000, 200, now(), TRUE),
	("DL006", "SC006", "SW006", 2000,  100, now(), FALSE),
	("DL007", "SC001", "SW007", 5000,  200, now(), FALSE),
	("DL008", "SC002", "SW008", 30000, 100, now(), TRUE),
	("DL009", "SC003", "SW009", 17000, 150, now(), TRUE),
	("DL010", "SC004", "SW010", 25000, 200, now(), FALSE),
	("DL011", "SC001", "SW001", 25000, 400, now(), TRUE);

	delete from delivery;
	
-- 거래내역 샘플데이터 입력
INSERT INTO sale(sale_code, clnt_code, sw_code, sale_amount, 
				isdeposit, order_date, supply_price, sale_price, sale_isExist) VALUES  
	("SL003","CL001","SW003",25, TRUE, "2009-12-13", 25000  , 40000,   TRUE),
	("SL004","CL001","SW004",25, TRUE, "2010-10-02", 32000  , 48000,   FALSE),
	("SL005","CL004","SW005",250,FALSE,"2010-10-02", 35000  , 50750,   FALSE),
	("SL006","CL006","SW006",2,  FALSE,"2010-10-02", 1370000, 1918000, TRUE),
	("SL007","CL003","SW007",20, TRUE, "2010-10-04", 25000  , 40000,   FALSE),
	("SL008","CL005","SW008",20, TRUE, "2010-10-04", 30000  , 48000,   FALSE),
	("SL009","CL006","SW009",2,  TRUE, "2010-10-04", 32000  , 48000,   FALSE),
	("SL010","CL004","SW010",320,TRUE, "2010-10-04", 980000 , 1519000, FALSE),
	("SL011","CL004","SW001",100,TRUE, "2010-10-04", 25000  , 40000,   TRUE),
	("SL012","CL001","SW001",100,TRUE, "2010-10-04", 25000  , 40000,   TRUE);
			

SELECT * FROM client;
SELECT * FROM sale;
SELECT * FROM delivery;
SELECT * FROM software;
SELECT * FROM supply_company;
SELECT * FROM category;


############################ Client ###########################


-- 전체검색
SELECT clnt_code, clnt_name, clnt_addr, clnt_tel, clnt_isExist 
	FROM client;

	
-- 번호별 검색
SELECT clnt_code, clnt_name, clnt_addr, clnt_tel, clnt_isExist 
	FROM client 
	WHERE clnt_code="CL001";

SELECT clnt_code, clnt_name, clnt_addr, clnt_tel, clnt_isExist 
	FROM client 
	WHERE clnt_code=#{clntCode};

	
-- 입력
INSERT INTO client VALUES ("CL999", "테스트 게임방", "서울시 동대문구 연희동", "02-111-1111", TRUE);
INSERT INTO client VALUES (#{clntCode},#{clntName},#{clntAddr},#{clntTel},#{clntIsExist});


-- 수정
UPDATE client SET clnt_name="Update Test", clnt_addr="Update Test", clnt_tel="Update Test", clnt_isExist=TRUE
	WHERE clnt_code="CL999";

UPDATE client SET clnt_name=#{clntName} ,clnt_addr={clntAddr},clnt_tel= #{clntTel}, clnt_isExist=#{clntIsExist} 
	WHERE clnt_code=#{clntCode};

	
-- 삭제
UPDATE client SET clnt_isExist=FALSE WHERE clnt_code="CL999";
UPDATE client SET clnt_isExist=FALSE WHERE clnt_code=#{clntCode};




############################ Supply_Company ###########################
-- 전체검색
SELECT comp_code, comp_name, comp_addr, comp_tel, comp_isExist 
	FROM supply_company;

	
-- 번호검색
SELECT comp_code, comp_name, comp_addr, comp_tel, comp_isExist
	FROM supply_company 
	WHERE comp_code="SC001";
	
SELECT comp_code, comp_name, comp_addr, comp_tel, comp_isExist
	FROM supply_company 
	WHERE comp_code=#{compCode};

	
-- 입력
INSERT INTO supply_company VALUES ("SC999", "테스트소프트", "경기도 부천시 계산동", "02-930-4551", TRUE);
INSERT INTO supply_company VALUES (#{compCode},#{compName},#{compAddr},#{compTel},#{compIsExist});


-- 수정
UPDATE supply_company
	SET comp_name="Update Test", comp_addr="Update Test", comp_tel="Update Test", comp_isExist=TRUE
	WHERE comp_code="SC999";

UPDATE supply_company
	SET comp_name=#{compName}, comp_addr=#{compAddr}, comp_tel=#{compTel}, comp_isExist=#{compIsExist}, 
	WHERE comp_code=#{compCode};

	
-- 삭제
UPDATE supply_company SET comp_isExist=FALSE WHERE comp_code="SC999";
UPDATE supply_company SET comp_isExist=FALSE WHERE comp_code=#{compCode};




################################# category ################################
-- 전체검색
SELECT group_code,group_name FROM category;


-- 번호검색
SELECT group_code,group_name FROM category WHERE group_code="ED";
SELECT group_code,group_name FROM category WHERE group_code=#{groupCode};


-- 입력
INSERT INTO category VALUES("TS","Test");
INSERT INTO category VALUES(#{groupCode},#{groupName});


-- 수정
UPDATE category SET group_code="ZZ", group_name="Test2" 
	WHERE group_code="TS";
UPDATE category SET group_code=#{groupCode}, group_name=#{groupName} 
	WHERE group_code=#{groupCode};

	
-- 삭제
DELETE FROM category WHERE group_code="ZZ";
DELETE FROM category WHERE group_code=#{groupCode};





################################# SoftWare ################################

-- 전체검색
SELECT sw_code, group_code, sw_name, sale_price, sw_inven, sw_issale FROM software;


--  번호검색
SELECT sw_code, group_code, sw_name, sale_price, sw_inven, sw_issale 
	FROM software 
	WHERE sw_code="SW001";
	
SELECT sw_code, group_code, sw_name, sale_price, sw_inven, sw_issale 
	FROM software 
	WHERE sw_code=#{swCode};

	
-- 입력 : 재고수량(sale_inven)에 대한 검토 필요.
INSERT INTO software(sw_code, group_code, sw_name, sale_price, sw_inven, sw_issale) 
	VALUES("SW999","GM", "TestSW",10000, 1000, TRUE);
INSERT INTO software(sw_code, group_code, sw_name, sale_price, sw_inven, sw_issale)
	VALUES(#{swCode}, #{groupCode}, #{swName}, #{salePrice}, #{swInven}, #{swIssale});


-- 수정
UPDATE software
	SET group_code="TS", sw_name="Test", sale_price=1000, sw_inven=10, sw_issale=TRUE
	WHERE sw_code="SW999";

UPDATE software 
	SET group_code=#{groupCode}, sw_name=#{swName}, sale_price=#{salePrice}, sw_issale=#{swIssale} 
	where sw_code=#{swCode};


-- 판매중지
UPDATE software SET sw_issale=FALSE 
	WHERE sw_code="SW999";
UPDATE software SET sw_issale=#{swIssale} 
	WHERE sw_code=#{swCode};


select * from client;


	
################################# Delivery ################################

-- 전체검색
SELECT del_code, comp_code, sw_code, sw_code, supply_price, supply_amount, order_date, del_isExist 
	FROM delivery;

	
-- 번호검색
SELECT del_code, comp_code, sw_code, sw_code, supply_price, supply_amount, order_date, del_isExist 
	FROM delivery
	WHERE del_code="DL001";
	
SELECT del_code, comp_code, sw_code, sw_code, supply_price, supply_amount, order_date, del_isExist 
	FROM delivery
	WHERE del_code=#{delCode};

	
-- 입력
INSERT INTO delivery(del_code, comp_code, sw_code, supply_price, supply_amount, order_date, del_isExist) VALUES
	("DL999", "SC001", "SW001", 20000, 100, now(), TRUE);

INSERT INTO delivery(del_code, comp_code, sw_code, supply_price, supply_amount, order_date, del_isExist) VALUES
	(#{delCode}, #{compCode}, #{swCode}, #{supplyPrice}, #{supplyAmount}, #{orderDate}, #{delIsExist});

-- 삭제(수정구현안함 오기입시 삭제후 재입력, 삭제되더라도 리스트에 출력 비고란에 취소됨출력)
-- admin 권한에 업데이트 구현여부 고민중
UPDATE delivery SET del_isExist=#{delIsExist} 
	WHERE del_code=#{delCode}




################################# Sale ################################

-- 전체검색
SELECT sale_code, clnt_code,sw_code, sale_amount, supply_price, sale_price, isdeposit, order_date, sale_isExist 
	FROM sale;


-- 번호검색
SELECT sale_code, clnt_code,sw_code, sale_amount, supply_price, sale_price, isdeposit ,order_date, sale_isExist
	FROM sale 
	WHERE sale_code="SL001";

SELECT sale_code, clnt_code,sw_code, sale_amount, supply_price, sale_price, isdeposit ,order_date, sale_isExist
	FROM sale 
	WHERE sale_code=#{saleCode};



-- 입력
INSERT INTO sale(sale_code, clnt_code, sw_code, sale_amount, isdeposit, order_date, supply_price, sale_price, sale_isExist) 
	VALUES ("SL999","CL001","SW001",25, TRUE, "2009-12-13", 25000, 40000, TRUE);
	
INSERT INTO sale(sale_code, clnt_code, sw_code, sale_amount, isdeposit, order_date, supply_price, sale_price, sale_isExist)
	VALUES (#{saleCode}, #{clntCode}, #{swCode}, #{saleAmount}, #{isDeposit}, #{orderDate}, #{supplyPrice}, #{salePrice}, #{saleIsExist});


-- 삭제(수정구현안함 오기입시 삭제후 재입력, 삭제되더라도 리스트에 출력 비고란에 취소됨출력)
-- admin 권한에 업데이트 구현여부 고민중
UPDATE sale SET sale_isExist= #{saleDelete} 
	WHERE sale_code=#{saleCode};

select * from sale;
       
       #### 고객별 판매현황조회 ####
-- 고객상호명 품목명 주문수량 입금여부 단가 매출금 미수금
create view vw_client_sale_view as

select cl.clnt_code,cl.clnt_name, sw.sw_name, s.sale_amount, s.isdeposit, s.sale_price,
/*매출금*/    sd.total_sale_price, 
/*미수금*/   sd.receivablePrice
   FROM client cl JOIN sale s ON cl.clnt_code = s.clnt_code 
               JOIN software sw ON s.sw_code = sw.sw_code
               JOIN view_sale_detail sd ON sd.sale_code = s.sale_code;
               
   
     select * from vw_client_sale_view;
   
   
   
SELECT cl.clnt_name, sw.sw_name, s.sale_amount, s.isdeposit, s.sale_price,
/*매출금*/    sd.total_sale_price, 
/*미수금*/   sd.receivablePrice
   FROM client cl JOIN sale s ON cl.clnt_code = s.clnt_code 
               JOIN software sw ON s.sw_code = sw.sw_code
               JOIN view_sale_detail sd ON sd.sale_code = s.sale_code
   WHERE cl.clnt_code=#{clntCode};

   
   
   
#### 소프트웨어별 판매현황조회 ####
-- 품목명 분류 공급회사명 공급금액 판매금액 판매이윤

 create view vw_sw_sale_view as
 
 SELECT  distinct s.sale_code, sw.sw_code,sw.sw_name, c.group_name , su.comp_name,
/*공급금액*/ (vs.total_supply_price) total_supply_price,
/*판매금액*/ (vs.total_sale_price) total_price,
/*판매이윤*/ (vs.margin) margin
   FROM sale s 
   join view_sale_detail vs on s.sale_code= vs.sale_code 
   join software sw on s.sw_code= sw.sw_code
   join category c on c.group_code= sw.group_code 
   join delivery d on d.sw_code= sw.sw_code
   join supply_company su on d.comp_code= su.comp_code
   where s.sw_code="SW001";
 
   select * from sale;
   
   
   select * from vw_sw_sale where sw_code ="SW001";
   
  select * from vw_sw_sale_view where sale_code=#{saleCode};
  
  
  
 
   
  
  
SELECT distinct s.sale_code,sw.sw_name, c.group_name, su.comp_name,
/*공급금액*/ @total_supply_price := SUM(vs.total_supply_price) total_supply_price,
/*판매금액*/ @total_price        := SUM(vs.total_sale_price) total_price,
/*판매이윤*/ @margin           := SUM(vs.margin) margin
  FROM sale s 
    join view_sale_detail vs on s.sale_code= vs.sale_code 
   join software sw on s.sw_code= sw.sw_code
   join category c on c.group_code= sw.group_code 
   join delivery d on d.sw_code= sw.sw_code
   join supply_company su on d.comp_code= su.comp_code;
   WHERE sw.sw_code =#{swCode};




#### 날짜별 판매현황조회 ####
-- 날짜 주문번호  상호 품명 수량 입금여부 
create view day_sale_view as
SELECT s.order_date, s.sale_code, cl.clnt_name, sw.sw_name, s.sale_amount, s.isdeposit
   FROM sale s JOIN client cl ON s.clnt_code = cl.clnt_code JOIN software sw ON s.sw_code = sw.sw_code;
   
   select * from vw_day_sale  WHERE order_date BETWEEN "2009-12-12" AND "2012-12-14" order by order_date;
   
   
SELECT s.order_date, s.sale_code, cl.clnt_name, sw.sw_name, s.sale_amount, s.isdeposit
   FROM sale s JOIN client cl ON s.clnt_code = cl.clnt_code JOIN software sw ON s.sw_code = sw.sw_code
   WHERE s.order_date BETWEEN #{date} AND #{date}
   ORDER BY s.sale_code;



   
#### 카테고리별 판매현황조회 ####
-- 그룹이름 총판매가격 총판매수량

create view category_sale_view as
SELECT c.group_name,
/*총판매가격*/   SUM(sd.total_sale_price) c_salePrice, 
/*총판매수량*/   SUM(s.sale_amount) c_amount
   FROM category c JOIN software sw ON c.group_code= sw.group_code 
               JOIN sale s ON sw.sw_code=s.sw_code
               JOIN view_sale_detail sd ON sd.sale_code = s.sale_code
   GROUP BY c.group_name;

select group_code,group_name,c_salePrice,c_amount from view_sale_by_category where=#{groupCode};


#### SW 전체판매현황 보고서 ####
-- 날짜 분류 품목명 주문번호 주문수량 판매금액
 
create view all_sale_report_view as
SELECT s.order_date, c.group_name, sw.sw_name, s.sale_code, sale_amount,
/*총 판매금액*/   sd.total_sale_price
   FROM sale s JOIN software sw ON s.sw_code=sw.sw_code 
            JOIN category c ON sw.group_code= c.group_code
            JOIN view_sale_detail sd ON sd.sale_code = s.sale_code
   ORDER BY s.order_date DESC;

   select * from view_sale_report;

   
   
-- 총합계

SELECT sum(total_sale_price) 
   FROM view_sale_detail;

            

            
#### 거래명세서 ####
-- 공급회사명 날짜 고객명 품명 단가 주문수량 총판매금액 세금 총납품금액

create view trade_list_view as

SELECT distinct sd.sale_code,comp_name, s.order_date, c.clnt_name, sw.sw_name, s.sale_price, s.sale_amount,
/*총판매금액*/   sd.total_sale_price, 
/*세금*/      sd.tax,   
/*총납품금액*/   sd.tax_saleprice
   FROM supply_company su JOIN delivery dl ON su.comp_code = dl.comp_code 
                     JOIN software sw ON dl.sw_code   = sw.sw_code 
                     JOIN sale s       ON sw.sw_code   = s.sw_code 
                     JOIN client c    ON s.clnt_code  = c.clnt_code
                        JOIN view_sale_detail sd ON sd.sale_code = s.sale_code;
                        
select sale_code,comp_name,order_date,clnt_name,sw_name,sale_price,sale_amount,total_sale_price,tax,tax_saleprice  from view_bill_list;
 
-- 총납품금액 합계

 SELECT sum(tax_saleprice) FROM view_sale_detail;


#### 그래프출력 ####
-- 주문현황 (고객이름 , 고객별 총주문갯수)

create view sale_graph_view as
SELECT c.clnt_name, SUM(sale_amount) 
   FROM sale s JOIN client c ON s.clnt_code=c.clnt_code
GROUP BY c.clnt_name;
   
 select clnt_name,`SUM(sale_amount)` from view_sale_graph;
   

$$$$$ 트리거 $$$$$

-- 납품 테이블 업데이트시 수량조정
DELIMITER $$
CREATE TRIGGER tri_software_after_update_delivery
    AFTER update 
    ON delivery
    FOR EACH ROW
BEGIN
  	if  NEW.del_isExist = false then
        update software set sw_inven = sw_inven-new.supply_amount
        where sw_code= new.sw_code;
        elseif new.del_isExist=   true then
          update software set sw_inven = sw_inven+new.supply_amount
        where sw_code= new.sw_code;
    END IF;
end $$
DELIMITER ;

-- 납품 테이블 인서트시 수량조정
DELIMITER $$
CREATE TRIGGER tri_software_after_insert_delivery
    AFTER insert 
    ON delivery
    FOR EACH ROW
BEGIN
    IF NEW.del_isExist = false THEN
        update software set sw_inven = sw_inven+new.supply_amount
        where sw_code= new.sw_code;
    END IF;
end $$
DELIMITER ;

-- 판매테이블 인서트시 수량조절
DELIMITER $$
CREATE TRIGGER tri_software_after_insert_sale
    AFTER insert 
    ON sale
    FOR EACH ROW
BEGIN
    IF NEW.sale_isExist = true THEN
        update software set sw_inven = sw_inven-new.sale_amount
        where sw_code= new.sw_code;
     END IF;
end $$
DELIMITER ;

-- 판매테이블 업데이트시 수량조절
DELIMITER $$
CREATE TRIGGER tri_software_after_update_sale
    AFTER update 
    ON sale
    FOR EACH ROW
BEGIN
   IF  NEW.sale_isExist = false then
        update software set sw_inven = sw_inven+new.sale_amount
        where sw_code= new.sw_code;
        elseif new.sale_isExist= true then
          update software set sw_inven = sw_inven-new.sale_amount
        where sw_code= new.sw_code;
    END IF;
end $$
DELIMITER ;


CREATE VIEW view_bill_list AS
SELECT DISTINCT s.sale_code, sc.comp_name, s.order_date, c.clnt_name, sw.sw_name, s.sale_price, s.sale_amount,
/*총판매금액*/  sd.total_sale_price, 
/*세금*/        sd.tax,   
/*총납품금액*/  sd.tax_saleprice
	FROM supply_company sc JOIN delivery del ON sc.comp_code = del.comp_code 
	                       JOIN software sw ON del.sw_code = sw.sw_code 
	                       JOIN sale s ON sw.sw_code = s.sw_code 
                     	   JOIN client c ON s.clnt_code = c.clnt_code
                     	   JOIN view_sale_detail sd ON sd.sale_code = s.sale_code;

select * from sale s join view_sale_detail sv on s.sale_code= sv.sale_code where s.sale_code="SL999";


select * from view_sale_by_orderdate;

select order_date,sale_code,clnt_name,sw_name,sale_amount,isdeposit from view_sale_by_orderdate 
WHERE order_date BETWEEN #{date} AND #{date}
order by order_date desc;

select sw_code,sw_name,group_name,comp_name,total_supply_price,total_price,margin from view_sw_sale where sw_code="SW001" order by sw_code;