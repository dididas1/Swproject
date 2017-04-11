package kr.or.dgit.sw_project.filessetting;

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
	public void initSetting(int swt) {
		Dao dao = Dao.getInstance();
		try {
			if(swt==1){
				dao.getUpdateResult("drop database if exists " + Config.DB_NAME);
				dao.getUpdateResult("create  database if not exists " +  Config.DB_NAME);
				dao.getUpdateResult("use " + Config.DB_NAME);
				for(int i=0 ; i<Config.CREATE_TABLES.length ; i++){
					dao.getUpdateResult(Config.CREATE_TABLES[i]);
				}
				dao.getUpdateResult(Config.CREATE_VIEW);
				for(int i=0 ; i<Config.TABLE_NAME.length ; i++){
					loadTableData(i);
				}
				JOptionPane.showMessageDialog(null, "복원 완료");
			}else{
				for(int i=0 ; i<Config.CREATE_TABLES.length ; i++){
					BackupTableData(i);
				}
				JOptionPane.showMessageDialog(null, "백업 완료");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadTableData(int tables){
		File file = new File(Config.IMPORT_DIR+Config.TABLE_NAME[tables]);
		String sql = "load data local infile '%s' "
					+"into table "+Config.TABLE_NAME[tables]+" "
					+"character set 'UTF8' "
					+"fields terminated by ',' "
					+"lines terminated by '\r\n'";
		
		executeImportData(String.format(sql,file.getAbsolutePath().replace("\\", "/")), file.getName());
	}
	
	public void BackupTableData(int tables){
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
			
			try(BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(Config.EXPORT_DIR+Config.TABLE_NAME[tables]));
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
}