package counter_demo;

public class User {
	private User[] friends;
	private Counter counter;
	private String username;
	public User(String username, int count) {
		this.username = username;
		this.counter = new Counter(count);
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
}
