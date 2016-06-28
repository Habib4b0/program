/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.discountprojectionresults.utils;

import com.stpl.app.galforecasting.dao.SalesProjectionDAO;
import com.stpl.app.galforecasting.dao.impl.SalesProjectionDAOImpl;
import com.stpl.app.galforecasting.discountprojectionresults.dto.DiscountProjectionResultsDTO;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.utils.Constants.FrequencyConstants.ANNUALLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUALLY;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author gopinath
 */
public class DPRQueryUtils {

    private static final Logger LOGGER = Logger.getLogger(DPRQueryUtils.class);

    public List getSumNMPivotValue(int projectionId, int userId, int sessionId, String freq, ProjectionSelectionDTO projSelDTO) {

        try {
            List list = new ArrayList();
            String frequency = StringUtils.EMPTY;
            if (freq.equals(Constant.QUARTERLY)) {
                frequency = "QUARTER";
            }
            if (freq.equals(ANNUALLY.getConstant())) {
                frequency = "YEAR";
            }
            if (freq.equals(SEMI_ANNUALLY.getConstant())) {
                frequency = "SEMI_ANNUAL";
            }
            if (freq.equals(MONTHLY.getConstant())) {
                frequency = "MONTH";
            }
            String str = "DECLARE @PROJECTION_MASTER_SID INT=" + projectionId + StringUtils.EMPTY
                    + "DECLARE @USER_ID INT=" + userId + StringUtils.EMPTY
                    + "DECLARE @SESSION_ID INT=" + sessionId + StringUtils.EMPTY
                    + "DECLARE @THERAPEUTIC_CLASS INT\n"
                    + "DECLARE @BRAND INT\n"
                    + "DECLARE @INPUT_INFO TABLE\n"
                    + "  (\n"
                    + "     PROJECTION_MASTER_SID  INT,\n"
                    + "     PROJECTION_DETAILS_SID INT,\n"
                    + "     ITEM_MASTER_SID        INT,\n"
                    + "     COMPANY_MASTER_SID     INT,\n"
                    + "     BRAND_MASTER_SID       INT,\n"
                    + "     BRAND_NAME             VARCHAR(100),\n"
                    + "     THERAPEUTIC_CLASS      INT,\n"
                    + "     COMPANY_CATEGORY       INT\n"
                    + "  )\n"
                    + " \n"
                    + "INSERT INTO @INPUT_INFO\n"
                    + "SELECT PROJECTION_MASTER_SID,\n"
                    + "       PROJECTION_DETAILS_SID,\n"
                    + "       C.ITEM_MASTER_SID,\n"
                    + "       CM.COMPANY_MASTER_SID,\n"
                    + "       IM.BRAND_MASTER_SID,\n"
                    + "       BRAND_NAME,\n"
                    + "       THERAPEUTIC_CLASS,\n"
                    + "       COMPANY_CATEGORY\n"
                    + "FROM   PROJECTION_DETAILS PD\n"
                    + "       INNER JOIN CCP_DETAILS C\n"
                    + "               ON C.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                    + "       INNER JOIN ITEM_MASTER IM\n"
                    + "               ON IM.ITEM_MASTER_SID = C.ITEM_MASTER_SID\n"
                    + "       INNER JOIN BRAND_MASTER B\n"
                    + "               ON B.BRAND_MASTER_SID = IM.BRAND_MASTER_SID\n"
                    + "       INNER JOIN COMPANY_MASTER CM\n"
                    + "               ON CM.COMPANY_MASTER_SID = C.COMPANY_MASTER_SID\n"
                    + "WHERE  PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID\n"
                    + " \n"
                    + "SELECT YEARS,\n"
                    + "       PERIODS,\n"
                    + "       Sum(PROJECTION_SALES)PROJECTION_SALES,\n"
                    + "       TYPE,\n"
                    + "       Sum(PROJECTION_RATE) PROJECTION_RATE,\n"
                    + "       Sum(PROJECTION_RPU)  PROJECTION_RPU\n"
                    + "FROM   (SELECT PER.year                                                                   AS YEARS,\n"
                    + "               PER." + frequency + "                                                                AS PERIODS,\n"
                    + "               Sum(B1.PROJECTION_SALES)                                                   PROJECTION_SALES,\n"
                    + "               'PROJECTION'                                                               AS TYPE,\n"
                    + "               ( COALESCE(Sum(B1.PROJECTION_SALES) / NULLIF(Sum(A1.SALES), 0), 0) * 100 ) AS PROJECTION_RATE,\n"
                    + "               ( COALESCE(Sum(B1.PROJECTION_SALES) / NULLIF(Sum(A1.UNITS), 0), 0) )       AS PROJECTION_RPU\n"
                    + "        FROM   (SELECT BRAND_NAME,\n"
                    + "                       Sum(SALES) AS SALES,\n"
                    + "                       Sum(UNITS) AS UNITS,\n"
                    + "                       PERIOD_SID\n"
                    + "                FROM   (SELECT Sum(B.PROJECTION_SALES) AS SALES,\n"
                    + "                               Sum(B.PROJECTION_UNITS) AS UNITS,\n"
                    + "                               PER.PERIOD_SID,\n"
                    + "                               I.BRAND_NAME,\n"
                    + "                               I.BRAND_MASTER_SID,\n"
                    + "                               I.THERAPEUTIC_CLASS\n"
                    + "                        FROM   dbo.ST_M_SALES_PROJECTION_MASTER A\n"
                    + "                               JOIN dbo.ST_M_SALES_PROJECTION B\n"
                    + "                                 ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID\n"
                    + "                                    AND A.USER_ID = B.USER_ID\n"
                    + "                                    AND A.SESSION_ID = B.SESSION_ID\n"
                    + "                               JOIN @INPUT_INFO I\n"
                    + "                                 ON I.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID\n"
                    + "                               JOIN PERIOD PER\n"
                    + "                                 ON PER.PERIOD_SID = B.PERIOD_SID\n"
                    + "                        WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID\n"
                    + "                               AND A.USER_ID = @USER_ID\n"
                    + "                               AND A.SESSION_ID = @SESSION_ID\n"
                    + "                        GROUP  BY PER.PERIOD_SID,\n"
                    + "                                  I.BRAND_NAME,\n"
                    + "                                  I.BRAND_MASTER_SID,\n"
                    + "                                  I.THERAPEUTIC_CLASS)A\n"
                    + "                WHERE  ( A.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS\n"
                    + "                         AND @THERAPEUTIC_CLASS IS NOT NULL\n"
                    + "                         AND @BRAND IS NULL )\n"
                    + "                        OR ( A.BRAND_MASTER_SID = @BRAND\n"
                    + "                             AND @BRAND IS NOT NULL\n"
                    + "                             AND @THERAPEUTIC_CLASS IS NULL )\n"
                    + "                        OR ( A.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS\n"
                    + "                             AND A.BRAND_MASTER_SID = @BRAND )\n"
                    + "                        OR ( @THERAPEUTIC_CLASS IS NULL\n"
                    + "                             AND @BRAND IS NULL )\n"
                    + "                GROUP  BY A.BRAND_NAME,\n"
                    + "                          A.PERIOD_SID) A1\n"
                    + "               LEFT JOIN (SELECT BRAND_NAME,\n"
                    + "                                 Sum(PROJECTION_SALES) AS PROJECTION_SALES,\n"
                    + "                                 PERIOD_SID\n"
                    + "                          FROM   (SELECT BRAND_NAME,\n"
                    + "                                         Sum(PROJECTION_SALES) AS PROJECTION_SALES,\n"
                    + "                                         PERIOD_SID,\n"
                    + "                                         I.BRAND_MASTER_SID,\n"
                    + "                                         THERAPEUTIC_CLASS\n"
                    + "                                  FROM   @INPUT_INFO I\n"
                    + "                                         CROSS APPLY (SELECT TOP 1 PM.PROJECTION_MASTER_SID\n"
                    + "                                                      FROM   PROJECTION_MASTER PM\n"
                    + "                                                             JOIN WORKFLOW_MASTER WM\n"
                    + "                                                               ON PM.PROJECTION_MASTER_SID = WM.PROJECTION_MASTER_SID\n"
                    + "                                                                  AND WM.WORKFLOW_STATUS_ID = (SELECT HELPER_TABLE_SID\n"
                    + "                                                                                               FROM   HELPER_TABLE\n"
                    + "                                                                                               WHERE  LIST_NAME = 'WorkFlowStatus'\n"
                    + "                                                                                                      AND DESCRIPTION = 'Approved')\n"
                    + "                                                             JOIN @INPUT_INFO I\n"
                    + "                                                              ON I.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID\n"
                    + "                                                      ORDER  BY WM.MODIFIED_DATE DESC) cs\n"
                    + "                                         JOIN [dbo].[NM_DISCOUNT_PROJECTION] C\n"
                    + "                                           ON c.PROJECTION_DETAILS_SID = I.PROJECTION_DETAILS_SID\n"
                    + "                                         JOIN HELPER_TABLE H\n"
                    + "                                           ON I.COMPANY_CATEGORY = H.HELPER_TABLE_SID\n"
                    + "                                  WHERE  I.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID\n"
                    + "                                         AND H.DESCRIPTION IN ( 'MM Medi', 'MCG Plan' )\n"
                    + "                                  GROUP  BY BRAND_NAME,\n"
                    + "                                            PERIOD_SID,\n"
                    + "                                            I.BRAND_MASTER_SID,\n"
                    + "                                            THERAPEUTIC_CLASS)B\n"
                    + "                          WHERE  ( B.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS\n"
                    + "                                   AND @THERAPEUTIC_CLASS IS NOT NULL\n"
                    + "                                   AND @BRAND IS NULL )\n"
                    + "                                  OR ( B.BRAND_MASTER_SID = @BRAND\n"
                    + "                                       AND @BRAND IS NOT NULL\n"
                    + "                                       AND @THERAPEUTIC_CLASS IS NULL )\n"
                    + "                                  OR ( B.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS\n"
                    + "                                       AND B.BRAND_MASTER_SID = @BRAND )\n"
                    + "                                  OR ( @THERAPEUTIC_CLASS IS NULL\n"
                    + "                                       AND @BRAND IS NULL )\n"
                    + "                          GROUP  BY B.BRAND_NAME,\n"
                    + "                                    B.PERIOD_SID)B1\n"
                    + "                      ON A1.BRAND_NAME = B1.BRAND_NAME\n"
                    + "                         AND A1.PERIOD_SID = B1.PERIOD_SID\n"
                    + "               JOIN period AS per\n"
                    + "                 ON per.PERIOD_SID = A1.PERIOD_SID\n"
                    + "        GROUP  BY A1.BRAND_NAME,\n"
                    + "                  per.year,\n"
                    + "                  per." + frequency + "\n"
                    + "        UNION ALL\n"
                    + "        SELECT per.year                                                                   AS YEARS,\n"
                    + "               per." + frequency + "                                                                AS PERIODS,\n"
                    + "               Sum(B1.PROJECTION_SALES)                                                   AS PROJECTION_SALES,\n"
                    + "               'ACTUAL'                                                                   AS TYPE,\n"
                    + "               ( COALESCE(Sum(B1.PROJECTION_SALES) / NULLIF(Sum(A1.SALES), 0), 0) * 100 ) AS PROJECTION_RATE,\n"
                    + "               ( COALESCE(Sum(B1.PROJECTION_SALES) / NULLIF(Sum(A1.UNITS), 0), 0) )       AS PROJECTION_RPU\n"
                    + "        FROM   (SELECT BRAND_NAME,\n"
                    + "                       Sum(SALES) AS SALES,\n"
                    + "                       Sum(UNITS) AS UNITS,\n"
                    + "                       PERIOD_SID\n"
                    + "                FROM   (SELECT Sum(B.ACTUAL_SALES) AS SALES,\n"
                    + "                               Sum(B.ACTUAL_UNITS) AS UNITS,\n"
                    + "                               PER.PERIOD_SID,\n"
                    + "                               I.BRAND_NAME,\n"
                    + "                               I.BRAND_MASTER_SID,\n"
                    + "                               I.THERAPEUTIC_CLASS\n"
                    + "                        FROM   dbo.ST_M_SALES_PROJECTION_MASTER A\n"
                    + "                               JOIN dbo.ST_M_ACTUAL_SALES B\n"
                    + "                                 ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID\n"
                    + "                                    AND A.USER_ID = B.USER_ID\n"
                    + "                                    AND A.SESSION_ID = B.SESSION_ID\n"
                    + "                               JOIN @INPUT_INFO I\n"
                    + "                                 ON I.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID\n"
                    + "                               JOIN PERIOD PER\n"
                    + "                                 ON PER.PERIOD_SID = B.PERIOD_SID\n"
                    + "                        WHERE  I.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID\n"
                    + "                               AND A.USER_ID = @USER_ID\n"
                    + "                               AND A.SESSION_ID = @SESSION_ID\n"
                    + "                        GROUP  BY PER.PERIOD_SID,\n"
                    + "                                  I.BRAND_NAME,\n"
                    + "                                  I.BRAND_MASTER_SID,\n"
                    + "                                  I.THERAPEUTIC_CLASS)A\n"
                    + "                WHERE  ( A.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS\n"
                    + "                         AND @THERAPEUTIC_CLASS IS NOT NULL\n"
                    + "                         AND @BRAND IS NULL )\n"
                    + "                        OR ( A.BRAND_MASTER_SID = @BRAND\n"
                    + "                             AND @BRAND IS NOT NULL\n"
                    + "                             AND @THERAPEUTIC_CLASS IS NULL )\n"
                    + "                        OR ( A.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS\n"
                    + "                             AND A.BRAND_MASTER_SID = @BRAND )\n"
                    + "                        OR ( @THERAPEUTIC_CLASS IS NULL\n"
                    + "                             AND @BRAND IS NULL )\n"
                    + "                GROUP  BY A.BRAND_NAME,\n"
                    + "                          A.PERIOD_SID) A1\n"
                    + "               LEFT JOIN (SELECT BRAND_NAME,\n"
                    + "                                 Sum(PROJECTION_SALES) AS PROJECTION_SALES,\n"
                    + "                                 PERIOD_SID\n"
                    + "                          FROM   (SELECT BRAND_NAME,\n"
                    + "                                         Sum(ACTUAL_SALES) AS PROJECTION_SALES,\n"
                    + "                                         PERIOD_SID,\n"
                    + "                                         I.BRAND_MASTER_SID,\n"
                    + "                                         THERAPEUTIC_CLASS\n"
                    + "                                  FROM   @INPUT_INFO I\n"
                    + "                                         CROSS APPLY (SELECT TOP 1 PM.PROJECTION_MASTER_SID\n"
                    + "                                                      FROM   PROJECTION_MASTER PM\n"
                    + "                                                             JOIN WORKFLOW_MASTER WM\n"
                    + "                                                               ON PM.PROJECTION_MASTER_SID = WM.PROJECTION_MASTER_SID\n"
                    + "                                                                  AND WM.WORKFLOW_STATUS_ID = (SELECT HELPER_TABLE_SID\n"
                    + "                                                                                               FROM   HELPER_TABLE\n"
                    + "                                                                                               WHERE  LIST_NAME = 'WorkFlowStatus'\n"
                    + "                                                                                                      AND DESCRIPTION = 'Approved')\n"
                    + "                                                             JOIN @INPUT_INFO I\n"
                    + "                                                               ON I.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID\n"
                    + "                                                      ORDER  BY WM.MODIFIED_DATE DESC) cs\n"
                    + "                                         JOIN [dbo].[NM_ACTUAL_DISCOUNT] C\n"
                    + "                                           ON c.PROJECTION_DETAILS_SID = I.PROJECTION_DETAILS_SID\n"
                    + "                                         JOIN HELPER_TABLE H\n"
                    + "                                           ON I.COMPANY_CATEGORY = H.HELPER_TABLE_SID\n"
                    + "                                  WHERE  I.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID\n"
                    + "                                         AND H.DESCRIPTION IN ( 'MM Medi', 'MCG Plan' )\n"
                    + "                                  GROUP  BY BRAND_NAME,\n"
                    + "                                            PERIOD_SID,\n"
                    + "                                            I.BRAND_MASTER_SID,\n"
                    + "                                            THERAPEUTIC_CLASS)B\n"
                    + "                          WHERE  ( B.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS\n"
                    + "                                   AND @THERAPEUTIC_CLASS IS NOT NULL\n"
                    + "                                   AND @BRAND IS NULL )\n"
                    + "                                  OR ( B.BRAND_MASTER_SID = @BRAND\n"
                    + "                                       AND @BRAND IS NOT NULL\n"
                    + "                                       AND @THERAPEUTIC_CLASS IS NULL )\n"
                    + "                                  OR ( B.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS\n"
                    + "                                       AND B.BRAND_MASTER_SID = @BRAND )\n"
                    + "                                  OR ( @THERAPEUTIC_CLASS IS NULL\n"
                    + "                                       AND @BRAND IS NULL )\n"
                    + "                          GROUP  BY B.BRAND_NAME,\n"
                    + "                                    B.PERIOD_SID)B1\n"
                    + "                      ON A1.BRAND_NAME = B1.BRAND_NAME\n"
                    + "                         AND A1.PERIOD_SID = B1.PERIOD_SID\n"
                    + "               JOIN period AS per\n"
                    + "                 ON per.PERIOD_SID = A1.PERIOD_SID\n"
                    + "        GROUP  BY per.year,\n"
                    + "                  per." + frequency + ")a\n"
                    + "GROUP  BY a.YEARS,\n"
                    + "          a.periods,\n"
                    + "          TYPE\n"
                    + "ORDER  BY YEARS,\n"
                    + "          PERIODS";
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            list = (List) salesProjectionDAO.executeSelectQuery(str);
            return list;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;

        }
    }

