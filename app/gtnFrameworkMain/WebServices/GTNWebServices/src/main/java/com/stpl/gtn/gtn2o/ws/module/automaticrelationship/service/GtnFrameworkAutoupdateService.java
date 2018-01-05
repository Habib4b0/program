package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service;

import java.util.List;

import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;

public interface GtnFrameworkAutoupdateService {

	public GtnFrameworkAutomaticRelationUpdateService getService();

	public boolean checkAutomaticRelation(int relationshipBuilderSid) throws GtnFrameworkGeneralException;

	public boolean checkForAutoUpdate(GtnWsRelationshipBuilderBean relationBean,
			List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList) throws InterruptedException;

	public void doAutomaticUpdate(List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList,
			GtnWsRelationshipBuilderBean relationBean, String userId) throws GtnFrameworkGeneralException;
        
}
