package propertiesReader;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesReader {

	private static PropertiesReader propR=new PropertiesReader();
	private FileInputStream fin;
	public Properties prop;
	
	private String DBadd;
	private String DBselect;
	
	/*public static void main (String args[]) {
		System.out.println(propR.prop.getProperty("user"));
	}*/
	
	public PropertiesReader() {
		try {
			
			//fin=new FileInputStream("\\CalendarioV3\\src\\main\\java\\propertiesReader\\Data.properties");
			prop=new Properties();
			prop.load(getClass().getResourceAsStream("/propertiesReader/Data.properties"));
			
			DBadd= new String(prop.getProperty("insertUser"));
			
			DBselect= new String (prop.getProperty("selectUser"));
			
		}catch(Exception e) {
			System.out.println("Error "+e.getMessage());
		}
	}
	
	public static PropertiesReader getInstances() {
		return propR;
	}
	
	public String add() {
		return this.DBadd;
	}
	
	public String select() {
		return this.DBselect;
	}
	
	public String getVal(String valor) {
		return prop.getProperty(valor);
		
	}
}
