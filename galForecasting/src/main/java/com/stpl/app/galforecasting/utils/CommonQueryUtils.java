/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.galforecasting.utils;

import com.stpl.app.galforecasting.dao.impl.CommonDAOImpl;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 * Class to generate the CCP query common to all screens. 
 * 
 * @author sibi
 */
public class CommonQueryUtils {
    
    private static CommonQueryUtils commonQueryUtils;
    private static final CommonDAOImpl commonDao = new CommonDAOImpl();
    
    /**
     * Private Constructor to make this class as a Singleton Class.
     */
    private CommonQueryUtils(){        
    }
    
    /**
     * Method returns the same CommonQueryUtils object, When called.
     * @return 
     */
    public static CommonQueryUtils getInstance(){
        return commonQueryUtils == null ? commonQueryUtils = new CommonQueryUtils() : commonQueryUtils;
    }
    
    /**
     * Method builds the CCP Query used in different views.
     * 
     * @param projSelDTO
     * @return 
     */
    public String getCCPQuery(ProjectionSelectionDTO projSelDTO) {
       String ccpQuery = getCCPTempTableQuery();
        if (projSelDTO.isIsCustomHierarchy()) {
            ccpQuery += getCustomCCPQuery(projSelDTO);
        } else {
            ccpQuery += getGeneralCCPQuery(projSelDTO);
        }
        return ccpQuery;
    }
     
    /**
     * Query to Declare the Temp CCP Table
     * @return 
     */
    public String getCCPTempTableQuery() {
        String tableQuery = "DECLARE @CCP TABLE\n"
                + "  (\n"
                + "     RELATIONSHIP_LEVEL_SID INT,\n"
                + "     PROJECTION_DETAILS_SID INT,\n"
                + "     CCP_DETAILS_SID        INT,\n"
                + "     HIERARCHY_NO           VARCHAR(50)\n"
                + "  ) \n"
                + " INSERT INTO @CCP\n"
                + "            (RELATIONSHIP_LEVEL_SID,PROJECTION_DETAILS_SID,CCP_DETAILS_SID,HIERARCHY_NO) \n";
        return tableQuery;
    }

    /**
     * Builds the query for Custom view based on given inputs.
     * 
     * @param projSelDTO - ProjectionSelectionDTO
     * @return 
     */
    public String getCustomCCPQuery(ProjectionSelectionDTO projSelDTO) {
        String customerHierarchyNo = projSelDTO.getCustomerHierarchyNo() + Constant.PERCENT;
        String productHierarchyNo = projSelDTO.getProductHierarchyNo() + Constant.PERCENT;
        String customerLevelNo = Constant.PERCENT;
        String productLevelNo = Constant.PERCENT;        

        if (projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
            customerLevelNo = StringUtils.EMPTY + projSelDTO.getTreeLevelNo();
        } else if (projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            productLevelNo = StringUtils.EMPTY + projSelDTO.getTreeLevelNo();
        }
        
        String ccpQuery = "SELECT distinct HLD" + projSelDTO.getHierarchyIndicator() + ".RELATIONSHIP_LEVEL_SID, PD.PROJECTION_DETAILS_SID,CCPMAP" + projSelDTO.getHierarchyIndicator() + ".CCP_DETAILS_SID,HLD" + projSelDTO.getHierarchyIndicator() + ".HIERARCHY_NO FROM \n"
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n"
                + " AND RLD.RELATIONSHIP_BUILDER_SID = " + projSelDTO.getCustRelationshipBuilderSid() + "\n"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n";

        ccpQuery += " ) CCPMAPC \n"
                + " JOIN"
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n"
                + " AND RLD.RELATIONSHIP_BUILDER_SID = " + projSelDTO.getProdRelationshipBuilderSid() + "\n"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n";

        ccpQuery += " ) CCPMAPP \n"
                + " ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID \n"
                + " JOIN "
                + " (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD \n"
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " AND CVD.CUSTOM_VIEW_MASTER_SID=" + projSelDTO.getCustomId() + " AND CVD.LEVEL_NO  like '" + customerLevelNo + "'\n"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " WHERE RLD2.HIERARCHY_NO like '" + customerHierarchyNo + "') HLDC ON CCPMAPC.HIERARCHY_NO like HLDC.HIERARCHY_NO+'%' \n"
                + " JOIN "
                + " (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD \n"
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " AND CVD.CUSTOM_VIEW_MASTER_SID=" + projSelDTO.getCustomId() + " AND CVD.LEVEL_NO  like '" + productLevelNo + "'\n"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " WHERE RLD2.HIERARCHY_NO like '" + productHierarchyNo + "') HLDP ON CCPMAPP.HIERARCHY_NO like HLDP.HIERARCHY_NO+'%' \n"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID = CCPMAPC.CCP_DETAILS_SID AND\n"
                + "  PD.PROJECTION_MASTER_SID =" + projSelDTO.getProjectionId() + "\n";

        return ccpQuery;
    }

