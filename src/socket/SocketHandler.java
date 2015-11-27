package socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import controller.MainController;


public class SocketHandler extends Thread{
	private BufferedReader in=null;
	private MainController controller;
	private Socket socket;
	private String ip;
	int port;
	
	public SocketHandler(MainController controller){
		this.controller = controller;
	}
	
	public void setParams(String ip, int port){
		this.ip = ip;
		this.port = port;
	}
	
	@Override
	public void run(){
		runSocket(ip, port);
	}
	
	public void runSocket(String ip, int port){
        try {
           socket = new Socket(ip, port);
           in = new BufferedReader(
               new InputStreamReader(socket.getInputStream()));
           System.out.println("Connected to server "+ip + ":" + port);
           controller.statusMessage("Conectado a "+ip + ":" + port);
        } catch (IOException ioe) {
           System.err.println("Can not establish connection to " +
        		   ip + ":" + port);
           controller.statusMessage("No se pudo establecer conexión con " +
        		   ip + ":" + port);
           ioe.printStackTrace();
           System.exit(-1);
        }
 
        try {
           // Read messages from the server and print them
           String message;
           while(!(message=in.readLine()).equals("")) {
               //System.out.println(message);
               controller.socketMessage(message);
           }
           System.out.println("Terminado");
           in.close();
           socket.close();
           controller.stop();
        } catch (IOException ioe) {
           System.err.println("Connection to server broken.");
           controller.statusMessage("Conexión terminada abruptamente.");
           ioe.printStackTrace();
        }
	}

}
