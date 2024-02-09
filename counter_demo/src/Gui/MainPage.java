package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

//TODO probabilmente meglio scrivere la gui in frammenti in modo da avere un codice meglio organizzato
public class MainPage extends JFrame {
	//listener
	private Listener l;
	//pannello nord
	private JPanel north;
	private JTextField search;
	private JButton menu;
	private JButton notify;
	//pannello center
	private JPanel center;
	private JButton plus;
	private JButton minus;
	private JTextField count;
	//pannello south
	private JPanel south;
	private JLabel current;
	private JLabel max;
	private JTextField fmax;
	private JTextField fcurrent;
	//panello menu(west)
	private JPanel menuPanel;
	private JLabel menuLabel;
	private JLabel usernameLabel;
	private boolean open;
	//TODO da aggiungere tutta sta roba
	private JScrollPane scrollFriends;
	private JButton[] friendList;
	private String[] friendNames;
	
	private JPanel notifyPanel;
	private JPanel mainPanel;
	public MainPage(Listener l) {
		open = true;
		plus = new JButton("+");
		minus = new JButton("-");
		count = new JTextField(5);
		count.setEditable(false);
		center = new JPanel();
		center.setLayout(new FlowLayout());
		search = new JTextField(15);
		search.setName("search");
		usernameLabel = new JLabel();
		menuLabel = new JLabel("menu:");
		south = new JPanel();
		current = new JLabel("current");
		max = new JLabel("max");
		fmax = new JTextField(2);
		fmax.setEnabled(false);
		fcurrent = new JTextField(2);
		fcurrent.setEnabled(false);
		notify = new JButton("n");
		menu = new JButton("m");
		notifyPanel = new JPanel();
		menuPanel = new JPanel();
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		center.add(minus);
		center.add(count);
		center.add(plus);
		north = new JPanel();
		north.setLayout(new FlowLayout());
		//north.add(lusername);
		//north.add(username);
		//north.add(connect);
		//north.add(login);
		//north.add(disconnect);
		north.add(menu);
		north.add(search);
		north.add(notify);
		
		south.add(current);
		south.add(fcurrent);
		south.add(max);
		south.add(fmax);
		
		menuPanel.add(menuLabel);
		menuPanel.add(usernameLabel);
		
		this.init();
		this.setSize(900, 400);
		mainPanel.add(north, BorderLayout.NORTH);
		mainPanel.add(center ,BorderLayout.CENTER);
		mainPanel.add(south, BorderLayout.SOUTH);
		mainPanel.add(menuPanel, BorderLayout.WEST);
		mainPanel.add(notifyPanel, BorderLayout.EAST);
		//this.pack();
		this.add(mainPanel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.setVisible(true);
		
		this.l = l;
		plus.setActionCommand("plus");
		minus.setActionCommand("minus");
		plus.addActionListener(l);
		minus.addActionListener(l);
		search.setActionCommand("search");
		search.addActionListener(l);
		notify.setActionCommand("notify");
		menu.setActionCommand("menu");
		notify.addActionListener(l);
		menu.addActionListener(l);
		
	}
	public void init() {
		plus.setEnabled(false);
		minus.setEnabled(false);
		count.setForeground(Color.gray);
		search.setEnabled(false);
	}
	public void connected() {
		plus.setEnabled(false);
		minus.setEnabled(false);
		count.setForeground(Color.gray);
		search.setEnabled(false);
	}
	public void logged() {
		plus.setEnabled(true);
		minus.setEnabled(true);
		count.setForeground(Color.black);
		search.setEnabled(true);
	}
	public void zero_state() {
		plus.setEnabled(true);
		minus.setEnabled(false);
		count.setForeground(Color.black);
		search.setEnabled(false);
	}
	
	public void setCount(String a) {
		count.setText(a);
	}
	public String getVisitaUsername() {
		return search.getText();
	}
	public void openMenu() {
		menuPanel.setVisible(true);
		open = true;
	}
	public void closeMenu() {
		menuPanel.setVisible(false);
		open = false;
	}
	public boolean isOpen() {
		return open;
	}
	public void setUsername(String u) {
		usernameLabel.setText(u);
	}
	public void setActual(String a) {
		fcurrent.setText(a);
	}
	public void setMax(String a) {
		fmax.setText(a);
	}
}
