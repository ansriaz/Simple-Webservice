package waes.assignment;

/** ===========================
 * 
 *	@author Ans Riaz
 *	
 *	Date: 28-Feb-2107
 *  
=========================== **/

import java.util.concurrent.atomic.AtomicLong;

import javax.websocket.server.PathParam;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ===========================
 * 
 * This is the main controller, where every request is entertained. This class
 * have some public and some private methods. Private variables are used to keep
 * the track of the request and there information.
 * 
 * @param AtomicLong
 *            counter => used to count the number of request entertained by the
 *            system int leftId => used to store the id of the left endpoint int
 *            rightId => used to store the id of the right endpoint String left
 *            => used to store the string of left endpoint String right => used
 *            to store the string of right endpoint
 * 
 *            ===========================
 **/

@RestController
public class ResponseController {

	private final AtomicLong counter = new AtomicLong();

	/**
	 * ===========================
	 * 
	 * This is a GET API that works as a computational API that compute the
	 * required functionality over the provided strings.
	 * 
	 * This API take only one parameter from the path to compute the requests
	 * received from its endpoints.
	 *
	 * @method Accepts path parameter Retrieve its requested strings provided
	 *         already by its endpoints Computer 3 different functions
	 *
	 *         (Assignment text) The results shall provide the following info in
	 *         JSON format 1. If equal return that 2. If not of equal size just
	 *         return that 3. If of same size provide insight in where the diffs
	 *         are, actual diffs are not needed. So mainly offsets + length in
	 *         the data
	 * 
	 * @param String
	 *            id => PathParam from url.
	 *
	 * @return RestResponse object with complete information about the requested
	 *         strings and their result e.g { "id": 0, "result": "kindly provide
	 *         the strings", "left": "no string", "right": "no string" }
	 *
	 *         Assumptions: 1. If the strings are equal, return one string as a
	 *         result parameter in JSON 2. If strings are not equal, return the
	 *         result "string are not equal" as string 3. If the size is same
	 *         but different in characters, . Search all the different
	 *         characters in both strings but are at the same indexes 1. returns
	 *         the offset of every single character that is different at exactly
	 *         the same index in both strings and 2. the number of total
	 *         characters that are different at the same indexes. 4. Json
	 *         response is designed as above to give the proper idea.
	 * 
	 *         ===========================
	 **/
	@RequestMapping("/v1/diff/{id}")
	public RestResponse calculateRequest(@PathParam("id") String id) {
		RestResponse obj;
		// System.out.println(Utils.left + " " + Utils.right);
		// System.out.println(Utils.leftId + " " + Utils.rightId + " " + id);

		if (Utils.leftId == Utils.rightId) {
			if (Utils.left.equals(Utils.right)) {
				obj = new RestResponse(counter.incrementAndGet(), Utils.left, Utils.right, Utils.left);
			} else if (Utils.left.length() == Utils.right.length()) {

				int d = 0;
				String indexes = "";
				for (int a : findDifference(Utils.left, Utils.right)) {
					if (a != 0) {
						indexes += String.valueOf(a) + ", ";
						d++;
					}
				}
				String finalResult = String.format(
						"String %s is different from " + "String %s by %d letters at positions " + "%s", Utils.left,
						Utils.right, d, indexes);
				obj = new RestResponse(counter.incrementAndGet(), Utils.left, Utils.right, finalResult);
			} else {
				obj = new RestResponse(counter.incrementAndGet(), Utils.left, Utils.right, "strings are not equal");
			}
		} else {
			obj = new RestResponse(0, "no string", "no string", "kindly provide the strings");
		}
		return obj;
	}

	/**
	 * ===========================
	 *
	 * @method GET API, just to check if the path parameter is working properly
	 *         for the left end point
	 * 
	 * @param String
	 *            leftId
	 *
	 * @return String with the path parameter
	 * 
	 *         ===========================
	 **/
	@RequestMapping("/v1/diff/{id}/left")
	public String leftMessage(@PathParam("id") String leftId) {
		System.out.println("Left ID = " + leftId);
		return "Value Left :" + leftId;
	}

