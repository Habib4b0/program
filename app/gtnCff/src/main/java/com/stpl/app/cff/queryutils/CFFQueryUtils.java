
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.queryutils;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.cff.dao.CFFDAO;
import com.stpl.app.cff.dao.impl.CFFDAOImpl;
import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.util.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.QueryUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Manasa
 */
public class CFFQueryUtils {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CFFQueryUtils.class);
    private static final CFFDAO DAO = CFFDAOImpl.getInstance();

    /**
     * Loads the latest approved CCP
     *
     * @return
     */
    public List getLatestCCP(int projectionId) {
        LOGGER.debug("Inside  getLatestCCP method");
        String query = SQlUtil.getQuery("getLatestCCPProjection");
        query = query.replace("?", String.valueOf(projectionId));
        try {
            return HelperTableLocalServiceUtil.executeSelectQuery(query);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * Loads the latest approved CCP
     *
     * @return
     */
    public List getCffDeatils(int cffSid) {
        LOGGER.debug("Inside getLatestCCP method");
        String sql = SQlUtil.getQuery("getCffDetails");
        sql += "AND CFF_MASTER_SID=" + cffSid;
        try {
            return (List) DAO.executeSelectQuery(sql);
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
            return Collections.emptyList();
        }
    }
    public static List<Integer> getApprovedDetails(int projectionId) {
        List<Integer> approvedDetailsSids = new ArrayList();
        try {
            String approveQuery = SQlUtil.getQuery("getApprovedProjMasterSids");
            approveQuery = approveQuery.replace("@PROJECTION_MASTER_SID", "" + Integer.toString(projectionId));
            List<Object[]> list = (List<Object[]>) DAO.executeSelectQuery(approveQuery);
            for (Object[] approveList : list) {
                approvedDetailsSids.add(Integer.valueOf(StringUtils.EMPTY + approveList[0]));
                approvedDetailsSids.add(Integer.valueOf(StringUtils.EMPTY + approveList[1]));
                approvedDetailsSids.add(Integer.valueOf(StringUtils.EMPTY + approveList[NumericConstants.TWO]));
                approvedDetailsSids.add(Integer.valueOf(StringUtils.EMPTY + approveList[NumericConstants.THREE]));
                approvedDetailsSids.add(Integer.valueOf(StringUtils.EMPTY + approveList[NumericConstants.FOUR]));
                approvedDetailsSids.add(Integer.valueOf(StringUtils.EMPTY + approveList[NumericConstants.FIVE]));
            }
            return approvedDetailsSids;
        } catch (PortalException | SystemException | NumberFormatException ex) {
            LOGGER.error(ex.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * Loads the latest approved CCP
     *
     * @param projectionId
     * @param sessionDTO
     * @param cffDTO
     * @return
     *
     * CD.PROJECTION_MASTER_SID = 853 and CM.CFF_MASTER_SID NOT IN (64) ORDER BY
     * CREATED_DATE DESC
     */
    public List getPriorName(int projectionId, SessionDTO sessionDTO) {
        LOGGER.debug("Inside getPriorName method");
        String sql = SQlUtil.getQuery("getPriorName");
        try {
            if (sessionDTO.getProjectionId() != 0) {
                sql += " CD.PROJECTION_MASTER_SID = " + projectionId + " AND CM.CFF_MASTER_SID NOT IN( " + sessionDTO.getProjectionId() + " )";
            } else {
                sql += " CD.PROJECTION_MASTER_SID = " + projectionId + " AND CM.CFF_MASTER_SID NOT IN (0)";
            }
            sql += " ORDER BY CM.CREATED_DATE DESC";
            return (List) DAO.executeSelectQuery(sql);
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * To create temp tables dynamically. It will return the tables created with
     * the user id and session id
     *
     * @param session
     */
    public static void createTempTables(SessionDTO session) {
        List<Object> createdTablesList = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.buildDynamicTempTableCreationQuery(session.getScreenName(), session.getProjectionId(), session.getUserId(), session.getSessionId()));
        for (int i = 0; i < createdTablesList.size(); i++) {
            Object[] ob = (Object[]) createdTablesList.get(i);
            session.addCurrentTableNames(ob[0].toString(), ob[1].toString());
}
    }
    public static Boolean updateDataFromMap(final Map<String, String> input, final String queryName) {
        StringBuilder queryString = new StringBuilder(SQlUtil.getQuery(queryName));
        if (input != null) {
            for (Map.Entry<String, String> entry : input.entrySet()) {
                final String string = entry.getKey();
                final String string1 = entry.getValue();
                while (queryString.toString().contains(string)) {
                    queryString.replace(queryString.indexOf(string), queryString.indexOf(string) + string.length(), String.valueOf(string1));
                }
            }
        }
        int count = HelperTableLocalServiceUtil.executeUpdateQueryCount(queryString.toString());
        return count > 0 ? Boolean.TRUE : Boolean.FALSE;
    }
}
