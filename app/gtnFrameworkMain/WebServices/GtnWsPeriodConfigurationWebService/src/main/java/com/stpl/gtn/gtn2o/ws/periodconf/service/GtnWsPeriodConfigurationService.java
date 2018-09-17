package com.stpl.gtn.gtn2o.ws.periodconf.service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryExecutorBean;
import com.stpl.dependency.queryengine.request.GtnQueryEngineWebServiceRequest;
import com.stpl.dependency.queryengine.response.GtnQueryEngineWebServiceResponse;
import com.stpl.dependency.webservice.GtnCommonWebServiceImplClass;
import com.stpl.gtn.gtn2o.ws.GtnFrameworkPropertyManager;
import com.stpl.gtn.gtn2o.ws.periodconf.constants.GtnWsPeriodConfigurationConstants;
import com.stpl.gtn.gtn2o.ws.periodconf.model.PeriodConfData;
import com.stpl.gtn.gtn2o.ws.periodconf.sqlservice.GtnWsPeriodConfSqlService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.serviceregistry.GtnServiceRegistryWsRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;

@Service
public class GtnWsPeriodConfigurationService extends GtnCommonWebServiceImplClass {

	private List<PeriodConfData> allBusinessProcessTypeResultObject = new ArrayList<>();

	public List<Object[]> getPeriodResults(String businessProcessType) {
		return loadDateBusinessType(businessProcessType);
	}

	@Autowired
	private GtnWsPeriodConfSqlService gtnWsPeriodConfSqlService;

	private GtnWsPeriodConfigurationService() {
		super(GtnWsPeriodConfigurationService.class);
	}

	public void init() {
		logger.info("Entering into init method");
		GtnUIFrameworkWebserviceRequest request = registerWs();
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(
				getWebServiceEndpointBasedOnModule(
						GtnWsPeriodConfigurationConstants.GTN_SERVICEREGISTTRY_REGISTERWEBSERVICE,
						GtnWsPeriodConfigurationConstants.GTN_SERVICEREGISTTRY),
				request, GtnUIFrameworkWebserviceResponse.class);
		logger.info("Webservice Registered");
		this.loadDate();
	}

	public GtnQueryEngineWebServiceRequest createQuery(String loadDateQuery) {
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		queryExecutorBean.setSqlQuery(loadDateQuery);
		queryExecutorBean.setQueryType("SELECT");
		GtnQueryEngineWebServiceRequest gtnQueryEngineWebServiceRequest = new GtnQueryEngineWebServiceRequest();
		gtnQueryEngineWebServiceRequest.setQueryExecutorBean(queryExecutorBean);
		return gtnQueryEngineWebServiceRequest;
	}

	public String readProperty(String lookUpValue) {
		String loadDateQuery = gtnWsPeriodConfSqlService.getQuery(lookUpValue);
		logger.debug("LoadDate Query:" + loadDateQuery);
		return loadDateQuery;
	}

