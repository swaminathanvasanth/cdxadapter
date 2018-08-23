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

public class etoilet {

	static JsonParser jsonParser;
	static JsonElement jsonTree;
	static JsonArray jsonArray;
	static JsonObject jsonObject;

	final static String body = "{\n   \"username\":\"Elcita_et\",\n   \"password\":\"Pass@1234\"\n}";
	static String getToken_URL;
	static String getData_URL;
	static String getPublish_URL;

	static String tokenresponse;
	static JsonElement token;
	static String accesstoken;

	static String dataresponse;
	static String accessdata;
	static JsonElement unit;
	static String _unit;
	static HashMap<String, String> id;
	static HashMap<String, String> apikey;

	public static void start() {
		System.out.println("Started etoilet adapter");
		setup();
		getToken_URL = URLs.adaptor_getToken;
		getData_URL = URLs.etoilet_getData;

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
		id = new HashMap<>();
		apikey = new HashMap<>();
		id.put("Under Fly Over East Phase - Elcita (2)", "etoiletunderflyovereastphaseelcita2");
		id.put("Y Junction East Phase - Elcita (1)", "etoiletyjunctioneastphaseelcita1");
		id.put("Shantipura Jn - Elcita (4)", "etoiletshantipurajnelcita4");
		id.put("Nttf (under Fly Over) - Elcita (3)", "etoiletnttfunderflyoverelcita3");
		id.put("Nttf (under Fly Over) - Elcita (1)", "etoiletnttfunderflyoverelcita1");
		id.put("Elcia Complex - Elcita (4)", "etoiletelciacomplexelcita4");
		id.put("Hp Skywalk - Elcita (4)", "etoilethpskywalkelcita4");
		id.put("Pump House - Elcita (5)", "etoiletpumphouseelcita5");
		id.put("Fanuc (nr) - Elcita (1)", "etoiletfanucnrelcita1");
		id.put("Hp Entrance - Elcita (1)", "etoilethpentranceelcita1");
		id.put("Doddathogur - Elcita (3)", "etoiletdoddathogurelcita3");
		id.put("Traffic Police Station - Elcita (5)", "etoilettrafficpolicejnelcita5");
		id.put("Dmart - Elcita (4)", "etoiletdmartelcita4");
		id.put("Kssidc Backside - Elcita (5)", "etoiletikssidcbacksideelcita5");
		id.put("Bwssb (velakanni Rd) - Elcita (2)", "etoiletbwssbvelakannirdelcita2");
		id.put("Fire Station Rd - Elcita (2)", "etoiletfirestationrdelcita2");
		id.put("We School - Elcita (3)", "etoiletweschoolelcita3");
		id.put("Msme Building - Elcita (5)", "etoiletmsmebuildingelcita5");
		id.put("Police Station (l&o) - Elcita (2)", "etoiletpolicestationelcita2");
		id.put("Tech Mahindra - Elcita (3)", "etoilettechmahindraelcita3");

		apikey.put("etoiletunderflyovereastphaseelcita2", "V-atmXUPlYWy9GiOKQmdTPKAF7P95FBQz0S6IjoFhZB");
		apikey.put("etoiletyjunctioneastphaseelcita1", "OntUEAQdI13jM1J_YStQurh6QoCuoQ4iINye_J-EeSw");
		apikey.put("etoiletshantipurajnelcita4", "aQPmivJiPdAUFPBKzjFb4EqePasYqZtCbJCbT-TVp9w");
		apikey.put("etoiletnttfunderflyoverelcita3", "kJ3f2T2e0DbHftdPAFA5K3oyxzjYgNacgWBGiF_1Tt_");
		apikey.put("etoiletnttfunderflyoverelcita1", "nP25Lf9u2u5dLs-nMNRFXqYvWEx83YDhMYT6nWMNXQC");
		apikey.put("etoiletelciacomplexelcita4", "ymSiboWLkItdfpzBjT1zLobPsB2wjuLdCL96ItIH2wV");
		apikey.put("etoilethpskywalkelcita4", "bBk1DOT98Dot8KEvDlK2Jt-pBI4U53PZoRGx9zw-2K9");
		apikey.put("etoiletpumphouseelcita5", "7-UO85YfbgwubV7K0f1ChntlE33lbdqIbspDqab7zGm");
		apikey.put("etoiletfanucnrelcita1", "_gc_52l1-EmUk5StXwacULseYhwOKsBgxC4Gv4N8rBV");
		apikey.put("etoilethpentranceelcita1", "6GdCcX9DQLnJ1M82BW1Xo732k7HYuT6FJBrwrvbmx00");
		apikey.put("etoiletdoddathogurelcita3", "QLxEtC483AUOZVljNs8YtyY_u1LplLKFnPD6udyRZOe");
		apikey.put("etoilettrafficpolicejnelcita5", "QtV4D6GzOxIqhqtrd6v3kW3ooz7daEGlqXfahNRwJ7v");
		apikey.put("etoiletdmartelcita4", "CuQ5ac6zt53g-jFjOP9-gTn0d5QJg7J5PZn4YjOzqwr");
		apikey.put("etoiletikssidcbacksideelcita5", "qjGpBQHPxZHmvUqdbwbzk4XJNxyeuNoylbh6JjUsKPw");
		apikey.put("etoiletbwssbvelakannirdelcita2", "yA2Ds7HM64hOblUSCnSP07-6lBNouuCqXJ1PuDoSPY-");
		apikey.put("etoiletfirestationrdelcita2", "zXegIQMUs2LXHrL3gEnzNBxl1jOLB6L4O3JmEcE4SpF");
		apikey.put("etoiletweschoolelcita3", "m58lQ2WdMqKry9Xew1TIeLRonBEGZAjpGTe8pfoeFwf");
		apikey.put("etoiletmsmebuildingelcita5", "Oy_5Qt3nfw_Ijt8WrF0J_YIqOikWJck3gQInllHvDJd");
		apikey.put("etoiletpolicestationelcita2", "CWCZcuSv7TnTKpY1l3GZyBnKeDXnlR7_rObzcCahM3k");
		apikey.put("etoilettechmahindraelcita3", "IKZ5jNvQfhFuZ3m4-iPjPDMpEJt59NOmHDj8lHldsDV");

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

		if (responseCode >= 200 && responseCode <= 300) {

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
						unit = jsonObject.get("unit");
						_unit = unit.toString();
						System.out.println(_unit);
						System.out.println(accessdata);
						System.out.println("====================");
						publish(accessdata);
					}
				}
			}
		}
	}

	public static void publish(String data) throws Exception {

		getPublish_URL = URLs.cdx_publish;

		String _id = id.get(_unit.replaceAll("^\"|\"$", ""));
		System.out.println(_unit + " ---- " + _id);

		URL obj = new URL(getPublish_URL + _id + ".protected");
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		String key = apikey.get(_id);
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
	}
}
