package com.stpl.gtn.gtn2o.ws.module.itemgroups.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.config.GtnWsAllListConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsColumnDetailsConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.entity.companymaster.CompanyMaster;
import com.stpl.gtn.gtn2o.ws.entity.itemgroup.ItemGroup;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.itemgroup.bean.GtnItemGrpInformationBean;
import com.stpl.gtn.gtn2o.ws.itemgroup.bean.GtnWsItemGroupBean;
import com.stpl.gtn.gtn2o.ws.itemgroup.bean.GtnWsItemGrpDataBean;
import com.stpl.gtn.gtn2o.ws.logic.GtnWsSearchQueryGenerationLogic;
import com.stpl.gtn.gtn2o.ws.module.itemgroups.config.GtnWebServiceItemGroupConfig;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.itemgroup.GtnWsItemGroupRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.itemgroupreponse.GtnWsItemGroupResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import com.stpl.gtn.gtn2o.ws.util.GtnCommonUtil;

@Service()
@Scope(value = "singleton")
public class GtnWsItemGrpService {
	public GtnWsItemGrpService() {
		/**
		 * empty constructor
		 */
	}

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@Autowired
	private GtnWsAllListConfig gtnWebServiceAllListConfig;

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public int addItemQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		try {
			String sql = gtnWsSqlService.getQuery("getItemGrpAddDeclareQuery");

			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append(sql);

			List<GtnFrameworkDataType> dataTypeListTemp = new ArrayList<>();
			List<Object> paramListTemp = new ArrayList<>();

			List<GtnWsItemGrpDataBean> beanList = gtnWsRequest.getGtnWsItemGroupRequest().getGtnWsItemGrpDataBeanList();

			dataTypeListTemp.add(GtnFrameworkDataType.STRING);
			paramListTemp.add(gtnWsRequest.getGtnWsGeneralRequest().getUserId());

			dataTypeListTemp.add(GtnFrameworkDataType.STRING);
			paramListTemp.add(gtnWsRequest.getGtnWsGeneralRequest().getSessionId());

			for (GtnWsItemGrpDataBean gtnWsItemGrpDataBean : beanList) {

				String subSql = gtnWsSqlService.getQuery("getItemGrpAddQuery");

				dataTypeListTemp.add(GtnFrameworkDataType.INTEGER);
				paramListTemp.add(gtnWsItemGrpDataBean.getItemGroupSid());

				dataTypeListTemp.add(GtnFrameworkDataType.INTEGER);
				paramListTemp.add(gtnWsItemGrpDataBean.getItemMasterSid());

				dataTypeListTemp.add(GtnFrameworkDataType.INTEGER);
				paramListTemp.add(gtnWsItemGrpDataBean.getVersionNo());

				sqlQuery.append(subSql);
			}
			if (sqlQuery.length() > 0) {
				return gtnSqlQueryEngine.executeInsertOrUpdateQuery(sqlQuery.toString(), paramListTemp.toArray(),
						dataTypeListTemp.toArray(new GtnFrameworkDataType[dataTypeListTemp.size()]));
			}
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in addItemQuery ", e);
		}
		return 0;
	}

	public int getAddAllQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		try {
			GtnWsGeneralRequest generalWSRequest = gtnWsRequest.getGtnWsGeneralRequest();
			String query = gtnWsSqlService.getQuery("getItemGrpAddAllQuery");

			Object[] params = { generalWSRequest.getUserId(), generalWSRequest.getSessionId() };
			GtnFrameworkDataType[] dataType = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING };
			GtnWsSearchQueryConfig mainClauseConfig = new GtnWebServiceItemGroupConfig().getSearchQueryConfigMap()
					.get("cGrpAddTabSearchQuery");
			GtnWsSearchQueryGenerationLogic gtnWsSearchQueryGenerationLogic = new GtnWsSearchQueryGenerationLogic(
					mainClauseConfig, gtnWsRequest);
			query = query.replace("[$MAIN_QUERY_CLAUSE]", mainClauseConfig.getSearchQuery());
			query = query.replace("[$WHERE_CLAUSE]", gtnWsSearchQueryGenerationLogic.generateSearchQueryWhereClause());
			return gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, params, dataType);
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in addItemQuery ", e);
		}
	}

	public int getRemoveQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		try {
			StringBuilder sql = new StringBuilder();
			GtnWsGeneralRequest generalWSRequest = gtnWsRequest.getGtnWsGeneralRequest();
			List<GtnWsItemGrpDataBean> beanList = gtnWsRequest.getGtnWsItemGroupRequest().getGtnWsItemGrpDataBeanList();

			List<GtnFrameworkDataType> dataTypeListTemp = new ArrayList<>();
			List<Object> paramListTemp = new ArrayList<>();

			for (GtnWsItemGrpDataBean gtnWsItemGrpDataBean : beanList) {
				String query = gtnWsSqlService.getQuery("getItemGrpRemoveQuery");

				dataTypeListTemp.add(GtnFrameworkDataType.INTEGER);
				paramListTemp.add(gtnWsItemGrpDataBean.getItemMasterSid());

				dataTypeListTemp.add(GtnFrameworkDataType.STRING);
				paramListTemp.add(generalWSRequest.getUserId());

				dataTypeListTemp.add(GtnFrameworkDataType.STRING);
				paramListTemp.add(generalWSRequest.getSessionId());

				sql.append(query);
			}
			return gtnSqlQueryEngine.executeInsertOrUpdateQuery(sql.toString(), paramListTemp.toArray(),
					dataTypeListTemp.toArray(new GtnFrameworkDataType[dataTypeListTemp.size()]));
		} catch (GtnFrameworkGeneralException e) {
			throw new GtnFrameworkGeneralException("Error while executing Remove query ", e);
		}
	}

	public int getRemoveAllQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		try {
			GtnWsGeneralRequest generalWSRequest = gtnWsRequest.getGtnWsGeneralRequest();
			GtnWsSearchQueryConfig mainClauseConfig = new GtnWebServiceItemGroupConfig().getSearchQueryConfigMap()
					.get("itemGrpAddTabSelectedSearchQuery");
			GtnWsSearchQueryGenerationLogic gtnWsSearchQueryGenerationLogic = new GtnWsSearchQueryGenerationLogic(
					mainClauseConfig, gtnWsRequest);
			String query = "DELETE IMTD " + mainClauseConfig.getSearchQuery()
					+ gtnWsSearchQueryGenerationLogic.generateSearchQueryWhereClause();
			if (generalWSRequest.getUserId() != null) {
				query += " AND IMTD.USERS_ID = " + generalWSRequest.getUserId();
			}
			if (generalWSRequest.getSessionId() != null) {
				query += " AND IMTD.SESSION_ID = " + generalWSRequest.getSessionId();
			}
			return gtnSqlQueryEngine.executeInsertOrUpdateQuery(query);
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Error while executing Remove query ", e);
		}
	}

	public void getItemGrpFetch(GtnUIFrameworkWebserviceRequest gtnWsRequest, GtnUIFrameworkWebserviceResponse response)
			throws GtnFrameworkGeneralException {

		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			int systemId = gtnWsRequest.getGtnWsItemGroupRequest().getGtnWsItemGroupBean().getItemGrpInfoBean()
					.getItemGrpSid();

			ItemGroup itemGroup = session.get(ItemGroup.class, systemId);
			GtnItemGrpInformationBean itemGrpInfo = new GtnItemGrpInformationBean();
			itemGrpInfo.setItemGrpSid(itemGroup.getItemGroupSid());
			itemGrpInfo.setItemGrpName(itemGroup.getItemGroupName());
			itemGrpInfo.setItemGrpNo(itemGroup.getItemGroupNo());
			itemGrpInfo.setItemGrpDesc(itemGroup.getItemGroupDescription());
			itemGrpInfo.setCompanyMasterSid(itemGroup.getCompanyMaster().getCompanyMasterSid());
			itemGrpInfo.setVersionNo(itemGroup.getVersionNo());
			tx.commit();
			GtnWsItemGroupResponse itemGrpResponse = new GtnWsItemGroupResponse();
			itemGrpResponse.setGtnItemGrpInformationBean(itemGrpInfo);
			response.setGtnWsItemGroupResponse(itemGrpResponse);
		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException(GtnFrameworkWebserviceConstant.ERROR_WHILE_EXECUTING_QUERY, e);
		} finally {
			session.close();
		}
	}

	public int updateItemGrpDetails(GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		try {
			String query = gtnWsSqlService.getQuery("getItemGrpDetailsFetchQuery");
			GtnFrameworkDataType[] type = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
					GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER };
			Object[] params = { gtnWsRequest.getGtnWsGeneralRequest().getUserId(),
					gtnWsRequest.getGtnWsGeneralRequest().getSessionId(), gtnWsRequest.getGtnWsItemGroupRequest()
							.getGtnWsItemGroupBean().getItemGrpInfoBean().getItemGrpSid(), gtnWsRequest.getGtnWsItemGroupRequest()
							.getGtnWsItemGroupBean().getItemGrpInfoBean().getVersionNo() };
			return gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, params, type);
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException(e);
		}

	}

	public int getItemGrpDeleteQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		int count = 0;
		try {
			if (gtnWsRequest.getGtnWsItemGroupRequest() != null) {
				String query = gtnWsSqlService.getQuery("getItemGrpDeleteQuery");
				GtnFrameworkDataType[] type = { GtnFrameworkDataType.INTEGER };
				Object[] params = { gtnWsRequest.getGtnWsItemGroupRequest().getGtnWsItemGroupBean().getItemGrpInfoBean()
						.getItemGrpSid() };
				count = gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, params, type);
			}
			if (gtnWsRequest.getGtnWsGeneralRequest() != null) {
				String query = gtnWsSqlService.getQuery("getItemGrpTempTableDeleteQuery");
				GtnFrameworkDataType[] type = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING };
				Object[] params = { gtnWsRequest.getGtnWsGeneralRequest().getUserId(),
						gtnWsRequest.getGtnWsGeneralRequest().getSessionId() };
				count = gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, params, type);
			}
			return count;
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException(GtnFrameworkWebserviceConstant.ERROR_WHILE_EXECUTING_QUERY, e);
		}

	}

	public int saveItemGrpQuery(GtnWsItemGroupBean bean) throws GtnFrameworkGeneralException {
		GtnItemGrpInformationBean itemGrpInfo = bean.getItemGrpInfoBean();

		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			ItemGroup itemGroup = new ItemGroup();
			itemGroup.setItemGroupSid(itemGrpInfo.getItemGrpSid());
			itemGroup.setItemGroupName(itemGrpInfo.getItemGrpName());
			itemGroup.setItemGroupNo(itemGrpInfo.getItemGrpNo());
			itemGroup.setItemGroupDescription(itemGrpInfo.getItemGrpDesc());
			itemGroup.setCompanyMaster(session.load(CompanyMaster.class, itemGrpInfo.getCompanyMasterSid()));
			itemGroup.setVersionNo(1);
			itemGroup.setCreatedBy(itemGrpInfo.getModifiedBy());
			itemGroup.setCreatedDate(new Date());
			itemGroup.setModifiedBy(itemGrpInfo.getModifiedBy());
			itemGroup.setModifiedDate(new Date());
			tx.commit();
			return (int) session.save(itemGroup);
		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException(GtnFrameworkWebserviceConstant.ERROR_WHILE_EXECUTING_QUERY, e);
		} finally {
			session.close();
		}

	}

	public void updateItemGrpQuery(GtnWsItemGroupBean bean) throws GtnFrameworkGeneralException {

		GtnItemGrpInformationBean infoBean = bean.getItemGrpInfoBean();
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			ItemGroup itemGroup = session.load(ItemGroup.class, infoBean.getItemGrpSid());
			itemGroup.setVersionNo(infoBean.getVersionNo());
			itemGroup.setModifiedBy(infoBean.getModifiedBy());
			itemGroup.setModifiedDate(new Date());
			tx.commit();
			session.saveOrUpdate(itemGroup);
		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException(GtnFrameworkWebserviceConstant.ERROR_WHILE_EXECUTING_QUERY, e);
		} finally {
			session.close();
		}
	}

	public int updateItemGrpDetailsTable(GtnUIFrameworkWebserviceRequest gtnWsRequest, int itemGrpSid, GtnWsItemGroupBean itemGrpBean)
			throws GtnFrameworkGeneralException {
		try {
			String query = gtnWsSqlService.getQuery("getItemGrpDetailsUpdateQuery");
			GtnFrameworkDataType[] type = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
					GtnFrameworkDataType.STRING, GtnFrameworkDataType.INTEGER };
			Object[] params = { gtnWsRequest.getGtnWsGeneralRequest().getUserId(),
					gtnWsRequest.getGtnWsGeneralRequest().getSessionId(), String.valueOf(itemGrpSid), itemGrpBean.getItemGrpInfoBean().getVersionNo() };
			return gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, params, type);
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException(e);
		}

	}

	@SuppressWarnings("unchecked")
	public int getItemGroupImtdCount(GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		try {
			String query = gtnWsSqlService.getQuery("getItemGrpImtdCountValidationQuery");
			GtnFrameworkDataType[] type = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING };
			Object[] params = { gtnWsRequest.getGtnWsGeneralRequest().getUserId(),
					gtnWsRequest.getGtnWsGeneralRequest().getSessionId() };
			return (int) ((List<Object>) gtnSqlQueryEngine.executeSelectQuery(query, params, type)).get(0);
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException(e);
		}

	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getSelectedTableData(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnWsSearchQueryConfig mainClauseConfig) throws GtnFrameworkGeneralException {
		try {
			GtnWsGeneralRequest generalWSRequest = gtnWsRequest.getGtnWsGeneralRequest();
			String itemGroupSelectedQuery;
			if (gtnWsRequest.getGtnWsSearchRequest().isCount()) {
				itemGroupSelectedQuery = gtnWsSqlService.getQuery("getItemGrpSelectedTableCountQuery");
			} else {
				itemGroupSelectedQuery = gtnWsSqlService.getQuery("getItemGrpSelectedTableQuery");
			}
			GtnWsSearchQueryGenerationLogic itemGroupSelectedQueryGenerationLogic = new GtnWsSearchQueryGenerationLogic(
					mainClauseConfig, gtnWsRequest);
			itemGroupSelectedQuery += itemGroupSelectedQueryGenerationLogic.generateSearchQueryWhereClause();

			if (generalWSRequest.getUserId() != null) {
				itemGroupSelectedQuery += " AND IMTD.USERS_ID = " + generalWSRequest.getUserId();
			}
			if (generalWSRequest.getSessionId() != null) {
				itemGroupSelectedQuery += " AND IMTD.SESSION_ID = " + generalWSRequest.getSessionId();
			}
			if (!gtnWsRequest.getGtnWsSearchRequest().isCount()) {
				itemGroupSelectedQuery += itemGroupSelectedQueryGenerationLogic.generateSearchQueryOrderByAndOffset();
			}
			return (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(itemGroupSelectedQuery);
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException(e);
		}

	}

	public void getCustomizedSearchFormFromObject(final List<?> list,
			GtnWsSearchQueryConfig gtnWebServiceSearchQueryContext, List<Object> columnName) {

		Map<String, GtnWsColumnDetailsConfig> rearrangedOrder = gtnWebServiceSearchQueryContext
				.getFieldToColumnDetailsMap();
		Map<Integer, String> helperMap = gtnWebServiceAllListConfig.getIdDescMap();
		List<String> typeList = new ArrayList<>();
		for (Object ob : columnName) {
			if (rearrangedOrder.get(ob) != null) {
				typeList.add(rearrangedOrder.get(ob).getDataType());
			}

		}
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				final Object[] obj = (Object[]) list.get(i);

				for (int index = 0; index < obj.length; index++) {
					switch (typeList.get(index)) {
					case "String":
						obj[index] = GtnCommonUtil.getString(obj[index]);
						break;
					case "Integer":
						// nothing to do
						break;
					case "Helper":
						obj[index] = getHelperValue(obj[index], helperMap);
						break;
					case "Boolean":
						obj[index] = GtnCommonUtil.getString(obj[index]);
						break;
					case "Date":
						Date startDate = (Date) obj[index];
						obj[index] = startDate;
						break;
					case "BigDecimal":
						BigDecimal numericColumn = (BigDecimal) obj[index];
						obj[index] = getDoubleValue(numericColumn);
						break;
					default:
						obj[index] = GtnCommonUtil.getString(obj[index]);
						break;
					}
				}
			}
		}
	}

	Object getDoubleValue(BigDecimal numericColumn) {
		return numericColumn == null ? numericColumn : numericColumn.doubleValue();
	}

	Object getHelperValue(Object value, Map<Integer, String> helperMap) {

		if (value != null && StringUtils.isNotBlank(value.toString()) && (Integer) value != 0) {
			return helperMap.get(value);
		}
		return GtnFrameworkCommonStringConstants.STRING_EMPTY;
	}

	@SuppressWarnings("unchecked")
	public List<Object> getItemGrpNameDuplicateValidationService(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		try {
			GtnWsItemGroupRequest gtnWsIGrpRequest = gtnWsRequest.getGtnWsItemGroupRequest();
			String query = gtnWsSqlService.getQuery("getItemGrpNameDuplicateValidationQuery");
			GtnFrameworkDataType[] type = { GtnFrameworkDataType.STRING };
			Object[] params = { gtnWsIGrpRequest.getGtnWsItemGroupBean().getItemGrpInfoBean().getItemGrpName() };
			Integer systemId = gtnWsIGrpRequest.getGtnWsItemGroupBean().getItemGrpInfoBean().getItemGrpSid();
			if (systemId != null && systemId > 0) {
				query += " AND ITEM_GROUP_SID <> " + systemId + " ;";
			}

			return (List<Object>) gtnSqlQueryEngine.executeSelectQuery(query, params, type);
		} catch (GtnFrameworkGeneralException e) {
			throw new GtnFrameworkGeneralException(GtnFrameworkWebserviceConstant.ERROR_WHILE_EXECUTING_QUERY, e);
		}

	}

	public void clearItemGroupTempTable(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {

		String itemGrpTempTableQuery = gtnWsSqlService.getQuery("itemGroupTempTableClear");

		Object[] itemGrpTempTableParams = { gtnWsRequest.getGtnWsGeneralRequest().getUserId(),
				gtnWsRequest.getGtnWsGeneralRequest().getSessionId() };
		GtnFrameworkDataType[] itemGrpTempDatatype = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING };
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(itemGrpTempTableQuery, itemGrpTempTableParams, itemGrpTempDatatype);

	}

}
