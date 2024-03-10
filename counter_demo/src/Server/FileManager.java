 package Server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Logger;

import Utente.User;

//posso potenzialmente cambiare il modo di scrivere le informazione nel database 
// e il tipo di database modificando solo ed esclusivamente questa classe 
public class FileManager {
	private Logger log;
	private File f;
	private Scanner leggiFile;
	private PrintWriter scriviFile; 
	public FileManager() {
		log = Logger.getLogger("FileManager");
		f = new File("./data.txt");
	}
	public User findUser(String username) {
		User user = null;
		try {
			Scanner data = new Scanner(this.f);
			String s;
			while(data.hasNextLine()) {
				s = data.nextLine();
				String[] str = s.split(":");
				if(str[0].equals(username)) {
					if(str.length == 4) {
						user = new User(username, Integer.parseInt(str[1]), Integer.parseInt(str[2]), Integer.parseInt(str[3]), str[4]);
					}
					else {
						user = new User(username, Integer.parseInt(str[1]), Integer.parseInt(str[2]), Integer.parseInt(str[3]));
					}
					try {
						log.info("aggiungo gli amici");
						user.setFriends(str[5]);
					}catch(IndexOutOfBoundsException e) {
						user.setFriends("");
					}
				}
			}
			data.close();
			return user;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			try {
				f.createNewFile();
				return null;
			} catch (IOException e1) {
				return null;
			}
			
		}
	}
	public User createUser(String username) throws IOException {
		User user = new User(username);
		scriviFile = new PrintWriter(new FileWriter(f, true));
		scriviFile.println(user.toString());
		scriviFile.flush();
		return user;
		
	}
	public void editUser(User user) {
		Scanner fr;
		try {
			fr = new Scanner(this.f);
			String str;
			String tot = "";
			while(fr.hasNextLine()) {
				str = fr.nextLine();
				if(str.contains(user.getUsername())){
					str = user.toString();
				}
				tot = tot + str + "\n";
			}
			tot = tot.substring(0, tot.length() - 1);//per togliere l'ultimo \n
			//lo apro dopo l'immagazzinamento delle info
			PrintWriter pw = new PrintWriter(new FileWriter(f));
			pw.println(tot);
			pw.flush();
			pw.close();
			fr.close();
		} catch (Exception e) {
			log.warning(e.getMessage());
		}
	}
}
