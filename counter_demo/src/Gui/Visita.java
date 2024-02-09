package Gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Visita extends JFrame{
	private JLabel username;
	private JTextField count;
	private JLabel current;
	private JLabel max;
	private JTextField fmax;
	private JTextField fcurrent;
	private JButton back;
	private JPanel north;
	private JPanel south;
	private JPanel center;
	private boolean premuto;
	public Visita(Listener l){
		username = new JLabel();
		count = new JTextField(5);
		count.setEditable(false);
		current = new JLabel("current:");
		max = new JLabel("max:");
		fmax = new JTextField(2);
		fmax.setEditable(false);
		fcurrent = new JTextField(2);
		fcurrent.setEditable(false);
		back = new JButton("<-");
		back.setActionCommand("back");
		north = new JPanel();
		south = new JPanel();
		center = new JPanel();
		premuto = false;
		
		north.add(back);
		north.add(username);
		center.add(count);
		south.add(current);
		south.add(fcurrent);
		south.add(max);
		south.add(fmax);
		
		
		this.setSize(900, 400);
		this.add(north, BorderLayout.NORTH);
		this.add(center ,BorderLayout.CENTER);
		this.add(south, BorderLayout.SOUTH);
		//this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		back.addActionListener(l);
	}
	public void visita(String u, String c, String act, String m) {
		username.setText(u);
		count.setText(c);
		fcurrent.setText(act);
		fmax.setText(m);
	}


}
