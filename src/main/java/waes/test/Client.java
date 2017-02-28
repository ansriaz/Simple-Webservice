package waes.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Client {

	public static void main(String[] args) throws MalformedURLException, IOException {

		/**********************
		 * 
		 * If both strings are equal
		 * string 1: Encode
		 * string 2: Encode
		 * 
		 **********************/
		// TODO: end point left
		postResponse("http://localhost:8080/v1/diff/1/left",
				"RW5jb2Rl".toString());
		// TODO: end point right
		postResponse("http://localhost:8080/v1/diff/1/right",
				"RW5jb2Rl".toString());
		
		getResponse("http://localhost:8080/v1/diff/1");
		
		
		
		/**********************
		 * 
		 * If both strings are not equal
		 * string 1: Encode
		 * string 2: Enric
		 * 
		 **********************/
		// TODO: end point left
		postResponse("http://localhost:8080/v1/diff/1/left",
				"RW5jb2Rl".toString());
		// TODO: end point right
		postResponse("http://localhost:8080/v1/diff/1/right",
				"RW5yaWM=".toString());
		
		getResponse("http://localhost:8080/v1/diff/1");
		
		
		
		/**********************
		 * 
		 * If both strings are of same size but different character
		 * string 1: Encode
		 * string 2: Decode
		 * 
		 **********************/
		// TODO: end point left
		postResponse("http://localhost:8080/v1/diff/1/left",
				"RW5jb2Rl".toString());
		// TODO: end point right
		postResponse("http://localhost:8080/v1/diff/1/right",
				"RGVjb2Rl".toString());
		
		getResponse("http://localhost:8080/v1/diff/1");

	}

	public static String postResponse(String serverUrl, String str) {
		String output = null;
		try {
			URL url = new URL(serverUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// conn.setRequestMethod("GET");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept", "application/json");

			conn.connect(); // Note the connect() here

			OutputStream os = conn.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
			osw.write(str);
			osw.flush();
			osw.close();

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}
	
	public static String getResponse(String serverUrl) {
		String output = null;
		try {
			URL url = new URL(serverUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestProperty("Accept", "application/json");

			conn.connect(); // Note the connect() here

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			conn.disconnect();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}
}
