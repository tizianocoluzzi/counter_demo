package Gui;

import java.awt.*;
import java.time.LocalDate;

import javax.swing.*;


public class Gui{
	private String username;
	Listener l;
	Login login;
	MainPage main;
	Visita visita;
	public Gui() {
		l = new Listener(this);
		main = new MainPage(l);
		login = new Login(l);
		visita = new Visita(l);
		this.init();
		l.tryConnect();
	}
	public void init() {
		System.out.println("intit");
		main.init();
		login.init();
		main.setVisible(false);
		visita.setVisible(false);
		login.setVisible(true);
	}
	public void connected() {
		System.out.println("chiamata connected");
		login.setVisible(true);
		main.setVisible(false);
		visita.setVisible(false);
		main.connected();
		login.connected();
	}
	public void logged() {
		setUsername();
		main.setVisible(true);
		login.setVisible(false);
		visita.setVisible(false);
		main.logged();
	}
	public void zero_state() {
		main.setVisible(true);
		login.setVisible(false);
		visita.setVisible(false);
		main.zero_state();
		
	}
	public void visita(String u, String c, String act, String max) {
		visita.setVisible(true);
		main.setVisible(false);
		login.setVisible(false);
		visita.visita(u, c, act, max);
	}
	public String getUsername() {
		if (username == null) {
			username = login.username.getText();
		}
		return username;
	}
	private void setUsername() {
		//lo prende appena passa dallo stato di connected a quello di log
		username = login.username.getText();
		main.setUsername(username); //inserisce l'username nella main
	}
	public Listener getListener() {
		return l;
	}
	public void setCount(String a) {
		main.setCount(a);
	}
	public void setActScore(String a) {
		main.setActual(a);
	}
	public void setMaxScore(String a) {
		main.setMax(a);
	}
	public MainPage getMainPage() {
		return main;
	}
	public void setFriendsNames(String[] friendsList) {
		main.createButtonFriends(friendsList);
	}
}
