/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.workflowinbox.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.constants.GtnFrameworkWorkflowInboxClassConstants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.workflow.GtnWsCommonWorkflowRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.workflow.GtnWsCommonWorkflowResponse;
import com.stpl.gtn.gtn2o.ws.workflow.bean.GtnWsWorkflowInboxBean;
import com.vaadin.server.Page;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.JavaScriptFunction;
import elemental.json.JsonArray;
import java.util.HashMap;
import java.util.List;


/**
 *
 * @author Hazihabibullah.Syed
 */
public class GtnFrameworkUpdateTableJSListenerAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkAddJSListenerAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// Empty Method

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> actionParams = gtnUIFrameWorkActionConfig.getActionParameterList();
		final GtnUIFrameworkBaseComponent table = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParams.get(1).toString());

		final String url = GtnFrameworkWorkflowInboxClassConstants.WORKFLOWLOOKUPSLASH
				+ GtnFrameworkWorkflowInboxClassConstants.GETGCMWORKFLOWLIST;
		String pageParameterss = null;
		String projectionmasterSid = null;
		pageParameterss = Page.getCurrent().getLocation().getQuery();

		if (pageParameterss != null) {

			String[] parameterss = pageParameterss.split("&");

			HashMap<String, String> hmm = new HashMap<>();

			for (String paraa : parameterss) {
				String[] paraStrr = paraa.split(GtnFrameworkWorkflowInboxClassConstants.EQUAL);
				hmm.put(paraStrr[0], paraStrr[1]);
			}

			projectionmasterSid = hmm.get(GtnFrameworkWorkflowInboxClassConstants.PROJECTIONMASTER_SID);
		}
		if (projectionmasterSid != null) {

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.SUMMARY_SEARCH_MAIN_INNERLAYOUT)
					.setComponentEnable(false);

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.BUTTONMAIN_LAYOUT)
					.setComponentEnable(false);

			JavaScript.getCurrent().addFunction("storageEventListener", new JavaScriptFunction() {
				/**
				 *
				 */
				private static final long serialVersionUID = 1L;


                            @Override
                            public void call(JsonArray arguments) {
                               	try {

						table.getLogicFromPagedDataTable()
								.setCurrentPage(table.getLogicFromPagedDataTable().getCurrentPage());

						if (arguments.getBoolean(1)) {

							JavaScript.getCurrent()
									.execute("localStorage.setItem('" + arguments.getString(0) + "', 'false');");
						}

					} catch (Exception e) {
						gtnLogger.error(e.getMessage());
					}
                            }
			});

			loadDataFromService(url, table, projectionmasterSid);
		}

	}

	private void loadDataFromService(String url, GtnUIFrameworkBaseComponent tableComponentId,
			String projectionMasterSid) {
		try {
			GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
			GtnWsCommonWorkflowRequest forecastRequest = new GtnWsCommonWorkflowRequest();
			GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
			GtnWsWorkflowInboxBean projMasterBean = new GtnWsWorkflowInboxBean();
			projMasterBean.setProjectionMasterSid(Integer.valueOf(projectionMasterSid));
			request.setGtnWsGeneralRequest(generalRequest);
			request.setGtnWSCommonWorkflowRequest(forecastRequest);
			request.getGtnWSCommonWorkflowRequest().setGtnWorkflowInboxBean(projMasterBean);
			GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(url, request,
					GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			request.setGtnWSCommonWorkflowRequest(forecastRequest);
			request.getGtnWSCommonWorkflowRequest().setGtnWorkflowInboxBean(projMasterBean);
			GtnWsCommonWorkflowResponse workflowResponse = response.getGtnWSCommonWorkflowResponse();
			List<GtnWsRecordBean> resultBeanList = workflowResponse.getResultList();
			tableComponentId.loadContainer(tableComponentId.getComponentId(), resultBeanList);

		} catch (Exception exception) {
			gtnLogger.error(exception.getMessage(), exception);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
