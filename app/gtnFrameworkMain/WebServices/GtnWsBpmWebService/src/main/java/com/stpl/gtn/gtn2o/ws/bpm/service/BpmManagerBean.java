package com.stpl.gtn.gtn2o.ws.bpm.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.drools.compiler.kproject.ReleaseIdImpl;
import org.kie.api.KieServices;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeEnvironmentBuilder;
import org.kie.api.runtime.manager.RuntimeManagerFactory;
import org.kie.internal.runtime.manager.RuntimeManagerRegistry;
import org.kie.scanner.MavenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;

import com.stpl.gtn.gtn2o.ws.bpm.properties.DroolsProperties;
import com.stpl.gtn.gtn2o.ws.constants.workflow.GtnWsBpmCommonConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

/**
 *
 * @author STPL
 */
@Service
public class BpmManagerBean {

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(BpmManagerBean.class);

	private static final String COM_STPL_APP_BPM = "com.stpl.app.bpm";
	private Map<String, RuntimeEngine> runtimeEngineMap = new HashMap<>();
	private Properties properties = DroolsProperties.getPropertiesData();
	private Properties cffproperties = DroolsProperties.getCffPropertiesData();
	private Properties armproperties = DroolsProperties.getArmPropertiesData();
	protected RuntimeManagerRegistry registry = RuntimeManagerRegistry.get();

	@Autowired
	private EntityManagerFactoryInfo enitiyManagerFactoryBean;

	@Autowired
	private CustomUserCallBack userGroupCallback;

	public BpmManagerBean() {
		super();
	}

	public RuntimeEngine getRuntimeEngine(String moduleName) {
		return runtimeEngineMap.get(moduleName);
	}

	public EntityManagerFactoryInfo getEnitiyManagerFactoryBean() {
		return enitiyManagerFactoryBean;
	}


	public CustomUserCallBack getUserGroupCallback() {
		return userGroupCallback;
	}

    @PostConstruct
    public void setUpEngineData() {
			initForecastRuntimeEngine();
			initContractRuntimeEngine();
			initReturnsRuntimeEngine();
			initCFFRuntimeEngine();
			initARMRuntimeEngine();
		}


	public void initReturnsRuntimeEngine() {
        try {
		LOGGER.info("initReturnsRuntimeEngine Started ");
		String identifier = "com.stpl:returns:1.0";
		ReleaseId releaseId = new ReleaseIdImpl(properties.getProperty("Forecasting_groupId", COM_STPL_APP_BPM),
				properties.getProperty("Forecasting_artifactId", "ForecastingWorkflow"),
				properties.getProperty("Forecasting_version", "1.0"));
		RuntimeEnvironmentBuilder builder = RuntimeEnvironmentBuilder.Factory.get().newDefaultBuilder(releaseId)
				.entityManagerFactory(enitiyManagerFactoryBean.getNativeEntityManagerFactory()).userGroupCallback(userGroupCallback);
                getContainerAndSetToEnvironment(builder, releaseId);
		if (registry.isRegistered(identifier)) {
			registry.remove(identifier);
		}
		RuntimeEngine runtimeEngine = RuntimeManagerFactory.Factory.get()
				.newSingletonRuntimeManager(builder.get(), identifier).getRuntimeEngine(null);
		runtimeEngineMap.put(GtnWsBpmCommonConstants.FORECAST_RETURNS, runtimeEngine);

		LOGGER.info("initReturnsRuntimeEngine End ");
        } catch (Exception e) {
            LOGGER.error("Exception in initialising Returns runtime ", e);
	}
    }

	public void initContractRuntimeEngine() {
        try {
		LOGGER.info("initContractRuntimeEngine Started ");
		String identifier = "com.stpl:contract:1.0";
		ReleaseId releaseId = new ReleaseIdImpl(properties.getProperty("Contract_groupId", COM_STPL_APP_BPM),
				properties.getProperty("Contract_artifactId", "ContractSubmissionWorkflow"),
				properties.getProperty("Contract_version", "1.0"));
		RuntimeEnvironmentBuilder builder = RuntimeEnvironmentBuilder.Factory.get().newDefaultBuilder(releaseId)
				.entityManagerFactory(enitiyManagerFactoryBean.getNativeEntityManagerFactory()).userGroupCallback(userGroupCallback);
                getContainerAndSetToEnvironment(builder, releaseId);
		if (registry.isRegistered(identifier)) {
			registry.remove(identifier);
		}
		RuntimeEngine runtimeEngine = RuntimeManagerFactory.Factory.get()
				.newSingletonRuntimeManager(builder.get(), identifier).getRuntimeEngine(null);
		runtimeEngineMap.put(GtnWsBpmCommonConstants.CONTRACT_MASTER, runtimeEngine);
		LOGGER.info("initContractRuntimeEngine End ");
        } catch (Exception e) {
            LOGGER.error("Exception in initialising Contract runtime ", e);
	}
    }

