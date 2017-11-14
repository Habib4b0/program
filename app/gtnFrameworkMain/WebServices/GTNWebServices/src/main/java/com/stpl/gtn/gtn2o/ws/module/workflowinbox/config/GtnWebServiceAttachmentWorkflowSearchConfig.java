package com.stpl.gtn.gtn2o.ws.module.workflowinbox.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoader;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.config.GtnWsColumnDetailsConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfigProvider;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.workflowinbox.constants.GtnWsWorkflowQueryContants;

public class GtnWebServiceAttachmentWorkflowSearchConfig implements GtnWsSearchQueryConfigLoader {

	private Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap = null;
	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWebServiceAttachmentWorkflowSearchConfig.class);

	@Override
	public Map<String, GtnWsSearchQueryConfig> getSearchQueryConfigMap() {
		if (searchQueryConfigMap == null) {
			searchQueryConfigMap = new HashMap<>();
			loadAttachmentWorkflowSearchQueryConfig();
		}
		return searchQueryConfigMap;
	}

	public void setSearchQueryConfigMap(Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap) {
		this.searchQueryConfigMap = searchQueryConfigMap;
	}

	public Map<String, GtnWsSearchQueryConfig> loadAttachmentWorkflowSearchQueryConfig() {
		GtnWsSearchQueryConfig gtnAttachmentSearchQueryConfig = new GtnWsSearchQueryConfig();
		try {
			gtnAttachmentSearchQueryConfig
					.setCountQuerySelectClause(GtnWsWorkflowQueryContants.GTN_WF_ATTACHMENT_COUNT);
			gtnAttachmentSearchQueryConfig.setCountQuery(GtnWsWorkflowQueryContants.GTN_WF_ATTACHMENT_COUNT_QUERY);
			gtnAttachmentSearchQueryConfig.setSearchQuery(GtnWsWorkflowQueryContants.GTN_WF_ATTACHMENT_SEARCH_QUERY);

			GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
			Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();
			fieldToColumnDetailsMap.put("documentName", configProvider.getColumnStringConfig("FILE_NAME", "HWM"));
			fieldToColumnDetailsMap.put("dateAdded", configProvider.getColumnDateConfig("UPLOADED_DATE", "HWM"));
			fieldToColumnDetailsMap.put("userName", configProvider.getColumnUserConfig("UPLOADED_BY", "HWM"));
			fieldToColumnDetailsMap.put("attachmentLink",
					configProvider.getColumnIntegerConfig("DOC_DETAILS_ID", "HWM"));
			fieldToColumnDetailsMap.put("fileType", configProvider.getColumnStringConfig("FILE_TYPE", "HWM"));

			gtnAttachmentSearchQueryConfig.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);

			List<GtnWebServiceOrderByCriteria> orderByClauseList = new ArrayList<>();
			orderByClauseList.add(new GtnWebServiceOrderByCriteria("DD.UPLOADED_DATE", "ASC"));
			gtnAttachmentSearchQueryConfig.setOrderByClause(orderByClauseList);

			searchQueryConfigMap.put("attachmentSearchQuery", gtnAttachmentSearchQueryConfig);

		} catch (Exception ex) {
			logger.error("error while cloning the GtnWsSearchQueryConfig");

		}
		return searchQueryConfigMap;
	}

}
