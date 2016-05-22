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

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtIp;
	private JLabel lblIp;
	private JTextField txtPort;
	private JLabel lblPort;

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
		setSize(300,380);
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
		txtIp.setBounds(69, 95, 155, 20);
		contentPane.add(txtIp);
		txtIp.setColumns(10);
		
		lblIp = new JLabel("IP Address:");
		lblIp.setBounds(116, 80, 61, 14);
		contentPane.add(lblIp);
		
		txtPort = new JTextField();
		txtPort.setBounds(69, 158, 155, 20);
		contentPane.add(txtPort);
		txtPort.setColumns(10);
		
		lblPort = new JLabel("Port:");
		lblPort.setBounds(124, 143, 46, 14);
		contentPane.add(lblPort);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int port = 0;
				try{
					port = Integer.parseInt(txtPort.getText());
				}catch(NumberFormatException ex){
					
				}
				login(txtName.getText(), txtIp.getText(), port);
			}
		});
		btnLogin.setBounds(102, 218, 89, 23);
		contentPane.add(btnLogin);
	}

	private void login(String name, String address, int port) {
		
		dispose();
		new Client(name, address, port);
		
	}
}
