package lp2.visao;

import java.awt.Color;
import java.awt.Container;
import java.io.IOException;
import java.text.ParseException;
import lp2.controle.Funcoes;
import lp2.modelo.CompromissoFamiliar;
import lp2.modelo.CompromissoProfissional;
import lp2.modelo.Evento;
import lp2.modelo.Lazer;
import lp2.modelo.Nota;


public interface Interface {
	public void adicionarNota(Nota nota);
	public void adicionarNota(CompromissoFamiliar nota);
	public void adicionarNota(CompromissoProfissional nota);
	public void adicionarNota(Evento nota);
	public void adicionarNota(Lazer nota);
    public Nota getNota(int id);
    public void removerNota(int id);
    public void atualizarNotas() throws ParseException;
	public int getTUltimoId();
	public void printar(int id) throws ParseException;
	public int salvar() throws IOException;
	public void AbrirBDD() throws IOException, NumberFormatException, ParseException ;
	public String getQuantidadeTarefa(String dataFormatada, Funcoes banco2);
	public Container getTarefaDodia(String dataFormatada);
	public Color getCor(String tipo);
}