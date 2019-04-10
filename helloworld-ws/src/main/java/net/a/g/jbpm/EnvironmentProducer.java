package net.a.g.jbpm;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.jbpm.services.api.DeploymentService;
import org.jbpm.services.cdi.Kjar;
import org.jbpm.services.task.events.DefaultTaskEventListener;
import org.jbpm.services.task.identity.JBossUserGroupCallbackImpl;
import org.kie.api.KieBase;
import org.kie.api.cdi.KReleaseId;
import org.kie.api.task.TaskLifeCycleEventListener;
import org.kie.internal.identity.IdentityProvider;

public class EnvironmentProducer {

	@PersistenceUnit(unitName = "org.jbpm.domain")
	private EntityManagerFactory emf;

	@Inject
	@Kjar
	private DeploymentService deploymentService;

	@Inject
	@KReleaseId(groupId = "net.a.g.jbpm", artifactId = "helloworld-kjar", version = "1.0.0")
	private KieBase kbase1;

	@Produces
	public EntityManagerFactory getEntityManagerFactory() {
		return this.emf;
	}

//	@Produces
//	public org.kie.api.task.UserGroupCallback produceSelectedUserGroupCalback() {
//		return new JBossUserGroupCallbackImpl();
//	}

	@Produces
	@Named("Logs")
	public TaskLifeCycleEventListener produceTaskAuditListener() {
		return new DefaultTaskEventListener();
	}

	@Produces
	public DeploymentService getDeploymentService() {

		return this.deploymentService;
	}

	@Produces
	public IdentityProvider produceIdentityProvider() {
		return new IdentityProvider() {

			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return "";
			}

			@Override
			public List<String> getRoles() {
				// TODO Auto-generated method stub
				return new ArrayList<String>();
			}

			@Override
			public boolean hasRole(String role) {
				// TODO Auto-generated method stub
				return true;
			}
			// implement identity provider
		};
	}

	@PostConstruct
	public void postConstruct() {
		System.setProperty("org.jbpm.var.log.length", "1024");
		System.setProperty("org.apache.cxf.logging.enabled", "true");
	}

}