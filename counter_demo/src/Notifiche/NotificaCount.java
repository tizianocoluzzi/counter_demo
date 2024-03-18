package Notifiche;

public class NotificaCount extends Notifica{
	public NotificaCount(String mittente, String data) {
		super(mittente, data);
		testo = mittente + " ha tirato lo sciacquone";
		
	}
}
