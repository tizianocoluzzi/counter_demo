package counter_demo;

import java.awt.*;

import javax.swing.*;

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
	public Gui() {
		l = new Listener(this);
		plus = new JButton("+");
		minus = new JButton("-");
		count = new JTextField(5);
		count.setEditable(false);
		center = new JPanel();
		center.setLayout(new FlowLayout());
		
		username = new JTextField(15);
		lusername = new JLabel("username:");
		connect = new JButton("connect");
		disconnect = new JButton("disconnect");
		login = new JButton("login");
		
		login.addActionListener(l);
		plus.setActionCommand("plus");
		minus.setActionCommand("minus");
		plus.addActionListener(l);
		minus.addActionListener(l);
		connect.addActionListener(l);
		disconnect.addActionListener(l);
		
		center.add(minus);
		center.add(count);
		center.add(plus);
		north = new JPanel();
		north.setLayout(new FlowLayout());
		north.add(lusername);
		north.add(username);
		north.add(connect);
		north.add(login);
		north.add(disconnect);
		
		this.init();
		this.setSize(400, 900);
		this.add(north, BorderLayout.NORTH);
		this.add(center ,BorderLayout.CENTER);
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	public void init() {
		connect.setEnabled(true);
		disconnect.setEnabled(false);
		login.setEnabled(false);
		plus.setEnabled(false);
		minus.setEnabled(false);
		count.setForeground(Color.gray);
	}
	public void connected() {
		connect.setEnabled(false);
		disconnect.setEnabled(true);
		login.setEnabled(true);
		plus.setEnabled(false);
		minus.setEnabled(false);
		count.setForeground(Color.gray);
	}
	public void logged() {
		connect.setEnabled(false);
		disconnect.setEnabled(true);
		login.setEnabled(false);
		plus.setEnabled(true);
		minus.setEnabled(true);
		count.setForeground(Color.black);
	}
	public void zero_state() {
		connect.setEnabled(false);
		disconnect.setEnabled(true);
		login.setEnabled(false);
		plus.setEnabled(true);
		minus.setEnabled(false);
		count.setForeground(Color.black);
	}
}
