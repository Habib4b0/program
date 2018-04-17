package com.stpl.gtn.gtn2o.ws.module.companyfamilyplan.service;

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
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean.GtnCFamilyPlan;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean.GtnCFamilyPlanCommonUpdateBean;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean.GtnCFamilyPlanInformation;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.companyfamilyplan.CfpModel;
import com.stpl.gtn.gtn2o.ws.entity.companymaster.Attachment;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.companyfamilyplan.constants.GtnWsCfpQueryContants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import com.stpl.gtn.gtn2o.ws.util.GtnWsCommonQueryContants;

/**
 *
 * @author Karthikeyan.Subraman
 */
@Service()
@Scope(value = "singleton")
public class GtnWsCfpSaveService {
	public GtnWsCfpSaveService() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsCfpSaveService.class);

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public int saveCfpQuery(GtnCFamilyPlan bean, GtnUIFrameworkWebserviceRequest generalWSRequest)
			throws GtnFrameworkGeneralException {

		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			CfpModel masterData = generateCfpModel(bean, generalWSRequest.getGtnWsGeneralRequest(), session);
			Integer cfpSystemId = (Integer) session.save(masterData);
			tx.commit();
			return cfpSystemId;
		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException("Exception in saveCfpQuery ", e);
		} finally {
			session.close();
		}
	}

	private CfpModel generateCfpModel(GtnCFamilyPlan masterbean, GtnWsGeneralRequest generalWSRequest,
			Session session) {
		GtnCFamilyPlanInformation info = masterbean.getCfpInfo();
		int userId = Integer.parseInt(generalWSRequest.getUserId().trim());
		CfpModel cfpModel = new CfpModel();
		cfpModel.setHelperTableByCfpStatus(getHelperTable(info.getCfpStatus(), session));
		cfpModel.setHelperTableByCfpTradeClass(getHelperTable(info.getCfpTradeClass(), session));
		cfpModel.setHelperTableByCfpType(getHelperTable(info.getCfpType(), session));
		cfpModel.setHelperTableByCfpCategory(getHelperTable(info.getCfpCategory(), session));
		cfpModel.setCfpId(info.getCfpId());
		cfpModel.setCfpNo(info.getCfpNo());
		cfpModel.setCfpName(info.getCfpName());
		cfpModel.setCfpDesignation(String.valueOf(info.getCfpDesignation() == null ? "0" : info.getCfpDesignation()));
		cfpModel.setCfpStartDate(info.getCfpStartDate());
		cfpModel.setCfpEndDate(info.getCfpEndDate());
		cfpModel.setParentCfpId((info.getParentCfpId() == null || info.getParentCfpId().isEmpty()
				|| GtnFrameworkCommonStringConstants.STRING_NULL.equals(info.getParentCfpId())) ? null
						: Integer.valueOf(info.getParentCfpId()));
		cfpModel.setParentCfpName(info.getParentCfpName());
		cfpModel.setInternalNotes(info.getInternalNotes());
		cfpModel.setInboundStatus('A');
		cfpModel.setRecordLockStatus(false);
		cfpModel.setSource("GTN");
		cfpModel.setCreatedBy(userId);
		cfpModel.setCreatedDate(new Date());
		cfpModel.setModifiedBy(userId);
		cfpModel.setModifiedDate(new Date());
		cfpModel.setSalesInclusion(info.getSalesInclusion());
		return cfpModel;
	}

	private HelperTable getHelperTable(Integer systemId, Session session) {
		return session.load(HelperTable.class, systemId == null ? Integer.valueOf(0) : systemId);
	}

	public void updateCfpQuery(GtnUIFrameworkWebserviceRequest generalWSRequest) throws GtnFrameworkGeneralException {

		GtnCFamilyPlanInformation info = generalWSRequest.getGtnWsCfpRequest().getGtnCFamilyPlan().getCfpInfo();
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			CfpModel updateCfpModel = session.get(CfpModel.class, info.getCfpSid());
			updateCfpModel.setHelperTableByCfpStatus(getHelperTable(info.getCfpStatus(), session));
			updateCfpModel.setHelperTableByCfpTradeClass(getHelperTable(info.getCfpTradeClass(), session));
			updateCfpModel.setHelperTableByCfpType(getHelperTable(info.getCfpType(), session));
			updateCfpModel.setHelperTableByCfpCategory(getHelperTable(info.getCfpCategory(), session));
			updateCfpModel.setCfpId(info.getCfpId());
			updateCfpModel.setCfpNo(info.getCfpNo());
			updateCfpModel.setCfpName(info.getCfpName());
			updateCfpModel.setCfpDesignation(
					String.valueOf(info.getCfpDesignation() == null ? "0" : info.getCfpDesignation()));
			updateCfpModel.setCfpStartDate(info.getCfpStartDate());
			updateCfpModel.setCfpEndDate(info.getCfpEndDate());
			updateCfpModel.setParentCfpId((info.getParentCfpId() == null || info.getParentCfpId().isEmpty()
					|| GtnFrameworkCommonStringConstants.STRING_NULL.equals(info.getParentCfpId())) ? null
							: Integer.valueOf(info.getParentCfpId()));
			updateCfpModel.setParentCfpName(info.getParentCfpName());
			updateCfpModel.setInternalNotes(info.getInternalNotes());
			updateCfpModel.setModifiedBy(Integer.parseInt(generalWSRequest.getGtnWsGeneralRequest().getUserId()));
			updateCfpModel.setModifiedDate(new Date());
			updateCfpModel.setSalesInclusion(info.getSalesInclusion());
			session.saveOrUpdate(updateCfpModel);
			tx.commit();

		} catch (Exception e) {
			tx.rollback();
			throw new GtnFrameworkGeneralException("Exception in updateCfpQuery ", e);
		} finally {
			session.close();
		}

	}

	public String saveCfpDetailsQuery() throws GtnFrameworkGeneralException {
		try {
			return gtnWsSqlService.getQuery("getCfpDetailsSaveQuery");
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in saveCfpDetailsQuery ", e);
		}

	}

	public String updateFieldsQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		try {
			String query = gtnWsSqlService.getQuery("getCfpCompaniesTabUpdateFieldsQuery");
			GtnCFamilyPlanCommonUpdateBean bean = gtnWsRequest.getGtnWsCfpRequest().getGtnCFamilyPlan().getUpdateBean();
			String column = getCompaniesTabUpdateColumns(bean.getColumnName());
			Object value = bean.getValue();
			String type = bean.getClassType();
                        if(value != null){
			value = "java.util.Date".equals(type) ? new Timestamp((long) value) : value;
                        }
			value = value != null ? " '" +  value.toString().trim() + "'" : null;
			query = query.replace("[$UPDATE_CLAUSE]", column +" = "+  value );
			if (bean.getImtdCfpDetailsSid() != null) {
				query = query.replace("[$SYSTEM_ID_CLAUSE]",
						"AND IMTD_CFP_DETAILS_SID = '" + bean.getImtdCfpDetailsSid() + "'");
			} else {
				query = query.replace("[$SYSTEM_ID_CLAUSE]", GtnFrameworkCommonStringConstants.STRING_EMPTY);
			}

			return query;
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in updateFieldsQuery ", e);
		}

	}

	private String getCompaniesTabUpdateColumns(String property) {
		String cfpUpdateColumnName;
		switch (property) {

		case "companyFamilyPlanStatusValue":
			cfpUpdateColumnName = GtnWsCfpQueryContants.CFP_DETAILS_ATTACHED_STATUS;
			break;
		case "companyFamilyPlanStartDate":
			cfpUpdateColumnName = GtnWsCfpQueryContants.CFP_DETAILS_START_DATE;
			break;
		case "companyFamilyPlanEndDate":
			cfpUpdateColumnName = GtnWsCfpQueryContants.CFP_DETAILS_END_DATE;
			break;
		case "tradingPartnerContractNo":
			cfpUpdateColumnName = GtnWsCfpQueryContants.TRADING_PARTNER_CONTRACT_NO;
			break;
		case "modifiedBy":
			cfpUpdateColumnName = GtnWsCfpQueryContants.CFP_DETAILS_MODIFIED_BY;
			break;
		case "checkRecordId":
			cfpUpdateColumnName = GtnWsCfpQueryContants.CHECK_RECORD;
			break;
                case "CfpEligibleDate":
			cfpUpdateColumnName = GtnWsCfpQueryContants.CFP_ELIGIBLE_DATE;
			break;
		default:
			cfpUpdateColumnName = GtnFrameworkCommonStringConstants.STRING_EMPTY;
			break;
                        
		}
		return cfpUpdateColumnName;
	}

	public void saveNotesTabDetails(GtnCFamilyPlan ruleInfoBean) throws  GtnFrameworkGeneralException {
		deleteNotesTab(ruleInfoBean.getCfpInfo().getCfpSid());
		deleteNotesTabAttachment(ruleInfoBean.getCfpInfo().getCfpSid());
		notesTabInsert(ruleInfoBean);
		notesTabAttachmentCfp(ruleInfoBean);
	}

	private int deleteNotesTab(int systemId) throws GtnFrameworkGeneralException {
		String deleteQuery = GtnWsCommonQueryContants.GTN_COMMON_NOTE_TAB_DELETE + systemId
				+ " AND MASTER_TABLE_NAME='CFP_MODEL'";
		return gtnSqlQueryEngine.executeInsertOrUpdateQuery(deleteQuery);
	}

	private int notesTabInsert(GtnCFamilyPlan cfpBean) throws GtnFrameworkGeneralException {
		logger.info("Enter Cfp notesTabInsert");
		List<NotesTabBean> notesTabRequestList = cfpBean.getNotesTabList();
		StringBuilder cmNotesTabQuery = new StringBuilder();
		if (notesTabRequestList != null && !notesTabRequestList.isEmpty()) {
			cmNotesTabQuery.append(GtnWsCommonQueryContants.GTN_COMMON_NOTE_TAB_INSERT + "VALUES ");
			int i = 0;
			for (NotesTabBean notesTabRequest : notesTabRequestList) {
				if (i == 0) {
					cmNotesTabQuery.append(" (").append(cfpBean.getCfpInfo().getCfpSid()).append(",'")
							.append(notesTabRequest.getMasterTableName()).append("','")
							.append(notesTabRequest.getFilePath()).append("',").append("GETDATE(),")
							.append(notesTabRequest.getCreatedBy()).append(')');
				} else {
					cmNotesTabQuery.append(",(").append(cfpBean.getCfpInfo().getCfpSid()).append(",'")
							.append(notesTabRequest.getMasterTableName()).append("','")
							.append(notesTabRequest.getFilePath()).append("',").append("GETDATE(),")
							.append(notesTabRequest.getCreatedBy()).append(')');
				}
				i++;
			}
			return gtnSqlQueryEngine.executeInsertOrUpdateQuery(cmNotesTabQuery.toString());
		}
		logger.info("Exit cfp notesTabInsert");
		return 0;
	}
	
	private void  notesTabAttachmentCfp(GtnCFamilyPlan cfpBean) throws  GtnFrameworkGeneralException {
		logger.info("Enter Cfp notesTabInsert");
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Attachment attachForCfp = new Attachment();
			List<NotesTabBean> notesTabRequestList = cfpBean.getNotesTabList();
			if (notesTabRequestList != null && !notesTabRequestList.isEmpty()) {
				for (NotesTabBean notesTabRequest : notesTabRequestList) {
					logger.info("*******" + cfpBean.getCfpInfo().getCfpSid());
					byte[] fileBytes = readBytesFromFileCfp(notesTabRequest.getFilePath());
					attachForCfp.setAttachmentTableSid(cfpBean.getCfpInfo().getCfpSid());
					attachForCfp.setFileName(notesTabRequest.getFileName());
					attachForCfp.setFileData(fileBytes);
					attachForCfp.setMasterTableName(notesTabRequest.getMasterTableName());
					attachForCfp.setCreatedBy(notesTabRequest.getCreatedBy());
					attachForCfp.setCreatedDate(new Date());
					session.saveOrUpdate(attachForCfp);
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
	 private static byte[] readBytesFromFileCfp(String filePath) throws IOException {
	        File inputFile = GtnFileNameUtils.getFile(filePath);
	        FileInputStream inputStreamCfp= GtnFileNameUtils.getFileInputStreamFile(inputFile);
	        byte[] fileBytes = new byte[(int) inputFile.length()];
	        int i=inputStreamCfp.read(fileBytes);
	        if(i>0) 
	        return fileBytes;
			return  new byte[0];
	    
	    }
	private int deleteNotesTabAttachment(int systemId) throws GtnFrameworkGeneralException {
		String deleteQuery = GtnWsCommonQueryContants.GTN_COMMON_NOTE_TAB_ATTACHMENT_DELETE + systemId
				+ " AND MASTER_TABLE_NAME='CFP_MODEL'";
		return gtnSqlQueryEngine.executeInsertOrUpdateQuery(deleteQuery);
	}
	
}
