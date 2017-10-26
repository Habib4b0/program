/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.queryUtils;

import com.stpl.app.gtnforecasting.dto.PVSelectionDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.projectionvariance.dto.ComparisonLookupDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.utils.Constants;
import static com.stpl.app.utils.Constants.CommonConstants.DATE_FORMAT;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.dao.orm.Criterion;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.data.Container.Filter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 *
 * @author Mohamed.hameed
 */
public class PVQueryUtils {

    /**
     * The Constant LOGGER.
     */
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(PVQueryUtils.class);

    public String getCCPQuery(PVSelectionDTO projSelDTO, int projectionId) {
        String viewtable = CommonLogic.getViewTableName(projSelDTO.getHierarchyIndicator());
        String ccpQuery = " (SELECT LCCP.RELATIONSHIP_LEVEL_SID, LCCP.CCP_DETAILS_SID, LCCP.HIERARCHY_NO from "
                + "(SELECT CCPMAP.CCP_DETAILS_SID, HLD.HIERARCHY_NO, HLD.RELATIONSHIP_LEVEL_SID from "
                + "(SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID "
                + "FROM RELATIONSHIP_LEVEL_DEFINITION RLD "
                + "JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID "
                + "JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projectionId
                + "JOIN PROJECTION_MASTER PM  ON PD.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID"
                + " WHERE PM.PROJECTION_MASTER_SID=" + projectionId + ") CCPMAP,"
                + " (SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD1"
                + " JOIN " + viewtable + " PCH "
                + " ON PCH.RELATIONSHIP_LEVEL_SID=RLD1.RELATIONSHIP_LEVEL_SID"
                + " AND PCH.PROJECTION_MASTER_SID=" + projectionId
                + " WHERE RLD1.HIERARCHY_NO like '" + projSelDTO.getHierarchyNo() + "%' ) HLD"
                + " WHERE CCPMAP.HIERARCHY_NO like HLD.HIERARCHY_NO+'%' ) LCCP"
                + " WHERE LCCP.HIERARCHY_NO in"
                + " (SELECT RLD2.HIERARCHY_NO"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD2"
                + " JOIN " + viewtable + " PCH2"
                + " ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID"
                + " AND PCH2.PROJECTION_MASTER_SID=" + projectionId
                + " WHERE RLD2.LEVEL_NO=" + projSelDTO.getLevelNo() + ")) CCP ";

        return ccpQuery;
    }

    public String getCCPGeneralQuery(PVSelectionDTO projSelDTO, int projectionId) {
        String viewtable = CommonLogic.getViewTableName(projSelDTO.getHierarchyIndicator());
        String ccpQuery = " SELECT LCCP.RELATIONSHIP_LEVEL_SID, LCCP.CCP_DETAILS_SID, LCCP.HIERARCHY_NO from "
                + "(SELECT CCPMAP.CCP_DETAILS_SID, HLD.HIERARCHY_NO, HLD.RELATIONSHIP_LEVEL_SID from "
                + "(SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID "
                + "FROM RELATIONSHIP_LEVEL_DEFINITION RLD "
                + "JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID "
                + "JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projectionId
                + " JOIN PROJECTION_MASTER PM  ON PD.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID"
                + " WHERE PM.PROJECTION_MASTER_SID=" + projectionId + ") CCPMAP,"
                + " (SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD1"
                + " JOIN " + viewtable + " PCH "
                + " ON PCH.RELATIONSHIP_LEVEL_SID=RLD1.RELATIONSHIP_LEVEL_SID"
                + " AND PCH.PROJECTION_MASTER_SID=" + projectionId
                + " WHERE RLD1.HIERARCHY_NO like '" + projSelDTO.getHierarchyNo() + "%' ) HLD"
                + " WHERE CCPMAP.HIERARCHY_NO like HLD.HIERARCHY_NO+'%' ) LCCP"
                + " WHERE LCCP.HIERARCHY_NO in"
                + " (SELECT RLD2.HIERARCHY_NO"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD2"
                + " JOIN " + viewtable + " PCH2"
                + " ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID"
                + " AND PCH2.PROJECTION_MASTER_SID=" + projectionId
                + " WHERE RLD2.LEVEL_NO=" + projSelDTO.getLevelNo() + ") ";
        return ccpQuery;
    }

    public String getUserSessionQueryCondition(int userId, int sessionId, String table) {
        String user = " and " + table + ".USER_ID=" + userId + " and " + table + ".SESSION_ID=" + sessionId + " ";
        return user;
    }

