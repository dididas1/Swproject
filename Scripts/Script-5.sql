
select * from client;

INSERT INTO client(clnt_code,clnt_name,clnt_tel,clnt_addr,clnt_isExist) VALUES ("11","11","11","11",true) ;
select * from client cl left outer join sale s on cl.clnt_code= s.clnt_code;

select * from delivery;
INSERT INTO delivery(del_code, comp_code, sw_code, supply_price, supply_amount, order_date, del_isExist) VALUES
	("DL001", "SC001", "SW001", 30000, 200, now(), TRUE);

select * from sale;
INSERT INTO sale(sale_code, clnt_code, sw_code, sale_amount, 
				isdeposit, order_date, supply_price, sale_price, sale_isExist) VALUES  
	("SL001","CL001","SW002",25, TRUE, "2009-12-13", 25000  , 40000,   TRUE);
	
	delete from sale where sale_code="SL001";

select * from sale s left outer join delivery d on sw.sw_code=d.sw_code left outer join supply_company su on d.comp_code=su.comp_code
left outer join software sw on sw.sw_code=s.sw_code left outer join client cl on s.clnt_code=cl.clnt_code left outer join category c on c.group_code= sw.group_code;

select * from delivery;
select * from sale s left outer join client cl on s.clnt_code= cl.clnt_code left outer join software sw on s.sw_code= sw.sw_code left outer join delivery d on sw.sw_code= d.sw_code
left outer join supply_company su on d.comp_code= su.comp_code;


UPDATE erp.delivery
SET  sw_code=, supply_price=, supply_amount=, order_date=, del_isExist=
WHERE del_code=

