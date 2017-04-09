package kr.or.dgit.sw_project.dao;

import java.util.List;

import kr.or.dgit.sw_project.dto.Delivery;

public interface DeliveryMapper {
	List<Delivery> selectDeliveryByAll();
	String lastDeliveryCode();
	int insertDeliveryItems(Delivery delivery); 
	int UpdateItems(Delivery delivery);
	int existDeliveryItem(Delivery delivery);
	Delivery getSuppyPrice(Delivery delivery);
}