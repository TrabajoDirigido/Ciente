package http_request;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class HttpRequest {
	
	private String url;
	private JSONObject data;

	public HttpRequest(String url){
		this.url = url;
		this.data = null;
	}
	
	public HttpRequest(String url, JSONObject data){
		this.url = url;
		this.data = data;
	}
	
	public String sendRequest() {
		StringBuffer response = new StringBuffer();
		try {
			URL url = new URL(this.url);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			if(data!=null){
				con.setUseCaches(false);
				con.setDoInput(true);
		        con.setDoOutput(true);
				con.setRequestMethod("POST");
				con.setRequestProperty("Content-Type", "application/json");
				con.setRequestProperty("Content-Language", "en-US"); 
				con.setRequestProperty("User-Agent",
		                "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11");
				OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
				wr.write(data.toString());
				wr.flush();
			}

			int responseCode = con.getResponseCode();
			if (responseCode != 200) {
				System.out.println("Error in HttpRequest. Url: "+this.url+", Code: "+responseCode);
				return null;
			}

			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return response.toString();
	}

}
