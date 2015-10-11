// A program to demonstrate the use of JTextFields's
//Import Statements
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField alumno1TextField, alumno2TextField, serverIpTextField,
			testTextField;
	private JButton registerButton, unregisterButton;
	
	private IController controller;

	public MainFrame(IController controller) {
		super("Client Demo");
		this.controller = controller;
		Container container = getContentPane();
		container.setLayout(new FlowLayout());

		alumno1TextField = new JTextField(10);
		alumno2TextField = new JTextField(10);
		serverIpTextField = new JTextField(10);

		testTextField = new JTextField("Esperando", 20);
		testTextField.setEditable(false);

		JTextField name1Text = new JTextField("Nombre primer alumno", 20);
		name1Text.setEditable(false);

		JTextField name2Text = new JTextField("Nombre segundo alumno", 20);
		name2Text.setEditable(false);

		JTextField serverIpText = new JTextField("Ip del Server", 20);
		serverIpText.setEditable(false);

		container.add(serverIpText);
		container.add(serverIpTextField);

		container.add(name1Text);
		container.add(alumno1TextField);

		container.add(name2Text);
		container.add(alumno2TextField);

		registerButton = new JButton("Empezar Laboratorio"); // construct a
																// JButton
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ip = serverIpTextField.getText();
				String nombre1 = alumno1TextField.getText();
				String nombre2 = alumno2TextField.getText();
				String message = "";
				if(!validIP(ip)) {
					message += "Campo IP invalido\n";
				}

				if(nombre1.equals("") && nombre2.equals("")) {
					message += "Al menos un nombre de alumno debe entregarse\n";
				}

				if(!message.equals("")) {
					JOptionPane.showMessageDialog(null, message);
				}else{
					getController().register(ip, nombre1, nombre2);
				}
			}
		});

		unregisterButton = new JButton("Terminar Laboratorio");
		unregisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getController().unregister();
			}
		});
		unregisterButton.setEnabled(false);

		container.add(registerButton);
		container.add(unregisterButton);
		container.add(testTextField);

		setSize(400, 200);
	}
	
	public void changeMessage(String message){
		testTextField.setText(message);
	}
	
	public void disableFields(){
		registerButton.setEnabled(false);
		serverIpTextField.setEditable(false);
		alumno1TextField.setEditable(false);
		alumno2TextField.setEditable(false);
		unregisterButton.setEnabled(true);
	}
	
	private IController getController(){
		return controller;
	}

	public static boolean validIP(String ip) {
		try {
			if (ip == null || ip.isEmpty()) {
				return false;
			}

			String[] parts = ip.split("\\.");
			if (parts.length != 4) {
				return false;
			}

			for (String s : parts) {
				int i = Integer.parseInt(s);
				if ((i < 0) || (i > 255)) {
					return false;
				}
			}
			if (ip.endsWith(".")) {
				return false;
			}

			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

}
