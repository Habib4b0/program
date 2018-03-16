/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.queryUtils;

import com.stpl.app.gtnforecasting.dao.CommonDAO;
import com.stpl.app.gtnforecasting.dao.impl.CommonDAOImpl;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.ifs.util.QueryUtil;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Mohamed.hameed
 */
public class CommonQueryUtils {

    private static final CommonDAO commonDao = new CommonDAOImpl();

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonQueryUtils.class);

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
            queryBuilder.append("INSERT INTO NM_PROJECTION_SELECTION (PROJECTION_MASTER_SID,SCREEN_NAME,FIELD_NAME,FIELD_VALUES)  ");
            for (int i = 0; i < map.size(); i++) {
                queryBuilder.append("SELECT '").append(projectionID).append("','").append(screenName).append("','");
                queryBuilder.append(obj[i]).append("','").append(map.get(obj[i])).append("'\n");
                if (i != map.size() - 1) {
                    queryBuilder.append("\n UNION ALL \n ");
                }
            }
        } else {
            for (int i = 0; i < map.size(); i++) {
                queryBuilder.append("UPDATE NM_PROJECTION_SELECTION SET FIELD_NAME = '");
                queryBuilder.append(obj[i]).append("',").append("FIELD_VALUES = '").append(map.get(obj[i])).append('\'');
                queryBuilder.append(" WHERE PROJECTION_MASTER_SID = '").append(projectionID).append(" ' AND SCREEN_NAME = '").append(screenName).append("' AND FIELD_NAME ='").append(obj[i]).append("'\n");
            }
        }
        if(screenName.equalsIgnoreCase("Discount Projection")){
        queryBuilder.append("Delete NM_PROJECTION_SELECTION\n"
                ).append( "WHERE PROJECTION_MASTER_SID=" ).append( projectionID);
        queryBuilder.append("\n   AND SCREEN_NAME='Discount Projection' \n"
                ).append( "AND FIELD_NAME='SelectedDiscountsSids';\n"
                ).append( "INSERT INTO NM_PROJECTION_SELECTION(PROJECTION_MASTER_SID,SCREEN_NAME,FIELD_NAME,FIELD_VALUES)\n"
                ).append( "VALUES(" ).append( projectionID);
        queryBuilder.append(" ,'Discount Projection','SelectedDiscountsSids','");
        queryBuilder.append(map.get("SelectedDiscountsSids").toString() ).append( "');");
        }
        commonDao.executeBulkUpdateQuery(queryBuilder.toString(), null, null);
    }

    public List getPriceGroupType(List<String> discountName,SessionDTO session) {
        try {
            String selectedDiscounts;
            selectedDiscounts = CommonUtils.CollectionToString(discountName, true);
            String customSql = "select Distinct DM.RS_CONTRACT_SID,DM.PRICE_GROUP_TYPE from ST_NM_DISCOUNT_PROJ_MASTER DM, RS_CONTRACT RS,PROJECTION_DETAILS D where  DM.PRICE_GROUP_TYPE IS NOT NULL "
                    + "and D.PROJECTION_MASTER_SID = " + session.getProjectionId()
                    + "and DM.RS_CONTRACT_SID=RS.RS_CONTRACT_SID "
                    + "and RS.RS_NAME in (" + selectedDiscounts + ")";
            List<Object[]> list = (List<Object[]>) commonDao.executeSelectQuery(QueryUtil.replaceTableNames(customSql, session.getCurrentTableNames()), null, null);
            return list;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return Collections.emptyList();
        }
    }
    
            }
