/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.compliancedeductionrule.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkQueryEngineMain;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.GtnWsCDRRuleDetailBean;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.GtnWsCDRRuleInfoBean;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.cdr.CdrDetails;
import com.stpl.gtn.gtn2o.ws.entity.cdr.CdrModel;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.compliancededuction.GtnWsComplianceGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.compliance.GtnWsComplianceGeneralResponse;
import com.stpl.gtn.gtn2o.ws.util.GtnCommonUtil;
import com.stpl.gtn.gtn2o.ws.util.GtnWsQueryConstants;

@RestController
@RequestMapping(value = GtnWsCDRContants.CDR_SERVICE)
public class GtnWsComplianceDeductionAndRulesController {
	public GtnWsComplianceDeductionAndRulesController() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsComplianceDeductionAndRulesController.class);

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private GtnFrameworkQueryEngineMain queryEngineMain;
	@Autowired
	private GtnFrameworkSqlQueryEngine sqlQueryEngine;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public GtnFrameworkQueryEngineMain getQueryEngineMain() {
		return queryEngineMain;
	}

	public void setQueryEngineMain(GtnFrameworkQueryEngineMain queryEngineMain) {
		this.queryEngineMain = queryEngineMain;
	}

	@RequestMapping(value = "/" + GtnWsCDRContants.SAVE_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse saveService(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter saveService");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();

		GtnWsGeneralResponse gtnGenWsesponse = new GtnWsGeneralResponse();
		gtnGenWsesponse.setSucess(true);

		gtnResponse.setGtnWsGeneralResponse(gtnGenWsesponse);

		try {
			GtnWsComplianceGeneralRequest gtnWsComplianceGeneralRequest = gtnWsRequest
					.getGtnWsComplianceGeneralRequest();
			cDRSave(gtnWsComplianceGeneralRequest.getRuleInfoBean());

		} catch (Exception ex) {
			gtnGenWsesponse.setSucess(false);
			logger.error("Error in saveService", ex);
		}

		logger.info("Exit saveService");
		return gtnResponse;
	}

	@RequestMapping(value = "/" + GtnWsCDRContants.GET_CDR_RULE_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadCDR(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter loadCDR");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsComplianceGeneralRequest gtnWsComplianceGeneralRequest = gtnWsRequest.getGtnWsComplianceGeneralRequest();
		int sysId = gtnWsComplianceGeneralRequest.getRuleInfoBean().getSystemId();
		GtnWsGeneralResponse gtnGenWsesponse = new GtnWsGeneralResponse();
		gtnGenWsesponse.setSucess(true);
		gtnResponse.setGtnWsGeneralResponse(gtnGenWsesponse);
		try {
			GtnWsComplianceGeneralResponse getGtnWsComplianceGeneralResponse = new GtnWsComplianceGeneralResponse();
			getGtnWsComplianceGeneralResponse.setRuleInfoBean(getCDRInfo(sysId));
			gtnResponse.setGtnWsComplianceGeneralResponse(getGtnWsComplianceGeneralResponse);
		} catch (Exception ex) {
			gtnGenWsesponse.setSucess(false);
			logger.error("Error in loadCDR", ex);
		}

		logger.info("Exit loadCDR");
		return gtnResponse;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/" + GtnWsCDRContants.IS_RULE_EXIST_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse isRuleExist(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnWsGeneralRequest gtnWsGeneralRequest = gtnWsRequest.getGtnWsGeneralRequest();
			List<Object> inputList = gtnWsGeneralRequest.getComboBoxWhereclauseParamList();
			Map<String, Object> inputMap = (Map<String, Object>) inputList.get(0);
			logger.info("Enter isRuleExist");
			String selectInfoQuery = GtnWsQueryConstants.CDR_RULE_EXIST + "'" + inputMap.get("ruleName")
					+ "'  OR RULE_NO='" + inputMap.get("ruleNo") + "'";

			List<Integer> list = executeQuery(selectInfoQuery);
			gtnResponse.setOutBountData(new Object[] { list.get(0) });

		} catch (GtnFrameworkGeneralException ex) {
			logger.error(ex.getMessage());
		}

		logger.info("Exit isRuleExist");
		return gtnResponse;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/" + GtnWsCDRContants.REMOVE_RULE_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse removeRuleRecord(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnWsGeneralRequest gtnWsGeneralRequest = gtnWsRequest.getGtnWsGeneralRequest();
			List<Object> inputList = gtnWsGeneralRequest.getComboBoxWhereclauseParamList();
			Map<String, Object> inputMap = (Map<String, Object>) inputList.get(0);
			logger.info("Enter removeRuleRec");
			int sysId = Integer.parseInt(String.valueOf(inputMap.get("sysId")));
			deleteCDRRule(sysId);
		} catch (GtnFrameworkGeneralException ex) {
			logger.error(ex.getMessage());
		}

		logger.info("Exit removeRuleRec");
		return gtnResponse;
	}

	@SuppressWarnings("unchecked")
	private GtnWsCDRRuleInfoBean getCDRInfo(int systemId) throws GtnFrameworkGeneralException {

		logger.info("Enter getCDRInfo");
		GtnWsCDRRuleInfoBean ruleInfoBean = new GtnWsCDRRuleInfoBean();
		try (Session session = getSessionFactory().openSession()) {
			CdrModel cdrModel = null;

			cdrModel = session.load(CdrModel.class, systemId);
			Set<CdrDetails> cdrDetailsSet = cdrModel.getCdrDetailses();

			ruleInfoBean = setRuleInfoBean(cdrModel);
			if (cdrDetailsSet != null) {
				ruleInfoBean.setRuleDetailBeanList(setRuleDetailsBean(cdrDetailsSet));
				ruleInfoBean.setNoteBeanList(getCdrNotesTabDetails(systemId));

			}

		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		logger.info("Exit getCDRInfo");
		return ruleInfoBean;
	}

	private GtnWsCDRRuleInfoBean setRuleInfoBean(CdrModel cdrModel) {
		GtnWsCDRRuleInfoBean ruleInfoBean = new GtnWsCDRRuleInfoBean();
		ruleInfoBean.setRuleType(cdrModel.getRuleType());
		ruleInfoBean.setRuleNo(cdrModel.getRuleNo());
		ruleInfoBean.setRuleName(cdrModel.getRuleName());
		ruleInfoBean.setRuleCategory(cdrModel.getRuleCategory());
		ruleInfoBean.setInternalNotes(cdrModel.getInternalNotes());

		return ruleInfoBean;

	}

	private List<GtnWsCDRRuleDetailBean> setRuleDetailsBean(Set<CdrDetails> cdrDetailsSet) {
		List<GtnWsCDRRuleDetailBean> ruleDetailsBeanList = new ArrayList<>();
		GtnWsCDRRuleDetailBean ruleDetailBean;
		for (CdrDetails cdrDetails : cdrDetailsSet) {
			ruleDetailBean = new GtnWsCDRRuleDetailBean();
			ruleDetailBean.setLineTypeDesc(cdrDetails.getHelperTableByLineType().getDescription());
			ruleDetailBean
					.setItemGMSAssociationDesc(cdrDetails.getHelperTableByItemGroupMsAssociation().getDescription());
			ruleDetailBean.setKeywordDesc(cdrDetails.getHelperTableByKeyword().getDescription());
			ruleDetailBean.setOperatorDesc(cdrDetails.getHelperTableByOperator().getDescription());
			ruleDetailBean.setValueDesc(cdrDetails.getValue().toPlainString());
			ruleDetailBean.setComparisionDesc(cdrDetails.getHelperTableByComparison().getDescription());
			ruleDetailBean.setLogicaloperaorDesc(cdrDetails.getHelperTableByLogicalOperator().getDescription());

			ruleDetailBean.setLineType(cdrDetails.getHelperTableByLineType().getHelperTableSid());
			ruleDetailBean
					.setItemGMSAssociation(cdrDetails.getHelperTableByItemGroupMsAssociation().getHelperTableSid());
			ruleDetailBean.setKeyword(cdrDetails.getHelperTableByKeyword().getHelperTableSid());
			ruleDetailBean.setOperator(cdrDetails.getHelperTableByOperator().getHelperTableSid());
			ruleDetailBean.setValue(cdrDetails.getValue().intValue());
			ruleDetailBean.setComparision(cdrDetails.getHelperTableByComparison().getHelperTableSid());
			ruleDetailBean.setLogicaloperaor(cdrDetails.getHelperTableByLogicalOperator().getHelperTableSid());
			ruleDetailsBeanList.add(ruleDetailBean);
		}
		return ruleDetailsBeanList;

	}

	@SuppressWarnings("unchecked")
	private List<NotesTabBean> getCdrNotesTabDetails(int systemId) throws GtnFrameworkGeneralException {
		logger.info("Enter getCdrNotesTabDetails");
		String cdrNotesTabDetailsSelectQuery = GtnWsQueryConstants.CDR_NOTES_SELECT + "'" + systemId + "'";
		List<Object[]> cdrNotesDetailsResultList = executeQuery(cdrNotesTabDetailsSelectQuery);
		List<NotesTabBean> cdrNotesDetailsInfoBeanList = GtnCommonUtil.getNotesTabBean(cdrNotesDetailsResultList, null);
		logger.info("Exit getCdrNotesTabDetails");
		return cdrNotesDetailsInfoBeanList;
	}

	@SuppressWarnings({ "rawtypes" })
	public List executeQuery(String sqlQuery) throws GtnFrameworkGeneralException {

		sqlQueryEngine.setSessionFactory(sessionFactory);
		return sqlQueryEngine.executeSelectQuery(sqlQuery);
	}

	private void deleteCDRRule(int sysId) throws GtnFrameworkGeneralException {

		Session session = getSessionFactory().openSession();
		Transaction transction = session.beginTransaction();
		try {
			CdrModel cdrModel = null;

			cdrModel = session.load(CdrModel.class, sysId);
			@SuppressWarnings("unchecked")
			Set<CdrDetails> cdrDetailsSet = cdrModel.getCdrDetailses();
			for (CdrDetails cdrDetail : cdrDetailsSet) {
				session.delete(cdrDetail);

			}

			session.delete(cdrModel);

			session.createSQLQuery(getnotesTabDeleteQuery(sysId)).executeUpdate();
			transction.commit();
		} catch (Exception ex) {
			transction.rollback();
			logger.error("Error while getting data.", ex);
			throw new GtnFrameworkGeneralException("Error in executing query : ", ex);
		} finally {
			session.close();
		}

	}

	@SuppressWarnings("unchecked")
	private boolean cDRSave(GtnWsCDRRuleInfoBean rulesInfoBean) throws GtnFrameworkGeneralException {

		Session session = getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			CdrModel cdrModel = null;
			if (rulesInfoBean.getSystemId() != 0) {

				cdrModel = session.load(CdrModel.class, rulesInfoBean.getSystemId());
				Set<CdrDetails> cdrDetailsSet = cdrModel.getCdrDetailses();
				for (CdrDetails cdrDetail : cdrDetailsSet) {
					session.delete(cdrDetail);

				}

				session.createSQLQuery(getnotesTabDeleteQuery(rulesInfoBean.getSystemId())).executeUpdate();
				buildCDRModel(rulesInfoBean, cdrModel);
				session.update(cdrModel);
			} else {
				cdrModel = new CdrModel();
				buildCDRModel(rulesInfoBean, cdrModel);
				cdrModel.setCdrModelSid((Integer) session.save(cdrModel));
				rulesInfoBean.setSystemId(cdrModel.getCdrModelSid());

			}

			buildCDRDetails(rulesInfoBean, cdrModel, session);

			if (rulesInfoBean.getNoteBeanList() != null && !rulesInfoBean.getNoteBeanList().isEmpty()) {
				cdrNotesTabInsert(rulesInfoBean, session);
			}
			transaction.commit();
		} catch (Exception ex) {

			transaction.rollback();
			logger.error("Error while getting data.", ex);
			throw new GtnFrameworkGeneralException("Error in executing query : ", ex);
		} finally {
			session.close();
		}

		return true;

	}

	private String getnotesTabDeleteQuery(int systemId) {
		return GtnWsQueryConstants.CDR_NOTES_DELETE1 + systemId + GtnWsQueryConstants.CDR_NOTES_DELETE2;
	}

	private void buildCDRModel(GtnWsCDRRuleInfoBean ruleInfoBean, CdrModel cdr) {

		cdr.setRuleType(ruleInfoBean.getRuleType());
		cdr.setRuleNo(ruleInfoBean.getRuleNo());
		cdr.setRuleName(ruleInfoBean.getRuleName());
		cdr.setRuleCategory(ruleInfoBean.getRuleCategory());
		cdr.setCreatedDate(new Date());
		cdr.setModifiedDate(new Date());
		cdr.setCreatedBy(ruleInfoBean.getUserId());
		cdr.setModifiedBy(ruleInfoBean.getUserId());
		cdr.setInternalNotes(ruleInfoBean.getInternalNotes());

	}

	private void buildCDRDetails(GtnWsCDRRuleInfoBean ruleInfoBean, CdrModel cdr, Session session) {
		logger.info("Enter getRuleDetailBeanInfo");
		List<GtnWsCDRRuleDetailBean> ruleDetailBeanList = ruleInfoBean.getRuleDetailBeanList();

		CdrDetails cdrDetrail = null;

		for (GtnWsCDRRuleDetailBean cdrDetailBean : ruleDetailBeanList) {

			cdrDetrail = new CdrDetails();

			cdrDetrail.setHelperTableByLineType(session.get(HelperTable.class, cdrDetailBean.getLineType()));
			cdrDetrail.setHelperTableByItemGroupMsAssociation(
					session.get(HelperTable.class, cdrDetailBean.getItemGMSAssociation()));
			cdrDetrail.setHelperTableByKeyword(session.get(HelperTable.class, cdrDetailBean.getKeyword()));
			cdrDetrail.setHelperTableByOperator(session.get(HelperTable.class, cdrDetailBean.getOperator()));
			cdrDetrail.setValue(BigDecimal.valueOf(cdrDetailBean.getValue()));
			cdrDetrail.setHelperTableByComparison(session.get(HelperTable.class, cdrDetailBean.getComparision()));
			cdrDetrail
					.setHelperTableByLogicalOperator(session.get(HelperTable.class, cdrDetailBean.getLogicaloperaor()));
			cdrDetrail.setCreatedDate(new Date());
			cdrDetrail.setModifiedDate(new Date());
			cdrDetrail.setCreatedBy(ruleInfoBean.getUserId());
			cdrDetrail.setModifiedBy(ruleInfoBean.getUserId());
			cdrDetrail.setCdrModel(cdr);
			session.saveOrUpdate(cdrDetrail);
		}

	}

	private Integer cdrNotesTabInsert(GtnWsCDRRuleInfoBean ruleInfoBean, Session session)
			throws GtnFrameworkGeneralException {
		logger.info("Enter getNotesTabInsertQuery");
		List<NotesTabBean> notesTabRequestList = ruleInfoBean.getNoteBeanList();
		StringBuilder cdrNoteTabFinalQuery = new StringBuilder();
		List<GtnFrameworkDataType> cdrNotesQueryDataTypeList = new ArrayList<>();
		List<Object> cdrNotesQueryParamList = new ArrayList<>();
		for (NotesTabBean notesTabRequest : notesTabRequestList) {
			cdrNoteTabFinalQuery.append(GtnWsQueryConstants.NOTES_INSERT);

			cdrNotesQueryDataTypeList.add(GtnFrameworkDataType.INTEGER);
			cdrNotesQueryParamList.add(ruleInfoBean.getSystemId());

			cdrNotesQueryDataTypeList.add(GtnFrameworkDataType.STRING);
			cdrNotesQueryParamList.add(notesTabRequest.getMasterTableName());
			
			cdrNotesQueryDataTypeList.add(GtnFrameworkDataType.STRING);
			cdrNotesQueryParamList.add(notesTabRequest.getFilePath());
			
			cdrNotesQueryDataTypeList.add(GtnFrameworkDataType.INTEGER);
			cdrNotesQueryParamList.add(ruleInfoBean.getUserId());
		}
		if (cdrNoteTabFinalQuery.length() > 0) {
			return sqlQueryEngine.executeInsertOrUpdateQuery(cdrNoteTabFinalQuery.toString(),
					cdrNotesQueryParamList.toArray(),
					cdrNotesQueryDataTypeList.toArray(new GtnFrameworkDataType[cdrNotesQueryDataTypeList.size()]),
					session);
		}

		logger.info("Exit getNotesTabInsertQuery");
		return 0;
	}

}
