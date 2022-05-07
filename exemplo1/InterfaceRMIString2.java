import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceRMIString2 extends Remote
{

	public Double multiplica(Integer valor) throws RemoteException;
}
