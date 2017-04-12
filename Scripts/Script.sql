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
