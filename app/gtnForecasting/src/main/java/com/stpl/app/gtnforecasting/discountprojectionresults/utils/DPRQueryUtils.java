/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.discountprojectionresults.utils;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.dao.SalesProjectionDAO;
import com.stpl.app.gtnforecasting.dao.impl.SalesProjectionDAOImpl;
import com.stpl.app.gtnforecasting.discountprojectionresults.dto.DiscountProjectionResultsDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import static com.stpl.app.utils.Constants.FrequencyConstants.ANNUALLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUALLY;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.QueryUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author gopinath
 */
public class DPRQueryUtils {

    private static final Logger LOGGER = Logger.getLogger(DPRQueryUtils.class);
    public static final String Q_FINALQ = ") Q) FINALQ\n";
    public List getSumNMPivotValue(int projectionId, String freq, ProjectionSelectionDTO projSelDTO) {

        try {
            List list;
            String frequency = StringUtils.EMPTY;
            if (freq.equals(Constant.QUARTERLY)) {
                frequency = Constant.QUARTER;
            }
            if (freq.equals(ANNUALLY.getConstant())) {
                frequency = "YEAR";
            }
            if (freq.equals(SEMI_ANNUALLY.getConstant())) {
                frequency = Constant.SEMI_ANNUAL;
            }
            if (freq.equals(MONTHLY.getConstant())) {
                frequency = Constant.MONTH_WITHOUT_SPACE;
            }
            String str = SQlUtil.getQuery("mmdprgetSumNMPivotValue");
            str = str.replaceAll(Constant.PROJ_ID, projectionId + StringUtils.EMPTY);
            str = str.replaceAll(Constant.FREQUENCY_VAR, frequency);
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            str=QueryUtil.replaceTableNames(str, projSelDTO.getSessionDTO().getCurrentTableNames());
            list = (List) salesProjectionDAO.executeSelectQuery(str);
            return list;
        } catch (PortalException | SystemException e) {
            LOGGER.error(e);
            return Collections.emptyList();

        }
    }

    public List getNonMandateTotalValue(int projectionId, String freq, ProjectionSelectionDTO projSelDTO) {
        try {
            List list;
            String frequency = StringUtils.EMPTY;
            if (freq.equals(Constant.QUARTERLY)) {
                frequency = Constant.QUARTER;
            }
            if (freq.equals(ANNUALLY.getConstant())) {
                frequency = "YEAR";
            }
            if (freq.equals(SEMI_ANNUALLY.getConstant())) {
                frequency = Constant.SEMI_ANNUAL;
            }
            if (freq.equals(MONTHLY.getConstant())) {
                frequency = Constant.MONTH_WITHOUT_SPACE;
            }
            String str = SQlUtil.getQuery("mmdprgetNonMandateTotalValue");
            str = str.replaceAll(Constant.PROJ_ID, projectionId + StringUtils.EMPTY);
            str = str.replaceAll(Constant.FREQUENCY_VAR, frequency);
            if ("YEAR".equalsIgnoreCase(frequency)) {
                str += "ORDER     BY   A1.BRAND_NAME,per.year\n"
                        + "             ";
            } else {
                str += "ORDER     BY  A1.BRAND_NAME, per.year,\n"
                        + "             per." + frequency + "\n"
                        + "           ";
            }
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
             str=QueryUtil.replaceTableNames(str, projSelDTO.getSessionDTO().getCurrentTableNames());
            list = (List) salesProjectionDAO.executeSelectQuery(str);
            return list;
        } catch (PortalException | SystemException e) {
            LOGGER.error(e);
            return Collections.emptyList();

        }

    }

