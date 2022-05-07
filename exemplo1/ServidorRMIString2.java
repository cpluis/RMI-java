import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServidorRMIString2 extends UnicastRemoteObject implements InterfaceRMIString2
{
	public ServidorRMIString2() throws RemoteException
	{
		super();
	}
	
	static public void main (String rmi[])
	{
		try 
		{
			ServidorRMIString2 objetoServidor = new ServidorRMIString2();
			String localizacao = "127.0.0.1/multiplica";
			Naming.rebind (localizacao, objetoServidor);
		} 
		catch (Exception exc) 
		{
			System.err.println (exc.toString());
		}
	}	

//	public String getData()
//	{
//		String data = new Date().toString();
//		return "blablaee";
//	}



	public Double multiplica(Integer valor)
	{
		return valor * 7.3;
	}
}
