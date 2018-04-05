package com.stpl.gtn.gtn2o.ws.module.priceschedule.service;

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

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.priceschedule.bean.GtnUIFrameWorkPSInfoBean;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import com.stpl.gtn.gtn2o.ws.util.GtnCommonUtil;
import com.stpl.gtn.gtn2o.ws.util.GtnWsQueryConstants;

@Service()
@Scope(value = "singleton")
public class GtnWsPriceScheduleService {
	public GtnWsPriceScheduleService() {
		/**
		 * empty constructor
		 */
	}

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;
	@Autowired
	private GtnWsAllListConfig gtnWebServiceAllListConfig;

	public void priceScheduleInsert(Map<String, String> inputValueMap) throws GtnFrameworkGeneralException {
		String psUserId = inputValueMap.get(GtnFrameworkWebserviceConstant.USER_ID);
		String psSessionId = inputValueMap.get("sessionId");
		String psId = inputValueMap.get("ps_id");
		deletePsTemptableRecords(psUserId, psSessionId);

		String imtdPsDetailsInsertQuery = gtnWsSqlService.getQuery("getImtdPsDetailsInsertQuery");
		Object[] imtdPsDetailsInsertQueryParams = { psUserId, psSessionId, Integer.valueOf(inputValueMap.get("ifpId")),
				psId };
		GtnFrameworkDataType[] imtdPsDetailsInsertQueryTypes = { GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING, GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.STRING };
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(imtdPsDetailsInsertQuery, imtdPsDetailsInsertQueryParams,
				imtdPsDetailsInsertQueryTypes);

	}

	private void deletePsTemptableRecords(String userId, String sessionId) throws GtnFrameworkGeneralException {
		String imtdPsDetailsDeleteQuery = gtnWsSqlService.getQuery("getImtdPsDetailsDeleteQuery");
		Object[] imtdPsDetailsDeleteQueryParams = { userId, sessionId };
		GtnFrameworkDataType[] imtdPsDetailsDeleteQueryTypes = { GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING };
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(imtdPsDetailsDeleteQuery, imtdPsDetailsDeleteQueryParams,
				imtdPsDetailsDeleteQueryTypes);
	}

	public void priceScheduleEditInsert(int psmodelSid, String psUserId, String psSessionId)
			throws GtnFrameworkGeneralException {

		deletePsTemptableRecords(psUserId, psSessionId);

		String psEditInsertMainToTempQuery = gtnWsSqlService.getQuery("getPsEditInsertMainToTempQuery");
		Object[] psEditInsertMainToTempQueryParams = { psUserId, psSessionId, psmodelSid };
		GtnFrameworkDataType[] psEditInsertMainToTempQueryTypes = { GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING, GtnFrameworkDataType.INTEGER };
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(psEditInsertMainToTempQuery, psEditInsertMainToTempQueryParams,
				psEditInsertMainToTempQueryTypes);

	}

