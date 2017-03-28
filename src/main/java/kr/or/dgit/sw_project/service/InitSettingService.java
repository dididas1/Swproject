package kr.or.dgit.sw_project.service;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import kr.or.dgit.sw_project.dao.Dao;
import kr.or.dgit.sw_project.utill.Config;
import kr.or.dgit.sw_project.utill.DBCon;
import kr.or.dgit.sw_project.utill.JdbcUtil;

public class InitSettingService {
	public void initSetting() {
		Dao dao = Dao.getInstance();
		try {
			dao.getUpdateResult("drop database if exists " + Config.DB_NAME);
			dao.getUpdateResult("create  database if not exists " +  Config.DB_NAME);
			dao.getUpdateResult("use " + Config.DB_NAME);
			for(int i=0;i<Config.CREATE_SQL_TABLE.length;i++){
				dao.getUpdateResult(Config.CREATE_SQL_TABLE[i]);
			}
			/*loadPostData();
			createIndex();*/
			JOptionPane.showMessageDialog(null, "초기화 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadPostData(){
		File file = new File(Config.IMPORT_DIR);
		File[] fileNames = file.listFiles();
		String sql = "LOAD data LOCAL INFILE '%s' INTO table  post   character set 'euckr'  fields TERMINATED by '|' IGNORE 1 lines "
				+ "(@zipcode, @sido, @d, @sigungu , @d, @d, @d, @d, @doro, @d, @d, @building1, @building2, @d, @d, @d, @d, @d, @d ,@d, @d, @d, @d, @d, @d, @d) "
				+ "set zipcode=@zipcode, sido=@sido, sigungu=@sigungu, doro=@doro, building1=@building1, building2=@building2";
		for(File f:fileNames){
			executeImportData(String.format(sql,f.getAbsolutePath().replace("\\", "/")), f.getName());
		}
		
	}
	
	

	private void createIndex() {
		System.out.printf("Index 생성 중 ~~!%n");
		for (int i = 0; i < Config.CREATE_INDEX.length; i++) {
			try {
				Dao.getInstance().getUpdateResult(Config.CREATE_INDEX[i]);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		System.out.printf("Index 생성 완료 ~~!%n");
	}
	
	protected void executeImportData(String sql, String tableName) {
		Statement stmt = null;
		try {
			Connection con = DBCon.getConnection(Config.URL+Config.DB_NAME,Config.USER,Config.PWD );
			stmt = con.createStatement();
			stmt.execute(sql);
			System.out.println(sql);
			System.out.printf("Import Table(%s) %d Rows Success! %n",tableName, stmt.getUpdateCount());
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				System.err.println("중복데이터 존재");
			}
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
		}

	}
}
