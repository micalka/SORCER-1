/**
 * 
 */
package sorcer.project.provider;

import java.rmi.Remote;
import java.rmi.RemoteException;

import sorcer.service.Context;
import sorcer.service.ContextException;


@SuppressWarnings("rawtypes")
public interface Average extends Remote {

	
	public Context avg(Context context) throws RemoteException, ContextException;
	
}
