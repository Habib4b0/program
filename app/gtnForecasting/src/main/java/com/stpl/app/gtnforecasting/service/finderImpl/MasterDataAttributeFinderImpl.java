/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.service.finderImpl;

import com.stpl.app.service.HelperTableLocalServiceUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Abishek.Ram
 */
public class MasterDataAttributeFinderImpl {
private static final Logger LOGGER = LoggerFactory.getLogger(MasterDataAttributeFinderImpl.class);
    public List getTotalLives(Object[] inputs) {
        List list = new ArrayList();
        String coundition = (String) inputs[1];
        StringBuilder queryBuilder = new StringBuilder(StringUtils.EMPTY);
        try {
            if (coundition.equals("TotalLives")) {
                queryBuilder.append("  SELECT COLUMN_3 as col3  ");
            } else {
                queryBuilder.append("  SELECT MASTER_ID,COLUMN_1 as col1,COLUMN_2 as col2,COLUMN_3 as col3   ");
            }
            queryBuilder.append(" FROM MASTER_DATA_ATTRIBUTE M JOIN COMPANY_MASTER C ON C.COMPANY_ID=M.MASTER_ID  ");
            queryBuilder.append(" JOIN CCP_DETAILS CCP  ON C.COMPANY_MASTER_SID=CCP.COMPANY_MASTER_SID JOIN PROJECTION_DETAILS P ON CCP.CCP_DETAILS_SID=P.CCP_DETAILS_SID  ");
            queryBuilder.append(" where M.MASTER_TYPE='COMPANY_MASTER' AND M.MASTER_ATTRIBUTE LIKE'%COV_LIVES%' and P.PROJECTION_MASTER_SID=" ).append( inputs[0] ).append( "   ");

            list = HelperTableLocalServiceUtil.executeSelectQuery(queryBuilder.toString());

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            LOGGER.error(queryBuilder.toString());
        } 

        return list;
    }
}
