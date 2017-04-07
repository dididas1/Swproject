show TABLEs;
DROP TABLE address;
CREATE TABLE Address(
	zipcode   char(5) NULL,
	sido 	  varchar(20) NULL,
	sigungu   varchar(20) NULL,
	doro 	  varchar(20) NULL,
	building1 int(5) NULL,
	building2 int(5) NULL
);

LOAD DATA LOCAL INFILE 'D:/Address/세종특별자치시.txt' 
INTO TABLE Address   
CHARACTER SET 'euckr'  
fields TERMINATED BY '|' 
IGNORE 1 LINES 
(@zipcode, @sido, @d, @sigungu , @d, @d, @d, @d, @doro, @d, @d, @building1, @building2, @d, @d, @d, @d, @d, @d ,@d, @d, @d, @d, @d, @d, @d) 
SET zipcode=@zipcode, sido=@sido, sigungu=@sigungu, doro=@doro, building1=@building1, building2=@building2;

LOAD data LOCAL INFILE 'D:/Address/강원도.txt' 
INTO TABLE Address   
CHARACTER SET 'euckr'  
fields TERMINATED BY '|' 
IGNORE 1 LINES
(@zipcode, @sido, @d, @sigungu , @d, @d, @d, @d, @doro, @d, @d, @building1, @building2, @d, @d, @d, @d, @d, @d ,@d, @d, @d, @d, @d, @d, @d) 
SET zipcode=@zipcode, sido=@sido, sigungu=@sigungu, doro=@doro, building1=@building1, building2=@building2;

LOAD DATA LOCAL INFILE 'D:/Address/경기도.txt' 
INTO TABLE Address 
CHARACTER SET 'euckr'  
fields TERMINATED BY '|' 
IGNORE 1 LINES 
(@zipcode, @sido, @d, @sigungu , @d, @d, @d, @d, @doro, @d, @d, @building1, @building2, @d, @d, @d, @d, @d, @d ,@d, @d, @d, @d, @d, @d, @d) 
set zipcode=@zipcode, sido=@sido, sigungu=@sigungu, doro=@doro, building1=@building1, building2=@building2;

LOAD data LOCAL INFILE 'D:/Address/경상남도.txt' 
INTO TABLE Address   
CHARACTER SET 'euckr'  
fields TERMINATED BY '|' 
IGNORE 1 LINES 
(@zipcode, @sido, @d, @sigungu , @d, @d, @d, @d, @doro, @d, @d, @building1, @building2, @d, @d, @d, @d, @d, @d ,@d, @d, @d, @d, @d, @d, @d) 
SET zipcode=@zipcode, sido=@sido, sigungu=@sigungu, doro=@doro, building1=@building1, building2=@building2;

LOAD data LOCAL INFILE 'D:/Address/경상북도.txt' 
INTO TABLE Address   
CHARACTER SET 'euckr'  
fields TERMINATED BY '|' 
IGNORE 1 LINES 
(@zipcode, @sido, @d, @sigungu , @d, @d, @d, @d, @doro, @d, @d, @building1, @building2, @d, @d, @d, @d, @d, @d ,@d, @d, @d, @d, @d, @d, @d) 
SET zipcode=@zipcode, sido=@sido, sigungu=@sigungu, doro=@doro, building1=@building1, building2=@building2;

LOAD DATA LOCAL INFILE 'D:/Address/광주광역시.txt' 
INTO TABLE Address   
CHARACTER SET 'euckr'  
fields TERMINATED BY '|' 
IGNORE 1 LINES
(@zipcode, @sido, @d, @sigungu , @d, @d, @d, @d, @doro, @d, @d, @building1, @building2, @d, @d, @d, @d, @d, @d ,@d, @d, @d, @d, @d, @d, @d) 
SET zipcode=@zipcode, sido=@sido, sigungu=@sigungu, doro=@doro, building1=@building1, building2=@building2;

LOAD data LOCAL INFILE 'D:/Address/대구광역시.txt' 
INTO TABLE Address   
character set 'euckr'  
fields TERMINATED BY '|' 
IGNORE 1 LINES
(@zipcode, @sido, @d, @sigungu , @d, @d, @d, @d, @doro, @d, @d, @building1, @building2, @d, @d, @d, @d, @d, @d ,@d, @d, @d, @d, @d, @d, @d) 
SET zipcode=@zipcode, sido=@sido, sigungu=@sigungu, doro=@doro, building1=@building1, building2=@building2;

LOAD data LOCAL INFILE 'D:/Address/부산광역시.txt' 
INTO TABLE Address   
CHARACTER SET 'euckr'  
fields TERMINATED BY '|' 
IGNORE 1 LINES 
(@zipcode, @sido, @d, @sigungu , @d, @d, @d, @d, @doro, @d, @d, @building1, @building2, @d, @d, @d, @d, @d, @d ,@d, @d, @d, @d, @d, @d, @d) 
SET zipcode=@zipcode, sido=@sido, sigungu=@sigungu, doro=@doro, building1=@building1, building2=@building2;

LOAD DATA LOCAL INFILE 'D:/Address/서울특별시.txt' 
INTO TABLE Address   
CHARACTER set 'euckr'  
fields TERMINATED BY '|' 
IGNORE 1 LINES 
(@zipcode, @sido, @d, @sigungu , @d, @d, @d, @d, @doro, @d, @d, @building1, @building2, @d, @d, @d, @d, @d, @d ,@d, @d, @d, @d, @d, @d, @d) 
SET zipcode=@zipcode, sido=@sido, sigungu=@sigungu, doro=@doro, building1=@building1, building2=@building2;

