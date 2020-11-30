package lp2.modelo;

import java.text.ParseException;

public class CompromissoProfissional extends Nota{
	
	protected String tipo;
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public CompromissoProfissional(String titulo,int id, int dia, int mes, int ano) throws ParseException {
		super(titulo, id, dia, mes, ano);
		setTipo("Compromisso Profissional");
	}

}
