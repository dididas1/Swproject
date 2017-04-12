package kr.or.dgit.sw_project.initsetting;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class InitSettingService {
	public void initSetting(int swt, int init) {
		Dao dao = Dao.getInstance();
		try {
			if(init==1){// 초기화
				dao.getUpdateResult("drop database if exists " + Config.DB_NAME);
				dao.getUpdateResult("create  database if not exists " +  Config.DB_NAME);
				dao.getUpdateResult("use " + Config.DB_NAME);
				for(int i=0;i<Config.CREATE_SQL_TABLE.length;i++){
					dao.getUpdateResult(Config.CREATE_SQL_TABLE[i]);
					System.err.println(Config.TABLE_NAME[i]+"Table 생성완료");
				}
				for(int i=0;i<Config.CREATE_VIEW.length;i++){
					dao.getUpdateResult(Config.CREATE_VIEW[i]);
					System.out.println("View 생성완료");
				}
				for(int i=0;i<Config.CRETE_TRIGGER.length;i++){
					dao.getUpdateResult(Config.CRETE_TRIGGER[i]);
					System.err.println("Trigger 생성완료");	
				}
				dao.getUpdateResult(Config.CREATE_ADMIN);
				loadPostData();
				createIndex();
				
				if(swt==1){// 복원
					for(int i=0 ; i<Config.TABLE_NAME.length ; i++){
						loadTableData(i); // BackupFiles폴더에 있는 파일들을 가져와 테이블에 삽입
					}
					JOptionPane.showMessageDialog(null, "복원 완료");
				}
				if(swt==0 && init==1){
					JOptionPane.showMessageDialog(null, "초기화 완료");
				}
			}else{
				File file = new File(Config.EXPORT_IMPORT_DIR);// 현재 작업하고 있는 프로젝트 경로안의 BackupFiles폴더
				File[] fies = file.listFiles(); // BackupFiles 안에 있는 파일들을 배열에 넣음
				
				if(file.exists()==false){ // 폴더 존재여부
					file.mkdir(); // 없다면 폴더생성
				}
				try{ // BackupFiles 안에 파일이 하나도 없는지 체크
					for(File f : fies){ // BackupFiles 안에 있는 파일들을 하나씩 검사
						if(f.exists()){ // 안에 파일이 존재한다면
							f.delete(); // 파일을 지움
						}
					}
				}catch(NullPointerException e){
				}finally{
					for(int i=0 ; i<Config.CREATE_SQL_TABLE.length ; i++){
						BackupTableData(i); // BackupFiles에 있는 파일안의 데이터를 가져와 DB테이블에 삽입
					}
					JOptionPane.showMessageDialog(null, "백업 완료");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadTableData(int tables){// 파일 복원
		File file = new File(Config.EXPORT_IMPORT_DIR+Config.TABLE_NAME[tables]);
		String sql = "load data local infile '%s' "
					+"into table "+Config.TABLE_NAME[tables]+" "
					+"character set 'UTF8' "
					+"fields terminated by ',' "
					+"lines terminated by '\r\n'";
		
		executeImportData(String.format(sql,file.getAbsolutePath().replace("\\", "/")), file.getName());
	}
	
	public void BackupTableData(int tables){// 파일 백업
		String sql = "select * from "+Config.TABLE_NAME[tables];
		Connection con = DBCon.getConnection(Config.URL+Config.DB_NAME,Config.USER,Config.PWD );
		try(PreparedStatement pstmt = con.prepareStatement(sql); ResultSet rs = pstmt.executeQuery();){
			StringBuilder sb = new StringBuilder();
			int colCnt = rs.getMetaData().getColumnCount();
			while(rs.next()){
				for(int i=1 ; i<=colCnt ; i++){
					Object obj = rs.getObject(i);
					if(obj.equals(true)){
						obj=1;
					}else if(obj.equals(false)){
						obj=0;
					}
					sb.append(obj+",");
				}
				sb.replace(sb.length()-1, sb.length(), "");
				sb.append("\r\n");
			}
			System.out.println(sb.toString());
			
			try(BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(Config.EXPORT_IMPORT_DIR+Config.TABLE_NAME[tables]));
					OutputStreamWriter osw = new OutputStreamWriter(bw, "UTF-8")){
				osw.write(sb.toString());
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void executeImportData(String sql, String tableName) {
		Statement stmt = null;
		try {
			Connection con = DBCon.getConnection(Config.URL+Config.DB_NAME,Config.USER,Config.PWD);
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
	
	private void loadPostData(){
		File file = new File(Config.ADDRESS_IMPORT_DIR);
		File[] fileNames = file.listFiles();
		String sql = "LOAD data LOCAL INFILE '%s' INTO table  address   character set 'euckr'  fields TERMINATED by '|' IGNORE 1 lines "
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
	
}