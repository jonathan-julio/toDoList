package lp2.controle;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.swing.JButton;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import lp2.modelo.*;
import lp2.visao.Interface;

public class Funcoes implements Interface{
	public ArrayList<Nota> notas = new ArrayList<>();
	public static ArrayList<Integer> ids = new ArrayList<Integer>();
	public Funcoes() {
		
	}
	public void adicionarNota(Nota nota) {
		notas.add(nota);
	}
	public void adicionarNota(CompromissoFamiliar nota) {
		notas.add(nota);
		ids.add(nota.getId());
	}
	public void adicionarNota(CompromissoProfissional nota) {
		notas.add(nota);
		ids.add(nota.getId());
	}
	public void adicionarNota(Evento nota) {
		notas.add(nota);
		ids.add(nota.getId());
	}
	public void adicionarNota(Lazer nota) {
		notas.add(nota);
		ids.add(nota.getId());
	}
    public Nota getNota(int id) {
    	for(int i = 0; i < notas.size(); i++) {
    		if (notas.get(i).getId() == id) {
    			return notas.get(i);
    		}
    	}		  
		return null;
	}
    public void removerNota(int id) {
    	for(int i = 0; i < notas.size(); i++) {
    		if (notas.get(i).getId() == id) {
    			notas.remove(i);
    			for(int i1 = 0; i1 < ids.size(); i1++) {
    	    		if (ids.get(i1) == id) {
    	    			ids.remove(i1);
    	    		}
    	    	}
    		}
    	}	
	}
    public void atualizarNotas() throws ParseException {
    	for(int i = 0; i < notas.size(); i++) {
    		printar(notas.get(i).getId());
    	}
	}
	public int getTUltimoId() {
		int valor = 0;
		if(ids.size() == 0) {
			valor = 1;
			return 1;
		}
		else {
			
			int lastValue = ids.get(ids.size()-1);
			valor = lastValue + 1;
			return valor;
		}
	}
	public void printar(int id) throws ParseException {
		Nota nota= getNota(id);
		System.out.println("Id:		" + nota.getId());
		System.out.println("Titulo:	" + nota.getTitulo());
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
		String date = formato.format(nota.getData());
		System.out.println("Data: 	" + date);
		System.out.println("Tipo:	" + nota.getTipo());
	}
	public void alterarData(int id, int dia, int mes, int ano, Funcoes banco2) throws ParseException {
		for(int i = 0; i < notas.size(); i++) {
    		if (banco2.notas.get(i).getId() == id) {
    			banco2.notas.get(i).setData(dia, mes, ano);
    		}
    	}
	}
	public int salvar() throws IOException {
		FileWriter arq = new FileWriter("\bBancoDadosAgenda.txt");
	    try (PrintWriter gravarArq = new PrintWriter(arq)) {
	    	for(int i = 0; i < notas.size(); i++) {
	    		gravarArq.printf(notas.get(i).getId()+ ",");
	    		gravarArq.printf(notas.get(i).getTitulo()+ ",");
	    		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
	    		String date = formato.format(notas.get(i).getData());
	    		gravarArq.printf(date+ ",");
	    		gravarArq.printf(notas.get(i).getTipo()+ ",");
	    		gravarArq.printf("%n");
	    		}
		}
		return 1;
		
	}
	public void AbrirBDD() throws IOException, NumberFormatException, ParseException {
		try {
		      FileReader arq = new FileReader("\bBancoDadosAgenda.txt");
		      BufferedReader lerArq = new BufferedReader(arq);

		      String linha = lerArq.readLine(); 
		      while (linha != null) {
		    	String[] textoSeparado = linha.split(",");
		    	if(textoSeparado[3].equals("Lazer")) {
		    		String[] data = textoSeparado[2].split("/");
		    		Lazer n = new Lazer(textoSeparado[1], Integer.parseInt(textoSeparado[0]) , Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]) );
		    		notas.add(n);
		    		ids.add(Integer.parseInt(textoSeparado[0]));
		    	}
		    	if(textoSeparado[3].equals("Evento")) {
		    		String[] data = textoSeparado[2].split("/");
		    		Evento n = new Evento(textoSeparado[1], Integer.parseInt(textoSeparado[0]) , Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]) );
		    		notas.add(n);
		    		ids.add(Integer.parseInt(textoSeparado[0]));
		    	}
		    	if(textoSeparado[3].equals("Compromisso Familiar")) {
		    		String[] data = textoSeparado[2].split("/");
		    		CompromissoFamiliar n = new CompromissoFamiliar(textoSeparado[1], Integer.parseInt(textoSeparado[0]) , Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]) );
		    		notas.add(n);
		    		ids.add(Integer.parseInt(textoSeparado[0]));
		    	}
		    	if(textoSeparado[3].equals("Compromisso Profissional")) {
		    		String[] data = textoSeparado[2].split("/");
		    		CompromissoProfissional n = new CompromissoProfissional(textoSeparado[1], Integer.parseInt(textoSeparado[0]) , Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]) );
		    		notas.add(n);
		    		ids.add(Integer.parseInt(textoSeparado[0]));
		    	}
		        linha = lerArq.readLine(); // lê da segunda até a última linha
		      }
		      arq.close();
		    } catch (IOException e) {
		        System.err.printf("Erro na abertura do arquivo: %s.\n",
		          e.getMessage());
		    }
		}
	public String getQuantidadeTarefa(String dataFormatada, Funcoes banco2) {
	    int saida = 0;
	    for(int i = 0; i < banco2.notas.size(); i++) {
	    		Date aux = banco2.notas.get(i).getData();
	    		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				String me = formato.format(aux);
	    		if(me.equals(dataFormatada)){
	    			saida +=1;
	    		}
	    		else {
	    			continue;
	    		}
	    	}
	    return Integer.toString(saida);  
	}
	public Container getTarefaDodia(String dataFormatada) {
		Container atividade = new Container();
		int contador = 0;
	    for(int i = 0; i < notas.size(); i++) {
	    		Date aux = notas.get(i).getData();
	    		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				String me = formato.format(aux);
	    		if(me.equals(dataFormatada)){
	    			String Detalhes = "ID: " + Integer.toString(notas.get(i).getId()) 
	    			+ "	Titulo: " + notas.get(i).getTitulo()
	    			+ "	Data: " + me
	    			+ "	Tipo: " + notas.get(i).getTipo();
	    			JButton listar = new JButton(Detalhes);
	    			listar.setContentAreaFilled(false);
	    			listar.setOpaque(true);
	    			listar.setBackground(getCor(notas.get(i).getTipo()));
	    			atividade.add(listar);
	    			contador+=1;
	    		}
	    	}
	    atividade.setLayout(new GridLayout(contador,1));
	    return atividade;  
	}
	public Color getCor(String tipo) {
		if(tipo.equals("Lazer")) {
			return Color.blue;
		}
		if(tipo.equals("Evento")) {
			return Color.gray;	
		}
		if(tipo.equals("Compromisso Familiar")) {
			return Color.LIGHT_GRAY;
		}
		if(tipo.equals("Compromisso Profissional")) {
			return Color.magenta;
		}
		return null;
	}
}
