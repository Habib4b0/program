/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.deductioncalendar.ui.util;

import com.stpl.app.global.deductioncalendar.dto.DeductionDetailsDTO;
import com.stpl.app.global.deductioncalendar.dto.TableDTO;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.xmlparser.SQLUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author gopinath
 */
public class QueryUtils {
    
    /**
     * Method for getting forecast configuration start and end date
     *
     * @return
     */
    public String getForecastConfigQuery() {
        return SQLUtil.getQuery("forecast-config");
    }

    /**
     * Method for returning the table count
     *
     * @param deductionDTO
     * @param tableDTO
     * @return
     */
    public String getDeductionCountQuery(DeductionDetailsDTO deductionDTO, TableDTO tableDTO) {     
        String query = "";
        if (deductionDTO.getDataView().equals(ConstantsUtils.CUSTOMER)) {
            if (deductionDTO.getFilterDdlb() != null && !ConstantsUtils.NULL.equals(deductionDTO.getFilterDdlb())) {
                if (deductionDTO.getLevelNo() == 1) {
                    query = SQLUtil.getQuery("customercount")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace(ConstantsUtils.QUESTION_FILTER," AND COMPANY_MASTER_SID='"+deductionDTO.getFilterDdlb()+"' ");
                } else if (deductionDTO.getLevelNo() == NumericConstants.TWO) {
                   query = SQLUtil.getQuery("brandcount")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'");

                } else if (deductionDTO.getLevelNo() == NumericConstants.THREE) {
                    query = SQLUtil.getQuery("cust-brand-item-count")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace(ConstantsUtils.CMID, "'"+tableDTO.getCompanySid()+"'")
                            .replace("?BMID", "'"+tableDTO.getBrandSid()+"'");
                }
            } else {
                if (deductionDTO.getLevelNo() == 1) {
                    query = "SELECT COUNT(DISTINCT 'Total') AS TOTAL FROM ST_DEDUCTION_CALENDAR_DETAILS";
                } else if (deductionDTO.getLevelNo() == NumericConstants.TWO) {
                    query = SQLUtil.getQuery("customercount")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace(ConstantsUtils.QUESTION_FILTER," ");
                } else if (deductionDTO.getLevelNo() == NumericConstants.THREE) {
                    query = SQLUtil.getQuery("brandcount")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'");

                } else if (deductionDTO.getLevelNo() == NumericConstants.FOUR) {
                    query = SQLUtil.getQuery("cust-brand-item-count")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace(ConstantsUtils.CMID, "'"+tableDTO.getCompanySid()+"'")
                            .replace("?BMID", "'"+tableDTO.getBrandSid()+"'");
                }
            }
        } else if (deductionDTO.getDataView().equals(ConstantsUtils.PRODUCT)) {
            if (deductionDTO.getFilterDdlb() != null && !ConstantsUtils.NULL.equals(deductionDTO.getFilterDdlb())) {
                
                if (deductionDTO.getLevelNo() == 1) {
                    query = SQLUtil.getQuery("prod-level1-count")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace(ConstantsUtils.QUESTION_FILTER,ConstantsUtils.AND_IM_MASTER_SID +deductionDTO.getFilterDdlb());
                } else if (deductionDTO.getLevelNo() == NumericConstants.TWO) {
                    query = SQLUtil.getQuery("prod-level2-count")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace(ConstantsUtils.QUESTION_BMSID,  "'"+tableDTO.getBrandSid()+"'")
                            .replace(ConstantsUtils.QUESTION_FILTER,ConstantsUtils.AND_IM_MASTER_SID +deductionDTO.getFilterDdlb());
                } else if (deductionDTO.getLevelNo() == NumericConstants.THREE) {
                    query = SQLUtil.getQuery("prod-level3-count")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace(ConstantsUtils.QUESTION_BMSID,  "'"+tableDTO.getBrandSid()+"'")
                            .replace(ConstantsUtils.CMID,  "'"+tableDTO.getCompanySid()+"'")
                            .replace(ConstantsUtils.QUESTION_FILTER,ConstantsUtils.AND_IM_MASTER_SID +deductionDTO.getFilterDdlb());
                }
                
            } else {
                if (deductionDTO.getLevelNo() == 1) {
                    query = "SELECT COUNT(DISTINCT 'Total') AS TOTAL FROM ST_DEDUCTION_CALENDAR_DETAILS";
                } else if (deductionDTO.getLevelNo() == NumericConstants.TWO) {
                    query = SQLUtil.getQuery("prod-level1-count")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace(ConstantsUtils.QUESTION_FILTER," ");
                } else if (deductionDTO.getLevelNo() == NumericConstants.THREE) {
                    query = SQLUtil.getQuery("prod-level2-count")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace(ConstantsUtils.QUESTION_BMSID,  "'"+tableDTO.getBrandSid()+"'")
                            .replace(ConstantsUtils.QUESTION_FILTER," ");
                } else if (deductionDTO.getLevelNo() == NumericConstants.FOUR) {
                    query = SQLUtil.getQuery("prod-level3-count")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace(ConstantsUtils.QUESTION_BMSID,  "'"+tableDTO.getBrandSid()+"'")
                            .replace(ConstantsUtils.CMID,  "'"+tableDTO.getCompanySid()+"'")
                            .replace(ConstantsUtils.QUESTION_FILTER," ");
                }
            }
        }
        return query;
    }