    /**
     * Builds the query for Customer/Product view based on given inputs.
     * 
     * @param projSelDTO - ProjectionSelectionDTO
     * @return String
     */
    public String getGeneralCCPQuery(ProjectionSelectionDTO projSelDTO) {

        String relationshipBuilderSid = StringUtils.EMPTY;
        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
            relationshipBuilderSid = projSelDTO.getCustRelationshipBuilderSid();
        } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
            relationshipBuilderSid = projSelDTO.getProdRelationshipBuilderSid();
        }

        String viewtable = getViewTableName(projSelDTO.getHierarchyIndicator());
        String ccpQuery = " SELECT LCCP.RELATIONSHIP_LEVEL_SID,LCCP.PROJECTION_DETAILS_SID, LCCP.CCP_DETAILS_SID, LCCP.HIERARCHY_NO from \n"
                + "(SELECT CCPMAP.CCP_DETAILS_SID, HLD.HIERARCHY_NO, HLD.RELATIONSHIP_LEVEL_SID,CCPMAP.PROJECTION_DETAILS_SID from \n"
                + "(SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID,PD.PROJECTION_DETAILS_SID \n"
                + "FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + "JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n"
                + "AND RLD.RELATIONSHIP_BUILDER_SID=" + relationshipBuilderSid + "\n"
                + "JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID \n"
                + "AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId();

        ccpQuery += " ) CCPMAP,\n"
                + " (SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD1 \n"
                + " JOIN " + viewtable + " PCH \n"
                + " ON PCH.RELATIONSHIP_LEVEL_SID=RLD1.RELATIONSHIP_LEVEL_SID \n"
                + " AND PCH.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " WHERE RLD1.HIERARCHY_NO like '" + projSelDTO.getHierarchyNo() + "%' ) HLD \n"
                + " WHERE CCPMAP.HIERARCHY_NO like HLD.HIERARCHY_NO+'%' ) LCCP \n"
                + " WHERE LCCP.HIERARCHY_NO in \n"
                + " (SELECT RLD2.HIERARCHY_NO \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD2 \n"
                + " JOIN " + viewtable + " PCH2 \n"
                + " ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID \n"
                + " AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " WHERE RLD2.LEVEL_NO=" + projSelDTO.getTreeLevelNo() + ") \n";

        return ccpQuery;
    }

    /**
     * Method returns the Table based on Hierarchy Indicator.
     * @param hierarchyIndicator
     * @return 
     */
    public String getViewTableName(String hierarchyIndicator){
    String viewtable = StringUtils.EMPTY;
        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(hierarchyIndicator)) {
            viewtable = "PROJECTION_CUST_HIERARCHY";
        } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(hierarchyIndicator)) {
            viewtable = "PROJECTION_PROD_HIERARCHY";
        }
        return viewtable;
    }
    /**
     * Logic to Save projection selection section in all screens
     *
     * @param map
     * @param projectionID
     * @param screenName
     * @param saveOrUpdate
     */
    public void saveSelection(Map map, int projectionID, String screenName, String saveOrUpdate) {
        Object[] obj = map.keySet().toArray();
        StringBuilder queryBuilder = new StringBuilder();
        if (Constant.SAVE.equalsIgnoreCase(saveOrUpdate)) {
            for (int i = 0; i < map.size(); i++) {
                queryBuilder.append("INSERT INTO CH_PROJECTION_SELECTION (PROJECTION_MASTER_SID,SCREEN_NAME,FIELD_NAME,FIELD_VALUES) VALUES ");
                queryBuilder.append("('").append(projectionID).append("','").append(screenName).append("','");
                queryBuilder.append(obj[i]).append("','").append(map.get(obj[i])).append("')\n");
            }
        } else {
            for (int i = 0; i < map.size(); i++) {
                queryBuilder.append("UPDATE CH_PROJECTION_SELECTION SET FIELD_NAME = '");
                queryBuilder.append(obj[i]).append("',").append("FIELD_VALUES = '").append(map.get(obj[i])).append("'");
                queryBuilder.append(" WHERE PROJECTION_MASTER_SID = '").append(projectionID).append(" ' AND SCREEN_NAME = '").append(screenName).append("' AND FIELD_NAME ='").append(obj[i]).append("'\n");
            }
        }
        commonDao.executeBulkUpdateQuery(queryBuilder.toString(), null, null);
    }
    
}
