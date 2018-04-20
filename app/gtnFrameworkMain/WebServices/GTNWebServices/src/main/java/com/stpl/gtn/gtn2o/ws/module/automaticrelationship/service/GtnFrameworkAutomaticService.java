package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.entity.relationshipbuilder.RelationshipBuilder;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.concurrency.GtnFrameworkAutomaticRunnable;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;

@Service
@Scope(value = "singleton")
public class GtnFrameworkAutomaticService {

	@Autowired
	private GtnFrameworkAutomaticRelationUpdateService autoUpdateService;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	private ExecutorService customerExecutorService = Executors.newFixedThreadPool(50);

        public GtnFrameworkAutomaticService() {
            super();
        }

	public synchronized void checkAndUpdateAllRelationShip(String relationShipType) {

		List<GtnWsRelationshipBuilderBean> finalResultbeanList = getRelationShipBuilderData(relationShipType);
		customerExecutorService = Executors.newFixedThreadPool(50);
		for (GtnWsRelationshipBuilderBean relationShipBuilderBean : finalResultbeanList) {
			GtnFrameworkAutomaticRunnable automaticRelationUpdateRunnable = applicationContext
					.getBean(GtnFrameworkAutomaticRunnable.class);
			automaticRelationUpdateRunnable.setRelationBean(relationShipBuilderBean);
			automaticRelationUpdateRunnable.setUserId(String.valueOf(relationShipBuilderBean.getCreatedBy()));
			customerExecutorService.submit(automaticRelationUpdateRunnable);
		}
		customerExecutorService.shutdown();
	}

	@SuppressWarnings("unchecked")
	public List<GtnWsRelationshipBuilderBean> getRelationShipBuilderData(String relationShipType) {
		SessionFactory sessionFactory = gtnSqlQueryEngine.getSessionFactory();
		List<GtnWsRelationshipBuilderBean> finalResultbeanList = new ArrayList<>();
		try (Session session = sessionFactory.openSession()) {
			Criteria criteria = session.createCriteria(RelationshipBuilder.class);
			criteria.add(Restrictions.eq("buildType", "Automatic"));
			if (relationShipType.equals("DeductionHierarchy")) {
				criteria.createAlias("hierarchyDefinition", "h1");
				criteria.add(Restrictions.eq("h1.hierarchyName", "DeductionHierarchy"));
			}
			List<RelationshipBuilder> results = criteria.list();
			for (RelationshipBuilder relationShipBuilderBean : results) {
				finalResultbeanList.add(autoUpdateService.getCustomizedRelationBean(relationShipBuilderBean, session));
			}
		}
		return finalResultbeanList;
	}

	public synchronized void waitForRelaitonUpdatetoFinish() throws InterruptedException {
		if (customerExecutorService.isShutdown())
			customerExecutorService.awaitTermination(30, TimeUnit.MINUTES);
	}

}
