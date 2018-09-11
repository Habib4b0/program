/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.service.finderImpl;

import static com.stpl.app.gtnforecasting.logic.CommonLogic.LOGGER;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Abishek.Ram
 */
public class CustomViewMasterImpl {
     public List getHierarchyLevelsForDiscount(int projectionId, String hierarchyIndicator,int levelNo,int hierarchyLevelDefId){
        String customSql = StringUtils.EMPTY;
            try {

                String selectClause1 = "select RELATIONSHIP_LEVEL_SID from ";
                if("C".equals(hierarchyIndicator)){
                    selectClause1+= "PROJECTION_CUST_HIERARCHY";
                }else if("P".equals(hierarchyIndicator)){
                    selectClause1+= "PROJECTION_PROD_HIERARCHY";
                }
                selectClause1+=" where PROJECTION_MASTER_SID="+projectionId;
                String selectClause="select"
                        + " LEVEL_NO, LEVEL_NAME,"
                        + " RELATIONSHIP_LEVEL_VALUES,"
                        + " PARENT_NODE,"
                        + " HIERARCHY_LEVEL_DEFINITION_SID,"
                        + " RELATIONSHIP_BUILDER_SID";
                customSql = selectClause+" from RELATIONSHIP_LEVEL_DEFINITION where RELATIONSHIP_LEVEL_SID in ("+selectClause1+")"
                        +" and HIERARCHY_LEVEL_DEFINITION_SID="+hierarchyLevelDefId
                        +" and LEVEL_NO="+levelNo;
                return HelperTableLocalServiceUtil.executeSelectQuery(customSql);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                LOGGER.error(customSql);
                return Collections.emptyList();    
            } 
    }
}
