package kr.or.dgit.sw_project.filessetting;

public class Config {
	public static final String DB_NAME = "sw_project";
	public static final String USER   = "root";
	public static final String PWD    = "rootroot";
	public static final String URL    = "jdbc:mysql://localhost:3306/";
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	
	public static final String[] TABLE_NAME = {"post","product"};
	public static final String IMPORT_DIR = System.getProperty("user.dir")+ "\\DataFiles\\";
}
