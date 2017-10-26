package com.stpl.app.adminconsole.bpm.service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.drools.compiler.kproject.ReleaseIdImpl;
import org.eclipse.aether.artifact.Artifact;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeEnvironmentBuilder;
import org.kie.api.runtime.manager.RuntimeManagerFactory;
import org.kie.scanner.MavenRepository;

import com.stpl.app.adminconsole.bpm.util.BPMCommonUtils;
import org.jboss.logging.Logger;

/**
*
* @author arulmurugan
*/

public class BPMManagerBean {

	public ReleaseId releaseId;
	private RuntimeEngine runtimeEngine;
	private static BPMManagerBean bpmManagerBean;
        private static final Logger LOGGER = Logger.getLogger(BPMManagerBean.class);

	private BPMManagerBean() {
		try{
		ReleaseId releaseId = new ReleaseIdImpl(BPMCommonUtils.groupId, BPMCommonUtils.artifactId,BPMCommonUtils.version);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.jbpm.domain");
		RuntimeEnvironmentBuilder builder = RuntimeEnvironmentBuilder.Factory.get().newDefaultBuilder(releaseId).entityManagerFactory(emf).userGroupCallback(new CustomUserCallBack());
		runtimeEngine = RuntimeManagerFactory.Factory.get().newSingletonRuntimeManager(builder.get(), "com.stpl.bpm.adminConsole:1.0").getRuntimeEngine(null);
		runtimeEngine.getKieSession().getWorkItemManager().registerWorkItemHandler("Email", new MailWorkItemHandler());
		}catch(Exception e){
			LOGGER.error(e);
		}
	}

	public static BPMManagerBean getEngine() {
		if (bpmManagerBean == null) {
			synchronized (BPMManagerBean.class) {
				bpmManagerBean = new BPMManagerBean();
			}

		}

		return bpmManagerBean;
	}

	public RuntimeEngine getRuntimeEngine() {
		return runtimeEngine;
	}
	
	public Artifact getArtifact() {
		Artifact artifact = MavenRepository.getMavenRepository().resolveArtifact(releaseId);
		return   artifact;
	}

}
