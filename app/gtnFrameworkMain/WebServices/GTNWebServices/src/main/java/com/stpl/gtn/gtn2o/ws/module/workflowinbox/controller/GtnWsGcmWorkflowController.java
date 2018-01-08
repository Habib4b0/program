/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.workflowinbox.controller;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.config.GtnWsAllListConfig;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.workflowinbox.constants.GtnWsWorkflowQueryContants;
import com.stpl.gtn.gtn2o.ws.module.workflowinbox.service.GtnWsGcmWorkflowService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.workflow.GtnWsCommonWorkflowResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Hazihabibullah.Syed
 */
@RestController
@RequestMapping(value = GtnWsWorkflowQueryContants.WORKFLOWLOOKUPSLASH)
public class GtnWsGcmWorkflowController {

	public GtnWsGcmWorkflowController() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsGcmWorkflowController.class);

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsGcmWorkflowService gcmWorkflowWebservice;

	@Autowired
	private GtnWsAllListConfig gtnWebServiceAllListConfig;

	public GtnWsAllListConfig getGtnWebServiceAllListConfig() {
		return gtnWebServiceAllListConfig;
	}

	public void setGtnWebServiceAllListConfig(GtnWsAllListConfig gtnWebServiceAllListConfig) {
		this.gtnWebServiceAllListConfig = gtnWebServiceAllListConfig;
	}

	@SuppressWarnings("unchecked")
	@PostMapping(value = GtnWsWorkflowQueryContants.GET_GCM_WF_LIST)

	public GtnUIFrameworkWebserviceResponse gcmWorkflowView(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnUIFrameworkWebserviceResponse gcmWorkflowResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsCommonWorkflowResponse workflowresponse = new GtnWsCommonWorkflowResponse();
		List<Object[]> resultList = null;

		try {
			gcmWorkflowResponse.setGtnWsGeneralResponse(new GtnWsGeneralResponse());
			gcmWorkflowResponse.getGtnWsGeneralResponse().setSucess(true);

			logger.info("Enter gcmWorkflowView");
			GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
			String query = gcmWorkflowWebservice.getWorkflowGcmQuery(gtnWsRequest);
			resultList = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(query);

			List<GtnWsRecordBean> resultListBean = getResultListBean(resultList);
			workflowresponse.setResultList(resultListBean);
			gcmWorkflowResponse.setGtnSerachResponse(gtnSerachResponse);
			gcmWorkflowResponse.setGtnWSCommonWorkflowResponse(workflowresponse);
			return gcmWorkflowResponse;
		} catch (GtnFrameworkGeneralException ex) {
			gcmWorkflowResponse.getGtnWsGeneralResponse().setSucess(false);
			logger.error("Exception while gcmWorkflowView web service", ex);
			gcmWorkflowResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
			return gcmWorkflowResponse;
		} catch (Exception e) {
			logger.error("Exception in End()----" + e.getMessage());
			return null;

		}

	}

	private List<GtnWsRecordBean> getResultListBean(List<Object[]> resultList) throws ParseException {
		List<GtnWsRecordBean> resultListBean = new ArrayList<>();
		for (int i = 0; i < resultList.size(); i++) {

			Object[] currentRow = resultList.get(i);
			GtnWsRecordBean bean = new GtnWsRecordBean();

			bean.setRecordHeader(
					Arrays.asList(GtnWsWorkflowQueryContants.WORKFLOWID, GtnWsWorkflowQueryContants.WORKFLOWNAME,
							GtnWsWorkflowQueryContants.WORKFLOWDESC, GtnWsWorkflowQueryContants.STATUS,
							GtnWsWorkflowQueryContants.CREATEDBY, GtnWsWorkflowQueryContants.CREATEDDATE,
							GtnWsWorkflowQueryContants.APPROVEDBY, GtnWsWorkflowQueryContants.APPROVEDDATE));

			currentRow[0] = getCurrentRow(currentRow[0]);
			currentRow[1] = getCurrentRow(currentRow[1]);
			currentRow[2] = getCurrentRow(currentRow[2]);
			currentRow[3] = getCurrentRow(currentRow[3]);
			currentRow[4] = getCurrentRow(currentRow[4]);
			currentRow[5] = getCurrentRow(currentRow[5]);

			currentRow[6] = gtnWebServiceAllListConfig.getUserIdNameMap().get(getInt(currentRow[6])) == null ? ""
					: gtnWebServiceAllListConfig.getUserIdNameMap().get(getInt(currentRow[6]));
			currentRow[7] = formatDate(String.valueOf(currentRow[7])) == null ? ""
					: formatDate(String.valueOf(currentRow[7]));
			currentRow[8] = gtnWebServiceAllListConfig.getUserIdNameMap().get(getInt(currentRow[8])) == null ? ""
					: gtnWebServiceAllListConfig.getUserIdNameMap().get(getInt(currentRow[8]));
			currentRow[9] = formatDate(String.valueOf(currentRow[9])) == null ? ""
					: formatDate(String.valueOf(currentRow[9]));

			bean.setProperties(Arrays.asList(currentRow));
			resultListBean.add(bean);

		}
		return resultListBean;
	}

	private String getCurrentRow(Object value) {
		return String.valueOf(value == null ? "" : String.valueOf(value));
	}

	private static String formatDate(String value) throws ParseException {
		String date = StringUtils.EMPTY;
		SimpleDateFormat parse = new SimpleDateFormat(DateFormatConstants.YYYYMMDDHHMMSSSSS.getConstant());
		SimpleDateFormat format = new SimpleDateFormat(DateFormatConstants.MMDDYYYY.getConstant());
		if (value != null && !StringUtils.EMPTY.equals(value) && !"null".equals(value)) {
			date = format.format(parse.parse(value));
		}
		return date;
	}

	public enum DateFormatConstants {

		MMDDYYYY(DATE_FORMAT_MMDDYYYY), YYYYMMDDHHMMSSSSS(GtnWsWorkflowQueryContants.YYYYSSS), MMDDYYYYHHMMSS(
				GtnWsWorkflowQueryContants.MMYYYYSS);
		private final String constant;

		private DateFormatConstants(String constant) {
			this.constant = constant;
		}

		public String getConstant() {
			return constant;
		}

		@Override
		public String toString() {
			return constant;
		}
	}

	public static final String DATE_FORMAT_MMDDYYYY = GtnWsWorkflowQueryContants.MMDDYYYY;

	int getInt(Object value) {
		return value == null ? 0 : (Integer) value;
	}

}
