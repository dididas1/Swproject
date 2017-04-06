
SoftWare 재고수량(sale_inven) 문제
Delivery, Sale에서 수정, 삭제 처리 문제 (admin, user)

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
INSERT INTO client VALUES (#{clntNo},#{clntName},#{clntAddr},#{clntTel},#{clntIsExist});


-- 수정
UPDATE client SET clnt_name="Update Test", clnt_addr="Update Test", clnt_tel="Update Test", clnt_isExist=TRUE
	WHERE clnt_code="CL999";

UPDATE client SET clnt_name=#{clntName} ,clnt_addr={clntAddr},clnt_tel= #{clntTel}, clnt_isExist=#{clntIsExist} 
	WHERE clnt_code=#{clntNo};

	
-- 삭제
UPDATE client SET clnt_isExist=FALSE WHERE clnt_code="CL999";
UPDATE client SET clnt_isExist=FALSE WHERE clnt_code=#{clntNo};




############################ Supply_Company ###########################
-- 전체검색
SELECT sc.comp_code, comp_name, comp_addr, comp_tel, comp_isExist, del.del_code 
	FROM supply_company sc
		JOIN delivery del ON del.comp_code = sc.comp_code;

	
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




	
################################# Delivery ################################

-- 전체검색
SELECT del_code, comp_code, sw_code, supply_price, supply_amount, order_date, del_isExist 
	FROM delivery;

SELECT del_code, sc.comp_code, sc.comp_name, sw.sw_code, sw.sw_name, supply_price, supply_amount, order_date, del_isExist 
	FROM delivery del 
		JOIN software sw ON del.sw_code = sw.sw_code
		JOIN supply_company sc ON sc.comp_code = del.comp_code
	ORDER BY del_code;
	
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
SELECT sale_code, clnt_code, sw_code, sale_amount, supply_price, sale_price, isdeposit, order_date, sale_isExist 
	FROM sale;

SELECT sale_code, s.clnt_code, c.clnt_name, s.sw_code, sw.sw_name, sale_amount, s.supply_price, s.sale_price, isdeposit, order_date, sale_isExist 
	FROM sale s JOIN client c ON c.clnt_code = s.clnt_code
				JOIN software sw ON sw.sw_code = s.sw_code;
	
-- 번호검색
SELECT sale_code, clnt_code,sw_code, sale_amount, supply_price, sale_price, isdeposit ,order_date, sale_isExist
	FROM sale 
	WHERE sale_code="SL001";

SELECT sale_code, clnt_code,sw_code, sale_amount, supply_price, sale_price, isdeposit ,order_date, sale_isExist
	FROM sale 
	WHERE sale_code=#{saleNo};


-- 입력
INSERT INTO sale(sale_code, clnt_code, sw_code, sale_amount, isdeposit, order_date, supply_price, sale_price, sale_isExist) 
	VALUES ("SL999","CL001","SW001",25, TRUE, "2009-12-13", 25000, 40000, TRUE);
	
INSERT INTO sale(sale_code, clnt_code, sw_code, sale_amount, isdeposit, order_date, supply_price, sale_price, sale_isExist)
	VALUES (#{saleNo}, #{clntNo}, #{swCode}, #{saleAmount}, #{isDeposit}, #{orderDate}, #{supplyPrice}, #{salePrice}, #{saleIsExist});


-- 삭제(수정구현안함 오기입시 삭제후 재입력, 삭제되더라도 리스트에 출력 비고란에 취소됨출력)
-- admin 권한에 업데이트 구현여부 고민중
UPDATE sale SET sale_isExist= #{saleDelete} 
	WHERE sale_code=#{saleCode};