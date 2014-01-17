/**
 * 
 */
package sorcer.project.provider;

import static sorcer.eo.operator.path;
import static sorcer.eo.operator.revalue;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Logger;

import com.sun.jini.start.LifeCycle;

import sorcer.core.context.ArrayContext;
import sorcer.core.context.Contexts;
import sorcer.core.context.PositionalContext;
import sorcer.core.context.ServiceContext;
import sorcer.core.provider.ServiceTasker;
import sorcer.service.Context;
import sorcer.service.ContextException;
import sorcer.project.provider.CosinusProvider;


@SuppressWarnings({"rawtypes","unchecked"})
public class CosinusProvider extends ServiceTasker implements Cosinus {
	private static Logger logger = Logger
			.getLogger(CosinusProvider.class.getName());
	
	private final static String COS = "cos";
	public CosinusProvider(String[] args, LifeCycle lifeCycle) throws Exception {
		super(args, lifeCycle);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see sorcer.project.provider.Head#head(sorcer.service.Context)
	 */
	
	@Override
	public Context cosinus(Context context) throws RemoteException,
			ContextException {
		// TODO Auto-generated method stub
		return calculate(context);
	}
	
	
	
	private Context calculate(Context context) throws RemoteException, ContextException {
		PositionalContext cxt = (PositionalContext) context;
		try {

			List<Double> inputs = (List<Double>)Contexts.getNamedInValues(context);
			if (inputs == null || inputs.size() == 0) {
				inputs = (List<Double>)Contexts.getPrefixedInValues(context);
			}
			if (inputs == null || inputs.size() == 0)
				inputs = (List<Double>)cxt.getInValues();
			logger.info("inputs: \n" + inputs);
			List<String> outpaths = cxt.getOutPaths();
		
			double result = (Double)revalue(inputs.get(0));
			result = Math.cos(result);			
			
			cxt.putValue(COS, result);
		
			
		} catch (Exception ex) {
			// ContextException, UnknownHostException
			ex.printStackTrace();
			context.reportException(ex);
			throw new ContextException(" calculate exception", ex);
		}
		return (Context) context;
	}
	
	private String getHostname() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostName();
	}

	

}
