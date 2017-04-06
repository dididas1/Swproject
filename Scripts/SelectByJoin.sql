#### 고객별 판매현황조회 ####
-- 고객상호명 품목명 주문수량 입금여부 단가 매출금 미수금

SELECT cl.clnt_name, sw.sw_name, s.sale_amount, s.isdeposit, sw.sale_price,
/*매출금*/ 	@salePrice  := s.sale_amount * sw.sale_price salePrice, 
/*미수금*/	@receivable := @salePrice * !s.isDeposit receivablePrice
	FROM client cl JOIN sale s ON cl.clnt_code = s.clnt_code 
				   JOIN software sw ON s.sw_code = sw.sw_code
	WHERE cl.clnt_name="아산시스템";

SELECT cl.clnt_name, sw.sw_name, s.sale_amount, s.isdeposit, sw.sale_price,
/*매출금*/ 	@salePrice  := s.sale_amount * sw.sale_price salePrice, 
/*미수금*/	@receivable := @salePrice * !s.isDeposit receivablePrice
	FROM client cl JOIN sale s ON cl.clnt_code = s.clnt_code 
				   JOIN software sw ON s.sw_code = sw.sw_code
	WHERE cl.clnt_name=#{clntName};



#### 소프트웨어별 판매현황조회 ####
-- 품목명 분류 공급회사명 공급금액 판매금액 판매이윤

SELECT sw.sw_name, ca.group_name, su.comp_name,
/*공급금액*/ @total_supply_price := SUM(s.sale_amount* dv.supply_price) total_supply_price,
/*판매금액*/ @total_price 		 := SUM(s.sale_amount * sw.sale_price) total_price,
/*판매이윤*/ @margin 			 := SUM(s.sale_amount * sw.sale_price - s.sale_amount* dv.supply_price) margin
	FROM software sw JOIN category ca ON sw.group_code = ca.group_code 
					 JOIN delivery dv ON sw.sw_code=dv.sw_code 
					 JOIN supply_company su ON su.comp_code = dv.comp_code
					 JOIN sale s ON s.sw_code = sw.sw_code
	WHERE sw.sw_name ="바람의제국";
	
SELECT sw.sw_name, ca.group_name , su.comp_name,
/*공급금액*/ @total_supply_price := SUM(s.sale_amount* dv.supply_price) total_supply_price,
/*판매금액*/ @total_price 		 := SUM(s.sale_amount * sw.sale_price) total_price,
/*판매이윤*/ @margin 			 := SUM(s.sale_amount * sw.sale_price - s.sale_amount* dv.supply_price) margin
	FROM software sw JOIN category ca ON sw.group_code = ca.group_code 
					 JOIN delivery dv ON sw.sw_code=dv.sw_code 
					 JOIN supply_company su ON su.comp_code= dv.comp_code
					 JOIN sale s ON s.sw_code= sw.sw_code
	WHERE sw.sw_name =#{swname};




#### 날짜별 판매현황조회 ####
-- 날짜 주문번호  상호 품명 수량 입금여부 

SELECT s.order_date, s.sale_code, cl.clnt_name, sw.sw_name, s.sale_amount, s.isdeposit
	FROM sale s JOIN client cl ON s.clnt_code = cl.clnt_code JOIN software sw ON s.sw_code = sw.sw_code
	WHERE s.order_date BETWEEN "2009-12-12" AND "2009-12-14"
	ORDER BY s.sale_code;
	
SELECT s.order_date, s.sale_code, cl.clnt_name, sw.sw_name, s.sale_amount, s.isdeposit
	FROM sale s JOIN client cl ON s.clnt_code = cl.clnt_code JOIN software sw ON s.sw_code = sw.sw_code
	WHERE s.order_date BETWEEN #{date} AND #{date}
	ORDER BY s.sale_code;



	
#### 카테고리별 판매현황조회 ####
-- 그룹이름 총판매가격 총판매수량

SELECT c.group_name,
/*총판매가격*/	@c_salePrice := SUM(s.sale_amount * sw.sale_price) c_salePrice, 
/*총판매수량*/	@c_amount 	 := SUM(s.sale_amount) c_amount
FROM category c JOIN software sw ON c.group_code= sw.group_code 
				JOIN sale s ON sw.sw_code=s.sw_code
GROUP BY c.group_name;




#### SW 전체판매현황 보고서 ####
-- 날짜 분류 품목명 주문번호 주문수량 판매금액
 
SELECT s.order_date, c.group_name, sw.sw_name, s.sale_code, sale_amount,
/*판매금액*/	@t_sale_price := sw.sale_price* s.sale_amount t_sale_price
	FROM sale s JOIN software sw ON s.sw_code=sw.sw_code 
				JOIN category c ON sw.group_code= c.group_code
	ORDER BY s.order_date DESC;


-- 총합계
SELECT @total_sale_price := SUM(sw.sale_price * s.sale_amount) total_sale_price
	FROM sale s JOIN software sw ON s.sw_code=sw.sw_code 
				JOIN category c ON sw.group_code= c.group_code;

				

				
#### 거래명세서 ####
-- 공급회사명 날짜 고객명 품명 단가 주문수량 총판매금액 세금 총납품금액

SELECT su.comp_name, s.order_date, c.clnt_name, sw.sw_name, sw.sale_price, s.sale_amount,
/*총판매금액*/	@total_saleprice := sw.sale_price * s.sale_amount total_sale_price,
/*세금*/		@tax 			 := @total_saleprice * 0.1 tax,
/*총납품금액*/	@tax_saleprice 	 := @tax + @total_saleprice tax_saleprice
	FROM supply_company su JOIN delivery dl ON su.comp_code = dl.comp_code 
						   JOIN software sw ON dl.sw_code   = sw.sw_code 
						   JOIN sale s 		ON sw.sw_code   = s.sw_code 
						   JOIN client c 	ON s.clnt_code  = c.clnt_code
	ORDER BY su.comp_name;
 
-- 총납품금액 합계
SELECT @tax_saleprice := SUM(sw.sale_price* s.sale_amount) * 1.1 tax_saleprice
	FROM supply_company su JOIN delivery dl ON su.comp_code = dl.comp_code 
						   JOIN software sw ON dl.sw_code = sw.sw_code 
						   JOIN sale s ON sw.sw_code = s.sw_code 
						   JOIN client c ON s.clnt_code = c.clnt_code ;

						   


#### 그래프출력 ####
-- 주문현황 (고객이름 , 고객별 총주문갯수)

SELECT c.clnt_name, SUM(sale_amount) 
	FROM sale s JOIN client c ON s.clnt_code=c.clnt_code
GROUP BY c.clnt_name;