package Gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CountAmiciPanel extends JPanel{
	private String username;
	private JLabel message;
	public CountAmiciPanel(String username) {
		message = new JLabel(username + " ha tirato lo sciacquone");
		this.add(message);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
}
