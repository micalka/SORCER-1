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
import java.util.Random;
import java.util.logging.Logger;

import com.sun.jini.start.LifeCycle;

import sorcer.core.context.ArrayContext;
import sorcer.core.context.Contexts;
import sorcer.core.context.PositionalContext;
import sorcer.core.context.ServiceContext;
import sorcer.core.provider.ServiceTasker;
import sorcer.service.Context;
import sorcer.service.ContextException;
import sorcer.project.provider.RandomNumProvider;


public class RandomNumProvider extends ServiceTasker implements RandomNum {
	private static Logger logger = Logger
			.getLogger(RandomNumProvider.class.getName());
	
	
	
	public RandomNumProvider(String[] args, LifeCycle lifeCycle) throws Exception {
		super(args, lifeCycle);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Context random(Context context) throws RemoteException,
			ContextException {
		// TODO Auto-generated method stub
		return calculate(context);
	}
	
	private double[] random(){
		double[] arr = new double[5];
		for(int i=0;i<5;i++){
			arr[i] = Math.random()*10+1;
		}
		return arr;
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
		
			double[] array = random();
			cxt.putValue("val1", array[0]);
			cxt.putValue("val2", array[1]);
			cxt.putValue("val3", array[2]);
			cxt.putValue("val4", array[3]);
			cxt.putValue("val5", array[4]);
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
