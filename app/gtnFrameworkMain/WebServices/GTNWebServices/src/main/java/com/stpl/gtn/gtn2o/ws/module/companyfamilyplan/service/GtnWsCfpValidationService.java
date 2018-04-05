package com.stpl.gtn.gtn2o.ws.module.companyfamilyplan.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean.GtnCFamilyPlanValidationBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.cfprequest.GtnWsCfpRequest;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@Service()
@Scope(value = "singleton")
public class GtnWsCfpValidationService {
	public GtnWsCfpValidationService() {
		/**
		 * empty constructor
		 */
	}
	
	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@SuppressWarnings("unchecked")
	public List<Object> cfpIdAndCfpNoValidation(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		try {
			
			GtnWsCfpRequest gtnWsCfpRequest = gtnWsRequest.getGtnWsCfpRequest();
			
			String query = gtnWsSqlService.getQuery("getCfpCfpIdAndCfpNoValidationQuery");

			Object[] params = { gtnWsCfpRequest.getGtnCFamilyPlan().getCfpInfo().getCfpId(),
					gtnWsCfpRequest.getGtnCFamilyPlan().getCfpInfo().getCfpNo() };
			GtnFrameworkDataType[] types = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING };
			if (gtnWsCfpRequest.getGtnCFamilyPlan().getCfpInfo().getCfpSid() != null
					&& gtnWsCfpRequest.getGtnCFamilyPlan().getCfpInfo().getCfpSid() > 0) {
				query += " AND CFP_MODEL_SID<>" + gtnWsCfpRequest.getGtnCFamilyPlan().getCfpInfo().getCfpSid() + " ;";
			}
			return (List<Object>) gtnSqlQueryEngine.executeSelectQuery(query, params, types);
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Error while executing cfpIdAndCfpNoValidation ", e);
		}

	}

	


	@SuppressWarnings("unchecked")
	public GtnCFamilyPlanValidationBean cfpCommonValidation(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		GtnCFamilyPlanValidationBean validationBean = new GtnCFamilyPlanValidationBean();
		try {
			String query = gtnWsSqlService.getQuery("getCfpCommonValidationQuery");

			Object[] params = { gtnWsRequest.getGtnWsGeneralRequest().getUserId(),
					gtnWsRequest.getGtnWsGeneralRequest().getSessionId() };
			GtnFrameworkDataType[] dataType = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING };
			List<Object[]> resultList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(query, params, dataType);
			for (Object[] objects : resultList) {
				String id = String.valueOf(objects[0]);
				String value = String.valueOf(objects[1]);
				switch (id) {
				case "COMPANIES_COUNT":
					validationBean.setCount(Integer.parseInt(value));
					break;
				case "CFP_DETAILS_START_DATE":
					validationBean.setStartDateNullCount(getInt(value));
					validationBean.setStartDateNullCompanyId(getString(value));
					break;
				case "CFP_DETAILS_ATTACHED_STATUS":
					validationBean.setStatusNullCount(getInt(value));
					validationBean.setStatusNullCompanyId(getString(value));
					break;
				case "START_DATE_GREATER_END_DATE":
					validationBean.setStartDateGreaterThanEndCount(getInt(value));
					validationBean.setStartDateGreaterThanEndCompanyId(getString(value));
					break;
				case "CHECKED_RECORD_COUNT":
					validationBean.setCheckedCount(Integer.parseInt(value));
					break;
				case "DUPLICATE":
					validationBean.setDuplicateCompanyId(value);
					validationBean.setDuplicateCompanyCount(Integer.parseInt(String.valueOf(objects[2])));
					break;
				default:
					break;
				}
			}
			return validationBean;
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Error while executing cfpCommonValidation ", e);
		}

	}

	int getInt(String value) {
		if (StringUtils.isNumeric(value)) {
			return Integer.parseInt(value);
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