	public void priceScheduleSave(GtnUIFrameWorkPSInfoBean psInfoBean, String userId, String sessionId)
			throws GtnFrameworkGeneralException {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			if (psInfoBean.getSystemId() != 0) {
				deletePsDetails(psInfoBean.getSystemId(), session);
				notesTabDelete(psInfoBean.getSystemId(), session);
				notesTabAttachDelete(psInfoBean.getSystemId(), session);
				psUpdateToPSModel(userId, psInfoBean, session);
			} else {

				psSaveToPSModel(userId, psInfoBean, session);
				Query query = session.createSQLQuery("SELECT @@IDENTITY");
				Object id = query.uniqueResult();
				psInfoBean.setSystemId(((BigDecimal) id).intValue());

			}
			psSaveInsertToPSDetails(psInfoBean.getSystemId(), userId, sessionId, session);
			if (psInfoBean.getNoteBeanList() != null && !psInfoBean.getNoteBeanList().isEmpty()) {
				psNotesTabInsert(psInfoBean, session);
				psNotesTabAttachInsert(psInfoBean, session);
			}
			transaction.commit();
		} catch (Exception ex) {
			transaction.rollback();
			throw new GtnFrameworkGeneralException(ex);
		} finally {
			session.close();
		}
	}

	public void priceScheduleInsertTemp(List<Object> inputValueList,
			GtnUIFrameworkWebserviceResponse gtnPsProtectionUpdateResponse) throws GtnFrameworkGeneralException {
		String psUserId = inputValueList.get(0).toString();
		String psSessionId = inputValueList.get(1).toString();
		String msg = "checkRecord";
		String addCopyDeleteQuery = inputValueList.get(2).toString();
		Object[] imtdPsDetailsInsertQueryParams = { psUserId, psSessionId };
		GtnFrameworkDataType[] imtdPsDetailsInsertQueryTypes = { GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING };

		List<Object> psCountList = getResultValue("getAddCopyDeleteLineAlertCountQuery", imtdPsDetailsInsertQueryParams,
				imtdPsDetailsInsertQueryTypes);

		if ((Integer) psCountList.get(0) != 0) {
			msg = "unableToRemove";
			psCountList = getResultValue("deleteLineAlertCountQuery", imtdPsDetailsInsertQueryParams,
					imtdPsDetailsInsertQueryTypes);
		}
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(gtnWsSqlService.getQuery(addCopyDeleteQuery),
				imtdPsDetailsInsertQueryParams, imtdPsDetailsInsertQueryTypes);
		gtnPsProtectionUpdateResponse.setOutBountData(new Object[] { psCountList.get(0), msg });

	}

	public void priceProtectionStartDateAlert(List<Object> inputValueList,
			GtnUIFrameworkWebserviceResponse gtnPsProtectionUpdateResponse) throws GtnFrameworkGeneralException {
		String psUserId = inputValueList.get(0).toString();
		String psSessionId = inputValueList.get(1).toString();
		Object[] imtdPsDetailsInsertQueryParams = { psUserId, psSessionId };
		GtnFrameworkDataType[] imtdPsDetailsInsertQueryTypes = { GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING };

		List<Object> psCountList = (List<Object>) gtnSqlQueryEngine.executeSelectQuery(
				gtnWsSqlService.getQuery("priceProtectionStartDateAlertQuery"), imtdPsDetailsInsertQueryParams,
				imtdPsDetailsInsertQueryTypes);

		gtnPsProtectionUpdateResponse.setOutBountData(new Object[] { psCountList.get(0) });

	}

	private void deletePsDetails(int systemId, Session session) throws GtnFrameworkGeneralException {
		String psDetailsDeleteQuery = gtnWsSqlService.getQuery("getPsDetailsDeleteQuery");
		Object[] psDetailsDeleteQueryParams = { systemId };
		GtnFrameworkDataType[] psDetailsDeleteQueryTypes = { GtnFrameworkDataType.INTEGER };
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(psDetailsDeleteQuery, psDetailsDeleteQueryParams,
				psDetailsDeleteQueryTypes, session);
	}

	private void notesTabDelete(int systemId, Session session) throws GtnFrameworkGeneralException {
		String rsNotesTabDeleteQuery = gtnWsSqlService.getQuery("getNotesTabDeleteQuery");
		Object[] rsNotesTabDeleteQueryParams = { systemId };
		GtnFrameworkDataType[] rsNotesTabDeleteQueryTypes = { GtnFrameworkDataType.INTEGER };
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(rsNotesTabDeleteQuery, rsNotesTabDeleteQueryParams,
				rsNotesTabDeleteQueryTypes, session);
	}
	

	private void notesTabAttachDelete(int systemId, Session session) throws GtnFrameworkGeneralException {
		String psNotesTabAttachDeleteQuery = gtnWsSqlService.getQuery("getNotesTabAttachDeleteQuery");
		Object[] psNotesTabAttachDeleteQueryParams = { systemId };
		GtnFrameworkDataType[] psNotesTabDeleteQueryTypes = { GtnFrameworkDataType.INTEGER };
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(psNotesTabAttachDeleteQuery, psNotesTabAttachDeleteQueryParams,
				psNotesTabDeleteQueryTypes, session);
	}

	private void psSaveInsertToPSDetails(int psmodelSid, String userId, String sessionId, Session session)
			throws GtnFrameworkGeneralException {

		String psDetailsInsertQuery = gtnWsSqlService.getQuery("getPsDetailsInsertQuery");
		Object[] psDetailsInsertQueryParams = { userId, sessionId, psmodelSid };
		GtnFrameworkDataType[] psDetailsInsertQueryTypes = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.INTEGER };
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(psDetailsInsertQuery, psDetailsInsertQueryParams,
				psDetailsInsertQueryTypes, session);

	}

	private void psSaveToPSModel(String userId, GtnUIFrameWorkPSInfoBean psInfoBean, Session session)
			throws GtnFrameworkGeneralException {

		String psmodelInsertQuery = gtnWsSqlService.getQuery("getPsModelInsertQuery");
		List<Object> psmodelInsertQueryParamsList = getPsModelQueryParams(psInfoBean, userId);
		Object[] psmodelInsertQueryParams = psmodelInsertQueryParamsList.toArray();
		List<GtnFrameworkDataType> rsmodelInsertQueryTypesList = getPsModelQueryTypes();
		GtnFrameworkDataType[] rsmodelInsertQueryTypes = rsmodelInsertQueryTypesList
				.toArray(new GtnFrameworkDataType[rsmodelInsertQueryTypesList.size()]);
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(psmodelInsertQuery, psmodelInsertQueryParams,
				rsmodelInsertQueryTypes, session);

	}

	private void psUpdateToPSModel(String userId, GtnUIFrameWorkPSInfoBean psInfoBean, Session session)
			throws GtnFrameworkGeneralException {
		String psmodelUpdateQuery = gtnWsSqlService.getQuery("getPsModelUpdateQuery");
		List<Object> psmodelUpdateQueryParamsList = new ArrayList<>(getPsModelQueryParams(psInfoBean, userId));
		psmodelUpdateQueryParamsList.add(psInfoBean.getSystemId());
		Object[] psmodelUpdateQueryParams = psmodelUpdateQueryParamsList.toArray();
		List<GtnFrameworkDataType> psmodelUpdateQueryTypesList = new ArrayList<>(getPsModelQueryTypes());
		psmodelUpdateQueryTypesList.add(GtnFrameworkDataType.INTEGER);
		GtnFrameworkDataType[] psmodelUpdateQueryTypes = psmodelUpdateQueryTypesList
				.toArray(new GtnFrameworkDataType[psmodelUpdateQueryTypesList.size()]);
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(psmodelUpdateQuery, psmodelUpdateQueryParams,
				psmodelUpdateQueryTypes, session);

	}

	private List<GtnFrameworkDataType> getPsModelQueryTypes() {
		return Arrays.asList(new GtnFrameworkDataType[] { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING, GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER,
				GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.STRING, GtnFrameworkDataType.INTEGER, });

	}

	private List<Object> getPsModelQueryParams(GtnUIFrameWorkPSInfoBean psInfoBean, String userId) {
		SimpleDateFormat formatter = new SimpleDateFormat(GtnFrameworkWebserviceConstant.YYYY_M_MDD_H_HMMSS);
		return Arrays.asList(new Object[] { psInfoBean.getPsId(), psInfoBean.getPsNo(), psInfoBean.getPsName(),
				psInfoBean.getPsType(), psInfoBean.getPsCategory(), psInfoBean.getPsStatus(),
				psInfoBean.getPsDesignation(),
				psInfoBean.getPsStartDate() == null ? null : formatter.format(psInfoBean.getPsStartDate()),
				psInfoBean.getPsEndDate() == null ? null : formatter.format(psInfoBean.getPsEndDate()),
				psInfoBean.getParentPsId(), psInfoBean.getParentpSNo(), psInfoBean.getPsTradeClass(),
				psInfoBean.getInternalNotes(), Integer.parseInt(userId) });
	}

	private Integer psNotesTabInsert(GtnUIFrameWorkPSInfoBean psInfoBean, Session session)
			throws GtnFrameworkGeneralException {
		List<NotesTabBean> psNotesTabRequestList = psInfoBean.getNoteBeanList();
		StringBuilder psNoteTabFinalQuery = new StringBuilder();
		List<GtnFrameworkDataType> psNotesQueryDataTypeList = new ArrayList<>();
		List<Object> psNotesQueryParamList = new ArrayList<>();
		for (NotesTabBean notesTabRequest : psNotesTabRequestList) {
			psNoteTabFinalQuery.append(GtnWsQueryConstants.NOTES_INSERT);

			psNotesQueryDataTypeList.add(GtnFrameworkDataType.INTEGER);
			psNotesQueryParamList.add(psInfoBean.getSystemId());

			psNotesQueryDataTypeList.add(GtnFrameworkDataType.STRING);
			psNotesQueryParamList.add(notesTabRequest.getMasterTableName());

			psNotesQueryDataTypeList.add(GtnFrameworkDataType.STRING);
			psNotesQueryParamList.add(notesTabRequest.getFilePath());

			psNotesQueryDataTypeList.add(GtnFrameworkDataType.INTEGER);
			psNotesQueryParamList.add(notesTabRequest.getCreatedBy());

		}
		if (psNoteTabFinalQuery.length() > 0) {
			return gtnSqlQueryEngine.executeInsertOrUpdateQuery(psNoteTabFinalQuery.toString(),
					psNotesQueryParamList.toArray(),
					psNotesQueryDataTypeList.toArray(new GtnFrameworkDataType[psNotesQueryDataTypeList.size()]),
					session);
		}
		return 0;
	}

	public void updateImtdPsDetailsColumns(Map<String, GtnWsColumnDetailsConfig> componetMap,
			GtnWsCheckAllUpdateBean psUpdateBean, GtnWsGeneralRequest generalRequest)
			throws GtnFrameworkGeneralException {
		String imtdPsDetailsUpdateQuery = gtnWsSqlService.getQuery("getImtdPsDetailsUpdateQuery");
		String updateValue;
		if (componetMap.get(psUpdateBean.getPropertyId()).getDataType().equals("Date")
				&& psUpdateBean.getValue() != null && !psUpdateBean.getValue().equals("NULL")) {
			String formatedDate = GtnCommonUtil.getFormatedDate(psUpdateBean);
			updateValue = formatedDate;
		} else if (componetMap.get(psUpdateBean.getPropertyId()).getDataType().equals("Date")
				&& psUpdateBean.getValue().equals("NULL")) {
			updateValue = null;
		} else {
			updateValue = String.valueOf(psUpdateBean.getValue());
		}
		String columnName = componetMap.get(psUpdateBean.getPropertyId()).getDbColumnName();
		Object[] imtdPsDetailsUpdateQueryParams = { columnName, updateValue, psUpdateBean.getMasterSid(),
				generalRequest.getUserId(), generalRequest.getSessionId() };
		GtnFrameworkDataType[] imtdPsDetailsUpdateQueryTypes = { GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING, GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING };
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(imtdPsDetailsUpdateQuery, imtdPsDetailsUpdateQueryParams,
				imtdPsDetailsUpdateQueryTypes);
	}

	public void deletePriceScheduleRecord(int sysId) throws GtnFrameworkGeneralException {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			deletePsDetails(sysId, session);
			notesTabDelete(sysId, session);
			notesTabAttachDelete(sysId, session);
			deletePsModel(sysId, session);
			transaction.commit();
		} catch (Exception ex) {
			transaction.rollback();
			throw new GtnFrameworkGeneralException("Exception while deleting ps record", ex);
		} finally {
			session.close();
		}

	}

	private void deletePsModel(int systemId, Session session) throws GtnFrameworkGeneralException {

		String psModelDeleteQuery = gtnWsSqlService.getQuery("getPsModelDeleteQuery");
		Object[] psModelDeleteQueryParams = { systemId };
		GtnFrameworkDataType[] psModelDeleteQueryTypes = { GtnFrameworkDataType.INTEGER };
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(psModelDeleteQuery, psModelDeleteQueryParams,
				psModelDeleteQueryTypes, session);
	}

	@SuppressWarnings("unchecked")
	public GtnUIFrameWorkPSInfoBean getPsInfoBean(int systemId) throws GtnFrameworkGeneralException {

		String psInfoQuery = gtnWsSqlService.getQuery("getPsInfoQuery");
		Object[] psInfoQueryParams = { systemId };
		GtnFrameworkDataType[] psInfoQueryTypes = { GtnFrameworkDataType.INTEGER };
		List<Object[]> rPInfo = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(psInfoQuery, psInfoQueryParams,
				psInfoQueryTypes);
		GtnUIFrameWorkPSInfoBean psInfoBean = null;
		if (rPInfo != null) {
			psInfoBean = setPsInfoBean(rPInfo.get(0));
			psInfoBean.setNoteBeanList(getPsNotesTabDetails(systemId));
			psInfoBean.setNoteBeanList(getPsNotesTabAttachDetails(systemId));
		}

		return psInfoBean;
	}

	@SuppressWarnings("unchecked")
	public List<Object> getIfpInfoBean(int systemId) throws GtnFrameworkGeneralException {

		String psIfpInfoQuery = gtnWsSqlService.getQuery("getPsIfpInfoQuery");
		Object[] psIfpInfoQueryParams = { systemId };
		GtnFrameworkDataType[] psIfpInfoQueryTypes = { GtnFrameworkDataType.INTEGER };
		List<Object[]> ifpInfoList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(psIfpInfoQuery,
				psIfpInfoQueryParams, psIfpInfoQueryTypes);
		if (ifpInfoList != null) {
			return setIFPInfoBean(ifpInfoList.get(0));

		}
		return Collections.emptyList();
	}

	@SuppressWarnings("unchecked")
	private List<NotesTabBean> getPsNotesTabDetails(int systemId) throws GtnFrameworkGeneralException {

		String psNotesTabDetailsQuery = gtnWsSqlService.getQuery("psNotesTabDetailsQuery");
		Object[] psNotesTabDetailsQueryParams = { systemId };
		GtnFrameworkDataType[] psInfoQueryTypes = { GtnFrameworkDataType.INTEGER };
		List<Object[]> psNotesDetailsResultList = (List<Object[]>) gtnSqlQueryEngine
				.executeSelectQuery(psNotesTabDetailsQuery, psNotesTabDetailsQueryParams, psInfoQueryTypes);
		return GtnCommonUtil.getNotesTabBean(psNotesDetailsResultList, gtnWebServiceAllListConfig);
	}
	
	@SuppressWarnings("unchecked")
	private List<NotesTabBean> getPsNotesTabAttachDetails(int systemId) throws GtnFrameworkGeneralException {

		String psNotesTabAttachDetailsQuery = gtnWsSqlService.getQuery("psNotesTabAttachDetailsQuery");
		Object[] psNotesTabAttachDetailsQueryParams = { systemId };
		GtnFrameworkDataType[] psInfoQueryTypes = { GtnFrameworkDataType.INTEGER };
		List<Object[]> psNotesDetailsResultList = (List<Object[]>) gtnSqlQueryEngine
				.executeSelectQuery(psNotesTabAttachDetailsQuery, psNotesTabAttachDetailsQueryParams, psInfoQueryTypes);
		return GtnCommonUtil.getNotesTabBean(psNotesDetailsResultList, gtnWebServiceAllListConfig);
	}

	private int getIntValue(Object obj) {
		int value = 0;
		if (obj != null && !getStringValue(obj).equals("") && !getStringValue(obj).equals(" ")) {
			value = Integer.parseInt(getStringValue(obj));
		}

		return value;
	}

	private String getStringValue(Object obj) {
		return obj == null ? "" : String.valueOf(obj);
	}

	private GtnUIFrameWorkPSInfoBean setPsInfoBean(Object[] result) {

		GtnUIFrameWorkPSInfoBean psInfoBean = new GtnUIFrameWorkPSInfoBean();

		psInfoBean.setPsId(getStringValue(result[0]));
		psInfoBean.setPsNo(getStringValue(result[1]));
		psInfoBean.setPsName(getStringValue(result[2]));
		psInfoBean.setParentpSNo(getStringValue(result[3]));
		int createdBy = getStringValue(result[4]) != null && !getStringValue(result[4]).isEmpty()
				? Integer.parseInt(getStringValue(result[4])) : 0;
		int modifiedBy = getStringValue(result[5]) != null && !getStringValue(result[5]).isEmpty()
				? Integer.parseInt(getStringValue(result[5])) : 0;
		psInfoBean.setCreatedBy(getStringValue(gtnWebServiceAllListConfig.getUserIdNameMap().get(createdBy)));
		psInfoBean.setModifiedBy(getStringValue(gtnWebServiceAllListConfig.getUserIdNameMap().get(modifiedBy)));
		psInfoBean.setParentPsSid(getStringValue(result[6]));
		psInfoBean.setParentPsId(getStringValue(result[7]));
		psInfoBean.setPsStatus(getIntValue(result[8]));
		psInfoBean.setPsDesignation(getIntValue(result[9]));
		psInfoBean.setPsType(getIntValue(result[10]));
		psInfoBean.setPsCategory(getIntValue(result[11]));
		psInfoBean.setPsTradeClass(getIntValue(result[12]));
		psInfoBean.setPsStartDate((Date) result[13]);
		psInfoBean.setPsEndDate((Date) result[14]);
		psInfoBean.setInternalNotes(
				result[15] != null ? getStringValue(result[15]) : GtnFrameworkCommonStringConstants.STRING_EMPTY);

		return psInfoBean;

	}

	private List<Object> setIFPInfoBean(Object[] result) {

		List<Object> valueList = new ArrayList<>();

		for (Object value : result) {
			valueList.add(value);
		}

		return valueList;

	}

	public StringBuilder getPopulateQuery(GtnWsCheckAllUpdateBean psPPUpdateBean, GtnWsGeneralRequest generalWSRequest,
			Map<String, GtnWsColumnDetailsConfig> componetMap, String propertyId, Object value) {

		StringBuilder psPPUpdateQuery = new StringBuilder(GtnFrameworkWebserviceConstant.UPDATE_IMTD_PS_DETAILS_SET);

		if (psPPUpdateBean.getPropertyValueMap() != null) {

			boolean isAdded = false;

			for (String key : psPPUpdateBean.getPropertyValueMap().keySet()) {
				if (isAdded) {
					psPPUpdateQuery.append(psPPUpdateQuery).append(',').append(componetMap.get(key).getDbColumnName())
							.append("='").append(psPPUpdateBean.getPropertyValueMap().get(key)).append("' ");

				} else {
					psPPUpdateQuery.append(componetMap.get(key).getDbColumnName()).append("='")
							.append(psPPUpdateBean.getPropertyValueMap().get(key)).append("' ");
					isAdded = true;
				}

			}

		} else {
			psPPUpdateQuery.append("").append(componetMap.get(propertyId).getDbColumnName()).append('=');

			psPPUpdateQuery.append(" '").append(value).append("' ");

		}

		psPPUpdateQuery.append(" WHERE  USERS_SID='").append(generalWSRequest.getUserId())
				.append(GtnFrameworkWebserviceConstant.AND_SESSION_ID).append(generalWSRequest.getSessionId())
				.append("' ");
		if (!psPPUpdateBean.isCheckAll()) {
			psPPUpdateQuery.append(" AND CHECK_RECORD='1' ");
		}
		return psPPUpdateQuery;
	}

	private List<Object> getResultValue(String query, Object[] imtdPsDetailsInsertQueryParams,
			GtnFrameworkDataType[] imtdPsDetailsInsertQueryTypes) throws GtnFrameworkGeneralException {
		String psQuery = gtnWsSqlService.getQuery(query);
		return (List<Object>) gtnSqlQueryEngine.executeSelectQuery(psQuery, imtdPsDetailsInsertQueryParams,
				imtdPsDetailsInsertQueryTypes);

	}
	private Integer psNotesTabAttachInsert(GtnUIFrameWorkPSInfoBean psInfoBean, Session session)
			throws GtnFrameworkGeneralException {
		List<NotesTabBean> psNotesTabRequestList = psInfoBean.getNoteBeanList();
		StringBuilder psNoteTabAttachFinalQuery = new StringBuilder();
		List<GtnFrameworkDataType> psNotesQueryDataTypeList = new ArrayList<>();
		List<Object> psNotesQueryParamList = new ArrayList<>();
		for (NotesTabBean notesTabRequest : psNotesTabRequestList) {
			psNoteTabAttachFinalQuery.append(GtnWsQueryConstants.NOTES_ATTACH_INSERT);

			psNotesQueryDataTypeList.add(GtnFrameworkDataType.INTEGER);
			psNotesQueryParamList.add(psInfoBean.getSystemId());

			psNotesQueryDataTypeList.add(GtnFrameworkDataType.STRING);
			psNotesQueryParamList.add(notesTabRequest.getFileName());

			psNotesQueryDataTypeList.add(GtnFrameworkDataType.BYTE);
			try {
				psNotesQueryParamList.add(readBytesFromFile(notesTabRequest.getFilePath()));
			} catch (IOException e) {
				throw new GtnFrameworkGeneralException(e);
			}

			psNotesQueryDataTypeList.add(GtnFrameworkDataType.STRING);
			psNotesQueryParamList.add(notesTabRequest.getMasterTableName());
			
			psNotesQueryDataTypeList.add(GtnFrameworkDataType.INTEGER);
			psNotesQueryParamList.add(notesTabRequest.getCreatedBy());

		}
		if (psNoteTabAttachFinalQuery.length() > 0) {
			return gtnSqlQueryEngine.executeInsertOrUpdateQuery(psNoteTabAttachFinalQuery.toString(),
					psNotesQueryParamList.toArray(),
					psNotesQueryDataTypeList.toArray(new GtnFrameworkDataType[psNotesQueryDataTypeList.size()]),
					session);
		}
		return 0;
		
	}
	 private static byte[] readBytesFromFile(String filePath) throws IOException {
		 File inputFile = GtnFileNameUtils.getFile(filePath);
	        FileInputStream inputStreamPs= GtnFileNameUtils.getFileInputStreamFile(inputFile);
	        byte[] fileBytes = new byte[(int) inputFile.length()];
	        int i=inputStreamPs.read(fileBytes);
	        if(i>0) 
	        return fileBytes;
			return  new byte[0];
	    }

}
