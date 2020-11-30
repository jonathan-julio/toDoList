package lp2.controle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JDialog;

public class Calendario{
	public static JButton tarefasDoDia(int i, String mesAno, Funcoes banco) {
		String dataGet = "";
		if (i == 1 || i == 2|| i == 3|| i == 4|| i == 5|| i == 6|| i == 7|| i == 8|| i == 9) {
			dataGet = "0" + i +"/" + mesAno;
		}
		else {
			dataGet = i +"/" + mesAno;
		}
		String totalNotas = banco.getQuantidadeTarefa(dataGet, banco);
		if (totalNotas.equals("0")) {
			JButton listar = new JButton(Integer.toString(i));
			listar.setContentAreaFilled(false);
			listar.setOpaque(true);
			listar.setBackground(Color.lightGray);
			return listar;
		}
		else {
			JButton listar = new JButton(Integer.toString(i) 
					+"(" + totalNotas + ")");
			listar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					abrirJDialog(i,mesAno, banco);
				}
			});
			listar.setContentAreaFilled(true);
			listar.setOpaque(true);
			listar.setBackground(Color.DARK_GRAY);
			return listar;
		}
	}
	@SuppressWarnings("deprecation")
	private static void abrirJDialog(int i, String mesAno, Funcoes banco){
		String dataGet = "";
		if (i == 1 || i == 2|| i == 3|| i == 4|| i == 5|| i == 6|| i == 7|| i == 8|| i == 9) {
			dataGet = "0" + i +"/" + mesAno;
		}
		else {
			dataGet = i +"/" + mesAno;
		}
		JDialog dia = new JDialog();
		dia.setTitle("Atividades do dia " + i);
		Container atividades = new Container();;
		dia.setLayout(new BorderLayout());
		atividades = banco.getTarefaDodia(dataGet);
	    dia.add(BorderLayout.CENTER, atividades);
		dia.setSize(720, atividades.countComponents()*50);
		dia.setVisible(true);
		}
	public static void formarCalendario(Container controle, Funcoes banco, int mesI)  {
		DateFormat dateFormat1 = new SimpleDateFormat("MM/yyyy");
		Date dateAux = new Date();
		String mesAno = dateFormat1.format(dateAux);
		if (mesI == 4 ||mesI == 6 || mesI == 9 || mesI == 11){
			for (int i = 1 ; i <= 30; i++) {
				controle.add(tarefasDoDia(i, mesAno, banco));
			}	
		}
		if(mesI == 2) {
			DateFormat dateFormata = new SimpleDateFormat("yyyy");
			Date date1 = new Date();
			String ano = dateFormata.format(date1);
			int anoI = Integer.parseInt(ano);
			if (((anoI % 4 == 0) && (anoI % 100 != 0)) || (anoI % 400 == 0)) {
				for (int i = 1 ; i <= 29; i++) {
					controle.add(tarefasDoDia(i, mesAno, banco));
				}
			}
			else {
				for (int i = 1 ; i <= 28; i++) {
					controle.add(tarefasDoDia(i, mesAno, banco));
				}
			}
		}
		if (mesI == 1 ||mesI == 3 || mesI == 5 || mesI == 7|| mesI == 8|| mesI == 10|| mesI == 12) {
			System.out.println("Aqui: " + mesI);
			for (int i = 1 ; i <= 31; i++) {
				controle.add(tarefasDoDia(i, mesAno, banco));
			}
		}
	}
}
