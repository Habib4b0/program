package com.stpl.app.gtnforecasting.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkHierarchyQueryBean;
import com.stpl.ifs.util.CommonUtil;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileReadWriteService {

    public FileReadWriteService() {
        super();
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(FileReadWriteService.class);

    private List<GtnFrameworkHierarchyQueryBean> getConfigFromJSON(int hierarchyId, int hierarchyVersionNo) {

        String fileName = getFileNameByHierarchyId(hierarchyId, hierarchyVersionNo);
        List<GtnFrameworkHierarchyQueryBean> rootConfig = null;
        ObjectMapper mapper = new ObjectMapper();
        // Convert object to JSON string and save into a file directly
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			rootConfig = (Arrays.asList(mapper.readValue(CommonUtil.getFilePath(fileName), GtnFrameworkHierarchyQueryBean[].class)));
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return rootConfig;

    }

    private String getFolderName() {
        return CommonUtil.getGtnDataPath() + "HierarchyQueries/";
    }

    private String getFileNameByHierarchyId(int hierarchyId, int hierarchyVersionNo) {

        return getFolderName() + "HierachyBuilerQuery" + hierarchyId + "_" + hierarchyVersionNo + ".json";

    }

    public GtnFrameworkHierarchyQueryBean getQueryFromFile(int hierarchyId, String hierarchyLevelDefnId,
            int hierarchyVersionNo) {
        List<GtnFrameworkHierarchyQueryBean> queryBeanList = getConfigFromJSON(hierarchyId, hierarchyVersionNo);
        for (GtnFrameworkHierarchyQueryBean gtnFrameworkQueryBean : queryBeanList) {
            if (gtnFrameworkQueryBean.getHierarchyLevelDefSid() == Integer.parseInt(hierarchyLevelDefnId)) {
                return gtnFrameworkQueryBean;
            }
        }
        return null;

    }

}
