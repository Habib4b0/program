package com.stpl.gtn.gtn2o.ui.module.util;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.netsales.bean.GtnWsNsfUpdateBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.netsales.GtnWsNetSalesFormulaGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.netsales.GtnWsNetSalesGeneralResponse;

public class GtnFrameworkNSFCommonLogic {

	private GtnFrameworkNSFCommonLogic() {

	}

	public static boolean updateField(String column, Object value, boolean checkAll, int systemId, boolean salesBasis,
			String url, boolean isPopulate) {

		GtnUIFrameworkWebserviceRequest updateRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();

		generalWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		generalWSRequest.setSessionId(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId").toString());
		updateRequest.setGtnWsGeneralRequest(generalWSRequest);
		GtnWsNsfUpdateBean nsfUpdateBean = new GtnWsNsfUpdateBean();

		nsfUpdateBean.setPropertyId(column);
		nsfUpdateBean.setValue(value);
		nsfUpdateBean.setMasterSid(systemId);
		nsfUpdateBean.setCheckAll(checkAll);
		nsfUpdateBean.setPopulate(isPopulate);
		nsfUpdateBean.setSalesBasis(salesBasis);

		GtnWsNetSalesFormulaGeneralRequest gtnWsNetSalesGeneralRequest = new GtnWsNetSalesFormulaGeneralRequest();
		gtnWsNetSalesGeneralRequest.setNsfUpdateBean(nsfUpdateBean);
		updateRequest.setGtnWsNetSalesGeneralRequest(gtnWsNetSalesGeneralRequest);
		updateRequest.setGtnWsGeneralRequest(generalWSRequest);

		GtnUIFrameworkWebserviceResponse commonWsResponse = new GtnUIFrameworkWebServiceClient()
				.callGtnWebServiceUrl(url, updateRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsGeneralResponse generalResponse = commonWsResponse.getGtnWsGeneralResponse();
		return generalResponse.isSucess();

	}

	public static void reloadTable(String viewId, String tableId) throws GtnFrameworkValidationFailedException {
		GtnUIFrameworkComponentData selectedCustomersComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(viewId + tableId);
		GtnUIFrameworkPagedTableLogic tableLogic = selectedCustomersComponentData.getCurrentPageTableLogic();
		tableLogic.setAdditioanlSearchCriteriaList(getAdditioanlSearchCriteriaList());
		tableLogic.startSearchProcess(new ArrayList<String>(), true);
	}

	public static List<GtnWebServiceSearchCriteria> getAdditioanlSearchCriteriaList() {
		List<GtnWebServiceSearchCriteria> additioanlSearchCriteriaList = new ArrayList<>();

		GtnWebServiceSearchCriteria userIdCriteria = new GtnWebServiceSearchCriteria();
		GtnWebServiceSearchCriteria sessionIdCriteria = new GtnWebServiceSearchCriteria();
		additioanlSearchCriteriaList.add(userIdCriteria);
		additioanlSearchCriteriaList.add(sessionIdCriteria);

		userIdCriteria.setFieldId(GtnFrameworkCommonStringConstants.USER_ID);
		userIdCriteria.setExpression("EQUALS");
		userIdCriteria.setFilterValue1(GtnUIFrameworkGlobalUI.getCurrentUser());

		sessionIdCriteria.setFieldId(GtnFrameworkCommonStringConstants.SESSION_ID);
		sessionIdCriteria.setExpression("EQUALS");
		sessionIdCriteria.setFilterValue1(
				GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.SESSION_ID).toString());
		return additioanlSearchCriteriaList;
	}

	public static boolean confirmCheckRecord(boolean isSalesBasis, String url) {
		GtnUIFrameworkWebserviceRequest updateRequest = getWsRequest(isSalesBasis);
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(url,
				updateRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsNetSalesGeneralResponse nsfGeneralResponse = gtnResponse.getGtnWsNetSalesGeneralResponse();
		GtnWsNsfUpdateBean nsfResponseBean = nsfGeneralResponse.getNsfUpdateBean();
		return nsfResponseBean.isCheckRecordFlag();
	}

	public static GtnUIFrameworkWebserviceRequest getWsRequest(boolean isSalesBasis) {
		GtnUIFrameworkWebserviceRequest updateRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		GtnWsNetSalesFormulaGeneralRequest gtnWsNsfUpdateRequest = new GtnWsNetSalesFormulaGeneralRequest();
		updateRequest.setGtnWsNetSalesGeneralRequest(gtnWsNsfUpdateRequest);
		GtnWsNsfUpdateBean nsfUpdateBean = new GtnWsNsfUpdateBean();
		nsfUpdateBean.setSalesBasis(isSalesBasis);
		gtnWsNsfUpdateRequest.setNsfUpdateBean(nsfUpdateBean);

		generalWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		generalWSRequest.setSessionId(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId").toString());
		updateRequest.setGtnWsGeneralRequest(generalWSRequest);

		return updateRequest;
	}
}