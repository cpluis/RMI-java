import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface InterfaceRMIString extends Remote
{
	public String getData() throws RemoteException;
	public String getHora() throws RemoteException;
	public String bemVindo(String nome) throws RemoteException;
	public String buscaCepInterface(String cep) throws IOException;
	public int fatorial(int valor) throws RemoteException;
	public Boolean validaCPF(String cpf) throws RemoteException;
	public List<Integer> listaNumericaOrdenada(List numeros) throws RemoteException;
}
