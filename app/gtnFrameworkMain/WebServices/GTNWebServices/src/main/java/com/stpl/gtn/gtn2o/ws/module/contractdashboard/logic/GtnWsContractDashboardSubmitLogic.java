/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.contractdashboard.logic;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractDashboardProcessBean;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;
import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.companymaster.CompanyMaster;
import com.stpl.gtn.gtn2o.ws.entity.companymaster.Udcs;
import com.stpl.gtn.gtn2o.ws.entity.contract.CfpContract;
import com.stpl.gtn.gtn2o.ws.entity.contract.ContractAliasMaster;
import com.stpl.gtn.gtn2o.ws.entity.contract.ContractMaster;
import com.stpl.gtn.gtn2o.ws.entity.contract.IfpContract;
import com.stpl.gtn.gtn2o.ws.entity.contract.ImtdCfpDetails;
import com.stpl.gtn.gtn2o.ws.entity.contract.ImtdItemPriceRebateDetails;
import com.stpl.gtn.gtn2o.ws.entity.contract.PsContract;
import com.stpl.gtn.gtn2o.ws.entity.contract.RsContract;
import com.stpl.gtn.gtn2o.ws.entity.contract.RsContractDetails;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.contractdashboard.controller.GtnWsContractDashboardController;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractDashboardRequest;
import com.stpl.gtn.gtn2o.ws.response.contract.GtnWsContractDashboardResponse;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnWsContractDashboardSubmitLogic {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsContractDashboardSubmitLogic.class);
	private final GtnWsContractDashboardController controller;

	public GtnWsContractDashboardSubmitLogic(GtnWsContractDashboardController controller) {
		this.controller = controller;
	}

	public GtnWsContractDashboardController getController() {
		return controller;
	}

	public String getQuery(String queryName) {
		return getController().getQuery(queryName);
	}

	public boolean checkCfpAdded(GtnWsContractDashboardRequest cdRequest) throws GtnFrameworkGeneralException {
		int count = 0;
		try (Session session = getController().getSessionFactory().openSession()) {
			Criteria cr = session.createCriteria(ImtdCfpDetails.class)
					.add(Restrictions.eq(GtnFrameworkWebserviceConstant.CHECK_RECORD, true))
					.add(Restrictions.eq(GtnFrameworkWebserviceConstant.USERS_SID, cdRequest.getUserId()))
					.add(Restrictions.eq(GtnFrameworkCommonConstants.SESSION_ID, cdRequest.getSessionId()))
					.add(Restrictions.ne(GtnFrameworkWebserviceConstant.OPERATION, "D"))
					.setProjection(Projections.countDistinct(GtnFrameworkWebserviceConstant.IMTD_CFP_DETAILS_SID));
			List<?> results = cr.list();
			count = Integer.valueOf(results.get(0).toString());
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in checkCfpAdded ", e);
		}
		return count != 0;
	}

	public boolean checkCfpTableNullValue(GtnWsContractDashboardRequest cdRequest, String field)
			throws GtnFrameworkGeneralException {
		int count = 0;
		try (Session session = getController().getSessionFactory().openSession()) {

			Criteria cr = session.createCriteria(ImtdCfpDetails.class)
					.add(Restrictions.eq(GtnFrameworkWebserviceConstant.CHECK_RECORD, true))
					.add(Restrictions.eq(GtnFrameworkWebserviceConstant.USERS_SID, cdRequest.getUserId()))
					.add(Restrictions.eq(GtnFrameworkCommonConstants.SESSION_ID, cdRequest.getSessionId()))
					.add(Restrictions.ne(GtnFrameworkWebserviceConstant.OPERATION, "D")).add(Restrictions.isNull(field))
					.setProjection(Projections.countDistinct(GtnFrameworkWebserviceConstant.IMTD_CFP_DETAILS_SID));
			List<?> results = cr.list();
			count = Integer.valueOf(results.get(0).toString());
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in checkCfpTableNullValue ", e);
		}
		return count != 0;
	}

	public boolean checkCfpTableDateNullValue(GtnWsContractDashboardRequest cdRequest, String field)
			throws GtnFrameworkGeneralException {
		int count = 0;
		try (Session session = getController().getSessionFactory().openSession()) {
			Criteria cr = session.createCriteria(ImtdCfpDetails.class)
					.add(Restrictions.eq(GtnFrameworkWebserviceConstant.CHECK_RECORD, true))
					.add(Restrictions.eq(GtnFrameworkWebserviceConstant.USERS_SID, cdRequest.getUserId()))
					.add(Restrictions.eq(GtnFrameworkCommonConstants.SESSION_ID, cdRequest.getSessionId()))
					.add(Restrictions.ne(GtnFrameworkWebserviceConstant.OPERATION, "D")).add(Restrictions.isNull(field))
					.setProjection(Projections.countDistinct(GtnFrameworkWebserviceConstant.IMTD_CFP_DETAILS_SID));
			List<?> results = cr.list();
			count = Integer.valueOf(results.get(0).toString());
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in checkCfpTableDateNullValue ", e);
		}
		return count != 0;
	}

	public boolean checkCfpTableEndDate(GtnWsContractDashboardRequest cdRequest) throws GtnFrameworkGeneralException {
		int count = 0;
		try (Session session = getController().getSessionFactory().openSession()) {
			Criteria cr = session.createCriteria(ImtdCfpDetails.class)
					.add(Restrictions.eq(GtnFrameworkWebserviceConstant.CHECK_RECORD, true))
					.add(Restrictions.eq(GtnFrameworkWebserviceConstant.USERS_SID, cdRequest.getUserId()))
					.add(Restrictions.eq(GtnFrameworkCommonConstants.SESSION_ID, cdRequest.getSessionId()))
					.add(Restrictions.ne(GtnFrameworkWebserviceConstant.OPERATION, "D"))
					.add(Restrictions.isNotNull("cfpDetailsEndDate"))
					.add(Restrictions.leProperty("cfpDetailsEndDate", "cfpDetailsStartDate"))
					.setProjection(Projections.countDistinct(GtnFrameworkWebserviceConstant.IMTD_CFP_DETAILS_SID));
			List<?> results = cr.list();
			count = Integer.valueOf(results.get(0).toString());
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in checkCfpTableEndDate ", e);
		}
		return count != 0;
	}

	public boolean checkIfpAdded(GtnWsContractDashboardRequest cdRequest) throws GtnFrameworkGeneralException {
		int count = 0;
		try (Session session = getController().getSessionFactory().openSession()) {
			Criteria cr = session.createCriteria(ImtdItemPriceRebateDetails.class)
					.add(Restrictions.eq(GtnFrameworkWebserviceConstant.CHECK_RECORD, true))
					.add(Restrictions.eq(GtnFrameworkWebserviceConstant.USERS_SID,
							Integer.valueOf(cdRequest.getUserId())))
					.add(Restrictions.eq(GtnFrameworkCommonConstants.SESSION_ID, cdRequest.getSessionId()))
					.add(Restrictions.ne(GtnFrameworkWebserviceConstant.OPERATION, "D")).setProjection(
							Projections.countDistinct(GtnFrameworkWebserviceConstant.IMTD_ITEM_PRICE_REBATE_SID));
			List<?> results = cr.list();
			count = Integer.valueOf(results.get(0).toString());
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in checkIfpAdded ", e);
		}
		return count != 0;
	}

	public boolean checkIfpTableNullValue(GtnWsContractDashboardRequest cdRequest, String field)
			throws GtnFrameworkGeneralException {
		int count = 0;
		try (Session session = getController().getSessionFactory().openSession()) {
			Criteria cr = session.createCriteria(ImtdItemPriceRebateDetails.class)
					.add(Restrictions.eq(GtnFrameworkWebserviceConstant.CHECK_RECORD, true))
					.add(Restrictions.eq(GtnFrameworkWebserviceConstant.USERS_SID,
							Integer.valueOf(cdRequest.getUserId())))
					.add(Restrictions.eq(GtnFrameworkCommonConstants.SESSION_ID, cdRequest.getSessionId()))
					.add(Restrictions.ne(GtnFrameworkWebserviceConstant.OPERATION, "D")).add(Restrictions.isNull(field))
					.setProjection(
							Projections.countDistinct(GtnFrameworkWebserviceConstant.IMTD_ITEM_PRICE_REBATE_SID));
			List<?> results = cr.list();
			count = Integer.valueOf(results.get(0).toString());
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException(
					GtnFrameworkWebserviceConstant.EXCEPTION_IN_CHECK_IFP_TABLE_NULL_VALUE, e);
		}
		return count != 0;
	}

	public boolean checkIfpTableDateNullValue(GtnWsContractDashboardRequest cdRequest, String field)
			throws GtnFrameworkGeneralException {
		int count = 0;
		try (Session session = getController().getSessionFactory().openSession()) {
			Criteria cr = session.createCriteria(ImtdItemPriceRebateDetails.class)
					.add(Restrictions.eq(GtnFrameworkWebserviceConstant.CHECK_RECORD, true))
					.add(Restrictions.eq(GtnFrameworkWebserviceConstant.USERS_SID,
							Integer.valueOf(cdRequest.getUserId())))
					.add(Restrictions.eq(GtnFrameworkCommonConstants.SESSION_ID, cdRequest.getSessionId()))
					.add(Restrictions.ne(GtnFrameworkWebserviceConstant.OPERATION, "D")).add(Restrictions.isNull(field))
					.setProjection(
							Projections.countDistinct(GtnFrameworkWebserviceConstant.IMTD_ITEM_PRICE_REBATE_SID));
			List<?> results = cr.list();
			count = Integer.valueOf(results.get(0).toString());
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in checkIfpTableDateNullValue ", e);
		}
		return count != 0;
	}

	public boolean checkIfpTableEndDate(GtnWsContractDashboardRequest cdRequest, String startDateField,
			String endDateField) throws GtnFrameworkGeneralException {
		int count = 0;
		try (Session session = getController().getSessionFactory().openSession()) {
			Criteria cr = session.createCriteria(ImtdItemPriceRebateDetails.class)
					.add(Restrictions.eq(GtnFrameworkWebserviceConstant.CHECK_RECORD, true))
					.add(Restrictions.eq(GtnFrameworkWebserviceConstant.USERS_SID,
							Integer.valueOf(cdRequest.getUserId())))
					.add(Restrictions.eq(GtnFrameworkCommonConstants.SESSION_ID, cdRequest.getSessionId()))
					.add(Restrictions.ne(GtnFrameworkWebserviceConstant.OPERATION, "D"))
					.add(Restrictions.isNotNull(endDateField))
					.add(Restrictions.leProperty(endDateField, startDateField)).setProjection(
							Projections.countDistinct(GtnFrameworkWebserviceConstant.IMTD_ITEM_PRICE_REBATE_SID));
			List<?> results = cr.list();
			count = Integer.valueOf(results.get(0).toString());
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in checkIfpTableEndDate ", e);
		}
		return count != 0;
	}

	public void saveContractDashboard(GtnWsContractDashboardRequest cdRequest,
			GtnWsContractDashboardResponse cdResponse, GtnWsGeneralRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		cdResponse.setSuccess(true);
		Session session = getController().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			saveAllContractDashboardInfo(session, cdRequest, gtnWsRequest);
			tx.commit();
			cdResponse.setMessageType("success");
			cdResponse.setMessageHeader("Submitted Information");
			cdResponse.setMessage("Workflow Id [WORKFLOW_ID] Submitted successfully");
		} catch (Exception e) {
			tx.rollback();
			cdResponse.setSuccess(false);
			cdResponse.setMessageType(GtnFrameworkCommonStringConstants.ERROR);
			cdResponse.setMessageHeader(GtnFrameworkCommonStringConstants.ERROR);
			cdResponse.setMessage(GtnWsContractDashboardContants.SUBMIT_FAIL_MESSAGE);
			logger.error("Exception in saveContractDashboard", e);
			throw new GtnFrameworkGeneralException("Exception in saveContractDashboard ", e);
		} finally {
			session.close();
		}
	}

	private void saveAllContractDashboardInfo(Session session, GtnWsContractDashboardRequest cdRequest,
			GtnWsGeneralRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		try {
			GtnWsContractDashboardProcessBean processBean = cdRequest.getContractDashboardBean().getProcessBean();
			updateContractInfo(session, cdRequest);
			if (processBean.getCfpContractId() != 0) {
				saveCFPInfo(session, cdRequest, gtnWsRequest);
			}
			if (processBean.getIfpContractId() != 0) {
				saveIFPInfo(session, cdRequest, gtnWsRequest);
			}
			if (processBean.getPsContractId() != 0) {
				savePsInfo(session, cdRequest, gtnWsRequest);
			}
			if (processBean.getRsContractId() != 0) {
				saveRsInfo(session, cdRequest, gtnWsRequest);
			}
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in save AllContractDashboardInfo", e);
		}
	}

	public Date getConvertedDateValue(Object value) {
		Object dateValue = value;
		if (dateValue != null && Long.class.isAssignableFrom(dateValue.getClass())) {
			dateValue = new Date((Long) dateValue);
		}
		return (Date) dateValue;
	}

	private void updateContractInfo(Session session, GtnWsContractDashboardRequest cdRequest)
			throws GtnFrameworkGeneralException {
		try {
			GtnWsRecordBean bean = cdRequest.getContractDashboardBean().getContractInfoBean();
			ContractMaster contractMaster = session.load(ContractMaster.class,
					cdRequest.getContractDashboardBean().getProcessBean().getContractId());
			contractMaster.setContractId(bean.getStringPropertyByIndex(0));
			contractMaster.setContractNo(bean.getStringPropertyByIndex(1));
			contractMaster.setContractName(bean.getStringPropertyByIndex(2));
			contractMaster
					.setHelperTableByContractType(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(3)));
			contractMaster
					.setHelperTableByContractStatus(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(4)));
			contractMaster
					.setHelperTableByDocumentType(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(5)));
			contractMaster.setStartDate(getConvertedDateValue(bean.getPropertyValueByIndex(6)));
			contractMaster.setEndDate(getConvertedDateValue(bean.getPropertyValueByIndex(7)));
			contractMaster
					.setHelperTableByDocumentClass(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(8)));
			int companyId = bean.getIntegerPropertyByIndex(49);
			if (companyId != 0) {
				contractMaster.setCompanyMasterByBunitCompanyMasterSid(session.load(CompanyMaster.class, companyId));
			}
			int tpId = bean.getIntegerPropertyByIndex(50);
			if (tpId != 0) {
				contractMaster.setCompanyMasterByContHoldCompanyMasterSid(session.load(CompanyMaster.class, tpId));
			}
			contractMaster.setHelperTableByContractTradeClass(
					session.load(HelperTable.class, bean.getIntegerPropertyByIndex(10)));
			contractMaster.setTerm(bean.getIntegerPropertyByIndex(11));
			contractMaster.setRenegotiationStartDate(getConvertedDateValue(bean.getPropertyValueByIndex(13)));
			contractMaster.setRenegotiationEndDate(getConvertedDateValue(bean.getPropertyValueByIndex(14)));
			contractMaster.setPriceprotectionStartDate(getConvertedDateValue(bean.getPropertyValueByIndex(15)));
			contractMaster.setPriceprotectionEndDate(getConvertedDateValue(bean.getPropertyValueByIndex(16)));
			int manuFactureId = bean.getIntegerPropertyByIndex(17);
			if (manuFactureId != 0) {
				contractMaster.setCompanyMasterByManfCompanyMasterSid(session.load(CompanyMaster.class, manuFactureId));
			}
                        contractMaster.setContractEligibleDate(bean.getPropertyValueByIndex(18) != null ? getConvertedDateValue(bean.getPropertyValueByIndex(18)) : null);
			contractMaster.setInsideOwner(bean.getStringPropertyByIndex(19));
			contractMaster.setInsidePhone(bean.getStringPropertyByIndex(20));
			contractMaster.setInsideAuthor(bean.getStringPropertyByIndex(21));
			contractMaster.setInsideAdditional(bean.getStringPropertyByIndex(22));
			contractMaster.setInsideAdditionalName(bean.getStringPropertyByIndex(23));
			contractMaster.setInsideAdditionalPhone(bean.getStringPropertyByIndex(24));
			contractMaster.setOutsideOwner(bean.getStringPropertyByIndex(25));
			contractMaster.setOutsidePhone(bean.getStringPropertyByIndex(26));
			contractMaster.setOutsideAuthor(bean.getStringPropertyByIndex(27));
			contractMaster.setOutsideAdditional(bean.getStringPropertyByIndex(28));
			contractMaster.setOutsideAdditionalName(bean.getStringPropertyByIndex(29));
			contractMaster.setOutsideAdditionalPhone(bean.getStringPropertyByIndex(30));
			contractMaster.setAdvanceNoticeDays(BigDecimal.valueOf(bean.getDoublePropertyByIndex(31)));
			contractMaster.setAffiliatedContractInfo(bean.getStringPropertyByIndex(32));
			contractMaster.setShippingTerms(bean.getStringPropertyByIndex(33));
			contractMaster.setProposalStartDate(getConvertedDateValue(bean.getPropertyValueByIndex(34)));
			contractMaster.setProposalEndDate(getConvertedDateValue(bean.getPropertyValueByIndex(35)));
			contractMaster.setOriginalStartDate(getConvertedDateValue(bean.getPropertyValueByIndex(36)));
			contractMaster.setOriginalEndDate(getConvertedDateValue(bean.getPropertyValueByIndex(37)));
			contractMaster
					.setHelperTableByAwardStatus(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(38)));
			contractMaster.setLastUpdatedDate(getConvertedDateValue(bean.getPropertyValueByIndex(39)));
			contractMaster.setPriceEscalationClause(bean.getStringPropertyByIndex(40));
			contractMaster.setExemptFromLowPrice(bean.getStringPropertyByIndex(41));
			contractMaster.setPriceResetIndicator(bean.getStringPropertyByIndex(42));
			contractMaster.setCancellationClause(bean.getStringPropertyByIndex(43));
			contractMaster.setMostFavoredNation(bean.getStringPropertyByIndex(44));
			contractMaster.setCategory(bean.getStringPropertyByIndex(45));
			contractMaster.setCurrency(bean.getStringPropertyByIndex(46));
			contractMaster.setMinimumOrder(bean.getStringPropertyByIndex(47));
			contractMaster
					.setHelperTableByPaymentTerms(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(48)));
			contractMaster.setInternalNotes(bean.getStringPropertyByIndex(49));
			contractMaster.setModifiedBy(Integer.valueOf(cdRequest.getUserId()));
			contractMaster.setModifiedDate(new Date());
			contractMaster.setSource("GTN");
			session.saveOrUpdate(contractMaster);
			session.flush();
			deletContractAliasInfo(contractMaster, session);
			saveContractAliasInfo(contractMaster, cdRequest, session);
			deletNotesTabInfo(contractMaster, session);
			saveNotesTabInfo(cdRequest, session);
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in save ContractInfo", e);
		}
	}

	@SuppressWarnings("unchecked")
	public void deletContractAliasInfo(ContractMaster contractMaster, Session session)
			throws GtnFrameworkGeneralException {
		try {
			Criteria cr = session.createCriteria(ContractAliasMaster.class)
					.add(Restrictions.eq("contractMaster", contractMaster));
			List<ContractAliasMaster> results = cr.list();
			if (results != null && !results.isEmpty()) {
				for (ContractAliasMaster contractAliasMaster : results) {
					session.delete(contractAliasMaster);
				}
				session.flush();
			}
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in deleting ContractAliasInfo", e);
		}
	}

	public void saveContractAliasInfo(ContractMaster contractMaster, GtnWsContractDashboardRequest cdRequest,
			Session session) throws GtnFrameworkGeneralException {
		try {
			for (GtnWsRecordBean aliasBean : cdRequest.getContractDashboardBean().getContractAliasRecordList()) {
				ContractAliasMaster contractAliasMaster = new ContractAliasMaster();
				contractAliasMaster.setContractMaster(contractMaster);
				contractAliasMaster.setContractAliasNo(aliasBean.getStringPropertyByIndex(2));
				contractAliasMaster.setContractAliasName(aliasBean.getStringPropertyByIndex(3));
				contractAliasMaster.setStartDate(getConvertedDateValue(aliasBean.getPropertyValueByIndex(4)));
				contractAliasMaster.setEndDate(getConvertedDateValue(aliasBean.getPropertyValueByIndex(5)));
				contractAliasMaster
						.setContracType(session.load(HelperTable.class, aliasBean.getIntegerPropertyByIndex(6)));
				contractAliasMaster.setTpCompanyMasterSid(aliasBean.getIntegerPropertyByIndex(7));

				contractAliasMaster.setCreatedBy(contractMaster.getModifiedBy());
				contractAliasMaster.setCreatedDate(contractMaster.getModifiedDate());
				contractAliasMaster.setModifiedBy(contractMaster.getModifiedBy());
				contractAliasMaster.setModifiedDate(contractMaster.getModifiedDate());
				contractAliasMaster.setSource("GTN");
				session.saveOrUpdate(contractAliasMaster);
			}
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in save ContractAliasInfo", e);
		}
	}

	public void deletNotesTabInfo(ContractMaster contractMaster, Session session) throws GtnFrameworkGeneralException {
		try {
			String query = getQuery("com.contractDashboard.process.deleteNotesTabInfo");
			Object[] delNotesTabParams = { contractMaster.getContractMasterSid() };
			GtnFrameworkDataType[] delNotesTabTypes = { GtnFrameworkDataType.INTEGER };
			getSqlQueryEngine().executeInsertOrUpdateQuery(query, delNotesTabParams, delNotesTabTypes, session);
			session.flush();
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in deleting NotesTabInfo", e);
		}
	}

	public void saveNotesTabInfo(GtnWsContractDashboardRequest cdRequest, Session session)
			throws GtnFrameworkGeneralException {
		try {
			for (GtnWsRecordBean notesBean : cdRequest.getContractDashboardBean().getNotesTabRecordList()) {
				String query = getQuery("com.contractDashboard.process.insertNotesTabInfo");
				Object[] saveNotesTabParams = { notesBean.getIntegerPropertyByIndex(1),
						notesBean.getStringPropertyByIndex(3),
						getConvertedDateValue(notesBean.getPropertyValueByIndex(4)),
						notesBean.getIntegerPropertyByIndex(5) };
				GtnFrameworkDataType[] saveNotesTabTypes = { GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.STRING,
						GtnFrameworkDataType.DATE, GtnFrameworkDataType.INTEGER };
				getSqlQueryEngine().executeInsertOrUpdateQuery(query, saveNotesTabParams, saveNotesTabTypes, session);

			}
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in save NotesTabInfo", e);
		}
	}

	private void saveCFPInfo(Session session, GtnWsContractDashboardRequest cdRequest, GtnWsGeneralRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		try {
			int userId = Integer.parseInt(gtnWsRequest.getUserId().trim());
			GtnWsRecordBean bean = cdRequest.getContractDashboardBean().getCompanyInfoBean();
			CfpContract cfpContract = session.load(CfpContract.class,
					cdRequest.getContractDashboardBean().getProcessBean().getCfpContractId());
			cfpContract.setCfpNo(bean.getStringPropertyByIndex(1));
			cfpContract.setCfpName(bean.getStringPropertyByIndex(2));
			cfpContract.setHelperTableByCfpStatus(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(3)));
			cfpContract.setCfpStartDate(getConvertedDateValue(bean.getPropertyValueByIndex(4)));
			cfpContract.setCfpEndDate(getConvertedDateValue(bean.getPropertyValueByIndex(5)));
			cfpContract.setHelperTableByCfpType(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(6)));
			cfpContract.setHelperTableByCfpCategory(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(7)));
			cfpContract
					.setHelperTableByCfpTradeClass(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(8)));
			cfpContract.setCfpDesignation(bean.getStringPropertyByIndex(9));
			cfpContract.setParentCfpId(bean.getIntegerPropertyByIndex(17));
			cfpContract.setParentCfpName(bean.getStringPropertyByIndex(11));
			cfpContract.setCreatedBy(userId);
			cfpContract.setModifiedBy(userId);
			cfpContract.setModifiedDate(new Date());
			cfpContract.setSalesInclusion(bean.getIntegerPropertyByIndex(12));
			cfpContract.setSource("GTN");
			session.saveOrUpdate(cfpContract);

			logger.info("cdRequest.getUserId() saveCFPInfo" + cdRequest.getUserId());
			logger.info("cdRequest.getSessionId()) saveCFPInfo" + cdRequest.getSessionId());
			logger.info("cfpContract.getCfpContractSid()" + cfpContract.getCfpContractSid());

			String cfpPendingQuery = getQuery("com.contractDashboard.process.saveCFPPENDINGDeleteQuery");
			Object[] cfpPendingDeleteParams = { cfpContract.getCfpContractSid() };
			GtnFrameworkDataType[] cfpPendingDeleteTypes = { GtnFrameworkDataType.INTEGER };
			getSqlQueryEngine().executeInsertOrUpdateQuery(cfpPendingQuery, cfpPendingDeleteParams,
					cfpPendingDeleteTypes, session);
			session.flush();

			cfpPendingQuery = getQuery("com.contractDashboard.process.saveCFPPENDINGQuery");
			Object[] cfpPendingParams = { cdRequest.getUserId(), cdRequest.getSessionId(),
					cfpContract.getCfpContractSid(), cfpContract.getCreatedBy(), cfpContract.getModifiedBy() };
			GtnFrameworkDataType[] cfpPendingTypes = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
					GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER };
			getSqlQueryEngine().executeInsertOrUpdateQuery(cfpPendingQuery, cfpPendingParams, cfpPendingTypes, session);
			session.flush();
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in save CFPInfo", e);
		}
	}

	private void saveIFPInfo(Session session, GtnWsContractDashboardRequest cdRequest, GtnWsGeneralRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		try {
			int userId = Integer.parseInt(gtnWsRequest.getUserId().trim());
			GtnWsRecordBean bean = cdRequest.getContractDashboardBean().getItemInfoBean();
			IfpContract ifpContract = session.load(IfpContract.class,
					cdRequest.getContractDashboardBean().getProcessBean().getIfpContractId());

			ifpContract.setIfpNo(bean.getStringPropertyByIndex(1));
			ifpContract.setIfpName(bean.getStringPropertyByIndex(2));
			ifpContract.setHelperTableByIfpStatus(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(3)));
			ifpContract.setIfpStartDate(getConvertedDateValue(bean.getPropertyValueByIndex(4)));
			ifpContract.setIfpEndDate(getConvertedDateValue(bean.getPropertyValueByIndex(5)));
			ifpContract
					.setHelperTableByIfpDesignation(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(6)));
			ifpContract.setParentIfpId(bean.getStringPropertyByIndex(7));
			ifpContract.setParentIfpName(bean.getStringPropertyByIndex(8));
			ifpContract.setHelperTableByIfpType(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(9)));
			ifpContract
					.setHelperTableByIfpCategory(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(12)));
			ifpContract.setCreatedBy(userId);
			ifpContract.setModifiedBy(userId);
			ifpContract.setModifiedDate(new Date());
			ifpContract.setSource("GTN");
			session.saveOrUpdate(ifpContract);

			logger.info("cdRequest.getUserId() saveIFPInfo" + cdRequest.getUserId());
			logger.info("cdRequest.getSessionId()) saveIFPInfo" + cdRequest.getSessionId());
			logger.info("ifpContract.getIfpContractSid()" + ifpContract.getIfpContractSid());

			String ifpQuery = getQuery("com.contractDashboard.process.saveIFPPENDINGDeleteQuery");
			Object[] ifpPendingDeleteParams = { ifpContract.getIfpContractSid() };
			GtnFrameworkDataType[] ifpPendingDeleteTypes = { GtnFrameworkDataType.INTEGER };
			getSqlQueryEngine().executeInsertOrUpdateQuery(ifpQuery, ifpPendingDeleteParams, ifpPendingDeleteTypes,
					session);
			session.flush();

			ifpQuery = getQuery("com.contractDashboard.process.saveIFPPendingQuery");
			Object[] ifpPendingParams = { cdRequest.getUserId(), cdRequest.getSessionId(),
					ifpContract.getIfpContractSid(), ifpContract.getCreatedBy(), ifpContract.getModifiedBy() };
			GtnFrameworkDataType[] ifpPendingTypes = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
					GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER };
			getSqlQueryEngine().executeInsertOrUpdateQuery(ifpQuery, ifpPendingParams, ifpPendingTypes, session);
			session.flush();
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in save IFPInfo", e);
		}
	}

	private void savePsInfo(Session session, GtnWsContractDashboardRequest cdRequest, GtnWsGeneralRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		try {
			int userId = Integer.parseInt(gtnWsRequest.getUserId().trim());
			GtnWsRecordBean bean = cdRequest.getContractDashboardBean().getPriceInfoBean();
			PsContract psContract = session.get(PsContract.class,
					cdRequest.getContractDashboardBean().getProcessBean().getPsContractId());

			psContract.setPsNo(bean.getStringPropertyByIndex(1));
			psContract.setPsName(bean.getStringPropertyByIndex(2));
			psContract.setHelperTableByPsStatus(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(3)));
			psContract.setPsStartDate(getConvertedDateValue(bean.getPropertyValueByIndex(4)));
			psContract.setPsEndDate(getConvertedDateValue(bean.getPropertyValueByIndex(5)));
			psContract
					.setHelperTableByPsDesignation(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(6)));
			psContract.setParentPsId(bean.getStringPropertyByIndex(7));
			psContract.setParentPsName(bean.getStringPropertyByIndex(8));
			psContract.setHelperTableByPsType(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(9)));
			psContract.setHelperTableByPsCategory(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(12)));
			psContract.setPsTradeClass(bean.getIntegerPropertyByIndex(15));
			psContract.setCreatedBy(userId);
			psContract.setModifiedBy(userId);
			psContract.setModifiedDate(new Date());
			psContract.setSource("GTN");
			session.saveOrUpdate(psContract);

			logger.info("cdRequest.getUserId() savePsInfo" + cdRequest.getUserId());
			logger.info("cdRequest.getSessionId()) savePsInfo" + cdRequest.getSessionId());
			logger.info("psContract.getPsContractSid() savePsInfo" + psContract.getPsContractSid());

			String psQuery = getQuery("com.contractDashboard.process.savePSPENDINGDeleteQuery");
			Object[] psQueryDeleteParams = { psContract.getPsContractSid() };
			GtnFrameworkDataType[] psQueryDeleteTypes = { GtnFrameworkDataType.INTEGER };
			getSqlQueryEngine().executeInsertOrUpdateQuery(psQuery, psQueryDeleteParams, psQueryDeleteTypes, session);
			session.flush();

			psQuery = getQuery("com.contractDashboard.process.savePSPendingQuery");
			Object[] psQueryParams = { cdRequest.getUserId(), cdRequest.getSessionId(), psContract.getPsContractSid(),
					psContract.getCreatedBy(), psContract.getModifiedBy() };
			GtnFrameworkDataType[] psQueryTypes = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
					GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER };
			getSqlQueryEngine().executeInsertOrUpdateQuery(psQuery, psQueryParams, psQueryTypes, session);
			session.flush();
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in save PsInfo", e);
		}
	}

	private void saveRsInfo(Session session, GtnWsContractDashboardRequest cdRequest, GtnWsGeneralRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		try {
			int userId = Integer.parseInt(gtnWsRequest.getUserId().trim());
			GtnWsRecordBean bean = cdRequest.getContractDashboardBean().getRebateInfoBean();
			RsContract rsContract = session.load(RsContract.class,
					cdRequest.getContractDashboardBean().getProcessBean().getRsContractId());

			rsContract.setRsNo(bean.getStringPropertyByIndex(1));
			rsContract.setRsName(bean.getStringPropertyByIndex(2));
			rsContract.setHelperTableByRsStatus(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(3)));
			rsContract.setHelperTableByRsType(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(4)));
			rsContract.setHelperTableByRebateProgramType(
					session.load(HelperTable.class, bean.getIntegerPropertyByIndex(5)));
			rsContract.setHelperTableByRsCategory(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(6)));
			rsContract.setRsStartDate(getConvertedDateValue(bean.getPropertyValueByIndex(7)));
			rsContract.setRsEndDate(getConvertedDateValue(bean.getPropertyValueByIndex(8)));
			rsContract.setHelperTableByRsTradeClass(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(9)));
			rsContract
					.setHelperTableByRsDesignation(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(10)));
			rsContract.setRsAlias(bean.getStringPropertyByIndex(11));
			rsContract.setParentRsId(bean.getStringPropertyByIndex(12));
			rsContract.setParentRsName(bean.getStringPropertyByIndex(13));
			rsContract.setRsTransRefNo(bean.getStringPropertyByIndex(14));
			rsContract.setRsTransRefName(bean.getStringPropertyByIndex(15));
			rsContract.setDeductionInclusion(bean.getIntegerPropertyByIndex(16));
			rsContract.setHelperTableByRsCalendar(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(23)));
			rsContract.setHelperTableByRebateFrequency(
					session.load(HelperTable.class, bean.getIntegerPropertyByIndex(24)));
			rsContract.setHelperTableByPaymentFrequency(
					session.load(HelperTable.class, bean.getIntegerPropertyByIndex(26)));
			rsContract.setPaymentGracePeriod(bean.getStringPropertyByIndex(27));
			rsContract
					.setHelperTableByPaymentTerms(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(28)));
			rsContract
					.setHelperTableByPaymentMethod(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(29)));
			rsContract.setHelperTableByInterestBearingBasis(
					session.load(HelperTable.class, bean.getIntegerPropertyByIndex(30)));
			rsContract.setHelperTableByInterestBearingBasis(
					session.load(HelperTable.class, bean.getIntegerPropertyByIndex(34)));
			rsContract.setEvaluationRuleLevel(bean.getIntegerPropertyByIndex(31));
			rsContract.setEvaluationRuleType(bean.getIntegerPropertyByIndex(32));
			rsContract.setEvaluationRuleOrAssociation(bean.getIntegerPropertyByIndex(43));
			rsContract.setCalculationRuleLevel(bean.getIntegerPropertyByIndex(35));
			rsContract.setCalculationType(bean.getIntegerPropertyByIndex(36));
			rsContract.setCalculationRule(bean.getIntegerPropertyByIndex(44));
			rsContract.setCalculationLevel(bean.getIntegerPropertyByIndex(38));
			rsContract.setHelperTableByRebateProcessingType(
					session.load(HelperTable.class, bean.getIntegerPropertyByIndex(39)));
			rsContract.setHelperTableByRsValidationProfile(
					session.load(HelperTable.class, bean.getIntegerPropertyByIndex(40)));
			rsContract.setCreatedBy(userId);
			rsContract.setModifiedBy(userId);
			rsContract.setModifiedDate(new Date());
			rsContract.setSource("GTN");
			session.saveOrUpdate(rsContract);

			saveRsUdcInfo(session, cdRequest, rsContract);

			logger.info("cdRequest.getUserId() saveRsInfo" + cdRequest.getUserId());
			logger.info("cdRequest.getSessionId()) saveRsInfo" + cdRequest.getSessionId());
			logger.info("rsContract.getRsContractSid()" + rsContract.getRsContractSid());

			String rsQuery = getQuery("com.contractDashboard.process.saveRSPENDINGDeleteQuery");
			Object[] rsQueryDeleteParams = { rsContract.getRsContractSid() };
			GtnFrameworkDataType[] rsQueryDeleteTypes = { GtnFrameworkDataType.INTEGER };
			getSqlQueryEngine().executeInsertOrUpdateQuery(rsQuery, rsQueryDeleteParams, rsQueryDeleteTypes, session);
			session.flush();

			rsQuery = getQuery("com.contractDashboard.process.saveRSPendingQuery");
			Object[] rsQueryParams = { cdRequest.getUserId(), cdRequest.getSessionId(), rsContract.getRsContractSid(),
					rsContract.getCreatedBy(), rsContract.getModifiedBy() };
			GtnFrameworkDataType[] rsQueryTypes = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
					GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER };
			getSqlQueryEngine().executeInsertOrUpdateQuery(rsQuery, rsQueryParams, rsQueryTypes, session);
			session.flush();
			saveRsFormulaDetailsInfo(session, cdRequest);
			saveRsDetailsInfo(session, cdRequest, rsContract);
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in save RsInfo", e);
		}
	}

	@SuppressWarnings("unchecked")
	private void saveRsUdcInfo(Session session, GtnWsContractDashboardRequest cdRequest, RsContract rsContract)
			throws GtnFrameworkGeneralException {
		try {
			GtnWsRecordBean bean = cdRequest.getContractDashboardBean().getRebateInfoBean();
			Criteria cr = session.createCriteria(Udcs.class)
					.add(Restrictions.eq("masterSid", rsContract.getRsContractSid()))
					.add(Restrictions.eq("masterType", GtnWsContractDashboardContants.RS_CONTRACT));

			List<Udcs> results = cr.list();
			Udcs udc = new Udcs();
			if (!results.isEmpty()) {
				udc = session.load(Udcs.class, results.get(0).getUdcsSid());
			}
			udc.setMasterSid(rsContract.getRsContractSid());
			udc.setMasterType(GtnWsContractDashboardContants.RS_CONTRACT);
			udc.setHelperTableByUdc1(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(17)));
			udc.setHelperTableByUdc2(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(18)));
			udc.setHelperTableByUdc3(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(19)));
			udc.setHelperTableByUdc4(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(20)));
			udc.setHelperTableByUdc5(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(21)));
			udc.setHelperTableByUdc6(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(22)));
			session.saveOrUpdate(udc);
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in save RsUdcInfo", e);
		}
	}

	private void saveRsFormulaDetailsInfo(Session session, GtnWsContractDashboardRequest cdRequest)
			throws GtnFrameworkGeneralException {
		try {
			String rsFromulaQuery = getQuery("com.contractDashboard.process.updateRSFormula");
			Object[] rsFromulaQueryParams = { cdRequest.getUserId(), cdRequest.getSessionId() };
			GtnFrameworkDataType[] rsFromulaQueryTypes = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING };
			getSqlQueryEngine().executeInsertOrUpdateQuery(rsFromulaQuery, rsFromulaQueryParams, rsFromulaQueryTypes,
					session);
			session.flush();
			rsFromulaQuery = getQuery("com.contractDashboard.process.saveRSFormula");
			getSqlQueryEngine().executeInsertOrUpdateQuery(rsFromulaQuery, rsFromulaQueryParams, rsFromulaQueryTypes,
					session);
			session.flush();
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in save RsFormulaDetailsInfo", e);
		}
	}

	@SuppressWarnings("unchecked")
	private void saveRsDetailsInfo(Session session, GtnWsContractDashboardRequest cdRequest, RsContract rsContract)
			throws GtnFrameworkGeneralException {
		try {
			Criteria crd = session.createCriteria(RsContractDetails.class)
					.add(Restrictions.eq("rsContract", rsContract)).add(Restrictions.ne("inboundStatus",
							GtnFrameworkCommonStringConstants.INBOUND_STATUS_D.charAt(0)));
			List<RsContractDetails> rsContDetList = crd.list();
			for (RsContractDetails rsContractDetails : rsContDetList) {
				String query = getQuery("com.contractDashboard.process.saveRSDetails");
				Object[] queryParams = { cdRequest.getUserId(), cdRequest.getSessionId(),
						rsContractDetails.getRsContractDetailsSid(),
						rsContractDetails.getItemMaster().getItemMasterSid() };
				GtnFrameworkDataType[] queryTypes = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
						GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER };
				getSqlQueryEngine().executeInsertOrUpdateQuery(query, queryParams, queryTypes, session);

			}
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in save RsDetailsInfo", e);
		}
	}

	public void validateContractDashboardToSave(GtnWsContractDashboardRequest cdRequest,
			GtnWsContractDashboardResponse cdResponse) throws GtnFrameworkGeneralException {
		cdResponse.setSuccess(true);
		try {
			cdRequest.getContractDashboardBean().getProcessBean();
			validateCfp(cdRequest, cdResponse);
			validateIfp(cdRequest, cdResponse);
			validatePs(cdRequest, cdResponse);
			validateRs(cdRequest, cdResponse);

		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in validateContractDashboardToSave", e);
		}
	}

	private void setValidationResponse(GtnWsContractDashboardResponse cdResponse, boolean status, String message) {
		cdResponse.setSuccess(status);
		cdResponse.setMessage(message);
	}

	public void validateCfp(GtnWsContractDashboardRequest cdRequest, GtnWsContractDashboardResponse cdResponse)
			throws GtnFrameworkGeneralException {
		GtnWsContractDashboardProcessBean processBean = cdRequest.getContractDashboardBean().getProcessBean();
		if (processBean.getCfpContractId() != 0) {
			if (!checkCfpAdded(cdRequest)) {
				setValidationResponse(cdResponse, false, "Select atleast one company for CFP  in Companies Tab.");
				return;
			}
			if (checkCfpTableNullValue(cdRequest, "cfpDetailsAttachedStatus")) {
				setValidationResponse(cdResponse, false, "Status is mandatory for all Company in Companies Tab.");
				return;
			}
			if (checkCfpTableDateNullValue(cdRequest, "cfpDetailsStartDate")) {
				setValidationResponse(cdResponse, false,
						"CFP start date is mandatory for all Company in Companies Tab.");
				return;
			}
			if (checkCfpTableEndDate(cdRequest)) {
				setValidationResponse(cdResponse, false,
						"CFP start date should be before CFP end date  in Companies Tab.");
				return;
			}
		}
	}

	public void validateIfp(GtnWsContractDashboardRequest cdRequest, GtnWsContractDashboardResponse cdResponse)
			throws GtnFrameworkGeneralException {
		GtnWsContractDashboardProcessBean processBean = cdRequest.getContractDashboardBean().getProcessBean();
		if (processBean.getIfpContractId() != 0) {
			if (!checkIfpAdded(cdRequest)) {
				setValidationResponse(cdResponse, false, "Add or select atleast one Item for IFP in Items Tab.");
				return;
			}
			if (checkIfpTableNullValue(cdRequest, "attachedStatus")) {
				setValidationResponse(cdResponse, false, "Status is mandatory in Items.");
				return;
			}
			if (checkIfpTableDateNullValue(cdRequest, "startDate")) {
				setValidationResponse(cdResponse, false, "Start Date is mandatory in Items.");
				return;
			}
			if (checkIfpTableEndDate(cdRequest, "startDate", "endDate")) {
				setValidationResponse(cdResponse, false, "IFP start date should be before IFP end date  in Items Tab.");
				return;
			}
		}
	}

	public void validatePs(GtnWsContractDashboardRequest cdRequest, GtnWsContractDashboardResponse cdResponse)
			throws GtnFrameworkGeneralException {
		GtnWsContractDashboardProcessBean processBean = cdRequest.getContractDashboardBean().getProcessBean();
		if (processBean.getPsContractId() != 0) {
			if (processBean.getIfpContractId() == 0 && !checkIfpAdded(cdRequest)) {
				setValidationResponse(cdResponse, false, "Add or select atleast one Item for Item Pricing Tab.");
				return;
			}
			if (checkIfpTableDateNullValue(cdRequest, "contractPriceStartDate")) {
				setValidationResponse(cdResponse, false, "CP Start Date  is mandatory in Item Pricing Tab.");
				return;
			}
			if (checkIfpTableNullValue(cdRequest, "priceType")) {
				setValidationResponse(cdResponse, false, "Price Type is mandatory in Pricing Tab.");
				return;
			}
			if (verifyPrice(cdRequest)) {
				setValidationResponse(cdResponse, false,
						"Price is mandatory in Pricing Tab when price type is Contract Price.");
				return;
			}

			if (checkIfpTableEndDate(cdRequest, "contractPriceStartDate", "contractPriceEndDate")) {
				setValidationResponse(cdResponse, false, "CP start date should be before CP end date in Pricing Tab.");
				return;
			}
			if (checkIfpTableNullValue(cdRequest, "psStatus")) {
				setValidationResponse(cdResponse, false, "Status is mandatory in Pricing Tab.");
				return;
			}
		}

	}

	public void validateRs(GtnWsContractDashboardRequest cdRequest, GtnWsContractDashboardResponse cdResponse)
			throws GtnFrameworkGeneralException {
		GtnWsContractDashboardProcessBean processBean = cdRequest.getContractDashboardBean().getProcessBean();
		if (processBean.getRsContractId() != 0) {
			if (processBean.getIfpContractId() == 0 && processBean.getPsContractId() == 0
					&& !checkIfpAdded(cdRequest)) {
				setValidationResponse(cdResponse, false, "Add or select atleast one Item for IFP in Rebate Setup Tab.");
				return;
			}
			if (checkIfpTableRegeXpValue(cdRequest, "bundleNo", "BUNDLE_NO", "([0-9|a-z|A-Z|\\ |\\*])*")) {
				setValidationResponse(cdResponse, false,
						"Bundle# can contain only Alphanumeric values in Rebate Setup Tab.");
				return;
			}
			if (checkIfpTableDateNullValue(cdRequest, "itemRebateStartDate")) {
				setValidationResponse(cdResponse, false, "Start Date is mandatory in Rebate Setup.");
				return;
			}
			if (checkIfpTableNullValue(cdRequest, "rsAttachedStatus")) {
				setValidationResponse(cdResponse, false, "Status is mandatory in Rebate Setup.");
				return;
			}
			if (checkIfpTableEndDate(cdRequest, "itemRebateStartDate", "itemRebateEndDate")) {
				setValidationResponse(cdResponse, false,
						"End Date should be greater than Start Date in Rebate Setup Tab.");
			}
		}
	}

	public boolean verifyPrice(GtnWsContractDashboardRequest cdRequest) throws GtnFrameworkGeneralException {
		int count = 0;
		try {
			String verifyPriceQuery = getQuery("com.contractDashboard.process.verifyPrice");
			Object[] verifyPriceQueryParams = { cdRequest.getUserId(), cdRequest.getSessionId() };
			GtnFrameworkDataType[] verifyPriceQueryTypes = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING };
			List<?> results = getSqlQueryEngine().executeSelectQuery(verifyPriceQuery, verifyPriceQueryParams,
					verifyPriceQueryTypes);

			count = Integer.valueOf(results.get(0).toString());
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException(
					GtnFrameworkWebserviceConstant.EXCEPTION_IN_CHECK_IFP_TABLE_NULL_VALUE, e);
		}
		return count != 0;
	}

	public boolean checkIfpTableRegeXpValue(GtnWsContractDashboardRequest cdRequest, String field, String dbField,
			String regxp) throws GtnFrameworkGeneralException {
		int count = 0;
		try (Session session = getController().getSessionFactory().openSession()) {
			Criteria cr = session.createCriteria(ImtdItemPriceRebateDetails.class)
					.add(Restrictions.eq(GtnFrameworkWebserviceConstant.CHECK_RECORD, true))
					.add(Restrictions.eq(GtnFrameworkWebserviceConstant.USERS_SID,
							Integer.valueOf(cdRequest.getUserId())))
					.add(Restrictions.eq(GtnFrameworkCommonConstants.SESSION_ID, cdRequest.getSessionId()))
					.add(Restrictions.ne(GtnFrameworkWebserviceConstant.OPERATION, "D"))
					.add(Restrictions.isNotNull(field)).add(Restrictions.sqlRestriction(dbField + " NOT LIKE ''"))
					.setProjection(Projections.property("bundleNo"));
			List<?> results = cr.list();
			for (int i = 0; i < results.size(); i++) {
				if (StringUtils.isNotBlank(results.get(0).toString()) && !results.get(0).toString().matches(regxp)) {
					count++;
				}
			}
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException(
					GtnFrameworkWebserviceConstant.EXCEPTION_IN_CHECK_IFP_TABLE_NULL_VALUE, e);
		}
		return count != 0;
	}

	public void approveContractDashboard(GtnWsContractDashboardRequest cdRequest,
			GtnWsContractDashboardResponse cdResponse) throws GtnFrameworkGeneralException {
		cdResponse.setSuccess(true);
		Session session = getController().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			approveAllContractDashboardInfo(session, cdRequest);

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			cdResponse.setSuccess(false);
			logger.error("Exception in saveContractDashboard", e);
			throw new GtnFrameworkGeneralException("Exception in saveContractDashboard ", e);
		} finally {
			session.close();
		}
	}

	private void approveAllContractDashboardInfo(Session session, GtnWsContractDashboardRequest cdRequest)
			throws GtnFrameworkGeneralException {
		try {
			GtnWsContractDashboardProcessBean processBean = cdRequest.getContractDashboardBean().getProcessBean();
			if (processBean.getCfpContractId() != 0) {
				approveCFPInfo(session, cdRequest);
			}
			if (processBean.getIfpContractId() != 0) {
				approveIFPInfo(session, cdRequest);
			}
			if (processBean.getRsContractId() != 0) {
				approveRSInfo(session, cdRequest);
			}
			if (processBean.getPsContractId() != 0) {
				approvePSInfo(session, cdRequest);
			}
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in save AllContractDashboardInfo", e);
		}
	}

	private void approveCFPInfo(Session session, GtnWsContractDashboardRequest cdRequest)
			throws GtnFrameworkGeneralException {
		try {
			CfpContract cfpContract = session.load(CfpContract.class,
					cdRequest.getContractDashboardBean().getProcessBean().getCfpContractId());
			logger.info("cdRequest.getUserId() approveCFPInfo" + cdRequest.getUserId());
			logger.info("cdRequest.getSessionId()) approveCFPInfo" + cdRequest.getSessionId());
			logger.info("cfpContract.getCfpContractSid()" + cfpContract.getCfpContractSid());
			String approveCfpquery = getQuery("com.contractDashboard.process.saveCFPApprove");
			Object[] approveCfpqueryParams = { cfpContract.getCfpContractSid() };
			GtnFrameworkDataType[] approveCfpqueryTypes = { GtnFrameworkDataType.INTEGER };
			getSqlQueryEngine().executeInsertOrUpdateQuery(approveCfpquery, approveCfpqueryParams, approveCfpqueryTypes,
					session);

			session.flush();

			approveCfpquery = getQuery("com.contractDashboard.process.saveCFPDeleteQuery");
			getSqlQueryEngine().executeInsertOrUpdateQuery(approveCfpquery, approveCfpqueryParams, approveCfpqueryTypes,
					session);
			session.flush();

		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in save approveCFPInfo", e);
		}
	}

	private void approveIFPInfo(Session session, GtnWsContractDashboardRequest cdRequest)
			throws GtnFrameworkGeneralException {
		try {
			IfpContract ifpContract = session.load(IfpContract.class,
					cdRequest.getContractDashboardBean().getProcessBean().getIfpContractId());
			logger.info("cdRequest.getUserId() approveIFPInfo" + cdRequest.getUserId());
			logger.info("cdRequest.getSessionId()) approveIFPInfo" + cdRequest.getSessionId());
			logger.info("ifpContract.getIfpContractSid()" + ifpContract.getIfpContractSid());
			String approveIfpquery = getQuery("com.contractDashboard.process.saveIFPApprove");
			Object[] approveIfpQueryParams = { ifpContract.getIfpContractSid() };
			GtnFrameworkDataType[] approveIfpqueryTypes = { GtnFrameworkDataType.INTEGER };
			getSqlQueryEngine().executeInsertOrUpdateQuery(approveIfpquery, approveIfpQueryParams, approveIfpqueryTypes,
					session);

			session.flush();

			approveIfpquery = getQuery("com.contractDashboard.process.saveIFPDeleteQuery");
			getSqlQueryEngine().executeInsertOrUpdateQuery(approveIfpquery, approveIfpQueryParams, approveIfpqueryTypes,
					session);

			session.flush();

		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in save approveIFPInfo", e);
		}
	}

	private void approveRSInfo(Session session, GtnWsContractDashboardRequest cdRequest)
			throws GtnFrameworkGeneralException {
		try {
			RsContract rsContract = session.load(RsContract.class,
					cdRequest.getContractDashboardBean().getProcessBean().getRsContractId());

			logger.info("cdRequest.getUserId() approveRSInfo() " + cdRequest.getUserId());
			logger.info("cdRequest.getSessionId()) approveRSInfo" + cdRequest.getSessionId());
			logger.info("rsContract.getRsContractSid()" + rsContract.getRsContractSid());
			String approveRSquery = getQuery("com.contractDashboard.process.saveRSApprove");
			Object[] approveRSqueryParams = { rsContract.getRsContractSid() };
			GtnFrameworkDataType[] approveRSqueryTypes = { GtnFrameworkDataType.INTEGER };
			getSqlQueryEngine().executeInsertOrUpdateQuery(approveRSquery, approveRSqueryParams, approveRSqueryTypes,
					session);

			session.flush();

			approveRSquery = getQuery("com.contractDashboard.process.saveRSDeleteQuery");
			getSqlQueryEngine().executeInsertOrUpdateQuery(approveRSquery, approveRSqueryParams, approveRSqueryTypes,
					session);
			session.flush();

		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in save approveRSInfo", e);
		}
	}

	private void approvePSInfo(Session session, GtnWsContractDashboardRequest cdRequest)
			throws GtnFrameworkGeneralException {
		try {
			PsContract psContract = session.get(PsContract.class,
					cdRequest.getContractDashboardBean().getProcessBean().getPsContractId());

			logger.info("cdRequest.getUserId() approvePSInfo" + cdRequest.getUserId());
			logger.info("cdRequest.getSessionId()) approvePSInfo" + cdRequest.getSessionId());
			logger.info("psContract.getPsContractSid() approvePSInfo" + psContract.getPsContractSid());

			String approvePSQuery = getQuery("com.contractDashboard.process.savePSApprove");
			Object[] approvePSqueryParams = { psContract.getPsContractSid() };
			GtnFrameworkDataType[] approvePSqueryTypes = { GtnFrameworkDataType.INTEGER };
			getSqlQueryEngine().executeInsertOrUpdateQuery(approvePSQuery, approvePSqueryParams, approvePSqueryTypes,
					session);

			session.flush();

			approvePSQuery = getQuery("com.contractDashboard.process.savePSDeleteQuery");
			getSqlQueryEngine().executeInsertOrUpdateQuery(approvePSQuery, approvePSqueryParams, approvePSqueryTypes,
					session);
			session.flush();

		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in save approvePSInfo", e);
		}
	}

	private GtnFrameworkSqlQueryEngine getSqlQueryEngine() {
		return getController().getGtnSqlQueryEngine();
	}

	public void rejectContractDashboard(GtnWsContractDashboardRequest cdRequest,
			GtnWsContractDashboardResponse cdResponse) throws GtnFrameworkGeneralException {
		cdResponse.setSuccess(true);
		Session session = getController().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			rejectAllContractDashboardInfo(session, cdRequest);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			cdResponse.setSuccess(false);
			logger.error("Exception in rejectContractDashboard", e);
			throw new GtnFrameworkGeneralException("Exception in rejectContractDashboard ", e);
		} finally {
			session.close();
		}
	}

	private void rejectAllContractDashboardInfo(Session session, GtnWsContractDashboardRequest cdRequest)
			throws GtnFrameworkGeneralException {
		try {
			GtnWsContractDashboardProcessBean processBean = cdRequest.getContractDashboardBean().getProcessBean();
			if (processBean.getCfpContractId() != 0) {
				rejectCFPInfo(session, cdRequest);
			}
			if (processBean.getIfpContractId() != 0) {
				rejectIFPInfo(session, cdRequest);
			}
			if (processBean.getRsContractId() != 0) {
				rejectRSInfo(session, cdRequest);
			}
			if (processBean.getPsContractId() != 0) {
				rejectPSInfo(session, cdRequest);
			}
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in rejectAllContractDashboardInfo", e);
		}
	}

	private void rejectCFPInfo(Session session, GtnWsContractDashboardRequest cdRequest)
			throws GtnFrameworkGeneralException {
		try {
			CfpContract rejectcfpContract = session.load(CfpContract.class,
					cdRequest.getContractDashboardBean().getProcessBean().getCfpContractId());
			logger.info("cdRequest.getUserId() rejectCFPInfo" + cdRequest.getUserId());
			logger.info("cdRequest.getSessionId()) rejectCFPInfo" + cdRequest.getSessionId());
			logger.info("cfpContract.getCfpContractSid() rejectCFPInfo" + rejectcfpContract.getCfpContractSid());
			String rejectCfpquery = getQuery("com.contractDashboard.process.saveCFPDeleteQuery");
			Object[] rejectCfpqueryParams = { rejectcfpContract.getCfpContractSid() };
			GtnFrameworkDataType[] rejectCfpqueryTypes = { GtnFrameworkDataType.INTEGER };
			getSqlQueryEngine().executeInsertOrUpdateQuery(rejectCfpquery, rejectCfpqueryParams, rejectCfpqueryTypes,
					session);

			session.flush();

		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in rejectCFPInfo", e);
		}
	}

	private void rejectIFPInfo(Session session, GtnWsContractDashboardRequest cdRequest)
			throws GtnFrameworkGeneralException {
		try {
			IfpContract rejectifpContract = session.load(IfpContract.class,
					cdRequest.getContractDashboardBean().getProcessBean().getIfpContractId());
			logger.info("cdRequest.getUserId() rejectIFPInfo" + cdRequest.getUserId());
			logger.info("cdRequest.getSessionId()) rejectIFPInfo" + cdRequest.getSessionId());
			logger.info("ifpContract.getIfpContractSid() rejectIFPInfo" + rejectifpContract.getIfpContractSid());
			String rejectIfpquery = getQuery("com.contractDashboard.process.saveIFPDeleteQuery");
			Object[] rejectIfpQueryParams = { rejectifpContract.getIfpContractSid() };
			GtnFrameworkDataType[] rejectIfpqueryTypes = { GtnFrameworkDataType.INTEGER };
			getSqlQueryEngine().executeInsertOrUpdateQuery(rejectIfpquery, rejectIfpQueryParams, rejectIfpqueryTypes,
					session);

			session.flush();

		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in rejectIFPInfo", e);
		}
	}

	private void rejectRSInfo(Session session, GtnWsContractDashboardRequest cdRequest)
			throws GtnFrameworkGeneralException {
		try {
			RsContract rejectrsContract = session.load(RsContract.class,
					cdRequest.getContractDashboardBean().getProcessBean().getRsContractId());

			logger.info("cdRequest.getUserId() rejectRSInfo() " + cdRequest.getUserId());
			logger.info("cdRequest.getSessionId()) rejectRSInfo" + cdRequest.getSessionId());
			logger.info("rsContract.getRsContractSid() rejectRSInfo" + rejectrsContract.getRsContractSid());
			String rejectRSquery = getQuery("com.contractDashboard.process.saveRSDeleteQuery");
			Object[] rejectRSqueryParams = { rejectrsContract.getRsContractSid() };
			GtnFrameworkDataType[] rejectRSqueryTypes = { GtnFrameworkDataType.INTEGER };
			getSqlQueryEngine().executeInsertOrUpdateQuery(rejectRSquery, rejectRSqueryParams, rejectRSqueryTypes,
					session);

			session.flush();

		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in rejectRSInfo", e);
		}
	}

	private void rejectPSInfo(Session session, GtnWsContractDashboardRequest cdRequest)
			throws GtnFrameworkGeneralException {
		try {
			PsContract rejectpsContract = session.get(PsContract.class,
					cdRequest.getContractDashboardBean().getProcessBean().getPsContractId());

			logger.info("cdRequest.getUserId() rejectPSInfo" + cdRequest.getUserId());
			logger.info("cdRequest.getSessionId()) rejectPSInfo" + cdRequest.getSessionId());
			logger.info("psContract.getPsContractSid() rejectPSInfo" + rejectpsContract.getPsContractSid());

			String rejectPSQuery = getQuery("com.contractDashboard.process.savePSDeleteQuery");
			Object[] rejectPSqueryParams = { rejectpsContract.getPsContractSid() };
			GtnFrameworkDataType[] rejectPSqueryTypes = { GtnFrameworkDataType.INTEGER };
			getSqlQueryEngine().executeInsertOrUpdateQuery(rejectPSQuery, rejectPSqueryParams, rejectPSqueryTypes,
					session);

			session.flush();

		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in rejectPSInfo", e);
		}
	}

}
