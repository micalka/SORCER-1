package sorcer.project.junit;
import static sorcer.co.operator.from;
import static sorcer.eo.operator.context;
import static sorcer.eo.operator.cxt;
import static sorcer.eo.operator.in;
import static sorcer.eo.operator.out;
import static sorcer.eo.operator.pipe;
import static sorcer.eo.operator.result;
import static sorcer.eo.operator.sig;
import static sorcer.eo.operator.srv;
import static sorcer.eo.operator.task;
import static sorcer.eo.operator.value;

import java.rmi.RMISecurityManager;
import java.util.logging.Logger;

import org.junit.Test;

import sorcer.core.SorcerConstants;
import sorcer.core.exertion.ObjectJob;
import sorcer.core.provider.jobber.ServiceJobber;
import sorcer.project.provider.Average;
import sorcer.project.provider.RandomNum;
import sorcer.project.provider.Cosinus;
import sorcer.service.Exertion;
import sorcer.service.Job;
import sorcer.service.ServiceExertion;
import sorcer.service.Task;
import sorcer.util.Sorcer;
@SuppressWarnings("unchecked")
public class ProjectTest implements SorcerConstants {

	private final static Logger logger = Logger
			.getLogger(ProjectTest.class.getName());
	
	static {
		ServiceExertion.debug = true;
		System.setProperty("java.security.policy", Sorcer.getHome()
				+ "/configs/policy.all");
		System.setSecurityManager(new RMISecurityManager());
		Sorcer.setCodeBase(new String[] { "RandomNumProvider.jar", "AverageProvider.jar", "CosinusProvider.jar" });
		System.out.println("CLASSPATH :" + System.getProperty("java.class.path"));
		System.setProperty("java.protocol.handler.pkgs", "sorcer.util.url|org.rioproject.url");
	}
	
	
	
	@Test
	public void addTest() throws Exception {
		
		logger.info("TEST");
		
		Task t1 = task(
				"Random",
				sig("random", RandomNum.class, "RandomNumProvider"),
				context("random"));
		logger.info("Task1: "+t1.getContext());
		
		Task t2 = srv(
				"Avg",
				sig("avg", Average.class, "AverageProvider"),
				cxt("avg", in("a"), in("b"),in("c"),in("d"),in("e")));
		logger.info("Task2: "+t2.getContext());
		Task t3 = srv(
				"Cosinus",
				sig("cosinus", Cosinus.class, "CosinusProvider"),
				context("cosinus", in("a")));
		logger.info("Task3: "+t3.getContext());
		Exertion job = new ObjectJob("Jobs");
		job.addExertion(t1);
		job.addExertion(t2);
		job.addExertion(t3);
		
		t1.getContext().connect("val1", "a", t2.getContext());
		t1.getContext().connect("val2", "b", t2.getContext());
		t1.getContext().connect("val3", "c", t2.getContext());
		t1.getContext().connect("val4", "d", t2.getContext());
		t1.getContext().connect("val5", "e", t2.getContext());

		t2.getContext().connect("avg", "a", t3.getContext());
		
		job = job.exert();
		logger.info("JOB :"+((Job)job).getJobContext());
		
	
		
	}
	
}