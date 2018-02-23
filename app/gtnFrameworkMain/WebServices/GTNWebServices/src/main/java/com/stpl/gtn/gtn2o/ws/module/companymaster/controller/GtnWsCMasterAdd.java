package com.stpl.gtn.gtn2o.ws.module.companymaster.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.config.GtnFrameworkQueryConfig;
import com.stpl.gtn.gtn2o.queryengine.config.GtnFrameworkQueryEngineConfig;
import com.stpl.gtn.gtn2o.queryengine.config.GtnFrameworkQueryEngineMainConfig;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkQueryEngineMain;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterCompanyParentBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterCompanyTradeClassBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterCompanyUdcInfoBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterIdentifierInfoBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterInformationBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.cmresponse.GtnCompanyMasterResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@RestController
@RequestMapping(value = GtnWebServiceUrlConstants.GTN_WS_COMPANY_MASTER)
public class GtnWsCMasterAdd {
	public GtnWsCMasterAdd() {
		/**
		 * empty constructor
		 */
	}

	private List<Integer> tradeClassSidList = new ArrayList<>();
	private List<Integer> parentDetailsSidList = new ArrayList<>();
	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsCMasterAdd.class);

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private GtnFrameworkQueryEngineMain gtnFrameworkQueryEngineMain;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public GtnFrameworkQueryEngineMain getGtnFrameworkQueryEngineMain() {
		return gtnFrameworkQueryEngineMain;
	}

	public void setGtnFrameworkQueryEngineMain(GtnFrameworkQueryEngineMain gtnFrameworkQueryEngineMain) {
		this.gtnFrameworkQueryEngineMain = gtnFrameworkQueryEngineMain;
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_WS_CM_SAVE_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse saveCompanyMaster(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		LOGGER.info("Enter saveCompanyMaster");
		int count = 0;
		if (gtnWsRequest.getGtnCMasterRequest().getGtnCMasterBean().getGtnCMasterInformationBean()
				.getCompanyMasterSystemId() == 0) {
			count = performUpdateForCompanyIdWithStatusD(gtnWsRequest.getGtnCMasterRequest().getGtnCMasterBean());
		}
		if (gtnWsRequest.getGtnCMasterRequest().getGtnCMasterBean().getGtnCMasterInformationBean()
				.getCompanyMasterSystemId() > 0) {
			int companyMasterSid=gtnWsRequest.getGtnCMasterRequest().getGtnCMasterBean().getGtnCMasterInformationBean()
					.getCompanyMasterSystemId() ;
			checkTradeClassList(companyMasterSid);
			checkParentDetailsList(companyMasterSid);
			
		}
		if (count == 1) {
			int automatedSystemId = getSysIdForCompanyIdWithStatusD(
					gtnWsRequest.getGtnCMasterRequest().getGtnCMasterBean());
			gtnWsRequest.getGtnCMasterRequest().getGtnCMasterBean().getGtnCMasterInformationBean()
					.setCompanyMasterSystemId(automatedSystemId);
		}
		GtnFrameworkQueryEngineMainConfig mainConfig = new GtnFrameworkQueryEngineMainConfig();
		mainConfig = configureDataArray(mainConfig, gtnWsRequest);
		mainConfig = buildQueryConfigForCMaster(mainConfig, gtnWsRequest);
		LOGGER.info("Exit saveCompanyMaster");
		return executeQuery(mainConfig);
	}

	private void checkParentDetailsList(int companyMasterSid) {
		
		List<Integer> parentClassList;
		parentClassList = findParentDetailsSidFromTable(companyMasterSid);
		for(Integer parentDetailsSid:parentClassList)
		{
			createParentDetailsListNotDeleteSupported(parentDetailsSid);
		}
	
	}

	@SuppressWarnings("unchecked")
	private void createParentDetailsListNotDeleteSupported(Integer parentDetailsSid) {
		List<Integer> countCheckParentDetailsList;
		if (parentDetailsSid != 0) {
			try {
				countCheckParentDetailsList = (List<Integer>) gtnSqlQueryEngine.executeSelectQuery(
						gtnWsSqlService.getQuery(Arrays.asList(parentDetailsSid),
								"checkCompanyGroupDetailsParentDetailsExists"));
				int countCheck=countCheckParentDetailsList.get(0);
				if (countCheck == 1) {
					parentDetailsSidList.add(parentDetailsSid);
				}
			} catch (GtnFrameworkGeneralException e) {
				LOGGER.error("Exception in createParentDetailsListNotDeleteSupported method:" + e);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private List<Integer> findParentDetailsSidFromTable(int companyMasterSid) {
		List<Integer> parentDetailsListFromTable=new ArrayList<>();
		try {
			parentDetailsListFromTable=(List<Integer>) gtnSqlQueryEngine
					.executeSelectQuery(gtnWsSqlService.getQuery(
							Arrays.asList(companyMasterSid) , "getCompanyParentDetailsSid"));
		} catch (GtnFrameworkGeneralException e) {
			
			LOGGER.error("Exception in Find ParentDetails Sid method:" + e);
		}
		return parentDetailsListFromTable;
	}

	private void checkTradeClassList(int companyMasterSid) {
		List<Integer> tradeClassList;
			tradeClassList = findTradeClassSidFromTable(companyMasterSid);
			for(Integer tradeSid:tradeClassList)
			{
				createTradeClassListNotDeleteSupported(tradeSid);
			}
		
	}

	@SuppressWarnings("unchecked")
	private List<Integer> findTradeClassSidFromTable(int companyMasterSid
			) {
		List<Integer> tradeClassListFromTable=new ArrayList<>();
		try {
			tradeClassListFromTable=(List<Integer>) gtnSqlQueryEngine
					.executeSelectQuery(gtnWsSqlService.getQuery(
							Arrays.asList(companyMasterSid) , "getCompanyTradeClassSid"));
		} catch (GtnFrameworkGeneralException e) {
			
			LOGGER.error("Exception:" + e);
		}
		return tradeClassListFromTable;
	}

	@SuppressWarnings("unchecked")
	private void createTradeClassListNotDeleteSupported(int tradeClassSid) {
		List<Integer> countCheckList;
		if (tradeClassSid != 0) {
			try {
				countCheckList = (List<Integer>) gtnSqlQueryEngine.executeSelectQuery(
						gtnWsSqlService.getQuery(Arrays.asList(tradeClassSid),
								"checkCompanyGroupDetailsTradeClassExists"));
				int countCheck=countCheckList.get(0);
				if (countCheck == 1) {
					tradeClassSidList.add(tradeClassSid);
				}
			} catch (GtnFrameworkGeneralException e) {
				LOGGER.error("Exception:" + e);
			}
		}
	}

	public GtnUIFrameworkWebserviceResponse executeQuery(GtnFrameworkQueryEngineMainConfig mainConfig) {
		LOGGER.info("ExecuteQuery");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalWSResponse = new GtnWsGeneralResponse();
		GtnCompanyMasterResponse generalCmResponse = new GtnCompanyMasterResponse();
		GtnCMasterBean masterBean = new GtnCMasterBean();
		masterBean.setGtnCMasterInformationBean(new GtnCMasterInformationBean());
		generalCmResponse.setGtnCMasterBean(masterBean);
		Session current = sessionFactory.openSession();
		gtnResponse.setGtnCompanyMasterResponse(generalCmResponse);
		Transaction tx = null;
		try {
			tx = current.beginTransaction();
			gtnFrameworkQueryEngineMain.executeQuery(current, mainConfig);
			tx.commit();
			if (generalCmResponse.getGtnCMasterBean().getGtnCMasterInformationBean().getCompanyMasterSystemId() != 0) {
				generalCmResponse.getGtnCMasterBean().getGtnCMasterInformationBean()
						.setModifiedBy((Integer) mainConfig.getQueryMemoryArray()[27]);
				generalCmResponse.getGtnCMasterBean().getGtnCMasterInformationBean()
						.setModifiedDate((Date) mainConfig.getQueryMemoryArray()[28]);
			}
			generalCmResponse.getGtnCMasterBean().getGtnCMasterInformationBean()
					.setCompanyMasterSystemId((int) mainConfig.getQueryMemoryArray()[29]);

		} catch (Exception ex) {
			LOGGER.error("Exception in executig query-", ex);
			generalWSResponse.setSucess(false);
			generalWSResponse.setGtnGeneralException(ex);
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			LOGGER.info("Closing session " + current);
			current.close();
		}
		generalWSResponse.setSucess(true);
		LOGGER.info("Exit saveCompanyMaster");
		gtnResponse.setGtnWsGeneralResponse(generalWSResponse);
		return gtnResponse;
	}

	public GtnFrameworkQueryEngineMainConfig buildQueryConfigForCMaster(GtnFrameworkQueryEngineMainConfig mainConfig,
			GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		LOGGER.info("Enter buildQueryConfigForCMaster");

		GtnCMasterBean companyMasterBean = gtnWsRequest.getGtnCMasterRequest().getGtnCMasterBean();
		GtnCMasterInformationBean companyInformationBean = companyMasterBean.getGtnCMasterInformationBean();
		int currentParamPos = 0;
		int companyMasterIdentifierPosition = 0;
		GtnFrameworkQueryEngineConfig companyMasterConfig = new GtnFrameworkQueryEngineConfig();
		if (companyInformationBean.getCompanyMasterSystemId() != 0) {
			companyMasterConfig = getCompanyMasterEditQueryConfig(companyInformationBean);
			companyMasterIdentifierPosition = 29;
			currentParamPos = 29;

		} else {
			currentParamPos = getCompanyMasterQueryConfig(companyMasterConfig, currentParamPos);
			companyMasterIdentifierPosition = currentParamPos;
		}

		List<GtnFrameworkQueryEngineConfig> childQueryConfigList = new ArrayList<>();
		companyMasterConfig.setChildQueryEngineConfigList(childQueryConfigList);

		GtnFrameworkQueryEngineConfig udcConfig = new GtnFrameworkQueryEngineConfig();
		currentParamPos = getSaveCMUdcQueryConfig(udcConfig, currentParamPos);
		childQueryConfigList.add(udcConfig);

		List<GtnCMasterIdentifierInfoBean> cmIdentifierInfoBeanList = companyMasterBean
				.getGtnCMasterIdentifierInfoBeanList();
		for (GtnCMasterIdentifierInfoBean companyIdentifierInfoBean : cmIdentifierInfoBeanList) {
			GtnFrameworkQueryEngineConfig cmIdentifierConfig = new GtnFrameworkQueryEngineConfig();
			LOGGER.info(GtnFrameworkWebserviceConstant.CURRENT_PARAM_POSITION + currentParamPos);
			LOGGER.debug(companyIdentifierInfoBean.toString());
			currentParamPos = getCmIdentifierQueryConfig(cmIdentifierConfig, currentParamPos,
					companyMasterIdentifierPosition);
			childQueryConfigList.add(cmIdentifierConfig);
		}
		List<GtnCMasterCompanyTradeClassBean> cmTradeClassInfoBeanList = companyMasterBean
				.getGtnCMasterCompanyTradeClassBeanList();
		for (GtnCMasterCompanyTradeClassBean companyTradeClassBean : cmTradeClassInfoBeanList) {
			GtnFrameworkQueryEngineConfig cmTradeClassConfig = new GtnFrameworkQueryEngineConfig();
			LOGGER.info(GtnFrameworkWebserviceConstant.CURRENT_PARAM_POSITION + currentParamPos);
			LOGGER.debug(companyTradeClassBean.toString());
			if (!tradeClassSidList.isEmpty()) {
				currentParamPos = getCmUpdateTradeClassQueryConfig(cmTradeClassConfig, currentParamPos,
						companyMasterIdentifierPosition, tradeClassSidList.get(tradeClassSidList.size()-1));
				tradeClassSidList.remove(tradeClassSidList.size() - 1);
			} else {
				currentParamPos = getCmTradeClassQueryConfig(cmTradeClassConfig, currentParamPos,
						companyMasterIdentifierPosition);
			}
			childQueryConfigList.add(cmTradeClassConfig);
		}
		List<GtnCMasterCompanyParentBean> cmCompanyParentBeanList = companyMasterBean
				.getGtnCMasterCompanyParentBeanList();
		for (GtnCMasterCompanyParentBean companyParentBean : cmCompanyParentBeanList) {
			GtnFrameworkQueryEngineConfig cmCompanyParentBeanConfig = new GtnFrameworkQueryEngineConfig();
			LOGGER.info(GtnFrameworkWebserviceConstant.CURRENT_PARAM_POSITION + currentParamPos);
			LOGGER.debug(companyParentBean.toString());
			if (!parentDetailsSidList.isEmpty()) {
				currentParamPos = getCmUpdateParentDetailsQueryConfig(cmCompanyParentBeanConfig, currentParamPos,
						companyMasterIdentifierPosition, parentDetailsSidList.get(parentDetailsSidList.size()-1));
				parentDetailsSidList.remove(parentDetailsSidList.size() - 1);
			}
			else{
			currentParamPos = getCmParentDetailsQueryConfig(cmCompanyParentBeanConfig, currentParamPos,
					companyMasterIdentifierPosition);
			}
			childQueryConfigList.add(cmCompanyParentBeanConfig);
		}
		List<NotesTabBean> cmNotesTabBeanList = companyMasterBean.getGtnCMasterCompanyNotesTabBeanList();
		for (NotesTabBean companyNotesTabBean : cmNotesTabBeanList) {
			GtnFrameworkQueryEngineConfig cmNotesTabBeanConfig = new GtnFrameworkQueryEngineConfig();
			LOGGER.info(GtnFrameworkWebserviceConstant.CURRENT_PARAM_POSITION + currentParamPos);
			LOGGER.debug(companyNotesTabBean.toString());
			currentParamPos = getCmNotesTabQueryConfig(cmNotesTabBeanConfig, currentParamPos,
					companyMasterIdentifierPosition);
			childQueryConfigList.add(cmNotesTabBeanConfig);
		}
		mainConfig.setRootEngineConfig(companyMasterConfig);
		LOGGER.info("Exit buildQueryConfigForCMaster");
		return mainConfig;
	}

	private int getCmUpdateParentDetailsQueryConfig(GtnFrameworkQueryEngineConfig cmParentDetailsUpdateQueryConfig,
			int currentParamPos, int companyMasterIdentifierPosition, Integer parentDetailsSid) {
		LOGGER.info("Enter getCmParentDetailsQueryConfig");
		int currentPosParentDetailsUpdate;
		List<GtnFrameworkQueryConfig> cmParentDetailsUpdateQueries = new ArrayList<>();
		cmParentDetailsUpdateQueryConfig.setQueryConfigList(cmParentDetailsUpdateQueries);
		GtnFrameworkQueryConfig cmParentDetailsUpdate = new GtnFrameworkQueryConfig();
		String cmParentDetailsUpdateQuery = "UPDATE COMPANY_PARENT_DETAILS SET COMPANY_MASTER_SID=?, PARENT_COMPANY_MASTER_SID=?, PARENT_START_DATE=?, PARENT_END_DATE=?, PRIOR_PARENT_CMPY_MASTER_SID=?, PRIOR_PARENT_START_DATE=?, LAST_UPDATED_DATE=?,"
				+ "INBOUND_STATUS=?, RECORD_LOCK_STATUS=?, BATCH_ID=?,\"SOURCE\"=?, CREATED_BY=?, CREATED_DATE=?, MODIFIED_BY=?, MODIFIED_DATE=?"
				+" WHERE COMPANY_PARENT_DETAILS_SID="+parentDetailsSid+" ;";
		cmParentDetailsUpdate.setQuery(cmParentDetailsUpdateQuery);
		cmParentDetailsUpdate.setInsertOrSelectQuery(true);
		cmParentDetailsUpdate.setDataTypeArray(new String[] { GtnFrameworkWebserviceConstant.INTEGER,
				GtnFrameworkWebserviceConstant.INTEGER, "Date", "Date", GtnFrameworkWebserviceConstant.STRING, "Date",
				"Date", GtnFrameworkWebserviceConstant.STRING, GtnFrameworkWebserviceConstant.INTEGER,
				GtnFrameworkWebserviceConstant.STRING, GtnFrameworkWebserviceConstant.STRING,
				GtnFrameworkWebserviceConstant.INTEGER, "Date", GtnFrameworkWebserviceConstant.INTEGER, "Date" });
		currentPosParentDetailsUpdate = cmParentDetailsUpdate.setParamPositionArray(new int[] { companyMasterIdentifierPosition },
				currentParamPos, currentParamPos + (cmParentDetailsUpdate.getDataTypeArray().length - 1));
		cmParentDetailsUpdateQueries.add(cmParentDetailsUpdate);
		LOGGER.info("Exit getCmParentDetailsQueryConfig");
		return currentPosParentDetailsUpdate;
	}

	private int getCmUpdateTradeClassQueryConfig(GtnFrameworkQueryEngineConfig cmTradeClassConfig, int currentParamPos,
			int companyMasterIdentifierPosition, Integer tradeClassSid) {
		LOGGER.info("Enter getCmTradeClassUpdateQueryConfig");
		int currentUpdatePos;
		List<GtnFrameworkQueryConfig> tradeclassUpdateQueries = new ArrayList<>();
		cmTradeClassConfig.setQueryConfigList(tradeclassUpdateQueries);
		GtnFrameworkQueryConfig tradeClassUpdate = new GtnFrameworkQueryConfig();
		String cmTradeclassUpdateQuery = "UPDATE COMPANY_TRADE_CLASS SET "
				+ "COMPANY_MASTER_SID=?, TRADE_CLASS_START_DATE=?, TRADE_CLASS_END_DATE=?, COMPANY_TRADE_CLASS=?, PRIOR_TRADE_CLASS=?, PRIOR_TRADE_CLASS_START_DATE=?, LAST_UPDATED_DATE=?, INBOUND_STATUS=?, RECORD_LOCK_STATUS=?, BATCH_ID=?,\"SOURCE\"=? ,CREATED_BY=?, CREATED_DATE=?, MODIFIED_BY=?, MODIFIED_DATE=? WHERE COMPANY_TRADE_CLASS_SID= "
				+ tradeClassSid+" ;";
		tradeClassUpdate.setQuery(cmTradeclassUpdateQuery);
		tradeClassUpdate.setUpdateOrDeleteQuery(true);
		tradeClassUpdate.setDataTypeArray(new String[] { GtnFrameworkWebserviceConstant.INTEGER, "Date", "Date",
				GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.STRING,
				GtnFrameworkWebserviceConstant.STRING, "Date", GtnFrameworkWebserviceConstant.STRING,
				GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.STRING,
				GtnFrameworkWebserviceConstant.STRING, GtnFrameworkWebserviceConstant.INTEGER, "Date",
				GtnFrameworkWebserviceConstant.INTEGER, "Date" });
		currentUpdatePos = tradeClassUpdate.setParamPositionArray(new int[] { companyMasterIdentifierPosition },
				currentParamPos, currentParamPos + (tradeClassUpdate.getDataTypeArray().length - 1));
		tradeclassUpdateQueries.add(tradeClassUpdate);
		LOGGER.info("Exit getCmTradeClassUpdateQueryConfig");
		return currentUpdatePos;
	}

	private GtnFrameworkQueryEngineMainConfig configureDataArray(GtnFrameworkQueryEngineMainConfig mainConfig,
			GtnUIFrameworkWebserviceRequest request) {
		Object[] saveDataArray;
		List<Object> saveDataList = new ArrayList<>();
		GtnCMasterBean cmRequest = request.getGtnCMasterRequest().getGtnCMasterBean();

		saveDataList = getCompanyMasterInfo(saveDataList, cmRequest);
		saveDataList = getCompanyUdc(saveDataList, cmRequest);
		saveDataList = getCompanyIdentifierInfo(saveDataList, cmRequest);
		saveDataList = getCompanyTradeClassInfo(saveDataList, cmRequest);
		saveDataList = getCompanyParentInfo(saveDataList, cmRequest);
		saveDataList = getCompanyMasterNotesTab(saveDataList, cmRequest);
		LOGGER.info("Before Converting to Obj Array::::" + saveDataList);
		saveDataArray = saveDataList.toArray();
		mainConfig.setQueryMemoryArray(saveDataArray);
		return mainConfig;
	}

	private int getCompanyMasterQueryConfig(GtnFrameworkQueryEngineConfig companyMasterConfig, int currentParamPos) {
		LOGGER.info("Enter getCompanyMasterQueryConfig");
		int currentPos;

		List<GtnFrameworkQueryConfig> cmQueryConfigList = new ArrayList<>();

		GtnFrameworkQueryConfig cmInsertQueryConfig = new GtnFrameworkQueryConfig();
		String cmInsertQuery = "INSERT INTO COMPANY_MASTER (COMPANY_ID, COMPANY_NO, COMPANY_NAME, COMPANY_TYPE, COMPANY_STATUS, COMPANY_CATEGORY, COMPANY_GROUP, "
				+ "COMPANY_START_DATE, COMPANY_END_DATE, ORGANIZATION_KEY, LIVES, FINANCIAL_SYSTEM, ADDRESS1, ADDRESS2, CITY, \"STATE\", ZIP_CODE, COUNTRY, REGION_CODE, LAST_UPDATED_DATE, INTERNAL_NOTES, INBOUND_STATUS, RECORD_LOCK_STATUS, BATCH_ID, \"SOURCE\", CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		cmInsertQueryConfig.setQuery(cmInsertQuery);
		cmInsertQueryConfig.setInsertOrSelectQuery(true);
		cmInsertQueryConfig.setDataTypeArray(new String[] { GtnFrameworkWebserviceConstant.STRING,
				GtnFrameworkWebserviceConstant.STRING, GtnFrameworkWebserviceConstant.STRING,
				GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.INTEGER,
				GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.INTEGER, "Date", "Date",
				GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.INTEGER,
				GtnFrameworkWebserviceConstant.STRING, GtnFrameworkWebserviceConstant.STRING,
				GtnFrameworkWebserviceConstant.STRING, GtnFrameworkWebserviceConstant.STRING,
				GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.STRING,
				GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.STRING,
				GtnFrameworkWebserviceConstant.STRING, GtnFrameworkWebserviceConstant.STRING,
				GtnFrameworkWebserviceConstant.STRING, GtnFrameworkWebserviceConstant.INTEGER,
				GtnFrameworkWebserviceConstant.STRING, GtnFrameworkWebserviceConstant.STRING,
				GtnFrameworkWebserviceConstant.INTEGER, "Date", GtnFrameworkWebserviceConstant.INTEGER, "Date" });
		currentPos = cmInsertQueryConfig.setParamPositionArray(currentParamPos,
				currentParamPos + cmInsertQueryConfig.getDataTypeArray().length);
		cmInsertQueryConfig.setResultStoragePositionArray(new int[] { currentPos });

		cmQueryConfigList.add(cmInsertQueryConfig);

		companyMasterConfig.setQueryConfigList(cmQueryConfigList);
		LOGGER.info("Exit getCompanyMasterQueryConfig");

		return currentPos;

	}

	private int getSaveCMUdcQueryConfig(GtnFrameworkQueryEngineConfig udcConfig, int currentParamPos) {
		LOGGER.info("Enter getSaveCMUdcQueryConfig");
		int currentPos;
		List<GtnFrameworkQueryConfig> udcqueryList = new ArrayList<>();
		udcConfig.setQueryConfigList(udcqueryList);

		GtnFrameworkQueryConfig udcinsertQueryConfig = new GtnFrameworkQueryConfig();
		String cmUdcsaveQuery = "INSERT INTO UDCS (MASTER_SID, MASTER_TYPE, UDC1, UDC2, UDC3, UDC4, UDC5, UDC6, UDC7, UDC8, UDC9, UDC10, UDC11, UDC12)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		udcinsertQueryConfig.setQuery(cmUdcsaveQuery);
		udcinsertQueryConfig.setInsertOrSelectQuery(true);
		udcinsertQueryConfig.setDataTypeArray(
				new String[] { GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.STRING,
						GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.INTEGER,
						GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.INTEGER,
						GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.INTEGER,
						GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.INTEGER,
						GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.INTEGER,
						GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.INTEGER });

		currentPos = udcinsertQueryConfig.setParamPositionArray(currentParamPos,
				currentParamPos + udcinsertQueryConfig.getDataTypeArray().length);

		udcqueryList.add(udcinsertQueryConfig);

		LOGGER.info("Exit getSaveCMUdcQueryConfig");
		return currentPos;
	}

	private int getCmIdentifierQueryConfig(GtnFrameworkQueryEngineConfig cmIdentifierConfig, int currentParamPos,
			int companyMasterIdentifierPosition) {
		LOGGER.info("Enter getCmIdentifierQueryConfig");
		int currentPos;

		List<GtnFrameworkQueryConfig> identifierqueryList = new ArrayList<>();
		cmIdentifierConfig.setQueryConfigList(identifierqueryList);

		GtnFrameworkQueryConfig identifierInsert = new GtnFrameworkQueryConfig();
		String cmIdentifiersaveQuery = "INSERT INTO COMPANY_IDENTIFIER ( COMPANY_MASTER_SID, COMPANY_QUALIFIER_SID, COMPANY_IDENTIFIER_VALUE, IDENTIFIER_STATUS, START_DATE, END_DATE, ENTITY_CODE, INBOUND_STATUS, RECORD_LOCK_STATUS, BATCH_ID, \"SOURCE\", CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		identifierInsert.setQuery(cmIdentifiersaveQuery);
		identifierInsert.setInsertOrSelectQuery(true);
		identifierInsert.setDataTypeArray(new String[] { GtnFrameworkWebserviceConstant.INTEGER,
				GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.STRING,
				GtnFrameworkWebserviceConstant.INTEGER, "Date", "Date", GtnFrameworkWebserviceConstant.STRING,
				GtnFrameworkWebserviceConstant.STRING, GtnFrameworkWebserviceConstant.INTEGER,
				GtnFrameworkWebserviceConstant.STRING, GtnFrameworkWebserviceConstant.STRING,
				GtnFrameworkWebserviceConstant.INTEGER, "Date", GtnFrameworkWebserviceConstant.INTEGER, "Date" });
		currentPos = identifierInsert.setParamPositionArray(new int[] { companyMasterIdentifierPosition },
				currentParamPos, currentParamPos + (identifierInsert.getDataTypeArray().length - 1));

		identifierqueryList.add(identifierInsert);

		LOGGER.info("Exit getCmIdentifierQueryConfig");
		return currentPos;
	}

	private int getCmTradeClassQueryConfig(GtnFrameworkQueryEngineConfig cmTradeClassConfig, int currentParamPos,
			int companyMasterIdentifierPosition) {
		LOGGER.info("Enter getCmTradeClassQueryConfig");
		int currentPos;
		List<GtnFrameworkQueryConfig> tradeclassQueries = new ArrayList<>();
		cmTradeClassConfig.setQueryConfigList(tradeclassQueries);
		GtnFrameworkQueryConfig tradeClassInsert = new GtnFrameworkQueryConfig();
		String cmTradeclassSaveQuery = "INSERT INTO COMPANY_TRADE_CLASS ("
				+ "COMPANY_MASTER_SID, TRADE_CLASS_START_DATE, TRADE_CLASS_END_DATE, COMPANY_TRADE_CLASS, PRIOR_TRADE_CLASS, PRIOR_TRADE_CLASS_START_DATE, LAST_UPDATED_DATE, INBOUND_STATUS, RECORD_LOCK_STATUS, BATCH_ID,\"SOURCE\" ,CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		tradeClassInsert.setQuery(cmTradeclassSaveQuery);
		tradeClassInsert.setInsertOrSelectQuery(true);
		tradeClassInsert.setDataTypeArray(new String[] { GtnFrameworkWebserviceConstant.INTEGER, "Date", "Date",
				GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.STRING,
				GtnFrameworkWebserviceConstant.STRING, "Date", GtnFrameworkWebserviceConstant.STRING,
				GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.STRING,
				GtnFrameworkWebserviceConstant.STRING, GtnFrameworkWebserviceConstant.INTEGER, "Date",
				GtnFrameworkWebserviceConstant.INTEGER, "Date" });
		currentPos = tradeClassInsert.setParamPositionArray(new int[] { companyMasterIdentifierPosition },
				currentParamPos, currentParamPos + (tradeClassInsert.getDataTypeArray().length - 1));
		tradeclassQueries.add(tradeClassInsert);
		LOGGER.info("Exit getCmTradeClassQueryConfig");
		return currentPos;
	}

	private int getCmParentDetailsQueryConfig(GtnFrameworkQueryEngineConfig cmParentDetailsQueryConfig,
			int currentParamPos, int companyMasterIdentifierPosition) {
		LOGGER.info("Enter getCmParentDetailsQueryConfig");
		int currentPos;
		List<GtnFrameworkQueryConfig> cmParentDetailsQueries = new ArrayList<>();
		cmParentDetailsQueryConfig.setQueryConfigList(cmParentDetailsQueries);
		GtnFrameworkQueryConfig cmParentDetailsInsert = new GtnFrameworkQueryConfig();
		String cmParentDetailsQuery = "INSERT INTO COMPANY_PARENT_DETAILS (COMPANY_MASTER_SID, PARENT_COMPANY_MASTER_SID, PARENT_START_DATE, PARENT_END_DATE, PRIOR_PARENT_CMPY_MASTER_SID, PRIOR_PARENT_START_DATE, LAST_UPDATED_DATE,"
				+ "INBOUND_STATUS, RECORD_LOCK_STATUS, BATCH_ID,\"SOURCE\", CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE)"
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		cmParentDetailsInsert.setQuery(cmParentDetailsQuery);
		cmParentDetailsInsert.setInsertOrSelectQuery(true);
		cmParentDetailsInsert.setDataTypeArray(new String[] { GtnFrameworkWebserviceConstant.INTEGER,
				GtnFrameworkWebserviceConstant.INTEGER, "Date", "Date", GtnFrameworkWebserviceConstant.STRING, "Date",
				"Date", GtnFrameworkWebserviceConstant.STRING, GtnFrameworkWebserviceConstant.INTEGER,
				GtnFrameworkWebserviceConstant.STRING, GtnFrameworkWebserviceConstant.STRING,
				GtnFrameworkWebserviceConstant.INTEGER, "Date", GtnFrameworkWebserviceConstant.INTEGER, "Date" });
		currentPos = cmParentDetailsInsert.setParamPositionArray(new int[] { companyMasterIdentifierPosition },
				currentParamPos, currentParamPos + (cmParentDetailsInsert.getDataTypeArray().length - 1));
		cmParentDetailsQueries.add(cmParentDetailsInsert);
		LOGGER.info("Exit getCmParentDetailsQueryConfig");
		return currentPos;
	}

	private int getCmNotesTabQueryConfig(GtnFrameworkQueryEngineConfig cmNotesTabQueryConfig, int currentParamPos,
			int companyMasterIdentifierPosition) {
		LOGGER.info("Enter getCmNotesTabQueryConfig");
		int currentPos;
		List<GtnFrameworkQueryConfig> cmNotesTabQueries = new ArrayList<>();
		cmNotesTabQueryConfig.setQueryConfigList(cmNotesTabQueries);
		GtnFrameworkQueryConfig cmNotesTabInsert = new GtnFrameworkQueryConfig();
		String cmNotesTabQuery = "INSERT INTO MASTER_DATA_FILES (MASTER_TABLE_SID, MASTER_TABLE_NAME, FILE_PATH, CREATED_DATE, CREATED_BY)"
				+ "VALUES (?, ?, ?, ?, ?);";
		cmNotesTabInsert.setQuery(cmNotesTabQuery);
		cmNotesTabInsert.setInsertOrSelectQuery(true);
		cmNotesTabInsert.setDataTypeArray(
				new String[] { GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.STRING,
						GtnFrameworkWebserviceConstant.STRING, "Date", GtnFrameworkWebserviceConstant.INTEGER });
		currentPos = cmNotesTabInsert.setParamPositionArray(new int[] { companyMasterIdentifierPosition },
				currentParamPos, currentParamPos + (cmNotesTabInsert.getDataTypeArray().length - 1));
		cmNotesTabQueries.add(cmNotesTabInsert);
		LOGGER.info("Exit getCmNotesTabQueryConfig");
		return currentPos;
	}

	private List<Object> getCompanyMasterNotesTab(List<Object> saveDataList, GtnCMasterBean cmRequest) {
		LOGGER.info("Enter getCompanyMasterNotesTab");
		List<NotesTabBean> cmNotesTabRequestList = cmRequest.getGtnCMasterCompanyNotesTabBeanList();
		Date currentDate = new Date();
		for (NotesTabBean companyNotesTabRequest : cmNotesTabRequestList) {
			saveDataList.add(companyNotesTabRequest.getMasterTableName());
			saveDataList.add(companyNotesTabRequest.getFilePath());
			saveDataList.add(currentDate);
			saveDataList.add(companyNotesTabRequest.getCreatedBy());
		}
		LOGGER.info("Exit getCompanyMasterNotesTab");
		return saveDataList;
	}

	private List<Object> getCompanyParentInfo(List<Object> saveDataList, GtnCMasterBean cmRequest) {
		LOGGER.info("Enter getCompanyParentInfo");
		Date currentDate = new Date();
		List<GtnCMasterCompanyParentBean> cmParentRequest = cmRequest.getGtnCMasterCompanyParentBeanList();
		for (GtnCMasterCompanyParentBean companyParentRequest : cmParentRequest) {
			companyParentRequest.setInboundStatus("A");
			companyParentRequest.setRecordLockStatus(0);
			companyParentRequest.setBatchId("1596");
			companyParentRequest.setSource("CARS");
			saveDataList.add(companyParentRequest.getParentCompanyMasterSystemId());
			saveDataList.add(companyParentRequest.getCompanyParentStartDate());
			saveDataList.add(companyParentRequest.getCompanyParentEndDate());
			saveDataList.add(companyParentRequest.getPriorParentCmpyMasterSystemId());
			saveDataList.add(companyParentRequest.getPriorParentStartDate());
			saveDataList.add(currentDate);
			saveDataList.add(companyParentRequest.getInboundStatus());
			saveDataList.add(companyParentRequest.getRecordLockStatus());
			saveDataList.add(companyParentRequest.getBatchId());
			saveDataList.add(companyParentRequest.getSource());
			saveDataList.add(companyParentRequest.getCreatedBy());
			saveDataList.add(currentDate);
			saveDataList.add(companyParentRequest.getModifiedBy());
			saveDataList.add(currentDate);
		}
		LOGGER.info("Exit getCompanyParentInfo");
		return saveDataList;
	}

	private List<Object> getCompanyTradeClassInfo(List<Object> saveDataList, GtnCMasterBean cmRequest) {
		LOGGER.info("Enter getCompanyTradeClassInfo");
		Date currentDate = new Date();
		List<GtnCMasterCompanyTradeClassBean> cmTradeClassRequest = cmRequest.getGtnCMasterCompanyTradeClassBeanList();
		for (GtnCMasterCompanyTradeClassBean companyTradeClassRequest : cmTradeClassRequest) {
			companyTradeClassRequest.setInboundStatus("A");
			companyTradeClassRequest.setRecordLockStatus(0);
			companyTradeClassRequest.setBatchId("1598");
			companyTradeClassRequest.setSource("CARS");
			saveDataList.add(companyTradeClassRequest.getCompanyTradeClassStartDate());
			saveDataList.add(companyTradeClassRequest.getCompanyTradeClassEndDate());
			saveDataList.add(companyTradeClassRequest.getCompanyTradeClassSid());
			saveDataList.add(companyTradeClassRequest.getCompanyPriorTradeClass());
			saveDataList.add(companyTradeClassRequest.getCompanyPriorTradeClassStartDate());
			saveDataList.add(currentDate);
			saveDataList.add(companyTradeClassRequest.getInboundStatus());
			saveDataList.add(companyTradeClassRequest.getRecordLockStatus());
			saveDataList.add(companyTradeClassRequest.getBatchId());
			saveDataList.add(companyTradeClassRequest.getSource());
			saveDataList.add(companyTradeClassRequest.getCreatedBy());
			saveDataList.add(currentDate);
			saveDataList.add(companyTradeClassRequest.getModifiedBy());
			saveDataList.add(currentDate);
		}
		LOGGER.info("Exit getCompanyTradeClassInfo");
		return saveDataList;
	}

	private List<Object> getCompanyIdentifierInfo(List<Object> saveDataList, GtnCMasterBean cmRequest) {
		LOGGER.info("Enter getCompanyIdentifierInfo");
		Date currentDate = new Date();
		List<GtnCMasterIdentifierInfoBean> cmIdentifierInfoRequest = cmRequest.getGtnCMasterIdentifierInfoBeanList();
		for (GtnCMasterIdentifierInfoBean companyIdentifierInfoRequest : cmIdentifierInfoRequest) {
			companyIdentifierInfoRequest.setInboundStatus("A");
			companyIdentifierInfoRequest.setRecordLockStatus(0);
			companyIdentifierInfoRequest.setBatchId("1594");
			companyIdentifierInfoRequest.setSource("CARS");
			saveDataList.add(companyIdentifierInfoRequest.getCompanyQualifierSid());
			saveDataList.add(companyIdentifierInfoRequest.getCompanyIdentifierValue());
			saveDataList.add(companyIdentifierInfoRequest.getIdentifierStatus());
			saveDataList.add(companyIdentifierInfoRequest.getCompanyIdentifierStartDate());
			saveDataList.add(companyIdentifierInfoRequest.getCompanyIdentifierEndDate());
			saveDataList.add(companyIdentifierInfoRequest.getEntityCode());
			saveDataList.add(companyIdentifierInfoRequest.getInboundStatus());
			saveDataList.add(companyIdentifierInfoRequest.getRecordLockStatus());
			saveDataList.add(companyIdentifierInfoRequest.getBatchId());
			saveDataList.add(companyIdentifierInfoRequest.getSource());
			saveDataList.add(companyIdentifierInfoRequest.getCreatedBy());
			saveDataList.add(companyIdentifierInfoRequest.getCreatedDate());
			saveDataList.add(companyIdentifierInfoRequest.getModifiedBy());
			saveDataList.add(currentDate);
		}
		LOGGER.info("Exit getCompanyIdentifierInfo");
		return saveDataList;
	}

	private List<Object> getCompanyUdc(List<Object> saveDataList, GtnCMasterBean cmRequest) {
		LOGGER.info("Enter getCompanyUdc");
		GtnCMasterCompanyUdcInfoBean cmUdcInfoRequest = cmRequest.getGtnCMasterCompanyUdcInfoBean();
		cmUdcInfoRequest.setMasterType("COMPANY_MASTER");
		saveDataList.add(cmUdcInfoRequest.getMasterType());
		saveDataList.add(cmUdcInfoRequest.getUdc1());
		saveDataList.add(cmUdcInfoRequest.getUdc2());
		saveDataList.add(cmUdcInfoRequest.getUdc3());
		saveDataList.add(cmUdcInfoRequest.getUdc4());
		saveDataList.add(cmUdcInfoRequest.getUdc5());
		saveDataList.add(cmUdcInfoRequest.getUdc6());
		saveDataList.add(cmUdcInfoRequest.getUdc7());
		saveDataList.add(cmUdcInfoRequest.getUdc8());
		saveDataList.add(cmUdcInfoRequest.getUdc9());
		saveDataList.add(cmUdcInfoRequest.getUdc10());
		saveDataList.add(cmUdcInfoRequest.getUdc11());
		saveDataList.add(cmUdcInfoRequest.getUdc12());
		// saveDataList.add(null); not needed for CM
		LOGGER.info("Exit getCompanyUdc");
		return saveDataList;
	}

	private List<Object> getCompanyMasterInfo(List<Object> saveDataList, GtnCMasterBean cmRequest) {
		LOGGER.info("Enter getCompanyMasterInfo");
		Date currentDate = new Date();
		GtnCMasterInformationBean companyInfoRequest = cmRequest.getGtnCMasterInformationBean();
		companyInfoRequest.setInboundStatus("A");
		companyInfoRequest.setRecordLockStatus(0);
		companyInfoRequest.setBatchId("20150731");
		companyInfoRequest.setSource("Cars");
		saveDataList.add(companyInfoRequest.getCompanyId());
		saveDataList.add(companyInfoRequest.getCompanyNo());
		saveDataList.add(companyInfoRequest.getCompanyName());
		saveDataList.add(companyInfoRequest.getCompanyType());
		saveDataList.add(companyInfoRequest.getCompanyStatus());
		saveDataList.add(companyInfoRequest.getCompanyCategory());
		saveDataList.add(companyInfoRequest.getCompanyGroup());
		saveDataList.add(companyInfoRequest.getCompanyStartDate());
		saveDataList.add(companyInfoRequest.getCompanyEndDate());
		saveDataList.add(companyInfoRequest.getOrganizationKey());
		saveDataList.add(companyInfoRequest.getLives());
		saveDataList.add(companyInfoRequest.getFinancialSystem());
		saveDataList.add(companyInfoRequest.getAddress1());
		saveDataList.add(companyInfoRequest.getAddress2());
		saveDataList.add(companyInfoRequest.getCity());
		saveDataList.add(companyInfoRequest.getState());
		saveDataList.add(companyInfoRequest.getZipCode());
		saveDataList.add(companyInfoRequest.getCountry());
		saveDataList.add(companyInfoRequest.getRegionCode());
		saveDataList.add(companyInfoRequest.getLastUpdatedDate());
		saveDataList.add(companyInfoRequest.getInternalNotes());
		saveDataList.add(companyInfoRequest.getInboundStatus());
		saveDataList.add(companyInfoRequest.getRecordLockStatus());
		saveDataList.add(companyInfoRequest.getBatchId());
		saveDataList.add(companyInfoRequest.getSource());
		saveDataList.add(companyInfoRequest.getCreatedBy());
		saveDataList.add(
				companyInfoRequest.getCompanyMasterSystemId() != 0 ? companyInfoRequest.getCreatedDate() : currentDate);
		saveDataList.add(companyInfoRequest.getModifiedBy());
		saveDataList.add(currentDate);

		if (companyInfoRequest.getCompanyMasterSystemId() != 0) {
			saveDataList.add(companyInfoRequest.getCompanyMasterSystemId());
		} else {
			saveDataList.add(null);
		}
		LOGGER.info("Enter getCompanyMasterInfo");
		return saveDataList;
	}

	private GtnFrameworkQueryEngineConfig getCompanyMasterEditQueryConfig(
			GtnCMasterInformationBean companyInformationBean) {
		LOGGER.info("Enter getCompanyMasterQueryConfig");
		GtnFrameworkQueryConfig cmUpdateConfig = new GtnFrameworkQueryConfig();
		String cmUpdateQuery = "UPDATE COMPANY_MASTER SET COMPANY_ID= ?, COMPANY_NO = ?, COMPANY_NAME = ?, COMPANY_TYPE = ?, COMPANY_STATUS = ?,COMPANY_CATEGORY = ?, COMPANY_GROUP = ?,"
				+ "COMPANY_START_DATE = ?, COMPANY_END_DATE = ?, ORGANIZATION_KEY = ?, LIVES = ?, FINANCIAL_SYSTEM = ?, ADDRESS1 = ?, ADDRESS2 = ?, CITY = ?, STATE = ?,"
				+ " ZIP_CODE = ?, COUNTRY = ?, REGION_CODE = ?, LAST_UPDATED_DATE = ?, INTERNAL_NOTES = ?, INBOUND_STATUS=?, RECORD_LOCK_STATUS=?, BATCH_ID=?, \"SOURCE\"=?, "
				+ "CREATED_BY=?, CREATED_DATE=?, MODIFIED_BY=?, MODIFIED_DATE=? WHERE COMPANY_MASTER_SID ="
				+ companyInformationBean.getCompanyMasterSystemId() + ";";
		cmUpdateConfig.setQuery(cmUpdateQuery);
		cmUpdateConfig.setUpdateOrDeleteQuery(true);
		cmUpdateConfig.setDataTypeArray(new String[] { GtnFrameworkWebserviceConstant.STRING,
				GtnFrameworkWebserviceConstant.STRING, GtnFrameworkWebserviceConstant.STRING,
				GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.INTEGER,
				GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.INTEGER, "Date", "Date",
				GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.INTEGER,
				GtnFrameworkWebserviceConstant.STRING, GtnFrameworkWebserviceConstant.STRING,
				GtnFrameworkWebserviceConstant.STRING, GtnFrameworkWebserviceConstant.STRING,
				GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.STRING,
				GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.STRING, "Date",
				GtnFrameworkWebserviceConstant.STRING, GtnFrameworkWebserviceConstant.STRING,
				GtnFrameworkWebserviceConstant.INTEGER, GtnFrameworkWebserviceConstant.STRING,
				GtnFrameworkWebserviceConstant.STRING, GtnFrameworkWebserviceConstant.INTEGER, "Date",
				GtnFrameworkWebserviceConstant.INTEGER, "Date" });
		cmUpdateConfig.setParamPositionArray(0, 29);

		List<GtnFrameworkQueryConfig> cmqueries = new ArrayList<>();
		cmqueries.add(cmUpdateConfig);
		cmqueries.addAll(getChildTableDeleteQuery(companyInformationBean));
		GtnFrameworkQueryEngineConfig companyMasterConfig = new GtnFrameworkQueryEngineConfig();
		companyMasterConfig.setQueryConfigList(cmqueries);
		LOGGER.info("Exit getCompanyMasterQueryConfig");
		return companyMasterConfig;
	}

	private List<GtnFrameworkQueryConfig> getChildTableDeleteQuery(GtnCMasterInformationBean companyInformationBean) {
		LOGGER.info("Enter getChildTableDeleteQuery");
		List<GtnFrameworkQueryConfig> childDeleteQueries = new ArrayList<>();
		String udcDeleteQuery = getUdcDeleteQuery(companyInformationBean);
		String identifierDeleteQuery = getIdentifierDeleteQuery(companyInformationBean);
		String tradeClassDeleteQuery = getTradeClassDeleteQuery(companyInformationBean);
		String parentDetailsDeleteQuery = getParentDetailsDeleteQuery(companyInformationBean);
		String notesTabDeleteQuery = getnotesTabDeleteQuery(companyInformationBean);

		GtnFrameworkQueryConfig udcDeleteQueryConfig = new GtnFrameworkQueryConfig();
		udcDeleteQueryConfig.setQuery(udcDeleteQuery);
		udcDeleteQueryConfig.setUpdateOrDeleteQuery(true);
		udcDeleteQueryConfig.setDataTypeArray(null);

		GtnFrameworkQueryConfig identifierDeleteQueryConfig = new GtnFrameworkQueryConfig();
		identifierDeleteQueryConfig.setQuery(identifierDeleteQuery);
		identifierDeleteQueryConfig.setUpdateOrDeleteQuery(true);
		identifierDeleteQueryConfig.setDataTypeArray(null);

		GtnFrameworkQueryConfig tradeClassDeleteQueryConfig = new GtnFrameworkQueryConfig();
		tradeClassDeleteQueryConfig.setQuery(tradeClassDeleteQuery);
		tradeClassDeleteQueryConfig.setUpdateOrDeleteQuery(true);
		tradeClassDeleteQueryConfig.setDataTypeArray(null);

		GtnFrameworkQueryConfig parentDetailsDeleteQueryConfig = new GtnFrameworkQueryConfig();
		parentDetailsDeleteQueryConfig.setQuery(parentDetailsDeleteQuery);
		parentDetailsDeleteQueryConfig.setUpdateOrDeleteQuery(true);
		parentDetailsDeleteQueryConfig.setDataTypeArray(null);

		GtnFrameworkQueryConfig notesTabDeleteConfig = new GtnFrameworkQueryConfig();
		notesTabDeleteConfig.setQuery(notesTabDeleteQuery);
		notesTabDeleteConfig.setUpdateOrDeleteQuery(true);
		notesTabDeleteConfig.setDataTypeArray(null);

		childDeleteQueries.add(udcDeleteQueryConfig);
		childDeleteQueries.add(identifierDeleteQueryConfig);
		childDeleteQueries.add(tradeClassDeleteQueryConfig);
		childDeleteQueries.add(parentDetailsDeleteQueryConfig);
		childDeleteQueries.add(notesTabDeleteConfig);
		LOGGER.info("Exit getChildTableDeleteQuery");
		return childDeleteQueries;
	}

	public String getnotesTabDeleteQuery(GtnCMasterInformationBean companyInformationBean) {
		return "DELETE FROM MASTER_DATA_FILES WHERE MASTER_TABLE_SID="
				+ companyInformationBean.getCompanyMasterSystemId() + " AND MASTER_TABLE_NAME='" + "COMPANY_MASTER'";
	}

	public String getParentDetailsDeleteQuery(GtnCMasterInformationBean companyInformationBean) {
		
		String queryToAppendParentDetails = queryGenerateParentDetails(companyInformationBean);
		if(queryToAppendParentDetails!=null)
		{
		return "DELETE FROM COMPANY_PARENT_DETAILS WHERE COMPANY_MASTER_SID="
				+ companyInformationBean.getCompanyMasterSystemId()+queryToAppendParentDetails;
		}
		return gtnWsSqlService.getQuery(Arrays.asList(companyInformationBean.getCompanyMasterSystemId(),companyInformationBean.getCompanyMasterSystemId()), "deleteCompanyParentDetailsForCompanyMasterSid");
		
	}

	private String queryGenerateParentDetails(GtnCMasterInformationBean companyInformationBean) {
		
		List<Integer>parentDetailsSid=findParentDetailsSidFromTable(companyInformationBean.getCompanyMasterSystemId());
		LOGGER.info("ParentDetailsSidList size:"+parentDetailsSidList.size());
		LOGGER.info("ParentDetails Sid List Size:"+parentDetailsSid.size());
		if(!parentDetailsSidList.isEmpty() && !parentDetailsSid.isEmpty())
		{
		parentDetailsSid.removeAll(parentDetailsSidList);
		}
		StringBuilder queryBuilderParentDetails = new StringBuilder();
		String queryToAppendParentDetails=null;
		if(!parentDetailsSid.isEmpty())
		{
		queryBuilderParentDetails = buildDeleteParentDetailsQuery(parentDetailsSid, queryBuilderParentDetails);
		queryToAppendParentDetails=queryBuilderParentDetails.toString();
		}
		return queryToAppendParentDetails;
	}

	private StringBuilder buildDeleteParentDetailsQuery(List<Integer> parentDetailsSid,
			StringBuilder queryBuilderParentDetails) {
		StringBuilder queryParentDetailsStringBuilder=queryBuilderParentDetails;
		for(int i=0;i<=parentDetailsSid.size()-1;i++)
		{
			if(i==0)
			{
				queryParentDetailsStringBuilder=new StringBuilder(" AND COMPANY_PARENT_DETAILS_SID="+parentDetailsSid.get(0));
			}
			else
			{
				queryParentDetailsStringBuilder.append(" OR  COMPANY_PARENT_DETAILS_SID="+parentDetailsSid.get(i));
			}
		}
		return queryParentDetailsStringBuilder;
	}

	public String getTradeClassDeleteQuery(GtnCMasterInformationBean companyInformationBean) {
		String queryToAppend = queryGenerateTradeClass(companyInformationBean);
		if(queryToAppend!=null)
		{
		return "DELETE FROM COMPANY_TRADE_CLASS WHERE COMPANY_MASTER_SID="
				+ companyInformationBean.getCompanyMasterSystemId()+queryToAppend;
		}
		return gtnWsSqlService.getQuery(Arrays.asList(companyInformationBean.getCompanyMasterSystemId(),companyInformationBean.getCompanyMasterSystemId()), "deleteCompanyTradeClassForCompanyMasterSid");
	}

	private String queryGenerateTradeClass(GtnCMasterInformationBean companyInformationBean) {
		List<Integer>tradeclassSid=findTradeClassSidFromTable(companyInformationBean.getCompanyMasterSystemId());
		LOGGER.info("tradeClassSidList size:"+tradeClassSidList.size());
		LOGGER.info("tradeClass Sid List Size:"+tradeclassSid.size());
		if(!tradeClassSidList.isEmpty() && !tradeclassSid.isEmpty())
		{
		tradeclassSid.removeAll(tradeClassSidList);
		}
		StringBuilder queryBuilder = new StringBuilder();
		String queryToAppend=null;
		if(!tradeclassSid.isEmpty())
		{
		queryBuilder = buildDeleteQuery(tradeclassSid, queryBuilder);
		queryToAppend=queryBuilder.toString();
		}
		return queryToAppend;
	}

	private StringBuilder buildDeleteQuery(List<Integer> tradeclassSid, StringBuilder stringBuilder) {
		StringBuilder query=stringBuilder;
		for(int i=0;i<=tradeclassSid.size()-1;i++)
		{
			if(i==0)
			{
				query=new StringBuilder(" AND COMPANY_TRADE_CLASS_SID="+tradeclassSid.get(0));
			}
			else
			{
				query.append(" OR  COMPANY_TRADE_CLASS_SID="+tradeclassSid.get(i));
			}
		}
		return query;
	}

	public String getIdentifierDeleteQuery(GtnCMasterInformationBean companyInformationBean) {
		return "DELETE FROM COMPANY_IDENTIFIER WHERE COMPANY_MASTER_SID="
				+ companyInformationBean.getCompanyMasterSystemId();
	}

	public String getUdcDeleteQuery(GtnCMasterInformationBean companyInformationBean) {
		return "DELETE FROM UDCS WHERE MASTER_SID =" + companyInformationBean.getCompanyMasterSystemId()
				+ " AND MASTER_TYPE='" + "COMPANY_MASTER" + "'";
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_WS_CP_DETAILS_FOR_COMPANY_INSERT_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse saveCPDetails(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		LOGGER.info("Enter saveCPDetails");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse gtnWsGeneralResponse = new GtnWsGeneralResponse();
		try {
			int companymasterSid = Integer
					.parseInt(gtnWsRequest.getGtnWsGeneralRequest().getExtraParameter().toString());
			Object[] saveData = { companymasterSid };
			GtnFrameworkDataType[] saveDataHeader = { GtnFrameworkDataType.INTEGER };
			String insertQuery = gtnWsSqlService.getQuery("company-CPDetailsInsert");
			gtnSqlQueryEngine.executeInsertOrUpdateQuery(insertQuery, saveData, saveDataHeader);
		} catch (GtnFrameworkGeneralException ex) {
			LOGGER.error(GtnFrameworkWebserviceConstant.EXCEPTION_IN_EXECUTIG_QUERY, ex);
			gtnWsGeneralResponse.setSucess(false);
			gtnWsGeneralResponse.setGtnGeneralException(ex);
		}
		LOGGER.info("Exit saveCPDetails");
		gtnWsGeneralResponse.setSucess(true);
		gtnResponse.setGtnWsGeneralResponse(gtnWsGeneralResponse);
		return gtnResponse;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_WS_CM_FETCH_HELPERSID, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse fetchCurrentMonth(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		LOGGER.info("Enter fetchCurrentMonth");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnCompanyMasterResponse generalCmResponse = new GtnCompanyMasterResponse();
		try {
			gtnResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			String query = fetchCurrentMonthQuery(gtnWsRequest);
			List<Object> resultList = (List<Object>) gtnSqlQueryEngine.executeSelectQuery(query);
			generalCmResponse.setHelperTableSid(Integer.parseInt(String.valueOf(resultList.get(0))));
			gtnResponse.setGtnCompanyMasterResponse(generalCmResponse);
			gtnResponse.getGtnWsGeneralResponse().setSucess(true);
			return gtnResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gtnResponse.getGtnWsGeneralResponse().setSucess(false);
			LOGGER.error("Exception while Excuting fetchCurrentMonth Query", ex);
			gtnResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gtnResponse;
		} finally {
			LOGGER.info("Exit fetchCurrentMonth");
		}
	}

	public String fetchCurrentMonthQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		StringBuilder sql = new StringBuilder();
		sql.append(gtnWsSqlService.getQuery("getFetchCurrentMonthQuery"));
		sql.append("WHERE DESCRIPTION = '").append(gtnWsRequest.getGtnWsGeneralRequest().getExtraParameter())
				.append("'");

		return sql.toString();

	}

	private int performUpdateForCompanyIdWithStatusD(GtnCMasterBean masterbean) {
		boolean isCompanyExist = false;
		List<Long> resultsDb4 = checkIfCompanyIdExistsWithStatusD(masterbean);
		if (resultsDb4 != null) {
			isCompanyExist = isCompanyExistCheck(isCompanyExist, resultsDb4);
		}
		List<String> compIdCriteria = new ArrayList<>();
		compIdCriteria.add(masterbean.getGtnCMasterInformationBean().getCompanyId());
		int countUpdate = 0;
		if (isCompanyExist) {
			try {
				countUpdate = gtnSqlQueryEngine.executeInsertOrUpdateQuery(
						gtnWsSqlService.getQuery(compIdCriteria, "updateCompanyIdWithStatusD"));
			} catch (GtnFrameworkGeneralException e) {
				LOGGER.info("Error in Updating");
			}
		}
		return countUpdate;
	}

	private boolean isCompanyExistCheck(boolean isCompExist, List<Long> resultsDb4) {
		boolean isCompanyExist = isCompExist;
		if (!resultsDb4.isEmpty()) {
			isCompanyExist = resultsDb4.get(0) > (long) 0;
		}
		return isCompanyExist;
	}

	@SuppressWarnings("unchecked")
	private int getSysIdForCompanyIdWithStatusD(GtnCMasterBean masterbean) {
		List<Integer> sysId = new ArrayList<>();
		List<String> compIdCriteria = new ArrayList<>();
		compIdCriteria.add(masterbean.getGtnCMasterInformationBean().getCompanyId());
		try {
			sysId = (List<Integer>) (gtnSqlQueryEngine
					.executeSelectQuery(gtnWsSqlService.getQuery(compIdCriteria, "getSysIdWithStatusD")));

		} catch (GtnFrameworkGeneralException e) {
			LOGGER.info("Error in Updating");
		}

		return sysId.get(0);
	}

	@SuppressWarnings("unchecked")
	private List<Long> checkIfCompanyIdExistsWithStatusD(GtnCMasterBean masterbean) {
		List<String> compNoCriteria = new ArrayList<>();
		compNoCriteria.add(masterbean.getGtnCMasterInformationBean().getCompanyId());

		List<Long> resultsDb3 = new ArrayList<>();
		try {
			resultsDb3 = (List<Long>) gtnSqlQueryEngine
					.executeSelectQuery(gtnWsSqlService.getQuery(compNoCriteria, "getCompanyIdWithStatusD"));

		} catch (GtnFrameworkGeneralException e) {

			LOGGER.error("Exception Occured while Checking Whetehr Company Exists");
		}
		return resultsDb3;
	}

}
