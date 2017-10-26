package com.stpl.app.contract.bpm.service;

import com.stpl.ifs.util.DroolsProperties;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.drools.compiler.kproject.ReleaseIdImpl;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeEnvironmentBuilder;
import org.kie.api.runtime.manager.RuntimeManagerFactory;
import java.util.Properties;

/**
 *
 * @author arulmurugan
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

    private BPMManagerBean() {
        try {
//        	Forecasting_ProjectName :ForecastingWorkflow
//        	Contract_groupId : com.stpl.app.bpm
//        	Contract_artifactId : ContractSubmissionWorkflow
//        	Contract_version : 1.0
//        	Contract_WorkflowId : ForecastingWorkflow.ContractSubmissionWorkflow.

            releaseId = new ReleaseIdImpl(properties.getProperty("Contract_groupId", "com.stpl.app.bpm"),
                    properties.getProperty("Contract_artifactId", "ContractSubmissionWorkflow"), properties.getProperty("Contract_version", "1.0"));

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.jbpm.domain");

            RuntimeEnvironmentBuilder builder = RuntimeEnvironmentBuilder.Factory.get().newDefaultBuilder(releaseId);

            builder = builder.entityManagerFactory(emf).userGroupCallback(new CustomUserCallBack());

            runtimeEngine = RuntimeManagerFactory.Factory.get().newSingletonRuntimeManager(builder.get(), "com.sample:example:1.0").getRuntimeEngine(null);

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
