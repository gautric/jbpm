package net.a.g.jbpm;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jbpm.kie.services.impl.KModuleDeploymentUnit;
import org.jbpm.services.api.DeploymentService;
import org.jbpm.services.api.ProcessService;
import org.jbpm.services.api.model.DeploymentUnit;

import net.a.g.data.Person;
import net.a.g.util.JBPMConstant;

@ApplicationScoped
public class JBPMServiceImpl implements JBPMService {

	@Inject
	ProcessService processService;

	@Inject
	DeploymentService deploy;

	@PostConstruct
	public void PostConstruct() {
		DeploymentUnit deploymentUnit = new KModuleDeploymentUnit(JBPMConstant.NET_A_G_JBPM, JBPMConstant.HELLOWORLD_KJAR, JBPMConstant.VERSION_1_0_0);

		if (!deploy.isDeployed(deploymentUnit.getIdentifier())) {
			deploy.deploy(deploymentUnit);
		}
	}

	@Override
	public void sayHelloToName(String name) {
		sayHelloToPerson(new Person(name));
	}

	@Override
	public void sayHelloToPerson(Person person) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("person", person);
		
		DeploymentUnit deploymentUnit = new KModuleDeploymentUnit(JBPMConstant.NET_A_G_JBPM, JBPMConstant.HELLOWORLD_KJAR, JBPMConstant.VERSION_1_0_0);

		processService.startProcess(deploymentUnit.getIdentifier(), JBPMConstant.NET_A_G_PROCESS_PROCESS_ID, params);
	}

}