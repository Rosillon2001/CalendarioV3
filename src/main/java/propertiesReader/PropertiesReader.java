package propertiesReader;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesReader {

	private static PropertiesReader propR=new PropertiesReader();
	private FileInputStream fin;
	public Properties prop;
	
	private String DBdata[];
	private String DBQuery[];
	
	/*public static void main (String args[]) {
		System.out.println(propR.prop.getProperty("user"));
	}*/
	
	public PropertiesReader() {
		try {
			fin=new FileInputStream("C:\\Users\\rosil\\eclipse-workspace2\\CalendarioV2\\src\\main\\java\\propertiesReader\\Data.properties");
			
			prop=new Properties();
			prop.load(fin);
			
			DBdata= new String[] { prop.getProperty("Driver"), prop.getProperty("UrlDB"), prop.getProperty("user"), prop.getProperty("pass")};
			
			DBQuery= new String[] { prop.getProperty("selectUser"), prop.getProperty("insertUser")};
			
		}catch(Exception e) {
			System.out.println("Error "+e.getMessage());
		}
	}
	
	public static PropertiesReader getInstances() {
		return propR;
	}
	
	public String[] getData() {
		return this.DBdata;
	}
	
	public String[] getQuery() {
		return this.DBQuery;
	}
	
	public String getVal(String valor) {
		return prop.getProperty(valor);
		
	}
}
