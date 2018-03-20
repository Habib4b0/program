
package com.stpl.gtn.gtn2o.ws.module.companymaster.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterCompanyParentBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterCompanyTradeClassBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterCompanyUdcInfoBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterFinancialCloseBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterIdentifierInfoBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterInformationBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.config.GtnWsAllListConfig;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.cmrequest.GtnCMasterRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.cmresponse.GtnCompanyMasterResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import com.stpl.gtn.gtn2o.ws.util.GtnCommonUtil;

@RestController
@RequestMapping(value = GtnWebServiceUrlConstants.GTN_WS_COMPANY_MASTER)
public class GtnWsCMasterEdit {
	public GtnWsCMasterEdit() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsCMasterEdit.class);

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsCMasterAdd companyMasterAddWebservice;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@Autowired
	private GtnWsAllListConfig gtnWebServiceAllListConfig;

	public GtnWsCMasterAdd getCompanyMasterAddWebservice() {
		return companyMasterAddWebservice;
	}

	public void setCompanyMasterAddWebservice(GtnWsCMasterAdd companyMasterAddWebservice) {
		this.companyMasterAddWebservice = companyMasterAddWebservice;
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_CM_GET_DETAILS_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getCompanyMasterDetails(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getCompanyMasterDetails");
		GtnCMasterRequest companyMasterRequest = gtnWsRequest.getGtnCMasterRequest();

		GtnCMasterBean companyMasterBean = companyMasterRequest.getGtnCMasterBean();
		GtnCMasterInformationBean companyInformationBean = companyMasterBean.getGtnCMasterInformationBean();
		List<GtnCMasterFinancialCloseBean> gtnCMasterFinancialCloseBean = companyMasterBean
				.getGtnCMasterFinancialCloseBean();
		logger.info("Exit getCompanyMasterDetails");
		return configureResponseForCompanyMaster(companyInformationBean, gtnCMasterFinancialCloseBean);
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_CM_UPDATE_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse updateCompanyMasterDetails(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Enter getCompanyMasterDetails");
		GtnUIFrameworkWebserviceResponse gtnResponse = companyMasterAddWebservice.saveCompanyMaster(gtnWsRequest);
		logger.info("Exit getCompanyMasterDetails");

		return gtnResponse;
	}

	private GtnUIFrameworkWebserviceResponse configureResponseForCompanyMaster(
			GtnCMasterInformationBean companyInformationBean,
			List<GtnCMasterFinancialCloseBean> gtnCMasterFinancialCloseBean) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnCMasterBean cmResponseBean = new GtnCMasterBean();
		GtnCompanyMasterResponse companyMasterResponse = new GtnCompanyMasterResponse();
		GtnWsGeneralResponse generalWSResponse = new GtnWsGeneralResponse();
		try {
			cmResponseBean.setGtnCMasterInformationBean(getCompanyMasterInfoDetails(companyInformationBean));
			cmResponseBean.setGtnCMasterIdentifierInfoBeanList(getCmIdentifierDetails(companyInformationBean));
			cmResponseBean.setGtnCMasterCompanyUdcInfoBean(getSaveCMUdcDetails(companyInformationBean));
			cmResponseBean.setGtnCMasterCompanyTradeClassBeanList(getCmTradeClassDetails(companyInformationBean));
			cmResponseBean.setGtnCMasterCompanyParentBeanList(getCmParentDetails(companyInformationBean));
			cmResponseBean.setGtnCMasterCompanyNotesTabBeanList(getCmNotesTabDetails(companyInformationBean));
			cmResponseBean.setGtnCMasterCompanyNotesTabBeanList(getCmNotesTabAttachDetails(companyInformationBean));
			for (int i = 0; gtnCMasterFinancialCloseBean != null && i < gtnCMasterFinancialCloseBean.size(); i++) {
				getFinancialCloseTab(companyInformationBean, gtnCMasterFinancialCloseBean.get(i));
			}
			companyMasterResponse.setGtnCMasterBean(cmResponseBean);
			generalWSResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException exception) {
			logger.error("Exception in getting data -", exception);
			generalWSResponse.setSucess(false);
			generalWSResponse.setGtnGeneralException(exception);
		}
		gtnResponse.setGtnWsGeneralResponse(generalWSResponse);
		gtnResponse.setGtnCompanyMasterResponse(companyMasterResponse);
		return gtnResponse;
	}

	private List<GtnCMasterCompanyParentBean> setCompanyParentDetailsInfoBean(
			final List<Object[]> companyParentDetailsList) {
		List<GtnCMasterCompanyParentBean> cmParentDetailsBeanList = null;
		if (companyParentDetailsList != null && !companyParentDetailsList.isEmpty()) {
			cmParentDetailsBeanList = new ArrayList<>();
			int companyParentDetailsSize = companyParentDetailsList.size();
			GtnCMasterCompanyParentBean companyParentDetailsBean = null;
			for (int i = 0; i < companyParentDetailsSize; i++) {
				companyParentDetailsBean = new GtnCMasterCompanyParentBean();
				final Object[] obj = companyParentDetailsList.get(i);
				companyParentDetailsBean.setCompanyNo(String.valueOf(obj[0]));
				companyParentDetailsBean.setCompanyName(String.valueOf(obj[1]));
				companyParentDetailsBean.setCompanyParentStartDate((Date) obj[2]);
				companyParentDetailsBean.setCompanyParentEndDate(getDate(obj[3]));
				companyParentDetailsBean.setCreatedDate(getDate(obj[4]));
				companyParentDetailsBean.setCreatedBy(getInt(obj[5]));
				String createdUserName = gtnWebServiceAllListConfig.getUserIdNameMap().get(getInt(obj[5]));
				companyParentDetailsBean.setCreatedByName(createdUserName);
				companyParentDetailsBean.setModifiedBy(getInt(obj[6]));
				String modifieduserName = gtnWebServiceAllListConfig.getUserIdNameMap().get(getInt(obj[6]));
				companyParentDetailsBean.setModifiedByName(modifieduserName);
				companyParentDetailsBean.setModifiedDate(getDate(obj[7]));
				companyParentDetailsBean.setParentCompanyMasterSystemId((Integer) obj[8]);
				companyParentDetailsBean.setCompanyMasterSystemId((Integer) obj[9]);
				cmParentDetailsBeanList.add(companyParentDetailsBean);
			}
		}
		return cmParentDetailsBeanList;
	}

	private List<GtnCMasterCompanyTradeClassBean> setCompanyTradeClassInfoBean(
			final List<Object[]> companyTradeClassList) {
		List<GtnCMasterCompanyTradeClassBean> cmTradeClassBeanList = null;
		if (companyTradeClassList != null && !companyTradeClassList.isEmpty()) {
			cmTradeClassBeanList = new ArrayList<>();
			int tradeClassListSize = companyTradeClassList.size();
			GtnCMasterCompanyTradeClassBean companyTradeClassBean = null;
			for (int i = 0; i < tradeClassListSize; i++) {
				companyTradeClassBean = new GtnCMasterCompanyTradeClassBean();
				final Object[] obj = companyTradeClassList.get(i);
				companyTradeClassBean.setCompanyTradeClassSid((Integer) obj[0]);
				companyTradeClassBean.setCompanyTradeClassStartDate(getDate(obj[1]));
				companyTradeClassBean.setCompanyTradeClassEndDate(getDate(obj[2]));
				companyTradeClassBean.setCreatedDate((Date) obj[3]);
				companyTradeClassBean.setCreatedBy((Integer) obj[4]);
				String createdUserName = gtnWebServiceAllListConfig.getUserIdNameMap().get(getInt(obj[4]));
				companyTradeClassBean.setCreatedByName(createdUserName);
				companyTradeClassBean.setModifiedBy((Integer) obj[5]);
				String modifieduserName = gtnWebServiceAllListConfig.getUserIdNameMap().get(getInt(obj[5]));
				companyTradeClassBean.setModifiedByName(modifieduserName);
				companyTradeClassBean.setModifiedDate(getDate(obj[6]));
				companyTradeClassBean.setCompanyPriorTradeClass(String.valueOf(obj[7]));
				companyTradeClassBean.setCompanyPriorTradeClassStartDate(getDate(obj[8]));
				companyTradeClassBean.setCompanyMasterSystemId((Integer) obj[9]);
				companyTradeClassBean.setCompanyTradeClassValue(getString(obj[11]));
				cmTradeClassBeanList.add(companyTradeClassBean);
			}
		}
		return cmTradeClassBeanList;
	}

	int getInt(Object value) {
		return value == null ? 0 : (Integer) value;
	}

	Date getDate(Object value) {
		return value == null ? null : (Date) value;
	}

	String getString(Object value) {
		return value == null ? null : (String) value;
	}

	private List<GtnCMasterIdentifierInfoBean> setCompanyIdentifierInfoBean(
			final List<Object[]> companyIdentifierList) {
		List<GtnCMasterIdentifierInfoBean> cmIdentifierInfoBeanList = null;
		if (companyIdentifierList != null && !companyIdentifierList.isEmpty()) {
			cmIdentifierInfoBeanList = new ArrayList<>();
			int identifierListSize = companyIdentifierList.size();
			GtnCMasterIdentifierInfoBean companyIdentifierInfoBean = null;
			for (int i = 0; i < identifierListSize; i++) {
				companyIdentifierInfoBean = new GtnCMasterIdentifierInfoBean();
				final Object[] obj = companyIdentifierList.get(i);
				companyIdentifierInfoBean.setCompanyQualifierSid((Integer) obj[0]);
				companyIdentifierInfoBean.setCompanyIdentifierValue(String.valueOf(obj[1]));
				companyIdentifierInfoBean.setIdentifierStatus((Integer) obj[2]);
				companyIdentifierInfoBean.setCompanyIdentifierStartDate((Date) obj[3]);
				companyIdentifierInfoBean.setCompanyIdentifierEndDate(getDate(obj[4]));
				companyIdentifierInfoBean.setCreatedDate(getDate(obj[5]));
				String createdUserName = gtnWebServiceAllListConfig.getUserIdNameMap().get(getInt(obj[6]));
				companyIdentifierInfoBean.setCreatedBy((Integer) obj[6]);
				companyIdentifierInfoBean.setCreatedByName(createdUserName);
				companyIdentifierInfoBean.setModifiedBy((Integer) obj[7]);
				String modifiedUserName = gtnWebServiceAllListConfig.getUserIdNameMap().get(getInt(obj[7]));
				companyIdentifierInfoBean.setModifiedByName(modifiedUserName);
				companyIdentifierInfoBean.setModifiedDate(getDate(obj[8]));
				companyIdentifierInfoBean.setCompanyMasterSid((Integer) obj[9]);
				companyIdentifierInfoBean.setCompanyQualifierValue(getString(obj[10]));
				companyIdentifierInfoBean.setIdentifierStatusValue(getString(obj[11]));
				cmIdentifierInfoBeanList.add(companyIdentifierInfoBean);
			}
		}
		return cmIdentifierInfoBeanList;
	}

	private GtnCMasterCompanyUdcInfoBean setCompanyUdcBean(final List<Object[]> udcsList) {
		GtnCMasterCompanyUdcInfoBean cmUdcInfoBean = null;
		if (udcsList != null && !udcsList.isEmpty()) {
			int udcsListSize = udcsList.size();
			for (int i = 0; i < udcsListSize; i++) {
				cmUdcInfoBean = new GtnCMasterCompanyUdcInfoBean();
				final Object[] obj = udcsList.get(i);
				cmUdcInfoBean.setUdc1((Integer) obj[0]);
				cmUdcInfoBean.setUdc2((Integer) obj[1]);
				cmUdcInfoBean.setUdc3((Integer) obj[2]);
				cmUdcInfoBean.setUdc4((Integer) obj[3]);
				cmUdcInfoBean.setUdc5((Integer) obj[4]);
				cmUdcInfoBean.setUdc6((Integer) obj[5]);
				cmUdcInfoBean.setCompanyMasterId((Integer) obj[6]);
			}
		}
		return cmUdcInfoBean;
	}

	private GtnCMasterInformationBean setCompanyMasterInfoBean(final List<Object[]> cmResultList) {
		GtnCMasterInformationBean companyInfoBean = null;
		if (cmResultList != null && !cmResultList.isEmpty()) {
			int cmResultsListSize = cmResultList.size();
			for (int i = 0; i < cmResultsListSize; i++) {
				companyInfoBean = new GtnCMasterInformationBean();
				final Object[] obj = cmResultList.get(i);
				companyInfoBean.setCompanyId(String.valueOf(obj[0]));
				companyInfoBean.setCompanyNo(String.valueOf(obj[1]));
				companyInfoBean.setCompanyName(String.valueOf(obj[2]));
				companyInfoBean.setCompanyStatus((Integer) obj[3]);
				companyInfoBean.setCompanyStartDate((Date) obj[4]);
				companyInfoBean.setCompanyEndDate(getDate(obj[5]));
				companyInfoBean.setCompanyCategory(getInt(obj[6]));
				companyInfoBean.setCompanyGroup(getInt(obj[7]));
				companyInfoBean.setCompanyType(getInt(obj[8]));
				companyInfoBean.setOrganizationKey(getInt(obj[9]));
				companyInfoBean.setSource(String.valueOf(obj[10]));
				companyInfoBean.setFinancialSystem(String.valueOf(obj[11]));
				companyInfoBean.setCompanyMasterSystemId((Integer) obj[12]);
				companyInfoBean.setCreatedBy((Integer) obj[13]);
				companyInfoBean.setCreatedByName(
						gtnWebServiceAllListConfig.getUserIdNameMap().get(companyInfoBean.getCreatedBy()));
				companyInfoBean.setCreatedDate(getDate(obj[14]));
				companyInfoBean.setModifiedBy((Integer) obj[15]);
				companyInfoBean.setModifiedByName(
						gtnWebServiceAllListConfig.getUserIdNameMap().get(companyInfoBean.getModifiedBy()));
				companyInfoBean.setModifiedDate(getDate(obj[16]));

				companyInfoBean.setAddress1(String.valueOf(obj[17]));
				companyInfoBean.setCity(String.valueOf(obj[18]));
				companyInfoBean.setZipCode(String.valueOf(obj[19]));
				companyInfoBean.setCountry(getInt(obj[20]));
				companyInfoBean.setAddress2(String.valueOf(obj[21]));
				companyInfoBean.setState(getInt(obj[22]));
				companyInfoBean.setRegionCode(String.valueOf(obj[23]));
				companyInfoBean.setInternalNotes(String.valueOf(obj[24]));
			}
		}
		return companyInfoBean;
	}

	public Date dateFormatter(final Date date) throws ParseException {

		logger.info("dateToDateConvert p1:" + date);
		Date formatedDate = null;
		try {
			final DateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy");
			formatedDate = inputFormat.parse(inputFormat.format(date));
		} catch (Exception e) {
			logger.error("Error while converting date.", e);
		}
		return formatedDate;
	}

	private GtnCMasterInformationBean getCompanyMasterInfoDetails(GtnCMasterInformationBean companyInformationBean)
			throws GtnFrameworkGeneralException {
		logger.info("Enter getCompanyMasterSelectQuery");
		String cmSelectQuery = "Select COMPANY_ID,COMPANY_NO,COMPANY_NAME,COMPANY_STATUS,COMPANY_START_DATE,COMPANY_END_DATE,COMPANY_CATEGORY,"
				+ "COMPANY_GROUP,COMPANY_TYPE,ORGANIZATION_KEY,\"SOURCE\",FINANCIAL_SYSTEM,COMPANY_MASTER_SID "
				+ ",CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,ADDRESS1,CITY,ZIP_CODE,COUNTRY,ADDRESS2,\"STATE\",REGION_CODE,INTERNAL_NOTES "
				+ "from COMPANY_MASTER WHERE COMPANY_MASTER_SID=" + companyInformationBean.getCompanyMasterSystemId()
				+ ";";
		@SuppressWarnings("unchecked")
		List<Object[]> cmResultList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(cmSelectQuery);
		GtnCMasterInformationBean companyInformationBeanWithResult = setCompanyMasterInfoBean(cmResultList);
		logger.info("Exit getCompanyMasterSelectQuery");
		return companyInformationBeanWithResult;
	}

	private GtnCMasterCompanyUdcInfoBean getSaveCMUdcDetails(GtnCMasterInformationBean companyInformationBean)
			throws GtnFrameworkGeneralException {
		logger.info("Enter getSaveCMUdcDetails");
		String cmUdcSelectQuery = "Select UDC1,UDC2,UDC3,UDC4,UDC5,UDC6,MASTER_SID FROM UDCS WHERE MASTER_SID="
				+ companyInformationBean.getCompanyMasterSystemId() + "AND MASTER_TYPE='COMPANY_MASTER'";
		@SuppressWarnings("unchecked")
		List<Object[]> cmUdcResultList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(cmUdcSelectQuery);
		GtnCMasterCompanyUdcInfoBean udcInfoBean = setCompanyUdcBean(cmUdcResultList);
		logger.info("Exit getSaveCMUdcDetails");
		return udcInfoBean;
	}

	private List<GtnCMasterIdentifierInfoBean> getCmIdentifierDetails(GtnCMasterInformationBean companyInformationBean)
			throws GtnFrameworkGeneralException {

		logger.info("Enter getCmIdentifierDetails");
		String cmIndentifierSelectQuery = "Select ci.COMPANY_QUALIFIER_SID,	COMPANY_IDENTIFIER_VALUE,	IDENTIFIER_STATUS,	START_DATE,	END_DATE,	ci.CREATED_DATE,	ci.CREATED_BY,	ci.MODIFIED_BY,	ci.MODIFIED_DATE,	COMPANY_MASTER_SID,	cq.COMPANY_QUALIFIER_NAME,	ISTATUS.DESCRIPTION as IDENTIFIER_STATUS_VALUE "
				+ "from	COMPANY_IDENTIFIER ci "
				+ "join COMPANY_QUALIFIER cq ON	ci.COMPANY_QUALIFIER_SID = cq.COMPANY_QUALIFIER_SID	"
				+ "left join dbo.HELPER_TABLE ISTATUS on IDENTIFIER_STATUS = ISTATUS.HELPER_TABLE_SID "
				+ "AND ISTATUS.DESCRIPTION NOT LIKE '%-Select One-%' where	ci.COMPANY_MASTER_SID ="
				+ companyInformationBean.getCompanyMasterSystemId() + ";";
		@SuppressWarnings("unchecked")
		List<Object[]> cmIndentifierResultList = (List<Object[]>) gtnSqlQueryEngine
				.executeSelectQuery(cmIndentifierSelectQuery);
		List<GtnCMasterIdentifierInfoBean> cmIdentifierInfoBeanList = setCompanyIdentifierInfoBean(
				cmIndentifierResultList);
		logger.info("Exit getCmIdentifierDetails");
		return cmIdentifierInfoBeanList;
	}

	private List<GtnCMasterCompanyTradeClassBean> getCmTradeClassDetails(
			GtnCMasterInformationBean companyInformationBean) throws GtnFrameworkGeneralException {
		logger.info("Enter getCmTradeClassDetails");
		String cmTradeClassSelectQuery = "SELECT COMPANY_TRADE_CLASS,trade_Class_Start_Date,trade_Class_End_Date,CT.created_Date,CT.CREATED_BY,CT.modified_By,CT.modified_Date,"
				+ "prior_Trade_Class,prior_Trade_Class_Start_Date,company_Master_Sid,company_Trade_Class_Sid,HT.DESCRIPTION "
				+ "FROM Company_Trade_Class CT JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID= CT.COMPANY_TRADE_CLASS WHERE CT.company_Master_Sid="
				+ companyInformationBean.getCompanyMasterSystemId() + ";";
		@SuppressWarnings("unchecked")
		List<Object[]> cmIndentifierResultList = (List<Object[]>) gtnSqlQueryEngine
				.executeSelectQuery(cmTradeClassSelectQuery);
		List<GtnCMasterCompanyTradeClassBean> cmTradeClassInfoBeanList = setCompanyTradeClassInfoBean(
				cmIndentifierResultList);
		logger.info("Exit getCmTradeClassDetails");
		return cmTradeClassInfoBeanList;
	}

	private List<GtnCMasterCompanyParentBean> getCmParentDetails(GtnCMasterInformationBean companyInformationBean)
			throws GtnFrameworkGeneralException {
		logger.info("Enter getCmParentDetails");
		String cmParentDetailsSelectQuery = "Select CM.COMPANY_NO,CM.COMPANY_NAME,CPD.PARENT_START_DATE,CPD.PARENT_END_DATE,CPD.CREATED_DATE,CPD.CREATED_BY,CPD.MODIFIED_BY,CPD.MODIFIED_DATE,CPD.PARENT_COMPANY_MASTER_SID,CPD.COMPANY_MASTER_SID "
				+ "from COMPANY_PARENT_DETAILS CPD "
				+ "join COMPANY_MASTER CM ON CPD.PARENT_COMPANY_MASTER_SID=CM.COMPANY_MASTER_SID "
				+ "where CPD.COMPANY_MASTER_SID=" + companyInformationBean.getCompanyMasterSystemId() + ";";
		@SuppressWarnings("unchecked")
		List<Object[]> cmParentDetailsResultList = (List<Object[]>) gtnSqlQueryEngine
				.executeSelectQuery(cmParentDetailsSelectQuery);
		List<GtnCMasterCompanyParentBean> cmParentDetailsInfoBeanList = setCompanyParentDetailsInfoBean(
				cmParentDetailsResultList);
		logger.info("Exit getCmParentDetails");
		return cmParentDetailsInfoBeanList;
	}

	private List<NotesTabBean> getCmNotesTabDetails(GtnCMasterInformationBean companyInformationBean)
			throws GtnFrameworkGeneralException {
		logger.info("Enter getCmNotesTabDetails");
		String cmNotesTabDetailsSelectQuery = "Select MASTER_TABLE_NAME,FILE_PATH,CREATED_DATE,CREATED_BY,MASTER_DATA_FILES_SID from MASTER_DATA_FILES "
				+ "where MASTER_TABLE_SID= " + companyInformationBean.getCompanyMasterSystemId() + ";";
		@SuppressWarnings("unchecked")
		List<Object[]> cmNotesDetailsResultList = (List<Object[]>) gtnSqlQueryEngine
				.executeSelectQuery(cmNotesTabDetailsSelectQuery);
		List<NotesTabBean> cmNotesDetailsInfoBeanList = GtnCommonUtil.getNotesTabBean(cmNotesDetailsResultList,
				gtnWebServiceAllListConfig);
		logger.info("Exit getCmNotesTabDetails");
		return cmNotesDetailsInfoBeanList;
	}

	private void getFinancialCloseTab(GtnCMasterInformationBean companyInformationBean,
			GtnCMasterFinancialCloseBean gtnCMasterFinancialCloseBean) {
		try {
			logger.info("Enter GtnCMasterFinancialCloseBean");
			String mainToTempInsert = gtnWsSqlService.getQuery("mainToTempInsert");
			Object[] params = { gtnCMasterFinancialCloseBean.getUserId(), gtnCMasterFinancialCloseBean.getSessionId(),
					companyInformationBean.getCompanyMasterSystemId() };
			GtnFrameworkDataType[] type = { GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER,
					GtnFrameworkDataType.INTEGER };

			int id = gtnSqlQueryEngine.executeInsertOrUpdateQuery(mainToTempInsert, params, type);

			logger.info("Exit GtnCMasterFinancialCloseBean" + id);
		} catch (GtnFrameworkGeneralException ex) {
			logger.error("Exception in executig query-", ex);

		} finally {
			logger.info("Exit deleteTempTable");
		}
	}
	private List<NotesTabBean> getCmNotesTabAttachDetails(GtnCMasterInformationBean companyInformationBean)
			throws GtnFrameworkGeneralException {
		logger.info("Enter getCmNotesTabAttachDetails");
		String cmNotesTabDetailsSelectQuery = "Select MASTER_TABLE_NAME,FILE_NAME, CREATED_DATE, CREATED_BY ,ID,FILE_DATA from ATTACHMENT   "
				+ "where ATTACHMENT_TABLE_SID = " + companyInformationBean.getCompanyMasterSystemId() + ";";
		@SuppressWarnings("unchecked")
		List<Object[]> cmNotesDetailsAttachResultList = (List<Object[]>) gtnSqlQueryEngine
				.executeSelectQuery(cmNotesTabDetailsSelectQuery);
		List<NotesTabBean> cmNotesAttachDetailsInfoBeanList = GtnCommonUtil.getNotesTabBean(cmNotesDetailsAttachResultList,
				gtnWebServiceAllListConfig);
		logger.info("Exit getCmNotesTabDetails");
		return cmNotesAttachDetailsInfoBeanList;
	}

}
