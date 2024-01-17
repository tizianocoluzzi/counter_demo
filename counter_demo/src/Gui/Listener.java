package Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Logger;

class Runner implements Runnable{
	Gui g;
	Listener l;
	Logger log;
	Scanner leggi;
	PrintWriter scrivi;
	public Runner(Listener l, Gui g) {
		log = Logger.getLogger("Thread Listener");
		this.l = l;
		this.g = g;
	}
	@Override
	public void run() {
		log.info("avviato thread listener client");
		try {
			leggi = new Scanner(l.s.getInputStream());
			String str;
			while(leggi.hasNextLine()) {
				str = leggi.nextLine();
				log.info(str);
				String[] msg = str.split(":");
				if(msg[0].equals("UPDATE")) {
					g.count.setEnabled(true);
					g.count.setText(msg[1]);
					if(msg[1].equals("0")) {
						g.zero_state();
					}
					else {
						g.logged();
					}
					
				}
			}
		} catch (IOException e) {
			log.warning(e.getMessage());
		}
	}
	
}

public class Listener implements ActionListener{
	Gui g;
	Socket s;
	Logger log;
	PrintWriter scrivi;
	Scanner leggi;
	Runner r;
	Thread t;
	Listener(Gui g){
		this.g = g;
		log = Logger.getLogger("main thread");
		tryConnect();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("connect")) {
			log.info("premuto connect");
			try {
				s = new Socket("localhost", 4000);
				g.connected();
				
			} catch (UnknownHostException e1) {
				g.init();
				log.warning(e1.getMessage());
			} catch (IOException e1) {
				g.init();
				log.warning(e1.getMessage());
			}
		}
		if(e.getActionCommand().equals("login")) {
			log.info("premuto login");
			try {
				scrivi = new PrintWriter(s.getOutputStream());
				scrivi.println("USERNAME:" + g.username.getText());
				scrivi.flush();
				r = new Runner(this, g);
				t = new Thread(r);
				t.start();
			} catch (IOException e1) {
				log.warning(e1.getMessage());
			}
		}
		//TODO modificare il logout e non disconnettere dal server
		if(e.getActionCommand().equals("logout")) {
			scrivi.println("LOGOUT");
			scrivi.flush();
			log.info("logged out");
			g.connected();
			
		}
		if(e.getActionCommand().equals("plus")) {
			log.info("premuto plus");
			scrivi.println("PLUS:1");
			scrivi.flush();
		}
		if(e.getActionCommand().equals("minus")) {
			log.info("premuto minus");
			scrivi.println("MINUS:1");
			scrivi.flush();
		}
		
	}
	public void tryConnect() {
		int counter = 0;
		try {
			s = new Socket("localhost", 4000);
			log.info("connesso");
			g.connected();
		}
		catch(Exception e) {
			log.info("non connesso");
			counter++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(counter >= 20) {
				log.severe("connessione fallita");
			}
			else {
				tryConnect();
			}
		}
	}

}
