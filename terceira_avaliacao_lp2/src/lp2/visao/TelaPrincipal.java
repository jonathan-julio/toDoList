package lp2.visao;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import lp2.controle.Calendario;
import lp2.controle.Funcoes;



public class TelaPrincipal extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	Funcoes banco = new Funcoes();
	Container container = this.getContentPane();
	Container controle = new Container();
	//itensmenu
	JMenuBar mnbar = new JMenuBar();	
	JMenu menuAdd = new JMenu("Adicionar tarefa");
	JMenu menuAlterar = new JMenu("Alterar tarefa");
	JMenu menuCalendario = new JMenu("Calendario");
	//subItens
	JMenuItem mItem1 = new JMenuItem("Compromisso Familiar");
	JMenuItem mItem2 = new JMenuItem("Compromisso Profissional");
	JMenuItem mItem3 = new JMenuItem("Evento");
	JMenuItem mItem4 = new JMenuItem("Lazer");
	JMenuItem mItem5 = new JMenuItem("Remover tarefa");
	JMenuItem mItem6 = new JMenuItem("Alterar data");
	JMenuItem mItem7 = new JMenuItem("Atualizar");
	JMenuItem mItem8 = new JMenuItem("Sair");
	
	
	
	DateFormat dateFormat = new SimpleDateFormat("MM");
	Date date = new Date();
	String mes = dateFormat.format(date);
	int mesAtual = Integer.parseInt(mes);
	
	public TelaPrincipal() throws IOException, ParseException{
		banco.AbrirBDD();
	    setDefaultCloseOperation(EXIT_ON_CLOSE );

	    setResizable(false);
	    super.setSize(720,480);
	    super.setTitle("Calendario");
	    
		setJMenuBar(mnbar);
		mnbar.add(menuAdd);
		mnbar.add(menuAlterar);
		mnbar.add(menuCalendario);
		menuAdd.add(mItem1);
		menuAdd.add(mItem2);
		menuAdd.add(mItem3);
		menuAdd.add(mItem4);
		menuAlterar.add(mItem5);
		menuAlterar.add(mItem6);
		menuCalendario.add(mItem7);
		menuCalendario.add(mItem8);
		mItem1.addActionListener(this);	
		mItem2.addActionListener(this);
		mItem3.addActionListener(this);
		mItem4.addActionListener(this);
		mItem5.addActionListener(this);
		mItem6.addActionListener(this);
		mItem7.addActionListener(this);
		mItem8.addActionListener(this);
		mItem7.doClick();
	}
	
	
	@SuppressWarnings({ "deprecation", "unused" })
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mItem1){
			TelaNovaNota novaTela = new TelaNovaNota(mItem7,"Compromisso Familiar",banco, controle);
		}
		if (e.getSource() == mItem2){
			TelaNovaNota novaTela = new TelaNovaNota(mItem7 , "Compromisso Profissional", banco,controle);
		}
		if (e.getSource() == mItem3){
			TelaNovaNota novaTela = new TelaNovaNota(mItem7,"Evento", banco,controle);
		}
		if (e.getSource() == mItem4) {
			TelaNovaNota novaTela = new TelaNovaNota(mItem7,"Lazer", banco,controle);
		}
		if (e.getSource() == mItem5) {
			TelaAlterarNota novaTela = new TelaAlterarNota( mItem7,"Remover Atividade", banco, container);
		}
		if (e.getSource() == mItem6) {
			TelaAlterarNota novaTela = new TelaAlterarNota(mItem7,"Alterar Data", banco, container);
			
		}
		if (e.getSource() == mItem7) {
			salvar();
			if (container.countComponents() == 0) {
				container = this.getContentPane();
				Container controle = new Container();
				controle.setLayout(new GridLayout(4,7));
				Calendario.formarCalendario(controle, banco ,mesAtual);
				container.add(BorderLayout.CENTER, controle);
				controle.setVisible(true);	
			}
			else {
				super.setVisible(false);
				TelaPrincipal telaPrincipal = null;
				try {
					telaPrincipal = new TelaPrincipal();
				} catch (IOException | ParseException e1) {
					e1.printStackTrace();
				}
				telaPrincipal.setVisible(true); 
				Container container = this.getContentPane();
				controle.setLayout(new GridLayout(4,7));
				Calendario.formarCalendario(controle, banco ,mesAtual);
				container.add(BorderLayout.CENTER, controle);
				controle.setVisible(true);	
			}
		}
		if (e.getSource() == mItem8) {
			salvar();
			System.exit(0);
		}	
	}
	public void salvar() {
		try {
			banco.salvar();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException, ParseException {
		TelaPrincipal telaPrincipal = new TelaPrincipal();
		telaPrincipal.setVisible(true);   
	}
}