    /**
     * Method for returning the data
     *
     * @param deductionDTO
     * @param tableDTO
     * @return
     */
    public String getDeductionDataQuery(DeductionDetailsDTO deductionDTO, TableDTO tableDTO, int start, int offset) {
        String[] fromDate = deductionDTO.getDetailsFromDate() == null || "".equals(deductionDTO.getDetailsFromDate()) || ConstantsUtils.NULL.equals(deductionDTO.getDetailsFromDate()) ? String.valueOf(deductionDTO.getForecastFromDate()).split("-") : deductionDTO.getDetailsFromDate().split("-");
        String[] toDate = deductionDTO.getDetailsToDate() == null || "".equals(deductionDTO.getDetailsToDate()) || ConstantsUtils.NULL.equals(deductionDTO.getDetailsToDate()) ? String.valueOf(deductionDTO.getForecastToDate()).split("-") : deductionDTO.getDetailsToDate().split("-");
        String query = "";
        if (deductionDTO.getDataView().equals(ConstantsUtils.CUSTOMER)) {
            if (deductionDTO.getFilterDdlb() != null && !ConstantsUtils.NULL.equals(deductionDTO.getFilterDdlb())) {
                if (deductionDTO.getLevelNo() == 1) {
                    query = SQLUtil.getQuery("customerlevel2")
                            .replace("?columnlist", SQLUtil.getQuery("customerlevelcolumns"))
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace(ConstantsUtils.QUESTION_FROM,fromDate[0])
                            .replace("?TO", toDate[0])
                            .replace(ConstantsUtils.QUESTION_START,String.valueOf(start))
                            .replace(ConstantsUtils.QUESTION_OFFSET,String.valueOf(offset))
                            .replace(ConstantsUtils.QUESTION_FILTER," AND COMPANY_MASTER_SID='"+deductionDTO.getFilterDdlb()+"' ");
                    query =getColumnListbyFreq(query, deductionDTO.getFrequency());
                } else if (deductionDTO.getLevelNo() == NumericConstants.TWO) {
                    query=SQLUtil.getQuery("customerlevel3")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace(ConstantsUtils.QUESTION_FROM, fromDate[0])
                            .replace("?TO", toDate[0])
                            .replace(ConstantsUtils.CMID, String.valueOf(tableDTO.getCompanySid()))
                            .replace(ConstantsUtils.QUESTION_START,String.valueOf(start))
                            .replace(ConstantsUtils.QUESTION_OFFSET,String.valueOf(offset));
                    query =getColumnListbyFreq(query, deductionDTO.getFrequency());
                    
                    
                } else if (deductionDTO.getLevelNo() == NumericConstants.THREE) {
                    query=SQLUtil.getQuery("customerlevel4")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace(ConstantsUtils.QUESTION_FROM, fromDate[0])
                            .replace("?TO", toDate[0])
                            .replace(ConstantsUtils.CMID, String.valueOf(tableDTO.getCompanySid()))
                            .replace(ConstantsUtils.QUESTION_BMSID, String.valueOf(tableDTO.getBrandSid()))
                            .replace(ConstantsUtils.QUESTION_START,String.valueOf(start))
                            .replace(ConstantsUtils.QUESTION_OFFSET,String.valueOf(offset));
                   
                    query =getColumnListbyFreq(query, deductionDTO.getFrequency());
                }
            } else {
                if (deductionDTO.getLevelNo() == 1) {
                    query=SQLUtil.getQuery("totalcustomer");
                    query =getColumnListbyFreq(query, deductionDTO.getFrequency());
                } else if (deductionDTO.getLevelNo() == NumericConstants.TWO) {
                    query = SQLUtil.getQuery("customerlevel2")
                            .replace("?columnlist", SQLUtil.getQuery("customerlevelcolumns"))
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace(ConstantsUtils.QUESTION_FROM,fromDate[0])
                            .replace("?TO", toDate[0])
                            .replace(ConstantsUtils.QUESTION_START,String.valueOf(start))
                            .replace(ConstantsUtils.QUESTION_OFFSET,String.valueOf(offset))
                            .replace(ConstantsUtils.QUESTION_FILTER," ");
                    query =getColumnListbyFreq(query, deductionDTO.getFrequency());
                } else if (deductionDTO.getLevelNo() == NumericConstants.THREE) {
                    query=SQLUtil.getQuery("customerlevel3")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace(ConstantsUtils.QUESTION_FROM, fromDate[0])
                            .replace("?TO", toDate[0])
                            .replace(ConstantsUtils.CMID, String.valueOf(tableDTO.getCompanySid()))
                            .replace(ConstantsUtils.QUESTION_START,String.valueOf(start))
                            .replace(ConstantsUtils.QUESTION_OFFSET,String.valueOf(offset));
                    query =getColumnListbyFreq(query, deductionDTO.getFrequency());
                } else if (deductionDTO.getLevelNo() == NumericConstants.FOUR) {
                    query=SQLUtil.getQuery("customerlevel4")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace(ConstantsUtils.QUESTION_FROM, fromDate[0])
                            .replace("?TO", toDate[0])
                            .replace(ConstantsUtils.CMID, String.valueOf(tableDTO.getCompanySid()))
                            .replace(ConstantsUtils.QUESTION_BMSID, String.valueOf(tableDTO.getBrandSid()))
                            .replace(ConstantsUtils.QUESTION_START,String.valueOf(start))
                            .replace(ConstantsUtils.QUESTION_OFFSET,String.valueOf(offset));
                    query =getColumnListbyFreq(query, deductionDTO.getFrequency());
                }
            }
        } else if (deductionDTO.getDataView().equals(ConstantsUtils.PRODUCT)) {
            if (deductionDTO.getFilterDdlb() != null && !ConstantsUtils.NULL.equals(deductionDTO.getFilterDdlb())) {
                if (deductionDTO.getLevelNo() == 1) {
                    query=SQLUtil.getQuery("prod-level1-data")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace(ConstantsUtils.QUESTION_FROM, fromDate[0])
                            .replace("?TO", toDate[0])
                            .replace(ConstantsUtils.QUESTION_START,String.valueOf(start))
                            .replace(ConstantsUtils.QUESTION_OFFSET,String.valueOf(offset))
                            .replace(ConstantsUtils.QUESTION_FILTER, ConstantsUtils.AND_IM_MASTER_SID +deductionDTO.getFilterDdlb());
                    query =getColumnListbyFreq(query, deductionDTO.getFrequency());
                } else if (deductionDTO.getLevelNo() == NumericConstants.TWO) {
                    query=SQLUtil.getQuery("prod-level2-data")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace(ConstantsUtils.QUESTION_FROM, fromDate[0])
                            .replace("?TO", toDate[0])
                            .replace(ConstantsUtils.QUESTION_START,String.valueOf(start))
                            .replace(ConstantsUtils.QUESTION_OFFSET,String.valueOf(offset))
                            .replace(ConstantsUtils.QUESTION_BMSID,  "'"+tableDTO.getBrandSid()+"'")
                            .replace(ConstantsUtils.QUESTION_FILTER, ConstantsUtils.AND_IM_MASTER_SID +deductionDTO.getFilterDdlb());
                    query =getColumnListbyFreq(query, deductionDTO.getFrequency());
                } else if (deductionDTO.getLevelNo() == NumericConstants.THREE) {
                    query=SQLUtil.getQuery("prod-level3-data")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace(ConstantsUtils.QUESTION_FROM, fromDate[0])
                            .replace("?TO", toDate[0])
                            .replace(ConstantsUtils.QUESTION_START,String.valueOf(start))
                            .replace(ConstantsUtils.QUESTION_OFFSET,String.valueOf(offset))
                            .replace(ConstantsUtils.QUESTION_BMSID,  "'"+tableDTO.getBrandSid()+"'")
                            .replace(ConstantsUtils.CMID,  "'"+tableDTO.getCompanySid()+"'")
                            .replace(ConstantsUtils.QUESTION_FILTER, ConstantsUtils.AND_IM_MASTER_SID +deductionDTO.getFilterDdlb());
                    query =getColumnListbyFreq(query, deductionDTO.getFrequency());
                }
                
            } else {
                if (deductionDTO.getLevelNo() == 1) {
                    query=SQLUtil.getQuery("totalcustomer");
                    query =getColumnListbyFreq(query, deductionDTO.getFrequency());
                } else if (deductionDTO.getLevelNo() == NumericConstants.TWO) {
                    query=SQLUtil.getQuery("prod-level1-data")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace(ConstantsUtils.QUESTION_FROM, fromDate[0])
                            .replace("?TO", toDate[0])
                            .replace(ConstantsUtils.QUESTION_START,String.valueOf(start))
                            .replace(ConstantsUtils.QUESTION_OFFSET,String.valueOf(offset))
                            .replace(ConstantsUtils.QUESTION_FILTER, "");
                    query =getColumnListbyFreq(query, deductionDTO.getFrequency());
                } else if (deductionDTO.getLevelNo() == NumericConstants.THREE) {
                    query=SQLUtil.getQuery("prod-level2-data")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace(ConstantsUtils.QUESTION_FROM, fromDate[0])
                            .replace("?TO", toDate[0])
                            .replace(ConstantsUtils.QUESTION_START,String.valueOf(start))
                            .replace(ConstantsUtils.QUESTION_OFFSET,String.valueOf(offset))
                            .replace(ConstantsUtils.QUESTION_BMSID,  "'"+tableDTO.getBrandSid()+"'")
                            .replace(ConstantsUtils.QUESTION_FILTER, "");
                    query =getColumnListbyFreq(query, deductionDTO.getFrequency());
                } else if (deductionDTO.getLevelNo() == NumericConstants.FOUR) {
                    query=SQLUtil.getQuery("prod-level3-data")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace(ConstantsUtils.QUESTION_FROM, fromDate[0])
                            .replace("?TO", toDate[0])
                            .replace(ConstantsUtils.QUESTION_START,String.valueOf(start))
                            .replace(ConstantsUtils.QUESTION_OFFSET,String.valueOf(offset))
                            .replace(ConstantsUtils.QUESTION_BMSID,  "'"+tableDTO.getBrandSid()+"'")
                            .replace(ConstantsUtils.CMID,  "'"+tableDTO.getCompanySid()+"'")
                            .replace(ConstantsUtils.QUESTION_FILTER, "");
                    query =getColumnListbyFreq(query, deductionDTO.getFrequency());
                }
            }
        }
        return query;
    }

