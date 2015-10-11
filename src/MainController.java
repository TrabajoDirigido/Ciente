import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;


public class MainController implements IController {
	
	private MainFrame frame;
	private int port;
	private String token;
	private String ip;
	
	private SocketHandler socketHandler;
	
	public static void main(String args[]) {
		MainController controller = new MainController();
		controller.run();
	}
	
	public MainController(){
		frame = new MainFrame(this);
		socketHandler = new SocketHandler(this);
		ip="0.0.0.0";
		port=0;
		token="";
	}
	
	public void run(){
		frame.setVisible(true);
	}

	public void register(String ip, String name1, String name2) {
		String url = "http://" + ip + ":8080/server_client/register/?names="
				+ getCodedNames(name1, name2);
		
		this.ip=ip;
		System.out.println(url);
		String response = sendRequest(url);
		if(response==null){
			frame.changeMessage("Error al registrarse");
			return;
		}
		System.out.println(response);
		
		JSONObject responseContainer = null;
		try {
			responseContainer = new JSONObject(response);
			token = responseContainer.getString("token");
			port = responseContainer.getInt("port");
		} catch (JSONException e) {
			e.printStackTrace();
			frame.changeMessage("Error al tratar de leer la respuesta del servidor");
			return;
		}
		
		frame.changeMessage("Conectado");
		frame.disableFields();
		
		socketHandler.setParams(ip, port);
		socketHandler.start();
		
		
	}
	
	public void unregister() {
		String url = "http://" + ip + ":8080/server_client/unregister/?token="
				+ token;
		System.out.println(url);
		String response = sendRequest(url);
		if(response!=null){
			frame.changeMessage("Desvinculando...");
		}
		else{
			frame.changeMessage("Error al desvincular cliente");
		}
	}
	
	private String getCodedNames(String name1, String name2) {
		if (name1 == null || name1.equals("")) {
			return name2.replace(" ", "%20");
		} else if (name2 == null || name2.equals("")) {
			return name1.replace(" ", "%20");
		}
		name1 = name1.replace(" ", "%20");
		name2 = name2.replace(" ", "%20");
		return name1 + "," + name2;
	}
	
	private String sendRequest(String pUrl) {
		StringBuffer response = new StringBuffer();
		try {
			URL url = new URL(pUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			int responseCode = con.getResponseCode();
			if (responseCode == 500) {
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

	public int getPort() {
		return port;
	}

	public String getIp() {
		return ip;
	}

	public void socketMessage(String message) {
		frame.changeMessage(message);		
	}

	public void statusMessage(String message) {
		frame.changeMessage(message);		
	}

	public void disposeFrame() {
		frame.dispose();		
	}
}
