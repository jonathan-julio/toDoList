package lp2.modelo;

import java.text.ParseException;

public class Evento extends Nota{
	
	protected String tipo;
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Evento(String titulo, int id, int dia, int mes, int ano) throws ParseException {
		super(titulo, id, dia, mes, ano);
		setTipo("Evento");
	}

}
