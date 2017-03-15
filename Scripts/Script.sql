-- sw판매관리프로그램
DROP SCHEMA IF EXISTS sw_project;

-- sw판매관리프로그램
CREATE SCHEMA sw_project;

-- 공급회사
CREATE TABLE sw_project.emp (
	empno      INTEGER     NOT NULL, -- 회사번호
	empname    VARCHAR(20) NOT NULL, -- 회사명
	adress     VARCHAR(50) NOT NULL, -- 주소
	emppnumber VARCHAR(20) NOT NULL  -- 전화번호
);

-- 공급회사
ALTER TABLE sw_project.emp
	ADD CONSTRAINT PK_emp -- 공급회사 기본키
		PRIMARY KEY (
			empname -- 회사명
		);

-- 새 테이블
CREATE TABLE sw_project.TABLE (
	empname VARCHAR(20) NULL -- 회사명
);

-- 소프트웨어
CREATE TABLE sw_project.soft (
	softname     VARCHAR(50) NOT NULL, -- 품목명
	empname      VARCHAR(20) NOT NULL, -- 회사명
	softno       INT         NOT NULL, -- 품목번호
	softcategory VARCHAR(10) NOT NULL, -- 분류
	softsupply   INTEGER     NOT NULL, -- 공급가격
	softprice    INTEGER     NOT NULL  -- 판매가격
);

-- 소프트웨어
ALTER TABLE sw_project.soft
	ADD CONSTRAINT PK_soft -- 소프트웨어 기본키
		PRIMARY KEY (
			softname -- 품목명
		);

-- 고객현황
CREATE TABLE sw_project.consumer (
	conname    VARCHAR(20) NOT NULL, -- 상호명
	conno      INT         NOT NULL, -- 고객번호
	conadress  VARCHAR(50) NOT NULL, -- 주소
	conpnumber VARCHAR(20) NOT NULL  -- 전화번호
);

-- 고객현황
ALTER TABLE sw_project.consumer
	ADD CONSTRAINT PK_consumer -- 고객현황 기본키
		PRIMARY KEY (
			conname -- 상호명
		);

-- 판매현황
CREATE TABLE sw_project.sale (
	saleno   INT         NOT NULL, -- 주문번호
	conname  VARCHAR(20) NOT NULL, -- 상호명
	softname VARCHAR(50) NOT NULL, -- 품목명
	conoder  INTEGER     NOT NULL, -- 주문수량
	conin    BOOLEAN     NULL,     -- 입금여부
	condate  DATE        NOT NULL  -- 주문일자
);

-- 새 테이블
ALTER TABLE sw_project.TABLE
	ADD CONSTRAINT FK_emp_TO_TABLE -- 공급회사 -> 새 테이블
		FOREIGN KEY (
			empname -- 회사명
		)
		REFERENCES sw_project.emp ( -- 공급회사
			empname -- 회사명
		);

-- 소프트웨어
ALTER TABLE sw_project.soft
	ADD CONSTRAINT FK_emp_TO_soft -- 공급회사 -> 소프트웨어
		FOREIGN KEY (
			empname -- 회사명
		)
		REFERENCES sw_project.emp ( -- 공급회사
			empname -- 회사명
		);

-- 판매현황
ALTER TABLE sw_project.sale
	ADD CONSTRAINT FK_consumer_TO_sale -- 고객현황 -> 판매현황
		FOREIGN KEY (
			conname -- 상호명
		)
		REFERENCES sw_project.consumer ( -- 고객현황
			conname -- 상호명
		);

-- 판매현황
ALTER TABLE sw_project.sale
	ADD CONSTRAINT FK_soft_TO_sale -- 소프트웨어 -> 판매현황
		FOREIGN KEY (
			softname -- 품목명
		)
		REFERENCES sw_project.soft ( -- 소프트웨어
			softname -- 품목명
		);
		
select * from emp;