    /**
     *
     * @param sid
     * @param levelNo
     * @return
     */
    public String getItemCount(int sid, int levelNo) {
        String query = "";
        if (levelNo == NumericConstants.TWO) {
            query = SQLUtil.getQuery("getItemCountForCustomer")
            .replace(ConstantsUtils.SID, String.valueOf(sid));
        } else if (levelNo == NumericConstants.THREE) {
            query = SQLUtil.getQuery("getItemCountForBrand")
            .replace(ConstantsUtils.SID, String.valueOf(sid));
        }
        return query;
    }

    /**
     * Line level update query for customer
     *
     * @param value
     * @param obj
     * @return
     */
    public String getLineUpdateQueryForCustomer(DeductionDetailsDTO deductionDTO,String value, Object[] obj, TableDTO dto) {
        int levelNo = Integer.parseInt(String.valueOf(obj[NumericConstants.FOUR]));
        boolean isFiltered= StringUtils.isNotEmpty(deductionDTO.getFilterDdlb())&&!ConstantsUtils.NULL.equalsIgnoreCase(deductionDTO.getFilterDdlb());
        String query = "";
        if (levelNo == (isFiltered ? 1 : NumericConstants.TWO)) {
            query = SQLUtil.getQuery("getLineLevelUpdateForCust")
           .replace(ConstantsUtils.QUESTION_VALUE, value)
           .replace(ConstantsUtils.SID, String.valueOf(dto.getCompanySid()))
           .replace(ConstantsUtils.AT_YEAR, String.valueOf(obj[NumericConstants.TWO]))
           .replace(ConstantsUtils.AT_MONTH, String.valueOf(obj[0]))
           .replace(ConstantsUtils.USERID_AT, deductionDTO.getUserId())
           .replace(ConstantsUtils.SESSION_ID_AT, deductionDTO.getSessionId());
        } else if (levelNo == (isFiltered ? NumericConstants.TWO : NumericConstants.THREE)) {
            query = SQLUtil.getQuery("getLineLevelUpdateForBrand")
            .replace(ConstantsUtils.QUESTION_VALUE, value)
            .replace(ConstantsUtils.SID, String.valueOf(dto.getBrandSid()))
            .replace(ConstantsUtils.AT_COMPANY_SID, String.valueOf(dto.getCompanySid()))
            .replace(ConstantsUtils.AT_YEAR, String.valueOf(obj[NumericConstants.TWO]))
            .replace(ConstantsUtils.AT_MONTH, String.valueOf(obj[0]))
            .replace(ConstantsUtils.USERID_AT, deductionDTO.getUserId())
            .replace(ConstantsUtils.SESSION_ID_AT, deductionDTO.getSessionId());
        } else {
            query = SQLUtil.getQuery("getLineLevelUpdateForItem")
            .replace(ConstantsUtils.QUESTION_VALUE, value)
            .replace(ConstantsUtils.SID, String.valueOf(dto.getItemSid()))
            .replace(ConstantsUtils.AT_BRAND_SID, String.valueOf(dto.getBrandSid()))
            .replace(ConstantsUtils.AT_COMPANY_SID, String.valueOf(dto.getCompanySid()))
            .replace(ConstantsUtils.AT_YEAR, String.valueOf(obj[NumericConstants.TWO]))
            .replace(ConstantsUtils.AT_MONTH, String.valueOf(obj[0]))
            .replace(ConstantsUtils.USERID_AT, deductionDTO.getUserId())
            .replace(ConstantsUtils.SESSION_ID_AT, deductionDTO.getSessionId());
        }
        return query;
    }

