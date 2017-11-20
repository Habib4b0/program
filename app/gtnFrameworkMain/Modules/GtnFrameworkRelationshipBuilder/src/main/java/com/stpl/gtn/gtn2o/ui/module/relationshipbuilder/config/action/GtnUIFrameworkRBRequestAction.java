package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.relationshipbuilder.GtnWsRelationshipBuilderRequest;

public class GtnUIFrameworkRBRequestAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// EMPTY Method
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnWsRelationshipBuilderRequest rbRequest = (GtnWsRelationshipBuilderRequest) gtnUIFrameWorkActionConfig
				.getActionParameterList().get(1);
		if (rbRequest.getMainNode() != null) {
			rbRequest.setRelationshipName(rbRequest.getMainNode().getStringPropertyByIndex(0));
			rbRequest.setRelationshipDescription(rbRequest.getMainNode().getStringPropertyByIndex(1));
			rbRequest.setRelationshipType(rbRequest.getMainNode().getStringPropertyByIndex(2));
			rbRequest.setHierarchyName(rbRequest.getMainNode().getStringPropertyByIndex(3));
			rbRequest.setVersionNo(rbRequest.getMainNode().getIntegerPropertyByIndex(4));
			rbRequest.setStartDate(rbRequest.getMainNode().getDatePropertyByIndex(5));
			rbRequest.setCreationDate(rbRequest.getMainNode().getDatePropertyByIndex(6));
			rbRequest.setModifiedDate(rbRequest.getMainNode().getDatePropertyByIndex(7));
			rbRequest.setCreatedBy(rbRequest.getMainNode().getStringPropertyByIndex(8));
			rbRequest.setRbSysId(rbRequest.getMainNode().getIntegerPropertyByIndex(9));
			rbRequest.setHierarchyDefSId(rbRequest.getMainNode().getIntegerPropertyByIndex(10));
			rbRequest.setBuildType(rbRequest.getMainNode().getStringPropertyByIndex(11));
			rbRequest.setHierarchyVersionNo(rbRequest.getMainNode().getIntegerPropertyByIndex(12));
			rbRequest.setNoOfLevels(rbRequest.getMainNode().getIntegerPropertyByIndex(13));
			rbRequest.setCreatedById(rbRequest.getMainNode().getIntegerPropertyByIndex(14));
			rbRequest.setRelationshipTypeId(rbRequest.getMainNode().getIntegerPropertyByIndex(15));
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}