    public List getNonMandateTotalValue(int projectionId, int userId, int sessionId, String freq, ProjectionSelectionDTO projSelDTO) {
        try {
            List list = new ArrayList();
            String frequency = StringUtils.EMPTY;
            if (freq.equals(Constant.QUARTERLY)) {
                frequency = "QUARTER";
            }
            if (freq.equals(ANNUALLY.getConstant())) {
                frequency = "YEAR";
            }
            if (freq.equals(SEMI_ANNUALLY.getConstant())) {
                frequency = "SEMI_ANNUAL";
            }
            if (freq.equals(MONTHLY.getConstant())) {
                frequency = "MONTH";
            }
            String str = "DECLARE @PROJECTION_MASTER_SID INT=" + projectionId + StringUtils.EMPTY
                    + "DECLARE @USER_ID INT=" + userId + StringUtils.EMPTY
                    + "DECLARE @SESSION_ID INT=" + sessionId + StringUtils.EMPTY
                    + "DECLARE @THERAPEUTIC_CLASS INT\n"
                    + "DECLARE @BRAND INT\n"
                    + "\n"
                    + " SELECT A1.BRAND_NAME                                                        AS BRAND_NAME,\n"
                    + "                     PER.year                                                             AS YEARS,\n"
                    + "                     PER." + frequency + "                                                          AS PERIODS,\n"
                    + "                     Sum(B1.PROJECTION_SALES)                                             PROJECTION_SALES,\n"
                    + "                     'PROJECTION'                                                         AS TYPE,\n"
                    + "                     ( COALESCE(Sum(B1.PROJECTION_SALES) / NULLIF(Sum(A1.SALES), 0), 0) ) AS PROJECTION_RATE,\n"
                    + "					 Sum(B1.PROJECTION_RPU) AS PROJECTION_RPU\n"
                    + "              FROM   (SELECT BRAND_NAME,\n"
                    + "                             Sum(SALES) AS SALES,\n"
                    + "                             PERIOD_SID\n"
                    + "                      FROM   (SELECT Sum(B.PROJECTION_SALES) AS SALES,\n"
                    + "                                     PER.PERIOD_SID,\n"
                    + "                                     BM.BRAND_NAME,\n"
                    + "                                     BM.BRAND_MASTER_SID,\n"
                    + "                                     IM.THERAPEUTIC_CLASS\n"
                    + "                              FROM   dbo.ST_M_SALES_PROJECTION_MASTER A\n"
                    + "                                     JOIN dbo.ST_M_SALES_PROJECTION B\n"
                    + "                                       ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID\n"
                    + "                                          AND A.USER_ID = B.USER_ID\n"
                    + "                                          AND A.SESSION_ID = B.SESSION_ID\n"
                    + "                                     JOIN dbo.PROJECTION_DETAILS P\n"
                    + "                                       ON P.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID\n"
                    + "                                     JOIN dbo.CCP_DETAILS C\n"
                    + "                                       ON C.CCP_DETAILS_SID = P.CCP_DETAILS_SID\n"
                    + "                                     JOIN dbo.ITEM_MASTER IM\n"
                    + "                                       ON IM.ITEM_MASTER_SID = C.ITEM_MASTER_SID\n"
                    + "                                     JOIN BRAND_MASTER BM\n"
                    + "                                       ON BM.BRAND_MASTER_SID = IM.BRAND_MASTER_SID\n"
                    + "                                     JOIN PERIOD PER\n"
                    + "                                       ON PER.PERIOD_SID = B.PERIOD_SID\n"
                    + "                              WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID\n"
                    + "                                     AND A.USER_ID = @USER_ID\n"
                    + "                                     AND A.SESSION_ID = @SESSION_ID\n"
                    + "                              GROUP  BY PER.PERIOD_SID,\n"
                    + "                                        BM.BRAND_NAME,\n"
                    + "                                        BM.BRAND_MASTER_SID,\n"
                    + "                                        IM.THERAPEUTIC_CLASS)A\n"
                    + "                      WHERE  ( A.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS\n"
                    + "                               AND @THERAPEUTIC_CLASS IS NOT NULL\n"
                    + "                               AND @BRAND IS NULL )\n"
                    + "                              OR ( A.BRAND_MASTER_SID = @BRAND\n"
                    + "                                   AND @BRAND IS NOT NULL\n"
                    + "                                   AND @THERAPEUTIC_CLASS IS NULL )\n"
                    + "                              OR ( A.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS\n"
                    + "                                   AND A.BRAND_MASTER_SID = @BRAND )\n"
                    + "                              OR ( @THERAPEUTIC_CLASS IS NULL\n"
                    + "                                   AND @BRAND IS NULL )\n"
                    + "                      GROUP  BY A.BRAND_NAME,\n"
                    + "                                A.PERIOD_SID) A1\n"
                    + "                     LEFT JOIN (SELECT BRAND_NAME,\n"
                    + "                                       Sum(PROJECTION_SALES) AS PROJECTION_SALES,\n"
                    + "                                       PERIOD_SID,\n"
                    + "									   Sum(PROJECTION_RPU) AS PROJECTION_RPU\n"
                    + "                                FROM   (SELECT BRAND_NAME,\n"
                    + "                                               Sum(PROJECTION_SALES) AS PROJECTION_SALES,\n"
                    + "                                               PERIOD_SID,\n"
                    + "                                               BM.BRAND_MASTER_SID,\n"
                    + "                                               THERAPEUTIC_CLASS,\n"
                    + "											   Sum(PROJECTION_RPU) AS PROJECTION_RPU\n"
                    + "                                        FROM   PROJECTION_DETAILS A\n"
                    + "                                               JOIN CCP_DETAILS B\n"
                    + "                                                 ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID\n"
                    + "                                               JOIN ITEM_MASTER IM\n"
                    + "                                                 ON IM.ITEM_MASTER_SID = B.ITEM_MASTER_SID\n"
                    + "                                               JOIN BRAND_MASTER BM\n"
                    + "                                                 ON BM.BRAND_MASTER_SID = IM.BRAND_MASTER_SID\n"
                    + "                                               CROSS APPLY (SELECT TOP 1 PM.PROJECTION_MASTER_SID\n"
                    + "                                                            FROM   PROJECTION_MASTER PM\n"
                    + "                                                                   JOIN WORKFLOW_MASTER WM\n"
                    + "                                                                     ON PM.PROJECTION_MASTER_SID = WM.PROJECTION_MASTER_SID\n"
                    + "                                                                        AND WM.WORKFLOW_STATUS_ID = (SELECT HELPER_TABLE_SID\n"
                    + "                                                                                                     FROM   HELPER_TABLE\n"
                    + "                                                                                                     WHERE  LIST_NAME = 'WorkFlowStatus'\n"
                    + "                                                                                                            AND DESCRIPTION = 'Approved')\n"
                    + "                                                                   JOIN PROJECTION_DETAILS PD\n"
                    + "                                                                     ON PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID\n"
                    + "                                                                   JOIN CCP_DETAILS CCP\n"
                    + "                                                                     ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                    + "                                                                        AND CCP.ITEM_MASTER_SID = b.ITEM_MASTER_SID\n"
                    + "                                                            ORDER  BY WM.MODIFIED_DATE DESC) cs\n"
                    + "                                               JOIN PROJECTION_DETAILS PROJ\n"
                    + "                                                 ON cs.projection_master_sid = proj.projection_master_sid\n"
                    + "                                               JOIN [dbo].[NM_DISCOUNT_PROJECTION] C\n"
                    + "                                                 ON c.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID\n"
                    + "                                               JOIN COMPANY_MASTER CM\n"
                    + "                                                 ON CM.COMPANY_MASTER_SID = B.COMPANY_MASTER_SID\n"
                    + "                                               JOIN HELPER_TABLE H\n"
                    + "                                                 ON CM.COMPANY_CATEGORY = H.HELPER_TABLE_SID\n"
                    + "                                        WHERE  A.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID\n"
                    + "                                               AND H.DESCRIPTION IN ( 'MM Medi', 'MCG Plan' )\n"
                    + "                                        GROUP  BY BRAND_NAME,\n"
                    + "                                                  PERIOD_SID,\n"
                    + "                                                  BM.BRAND_MASTER_SID,\n"
                    + "                                                  THERAPEUTIC_CLASS)B\n"
                    + "                                WHERE  ( B.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS\n"
                    + "                                         AND @THERAPEUTIC_CLASS IS NOT NULL\n"
                    + "                                         AND @BRAND IS NULL )\n"
                    + "                                        OR ( B.BRAND_MASTER_SID = @BRAND\n"
                    + "                                             AND @BRAND IS NOT NULL\n"
                    + "                                             AND @THERAPEUTIC_CLASS IS NULL )\n"
                    + "                                        OR ( B.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS\n"
                    + "                                             AND B.BRAND_MASTER_SID = @BRAND )\n"
                    + "                                        OR ( @THERAPEUTIC_CLASS IS NULL\n"
                    + "                                             AND @BRAND IS NULL )\n"
                    + "                                GROUP  BY B.BRAND_NAME,\n"
                    + "                                          B.PERIOD_SID)B1\n"
                    + "                            ON A1.BRAND_NAME = B1.BRAND_NAME\n"
                    + "                               AND A1.PERIOD_SID = B1.PERIOD_SID\n"
                    + "                     JOIN period AS per\n"
                    + "                       ON per.PERIOD_SID = A1.PERIOD_SID\n"
                    + "              GROUP  BY A1.BRAND_NAME,\n"
                    + "                        per.year,\n"
                    + "                        per." + frequency + "\n"
                    + "              UNION ALL\n"
                    + "              SELECT A1.BRAND_NAME                                                        AS BRAND_NAME,\n"
                    + "                     per.year                                                             AS YEARS,\n"
                    + "                     per." + frequency + "                                                          AS PERIODS,\n"
                    + "                     Sum(B1.PROJECTION_SALES)                                             AS PROJECTION_SALES,\n"
                    + "                     'ACTUAL'                                                             AS TYPE,\n"
                    + "                     ( COALESCE(Sum(B1.PROJECTION_SALES) / NULLIF(Sum(A1.SALES), 0), 0) ) AS PROJECTION_RATE,\n"
                    + "					 Sum(B1.PROJECTION_RPU) AS PROJECTION_RPU\n"
                    + "              FROM   (SELECT BRAND_NAME,\n"
                    + "                             Sum(SALES) AS SALES,\n"
                    + "                             PERIOD_SID\n"
                    + "                      FROM   (SELECT Sum(B.ACTUAL_SALES) AS SALES,\n"
                    + "                                     PER.PERIOD_SID,\n"
                    + "                                     BM.BRAND_NAME,\n"
                    + "                                     BM.BRAND_MASTER_SID,\n"
                    + "                                     IM.THERAPEUTIC_CLASS\n"
                    + "                              FROM   dbo.ST_M_SALES_PROJECTION_MASTER A\n"
                    + "                                     JOIN dbo.ST_M_ACTUAL_SALES B\n"
                    + "                                       ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID\n"
                    + "                                          AND A.USER_ID = B.USER_ID\n"
                    + "                                          AND A.SESSION_ID = B.SESSION_ID\n"
                    + "                                     JOIN dbo.PROJECTION_DETAILS P\n"
                    + "                                       ON P.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID\n"
                    + "                                     JOIN dbo.CCP_DETAILS C\n"
                    + "                                       ON C.CCP_DETAILS_SID = P.CCP_DETAILS_SID\n"
                    + "                                     JOIN dbo.ITEM_MASTER IM\n"
                    + "                                       ON IM.ITEM_MASTER_SID = C.ITEM_MASTER_SID\n"
                    + "                                     JOIN BRAND_MASTER BM\n"
                    + "                                       ON BM.BRAND_MASTER_SID = IM.BRAND_MASTER_SID\n"
                    + "                                     JOIN PERIOD PER\n"
                    + "                                       ON PER.PERIOD_SID = B.PERIOD_SID\n"
                    + "                              WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID\n"
                    + "                                     AND A.USER_ID = @USER_ID\n"
                    + "                                     AND A.SESSION_ID = @SESSION_ID\n"
                    + "                              GROUP  BY PER.PERIOD_SID,\n"
                    + "                                        BM.BRAND_NAME,\n"
                    + "                                        BM.BRAND_MASTER_SID,\n"
                    + "                                        IM.THERAPEUTIC_CLASS)A\n"
                    + "                      WHERE  ( A.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS\n"
                    + "                               AND @THERAPEUTIC_CLASS IS NOT NULL\n"
                    + "                               AND @BRAND IS NULL )\n"
                    + "                              OR ( A.BRAND_MASTER_SID = @BRAND\n"
                    + "                                   AND @BRAND IS NOT NULL\n"
                    + "                                   AND @THERAPEUTIC_CLASS IS NULL )\n"
                    + "                              OR ( A.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS\n"
                    + "                                   AND A.BRAND_MASTER_SID = @BRAND )\n"
                    + "                              OR ( @THERAPEUTIC_CLASS IS NULL\n"
                    + "                                   AND @BRAND IS NULL )\n"
                    + "                      GROUP  BY A.BRAND_NAME,\n"
                    + "                                A.PERIOD_SID) A1\n"
                    + "                     LEFT JOIN (SELECT BRAND_NAME,\n"
                    + "                                       Sum(PROJECTION_SALES) AS PROJECTION_SALES,\n"
                    + "                                       PERIOD_SID,\n"
                    + "									   Sum(PROJECTION_RPU) AS PROJECTION_RPU\n"
                    + "                                FROM   (SELECT BRAND_NAME,\n"
                    + "                                               Sum(ACTUAL_SALES) AS PROJECTION_SALES,\n"
                    + "                                               PERIOD_SID,\n"
                    + "                                               BM.BRAND_MASTER_SID,\n"
                    + "                                               THERAPEUTIC_CLASS,\n"
                    + "											   Sum(ACTUAL_RPU) AS PROJECTION_RPU\n"
                    + "                                        FROM   PROJECTION_DETAILS A\n"
                    + "                                               JOIN CCP_DETAILS B\n"
                    + "                                                 ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID\n"
                    + "                                               JOIN ITEM_MASTER IM\n"
                    + "                                                 ON IM.ITEM_MASTER_SID = B.ITEM_MASTER_SID\n"
                    + "                                               JOIN BRAND_MASTER BM\n"
                    + "                                                 ON BM.BRAND_MASTER_SID = IM.BRAND_MASTER_SID\n"
                    + "                                               CROSS APPLY (SELECT TOP 1 PM.PROJECTION_MASTER_SID\n"
                    + "                                                            FROM   PROJECTION_MASTER PM\n"
                    + "                                                                   JOIN WORKFLOW_MASTER WM\n"
                    + "                                                                     ON PM.PROJECTION_MASTER_SID = WM.PROJECTION_MASTER_SID\n"
                    + "                                                                        AND WM.WORKFLOW_STATUS_ID = (SELECT HELPER_TABLE_SID\n"
                    + "                                                                                                     FROM   HELPER_TABLE\n"
                    + "                                                                                                     WHERE  LIST_NAME = 'WorkFlowStatus'\n"
                    + "                                                                                                            AND DESCRIPTION = 'Approved')\n"
                    + "                                                                   JOIN PROJECTION_DETAILS PD\n"
                    + "                                                                     ON PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID\n"
                    + "                                                                   JOIN CCP_DETAILS CCP\n"
                    + "                                                                     ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                    + "                                                                        AND CCP.ITEM_MASTER_SID = b.ITEM_MASTER_SID\n"
                    + "                                                            ORDER  BY WM.MODIFIED_DATE DESC) cs\n"
                    + "                                               JOIN PROJECTION_DETAILS PROJ\n"
                    + "                                                 ON cs.projection_master_sid = proj.projection_master_sid\n"
                    + "                                               JOIN [dbo].[NM_ACTUAL_DISCOUNT] C\n"
                    + "                                                 ON c.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID\n"
                    + "                                               JOIN COMPANY_MASTER CM\n"
                    + "                                                 ON CM.COMPANY_MASTER_SID = B.COMPANY_MASTER_SID\n"
                    + "                                               JOIN HELPER_TABLE H\n"
                    + "                                                 ON CM.COMPANY_CATEGORY = H.HELPER_TABLE_SID\n"
                    + "                                        WHERE  A.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID\n"
                    + "                                               AND H.DESCRIPTION IN ( 'MM Medi', 'MCG Plan' )\n"
                    + "                                        GROUP  BY BRAND_NAME,\n"
                    + "                                                  PERIOD_SID,\n"
                    + "                                                  BM.BRAND_MASTER_SID,\n"
                    + "                                                  THERAPEUTIC_CLASS)B\n"
                    + "                                WHERE  ( B.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS\n"
                    + "                                         AND @THERAPEUTIC_CLASS IS NOT NULL\n"
                    + "                                         AND @BRAND IS NULL )\n"
                    + "                                        OR ( B.BRAND_MASTER_SID = @BRAND\n"
                    + "                                             AND @BRAND IS NOT NULL\n"
                    + "                                             AND @THERAPEUTIC_CLASS IS NULL )\n"
                    + "                                        OR ( B.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS\n"
                    + "                                             AND B.BRAND_MASTER_SID = @BRAND )\n"
                    + "                                        OR ( @THERAPEUTIC_CLASS IS NULL\n"
                    + "                                             AND @BRAND IS NULL )\n"
                    + "                                GROUP  BY B.BRAND_NAME,\n"
                    + "                                          B.PERIOD_SID)B1\n"
                    + "                            ON A1.BRAND_NAME = B1.BRAND_NAME\n"
                    + "                               AND A1.PERIOD_SID = B1.PERIOD_SID\n"
                    + "                     JOIN period AS per\n"
                    + "                       ON per.PERIOD_SID = A1.PERIOD_SID\n"
                    + "              GROUP  BY per.year,\n"
                    + "                        A1.BRAND_NAME,\n"
                    + "             \n"
                    + "             per." + frequency + "\n";
            if ("YEAR".equalsIgnoreCase(frequency)) {
                str += "ORDER     BY   A1.BRAND_NAME,per.year\n"
                        + "             ";
            } else {
                str += "ORDER     BY  A1.BRAND_NAME, per.year,\n"
                        + "             per." + frequency + "\n"
                        + "           ";
            }
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            list = (List) salesProjectionDAO.executeSelectQuery(str);
            return list;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;

        }

    }

