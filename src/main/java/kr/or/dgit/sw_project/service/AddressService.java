package kr.or.dgit.sw_project.service;

public class AddressService {
	public static final AddressService instence= new AddressService();
	
	
	public static AddressService getInstence() {
		return instence;
	}


	/*public List<Address> serchSido(Address address){
		try(SqlSession sqlsession = MybatisSqlSessionFactory.opensesstion();){
			AddressImpl postimpl= new AddressImpl(sqlsession);
			return postimpl.serchSido(address);
		}
	}*/
}
