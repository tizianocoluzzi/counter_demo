package Notifiche;

import java.time.LocalDate;

public abstract class Notifica {
	protected String testo;
	private String mittente;
	private LocalDate data;
	public Notifica(String mittente, String data) {
		this.mittente = mittente;
		this.data = LocalDate.parse(data);
	}
	public String getTesto() {
		return testo;
	}
	@Override
	public String toString() {
		return mittente + "." + data.toString();
		
	}
	public String getMittente() {
		return mittente;
	}
	public String getData() {
		return data.toString();
	}
}