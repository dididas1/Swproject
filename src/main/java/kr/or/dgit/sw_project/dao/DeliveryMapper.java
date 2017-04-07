package kr.or.dgit.sw_project.dao;

import java.util.List;

import kr.or.dgit.sw_project.dto.Delivery;

public interface DeliveryMapper {
	List<Delivery> selectDeliveryByAll();
	int insertDeliveryItems(Delivery delivery); 
}
