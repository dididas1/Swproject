CREATE TABLE members(
	mem_id		   VARCHAR(20) NOT NULL,
	mem_name	   VARCHAR(20) NOT NULL,
	mem_password   VARCHAR(20) NOT NULL,
	mem_mail	   VARCHAR(50) NOT NULL,
	mem_permission VARCHAR(10) NOT NULL,
	mem_isExist    BOOLEAN     NOT NULL,
	PRIMARY KEY (mem_id)
);
-- Permission: personnel, manager
-- mem_id, mem_name, mem_password, mem_mail, mem_permission, mem_isExist
INSERT INTO members(mem_id, mem_name, mem_password, mem_mail, mem_permission, mem_isExist)
	VALUES("luuzun","이원준","1234","luuzun@naver.com", "manager", TRUE);

INSERT INTO members(mem_id, mem_name, mem_password, mem_mail, mem_permission, mem_isExist)
	VALUES("dididas","이승우","1234","dididas@naver.com", "personnel", TRUE);

SELECT mem_id, mem_name, mem_password, mem_mail, mem_permission, mem_isExist FROM members;

SELECT mem_id, mem_permission FROM members 
			WHERE mem_id="luuzun" AND mem_password="1234";