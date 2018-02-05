package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.concurrency;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticRelationUpdateService;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutoupdateService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;

@Service
@Scope(value = "prototype")
public class GtnFrameworkAutomaticRunnable implements Runnable {


	@Autowired
	private GtnFrameworkAutomaticRelationUpdateService autoUpdateService;


	private static final GtnWSLogger LOGGER = GtnWSLogger
			.getGTNLogger(GtnFrameworkAutomaticRelationUpdateService.class);

	private GtnWsRelationshipBuilderBean relationBean;
	private String userId;

	public GtnFrameworkAutomaticRunnable() {
		super();
	}

	public GtnWsRelationshipBuilderBean getRelationBean() {
		return relationBean;
	}

	public void setRelationBean(GtnWsRelationshipBuilderBean relationBean) {
		this.relationBean = relationBean;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public void run() {
		try {
			checkAndUpdateAutomaticRelationship(relationBean, userId);
		} catch (GtnFrameworkGeneralException | InterruptedException e) {
			LOGGER.error(e.getMessage());
		}

	}
	public boolean checkAndUpdateAutomaticRelationship(GtnWsRelationshipBuilderBean relationBean, String userId)
			throws GtnFrameworkGeneralException, InterruptedException {
		if (relationBean != null) {
			GtnFrameworkAutoupdateService automaticService = autoUpdateService
					.getAutomaticserviceObject(
					relationBean.getHierarchycategory());
			List<HierarchyLevelDefinitionBean> hierarchyDefinitionList = autoUpdateService
					.getHierarchyBuilder(
					relationBean.getHierarchyDefinitionSid(), relationBean.getHierarchyVersion());
			if (automaticService.checkForAutoUpdate(relationBean, hierarchyDefinitionList)) {
				automaticService.doAutomaticUpdate(hierarchyDefinitionList, relationBean);
				return Boolean.TRUE;
			}
			LOGGER.info("checkAndUpdateAutomaticRelationship has finihsed");
		}
		return Boolean.FALSE;
	}


}