	public void initForecastRuntimeEngine() {
        try {
		LOGGER.info("init Forecast RuntimeEngine Started ");
		String identifier = "com.stpl:forecast:1.0";
		ReleaseId releaseId = new ReleaseIdImpl(properties.getProperty("Forecasting_groupId", COM_STPL_APP_BPM),
				properties.getProperty("Forecasting_artifactId", "ForecastingWorkflow"),
				properties.getProperty("Forecasting_version", "1.0"));
		RuntimeEnvironmentBuilder builder = RuntimeEnvironmentBuilder.Factory.get().newDefaultBuilder(releaseId)
				.entityManagerFactory(enitiyManagerFactoryBean.getNativeEntityManagerFactory()).userGroupCallback(userGroupCallback);
		getContainerAndSetToEnvironment(builder, releaseId);

		if (registry.isRegistered(identifier)) {
			registry.remove(identifier);
		}
		RuntimeEngine runtimeEngine = RuntimeManagerFactory.Factory.get()
				.newSingletonRuntimeManager(builder.get(), identifier).getRuntimeEngine(null);
		runtimeEngineMap.put(GtnWsBpmCommonConstants.FORECAST_COMMERCIAL, runtimeEngine);
		LOGGER.info("initForecastRuntimeEngine End ");
        } catch (Exception e) {
            LOGGER.error("Exception in initialising Forecast runtime ", e);
	}
    }

	private void getContainerAndSetToEnvironment(RuntimeEnvironmentBuilder builder, ReleaseId releaseId2) {
		MavenRepository repository = MavenRepository.getMavenRepository();
		repository.resolveArtifact(releaseId2.toExternalForm());
		KieServices ks = KieServices.Factory.get();
		KieContainer kieContainer = ks.newKieContainer(releaseId2);
		builder.addEnvironmentEntry("KieContainer", kieContainer);
	}

	public void initCFFRuntimeEngine() {
        try {
		LOGGER.info("Init CffRuntime Engine Started ");
		String identifier = "com.stpl:cfforecast:1.0";
		ReleaseId releaseId = new ReleaseIdImpl(cffproperties.getProperty("CFF_groupId", COM_STPL_APP_BPM),
				cffproperties.getProperty("CFF_artifactId", "CFFWorkflow"),
				cffproperties.getProperty("CFF_version", "1.0"));
		RuntimeEnvironmentBuilder builder = RuntimeEnvironmentBuilder.Factory.get().newDefaultBuilder(releaseId)
				.entityManagerFactory(enitiyManagerFactoryBean.getNativeEntityManagerFactory()).userGroupCallback(userGroupCallback);
                getContainerAndSetToEnvironment(builder, releaseId);
		if (registry.isRegistered(identifier)) {
			registry.remove(identifier);
		}
		RuntimeEngine runtimeEngine = RuntimeManagerFactory.Factory.get()
				.newSingletonRuntimeManager(builder.get(), identifier).getRuntimeEngine(null);
		runtimeEngineMap.put(GtnWsBpmCommonConstants.CFF, runtimeEngine);
		LOGGER.info("init CffRuntime RuntimeEngine End ");
        } catch (Exception e) {
            LOGGER.error("Exception in initialising CFF runtime ", e);
	}
    }

	public void initARMRuntimeEngine() {
        try {
		LOGGER.info("Init CffRuntime Engine Started ");
		String identifier = "com.stpl:arm:1.0";
		ReleaseId releaseId = new ReleaseIdImpl(armproperties.getProperty("ARM_groupId", COM_STPL_APP_BPM),
				armproperties.getProperty("ARM_artifactId", "ARMWorkflow"),
				armproperties.getProperty("ARM_version", "1.0"));
		RuntimeEnvironmentBuilder builder = RuntimeEnvironmentBuilder.Factory.get().newDefaultBuilder(releaseId)
				.entityManagerFactory(enitiyManagerFactoryBean.getNativeEntityManagerFactory()).userGroupCallback(userGroupCallback);
                getContainerAndSetToEnvironment(builder, releaseId);
		if (registry.isRegistered(identifier)) {
			registry.remove(identifier);
		}
		RuntimeEngine runtimeEngine = RuntimeManagerFactory.Factory.get()
				.newSingletonRuntimeManager(builder.get(), identifier).getRuntimeEngine(null);
		runtimeEngineMap.put(GtnWsBpmCommonConstants.ARM, runtimeEngine);
		LOGGER.info("init CffRuntime RuntimeEngine End ");
        } catch (Exception e) {
            LOGGER.error("Exception in initialising ARM runtime ", e);
	}
    }

}
