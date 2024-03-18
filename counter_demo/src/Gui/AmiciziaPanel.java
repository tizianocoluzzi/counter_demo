package Gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AmiciziaPanel extends JPanel{
	String username;
	JLabel lusername;
	JPanel buttons;
	JButton accetta;
	JButton rifiuta;
	public AmiciziaPanel(String username) {
		this.username = username;
		lusername = new JLabel(username + " vuole essere tuo amico:\n");
		accetta = new JButton("accetta");
		rifiuta = new JButton("rifiuta");
		buttons = new JPanel();
		buttons.setLayout(new GridLayout(1, 2));
		buttons.add(accetta);
		buttons.add(rifiuta);
		this.setLayout(new GridLayout(2, 1));
		this.add(lusername);
		this.add(buttons);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
}
