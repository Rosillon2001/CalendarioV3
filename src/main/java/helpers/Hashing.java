package helpers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {

	public Hashing() {
		
	}
	
	public String getHash(String pass) {
		MessageDigest MD=null;
		try {
			MD=MessageDigest.getInstance("SHA-256");
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		byte[] hash = MD.digest(pass.getBytes());
		StringBuffer sb = new StringBuffer();
		    
		for(byte b : hash) {        
			sb.append(String.format("%02x", b));
		}
		    
		return sb.toString();
	}
	
}
