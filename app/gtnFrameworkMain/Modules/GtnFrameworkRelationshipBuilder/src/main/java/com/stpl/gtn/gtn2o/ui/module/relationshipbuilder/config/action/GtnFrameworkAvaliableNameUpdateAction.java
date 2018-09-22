package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.constants.GtnFrameworkRelationshipBuilderConstants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.constants.GtnWsRelationshipBuilderConstants;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.constants.GtnWsRelationshipBuilderKeyConstant;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.relationshipbuilder.GtnWsRelationshipBuilderRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkAvaliableNameUpdateAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
                String prefix="RB001_";
		GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId);
		tableBaseComponent.setTableValue(null);
		List<GtnWsRecordBean> records = tableBaseComponent.getComponentData().getDataTableRecordList();
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		if (records != null && !records.isEmpty()) {
			String levelName = records.get(0)
					.getStringPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.LEVEL_NAME.ordinal());
			tableBaseComponent.setTableColumnHeader(GtnFrameworkRelationshipBuilderConstants.AVAILABLE_LIST_COLUMNID,
					levelName);
			int levelNo = records.get(0)
					.getIntegerPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.LEVEL_NO.ordinal());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent((String) parameters.get(1), componentId)
					.setCaption("Available &lt; Level " + levelNo + " &gt; :");
		} else {
                    loadLevelNameForAvailableEmptyTable(gtnUIFrameWorkActionConfig, prefix, tableBaseComponent, parameters, componentId);
                }
	}

    private void loadLevelNameForAvailableEmptyTable(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig, String prefix, 
            GtnUIFrameworkBaseComponent tableBaseComponent, List<Object> parameters, String componentId) throws GtnFrameworkGeneralException {
        GtnUIFrameworkWebServiceClient wsRelationclient = new GtnUIFrameworkWebServiceClient();
        GtnUIFrameworkWebserviceRequest wsRelationrequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsRelationshipBuilderRequest relationGtnRequest = new GtnWsRelationshipBuilderRequest();
        
        int levelNoForHierarchy = ((GtnWsRecordBean) gtnUIFrameWorkActionConfig.getEventParameter())
                .getIntegerPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.LEVEL_NO.ordinal()) + 1;
        relationGtnRequest.setLevelNo(levelNoForHierarchy);
        GtnUIFrameworkBaseComponent hierarchyDefinitionSid = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(prefix+"hierarchyName");
        relationGtnRequest.setHierarchyDefSId(hierarchyDefinitionSid.getIntegerFromField());
        
        GtnUIFrameworkBaseComponent hierachyVersion = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(prefix+GtnFrameworkCommonConstants.VERSION_NO);
        relationGtnRequest.setVersionNo(hierachyVersion.getIntegerFromField());
        wsRelationrequest.setRelationshipBuilderRequest(relationGtnRequest);
        
        GtnUIFrameworkWebserviceResponse gtnResponse = wsRelationclient.callGtnWebServiceUrl(
                GtnWsRelationshipBuilderConstants.GTN_RELATIONSHIP_BUILDER_SERVICE
                        + GtnWsRelationshipBuilderConstants.HIERARCHY_NAME_LEVEL_VALUE,
                wsRelationrequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
        if(!gtnResponse.getGtnWsRelationshipBuilderResponse().getHierarchyName().equals("null")){
        tableBaseComponent.setTableColumnHeader(GtnFrameworkRelationshipBuilderConstants.AVAILABLE_LIST_COLUMNID,
                gtnResponse.getGtnWsRelationshipBuilderResponse().getHierarchyName());
        GtnUIFrameworkGlobalUI.getVaadinBaseComponent((String) parameters.get(1), componentId)
                .setCaption("Available &lt; Level " + gtnResponse.getGtnWsRelationshipBuilderResponse().getLevelNo() + " &gt; :");
        }
    }

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
