package waes.test;

import org.apache.tomcat.util.codec.binary.Base64;

public class Encoder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		String str = "HELLO WORLD WELAY LOG ";
		byte[]   bytesEncoded = Base64.encodeBase64(str .getBytes());
		System.out.println("ecncoded value is " + new String(bytesEncoded ));
		String s ="TXVzYXdhcg=="; 
		// Decode data on other side, by processing encoded data
		byte[] valueDecoded= Base64.decodeBase64(s );
		System.out.println("Decoded value is " + new String(valueDecoded));
		
		
	}

}
