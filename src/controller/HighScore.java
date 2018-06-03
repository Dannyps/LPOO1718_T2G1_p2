package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringJoiner;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.util.Pair;

public class HighScore {

	private static final String HIGHSCORES_URL = "http://lpoo.dannyps.net/";

	String uname;
	int score;

	public HighScore(String uname, int score) {
		this.uname = uname;
		this.score = score;
	}
	
	public Boolean submit() throws IOException {
		URL url = new URL(HIGHSCORES_URL+"highscore");
		URLConnection con = url.openConnection();
		HttpURLConnection http = (HttpURLConnection)con;
		http.setRequestMethod("POST"); // PUT is another valid option
		http.setDoOutput(true);
		
		Map<String,String> arguments = new HashMap<>();
		arguments.put("name", this.uname);
		arguments.put("score", Integer.toString(this.score)); // This is a fake password obviously
		StringJoiner sj = new StringJoiner("&");
		for(Map.Entry<String,String> entry : arguments.entrySet())
		    sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "=" 
		         + URLEncoder.encode(entry.getValue(), "UTF-8"));
		byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);
		int length = out.length;
		
		
		http.setFixedLengthStreamingMode(length);
		http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		http.connect();
		try(OutputStream os = http.getOutputStream()) {
		    os.write(out);
		}
		// Do something with http.getInputStream()
		
		http.disconnect();
		if(http.getResponseCode() == 204)
			return true;
		else
			return false;
		
	}
		
	
	/**
	 * Used to retrieve the highscores stored in the server
	 * @return Pair<String, Integer>[]
	 * @throws Throwable
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Pair<String, Integer>[] getHighscores() throws Throwable {

		// build a URL
		URL url = new URL(HIGHSCORES_URL + "highscores");

		// read from the URL
		Scanner scan = new Scanner(url.openStream());
		String str = new String();
		while (scan.hasNext())
			str += scan.nextLine();
		scan.close();

		JSONArray arr = new JSONArray(str);

		// create a pair array with the right length
		Pair<String, Integer>[] ret = (Pair<String, Integer>[]) new Pair[arr.length()];

		int i = 0;

		for (Object obj : arr) {
			JSONObject o = (JSONObject) obj;
			ret[i++] = new Pair(o.get("name"), o.get("score"));
		}

		return ret;

	}
}