	/**
	 * ===========================
	 *
	 * @method GET API, just to check if the path parameter is working properly
	 *         for the right end point
	 * 
	 * @param String
	 *            rightId
	 *
	 * @return String with the path parameter
	 * 
	 *         ===========================
	 **/
	@RequestMapping("/v1/diff/{id}/right")
	public String rightMessage(@PathParam("id") String leftId) {
		System.out.println("Left ID = " + leftId);
		return "Value Left :" + leftId;
	}

	/**
	 * ===========================
	 * 
	 * This is a POST API (named as left endpoint) that takes only one parameter
	 * in the body for the specific url {id} provided by user
	 *
	 * @method Accepts one path parameter and one Base64 encoded string from
	 *         body Decode the Base64 encoded string into a simple string and
	 *         stores for later use
	 * 
	 *         (Assignment text) Provide 2 http endpoints that accepts JSON
	 *         base64 encoded binary data on both endpoints
	 *         <host>/v1/diff/<ID>/left
	 * 
	 * @param id
	 *            => PathParam from url body => Body of the request
	 *
	 * @return RestResponse object with complete information about the requested
	 *         strings and their result e.g { "id": 0, "result": "", "left":
	 *         "this is my first string baby", "right": "" }
	 * 
	 *         ===========================
	 **/
	@RequestMapping(value = "/v1/diff/{id}/left", method = { RequestMethod.POST, RequestMethod.POST })
	@ResponseBody
	public RestResponse leftSide(@PathVariable("id") String id, @RequestBody String body) {
		// System.out.println("Received: " + body);
		byte[] valueDecoded = Base64.decodeBase64(body);
		// System.out.println("Left side: " + id + " Decoded:" + (new
		// String(valueDecoded)));

		Utils.leftId = Integer.parseInt(id);
		Utils.left = new String(valueDecoded);

		RestResponse obj = new RestResponse(0, Utils.left, "", "");
		return obj;
	}

	/**
	 * ===========================
	 * 
	 * This is a POST API (named as right endpoint) that takes only one
	 * parameter in the body for the specific url {id} provided by user
	 *
	 * @method Accepts one path parameter and one Base64 encoded string from
	 *         body Decode the Base64 encoded string into a simple string and
	 *         stores for later use
	 * 
	 *         (Assignment text) Provide 2 http endpoints that accepts JSON
	 *         base64 encoded binary data on both endpoints
	 *         <host>/v1/diff/<ID>/right
	 * 
	 * @param id
	 *            => PathParam from url body => Body of the request
	 *
	 * @return RestResponse object with complete information about the requested
	 *         strings and their result e.g { "id": 0, "result": "", "left": "",
	 *         "right": "this is my first string baby" }
	 * 
	 *         ===========================
	 **/
	@RequestMapping(value = "/v1/diff/{id}/right", method = { RequestMethod.POST, RequestMethod.POST })
	@ResponseBody
	public RestResponse rightSide(@PathVariable("id") String id, @RequestBody String body) {
		// System.out.println("Right side: " + id + " Decoded:" +
		// Base64.decodeBase64(id));
		byte[] valueDecoded = Base64.decodeBase64(body);
		// System.out.println("Left side: " + id + " Decoded:" + (new
		// String(valueDecoded)));

		Utils.right = new String(valueDecoded);
		Utils.rightId = Integer.parseInt(id);

		RestResponse obj = new RestResponse(0, "", Utils.right, "");
		return obj;
	}

	/**
	 * ===========================
	 *
	 * @method Accepts 2 strings and check for the index where one string is
	 *         different from other
	 * 
	 * @param String
	 *            s1 String s2
	 *
	 * @return int i => starting index of string where character is different
	 * 
	 *         ===========================
	 **/
	private int[] findDifference(String s1, String s2) {
		int[] index = new int[s1.length()];
		int i;
		for (i = 0; i < s1.length() && i < s2.length(); ++i) {
			System.out.println(s1.charAt(i)+" " + s2.charAt(i));
			
			if (s1.charAt(i) != s2.charAt(i)) {
				index[i] = i;
			} else {
				index[i] = 0;
			}
			System.out.print(index[i]+" ");
		}
		return index;
	}
}
