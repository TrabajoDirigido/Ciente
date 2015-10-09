// A program to demonstrate the use of JTextFields's
//Import Statements
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {

	//Class Declarations
	private JTextField alumno1TextField, alumno2TextField, serverIpTextField;
	
	//Constructor
	public MainFrame() {
		super("Client Demo");
		 

		
		setSize(400, 200);
		setVisible(true);
	}
	
	public static boolean validIP (String ip) {
	    try{
	        if(ip == null || ip.isEmpty()){
	            return false;
	        }

	        String[] parts = ip.split("\\.");
	        if(parts.length != 4){
	            return false;
	        }

	        for(String s : parts) {
	            int i = Integer.parseInt( s );
	            if((i < 0) || (i > 255)){
	                return false;
	            }
	        }
	        if(ip.endsWith(".")){
	            return false;
	        }

	        return true;
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	}
	
	
	//Main Program that starts Execution
	public static void main(String args[]) {
		MainFrame test = new MainFrame();
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}// End of class TextFieldTest
