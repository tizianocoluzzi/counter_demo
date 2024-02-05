package Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JTextField;

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
					//g.count.setEnabled(true);
					g.setCount(msg[1]);
					if(msg[1].equals("0")) {
						g.zero_state();
					}
					else {
						g.logged();
					}
				}
				if(msg[0].equals("SEARCH")) {
					g.visita(msg[1], msg[2]);
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
	public Listener(Gui g){
		this.g = g;
		log = Logger.getLogger("main thread");
	}
	@Override
	public synchronized void actionPerformed(ActionEvent e) {
		log.info(""+ e.paramString() );
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
				scrivi.println("USERNAME:" + g.getUsername());
				scrivi.flush();
				r = new Runner(this, g);
				t = new Thread(r);
				t.start();
			} catch (IOException e1) {
				log.warning(e1.getMessage());
			}
		}
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
		if (e.getActionCommand().equals("back")) {
			log.info("premuto back");
			g.logged();
		}
		if(e.getActionCommand().equals("search")) {
			log.info("premuto search");
			scrivi.println("SEARCH:"+g.getMainPage().getVisitaUsername());
			scrivi.flush();
		}
	}

	private int counter = 0; //conta quante volte tenta la connessione
	public void tryConnect() {
		try {
			s = new Socket("localhost", 4000);
			log.info("connesso");
			counter = 0; //reset counter
			g.connected();
			
		}
		catch(IOException e) {
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
