/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.contractdashboard.logic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataRow;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;
import com.stpl.gtn.gtn2o.ws.entity.contract.CfpContract;
import com.stpl.gtn.gtn2o.ws.entity.contract.CfpContractDetails;
import com.stpl.gtn.gtn2o.ws.entity.contract.ContractMaster;
import com.stpl.gtn.gtn2o.ws.entity.contract.IfpContract;
import com.stpl.gtn.gtn2o.ws.entity.contract.IfpContractDetails;
import com.stpl.gtn.gtn2o.ws.entity.contract.PsContract;
import com.stpl.gtn.gtn2o.ws.entity.contract.PsContractDetails;
import com.stpl.gtn.gtn2o.ws.entity.contract.RsContract;
import com.stpl.gtn.gtn2o.ws.entity.contract.RsContractDetails;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.contractdashboard.controller.GtnWsContractDashboardController;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractDashboardRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsExcelResponse;
import com.stpl.gtn.gtn2o.ws.response.contract.GtnWsContractDashboardResponse;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnWsContractDashboardProcessLogic {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsContractDashboardProcessLogic.class);
	private final GtnWsContractDashboardController controller;
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public GtnWsContractDashboardProcessLogic(GtnWsContractDashboardController controller) {
		this.controller = controller;
	}

	public GtnWsContractDashboardController getController() {
		return controller;
	}

	@SuppressWarnings("rawtypes")
	public String getQuery(List input, String queryName) {
		return getController().getQuery(input, queryName);
	}

	public String getQuery(String queryName) {
		return getController().getQuery(queryName);
	}

	public String getWhereClauseForAColumn(String expersion, String field, String value1, String value2)
			throws GtnFrameworkGeneralException {
		return getController().getWhereClauseForAColumn(expersion, field, value1, value2);
	}

	public List<Object> getSearchInput(GtnWsSearchRequest searchRequest, String comp, boolean leftSearch)
			throws GtnFrameworkGeneralException {
		return getController().getLogic().getSearchInput(searchRequest, comp, leftSearch);
	}

	private List<String> getForeCastTypeTables(String forecastType) {
		if ("Mandated".endsWith(forecastType)) {
			return Arrays.asList("M_SALES_PROJECTION_MASTER", "M_DISCOUNT_PROJECTION");
		}
		if ("Non Mandated".endsWith(forecastType)) {
			return Arrays.asList("NM_SALES_PROJECTION_MASTER", "NM_DISCOUNT_PROJ_MASTER");
		}
		if ("Channel".endsWith(forecastType)) {
			return Arrays.asList("CH_SALES_PROJECTION", "CH_PROJECTION_DISCOUNT");
		}
		return new ArrayList<>();
	}

	public GtnUIFrameworkWebserviceResponse getContractDashboardProcessTreeData(
			GtnUIFrameworkWebserviceRequest gtnWsRequest, GtnUIFrameworkWebserviceResponse gtnResponse)
			throws GtnFrameworkGeneralException {
		try {
			GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
			GtnWsSearchRequest searchRequest = gtnWsRequest.getGtnWsSearchRequest();
			if (searchRequest.isCount()) {
				gtnSerachResponse.setCount(getProcessTreeSearchCount(searchRequest));
			} else {
				GtnUIFrameworkDataTable tableData = new GtnUIFrameworkDataTable();
				gtnSerachResponse.setResultSet(getProcessTreeSearchData(searchRequest, tableData));
			}
			gtnResponse.setGtnSerachResponse(gtnSerachResponse);
		} catch (Exception ex) {
			logger.error("Exception in getContractDashboardProcessTreeData", ex);
			throw new GtnFrameworkGeneralException("Exception in getContractDashboardTableData", ex);
		}
		return gtnResponse;
	}

	private void getCountInputList(GtnWsSearchRequest searchRequest, String memberLevel, List<Object> countInputlist,
			int queryIndex, String conId) {
		if ("1".equals(memberLevel) && queryIndex == 2) {

			countInputlist.add(getCountQuery(3, "=" + conId, GtnFrameworkWebserviceConstant.CFPCO_CFP_CONTRACT_SID));
			countInputlist.add(getCountQuery(4, "=" + conId, GtnFrameworkWebserviceConstant.CFPCO_CFP_CONTRACT_SID,
					GtnFrameworkWebserviceConstant.IS_NULL));
			countInputlist.add(getCountQuery(5, "=" + conId, GtnFrameworkWebserviceConstant.CFPCO_CFP_CONTRACT_SID,
					GtnFrameworkWebserviceConstant.IS_NULL, GtnFrameworkWebserviceConstant.IS_NULL));
			return;

		}

		if (queryIndex == 3) {
			if ("1-2".equals(memberLevel)) {
				String cfpId = searchRequest.getParentBean().getStringPropertyByIndex(7);
				countInputlist.add(getCountQuery(4, "=" + conId, "=" + cfpId,
						GtnFrameworkWebserviceConstant.IFPCO_IFP_CONTRACT_SID));
				countInputlist.add(getCountQuery(5, "=" + conId, "=" + cfpId,
						GtnFrameworkWebserviceConstant.IFPCO_IFP_CONTRACT_SID, GtnFrameworkWebserviceConstant.IS_NULL));
				return;

			}
			if ("1".equals(memberLevel)) {
				countInputlist.add(getCountQuery(4, "=" + conId, GtnFrameworkWebserviceConstant.IS_NULL,
						GtnFrameworkWebserviceConstant.IFPCO_IFP_CONTRACT_SID));
				countInputlist.add(getCountQuery(5, "=" + conId, GtnFrameworkWebserviceConstant.IS_NULL,
						GtnFrameworkWebserviceConstant.IFPCO_IFP_CONTRACT_SID, GtnFrameworkWebserviceConstant.IS_NULL));
				return;
			}
		}
		if (queryIndex == 4) {
			addInputBasedOnLevel(countInputlist, memberLevel, conId, searchRequest);
		}
	}

	void addInputBasedOnLevel(List<Object> countInputlist, String memberLevel, String conId,
			GtnWsSearchRequest searchRequest) {

		if ("1".equals(memberLevel)) {
			countInputlist.add(getCountQuery(5, "=" + conId, GtnFrameworkWebserviceConstant.IS_NULL,
					GtnFrameworkWebserviceConstant.IS_NULL, GtnFrameworkWebserviceConstant.PSCO_PS_CONTRACT_SID));
			return;
		}
		if ("1-2".equals(memberLevel)) {
			String cfpId = searchRequest.getParentBean().getStringPropertyByIndex(7);
			countInputlist.add(getCountQuery(5, "=" + conId, "=" + cfpId, GtnFrameworkWebserviceConstant.IS_NULL,
					GtnFrameworkWebserviceConstant.PSCO_PS_CONTRACT_SID));
			return;

		}
		if ("1-3".equals(memberLevel)) {
			String ifpId = searchRequest.getParentBean().getStringPropertyByIndex(8);
			countInputlist.add(getCountQuery(5, "=" + conId, GtnFrameworkWebserviceConstant.IS_NULL, "=" + ifpId,
					GtnFrameworkWebserviceConstant.PSCO_PS_CONTRACT_SID));
			return;
		}
		if (GtnFrameworkWebserviceConstant.ONE_TWO_THREE.equals(memberLevel)) {
			String cfpId = searchRequest.getParentBean().getStringPropertyByIndex(7);
			String ifpId = searchRequest.getParentBean().getStringPropertyByIndex(8);

			countInputlist.add(getCountQuery(5, "=" + conId, "=" + cfpId, "=" + ifpId,
					GtnFrameworkWebserviceConstant.PSCO_PS_CONTRACT_SID));
		}
	}

	private void getExtraSelectParameterInputList(GtnWsSearchRequest searchRequest, List<Object> inputlist,
			int queryIndex) {
		for (int i = 14; i < 13 + queryIndex; i++) {
			inputlist.add(searchRequest.getParentBean().getStringPropertyByIndex(i));
		}
	}

	private void getExtraWhereParameterInputList(GtnWsSearchRequest searchRequest, String memberLevel,
			List<Object> inputlist, int queryIndex) {
		if ("1".equals(memberLevel)) {
			if (queryIndex == 3) {
				inputlist.add(GtnFrameworkWebserviceConstant.IS_NULL);
				return;
			}
			if (queryIndex == 4) {
				inputlist.add(GtnFrameworkWebserviceConstant.IS_NULL);
				inputlist.add(GtnFrameworkWebserviceConstant.IS_NULL);
				return;
			}

		}
		if ("1-2".equals(memberLevel)) {
			String cfpId = searchRequest.getParentBean().getStringPropertyByIndex(7);
			if (queryIndex == 3) {
				inputlist.add("= " + cfpId);
				return;
			}
		}
		if ("1-3".equals(memberLevel)) {
			String ifpId = searchRequest.getParentBean().getStringPropertyByIndex(8);
			if (queryIndex == 4) {
				inputlist.add(GtnFrameworkWebserviceConstant.IS_NULL);
				inputlist.add("= " + ifpId);
				return;
			}
		}

		if (GtnFrameworkWebserviceConstant.ONE_TWO_THREE.equals(memberLevel)) {
			String cfpId = searchRequest.getParentBean().getStringPropertyByIndex(7);
			String ifpId = searchRequest.getParentBean().getStringPropertyByIndex(8);
			if (queryIndex == 4) {
				inputlist.add("= " + cfpId);
				inputlist.add("= " + ifpId);
				return;
			}
		}
		getExtraWhereParameterInputListBasedOnLevel(searchRequest, memberLevel, inputlist, queryIndex);

	}

	private void getExtraWhereParameterInputListBasedOnLevel(GtnWsSearchRequest searchRequest, String memberLevel,
			List<Object> inputlist, int queryIndex) {
		if (queryIndex == 5) {
			String ifpId;
			String cfpId;
			String psId;
			if (memberLevel == null) {
				return;
			} else {
				switch (memberLevel) {
				case "1":
					inputlist.add(GtnFrameworkWebserviceConstant.IS_NULL);
					inputlist.add(GtnFrameworkWebserviceConstant.IS_NULL);
					inputlist.add(GtnFrameworkWebserviceConstant.IS_NULL);
					break;
				case "1-2":
					cfpId = searchRequest.getParentBean().getStringPropertyByIndex(7);
					inputlist.add("= " + cfpId);
					inputlist.add(GtnFrameworkWebserviceConstant.IS_NULL);
					inputlist.add(GtnFrameworkWebserviceConstant.IS_NULL);
					break;
				case "1-3":
					ifpId = searchRequest.getParentBean().getStringPropertyByIndex(8);
					inputlist.add(GtnFrameworkWebserviceConstant.IS_NULL);
					inputlist.add("= " + ifpId);
					inputlist.add(GtnFrameworkWebserviceConstant.IS_NULL);
					break;
				case "1-4":
					psId = searchRequest.getParentBean().getStringPropertyByIndex(9);
					inputlist.add(GtnFrameworkWebserviceConstant.IS_NULL);
					inputlist.add(GtnFrameworkWebserviceConstant.IS_NULL);
					inputlist.add("= " + psId);
					break;
				case GtnFrameworkWebserviceConstant.ONE_TWO_THREE:
					cfpId = searchRequest.getParentBean().getStringPropertyByIndex(7);
					ifpId = searchRequest.getParentBean().getStringPropertyByIndex(8);
					inputlist.add("= " + cfpId);
					inputlist.add("= " + ifpId);
					inputlist.add(GtnFrameworkWebserviceConstant.IS_NULL);
					break;
				case "1-2-4":
					cfpId = searchRequest.getParentBean().getStringPropertyByIndex(7);
					psId = searchRequest.getParentBean().getStringPropertyByIndex(9);
					inputlist.add("= " + cfpId);
					inputlist.add(GtnFrameworkWebserviceConstant.IS_NULL);
					inputlist.add("= " + psId);
					break;
				case "1-3-4":
					ifpId = searchRequest.getParentBean().getStringPropertyByIndex(8);
					psId = searchRequest.getParentBean().getStringPropertyByIndex(9);
					inputlist.add(GtnFrameworkWebserviceConstant.IS_NULL);
					inputlist.add("= " + ifpId);
					inputlist.add("= " + psId);
					break;
				case "1-2-3-4":
					cfpId = searchRequest.getParentBean().getStringPropertyByIndex(7);
					ifpId = searchRequest.getParentBean().getStringPropertyByIndex(8);
					psId = searchRequest.getParentBean().getStringPropertyByIndex(9);
					inputlist.add("= " + cfpId);
					inputlist.add("= " + ifpId);
					inputlist.add("= " + psId);
					break;
				default:
					break;
				}

			}

		}
	}

	@SuppressWarnings("unchecked")
	private void getProcessTreeChildData(GtnWsSearchRequest searchRequest, GtnUIFrameworkDataTable tableData, int index,
			int parentNo, String memberLevel) throws GtnFrameworkGeneralException {
		int startIndex = index + parentNo;
		if (startIndex < 11 && searchRequest.getTableRecordOffset() > 0) {
			int childCount = searchRequest.getParentBean().getIntegerPropertyByIndex(startIndex);
			if (searchRequest.getTableRecordStart() >= childCount) {
				searchRequest.setTableRecordStart(searchRequest.getTableRecordStart() - childCount);
				getProcessTreeChildData(searchRequest, tableData, index + 1, parentNo, memberLevel);
				return;
			}
			int offset = childCount - searchRequest.getTableRecordStart();
			offset = offset > searchRequest.getTableRecordOffset() ? searchRequest.getTableRecordOffset() : offset;
			int queryIndex = startIndex - 5;
			String conId = searchRequest.getParentBean().getStringPropertyByIndex(6);
			List<Object> inputlist = new ArrayList<>();
			inputlist.add(memberLevel);
			getCountInputList(searchRequest, memberLevel, inputlist, queryIndex, conId);
			getExtraSelectParameterInputList(searchRequest, inputlist, queryIndex);
			inputlist.add("=" + conId);
			getExtraWhereParameterInputList(searchRequest, memberLevel, inputlist, queryIndex);
			inputlist.add(searchRequest.getTableRecordStart());
			inputlist.add(offset);
			List<Object[]> result = getController()
					.executeQuery(getQuery(inputlist, "getCDR" + queryIndex + "Results"));
			tableData.addData(result);
			if (offset < searchRequest.getTableRecordOffset()) {
				searchRequest.setTableRecordStart(0);
				searchRequest.setTableRecordOffset(searchRequest.getTableRecordOffset() - offset);
				getProcessTreeChildData(searchRequest, tableData, index + 1, parentNo, memberLevel);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private GtnUIFrameworkDataTable getProcessTreeSearchData(GtnWsSearchRequest searchRequest,
			GtnUIFrameworkDataTable tableData) throws GtnFrameworkGeneralException {
		if (searchRequest.getParentBean() == null) {
			List<Object> inputlist = new ArrayList<>();
			for (int i = 2; i < 6; i++) {
				inputlist.add(getCountQuery(i, "= cm.CONTRACT_MASTER_SID"));
			}
			String comp = "Contract";
			if (!searchRequest.getGtnWebServiceSearchCriteriaList().isEmpty()) {
				GtnWebServiceSearchCriteria searchCriteria = searchRequest.getGtnWebServiceSearchCriteriaList().get(0);
				comp = GtnWsContractDashboardContants.getComponentMappedValue(searchCriteria.getFilterValue1());
			}
			inputlist.addAll(getSearchInput(searchRequest, comp, false));
			List<Object[]> result = getController().executeQuery(getQuery(inputlist, "getCDR1Results"));
			tableData.addData(result);
		} else {
			int parentNo = searchRequest.getParentBean().getIntegerPropertyByIndex(4);
			String memberLevel = searchRequest.getParentBean().getStringPropertyByIndex(5);
			getProcessTreeChildData(searchRequest, tableData, 6, parentNo, memberLevel);
		}
		return tableData;
	}

	public GtnUIFrameworkWebserviceResponse validateContractToRebuild(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
		gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
		cdResponse.setSuccess(false);
		Session session = getController().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			updateContractToRebuild(session, gtnWsRequest.getGtnWsContractDashboardRequest().getContractId());
			tx.commit();
			cdResponse.setSuccess(true);
		} catch (Exception e) {
			tx.rollback();
			cdResponse.setSuccess(false);
			logger.error("Exception in validateContractToRebuild", e);
			throw new GtnFrameworkGeneralException("Exception in validateContractToRebuild ", e);
		} finally {
			session.close();
		}
		return gtnResponse;
	}

	public void updateContractToRebuild(Session session, int contractMasterSid) throws GtnFrameworkGeneralException {

		try {
			ContractMaster contractMaster = session.load(ContractMaster.class, contractMasterSid);
			String updateContractQuery = getQuery("com.contractDashboard.rebuild.updateContract");
			Object[] updateContractQueryParams = { contractMasterSid };
			GtnFrameworkDataType[] updateContractQueryTypes = { GtnFrameworkDataType.INTEGER };
			getSqlQueryEngine().executeInsertOrUpdateQuery(updateContractQuery, updateContractQueryParams,
					updateContractQueryTypes, session);
			contractMaster.setModifiedDate(new Date());
			session.update(contractMaster);

		} catch (Exception ex) {
			logger.error("Exception in updateContractToRebuild", ex);
			throw new GtnFrameworkGeneralException("Exception in updateContractToRebuild", ex);
		}
	}

	public void updateContractToDForRebuild(ContractMaster contractMaster, Session session) {
		updateCFPforContract(contractMaster, session);
		updateIFPforContract(contractMaster, session);
		updatePSforContract(contractMaster, session);
		updateRSforContract(contractMaster, session);
	}

	@SuppressWarnings("unchecked")
	void updateCFPforContract(ContractMaster contractMaster, Session session) {
		Criteria cfpCriteria = session.createCriteria(CfpContract.class)
				.add(Restrictions.eq(GtnFrameworkWebserviceConstant.CONTRACT_MASTER, contractMaster))
				.add(Restrictions.eq(GtnFrameworkWebserviceConstant.RECORD_LOCK_STATUS, false));

		List<CfpContract> cfpList = cfpCriteria.list();
		if (cfpList != null) {
			for (CfpContract cfpContract : cfpList) {
				cfpContract.setInboundStatus('D');
				session.update(cfpContract);
				Criteria cfpDetailsCriteria = session.createCriteria(CfpContractDetails.class)
						.add(Restrictions.eq("cfpContract", cfpContract))
						.add(Restrictions.eq(GtnFrameworkWebserviceConstant.RECORD_LOCK_STATUS, false));
				List<CfpContractDetails> cfpDetailsList = cfpDetailsCriteria.list();
				if (cfpDetailsList != null) {
					for (CfpContractDetails cfpContractDetails : cfpDetailsList) {
						cfpContractDetails.setInboundStatus('D');
						session.update(cfpContractDetails);

					}
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	void updateIFPforContract(ContractMaster contractMaster, Session session) {
		Criteria ifpCriteria = session.createCriteria(IfpContract.class)
				.add(Restrictions.eq(GtnFrameworkWebserviceConstant.CONTRACT_MASTER, contractMaster))
				.add(Restrictions.eq(GtnFrameworkWebserviceConstant.RECORD_LOCK_STATUS, false));
		List<IfpContract> ifpList = ifpCriteria.list();
		if (ifpList != null) {
			for (IfpContract childContract : ifpList) {
				childContract.setInboundStatus('D');
				session.update(childContract);
				Criteria detailsCriteria = session.createCriteria(IfpContractDetails.class)
						.add(Restrictions.eq("ifpContract", childContract))
						.add(Restrictions.eq(GtnFrameworkWebserviceConstant.RECORD_LOCK_STATUS, false));
				List<IfpContractDetails> detailsList = detailsCriteria.list();
				if (detailsList != null) {
					for (IfpContractDetails childContractDetails : detailsList) {
						childContractDetails.setInboundStatus('D');
						session.update(childContractDetails);
					}
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	void updatePSforContract(ContractMaster contractMaster, Session session) {

		Criteria psCriteria = session.createCriteria(PsContract.class)
				.add(Restrictions.eq(GtnFrameworkWebserviceConstant.CONTRACT_MASTER, contractMaster))
				.add(Restrictions.eq(GtnFrameworkWebserviceConstant.RECORD_LOCK_STATUS, false));
		List<PsContract> psList = psCriteria.list();
		if (psList != null) {
			for (PsContract childContract : psList) {
				childContract.setInboundStatus('D');
				session.update(childContract);
				Criteria detailsCriteria = session.createCriteria(PsContractDetails.class)
						.add(Restrictions.eq("psContract", childContract))
						.add(Restrictions.eq(GtnFrameworkWebserviceConstant.RECORD_LOCK_STATUS, false));
				List<PsContractDetails> detailsList = detailsCriteria.list();
				if (detailsList != null) {
					for (PsContractDetails childContractDetails : detailsList) {
						childContractDetails.setInboundStatus('D');
						session.update(childContractDetails);
					}
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	void updateRSforContract(ContractMaster contractMaster, Session session) {

		Criteria rsCriteria = session.createCriteria(RsContract.class)
				.add(Restrictions.eq(GtnFrameworkWebserviceConstant.CONTRACT_MASTER, contractMaster))
				.add(Restrictions.eq(GtnFrameworkWebserviceConstant.RECORD_LOCK_STATUS, false));
		List<RsContract> rsList = rsCriteria.list();
		if (rsList != null) {
			for (RsContract childContract : rsList) {
				childContract.setInboundStatus('D');
				session.update(childContract);
				Criteria detailsCriteria = session.createCriteria(RsContractDetails.class)
						.add(Restrictions.eq("rsContract", childContract))
						.add(Restrictions.eq(GtnFrameworkWebserviceConstant.RECORD_LOCK_STATUS, false));
				List<RsContractDetails> detailsList = detailsCriteria.list();
				if (detailsList != null) {
					for (RsContractDetails childContractDetails : detailsList) {
						childContractDetails.setInboundStatus('D');
						session.update(childContractDetails);
					}
				}
			}
		}
	}

	public GtnUIFrameworkWebserviceResponse getContractDashboardRebuildTreeData(
			GtnUIFrameworkWebserviceRequest gtnWsRequest, GtnUIFrameworkWebserviceResponse gtnResponse)
			throws GtnFrameworkGeneralException {
		try {
			GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
			gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
			GtnWsRecordBean parentRecordBean = getRebuildTreeData(gtnWsRequest.getGtnWsSearchRequest());
			Object idVal = parentRecordBean.getPropertyValueByIndex(6);
			parentRecordBean = getModifiedBeanForTree(parentRecordBean);
			GtnWsRecordBean.addProperties(4, idVal, parentRecordBean.getProperties());
			cdResponse.setTreeBean(parentRecordBean);

			getContractMasterAndUpdate(gtnWsRequest, cdResponse);
		} catch (Exception ex) {
			logger.error(GtnFrameworkWebserviceConstant.EXCEPTION_IN_GET_CONTRACT_DASHBOARD_REBUILD_T, ex);
			throw new GtnFrameworkGeneralException(
					GtnFrameworkWebserviceConstant.EXCEPTION_IN_GET_CONTRACT_DASHBOARD_REBUILD_T, ex);
		}
		return gtnResponse;
	}

	private void getContractMasterAndUpdate(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnWsContractDashboardResponse cdResponse) throws GtnFrameworkGeneralException {
		Session session = getController().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			ContractMaster contractMaster = session.load(ContractMaster.class,
					gtnWsRequest.getGtnWsContractDashboardRequest().getContractId());
			updateContractToDForRebuild(contractMaster, session);
			tx.commit();
			cdResponse.setSuccess(true);
		} catch (Exception e) {
			tx.rollback();
			cdResponse.setSuccess(false);
			logger.error(GtnFrameworkWebserviceConstant.EXCEPTION_IN_GET_CONTRACT_DASHBOARD_REBUILD_T, e);
			throw new GtnFrameworkGeneralException("Exception in getContractDashboardRebuildTreeData ", e);
		} finally {
			session.close();
		}
	}

	private GtnWsRecordBean detectChildrenAllowed(GtnWsRecordBean recordBean) {
		int totalChild = 0;
		int startIndex = 6 + recordBean.getIntegerPropertyByIndex(4);
		for (int i = startIndex; i < 11; i++) {
			totalChild = totalChild + recordBean.getIntegerPropertyByIndex(i);
		}
		recordBean.getProperties().set(11, totalChild);
		recordBean.setParentFlag(totalChild != 0);
		return recordBean;
	}

	private GtnWsRecordBean getRebuildTreeData(GtnWsSearchRequest searchRequest) throws GtnFrameworkGeneralException {
		GtnWsRecordBean parentRecordBean = searchRequest.getParentBean();
		try {
			GtnUIFrameworkDataTable tableData = new GtnUIFrameworkDataTable();
			int parentNo = parentRecordBean.getIntegerPropertyByIndex(4);
			String memberLevel = parentRecordBean.getStringPropertyByIndex(5);
			searchRequest.setTableRecordStart(0);
			searchRequest.setTableRecordOffset(parentRecordBean.getIntegerPropertyByIndex(11));
			getProcessTreeChildData(searchRequest, tableData, 6, parentNo, memberLevel);
			for (GtnUIFrameworkDataRow dataRow : tableData.getDataTable()) {
				GtnWsRecordBean tempRecordBean = new GtnWsRecordBean();
				tempRecordBean.setProperties(dataRow.getColList());
				tempRecordBean = detectChildrenAllowed(tempRecordBean);
				if (tempRecordBean.getParentFlag()) {
					searchRequest.setParentBean(tempRecordBean);
					getRebuildTreeData(searchRequest);
				}
				parentRecordBean.addChild(getModifiedBeanForTree(tempRecordBean));
			}
		} catch (Exception e) {
			logger.error("Exception in getRebuildTreeData", e);
		}
		return parentRecordBean;
	}

	private GtnWsRecordBean getModifiedBeanForTree(GtnWsRecordBean recordBean) {
		List<Object> properties = new ArrayList<>();
		GtnWsRecordBean.addProperties(0, recordBean.getPropertyValueByIndex(1), properties);
		GtnWsRecordBean.addProperties(1, recordBean.getPropertyValueByIndex(2), properties);
		GtnWsRecordBean.addProperties(2, recordBean.getPropertyValueByIndex(3), properties);
		GtnWsRecordBean.addProperties(3, "", properties);
		int index = recordBean.getIntegerPropertyByIndex(4);
		GtnWsRecordBean.addProperties(4, recordBean.getPropertyValueByIndex(13 + index), properties);
		GtnWsRecordBean.addProperties(5, recordBean.getPropertyValueByIndex(12), properties);
		GtnWsRecordBean.addProperties(6, recordBean.getPropertyValueByIndex(13), properties);
		GtnWsRecordBean.addProperties(7, recordBean.getPropertyValueByIndex(4), properties);
		GtnWsRecordBean.addProperties(8, recordBean.getPropertyValueByIndex(0), properties);
		GtnWsRecordBean.addProperties(9, recordBean.getPropertyValueByIndex(6), properties);
		recordBean.setProperties(properties);
		return recordBean;
	}

	@SuppressWarnings("unchecked")
	private int getProcessTreeSearchCount(GtnWsSearchRequest searchRequest) throws GtnFrameworkGeneralException {
		int count = 0;
		if (searchRequest.getParentBean() == null) {
			String comp = "Contract";
			if (!searchRequest.getGtnWebServiceSearchCriteriaList().isEmpty()) {
				GtnWebServiceSearchCriteria searchCriteria = searchRequest.getGtnWebServiceSearchCriteriaList().get(0);
				comp = GtnWsContractDashboardContants.getComponentMappedValue(searchCriteria.getFilterValue1());
			}
			List<Object[]> result = getController()
					.executeQuery(getQuery(getSearchInput(searchRequest, comp, false), "getCDR1Count"));
			count = Integer.valueOf(String.valueOf(result.get(0)));
		} else {
			count = searchRequest.getParentBean().getIntegerPropertyByIndex(11);
		}
		return count;
	}

	private String getCountQuery(int queryNo, String... input) {
		List<Object> inputlist = new ArrayList<>();
		for (String inputVal : input) {
			inputlist.add(inputVal);
		}
		for (int i = inputlist.size(); i < queryNo - 1; i++) {
			inputlist.add(GtnFrameworkWebserviceConstant.IS_NULL);
		}
		return getQuery(inputlist, "getCDR" + queryNo + "Count");
	}

	public void validateContractToProcess(GtnWsContractDashboardRequest cdRequest,
			GtnWsContractDashboardResponse cdResponse) throws GtnFrameworkGeneralException {
		cdResponse.setSuccess(checkContractHasActuals(cdRequest.getContractId()));
		if (!cdResponse.isSuccess()) {
			cdResponse.setSuccess(findContractUsedInProjections(cdRequest.getContractId()));
		}
	}

	@SuppressWarnings("rawtypes")
	private boolean checkContractHasActuals(int contractSid) throws GtnFrameworkGeneralException {
		boolean contractHasActuals = false;
		try {
			List result = getController()
					.executeQuery(getQuery(Arrays.asList(contractSid), "getCDValidateContractToProcess"));
			if (result != null && !result.isEmpty()) {
				int count = Integer.parseInt(String.valueOf(result.get(0)));
				contractHasActuals = count > 0;
			}
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in checkContractHasActuals", e);
		}
		return contractHasActuals;
	}

	@SuppressWarnings("rawtypes")
	private boolean findContractUsedInProjections(int contractSid) throws GtnFrameworkGeneralException {
		boolean contractHasActuals = false;
		try {
			List resultList = getController().executeQuery(getQuery(Arrays.asList(contractSid), "getCDForcastType"));
			if (resultList != null && !resultList.isEmpty()) {
				for (int i = 0; i < resultList.size() && !contractHasActuals; i++) {
					String forcastType = String.valueOf(resultList.get(i));
					List<String> tableList = getForeCastTypeTables(forcastType);
					for (int j = 0; i < tableList.size() && !contractHasActuals; j++) {
						contractHasActuals = checkContractUsedInProjection(contractSid, tableList.get(j));
					}
				}
			}
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in findContractUsedInProjections", e);
		}
		return contractHasActuals;
	}

	@SuppressWarnings("rawtypes")
	private boolean checkContractUsedInProjection(int contractSid, String tableName)
			throws GtnFrameworkGeneralException {
		boolean contractHasActuals = false;
		try {
			List result = getController()
					.executeQuery(getQuery(Arrays.asList(contractSid, tableName), "checkContractInProjection"));
			if (result != null && !result.isEmpty()) {
				int count = Integer.parseInt(String.valueOf(result.get(0)));
				contractHasActuals = count > 0;
			}
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in checkContractUsedInProjection =" + tableName, e);
		}
		return contractHasActuals;
	}

	public GtnUIFrameworkWebserviceResponse deleteAllTempCfpOnLoad(GtnUIFrameworkWebserviceRequest tempCfpDeleteRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			GtnWsGeneralRequest getRequest = tempCfpDeleteRequest.getGtnWsGeneralRequest();
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 1);
			List<String> inputList = Arrays.asList(getRequest.getUserId(), getRequest.getSessionId(),
					dateFormat.format(cal.getTime()));
			getController()
					.executeUpdateQuery(getQuery(inputList, "com.contractDashboard.process.deleteOnLoadTempCFP"));
		} catch (Exception ex) {
			logger.error("Exception in deleteAllTempCfpOnLoad", ex);
			throw new GtnFrameworkGeneralException("Exception in deleteAllTempCfpOnLoad", ex);
		}
		return gtnResponse;
	}

	public GtnUIFrameworkWebserviceResponse deleteAllTempCfpOnBack(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			GtnWsGeneralRequest getRequest = gtnWsRequest.getGtnWsGeneralRequest();
			List<String> inputList = Arrays.asList(getRequest.getUserId(), getRequest.getSessionId());
			getController()
					.executeUpdateQuery(getQuery(inputList, "com.contractDashboard.process.deleteOnBackTempCFP"));
		} catch (Exception ex) {
			logger.error("Exception in deleteAllTempCfpOnBack", ex);
			throw new GtnFrameworkGeneralException("Exception in deleteAllTempCfpOnBack", ex);
		}
		return gtnResponse;
	}

	public GtnUIFrameworkWebserviceResponse addAllTempCfpOnLoad(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			GtnWsGeneralRequest getRequest = gtnWsRequest.getGtnWsGeneralRequest();
			GtnWsContractDashboardRequest cdRequest = gtnWsRequest.getGtnWsContractDashboardRequest();
			List<String> inputList = Arrays.asList(getRequest.getUserId(), getRequest.getSessionId(),
					cdRequest.getSessionDate(), String.valueOf(cdRequest.getCfpContractId()),
					String.valueOf(cdRequest.getContractId()));
			getController().executeUpdateQuery(getQuery(inputList, "com.contractDashboard.process.loadTempCFP"));
		} catch (Exception ex) {
			logger.error("Exception in addAllTempCfpOnLoad", ex);
			throw new GtnFrameworkGeneralException("Exception in addAllTempCfpOnLoad", ex);
		}
		return gtnResponse;
	}

	public GtnUIFrameworkWebserviceResponse deleteAllTempIfpOnLoad(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			GtnWsGeneralRequest getRequest = gtnWsRequest.getGtnWsGeneralRequest();
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 1);
			List<String> inputList = Arrays.asList(getRequest.getUserId(), getRequest.getSessionId(),
					dateFormat.format(cal.getTime()));
			getController()
					.executeUpdateQuery(getQuery(inputList, "com.contractDashboard.process.deleteOnLoadTempIFP"));
		} catch (Exception ex) {
			logger.error("Exception in deleteAllTempIfpOnLoad", ex);
			throw new GtnFrameworkGeneralException("Exception in deleteAllTempIfpOnLoad", ex);
		}
		return gtnResponse;
	}

	public GtnUIFrameworkWebserviceResponse deleteAllTempIfpOnBack(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			GtnWsGeneralRequest getRequest = gtnWsRequest.getGtnWsGeneralRequest();
			List<String> inputList = Arrays.asList(getRequest.getUserId(), getRequest.getSessionId());
			getController()
					.executeUpdateQuery(getQuery(inputList, "com.contractDashboard.process.deleteOnBackTempIFP"));
		} catch (Exception ex) {
			logger.error("Exception in deleteAllTempIfpOnBack", ex);
			throw new GtnFrameworkGeneralException("Exception in deleteAllTempIfpOnBack", ex);
		}
		return gtnResponse;
	}

	public GtnUIFrameworkWebserviceResponse addAllTempIfpOnLoad(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			GtnWsGeneralRequest getRequest = gtnWsRequest.getGtnWsGeneralRequest();
			GtnWsContractDashboardRequest cdRequest = gtnWsRequest.getGtnWsContractDashboardRequest();
			List<String> inputList = new ArrayList<>();
			inputList.add(getRequest.getUserId());
			inputList.add(getRequest.getSessionId());
			inputList.add(cdRequest.getSessionDate());
			inputList.add(" =" + cdRequest.getContractId());
			getController().executeUpdateQuery(
					getQuery(inputList, getQueryNameToLoadImtdItemPriceRebateDetails(inputList, cdRequest)));
		} catch (Exception ex) {
			logger.error("Exception in addAllTempIfpOnLoad", ex);
			throw new GtnFrameworkGeneralException("Exception in addAllTempIfpOnLoad", ex);
		}
		return gtnResponse;
	}

	private String getQueryNameToLoadTempIFP(List<String> inputList, GtnWsContractDashboardRequest cdRequest) {
                inputList.add(cdRequest.getCfpContractId() == 0 ? GtnFrameworkWebserviceConstant.IS_NULL
				: " =" + cdRequest.getCfpContractId());
		inputList.add(cdRequest.getIfpContractId() == 0 ? GtnFrameworkWebserviceConstant.IS_NULL
				: " =" + cdRequest.getIfpContractId());
		if (cdRequest.getRsContractId() == 0 && cdRequest.getPsContractId() == 0) {
			return "com.contractDashboard.process.loadTempIFP";
		}
		if (cdRequest.getRsContractId() == 0) {
			inputList.add(cdRequest.getPsContractId() == 0 ? GtnFrameworkWebserviceConstant.IS_NULL
					: " =" + cdRequest.getPsContractId());
			return "com.contractDashboard.process.loadTempPS";
		}
		inputList.add(cdRequest.getPsContractId() == 0 ? GtnFrameworkWebserviceConstant.IS_NULL
				: " =" + cdRequest.getPsContractId());
                inputList.add(3,cdRequest.getIfpContractId() == 0 ? GtnFrameworkWebserviceConstant.IS_NULL
				: " =" + cdRequest.getIfpContractId());
                inputList.add(4,cdRequest.getPsContractId() == 0 ? GtnFrameworkWebserviceConstant.IS_NULL
				: " =" + cdRequest.getPsContractId());
		inputList.add(5,cdRequest.getRsContractId() == 0 ? GtnFrameworkWebserviceConstant.IS_NULL
				: " =" + cdRequest.getRsContractId());
		
                inputList.add(cdRequest.getRsContractId() == 0 ? GtnFrameworkWebserviceConstant.IS_NULL
				: " =" + cdRequest.getRsContractId());
		return "com.contractDashboard.process.loadTempRS";
	}

	private String getQueryNameToLoadImtdItemPriceRebateDetails(List<String> inputList,
			GtnWsContractDashboardRequest cdRequest) {
		if (cdRequest.getIfpContractId() == 0) {
			if (cdRequest.getPsContractId() == 0) {
				inputList.add(getQueryInputBasedOnValue(cdRequest.getCfpContractId()));
				inputList.add(getQueryInputBasedOnValue(cdRequest.getRsContractId()));
				return "com.contractDashboard.process.loadImtdCRS";
			}
			if (cdRequest.getRsContractId() == 0) {
				inputList.add(getQueryInputBasedOnValue(cdRequest.getCfpContractId()));
				inputList.add(getQueryInputBasedOnValue(cdRequest.getPsContractId()));
				return "com.contractDashboard.process.loadImtdCPS";
			}
			inputList.add(getQueryInputBasedOnValue(cdRequest.getCfpContractId()));
			inputList.add(getQueryInputBasedOnValue(cdRequest.getPsContractId()));
			inputList.add(getQueryInputBasedOnValue(cdRequest.getRsContractId()));
			inputList.add(getQueryInputBasedOnValue(cdRequest.getPsContractId()));
			return "com.contractDashboard.process.loadImtdCPSRS";
		}
		if (cdRequest.getPsContractId() == 0 && cdRequest.getRsContractId() != 0) {
			inputList.add(getQueryInputBasedOnValue(cdRequest.getCfpContractId()));
			inputList.add(getQueryInputBasedOnValue(cdRequest.getIfpContractId()));
			inputList.add(getQueryInputBasedOnValue(cdRequest.getRsContractId()));
			inputList.add(getQueryInputBasedOnValue(cdRequest.getIfpContractId()));
			return "com.contractDashboard.process.loadImtdCIFPRS";
		}
		return getQueryNameToLoadTempIFP(inputList, cdRequest);
	}

	String getQueryInputBasedOnValue(int value) {
		return value == 0 ? GtnFrameworkWebserviceConstant.IS_NULL : " =" + value;
	}

	public GtnUIFrameworkWebserviceResponse addRuleForRSOnLoad(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			GtnWsGeneralRequest getRequest = gtnWsRequest.getGtnWsGeneralRequest();
			GtnWsContractDashboardRequest cdRequest = gtnWsRequest.getGtnWsContractDashboardRequest();
			List<String> inputList = new ArrayList<>();
			inputList.add(getRequest.getUserId());
			inputList.add(getRequest.getSessionId());
			inputList.add(cdRequest.getSessionDate());
			inputList.add(String.valueOf(cdRequest.getRsContractId()));
			getController().executeUpdateQuery(getQuery(inputList, "com.contractDashboard.process.loadImtdRSFR"));
		} catch (Exception ex) {
			logger.error("Exception in addRuleForRSOnLoad", ex);
			throw new GtnFrameworkGeneralException("Exception in addRuleForRSOnLoad", ex);
		}
		return gtnResponse;
	}

	public GtnUIFrameworkWebserviceResponse processContractInfoToSession(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			GtnWsContractDashboardRequest cdRequest = gtnWsRequest.getGtnWsContractDashboardRequest();
			deleteAllTempCfpOnLoad(gtnWsRequest, gtnResponse);
			deleteAllTempIfpOnLoad(gtnWsRequest, gtnResponse);
			if (cdRequest.getCfpContractId() != 0) {
				addAllTempCfpOnLoad(gtnWsRequest, gtnResponse);
			}
			if (cdRequest.getIfpContractId() != 0 || cdRequest.getRsContractId() != 0
					|| cdRequest.getPsContractId() != 0) {
				addAllTempIfpOnLoad(gtnWsRequest, gtnResponse);
			}
			if (cdRequest.getRsContractId() != 0) {
				addRuleForRSOnLoad(gtnWsRequest, gtnResponse);
			}
		} catch (Exception ex) {
			logger.error("Exception in processContractInfoToSession", ex);
			throw new GtnFrameworkGeneralException("Exception in processContractInfoToSession", ex);
		}
		return gtnResponse;
	}

	public GtnUIFrameworkWebserviceResponse deleteContractInfoOnBackProcess(
			GtnUIFrameworkWebserviceRequest gtnWsRequest, GtnUIFrameworkWebserviceResponse gtnResponse)
			throws GtnFrameworkGeneralException {
		try {
			deleteAllTempCfpOnBack(gtnWsRequest, gtnResponse);
			deleteAllTempIfpOnBack(gtnWsRequest, gtnResponse);
		} catch (Exception ex) {
			logger.error("Exception in deleteContractInfoOnBackProcess", ex);
			throw new GtnFrameworkGeneralException("Exception in deleteContractInfoOnBackProcess", ex);
		}
		return gtnResponse;
	}

	@SuppressWarnings("unchecked")
	public GtnUIFrameworkWebserviceResponse getContractInfoFieldData(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			GtnWsContractDashboardRequest cdRequest = gtnWsRequest.getGtnWsContractDashboardRequest();
			GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
			cdResponse.setContractInfoBean(new GtnWsRecordBean());
			gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
			List<Object[]> result = getController().executeQuery(getQuery(Arrays.asList(cdRequest.getContractId()),
					"com.contractDashboard.process.loadContractField"));
			cdResponse.getContractInfoBean().setProperties(Arrays.asList(result.get(0)));
			getNotesTabInfoData(cdRequest, cdResponse);
			getCDAliasInfoTableData(cdRequest, cdResponse);
		} catch (Exception ex) {
			logger.error("Exception in getContractInfoFieldData", ex);
			throw new GtnFrameworkGeneralException("Exception in getContractInfoFieldData", ex);
		}
		return gtnResponse;
	}

	@SuppressWarnings("unchecked")
	public GtnWsContractDashboardResponse getNotesTabInfoData(GtnWsContractDashboardRequest cdRequest,
			GtnWsContractDashboardResponse cdResponse) throws GtnFrameworkGeneralException {
		try {
			String query = getQuery(Arrays.asList(getController().getSysSchemaCatalog(), cdRequest.getContractId()),
					"com.contractDashboard.process.loadNotesTabInfo");
			List<Object[]> result = getController().executeQuery(query);
			cdResponse.setNotesTabRecordList(new ArrayList<GtnUIFrameworkDataRow>());
			if (result != null) {
				for (Object[] ob : result) {
					GtnUIFrameworkDataRow newDataRow = new GtnUIFrameworkDataRow();
					newDataRow.setColList(Arrays.asList(ob));
					cdResponse.addNotesTabRecordList(newDataRow);
				}
			}
		} catch (Exception ex) {
			logger.error("Exception in getNotesTabInfoData", ex);
			throw new GtnFrameworkGeneralException("Exception in getNotesTabInfoData", ex);
		}
		return cdResponse;
	}

	@SuppressWarnings("unchecked")
	public GtnWsContractDashboardResponse getCDAliasInfoTableData(GtnWsContractDashboardRequest cdRequest,
			GtnWsContractDashboardResponse cdResponse) throws GtnFrameworkGeneralException {
		try {
			String query = getQuery("com.contractDashboard.process.loadAliasTable");
			List<Object[]> result = getController().executeQuery(query,
					getController().createParams(cdRequest.getContractId()),
					getController().createDataTypes(GtnFrameworkDataType.INTEGER));
			cdResponse.setContractAliasRecordList(new ArrayList<GtnUIFrameworkDataRow>());
			if (result != null) {
				for (Object[] ob : result) {
					GtnUIFrameworkDataRow newDataRow = new GtnUIFrameworkDataRow();
					newDataRow.setColList(Arrays.asList(ob));
					cdResponse.addContractAliasRecordList(newDataRow);
				}
			}
		} catch (Exception ex) {
			logger.error("Exception in getCDAliasInfoTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getCDAliasInfoTableData", ex);
		}
		return cdResponse;
	}

	@SuppressWarnings("unchecked")
	public GtnUIFrameworkWebserviceResponse getCompanyInfoFieldData(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			GtnWsContractDashboardRequest cdRequest = gtnWsRequest.getGtnWsContractDashboardRequest();
			String catalog = getController().getSysSchemaCatalog();
			List<Object[]> result = getController()
					.executeQuery(getQuery(Arrays.asList(catalog, catalog, cdRequest.getCfpContractId()),
							"com.contractDashboard.process.loadCompaniesField"));
			GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
			cdResponse.setCompanyInfoBean(new GtnWsRecordBean());
			cdResponse.getCompanyInfoBean().setProperties(Arrays.asList(result.get(0)));
			gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
		} catch (Exception ex) {
			logger.error("Exception in getCompanyInfoFieldData", ex);
			throw new GtnFrameworkGeneralException("Exception in getCompanyInfoFieldData", ex);
		}
		return gtnResponse;
	}

	@SuppressWarnings("unchecked")
	public GtnUIFrameworkWebserviceResponse getItemInfoFieldData(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			GtnWsContractDashboardRequest cdRequest = gtnWsRequest.getGtnWsContractDashboardRequest();
			String catalog = getController().getSysSchemaCatalog();
			List<Object[]> result = getController()
					.executeQuery(getQuery(Arrays.asList(catalog, catalog, cdRequest.getIfpContractId()),
							"com.contractDashboard.process.loadItemsField"));
			GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
			cdResponse.setItemInfoBean(new GtnWsRecordBean());
			cdResponse.getItemInfoBean().setProperties(Arrays.asList(result.get(0)));
			gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
		} catch (Exception ex) {
			logger.error("Exception in getItemInfoFieldData", ex);
			throw new GtnFrameworkGeneralException("Exception in getItemInfoFieldData", ex);
		}
		return gtnResponse;
	}

	@SuppressWarnings("unchecked")
	public GtnUIFrameworkWebserviceResponse getPricingInfoFieldData(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			GtnWsContractDashboardRequest cdRequest = gtnWsRequest.getGtnWsContractDashboardRequest();
			String catalog = getController().getSysSchemaCatalog();
			List<Object[]> result = getController()
					.executeQuery(getQuery(Arrays.asList(catalog, catalog, cdRequest.getPsContractId()),
							"com.contractDashboard.process.loadPricingField"));
			GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
			cdResponse.setPriceInfoBean(new GtnWsRecordBean());
			cdResponse.getPriceInfoBean().setProperties(Arrays.asList(result.get(0)));
			gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
		} catch (Exception ex) {
			logger.error("Exception in getPricingInfoFieldData", ex);
			throw new GtnFrameworkGeneralException("Exception in getPricingInfoFieldData", ex);
		}
		return gtnResponse;
	}

	@SuppressWarnings("unchecked")
	public GtnUIFrameworkWebserviceResponse getRebateInfoFieldData(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			GtnWsContractDashboardRequest cdRequest = gtnWsRequest.getGtnWsContractDashboardRequest();
			List<Object[]> result = getController().executeQuery(getQuery(Arrays.asList(cdRequest.getRsContractId()),
					"com.contractDashboard.process.loadRebateField"));
			GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
			cdResponse.setRebateInfoBean(new GtnWsRecordBean());
			cdResponse.getRebateInfoBean().setProperties(Arrays.asList(result.get(0)));
			gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
		} catch (Exception ex) {
			logger.error("Exception in getRebateInfoFieldData", ex);
			throw new GtnFrameworkGeneralException("Exception in getRebateInfoFieldData", ex);
		}
		return gtnResponse;
	}

	public GtnUIFrameworkWebserviceResponse getContractDashboardProcessTreeExcelData(
			GtnUIFrameworkWebserviceRequest gtnWsRequest, GtnUIFrameworkWebserviceResponse gtnResponse)
			throws GtnFrameworkGeneralException {
		try {
			GtnWsSearchRequest searchRequest = gtnWsRequest.getGtnWsSearchRequest();
			searchRequest.setParentBean(null);
			List<GtnWsRecordBean> records = new ArrayList<>();
			getContractDashboardProcessTreeRecursiveExcelData(searchRequest, records);
			GtnWsExcelResponse excelResponse = new GtnWsExcelResponse();
			excelResponse.setResultBeanList(records);
			gtnResponse.setGtnWsExcelResponse(excelResponse);
		} catch (Exception ex) {
			logger.error("Exception in getContractDashboardProcessTreeExcelData", ex);
			throw new GtnFrameworkGeneralException("Exception in getContractDashboardProcessTreeExcelData", ex);
		}
		return gtnResponse;
	}

	public List<GtnWsRecordBean> getContractDashboardProcessTreeRecursiveExcelData(GtnWsSearchRequest searchRequest,
			List<GtnWsRecordBean> records) throws GtnFrameworkGeneralException {
		try {
			searchRequest.setCount(true);
			int count = getProcessTreeSearchCount(searchRequest);
			searchRequest.setCount(false);
			if (count > 0) {
				searchRequest.setTableRecordStart(0);
				searchRequest.setTableRecordOffset(count);
				GtnUIFrameworkDataTable tableData = new GtnUIFrameworkDataTable();
				getProcessTreeSearchData(searchRequest, tableData);
				for (GtnUIFrameworkDataRow record : tableData.getDataTable()) {
					GtnWsRecordBean dto = new GtnWsRecordBean();
					dto.setProperties(record.getColList());
					dto.setRecordHeader(searchRequest.getSearchColumnNameList());
					records.add(dto);
					detectChildrenAllowed(dto);
					dto.addAdditionalProperty(dto.getParentFlag());
					dto.addAdditionalProperty(records.size());
					if (dto.getAdditionalBooleanPropertyByIndex(0)) {
						searchRequest.setParentBean(dto);
						getContractDashboardProcessTreeRecursiveExcelData(searchRequest, records);
						dto.addAdditionalProperty(records.size() - 1);
					}
				}
			}
		} catch (Exception ex) {
			logger.error("Exception in getContractDashboardProcessTreeRecursiveExcelData", ex);
			throw new GtnFrameworkGeneralException("Exception in getContractDashboardProcessTreeRecursiveExcelData",
					ex);
		}
		return records;
	}

	private GtnFrameworkSqlQueryEngine getSqlQueryEngine() {
		return getController().getGtnSqlQueryEngine();
	}

}
