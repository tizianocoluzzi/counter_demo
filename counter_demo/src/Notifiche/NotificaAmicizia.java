package Notifiche;

public class NotificaAmicizia extends Notifica{

	public NotificaAmicizia(String mittente, String destinatario) {
		super(mittente, destinatario);
		testo = mittente + " vuole fare amicizia ";
	}
	
}
