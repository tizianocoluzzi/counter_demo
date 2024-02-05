package Gui;

import java.awt.*;

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
		main.zero_state();
		
	}
	public void visita(String u, String c) {
		visita.setVisible(true);
		main.setVisible(false);
		login.setVisible(false);
		visita.visita(u, c);
	}
	public String getUsername() {
		return login.username.getText();
	}
	private void setUsername() {
		//lo prende appena passa dallo stato di connected a quello di log
		username = login.username.getText();
	}
	public Listener getListener() {
		return l;
	}
	public void setCount(String a) {
		main.setCount(a);
	}
	public MainPage getMainPage() {
		return main;
	}
}
