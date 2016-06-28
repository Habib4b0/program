
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.common;

import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.copycontract.dto.CFPCompanyDTO;
import com.stpl.app.gcm.copycontract.dto.IFPItemDTO;
import com.stpl.app.gcm.copycontract.dto.PSIFPDTO;
import com.stpl.app.gcm.copycontract.dto.RsIfpDto;
import com.stpl.app.gcm.discount.dto.ContractsDetailsDto;
import com.stpl.app.gcm.discount.dto.LookupDTO;
//import com.stpl.apgcmnt.discount.dto.NewDiscountTabDto;
import com.stpl.app.gcm.discount.dto.RemoveDiscountDto;
import com.stpl.app.model.HelperTable;
import com.stpl.app.gcm.promotetptocontract.dto.ComponentInfoDTO;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.transfercontract.dto.ContractSearchDTO;
import com.stpl.app.gcm.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.gcm.util.Constants;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ComboBox;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;

/**
 *
 * @author vigneshkanna
 */
public class QueryUtils {

    public String rdMarketType = "select DESCRIPTION from HELPER_TABLE where LIST_NAME= 'CONTRACT_TYPE'";
    CommonUtils commonUtils = new CommonUtils();
    static HashMap<String, String> columnNames = new HashMap<String, String>();
    public static final SimpleDateFormat DBDate = new SimpleDateFormat(Constants.DBDATE_FORMAT);
    public static final char CHAR_PERCENT = '%';
    final SessionDTO sessiondto = new SessionDTO();
    Map<String, String> fieldMap = new HashMap<String, String>();
    private static final Logger LOGGER = Logger.getLogger(QueryUtils.class);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DBDATE_FORMAT);
    /**
     * The Constant CHAR_ASTERISK.
     */
    public static final char CHAR_ASTERISK = '*';

    /**
     * The Constant CHAR_PERCENT.
     */
    //This method will return the query for contract search in landing screem
    public String getDiscContract(CustomFieldGroup removeDiscountDto, Set<Container.Filter> filters, List<SortByColumn> sortByColumn) {
        String Query = searchQuery(removeDiscountDto);
        Map parameters = new HashMap();
        try {
            boolean check = false;
            parameters.put(Constants.IS_ORDERED, "false");
            for (Iterator<SortByColumn> iterator = sortByColumn.iterator(); iterator.hasNext();) {
                SortByColumn orderByColumn = (SortByColumn) iterator.next();
                String columnId = orderByColumn.getName();
                if (orderByColumn.getType() == SortByColumn.Type.ASC) {
                    parameters.put("orderBy~" + columnId, "asc");
                    parameters.put(Constants.IS_ORDERED, Constants.TRUE);
                } else {
                    parameters.put("orderBy~" + columnId, "desc");
                    parameters.put(Constants.IS_ORDERED, Constants.TRUE);
                }
            }

            if (getNull(removeDiscountDto.getField(Constants.CONTRACT_HOLDER).getValue().toString())) {
                String contractHolder = removeDiscountDto.getField(Constants.CONTRACT_HOLDER).getValue().toString();
                contractHolder = contractHolder.replace(CHAR_ASTERISK, CHAR_PERCENT);
                Query = Query + " AND CM.COMPANY_NAME like '" + contractHolder + "'";
            }
            if (getNull(String.valueOf(removeDiscountDto.getField(Constants.MARKET_TYPE).getValue()))
                    && !Constants.IndicatorConstants.SELECT_ONE.getConstant().equals(removeDiscountDto.getField(Constants.MARKET_TYPE).getValue().toString())) {
                String marketType = removeDiscountDto.getField(Constants.MARKET_TYPE).getValue().toString();

                marketType = marketType.replace(CHAR_ASTERISK, CHAR_PERCENT);
                Query = Query + " AND HT.DESCRIPTION like '" + marketType
                        + "' and HT.LIST_NAME = 'CONTRACT_TYPE'";
            }
            if (getNull(removeDiscountDto.getField(Constants.CFP_NAME).getValue().toString())) {
                String cfpName = removeDiscountDto.getField(Constants.CFP_NAME).getValue().toString();
                cfpName = cfpName.replace(CHAR_ASTERISK, CHAR_PERCENT);
                Query = Query + " AND CFC.CFP_NAME like '" + cfpName + "'";

            }
            if (getNull(removeDiscountDto.getField(Constants.CONTRACT_NO).getValue().toString())) {
                String contractNo = removeDiscountDto.getField(Constants.CONTRACT_NO).getValue().toString();
                contractNo = contractNo.replace(CHAR_ASTERISK, CHAR_PERCENT);
                Query = Query + " AND CN.CONTRACT_NO like '" + contractNo + "'";

            }
            if (getNull(String.valueOf(removeDiscountDto.getField("contractstartDate").getValue()))) {
                Date startDate = (Date) removeDiscountDto.getField("contractstartDate").getValue();
                Query = Query + " AND CN.START_DATE = '" + DBDate.format(startDate) + "'";

            }
            if (getNull(String.valueOf(removeDiscountDto.getField("contractendDate").getValue()))) {
                Date endDate = (Date) removeDiscountDto.getField("contractendDate").getValue();
                Query = Query + " AND CN.END_DATE = '" + DBDate.format(endDate) + "'";

            }
            if (getNull(removeDiscountDto.getField(Constants.IFPNAME).getValue().toString())) {
                String ifpName = removeDiscountDto.getField(Constants.IFPNAME).getValue().toString();
                ifpName = ifpName.replace(CHAR_ASTERISK, CHAR_PERCENT);
                Query = Query + " AND IFC.IFP_NAME like '" + ifpName + "'";

            }
            if (getNull(removeDiscountDto.getField(Constants.CONTRACT_NAME).getValue().toString())) {
                String contractName = removeDiscountDto.getField(Constants.CONTRACT_NAME).getValue().toString();
                contractName = contractName.replace(CHAR_ASTERISK, CHAR_PERCENT);
                Query = Query + " AND CN.CONTRACT_NAME like '" + contractName + "'";

            }
            if (getNull(removeDiscountDto.getField(Constants.PSNAME).getValue().toString())) {
                String psName = removeDiscountDto.getField(Constants.PSNAME).getValue().toString();
                psName = psName.replace(CHAR_ASTERISK, CHAR_PERCENT);
                Query = Query + " AND PSC.PS_NAME like '" + psName + "'";

            }

            if (getNull(removeDiscountDto.getField(Constants.RSNAME).getValue().toString())) {
                String rsName = removeDiscountDto.getField(Constants.RSNAME).getValue().toString();
                rsName = rsName.replace(CHAR_ASTERISK, CHAR_PERCENT);
                Query = Query + " AND RSC.RS_NAME like '" + rsName + "'";

            }

            if (getNull(String.valueOf(removeDiscountDto.getField("aliastype").getValue()))
                    && !Constants.IndicatorConstants.SELECT_ONE.getConstant().equals(removeDiscountDto.getField("aliastype").getValue().toString())) {
                String aliesType = String.valueOf(removeDiscountDto.getField("aliastype").getValue());
                Query = Query + "AND CAM.CONTRACT_ALIAS_TYPE like '" + aliesType + "'";

            }

            if (getNull(String.valueOf(removeDiscountDto.getField("aliasnumber").getValue()))
                    && !Constants.IndicatorConstants.SELECT_ONE.getConstant().equals(removeDiscountDto.getField("aliasnumber").getValue().toString())) {
                String aliasnumber = removeDiscountDto.getField("aliasnumber").getValue().toString();
                aliasnumber = aliasnumber.replace(CHAR_ASTERISK, CHAR_PERCENT);
                Query = Query + "AND CAM.CONTRACT_ALIAS_NO like '" + aliasnumber + "'";
            }

            if (getNull(String.valueOf(removeDiscountDto.getField("aliasStartDate").getValue()))) {
                Date startDate = (Date) removeDiscountDto.getField("aliasStartDate").getValue();
                Query = Query + " AND CAM.START_DATE = '" + DBDate.format(startDate) + "'";
            }

            if (getNull(String.valueOf(removeDiscountDto.getField("aliasEndDate").getValue()))) {
                Date aliasEndDate = (Date) removeDiscountDto.getField("aliasEndDate").getValue();
                Query = Query + " AND CAM.END_DATE = '" + DBDate.format(aliasEndDate) + "'";
            }
            if (!filters.isEmpty()) {
                for (Container.Filter filter : filters) {
                    if (filter instanceof SimpleStringFilter) {

                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterString = "%" + stringFilter.getFilterString() + "%";
                        if (Constants.CONTRACT_HOLDER.equals(stringFilter.getPropertyId())) {
                            Query = Query + " AND CM.COMPANY_NAME like '" + filterString + "'";
                        }
                        if (Constants.CONTRACT_NO.equals(stringFilter.getPropertyId())) {
                            Query = Query + " AND CN.CONTRACT_NO like '" + filterString + "'";
                        }
                        if (Constants.CFP_NAME.equals(stringFilter.getPropertyId())) {
                            Query = Query + " AND CFC.CFP_NAME like '" + filterString + "'";
                        }
                        if (Constants.CONTRACT_NAME.equals(stringFilter.getPropertyId())) {
                            Query = Query + " AND CN.CONTRACT_NAME like '" + filterString + "'";
                        }
                        if (Constants.MARKET_TYPE.equals(stringFilter.getPropertyId())) {
                            Query = Query + " AND HT.DESCRIPTION like '" + filterString
                                    + "' and HT.LIST_NAME = 'CONTRACT_TYPE'";
                        }
                        if (Constants.START_DATE.equals(stringFilter.getPropertyId())) {
                            Query = Query + " AND CN.START_DATE = '" + DBDate.format(filterString) + "'";
                        }
                        if (Constants.END_DATE.equals(stringFilter.getPropertyId())) {
                            Query = Query + " AND CN.END_DATE = '" + DBDate.format(filterString) + "'";
                        }
                        if (Constants.IFPNAME.equals(stringFilter.getPropertyId())) {
                            Query = Query + " AND IFC.IFP_NAME like '" + filterString + "'";
                        }
                        if (Constants.PSNAME.equals(stringFilter.getPropertyId())) {
                            Query = Query + " AND PSC.PS_NAME like '" + filterString + "'";
                        }
                        if (Constants.RSNAME.equals(stringFilter.getPropertyId())) {
                            Query = Query + " AND RSC.RS_NAME like '" + filterString + "'";
                        }
                    }
                }
            }
            Query = Query + " GROUP BY CM.COMPANY_NAME,CN.CONTRACT_NO,CN.CONTRACT_NAME,HT.DESCRIPTION,\n"
                    + " CN.START_DATE,CN.END_DATE,\n"
                    + " CFC.CFP_NAME,IFC.IFP_NAME,PSC.PS_NAME,RSC.RS_NAME,\n"
                    + " CN.CONTRACT_MASTER_SID,CFC.CFP_MODEL_SID,\n"
                    + " IFC.IFP_MODEL_SID,PSC.PS_MODEL_SID,\n"
                    + " RSC.RS_CONTRACT_SID,CM.COMPANY_MASTER_SID,CN.CONTRACT_ID,CN_ST.DESCRIPTION  ORDER BY  ";
            if ((parameters.get(Constants.IS_ORDERED) == null || "false".equalsIgnoreCase(String.valueOf(parameters.get(Constants.IS_ORDERED))))) {
                Query += "CN.CONTRACT_MASTER_SID ";
            } else if (parameters.get(Constants.IS_ORDERED) != null && Constants.TRUE.equalsIgnoreCase(String.valueOf(parameters.get(Constants.IS_ORDERED)))) {
                if (parameters.get("orderBy~contractHolder") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~contractHolder"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~contractHolder")))) {
                    Query += " CM.COMPANY_NAME ";
                    Query += String.valueOf(parameters.get("orderBy~contractHolder"));
                }
                if (parameters.get("orderBy~contractNo") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~contractNo"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~contractNo")))) {
                    Query += " CN.CONTRACT_NO ";
                    Query += String.valueOf(parameters.get("orderBy~contractNo"));
                }
                if (parameters.get("orderBy~cfpName") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~cfpName"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~cfpName")))) {
                    Query += " CFC.CFP_NAME ";
                    Query += String.valueOf(parameters.get("orderBy~cfpName"));
                }
                if (parameters.get("orderBy~contractName") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~contractName"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~contractName")))) {
                    Query += " CN.CONTRACT_NAME ";
                    Query += String.valueOf(parameters.get("orderBy~contractName"));
                }
                if (parameters.get("orderBy~marketType") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~marketType"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~marketType")))) {
                    Query += " HT.DESCRIPTION ";
                    Query += String.valueOf(parameters.get("orderBy~marketType"));
                }
                if (parameters.get("orderBy~startDate") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~startDate"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~startDate")))) {
                    Query += " CN.START_DATE ";
                    Query += String.valueOf(parameters.get("orderBy~startDate"));
                }
                if (parameters.get("orderBy~endDate") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~endDate"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~endDate")))) {
                    Query += " CN.END_DATE ";
                    Query += String.valueOf(parameters.get("orderBy~endDate"));
                }
                if (parameters.get("orderBy~ifpName") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~ifpName"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~ifpName")))) {
                    Query += " IFC.IFP_NAME ";
                    Query += String.valueOf(parameters.get("orderBy~ifpName"));
                }
                if (parameters.get("orderBy~psName") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~psName"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~psName")))) {
                    Query += " PSC.PS_NAME ";
                    Query += String.valueOf(parameters.get("orderBy~psName"));
                }
                if (parameters.get("orderBy~rsName") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~rsName"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~rsName")))) {
                    Query += " RSC.RS_NAME ";
                    Query += String.valueOf(parameters.get("orderBy~rsName"));
                }
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return Query;
    }

    public boolean getNull(String value) {
        boolean check = false;
        if (!StringUtils.EMPTY.equals(value) && !Constants.NULL.equals(value) && value != null) {
            check = true;
        }
        return check;
    }

    public String getLatestCCPQuery(List contractSid, List rsSid) {
        commonUtils.CollectionToString(contractSid, true);
        String query = "select top 1 PROJECTION_MASTER_SID,FORECASTING_TYPE from PROJECTION_MASTER where PROJECTION_MASTER_SID in\n"
                + " (select distinct PD.PROJECTION_MASTER_SID  from PROJECTION_DETAILS PD\n"
                + "JOIN CCP_DETAILS CCP ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID AND\n"
                + "CCP.CONTRACT_MASTER_SID IN (" + commonUtils.CollectionToString(contractSid, true) + ") \n"
                + "JOIN RS_CONTRACT RSC ON RSC.CONTRACT_MASTER_SID = CCP.CONTRACT_MASTER_SID\n"
                + "AND RSC.RS_CONTRACT_SID in (" + commonUtils.CollectionToString(rsSid, true) + ")\n"
                + "JOIN RS_CONTRACT_DETAILS RSD ON RSC.RS_CONTRACT_SID = RSD.RS_CONTRACT_SID\n"
                + "AND RSD.ITEM_MASTER_SID = CCP.ITEM_MASTER_SID\n"
                + "JOIN WORKFLOW_MASTER WM ON PD.PROJECTION_MASTER_SID = WM.PROJECTION_MASTER_SID\n"
                + "JOIN HELPER_TABLE HT ON WM.WORKFLOW_STATUS_ID = HT.HELPER_TABLE_SID \n"
                + "AND HT.DESCRIPTION = 'Approved' AND HT.LIST_NAME = 'WorkFlowStatus'\n"
                + ") and IS_APPROVED='A' and FORECASTING_TYPE <> 'Channel' order by MODIFIED_DATE desc";
        return query;
    }

    public String getSummaryQuery(RemoveDiscountDto dto) {

        String select = StringUtils.EMPTY;
        String where = StringUtils.EMPTY;
        String groupBy = StringUtils.EMPTY;
        String orderBy = StringUtils.EMPTY;
        String prjQuery = StringUtils.EMPTY;

        if ("Channel".equals(dto.getForecastingType())) {
            prjQuery = " join CH_SALES_PROJECTION NMSP \n";
        } else if (Constants.IndicatorConstants.MANDATED.toString().equals(dto.getForecastingType())) {
            prjQuery = " join M_SALES_PROJECTION NMSP \n";
        } else if (Constants.IndicatorConstants.NON_MANDATED.toString().equals(dto.getForecastingType())) {
            prjQuery = " join NM_SALES_PROJECTION NMSP \n";
        }

        if (dto.getLevelNo() == 1) {
            select = "CM.COMPANY_NAME";
            where = " join CONTRACT_MASTER CTM ON CCPD.CONTRACT_MASTER_SID = CTM.CONTRACT_MASTER_SID\n "
                    + " join COMPANY_MASTER CM ON CTM.CONT_HOLD_COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID\n ";
            groupBy = " CM.COMPANY_NAME " + groupBy;

        } else if (dto.getLevelNo() == 2) {
            select = "CM.CONTRACT_NAME";
            where = " join CONTRACT_MASTER CM ON CCPD.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n ";
            groupBy = " CM.CONTRACT_NAME " + groupBy;
        } else if (dto.getLevelNo() == 3) {
            select = "RM.RS_NAME";
            where = " join RS_CONTRACT RM ON CCPD.CONTRACT_MASTER_SID = RM.CONTRACT_MASTER_SID\n"
                    + "AND RM.RS_CONTRACT_SID in ( " + dto.getRsContractSid() + ") \n ";
            groupBy = " RM.RS_NAME " + groupBy;
        } else if (dto.getLevelNo() == 4) {
            select = "IM.ITEM_NAME";
            where = " join ITEM_MASTER IM ON CCPD.ITEM_MASTER_SID = IM.ITEM_MASTER_SID\n ";
            groupBy = " IM.ITEM_NAME " + groupBy;
        }

        orderBy = " ORDER BY LEVEL_NAME ";
        String query = "select " + select + " as LEVEL_NAME,I.\"YEAR\" as YEARS,"
                + " I.QUARTER as PERIODS,\n"
                + "sum(NMSP.PROJECTION_SALES) as Sales,sum(NMSP.PROJECTION_UNITS) as Units, "
                + (dto.getLevelNo()) + " AS LEVEL_NO,DENSE_RANK() OVER (ORDER BY " + select + ") AS ROW_NUM  from \n"
                + " PROJECTION_DETAILS PD\n"
                + "join CCP_DETAILS CCPD ON CCPD.CCP_DETAILS_SID=PD.CCP_DETAILS_SID \n"
                + "AND CCPD.CONTRACT_MASTER_SID=" + dto.getContractSid() + "\n"
                + prjQuery
                + "ON  NMSP.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
                + where
                + "join \"PERIOD\" I on I.PERIOD_SID = NMSP.PERIOD_SID \n"
                + "WHERE PD.PROJECTION_MASTER_SID= " + dto.getProjectionSid() + " \n"
                + "AND I.PERIOD_DATE  BETWEEN CONVERT(DATE, '" + DBDate.format(dto.getFromDate())
                + "') AND CONVERT(DATE, '" + DBDate.format(dto.getToDate()) + "')"
                + "group by I.\"YEAR\", I.QUARTER," + groupBy;

        String finalQuery = "select LEVEL_NAME,YEARS,PERIODS,Sales,Units,LEVEL_NO from(" + query + ") MAINQ where MAINQ.ROW_NUM>" + dto.getStartIndex() + " AND MAINQ.ROW_NUM<=" + (dto.getStartIndex() + dto.getOffSet()) + " " + orderBy;
        return finalQuery;
    }

    public String getSummaryCountQuery(RemoveDiscountDto dto) {
        LOGGER.info("Entering getSummaryCountQuery" + dto.getFromDate() + dto.getToDate());
        String select = StringUtils.EMPTY;
        String where = StringUtils.EMPTY;
        String prjQuery = StringUtils.EMPTY;
        String groupBy = StringUtils.EMPTY;

        if ("Channel".equals(dto.getForecastingType())) {
            prjQuery = " join CH_SALES_PROJECTION NMSP \n";
        } else if (Constants.IndicatorConstants.MANDATED.equals(dto.getForecastingType())) {
            prjQuery = " join M_SALES_PROJECTION NMSP \n";
        } else if (Constants.IndicatorConstants.NON_MANDATED.equals(dto.getForecastingType())) {
            prjQuery = " join NM_SALES_PROJECTION NMSP \n";
        }
        if (dto.getLevelNo() == 1) {
            select = "CM.COMPANY_NAME ";
            where = " join CONTRACT_MASTER CTM ON CCPD.CONTRACT_MASTER_SID = CTM.CONTRACT_MASTER_SID\n "
                    + " join COMPANY_MASTER CM ON CTM.CONT_HOLD_COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID\n ";
            groupBy = " CM.COMPANY_NAME ";
        } else if (dto.getLevelNo() == 2) {
            select = "CM.CONTRACT_NAME ";
            where = " join CONTRACT_MASTER CM ON CCPD.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID\n ";
            groupBy = " CM.CONTRACT_NAME ";
        } else if (dto.getLevelNo() == 3) {

            select = "RM.RS_NAME ";
            where = " join RS_CONTRACT RM ON CCPD.CONTRACT_MASTER_SID = RM.CONTRACT_MASTER_SID\n"
                    + "AND RM.RS_CONTRACT_SID in ( " + dto.getRsContractSid() + ") \n ";
            groupBy = " RM.RS_NAME ";
        } else if (dto.getLevelNo() == 4) {
            select = "IM.ITEM_NAME ";
            where = " join ITEM_MASTER IM ON CCPD.ITEM_MASTER_SID = IM.ITEM_MASTER_SID\n ";
            groupBy = " IM.ITEM_NAME ";
        }

        String query = "select count ( DISTINCT " + select + ")  \n"
                + "from \n"
                + " PROJECTION_DETAILS PD\n"
                + "join CCP_DETAILS CCPD ON CCPD.CCP_DETAILS_SID=PD.CCP_DETAILS_SID \n"
                + "AND CCPD.CONTRACT_MASTER_SID=" + dto.getContractSid() + "\n"
                + where
                + "WHERE PD.PROJECTION_MASTER_SID= " + dto.getProjectionSid() + " \n";

        return query;
    }

    public String getForecastDates(String type) {
        String forecastDates = "SELECT TOP 1 FROM_DATE,TO_DATE FROM FORECAST_CONFIG FC JOIN dbo.HELPER_TABLE HT ON HT.HELPER_TABLE_SID = FC.BUSINESS_PROCESS_TYPE \n" +
         " WHERE ht.DESCRIPTION ='"+type + "'ORDER BY VERSION_NO DESC";
        return forecastDates;
    }
    
    public String getCFPcount(CFPCompanyDTO CFPCompanyDTO, BeanSearchCriteria bsc) {
        String CFPid = StringUtils.EMPTY;
        String CFPName = StringUtils.EMPTY;
        String CFPType = StringUtils.EMPTY;
        String CFPStatus = StringUtils.EMPTY;
        String CFPNO = StringUtils.EMPTY;
        String Query = "select DISTINCT cfp.CFP_ID, cfp.CFP_NO, cfp.CFP_NAME, cfp.CFP_TYPE, cfp.CFP_STATUS, cfp.CFP_START_DATE, cfp.CFP_END_DATE, cfp.CFP_TRADE_CLASS, cfp.CFP_CATEGORY,\n"
                + "cfp.CFP_DESIGNATION,cfp.PARENT_CFP_NAME,parenCfp.CFP_ID as PARENT_ID,htype.DESCRIPTION as ctype,hstatus.DESCRIPTION as cstatus,htrade.DESCRIPTION as trade,\n"
                + "hcategory.DESCRIPTION as category,cfp.CFP_MODEL_SID from CFP_MODEL cfp \n"
                + "LEFT JOIN CFP_MODEL parenCfp on parenCfp.CFP_MODEL_SID = cfp.PARENT_CFP_ID"
                + " LEFT JOIN HELPER_TABLE htrade on htrade.HELPER_TABLE_SID =  cfp.CFP_TRADE_CLASS "
                + " LEFT JOIN HELPER_TABLE hcategory on hcategory.HELPER_TABLE_SID =  cfp.CFP_CATEGORY "
                + " LEFT JOIN HELPER_TABLE htype on htype.HELPER_TABLE_SID =  cfp.CFP_TYPE "
                + " LEFT JOIN HELPER_TABLE hstatus on hstatus.HELPER_TABLE_SID =  cfp.CFP_STATUS "
                + " JOIN CFP_DETAILS cfd on cfp.CFP_MODEL_SID=cfd.CFP_MODEL_SID  where cfp.INBOUND_STATUS <> 'D'";

        if (CFPCompanyDTO.getCompanyFamilyPlanId().toString() != StringUtils.EMPTY) {
            CFPid = CFPCompanyDTO.getCompanyFamilyPlanId().toString();
            Query = Query + " and  cfp.CFP_ID like '" + CFPid.replace(CHAR_ASTERISK, CHAR_PERCENT) + "'";

        }
        if (CFPCompanyDTO.getCompanyFamilyPlanNo().toString() != StringUtils.EMPTY) {
            CFPNO = CFPCompanyDTO.getCompanyFamilyPlanNo().toString();
            Query = Query + " and  cfp.CFP_NO like '" + CFPNO.replace(CHAR_ASTERISK, CHAR_PERCENT) + "'";

        }
        if (CFPCompanyDTO.getCompanyFamilyPlanName().toString() != StringUtils.EMPTY) {
            CFPName = CFPCompanyDTO.getCompanyFamilyPlanName().toString();
            Query = Query + " and  cfp.CFP_NAME like '" + CFPName.replace(CHAR_ASTERISK, CHAR_PERCENT) + "'";

        }

        if (CFPCompanyDTO.getCompanyFamilyPlanTypeValue().toString() != StringUtils.EMPTY) {
            CFPType = CFPCompanyDTO.getCompanyFamilyPlanTypeValue().toString();
            Query = Query + " and  cfp.CFP_TYPE=" + CFPType;

        }
        if (CFPCompanyDTO.getCompanyFamilyPlanStatusValue().toString() != StringUtils.EMPTY) {
            CFPStatus = CFPCompanyDTO.getCompanyFamilyPlanStatusValue().toString();
            Query = Query + " and  cfp.CFP_STATUS=" + CFPStatus;

        }

        return Query;
    }

    public String getCFPAttachedCompanies(CFPCompanyDTO CFPCompanyDTO, BeanSearchCriteria bsc) {
        int sidvalue = CFPCompanyDTO.getCompanyFamilyPlanSystemId();
        String Query = "select DISTINCT cm.COMPANY_ID,cm.COMPANY_NO,cm.COMPANY_NAME,cm.COMPANY_START_DATE,cm.COMPANY_END_DATE,hstatus.DESCRIPTION as cstatus from CFP_MODEL cfp"
        
                + " JOIN CFP_DETAILS cfd on cfp.CFP_MODEL_SID=cfd.CFP_MODEL_SID \n"
                + " JOIN COMPANY_MASTER cm on cfd.COMPANY_MASTER_SID=cm.COMPANY_MASTER_SID"
                + " JOIN HELPER_TABLE hstatus on hstatus.HELPER_TABLE_SID =  cm.COMPANY_STATUS "
                + "  where cfp.INBOUND_STATUS <> 'D' and cfd.CFP_MODEL_SID=" + sidvalue + StringUtils.EMPTY;
        return Query;
    }

    public static String getDbColumnNames(String visibleColumnName) {
        return columnNames.get(visibleColumnName);
    }

    public static HashMap<String, String> loadColumnNames() {
        columnNames.put(" contractHolder", "CM.COMPANY_NAME");
        columnNames.put(" contractNo", "CN.CONTRACT_NO");
        columnNames.put(" contractName", "CN.CONTRACT_NAME");
        columnNames.put(" marketType", "CN.CONTRACT_TYPE");
        columnNames.put(" startDate", "CN.START_DATE");
        columnNames.put(" endDate", "CN.END_DATE");
        columnNames.put(" cfpName", "CFC.CFP_NAME");
        columnNames.put(" ifpName", "IFC.IFP_NAME");
        columnNames.put(" psName", "PSC.PS_NAME");
        columnNames.put(" rsName", "RSC.RS_NAME");
        columnNames.put("RS Name", "RS_NAME");
        columnNames.put("RS ID", "RS_ID");
        columnNames.put("RS No", "RS_NO");
        columnNames.put("RS Status", "RS_STATUS");
        columnNames.put("RS Type", "RS_TYPE");

        return columnNames;
    }

    public String GetIFPAttachedItems(IFPItemDTO IFPItemDTO, BeanSearchCriteria bsc) {
        int sidvalue = IFPItemDTO.getIfpDetailsSystemId();
        String sql = "SELECT DISTINCT im.ITEM_NO,im.ITEM_NAME,theclass.DESCRIPTION as tclass,bm.BRAND_NAME,im.ITEM_START_DATE,im.ITEM_END_DATE,\n"
                + " itemsatus.DESCRIPTION "
                + "                 from IFP_MODEL ifp JOIN IFP_DETAILS ifd on ifp.IFP_MODEL_SID=ifd.IFP_MODEL_SID JOIN ITEM_MASTER im on ifd.ITEM_MASTER_SID=im.ITEM_MASTER_SID\n"
                + "                LEFT JOIN HELPER_TABLE htype on htype.HELPER_TABLE_SID =  ifp.IFP_TYPE​\n"
                + "                LEFT JOIN HELPER_TABLE hstatus on hstatus.HELPER_TABLE_SID =  ifp.IFP_STATUS\n"
                + "            LEFT JOIN HELPER_TABLE hcategory on hcategory.HELPER_TABLE_SID =  ifp.IFP_CATEGORY\n"
                + "                LEFT JOIN HELPER_TABLE hdegin on hdegin.HELPER_TABLE_SID =  ifp.IFP_DESIGNATION\n"
                + "                 LEFT JOIN HELPER_TABLE theclass on theclass.HELPER_TABLE_SID=im.THERAPEUTIC_CLASS\n"
                + "          LEFT JOIN BRAND_MASTER bm on bm.BRAND_MASTER_SID=im.BRAND_MASTER_SID\n"
                + " LEFT JOIN HELPER_TABLE itemsatus on itemsatus.HELPER_TABLE_SID =  im.ITEM_STATUS\n"
                + "                WHERE ifp.INBOUND_STATUS <> 'D' and ifd.IFP_MODEL_SID=" + sidvalue + StringUtils.EMPTY
                + StringUtils.EMPTY;
        return sql;
    }

    public String getIFPcount(IFPItemDTO IFPItemDTO, BeanSearchCriteria bsc) {
        String IFPid = StringUtils.EMPTY;
        String IFPName = StringUtils.EMPTY;
        String IFPType = StringUtils.EMPTY;
        String IFPStatus = StringUtils.EMPTY;
        String IFPNO = StringUtils.EMPTY;

        String sql = "SELECT DISTINCT ifp.IFP_MODEL_SID,ifp.IFP_ID,ifp.IFP_NO, ifp.IFP_NAME,ifp.IFP_STATUS,"
                + "              ifp.IFP_TYPE,ifp.IFP_CATEGORY, ifp.IFP_START_DATE, ifp.IFP_END_DATE,ifp.IFP_DESIGNATION,"
                + "               ifp.PARENT_IFP_ID,ifp.PARENT_IFP_NAME,ifp.TOTAL_DOLLAR_COMMITMENT,ifp.COMMITMENT_PERIOD,ifp.TOTAL_VOLUME_COMMITMENT,"
                + "             ifp.TOTAL_MARKETSHARE_COMMITMENT, ifp.CREATED_BY,ifp.CREATED_DATE, htype.DESCRIPTION as itype,hstatus.DESCRIPTION as istatus,"
                + "                hcategory.DESCRIPTION as icategory,hdegin.DESCRIPTION as idesign,ifp.RECORD_LOCK_STATUS"
              
                + "                 from IFP_MODEL ifp JOIN IFP_DETAILS ifd on ifp.IFP_MODEL_SID=ifd.IFP_MODEL_SID "
                + " JOIN ITEM_MASTER im on ifd.ITEM_MASTER_SID=im.ITEM_MASTER_SID"
                + "                LEFT JOIN HELPER_TABLE htype on htype.HELPER_TABLE_SID =  ifp.IFP_TYPE​"
                + "                LEFT JOIN HELPER_TABLE hstatus on hstatus.HELPER_TABLE_SID =  ifp.IFP_STATUS"
                + "            LEFT JOIN HELPER_TABLE hcategory on hcategory.HELPER_TABLE_SID =  ifp.IFP_CATEGORY"
                + "                LEFT JOIN HELPER_TABLE hdegin on hdegin.HELPER_TABLE_SID =  ifp.IFP_DESIGNATION"
              
                + "                WHERE ifp.INBOUND_STATUS <> 'D'";
    
        if (IFPItemDTO.getItemFamilyplanId().toString() != StringUtils.EMPTY) {
            IFPid = IFPItemDTO.getItemFamilyplanId().toString();
            sql = sql + " and  ifp.IFP_ID like '" + IFPid.replace(CHAR_ASTERISK, CHAR_PERCENT) + "'";

        }
        if (IFPItemDTO.getItemFamilyplanNo().toString() != StringUtils.EMPTY) {
            IFPNO = IFPItemDTO.getItemFamilyplanNo().toString();
            sql = sql + " and  ifp.IFP_NO like '" + IFPNO.replace(CHAR_ASTERISK, CHAR_PERCENT) + "'";

        }
        if (IFPItemDTO.getItemFamilyplanName().toString() != StringUtils.EMPTY) {
            IFPName = IFPItemDTO.getItemFamilyplanName().toString();
            sql = sql + " and  ifp.IFP_NAME like '" + IFPName.replace(CHAR_ASTERISK, CHAR_PERCENT) + "'";

        }
        if (IFPItemDTO.getIFPtype() != 0) {
            IFPType = IFPItemDTO.getIFPtype().toString();
            sql = sql + " and   ifp.IFP_TYPE=" + IFPType;

        }
        if (IFPItemDTO.getItemFamilyplanStatus() != 0) {
            IFPStatus = IFPItemDTO.getItemFamilyplanStatus().toString();
            sql = sql + " and  ifp.IFP_STATUS=" + IFPStatus;

        }

        return sql;
    }

    public String getPScount(PSIFPDTO PSIFPDTO, BeanSearchCriteria bsc) {
        String Pid = StringUtils.EMPTY;
        String PName = StringUtils.EMPTY;
        String PType = StringUtils.EMPTY;
        String PStatus = StringUtils.EMPTY;
        String PNO = StringUtils.EMPTY;
        String sql = "SELECT DISTINCT ps.PS_MODEL_SID,ps.PS_ID,ps.PS_NO,ps.PS_NAME,ps.PS_TYPE,ps.PS_STATUS,ps.PS_CATEGORY,ps.PS_START_DATE,ps.PS_END_DATE,"
                + "ps.PS_DESIGNATION,ps.PARENT_PS_ID,ps.PARENT_PS_NAME,ps.PS_TRADE_CLASS,htype.DESCRIPTION as type,hstatus.DESCRIPTION as status,"
                + "hcategory.DESCRIPTION as category,hdesign.DESCRIPTION as designation,htrade.DESCRIPTION as trade,ps.RECORD_LOCK_STATUS,ifp.IFP_NAME,ifp.IFP_MODEL_SID"
                + "  FROM PS_MODEL ps"
                + " JOIN PS_DETAILS psd on ps.PS_MODEL_SID=psd.PS_MODEL_SID"
                + "  JOIN IFP_MODEL ifp on ifp.IFP_MODEL_SID=psd.IFP_MODEL_SID"
                + " JOIN ITEM_MASTER im on psd.ITEM_MASTER_SID=im.ITEM_MASTER_SID"
                + " LEFT JOIN HELPER_TABLE htype on htype.HELPER_TABLE_SID=ps.PS_TYPE"
                + " LEFT JOIN HELPER_TABLE hstatus on hstatus.HELPER_TABLE_SID=ps.PS_STATUS"
                + " LEFT JOIN HELPER_TABLE hcategory on hcategory.HELPER_TABLE_SID=ps.PS_CATEGORY"
                + " LEFT JOIN HELPER_TABLE hdesign on hdesign.HELPER_TABLE_SID=ps.PS_DESIGNATION"
                + " LEFT JOIN HELPER_TABLE htrade on htrade.HELPER_TABLE_SID=ps.PS_TRADE_CLASS"
                + " WHERE ps.INBOUND_STATUS <> 'D' ";
        if (PSIFPDTO.getPriceScheduleIdValue().toString() != StringUtils.EMPTY) {
            Pid = PSIFPDTO.getPriceScheduleIdValue().toString();
            sql = sql + " and  ps.PS_ID like '" + Pid.replace(CHAR_ASTERISK, CHAR_PERCENT) + "'";

        }
        if (PSIFPDTO.getPriceScheduleNoValue().toString() != StringUtils.EMPTY) {
            PNO = PSIFPDTO.getPriceScheduleNoValue().toString();
            sql = sql + " and  ps.PS_NO like '" + PNO.replace(CHAR_ASTERISK, CHAR_PERCENT) + "'";

        }
        if (PSIFPDTO.getPriceScheduleNameValue().toString() != StringUtils.EMPTY) {
            PName = PSIFPDTO.getPriceScheduleNameValue().toString();
            sql = sql + " and  ps.PS_NAME like '" + PName.replace(CHAR_ASTERISK, CHAR_PERCENT) + "'";

        }
        if (PSIFPDTO.getPtype() != 0) {
            PType = PSIFPDTO.getPtype().toString();
            sql = sql + " and  ps.PS_TYPE=" + PType;

        }
        if (PSIFPDTO.getPStatus() != 0) {
            PStatus = PSIFPDTO.getPStatus().toString();
            sql = sql + " and  ps.PS_STATUS=" + PStatus;

        }

        return sql;
    }

    public String getPSItemcount(PSIFPDTO PSIFPDTO, BeanSearchCriteria bsc) {
        String psSid = PSIFPDTO.getPriceScheduleSystemId();
        String sql = "SELECT DISTINCT  im.ITEM_NO,im.ITEM_NAME,theclass.DESCRIPTION as tclass,bm.BRAND_NAME,im.ITEM_START_DATE,im.ITEM_END_DATE,itemsatus.DESCRIPTION,IPQ.ITEM_PRICING_QUALIFIER_NAME,psd.PRICE_PROTECTION_START_DATE"
                + " FROM PS_MODEL ps"
                + " JOIN PS_DETAILS psd on ps.PS_MODEL_SID=psd.PS_MODEL_SID"
                + "  JOIN IFP_MODEL ifp on ifp.IFP_MODEL_SID=psd.IFP_MODEL_SID"
                + " JOIN ITEM_MASTER im on psd.ITEM_MASTER_SID=im.ITEM_MASTER_SID"
                + " LEFT JOIN HELPER_TABLE htype on htype.HELPER_TABLE_SID=ps.PS_TYPE"
                + " LEFT JOIN HELPER_TABLE hstatus on hstatus.HELPER_TABLE_SID=ps.PS_STATUS"
                + " LEFT JOIN HELPER_TABLE hcategory on hcategory.HELPER_TABLE_SID=ps.PS_CATEGORY"
                + " LEFT JOIN HELPER_TABLE hdesign on hdesign.HELPER_TABLE_SID=ps.PS_DESIGNATION"
                + " LEFT JOIN HELPER_TABLE htrade on htrade.HELPER_TABLE_SID=ps.PS_TRADE_CLASS"
                + "                 LEFT JOIN HELPER_TABLE theclass on theclass.HELPER_TABLE_SID=im.THERAPEUTIC_CLASS \n"
                + "          LEFT JOIN BRAND_MASTER bm on bm.BRAND_MASTER_SID=im.BRAND_MASTER_SID \n"
                + " LEFT JOIN HELPER_TABLE itemsatus on itemsatus.HELPER_TABLE_SID =  im.ITEM_STATUS \n"
                + "  JOIN ITEM_PRICING_QUALIFIER IPQ ON IPQ.ITEM_PRICING_QUALIFIER_SID=psd.ITEM_PRICING_QUALIFIER_SID "
                + " WHERE ps.INBOUND_STATUS <> 'D' and  psd.PS_MODEL_SID=" + psSid + StringUtils.EMPTY;
        return sql;
    }

    public String getRSAttachedItems(RsIfpDto RsIfpDto, BeanSearchCriteria bsc) {
        int sidvalue = RsIfpDto.getRebateScheduleSystemId();
        String sql = "SELECT DISTINCT im.ITEM_NO,im.ITEM_NAME,theclass.DESCRIPTION as tclass,bm.BRAND_NAME,im.ITEM_START_DATE,im.ITEM_END_DATE,"
                + "itemsatus.DESCRIPTION,rsd.FORMULA_ID,RPM.REBATE_PLAN_NAME\n"
                + " from RS_MODEL rs"
                + " LEFT JOIN RS_DETAILS rsd on rsd.RS_MODEL_SID=rs.RS_MODEL_SID"
                + "  JOIN IFP_MODEL ifp on ifp.IFP_MODEL_SID=rsd.IFP_MODEL_SID"
                + " LEFT JOIN ITEM_MASTER im on im.ITEM_MASTER_SID=rsd.ITEM_MASTER_SID"
                + " LEFT JOIN HelPER_TABLE htype on htype.HELPER_TABLE_SID =  rs.RS_TYPE"
                + " LEFT JOIN HELPER_TABLE hstatus on hstatus.HELPER_TABLE_SID =  rs.RS_STATUS"
                + " LEFT JOIN HELPER_TABLE hpstatus on hpstatus.HELPER_TABLE_SID =  rs.REBATE_PROGRAM_TYPE"
                + " LEFT JOIN HELPER_TABLE hcategory on hcategory.HELPER_TABLE_SID =  rs.RS_CATEGORY"
                + " LEFT JOIN HELPER_TABLE hdesignation on hdesignation.HELPER_TABLE_SID =  rs.RS_DESIGNATION"
                + "                 LEFT JOIN HELPER_TABLE theclass on theclass.HELPER_TABLE_SID=im.THERAPEUTIC_CLASS \n"
                + "          LEFT JOIN BRAND_MASTER bm on bm.BRAND_MASTER_SID=im.BRAND_MASTER_SID \n"
                + " LEFT JOIN HELPER_TABLE itemsatus on itemsatus.HELPER_TABLE_SID =  im.ITEM_STATUS \n"
                + " LEFT JOIN REBATE_PLAN_MASTER RPM on RPM.REBATE_PLAN_MASTER_SID=rsd.REBATE_PLAN_MASTER_SID"
                + " WHERE rs.INBOUND_STATUS != 'D' and rs.RS_MODEL_SID=" + sidvalue + StringUtils.EMPTY;
        return sql;
    }

    public String getRScount(RsIfpDto RsIfpDto, BeanSearchCriteria bsc) {
        String Pid = StringUtils.EMPTY;
        String PName = StringUtils.EMPTY;
        String PType = StringUtils.EMPTY;
        String PStatus = StringUtils.EMPTY;
        String PNO = StringUtils.EMPTY;
        String sql = "SELECT DISTINCT rs.RS_MODEL_SID, rs.RS_ID, rs.RS_NO, rs.RS_NAME, rs.RS_STATUS, rs.RS_TYPE, rs.REBATE_PROGRAM_TYPE, htype.DESCRIPTION as rtype, hstatus.DESCRIPTION as rstatus, hpstatus.DESCRIPTION as rptype,hcategory.DESCRIPTION as rscategory,"
                + "rs.RS_START_DATE,rs.RS_END_DATE,hdesignation.DESCRIPTION as rsdesignation,rs.PARENT_RS_ID,rs.PARENT_RS_NAME,rs.RECORD_LOCK_STATUS,ifp.IFP_NAME"
                + " from RS_MODEL rs"
                + " LEFT JOIN RS_DETAILS rsd on rsd.RS_MODEL_SID=rs.RS_MODEL_SID"
                + "  JOIN IFP_MODEL ifp on ifp.IFP_MODEL_SID=rsd.IFP_MODEL_SID"
                + " LEFT JOIN HelPER_TABLE htype on htype.HELPER_TABLE_SID =  rs.RS_TYPE"
                + " LEFT JOIN HELPER_TABLE hstatus on hstatus.HELPER_TABLE_SID =  rs.RS_STATUS"
                + " LEFT JOIN HELPER_TABLE hpstatus on hpstatus.HELPER_TABLE_SID =  rs.REBATE_PROGRAM_TYPE"
                + " LEFT JOIN HELPER_TABLE hcategory on hcategory.HELPER_TABLE_SID =  rs.RS_CATEGORY"
                + " LEFT JOIN HELPER_TABLE hdesignation on hdesignation.HELPER_TABLE_SID =  rs.RS_DESIGNATION"
                + " WHERE rs.INBOUND_STATUS != 'D'";
        if (RsIfpDto.getRebateScheduleId().toString() != StringUtils.EMPTY) {
            Pid = RsIfpDto.getRebateScheduleId().toString();
            sql = sql + " and  rs.RS_ID like '" + Pid.replace(CHAR_ASTERISK, CHAR_PERCENT) + "'";

        }
        if (RsIfpDto.getRebateScheduleNo().toString() != StringUtils.EMPTY) {
            PNO = RsIfpDto.getRebateScheduleNo().toString();
            sql = sql + " and  rs.RS_NO like '" + PNO.replace(CHAR_ASTERISK, CHAR_PERCENT) + "'";

        }
        if (RsIfpDto.getRebateScheduleName().toString() != StringUtils.EMPTY) {
            PName = RsIfpDto.getRebateScheduleName().toString();
            sql = sql + " and  rs.RS_NAME like '" + PName.replace(CHAR_ASTERISK, CHAR_PERCENT) + "'";

        }
        if (RsIfpDto.getRebatetype() != StringUtils.EMPTY) {
            PType = RsIfpDto.getRebatetype();
            sql = sql + " and  rs.RS_TYPE=" + PType;

        }
        if (RsIfpDto.getRebateScheduleStatus() != 0) {
            PStatus = RsIfpDto.getRebateScheduleStatus().toString();
            sql = sql + " and  rs.RS_STATUS=" + PStatus;

        }
        return sql;
    }

    public String getRebateSearchQuery(ContractsDetailsDto newDiscountTabDto, boolean isCount) {
        loadColumnNames();
        String query = StringUtils.EMPTY;
        if (!isCount) {
            query += "ORDER BY RS.RS_NAME OFFSET " + newDiscountTabDto.getStartIndex() + "  ROWS FETCH NEXT " + newDiscountTabDto.getEndIndex() + " ROWS ONLY";
        }

        if (newDiscountTabDto.getSearchField().contains(Constants.IndicatorConstants.PS_VALUE.toString())) {
            if (!isCount) {
                query += "ORDER BY PS.PS_NAME OFFSET " + newDiscountTabDto.getStartIndex() + "  ROWS FETCH NEXT " + newDiscountTabDto.getEndIndex() + " ROWS ONLY";
            }
        }
        return query;
    }

    public String getItemsFromRs(ContractsDetailsDto newDiscountTabDto) {
        String query = "select distinct IM.ITEM_MASTER_SID,IM.ITEM_ID,IM.ITEM_NAME,IM.ITEM_NO,HT.DESCRIPTION,BM.BRAND_NAME,HT1.DESCRIPTION,IM.ITEM_START_DATE,IM.ITEM_END_DATE\n"
                + " from IFP_DETAILS ID\n"
                + "join ITEM_MASTER IM oN IM.ITEM_MASTER_SID=ID.ITEM_MASTER_SID \n";
        if (getNull(newDiscountTabDto.getIfpSystemId())) {
            String value = newDiscountTabDto.getIfpSystemId();
            query += " AND ID.IFP_MODEL_SID=" + value;
        }
        query += " join BRAND_MASTER BM ON BM.BRAND_MASTER_SID=IM.BRAND_MASTER_SID\n"
                + "join HELPER_TABLE HT ON HT.HELPER_TABLE_SID=IM.THERAPEUTIC_CLASS\n"
                + "join HELPER_TABLE HT1 ON HT1.HELPER_TABLE_SID=IM.ITEM_STATUS";
        return query;
    }

    public static String getLoadDataSales(RemoveDiscountDto projSelDTO) {
        String whereClause = " and PD.PROJECTION_MASTER_SID= ";
        String groupBy = " ,I.\"YEAR\",I.QUARTER";
        String customSql = StringUtils.EMPTY;
        String orderBy = StringUtils.EMPTY;
        String selectClause = ",I.\"YEAR\"                   AS YEARS,\n"
                + "       I.QUARTER                  AS PERIODS,\n"
                + "       Sum(NMSP.PROJECTION_SALES) AS Sales,\n"
                + "       Sum(NMSP.PROJECTION_UNITS) AS Units,"
                + (projSelDTO.getLevelNo()) + " AS LEVEL_NO ";
        if (projSelDTO.getLevelNo() == 1) {
            selectClause = "COM.COMPANY_NAME " + selectClause;
            groupBy = " COM.COMPANY_NAME " + groupBy;
            orderBy = " ORDER BY COM.COMPANY_NAME OFFSET " + projSelDTO.getStartIndex() + " ROWS FETCH NEXT " + projSelDTO.getOffSet() + " ROWS ONLY ";

        } else if (projSelDTO.getLevelNo() == 2) {
            selectClause = "COM.CONTRACT_NAME " + selectClause;
            groupBy = " COM.CONTRACT_NAME " + groupBy;
            orderBy = " ORDER BY COM.CONTRACT_NAME OFFSET " + projSelDTO.getStartIndex() + " ROWS FETCH NEXT " + projSelDTO.getOffSet() + " ROWS ONLY ";
        } else if (projSelDTO.getLevelNo() == 3) {
            selectClause = "IM.ITEM_NAME " + selectClause;
            groupBy = " IM.ITEM_NAME " + groupBy;
            orderBy = " ORDER BY IM.ITEM_NAME OFFSET " + projSelDTO.getStartIndex() + " ROWS FETCH NEXT " + projSelDTO.getOffSet() + " ROWS ONLY ";
        } else if (projSelDTO.getLevelNo() == 4) {
            selectClause = "BM.BRAND_NAME " + selectClause;
            groupBy = " BM.BRAND_NAME " + groupBy;
            orderBy = " ORDER BY BM.BRAND_NAME OFFSET " + projSelDTO.getStartIndex() + " ROWS FETCH NEXT " + projSelDTO.getOffSet() + "ROWS ONLY ";
        }
        customSql = " select " + selectClause + " FROM   PROJECTION_DETAILS PD\n"
                + "       JOIN NM_SALES_PROJECTION NMSP\n"
                + "         ON NMSP.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n"
                + "       JOIN \"PERIOD\" I\n"
                + "         ON I.PERIOD_SID = NMSP.PERIOD_SID\n"
                + "       JOIN CCP_DETAILS CCPD\n"
                + "         ON CCPD.CCP_DETAILS_SID = PD.CCP_DETAILS_SID    ";
        if (projSelDTO.getLevelNo() == 1) {
            customSql += " JOIN CONTRACT_MASTER COM\n"
                    + "         ON COM.CONTRACT_MASTER_SID = CCPD.CONTRACT_MASTER_SID";
        } else if (projSelDTO.getLevelNo() == 2) {
            customSql += " join COMPANY_MASTER COM ON COM.COMPANY_MASTER_SID=CCPD.COMPANY_MASTER_SID ";
        } else if (projSelDTO.getLevelNo() == 3) {
            customSql += " join ITEM_MASTER IM on IM.ITEM_MASTER_SID=CCPD.ITEM_MASTER_SID ";
        } else if (projSelDTO.getLevelNo() == 4) {
            customSql += " join ITEM_MASTER IM ON IM.ITEM_MASTER_SID=CCPD.ITEM_MASTER_SID ";
            customSql += " join BRAND_MASTER BM ON IM.BRAND_MASTER_SID=BM.BRAND_MASTER_SID ";
        }

        customSql += whereClause + " " + projSelDTO.getProjectionSid() + " group by" + groupBy;
        return customSql;
    }

    public String getItems(int contractSid, int rsSid, boolean flag, int start, int offset) {
        String query = "select IM.ITEM_MASTER_SID,IM.ITEM_NO,IM.ITEM_NAME,IM.THERAPEUTIC_CLASS,\n"
                + "BM.BRAND_NAME,HT.DESCRIPTION AS ITEM_STATUS,IM.ITEM_START_DATE,IM.ITEM_END_DATE,\n"
                + "RP.REBATE_PLAN_NAME,RSD.FORMULA_ID,\n"
                + "RS.RS_CONTRACT_SID,RS.RS_ID,RS.RS_NO,RS.RS_NAME,\n"
                + "HTR.DESCRIPTION AS RS_STATUS,RS.RS_START_DATE,RS.RS_END_DATE,RS.REBATE_FREQUENCY,"
                + "HTPT.DESCRIPTION AS PROGRAM_TYPE,"
                + "HTRT.DESCRIPTION AS RS_TYPE,RS.RS_CATEGORY,RS.PAYMENT_FREQUENCY,RS.REBATE_PLAN_LEVEL,"
                + "RP.FORMULA_TYPE,FM.FORMULA_NO,RSD.REBATE_AMOUNT,RP.REBATE_PLAN_NAME,\n"
                + "RP.REBATE_PLAN_ID"
                + " from RS_CONTRACT_DETAILS RSD join\n"
                + " RS_CONTRACT RS ON  RSD.RS_CONTRACT_SID = RS.RS_CONTRACT_SID JOIN\n"
                + " ITEM_MASTER IM ON RSD.ITEM_MASTER_SID = IM.ITEM_MASTER_SID JOIN\n"
                + " BRAND_MASTER BM ON IM.BRAND_MASTER_SID = BM.BRAND_MASTER_SID LEFT JOIN\n"
                + " REBATE_PLAN_MASTER RP ON RSD.REBATE_PLAN_MASTER_SID = RP.REBATE_PLAN_MASTER_SID  JOIN \n"
                + " HELPER_TABLE HT ON IM.ITEM_STATUS = HT.HELPER_TABLE_SID \n"
                + "  JOIN\n"
                + " HELPER_TABLE HTR ON RS.RS_STATUS = HTR.HELPER_TABLE_SID JOIN\n"
                + " HELPER_TABLE HTRT ON RS.RS_TYPE = HTRT.HELPER_TABLE_SID JOIN\n"
                + " HELPER_TABLE HTPT ON RS.REBATE_PROGRAM_TYPE = HTPT.HELPER_TABLE_SID"
                + " LEFT JOIN \n"
                + " FORMULA_DETAILS_MASTER FM ON FM.FORMULA_ID = RSD.FORMULA_ID"
                + " WHERE RS.CONTRACT_MASTER_SID = " + contractSid;

        query = query + " AND RS.RS_CONTRACT_SID='" + rsSid + "'";
        if (!flag) {
            query = query + " order by IM.ITEM_NO " + " OFFSET " + start + "  ROWS FETCH NEXT " + offset + " ROWS ONLY";
        }
        return query;
    }

    public Map<String, String> getQueryFields() {
        fieldMap.put("Rebate Schedule ID", "RS_ID");
        fieldMap.put("Rebate Schedule No", "RS_NO");
        fieldMap.put("Rebate Schedule Name", "RS_NAME");
        fieldMap.put("Rebate Schedule Status", "RS_STATUS");
        fieldMap.put("Rebate Schedule Type", "RS_TYPE");
        return fieldMap;
    }

    public String getRebates(String field, String value, int contractSid, int rsSid) {

        String query = searchQuery(null);
        getQueryFields();
        value = value.replace(CHAR_ASTERISK, CHAR_PERCENT);
        query = query + " AND CN.CONTRACT_MASTER_SID = '" + contractSid + "'";
        query = query + " AND RSC.RS_CONTRACT_SID = '" + rsSid + "'";
        query = query + " AND RSC." + fieldMap.get(field) + " like '" + value + "'";
        query = query + " GROUP BY CM.COMPANY_NAME,CN.CONTRACT_NO,CN.CONTRACT_NAME,HT.DESCRIPTION,\n"
                + " CN.START_DATE,CN.END_DATE,\n"
                + " CFC.CFP_NAME,IFC.IFP_NAME,PSC.PS_NAME,RSC.RS_NAME,\n"
                + " CN.CONTRACT_MASTER_SID,CFC.CFP_MODEL_SID,\n"
                + " IFC.IFP_MODEL_SID,PSC.PS_MODEL_SID,\n"
                + " RSC.RS_CONTRACT_SID,CM.COMPANY_MASTER_SID,CN.CONTRACT_ID ORDER BY CN.CONTRACT_MASTER_SID ";
        return query;
    }

    public String searchQuery(CustomFieldGroup removeDiscountDto) {
        String query = StringUtils.EMPTY;
        if (removeDiscountDto != null && getNull(removeDiscountDto.getField("customer").getValue().toString())) {
            String customer = removeDiscountDto.getField("customer").getValue().toString();
            customer = customer.replace(CHAR_ASTERISK, CHAR_PERCENT);
            query += "DECLARE @CFP TABLE\n"
                    + "  (CFP_CONTRACT_SID INT)\n"
                    + "INSERT INTO @CFP\n"
                    + "            (CFP_CONTRACT_SID)\n"
                    + "SELECT Distinct CFP_CD.CFP_CONTRACT_SID FROM\n"
                    + "CFP_CONTRACT_DETAILS CFP_CD \n"
                    + "JOIN COMPANY_MASTER CFP_CM ON CFP_CM.COMPANY_MASTER_SID=CFP_CD.COMPANY_MASTER_SID AND CFP_CM.COMPANY_NO like '%' AND CFP_CD.INBOUND_STATUS <> 'D'\n"
                    + "    AND CFP_CM.COMPANY_NAME like '" + customer + "' \n";
        }
        if (removeDiscountDto != null && getNull(removeDiscountDto.getField("item").getValue().toString())) {
            String item = removeDiscountDto.getField("item").getValue().toString();
            item = item.replace(CHAR_ASTERISK, CHAR_PERCENT);
            query += "DECLARE @IFP TABLE\n"
                    + "  (\n"
                    + "     IFP_CONTRACT_SID INT\n"
                    + "  )\n"
                    + "\n"
                    + "INSERT INTO @IFP\n"
                    + "            (IFP_CONTRACT_SID)\n"
                    + "\n"
                    + "SELECT Distinct IFP_CD.IFP_CONTRACT_SID FROM\n"
                    + "IFP_CONTRACT_DETAILS IFP_CD \n"
                    + "JOIN ITEM_MASTER IFP_IM ON IFP_IM.ITEM_MASTER_SID=IFP_CD.ITEM_MASTER_SID AND IFP_IM.ITEM_NAME like '" + item + "' AND IFP_CD.INBOUND_STATUS <> 'D' \n";
        }
        query += "select CM.COMPANY_NAME,CN.CONTRACT_NO,CN.CONTRACT_NAME,HT.DESCRIPTION,\n"
                + "CN.START_DATE,CN.END_DATE,\n"
                + "CFC.CFP_NAME,IFC.IFP_NAME,PSC.PS_NAME,RSC.RS_NAME,\n"
                + "CN.CONTRACT_MASTER_SID,CFC.CFP_MODEL_SID,\n"
                + "IFC.IFP_MODEL_SID,PSC.PS_MODEL_SID,\n"
                + "RSC.RS_CONTRACT_SID,CM.COMPANY_MASTER_SID \n "
                + ",CN.CONTRACT_ID \n "
                + ",CN_ST.DESCRIPTION AS STATUS \n "
                + "from CONTRACT_MASTER CN \n"
                + "LEFT OUTER JOIN CONTRACT_ALIAS_MASTER CAM ON CAM.CONTRACT_MASTER_SID=CN.CONTRACT_MASTER_SID\n"
                + " LEFT JOIN RS_CONTRACT RSC ON CN.CONTRACT_MASTER_SID = RSC.CONTRACT_MASTER_SID\n"
                + " LEFT JOIN IFP_CONTRACT IFC ON RSC.IFP_CONTRACT_SID= IFC.IFP_CONTRACT_SID \n";
        if (removeDiscountDto != null && getNull(removeDiscountDto.getField("item").getValue().toString())) {
            query += " JOIN @IFP IFD ON IFD.IFP_CONTRACT_SID= IFC.IFP_CONTRACT_SID \n";
        }
        query += " LEFT JOIN PS_CONTRACT PSC ON RSC.PS_CONTRACT_SID = PSC.PS_CONTRACT_SID\n"
                + " LEFT JOIN CFP_CONTRACT CFC ON RSC.CFP_CONTRACT_SID = CFC.CFP_CONTRACT_SID \n";

        if (removeDiscountDto != null && getNull(removeDiscountDto.getField("customer").getValue().toString())) {
            query += " JOIN @CFP CFD ON CFD.CFP_CONTRACT_SID= CFC.CFP_CONTRACT_SID \n";
        }
        query += "  LEFT JOIN DBO.HELPER_TABLE CN_ST \n"
                + "  ON CN_ST.HELPER_TABLE_SID=CN.CONTRACT_STATUS ";
        query += " LEFT JOIN COMPANY_MASTER CM ON CN.CONT_HOLD_COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID,"
                + "HELPER_TABLE HT \n"
                + "WHERE CN.CONTRACT_TYPE = HT.HELPER_TABLE_SID AND \n"
                + "CN.RECORD_LOCK_STATUS='false' AND \n"
                + " CN.INBOUND_STATUS!='D' AND"
                + " RSC.INBOUND_STATUS!='D' AND"
                + " CFC.INBOUND_STATUS!='D' AND"
                + " IFC.INBOUND_STATUS!='D' AND"
                + " PSC.INBOUND_STATUS!='D' ";
        return query;
    }

    public String updateDate(String rsSid) {
        Date date = new Date();
        String query = "UPDATE RS_CONTRACT SET RS_END_DATE = '" + DBDate.format(date) + "' from"
                + " RS_CONTRACT WHERE RS_CONTRACT_SID in (" + rsSid + ")";

        return query;
    }

    public String getTpRebateSearchQuery(ComponentInfoDTO newDiscountTabDto, boolean isCount, boolean isPriceSchedule) {

        String searchField = newDiscountTabDto.getSearchField();
        String searchFieldValue = newDiscountTabDto.getSearchFieldValue();
        String query = StringUtils.EMPTY;
        String composedValue1 = searchField.replaceAll(" ", "_");
        String composedValue = searchFieldValue.replaceAll("\\*", "%");

        if (isPriceSchedule) {
            query = "SELECT PS.PS_MODEL_SID,PS.PS_ID,PS.PS_NO,PS.PS_NAME,HT.DESCRIPTION AS STATUS,PS.PS_START_DATE,PS.PS_END_DATE,IFP_M.IFP_NAME,HT1.DESCRIPTION AS PSTYPE,HT2.DESCRIPTION AS CATREGORY\n"
                    + " FROM dbo.PS_MODEL PS LEFT JOIN DBO.PS_DETAILS PSD ON PS.PS_MODEL_SID=PSD.PS_MODEL_SID\n"
                    + "LEFT JOIN DBO.HELPER_TABLE HT ON HT.HELPER_TABLE_SID=PS.PS_STATUS LEFT JOIN DBO.IFP_MODEL IFP_M ON PSD.IFP_MODEL_SID=IFP_M.IFP_MODEL_SID\n"
                    + "LEFT JOIN DBO.HELPER_TABLE HT1 ON HT1.HELPER_TABLE_SID=PS.PS_TYPE\n"
                    + "LEFT JOIN DBO.HELPER_TABLE HT2 ON HT2.HELPER_TABLE_SID=PS.PS_CATEGORY WHERE PS." + composedValue1 + " like '" + composedValue + "'";
        } else {
            query = "SELECT RS.RS_MODEL_SID,RS.RS_ID,RS.RS_NO,RS.RS_NAME,HT.DESCRIPTION AS STATUS,RS.RS_START_DATE,RS.RS_END_DATE,IFP_M.IFP_MODEL_SID,IFP_M.IFP_NAME,HT3.DESCRIPTION AS FRE,HT1.DESCRIPTION AS PSTYPE,HT2.DESCRIPTION AS CATREGORY\n"
                    + " FROM dbo.RS_MODEL RS LEFT JOIN DBO.RS_DETAILS RSD ON RS.RS_MODEL_SID=RSD.RS_MODEL_SID\n"
                    + "LEFT JOIN DBO.HELPER_TABLE HT ON HT.HELPER_TABLE_SID=RS.RS_STATUS LEFT JOIN DBO.IFP_MODEL IFP_M ON RSD.IFP_MODEL_SID=IFP_M.IFP_MODEL_SID\n"
                    + "LEFT JOIN DBO.HELPER_TABLE HT1 ON HT1.HELPER_TABLE_SID=RS.RS_TYPE\n"
                    + "LEFT JOIN DBO.HELPER_TABLE HT2 ON HT2.HELPER_TABLE_SID=RS.RS_CATEGORY "
                    + " LEFT JOIN DBO.HELPER_TABLE HT3 ON HT3.HELPER_TABLE_SID = RS.REBATE_FREQUENCY"
                    + "  WHERE RS." + composedValue1 + " like '" + composedValue + "'";
        }

        return query;
    }

    public String getTpItemsFromRs(ComponentInfoDTO newDiscountTabDto, boolean qCount) {
        String query = StringUtils.EMPTY;
        String component = newDiscountTabDto.getComponentValue();
        if (component.equals("Item Family Plan")) {
            query = "select IM.ITEM_NO,IM.ITEM_NAME,BM.BRAND_NAME,HT.DESCRIPTION AS STATUS,IM.ITEM_START_DATE,IM.ITEM_END_DATE from dbo.IFP_DETAILS IFP_D JOIN dbo.ITEM_MASTER IM ON IFP_D.ITEM_MASTER_SID=IM.ITEM_MASTER_SID\n"
                    + "LEFT JOIN dbo.BRAND_MASTER BM ON IM.BRAND_MASTER_SID=BM.BRAND_MASTER_SID LEFT JOIN dbo.HELPER_TABLE HT ON HT.HELPER_TABLE_SID=IM.ITEM_STATUS\n"
                    + "WHERE IFP_D.IFP_MODEL_SID=" + newDiscountTabDto.getIfpSystemId();
        }
        if (component.equals(Constants.PRICE_SCHEDULE)) {
            query = "select IM.ITEM_NO,HT.DESCRIPTION AS MIC,HT1.DESCRIPTION AS PT,PSD.RESET_ELIGIBLE AS RE,HT2.DESCRIPTION AS RT,PSD.RESET_DATE,HT3.DESCRIPTION AS RI \n"
                    + ",HT4.DESCRIPTION AS RF from dbo.PS_MODEL  PS LEFT JOIN dbo.PS_DETAILS PSD ON PS.PS_MODEL_SID=PSD.PS_MODEL_SID\n"
                    + " JOIN dbo.ITEM_MASTER IM ON IM.ITEM_MASTER_SID=PSD.ITEM_MASTER_SID LEFT JOIN dbo.HELPER_TABLE HT ON PSD.MAX_INCREMENTAL_CHANGE=HT.HELPER_TABLE_SID\n"
                    + "LEFT JOIN dbo.HELPER_TABLE HT1 ON PSD.PRICE_TOLERANCE=HT1.HELPER_TABLE_SID\n"
                    + "LEFT JOIN dbo.HELPER_TABLE HT2 ON PSD.RESET_TYPE=HT2.HELPER_TABLE_SID\n"
                    + "LEFT JOIN dbo.HELPER_TABLE HT3 ON PSD.RESET_INTERVAL=HT3.HELPER_TABLE_SID\n"
                    + "LEFT JOIN dbo.HELPER_TABLE HT4 ON PSD.RESET_FREQUENCY=HT4.HELPER_TABLE_SID WHERE PS.PS_MODEL_SID=" + newDiscountTabDto.getPsSid();
        }

        if (component.equals(Constants.REBATE_SCHEDULE)) {
            query = "select IM.ITEM_NO,IM.ITEM_NAME,BM.BRAND_NAME AS BRAND,HT1.DESCRIPTION AS STATUS,RS.RS_START_DATE,RS.RS_END_DATE\n"
                    + ",HT2.DESCRIPTION AS FT,FDM.FORMULA_ID,FDM.FORMULA_DESC,RPM.REBATE_PLAN_NO,RPM.REBATE_PLAN_NAME,RSD.REBATE_AMOUNT,\n"
                    + "RSD.BUNDLE_NO from dbo.RS_MODEL RS JOIN DBO.RS_DETAILS RSD ON RS.RS_MODEL_SID=RSD.RS_MODEL_SID\n"
                    + "LEFT JOIN DBO.ITEM_MASTER IM ON RSD.ITEM_MASTER_SID=IM.ITEM_MASTER_SID LEFT JOIN DBO.BRAND_MASTER BM ON \n"
                    + "BM.BRAND_MASTER_SID=IM.BRAND_MASTER_SID LEFT JOIN DBO.HELPER_TABLE HT1 ON HT1.HELPER_TABLE_SID=RS.RS_STATUS\n"
                    + "LEFT JOIN DBO.HELPER_TABLE HT2 ON RSD.FORMULA_TYPE=HT2.HELPER_TABLE_SID LEFT JOIN DBO.FORMULA_DETAILS_MASTER FDM \n"
                    + "ON FDM.ITEM_ID=IM.ITEM_ID LEFT JOIN DBO.REBATE_PLAN_MASTER RPM ON RPM.REBATE_PLAN_MASTER_SID=RSD.REBATE_PLAN_MASTER_SID\n"
                    + "WHERE RS.RS_MODEL_SID=" + newDiscountTabDto.getRsSid();
        }

        return query;
    }

    public int getTpItemsFromRsCount(ComponentInfoDTO newDiscountTabDto) {
        int count = 0;
        String query = StringUtils.EMPTY;
        String component = newDiscountTabDto.getComponentValue();
        if (component.equals("Item Family Plan")) {
            query = "select count(IM.ITEM_NO) from dbo.IFP_DETAILS IFP_D JOIN dbo.ITEM_MASTER IM ON IFP_D.ITEM_MASTER_SID=IM.ITEM_MASTER_SID\n"
                    + "LEFT JOIN dbo.BRAND_MASTER BM ON IM.BRAND_MASTER_SID=BM.BRAND_MASTER_SID LEFT JOIN dbo.HELPER_TABLE HT ON HT.HELPER_TABLE_SID=IM.ITEM_STATUS\n"
                    + "WHERE IFP_D.IFP_MODEL_SID=" + newDiscountTabDto.getIfpSystemId();
        } else if (component.equals(Constants.PRICE_SCHEDULE)) {
            query = "select count(IM.ITEM_NO) from dbo.PS_MODEL  PS LEFT JOIN dbo.PS_DETAILS PSD ON PS.PS_MODEL_SID=PSD.PS_MODEL_SID\n"
                    + " JOIN dbo.ITEM_MASTER IM ON IM.ITEM_MASTER_SID=PSD.ITEM_MASTER_SID LEFT JOIN dbo.HELPER_TABLE HT ON PSD.MAX_INCREMENTAL_CHANGE=HT.HELPER_TABLE_SID\n"
                    + "LEFT JOIN dbo.HELPER_TABLE HT1 ON PSD.PRICE_TOLERANCE=HT1.HELPER_TABLE_SID\n"
                    + "LEFT JOIN dbo.HELPER_TABLE HT2 ON PSD.RESET_TYPE=HT2.HELPER_TABLE_SID\n"
                    + "LEFT JOIN dbo.HELPER_TABLE HT3 ON PSD.RESET_INTERVAL=HT3.HELPER_TABLE_SID\n"
                    + "LEFT JOIN dbo.HELPER_TABLE HT4 ON PSD.RESET_FREQUENCY=HT4.HELPER_TABLE_SID WHERE PS.PS_MODEL_SID=" + newDiscountTabDto.getPsSid();
        } else if (component.equals(Constants.REBATE_SCHEDULE)) {
            query = "select count(IM.ITEM_NO) from dbo.RS_MODEL RS JOIN DBO.RS_DETAILS RSD ON RS.RS_MODEL_SID=RSD.RS_MODEL_SID\n"
                    + "LEFT JOIN DBO.ITEM_MASTER IM ON RSD.ITEM_MASTER_SID=IM.ITEM_MASTER_SID LEFT JOIN DBO.BRAND_MASTER BM ON \n"
                    + "BM.BRAND_MASTER_SID=IM.BRAND_MASTER_SID LEFT JOIN DBO.HELPER_TABLE HT1 ON HT1.HELPER_TABLE_SID=RS.RS_STATUS\n"
                    + "LEFT JOIN DBO.HELPER_TABLE HT2 ON RSD.FORMULA_TYPE=HT2.HELPER_TABLE_SID LEFT JOIN DBO.FORMULA_DETAILS_MASTER FDM \n"
                    + "ON FDM.ITEM_ID=IM.ITEM_ID LEFT JOIN DBO.REBATE_PLAN_MASTER RPM ON RPM.REBATE_PLAN_MASTER_SID=RSD.REBATE_PLAN_MASTER_SID\n"
                    + "WHERE RS.RS_MODEL_SID=" + newDiscountTabDto.getRsSid();
        }
        List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        if (list != null && list.size() > 0) {
            String retCount = String.valueOf(list.get(0));
            count = Integer.valueOf(retCount);
            return count;
        }

        return count;
    }

    public String getCFPDetails(int cfpIdValue) {
        String query = " select cfpm.CFP_ID,cfpm.CFP_NO,cfpm.CFP_NAME,h.DESCRIPTION,cfpm.CFP_START_DATE,mdf.FILE_PATH from dbo.CFP_MODEL cfpm join dbo.CFP_CONTRACT cfpc on cfpm.CFP_MODEL_SID=cfpc.CFP_MODEL_SID and cfpc.CFP_CONTRACT_SID=" + cfpIdValue + " join dbo.HELPER_TABLE h on cfpm.CFP_STATUS = h.HELPER_TABLE_SID left join dbo.MASTER_DATA_FILES mdf on cfpm.CFP_MODEL_SID=mdf.MASTER_TABLE_SID and mdf.MASTER_TABLE_NAME='CFP_MODEL' ";
        return query;
    }

    public String getIFPDetails(int ifpIdValue) {
        String query = "select ifpm.IFP_ID,ifpm.IFP_NO,ifpm.IFP_NAME,h.DESCRIPTION,ifpm.IFP_START_DATE,mdf.FILE_PATH from dbo.IFP_MODEL ifpm join dbo.IFP_CONTRACT ifpc on ifpm.IFP_MODEL_SID=ifpc.IFP_MODEL_SID and ifpc.IFP_CONTRACT_SID=" + ifpIdValue + " join dbo.HELPER_TABLE h on ifpm.IFP_STATUS = h.HELPER_TABLE_SID left join dbo.MASTER_DATA_FILES mdf on ifpm.IFP_MODEL_SID=mdf.MASTER_TABLE_SID and mdf.MASTER_TABLE_NAME='IFP_MODEL'";
        return query;
    }

    public String getPSDetails(int psIdValue) {
        String query = "select pm.PS_ID,pm.PS_NO,pm.PS_NAME,h.DESCRIPTION,pm.PS_START_DATE,mdf.FILE_PATH from dbo.PS_MODEL pm join dbo.PS_CONTRACT pc on pm.PS_MODEL_SID=pc.PS_MODEL_SID and pc.PS_CONTRACT_SID=" + psIdValue + " left join dbo.HELPER_TABLE h on pm.PS_STATUS = h.HELPER_TABLE_SID left join dbo.MASTER_DATA_FILES mdf on pm.PS_MODEL_SID=mdf.MASTER_TABLE_SID and mdf.MASTER_TABLE_NAME='PS_MODEL'";
        return query;
    }

    public String getCompanyMasterSid(String ids) {
        String query = "select distinct COMPANY_MASTER_SID from dbo.CFP_CONTRACT_DETAILS where CFP_CONTRACT_SID in (" + ids + ")";
        return query;
    }

    public String getItemMasterDetails(String ids) {
        String query = "select im.ITEM_NO,im.ITEM_NAME,h1.DESCRIPTION AS THERAPHY,bm.BRAND_NAME,h.DESCRIPTION AS STATUS,im.ITEM_START_DATE,im.ITEM_END_DATE from dbo.ITEM_MASTER im join dbo.IFP_DETAILS icd "
                + " on im.ITEM_MASTER_SID=icd.ITEM_MASTER_SID and icd.IFP_MODEL_SID in (" + ids + ") join dbo.HELPER_TABLE h on im.ITEM_STATUS=h.HELPER_TABLE_SID join dbo.HELPER_TABLE h1 on im.THERAPEUTIC_CLASS=h1.HELPER_TABLE_SID "
                + " join dbo.BRAND_MASTER bm on im.BRAND_MASTER_SID=bm.BRAND_MASTER_SID ";

        return query;
    }

    public String getPSDetails(String ids) {
        String query = "SELECT\n" +
                    "    im.ITEM_NO,\n" +
                    "    im.ITEM_NAME,\n" +
                    "    h.DESCRIPTION AS THERAPHY,\n" +
                    "    bm.BRAND_NAME,\n" +
                    "    h1.DESCRIPTION AS STATUS,\n" +
                    "    im.ITEM_START_DATE,\n" +
                    "    im.ITEM_END_DATE,\n" +
                    "    pcd.PRICE_PROTECTION_START_DATE,\n" +
                    "    h2.DESCRIPTION AS PRICE_TOLERANCE_TYPE,\n" +
                    "    ps.PS_NO,\n" +
                    "    ps.PS_NAME,\n" +
                    "    PS_Status.DESCRIPTION,\n" +
                    "    pcd.PRICE_PROTECTION_END_DATE,\n" +
                    "    PT_Type.DESCRIPTION,\n" +
                    "    pcd.PRICE_TOLERANCE_INTERVAL,\n" +
                    "    pcd.PRICE_TOLERANCE_FREQUENCY,\n" +
                    "    P_TYPE.DESCRIPTION,\n" +
                    "    pcd.MAX_INCREMENTAL_CHANGE,\n" +
                    "    pcd.PRICE_TOLERANCE,\n" +
                    "    '0',\n" +
                    "    pcd.RESET_ELIGIBLE,\n" +
                    "    ResetTy.DESCRIPTION AS ResetTy,\n" +
                    "    pcd.RESET_DATE,\n" +
                    "    R_IN.DESCRIPTION AS R_IN,\n" +
                    "    R_FR.DESCRIPTION AS R_FR,\n" +
                    "    getdate() AS attachedDate\n" +
                    "FROM\n" +
                    "    ITEM_MASTER im JOIN PS_DETAILS pcd\n" +
                    "        ON im.ITEM_MASTER_SID = pcd.ITEM_MASTER_SID\n" +
                    "    AND pcd.PS_MODEL_SID IN("+ ids +") LEFT JOIN HELPER_TABLE h\n" +
                    "        ON h.HELPER_TABLE_SID = im.THERAPEUTIC_CLASS JOIN PS_MODEL ps\n" +
                    "        ON ps.PS_MODEL_SID = pcd.PS_MODEL_SID JOIN BRAND_MASTER bm\n" +
                    "        ON im.BRAND_MASTER_SID = bm.BRAND_MASTER_SID LEFT JOIN HELPER_TABLE h1\n" +
                    "        ON h1.HELPER_TABLE_SID = im.ITEM_STATUS LEFT JOIN HELPER_TABLE h2\n" +
                    "        ON h2.HELPER_TABLE_SID = pcd.PRICE_TOLERANCE_TYPE LEFT JOIN HELPER_TABLE PS_Status\n" +
                    "        ON PS_Status.HELPER_TABLE_SID = ps.PS_STATUS LEFT JOIN HELPER_TABLE PT_Type\n" +
                    "        ON PT_Type.HELPER_TABLE_SID = ps.PS_TYPE LEFT JOIN HELPER_TABLE ResetTy\n" +
                    "        ON ResetTy.HELPER_TABLE_SID = pcd.RESET_TYPE LEFT JOIN HELPER_TABLE R_IN\n" +
                    "        ON R_IN.HELPER_TABLE_SID = pcd.RESET_INTERVAL LEFT JOIN HELPER_TABLE R_FR\n" +
                    "        ON R_FR.HELPER_TABLE_SID = pcd.RESET_FREQUENCY LEFT JOIN HELPER_TABLE P_TYPE\n" +
                    "        ON P_TYPE.HELPER_TABLE_SID = pcd.PRICE_TOLERANCE_TYPE";
        return query;
    }

    public String getRSDetails(String ids) {
        String query = "SELECT\n" +
                    "    im.ITEM_NO,\n" +
                    "    im.ITEM_NAME,\n" +
                    "    h.DESCRIPTION AS THERAPHY,\n" +
                    "    bm.BRAND_NAME,\n" +
                    "    h1.DESCRIPTION AS STATUS,\n" +
                    "    im.ITEM_START_DATE,\n" +
                    "    im.ITEM_END_DATE,\n" +
                    "    rcd.FORMULA_ID,\n" +
                    "    rpm.REBATE_PLAN_NAME,\n" +
                    "    rcd.FORMULA_TYPE,\n" +
                    "    ' ',\n" +
                    "    rpm.REBATE_PLAN_ID,\n" +
                    "    rcd.REBATE_AMOUNT,\n" +
                    "    rcd.BUNDLE_NO\n" +
                    "FROM\n" +
                    "    dbo.ITEM_MASTER im JOIN dbo.RS_DETAILS rcd\n" +
                    "        ON im.ITEM_MASTER_SID = rcd.ITEM_MASTER_SID\n" +
                    "    AND rcd.RS_MODEL_SID IN("+ ids +") JOIN dbo.RS_MODEL rc\n" +
                    "        ON rc.RS_MODEL_SID = rcd.RS_MODEL_SID JOIN dbo.BRAND_MASTER bm\n" +
                    "        ON im.BRAND_MASTER_SID = bm.BRAND_MASTER_SID LEFT JOIN dbo.HELPER_TABLE h1\n" +
                    "        ON h1.HELPER_TABLE_SID = im.ITEM_STATUS LEFT JOIN dbo.HELPER_TABLE h\n" +
                    "        ON h.HELPER_TABLE_SID = im.THERAPEUTIC_CLASS LEFT JOIN dbo.REBATE_PLAN_MASTER rpm\n" +
                    "        ON rcd.REBATE_PLAN_MASTER_SID = rpm.REBATE_PLAN_MASTER_SID";
        return query;
    }

    public String getCompanyDetails(String id) {
        String query = "select cm.COMPANY_NO,COMPANY_NAME,h.DESCRIPTION AS STATUS,cm.COMPANY_START_DATE,cm.COMPANY_END_DATE from dbo.COMPANY_MASTER cm join dbo.CFP_DETAILS cfpd on cfpd.COMPANY_MASTER_SID=cm.COMPANY_MASTER_SID"
                + " and cfpd.CFP_MODEL_SID=" + id + " left join dbo.HELPER_TABLE h on cm.COMPANY_STATUS=h.HELPER_TABLE_SID";
        return query;
    }

    public String getIFP(String id) {
        String query = "select IFP_ID,IFP_NO,IFP_NAME,IFP_MODEL_SID from dbo.IFP_MODEL where IFP_MODEL_SID in (select IFP_MODEL_SID from dbo.IFP_CONTRACT where IFP_CONTRACT_SID=" + id + ")";
        return query;
    }

    public String getCompanyValues(String cfpModelId) {
        String componentQuery = "select cm.COMPANY_NO,cm.COMPANY_NAME,h1.DESCRIPTION,cm.COMPANY_START_DATE,cm.COMPANY_END_DATE from dbo.COMPANY_MASTER cm join dbo.CFP_DETAILS cd on cm.COMPANY_MASTER_SID=cd.COMPANY_MASTER_SID"
                + " and cd.CFP_MODEL_SID =" + cfpModelId + " join dbo.HELPER_TABLE h1 on h1.HELPER_TABLE_SID=cm.COMPANY_STATUS";

        return componentQuery;
    }

    public String getItemsForIFPName(String searchValue) {
        String query = "select distinct im.ITEM_NO,im.ITEM_NAME,h.DESCRIPTION AS THERAPHY,bm.BRAND_NAME,im.ITEM_STATUS,im.ITEM_START_DATE,im.ITEM_END_DATE,h3.DESCRIPTION AS PRICETYPE,psd.PRICE_PROTECTION_START_DATE,ifpd.IFP_MODEL_SID,im.ITEM_MASTER_SID  from dbo.ITEM_MASTER im join dbo.IFP_DETAILS ifpd on im.ITEM_MASTER_SID=ifpd.ITEM_MASTER_SID join dbo.IFP_MODEL ifpm on ifpm.IFP_MODEL_SID=ifpd.IFP_MODEL_SID and ifpm.IFP_MODEL_SID in ( " + searchValue + ")\n"
                + " left join dbo.HELPER_TABLE h on h.HELPER_TABLE_SID=im.THERAPEUTIC_CLASS join dbo.BRAND_MASTER bm on bm.BRAND_MASTER_SID=im.BRAND_MASTER_SID \n"
                + " left join dbo.PS_DETAILS psd on psd.IFP_MODEL_SID=ifpd.IFP_MODEL_SID and ifpd.ITEM_MASTER_SID=psd.ITEM_MASTER_SID left join dbo.HELPER_TABLE h3 on h3.HELPER_TABLE_SID=psd.PRICE_TOLERANCE_TYPE";
        return query;
    }

    public String getItemsForIFPID(String searchValue) {
        String query = "select im.ITEM_NO,im.ITEM_NAME,h.DESCRIPTION AS THERAPHY,bm.BRAND_NAME,h2.DESCRIPTION AS STATUS,im.ITEM_START_DATE,im.ITEM_END_DATE,h3.DESCRIPTION AS PRICETYPE,psd.PRICE_PROTECTION_START_DATE,ifpd.IFP_MODEL_SID from dbo.ITEM_MASTER im join dbo.IFP_DETAILS ifpd on im.ITEM_MASTER_SID=ifpd.ITEM_MASTER_SID join dbo.IFP_MODEL ifpm on ifpm.IFP_MODEL_SID=ifpd.IFP_MODEL_SID and ifpm.IFP_ID like '" + searchValue + "'\n"
                + " left join dbo.HELPER_TABLE h on h.HELPER_TABLE_SID=im.THERAPEUTIC_CLASS join dbo.BRAND_MASTER bm on bm.BRAND_MASTER_SID=im.BRAND_MASTER_SID join dbo.HELPER_TABLE h2 on h2.HELPER_TABLE_SID=im.ITEM_STATUS\n"
                + " left join dbo.PS_DETAILS psd on psd.IFP_MODEL_SID=ifpd.IFP_MODEL_SID and ifpd.ITEM_MASTER_SID=psd.ITEM_MASTER_SID left join dbo.HELPER_TABLE h3 on h3.HELPER_TABLE_SID=psd.PRICE_TOLERANCE_TYPE";
        return query;
    }

    public String getItemsForIFPNO(String searchValue) {
        String query = "select im.ITEM_NO,im.ITEM_NAME,h.DESCRIPTION AS THERAPHY,bm.BRAND_NAME,h2.DESCRIPTION AS STATUS,im.ITEM_START_DATE,im.ITEM_END_DATE,h3.DESCRIPTION AS PRICETYPE,psd.PRICE_PROTECTION_START_DATE,ifpd.IFP_MODEL_SID from dbo.ITEM_MASTER im join dbo.IFP_DETAILS ifpd on im.ITEM_MASTER_SID=ifpd.ITEM_MASTER_SID join dbo.IFP_MODEL ifpm on ifpm.IFP_MODEL_SID=ifpd.IFP_MODEL_SID and ifpm.IFP_NO like '" + searchValue + "'\n"
                + " left join dbo.HELPER_TABLE h on h.HELPER_TABLE_SID=im.THERAPEUTIC_CLASS join dbo.BRAND_MASTER bm on bm.BRAND_MASTER_SID=im.BRAND_MASTER_SID join dbo.HELPER_TABLE h2 on h2.HELPER_TABLE_SID=im.ITEM_STATUS\n"
                + " left join dbo.PS_DETAILS psd on psd.IFP_MODEL_SID=ifpd.IFP_MODEL_SID and ifpd.ITEM_MASTER_SID=psd.ITEM_MASTER_SID left join dbo.HELPER_TABLE h3 on h3.HELPER_TABLE_SID=psd.PRICE_TOLERANCE_TYPE";
        return query;
    }

    public String getIFPDetails(String searchValue, int id) {
        String query = "select ifpm.IFP_ID,ifpm.IFP_NAME,ifpm.IFP_NO,h.DESCRIPTION AS STATUS,h1.DESCRIPTION AS TYPE,ifpm.IFP_MODEL_SID from dbo.IFP_MODEL ifpm join dbo.HELPER_TABLE h on h.HELPER_TABLE_SID=ifpm.IFP_STATUS "
                + " join dbo.HELPER_TABLE h1 on h1.HELPER_TABLE_SID=ifpm.IFP_TYPE ";
        if (id == 1) {
            query = query + "and ifpm.IFP_ID like '" + searchValue + "'";
        } else if (id == 2) {
            query = query + "and ifpm.IFP_NO like '" + searchValue + "'";
        } else if (id == 3) {
            query = query + "and ifpm.IFP_NAME like '" + searchValue + "'";
        } else if (id == 4) {
            query = query + "and ifpm.IFP_STATUS = '" + searchValue + "'";
        } else if (id == 5) {
            query = query + "and ifpm.IFP_TYPE = '" + searchValue + "'";
        }

        return query;
    }

    public ComboBox getNativeSelect(final ComboBox select, final List<HelperDTO> helperList) {

        LOGGER.info("enters getNativeSelect()");
        for (int i = 0; helperList.size() > i; i++) {
            select.addItem(String.valueOf(helperList.get(i)
                    .getId()));
            select.setItemCaption(String.valueOf(helperList.get(i)
                    .getId()), helperList.get(i).getDescription());

        }

        LOGGER.info("method getNativeSelect ends and returns select");
        return select;
    }

    public List<HelperDTO> getDropDownList(final String listType) throws SystemException {
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();

        LOGGER.info("entering getDropDownList method with paramater listType=" + listType);
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        dynamicQuery.add(RestrictionsFactoryUtil.or(RestrictionsFactoryUtil.like(Constants.LIST_NAME, listType), RestrictionsFactoryUtil.like(Constants.LIST_NAME, Constants.ALL)));
        dynamicQuery.addOrder(OrderFactoryUtil.asc(Constants.DESCRIPTION));
        final List<HelperTable> list = HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);
        helperList.add(new HelperDTO(0, Constants.SELECT_ONE));
        if (list != null) {

            for (int i = 0; i < list.size(); i++) {

                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(),
                        helperTable.getDescription()));

            }
        }

        LOGGER.info(" getDropDownList method ends with return value strList size =" + helperList.size());

        return helperList;
    }

    public String getSearchValues(CustomFieldGroup discountChBinder, String moduleName, int start, int offset, Set<Container.Filter> filters, List<SortByColumn> sortByColumns) {
        String query = StringUtils.EMPTY;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        LookupDTO binderDTO = ((BeanItem<LookupDTO>) (discountChBinder.getItemDataSource())).getBean();
        Map<String, Object> parameters = new HashMap<String, Object>();
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                    parameters.put("filter~" + stringFilter.getPropertyId(), filterString);

                } else if (filter instanceof Between) {
                    Between betweenFilter = (Between) filter;
                    Date startValue = (Date) betweenFilter.getStartValue();
                    Date endValue = (Date) betweenFilter.getEndValue();
                    parameters.put("filter~" + betweenFilter.getPropertyId() + "from", String.valueOf(dateFormat.format(startValue)));
                    parameters.put("filter~" + betweenFilter.getPropertyId() + "to", String.valueOf(dateFormat.format(endValue)));
                    parameters.put(betweenFilter.getPropertyId() + "from", startValue);
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    if (stringFilter.getValue() instanceof Date) {

                        Date filterString = (Date) stringFilter.getValue();
                        if (Constants.RS_START_DATE.equals(stringFilter.getPropertyId())) {
                            if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER_OR_EQUAL)) {
                                parameters.put("filter~" + stringFilter.getPropertyId() + "from", String.valueOf(dateFormat.format(filterString)));
                            } else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS_OR_EQUAL)) {
                                parameters.put("filter~" + stringFilter.getPropertyId() + "to", String.valueOf(dateFormat.format(filterString)));
                            }
                        } else if (Constants.RS_END_DATE.equals(stringFilter.getPropertyId())) {
                            if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER_OR_EQUAL)) {
                                parameters.put("filter~" + stringFilter.getPropertyId() + "from", String.valueOf(dateFormat.format(filterString)));
                            } else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS_OR_EQUAL)) {
                                parameters.put("filter~" + stringFilter.getPropertyId() + "to", String.valueOf(dateFormat.format(filterString)));
                            }
                        } else if (Constants.CFP_START_DATE.equals(stringFilter.getPropertyId())) {

                            if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER_OR_EQUAL)) {

                                parameters.put("filter~" + stringFilter.getPropertyId() + "from", String.valueOf(dateFormat.format(filterString)));
                            } else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS_OR_EQUAL)) {

                                parameters.put("filter~" + stringFilter.getPropertyId() + "to", String.valueOf(dateFormat.format(filterString)));
                            }
                        } else if (Constants.CFP_END_DATE.equals(stringFilter.getPropertyId())) {
                            if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER_OR_EQUAL)) {

                                parameters.put("filter~" + stringFilter.getPropertyId() + "from", String.valueOf(dateFormat.format(filterString)));
                            } else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS_OR_EQUAL)) {

                                parameters.put("filter~" + stringFilter.getPropertyId() + "to", String.valueOf(dateFormat.format(filterString)));
                            }
                        } else if (Constants.PS_START_DATE.equals(stringFilter.getPropertyId())) {

                            if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER_OR_EQUAL)) {

                                parameters.put("filter~" + stringFilter.getPropertyId() + "from", String.valueOf(dateFormat.format(filterString)));
                            } else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS_OR_EQUAL)) {

                                parameters.put("filter~" + stringFilter.getPropertyId() + "to", String.valueOf(dateFormat.format(filterString)));
                            }
                        } else if (Constants.PS_END_DATE.equals(stringFilter.getPropertyId())) {
                            if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER_OR_EQUAL)) {

                                parameters.put("filter~" + stringFilter.getPropertyId() + "from", String.valueOf(dateFormat.format(filterString)));
                            } else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS_OR_EQUAL)) {

                                parameters.put("filter~" + stringFilter.getPropertyId() + "to", String.valueOf(dateFormat.format(filterString)));
                            }
                        } else if (Constants.IFP_START_DATE.equals(stringFilter.getPropertyId())) {

                            if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER_OR_EQUAL)) {

                                parameters.put("filter~" + stringFilter.getPropertyId() + "from", String.valueOf(dateFormat.format(filterString)));
                            } else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS_OR_EQUAL)) {

                                parameters.put("filter~" + stringFilter.getPropertyId() + "to", String.valueOf(dateFormat.format(filterString)));
                            }

                        } else if (Constants.IFP_END_DATE.equals(stringFilter.getPropertyId())) {

                            if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER_OR_EQUAL)) {

                                parameters.put("filter~" + stringFilter.getPropertyId() + "from", String.valueOf(dateFormat.format(filterString)));
                            } else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS_OR_EQUAL)) {

                                parameters.put("filter~" + stringFilter.getPropertyId() + "to", String.valueOf(dateFormat.format(filterString)));
                            }
                        }

                    }
                }

            }

        }

        parameters.put(Constants.IS_ORDERED, "false");
        for (Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
            SortByColumn orderByColumn = (SortByColumn) iterator.next();
            String columnId = orderByColumn.getName();
            if (orderByColumn.getType() == SortByColumn.Type.ASC) {
                parameters.put("orderBy~" + columnId, "asc");
                parameters.put(Constants.IS_ORDERED, Constants.TRUE);
            } else {
                parameters.put("orderBy~" + columnId, "desc");
                parameters.put(Constants.IS_ORDERED, Constants.TRUE);
            }
        }

        if (Constants.CFP.equals(moduleName)) {

            query += "select distinct CM.CFP_ID,CM.CFP_NO,CM.CFP_NAME,(case when HT.HELPER_TABLE_SID=0 then ' ' else HT.DESCRIPTION end) AS CFP_TYPE,(case when  HTCA.HELPER_TABLE_SID=0 then ' ' else HTCA.DESCRIPTION end) AS CFP_CATEGORY,\n"
                    + "(case when HTCD.HELPER_TABLE_SID=0 then ' ' else HTCD.DESCRIPTION end) AS CFP_DESIGNATION,(case when  HTS.HELPER_TABLE_SID=0 then ' ' else HTS.DESCRIPTION end) AS STATUS,(case when  HTTC.HELPER_TABLE_SID=0 then ' ' else HTTC.DESCRIPTION end) AS CFP_TRADE_CLASS,CM.CFP_START_DATE,\n"
                    + " CM.CFP_END_DATE,CM.CFP_MODEL_SID"
                    + " from CFP_MODEL CM JOIN\n"
                    + " CFP_CONTRACT CFC ON CM.CFP_MODEL_SID = CFC.CFP_MODEL_SID JOIN"
                    + " HELPER_TABLE HT ON CM.CFP_TYPE = HT.HELPER_TABLE_SID JOIN"
                    + " HELPER_TABLE HTS ON CM.CFP_STATUS = HTS.HELPER_TABLE_SID JOIN \n"
                    + " HELPER_TABLE HTCA ON CM.CFP_CATEGORY = HTCA.HELPER_TABLE_SID JOIN \n"
                    + " HELPER_TABLE HTCD ON CM.CFP_DESIGNATION = HTCD.HELPER_TABLE_SID JOIN \n"
                    + " HELPER_TABLE HTTC ON CM.CFP_TRADE_CLASS = HTTC.HELPER_TABLE_SID"
                    + " AND CM.CFP_ID like '" + astToPerConverter(binderDTO.getCfpId()) + "' AND CM.CFP_NO like '" + astToPerConverter(binderDTO.getCfpNo()) + "'  AND CM.CFP_NAME like '" + astToPerConverter(binderDTO.getCfpName()) + "'"
                    + " AND CM.CFP_TYPE like '" + zeroToPerConverter(String.valueOf(binderDTO.getCfpType() == null ? StringUtils.EMPTY : binderDTO.getCfpType().getId())) + "' AND CM.CFP_CATEGORY like '" + zeroToPerConverter(String.valueOf(binderDTO.getCfpCategory() == null ? StringUtils.EMPTY : binderDTO.getCfpCategory().getId())) + "' AND CM.CFP_STATUS like '" + zeroToPerConverter(String.valueOf(binderDTO.getCfpStatus() == null ? StringUtils.EMPTY : binderDTO.getCfpStatus().getId())) + "'";
            if ((binderDTO.getCfpStartDate() != null)) {
                query += "AND CM.CFP_START_DATE = '" + getDBDate((Date) discountChBinder.getField(Constants.CFP_START_DATE).getValue()) + "'\n";
            }

            if ((binderDTO.getCfpEndDate() != null)) {
                query += "AND CM.CFP_END_DATE = '" + getDBDate((Date) discountChBinder.getField(Constants.CFP_END_DATE).getValue()) + "'\n";
            }
            if (parameters.get("filter~cfpId") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~cfpId"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~cfpId")))) {
                query += " AND cm.CFP_ID like '";
                query += String.valueOf(parameters.get("filter~cfpId")) + "' ";
            }
            if (parameters.get("filter~cfpNo") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~cfpNo"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~cfpNo")))) {
                query += " AND cm.CFP_NO like '";
                query += String.valueOf(parameters.get("filter~cfpNo")) + "' ";
            }
            if (parameters.get("filter~cfpName") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~cfpName"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~cfpName")))) {
                query += " AND cm.CFP_NAME like '";
                query += String.valueOf(parameters.get("filter~cfpName")) + "' ";
            }
            if (parameters.get("filter~cfpType") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~cfpType"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~cfpType")))) {
                query += " AND cm.CFP_TYPE like '";
                query += String.valueOf(parameters.get("filter~cfpType")) + "' ";
            }
            if (parameters.get("filter~cfpCategory") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~cfpCategory"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~cfpCategory")))) {
                query += " AND cm.CFP_CATEGORY like '";
                query += String.valueOf(parameters.get("filter~cfpCategory")) + "' ";
            }
            if (parameters.get("filter~cfpDesignation") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~cfpDesignation"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~cfpDesignation")))) {
                query += " AND cm.CFP_DESIGNATION like '";
                query += String.valueOf(parameters.get("filter~cfpDesignation")) + "' ";
            }
            if (parameters.get("filter~cfpStatus") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~cfpStatus"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~cfpStatus")))) {
                query += " AND cm.CFP_STATUS like '";
                query += String.valueOf(parameters.get("filter~cfpStatus")) + "' ";
            }
            if (parameters.get("filter~cfpTradeClass") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~cfpTradeClass"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~cfpTradeClass")))) {
                query += " AND cm.CFP_TRADE_CLASS like '";
                query += String.valueOf(parameters.get("filter~cfpTradeClass")) + "' ";
            }
            if ((parameters.get("filter~cfpStartDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("filter~cfpStartDatefrom"))))) {
                query += " AND  cm.CFP_START_DATE >='" + String.valueOf(parameters.get("filter~cfpStartDatefrom")) + "'";
            }
            if ((parameters.get("filter~cfpStartDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("filter~cfpStartDateto"))))) {
                query += " AND  cm.CFP_START_DATE <='" + String.valueOf(parameters.get("filter~cfpStartDateto")) + "'";
            }
            if ((parameters.get("filter~cfpEndDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("filter~cfpEndDatefrom"))))) {
                query += " AND  cm.CFP_END_DATE >='" + String.valueOf(parameters.get("filter~cfpEndDatefrom")) + "'";
            }
            if ((parameters.get("filter~cfpEndDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("filter~cfpEndDateto"))))) {
                query += " AND  cm.CFP_END_DATE <='" + String.valueOf(parameters.get("filter~cfpEndDateto")) + "'";
            }
            if ((parameters.get(Constants.IS_ORDERED) == null || "false".equalsIgnoreCase(String.valueOf(parameters.get(Constants.IS_ORDERED))))) {
                query += " ORDER BY CM.CFP_ID ";
            } else if (parameters.get(Constants.IS_ORDERED) != null && Constants.TRUE.equalsIgnoreCase(String.valueOf(parameters.get(Constants.IS_ORDERED)))) {
                if (parameters.get("orderBy~cfpId") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~cfpId"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~cfpId")))) {
                    query += " ORDER BY cm.CFP_ID ";
                    query += String.valueOf(parameters.get("orderBy~cfpId"));
                }
                if (parameters.get("orderBy~cfpNo") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~cfpNo"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~cfpNo")))) {
                    query += " ORDER BY cm.CFP_NO ";
                    query += String.valueOf(parameters.get("orderBy~cfpNo"));
                }
                if (parameters.get("orderBy~cfpName") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~cfpName"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~cfpName")))) {
                    query += " ORDER BY cm.CFP_NAME ";
                    query += String.valueOf(parameters.get("orderBy~cfpName"));
                }
                if (parameters.get("orderBy~cfpType") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~cfpType"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~cfpType")))) {
                    query += " ORDER BY CFP_TYPE ";
                    query += String.valueOf(parameters.get("orderBy~cfpType"));
                }
                if (parameters.get("orderBy~cfpCategory") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~cfpCategory"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~cfpCategory")))) {
                    query += " ORDER BY CFP_CATEGORY ";
                    query += String.valueOf(parameters.get("orderBy~cfpCategory"));
                }
                if (parameters.get("orderBy~cfpDesignation") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~cfpDesignation"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~cfpDesignation")))) {
                    query += " ORDER BY cm.CFP_DESIGNATION ";
                    query += String.valueOf(parameters.get("orderBy~cfpDesignation"));
                }
                if (parameters.get("orderBy~cfpStatus") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~cfpStatus"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~cfpStatus")))) {
                    query += " ORDER BY STATUS ";
                    query += String.valueOf(parameters.get("orderBy~cfpStatus"));
                }
                if (parameters.get("orderBy~cfpTradeClass") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~cfpTradeClass"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~cfpTradeClass")))) {
                    query += " ORDER BY CFP_TRADE_CLASS ";
                    query += String.valueOf(parameters.get("orderBy~cfpTradeClass"));
                }
                if (parameters.get("orderBy~cfpStartDate") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~cfpStartDate"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~cfpStartDate")))) {
                    query += " ORDER BY cm.CFP_START_DATE ";
                    query += String.valueOf(parameters.get("orderBy~cfpStartDate"));
                }
                if (parameters.get("orderBy~cfpEndDate") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~cfpEndDate"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~cfpEndDate")))) {
                    query += " ORDER BY cm.CFP_END_DATE ";
                    query += String.valueOf(parameters.get("orderBy~cfpEndDate"));
                }
            }
            query += " OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY";

        } else if (Constants.IFP.equals(moduleName)) {
            query += "select DISTINCT IM.IFP_MODEL_SID,IM.IFP_ID,IM.IFP_NO,IM.IFP_NAME,(case when  HT.HELPER_TABLE_SID=0 then ' ' else HT.DESCRIPTION end) AS IFP_TYPE,(case when HTCAT.HELPER_TABLE_SID=0 then ' ' else HTCAT.DESCRIPTION end) AS IFP_CATEGORY,\n"
                    + " (case when HTDE.HELPER_TABLE_SID=0 then ' ' else HTDE.DESCRIPTION end) AS IFP_DESIGNATION,(case when HTS.HELPER_TABLE_SID=0 then ' ' else HTS.DESCRIPTION end) AS STATUS,IM.IFP_START_DATE,\n"
                    + " IM.IFP_END_DATE,IM.PARENT_IFP_ID,IM.PARENT_IFP_NAME\n"
                    + " from IFP_MODEL IM JOIN\n"
                    + " IFP_CONTRACT CFC ON IM.IFP_MODEL_SID = CFC.IFP_MODEL_SID JOIN \n"
                    + " HELPER_TABLE HT ON IM.IFP_TYPE = HT.HELPER_TABLE_SID JOIN \n"
                    + " HELPER_TABLE HTS ON IM.IFP_STATUS = HTS.HELPER_TABLE_SID JOIN \n"
                    + " HELPER_TABLE HTCAT ON IM.IFP_CATEGORY = HTCAT.HELPER_TABLE_SID JOIN \n"
                    + " HELPER_TABLE HTDE ON IM.IFP_DESIGNATION = HTDE.HELPER_TABLE_SID "
                    + " AND IM.IFP_NO like '" + astToPerConverter(binderDTO.getIfpNo()) + "'  AND IM.IFP_NAME like '" + astToPerConverter(binderDTO.getIfpName()) + "'"
                    + " AND IM.IFP_TYPE like '" + zeroToPerConverter(String.valueOf(binderDTO.getIfpType() == null ? StringUtils.EMPTY : binderDTO.getIfpType().getId())) + "' AND IM.IFP_CATEGORY like '" + zeroToPerConverter(String.valueOf(binderDTO.getIfpCategory() == null ? StringUtils.EMPTY : binderDTO.getIfpCategory().getId())) + "' AND IM.IFP_STATUS like '" + zeroToPerConverter(String.valueOf(binderDTO.getIfpStatus() == null ? StringUtils.EMPTY : binderDTO.getIfpStatus().getId())) + "'";
            if ((binderDTO.getIfpStartDate() != null)) {
                query += "AND IM.IFP_START_DATE = '" + getDBDate((Date) discountChBinder.getField(Constants.IFP_START_DATE).getValue()) + "'\n";
            }

            if ((binderDTO.getIfpEndDate() != null)) {
                query += "AND IM.IFP_END_DATE = '" + getDBDate((Date) discountChBinder.getField(Constants.IFP_END_DATE).getValue()) + "'\n";
            }
            if (parameters.get("filter~ifpNo") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~ifpNo"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~ifpNo")))) {
                query += " AND IM.IFP_NO like '";
                query += String.valueOf(parameters.get("filter~ifpNo")) + "' ";
            }
            if (parameters.get("filter~ifpName") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~ifpName"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~ifpName")))) {
                query += " AND IM.IFP_NAME like '";
                query += String.valueOf(parameters.get("filter~ifpName")) + "' ";
            }
            if (parameters.get("filter~ifpType") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~ifpType"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~ifpType")))) {
                query += " AND IM.IFP_TYPE like '";
                query += String.valueOf(parameters.get("filter~ifpType")) + "' ";
            }
            if (parameters.get("filter~ifpCategory") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~ifpCategory"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~ifpCategory")))) {
                query += " AND IM.IFP_CATEGORY like '";
                query += String.valueOf(parameters.get("filter~ifpCategory")) + "' ";
            }
            if (parameters.get("filter~ifpDesignation") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~ifpDesignation"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~ifpDesignation")))) {
                query += " AND IM.IFP_DESIGNATION like '";
                query += String.valueOf(parameters.get("filter~ifpDesignation")) + "' ";
            }
            if (parameters.get("filter~ifpStatus") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~ifpStatus"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~ifpStatus")))) {
                query += " AND IM.IFP_STATUS like '";
                query += String.valueOf(parameters.get("filter~ifpStatus")) + "' ";
            }
            if (parameters.get("filter~ifpPlanId") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~ifpPlanId"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~ifpPlanId")))) {
                query += " AND IM.PARENT_IFP_ID like '";
                query += String.valueOf(parameters.get("filter~ifpPlanId")) + "' ";
            }
            if (parameters.get("filter~ifpPlanName") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~ifpPlanName"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~ifpPlanName")))) {
                query += " AND IM.PARENT_IFP_NAME like '";
                query += String.valueOf(parameters.get("filter~ifpPlanName")) + "' ";
            }
            if ((parameters.get("filter~ifpStartDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("filter~ifpStartDatefrom"))))) {
                query += " AND  IM.IFP_START_DATE >='" + String.valueOf(parameters.get("filter~ifpStartDatefrom")) + "'";
            }
            if ((parameters.get("filter~ifpStartDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("filter~ifpStartDateto"))))) {
                query += " AND  IM.IFP_START_DATE <='" + String.valueOf(parameters.get("filter~ifpStartDateto")) + "'";
            }
            if ((parameters.get("filter~ifpEndDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("filter~ifpEndDatefrom"))))) {
                query += " AND  IM.IFP_END_DATE >='" + String.valueOf(parameters.get("filter~ifpEndDatefrom")) + "'";
            }
            if ((parameters.get("filter~ifpEndDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("filter~ifpEndDateto"))))) {
                query += " AND  IM.IFP_END_DATE <='" + String.valueOf(parameters.get("filter~ifpEndDateto")) + "'";
            }
            if ((parameters.get(Constants.IS_ORDERED) == null || "false".equalsIgnoreCase(String.valueOf(parameters.get(Constants.IS_ORDERED))))) {
                query += " ORDER BY IM.IFP_MODEL_SID  ";
            } else if (parameters.get(Constants.IS_ORDERED) != null && Constants.TRUE.equalsIgnoreCase(String.valueOf(parameters.get(Constants.IS_ORDERED)))) {
                if (parameters.get("orderBy~ifpNo") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~ifpNo"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~ifpNo")))) {
                    query += " ORDER BY IM.IFP_NO ";
                    query += String.valueOf(parameters.get("orderBy~ifpNo"));
                }
                if (parameters.get("orderBy~ifpName") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~ifpName"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~ifpName")))) {
                    query += " ORDER BY IM.IFP_NAME ";
                    query += String.valueOf(parameters.get("orderBy~ifpName"));
                }
                if (parameters.get("orderBy~ifpType") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~ifpType"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~ifpType")))) {
                    query += " ORDER BY IFP_TYPE ";
                    query += String.valueOf(parameters.get("orderBy~ifpType"));
                }
                if (parameters.get("orderBy~ifpCategory") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~ifpCategory"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~ifpCategory")))) {
                    query += " ORDER BY IFP_CATEGORY ";
                    query += String.valueOf(parameters.get("orderBy~ifpCategory"));
                }
                if (parameters.get("orderBy~ifpDesignation") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~ifpDesignation"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~ifpDesignation")))) {
                    query += " ORDER BY IFP_DESIGNATION ";
                    query += String.valueOf(parameters.get("orderBy~ifpDesignation"));
                }
                if (parameters.get("orderBy~ifpStatus") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~ifpStatus"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~ifpStatus")))) {
                    query += " ORDER BY STATUS ";
                    query += String.valueOf(parameters.get("orderBy~ifpStatus"));
                }
                if (parameters.get("orderBy~ifpStartDate") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~ifpStartDate"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~ifpStartDate")))) {
                    query += " ORDER BY IM.IFP_START_DATE ";
                    query += String.valueOf(parameters.get("orderBy~ifpStartDate"));
                }
                if (parameters.get("orderBy~ifpEndDate") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~ifpEndDate"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~ifpEndDate")))) {
                    query += " ORDER BY IM.IFP_END_DATE ";
                    query += String.valueOf(parameters.get("orderBy~ifpEndDate"));
                }
            }
            query += " OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY";
        } else if (Constants.PS.equals(moduleName)) {
            query = "select DISTINCT PS.PS_MODEL_SID,PS.PS_ID,PS.PS_NO,PS.PS_NAME,(case when HT.HELPER_TABLE_SID=0 then ' ' else HT.DESCRIPTION end) AS PS_TYPE,(case when HTCAT.HELPER_TABLE_SID=0 then ' ' else HTCAT.DESCRIPTION end) AS PS_CATEGORY\n"
                    + " ,(case when HTTC.HELPER_TABLE_SID=0 then ' ' else HTTC.DESCRIPTION end) AS TRADE_CLASS, (case when HTDE.HELPER_TABLE_SID=0 then ' ' else HTDE.DESCRIPTION end) AS PS_DESIGNATION,(case when HTS.HELPER_TABLE_SID=0 then ' ' else HTS.DESCRIPTION end) AS STATUS,PS.PS_START_DATE,\n"
                    + "  PS.PS_END_DATE\n"
                    + "  from PS_MODEL PS JOIN\n"
                    + "  PS_CONTRACT CFC ON PS.PS_MODEL_SID = CFC.PS_MODEL_SID JOIN\n"
                    + "  HELPER_TABLE HT ON PS.PS_TYPE = HT.HELPER_TABLE_SID JOIN\n"
                    + "  HELPER_TABLE HTS ON PS.PS_STATUS = HTS.HELPER_TABLE_SID JOIN\n"
                    + "  HELPER_TABLE HTCAT ON PS.PS_CATEGORY = HTCAT.HELPER_TABLE_SID JOIN\n"
                    + "  HELPER_TABLE HTTC ON PS.PS_TRADE_CLASS = HTTC.HELPER_TABLE_SID JOIN\n"
                    + "  HELPER_TABLE HTDE ON PS.PS_DESIGNATION = HTDE.HELPER_TABLE_SID"
                    + " AND PS.PS_NO like '" + astToPerConverter(binderDTO.getPsNo()) + "'  AND PS.PS_NAME like '" + astToPerConverter(binderDTO.getPsName()) + "'"
                    + " AND PS.PS_TYPE like '" + zeroToPerConverter(String.valueOf(binderDTO.getPsType() == null ? StringUtils.EMPTY : binderDTO.getPsType().getId())) + "' AND PS.PS_CATEGORY like '" + zeroToPerConverter(String.valueOf(binderDTO.getPsCategory() == null ? StringUtils.EMPTY : binderDTO.getPsCategory().getId())) + "' AND PS.PS_STATUS like '" + zeroToPerConverter(String.valueOf(binderDTO.getPsStatus() == null ? StringUtils.EMPTY : binderDTO.getPsStatus().getId())) + "'";
            if ((binderDTO.getPsStartDate() != null)) {
                query += "AND PS.PS_START_DATE = '" + getDBDate((Date) discountChBinder.getField(Constants.PS_START_DATE).getValue()) + "'\n";
            }

            if ((binderDTO.getPsEndDate() != null)) {
                query += "AND PS.PS_END_DATE = '" + getDBDate((Date) discountChBinder.getField(Constants.PS_END_DATE).getValue()) + "'\n";
            }
            if (parameters.get("filter~psNo") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~psNo"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~psNo")))) {
                query += " AND PS.PS_NO like '";
                query += String.valueOf(parameters.get("filter~psNo")) + "' ";
            }
            if (parameters.get("filter~psName") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~psName"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~psName")))) {
                query += " AND PS.PS_NAME like '";
                query += String.valueOf(parameters.get("filter~psName")) + "' ";
            }
            if (parameters.get("filter~psType") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~psType"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~psType")))) {
                query += " AND PS.PS_TYPE like '";
                query += String.valueOf(parameters.get("filter~psType")) + "' ";
            }
            if (parameters.get("filter~psCategory") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~psCategory"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~psCategory")))) {
                query += " AND PS.PS_CATEGORY like '";
                query += String.valueOf(parameters.get("filter~psCategory")) + "' ";
            }
            if (parameters.get("filter~psTradeClass") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~psTradeClass"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~psTradeClass")))) {
                query += " AND PS.PS_TRADE_CLASS like '";
                query += String.valueOf(parameters.get("filter~psTradeClass")) + "' ";
            }
            if (parameters.get("filter~psDesignation") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~psDesignation"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~psDesignation")))) {
                query += " AND PS.PS_DESIGNATION like '";
                query += String.valueOf(parameters.get("filter~psDesignation")) + "' ";
            }
            if (parameters.get("filter~psStatus") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~psStatus"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~psStatus")))) {
                query += " AND PS.PS_STATUS like '";
                query += String.valueOf(parameters.get("filter~psStatus")) + "' ";
            }

            if (parameters.get("filter~parentPsId") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~parentPsId"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~parentPsId")))) {
                query += " AND PS.PARENT_PS_ID like '";
                query += String.valueOf(parameters.get("filter~parentPsId")) + "' ";
            }
            if (parameters.get("filter~parentPsName") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~parentPsName"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~parentPsName")))) {
                query += " AND PS.PARENT_PS_NAME like '";
                query += String.valueOf(parameters.get("filter~parentPsName")) + "' ";
            }
            if ((parameters.get("filter~psStartDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("filter~psStartDatefrom"))))) {
                query += " AND  PS.PS_START_DATE >='" + String.valueOf(parameters.get("filter~psStartDatefrom")) + "'";
            }
            if ((parameters.get("filter~psStartDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("filter~psStartDateto"))))) {
                query += " AND  PS.PS_START_DATE <='" + String.valueOf(parameters.get("filter~psStartDateto")) + "'";
            }
            if ((parameters.get("filter~psEndDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("filter~psEndDatefrom"))))) {
                query += " AND  PS.PS_END_DATE >='" + String.valueOf(parameters.get("filter~psEndDatefrom")) + "'";
            }
            if ((parameters.get("filter~psEndDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("filter~psEndDateto"))))) {
                query += " AND  PS.PS_END_DATE <='" + String.valueOf(parameters.get("filter~psEndDateto")) + "'";
            }
            if ((parameters.get(Constants.IS_ORDERED) == null || "false".equalsIgnoreCase(String.valueOf(parameters.get(Constants.IS_ORDERED))))) {
                query += " ORDER BY PS.PS_MODEL_SID ";
            } else if (parameters.get(Constants.IS_ORDERED) != null && Constants.TRUE.equalsIgnoreCase(String.valueOf(parameters.get(Constants.IS_ORDERED)))) {
                if (parameters.get("orderBy~psNo") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~psNo"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~psNo")))) {
                    query += " ORDER BY PS.PS_NO ";
                    query += String.valueOf(parameters.get("orderBy~psNo"));
                }
                if (parameters.get("orderBy~psName") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~psName"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~psName")))) {
                    query += " ORDER BY PS.PS_NAME ";
                    query += String.valueOf(parameters.get("orderBy~psName"));
                }
                if (parameters.get("orderBy~psType") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~psType"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~psType")))) {
                    query += " ORDER BY PS_TYPE ";
                    query += String.valueOf(parameters.get("orderBy~psType"));
                }
                if (parameters.get("orderBy~psCategory") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~psCategory"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~psCategory")))) {
                    query += " ORDER BY PS_CATEGORY ";
                    query += String.valueOf(parameters.get("orderBy~psCategory"));
                }
                if (parameters.get("orderBy~psTradeClass") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~psTradeClass"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~psTradeClass")))) {
                    query += " ORDER BY PS_TRADE_CLASS ";
                    query += String.valueOf(parameters.get("orderBy~psTradeClass"));
                }
                if (parameters.get("orderBy~psDesignation") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~psDesignation"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~psDesignation")))) {
                    query += " ORDER BY PS_DESIGNATION ";
                    query += String.valueOf(parameters.get("orderBy~psDesignation"));
                }
                if (parameters.get("orderBy~psStatus") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~psStatus"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~psStatus")))) {
                    query += " ORDER BY STATUS ";
                    query += String.valueOf(parameters.get("orderBy~psStatus"));
                }
                if (parameters.get("orderBy~psStartDate") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~psStartDate"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~psStartDate")))) {
                    query += " ORDER BY PS.PS_START_DATE ";
                    query += String.valueOf(parameters.get("orderBy~psStartDate"));
                }
                if (parameters.get("orderBy~psEndDate") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~psEndDate"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~psEndDate")))) {
                    query += " ORDER BY PS.PS_END_DATE ";
                    query += String.valueOf(parameters.get("orderBy~psEndDate"));
                }
            }
            query += " OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY";
        } else if (Constants.RS.equals(moduleName)) {
            query = "select DISTINCT RS.RS_MODEL_SID,RS.RS_ID,RS.RS_NO,RS.RS_NAME,HTR.DESCRIPTION AS RS_TYPE,HTRP.DESCRIPTION as REBATE_PROGRAM_TYPE \n"
                    + " ,HTCAT.DESCRIPTION AS RS_CATEGORY,HTTC.DESCRIPTION AS TRADE_CLASS, HTDES.DESCRIPTION as RS_DESIGNATION,\n"
                    + "     HTS.DESCRIPTION AS STATUS,RS.RS_START_DATE,\n"
                    + "  RS.RS_END_DATE\n"
                    + "  from RS_MODEL RS JOIN\n"
                    + "  RS_CONTRACT CFC ON RS.RS_MODEL_SID = CFC.RS_MODEL_SID JOIN\n"
                    + "  HELPER_TABLE HTS ON RS.RS_STATUS = HTS.HELPER_TABLE_SID JOIN\n"
                    + "  HELPER_TABLE HTR ON RS.RS_TYPE = HTR.HELPER_TABLE_SID JOIN\n"
                    + "  HELPER_TABLE HTRP ON RS.REBATE_PROGRAM_TYPE = HTRP.HELPER_TABLE_SID JOIN\n"
                    + "  HELPER_TABLE HTCAT ON RS.RS_CATEGORY = HTCAT.HELPER_TABLE_SID JOIN\n"
                    + "  HELPER_TABLE HTTC ON RS.RS_TRADE_CLASS = HTTC.HELPER_TABLE_SID JOIN"
                    + "  HELPER_TABLE HTDES ON RS.RS_DESIGNATION = HTDES.HELPER_TABLE_SID "
                    + " AND RS.RS_ID like '" + astToPerConverter(binderDTO.getRsId()) + "' AND RS.RS_NO like '" + astToPerConverter(binderDTO.getRsNo()) + "'  AND RS.RS_NAME like '" + astToPerConverter(binderDTO.getRsName()) + "'"
                    + " AND RS.RS_TYPE like '" + zeroToPerConverter(String.valueOf(binderDTO.getRsType() == null ? StringUtils.EMPTY : binderDTO.getRsType().getId())) + "' AND RS.RS_CATEGORY like '" + zeroToPerConverter(String.valueOf(binderDTO.getRsCategory() == null ? StringUtils.EMPTY : binderDTO.getRsCategory().getId())) + "' AND RS.RS_STATUS like '" + zeroToPerConverter(String.valueOf(binderDTO.getRsStatus() == null ? StringUtils.EMPTY : binderDTO.getRsStatus().getId())) + "' AND RS.REBATE_PROGRAM_TYPE like '" + zeroToPerConverter(String.valueOf(binderDTO.getRebateProgramType() == null ? StringUtils.EMPTY : binderDTO.getRebateProgramType().getId())) + "'";
            if ((binderDTO.getRsStartDate() != null)) {

                query += " AND CONVERT(VARCHAR, RS.RS_START_DATE, 120) like '" + getDBDate((Date) discountChBinder.getField(Constants.RS_START_DATE).getValue()) + "%'\n";
            }

            if ((binderDTO.getRsEndDate() != null)) {
                query += " AND CONVERT(VARCHAR, RS.RS_END_DATE, 120) like '" + getDBDate((Date) discountChBinder.getField(Constants.RS_END_DATE).getValue()) + "%'\n";
            }

            if (parameters.get("filter~rsId") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~rsId"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~rsId")))) {
                query += " AND RS.RS_ID like '";
                query += String.valueOf(parameters.get("filter~rsId")) + "' ";
            }
            if (parameters.get("filter~rsNo") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~rsNo"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~rsNo")))) {
                query += " AND RS.RS_NO like '";
                query += String.valueOf(parameters.get("filter~rsNo")) + "' ";
            }
            if (parameters.get("filter~rsName") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~rsName"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~rsName")))) {
                query += " AND RS.RS_NAME like '";
                query += String.valueOf(parameters.get("filter~rsName")) + "' ";
            }
            if (parameters.get("filter~rsType") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~rsType"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~rsType")))) {
                query += " AND RS.RS_TYPE like '";
                query += String.valueOf(parameters.get("filter~rsType")) + "' ";
            }
            if (parameters.get("filter~rsCategory") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~rsCategory"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~rsCategory")))) {
                query += " AND RS.RS_CATEGORY like '";
                query += String.valueOf(parameters.get("filter~rsCategory")) + "' ";
            }
            if (parameters.get("filter~rsTradeClass") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~rsTradeClass"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~rsTradeClass")))) {
                query += " AND RS.RS_TRADE_CLASS like '";
                query += String.valueOf(parameters.get("filter~rsTradeClass")) + "' ";
            }
            if (parameters.get("filter~rsDesignation") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~rsDesignation"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~rsDesignation")))) {
                query += " AND RS.RS_DESIGNATION like '";
                query += String.valueOf(parameters.get("filter~rsDesignation")) + "' ";
            }
            if (parameters.get("filter~rebateProgramType") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~rebateProgramType"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~rebateProgramType")))) {
                query += " AND RS.REBATE_PROGRAM_TYPE like '";
                query += String.valueOf(parameters.get("filter~rebateProgramType")) + "' ";
            }
            if (parameters.get("filter~rsStatus") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~rsStatus"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~rsStatus")))) {
                query += " AND RS.RS_STATUS like '";
                query += String.valueOf(parameters.get("filter~rsStatus")) + "' ";
            }
            if ((parameters.get("filter~rsStartDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("filter~rsStartDatefrom"))))) {
                query += " AND  RS.RS_START_DATE >='" + String.valueOf(parameters.get("filter~rsStartDatefrom")) + "'";
            }
            if ((parameters.get("filter~rsStartDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("filter~rsStartDateto"))))) {
                query += " AND  RS.RS_START_DATE <='" + String.valueOf(parameters.get("filter~rsStartDateto")) + "'";
            }
            if ((parameters.get("filter~rsEndDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("filter~rsEndDatefrom"))))) {
                query += " AND  RS.RS_END_DATE >='" + String.valueOf(parameters.get("filter~rsEndDatefrom")) + "'";
            }
            if ((parameters.get("filter~rsEndDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("filter~rsEndDateto"))))) {
                query += " AND  RS.RS_END_DATE <='" + String.valueOf(parameters.get("filter~rsEndDateto")) + "'";
            }
            if ((parameters.get(Constants.IS_ORDERED) == null || "false".equalsIgnoreCase(String.valueOf(parameters.get(Constants.IS_ORDERED))))) {
                query += " ORDER BY RS.RS_MODEL_SID ";
            } else if (parameters.get(Constants.IS_ORDERED) != null && Constants.TRUE.equalsIgnoreCase(String.valueOf(parameters.get(Constants.IS_ORDERED)))) {
                if (parameters.get("orderBy~rsId") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~rsId"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~rsId")))) {
                    query += " ORDER BY RS.RS_ID ";
                    query += String.valueOf(parameters.get("orderBy~rsId"));
                }
                if (parameters.get("orderBy~rsNo") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~rsNo"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~rsNo")))) {
                    query += " ORDER BY RS.RS_NO ";
                    query += String.valueOf(parameters.get("orderBy~rsNo"));
                }
                if (parameters.get("orderBy~rsName") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~rsName"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~rsName")))) {
                    query += " ORDER BY RS.RS_NAME ";
                    query += String.valueOf(parameters.get("orderBy~rsName"));
                }
                if (parameters.get("orderBy~rsType") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~rsType"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~rsType")))) {
                    query += " ORDER BY RS_TYPE ";
                    query += String.valueOf(parameters.get("orderBy~rsType"));
                }
                if (parameters.get("orderBy~rsCategory") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~rsCategory"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~rsCategory")))) {
                    query += " ORDER BY RS_CATEGORY ";
                    query += String.valueOf(parameters.get("orderBy~rsCategory"));
                }
                if (parameters.get("orderBy~rsTradeClass") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~rsTradeClass"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~rsTradeClass")))) {
                    query += " ORDER BY RS_TRADE_CLASS ";
                    query += String.valueOf(parameters.get("orderBy~rsTradeClass"));
                }
                if (parameters.get("orderBy~rsDesignation") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~rsDesignation"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~rsDesignation")))) {
                    query += " ORDER BY RS_DESIGNATION ";
                    query += String.valueOf(parameters.get("orderBy~rsDesignation"));
                }
                if (parameters.get("orderBy~rebateProgramType") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~rebateProgramType"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~rebateProgramType")))) {
                    query += " ORDER BY REBATE_PROGRAM_TYPE ";
                    query += String.valueOf(parameters.get("orderBy~rebateProgramType"));
                }
                if (parameters.get("orderBy~rsStatus") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~rsStatus"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~rsStatus")))) {
                    query += " ORDER BY STATUS ";
                    query += String.valueOf(parameters.get("orderBy~rsStatus"));
                }
                if (parameters.get("orderBy~rsStartDate") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~rsStartDate"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~rsStartDate")))) {
                    query += " ORDER BY RS.RS_START_DATE ";
                    query += String.valueOf(parameters.get("orderBy~rsStartDate"));
                }
                if (parameters.get("orderBy~rsEndDate") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~rsEndDate"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~rsEndDate")))) {
                    query += " ORDER BY RS.RS_END_DATE ";
                    query += String.valueOf(parameters.get("orderBy~rsEndDate"));
                }
            }
            query += " OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY";
        }
        return query;
    }

    public String getSearchValuesCount(CustomFieldGroup discountChBinder, String moduleName, Set<Container.Filter> filters) {
        String query = StringUtils.EMPTY;
        LookupDTO binderDTO = ((BeanItem<LookupDTO>) (discountChBinder.getItemDataSource())).getBean();
        Map<String, Object> parameters = new HashMap<String, Object>();
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                    parameters.put("filter~" + stringFilter.getPropertyId(), filterString);
                }
            }
        }
        if (Constants.CFP.equals(moduleName)) {
            query += "select distinct CM.CFP_ID\n"
                    + " from CFP_MODEL CM JOIN\n"
                    + " CFP_CONTRACT CFC ON CM.CFP_MODEL_SID = CFC.CFP_MODEL_SID"
                    + " AND CM.CFP_ID like '" + astToPerConverter(binderDTO.getCfpId()) + "' AND CM.CFP_NO like '" + astToPerConverter(binderDTO.getCfpNo()) + "'  AND CM.CFP_NAME like '" + astToPerConverter(binderDTO.getCfpName()) + "'"
                    + " AND CM.CFP_TYPE like '" + zeroToPerConverter(String.valueOf(binderDTO.getCfpType() == null ? StringUtils.EMPTY : binderDTO.getCfpType().getId())) + "' AND CM.CFP_CATEGORY like '" + zeroToPerConverter(String.valueOf(binderDTO.getCfpCategory() == null ? StringUtils.EMPTY : binderDTO.getCfpCategory().getId())) + "' AND CM.CFP_STATUS like '" + zeroToPerConverter(String.valueOf(binderDTO.getCfpStatus() == null ? StringUtils.EMPTY : binderDTO.getCfpStatus().getId())) + "'";
            if ((binderDTO.getCfpStartDate() != null)) {
                query += "AND CM.CFP_START_DATE = '" + getDBDate((Date) discountChBinder.getField(Constants.CFP_START_DATE).getValue()) + "'\n";
            }

            if ((binderDTO.getCfpEndDate() != null)) {
                query += "AND CM.CFP_END_DATE = '" + getDBDate((Date) discountChBinder.getField(Constants.CFP_END_DATE).getValue()) + "'\n";
            }

            if (parameters.get("filter~cfpId") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~cfpId"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~cfpId")))) {
                query += " AND cm.CFP_ID like '";
                query += String.valueOf(parameters.get("filter~cfpId")) + "' ";
            }
            if (parameters.get("filter~cfpNo") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~cfpNo"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~cfpNo")))) {
                query += " AND cm.CFP_NO like '";
                query += String.valueOf(parameters.get("filter~cfpNo")) + "' ";
            }
            if (parameters.get("filter~cfpName") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~cfpName"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~cfpName")))) {
                query += " AND cm.CFP_NAME like '";
                query += String.valueOf(parameters.get("filter~cfpName")) + "' ";
            }
            if (parameters.get("filter~cfpType") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~cfpType"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~cfpType")))) {
                query += " AND cm.CFP_TYPE like '";
                query += String.valueOf(parameters.get("filter~cfpType")) + "' ";
            }
            if (parameters.get("filter~cfpCategory") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~cfpCategory"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~cfpCategory")))) {
                query += " AND cm.CFP_CATEGORY like '";
                query += String.valueOf(parameters.get("filter~cfpCategory")) + "' ";
            }
            if (parameters.get("filter~cfpDesignation") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~cfpDesignation"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~cfpDesignation")))) {
                query += " AND cm.CFP_DESIGNATION like '";
                query += String.valueOf(parameters.get("filter~cfpDesignation")) + "' ";
            }
            if (parameters.get("filter~cfpStatus") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~cfpStatus"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~cfpStatus")))) {
                query += " AND cm.CFP_STATUS like '";
                query += String.valueOf(parameters.get("filter~cfpStatus")) + "' ";
            }
            if (parameters.get("filter~cfpTradeClass") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~cfpTradeClass"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~cfpTradeClass")))) {
                query += " AND cm.CFP_TRADE_CLASS like '";
                query += String.valueOf(parameters.get("filter~cfpTradeClass")) + "' ";
            }
            if (parameters.get("filter~cfpStartDate") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~cfpStartDate"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~cfpStartDate")))) {
                query += " AND cm.CFP_START_DATE like '";
                query += String.valueOf(parameters.get("filter~cfpStartDate")) + "' ";
            }
            if (parameters.get("filter~cfpEndDate") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~cfpEndDate"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~cfpEndDate")))) {
                query += " AND cm.CFP_END_DATE like '";
                query += String.valueOf(parameters.get("filter~cfpEndDate")) + "' ";
            }
        } else if (Constants.IFP.equals(moduleName)) {
            query += "select DISTINCT IM.IFP_MODEL_SID\n"
                    + " from IFP_MODEL IM JOIN\n"
                    + " IFP_CONTRACT CFC ON IM.IFP_MODEL_SID = CFC.IFP_MODEL_SID"
                    + " AND IM.IFP_NO like '" + astToPerConverter(binderDTO.getIfpNo()) + "'  AND IM.IFP_NAME like '" + astToPerConverter(binderDTO.getIfpName()) + "'"
                    + " AND IM.IFP_TYPE like '" + zeroToPerConverter(String.valueOf(binderDTO.getIfpType() == null ? StringUtils.EMPTY : binderDTO.getIfpType().getId())) + "' AND IM.IFP_CATEGORY like '" + zeroToPerConverter(String.valueOf(binderDTO.getIfpCategory() == null ? StringUtils.EMPTY : binderDTO.getIfpCategory().getId())) + "' AND IM.IFP_STATUS like '" + zeroToPerConverter(String.valueOf(binderDTO.getIfpStatus() == null ? StringUtils.EMPTY : binderDTO.getIfpStatus().getId())) + "'";
            if ((binderDTO.getIfpStartDate() != null)) {
                query += "AND IM.IFP_START_DATE = '" + getDBDate((Date) discountChBinder.getField(Constants.IFP_START_DATE).getValue()) + "'\n";
            }

            if ((binderDTO.getIfpEndDate() != null)) {
                query += "AND IM.IFP_END_DATE = '" + getDBDate((Date) discountChBinder.getField(Constants.IFP_END_DATE).getValue()) + "'\n";
            }
            if (parameters.get("filter~ifpNo") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~ifpNo"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~ifpNo")))) {
                query += " AND IM.IFP_NO like '";
                query += String.valueOf(parameters.get("filter~ifpNo")) + "' ";
            }
            if (parameters.get("filter~ifpName") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~ifpName"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~ifpName")))) {
                query += " AND IM.IFP_NAME like '";
                query += String.valueOf(parameters.get("filter~ifpName")) + "' ";
            }
            if (parameters.get("filter~ifpType") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~ifpType"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~ifpType")))) {
                query += " AND IM.IFP_TYPE like '";
                query += String.valueOf(parameters.get("filter~ifpType")) + "' ";
            }
            if (parameters.get("filter~ifpCategory") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~ifpCategory"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~ifpCategory")))) {
                query += " AND IM.IFP_CATEGORY like '";
                query += String.valueOf(parameters.get("filter~ifpCategory")) + "' ";
            }
            if (parameters.get("filter~ifpDesignation") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~ifpDesignation"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~ifpDesignation")))) {
                query += " AND IM.IFP_DESIGNATION like '";
                query += String.valueOf(parameters.get("filter~ifpDesignation")) + "' ";
            }
            if (parameters.get("filter~ifpStatus") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~ifpStatus"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~ifpStatus")))) {
                query += " AND IM.IFP_STATUS like '";
                query += String.valueOf(parameters.get("filter~ifpStatus")) + "' ";
            }
            if (parameters.get("filter~ifpStartDate") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~ifpStartDate"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~ifpStartDate")))) {
                query += " AND IM.IFP_START_DATE like '";
                query += String.valueOf(parameters.get("filter~ifpStartDate")) + "' ";
            }
            if (parameters.get("filter~ifpEndDate") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~ifpEndDate"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~ifpEndDate")))) {
                query += " AND IM.IFP_END_DATE like '";
                query += String.valueOf(parameters.get("filter~ifpEndDate")) + "' ";
            }
        } else if (Constants.PS.equals(moduleName)) {
            query = "select DISTINCT PS.PS_MODEL_SID\n"
                    + " from PS_MODEL PS JOIN\n"
                    + " PS_CONTRACT CFC ON PS.PS_MODEL_SID = CFC.PS_MODEL_SID"
                    + " AND PS.PS_NO like '" + astToPerConverter(binderDTO.getPsNo()) + "'  AND PS.PS_NAME like '" + astToPerConverter(binderDTO.getPsName()) + "'"
                    + " AND PS.PS_TYPE like '" + zeroToPerConverter(String.valueOf(binderDTO.getPsType() == null ? StringUtils.EMPTY : binderDTO.getPsType().getId())) + "' AND PS.PS_CATEGORY like '" + zeroToPerConverter(String.valueOf(binderDTO.getPsCategory() == null ? StringUtils.EMPTY : binderDTO.getPsCategory().getId())) + "' AND PS.PS_STATUS like '" + zeroToPerConverter(String.valueOf(binderDTO.getPsStatus() == null ? StringUtils.EMPTY : binderDTO.getPsStatus().getId())) + "'";
            if ((binderDTO.getPsStartDate() != null)) {
                query += "AND PS.PS_START_DATE = '" + getDBDate((Date) discountChBinder.getField(Constants.PS_START_DATE).getValue()) + "'\n";
            }

            if ((binderDTO.getPsEndDate() != null)) {
                query += "AND PS.PS_END_DATE = '" + getDBDate((Date) discountChBinder.getField(Constants.PS_END_DATE).getValue()) + "'\n";
            }
            if (parameters.get("filter~psNo") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~psNo"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~psNo")))) {
                query += " AND PS.PS_NO like '";
                query += String.valueOf(parameters.get("filter~psNo")) + "' ";
            }
            if (parameters.get("filter~psName") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~psName"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~psName")))) {
                query += " AND PS.PS_NAME like '";
                query += String.valueOf(parameters.get("filter~psName")) + "' ";
            }
            if (parameters.get("filter~psType") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~psType"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~psType")))) {
                query += " AND PS.PS_TYPE like '";
                query += String.valueOf(parameters.get("filter~psType")) + "' ";
            }
            if (parameters.get("filter~psCategory") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~psCategory"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~psCategory")))) {
                query += " AND PS.PS_CATEGORY like '";
                query += String.valueOf(parameters.get("filter~psCategory")) + "' ";
            }
            if (parameters.get("filter~psTradeClass") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~psTradeClass"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~psTradeClass")))) {
                query += " AND PS.PS_TRADE_CLASS like '";
                query += String.valueOf(parameters.get("filter~psTradeClass")) + "' ";
            }
            if (parameters.get("filter~psDesignation") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~psDesignation"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~psDesignation")))) {
                query += " AND PS.PS_DESIGNATION like '";
                query += String.valueOf(parameters.get("filter~psDesignation")) + "' ";
            }
            if (parameters.get("filter~psStatus") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~psStatus"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~psStatus")))) {
                query += " AND PS.PS_STATUS like '";
                query += String.valueOf(parameters.get("filter~psStatus")) + "' ";
            }
            if (parameters.get("filter~psStartDate") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~psStartDate"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~psStartDate")))) {
                query += " AND PS.PS_START_DATE like '";
                query += String.valueOf(parameters.get("filter~psStartDate")) + "' ";
            }
            if (parameters.get("filter~psEndDate") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~psEndDate"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~psEndDate")))) {
                query += " AND PS.PS_END_DATE like '";
                query += String.valueOf(parameters.get("filter~psEndDate")) + "' ";
            }
        } else if (Constants.RS.equals(moduleName)) {
            query = "select DISTINCT RS.RS_MODEL_SID \n"
                    + " from RS_MODEL RS JOIN\n"
                    + " RS_CONTRACT CFC ON RS.RS_MODEL_SID = CFC.RS_MODEL_SID"
                    + " AND RS.RS_ID like '" + astToPerConverter(binderDTO.getRsId()) + "' AND RS.RS_NO like '" + astToPerConverter(binderDTO.getRsNo()) + "'  AND RS.RS_NAME like '" + astToPerConverter(binderDTO.getRsName()) + "'"
                    + " AND RS.RS_TYPE like '" + zeroToPerConverter(String.valueOf(binderDTO.getRsType() == null ? StringUtils.EMPTY : binderDTO.getRsType().getId())) + "' AND RS.RS_CATEGORY like '" + zeroToPerConverter(String.valueOf(binderDTO.getRsCategory() == null ? StringUtils.EMPTY : binderDTO.getRsCategory().getId())) + "' AND RS.RS_STATUS like '" + zeroToPerConverter(String.valueOf(binderDTO.getRsStatus() == null ? StringUtils.EMPTY : binderDTO.getRsStatus().getId())) + "' AND RS.REBATE_PROGRAM_TYPE like '" + zeroToPerConverter(String.valueOf(binderDTO.getRebateProgramType() == null ? StringUtils.EMPTY : binderDTO.getRebateProgramType().getId())) + "'";
            if ((binderDTO.getRsStartDate() != null)) {
                query += " AND CONVERT(VARCHAR, RS.RS_START_DATE, 120) like '" + getDBDate((Date) discountChBinder.getField(Constants.RS_START_DATE).getValue()) + "%'\n";
            }

            if ((binderDTO.getRsEndDate() != null)) {
                query += " AND CONVERT(VARCHAR, RS.RS_END_DATE, 120) like '" + getDBDate((Date) discountChBinder.getField(Constants.RS_END_DATE).getValue()) + "%'\n";
            }
            if (parameters.get("filter~rsId") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~rsId"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~rsId")))) {
                query += " AND RS.RS_ID like '";
                query += String.valueOf(parameters.get("filter~rsId")) + "' ";
            }
            if (parameters.get("filter~rsNo") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~rsNo"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~rsNo")))) {
                query += " AND RS.RS_NO like '";
                query += String.valueOf(parameters.get("filter~rsNo")) + "' ";
            }
            if (parameters.get("filter~rsName") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~rsName"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~rsName")))) {
                query += " AND RS.RS_NAME like '";
                query += String.valueOf(parameters.get("filter~rsName")) + "' ";
            }
            if (parameters.get("filter~rsType") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~rsType"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~rsType")))) {
                query += " AND RS.RS_TYPE like '";
                query += String.valueOf(parameters.get("filter~rsType")) + "' ";
            }
            if (parameters.get("filter~rsCategory") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~rsCategory"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~rsCategory")))) {
                query += " AND RS.RS_CATEGORY like '";
                query += String.valueOf(parameters.get("filter~rsCategory")) + "' ";
            }
            if (parameters.get("filter~rsTradeClass") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~rsTradeClass"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~rsTradeClass")))) {
                query += " AND RS.RS_TRADE_CLASS like '";
                query += String.valueOf(parameters.get("filter~rsTradeClass")) + "' ";
            }
            if (parameters.get("filter~rsDesignation") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~rsDesignation"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~rsDesignation")))) {
                query += " AND RS.RS_DESIGNATION like '";
                query += String.valueOf(parameters.get("filter~rsDesignation")) + "' ";
            }
            if (parameters.get("filter~rebateProgramType") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~rebateProgramType"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~rebateProgramType")))) {
                query += " AND RS.REBATE_PROGRAM_TYPE like '";
                query += String.valueOf(parameters.get("filter~rebateProgramType")) + "' ";
            }
            if (parameters.get("filter~rsStatus") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~rsStatus"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~rsStatus")))) {
                query += " AND RS.RS_STATUS like '";
                query += String.valueOf(parameters.get("filter~rsStatus")) + "' ";
            }
            if (parameters.get("filter~rsStartDate") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~rsStartDate"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~rsStartDate")))) {
                query += " AND RS.RS_START_DATE like '";
                query += String.valueOf(parameters.get("filter~rsStartDate")) + "' ";
            }
            if (parameters.get("filter~rsEndDate") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~rsEndDate"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~rsEndDate")))) {
                query += " AND RS.RS_END_DATE like '";
                query += String.valueOf(parameters.get("filter~rsEndDate")) + "' ";
            }
        }
        return query;
    }

    public String LoadNewCompany(Map<String, String> input) {

        String query = "select distinct cm.COMPANY_MASTER_SID,cm.COMPANY_ID,cm.COMPANY_NO,cm.COMPANY_NAME,COMPANY_TYPE.DESCRIPTION as type,Status.DESCRIPTION as status,CATEGORY.DESCRIPTION as category,"
                + " tradeclass.DESCRIPTION  as tradeclassvalue from  COMPANY_MASTER cm "
                + " inner join HELPER_TABLE Status on Status.HELPER_TABLE_SID=cm.COMPANY_STATUS "
                + " inner join HELPER_TABLE COMPANY_TYPE on COMPANY_TYPE.HELPER_TABLE_SID=cm.COMPANY_TYPE  "
                + " inner join COMPANY_TRADE_CLASS CT on CT.COMPANY_MASTER_SID=cm.COMPANY_MASTER_SID inner join HELPER_TABLE tradeclass on tradeclass.HELPER_TABLE_SID=CT.COMPANY_TRADE_CLASS"
                + " left  join HELPER_TABLE CATEGORY on CATEGORY.HELPER_TABLE_SID=cm.COMPANY_CATEGORY "
                + " where cm.INBOUND_STATUS<>'D' and cm.RECORD_LOCK_STATUS=0 "
                + " and CT.INBOUND_STATUS<>'D' and CT.RECORD_LOCK_STATUS=0";

        String searchfield = StringUtils.EMPTY;
        String searchvalue = StringUtils.EMPTY;
        for (Map.Entry<String, String> entry : input.entrySet()) {
            searchfield = entry.getKey();
            searchvalue = entry.getValue();
        }
        if (searchfield.contains("STATUS") || searchfield.contains("CLASS") || searchfield.contains("CATEGORY") || searchfield.contains("TYPE")) {
            query = query + " and " + StringUtils.EMPTY + searchfield + "=" + searchvalue;
        } else {
            query = query + " and " + StringUtils.EMPTY + searchfield + " like '" + searchvalue.replace(CHAR_ASTERISK, CHAR_PERCENT) + "'";

        }

        return query;
    }

    public String LoadItemCompany(Map<String, String> input) {
        String query = "select ITEM_ID,ITEM_NAME,ITEM_NO,status.DESCRIPTION as status,itype.DESCRIPTION as type,br.BRAND_NAME,HT.DESCRIPTION as form,str.DESCRIPTION as atrength,tc.DESCRIPTION as tc,ITEM_START_DATE,ITEM_END_DATE,im.ITEM_MASTER_SID from ITEM_MASTER im\n"
                + "inner join HELPER_TABLE HT on HT.HELPER_TABLE_SID=im.FORM inner join HELPER_TABLE status on\n"
                + "status.HELPER_TABLE_SID=im.ITEM_STATUS inner join HELPER_TABLE str on str.HELPER_TABLE_SID=im.STRENGTH\n"
                + "left join HELPER_TABLE  itype on itype.HELPER_TABLE_SID=im.ITEM_TYPE left join HELPER_TABLE tc on tc.HELPER_TABLE_SID=im.THERAPEUTIC_CLASS\n"
                + "inner join BRAND_MASTER br on br.BRAND_MASTER_SID=im.BRAND_MASTER_SID\n"
                + "where im.RECORD_LOCK_STATUS=0 and im.INBOUND_STATUS<>'D' and br.INBOUND_STATUS<>'D'";
        String searchfield = StringUtils.EMPTY;
        String searchvalue = StringUtils.EMPTY;
        for (Map.Entry<String, String> entry : input.entrySet()) {
            searchfield = entry.getKey();
            searchvalue = entry.getValue();
        }
        if (searchfield.contains("ITEM_ID") || searchfield.contains("NAME") || searchfield.contains("NO")) {
            query = query + " and " + StringUtils.EMPTY + searchfield + " like '" + searchvalue.replace(CHAR_ASTERISK, CHAR_PERCENT) + "'";
        } else {
            query = query + " and " + StringUtils.EMPTY + searchfield + "=" + searchvalue;
        }

        return query;
    }

    public String astToPerConverter(final String inputString) {
        return StringUtils.isBlank(inputString) || Constants.NULL.equals(inputString) ? "%" : inputString.replace("*", "%");
    }

    public String zeroToPerConverter(final String inputString) {
        return StringUtils.isBlank(inputString) || Constants.ZEROSTRING.equals(inputString) || Constants.NULL.equals(inputString) ? "%" : inputString.replace("*", "%");
    }

    public String getPureValue(final String inputString) {
        return StringUtils.isBlank(inputString) || Constants.NULL.equals(inputString) ? StringUtils.EMPTY : inputString;
    }

    public String getDBDate(final Date input) {
        SimpleDateFormat temp = new SimpleDateFormat("yyyy-MM-dd");
        return input == null ? StringUtils.EMPTY : temp.format(input);
    }

    public String LoadmassupdateCompany(String sid) {
        String query = "select distinct cm.COMPANY_MASTER_SID,cm.COMPANY_NO,cm.COMPANY_NAME,cm.COMPANY_STATUS as status ,"
                + " cm.COMPANY_START_DATE,cm.COMPANY_END_DATE,Status.DESCRIPTION as statusdescription from  COMPANY_MASTER cm "
                + " inner join HELPER_TABLE Status on Status.HELPER_TABLE_SID=cm.COMPANY_STATUS "
                + " inner join HELPER_TABLE COMPANY_TYPE on COMPANY_TYPE.HELPER_TABLE_SID=cm.COMPANY_TYPE  "
                + " inner join COMPANY_TRADE_CLASS CT on CT.COMPANY_MASTER_SID=cm.COMPANY_MASTER_SID inner join HELPER_TABLE tradeclass on tradeclass.HELPER_TABLE_SID=CT.COMPANY_TRADE_CLASS"
                + " left  join HELPER_TABLE CATEGORY on CATEGORY.HELPER_TABLE_SID=cm.COMPANY_CATEGORY "
                + " where cm.INBOUND_STATUS<>'D' "
//                + "and cm.RECORD_LOCK_STATUS=0 "
                + " and CT.INBOUND_STATUS<>'D' "
//                + "and CT.RECORD_LOCK_STATUS=0 "
                + "and cm.COMPANY_MASTER_SID in(" + sid + ")";
        return query;
    }

    public String Loadmassupdateitem(String sid) {
        String query = "select ITEM_NAME,ITEM_NO,im.ITEM_STATUS as status,br.BRAND_NAME,ITEM_START_DATE,ITEM_END_DATE,im.ITEM_MASTER_SID,tc.DESCRIPTION as tc,status.DESCRIPTION as statusdescription from ITEM_MASTER im\n"
                + "inner join HELPER_TABLE HT on HT.HELPER_TABLE_SID=im.FORM inner join HELPER_TABLE status on\n"
                + "status.HELPER_TABLE_SID=im.ITEM_STATUS inner join HELPER_TABLE str on str.HELPER_TABLE_SID=im.STRENGTH\n"
                + "left join HELPER_TABLE  itype on itype.HELPER_TABLE_SID=im.ITEM_TYPE left join HELPER_TABLE tc on tc.HELPER_TABLE_SID=im.THERAPEUTIC_CLASS\n"
                + "inner join BRAND_MASTER br on br.BRAND_MASTER_SID=im.BRAND_MASTER_SID\n"
                + "where   im.INBOUND_STATUS<>'D' and br.INBOUND_STATUS<>'D' and im.ITEM_MASTER_SID in(" + sid + ")";
        return query;
    }

    public String getTempTableValue(String temptableSId) {
        String query = " select ITEM_ID,ITEM_NO,ITEM_NAME,ITEM_STATUS,START_DATE,FORMULA_NAME from GCM_GLOBAL_DETAILS where GCM_GLOBAL_DETAILS_SID='" + temptableSId + "'";
        return query;
    }

    public String getActuals(RemoveDiscountDto dto) {
        String prjQuery = StringUtils.EMPTY;
        String selectQuery = StringUtils.EMPTY;
        if ("Channel".equals(dto.getForecastingType())) {
            selectQuery = "select max(NMSP.ACTUAL_SALES ) as Sales,0 as Units from\n";
            prjQuery = " join CH_ACTUAL_DISCOUNT NMSP\n";
        } else if (Constants.IndicatorConstants.MANDATED.equals(dto.getForecastingType())) {
            selectQuery = "select max(NMSP.ACTUAL_SALES ) as Sales,max(NMSP.ACTUAL_UNITS) as Units from\n";
            prjQuery = " join M_ACTUAL_SALES NMSP\n";
        } else if (Constants.IndicatorConstants.NON_MANDATED.equals(dto.getForecastingType())) {
            selectQuery = "select max(NMSP.ACTUAL_SALES ) as Sales,max(NMSP.ACTUAL_UNITS) as Units from\n";
            prjQuery = " join NM_ACTUAL_SALES NMSP\n";
        }
        String query = "select max(NMSP.ACTUAL_SALES ) as Sales,max(NMSP.ACTUAL_UNITS) as Units from\n"
                + " PROJECTION_DETAILS PD\n"
                + "join CCP_DETAILS CCPD ON CCPD.CCP_DETAILS_SID=PD.CCP_DETAILS_SID\n"
                + "AND CCPD.CONTRACT_MASTER_SID=" + dto.getContractSid() + "\n"
                + " join NM_ACTUAL_SALES NMSP\n"
                + "ON  NMSP.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
                + " join CONTRACT_MASTER CTM ON CCPD.CONTRACT_MASTER_SID = CTM.CONTRACT_MASTER_SID\n"
                + "  join RS_CONTRACT RSC ON RSC.CONTRACT_MASTER_SID = CCPD.CONTRACT_MASTER_SID\n"
                + "and RSC.RS_CONTRACT_SID=" + dto.getRsContractSid()
                + "WHERE PD.PROJECTION_MASTER_SID= " + dto.getProjectionSid();
        return query;
    }

    /**
     * Component - Item Search Count
     *
     * @param newDiscountTabDto
     * @return
     */
    public String getTpItemsCount(ComponentInfoDTO newDiscountTabDto) {
        String query = "select count(IM.ITEM_MASTER_SID) \n"
                + "from ITEM_MASTER IM \n"
                + "join BRAND_MASTER BM ON BM.BRAND_MASTER_SID=IM.BRAND_MASTER_SID \n"
                + "join HELPER_TABLE HT ON HT.HELPER_TABLE_SID=IM.THERAPEUTIC_CLASS \n"
                + "join HELPER_TABLE HT1 ON HT1.HELPER_TABLE_SID=IM.ITEM_STATUS \n"
                + "join HELPER_TABLE HT2 ON HT2.HELPER_TABLE_SID=IM.FORM \n"
                + "join HELPER_TABLE HT3 ON HT3.HELPER_TABLE_SID=IM.STRENGTH \n"
                + "WHERE IM.INBOUND_STATUS <> 'D'";

        if (getNull(newDiscountTabDto.getSearchFieldValue())) {
            String brandName = newDiscountTabDto.getSearchFieldValue();
            brandName = brandName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            query += " AND BM.BRAND_NAME like '" + brandName;
            query += "'";
        }
        return query;
    }

    /**
     * Component Item Search Query - Brand
     *
     * @param newDiscountTabDto
     * @param qCount
     * @return
     */
    public String getTpItems(ComponentInfoDTO newDiscountTabDto, boolean qCount) {
        String query = "select distinct \n"
                + "IM.ITEM_MASTER_SID,\n"
                + "IM.ITEM_ID,\n"
                + "IM.ITEM_NAME,\n"
                + "IM.ITEM_NO,\n"
                + "HT.DESCRIPTION AS therapyClass,\n"
                + "BM.BRAND_NAME,\n"
                + "HT2.DESCRIPTION AS Form,\n"
                + "HT3.DESCRIPTION AS strength,\n"
                + "HT1.DESCRIPTION AS itemStatus,\n"
                + "IM.ITEM_START_DATE,\n"
                + "IM.ITEM_END_DATE \n"
                + "from ITEM_MASTER IM \n"
                + "join BRAND_MASTER BM ON BM.BRAND_MASTER_SID=IM.BRAND_MASTER_SID \n"
                + "join HELPER_TABLE HT ON HT.HELPER_TABLE_SID=IM.THERAPEUTIC_CLASS \n"
                + "join HELPER_TABLE HT1 ON HT1.HELPER_TABLE_SID=IM.ITEM_STATUS \n"
                + "join HELPER_TABLE HT2 ON HT2.HELPER_TABLE_SID=IM.FORM \n"
                + "join HELPER_TABLE HT3 ON HT3.HELPER_TABLE_SID=IM.STRENGTH \n"
                + "WHERE IM.INBOUND_STATUS <> 'D'";

        if (getNull(newDiscountTabDto.getSearchFieldValue())) {
            String brandName = newDiscountTabDto.getSearchFieldValue();
            brandName = brandName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
            query += " AND BM.BRAND_NAME like '" + brandName;
            query += "'";
        }
        if (!qCount) {
            query += " ORDER BY IM.ITEM_NAME OFFSET " + newDiscountTabDto.getStartIndex() + "  ROWS FETCH NEXT " + newDiscountTabDto.getEndIndex() + " ROWS ONLY";
        }
        return query;
    }

    public String getIFPInfo(String searchValue) {
        String query = "SELECT im.ITEM_MASTER_SID,im.ITEM_NO,im.ITEM_NAME,h3.DESCRIPTION AS THERAPHY,h4.DESCRIPTION AS STATUS,bm.BRAND_NAME,im.ITEM_START_DATE,im.ITEM_END_DATE,ifpd.IFP_MODEL_SID,rd.REBATE_PLAN_MASTER_SID,\n"
                + " rpm.REBATE_PLAN_NAME,rd.FORMULA_ID,rd.FORMULA_METHOD_ID FROM   dbo.ITEM_MASTER im JOIN   dbo.IFP_DETAILS ifpd ON ifpd.ITEM_MASTER_SID = im.ITEM_MASTER_SID";
        query = query + " and ifpd.IFP_MODEL_SID in (" + searchValue + ")";

        query = query + " JOIN   dbo.RS_DETAILS rd ON rd.ITEM_MASTER_SID = ifpd.ITEM_MASTER_SID AND rd.IFP_MODEL_SID = ifpd.IFP_MODEL_SID LEFT JOIN dbo.REBATE_PLAN_MASTER rpm ON rpm.REBATE_PLAN_MASTER_SID=rd.REBATE_PLAN_MASTER_SID \n"
                + "Left join dbo.HELPER_TABLE h3 on h3.HELPER_TABLE_SID=im.THERAPEUTIC_CLASS left join dbo.HELPER_TABLE h4 on h4.HELPER_TABLE_SID=im.ITEM_STATUS left join dbo.BRAND_MASTER bm on bm.BRAND_MASTER_SID=im.BRAND_MASTER_SID";

        return query;
    }

    public String getPSInfo(String searchValue) {
        String query = "select distinct im.ITEM_NO,im.ITEM_NAME,h.DESCRIPTION AS THERAPHY,bm.BRAND_NAME,im.ITEM_STATUS,im.ITEM_START_DATE,im.ITEM_END_DATE,h3.DESCRIPTION AS PRICETYPE,\n"
                + " psd.PRICE_PROTECTION_START_DATE,ifpd.IFP_MODEL_SID,im.ITEM_MASTER_SID,psd.PRICE  from ITEM_MASTER im join IMTD_IFP_DETAILS ifpd on im.ITEM_MASTER_SID=ifpd.ITEM_MASTER_SID \n"
                + " and ifpd.IFP_MODEL_SID in (" + searchValue + ")";
        query = query + " left join HELPER_TABLE h on h.HELPER_TABLE_SID=im.THERAPEUTIC_CLASS join BRAND_MASTER bm on bm.BRAND_MASTER_SID=im.BRAND_MASTER_SID \n"
                + " left join PS_DETAILS psd on psd.IFP_MODEL_SID=ifpd.IFP_MODEL_SID and ifpd.ITEM_MASTER_SID=psd.ITEM_MASTER_SID left join HELPER_TABLE h3 on h3.HELPER_TABLE_SID=psd.PRICE_TOLERANCE_TYPE;";
        return query;
    }

    public String getTempTableValueForPS(String temptableSId) {
        String query = " select\n"
                + "ITEM_ID,\n"
                + "ITEM_NO,\n"
                + "ITEM_NAME,\n"
                + "ITEM_STATUS_SID,\n"
                + "START_DATE,\n"
                + "END_DATE,\n"
                + "ITEM_TYPE,\n"
                + "PAYMENT_FREQUENCY,\n"
                + "FORMULA_ID,\n"
                + "FORMULA_NAME,\n"
                + "PAYMENT_METHOD,\n"
                + "CALENDAR\n"
                + "from GCM_GLOBAL_DETAILS\n"
                + "where GCM_GLOBAL_DETAILS_SID='" + temptableSId + "'";
        return query;
    }

    public String getTempTableIFP(String temptableSId) {
        String query = " select iiprd.ITEM_ID,iiprd.ITEM_NAME,iiprd.ITEM_NO,h.DESCRIPTION AS STATUS,iiprd.START_DATE,iiprd.END_DATE,h1.DESCRIPTION AS TYPE,iiprd.IMTD_ITEM_PRICE_REBATE_SID from dbo.IMTD_ITEM_PRICE_REBATE_DETAILS iiprd "
                + " left join dbo.HELPER_TABLE h on h.HELPER_TABLE_SID=iiprd.PS_STATUS join dbo.HELPER_TABLE h1 on h1.HELPER_TABLE_SID=iiprd.ITEM_TYPE and iiprd.IMTD_ITEM_PRICE_REBATE_SID in ( " + temptableSId + ")";
        return query;
    }

    public String contractSearch(final ErrorfulFieldGroup binder) {
        String query = "SELECT DISTINCT CM.CONT_HOLD_COMPANY_MASTER_SID,COMP.COMPANY_NAME,CM.CONTRACT_NAME,CM.CONTRACT_NO,H1.DESCRIPTION AS MARKET_TYPE,CM.START_DATE,CM.END_DATE,CCT.CFP_NAME,IFP_CONT.IFP_NAME,PSC.PS_NAME,\n"
                + "                RSC.RS_NAME,CM.CONTRACT_MASTER_SID,CCT.CFP_CONTRACT_SID,IFP_CONT.IFP_CONTRACT_SID,PSC.PS_CONTRACT_SID,RSC.RS_CONTRACT_SID FROM   CONTRACT_ALIAS_MASTER CAM JOIN CONTRACT_MASTER CM\n"
                + "         ON CAM.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID INNER JOIN CFP_CONTRACT CCT ON CM.CONTRACT_MASTER_SID = CCT.CONTRACT_MASTER_SID  AND CM.INBOUND_STATUS <> 'D'\n"
                + "                  AND CCT.INBOUND_STATUS <> 'D' LEFT JOIN CFP_CONTRACT_DETAILS CCD ON CCT.CFP_CONTRACT_SID = CCD.CFP_CONTRACT_SID LEFT JOIN COMPANY_MASTER CMP_MAS ON CMP_MAS.COMPANY_MASTER_SID = CCD.COMPANY_MASTER_SID\n"
                + "                  AND CMP_MAS.INBOUND_STATUS <> 'D' LEFT JOIN IFP_CONTRACT IFP_CONT ON IFP_CONT.CFP_CONTRACT_SID = CCT.CFP_CONTRACT_SID AND IFP_CONT.INBOUND_STATUS <> 'D'AND CM.CONTRACT_MASTER_SID = IFP_CONT.CONTRACT_MASTER_SID\n"
                + "       LEFT JOIN IFP_CONTRACT_DETAILS ICD ON IFP_CONT.IFP_CONTRACT_SID = ICD.IFP_CONTRACT_SID LEFT JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID = ICD.ITEM_MASTER_SID AND IM.INBOUND_STATUS <> 'D' LEFT JOIN PS_CONTRACT PSC\n"
                + "               ON PSC.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID AND PSC.INBOUND_STATUS <> 'D' LEFT JOIN PS_CONTRACT_DETAILS PSCD ON PSC.PS_CONTRACT_SID = PSCD.PS_CONTRACT_SID AND PSCD.ITEM_MASTER_SID = ICD.ITEM_MASTER_SID\n"
                + "       LEFT JOIN RS_CONTRACT RSC ON RSC.CONTRACT_MASTER_SID = CM.CONTRACT_MASTER_SID AND RSC.INBOUND_STATUS <> 'D' LEFT JOIN RS_CONTRACT_DETAILS RSCD ON RSC.RS_CONTRACT_SID = RSCD.RS_CONTRACT_SID AND RSCD.ITEM_MASTER_SID = ICD.ITEM_MASTER_SID\n"
                + "       JOIN HELPER_TABLE H1 ON CM.CONTRACT_TYPE = H1.HELPER_TABLE_SID LEFT JOIN COMPANY_MASTER COMP ON CM.CONT_HOLD_COMPANY_MASTER_SID = COMP.COMPANY_MASTER_SID WHERE ";

        ContractSearchDTO binderDTO = ((BeanItem<ContractSearchDTO>) (binder.getItemDataSource())).getBean();
        if (!StringUtils.EMPTY.equals(binderDTO.getContractNo()) && !Constants.NULL.equals(binderDTO.getContractNo())) {
            String cNo = String.valueOf(binderDTO.getContractNo()).replaceAll("\\*", "%");
            query = query + " CM.CONTRACT_NO like '" + cNo + "' AND ";
        }
        if (!StringUtils.EMPTY.equals(binderDTO.getContractName()) && !Constants.NULL.equals(binderDTO.getContractName())) {
            String cName = String.valueOf(binderDTO.getContractName()).replaceAll("\\*", "%");
            query = query + " CM.CONTRACT_NAME like '" + cName + "' AND ";
        }

        if (!StringUtils.EMPTY.equals(binderDTO.getHiddenId())) {
            query = query + "  CM.CONT_HOLD_COMPANY_MASTER_SID ='" + binderDTO.getHiddenId() + "' AND ";
        }

        if (binderDTO.getMarketType() != null && !binderDTO.getMarketType().equals(StringUtils.EMPTY) && !binderDTO.getMarketType().equals(Constants.NULL)) {

            query = query + " CM.CONTRACT_TYPE='" + binderDTO.getMarketType() + "' AND";

        }
        if (!StringUtils.EMPTY.equals(binderDTO.getCompanyFamilyPlan()) && !Constants.NULL.equals(binderDTO.getCompanyFamilyPlan())) {
            String cfp = String.valueOf(binderDTO.getCompanyFamilyPlan()).replaceAll("\\*", "%");
            query = query + " CCT.CFP_NAME like '" + cfp + "' AND ";
        }
        if (!StringUtils.EMPTY.equals(binderDTO.getItemFamilyPlan()) && !Constants.NULL.equals(binderDTO.getItemFamilyPlan())) {
            String ifp = String.valueOf(binderDTO.getItemFamilyPlan()).replaceAll("\\*", "%");
            query = query + " IFP_CONT.IFP_NAME like '" + ifp + "' AND ";
        }
        if (!StringUtils.EMPTY.equals(binderDTO.getPriceSchedule()) && !Constants.NULL.equals(binderDTO.getPriceSchedule())) {
            String ps = String.valueOf(binderDTO.getPriceSchedule()).replaceAll("\\*", "%");
            query = query + " PSC.PS_NAME like '" + ps + "' AND ";
        }
        String sDate = Constants.NULL;
        String eDate = Constants.NULL;
        if (binderDTO.getStartDate() != null) {
            sDate = DBDate.format(binderDTO.getStartDate());
        }
        if (binderDTO.getEndDate() != null) {
            eDate = DBDate.format(binderDTO.getEndDate());
        }
        query = query + " CM.START_DATE BETWEEN ISNULL(" + sDate + ",'1965-01-01') AND ISNULL(" + eDate + ",'2065-01-01') AND";
        if (!StringUtils.EMPTY.equals(binderDTO.getAliasNumber()) && !Constants.NULL.equals(binderDTO.getAliasNumber())) {
            String aliasNo = String.valueOf(binderDTO.getAliasNumber()).replaceAll("\\*", "%");
            query = query + " CAM.CONTRACT_ALIAS_NO like '" + aliasNo + "' AND";
        }
        if (binderDTO.getAliastypecc() != null && !binderDTO.getAliastypecc().equals(StringUtils.EMPTY) && !binderDTO.getAliastypecc().equals(Constants.NULL)) {
            query = query + " CAM.CONTRACT_ALIAS_TYPE='" + binderDTO.getAliastypecc() + "' AND";
        }
        String asDate = Constants.NULL;
        String aeDate = Constants.NULL;
        if (binderDTO.getAliasStartDate() != null) {
            asDate = DBDate.format(binderDTO.getAliasStartDate());
        }
        if (binderDTO.getAliasEndDate() != null) {
            aeDate = DBDate.format(binderDTO.getAliasEndDate());
        }
        query = query + " CAM.START_DATE BETWEEN ISNULL(" + asDate + ",'1965-01-01') AND ISNULL(" + aeDate + ",'2065-01-01') AND";
        query = query.trim();
        if (query.endsWith("AND")) {
            query = query.substring(0, query.length() - 3);
        }
        return query;
    }

    /**
     * Get Items Information for Promote - TP
     *
     * @param searchValue
     * @return
     */
    public String getIFPInformation(String searchValue) {

        searchValue = searchValue.replace(CHAR_ASTERISK, CHAR_PERCENT);
        String query2 = "select distinct\n"
                + "IM.ITEM_MASTER_SID,\n"
                + "IM.ITEM_ID,\n"
                + "IM.ITEM_NAME,\n"
                + "IM.ITEM_NO,\n"
                + "HT.DESCRIPTION AS therapyClass,\n"
                + "BM.BRAND_NAME,\n"
                + "HT2.DESCRIPTION AS Form,\n"
                + "HT3.DESCRIPTION AS strength,\n"
                + "IM.ITEM_STATUS AS itemStatusId,\n"
                + "IM.ITEM_START_DATE,\n"
                + "IM.ITEM_END_DATE\n"
                + "from ITEM_MASTER IM\n"
                + "join BRAND_MASTER BM ON BM.BRAND_MASTER_SID=IM.BRAND_MASTER_SID\n"
                + "join HELPER_TABLE HT ON HT.HELPER_TABLE_SID=IM.THERAPEUTIC_CLASS\n"
                + "join HELPER_TABLE HT1 ON HT1.HELPER_TABLE_SID=IM.ITEM_STATUS\n"
                + "join HELPER_TABLE HT2 ON HT2.HELPER_TABLE_SID=IM.FORM\n"
                + "join HELPER_TABLE HT3 ON HT3.HELPER_TABLE_SID=IM.STRENGTH\n"
                + "WHERE IM.INBOUND_STATUS <> 'D' AND IM.ITEM_MASTER_SID in(" + searchValue + ") \n"
                + "ORDER BY IM.ITEM_NAME ";

        return query2;
    }

    public String rsValue(int rsIdValue) {
        String query = "select rm.RS_ID,h.DESCRIPTION AS STATUS,rm.RS_NO,rm.RS_START_DATE,rm.RS_NAME,rm.RS_END_DATE,h1.DESCRIPTION AS FREQ,h2.DESCRIPTION AS TYPE "
                + " from dbo.RS_MODEL rm join dbo.RS_CONTRACT rc on rm.RS_MODEL_SID=rc.RS_MODEL_SID and rc.RS_CONTRACT_SID=" + rsIdValue + " left join dbo.HELPER_TABLE h on rm.RS_STATUS=h.HELPER_TABLE_SID join dbo.HELPER_TABLE h1 "
                + " on h1.HELPER_TABLE_SID=rm.REBATE_FREQUENCY join dbo.HELPER_TABLE h2 on h2.HELPER_TABLE_SID=rm.REBATE_PROGRAM_TYPE";
        return query;
    }

    public String compValue(String value) {
        String query = "select cm.COMPANY_NO,cm.COMPANY_NAME,H.DESCRIPTION,cm.COMPANY_START_DATE,cm.COMPANY_END_DATE,tradeclass.DESCRIPTION as tradeclass from dbo.COMPANY_MASTER cm "
                + "INNER JOIN COMPANY_TRADE_CLASS CT\n"
                + "  ON CT.COMPANY_MASTER_SID = cm.COMPANY_MASTER_SID\n"
                + "INNER JOIN HELPER_TABLE tradeclass\n"
                + " ON tradeclass.HELPER_TABLE_SID = CT.COMPANY_TRADE_CLASS  join dbo.HELPER_TABLE H"
                + " ON H.HELPER_TABLE_SID = cm.COMPANY_STATUS and cm.COMPANY_MASTER_SID in (" + value + ")";
        return query;
    }

    public String cfpValue(String id) {
        String query = "select CFP_ID,CFP_NO,CFP_NAME,CFP_MODEL_SID from dbo.CFP_MODEL where CFP_MODEL_SID in (select CFP_MODEL_SID from dbo.CFP_CONTRACT where CFP_CONTRACT_SID=" + id + ")";
        return query;
    }

    public String psValue(String id) {
        String query = "select PS_ID,PS_NO,PS_NAME,PS_MODEL_SID from dbo.PS_MODEL where PS_MODEL_SID in (select PS_MODEL_SID from dbo.PS_CONTRACT where PS_CONTRACT_SID=" + id + ")";
        return query;
    }

    public String conditionQuery(String modelId, String modelSId) {
        String query = "select * from PS_DETAILS where PS_MODEL_SID=" + modelId + " and IFP_MODEL_SID=" + modelSId;
        return query;
    }

    public String rsValue(String id) {
        String query = "select RS_ID,RS_NO,RS_NAME,RS_MODEL_SID from dbo.RS_MODEL where RS_MODEL_SID in (select RS_MODEL_SID from dbo.RS_CONTRACT where RS_CONTRACT_SID=" + id + ")";
        return query;
    }

    public String populateQuery(String value) {
        String query = "select cm.COMPANY_NO,cm.COMPANY_NAME,H.DESCRIPTION,cm.COMPANY_START_DATE,cm.COMPANY_END_DATE from dbo.COMPANY_MASTER cm join dbo.HELPER_TABLE H"
                + " ON H.HELPER_TABLE_SID = cm.COMPANY_STATUS and COMPANY_MASTER_SID in (" + value + ")";
        return query;
    }

    public String cfpContractDetailsInsert(String contractSid, String createdDate, String cfpContractSId) {
        String query = "INSERT INTO CFP_CONTRACT (CFP_MODEL_SID,CFP_NAME,CFP_TYPE,CFP_CATEGORY,CFP_DESIGNATION,CFP_STATUS,CFP_TRADE_CLASS,CFP_START_DATE,CFP_END_DATE,"
                + " CONTRACT_MASTER_SID,CFP_CONTRACT_ATTACHED_STATUS,CFP_CONTRACT_ATTACHED_DATE,PARENT_CFP_ID,PARENT_CFP_NAME,INBOUND_STATUS,RECORD_LOCK_STATUS,"
                + " BATCH_ID,SOURCE,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE) SELECT CFP_MODEL_SID,CFP_NAME,CFP_TYPE,CFP_CATEGORY,CFP_DESIGNATION,CFP_STATUS,CFP_TRADE_CLASS,CFP_START_DATE,CFP_END_DATE," + contractSid + ", CFP_CONTRACT_ATTACHED_STATUS,"
                + " CFP_CONTRACT_ATTACHED_DATE,PARENT_CFP_ID,PARENT_CFP_NAME,'A',RECORD_LOCK_STATUS,BATCH_ID,SOURCE," + 1 + "," + createdDate + ",1,1 FROM   CFP_CONTRACT WHERE  CFP_CONTRACT_SID =" + cfpContractSId;
        return query;
    }

    public String idenCFPQuery() {
        String query = " select IDENT_CURRENT('CFP_CONTRACT')";
        return query;
    }

    public String cfpModelQuery(String id) {
        String query = " select CFP_MODEL_SID from dbo.CFP_CONTRACT where CFP_CONTRACT_SID=" + id;
        return query;
    }

    public String ifpModelQuery(String id) {
        String query = " select IFP_MODEL_SID from dbo.IFP_CONTRACT where IFP_CONTRACT_SID=" + id;
        return query;
    }

    public String psModelQuery(String id) {
        String query = " select PS_MODEL_SID from dbo.PS_CONTRACT where PS_CONTRACT_SID=" + id;
        return query;
    }

    public String rsModelQuery(String id) {
        String query = " select RS_MODEL_SID from dbo.RS_CONTRACT where RS_CONTRACT_SID=" + id;
        return query;
    }

    public String ifpContractDetailsInsert(String parentCFPId, String contractSid, String createdDate, String ifpContractSId) {
        String query = "INSERT INTO IFP_CONTRACT (IFP_MODEL_SID,IFP_NAME,IFP_TYPE,IFP_CATEGORY,IFP_DESIGNATION,IFP_STATUS,IFP_START_DATE,IFP_END_DATE,CFP_CONTRACT_SID,"
                + " CONTRACT_MASTER_SID,IFP_CONTRACT_ATTACHED_STATUS,IFP_CONTRACT_ATTACHED_DATE,PARENT_IFP_ID,PARENT_IFP_NAME,"
                + " INBOUND_STATUS,RECORD_LOCK_STATUS,BATCH_ID,SOURCE,CREATED_BY,CREATED_DATE, MODIFIED_BY,MODIFIED_DATE)"
                + " SELECT IFP_MODEL_SID,IFP_NAME,IFP_TYPE,IFP_CATEGORY,IFP_DESIGNATION,IFP_STATUS,IFP_START_DATE,IFP_END_DATE," + parentCFPId + ", " + contractSid + ",IFP_CONTRACT_ATTACHED_STATUS,IFP_CONTRACT_ATTACHED_DATE,PARENT_IFP_ID,PARENT_IFP_NAME,INBOUND_STATUS,"
                + " RECORD_LOCK_STATUS,BATCH_ID,SOURCE,1," + createdDate + ",1,1 FROM   IFP_CONTRACT WHERE  IFP_CONTRACT_SID =" + ifpContractSId;

        return query;
    }

    public String psContractDetailsInsert(String contractSid, String parentCFPId, String parentIFPId, String createdDate, String psContractSId) {
        String query = "INSERT INTO PS_CONTRACT (PS_MODEL_SID,PS_NAME,PS_TYPE,PS_CATEGORY,PS_STATUS,PS_DESIGNATION,PS_START_DATE,PS_END_DATE,CONTRACT_MASTER_SID,CFP_CONTRACT_SID,IFP_CONTRACT_SID,PS_CONTRACT_ATTACHED_STATUS,"
                + " PS_CONTRACT_ATTACHED_DATE,PARENT_PS_ID,PARENT_PS_NAME,INBOUND_STATUS,RECORD_LOCK_STATUS,BATCH_ID,SOURCE,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE)"
                + " SELECT PS_MODEL_SID,PS_NAME,PS_TYPE,PS_CATEGORY,PS_STATUS,PS_DESIGNATION,PS_START_DATE,PS_END_DATE," + contractSid + "," + parentCFPId + "," + parentIFPId + ",PS_CONTRACT_ATTACHED_STATUS,PS_CONTRACT_ATTACHED_DATE,PARENT_PS_ID,PARENT_PS_NAME,INBOUND_STATUS,RECORD_LOCK_STATUS,BATCH_ID,SOURCE,1," + createdDate + ",1,1"
                + " FROM   PS_CONTRACT WHERE  PS_CONTRACT_SID =" + psContractSId;
        return query;
    }

    public String rsContractDetailsInsert(String contractSid, String parentCFPId, String parentIFPId, String parentPSId, String createdDate, String rsContractSId) {
        String bpi = "BPI";
        String query = "INSERT INTO RS_CONTRACT (RS_MODEL_SID,RS_ID,RS_NO,RS_NAME,RS_TYPE,REBATE_PROGRAM_TYPE,RS_CATEGORY,RS_STATUS,RS_DESIGNATION,RS_START_DATE,RS_END_DATE,RS_TRADE_CLASS,PARENT_RS_ID,PARENT_RS_NAME,CONTRACT_MASTER_SID,CFP_CONTRACT_SID,"
                + " IFP_CONTRACT_SID,PS_CONTRACT_SID,RS_CONTRACT_ATTACHED_STATUS,RS_CONTRACT_ATTACHED_DATE,RS_TRANS_REF_ID,RS_TRANS_REF_NO,RS_TRANS_REF_NAME,REBATE_RULE_TYPE,REBATE_RULE_ASSOCIATION,REBATE_PLAN_LEVEL,"
                + " INTEREST_BEARING_INDICATOR,INTEREST_BEARING_BASIS,REBATE_PROCESSING_TYPE,REBATE_FREQUENCY,PAYMENT_METHOD,PAYMENT_FREQUENCY,PAYMENT_TERMS,PAYMENT_GRACE_PERIOD,RS_CALENDAR,RS_VALIDATION_PROFILE,MAKE_PAYABLE_TO,"
                + " ADDRESS_1,ADDRESS_2,CITY,STATE,ZIP_CODE,RS_ALIAS,FORMULA_METHOD_ID,INBOUND_STATUS,RECORD_LOCK_STATUS,BATCH_ID, SOURCE,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE)"
                + " SELECT RS_MODEL_SID,RS_ID,RS_NO,RS_NAME,RS_TYPE,REBATE_PROGRAM_TYPE,RS_CATEGORY,RS_STATUS,RS_DESIGNATION,RS_START_DATE,RS_END_DATE,RS_TRADE_CLASS,PARENT_RS_ID,PARENT_RS_NAME," + contractSid + "," + parentCFPId + "," + parentIFPId + "," + parentPSId + ",RS_CONTRACT_ATTACHED_STATUS,"
                + " RS_CONTRACT_ATTACHED_DATE,RS_TRANS_REF_ID,RS_TRANS_REF_NO,RS_TRANS_REF_NAME,REBATE_RULE_TYPE,REBATE_RULE_ASSOCIATION,REBATE_PLAN_LEVEL,INTEREST_BEARING_INDICATOR,"
                + " INTEREST_BEARING_BASIS,REBATE_PROCESSING_TYPE,REBATE_FREQUENCY,PAYMENT_METHOD,PAYMENT_FREQUENCY,PAYMENT_TERMS,PAYMENT_GRACE_PERIOD,RS_CALENDAR,RS_VALIDATION_PROFILE,MAKE_PAYABLE_TO,ADDRESS_1,"
                + " ADDRESS_2,CITY,STATE,ZIP_CODE,RS_ALIAS,FORMULA_METHOD_ID,INBOUND_STATUS,RECORD_LOCK_STATUS,BATCH_ID,'" + bpi + "',1," + createdDate + ",1,1 FROM   RS_CONTRACT WHERE  RS_CONTRACT_SID =" + rsContractSId;
        return query;

    }

    public String idenIFPQuery() {
        String query = " select IDENT_CURRENT('IFP_CONTRACT')";
        return query;
    }

    public String idenPSQuery() {
        String query = " select IDENT_CURRENT('PS_CONTRACT')";
        return query;
    }

    public String idenRSQuery() {
        String query = " select IDENT_CURRENT('RS_CONTRACT')";
        return query;
    }

    public String getItemInfo(String ids, String ifpIds) {
        String query = "SELECT IFP_MODEL_SID,\n" +
                        "       ITEM_MASTER_SID,\n" +
                        "       ITEM_NO,\n" +
                        "       ITEM_NAME,\n" +
                        "       THERAPHY,\n" +
                        "       BRAND,\n" +
                        "       STATUS_ID,\n" +
                        "       ITEM_START_DATE,\n" +
                        "       ITEM_END_DATE\n" +
                        "FROM   (SELECT    IFP_M.IFP_MODEL_SID,\n" +
                        "                  IM.ITEM_MASTER_SID,\n" +
                        "                  IM.ITEM_NO,\n" +
                        "                  IM.ITEM_NAME,\n" +
                        "                  HT.DESCRIPTION                                           AS THERAPHY,\n" +
                        "                  BM.BRAND_NAME                                            AS BRAND,\n" +
                        "                  IM.ITEM_STATUS                                           AS STATUS_ID,\n" +
                        "                  IM.ITEM_START_DATE,\n" +
                        "                  IM.ITEM_END_DATE,\n" +
                        "                  ROW_NUMBER()\n" +
                        "                    OVER(\n" +
                        "                      PARTITION BY IFP_D.ITEM_MASTER_SID\n" +
                        "                      ORDER BY IFP_D.ITEM_MASTER_SID, IFP_M.IFP_MODEL_SID) AS RN\n" +
                        "        FROM      dbo.IFP_MODEL IFP_M\n" +
                        "        JOIN      dbo.IFP_DETAILS IFP_D ON IFP_M.IFP_MODEL_SID = IFP_D.IFP_MODEL_SID\n" +
                        "        LEFT JOIN dbo.ITEM_MASTER IM ON IFP_D.ITEM_MASTER_SID = IM.ITEM_MASTER_SID\n" +
                        "        LEFT JOIN dbo.HELPER_TABLE HT ON IM.THERAPEUTIC_CLASS = HT.HELPER_TABLE_SID\n" +
                        "        LEFT JOIN dbo.BRAND_MASTER BM ON IM.BRAND_MASTER_SID = BM.BRAND_MASTER_SID\n" +
                        "        LEFT JOIN dbo.HELPER_TABLE HT1 ON HT1.HELPER_TABLE_SID = IM.ITEM_STATUS\n" +
                        "        WHERE     IFP_M.IFP_MODEL_SID IN ( " + ifpIds + " )\n" +
                        "                  AND IFP_D.ITEM_MASTER_SID IN ("+ids+"))A\n" +
                        "WHERE  RN = 1 ";
        
        return query;
    }

    public String getCompanyInformation(String companySid) {
        String query = "select CM.COMPANY_ID,CM.COMPANY_NO,CM.COMPANY_NAME,CM.COMPANY_START_DATE,CM.COMPANY_END_DATE,HT.DESCRIPTION AS STATUS from dbo.COMPANY_MASTER CM\n"
                + "LEFT JOIN DBO.HELPER_TABLE HT ON CM.COMPANY_STATUS=HT.HELPER_TABLE_SID WHERE CM.COMPANY_MASTER_SID='" + companySid + "'";
        return query;
    }

    public String getRsValueFromTempTable(String tempTableSid) {
        String query = "select COMPONENT_NAME,COMPONENT_ID,COMPONENT_NO,START_DATE,END_DATE,COMPONENT_TYPE,COMPONENT_STATUS,PROGRAM_TYPE,"
                + "PLAN_LEVEL,PAYMENT_FREQUENCY,PAYMENT_METHOD from dbo.GCM_CONTRACT_DETAILS WHERE GCM_CONTRACT_DETAILS_SID='" + tempTableSid + "'";
        return query;
    }

    public String getRSDetailsFromTempTable(String tempTableSid) {
        String query = "select ITEM_MASTER_SID from dbo.GCM_GLOBAL_DETAILS where RS_MODEL_SID='" + tempTableSid + "'";
        return query;

    }

    public String insertIntoRsDeatils(String rsModelId, String ifpModelId, String itemMasterId, Object startDate) {
        String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        String query1 = "INSERT INTO dbo.RS_DETAILS(RS_MODEL_SID,IFP_MODEL_SID,ITEM_MASTER_SID,ITEM_REBATE_START_DATE,\"SOURCE\",INBOUND_STATUS,RECORD_LOCK_STATUS,\n"
                + "CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE) VALUES ";
        query1 += "(?RS_MODEL_SID,?IFP_MODEL_ID,?ITEM_MASTER_ID,?ITEM_REBATE_START_DATE,'GCM','A',?RECORD_LOCK_STATUS,?CREATED_BY,'?CREATED_DATE',?MODIFIED_BY,'?MODIFIED_DATE')";
        query1 = query1.replaceAll("\\?RS_MODEL_SID", rsModelId);
        query1 = query1.replaceAll("\\?IFP_MODEL_ID", ifpModelId);
        query1 = query1.replaceAll("\\?ITEM_MASTER_ID", itemMasterId);
        if (startDate != null) {
            String date = simpleDateFormat.format((Date) startDate);
            query1 = query1.replaceAll("\\?ITEM_REBATE_START_DATE", "'" + date + "'");
        } else {
            query1 = query1.replaceAll("\\?ITEM_REBATE_START_DATE", "NULL");
        }
        query1 = query1.replaceAll("\\?RECORD_LOCK_STATUS", "'false'");
        String createdDate = simpleDateFormat.format(new Date());
        query1 = query1.replaceAll("\\?CREATED_DATE", createdDate);
        query1 = query1.replaceAll("\\?MODIFIED_DATE", createdDate);
        query1 = query1.replaceAll("\\?CREATED_BY", userId);
        query1 = query1.replaceAll("\\?MODIFIED_BY", userId);
        return query1;

    }
}