LOAD DATA LOCAL INFILE 'D:/Address/울산광역시.txt' 
INTO TABLE Address   
CHARACTER SET 'euckr'  
fields TERMINATED BY '|' 
IGNORE 1 LINES 
(@zipcode, @sido, @d, @sigungu , @d, @d, @d, @d, @doro, @d, @d, @building1, @building2, @d, @d, @d, @d, @d, @d ,@d, @d, @d, @d, @d, @d, @d) 
SET zipcode=@zipcode, sido=@sido, sigungu=@sigungu, doro=@doro, building1=@building1, building2=@building2;

LOAD DATA LOCAL INFILE 'D:/Address/인천광역시.txt' 
INTO TABLE Address   
CHARACTER SET 'euckr'  
fields TERMINATED BY '|' 
IGNORE 1 LINES
(@zipcode, @sido, @d, @sigungu , @d, @d, @d, @d, @doro, @d, @d, @building1, @building2, @d, @d, @d, @d, @d, @d ,@d, @d, @d, @d, @d, @d, @d) 
SET zipcode=@zipcode, sido=@sido, sigungu=@sigungu, doro=@doro, building1=@building1, building2=@building2;

LOAD DATA LOCAL INFILE 'D:/Address/전라남도.txt' 
INTO TABLE Address   
CHARACTER SET 'euckr'  
fields TERMINATED BY '|' 
IGNORE 1 LINES 
(@zipcode, @sido, @d, @sigungu , @d, @d, @d, @d, @doro, @d, @d, @building1, @building2, @d, @d, @d, @d, @d, @d ,@d, @d, @d, @d, @d, @d, @d) 
SET zipcode=@zipcode, sido=@sido, sigungu=@sigungu, doro=@doro, building1=@building1, building2=@building2;

LOAD DATA LOCAL INFILE 'D:/Address/전라북도.txt' 
INTO TABLE Address   
CHARACTER SET 'euckr'  
fields TERMINATED BY '|' 
IGNORE 1 LINES 
(@zipcode, @sido, @d, @sigungu , @d, @d, @d, @d, @doro, @d, @d, @building1, @building2, @d, @d, @d, @d, @d, @d ,@d, @d, @d, @d, @d, @d, @d) 
SET zipcode=@zipcode, sido=@sido, sigungu=@sigungu, doro=@doro, building1=@building1, building2=@building2;

LOAD DATA LOCAL INFILE 'D:/Address/제주특별자치도.txt' 
INTO TABLE Address   
CHARACTER SET 'euckr'  
fields TERMINATED BY '|' 
IGNORE 1 LINES
(@zipcode, @sido, @d, @sigungu , @d, @d, @d, @d, @doro, @d, @d, @building1, @building2, @d, @d, @d, @d, @d, @d ,@d, @d, @d, @d, @d, @d, @d) 
SET zipcode=@zipcode, sido=@sido, sigungu=@sigungu, doro=@doro, building1=@building1, building2=@building2;

LOAD data LOCAL INFILE 'D:/Address/충청남도.txt' 
INTO TABLE Address   
CHARACTER SET 'euckr'  
fields TERMINATED BY '|' 
IGNORE 1 LINES
(@zipcode, @sido, @d, @sigungu , @d, @d, @d, @d, @doro, @d, @d, @building1, @building2, @d, @d, @d, @d, @d, @d ,@d, @d, @d, @d, @d, @d, @d) 
SET zipcode=@zipcode, sido=@sido, sigungu=@sigungu, doro=@doro, building1=@building1, building2=@building2;

LOAD data LOCAL INFILE 'D:/Address/충청북도.txt' 
INTO TABLE Address   
CHARACTER SET 'euckr'  
fields TERMINATED by '|' 
IGNORE 1 LINES 
(@zipcode, @sido, @d, @sigungu , @d, @d, @d, @d, @doro, @d, @d, @building1, @building2, @d, @d, @d, @d, @d, @d ,@d, @d, @d, @d, @d, @d, @d) 
SET zipcode=@zipcode, sido=@sido, sigungu=@sigungu, doro=@doro, building1=@building1, building2=@building2;

LOAD DATA LOCAL INFILE 'D:/Address/대전광역시.txt' 
INTO TABLE Address   
CHARACTER SET 'euckr'  
fields TERMINATED BY '|' 
IGNORE 1 LINES 
(@zipcode, @sido, @d, @sigungu , @d, @d, @d, @d, @doro, @d, @d, @building1, @building2, @d, @d, @d, @d, @d, @d ,@d, @d, @d, @d, @d, @d, @d) 
SET zipcode=@zipcode, sido=@sido, sigungu=@sigungu, doro=@doro, building1=@building1, building2=@building2;

CREATE INDEX idx_Address_sido 	   ON Address(sido);
CREATE INDEX idx_Address_sigungu   ON Address(sigungu);
CREATE INDEX idx_Address_doro	   ON Address(doro);
CREATE INDEX idx_Address_building1 ON Address(building1);
CREATE INDEX idx_Address_building2 ON Address(building2);

SELECT * FROM Address WHERE sido='서울특별시';

SELECT zipcode, sido, sigungu, doro, building1, building2	
	FROM Address WHERE sido LIKE '%대구%' AND doro LIKE '%산격로%';

SELECT zipcode, sido, sigungu, doro, building1, building2 
			FROM Address 
			WHERE sido = '대구광역시' AND doro = '상화로';
			
SELECT zipcode, sido, sigungu, doro, building1, building2	
	FROM Address WHERE sido LIKE '%대구%' AND doro LIKE '%상화로%' AND building1 = '70' AND building2 = 0;
	
SELECT zipcode, sido, sigungu, doro, building1, building2   
	FROM Address     
	
	SELECT * FROM client
