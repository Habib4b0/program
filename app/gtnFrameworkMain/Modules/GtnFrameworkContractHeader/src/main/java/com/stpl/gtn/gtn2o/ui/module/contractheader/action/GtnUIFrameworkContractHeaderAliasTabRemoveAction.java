package com.stpl.gtn.gtn2o.ui.module.contractheader.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.contract.bean.GtnwsContractAliasMasterBean;
import com.stpl.gtn.gtn2o.ws.contract.contants.GtnWsContractHeaderContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractHeaderRequest;

public class GtnUIFrameworkContractHeaderAliasTabRemoveAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> resultTableIdList = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnWsRecordBean dto = GtnUIFrameworkGlobalUI.getVaadinBaseComponent((String) resultTableIdList.get(0))
				.getValueFromPagedDataTable();
		if (dto != null) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent((String) resultTableIdList.get(0))
					.removeItemFromDataTable(dto);
			Integer aliasMasterSid = (Integer) dto.getPropertyValueByIndex(7);
			if (aliasMasterSid != null && aliasMasterSid > 0) {

				GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();

				GtnWsContractHeaderRequest chRequest = new GtnWsContractHeaderRequest();
				List<GtnwsContractAliasMasterBean> beanList = new ArrayList<>();
				GtnwsContractAliasMasterBean bean = new GtnwsContractAliasMasterBean();
				bean.setContractAliasMasterSid(aliasMasterSid);
				chRequest.setGtnwsContractAliasMasterBeanList(beanList);
				request.setGtnWsContractHeaderRequest(chRequest);
				new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
						GtnWsContractHeaderContants.GTN_WS_CONTRACT_HEADER_SERVICE
								+ GtnWsContractHeaderContants.GTN_WS_CONTRACT_ALIAS_DELETE_SERVICE,
						request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			}
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
