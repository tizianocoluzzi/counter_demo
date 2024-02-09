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
					user = findUser(msg[1]);
					
					if(this.findUser(msg[1]) != null) {
						log.info("utente trovato");
					}
					else {
						user = createUser(msg[1]);
						//TODO cambiare tutto cio che viene gestito del file in una classe file manager
						scriviFile = new PrintWriter(new FileWriter(f, true));
						scriviFile.println(user.toString());
						scriviFile.flush();
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
		log.info("entro nel FindUser");
		try {
			log.info(f.toString());
			Scanner data = new Scanner(this.f);
			String s;
			while(data.hasNextLine()) {
				s = data.nextLine();
				log.info(s);
				String[] str = s.split(":");
				if(str[0].equals(username)) {
					if(str.length == 4) {
						return new User(username, Integer.parseInt(str[1]), Integer.parseInt(str[2]), Integer.parseInt(str[3]), str[4]);
					}
					return new User(username, Integer.parseInt(str[1]), Integer.parseInt(str[2]), Integer.parseInt(str[3]));
				}
			}
			data.close();
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
	private void editUser(User user) {
		
		Scanner fr;
		log.info("chiamata funzione editUser");
		try {
			fr = new Scanner(this.f);
			String str;
			String tot = "";
			while(fr.hasNextLine()) {
				str = fr.nextLine();
				log.info("stringa in letttura attuale: " + str );
				if(str.contains(user.getUsername())){
					log.info("trovata stringa da replace");
					str = user.toString();
				}
				tot = tot + str + "\n";
				log.info("tot attuale: " + tot);
			}
			tot = tot.substring(0, tot.length() - 1);//per togliere l'ultimo \n
			log.info("uscito dal file di modifica con tot:" + tot);
			//lo apro dopo l'immagazzinamento delle info
			PrintWriter pw = new PrintWriter(new FileWriter(f));
			pw.println(tot);
			pw.flush();
			pw.close();
			fr.close();
		} catch (Exception e) {
			log.severe(e.getMessage());
		}
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
