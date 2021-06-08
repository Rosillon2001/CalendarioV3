package helpers;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import propertiesReader.PropertiesReader;
import java.sql.Connection;



public class ConnectionDB {

	private static ConnectionDB DB=new ConnectionDB();
	private Connection conn;

	
	private PropertiesReader PR= PropertiesReader.getInstances();
	Properties prop=new Properties();
	
	private ConnectionDB() {
		try {
			Class.forName(PR.prop.getProperty("Driver"));
			this.conn=DriverManager.getConnection(PR.prop.getProperty("UrlDB"), PR.prop.getProperty("user"), PR.prop.getProperty("pass"));
			System.out.print("Conexion establecida con la DB");
			
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	//patron singleton
	public static ConnectionDB getInstances() {
		// TODO Auto-generated method stub
		return DB;
	}

	//cerrar conexion con la base de datos
		public void dbClose() {
			try {
				this.conn.close();
				System.out.println("Conexion cerrada");
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	
}
