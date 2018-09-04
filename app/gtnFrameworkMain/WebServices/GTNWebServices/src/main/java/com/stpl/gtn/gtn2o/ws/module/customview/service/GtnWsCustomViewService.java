/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.customview.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkQueryGeneratorService;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.entity.customviewforecast.CustViewDetails;
import com.stpl.gtn.gtn2o.ws.entity.customviewforecast.CustViewMaster;
import com.stpl.gtn.gtn2o.ws.entity.customviewforecast.CustomViewVariables;
import com.stpl.gtn.gtn2o.ws.entity.relationshipbuilder.RelationshipBuilder;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticRelationUpdateService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsHierarchyType;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportVariablesType;
import com.stpl.gtn.gtn2o.ws.request.customview.GtnWsCustomViewRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsCustomViewResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import com.stpl.gtn.gtn2o.ws.util.GtnWsConstants;

/**
 *
 * @author Lokeshwari.Kumarasam
 */
@Service
@Scope(value = "singleton")
public class GtnWsCustomViewService {

	private static final String REPORT = "report";
	private static final String CUSTOM_VIEW_MASTER_SID_DB_PROPERTY = "customViewMasterSid";

	public GtnWsCustomViewService() {
		super();
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsCustomViewService.class);

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@Autowired
	private GtnFrameworkQueryGeneratorService queryGeneratorService;

	@Autowired
	private GtnFrameworkAutomaticRelationUpdateService relationUpdateService;

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void checkCustomViewSave(GtnWsCustomViewRequest cvRequest, GtnWsCustomViewResponse cvResponse)
			throws GtnFrameworkGeneralException {
		try {
			cvResponse.setSuccess(true);
			if (cvRequest.getCvSysId() == 0 && checkDuplicateCustomViewName(cvRequest)) {
				cvResponse.setSuccess(false);
				cvResponse.setMessageType(GtnFrameworkCommonStringConstants.ERROR);
				cvResponse.setMessage("Entered Custom View Name already exists.");
				return;
			}
		} catch (Exception e) {
			cvResponse.setSuccess(false);
			throw new GtnFrameworkGeneralException("Exception in checkSaveRelationship", e);
		}

	}

	@SuppressWarnings("rawtypes")
	private boolean checkDuplicateCustomViewName(GtnWsCustomViewRequest cvRequest) throws GtnFrameworkGeneralException {
		int relationCount = 0;
		try {
			List<String> inputlist = new ArrayList<>();
			inputlist.add(cvRequest.getCustomViewName());
			String query = cvRequest.getCustomViewType().startsWith(REPORT) ? "getCustomViewNameDuplicateCheckReport"
					: "getCustomViewNameDuplicateCheck";
			List result = executeQuery(getQuery(query), inputlist);
			if (result != null && !result.isEmpty()) {
				relationCount = Integer.parseInt(String.valueOf(result.get(0)));
			}
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in checkDuplicateRelationshipName", e);
		}
		return relationCount != 0;
	}

	@SuppressWarnings("rawtypes")
	public GtnWsCustomViewResponse saveCustomView(GtnWsCustomViewRequest cvRequest) {
		GtnWsCustomViewResponse cvResponse = new GtnWsCustomViewResponse();
		int customViewMasterSid = saveCustViewMaster(cvRequest);
		saveCustomViewDetails(customViewMasterSid, cvRequest);
		customViewSaveLogicCCPDetails(customViewMasterSid, cvRequest);
		if (!cvRequest.getCustomViewType().startsWith(REPORT)) {
			callProcedureForDiscountPopulation(customViewMasterSid);
		}
		cvResponse.setSuccess(true);
		cvResponse.setCvSysId(customViewMasterSid);
		return cvResponse;
	}