    public List getNonMandatedBrand(int projectionId, int userId, int sessionId, String freq, ProjectionSelectionDTO projSelDTO) {
        try {
            List list = new ArrayList();
            String frequency = StringUtils.EMPTY;
            if (freq.equals(Constant.QUARTERLY)) {
                frequency = "QUARTER";
            }
            if (freq.equals(ANNUALLY.getConstant())) {
                frequency = "YEAR";
            }
            if (freq.equals(SEMI_ANNUALLY.getConstant())) {
                frequency = "SEMI_ANNUAL";
            }
            if (freq.equals(MONTHLY.getConstant())) {
                frequency = "MONTH";
            }
            String str = "DECLARE @PROJECTION_MASTER_SID INT=" + projectionId + StringUtils.EMPTY
                    + "DECLARE @USER_ID INT=" + userId + StringUtils.EMPTY
                    + "DECLARE @SESSION_ID INT=" + sessionId + StringUtils.EMPTY
                    + "DECLARE @THERAPEUTIC_CLASS INT\n"
                    + "DECLARE @BRAND INT\n"
                    + "\n"
                    + "DECLARE @INPUT_INFO TABLE\n"
                    + "  (\n"
                    + "     PROJECTION_MASTER_SID  INT,\n"
                    + "     PROJECTION_DETAILS_SID INT,\n"
                    + "     ITEM_MASTER_SID        INT,\n"
                    + "     COMPANY_MASTER_SID     INT,\n"
                    + "     BRAND_MASTER_SID       INT,\n"
                    + "     BRAND_NAME             VARCHAR(100),\n"
                    + "     THERAPEUTIC_CLASS      INT,\n"
                    + "     COMPANY_CATEGORY       INT\n"
                    + "  )\n"
                    + "\n"
                    + "INSERT INTO @INPUT_INFO\n"
                    + "SELECT PROJECTION_MASTER_SID,\n"
                    + "       PROJECTION_DETAILS_SID,\n"
                    + "       C.ITEM_MASTER_SID,\n"
                    + "       CM.COMPANY_MASTER_SID,\n"
                    + "       IM.BRAND_MASTER_SID,\n"
                    + "       BRAND_NAME,\n"
                    + "       THERAPEUTIC_CLASS,\n"
                    + "       COMPANY_CATEGORY\n"
                    + "FROM   PROJECTION_DETAILS PD\n"
                    + "       INNER JOIN CCP_DETAILS C\n"
                    + "               ON C.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                    + "       INNER JOIN ITEM_MASTER IM\n"
                    + "               ON IM.ITEM_MASTER_SID = C.ITEM_MASTER_SID\n"
                    + "       INNER JOIN BRAND_MASTER B\n"
                    + "               ON B.BRAND_MASTER_SID = IM.BRAND_MASTER_SID\n"
                    + "       INNER JOIN COMPANY_MASTER CM\n"
                    + "               ON CM.COMPANY_MASTER_SID = C.COMPANY_MASTER_SID\n"
                    + "WHERE  PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID\n"
                    + "\n"
                    + "SELECT FINALQ.BRAND_NAME,\n"
                    + "       FINALQ.YEARS,\n"
                    + "       FINALQ.PERIODS,\n"
                    + "       FINALQ.PROJECTION_SALES,\n"
                    + "       FINALQ.TYPE,\n"
                    + "       FINALQ.PROJECTION_RATE,\n"
                    + "	   FINALQ.PROJECTION_RPU\n"
                    + "FROM   (SELECT Q.BRAND_NAME,\n"
                    + "               Q.YEARS,\n"
                    + "               Q.PERIODS,\n"
                    + "               Q.PROJECTION_SALES,\n"
                    + "               Q.TYPE,\n"
                    + "               Q.PROJECTION_RATE,\n"
                    + "               Q.PROJECTION_RPU,\n"
                    + "               Dense_rank()\n"
                    + "                 OVER (\n"
                    + "                   ORDER BY Q.BRAND_NAME) AS TEMP_INDEX\n"
                    + "        FROM   (SELECT A1.BRAND_NAME                                                              AS BRAND_NAME,\n"
                    + "                       PER.year                                                                   AS YEARS,\n"
                    + "                       PER." + frequency + "                                                                AS PERIODS,\n"
                    + "                       Sum(B1.PROJECTION_SALES)                                                   PROJECTION_SALES,\n"
                    + "                       'PROJECTION'                                                               AS TYPE,\n"
                    + "                       ( COALESCE(Sum(B1.PROJECTION_SALES) / NULLIF(Sum(A1.SALES), 0), 0) * 100 ) AS PROJECTION_RATE,\n"
                    + "                       ( COALESCE(Sum(B1.PROJECTION_SALES) / NULLIF(Sum(A1.UNITS), 0), 0) )       AS PROJECTION_RPU\n"
                    + "                FROM   (SELECT BRAND_NAME,\n"
                    + "                               Sum(SALES) AS SALES,\n"
                    + "                               Sum(UNITS) AS UNITS,\n"
                    + "                               PERIOD_SID\n"
                    + "                        FROM   (SELECT Sum(B.PROJECTION_SALES) AS SALES,\n"
                    + "                                       Sum(B.PROJECTION_UNITS) AS UNITS,\n"
                    + "                                       PER.PERIOD_SID,\n"
                    + "                                       I.BRAND_NAME,\n"
                    + "                                       I.BRAND_MASTER_SID,\n"
                    + "                                       I.THERAPEUTIC_CLASS\n"
                    + "                                FROM   dbo.ST_M_SALES_PROJECTION_MASTER A\n"
                    + "                                       JOIN dbo.ST_M_SALES_PROJECTION B\n"
                    + "                                         ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID\n"
                    + "                                            AND A.USER_ID = B.USER_ID\n"
                    + "                                            AND A.SESSION_ID = B.SESSION_ID\n"
                    + "                                       JOIN @INPUT_INFO I\n"
                    + "                                         ON I.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID\n"
                    + "                                       JOIN PERIOD PER\n"
                    + "                                         ON PER.PERIOD_SID = B.PERIOD_SID\n"
                    + "                                WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID\n"
                    + "                                       AND A.USER_ID = @USER_ID\n"
                    + "                                       AND A.SESSION_ID = @SESSION_ID\n"
                    + "                                GROUP  BY PER.PERIOD_SID,\n"
                    + "                                          I.BRAND_NAME,\n"
                    + "                                          I.BRAND_MASTER_SID,\n"
                    + "                                          I.THERAPEUTIC_CLASS)A\n"
                    + "                        WHERE  ( A.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS\n"
                    + "                                 AND @THERAPEUTIC_CLASS IS NOT NULL\n"
                    + "                                 AND @BRAND IS NULL )\n"
                    + "                                OR ( A.BRAND_MASTER_SID = @BRAND\n"
                    + "                                     AND @BRAND IS NOT NULL\n"
                    + "                                     AND @THERAPEUTIC_CLASS IS NULL )\n"
                    + "                                OR ( A.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS\n"
                    + "                                     AND A.BRAND_MASTER_SID = @BRAND )\n"
                    + "                                OR ( @THERAPEUTIC_CLASS IS NULL\n"
                    + "                                     AND @BRAND IS NULL )\n"
                    + "                        GROUP  BY A.BRAND_NAME,\n"
                    + "                                  A.PERIOD_SID) A1\n"
                    + "                       LEFT JOIN (SELECT BRAND_NAME,\n"
                    + "                                         Sum(PROJECTION_SALES) AS PROJECTION_SALES,\n"
                    + "                                         PERIOD_SID\n"
                    + "                                  FROM   (SELECT BRAND_NAME,\n"
                    + "                                                 Sum(PROJECTION_SALES) AS PROJECTION_SALES,\n"
                    + "                                                 PERIOD_SID,\n"
                    + "                                                 I.BRAND_MASTER_SID,\n"
                    + "                                                 THERAPEUTIC_CLASS\n"
                    + "                                          FROM   @INPUT_INFO I\n"
                    + "                                                 CROSS APPLY (SELECT TOP 1 PM.PROJECTION_MASTER_SID\n"
                    + "                                                              FROM   PROJECTION_MASTER PM\n"
                    + "                                                                     JOIN WORKFLOW_MASTER WM\n"
                    + "                                                                       ON PM.PROJECTION_MASTER_SID = WM.PROJECTION_MASTER_SID\n"
                    + "                                                                          AND WM.WORKFLOW_STATUS_ID = (SELECT HELPER_TABLE_SID\n"
                    + "                                                                                                       FROM   HELPER_TABLE\n"
                    + "                                                                                                       WHERE  LIST_NAME = 'WorkFlowStatus'\n"
                    + "                                                                                                              AND DESCRIPTION = 'Approved')\n"
                    + "                                                                     JOIN @INPUT_INFO I\n"
                    + "                                                                       ON I.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID\n"
                    + "                                                              ORDER  BY WM.MODIFIED_DATE DESC) cs\n"
                    + "                                                 JOIN [dbo].[NM_DISCOUNT_PROJECTION] C\n"
                    + "                                                   ON c.PROJECTION_DETAILS_SID = I.PROJECTION_DETAILS_SID\n"
                    + "                                                 JOIN HELPER_TABLE H\n"
                    + "                                                   ON I.COMPANY_CATEGORY = H.HELPER_TABLE_SID\n"
                    + "                                          WHERE  I.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID\n"
                    + "                                                 AND H.DESCRIPTION IN ( 'MM Medi', 'MCG Plan' )\n"
                    + "                                          GROUP  BY BRAND_NAME,\n"
                    + "                                                    PERIOD_SID,\n"
                    + "                                                    I.BRAND_MASTER_SID,\n"
                    + "                                                    THERAPEUTIC_CLASS)B\n"
                    + "                                  WHERE  ( B.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS\n"
                    + "                                           AND @THERAPEUTIC_CLASS IS NOT NULL\n"
                    + "                                           AND @BRAND IS NULL )\n"
                    + "                                          OR ( B.BRAND_MASTER_SID = @BRAND\n"
                    + "                                               AND @BRAND IS NOT NULL\n"
                    + "                                               AND @THERAPEUTIC_CLASS IS NULL )\n"
                    + "                                          OR ( B.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS\n"
                    + "                                               AND B.BRAND_MASTER_SID = @BRAND )\n"
                    + "                                          OR ( @THERAPEUTIC_CLASS IS NULL\n"
                    + "                                               AND @BRAND IS NULL )\n"
                    + "                                  GROUP  BY B.BRAND_NAME,\n"
                    + "                                            B.PERIOD_SID)B1\n"
                    + "                              ON A1.BRAND_NAME = B1.BRAND_NAME\n"
                    + "                                 AND A1.PERIOD_SID = B1.PERIOD_SID\n"
                    + "                       JOIN period AS per\n"
                    + "                         ON per.PERIOD_SID = A1.PERIOD_SID\n"
                    + "                GROUP  BY A1.BRAND_NAME,\n"
                    + "                          per.year,\n"
                    + "                          per." + frequency + "\n"
                    + "                UNION ALL\n"
                    + "                SELECT A1.BRAND_NAME                                                              AS BRAND_NAME,\n"
                    + "                       per.year                                                                   AS YEARS,\n"
                    + "                       per." + frequency + "                                                                AS PERIODS,\n"
                    + "                       Sum(B1.PROJECTION_SALES)                                                   AS PROJECTION_SALES,\n"
                    + "                       'ACTUAL'                                                                   AS TYPE,\n"
                    + "                       ( COALESCE(Sum(B1.PROJECTION_SALES) / NULLIF(Sum(A1.SALES), 0), 0) * 100 ) AS PROJECTION_RATE,\n"
                    + "                       ( COALESCE(Sum(B1.PROJECTION_SALES) / NULLIF(Sum(A1.UNITS), 0), 0) )       AS PROJECTION_RPU\n"
                    + "                FROM   (SELECT BRAND_NAME,\n"
                    + "                               Sum(SALES) AS SALES,\n"
                    + "                               Sum(UNITS) AS UNITS,\n"
                    + "                               PERIOD_SID\n"
                    + "                        FROM   (SELECT Sum(B.ACTUAL_SALES) AS SALES,\n"
                    + "                                       Sum(B.ACTUAL_UNITS) AS UNITS,\n"
                    + "                                       PER.PERIOD_SID,\n"
                    + "                                       I.BRAND_NAME,\n"
                    + "                                       I.BRAND_MASTER_SID,\n"
                    + "                                       I.THERAPEUTIC_CLASS\n"
                    + "                                FROM   dbo.ST_M_SALES_PROJECTION_MASTER A\n"
                    + "                                       JOIN dbo.ST_M_ACTUAL_SALES B\n"
                    + "                                         ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID\n"
                    + "                                            AND A.USER_ID = B.USER_ID\n"
                    + "                                            AND A.SESSION_ID = B.SESSION_ID\n"
                    + "                                       JOIN @INPUT_INFO I\n"
                    + "                                         ON I.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID\n"
                    + "                                       JOIN PERIOD PER\n"
                    + "                                         ON PER.PERIOD_SID = B.PERIOD_SID\n"
                    + "                                WHERE  I.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID\n"
                    + "                                       AND A.USER_ID = @USER_ID\n"
                    + "                                       AND A.SESSION_ID = @SESSION_ID\n"
                    + "                                GROUP  BY PER.PERIOD_SID,\n"
                    + "                                          I.BRAND_NAME,\n"
                    + "                                          I.BRAND_MASTER_SID,\n"
                    + "                                          I.THERAPEUTIC_CLASS)A\n"
                    + "                        WHERE  ( A.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS\n"
                    + "                                 AND @THERAPEUTIC_CLASS IS NOT NULL\n"
                    + "                                 AND @BRAND IS NULL )\n"
                    + "                                OR ( A.BRAND_MASTER_SID = @BRAND\n"
                    + "                                     AND @BRAND IS NOT NULL\n"
                    + "                                     AND @THERAPEUTIC_CLASS IS NULL )\n"
                    + "                                OR ( A.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS\n"
                    + "                                     AND A.BRAND_MASTER_SID = @BRAND )\n"
                    + "                                OR ( @THERAPEUTIC_CLASS IS NULL\n"
                    + "                                     AND @BRAND IS NULL )\n"
                    + "                        GROUP  BY A.BRAND_NAME,\n"
                    + "                                  A.PERIOD_SID) A1\n"
                    + "                       LEFT JOIN (SELECT BRAND_NAME,\n"
                    + "                                         Sum(PROJECTION_SALES) AS PROJECTION_SALES,\n"
                    + "                                         PERIOD_SID\n"
                    + "                                  FROM   (SELECT BRAND_NAME,\n"
                    + "                                                 Sum(ACTUAL_SALES) AS PROJECTION_SALES,\n"
                    + "                                                 PERIOD_SID,\n"
                    + "                                                 I.BRAND_MASTER_SID,\n"
                    + "                                                 THERAPEUTIC_CLASS\n"
                    + "                                          FROM   @INPUT_INFO I\n"
                    + "                                                 CROSS APPLY (SELECT TOP 1 PM.PROJECTION_MASTER_SID\n"
                    + "                                                              FROM   PROJECTION_MASTER PM\n"
                    + "                                                                     JOIN WORKFLOW_MASTER WM\n"
                    + "                                                                       ON PM.PROJECTION_MASTER_SID = WM.PROJECTION_MASTER_SID\n"
                    + "                                                                          AND WM.WORKFLOW_STATUS_ID = (SELECT HELPER_TABLE_SID\n"
                    + "                                                                                                       FROM   HELPER_TABLE\n"
                    + "                                                                                                       WHERE  LIST_NAME = 'WorkFlowStatus'\n"
                    + "                                                                                                              AND DESCRIPTION = 'Approved')\n"
                    + "                                                                     JOIN @INPUT_INFO I\n"
                    + "                                                                       ON I.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID\n"
                    + "                                                              ORDER  BY WM.MODIFIED_DATE DESC) cs\n"
                    + "                                                 JOIN [dbo].[NM_ACTUAL_DISCOUNT] C\n"
                    + "                                                   ON c.PROJECTION_DETAILS_SID = I.PROJECTION_DETAILS_SID\n"
                    + "                                                 JOIN HELPER_TABLE H\n"
                    + "                                                   ON I.COMPANY_CATEGORY = H.HELPER_TABLE_SID\n"
                    + "                                          WHERE  I.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID\n"
                    + "                                                 AND H.DESCRIPTION IN ( 'MM Medi', 'MCG Plan' )\n"
                    + "                                          GROUP  BY BRAND_NAME,\n"
                    + "                                                    PERIOD_SID,\n"
                    + "                                                    I.BRAND_MASTER_SID,\n"
                    + "                                                    THERAPEUTIC_CLASS)B\n"
                    + "                                  WHERE  ( B.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS\n"
                    + "                                           AND @THERAPEUTIC_CLASS IS NOT NULL\n"
                    + "                                           AND @BRAND IS NULL )\n"
                    + "                                          OR ( B.BRAND_MASTER_SID = @BRAND\n"
                    + "                                               AND @BRAND IS NOT NULL\n"
                    + "                                               AND @THERAPEUTIC_CLASS IS NULL )\n"
                    + "                                          OR ( B.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS\n"
                    + "                                               AND B.BRAND_MASTER_SID = @BRAND )\n"
                    + "                                          OR ( @THERAPEUTIC_CLASS IS NULL\n"
                    + "                                               AND @BRAND IS NULL )\n"
                    + "                                  GROUP  BY B.BRAND_NAME,\n"
                    + "                                            B.PERIOD_SID)B1\n"
                    + "                              ON A1.BRAND_NAME = B1.BRAND_NAME\n"
                    + "                                 AND A1.PERIOD_SID = B1.PERIOD_SID\n"
                    + "                       JOIN period AS per\n"
                    + "                         ON per.PERIOD_SID = A1.PERIOD_SID\n"
                    + "                GROUP  BY per.year,\n"
                    + "                          A1.BRAND_NAME,\n"
                    + "                          per." + frequency + ") Q) FINALQ\n"
                    + "WHERE  FINALQ.TEMP_INDEX > " + projSelDTO.getStart() + " AND FINALQ.TEMP_INDEX <=   " + (projSelDTO.getStart() + projSelDTO.getOffset()) + "\n"
                    + "\n"
                    + " order by BRAND_NAME,YEARS,PERIODS";
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            list = (List) salesProjectionDAO.executeSelectQuery(str);
            return list;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;

        }

    }

    public List getLevelValue(ProjectionSelectionDTO projSelDTO) {
        List list = new ArrayList();
        String query = StringUtils.EMPTY;
        String frequency = projSelDTO.getFrequency();
        if (frequency.equals(Constant.QUARTERLY)) {
            frequency = "QUARTER";
        }
        if (frequency.equals(ANNUALLY.getConstant())) {
            frequency = "YEAR";
        }
        if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
            frequency = "SEMI_ANNUAL";

        }
        if (frequency.equals(MONTHLY.getConstant())) {
            frequency = "MONTH";
        }

        try {
            query += "\n"
                    + "select FINALQ.YEARS,FINALQ.PERIODS,FINALQ.COM_ID,FINALQ.CON_ID,FINALQ.BRAND_ID, FINALQ.COMPANY_MASTER_SID,FINALQ.COMPANY_NO,\n"
                    + "  FINALQ.LEVEL_NAME,FINALQ.Actual_Sale,FINALQ.Disc_Rate,FINALQ.Flag,FINALQ.RPU from(\n"
                    + "select Q.YEARS,Q.PERIODS,Q.COM_ID,Q.CON_ID,Q.BRAND_ID, Q.COMPANY_MASTER_SID,Q.COMPANY_NO,\n"
                    + "  Q.LEVEL_NAME,Q.Actual_Sale,Q.Disc_Rate,Q.Flag,Q.RPU,Dense_rank() OVER    (ORDER BY Q.COMPANY_MASTER_SID) AS TEMP_INDEX from("
                    + "select per.YEAR as YEARS,per." + frequency + " as PERIODS, 0 AS COM_ID,0 AS CON_ID,0 AS BRAND_ID, TT.COMPANY_MASTER_SID as COMPANY_MASTER_SID,TT.COMPANY_NO as COMPANY_NO,\n"
                    + " 'Customer' as LEVEL_NAME,\n"
                    + "\n"
                    + " sum(SUPMAS.ACTUAL_SALES) As Actual_Sale,\n"
                    + "--  avg(SUPMAS.ACTUAL_RATE) As Actual_rate,\n"
                    + "Coalesce(Sum(SUPMAS.ACTUAL_SALES)/Nullif(Sum(m_mas.ACTUAL_SALES),0),0)*100       AS  Disc_Rate,\n"
                    + " 'ACT' As Flag,Coalesce(Sum(SUPMAS.ACTUAL_SALES)/Nullif(Sum(m_mas.ACTUAL_UNITS),0),0) As RPU\n"
                    + "\n"
                    + " FROM PROJECTION_DETAILS PD,\n"
                    + " CCP_DETAILS CCP,\n"
                    + " COMPANY_MASTER TT,\n"
                    + " M_ACTUAL_DISCOUNT SUPMAS,\n"
                    + " Period per,\n"
                    + "ST_M_ACTUAL_SALES m_mas,\n"
                    + "ST_M_SALES_PROJECTION_MASTER m_mac\n"
                    + "where  CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                    + " and TT.COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID\n"
                    + "  and SUPMAS.USER_ID =" + projSelDTO.getUserId() + " \n"
                    + "  and SUPMAS.SESSION_ID =" + projSelDTO.getSessionId() + "\n"
                    + "  and PD.PROJECTION_MASTER_SID =" + projSelDTO.getProjectionId() + "\n"
                    + "  and Per.PERIOD_SID = SUPMAS.PERIOD_SID\n"
                    + "and Per.PERIOD_SID = m_mas.PERIOD_SID\n"
                    + "  and SUPMAS.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID \n"
                    + "and pd.PROJECTION_DETAILS_SID = m_mas.PROJECTION_DETAILS_SID\n"
                    + "AND m_mas.USER_ID = SUPMAS.USER_ID \n"
                    + " AND m_mas.SESSION_ID = SUPMAS.SESSION_ID\n"
                    + "and m_mac.PROJECTION_DETAILS_SID=pd.PROJECTION_DETAILS_SID\n"
                    + "and m_mac.USER_ID=SUPMAS.USER_ID\n"
                    + "AND m_mac.SESSION_ID =  SUPMAS.SESSION_ID\n"
                    + "\n";
            if ((!StringUtils.EMPTY.equalsIgnoreCase(projSelDTO.getPivotValue()) && !Constant.NULL.equalsIgnoreCase(projSelDTO.getPivotValue())) && Constant.DISCOUNT_SMALL.equalsIgnoreCase(projSelDTO.getPivotView())) {
                if ("YEAR".equalsIgnoreCase(frequency)) {
                    query += "AND per.YEAR =" + projSelDTO.getPivotValue();
                } else {
                    String str[] = projSelDTO.getPivotValue().split(" ");
                    query += " AND per." + frequency + Constant.EQUAL + str[0] + " and " + "per.YEAR =" + str[1];
                }

            }
            if (projSelDTO.isFilterDdlb()) {
                query += " and TT." + projSelDTO.getDefinedContract() + " = '" + projSelDTO.getSelectedCust() + "' ";
            }
            query += " group by  TT.COMPANY_MASTER_SID,TT.COMPANY_NO,per.YEAR,\n"
                    + "           per." + frequency + "\n"
                    + "UNION\n"
                    + "\n"
                    + "select per.YEAR as YEARS,per." + frequency + " as PERIODS, 0 AS COM_ID,0 AS CON_ID,0 AS BRAND_ID, TT.COMPANY_MASTER_SID as COMPANY_MASTER_SID,TT.COMPANY_NO as COMPANY_NO,\n"
                    + " 'Customer' as LEVEL_NAME,\n"
                    + "\n"
                    + " sum(SUPMAS.PROJECTION_SALES) As Actual_Sale,\n"
                    + "--  avg(SUPMAS.PROJECTION_RATE) As Proj_rate,\n"
                    + "Coalesce(Sum(SUPMAS.PROJECTION_SALES)/Nullif(Sum(m_mas.PROJECTION_SALES),0),0)*100       AS  Disc_Rate,\n"
                    + " 'Proj' As Flag,Coalesce(Sum(SUPMAS.PROJECTION_SALES)/Nullif(Sum(m_mas.PROJECTION_UNITS),0),0) As RPU \n"
                    + "\n"
                    + " FROM PROJECTION_DETAILS PD,\n"
                    + " CCP_DETAILS CCP,\n"
                    + " COMPANY_MASTER TT,\n"
                    + " M_DISCOUNT_PROJECTION SUPMAS,\n"
                    + " Period per,\n"
                    + "ST_M_SALES_PROJECTION m_mas,\n"
                    + "ST_M_SALES_PROJECTION_MASTER m_mac\n"
                    + "where  CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                    + " and TT.COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID\n"
                    + " and SUPMAS.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID   and SUPMAS.USER_ID =" + projSelDTO.getUserId() + " and SUPMAS.SESSION_ID =" + projSelDTO.getSessionId() + "and PD.PROJECTION_MASTER_SID =" + projSelDTO.getProjectionId() + "\n"
                    + "  and Per.PERIOD_SID = SUPMAS.PERIOD_SID\n"
                    + "and Per.PERIOD_SID=m_mas.PERIOD_SID\n"
                    + "and pd.PROJECTION_DETAILS_SID = m_mas.PROJECTION_DETAILS_SID\n"
                    + "AND m_mas.USER_ID = SUPMAS.USER_ID \n"
                    + " AND m_mas.SESSION_ID = SUPMAS.SESSION_ID\n"
                    + "and m_mac.PROJECTION_DETAILS_SID=pd.PROJECTION_DETAILS_SID\n"
                    + "and m_mac.USER_ID=SUPMAS.USER_ID\n"
                    + "AND m_mac.SESSION_ID =  SUPMAS.SESSION_ID\n"
                    + "\n";
            if (projSelDTO.isFilterDdlb()) {
                query += " and TT." + projSelDTO.getDefinedContract() + " = '" + projSelDTO.getSelectedCust() + "' ";
            }
            if (!StringUtils.EMPTY.equalsIgnoreCase(projSelDTO.getPivotValue()) && !Constant.NULL.equalsIgnoreCase(projSelDTO.getPivotValue()) && Constant.DISCOUNT_SMALL.equalsIgnoreCase(projSelDTO.getPivotView())) {
                if ("YEAR".equalsIgnoreCase(frequency)) {
                    query += " AND  per.YEAR =" + projSelDTO.getPivotValue();
                } else {
                    String str[] = projSelDTO.getPivotValue().split(" ");
                    query += "and per." + frequency + Constant.EQUAL + str[0] + " and " + "per.YEAR =" + str[1];
                }
            }
            query += "  group by  TT.COMPANY_MASTER_SID,TT.COMPANY_NO,per.YEAR,\n"
                    + "           per." + frequency + "\n"
                    + "\n"
                    + ") Q) FINALQ\n"
                    + "WHERE  FINALQ.TEMP_INDEX > " + projSelDTO.getStart() + " AND FINALQ.TEMP_INDEX <=  " + (projSelDTO.getStart() + projSelDTO.getOffset()) + "\n"
                    + "\n"
                    + " order by COMPANY_MASTER_SID,YEARS,PERIODS";
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            list = (List) salesProjectionDAO.executeSelectQuery(query);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        return list;
    }

