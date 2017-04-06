



SELECT DISTINCT s.sale_code , s.clnt_code,s.sw_code,s.sale_amount,s.isdeposit,s.order_date,s.supply_price,s.sale_price,s.sale_isExist,
			sw.sw_code,sw.group_code,sw.sw_name,sw.sale_price,sw.sw_inven,sw.sw_issale,
			sc.comp_code,sc.comp_name,sc.comp_addr,sc.comp_tel,sc.comp_isExist,
			cat.group_code,cat.group_name,
			sd.margin,sd.receivablePrice,sd.sale_code,sd.tax,sd.tax_saleprice,sd.total_sale_price,sd.total_supply_price
			FROM software sw
			LEFT OUTER JOIN delivery del ON sw.sw_code = del.sw_code
			LEFT OUTER JOIN supply_company sc ON sc.comp_code = del.comp_code
			LEFT OUTER JOIN category cat ON sw.group_code = cat.group_code
			LEFT OUTER JOIN sale s ON sw.sw_code = s.sw_code
			LEFT OUTER JOIN view_sale_detail sd ON s.sale_code = sd.sale_code;
			
