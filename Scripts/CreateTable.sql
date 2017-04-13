DROP DATABASE sw_project;
CREATE DATABASE sw_project;
USE sw_project;

-- 거래회사
-- clnt_code, clnt_name, clnt_addr, clnt_tel, clnt_isExist
CREATE TABLE client (
	clnt_code    VARCHAR(6)  NOT NULL, 
	clnt_name    VARCHAR(20) NOT NULL,
	clnt_addr    VARCHAR(50) NULL,
	clnt_tel     VARCHAR(15) NULL,
	clnt_isExist BOOLEAN     NOT NULL,
	PRIMARY KEY (clnt_code)
);

-- 공급회사
-- comp_code, comp_name, comp_addr, comp_tel, comp_isExist
CREATE TABLE supply_company (
	comp_code    VARCHAR(6)  NOT NULL,
	comp_name    VARCHAR(20) NOT NULL,
	comp_addr    VARCHAR(50) NULL,
	comp_tel     VARCHAR(15) NULL,
	comp_isExist BOOLEAN     NOT NULL,
	PRIMARY KEY (comp_code)
);

-- 분류
-- group_code, group_name
CREATE TABLE category (
	group_code VARCHAR(2)  NOT NULL,   
	group_name VARCHAR(20) NOT NULL,
	PRIMARY KEY (group_code)
);

-- 소프트웨어
-- sw_code, group_code, sw_name, sale_price, sw_inven, sw_img, sw_issale  
CREATE TABLE software (
	sw_code    VARCHAR(6)   NOT NULL,
	group_code VARCHAR(6)   NOT NULL,
	sw_name    VARCHAR(50)  NOT NULL,
	sale_price INTEGER      NOT NULL,
	sw_inven   INTEGER      NOT NULL,
	sw_img	   BLOB			NOT NULL,
	sw_issale  BOOLEAN      NOT NULL,
	PRIMARY KEY (sw_code),
	FOREIGN KEY (group_code) 
		REFERENCES category(group_code)
		ON UPDATE CASCADE
);

-- 납품
-- del_code, comp_code, sw_code, supply_price, supply_amount, order_date, del_isExist
CREATE TABLE delivery (
	del_code 	  VARCHAR(6) NOT NULL,
	comp_code 	  VARCHAR(6) NOT NULL,
	sw_code   	  VARCHAR(6) NOT NULL,
	supply_price  INTEGER  	 NOT NULL,
	supply_amount INTEGER	 NOT NULL,
	order_date 	  DATE       NOT NULL, 
	del_isExist   BOOLEAN	 NOT NULL,
	PRIMARY KEY (del_code),
	FOREIGN KEY (comp_code) 
		REFERENCES supply_company (comp_code)
		ON UPDATE CASCADE,
	FOREIGN KEY (sw_code)   
		REFERENCES software (sw_code)
		ON UPDATE CASCADE
);

-- 거래내역
-- sale_code, clnt_code, sw_code, sale_amount, isdeposit, order_date, supply_price, sale_price, sale_delete
CREATE TABLE sale (
	sale_code    VARCHAR(6) NOT NULL,
	clnt_code    VARCHAR(6) NOT NULL,
	sw_code      VARCHAR(6) NOT NULL,
	sale_amount  INTEGER    NOT NULL,
	isdeposit    BOOLEAN    NOT NULL,
	order_date   DATE       NOT NULL,
	supply_price INTEGER    NOT NULL,
	sale_price   INTEGER    NOT NULL, 
	sale_isExist BOOLEAN    NOT NULL,
	PRIMARY KEY (sale_code),
	FOREIGN KEY (clnt_code) 
		REFERENCES client (clnt_code)
		ON UPDATE CASCADE,
	FOREIGN KEY (sw_code) 
		REFERENCES software (sw_code)
		ON UPDATE CASCADE
);

#### Create View View_Sale_Detail####
CREATE VIEW view_sale_detail AS
	SELECT sale_code,
/*총판매금액*/	sale_price * sale_amount AS total_sale_price,
/*총공급금액*/	supply_price * sale_amount AS total_supply_price,
/*마진액*/		(sale_price * sale_amount)/*총공급금액*/ - (supply_price*sale_amount)/*총판매금액*/	 AS margin, 	
/*세금*/		(sale_price * sale_amount) * 0.1 AS tax,
/*총납품금액*/	((sale_price * sale_amount) * 0.1) + (sale_price * sale_amount) AS tax_saleprice,
/*미수금*/		(sale_price * sale_amount) * !isDeposit AS receivablePrice
	FROM sale;