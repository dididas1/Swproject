package kr.or.digt.sw_project.connection;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisSqlSessionFactory {
	private static SqlSessionFactory sqlSesstionFactory;

	public static SqlSessionFactory getSqlSesstionFactory() {
		if(sqlSesstionFactory==null){
			InputStream inputStream;
			try {
				inputStream = Resources.getResourceAsStream("mybatis_config.xml");
				sqlSesstionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sqlSesstionFactory;
	}
	
	public static SqlSession opensesstion(){
		return getSqlSesstionFactory().openSession();
	}
}