    /**
     * Line level update query for product
     *
     * @param value
     * @param obj
     * @return
     */
    public String getLineUpdateQueryForProduct(DeductionDetailsDTO deductionDTO,String value, Object[] obj,TableDTO dto) {
        int levelNo = Integer.parseInt(String.valueOf(obj[NumericConstants.FOUR]));
        boolean isFiltered= StringUtils.isNotEmpty(deductionDTO.getFilterDdlb())&&!ConstantsUtils.NULL.equalsIgnoreCase(deductionDTO.getFilterDdlb());
        String query = "";
        if (levelNo == (isFiltered ? 1 : NumericConstants.TWO)) {
            query = SQLUtil.getQuery("getLineLevelUpdateForBrandinProd")
            .replace(ConstantsUtils.QUESTION_VALUE, value)
            .replace(ConstantsUtils.SID, String.valueOf(dto.getBrandSid()))
            .replace(ConstantsUtils.AT_COMPANY_SID, StringUtils.EMPTY)
            .replace(ConstantsUtils.AT_YEAR, String.valueOf(obj[NumericConstants.TWO]))
            .replace(ConstantsUtils.AT_MONTH, String.valueOf(obj[0]))
            .replace(ConstantsUtils.USERID_AT, deductionDTO.getUserId())
            .replace(ConstantsUtils.SESSION_ID_AT, deductionDTO.getSessionId())
            .replace(ConstantsUtils.QUESTION_FILTER,isFiltered ? ConstantsUtils.AND_IM_MASTER_SID +deductionDTO.getFilterDdlb() : " ");
        } else if (levelNo == (isFiltered ? NumericConstants.TWO : NumericConstants.THREE)) {
            query = SQLUtil.getQuery("getLineLevelUpdateForCustinProd")
            .replace(ConstantsUtils.QUESTION_VALUE, value)
            .replace(ConstantsUtils.SID, String.valueOf(dto.getCompanySid()))
            .replace(ConstantsUtils.AT_BRAND_SID, String.valueOf(dto.getBrandSid()))
            .replace(ConstantsUtils.AT_YEAR, String.valueOf(obj[NumericConstants.TWO]))
            .replace(ConstantsUtils.AT_MONTH, String.valueOf(obj[0]))
            .replace(ConstantsUtils.USERID_AT, deductionDTO.getUserId())
            .replace(ConstantsUtils.SESSION_ID_AT, deductionDTO.getSessionId())
            .replace(ConstantsUtils.QUESTION_FILTER, isFiltered ? ConstantsUtils.AND_IM_MASTER_SID +deductionDTO.getFilterDdlb() : " ");
        } else {
            query = SQLUtil.getQuery("getLineLevelUpdateForItem")
            .replace(ConstantsUtils.QUESTION_VALUE, value)
            .replace(ConstantsUtils.SID, String.valueOf(dto.getItemSid()))
            .replace(ConstantsUtils.AT_BRAND_SID, String.valueOf(dto.getBrandSid()))
            .replace(ConstantsUtils.AT_COMPANY_SID, String.valueOf(dto.getCompanySid()))
            .replace(ConstantsUtils.AT_YEAR, String.valueOf(obj[NumericConstants.TWO]))
            .replace(ConstantsUtils.AT_MONTH, String.valueOf(obj[0]))
            .replace(ConstantsUtils.USERID_AT, deductionDTO.getUserId())
            .replace(ConstantsUtils.SESSION_ID_AT, deductionDTO.getSessionId());
        }
        return query;
    }

