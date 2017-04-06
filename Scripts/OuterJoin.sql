
#### 고객별 판매현황조회 ####

SELECT cl.clnt_name, sw.sw_name, s.sale_amount, s.isdeposit, sw.sale_price,
/*매출금*/ 	@salePrice  := s.sale_amount * sw.sale_price salePrice, 
/*미수금*/	@receivable := @salePrice * !s.isDeposit receivablePrice
	FROM client cl JOIN sale s ON cl.clnt_code = s.clnt_code
				   JOIN software sw ON s.sw_code = sw.sw_code;

				   
SELECT cl.clnt_name, sw.sw_name, s.sale_amount, s.isdeposit, sw.sale_price,
/*매출금*/ 	@salePrice  := s.sale_amount * sw.sale_price salePrice, 
/*미수금*/	@receivable := @salePrice * !s.isDeposit receivablePrice
	FROM client cl LEFT OUTER JOIN sale s ON cl.clnt_code = s.clnt_code
				   LEFT OUTER JOIN software sw ON s.sw_code = sw.sw_code
	UNION 
SELECT cl.clnt_name, sw.sw_name, s.sale_amount, s.isdeposit, sw.sale_price,
/*매출금*/ 	@salePrice  := s.sale_amount * sw.sale_price salePrice, 
/*미수금*/	@receivable := @salePrice * !s.isDeposit receivablePrice
	FROM client cl RIGHT OUTER JOIN sale s ON cl.clnt_code = s.clnt_code
				   RIGHT OUTER JOIN software sw ON s.sw_code = sw.sw_code;
				 
				  ## 그래프, 날짜별 판매현황, s/w전체 현황
SELECT * FROM sale s
		JOIN software sw ON s.sw_code = sw.sw_code
		JOIN client cl ON cl.clnt_code = s.clnt_code
		JOIN category cat ON sw.group_code = cat.group_code
		JOIN view_sale_detail sd ON s.sale_code = sd.sale_code
	ORDER BY s.sale_code;
DESC view_sale_detail;


SELECT sw.sw_name, del.del_code, s.sale_code, cat.group_name, sc.comp_name, sd.total_supply_price FROM software sw
	JOIN delivery del ON sw.sw_code = del.sw_code
	JOIN supply_company sc ON sc.comp_code = del.comp_code
	JOIN category cat ON sw.group_code = cat.group_code
	JOIN sale s ON sw.sw_code = s.sw_code
	JOIN view_sale_detail sd ON s.sale_code = sd.sale_code;
	
SELECT * FROM software
	;