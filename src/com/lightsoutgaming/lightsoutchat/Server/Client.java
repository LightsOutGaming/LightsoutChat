package com.lightsoutgaming.lightsoutchat.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable {
	
	Socket socket;
	BufferedReader socketIn;
	PrintWriter socketOut;
	String name;
	Thread listenThread;
	Server server;
	boolean response;
	int tries;
	boolean running = true;
	
	public Client(Server server, Socket socket){
		this.server = server;
		this.socket = socket;
		try {
			socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			socketOut = new PrintWriter(socket.getOutputStream());
			socketOut.println("Connected");
			socketOut.flush();
			name = socketIn.readLine();
			listenThread = new Thread(this, name+"'s listen thread");
			listenThread.start();
			server.sendToAll(name+" has Connected.");
			System.out.println(name+" has Connected.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		while(server.running && running){
			String msg;
			try {
				msg = socketIn.readLine();
				if(msg.equals("/u")){
					response = true;
				}else if(!msg.equals("")){
					server.sendToAll("<"+name+"> "+msg);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
		}
		
	}
	
	public void send(String msg){
		socketOut.println(msg);
		socketOut.flush();
	}

}
