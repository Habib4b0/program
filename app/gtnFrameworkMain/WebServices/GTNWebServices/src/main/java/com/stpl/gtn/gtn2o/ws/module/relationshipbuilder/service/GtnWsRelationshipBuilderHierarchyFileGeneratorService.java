package com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkHierarchyQueryBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.querygenerator.GtnWsAllHierarchyQueryGenerator;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkFileReadWriteService;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkHierarchyService;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

public class GtnWsRelationshipBuilderHierarchyFileGeneratorService {

	private static final GtnWSLogger LOGGER = GtnWSLogger
			.getGTNLogger(GtnWsRelationshipBuilderHierarchyFileGeneratorService.class);

	private org.hibernate.SessionFactory sessionFactory;

	private GtnFrameworkHierarchyService gtnHierarchyServiceBuilder;

	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	private GtnWsSqlService gtnWsSqlService;


	private GtnWsAllHierarchyQueryGenerator queryGeneratorService;

	public GtnWsRelationshipBuilderHierarchyFileGeneratorService() {
		super();
	}

	@SuppressWarnings("rawtypes")
	public List executeQuery(String sqlQuery) throws GtnFrameworkGeneralException {
		return gtnSqlQueryEngine.executeSelectQuery(sqlQuery);
	}

	@SuppressWarnings("rawtypes")
	public List executeQuery(String sqlQuery, Object[] params, GtnFrameworkDataType[] type)
			throws GtnFrameworkGeneralException {
		return gtnSqlQueryEngine.executeSelectQuery(sqlQuery, params, type);
	}

	public String getQuery(String sqlId) {
		return gtnWsSqlService.getQuery(sqlId);
	}

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public GtnFrameworkHierarchyService getGtnHierarchyServiceBuilder() {
		return gtnHierarchyServiceBuilder;
	}

	public void setGtnHierarchyServiceBuilder(GtnFrameworkHierarchyService gtnHierarchyServiceBuilder) {
		this.gtnHierarchyServiceBuilder = gtnHierarchyServiceBuilder;
	}


	public GtnFrameworkSqlQueryEngine getGtnSqlQueryEngine() {
		return gtnSqlQueryEngine;
	}

	public void setGtnSqlQueryEngine(GtnFrameworkSqlQueryEngine gtnSqlQueryEngine) {
		this.gtnSqlQueryEngine = gtnSqlQueryEngine;
	}

	public GtnWsSqlService getGtnWsSqlService() {
		return gtnWsSqlService;
	}

	public void setGtnWsSqlService(GtnWsSqlService gtnWsSqlService) {
		this.gtnWsSqlService = gtnWsSqlService;
	}

	public GtnWsAllHierarchyQueryGenerator getQueryGeneratorService() {
		return queryGeneratorService;
	}

	public void setQueryGeneratorService(GtnWsAllHierarchyQueryGenerator queryGeneratorService) {
		this.queryGeneratorService = queryGeneratorService;
	}

	public String getQueryReplaced(List<String> input, String queryName) {
		StringBuilder sqlStringBuilder = new StringBuilder();
		try {
			sqlStringBuilder = new StringBuilder(getQuery(queryName));
			if (input != null) {
				for (Object tempInput : input) {
					sqlStringBuilder.replace(sqlStringBuilder.indexOf("?"), sqlStringBuilder.indexOf("?") + 1,
							String.valueOf(tempInput));
				}
			}

		} catch (Exception ex) {
			LOGGER.error("Exception in getQueryReplaced", ex);
		}
		return sqlStringBuilder.toString();
	}

	public String getFinalQueryReplaced(List<String> input, String finalQuery) {
		StringBuilder sqlStrBuilder = new StringBuilder();
		try {
			sqlStrBuilder = new StringBuilder(finalQuery);
			if (input != null) {
				for (Object tempInput : input) {
					sqlStrBuilder.replace(sqlStrBuilder.indexOf("?"), sqlStrBuilder.indexOf("?") + 1,
							String.valueOf(tempInput));
				}
			}

		} catch (Exception ex) {
			LOGGER.error("Exception in getQuery", ex);
		}
		return sqlStrBuilder.toString();
	}

	public void updateQueryInHierarchy(int hierarchyDefSId, int versionNo) {
		try (Session session = sessionFactory.openSession()) {
			Transaction tx = session.beginTransaction();
			List<HierarchyLevelDefinitionBean> hierarchyDefList = getRBHierarchyLevelDefinitionBySid(hierarchyDefSId,
					versionNo);
			GtnFrameworkFileReadWriteService fileService = new GtnFrameworkFileReadWriteService();
			List<GtnFrameworkHierarchyQueryBean> queryBeanList = new ArrayList<>();
			for (HierarchyLevelDefinitionBean hierarchyLevelDefinitionBean : hierarchyDefList) {
				HierarchyLevelDefinitionBean destinationHierarchyBean = getHierarchyBeanByLevelNo(hierarchyDefList,
						hierarchyLevelDefinitionBean.getLevelNo());
				if (destinationHierarchyBean != null && !GtnFrameworkWebserviceConstant.USER_DEFINED
						.equals(destinationHierarchyBean.getLevelValueReference())) {
					GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
					queryGeneratorService.getLinkedValueQuery(destinationHierarchyBean, hierarchyDefList, queryBean);
					GtnFrameworkHierarchyQueryBean hierarchyQueryBean = new GtnFrameworkHierarchyQueryBean();
					hierarchyQueryBean
							.setHierarchyLevelDefSid(hierarchyLevelDefinitionBean.getHierarchyLevelDefinitionSid());
					hierarchyQueryBean.setQuery(queryBean);
					queryBeanList.add(hierarchyQueryBean);
				}
			}
			fileService.createJson(queryBeanList, hierarchyDefSId, versionNo);
			tx.commit();
		} catch (Exception e) {
			LOGGER.error("Exception in getQuery", e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<HierarchyLevelDefinitionBean> getRBHierarchyLevelDefinitionBySid(int hierarchyDefSid, int versionNo)
			throws GtnFrameworkGeneralException {
		List<String> inputlist = new ArrayList<>();
		inputlist.add(String.valueOf(hierarchyDefSid));
		inputlist.add(String.valueOf(versionNo));
		List<Object[]> result = executeQuery(getQueryReplaced(inputlist, "getRBHierarchyLevelDefinitionBySid"));

		return gettHierarchyLevelDefinitionListMain(result);
	}

	public List<HierarchyLevelDefinitionBean> gettHierarchyLevelDefinitionListMain(List<Object[]> resultList) {
		List<HierarchyLevelDefinitionBean> hierarchyList = new ArrayList<>();
		HierarchyLevelDefinitionBean hierarchyLevelBean = null;

		for (Object[] result : resultList) {
			hierarchyLevelBean = new HierarchyLevelDefinitionBean();
			hierarchyLevelBean.customize(result);
			hierarchyList.add(hierarchyLevelBean);
		}
		Collections.sort(hierarchyList);
		return hierarchyList;
	}

	public HierarchyLevelDefinitionBean getHierarchyBeanByLevelNo(List<HierarchyLevelDefinitionBean> hierarchyList,
			int levelNo) {
		// we are sorting the list based on the level No so get the value by
		// index
		if (levelNo < hierarchyList.size() && hierarchyList.get(levelNo).getLevelNo() == levelNo) {
			return hierarchyList.get(levelNo);
		}
		for (HierarchyLevelDefinitionBean hierarchyLevelDefinitionBean : hierarchyList) {
			if (hierarchyLevelDefinitionBean.getLevelNo() == levelNo)
				return hierarchyLevelDefinitionBean;
		}
		return null;
	}
}
