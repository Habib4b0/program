package com.stpl.app.arm.bpm.service;

import com.stpl.ifs.util.DroolsProperties;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.drools.compiler.kproject.ReleaseIdImpl;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeEnvironmentBuilder;
import org.kie.api.runtime.manager.RuntimeManagerFactory;
import org.kie.internal.runtime.manager.RuntimeManagerRegistry;

/**
 *
 * @author asha
 */
public class BPMManagerBean {

    /**
     * The Constant LOGGER.
     */
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(BPMManagerBean.class);

    public ReleaseId releaseId;
    private RuntimeEngine runtimeEngine;
    private static BPMManagerBean bpmManagerBean;
    private Properties properties = DroolsProperties.getPropertiesData();
    protected RuntimeManagerRegistry registry = RuntimeManagerRegistry.get();
    private BPMManagerBean() {
        try {
            String identifier = "com.stpl.app.bpm:ARMWorkflow:1.0";
            ReleaseId releaseId = new ReleaseIdImpl(properties.getProperty("ARMWorkflow_groupId","com.stpl.app.bpm"), properties.getProperty("ARMWorkflow_artifactId","ARMWorkflow"), properties.getProperty("ARMWorkflow_version","1.0"));
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.jbpm.domain");
            RuntimeEnvironmentBuilder builder = RuntimeEnvironmentBuilder.Factory.get().newDefaultBuilder(releaseId).entityManagerFactory(emf).userGroupCallback(new CustomUserCallBack());
            if (registry.isRegistered(identifier)){
                registry.remove(identifier);
            }
            runtimeEngine = RuntimeManagerFactory.Factory.get().newSingletonRuntimeManager(builder.get(), identifier).getRuntimeEngine(null);
            runtimeEngine.getKieSession().getWorkItemManager().registerWorkItemHandler("Email", new MailWorkItemHandler());
        } catch (Exception e) {
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
    
}
