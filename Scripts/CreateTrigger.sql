-- 납품 테이블 업데이트시 수량조정
DELIMITER $$
CREATE TRIGGER tri_software_after_update_delivery
    AFTER update ON delivery
    FOR EACH ROW
BEGIN
	IF NEW.del_isExist = FALSE THEN
		UPDATE software SET sw_inven = sw_inven - NEW.supply_amount
		WHERE sw_code= NEW.sw_code;
	ELSEIF NEW.del_isExist = TRUE THEN
		UPDATE software SET sw_inven = sw_inven+new.supply_amount
        WHERE sw_code = NEW.sw_code;
	END IF;
END $$
DELIMITER ;

-- 납품 테이블 인서트시 수량조정
DELIMITER $$
CREATE TRIGGER tri_software_after_insert_delivery
	AFTER INSERT ON delivery
	FOR EACH ROW
BEGIN
	IF NEW.del_isExist = FALSE THEN
		UPDATE software SET sw_inven = sw_inven + NEW.supply_amount
		WHERE sw_code = NEW.sw_code;
    END IF;
END $$
DELIMITER ;

-- 판매테이블 인서트시 수량조절
DELIMITER $$
CREATE TRIGGER tri_software_after_insert_sale
    AFTER insert 
    ON sale
    FOR EACH ROW
BEGIN
	IF NEW.sale_isExist = TRUE THEN
		UPDATE software SET sw_inven = sw_inven - NEW.sale_amount
		WHERE sw_code= NEW.sw_code;
	END IF;
END $$
DELIMITER ;

-- 판매테이블 업데이트시 수량조절
DELIMITER $$
CREATE TRIGGER tri_software_after_update_sale
	AFTER UPDATE ON sale
	FOR EACH ROW
BEGIN
	IF NEW.sale_isExist = FALSE THEN
		UPDATE software SET sw_inven = sw_inven + NEW.sale_amount
		WHERE sw_code = NEW.sw_code;
	ELSEIF NEW.sale_isExist = TRUE THEN
		UPDATE software SET sw_inven = sw_inven - NEW.sale_amount
		WHERE sw_code= NEW.sw_code;
    END IF;
END $$
DELIMITER ;