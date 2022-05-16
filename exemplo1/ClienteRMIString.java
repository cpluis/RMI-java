import javax.swing.*;
import java.awt.*;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

public class ClienteRMIString  {
	static public void main (String rmi[])
	{
		try 
		{
			String localizacaoData = "127.0.0.1/data";
			InterfaceRMIString objetoData = (InterfaceRMIString) Naming.lookup (localizacaoData);
			boolean x =true;
			List list = new ArrayList();
			Integer valorLista = 0;
			int fatorial = 0;

//			String op ="opções";
			System.out.printf("Olá pessoal", "Aqui é o Fábio");
			System.out.println("Olá pessoal Aqui é o Fábio");

			int valor = 0;
			while ( x == true) {
				UIManager.getDefaults().put("OptionPane.background",new Color(7, 7, 7));
				UIManager.put ("Panel.background", new Color(185, 127, 3));
				UIManager.put("OptionPane.minimumSize", new Dimension(560, 300));
				valor = Integer.parseInt(JOptionPane.showInputDialog(
						"\n=*=   SELECIONE UMA DAS OPCÕES   =*=\n\n" +
						"    1 - Para saber a data do servidor  \n" +
						"    2 - Para saber a hora do servidor    \n" +
						"    3 - Para criar uma lista \n" +
						"    4 - Para realizar de um  Fatorial \n" +
						"    5 - Para pesquisar um CPF\n" +
						"    6 - Para para uma mensagem de boas vindas\n" +
						"    7 - Para para consultar um cep\n" +
						"    8 - Para Encerar.     \n\n\n\n"
				));
				UIManager.getDefaults().put("OptionPane.background",new Color(3, 232, 198));
				UIManager.put ("Panel.background", new Color(185, 127, 3));
				UIManager.put("OptionPane.minimumSize", new Dimension(360, 180));
				if (valor == 8){
					JOptionPane.showMessageDialog(null,"Não eh Adeus, eh Ate Ja");
					x = false;
					continue;
				}
				if (valor == 1){
					JOptionPane.showMessageDialog(null, "A data atual no servidor eh: " + objetoData.getData());
				}
				if (valor == 2){
					JOptionPane.showMessageDialog(null, "A hora atual no servidor eh: " + objetoData.getHora());
				}
				if (valor == 3){
					boolean y = false;
					while (y==false){
						valorLista = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite mais uma valor ou 0 para encerrar a quantidade da lista"));
						if (valorLista == 0){
							y=true;
							JOptionPane.showMessageDialog(null, "A lista antes de ordenar era " + list + " apos ordenada ficou: " + objetoData.listaNumericaOrdenada(list));
							valor = 8;
							continue;
						}
						list.add(valorLista);
					}
				}
				if (valor == 4){
					fatorial = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite o valor do fatorial"));
					JOptionPane.showMessageDialog(null, "O fatorial de " + fatorial + " eh: " + objetoData.fatorial(fatorial));
				}
				if (valor == 5){
					String cpf = JOptionPane.showInputDialog(null,"Digite o CPF com 11 digitos");
					JOptionPane.showMessageDialog(null, objetoData.validaCPF(cpf) ? "O CPF eh Valido!!" : "O CPF nao eh Valido!!");
				}
				if (valor == 6){
					String nome = JOptionPane.showInputDialog(null,"Digite seu nome");
					JOptionPane.showMessageDialog(null, objetoData.bemVindo(nome));
				}
				if (valor == 7){
					String cep = JOptionPane.showInputDialog(null,"Digite seu cep");
					JOptionPane.showMessageDialog(null, objetoData.buscaCepInterface(cep));
				}
				if(valor <1 || valor >8){
					JOptionPane.showMessageDialog(null, "Valor nao encontardo nas opcoes!!!");
				}
			}		}
		catch (Exception exc) 
		{
			System.err.println (exc.toString());
		}
	}	
}
