package org.generation.italy.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Movimento {
	public int idMovimento;
	public String codiceProdotto;
	public int quantitaProdotto;
	public String codiceMovimento;
	public LocalDate data;
	public String riferimento;
	@Override
	public String toString() {
		DateTimeFormatter df=DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return "Movimento "+idMovimento+ "[codiceProdotto=" + codiceProdotto + ", quantitaProdotto=" + quantitaProdotto
				+ ", codiceMovimento=" + codiceMovimento + ", data=" + data.format(df) + ", riferimento=" + riferimento + "]";
	}
}