    /**
     * check query for customer
     * 
     * @param deductionDTO
     * @param dto
     * @param check
     * @return 
     */
    public String getCheckQueryForCustomer(TableDTO dto, boolean check,String filterValue) {
        String query = "";
        String value = "";
        if (check) {
            value = "1";
        } else {
            value = "0";
        }
        if (dto.getLevelNo() == (ConstantsUtils.NULL.equals(filterValue) ? NumericConstants.TWO : 1)) {
            query = SQLUtil.getQuery(ConstantsUtils.CHECK_TEMP_TABLE)
            .replace(ConstantsUtils.AT_CHECK, value)
            .replace(ConstantsUtils.AT_JOIN, StringUtils.EMPTY)
            .replace(ConstantsUtils.AT_WHERE, ConstantsUtils.WHERE_COMPANY_MASTER_SID + String.valueOf(dto.getCompanySid()));
        } else if (dto.getLevelNo() == (ConstantsUtils.NULL.equals(filterValue) ? NumericConstants.THREE : NumericConstants.TWO)) {
            query = SQLUtil.getQuery("checkTempTableBrand")
            .replace(ConstantsUtils.AT_CHECK, value)
            .replace(ConstantsUtils.AT_WHERE, " WHERE IM.BRAND_MASTER_SID =" + dto.getBrandSid() + " AND CD.COMPANY_MASTER_SID =" + dto.getCompanySid());
        } else {
            query = SQLUtil.getQuery(ConstantsUtils.CHECK_TEMP_TABLE)
            .replace(ConstantsUtils.AT_CHECK, value)
            .replace(ConstantsUtils.AT_JOIN, StringUtils.EMPTY)
            .replace(ConstantsUtils.AT_WHERE, ConstantsUtils.WHERE_COMPANY_MASTER_SID + (dto.getCompanySid()) + " AND CD.ITEM_MASTER_SID = " + (dto.getItemSid()));
        }
        return query;
    }

