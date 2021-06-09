package helpers;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import propertiesReader.PropertiesReader;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;



public class ConnectionDB {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static ConnectionDB DB=new ConnectionDB();
	private PropertiesReader PR= PropertiesReader.getInstances();
	Properties prop=new Properties();
	
	private Connection ConnectionDB() throws ClassNotFoundException {
		try {
			URI dbUri = new URI(System.getenv(PR.prop.getProperty("UrlDB")));

		    String username = dbUri.getUserInfo().split(":")[0];
		    String password = dbUri.getUserInfo().split(":")[1];
		    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

		    return DriverManager.getConnection(dbUrl, username, password);
			
		}catch(SQLException | URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//patron singleton
	public static ConnectionDB getInstances() {
		// TODO Auto-generated method stub
		return DB;
	}
	
	//Id
public int userId(){
		int id=1;
		try {
			this.stmt=this.conn.createStatement();
			this.rs=this.stmt.executeQuery("SELECT MAX(id_usuario) FROM Usuario");
			while(rs.next()) {
				id=rs.getInt(1)+1;
			}
			}catch(Exception e) {
			System.out.println("Error"+e.getMessage());	
			}finally {
				try {
					this.stmt.close();
					this.rs.close();
				}catch(Exception o){	
				}
			}
		return id;
	}

public void regUsuario(String query, int id, String username, String pass ) {
	try {
		this.pstmt=this.conn.prepareStatement(query);
		this.pstmt.setInt(1, id);
		this.pstmt.setString(2, username);
		this.pstmt.setString(3, pass);
		
		this.pstmt.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
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
