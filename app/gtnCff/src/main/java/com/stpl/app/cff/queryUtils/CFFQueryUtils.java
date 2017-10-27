
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.queryUtils;

import com.stpl.app.cff.dao.CFFDAO;
import com.stpl.app.cff.dao.impl.CFFDAOImpl;
import com.stpl.app.cff.dto.CFFDTO;
import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.util.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.QueryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Manasa
 */
public class CFFQueryUtils {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LogManager.getLogger(CFFQueryUtils.class);
    private static final CFFDAO DAO = CFFDAOImpl.getInstance();
    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    /**
     * Gets the CCP combination
     *
     * @param cffDTO
     * @return
     */
    public List getCCPCombinationForDisplay(CFFDTO cffDTO) {
        LOGGER.debug("getCCPCombinationForDisplay method started");
        try {
            String sql = "";

            if (cffDTO.getProjectionIds() != null) {
                sql = DAO.getQuery("getCffDetailsOnEditPart1");
                sql += " AND CFFD.CFF_MASTER_SID != " + cffDTO.getCffMasterId() + "  AND CFFD.CFF_MASTER_SID < " + cffDTO.getCffMasterId() + ") ";
                sql += DAO.getQuery("getCffDetailsOnEditPart2");
                sql += " AND CFFM.CFF_MASTER_SID != " + cffDTO.getCffMasterId() + "  AND CFFM.CFF_MASTER_SID < " + cffDTO.getCffMasterId() + ") ";
                sql += DAO.getQuery("getCffDetailsOnEditPart3");
                sql += " AND T.PROJECTION_MASTER_SID IN (" + cffDTO.getProjectionIds() + ")";
            } else {
                sql = DAO.getQuery("getCffResultsData");
            }

            return (List) DAO.executeSelectQuery(sql);
        } catch (Exception e) {
            LOGGER.error(e);
            LOGGER.debug("getCCPCombinationForDisplay method ended");
            return Collections.emptyList();
        }
    }

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
            List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
            return list;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return Collections.emptyList();
        }
    }

    /**
     * Loads the latest approved CCP
     *
     * @return
     */
    public List getApprovedCFF() {
        LOGGER.debug("Inside getLatestCCP method");
        String sql = DAO.getQuery("getLastApprovedCFF");
        try {
            return (List) DAO.executeSelectQuery(sql);
        } catch (PortalException ex) {
            LOGGER.error(ex);
            return Collections.emptyList();
        } catch (Exception ex) {
            LOGGER.error(ex);
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
        String sql = DAO.getQuery("getCffDetails");
        sql += "AND CFF_MASTER_SID=" + cffSid;
        try {
            return (List) DAO.executeSelectQuery(sql);
        } catch (PortalException ex) {
            LOGGER.error(ex);
            return Collections.emptyList();
        } catch (Exception ex) {
            LOGGER.error(ex);
            return Collections.emptyList();
        }
    }
    public static List<Integer> getApprovedDetails(int projectionId) {
        List<Integer> approvedDetailsSids = new ArrayList();
        try {
            String approveQuery = SQlUtil.getQuery("getApprovedProjMasterSids");
            approveQuery = approveQuery.replace("@PROJECTION_MASTER_SID", "" + projectionId);
            List<Object[]> list = (List<Object[]>) DAO.executeSelectQuery(approveQuery);
            for (Object[] approveList : list) {
                approvedDetailsSids.add(Integer.valueOf(StringUtils.EMPTY + approveList[0]));
                approvedDetailsSids.add(Integer.valueOf(StringUtils.EMPTY + approveList[1]));
                approvedDetailsSids.add(Integer.valueOf(StringUtils.EMPTY + approveList[NumericConstants.TWO]));
                approvedDetailsSids.add(Integer.valueOf(StringUtils.EMPTY + approveList[NumericConstants.THREE]));
                approvedDetailsSids.add(Integer.valueOf(StringUtils.EMPTY + approveList[NumericConstants.FOUR]));
            }
            return approvedDetailsSids;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return Collections.EMPTY_LIST;
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
        String sql = DAO.getQuery("getPriorName");
        try {
            if (sessionDTO.getProjectionId() != 0) {
                sql += " CD.PROJECTION_MASTER_SID = " + projectionId + " AND CM.CFF_MASTER_SID NOT IN( " + sessionDTO.getProjectionId() + " )";
            } else {
                sql += " CD.PROJECTION_MASTER_SID = " + projectionId + " AND CM.CFF_MASTER_SID NOT IN (0)";
            }
            sql += " ORDER BY CM.CREATED_DATE DESC";
            return (List) DAO.executeSelectQuery(sql);
        } catch (PortalException ex) {
            LOGGER.error(ex);
            return Collections.emptyList();
        } catch (Exception ex) {
            LOGGER.error(ex);
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
        int count = (Integer) HelperTableLocalServiceUtil.executeUpdateQueryCount(queryString.toString());
        return count > 0 ? Boolean.TRUE : Boolean.FALSE;
    }
}