    /**
     * Check query for product
     *
     * @param levelNo
     * @param sid
     * @param check
     * @return
     */
    public String getCheckQueryForProduct(TableDTO dto, boolean check, String filterValue) {
        String query = "";
        String value = "";
        if (check) {
            value = "1";
        } else {
            value = "0";
        }
        if (dto.getLevelNo() == (ConstantsUtils.NULL.equals(filterValue) ? NumericConstants.TWO : 1)) {
            query = SQLUtil.getQuery("checkTempTableBrand");
            query = query.replace(ConstantsUtils.AT_CHECK, value);
            query = query.replace(ConstantsUtils.AT_WHERE, " WHERE IM.BRAND_MASTER_SID = " + dto.getBrandSid());
        } else if (dto.getLevelNo() == (ConstantsUtils.NULL.equals(filterValue) ? NumericConstants.THREE : NumericConstants.TWO)) {
            query = SQLUtil.getQuery(ConstantsUtils.CHECK_TEMP_TABLE);
            query = query.replace(ConstantsUtils.AT_CHECK, value);
            query = query.replace(ConstantsUtils.AT_JOIN, " JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID = CD.ITEM_MASTER_SID");
            query = query.replace(ConstantsUtils.AT_WHERE,ConstantsUtils.WHERE_COMPANY_MASTER_SID + (dto.getCompanySid()) + " AND IM.BRAND_MASTER_SID ="+ (dto.getBrandSid()));
        } else {
            query = SQLUtil.getQuery(ConstantsUtils.CHECK_TEMP_TABLE);
            query = query.replace(ConstantsUtils.AT_CHECK, value);
            query = query.replace(ConstantsUtils.AT_JOIN, " JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID= CD.ITEM_MASTER_SID");
            query = query.replace(ConstantsUtils.AT_WHERE, ConstantsUtils.WHERE_COMPANY_MASTER_SID + (dto.getCompanySid()) + " AND IM.BRAND_MASTER_SID ="+ (dto.getBrandSid())+" AND IM.ITEM_MASTER_SID = " + (dto.getItemSid()));
        }
        return query;
    }

