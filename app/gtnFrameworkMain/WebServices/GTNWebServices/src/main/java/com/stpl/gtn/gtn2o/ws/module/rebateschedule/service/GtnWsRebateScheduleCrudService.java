package com.stpl.gtn.gtn2o.ws.module.rebateschedule.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.GtnFileNameUtils;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsCheckAllUpdateBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.config.GtnWsAllListConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsColumnDetailsConfig;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.cdr.CdrModel;
import com.stpl.gtn.gtn2o.ws.entity.companymaster.Udcs;
import com.stpl.gtn.gtn2o.ws.entity.rebateschedule.RsModel;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.rebateschedule.GtnWsRebateScheduleInfoBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import com.stpl.gtn.gtn2o.ws.util.GtnCommonUtil;
import com.stpl.gtn.gtn2o.ws.util.GtnWsQueryConstants;

@Service()
@Scope(value = "singleton")
public class GtnWsRebateScheduleCrudService {
	public GtnWsRebateScheduleCrudService() {
		/**
		 * empty constructor
		 */
	}

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsRebateScheduleCrudService.class);

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsAllListConfig gtnWebServiceAllListConfig;

	public int saveRsInformation(GtnWsRebateScheduleInfoBean rsInfoBean,
			GtnUIFrameworkWebserviceRequest generalWSRequest) throws GtnFrameworkGeneralException {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			RsModel rsModel = generateRsModel(rsInfoBean, generalWSRequest.getGtnWsGeneralRequest(), session);
			Integer cfpSystemId = (Integer) session.save(rsModel);
			tx.commit();
			return cfpSystemId;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tx.rollback();
			throw new GtnFrameworkGeneralException("Exception in saveCfpQuery ", e);
		} finally {
			session.close();
		}
	}

	private RsModel generateRsModel(GtnWsRebateScheduleInfoBean rsInfoBean, GtnWsGeneralRequest gtnWsGeneralRequest,
			Session session) {
		String userId = gtnWsGeneralRequest.getUserId();
		RsModel rsModel = new RsModel();
		rsModel.setRsId(rsInfoBean.getRebateScheduleId());
		rsModel.setRsNo(rsInfoBean.getRebateScheduleNo());
		rsModel.setRsName(rsInfoBean.getRebateScheduleName());
		rsModel.setHelperTableByRsType(getHelperTable(rsInfoBean.getRebateScheduleType(), session));
		rsModel.setHelperTableByRebateProgramType(getHelperTable(rsInfoBean.getRebateProgramType(), session));
		rsModel.setHelperTableByRsCategory(getHelperTable(rsInfoBean.getRebateScheduleCategory(), session));
		rsModel.setHelperTableByRsStatus(getHelperTable(rsInfoBean.getRebateScheduleStatus(), session));
		rsModel.setHelperTableByRsDesignation(getHelperTable(rsInfoBean.getRebateScheduleDesignation(), session));
		rsModel.setRsStartDate(rsInfoBean.getRebateScheduleStartDate());
		rsModel.setRsEndDate(rsInfoBean.getRebateScheduleEndDate());
		rsModel.setHelperTableByRsTradeClass(getHelperTable(rsInfoBean.getRebateScheduleTradeClass(), session));
		rsModel.setParentRsId(rsInfoBean.getParentRebateScheduleID());
		rsModel.setParentRsName(rsInfoBean.getParentRebateScheduleName());
		rsModel.setRsTransRefId(rsInfoBean.getRsTransactionRefId());
		rsModel.setRsTransRefNo(rsInfoBean.getRsTransactionRefName());
		rsModel.setRsTransRefName(rsInfoBean.getRsTransactionRefName());
		rsModel.setHelperTableByRebateRuleType(getHelperTable(rsInfoBean.getRebateRuleType(), session));
		rsModel.setHelperTableByInterestBearingIndicator(
				getHelperTable(rsInfoBean.getInterestBearingIndicator(), session));
		rsModel.setHelperTableByInterestBearingBasis(getHelperTable(rsInfoBean.getInterestBearingBasis(), session));
		rsModel.setHelperTableByRebateFrequency(getHelperTable(rsInfoBean.getRebateFrequency(), session));
		rsModel.setHelperTableByPaymentMethod(getHelperTable(rsInfoBean.getPaymentMethod(), session));
		rsModel.setHelperTableByPaymentFrequency(getHelperTable(rsInfoBean.getPaymentFrequency(), session));
		rsModel.setHelperTableByPaymentTerms(getHelperTable(rsInfoBean.getPaymentTerms(), session));
		rsModel.setPaymentGracePeriod(rsInfoBean.getPaymentGracePeriod());
		rsModel.setHelperTableByRsCalendar(getHelperTable(rsInfoBean.getRsCalendar(), session));
		rsModel.setInternalNotes(rsInfoBean.getInternalNotes());
		rsModel.setRsAlias(rsInfoBean.getRebateScheduleAliasId());
		rsModel.setHelperTableByCalculationType(getHelperTable(rsInfoBean.getCalculationType(), session));
		rsModel.setHelperTableByCalculationLevel(getHelperTable(rsInfoBean.getCalculationLevel(), session));
		rsModel.setInboundStatus('A');
		rsModel.setHelperTableByPaymentLevel(getHelperTable(rsInfoBean.getPaymentLevel(), session));
		rsModel.setCdrModelByCalculationRule(getCdrModel(rsInfoBean.getCalculationRuleSid(), session));
		rsModel.setHelperTableByCalculationRuleLevel(getHelperTable(rsInfoBean.getCalculationRuleLevel(), session));
		rsModel.setHelperTableByEvaluationRuleType(getHelperTable(rsInfoBean.getEvaluationRuleType(), session));
		rsModel.setHelperTableByEvaluationRuleLevel(getHelperTable(rsInfoBean.getEvaluationRuleLevel(), session));
		rsModel.setCdrModelByEvaluationRuleOrAssociation(
				getCdrModel(rsInfoBean.getEvaluationRuleAssociationSid(), session));
		rsModel.setHelperTableByDeductionInclusion(getHelperTable(rsInfoBean.getRsDeductionInclusion(), session));
		rsModel.setCreatedBy(Integer.valueOf(userId));
		rsModel.setCreatedDate(new Date());
		rsModel.setModifiedBy(Integer.valueOf(userId));
		rsModel.setModifiedDate(new Date());
		return rsModel;
	}

	private HelperTable getHelperTable(Integer systemId, Session session) {
		return session.load(HelperTable.class, systemId == null ? 0 : systemId);
	}

	private CdrModel getCdrModel(Integer systemId, Session session) {
		return session.load(CdrModel.class, systemId == null ? 0 : systemId);
	}

	public int checkAllItems(GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {

		String query = gtnWsSqlService.getQuery("getRSItemsCheckAllQuery");
		boolean isCheckAll = (boolean) gtnWsRequest.getGtnWsRebateScheduleGeneralRequest().getRebateScheduleInfoBean()
				.getValue();

		Object[] params = { isCheckAll ? "1" : "0", gtnWsRequest.getGtnWsGeneralRequest().getUserId(),
				gtnWsRequest.getGtnWsGeneralRequest().getSessionId() };
		GtnFrameworkDataType[] types = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING };
		return gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, params, types);

	}

	public boolean rebateScheduleSave(GtnWsRebateScheduleInfoBean rsInfoBean, String userId, String sessionId)
			throws GtnFrameworkGeneralException {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			if (rsInfoBean.getSystemId() != 0) {
				getRsDetailsDeleteQuery(rsInfoBean.getSystemId(), session);
				notesTabDelete(rsInfoBean.getSystemId(), session);
				notesTabAttachDelete(rsInfoBean.getSystemId(), session);
				if (rsInfoBean.getNoteBeanList() != null && !rsInfoBean.getNoteBeanList().isEmpty()) {
					rsNotesTabInsert(rsInfoBean, session);
					rsNotesTabAttachInsert(rsInfoBean, session);
				}
				rsUpdate(userId, rsInfoBean, session);
				rebateScheduleDetailsSaveInsert(rsInfoBean.getSystemId(), userId, sessionId, session);

			} else {

				String rsId = rebateScheduleCheckInsertUpdate(rsInfoBean.getRebateScheduleId());
				if (!rsId.isEmpty()) {
					rsInfoBean.setSystemId(Integer.valueOf(rsId));
					rsUpdate(userId, rsInfoBean, session);
				} else {

					rsInsertToRSModel(userId, rsInfoBean, session);
					Query query = session.createSQLQuery("SELECT @@IDENTITY");
					Object id = query.uniqueResult();
					rsInfoBean.setSystemId(((BigDecimal) id).intValue());

				}
				rebateScheduleDetailsSaveInsert(rsInfoBean.getSystemId(), userId, sessionId, session);

				if (rsInfoBean.getNoteBeanList() != null && !rsInfoBean.getNoteBeanList().isEmpty()) {
					rsNotesTabInsert(rsInfoBean, session);
					rsNotesTabAttachInsert(rsInfoBean, session);
				}
			}
			saveOrUpdateUdcs(rsInfoBean, session);
			transaction.commit();
			return true;

		} catch (Exception ex) {
			transaction.rollback();
			throw new GtnFrameworkGeneralException("Error in executing query : ", ex);
		} finally {
			session.close();
		}

	}

	private Integer rsNotesTabInsert(GtnWsRebateScheduleInfoBean rsInfoBean, Session session)
			throws GtnFrameworkGeneralException {
		List<NotesTabBean> rsNotesTabRequestList = rsInfoBean.getNoteBeanList();
		StringBuilder rsNoteTabFinalQuery = new StringBuilder();
		List<GtnFrameworkDataType> rsNotesQueryDataTypeList = new ArrayList<>();
		List<Object> rsNotesQueryParamList = new ArrayList<>();
		for (NotesTabBean notesTabRequest : rsNotesTabRequestList) {
			rsNoteTabFinalQuery.append(GtnWsQueryConstants.NOTES_INSERT);

			rsNotesQueryDataTypeList.add(GtnFrameworkDataType.INTEGER);
			rsNotesQueryParamList.add(rsInfoBean.getSystemId());

			rsNotesQueryDataTypeList.add(GtnFrameworkDataType.STRING);
			rsNotesQueryParamList.add(notesTabRequest.getMasterTableName());

			rsNotesQueryDataTypeList.add(GtnFrameworkDataType.STRING);
			rsNotesQueryParamList.add(notesTabRequest.getFilePath());

			rsNotesQueryDataTypeList.add(GtnFrameworkDataType.INTEGER);
			rsNotesQueryParamList.add(notesTabRequest.getCreatedBy());

		}
		if (rsNoteTabFinalQuery.length() > 0) {
			return gtnSqlQueryEngine.executeInsertOrUpdateQuery(rsNoteTabFinalQuery.toString(),
					rsNotesQueryParamList.toArray(),
					rsNotesQueryDataTypeList.toArray(new GtnFrameworkDataType[rsNotesQueryDataTypeList.size()]),
					session);
		}

		return 0;
	}

	private void rsUpdate(String userId, GtnWsRebateScheduleInfoBean rsInfoBean, Session session)
			throws GtnFrameworkGeneralException {

		String rsmodelUpdateQuery = gtnWsSqlService.getQuery("getRsModelUpdateQuery");
		List<Object> rsQueryParamList = new ArrayList<>(getRsModelQueryParams(rsInfoBean, userId));
		rsQueryParamList.add(rsInfoBean.getSystemId());
		Object[] rsmodelUpdateQueryParams = rsQueryParamList.toArray();
		List<GtnFrameworkDataType> rsQueryTypeList = new ArrayList<>(getRsModelQueryTypes());
		rsQueryTypeList.add(GtnFrameworkDataType.INTEGER);
		GtnFrameworkDataType[] rsmodelUpdateQueryTypes = rsQueryTypeList
				.toArray(new GtnFrameworkDataType[rsQueryTypeList.size()]);
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(rsmodelUpdateQuery, rsmodelUpdateQueryParams,
				rsmodelUpdateQueryTypes, session);

	}

	private void rebateScheduleDetailsSaveInsert(int rsmodelSid, String userId, String sessionId, Session session)
			throws GtnFrameworkGeneralException {
		String rsDetailsInsertQuery = gtnWsSqlService.getQuery("getRsDetailsInsertQuery");
		Object[] rsDetailsInsertQueryParams = { rsmodelSid, userId, sessionId };
		GtnFrameworkDataType[] rsDetailsInsertQueryTypes = { GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING };
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(rsDetailsInsertQuery, rsDetailsInsertQueryParams,
				rsDetailsInsertQueryTypes, session);
	}

	private void rsInsertToRSModel(String userId, GtnWsRebateScheduleInfoBean rsInfoBean, Session session)
			throws GtnFrameworkGeneralException {
		String rsmodelInsertQuery = gtnWsSqlService.getQuery("getRsModelInsertQuery");
		List<Object> rsInsertQueryParamList = new ArrayList<>(getRsModelQueryParams(rsInfoBean, userId));
		Object[] rsmodelInsertQueryParams = rsInsertQueryParamList.toArray();
		List<GtnFrameworkDataType> rsInsertQueryTypeList = new ArrayList<>(getRsModelQueryTypes());
		GtnFrameworkDataType[] rsmodelInsertQueryTypes = rsInsertQueryTypeList
				.toArray(new GtnFrameworkDataType[rsInsertQueryTypeList.size()]);
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(rsmodelInsertQuery, rsmodelInsertQueryParams,
				rsmodelInsertQueryTypes, session);

	}

	private void notesTabDelete(int systemId, Session session) throws GtnFrameworkGeneralException {
		String notesTabDeleteQuery = gtnWsSqlService.getQuery("getnotesTabDeleteQuery");
		Object[] notesTabDeleteQueryParams = { systemId };
		GtnFrameworkDataType[] notesTabDeleteQueryTypes = { GtnFrameworkDataType.INTEGER };
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(notesTabDeleteQuery, notesTabDeleteQueryParams,
				notesTabDeleteQueryTypes, session);
	}
	
	private void notesTabAttachDelete(int systemId, Session session) throws GtnFrameworkGeneralException {
		String notesTabAttachDeleteQuery = gtnWsSqlService.getQuery("getNotesTabAttachDeleteQuery");
		Object[] notesTabDeleteQueryParams = { systemId };
		GtnFrameworkDataType[] notesTabAttachDeleteQueryTypes = { GtnFrameworkDataType.INTEGER };
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(notesTabAttachDeleteQuery, notesTabDeleteQueryParams,
				notesTabAttachDeleteQueryTypes, session);
	}

	@SuppressWarnings("unchecked")
	private String rebateScheduleCheckInsertUpdate(String rsId) throws GtnFrameworkGeneralException {
		String rsDuplicateChcekQuery = gtnWsSqlService.getQuery("getRsDuplicateCheckQuery");
		String rsModelSid = StringUtils.EMPTY;
		Object[] rsDuplicateChcekQueryParams = { rsId };
		GtnFrameworkDataType[] rsDuplicateChcekQueryTypes = { GtnFrameworkDataType.STRING };
		List<Integer> rsInfo = (List<Integer>) gtnSqlQueryEngine.executeSelectQuery(rsDuplicateChcekQuery,
				rsDuplicateChcekQueryParams, rsDuplicateChcekQueryTypes);
		if (rsInfo != null && !rsInfo.isEmpty()) {
			rsModelSid = rsInfo.get(0).toString();
		}
		return rsModelSid;

	}

	private void getRsDetailsDeleteQuery(int systemId, Session session) throws GtnFrameworkGeneralException {

		String rsDetailsDeleteQuery = gtnWsSqlService.getQuery("getRsDetailsDeleteQuery");
		Object[] reDetailsDeleteQueryParams = { systemId };
		GtnFrameworkDataType[] rsDetailsDeleteQueryTypes = { GtnFrameworkDataType.INTEGER };
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(rsDetailsDeleteQuery, reDetailsDeleteQueryParams,
				rsDetailsDeleteQueryTypes, session);

	}

	private List<Object> getRsModelQueryParams(GtnWsRebateScheduleInfoBean rsInfoBean, String userId) {
		SimpleDateFormat formatter = new SimpleDateFormat(GtnFrameworkWebserviceConstant.YYYY_M_MDD_H_HMMSS);
		int userIdAsInt = Integer.parseInt(userId);

		return Arrays.asList(new Object[] { rsInfoBean.getRebateScheduleId(), rsInfoBean.getRebateScheduleNo(),
				rsInfoBean.getRebateScheduleName(), rsInfoBean.getRebateScheduleType(),
				rsInfoBean.getRebateProgramType(), rsInfoBean.getRebateScheduleCategory(),
				rsInfoBean.getRebateScheduleStatus(), rsInfoBean.getRebateScheduleDesignation(),
				rsInfoBean.getRebateScheduleStartDate() == null ? null
						: formatter.format(rsInfoBean.getRebateScheduleStartDate()),
				rsInfoBean.getRebateScheduleEndDate() == null ? null
						: formatter.format(rsInfoBean.getRebateScheduleEndDate()),
				rsInfoBean.getRebateScheduleTradeClass(), rsInfoBean.getParentRebateScheduleSid(),
				rsInfoBean.getParentRebateScheduleName(), rsInfoBean.getRsTransactionReSid(),
				rsInfoBean.getRsTransactionRefName(), rsInfoBean.getRsTransactionRefName(),
				rsInfoBean.getRebateRuleType(), rsInfoBean.getInterestBearingIndicator(),
				rsInfoBean.getInterestBearingBasis(), rsInfoBean.getRebateFrequency(), rsInfoBean.getPaymentMethod(),
				rsInfoBean.getPaymentFrequency(), rsInfoBean.getPaymentTerms(), rsInfoBean.getPaymentGracePeriod(),
				rsInfoBean.getRsCalendar(), rsInfoBean.getInternalNotes(), rsInfoBean.getRebateScheduleAliasId(),
				rsInfoBean.getCalculationType(), rsInfoBean.getCalculationLevel(), rsInfoBean.getPaymentLevel(),
				rsInfoBean.getCalculationRuleSid(), rsInfoBean.getCalculationRuleLevel(),
				rsInfoBean.getEvaluationRuleType(), rsInfoBean.getEvaluationRuleLevel(),
				rsInfoBean.getEvaluationRuleAssociationSid(), rsInfoBean.getRsDeductionInclusion(), userIdAsInt,
				userIdAsInt });

	}

	private List<GtnFrameworkDataType> getRsModelQueryTypes() {
		return Arrays.asList(new GtnFrameworkDataType[] { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING, GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER,
				GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER,
				GtnFrameworkDataType.STRING, GtnFrameworkDataType.NULL_ALLOWED, GtnFrameworkDataType.INTEGER,
				GtnFrameworkDataType.NULL_ALLOWED, GtnFrameworkDataType.STRING, GtnFrameworkDataType.NULL_ALLOWED,
				GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING, GtnFrameworkDataType.NULL_ALLOWED,
				GtnFrameworkDataType.NULL_ALLOWED, GtnFrameworkDataType.NULL_ALLOWED, GtnFrameworkDataType.NULL_ALLOWED,
				GtnFrameworkDataType.NULL_ALLOWED, GtnFrameworkDataType.NULL_ALLOWED, GtnFrameworkDataType.NULL_ALLOWED,
				GtnFrameworkDataType.STRING, GtnFrameworkDataType.NULL_ALLOWED, GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING, GtnFrameworkDataType.NULL_ALLOWED, GtnFrameworkDataType.NULL_ALLOWED,
				GtnFrameworkDataType.NULL_ALLOWED, GtnFrameworkDataType.NULL_ALLOWED, GtnFrameworkDataType.NULL_ALLOWED,
				GtnFrameworkDataType.NULL_ALLOWED, GtnFrameworkDataType.NULL_ALLOWED, GtnFrameworkDataType.NULL_ALLOWED,
				GtnFrameworkDataType.NULL_ALLOWED, GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER });

	}

	public void rebateScheduleInsert(Map<String, String> inputValueMap) throws GtnFrameworkGeneralException {
		String userId = inputValueMap.get("userId");
		String sessionId = inputValueMap.get("sessionId");
		deleteTemptableRecords(userId, sessionId);

		String imtdRsDetailsInsertQuery = gtnWsSqlService.getQuery("getImtdRsDetailsInsertQuery");
		Object[] imtdRsDetailsInsertQueryParams = { userId, sessionId, Integer.valueOf(inputValueMap.get("ifpId")) };
		GtnFrameworkDataType[] imtdRsDetailsInsertQueryTypes = { GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING, GtnFrameworkDataType.INTEGER };
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(imtdRsDetailsInsertQuery, imtdRsDetailsInsertQueryParams,
				imtdRsDetailsInsertQueryTypes);

	}

	public void updateColumn(Map<String, GtnWsColumnDetailsConfig> componetMap, GtnWsCheckAllUpdateBean rsUpdateBean,
			GtnWsGeneralRequest generalWSRequest) throws GtnFrameworkGeneralException {
		String imtdRsDetailsUpdateQuery = gtnWsSqlService.getQuery("getImtdRsDetailsUpdateQuery");
		String updateValue;
		if (componetMap.get(rsUpdateBean.getPropertyId()).getDataType().equals("Date")
				&& rsUpdateBean.getValue() != null) {
			String formatedDate = GtnCommonUtil.getFormatedDate(rsUpdateBean);
			updateValue = formatedDate;
		} else {
			updateValue = String.valueOf(rsUpdateBean.getValue());
		}
		String columnName = componetMap.get(rsUpdateBean.getPropertyId()).getDbColumnName();
		Object[] imtdRsDetailsUpdateQueryParams = { columnName, updateValue, rsUpdateBean.getMasterSid(),
				generalWSRequest.getUserId(), generalWSRequest.getSessionId() };
		GtnFrameworkDataType[] imtdRsDetailsUpdateQueryTypes = { GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING, GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING };
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(imtdRsDetailsUpdateQuery, imtdRsDetailsUpdateQueryParams,
				imtdRsDetailsUpdateQueryTypes);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GtnWsRebateScheduleInfoBean getRsInfoBean(int systemId) throws GtnFrameworkGeneralException {
		GtnWsRebateScheduleInfoBean rsInfoBean = new GtnWsRebateScheduleInfoBean();
		String rsInfoQuery = gtnWsSqlService.getQuery("getRsInfoQuery");
		Object[] rsInfoQueryParams = { systemId };
		GtnFrameworkDataType[] rsInfoQueryTypes = { GtnFrameworkDataType.INTEGER };

		List<Object[]> rsInfoList = (List) gtnSqlQueryEngine.executeSelectQuery(rsInfoQuery, rsInfoQueryParams,
				rsInfoQueryTypes);
		if (rsInfoList != null) {
			rsInfoBean = setRsInfoBean(rsInfoList.get(0));
			getUdcsValue(rsInfoBean, systemId);
			rsInfoBean.setNoteBeanList(getRsNotesTabDetails(systemId));
			rsInfoBean.setNoteBeanList(getRsNotesTabAttachDetails(systemId));
		}
		return rsInfoBean;

	}

	private GtnWsRebateScheduleInfoBean setRsInfoBean(Object[] result) {

		GtnWsRebateScheduleInfoBean rsInfoBean = new GtnWsRebateScheduleInfoBean();

		rsInfoBean.setRebateScheduleId(getStringValue(result[0]));
		rsInfoBean.setRebateScheduleNo(getStringValue(result[1]));
		rsInfoBean.setRebateScheduleName(getStringValue(result[2]));
		rsInfoBean.setRebateScheduleType(getIntValue(result[3]));
		rsInfoBean.setRebateProgramType(getIntValue(result[4]));
		rsInfoBean.setRebateScheduleCategory(getIntValue(result[5]));
		rsInfoBean.setRebateScheduleStatus(getIntValue(result[6]));
		rsInfoBean.setRebateScheduleDesignation(getIntValue(result[7]));
		rsInfoBean.setRebateScheduleStartDate((Date) result[8]);
		rsInfoBean.setRebateScheduleEndDate((Date) result[9]);
		rsInfoBean.setRebateScheduleTradeClass(getIntValue(result[10]));
		rsInfoBean.setParentRebateScheduleID(getStringValue(result[11]));
		rsInfoBean.setParentRebateScheduleName(getStringValue(result[12]));
		rsInfoBean.setRsTransactionRefId(getStringValue(result[13]));
		rsInfoBean.setRsTransactionRefName(getStringValue(result[14]));
		rsInfoBean.setRsTransactionRefName(getStringValue(result[15]));
		rsInfoBean.setRebateRuleType(getIntValue(result[16]));
		rsInfoBean.setInterestBearingIndicator(getIntValue(result[17]));
		rsInfoBean.setInterestBearingBasis(getIntValue(result[18]));
		rsInfoBean.setRebateFrequency(getIntValue(result[19]));
		rsInfoBean.setPaymentMethod(getIntValue(result[20]));
		rsInfoBean.setPaymentFrequency(getIntValue(result[21]));
		rsInfoBean.setPaymentTerms(getIntValue(result[22]));
		rsInfoBean.setPaymentGracePeriod(getStringValue(result[23]));
		rsInfoBean.setRsCalendar(getIntValue(result[24]));
		rsInfoBean.setInternalNotes(getStringValue(result[25]));
		rsInfoBean.setRebateScheduleAliasId(getStringValue(result[26]));
		rsInfoBean.setCalculationType(getIntValue(result[27]));
		rsInfoBean.setCalculationLevel(getIntValue(result[28]));
		rsInfoBean.setPaymentLevel(getIntValue(result[29]));
		rsInfoBean.setCalculationRule(getStringValue(result[30]));
		rsInfoBean.setCalculationRuleLevel(getIntValue(result[31]));
		rsInfoBean.setEvaluationRuleType(getIntValue(result[32]));
		rsInfoBean.setEvaluationRuleLevel(getIntValue(result[33]));
		rsInfoBean.setEvaluationRuleAssociationSid(getIntValue(result[34]));
		rsInfoBean.setRsDeductionInclusion(getIntValue(result[35]));

		return rsInfoBean;

	}

	@SuppressWarnings("unchecked")
	private List<NotesTabBean> getRsNotesTabDetails(int systemId) throws GtnFrameworkGeneralException {

		String rsNoteDetailsQuery = gtnWsSqlService.getQuery("getRsNoteDetailsQuery");
		Object[] rsNoteDetailsQueryParams = { systemId };
		GtnFrameworkDataType[] rsNoteDetailsQueryTypes = { GtnFrameworkDataType.INTEGER };

		List<Object[]> rsNotesDetailsList = (List) gtnSqlQueryEngine.executeSelectQuery(rsNoteDetailsQuery,
				rsNoteDetailsQueryParams, rsNoteDetailsQueryTypes);
		return GtnCommonUtil.getNotesTabBean(rsNotesDetailsList, gtnWebServiceAllListConfig);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<NotesTabBean> getRsNotesTabAttachDetails(int systemId) throws GtnFrameworkGeneralException {

		String rsNoteAttachDetailsQuery = gtnWsSqlService.getQuery("rsNotesTabAttachDetailsQuery");
		Object[] rsNoteAttachDetailsQueryParams = { systemId };
		GtnFrameworkDataType[] rsNoteAttachDetailsQueryTypes = { GtnFrameworkDataType.INTEGER };

		List<Object[]> rsNotesAttachDetailsList = (List) gtnSqlQueryEngine.executeSelectQuery(rsNoteAttachDetailsQuery,
				rsNoteAttachDetailsQueryParams, rsNoteAttachDetailsQueryTypes);
		return GtnCommonUtil.getNotesTabBean(rsNotesAttachDetailsList, gtnWebServiceAllListConfig);
	}

	private int getIntValue(Object obj) {
		int value = 0;
		if (obj != null && !getStringValue(obj).equals("") && !getStringValue(obj).equals(" ")) {
			value = Integer.parseInt(getStringValue(obj));
		}
		return value;
	}

	private String getStringValue(Object obj) {
		return String.valueOf(obj);
	}

	@SuppressWarnings("unchecked")
	public List<Object> getIFPInfoBean(int rsModelSid) throws GtnFrameworkGeneralException {
		String ifpInfoQuery = gtnWsSqlService.getQuery("getIfpInfoQuery");
		Object[] ifpInfoQueryParams = { rsModelSid };
		GtnFrameworkDataType[] ifpInfoQueryTypes = { GtnFrameworkDataType.INTEGER };

		List<Object[]> ifpInfoList = (List) gtnSqlQueryEngine.executeSelectQuery(ifpInfoQuery, ifpInfoQueryParams,
				ifpInfoQueryTypes);

		if (ifpInfoList != null) {
			return setIFPInfoBean(ifpInfoList.get(0));
		}

		return Collections.emptyList();
	}

	private List<Object> setIFPInfoBean(Object[] result) {
		List<Object> valueList = new ArrayList<>();
		for (Object value : result) {
			valueList.add(value == null ? value : getStringValue(value));
		}
		return valueList;
	}

	public void rebateScheduleEditInsert(int rsmodelSid, String userId, String sessionId)
			throws GtnFrameworkGeneralException {
		deleteTemptableRecords(userId, sessionId);

		String rsEditInsertMainToTempQuery = gtnWsSqlService.getQuery("getRsEditInsertMainToTempQuery");
		Object[] rsEditInsertMainToTempQueryParams = { userId, sessionId, rsmodelSid };
		GtnFrameworkDataType[] rsEditInsertMainToTempQueryTypes = { GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING, GtnFrameworkDataType.INTEGER };
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(rsEditInsertMainToTempQuery, rsEditInsertMainToTempQueryParams,
				rsEditInsertMainToTempQueryTypes);
	}

	private void deleteTemptableRecords(String userId, String sessionId) throws GtnFrameworkGeneralException {
		String imtdRsDetailsDeleteQuery = gtnWsSqlService.getQuery("getImtdRsDetailsDeleteQuery");
		Object[] imtdRsDetailsDeleteQueryParams = { userId, sessionId };
		GtnFrameworkDataType[] imtdRsDetailsDeleteQueryTypes = { GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING };
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(imtdRsDetailsDeleteQuery, imtdRsDetailsDeleteQueryParams,
				imtdRsDetailsDeleteQueryTypes);
	}

	@SuppressWarnings("unchecked")
	public String deleteRebateScheduleRecord(int sysId) throws GtnFrameworkGeneralException {
		Session session = sessionFactory.openSession();
		Transaction transction = session.beginTransaction();
		try {
			String rsContractCountQuery = gtnWsSqlService.getQuery("getRsContractCountQuery");
			Object[] rsContractCountQueryParams = { sysId };
			GtnFrameworkDataType[] rsContractCountQueryTypes = { GtnFrameworkDataType.INTEGER };
			List<Integer> list = (List<Integer>) gtnSqlQueryEngine.executeSelectQuery(rsContractCountQuery,
					rsContractCountQueryParams, rsContractCountQueryTypes);
			if (list.get(0) == 0) {
				notesTabDelete(sysId, session);
				getRsDetailsDeleteQuery(sysId, session);
				transction.commit();
			} else {
				return "Fail";
			}
		} catch (Exception ex) {
			transction.rollback();
			throw new GtnFrameworkGeneralException("Error in deleteRebateScheduleRecord query : ", ex);

		} finally {
			session.close();
		}
		return "Sucess";
	}

	private void saveOrUpdateUdcs(GtnWsRebateScheduleInfoBean rsInfoBean, Session session)
			throws GtnFrameworkGeneralException {
		if ((rsInfoBean.getRsUDC1() != null && rsInfoBean.getRsUDC1() == 0)
				&& (rsInfoBean.getRsUDC2() != null && rsInfoBean.getRsUDC2() == 0)
				&& (rsInfoBean.getRsUDC3() != null && rsInfoBean.getRsUDC3() == 0)
				&& (rsInfoBean.getRsUDC4() != null && rsInfoBean.getRsUDC4() == 0)
				&& (rsInfoBean.getRsUDC5() != null && rsInfoBean.getRsUDC5() == 0)
				&& (rsInfoBean.getRsUDC6() != null && rsInfoBean.getRsUDC6() == 0)) {
			return;
		}
		List<Integer> results = getRebateUdcsList(rsInfoBean);

		Udcs udcSave = new Udcs();
		if (results != null && !results.isEmpty() && results.get(0) > 0) {
			udcSave = session.get(Udcs.class, results.get(0));
		}
		udcSave.setMasterSid(rsInfoBean.getSystemId());
		udcSave.setMasterType("RS_MODEL");
		udcSave.setHelperTableByUdc1(getHelperTable(rsInfoBean.getRsUDC1(), session));
		udcSave.setHelperTableByUdc2(getHelperTable(rsInfoBean.getRsUDC2(), session));
		udcSave.setHelperTableByUdc3(getHelperTable(rsInfoBean.getRsUDC3(), session));
		udcSave.setHelperTableByUdc4(getHelperTable(rsInfoBean.getRsUDC4(), session));
		udcSave.setHelperTableByUdc5(getHelperTable(rsInfoBean.getRsUDC5(), session));
		udcSave.setHelperTableByUdc6(getHelperTable(rsInfoBean.getRsUDC6(), session));
		session.saveOrUpdate(udcSave);
	}

	@SuppressWarnings("unchecked")
	private List<Integer> getRebateUdcsList(GtnWsRebateScheduleInfoBean rsInfoBean)
			throws GtnFrameworkGeneralException {
		Criterion rebateSidCriterion = Restrictions.eq("masterSid", rsInfoBean.getSystemId());
		Criterion masterTypeCriterion = Restrictions.eq("masterType", "RS_MODEL");
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.distinct(Projections.property("udcsSid")));
		return (List<Integer>) gtnSqlQueryEngine.executeSelectQuery(Udcs.class,
				Arrays.asList(rebateSidCriterion, masterTypeCriterion), projectionList);

	}

	@SuppressWarnings("unchecked")
	private void getUdcsValue(GtnWsRebateScheduleInfoBean rsInfoBean, int systemId)
			throws GtnFrameworkGeneralException {
		String rsInfoQuery = gtnWsSqlService.getQuery("getRsUdcDetailsQuery");
		Object[] rsInfoQueryParams = { systemId };
		GtnFrameworkDataType[] rsInfoQueryTypes = { GtnFrameworkDataType.INTEGER };

		List<Object[]> rsUdcList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(rsInfoQuery, rsInfoQueryParams,
				rsInfoQueryTypes);
		if (rsUdcList != null && !rsUdcList.isEmpty()) {
			Object[] result = rsUdcList.get(0);
			rsInfoBean.setRsUDC1(getIntValue(result[0]));
			rsInfoBean.setRsUDC2(getIntValue(result[1]));
			rsInfoBean.setRsUDC3(getIntValue(result[2]));
			rsInfoBean.setRsUDC4(getIntValue(result[3]));
			rsInfoBean.setRsUDC5(getIntValue(result[4]));
			rsInfoBean.setRsUDC6(getIntValue(result[5]));

		}
	}
	
	private Integer rsNotesTabAttachInsert(GtnWsRebateScheduleInfoBean rsInfoBean, Session session)
			throws GtnFrameworkGeneralException {
		List<NotesTabBean> rsNotesTabRequestList = rsInfoBean.getNoteBeanList();
		StringBuilder rsNoteTabAttachFinalQuery = new StringBuilder();
		List<GtnFrameworkDataType> rsNotesQueryDataTypeList = new ArrayList<>();
		List<Object> rsNotesQueryParamList = new ArrayList<>();
		for (NotesTabBean notesTabRequest : rsNotesTabRequestList) {
			rsNoteTabAttachFinalQuery.append(GtnWsQueryConstants.NOTES_ATTACH_INSERT);

			rsNotesQueryDataTypeList.add(GtnFrameworkDataType.INTEGER);
			rsNotesQueryParamList.add(rsInfoBean.getSystemId());

			rsNotesQueryDataTypeList.add(GtnFrameworkDataType.STRING);
			rsNotesQueryParamList.add(notesTabRequest.getFileName());

			rsNotesQueryDataTypeList.add(GtnFrameworkDataType.BYTE);
			try {
				rsNotesQueryParamList.add(readBytesFromFile(notesTabRequest.getFilePath()));
			} catch (IOException e) {
				throw new GtnFrameworkGeneralException("Error in rsNotesTabAttachInsert query : ", e);	
			}

			rsNotesQueryDataTypeList.add(GtnFrameworkDataType.STRING);
			rsNotesQueryParamList.add(notesTabRequest.getMasterTableName());
			
			rsNotesQueryDataTypeList.add(GtnFrameworkDataType.INTEGER);
			rsNotesQueryParamList.add(notesTabRequest.getCreatedBy());

		}
		if (rsNoteTabAttachFinalQuery.length() > 0) {
			return gtnSqlQueryEngine.executeInsertOrUpdateQuery(rsNoteTabAttachFinalQuery.toString(),
					rsNotesQueryParamList.toArray(),
					rsNotesQueryDataTypeList.toArray(new GtnFrameworkDataType[rsNotesQueryDataTypeList.size()]),
					session);
		}
		return 0;
	}
	 private static byte[] readBytesFromFile(String filePath) throws IOException {
		 File inputFile = GtnFileNameUtils.getFile(filePath);
	        FileInputStream inputStreamRs= GtnFileNameUtils.getFileInputStreamFile(inputFile);
	        byte[] fileBytes = new byte[(int) inputFile.length()];
	        int i=inputStreamRs.read(fileBytes);
	        if(i>0) 
	        return fileBytes;
			return  new byte[0];
	    }

}
