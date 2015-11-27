package controller;

import http_request.RegisterRequest;
import http_request.UnregisterRequest;

import org.json.JSONException;
import org.json.JSONObject;

import frame.MainFrame;
import request.RequestHandler;
import socket.SocketHandler;

public class MainController {
	
	private MainFrame frame;
	private int port;
	private String token;
	private String ip;
	private String filename;
	private RequestHandler requestHandler;
		
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
		filename="";
		requestHandler = new RequestHandler(this);
	}
	
	public void run(){
		frame.setVisible(true);
	}

	public void register(String ip, String name1, String name2, String filename) {		
		this.ip=ip;
		this.filename=filename;
		
		RegisterRequest request = new RegisterRequest(ip, name1, name2);
		String response = request.sendRequest();
		if(response==null){
			frame.changeMessage("Error al registrarse");
			return;
		}
		
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
		UnregisterRequest request = new UnregisterRequest(this.ip, this.token);
		String response = request.sendRequest();
		if(response!=null){
			frame.changeMessage("Desvinculando...");
		}
		else{
			frame.changeMessage("Error al desvincular cliente");
		}
	}
	
	

	public int getPort() {
		return port;
	}

	public String getIp() {
		return ip;
	}
	
	public void socketMessage(String message) {
		frame.changeMessage(message);
		requestHandler.handleRequest(message);
	}

	public void statusMessage(String message) {
		frame.changeMessage(message);		
	}

	public void stop() {
		frame.dispose();		
	}

	public String getFilename() {
		return this.filename;
	}

	public String getToken() {
		return this.token;
	}
}
