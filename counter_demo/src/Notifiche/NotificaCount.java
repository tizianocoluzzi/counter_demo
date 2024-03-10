package Notifiche;

public class NotificaCount extends Notifica{
	public NotificaCount(String mittente, String destinatario) {
		super(mittente, destinatario);
		testo = mittente + " ha tirato lo sciacquone";
		
	}
}
