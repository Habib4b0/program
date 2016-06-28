
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
import com.stpl.portal.kernel.exception.PortalException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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
        LOGGER.info("getCCPCombinationForDisplay method started");
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
            LOGGER.info("getCCPCombinationForDisplay method ended");
            return null;
        }
    }

    /**
     * Loads the latest approved CCP
     *
     * @return
     */
    public List getLatestCCP(int projectionId) {
        LOGGER.info("Inside getLatestCCP method");
        String query = SQlUtil.getQuery("getLatestCCPProjection");
        query = query.replace("?", String.valueOf(projectionId));
        try {
            List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
            return list;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }

    /**
     * Loads the latest approved CCP
     *
     * @return
     */
    public List getApprovedCFF() {
        LOGGER.info("Inside getLatestCCP method");
        String sql = DAO.getQuery("getLastApprovedCFF");
        try {
            return (List) DAO.executeSelectQuery(sql);
        } catch (PortalException ex) {
            LOGGER.error(ex);
            return null;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }

    /**
     * Loads the latest approved CCP
     *
     * @return
     */
    public List getCffDeatils(int cffSid) {
        LOGGER.info("Inside getLatestCCP method");
        String sql = DAO.getQuery("getCffDetails");
        sql += "AND CFF_MASTER_SID=" + cffSid;
        try {
            return (List) DAO.executeSelectQuery(sql);
        } catch (PortalException ex) {
            LOGGER.error(ex);
            return null;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }

    /**
     * Save CCP combination
     *
     * @param cffDTO
     * @return
     */
    public List getCCPCombinationForSave(CFFDTO cffDTO, String ccps) {
        try {
//            String sql = "";
//            sql = DAO.getQuery("saveCffResults");
            List list = new ArrayList();
            list.add(ccps);
            String query = CommonQueryUtils.getAppQuery(list, "saveCffResults");
//            LOGGER.info("--------sql getCCPCombinationForSave----------------->>>>>>" + query);
            return (List) DAO.executeSelectQuery(query);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    public List getCffSearchResults(List parameterslist, boolean isCount, String dbColumnName, boolean sortOrder) throws Exception {
        String sql = "";
        if (isCount) {
            sql = DAO.getQuery("getCffSearchCount");
        } else {
            sql = DAO.getQuery("getCffSearchResults");
        }
        return (List) DAO.executeSelectQuery(sql);
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
        LOGGER.info("Inside getPriorName method");
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
            return null;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }
}
