package kr.or.dgit.sw_project.dao;

import java.util.List;

import kr.or.dgit.sw_project.dto.Client;

public interface ClientMapper {
	List<Client> selectClientByAll();
	Client selectByNoClnt(Client client);
	int insetClntItem(Client client);
	int updateClntItem(Client client);
	int existClntItem(Client client);
}
