package Gui;

import java.awt.*;

import javax.swing.*;


//TODO cambiare la classe GUI in una classe generica formata da tre finestre: Login Main e Visit
//perche bisogna gestire correttamente l'aprtura e la chiusura di queste tre finestre nei vari momenti
class Login extends JFrame{
	JTextField password;
	JLabel lpassword;
	JTextField username;
	JLabel lusername;
	JButton login;
	JPanel pusername;
	JPanel ppassword;
	JPanel plogin;
	Listener l;
	//JButton logout;
	Login(Listener l) {
		password = new JTextField(20);
		username = new JTextField(20);
		lpassword = new JLabel("password:");
		lusername = new JLabel("username:");
		login = new JButton("login");
		
		pusername = new JPanel();
		ppassword = new JPanel();
		plogin = new JPanel();
		
		pusername.setLayout(new FlowLayout());
		ppassword.setLayout(new FlowLayout());
		
		pusername.add(lusername);
		pusername.add(username);
		ppassword.add(lpassword);
		ppassword.add(password);
		plogin.add(login);
		
		this.setLayout(new GridLayout(5, 1));
		this.add(pusername);
		this.add(ppassword);
		this.add(plogin);
		
		//this.setSize(900, 400);
		this.pack();
		this.setAlwaysOnTop(true);
		this.setVisible(true);
		

		this.l = l;
		login.addActionListener(this.l);
		login.setActionCommand("login");
	}
}
public class Gui extends JFrame{
	JButton login;
	JButton plus;
	JButton minus;
	JTextField count;
	JTextField username;
	JLabel lusername;
	JButton connect;
	JButton disconnect;
	JPanel north;
	JPanel center;
	Listener l;
	Login logn;
	public Gui() {
		plus = new JButton("+");
		minus = new JButton("-");
		count = new JTextField(5);
		count.setEditable(false);
		center = new JPanel();
		center.setLayout(new FlowLayout());
		
		username = new JTextField(15);
		lusername = new JLabel("username:");
		connect = new JButton("connect");
		disconnect = new JButton("logout");
		login = new JButton("login");
		
		center.add(minus);
		center.add(count);
		center.add(plus);
		north = new JPanel();
		north.setLayout(new FlowLayout());
		//north.add(lusername);
		//north.add(username);
		//north.add(connect);
		//north.add(login);
		north.add(disconnect);
		
		logn = new Login(l);
		
		this.init();
		//this.setSize(900, 400);
		this.add(north, BorderLayout.NORTH);
		this.add(center ,BorderLayout.CENTER);
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.setVisible(true);
		
		l = new Listener(this);
		
		login.addActionListener(l);
		plus.setActionCommand("plus");
		minus.setActionCommand("minus");
		plus.addActionListener(l);
		minus.addActionListener(l);
		connect.addActionListener(l);
		disconnect.addActionListener(l);
		
	}
	public void init() {
		//connect.setEnabled(true);
		//gestione delle due finstre
		this.setVisible(false);
		logn.setVisible(true);
		
		disconnect.setEnabled(false);
		login.setEnabled(false);
		plus.setEnabled(false);
		minus.setEnabled(false);
		count.setForeground(Color.gray);
	}
	public void connected() {
		System.out.println("chiamata connected");
		//connect.setEnabled(false);
		logn.setVisible(true);
		this.setVisible(false);
		
		
		disconnect.setEnabled(true);
		login.setEnabled(true);
		plus.setEnabled(false);
		minus.setEnabled(false);
		count.setForeground(Color.gray);
	}
	public void logged() {
		//connect.setEnabled(false);
		this.setVisible(true);
		logn.setVisible(false);
		
		disconnect.setEnabled(true);
		login.setEnabled(false);
		plus.setEnabled(true);
		minus.setEnabled(true);
		count.setForeground(Color.black);
	}
	public void zero_state() {
		//connect.setEnabled(false);
		disconnect.setEnabled(true);
		login.setEnabled(false);
		plus.setEnabled(true);
		minus.setEnabled(false);
		count.setForeground(Color.black);
	}
}
