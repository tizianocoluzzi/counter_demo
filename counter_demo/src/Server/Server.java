package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Logger;

import Utente.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

class Run implements Runnable{
	public Logger log;
	public PrintWriter scrivi;
	public Scanner leggi;
	public ServerSocket server;
	public Socket socket;
	public User user;
	private FileManager fileManager;
	public Run(Socket socket) {
		log = Logger.getLogger("thread");
		this.socket = socket;
		log.info("creato runner");
		fileManager = new FileManager();
		
	}
	@Override
	public void run() {
		log.info("connessione accettata");
		try {
			leggi = new Scanner(socket.getInputStream());
			scrivi = new PrintWriter(socket.getOutputStream());
			String ricevuto;
			while(leggi.hasNextLine()) {
				ricevuto = leggi.nextLine();
				log.info(ricevuto);
				String[] msg = ricevuto.split(":");
				if(msg[0].equals("USERNAME")) {
					log.info("ricevuto un username" );
					user = findUser(msg[1]);
					
					if(this.findUser(msg[1]) != null) {
						log.info("utente trovato");
					}
					else {
						user = createUser(msg[1]);
						log.info("scritto username");
					}
					scrivi.println("UPDATE:" + user.toString());
					scrivi.flush();
					log.info("inviato UPDATE");
				}
				else if(msg[0].equals("LOGOUT")) {
					user = null;
				}
				else if(msg[0].equals("PLUS")) {
					log.info("ricevuto plus");
					user.incCounter();
					editUser(user);
					scrivi.println("UPDATE:" + user.toString());
					scrivi.flush();
				}
				else if(msg[0].equals("MINUS")) {
					log.info("ricevuto minus");
					user.decCounter();
					editUser(user);
					//user.decCounter();
					scrivi.println("UPDATE:" + user.toString());
					scrivi.flush();
					
				}
				else if(msg[0].equals("SEARCH")) {
					log.info("ricevuto search");
					User cercato = findUser(msg[1]);
					scrivi.println("SEARCH:" + cercato.toString());
					scrivi.flush();
				}
				
				else {
					log.info("errore");
				}
				
			}
			log.info("uscito dal while");
		} catch (IOException e) {
			log.warning(e.getMessage());
		}
	}
	private User findUser(String username){
		return fileManager.findUser(username);
	}
	private User createUser(String username) throws IOException {
		return fileManager.createUser(username);
	}
	private void editUser(User user) {
		fileManager.editUser(user);

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
			log.warning(e.getMessage());
		}
	}
	
}
