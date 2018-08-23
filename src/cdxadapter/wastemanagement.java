package cdxadapter;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class wastemanagement {

	static JsonParser jsonParser;
	static JsonElement jsonTree;
	static JsonArray jsonArray;
	static JsonObject jsonObject;

	final static String body = "{\n   \"username\":\"Elcita_wm\",\n   \"password\":\"Password@456\"\n}";
	static String getToken_URL;
	static String getData_URL;
	static String getPublish_URL;

	static String tokenresponse;
	static JsonElement token;
	static String accesstoken;

	static String dataresponse;
	static String accessdata;
	static HashMap<String, String> id;
	static HashMap<String, String> apikey;

	static JsonElement customerId;
	static String _customerId;

	public static void start() {
		System.out.println("Started waste management adapter");
		setup();
		getToken_URL = URLs.adaptor_getToken;
		getData_URL = URLs.waste_getData;

		while (true) {
			try {
				getToken();
				getData();
				Thread.sleep(1 * 60000 * 60);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void setup() {
		// TODO Auto-generated method stub
		apikey = new HashMap<>();

		apikey.put("elcita5509", "e8N0EoQ5cbFH-oUzitCaLRmVfrQ1oQnRqYTGYbv96qK");
		apikey.put("elcita5561", "7kN9foWMtEvgAn3DKYEsRo2uhEJVeFg3opg24tXN-x9");
		apikey.put("elcita7006", "cHFNlxofzDat8XgoD0RfWjqzVN06Z44mmZglxoSu6Zs");
		apikey.put("elcita5570", "CcNxv7YUcJWbCadFJWITswOxFyT2R_qNhPqu5TONDW_");
		apikey.put("elcita7003", "MrhI7bMbbvNano01D4x9QjFdYp7wGEVb93lYfG6b3jf");
		apikey.put("elcita5627", "0KaTITuacYYYdVEm0xzFfF6bcuimrfY6rcRgG5FBhVO");
		apikey.put("elcita5639", "Ylj2JXTgSQiWuXTm1ILiBz9kUVtrLIg9h0wk46rDyps");
		apikey.put("elcita5650", "419eo9noENVcE5DhbJ1C9pDG871Rm361H6nB60P1n2i");
		apikey.put("elcita5653", "EUE6Fd_mIkxol3LF_-BbYv1eoUE-mU6cXloBoGq346p");
		apikey.put("elcita5682", "cOp-R2m2xQxYM6ajtPBryNz9CG8vV5LwMKoIsjodPNC");
		apikey.put("elcita5686", "A-0qcwt6KwbdfvN2X92Je-0HKp_J-h70rvX1vycp485");

		apikey.put("elcita5792", "e8N0EoQ5cbFH-oUzitCaLRmVfrQ1oQnRqYTGYbv96qK");
		apikey.put("elcita5701", "7kN9foWMtEvgAn3DKYEsRo2uhEJVeFg3opg24tXN-x9");
		apikey.put("elcita5659", "cHFNlxofzDat8XgoD0RfWjqzVN06Z44mmZglxoSu6Zs");
		apikey.put("elcita5662", "CcNxv7YUcJWbCadFJWITswOxFyT2R_qNhPqu5TONDW_");
		apikey.put("elcita5690", "MrhI7bMbbvNano01D4x9QjFdYp7wGEVb93lYfG6b3jf");
		apikey.put("elcita5704", "0KaTITuacYYYdVEm0xzFfF6bcuimrfY6rcRgG5FBhVO");
		apikey.put("elcita5705", "Ylj2JXTgSQiWuXTm1ILiBz9kUVtrLIg9h0wk46rDyps");
		apikey.put("elcita5804", "419eo9noENVcE5DhbJ1C9pDG871Rm361H6nB60P1n2i");
		apikey.put("elcita5706", "EUE6Fd_mIkxol3LF_-BbYv1eoUE-mU6cXloBoGq346p");
		apikey.put("elcita5793", "cOp-R2m2xQxYM6ajtPBryNz9CG8vV5LwMKoIsjodPNC");
		apikey.put("elcita5723", "A-0qcwt6KwbdfvN2X92Je-0HKp_J-h70rvX1vycp485");

		apikey.put("elcita5811", "e8N0EoQ5cbFH-oUzitCaLRmVfrQ1oQnRqYTGYbv96qK");
		apikey.put("elcita5805", "7kN9foWMtEvgAn3DKYEsRo2uhEJVeFg3opg24tXN-x9");
		apikey.put("elcita5731", "cHFNlxofzDat8XgoD0RfWjqzVN06Z44mmZglxoSu6Zs");
		apikey.put("elcita7009", "CcNxv7YUcJWbCadFJWITswOxFyT2R_qNhPqu5TONDW_");
		apikey.put("elcita5737", "MrhI7bMbbvNano01D4x9QjFdYp7wGEVb93lYfG6b3jf");
		apikey.put("elcita5794", "0KaTITuacYYYdVEm0xzFfF6bcuimrfY6rcRgG5FBhVO");
		apikey.put("elcita7367", "Ylj2JXTgSQiWuXTm1ILiBz9kUVtrLIg9h0wk46rDyps");
		apikey.put("elcita5840", "419eo9noENVcE5DhbJ1C9pDG871Rm361H6nB60P1n2i");
		apikey.put("elcita5786", "EUE6Fd_mIkxol3LF_-BbYv1eoUE-mU6cXloBoGq346p");
		apikey.put("elcita5787", "cOp-R2m2xQxYM6ajtPBryNz9CG8vV5LwMKoIsjodPNC");
		apikey.put("elcita5788", "A-0qcwt6KwbdfvN2X92Je-0HKp_J-h70rvX1vycp485");
		
	}

	// HTTP Post request
	private static boolean getToken() throws Exception {

		URL obj = new URL(getToken_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// Setting basic post request
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(body);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("Sending 'POST' request to URL : " + getToken_URL);
		System.out.println("Post Data : " + body);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String output;
		StringBuffer response = new StringBuffer();

		while ((output = in.readLine()) != null) {
			response.append(output);
		}
		in.close();

		tokenresponse = response.toString();
		// printing result from response
		System.out.println(tokenresponse);

		jsonParser = new JsonParser();
		jsonTree = jsonParser.parse(tokenresponse);

		if (jsonTree.isJsonArray()) {
			System.out.println("Its an Array");
			System.out.println(jsonTree.toString());
			jsonArray = jsonTree.getAsJsonArray();
			System.out.println(jsonArray.get(0));
			jsonObject = (JsonObject) jsonArray.get(0);
			System.out.println(jsonObject.get("token"));
			token = jsonObject.get("token");
			accesstoken = token.toString().replaceAll("^\"|\"$", "");
		}

		return true;

	}

	// HTTP GET request
	private static void getData() throws Exception {

		URL url = new URL(getData_URL + accesstoken);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();
		System.out.println("Sending get request : " + url);
		System.out.println("Response code : " + responseCode);

		// Reading response from input Stream
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String output;
		StringBuffer response = new StringBuffer();

		while ((output = in.readLine()) != null) {
			response.append(output);
		}
		in.close();

		// printing result from response
		dataresponse = response.toString();
		System.out.println(dataresponse);

		jsonParser = new JsonParser();
		jsonTree = jsonParser.parse(dataresponse);

		if (jsonTree.isJsonArray()) {
			System.out.println("Its an Array");
			System.out.println(jsonTree.toString());
			jsonArray = jsonTree.getAsJsonArray();
			int size = jsonArray.size();
			System.out.println(size);

			if (size > 0) {
				for (int i = 0; i < size; i++) {
					jsonObject = (JsonObject) jsonArray.get(i);
					System.out.println("====================");
					accessdata = jsonObject.toString();
					customerId = jsonObject.get("customerId");
					_customerId = customerId.toString();
					System.out.println(customerId);
					System.out.println(accessdata);
					System.out.println("====================");
					//publish(accessdata);
				}
			}
		}
	}

	public static void publish(String data) throws Exception {

		getPublish_URL = URLs.cdx_publish;
		String key = "";

		String _id = "elcita" + _customerId.replaceAll("^\"|\"$", "");
		System.out.println(customerId + " ---- " + _id);

		URL obj = new URL(getPublish_URL + _id + ".protected");
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		try {
			key = apikey.get(_id);
			System.out.println(key);

			// Setting basic post request
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("apikey", key);
			con.setRequestProperty("routingKey", _id);

			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(data);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			System.out.println("Sending 'POST' request to URL : " + getPublish_URL);
			System.out.println("Post Data : " + data);
			System.out.println("Response Code : " + responseCode);

		} catch (Exception e) {
			System.out.println("Not in List");
		}
	}
}
