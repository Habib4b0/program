package com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanValidationBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.ifprequest.GtnWsIfpRequest;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@Service()
@Scope(value = "singleton")
public class GtnWsIfpValidationService {
	public GtnWsIfpValidationService() {
		/**
		 * empty constructor
		 */
	}

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@SuppressWarnings("unchecked")
	public List<Object> ifpIdAndIfpNoValidation(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		try {
			GtnWsIfpRequest gtnWsIfpRequest = gtnWsRequest.getGtnWsIfpRequest();
			String query = gtnWsSqlService.getQuery("getIfpIfpIdAndIfpNoValidationQuery");

			Object[] params = { gtnWsIfpRequest.getGtnIFamilyPlan().getIfpInfo().getIfpId(),
					gtnWsIfpRequest.getGtnIFamilyPlan().getIfpInfo().getIfpNo() };
			GtnFrameworkDataType[] dataType = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING };

			if (gtnWsIfpRequest.getGtnIFamilyPlan().getIfpInfo().getIfpSid() != null) {
				query += " AND IFP_MODEL_SID <>" + gtnWsIfpRequest.getGtnIFamilyPlan().getIfpInfo().getIfpSid() + " ;";
			}
			return (List<Object>) gtnSqlQueryEngine.executeSelectQuery(query, params, dataType);
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException(e.getMessage(), e);
		}

	}

	@SuppressWarnings("unchecked")
	public GtnIFamilyPlanValidationBean ifpCommonValidation(GtnUIFrameworkWebserviceRequest ifpValidationRequest)
			throws GtnFrameworkGeneralException {
		GtnIFamilyPlanValidationBean ifpValidationBean = new GtnIFamilyPlanValidationBean();
		try {

			String ifpValidationQuery = gtnWsSqlService.getQuery("getIfpCommonValidationQuery");

			Object[] params = { ifpValidationRequest.getGtnWsGeneralRequest().getUserId(),
					ifpValidationRequest.getGtnWsGeneralRequest().getSessionId() };
			GtnFrameworkDataType[] dataType = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING };
			List<Object[]> resultList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(ifpValidationQuery, params, dataType);
			for (Object[] objects : resultList) {
				String id = String.valueOf(objects[0]);
				String value = String.valueOf(objects[1]);
				switch (id) {
				case "ITEMS_COUNT":
					ifpValidationBean.setCount(Integer.valueOf(value));
					break;
				case "IFP_DETAILS_START_DATE":
					ifpValidationBean.setStartDateNullCount(getInt(value));
					ifpValidationBean.setStartDateNullItemId(getString(value));
					break;
				case "IFP_DETAILS_ATTACHED_STATUS":
					ifpValidationBean.setStatusNullCount(getInt(value));
					ifpValidationBean.setStatusNullItemId(getString(value));
					break;
				case "START_DATE_GREATER_END_DATE":
					ifpValidationBean.setStartDateGreaterThanEndCount(getInt(value));
					ifpValidationBean.setStartDateGreaterThanEndItemId(getString(value));
					break;
				case "CHECKED_RECORD_COUNT":
					ifpValidationBean.setCheckedCount(Integer.valueOf(value));
					break;
				default:
					break;
				}
			}
			return ifpValidationBean;
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException(e.getMessage(), e);
		}

	}

	int getInt(String value) {
		if (StringUtils.isNumeric(value)) {
			return Integer.valueOf(value);
		}
		return 1;
	}

	String getString(String value) {
		if (StringUtils.isNumeric(value)) {
			return null;
		}
		return value;
	}

}
