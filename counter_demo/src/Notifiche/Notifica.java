package Notifiche;

public abstract class Notifica {
	protected String testo;
	private String mittente;
	private String destinatario;
	public Notifica(String mittente, String destinatario) {
		this.mittente = mittente;
		this.destinatario = destinatario;
	}
	public String getTesto() {
		return testo;
	}
	
}