    public List getNonMandatedBrand(int projectionId, String freq, ProjectionSelectionDTO projSelDTO) {
        try {
            List list;
            String frequency = StringUtils.EMPTY;
            if (freq.equals(Constant.QUARTERLY)) {
                frequency = Constant.QUARTER;
            }
            if (freq.equals(ANNUALLY.getConstant())) {
                frequency = "YEAR";
            }
            if (freq.equals(SEMI_ANNUALLY.getConstant())) {
                frequency = Constant.SEMI_ANNUAL;
            }
            if (freq.equals(MONTHLY.getConstant())) {
                frequency = Constant.MONTH_WITHOUT_SPACE;
            }
            String query = SQlUtil.getQuery("mmdprgetNonmandatedbrand");
            query = query.replaceAll(Constant.PROJ_ID, projectionId + StringUtils.EMPTY);
            query = query.replaceAll(Constant.FREQUENCY_VAR, frequency);
            query = query + " WHERE  FINALQ.TEMP_INDEX > " + projSelDTO.getStart() + " AND FINALQ.TEMP_INDEX <=   " + (projSelDTO.getStart() + projSelDTO.getOffset()) + "\n"
                    + "\n"
                    + " order by BRAND_NAME,YEARS,PERIODS";
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            query = QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames());
            list = (List) salesProjectionDAO.executeSelectQuery(query);
            return list;
        } catch (PortalException | SystemException e) {
            LOGGER.error(e);
            return Collections.emptyList();

        }

    }

    public List getLevelValue(ProjectionSelectionDTO projSelDTO) {
        List list = new ArrayList();
        String query = StringUtils.EMPTY;
        String frequency = projSelDTO.getFrequency();
        if (frequency.equals(Constant.QUARTERLY)) {
            frequency = Constant.QUARTER;
        }
        if (frequency.equals(ANNUALLY.getConstant())) {
            frequency = "YEAR";
        }
        if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
            frequency = Constant.SEMI_ANNUAL;

        }
        if (frequency.equals(MONTHLY.getConstant())) {
            frequency = Constant.MONTH_WITHOUT_SPACE;
        }

        try {
            query += "\n"
                    + "select FINALQ.YEARS,FINALQ.PERIODS,FINALQ.COM_ID,FINALQ.CON_ID,FINALQ.BRAND_ID, FINALQ.COMPANY_MASTER_SID,FINALQ.COMPANY_NO,\n"
                    + "  FINALQ.LEVEL_NAME,FINALQ.Actual_Sale,FINALQ.Disc_Rate,FINALQ.Flag,FINALQ.RPU from(\n"
                    + "select Q.YEARS,Q.PERIODS,Q.COM_ID,Q.CON_ID,Q.BRAND_ID, Q.COMPANY_MASTER_SID,Q.COMPANY_NO,\n"
                    + "  Q.LEVEL_NAME,Q.Actual_Sale,Q.Disc_Rate,Q.Flag,Q.RPU,Dense_rank() OVER    (ORDER BY Q.COMPANY_MASTER_SID) AS TEMP_INDEX from("
                    + "select per.YEAR as YEARS ,per." + frequency + " as PERIODS, 0 AS COM_ID,0 AS CON_ID,0 AS BRAND_ID, TT.COMPANY_MASTER_SID as COMPANY_MASTER_SID,TT.COMPANY_NO as COMPANY_NO,\n"
                    + Constant.CUSTOMER_AS_LEVEL_NAME
                    + "\n"
                    + " sum(SUPMAS.ACTUAL_SALES) As Actual_Sale,\n"
                    + "--  avg(SUPMAS.ACTUAL_RATE) As Actual_rate,\n"
                    + "Coalesce(Sum(SUPMAS.ACTUAL_SALES)/Nullif(Sum(m_mas.ACTUAL_SALES),0),0)*100       AS  Disc_Rate,\n"
                    + " 'ACT' As Flag,Coalesce(Sum(SUPMAS.ACTUAL_SALES)/Nullif(Sum(m_mas.ACTUAL_UNITS),0),0) As RPU\n"
                    + "\n"
                    + Constant.FROM_ST_CCP_HIERARCHY_PD
                    + Constant.CCP_DETAILS_CCP
                    + Constant.COMPANY_MASTER_TT
                    + " ST_M_ACTUAL_DISCOUNT SUPMAS,\n"
                    + " Period per,\n"
                    + "ST_M_ACTUAL_SALES m_mas,\n"
                    + "ST_M_SALES_PROJECTION_MASTER m_mac\n"
                    + Constant.WHERE_CCPCCP_DETAILS_SID_PDCCP_DETAILS
                    + Constant.AND_TTCOMPANY_MASTER_SID_CCP_MAS
                    + Constant.AND_PER_PERIOD_SID_SUP_PERIOD_SID
                    + "and Per.PERIOD_SID = m_mas.PERIOD_SID\n"
                    + "  and SUPMAS.CCP_DETAILS_SID = PD.CCP_DETAILS_SID \n"
                    + "and pd.CCP_DETAILS_SID = m_mas.CCP_DETAILS_SID\n"
                    + "and m_mac.CCP_DETAILS_SID=pd.CCP_DETAILS_SID\n"
                    + "\n";
            if ((!StringUtils.EMPTY.equalsIgnoreCase(projSelDTO.getPivotValue()) && !Constant.NULL.equalsIgnoreCase(projSelDTO.getPivotValue())) && Constant.DISCOUNT_SMALL.equalsIgnoreCase(projSelDTO.getPivotView())) {
                if ("YEAR".equalsIgnoreCase(frequency)) {
                    query += Constant.AND_PER_YEAR + projSelDTO.getPivotValue();
                } else {
                    String str[] = projSelDTO.getPivotValue().split(" ");
                    query += Constant.AND_PER_DOT + frequency + Constant.EQUAL + str[0] + AND_SPACE + Constant.PER_YEAR_EQUAL + str[1];
                }

            }
            if (projSelDTO.isFilterDdlb()) {
                query += Constant.AND_TT + projSelDTO.getDefinedContract() + " = '" + projSelDTO.getSelectedCust() + "' ";
            }
            query += " group by  TT.COMPANY_MASTER_SID,TT.COMPANY_NO,per.YEAR,\n"
                    + Constant.SPACE_PER + frequency + "\n"
                    + "UNION\n"
                    + "\n"
                    + "select per.YEAR as  YEARS,per." + frequency + " as PERIODS, 0 AS COM_ID,0 AS CON_ID,0 AS BRAND_ID, TT.COMPANY_MASTER_SID as COMPANY_MASTER_SID,TT.COMPANY_NO as COMPANY_NO,\n"
                    + Constant.CUSTOMER_AS_LEVEL_NAME
                    + "\n"
                    + " sum(SUPMAS.PROJECTION_SALES) As Actual_Sale,\n"
                    + "--  avg(SUPMAS.PROJECTION_RATE) As Proj_rate,\n"
                    + "Coalesce(Sum(SUPMAS.PROJECTION_SALES)/Nullif(Sum(m_mas.PROJECTION_SALES),0),0)*100       AS  Disc_Rate,\n"
                    + " 'Proj' As Flag,Coalesce(Sum(SUPMAS.PROJECTION_SALES)/Nullif(Sum(m_mas.PROJECTION_UNITS),0),0) As RPU \n"
                    + "\n"
                    + Constant.FROM_ST_CCP_HIERARCHY_PD
                    + Constant.CCP_DETAILS_CCP
                    + Constant.COMPANY_MASTER_TT
                    + " ST_M_DISCOUNT_PROJECTION SUPMAS,\n"
                    + " Period per,\n"
                    + "ST_M_SALES_PROJECTION m_mas,\n"
                    + "ST_M_SALES_PROJECTION_MASTER m_mac\n"
                    + Constant.WHERE_CCPCCP_DETAILS_SID_PDCCP_DETAILS
                    + Constant.AND_TTCOMPANY_MASTER_SID_CCP_MAS
                    + " and SUPMAS.CCP_DETAILS_SID = PD.CCP_DETAILS_SID  "
                    + Constant.AND_PER_PERIOD_SID_SUP_PERIOD_SID
                    + "and Per.PERIOD_SID=m_mas.PERIOD_SID\n"
                    + "and pd.CCP_DETAILS_SID = m_mas.CCP_DETAILS_SID\n"
                    + "and m_mac.CCP_DETAILS_SID=pd.CCP_DETAILS_SID\n"
                    + "\n";
            if (projSelDTO.isFilterDdlb()) {
                query += Constant.AND_TT + projSelDTO.getDefinedContract() + " = '" + projSelDTO.getSelectedCust() + "' ";
            }
            if (!StringUtils.EMPTY.equalsIgnoreCase(projSelDTO.getPivotValue()) && !Constant.NULL.equalsIgnoreCase(projSelDTO.getPivotValue()) && Constant.DISCOUNT_SMALL.equalsIgnoreCase(projSelDTO.getPivotView())) {
                if ("YEAR".equalsIgnoreCase(frequency)) {
                    query += Constant.AND_PER_YEAR_EQUAL + projSelDTO.getPivotValue();
                } else {
                    String str[] = projSelDTO.getPivotValue().split(" ");
                    query += "and per." + frequency + Constant.EQUAL + str[0] + AND_SPACE + Constant.PER_YEAR_EQUAL + str[1];
                }
            }
            query += "  group by  TT.COMPANY_MASTER_SID,TT.COMPANY_NO,per.YEAR,\n"
                    + Constant.SPACE_PER + frequency + "\n"
                    + "\n"
                    + Q_FINALQ
                    + Constant.WHERE_FINALQTEMP_INDEX_GREATER + projSelDTO.getStart() + Constant.AND_FINALQTEMP_INDEX_LESSER + (projSelDTO.getStart() + projSelDTO.getOffset()) + "\n"
                    + "\n"
                    + " order by COMPANY_MASTER_SID,YEARS,PERIODS";
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            query=QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames());
            list = (List) salesProjectionDAO.executeSelectQuery(query);
        } catch (PortalException | SystemException e) {
            LOGGER.error(e);
        }

        return list;
    }
    public static final String AND_SPACE = " and ";

    public List getContractLevelValue(ProjectionSelectionDTO projSelDTO) {
        List list;
        String query = StringUtils.EMPTY;
        String frequency = projSelDTO.getFrequency();
        if (frequency.equals(Constant.QUARTERLY)) {
            frequency = Constant.QUARTER;
        }
        if (frequency.equals(ANNUALLY.getConstant())) {
            frequency = "YEAR";
        }
        if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
            frequency = Constant.SEMI_ANNUAL;

        }
        if (frequency.equals(MONTHLY.getConstant())) {
            frequency = Constant.MONTH_WITHOUT_SPACE;
        }
        try {
            if (!projSelDTO.getCurrentCustomer().equals(Constant.NULL)) {
                query = "select FINALQ.YEARS,FINALQ.PERIODS,FINALQ.COM_ID,FINALQ.CON_ID,FINALQ.BRAND_ID, FINALQ.CONTRACT_MASTER_SID,FINALQ.CONTRACT_NO,\n"
                        + "   FINALQ.LEVEL_NAME,FINALQ.Actual_Sale,FINALQ.Actual_Rate,FINALQ.Flag,FINALQ.RPU from (\n"
                        + "select Q.YEARS,Q.PERIODS,Q.COM_ID,Q.CON_ID,Q.BRAND_ID, Q.CONTRACT_MASTER_SID,Q.CONTRACT_NO,\n"
                        + "  Q.LEVEL_NAME,Q.Actual_Sale,Q.Actual_Rate,Q.Flag,Q.RPU,Dense_rank() OVER(ORDER BY Q.CONTRACT_MASTER_SID) AS TEMP_INDEX from("
                        + "select per.YEAR as YEARS,per." + frequency + "  as PERIODS," + projSelDTO.getCurrentCustomer() + " AS COM_ID,0 AS CON_ID,0 AS BRAND_ID, TT.CONTRACT_MASTER_SID AS CONTRACT_MASTER_SID,TT.CONTRACT_NO AS CONTRACT_NO,\n"
                        + " 'Contract' as LEVEL_NAME, \n"
                        + " Sum(SUPMAS.ACTUAL_SALES) AS Actual_Sale  ,\n"
                        + "Coalesce(Sum(SUPMAS.ACTUAL_SALES)/Nullif(Sum(STMAS.ACTUAL_SALES),0),0)*100       AS  Actual_Rate,\n"
                        + " 'ACT' AS Flag,Coalesce(Sum(SUPMAS.ACTUAL_SALES)/Nullif(Sum(STMAS.ACTUAL_UNITS),0),0) AS RPU  \n"
                        + "  FROM ST_CCP_HIERARCHY PD,\n"
                        + "  CCP_DETAILS CCP ,\n"
                        + "  CONTRACT_MASTER TT,\n"
                        + "  ST_M_ACTUAL_DISCOUNT SUPMAS,\n"
                        + " ST_M_ACTUAL_SALES  STMAS,\n"
                        + " ST_M_SALES_PROJECTION_MASTER STMSP, \n"
                        + "  Period per"
                        + "  where  CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                        + "  and TT.CONTRACT_MASTER_SID = CCP.CONTRACT_MASTER_SID\n"
                        + "  and SUPMAS.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                        + "  and per.PERIOD_SID=SUPMAS.PERIOD_SID\n"
                        + AND_CCPCOMPANY_MASTER_SID + projSelDTO.getCurrentCustomer() + " \n"
                        + "and STMAS.CCP_DETAILS_SID=PD.CCP_DETAILS_SID\n"
                        + "  and STMAS.PERIOD_SID= per.PERIOD_SID\n"
                        + "  and STMSP.CCP_DETAILS_SID=PD.CCP_DETAILS_SID \n";
                if (!StringUtils.EMPTY.equalsIgnoreCase(projSelDTO.getPivotValue()) && !Constant.NULL.equalsIgnoreCase(projSelDTO.getPivotValue()) && Constant.DISCOUNT_SMALL.equalsIgnoreCase(projSelDTO.getPivotView())) {
                    if ("YEAR".equalsIgnoreCase(frequency)) {
                        query += Constant.AND_PER_YEAR_EQUAL + projSelDTO.getPivotValue();
                    } else {
                        String str[] = projSelDTO.getPivotValue().split(" ");
                        query += Constant.AND_PER_DOT + frequency + Constant.EQUAL + str[0] + AND_SPACE + Constant.PER_YEAR_EQUAL + str[1];
                    }
                }
                query += "  group by  TT.CONTRACT_MASTER_SID,TT.CONTRACT_NO,per.YEAR,per." + frequency + "\n"
                        + "  UNION\n"
                        + "select per.YEAR as YEARS, per." + frequency + " as PERIODS," + projSelDTO.getCurrentCustomer() + " AS COM_ID,0 AS CON_ID,0 AS BRAND_ID, TT.CONTRACT_MASTER_SID AS CONTRACT_MASTER_SID,TT.CONTRACT_NO AS CONTRACT_NO,\n"
                        + " 'Contract' as LEVEL_NAME, \n"
                        + " Sum(SUPMAS.PROJECTION_SALES)AS Actual_Sale,\n"
                        + "Coalesce(Sum(SUPMAS.PROJECTION_SALES)/Nullif(Sum(STSP.PROJECTION_SALES),0),0)*100       AS  Actual_Rate,\n"
                        + " 'Proj' AS Flag,Coalesce(Sum(SUPMAS.PROJECTION_SALES)/Nullif(Sum(STSP.PROJECTION_UNITS),0),0) AS RPU \n"
                        + "\n"
                        + "  FROM ST_CCP_HIERARCHY  PD,\n"
                        + "  CCP_DETAILS CCP, \n"
                        + "  CONTRACT_MASTER TT,\n"
                        + "  ST_M_DISCOUNT_PROJECTION SUPMAS,\n"
                        + "ST_M_SALES_PROJECTION STSP,\n"
                        + "ST_M_SALES_PROJECTION_MASTER STMSP, \n"
                        + "  Period per"
                        + "  where  CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                        + "  and TT.CONTRACT_MASTER_SID = CCP.CONTRACT_MASTER_SID\n"
                        + "  and SUPMAS.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                        + "  and per.PERIOD_SID=SUPMAS.PERIOD_SID\n"
                        + AND_CCPCOMPANY_MASTER_SID + projSelDTO.getCurrentCustomer() + " \n"
                        + " and STSP.CCP_DETAILS_SID=PD.CCP_DETAILS_SID\n"
                        + "  and STSP.PERIOD_SID= per.PERIOD_SID\n"
                        + "  and STMSP.CCP_DETAILS_SID=PD.CCP_DETAILS_SID\n";
                if (!StringUtils.EMPTY.equalsIgnoreCase(projSelDTO.getPivotValue()) && !Constant.NULL.equalsIgnoreCase(projSelDTO.getPivotValue()) && Constant.DISCOUNT_SMALL.equalsIgnoreCase(projSelDTO.getPivotView())) {
                    if ("YEAR".equalsIgnoreCase(frequency)) {
                        query += Constant.AND_PER_YEAR_EQUAL + projSelDTO.getPivotValue();
                    } else {
                        String str[] = projSelDTO.getPivotValue().split(" ");
                        query += Constant.AND_PER_DOT + frequency + Constant.EQUAL + str[0] + AND_SPACE + Constant.PER_YEAR_EQUAL + str[1];
                    }
                }
                query += "  group by  TT.CONTRACT_MASTER_SID,TT.CONTRACT_NO,per.YEAR,per." + frequency + "\n"
                        + "     "
                        + Q_FINALQ
                        + Constant.WHERE_FINALQTEMP_INDEX_GREATER + projSelDTO.getStart() + Constant.AND_FINALQTEMP_INDEX_LESSER + (projSelDTO.getStart() + projSelDTO.getOffset()) + "\n"
                        + "\n"
                        + " order by CONTRACT_MASTER_SID,YEARS,PERIODS";

            }
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            query=QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames());
            list = (List) salesProjectionDAO.executeSelectQuery(query);
            return list;
        } catch (PortalException | SystemException e) {
            LOGGER.error(e);
            return Collections.emptyList();
        }

    }
    public static final String AND_CCPCOMPANY_MASTER_SID = "  and CCP.COMPANY_MASTER_SID = ";

    public List getBrandLevelValue(ProjectionSelectionDTO projSelDTO) {
        List list = new ArrayList();
        String query;
        String frequency = projSelDTO.getFrequency();
        if (frequency.equals(Constant.QUARTERLY)) {
            frequency = Constant.QUARTER;
        }
        if (frequency.equals(ANNUALLY.getConstant())) {
            frequency = "YEAR";
        }
        if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
            frequency = Constant.SEMI_ANNUAL;

        }
        if (frequency.equals(MONTHLY.getConstant())) {
            frequency = Constant.MONTH_WITHOUT_SPACE;
        }
        try {
            if (!Constant.NULL.equals(projSelDTO.getCurrentContract()) && !Constant.NULL.equals(projSelDTO.getCurrentCustomer())) {
                query = "  select FINALQ.YEARS,FINALQ.PERIODS,FINALQ.COM_ID,FINALQ.CON_ID,FINALQ.BRAND_ID, FINALQ.BRAND_MASTER_SID,FINALQ.BRAND_NAME,\n"
                        + "    FINALQ.LEVEL_NAME,FINALQ.Actual_Sale,FINALQ.Actual_Rate,FINALQ.Flag,FINALQ.RPU from(\n"
                        + " select Q.YEARS,Q.PERIODS,Q.COM_ID,Q.CON_ID,Q.BRAND_ID, Q.BRAND_MASTER_SID,Q.BRAND_NAME,\n"
                        + "    Q.LEVEL_NAME,Q.Actual_Sale,Q.Actual_Rate,Q.Flag,Q.RPU,Dense_rank() OVER (ORDER BY Q.BRAND_MASTER_SID) AS TEMP_INDEX from("
                        + "select per.YEAR AS YEARS, per." + frequency + " AS PERIODS ," + projSelDTO.getCurrentCustomer() + " AS COM_ID," + projSelDTO.getCurrentContract() + " AS CON_ID,0 AS BRAND_ID,TT.BRAND_MASTER_SID AS BRAND_MASTER_SID,TT.BRAND_NAME AS BRAND_NAME,\n"
                        + "'Brand' as LEVEL_NAME, \n"
                        + "sum(SUPMAS.ACTUAL_SALES)AS Actual_Sale,\n"
                        + "Coalesce(Sum(SUPMAS.ACTUAL_SALES)/Nullif(Sum(STMAS.ACTUAL_SALES),0),0)*100       AS  Actual_Rate,\n"
                        + "'ACT' AS Flag,Coalesce(Sum(SUPMAS.ACTUAL_SALES)/Nullif(Sum(STMAS.ACTUAL_UNITS),0),0) AS RPU \n"
                        + "\n"
                        + FROM_ST_CCP_HIERARCHY_PD
                        + " CCP_DETAILS CCP, \n"
                        + "ITEM_MASTER IM , \n"
                        + " BRAND_MASTER TT,\n"
                        + " ST_M_ACTUAL_DISCOUNT SUPMAS ,\n"
                        + " ST_M_ACTUAL_SALES STMAS,\n"
                        + "ST_M_SALES_PROJECTION_MASTER STMSPM, \n"
                        + " Period per"
                        + WHERE_CCPCCP_DETAILS_SID_PDCCP_DETAILS
                        + "and TT.BRAND_MASTER_SID =  IM.BRAND_MASTER_SID\n"
                        + " and SUPMAS.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                        + " and per.PERIOD_SID=SUPMAS.PERIOD_SID\n"
                        + AND_CCP_COMPANY_MASTER_SID + projSelDTO.getCurrentCustomer() + "\n"
                        + "  and CCP.CONTRACT_MASTER_SID = " + projSelDTO.getCurrentContract() + "\n"
                        + " and CCP.ITEM_MASTER_SID = IM.ITEM_MASTER_SID  \n"
                        + "and STMAS.CCP_DETAILS_SID=PD.CCP_DETAILS_SID\n"
                        + "and STMAS.PERIOD_SID=per.PERIOD_SID\n"
                        + "and STMSPM.CCP_DETAILS_SID=PD.CCP_DETAILS_SID\n";
                if (!StringUtils.EMPTY.equalsIgnoreCase(projSelDTO.getPivotValue()) && !Constant.NULL.equalsIgnoreCase(projSelDTO.getPivotValue()) && Constant.DISCOUNT_SMALL.equalsIgnoreCase(projSelDTO.getPivotView())) {
                    if ("YEAR".equalsIgnoreCase(frequency)) {
                        query += Constant.AND_PER_YEAR_EQUAL + projSelDTO.getPivotValue();
                    } else {
                        String str[] = projSelDTO.getPivotValue().split(" ");
                        query += Constant.AND_PER_DOT + frequency + Constant.EQUAL + str[0] + AND_SPACE + Constant.PER_YEAR_EQUAL + str[1];
                    }
                }
                query += "  group by TT.BRAND_MASTER_SID,TT.BRAND_NAME, per.YEAR,per." + frequency + " \n"
                        + "  \n"
                        + "  union \n"
                        + "  \n"
                        + "select per.YEAR AS YEARS, per." + frequency + "  AS PERIODS ," + projSelDTO.getCurrentCustomer() + " AS COM_ID ," + projSelDTO.getCurrentContract() + " AS CON_ID,0 AS BRAND_ID,TT.BRAND_MASTER_SID AS BRAND_MASTER_SID,TT.BRAND_NAME AS BRAND_NAME,\n"
                        + "'Brand' as LEVEL_NAME, \n"
                        + "sum(SUPMAS.PROJECTION_SALES)AS Projection_Sale,\n"
                        + "Coalesce(Sum(SUPMAS.PROJECTION_SALES)/Nullif(Sum(STSP.PROJECTION_SALES),0),0)*100       AS  Actual_Rate,\n"
                        + "'Proj'AS Flag,Coalesce(Sum(SUPMAS.PROJECTION_SALES)/Nullif(Sum(STSP.PROJECTION_UNITS),0),0) AS RPU \n"
                        + "\n"
                        + FROM_ST_CCP_HIERARCHY_PD
                        + " CCP_DETAILS CCP ,\n"
                        + "ITEM_MASTER IM ,  \n"
                        + " BRAND_MASTER TT,\n"
                        + " ST_M_DISCOUNT_PROJECTION SUPMAS ,\n"
                        + "ST_M_SALES_PROJECTION STSP,\n"
                        + "ST_M_SALES_PROJECTION_MASTER STMSPM ,\n"
                        + " Period per"
                        + WHERE_CCPCCP_DETAILS_SID_PDCCP_DETAILS
                        + "and TT.BRAND_MASTER_SID  = IM.BRAND_MASTER_SID\n"
                        + " and SUPMAS.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                        + " and per.PERIOD_SID=SUPMAS.PERIOD_SID\n"
                        + AND_CCP_COMPANY_MASTER_SID + projSelDTO.getCurrentCustomer() + "\n"
                        + "  and CCP.CONTRACT_MASTER_SID = " + projSelDTO.getCurrentContract() + "\n"
                        + " and CCP.ITEM_MASTER_SID = IM.ITEM_MASTER_SID  \n"
                        + "and STSP.CCP_DETAILS_SID=PD.CCP_DETAILS_SID\n"
                        + "and STSP.PERIOD_SID=per.PERIOD_SID\n"
                        + "and STMSPM.CCP_DETAILS_SID=PD.CCP_DETAILS_SID\n";
                if (!StringUtils.EMPTY.equalsIgnoreCase(projSelDTO.getPivotValue()) && !Constant.NULL.equalsIgnoreCase(projSelDTO.getPivotValue()) && Constant.DISCOUNT_SMALL.equalsIgnoreCase(projSelDTO.getPivotView())) {
                    if ("YEAR".equalsIgnoreCase(frequency)) {
                        query += Constant.AND_PER_YEAR_EQUAL + projSelDTO.getPivotValue();
                    } else {
                        String str[] = projSelDTO.getPivotValue().split(" ");
                        query += Constant.AND_PER_DOT + frequency + Constant.EQUAL + str[0] + AND_SPACE + Constant.PER_YEAR_EQUAL + str[1];
                    }
                }
                query += "  group by TT.BRAND_MASTER_SID,TT.BRAND_NAME, per.YEAR,per." + frequency + " \n"
                        + "\n"
                        + "  \n"
                        + "  "
                        + Q_FINALQ
                        + Constant.WHERE_FINALQTEMP_INDEX_GREATER + projSelDTO.getStart() + Constant.AND_FINALQTEMP_INDEX_LESSER + (projSelDTO.getStart() + projSelDTO.getOffset()) + "\n"
                        + "\n"
                        + " order by BRAND_MASTER_SID,YEARS,PERIODS";

                SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
                query=QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames());
                list = (List) salesProjectionDAO.executeSelectQuery(query);

            }

        } catch (PortalException | SystemException e) {
            LOGGER.error(e);
        }

        return list;
    }
    public static final String AND_CCP_COMPANY_MASTER_SID = " and CCP.COMPANY_MASTER_SID = ";
    public static final String FROM_ST_CCP_HIERARCHY_PD = "FROM ST_CCP_HIERARCHY PD,\n";

    public List getSuppLevelValue(ProjectionSelectionDTO projSelDTO) {
        List list = new ArrayList();
        String query = StringUtils.EMPTY;
        String frequency = projSelDTO.getFrequency();
        if (frequency.equals(Constant.QUARTERLY)) {
            frequency = Constant.QUARTER;
        }
        if (frequency.equals(ANNUALLY.getConstant())) {
            frequency = "YEAR";
        }
        if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
            frequency = Constant.SEMI_ANNUAL;

        }
        if (frequency.equals(MONTHLY.getConstant())) {
            frequency = Constant.MONTH_WITHOUT_SPACE;
        }

        try {
            query += "select FINALQ.YEARS,FINALQ.PERIODS,FINALQ.COM_ID,FINALQ.CON_ID,FINALQ.BRAND_ID, FINALQ.COMPANY_MASTER_SID,FINALQ.COMPANY_NO,\n"
                    + "   FINALQ.LEVEL_NAME,FINALQ.Actual_Sale,FINALQ.Actual_Rate,FINALQ.Flag,FINALQ.RPU from(\n"
                    + "select Q.YEARS,Q.PERIODS,Q.COM_ID,Q.CON_ID,Q.BRAND_ID, Q.COMPANY_MASTER_SID,Q.COMPANY_NO,\n"
                    + "   Q.LEVEL_NAME,Q.Actual_Sale,Q.Actual_Rate,Q.Flag,Q.RPU,Dense_rank() OVER (ORDER BY Q.COMPANY_MASTER_SID) AS TEMP_INDEX from("
                    + " select per.YEAR AS YEARS, per." + frequency + " AS PERIODS, 0 AS COM_ID ,0 AS CON_ID,0 AS BRAND_ID, TT.COMPANY_MASTER_SID AS COMPANY_MASTER_SID ,TT.COMPANY_NO AS COMPANY_NO,\n"
                    + Constant.CUSTOMER_AS_LEVEL_NAME
                    + "\n"
                    + " sum(ST_M_DIS_ACT.ACTUAL_SALES) As Actual_Sale,\n"
                    + "Coalesce(Sum(ST_M_DIS_ACT.ACTUAL_SALES)/Nullif(Sum(ST_M_ACT_SAL.ACTUAL_SALES),0),0)*100     AS  Actual_Rate,"
                    + " 'ACT' As Flag,Coalesce(Sum(ST_M_DIS_ACT.ACTUAL_SALES)/Nullif(Sum(ST_M_ACT_SAL.ACTUAL_UNITS),0),0) As RPU \n"
                    + "\n"
                    + Constant.FROM_ST_CCP_HIERARCHY_PD
                    + Constant.CCP_DETAILS_CCP
                    + Constant.COMPANY_MASTER_TT
                    + " ST_M_SUPPLEMENTAL_DISC_ACTUALS ST_M_DIS_ACT, \n"
                    + " ST_M_ACTUAL_SALES               ST_M_ACT_SAL,\n"
                    + "\n"
                    + "ST_M_SALES_PROJECTION_MASTER     ST_M_SAL_PROJ_MAS,  \n"
                    + PERIOD_PER_N
                    + Constant.WHERE_CCPCCP_DETAILS_SID_PDCCP_DETAILS
                    + Constant.AND_TTCOMPANY_MASTER_SID_CCP_MAS
                    + "  and  ST_M_DIS_ACT.PERIOD_SID =Per.PERIOD_SID \n"
                    + "and ST_M_DIS_ACT.CCP_DETAILS_SID=PD.CCP_DETAILS_SID \n"
                    + "and ST_M_ACT_SAL.CCP_DETAILS_SID = ST_M_DIS_ACT.CCP_DETAILS_SID\n"
                    + "and ST_M_ACT_SAL.PERIOD_SID=Per.PERIOD_SID\n"
                    + "\n"
                    + AND_ST_M_SAL_PROJ_MASCCP_DETAILS_SIDPDCCP
                    + "\n"
                    + "\n";
            if (projSelDTO.isFilterDdlb()) {
                query += Constant.AND_TT + projSelDTO.getDefinedContract() + " = '" + projSelDTO.getSelectedCust() + "' ";
            }
            if (!StringUtils.EMPTY.equalsIgnoreCase(projSelDTO.getPivotValue()) && !Constant.NULL.equalsIgnoreCase(projSelDTO.getPivotValue()) && Constant.DISCOUNT_SMALL.equalsIgnoreCase(projSelDTO.getPivotView())) {
                if ("YEAR".equalsIgnoreCase(frequency)) {
                    query += Constant.AND_PER_YEAR + projSelDTO.getPivotValue();
                } else {
                    String str[] = projSelDTO.getPivotValue().split(" ");
                    query += Constant.AND_PER_DOT + frequency + Constant.EQUAL + str[0] + AND_SPACE + Constant.PER_YEAR_EQUAL + str[1];
                }
            }
            query += "  group by  TT.COMPANY_MASTER_SID,TT.COMPANY_NO,per.YEAR,\n"
                    + Constant.SPACE_PER + frequency + "\n"
                    + "\n"
                    + Constant.UNION
                    + "\n"
                    + " select per.YEAR AS  YEARS,per." + frequency + " AS PERIODS, 0 AS COM_ID ,0 AS CON_ID,0 AS BRAND_ID, TT.COMPANY_MASTER_SID AS COMPANY_MASTER_SID ,TT.COMPANY_NO AS COMPANY_NO,\n"
                    + Constant.CUSTOMER_AS_LEVEL_NAME
                    + "\n"
                    + " sum(ST_M_DIS_Proj.PROJECTION_SALES) As Actual_Sale,\n"
                    + "Coalesce(Sum(ST_M_DIS_Proj.PROJECTION_SALES)/Nullif(Sum(ST_M_PROJ_SAL.PROJECTION_SALES),0),0)*100       AS  Actual_Rate,"
                    + " 'PROJ' As Flag,Coalesce(Sum(ST_M_DIS_Proj.PROJECTION_SALES)/Nullif(Sum(ST_M_PROJ_SAL.PROJECTION_UNITS),0),0) As RPU \n"
                    + "\n"
                    + Constant.FROM_ST_CCP_HIERARCHY_PD
                    + Constant.CCP_DETAILS_CCP
                    + Constant.COMPANY_MASTER_TT
                    + " ST_M_SUPPLEMENTAL_DISC_PROJ ST_M_DIS_Proj,\n"
                    + " ST_M_SALES_PROJECTION       ST_M_PROJ_SAL,\n"
                    + "\n"
                    + "ST_M_SALES_PROJECTION_MASTER     ST_M_SAL_PROJ_MAS,  \n"
                    + PERIOD_PER_N
                    + Constant.WHERE_CCPCCP_DETAILS_SID_PDCCP_DETAILS
                    + Constant.AND_TTCOMPANY_MASTER_SID_CCP_MAS
                    + "  and  ST_M_DIS_Proj.PERIOD_SID =Per.PERIOD_SID\n"
                    + "and ST_M_DIS_Proj.CCP_DETAILS_SID=PD.CCP_DETAILS_SID\n"
                    + "and ST_M_PROJ_SAL.CCP_DETAILS_SID = ST_M_DIS_Proj.CCP_DETAILS_SID\n"
                    + "and ST_M_PROJ_SAL.PERIOD_SID = Per.PERIOD_SID\n"
                    + "\n"
                    + "and ST_M_SAL_PROJ_MAS.CCP_DETAILS_SID= PD.CCP_DETAILS_SID\n";
            if (projSelDTO.isFilterDdlb()) {
                query += Constant.AND_TT + projSelDTO.getDefinedContract() + " = '" + projSelDTO.getSelectedCust() + "' ";
            }
            if (!StringUtils.EMPTY.equalsIgnoreCase(projSelDTO.getPivotValue()) && !Constant.NULL.equalsIgnoreCase(projSelDTO.getPivotValue()) && Constant.DISCOUNT_SMALL.equalsIgnoreCase(projSelDTO.getPivotView())) {
                if ("YEAR".equalsIgnoreCase(frequency)) {
                    query += Constant.AND_PER_YEAR + projSelDTO.getPivotValue();
                } else {
                    String str[] = projSelDTO.getPivotValue().split(" ");
                    query += Constant.AND_PER_DOT + frequency + Constant.EQUAL + str[0] + AND_SPACE + Constant.PER_YEAR_EQUAL + str[1];
                }
            }
            query += " group by  TT.COMPANY_MASTER_SID,TT.COMPANY_NO,per.YEAR,\n"
                    + Constant.SPACE_PER + frequency + "\n"
                    + "\n"
                    + "  "
                    + Q_FINALQ
                    + Constant.WHERE_FINALQTEMP_INDEX_GREATER + projSelDTO.getStart() + Constant.AND_FINALQTEMP_INDEX_LESSER + (projSelDTO.getStart() + projSelDTO.getOffset()) + "\n"
                    + "\n"
                    + " order by COMPANY_MASTER_SID,YEARS,PERIODS";

            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            query=QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames());
            list = (List) salesProjectionDAO.executeSelectQuery(query);
        } catch (PortalException | SystemException e) {
            LOGGER.error(e);
        }

        return list;
    }
    public static final String AND_ST_M_SAL_PROJ_MASCCP_DETAILS_SIDPDCCP = "and ST_M_SAL_PROJ_MAS.CCP_DETAILS_SID=PD.CCP_DETAILS_SID\n";
    public static final String PERIOD_PER_N = " Period per\n";

    public List getSuppContractLevelValue(ProjectionSelectionDTO projSelDTO) {
        List list;
        String query = StringUtils.EMPTY;
        String frequency = projSelDTO.getFrequency();
        if (frequency.equals(Constant.QUARTERLY)) {
            frequency = Constant.QUARTER;
        }
        if (frequency.equals(ANNUALLY.getConstant())) {
            frequency = "YEAR";
        }
        if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
            frequency = Constant.SEMI_ANNUAL;

        }
        if (frequency.equals(MONTHLY.getConstant())) {
            frequency = Constant.MONTH_WITHOUT_SPACE;
        }
        try {
            if (!projSelDTO.getCurrentCustomer().equals(Constant.NULL)) {
                query = "select FINALQ.YEARS,FINALQ.PERIODS,FINALQ.COM_ID,FINALQ.CON_ID,FINALQ.BRAND_ID, FINALQ.CONTRACT_MASTER_SID,FINALQ.CONTRACT_NO,\n"
                        + "    FINALQ.LEVEL_NAME,FINALQ.Actual_Sale,FINALQ.Actual_Rate,FINALQ.Flag,FINALQ.RPU from(\n"
                        + " select Q.YEARS,Q.PERIODS,Q.COM_ID,Q.CON_ID,Q.BRAND_ID, Q.CONTRACT_MASTER_SID,Q.CONTRACT_NO,\n"
                        + "   Q.LEVEL_NAME,Q.Actual_Sale,Q.Actual_Rate,Q.Flag,Q.RPU,Dense_rank() OVER (ORDER BY Q.CONTRACT_MASTER_SID) AS TEMP_INDEX from("
                        + "select  per.YEAR AS YEARS,per." + frequency + " AS PERIODS," + projSelDTO.getCurrentCustomer() + " AS COM_ID,0 AS CON_ID,0 AS BRAND_ID, TT.CONTRACT_MASTER_SID AS CONTRACT_MASTER_SID ,TT.CONTRACT_NO AS CONTRACT_NO,\n"
                        + " 'Contract' as LEVEL_NAME,\n"
                        + "SUM(ST_M_DIS_ACT.ACTUAL_SALES) As Actual_Sale,\n"
                        + "Coalesce(Sum(ST_M_DIS_ACT.ACTUAL_SALES)/Nullif(Sum(ST_M_ACT_SAL.ACTUAL_SALES),0),0)*100       AS  Actual_Rate,"
                        + "'ACT' As Flag,Coalesce(Sum(ST_M_DIS_ACT.ACTUAL_SALES)/Nullif(Sum(ST_M_ACT_SAL.ACTUAL_UNITS),0),0) As RPU \n"
                        + " FROM \n"
                        + "  ST_CCP_HIERARCHY PD,\n"
                        + Constant.CCP_DETAILS_CCP
                        + " CONTRACT_MASTER TT,\n"
                        + " ST_M_SUPPLEMENTAL_DISC_ACTUALS ST_M_DIS_ACT,\n"
                        + "  ST_M_ACTUAL_SALES               ST_M_ACT_SAL,\n"
                        + "\n"
                        + " ST_M_SALES_PROJECTION_MASTER     ST_M_SAL_PROJ_MAS,  \n"
                        + PERIOD_PER_N
                        + Constant.WHERE_CCPCCP_DETAILS_SID_PDCCP_DETAILS
                        + "  and TT.CONTRACT_MASTER_SID =  CCP.CONTRACT_MASTER_SID\n"
                        + AND_CCPCOMPANY_MASTER_SID + projSelDTO.getCurrentCustomer() + "  \n"
                        + "  and  ST_M_DIS_ACT.PERIOD_SID =Per.PERIOD_SID\n"
                        + "and  ST_M_DIS_ACT.CCP_DETAILS_SID=PD.CCP_DETAILS_SID\n"
                        + "and ST_M_ACT_SAL.CCP_DETAILS_SID =ST_M_DIS_ACT.CCP_DETAILS_SID\n"
                        + "and ST_M_ACT_SAL.PERIOD_SID=Per.PERIOD_SID\n"
                        + "\n"
                        + "and ST_M_SAL_PROJ_MAS.CCP_DETAILS_SID =PD.CCP_DETAILS_SID\n"
                        + "\n";
                if (!StringUtils.EMPTY.equalsIgnoreCase(projSelDTO.getPivotValue()) && !Constant.NULL.equalsIgnoreCase(projSelDTO.getPivotValue()) && Constant.DISCOUNT_SMALL.equalsIgnoreCase(projSelDTO.getPivotView())) {
                    if ("YEAR".equalsIgnoreCase(frequency)) {
                        query += Constant.AND_PER_YEAR + projSelDTO.getPivotValue();
                    } else {
                        String str[] = projSelDTO.getPivotValue().split(" ");
                        query += Constant.AND_PER_DOT + frequency + Constant.EQUAL + str[0] + AND_SPACE + Constant.PER_YEAR_EQUAL + str[1];
                    }
                }
                query += "group by  TT.CONTRACT_MASTER_SID,TT.CONTRACT_NO, per.YEAR,per." + frequency + "\n"
                        + "\n"
                        + Constant.UNION
                        + " select per.YEAR AS YEARS,per." + frequency + " AS PERIODS," + projSelDTO.getCurrentCustomer() + " AS COM_ID,0 AS CON_ID,0 AS BRAND_ID, TT.CONTRACT_MASTER_SID AS CONTRACT_MASTER_SID ,TT.CONTRACT_NO AS CONTRACT_NO,\n"
                        + " 'Contract' as LEVEL_NAME,\n"
                        + "SUM(ST_M_DIS_PROJ.PROJECTION_SALES) As Actual_Sale,\n"
                        + "Coalesce(Sum(ST_M_DIS_Proj.PROJECTION_SALES)/Nullif(Sum(ST_M_PROJ_SAL.PROJECTION_SALES),0),0)*100       AS  Proj_rate,"
                        + "'PROJ' As Flag,Coalesce(Sum(ST_M_DIS_Proj.PROJECTION_SALES)/Nullif(Sum(ST_M_PROJ_SAL.PROJECTION_UNITS),0),0) As RPU \n"
                        + " FROM \n"
                        + " ST_CCP_HIERARCHY PD,\n"
                        + Constant.CCP_DETAILS_CCP
                        + " CONTRACT_MASTER TT,\n"
                        + " ST_M_SUPPLEMENTAL_DISC_PROJ ST_M_DIS_PROJ,\n"
                        + " ST_M_SALES_PROJECTION               ST_M_PROJ_SAL,\n"
                        + "\n"
                        + "ST_M_SALES_PROJECTION_MASTER    ST_M_SAL_PROJ_MAS,  \n"
                        + PERIOD_PER_N
                        + Constant.WHERE_CCPCCP_DETAILS_SID_PDCCP_DETAILS
                        + "  and  TT.CONTRACT_MASTER_SID = CCP.CONTRACT_MASTER_SID\n"
                        + AND_CCPCOMPANY_MASTER_SID + projSelDTO.getCurrentCustomer() + "  \n"
                        + "  and  ST_M_DIS_PROJ.PERIOD_SID =Per.PERIOD_SID\n"
                        + "and ST_M_DIS_PROJ.CCP_DETAILS_SID=PD.CCP_DETAILS_SID\n"
                        + "and ST_M_PROJ_SAL.CCP_DETAILS_SID=ST_M_DIS_PROJ.CCP_DETAILS_SID\n"
                        + "and ST_M_PROJ_SAL.PERIOD_SID=Per.PERIOD_SID\n"
                        + "\n"
                        + "and  ST_M_SAL_PROJ_MAS.CCP_DETAILS_SID=PD.CCP_DETAILS_SID\n"
                        + "\n";
                if (!StringUtils.EMPTY.equalsIgnoreCase(projSelDTO.getPivotValue()) && !Constant.NULL.equalsIgnoreCase(projSelDTO.getPivotValue()) && Constant.DISCOUNT_SMALL.equalsIgnoreCase(projSelDTO.getPivotView())) {
                    if ("YEAR".equalsIgnoreCase(frequency)) {
                        query += Constant.AND_PER_YEAR + projSelDTO.getPivotValue();
                    } else {
                        String str[] = projSelDTO.getPivotValue().split(" ");
                        query += Constant.AND_PER_DOT + frequency + Constant.EQUAL + str[0] + AND_SPACE + Constant.PER_YEAR_EQUAL + str[1];
                    }
                }

                query += "group by  TT.CONTRACT_MASTER_SID,TT.CONTRACT_NO, per.YEAR,per." + frequency + "\n"
                        + "\n"
                        + StringUtils.EMPTY
                        + Q_FINALQ
                        + Constant.WHERE_FINALQTEMP_INDEX_GREATER + projSelDTO.getStart() + Constant.AND_FINALQTEMP_INDEX_LESSER + (projSelDTO.getStart() + projSelDTO.getOffset()) + "\n"
                        + "\n"
                        + " order by CONTRACT_MASTER_SID,YEARS,PERIODS";

            }
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
                query=QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames());
            list = (List) salesProjectionDAO.executeSelectQuery(query);
            return list;
        } catch (PortalException | SystemException e) {
            LOGGER.error(e);
            return Collections.emptyList();
        }

    }

    public List getSuppBrandLevelValue(ProjectionSelectionDTO projSelDTO) {
        List list = new ArrayList();
        String query;
        String frequency = projSelDTO.getFrequency();
        if (frequency.equals(Constant.QUARTERLY)) {
            frequency = Constant.QUARTER;
        }
        if (frequency.equals(ANNUALLY.getConstant())) {
            frequency = "YEAR";
        }
        if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
            frequency = Constant.SEMI_ANNUAL;

        }
        if (frequency.equals(MONTHLY.getConstant())) {
            frequency = Constant.MONTH_WITHOUT_SPACE;
        }
        try {
            if (!Constant.NULL.equals(projSelDTO.getCurrentContract()) && !Constant.NULL.equals(projSelDTO.getCurrentCustomer())) {
                query = "select FINALQ.YEARS,FINALQ.PERIODS,FINALQ.COM_ID,FINALQ.CON_ID,FINALQ.BRAND_ID, FINALQ.BRAND_MASTER_SID,FINALQ.BRAND_NAME,\n"
                        + "   FINALQ.LEVEL_NAME,FINALQ.Actual_Sale,FINALQ.Actual_Rate,FINALQ.Flag,FINALQ.RPU from(\n"
                        + "select Q.YEARS,Q.PERIODS,Q.COM_ID,Q.CON_ID,Q.BRAND_ID, Q.BRAND_MASTER_SID,Q.BRAND_NAME,\n"
                        + "   Q.LEVEL_NAME,Q.Actual_Sale,Q.Actual_Rate,Q.Flag,Q.RPU,Dense_rank() OVER (ORDER BY Q.BRAND_MASTER_SID) AS TEMP_INDEX from("
                        + "select per.YEAR AS YEARS,per." + frequency + " AS  PERIODS ," + projSelDTO.getCurrentCustomer() + " AS  COM_ID," + projSelDTO.getCurrentContract() + " AS CON_ID,0 AS BRAND_ID,TT.BRAND_MASTER_SID AS BRAND_MASTER_SID ,TT.BRAND_NAME AS BRAND_NAME,\n"
                        + "'Brand' as LEVEL_NAME,\n"
                        + "sum(ST_M_DIS_ACT.ACTUAL_SALES)AS Actual_Sale,\n"
                        + "Coalesce(Sum(ST_M_DIS_ACT.ACTUAL_SALES)/Nullif(Sum(ST_M_ACT_SAL.ACTUAL_SALES),0),0)*100       AS  Actual_Rate,"
                        + "'ACT' AS Flag,Coalesce(Sum(ST_M_DIS_ACT.ACTUAL_SALES)/Nullif(Sum(ST_M_ACT_SAL.ACTUAL_UNITS),0),0) AS RPU \n"
                        + "\n"
                        + FROM_ST_CCP_HIERARCHY_PD
                        + "CCP_DETAILS CCP,\n"
                        + "ITEM_MASTER IM  , \n"
                        + "  BRAND_MASTER TT,\n"
                        + "ST_M_SUPPLEMENTAL_DISC_ACTUALS ST_M_DIS_ACT,\n"
                        + " ST_M_ACTUAL_SALES              ST_M_ACT_SAL,\n"
                        + "\n"
                        + "ST_M_SALES_PROJECTION_MASTER     ST_M_SAL_PROJ_MAS,\n"
                        + PERIOD_PER_N
                        + WHERE_CCPCCP_DETAILS_SID_PDCCP_DETAILS
                        + "and  TT.BRAND_MASTER_SID = IM.BRAND_MASTER_SID\n"
                        + "and ST_M_DIS_ACT.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                        + "and CCP.ITEM_MASTER_SID = IM.ITEM_MASTER_SID\n"
                        + "and per.PERIOD_SID=ST_M_DIS_ACT.PERIOD_SID\n"
                        + "and CCP.COMPANY_MASTER_SID = " + projSelDTO.getCurrentCustomer() + " \n"
                        + "and CCP.CONTRACT_MASTER_SID = " + projSelDTO.getCurrentContract() + "\n"
                        + "and ST_M_DIS_ACT.CCP_DETAILS_SID =PD.CCP_DETAILS_SID\n"
                        + "and ST_M_ACT_SAL.CCP_DETAILS_SID=ST_M_DIS_ACT.CCP_DETAILS_SID\n"
                        + "and ST_M_ACT_SAL.PERIOD_SID =Per.PERIOD_SID\n"
                        + "\n"
                        + AND_ST_M_SAL_PROJ_MASCCP_DETAILS_SIDPDCCP;
                if (!StringUtils.EMPTY.equalsIgnoreCase(projSelDTO.getPivotValue()) && !Constant.NULL.equalsIgnoreCase(projSelDTO.getPivotValue()) && Constant.DISCOUNT_SMALL.equalsIgnoreCase(projSelDTO.getPivotView())) {
                    if ("YEAR".equalsIgnoreCase(frequency)) {
                        query += Constant.AND_PER_YEAR + projSelDTO.getPivotValue();
                    } else {
                        String str[] = projSelDTO.getPivotValue().split(" ");
                        query += Constant.AND_PER_DOT + frequency + Constant.EQUAL + str[0] + AND_SPACE + Constant.PER_YEAR_EQUAL + str[1];
                    }
                }
                query += "  group by TT.BRAND_MASTER_SID, TT.BRAND_NAME, per.YEAR,per." + frequency + "\n"
                        + "\n"
                        + "\n"
                        + "  union\n"
                        + "\n"
                        + "select per.YEAR AS YEARS,per." + frequency + " AS PERIODS  ," + projSelDTO.getCurrentCustomer() + " AS COM_ID," + projSelDTO.getCurrentContract() + " AS CON_ID,0 AS BRAND_ID,TT.BRAND_MASTER_SID AS BRAND_MASTER_SID ,TT.BRAND_NAME AS BRAND_NAME,\n"
                        + "'Brand' as LEVEL_NAME,\n"
                        + "sum(ST_M_DIS_ACT.PROJECTION_SALES)AS Actual_Sale,\n"
                        + "Coalesce(Sum(ST_M_DIS_ACT.PROJECTION_SALES)/Nullif(Sum(ST_M_ACT_SAL.PROJECTION_SALES),0),0)*100       AS  Proj_rate,"
                        + "'Proj' AS Flag,Coalesce(Sum(ST_M_DIS_ACT.PROJECTION_SALES)/Nullif(Sum(ST_M_ACT_SAL.PROJECTION_UNITS),0),0) AS RPU \n"
                        + "\n"
                        + FROM_ST_CCP_HIERARCHY_PD
                        + "CCP_DETAILS CCP,\n"
                        + "ITEM_MASTER IM,\n"
                        + "  BRAND_MASTER TT,\n"
                        + "ST_M_SUPPLEMENTAL_DISC_PROJ ST_M_DIS_ACT,\n"
                        + " ST_M_SALES_PROJECTION               ST_M_ACT_SAL,\n"
                        + "\n"
                        + "ST_M_SALES_PROJECTION_MASTER     ST_M_SAL_PROJ_MAS,\n"
                        + PERIOD_PER_N
                        + WHERE_CCPCCP_DETAILS_SID_PDCCP_DETAILS
                        + "and TT.BRAND_MASTER_SID = IM.BRAND_MASTER_SID\n"
                        + "and ST_M_DIS_ACT.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                        + "and CCP.ITEM_MASTER_SID = IM.ITEM_MASTER_SID\n"
                        + "and per.PERIOD_SID=ST_M_DIS_ACT.PERIOD_SID\n"
                        + "and  CCP.COMPANY_MASTER_SID = " + projSelDTO.getCurrentCustomer() + " \n"
                        + "and CCP.CONTRACT_MASTER_SID = " + projSelDTO.getCurrentContract() + "\n"
                        + "and ST_M_DIS_ACT.CCP_DETAILS_SID= PD.CCP_DETAILS_SID\n"
                        + "and ST_M_ACT_SAL.CCP_DETAILS_SID=ST_M_DIS_ACT.CCP_DETAILS_SID\n"
                        + "and ST_M_ACT_SAL.PERIOD_SID= Per.PERIOD_SID\n"
                        + "\n"
                        + AND_ST_M_SAL_PROJ_MASCCP_DETAILS_SIDPDCCP;
                if (!StringUtils.EMPTY.equalsIgnoreCase(projSelDTO.getPivotValue()) && !Constant.NULL.equalsIgnoreCase(projSelDTO.getPivotValue()) && Constant.DISCOUNT_SMALL.equalsIgnoreCase(projSelDTO.getPivotView())) {
                    if ("YEAR".equalsIgnoreCase(frequency)) {
                        query += Constant.AND_PER_YEAR + projSelDTO.getPivotValue();
                    } else {
                        String str[] = projSelDTO.getPivotValue().split(" ");
                        query += Constant.AND_PER_DOT + frequency + Constant.EQUAL + str[0] + AND_SPACE + Constant.PER_YEAR_EQUAL + str[1];
                    }
                }

                query += "  group by TT.BRAND_MASTER_SID,TT.BRAND_NAME, per.YEAR, per." + frequency + " \n"
                        + "\n"
                        + "  \n"
                        + "  "
                        + Q_FINALQ
                        + Constant.WHERE_FINALQTEMP_INDEX_GREATER + projSelDTO.getStart() + Constant.AND_FINALQTEMP_INDEX_LESSER + (projSelDTO.getStart() + projSelDTO.getOffset()) + "\n"
                        + "\n"
                        + " order by BRAND_MASTER_SID,YEARS,PERIODS";
                SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
                  query=QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames());
                list = (List) salesProjectionDAO.executeSelectQuery(query);

            }

        } catch (PortalException | SystemException e) {
            LOGGER.error(e);
        }

        return list;
    }

    public List<Integer> getProjectionDetailsId(ProjectionSelectionDTO projSelDTO) {
        try {
            List list;
            List<Integer> listInte = new ArrayList<>();
            String sql = "select CCP_DETAILS_SID from dbo.ST_CCP_HIERARCHY\n";

            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            
            sql=QueryUtil.replaceTableNames(sql, projSelDTO.getSessionDTO().getCurrentTableNames());
            list = (List) salesProjectionDAO.executeSelectQuery(sql);
            for (int i = 0; i < list.size(); i++) {
                listInte.add(Integer.valueOf(String.valueOf(list.get(i))));

            }

            return listInte;
        } catch (PortalException | SystemException | NumberFormatException ex) {
            LOGGER.error(ex);
            return Collections.emptyList();
        }
    }

    public List getMandatedPivotLevel(ProjectionSelectionDTO projSelDTO) {
        List list = new ArrayList();
        String query = StringUtils.EMPTY;
        String frequency = projSelDTO.getFrequency();
        if (frequency.equals(Constant.QUARTERLY)) {
            frequency = Constant.QUARTER;
        }
        if (frequency.equals(ANNUALLY.getConstant())) {
            frequency = "YEAR";
        }
        if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
            frequency = Constant.SEMI_ANNUAL;

        }
        if (frequency.equals(MONTHLY.getConstant())) {
            frequency = Constant.MONTH_WITHOUT_SPACE;
        }

        try {
            query += " select  per.YEAR,per." + frequency + ", 0 AS COM_ID,0 AS CON_ID,0 AS BRAND_ID,0 As COMPANY_MASTER_SID,0 AS COMPANY_NO,  "
                    + Constant.CUSTOMER_AS_LEVEL_NAME
                    + "\n"
                    + " sum(SUPMAS.ACTUAL_SALES) As Actual_Sale,\n"
                    + "Coalesce(Sum(SUPMAS.ACTUAL_SALES)/Nullif(Sum(STMSP.ACTUAL_SALES),0),0)*100       AS ACTUAL_Rate,\n"
                    + " 'ACT' As Flag,Coalesce(Sum(SUPMAS.ACTUAL_SALES)/Nullif(Sum(STMSP.ACTUAL_UNITS),0),0)  As RPU  \n"
                    + "\n"
                    + Constant.FROM_ST_CCP_HIERARCHY_PD
                    + Constant.CCP_DETAILS_CCP
                    + Constant.COMPANY_MASTER_TT
                    + " ST_M_ACTUAL_DISCOUNT SUPMAS,\n"
                    + "ST_M_ACTUAL_SALES STMSP,\n"
                    + " ST_M_SALES_PROJECTION_MASTER STMSPM,\n"
                    + PERIOD_PER_N
                    + Constant.WHERE_CCPCCP_DETAILS_SID_PDCCP_DETAILS
                    + Constant.AND_TTCOMPANY_MASTER_SID_CCP_MAS
                    + Constant.AND_PER_PERIOD_SID_SUP_PERIOD_SID
                    + "  and SUPMAS.CCP_DETAILS_SID = PD.CCP_DETAILS_SID"
                    + "  and STMSP.CCP_DETAILS_SID=PD.CCP_DETAILS_SID\n"
                    + "and STMSP.PERIOD_SID=Per.PERIOD_SID \n"
                    + " and STMSPM.CCP_DETAILS_SID=PD.CCP_DETAILS_SID\n"
                    + "  group by   "
                    + "per.YEAR , \n"
                    + Constant.SPACE_PER + frequency + "\n"
                    + "\n"
                    + "UNION\n"
                    + "\n"
                    + " select per.YEAR,per." + frequency + ", 0 AS COM_ID,0 AS CON_ID,0 AS BRAND_ID,0 As COMPANY_MASTER_SID,0 AS COMPANY_NO,"
                    + Constant.CUSTOMER_AS_LEVEL_NAME
                    + "\n"
                    + " sum(SUPMAS.PROJECTION_SALES) As Proj_Sale,\n"
                    + "Coalesce(Sum(SUPMAS.PROJECTION_SALES)/Nullif(Sum(STMSP.PROJECTION_SALES),0),0)*100       AS PROJ_Rate,\n"
                    + " 'Proj' As Flag,Coalesce(Sum(SUPMAS.PROJECTION_SALES)/Nullif(Sum(STMSP.PROJECTION_UNITS),0),0) AS RPU   \n"
                    + "\n"
                    + Constant.FROM_ST_CCP_HIERARCHY_PD
                    + Constant.CCP_DETAILS_CCP
                    + Constant.COMPANY_MASTER_TT
                    + " ST_M_DISCOUNT_PROJECTION SUPMAS,\n"
                    + "ST_M_SALES_PROJECTION STMSP,\n"
                    + "ST_M_SALES_PROJECTION_MASTER STMSPM,  \n"
                    + PERIOD_PER_N
                    + Constant.WHERE_CCPCCP_DETAILS_SID_PDCCP_DETAILS
                    + Constant.AND_TTCOMPANY_MASTER_SID_CCP_MAS
                    + " and SUPMAS.CCP_DETAILS_SID = PD.CCP_DETAILS_SID "
                    + Constant.AND_PER_PERIOD_SID_SUP_PERIOD_SID
                    + "and STMSP.CCP_DETAILS_SID=PD.CCP_DETAILS_SID\n"
                    + "and STMSP.PERIOD_SID=Per.PERIOD_SID \n"
                    + " and STMSPM.CCP_DETAILS_SID=PD.CCP_DETAILS_SID\n"
                    + "-- and STMSP.PERIOD_SID=Per.PERIOD_SID \n"
                    + "  group by  "
                    + "per.YEAR,  \n"
                    + Constant.SPACE_PER + frequency + "\n"
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
            query=QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames());
            list = (List) salesProjectionDAO.executeSelectQuery(query);
        } catch (PortalException | SystemException e) {
            LOGGER.error(e);
        }

        return list;
    }

    public List getSuppPivotLevelValue(ProjectionSelectionDTO projSelDTO) {
        List list = new ArrayList();
        String query = StringUtils.EMPTY;
        String frequency = projSelDTO.getFrequency();
        if (frequency.equals(Constant.QUARTERLY)) {
            frequency = Constant.QUARTER;
        }
        if (frequency.equals(ANNUALLY.getConstant())) {
            frequency = "YEAR";
        }
        if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
            frequency = Constant.SEMI_ANNUAL;

        }
        if (frequency.equals(MONTHLY.getConstant())) {
            frequency = Constant.MONTH_WITHOUT_SPACE;
        }

        try {
            query += " select per.YEAR,per." + frequency + ", 0 AS COM_ID,0 AS CON_ID,0 AS BRAND_ID,0 As COMPANY_MASTER_SID,0 AS COMPANY_NO,"
                    + Constant.CUSTOMER_AS_LEVEL_NAME
                    + "\n"
                    + " sum(ST_M_DIS_ACT.ACTUAL_SALES) As Actual_Sale,\n"
                    + "Coalesce(Sum(ST_M_DIS_ACT.ACTUAL_SALES)/Nullif(Sum(ST_M_ACT_SAL.ACTUAL_SALES),0),0)*100       AS ACTUAL_Rate,\n"
                    + " 'ACT' As Flag,Coalesce(Sum(ST_M_DIS_ACT.ACTUAL_SALES)/Nullif(Sum(ST_M_ACT_SAL.ACTUAL_UNITS),0),0) As RPU    \n"
                    + "\n"
                    + Constant.FROM_ST_CCP_HIERARCHY_PD
                    + Constant.CCP_DETAILS_CCP
                    + Constant.COMPANY_MASTER_TT
                    + " ST_M_SUPPLEMENTAL_DISC_ACTUALS ST_M_DIS_ACT,\n"
                    + " ST_M_ACTUAL_SALES               ST_M_ACT_SAL,\n"
                    + "\n"
                    + "ST_M_SALES_PROJECTION_MASTER   ST_M_SAL_PROJ_MAS,  \n"
                    + PERIOD_PER_N
                    + Constant.WHERE_CCPCCP_DETAILS_SID_PDCCP_DETAILS
                    + Constant.AND_TTCOMPANY_MASTER_SID_CCP_MAS
                    + "  and  ST_M_DIS_ACT.PERIOD_SID =Per.PERIOD_SID\n"
                    + "and ST_M_DIS_ACT.CCP_DETAILS_SID=PD.CCP_DETAILS_SID\n"
                    + "and ST_M_ACT_SAL.CCP_DETAILS_SID  = ST_M_DIS_ACT.CCP_DETAILS_SID\n"
                    + "and  ST_M_ACT_SAL.PERIOD_SID=Per.PERIOD_SID\n"
                    + "\n"
                    + AND_ST_M_SAL_PROJ_MASCCP_DETAILS_SIDPDCCP
                    + "\n"
                    + "\n"
                    + "  group by  "
                    + "per.YEAR,\n"
                    + Constant.SPACE_PER + frequency + "\n"
                    + "\n"
                    + Constant.UNION
                    + "\n"
                    + "select per.YEAR,per." + frequency + ", 0 AS COM_ID,0 AS CON_ID,0 AS BRAND_ID,0 As COMPANY_MASTER_SID,0 AS COMPANY_NO, "
                    + Constant.CUSTOMER_AS_LEVEL_NAME
                    + "\n"
                    + " sum(ST_M_DIS_Proj.PROJECTION_SALES) As Proj_Sale,\n"
                    + "Coalesce(Sum(ST_M_DIS_Proj.PROJECTION_SALES)/Nullif(Sum(ST_M_PROJ_SAL.PROJECTION_SALES),0),0)*100       AS PROJ_Rate,\n"
                    + " 'PROJ' As Flag,Coalesce(Sum(ST_M_DIS_Proj.PROJECTION_SALES)/Nullif(Sum(ST_M_PROJ_SAL.PROJECTION_UNITS),0),0) As RPU  \n"
                    + "\n"
                    + Constant.FROM_ST_CCP_HIERARCHY_PD
                    + Constant.CCP_DETAILS_CCP
                    + Constant.COMPANY_MASTER_TT
                    + " ST_M_SUPPLEMENTAL_DISC_PROJ ST_M_DIS_Proj,\n"
                    + " ST_M_SALES_PROJECTION       ST_M_PROJ_SAL,\n"
                    + "\n"
                    + "ST_M_SALES_PROJECTION_MASTER      ST_M_SAL_PROJ_MAS,  \n"
                    + PERIOD_PER_N
                    + Constant.WHERE_CCPCCP_DETAILS_SID_PDCCP_DETAILS
                    + Constant.AND_TTCOMPANY_MASTER_SID_CCP_MAS
                    + "  and  ST_M_DIS_Proj.PERIOD_SID =Per.PERIOD_SID\n"
                    + "and ST_M_DIS_Proj.CCP_DETAILS_SID=PD.CCP_DETAILS_SID\n"
                    + "and ST_M_PROJ_SAL.CCP_DETAILS_SID=ST_M_DIS_Proj.CCP_DETAILS_SID\n"
                    + "and ST_M_PROJ_SAL.PERIOD_SID=Per.PERIOD_SID\n"
                    + "\n"
                    + AND_ST_M_SAL_PROJ_MASCCP_DETAILS_SIDPDCCP
                    + " group by  "
                    + "per.YEAR,\n"
                    + Constant.SPACE_PER + frequency + "\n"
                    + "\n"
                    + "  ";
            if (!frequency.equals("YEAR")) {
                query += "order by  "
                        + "per.YEAR,per." + frequency + "\n";
            } else {
                query += "order by  "
                        + "per.YEAR";
            }
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            query=QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames());
            list = (List) salesProjectionDAO.executeSelectQuery(query);
        } catch (PortalException | SystemException e) {
            LOGGER.error(e);
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
                    tempWerQuery += AND_CCP_COMPANY_MASTER_SID + parentDto.getCurrentCustomer() + " \n"
                            + "and  CCP.CONTRACT_MASTER_SID = " + parentDto.getCurrentContract() + "\n";
                    tempFrom += ",CONTRACT_MASTER CONTR, COMPANY_MASTER CM";
                }
            }

            String fromQuery = " from ST_CCP_HIERARCHY PD,CCP_DETAILS CCP, ";

            String werQuery = " where CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n";


            query.append(tempSelect + fromQuery + tempFrom + werQuery + tempWerQuery );
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            String sql=QueryUtil.replaceTableNames(query.toString(), projSelDTO.getSessionDTO().getCurrentTableNames());
            List<Object> list = (List<Object>) salesProjectionDAO.executeSelectQuery(sql);

            if (list != null && !list.isEmpty()) {
                Object ob = list.get(0);
                count = Integer.valueOf(ob.toString());

            }

        } catch (PortalException | SystemException | NumberFormatException ex) {
            LOGGER.error(ex);
        }
        return count;
    }

    public static List getNonMandatedTotal(int projectionId,ProjectionSelectionDTO projSelDTO) {
        String frequency = StringUtils.EMPTY;
        try {
            if (Constant.QUARTERLY.equals(projSelDTO.getFrequency())) {
                frequency = "Quarter";
            } else if (SEMI_ANNUALLY.getConstant().equals(projSelDTO.getFrequency())) {
                frequency = "Semi_Annual";
            } else if (MONTHLY.getConstant().equals(projSelDTO.getFrequency())) {
                frequency = "Month";
            } else if (ANNUALLY.getConstant().equals(projSelDTO.getFrequency())) {
                frequency = "YEAR";
            }
            List list;
            String query = SQlUtil.getQuery("mmdprgetNonmandatedtotal");
            query = query.replaceAll(Constant.PROJ_ID, projectionId + StringUtils.EMPTY);
            query = query.replaceAll(Constant.FREQUENCY_VAR, frequency);
            if (!frequency.equals("YEAR")) {
                query += ",\n"
                        + "per." + frequency + StringUtils.EMPTY;
            }

            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            query = QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames());
            list = (List) salesProjectionDAO.executeSelectQuery(query);

            return list;
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex);
            return Collections.emptyList();
        }
    }

    public List getDiscountProjectionResults(List<Integer> discountprojectionId, String frequency, String view, List<Integer> startAndEndPeriods,ProjectionSelectionDTO selection) {
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
            if (startAndEndPeriods != null && startAndEndPeriods.size() != 0) {
                String hsYear = String.valueOf(startAndEndPeriods.get(0));
                String hsMonth = String.valueOf(startAndEndPeriods.get(1));
                String heYear = String.valueOf(startAndEndPeriods.get(NumericConstants.TWO));
                String heMonth = String.valueOf(startAndEndPeriods.get(NumericConstants.THREE));

                String fsYear = String.valueOf(startAndEndPeriods.get(NumericConstants.FOUR));
                String fsMonth = String.valueOf(startAndEndPeriods.get(NumericConstants.FIVE));
                String feYear = String.valueOf(startAndEndPeriods.get(NumericConstants.SIX));
                String feMonth = String.valueOf(startAndEndPeriods.get(NumericConstants.SEVEN));

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
                frequency = Constant.QUARTER;
            }
            if (frequency.equals(ANNUALLY.getConstant())) {
                frequency = "YEAR";
            }
            if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
                frequency = Constant.SEMI_ANNUAL;

            }
            if (frequency.equals(MONTHLY.getConstant())) {
                frequency = Constant.MONTH_WITHOUT_SPACE;
            }
            String projectionQuery;
            projectionQuery = "SELECT PR.YEAR,\n"
                    + "       PR." + frequency + "             AS BASE,\n"
                    + "       0.00                        AS ACTUAL_SALES,\n"
                    + "       0.00                        AS ACTUAL_DISCOUNT,\n"
                    + "       Max(NMDPM.PROJECTION_SALES)  AS PROJECTION_SALES,\n"
                    + "       Sum(NMDPM.PROJECTION_SALES) AS PROJECTION_DISCOUNT,\n"
                    + "       PR." + frequency + ",\n"
                    + "       PR.MONTH\n"
                    + "FROM   ST_CCP_HIERARCHY PD\n"
                    + "JOIN   ST_M_DISCOUNT_PROJECTION NMDPM ON NMDPM.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                    + "JOIN   PERIOD PR ON PR.PERIOD_SID = NMDPM.PERIOD_SID\n"
                    + "JOIN   ST_M_SALES_PROJECTION_MASTER NMSPM ON NMSPM.CCP_DETAILS_SID = NMDPM.CCP_DETAILS_SID\n"
                    + "JOIN   ST_M_SALES_PROJECTION NMSP ON NMSP.CCP_DETAILS_SID = NMDPM.CCP_DETAILS_SID\n"
                    + "                                 AND NMSP.PERIOD_SID = PR.PERIOD_SID\n"
                    + "WHERE  PD.CCP_DETAILS_SID IN (" + idString + ")\n"
                    + "  AND\n"
                    + " Cast(PR.YEAR AS VARCHAR(4))\n"
                    + "       + RIGHT('0'+Cast(PR.MONTH AS VARCHAR), 2) >=" + forecastStartPeriod + "\n"
                    + "   AND Cast(PR.YEAR AS VARCHAR(4))\n"
                    + "       + RIGHT('0'+Cast(PR.MONTH AS VARCHAR), 2) <=" + forecastEndPeriod + "\n"
                    + "GROUP  BY PR.YEAR,\n"
                    + PR + frequency + ",\n"
                    + "          PR.MONTH,\n"
                    + "          PD.CCP_DETAILS_SID \n"
                    + "\n"
                    + "UNION ALL \n"
                    + "SELECT    PR.YEAR,\n"
                    + PR + frequency + "                 AS BASE,\n"
                    + "          Max(NMADM.ACTUAL_SALES)                AS ACTUAL_SALES,\n"
                    + "          Sum(NMADM.ACTUAL_SALES)               AS ACTUAL_DISCOUNT,\n"
                    + "          Max(IsNull(NMSP.PROJECTION_SALES, 0)) AS PROJECTION_SALES,\n"
                    + "          Sum(IsNull(NMAS.ACTUAL_SALES, 0)) AS PROJECTION_DISCOUNT,\n"
                    + PR + frequency + ",\n"
                    + "          PR.MONTH\n"
                    + "FROM      ST_CCP_HIERARCHY PD\n"
                    + "JOIN      ST_M_ACTUAL_DISCOUNT NMADM ON NMADM.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                    + "JOIN      PERIOD PR ON PR.PERIOD_SID = NMADM.PERIOD_SID\n"
                    + "JOIN      ST_M_SALES_PROJECTION_MASTER NMSPM ON NMSPM.CCP_DETAILS_SID = NMADM.CCP_DETAILS_SID\n"
                    + "JOIN      ST_M_ACTUAL_SALES NMAS ON NMAS.PERIOD_SID = PR.PERIOD_SID\n"
                    + "                                AND NMAS.CCP_DETAILS_SID = NMADM.CCP_DETAILS_SID\n"
                    + "LEFT JOIN ST_M_SALES_PROJECTION NMSP ON NMSP.CCP_DETAILS_SID = NMSPM.CCP_DETAILS_SID\n"
                    + "                                    AND NMSP.PERIOD_SID = PR.PERIOD_SID\n"
                    + "\n"
                    + "WHERE    \n"
                    + " PD.CCP_DETAILS_SID IN( " + idString + ")\n"
                    + "      AND \n"
                    + "	  Cast(PR.YEAR AS VARCHAR(4))\n"
                    + "          + RIGHT ('0'+Cast(PR.MONTH AS VARCHAR), 2)  >=" + startPeriod + "\n"
                    + "      AND Cast(PR.YEAR AS VARCHAR(4))\n"
                    + "          + RIGHT('0'+Cast(PR.MONTH AS VARCHAR), 2) <=" + endPeriod + "\n"
                    + "      GROUP     BY PR.YEAR,\n"
                    + "             PR." + frequency + ",\n"
                    + "             PR.MONTH,\n"
                    + "             PD.CCP_DETAILS_SID\n";

            if (view.equalsIgnoreCase(Constant.PARENT)) {
                if (frequency.equals("YEAR") || frequency.equals(Constant.MONTH_WITHOUT_SPACE)) {
                    projectionQuery = projectionQuery + "ORDER BY PR.YEAR";
                } else {
                    projectionQuery = projectionQuery + "ORDER BY PR.YEAR,PR." + frequency + "";
                }
            } else {
                if (frequency.equals("YEAR") || frequency.equals(Constant.MONTH_WITHOUT_SPACE)) {
                    projectionQuery = projectionQuery + "ORDER BY RSM.RS_NAME,PR.YEAR";
                } else {
                    projectionQuery = projectionQuery + "ORDER BY RSM.RS_NAME,PR.YEAR,PR." + frequency + "";
                }
            }

            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            projectionQuery=QueryUtil.replaceTableNames(projectionQuery, selection.getSessionDTO().getCurrentTableNames());
            list = (List) salesProjectionDAO.executeSelectQuery(projectionQuery);

            return list;
        } catch (PortalException | SystemException e) {
            LOGGER.error(e);
            return Collections.emptyList();
        }
    }
    public static final String PR = "          PR.";

    public List getMandatedSupp2(List<Integer> discountprojectionId, String frequency, List<Integer> startAndEndPeriods,ProjectionSelectionDTO selection) {
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
            if (startAndEndPeriods != null && startAndEndPeriods.size() != 0) {
                String hsYear = String.valueOf(startAndEndPeriods.get(0));
                String hsMonth = String.valueOf(startAndEndPeriods.get(1));
                String heYear = String.valueOf(startAndEndPeriods.get(NumericConstants.TWO));
                String heMonth = String.valueOf(startAndEndPeriods.get(NumericConstants.THREE));

                String fsYear = String.valueOf(startAndEndPeriods.get(NumericConstants.FOUR));
                String fsMonth = String.valueOf(startAndEndPeriods.get(NumericConstants.FIVE));
                String feYear = String.valueOf(startAndEndPeriods.get(NumericConstants.SIX));
                String feMonth = String.valueOf(startAndEndPeriods.get(NumericConstants.SEVEN));

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
                frequency = Constant.QUARTER;
            }
            if (frequency.equals(ANNUALLY.getConstant())) {
                frequency = "YEAR";
            }
            if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
                frequency = Constant.SEMI_ANNUAL;

            }
            if (frequency.equals(MONTHLY.getConstant())) {
                frequency = Constant.MONTH_WITHOUT_SPACE;
            }
            String projectionQuery;

            projectionQuery = "SELECT PR.YEAR,\n"
                    + "    PR." + frequency + "            AS BASE,\n"
                    + "       0.00                        AS ACTUAL_SALES,\n"
                    + "       0.00                        AS ACTUAL_DISCOUNT,\n"
                    + "       Max(NMDPM.PROJECTION_SALES)  AS PROJECTION_SALES,\n"
                    + "       Sum(NMDPM.PROJECTION_SALES) AS PROJECTION_DISCOUNT,\n"
                    + "    PR." + frequency + ",\n"
                    + "       PR.MONTH\n"
                    + "FROM   ST_CCP_HIERARCHY PD\n"
                    + "JOIN   ST_M_SUPPLEMENTAL_DISC_PROJ NMDPM ON NMDPM.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                    + "JOIN   PERIOD PR ON PR.PERIOD_SID = NMDPM.PERIOD_SID\n"
                    + "JOIN   ST_M_SALES_PROJECTION_MASTER NMSPM ON NMSPM.CCP_DETAILS_SID = NMDPM.CCP_DETAILS_SID\n"
                    + "JOIN   ST_M_SALES_PROJECTION NMSP ON NMSP.CCP_DETAILS_SID = NMDPM.CCP_DETAILS_SID\n"
                    + "                                 AND NMSP.PERIOD_SID = PR.PERIOD_SID\n"
                    + "WHERE  PD.CCP_DETAILS_SID IN (" + idString + ")\n"
                    + "  AND\n"
                    + " Cast(PR.YEAR AS VARCHAR(4))\n"
                    + "       + RIGHT('0'+Cast(PR.MONTH AS VARCHAR), 2) >=" + forecastStartPeriod + "\n"
                    + "   AND Cast(PR.YEAR AS VARCHAR(4))\n"
                    + "       + RIGHT('0'+Cast(PR.MONTH AS VARCHAR), 2) <=" + forecastEndPeriod + "\n"
                    + "GROUP  BY PR.YEAR,\n"
                    + PR + frequency + ",\n"
                    + "          PR.MONTH,\n"
                    + "          PD.CCP_DETAILS_SID \n"
                    + "\n"
                    + "UNION ALL\n"
                    + "SELECT    PR.YEAR,\n"
                    + PR + frequency + "                            AS BASE,\n"
                    + "          Max(NMADM.ACTUAL_SALES)                AS ACTUAL_SALES,\n"
                    + "          Sum(NMADM.ACTUAL_SALES)               AS ACTUAL_DISCOUNT,\n"
                    + "          Max(IsNull(NMDP.PROJECTION_SALES, 0)) AS PROJECTION_SALES,\n"
                    + "          Sum(IsNull(NMDP.PROJECTION_SALES, 0)) AS PROJECTION_DISCOUNT"
                    + ",\n"
                    + PR + frequency + ",\n"
                    + "          PR.MONTH\n"
                    + "  FROM      ST_CCP_HIERARCHY PD\n"
                    + "JOIN      ST_M_SUPPLEMENTAL_DISC_ACTUALS NMADM ON NMADM.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                    + "JOIN      PERIOD PR ON PR.PERIOD_SID = NMADM.PERIOD_SID\n"
                    + "JOIN      ST_M_SALES_PROJECTION_MASTER NMSPM ON NMSPM.CCP_DETAILS_SID = NMADM.CCP_DETAILS_SID\n"
                    + "JOIN      ST_M_ACTUAL_SALES NMAS ON NMAS.PERIOD_SID = PR.PERIOD_SID\n"
                    + "                                AND NMAS.CCP_DETAILS_SID = NMADM.CCP_DETAILS_SID\n"
                    + "LEFT JOIN ST_M_SALES_PROJECTION NMSP ON NMSP.CCP_DETAILS_SID = NMSPM.CCP_DETAILS_SID\n"
                    + "                                    AND NMSP.PERIOD_SID = PR.PERIOD_SID\n"
                    + "LEFT JOIN ST_M_SUPPLEMENTAL_DISC_PROJ NMDP ON NMDP.CCP_DETAILS_SID = NMSPM.CCP_DETAILS_SID\n"
                    + "                                    AND NMDP.PERIOD_SID = PR.PERIOD_SID\n"
                    + "\n"
                    + "WHERE    \n"
                    + " PD.CCP_DETAILS_SID IN( " + idString + ")\n"
                    + "      AND \n"
                    + "	  Cast(PR.YEAR AS VARCHAR(4))\n"
                    + "          + RIGHT ('0'+Cast(PR.MONTH AS VARCHAR), 2)  >=" + startPeriod + "\n"
                    + "      AND Cast(PR.YEAR AS VARCHAR(4))\n"
                    + "          + RIGHT('0'+Cast(PR.MONTH AS VARCHAR), 2) <=" + endPeriod + "\n"
                    + "      GROUP     BY PR.YEAR,\n"
                    + "             PR." + frequency + ",\n"
                    + "             PR.MONTH,\n"
                    + "             PD.CCP_DETAILS_SID\n";

            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            projectionQuery=QueryUtil.replaceTableNames(projectionQuery, selection.getSessionDTO().getCurrentTableNames());
            list = (List) salesProjectionDAO.executeSelectQuery(projectionQuery);
            return list;

        } catch (PortalException | SystemException e) {
            LOGGER.error(e);
            return Collections.emptyList();
        }

    }

    public List getMandatedTotal(List<Integer> discountprojectionId, String frequency, ProjectionSelectionDTO projSelDTO) {
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
            if (frequency.equals(Constant.QUARTERLY)) {
                frequency = Constant.QUARTER;
            }
            if (frequency.equals(ANNUALLY.getConstant())) {
                frequency = "YEAR";
            }
            if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
                frequency = Constant.SEMI_ANNUAL;

            }
            if (frequency.equals(MONTHLY.getConstant())) {
                frequency = Constant.MONTH_WITHOUT_SPACE;
            }

            String str = "SELECT PR.YEAR, \n"
                    + "        PR." + frequency + " AS BASE,\n"
                    + "      \n"
                    + "sum(SUPMAS.ACTUAL_SALES) As Actual_Sale,\n"
                    + "Coalesce(Sum(SUPMAS.ACTUAL_SALES)/Nullif(Sum(m_mas.ACTUAL_SALES),0),0)*100       AS  Disc_Rate,\n"
                    + "  '0' As Flag,\n"
                    + "Coalesce(Sum(SUPMAS.ACTUAL_SALES)/Nullif(Sum(m_mas.ACTUAL_UNITS),0),0) As RPU\n"
                    + FROM_ST_CCP_HIERARCHY_PD
                    + "  CCP_DETAILS CCP,\n"
                    + "  COMPANY_MASTER TT,\n"
                    + "  ST_M_ACTUAL_DISCOUNT SUPMAS,\n"
                    + "  Period PR,\n"
                    + " ST_M_ACTUAL_SALES m_mas,\n"
                    + " ST_M_SALES_PROJECTION_MASTER m_mac\n"
                    + WHERE_CCPCCP_DETAILS_SID_PDCCP_DETAILS
                    + "  and TT.COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID\n"
                    + "   and PR.PERIOD_SID = SUPMAS.PERIOD_SID\n"
                    + " and PR.PERIOD_SID = m_mas.PERIOD_SID\n"
                    + "   and SUPMAS.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                    + " and pd.CCP_DETAILS_SID = m_mas.CCP_DETAILS_SID\n"
                    + " and m_mac.CCP_DETAILS_SID=pd.CCP_DETAILS_SID\n"
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
                    + "   FROM ST_CCP_HIERARCHY PD,\n"
                    + "  CCP_DETAILS CCP,\n"
                    + "  COMPANY_MASTER TT,\n"
                    + "  ST_M_DISCOUNT_PROJECTION SUPMAS,\n"
                    + "  Period PR,\n"
                    + " ST_M_SALES_PROJECTION m_mas,\n"
                    + " ST_M_SALES_PROJECTION_MASTER m_mac\n"
                    + WHERE_CCPCCP_DETAILS_SID_PDCCP_DETAILS
                    + "  and TT.COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID\n"
                    + "  and SUPMAS.CCP_DETAILS_SID = PD.CCP_DETAILS_SID   \n"
                    + "   and PR.PERIOD_SID = SUPMAS.PERIOD_SID\n"
                    + " and PR.PERIOD_SID=m_mas.PERIOD_SID\n"
                    + " and pd.CCP_DETAILS_SID = m_mas.CCP_DETAILS_SID\n"
                    + " and m_mac.CCP_DETAILS_SID=pd.CCP_DETAILS_SID\n"
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
            str= QueryUtil.replaceTableNames(str, projSelDTO.getSessionDTO().getCurrentTableNames());
            list = (List) salesProjectionDAO.executeSelectQuery(str);

            return list;
        } catch (PortalException | SystemException e) {
            LOGGER.error(e);
            return Collections.emptyList();
        }

    }
    public static final String WHERE_CCPCCP_DETAILS_SID_PDCCP_DETAILS = " where  CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n";

    public List getSuppTotal(List<Integer> discountprojectionId, String frequency, ProjectionSelectionDTO projSelDTO) {
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
            if (frequency.equals(Constant.QUARTERLY)) {
                frequency = Constant.QUARTER;
            }
            if (frequency.equals(ANNUALLY.getConstant())) {
                frequency = "YEAR";
            }
            if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
                frequency = Constant.SEMI_ANNUAL;

            }
            if (frequency.equals(MONTHLY.getConstant())) {
                frequency = Constant.MONTH_WITHOUT_SPACE;
            }

            String str = "SELECT PR.YEAR ,\n"
                    + " PR." + frequency + "  AS BASE,\n"
                    + "  SUM(STPROJ.PROJECTION_SALES)  AS PROJECTION_SALES,\n"
                    + "Coalesce(Sum(STPROJ.PROJECTION_SALES)/Nullif(Sum(STMSP.PROJECTION_SALES),0),0)*100       AS ProJ_Rate,\n"
                    + "      1  AS Flag,Coalesce(Sum(STPROJ.PROJECTION_SALES)/Nullif(Sum(STMSP.PROJECTION_UNITS),0),0) AS RPU  FROM ST_M_SUPPLEMENTAL_DISC_PROJ STPROJ,\n"
                    + "ST_CCP_HIERARCHY PD,\n"
                    + "ST_M_SALES_PROJECTION STMSP,\n"
                    + "ST_M_SALES_PROJECTION_MASTER STMSPM,\n"
                    + "\"PERIOD\" PR "
                    + "WHERE \n"
                    + "STPROJ.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                    + "AND PR.PERIOD_SID=STPROJ.PERIOD_SID\n"
                    + "AND PD.CCP_DETAILS_SID IN (" + idString + ")\n"
                    + "AND STMSP.CCP_DETAILS_SID=PD.CCP_DETAILS_SID\n"
                    + "AND STMSP.PERIOD_SID=PR.PERIOD_SID\n"
                    + "AND STMSPM.CCP_DETAILS_SID=PD.CCP_DETAILS_SID\n"
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
                    + "ST_CCP_HIERARCHY PD, \n"
                    + "\"PERIOD\" PR ,\n"
                    + "ST_M_ACTUAL_SALES STMAS,\n"
                    + "ST_M_SALES_PROJECTION_MASTER STMSPM "
                    + "WHERE \n"
                    + "STPROJ.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                    + "AND PR.PERIOD_SID=STPROJ.PERIOD_SID\n"
                    + "AND PD.CCP_DETAILS_SID IN (" + idString + ")\n"
                    + "AND STMAS.CCP_DETAILS_SID=PD.CCP_DETAILS_SID\n"
                    + "AND STMAS.PERIOD_SID=PR.PERIOD_SID\n"
                    + "AND STMSPM.CCP_DETAILS_SID=PD.CCP_DETAILS_SID\n"
                    + "  GROUP BY PR.YEAR,PR." + frequency + "\n";

            if (!frequency.equals("YEAR")) {
                str += " ORDER BY PR.YEAR,PR." + frequency + "";
            } else {
                str += " ORDER BY PR.YEAR";
            }

            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            str= QueryUtil.replaceTableNames(str, projSelDTO.getSessionDTO().getCurrentTableNames());
            list = (List) salesProjectionDAO.executeSelectQuery(str);
            return list;
        } catch (PortalException | SystemException e) {
            LOGGER.error(e);
            return Collections.emptyList();
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
        } catch (PortalException | SystemException e) {
            LOGGER.error(e);
            return Collections.emptyList();
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
        } catch (PortalException | SystemException e) {
            LOGGER.error(e);
            return Collections.emptyList();
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
        } catch (PortalException | SystemException e) {
            LOGGER.error(e);
            return Collections.emptyList();
        }
    }
}
