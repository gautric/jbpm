package net.a.g.jbpm;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import org.jbpm.services.api.DeploymentEvent;
import org.jbpm.services.cdi.Deploy;
import org.jbpm.services.cdi.Undeploy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class DeploymentListener {
	private static final Logger logger = LoggerFactory.getLogger(DeploymentListener.class);

	public void onDeployment(@Observes @Deploy DeploymentEvent event) {
		logger.info("Unit {} has been successfully deployed ", event.getDeploymentId(), event.getDeployedUnit());

	}

	public void onUndeployment(@Observes @Undeploy DeploymentEvent event) {
		logger.info("Unit {} has been successfully undeployed", event.getDeploymentId());
	}
}