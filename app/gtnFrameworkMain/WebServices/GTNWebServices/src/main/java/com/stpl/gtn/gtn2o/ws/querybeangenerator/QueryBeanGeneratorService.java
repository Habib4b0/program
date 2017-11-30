package com.stpl.gtn.gtn2o.ws.querybeangenerator;

import java.util.List;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.logic.GtnWsRelationshipBuilderHierarchyFileGenerator;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

public class QueryBeanGeneratorService{

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(QueryBeanGeneratorService.class);

	
	private GtnWsRelationshipBuilderHierarchyFileGenerator gtnWsRelationshipBuilderHierarchyFileGenerator;

	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	private GtnWsSqlService gtnWsSqlService;
	
	public QueryBeanGeneratorService() {
		super();
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

	public GtnWsRelationshipBuilderHierarchyFileGenerator getGtnWsRelationshipBuilderHierarchyFileGenerator() {
		return gtnWsRelationshipBuilderHierarchyFileGenerator;
	}

	public void setGtnWsRelationshipBuilderHierarchyFileGenerator(
			GtnWsRelationshipBuilderHierarchyFileGenerator gtnWsRelationshipBuilderHierarchyFileGenerator) {
		this.gtnWsRelationshipBuilderHierarchyFileGenerator = gtnWsRelationshipBuilderHierarchyFileGenerator;
	}

	@SuppressWarnings("rawtypes")
	public List executeQuery(String sqlQuery) throws GtnFrameworkGeneralException {
		return gtnSqlQueryEngine.executeSelectQuery(sqlQuery);
	}

	public String getQuery(String sqlId) {
		return gtnWsSqlService.getQuery(sqlId);
	}
	
	public void init() {
		LOGGER.info(" Inside context Initializer Listener ");
		try {
			@SuppressWarnings("unchecked")
			List<Object[]> hierarchyVersionList = (List<Object[]>) executeQuery(getQuery("getHierarchyVersionList"));
			if (hierarchyVersionList != null && !hierarchyVersionList.isEmpty()) {
				for (Object[] hierarchyVersion : hierarchyVersionList) {
					gtnWsRelationshipBuilderHierarchyFileGenerator.updateQueryInHierarchy(Integer.parseInt(String.valueOf(hierarchyVersion[0])),
							Integer.parseInt(String.valueOf(hierarchyVersion[1])));
				}
			}
		} catch (GtnFrameworkGeneralException e) {
			LOGGER.error(" Error in context Initializer Listener " + e.getMessage());
		}
	}


}
