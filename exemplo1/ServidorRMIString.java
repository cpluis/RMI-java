import javax.swing.text.MaskFormatter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.Normalizer;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.Pattern;

public class ServidorRMIString extends UnicastRemoteObject implements InterfaceRMIString
{
	public ServidorRMIString() throws RemoteException
	{
		super();
	}
	
	static public void main (String rmi[])
	{

		try 
		{
			ServidorRMIString objetoServidor = new ServidorRMIString();
			String localizacao = "127.0.0.1/data";
			Naming.rebind (localizacao, objetoServidor);
		} 
		catch (Exception exc) 
		{
			System.err.println (exc.toString());
		}
	}	

	public String getData(){
		String data = String.valueOf(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		return data;
	}

	@Override
	public String getHora() throws RemoteException {
		String hora = String.valueOf(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
		return hora;
	}

	@Override
	public String bemVindo(String nome) throws RemoteException {

		String retornoBemVindo = "Bem Vindo !! "+nome;
		return retornoBemVindo;
	}

	@Override
	public String buscaCepInterface(String cep) throws IOException {
		String logradouro;
		String bairro;
		String cidade;
		String uf;

		String json;
		URL url = new URL("http://viacep.com.br/ws/"+ cep +"/json");
		URLConnection urlConnection = url.openConnection();
		InputStream is = urlConnection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuilder jsonSb = new StringBuilder();

		br.lines().forEach(l -> jsonSb.append(l.trim())
		);
		json = jsonSb.toString();
		json = json.replaceAll("[{},:]", "");
		json = json.replaceAll("\"", "\n");
		String array[] = new String[30];
		array = json.split("\n");

		logradouro = array[7];
		bairro = array[15];
		cidade = array[19];
		uf = array[23];
		return bairro+"\n"+logradouro+"\n"+cidade+"\n"+uf;
	}

	@Override
	public int fatorial(int num) throws RemoteException {
		int fact = 1;
		for( int i = 1; i <= num; i++ ) {
			fact *= i;
		}
		return fact;
	}

	@Override
	public Boolean validaCPF(String CPF) throws RemoteException {
		if (CPF.equals("00000000000") ||
				CPF.equals("11111111111") ||
				CPF.equals("22222222222") || CPF.equals("33333333333") ||
				CPF.equals("44444444444") || CPF.equals("55555555555") ||
				CPF.equals("66666666666") || CPF.equals("77777777777") ||
				CPF.equals("88888888888") || CPF.equals("99999999999") ||
				(CPF.length() != 11))
			return(false);

		char dig10, dig11;
		int sm, i, r, num, peso;
		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			for (i=0; i<9; i++) {
				num = (int)(CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for(i=0; i<10; i++) {
				num = (int)(CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else dig11 = (char)(r + 48);

			// Verifica se os digitos calculados conferem com os digitos informados.
			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
				return(true);
			else return(false);
		} catch (InputMismatchException erro) {
			return(false);
		}catch (NumberFormatException  erro) {
			return(false);
		}
	}

	@Override
	public List<Integer> listaNumericaOrdenada(List numeros) throws RemoteException {
		Collections.sort(numeros);
		return numeros;
	}
}
