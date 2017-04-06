create table if not exists address(
	zipcode char(5) null,
	sido varchar(20) null,
	sigungu varchar(20) null,
	doro varchar(20) null,
	building1 int(5) null,
	building2 int(5) null
);

SELECT clnt_code, clnt_name, clnt_addr, clnt_tel, clnt_isExist 
			FROM client
			where clnt_isExist= true;