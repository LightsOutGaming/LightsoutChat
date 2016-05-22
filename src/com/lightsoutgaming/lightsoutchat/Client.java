package com.lightsoutgaming.lightsoutchat;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

public class Client extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	String name, address;
	int port;
	private JTextField txtMsg;
	private JTextArea txtrHistory;
	private DefaultCaret caret;

	/**
	 * Create the frame.
	 */
	public Client(String name, String address, int port) {
		setTitle("LightsOutChat");
		this.name = name;
		this.address = address;
		this.port = port;
		createWindow();
	}
	
	private void createWindow(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 800, 300);
		setSize(880, 570);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{10, 845, 30, 5};
		gbl_contentPane.rowHeights = new int[]{10, 520, 40};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0, 0};
		gbl_contentPane.rowWeights = new double[]{0, 1, 0};
		contentPane.setLayout(gbl_contentPane);
		
		txtrHistory = new JTextArea();
		txtrHistory.setEditable(false);
		caret = (DefaultCaret)txtrHistory.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		JScrollPane scroll = new JScrollPane(txtrHistory);
		
		GridBagConstraints gbc_txtrHistory = new GridBagConstraints();
		gbc_txtrHistory.insets = new Insets(0, 0, 5, 5);
		gbc_txtrHistory.fill = GridBagConstraints.BOTH;
		gbc_txtrHistory.gridwidth = 2;
		gbc_txtrHistory.gridx = 1;
		gbc_txtrHistory.gridy = 1;
		contentPane.add(scroll, gbc_txtrHistory);
		
		txtMsg = new JTextField();
		txtMsg.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					send(txtMsg.getText());
				}
			}
		});
		GridBagConstraints gbc_txtMsg = new GridBagConstraints();
		gbc_txtMsg.insets = new Insets(0, 0, 0, 5);
		gbc_txtMsg.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMsg.gridx = 1;
		gbc_txtMsg.gridy = 2;
		contentPane.add(txtMsg, gbc_txtMsg);
		txtMsg.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				send(txtMsg.getText());
			}
		});
		GridBagConstraints gbc_btnSend = new GridBagConstraints();
		gbc_btnSend.insets = new Insets(0, 0, 0, 5);
		gbc_btnSend.gridx = 2;
		gbc_btnSend.gridy = 2;
		contentPane.add(btnSend, gbc_btnSend);
		
		setVisible(true);
		
		txtMsg.requestFocusInWindow();
		
	}
	
	public void send(String text){
		if(!text.equals("")){
			log("<"+name+"> "+text);
			txtMsg.setText("");
		}
	}
	
	public void log(String string){
		txtrHistory.append(string + "\n\r");
		txtrHistory.setCaretPosition(txtrHistory.getDocument().getLength());
	}
	

}
