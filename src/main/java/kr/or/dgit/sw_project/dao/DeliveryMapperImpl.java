package kr.or.dgit.sw_project.dao;
import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.sw_project.dto.Delivery;

public class DeliveryMapperImpl implements DeliveryMapper{
	private SqlSession sqlSession;
	private static final Log log = LogFactory.getLog(DeliveryMapperImpl.class);
	private String nameSpace = "kr.or.dgit.sw_project.dao.DeliveryMapper.";
	
	public DeliveryMapperImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<Delivery> selectDeliveryByAll() {
		log.debug("selectDeliveryByAll()");
		return sqlSession.selectList(nameSpace + "selectDeliveryByAll");
	}

	@Override
	public Delivery getSuppyPrice(Delivery delivery) {
		log.debug("getSuppyPrice()");
		return sqlSession.selectOne(nameSpace + "getSuppyPrice",delivery);
	}
}
