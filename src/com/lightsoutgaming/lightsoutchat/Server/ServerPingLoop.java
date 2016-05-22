package com.lightsoutgaming.lightsoutchat.Server;

import java.util.ArrayList;

public class ServerPingLoop implements Runnable{
	
	Server server;
	int maxTries = 5;
	boolean firstTime = true;
	
	public ServerPingLoop(Server server){
		this.server = server;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(server.running){
			if(!firstTime){
				ArrayList<Client> toberemoved = new ArrayList<Client>();
				for(Client c : server.clients){
					if(!c.response){
						c.tries++;
						if(c.tries > maxTries){
							c.running = false;
							//server.clients.remove(c);
							toberemoved.add(c);
							server.sendToAll(c.name+" has dissconnected.");
							System.out.println(c.name+" has dissconnected.");
						}
					}else{
						c.tries = 0;
					}
					c.response = false;
				}
				
				for(Client c : toberemoved){
					server.clients.remove(c);
				}
				
			}else{
				firstTime = false;
			}
			server.sendToAll("/u");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
