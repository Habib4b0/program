/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.rebateplan.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.config.GtnWsAllListConfig;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.cdr.CdrModel;
import com.stpl.gtn.gtn2o.ws.entity.netsalesformula.NetSalesFormulaMaster;
import com.stpl.gtn.gtn2o.ws.entity.rebateplan.RebatePlanMaster;
import com.stpl.gtn.gtn2o.ws.entity.rebateplan.RebatePlanTier;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.rebateplan.GtnWsRebatePlanInfoBean;
import com.stpl.gtn.gtn2o.ws.rebateplan.GtnWsRebatePlanRuleDetailBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.rebateplan.GtnWsRebatePlanGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.rebateplan.GtnWsRebatePlanGeneralResponse;
import com.stpl.gtn.gtn2o.ws.util.GtnCommonUtil;
import com.stpl.gtn.gtn2o.ws.util.GtnWsConstants;
import com.stpl.gtn.gtn2o.ws.util.GtnWsQueryConstants;

/**
 *
 * @author Mahesh.James
 */
@RestController
@RequestMapping(value = GtnWsCDRContants.RP_SERVICE)
public class GtnWsRebatePlanController {
	public GtnWsRebatePlanController() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsRebatePlanController.class);

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private GtnFrameworkSqlQueryEngine sqlQueryEngine;

	@Autowired
	private GtnWsAllListConfig gtnWebServiceAllListConfig;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@RequestMapping(value = "/" + GtnWsCDRContants.SAVE_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse saveRebatePlan(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter saveRebatePlan");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse gtnGenWsesponse = new GtnWsGeneralResponse();
		gtnGenWsesponse.setSucess(true);
		gtnResponse.setGtnWsGeneralResponse(gtnGenWsesponse);
		try {
			GtnWsRebatePlanGeneralRequest gtnWsRebatePlanGeneralRequest = gtnWsRequest
					.getGtnWsRebatePlanGeneralRequest();
			boolean flag = checkRebatePlanMaster(
					gtnWsRebatePlanGeneralRequest.getRebatePlanInfoBean().getRebatePlanNo());
			GtnWsRebatePlanGeneralResponse getGtnWsRebatePlanGeneralResponse = new GtnWsRebatePlanGeneralResponse();
			getGtnWsRebatePlanGeneralResponse
					.setRebatePlanInfoBean(rebatePlanSave(gtnWsRebatePlanGeneralRequest.getRebatePlanInfoBean(), flag));
			gtnResponse.setGtnWsRebatePlanGeneralResponse(getGtnWsRebatePlanGeneralResponse);

		} catch (Exception ex) {
			gtnGenWsesponse.setSucess(false);
			logger.error("Error in saveRebatePlan", ex);
		}

		logger.info("Exit saveRebatePlan");
		return gtnResponse;
	}

	@RequestMapping(value = "/" + GtnWsCDRContants.GET_RP_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadRebatePlan(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter loadRebatePlan");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsRebatePlanGeneralRequest gtnWsRebatePlanGeneralRequest = gtnWsRequest.getGtnWsRebatePlanGeneralRequest();
		int sysId = gtnWsRebatePlanGeneralRequest.getRebatePlanInfoBean().getSystemId();
		GtnWsGeneralResponse gtnGenWsesponse = new GtnWsGeneralResponse();
		gtnGenWsesponse.setSucess(true);
		gtnResponse.setGtnWsGeneralResponse(gtnGenWsesponse);
		try {
			GtnWsRebatePlanGeneralResponse getGtnWsRebatePlanGeneralResponse = new GtnWsRebatePlanGeneralResponse();
			getGtnWsRebatePlanGeneralResponse.setRebatePlanInfoBean(getRebatePlanInfo(sysId));
			gtnResponse.setGtnWsRebatePlanGeneralResponse(getGtnWsRebatePlanGeneralResponse);
		} catch (Exception ex) {
			gtnGenWsesponse.setSucess(false);
			logger.error("Error in loadRebatePlan", ex);
		}

		logger.info("Exit loadRebatePlan");
		return gtnResponse;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = GtnWsCDRContants.REMOVE_RP_REC, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse removeRuleRecord(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter removeRPRec");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnWsGeneralRequest gtnWsGeneralRequest = gtnWsRequest.getGtnWsGeneralRequest();
			List<Object> inputList = gtnWsGeneralRequest.getComboBoxWhereclauseParamList();
			Map<String, Object> inputMap = (Map<String, Object>) inputList.get(0);

			int sysId = Integer.parseInt(String.valueOf(inputMap.get("sysId")));

			deleteRebatePlanRecord(sysId);
		} catch (GtnFrameworkGeneralException ex) {
			logger.error(ex.getMessage());
		}

		logger.info("Exit removeRPRec");
		return gtnResponse;
	}

	@SuppressWarnings("unchecked")
	private GtnWsRebatePlanInfoBean getRebatePlanInfo(int systemId) throws GtnFrameworkGeneralException {

		logger.info("Enter getRebatePlanInfo");

		RebatePlanMaster rpMaster;
		GtnWsRebatePlanInfoBean rebatePlanInfoBean;
		try (Session session = sessionFactory.openSession()) {
			rpMaster = session.load(RebatePlanMaster.class, systemId);
			Set<RebatePlanTier> rpTierSet = rpMaster.getRebatePlanTiers();

			rebatePlanInfoBean = setRuleInfoBean(rpMaster);
			if (rpTierSet != null) {
				rebatePlanInfoBean.setRebatePlanRuleDetailBean(
						setRpTierDetailsBean(rpTierSet, rebatePlanInfoBean.getFormulaType()));
				rebatePlanInfoBean.setNoteBeanList(getRpNotesTabDetails(systemId));
			}
		} catch (Exception ex) {
			throw new GtnFrameworkGeneralException(ex);
		}

		logger.info("Exit getRebatePlanInfo");
		return rebatePlanInfoBean;
	}

	@SuppressWarnings("unchecked")
	public boolean checkRebatePlanMaster(String sid) throws GtnFrameworkGeneralException {
		List<RebatePlanMaster> results = null;
		try (Session session = sessionFactory.openSession()) {
			Criteria criteria = session.createCriteria(RebatePlanMaster.class)
					.add(Restrictions.eq("rebatePlanNo", sid));
			results = criteria.list();
		} catch (Exception ex) {
			throw new GtnFrameworkGeneralException(ex);
		}
		return results != null && !results.isEmpty();
	}

	private GtnWsRebatePlanInfoBean setRuleInfoBean(RebatePlanMaster rpMaster) throws GtnFrameworkGeneralException {
		GtnWsRebatePlanInfoBean rebatePlanInfoBean = new GtnWsRebatePlanInfoBean();

		rebatePlanInfoBean.setRebatePlanId(String.valueOf(rpMaster.getRebatePlanId()));
		rebatePlanInfoBean.setRebatePlanNo(String.valueOf(rpMaster.getRebatePlanNo()));
		rebatePlanInfoBean.setRebatePlanName(String.valueOf(rpMaster.getRebatePlanName()));
		rebatePlanInfoBean
				.setRebatePlanStatus(getIntValue(rpMaster.getHelperTableByRebatePlanStatus().getHelperTableSid()));
		rebatePlanInfoBean
				.setRebatePlanType(getIntValue(rpMaster.getHelperTableByRebatePlanType().getHelperTableSid()));
		rebatePlanInfoBean
				.setRebateStructure(getIntValue(rpMaster.getHelperTableByRebateStructure().getHelperTableSid()));
		rebatePlanInfoBean
				.setRangeBasedOn(getIntValue(rpMaster.getHelperTableByRebateRangeBasedOn().getHelperTableSid()));
		rebatePlanInfoBean.setRebateBasedOn(getIntValue(rpMaster.getHelperTableByRebateBasedOn().getHelperTableSid()));
		rebatePlanInfoBean
				.setFormulaTypeData(getIntValue(rpMaster.getHelperTableByRebateBasedOn().getHelperTableSid()));

		rebatePlanInfoBean.setFormulaType(rpMaster.getHelperTableByFormulaType() == null ? ""
				: getFormulaTypeData(getIntValue(rpMaster.getHelperTableByFormulaType().getHelperTableSid())));

		rebatePlanInfoBean.setSelfGrowthIndicator(getStringValue(rpMaster.getSelfGrowthIndicator()));
		rebatePlanInfoBean.setSelfGrowthReference(String.valueOf(rpMaster.getSelfGrowthReference()));
		rebatePlanInfoBean.setSelfGrowthFrom(rpMaster.getSelfGrowthFrom());
		rebatePlanInfoBean.setSelfGrowthTo(rpMaster.getSelfGrowthTo());
		rebatePlanInfoBean.setMarketShareIndicator(getStringValue(rpMaster.getMarketShareIndicator()));
		rebatePlanInfoBean.setMarketShareReference(String.valueOf(rpMaster.getMarketShareReference()));
		rebatePlanInfoBean.setMarketShareFrom(rpMaster.getMarketShareFrom());
		rebatePlanInfoBean.setMarketShareTo(rpMaster.getMarketShareTo());

		rebatePlanInfoBean.setNetSalesFormula(rpMaster.getNetSalesFormulaMaster() != null
				? rpMaster.getNetSalesFormulaMaster().getNetSalesFormulaMasterSid()
				: 0);
		rebatePlanInfoBean.setNetSalesFormulaData(rpMaster.getNetSalesFormulaMaster() != null
				? rpMaster.getNetSalesFormulaMaster().getNetSalesFormulaName()
				: "");

		rebatePlanInfoBean
				.setNetSalesRule(rpMaster.getCdrModel() != null ? rpMaster.getCdrModel().getCdrModelSid() : 0);
		rebatePlanInfoBean
				.setNetSalesRuleData(rpMaster.getCdrModel() != null ? rpMaster.getCdrModel().getRuleName() : "");

		rebatePlanInfoBean.setInternalNotes(String.valueOf(rpMaster.getInternalNotes()));

		return rebatePlanInfoBean;

	}

	private int getIntValue(Object obj) {
		int value = 0;
		if (obj != null && !getStringValue(obj).equals("") && !getStringValue(obj).equals(" ")
				&& !getStringValue(obj).equals("null")) {
			value = Integer.parseInt(getStringValue(obj));
		}
		return value;
	}

	private String getStringValue(Object obj) {
		return obj == null ? "" : String.valueOf(obj);
	}

	private List<GtnWsRebatePlanRuleDetailBean> setRpTierDetailsBean(Set<RebatePlanTier> rpTierSet,
			String formulaType) {

		List<GtnWsRebatePlanRuleDetailBean> ruleDetailsBeanList = new ArrayList<>();
		GtnWsRebatePlanRuleDetailBean rPTierDetailBean;
		for (RebatePlanTier rpTier : rpTierSet) {

			rPTierDetailBean = new GtnWsRebatePlanRuleDetailBean();
			rPTierDetailBean.setFromDesc(getDeoubleValue(rpTier.getTierFrom()));
			rPTierDetailBean.setToDesc(getDeoubleValue(rpTier.getTierTo()));
			rPTierDetailBean.setOperatorDesc(getStringValue(rpTier.getHelperTable().getDescription()));
			rPTierDetailBean.setTierToleranceDesc(getDeoubleValue(rpTier.getTierTolerance()));
			rPTierDetailBean.setTierFormulaDesc(getStringValue(rpTier.getFormulaNo()));
			rPTierDetailBean.setTierFormulaNameDesc(getStringValue(rpTier.getFormulaName()));
			rPTierDetailBean.setSecondaryRebatePlanIdDesc(getStringValue(rpTier.getSecondaryRebatePlanNo()));
			rPTierDetailBean.setSecondaryRebatePlanNameDesc(getStringValue(rpTier.getSecondaryRebatePlanName()));
			rPTierDetailBean.setOperator(
					rpTier.getHelperTable() == null ? 0 : getIntValue(rpTier.getHelperTable().getHelperTableSid()));

			rPTierDetailBean.setFrom(rpTier.getTierFrom() == null ? 0d : rpTier.getTierFrom().intValue());
			if (rpTier.getTierTo() != null && "null".equals(String.valueOf(rpTier.getTierTo()))) {
				rPTierDetailBean.setTo(Double.valueOf(String.valueOf(rpTier.getTierTo())));
			}
			rPTierDetailBean
					.setTierTolerance(rpTier.getTierTolerance() == null ? 0d : rpTier.getTierTolerance().doubleValue());
			getSimpleAndComplexFormula(formulaType, rPTierDetailBean, rpTier);
			ruleDetailsBeanList.add(rPTierDetailBean);

		}
		return ruleDetailsBeanList;

	}

	void setRpTierOperatorValue(RebatePlanTier rpTier, GtnWsRebatePlanRuleDetailBean rPTierDetailBean) {
		if ("$".equals(rpTier.getHelperTable().getDescription()) && rpTier.getItemPricingQualifierSid() != null) {
			rPTierDetailBean.setValueDesc(rpTier.getItemPricingQualifierSid());
		} else if (rpTier.getHelperTableReturnRateSid() != null) {
			rPTierDetailBean.setValueDesc(rpTier.getHelperTableReturnRateSid().getDescription());
			rPTierDetailBean.setValue(rpTier.getHelperTableReturnRateSid().getHelperTableSid());
		} else {
			rPTierDetailBean.setValue(rpTier.getTierValue().intValue());
			rPTierDetailBean.setValueDesc(String.valueOf(rpTier.getTierValue()));
		}
	}

	Double getDeoubleValue(Object value) {

		return value == null ? 0d : Double.parseDouble(value.toString());
	}

	@SuppressWarnings("unchecked")
	private List<NotesTabBean> getRpNotesTabDetails(int systemId) throws GtnFrameworkGeneralException {
		logger.info("Enter getRpNotesTabDetails");
		String rpNotesTabDetailsSelectQuery = GtnWsQueryConstants.RP_NOTES_SELECT + systemId;
		List<Object[]> rpNotesDetailsResultList = executeQuery(rpNotesTabDetailsSelectQuery);
		List<NotesTabBean> rpNotesDetailsInfoBeanList = GtnCommonUtil.getNotesTabBean(rpNotesDetailsResultList,
				gtnWebServiceAllListConfig);
		logger.info("Exit getRpNotesTabDetails");
		return rpNotesDetailsInfoBeanList;
	}

	@SuppressWarnings("rawtypes")
	public List executeQuery(String sqlQuery) throws GtnFrameworkGeneralException {

		sqlQueryEngine.setSessionFactory(sessionFactory);
		return sqlQueryEngine.executeSelectQuery(sqlQuery);
	}

	@SuppressWarnings("unchecked")
	private void deleteRebatePlanRecord(int sysId) throws GtnFrameworkGeneralException {

		Session session = getSessionFactory().openSession();
		Transaction transction = session.beginTransaction();
		try {
			RebatePlanMaster rpMaster = null;

			rpMaster = session.load(RebatePlanMaster.class, sysId);
			Set<RebatePlanTier> rptierSet = rpMaster.getRebatePlanTiers();
			for (RebatePlanTier rpTier : rptierSet) {
				session.delete(rpTier);
			}
			session.delete(rpMaster);
			session.createSQLQuery(getnotesTabDeleteQuery(sysId)).executeUpdate();
			transction.commit();
		} catch (Exception ex) {
			transction.rollback();
			logger.error(GtnFrameworkWebserviceConstant.ERROR_WHILE_GETTING_DATA, ex);
			throw new GtnFrameworkGeneralException(GtnFrameworkWebserviceConstant.ERROR_IN_EXECUTING_QUERY, ex);
		} finally {
			session.close();
		}

	}

	@SuppressWarnings("unchecked")
	private GtnWsRebatePlanInfoBean rebatePlanSave(GtnWsRebatePlanInfoBean rebatePlanInfoBean, boolean flag)
			throws GtnFrameworkGeneralException {

		Session session = getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			RebatePlanMaster rpMaster;
			if (rebatePlanInfoBean.getSystemId() != 0) {
				rpMaster = session.load(RebatePlanMaster.class, rebatePlanInfoBean.getSystemId());
				Set<RebatePlanTier> rpTierSet = rpMaster.getRebatePlanTiers();
				for (RebatePlanTier rpTier : rpTierSet) {
					session.delete(rpTier);
				}
				session.flush();
				session.createSQLQuery(getnotesTabDeleteQuery(rebatePlanInfoBean.getSystemId())).executeUpdate();
				buildRebatePlanMaster(rpMaster, rebatePlanInfoBean, session);
				session.update(rpMaster);
				buildRebatePlanTier(rpMaster, rebatePlanInfoBean, session);
				if (rebatePlanInfoBean.getNoteBeanList() != null && !rebatePlanInfoBean.getNoteBeanList().isEmpty()) {
					rpNotesTabInsert(rebatePlanInfoBean, session);
				}
			} else {
				if (!flag) {
					rpMaster = new RebatePlanMaster();
					buildRebatePlanMaster(rpMaster, rebatePlanInfoBean, session);
					rpMaster.setRebatePlanMasterSid((Integer) session.save(rpMaster));
					buildRebatePlanTier(rpMaster, rebatePlanInfoBean, session);
					rebatePlanInfoBean.setSystemId(rpMaster.getRebatePlanMasterSid());
					if (rebatePlanInfoBean.getNoteBeanList() != null
							&& !rebatePlanInfoBean.getNoteBeanList().isEmpty()) {
						rpNotesTabInsert(rebatePlanInfoBean, session);
					}

				}
				rebatePlanInfoBean.setRebatePlanIdAlreadyExist(flag);

			}

			transaction.commit();
		} catch (Exception ex) {
			transaction.rollback();
			logger.error(GtnFrameworkWebserviceConstant.ERROR_WHILE_GETTING_DATA, ex);
			throw new GtnFrameworkGeneralException(GtnFrameworkWebserviceConstant.ERROR_IN_EXECUTING_QUERY, ex);
		} finally {
			session.close();
		}
		return rebatePlanInfoBean;
	}

	private String getnotesTabDeleteQuery(int systemId) {
		return GtnWsQueryConstants.RP_NOTES_DELETE + systemId;
	}

	private void buildRebatePlanMaster(RebatePlanMaster rpMaster, GtnWsRebatePlanInfoBean rebatePlanInfoBean,
			Session session) throws GtnFrameworkGeneralException {

		rpMaster.setRebatePlanId(rebatePlanInfoBean.getRebatePlanId());
		rpMaster.setRebatePlanNo(rebatePlanInfoBean.getRebatePlanNo());
		rpMaster.setRebatePlanName(rebatePlanInfoBean.getRebatePlanName());
		rpMaster.setHelperTableByRebatePlanStatus(
				session.get(HelperTable.class, rebatePlanInfoBean.getRebatePlanStatus()));
		rpMaster.setHelperTableByRebatePlanType(session.get(HelperTable.class, rebatePlanInfoBean.getRebatePlanType()));
		rpMaster.setHelperTableByRebateStructure(
				session.get(HelperTable.class, rebatePlanInfoBean.getRebateStructure()));
		rpMaster.setHelperTableByRebateBasedOn(session.get(HelperTable.class, rebatePlanInfoBean.getRebateBasedOn()));
		rpMaster.setHelperTableByRebateRangeBasedOn(
				session.get(HelperTable.class, rebatePlanInfoBean.getRangeBasedOn()));
		rpMaster.setHelperTableByFormulaType(
				session.get(HelperTable.class, getFormulaType(rebatePlanInfoBean.getFormulaType())));

		if (rebatePlanInfoBean.getSelfGrowthIndicator() != null) {
			rpMaster.setSelfGrowthIndicator(
					getStringValue(rebatePlanInfoBean.getSelfGrowthIndicator() + " ").charAt(0));
		}

		rpMaster.setSelfGrowthReference(rebatePlanInfoBean.getSelfGrowthReference() + "");
		rpMaster.setSelfGrowthFrom(rebatePlanInfoBean.getSelfGrowthFrom());
		rpMaster.setSelfGrowthTo(rebatePlanInfoBean.getSelfGrowthTo());

		if (rebatePlanInfoBean.getMarketShareIndicator() != null) {
			rpMaster.setMarketShareIndicator(
					getStringValue(rebatePlanInfoBean.getMarketShareIndicator() + " ").charAt(0));
		}
		rpMaster.setMarketShareReference(rebatePlanInfoBean.getMarketShareReference() + "");
		rpMaster.setMarketShareFrom(rebatePlanInfoBean.getMarketShareFrom());
		rpMaster.setMarketShareTo(rebatePlanInfoBean.getMarketShareTo());
		rpMaster.setInternalNotes(rebatePlanInfoBean.getInternalNotes());
		rpMaster.setCreatedDate(new Date());
		rpMaster.setCreatedBy(rebatePlanInfoBean.getUserId());
		rpMaster.setModifiedBy(rebatePlanInfoBean.getUserId());
		rpMaster.setModifiedDate(new Date());

		rpMaster.setNetSalesFormulaMaster(rebatePlanInfoBean.getNetSalesFormula() != null
				? getNetSalesFormulaMaster(rebatePlanInfoBean.getNetSalesFormula(), session)
				: null);
		rpMaster.setCdrModel(rebatePlanInfoBean.getNetSalesRule() != null
				? getCdrModel(rebatePlanInfoBean.getNetSalesRule(), session)
				: null);
		if (rebatePlanInfoBean.getSystemId() != 0) {
			rpMaster.setInboundStatus('A');
		} else {
			rpMaster.setInboundStatus('C');

		}

	}

	@SuppressWarnings("unchecked")
	public NetSalesFormulaMaster getNetSalesFormulaMaster(int netSalesFormulaMasterSid, Session session) {
		Criteria criteria = session.createCriteria(NetSalesFormulaMaster.class)
				.add(Restrictions.eq("netSalesFormulaMasterSid", netSalesFormulaMasterSid));
		List<NetSalesFormulaMaster> results = criteria.list();
		if (results != null && !results.isEmpty()) {
			return results.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public CdrModel getCdrModel(int cdrModelSid, Session session) {
		Criteria criteria = session.createCriteria(CdrModel.class).add(Restrictions.eq("cdrModelSid", cdrModelSid));
		List<CdrModel> results = criteria.list();
		if (results != null && !results.isEmpty()) {
			return results.get(0);
		}
		return null;
	}

	private Integer getFormulaType(String formulaType) throws GtnFrameworkGeneralException {
		Object formulaTypeId = 0;
		try {
			String hql = "select helper_table_sid from helper_table where Description = ?";
			Object[] params = { formulaType };
			GtnFrameworkDataType[] dataType = { GtnFrameworkDataType.STRING };
			List<?> result = sqlQueryEngine.executeSelectQuery(hql, params, dataType);
			formulaTypeId = result.get(0);
		} catch (Exception ex) {
			logger.error(GtnFrameworkWebserviceConstant.ERROR_WHILE_GETTING_DATA, ex);
			throw new GtnFrameworkGeneralException(GtnFrameworkWebserviceConstant.ERROR_IN_EXECUTING_QUERY, ex);
		}
		return (Integer) formulaTypeId;
	}

	private String getFormulaTypeData(int formulaType) throws GtnFrameworkGeneralException {
		Object formulaTypeData = null;
		try {
			if (formulaType != 0) {
				String hql = "select Description  from helper_table where helper_table_sid = ?";
				Object[] params = { formulaType };
				GtnFrameworkDataType[] dataType = { GtnFrameworkDataType.INTEGER };
				List<?> result = sqlQueryEngine.executeSelectQuery(hql, params, dataType);
				formulaTypeData = result.get(0);
			}
		} catch (Exception ex) {
			logger.error(GtnFrameworkWebserviceConstant.ERROR_WHILE_GETTING_DATA, ex);
			throw new GtnFrameworkGeneralException(GtnFrameworkWebserviceConstant.ERROR_IN_EXECUTING_QUERY, ex);
		}
		return (String) formulaTypeData;
	}

	private void buildRebatePlanTier(RebatePlanMaster rpMaster, GtnWsRebatePlanInfoBean rebatePlanInfoBean,
			Session session) {
		List<GtnWsRebatePlanRuleDetailBean> rpTierDetailsList = rebatePlanInfoBean.getRebatePlanRuleDetailBean();
		RebatePlanTier rpTier;
		int i = 0;
		for (GtnWsRebatePlanRuleDetailBean ruleDetailBean : rpTierDetailsList) {
			rpTier = new RebatePlanTier();
			rpTier.setRebatePlanMaster(rpMaster);
			rpTier.setRebatePlanTierId(Integer.toString(rebatePlanInfoBean.getSystemId() + i));
			rpTier.setTierFrom(BigDecimal.valueOf(ruleDetailBean.getFrom()));
			rpTier.setTierTo(ruleDetailBean.getTo() != null ? BigDecimal.valueOf(ruleDetailBean.getTo()) : null);
			rpTier.setTierLevel(Integer.toString(i));
			rpTier.setHelperTable(session.get(HelperTable.class, ruleDetailBean.getOperator()));
			rpTier.setTierValue(ruleDetailBean.getValueDesc() != null
					? BigDecimal.valueOf(Double.valueOf(ruleDetailBean.getValueDesc()))
					: BigDecimal.ZERO);
			rpTier.setTierTolerance(
					ruleDetailBean.getTierTolerance() != null ? BigDecimal.valueOf(ruleDetailBean.getTierTolerance())
							: null);

			rpTier.setFormulaNo(ruleDetailBean.getTierFormulaDesc());
			rpTier.setFormulaName(ruleDetailBean.getTierFormulaNameDesc());
			rpTier.setSecondaryRebatePlanNo(ruleDetailBean.getSecondaryRebatePlanIdDesc());
			rpTier.setSecondaryRebatePlanName(ruleDetailBean.getSecondaryRebatePlanNameDesc());
			if (ruleDetailBean.getItemPricingQualifierSid() != null) {
				rpTier.setItemPricingQualifierSid(ruleDetailBean.getItemPricingQualifierSid());
                                rpTier.setFormulaCalculation(ruleDetailBean.getFormulaForCalculation());
			}
			if (ruleDetailBean.getReturnRateSid() != null) {
				rpTier.setHelperTableReturnRateSid(session.get(HelperTable.class, ruleDetailBean.getReturnRateSid()));
			}else if(ruleDetailBean.getItemPricingQualifierSid() !=null && ruleDetailBean.getItemPricingQualifierSid().contains(GtnWsConstants.RETURN_RATE)){
                            try {
                                rpTier.setHelperTableReturnRateSid(session.get(HelperTable.class,getFormulaType(GtnWsConstants.RETURN_RATE)));
                            } catch (GtnFrameworkGeneralException ex) {
                               logger.error(GtnFrameworkWebserviceConstant.ERROR_WHILE_GETTING_DATA, ex);
                            }
                        }
			rpTier.setCreatedDate(new Date());
			rpTier.setCreatedBy(rebatePlanInfoBean.getUserId());
			rpTier.setModifiedDate(new Date());
			rpTier.setModifiedBy(rebatePlanInfoBean.getUserId());
			session.saveOrUpdate(rpTier);
			i++;
		}

	}

	private Integer rpNotesTabInsert(GtnWsRebatePlanInfoBean rebatePlanInfoBean, Session session)
			throws GtnFrameworkGeneralException {
		logger.info("Enter getNotesTabInsertQuery");
		List<NotesTabBean> notesTabRequestList = rebatePlanInfoBean.getNoteBeanList();
		StringBuilder rpNoteTabFinalQuery = new StringBuilder();
		List<GtnFrameworkDataType> rpNotesQueryDataTypeList = new ArrayList<>();
		List<Object> rpNotesQueryParamList = new ArrayList<>();
		for (NotesTabBean notesTabRequest : notesTabRequestList) {
			rpNoteTabFinalQuery.append(GtnWsQueryConstants.NOTES_INSERT);

			rpNotesQueryDataTypeList.add(GtnFrameworkDataType.INTEGER);
			rpNotesQueryParamList.add(rebatePlanInfoBean.getSystemId());

			rpNotesQueryDataTypeList.add(GtnFrameworkDataType.STRING);
			rpNotesQueryParamList.add(notesTabRequest.getMasterTableName());

			rpNotesQueryDataTypeList.add(GtnFrameworkDataType.STRING);
			rpNotesQueryParamList.add(notesTabRequest.getFilePath());

			rpNotesQueryDataTypeList.add(GtnFrameworkDataType.INTEGER);
			rpNotesQueryParamList.add(rebatePlanInfoBean.getUserId());

		}
		if (rpNoteTabFinalQuery.length() > 0) {
			return sqlQueryEngine.executeInsertOrUpdateQuery(rpNoteTabFinalQuery.toString(),
					rpNotesQueryParamList.toArray(),
					rpNotesQueryDataTypeList.toArray(new GtnFrameworkDataType[rpNotesQueryDataTypeList.size()]),
					session);
		}

		logger.info("Exit getNotesTabInsertQuery");
		return 0;
	}

	private void getSimpleAndComplexFormula(String formulaType, GtnWsRebatePlanRuleDetailBean rPTierDetailBean,
			RebatePlanTier rpTier) {

		if ("Complex".equalsIgnoreCase(formulaType)) {
			if (rpTier.getItemPricingQualifierSid() != null) {
				rPTierDetailBean.setValueDesc(rpTier.getItemPricingQualifierSid());
			}
		} else {
			if ("$".equals(rpTier.getHelperTable().getDescription()) && rpTier.getItemPricingQualifierSid() != null) {
				rPTierDetailBean.setValueDesc(rpTier.getItemPricingQualifierSid());
			} else if (rpTier.getHelperTableReturnRateSid() != null) {
				rPTierDetailBean.setValueDesc(rpTier.getHelperTableReturnRateSid().getDescription());
				rPTierDetailBean.setValue(rpTier.getHelperTableReturnRateSid().getHelperTableSid());
			} else {
				rPTierDetailBean.setValue(rpTier.getTierValue().intValue());
				rPTierDetailBean.setValueDesc(String.valueOf(rpTier.getTierValue()));
			}
		}

	}

}