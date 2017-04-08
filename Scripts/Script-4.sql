


SELECT s.sale_code,sw.sw_name,cl.clnt_name,s.sale_amount,s.order_date,s.isdeposit,sw.sale_price,sd.total_sale_price FROM sale s 
			JOIN software sw ON s.sw_code = sw.sw_code 
			JOIN view_sale_detail sd ON s.sale_code = sd.sale_code 
			JOIN client cl ON cl.clnt_code = s.clnt_code 
			JOIN category cat ON sw.group_code = cat.group_code 
			order by s.sale_code;
			
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
	