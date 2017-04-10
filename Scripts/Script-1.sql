select * from view_sale_by_category;

				
				
				#### 고객별 판매현황조회 ####
-- 고객상호명 품목명 주문수량 입금여부 단가 매출금 미수금
CREATE VIEW view_client_sale AS 
SELECT cl.clnt_code, cl.clnt_name, sw.sw_code, sw.sw_name, s.sale_amount, s.isdeposit, s.sale_price,
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
   				
				   