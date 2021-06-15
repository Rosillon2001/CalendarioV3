package helpers;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import propertiesReader.PropertiesReader;
import java.sql.Connection;



public class ConnectionDB {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	
	private static ConnectionDB DB=new ConnectionDB();
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
	
	//------------------------------------------------------MANEJO DE USUARIO------------------------------------------------------------------
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

//actualizar usuario
public void modificar( int id, String username, String pass) {
	try {
		this.pstmt=conn.prepareStatement("UPDATE Usuario SET nombre_usuario=?,clave=? Where id_usuario="+id);
		this.pstmt.setString(1, username);
		this.pstmt.setString(2, pass);
		this.pstmt.executeUpdate();
	}	catch(SQLException e) {
		e.printStackTrace();
	}finally {
		try {
			this.stmt.close();
			this.rs.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}

//obtener el id de usuario de la sesion
public int idSession(String username) {
	int idUs=0;
	try {
		this.stmt=this.conn.createStatement();
		this.rs=this.stmt.executeQuery("select *from usuario where nombre_usuario="+"'"+username+"'");
		while(rs.next()) {
			idUs=rs.getInt("id_usuario");
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return idUs;
}

//obtener el usuario si esta registrado en la DB
public String getUsuario(String username) {
	String user = ""; 
	PropertiesReader PR= PropertiesReader.getInstances();
	Properties prop=new Properties();
	try {
		this.stmt=this.conn.createStatement();
		this.rs=this.stmt.executeQuery(PR.prop.getProperty("selectUser")+"'"+username+"'");
		while(rs.next()) {
		user= rs.getString("nombre_usuario");
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}
	
	return user;
}

//Obtener la clave en la DB para la validacion
public String getClave(String username) {
	String pass = "";
	PropertiesReader PR= PropertiesReader.getInstances();
	Properties prop=new Properties();
	try {
		this.stmt=this.conn.createStatement();
		this.rs=this.stmt.executeQuery(PR.prop.getProperty("selectUser")+"'"+username+"'");
		while(rs.next()) {
		pass=rs.getString("clave");
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}
	
	return pass;
	
}


//-------------------------------------------------------------------------MANEJO DEL CALENDARIO------------------------------------------------------------------
//id del calendario
public int idCal() {
		int id=1;
		try {
			this.stmt=this.conn.createStatement();
			this.rs=this.stmt.executeQuery("SELECT MAX(id_calendario) FROM Calendario");
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


public String nameCalendar(String calendario) {
	String cal =""; 
	PropertiesReader PR= PropertiesReader.getInstances();
	Properties prop=new Properties();
	try {
		this.stmt=this.conn.createStatement();
		this.rs=this.stmt.executeQuery("select *from calendario where nombre_calendario="+"'"+calendario+"'");
		while(rs.next()) {
		cal= rs.getString("nombre_calendario");
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}
	
	return cal;
}
//registro del calendario
public void regCalendar(int id, String nombre, String color) {
	//insert into Usuario values(?,?,?)
	try {
		this.pstmt=this.conn.prepareStatement("insert into Calendario values(?,?,?)");
		this.pstmt.setInt(1, id);
		this.pstmt.setString(2, nombre);
		this.pstmt.setString(3, color);
		
		this.pstmt.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

//vinculacion entre usuario y calendario mediante el registro de acceso
public void accesoEd(int userId, int idCalendar) {
	try {
	this.pstmt=this.conn.prepareStatement("insert into Acceso values(?,?,?)");
	this.pstmt.setInt(1, userId);
	this.pstmt.setInt(2, idCalendar);
	this.pstmt.setString(3, "Editor");
	
	this.pstmt.executeUpdate();
	}catch(SQLException e) {
		e.printStackTrace();
	}
}

//Obtener Calendarios por id
public String getCalendars(int idcal) {
	String AA="";
	try {
		this.stmt=this.conn.createStatement();
		this.rs=this.stmt.executeQuery("SELECT *FROM Calendario where id_calendario="+"'"+ idcal+"'");
		while(rs.next()) {
			AA+="{\"nombre\":\""+rs.getString("nombre_calendario")+"\",\"color\":\""+rs.getString("color")+"\"}";
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		try {
			this.stmt.close();
			this.rs.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	return AA;
}

//Obtener el nombre del calendario
public String getCalendarNames(int idcal) {
	String AA="";
	try {
		this.stmt=this.conn.createStatement();
		this.rs=this.stmt.executeQuery("SELECT *FROM Calendario where id_calendario="+"'"+ idcal+"'");
		while(rs.next()) {
			AA=rs.getString("nombre_calendario");
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		try {
			this.stmt.close();
			this.rs.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	return AA;
}


//metodo para obtener los calendarios de editor
public String OwnCalendars(int userid) {
	String idcalendarios = "";
	try {
		this.stmt=this.conn.createStatement();
		this.rs=this.stmt.executeQuery("SELECT *FROM Acceso where id_usuario="+"'"+userid+"'"+"and privilegios ="+"'Editor'");
		while(rs.next()) {
			idcalendarios+=rs.getInt("id_calendario")+"-";
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		try {
			this.stmt.close();
			this.rs.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	return idcalendarios;
}	

//borrado calendario
public boolean borrarCalendar(int idcalendar) {
	try {
		this.stmt=this.conn.createStatement();
		this.stmt.executeQuery("DELETE FROM Calendario Where id_calendario="+"'"+idcalendar+"'");
		return true;
	}	catch(SQLException e) {
		e.printStackTrace();
		return false;
	}finally {
		try {
			this.stmt.close();
			this.rs.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}



//-----------------------------------------------------------CIERRE DE LA CONEXION--------------------------------------------------------------------------------------------
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