	private int saveCustViewMaster(GtnWsCustomViewRequest cvRequest) {
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		int customViewMasterSid = 0;
		try {
			if (cvRequest.getCvSysId() == 0) {
				CustViewMaster master = new CustViewMaster();
				master.setCustViewName(cvRequest.getCustomViewName());
				master.setCustViewDescription(cvRequest.getCustomViewDescription());
				master.setCustViewType(cvRequest.getCustomViewType());
				master.setCustomerRelationshipSid(cvRequest.getCustomerRelationshipSid());
				master.setProductRelationshipSid(cvRequest.getProductRelationshipSid());
				master.setCreatedBy(Integer.valueOf(cvRequest.getCreatedBy()));
				master.setCreatedDate(cvRequest.getCreatedDate());
				master.setModifiedBy(Integer.valueOf(cvRequest.getModifiedBy()));
				master.setModuleType(cvRequest.getModuleType());
				master.setModifiedDate(cvRequest.getModifiedDate());
				master.setScreenName(cvRequest.getCustomViewType());
				customViewMasterSid = (int) session.save(master);
			} else {
				CustViewMaster master = session.get(CustViewMaster.class, cvRequest.getCvSysId());
				master.setCustViewName(cvRequest.getCustomViewName());
				master.setCustViewDescription(cvRequest.getCustomViewDescription());
				master.setCustViewType(cvRequest.getCustomViewType());
				master.setCustomerRelationshipSid(cvRequest.getCustomerRelationshipSid());
				master.setProductRelationshipSid(cvRequest.getProductRelationshipSid());
				master.setModifiedBy(Integer.valueOf(cvRequest.getModifiedBy()));
				master.setModifiedDate(cvRequest.getModifiedDate());
				master.setModuleType(cvRequest.getModuleType());
				master.setScreenName(cvRequest.getCustomViewType());
				session.update(master);
				customViewMasterSid = cvRequest.getCvSysId();
			}
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			logger.error(e.getMessage(), e);
		} finally {
			session.close();
		}
		return customViewMasterSid;
	}

