package com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.logic;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkHierarchyQueryBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSingleColumnRelationBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkHierarchyService;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.service.GtnFrameworkFileReadWriteService;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

public class GtnWsRelationshipBuilderHierarchyFileGenerator {

	private static final GtnWSLogger LOGGER = GtnWSLogger
			.getGTNLogger(GtnWsRelationshipBuilderHierarchyFileGenerator.class);

	private org.hibernate.SessionFactory sessionFactory;

	private GtnFrameworkHierarchyService gtnHierarchyServiceBuilder;

	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	private GtnWsSqlService gtnWsSqlService;

	private GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean;

	public GtnWsRelationshipBuilderHierarchyFileGenerator() {
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

	public GtnFrameworkEntityMasterBean getGtnFrameworkEntityMasterBean() {
		return gtnFrameworkEntityMasterBean;
	}

	public void setGtnFrameworkEntityMasterBean(GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean) {
		this.gtnFrameworkEntityMasterBean = gtnFrameworkEntityMasterBean;
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

	public String getQueryReplaced(List<String> input, String queryName) {
		StringBuilder sqlStringBuilder = new StringBuilder();
		try {
			sqlStringBuilder = new StringBuilder(getQuery(queryName));
			if (input != null) {
				for (Object tempInput : input) {
					sqlStringBuilder.replace(sqlStringBuilder.indexOf("?"), sqlStringBuilder.indexOf("?") + 1, String.valueOf(tempInput));
				}
			}

		} catch (Exception ex) {
			LOGGER.error("Exception in getQuery", ex);
		}
		return sqlStringBuilder.toString();
	}

	public void updateQueryInHierarchy(int hierarchyDefSId, int versionNo)
			throws GtnFrameworkGeneralException, IOException {

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
					getLinkedValueQuery(destinationHierarchyBean, hierarchyDefSId,
							hierarchyLevelDefinitionBean.getLevelNo(), hierarchyDefList, queryBean);
					GtnFrameworkHierarchyQueryBean hierarchyQueryBean = new GtnFrameworkHierarchyQueryBean();
					hierarchyQueryBean
							.setHierarchyLevelDefSid(hierarchyLevelDefinitionBean.getHierarchyLevelDefinitionSid());
					hierarchyQueryBean.setQuery(queryBean);
					queryBeanList.add(hierarchyQueryBean);
				}
			}
			fileService.createJson(queryBeanList, hierarchyDefSId, versionNo);
			tx.commit();
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
			getHierarchyCustomizer(hierarchyLevelBean, result);
			hierarchyList.add(hierarchyLevelBean);
		}
		Collections.sort(hierarchyList);
		return hierarchyList;
	}

	public void getHierarchyCustomizer(HierarchyLevelDefinitionBean hierarchyLevelBean, Object[] result) {
		hierarchyLevelBean.setHierarchyLevelDefinitionSid((Integer) result[0]);
		hierarchyLevelBean.setLevelNo((((BigDecimal) result[1]).intValue()));
		hierarchyLevelBean.setLevelName((String) result[2]);
		hierarchyLevelBean.setLevelValueReference((String) result[3]);
		hierarchyLevelBean.setHierarchyDefinitionSid((Integer) result[4]);
		hierarchyLevelBean.setTableName((String) result[5]);
		hierarchyLevelBean.setFieldName((String) result[6]);
		hierarchyLevelBean.setVersionNo((Integer) result[7]);
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

	public void getLinkedValueQuery(HierarchyLevelDefinitionBean destinationHierarchyBean, int hierarchDefSid,
			int levelNo, List<HierarchyLevelDefinitionBean> hierarchyList, GtnFrameworkQueryGeneratorBean queryBean)
			throws GtnFrameworkGeneralException {
		final String hierarchyType = getHierarchyTypeFromSid(hierarchDefSid);
		final Set<String> tableNameList = getDefaultTableNameList(hierarchyType);
		Set<String> hierarchyTableList = HierarchyLevelDefinitionBean.getTableNameSet(hierarchyList);
		tableNameList.addAll(hierarchyTableList);
		if (tableNameList.isEmpty()) {
			return;
		}
		final GtnFrameworkSingleColumnRelationBean destinationkeyBean = gtnFrameworkEntityMasterBean
				.getKeyRelationBeanUsingTableIdAndColumnName(destinationHierarchyBean.getTableName(),
						destinationHierarchyBean.getFieldName());
		gtnHierarchyServiceBuilder.getSelectColumnsForRelationShipBuilder(destinationkeyBean, queryBean);
		gtnHierarchyServiceBuilder.getQueryByTableNameAndHierarchyTypeForMultiLevel(new ArrayList<>(tableNameList),
				hierarchyType, queryBean);

		if (!destinationkeyBean.isDescriptionColumnAvailable()) {
			gtnHierarchyServiceBuilder.addTableJoin(destinationkeyBean, queryBean);
		}
		final List<GtnFrameworkSingleColumnRelationBean> keyListBeanList = getKeyListBean(levelNo, hierarchyList);
		gtnHierarchyServiceBuilder.getWhereQuery(keyListBeanList, queryBean);
	}

	private Set<String> getDefaultTableNameList(String hierarchyType) {
		Set<String> selectedTableNamesList = new HashSet<>();
		if ("Customer Hierarchy".equalsIgnoreCase(hierarchyType)) {
			selectedTableNamesList.add("COMPANY_MASTER");
			selectedTableNamesList.add("CONTRACT_MASTER");
		} else if ("PRODUCT HIERARCHY".equalsIgnoreCase(hierarchyType)) {
			selectedTableNamesList.add("ITEM_MASTER");
		}
		return selectedTableNamesList;
	}

	private String getHierarchyTypeFromSid(int hierarchyDefinitionSid) throws GtnFrameworkGeneralException {
		final String query = getQuery("getHierarchyCatBySid");
		final Object[] params = { hierarchyDefinitionSid };
		final GtnFrameworkDataType[] paramsType = { GtnFrameworkDataType.INTEGER };
		final List<?> results = executeQuery(query, params, paramsType);
		return results.isEmpty() ? "" : results.get(0).toString();
	}

	public List<GtnFrameworkSingleColumnRelationBean> getKeyListBean(int levelNo,
			List<HierarchyLevelDefinitionBean> hierarchyList) {
		final List<GtnFrameworkSingleColumnRelationBean> keyListBeanList = new ArrayList<>();
		for (int i = 0; i < levelNo - 1; i++) {
			final HierarchyLevelDefinitionBean hierarchyBean = hierarchyList.get(i);
			if (!GtnFrameworkWebserviceConstant.USER_DEFINED.equals(hierarchyBean.getLevelValueReference())) {
				final GtnFrameworkSingleColumnRelationBean keyListBean = gtnFrameworkEntityMasterBean
						.getKeyRelationBeanUsingTableIdAndColumnName(hierarchyBean.getTableName(),
								hierarchyBean.getFieldName());
				keyListBeanList.add(keyListBean);
			}
		}
		return keyListBeanList;
	}

}
