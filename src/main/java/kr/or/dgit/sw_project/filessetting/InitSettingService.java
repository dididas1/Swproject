package kr.or.dgit.sw_project.filessetting;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class InitSettingService {
	public void initSetting() {
		try {
			loadPostData(1);
			JOptionPane.showMessageDialog(null, "복원 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadPostData(int tables){
		File file = new File(Config.IMPORT_DIR);
		File[] fileNames = file.listFiles();
		String sql = "load data local infile '%s' "
					+"into table "+Config.TABLE_NAME[tables]+" "
					+"character set 'UTF8' "
					+"fields terminated by ',' "
					+"lines terminated by '\r\n'";
		for(File f:fileNames){
			executeImportData(String.format(sql,f.getAbsolutePath().replace("\\", "/")), f.getName());
		}
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