	public boolean deleteCustViewMaster(GtnWsCustomViewRequest cvRequest) {
		Transaction tx = null;
		try (Session session = getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			if (cvRequest.getCvSysId() != 0) {
				Object[] param = { cvRequest.getCvSysId() };
				GtnFrameworkDataType[] type = { GtnFrameworkDataType.INTEGER };
				gtnSqlQueryEngine.executeInsertOrUpdateQuery(gtnWsSqlService.getQuery("getCustomCCPDetailsDeleteQuery"),
						param, type);

				List<CustViewDetails> details = session.createCriteria(CustViewDetails.class)
						.add(Restrictions.eq(CUSTOM_VIEW_MASTER_SID_DB_PROPERTY, cvRequest.getCvSysId())).list();
				customViewDetailsAndVariablesDeletion(details, session);

				CustViewMaster master = session.load(CustViewMaster.class, cvRequest.getCvSysId());
				if (master != null) {
					session.delete(master);
				}
			}
			tx.commit();
			return true;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	private void customViewDetailsAndVariablesDeletion(List<CustViewDetails> details, Session session) {
		if (details != null) {
			for (CustViewDetails custViewDetails : details) {
				if (custViewDetails.getHierarchyIndicator() == 'V') {
					List<CustomViewVariables> variables = session.createCriteria(CustomViewVariables.class)
							.add(Restrictions.eq("customViewDetailsSid", custViewDetails.getCustomViewDetailsSid()))
							.list();
					if (variables != null) {
						for (CustomViewVariables customViewVariables : variables) {
							session.delete(customViewVariables);
						}
					}
				}
				session.delete(custViewDetails);
			}
		}
	}

	public boolean validateCustViewUser(GtnWsCustomViewRequest cvRequest) {
		Transaction tx = null;
		try (Session session = getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			CustViewMaster master = session.get(CustViewMaster.class, cvRequest.getCvSysId());
			if (master != null) {
				return master.getCreatedBy() == Integer.parseInt(cvRequest.getCreatedBy());
			}
			tx.commit();
			return false;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	public GtnWsCustomViewResponse getCustViewMaster(GtnWsCustomViewRequest cvRequest) {
		GtnWsCustomViewResponse response = null;
		try (Session session = getSessionFactory().openSession()) {
			CustViewMaster master = session.get(CustViewMaster.class, cvRequest.getCvSysId());
			if (master != null) {
				response = new GtnWsCustomViewResponse();
				response.setCustomViewName(master.getCustViewName());
				response.setCustomViewType(master.getCustViewType());
				response.setCvSysId(cvRequest.getCvSysId());
				cvRequest.setCustomViewType(master.getCustViewType());
				response.setCvTreeNodeList(getSavedTreeData(cvRequest));

			}
			return response;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return response;
		}
	}

	@SuppressWarnings("rawtypes")
	public List executeQuery(String sqlQuery, List paramList) throws GtnFrameworkGeneralException {
		return gtnSqlQueryEngine.executeSelectQuery(sqlQuery, paramList);
	}

	public String getQuery(String sqlId) {
		return gtnWsSqlService.getQuery(sqlId);
	}

	private void saveCustomViewDetails(int customViewMasterSid, GtnWsCustomViewRequest cvRequest) {
		List<GtnWsRecordBean> cvTreeNodeList = cvRequest.getCvTreeNodeList();
		int masterSid = cvRequest.getCvSysId();

		Transaction tx = null;
		int masterId = masterSid == 0 ? customViewMasterSid : cvRequest.getCvSysId();
		try (Session session = getSessionFactory().openSession()) {
			tx = session.getTransaction();
			if (cvRequest.getCustomViewType().startsWith(REPORT)) {
				if (cvRequest.getCvSysId() != 0) {
					Object[] param = { cvRequest.getCvSysId() };
					GtnFrameworkDataType[] type = { GtnFrameworkDataType.INTEGER };
					gtnSqlQueryEngine.executeInsertOrUpdateQuery(
							gtnWsSqlService.getQuery("getCustomCCPDetailsDeleteQuery"), param, type);

					List<CustViewDetails> details = session.createCriteria(CustViewDetails.class)
							.add(Restrictions.eq(CUSTOM_VIEW_MASTER_SID_DB_PROPERTY, cvRequest.getCvSysId())).list();
					customViewDetailsAndVariablesDeletion(details, session);
				}
			} else if (masterSid != 0) {
				String hql = "delete from CustViewDetails where customViewMasterSid= :classId";
				session.createQuery(hql).setString("classId", String.valueOf(masterSid)).executeUpdate();
			}
			if (cvRequest.getCustomViewType().startsWith(REPORT)) {
				saveReportCustViewDetailsRecords(cvTreeNodeList, masterId, cvRequest.getCustomViewType(), session);
			} else {
				saveCustViewDetailsRecords(cvTreeNodeList, masterId, session);
			}
			commitTrans(tx);
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e.getMessage(), e);
		}
	}

	private void saveCustViewDetailsRecords(List<GtnWsRecordBean> cvTreeNodeList, int customViewMasterSid,
			Session session) {
		CustViewDetails details;
		int i = 1;

		for (Object ob : cvTreeNodeList) {
			GtnWsRecordBean dto = (GtnWsRecordBean) ob;
			String indicator = String.valueOf(dto.getAdditionalPropertyByIndex(2));

			details = new CustViewDetails();
			details.setCustomViewMasterSid(customViewMasterSid);
			details.setHierarchyId((Integer) dto.getAdditionalPropertyByIndex(3));

			details.setHierarchyIndicator(indicator.charAt(0));
			details.setLevelNo(i);
			details.setLevelName(dto.getStringPropertyByIndex(0));
			i++;
			session.save(details);
		}

	}

	private void saveReportCustViewDetailsRecords(List<GtnWsRecordBean> cvTreeNodeList, int customViewMasterSid,
			String variableType, Session session) {

		List<GtnWsRecordBean> variablesList = new ArrayList<>();
		int lastCustomViewMasterSid = 0;
		try {
			int j = 0;
			for (j = 0; j < cvTreeNodeList.size(); j++) {
				CustViewDetails details = new CustViewDetails();
				GtnWsRecordBean dto = cvTreeNodeList.get(j);
				String indicator = dto.getStringPropertyByIndex(3);
				if (variableType.toLowerCase(Locale.ENGLISH).contains("static")
						&& indicator.toLowerCase(Locale.ENGLISH).startsWith("v")) {
					variablesList.add(dto);
				} else if (variableType.toLowerCase(Locale.ENGLISH).contains("expandable")
						&& indicator.toLowerCase(Locale.ENGLISH).startsWith("v")) {
					variablesList
							.addAll(getRecordBeanFromObjectArray((List<List<Object>>) dto.getPropertyValueByIndex(5),
									dto.getAdditionalIntegerPropertyByIndex(0)));
				} else {
					saveVariableData(customViewMasterSid, session, variablesList, lastCustomViewMasterSid);

					details.setCustomViewMasterSid(customViewMasterSid);
					details.setHierarchyId(dto.getIntegerPropertyByIndex(4));
					details.setHierarchyIndicator(indicator.toUpperCase(Locale.ENGLISH).charAt(0));
					details.setLevelName(dto.getStringPropertyByIndex(0));
					details.setLevelNo(dto.getAdditionalIntegerPropertyByIndex(0));
					lastCustomViewMasterSid = (int) session.save(details);
					session.flush();
				}

			}
			saveVariableData(customViewMasterSid, session, variablesList, lastCustomViewMasterSid);

		} catch (HibernateException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void saveVariableData(int customViewMasterSid, Session session, List<GtnWsRecordBean> variablesList,
			int lastCustomViewMasterSid) {
		if (!variablesList.isEmpty()) {
			int levelCountForSaveVariableData = variablesList.get(0).getAdditionalIntegerPropertyByIndex(0);
			CustViewDetails lastLevel = session.load(CustViewDetails.class, lastCustomViewMasterSid);
			lastLevel.setVariableCount(variablesList.size());
			session.update(lastLevel);
			session.flush();
			int customViewDetailsSid = saveCustomDetailsForVariable(customViewMasterSid, session,
					levelCountForSaveVariableData, variablesList);
			insertCustomVariables(variablesList, customViewDetailsSid, session);
		}
	}

	private int saveCustomDetailsForVariable(int customViewMasterSid, Session session, int levelCount,
			List<GtnWsRecordBean> variablesList) {
		CustViewDetails details = new CustViewDetails();
		details.setCustomViewMasterSid(customViewMasterSid);
		details.setHierarchyId(0);
		details.setHierarchyIndicator('V');
		details.setLevelName(GtnWsConstants.VARIABLES);
		details.setVariableCount(variablesList.size());
		details.setLevelNo(levelCount);
		int customViewDetailsSid = (int) session.save(details);
		session.flush();
		return customViewDetailsSid;
	}

	public void insertCustomVariables(List<GtnWsRecordBean> variablesList, int customViewDetailsSid, Session session) {
		for (GtnWsRecordBean gtnWsRecordBean : variablesList) {
			CustomViewVariables variable = new CustomViewVariables();
			variable.setVariableId(gtnWsRecordBean.getIntegerPropertyByIndex(4));
			variable.setCustomViewDetailsSid(customViewDetailsSid);
			variable.setVariableIndicator((char) gtnWsRecordBean.getIntegerPropertyByIndex(2));
			session.save(variable);
			session.flush();
		}
		variablesList.clear();
	}

	public void commitTrans(Transaction tx) {
		if (tx.getStatus().equals(TransactionStatus.ACTIVE)) {
			tx.commit();
		}
	}

	private void customViewSaveLogicCCPDetails(int customViewMasterSid, GtnWsCustomViewRequest cvRequest) {
		int tempCustomViewMasterSid = 0;
		try (Session session = getSessionFactory().openSession()) {
			GtnForecastHierarchyInputBean inputBean = new GtnForecastHierarchyInputBean();
			inputBean.setSelectedCustomerRelationShipBuilderSid(cvRequest.getCustomerRelationshipSid());
			inputBean.setSelectedProductRelationShipBuilderSid(cvRequest.getProductRelationshipSid());

			RelationshipBuilder customerRb = session.get(RelationshipBuilder.class,
					inputBean.getSelectedCustomerRelationShipBuilderSid());
			inputBean.setSelectedCustomerRelationShipBuilderVersionNo(customerRb.getVersionNo());
			inputBean.setSelectedCustomerHierarcySid(customerRb.getHierarchyDefinition().getHierarchyDefinitionSid());
			inputBean.setSelectedCustomerHierarchyVersionNo(customerRb.getHierarchyVersion());

			RelationshipBuilder productRb = session.get(RelationshipBuilder.class,
					inputBean.getSelectedProductRelationShipBuilderSid());
			inputBean.setSelectedProductRelationShipBuilderVersionNo(productRb.getVersionNo());
			inputBean.setSelectedProductHierarcySid(productRb.getHierarchyDefinition().getHierarchyDefinitionSid());
			inputBean.setSelectedProductHierarchyVersionNo(productRb.getHierarchyVersion());
			int masterSid = cvRequest.getCvSysId();
			tempCustomViewMasterSid = (masterSid == 0) ? customViewMasterSid : cvRequest.getCvSysId();
			insertToCCPTable(inputBean, tempCustomViewMasterSid);
		} catch (GtnFrameworkGeneralException ex) {
			logger.error(ex.getMessage());
		}
	}

	public void insertToCCPTable(GtnForecastHierarchyInputBean inputBean, int customViewMasterSid)
			throws GtnFrameworkGeneralException {
		try {
			List<HierarchyLevelDefinitionBean> customerHierarchyLevelDefinitionList = relationUpdateService
					.getHierarchyBuilder(inputBean.getSelectedCustomerHierarcySid(),
							inputBean.getSelectedCustomerHierarchyVersionNo());
			List<HierarchyLevelDefinitionBean> productHierarchyLevelDefinitionList = relationUpdateService
					.getHierarchyBuilder(inputBean.getSelectedProductHierarcySid(),
							inputBean.getSelectedProductHierarchyVersionNo());
			String customerHierarchyQuery = getCustomerAndContractHierarchyQuery(customerHierarchyLevelDefinitionList,
					inputBean.getSelectedCustomerRelationShipBuilderVersionNo(),
					inputBean.getSelectedCustomerRelationShipBuilderSid(), false);
			String productHierarchyQuery = getCustomerAndContractHierarchyQuery(productHierarchyLevelDefinitionList,
					inputBean.getSelectedProductRelationShipBuilderVersionNo(),
					inputBean.getSelectedProductRelationShipBuilderSid(), true);
			List<String> input = new ArrayList<>();
			input.add(customerHierarchyQuery);
			input.add(productHierarchyQuery);
			input.add(String.valueOf(inputBean.getSelectedCustomerRelationShipBuilderSid()));
			input.add(String.valueOf(inputBean.getSelectedProductRelationShipBuilderSid()));
			input.add(String.valueOf(customViewMasterSid));
			input.add(String.valueOf(inputBean.getSelectedCustomerRelationShipBuilderVersionNo()));
			input.add(String.valueOf(inputBean.getSelectedProductRelationShipBuilderVersionNo()));
			String sql = "DELETE FROM CUSTOM_CCP_DETAILS WHERE CUST_VIEW_MASTER_SID= " + customViewMasterSid;
			gtnSqlQueryEngine.executeInsertOrUpdateQuery(sql);
			String withTableNameQuery = gtnWsSqlService.getQuery(input, "ccpInsertQueryCustom");
			gtnSqlQueryEngine.executeInsertOrUpdateQuery(withTableNameQuery);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	private String getCustomerAndContractHierarchyQuery(List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList,
			int relationVersionNo, int relationSid, boolean isProduct) {

		List<Object> input = new ArrayList<>();

		HierarchyLevelDefinitionBean lastLevelDto = HierarchyLevelDefinitionBean
				.getLastLinkedLevel(hierarchyLevelDefinitionList);
		input.add(relationVersionNo);
		input.add(lastLevelDto.getLevelNo());
		input.add(relationSid);

		String beanName = isProduct ? "CCP_INSERT_PRODUCT" : "CCP_INSERT_CUSTOMER";
		GtnFrameworkQueryGeneratorBean queryBean = queryGeneratorService.getQuerybySituationNameAndLevel(lastLevelDto,
				beanName, hierarchyLevelDefinitionList);

		return gtnWsSqlService.getReplacedQuery(input, queryBean.generateQuery());
	}

	@SuppressWarnings("unchecked")
	public GtnUIFrameworkWebserviceComboBoxResponse getCustomViewLevelData(GtnWsCustomViewRequest cvRequest) {
		GtnUIFrameworkWebserviceComboBoxResponse response = new GtnUIFrameworkWebserviceComboBoxResponse();
		try (Session session = getSessionFactory().openSession()) {
			Criteria selectCriteria = session.createCriteria(CustViewDetails.class);
			selectCriteria.add(Restrictions.eq(CUSTOM_VIEW_MASTER_SID_DB_PROPERTY, cvRequest.getCvSysId()));
			List<CustViewDetails> gtnListOfData = selectCriteria.list();
			for (CustViewDetails detailsData : gtnListOfData) {
				response.addItemCodeList(Integer.toString(detailsData.getLevelNo()));
				response.addItemValueList("Level " + detailsData.getLevelNo() + " - "
						+ GtnFrameworkCommonStringConstants.STRING_EMPTY + detailsData.getLevelName());
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	public GtnUIFrameworkWebserviceComboBoxResponse getCustomViewList() {
		GtnUIFrameworkWebserviceComboBoxResponse response = new GtnUIFrameworkWebserviceComboBoxResponse();
		try (Session session = getSessionFactory().openSession()) {
			Criteria selectCriteria = session.createCriteria(CustViewMaster.class);
			selectCriteria.add(Restrictions.like("screenName", REPORT, MatchMode.ANYWHERE));
			List<CustViewMaster> gtnListOfData = selectCriteria.list();
			for (CustViewMaster detailsData : gtnListOfData) {
				response.addItemCodeList(Integer.toString(detailsData.getCustViewMasterSid()));
				response.addItemValueList(detailsData.getCustViewName());
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	public List<GtnWsRecordBean> getSavedTreeData(GtnWsCustomViewRequest cvRequest)
			throws GtnFrameworkGeneralException {
		try (Session session = getSessionFactory().openSession()) {
			Criteria selectCriteria = session.createCriteria(CustViewDetails.class);
			selectCriteria.add(Restrictions.eq(CUSTOM_VIEW_MASTER_SID_DB_PROPERTY, cvRequest.getCvSysId()));
			List<CustViewDetails> gtnListOfData = selectCriteria.list();

			List<GtnWsRecordBean> recordTreeData = new ArrayList<>();
			GtnWsRecordBean gtnWsRecordBean;
			for (CustViewDetails detailsData : gtnListOfData) {

				String levelName = "";
				if (detailsData.getHierarchyId() != 0) {
					levelName = detailsData.getLevelName();
				} else if (cvRequest.getCustomViewType().contains("Expandable")) {
					levelName = GtnWsConstants.VARIABLES;
				} else {
					fetchReportVariables(detailsData, recordTreeData);
					continue;
				}
				gtnWsRecordBean = new GtnWsRecordBean();
				if (cvRequest.getCustomViewType().startsWith(REPORT)) {
					configureReportBean(gtnWsRecordBean, levelName, detailsData.getHierarchyId(), detailsData);
				} else {
					gtnWsRecordBean.addAdditionalProperty(detailsData.getLevelNo());
					gtnWsRecordBean.addAdditionalProperty(detailsData.getLevelNo());
					gtnWsRecordBean.addAdditionalProperty(detailsData.getHierarchyIndicator());
					gtnWsRecordBean.addAdditionalProperty(detailsData.getHierarchyId());
					gtnWsRecordBean.addProperties(levelName);
					gtnWsRecordBean.setRecordHeader(Arrays.asList("levelName"));
				}

				recordTreeData.add(gtnWsRecordBean);
			}
			return recordTreeData;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return Collections.emptyList();
		}
	}

	public void fetchReportVariables(CustViewDetails detailsData, List<GtnWsRecordBean> recordTreeData)
			throws GtnFrameworkGeneralException {
		GtnWsRecordBean gtnWsRecordBean;
		@SuppressWarnings("unchecked")
		List<Object[]> variablesData = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(
				gtnWsSqlService.getQuery("getCustomViewVariables"),
				new Object[] { detailsData.getCustomViewDetailsSid() },
				new GtnFrameworkDataType[] { GtnFrameworkDataType.INTEGER });
		for (Object[] object : variablesData) {
			gtnWsRecordBean = new GtnWsRecordBean();
                        gtnWsRecordBean.addProperties(object[1].toString());
                        gtnWsRecordBean.addProperties(detailsData.getLevelNo());
                        int index = GtnWsReportVariablesType.fromString(object[1].toString()).ordinal();
                        gtnWsRecordBean.addProperties((65+index));
                        gtnWsRecordBean.addProperties(detailsData.getHierarchyIndicator());
                        gtnWsRecordBean.addProperties(Integer.parseInt(object[0].toString()));
                        gtnWsRecordBean.addAdditionalProperty(detailsData.getLevelNo());
                        gtnWsRecordBean.setRecordHeader(Arrays.asList("levelValue"));
			recordTreeData.add(gtnWsRecordBean);
		}
	}

	public void configureReportBean(GtnWsRecordBean gtnWsRecordBean, String levelName, int hierarchyId,
			CustViewDetails detailsData) throws GtnFrameworkGeneralException {
		gtnWsRecordBean.addProperties(levelName);
		gtnWsRecordBean.addProperties(detailsData.getLevelNo());
		gtnWsRecordBean.addProperties(detailsData.getLevelNo());
		gtnWsRecordBean.addProperties(detailsData.getHierarchyIndicator());
		gtnWsRecordBean.addProperties(hierarchyId);
		gtnWsRecordBean.addAdditionalProperty(detailsData.getLevelNo());
		if (levelName.equals(GtnWsConstants.VARIABLES)) {
			@SuppressWarnings("unchecked")
			List<Object[]> variablesData = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(
					gtnWsSqlService.getQuery("getCustomExpandableVariables"),
					new Object[] { detailsData.getCustomViewDetailsSid() },
					new GtnFrameworkDataType[] { GtnFrameworkDataType.INTEGER });
			if (variablesData != null && !variablesData.isEmpty()) {
				List<Object[]> selectedVariablesValues = new ArrayList<>();
				for (int j = 0; j < variablesData.size(); j++) {
					Object[] subDataArray = new Object[6];
					Object[] result = variablesData.get(j);
					int index = GtnWsReportVariablesType.fromString(result[0].toString()).ordinal();
					subDataArray[0] = result[0];
					subDataArray[1] = index;
					subDataArray[2] = (65 + index);
					subDataArray[3] = GtnWsHierarchyType.VARIABLES.toString();
					subDataArray[4] = result[2];
					selectedVariablesValues.add(subDataArray);
				}
				Collections.sort(selectedVariablesValues, (ob1, ob2) -> {
					int object1 = (int) ob1[1];
					int object2 = (int) ob2[1];
					if (object1 > object2) {
						return 1;
					} else if (object1 < object2) {
						return -1;
					} else {
						return 0;
					}
				});
				gtnWsRecordBean.addProperties(selectedVariablesValues);
			}
		}
		gtnWsRecordBean.setRecordHeader(Arrays.asList("levelValue"));
	}

	private void callProcedureForDiscountPopulation(int customViewMasterSid) {
		Session session = getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {

			SQLQuery query = session.createSQLQuery(" EXEC PRC_CUSTOM_CCPD_POPULATION :param ");
			query.setParameter("param", customViewMasterSid);

			query.executeUpdate();
			transaction.commit();
		} catch (Exception ex) {
			transaction.rollback();
			logger.error(ex.getMessage());
		} finally {
			session.close();
		}

	}

	public void deleteCustomViewFromTable(GtnWsCustomViewRequest cvRequest, GtnWsCustomViewResponse cvResponse)
			throws GtnFrameworkGeneralException {
		cvResponse.setSuccess(true);
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			if (checkUsedCustomView(cvRequest.getCvSysId())) {
				cvResponse.setSuccess(false);
				cvResponse.setMessageType(GtnFrameworkCommonStringConstants.DELETE);
				cvResponse.setMessage(
						"Cannot Delete the custom view which is already associated with existing projection.");
				return;
			}
			custViewDelete(cvRequest.getCvSysId());

			tx.commit();
			cvResponse.setMessageType("success");
			cvResponse.setMessage(cvRequest.getCustomViewName() + " has been deleted Successfully.");
		} catch (Exception e) {
			tx.rollback();
			cvResponse.setSuccess(false);
			cvResponse.setMessageType(GtnFrameworkCommonStringConstants.DELETE);
			cvResponse.setMessage(cvRequest.getCustomViewName() + " has not been deleted.");
			logger.error("Exception in deleteCustomView", e);
			throw new GtnFrameworkGeneralException("Exception in deleteCustomView ", e);
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("rawtypes")
	public boolean checkUsedCustomView(final int customViewMasterSid) throws GtnFrameworkGeneralException {
		boolean customViewUsed = false;
		try {
			if (customViewMasterSid != 0) {
				int custCount = 0;
				Object[] cvQueryParams = { customViewMasterSid, customViewMasterSid };
				GtnFrameworkDataType[] cvQueryTypes = { GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER };
				List<Object> resultList = getResultValue("getCustomViewDeleteAlreadyUsed", cvQueryParams, cvQueryTypes);
				if (resultList != null && !resultList.isEmpty()) {
					Object result = resultList.get(0);
					custCount = result == null ? 0 : (Integer) result;
				}
				if (custCount > 0) {
					customViewUsed = true;
				}
			}
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in checkUsedCustomView", e);
		}
		return customViewUsed;
	}

	@SuppressWarnings("unchecked")

	private List<Object> getResultValue(String query, Object[] imtdPsDetailsInsertQueryParams,
			GtnFrameworkDataType[] imtdPsDetailsInsertQueryTypes) throws GtnFrameworkGeneralException {
		String psQuery = gtnWsSqlService.getQuery(query);
		return (List<Object>) gtnSqlQueryEngine.executeSelectQuery(psQuery, imtdPsDetailsInsertQueryParams,
				imtdPsDetailsInsertQueryTypes);

	}

	private List<GtnWsRecordBean> getRecordBeanFromObjectArray(List<List<Object>> object, int variableLevelNo) {
		GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
		for (List<Object> row : object) {
			dataTable.addDataRow(row);
		}
		List<GtnWsRecordBean> recordBeanList = new ArrayList<>();
		for (int i = 0; i < dataTable.getDataTable().size(); i++) {
			GtnWsRecordBean bean = new GtnWsRecordBean();
			bean.setProperties(dataTable.getDataTable().get(i).getColList());
			bean.addAdditionalProperty(variableLevelNo);
			recordBeanList.add(bean);
		}
		return recordBeanList;
	}

	private void custViewDelete(int systemId) throws GtnFrameworkGeneralException {
		String custViewDeleteQuery = gtnWsSqlService.getQuery("getCustomViewDeleteNotUsedInProjection");
		Object[] custViewDeleteQueryParams = { systemId };
		GtnFrameworkDataType[] custViewDeleteQueryTypes = { GtnFrameworkDataType.INTEGER };
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(custViewDeleteQuery, custViewDeleteQueryParams,
				custViewDeleteQueryTypes);
	}
}
