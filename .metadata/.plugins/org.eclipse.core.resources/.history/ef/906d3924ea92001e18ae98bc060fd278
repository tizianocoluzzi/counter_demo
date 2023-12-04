package counter_demo;

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
					g.logged();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("connect")) {
			log.info("premuto connect");
			try {
				s = new Socket("localhost", 4000);
				g.connected();
				
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getActionCommand().equals("disconnect")) {
			try {
				s.close();
				log.info("connessione chiusa");
				g.init();
			} catch (IOException e1) {
				log.info("problemi con la chiusura della connessione");
			}
		}
		if(e.getActionCommand().equals("plus")) {
			scrivi.println("PLUS");
		}
		if(e.getActionCommand().equals("minus")) {
			scrivi.println("MINUS");
		}
		
	}

}