    public List getContractLevelValue(ProjectionSelectionDTO projSelDTO) {
        List list = new ArrayList();
        String query = StringUtils.EMPTY;
        String frequency = projSelDTO.getFrequency();
        if (frequency.equals(Constant.QUARTERLY)) {
            frequency = "QUARTER";
        }
        if (frequency.equals(ANNUALLY.getConstant())) {
            frequency = "YEAR";
        }
        if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
            frequency = "SEMI_ANNUAL";

        }
        if (frequency.equals(MONTHLY.getConstant())) {
            frequency = "MONTH";
        }
        try {
            if (!projSelDTO.getCurrentCustomer().equals(Constant.NULL)) {
                query = "select FINALQ.YEARS,FINALQ.PERIODS,FINALQ.COM_ID,FINALQ.CON_ID,FINALQ.BRAND_ID, FINALQ.CONTRACT_MASTER_SID,FINALQ.CONTRACT_NO,\n"
                        + "   FINALQ.LEVEL_NAME,FINALQ.Actual_Sale,FINALQ.Actual_Rate,FINALQ.Flag,FINALQ.RPU from(\n"
                        + "select Q.YEARS,Q.PERIODS,Q.COM_ID,Q.CON_ID,Q.BRAND_ID, Q.CONTRACT_MASTER_SID,Q.CONTRACT_NO,\n"
                        + "  Q.LEVEL_NAME,Q.Actual_Sale,Q.Actual_Rate,Q.Flag,Q.RPU,Dense_rank() OVER(ORDER BY Q.CONTRACT_MASTER_SID) AS TEMP_INDEX from("
                        + "select per.YEAR as YEARS,per." + frequency + "  as PERIODS," + projSelDTO.getCurrentCustomer() + " AS COM_ID,0 AS CON_ID,0 AS BRAND_ID, TT.CONTRACT_MASTER_SID AS CONTRACT_MASTER_SID,TT.CONTRACT_NO AS CONTRACT_NO,\n"
                        + " 'Contract' as LEVEL_NAME, \n"
                        + " Sum(SUPMAS.ACTUAL_SALES) AS Actual_Sale  ,\n"
                        + "Coalesce(Sum(SUPMAS.ACTUAL_SALES)/Nullif(Sum(STMAS.ACTUAL_SALES),0),0)*100       AS  Actual_Rate,\n"
                        + " 'ACT' AS Flag,Coalesce(Sum(SUPMAS.ACTUAL_SALES)/Nullif(Sum(STMAS.ACTUAL_UNITS),0),0) AS RPU \n"
                        + "  FROM PROJECTION_DETAILS PD,\n"
                        + "  CCP_DETAILS CCP,\n"
                        + "  CONTRACT_MASTER TT,\n"
                        + "  M_ACTUAL_DISCOUNT SUPMAS,\n"
                        + " ST_M_ACTUAL_SALES  STMAS,\n"
                        + " ST_M_SALES_PROJECTION_MASTER STMSP, \n"
                        + "  Period per"
                        + "  where  CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                        + "  and TT.CONTRACT_MASTER_SID = CCP.CONTRACT_MASTER_SID\n"
                        + "  and SUPMAS.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n"
                        + "  and per.PERIOD_SID=SUPMAS.PERIOD_SID\n"
                        + "  and CCP.COMPANY_MASTER_SID = " + projSelDTO.getCurrentCustomer() + " \n"
                        + "  and SUPMAS.USER_ID =" + projSelDTO.getUserId() + " \n"
                        + "  and SUPMAS.SESSION_ID =" + projSelDTO.getSessionId() + "\n"
                        + "  and PD.PROJECTION_MASTER_SID =" + projSelDTO.getProjectionId() + "\n"
                        + "and STMAS.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
                        + "  and STMAS.USER_ID=SUPMAS.USER_ID\n"
                        + "  and STMAS.SESSION_ID=SUPMAS.SESSION_ID\n"
                        + "  and STMAS.PERIOD_SID= per.PERIOD_SID\n"
                        + "  and STMSP.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
                        + "  and STMSP.USER_ID=SUPMAS.USER_ID\n"
                        + "  and STMSP.SESSION_ID=SUPMAS.SESSION_ID";
                if (!StringUtils.EMPTY.equalsIgnoreCase(projSelDTO.getPivotValue()) && !Constant.NULL.equalsIgnoreCase(projSelDTO.getPivotValue()) && Constant.DISCOUNT_SMALL.equalsIgnoreCase(projSelDTO.getPivotView())) {
                    if ("YEAR".equalsIgnoreCase(frequency)) {
                        query += " AND  per.YEAR =" + projSelDTO.getPivotValue();
                    } else {
                        String str[] = projSelDTO.getPivotValue().split(" ");
                        query += " AND per." + frequency + Constant.EQUAL + str[0] + " and " + "per.YEAR =" + str[1];
                    }
                }
                query += "  group by  TT.CONTRACT_MASTER_SID,TT.CONTRACT_NO,per.YEAR,per." + frequency + "\n"
                        + "  UNION\n"
                        + "select per.YEAR as YEARS,per." + frequency + " as PERIODS," + projSelDTO.getCurrentCustomer() + " AS COM_ID,0 AS CON_ID,0 AS BRAND_ID, TT.CONTRACT_MASTER_SID AS CONTRACT_MASTER_SID,TT.CONTRACT_NO AS CONTRACT_NO,\n"
                        + " 'Contract' as LEVEL_NAME, \n"
                        + " Sum(SUPMAS.PROJECTION_SALES)AS Actual_Sale,\n"
                        + "Coalesce(Sum(SUPMAS.PROJECTION_SALES)/Nullif(Sum(STSP.PROJECTION_SALES),0),0)*100       AS  Actual_Rate,\n"
                        + " 'Proj' AS Flag,Coalesce(Sum(SUPMAS.PROJECTION_SALES)/Nullif(Sum(STSP.PROJECTION_UNITS),0),0) AS RPU \n"
                        + "\n"
                        + "  FROM PROJECTION_DETAILS PD,\n"
                        + "  CCP_DETAILS CCP,\n"
                        + "  CONTRACT_MASTER TT,\n"
                        + "  M_DISCOUNT_PROJECTION SUPMAS,\n"
                        + "ST_M_SALES_PROJECTION STSP,\n"
                        + "ST_M_SALES_PROJECTION_MASTER STMSP, \n"
                        + "  Period per"
                        + "  where  CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                        + "  and TT.CONTRACT_MASTER_SID = CCP.CONTRACT_MASTER_SID\n"
                        + "  and SUPMAS.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n"
                        + "  and per.PERIOD_SID=SUPMAS.PERIOD_SID\n"
                        + "  and CCP.COMPANY_MASTER_SID = " + projSelDTO.getCurrentCustomer() + " \n"
                        + "  and SUPMAS.USER_ID =" + projSelDTO.getUserId() + " \n"
                        + "  and SUPMAS.SESSION_ID =" + projSelDTO.getSessionId() + "\n"
                        + "  and PD.PROJECTION_MASTER_SID =" + projSelDTO.getProjectionId() + "\n"
                        + " and STSP.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
                        + "  and STSP.USER_ID=SUPMAS.USER_ID\n"
                        + "  and STSP.SESSION_ID=SUPMAS.SESSION_ID\n"
                        + "  and STSP.PERIOD_SID= per.PERIOD_SID\n"
                        + "  and STMSP.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
                        + "  and STMSP.USER_ID=SUPMAS.USER_ID\n"
                        + "  and STMSP.SESSION_ID=SUPMAS.SESSION_ID";
                if (!StringUtils.EMPTY.equalsIgnoreCase(projSelDTO.getPivotValue()) && !Constant.NULL.equalsIgnoreCase(projSelDTO.getPivotValue()) && Constant.DISCOUNT_SMALL.equalsIgnoreCase(projSelDTO.getPivotView())) {
                    if ("YEAR".equalsIgnoreCase(frequency)) {
                        query += " AND  per.YEAR =" + projSelDTO.getPivotValue();
                    } else {
                        String str[] = projSelDTO.getPivotValue().split(" ");
                        query += " AND per." + frequency + Constant.EQUAL + str[0] + " and " + "per.YEAR =" + str[1];
                    }
                }
                query += "  group by  TT.CONTRACT_MASTER_SID,TT.CONTRACT_NO,per.YEAR,per." + frequency + "\n"
                        + "     "
                        + ") Q) FINALQ\n"
                        + "WHERE  FINALQ.TEMP_INDEX > " + projSelDTO.getStart() + " AND FINALQ.TEMP_INDEX <=  " + (projSelDTO.getStart() + projSelDTO.getOffset()) + "\n"
                        + "\n"
                        + " order by CONTRACT_MASTER_SID,YEARS,PERIODS";

            }
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            list = (List) salesProjectionDAO.executeSelectQuery(query);
            return list;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }

    }

    public List getBrandLevelValue(ProjectionSelectionDTO projSelDTO) {
        List list = new ArrayList();
        String query = StringUtils.EMPTY;
        String frequency = projSelDTO.getFrequency();
        if (frequency.equals(Constant.QUARTERLY)) {
            frequency = "QUARTER";
        }
        if (frequency.equals(ANNUALLY.getConstant())) {
            frequency = "YEAR";
        }
        if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
            frequency = "SEMI_ANNUAL";

        }
        if (frequency.equals(MONTHLY.getConstant())) {
            frequency = "MONTH";
        }
        try {
            if (!Constant.NULL.equals(projSelDTO.getCurrentContract()) && !Constant.NULL.equals(projSelDTO.getCurrentCustomer())) {
                query = "  select FINALQ.YEARS,FINALQ.PERIODS,FINALQ.COM_ID,FINALQ.CON_ID,FINALQ.BRAND_ID, FINALQ.BRAND_MASTER_SID,FINALQ.BRAND_NAME,\n"
                        + "    FINALQ.LEVEL_NAME,FINALQ.Actual_Sale,FINALQ.Actual_Rate,FINALQ.Flag,FINALQ.RPU from(\n"
                        + " select Q.YEARS,Q.PERIODS,Q.COM_ID,Q.CON_ID,Q.BRAND_ID, Q.BRAND_MASTER_SID,Q.BRAND_NAME,\n"
                        + "    Q.LEVEL_NAME,Q.Actual_Sale,Q.Actual_Rate,Q.Flag,Q.RPU,Dense_rank() OVER (ORDER BY Q.BRAND_MASTER_SID) AS TEMP_INDEX from("
                        + "select per.YEAR AS YEARS,per." + frequency + " AS PERIODS ," + projSelDTO.getCurrentCustomer() + " AS COM_ID," + projSelDTO.getCurrentContract() + " AS CON_ID,0 AS BRAND_ID,TT.BRAND_MASTER_SID AS BRAND_MASTER_SID,TT.BRAND_NAME AS BRAND_NAME,\n"
                        + "'Brand' as LEVEL_NAME,\n"
                        + "sum(SUPMAS.ACTUAL_SALES)AS Actual_Sale,\n"
                        + "Coalesce(Sum(SUPMAS.ACTUAL_SALES)/Nullif(Sum(STMAS.ACTUAL_SALES),0),0)*100       AS  Actual_Rate,\n"
                        + "'ACT' AS Flag,Coalesce(Sum(SUPMAS.ACTUAL_SALES)/Nullif(Sum(STMAS.ACTUAL_UNITS),0),0) AS RPU \n"
                        + "\n"
                        + "FROM PROJECTION_DETAILS PD,\n"
                        + "CCP_DETAILS CCP,\n"
                        + "ITEM_MASTER IM,\n"
                        + " BRAND_MASTER TT,\n"
                        + " M_ACTUAL_DISCOUNT SUPMAS ,\n"
                        + " ST_M_ACTUAL_SALES STMAS,\n"
                        + "ST_M_SALES_PROJECTION_MASTER STMSPM,\n"
                        + " Period per"
                        + " where  CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                        + "and TT.BRAND_MASTER_SID = IM.BRAND_MASTER_SID\n"
                        + " and SUPMAS.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n"
                        + " and per.PERIOD_SID=SUPMAS.PERIOD_SID\n"
                        + " and CCP.COMPANY_MASTER_SID = " + projSelDTO.getCurrentCustomer() + "\n"
                        + "  and CCP.CONTRACT_MASTER_SID = " + projSelDTO.getCurrentContract() + "\n"
                        + " and CCP.ITEM_MASTER_SID = IM.ITEM_MASTER_SID  \n"
                        + " and SUPMAS.USER_ID =" + projSelDTO.getUserId() + " and\n"
                        + "  SUPMAS.SESSION_ID =" + projSelDTO.getSessionId() + "and PD.PROJECTION_MASTER_SID =" + projSelDTO.getProjectionId() + "\n"
                        + "and STMAS.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
                        + "and STMAS.PERIOD_SID=per.PERIOD_SID\n"
                        + "and STMAS.USER_ID=SUPMAS.USER_ID\n"
                        + "and STMAS.SESSION_ID=SUPMAS.SESSION_ID\n"
                        + "and STMSPM.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
                        + "and STMSPM.USER_ID=SUPMAS.USER_ID\n"
                        + "and STMSPM.SESSION_ID=SUPMAS.SESSION_ID  ";
                if (!StringUtils.EMPTY.equalsIgnoreCase(projSelDTO.getPivotValue()) && !Constant.NULL.equalsIgnoreCase(projSelDTO.getPivotValue()) && Constant.DISCOUNT_SMALL.equalsIgnoreCase(projSelDTO.getPivotView())) {
                    if ("YEAR".equalsIgnoreCase(frequency)) {
                        query += " AND  per.YEAR =" + projSelDTO.getPivotValue();
                    } else {
                        String str[] = projSelDTO.getPivotValue().split(" ");
                        query += " AND per." + frequency + Constant.EQUAL + str[0] + " and " + "per.YEAR =" + str[1];
                    }
                }
                query += "  group by TT.BRAND_MASTER_SID,TT.BRAND_NAME, per.YEAR,per." + frequency + " \n"
                        + "  \n"
                        + "  union \n"
                        + "  \n"
                        + "select per.YEAR AS YEARS,per." + frequency + " AS PERIODS ," + projSelDTO.getCurrentCustomer() + " AS COM_ID," + projSelDTO.getCurrentContract() + " AS CON_ID,0 AS BRAND_ID,TT.BRAND_MASTER_SID AS BRAND_MASTER_SID,TT.BRAND_NAME AS BRAND_NAME,\n"
                        + "'Brand' as LEVEL_NAME,\n"
                        + "sum(SUPMAS.PROJECTION_SALES)AS Projection_Sale,\n"
                        + "Coalesce(Sum(SUPMAS.PROJECTION_SALES)/Nullif(Sum(STSP.PROJECTION_SALES),0),0)*100       AS  Actual_Rate,\n"
                        + "'Proj'AS Flag,Coalesce(Sum(SUPMAS.PROJECTION_SALES)/Nullif(Sum(STSP.PROJECTION_UNITS),0),0) AS RPU \n"
                        + "\n"
                        + "FROM PROJECTION_DETAILS PD,\n"
                        + "CCP_DETAILS CCP,\n"
                        + "ITEM_MASTER IM,\n"
                        + " BRAND_MASTER TT,\n"
                        + " M_DISCOUNT_PROJECTION SUPMAS ,\n"
                        + "ST_M_SALES_PROJECTION STSP,\n"
                        + "ST_M_SALES_PROJECTION_MASTER STMSPM,\n"
                        + " Period per"
                        + " where  CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                        + "and TT.BRAND_MASTER_SID = IM.BRAND_MASTER_SID\n"
                        + " and SUPMAS.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n"
                        + " and per.PERIOD_SID=SUPMAS.PERIOD_SID\n"
                        + " and CCP.COMPANY_MASTER_SID = " + projSelDTO.getCurrentCustomer() + "\n"
                        + "  and CCP.CONTRACT_MASTER_SID = " + projSelDTO.getCurrentContract() + "\n"
                        + " and CCP.ITEM_MASTER_SID = IM.ITEM_MASTER_SID  \n"
                        + " and SUPMAS.USER_ID =" + projSelDTO.getUserId() + " and\n"
                        + "  SUPMAS.SESSION_ID =" + projSelDTO.getSessionId() + "  and PD.PROJECTION_MASTER_SID =" + projSelDTO.getProjectionId() + StringUtils.EMPTY
                        + "and STSP.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
                        + "and STSP.PERIOD_SID=per.PERIOD_SID\n"
                        + "and STSP.USER_ID=SUPMAS.USER_ID\n"
                        + "and STSP.SESSION_ID=SUPMAS.SESSION_ID\n"
                        + "and STMSPM.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
                        + "and STMSPM.USER_ID=SUPMAS.USER_ID\n"
                        + "and STMSPM.SESSION_ID=SUPMAS.SESSION_ID";
                if (!StringUtils.EMPTY.equalsIgnoreCase(projSelDTO.getPivotValue()) && !Constant.NULL.equalsIgnoreCase(projSelDTO.getPivotValue()) && Constant.DISCOUNT_SMALL.equalsIgnoreCase(projSelDTO.getPivotView())) {
                    if ("YEAR".equalsIgnoreCase(frequency)) {
                        query += " AND  per.YEAR =" + projSelDTO.getPivotValue();
                    } else {
                        String str[] = projSelDTO.getPivotValue().split(" ");
                        query += " AND per." + frequency + Constant.EQUAL + str[0] + " and " + "per.YEAR =" + str[1];
                    }
                }
                query += "  group by TT.BRAND_MASTER_SID,TT.BRAND_NAME, per.YEAR,per." + frequency + " \n"
                        + "\n"
                        + "  \n"
                        + "  "
                        + ") Q) FINALQ\n"
                        + "WHERE  FINALQ.TEMP_INDEX > " + projSelDTO.getStart() + " AND FINALQ.TEMP_INDEX <=  " + (projSelDTO.getStart() + projSelDTO.getOffset()) + "\n"
                        + "\n"
                        + " order by BRAND_MASTER_SID,YEARS,PERIODS";

                SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
                list = (List) salesProjectionDAO.executeSelectQuery(query);

            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        return list;
    }

    public List getSuppLevelValue(ProjectionSelectionDTO projSelDTO) {
        List list = new ArrayList();
        String query = StringUtils.EMPTY;
        String frequency = projSelDTO.getFrequency();
        if (frequency.equals(Constant.QUARTERLY)) {
            frequency = "QUARTER";
        }
        if (frequency.equals(ANNUALLY.getConstant())) {
            frequency = "YEAR";
        }
        if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
            frequency = "SEMI_ANNUAL";

        }
        if (frequency.equals(MONTHLY.getConstant())) {
            frequency = "MONTH";
        }

        try {
            query += "select FINALQ.YEARS,FINALQ.PERIODS,FINALQ.COM_ID,FINALQ.CON_ID,FINALQ.BRAND_ID, FINALQ.COMPANY_MASTER_SID,FINALQ.COMPANY_NO,\n"
                    + "   FINALQ.LEVEL_NAME,FINALQ.Actual_Sale,FINALQ.Actual_Rate,FINALQ.Flag,FINALQ.RPU from(\n"
                    + "select Q.YEARS,Q.PERIODS,Q.COM_ID,Q.CON_ID,Q.BRAND_ID, Q.COMPANY_MASTER_SID,Q.COMPANY_NO,\n"
                    + "   Q.LEVEL_NAME,Q.Actual_Sale,Q.Actual_Rate,Q.Flag,Q.RPU,Dense_rank() OVER (ORDER BY Q.COMPANY_MASTER_SID) AS TEMP_INDEX from("
                    + " select per.YEAR AS YEARS,per." + frequency + " AS PERIODS, 0 AS COM_ID ,0 AS CON_ID,0 AS BRAND_ID, TT.COMPANY_MASTER_SID AS COMPANY_MASTER_SID ,TT.COMPANY_NO AS COMPANY_NO,\n"
                    + " 'Customer' as LEVEL_NAME,\n"
                    + "\n"
                    + " sum(ST_M_DIS_ACT.ACTUAL_SALES) As Actual_Sale,\n"
                    + "Coalesce(Sum(ST_M_DIS_ACT.ACTUAL_SALES)/Nullif(Sum(ST_M_ACT_SAL.ACTUAL_SALES),0),0)*100       AS  Actual_Rate,"
                    + " 'ACT' As Flag,Coalesce(Sum(ST_M_DIS_ACT.ACTUAL_SALES)/Nullif(Sum(ST_M_ACT_SAL.ACTUAL_UNITS),0),0) As RPU \n"
                    + "\n"
                    + " FROM PROJECTION_DETAILS PD,\n"
                    + " CCP_DETAILS CCP,\n"
                    + " COMPANY_MASTER TT,\n"
                    + " ST_M_SUPPLEMENTAL_DISC_ACTUALS ST_M_DIS_ACT,\n"
                    + " ST_M_ACTUAL_SALES               ST_M_ACT_SAL,\n"
                    + "\n"
                    + "ST_M_SALES_PROJECTION_MASTER     ST_M_SAL_PROJ_MAS,  \n"
                    + " Period per\n"
                    + "where  CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                    + " and TT.COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID\n"
                    + "  and ST_M_DIS_ACT.USER_ID =" + projSelDTO.getUserId() + " \n"
                    + "  and ST_M_DIS_ACT.SESSION_ID =" + projSelDTO.getSessionId() + "\n"
                    + "  and PD.PROJECTION_MASTER_SID =" + projSelDTO.getProjectionId() + "\n"
                    + "  and  ST_M_DIS_ACT.PERIOD_SID =Per.PERIOD_SID\n"
                    + "and ST_M_DIS_ACT.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
                    + "and ST_M_ACT_SAL.PROJECTION_DETAILS_SID=ST_M_DIS_ACT.PROJECTION_DETAILS_SID\n"
                    + "and  ST_M_ACT_SAL.USER_ID= ST_M_DIS_ACT.USER_ID\n"
                    + "and ST_M_ACT_SAL.SESSION_ID= ST_M_DIS_ACT.SESSION_ID\n"
                    + "and ST_M_ACT_SAL.PERIOD_SID=Per.PERIOD_SID\n"
                    + "\n"
                    + "and ST_M_SAL_PROJ_MAS.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
                    + "and  ST_M_SAL_PROJ_MAS.USER_ID= ST_M_DIS_ACT.USER_ID\n"
                    + "and ST_M_SAL_PROJ_MAS.SESSION_ID= ST_M_DIS_ACT.SESSION_ID   \n"
                    + "\n"
                    + "\n";
            if (projSelDTO.isFilterDdlb()) {
                query += " and TT." + projSelDTO.getDefinedContract() + " = '" + projSelDTO.getSelectedCust() + "' ";
            }
            if (!StringUtils.EMPTY.equalsIgnoreCase(projSelDTO.getPivotValue()) && !Constant.NULL.equalsIgnoreCase(projSelDTO.getPivotValue()) && Constant.DISCOUNT_SMALL.equalsIgnoreCase(projSelDTO.getPivotView())) {
                if ("YEAR".equalsIgnoreCase(frequency)) {
                    query += "AND per.YEAR =" + projSelDTO.getPivotValue();
                } else {
                    String str[] = projSelDTO.getPivotValue().split(" ");
                    query += " AND per." + frequency + Constant.EQUAL + str[0] + " and " + "per.YEAR =" + str[1];
                }
            }
            query += "  group by  TT.COMPANY_MASTER_SID,TT.COMPANY_NO,per.YEAR,\n"
                    + "           per." + frequency + "\n"
                    + "\n"
                    + "Union\n"
                    + "\n"
                    + " select per.YEAR AS YEARS,per." + frequency + " AS PERIODS, 0 AS COM_ID ,0 AS CON_ID,0 AS BRAND_ID, TT.COMPANY_MASTER_SID AS COMPANY_MASTER_SID ,TT.COMPANY_NO AS COMPANY_NO,\n"
                    + " 'Customer' as LEVEL_NAME,\n"
                    + "\n"
                    + " sum(ST_M_DIS_Proj.PROJECTION_SALES) As Actual_Sale,\n"
                    + "Coalesce(Sum(ST_M_DIS_Proj.PROJECTION_SALES)/Nullif(Sum(ST_M_PROJ_SAL.PROJECTION_SALES),0),0)*100       AS  Actual_Rate,"
                    + " 'PROJ' As Flag,Coalesce(Sum(ST_M_DIS_Proj.PROJECTION_SALES)/Nullif(Sum(ST_M_PROJ_SAL.PROJECTION_UNITS),0),0) As RPU \n"
                    + "\n"
                    + " FROM PROJECTION_DETAILS PD,\n"
                    + " CCP_DETAILS CCP,\n"
                    + " COMPANY_MASTER TT,\n"
                    + " ST_M_SUPPLEMENTAL_DISC_PROJ ST_M_DIS_Proj,\n"
                    + " ST_M_SALES_PROJECTION       ST_M_PROJ_SAL,\n"
                    + "\n"
                    + "ST_M_SALES_PROJECTION_MASTER     ST_M_SAL_PROJ_MAS,  \n"
                    + " Period per\n"
                    + "where  CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                    + " and TT.COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID\n"
                    + "  and ST_M_DIS_Proj.USER_ID =" + projSelDTO.getUserId() + " \n"
                    + "  and ST_M_DIS_Proj.SESSION_ID =" + projSelDTO.getSessionId() + "\n"
                    + "  and PD.PROJECTION_MASTER_SID =" + projSelDTO.getProjectionId() + "\n"
                    + "  and  ST_M_DIS_Proj.PERIOD_SID =Per.PERIOD_SID\n"
                    + "and ST_M_DIS_Proj.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
                    + "and ST_M_PROJ_SAL.PROJECTION_DETAILS_SID=ST_M_DIS_Proj.PROJECTION_DETAILS_SID\n"
                    + "and  ST_M_PROJ_SAL.USER_ID= ST_M_DIS_Proj.USER_ID\n"
                    + "and ST_M_PROJ_SAL.SESSION_ID= ST_M_DIS_Proj.SESSION_ID\n"
                    + "and ST_M_PROJ_SAL.PERIOD_SID=Per.PERIOD_SID\n"
                    + "\n"
                    + "and ST_M_SAL_PROJ_MAS.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
                    + "and  ST_M_SAL_PROJ_MAS.USER_ID= ST_M_DIS_Proj.USER_ID\n"
                    + "and ST_M_SAL_PROJ_MAS.SESSION_ID= ST_M_DIS_Proj.SESSION_ID  \n";
            if (projSelDTO.isFilterDdlb()) {
                query += " and TT." + projSelDTO.getDefinedContract() + " = '" + projSelDTO.getSelectedCust() + "' ";
            }
            if (!StringUtils.EMPTY.equalsIgnoreCase(projSelDTO.getPivotValue()) && !Constant.NULL.equalsIgnoreCase(projSelDTO.getPivotValue()) && Constant.DISCOUNT_SMALL.equalsIgnoreCase(projSelDTO.getPivotView())) {
                if ("YEAR".equalsIgnoreCase(frequency)) {
                    query += "AND per.YEAR =" + projSelDTO.getPivotValue();
                } else {
                    String str[] = projSelDTO.getPivotValue().split(" ");
                    query += " AND per." + frequency + Constant.EQUAL + str[0] + " and " + "per.YEAR =" + str[1];
                }
            }
            query += " group by  TT.COMPANY_MASTER_SID,TT.COMPANY_NO,per.YEAR,\n"
                    + "           per." + frequency + "\n"
                    + "\n"
                    + "  "
                    + ") Q) FINALQ\n"
                    + "WHERE  FINALQ.TEMP_INDEX > " + projSelDTO.getStart() + " AND FINALQ.TEMP_INDEX <=  " + (projSelDTO.getStart() + projSelDTO.getOffset()) + "\n"
                    + "\n"
                    + " order by COMPANY_MASTER_SID,YEARS,PERIODS";

            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            list = (List) salesProjectionDAO.executeSelectQuery(query);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        return list;
    }

    public List getSuppContractLevelValue(ProjectionSelectionDTO projSelDTO) {
        List list = new ArrayList();
        String query = StringUtils.EMPTY;
        String frequency = projSelDTO.getFrequency();
        if (frequency.equals(Constant.QUARTERLY)) {
            frequency = "QUARTER";
        }
        if (frequency.equals(ANNUALLY.getConstant())) {
            frequency = "YEAR";
        }
        if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
            frequency = "SEMI_ANNUAL";

        }
        if (frequency.equals(MONTHLY.getConstant())) {
            frequency = "MONTH";
        }
        try {
            if (!projSelDTO.getCurrentCustomer().equals(Constant.NULL)) {
                query = "select FINALQ.YEARS,FINALQ.PERIODS,FINALQ.COM_ID,FINALQ.CON_ID,FINALQ.BRAND_ID, FINALQ.CONTRACT_MASTER_SID,FINALQ.CONTRACT_NO,\n"
                        + "    FINALQ.LEVEL_NAME,FINALQ.Actual_Sale,FINALQ.Actual_Rate,FINALQ.Flag,FINALQ.RPU from(\n"
                        + " select Q.YEARS,Q.PERIODS,Q.COM_ID,Q.CON_ID,Q.BRAND_ID, Q.CONTRACT_MASTER_SID,Q.CONTRACT_NO,\n"
                        + "   Q.LEVEL_NAME,Q.Actual_Sale,Q.Actual_Rate,Q.Flag,Q.RPU,Dense_rank() OVER (ORDER BY Q.CONTRACT_MASTER_SID) AS TEMP_INDEX from("
                        + "select per.YEAR AS YEARS,per." + frequency + " AS PERIODS," + projSelDTO.getCurrentCustomer() + " AS COM_ID,0 AS CON_ID,0 AS BRAND_ID, TT.CONTRACT_MASTER_SID AS CONTRACT_MASTER_SID ,TT.CONTRACT_NO AS CONTRACT_NO,\n"
                        + " 'Contract' as LEVEL_NAME,\n"
                        + "SUM(ST_M_DIS_ACT.ACTUAL_SALES) As Actual_Sale,\n"
                        + "Coalesce(Sum(ST_M_DIS_ACT.ACTUAL_SALES)/Nullif(Sum(ST_M_ACT_SAL.ACTUAL_SALES),0),0)*100       AS  Actual_Rate,"
                        + "'ACT' As Flag,Coalesce(Sum(ST_M_DIS_ACT.ACTUAL_SALES)/Nullif(Sum(ST_M_ACT_SAL.ACTUAL_UNITS),0),0) As RPU \n"
                        + " FROM \n"
                        + "PROJECTION_DETAILS PD,\n"
                        + " CCP_DETAILS CCP,\n"
                        + " CONTRACT_MASTER TT,\n"
                        + " ST_M_SUPPLEMENTAL_DISC_ACTUALS ST_M_DIS_ACT,\n"
                        + " ST_M_ACTUAL_SALES               ST_M_ACT_SAL,\n"
                        + "\n"
                        + "ST_M_SALES_PROJECTION_MASTER     ST_M_SAL_PROJ_MAS,  \n"
                        + " Period per\n"
                        + "where  CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                        + "  and TT.CONTRACT_MASTER_SID = CCP.CONTRACT_MASTER_SID\n"
                        + "  and CCP.COMPANY_MASTER_SID = " + projSelDTO.getCurrentCustomer() + "  \n"
                        + "and ST_M_DIS_ACT.USER_ID =" + projSelDTO.getUserId() + " \n"
                        + "  and ST_M_DIS_ACT.SESSION_ID =" + projSelDTO.getSessionId() + "\n"
                        + " and PD.PROJECTION_MASTER_SID =" + projSelDTO.getProjectionId() + "\n"
                        + "  and  ST_M_DIS_ACT.PERIOD_SID =Per.PERIOD_SID\n"
                        + "and ST_M_DIS_ACT.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
                        + "and ST_M_ACT_SAL.PROJECTION_DETAILS_SID=ST_M_DIS_ACT.PROJECTION_DETAILS_SID\n"
                        + "and  ST_M_ACT_SAL.USER_ID= ST_M_DIS_ACT.USER_ID\n"
                        + "and ST_M_ACT_SAL.SESSION_ID= ST_M_DIS_ACT.SESSION_ID\n"
                        + "and ST_M_ACT_SAL.PERIOD_SID=Per.PERIOD_SID\n"
                        + "\n"
                        + "and ST_M_SAL_PROJ_MAS.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
                        + "and  ST_M_SAL_PROJ_MAS.USER_ID= ST_M_DIS_ACT.USER_ID\n"
                        + "and ST_M_SAL_PROJ_MAS.SESSION_ID= ST_M_DIS_ACT.SESSION_ID   \n"
                        + "\n";
                if (!StringUtils.EMPTY.equalsIgnoreCase(projSelDTO.getPivotValue()) && !Constant.NULL.equalsIgnoreCase(projSelDTO.getPivotValue()) && Constant.DISCOUNT_SMALL.equalsIgnoreCase(projSelDTO.getPivotView())) {
                    if ("YEAR".equalsIgnoreCase(frequency)) {
                        query += "AND per.YEAR =" + projSelDTO.getPivotValue();
                    } else {
                        String str[] = projSelDTO.getPivotValue().split(" ");
                        query += " AND per." + frequency + Constant.EQUAL + str[0] + " and " + "per.YEAR =" + str[1];
                    }
                }
                query += "group by  TT.CONTRACT_MASTER_SID,TT.CONTRACT_NO, per.YEAR,per." + frequency + "\n"
                        + "\n"
                        + "Union\n"
                        + "select per.YEAR AS YEARS,per." + frequency + " AS PERIODS," + projSelDTO.getCurrentCustomer() + " AS COM_ID,0 AS CON_ID,0 AS BRAND_ID, TT.CONTRACT_MASTER_SID AS CONTRACT_MASTER_SID ,TT.CONTRACT_NO AS CONTRACT_NO,\n"
                        + " 'Contract' as LEVEL_NAME,\n"
                        + "SUM(ST_M_DIS_PROJ.PROJECTION_SALES) As Actual_Sale,\n"
                        + "Coalesce(Sum(ST_M_DIS_Proj.PROJECTION_SALES)/Nullif(Sum(ST_M_PROJ_SAL.PROJECTION_SALES),0),0)*100       AS  Proj_rate,"
                        + "'PROJ' As Flag,Coalesce(Sum(ST_M_DIS_Proj.PROJECTION_SALES)/Nullif(Sum(ST_M_PROJ_SAL.PROJECTION_UNITS),0),0) As RPU \n"
                        + " FROM \n"
                        + "PROJECTION_DETAILS PD,\n"
                        + " CCP_DETAILS CCP,\n"
                        + " CONTRACT_MASTER TT,\n"
                        + " ST_M_SUPPLEMENTAL_DISC_PROJ ST_M_DIS_PROJ,\n"
                        + " ST_M_SALES_PROJECTION               ST_M_PROJ_SAL,\n"
                        + "\n"
                        + "ST_M_SALES_PROJECTION_MASTER     ST_M_SAL_PROJ_MAS,  \n"
                        + " Period per\n"
                        + "where  CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                        + "  and TT.CONTRACT_MASTER_SID = CCP.CONTRACT_MASTER_SID\n"
                        + "  and CCP.COMPANY_MASTER_SID = " + projSelDTO.getCurrentCustomer() + "  \n"
                        + "and ST_M_DIS_PROJ.USER_ID =" + projSelDTO.getUserId() + " \n"
                        + "  and ST_M_DIS_PROJ.SESSION_ID =" + projSelDTO.getSessionId() + "\n"
                        + " and PD.PROJECTION_MASTER_SID =" + projSelDTO.getProjectionId() + "\n"
                        + "  and  ST_M_DIS_PROJ.PERIOD_SID =Per.PERIOD_SID\n"
                        + "and ST_M_DIS_PROJ.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
                        + "and ST_M_PROJ_SAL.PROJECTION_DETAILS_SID=ST_M_DIS_PROJ.PROJECTION_DETAILS_SID\n"
                        + "and  ST_M_PROJ_SAL.USER_ID= ST_M_DIS_PROJ.USER_ID\n"
                        + "and ST_M_PROJ_SAL.SESSION_ID= ST_M_DIS_PROJ.SESSION_ID\n"
                        + "and ST_M_PROJ_SAL.PERIOD_SID=Per.PERIOD_SID\n"
                        + "\n"
                        + "and ST_M_SAL_PROJ_MAS.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
                        + "and  ST_M_SAL_PROJ_MAS.USER_ID= ST_M_DIS_PROJ.USER_ID\n"
                        + "and ST_M_SAL_PROJ_MAS.SESSION_ID= ST_M_DIS_PROJ.SESSION_ID   \n"
                        + "\n";
                if (!StringUtils.EMPTY.equalsIgnoreCase(projSelDTO.getPivotValue()) && !Constant.NULL.equalsIgnoreCase(projSelDTO.getPivotValue()) && Constant.DISCOUNT_SMALL.equalsIgnoreCase(projSelDTO.getPivotView())) {
                    if ("YEAR".equalsIgnoreCase(frequency)) {
                        query += "AND per.YEAR =" + projSelDTO.getPivotValue();
                    } else {
                        String str[] = projSelDTO.getPivotValue().split(" ");
                        query += " AND per." + frequency + Constant.EQUAL + str[0] + " and " + "per.YEAR =" + str[1];
                    }
                }

                query += "group by  TT.CONTRACT_MASTER_SID,TT.CONTRACT_NO, per.YEAR,per." + frequency + "\n"
                        + "\n"
                        + StringUtils.EMPTY
                        + ") Q) FINALQ\n"
                        + "WHERE  FINALQ.TEMP_INDEX > " + projSelDTO.getStart() + " AND FINALQ.TEMP_INDEX <=  " + (projSelDTO.getStart() + projSelDTO.getOffset()) + "\n"
                        + "\n"
                        + " order by CONTRACT_MASTER_SID,YEARS,PERIODS";

            }
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            list = (List) salesProjectionDAO.executeSelectQuery(query);
            return list;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }

    }

    public List getSuppBrandLevelValue(ProjectionSelectionDTO projSelDTO) {
        List list = new ArrayList();
        String query = StringUtils.EMPTY;
        String frequency = projSelDTO.getFrequency();
        if (frequency.equals(Constant.QUARTERLY)) {
            frequency = "QUARTER";
        }
        if (frequency.equals(ANNUALLY.getConstant())) {
            frequency = "YEAR";
        }
        if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
            frequency = "SEMI_ANNUAL";

        }
        if (frequency.equals(MONTHLY.getConstant())) {
            frequency = "MONTH";
        }
        try {
            if (!Constant.NULL.equals(projSelDTO.getCurrentContract()) && !Constant.NULL.equals(projSelDTO.getCurrentCustomer())) {
                query = "select FINALQ.YEARS,FINALQ.PERIODS,FINALQ.COM_ID,FINALQ.CON_ID,FINALQ.BRAND_ID, FINALQ.BRAND_MASTER_SID,FINALQ.BRAND_NAME,\n"
                        + "   FINALQ.LEVEL_NAME,FINALQ.Actual_Sale,FINALQ.Actual_Rate,FINALQ.Flag,FINALQ.RPU from(\n"
                        + "select Q.YEARS,Q.PERIODS,Q.COM_ID,Q.CON_ID,Q.BRAND_ID, Q.BRAND_MASTER_SID,Q.BRAND_NAME,\n"
                        + "   Q.LEVEL_NAME,Q.Actual_Sale,Q.Actual_Rate,Q.Flag,Q.RPU,Dense_rank() OVER (ORDER BY Q.BRAND_MASTER_SID) AS TEMP_INDEX from("
                        + "select per.YEAR AS YEARS,per." + frequency + " AS PERIODS ," + projSelDTO.getCurrentCustomer() + " AS COM_ID," + projSelDTO.getCurrentContract() + " AS CON_ID,0 AS BRAND_ID,TT.BRAND_MASTER_SID AS BRAND_MASTER_SID ,TT.BRAND_NAME AS BRAND_NAME,\n"
                        + "'Brand' as LEVEL_NAME,\n"
                        + "sum(ST_M_DIS_ACT.ACTUAL_SALES)AS Actual_Sale,\n"
                        + "Coalesce(Sum(ST_M_DIS_ACT.ACTUAL_SALES)/Nullif(Sum(ST_M_ACT_SAL.ACTUAL_SALES),0),0)*100       AS  Actual_Rate,"
                        + "'ACT' AS Flag,Coalesce(Sum(ST_M_DIS_ACT.ACTUAL_SALES)/Nullif(Sum(ST_M_ACT_SAL.ACTUAL_UNITS),0),0) AS RPU \n"
                        + "\n"
                        + "FROM PROJECTION_DETAILS PD,\n"
                        + "CCP_DETAILS CCP,\n"
                        + "ITEM_MASTER IM,\n"
                        + " BRAND_MASTER TT,\n"
                        + "ST_M_SUPPLEMENTAL_DISC_ACTUALS ST_M_DIS_ACT,\n"
                        + " ST_M_ACTUAL_SALES               ST_M_ACT_SAL,\n"
                        + "\n"
                        + "ST_M_SALES_PROJECTION_MASTER     ST_M_SAL_PROJ_MAS,\n"
                        + " Period per\n"
                        + " where  CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                        + "and TT.BRAND_MASTER_SID = IM.BRAND_MASTER_SID\n"
                        + "and ST_M_DIS_ACT.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n"
                        + "and CCP.ITEM_MASTER_SID = IM.ITEM_MASTER_SID\n"
                        + "and per.PERIOD_SID=ST_M_DIS_ACT.PERIOD_SID\n"
                        + "and CCP.COMPANY_MASTER_SID = " + projSelDTO.getCurrentCustomer() + " \n"
                        + "and CCP.CONTRACT_MASTER_SID = " + projSelDTO.getCurrentContract() + "\n"
                        + "and ST_M_DIS_ACT.USER_ID =" + projSelDTO.getUserId() + "\n"
                        + "  and ST_M_DIS_ACT.SESSION_ID =" + projSelDTO.getSessionId() + "\n"
                        + "  and PD.PROJECTION_MASTER_SID =" + projSelDTO.getProjectionId() + "\n"
                        + "and ST_M_DIS_ACT.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
                        + "and ST_M_ACT_SAL.PROJECTION_DETAILS_SID=ST_M_DIS_ACT.PROJECTION_DETAILS_SID\n"
                        + "and  ST_M_ACT_SAL.USER_ID= ST_M_DIS_ACT.USER_ID\n"
                        + "and ST_M_ACT_SAL.SESSION_ID= ST_M_DIS_ACT.SESSION_ID\n"
                        + "and ST_M_ACT_SAL.PERIOD_SID=Per.PERIOD_SID\n"
                        + "\n"
                        + "and ST_M_SAL_PROJ_MAS.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
                        + "and  ST_M_SAL_PROJ_MAS.USER_ID= ST_M_DIS_ACT.USER_ID\n"
                        + "and ST_M_SAL_PROJ_MAS.SESSION_ID= ST_M_DIS_ACT.SESSION_ID\n";
                if (!StringUtils.EMPTY.equalsIgnoreCase(projSelDTO.getPivotValue()) && !Constant.NULL.equalsIgnoreCase(projSelDTO.getPivotValue()) && Constant.DISCOUNT_SMALL.equalsIgnoreCase(projSelDTO.getPivotView())) {
                    if ("YEAR".equalsIgnoreCase(frequency)) {
                        query += "AND per.YEAR =" + projSelDTO.getPivotValue();
                    } else {
                        String str[] = projSelDTO.getPivotValue().split(" ");
                        query += " AND per." + frequency + Constant.EQUAL + str[0] + " and " + "per.YEAR =" + str[1];
                    }
                }
                query += "  group by TT.BRAND_MASTER_SID,TT.BRAND_NAME, per.YEAR,per." + frequency + "\n"
                        + "\n"
                        + "\n"
                        + "  union\n"
                        + "\n"
                        + "select per.YEAR AS YEARS,per." + frequency + " AS PERIODS ," + projSelDTO.getCurrentCustomer() + " AS COM_ID," + projSelDTO.getCurrentContract() + " AS CON_ID,0 AS BRAND_ID,TT.BRAND_MASTER_SID AS BRAND_MASTER_SID ,TT.BRAND_NAME AS BRAND_NAME,\n"
                        + "'Brand' as LEVEL_NAME,\n"
                        + "sum(ST_M_DIS_ACT.PROJECTION_SALES)AS Actual_Sale,\n"
                        + "Coalesce(Sum(ST_M_DIS_ACT.PROJECTION_SALES)/Nullif(Sum(ST_M_ACT_SAL.PROJECTION_SALES),0),0)*100       AS  Proj_rate,"
                        + "'Proj' AS Flag,Coalesce(Sum(ST_M_DIS_ACT.PROJECTION_SALES)/Nullif(Sum(ST_M_ACT_SAL.PROJECTION_UNITS),0),0) AS RPU \n"
                        + "\n"
                        + "FROM PROJECTION_DETAILS PD,\n"
                        + "CCP_DETAILS CCP,\n"
                        + "ITEM_MASTER IM,\n"
                        + " BRAND_MASTER TT,\n"
                        + "ST_M_SUPPLEMENTAL_DISC_PROJ ST_M_DIS_ACT,\n"
                        + " ST_M_SALES_PROJECTION               ST_M_ACT_SAL,\n"
                        + "\n"
                        + "ST_M_SALES_PROJECTION_MASTER     ST_M_SAL_PROJ_MAS,\n"
                        + " Period per\n"
                        + " where  CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                        + "and TT.BRAND_MASTER_SID = IM.BRAND_MASTER_SID\n"
                        + "and ST_M_DIS_ACT.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n"
                        + "and CCP.ITEM_MASTER_SID = IM.ITEM_MASTER_SID\n"
                        + "and per.PERIOD_SID=ST_M_DIS_ACT.PERIOD_SID\n"
                        + "and CCP.COMPANY_MASTER_SID = " + projSelDTO.getCurrentCustomer() + " \n"
                        + "and CCP.CONTRACT_MASTER_SID = " + projSelDTO.getCurrentContract() + "\n"
                        + "and ST_M_DIS_ACT.USER_ID =" + projSelDTO.getUserId() + "\n"
                        + "  and ST_M_DIS_ACT.SESSION_ID =" + projSelDTO.getSessionId() + "\n"
                        + "  and PD.PROJECTION_MASTER_SID =" + projSelDTO.getProjectionId() + "\n"
                        + "and ST_M_DIS_ACT.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
                        + "and ST_M_ACT_SAL.PROJECTION_DETAILS_SID=ST_M_DIS_ACT.PROJECTION_DETAILS_SID\n"
                        + "and  ST_M_ACT_SAL.USER_ID= ST_M_DIS_ACT.USER_ID\n"
                        + "and ST_M_ACT_SAL.SESSION_ID= ST_M_DIS_ACT.SESSION_ID\n"
                        + "and ST_M_ACT_SAL.PERIOD_SID=Per.PERIOD_SID\n"
                        + "\n"
                        + "and ST_M_SAL_PROJ_MAS.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
                        + "and  ST_M_SAL_PROJ_MAS.USER_ID= ST_M_DIS_ACT.USER_ID\n"
                        + "and ST_M_SAL_PROJ_MAS.SESSION_ID= ST_M_DIS_ACT.SESSION_ID\n";
                if (!StringUtils.EMPTY.equalsIgnoreCase(projSelDTO.getPivotValue()) && !Constant.NULL.equalsIgnoreCase(projSelDTO.getPivotValue()) && Constant.DISCOUNT_SMALL.equalsIgnoreCase(projSelDTO.getPivotView())) {
                    if ("YEAR".equalsIgnoreCase(frequency)) {
                        query += "AND per.YEAR =" + projSelDTO.getPivotValue();
                    } else {
                        String str[] = projSelDTO.getPivotValue().split(" ");
                        query += " AND per." + frequency + Constant.EQUAL + str[0] + " and " + "per.YEAR =" + str[1];
                    }
                }

                query += "  group by TT.BRAND_MASTER_SID,TT.BRAND_NAME, per.YEAR,per." + frequency + " \n"
                        + "\n"
                        + "  \n"
                        + "  "
                        + ") Q) FINALQ\n"
                        + "WHERE  FINALQ.TEMP_INDEX > " + projSelDTO.getStart() + " AND FINALQ.TEMP_INDEX <=  " + (projSelDTO.getStart() + projSelDTO.getOffset()) + "\n"
                        + "\n"
                        + " order by BRAND_MASTER_SID,YEARS,PERIODS";
                SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
                list = (List) salesProjectionDAO.executeSelectQuery(query);

            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        return list;
    }

    public List<Integer> getProjectionDetailsId(int projectionId) {
        try {
            List list = new ArrayList();
            List<Integer> listInte = new ArrayList<Integer>();
            String sql = "select PROJECTION_DETAILS_SID from dbo.PROJECTION_DETAILS\n"
                    + "where PROJECTION_MASTER_SID=" + projectionId;

            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            list = (List) salesProjectionDAO.executeSelectQuery(sql);
            for (int i = 0; i < list.size(); i++) {
                listInte.add(Integer.valueOf(String.valueOf(list.get(i))));

            }

            return listInte;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return null;
        }
    }

    public List getMandatedPivotLevel(ProjectionSelectionDTO projSelDTO) {
        List list = new ArrayList();
        String query = StringUtils.EMPTY;
        String frequency = projSelDTO.getFrequency();
        if (frequency.equals(Constant.QUARTERLY)) {
            frequency = "QUARTER";
        }
        if (frequency.equals(ANNUALLY.getConstant())) {
            frequency = "YEAR";
        }
        if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
            frequency = "SEMI_ANNUAL";

        }
        if (frequency.equals(MONTHLY.getConstant())) {
            frequency = "MONTH";
        }

        try {
            query += " select per.YEAR,per." + frequency + ", 0 AS COM_ID,0 AS CON_ID,0 AS BRAND_ID,0 As COMPANY_MASTER_SID,0 AS COMPANY_NO,  "
                    + " 'Customer' as LEVEL_NAME,\n"
                    + "\n"
                    + " sum(SUPMAS.ACTUAL_SALES) As Actual_Sale,\n"
                    + "Coalesce(Sum(SUPMAS.ACTUAL_SALES)/Nullif(Sum(STMSP.ACTUAL_SALES),0),0)*100       AS ACTUAL_Rate,\n"
                    + " 'ACT' As Flag,Coalesce(Sum(SUPMAS.ACTUAL_SALES)/Nullif(Sum(STMSP.ACTUAL_UNITS),0),0)  As RPU  \n"
                    + "\n"
                    + " FROM PROJECTION_DETAILS PD,\n"
                    + " CCP_DETAILS CCP,\n"
                    + " COMPANY_MASTER TT,\n"
                    + " M_ACTUAL_DISCOUNT SUPMAS,\n"
                    + "ST_M_ACTUAL_SALES STMSP,\n"
                    + "ST_M_SALES_PROJECTION_MASTER STMSPM,\n"
                    + " Period per\n"
                    + "where  CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                    + " and TT.COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID\n"
                    + "  and SUPMAS.USER_ID =" + projSelDTO.getUserId() + " \n"
                    + "  and SUPMAS.SESSION_ID =" + projSelDTO.getSessionId() + "\n"
                    + "  and PD.PROJECTION_MASTER_SID =" + projSelDTO.getProjectionId() + "\n"
                    + "  and Per.PERIOD_SID = SUPMAS.PERIOD_SID\n"
                    + "  and SUPMAS.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID"
                    + "  and STMSP.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
                    + "and STMSP.PERIOD_SID=Per.PERIOD_SID \n"
                    + "and STMSP.USER_ID=SUPMAS.USER_ID\n"
                    + "and STMSP.SESSION_ID=SUPMAS.SESSION_ID\n"
                    + " and STMSPM.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
                    + "and STMSPM.USER_ID=SUPMAS.USER_ID\n"
                    + "and STMSPM.SESSION_ID=SUPMAS.SESSION_ID"
                    + "  group by  "
                    + "per.YEAR,\n"
                    + "           per." + frequency + "\n"
                    + "\n"
                    + "UNION\n"
                    + "\n"
                    + " select per.YEAR,per." + frequency + ", 0 AS COM_ID,0 AS CON_ID,0 AS BRAND_ID,0 As COMPANY_MASTER_SID,0 AS COMPANY_NO,"
                    + " 'Customer' as LEVEL_NAME,\n"
                    + "\n"
                    + " sum(SUPMAS.PROJECTION_SALES) As Proj_Sale,\n"
                    + "Coalesce(Sum(SUPMAS.PROJECTION_SALES)/Nullif(Sum(STMSP.PROJECTION_SALES),0),0)*100       AS PROJ_Rate,\n"
                    + " 'Proj' As Flag,Coalesce(Sum(SUPMAS.PROJECTION_SALES)/Nullif(Sum(STMSP.PROJECTION_UNITS),0),0) AS RPU   \n"
                    + "\n"
                    + " FROM PROJECTION_DETAILS PD,\n"
                    + " CCP_DETAILS CCP,\n"
                    + " COMPANY_MASTER TT,\n"
                    + " M_DISCOUNT_PROJECTION SUPMAS,\n"
                    + "ST_M_SALES_PROJECTION STMSP,\n"
                    + "ST_M_SALES_PROJECTION_MASTER STMSPM,\n"
                    + " Period per\n"
                    + "where  CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                    + " and TT.COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID\n"
                    + " and SUPMAS.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID "
                    + "  and SUPMAS.USER_ID =" + projSelDTO.getUserId() + " and SUPMAS.SESSION_ID =" + projSelDTO.getSessionId() + "and PD.PROJECTION_MASTER_SID =" + projSelDTO.getProjectionId() + "\n"
                    + "  and Per.PERIOD_SID = SUPMAS.PERIOD_SID\n"
                    + "and STMSP.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
                    + "and STMSP.PERIOD_SID=Per.PERIOD_SID \n"
                    + "and STMSP.USER_ID=SUPMAS.USER_ID\n"
                    + "and STMSP.SESSION_ID=SUPMAS.SESSION_ID\n"
                    + " and STMSPM.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
                    + "-- and STMSP.PERIOD_SID=Per.PERIOD_SID \n"
                    + "and STMSPM.USER_ID=SUPMAS.USER_ID\n"
                    + "and STMSPM.SESSION_ID=SUPMAS.SESSION_ID "
                    + "  group by  "
                    + "per.YEAR,\n"
                    + "           per." + frequency + "\n"
                    + "\n"
                    + "  ";
            if (!frequency.equals("YEAR")) {
                query += "order by "
                        + "per.YEAR,per." + frequency + "\n";
            } else {
                query += "order by "
                        + "per.YEAR";
            }

            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            list = (List) salesProjectionDAO.executeSelectQuery(query);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        return list;
    }

    public List getSuppPivotLevelValue(ProjectionSelectionDTO projSelDTO) {
        List list = new ArrayList();
        String query = StringUtils.EMPTY;
        String frequency = projSelDTO.getFrequency();
        if (frequency.equals(Constant.QUARTERLY)) {
            frequency = "QUARTER";
        }
        if (frequency.equals(ANNUALLY.getConstant())) {
            frequency = "YEAR";
        }
        if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
            frequency = "SEMI_ANNUAL";

        }
        if (frequency.equals(MONTHLY.getConstant())) {
            frequency = "MONTH";
        }

        try {
            query += " select per.YEAR,per." + frequency + ", 0 AS COM_ID,0 AS CON_ID,0 AS BRAND_ID,0 As COMPANY_MASTER_SID,0 AS COMPANY_NO,"
                    + " 'Customer' as LEVEL_NAME,\n"
                    + "\n"
                    + " sum(ST_M_DIS_ACT.ACTUAL_SALES) As Actual_Sale,\n"
                    + "Coalesce(Sum(ST_M_DIS_ACT.ACTUAL_SALES)/Nullif(Sum(ST_M_ACT_SAL.ACTUAL_SALES),0),0)*100       AS ACTUAL_Rate,\n"
                    + " 'ACT' As Flag,Coalesce(Sum(ST_M_DIS_ACT.ACTUAL_SALES)/Nullif(Sum(ST_M_ACT_SAL.ACTUAL_UNITS),0),0) As RPU    \n"
                    + "\n"
                    + " FROM PROJECTION_DETAILS PD,\n"
                    + " CCP_DETAILS CCP,\n"
                    + " COMPANY_MASTER TT,\n"
                    + " ST_M_SUPPLEMENTAL_DISC_ACTUALS ST_M_DIS_ACT,\n"
                    + " ST_M_ACTUAL_SALES               ST_M_ACT_SAL,\n"
                    + "\n"
                    + "ST_M_SALES_PROJECTION_MASTER     ST_M_SAL_PROJ_MAS,  \n"
                    + " Period per\n"
                    + "where  CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                    + " and TT.COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID\n"
                    + "  and ST_M_DIS_ACT.USER_ID =" + projSelDTO.getUserId() + " \n"
                    + "  and ST_M_DIS_ACT.SESSION_ID =" + projSelDTO.getSessionId() + "\n"
                    + "  and PD.PROJECTION_MASTER_SID =" + projSelDTO.getProjectionId() + "\n"
                    + "  and  ST_M_DIS_ACT.PERIOD_SID =Per.PERIOD_SID\n"
                    + "and ST_M_DIS_ACT.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
                    + "and ST_M_ACT_SAL.PROJECTION_DETAILS_SID=ST_M_DIS_ACT.PROJECTION_DETAILS_SID\n"
                    + "and  ST_M_ACT_SAL.USER_ID= ST_M_DIS_ACT.USER_ID\n"
                    + "and ST_M_ACT_SAL.SESSION_ID= ST_M_DIS_ACT.SESSION_ID\n"
                    + "and ST_M_ACT_SAL.PERIOD_SID=Per.PERIOD_SID\n"
                    + "\n"
                    + "and ST_M_SAL_PROJ_MAS.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
                    + "and  ST_M_SAL_PROJ_MAS.USER_ID= ST_M_DIS_ACT.USER_ID\n"
                    + "and ST_M_SAL_PROJ_MAS.SESSION_ID= ST_M_DIS_ACT.SESSION_ID   \n"
                    + "\n"
                    + "\n"
                    + "  group by  "
                    + "per.YEAR,\n"
                    + "           per." + frequency + "\n"
                    + "\n"
                    + "Union\n"
                    + "\n"
                    + "select per.YEAR,per." + frequency + ", 0 AS COM_ID,0 AS CON_ID,0 AS BRAND_ID,0 As COMPANY_MASTER_SID,0 AS COMPANY_NO, "
                    + " 'Customer' as LEVEL_NAME,\n"
                    + "\n"
                    + " sum(ST_M_DIS_Proj.PROJECTION_SALES) As Proj_Sale,\n"
                    + "Coalesce(Sum(ST_M_DIS_Proj.PROJECTION_SALES)/Nullif(Sum(ST_M_PROJ_SAL.PROJECTION_SALES),0),0)*100       AS PROJ_Rate,\n"
                    + " 'PROJ' As Flag,Coalesce(Sum(ST_M_DIS_Proj.PROJECTION_SALES)/Nullif(Sum(ST_M_PROJ_SAL.PROJECTION_UNITS),0),0) As RPU  \n"
                    + "\n"
                    + " FROM PROJECTION_DETAILS PD,\n"
                    + " CCP_DETAILS CCP,\n"
                    + " COMPANY_MASTER TT,\n"
                    + " ST_M_SUPPLEMENTAL_DISC_PROJ ST_M_DIS_Proj,\n"
                    + " ST_M_SALES_PROJECTION       ST_M_PROJ_SAL,\n"
                    + "\n"
                    + "ST_M_SALES_PROJECTION_MASTER     ST_M_SAL_PROJ_MAS,  \n"
                    + " Period per\n"
                    + "where  CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                    + " and TT.COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID\n"
                    + "  and ST_M_DIS_Proj.USER_ID =" + projSelDTO.getUserId() + " \n"
                    + "  and ST_M_DIS_Proj.SESSION_ID =" + projSelDTO.getSessionId() + "\n"
                    + "  and PD.PROJECTION_MASTER_SID =" + projSelDTO.getProjectionId() + "\n"
                    + "  and  ST_M_DIS_Proj.PERIOD_SID =Per.PERIOD_SID\n"
                    + "and ST_M_DIS_Proj.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
                    + "and ST_M_PROJ_SAL.PROJECTION_DETAILS_SID=ST_M_DIS_Proj.PROJECTION_DETAILS_SID\n"
                    + "and  ST_M_PROJ_SAL.USER_ID= ST_M_DIS_Proj.USER_ID\n"
                    + "and ST_M_PROJ_SAL.SESSION_ID= ST_M_DIS_Proj.SESSION_ID\n"
                    + "and ST_M_PROJ_SAL.PERIOD_SID=Per.PERIOD_SID\n"
                    + "\n"
                    + "and ST_M_SAL_PROJ_MAS.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
                    + "and  ST_M_SAL_PROJ_MAS.USER_ID= ST_M_DIS_Proj.USER_ID\n"
                    + "and ST_M_SAL_PROJ_MAS.SESSION_ID= ST_M_DIS_Proj.SESSION_ID  \n"
                    + " group by  "
                    + "per.YEAR,\n"
                    + "           per." + frequency + "\n"
                    + "\n"
                    + "  ";
            if (!frequency.equals("YEAR")) {
                query += "order by "
                        + "per.YEAR,per." + frequency + "\n";
            } else {
                query += "order by "
                        + "per.YEAR";
            }
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            list = (List) salesProjectionDAO.executeSelectQuery(query);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        return list;
    }

    public int getCount(DiscountProjectionResultsDTO parentDto, ProjectionSelectionDTO projSelDTO, boolean brandFlag) {
        int count = 0;
        try {
            StringBuilder query = new StringBuilder();

            String tempFrom = StringUtils.EMPTY;
            String tempSelect = StringUtils.EMPTY;
            String tempWerQuery = StringUtils.EMPTY;
            if (Constant.CUSTOMER_SMALL.equals(parentDto.getSupplementalLevelName())) {
                tempFrom = "COMPANY_MASTER CM";
                tempSelect = "select Count(distinct CM.COMPANY_NO)";
                tempWerQuery = " AND CM.COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID \n";
            } else if (Constant.CONTRACT_SMALL.equals(parentDto.getSupplementalLevelName())) {
                tempFrom = "CONTRACT_MASTER CONTR, COMPANY_MASTER CM ";
                tempSelect = "select Count(distinct CONTR.CONTRACT_NO)";
                tempWerQuery = " AND CONTR.CONTRACT_MASTER_SID = CCP.CONTRACT_MASTER_SID AND  CM.COMPANY_MASTER_SID=CCP.COMPANY_MASTER_SID AND CM.COMPANY_NO ='" + parentDto.getGroup() + "' \n";
            } else if (Constant.BRAND_CAPS.equals(parentDto.getSupplementalLevelName()) || Constant.NON_MANDATED_SUPPLEMENTAL.equals(parentDto.getSupplementalLevelName())) {
                tempFrom = " ITEM_MASTER IM,BRAND_MASTER BM ";
                tempSelect = "select Count(distinct BM.BRAND_NAME)";
                tempWerQuery = " AND IM.ITEM_MASTER_SID = CCP.ITEM_MASTER_SID\n"
                        + "AND BM.BRAND_MASTER_SID = IM.BRAND_MASTER_SID\n";
                if (brandFlag) {
                    tempWerQuery += "and CCP.COMPANY_MASTER_SID = " + parentDto.getCurrentCustomer() + " \n"
                            + "and CCP.CONTRACT_MASTER_SID = " + parentDto.getCurrentContract() + "\n";
                    tempFrom += ",CONTRACT_MASTER CONTR, COMPANY_MASTER CM";
                }
            }

            String fromQuery = " from PROJECTION_DETAILS PD,CCP_DETAILS CCP, ";

            String werQuery = " where CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n";

            String filterQuery = " AND PD.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId();

            query.append(tempSelect + fromQuery + tempFrom + werQuery + tempWerQuery + filterQuery);
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            List<Object> list = (List<Object>) salesProjectionDAO.executeSelectQuery(query.toString());

            if (list != null && !list.isEmpty()) {
                Object ob = list.get(0);
                count = Integer.valueOf(ob.toString());

            }

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return count;
    }

    public static List getNonMandatedTotal(int projectionId, String userId, String sessionId, String freq) {
        String frequency = StringUtils.EMPTY;
        try {
            if (Constant.QUARTERLY.equals(freq)) {
                frequency = "Quarter";
            } else if (SEMI_ANNUALLY.getConstant().equals(freq)) {
                frequency = "Semi_Annual";
            } else if (MONTHLY.getConstant().equals(freq)) {
                frequency = "Month";
            } else if (ANNUALLY.getConstant().equals(freq)) {
                frequency = "YEAR";
            }
            List list = new ArrayList();
            String query = "DECLARE @PROJECTION_MASTER_SID INT=" + projectionId
                    + "DECLARE @USER_ID INT=" + userId
                    + "DECLARE @SESSION_ID INT=" + sessionId
                    + "DECLARE @THERAPEUTIC_CLASS INT\n"
                    + "DECLARE @BRAND INT\n"
                    + "\n"
                    + "\n"
                    + "DECLARE @INPUT_INFO TABLE\n"
                    + "  (\n"
                    + "     PROJECTION_MASTER_SID  INT,\n"
                    + "     PROJECTION_DETAILS_SID INT,\n"
                    + "     ITEM_MASTER_SID        INT,\n"
                    + "     COMPANY_MASTER_SID     INT,\n"
                    + "     BRAND_MASTER_SID       INT,\n"
                    + "     BRAND_NAME             VARCHAR(100),\n"
                    + "     THERAPEUTIC_CLASS      INT,\n"
                    + "     COMPANY_CATEGORY       INT\n"
                    + "  )\n"
                    + "\n"
                    + "INSERT INTO @INPUT_INFO\n"
                    + "SELECT PROJECTION_MASTER_SID,\n"
                    + "       PROJECTION_DETAILS_SID,\n"
                    + "       C.ITEM_MASTER_SID,\n"
                    + "       CM.COMPANY_MASTER_SID,\n"
                    + "       IM.BRAND_MASTER_SID,\n"
                    + "       BRAND_NAME,\n"
                    + "       THERAPEUTIC_CLASS,\n"
                    + "       COMPANY_CATEGORY\n"
                    + "FROM   PROJECTION_DETAILS PD\n"
                    + "       INNER JOIN CCP_DETAILS C\n"
                    + "               ON C.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                    + "       INNER JOIN ITEM_MASTER IM\n"
                    + "               ON IM.ITEM_MASTER_SID = C.ITEM_MASTER_SID\n"
                    + "       INNER JOIN BRAND_MASTER B\n"
                    + "               ON B.BRAND_MASTER_SID = IM.BRAND_MASTER_SID\n"
                    + "       INNER JOIN COMPANY_MASTER CM\n"
                    + "               ON CM.COMPANY_MASTER_SID = C.COMPANY_MASTER_SID\n"
                    + "WHERE  PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID\n"
                    + "\n"
                    + "SELECT PER.YEAR,\n"
                    + "       PER." + frequency + ",\n"
                    + "       SUM(B1.PROJECTION_SALES)                                                   PROJECTION_SALES,\n"
                    + "       'PROJECTION'                                                               AS TYPE,\n"
                    + "       ( COALESCE(SUM(B1.PROJECTION_SALES) / NULLIF(SUM(A1.SALES), 0), 0) * 100 ) AS PROJECTION_RATE,\n"
                    + "       ( COALESCE(SUM(B1.PROJECTION_SALES) / NULLIF(SUM(A1.UNITS), 0), 0) )       AS PROJECTION_RPU\n"
                    + "FROM   (SELECT BRAND_NAME,\n"
                    + "               SUM(SALES) AS SALES,\n"
                    + "               SUM(UNITS) AS UNITS,\n"
                    + "               PERIOD_SID\n"
                    + "        FROM   (SELECT SUM(B.PROJECTION_SALES) AS SALES,\n"
                    + "                       SUM(B.PROJECTION_UNITS) AS UNITS,\n"
                    + "                       PER.PERIOD_SID,\n"
                    + "                       I.BRAND_NAME,\n"
                    + "                       I.BRAND_MASTER_SID,\n"
                    + "                       I.THERAPEUTIC_CLASS\n"
                    + "                FROM   DBO.ST_M_SALES_PROJECTION_MASTER A\n"
                    + "                       JOIN DBO.ST_M_SALES_PROJECTION B\n"
                    + "                         ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID\n"
                    + "                            AND A.USER_ID = B.USER_ID\n"
                    + "                            AND A.SESSION_ID = B.SESSION_ID\n"
                    + "                       JOIN @INPUT_INFO I\n"
                    + "                         ON I.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID\n"
                    + "                       JOIN PERIOD PER\n"
                    + "                         ON PER.PERIOD_SID = B.PERIOD_SID\n"
                    + "                WHERE  I.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID\n"
                    + "                       AND A.USER_ID = @USER_ID\n"
                    + "                       AND A.SESSION_ID = @SESSION_ID\n"
                    + "                GROUP  BY PER.PERIOD_SID,\n"
                    + "                          I.BRAND_NAME,\n"
                    + "                          I.BRAND_MASTER_SID,\n"
                    + "                          I.THERAPEUTIC_CLASS)A\n"
                    + "        WHERE  ( A.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS\n"
                    + "                 AND @THERAPEUTIC_CLASS IS NOT NULL\n"
                    + "                 AND @BRAND IS NULL )\n"
                    + "                OR ( A.BRAND_MASTER_SID = @BRAND\n"
                    + "                     AND @BRAND IS NOT NULL\n"
                    + "                     AND @THERAPEUTIC_CLASS IS NULL )\n"
                    + "                OR ( A.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS\n"
                    + "                     AND A.BRAND_MASTER_SID = @BRAND )\n"
                    + "                OR ( @THERAPEUTIC_CLASS IS NULL\n"
                    + "                     AND @BRAND IS NULL )\n"
                    + "        GROUP  BY A.BRAND_NAME,\n"
                    + "                  A.PERIOD_SID) A1\n"
                    + "       LEFT JOIN (SELECT BRAND_NAME,\n"
                    + "                         SUM(PROJECTION_SALES) AS PROJECTION_SALES,\n"
                    + "                         PERIOD_SID\n"
                    + "                  FROM   (SELECT BRAND_NAME,\n"
                    + "                                 SUM(PROJECTION_SALES) AS PROJECTION_SALES,\n"
                    + "                                 PERIOD_SID,\n"
                    + "                                 I.BRAND_MASTER_SID,\n"
                    + "                                 THERAPEUTIC_CLASS\n"
                    + "                          FROM   @INPUT_INFO I\n"
                    + "                                 CROSS APPLY (SELECT TOP 1 PM.PROJECTION_MASTER_SID\n"
                    + "                                              FROM   PROJECTION_MASTER PM\n"
                    + "                                                     JOIN WORKFLOW_MASTER WM\n"
                    + "                                                       ON PM.PROJECTION_MASTER_SID = WM.PROJECTION_MASTER_SID\n"
                    + "                                                          AND WM.WORKFLOW_STATUS_ID = (SELECT HELPER_TABLE_SID\n"
                    + "                                                                                       FROM   HELPER_TABLE\n"
                    + "                                                                                       WHERE  LIST_NAME = 'WORKFLOWSTATUS'\n"
                    + "                                                                                              AND DESCRIPTION = 'APPROVED')\n"
                    + "                                                     JOIN @INPUT_INFO I\n"
                    + "                                                       ON I.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID\n"
                    + "                                              ORDER  BY WM.MODIFIED_DATE DESC) CS\n"
                    + "                                 JOIN [DBO].[NM_DISCOUNT_PROJECTION] C\n"
                    + "                                   ON C.PROJECTION_DETAILS_SID = I.PROJECTION_DETAILS_SID\n"
                    + "                                 JOIN HELPER_TABLE H\n"
                    + "                                   ON I.COMPANY_CATEGORY = H.HELPER_TABLE_SID\n"
                    + "                          WHERE  I.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID\n"
                    + "                                 AND H.DESCRIPTION IN ( 'MM MEDI', 'MCG PLAN' )\n"
                    + "                          GROUP  BY BRAND_NAME,\n"
                    + "                                    PERIOD_SID,\n"
                    + "                                    I.BRAND_MASTER_SID,\n"
                    + "                                    THERAPEUTIC_CLASS)B\n"
                    + "                  WHERE  ( B.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS\n"
                    + "                           AND @THERAPEUTIC_CLASS IS NOT NULL\n"
                    + "                           AND @BRAND IS NULL )\n"
                    + "                          OR ( B.BRAND_MASTER_SID = @BRAND\n"
                    + "                               AND @BRAND IS NOT NULL\n"
                    + "                               AND @THERAPEUTIC_CLASS IS NULL )\n"
                    + "                          OR ( B.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS\n"
                    + "                               AND B.BRAND_MASTER_SID = @BRAND )\n"
                    + "                          OR ( @THERAPEUTIC_CLASS IS NULL\n"
                    + "                               AND @BRAND IS NULL )\n"
                    + "                  GROUP  BY B.BRAND_NAME,\n"
                    + "                            B.PERIOD_SID)B1\n"
                    + "              ON A1.BRAND_NAME = B1.BRAND_NAME\n"
                    + "                 AND A1.PERIOD_SID = B1.PERIOD_SID\n"
                    + "       JOIN PERIOD AS PER\n"
                    + "         ON PER.PERIOD_SID = A1.PERIOD_SID\n"
                    + "GROUP  BY PER.YEAR,\n"
                    + "          PER." + frequency + "\n"
                    + "UNION ALL\n"
                    + "SELECT PER.YEAR,\n"
                    + "       PER." + frequency + ",\n"
                    + "       SUM(B1.PROJECTION_SALES)                                                   AS PROJECTION_SALES,\n"
                    + "       'ACTUAL'                                                                   AS TYPE,\n"
                    + "       ( COALESCE(SUM(B1.PROJECTION_SALES) / NULLIF(SUM(A1.SALES), 0), 0) * 100 ) AS PROJECTION_RATE,\n"
                    + "       ( COALESCE(SUM(B1.PROJECTION_SALES) / NULLIF(SUM(A1.UNITS), 0), 0) )       AS PROJECTION_RPU\n"
                    + "FROM   (SELECT BRAND_NAME,\n"
                    + "               SUM(SALES) AS SALES,\n"
                    + "               SUM(UNITS) AS UNITS,\n"
                    + "               PERIOD_SID\n"
                    + "        FROM   (SELECT SUM(B.ACTUAL_SALES) AS SALES,\n"
                    + "                       SUM(B.ACTUAL_UNITS) AS UNITS,\n"
                    + "                       PER.PERIOD_SID,\n"
                    + "                       I.BRAND_NAME,\n"
                    + "                       I.BRAND_MASTER_SID,\n"
                    + "                       I.THERAPEUTIC_CLASS\n"
                    + "                FROM   DBO.ST_M_SALES_PROJECTION_MASTER A\n"
                    + "                       JOIN DBO.ST_M_ACTUAL_SALES B\n"
                    + "                         ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID\n"
                    + "                            AND A.USER_ID = B.USER_ID\n"
                    + "                            AND A.SESSION_ID = B.SESSION_ID\n"
                    + "                       JOIN @INPUT_INFO I\n"
                    + "                         ON I.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID\n"
                    + "                       JOIN PERIOD PER\n"
                    + "                         ON PER.PERIOD_SID = B.PERIOD_SID\n"
                    + "                WHERE  I.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID\n"
                    + "                       AND A.USER_ID = @USER_ID\n"
                    + "                       AND A.SESSION_ID = @SESSION_ID\n"
                    + "                GROUP  BY PER.PERIOD_SID,\n"
                    + "                          I.BRAND_NAME,\n"
                    + "                          I.BRAND_MASTER_SID,\n"
                    + "                          I.THERAPEUTIC_CLASS)A\n"
                    + "        WHERE  ( A.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS\n"
                    + "                 AND @THERAPEUTIC_CLASS IS NOT NULL\n"
                    + "                 AND @BRAND IS NULL )\n"
                    + "                OR ( A.BRAND_MASTER_SID = @BRAND\n"
                    + "                     AND @BRAND IS NOT NULL\n"
                    + "                     AND @THERAPEUTIC_CLASS IS NULL )\n"
                    + "                OR ( A.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS\n"
                    + "                     AND A.BRAND_MASTER_SID = @BRAND )\n"
                    + "                OR ( @THERAPEUTIC_CLASS IS NULL\n"
                    + "                     AND @BRAND IS NULL )\n"
                    + "        GROUP  BY A.BRAND_NAME,\n"
                    + "                  A.PERIOD_SID) A1\n"
                    + "       LEFT JOIN (SELECT BRAND_NAME,\n"
                    + "                         SUM(PROJECTION_SALES) AS PROJECTION_SALES,\n"
                    + "                         PERIOD_SID\n"
                    + "                  FROM   (SELECT BRAND_NAME,\n"
                    + "                                 SUM(ACTUAL_SALES) AS PROJECTION_SALES,\n"
                    + "                                 PERIOD_SID,\n"
                    + "                                 I.BRAND_MASTER_SID,\n"
                    + "                                 THERAPEUTIC_CLASS\n"
                    + "                          FROM   @INPUT_INFO I\n"
                    + "                                 CROSS APPLY (SELECT TOP 1 PM.PROJECTION_MASTER_SID\n"
                    + "                                              FROM   PROJECTION_MASTER PM\n"
                    + "                                                     JOIN WORKFLOW_MASTER WM\n"
                    + "                                                       ON PM.PROJECTION_MASTER_SID = WM.PROJECTION_MASTER_SID\n"
                    + "                                                          AND WM.WORKFLOW_STATUS_ID = (SELECT HELPER_TABLE_SID\n"
                    + "                                                                                       FROM   HELPER_TABLE\n"
                    + "                                                                                       WHERE  LIST_NAME = 'WORKFLOWSTATUS'\n"
                    + "                                                                                              AND DESCRIPTION = 'APPROVED')\n"
                    + "                                                     JOIN @INPUT_INFO I\n"
                    + "                                                       ON I.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID\n"
                    + "                                              ORDER  BY WM.MODIFIED_DATE DESC) CS\n"
                    + "                                 JOIN [DBO].[NM_ACTUAL_DISCOUNT] C\n"
                    + "                                   ON C.PROJECTION_DETAILS_SID = I.PROJECTION_DETAILS_SID\n"
                    + "                                 JOIN HELPER_TABLE H\n"
                    + "                                   ON I.COMPANY_CATEGORY = H.HELPER_TABLE_SID\n"
                    + "                          WHERE  I.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID\n"
                    + "                                 AND H.DESCRIPTION IN ( 'MM MEDI', 'MCG PLAN' )\n"
                    + "                          GROUP  BY BRAND_NAME,\n"
                    + "                                    PERIOD_SID,\n"
                    + "                                    I.BRAND_MASTER_SID,\n"
                    + "                                    THERAPEUTIC_CLASS)B\n"
                    + "                  WHERE  ( B.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS\n"
                    + "                           AND @THERAPEUTIC_CLASS IS NOT NULL\n"
                    + "                           AND @BRAND IS NULL )\n"
                    + "                          OR ( B.BRAND_MASTER_SID = @BRAND\n"
                    + "                               AND @BRAND IS NOT NULL\n"
                    + "                               AND @THERAPEUTIC_CLASS IS NULL )\n"
                    + "                          OR ( B.THERAPEUTIC_CLASS = @THERAPEUTIC_CLASS\n"
                    + "                               AND B.BRAND_MASTER_SID = @BRAND )\n"
                    + "                          OR ( @THERAPEUTIC_CLASS IS NULL\n"
                    + "                               AND @BRAND IS NULL )\n"
                    + "                  GROUP  BY B.BRAND_NAME,\n"
                    + "                            B.PERIOD_SID)B1\n"
                    + "              ON A1.BRAND_NAME = B1.BRAND_NAME\n"
                    + "                 AND A1.PERIOD_SID = B1.PERIOD_SID\n"
                    + "       JOIN PERIOD AS PER\n"
                    + "         ON PER.PERIOD_SID = A1.PERIOD_SID\n"
                    + "GROUP  BY per.year,\n"
                    + "          per." + frequency + "\n  "
                    + "ORDER     BY per.year  ";
            if (!frequency.equals("YEAR")) {
                query += ",\n"
                        + "per." + frequency + StringUtils.EMPTY;
            }

            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            list = (List) salesProjectionDAO.executeSelectQuery(query);

            return list;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return null;
        }
    }

    public List getDiscountProjectionResults(List<Integer> discountprojectionId, String frequency, String discountString, String actualsOrProjections, String view, String order, List<Integer> startAndEndPeriods, int userId, int sessionId) {
        List list = new ArrayList();
        try {

            String idString = StringUtils.EMPTY;
            for (int i = 0; i < discountprojectionId.size(); i++) {
                if (i != discountprojectionId.size() - 1) {
                    idString = idString + discountprojectionId.get(i) + ",";
                } else {
                    idString = idString + discountprojectionId.get(i);
                }
            }
            String startPeriod = StringUtils.EMPTY;
            String endPeriod = StringUtils.EMPTY;

            String forecastStartPeriod = StringUtils.EMPTY;
            String forecastEndPeriod = StringUtils.EMPTY;
            if (discountString.equals(Constant.DASH)) {
                discountString = "'" + discountString + "'";
            }
            if (startAndEndPeriods != null && startAndEndPeriods.size() != 0) {
                String hsYear = String.valueOf(startAndEndPeriods.get(0));
                String hsMonth = String.valueOf(startAndEndPeriods.get(1));
                String heYear = String.valueOf(startAndEndPeriods.get(2));
                String heMonth = String.valueOf(startAndEndPeriods.get(3));

                String fsYear = String.valueOf(startAndEndPeriods.get(4));
                String fsMonth = String.valueOf(startAndEndPeriods.get(5));
                String feYear = String.valueOf(startAndEndPeriods.get(6));
                String feMonth = String.valueOf(startAndEndPeriods.get(7));

                if (hsMonth.length() == 1) {
                    hsMonth = Constant.DASH + hsMonth;
                }
                startPeriod = hsYear + hsMonth;
                if (heMonth.length() == 1) {
                    heMonth = Constant.DASH + heMonth;
                }
                endPeriod = heYear + heMonth;
                if (fsMonth.length() == 1) {
                    fsMonth = Constant.DASH + fsMonth;
                }
                forecastStartPeriod = fsYear + fsMonth;
                if (feMonth.length() == 1) {
                    feMonth = Constant.DASH + feMonth;
                }
                forecastEndPeriod = feYear + feMonth;

            }
            if (frequency.equals(Constant.QUARTERLY)) {
                frequency = "QUARTER";
            }
            if (frequency.equals(ANNUALLY.getConstant())) {
                frequency = "YEAR";
            }
            if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
                frequency = "SEMI_ANNUAL";

            }
            if (frequency.equals(MONTHLY.getConstant())) {
                frequency = "MONTH";
            }
            String projectionQuery = StringUtils.EMPTY;
            projectionQuery = "SELECT PR.YEAR,\n"
                    + "       PR." + frequency + "            AS BASE,\n"
                    + "       0.00                        AS ACTUAL_SALES,\n"
                    + "       0.00                        AS ACTUAL_DISCOUNT,\n"
                    + "       Max(NMDPM.PROJECTION_SALES)  AS PROJECTION_SALES,\n"
                    + "       Sum(NMDPM.PROJECTION_SALES) AS PROJECTION_DISCOUNT,\n"
                    + "       PR." + frequency + ",\n"
                    + "       PR.MONTH\n"
                    + "FROM   PROJECTION_DETAILS PD\n"
                    + "JOIN   M_DISCOUNT_PROJECTION NMDPM ON NMDPM.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n"
                    + "JOIN   PERIOD PR ON PR.PERIOD_SID = NMDPM.PERIOD_SID\n"
                    + "JOIN   ST_M_SALES_PROJECTION_MASTER NMSPM ON NMSPM.PROJECTION_DETAILS_SID = NMDPM.PROJECTION_DETAILS_SID\n"
                    + "                                         AND NMSPM.USER_ID = NMDPM.USER_ID\n"
                    + "                                         AND NMSPM.SESSION_ID = NMDPM.SESSION_ID\n"
                    + "JOIN   ST_M_SALES_PROJECTION NMSP ON NMSP.PROJECTION_DETAILS_SID = NMDPM.PROJECTION_DETAILS_SID\n"
                    + "                                 AND NMSP.PERIOD_SID = PR.PERIOD_SID\n"
                    + "WHERE  PD.PROJECTION_DETAILS_SID IN (" + idString + ")\n"
                    + "  AND NMDPM.USER_ID =" + userId + "\n"
                    + "   AND NMDPM.SESSION_ID =" + sessionId + "\n"
                    + "  AND\n"
                    + " Cast(PR.YEAR AS VARCHAR(4))\n"
                    + "       + RIGHT('0'+Cast(PR.MONTH AS VARCHAR), 2) >=" + forecastStartPeriod + "\n"
                    + "   AND Cast(PR.YEAR AS VARCHAR(4))\n"
                    + "       + RIGHT('0'+Cast(PR.MONTH AS VARCHAR), 2) <=" + forecastEndPeriod + "\n"
                    + "GROUP  BY PR.YEAR,\n"
                    + "          PR." + frequency + ",\n"
                    + "          PR.MONTH,\n"
                    + "          PD.PROJECTION_DETAILS_SID \n"
                    + "\n"
                    + "UNION ALL\n"
                    + "SELECT    PR.YEAR,\n"
                    + "          PR." + frequency + "                            AS BASE,\n"
                    + "          Max(NMADM.ACTUAL_SALES)                AS ACTUAL_SALES,\n"
                    + "          Sum(NMADM.ACTUAL_SALES)               AS ACTUAL_DISCOUNT,\n"
                    + "          Max(IsNull(NMSP.PROJECTION_SALES, 0)) AS PROJECTION_SALES,\n"
                    + "          Sum(IsNull(NMAS.ACTUAL_SALES, 0)) AS PROJECTION_DISCOUNT,\n"
                    + "          PR." + frequency + ",\n"
                    + "          PR.MONTH\n"
                    + "FROM      PROJECTION_DETAILS PD\n"
                    + "JOIN      M_ACTUAL_DISCOUNT NMADM ON NMADM.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n"
                    + "JOIN      PERIOD PR ON PR.PERIOD_SID = NMADM.PERIOD_SID\n"
                    + "JOIN      ST_M_SALES_PROJECTION_MASTER NMSPM ON NMSPM.PROJECTION_DETAILS_SID = NMADM.PROJECTION_DETAILS_SID\n"
                    + "                                            AND NMSPM.USER_ID = NMADM.USER_ID\n"
                    + "                                            AND NMSPM.SESSION_ID = NMADM.SESSION_ID\n"
                    + "JOIN      ST_M_ACTUAL_SALES NMAS ON NMAS.PERIOD_SID = PR.PERIOD_SID\n"
                    + "                                AND NMAS.USER_ID = NMADM.USER_ID\n"
                    + "                                AND NMAS.SESSION_ID = NMADM.SESSION_ID\n"
                    + "                                AND NMAS.PROJECTION_DETAILS_SID = NMADM.PROJECTION_DETAILS_SID\n"
                    + "LEFT JOIN ST_M_SALES_PROJECTION NMSP ON NMSP.PROJECTION_DETAILS_SID = NMSPM.PROJECTION_DETAILS_SID\n"
                    + "                                    AND NMSP.PERIOD_SID = PR.PERIOD_SID\n"
                    + "                                    AND NMSP.USER_ID = NMSPM.USER_ID\n"
                    + "                                    AND NMSP.SESSION_ID = NMSPM.SESSION_ID\n"
                    + "\n"
                    + "WHERE    \n"
                    + " PD.PROJECTION_DETAILS_SID IN( " + idString + ")\n"
                    + "      AND NMADM.USER_ID =" + userId + "\n"
                    + "      AND NMADM.SESSION_ID =" + sessionId + "\n"
                    + "      AND \n"
                    + "	  Cast(PR.YEAR AS VARCHAR(4))\n"
                    + "          + RIGHT ('0'+Cast(PR.MONTH AS VARCHAR), 2)  >=" + startPeriod + "\n"
                    + "      AND Cast(PR.YEAR AS VARCHAR(4))\n"
                    + "          + RIGHT('0'+Cast(PR.MONTH AS VARCHAR), 2) <=" + endPeriod + "\n"
                    + "      GROUP     BY PR.YEAR,\n"
                    + "             PR." + frequency + ",\n"
                    + "             PR.MONTH,\n"
                    + "             PD.PROJECTION_DETAILS_SID\n";

            if (view.equalsIgnoreCase(Constant.PARENT)) {
                if (frequency.equals("YEAR") || frequency.equals("MONTH")) {
                    projectionQuery = projectionQuery + "ORDER BY PR.YEAR";
                } else {
                    projectionQuery = projectionQuery + "ORDER BY PR.YEAR,PR." + frequency + "";
                }
            } else {
                if (frequency.equals("YEAR") || frequency.equals("MONTH")) {
                    projectionQuery = projectionQuery + "ORDER BY RSM.RS_NAME,PR.YEAR";
                } else {
                    projectionQuery = projectionQuery + "ORDER BY RSM.RS_NAME,PR.YEAR,PR." + frequency + "";
                }
            }

            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            list = (List) salesProjectionDAO.executeSelectQuery(projectionQuery);

            return list;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    public List getMandatedSupp2(List<Integer> discountprojectionId, String frequency, String discountString, String actualsOrProjections, String view, String order, List<Integer> startAndEndPeriods, int userId, int sessionId) {
        List list = new ArrayList();
        try {

            String idString = StringUtils.EMPTY;
            for (int i = 0; i < discountprojectionId.size(); i++) {
                if (i != discountprojectionId.size() - 1) {
                    idString = idString + discountprojectionId.get(i) + ",";
                } else {
                    idString = idString + discountprojectionId.get(i);
                }
            }
            String startPeriod = StringUtils.EMPTY;
            String endPeriod = StringUtils.EMPTY;

            String forecastStartPeriod = StringUtils.EMPTY;
            String forecastEndPeriod = StringUtils.EMPTY;
            if (discountString.equals(Constant.DASH)) {
                discountString = "'" + discountString + "'";
            }
            if (startAndEndPeriods != null && startAndEndPeriods.size() != 0) {
                String hsYear = String.valueOf(startAndEndPeriods.get(0));
                String hsMonth = String.valueOf(startAndEndPeriods.get(1));
                String heYear = String.valueOf(startAndEndPeriods.get(2));
                String heMonth = String.valueOf(startAndEndPeriods.get(3));

                String fsYear = String.valueOf(startAndEndPeriods.get(4));
                String fsMonth = String.valueOf(startAndEndPeriods.get(5));
                String feYear = String.valueOf(startAndEndPeriods.get(6));
                String feMonth = String.valueOf(startAndEndPeriods.get(7));

                if (hsMonth.length() == 1) {
                    hsMonth = Constant.DASH + hsMonth;
                }
                startPeriod = hsYear + hsMonth;
                if (heMonth.length() == 1) {
                    heMonth = Constant.DASH + heMonth;
                }
                endPeriod = heYear + heMonth;
                if (fsMonth.length() == 1) {
                    fsMonth = Constant.DASH + fsMonth;
                }
                forecastStartPeriod = fsYear + fsMonth;
                if (feMonth.length() == 1) {
                    feMonth = Constant.DASH + feMonth;
                }
                forecastEndPeriod = feYear + feMonth;

            }
            if (frequency.equals(Constant.QUARTERLY)) {
                frequency = "QUARTER";
            }
            if (frequency.equals(ANNUALLY.getConstant())) {
                frequency = "YEAR";
            }
            if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
                frequency = "SEMI_ANNUAL";

            }
            if (frequency.equals(MONTHLY.getConstant())) {
                frequency = "MONTH";
            }
            String projectionQuery = StringUtils.EMPTY;

            projectionQuery = "SELECT PR.YEAR,\n"
                    + "       PR." + frequency + "            AS BASE,\n"
                    + "       0.00                        AS ACTUAL_SALES,\n"
                    + "       0.00                        AS ACTUAL_DISCOUNT,\n"
                    + "       Max(NMDPM.PROJECTION_SALES)  AS PROJECTION_SALES,\n"
                    + "       Sum(NMDPM.PROJECTION_SALES) AS PROJECTION_DISCOUNT,\n"
                    + "       PR." + frequency + ",\n"
                    + "       PR.MONTH\n"
                    + "FROM   PROJECTION_DETAILS PD\n"
                    + "JOIN   ST_M_SUPPLEMENTAL_DISC_PROJ NMDPM ON NMDPM.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n"
                    + "JOIN   PERIOD PR ON PR.PERIOD_SID = NMDPM.PERIOD_SID\n"
                    + "JOIN   ST_M_SALES_PROJECTION_MASTER NMSPM ON NMSPM.PROJECTION_DETAILS_SID = NMDPM.PROJECTION_DETAILS_SID\n"
                    + "                                         AND NMSPM.USER_ID = NMDPM.USER_ID\n"
                    + "                                         AND NMSPM.SESSION_ID = NMDPM.SESSION_ID\n"
                    + "JOIN   ST_M_SALES_PROJECTION NMSP ON NMSP.PROJECTION_DETAILS_SID = NMDPM.PROJECTION_DETAILS_SID\n"
                    + "                                 AND NMSP.PERIOD_SID = PR.PERIOD_SID\n"
                    + "WHERE  PD.PROJECTION_DETAILS_SID IN (" + idString + ")\n"
                    + "  AND NMDPM.USER_ID =" + userId + "\n"
                    + "   AND NMDPM.SESSION_ID =" + sessionId + "\n"
                    + "  AND\n"
                    + " Cast(PR.YEAR AS VARCHAR(4))\n"
                    + "       + RIGHT('0'+Cast(PR.MONTH AS VARCHAR), 2) >=" + forecastStartPeriod + "\n"
                    + "   AND Cast(PR.YEAR AS VARCHAR(4))\n"
                    + "       + RIGHT('0'+Cast(PR.MONTH AS VARCHAR), 2) <=" + forecastEndPeriod + "\n"
                    + "GROUP  BY PR.YEAR,\n"
                    + "          PR." + frequency + ",\n"
                    + "          PR.MONTH,\n"
                    + "          PD.PROJECTION_DETAILS_SID \n"
                    + "\n"
                    + "UNION ALL\n"
                    + "SELECT    PR.YEAR,\n"
                    + "          PR." + frequency + "                            AS BASE,\n"
                    + "          Max(NMADM.ACTUAL_SALES)                AS ACTUAL_SALES,\n"
                    + "          Sum(NMADM.ACTUAL_SALES)               AS ACTUAL_DISCOUNT,\n"
                    + "          Max(IsNull(NMDP.PROJECTION_SALES, 0)) AS PROJECTION_SALES,\n"
                    + "          Sum(IsNull(NMDP.PROJECTION_SALES, 0)) AS PROJECTION_DISCOUNT"
                    + ",\n"
                    + "          PR." + frequency + ",\n"
                    + "          PR.MONTH\n"
                    + "  FROM      PROJECTION_DETAILS PD\n"
                    + "JOIN      ST_M_SUPPLEMENTAL_DISC_ACTUALS NMADM ON NMADM.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n"
                    + "JOIN      PERIOD PR ON PR.PERIOD_SID = NMADM.PERIOD_SID\n"
                    + "JOIN      ST_M_SALES_PROJECTION_MASTER NMSPM ON NMSPM.PROJECTION_DETAILS_SID = NMADM.PROJECTION_DETAILS_SID\n"
                    + "                                            AND NMSPM.USER_ID = NMADM.USER_ID\n"
                    + "                                            AND NMSPM.SESSION_ID = NMADM.SESSION_ID\n"
                    + "JOIN      ST_M_ACTUAL_SALES NMAS ON NMAS.PERIOD_SID = PR.PERIOD_SID\n"
                    + "                                AND NMAS.USER_ID = NMADM.USER_ID\n"
                    + "                                AND NMAS.SESSION_ID = NMADM.SESSION_ID\n"
                    + "                                AND NMAS.PROJECTION_DETAILS_SID = NMADM.PROJECTION_DETAILS_SID\n"
                    + "LEFT JOIN ST_M_SALES_PROJECTION NMSP ON NMSP.PROJECTION_DETAILS_SID = NMSPM.PROJECTION_DETAILS_SID\n"
                    + "                                    AND NMSP.PERIOD_SID = PR.PERIOD_SID\n"
                    + "                                    AND NMSP.USER_ID = NMSPM.USER_ID\n"
                    + "                                    AND NMSP.SESSION_ID = NMSPM.SESSION_ID\n"
                    + "LEFT JOIN ST_M_SUPPLEMENTAL_DISC_PROJ NMDP ON NMDP.PROJECTION_DETAILS_SID = NMSPM.PROJECTION_DETAILS_SID\n"
                    + "                                    AND NMDP.PERIOD_SID = PR.PERIOD_SID\n"
                    + "                                    AND NMDP.USER_ID = NMSPM.USER_ID\n"
                    + "                                    AND NMDP.SESSION_ID = NMSPM.SESSION_ID \n"
                    + "\n"
                    + "WHERE    \n"
                    + " PD.PROJECTION_DETAILS_SID IN( " + idString + ")\n"
                    + "      AND NMADM.USER_ID =" + userId + "\n"
                    + "      AND NMADM.SESSION_ID =" + sessionId + "\n"
                    + "      AND \n"
                    + "	  Cast(PR.YEAR AS VARCHAR(4))\n"
                    + "          + RIGHT ('0'+Cast(PR.MONTH AS VARCHAR), 2)  >=" + startPeriod + "\n"
                    + "      AND Cast(PR.YEAR AS VARCHAR(4))\n"
                    + "          + RIGHT('0'+Cast(PR.MONTH AS VARCHAR), 2) <=" + endPeriod + "\n"
                    + "      GROUP     BY PR.YEAR,\n"
                    + "             PR." + frequency + ",\n"
                    + "             PR.MONTH,\n"
                    + "             PD.PROJECTION_DETAILS_SID\n";

            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            list = (List) salesProjectionDAO.executeSelectQuery(projectionQuery);
            return list;

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }

    }

    public List getMandatedTotal(List<Integer> discountprojectionId, String frequency, String discountString, int projId, String view, ProjectionSelectionDTO projSelDTO, List<Integer> startAndEndPeriods, int userId, int sessionId) {
        List list = new ArrayList();
        try {

            String idString = StringUtils.EMPTY;
            for (int i = 0; i < discountprojectionId.size(); i++) {
                if (i != discountprojectionId.size() - 1) {
                    idString = idString + discountprojectionId.get(i) + ",";
                } else {
                    idString = idString + discountprojectionId.get(i);
                }
            }
            String startPeriod = StringUtils.EMPTY;
            String endPeriod = StringUtils.EMPTY;

            String forecastStartPeriod = StringUtils.EMPTY;
            String forecastEndPeriod = StringUtils.EMPTY;
            if (discountString.equals(Constant.DASH)) {
                discountString = "'" + discountString + "'";
            }
            if (startAndEndPeriods != null && startAndEndPeriods.size() != 0) {
                String hsYear = String.valueOf(startAndEndPeriods.get(0));
                String hsMonth = String.valueOf(startAndEndPeriods.get(1));
                String heYear = String.valueOf(startAndEndPeriods.get(2));
                String heMonth = String.valueOf(startAndEndPeriods.get(3));

                String fsYear = String.valueOf(startAndEndPeriods.get(4));
                String fsMonth = String.valueOf(startAndEndPeriods.get(5));
                String feYear = String.valueOf(startAndEndPeriods.get(6));
                String feMonth = String.valueOf(startAndEndPeriods.get(7));

                if (hsMonth.length() == 1) {
                    hsMonth = Constant.DASH + hsMonth;
                }
                startPeriod = hsYear + hsMonth;
                if (heMonth.length() == 1) {
                    heMonth = Constant.DASH + heMonth;
                }
                endPeriod = heYear + heMonth;
                if (fsMonth.length() == 1) {
                    fsMonth = Constant.DASH + fsMonth;
                }
                forecastStartPeriod = fsYear + fsMonth;
                if (feMonth.length() == 1) {
                    feMonth = Constant.DASH + feMonth;
                }
                forecastEndPeriod = feYear + feMonth;

            }
            if (frequency.equals(Constant.QUARTERLY)) {
                frequency = "QUARTER";
            }
            if (frequency.equals(ANNUALLY.getConstant())) {
                frequency = "YEAR";
            }
            if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
                frequency = "SEMI_ANNUAL";

            }
            if (frequency.equals(MONTHLY.getConstant())) {
                frequency = "MONTH";
            }

            String str = "SELECT PR.YEAR,\n"
                    + "        PR." + frequency + " AS BASE,\n"
                    + "      \n"
                    + "sum(SUPMAS.ACTUAL_SALES) As Actual_Sale,\n"
                    + "Coalesce(Sum(SUPMAS.ACTUAL_SALES)/Nullif(Sum(m_mas.ACTUAL_SALES),0),0)*100       AS  Disc_Rate,\n"
                    + "  '0' As Flag,\n"
                    + "Coalesce(Sum(SUPMAS.ACTUAL_SALES)/Nullif(Sum(m_mas.ACTUAL_UNITS),0),0) As RPU\n"
                    + "FROM PROJECTION_DETAILS PD,\n"
                    + "  CCP_DETAILS CCP,\n"
                    + "  COMPANY_MASTER TT,\n"
                    + "  M_ACTUAL_DISCOUNT SUPMAS,\n"
                    + "  Period PR,\n"
                    + " ST_M_ACTUAL_SALES m_mas,\n"
                    + " ST_M_SALES_PROJECTION_MASTER m_mac\n"
                    + " where  CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                    + "  and TT.COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID\n"
                    + "   and SUPMAS.USER_ID =" + userId + "\n"
                    + "   and SUPMAS.SESSION_ID =" + sessionId + "\n"
                    + "   and PD.PROJECTION_MASTER_SID =" + projId + "\n"
                    + "   and PR.PERIOD_SID = SUPMAS.PERIOD_SID\n"
                    + " and PR.PERIOD_SID = m_mas.PERIOD_SID\n"
                    + "   and SUPMAS.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n"
                    + " and pd.PROJECTION_DETAILS_SID = m_mas.PROJECTION_DETAILS_SID\n"
                    + " AND m_mas.USER_ID = SUPMAS.USER_ID\n"
                    + "  AND m_mas.SESSION_ID = SUPMAS.SESSION_ID\n"
                    + " and m_mac.PROJECTION_DETAILS_SID=pd.PROJECTION_DETAILS_SID\n"
                    + " and m_mac.USER_ID=SUPMAS.USER_ID\n"
                    + " AND m_mac.SESSION_ID =  SUPMAS.SESSION_ID\n"
                    + ""
                    + "\n"
                    + " GROUP  BY PR.YEAR,\n"
                    + "           PR." + frequency + "\n"
                    + "\n"
                    + "\n"
                    + " UNION ALL\n"
                    + " SELECT    PR.YEAR,\n"
                    + "           PR." + frequency + "                            AS BASE,\n"
                    + "sum(SUPMAS.PROJECTION_SALES) As Proj_Sale,\n"
                    + " Coalesce(Sum(SUPMAS.PROJECTION_SALES)/Nullif(Sum(m_mas.PROJECTION_SALES),0),0)*100       AS  Disc_Rate,\n"
                    + "  '1' As Flag,\n"
                    + "Coalesce(Sum(SUPMAS.PROJECTION_SALES)/Nullif(Sum(m_mas.PROJECTION_UNITS),0),0) As RPU \n"
                    + "\n"
                    + "  FROM PROJECTION_DETAILS PD,\n"
                    + "  CCP_DETAILS CCP,\n"
                    + "  COMPANY_MASTER TT,\n"
                    + "  M_DISCOUNT_PROJECTION SUPMAS,\n"
                    + "  Period PR,\n"
                    + " ST_M_SALES_PROJECTION m_mas,\n"
                    + " ST_M_SALES_PROJECTION_MASTER m_mac\n"
                    + " where  CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                    + "  and TT.COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID\n"
                    + "  and SUPMAS.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID   and SUPMAS.USER_ID =" + userId + " and SUPMAS.SESSION_ID =" + sessionId + " and PD.PROJECTION_MASTER_SID =" + projId + "\n"
                    + "   and PR.PERIOD_SID = SUPMAS.PERIOD_SID\n"
                    + " and PR.PERIOD_SID=m_mas.PERIOD_SID\n"
                    + " and pd.PROJECTION_DETAILS_SID = m_mas.PROJECTION_DETAILS_SID\n"
                    + " AND m_mas.USER_ID = SUPMAS.USER_ID\n"
                    + "  AND m_mas.SESSION_ID = SUPMAS.SESSION_ID\n"
                    + " and m_mac.PROJECTION_DETAILS_SID=pd.PROJECTION_DETAILS_SID\n"
                    + " and m_mac.USER_ID=SUPMAS.USER_ID\n"
                    + " AND m_mac.SESSION_ID =  SUPMAS.SESSION_ID"
                    + "\n"
                    + "       GROUP     BY PR.YEAR,\n"
                    + "              PR." + frequency + "\n"
                    + "\n";
            if (!frequency.equals("YEAR")) {
                str += " ORDER BY PR.YEAR,PR." + frequency + "";
            } else {
                str += " ORDER BY PR.YEAR";
            }
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            list = (List) salesProjectionDAO.executeSelectQuery(str);

            return list;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }

    }

    public List getSuppTotal(List<Integer> discountprojectionId, String frequency, String discountString, String actualsOrProjections, String view, ProjectionSelectionDTO projSelDTO, List<Integer> startAndEndPeriods, int userId, int sessionId) {
        List list = new ArrayList();
        try {

            String idString = StringUtils.EMPTY;
            for (int i = 0; i < discountprojectionId.size(); i++) {
                if (i != discountprojectionId.size() - 1) {
                    idString = idString + discountprojectionId.get(i) + ",";
                } else {
                    idString = idString + discountprojectionId.get(i);
                }
            }
            String startPeriod = StringUtils.EMPTY;
            String endPeriod = StringUtils.EMPTY;

            String forecastStartPeriod = StringUtils.EMPTY;
            String forecastEndPeriod = StringUtils.EMPTY;
            if (discountString.equals(Constant.DASH)) {
                discountString = "'" + discountString + "'";
            }
            if (startAndEndPeriods != null && startAndEndPeriods.size() != 0) {
                String hsYear = String.valueOf(startAndEndPeriods.get(0));
                String hsMonth = String.valueOf(startAndEndPeriods.get(1));
                String heYear = String.valueOf(startAndEndPeriods.get(2));
                String heMonth = String.valueOf(startAndEndPeriods.get(3));

                String fsYear = String.valueOf(startAndEndPeriods.get(4));
                String fsMonth = String.valueOf(startAndEndPeriods.get(5));
                String feYear = String.valueOf(startAndEndPeriods.get(6));
                String feMonth = String.valueOf(startAndEndPeriods.get(7));

                if (hsMonth.length() == 1) {
                    hsMonth = Constant.DASH + hsMonth;
                }
                startPeriod = hsYear + hsMonth;
                if (heMonth.length() == 1) {
                    heMonth = Constant.DASH + heMonth;
                }
                endPeriod = heYear + heMonth;
                if (fsMonth.length() == 1) {
                    fsMonth = Constant.DASH + fsMonth;
                }
                forecastStartPeriod = fsYear + fsMonth;
                if (feMonth.length() == 1) {
                    feMonth = Constant.DASH + feMonth;
                }
                forecastEndPeriod = feYear + feMonth;

            }
            if (frequency.equals(Constant.QUARTERLY)) {
                frequency = "QUARTER";
            }
            if (frequency.equals(ANNUALLY.getConstant())) {
                frequency = "YEAR";
            }
            if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
                frequency = "SEMI_ANNUAL";

            }
            if (frequency.equals(MONTHLY.getConstant())) {
                frequency = "MONTH";
            }

            String str = "SELECT PR.YEAR,\n"
                    + " PR." + frequency + "  AS BASE,\n"
                    + "  SUM(STPROJ.PROJECTION_SALES)  AS PROJECTION_SALES,\n"
                    + "Coalesce(Sum(STPROJ.PROJECTION_SALES)/Nullif(Sum(STMSP.PROJECTION_SALES),0),0)*100       AS ProJ_Rate,\n"
                    + "      1  AS Flag,Coalesce(Sum(STPROJ.PROJECTION_SALES)/Nullif(Sum(STMSP.PROJECTION_UNITS),0),0) AS RPU  FROM ST_M_SUPPLEMENTAL_DISC_PROJ STPROJ,\n"
                    + "PROJECTION_DETAILS PD,\n"
                    + "ST_M_SALES_PROJECTION STMSP,\n"
                    + "ST_M_SALES_PROJECTION_MASTER STMSPM,\n"
                    + "\"PERIOD\" PR "
                    + "WHERE \n"
                    + "STPROJ.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n"
                    + "AND PR.PERIOD_SID=STPROJ.PERIOD_SID\n"
                    + "AND PD.PROJECTION_DETAILS_SID IN (" + idString + ")\n"
                    + "AND STPROJ.USER_ID = " + userId + "\n"
                    + "AND STPROJ.SESSION_ID = " + sessionId + "\n"
                    + "AND STMSP.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
                    + "AND STMSP.PERIOD_SID=PR.PERIOD_SID\n"
                    + "AND STMSP.USER_ID=STPROJ.USER_ID\n"
                    + "AND STMSP.SESSION_ID=STPROJ.SESSION_ID\n"
                    + "AND STMSPM.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
                    + "AND STMSPM.USER_ID=STPROJ.USER_ID\n"
                    + "AND STMSPM.SESSION_ID=STPROJ.SESSION_ID"
                    + " GROUP BY PR.YEAR,PR." + frequency + "\n"
                    + "UNION ALL\n"
                    + "\n"
                    + " SELECT PR.YEAR,\n"
                    + "         PR." + frequency + "            AS BASE,\n"
                    + "\n"
                    + "         SUM(STPROJ.ACTUAL_SALES)  AS PROJECTION_SALES,\n"
                    + "Coalesce(Sum(STPROJ.ACTUAL_SALES)/Nullif(Sum(STMAS.ACTUAL_SALES),0),0)*100       AS Actual_Rate,\n"
                    + "         0                          AS Flag,Coalesce(Sum(STPROJ.ACTUAL_SALES)/Nullif(Sum(STMAS.ACTUAL_UNITS),0),0)  AS RPU \n"
                    + "FROM ST_M_SUPPLEMENTAL_DISC_ACTUALS STPROJ,\n"
                    + "PROJECTION_DETAILS PD,\n"
                    + "\"PERIOD\" PR ,\n"
                    + "ST_M_ACTUAL_SALES STMAS,\n"
                    + "ST_M_SALES_PROJECTION_MASTER STMSPM "
                    + "WHERE \n"
                    + "STPROJ.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n"
                    + "AND PR.PERIOD_SID=STPROJ.PERIOD_SID\n"
                    + "AND PD.PROJECTION_DETAILS_SID IN (" + idString + ")\n"
                    + "AND STPROJ.USER_ID = " + userId + "\n"
                    + "AND STPROJ.SESSION_ID = " + sessionId + "\n"
                    + "AND STMAS.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
                    + "AND STMAS.PERIOD_SID=PR.PERIOD_SID\n"
                    + "AND STMAS.USER_ID=STPROJ.USER_ID\n"
                    + "AND STMAS.SESSION_ID=STPROJ.SESSION_ID\n"
                    + "AND STMSPM.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
                    + "AND STMSPM.USER_ID=STPROJ.USER_ID\n"
                    + "AND STMSPM.SESSION_ID=STPROJ.SESSION_ID"
                    + "  GROUP BY PR.YEAR,PR." + frequency + "\n";

            if (!frequency.equals("YEAR")) {
                str += " ORDER BY PR.YEAR,PR." + frequency + "";
            } else {
                str += " ORDER BY PR.YEAR";
            }

            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            list = (List) salesProjectionDAO.executeSelectQuery(str);
            return list;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    public List getTherapeuticValue(int projectionId) {
        List list = new ArrayList();
        String query = StringUtils.EMPTY;
        try {
            if (projectionId != 0) {
                query = "select RELATIONSHIP_LEVEL_VALUES  from dbo.RELATIONSHIP_LEVEL_DEFINITION where RELATIONSHIP_BUILDER_SID in\n"
                        + "(Select PROD_RELATIONSHIP_BUILDER_SID from PROJECTION_MASTER where PROJECTION_MASTER_SID=" + projectionId + ")\n"
                        + "and LEVEL_NAME='Therapeutic Class'";

                SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
                list = (List) salesProjectionDAO.executeSelectQuery(query);

            }

            return list;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    public List getBrandValue(int projectionId, String therapeuticValue) {
        List list = new ArrayList();
        String query = StringUtils.EMPTY;
        try {
            if (projectionId != 0) {
                query = " select RELATIONSHIP_LEVEL_VALUES  from dbo.RELATIONSHIP_LEVEL_DEFINITION where RELATIONSHIP_BUILDER_SID in\n"
                        + " (Select PROD_RELATIONSHIP_BUILDER_SID from PROJECTION_MASTER where PROJECTION_MASTER_SID=" + projectionId + ")\n"
                        + " and LEVEL_NAME='Brand'\n"
                        + "and PARENT_NODE Like'%" + therapeuticValue + "%'\n";

                SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
                list = (List) salesProjectionDAO.executeSelectQuery(query);

            }
            return list;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    public List getFSValue(String relationshipLevelValue) {
        List list = new ArrayList();
        String str = StringUtils.EMPTY;
        try {
            str = "Select DISTINCT h1.DESCRIPTION AS Form,h2.DESCRIPTION As STRENGTH  from ITEM_MASTER im,HELPER_TABLE h1,HELPER_TABLE h2\n"
                    + "where h1.HELPER_TABLE_SID in (Select FORM from ITEM_MASTER where ITEM_ID='" + relationshipLevelValue + "') \n"
                    + "\n"
                    + "and h2.HELPER_TABLE_SID in (Select STRENGTH from ITEM_MASTER where ITEM_ID='" + relationshipLevelValue + "' )";
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            list = (List) salesProjectionDAO.executeSelectQuery(str);

            return list;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }
}
