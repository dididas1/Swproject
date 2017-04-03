select * from sale;

select * from client cl left outer join sale s on cl.clnt_code= s.clnt_code;

select * from software sw left outer join delivery d on sw.sw_code=d.sw_code left outer join supply_company su on d.comp_code=su.comp_code
left outer join sale s on sw.sw_code=s.sw_code left outer join client cl on s.clnt_code=cl.clnt_code left outer join category c on c.group_code= sw.group_code;