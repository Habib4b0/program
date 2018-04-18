/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.forecastconfiguration.controller;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnWsNumericConstants;
import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.forecastconfiguration.ForecastConfig;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecastconfiguration.constants.GtnWsForecastConfigurationConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.forecastconfiguration.util.GtnWsForecastConfigurationUtil;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecastconfiguration.GtnWsForecastConfigurationRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceTextBoxResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.forecastconfiguration.GtnWsForecastConfigurationResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import com.stpl.gtn.gtn2o.ws.util.GtnCommonUtil;

/**
 *
 * @author Abhiram.Giri
 */
@RestController
@RequestMapping(value = GtnWsForecastConfigurationConstants.GTN_FORECAST_CONFIGURATION_SERVICE)
public class GtnWsForecastConfigurationController {
	public GtnWsForecastConfigurationController() {
		/**
		 * empty constructor
		 */
	}

	private Map<String, String> filterAndSortingCriteriaMap = new HashMap<>();

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsForecastConfigurationController.class);

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private org.hibernate.SessionFactory sysSessionFactory;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@RequestMapping(value = GtnWsForecastConfigurationConstants.LOAD_FORECAST_PERIOD, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadForecastPeriod(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		LOGGER.info(GtnWsForecastConfigurationConstants.LOAD_FORECAST_PERIOD);
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnUIFrameworkWebserviceTextBoxResponse textBoxResponse = new GtnUIFrameworkWebserviceTextBoxResponse();
			Calendar gtsCal = getCurrentGTSToCalendar(GtnWsForecastConfigurationConstants.EX_FACTORY_SALES);
			String str = GtnWsForecastConfigurationUtil.getMonthForInt(gtsCal.get(Calendar.MONTH)) + " "
					+ gtsCal.get(Calendar.YEAR);
			textBoxResponse.setDefaultValue(str);
			gtnResponse.setGtnUIFrameworkWebserviceTextBoxResponse(textBoxResponse);
		} catch (Exception ex) {
			LOGGER.error(
					GtnFrameworkWebserviceConstant.ERROR_IN + GtnWsForecastConfigurationConstants.LOAD_FORECAST_PERIOD,
					ex);
		}
		LOGGER.info(GtnFrameworkWebserviceConstant.EXIT + GtnWsForecastConfigurationConstants.LOAD_FORECAST_PERIOD);
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsForecastConfigurationConstants.FUTURE_FREQUENCY_VALUE_CHANGE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse futureFrequencyValueChange(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		LOGGER.info(GtnWsForecastConfigurationConstants.FUTURE_FREQUENCY_VALUE_CHANGE);
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();

		GtnWsGeneralResponse gtnGenWsResponse = new GtnWsGeneralResponse();
		gtnGenWsResponse.setSucess(true);

		gtnResponse.setGtnWsGeneralResponse(gtnGenWsResponse);
		try {
			GtnWsForecastConfigurationResponse forecastResponse = futureIntervalDynamicValueChangeLogic(
					gtnWsRequest.getForecastConfigurationRequest());
			forecastResponse.setSuccess(forecastResponse.isSuccess());
			gtnResponse.setGtnWsForecastConfigurationResponse(forecastResponse);
		} catch (Exception ex) {
			gtnGenWsResponse.setSucess(false);
			LOGGER.error(GtnFrameworkWebserviceConstant.ERROR_IN
					+ GtnWsForecastConfigurationConstants.FUTURE_FREQUENCY_VALUE_CHANGE, ex);
		}
		LOGGER.info(GtnFrameworkWebserviceConstant.EXIT
				+ GtnWsForecastConfigurationConstants.FUTURE_FREQUENCY_VALUE_CHANGE);
		return gtnResponse;
	}
        @RequestMapping(value = GtnWsForecastConfigurationConstants.PERIOD_FREQUENCY_VALUE_CHANGE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse periodModeValue(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		LOGGER.info(GtnWsForecastConfigurationConstants.FUTURE_FREQUENCY_VALUE_CHANGE);
		GtnUIFrameworkWebserviceResponse gtnWsResponse = new GtnUIFrameworkWebserviceResponse();

		GtnWsGeneralResponse gtnGeneralWsResponse = new GtnWsGeneralResponse();
		gtnGeneralWsResponse.setSucess(true);

		gtnWsResponse.setGtnWsGeneralResponse(gtnGeneralWsResponse);
		try {
                        Calendar gtsCalculation = getCurrentGTSToCalendar(GtnWsForecastConfigurationConstants.EX_FACTORY_SALES);
			String string = GtnWsForecastConfigurationUtil.getMonthForInt(gtsCalculation.get(Calendar.MONTH)) + " "
					+ gtsCalculation.get(Calendar.YEAR);
			GtnWsForecastConfigurationResponse forecastResponse = new GtnWsForecastConfigurationResponse();
                        LOGGER.info("str******************"+string);
                        forecastResponse.setForecastPeriod(string);
			forecastResponse.setSuccess(forecastResponse.isSuccess());
			gtnWsResponse.setGtnWsForecastConfigurationResponse(forecastResponse);
		} catch (Exception ex) {
			gtnGeneralWsResponse.setSucess(false);
			LOGGER.error(GtnFrameworkWebserviceConstant.ERROR_IN
					+ GtnWsForecastConfigurationConstants.FUTURE_FREQUENCY_VALUE_CHANGE, ex);
		}
		LOGGER.info(GtnFrameworkWebserviceConstant.EXIT
				+ GtnWsForecastConfigurationConstants.FUTURE_FREQUENCY_VALUE_CHANGE);
		return gtnWsResponse;
	}

	@RequestMapping(value = GtnWsForecastConfigurationConstants.HISTORY_INTERVAL_VALUE_CHANGE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse historyIntervalValueChange(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		LOGGER.info(GtnWsForecastConfigurationConstants.HISTORY_INTERVAL_VALUE_CHANGE);
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();

		GtnWsGeneralResponse gtnGenWsResponse = new GtnWsGeneralResponse();
		gtnGenWsResponse.setSucess(true);

		gtnResponse.setGtnWsGeneralResponse(gtnGenWsResponse);
		try {
			GtnWsForecastConfigurationResponse forecastResponse = historyIntervalValueChangeLogic(
					gtnWsRequest.getForecastConfigurationRequest());
			forecastResponse.setSuccess(forecastResponse.isSuccess());
			gtnResponse.setGtnWsForecastConfigurationResponse(forecastResponse);
		} catch (Exception ex) {
			gtnGenWsResponse.setSucess(false);
			LOGGER.error(GtnFrameworkWebserviceConstant.ERROR_IN
					+ GtnWsForecastConfigurationConstants.HISTORY_INTERVAL_VALUE_CHANGE, ex);
		}
		LOGGER.info(GtnFrameworkWebserviceConstant.EXIT
				+ GtnWsForecastConfigurationConstants.HISTORY_INTERVAL_VALUE_CHANGE);
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsForecastConfigurationConstants.CHECK_SAVE_FORECAST_CONF, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse checkSaveForecastConfiguration(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		LOGGER.info(GtnWsForecastConfigurationConstants.CHECK_SAVE_FORECAST_CONF);
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnWsForecastConfigurationResponse forecastResponse = new GtnWsForecastConfigurationResponse();
			gtnResponse.setGtnWsForecastConfigurationResponse(forecastResponse);
			validateSaveForecastConfiguration(gtnWsRequest.getForecastConfigurationRequest(), forecastResponse);
		} catch (Exception ex) {
			LOGGER.error(GtnFrameworkWebserviceConstant.ERROR_IN
					+ GtnWsForecastConfigurationConstants.CHECK_SAVE_FORECAST_CONF, ex);
		}
		LOGGER.info(GtnFrameworkWebserviceConstant.EXIT + GtnWsForecastConfigurationConstants.CHECK_SAVE_FORECAST_CONF);
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsForecastConfigurationConstants.SAVE_FORECAST_CONF, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse saveForecastConfiguration(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		LOGGER.info(GtnWsForecastConfigurationConstants.SAVE_FORECAST_CONF);
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnWsForecastConfigurationResponse forecastResponse = new GtnWsForecastConfigurationResponse();
			gtnResponse.setGtnWsForecastConfigurationResponse(forecastResponse);
			saveForecastConfiguration(gtnWsRequest.getForecastConfigurationRequest(), forecastResponse);
		} catch (Exception ex) {
			LOGGER.error(
					GtnFrameworkWebserviceConstant.ERROR_IN + GtnWsForecastConfigurationConstants.SAVE_FORECAST_CONF,
					ex);
		}
		LOGGER.info(GtnFrameworkWebserviceConstant.EXIT + GtnWsForecastConfigurationConstants.SAVE_FORECAST_CONF);
		return gtnResponse;
	}

	public GtnWsForecastConfigurationResponse historyIntervalValueChangeLogic(
			GtnWsForecastConfigurationRequest request) {
		LOGGER.info("Enter historyIntervalValueChangeLogic");
		GtnWsForecastConfigurationResponse response = new GtnWsForecastConfigurationResponse();
		response.setHistoryPeriod(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		response.setSuccess(true);
		try (Session session = sessionFactory.openSession()) {
			if (!request.getHistoryInterval().equals(GtnFrameworkCommonStringConstants.STRING_EMPTY)) {
				Integer interval = Integer.valueOf(request.getHistoryInterval());
				Map<String, Integer> frequencyIntervalMap = new HashMap<>();
				frequencyIntervalMap.put(GtnWsForecastConfigurationConstants.ANNUAL, GtnWsNumericConstants.THREE);
				frequencyIntervalMap.put(GtnWsForecastConfigurationConstants.SEMI_ANNUAL, GtnWsNumericConstants.SIX);
				frequencyIntervalMap.put(GtnWsForecastConfigurationConstants.QUARTER, GtnWsNumericConstants.TWELVE);
				frequencyIntervalMap.put(GtnWsForecastConfigurationConstants.MONTHLY, GtnWsNumericConstants.THIRTY_SIX);
				Date date = new Date();
				if (request.getHistoryFrequency() != 0) {
					HelperTable table = session.load(HelperTable.class, request.getHistoryFrequency());
					Integer valueToCheck = frequencyIntervalMap.get(table.getDescription());
					if (interval <= valueToCheck) {
						if (table.getDescription().equals(GtnWsForecastConfigurationConstants.MONTHLY)) {
							final Calendar monthlyDate = Calendar.getInstance();
							monthlyDate.setTime(new Date());
							monthlyDate.add(Calendar.MONTH, -1);
							date = monthlyDate.getTime();
						}
						response.setHistoryPeriod(GtnWsForecastConfigurationUtil.getHistoryDetail(interval,
								table.getDescription(), date));
					} else {
						response.setErrorMessage(true);
						response.setHistoryInterval(GtnFrameworkCommonStringConstants.STRING_EMPTY);
						response.setMessage(
								"History historyInterval should be less than or equal to " + valueToCheck + ".");
					}
				}

			}
		} catch (Exception ex) {
			response.setSuccess(false);
			LOGGER.error("Error in historyIntervalValueChangeLogic", ex);
		}
		LOGGER.info("Exit historyIntervalValueChangeLogic");
		return response;
	}

	public GtnWsForecastConfigurationResponse futureIntervalDynamicValueChangeLogic(
			GtnWsForecastConfigurationRequest request) {
		LOGGER.info("Enter historyIntervalValueChangeLogic");
		GtnWsForecastConfigurationResponse response = new GtnWsForecastConfigurationResponse();
		response.setHistoryPeriod(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		response.setSuccess(true);
		try (Session session = sessionFactory.openSession()) {
			if (!request.getFutureInterval().equals(GtnFrameworkCommonStringConstants.STRING_EMPTY)) {
				Integer interval = Integer.valueOf(request.getFutureInterval());
				Map<String, Integer> frequencyIntervalMap = new HashMap<>();
				frequencyIntervalMap.put(GtnWsForecastConfigurationConstants.ANNUAL, GtnWsNumericConstants.THREE);
				frequencyIntervalMap.put(GtnWsForecastConfigurationConstants.SEMI_ANNUAL, GtnWsNumericConstants.SIX);
				frequencyIntervalMap.put(GtnWsForecastConfigurationConstants.QUARTER, GtnWsNumericConstants.TWELVE);
				frequencyIntervalMap.put(GtnWsForecastConfigurationConstants.MONTHLY, GtnWsNumericConstants.THIRTY_SIX);
				Date date = new Date();
				LOGGER.info("request.getFutureFrequency()===============" + request.getFutureFrequency());
				if (request.getFutureFrequency() != 0) {
					HelperTable table = session.load(HelperTable.class, request.getFutureFrequency());
					Integer valueToCheck = frequencyIntervalMap.get(table.getDescription());
					LOGGER.info("interval===============>>" + interval);
					LOGGER.info("valueToCheck===============>>>" + valueToCheck);
					if (table.getDescription().equals(GtnWsForecastConfigurationConstants.MONTHLY)) {
						final Calendar monthlyDate = Calendar.getInstance();
						monthlyDate.setTime(new Date());
						monthlyDate.add(Calendar.MONTH, -1);
						date = monthlyDate.getTime();
					}
					String forecastPertiod = GtnWsForecastConfigurationUtil.getFutureDetail(interval,
							table.getDescription(), date);
					LOGGER.info("futureIntervalYear=====================" + forecastPertiod);
					futureIntervalValueChangeLogic(request, forecastPertiod, response);
				}

			}
		} catch (Exception ex) {
			response.setSuccess(false);
			LOGGER.error("Error in historyIntervalValueChangeLogic", ex);
		}
		LOGGER.info("Exit historyIntervalValueChangeLogic");
		return response;
	}

	public void futureIntervalValueChangeLogic(GtnWsForecastConfigurationRequest request, String foreCastPeriod,
			GtnWsForecastConfigurationResponse response) {
		LOGGER.info("Enter futureIntervalValueChangeLogic");

		try (Session session = sessionFactory.openSession()) {
			if (request.getFutureInterval() != null
					&& !request.getFutureInterval().trim().equals(GtnFrameworkCommonStringConstants.STRING_EMPTY)) {
				int freq = request.getFutureFrequency();
				if (freq != 0) {
					HelperTable table = session.load(HelperTable.class, freq);
					Calendar gtsCal = getCurrentGTSToCalendar(GtnWsForecastConfigurationConstants.EX_FACTORY_SALES);
					String leastYear = getFrequencyDivision(table.getDescription(), gtsCal, foreCastPeriod);
					LOGGER.info("leastYear===============>" + leastYear);
					response.setForecastPeriod(leastYear);
					String interval = request.getFutureInterval().trim();
					LOGGER.info("interval===============>" + interval);
					if (!"".equals(interval)) {
						Integer intervalValue = Integer.valueOf(request.getFutureInterval().trim());
						Calendar futureDate = GtnWsForecastConfigurationUtil.convertPeriod(1, table.getDescription(),
								intervalValue);
						if (futureDate.getTime()
								.after(getCurrentGTSToCalendar(GtnWsForecastConfigurationConstants.EX_FACTORY_SALES)
										.getTime())) {
							response.setErrorMessage(true);
							response.setMessage(GtnWsForecastConfigurationConstants.ENTERED_FUTUREPERIOD);
				}
			}
				}
			}
		} catch (Exception ex) {
			LOGGER.error("Error in futureIntervalValueChangeLogic", ex);
		}
		LOGGER.info("Exit futureIntervalValueChangeLogic");

	}

	@RequestMapping(value = GtnWsForecastConfigurationConstants.FROM_PERIOD_VALUE_CHANGE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse fromPeriodValueChange(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		LOGGER.info(GtnWsForecastConfigurationConstants.FROM_PERIOD_VALUE_CHANGE);
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnWsForecastConfigurationResponse forecastResponse = new GtnWsForecastConfigurationResponse();
			gtnResponse.setGtnWsForecastConfigurationResponse(forecastResponse);
			DateFormat df = new SimpleDateFormat("yyyy");
			String fromDate = df.format(gtnWsRequest.getForecastConfigurationRequest().getFromDate());
			Calendar lastCal = Calendar.getInstance();
			int a = lastCal.get(Calendar.YEAR) - Integer.parseInt(fromDate);
			if (a > GtnWsNumericConstants.THREE) {
				forecastResponse.setFromDate(null);
				forecastResponse.setErrorMessage(true);
				forecastResponse.setMessage("History year should be less than or equal to 3 years.");
			} else if (a <= 0) {
				forecastResponse.setFromDate(null);
				forecastResponse.setErrorMessage(true);
				forecastResponse.setMessage("History year should be less than current year.");
			}
		} catch (Exception ex) {
			LOGGER.error(GtnFrameworkWebserviceConstant.ERROR_IN
					+ GtnWsForecastConfigurationConstants.FROM_PERIOD_VALUE_CHANGE, ex);
		}
		LOGGER.info(GtnFrameworkWebserviceConstant.EXIT + GtnWsForecastConfigurationConstants.FROM_PERIOD_VALUE_CHANGE);
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsForecastConfigurationConstants.TO_PERIOD_VALUE_CHANGE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse toPeriodValueChange(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		LOGGER.info(GtnWsForecastConfigurationConstants.TO_PERIOD_VALUE_CHANGE);
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnWsForecastConfigurationResponse forecastResponse = new GtnWsForecastConfigurationResponse();
			gtnResponse.setGtnWsForecastConfigurationResponse(forecastResponse);
                        Calendar gtsCal = getCurrentGTSToCalendar(GtnWsForecastConfigurationConstants.EX_FACTORY_SALES);
			gtnResponse.setGtnWsForecastConfigurationResponse(forecastResponse);
			if (gtnWsRequest.getForecastConfigurationRequest().getToDate().after(gtsCal.getTime())) {
				forecastResponse.setErrorMessage(true);
				forecastResponse.setMessage(GtnWsForecastConfigurationConstants.ENTERED_FUTUREPERIOD);
			}
		} catch (Exception ex) {
			LOGGER.error(GtnFrameworkWebserviceConstant.ERROR_IN
					+ GtnWsForecastConfigurationConstants.TO_PERIOD_VALUE_CHANGE, ex);
		}
		LOGGER.info(GtnFrameworkWebserviceConstant.EXIT + GtnWsForecastConfigurationConstants.TO_PERIOD_VALUE_CHANGE);
		return gtnResponse;
	}

	@RequestMapping(value = GtnWsForecastConfigurationConstants.GET_FORECAST_CONF_TABLE_DATA, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getForecastConfigurationTableData(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse forecastConfigurationResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		try {
			GtnSerachResponse forecastConfigurationSerachResponse = new GtnSerachResponse();
			String forecastConfigurationQueryName = gtnWsRequest.getGtnWsSearchRequest().isCount()
					? "getForecastConfigurationCount" : "getForecastConfigurationResults";
			List<Object> inputlist = getSearchInput(gtnWsRequest);
			@SuppressWarnings("unchecked")
			List<Object[]> result = executeQuery(gtnWsSqlService.getQuery(inputlist, forecastConfigurationQueryName));
			if (gtnWsRequest.getGtnWsSearchRequest().isCount()) {
				forecastConfigurationSerachResponse.setCount(Integer.parseInt(String.valueOf(result.get(0))));
			} else {
				GtnUIFrameworkDataTable forecastConfigurationDataTable = new GtnUIFrameworkDataTable();
				forecastConfigurationDataTable.addData(result);
				forecastConfigurationSerachResponse.setResultSet(forecastConfigurationDataTable);
			}
			forecastConfigurationResponse.setGtnSerachResponse(forecastConfigurationSerachResponse);
			generalResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			generalResponse.setSucess(false);
			generalResponse.setGtnGeneralException(ex);
		}
		forecastConfigurationResponse.setGtnWsGeneralResponse(generalResponse);
		return forecastConfigurationResponse;
	}

	private List<Object> getSearchInput(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		List<Object> forecastConfigurationInputList = new ArrayList<>();

		StringBuilder inputWhereConditions = new StringBuilder();
		String and = "";
		String where = " WHERE ";
		try {
			for (GtnWebServiceSearchCriteria forecastConfigurationSearchCriteria : gtnWsRequest.getGtnWsSearchRequest()
					.getGtnWebServiceSearchCriteriaList()) {

				if (forecastConfigurationSearchCriteria.isFilter()) {

					StringBuilder value = new StringBuilder(forecastConfigurationSearchCriteria.getFilterValue1());
					if ("LIKE".equalsIgnoreCase(forecastConfigurationSearchCriteria.getExpression())) {
						value.append('%').append(value).append('%');
					}
					inputWhereConditions.append(where).append(and)
							.append(GtnCommonUtil.getWhereClauseForAColumn(
									forecastConfigurationSearchCriteria.getExpression(),
									filterAndSortingCriteriaMap().get(forecastConfigurationSearchCriteria.getFieldId()),
									value.toString(), forecastConfigurationSearchCriteria.getFilterValue2()));
					and = " AND ";
					where = "";
				}
			}
			forecastConfigurationInputList.add(getSysSchemaCatalog());
			forecastConfigurationInputList.add(inputWhereConditions);

			if (!gtnWsRequest.getGtnWsSearchRequest().isCount()) {
				forecastConfigurationInputList.addAll(
						getSortedInputs(gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceOrderByCriteriaList()));
				forecastConfigurationInputList.add(gtnWsRequest.getGtnWsSearchRequest().getTableRecordStart());
				forecastConfigurationInputList.add(gtnWsRequest.getGtnWsSearchRequest().getTableRecordOffset());
			}
		} catch (Exception ex) {
			LOGGER.error("Exception in executig query-", ex);
			throw new GtnFrameworkGeneralException("Error in executing query : ", ex);

		}
		return forecastConfigurationInputList;
	}

	private List<Object> getSortedInputs(List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList) {
		List<Object> list = new ArrayList<>();
		if (!gtnWebServiceOrderByCriteriaList.isEmpty()) {
			GtnWebServiceOrderByCriteria dto = gtnWebServiceOrderByCriteriaList.get(0);
			list.add(filterAndSortingCriteriaMap().get(dto.getPropertyId()) + " " + dto.getOrderByCriteria());
		} else {
			list.add(filterAndSortingCriteriaMap().get(GtnFrameworkCommonConstants.VERSION_NO) + " DESC");
		}
		return list;
	}

	private Map<String, String> filterAndSortingCriteriaMap() {
		if (filterAndSortingCriteriaMap.isEmpty()) {
			filterAndSortingCriteriaMap.put("businessProcess", "FC.BUSINESS_PROCESS_TYPE");
			filterAndSortingCriteriaMap.put("processType", "FC.PROCESS_TYPE_1");
			filterAndSortingCriteriaMap.put("mode", "FC.MODE");
			filterAndSortingCriteriaMap.put("fromDateSearch", "FC.FROM_DATE");
			filterAndSortingCriteriaMap.put("toDateSearch", "FC.TO_DATE");
			filterAndSortingCriteriaMap.put("historicalInterval", "HHF.DESCRIPTION");
			filterAndSortingCriteriaMap.put("historicalValue", "HIST_VALUE");
			filterAndSortingCriteriaMap.put("futureInterval", "HPF.DESCRIPTION");
			filterAndSortingCriteriaMap.put("futureValue", "FC.PROJ_VALUE");
			filterAndSortingCriteriaMap.put(GtnFrameworkCommonConstants.VERSION_NO, "FC.VERSION_NO");
			filterAndSortingCriteriaMap.put("fromPeriod", "FC.ACTIVE_START_DATE");
			filterAndSortingCriteriaMap.put("toPeriod", "FC.ACTIVE_END_DATE");
			filterAndSortingCriteriaMap.put("createdBy", "usr.lastName + ' ' + usr.middleName + ' ' + usr.firstName");
			filterAndSortingCriteriaMap.put("activeFlag", "FC.Active_Inactive");
		}
		return filterAndSortingCriteriaMap;
	}

	public Calendar getCurrentGTSToCalendar(String fileType) throws GtnFrameworkGeneralException {
		Date gtsDate = getCurrentGTSToDate(fileType);
		Calendar gtsCal = Calendar.getInstance();
		gtsCal.setTime(gtsDate);
		gtsCal.set(Calendar.SECOND, gtsCal.getActualMaximum(Calendar.SECOND));
		gtsCal.set(Calendar.MINUTE, gtsCal.getActualMaximum(Calendar.MINUTE));
		gtsCal.set(Calendar.HOUR, gtsCal.getActualMaximum(Calendar.HOUR));
		return gtsCal;
	}

	public Date getCurrentGTSToDate(String fileType) throws GtnFrameworkGeneralException {
		Object[] fileNameObject = getFileName(fileType);
		Calendar cal = Calendar.getInstance();
		if (fileNameObject != null) {
			String forecastName = fileNameObject[0] != null ? fileNameObject[0].toString() : "";
			String selectedVersion = fileNameObject[1] != null ? fileNameObject[1].toString() : "";
			String finalVersion;
			if (selectedVersion.contains(".")) {
				String[] etlVersion = selectedVersion.split("\\.");
				finalVersion = etlVersion[0] + "~" + selectedVersion;
			} else {
				finalVersion = selectedVersion;
			}
			Object[] forecastYear = getForecastYear(finalVersion, forecastName);
			int year = forecastYear[0] != null ? Integer.parseInt(forecastYear[0].toString()) : 0;
			int month = forecastYear[1] != null ? Integer.parseInt(forecastYear[1].toString()) : 0;
			cal = new GregorianCalendar(year, month - 1, 1);
			int day = cal.getActualMaximum(Calendar.DATE);
			cal.set(Calendar.DATE, day);
		}
		return cal.getTime();
	}

	@SuppressWarnings("unchecked")
	public Object[] getFileName(String fileType) throws GtnFrameworkGeneralException {
		Object[] fileName = null;
		List<Object> inputlist = new ArrayList<>();
		inputlist.add(fileType);
		List<Object[]> result = executeQuery(gtnWsSqlService.getQuery(inputlist, "getFCFileName"));
		if (result != null && !result.isEmpty()) {
			fileName = result.get(0);
		}
		return fileName;
	}

	public Object[] getForecastYear(final String version, final String forecastName)
			throws GtnFrameworkGeneralException {
		String sqlString = "SELECT DISTINCT FM.FORECAST_YEAR,FM.FORECAST_MONTH FROM FORECASTING_MASTER FM WHERE ";
		if (version.contains("~")) {
			String[] versionArray = version.split("~");
			sqlString = sqlString.concat(" (FM.FORECAST_VER='").concat(versionArray[0]).concat("' or FM.FORECAST_VER='")
					.concat(versionArray[1]).concat("')");
		} else {
			sqlString = sqlString.concat(" FM.FORECAST_VER='").concat(version).concat("'");
		}
		sqlString = sqlString.concat("AND FM.FORECAST_NAME='").concat(forecastName).concat("'")
				.concat(" ORDER BY FM.FORECAST_YEAR DESC,FM.FORECAST_MONTH DESC");
		Object[] forecastYear = null;
		List<?> result = executeQuery(sqlString);
		if (result != null && !result.isEmpty()) {
			forecastYear = (Object[]) result.get(0);
		}
		return forecastYear;
	}

	public void validateSaveForecastConfiguration(GtnWsForecastConfigurationRequest fcRequest,
			GtnWsForecastConfigurationResponse fcResponse) throws GtnFrameworkGeneralException {
		try {
			fcResponse.setSuccess(true);
			if (fcRequest.getBusinessProcess() == 0) {
				fcResponse.setSuccess(false);
				fcResponse.setMessageType(GtnFrameworkCommonStringConstants.VALIDATION);
				fcResponse.setMessage("Please Select Business Process");
				return;
			}
			if (fcRequest.getMode().equals(String.valueOf(GtnWsForecastConfigurationConstants.MODE_VALUE_PERIOD))) {
				validatePeriod(fcRequest, fcResponse);
				return;
			}
			validateInterval(fcRequest, fcResponse);
		} catch (Exception e) {
			fcResponse.setSuccess(false);
			fcResponse.setMessageType(GtnFrameworkCommonStringConstants.ERROR);
			fcResponse.setMessage("Entered interval is not valid");
			throw new GtnFrameworkGeneralException("Exception in validateSaveForecastConfiguration", e);
		}
	}

	private void validateInterval(GtnWsForecastConfigurationRequest fcRequest,
			GtnWsForecastConfigurationResponse fcResponse) {
		if (fcRequest.getHistoryFrequency() == 0) {
			fcResponse.setSuccess(false);
			fcResponse.setMessageType(GtnFrameworkCommonStringConstants.VALIDATION);
			fcResponse.setMessage("Please Select Historical Data Frequency");
			return;
		}
		if (fcRequest.getHistoryInterval() == null || StringUtils.isEmpty(fcRequest.getHistoryInterval())) {
			fcResponse.setSuccess(false);
			fcResponse.setMessageType(GtnFrameworkCommonStringConstants.VALIDATION);
			fcResponse.setMessage("Please Enter Historical Data Interval");
			return;
		}
		int historyInterval = Integer.parseInt(fcRequest.getHistoryInterval());
		if (historyInterval == 0) {
			fcResponse.setSuccess(false);
			fcResponse.setMessageType(GtnFrameworkCommonStringConstants.ERROR);
			fcResponse.setMessage("Historical data interval can't be Zero ");
			return;
		}
		validateIntervalDefined(fcRequest, fcResponse);
	}

	private void validateIntervalDefined(GtnWsForecastConfigurationRequest fcRequest,
			GtnWsForecastConfigurationResponse fcResponse) {
		if (fcRequest.getProcessType().equals(GtnWsForecastConfigurationConstants.PROCESS_TYPE_VALUE_DEFINED)) {
			if (fcRequest.getFutureFrequency() == 0) {
				fcResponse.setSuccess(false);
				fcResponse.setMessageType(GtnFrameworkCommonStringConstants.VALIDATION);
				fcResponse.setMessage("Please Select Future Interval Frequency");
				return;
			}
			int futureInterval = Integer.parseInt(fcRequest.getFutureInterval());
			if (fcRequest.getFutureInterval() == null || StringUtils.isEmpty(fcRequest.getFutureInterval())) {
				fcResponse.setSuccess(false);
				fcResponse.setMessageType(GtnFrameworkCommonStringConstants.VALIDATION);
				fcResponse.setMessage("Please Enter Future Interval");
				return;
			}
			if (futureInterval == 0) {
				fcResponse.setSuccess(false);
				fcResponse.setMessageType(GtnFrameworkCommonStringConstants.ERROR);
				fcResponse.setMessage("Future interval can't be Zero ");

			}
		}
	}

	private boolean validatePeriodDefined(GtnWsForecastConfigurationRequest fcRequest,
			GtnWsForecastConfigurationResponse fcResponse) {
		Date toDate = fcRequest.getToDate();
		if (toDate == null) {
			fcResponse.setSuccess(false);
			fcResponse.setMessageType(GtnFrameworkCommonStringConstants.VALIDATION);
			fcResponse.setMessage("Please Enter Historical Data Time Period To");
			return true;
		}
		Calendar lastCal = Calendar.getInstance();
		lastCal.setTime(fcRequest.getToDate());
		int lastDate = lastCal.getActualMaximum(Calendar.DATE);

		Calendar toDateCalendar = Calendar.getInstance();
		toDateCalendar.setTime(toDate);

		if (toDateCalendar.get(Calendar.DATE) != lastDate) {
			fcResponse.setSuccess(false);
			fcResponse.setMessageType(GtnFrameworkCommonStringConstants.ERROR);
			fcResponse.setMessage("To Date should be end date of the month");
			return true;
		}
		if (toDate.before(fcRequest.getFromDate())) {
			fcResponse.setSuccess(false);
			fcResponse.setMessageType(GtnFrameworkCommonStringConstants.ERROR);
			fcResponse.setMessage("From Date should not be after To date");
			return true;
		}
		return false;
	}

	private void validatePeriod(GtnWsForecastConfigurationRequest fcRequest,
			GtnWsForecastConfigurationResponse fcResponse) {
		Date fromDate = fcRequest.getFromDate();
		Date toDate = fcRequest.getToDate();
		if (fromDate == null) {
			fcResponse.setSuccess(false);
			fcResponse.setMessageType(GtnFrameworkCommonStringConstants.VALIDATION);
			fcResponse.setMessage("Please Enter Historical Data Time Period From");
			return;
		}
		Calendar calHisCalendar = Calendar.getInstance();
		if (fcRequest.getProcessType().equals(GtnWsForecastConfigurationConstants.PROCESS_TYPE_VALUE_AUTO_UPDATE)
				&& calHisCalendar.getTime().before(fromDate)) {
			fcResponse.setSuccess(false);
			fcResponse.setMessageType(GtnFrameworkCommonStringConstants.ERROR);
			fcResponse.setMessage("From date should be atleast one month before current Date");
			return;
		}
		Calendar calPrCalendar = Calendar.getInstance();
		calHisCalendar.setTimeZone(TimeZone.getTimeZone("GMT"));
		calHisCalendar.add(Calendar.MONTH, -1);
		calPrCalendar.add(Calendar.MONTH, 1);
		if (fcRequest.getProcessType().equals(GtnWsForecastConfigurationConstants.PROCESS_TYPE_VALUE_DEFINED)
				&& validatePeriodDefined(fcRequest, fcResponse)) {
			return;
		}
		Calendar fromDateCalendar = Calendar.getInstance();
		fromDateCalendar.setTime(fromDate);
		if (fromDateCalendar.get(Calendar.DATE) != 1) {
			fcResponse.setSuccess(false);
			fcResponse.setMessageType(GtnFrameworkCommonStringConstants.ERROR);
			fcResponse.setMessage("From Date should be begining date of the month");
			return;
		}
		if (calHisCalendar.getTime().before(fromDate) || toDate != null && toDate.before(calPrCalendar.getTime())) {
			fcResponse.setSuccess(false);
			fcResponse.setMessageType(GtnFrameworkCommonStringConstants.ERROR);
			fcResponse.setMessage("From / To Should be atleast one month before / After Current Date");
		}
	}

	public void updateActiveEndDate(Session session, int businessProcess) throws GtnFrameworkGeneralException {
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String activeEndDate = df.format(new Date());
			String updateForecastConfigQuery = gtnWsSqlService.getQuery("updateForecastConfigEndDate");
			Object[] updateForecastConfigQueryParams = { activeEndDate, businessProcess };
			GtnFrameworkDataType[] updateForecastConfigQueryTypes = { GtnFrameworkDataType.STRING,
					GtnFrameworkDataType.INTEGER };
			gtnSqlQueryEngine.executeInsertOrUpdateQuery(updateForecastConfigQuery, updateForecastConfigQueryParams,
					updateForecastConfigQueryTypes, session);
		} catch (Exception ex) {
			throw new GtnFrameworkGeneralException("Error in updateActiveEndDate", ex);
		}
	}

	public void saveForecastConfiguration(GtnWsForecastConfigurationRequest fcRequest,
			GtnWsForecastConfigurationResponse fcResponse) throws GtnFrameworkGeneralException {
		fcResponse.setSuccess(true);
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		HelperTable businessProcess = session.load(HelperTable.class, fcRequest.getBusinessProcess());
		try {
			ForecastConfig forecastConfig = new ForecastConfig();
			Criteria cr = session.createCriteria(ForecastConfig.class);
			cr.add(Restrictions.eq("businessProcessType", fcRequest.getBusinessProcess()));
			cr.addOrder(Order.desc(GtnFrameworkCommonConstants.VERSION_NO));
			@SuppressWarnings("unchecked")
			List<ForecastConfig> results = cr.list();
			if (results != null && !results.isEmpty()) {
				updateActiveEndDate(session, fcRequest.getBusinessProcess());
				ForecastConfig config = results.get(0);
				forecastConfig.setVersionNo(config.getVersionNo() + 1);
			}
			forecastConfig.setBusinessProcessType(fcRequest.getBusinessProcess());
			forecastConfig.setProcessType(!fcRequest.getProcessType()
					.equalsIgnoreCase(GtnWsForecastConfigurationConstants.PROCESS_TYPE_VALUE_DEFINED));
			forecastConfig.setActiveStartDate(new Date());
			if (forecastConfig.isProcessType() || fcRequest.getToDate() == null) {
				final Date gtsDate = getCurrentGTSToDate(GtnWsForecastConfigurationConstants.EX_FACTORY_SALES);
				forecastConfig.setToDate(gtsDate);
			}
			getModifiedProcessModeValue(forecastConfig, fcRequest, session);
			forecastConfig.setCreatedDate(new Date());
			forecastConfig.setModifiedDate(new Date());

			forecastConfig.setCreatedBy(fcRequest.getUserId());
			forecastConfig.setModifiedBy(fcRequest.getUserId());
			session.saveOrUpdate(forecastConfig);
			tx.commit();
			fcResponse.setMessageType("success");
			fcResponse.setMessage(businessProcess.getDescription() + " business process Saved Successfully.");
		} catch (Exception e) {
			tx.rollback();
			fcResponse.setSuccess(false);
			fcResponse.setMessageType("fail");
			fcResponse.setMessage(businessProcess.getDescription() + " has not been saved.");
			throw new GtnFrameworkGeneralException("Exception in save Forecast Configuration ", e);
		} finally {
			session.close();
		}
	}

	private void getModifiedProcessModeValue(ForecastConfig forecastConfig, GtnWsForecastConfigurationRequest fcRequest,
			Session session) throws GtnFrameworkGeneralException {
		try {
			if (fcRequest.getMode().equalsIgnoreCase(GtnWsForecastConfigurationConstants.MODE_VALUE_INTERVAL)) {
				forecastConfig.setProcessMode(true);
				forecastConfig.setHistFreq(fcRequest.getHistoryFrequency());
				forecastConfig.setHistValue(Integer.valueOf(
						StringUtils.isNotBlank(fcRequest.getHistoryInterval()) ? fcRequest.getHistoryInterval() : "0"));
				forecastConfig.setProjFreq(fcRequest.getFutureFrequency());
				forecastConfig.setProjValue(Integer.valueOf(
						StringUtils.isNotBlank(fcRequest.getFutureInterval()) ? fcRequest.getFutureInterval() : "0"));
				HelperTable historyFrequency = session.load(HelperTable.class, fcRequest.getHistoryFrequency());
				forecastConfig.setFromDate(GtnWsForecastConfigurationUtil.convertToPeriod(-1,
						historyFrequency.getDescription(), forecastConfig.getHistValue()));
				forecastConfig.setProjHistFreq(0);
				forecastConfig.setProjHistValue(0);
				if (!forecastConfig.isProcessType()) {
					HelperTable futureFrequency = session.load(HelperTable.class, fcRequest.getFutureFrequency());
					forecastConfig.setToDate(GtnWsForecastConfigurationUtil.convertToPeriod(1,
							futureFrequency.getDescription(), forecastConfig.getProjValue()));
				}
			} else {
				forecastConfig.setProcessMode(false);
				forecastConfig.setFromDate(fcRequest.getFromDate());
				if (!forecastConfig.isProcessType() && fcRequest.getToDate() != null) {
					forecastConfig.setToDate(fcRequest.getToDate());
				}
			}
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in getModifiedProcessModeValue ", e);
		}

	}

	public GtnWsSqlService getGtnWsSqlService() {
		return gtnWsSqlService;
	}

	@SuppressWarnings("rawtypes")
	public List executeQuery(String sqlQuery) throws GtnFrameworkGeneralException {
		return gtnSqlQueryEngine.executeSelectQuery(sqlQuery);
	}

	public String getSysSchemaCatalog() throws GtnFrameworkGeneralException {
		String catalog = "";
		try (Connection connection = sysSessionFactory.getSessionFactoryOptions().getServiceRegistry()
				.getService(ConnectionProvider.class).getConnection()) {
			catalog = connection.getCatalog();
		} catch (Exception ex) {
			throw new GtnFrameworkGeneralException("Exception in getSysSchemaCatalog", ex);
		}
		return catalog;
	}

	private String getFrequencyDivision(String description, Calendar gtsCal, String foreCastPeriod)
			throws GtnFrameworkGeneralException {
		String gtsPeriod = "";
		int frequencyDivision = 1;
		String leastYear = "";
		if (description.equals(GtnWsForecastConfigurationConstants.QUARTER)) {
			gtsPeriod = "Q" + getQuarterForMonth(gtsCal.get(Calendar.MONTH)) + " " + gtsCal.get(Calendar.YEAR);
			leastYear = compareLowestPeriod(foreCastPeriod, gtsPeriod);
                        return leastYear;
		}
                if (description.equals(GtnWsForecastConfigurationConstants.SEMI_ANNUAL)) {
			gtsPeriod = "S" + getSemmiAnnualForMonth(gtsCal.get(Calendar.MONTH)) + " " + gtsCal.get(Calendar.YEAR);
			leastYear = compareLowestPeriod(foreCastPeriod, gtsPeriod);
                        return leastYear;
		} 
                if (description.equals(GtnWsForecastConfigurationConstants.MONTHLY)) {
			frequencyDivision = 12;
			gtsPeriod = GtnWsForecastConfigurationUtil.getMonthForInt(gtsCal.get(Calendar.MONTH)) + " "
					+ gtsCal.get(Calendar.YEAR);
			leastYear = compareLowestPeriodAnnualMonthly(foreCastPeriod, gtsPeriod, frequencyDivision);
                        return leastYear;
		} 
                if (description.equals(GtnWsForecastConfigurationConstants.ANNUAL)) {
			gtsPeriod = String.valueOf(gtsCal.get(Calendar.YEAR));
			leastYear = compareLowestPeriodAnnual(foreCastPeriod, gtsPeriod, frequencyDivision);
                        return leastYear;
		}
		return leastYear;
	}

	public static String getQuarterForMonth(int num) {
		int newNum = num + 1;
		int quarter = 1;
		if (newNum <= 3) {
			quarter = 1;
		} else if (newNum > 3 && newNum <= 6) {
			quarter = 2;
		} else if (newNum > 6 && newNum <= 9) {
			quarter = 3;
		} else if (newNum > 9 && newNum <= 12) {
			quarter = 4;
		}
		return String.valueOf(quarter);

	}

	public static String getSemmiAnnualForMonth(int num) {
		int newNum = num + 1;
		int semmiAnnual = 1;
		if (newNum <= 6) {
			semmiAnnual = 1;
		} else if (newNum > 6 && newNum <= 12) {
			semmiAnnual = 2;
		}
		return String.valueOf(semmiAnnual);

	}

	private static String compareLowestPeriod(String s1, String s2) {
		String[] arrays1 = s1.split(" ");
		String[] arrays2 = s2.split(" ");
		               
		String[] splittedArrayS1 = arrays1[0].split("");
		String[] splittedArrayS2 = arrays2[0].split("");
                if ((Integer.parseInt(arrays1[1]) == Integer.parseInt(arrays2[1])) && (Integer.parseInt(splittedArrayS1[2]) < Integer.parseInt(splittedArrayS2[2]))) {
			return s1;
		}
                if (Integer.parseInt(arrays1[1]) < Integer.parseInt(arrays2[1])) {
			return s1;
		}
                else {
			return s2;
		}	
		
	}

	private static String compareLowestPeriodAnnual(String s1, String s2, int frequencyDivision) {
		String[] arrays1 = s1.split(" ");
		String[] arrays2 = s2.split(" ");
		LOGGER.info("frequencyDivision===========================" + frequencyDivision);
		for (int i = 0; i < arrays1.length; i++) {
			LOGGER.info("arrays1===========================" + arrays1[i]);
		}
		if ((frequencyDivision == 1) && (Integer.parseInt(arrays1[0]) < Integer.parseInt(arrays2[0]))) {
			return s1;
		}
		return s2;
	}

	private static String compareLowestPeriodAnnualMonthly(String s1, String s2, int frequencyDivision)
			throws GtnFrameworkGeneralException {
		try {
			String[] arrays1 = s1.split(" ");
			String[] arrays2 = s2.split(" ");
			LOGGER.info("frequencyDivision===========================" + frequencyDivision);
			if ((frequencyDivision == 12) && (Integer.parseInt(arrays1[1]) < Integer.parseInt(arrays2[1]))) {
				LOGGER.info("***********arrays1[1])***************" + Integer.valueOf(arrays1[1]));
				return s1;
			}
			Date date1 = new SimpleDateFormat("MMM").parse(arrays1[0]);
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(date1);
			LOGGER.info("cal.get(Calendar.MONTH)===========" + cal1.get(Calendar.MONTH));
			Date date2 = new SimpleDateFormat("MMM").parse(arrays2[0]);
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(date2);
			LOGGER.info("cal.get(Calendar.MONTH)=====2nd======" + cal2.get(Calendar.MONTH));
			if (arrays1[1].equals(arrays2[1])
					&& (cal1.get(Calendar.MONTH) < cal2.get(Calendar.MONTH))) {
				return s1;
			}
			LOGGER.info("return s2================" + s2);
			return s2;
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in getModifiedProcessModeValue ", e);
		}
	}
}
