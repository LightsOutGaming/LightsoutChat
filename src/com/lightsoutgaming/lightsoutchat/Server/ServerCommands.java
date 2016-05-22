package com.lightsoutgaming.lightsoutchat.Server;

import java.util.Scanner;

public class ServerCommands implements Runnable {
	
	Server server;
	
	public ServerCommands(Server server){
		this.server = server;
	}

	@Override
	public void run() {
		Scanner in = new Scanner(System.in);
		while(server.running){
			String text = in.nextLine();
			if(text.startsWith("/")){
				String[] args = text.split("/");
				String cmd = args[1];
				if(cmd.equals("stop")){
					server.running = false;
				}
			}
		}
		
	}

}