    /**
     * Check all
     *
     * @param check
     * @return
     */
    public String getCheckAllQuery(boolean check) {
        String query = SQLUtil.getQuery("checkAllTemp");
        if (check) {
            query = query.replace(ConstantsUtils.AT_CHECK, "1");
        } else {
            query = query.replace(ConstantsUtils.AT_CHECK, "0");
        }
        return query;
    }

    /**
     * 
     * @param list
     * @return 
     */   
    public String getAdjustPeriodQuery(List list) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT PERIOD_SID FROM PERIOD WHERE (MONTH IN ('");
        String lastValue = "";
        for (int i = 0; i < list.size(); i++) {
              String[] arr = String.valueOf(list.get(i)).split("~");
            if (!lastValue.isEmpty() && !lastValue.equals(String.valueOf(arr[NumericConstants.TWO]))) {
                queryBuilder.append("') AND YEAR =" ).append( lastValue);
                queryBuilder.append(") OR ( MONTH IN ('");
            }
            if (list.size() > 1) {
                if (i == 0) {
                    queryBuilder.append(arr[1]);
                } else {
                    queryBuilder.append("','").append(arr[1]);
                }
            } else {
                queryBuilder.append(arr[1]);
            }
            lastValue = String.valueOf(arr[NumericConstants.TWO]);
            if(i==list.size()-1){
                queryBuilder.append("') AND YEAR =").append(arr[NumericConstants.TWO]).append(" )");
            }
        }
        return queryBuilder.toString();
    }
    
    /**
     * 
     * @param dto
     * @param type
     * @param basis
     * @param variable
     * @param amount
     * @param method
     * @param periodQuery
     * @param selectedOption
     * @return 
     */
    public String getAdjustQuery(String type, String basis, String variable, String amount, String method, String periodQuery, String selectedOption) {
        String query = "UPDATE ST_DEDUCTION_CALENDAR_DETAILS SET ADJUSTMENT_TYPE = '" + type + "', ADJUSTMENT_BASIS = '" + basis + "',ADJUSTMENT_VARIABLE = '" + variable + "',"
                + "ADJUSTMENT_VALUE = '" + amount + "', ADJUSTMENT_ALLOCATION_METHODOLOGY = '" + method + "'";
        if ("Select".equals(selectedOption)) {
            query += " WHERE PERIOD_SID IN (" + periodQuery + ") ";
        }
        return query;
    }
    
    /**
     * 
     * @param query
     * @param freq
     * @return 
     */
    public String getColumnListbyFreq(String query, String freq) {

        switch (freq) {
            case "Annual":
                query = query.replace(ConstantsUtils.FREQ, SQLUtil.getQuery("annualdisc"));
                break;
            case "Quarterly":
                query = query.replace(ConstantsUtils.FREQ, SQLUtil.getQuery("quarterlydisc"));
                break;
            case "Semi-Annual":
                query = query.replace(ConstantsUtils.FREQ, SQLUtil.getQuery("semidisc"));
                break;
            case "Monthly":
                query = query.replace(ConstantsUtils.FREQ, SQLUtil.getQuery("monthlydisc"));
                break;
            default:
                break;
        }
        return query;

    }
}