	public GtnQueryEngineWebServiceResponse callQueryEngine(
			GtnQueryEngineWebServiceRequest gtnQueryEngineWebServiceRequest) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForObject(
				getWebServiceEndpointBasedOnModule(
						GtnWsPeriodConfigurationConstants.GTN_SERVICEREGISTTRY_REDIRECTTOQUERYENGINE,
						GtnWsPeriodConfigurationConstants.GTN_SERVICEREGISTTRY),
				gtnQueryEngineWebServiceRequest, GtnQueryEngineWebServiceResponse.class);
	}

	public void populateallBusinessProcessTypeResultObject(List<Object[]> resultDataSet) {
		for (Object[] resultList : resultDataSet) {
			PeriodConfData periodconfdata = new PeriodConfData();
			periodconfdata.setFromDate(new SimpleDateFormat(GtnWsPeriodConfigurationConstants.GTN_PERIOD_DATE_FORMAT)
					.format(new Date((long) resultList[0])));
			periodconfdata.setToDate(new SimpleDateFormat(GtnWsPeriodConfigurationConstants.GTN_PERIOD_DATE_FORMAT)
					.format(new Date((long) resultList[1])));
			periodconfdata.setDescription(resultList[2].toString());
			logger.debug("StartDate:"
					+ new SimpleDateFormat(GtnWsPeriodConfigurationConstants.GTN_PERIOD_DATE_FORMAT)
							.format(new Date((long) resultList[0]))
					+ ":EndDate:" + new SimpleDateFormat(GtnWsPeriodConfigurationConstants.GTN_PERIOD_DATE_FORMAT)
							.format(new Date((long) resultList[1]))
					+ "Description:" + resultList[2]);
			periodconfdata.setQuaterInfo(getQuarter(periodconfdata.getFromDate(), periodconfdata.getToDate()));
			allBusinessProcessTypeResultObject.add(periodconfdata);
		}
	}

	public void loadDate() {
		logger.debug("Entering into webservice loadDate  WS->SR->QE->SR->WS");
		GtnQueryEngineWebServiceResponse response = callQueryEngine(createQuery(readProperty("loadDate")));
		populateallBusinessProcessTypeResultObject(response.getQueryResponseBean().getResultList());
	}

	public List<Object[]> loadDateBusinessType(String businessType) {
		List<Object[]> result = new ArrayList<>();
		Iterator<PeriodConfData> ite = allBusinessProcessTypeResultObject.iterator();
		while (ite.hasNext()) {
			PeriodConfData temp = ite.next();
			if (temp.getDescription().equals(businessType)) {
				return temp.getQuaterInfo();
			}
		}
		return result;
	}

	@Override
	public GtnUIFrameworkWebserviceRequest registerWs() {
		logger.info("Building request to register Webservice in Service Registry");
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnServiceRegistryWsRequest gtnServiceRegistryWsRequest = new GtnServiceRegistryWsRequest();

		GtnWsServiceRegistryBean webServiceRegistryBean = new GtnWsServiceRegistryBean();
		getEndPointUrl(webServiceRegistryBean);
		logger.info("Webservice to Register:" + webServiceRegistryBean.getRegisteredWebContext());
		gtnServiceRegistryWsRequest.setGtnWsServiceRegistryBean(webServiceRegistryBean);
		request.setGtnServiceRegistryWsRequest(gtnServiceRegistryWsRequest);
		return request;
	}

	private void getEndPointUrl(GtnWsServiceRegistryBean webServiceRegistryBean) {
		webServiceRegistryBean.setWebserviceEndPointUrl(
				GtnFrameworkPropertyManager.getProperty("gtn.webservices.periodConfiguration.endPointUrl"));
		webServiceRegistryBean.setRegisteredWebContext("/GtnWsPeriodConfigurationWebService");

	}

	private List<Object[]> getQuarter(String startDate, String endDate) {
		List<Object[]> quarters = new ArrayList<>();
		DateFormat df = new SimpleDateFormat(GtnWsPeriodConfigurationConstants.GTN_PERIOD_DATE_FORMAT);
		DateFormat dfpsid = new SimpleDateFormat(GtnWsPeriodConfigurationConstants.GTN_PERIOD_SID_DATE_FORMAT);
		DateFormat dfYY = new SimpleDateFormat(GtnWsPeriodConfigurationConstants.GTN_PERIOD_YEAR_FORMAT);

		try {
			String periodCounterStartDate = readProperty("periodCounterStartDate");

			Calendar scal = Calendar.getInstance();
			scal.setTime(df.parse(startDate));
			Calendar ecal = Calendar.getInstance();
			ecal.setTime(df.parse(endDate));
			Calendar pcal = Calendar.getInstance();
			pcal.setTime(dfpsid.parse(periodCounterStartDate.trim()));

			while (ecal.getTime().after(scal.getTime()) || ecal.getTime().equals(scal.getTime())) {
				int periodSid = (scal.get(Calendar.YEAR) - pcal.get(Calendar.YEAR)) * 12
						+ (scal.get(Calendar.MONTH) - pcal.get(Calendar.MONTH)) + 1;
				int month = scal.get(Calendar.MONTH) + 1;

				int quarter = month % 3 == 0 ? (month / 3) : (month / 3) + 1;
				String[] s = { String.valueOf(periodSid), "Q" + quarter + "-" + dfYY.format(scal.getTime()) };
				logger.debug("Period Value" + Arrays.toString(s));
				quarters.add(s);
				scal.add(Calendar.MONTH, 3);
			}
		} catch (ParseException e) {
			logger.error("Error in generating quarter information:" + e.toString());
		}
		return quarters;
	}

}
