package com.stpl.gtn.gtn2o.ws.bpm.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.drools.compiler.kproject.ReleaseIdImpl;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeEnvironmentBuilder;
import org.kie.api.runtime.manager.RuntimeManagerFactory;
import org.kie.internal.runtime.manager.RuntimeManagerRegistry;
import org.springframework.beans.factory.annotation.Autowired;

import com.stpl.gtn.gtn2o.ws.bpm.properties.DroolsProperties;
import com.stpl.gtn.gtn2o.ws.constants.workflow.GtnWsBpmCommonConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
 *
 * @author STPL
 */
public class BpmManagerBean {

    private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(BpmManagerBean.class);

    protected ReleaseId releaseId;
    private Map<String, RuntimeEngine> runtimeEngineMap = new HashMap<>();
    private Properties properties = DroolsProperties.getPropertiesData();
    private Properties cffproperties = DroolsProperties.getCffPropertiesData();
    protected RuntimeManagerRegistry registry = RuntimeManagerRegistry.get();
    @Autowired
    private CustomUserCallBack userGroupCallback;

    public BpmManagerBean() {
        super();
    }

    public BpmManagerBean(ReleaseId releaseId, Map<String, RuntimeEngine> runtimeEngineMap, Properties properties,
            RuntimeManagerRegistry registry, CustomUserCallBack userGroupCallback) {
        super();
        this.releaseId = releaseId;
        this.runtimeEngineMap = runtimeEngineMap;
        this.properties = properties;
        this.registry = registry;
        this.userGroupCallback = userGroupCallback;
    }

    public RuntimeEngine getRuntimeEngine(String moduleName) {
        return runtimeEngineMap.get(moduleName);
    }

    public CustomUserCallBack getUserGroupCallback() {
        return userGroupCallback;
    }

    public void setUserGroupCallback(CustomUserCallBack userGroupCallback) {
        this.userGroupCallback = userGroupCallback;
        try {
            initForecastRuntimeEngine();
            initContractRuntimeEngine();
            initReturnsRuntimeEngine();
            initCFFRuntimeEngine();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void initReturnsRuntimeEngine() {
        LOGGER.info("initReturnsRuntimeEngine Started ");
        String identifier = "com.stpl:returns:1.0";
        releaseId = new ReleaseIdImpl(properties.getProperty("Forecasting_groupId", "com.stpl.app.bpm"),
                properties.getProperty("Forecasting_artifactId", "ForecastingWorkflow"),
                properties.getProperty("Forecasting_version", "1.0"));
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.jbpm.domain");
        RuntimeEnvironmentBuilder builder = RuntimeEnvironmentBuilder.Factory.get().newDefaultBuilder(releaseId)
                .entityManagerFactory(emf).userGroupCallback(userGroupCallback);
        if (registry.isRegistered(identifier)) {
            registry.remove(identifier);
        }
        RuntimeEngine runtimeEngine = RuntimeManagerFactory.Factory.get()
                .newSingletonRuntimeManager(builder.get(), identifier).getRuntimeEngine(null);
        runtimeEngineMap.put(GtnWsBpmCommonConstants.FORECAST_RETURNS, runtimeEngine);

        LOGGER.info("initReturnsRuntimeEngine End ");
    }

    public void initContractRuntimeEngine() {
        LOGGER.info("initContractRuntimeEngine Started ");
        String identifier = "com.stpl:contract:1.0";
        releaseId = new ReleaseIdImpl(properties.getProperty("Contract_groupId", "com.stpl.app.bpm"),
                properties.getProperty("Contract_artifactId", "ContractSubmissionWorkflow"),
                properties.getProperty("Contract_version", "1.0"));
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.jbpm.domain");
        RuntimeEnvironmentBuilder builder = RuntimeEnvironmentBuilder.Factory.get().newDefaultBuilder(releaseId)
                .entityManagerFactory(emf).userGroupCallback(userGroupCallback);
        if (registry.isRegistered(identifier)) {
            registry.remove(identifier);
        }
        RuntimeEngine runtimeEngine = RuntimeManagerFactory.Factory.get()
                .newSingletonRuntimeManager(builder.get(), identifier).getRuntimeEngine(null);
        runtimeEngineMap.put(GtnWsBpmCommonConstants.CONTRACT_MASTER, runtimeEngine);
        LOGGER.info("initContractRuntimeEngine End ");
    }

    public void initForecastRuntimeEngine() {
        LOGGER.info("init Forecast RuntimeEngine Started ");
        String identifier = "com.stpl:forecast:1.0";
        releaseId = new ReleaseIdImpl(properties.getProperty("Forecasting_groupId", "com.stpl.app.bpm"), properties.getProperty("Forecasting_artifactId", "ForecastingWorkflow"), properties.getProperty("Forecasting_version", "1.0"));
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.jbpm.domain");
        RuntimeEnvironmentBuilder builder = RuntimeEnvironmentBuilder.Factory.get().newDefaultBuilder(releaseId)
                .entityManagerFactory(emf).userGroupCallback(userGroupCallback);
        if (registry.isRegistered(identifier)) {
            registry.remove(identifier);
        }
        RuntimeEngine runtimeEngine = RuntimeManagerFactory.Factory.get()
                .newSingletonRuntimeManager(builder.get(), identifier).getRuntimeEngine(null);
        runtimeEngineMap.put(GtnWsBpmCommonConstants.FORECAST_COMMERCIAL, runtimeEngine);
        LOGGER.info("initContractRuntimeEngine End ");
    }
    
    public void initCFFRuntimeEngine() {
        LOGGER.info("Init CffRuntime Engine Started ");
        String identifier = "com.sample:example:1.0";
        releaseId = new ReleaseIdImpl(cffproperties.getProperty("CFF_groupId", "com.stpl.app.bpm"), cffproperties.getProperty("CFF_artifactId","CFFWorkflow"), cffproperties.getProperty("CFF_version","1.0"));
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.jbpm.domain");
        RuntimeEnvironmentBuilder builder = RuntimeEnvironmentBuilder.Factory.get().newDefaultBuilder(releaseId)
                .entityManagerFactory(emf).userGroupCallback(userGroupCallback);
        if (registry.isRegistered(identifier)) {
            registry.remove(identifier);
        }
        RuntimeEngine runtimeEngine = RuntimeManagerFactory.Factory.get()
                .newSingletonRuntimeManager(builder.get(), identifier).getRuntimeEngine(null);
        runtimeEngineMap.put(GtnWsBpmCommonConstants.CFF, runtimeEngine);
        LOGGER.info("init CffRuntime RuntimeEngine End ");
    }

}
