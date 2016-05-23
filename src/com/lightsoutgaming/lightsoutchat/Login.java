package com.lightsoutgaming.lightsoutchat;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtIp;
	private JLabel lblIp;
	private JTextField txtPort;
	private JLabel lblPort;
	private JPasswordField pwdPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 300, 380);
		setSize(300,384);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtName = new JTextField();
		txtName.setBounds(69, 37, 155, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(124, 22, 46, 14);
		contentPane.add(lblName);
		
		txtIp = new JTextField();
		txtIp.setText("174.45.148.43");
		txtIp.setBounds(69, 148, 155, 20);
		contentPane.add(txtIp);
		txtIp.setColumns(10);
		
		lblIp = new JLabel("IP Address:");
		lblIp.setBounds(116, 133, 61, 14);
		contentPane.add(lblIp);
		
		txtPort = new JTextField();
		txtPort.setText("2576");
		txtPort.setBounds(69, 211, 155, 20);
		contentPane.add(txtPort);
		txtPort.setColumns(10);
		
		lblPort = new JLabel("Port:");
		lblPort.setBounds(124, 196, 46, 14);
		contentPane.add(lblPort);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int port = 0;
				try{
					port = Integer.parseInt(txtPort.getText());
				}catch(NumberFormatException ex){
					
				}
				login(txtName.getText(), txtIp.getText(), port, new String(pwdPassword.getPassword()));
			}
		});
		btnLogin.setBounds(102, 271, 89, 23);
		contentPane.add(btnLogin);
		
		JLabel PassLabel = new JLabel("Password:");
		PassLabel.setBounds(116, 74, 61, 14);
		contentPane.add(PassLabel);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setBounds(69, 98, 155, 20);
		contentPane.add(pwdPassword);
	}

	private void login(String name, String address, int port, String pass) {
		
		dispose();
		new Client(name, address, port, pass);
		
	}
}
