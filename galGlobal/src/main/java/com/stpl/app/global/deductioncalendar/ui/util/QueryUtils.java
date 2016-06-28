/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.deductioncalendar.ui.util;

import com.stpl.app.global.deductioncalendar.dto.DeductionDetailsDTO;
import com.stpl.app.global.deductioncalendar.dto.TableDTO;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.util.dao.orm.CustomSQLUtil;
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
        String query = CustomSQLUtil.get("forecast-config");
        return query;
    }

    /**
     * Method for returning the table count
     *
     * @param deductionDTO
     * @param tableDTO
     * @return
     */
    public String getDeductionCountQuery(DeductionDetailsDTO deductionDTO, TableDTO tableDTO) {
        String fromDate[] = deductionDTO.getDetailsFromDate() == null || "".equals(deductionDTO.getDetailsFromDate()) || ConstantsUtils.NULL.equals(deductionDTO.getDetailsFromDate()) ? String.valueOf(deductionDTO.getForecastFromDate()).split("-") : deductionDTO.getDetailsFromDate().split("-");
        String toDate[] = deductionDTO.getDetailsToDate() == null || "".equals(deductionDTO.getDetailsToDate()) || ConstantsUtils.NULL.equals(deductionDTO.getDetailsToDate()) ? String.valueOf(deductionDTO.getForecastToDate()).split("-") : deductionDTO.getDetailsToDate().split("-");
        String query = "";
        if (deductionDTO.getDataView().equals(ConstantsUtils.CUSTOMER)) {
            if (deductionDTO.getFilterDdlb() != null && !ConstantsUtils.NULL.equals(deductionDTO.getFilterDdlb())) {
                if (deductionDTO.getLevelNo() == 1) {
                    query = CustomSQLUtil.get("customercount")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace("?FILTER"," AND COMPANY_MASTER_SID='"+deductionDTO.getFilterDdlb()+"' ");
                } else if (deductionDTO.getLevelNo() == 2) {
                   query = CustomSQLUtil.get("brandcount")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'");

                } else if (deductionDTO.getLevelNo() == 3) {
                    query = CustomSQLUtil.get("cust-brand-item-count")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace("?CMID", "'"+tableDTO.getCompanySid()+"'")
                            .replace("?BMID", "'"+tableDTO.getBrandSid()+"'");
                }
            } else {
                if (deductionDTO.getLevelNo() == 1) {
                    query = "SELECT COUNT(DISTINCT 'Total') AS TOTAL FROM ST_DEDUCTION_CALENDAR_DETAILS WHERE USER_ID='" + deductionDTO.getUserId() + "' AND SESSION_ID='" + deductionDTO.getSessionId() + "' ";
                } else if (deductionDTO.getLevelNo() == 2) {
                    query = CustomSQLUtil.get("customercount")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace("?FILTER"," ");
                } else if (deductionDTO.getLevelNo() == 3) {
                    query = CustomSQLUtil.get("brandcount")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'");

                } else if (deductionDTO.getLevelNo() == 4) {
                    query = CustomSQLUtil.get("cust-brand-item-count")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace("?CMID", "'"+tableDTO.getCompanySid()+"'")
                            .replace("?BMID", "'"+tableDTO.getBrandSid()+"'");
                }
            }
        } else if (deductionDTO.getDataView().equals(ConstantsUtils.PRODUCT)) {
            if (deductionDTO.getFilterDdlb() != null && !ConstantsUtils.NULL.equals(deductionDTO.getFilterDdlb())) {
                
                if (deductionDTO.getLevelNo() == 1) {
                    query = CustomSQLUtil.get("prod-level1-count")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace("?FILTER"," AND IM.ITEM_MASTER_SID= "+deductionDTO.getFilterDdlb());
                } else if (deductionDTO.getLevelNo() == 2) {
                    query = CustomSQLUtil.get("prod-level2-count")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace("?BMSID",  "'"+tableDTO.getBrandSid()+"'")
                            .replace("?FILTER"," AND IM.ITEM_MASTER_SID= "+deductionDTO.getFilterDdlb());
                } else if (deductionDTO.getLevelNo() == 3) {
                    query = CustomSQLUtil.get("prod-level3-count")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace("?BMSID",  "'"+tableDTO.getBrandSid()+"'")
                            .replace("?CMID",  "'"+tableDTO.getCompanySid()+"'")
                            .replace("?FILTER"," AND IM.ITEM_MASTER_SID= "+deductionDTO.getFilterDdlb());
                }
                
            } else {
                if (deductionDTO.getLevelNo() == 1) {
                    query = "SELECT COUNT(DISTINCT 'Total') AS TOTAL FROM ST_DEDUCTION_CALENDAR_DETAILS WHERE USER_ID='" + deductionDTO.getUserId() + "' AND SESSION_ID='" + deductionDTO.getSessionId() + "' ";
                } else if (deductionDTO.getLevelNo() == 2) {
                    query = CustomSQLUtil.get("prod-level1-count")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace("?FILTER"," ");
                } else if (deductionDTO.getLevelNo() == 3) {
                    query = CustomSQLUtil.get("prod-level2-count")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace("?BMSID",  "'"+tableDTO.getBrandSid()+"'")
                            .replace("?FILTER"," ");
                } else if (deductionDTO.getLevelNo() == 4) {
                    query = CustomSQLUtil.get("prod-level3-count")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace("?BMSID",  "'"+tableDTO.getBrandSid()+"'")
                            .replace("?CMID",  "'"+tableDTO.getCompanySid()+"'")
                            .replace("?FILTER"," ");
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
        String fromDate[] = deductionDTO.getDetailsFromDate() == null || "".equals(deductionDTO.getDetailsFromDate()) || ConstantsUtils.NULL.equals(deductionDTO.getDetailsFromDate()) ? String.valueOf(deductionDTO.getForecastFromDate()).split("-") : deductionDTO.getDetailsFromDate().split("-");
        String toDate[] = deductionDTO.getDetailsToDate() == null || "".equals(deductionDTO.getDetailsToDate()) || ConstantsUtils.NULL.equals(deductionDTO.getDetailsToDate()) ? String.valueOf(deductionDTO.getForecastToDate()).split("-") : deductionDTO.getDetailsToDate().split("-");
        String query = "";
        if (deductionDTO.getDataView().equals(ConstantsUtils.CUSTOMER)) {
            if (deductionDTO.getFilterDdlb() != null && !ConstantsUtils.NULL.equals(deductionDTO.getFilterDdlb())) {
                if (deductionDTO.getLevelNo() == 1) {
                    query = CustomSQLUtil.get("customerlevel2")
                            .replace("?columnlist", CustomSQLUtil.get("customerlevelcolumns"))
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace("?FROM",fromDate[0])
                            .replace("?TO", toDate[0])
                            .replace("?START",String.valueOf(start))
                            .replace("?OFFSET",String.valueOf(offset))
                            .replace("?FILTER"," AND COMPANY_MASTER_SID='"+deductionDTO.getFilterDdlb()+"' ");
                    query =getColumnListbyFreq(query, deductionDTO.getFrequency());
                } else if (deductionDTO.getLevelNo() == 2) {
                    query=CustomSQLUtil.get("customerlevel3")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace("?FROM", fromDate[0])
                            .replace("?TO", toDate[0])
                            .replace("?CMID", String.valueOf(tableDTO.getCompanySid()))
                            .replace("?START",String.valueOf(start))
                            .replace("?OFFSET",String.valueOf(offset));
                    query =getColumnListbyFreq(query, deductionDTO.getFrequency());
                    
                    
                } else if (deductionDTO.getLevelNo() == 3) {
                    query=CustomSQLUtil.get("customerlevel4")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace("?FROM", fromDate[0])
                            .replace("?TO", toDate[0])
                            .replace("?CMID", String.valueOf(tableDTO.getCompanySid()))
                            .replace("?BMSID", String.valueOf(tableDTO.getBrandSid()))
                            .replace("?START",String.valueOf(start))
                            .replace("?OFFSET",String.valueOf(offset));
                   
                    query =getColumnListbyFreq(query, deductionDTO.getFrequency());
                }
            } else {
                if (deductionDTO.getLevelNo() == 1) {
                    query=CustomSQLUtil.get("totalcustomer")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'");
                    query =getColumnListbyFreq(query, deductionDTO.getFrequency());
                } else if (deductionDTO.getLevelNo() == 2) {
                    query = CustomSQLUtil.get("customerlevel2")
                            .replace("?columnlist", CustomSQLUtil.get("customerlevelcolumns"))
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace("?FROM",fromDate[0])
                            .replace("?TO", toDate[0])
                            .replace("?START",String.valueOf(start))
                            .replace("?OFFSET",String.valueOf(offset))
                            .replace("?FILTER"," ");
                    query =getColumnListbyFreq(query, deductionDTO.getFrequency());
                } else if (deductionDTO.getLevelNo() == 3) {
                    query=CustomSQLUtil.get("customerlevel3")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace("?FROM", fromDate[0])
                            .replace("?TO", toDate[0])
                            .replace("?CMID", String.valueOf(tableDTO.getCompanySid()))
                            .replace("?START",String.valueOf(start))
                            .replace("?OFFSET",String.valueOf(offset));
                    query =getColumnListbyFreq(query, deductionDTO.getFrequency());
                } else if (deductionDTO.getLevelNo() == 4) {
                    query=CustomSQLUtil.get("customerlevel4")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace("?FROM", fromDate[0])
                            .replace("?TO", toDate[0])
                            .replace("?CMID", String.valueOf(tableDTO.getCompanySid()))
                            .replace("?BMSID", String.valueOf(tableDTO.getBrandSid()))
                            .replace("?START",String.valueOf(start))
                            .replace("?OFFSET",String.valueOf(offset));
                    query =getColumnListbyFreq(query, deductionDTO.getFrequency());
                }
            }
        } else if (deductionDTO.getDataView().equals(ConstantsUtils.PRODUCT)) {
            if (deductionDTO.getFilterDdlb() != null && !ConstantsUtils.NULL.equals(deductionDTO.getFilterDdlb())) {
                if (deductionDTO.getLevelNo() == 1) {
                    query=CustomSQLUtil.get("prod-level1-data")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace("?FROM", fromDate[0])
                            .replace("?TO", toDate[0])
                            .replace("?START",String.valueOf(start))
                            .replace("?OFFSET",String.valueOf(offset))
                            .replace("?FILTER", " AND im.ITEM_MASTER_SID = "+deductionDTO.getFilterDdlb());
                    query =getColumnListbyFreq(query, deductionDTO.getFrequency());
                } else if (deductionDTO.getLevelNo() == 2) {
                    query=CustomSQLUtil.get("prod-level2-data")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace("?FROM", fromDate[0])
                            .replace("?TO", toDate[0])
                            .replace("?START",String.valueOf(start))
                            .replace("?OFFSET",String.valueOf(offset))
                            .replace("?BMSID",  "'"+tableDTO.getBrandSid()+"'")
                            .replace("?FILTER", " AND im.ITEM_MASTER_SID = "+deductionDTO.getFilterDdlb());
                    query =getColumnListbyFreq(query, deductionDTO.getFrequency());
                } else if (deductionDTO.getLevelNo() == 3) {
                    query=CustomSQLUtil.get("prod-level3-data")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace("?FROM", fromDate[0])
                            .replace("?TO", toDate[0])
                            .replace("?START",String.valueOf(start))
                            .replace("?OFFSET",String.valueOf(offset))
                            .replace("?BMSID",  "'"+tableDTO.getBrandSid()+"'")
                            .replace("?CMID",  "'"+tableDTO.getCompanySid()+"'")
                            .replace("?FILTER", " AND im.ITEM_MASTER_SID = "+deductionDTO.getFilterDdlb());
                    query =getColumnListbyFreq(query, deductionDTO.getFrequency());
                }
                
            } else {
                if (deductionDTO.getLevelNo() == 1) {
                    query=CustomSQLUtil.get("totalcustomer")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'");
                    query =getColumnListbyFreq(query, deductionDTO.getFrequency());
                } else if (deductionDTO.getLevelNo() == 2) {
                    query=CustomSQLUtil.get("prod-level1-data")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace("?FROM", fromDate[0])
                            .replace("?TO", toDate[0])
                            .replace("?START",String.valueOf(start))
                            .replace("?OFFSET",String.valueOf(offset))
                            .replace("?FILTER", "");
                    query =getColumnListbyFreq(query, deductionDTO.getFrequency());
                } else if (deductionDTO.getLevelNo() == 3) {
                    query=CustomSQLUtil.get("prod-level2-data")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace("?FROM", fromDate[0])
                            .replace("?TO", toDate[0])
                            .replace("?START",String.valueOf(start))
                            .replace("?OFFSET",String.valueOf(offset))
                            .replace("?BMSID",  "'"+tableDTO.getBrandSid()+"'")
                            .replace("?FILTER", "");
                    query =getColumnListbyFreq(query, deductionDTO.getFrequency());
                } else if (deductionDTO.getLevelNo() == 4) {
                    query=CustomSQLUtil.get("prod-level3-data")
                            .replace(ConstantsUtils.UID, deductionDTO.getUserId())
                            .replace("?SID", "'"+deductionDTO.getSessionId()+"'")
                            .replace("?FROM", fromDate[0])
                            .replace("?TO", toDate[0])
                            .replace("?START",String.valueOf(start))
                            .replace("?OFFSET",String.valueOf(offset))
                            .replace("?BMSID",  "'"+tableDTO.getBrandSid()+"'")
                            .replace("?CMID",  "'"+tableDTO.getCompanySid()+"'")
                            .replace("?FILTER", "");
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
    public String getItemCount(int sid, int levelNo,DeductionDetailsDTO deductionDTO) {
        String query = "";
        if (levelNo == 2) {
            query = CustomSQLUtil.get("getItemCountForCustomer")
            .replace(ConstantsUtils.SID, String.valueOf(sid))
            .replace(ConstantsUtils.USERID_AT, deductionDTO.getUserId())
            .replace(ConstantsUtils.SESSION_ID_AT, deductionDTO.getSessionId());
        } else if (levelNo == 3) {
            query = CustomSQLUtil.get("getItemCountForBrand")
            .replace(ConstantsUtils.SID, String.valueOf(sid))
            .replace(ConstantsUtils.USERID_AT, deductionDTO.getUserId())
            .replace(ConstantsUtils.SESSION_ID_AT, deductionDTO.getSessionId());
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
        int levelNo = Integer.valueOf(String.valueOf(obj[4]));
        boolean isFiltered= StringUtils.isNotEmpty(deductionDTO.getFilterDdlb())&&!ConstantsUtils.NULL.equalsIgnoreCase(deductionDTO.getFilterDdlb());
        String query = "";
        if (levelNo == (isFiltered ? 1 : 2)) {
            query = CustomSQLUtil.get("getLineLevelUpdateForCust")
           .replace("?VALUE", value)
           .replace(ConstantsUtils.SID, String.valueOf(dto.getCompanySid()))
           .replace("@YEAR", String.valueOf(obj[2]))
           .replace("@MONTH", String.valueOf(obj[0]))
           .replace(ConstantsUtils.USERID_AT, deductionDTO.getUserId())
           .replace(ConstantsUtils.SESSION_ID_AT, deductionDTO.getSessionId());
        } else if (levelNo == (isFiltered ? 2 : 3)) {
            query = CustomSQLUtil.get("getLineLevelUpdateForBrand")
            .replace("?VALUE", value)
            .replace(ConstantsUtils.SID, String.valueOf(dto.getBrandSid()))
            .replace("@COMPANY_SID", String.valueOf(dto.getCompanySid()))
            .replace("@YEAR", String.valueOf(obj[2]))
            .replace("@MONTH", String.valueOf(obj[0]))
            .replace(ConstantsUtils.USERID_AT, deductionDTO.getUserId())
            .replace(ConstantsUtils.SESSION_ID_AT, deductionDTO.getSessionId());
        } else {
            query = CustomSQLUtil.get("getLineLevelUpdateForItem")
            .replace("?VALUE", value)
            .replace(ConstantsUtils.SID, String.valueOf(dto.getItemSid()))
            .replace("@BRAND_SID", String.valueOf(dto.getBrandSid()))
            .replace("@COMPANY_SID", String.valueOf(dto.getCompanySid()))
            .replace("@YEAR", String.valueOf(obj[2]))
            .replace("@MONTH", String.valueOf(obj[0]))
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
        int levelNo = Integer.valueOf(String.valueOf(obj[4]));
        boolean isFiltered= StringUtils.isNotEmpty(deductionDTO.getFilterDdlb())&&!ConstantsUtils.NULL.equalsIgnoreCase(deductionDTO.getFilterDdlb());
        String query = "";
        if (levelNo == (isFiltered ? 1 : 2)) {
            query = CustomSQLUtil.get("getLineLevelUpdateForBrandinProd")
            .replace("?VALUE", value)
            .replace(ConstantsUtils.SID, String.valueOf(dto.getBrandSid()))
            .replace("@COMPANY_SID", StringUtils.EMPTY)
            .replace("@YEAR", String.valueOf(obj[2]))
            .replace("@MONTH", String.valueOf(obj[0]))
            .replace(ConstantsUtils.USERID_AT, deductionDTO.getUserId())
            .replace(ConstantsUtils.SESSION_ID_AT, deductionDTO.getSessionId())
            .replace("?FILTER",isFiltered ? " AND im.ITEM_MASTER_SID = "+deductionDTO.getFilterDdlb() : " ");
        } else if (levelNo == (isFiltered ? 2 : 3)) {
            query = CustomSQLUtil.get("getLineLevelUpdateForCustinProd")
            .replace("?VALUE", value)
            .replace(ConstantsUtils.SID, String.valueOf(dto.getCompanySid()))
            .replace("@BRAND_SID", String.valueOf(dto.getBrandSid()))
            .replace("@YEAR", String.valueOf(obj[2]))
            .replace("@MONTH", String.valueOf(obj[0]))
            .replace(ConstantsUtils.USERID_AT, deductionDTO.getUserId())
            .replace(ConstantsUtils.SESSION_ID_AT, deductionDTO.getSessionId())
            .replace("?FILTER", isFiltered ? " AND im.ITEM_MASTER_SID = "+deductionDTO.getFilterDdlb() : " ");
        } else {
            query = CustomSQLUtil.get("getLineLevelUpdateForItem")
            .replace("?VALUE", value)
            .replace(ConstantsUtils.SID, String.valueOf(dto.getItemSid()))
            .replace("@BRAND_SID", String.valueOf(dto.getBrandSid()))
            .replace("@COMPANY_SID", String.valueOf(dto.getCompanySid()))
            .replace("@YEAR", String.valueOf(obj[2]))
            .replace("@MONTH", String.valueOf(obj[0]))
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
     * @param isCheckAll
     * @return 
     */
    public String getCheckQueryForCustomer(DeductionDetailsDTO deductionDTO, TableDTO dto, boolean check, boolean isCheckAll, String filterValue) {
        String fromDate[] = deductionDTO.getDetailsFromDate() == null || "".equals(deductionDTO.getDetailsFromDate()) || ConstantsUtils.NULL.equals(deductionDTO.getDetailsFromDate()) ? String.valueOf(deductionDTO.getForecastFromDate()).split("-") : deductionDTO.getDetailsFromDate().split("-");
        String toDate[] = deductionDTO.getDetailsToDate() == null || "".equals(deductionDTO.getDetailsToDate()) || ConstantsUtils.NULL.equals(deductionDTO.getDetailsToDate()) ? String.valueOf(deductionDTO.getForecastToDate()).split("-") : deductionDTO.getDetailsToDate().split("-");
        String query = "";
        String value = "";
        if (check) {
            value = "1";
        } else {
            value = "0";
        }
        if (dto.getLevelNo() == (ConstantsUtils.NULL.equals(filterValue) ? 2 : 1)) {
            query = CustomSQLUtil.get("checkTempTable")
            .replace("@CHECK", value)
            .replace("@JOIN", StringUtils.EMPTY)
            .replace("@WHERE", "CD.COMPANY_MASTER_SID = " + String.valueOf(dto.getCompanySid()))
            .replace(ConstantsUtils.USERID_AT, deductionDTO.getUserId())
            .replace(ConstantsUtils.SESSION_ID_AT, deductionDTO.getSessionId());
        } else if (dto.getLevelNo() == (ConstantsUtils.NULL.equals(filterValue) ? 3 : 2)) {
            query = CustomSQLUtil.get("checkTempTableBrand")
            .replace("@CHECK", value)
            .replace("@WHERE", "IM.BRAND_MASTER_SID =" + dto.getBrandSid() + " AND CD.COMPANY_MASTER_SID =" + dto.getCompanySid())
            .replace(ConstantsUtils.USERID_AT, deductionDTO.getUserId())
            .replace(ConstantsUtils.SESSION_ID_AT, deductionDTO.getSessionId());
        } else {
            query = CustomSQLUtil.get("checkTempTable")
            .replace("@CHECK", value)
            .replace("@JOIN", StringUtils.EMPTY)
            .replace("@WHERE", "CD.COMPANY_MASTER_SID = " + String.valueOf(dto.getCompanySid()) + " AND CD.ITEM_MASTER_SID = " + String.valueOf(dto.getItemSid()))
            .replace(ConstantsUtils.USERID_AT, deductionDTO.getUserId())
            .replace(ConstantsUtils.SESSION_ID_AT, deductionDTO.getSessionId());
        }
        return query;
    }

    /**
     * Check query for product
     *
     * @param levelNo
     * @param sid
     * @param check
     * @param isCheckAll
     * @return
     */
    public String getCheckQueryForProduct(DeductionDetailsDTO deductionDTO, TableDTO dto, boolean check, boolean isCheckAll, String filterValue) {
        String query = "";
        String value = "";
        if (check) {
            value = "1";
        } else {
            value = "0";
        }
        if (dto.getLevelNo() == (ConstantsUtils.NULL.equals(filterValue) ? 2 : 1)) {
            query = CustomSQLUtil.get("checkTempTableBrand");
            query = query.replace("@CHECK", value);
            query = query.replace("@WHERE", "IM.BRAND_MASTER_SID = " + dto.getBrandSid());
            query = query.replace(ConstantsUtils.USERID_AT, deductionDTO.getUserId());
            query = query.replace(ConstantsUtils.SESSION_ID_AT, deductionDTO.getSessionId());
        } else if (dto.getLevelNo() == (ConstantsUtils.NULL.equals(filterValue) ? 3 : 2)) {
            query = CustomSQLUtil.get("checkTempTable");
            query = query.replace("@CHECK", value);
            query = query.replace("@JOIN", " JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID = CD.ITEM_MASTER_SID");
            query = query.replace("@WHERE", "CD.COMPANY_MASTER_SID = " + String.valueOf(dto.getCompanySid()) + " AND IM.BRAND_MASTER_SID ="+String.valueOf(dto.getBrandSid()));
            query = query.replace(ConstantsUtils.USERID_AT, deductionDTO.getUserId());
            query = query.replace(ConstantsUtils.SESSION_ID_AT, deductionDTO.getSessionId());
        } else {
            query = CustomSQLUtil.get("checkTempTable");
            query = query.replace("@CHECK", value);
            query = query.replace("@JOIN", " JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID= CD.ITEM_MASTER_SID");
            query = query.replace("@WHERE", "CD.COMPANY_MASTER_SID = " + String.valueOf(dto.getCompanySid()) + " AND IM.BRAND_MASTER_SID ="+String.valueOf(dto.getBrandSid())+" AND IM.ITEM_MASTER_SID = " + String.valueOf(dto.getItemSid()));
            query = query.replace(ConstantsUtils.USERID_AT, deductionDTO.getUserId());
            query = query.replace(ConstantsUtils.SESSION_ID_AT, deductionDTO.getSessionId());
        }
        return query;
    }

    /**
     * Check all
     *
     * @param check
     * @return
     */
    public String getCheckAllQuery(DeductionDetailsDTO deductionDTO,boolean check) {
        String query = CustomSQLUtil.get("checkAllTemp");
        if (check) {
            query = query.replace("@CHECK", "1");
        } else {
            query = query.replace("@CHECK", "0");
        }
        query = query.replace(ConstantsUtils.USERID_AT, deductionDTO.getUserId());
        query = query.replace(ConstantsUtils.SESSION_ID_AT, deductionDTO.getSessionId());
        return query;
    }

    /**
     * 
     * @param list
     * @return 
     */   
    public String getAdjustPeriodQuery(List list) {
        String query = "SELECT PERIOD_SID FROM PERIOD WHERE (MONTH IN ('";
        String lastValue = "";
        for (int i = 0; i < list.size(); i++) {
            Object[] arr = String.valueOf(list.get(i)).split("~");
            if (!lastValue.isEmpty() && !lastValue.equals(String.valueOf(arr[2]))) {
                query += "') AND YEAR =" + lastValue;
                query += ") OR ( MONTH IN ('";
            }
            if (list.size() > 1) {
                if (i == 0) {
                    query += arr[1];
                } else {
                    query += "','" + arr[1];
                }
            } else {
                query += arr[1];
            }
            lastValue = String.valueOf(arr[2]);
            if(i==list.size()-1){
                query += "') AND YEAR =" + arr[2]+" )";
            }
        }
        return query;
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
    public String getAdjustQuery(DeductionDetailsDTO dto, String type, String basis, String variable, String amount, String method, String periodQuery, String selectedOption) {
        String query = "UPDATE ST_DEDUCTION_CALENDAR_DETAILS SET ADJUSTMENT_TYPE = '" + type + "', ADJUSTMENT_BASIS = '" + basis + "',ADJUSTMENT_VARIABLE = '" + variable + "',"
                + "ADJUSTMENT_VALUE = '" + amount + "', ADJUSTMENT_ALLOCATION_METHODOLOGY = '" + method + "' WHERE";
        if ("Select".equals(selectedOption)) {
            query += " PERIOD_SID IN (" + periodQuery + ") AND ";
        }
        query += " USER_ID = '" + dto.getUserId() + "' AND SESSION_ID = '" + dto.getSessionId()+ "'";
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
                query = query.replace("?FREQ", CustomSQLUtil.get("annualdisc"));
                break;
            case "Quarterly":
                query = query.replace("?FREQ", CustomSQLUtil.get("quarterlydisc"));
                break;
            case "Semi-Annual":
                query = query.replace("?FREQ", CustomSQLUtil.get("semidisc"));
                break;
            case "Monthly":
                query = query.replace("?FREQ", CustomSQLUtil.get("monthlydisc"));
                break;
        }
        return query;

    }
}
