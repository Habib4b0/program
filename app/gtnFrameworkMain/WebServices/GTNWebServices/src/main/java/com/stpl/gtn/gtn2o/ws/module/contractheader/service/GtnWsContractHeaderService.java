package com.stpl.gtn.gtn2o.ws.module.contractheader.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.config.GtnWsAllListConfig;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.contract.bean.GtnWsContractMasterBean;
import com.stpl.gtn.gtn2o.ws.contract.bean.GtnwsContractAliasMasterBean;
import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.companygroup.CompanyGroup;
import com.stpl.gtn.gtn2o.ws.entity.companymaster.CompanyMaster;
import com.stpl.gtn.gtn2o.ws.entity.contract.ContractAliasMaster;
import com.stpl.gtn.gtn2o.ws.entity.contract.ContractMaster;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractHeaderRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.contract.GtnWsContractHeaderResponse;
import com.stpl.gtn.gtn2o.ws.util.GtnCommonUtil;
import com.stpl.gtn.gtn2o.ws.util.GtnWsCommonQueryContants;

@Service()
@Scope(value = "singleton")
public class GtnWsContractHeaderService {
	public GtnWsContractHeaderService() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsContractHeaderService.class);

	@Autowired
	private GtnWsAllListConfig gtnWebServiceAllListConfig;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnFrameworkAutomaticService automaticRelationService;

	public void getCompanyHeaderFetchQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse response) throws GtnFrameworkGeneralException {

		Session session = gtnWebServiceAllListConfig.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			int systemId = gtnWsRequest.getGtnWsContractHeaderRequest().getGtnWsContractMasterBean()
					.getContractMasterSid();
			ContractMaster contractMaster = session.get(ContractMaster.class, systemId);
			GtnWsContractMasterBean infoBean = new GtnWsContractMasterBean();
			infoBean.setContractId(contractMaster.getContractId());
			infoBean.setContractNo(contractMaster.getContractNo());
			infoBean.setContractName(contractMaster.getContractName());
			infoBean.setContractType(getHelpervalue(contractMaster.getHelperTableByContractType()));
			infoBean.setContractStatus(getHelpervalue(contractMaster.getHelperTableByContractStatus()));
			infoBean.setDocumentType(getHelpervalue(contractMaster.getHelperTableByDocumentType()));
			infoBean.setStartDate(contractMaster.getStartDate());
			infoBean.setEndDate(contractMaster.getEndDate());
			infoBean.setDocumentClass(getHelpervalue(contractMaster.getHelperTableByDocumentClass()));
			if (contractMaster.getCompanyMasterByContHoldCompanyMasterSid() != null) {
				infoBean.setCompanyMasterByContHoldCompanyMasterSid(
						contractMaster.getCompanyMasterByContHoldCompanyMasterSid() == null ? null
								: contractMaster.getCompanyMasterByContHoldCompanyMasterSid().getCompanyMasterSid());
				infoBean.setTradingPartnerName(
						contractMaster.getCompanyMasterByContHoldCompanyMasterSid().getCompanyNo());
			}
			if (contractMaster.getCompanyMasterByBunitCompanyMasterSid() != null) {
				infoBean.setCompanyMasterByBunitCompanyMasterSid(
						contractMaster.getCompanyMasterByBunitCompanyMasterSid() == null ? null
								: contractMaster.getCompanyMasterByBunitCompanyMasterSid().getCompanyMasterSid());
				infoBean.setCompanyName(contractMaster.getCompanyMasterByBunitCompanyMasterSid().getCompanyNo());
			}
			infoBean.setContractTradeClass(getHelpervalue(contractMaster.getHelperTableByContractTradeClass()));
			infoBean.setTerm(contractMaster.getTerm());
			infoBean.setRenegotiationStartDate(contractMaster.getRenegotiationStartDate());
			infoBean.setRenegotiationEndDate(contractMaster.getRenegotiationEndDate());
			infoBean.setPriceprotectionStartDate(contractMaster.getPriceprotectionStartDate());
			infoBean.setPriceprotectionEndDate(contractMaster.getPriceprotectionEndDate());
			if (contractMaster.getCompanyMasterByManfCompanyMasterSid() != null) {
				infoBean.setCompanyMasterByManfCompanyMasterSid(
						contractMaster.getCompanyMasterByManfCompanyMasterSid().getCompanyMasterSid());

			}
			infoBean.setInsideOwner(contractMaster.getInsideOwner());
			infoBean.setInsidePhone(contractMaster.getInsidePhone());
			infoBean.setInsideAuthor(contractMaster.getInsideAuthor());
			infoBean.setInsideAdditional(contractMaster.getInsideAdditional());
			infoBean.setInsideAdditionalName(contractMaster.getInsideAdditionalName());
			infoBean.setInsideAdditionalPhone(contractMaster.getInsideAdditionalPhone());
			infoBean.setOutsideOwner(contractMaster.getOutsideOwner());
			infoBean.setOutsidePhone(contractMaster.getOutsidePhone());
			infoBean.setOutsideAuthor(contractMaster.getOutsideAuthor());
			infoBean.setOutsideAdditional(contractMaster.getOutsideAdditional());
			infoBean.setOutsideAdditionalName(contractMaster.getOutsideAdditionalName());
			infoBean.setOutsideAdditionalPhone(contractMaster.getOutsideAdditionalPhone());
			infoBean.setAdvanceNoticeDays(contractMaster.getAdvanceNoticeDays() == null ? null
					: contractMaster.getAdvanceNoticeDays().doubleValue());
			infoBean.setAffiliatedContractInfo(contractMaster.getAffiliatedContractInfo());
			infoBean.setShippingTerms(contractMaster.getShippingTerms());
			infoBean.setProposalStartDate(contractMaster.getProposalStartDate());
			infoBean.setProposalEndDate(contractMaster.getProposalEndDate());
			infoBean.setOriginalStartDate(contractMaster.getOriginalStartDate());
			infoBean.setOriginalEndDate(contractMaster.getOriginalEndDate());
			infoBean.setAwardStatus(contractMaster.getHelperTableByAwardStatus() == null ? 0
					: contractMaster.getHelperTableByAwardStatus().getHelperTableSid());
			infoBean.setLastUpdatedDate(contractMaster.getLastUpdatedDate());
			infoBean.setPriceEscalationClause(contractMaster.getPriceEscalationClause());
			infoBean.setExemptFromLowPrice(contractMaster.getExemptFromLowPrice());
			infoBean.setPriceResetIndicator((contractMaster.getPriceResetIndicator() == null
					|| contractMaster.getPriceResetIndicator().isEmpty()) ? null
							: Integer.valueOf(contractMaster.getPriceResetIndicator()));
			infoBean.setCancellationClause(contractMaster.getCancellationClause());
			infoBean.setMostFavoredNation(contractMaster.getMostFavoredNation());
			infoBean.setCategory(contractMaster.getCategory());
			infoBean.setCurrency(contractMaster.getCurrency());
			infoBean.setMinimumOrder(contractMaster.getMinimumOrder());
			infoBean.setPaymentTerms(contractMaster.getHelperTableByPaymentTerms() == null ? 0
					: contractMaster.getHelperTableByPaymentTerms().getHelperTableSid());
			infoBean.setInternalNotes(contractMaster.getInternalNotes());

			infoBean.setRecordLockStatus(contractMaster.isRecordLockStatus());
			infoBean.setCreatedBy(gtnWebServiceAllListConfig.getUserIdNameMap().get(contractMaster.getCreatedBy()));

			infoBean.setCreatedDate(contractMaster.getCreatedDate());
			infoBean.setSource(contractMaster.getSource());
			infoBean.setBatchId(contractMaster.getBatchId());
			infoBean.setModifiedBy(gtnWebServiceAllListConfig.getUserIdNameMap().get(contractMaster.getModifiedBy()));
			infoBean.setModifiedDate(contractMaster.getModifiedDate());

			GtnWsContractHeaderResponse cGrpResponse = new GtnWsContractHeaderResponse();
			cGrpResponse.setGtnWsContractMasterBean(infoBean);
			cGrpResponse.setGtnwsContractAliasMasterBeanList(fetchContractAlias(session, systemId));
			cGrpResponse.setNotesTabList(getNotesTabDetails(systemId));
			response.setGtnWsContractHeaderResponse(cGrpResponse);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException("Exception in getCompanyHeaderFetchQuery ", e);
		} finally {
			session.close();
		}
	}

	Integer getHelpervalue(Object value) {
		return value == null ? null : ((HelperTable) value).getHelperTableSid();
	}

	private List<GtnwsContractAliasMasterBean> fetchContractAlias(Session session, int contractystemId) {
		List<GtnwsContractAliasMasterBean> aliasBeanList = new ArrayList<>();
		Criteria cr = session.createCriteria(ContractAliasMaster.class).add(Restrictions.ne("inboundStatus", 'D'));
		cr.add(Restrictions.eq("contractMaster", session.load(ContractMaster.class, contractystemId)));
		@SuppressWarnings("unchecked")
		List<ContractAliasMaster> results = cr.list();
		GtnwsContractAliasMasterBean aliasMaster = null;
		for (ContractAliasMaster idenBean : results) {
			aliasMaster = new GtnwsContractAliasMasterBean();
			aliasMaster.setContractAliasMasterSid(idenBean.getContractAliasMasterSid());
			aliasMaster.setContractAliasNo(idenBean.getContractAliasNo());
			aliasMaster.setContractAliasName(idenBean.getContractAliasName());
			aliasMaster.setContractType(idenBean.getContracType().getHelperTableSid());
			aliasMaster.setContractTypeDesc(idenBean.getContracType().getDescription());
			aliasMaster.setStartDate(idenBean.getStartDate());
			aliasMaster.setEndDate(idenBean.getEndDate());
			aliasMaster.setTpCompanyMasterSid(idenBean.getTpCompanyMasterSid());
			aliasBeanList.add(aliasMaster);
		}
		return aliasBeanList;
	}

	private HelperTable getHelperTable(Integer systemId, Session session) {
		return session.load(HelperTable.class, systemId == null ? 0 : systemId);
	}

	public void getCompanyGrpDeleteQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		Session session = gtnWebServiceAllListConfig.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			int systemId = gtnWsRequest.getGtnCompanyGroupRequest().getGtnCompanyGroupBean()
					.getGtnCompanyGrpInformationBean().getCompanyGrpSid();
			CompanyGroup masterData = session.get(CompanyGroup.class, systemId);
			session.delete(masterData);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException("Exception in getCompanyGrpDeleteQuery ", e);
		} finally {
			session.close();
		}

	}

	public int saveContractHeader(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		Session session = gtnWebServiceAllListConfig.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			ContractMaster masterData = generateContractHeader(gtnWsRequest, session);
			Integer contractMasterSid = (Integer) session.save(masterData);
			saveOrUpdateContractAlias(gtnWsRequest.getGtnWsContractHeaderRequest(), session, contractMasterSid);
			saveNotesTabDetails(gtnWsRequest.getGtnWsContractHeaderRequest(), contractMasterSid);

			GtnWsContractMasterBean bean = gtnWsRequest.getGtnWsContractHeaderRequest().getGtnWsContractMasterBean();
			List<GtnwsContractAliasMasterBean> aliasList = gtnWsRequest.getGtnWsContractHeaderRequest()
					.getGtnwsContractAliasMasterBeanList();
			bean.setContractMasterSid(contractMasterSid);
			GtnWsContractHeaderResponse gtnConResponse = new GtnWsContractHeaderResponse();
			gtnConResponse.setGtnWsContractMasterBean(bean);
			gtnConResponse.setGtnwsContractAliasMasterBeanList(aliasList);
			gtnResponse.setGtnWsContractHeaderResponse(gtnConResponse);
			tx.commit();
			automaticRelationService.checkAndUpdateAllRelationShip("");
			return contractMasterSid;
		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException("Exception in saveContractHeader ", e);
		} finally {
			session.close();
		}
	}

	private ContractMaster generateContractHeader(GtnUIFrameworkWebserviceRequest gtnWsRequest, Session session) {
		GtnWsContractMasterBean infoBean = gtnWsRequest.getGtnWsContractHeaderRequest().getGtnWsContractMasterBean();
		int userId = Integer.parseInt(gtnWsRequest.getGtnWsContractHeaderRequest().getUserId());
		ContractMaster contractMaster = new ContractMaster();
		contractMaster.setContractId(infoBean.getContractId());
		contractMaster.setContractNo(infoBean.getContractNo());
		contractMaster.setContractName(infoBean.getContractName());
		contractMaster.setHelperTableByContractType(getHelperTable(infoBean.getContractType(), session));
		contractMaster.setHelperTableByContractStatus(getHelperTable(infoBean.getContractStatus(), session));
		contractMaster.setHelperTableByDocumentType(getHelperTable(infoBean.getDocumentType(), session));
		contractMaster.setStartDate(infoBean.getStartDate());
		contractMaster.setEndDate(infoBean.getEndDate());
		contractMaster.setHelperTableByDocumentClass(getHelperTable(infoBean.getDocumentClass(), session));
		if (infoBean.getCompanyMasterByContHoldCompanyMasterSid() == null) {
			contractMaster.setCompanyMasterByContHoldCompanyMasterSid(null);
		} else {
			contractMaster.setCompanyMasterByContHoldCompanyMasterSid(
					session.load(CompanyMaster.class, infoBean.getCompanyMasterByContHoldCompanyMasterSid()));
		}
		if (infoBean.getCompanyMasterByBunitCompanyMasterSid() == null) {
			contractMaster.setCompanyMasterByBunitCompanyMasterSid(null);
		} else {
			contractMaster.setCompanyMasterByBunitCompanyMasterSid(
					session.load(CompanyMaster.class, infoBean.getCompanyMasterByBunitCompanyMasterSid()));
		}

		contractMaster.setHelperTableByContractTradeClass(getHelperTable(infoBean.getContractTradeClass(), session));
		contractMaster.setTerm(infoBean.getTerm());
		contractMaster.setRenegotiationStartDate(infoBean.getRenegotiationStartDate());
		contractMaster.setRenegotiationEndDate(infoBean.getRenegotiationEndDate());
		contractMaster.setPriceprotectionStartDate(infoBean.getPriceprotectionStartDate());
		contractMaster.setPriceprotectionEndDate(infoBean.getPriceprotectionEndDate());
		if (infoBean.getCompanyMasterByManfCompanyMasterSid() == null) {
			contractMaster.setCompanyMasterByManfCompanyMasterSid(null);
		} else {
			contractMaster.setCompanyMasterByManfCompanyMasterSid(
					session.load(CompanyMaster.class, infoBean.getCompanyMasterByManfCompanyMasterSid()));
		}
		contractMaster.setInsideOwner(infoBean.getInsideOwner());
		contractMaster.setInsidePhone(infoBean.getInsidePhone());
		contractMaster.setInsideAuthor(infoBean.getInsideAuthor());
		contractMaster.setInsideAdditional(infoBean.getInsideAdditional());
		contractMaster.setInsideAdditionalName(infoBean.getInsideAdditionalName());
		contractMaster.setInsideAdditionalPhone(infoBean.getInsideAdditionalPhone());
		contractMaster.setOutsideOwner(infoBean.getOutsideOwner());
		contractMaster.setOutsidePhone(infoBean.getOutsidePhone());
		contractMaster.setOutsideAuthor(infoBean.getOutsideAuthor());
		contractMaster.setOutsideAdditional(infoBean.getOutsideAdditional());
		contractMaster.setOutsideAdditionalName(infoBean.getOutsideAdditionalName());
		contractMaster.setOutsideAdditionalPhone(infoBean.getOutsideAdditionalPhone());
		contractMaster.setAdvanceNoticeDays(
				infoBean.getAdvanceNoticeDays() == null ? null : new BigDecimal(infoBean.getAdvanceNoticeDays()));
		contractMaster.setAffiliatedContractInfo(infoBean.getAffiliatedContractInfo());
		contractMaster.setShippingTerms(infoBean.getShippingTerms());
		contractMaster.setProposalStartDate(infoBean.getProposalStartDate());
		contractMaster.setProposalEndDate(infoBean.getProposalEndDate());
		contractMaster.setOriginalStartDate(infoBean.getOriginalStartDate());
		contractMaster.setOriginalEndDate(infoBean.getOriginalEndDate());
		contractMaster.setHelperTableByAwardStatus(getHelperTable(infoBean.getAwardStatus(), session));
		contractMaster.setLastUpdatedDate(infoBean.getLastUpdatedDate());
		contractMaster.setPriceEscalationClause(infoBean.getPriceEscalationClause());
		contractMaster.setExemptFromLowPrice(infoBean.getExemptFromLowPrice());
		contractMaster.setPriceResetIndicator(
				infoBean.getPriceResetIndicator() == null ? null : String.valueOf(infoBean.getPriceResetIndicator()));
		contractMaster.setCancellationClause(infoBean.getCancellationClause());
		contractMaster.setMostFavoredNation(infoBean.getMostFavoredNation());
		contractMaster.setCategory(infoBean.getCategory());
		contractMaster.setCurrency(infoBean.getCurrency());
		contractMaster.setMinimumOrder(infoBean.getMinimumOrder());
		contractMaster.setHelperTableByPaymentTerms(getHelperTable(infoBean.getPaymentTerms(), session));
		contractMaster.setInternalNotes(infoBean.getInternalNotes());

		contractMaster.setInboundStatus('A');
		contractMaster.setRecordLockStatus(false);
		contractMaster.setCreatedBy(userId);
		Date date = new Date();
		contractMaster.setCreatedDate(date);
		contractMaster.setSource("GTN");
		contractMaster.setBatchId(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		contractMaster.setModifiedBy(userId);
		contractMaster.setModifiedDate(date);

		return contractMaster;
	}

	public void updateCompanyGrpQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {

		GtnWsContractMasterBean infoBean = gtnWsRequest.getGtnWsContractHeaderRequest().getGtnWsContractMasterBean();
		int userId = Integer.parseInt(gtnWsRequest.getGtnWsContractHeaderRequest().getUserId());
		Session session = gtnWebServiceAllListConfig.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			ContractMaster updateContractMaster = session.get(ContractMaster.class, infoBean.getContractMasterSid());
			updateContractMaster.setContractId(infoBean.getContractId());
			updateContractMaster.setContractNo(infoBean.getContractNo());
			updateContractMaster.setContractName(infoBean.getContractName());
			updateContractMaster.setHelperTableByContractType(getHelperTable(infoBean.getContractType(), session));
			updateContractMaster.setHelperTableByContractStatus(getHelperTable(infoBean.getContractStatus(), session));
			updateContractMaster.setHelperTableByDocumentType(getHelperTable(infoBean.getDocumentType(), session));
			updateContractMaster.setStartDate(infoBean.getStartDate());
			updateContractMaster.setEndDate(infoBean.getEndDate());
			updateContractMaster.setHelperTableByDocumentClass(getHelperTable(infoBean.getDocumentClass(), session));
			if (infoBean.getCompanyMasterByContHoldCompanyMasterSid() == null) {
				updateContractMaster.setCompanyMasterByContHoldCompanyMasterSid(null);
			} else {
				updateContractMaster.setCompanyMasterByContHoldCompanyMasterSid(
						session.load(CompanyMaster.class, infoBean.getCompanyMasterByContHoldCompanyMasterSid()));
			}
			if (infoBean.getCompanyMasterByBunitCompanyMasterSid() == null) {
				updateContractMaster.setCompanyMasterByBunitCompanyMasterSid(null);
			} else {
				updateContractMaster.setCompanyMasterByBunitCompanyMasterSid(
						session.load(CompanyMaster.class, infoBean.getCompanyMasterByBunitCompanyMasterSid()));
			}
			updateContractMaster
					.setHelperTableByContractTradeClass(getHelperTable(infoBean.getContractTradeClass(), session));
			updateContractMaster.setTerm(infoBean.getTerm());
			updateContractMaster.setRenegotiationStartDate(infoBean.getRenegotiationStartDate());
			updateContractMaster.setRenegotiationEndDate(infoBean.getRenegotiationEndDate());
			updateContractMaster.setPriceprotectionStartDate(infoBean.getPriceprotectionStartDate());
			updateContractMaster.setPriceprotectionEndDate(infoBean.getPriceprotectionEndDate());
			if (infoBean.getCompanyMasterByManfCompanyMasterSid() == null) {
				updateContractMaster.setCompanyMasterByManfCompanyMasterSid(null);
			} else {
				updateContractMaster.setCompanyMasterByManfCompanyMasterSid(
						session.load(CompanyMaster.class, infoBean.getCompanyMasterByManfCompanyMasterSid()));
			}
			updateContractMaster.setInsideOwner(infoBean.getInsideOwner());
			updateContractMaster.setInsidePhone(infoBean.getInsidePhone());
			updateContractMaster.setInsideAuthor(infoBean.getInsideAuthor());
			updateContractMaster.setInsideAdditional(infoBean.getInsideAdditional());
			updateContractMaster.setInsideAdditionalName(infoBean.getInsideAdditionalName());
			updateContractMaster.setInsideAdditionalPhone(infoBean.getInsideAdditionalPhone());
			updateContractMaster.setOutsideOwner(infoBean.getOutsideOwner());
			updateContractMaster.setOutsidePhone(infoBean.getOutsidePhone());
			updateContractMaster.setOutsideAuthor(infoBean.getOutsideAuthor());
			updateContractMaster.setOutsideAdditional(infoBean.getOutsideAdditional());
			updateContractMaster.setOutsideAdditionalName(infoBean.getOutsideAdditionalName());
			updateContractMaster.setOutsideAdditionalPhone(infoBean.getOutsideAdditionalPhone());
			updateContractMaster.setAdvanceNoticeDays(
					infoBean.getAdvanceNoticeDays() == null ? null : new BigDecimal(infoBean.getAdvanceNoticeDays()));
			updateContractMaster.setAffiliatedContractInfo(infoBean.getAffiliatedContractInfo());
			updateContractMaster.setShippingTerms(infoBean.getShippingTerms());
			updateContractMaster.setProposalStartDate(infoBean.getProposalStartDate());
			updateContractMaster.setProposalEndDate(infoBean.getProposalEndDate());
			updateContractMaster.setOriginalStartDate(infoBean.getOriginalStartDate());
			updateContractMaster.setOriginalEndDate(infoBean.getOriginalEndDate());
			updateContractMaster.setHelperTableByAwardStatus(getHelperTable(infoBean.getAwardStatus(), session));
			updateContractMaster.setLastUpdatedDate(infoBean.getLastUpdatedDate());
			updateContractMaster.setPriceEscalationClause(infoBean.getPriceEscalationClause());
			updateContractMaster.setExemptFromLowPrice(infoBean.getExemptFromLowPrice());
			updateContractMaster.setPriceResetIndicator(infoBean.getPriceResetIndicator() == null ? null
					: String.valueOf(infoBean.getPriceResetIndicator()));
			updateContractMaster.setCancellationClause(infoBean.getCancellationClause());
			updateContractMaster.setMostFavoredNation(infoBean.getMostFavoredNation());
			updateContractMaster.setCategory(infoBean.getCategory());
			updateContractMaster.setCurrency(infoBean.getCurrency());
			updateContractMaster.setMinimumOrder(infoBean.getMinimumOrder());
			updateContractMaster.setHelperTableByPaymentTerms(getHelperTable(infoBean.getPaymentTerms(), session));
			updateContractMaster.setInternalNotes(infoBean.getInternalNotes());

			updateContractMaster.setModifiedBy(userId);
			updateContractMaster.setModifiedDate(new Date());

			session.saveOrUpdate(updateContractMaster);
			saveOrUpdateContractAlias(gtnWsRequest.getGtnWsContractHeaderRequest(), session,
					updateContractMaster.getContractMasterSid());
			saveNotesTabDetails(gtnWsRequest.getGtnWsContractHeaderRequest(),
					gtnWsRequest.getGtnWsContractHeaderRequest().getGtnWsContractMasterBean().getContractMasterSid());

			GtnWsContractMasterBean bean = gtnWsRequest.getGtnWsContractHeaderRequest().getGtnWsContractMasterBean();
			List<GtnwsContractAliasMasterBean> aliasList = gtnWsRequest.getGtnWsContractHeaderRequest()
					.getGtnwsContractAliasMasterBeanList();
			GtnWsContractHeaderResponse gtnConResponse = new GtnWsContractHeaderResponse();
			gtnConResponse.setGtnWsContractMasterBean(bean);
			gtnConResponse.setGtnwsContractAliasMasterBeanList(aliasList);
			gtnResponse.setGtnWsContractHeaderResponse(gtnConResponse);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException("Exception in updateCompanyGrpQuery ", e);
		} finally {
			session.flush();
			session.close();
		}

	}

	private void saveOrUpdateContractAlias(GtnWsContractHeaderRequest imRquest, Session session, int contractystemId) {
		List<GtnwsContractAliasMasterBean> aliasBeanList = imRquest.getGtnwsContractAliasMasterBeanList();
		for (GtnwsContractAliasMasterBean idenBean : aliasBeanList) {
			ContractAliasMaster aliasMaster = null;
			if (idenBean.getContractAliasMasterSid() == 0) {
				aliasMaster = new ContractAliasMaster();
				aliasMaster.setCreatedBy(Integer.valueOf(imRquest.getUserId()));
				aliasMaster.setCreatedDate(new Date());
				aliasMaster.setInboundStatus('A');
				aliasMaster.setSource("GTN");
				aliasMaster.setContractMaster(session.load(ContractMaster.class, contractystemId));
				aliasMaster.setContractAliasNo(idenBean.getContractAliasNo());
				aliasMaster.setContractAliasName(idenBean.getContractAliasName());
				aliasMaster.setContracType(getHelperTable(idenBean.getContractType(), session));
				aliasMaster.setStartDate(idenBean.getStartDate());
				aliasMaster.setEndDate(idenBean.getEndDate());
				aliasMaster.setTpCompanyMasterSid(idenBean.getTpCompanyMasterSid());
				aliasMaster.setModifiedBy(Integer.valueOf(imRquest.getUserId()));
				aliasMaster.setModifiedDate(new Date());
				Integer aliasMasterSid = (Integer) session.save(aliasMaster);
				idenBean.setContractAliasMasterSid(aliasMasterSid);
			} else {
				aliasMaster = session.get(ContractAliasMaster.class, idenBean.getContractAliasMasterSid());
				aliasMaster.setContractMaster(session.load(ContractMaster.class, contractystemId));
				aliasMaster.setContractAliasNo(idenBean.getContractAliasNo());
				aliasMaster.setContractAliasName(idenBean.getContractAliasName());
				aliasMaster.setContracType(getHelperTable(idenBean.getContractType(), session));
				aliasMaster.setStartDate(idenBean.getStartDate());
				aliasMaster.setEndDate(idenBean.getEndDate());
				aliasMaster.setTpCompanyMasterSid(idenBean.getTpCompanyMasterSid());

				aliasMaster.setInboundStatus('C');
				aliasMaster.setModifiedBy(Integer.valueOf(imRquest.getUserId()));
				aliasMaster.setModifiedDate(new Date());

				session.saveOrUpdate(aliasMaster);
			}

		}
	}

	public void saveNotesTabDetails(GtnWsContractHeaderRequest imRquest, int masterSid)
			throws GtnFrameworkGeneralException {
		deleteNotesTab(imRquest.getGtnWsContractMasterBean().getContractMasterSid());
		notesTabInsert(imRquest, masterSid);
	}

	private int deleteNotesTab(int systemId) throws GtnFrameworkGeneralException {

		String deleteQuery = GtnWsCommonQueryContants.GTN_COMMON_NOTE_TAB_DELETE + systemId
				+ " AND MASTER_TABLE_NAME='CONTRACT_MASTER'";
		return gtnSqlQueryEngine.executeInsertOrUpdateQuery(deleteQuery);
	}

	private int notesTabInsert(GtnWsContractHeaderRequest imRquest, int masterSid) throws GtnFrameworkGeneralException {
		logger.info("Enter Item Master notesTabInsert");
		List<NotesTabBean> notesTabRequestList = imRquest.getNoteBeanList();
		if (notesTabRequestList != null && !notesTabRequestList.isEmpty()) {
			StringBuilder cmNotesTabQuery = new StringBuilder(
					GtnWsCommonQueryContants.GTN_COMMON_NOTE_TAB_INSERT + "VALUES ");
			int i = 0;
			for (NotesTabBean notesTabRequest : notesTabRequestList) {
				if (i == 0) {
					cmNotesTabQuery.append(" (").append(masterSid).append(",'")
							.append(notesTabRequest.getMasterTableName()).append("','")
							.append(notesTabRequest.getFilePath()).append("',").append("GETDATE(),")
							.append(notesTabRequest.getCreatedBy()).append(")");
				} else {
					cmNotesTabQuery.append(",(").append(masterSid).append(",'")
							.append(notesTabRequest.getMasterTableName()).append("','")
							.append(notesTabRequest.getFilePath()).append("',").append("GETDATE(),")
							.append(notesTabRequest.getCreatedBy() + ")");
				}
				i++;
			}
			logger.info("Exit Item Master   notesTabInsert");
			return gtnSqlQueryEngine.executeInsertOrUpdateQuery(cmNotesTabQuery.toString());
		}
		return 0;
	}

	public void deletContractAlias(GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		Session session = gtnWebServiceAllListConfig.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		List<GtnwsContractAliasMasterBean> aliasList = gtnWsRequest.getGtnWsContractHeaderRequest()
				.getGtnwsContractAliasMasterBeanList();
		try {
			for (GtnwsContractAliasMasterBean gtnwsContractAliasMasterBean : aliasList) {
				session.delete(session.load(ContractAliasMaster.class,
						gtnwsContractAliasMasterBean.getContractAliasMasterSid()));
			}
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException("Exception in deletContractAlias ", e);
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public void deletContractMaster(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		Session session = gtnWebServiceAllListConfig.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		GtnWsContractMasterBean bean = gtnWsRequest.getGtnWsContractHeaderRequest().getGtnWsContractMasterBean();
		try {
			Criteria cr = session.createCriteria(ContractMaster.class)
					.add(Restrictions.eq("contractMasterSid", bean.getContractMasterSid()));
			ProjectionList proj = Projections.projectionList();
			proj.add(Projections.property("processStatus"));
			cr.setProjection(proj);
			List<?> results = cr.list();
			if (results != null && !results.isEmpty()) {
				Boolean isContractUsed = (Boolean) results.get(0);
				if (isContractUsed == null || !isContractUsed) {
					Criteria cr2 = session.createCriteria(ContractAliasMaster.class).add(Restrictions
							.eq("contractMaster", session.load(ContractMaster.class, bean.getContractMasterSid())));
					List<ContractAliasMaster> results2 = cr2.list();
					for (ContractAliasMaster contractAliasMaster : results2) {
						session.delete(contractAliasMaster);
					}
					session.delete(session.load(ContractMaster.class, bean.getContractMasterSid()));

				}
				GtnWsContractHeaderResponse chResponse = new GtnWsContractHeaderResponse();
				chResponse.setContractMasterUsed(isContractUsed == null ? false : isContractUsed);
				gtnResponse.setGtnWsContractHeaderResponse(chResponse);
			}
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException("Exception in deletContractMaster ", e);
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	private List<NotesTabBean> getNotesTabDetails(int systemId) throws GtnFrameworkGeneralException {
		logger.info("Enter getNotesTabDetails");
		String cmNotesTabDetailsSelectQuery = GtnWsCommonQueryContants.GTN_COMMON_NOTE_TAB_SELECT + systemId
				+ " AND MASTER_TABLE_NAME='CONTRACT_MASTER'";
		List<Object[]> cmNotesDetailsResultList = (List<Object[]>) gtnSqlQueryEngine
				.executeSelectQuery(cmNotesTabDetailsSelectQuery);
		List<NotesTabBean> cmNotesDetailsInfoBeanList = GtnCommonUtil.getNotesTabBean(cmNotesDetailsResultList,
				gtnWebServiceAllListConfig);
		logger.info("Exit getNotesTabDetails");
		return cmNotesDetailsInfoBeanList;
	}
}