    public String getProjectionResultsPivotQuery(PVSelectionDTO projSelDTO, boolean isPriorDiscount) {
        projSelDTO.setIsTotal(true);
        String selectClause = " select ";
        selectClause += "Isnull(TODIS.YEARS ,SALEPPA.YEARS ) as YEARS, Isnull(TODIS.PERIODS ,SALEPPA.PERIODS ) AS PERIODS,";
        selectClause += getPivotSelectClause();
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, isPriorDiscount);
        String salesQuery = getSalesQuery(getProjectionResultsSalesQueryForPeriod(projSelDTO));
        String ppaQuery = getPPAQuery(getPPAProjectionResultsQueryForPeriod(projSelDTO, Boolean.FALSE), Boolean.FALSE,"Program Category".equalsIgnoreCase(projSelDTO.getDiscountLevel()));
        String productQuery = getProperFileData(projSelDTO);
        String customQuery = selectClause + " from " + getPivotSelectQuery(totalDiscountQuery, salesQuery, ppaQuery , productQuery);
        return customQuery;
    }
    
    public String getPVMainDiscountDolQuery(PVSelectionDTO projSelDTO) {
        projSelDTO.setIsTotal(false);
        String selectClause = " select C.YEARS as YEARS, C.PERIODS AS PERIODS,C.DISCOUNTS AS DISCOUNTS,";
        String customQuery = StringUtils.EMPTY;
        String orderBy = " DISCOUNTS, YEARS,PERIODS ";
        String projectedSales = "PROJECTION_SALES";
        if (projSelDTO.getGroup().contains(CommonUtils.COL_VALUE)) {
            selectClause += "C." + projectedSales + " AS C_PROJECTION_SALES";
        } else if (projSelDTO.getGroup().contains(CommonUtils.COL_VARIANCE)) {
            selectClause += "0  AS C_PROJECTION_SALES";
        } else {
            selectClause += "0  AS C_PROJECTION_SALES";
        }
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            if (projSelDTO.getGroup().contains(CommonUtils.COL_VALUE)) {
                selectClause += ", P" + i + "." + projectedSales + " AS P" + i + "_PROJECTION_SALES ";
            } else if (projSelDTO.getGroup().contains(CommonUtils.COL_VARIANCE)) {
                selectClause += ", (IsNull(C." + projectedSales + ", 0)- IsNull(P" + i + "." + projectedSales + ", 0)) AS P" + i + "_PROJECTION_SALES ";
            } else {
                selectClause += ", CASE WHEN P" + i + "." + projectedSales + " = 0 THEN 0\n"
                        + " ELSE ( IsNull(C." + projectedSales + ", 0) - IsNull(P" + i + "." + projectedSales + ", 0) / P" + i + "." + projectedSales + " )  \n"
                        + " END  AS P" + i + "_PROJECTION_SALES ";
            }

        }
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
        projSelDTO.setIsPrior(false);
        customQuery += selectClause + " from  \n(" + getProjectionResultsDiscountsQuery(projSelDTO, Boolean.FALSE) + ") C\n ";
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
            projSelDTO.setIsPrior(true);
            projSelDTO.setProjectionId(projSelDTO.getProjIdList().get(i));
            customQuery += " LEFT JOIN (\n" + getProjectionResultsDiscountsQuery(projSelDTO, Boolean.TRUE) + "\n) " + Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY + i + " \n on C.DISCOUNTS=P" + i + ".DISCOUNTS \n "
                    + " AND C.YEARS=P" + i + ".YEARS \n "
                    + " and C.PERIODS=P" + i + ".PERIODS  ";
        }
        customQuery += " order by " + orderBy;
        projSelDTO.setIsPrior(false);
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        return customQuery;
    }

    public String getPVMainDiscountPerQuery(PVSelectionDTO projSelDTO) {
        projSelDTO.setIsTotal(false);
        String selectClause = " select C.YEARS as YEARS, C.PERIODS AS PERIODS,C.DISCOUNTS AS DISCOUNTS,";
        String customQuery = StringUtils.EMPTY;
        String orderBy = " DISCOUNTS, YEARS,PERIODS ";
        String projectedSales = StringUtils.EMPTY;
        if (projSelDTO.isRPU()) {
            projectedSales = "RPU ";
        } else {
            projectedSales = "PROJECTION_RATE ";
        }
        if (projSelDTO.getGroup().contains(CommonUtils.COL_VALUE)) {
            selectClause += "C." + projectedSales + " AS C_PROJECTION_RATE";
        } else if (projSelDTO.getGroup().contains(CommonUtils.COL_VARIANCE)) {
            selectClause += "0  AS PROJECTION_RATE";
        } else {
            selectClause += "0  AS PROJECTION_RATE";
        }
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            if (projSelDTO.getGroup().contains(CommonUtils.COL_VALUE)) {
                selectClause += ", P" + i + "." + projectedSales + " AS P" + i + "_" + projectedSales + " ";
            } else if (projSelDTO.getGroup().contains(CommonUtils.COL_VARIANCE)) {
                selectClause += ", (IsNull(C." + projectedSales + ", 0)- IsNull(P" + i + "." + projectedSales + ", 0)) AS P" + i + "_" + projectedSales + " ";
            } else {
                selectClause += ", CASE WHEN P" + i + "." + projectedSales + " = 0 THEN 0\n"
                        + " ELSE (IsNull(C." + projectedSales + ", 0) - IsNull(P" + i + "." + projectedSales + ", 0) / P" + i + "." + projectedSales + ") \n"
                        + " END  AS P" + i + "_" + projectedSales + " ";
            }
        }
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
        projSelDTO.setIsPrior(false);
        customQuery += selectClause + " from  \n(" + getProjectionResultsDiscountsPerQuery(projSelDTO) + ") C\n ";
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
        projSelDTO.setIsPrior(true);
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            projSelDTO.setProjectionId(projSelDTO.getProjIdList().get(i));
            customQuery += " LEFT JOIN (\n" + getProjectionResultsDiscountsPerQuery(projSelDTO) + "\n) " + Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY + i + " \n on C.DISCOUNTS=P" + i + ".DISCOUNTS \n "
                    + " AND C.YEARS=P" + i + ".YEARS \n "
                    + " and C.PERIODS=P" + i + ".PERIODS  ";
        }
        customQuery += " order by " + orderBy;
        projSelDTO.setIsPrior(false);
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        return customQuery;
    }

    public String getPeriodDiscountExpand(PVSelectionDTO projSelDTO) {
        boolean isActuals = "Actuals".equals(projSelDTO.getComparisonBasis());
        projSelDTO.setIsTotal(false);
        String selectClause = " select C.YEARS as YEARS, C.PERIODS AS PERIODS,C.DISCOUNTS AS DISCOUNTS, ";
        String customQuery = StringUtils.EMPTY;
        String orderBy = " DISCOUNTS, YEARS,PERIODS ";
        String projectedSales = StringUtils.EMPTY;
        String actualSales = StringUtils.EMPTY;
        projSelDTO.setSales(Constant.SALES_WHOLE_CAPS);
        
        if (projSelDTO.getDiscountGroupName().contains(CommonUtils.VAR_DIS_PER_EXFAC)) {
            projSelDTO.setSales(Constant.RATE);
            projectedSales = "discount_of_exfactory_forecast";
            actualSales = "discount_of_exfactory_actuals";
            if (projSelDTO.getDiscountGroupName().contains(CommonUtils.COL_VALUE)) {
                selectClause += "C." + projectedSales + " AS C_discount_of_exfactory_forecast , CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS,C.PERIODS,01) THEN  C. " + actualSales + " ELSE NULL END AS c_discount_of_exfactory_actuals " ;
            } else if (projSelDTO.getDiscountGroupName().contains(CommonUtils.COL_VARIANCE)) {
                if (!isActuals) {
                    selectClause += "0  AS C_discount_of_exfactory_forecast, 0 AS c_discount_of_exfactory_actuals  " ;
                } else {
                    selectClause += " (IsNull(C." + actualSales + ", 0)- IsNull(C." + projectedSales + ", 0)) AS  C_discount_of_exfactory_forecast ";
                    selectClause += ", 0 AS c_discount_of_exfactory_actuals ";
//                    selectClause += ("Program Category".equalsIgnoreCase(projSelDTO.getDiscountLevel()) ? StringUtils.EMPTY : "    ,C.RS_CONTRACT_SID  ");
                }
            } else {
                 if (!isActuals) {
                    selectClause += "0  AS C_discount_of_exfactory_forecast, 0 AS c_discount_of_exfactory_actuals  " ;
                } else {
                    selectClause += " (CASE WHEN C." + projectedSales + " = 0 THEN 0\n"; 
                    selectClause += " ELSE (IsNull(C." + actualSales + ", 0) - IsNull(C." + projectedSales + ", 0)) / C." + projectedSales + " END ) * 100 AS C_discount_of_exfactory_forecast  \n";
                    selectClause += ", 0 AS c_discount_of_exfactory_actuals ";
//                    selectClause += ("Program Category".equalsIgnoreCase(projSelDTO.getDiscountLevel())?StringUtils.EMPTY:"    ,C.RS_CONTRACT_SID  ");
                }
            }
            }else if (projSelDTO.getDiscountGroupName().contains(CommonUtils.VAR_DIS_AMOUNT)) {
            projSelDTO.setSales(Constant.SALES_WHOLE_CAPS);
            projectedSales = "PROJECTION_SALES";
            actualSales = "ACTUAL_SALES";
            if (projSelDTO.getDiscountGroupName().contains(CommonUtils.COL_VALUE)) {
                selectClause += "C." + projectedSales + " AS C_PROJECTION_AMNT , CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS, isnull(nullif(C.PERIODS,'0'),1), 01) THEN C. " + actualSales + " ELSE NULL END AS C_ACTUAL_SALES " ;
            } else if (projSelDTO.getDiscountGroupName().contains(CommonUtils.COL_VARIANCE)) {
                if (!isActuals) {
                    selectClause += "0  AS PROJECTION_AMNT, 0 AS C_ACTUAL_SALES  " ;
                } else {
                    selectClause += " (IsNull(C." + actualSales + ", 0)- IsNull(C." + projectedSales + ", 0)) AS  C_PROJECTION_AMNT ";
                    selectClause += ", 0 AS C_ACTUAL_SALES ";
//                    selectClause += ("Program Category".equalsIgnoreCase(projSelDTO.getDiscountLevel()) ? StringUtils.EMPTY : "    ,C.RS_CONTRACT_SID  ");
                }
            } else {
                 if (!isActuals) {
                    selectClause += "0  AS PROJECTION_AMNT, 0 AS C_ACTUAL_SALES  " ;
                } else {
                    selectClause += " (CASE WHEN C." + projectedSales + " = 0 THEN 0\n"; 
                    selectClause += " ELSE (IsNull(C." + actualSales + ", 0) - IsNull(C." + projectedSales + ", 0)) / C." + projectedSales + " END ) * 100 AS PROJECTION_AMNT  \n";
                    selectClause += ", 0 AS C_ACTUAL_SALES ";
//                    selectClause += ("Program Category".equalsIgnoreCase(projSelDTO.getDiscountLevel())?StringUtils.EMPTY:"    ,C.RS_CONTRACT_SID  ");
                }
            }
        } else if (projSelDTO.getDiscountGroupName().contains(CommonUtils.VAR_DIS_RATE)) {
            projSelDTO.setSales(Constant.RATE);
            projectedSales = "PROJECTION_RATE";
            actualSales = "ACTUAL_RATE";
            if (projSelDTO.getDiscountGroupName().contains(CommonUtils.COL_VALUE)) {
                selectClause += "C." + projectedSales + " AS C_PROJECTION_RATE  , CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS, isnull(nullif(C.PERIODS,'0'),1), 01) THEN C."+ actualSales +" ELSE NULL END AS C_ACTUAL_RATE ";
            } else if (projSelDTO.getDiscountGroupName().contains(CommonUtils.COL_VARIANCE)) {
                 if (!isActuals) {
                    selectClause += "0  AS PROJECTION_RATE, 0 AS C_ACTUAL_RATE  " ;
                } else {
                    selectClause += " (IsNull(C." + actualSales + ", 0)- IsNull(C." + projectedSales + ", 0)) AS  PROJECTION_RATE ";
                    selectClause += ", 0 AS C_ACTUAL_RATE ";
//                    selectClause += ("Program Category".equalsIgnoreCase(projSelDTO.getDiscountLevel()) ? StringUtils.EMPTY : "    ,C.RS_CONTRACT_SID  ");
                }
            } else {
                if (!isActuals) {
                    selectClause += "0  AS PROJECTION_RATE, 0 AS C_ACTUAL_RATE  " ;
                } else {
                    selectClause += " (CASE WHEN C." + projectedSales + " = 0 THEN 0\n"; 
                    selectClause += " ELSE (IsNull(C." + actualSales + ", 0) - IsNull(C." + projectedSales + ", 0)) / C." + projectedSales + " END ) * 100 AS PROJECTION_RATE  \n";
                    selectClause += ", 0 AS C_ACTUAL_RATE ";
//                    selectClause += ("Program Category".equalsIgnoreCase(projSelDTO.getDiscountLevel())?StringUtils.EMPTY:"    ,C.RS_CONTRACT_SID  ");
                }
            }
        } else {
            projSelDTO.setSales(Constant.SALES_WHOLE_CAPS);
            projectedSales = "PROJECTED_RPU";
            actualSales = "ACTUAL_RPU";
            if (projSelDTO.getDiscountGroupName().contains(CommonUtils.COL_VALUE)) {
                selectClause += "C." + projectedSales + " AS C_PROJECTED_RPU   , CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS, isnull(nullif(C.PERIODS,'0'),1), 01) THEN C."+ actualSales +" ELSE NULL END AS C_ACTUAL_RPU ";
            } else if (projSelDTO.getDiscountGroupName().contains(CommonUtils.COL_VARIANCE)) {
                 if (!isActuals) {
                    selectClause += "0  AS C_PROJECTED_RPU , 0 AS C_ACTUAL_RPU  " ;
                } else {
                    selectClause += " (IsNull(C." + actualSales + ", 0)- IsNull(C." + projectedSales + ", 0)) AS  C_PROJECTED_RPU ";
                    selectClause += ", 0 AS C_ACTUAL_RPU ";
//                    selectClause += ("Program Category".equalsIgnoreCase(projSelDTO.getDiscountLevel()) ? StringUtils.EMPTY : "    ,C.RS_CONTRACT_SID  ");
                }
            } else {
                 if (!isActuals) {
                    selectClause += "0  AS C_PROJECTED_RPU, 0 AS C_ACTUAL_RPU  " ;
                } else {
                    selectClause += " (CASE WHEN C." + projectedSales + " = 0 THEN 0\n"; 
                    selectClause += " ELSE (IsNull(C." + actualSales + ", 0) - IsNull(C." + projectedSales + ", 0)) / C." + projectedSales + " END ) * 100 AS C_PROJECTED_RPU  \n";
                    selectClause += ", 0 AS C_ACTUAL_RPU ";
//                    selectClause += ("Program Category".equalsIgnoreCase(projSelDTO.getDiscountLevel())?StringUtils.EMPTY:"    ,C.RS_CONTRACT_SID  ");
                }
            }
        }
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            if (projSelDTO.getGroup().contains(CommonUtils.COL_VALUE)) {
                selectClause += ", P" + i + "." + projectedSales + " AS P" + i + "_" + projectedSales + " ";
                selectClause += ", P" + i + "." + actualSales + " AS P" + i + "_" + actualSales + " ";
            } else if (projSelDTO.getGroup().contains(CommonUtils.COL_VARIANCE)) {
                if(!isActuals){
                selectClause += ", convert(numeric(22,2),(IsNull(C." + projectedSales + ", 0) - IsNull(P" + i + "." + projectedSales + ", 0))) " ;
                selectClause += ", 0 AS P" + i + "_" + actualSales + " ";
                }else{
                selectClause += ", convert(numeric(22,2),(IsNull(C." + actualSales + ", 0) - IsNull(P" + i + "." + projectedSales + ", 0))) ";
                selectClause += ", 0 AS P" + i + "_" + actualSales + " ";
                }
            } else {
                if(!isActuals){
                selectClause += ", (CASE WHEN P" + i + "." + projectedSales + " = 0 THEN 0\n"
                        + " ELSE convert(numeric(22,2),(IsNull(C." + projectedSales + ", 0) - IsNull(P" + i + "." + projectedSales + ", 0))) / convert(numeric(22,2),P" + i + "." + projectedSales + ") \n"
                        + " END ) * 100  AS P" + i + "_" + projectedSales + " ";
                selectClause += ", 0 AS P" + i + "_" + actualSales + " ";
                }else{
                selectClause += ", (CASE WHEN P" + i + "." + projectedSales + " = 0 THEN 0\n"
                        + " ELSE convert(numeric(22,2),(IsNull(C." + actualSales + ", 0) - IsNull(P" + i + "." + projectedSales + ", 0))) / convert(numeric(22,2),P" + i + "." + projectedSales + ") \n"
                        + " END) * 100  AS P" + i + "_" + projectedSales + " ";
                selectClause += ", 0 AS P" + i + "_" + actualSales + " ";
                }
            }
        }
        selectClause += ("Program Category".equalsIgnoreCase(projSelDTO.getDiscountLevel())?StringUtils.EMPTY:"    ,C.RS_CONTRACT_SID  ");
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
        projSelDTO.setIsPrior(false);
        customQuery += selectClause + " from  \n(" + getProjectionResultsDiscountsPerQuery(projSelDTO) + ") C\n ";
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
        projSelDTO.setIsPrior(true);
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            projSelDTO.setProjectionId(projSelDTO.getProjIdList().get(i));
            customQuery += " LEFT JOIN (\n" + getProjectionResultsDiscountsPerQuery(projSelDTO) + "\n) " + Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY + i + " \n on C.DISCOUNTS=P" + i + ".DISCOUNTS \n "
                    + " AND C.YEARS=P" + i + ".YEARS \n "
                    + " and C.PERIODS=P" + i + ".PERIODS  ";
        }
        customQuery += " order by " + orderBy;
        projSelDTO.setIsPrior(false);
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        return customQuery;
    }

    public String getProjectionResultsMainPivotQuery(PVSelectionDTO projSelDTO) {
        projSelDTO.setIsTotal(true);
        String selectClause = " select ";
        String customQuery = StringUtils.EMPTY;
        String orderBy = " YEARS,PERIODS ";
        selectClause += "C.YEARS as YEARS, C.PERIODS AS PERIODS,";

        customQuery = selectClause + getPivotMainSelectClause(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            customQuery += ", " + getPivotMainSelectClause(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY + i);
        }
        customQuery += " , C.CONTRACT_ACTUAL_SALES - C.CONTRACT_PROJECTION_SALES AS C_CON_WAC_SAL_VAR, "
	  + "  C.CONTRACT_ACTUAL_SALES - C.CONTRACT_PROJECTION_SALES AS C_CON_WAC_SAL_VAR, "
	  + " (C.CONTRACT_ACTUAL_SALES - C.CONTRACT_PROJECTION_SALES)/NULLIF(C.CONTRACT_PROJECTION_SALES,0) AS C_CON_WAC_SAL_PER_CNG, "
	  + " (C.CONTRACT_ACTUAL_SALES - C.CONTRACT_PROJECTION_SALES)/NULLIF(C.CONTRACT_PROJECTION_SALES,0) AS C_CON_WAC_SAL_PER_CNG, "
	  + "  C.CONTRACT_ACTUAL_UNITS - C.CONTRACT_PROJECTION_UNITS AS C_CON_WAC_UNT_VAR, "
	  + "  C.CONTRACT_ACTUAL_UNITS - C.CONTRACT_PROJECTION_UNITS AS C_CON_WAC_UNT_VAR, "
	  + " (C.CONTRACT_ACTUAL_UNITS - C.CONTRACT_PROJECTION_UNITS)/NULLIF(C.CONTRACT_PROJECTION_UNITS,0) AS C_CON_WAC_UNT_PER_CNG, "
	  + " (C.CONTRACT_ACTUAL_UNITS - C.CONTRACT_PROJECTION_UNITS)/NULLIF(C.CONTRACT_PROJECTION_UNITS,0) AS C_CON_WAC_UNT_PER_CNG ";
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
        projSelDTO.setIsPrior(false);
        customQuery += " from  \n(" + getProjectionResultsPivotQuery(projSelDTO, Boolean.FALSE) + ") C\n ";
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
        projSelDTO.setIsPrior(true);
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            projSelDTO.setProjectionId(projSelDTO.getProjIdList().get(i));
            customQuery += " LEFT JOIN (\n" + getProjectionResultsPivotQuery(projSelDTO, Boolean.TRUE) + "\n) " + Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY + i + " \n" + getPivotMainWhereCond(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY + i);
        }
        customQuery += " order by " + orderBy;
        projSelDTO.setIsPrior(false);
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        return customQuery;
    }
    
    
    
    public String getProjectionResultsMainPeriodQuery(PVSelectionDTO projSelDTO) {
        projSelDTO.setIsTotal(true);
        String selectClause = " select ";
        String customQuery = StringUtils.EMPTY;
        String orderBy = " YEARS,PERIODS ";
        selectClause += "C.YEARS as YEARS, C.PERIODS AS PERIODS,";

        customQuery = selectClause + getPivotMainSelectClausePeriod(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            customQuery += ", " + getPivotMainSelectClausePeriod(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY + i);
        }
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
        projSelDTO.setIsPrior(false);
        customQuery += " from  \n(" + getProjectionResultsPivotQuery(projSelDTO, Boolean.FALSE) + ") C\n ";
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
        projSelDTO.setIsPrior(true);
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            projSelDTO.setProjectionId(projSelDTO.getProjIdList().get(i));
            customQuery += " LEFT JOIN (\n" + getProjectionResultsPivotQuery(projSelDTO, Boolean.TRUE) + "\n) " + Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY + i + " \n" + getPivotMainWhereCond(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY + i);
        }
        customQuery += " order by " + orderBy;
        projSelDTO.setIsPrior(false);
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        return customQuery;
    }

    public String getPivotSelectQuery(String query1, String query2, String query3 ,String query4) {
        String finalWhereCond = " on TODIS.YEARS=SALEPPA.YEARS AND TODIS.PERIODS=SALEPPA.PERIODS";
        String Condition = "full join (select i.QUARTER PERIODS,i.YEAR YEARS,sum(EXFACTORY_ACTUAL_SALES) as EXFACTORY_ACTUAL_SALES,sum(EXFACTORY_FORECAST_SALES) as EXFACTORY_FORECAST_SALES"
                + "from @PERIOD I join (select distinct pf.item_master_Sid,pf.period_Sid,case when @CURRENT_DTAE >period_date then EXFACTORY_ACTUAL_SALES else 0 end EXFACTORY_ACTUAL_SALES,EXFACTORY_FORECAST_SALES from #SELECTED_HIERARCHY_NO sh join #TEMP_CCP tc on tc.CCP_DETAILS_SID=sh.CCP_DETAILS_SID"
                + "join ST_PRODUCT_FILE pf on pf.item_master_Sid=tc.ITEM_MASTER_SID"
                + " join period p on p.PERIOD_SID=pf.period_sid) pf"
                + " on i.PERIOD_SID=pf.period_Sid"
                + "group by i.QUARTER,i.YEAR) SALEPPA1 ON TODIS.YEARS = SALEPPA1.YEARS"
                + "AND TODIS.PERIODS = SALEPPA1.PERIODS";

        String finalWhereCond1 = " ON coalesce(TODIS.YEARS,SALEPPA.YEARS)=PPA.YEARS AND coalesce(TODIS.PERIODS,SALEPPA.PERIODS)=PPA.PERIODS";
        String selectClause = " (\n" + query1 + "\n) TODIS FULL JOIN (\n" + query2 + "\n) SALEPPA \n " + finalWhereCond + " FULL JOIN (\n" + query4 + "\n) SALEPPA1 ON SALEPPA.YEARS = SALEPPA1.YEARS\n" +
"              AND SALEPPA.PERIODS = SALEPPA1.PERIODS" + " FULL JOIN (\n" + query3 + "\n) PPA" + finalWhereCond1;
        return selectClause;
    }

    public String getPivotMainWhereCond(String table) {
        return " on C.YEARS=" + table + ".YEARS AND C.PERIODS=" + table + ".PERIODS \n";
    }

    public String getPivotSelectClause() {
        String selectClause = " SALEPPA.SALES_PROJECTION_SALES as CONTRACT_PROJECTION_SALES,"
                + "SALEPPA.PROJECTION_UNITS as CONTRACT_PROJECTION_UNITS,"
                
                + " SALEPPA.ACTUAL_SALES AS CONTRACT_ACTUAL_SALES, "
                + "  SALEPPA.ACTUAL_UNITS AS CONTRACT_ACTUAL_UNITS, "
                + " CASE WHEN SALEPPA.sales_projection_sales = 0 THEN 0 ELSE isnull((Isnull(TODIS.projection_sales,0) + ISNULL(PPA.SALES_PPA, 0)) / NULLIF(SALEPPA.sales_projection_sales, 0), 0)* 100 END AS TOTAL_PROJECTION_RATE, "
                
//                + " CASE  WHEN @CURRENT_DTAE >  DATEFROMPARTS(TODIS.YEARS,case when ISNULL(TODIS.PERIODS, SALEPPA.PERIODS)=0 then 01 else  ISNULL(TODIS.PERIODS, SALEPPA.PERIODS) end, 01)  THEN "
                + " CASE WHEN SALEPPA.ACTUAL_SALES = 0 THEN 0 ELSE isnull((Isnull(TODIS.D_ACTUAL_SALES,0) + ISNULL(PPA.PPA_ACTUAL_AMOUNT, 0)) / NULLIF(SALEPPA.ACTUAL_SALES, 0), 0)* 100  END AS TOTAL_ACTUAL_RATE, "
                + " (Isnull(TODIS.projection_sales, 0) + ISNULL(PPA.SALES_PPA, 0)) AS TOTAL_PROJECTION_DOLAR,"
//                + " CASE  WHEN @CURRENT_DTAE >  DATEFROMPARTS(TODIS.YEARS,case when ISNULL(TODIS.PERIODS, SALEPPA.PERIODS)=0 then 01 else  ISNULL(TODIS.PERIODS, SALEPPA.PERIODS) end, 01)  THEN "
                
                + " (Isnull(TODIS.D_ACTUAL_SALES, 0) + ISNULL(PPA.PPA_ACTUAL_AMOUNT, 0))  AS TOTAL_ACTUAL_DOLAR,"
                + "(Isnull(SALEPPA.SALES_PROJECTION_SALES, 0) - (Isnull(TODIS.PROJECTION_SALES, 0) + ISNULL(PPA.SALES_PPA, 0)))  AS NET_PROJECTION_SALES, "
//                + " CASE  WHEN @CURRENT_DTAE >  DATEFROMPARTS(TODIS.YEARS,case when ISNULL(TODIS.PERIODS, SALEPPA.PERIODS)=0 then 01 else  ISNULL(TODIS.PERIODS, SALEPPA.PERIODS) end, 01)  THEN "
                
                + "(Isnull(SALEPPA.ACTUAL_SALES, 0) - (Isnull(TODIS.D_ACTUAL_SALES, 0) + ISNULL(PPA.PPA_ACTUAL_AMOUNT, 0)))  AS NET_ACTUAL_SALES, "
                + " CASE WHEN SALEPPA.projection_units = 0 THEN 0  ELSE IsNull((isnull(TODIS.projection_sales,0)+ ISNULL(PPA.SALES_PPA, 0)) / NULLIF(SALEPPA.PROJECTION_UNITS, 0), 0) END AS PROJECTED_RPU,\n"
//                + " CASE  WHEN @CURRENT_DTAE >  DATEFROMPARTS(TODIS.YEARS,case when ISNULL(TODIS.PERIODS, SALEPPA.PERIODS)=0 then 01 else  ISNULL(TODIS.PERIODS, SALEPPA.PERIODS) end, 01)  THEN "
                
                + " CASE WHEN SALEPPA.ACTUAL_UNITS = 0 THEN 0  ELSE IsNull((isnull(TODIS.D_ACTUAL_SALES,0)+ ISNULL(PPA.PPA_ACTUAL_AMOUNT, 0)) / NULLIF(SALEPPA.ACTUAL_UNITS, 0), 0)  END AS ACTUAL_RPU,\n"
                + " COGS_PROJECTED = ISNULL(SALEPPA.COGS_PROJECTED, 0),\n"
                
                + " COGS_ACTUALS  =  ISNULL(SALEPPA.COGS_ACTUALS, 0) ,\n"
                + " NET_PROFIT_PROJECTED = ((Isnull(SALEPPA.SALES_PROJECTION_SALES, 0) - (Isnull(TODIS.PROJECTION_SALES, 0) + ISNULL(PPA.SALES_PPA, 0))) - ISNULL(SALEPPA.COGS_PROJECTED, 0)),\n"
                + " NET_PROFIT_ACTUAL  =  ((Isnull(SALEPPA.ACTUAL_SALES, 0) - (Isnull(TODIS.D_ACTUAL_SALES, 0) + ISNULL(PPA.PPA_ACTUAL_AMOUNT, 0))) - ISNULL(SALEPPA.COGS_ACTUALS, 0)) ,\n"
                + " NET_SALES_ACTUAL_PERCENTAGE   = COALESCE(((Isnull(SALEPPA.ACTUAL_SALES, 0) - (Isnull(TODIS.D_ACTUAL_SALES, 0) + ISNULL(PPA.PPA_ACTUAL_AMOUNT, 0)))) / NULLIF( SALEPPA1.EXFACTORY_ACTUAL_SALES, 0),0)*100  ,\n"
                + " NET_SALES_PROJ_PERCENTAGE    = COALESCE(((Isnull(SALEPPA.SALES_PROJECTION_SALES, 0) - (Isnull(TODIS.PROJECTION_SALES, 0) + ISNULL(PPA.SALES_PPA, 0)))) / NULLIF( SALEPPA1.EXFACTORY_FORECAST_SALES, 0),0)*100 ,\n"
                
                
                 + " EXFACTORY_ACTUAL_SALES = isnull(SALEPPA1.EXFACTORY_ACTUAL_SALES,0),\n"
                + " EXFACTORY_FORECAST_SALES = isnull(SALEPPA1.EXFACTORY_FORECAST_SALES,	0),\n"
                + " CONTRACTSALES_OF_EXFACTORY_ACTUALS = COALESCE(((SALEPPA.ACTUAL_SALES))/ NULLIF(SALEPPA1.EXFACTORY_ACTUAL_SALES,0),0)* 100, \n"
                + " CONTRACTSALES_OF_EXFACTORY_PROJECTION = COALESCE(((	Isnull(SALEPPA.SALES_PROJECTION_SALES,0)))/ NULLIF(SALEPPA1.EXFACTORY_FORECAST_SALES,0),0)* 100, \n"
                + " DISCOUNT_OF_EXFACTORY_ACTUALS = COALESCE(((	ISNULL(	TODIS.D_ACTUAL_SALES,0	)+ ISNULL(PPA.PPA_ACTUAL_AMOUNT,0)))/ NULLIF(	SALEPPA1.EXFACTORY_ACTUAL_SALES,0),0)* 100,\n"
                + " DISCOUNT_OF_EXFACTORY_PROJECTION = COALESCE(((ISNULL(TODIS.PROJECTION_SALES,0)+ ISNULL(PPA.SALES_PPA,0	)))/ NULLIF(SALEPPA1.EXFACTORY_FORECAST_SALES,0),0)* 100 \n"
                
                + " ,DEMAND_ACTUAL_SALES = isnull(SALEPPA1.DEMAND_ACTUAL_SALES, 0)"
		+ " ,DEMAND_FORECAST_SALES = isnull(SALEPPA1.DEMAND_FORECAST_SALES, 0)"
                
                + " ,DEMAND_SALES_ACTUALS_PERCENT=( ISNULL(SALEPPA.ACTUAL_SALES / NULLIF(SALEPPA1.DEMAND_ACTUAL_SALES, 0), 0) ) * 100 \n"
                + " ,DEMAND_SALES_PROJECTED_PERCENT=( ISNULL(SALEPPA.SALES_PROJECTION_SALES / NULLIF(SALEPPA1.DEMAND_FORECAST_SALES, 0), 0) ) * 100 \n"
                + " ,INVENTORY_WITHDRAWAL_SALES_ACTUALS_PERCENT=( ISNULL(SALEPPA.ACTUAL_SALES / NULLIF(SALEPPA1.INVENTORY_ACTUAL_SALES, 0), 0) ) * 100   \n"
                + " ,INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT=( ISNULL(SALEPPA.SALES_PROJECTION_SALES / NULLIF(SALEPPA1.INVENTORY_FORECAST_SALES, 0), 0) ) * 100 \n"
		+ " ,INVENTORY_ACTUAL_SALES = isnull(SALEPPA1.INVENTORY_ACTUAL_SALES, 0)"
		+ " ,INVENTORY_FORECAST_SALES = isnull(SALEPPA1.INVENTORY_FORECAST_SALES, 0)";
                
                
        return selectClause;
    }

    public String getPivotMainSelectClause(String projName) {
//        String selectClause = " " + projName + ".CONTRACT_ACTUAL_SALES as " + projName + "CONTRACT_ACTUAL_SALES,"
//                + " " + projName + ".CONTRACT_PROJECTION_SALES as " + projName + "CONTRACT_PROJECTION_SALES,"
//                + " " + projName + ".CONTRACT_ACTUAL_UNITS as " + projName + "CONTRACT_ACTUAL_UNITS,"
//                + " " + projName + ".CONTRACT_PROJECTION_UNITS as " + projName + "CONTRACT_PROJECTION_UNITS,"
//                + " " + projName + ".TOTAL_ACTUAL_RATE AS " + projName + "TOTAL_ACTUAL_RATE, "
//                + " " + projName + ".TOTAL_PROJECTION_RATE AS " + projName + "TOTAL_PROJECTION_RATE, "
//                + " " + projName + ".TOTAL_ACTUAL_DOLAR as " + projName + "TOTAL_ACTUAL_DOLAR,"
//                + " " + projName + ".TOTAL_PROJECTION_DOLAR as " + projName + "TOTAL_PROJECTION_DOLAR,"
//                + " " + projName + ".NET_ACTUAL_SALES AS " + projName + "NET_ACTUAL_SALES, "
//                + " " + projName + ".NET_PROJECTION_SALES AS " + projName + "NET_PROJECTION_SALES, "
//                + " " + projName + ".ACTUAL_RPU AS " + projName + "ACTUAL_RPU, "
//                + " " + projName + ".PROJECTED_RPU AS " + projName + "PROJECTED_RPU, "
//                + " " + projName + ".COGS_ACTUALS AS " + projName + "COGS_ACTUALS, "
//                + " " + projName + ".COGS_PROJECTED AS " + projName + "COGS_PROJECTED, "
//                + " " + projName + ".NET_PROFIT_ACTUAL  AS " + projName + "NET_PROFIT_ACTUAL,  "
//                + " " + projName + ".NET_PROFIT_PROJECTED AS " + projName + "NET_PROFIT, "
//                + " " + projName + ".NET_SALES_ACTUAL_PERCENTAGE  AS " + projName + "NET_SALES_ACTUAL_PERCENTAGE,  "
//                + " " + projName + ".NET_SALES_PROJ_PERCENTAGE   AS " + projName + "NET_SALES_PROJ_PERCENTAGE ,  "
//        
//                + " " + projName + ".EXFACTORY_ACTUAL_SALES AS " + projName + "EXFACTORY_ACTUAL_SALES, "
//                + " " + projName + ".EXFACTORY_FORECAST_SALES  AS " + projName + "EXFACTORY_FORECAST_SALES,  "
//                + " " + projName + ".CONTRACTSALES_OF_EXFACTORY_ACTUALS AS " + projName + "CONTRACTSALES_OF_EXFACTORY_ACTUALS , "
//                + " " + projName + ".CONTRACTSALES_OF_EXFACTORY_PROJECTION  AS " + projName + "CONTRACTSALES_OF_EXFACTORY_PROJECTION,  "
//                + " " + projName + ".DISCOUNT_OF_EXFACTORY_ACTUALS   AS " + projName + "DISCOUNT_OF_EXFACTORY_ACTUALS ,  "
//                + " " + projName + ".DISCOUNT_OF_EXFACTORY_PROJECTION AS " + projName + "DISCOUNT_OF_EXFACTORY_PROJECTION ";

               String selectClause = " CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS,case  when isnull( C.PERIODS, 0 )= 0 then 1 else C.PERIODS end,01) THEN "
                + " " + projName + ".CONTRACT_ACTUAL_SALES  ELSE NULL END as " + projName + "CONTRACT_ACTUAL_SALES,"
                + " " + projName + ".CONTRACT_PROJECTION_SALES as " + projName + "CONTRACT_PROJECTION_SALES,"
                + " CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS,case  when isnull( C.PERIODS, 0 )= 0 then 1 else C.PERIODS end,01) THEN "
                + " " + projName + ".CONTRACT_ACTUAL_UNITS  ELSE NULL END as " + projName + "CONTRACT_ACTUAL_UNITS,"
                + " " + projName + ".CONTRACT_PROJECTION_UNITS as " + projName + "CONTRACT_PROJECTION_UNITS,"
                + " CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS,case  when isnull( C.PERIODS, 0 )= 0 then 1 else C.PERIODS end,01) THEN "
                + " " + projName + ".TOTAL_ACTUAL_RATE ELSE NULL END  AS " + projName + "TOTAL_ACTUAL_RATE, "
                + " " + projName + ".TOTAL_PROJECTION_RATE AS " + projName + "TOTAL_PROJECTION_RATE, "
                + " CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS,case  when isnull( C.PERIODS, 0 )= 0 then 1 else C.PERIODS end,01) THEN "
                + " " + projName + ".TOTAL_ACTUAL_DOLAR ELSE NULL END as " + projName + "TOTAL_ACTUAL_DOLAR,"
                + " " + projName + ".TOTAL_PROJECTION_DOLAR as " + projName + "TOTAL_PROJECTION_DOLAR,"
                + " CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS,case  when isnull( C.PERIODS, 0 )= 0 then 1 else C.PERIODS end,01) THEN "
                + " " + projName + ".NET_ACTUAL_SALES ELSE NULL END AS " + projName + "NET_ACTUAL_SALES, "
                + " " + projName + ".NET_PROJECTION_SALES AS " + projName + "NET_PROJECTION_SALES, "
                + " CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS,case  when isnull( C.PERIODS, 0 )= 0 then 1 else C.PERIODS end,01) THEN "
                + " " + projName + ".ACTUAL_RPU ELSE NULL END AS " + projName + "ACTUAL_RPU, "
                + " " + projName + ".PROJECTED_RPU AS " + projName + "PROJECTED_RPU, "
                + " CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS,case  when isnull( C.PERIODS, 0 )= 0 then 1 else C.PERIODS end,01) THEN "
                + " " + projName + ".COGS_ACTUALS ELSE NULL END AS " + projName + "COGS_ACTUALS, "
                + " " + projName + ".COGS_PROJECTED AS " + projName + "COGS_PROJECTED, "
                + " CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS,case  when isnull( C.PERIODS, 0 )= 0 then 1 else C.PERIODS end,01) THEN "
                + " " + projName + ".NET_PROFIT_ACTUAL  ELSE NULL END AS " + projName + "NET_PROFIT_ACTUAL,  "
                + " " + projName + ".NET_PROFIT_PROJECTED AS " + projName + "NET_PROFIT, "
                + " CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS,case  when isnull( C.PERIODS, 0 )= 0 then 1 else C.PERIODS end,01) THEN "
                + " " + projName + ".NET_SALES_ACTUAL_PERCENTAGE  ELSE NULL END AS " + projName + "NET_SALES_ACTUAL_PERCENTAGE,  "
                + " " + projName + ".NET_SALES_PROJ_PERCENTAGE   AS " + projName + "NET_SALES_PROJ_PERCENTAGE ,  "
                + " CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS,case  when isnull( C.PERIODS, 0 )= 0 then 1 else C.PERIODS end,01) THEN "
                + " " + projName + ".EXFACTORY_ACTUAL_SALES  ELSE NULL END AS " + projName + "EXFACTORY_ACTUAL_SALES,  "
                + " " + projName + ".EXFACTORY_FORECAST_SALES AS " + projName + "EXFACTORY_FORECAST_SALES, "
                + " CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS,case  when isnull( C.PERIODS, 0 )= 0 then 1 else C.PERIODS end,01) THEN "
                + " " + projName + ".CONTRACTSALES_OF_EXFACTORY_ACTUALS  ELSE NULL END AS " + projName + "CONTRACTSALES_OF_EXFACTORY_ACTUALS,  "
                + " " + projName + ".CONTRACTSALES_OF_EXFACTORY_PROJECTION AS " + projName + "CONTRACTSALES_OF_EXFACTORY_PROJECTION, "
                + " CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS,case  when isnull( C.PERIODS, 0 )= 0 then 1 else C.PERIODS end,01) THEN "
                + " " + projName + ".DISCOUNT_OF_EXFACTORY_ACTUALS  ELSE NULL END AS " + projName + "DISCOUNT_OF_EXFACTORY_ACTUALS,  "
                + " " + projName + ".DISCOUNT_OF_EXFACTORY_PROJECTION AS " + projName + "DISCOUNT_OF_EXFACTORY_PROJECTION, "
                       
                       
                + " CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS,case  when isnull( C.PERIODS, 0 )= 0 then 1 else C.PERIODS end,01) THEN "
                + " " + projName + ".DEMAND_ACTUAL_SALES  ELSE NULL END AS " + projName + "DEMAND_ACTUAL_SALES,  "
                + " " + projName + ".DEMAND_FORECAST_SALES AS " + projName + "DEMAND_FORECAST_SALES, "
                + " CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS,case  when isnull( C.PERIODS, 0 )= 0 then 1 else C.PERIODS end,01) THEN "
                + " " + projName + ".DEMAND_SALES_ACTUALS_PERCENT  ELSE NULL END AS " + projName + "DEMAND_SALES_ACTUALS_PERCENT,  "
                + " " + projName + ".DEMAND_SALES_PROJECTED_PERCENT AS " + projName + "DEMAND_SALES_PROJECTED_PERCENT, "   
                       
                + " CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS,case  when isnull( C.PERIODS, 0 )= 0 then 1 else C.PERIODS end,01) THEN "
                + " " + projName + ".INVENTORY_ACTUAL_SALES  ELSE NULL END AS " + projName + "INVENTORY_ACTUAL_SALES,  "
                + " " + projName + ".INVENTORY_FORECAST_SALES AS " + projName + "INVENTORY_FORECAST_SALES, "
                + " CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS,case  when isnull( C.PERIODS, 0 )= 0 then 1 else C.PERIODS end,01) THEN "
                + " " + projName + ".INVENTORY_WITHDRAWAL_SALES_ACTUALS_PERCENT  ELSE NULL END AS " + projName + "INVENTORY_WITHDRAWAL_SALES_ACTUALS_PERCENT,  "
                + " " + projName + ".INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT AS " + projName + "INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT "  ;      
               
        return selectClause;
    }
    
    
    public String getPivotMainSelectClausePeriod(String projName) {
        String selectClause = " CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS,case  when isnull( C.PERIODS, 0 )= 0 then 1 else C.PERIODS end,01) THEN "
                + " " + projName + ".CONTRACT_ACTUAL_SALES  ELSE NULL END as " + projName + "CONTRACT_ACTUAL_SALES,"
                + " " + projName + ".CONTRACT_PROJECTION_SALES as " + projName + "CONTRACT_PROJECTION_SALES,"
                + " CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS,case  when isnull( C.PERIODS, 0 )= 0 then 1 else C.PERIODS end,01) THEN "
                + " " + projName + ".CONTRACT_ACTUAL_UNITS  ELSE NULL END as " + projName + "CONTRACT_ACTUAL_UNITS,"
                + " " + projName + ".CONTRACT_PROJECTION_UNITS as " + projName + "CONTRACT_PROJECTION_UNITS,"
                + " CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS,case  when isnull( C.PERIODS, 0 )= 0 then 1 else C.PERIODS end,01) THEN "
                + " " + projName + ".TOTAL_ACTUAL_RATE ELSE NULL END  AS " + projName + "TOTAL_ACTUAL_RATE, "
                + " " + projName + ".TOTAL_PROJECTION_RATE AS " + projName + "TOTAL_PROJECTION_RATE, "
                + " CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS,case  when isnull( C.PERIODS, 0 )= 0 then 1 else C.PERIODS end,01) THEN "
                + " " + projName + ".TOTAL_ACTUAL_DOLAR ELSE NULL END as " + projName + "TOTAL_ACTUAL_DOLAR,"
                + " " + projName + ".TOTAL_PROJECTION_DOLAR as " + projName + "TOTAL_PROJECTION_DOLAR,"
                + " CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS,case  when isnull( C.PERIODS, 0 )= 0 then 1 else C.PERIODS end,01) THEN "
                + " " + projName + ".NET_ACTUAL_SALES ELSE NULL END AS " + projName + "NET_ACTUAL_SALES, "
                + " " + projName + ".NET_PROJECTION_SALES AS " + projName + "NET_PROJECTION_SALES, "
                + " CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS,case  when isnull( C.PERIODS, 0 )= 0 then 1 else C.PERIODS end,01) THEN "
                + " " + projName + ".ACTUAL_RPU ELSE NULL END AS " + projName + "ACTUAL_RPU, "
                + " " + projName + ".PROJECTED_RPU AS " + projName + "PROJECTED_RPU, "
                + " CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS,case  when isnull( C.PERIODS, 0 )= 0 then 1 else C.PERIODS end,01) THEN "
                + " " + projName + ".COGS_ACTUALS ELSE NULL END AS " + projName + "COGS_ACTUALS, "
                + " " + projName + ".COGS_PROJECTED AS " + projName + "COGS_PROJECTED, "
                + " CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS,case  when isnull( C.PERIODS, 0 )= 0 then 1 else C.PERIODS end,01) THEN "
                + " " + projName + ".NET_PROFIT_ACTUAL  ELSE NULL END AS " + projName + "NET_PROFIT_ACTUAL,  "
                + " " + projName + ".NET_PROFIT_PROJECTED AS " + projName + "NET_PROFIT, "
                + " CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS,case  when isnull( C.PERIODS, 0 )= 0 then 1 else C.PERIODS end,01) THEN "
                + " " + projName + ".NET_SALES_ACTUAL_PERCENTAGE  ELSE NULL END AS " + projName + "NET_SALES_ACTUAL_PERCENTAGE,  "
                + " " + projName + ".NET_SALES_PROJ_PERCENTAGE   AS " + projName + "NET_SALES_PROJ_PERCENTAGE ,  "
                + " CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS,case  when isnull( C.PERIODS, 0 )= 0 then 1 else C.PERIODS end,01) THEN "
                + " " + projName + ".EXFACTORY_ACTUAL_SALES  ELSE NULL END AS " + projName + "EXFACTORY_ACTUAL_SALES,  "
                + " " + projName + ".EXFACTORY_FORECAST_SALES AS " + projName + "EXFACTORY_FORECAST_SALES, "
                + " CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS,case  when isnull( C.PERIODS, 0 )= 0 then 1 else C.PERIODS end,01) THEN "
                + " " + projName + ".CONTRACTSALES_OF_EXFACTORY_ACTUALS  ELSE NULL END AS " + projName + "CONTRACTSALES_OF_EXFACTORY_ACTUALS,  "
                + " " + projName + ".CONTRACTSALES_OF_EXFACTORY_PROJECTION AS " + projName + "CONTRACTSALES_OF_EXFACTORY_PROJECTION, "
                 + " CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS,case  when isnull( C.PERIODS, 0 )= 0 then 1 else C.PERIODS end,01) THEN "
                + " " + projName + ".DISCOUNT_OF_EXFACTORY_ACTUALS  ELSE NULL END AS " + projName + "DISCOUNT_OF_EXFACTORY_ACTUALS,  "
                + " " + projName + ".DISCOUNT_OF_EXFACTORY_PROJECTION AS " + projName + "DISCOUNT_OF_EXFACTORY_PROJECTION, "
                
                + " CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS,case  when isnull( C.PERIODS, 0 )= 0 then 1 else C.PERIODS end,01) THEN "
                + " " + projName + ".DEMAND_ACTUAL_SALES  ELSE NULL END AS " + projName + "DEMAND_ACTUAL_SALES,  "
                + " " + projName + ".DEMAND_FORECAST_SALES AS " + projName + "DEMAND_FORECAST_SALES, "
                + " CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS,case  when isnull( C.PERIODS, 0 )= 0 then 1 else C.PERIODS end,01) THEN "
                + " " + projName + ".DEMAND_SALES_ACTUALS_PERCENT  ELSE NULL END AS " + projName + "DEMAND_SALES_ACTUALS_PERCENT,  "
                + " " + projName + ".DEMAND_SALES_PROJECTED_PERCENT AS " + projName + "DEMAND_SALES_PROJECTED_PERCENT, "   
                       
                + " CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS,case  when isnull( C.PERIODS, 0 )= 0 then 1 else C.PERIODS end,01) THEN "
                + " " + projName + ".INVENTORY_ACTUAL_SALES  ELSE NULL END AS " + projName + "INVENTORY_ACTUAL_SALES,  "
                + " " + projName + ".INVENTORY_FORECAST_SALES AS " + projName + "INVENTORY_FORECAST_SALES, "
                + " CASE WHEN @CURRENT_DTAE>DATEFROMPARTS(C.YEARS,case  when isnull( C.PERIODS, 0 )= 0 then 1 else C.PERIODS end,01) THEN "
                + " " + projName + ".INVENTORY_WITHDRAWAL_SALES_ACTUALS_PERCENT  ELSE NULL END AS " + projName + "INVENTORY_WITHDRAWAL_SALES_ACTUALS_PERCENT,  "
                + " " + projName + ".INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT AS " + projName + "INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT "  ;      
        return selectClause;
    }

        public String getProjectionResultsDiscountsQuery(PVSelectionDTO projSelDTO, boolean isPriorDiscount) {
         boolean viewFlag=Boolean.FALSE;
        String mainMasterTable = "  NM_DISCOUNT_PROJ_MASTER ";
        String tempMasterTable = " ST_NM_DISCOUNT_PROJ_MASTER ";
        String mainTable = " NM_DISCOUNT_PROJECTION ";
        String mainTable1 = " NM_ACTUAL_DISCOUNT ";
        String tempTable = " ST_NM_DISCOUNT_PROJECTION ";
        String tempTable1 = " ST_NM_ACTUAL_DISCOUNT ";
        String projTableSid = " CCP_DETAILS_SID ";
        String masterTable = StringUtils.EMPTY;
        String table = StringUtils.EMPTY;
        String table1 = StringUtils.EMPTY;
        if (!projSelDTO.isIsPrior() && !viewFlag) {
            masterTable = tempMasterTable;
            table = tempTable;
            table1 = tempTable1;
        } else {
            masterTable = mainMasterTable;
            table = mainTable;
            table1 = mainTable1;
            projTableSid=" PROJECTION_DETAILS_SID ";
        }
        String selectClause = " select ";
        String whereClause = StringUtils.EMPTY;
        String groupBy = " I.\"YEAR\"";

        String customQuery = StringUtils.EMPTY;
        selectClause += "I.\"YEAR\" as YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "cast ('0' as int) as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += "I.\"MONTH\" as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.\"MONTH\"";
        }

        // To handle the scenario where any discount is not selected in program selection lookup
        String discountTypeColumnName = "'Total discounts' as DISCOUNTS";
        if (projSelDTO.getDiscountLevel().equals("Program Category")) {
            if (projSelDTO.getDiscountNameList() != null && !projSelDTO.getDiscountNameList().isEmpty()) {
                if (!projSelDTO.isIsTotal()) {
                    discountTypeColumnName = " B.PRICE_GROUP_TYPE AS DISCOUNTS";
                    groupBy += ", " + " B.PRICE_GROUP_TYPE";
                }
                if (!isPriorDiscount) {
                    whereClause += " WHERE B.RS_CONTRACT_SID in (" + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false) + ")";
                }
            }
        } else {
            if (projSelDTO.getDiscountNoList() != null && !projSelDTO.getDiscountNoList().isEmpty()) {
                if (!projSelDTO.isIsTotal()) {
                    discountTypeColumnName = " J.RS_NAME as DISCOUNTS";
                    groupBy += ", " + " J.RS_NAME";
                }
                if (!isPriorDiscount) {
                    whereClause += " WHERE B.RS_CONTRACT_SID in (" + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false) + ")";
                }
            }
        }
        selectClause += discountTypeColumnName + ", ";

        String futureQuery = selectClause + "sum(A.PROJECTION_SALES) as PROJECTION_SALES, Sum(A.PROJECTION_RPU) AS RPU, SUM( C.ACTUAL_SALES ) AS D_ACTUAL_SALES from \n";

        if (projSelDTO.getCurrentOrPrior().equalsIgnoreCase(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
            futureQuery += "#TEMP_CCP T  CROSS\n" +
"				JOIN @PERIOD I   "
                    + "JOIN #SELECTED_HIERARCHY_NO CCP ON\n" +
"					T.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID \n"
                    + "                   JOIN    " + masterTable + " B ON T.CCP_DETAILS_SID=B.CCP_DETAILS_SID \n "
                    + "                   JOIN   " + table + " A ON A.CCP_DETAILS_SID = T.CCP_DETAILS_SID  AND I.PERIOD_SID = A.PERIOD_SID\n" +
"					AND A.RS_CONTRACT_SID = B.RS_CONTRACT_SID\n"
                    
                    
                    + "                   LEFT  JOIN   " + table1 + " C ON A."+projTableSid+" = C."+projTableSid+"\n"
                    + "                   AND A.RS_CONTRACT_SID = C.RS_CONTRACT_SID\n "                    
                    + "                   AND I.PERIOD_SID  = C.PERIOD_SID \n "     ;               
                    
                    
                    
                    
//                    +"                    JOIN   @PERIOD I ON I.PERIOD_SID = A.PERIOD_SID\n"
//                    + "                   JOIN   @CCP CCP ON T.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID ";
        } else {
            futureQuery += " PROJECTION_DETAILS PD JOIN @PERIOD I ON PD.PROJECTION_MASTER_SID = "+ projSelDTO.getProjectionId() +"\n"
            
             + "JOIN #SELECTED_HIERARCHY_NO SN ON\n" +
"					SN.CCP_DETAILS_SID = PD.CCP_DETAILS_SID \n"
            
            
                    + "                      JOIN   " + masterTable + " B ON PD.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID\n "
                     + "                   AND   B.RS_CONTRACT_SID in (" + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false) + ")"
                    +"  LEFT JOIN " +table+ " A ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID AND A.RS_CONTRACT_SID = B.RS_CONTRACT_SID AND I.PERIOD_SID = A.PERIOD_SID"
                    
                    +"  LEFT JOIN " +table1+ " C ON B.PROJECTION_DETAILS_SID = C.PROJECTION_DETAILS_SID AND B.RS_CONTRACT_SID = C.RS_CONTRACT_SID AND I.PERIOD_SID = C.PERIOD_SID";
                   
            
//                    + "                      JOIN   PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID\n"
//                    + "                                                  AND PD.PROJECTION_MASTER_SID = "+ projSelDTO.getProjectionId() +"\n"
//                    + "                      JOIN   @PERIOD I ON I.PERIOD_SID = A.PERIOD_SID "
//                    + "                      LEFT JOIN NM_ACTUAL_DISCOUNT C ON A.PROJECTION_DETAILS_SID = C.PROJECTION_DETAILS_SID AND A.RS_CONTRACT_SID = C.RS_CONTRACT_SID AND A.PERIOD_SID = A.PERIOD_SID ";
        }
        if (!projSelDTO.isIsTotal()) {
            futureQuery += " JOIN RS_CONTRACT J ON J.RS_CONTRACT_SID = B.RS_CONTRACT_SID ";
        }
        futureQuery += whereClause + " GROUP BY " + groupBy;
        customQuery = futureQuery;
        return customQuery;
    }

    public String getProjectionResultsSalesQuery(PVSelectionDTO projSelDTO) {
           boolean viewFlag=Boolean.FALSE;
        String mainSalesTable = " NM_SALES_PROJECTION ";
        String mainSalesTable1 = " NM_ACTUAL_SALES ";
        String mainSalesTable2 = " PRODUCT_FILE ";
        String tempSalesTable = " ST_NM_SALES_PROJECTION ";
        String tempSalesTable1 = " ST_NM_ACTUAL_SALES ";
        String tempSalesTable2 = " ST_PRODUCT_FILE ";

        String salesTable = StringUtils.EMPTY;
        String salesTable1 = StringUtils.EMPTY;
        String salesTable2 = StringUtils.EMPTY;

        if (!projSelDTO.isIsPrior() && !viewFlag) {
            salesTable = tempSalesTable;
            salesTable1 = tempSalesTable1;
            salesTable2 = tempSalesTable2;
        } else {
            salesTable = mainSalesTable;
            salesTable1 = mainSalesTable1;
            salesTable2 = mainSalesTable2;
        }
        String selectClause = " select ";
        String groupBy = " YEARS";
        selectClause += "I.\"YEAR\" as YEARS, ";
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER as PERIODS, ";
            groupBy += ", PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, ";
            groupBy += ", PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += " cast ('0' as int) as PERIODS, ";
            groupBy += ", PERIODS";

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += "I.\"MONTH\" as PERIODS, ";
            groupBy += ", PERIODS";
        }

        // To handle the scenario where any discount is not selected in program selection lookup
        String customSql = "";
        if (projSelDTO.getCurrentOrPrior().equalsIgnoreCase(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            customSql += " FROM NM_SALES_PROJECTION A1\n"
                    + "                              INNER JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID = A1.PROJECTION_DETAILS_SID\n"
                    + "                                                              AND PD.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                    + "                              INNER JOIN CCP_DETAILS CC ON CC.CCP_DETAILS_SID = PD.CCP_DETAILS_SID"
        
                    + "                              LEFT JOIN NM_ACTUAL_SALES A2 ON A2.PROJECTION_DETAILS_SID = A1.PROJECTION_DETAILS_SID AND A1.PERIOD_SID = A2.PERIOD_SID LEFT JOIN ST_PRODUCT_FILE ST ON ST.ITEM_MASTER_SID = CC.ITEM_MASTER_SID AND A1.PERIOD_SID = ST.PERIOD_SID "
        
                   
                   
        
                    +"                           LEFT JOIN @COGS_ITEM U ON CC.ITEM_MASTER_SID = U.ITEM_MASTER_SID\n";
        } else {
            customSql += " from "
                    + salesTable + " A1 INNER JOIN #TEMP_CCP T ON T.CCP_DETAILS_SID = A1.CCP_DETAILS_SID "
                    
                    + " LEFT JOIN "+ salesTable1 + " A2 ON A2.CCP_DETAILS_SID = A1.CCP_DETAILS_SID AND A1.PERIOD_SID = A2.PERIOD_SID "
                    
                    + " LEFT JOIN "+ salesTable2 + " ST  ON ST.ITEM_MASTER_SID = T.ITEM_MASTER_SID AND A1.PERIOD_SID = ST.PERIOD_SID "
                    
                    + "                           INNER JOIN @CCP CC ON CC.CCP_DETAILS_SID = T.CCP_DETAILS_SID\n"
                    +"                           LEFT JOIN @COGS_ITEM U ON T.ITEM_MASTER_SID = U.ITEM_MASTER_SID\n";
        }
                   
        customSql += "                                                  AND A1.PERIOD_SID = U.PERIOD_SID\n"
                + "                           INNER JOIN @PERIOD I ON I.PERIOD_SID = A1.PERIOD_SID  "
                + "\n";
                    
        customSql += "  )A"
                + " group by  " + groupBy;

        String futureQuery = selectClause
                + " (A1.PROJECTION_SALES) "
                + " ,(A1.PROJECTION_UNITS)"
                
                + " , ISNULL( A2.ACTUAL_SALES,0 )AS ACTUAL_SALES,"
                + " ISNULL( A2.ACTUAL_UNITS,0 )AS ACTUAL_UNITS"
                + " , A1.PERIOD_SID\n"
                + " , U.ITEM_PRICE"
                + " , ISNULL( ST.EXFACTORY_ACTUAL_SALES , 0) AS EXFACTORY_ACTUAL_SALES"
                + " , ISNULL( ST.EXFACTORY_FORECAST_SALES , 0) AS EXFACTORY_FORECAST_SALES"
                + customSql;

        return futureQuery;
    }
    
    
    public String getProjectionResultsSalesQueryForPeriod(PVSelectionDTO projSelDTO) {
           boolean viewFlag=Boolean.FALSE;
        String mainSalesTable = " NM_SALES_PROJECTION ";
        String mainSalesTable1 = " NM_ACTUAL_SALES ";
        String mainSalesTable2 = " PRODUCT_FILE ";
        String tempSalesTable = " ST_NM_SALES_PROJECTION ";
        String tempSalesTable1 = " ST_NM_ACTUAL_SALES ";
        String tempSalesTable2 = " ST_PRODUCT_FILE ";

        String salesTable = StringUtils.EMPTY;
        String salesTable1 = StringUtils.EMPTY;
        String salesTable2 = StringUtils.EMPTY;

        if (!projSelDTO.isIsPrior() && !viewFlag) {
            salesTable = tempSalesTable;
            salesTable1 = tempSalesTable1;
            salesTable2 = tempSalesTable2;
        } else {
            salesTable = mainSalesTable;
            salesTable1 = mainSalesTable1;
            salesTable2 = mainSalesTable2;
        }
        String selectClause = " select ";
        String groupBy = " YEARS";
        selectClause += "I.\"YEAR\" as YEARS, ";
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER as PERIODS, ";
            groupBy += ", PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, ";
            groupBy += ", PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += " cast ('0' as int) as PERIODS, ";
            groupBy += ", PERIODS";

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += "I.\"MONTH\" as PERIODS, ";
            groupBy += ", PERIODS";
        }

        // To handle the scenario where any discount is not selected in program selection lookup
        String customSql = "";
        if (projSelDTO.getCurrentOrPrior().equalsIgnoreCase(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
             customSql += " FROM PROJECTION_DETAILS PD \n"
                    + "  JOIN  @PERIOD I ON PD.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                    + "  join #SELECTED_HIERARCHY_NO sn on sn.CCP_DETAILS_SID=pd.CCP_DETAILS_SID "
                    + "  INNER JOIN CCP_DETAILS CC ON CC.CCP_DETAILS_SID = PD.CCP_DETAILS_SID"
                   
                    + "  LEFT JOIN NM_SALES_PROJECTION  A1 ON PD.PROJECTION_DETAILS_SID = A1.PROJECTION_DETAILS_SID AND I.PERIOD_SID = A1.PERIOD_SID "
                    + "  LEFT JOIN NM_ACTUAL_SALES A2   ON PD.PROJECTION_DETAILS_SID = A2.PROJECTION_DETAILS_SID AND I.PERIOD_SID = A2.PERIOD_SID "
                    + " LEFT JOIN #PRODUCT_FILE_DATA ST ON ST.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID AND ST.ITEM_MASTER_SID = CC.ITEM_MASTER_SID AND I.PERIOD_SID = ST.PERIOD_SID "
                   
                    +"   LEFT JOIN @COGS_ITEM U ON CC.ITEM_MASTER_SID = U.ITEM_MASTER_SID AND I.PERIOD_SID = U.PERIOD_SID \n";
        
        
        } else {
            customSql += " from #TEMP_CCP T CROSS\n" +
"						JOIN @PERIOD I\n" +
"						INNER JOIN #SELECTED_HIERARCHY_NO CC ON\n" +
"							CC.CCP_DETAILS_SID = T.CCP_DETAILS_SID "
                    + " LEFT JOIN "+ salesTable + " A1 ON T.CCP_DETAILS_SID = A1.CCP_DETAILS_SID AND A1.PERIOD_SID = I.PERIOD_SID "
                    
                   + " LEFT JOIN "+ salesTable1 + " A2 ON T.CCP_DETAILS_SID = A2.CCP_DETAILS_SID AND A2.PERIOD_SID = I.PERIOD_SID "
                    
                    
                   
                    + " LEFT JOIN "+ salesTable2 + " ST  ON ST.ITEM_MASTER_SID = T.ITEM_MASTER_SID AND I.PERIOD_SID = ST.PERIOD_SID "
                    
                    +"                           LEFT JOIN @COGS_ITEM U ON T.ITEM_MASTER_SID = U.ITEM_MASTER_SID AND I.PERIOD_SID = U.PERIOD_SID \n";
        }

        customSql += "  )A"
                + " group by  " + groupBy;

        String futureQuery = selectClause
                + " (A1.PROJECTION_SALES) "
                + " ,(A1.PROJECTION_UNITS)"
                
                + " , ISNULL( A2.ACTUAL_SALES,0 )AS ACTUAL_SALES,"
                + " ISNULL( A2.ACTUAL_UNITS,0 )AS ACTUAL_UNITS"
                + " , A1.PERIOD_SID\n"
                + " , U.ITEM_PRICE"
                + " , ISNULL( ST.EXFACTORY_ACTUAL_SALES , 0) AS EXFACTORY_ACTUAL_SALES"
                + " , ISNULL( ST.EXFACTORY_FORECAST_SALES , 0) AS EXFACTORY_FORECAST_SALES"
                + customSql;

        return futureQuery;
    }
    
    
     public String getProjectionResultsSalesQueryForDiscount(PVSelectionDTO projSelDTO) {
           boolean viewFlag=Boolean.FALSE;
        String mainSalesTable = " NM_SALES_PROJECTION ";
        String mainSalesTable1 = " NM_ACTUAL_SALES ";
        String mainSalesTable2 = " PRODUCT_FILE ";
        String tempSalesTable = " ST_NM_SALES_PROJECTION ";
        String tempSalesTable1 = " ST_NM_ACTUAL_SALES ";
        String tempSalesTable2 = " ST_PRODUCT_FILE ";

        String salesTable = StringUtils.EMPTY;
        String salesTable1 = StringUtils.EMPTY;
        String salesTable2 = StringUtils.EMPTY;

        if (!projSelDTO.isIsPrior() && !viewFlag) {
            salesTable = tempSalesTable;
            salesTable1 = tempSalesTable1;
            salesTable2 = tempSalesTable2;
        } else {
            salesTable = mainSalesTable;
            salesTable1 = mainSalesTable1;
            salesTable2 = mainSalesTable2;
        }
        String selectClause = " select ";
        String groupBy = " YEARS";
        selectClause += "I.\"YEAR\" as YEARS, ";
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER as PERIODS, ";
            groupBy += ", PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, ";
            groupBy += ", PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += " cast ('0' as int) as PERIODS, ";
            groupBy += ", PERIODS";

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += "I.\"MONTH\" as PERIODS, ";
            groupBy += ", PERIODS";
        }
         

        // To handle the scenario where any discount is not selected in program selection lookup
        String customSql = "";
        if (projSelDTO.getCurrentOrPrior().equalsIgnoreCase(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
             customSql += "  , RC.RS_CONTRACT_SID  " +
                     " , RC.PRICE_GROUP_TYPE AS DISCOUNTS " +
                     " FROM PROJECTION_DETAILS PD \n"
                    + "  JOIN  @PERIOD I ON PD.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                    + "  join (SELECT distinct RS_CONTRACT_SID,CCP_DETAILS_SID,PRICE_GROUP_TYPE FROM #rs_combination) rc on rc.CCP_DETAILS_SID=pd.CCP_DETAILS_SID "
                    + "  INNER JOIN CCP_DETAILS CC ON CC.CCP_DETAILS_SID = PD.CCP_DETAILS_SID"
                   
                    + "  LEFT JOIN NM_SALES_PROJECTION  A1 ON PD.PROJECTION_DETAILS_SID = A1.PROJECTION_DETAILS_SID AND I.PERIOD_SID = A1.PERIOD_SID "
                    + "  LEFT JOIN NM_ACTUAL_SALES A2   ON PD.PROJECTION_DETAILS_SID = A2.PROJECTION_DETAILS_SID AND I.PERIOD_SID = A2.PERIOD_SID ";
             
             groupBy += " , A.DISCOUNTS ";
        
        } else {
            customSql +=  " , RS_CONTRACT_SID\n"
                + " , B.PRICE_GROUP_TYPE AS DISCOUNTS\n"
                 +   " from #TEMP_CCP T CROSS JOIN @PERIOD I  \n" +
    "	inner  join (select distinct hierarchy_no,CCP_DETAILS_SID,RS_CONTRACT_SID,PRICE_GROUP_TYPE from #rs_combination ) B on B.CCP_DETAILS_SID=t.CCP_DETAILS_SID \n" +
                     " LEFT JOIN "+ salesTable + " A1 ON T.CCP_DETAILS_SID = A1.CCP_DETAILS_SID AND A1.PERIOD_SID = I.PERIOD_SID "
                    
                   + " LEFT JOIN "+ salesTable1 + " A2 ON T.CCP_DETAILS_SID = A2.CCP_DETAILS_SID AND A2.PERIOD_SID = I.PERIOD_SID ";
            groupBy += ", DISCOUNTS ";
        }


        customSql += "  )A"
                + " group by  " + groupBy;

        String futureQuery = selectClause
                + " (A1.PROJECTION_SALES) "
                + " ,(A1.PROJECTION_UNITS)"
                + " , ISNULL( A2.ACTUAL_SALES,0 )AS ACTUAL_SALES,"
                + " ISNULL( A2.ACTUAL_UNITS,0 )AS ACTUAL_UNITS"
                + " , A1.PERIOD_SID\n"
                
                + customSql;

        return futureQuery;
    }
     
     
      public String getProjectionResultsSalesQueryForDiscount1(PVSelectionDTO projSelDTO) {
           boolean viewFlag=Boolean.FALSE;
        String mainSalesTable = " NM_SALES_PROJECTION ";
        String mainSalesTable1 = " NM_ACTUAL_SALES ";
        String mainSalesTable2 = " PRODUCT_FILE ";
        String tempSalesTable = " ST_NM_SALES_PROJECTION ";
        String tempSalesTable1 = " ST_NM_ACTUAL_SALES ";
        String tempSalesTable2 = " ST_PRODUCT_FILE ";

        String salesTable = StringUtils.EMPTY;
        String salesTable1 = StringUtils.EMPTY;
        String salesTable2 = StringUtils.EMPTY;

        if (!projSelDTO.isIsPrior() && !viewFlag) {
            salesTable = tempSalesTable;
            salesTable1 = tempSalesTable1;
            salesTable2 = tempSalesTable2;
        } else {
            salesTable = mainSalesTable;
            salesTable1 = mainSalesTable1;
            salesTable2 = mainSalesTable2;
        }
        String selectClause = " select ";
        String groupBy = " YEARS";
        selectClause += "I.\"YEAR\" as YEARS, ";
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER as PERIODS, ";
            groupBy += ", PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, ";
            groupBy += ", PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += " cast ('0' as int) as PERIODS, ";
            groupBy += ", PERIODS";

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += "I.\"MONTH\" as PERIODS, ";
            groupBy += ", PERIODS";
        }
         

        // To handle the scenario where any discount is not selected in program selection lookup
        String customSql = "";
        if (projSelDTO.getCurrentOrPrior().equalsIgnoreCase(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
             customSql += " , RC.PRICE_GROUP_TYPE AS DISCOUNTS  "
                     + " FROM PROJECTION_DETAILS PD \n"
                    + "  JOIN  @PERIOD I ON PD.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                    + "  join (SELECT distinct RS_CONTRACT_SID,CCP_DETAILS_SID,PRICE_GROUP_TYPE FROM #PPA_RS_COMBINATION) rc on rc.CCP_DETAILS_SID=pd.CCP_DETAILS_SID "
                    + "  INNER JOIN CCP_DETAILS CC ON CC.CCP_DETAILS_SID = PD.CCP_DETAILS_SID"
                   
                    + "  LEFT JOIN NM_SALES_PROJECTION  A1 ON PD.PROJECTION_DETAILS_SID = A1.PROJECTION_DETAILS_SID AND I.PERIOD_SID = A1.PERIOD_SID "
                    + "  LEFT JOIN NM_ACTUAL_SALES A2   ON PD.PROJECTION_DETAILS_SID = A2.PROJECTION_DETAILS_SID AND I.PERIOD_SID = A2.PERIOD_SID ";
             
              groupBy += ",  DISCOUNTS ";
             
        
        } else {
            customSql += " , PRICE_GROUP_TYPE AS DISCOUNTS "
                    + " from #TEMP_CCP T CROSS JOIN @PERIOD I  \n" +
    "	 join (select distinct hierarchy_no,CCP_DETAILS_SID,RS_CONTRACT_SID,PRICE_GROUP_TYPE from #PPA_RS_COMBINATION ) B on B.CCP_DETAILS_SID=t.CCP_DETAILS_SID \n" +
                     " LEFT JOIN "+ salesTable + " A1 ON T.CCP_DETAILS_SID = A1.CCP_DETAILS_SID AND A1.PERIOD_SID = I.PERIOD_SID "
                    
                   + " LEFT JOIN "+ salesTable1 + " A2 ON T.CCP_DETAILS_SID = A2.CCP_DETAILS_SID AND A2.PERIOD_SID = I.PERIOD_SID ";
            
            groupBy += ",  DISCOUNTS ";
        }


        customSql += "  )A"
                + " group by  " + groupBy;

        String futureQuery = selectClause
                + " (A1.PROJECTION_SALES) "
                + " ,(A1.PROJECTION_UNITS)"
                + " , ISNULL( A2.ACTUAL_SALES,0 )AS ACTUAL_SALES,"
                + " ISNULL( A2.ACTUAL_UNITS,0 )AS ACTUAL_UNITS"
                + " , A1.PERIOD_SID\n"
                + " , RS_CONTRACT_SID\n"
                
                + customSql;

        return futureQuery;
    }
    
    
    
     public String getProperFileData(PVSelectionDTO projSelDTO) {
        boolean viewFlag=Boolean.FALSE;
        String mainSalesTable = " #PRODUCT_FILE_DATA ";
        String tempSalesTable = " ST_PRODUCT_FILE ";

        String salesTable = StringUtils.EMPTY;
        String salesTable1 = StringUtils.EMPTY;
        String salesTable2 = StringUtils.EMPTY;

        if (!projSelDTO.isIsPrior() && !viewFlag) {
            salesTable = tempSalesTable;
        } else {
            salesTable = mainSalesTable;
        }
        String selectClause = " select ";
        String groupBy = " I.\"YEAR\"  ";
        selectClause += "I.\"YEAR\" as YEARS, ";
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER as PERIODS, ";
            groupBy += ", I.QUARTER ";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, ";
            groupBy += ", I.SEMI_ANNUAL ";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += " cast ('0' as int) as PERIODS, ";

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += "I.\"MONTH\" as PERIODS, ";
            groupBy += ", I.\"MONTH\" ";
        }

        // To handle the scenario where any discount is not selected in program selection lookup
        String customSql = "";
        if (projSelDTO.getCurrentOrPrior().equalsIgnoreCase(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
        
             customSql += " from @PERIOD I join (select distinct pf.item_master_Sid,pf.period_Sid,EXFACTORY_ACTUAL_SALES, "
                     +" EXFACTORY_FORECAST_SALES ,DEMAND_ACTUAL_SALES,DEMAND_FORECAST_SALES,INVENTORY_ACTUAL_SALES,INVENTORY_FORECAST_SALES from #SELECTED_HIERARCHY_NO sh join #TEMP_CCP tc on tc.CCP_DETAILS_SID=sh.CCP_DETAILS_SID "
                     +" join "+ salesTable + "pf on pf.item_master_Sid=tc.ITEM_MASTER_SID join period p on p.PERIOD_SID=pf.period_sid) pf on i.PERIOD_SID=pf.period_Sid ";
        
        
        } else {
            customSql += " from @PERIOD I join (select distinct pf.item_master_Sid,pf.period_Sid,EXFACTORY_ACTUAL_SALES, "
                     +" EXFACTORY_FORECAST_SALES ,DEMAND_ACTUAL_SALES,DEMAND_FORECAST_SALES,INVENTORY_ACTUAL_SALES,INVENTORY_FORECAST_SALES from #SELECTED_HIERARCHY_NO sh join #TEMP_CCP tc on tc.CCP_DETAILS_SID=sh.CCP_DETAILS_SID "
                     +" join "+ salesTable + "pf on pf.item_master_Sid=tc.ITEM_MASTER_SID join period p on p.PERIOD_SID=pf.period_sid) pf on i.PERIOD_SID=pf.period_Sid ";
            
        }

        customSql += " group by  " + groupBy;

        String futureQuery = selectClause
                + " sum(EXFACTORY_ACTUAL_SALES) as EXFACTORY_ACTUAL_SALES "
                + " ,sum (COALESCE(EXFACTORY_FORECAST_SALES,EXFACTORY_ACTUAL_SALES)) AS EXFACTORY_FORECAST_SALES "
               
                + " ,sum(DEMAND_ACTUAL_SALES) AS DEMAND_ACTUAL_SALES "
                + " ,sum(COALESCE(DEMAND_FORECAST_SALES,DEMAND_ACTUAL_SALES)) AS DEMAND_FORECAST_SALES "
                + " ,sum(INVENTORY_ACTUAL_SALES) AS INVENTORY_ACTUAL_SALES "
                + " ,sum(COALESCE(INVENTORY_FORECAST_SALES,INVENTORY_ACTUAL_SALES)) AS INVENTORY_FORECAST_SALES "
                + customSql;

        return futureQuery;
    }
    
     
     
     public String getProperFileDataDiscount(PVSelectionDTO projSelDTO) {
        boolean viewFlag=Boolean.FALSE;
        String mainSalesTable = " #PRODUCT_FILE_DATA ";
        String tempSalesTable = " ST_PRODUCT_FILE ";

        String salesTable = StringUtils.EMPTY;
        String salesTable1 = StringUtils.EMPTY;
        String salesTable2 = StringUtils.EMPTY;

        if (!projSelDTO.isIsPrior() && !viewFlag) {
            salesTable = tempSalesTable;
        } else {
            salesTable = mainSalesTable;
        }
        String selectClause = " select ";
        String groupBy = " I.\"YEAR\"  ";
        selectClause += "I.\"YEAR\" as YEARS, ";
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER as PERIODS, ";
            groupBy += ", I.QUARTER ";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, ";
            groupBy += ", I.SEMI_ANNUAL ";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += " cast ('0' as int) as PERIODS, ";

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += "I.\"MONTH\" as PERIODS, ";
            groupBy += ", I.\"MONTH\" ";
        }
        
       

        // To handle the scenario where any discount is not selected in program selection lookup
        String customSql = "";
        if (projSelDTO.getCurrentOrPrior().equalsIgnoreCase(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
        
             customSql += "  , PRICE_GROUP_TYPE as DISCOUNTS "
                     + " from @PERIOD I join "
                     + "(\n" +
"                     SELECT DISTINCT pf.item_master_Sid\n " +
"                           ,pf.period_Sid\n" +
"                           ,EXFACTORY_ACTUAL_SALES\n" +
"                           ,EXFACTORY_FORECAST_SALES\n" +
"                           ,rs.PRICE_GROUP_TYPE\n" +
"                     FROM (SELECT distinct hierarchy_no,RS_CONTRACT_SID,ITEM_MASTER_SID,PRICE_GROUP_TYPE FROM #rs_combination) rs "
                     +" join "+ salesTable + "pf on pf.item_master_Sid = rs.ITEM_MASTER_SID join period p on p.PERIOD_SID=pf.period_sid) pf on i.PERIOD_SID=pf.period_Sid ";
             
              groupBy += ", PRICE_GROUP_TYPE  ";
        
        
        } else {
            customSql += "  , PF.DISCOUNTS AS DISCOUNTS  "
                   + " from PERIOD I join ("
                    + "  SELECT DISTINCT pf.item_master_Sid\n" +
"                           ,pf.period_Sid\n" +
"                           ,EXFACTORY_ACTUAL_SALES\n" +
"                           ,EXFACTORY_FORECAST_SALES\n" +
"                           ,TC.PRICE_GROUP_TYPE AS DISCOUNTS \n" +
"                     FROM (select  distinct hierarchy_no,item_master_Sid,RS_CONTRACT_SID,PRICE_GROUP_TYPE  from #rs_combination) TC "
                     +" join "+ salesTable + "pf on pf.item_master_Sid=tc.ITEM_MASTER_SID join period p on p.PERIOD_SID=pf.period_sid) pf on i.PERIOD_SID=pf.period_Sid ";
            
             groupBy += ",PF.DISCOUNTS  ";
            
        }

        customSql += " group by  " + groupBy;

        String futureQuery = selectClause
                + " sum(EXFACTORY_ACTUAL_SALES) as EXFACTORY_ACTUAL_SALES "
                + " ,sum (COALESCE(EXFACTORY_FORECAST_SALES,EXFACTORY_ACTUAL_SALES)) AS EXFACTORY_FORECAST_SALES "
                
                + customSql;

        return futureQuery;
    }
     
     
     
     public String getProperFileDataDiscount1(PVSelectionDTO projSelDTO) {
        boolean viewFlag=Boolean.FALSE;
        String mainSalesTable = " #PRODUCT_FILE_DATA ";
        String tempSalesTable = " ST_PRODUCT_FILE ";

        String salesTable = StringUtils.EMPTY;
        String salesTable1 = StringUtils.EMPTY;
        String salesTable2 = StringUtils.EMPTY;

        if (!projSelDTO.isIsPrior() && !viewFlag) {
            salesTable = tempSalesTable;
        } else {
            salesTable = mainSalesTable;
        }
        String selectClause = " select ";
        String groupBy = " I.\"YEAR\"  ";
        selectClause += "I.\"YEAR\" as YEARS, ";
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER as PERIODS, ";
            groupBy += ", I.QUARTER ";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, ";
            groupBy += ", I.SEMI_ANNUAL ";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += " cast ('0' as int) as PERIODS, ";

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += "I.\"MONTH\" as PERIODS, ";
            groupBy += ", I.\"MONTH\" ";
        }
        
        

        // To handle the scenario where any discount is not selected in program selection lookup
        String customSql = "";
        if (projSelDTO.getCurrentOrPrior().equalsIgnoreCase(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
        
             customSql += " , PRICE_GROUP_TYPE as DISCOUNTS "
                     + " from @PERIOD I join "
                     + "(\n" +
"                     SELECT DISTINCT pf.item_master_Sid\n" +
"                           ,pf.period_Sid\n" +
"                           , EXFACTORY_ACTUAL_SALES\n" +
"                           ,EXFACTORY_FORECAST_SALES\n" +
"                           ,PRICE_GROUP_TYPE\n" +
"                     FROM (SELECT distinct RS_CONTRACT_SID,ITEM_MASTER_SID,PRICE_GROUP_TYPE FROM #PPA_RS_COMBINATION) disc "
                     +" join "+ salesTable + "pf on pf.item_master_Sid = disc.ITEM_MASTER_SID join period p on p.PERIOD_SID=pf.period_sid) pf on i.PERIOD_SID=pf.period_Sid ";
             
             groupBy += ",PRICE_GROUP_TYPE  ";
        
        
        } else {
            customSql += "  , PF.PRICE_GROUP_TYPE AS DISCOUNTS "
                    +" from @PERIOD I join ("
                    + "  SELECT DISTINCT pf.item_master_Sid\n" +
"                           ,pf.period_Sid\n" +
"                           , EXFACTORY_ACTUAL_SALES\n" +
"                           ,EXFACTORY_FORECAST_SALES\n" +
"                           ,rs.PRICE_GROUP_TYPE \n" +
"                     FROM (select  distinct hierarchy_no,item_master_Sid,RS_CONTRACT_SID,PRICE_GROUP_TYPE  from #rs_combination) rs "
                     +" join "+ salesTable + "pf on pf.item_master_Sid=rs.ITEM_MASTER_SID join period p on p.PERIOD_SID=pf.period_sid) pf on i.PERIOD_SID=pf.period_Sid ";
            
            groupBy += ",PRICE_GROUP_TYPE  ";
            
        }

        customSql += " group by  " + groupBy;

        String futureQuery = selectClause
                + " sum(EXFACTORY_ACTUAL_SALES) as EXFACTORY_ACTUAL_SALES "
                + " ,sum (COALESCE(EXFACTORY_FORECAST_SALES,EXFACTORY_ACTUAL_SALES)) AS EXFACTORY_FORECAST_SALES "
               
                + customSql;

        return futureQuery;
    }

    public String getPPAProjectionResultsQuery(PVSelectionDTO projSelDTO, Boolean flag) {
         boolean viewFlag=Boolean.FALSE;
        String mainSalesTable = " NM_PPA_PROJECTION ";
        String mainSalesTable1 = " NM_ACTUAL_PPA ";
        String tempSalesTable = " ST_NM_PPA_PROJECTION ";
        String tempSalesTable1 = " ST_NM_ACTUAL_PPA ";
        String projTableSid = " CCP_DETAILS_SID ";
        String rsCondition = flag ? ", CASE WHEN @PROGRAM_TYPE='PROGRAM' THEN R.RS_NAME ELSE 'Price Protection' END RS_NAME "
               +("Program Category".equalsIgnoreCase(projSelDTO.getDiscountLevel())?StringUtils.EMPTY:"    ,R.RS_CONTRACT_SID  "): StringUtils.EMPTY+("Program Category".equalsIgnoreCase(projSelDTO.getDiscountLevel())?StringUtils.EMPTY:"    ,A1.RS_CONTRACT_SID  ");
        String rsCondition1 = flag ? ", AA.RS_NAME"
               +("Program Category".equalsIgnoreCase(projSelDTO.getDiscountLevel())?StringUtils.EMPTY:"    ,RS_CONTRACT_SID  ") : StringUtils.EMPTY+ ("Program Category".equalsIgnoreCase(projSelDTO.getDiscountLevel())?StringUtils.EMPTY:"    ,RS_CONTRACT_SID  ") ;
        String rsJoin = flag ? " INNER JOIN RS_CONTRACT R ON A1.RS_CONTRACT_SID = R.RS_CONTRACT_SID " : StringUtils.EMPTY;
        String salesTable = StringUtils.EMPTY;
        String salesTable1 = StringUtils.EMPTY;

        if (!projSelDTO.isIsPrior() && !viewFlag) {
            salesTable = tempSalesTable;
            salesTable1 = tempSalesTable1;
            
        } else {
            salesTable = mainSalesTable;
            salesTable1 = mainSalesTable1;
            projTableSid=" PROJECTION_DETAILS_SID ";
        }
        String selectClause = " select ";
        selectClause += "I.\"YEAR\" as YEARS, ";
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER as PERIODS, ";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, ";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += " cast ('0' as int) as PERIODS, ";

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += "I.\"MONTH\" as PERIODS, ";
        }

        // To handle the scenario where any discount is not selected in program selection lookup
        String customSql = "";
        if (projSelDTO.getCurrentOrPrior().equalsIgnoreCase(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            customSql += "      JOIN       PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID = A1.PROJECTION_DETAILS_SID\n"
                    + "                                                              AND PD.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n";
        } else {
            customSql += " INNER JOIN #TEMP_CCP T ON T.CCP_DETAILS_SID = A1.CCP_DETAILS_SID\n"
                    +"                      INNER JOIN @CCP CC ON CC.CCP_DETAILS_SID = T.CCP_DETAILS_SID\n";
        }
        customSql +="                           INNER JOIN @PERIOD I ON I.PERIOD_SID = A1.PERIOD_SID \n" ;
        customSql +="                           LEFT JOIN " + salesTable1 + " A2 ON  A2."+projTableSid+ "= A1."+projTableSid+ "AND A2.RS_CONTRACT_SID = A1.RS_CONTRACT_SID AND A1.PERIOD_SID = A2.PERIOD_SID \n" + rsJoin + "   ";
        if (projSelDTO.getCurrentOrPrior().equalsIgnoreCase(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
        } else {
            customSql += " JOIN NM_SALES_PROJECTION ns on ns.PROJECTION_DETAILS_SID=A1.PROJECTION_DETAILS_SID\n"
                    + "			and ns.PERIOD_SID=A1.PERIOD_SID";
        }
        customSql += "  )AA"
                + " group by  YEARS,PERIODS " + rsCondition1;

        String futureQuery = selectClause
                + " A1.PROJECTION_DISCOUNT_DOLLAR "
                + " ,A1.PROJECTION_RATE \n"
                + " ,A2.ACTUAL_DISCOUNT_DOLLAR AS PPA_ACTUAL_AMOUNT \n"
                + rsCondition + "\n"
                + " from "
                + salesTable + " A1 " + customSql;
        return futureQuery;
    }
    
    
    public String getPPAProjectionResultsQueryForPeriod(PVSelectionDTO projSelDTO, Boolean flag) {
         boolean viewFlag=Boolean.FALSE;
        String mainSalesTable = " NM_PPA_PROJECTION ";
        String mainSalesTable1 = " NM_ACTUAL_PPA ";
        String tempSalesTable = " ST_NM_PPA_PROJECTION ";
        String tempSalesTable1 = " ST_NM_ACTUAL_PPA ";
        String tempSalesprojectionmaster = " ST_NM_PPA_PROJECTION_MASTER ";
        String projectionmastertable = StringUtils.EMPTY;
        String projTableSid = " CCP_DETAILS_SID ";
        String rsCondition = flag ? ", CASE WHEN @PROGRAM_TYPE='PROGRAM' THEN R.RS_NAME ELSE 'Price Protection' END RS_NAME "
               +("Program Category".equalsIgnoreCase(projSelDTO.getDiscountLevel())?StringUtils.EMPTY:"    ,R.RS_CONTRACT_SID  "): StringUtils.EMPTY+("Program Category".equalsIgnoreCase(projSelDTO.getDiscountLevel())?StringUtils.EMPTY:"    ,A1.RS_CONTRACT_SID  ");
        String rsCondition1 = flag ? ", AA.RS_NAME"
               +("Program Category".equalsIgnoreCase(projSelDTO.getDiscountLevel())?StringUtils.EMPTY:"    ,RS_CONTRACT_SID  ") : StringUtils.EMPTY+ ("Program Category".equalsIgnoreCase(projSelDTO.getDiscountLevel())?StringUtils.EMPTY:"    ,RS_CONTRACT_SID  ") ;
        String rsJoin = flag ? " INNER JOIN RS_CONTRACT R ON A1.RS_CONTRACT_SID = R.RS_CONTRACT_SID " : StringUtils.EMPTY;
        String salesTable = StringUtils.EMPTY;
        String salesTable1 = StringUtils.EMPTY;

        if (!projSelDTO.isIsPrior() && !viewFlag) {
            salesTable = tempSalesTable;
            salesTable1 = tempSalesTable1;
            projectionmastertable = tempSalesprojectionmaster;
            
        } else {
            salesTable = mainSalesTable;
            salesTable1 = mainSalesTable1;
            projTableSid=" PROJECTION_DETAILS_SID ";
        }
        String selectClause = " select ";
        selectClause += "I.\"YEAR\" as YEARS, ";
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER as PERIODS, ";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, ";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += " cast ('0' as int) as PERIODS, ";

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += "I.\"MONTH\" as PERIODS, ";
        }

        // To handle the scenario where any discount is not selected in program selection lookup
        String customSql = "";
        if (projSelDTO.getCurrentOrPrior().equalsIgnoreCase(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
//            customSql += "      JOIN       PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID = A1.PROJECTION_DETAILS_SID\n"
//                    + "                                                              AND PD.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n";
            
            
            customSql += " PROJECTION_DETAILS PD JOIN  @PERIOD I ON  PD.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n" + "	"
                    + " join #SELECTED_HIERARCHY_NO sn on sn.CCP_DETAILS_SID=pd.CCP_DETAILS_SID "
                    + "	  JOIN NM_PPA_PROJECTION_MASTER A3 ON A3.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n" +
"                 LEFT JOIN NM_PPA_PROJECTION A1\n" +
"                ON PD.PROJECTION_DETAILS_SID = A1.PROJECTION_DETAILS_SID\n" +
"			   AND A1.RS_CONTRACT_SID = A3.RS_CONTRACT_SID\n" +
"                       AND I.PERIOD_SID = A1.PERIOD_SID\n" +
"                      LEFT JOIN NM_ACTUAL_PPA A2\n" +
"                             ON A2. PROJECTION_DETAILS_SID = PD. PROJECTION_DETAILS_SID\n" +
"                               AND A2.RS_CONTRACT_SID = A3.RS_CONTRACT_SID\n" +
"                          AND A1.PERIOD_SID = A2.PERIOD_SID\n" ;
        } else {
            
        customSql += "    #TEMP_CCP T CROSS "
			+"			JOIN @PERIOD I "
			+"			INNER JOIN #SELECTED_HIERARCHY_NO CC ON "
			+"				CC.CCP_DETAILS_SID = T.CCP_DETAILS_SID "
			+"			JOIN "  +projectionmastertable+ " A3 ON "
			+"				A3. CCP_DETAILS_SID = T. CCP_DETAILS_SID "
			+"			LEFT JOIN " +tempSalesTable+ " A1 ON "
			+"				T.CCP_DETAILS_SID = A1.CCP_DETAILS_SID "
			+"				AND A3.RS_CONTRACT_SID = A1.RS_CONTRACT_SID "
			+"				AND I.PERIOD_SID = A1.PERIOD_SID "
			+"			LEFT JOIN " +salesTable1+ " A2 ON "
			+"				A2. CCP_DETAILS_SID = T. CCP_DETAILS_SID "
			+"				AND A2.RS_CONTRACT_SID = A3.RS_CONTRACT_SID "
			+"				AND I.PERIOD_SID = A2.PERIOD_SID INNER JOIN RS_CONTRACT R ON a3.RS_CONTRACT_SID = R.RS_CONTRACT_SID";
            
        }
        if (projSelDTO.getCurrentOrPrior().equalsIgnoreCase(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
        } else {
            customSql += " LEFT JOIN NM_SALES_PROJECTION ns on ns.PROJECTION_DETAILS_SID=A3.PROJECTION_DETAILS_SID\n"
                    + "			and ns.PERIOD_SID=I.PERIOD_SID  INNER JOIN RS_CONTRACT R ON A3.RS_CONTRACT_SID = R.RS_CONTRACT_SID";
        }
        customSql += "  )AA"
                + " group by  YEARS,PERIODS " + rsCondition1;

        String futureQuery = selectClause
                + " A1.PROJECTION_DISCOUNT_DOLLAR "
                + " ,A1.PROJECTION_RATE \n"
                + " ,A2.ACTUAL_DISCOUNT_DOLLAR AS PPA_ACTUAL_AMOUNT \n"
                + rsCondition + "\n"
                + " from "
//                + salesTable + " A1 " 
                + customSql;
        return futureQuery;
    }

    public String getSalesQuery(final String salesInnerJoin) {
        String query = "SELECT YEARS\n"
                + " , PERIODS\n"
                
                + " , Sum(ACTUAL_SALES ) AS ACTUAL_SALES \n"
                + "  , Sum(ACTUAL_UNITS ) AS ACTUAL_UNITS \n"
                
                + " , Sum(projection_sales) AS SALES_PROJECTION_SALES\n"
                + "  , Sum(projection_units) AS PROJECTION_UNITS\n"
                + " , (Isnull(Sum(PROJECTION_UNITS), 0) * Isnull(Avg(nullif(ITEM_PRICE,0)), 0)) AS COGS_PROJECTED\n"
                + " , (Isnull(Sum(ACTUAL_UNITS ), 0) * Isnull(Avg(nullif(ITEM_PRICE,0)), 0)) AS COGS_ACTUALS\n"
                + " , SUM( EXFACTORY_ACTUAL_SALES ) AS EXFACTORY_ACTUAL_SALES\n"
                + " , SUM( EXFACTORY_FORECAST_SALES  ) AS EXFACTORY_FORECAST_SALES\n"
                + " FROM ( " + salesInnerJoin;
        return query;
    }
    
    public String getSalesQueryDiscount(final String salesInnerJoin) {
        String query = "SELECT YEARS\n"
                + " , PERIODS\n"
                + " , A.DISCOUNTS \n"
                + " , Sum(ACTUAL_SALES ) AS ACTUAL_SALES \n"
                + "  , Sum(ACTUAL_UNITS ) AS ACTUAL_UNITS \n"
                + " , Sum(projection_sales) AS SALES_PROJECTION_SALES\n"
                + "  , Sum(projection_units) AS PROJECTION_UNITS\n"
                + " FROM ( " + salesInnerJoin;
        return query;
    }
    
     public String getSalesQueryDiscount1(final String salesInnerJoin) {
        String query = "SELECT YEARS\n"
                + " , PERIODS\n"
                + " , DISCOUNTS \n"
                + " , Sum(ACTUAL_SALES ) AS ACTUAL_SALES \n"
                + "  , Sum(ACTUAL_UNITS ) AS ACTUAL_UNITS \n"
                + " , Sum(projection_sales) AS SALES_PROJECTION_SALES\n"
                + "  , Sum(projection_units) AS PROJECTION_UNITS\n"
                + " FROM ( " + salesInnerJoin;
        return query;
    }

    public String getPPAQuery(final String salesInnerJoin, Boolean flag,boolean discountLevel) {
        String query = "SELECT AA.YEARS\n"
                + " , AA.PERIODS\n";
        query += flag ? " , RS_NAME AS PPA_DISCOUNT \n" : StringUtils.EMPTY + "\n";
        query += " , SUM(AA.PROJECTION_DISCOUNT_DOLLAR) AS SALES_PPA \n"
                + "  ,  SUM(AA.PROJECTION_RATE) AS SALES_RPU, SUM( PPA_ACTUAL_AMOUNT ) AS PPA_ACTUAL_AMOUNT "+(discountLevel?StringUtils.EMPTY:"    ,RS_CONTRACT_SID  ") 
                + " FROM ( " + salesInnerJoin;
        return query;
    }

    public String getCCPWhereConditionQuery(String projectionDetails, String CCP) {
        String ccpWhereCond = " and " + CCP + ".CCP_DETAILS_SID=" + projectionDetails + ".CCP_DETAILS_SID ";
        return ccpWhereCond;
    }

    public String getComparisonSearch(String workflowStatus, String marketType, String brand,
            String projName, String contHldr, String ndcNo, String ndcName, String desc, String contract,
            String from, String to, String notSearchProjId) {
        String QUOTES = "'";
        String ASTERIK = "*";
        String PERCENT = Constant.PERCENT;
        String marketTypeVal;
        String brandVal;
        String projNameVal;
        String contHldrVal;
        String ndcNoVal;
        String ndcNameVal;
        String descVal;
        String contractVal;
        boolean isProjectionStatus = false;
        try {
            StringBuilder customSql = new StringBuilder(StringUtils.EMPTY);

            if (workflowStatus.equals(Constant.SAVED)) {
                isProjectionStatus = true;
            }
            if (isProjectionStatus) {
                customSql = new StringBuilder(CustomSQLUtil.get("getProjectionLists"));
            } else {
                customSql = new StringBuilder(CustomSQLUtil.get("getWorkFlowLists"));
            }

            if (marketType == null || marketType.equals(StringUtils.EMPTY)) {
                marketTypeVal = "'%'";
            } else {
                marketTypeVal = marketType.replace(ASTERIK, PERCENT);
                marketTypeVal = QUOTES + marketTypeVal + QUOTES;
            }
            customSql.append("( HT.list_name = 'CONTRACT_TYPE' AND HT.DESCRIPTION LIKE " + marketTypeVal + ")");
            if (brand == null || brand.equals(StringUtils.EMPTY)) {
                brandVal = "'%'";
            } else {
                brandVal = brand.replace(ASTERIK, PERCENT);
                brandVal = QUOTES + brandVal + QUOTES;
            }
            customSql.append("  AND (BM.BRAND_NAME LIKE " + brandVal + " or BM.BRAND_NAME is null)");
            if (projName == null || projName.equals(StringUtils.EMPTY)) {
                projNameVal = "'%'";
            } else {
                projNameVal = projName.replace(ASTERIK, PERCENT);
                projNameVal = QUOTES + projNameVal + QUOTES;
            }
            customSql.append("AND (PM.PROJECTION_NAME LIKE " + projNameVal + " or PM.PROJECTION_NAME is null)");
            if (contHldr == null || contHldr.equals(StringUtils.EMPTY)) {
                contHldrVal = "'%'";
            } else {
                contHldrVal = contHldr.replace(ASTERIK, PERCENT);
                contHldrVal = QUOTES + contHldrVal + QUOTES;
            }
            customSql.append("AND (C.CONTRACT_NO LIKE " + contHldrVal + " or C.CONTRACT_NO is null)");
            if (ndcName == null || ndcName.equals(StringUtils.EMPTY)) {
                ndcNameVal = "'%'";
            } else {
                ndcNameVal = ndcName.replace(ASTERIK, PERCENT);
                ndcNameVal = QUOTES + ndcNameVal + QUOTES;
            }
            customSql.append("AND (IM.ITEM_NAME LIKE " + ndcNameVal + " or IM.ITEM_NAME is null)");
            if (ndcNo == null || ndcNo.equals(StringUtils.EMPTY)) {
                ndcNoVal = "'%'";
            } else {
                ndcNoVal = ndcNo.replace(ASTERIK, PERCENT);
                ndcNoVal = QUOTES + ndcNoVal + QUOTES;
            }
            customSql.append("AND (IM.ITEM_NO LIKE " + ndcNoVal + "or IM.ITEM_NO is null)");
            if (contract == null || contract.equals(StringUtils.EMPTY)) {
                contractVal = "'%'";
            } else {
                contractVal = contract.replace(ASTERIK, PERCENT);
                contractVal = QUOTES + contractVal + QUOTES;
            }
            customSql.append("AND (CM.COMPANY_NO LIKE " + contractVal + "or CM.COMPANY_NO is null)");
            if (desc == null || desc.equals(StringUtils.EMPTY)) {
                descVal = "'%'";
            } else {
                descVal = desc.replace(ASTERIK, PERCENT);
                descVal = QUOTES + descVal + QUOTES;
            }
            customSql.append("AND (PM.PROJECTION_NAME LIKE " + descVal + "or PM.PROJECTION_NAME is null)");
            if (isProjectionStatus) {
                customSql.append("and pm.is_approved not in ('Y','C','A','R')");
            } else {
                customSql.append("AND HT1.list_name = 'WorkFlowStatus' and ht1.description = " + QUOTES + workflowStatus + QUOTES);
            }
            if (from != null && !Constant.NULL.equals(from) && !StringUtils.isEmpty(from)
                    && to != null && !Constant.NULL.equals(to) && !StringUtils.isEmpty(to)) {
                SimpleDateFormat format2 = new SimpleDateFormat(DATE_FORMAT.getConstant());
                customSql.append(" AND PM.CREATED_DATE BETWEEN '");
                customSql.append(format2.format(format2.parse(from)));
                customSql.append("' AND '");
                customSql.append(format2.format(format2.parse(to)));
                customSql.append("' ");
            }
            customSql.append("AND PM.PROJECTION_MASTER_SID NOT IN (" + notSearchProjId + ")");
            customSql.append("AND PM.FORECASTING_TYPE='Non Mandated'");

            customSql.append(" group by  pm.projection_name,pm.projection_description,ht.description ,cm.company_no  ,c.contract_no  ,pm.projection_master_sid,PM.created_date,PM.created_by ");

            return customSql.toString();
        } catch (Exception e) {
            LOGGER.error(e + " at PvQueryUtils -> getComparisonSearch");
            return null;
        }
    }

    /**
     * Logic to make persistency of already added projection in the selected
     * table - comparison look up
     *
     * @param projId
     * @return query -String
     */
    public String getPVComparisonProjections(final List<Integer> projId) {
        try {
            String customSql = CustomSQLUtil.get("getProjectionLists");
            if (projId != null && !projId.isEmpty()) {
                customSql += (" PM.PROJECTION_MASTER_SID IN (" + CommonUtils.CollectionToString(projId, false) + ")");
            } else {
                customSql += (" PM.PROJECTION_MASTER_SID IN ('')");
            }
            customSql += (" group by  pm.projection_name,pm.projection_description,ht.description ,pm.projection_master_sid,PM.created_date,PM.created_by ");

            return customSql;
        } catch (Exception e) {
            LOGGER.error(e + " at PvQueryUtils -> getComparisonSearch");
            return null;
        }
    }

    public String getProjectionResultsDiscountsQuery(PVSelectionDTO projSelDTO, String orderBy) {
        boolean viewFlag=Constant.VIEW.equals(projSelDTO.getSessionDTO().getAction());
        String mainMasterTable = "  NM_DISCOUNT_PROJ_MASTER ";
        String tempMasterTable = " ST_NM_DISCOUNT_PROJ_MASTER ";
        String mainTable = " NM_DISCOUNT_PROJECTION ";
        String mainTable1 = " NM_ACTUAL_DISCOUNT ";
        String tempTable = " ST_NM_DISCOUNT_PROJECTION ";
        String tempTable1 = " ST_NM_ACTUAL_DISCOUNT ";
        String projTableSid = " CCP_DETAILS_SID ";
        String masterTable = StringUtils.EMPTY;
        String table = StringUtils.EMPTY;
        String table1 = StringUtils.EMPTY;
        String selectClause = " select ";
        String whereClause = StringUtils.EMPTY;
        String groupBy = "  I.\"YEAR\"";
        if (!projSelDTO.isIsPrior() && !viewFlag) {
            masterTable = tempMasterTable;
            table = tempTable;
            table1 = tempTable1;
        } else {
            masterTable = mainMasterTable;
            table = mainTable;
            table1 = mainTable1;
            projTableSid=" PROJECTION_DETAILS_SID ";
        }
        String customQuery = StringUtils.EMPTY;
        selectClause += "I.\"YEAR\" as YEARS , ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += "I.\"MONTH\" as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.\"MONTH\"";
        }


        // To handle the scenario where any discount is not selected in program selection lookup
        String discountTypeColumnName = "'Total discounts' as DISCOUNTS";

        if (projSelDTO.getDiscountLevel().equals("Program Category")) {
            if (projSelDTO.getDiscountNameList() != null && !projSelDTO.getDiscountNameList().isEmpty()) {
                if (!projSelDTO.isIsTotal()) {
                    discountTypeColumnName = " B.PRICE_GROUP_TYPE AS DISCOUNTS";
                    groupBy += ", " + " B.PRICE_GROUP_TYPE  "  ;
                }
//                if (!projSelDTO.isIsPrior()) {
                    whereClause += " WHERE B.RS_CONTRACT_SID in (" + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false) + ")";
//                }
            }
        } else {
            if (projSelDTO.getDiscountNoList() != null && !projSelDTO.getDiscountNoList().isEmpty()) {
                if (!projSelDTO.isIsTotal()) {
                    discountTypeColumnName = " J.RS_NAME AS DISCOUNTS";
                    groupBy += ", " + " J.RS_NAME " + ", " + " J.RS_CONTRACT_SID " ;
                }
//                if (!projSelDTO.isIsPrior()) {
                    whereClause += " WHERE B.RS_CONTRACT_SID in (" + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false) + ")";
//                }
            }
        }

        selectClause += discountTypeColumnName + ", ";

        String futureQuery = selectClause + "sum(A.PROJECTION_SALES) as PROJECTION_SALES, Sum(A.PROJECTION_RPU) AS RPU, ISNULL(SUM(C.ACTUAL_SALES ), 0) AS ACTUAL_SALES "
                 +("Program Category".equalsIgnoreCase(projSelDTO.getDiscountLevel())?StringUtils.EMPTY:"    ,J.RS_CONTRACT_SID  ") + " from \n";

        if (projSelDTO.getCurrentOrPrior().equalsIgnoreCase(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
            
             futureQuery += "#TEMP_CCP T  CROSS\n" +
"				JOIN @PERIOD I   JOIN #SELECTED_HIERARCHY_NO CCP ON\n" +
"					T.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID \n"
                    + "                   JOIN    " + masterTable + " B ON T.CCP_DETAILS_SID=B.CCP_DETAILS_SID \n "
                    + "                   JOIN   " + table + " A ON A.CCP_DETAILS_SID = T.CCP_DETAILS_SID  AND I.PERIOD_SID = A.PERIOD_SID\n" +
"					AND A.RS_CONTRACT_SID = B.RS_CONTRACT_SID\n"
                    
                    
                    + "                   LEFT  JOIN   " + table1 + " C ON A."+projTableSid+" = C."+projTableSid+"\n"
                    + "                   AND A.RS_CONTRACT_SID = C.RS_CONTRACT_SID\n "                    
                    + "                   AND I.PERIOD_SID  = C.PERIOD_SID \n "     ;               

        } else {
             futureQuery += " PROJECTION_DETAILS PD JOIN @PERIOD I ON PD.PROJECTION_MASTER_SID = "+ projSelDTO.getProjectionId() +"\n"
                     
          +"  JOIN #SELECTED_HIERARCHY_NO CCP ON\n" +
"					PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID \n"
            
            
            
                    + "                      JOIN   " + masterTable + " B ON PD.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID\n"
                    +"  LEFT JOIN " +table+ " A ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID AND A.RS_CONTRACT_SID = B.RS_CONTRACT_SID AND I.PERIOD_SID = A.PERIOD_SID"
                    
                    +"  LEFT JOIN " +table1+ " C ON B.PROJECTION_DETAILS_SID = C.PROJECTION_DETAILS_SID AND B.RS_CONTRACT_SID = C.RS_CONTRACT_SID AND I.PERIOD_SID = C.PERIOD_SID";

        }
        if (!projSelDTO.isIsTotal()) {
            futureQuery += " JOIN RS_CONTRACT J ON J.RS_CONTRACT_SID = B.RS_CONTRACT_SID ";
//            futureQuery += " LEFT JOIN " + table1 + " C ON C."+projTableSid+" = A."+projTableSid+" AND A.RS_CONTRACT_SID = C.RS_CONTRACT_SID AND A.PERIOD_SID = C.PERIOD_SID ";
        }

        futureQuery += whereClause + " GROUP BY " + groupBy;
        customQuery = futureQuery;
        return customQuery;

    }
    
    
    
    public String getProjectionResultsDiscountsQueryDiscount(PVSelectionDTO projSelDTO, String orderBy) {
        boolean viewFlag=Constant.VIEW.equals(projSelDTO.getSessionDTO().getAction());
        String mainMasterTable = "  NM_DISCOUNT_PROJ_MASTER ";
        String tempMasterTable = " ST_NM_DISCOUNT_PROJ_MASTER ";
        String mainTable = " NM_DISCOUNT_PROJECTION ";
        String mainTable1 = " NM_ACTUAL_DISCOUNT ";
        String tempTable = " ST_NM_DISCOUNT_PROJECTION ";
        String tempTable1 = " ST_NM_ACTUAL_DISCOUNT ";
        String projTableSid = " CCP_DETAILS_SID ";
        String masterTable = StringUtils.EMPTY;
        String table = StringUtils.EMPTY;
        String table1 = StringUtils.EMPTY;
        String selectClause = " select ";
        String whereClause = StringUtils.EMPTY;
        String groupBy = "  I.\"YEAR\"";
        if (!projSelDTO.isIsPrior()) {
            masterTable = tempMasterTable;
            table = tempTable;
            table1 = tempTable1;
        } else {
            masterTable = mainMasterTable;
            table = mainTable;
            table1 = mainTable1;
            projTableSid=" PROJECTION_DETAILS_SID ";
        }
        String customQuery = StringUtils.EMPTY;
        selectClause += "I.\"YEAR\" as YEARS , ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += "I.\"MONTH\" as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.\"MONTH\"";
        }


        // To handle the scenario where any discount is not selected in program selection lookup
        String discountTypeColumnName = StringUtils.EMPTY;

        if (projSelDTO.getDiscountLevel().equals("Program Category")) {
            if (projSelDTO.getDiscountNoList() != null && !projSelDTO.getDiscountNoList().isEmpty()) {
                if (!projSelDTO.isIsTotal()) {
                    discountTypeColumnName = " B.PRICE_GROUP_TYPE AS DISCOUNTS";
                    groupBy += ", " + " B.PRICE_GROUP_TYPE  "  ;
                }
//                if (!projSelDTO.isIsPrior()) {
                    whereClause += " WHERE B.RS_CONTRACT_SID in (" + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false) + ")";
//                }
            }
        } else {
            if (projSelDTO.getDiscountNoList() != null && !projSelDTO.getDiscountNoList().isEmpty()) {
                if (!projSelDTO.isIsTotal()) {
                    discountTypeColumnName = " J.RS_NAME AS DISCOUNTS";
                    groupBy += ", " + " J.RS_NAME " + ", " + " J.RS_CONTRACT_SID " ;
                }
//                if (!projSelDTO.isIsPrior()) {
                    whereClause += " WHERE B.RS_CONTRACT_SID in (" + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false) + ")";
//                }
            }
        }

        selectClause += discountTypeColumnName + ", ";

        String futureQuery = selectClause + "sum(A.PROJECTION_SALES) as PROJECTION_SALES, Sum(A.PROJECTION_RPU) AS RPU, ISNULL(SUM(C.ACTUAL_SALES ), 0) AS ACTUAL_SALES "
                 +("Program Category".equalsIgnoreCase(projSelDTO.getDiscountLevel())?StringUtils.EMPTY:"    ,J.RS_CONTRACT_SID  ") + " from \n";

        if (projSelDTO.getCurrentOrPrior().equalsIgnoreCase(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
            
             futureQuery += "#TEMP_CCP T  CROSS\n" +
"				JOIN PERIOD I   JOIN #SELECTED_HIERARCHY_NO CCP ON\n" +
"					T.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID \n"
                    + "                   JOIN    " + masterTable + " B ON T.CCP_DETAILS_SID=B.CCP_DETAILS_SID \n "
                    + "                   JOIN   " + table + " A ON A.CCP_DETAILS_SID = T.CCP_DETAILS_SID  AND I.PERIOD_SID = A.PERIOD_SID\n" +
"					AND A.RS_CONTRACT_SID = B.RS_CONTRACT_SID\n"
                    
                    
                    + "                   LEFT  JOIN   " + table1 + " C ON A."+projTableSid+" = C."+projTableSid+"\n"
                    + "                   AND A.RS_CONTRACT_SID = C.RS_CONTRACT_SID\n "                    
                    + "                   AND I.PERIOD_SID  = C.PERIOD_SID \n "     ;               

        } else {
             futureQuery += " PROJECTION_DETAILS PD JOIN @PERIOD I ON PD.PROJECTION_MASTER_SID = "+ projSelDTO.getProjectionId() +"\n"
                     
          +    "                      JOIN   " + masterTable + " B ON PD.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID\n"
                    +"  LEFT JOIN " +table+ " A ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID AND A.RS_CONTRACT_SID = B.RS_CONTRACT_SID AND I.PERIOD_SID = A.PERIOD_SID"
                    
                    +"  LEFT JOIN " +table1+ " C ON B.PROJECTION_DETAILS_SID = C.PROJECTION_DETAILS_SID AND B.RS_CONTRACT_SID = C.RS_CONTRACT_SID AND I.PERIOD_SID = C.PERIOD_SID";

        }
        if (!projSelDTO.isIsTotal()) {
            futureQuery += " JOIN RS_CONTRACT J ON J.RS_CONTRACT_SID = B.RS_CONTRACT_SID ";
//            futureQuery += " LEFT JOIN " + table1 + " C ON C."+projTableSid+" = A."+projTableSid+" AND A.RS_CONTRACT_SID = C.RS_CONTRACT_SID AND A.PERIOD_SID = C.PERIOD_SID ";
        }

        futureQuery += whereClause + " GROUP BY " + groupBy;
        customQuery = futureQuery;
        return customQuery;

    }


    public List<String> getCommonSelectWhereOrderGroupByClause(String table1, String table2, int frequencyDivision, String where) {
        List<String> list = new ArrayList<String>();
        String orderBy = " YEARS, PERIODS";
        String groupBy = " " + table1 + ".YEARS";
        String selectClause = "Isnull(" + table1 + ".YEARS, " + table2 + ".YEARS) as YEARS, ";
        String finalWhereCond = " " + where + " " + table1 + ".YEARS=" + table2 + ".YEARS";
        groupBy += ", " + table1 + ".PERIODS";
        selectClause += "Isnull(" + table1 + ".PERIODS, " + table2 + ".PERIODS) as PERIODS,";
        finalWhereCond += " and " + table1 + ".PERIODS=" + table2 + ".PERIODS";
        list.add(selectClause);
        list.add(finalWhereCond);
        list.add(orderBy);
        list.add(groupBy);
        return list;
    }

    public String getPVMainDiscountPivotQuery(PVSelectionDTO projSelDTO) {
        projSelDTO.setIsTotal(false);
        String selectClauseDol = " select C.YEARS as YEARS, C.PERIODS AS PERIODS,C.DISCOUNTS AS DISCOUNTS,";
        String customQuery = StringUtils.EMPTY;
        String orderBy = "  YEARS,PERIODS ";
        selectClauseDol += "IsNull(C.PROJECTION_SALES, 0) AS C_DOL_VAL ";
        selectClauseDol += ",0  AS C_DOL_VAR ";
        selectClauseDol += ",0  AS C_DOL_PER ";

        selectClauseDol += ",IsNull(C.PROJECTION_RATE, 0) C_RATE_VAL";
        selectClauseDol += ",0  AS C_RATE_VAR ";
        selectClauseDol += ",0  AS C_RATE_PER ";

        selectClauseDol += ",IsNull(C.PROJECTED_RPU, 0) C_RPU_VAL";
        selectClauseDol += ",0  AS C_RPU_VAR ";
        selectClauseDol += ",0  AS C_RPU_PER ";
        
        selectClauseDol += ",c_disc_exfactory_current_var =0";
        selectClauseDol += ",c_disc_exfactory_current_per =0";
         selectClauseDol += ", Isnull( C.ACTUAL_SALES,0) AS C_DISCOUNT_PER_VAL , Isnull(C.ACTUAL_RATE,0) AS C_ACTUAL_RATE , Isnull( C.ACTUAL_RPU, 0) AS C_ACTUAL_RPU ,  ";
         selectClauseDol += " c.discount_of_exfactory_actuals , c.discount_of_exfactory_forecast,  ";
         selectClauseDol += "  C.ACTUAL_SALES -ISNULL(C.PROJECTION_SALES, 0) AS  C_DISC_AMOUNT_VAR, " ;
         selectClauseDol += " (C.ACTUAL_SALES -ISNULL(C.PROJECTION_SALES, 0))/NULLIF(ISNULL(C.PROJECTION_SALES, 0),0) AS  C_DISC_AMOUNT_PER_CNG, ";
         selectClauseDol += " C.ACTUAL_RATE -ISNULL(C.PROJECTION_RATE, 0) AS  C_DISC_RATE_PER_VAR, ";
         selectClauseDol += " (C.ACTUAL_RATE -ISNULL(C.PROJECTION_RATE, 0))/NULLIF(ISNULL(C.PROJECTION_RATE, 0),0) AS  C_DISC_RATE_PER_PER_CNG, ";
         selectClauseDol += " C.ACTUAL_RPU -ISNULL(C.PROJECTED_RPU, 0) AS  C_DISC_RPU_VAR, ";
         selectClauseDol += " (C.ACTUAL_RPU -ISNULL(C.PROJECTED_RPU, 0))/NULLIF(ISNULL(C.PROJECTION_SALES, 0),0) AS  C_DISC_RPU_PER_CNG ";
         
         selectClauseDol += " , C_disc_exfactory_actual_var =ISNULL(c.discount_of_exfactory_actuals,0)-ISNULL(c.discount_of_exfactory_forecast,0) ";
         selectClauseDol += " , C_disc_exfactory_actual_per =ISNULL((ISNULL(c.discount_of_exfactory_actuals,0)-ISNULL(c.discount_of_exfactory_forecast,0))/NULLIF(c.discount_of_exfactory_forecast,0),0) ";
                 for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            selectClauseDol += ", IsNull(P" + i + ".PROJECTION_SALES,0) AS P" + i + "_DOL_VALUE ";
            selectClauseDol += ", (IsNull(C.PROJECTION_SALES, 0)- IsNull(P" + i + ".PROJECTION_SALES, 0)) AS P" + i + "_DOL_VAR ";
            selectClauseDol += ", CASE WHEN P" + i + ".PROJECTION_SALES = 0 THEN 0\n"
                    + " ELSE (IsNull(C.PROJECTION_SALES, 0) - IsNull(P" + i + ".PROJECTION_SALES, 0) / P" + i + ".PROJECTION_SALES) \n"
                    + " END  AS P" + i + "_DOL_PER ";

            selectClauseDol += ", IsNull(P" + i + ".PROJECTION_RATE,0) AS P" + i + "_RATE_VAL ";
            selectClauseDol += ", (IsNull(C.PROJECTION_RATE, 0)- IsNull(P" + i + ".PROJECTION_RATE, 0)) AS P" + i + "_RATE_VAR ";
            selectClauseDol += ", CASE WHEN P" + i + ".PROJECTION_RATE = 0 THEN 0\n"
                    + " ELSE (IsNull(C.PROJECTION_RATE, 0) - IsNull(P" + i + ".PROJECTION_RATE, 0) / P" + i + ".PROJECTION_RATE) \n"
                    + " END  AS     P" + i + "_RATE_PER ";

            selectClauseDol += ", IsNull(P" + i + ".PROJECTED_RPU,0) AS P" + i + "_PROJECTED_RPU_VAL ";
            selectClauseDol += ", (IsNull(C.PROJECTED_RPU, 0)- IsNull(P" + i + ".PROJECTED_RPU, 0)) AS P" + i + "_PROJECTED_RPU_VAR ";
            selectClauseDol += ", CASE WHEN P" + i + ".PROJECTED_RPU = 0 THEN 0\n"
                    + " ELSE (IsNull(C.PROJECTED_RPU, 0) - IsNull(P" + i + ".PROJECTED_RPU, 0) / P" + i + ".PROJECTED_RPU) \n"
                    + " END  AS     P" + i + "_PROJECTED_RPU_PER ";
            
            selectClauseDol += ",P"+ i +".discount_of_exfactory_actuals";
            selectClauseDol += ",P"+ i +".discount_of_exfactory_forecast";
            
            selectClauseDol += " , P"+ i + "_disc_exfactory_current_var = ISNULL(c.discount_of_exfactory_forecast,0) - IsNull(P" + i + ".discount_of_exfactory_forecast,0) ";
            selectClauseDol += " , P"+ i + "_disc_exfactory_current_per = (ISNULL(c.discount_of_exfactory_forecast,0) - IsNull(P" + i + ".discount_of_exfactory_forecast,0)) / nullif(P" + i + ".discount_of_exfactory_forecast,0) ";
            
	  selectClauseDol += " , (C.ACTUAL_SALES -ISNULL(P" + i + ".PROJECTION_SALES, 0)) AS  P" + i +"_DISC_AMOUNT_VAR, "
	  + " (C.ACTUAL_SALES -ISNULL(P" + i + ".PROJECTION_SALES, 0))/NULLIF(ISNULL(P" + i + ".PROJECTION_SALES, 0),0) AS  P" + i +"_DISC_AMOUNT_PER_CNG, "
	  + " C.ACTUAL_RATE -ISNULL(P" + i + ".PROJECTION_RATE, 0) AS  P" + i +"_DISC_RATE_PER_VAR, "
	  + " (C.ACTUAL_RATE -ISNULL(P" + i + ".PROJECTION_RATE, 0))/NULLIF(ISNULL(P" + i + ".PROJECTION_RATE, 0),0) AS  P" + i +"_DISC_RATE_PER_PER_CNG, "
	  + " C.ACTUAL_RPU -ISNULL(P" + i + ".PROJECTED_RPU, 0) AS  P" + i +"_DISCOUNT_PER_VAR, "
	  + " (C.ACTUAL_RPU -ISNULL(P" + i + ".PROJECTED_RPU, 0))/NULLIF(ISNULL(P" + i + ".PROJECTION_SALES, 0),0) AS  P" + i +"_DISC_RPU_PER_CNG ";
          
          selectClauseDol += " , P"+ i + "_disc_exfactory_actual_var = ISNULL(c.discount_of_exfactory_actuals,0) - IsNull(P" + i + ".discount_of_exfactory_forecast,0) ";
          selectClauseDol += " , P"+ i + "_disc_exfactory_actual_per = (ISNULL(c.discount_of_exfactory_actuals,0) - IsNull(P" + i + ".discount_of_exfactory_forecast,0)) / nullif(P" + i + ".discount_of_exfactory_forecast,0) ";


        }
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
        projSelDTO.setIsPrior(false);
        customQuery += selectClauseDol + " from  \n(" + getProjectionResultsDiscountsPerQuery(projSelDTO) + ") C\n ";
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
        projSelDTO.setIsPrior(true);
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            projSelDTO.setProjectionId(projSelDTO.getProjIdList().get(i));
            customQuery += " LEFT JOIN (\n" + getProjectionResultsDiscountsPerQuery(projSelDTO) + "\n) " + Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY + i + " \n on C.DISCOUNTS=P" + i + ".DISCOUNTS \n "
                    + " AND C.YEARS=P" + i + ".YEARS \n "
                    + " and C.PERIODS=P" + i + ".PERIODS  ";
        }
        customQuery += " order by " + orderBy;
        projSelDTO.setIsPrior(false);
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        return customQuery;
    }

    public String getProjectionResultsDiscountsPerQuery(PVSelectionDTO projSelDTO) {
        String selectClause = " select ";
        String ppaSelectClause = selectClause;
        String customQuery = StringUtils.EMPTY;
        List<String> list = getCommonSelectWhereOrderGroupByClause("TODIS", "SALEPPA", projSelDTO.getFrequencyDivision(), "on");
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        finalWhereCond += " AND TODIS.DISCOUNTS = SALEPPA.DISCOUNTS ";
        list = getCommonSelectWhereOrderGroupByClause("PPA", "SALEPPA", projSelDTO.getFrequencyDivision(), "on");
        selectClause += "TODIS.DISCOUNTS, ";
        selectClause += "TODIS.PROJECTION_SALES AS PROJECTION_SALES, ";
        selectClause += " PROJECTION_RATE=Isnull(TODIS.PROJECTION_SALES / NULLIF(SALEPPA.SALES_PROJECTION_SALES, 0), 0)*100,";
        selectClause += " CASE WHEN SALEPPA.PROJECTION_UNITS = 0 THEN 0 ELSE (((Isnull(TODIS.PROJECTION_SALES, 0)) /SALEPPA.PROJECTION_UNITS) ) END AS PROJECTED_RPU , ";
        
        selectClause += " CASE WHEN @CURRENT_DTAE > DATEFROMPARTS(TODIS.YEARS,case when ISNULL(TODIS.PERIODS, SALEPPA.PERIODS)=0 then 01 else  ISNULL(TODIS.PERIODS, SALEPPA.PERIODS) end, 01) \n" +
        "  THEN TODIS.ACTUAL_SALES  ELSE NULL  END   AS ACTUAL_SALES, ";
      
        selectClause += " ACTUAL_RATE = CASE  WHEN @CURRENT_DTAE > DATEFROMPARTS(TODIS.YEARS,case when ISNULL(TODIS.PERIODS, SALEPPA.PERIODS)=0 then 01 \n" +
        " else  ISNULL(TODIS.PERIODS, SALEPPA.PERIODS) end, 01) THEN  ISNULL  (TODIS.ACTUAL_SALES / NULLIF(SALEPPA.ACTUAL_SALES, 0), 0) * 100 ELSE NULL  END, ";
        
        selectClause += "  CASE  WHEN @CURRENT_DTAE > DATEFROMPARTS(TODIS.YEARS, case when ISNULL(TODIS.PERIODS, SALEPPA.PERIODS)=0 then 01 else  ISNULL(TODIS.PERIODS, SALEPPA.PERIODS) end , 01) \n" +
        " THEN  CASE   WHEN SALEPPA.ACTUAL_UNITS = 0 THEN 0  ELSE(( ( ISNULL(TODIS.ACTUAL_SALES, 0) ) /  SALEPPA.ACTUAL_UNITS ))  END  ELSE NULL  END   AS ACTUAL_RPU,  ";
                
                
                
       selectClause += " discount_of_exfactory_actuals = ((CASE  WHEN @CURRENT_DTAE > DATEFROMPARTS(TODIS.YEARS, case when ISNULL(TODIS.PERIODS, SALEPPA.PERIODS)=0 then 01 else  ISNULL(TODIS.PERIODS, SALEPPA.PERIODS) end , 01) \n" +
        " THEN  TODIS.ACTUAL_SALES ELSE NULL END ) / nullif(SALEPPA1.EXFACTORY_ACTUAL_SALES, 0) ) * 100,  " ;     
       
       selectClause += " discount_of_exfactory_forecast = isnull((TODIS.PROJECTION_SALES / nullif(SALEPPA1.EXFACTORY_FORECAST_SALES, 0)), 0) * 100 "
                
                 +("Program Category".equalsIgnoreCase(projSelDTO.getDiscountLevel())?StringUtils.EMPTY:"    ,RS_CONTRACT_SID  ") ;
        ppaSelectClause += list.get(0) + " PPA.PPA_DISCOUNT\n, (ISNULL(PPA.SALES_PPA,0)) AS PPA_DISCOUNT_AMOUNT\n"
                + "              ,Isnull(PPA.SALES_PPA / NULLIF(SALEPPA.SALES_PROJECTION_SALES, 0), 0) * 100 AS PPA_DISCOUNT_PERCENT\n"
                + "              ,(Isnull(PPA.SALES_PPA / NULLIF(SALEPPA.PROJECTION_UNITS, 0), 0)) AS PPA_DISCOUNT_RPU"
                + "              , ISNULL(PPA.PPA_ACTUAL_AMOUNT,0) AS ACTUAL_AMOUNT  "
                + "              , ISNULL(PPA.PPA_ACTUAL_AMOUNT/NULLIF(ACTUAL_SALES,0),0)*100 ACTUAL_RATE "
                + "              , ISNULL(PPA.PPA_ACTUAL_AMOUNT/NULLIF(ACTUAL_UNITS,0),0) "
               
                + "              ,(PPA.PPA_ACTUAL_AMOUNT / nullif(SALEPPA1.EXFACTORY_ACTUAL_SALES, 0)) * 100 "
                + "              , isnull((PPA.SALES_PPA / nullif(SALEPPA1.EXFACTORY_FORECAST_SALES, 0)), 0) * 100 "
                 +("Program Category".equalsIgnoreCase(projSelDTO.getDiscountLevel())?StringUtils.EMPTY:"    ,RS_CONTRACT_SID  ") ;
        String totalDiscountQuery = getProjectionResultsDiscountsQueryDiscount(projSelDTO, StringUtils.EMPTY);
        String salesPpaQueryDiscount = getSalesQueryDiscount(getProjectionResultsSalesQueryForDiscount(projSelDTO));
        String salesPpaQueryDiscount1 = getSalesQueryDiscount1(getProjectionResultsSalesQueryForDiscount1(projSelDTO));
//        String salesPpaQuery = getSalesQuery(getProjectionResultsSalesQueryForPeriod(projSelDTO));
        String ppaQuery = getPPAQuery(getPPAProjectionResultsQueryForPeriod(projSelDTO, Boolean.TRUE), Boolean.TRUE,"Program Category".equalsIgnoreCase(projSelDTO.getDiscountLevel()));
        String ppaGroupBy = " ON SALEPPA.YEARS = PPA.YEARS AND SALEPPA.PERIODS = PPA.PERIODS AND SALEPPA.DISCOUNTS = PPA.PPA_DISCOUNT ";
        String productQuery = getProperFileDataDiscount(projSelDTO);
        String productQuery1 = getProperFileDataDiscount1(projSelDTO);
        customQuery = selectClause + " from (" + totalDiscountQuery + ") TODIS INNER JOIN (" + salesPpaQueryDiscount + ") SALEPPA " + finalWhereCond + " LEFT JOIN (" + productQuery + ") SALEPPA1 ON SALEPPA1.YEARS = SALEPPA.YEARS AND SALEPPA1.PERIODS = SALEPPA.PERIODS AND SALEPPA1.DISCOUNTS = SALEPPA.DISCOUNTS " + " UNION ALL " + ppaSelectClause + " FROM ( \n" + ppaQuery + ")PPA INNER JOIN (" + salesPpaQueryDiscount1 + ") SALEPPA" + ppaGroupBy + " LEFT JOIN ( "+ productQuery1 +") SALEPPA1 ON SALEPPA1.YEARS = SALEPPA.YEARS\n" +
        "  AND SALEPPA1.PERIODS = SALEPPA.PERIODS AND SALEPPA1.DISCOUNTS = SALEPPA.DISCOUNTS ";
        return customQuery;
    }

    public String getProjectionResultsPPAPerQuery(PVSelectionDTO projSelDTO) {
        String selectClause = " select ";
        String customQuery = StringUtils.EMPTY;
        selectClause += "SALEPPA.YEARS ,SALEPPA.PERIODS, ";
        selectClause += "'PPA Discount' as DISCOUNTS,";
        selectClause += " ACTUAL_RATE=Isnull(SALEPPA.ACTUAL_SALES / NULLIF(SALEPPA.SALES_ACTUAL_SALES, 0), 0) * 100,"
                + " PROJECTION_RATE=Isnull(SALEPPA.PROJECTION_SALES / NULLIF(SALEPPA.SALES_PROJECTION_SALES, 0), 0) * 100 ";
        String ppaQuery = getProjectionResultsSalesQuery(projSelDTO);
        customQuery = selectClause + " from (" + ppaQuery + ") SALEPPA";
        return customQuery;
    }

    public String getGeneralCCPQuery(PVSelectionDTO projSelDTO) {
        String viewtable = CommonLogic.getViewTableName(projSelDTO.getHierarchyIndicator());
        String ccpQuery = " SELECT LCCP.RELATIONSHIP_LEVEL_SID, LCCP.CCP_DETAILS_SID, LCCP.HIERARCHY_NO from \n"
                + "(SELECT CCPMAP.CCP_DETAILS_SID, HLD.HIERARCHY_NO, HLD.RELATIONSHIP_LEVEL_SID from \n"
                + "(SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + "FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + "JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n"
                + "JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID \n"
                + "AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId();
        if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            String userGroup = projSelDTO.getGroupFilter();
            ccpQuery += " " + CommonLogic.getGroupFilterQuery(userGroup, Integer.valueOf(projSelDTO.getUserId()), Integer.valueOf(projSelDTO.getSessionId()), false, null);
        }
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

    public String getCustomCCPQuery(PVSelectionDTO projSelDTO) {
        String customerHierarchyNo = projSelDTO.getCustomerHierarchyNo() + Constant.PERCENT;
        String productHierarchyNo = projSelDTO.getProductHierarchyNo() + Constant.PERCENT;
        String customerLevelNo = Constant.PERCENT;
        String productLevelNo = Constant.PERCENT;

        if (projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {

            customerLevelNo = StringUtils.EMPTY + projSelDTO.getTreeLevelNo();
        } else if (projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {

            productLevelNo = StringUtils.EMPTY + projSelDTO.getTreeLevelNo();
        }
        String ccpQuery = " SELECT distinct HLD" + projSelDTO.getHierarchyIndicator() + ".RELATIONSHIP_LEVEL_SID, CCPMAP" + projSelDTO.getHierarchyIndicator() + ".CCP_DETAILS_SID, HLD" + projSelDTO.getHierarchyIndicator() + ".HIERARCHY_NO FROM \n"
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n";

        if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            String userGroup = projSelDTO.getGroupFilter();
            ccpQuery += " " + CommonLogic.getGroupFilterQuery(userGroup, Integer.valueOf(projSelDTO.getUserId()), Integer.valueOf(projSelDTO.getSessionId()), false, null);
        }

        ccpQuery += " ) CCPMAPC \n"
                + " JOIN"
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n";

        if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            String userGroup = projSelDTO.getGroupFilter();
            ccpQuery += " " + CommonLogic.getGroupFilterQuery(userGroup, Integer.valueOf(projSelDTO.getUserId()), Integer.valueOf(projSelDTO.getSessionId()), false, null);
        }

        ccpQuery += " ) CCPMAPP \n"
                + " ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID \n"
                + " JOIN "
                + " (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD \n"
                + " JOIN dbo.CUSTOM_VIEW_MASTER CVM ON \n"
                + " CVD.CUSTOM_VIEW_MASTER_SID=" + projSelDTO.getCustomId() + " AND CVD.LEVEL_NO  like '" + customerLevelNo + "'\n"
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " WHERE RLD2.HIERARCHY_NO like '" + customerHierarchyNo + "') HLDC ON CCPMAPC.HIERARCHY_NO like HLDC.HIERARCHY_NO+'%' \n"
                + " JOIN "
                + " (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD \n"
                + " JOIN dbo.CUSTOM_VIEW_MASTER CVM ON \n"
                + " CVD.CUSTOM_VIEW_MASTER_SID=" + projSelDTO.getCustomId() + " AND CVD.LEVEL_NO like '" + productLevelNo + "'\n"
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " WHERE RLD2.HIERARCHY_NO like '" + productHierarchyNo + "') HLDP ON CCPMAPP.HIERARCHY_NO like HLDP.HIERARCHY_NO+'%'";

        return ccpQuery;
    }

    public String getCCPQuery1(PVSelectionDTO projSelDTO) {
        String ccpQuery = getCCPTempTableQuery();
        if (projSelDTO.isIsCustomHierarchy()) {
            ccpQuery += getCustomCCPQuery(projSelDTO);
        } else {
            ccpQuery += getGeneralCCPQuery(projSelDTO);
        }
        return ccpQuery;
    }

    public String getCCPTempTableQuery() {
        String tableQuery = "DECLARE @CCP TABLE\n"
                + "  (\n"
                + "     RELATIONSHIP_LEVEL_SID INT,\n"
                + "     CCP_DETAILS_SID        INT,\n"
                + "     HIERARCHY_NO           VARCHAR(50)\n"
                + "  ) \n"
                + " INSERT INTO @CCP\n"
                + "            (RELATIONSHIP_LEVEL_SID,CCP_DETAILS_SID,HIERARCHY_NO) \n";
        return tableQuery;
    }

    /**
     * Comparison Look up search logic
     *
     * @param comparisonLookupDTO
     * @return String for Query
     */
    public String getComparisonSearchCount(final ComparisonLookupDTO comparisonLookupDTO, Set<Filter> filter, String screenName, Map<String, Object> parameters) {
        String QUOTES = "'";
        String ASTERIK = "*";
        String PERCENT = Constant.PERCENT;
        String marketTypeVal = StringUtils.EMPTY;
        String brandVal = StringUtils.EMPTY;
        String projNameVal = StringUtils.EMPTY;
        String contHldrVal = StringUtils.EMPTY;
        String ndcNoVal = StringUtils.EMPTY;
        String ndcNameVal = StringUtils.EMPTY;
        String descVal = StringUtils.EMPTY;
        String contractVal = StringUtils.EMPTY;
        boolean isProjectionStatus = false;
        try {
            StringBuilder customSql = new StringBuilder(StringUtils.EMPTY);
            if (comparisonLookupDTO.getWorkflowStatus().equals(Constant.SAVED)) {
                isProjectionStatus = true;
            }
            if (isProjectionStatus) {
                customSql = new StringBuilder(CustomSQLUtil.get("getProjectionListsCount"));
            } else {
                customSql = new StringBuilder(CustomSQLUtil.get("getWorkFlowListsCount"));
            }

            if (comparisonLookupDTO.getMarketType() == null || comparisonLookupDTO.getMarketType().equals(StringUtils.EMPTY)) {
                marketTypeVal = "'%'";
            } else {
                marketTypeVal = comparisonLookupDTO.getMarketType().replace(ASTERIK, PERCENT);
                marketTypeVal = QUOTES + marketTypeVal + QUOTES;
            }
            customSql.append("( HT.list_name = 'CONTRACT_TYPE' AND HT.DESCRIPTION LIKE ").append(marketTypeVal).append(")");
            if (comparisonLookupDTO.getBrand() == null || comparisonLookupDTO.getBrand().equals(StringUtils.EMPTY)) {
                brandVal = "'%'";
            } else {
                brandVal = comparisonLookupDTO.getBrand().replace(ASTERIK, PERCENT);
                brandVal = QUOTES + brandVal + QUOTES;
            }
            customSql.append("  AND (BM.BRAND_NAME LIKE ").append(brandVal).append(" or BM.BRAND_NAME is null)");
            if (comparisonLookupDTO.getProjectionName() == null || comparisonLookupDTO.getProjectionName().equals(StringUtils.EMPTY)) {
                projNameVal = "'%'";
            } else {
                projNameVal = comparisonLookupDTO.getProjectionName().replace(ASTERIK, PERCENT);
                projNameVal = QUOTES + projNameVal + QUOTES;
            }
            customSql.append("AND (PM.PROJECTION_NAME LIKE ").append(projNameVal).append(" or PM.PROJECTION_NAME is null)");
            if (comparisonLookupDTO.getCustomer() == null || comparisonLookupDTO.getCustomer().equals(StringUtils.EMPTY)) {
                contHldrVal = "'%'";
            } else {
                contHldrVal = comparisonLookupDTO.getCustomer().replace(ASTERIK, PERCENT);
                contHldrVal = QUOTES + contHldrVal + QUOTES;
            }
            customSql.append("AND (C.CONTRACT_NO LIKE ").append(contHldrVal).append(" or C.CONTRACT_NO is null)");
            if (comparisonLookupDTO.getNdcName() == null || comparisonLookupDTO.getNdcName().equals(StringUtils.EMPTY)) {
                ndcNameVal = "'%'";
            } else {
                ndcNameVal = comparisonLookupDTO.getNdcName().replace(ASTERIK, PERCENT);
                ndcNameVal = QUOTES + ndcNameVal + QUOTES;
            }
            customSql.append("AND (IM.ITEM_NAME LIKE ").append(ndcNameVal).append(" or IM.ITEM_NAME is null)");
            if (comparisonLookupDTO.getNdcNo() == null || comparisonLookupDTO.getNdcNo().equals(StringUtils.EMPTY)) {
                ndcNoVal = "'%'";
            } else {
                ndcNoVal = comparisonLookupDTO.getNdcNo().replace(ASTERIK, PERCENT);
                ndcNoVal = QUOTES + ndcNoVal + QUOTES;
            }
            customSql.append("AND (IM.ITEM_NO LIKE ").append(ndcNoVal).append("or IM.ITEM_NO is null)");
            if (comparisonLookupDTO.getContract() == null || comparisonLookupDTO.getContract().equals(StringUtils.EMPTY)) {
                contractVal = "'%'";
            } else {
                contractVal = comparisonLookupDTO.getContract().replace(ASTERIK, PERCENT);
                contractVal = QUOTES + contractVal + QUOTES;
            }
            customSql.append("AND (CM.COMPANY_NO LIKE ").append(contractVal).append("or CM.COMPANY_NO is null)");
            if (comparisonLookupDTO.getProjectionDescription() == null || comparisonLookupDTO.getProjectionDescription().equals(StringUtils.EMPTY)) {
                descVal = "'%'";
            } else {
                descVal = comparisonLookupDTO.getProjectionDescription().replace(ASTERIK, PERCENT);
                descVal = QUOTES + descVal + QUOTES;
            }
            customSql.append("AND (PM.PROJECTION_DESCRIPTION LIKE ").append(descVal).append("or PM.PROJECTION_DESCRIPTION is null)");
            if (isProjectionStatus) {
                customSql.append("and pm.is_approved not in ('Y','C','A','R')");
            } else {
                customSql.append("AND HT1.list_name = 'WorkFlowStatus' and ht1.description = ").append(QUOTES).append(comparisonLookupDTO.getWorkflowStatus()).append(QUOTES);
            }
            if ((comparisonLookupDTO.getCreatedDateFrom() != null && !StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())) && !Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())))
                    && (comparisonLookupDTO.getCreatedDateTo() == null || StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())) || Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())))) {
                SimpleDateFormat format2 = new SimpleDateFormat(DATE_FORMAT.getConstant());
                customSql.append(" AND PM.CREATED_DATE >= '");
                customSql.append(format2.format(comparisonLookupDTO.getCreatedDateFrom()));
                customSql.append("' ");
            } else if ((comparisonLookupDTO.getCreatedDateTo() != null && !StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())) && !Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())))
                    && (comparisonLookupDTO.getCreatedDateFrom() == null || StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())) || Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())))) {
                SimpleDateFormat format2 = new SimpleDateFormat(DATE_FORMAT.getConstant());
                customSql.append(" AND PM.CREATED_DATE <= '");
                customSql.append(format2.format(format2.parse(comparisonLookupDTO.getCreatedDateTo())));
                customSql.append("' ");
            } else if (comparisonLookupDTO.getCreatedDateFrom() != null && !Constant.NULL.equals(comparisonLookupDTO.getCreatedDateFrom()) && comparisonLookupDTO.getCreatedDateFrom() != null
                    && comparisonLookupDTO.getCreatedDateTo() != null && !Constant.NULL.equals(comparisonLookupDTO.getCreatedDateTo()) && !StringUtils.isEmpty(comparisonLookupDTO.getCreatedDateTo())) {
                SimpleDateFormat format2 = new SimpleDateFormat(DATE_FORMAT.getConstant());
                customSql.append(" AND PM.CREATED_DATE BETWEEN '");
                customSql.append(format2.format(comparisonLookupDTO.getCreatedDateFrom()));
                customSql.append("' AND '");
                customSql.append(format2.format(format2.parse(comparisonLookupDTO.getCreatedDateTo())));
                customSql.append("' ");
            }
            //Filter
            if (parameters.get("filter~projectionName") != null) {
                customSql.append("AND (PM.PROJECTION_NAME LIKE '").append(parameters.get("filter~projectionName")).append("') ");
            }
            if (parameters.get("filter~projectionDescription") != null) {
                customSql.append("AND (PM.PROJECTION_DESCRIPTION LIKE '").append(parameters.get("filter~projectionDescription")).append("') ");
            }
            if (parameters.get("filter~marketType") != null) {
                customSql.append("AND (HT.DESCRIPTION LIKE '").append(parameters.get("filter~marketType")).append("') ");
            }
            if (parameters.get("filter~contract") != null) {
                customSql.append("AND (C.CONTRACT_NO LIKE '").append(parameters.get("filter~contract")).append("')");
            }
            if (parameters.get("filter~customer") != null) {
                customSql.append("AND (CM.COMPANY_NO LIKE '").append(parameters.get("filter~customer")).append("') ");
            }
            if (parameters.get("filter~brand") != null) {
                String value = String.valueOf(parameters.get("filter~brand"));
                value = value.replace(Constant.PERCENT, StringUtils.EMPTY);
                if ("multiple".contains(value.toLowerCase())) {
                    customSql.append("AND BM.BRAND_NAME IS NOT NULL ");
                } else {
                    customSql.append("AND (BM.BRAND_NAME LIKE '").append(parameters.get("filter~brand")).append("') ");
                }
            }
            if (parameters.get("filter~Between~createdDateFrom~from") != null && parameters.get("filter~Between~createdDateFrom~to") != null) {
                customSql.append("AND PM.CREATED_DATE BETWEEN '").append(parameters.get("filter~Between~createdDateFrom~from")).append("' AND '").append(parameters.get("filter~Between~createdDateFrom~to")).append("' ");
            }
            if (parameters.get("filter~GOE~createdDateFrom~from") != null) {
                customSql.append("AND PM.CREATED_DATE >= '").append(parameters.get("filter~GOE~createdDateFrom~from")).append("' ");
            }
            if (parameters.get("filter~GOE~createdDateFrom~to") != null) {
                customSql.append("AND PM.CREATED_DATE <= '").append(parameters.get("filter~GOE~createdDateFrom~to")).append("' ");
            }
            if ((parameters.get("filter~createdBy") != null) && (parameters.get("filter~createdBy") != null)) {
                    List<String> strList = new ArrayList<String>();
                    final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
                    Criterion criterion = RestrictionsFactoryUtil.ilike(Constant.FIRSTNAME, Constant.PERCENT + parameters.get("filter~createdBy") + Constant.PERCENT);
                    Criterion criterion1 = RestrictionsFactoryUtil.ilike(Constant.LASTNAME, Constant.PERCENT + parameters.get("filter~createdBy") + Constant.PERCENT);
                    dynamicQuery.add(RestrictionsFactoryUtil.or(criterion, criterion1));
                    final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
                    productProjectionList.add(ProjectionFactoryUtil.property(Constant.USER_ID));
                    dynamicQuery.setProjection(productProjectionList);
                    strList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
                    String userID = CommonUtils.CollectionToString(strList, false);
                    customSql.append("AND (PM.CREATED_BY IN (").append(userID).append(") ) ");
            }
            customSql.append("AND PM.PROJECTION_MASTER_SID NOT IN (").append(comparisonLookupDTO.getCurrentProjId()).append(")");
            customSql.append("AND CUR_PD.PROJECTION_MASTER_SID IN (").append(comparisonLookupDTO.getCurrentProjId()).append(") ");
            customSql.append("AND PM.FORECASTING_TYPE='").append(screenName).append("' ");
            customSql.append(" group by  pm.projection_name,pm.projection_description,pm.projection_master_sid,PM.created_date,PM.created_by ) RSLT ");
            return customSql.toString();
        } catch (Exception e) {
            LOGGER.error(e + " at PvQueryUtils -> getComparisonSearch");
            return null;
        }
    }

    /**
     * Comparison Look up search logic
     *
     * @param comparisonLookupDTO
     * @return String for Query
     */
    public String getComparisonSearch(final ComparisonLookupDTO comparisonLookupDTO, int start, int offset, Set<Filter> filter, List<SortByColumn> sortColumns, String screenName, Map<String, Object> parameters) {
        String QUOTES = "'";
        String ASTERIK = "*";
        String PERCENT = Constant.PERCENT;
        String marketTypeVal = StringUtils.EMPTY;
        String brandVal = StringUtils.EMPTY;
        String projNameVal = StringUtils.EMPTY;
        String contHldrVal = StringUtils.EMPTY;
        String ndcNoVal = StringUtils.EMPTY;
        String ndcNameVal = StringUtils.EMPTY;
        String descVal = StringUtils.EMPTY;
        String contractVal = StringUtils.EMPTY;
        boolean isProjectionStatus = false;
        Map<Object, String> columns = new HashMap<Object, String>();
        columns.put(Constant.PROJECTION_NAME, "pm.projection_name");
        columns.put(Constant.PROJECTIONDESCRIPTION, "pm.projection_description");
        columns.put("marketType", "market_type");
        columns.put("customer", Constant.CUSTOMER_SMALL);
        columns.put(Constant.CONTRACT, Constant.CONTRACT_SMALL);
        columns.put(Constant.BRAND, Constant.BRAND_CAPS);
        columns.put("createdDateFrom", "PM.created_date");
        columns.put("createdBy", "PM.created_by");
        try {
            StringBuilder customSql = new StringBuilder(StringUtils.EMPTY);
            if (comparisonLookupDTO.getWorkflowStatus().equals(Constant.SAVED)) {
                isProjectionStatus = true;
            }

            if (isProjectionStatus) {
                customSql = new StringBuilder(CustomSQLUtil.get("getProjectionLists"));
            } else {
                customSql = new StringBuilder(CustomSQLUtil.get("getWorkFlowLists"));
            }

            if (comparisonLookupDTO.getMarketType() == null || comparisonLookupDTO.getMarketType().equals(StringUtils.EMPTY)) {
                marketTypeVal = "'%'";
            } else {
                marketTypeVal = comparisonLookupDTO.getMarketType().replace(ASTERIK, PERCENT);
                marketTypeVal = QUOTES + marketTypeVal + QUOTES;
            }
            customSql.append("( HT.list_name = 'CONTRACT_TYPE' AND HT.DESCRIPTION LIKE ").append(marketTypeVal).append(")");
            if (comparisonLookupDTO.getBrand() == null || comparisonLookupDTO.getBrand().equals(StringUtils.EMPTY)) {
                brandVal = "'%'";
            } else {
                brandVal = comparisonLookupDTO.getBrand().replace(ASTERIK, PERCENT);
                brandVal = QUOTES + brandVal + QUOTES;
            }
            customSql.append("  AND (BM.BRAND_NAME LIKE ").append(brandVal).append(" or BM.BRAND_NAME is null)");
            if (comparisonLookupDTO.getProjectionName() == null || comparisonLookupDTO.getProjectionName().equals(StringUtils.EMPTY)) {
                projNameVal = "'%'";
            } else {
                projNameVal = comparisonLookupDTO.getProjectionName().replace(ASTERIK, PERCENT);
                projNameVal = QUOTES + projNameVal + QUOTES;
            }
            customSql.append("AND (PM.PROJECTION_NAME LIKE ").append(projNameVal).append(" or PM.PROJECTION_NAME is null)");
            if (comparisonLookupDTO.getCustomer() == null || comparisonLookupDTO.getCustomer().equals(StringUtils.EMPTY)) {
                contHldrVal = "'%'";
            } else {
                contHldrVal = comparisonLookupDTO.getCustomer().replace(ASTERIK, PERCENT);
                contHldrVal = QUOTES + contHldrVal + QUOTES;
            }
            customSql.append("AND (C.CONTRACT_NO LIKE ").append(contHldrVal).append(" or C.CONTRACT_NO is null)");
            if (comparisonLookupDTO.getNdcName() == null || comparisonLookupDTO.getNdcName().equals(StringUtils.EMPTY)) {
                ndcNameVal = "'%'";
            } else {
                ndcNameVal = comparisonLookupDTO.getNdcName().replace(ASTERIK, PERCENT);
                ndcNameVal = QUOTES + ndcNameVal + QUOTES;
            }
            customSql.append("AND (IM.ITEM_NAME LIKE ").append(ndcNameVal).append(" or IM.ITEM_NAME is null)");
            if (comparisonLookupDTO.getNdcNo() == null || comparisonLookupDTO.getNdcNo().equals(StringUtils.EMPTY)) {
                ndcNoVal = "'%'";
            } else {
                ndcNoVal = comparisonLookupDTO.getNdcNo().replace(ASTERIK, PERCENT);
                ndcNoVal = QUOTES + ndcNoVal + QUOTES;
            }
            customSql.append("AND (IM.ITEM_NO LIKE ").append(ndcNoVal).append("or IM.ITEM_NO is null)");
            if (comparisonLookupDTO.getContract() == null || comparisonLookupDTO.getContract().equals(StringUtils.EMPTY)) {
                contractVal = "'%'";
            } else {
                contractVal = comparisonLookupDTO.getContract().replace(ASTERIK, PERCENT);
                contractVal = QUOTES + contractVal + QUOTES;
            }
            customSql.append("AND (CM.COMPANY_NO LIKE ").append(contractVal).append("or CM.COMPANY_NO is null)");
            if (comparisonLookupDTO.getProjectionDescription() == null || comparisonLookupDTO.getProjectionDescription().equals(StringUtils.EMPTY)) {
                descVal = "'%'";
            } else {
                descVal = comparisonLookupDTO.getProjectionDescription().replace(ASTERIK, PERCENT);
                descVal = QUOTES + descVal + QUOTES;
            }
            customSql.append("AND (PM.PROJECTION_DESCRIPTION LIKE ").append(descVal).append("or PM.PROJECTION_DESCRIPTION is null)");
            if (isProjectionStatus) {
                customSql.append("and pm.is_approved not in ('Y','C','A','R')");
            } else {
                customSql.append("AND HT1.list_name = 'WorkFlowStatus' and ht1.description = ").append(QUOTES).append(comparisonLookupDTO.getWorkflowStatus()).append(QUOTES);
            }
            if ((comparisonLookupDTO.getCreatedDateFrom() != null && !StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())) && !Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())))
                    && (comparisonLookupDTO.getCreatedDateTo() == null || StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())) || Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())))) {
                SimpleDateFormat format2 = new SimpleDateFormat(DATE_FORMAT.getConstant());
                customSql.append(" AND PM.CREATED_DATE >= '");
                customSql.append(format2.format(comparisonLookupDTO.getCreatedDateFrom()));
                customSql.append("' ");
            } else if ((comparisonLookupDTO.getCreatedDateTo() != null && !StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())) && !Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())))
                    && (comparisonLookupDTO.getCreatedDateFrom() == null || StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())) || Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())))) {
                SimpleDateFormat format2 = new SimpleDateFormat(DATE_FORMAT.getConstant());
                customSql.append(" AND PM.CREATED_DATE <= '");
                customSql.append(format2.format(format2.parse(comparisonLookupDTO.getCreatedDateTo())));
                customSql.append("' ");
            } else if (comparisonLookupDTO.getCreatedDateFrom() != null && !Constant.NULL.equals(comparisonLookupDTO.getCreatedDateFrom()) && comparisonLookupDTO.getCreatedDateFrom() != null
                    && comparisonLookupDTO.getCreatedDateTo() != null && !Constant.NULL.equals(comparisonLookupDTO.getCreatedDateTo()) && !StringUtils.isEmpty(comparisonLookupDTO.getCreatedDateTo())) {
                SimpleDateFormat format2 = new SimpleDateFormat(DATE_FORMAT.getConstant());
                customSql.append(" AND PM.CREATED_DATE BETWEEN '");
                customSql.append(format2.format(comparisonLookupDTO.getCreatedDateFrom()));
                customSql.append("' AND '");
                customSql.append(format2.format(format2.parse(comparisonLookupDTO.getCreatedDateTo())));
                customSql.append("' ");
            }
            //Filter
            if (parameters.get("filter~projectionName") != null) {
                customSql.append("AND (PM.PROJECTION_NAME LIKE '").append(parameters.get("filter~projectionName")).append("') ");
            }
            if (parameters.get("filter~projectionDescription") != null) {
                customSql.append("AND (PM.PROJECTION_DESCRIPTION LIKE '").append(parameters.get("filter~projectionDescription")).append("') ");
            }
            if (parameters.get("filter~marketType") != null) {
                customSql.append("AND (HT.DESCRIPTION LIKE '").append(parameters.get("filter~marketType")).append("') ");
            }
            if (parameters.get("filter~contract") != null) {
                customSql.append("AND (C.CONTRACT_NO LIKE '").append(parameters.get("filter~contract")).append("')");
            }
            if (parameters.get("filter~customer") != null) {
                customSql.append("AND (CM.COMPANY_NO LIKE '").append(parameters.get("filter~customer")).append("') ");
            }
            if (parameters.get("filter~brand") != null) {
                String value = String.valueOf(parameters.get("filter~brand"));
                value = value.replace(Constant.PERCENT, StringUtils.EMPTY);
                if ("multiple".contains(value.toLowerCase())) {
                    customSql.append("AND BM.BRAND_NAME IS NOT NULL ");
                } else {
                    customSql.append("AND (BM.BRAND_NAME LIKE '").append(parameters.get("filter~brand")).append("') ");
                }
            }
            if (parameters.get("filter~Between~createdDateFrom~from") != null && parameters.get("filter~Between~createdDateFrom~to") != null) {
                customSql.append("AND PM.CREATED_DATE BETWEEN '").append(parameters.get("filter~Between~createdDateFrom~from")).append("' AND '").append(parameters.get("filter~Between~createdDateFrom~to")).append("' ");
            }
            if (parameters.get("filter~GOE~createdDateFrom~from") != null) {
                customSql.append("AND PM.CREATED_DATE > = '").append(parameters.get("filter~GOE~createdDateFrom~from")).append("' ");
            }
            if (parameters.get("filter~GOE~createdDateFrom~to") != null) {
                customSql.append("AND PM.CREATED_DATE < = '").append(parameters.get("filter~GOE~createdDateFrom~to")).append("' ");
            }
            if (parameters.get("filter~createdBy") != null) {
                List<String> strList = new ArrayList<String>();
                final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
                Criterion criterion = RestrictionsFactoryUtil.ilike(Constant.FIRSTNAME, Constant.PERCENT + parameters.get("filter~createdBy") + Constant.PERCENT);
                Criterion criterion1 = RestrictionsFactoryUtil.ilike(Constant.LASTNAME, Constant.PERCENT + parameters.get("filter~createdBy") + Constant.PERCENT);
                dynamicQuery.add(RestrictionsFactoryUtil.or(criterion, criterion1));
                final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
                productProjectionList.add(ProjectionFactoryUtil.property(Constant.USER_ID));
                dynamicQuery.setProjection(productProjectionList);
                strList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
                String userID = CommonUtils.CollectionToString(strList, false);
                customSql.append("AND (PM.CREATED_BY IN (").append(userID).append(") ) ");
            }
            customSql.append("AND PM.PROJECTION_MASTER_SID NOT IN (").append(comparisonLookupDTO.getCurrentProjId()).append(")");
            customSql.append("AND CUR_PD.PROJECTION_MASTER_SID IN (").append(comparisonLookupDTO.getCurrentProjId()).append(") ");
            customSql.append("AND PM.FORECASTING_TYPE='").append(screenName).append("' ");
            customSql.append(" group by  pm.projection_name,pm.projection_description,pm.projection_master_sid,PM.created_date,PM.created_by ");
            customSql.append(" ORDER BY ");

            if (sortColumns == null || sortColumns.size() == 0) {
                customSql.append(" pm.projection_name, pm.projection_description, PM.created_date, PM.created_by,  pm.projection_master_sid ");
            } else {
                Boolean temp = Boolean.FALSE;
                for (Iterator<SortByColumn> it = sortColumns.iterator(); it.hasNext();) {
                    SortByColumn sortByColumn = it.next();
                    if (temp) {
                        customSql.append(", ");
                        temp = Boolean.TRUE;
                    }
                    customSql.append(columns.get(sortByColumn.getName())).append(" ").append(sortByColumn.getType());
                }
            }

            customSql.append(" OFFSET ");
            customSql.append(start);
            customSql.append(" ROWS FETCH NEXT ");
            customSql.append(offset);
            customSql.append(" ROWS ONLY ;");
            return customSql.toString();
        } catch (Exception e) {
            LOGGER.error(e + " at PvQueryUtils -> getComparisonSearch");
            return null;
        }
    }

    /**
     * Main Whole Query
     *
     * @param projSelDTO
     * @return String
     */
    public String getProjVarianceMainQuery(PVSelectionDTO projSelDTO) {
        projSelDTO.setIsTotal(true);
        String selectClause = " select ";
        String customQuery = StringUtils.EMPTY;
        String orderBy = " YEARS,PERIODS ";
        selectClause += "C.YEARS as YEARS, C.PERIODS AS PERIODS,";

        customQuery = selectClause + getMainSelectClause(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            customQuery += ", " + getMainSelectClause(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY + i);
        }
        projSelDTO.setProjectionId(projSelDTO.getCurrentProjId());
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
        projSelDTO.setIsPrior(false);
        customQuery += " from  \n(" + getProjVarianceQuery(projSelDTO) + ") C\n ";
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
        projSelDTO.setIsPrior(true);
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            projSelDTO.setProjectionId(projSelDTO.getProjIdList().get(i));
            customQuery += " LEFT JOIN (\n" + getProjVarianceQuery(projSelDTO) + "\n) " + Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY + i + " \n" + getProjVarMainWhereCond(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY + i);
        }
        customQuery += " order by " + orderBy;
        projSelDTO.setIsPrior(false);
        projSelDTO.setCurrentOrPrior(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
        return customQuery;
    }

    /**
     * Main Select clause
     *
     * @param projName
     * @return String
     */
    public String getMainSelectClause(String projName) {
        String selectClause = " " + projName + ".CONTRACT_PROJECTION_SALES as " + projName + "CONTRACT_PROJECTION_SALES,"
                + " " + projName + ".CONTRACT_PROJECTION_UNITS as " + projName + "CONTRACT_PROJECTION_UNITS,"
                + " " + projName + ".TOTAL_PROJECTION_RATE AS " + projName + "TOTAL_PROJECTION_RATE, "
                + " " + projName + ".TOTAL_PROJECTION_DOLAR as " + projName + "TOTAL_PROJECTION_DOLAR ";
        return selectClause;
    }

    /**
     * Variance sales and discount query
     *
     * @param projSelDTO
     * @return String
     */
    public String getProjVarianceQuery(PVSelectionDTO projSelDTO) {
        projSelDTO.setIsTotal(true);
        String selectClause = " select ";
        selectClause += "TODIS.YEARS as YEARS, TODIS.PERIODS AS PERIODS,";
        selectClause += getProjVarianceSelectClause();
        String totalDiscountQuery = getProjVarianceDiscountQuery(projSelDTO);
        String salesQuery = getProjVariancesSalesQuery(projSelDTO);
        String customQuery = selectClause + " from " + getProjVarSelectQuery(totalDiscountQuery, salesQuery);
        return customQuery;
    }

    /**
     * Get select query
     *
     * @param query1
     * @param query2
     * @return
     */
    public String getProjVarSelectQuery(String query1, String query2) {
        String finalWhereCond = " on TODIS.YEARS=SALEPPA.YEARS AND TODIS.PERIODS=SALEPPA.PERIODS";
        String selectClause = " (\n" + query1 + "\n) TODIS FULL OUTER JOIN (\n" + query2 + "\n) SALEPPA \n" + finalWhereCond;
        return selectClause;
    }

    /**
     * Where COnditions
     *
     * @param table
     * @return
     */
    public String getProjVarMainWhereCond(String table) {
        return " on C.YEARS=" + table + ".YEARS AND C.PERIODS=" + table + ".PERIODS \n";
    }

    /**
     * Select clause
     *
     * @return
     */
    public String getProjVarianceSelectClause() {
        String selectClause = " SALEPPA.SALES_PROJECTION_SALES as CONTRACT_PROJECTION_SALES,"
                + "SALEPPA.PROJECTION_UNITS as CONTRACT_PROJECTION_UNITS,"
                + " CASE WHEN SALEPPA.SALES_PROJECTION_SALES = 0 THEN 0 ELSE ((Isnull(TODIS.PROJECTION_SALES, 0)) /SALEPPA.SALES_PROJECTION_SALES) * 100 END  AS TOTAL_PROJECTION_RATE, "
                + "(Isnull(TODIS.PROJECTION_SALES, 0)) as TOTAL_PROJECTION_DOLAR ";
        return selectClause;
    }

    /**
     * Discount Query
     *
     * @param projSelDTO
     * @return String
     */
    public String getProjVarianceDiscountQuery(PVSelectionDTO projSelDTO) {
        boolean viewFlag=Constant.VIEW.equals(projSelDTO.getSessionDTO().getAction());
        String mainMasterTable = "  CH_DISCOUNT_PROJ_MASTER ";
        String tempMasterTable = " ST_CH_DISCOUNT_PROJ_MASTER ";
        String mainTable = " CH_PROJECTION_DISCOUNT ";
        String tempTable = " ST_CH_PROJECTION_DISCOUNT ";
        String masterTable = StringUtils.EMPTY;
        String table = StringUtils.EMPTY;
        if (!projSelDTO.isIsPrior() && !viewFlag) {
            masterTable = tempMasterTable;
            table = tempTable;
        } else {
            masterTable = mainMasterTable;
            table = mainTable;
        }
        String selectClause = " select ";
        String whereClause = StringUtils.EMPTY;
        String groupBy = " I.\"YEAR\"";

        String ccpWhereCond = getCCPWhereConditionQuery("E", "CCP");

        selectClause += "I.\"YEAR\" as YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += "I.\"MONTH\" as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.\"MONTH\"";
        }

        // To filter the data according to selected period
        String periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);

        // To handle the scenario where any discount is not selected in program selection lookup
        String discountTypeColumnName = "'Total discounts' as DISCOUNTS";
        if (projSelDTO.getDiscountNoList() != null && !projSelDTO.getDiscountNoList().isEmpty()) {
            if (!projSelDTO.isIsTotal()) {
                discountTypeColumnName = " J.RS_NAME as DISCOUNTS";
                groupBy += ", " + " J.RS_NAME";
            }
            whereClause += " and B.RS_CONTRACT_SID in (" + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false) + ")";
        }
        selectClause += discountTypeColumnName + ", ";

        String customSql = masterTable
                + " B, PROJECTION_DETAILS E ,"
                + " \"PERIOD\" I, "
                + " @CCP CCP ";
        if (!projSelDTO.isIsTotal()) {
            customSql += ", RS_CONTRACT J ";
        }
        customSql += "where A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID and B.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID ";
        if (projSelDTO.getCurrentOrPrior().equalsIgnoreCase(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
            customSql += getUserSessionQueryCondition(Integer.valueOf(projSelDTO.getUserId()), Integer.valueOf(projSelDTO.getSessionId()), "B")
                    + getUserSessionQueryCondition(Integer.valueOf(projSelDTO.getUserId()), Integer.valueOf(projSelDTO.getSessionId()), "A");
        }
        if (!projSelDTO.isIsTotal()) {
            customSql += "and B.RS_CONTRACT_SID= J.RS_CONTRACT_SID ";
        }
        customSql += "and A.RS_CONTRACT_SID = B.RS_CONTRACT_SID "
                + " and E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId()
                + ccpWhereCond + "and A.PERIOD_SID = I.PERIOD_SID " + periodFilter
                + whereClause + " group by " + groupBy;

        String futureQuery = selectClause + " 0 as ACTUAL_SALES, sum(A.PROJECTION_SALES) as PROJECTION_SALES from "
                + table + " A," + customSql;
        return futureQuery;
    }

    /**
     * Sales Query
     *
     * @param projSelDTO
     * @return
     */
    public String getProjVariancesSalesQuery(PVSelectionDTO projSelDTO) {
        boolean viewFlag=Constant.VIEW.equals(projSelDTO.getSessionDTO().getAction());
        String mainMasterSalesTable = "  CH_SALES_PROJECTION_MASTER ";
        String tempMasterSalesTable = " ST_CH_SALES_PROJECTION_MASTER ";
        String mainSalesTable = " CH_SALES_PROJECTION ";
        String tempSalesTable = " ST_CH_SALES_PROJECTION ";

        String masterSalesTable = StringUtils.EMPTY;
        String salesTable = StringUtils.EMPTY;

        if (!projSelDTO.isIsPrior() && !viewFlag) {
            masterSalesTable = tempMasterSalesTable;
            salesTable = tempSalesTable;
        } else {
            masterSalesTable = mainMasterSalesTable;
            salesTable = mainSalesTable;
        }
        String selectClause = " select ";
        String whereClause = StringUtils.EMPTY;
        String groupBy = " I.\"YEAR\"";
        String ccpWhereCond = getCCPWhereConditionQuery("E", "CCP");
        selectClause += "I.\"YEAR\" as YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += "I.\"MONTH\" as PERIODS, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.\"MONTH\"";
        }
        // To filter the data according to selected period
        String periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);

        // To handle the scenario where any discount is not selected in program selection lookup
        String customSql
                = masterSalesTable + "  B1,"
                + " PROJECTION_DETAILS E, "
                + " \"PERIOD\" I, "
                + " @CCP CCP "
                + "where A1.PROJECTION_DETAILS_SID = B1.PROJECTION_DETAILS_SID "
                + "and B1.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID ";
        if (projSelDTO.getCurrentOrPrior().equalsIgnoreCase(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
            customSql += getUserSessionQueryCondition(Integer.valueOf(projSelDTO.getUserId()), Integer.valueOf(projSelDTO.getSessionId()), "B1")
                    + getUserSessionQueryCondition(Integer.valueOf(projSelDTO.getUserId()), Integer.valueOf(projSelDTO.getSessionId()), "A1");
        }
        customSql += " and E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId()
                + ccpWhereCond
                + "and A1.PERIOD_SID = I.PERIOD_SID "
                + periodFilter
                + whereClause
                + " group by " + groupBy;
        String futureQuery = selectClause
                + " 0 as SALES_ACTUAL_SALES, sum(A1.CONTRACT_SALES) as SALES_PROJECTION_SALES, "
                + " 0 as ACTUAL_UNITS, sum(A1.CONTRACT_UNITS) as PROJECTION_UNITS "
                + " from "
                + salesTable + " A1," + customSql;

        return futureQuery;
    }

    public static String getFinalQueryForCOGS(PVSelectionDTO pvsDTO) {
        String periodFilter = CommonLogic.getPeriodRestrictionQuery(pvsDTO);
        String query = " FULL JOIN ( SELECT ITEM_PRICE = COALESCE(AVG(ITEM_PRICE), 0)\n"
                + "  , I.YEAR\n"
                + "  , I.QUARTER\n"
                + "  FROM [DBO].[UDF_ITEM_PRICING](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, 'UN') U\n"
                + "  , PERIOD I\n"
                + "  WHERE I.PERIOD_SID = U.PERIOD_SID\n" + periodFilter
                + "  GROUP BY I.\"YEAR\"\n"
                + "  , I.QUARTER\n"
                + "  ) COGS\n"
                + "  ON SALEPPA.YEARS = COGS.YEAR\n"
                + "  AND SALEPPA.PERIODS = COGS.QUARTER";
        return query;
    }
    
    /**
     * 
     * @param comparisonLookupDTO
     * @param screenName
     * @param parameters
     * @param sortColumns
     * @param start
     * @param offset
     * @param isCount
     * @return 
     */
    public String getComparisionSearchResults(final ComparisonLookupDTO comparisonLookupDTO, final String screenName, final Map<String, Object> parameters, final List<SortByColumn> sortColumns, final int start, final int offset, boolean isCount) {
        try {
            String QUOTES = "'";
            String ASTERIK = "*";
            String PERCENT = Constant.PERCENT;
            String marketTypeVal = StringUtils.EMPTY;
            String brandVal = StringUtils.EMPTY;
            String projNameVal = StringUtils.EMPTY;
            String contHldrVal = StringUtils.EMPTY;
            String ndcNoVal = StringUtils.EMPTY;
            String ndcNameVal = StringUtils.EMPTY;
            String descVal = StringUtils.EMPTY;
            String contractVal = StringUtils.EMPTY;
            boolean isProjectionStatus = false;
            Map<Object, String> columns = new HashMap<Object, String>();
            columns.put(Constant.PROJECTION_NAME, "pm.projection_name");
            columns.put(Constant.PROJECTIONDESCRIPTION, "pm.projection_description");
            columns.put("marketType", "market_type");
            columns.put("customer", Constant.CUSTOMER_SMALL);
            columns.put(Constant.CONTRACT, Constant.CONTRACT_SMALL);
            columns.put(Constant.BRAND, Constant.BRAND_CAPS);
            columns.put("createdDateFrom", "PM.created_date");
            columns.put("createdBy", "PM.created_by");

            if (comparisonLookupDTO.getWorkflowStatus().equals(Constant.SAVED)) {
                isProjectionStatus = true;
            }

            String workflowQuery = isProjectionStatus ? StringUtils.EMPTY : "INNER JOIN WORKFLOW_MASTER WM\n"
                    + "               ON PM.PROJECTION_MASTER_SID = WM.PROJECTION_MASTER_SID\n"
                    + "                      LEFT JOIN HELPER_TABLE HT1\n"
                    + "              ON HT1.HELPER_TABLE_SID = WM.WORKFLOW_STATUS_ID\n"
                    + "                 AND HT1.LIST_NAME = 'WORKFLOWSTATUS'";
            String replaceQuery = SQlUtil.getQuery("PVComparisonQuery");
            replaceQuery = replaceQuery.replace("?COUNT", isCount ? " SELECT COUNT(*) FROM ( " : StringUtils.EMPTY);
            replaceQuery = replaceQuery.replaceAll("\\?PROJECTIONMASTERSID", comparisonLookupDTO.getCurrentProjId());
            replaceQuery = replaceQuery.replace("?WORKFLOWJOIN", workflowQuery);

            StringBuilder customSql = new StringBuilder(replaceQuery);

            if (isProjectionStatus) {
                customSql.append(" PM.IS_APPROVED NOT IN ('Y','C','A','R')");
            } else {
                customSql.append(" HT1.list_name = 'WorkFlowStatus' and HT1.description = ").append(QUOTES).append(comparisonLookupDTO.getWorkflowStatus()).append(QUOTES);
            }

            if (isValidateSearchCriteria(comparisonLookupDTO.getMarketType())) {
                marketTypeVal = comparisonLookupDTO.getMarketType().replace(ASTERIK, PERCENT);
                marketTypeVal = QUOTES + marketTypeVal + QUOTES;
                customSql.append(" AND ( HT.list_name = 'CONTRACT_TYPE' AND HT.DESCRIPTION LIKE ").append(marketTypeVal).append(")");
            }
            if (isValidateSearchCriteria(comparisonLookupDTO.getBrand())) {
                brandVal = comparisonLookupDTO.getBrand().replace(ASTERIK, PERCENT);
                brandVal = QUOTES + brandVal + QUOTES;
                customSql.append("  AND (BM.BRAND_NAME LIKE ").append(brandVal).append(" or BM.BRAND_NAME is null)");
            }
            if (isValidateSearchCriteria(comparisonLookupDTO.getProjectionName())) {
                projNameVal = comparisonLookupDTO.getProjectionName().replace(ASTERIK, PERCENT);
                projNameVal = QUOTES + projNameVal + QUOTES;
                customSql.append(" AND (PM.PROJECTION_NAME LIKE ").append(projNameVal).append(" or PM.PROJECTION_NAME is null)");
            }
            if (isValidateSearchCriteria(comparisonLookupDTO.getCustomer())) {
                contHldrVal = comparisonLookupDTO.getCustomer().replace(ASTERIK, PERCENT);
                contHldrVal = QUOTES + contHldrVal + QUOTES;
                customSql.append(" AND (C.CONTRACT_NO LIKE ").append(contHldrVal).append(" or C.CONTRACT_NO is null)");
            }
            if (isValidateSearchCriteria(comparisonLookupDTO.getNdcName())) {
                ndcNameVal = comparisonLookupDTO.getNdcName().replace(ASTERIK, PERCENT);
                ndcNameVal = QUOTES + ndcNameVal + QUOTES;
                customSql.append(" AND (IM.ITEM_NAME LIKE ").append(ndcNameVal).append(" or IM.ITEM_NAME is null)");
            }
            if (isValidateSearchCriteria(comparisonLookupDTO.getNdcNo())) {
                ndcNoVal = comparisonLookupDTO.getNdcNo().replace(ASTERIK, PERCENT);
                ndcNoVal = QUOTES + ndcNoVal + QUOTES;
                customSql.append(" AND (IM.ITEM_NO LIKE ").append(ndcNoVal).append("or IM.ITEM_NO is null)");
            }
            if (isValidateSearchCriteria(comparisonLookupDTO.getContract())) {
                contractVal = comparisonLookupDTO.getContract().replace(ASTERIK, PERCENT);
                contractVal = QUOTES + contractVal + QUOTES;
                customSql.append(" AND (CM.COMPANY_NO LIKE ").append(contractVal).append("or CM.COMPANY_NO is null)");
            }
            if (isValidateSearchCriteria(comparisonLookupDTO.getProjectionDescription())) {
                descVal = comparisonLookupDTO.getProjectionDescription().replace(ASTERIK, PERCENT);
                descVal = QUOTES + descVal + QUOTES;
                customSql.append(" AND (PM.PROJECTION_DESCRIPTION LIKE ").append(descVal).append("or PM.PROJECTION_DESCRIPTION is null)");
            }

            if ((comparisonLookupDTO.getCreatedDateFrom() != null && !StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())) && !Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())))
                    && (comparisonLookupDTO.getCreatedDateTo() == null || StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())) || Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())))) {
                SimpleDateFormat format2 = new SimpleDateFormat(DATE_FORMAT.getConstant());
                customSql.append(" AND PM.CREATED_DATE >= '");
                customSql.append(format2.format(comparisonLookupDTO.getCreatedDateFrom()));
                customSql.append("' ");
            } else if ((comparisonLookupDTO.getCreatedDateTo() != null && !StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())) && !Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateTo())))
                    && (comparisonLookupDTO.getCreatedDateFrom() == null || StringUtils.EMPTY.equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())) || Constants.CommonConstants.NULL.getConstant().equals(String.valueOf(comparisonLookupDTO.getCreatedDateFrom())))) {
                SimpleDateFormat format2 = new SimpleDateFormat(DATE_FORMAT.getConstant());
                customSql.append(" AND PM.CREATED_DATE <= '");
                customSql.append(format2.format(format2.parse(comparisonLookupDTO.getCreatedDateTo())));
                customSql.append("' ");
            } else if (comparisonLookupDTO.getCreatedDateFrom() != null && !Constant.NULL.equals(comparisonLookupDTO.getCreatedDateFrom()) && comparisonLookupDTO.getCreatedDateFrom() != null
                    && comparisonLookupDTO.getCreatedDateTo() != null && !Constant.NULL.equals(comparisonLookupDTO.getCreatedDateTo()) && !StringUtils.isEmpty(comparisonLookupDTO.getCreatedDateTo())) {
                SimpleDateFormat format2 = new SimpleDateFormat(DATE_FORMAT.getConstant());
                customSql.append(" AND PM.CREATED_DATE BETWEEN '");
                customSql.append(format2.format(comparisonLookupDTO.getCreatedDateFrom()));
                customSql.append("' AND '");
                customSql.append(format2.format(format2.parse(comparisonLookupDTO.getCreatedDateTo())));
                customSql.append("' ");
            }
            //Filter
            if (parameters.get("filter~projectionName") != null) {
                customSql.append(" AND (PM.PROJECTION_NAME LIKE '").append(parameters.get("filter~projectionName")).append("') ");
            }
            if (parameters.get("filter~projectionDescription") != null) {
                customSql.append(" AND (PM.PROJECTION_DESCRIPTION LIKE '").append(parameters.get("filter~projectionDescription")).append("') ");
            }
            if (parameters.get("filter~marketType") != null) {
                customSql.append(" AND (HT.DESCRIPTION LIKE '").append(parameters.get("filter~marketType")).append("') ");
            }
            if (parameters.get("filter~contract") != null) {
                customSql.append(" AND (C.CONTRACT_NO LIKE '").append(parameters.get("filter~contract")).append("')");
            }
            if (parameters.get("filter~customer") != null) {
                customSql.append(" AND (CM.COMPANY_NO LIKE '").append(parameters.get("filter~customer")).append("') ");
            }
            if (parameters.get("filter~brand") != null) {
                String value = String.valueOf(parameters.get("filter~brand"));
                value = value.replace(Constant.PERCENT, StringUtils.EMPTY);
                if ("multiple".contains(value.toLowerCase())) {
                    customSql.append(" AND BM.BRAND_NAME IS NOT NULL ");
                } else {
                    customSql.append(" AND (BM.BRAND_NAME LIKE '").append(parameters.get("filter~brand")).append("') ");
                }
            }
            if (parameters.get("filter~Between~createdDateFrom~from") != null && parameters.get("filter~Between~createdDateFrom~to") != null) {
                customSql.append(" AND PM.CREATED_DATE BETWEEN '").append(parameters.get("filter~Between~createdDateFrom~from")).append("' AND '").append(parameters.get("filter~Between~createdDateFrom~to")).append("' ");
            }
            if (parameters.get("filter~GOE~createdDateFrom~from") != null) {
                customSql.append(" AND PM.CREATED_DATE > = '").append(parameters.get("filter~GOE~createdDateFrom~from")).append("' ");
            }
            if (parameters.get("filter~GOE~createdDateFrom~to") != null) {
                customSql.append(" AND PM.CREATED_DATE < = '").append(parameters.get("filter~GOE~createdDateFrom~to")).append("' ");
            }
            if (parameters.get("filter~createdBy") != null) {
                List<String> strList = new ArrayList<String>();
                final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
                Criterion criterion = RestrictionsFactoryUtil.ilike(Constant.FIRSTNAME, Constant.PERCENT + parameters.get("filter~createdBy") + Constant.PERCENT);
                Criterion criterion1 = RestrictionsFactoryUtil.ilike(Constant.LASTNAME, Constant.PERCENT + parameters.get("filter~createdBy") + Constant.PERCENT);
                dynamicQuery.add(RestrictionsFactoryUtil.or(criterion, criterion1));
                final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
                productProjectionList.add(ProjectionFactoryUtil.property(Constant.USER_ID));
                dynamicQuery.setProjection(productProjectionList);
                strList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
                String userID = CommonUtils.CollectionToString(strList, false);
                customSql.append(" AND (PM.CREATED_BY IN (").append(userID).append(") ) ");
            }
            customSql.append(" AND PM.FORECASTING_TYPE='").append(screenName).append("' ");
            customSql.append(" group by  pm.projection_name,pm.projection_description,pm.projection_master_sid,PM.created_date,PM.created_by ");

            if (isCount) {
                customSql.append(" ) RSLT ");
            } else {
                customSql.append(" ORDER BY ");

                if (sortColumns == null || sortColumns.isEmpty()) {
                    customSql.append(" pm.projection_name, pm.projection_description, PM.created_date, PM.created_by,  pm.projection_master_sid ");
                } else {
                    Boolean temp = Boolean.FALSE;
                    for (Iterator<SortByColumn> it = sortColumns.iterator(); it.hasNext();) {
                        SortByColumn sortByColumn = it.next();
                        if (temp) {
                            customSql.append(", ");
                            temp = Boolean.TRUE;
                        }
                        customSql.append(columns.get(sortByColumn.getName())).append(" ").append(sortByColumn.getType());
                    }
                }

                customSql.append(" OFFSET ");
                customSql.append(start);
                customSql.append(" ROWS FETCH NEXT ");
                customSql.append(offset);
                customSql.append(" ROWS ONLY ;");
            }

            return customSql.toString();
        } catch (ParseException | SystemException ex) {
            LOGGER.error(ex);
            return StringUtils.EMPTY;
        }
    }

    private boolean isValidateSearchCriteria(String dto) {
        if (dto == null || StringUtils.isBlank(dto)) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }
    
}
