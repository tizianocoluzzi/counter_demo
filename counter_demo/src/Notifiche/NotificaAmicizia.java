package Notifiche;

public class NotificaAmicizia extends Notifica{

	public NotificaAmicizia(String mittente, String data) {
		super(mittente, data);
		testo = mittente + " vuole fare amicizia ";
	}
}
