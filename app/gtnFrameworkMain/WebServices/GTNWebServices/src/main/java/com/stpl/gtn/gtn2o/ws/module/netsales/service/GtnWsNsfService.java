/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.netsales.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.cdr.CdrModel;
import com.stpl.gtn.gtn2o.ws.entity.netsalesformula.NetSalesFormulaMaster;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.netsales.bean.GtnUIFrameworkNsfInfoBean;
import com.stpl.gtn.gtn2o.ws.netsales.bean.GtnUIFrameworkNsfSelectedDeductionsBean;
import com.stpl.gtn.gtn2o.ws.netsales.bean.GtnUiFrameworkNsfSelectedCustomerBean;
import com.stpl.gtn.gtn2o.ws.netsales.bean.GtnWsNsfUpdateBean;
import com.stpl.gtn.gtn2o.ws.netsales.constants.GtnWsNsfCommonConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.netsales.GtnWsNetSalesFormulaGeneralRequest;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

/**
 *
 * @author STPL
 */
@Service()
@Scope(value = "singleton")
public class GtnWsNsfService {

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	public GtnWsNsfService() {
		// Empty Constructor
	}

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public int insertSelectedCustomersIntoTempTable(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		try {
			String sql = gtnWsSqlService.getQuery("getNsfAddDeclareQuery");

			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append(sql);

			List<GtnFrameworkDataType> dataTypeListTemp = new ArrayList<>();
			List<Object> paramListTemp = new ArrayList<>();

			List<GtnUiFrameworkNsfSelectedCustomerBean> beanList = gtnWsRequest.getGtnWsNetSalesGeneralRequest()
					.getNsCustomerInfoBeanList();

			dataTypeListTemp.add(GtnFrameworkDataType.STRING);
			paramListTemp.add(gtnWsRequest.getGtnWsGeneralRequest().getUserId());

			dataTypeListTemp.add(GtnFrameworkDataType.STRING);
			paramListTemp.add(gtnWsRequest.getGtnWsGeneralRequest().getSessionId());

			for (GtnUiFrameworkNsfSelectedCustomerBean selectedBeanList : beanList) {

				String subSql = gtnWsSqlService.getQuery("getNsfSalesBasisAddQuery");

				dataTypeListTemp.add(GtnFrameworkDataType.INTEGER);
				paramListTemp.add(selectedBeanList.getContractMasterSid());

				dataTypeListTemp.add(GtnFrameworkDataType.STRING);
				paramListTemp.add(selectedBeanList.getContractNo());

				dataTypeListTemp.add(GtnFrameworkDataType.STRING);
				paramListTemp.add(selectedBeanList.getContractName());

				dataTypeListTemp.add(GtnFrameworkDataType.INTEGER);
				paramListTemp.add(selectedBeanList.getContractDetSid());

				dataTypeListTemp.add(GtnFrameworkDataType.STRING);
				paramListTemp.add(selectedBeanList.getCfpNo());

				dataTypeListTemp.add(GtnFrameworkDataType.STRING);
				paramListTemp.add(selectedBeanList.getCfpName());

				dataTypeListTemp.add(GtnFrameworkDataType.STRING);
				paramListTemp.add(selectedBeanList.getCustomerNo());

				dataTypeListTemp.add(GtnFrameworkDataType.STRING);
				paramListTemp.add(selectedBeanList.getCustomerName());

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

	public int insertSelectedDeductionsIntoTempTable(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		try {
			String sql = gtnWsSqlService.getQuery("getNsfAddDeclareQuery");

			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append(sql);

			List<GtnFrameworkDataType> dataTypeListTemp = new ArrayList<>();
			List<Object> paramListTemp = new ArrayList<>();

			List<GtnUIFrameworkNsfSelectedDeductionsBean> beanList = gtnWsRequest.getGtnWsNetSalesGeneralRequest()
					.getSelectedDeductionInfoBeanList();

			dataTypeListTemp.add(GtnFrameworkDataType.STRING);
			paramListTemp.add(gtnWsRequest.getGtnWsGeneralRequest().getUserId());

			dataTypeListTemp.add(GtnFrameworkDataType.STRING);
			paramListTemp.add(gtnWsRequest.getGtnWsGeneralRequest().getSessionId());

			for (GtnUIFrameworkNsfSelectedDeductionsBean selectedBeanList : beanList) {

				String subSql = gtnWsSqlService.getQuery("getNsfDeductionsTabAddQuery");

				dataTypeListTemp.add(GtnFrameworkDataType.NULL_ALLOWED);
				paramListTemp.add(selectedBeanList.getContractMasterSid());

				dataTypeListTemp.add(GtnFrameworkDataType.STRING);
				paramListTemp.add(selectedBeanList.getContractNo());

				dataTypeListTemp.add(GtnFrameworkDataType.STRING);
				paramListTemp.add(selectedBeanList.getContractName());

				dataTypeListTemp.add(GtnFrameworkDataType.NULL_ALLOWED);
				paramListTemp.add(selectedBeanList.getRsContractSid());

				dataTypeListTemp.add(GtnFrameworkDataType.STRING);
				paramListTemp.add(selectedBeanList.getDeductionNo());

				dataTypeListTemp.add(GtnFrameworkDataType.STRING);
				paramListTemp.add(selectedBeanList.getDeductionName());

				dataTypeListTemp.add(GtnFrameworkDataType.INTEGER);
				paramListTemp.add(selectedBeanList.getDeductionType());

				dataTypeListTemp.add(GtnFrameworkDataType.INTEGER);
				paramListTemp.add(selectedBeanList.getDeductionSubType());

				dataTypeListTemp.add(GtnFrameworkDataType.INTEGER);
				paramListTemp.add(selectedBeanList.getDeductionCategory());

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

	public Integer saveNsfInfo(GtnUIFrameworkWebserviceRequest nsfSaveWsRequest) throws GtnFrameworkGeneralException {
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			GtnUIFrameworkNsfInfoBean nsfInfoBean = nsfSaveWsRequest.getGtnWsNetSalesGeneralRequest().getnSfInfoBean();
			GtnWsGeneralRequest nsfSaveRequest = nsfSaveWsRequest.getGtnWsGeneralRequest();
			NetSalesFormulaMaster formulaMaster = getNetSalesFormulaMasterModel(nsfInfoBean, nsfSaveRequest, session);
			Integer nsfSystemId = (Integer) session.save(formulaMaster);
			getNfsInfo(session, nsfSaveRequest, nsfSystemId,false);
			tx.commit();
			return nsfSystemId;
		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException(e.getMessage(), e);
		} finally {
			session.close();
		}
	}

	private void getNfsInfo(Session session, GtnWsGeneralRequest nsfSaveRequest, Integer nsfSystemId,boolean isEdit)
			throws GtnFrameworkGeneralException {
		
		String salesBasisQuery = gtnWsSqlService.getQuery("getNsfSalesBasisMergeQuery");
		String deductionQuery = gtnWsSqlService.getQuery("getNsfDeductionsMergeQuery");
		String updatedeductionQuery=gtnWsSqlService.getQuery("getDeductionsRemoveUpdateRecordQuery");
		String updatedeductionQueryisEmpty=gtnWsSqlService.getQuery("getDeductionsRemoveUpdateRecordQueryisempty");
		
		String deleteTempQuery = gtnWsSqlService.getQuery("getNsfDeleteTempTableDataQuery");

		Object[] params = { nsfSystemId, nsfSaveRequest.getUserId(), nsfSaveRequest.getSessionId() };
		Object[] deleteQueryParams = { nsfSaveRequest.getUserId(), nsfSaveRequest.getSessionId() };
		GtnFrameworkDataType[] types = { GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING };
		GtnFrameworkDataType[] deleteQueryTypes = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING };
		
		if (isEdit) {
			@SuppressWarnings("unchecked")
			List<Integer> selectList = (List<Integer>) (gtnSqlQueryEngine.executeSelectQuery(gtnWsSqlService.getQuery(
					Arrays.asList(nsfSystemId, nsfSaveRequest.getUserId(), nsfSaveRequest.getSessionId()),
					"getDeductionsupdateselectRecordQuery")));
			Object[] paramsUpdate = { nsfSystemId, selectList };
			GtnFrameworkDataType[] updateQueryTypes = { GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.IN_LIST };
			Object[] paramsisempty = { nsfSystemId };
			GtnFrameworkDataType[] isemptyTypes = { GtnFrameworkDataType.INTEGER };
			if (selectList.isEmpty()) {
				gtnSqlQueryEngine.executeInsertOrUpdateQuery(updatedeductionQueryisEmpty, paramsisempty, isemptyTypes,
						session);
			} else {
				gtnSqlQueryEngine.executeInsertOrUpdateQuery(updatedeductionQuery, paramsUpdate, updateQueryTypes,
						session);
			}
		}
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(salesBasisQuery, params, types, session);
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(deductionQuery, params, types, session);
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(deleteTempQuery, deleteQueryParams, deleteQueryTypes, session);
	}
		public void updateNsfInfo(GtnUIFrameworkWebserviceRequest nsfRequest) throws GtnFrameworkGeneralException {

		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			GtnUIFrameworkNsfInfoBean nsfInfoBean = nsfRequest.getGtnWsNetSalesGeneralRequest().getnSfInfoBean();
			GtnWsGeneralRequest generalRequest = nsfRequest.getGtnWsGeneralRequest();
			NetSalesFormulaMaster formulaMaster = getNetSalesFormulaMasterModel(nsfInfoBean, generalRequest, session);
			session.saveOrUpdate(formulaMaster);
			getNfsInfo(session, generalRequest, nsfInfoBean.getSystemId(),true);
			tx.commit();

		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException(e.getMessage(), e);
		} finally {
			session.close();
		}
	}

	private NetSalesFormulaMaster getNetSalesFormulaMasterModel(GtnUIFrameworkNsfInfoBean nsfInfoBean,
			GtnWsGeneralRequest generalRequest, Session session) {
		NetSalesFormulaMaster nsf = new NetSalesFormulaMaster();
		Integer nsfSystemId = nsfInfoBean.getSystemId();
		if (nsfSystemId != null && nsfSystemId > 0) {
			nsf = session.load(NetSalesFormulaMaster.class, nsfSystemId);
		}
		nsf.setNetSalesFormulaId(nsfInfoBean.getFormulaId());
		nsf.setNetSalesFormulaNo(nsfInfoBean.getFormulaNo());
		nsf.setNetSalesFormulaName(nsfInfoBean.getFormulaName());
		nsf.setHelperTableByNetSalesFormulaCategory(getHelperTable(nsfInfoBean.getFormulaCategory(), session));
		nsf.setHelperTableByNetSalesFormulaType(getHelperTable(nsfInfoBean.getFormulaType(), session));
		nsf.setContractSelection(nsfInfoBean.getContractSelection());
		nsf.setCreatedBy(Integer.parseInt(generalRequest.getUserId()));
		nsf.setModifiedBy(Integer.parseInt(generalRequest.getUserId()));
		nsf.setInboundStatus('A');
		nsf.setModifiedDate(new Date());
		nsf.setCreatedDate(new Date());
		if (nsf.getContractSelection().startsWith("Existing")) {
			nsf.setCdrModel(
					nsfInfoBean.getNetSalesSid() == null ? null : getCdrModel(nsfInfoBean.getNetSalesSid(), session));
		} else {
			nsf.setCdrModel(null);
		}
		return nsf;
	}

	private HelperTable getHelperTable(Integer systemId, Session session) {
		return session.load(HelperTable.class, systemId == null ? new Integer(0) : systemId);
	}

	private CdrModel getCdrModel(Integer systemId, Session session) {
		return session.load(CdrModel.class, systemId);
	}

	public GtnUIFrameworkNsfInfoBean getNsfData(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			GtnUIFrameworkNsfInfoBean nsfInfoBean = gtnWsRequest.getGtnWsNetSalesGeneralRequest().getnSfInfoBean();
			Integer nsfSystemId = nsfInfoBean.getSystemId();
			NetSalesFormulaMaster nsfMaster = session.get(NetSalesFormulaMaster.class, nsfSystemId);
			nsfInfoBean.setFormulaId(nsfMaster.getNetSalesFormulaId());
			nsfInfoBean.setFormulaNo(nsfMaster.getNetSalesFormulaName());
			nsfInfoBean.setFormulaName(nsfMaster.getNetSalesFormulaName());
			nsfInfoBean.setFormulaType(nsfMaster.getHelperTableByNetSalesFormulaType().getHelperTableSid());
			nsfInfoBean.setFormulaCategory(nsfMaster.getHelperTableByNetSalesFormulaCategory() == null ? 0
					: nsfMaster.getHelperTableByNetSalesFormulaCategory().getHelperTableSid());
			nsfInfoBean.setContractSelection(nsfMaster.getContractSelection());
			nsfInfoBean
					.setNetSalesSid(nsfMaster.getCdrModel() == null ? null : nsfMaster.getCdrModel().getCdrModelSid());
			nsfInfoBean.setNetSalesRuleName(
					nsfMaster.getCdrModel() == null ? null : nsfMaster.getCdrModel().getRuleName());
			insertIntoTempTables(gtnWsRequest, session,
					GtnWsNsfCommonConstants.GTN_NSF_SALES_BASIS_TEMP_TABLE_INSERT_QUERY, nsfSystemId);
			insertIntoTempTables(gtnWsRequest, session,
					GtnWsNsfCommonConstants.GTN_NSF_DEDUCTIONS_TEMP_TABLE_INSERT_QUERY, nsfSystemId);
			tx.commit();
			return nsfInfoBean;
		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException("Error while executing query ", e);
		} finally {
			session.close();
		}
	}

	public int insertIntoTempTables(GtnUIFrameworkWebserviceRequest gtnWsRequest, Session session, String queryName,
			Integer nsfSystemId) throws GtnFrameworkGeneralException {
		try {
			String query = gtnWsSqlService.getQuery(queryName);
			GtnFrameworkDataType[] type = { GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.STRING,
					GtnFrameworkDataType.STRING, };
			Object[] params = { nsfSystemId, gtnWsRequest.getGtnWsGeneralRequest().getUserId(),
					gtnWsRequest.getGtnWsGeneralRequest().getSessionId() };
			return gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, params, type, session);
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException(e);
		}

	}

	@SuppressWarnings("unchecked")
	public void isCheckedRecord(GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		GtnWsNetSalesFormulaGeneralRequest gtnWsNsfUpdateRequest = gtnWsRequest.getGtnWsNetSalesGeneralRequest();
		GtnWsNsfUpdateBean nsfUpdateBean = gtnWsNsfUpdateRequest.getNsfUpdateBean();
		GtnWsGeneralRequest generalWSRequest = gtnWsRequest.getGtnWsGeneralRequest();
		String query = getPopulateValidationQuery(nsfUpdateBean);
		GtnFrameworkDataType[] type = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING, };
		Object[] params = { generalWSRequest.getUserId(), generalWSRequest.getSessionId() };
		List<Object[]> checkedRecords = executeQuery(query, params, type);
		int checkedRecordCount = Integer.parseInt((String.valueOf(checkedRecords.get(0))));
		nsfUpdateBean.setCheckRecordFlag(checkedRecordCount > 0);
	}

