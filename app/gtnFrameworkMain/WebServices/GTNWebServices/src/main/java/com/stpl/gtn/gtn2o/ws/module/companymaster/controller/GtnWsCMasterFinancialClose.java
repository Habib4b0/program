package com.stpl.gtn.gtn2o.ws.module.companymaster.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterFinancialCloseBean;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.controller.GtnWsSearchServiceController;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.logic.GtnWsSearchQueryGenerationLogic;
import com.stpl.gtn.gtn2o.ws.module.companymaster.config.GtnWsCMasterFinancialCloseConfig;
import com.stpl.gtn.gtn2o.ws.module.companymaster.constants.GtnWsCMasterConstants;
import com.stpl.gtn.gtn2o.ws.module.companymaster.quartz.GtnCmQuartzListener;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.cmrequest.GtnCMasterRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@RestController
public class GtnWsCMasterFinancialClose {
	public GtnWsCMasterFinancialClose() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsCMasterFinancialClose.class);

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsSearchServiceController gTNSearchServiceController;

	@Autowired
	private GtnCmQuartzListener quartzListner;

	public GtnWsSearchServiceController getgTNSearchServiceController() {
		return gTNSearchServiceController;
	}

	public void setgTNSearchServiceController(GtnWsSearchServiceController gTNSearchServiceController) {
		this.gTNSearchServiceController = gTNSearchServiceController;
	}

	public GtnCmQuartzListener getQuartzListner() {
		return quartzListner;
	}

	public void setQuartzListner(GtnCmQuartzListener quartzListner) {
		this.quartzListner = quartzListner;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_CM_FINANCIAL_CLOSE
			+ GtnWebServiceUrlConstants.GTN_CM_FINANCIAL_MANUAL_CLOSE_SERVICE, method = RequestMethod.POST)

	public GtnUIFrameworkWebserviceResponse setManualCloseOrOpen(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Entering setManualCloseOrOpen");
		SimpleDateFormat simpleDate = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		GtnUIFrameworkWebserviceResponse manualCloseResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse manualCloseGeneralResponse = new GtnWsGeneralResponse();
		GtnCMasterRequest gtnCMasterRequest = gtnWsRequest.getGtnCMasterRequest();
		List<GtnCMasterFinancialCloseBean> list = gtnCMasterRequest.getGtnCMasterBean()
				.getGtnCMasterFinancialCloseBean();
		@SuppressWarnings({ "rawtypes" })
		List<Object[]> searchresults = new ArrayList();
		GtnCMasterFinancialCloseBean gtnCMasterFinancialCloseBean = list.get(0);
		Object[] searchparm = { gtnCMasterFinancialCloseBean.getCompanyMasterSid(),
				gtnCMasterFinancialCloseBean.getUserId(), gtnCMasterFinancialCloseBean.getSessionId() };
		GtnFrameworkDataType[] searchparmtype = { GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER,
				GtnFrameworkDataType.INTEGER };

		try {
			searchresults = (List<Object[]>) gtnSqlQueryEngine
					.executeSelectQuery(gtnWsSqlService.getQuery("searchForFinancialTemp"), searchparm, searchparmtype);
		} catch (GtnFrameworkGeneralException ex) {
			logger.error(GtnFrameworkWebserviceConstant.EXCEPTION_IN_EXECUTIG_QUERY, ex);
		}

		for (Object[] list1 : searchresults) {
			Object[] saveData = { list1[0], list1[1], list1[2], list1[7], simpleDate.format(list1[8]), list1[9],
					simpleDate.format(list1[10]), list1[11], list1[12] };
			GtnFrameworkDataType[] saveDataHeader = { GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER,
					GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.STRING,
					GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.STRING, GtnFrameworkDataType.INTEGER,
					GtnFrameworkDataType.INTEGER };
			try {
				String insertQuery = gtnWsSqlService.getQuery("insertUpdateCompanyFinancial");
				gtnSqlQueryEngine.executeInsertOrUpdateQuery(insertQuery, saveData, saveDataHeader);
			} catch (GtnFrameworkGeneralException ex) {
				logger.error(GtnFrameworkWebserviceConstant.EXCEPTION_IN_EXECUTIG_QUERY, ex);
				manualCloseGeneralResponse.setSucess(false);
				manualCloseGeneralResponse.setGtnGeneralException(ex);
			} finally {
				logger.info("Exit setManualCloseOrOpen");
			}
		}

		manualCloseGeneralResponse.setSucess(true);
		manualCloseResponse.setGtnWsGeneralResponse(manualCloseGeneralResponse);
		return manualCloseResponse;
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_CM_FINANCIAL_CLOSE
			+ GtnWebServiceUrlConstants.GTN_CM_FINANCIAL_AUTOMATIC_CLOSE_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse setAutomaticClose(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Entering setAutomaticClose");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse gtnWsGeneralResponse = new GtnWsGeneralResponse();
		GtnCMasterRequest gtnCMasterRequest = gtnWsRequest.getGtnCMasterRequest();
		List<GtnCMasterFinancialCloseBean> gtnCMasterFinancialCloseBean = gtnCMasterRequest.getGtnCMasterBean()
				.getGtnCMasterFinancialCloseBean();
		GtnFrameworkDataType[] saveDataHeader = { GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING, GtnFrameworkDataType.INTEGER };
		try {
			for (GtnCMasterFinancialCloseBean gtnCMasterFinancialCloseBean1 : gtnCMasterFinancialCloseBean) {
				Object[] saveData = { gtnCMasterFinancialCloseBean1.getCompanyMasterSid(),
						gtnCMasterFinancialCloseBean1.getBusinessDayDdlb(), gtnCMasterFinancialCloseBean1.getHourDdlb(),
						gtnCMasterFinancialCloseBean1.getMinuteValue(),
						gtnCMasterFinancialCloseBean1.getCalenderDdlb() };
				String insertQuery = gtnWsSqlService.getQuery("autoInsertorUpdate");
				gtnSqlQueryEngine.executeInsertOrUpdateQuery(insertQuery, saveData, saveDataHeader);
				quartzListner.scheduleCompanyFinancialClose();
			}
			gtnWsGeneralResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			logger.error(GtnFrameworkWebserviceConstant.EXCEPTION_IN_EXECUTIG_QUERY, ex);
			gtnWsGeneralResponse.setSucess(false);
			gtnWsGeneralResponse.setGtnGeneralException(ex);

		} finally {
			logger.info("Exit setAutomaticClose");
		}
		gtnResponse.setGtnWsGeneralResponse(gtnWsGeneralResponse);
		return gtnResponse;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_CM_FINANCIAL_CLOSE
			+ GtnWebServiceUrlConstants.GTN_CM_GET_STATUS, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getStatusFC(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Entering setAutomaticClose");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse gtnWsGeneralResponse = new GtnWsGeneralResponse();
		GtnCMasterRequest gtnCMasterRequest = gtnWsRequest.getGtnCMasterRequest();
		Object[] saveData = gtnCMasterRequest.getSaveData();
		GtnFrameworkDataType[] saveDataHeader = { GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER,
				GtnFrameworkDataType.INTEGER };
		try {
			String insertQuery = getFCStatusQuery();
			List<Object[]> selectedData = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(insertQuery, saveData,
					saveDataHeader);
			String status = StringUtils.EMPTY;
			if (!selectedData.isEmpty()) {
				Object[] obj = selectedData.get(0);
				status = obj[1] != null ? (String) obj[1] : StringUtils.EMPTY;
			}
			gtnResponse.setOutBountData(new Object[] { status });
		} catch (GtnFrameworkGeneralException ex) {
			logger.error("Exception in executing query-", ex);
			gtnWsGeneralResponse.setSucess(false);
			gtnWsGeneralResponse.setGtnGeneralException(ex);
		} finally {
			logger.info("Exit getFCStatus");
		}
		gtnWsGeneralResponse.setSucess(true);
		gtnResponse.setGtnWsGeneralResponse(gtnWsGeneralResponse);
		return gtnResponse;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_CM_FC_HISTORY_DATA_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getFCHistory(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse gtnWsGeneralResponse = new GtnWsGeneralResponse();
		GtnWsCMasterFinancialCloseConfig gtnWsCMasterFinancialCloseConfig = new GtnWsCMasterFinancialCloseConfig();
		try {

			String queryName = gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().getSearchQueryName();
			boolean isCount = gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().isCount();

			GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig = gtnWsCMasterFinancialCloseConfig
					.getSearchQueryConfigMap().get(queryName);

			GtnWsSearchQueryGenerationLogic searchQueryGenerationLogic = new GtnWsSearchQueryGenerationLogic(
					gtnWebServiceSearchQueryConfig, gtnUIFrameworkWebserviceRequest);
			String generatedQuery = searchQueryGenerationLogic.generateSearchQuery();

			List<Object[]> resultList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(generatedQuery);
			GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();

			if (!isCount && gtnWebServiceSearchQueryConfig.getFieldToColumnDetailsMap() != null) {

				gTNSearchServiceController.getCustomizedSearchFormFromObject(resultList, gtnWebServiceSearchQueryConfig,
						gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().getSearchColumnNameList());
			}

			if (isCount) {

				gtnSerachResponse.setCount(Integer.parseInt(String.valueOf(resultList.get(0))));

			} else {
				GtnUIFrameworkDataTable gtnUIFrameworkDataTable = new GtnUIFrameworkDataTable();
				gtnUIFrameworkDataTable.addData(resultList);
				gtnSerachResponse.setResultSet(gtnUIFrameworkDataTable);
			}
			gtnResponse.setGtnSerachResponse(gtnSerachResponse);
		} catch (Exception exception) {
			logger.error("Exception in getSearchResultForAllModule ", exception);
			gtnWsGeneralResponse.setSucess(false);
			gtnWsGeneralResponse.setGtnGeneralException(exception);
		} finally {
			logger.info("Exit getSearchResultForAllModule");
		}
		gtnResponse.setGtnWsGeneralResponse(gtnWsGeneralResponse);
		return gtnResponse;
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_CM_FC_SWAP_AUTO_MANUAL_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse swapAutoManual(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Entering swapAutoManual");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse gtnWsGeneralResponse = new GtnWsGeneralResponse();
		GtnCMasterRequest gtnCMasterRequest = gtnWsRequest.getGtnCMasterRequest();
		Object[] saveData = gtnCMasterRequest.getSaveData();
		GtnFrameworkDataType[] saveDataHeader = { GtnFrameworkDataType.INTEGER };
		try {
			String swapQuery = getSwapQuery();
			gtnSqlQueryEngine.executeInsertOrUpdateQuery(swapQuery, saveData, saveDataHeader);
		} catch (GtnFrameworkGeneralException ex) {
			logger.error(GtnFrameworkWebserviceConstant.EXCEPTION_IN_EXECUTIG_QUERY, ex);
			gtnWsGeneralResponse.setSucess(false);
			gtnWsGeneralResponse.setGtnGeneralException(ex);
		} finally {
			logger.info("Exit swapAutoManual");
		}
		gtnWsGeneralResponse.setSucess(true);
		gtnResponse.setGtnWsGeneralResponse(gtnWsGeneralResponse);
		return gtnResponse;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_CM_FINANCIAL_CLOSE
			+ GtnWebServiceUrlConstants.GTN_CM_GET_CALENDAR, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getCalendar(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Entering getCalendar");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse gtnWsGeneralResponse = new GtnWsGeneralResponse();
		try {
			String swapQuery = getCalendarValuesQuery();
			List<Object[]> calendarValues = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(swapQuery);
			GtnUIFrameworkWebserviceComboBoxResponse comboBoxResponse = new GtnUIFrameworkWebserviceComboBoxResponse();
			comboBoxResponse.setComboBoxList(calendarValues);
			gtnResponse.setGtnUIFrameworkWebserviceComboBoxResponse(comboBoxResponse);
		} catch (GtnFrameworkGeneralException ex) {
			logger.error(GtnFrameworkWebserviceConstant.EXCEPTION_IN_EXECUTIG_QUERY, ex);
			gtnWsGeneralResponse.setSucess(false);
			gtnWsGeneralResponse.setGtnGeneralException(ex);
		} finally {
			logger.info("Exit getCalendar");
		}
		gtnWsGeneralResponse.setSucess(true);
		gtnResponse.setGtnWsGeneralResponse(gtnWsGeneralResponse);
		return gtnResponse;
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_CM_FINANCIAL_CLOSE
			+ GtnWebServiceUrlConstants.GTN_CM_BUSINESS_YEAR, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadBusinessYear(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalWSResponse = new GtnWsGeneralResponse();
		generalWSResponse.setSucess(true);
		try {

			Calendar date = new GregorianCalendar();
			int year = date.get(Calendar.YEAR) + 1;
			GtnUIFrameworkWebserviceComboBoxResponse comboBoxResponse = new GtnUIFrameworkWebserviceComboBoxResponse();

			for (int i = year - 5; i <= year; i++) {
				comboBoxResponse.addItemCodeList(String.valueOf(i));
				comboBoxResponse.addItemValueList(String.valueOf(i));
			}

			comboBoxResponse.setDefaultValue(String.valueOf(year - 1));
			gtnResponse.setGtnUIFrameworkWebserviceComboBoxResponse(comboBoxResponse);

			gtnResponse.setGtnWsGeneralResponse(generalWSResponse);
		} catch (Exception ex) {
			logger.error("Exception in loadBusinessYear ", ex);
		}
		return gtnResponse;
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_CM_FINANCIAL_CLOSE
			+ GtnWebServiceUrlConstants.GTN_CM_IS_THERE_BUSINESSDAY_FOR_MONTHS, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse isThereBusinessdayForMonths(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Entering getFCHistoryCount");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse gtnWsGeneralResponse = new GtnWsGeneralResponse();
		GtnCMasterRequest gtnCMasterRequest = gtnWsRequest.getGtnCMasterRequest();
		Object[] data = gtnCMasterRequest.getSaveData();
		GtnFrameworkDataType[] dataHeader = { GtnFrameworkDataType.INTEGER };
		try {
			String stringQuery = getHolidaysMonthWiseQuery();
			@SuppressWarnings("unchecked")
			List<Object[]> monthWiseHolidays = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(stringQuery,
					new Object[] { data[0] }, dataHeader);
			Calendar cal = Calendar.getInstance();
			Set<Integer> awaySet = new HashSet<>();
			int busineesDay = (int) data[1];
			for (Object[] objects : monthWiseHolidays) {
				cal.set(Calendar.MONTH, (Integer) objects[0]);
				if (busineesDay + (Integer) objects[1] >= cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
					awaySet.add((Integer) objects[0]);
				}
			}
			gtnResponse.setOutBountData(awaySet.toArray(new Object[awaySet.size()]));
		} catch (GtnFrameworkGeneralException ex) {
			logger.error(GtnFrameworkWebserviceConstant.EXCEPTION_IN_EXECUTIG_QUERY, ex);
			gtnWsGeneralResponse.setSucess(false);
			gtnWsGeneralResponse.setGtnGeneralException(ex);
		} finally {
			logger.info("Exit isThereBusinessdayForMonths");
		}
		gtnWsGeneralResponse.setSucess(true);
		gtnResponse.setGtnWsGeneralResponse(gtnWsGeneralResponse);
		return gtnResponse;
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_CM_FINANCIAL_CLOSE
			+ GtnWebServiceUrlConstants.GTN_CM_FINANCIAL_TEMP_INSERT, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse insertToTempTable(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Entering insertTempQuery");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse gtnWsGeneralResponse = new GtnWsGeneralResponse();
		GtnCMasterRequest gtnCMasterRequest = gtnWsRequest.getGtnCMasterRequest();
		Object[] cmBean = gtnCMasterRequest.getSaveData();
		String periodQuery = getPeriodQuery(String.valueOf(cmBean[3]));
		String statusQuery = getStatusQuery(String.valueOf(cmBean[7]));
		try {
			@SuppressWarnings("unchecked")
			List<Object> periodValues = (List<Object>) gtnSqlQueryEngine.executeSelectQuery(periodQuery);
			@SuppressWarnings("unchecked")
			List<Integer> statusValues = (List<Integer>) gtnSqlQueryEngine.executeSelectQuery(statusQuery);
			Integer periodId = periodValues.isEmpty() ? 0 : Integer.parseInt(String.valueOf(periodValues.get(0)));
			Integer statusId = statusValues.isEmpty() ? new Integer(0) : statusValues.get(0);

			String stringQuery = gtnWsSqlService.getQuery("insertTempTable");
			GtnFrameworkDataType[] type = { GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.STRING,
					GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER,
					GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER,
					GtnFrameworkDataType.STRING, GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.STRING,
					GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER };
			cmBean[3] = periodId;
			cmBean[7] = statusId;
			gtnSqlQueryEngine.executeInsertOrUpdateQuery(stringQuery, cmBean, type);

		} catch (GtnFrameworkGeneralException ex) {
			logger.error(GtnFrameworkWebserviceConstant.EXCEPTION_IN_EXECUTIG_QUERY, ex);
			gtnWsGeneralResponse.setSucess(false);
			gtnWsGeneralResponse.setGtnGeneralException(ex);
		} finally {
			logger.info("Exit insertTempQuery");
		}
		gtnWsGeneralResponse.setSucess(true);
		gtnResponse.setGtnWsGeneralResponse(gtnWsGeneralResponse);
		return gtnResponse;
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_CM_FINANCIAL_CLOSE
			+ GtnWebServiceUrlConstants.GTN_CM_FINANCIAL_TEMP_DELETE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse deleteTempTable(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Entering deleteTempTable");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse gtnWsGeneralResponse = new GtnWsGeneralResponse();
		GtnCMasterRequest gtnCMasterRequest = gtnWsRequest.getGtnCMasterRequest();
		Object[] cmBean = gtnCMasterRequest.getSaveData();
		try {

			Object[] deleteParam = { cmBean[0], cmBean[1] };

			GtnFrameworkDataType[] deletetype = { GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER };
			String deletequery = gtnWsSqlService.getQuery("deleteTempTable");
			gtnSqlQueryEngine.executeInsertOrUpdateQuery(deletequery, deleteParam, deletetype);

		} catch (GtnFrameworkGeneralException ex) {
			logger.error(GtnFrameworkWebserviceConstant.EXCEPTION_IN_EXECUTIG_QUERY, ex);
			gtnWsGeneralResponse.setSucess(false);
			gtnWsGeneralResponse.setGtnGeneralException(ex);
		} finally {
			logger.info("Exit deleteTempTable");
		}
		gtnWsGeneralResponse.setSucess(true);
		gtnResponse.setGtnWsGeneralResponse(gtnWsGeneralResponse);
		return gtnResponse;
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_CM_FINANCIAL_CLOSE
			+ GtnWebServiceUrlConstants.GTN_CM_FINANCIAL_TEMP_EDIT_SELECT, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse tempTableEditSelect(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Entering tempTableEditSelect");
		SimpleDateFormat simpleDate = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		GtnUIFrameworkWebserviceResponse fcTempTableEditResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse fcTempableEditGeneralResponse = new GtnWsGeneralResponse();
		GtnCMasterRequest gtnCMasterRequest = gtnWsRequest.getGtnCMasterRequest();

		List<GtnCMasterFinancialCloseBean> cmBean = gtnCMasterRequest.getGtnCMasterBean()
				.getGtnCMasterFinancialCloseBean();
		try {
			GtnCMasterFinancialCloseBean gtnCMasterFinancialCloseBean = cmBean.get(0);

			Object[] updateParam = { gtnCMasterFinancialCloseBean.getUserId(),
					gtnCMasterFinancialCloseBean.getSessionId(), gtnCMasterFinancialCloseBean.getCompanyMasterSid(),
					gtnCMasterFinancialCloseBean.getUserId(), gtnCMasterFinancialCloseBean.getSessionId() };

			GtnFrameworkDataType[] updatetype = { GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER,
					GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER };

			String updateForManualInEditMode = gtnWsSqlService.getQuery("updateForManualInEditMode");

			gtnSqlQueryEngine.executeInsertOrUpdateQuery(updateForManualInEditMode, updateParam, updatetype);

			Object[] selectParam = { gtnCMasterFinancialCloseBean.getCompanyMasterSid(),
					gtnCMasterFinancialCloseBean.getCompanyMasterSid(), gtnCMasterFinancialCloseBean.getUserId(),
					gtnCMasterFinancialCloseBean.getSessionId(), gtnCMasterFinancialCloseBean.getUserId(),
					gtnCMasterFinancialCloseBean.getSessionId() };

			GtnFrameworkDataType[] deletetype = { GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER,
					GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER,
					GtnFrameworkDataType.INTEGER };
			String selectForManualInEditMode = gtnWsSqlService.getQuery("selectForManualInEditMode");
			@SuppressWarnings("unchecked")
			List<Object[]> selectList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(selectForManualInEditMode,
					selectParam, deletetype);

			for (Object[] selectList1 : selectList) {

				Object[] saveData = { selectList1[0], selectList1[1], selectList1[2], selectList1[3],
						simpleDate.format(selectList1[4]), selectList1[5], simpleDate.format(selectList1[6]),
						selectList1[7], selectList1[8] };
				GtnFrameworkDataType[] saveDataHeader = { GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER,
						GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.STRING,
						GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.STRING, GtnFrameworkDataType.INTEGER,
						GtnFrameworkDataType.INTEGER };

				String insertQuery = gtnWsSqlService.getQuery("insertUpdateCompanyFinancial");
				gtnSqlQueryEngine.executeInsertOrUpdateQuery(insertQuery, saveData, saveDataHeader);

			}

		} catch (GtnFrameworkGeneralException ex) {
			logger.error(GtnFrameworkWebserviceConstant.EXCEPTION_IN_EXECUTIG_QUERY, ex);
			fcTempableEditGeneralResponse.setSucess(false);
			fcTempableEditGeneralResponse.setGtnGeneralException(ex);
		} finally {
			logger.info("Exit tempTableEditSelect");
		}
		fcTempableEditGeneralResponse.setSucess(true);
		fcTempTableEditResponse.setGtnWsGeneralResponse(fcTempableEditGeneralResponse);
		return fcTempTableEditResponse;
	}

	@RequestMapping(value = GtnWebServiceUrlConstants.GTN_CM_FINANCIAL_CLOSE
			+ GtnWebServiceUrlConstants.GTN_CM_FINANCIAL_CLOSE_VALIDATION, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse financialCloseValidation(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		logger.info("Entering tempTableEditSelect");
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse gtnWsGeneralResponse = new GtnWsGeneralResponse();
		GtnCMasterRequest gtnCMasterRequest = gtnWsRequest.getGtnCMasterRequest();
		Object[] cmBean = gtnCMasterRequest.getSaveData();
		Integer monthIndex = GtnWsCMasterConstants.getMonthList().indexOf(cmBean[1]) + 1;
		try {

			Object[] selectParam = { cmBean[3], cmBean[4], cmBean[0], monthIndex };

			GtnFrameworkDataType[] selectType = { GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER,
					GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER };
			String selectquery = gtnWsSqlService.getQuery("validationForClose");
			@SuppressWarnings("unchecked")
			List<Object> resultList = (List<Object>) gtnSqlQueryEngine.executeSelectQuery(selectquery, selectParam,
					selectType);

			if (resultList.isEmpty()) {
				gtnResponse.setOutBountData(new Object[] { Boolean.FALSE });
			} else {
				Object[] resultArray = { resultList.get(0) };
				gtnResponse.setOutBountData(resultArray);
			}

		} catch (GtnFrameworkGeneralException ex) {
			logger.error(GtnFrameworkWebserviceConstant.EXCEPTION_IN_EXECUTIG_QUERY, ex);
			gtnWsGeneralResponse.setSucess(false);
			gtnWsGeneralResponse.setGtnGeneralException(ex);
		} finally {
			logger.info("Exit deleteTempTable");
		}
		gtnWsGeneralResponse.setSucess(true);
		gtnResponse.setGtnWsGeneralResponse(gtnWsGeneralResponse);
		return gtnResponse;
	}

	private String getSwapQuery() {
		return "DELETE\n" + "FROM\n" + "    dbo.COMPANY_FINANCIAL_CLOSE_AUTO\n" + "WHERE\n"
				+ "    COMPANY_MASTER_SID = ?";
	}

	private String getFCStatusQuery() {
		return " SELECT\n" + "    COMPANY_MASTER_SID,HT.DESCRIPTION AS STATUS\n" + "FROM\n"
				+ "    COMPANY_FINANCIAL_CLOSE CFC \n" + "    JOIN PERIOD P\n"
				+ "        ON CFC.PERIOD_SID = P.PERIOD_SID\n" + "        JOIN dbo.HELPER_TABLE HT\n"
				+ "        ON HT.HELPER_TABLE_SID=CFC.STATUS\n" + "WHERE\n" + "    P.YEAR = ?\n"
				+ "    AND P.\"MONTH\" = ?\n" + "    AND COMPANY_MASTER_SID= ?";
	}

	private String getHolidaysMonthWiseQuery() {
		return " SELECT " + "(MONTH(HOLIDAYS_PERIOD_DATE) - 1) MONTH," + " COUNT(*) COUNT " + "FROM "
				+ "CALENDAR_CONFIG_DETAILS"
				+ " where CALENDAR_CONFIG_MASTER_SID=? GROUP BY  MONTH(HOLIDAYS_PERIOD_DATE)";
	}

	private String getCalendarValuesQuery() {
		return "SELECT \n" + "        CALENDAR_CONFIG_MASTER_SID,CALENDAR_NAME \n" + "    FROM \n"
				+ "        CALENDAR_CONFIG_MASTER";
	}

	private String getPeriodQuery(String monthName) {
		Integer month = GtnWsCMasterConstants.getMonthList().indexOf(monthName.split(",")[0]);
		return "SELECT PERIOD_SID FROM PERIOD WHERE YEAR=" + monthName.split(",")[1] + " AND MONTH=" + (month + 1);
	}

	private String getStatusQuery(String description) {
		return " SELECT HELPER_TABLE_SID FROM dbo.HELPER_TABLE WHERE DESCRIPTION like '" + description
				+ "' and list_name ='ARM_STATUS'";
	}

}
