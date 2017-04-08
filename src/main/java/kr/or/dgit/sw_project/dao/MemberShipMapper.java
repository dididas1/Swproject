package kr.or.dgit.sw_project.dao;

import java.util.List;

import kr.or.dgit.sw_project.dto.Client;
import kr.or.dgit.sw_project.dto.Members;

public interface MemberShipMapper {
	List<Members> selectMembersByAll();
	Members selectMembersByID(Members members);
	int insetMembersItem(Members members);
	int updateMembersItem(Members members);
	int existMembersItem(Members members);
	Members selectMembersForLogIn(Members members);
}