	public String getPopulateValidationQuery(GtnWsNsfUpdateBean nsfUpdateBean) {
		if (nsfUpdateBean.isSalesBasis()) {
			return gtnWsSqlService.getQuery("getSalesBasisPopulateValidationQuery");
		} else {
			return gtnWsSqlService.getQuery("getDeductionsPopulateValidationQuery");
		}

	}

	@SuppressWarnings("rawtypes")
	public List executeQuery(String sqlQuery, Object[] params, GtnFrameworkDataType[] type)
			throws GtnFrameworkGeneralException {
		return gtnSqlQueryEngine.executeSelectQuery(sqlQuery, params, type);
	}

	public void populateColumns(GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {

		GtnWsNetSalesFormulaGeneralRequest gtnWsNsfUpdateRequest = gtnWsRequest.getGtnWsNetSalesGeneralRequest();
		GtnWsNsfUpdateBean nsfUpdateBean = gtnWsNsfUpdateRequest.getNsfUpdateBean();

		GtnWsGeneralRequest generalWSRequest = gtnWsRequest.getGtnWsGeneralRequest();

		Object[] params = { generalWSRequest.getUserId(), generalWSRequest.getSessionId() };
		String query;
		if (nsfUpdateBean.isSalesBasis()) {
			query = getSalesBasisColumnUpdateQuery(nsfUpdateBean, "getNsfSalesBasisUpdateColumnQuery",
					"IMTD_SALES_BASIS_DETAILS_SID");
		} else {
			query = getSalesBasisColumnUpdateQuery(nsfUpdateBean, "getNsfDeductionsUpdateColumnQuery",
					"IMTD_DEDUCTION_DETAILS_SID");
		}
		GtnFrameworkDataType[] types = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING };
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, params, types);

	}

	private String getSalesBasisColumnUpdateQuery(GtnWsNsfUpdateBean nsfUpdateBean, String quesyName,
			String idColumnName) {

		StringBuilder sql = new StringBuilder();
		String query = gtnWsSqlService.getQuery(quesyName);
		String columnName = getColumnName(nsfUpdateBean);

		query = query.replace("[$UPDATE_CLAUSE]", columnName);
		query = query.replace("[$UPDATE_CLAUSE_VALUE]", getUpdateValue(nsfUpdateBean, columnName));
		String subQuery = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		if (nsfUpdateBean.getMasterSid() != 0) {
			subQuery += " AND " + idColumnName + " = '" + nsfUpdateBean.getMasterSid() + "' ";
		}
		if (nsfUpdateBean.isPopulate()) {
			subQuery += " AND CHECK_RECORD = 1 ";
		}
		query = query.replace("[$WHERE_CLAUSE]", subQuery);
		sql.append(query);
		return sql.toString();

	}

	private String getUpdateValue(GtnWsNsfUpdateBean nsfUpdateBean, String columnName) {
		if ("CDR_MODEL_SID".equals(columnName.trim()) && !nsfUpdateBean.isPopulate()) {
			return "( select CDR_MODEL_SID from CDR_MODEL where RULE_NO LIKE '" + nsfUpdateBean.getValue() + "' )";
		}
		return "'" + nsfUpdateBean.getValue() + "'";
	}

	public String getColumnName(GtnWsNsfUpdateBean nsfUpdateBean) {

		String propertyId = nsfUpdateBean.getPropertyId();
		String columnName;
		switch (propertyId) {
		case GtnFrameworkCommonConstants.CHECK_RECORD_ID:
			columnName = " CHECK_RECORD";
			break;
		case "ruleNo":
		case "netSalesRuleNo":
		case "ruleName":
		case "netSalesRuleName":
			columnName = " CDR_MODEL_SID ";
			break;
		case "ruleSid":
			columnName = " CDR_MODEL_SID";
			break;
		case "indicator":
			columnName = " INDICATOR";
			changeColumnValuetoChar(nsfUpdateBean);
			break;
		default:
			columnName = GtnFrameworkCommonStringConstants.STRING_EMPTY;
			break;

		}
		return columnName;
	}

	private void changeColumnValuetoChar(GtnWsNsfUpdateBean nsfUpdateBean) {
		if ("Add".equals(nsfUpdateBean.getValue()) || "+".equals(nsfUpdateBean.getValue())) {
			nsfUpdateBean.setValue('+');
		} else {
			nsfUpdateBean.setValue('-');
		}

	}

	public void removeRecords(GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		GtnWsNetSalesFormulaGeneralRequest gtnWsNsfUpdateRequest = gtnWsRequest.getGtnWsNetSalesGeneralRequest();
		GtnWsNsfUpdateBean nsfUpdateBean = gtnWsNsfUpdateRequest.getNsfUpdateBean();
		GtnWsGeneralRequest generalWSRequest = gtnWsRequest.getGtnWsGeneralRequest();
		String query = getRemoveRecordQuery(nsfUpdateBean);
		GtnFrameworkDataType[] type = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING, };
		Object[] params = { generalWSRequest.getUserId(), generalWSRequest.getSessionId() };
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, params, type);
	}
	

	public String getRemoveRecordQuery(GtnWsNsfUpdateBean nsfUpdateBean) {
		if (nsfUpdateBean.isSalesBasis()) {
			return gtnWsSqlService.getQuery("getSalesBasisRemoveRecordQuery");
		} else {
			return gtnWsSqlService.getQuery("getDeductionsRemoveRecordQuery");
		}

	}
	
	public void deleteNsfRecord(GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		GtnWsNetSalesFormulaGeneralRequest gtnWsNetSalesGeneralRequest = gtnWsRequest.getGtnWsNetSalesGeneralRequest();
		GtnUIFrameworkNsfInfoBean nSfInfoBean = gtnWsNetSalesGeneralRequest.getnSfInfoBean();
		String nsfDeleteQuery = getNsfDeleteQuery();
		GtnFrameworkDataType[] type = { GtnFrameworkDataType.INTEGER };
		Object[] params = { nSfInfoBean.getSystemId() };
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(nsfDeleteQuery, params, type);

	}

	private String getNsfDeleteQuery() {
		return gtnWsSqlService.getQuery("getNsfDeleteQuery");
	}

	public void resetTables(GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		GtnWsNetSalesFormulaGeneralRequest gtnWsNsfResetRequest = gtnWsRequest.getGtnWsNetSalesGeneralRequest();
		GtnWsNsfUpdateBean nsfResetBean = gtnWsNsfResetRequest.getNsfUpdateBean();
		GtnWsGeneralRequest generalWSRequest = gtnWsRequest.getGtnWsGeneralRequest();
		String query = getResetRecordQuery(nsfResetBean);
		GtnFrameworkDataType[] type = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING, };
		Object[] params = { generalWSRequest.getUserId(), generalWSRequest.getSessionId() };
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, params, type);
	}

	public String getResetRecordQuery(GtnWsNsfUpdateBean nsfUpdateBean) {
		if (nsfUpdateBean.isSalesBasis()) {
			return gtnWsSqlService.getQuery("getNsfResetSalesBasisTableQuery");
		} else {
			return gtnWsSqlService.getQuery("getNsfResetDeductionTableQuery");
		}

	}

	public int checkAllItems(GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {

		String query = gtnWsSqlService.getQuery("getNSFItemsCheckAllQuery");
		boolean isCheckAll = (boolean) gtnWsRequest.getGtnWsNetSalesGeneralRequest().getnSfInfoBean().getValue();

		Object[] params = { isCheckAll ? "1" : "0", gtnWsRequest.getGtnWsGeneralRequest().getUserId(),
				gtnWsRequest.getGtnWsGeneralRequest().getSessionId() };
		GtnFrameworkDataType[] types = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING };
		return gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, params, types);

	}
}
