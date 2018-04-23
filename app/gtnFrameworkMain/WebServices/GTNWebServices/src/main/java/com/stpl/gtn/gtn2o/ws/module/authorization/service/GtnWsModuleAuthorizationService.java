package com.stpl.gtn.gtn2o.ws.module.authorization.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.procedure.ProcedureCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.authorization.bean.GtnWsModuleAuthorizationBean;
import com.stpl.gtn.gtn2o.ws.authorization.bean.GtnWsModuleComponentBean;
import com.stpl.gtn.gtn2o.ws.authorization.bean.GtnWsTablePropertyBean;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.entity.security.GtnModuleComponentMaster;
import com.stpl.gtn.gtn2o.ws.entity.security.GtnModuleComponentRoleDetails;
import com.stpl.gtn.gtn2o.ws.entity.security.GtnUitableComponentDetails;
import com.stpl.gtn.gtn2o.ws.entity.security.ModuleSubmoduleMaster;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.authorization.GtnWsModuleAuthorizationGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.authorization.GtnWsModuleAuthorizationGeneralResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import com.stpl.gtn.gtn2o.ws.util.GtnCommonUtil;

@Service()
@Scope(value = "singleton")
public class GtnWsModuleAuthorizationService {
	public GtnWsModuleAuthorizationService() {
		super();
	}

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsModuleAuthorizationService.class);

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private org.hibernate.SessionFactory sysSessionFactory;

	public void saveAuthorizationDetails(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		LOGGER.debug("Entering saveSecurityDetails method");
		GtnWsModuleAuthorizationGeneralRequest moduleSecurityGeneralRequest = gtnWsRequest
				.getGtnWsModuleAuthorizationGeneralRequest();
		List<GtnWsModuleAuthorizationBean> gtnSecurityBeanList = moduleSecurityGeneralRequest
				.getGtnWsModuleSecuritySaveBeanList();
		GtnWsGeneralRequest gtnSecurityGeneralRequest = gtnWsRequest.getGtnWsGeneralRequest();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			for (GtnWsModuleAuthorizationBean gtnWsModuleSecurityBean : gtnSecurityBeanList) {
				GtnModuleComponentRoleDetails moduleCompRoleDetailsModel = getGtnModuleComponentRoleDetailsModel(
						gtnWsModuleSecurityBean, gtnSecurityGeneralRequest, session);
				session.saveOrUpdate(moduleCompRoleDetailsModel);
			}

			transaction.commit();
		} catch (Exception ex) {
			transaction.rollback();
			throw new GtnFrameworkGeneralException("Exception in saveSecurityDetails", ex);
		} finally {
			session.close();
		}
	}

	private GtnModuleComponentRoleDetails getGtnModuleComponentRoleDetailsModel(
			GtnWsModuleAuthorizationBean gtnSecurityBean, GtnWsGeneralRequest gtnSecurityGeneralRequest,
			Session session) {
		GtnModuleComponentRoleDetails moduleCompRoleDetailsModel = new GtnModuleComponentRoleDetails();
		Integer userId = Integer.valueOf(gtnSecurityGeneralRequest.getUserId());
		Integer moduleCompRoleDetailsSid = gtnSecurityBean.getGtnModuleComponentRoleDetailsSid();
		if (moduleCompRoleDetailsSid != null && moduleCompRoleDetailsSid > 0) {
			moduleCompRoleDetailsModel = session.load(GtnModuleComponentRoleDetails.class, moduleCompRoleDetailsSid);
			moduleCompRoleDetailsModel.setModifiedBy(userId);
			moduleCompRoleDetailsModel.setModifiedDate(new Date());
		}

		moduleCompRoleDetailsModel.setGtnRoleMasterSid(gtnSecurityBean.getGtnRoleMasterSid());
		moduleCompRoleDetailsModel.setGtnModuleMasterSid(gtnSecurityBean.getGtnModuleMasterSid());
		moduleCompRoleDetailsModel.setGtnComponentSid(gtnSecurityBean.getGtnComponentSid());
		moduleCompRoleDetailsModel.setIsEditable(gtnSecurityBean.getIsEditable());
		moduleCompRoleDetailsModel.setIsVisible(gtnSecurityBean.getIsVisible());
		moduleCompRoleDetailsModel.setIsTableProperty(gtnSecurityBean.getIsTableProperty());
		moduleCompRoleDetailsModel.setCreatedBy(userId);
		moduleCompRoleDetailsModel.setCreatedDate(new Date());

		return moduleCompRoleDetailsModel;
	}

	public List<Object[]> getComponentAuthorizationDetails(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		GtnWsSearchRequest searchRequest = gtnWsRequest.getGtnWsSearchRequest();
		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = searchRequest
				.getGtnWebServiceSearchCriteriaList();
		List<Object> roleDetailsSelectQueryParams = new ArrayList<>();
		for (GtnWebServiceSearchCriteria gtnWebServiceSearchCriteria : gtnWebServiceSearchCriteriaList) {
			roleDetailsSelectQueryParams.add(gtnWebServiceSearchCriteria.getFilterValue1());
		}
		String roleDetailsSelectQuery = gtnWsSqlService.getQuery(searchRequest.getSearchQueryName().trim());
		GtnFrameworkDataType[] roleDetailsSelectQueryTypes = { GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING };
		return (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(roleDetailsSelectQuery,
				roleDetailsSelectQueryParams.toArray(), roleDetailsSelectQueryTypes);

	}

	public void getModuleWiseAuthorizationDetails(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnWsModuleAuthorizationGeneralResponse moduleWiseSecurityResponse) throws GtnFrameworkGeneralException {
		GtnWsModuleAuthorizationGeneralRequest moduleSecurityGeneralRequest = gtnWsRequest
				.getGtnWsModuleAuthorizationGeneralRequest();

		String catelog = "";
		try (Connection connection = sysSessionFactory.getSessionFactoryOptions().getServiceRegistry()
				.getService(ConnectionProvider.class).getConnection()) {
			catelog = connection.getCatalog();
		} catch (SQLException exception) {

			throw new GtnFrameworkGeneralException("", exception);
		}
		GtnWsModuleAuthorizationBean modulewiseSecurityBean = moduleSecurityGeneralRequest.getGtnWsModuleSecurityBean();
		String moduleName = modulewiseSecurityBean.getModuleName();
		String getModuleWiseComponentQuery = gtnWsSqlService.getQuery("getModuleWiseComponentDetailsQuery");
		GtnFrameworkDataType[] moduleWiseQueryDataType = { GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING };
		Object[] moduleWiseQueryDataParams = { Integer.valueOf(gtnWsRequest.getGtnWsGeneralRequest().getUserId()),
				catelog, moduleName.trim() };
		List<Object[]> moduleWiseComponentResultList = (List<Object[]>) gtnSqlQueryEngine
				.executeSelectQuery(getModuleWiseComponentQuery, moduleWiseQueryDataParams, moduleWiseQueryDataType);
		setModuleWiseResponse(moduleWiseComponentResultList, moduleWiseSecurityResponse, false);
		String getModuleWiseTableDetailsQuery = gtnWsSqlService.getQuery("getModuleWiseTablePropertyDetailsQuery");
		List<Object[]> moduleWiseTableDetailsResultList = (List<Object[]>) gtnSqlQueryEngine
				.executeSelectQuery(getModuleWiseTableDetailsQuery, moduleWiseQueryDataParams, moduleWiseQueryDataType);
		setModuleWiseResponse(moduleWiseTableDetailsResultList, moduleWiseSecurityResponse, true);
	}

	private void setModuleWiseResponse(List<Object[]> moduleWiseComponentResultList,
			GtnWsModuleAuthorizationGeneralResponse moduleWiseSecurityResponse, boolean isTableDetails) {
		if (moduleWiseComponentResultList != null) {
			List<GtnWsModuleAuthorizationBean> tablePropertyBeanList = new ArrayList<>();

			for (Object[] resultArray : moduleWiseComponentResultList) {
				GtnWsModuleAuthorizationBean gtnWsModuleSecurityBean = new GtnWsModuleAuthorizationBean();
				gtnWsModuleSecurityBean.setComponentId(GtnCommonUtil.getString(resultArray[0]));
				gtnWsModuleSecurityBean.setIsVisible((Boolean) resultArray[3]);
				gtnWsModuleSecurityBean.setIsEditable((Boolean) resultArray[4]);
				gtnWsModuleSecurityBean.setIsTableProperty((Boolean) resultArray[5]);
				gtnWsModuleSecurityBean.setModuleName(GtnCommonUtil.getString(resultArray[6]));
				gtnWsModuleSecurityBean.setGtnComponentSid(GtnCommonUtil.getInteger(resultArray[7]));
				if (isTableDetails) {
					gtnWsModuleSecurityBean.setTablePropertyId(GtnCommonUtil.getString(resultArray[8]));
					GtnWsModuleAuthorizationBean udpateBean = tablePropertyBeanList.isEmpty() ? null
							: tablePropertyBeanList.get(tablePropertyBeanList.size() - 1);
					compareAuthorizationPropertyBean(udpateBean, gtnWsModuleSecurityBean, tablePropertyBeanList);
				} else {
					gtnWsModuleSecurityBean.setComponentType(GtnCommonUtil.getString(resultArray[1]));
					moduleWiseSecurityResponse
							.addModuleWiseComponentMap(gtnWsModuleSecurityBean.getComponentId(),
									getUpdatedAuthorizationBean(
											moduleWiseSecurityResponse.getModuleWiseComponentMap(
													gtnWsModuleSecurityBean.getComponentId()),
											gtnWsModuleSecurityBean));

				}

			}

			if (isTableDetails) {
				moduleWiseSecurityResponse.setModuleWiseTablePropertyList(tablePropertyBeanList);
			}
		}
	}

	private GtnWsModuleAuthorizationBean getUpdatedAuthorizationBean(GtnWsModuleAuthorizationBean update,
			GtnWsModuleAuthorizationBean source) {
		if (update == null) {
			return source;
		}
		update.setIsEditable(update.getIsEditable() || source.getIsEditable());
		update.setIsVisible(update.getIsVisible() || source.getIsVisible());
		return update;
	}

	private void compareAuthorizationPropertyBean(GtnWsModuleAuthorizationBean update,
			GtnWsModuleAuthorizationBean source, List<GtnWsModuleAuthorizationBean> tablePropertyBeanList) {
		if (update == null) {
			tablePropertyBeanList.add(source);
			return;
		}
		if (update.getComponentId().equals(source.getComponentId())
				&& update.getTablePropertyId().equals(source.getTablePropertyId())) {
			getUpdatedAuthorizationBean(update, source);
			return;
		}
		tablePropertyBeanList.add(source);
	}

	public void saveComponentDetails(GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		GtnWsModuleAuthorizationGeneralRequest moduleSecurityGeneralRequest = gtnWsRequest
				.getGtnWsModuleAuthorizationGeneralRequest();
		List<GtnWsModuleComponentBean> gtnModuleComponentBeanList = moduleSecurityGeneralRequest
				.getGtnWsModuleComponentBeanList();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		List<String> propertyIds = new ArrayList<>();
		propertyIds.add("submoduleName");
		try {
			for (GtnWsModuleComponentBean gtnWsModuleComponentBean : gtnModuleComponentBeanList) {
				List<Object> values = new ArrayList<>();
				values.add(gtnWsModuleComponentBean.getModuleName());
				GtnModuleComponentMaster gtnModuleComponentMaster = getGtnModuleMasterModel(gtnWsModuleComponentBean,
						gtnWsRequest.getGtnWsGeneralRequest(), session, propertyIds, values);
				if (!ifRecordExists(gtnWsModuleComponentBean, session, propertyIds, values)) {
					session.saveOrUpdate(gtnModuleComponentMaster);
				}
			}
			transaction.commit();
		} catch (Exception ex) {
			transaction.rollback();
			throw new GtnFrameworkGeneralException("Exception in saveComponentDetails", ex);
		} finally {
			session.close();
		}

	}

	private GtnModuleComponentMaster getGtnModuleMasterModel(GtnWsModuleComponentBean gtnModuleComponentBean,
			GtnWsGeneralRequest gtnSecurityGeneralRequest, Session session, List<String> propertyIds,
			List<Object> values) throws GtnFrameworkGeneralException {
		List<GtnWsTablePropertyBean> gtnWsTablePropertyBeanList = gtnModuleComponentBean.getTablePropertyList();
		ModuleSubmoduleMaster moduleSubmoduleMaster = (ModuleSubmoduleMaster) GtnCommonUtil
				.getAnyModelUsingCriteria(propertyIds, values, ModuleSubmoduleMaster.class, session);
		GtnModuleComponentMaster moduleCompMasterModel = new GtnModuleComponentMaster();
		Integer userId = Integer.valueOf(gtnSecurityGeneralRequest.getUserId());
		moduleCompMasterModel.setModuleSubmoduleMaster(moduleSubmoduleMaster);
		moduleCompMasterModel.setComponentId(gtnModuleComponentBean.getComponentId());
		moduleCompMasterModel
				.setComponentDesc(GtnCommonUtil.getString(gtnModuleComponentBean.getComponentDesc()).isEmpty()
						? gtnModuleComponentBean.getComponentId() : gtnModuleComponentBean.getComponentDesc());
		moduleCompMasterModel.setComponentType(gtnModuleComponentBean.getComponentType());
		moduleCompMasterModel.setScreenName(gtnModuleComponentBean.getScreenName());
		moduleCompMasterModel.setCreatedBy(userId);
		moduleCompMasterModel.setCreatedDate(new Date());
		moduleCompMasterModel.setModifiedBy(userId);
		moduleCompMasterModel.setModifiedDate(new Date());
		Set gtnUitableComponentDetailses = getGtnUiTableComponentDetailsModelSet(gtnWsTablePropertyBeanList,
				gtnSecurityGeneralRequest, moduleSubmoduleMaster);
		moduleCompMasterModel.setGtnUitableComponentDetailses(gtnUitableComponentDetailses);
		return moduleCompMasterModel;
	}

	private Set getGtnUiTableComponentDetailsModelSet(List<GtnWsTablePropertyBean> gtnWsTablePropertyBeanList,
			GtnWsGeneralRequest gtnSecurityGeneralRequest, ModuleSubmoduleMaster moduleCompMasterModel) {
		Set gtnUitableComponentDetailsSet = new HashSet(0);
		if (gtnWsTablePropertyBeanList != null && !gtnWsTablePropertyBeanList.isEmpty()) {
			for (GtnWsTablePropertyBean gtnWsTablePropertyBean : gtnWsTablePropertyBeanList) {
				GtnUitableComponentDetails uitableComponentDetails = getGtnUiTableComponentDetailsModel(
						gtnWsTablePropertyBean, gtnSecurityGeneralRequest, moduleCompMasterModel);
				gtnUitableComponentDetailsSet.add(uitableComponentDetails);
			}
		}
		return gtnUitableComponentDetailsSet;
	}

	private GtnUitableComponentDetails getGtnUiTableComponentDetailsModel(GtnWsTablePropertyBean gtnTablePropertyBean,
			GtnWsGeneralRequest gtnSecurityGeneralRequest, ModuleSubmoduleMaster moduleSubmoduleMaster) {

		GtnUitableComponentDetails uiTableComponentDetails = new GtnUitableComponentDetails();
		Integer userId = Integer.valueOf(gtnSecurityGeneralRequest.getUserId());
		uiTableComponentDetails.setModuleSubmoduleMaster(moduleSubmoduleMaster);
		uiTableComponentDetails.setSingleHeaderVisibleColumns(gtnTablePropertyBean.getSingleHeaderVisibleColumns());
		uiTableComponentDetails.setSingleHeaderVisibleHeaders(gtnTablePropertyBean.getSingleHeaderVisibleHeaders());
		uiTableComponentDetails.setDoubleHeaderVisibleColumns(gtnTablePropertyBean.getDoubleHeaderVisibleColumns());
		uiTableComponentDetails.setDoubleHeaderVisibleHeaders(gtnTablePropertyBean.getDoubleHeaderVisibleHeaders());
		uiTableComponentDetails.setCreatedBy(userId);
		uiTableComponentDetails.setCreatedDate(new Date());
		uiTableComponentDetails.setModifiedBy(userId);
		uiTableComponentDetails.setModifiedDate(new Date());
		return uiTableComponentDetails;
	}

	private boolean ifRecordExists(GtnWsModuleComponentBean gtnWsModuleComponentBean, Session session,
			List<String> propertyIds, List<Object> values) throws GtnFrameworkGeneralException {
		ModuleSubmoduleMaster moduleSubmoduleMaster = (ModuleSubmoduleMaster) GtnCommonUtil
				.getAnyModelUsingCriteria(propertyIds, values, ModuleSubmoduleMaster.class, session);
		List<String> duplicateCheckPropertyIds = new ArrayList<>();
		List<Object> duplicateCheckPropertyValues = new ArrayList<>();
		duplicateCheckPropertyIds.add("moduleSubmoduleMaster");
		duplicateCheckPropertyIds.add("componentId");
		duplicateCheckPropertyIds.add("componentDesc");
		duplicateCheckPropertyIds.add("componentType");
		duplicateCheckPropertyIds.add("screenName");

		duplicateCheckPropertyValues.add(moduleSubmoduleMaster);
		duplicateCheckPropertyValues.add(gtnWsModuleComponentBean.getComponentId());
		duplicateCheckPropertyValues.add(gtnWsModuleComponentBean.getComponentDesc());
		duplicateCheckPropertyValues.add(gtnWsModuleComponentBean.getComponentType());
		duplicateCheckPropertyValues.add(gtnWsModuleComponentBean.getScreenName());

		GtnModuleComponentMaster moduleComponentMaster = (GtnModuleComponentMaster) GtnCommonUtil
				.getAnyModelUsingCriteria(duplicateCheckPropertyIds, duplicateCheckPropertyValues,
						GtnModuleComponentMaster.class, session);

		return moduleComponentMaster != null;
	}

	@SuppressWarnings("unchecked")
	public boolean getUpdateModuleFlag(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		GtnWsModuleAuthorizationGeneralRequest moduleSecurityGeneralRequest = gtnWsRequest
				.getGtnWsModuleAuthorizationGeneralRequest();
		GtnWsModuleAuthorizationBean modulewiseSecurityBean = moduleSecurityGeneralRequest.getGtnWsModuleSecurityBean();
		String moduleName = modulewiseSecurityBean.getModuleName();
		String getModuleWiseComponentQuery = gtnWsSqlService.getQuery("getModuleUpdateFlagQuery");
		GtnFrameworkDataType[] moduleWiseQueryDataType = { GtnFrameworkDataType.STRING };
		Object[] moduleWiseQueryDataParams = { moduleName.trim() };
		List<Object> moduleWiseComponentResultList = (List<Object>) gtnSqlQueryEngine
				.executeSelectQuery(getModuleWiseComponentQuery, moduleWiseQueryDataParams, moduleWiseQueryDataType);
		if (moduleWiseComponentResultList.isEmpty() || moduleWiseComponentResultList.get(0) == null) {
			return false;
		}
		return (boolean) moduleWiseComponentResultList.get(0);
	}

	public void updateUpdateModuleFlag(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		GtnWsModuleAuthorizationGeneralRequest moduleSecurityGeneralRequest = gtnWsRequest
				.getGtnWsModuleAuthorizationGeneralRequest();
		GtnWsModuleAuthorizationBean modulewiseSecurityBean = moduleSecurityGeneralRequest.getGtnWsModuleSecurityBean();
		String moduleName = modulewiseSecurityBean.getModuleName();
		String getModuleWiseComponentQuery = gtnWsSqlService.getQuery("getModuleUpdateFlagUpdateQuery");
		GtnFrameworkDataType[] moduleWiseQueryDataType = { GtnFrameworkDataType.STRING };
		Object[] moduleWiseQueryDataParams = { moduleName.trim() };
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(getModuleWiseComponentQuery, moduleWiseQueryDataParams,
				moduleWiseQueryDataType);
	}

	public void updateModuleFlagInModuleMaster(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		GtnWsModuleAuthorizationGeneralRequest moduleSubmoduleUpdateGenRequest = gtnWsRequest
				.getGtnWsModuleAuthorizationGeneralRequest();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			for (GtnWsModuleAuthorizationBean updateModuleMasterBean : moduleSubmoduleUpdateGenRequest
					.getGtnWsModuleSecuritySaveBeanList()) {
				int moduleSid = updateModuleMasterBean.getModuleSubModuleSid();
				ModuleSubmoduleMaster submoduleMaster = session.load(ModuleSubmoduleMaster.class, moduleSid);
				submoduleMaster.setUpdateModuleFlag(updateModuleMasterBean.isUpdateModuleFlag());
				session.saveOrUpdate(submoduleMaster);
			}
			transaction.commit();
		} catch (Exception exception) {
			transaction.rollback();
			throw new GtnFrameworkGeneralException("Exception in updateModuleFlagInModuleMaster", exception);
		} finally {
			session.close();
		}

	}

	public void populateGtnModuleComponentRoleDetails() throws GtnFrameworkGeneralException {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			ProcedureCall procedureCall = session.createStoredProcedureCall("PRC_GTN_MODULE_COMPONENT_POPULATION");
			procedureCall.getOutputs();
			transaction.commit();
		} catch (Exception ex) {
			transaction.rollback();
			throw new GtnFrameworkGeneralException("Exception while calling the procedure", ex);
		} finally {
			session.close();
		}
	}

}
