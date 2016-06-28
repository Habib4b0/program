/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.queryUtils;

import com.stpl.app.galforecasting.dao.CommonDAO;
import com.stpl.app.galforecasting.dao.impl.CommonDAOImpl;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Mohamed.hameed
 */
public class CommonQueryUtils {

    private static final CommonDAO commonDao = new CommonDAOImpl();

    /**
     * The Constant LOGGER.
     */
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(CommonQueryUtils.class);

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
                queryBuilder.append("INSERT INTO NM_PROJECTION_SELECTION (PROJECTION_MASTER_SID,SCREEN_NAME,FIELD_NAME,FIELD_VALUES) VALUES ");
                queryBuilder.append("('").append(projectionID).append("','").append(screenName).append("','");
                queryBuilder.append(obj[i]).append("','").append(map.get(obj[i])).append("')\n");
            }
        } else {
            for (int i = 0; i < map.size(); i++) {
                queryBuilder.append("UPDATE NM_PROJECTION_SELECTION SET FIELD_NAME = '");
                queryBuilder.append(obj[i]).append("',").append("FIELD_VALUES = '").append(map.get(obj[i])).append("'");
                queryBuilder.append(" WHERE PROJECTION_MASTER_SID = '").append(projectionID).append(" ' AND SCREEN_NAME = '").append(screenName).append("' AND FIELD_NAME ='").append(obj[i]).append("'\n");
            }
        }
        commonDao.executeBulkUpdateQuery(queryBuilder.toString(), null, null);
    }

    public List getPriceGroupType(int projectionId, List<String> discountName, String userId, String sessionId) {
        try {
            String selectedDiscounts = StringUtils.EMPTY;
            selectedDiscounts = CommonUtils.CollectionToString(discountName, true);
            String customSql = "select Distinct DM.RS_MODEL_SID,DM.PRICE_GROUP_TYPE from ST_NM_DISCOUNT_PROJ_MASTER DM, RS_MODEL RS,PROJECTION_DETAILS D where DM.PROJECTION_DETAILS_SID = D.PROJECTION_DETAILS_SID AND DM.PRICE_GROUP_TYPE IS NOT NULL "
                    + "and D.PROJECTION_MASTER_SID = " + projectionId
                    + "and DM.RS_MODEL_SID=RS.RS_MODEL_SID "
                    + "and RS.RS_NAME in (" + selectedDiscounts + ") AND DM.USER_ID="+userId+" AND DM.SESSION_ID ='"+sessionId+"'";
            List<Object[]> list = (List<Object[]>) commonDao.executeSelectQuery(customSql, null, null);
            return list;
        } catch (Exception e) {
            LOGGER.error(e.getMessage() + " at CommonQueryUtils -> getPriceGroupType");
            return null;
        }
    }
    
    /**
     * Used to get the marketType helperTable sid using the projection Id
     * Used to pass marketType value for Supplemental Procedure Call.
     * 
     * @param projectionId
     * @return marketType Sid 
     */
    public static String getMarketType(int projectionId) {
        try {
            List list = new ArrayList();
            StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
            queryString.append("select RELATIONSHIP_LEVEL_VALUES,LEVEL_NO from RELATIONSHIP_LEVEL_DEFINITION \n"
                    + "where RELATIONSHIP_LEVEL_SID in ( select RELATIONSHIP_LEVEL_SID\n"
                    + "from PROJECTION_CUST_HIERARCHY where PROJECTION_MASTER_SID= ");
            if (projectionId != 0) {
                queryString.append(StringUtils.EMPTY + projectionId);
                queryString.append(" ) and LEVEL_NAME='Market TYPE'");
            }
            list = (List) commonDao.executeSelectQuery(queryString.toString(), null, null);
            String marketType = StringUtils.EMPTY;
            if (list.size() > 0) {
                Object[] objects = (Object[]) list.get(0);
                if (String.valueOf(objects[0]) != null && !StringUtils.EMPTY.equals(String.valueOf(objects[0]))) {
                    marketType = String.valueOf(objects[0]);
                }
            }
            return marketType;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }
}
