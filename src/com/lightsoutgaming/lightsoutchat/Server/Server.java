package com.lightsoutgaming.lightsoutchat.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable{
	
	ServerSocket socket;
	Thread connectionThread;
	Thread serverCommandsThread;
	Thread serverPingLoop;
	public boolean running = true;
	ArrayList<Client> clients = new ArrayList<Client>();
	public static String password = "";
	
	public static void main(String[] args){
		if(args.length > 0){
			password = args[0];
		}
		new Server(2576);
	}
	
	public Server(int port){
		try {
			socket = new ServerSocket(port);
			socket.setSoTimeout(1000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connectionThread = new Thread(this, "Connection Thread");
		connectionThread.start();
		serverCommandsThread = new Thread(new ServerCommands(this), "Server Commands");
		serverCommandsThread.start();
		serverPingLoop = new Thread(new ServerPingLoop(this), "server ping loop");
		serverPingLoop.start();
	}

	@Override
	public void run() {
		
		while(running){
			try {
				Socket clientSocket = socket.accept();
				Client client = new Client(this, clientSocket);
				if(client.connect()){
					clients.add(client);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
		}
		
	}
	
	public void sendToAll(String msg){
		for(Client c : clients){
			c.send(msg);
		}
	}

}
