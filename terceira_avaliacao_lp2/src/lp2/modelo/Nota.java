package lp2.modelo;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public abstract class Nota{
	protected int id;
	protected String titulo;
	protected Date data;


	
	public Nota(String titulo, int id, int dia, int mes, int ano ) throws ParseException {
		setTitulo(titulo);
		setId(id);
		setData(dia,mes, ano);
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Date getData() {
		return data;
	}

	public void setData(int dia, int mes, int ano) throws ParseException {
		String diaStr = Integer.toString(dia);
		String mesStr = Integer.toString(mes);
		String anoStr = Integer.toString(ano);
		String dataStr = diaStr + "/" + mesStr + "/" + anoStr;
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
		Date dataAux = formato.parse(dataStr);
		
		this.data = dataAux;
	}

	public abstract String getTipo();
	
}
