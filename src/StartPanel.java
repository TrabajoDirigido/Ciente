import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class StartPanel extends JPanel{
	private JTextField alumno1TextField, alumno2TextField, serverIpTextField;
	
	public StartPanel(){
		alumno1TextField = new JTextField(10);
		alumno2TextField = new JTextField(10);
		serverIpTextField = new JTextField(10);
		
		JTextField name1Text = new JTextField("Nombre primer alumno", 20);
		name1Text.setEditable(false);
		
		JTextField name2Text = new JTextField("Nombre segundo alumno", 20);
		name2Text.setEditable(false);
		
		JTextField serverIpText = new JTextField("Ip del Server", 20);
		serverIpText.setEditable(false);
		
		
		add(serverIpText);
		add(serverIpTextField);
		
		add(name1Text);
		add(alumno1TextField);
		
		add(name2Text);
		add(alumno2TextField);
		
		JButton submitButton = new JButton("Empezar Laboratorio"); // construct a JButton
		submitButton.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent e) {
			  String ip = serverIpTextField.getText();
			  String nombre1 = alumno1TextField.getText();
			  String nombre2 = alumno2TextField.getText();
			  String message = "";
			  if(!MainFrame.validIP(ip)){
				 message+="Campo IP invalido\n";
			  }
			  
			  if(nombre1.equals("") && nombre2.equals("")){
				  message+="Al menos un nombre de alumno debe entregarse\n";
			  }
			  
			  if(!message.equals("")){
				  JOptionPane.showMessageDialog(null, message); 
			  }
		  }
		});
	}
}
