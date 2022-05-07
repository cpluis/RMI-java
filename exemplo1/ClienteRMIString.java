import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.Naming;

public class ClienteRMIString
{
	static public void main (String rmi[])
	{
		try 
		{
			String localizacao = "127.0.0.1/data";
			String localizacao2 = "127.0.0.1/multiplica";
			InterfaceRMIString objeto = (InterfaceRMIString) Naming.lookup (localizacao);
			InterfaceRMIString2 objeto2 = (InterfaceRMIString2) Naming.lookup (localizacao2);

			System.out.println ("A data atual no servidor é: " + objeto2.multiplica(8));
			System.out.println ("A data atual no servidor é: " + objeto.getData());
		} 
		catch (Exception exc) 
		{
			System.err.println (exc.toString());
		}
	}	
}
