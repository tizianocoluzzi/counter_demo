package Notifiche;

public class NotificaAmicizia extends Notifica{

	public NotificaAmicizia(String mittente, String data) {
		super(mittente, data);
		testo = mittente + " vuole fare amicizia ";
	}
	public boolean equals(Object o) {
		if(!o.getClass().equals(NotificaAmicizia.class)) return false;
		return this.getMittente().equals(((Notifica) o).getMittente());
	}
	
}
