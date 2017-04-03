select * from sale;

select * from client cl left outer join sale s on cl.clnt_code= s.clnt_code;

select * from delivery;
INSERT INTO delivery(del_code, comp_code, sw_code, supply_price, supply_amount, order_date, del_isExist) VALUES
	("DL002", "SC002", "SW002", 30000, 200, now(), TRUE);

select * from sale;
INSERT INTO sale(sale_code, clnt_code, sw_code, sale_amount, 
				isdeposit, order_date, supply_price, sale_price, sale_isExist) VALUES  
	("SL001","CL001","SW002",25, TRUE, "2009-12-13", 25000  , 40000,   TRUE);

select * from software sw left outer join delivery d on sw.sw_code=d.sw_code left outer join supply_company su on d.comp_code=su.comp_code
left outer join sale s on sw.sw_code=s.sw_code left outer join client cl on s.clnt_code=cl.clnt_code left outer join category c on c.group_code= sw.group_code;