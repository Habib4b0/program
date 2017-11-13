package com.stpl.gtn.gtn2o.ws.module.periodconfiguration;

import java.util.ArrayList;

import org.junit.Ignore;

import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;

@Ignore
public class RequestBuilder {

	public GtnUIFrameworkWebserviceRequest getSearchCountRequest() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchRequest searchRequest = new GtnWsSearchRequest();
		searchRequest.setCount(true);
		searchRequest.setGtnWebServiceSearchCriteriaList(new ArrayList<GtnWebServiceSearchCriteria>());
		searchRequest.setGtnWebServiceOrderByCriteriaList(new ArrayList<GtnWebServiceOrderByCriteria>());
		gtnWsRequest.setGtnWsSearchRequest(searchRequest);
		return gtnWsRequest;
	}

	public GtnUIFrameworkWebserviceRequest getSearchDataRequest(int start, int offset) {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchRequest searchRequest = new GtnWsSearchRequest();
		searchRequest.setGtnWebServiceSearchCriteriaList(new ArrayList<GtnWebServiceSearchCriteria>());
		searchRequest.setGtnWebServiceOrderByCriteriaList(new ArrayList<GtnWebServiceOrderByCriteria>());
		searchRequest.setCount(false);
		searchRequest.setTableRecordStart(start);
		searchRequest.setTableRecordOffset(offset);
		gtnWsRequest.setGtnWsSearchRequest(searchRequest);
		return gtnWsRequest;
	}

	public GtnUIFrameworkWebserviceRequest getComboboxData(String type) {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		generalWSRequest.setComboBoxType(type);
		request.setGtnWsGeneralRequest(generalWSRequest);
		return request;
	}

}
