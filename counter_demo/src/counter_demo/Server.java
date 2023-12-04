package counter_demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileNotFoundException;

class Run implements Runnable{
	public Logger log;
	public PrintWriter scrivi;
	public Scanner leggi;
	public ServerSocket server;
	public Socket socket;
	public File f;
	public Scanner leggiFile;
	public PrintWriter scriviFile; 
	public User user;
	public Run(Socket socket) {
		log = Logger.getLogger("thread");
		this.socket = socket;
		log.info("creato runner");
		f = new File("./data.txt");
		log.info(f.toString());
		
	}
	@Override
	public void run() {
		log.info("connessione accettata");
		try {
			log.info(f.toString());
			leggi = new Scanner(socket.getInputStream());
			scrivi = new PrintWriter(socket.getOutputStream());
			String ricevuto;
			while(leggi.hasNextLine()) {
				ricevuto = leggi.nextLine();
				log.info(ricevuto);
				String[] msg = ricevuto.split(":");
				if(msg[0].equals("USERNAME")) {
					log.info("ricevuto un username" );
					user = findUser(msg[1], f);
					
					if(this.findUser(msg[1], f) != null) {
						log.info("utente trovato");
					}
					else {
						user = createUser(msg[1]);
						scriviFile = new PrintWriter(f);
						scriviFile.println(msg[1] + ":" + "0");
						scriviFile.flush();
						log.info("scritto username");
					}
					scrivi.println("UPDATE:" + user.getCount());
					scrivi.flush();
					log.info("inviato UPDATE");
				}
				else if(msg[0].equals("INTERRUPT")) {
					log.info("ricevuto interrupt");
					socket.close();
				}
				else if(msg[0].equals("PLUS")) {
					log.info("ricevuto plus");
				}
				else if(msg[0].equals("MINUS")) {
					log.info("ricevuto minus");
				}
				else {
					log.info("errore");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private User findUser(String username, File f){
		File file = f;
		log.info("entro nel FindUser");
		try {
			log.info(file.toString());
			Scanner data = new Scanner(file);
			String s;
			while(data.hasNextLine()) {
				s = data.nextLine();
				log.info(s);
				String[] str = s.split(":");
				if(str[0].equals(username)) {
					return new User(username, Integer.parseInt(str[1]));
				}
			}
			return null;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			log.severe("missing database");
			try {
				f.createNewFile();
				return null;
			} catch (IOException e1) {
				log.severe("un macello col data");
				return null;
			}
			
		}
	}
	private User createUser(String username) {
		return new User(username);
	}
	
}

public class Server {
	Logger log;
	public ServerSocket server;
	public Socket socket;
	public Thread t;
	public Run r;
	int port;
	public static void main(String[] args) {
		Server s = new Server();
	}
	public Server() {
		try {
			log = Logger.getLogger("server");
			log.info("entrato");
			server = new ServerSocket(4000);
			server.setReuseAddress(true);
			log.info("creato");
			while(true) {
				socket = server.accept();
				log.info("connessione accettata");
				Run r = new Run(socket);
				Thread t = new Thread(r);
				t.start();
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
