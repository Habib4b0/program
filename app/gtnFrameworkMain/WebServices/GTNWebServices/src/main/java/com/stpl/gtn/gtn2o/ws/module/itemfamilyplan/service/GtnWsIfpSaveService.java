package com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.GtnFileNameUtils;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.companymaster.Attachment;
import com.stpl.gtn.gtn2o.ws.entity.itemfamilyplan.IfpModel;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanCommonUpdateBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanInformationBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import com.stpl.gtn.gtn2o.ws.util.GtnWsCommonQueryContants;

/**
 *
 * @author Karthikeyan.Subraman
 */
@Service()
@Scope(value = "singleton")
public class GtnWsIfpSaveService {
	public GtnWsIfpSaveService() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsIfpSaveService.class);

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;
	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public int saveCfpQuery(GtnIFamilyPlanBean gtnIFamilyPlan) throws GtnFrameworkGeneralException {
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			IfpModel masterData = generateIfpModel(gtnIFamilyPlan, session);
			Integer cfpSystemId = (Integer) session.save(masterData);
			tx.commit();
			return cfpSystemId;
		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException(e.getMessage(), e);
		} finally {
			session.close();
		}
	}

	private IfpModel generateIfpModel(GtnIFamilyPlanBean gtnIFamilyPlan, Session session) {
		GtnIFamilyPlanInformationBean info = gtnIFamilyPlan.getIfpInfo();
		int userId = Integer.parseInt(info.getCreatedBy().trim());
		IfpModel ifpModel = new IfpModel();
		ifpModel.setIfpId(info.getIfpId());
		ifpModel.setIfpNo(info.getIfpNo());
		ifpModel.setIfpName(info.getIfpName());
		ifpModel.setHelperTableByIfpType(getHelperTable(info.getIfpType(), session));
		ifpModel.setHelperTableByIfpCategory(getHelperTable(info.getIfpCategory(), session));
		ifpModel.setHelperTableByIfpDesignation(getHelperTable(info.getIfpDesignation(), session));
		ifpModel.setHelperTableByIfpStatus(getHelperTable(info.getIfpStatus(), session));
		ifpModel.setIfpStartDate(info.getIfpStartDate());
		ifpModel.setIfpEndDate(info.getIfpEndDate());
		ifpModel.setParentIfpId(info.getParentIfpId());
		ifpModel.setParentIfpName(info.getParentIfpName());
		ifpModel.setInboundStatus('A');
		ifpModel.setRecordLockStatus(false);
		ifpModel.setSource("GTN");
		ifpModel.setCreatedBy(userId);
		ifpModel.setCreatedDate(new Date());
		ifpModel.setModifiedBy(userId);
		ifpModel.setModifiedDate(new Date());
		ifpModel.setInternalNotes(info.getInternalNotes());
		return ifpModel;
	}

	private HelperTable getHelperTable(Integer systemId, Session session) {
		return session.load(HelperTable.class, systemId == null ? new Integer(0) : systemId);
	}

	public void updateCfpQuery(GtnIFamilyPlanBean bean) throws GtnFrameworkGeneralException {
		GtnIFamilyPlanInformationBean info = bean.getIfpInfo();
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		int userId = Integer.parseInt(info.getCreatedBy().trim());
		try {
			IfpModel updateIfpModel = session.get(IfpModel.class, info.getIfpSid());
			updateIfpModel.setIfpId(info.getIfpId());
			updateIfpModel.setIfpNo(info.getIfpNo());
			updateIfpModel.setIfpName(info.getIfpName());
			updateIfpModel.setHelperTableByIfpType(getHelperTable(info.getIfpType(), session));
			updateIfpModel.setHelperTableByIfpCategory(getHelperTable(info.getIfpCategory(), session));
			updateIfpModel.setHelperTableByIfpDesignation(getHelperTable(info.getIfpDesignation(), session));
			updateIfpModel.setHelperTableByIfpStatus(getHelperTable(info.getIfpStatus(), session));
			updateIfpModel.setIfpStartDate(info.getIfpStartDate());
			updateIfpModel.setIfpEndDate(info.getIfpEndDate());
			updateIfpModel.setParentIfpId(info.getParentIfpId());
			updateIfpModel.setParentIfpName(info.getParentIfpName());
			updateIfpModel.setModifiedBy(userId);
			updateIfpModel.setModifiedDate(new Date());
			updateIfpModel.setInternalNotes(info.getInternalNotes());
			session.saveOrUpdate(updateIfpModel);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException(e.getMessage(), e);

		} finally {
			session.close();
		}
	}

	public String saveCfpDetailsQuery() {

		return gtnWsSqlService.getQuery("getIfpDetailsSaveQuery");

	}

	public String updateFieldsQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		String query = gtnWsSqlService.getQuery("getIfpItemsTabUpdateFieldsQuery");
		GtnIFamilyPlanCommonUpdateBean bean = gtnWsRequest.getGtnWsIfpRequest().getGtnIFamilyPlan().getUpdateBean();
		String column = getItemsTabUpdateColumns(bean.getColumnName());
		Object value = bean.getValue();
		String type = bean.getClassType();
		value = "java.util.Date".equals(type) ? new Timestamp((long) value) : value;
		query = query.replace("[$UPDATE_CLAUSE]", column + " = '" + value.toString().trim() + "'");
		if (bean.getImtdIfpDetailsSid() != null) {
			query = query.replace("[$SYSTEM_ID_CLAUSE]",
					"AND IMTD_IFP_DETAILS_SID = '" + bean.getImtdIfpDetailsSid() + "'");
		} else {
			query = query.replace("[$SYSTEM_ID_CLAUSE]", GtnFrameworkCommonStringConstants.STRING_EMPTY);
		}

		return query;

	}

	private String getItemsTabUpdateColumns(String property) {
		String itemsTabUpdateColumnName;
		switch (property) {

		case "itemFamilyPlanStatus":
			itemsTabUpdateColumnName = "IFP_DETAILS_ATTACHED_STATUS";
			break;
		case "itemFamilyPlanStartDate":
			itemsTabUpdateColumnName = "IFP_DETAILS_START_DATE";
			break;
		case "itemFamilyPlanEndDate":
			itemsTabUpdateColumnName = "IFP_DETAILS_END_DATE";
			break;
		case "checkRecordId":
			itemsTabUpdateColumnName = "CHECK_BOX";
			break;
		default:
			itemsTabUpdateColumnName = GtnFrameworkCommonStringConstants.STRING_EMPTY;
			break;
		}
		return itemsTabUpdateColumnName;
	}

	public void saveNotesTabDetails(GtnIFamilyPlanBean ruleInfoBean) throws GtnFrameworkGeneralException {
		deleteNotesTab(ruleInfoBean.getIfpInfo().getIfpSid());
		deleteNotesTabAttachment(ruleInfoBean.getIfpInfo().getIfpSid());
		notesTabInsert(ruleInfoBean);
		notesTabAttachmentForIfp(ruleInfoBean);
	}

	private int deleteNotesTab(int systemId) throws GtnFrameworkGeneralException {
		String deleteQuery = GtnWsCommonQueryContants.GTN_COMMON_NOTE_TAB_DELETE + systemId
				+ " AND MASTER_TABLE_NAME='IFP_MODEL'";
		return gtnSqlQueryEngine.executeInsertOrUpdateQuery(deleteQuery);
	}

	private int notesTabInsert(GtnIFamilyPlanBean ifpBean) throws GtnFrameworkGeneralException {
		logger.info("Enter Ifp notesTabInsert");
		List<NotesTabBean> notesTabRequestList = ifpBean.getNotesTabList();
		if (notesTabRequestList != null && !notesTabRequestList.isEmpty()) {
			StringBuilder cmNotesTabQuery = new StringBuilder(
					GtnWsCommonQueryContants.GTN_COMMON_NOTE_TAB_INSERT + "VALUES ");
			int i = 0;
			for (NotesTabBean notesTabRequest : notesTabRequestList) {
				if (i == 0) {
					cmNotesTabQuery.append(" (").append(ifpBean.getIfpInfo().getIfpSid()).append(",'")
							.append(notesTabRequest.getMasterTableName()).append("','")
							.append(notesTabRequest.getFilePath()).append("',").append("GETDATE(),")
							.append(notesTabRequest.getCreatedBy()).append(')');
				} else {
					cmNotesTabQuery.append(",(").append(ifpBean.getIfpInfo().getIfpSid()).append(",'")
							.append(notesTabRequest.getMasterTableName()).append("','")
							.append(notesTabRequest.getFilePath()).append("',").append("GETDATE(),")
							.append(notesTabRequest.getCreatedBy()).append(')');
				}
				i++;
			}
			logger.info("Exit ifp notesTabInsert");
			return gtnSqlQueryEngine.executeInsertOrUpdateQuery(cmNotesTabQuery.toString());
		}
		return 0;
	}

	private void notesTabAttachmentForIfp(GtnIFamilyPlanBean ifpBean) throws GtnFrameworkGeneralException {
		logger.info("Enter ifp notesTabAttachment");
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Attachment attachForIfp = new Attachment();
			List<NotesTabBean> notesTabRequestList = ifpBean.getNotesTabList();
			if (notesTabRequestList != null && !notesTabRequestList.isEmpty()) {
				for (NotesTabBean notesTabRequest : notesTabRequestList) {
					byte[] fileBytes = readBytesFromFileForIFP(notesTabRequest.getFilePath());
					attachForIfp.setAttachmentTableSid(ifpBean.getIfpInfo().getIfpSid());
					attachForIfp.setFileName(notesTabRequest.getFileName());
					attachForIfp.setFileData(fileBytes);
					attachForIfp.setMasterTableName(notesTabRequest.getMasterTableName());
					attachForIfp.setCreatedBy(notesTabRequest.getCreatedBy());
					attachForIfp.setCreatedDate(new Date());
					session.saveOrUpdate(attachForIfp);
					tx.commit();
				}
			}
		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException("Exception in save attachQuery ", e);
		} finally {
			session.close();
		}
	}

	private static byte[] readBytesFromFileForIFP(String filePath) throws IOException {
		File inputFile = GtnFileNameUtils.getFile(filePath);
        FileInputStream inputStreamIfp= GtnFileNameUtils.getFileInputStreamFile(inputFile);
        byte[] fileBytes = new byte[(int) inputFile.length()];
        int i=inputStreamIfp.read(fileBytes);
        if(i>0) 
        return fileBytes;
		return  new byte[0];
	}

	private int deleteNotesTabAttachment(int systemId) throws GtnFrameworkGeneralException {
		String deleteQuery = GtnWsCommonQueryContants.GTN_COMMON_NOTE_TAB_ATTACHMENT_DELETE + systemId
				+ " AND MASTER_TABLE_NAME='IFP_MODEL'";
		return gtnSqlQueryEngine.executeInsertOrUpdateQuery(deleteQuery);
	}
}
