package lp2.visao;

import lp2.controle.Funcoes;
import java.awt.Container;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class TelaAlterarNota implements ActionListener {
	// rotulos
	JLabel lId  = new JLabel("ID da tareda.:");
	JLabel lDia 	= new JLabel("Dia........:");
	JLabel lMes 	= new JLabel("Mes........:");
	JLabel lAno 	= new JLabel("Ano........:");
	// campos
	JTextField tId  	= new JTextField();	
	JTextField tDia 	= new JTextField();
	JTextField tMes 	= new JTextField();
	JTextField tAno 	= new JTextField();
	// botoes
	JButton btAdiconar = new JButton("Adicionar");
	JButton btRemover = new JButton("Remover");
	JButton btAlterarData = new JButton("Alterar");
	JButton btVoltar = new JButton("Cancelar");
	String str;
	Funcoes banco2; 
	
	JDialog NovaNota = new JDialog();
	Container ct = new Container();
	JMenuItem mItem7;
	public TelaAlterarNota(JMenuItem mItem7, String str, Funcoes banco,Container controle)  {
		banco2 = banco;
		// coordenadas
		this.mItem7 = mItem7;
		lId.setBounds(10,10,100,30);
		tId.setBounds(92,10,200,25);
		lDia.setBounds(10,40,100,30);
		tDia.setBounds(92,40,65,25);
		lMes.setBounds(10,70,100,30);
		tMes.setBounds(92,70,65,25);
		lAno.setBounds(10,100,100,30);
		tAno.setBounds(92,100,65,25);
		// idem
		btRemover.setBounds(50,140,100,30);
		btVoltar.setBounds(230,140,100,30);
		btAlterarData.setBounds(50,140,100,30);
		// adicionando componentes
		ct.add(lId);
		ct.add(tId);
		if(str.equals("Remover Atividade")) {
			ct.add(btRemover);
		}
		if(str.equals("Alterar Data")) {
			ct.add(lDia);
			ct.add(tDia);
			ct.add(lMes);
			ct.add(tMes);
			ct.add(lAno);
			ct.add(tAno);
			ct.add(btAlterarData);
		}
		ct.add(btVoltar);
		// evento dos botoes
		btRemover.addActionListener(this);	
		btAlterarData.addActionListener(this);	
		btVoltar.addActionListener(this);
		// especificações do formulario
		NovaNota.setSize(390,210);
		NovaNota.setTitle(str);
		NovaNota.setVisible(true);
		NovaNota.add(ct);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btRemover) {
			banco2.removerNota(Integer.parseInt(tId.getText()));
			tId.setText("");
			NovaNota.setVisible(false);
			mItem7.doClick();
		}
		if (e.getSource() == btAlterarData) {
			try {
				banco2.alterarData(Integer.parseInt(tId.getText()),
						Integer.parseInt(tDia.getText()),
						Integer.parseInt(tMes.getText()),
						Integer.parseInt(tAno.getText()), banco2);
				
				tId.setText("");
				tDia.setText("");
				tMes.setText("");
				tAno.setText("");
				NovaNota.setVisible(false);
				mItem7.doClick();
			} catch (NumberFormatException | ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource() == btVoltar){
			NovaNota.setVisible(false);
			mItem7.doClick();
		}
	}
}
