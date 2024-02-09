package Gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
		this.setTitle("Login");
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
		
		this.setSize(900, 400);
		//this.pack();
		this.setAlwaysOnTop(true);
		
		this.l = l;
		login.addActionListener(this.l);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void init() {
		username.setText("");
		password.setText("");
		login.setEnabled(false);
	}
	public void connected() {
		login.setEnabled(true);
	}
}