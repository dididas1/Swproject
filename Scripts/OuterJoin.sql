
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
	
SELECT * FROM software	;
	
	SELECT * FROM software sw
				LEFT OUTER JOIN delivery del ON sw.sw_code = del.sw_code
				LEFT OUTER JOIN supply_company sc ON sc.comp_code = del.comp_code
				LEFT OUTER JOIN category cat ON sw.group_code = cat.group_code
				LEFT OUTER JOIN sale s ON sw.sw_code = s.sw_code
				LEFT OUTER JOIN view_sale_detail sd ON s.sale_code = sd.sale_code
				LEFT OUTER JOIN client cl ON cl.clnt_code = s.clnt_code
				GROUP BY sw.sw_code;

SELECT DISTINCT 
		s.sale_code, s.clnt_code, s.sw_code,s.sale_amount, s.isdeposit,s.order_date, s.supply_price, s.sale_price, s.sale_isExist,
		sw.sw_code, sw.group_code, sw.sw_name, sw.sale_price, sw.sw_inven, sw.sw_issale,
		sc.comp_code, sc.comp_name, sc.comp_addr, sc.comp_tel, sc.comp_isExist,
		cat.group_code, cat.group_name,
		sd.margin, sd.receivablePrice, sd.sale_code, sd.tax, sd.tax_saleprice, sd.total_sale_price, sd.total_supply_price,
		del.del_code, del.comp_code, del.sw_code, del.supply_price, del.supply_amount, del.order_date, del.del_isExist
	FROM software sw
		LEFT OUTER JOIN delivery del ON sw.sw_code = del.sw_code
		LEFT OUTER JOIN supply_company sc ON sc.comp_code = del.comp_code
		LEFT OUTER JOIN category cat ON sw.group_code = cat.group_code
		LEFT OUTER JOIN sale s ON sw.sw_code = s.sw_code
		LEFT OUTER JOIN view_sale_detail sd ON s.sale_code = sd.sale_code
	ORDER BY s.sale_code;
	
	
	SELECT * FROM sale s 
		JOIN software sw ON s.sw_code = sw.sw_code 
		JOIN view_sale_detail sd ON s.sale_code = sd.sale_code 
		JOIN client cl ON cl.clnt_code = s.clnt_code 
		JOIN category cat ON sw.group_code = cat.group_code
		JOIN delivery del ON s.sw_code = del.sw_code
	GROUP BY s.sale_code
	ORDER BY s.sale_code;
	
	   SELECT DISTINCT sw.sw_code, cat.group_name, sw.sw_name, sw.sale_price
         FROM software sw
         LEFT OUTER JOIN category cat ON sw.group_code = cat.group_code
         order by sw.sw_code
         
SELECT * FROM software;

SELECT DISTINCT sw.sw_code, cat.group_name, sw.sw_name, sw.sale_price ,sw.sw_inven,sw.sw_issale
	FROM software sw
	LEFT OUTER JOIN category cat ON sw.group_code = cat.group_code
	order by sw.sw_code;
	
SELECT * FROM software sw
		JOIN delivery del ON sw.sw_code = del.sw_code
		JOIN supply_company sc ON sc.comp_code = del.comp_code
		JOIN category cat ON sw.group_code = cat.group_code
		JOIN sale s ON sw.sw_code = s.sw_code
		JOIN view_sale_detail sd ON s.sale_code = sd.sale_code
		JOIN client cl ON cl.clnt_code = s.clnt_code
	GROUP BY s.sale_code
	ORDER BY comp_name;
	
	SELECT * FROM sale s 
			JOIN software sw ON s.sw_code = sw.sw_code 
			JOIN view_sale_detail sd ON s.sale_code = sd.sale_code 
			JOIN client cl ON cl.clnt_code = s.clnt_code 
			JOIN category cat ON sw.group_code = cat.group_code 
			ORDER BY order_date desc;