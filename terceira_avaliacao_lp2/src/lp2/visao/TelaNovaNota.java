package lp2.visao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.*;
import lp2.controle.Funcoes;
import lp2.modelo.*;

public class TelaNovaNota implements ActionListener{

	// Format as datas
	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	JMenuItem mItem7;
	// rotulos
	JLabel lTitulo  = new JLabel("Titulo.....:");
	JLabel lDia 	= new JLabel("Dia........:");
	JLabel lMes 	= new JLabel("Mes........:");
	JLabel lAno 	= new JLabel("Ano........:");
	// campos
	JTextField tTitulo  = new JTextField();
	JTextField tDia 	= new JTextField();
	JTextField tMes 	= new JTextField();
	JTextField tAno 	= new JTextField();
	// botoes
	JButton btAdiconar = new JButton("Adicionar");
	JButton btVoltar = new JButton("Cancelar");
	String str;
	Funcoes banco2; 
	JDialog NovaNota = new JDialog();
	Container ct = new Container();
	public TelaNovaNota(JMenuItem mItem7 ,String str, Funcoes banco,Container controle)  {
		this.mItem7 = mItem7;
		banco2 = banco;	
		// coordenadas
		lTitulo.setBounds(10,10,100,30);
		tTitulo.setBounds(92,10,200,25);
		lDia.setBounds(10,40,100,30);
		tDia.setBounds(92,40,65,25);
		lMes.setBounds(10,70,100,30);
		tMes.setBounds(92,70,65,25);
		lAno.setBounds(10,100,100,30);
		tAno.setBounds(92,100,65,25);
		// idem
		btAdiconar.setBounds(50,140,100,30);
		btVoltar.setBounds(230,140,100,30);	
		// adicionando componentes
		ct.add(lTitulo);
		ct.add(tTitulo);
		ct.add(lDia);
		ct.add(tDia);
		ct.add(lMes);
		ct.add(tMes);
		ct.add(lAno);
		ct.add(tAno);
		ct.add(btAdiconar);
		ct.add(btVoltar);
		// evento dos botões
		btAdiconar.addActionListener(this);		
		btVoltar.addActionListener(this);
		// especificações do formulario
		NovaNota.setSize(390,210);
		NovaNota.setTitle(str);
		NovaNota.setVisible(true);
		NovaNota.add(ct);
		this.str = str;
	}
	public void actionPerformed(ActionEvent e ) {
		if (e.getSource() == btAdiconar){
			if(str.equals("Evento")) {
				Evento i2 = null;
				try {
					i2 = new Evento(tTitulo.getText(), banco2.getTUltimoId() , 
							Integer.parseInt(tDia.getText()), 
							Integer.parseInt(tMes.getText()), 
							Integer.parseInt(tAno.getText())
							);
				} catch (NumberFormatException | ParseException e1) {
					e1.printStackTrace();
				}
				banco2.adicionarNota(i2);
			}
			if(str.equals("Lazer")) {
				Lazer i2 = null;
				try {
					i2 = new Lazer(tTitulo.getText(), banco2.getTUltimoId() , 
							Integer.parseInt(tDia.getText()), 
							Integer.parseInt(tMes.getText()), 
							Integer.parseInt(tAno.getText())
							);
				} catch (NumberFormatException | ParseException e1) {
					e1.printStackTrace();
				}
				banco2.adicionarNota(i2);
			}
			if(str.equals("Compromisso Familiar")) {
				CompromissoFamiliar i2 = null;
				try {
					i2 = new CompromissoFamiliar(tTitulo.getText(), banco2.getTUltimoId() , 
							Integer.parseInt(tDia.getText()), 
							Integer.parseInt(tMes.getText()), 
							Integer.parseInt(tAno.getText())
							);
				} catch (NumberFormatException | ParseException e1) {
					e1.printStackTrace();
				}
				banco2.adicionarNota(i2);
			}
			if(str.equals("Compromisso Profissional")) {
				CompromissoProfissional i2 = null;
				try {
					i2 = new CompromissoProfissional(tTitulo.getText(), 
							banco2.getTUltimoId() , 
							Integer.parseInt(tDia.getText()), 
							Integer.parseInt(tMes.getText()), 
							Integer.parseInt(tAno.getText())
							);
				} catch (NumberFormatException | ParseException e1) {
					e1.printStackTrace();
				}
				banco2.adicionarNota(i2);
			}
			tTitulo.setText("");
			tDia.setText("");
			tMes.setText("");
			tAno.setText("");
			
			tTitulo.requestFocus();
			NovaNota.setVisible(false);
			mItem7.doClick();
		}
		if(e.getSource() == btVoltar){
			NovaNota.setVisible(false);
			mItem7.doClick();
		}
	}
}
