#### Create View View_Sale_Detail####
CREATE VIEW view_sale_detail AS
	SELECT sale_code,
/*총판매금액*/	sale_price * sale_amount AS total_sale_price,
/*총공급금액*/	supply_price * sale_amount AS total_supply_price,
/*마진액*/		(sale_price * sale_amount) - (supply_price*sale_amount) AS margin, 	
/*세금*/		(sale_price * sale_amount) * 0.1 AS tax,
/*총납품금액*/	((sale_price * sale_amount) * 0.1) + (sale_price * sale_amount) AS tax_saleprice,
/*미수금*/		(sale_price * sale_amount) * !isDeposit AS receivablePrice
	FROM sale;
	
	
	
#### 고객별 판매현황조회 ####
-- 고객상호명 품목명 주문수량 입금여부 단가 매출금 미수금
CREATE VIEW view_client_sale AS 
SELECT cl.clnt_code, cl.clnt_name, sw.sw_code, sw.sw_name, s.sale_amount, s.isdeposit, s.sale_price,
/*매출금*/	sd.total_sale_price, 
/*미수금*/	sd.receivablePrice
	FROM client cl JOIN sale s ON cl.clnt_code = s.clnt_code 
				   JOIN software sw ON s.sw_code = sw.sw_code
				   JOIN view_sale_detail sd ON sd.sale_code = s.sale_code;
               

SELECT clnt_code, clnt_name, sw_code, sw_name, sale_amount, isdeposit, sale_price,
/*매출금*/	total_sale_price, 
/*미수금*/	receivablePrice FROM view_client_sale WHERE clnt_code="CL001";

SELECT clnt_code, clnt_name, sw_code, sw_name, sale_amount, isdeposit, sale_price,
/*매출금*/	total_sale_price, 
/*미수금*/	receivablePrice FROM view_client_sale WHERE clnt_code=#{clntCode};         
   


#### 소프트웨어별 판매현황조회 ####
-- 품목명 분류 공급회사명 공급금액 판매금액 판매이윤
CREATE VIEW view_sw_sale AS
SELECT DISTINCT s.sale_code, s.sw_code, sw.sw_name, c.group_name, sc.comp_code, sc.comp_name,
/*공급금액*/ (sd.total_supply_price) total_supply_price,
/*판매금액*/ (sd.total_sale_price) total_price,
/*판매이윤*/ (sd.margin) margin
	FROM sale s JOIN view_sale_detail sd ON s.sale_code= sd.sale_code 
   				JOIN software sw ON s.sw_code= sw.sw_code
				JOIN category c ON c.group_code= sw.group_code 
				JOIN delivery del ON del.sw_code= sw.sw_code
				JOIN supply_company sc ON del.comp_code= sc.comp_code;
  
SELECT sw_code, sw_name, group_name, comp_code, comp_name, total_supply_price, total_price, margin 
	FROM view_sw_sale 
	WHERE sw_code="SW001";
	
SELECT sw_code, sw_name, group_name, comp_code, comp_name, total_supply_price, total_price, margin 
	FROM view_sw_sale 
	WHERE sw_code=#{swCode};

	

#### 날짜별 판매현황조회 ####
-- 날짜 주문번호 상호 품명 수량 입금여부 
CREATE VIEW view_sale_by_orderdate AS
SELECT s.order_date, s.sale_code, cl.clnt_code, cl.clnt_name, sw.sw_code, sw.sw_name, s.sale_amount, s.isdeposit
	FROM sale s JOIN client cl ON s.clnt_code = cl.clnt_code 
   				JOIN software sw ON s.sw_code = sw.sw_code;
   
SELECT order_date, sale_code, clnt_code, clnt_name, sw_code, sw_name, sale_amount, isdeposit
	FROM view_sale_by_orderdate 
	WHERE order_date 
	BETWEEN "2009-12-12" AND "2009-12-14" 
	ORDER BY order_date;
   
SELECT order_date, sale_code, clnt_code, clnt_name, sw_code, sw_name, sale_amount, isdeposit
	FROM view_sale_by_orderdate 
	WHERE order_date 
	BETWEEN  #{startDate} AND #{endDate} 
	ORDER BY order_date;
	



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

SELECT group_name, c_salePrice, c_amount 
	FROM view_sale_by_category;


#### SW 전체판매현황 보고서 ####
-- 날짜 분류 품목명 주문번호 주문수량 판매금액
 
CREATE VIEW view_sale_report AS
SELECT s.order_date, c.group_name, sw.sw_code, sw.sw_name, s.sale_code, sale_amount,
/*총 판매금액*/ sd.total_sale_price
	FROM sale s JOIN software sw ON s.sw_code=sw.sw_code 
            	JOIN category c ON sw.group_code= c.group_code
            	JOIN view_sale_detail sd ON sd.sale_code = s.sale_code
	ORDER BY s.order_date DESC;

SELECT order_date, group_name, sw_code, sw_name, sale_code, sale_amount, total_sale_price 
	FROM view_sale_report
	ORDER BY order_date DESC;

-- 총합계
SELECT SUM(total_sale_price) 
	FROM view_sale_report;

            

            
#### 거래명세서 ####
-- 공급회사명 날짜 고객명 품명 단가 주문수량 총판매금액 세금 총납품금액

CREATE VIEW view_bill_list AS
SELECT DISTINCT s.sale_code, sc.comp_code, sc.comp_name, s.order_date, c.clnt_code, c.clnt_name, 
				sw.sw_code, sw.sw_name, s.sale_price, s.sale_amount,
/*총판매금액*/  sd.total_sale_price, 
/*세금*/        sd.tax,   
/*총납품금액*/  sd.tax_saleprice
	FROM supply_company sc JOIN delivery del ON sc.comp_code = del.comp_code 
	                       JOIN software sw ON del.sw_code = sw.sw_code 
	                       JOIN sale s ON sw.sw_code = s.sw_code 
                     	   JOIN client c ON s.clnt_code = c.clnt_code
                     	   JOIN view_sale_detail sd ON sd.sale_code = s.sale_code;
                        
SELECT comp_code, comp_name, order_date, clnt_code, clnt_name, sw_code, sw_name, 
		sale_price, sale_amount, total_sale_price, tax, tax_saleprice
	FROM view_bill_list;
 
-- 총납품금액 합계

SELECT SUM(tax_saleprice) 
	FROM view_bill_list;


#### 그래프출력 ####
-- 주문현황 (고객이름 , 고객별 총주문갯수)

CREATE VIEW view_sale_graph AS 
SELECT c.clnt_code, c.clnt_name, SUM(sale_amount) sale_amount 
	FROM sale s JOIN client c ON s.clnt_code=c.clnt_code
GROUP BY c.clnt_name;
   
SELECT clnt_code, clnt_name, sale_amount 
	FROM view_sale_graph;

SELECT * FROM view_client_sale;
SELECT * FROM view_sw_sale;
SELECT * FROM view_sale_by_orderdate;
SELECT * FROM view_sale_by_category;
SELECT * FROM view_sale_report;
SELECT * FROM view_bill_list;
SELECT * FROM view_sale_detail;
SELECT * FROM view_sale_graph;

/*DROP VIEW view_client_sale;
DROP VIEW view_sw_sale;
DROP VIEW view_sale_by_orderdate;
DROP VIEW view_sale_by_category;
DROP VIEW view_sale_report;
DROP VIEW view_bill_list;
DROP VIEW view_sale_detail;
DROP VIEW view_sale_graph;*/