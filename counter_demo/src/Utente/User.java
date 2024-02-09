package Utente;

import Counter.Counter;

public class User {
	private User[] friends;
	private Counter counter;
	private String username;
	public User(String username, int count, int actScore, int maxScore, String lastCount) {
		this.username = username;
		this.counter = new Counter(count, actScore, maxScore, lastCount);
	}
	public User(String username, int count, int actScore, int maxScore) {
		this.username = username;
		this.counter = new Counter(count, actScore, maxScore);
	}
	public User(String username) {
		this.username = username;
		this.counter = new Counter();
	}
	public void incCounter() {
		counter.incCount();
	}
	public void decCounter() {
		counter.decCount();
	}
	public int getCount() {
		return counter.getCount();
	}
	public String getUsername() {
		return username;
	}
	public int getActScore() {
		return counter.getActScore();
	}
	public int getMaxScore() {
		return counter.getMaxScore();
	}
	public String getLastCount() {
		return counter.getLastCount();
	}
	
	public String toString() {
		return username + ":" + counter.toString();
 	}
	
}
