package kr.or.dgit.sw_project.dao;

import java.util.List;

import kr.or.dgit.sw_project.dto.Delivery;

public interface DeliveryMapper {
	List<Delivery> selectDeliveryByAll();//all
	String lastDeliveryCode();//마지막코드를 위한거 (필요없어짐)
	int insertDeliveryItems(Delivery delivery); //db table입력
	int UpdateItems(Delivery delivery);//db table 값수정
	int existDeliveryItem(Delivery delivery);//논리삭제 db에서 exist칼럼 true로 변경 
	Delivery getSuppyPrice(Delivery delivery);
}