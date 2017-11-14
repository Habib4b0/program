package com.stpl.gtn.gtn2o.ws.bpm.service;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.drools.compiler.kproject.ReleaseIdImpl;
import org.jbpm.runtime.manager.impl.RuntimeEnvironmentBuilder;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.manager.RuntimeEnvironment;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.manager.RuntimeManagerFactory;
import org.kie.internal.runtime.manager.RuntimeManagerRegistry;
import org.kie.internal.runtime.manager.context.EmptyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.stpl.gtn.gtn2o.ws.bpm.properties.DroolsProperties;

public final class GtnWsBpmInitializeBackUpCode {

	public GtnWsBpmInitializeBackUpCode() {
		/**
		 * empty constructor
		 */
	}

	private Properties properties = DroolsProperties.getPropertiesData();
	protected RuntimeManagerRegistry registry = RuntimeManagerRegistry.get();

	@Autowired
	private CustomUserCallBack userGroupCallback;

	@SuppressWarnings({ "unused", "resource" })
	private final void backUpcode() {

		ReleaseId releaseId = new ReleaseIdImpl(properties.getProperty("Forecasting_groupId", "com.stpl.app.bpm"),
				properties.getProperty("Forecasting_artifactId", "ForecastingWorkflow"),
				properties.getProperty("Forecasting_version", "1.0"));
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"com/stpl/gtn/gtn2o/ws/bpm/jbpmspringconfigs/Gtnjbpm-spring-beans.xml");
		EntityManagerFactory emf = (EntityManagerFactory) context.getBean("jbpmEMF");
		RuntimeEnvironment environment = RuntimeEnvironmentBuilder.Factory.get().newDefaultBuilder(releaseId)
				.entityManagerFactory(emf).userGroupCallback(userGroupCallback).get();
		RuntimeManager manager = RuntimeManagerFactory.Factory.get().newSingletonRuntimeManager(environment);
		manager.getRuntimeEngine(EmptyContext.get());

	}
}
