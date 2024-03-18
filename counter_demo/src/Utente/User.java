package Utente;

import java.time.LocalDate;
import java.util.LinkedList;

import Counter.Counter;
//in realta non sto usando il tipo notifica perche sembra inutile
import Notifiche.Notifica;
import Notifiche.NotificaAmicizia;
import Notifiche.NotificaCount;

public class User {
	private LinkedList<Notifica> notifiche;
	private String notificheAmicizia;
	private String notificheCount;
	private String friends;
	private Counter counter;
	private String username;

	public User(String username, int count, int actScore, int maxScore, String lastCount) {
		this.username = username;
		this.counter = new Counter(count, actScore, maxScore, lastCount);
		friends = "";
		notifiche = new LinkedList<Notifica>();
		notificheAmicizia = "";
		notificheCount = "";
	}

	public User(String username, int count, int actScore, int maxScore) {
		this(username, count, actScore, maxScore, "");
	}

	public User(String username) {
		this(username, 0, 0, 0, "");
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
		return username + ":" + counter.toString() + ":" + friends + ":" + getNotifiche();
	}

	public String getFriends() {
		return friends;
	}

	public void setFriends(String friends) {
		this.friends = friends;
	}

	public String getNotifiche() {
		return notificheAmicizia + "/" + notificheCount;
	}

	public void setNotifiche(String n) {
		notificheAmicizia = n.split("/")[0];
		notificheCount = n.split("/")[1];
		String[] a = notificheAmicizia.split("$");
		String[] c = notificheCount.split("$");
		for (int i = 0; i < a.length; i++) {
			NotificaAmicizia r = new NotificaAmicizia(a[i].split(".")[0], a[i].split(".")[1]);
			if (r != null)
				notifiche.add(r);
		}
		for (int i = 0; i < c.length; i++) {
			NotificaCount r = new NotificaCount(c[i].split(".")[0], c[i].split(".")[1]);
			if (r != null)
				notifiche.add(r);
		}
	}

	public void addNotificaAmicizia(String mitt) {
		NotificaAmicizia n = new NotificaAmicizia(mitt, LocalDate.now().toString());
		if (!notificaAmiciziaValida(n)) {
			System.out.println("notifica non inviata");
			return;
		}
		notifiche.add(n);
		if (notificheAmicizia == "") {
			notificheAmicizia = n.toString();
			return;
		}
		notificheAmicizia = notificheAmicizia + "$" + n.toString();
	}

	public void addNotificaCount(String mitt) {
		NotificaCount n = new NotificaCount(mitt, LocalDate.now().toString());
		if (notifiche.contains(n)) {
			return;
		}
		notifiche.add(n);
		if (notificheCount == "") {
			notificheCount = n.toString();
			return;
		}
		notificheCount = notificheCount + "$" + n.toString();
	}

	private boolean notificaAmiciziaValida(NotificaAmicizia n) {
		return n!= null && !areFriends(n.getMittente()) && !notificaGiaInviata(n);
	}

	private boolean areFriends(String u) {
		return friends.contains(u);
	}
	private boolean notificaGiaInviata(NotificaAmicizia n) {
		return notificheAmicizia.contains(n.getMittente());
	}

}
