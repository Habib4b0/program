/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.deductioncalendar.logic;

import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.deductioncalendar.dto.DeductionDetailsDTO;
import com.stpl.app.global.deductioncalendar.dto.TableDTO;
import com.stpl.app.global.deductioncalendar.ui.util.CommonUtil;
import com.stpl.app.global.deductioncalendar.ui.util.QueryUtils;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.util.dao.orm.CustomSQLUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author sibi
 */
public class DeductionDetailsLogic {

    private static final Logger LOGGER = Logger.getLogger(DeductionDetailsLogic.class);
    QueryUtils queryUtils = new QueryUtils();
    DecimalFormat DEC_FORMAT = new DecimalFormat("###0.00");

    /**
     * Method for getting start and end period from forecast configuration
     *
     * @param detailsDTO
     * @return
     */
    public DeductionDetailsDTO getForecastConfig(DeductionDetailsDTO detailsDTO) {
        try {
            String query = queryUtils.getForecastConfigQuery();
            List<Object> resultList = (List) RsModelLocalServiceUtil.executeSelectQuery(query, null, null);
            if (resultList.size() > 0) {
                Object[] obj = null;
                for (int i = 0; i < resultList.size(); i++) {
                    obj = (Object[]) resultList.get(i);
                    detailsDTO.setForecastFromDate(String.valueOf(obj[0]));
                    detailsDTO.setForecastToDate(String.valueOf(obj[1]));
                }
                return detailsDTO;
            } else {
                return null;
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return null;
        }
    }

    /**
     * Count method
     *
     * @param getParent
     * @param deductionDTO
     * @param tableDTO
     * @return
     */
    public int getDeductionDetailsCount(Object getParent, DeductionDetailsDTO deductionDTO, TableDTO tableDTO) {
        LOGGER.info("Inside getDeductionDetailsCount");
        try {
            int count = 0;
            if (getParent != null && getParent instanceof TableDTO) {
                tableDTO = (TableDTO) getParent;
                deductionDTO.setLevelNo(tableDTO.getLevelNo() + 1);
            } else {
                if (tableDTO == null) {
                    tableDTO = new TableDTO();
                }
                deductionDTO.setLevelNo(1);
            }
            String query = queryUtils.getDeductionCountQuery(deductionDTO, tableDTO);
            List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
            count = list.size() == 0 || list == null ? 0 : Integer.valueOf(String.valueOf(list.get(0)));
            LOGGER.info("Inside getDeductionDetailsCount");
            return count;
        } catch (Exception e) {
            LOGGER.error(e);;
            return 0;
        }
    }
    
    /**
     * Load data method
     *
     * @param getParent
     * @param deductionDTO
     * @param tableDTO
     * @param start
     * @param offset
     * @return
     */
    public List<TableDTO> getDeductionDetailsData(Object getParent, DeductionDetailsDTO deductionDTO, TableDTO tableDTO, int start, int offset) {
        LOGGER.info("Inside getDeductionDetailsData ");
        if (getParent != null && getParent instanceof TableDTO) {
            tableDTO = (TableDTO) getParent;
            deductionDTO.setLevelNo(tableDTO.getLevelNo() + 1);
        } else {
            if (tableDTO == null) {
                tableDTO = new TableDTO();
            }
            deductionDTO.setLevelNo(1);
        }

        String query = queryUtils.getDeductionDataQuery(deductionDTO, tableDTO, start, offset);
        List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        List<TableDTO> returnList = getCustomizedResultList(list, deductionDTO, tableDTO);
        LOGGER.info("Ending getDeductionDetailsData ");
        return returnList;
    }

    /**
     * Customized form
     *
     * @param list
     * @return
     */
    private List getCustomizedResultList(List list, DeductionDetailsDTO deductionDTO, TableDTO parentIdDto) {
        try {
            LOGGER.info("Inside getCustomizedResultList with list size" + list.size());
            List<TableDTO> resultList = new ArrayList<TableDTO>();
            String lastValue = StringUtils.EMPTY;
            TableDTO dto = new TableDTO();
            String freq=deductionDTO.getFrequency();
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                if (StringUtils.isBlank(lastValue) || String.valueOf(obj[0]).equals(lastValue)) {
                } else {
                    resultList.add(dto);
                    dto = new TableDTO();
                }
                if (deductionDTO.getFilterDdlb()!=null && !ConstantsUtils.NULL.equals(deductionDTO.getFilterDdlb())) {
                        dto.setParent(deductionDTO.getLevelNo()== 3 ? 0 : 1);
                        dto.setLevelNo(deductionDTO.getLevelNo());
                        dto.setSid(Integer.valueOf(String.valueOf(obj[0])));
                        dto.setGroup(String.valueOf(obj[1]));
                        dto.setCcpCount(Integer.valueOf(String.valueOf(obj[3])));
                        if (obj[4] != null && "1".equals(String.valueOf(obj[4]))) {
                            dto.addBooleanProperties("check", Boolean.TRUE);
                        }
                        dto.setUncheckCount(Integer.valueOf(String.valueOf(obj[5])));
                        int index = 6;
                        String stringProperty;
                        switch (freq) {
                            case "Annual":
                                stringProperty = CommonUtil.columnProperty(Integer.valueOf(String.valueOf(obj[2])),
                                        0, deductionDTO);
                                dto.addStringProperties(stringProperty, obj[index] != null || !ConstantsUtils.NULL.equals(String.valueOf(obj[index])) ? DEC_FORMAT.format(Double.valueOf(String.valueOf(obj[index]))) : "0.00");
                                break;
                            case "Semi-Annual":
                                for (int semi = 1; semi <= 2; semi++, index++) {
                                    stringProperty = CommonUtil.columnProperty(Integer.valueOf(String.valueOf(obj[2])),
                                            semi, deductionDTO);
                                    dto.addStringProperties(stringProperty, obj[index] != null || !ConstantsUtils.NULL.equals(String.valueOf(obj[index])) ? DEC_FORMAT.format(Double.valueOf(String.valueOf(obj[index]))) : "0.00");
                                }
                                break;
                            case "Quarterly":
                                for (int quarter = 1; quarter <= 4; quarter++, index++) {
                                    stringProperty = CommonUtil.columnProperty(Integer.valueOf(String.valueOf(obj[2])),
                                            quarter, deductionDTO);
                                    dto.addStringProperties(stringProperty, obj[index] != null || !ConstantsUtils.NULL.equals(String.valueOf(obj[index])) ? DEC_FORMAT.format(Double.valueOf(String.valueOf(obj[index]))) : "0.00");
                                }
                                break;
                            case "Monthly":
                                for (int month = 1; month <= 12; month++, index++) {
                                    stringProperty = CommonUtil.columnProperty(Integer.valueOf(String.valueOf(obj[2])),
                                            month, deductionDTO);
                                    dto.addStringProperties(stringProperty, obj[index] != null || !ConstantsUtils.NULL.equals(String.valueOf(obj[index])) ? DEC_FORMAT.format(Double.valueOf(String.valueOf(obj[index]))) : "0.00");
                                }
                                break;

                        }
                        lastValue = String.valueOf(obj[0]);
                    if (deductionDTO.getDataView().equals(ConstantsUtils.CUSTOMER)) {
                        if (deductionDTO.getLevelNo() == 1) {
                            dto.setCompanySid(dto.getSid());
                        } else if (deductionDTO.getLevelNo() == 2) {
                            dto.setBrandSid(dto.getSid());
                            dto.setCompanySid(parentIdDto.getCompanySid());
                        } else if (deductionDTO.getLevelNo() == 3) {
                            dto.setItemSid(dto.getSid());
                            dto.setBrandSid(parentIdDto.getBrandSid());
                            dto.setCompanySid(parentIdDto.getCompanySid());
                        }
                    } else {
                        if (deductionDTO.getLevelNo() == 1) {
                            dto.setBrandSid(dto.getSid());
                        } else if (deductionDTO.getLevelNo() == 2) {
                            dto.setCompanySid(dto.getSid());
                            dto.setBrandSid(parentIdDto.getBrandSid());
                        } else if (deductionDTO.getLevelNo() == 3) {
                            dto.setItemSid(dto.getSid());
                            dto.setBrandSid(parentIdDto.getBrandSid());
                            dto.setCompanySid(parentIdDto.getCompanySid());
                        }
                    }
                } else {
                    if (deductionDTO.getLevelNo() > 1) {
                        dto.setParent(deductionDTO.getLevelNo()== 4 ? 0 : 1);
                        dto.setLevelNo(deductionDTO.getLevelNo());
                        dto.setSid(Integer.valueOf(String.valueOf(obj[0])));
                        dto.setGroup(String.valueOf(obj[1]));
                        dto.setCcpCount(Integer.valueOf(String.valueOf(obj[3])));
                        if (obj[4] != null && "1".equals(String.valueOf(obj[4]))) {
                            dto.addBooleanProperties("check", Boolean.TRUE);
                        }
                        dto.setUncheckCount(Integer.valueOf(String.valueOf(obj[5])));
                        int index = 6;
                        String stringProperty;
                        switch (freq) {
                            case "Annual":
                                stringProperty = CommonUtil.columnProperty(Integer.valueOf(String.valueOf(obj[2])),
                                        0, deductionDTO);
                                dto.addStringProperties(stringProperty, obj[index] != null || !ConstantsUtils.NULL.equals(String.valueOf(obj[index])) ? DEC_FORMAT.format(Double.valueOf(String.valueOf(obj[index]))) : "0.00");
                                break;
                            case "Semi-Annual":
                                for (int semi = 1; semi <= 2; semi++, index++) {
                                    stringProperty = CommonUtil.columnProperty(Integer.valueOf(String.valueOf(obj[2])),
                                            semi, deductionDTO);
                                    dto.addStringProperties(stringProperty, obj[index] != null || !ConstantsUtils.NULL.equals(String.valueOf(obj[index])) ? DEC_FORMAT.format(Double.valueOf(String.valueOf(obj[index]))) : "0.00");
                                }
                                break;
                            case "Quarterly":
                                for (int quarter = 1; quarter <= 4; quarter++, index++) {
                                    stringProperty = CommonUtil.columnProperty(Integer.valueOf(String.valueOf(obj[2])),
                                            quarter, deductionDTO);
                                    dto.addStringProperties(stringProperty, obj[index] != null || !ConstantsUtils.NULL.equals(String.valueOf(obj[index])) ? DEC_FORMAT.format(Double.valueOf(String.valueOf(obj[index]))) : "0.00");
                                }
                                break;
                            case "Monthly":
                                for (int month = 1; month <= 12; month++, index++) {
                                    stringProperty = CommonUtil.columnProperty(Integer.valueOf(String.valueOf(obj[2])),
                                            month, deductionDTO);
                                    dto.addStringProperties(stringProperty, obj[index] != null || !ConstantsUtils.NULL.equals(String.valueOf(obj[index])) ? DEC_FORMAT.format(Double.valueOf(String.valueOf(obj[index]))) : "0.00");
                                }
                                break;

                        }
                        lastValue = String.valueOf(obj[0]);
                    } else {
                        dto.setParent(1);
                        dto.setLevelNo(deductionDTO.getLevelNo());
                        dto.setGroup(String.valueOf(obj[0]));
                        dto.setCcpCount(Integer.valueOf(String.valueOf(obj[2])));
                        dto.setUncheckCount(Integer.valueOf(String.valueOf(obj[4])));
                        if (obj[3] != null && "1".equals(String.valueOf(obj[3]))) {
                            dto.addBooleanProperties("check", Boolean.TRUE);
                        }
                        int index = 5;
                        String stringProperty;
                        switch (freq) {
                            case "Annual":
                                stringProperty = CommonUtil.columnProperty(Integer.valueOf(String.valueOf(obj[1])),
                                        0, deductionDTO);
                                dto.addStringProperties(stringProperty, obj[index] != null || !ConstantsUtils.NULL.equals(String.valueOf(obj[index])) ? DEC_FORMAT.format(Double.valueOf(String.valueOf(obj[index]))) : "0.00");
                                break;
                            case "Semi-Annual":
                                for (int semi = 1; semi <= 2; semi++, index++) {
                                    stringProperty = CommonUtil.columnProperty(Integer.valueOf(String.valueOf(obj[1])),
                                            semi, deductionDTO);
                                    dto.addStringProperties(stringProperty, obj[index] != null || !ConstantsUtils.NULL.equals(String.valueOf(obj[index])) ? DEC_FORMAT.format(Double.valueOf(String.valueOf(obj[index]))) : "0.00");
                                }
                                break;
                            case "Quarterly":
                                for (int quarter = 1; quarter <= 4; quarter++, index++) {
                                    stringProperty = CommonUtil.columnProperty(Integer.valueOf(String.valueOf(obj[1])),
                                            quarter, deductionDTO);
                                    dto.addStringProperties(stringProperty, obj[index] != null || !ConstantsUtils.NULL.equals(String.valueOf(obj[index])) ? DEC_FORMAT.format(Double.valueOf(String.valueOf(obj[index]))) : "0.00");
                                }
                                break;
                            case "Monthly":
                                for (int month = 1; month <= 12; month++, index++) {
                                    stringProperty = CommonUtil.columnProperty(Integer.valueOf(String.valueOf(obj[1])),
                                            month, deductionDTO);
                                    dto.addStringProperties(stringProperty, obj[index] != null || !ConstantsUtils.NULL.equals(String.valueOf(obj[index])) ? DEC_FORMAT.format(Double.valueOf(String.valueOf(obj[index]))) : "0.00");
                                }
                                break;

                        }
                        
                        
                        
                    }
                    if (deductionDTO.getDataView().equals(ConstantsUtils.CUSTOMER)) {
                        if (deductionDTO.getLevelNo() == 2) {
                            dto.setCompanySid(dto.getSid());
                        } else if (deductionDTO.getLevelNo() == 3) {
                            dto.setBrandSid(dto.getSid());
                            dto.setCompanySid(parentIdDto.getCompanySid());
                        } else if (deductionDTO.getLevelNo() == 4) {
                            dto.setItemSid(dto.getSid());
                            dto.setBrandSid(parentIdDto.getBrandSid());
                            dto.setCompanySid(parentIdDto.getCompanySid());
                        }
                    } else {
                        if (deductionDTO.getLevelNo() == 2) {
                            dto.setBrandSid(dto.getSid());
                        } else if (deductionDTO.getLevelNo() == 3) {
                            dto.setCompanySid(dto.getSid());
                            dto.setBrandSid(parentIdDto.getBrandSid());
                        } else if (deductionDTO.getLevelNo() == 4) {
                            dto.setItemSid(dto.getSid());
                            dto.setBrandSid(parentIdDto.getBrandSid());
                            dto.setCompanySid(parentIdDto.getCompanySid());
                        }
                    }
                }
                if (i == list.size() - 1) {
                    resultList.add(dto);
                }
            }
            LOGGER.info("Ending getCustomizedResultList with list size " + resultList.size());
            return resultList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }

    /**
     * For getting no of items under a company
     *
     * @param value
     * @return
     */
    public int getItemCount(String value, DeductionDetailsDTO deductionDTO) {
        Object[] obj = value.split("~");
        String query = queryUtils.getItemCount(Integer.valueOf(String.valueOf(obj[3])), Integer.valueOf(String.valueOf(obj[4])), deductionDTO);
        List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return list.size() == 0 || list == null ? 0 : Integer.valueOf(String.valueOf(list.get(0)));
    }

    /**
     * Update values in line level
     *
     * @param deductionDTO
     * @param value
     * @param getData
     */
    public void UpdateTempTable(DeductionDetailsDTO deductionDTO, String value, String getData, TableDTO dto) {
        try {
            Object[] obj = getData.split("~");
            String query = StringUtils.EMPTY;
            if (deductionDTO.getDataView().equals(ConstantsUtils.CUSTOMER)) {
                query = queryUtils.getLineUpdateQueryForCustomer(deductionDTO, value, obj,dto);
            } else if (deductionDTO.getDataView().equals(ConstantsUtils.PRODUCT)) {
                query = queryUtils.getLineUpdateQueryForProduct(deductionDTO, value, obj,dto);
            }
            HelperTableLocalServiceUtil.executeUpdateQuery(query);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * To check a record
     *
     * @param deductionDTO
     * @param ischeck
     * @param check
     * @param dto
     */
    public int checkTempTable(DeductionDetailsDTO deductionDTO, boolean ischeck, String check, TableDTO dto, String filterValue) {
        try {
            String query = StringUtils.EMPTY;
            int count = 0;
            if (deductionDTO.getDataView().equals(ConstantsUtils.CUSTOMER)) {
                query = queryUtils.getCheckQueryForCustomer(deductionDTO, dto, ischeck, false, filterValue);
            } else if (deductionDTO.getDataView().equals(ConstantsUtils.PRODUCT)) {
                query = queryUtils.getCheckQueryForProduct(deductionDTO, dto, ischeck, false, filterValue);
            }
            count = HelperTableLocalServiceUtil.executeUpdateQueryCount(query);
            String qry="SELECT COUNT(DISTINCT \"YEAR\") FROM ST_DEDUCTION_CALENDAR_DETAILS WHERE USER_ID="+deductionDTO.getUserId()+" AND SESSION_ID='"+deductionDTO.getSessionId()+"'";
            List list=HelperTableLocalServiceUtil.executeSelectQuery(qry);
            return count/Integer.valueOf(list.get(0).toString());
        } catch (Exception ex) {
            LOGGER.error(ex);
            return 0;
        }
    }

    /**
     * Check all
     *
     * @param check
     */
    public int checkTempTable(DeductionDetailsDTO dto, boolean check) {
        int count = 0;
        String query = queryUtils.getCheckAllQuery(dto, check);
        count = HelperTableLocalServiceUtil.executeUpdateQueryCount(query);
        return count;
    }

    /**
     * Method for mass updating
     *
     * @param dto
     * @param value
     * @param startPeriod
     * @param endPeriod
     */
    public String massUpdateValue(DeductionDetailsDTO dto, TableDTO tableDTO, String value, String startPeriod, String endPeriod, String filterValue) {
        String query = StringUtils.EMPTY;
        if (dto.getDataView().equals(ConstantsUtils.CUSTOMER)) {
            if (tableDTO.getLevelNo() == (ConstantsUtils.NULL.equals(filterValue) ? 2 : 1)) {
                query = "UPDATE SDC\n"
                        + "SET SDC.DISCOUNT_AMOUNT = " + value + "  \n"
                        + "FROM ST_DEDUCTION_CALENDAR_DETAILS SDC  \n"
                        + "JOIN PERIOD P ON P.PERIOD_SID = SDC.PERIOD_SID\n"
                        + "WHERE CHECK_RECORD = 1 AND SDC.COMPANY_MASTER_SID = " + tableDTO.getCompanySid() + "\n"
                        + "       AND Cast(P.YEAR AS VARCHAR(4))\n"
                        + "           + RIGHT ('0'+Cast(P.MONTH AS VARCHAR), 2) >= '" + startPeriod + "'\n"
                        + "       AND Cast(P.YEAR AS VARCHAR(4))\n"
                        + "           + RIGHT('0'+Cast(P.MONTH AS VARCHAR), 2) <= '" + endPeriod + "' \n"
                        + "        AND  SDC.USER_ID = " + dto.getUserId() + " AND SDC.SESSION_ID = '" + dto.getSessionId() + "';\n";
            } else if (tableDTO.getLevelNo() == (ConstantsUtils.NULL.equals(filterValue) ? 3 : 2)) {
                query = "UPDATE SDC\n"
                        + "SET SDC.DISCOUNT_AMOUNT = " + value + "  \n"
                        + "FROM ITEM_MASTER IM \n"
                        + "JOIN ST_DEDUCTION_CALENDAR_DETAILS SDC ON SDC.ITEM_MASTER_SID = IM.ITEM_MASTER_SID AND IM.BRAND_MASTER_SID = " + tableDTO.getBrandSid() + "\n"
                        + "JOIN PERIOD P ON P.PERIOD_SID = SDC.PERIOD_SID\n"
                        + "WHERE CHECK_RECORD = 1 AND SDC.COMPANY_MASTER_SID = " + tableDTO.getCompanySid() + "\n"
                        + "       AND Cast(P.YEAR AS VARCHAR(4))\n"
                        + "           + RIGHT ('0'+Cast(P.MONTH AS VARCHAR), 2) >= '" + startPeriod + "'\n"
                        + "       AND Cast(P.YEAR AS VARCHAR(4))\n"
                        + "           + RIGHT('0'+Cast(P.MONTH AS VARCHAR), 2) <= '" + endPeriod + "' \n"
                        + "        AND  SDC.USER_ID = " + dto.getUserId() + " AND SDC.SESSION_ID = '" + dto.getSessionId() + "';\n";
            } else if (tableDTO.getLevelNo() == (ConstantsUtils.NULL.equals(filterValue) ? 4 : 3)) {
                query = "UPDATE SDC\n"
                        + "SET SDC.DISCOUNT_AMOUNT = " + value + "  \n"
                        + "FROM ITEM_MASTER IM\n"
                        + "JOIN ST_DEDUCTION_CALENDAR_DETAILS SDC ON SDC.ITEM_MASTER_SID = IM.ITEM_MASTER_SID\n"
                        + "JOIN PERIOD P ON P.PERIOD_SID = SDC.PERIOD_SID\n"
                        + "WHERE CHECK_RECORD = 1 AND SDC.COMPANY_MASTER_SID = " + tableDTO.getCompanySid() + " AND SDC.ITEM_MASTER_SID = " + tableDTO.getItemSid() + "\n"
                        + "       AND Cast(P.YEAR AS VARCHAR(4))\n"
                        + "           + RIGHT ('0'+Cast(P.MONTH AS VARCHAR), 2) >= '" + startPeriod + "'\n"
                        + "       AND Cast(P.YEAR AS VARCHAR(4))\n"
                        + "           + RIGHT('0'+Cast(P.MONTH AS VARCHAR), 2) <= '" + endPeriod + "' \n"
                        + "        AND  SDC.USER_ID = " + dto.getUserId() + " AND SDC.SESSION_ID = '" + dto.getSessionId() + "';\n";
            }

        } else if (dto.getDataView().equals(ConstantsUtils.PRODUCT)) {
            if (tableDTO.getLevelNo() == (ConstantsUtils.NULL.equals(filterValue) ? 2 : 1)) {
                query = "UPDATE SDC\n"
                        + "SET SDC.DISCOUNT_AMOUNT = " + value + "  \n"
                        + "FROM ITEM_MASTER IM\n"
                        + "JOIN ST_DEDUCTION_CALENDAR_DETAILS SDC ON SDC.ITEM_MASTER_SID = IM.ITEM_MASTER_SID AND IM.BRAND_MASTER_SID = " + tableDTO.getBrandSid() + "\n"
                        + "JOIN PERIOD P ON P.PERIOD_SID = SDC.PERIOD_SID\n"
                        + "WHERE CHECK_RECORD = 1 \n"
                        + "       AND Cast(P.YEAR AS VARCHAR(4))\n"
                        + "           + RIGHT ('0'+Cast(P.MONTH AS VARCHAR), 2) >= '" + startPeriod + "'\n"
                        + "       AND Cast(P.YEAR AS VARCHAR(4))\n"
                        + "           + RIGHT('0'+Cast(P.MONTH AS VARCHAR), 2) <= '" + endPeriod + "' \n"
                        + "        AND  SDC.USER_ID = " + dto.getUserId() + " AND SDC.SESSION_ID = '" + dto.getSessionId() + "';\n";
            } else if (tableDTO.getLevelNo() == (ConstantsUtils.NULL.equals(filterValue) ? 3 : 2)) {
                query = "UPDATE SDC\n"
                        + "SET SDC.DISCOUNT_AMOUNT = " + value + "  \n"
                        + "FROM ITEM_MASTER IM\n"
                        + "JOIN ST_DEDUCTION_CALENDAR_DETAILS SDC ON SDC.ITEM_MASTER_SID = IM.ITEM_MASTER_SID AND IM.BRAND_MASTER_SID = " + tableDTO.getBrandSid() + "\n"
                        + "JOIN PERIOD P ON P.PERIOD_SID = SDC.PERIOD_SID\n"
                        + "WHERE CHECK_RECORD = 1 AND SDC.COMPANY_MASTER_SID = " + tableDTO.getCompanySid() + "\n"
                        + "       AND Cast(P.YEAR AS VARCHAR(4))\n"
                        + "           + RIGHT ('0'+Cast(P.MONTH AS VARCHAR), 2) >= '" + startPeriod + "'\n"
                        + "       AND Cast(P.YEAR AS VARCHAR(4))\n"
                        + "           + RIGHT('0'+Cast(P.MONTH AS VARCHAR), 2) <= '" + endPeriod + "' \n"
                        + "        AND  SDC.USER_ID = " + dto.getUserId() + " AND SDC.SESSION_ID = '" + dto.getSessionId() + "';\n";
            } else if (tableDTO.getLevelNo() == (ConstantsUtils.NULL.equals(filterValue) ? 4 : 3)) {
                query = "UPDATE SDC\n"
                        + "SET SDC.DISCOUNT_AMOUNT = " + value + "  \n"
                        + "FROM ITEM_MASTER IM\n"
                        + "JOIN ST_DEDUCTION_CALENDAR_DETAILS SDC ON SDC.ITEM_MASTER_SID = IM.ITEM_MASTER_SID\n"
                        + "JOIN PERIOD P ON P.PERIOD_SID = SDC.PERIOD_SID\n"
                        + "WHERE CHECK_RECORD = 1 AND SDC.COMPANY_MASTER_SID = " + tableDTO.getCompanySid() + " AND SDC.ITEM_MASTER_SID = " + tableDTO.getItemSid() + "\n"
                        + "       AND Cast(P.YEAR AS VARCHAR(4))\n"
                        + "           + RIGHT ('0'+Cast(P.MONTH AS VARCHAR), 2) >= '" + startPeriod + "'\n"
                        + "       AND Cast(P.YEAR AS VARCHAR(4))\n"
                        + "           + RIGHT('0'+Cast(P.MONTH AS VARCHAR), 2) <= '" + endPeriod + "' \n"
                        + "        AND  SDC.USER_ID = " + dto.getUserId() + " AND SDC.SESSION_ID = '" + dto.getSessionId() + "';\n";
            }
        }
        return query;
    }

    /**
     * Bulk update query for mass update.
     * @param query 
     */
    public void bulkUpdate(String query) {
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }

    public void adjust(String selectedOption, List checkList, DeductionDetailsDTO dto, String type, String basis, String variable, String amount, String method) {
        String query = StringUtils.EMPTY;
        if ("All".equals(selectedOption)) {
            query = queryUtils.getAdjustQuery(dto, type, basis, variable, amount, method, StringUtils.EMPTY, selectedOption);
        } else {
            query = queryUtils.getAdjustQuery(dto, type, basis, variable, amount, method, queryUtils.getAdjustPeriodQuery(checkList), selectedOption);
        }
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }

    public List loadFilterDdlb(DeductionDetailsDTO dto) {
        String query = StringUtils.EMPTY;
        if (ConstantsUtils.CUSTOMER.equals(dto.getDataView())) {
            query = CustomSQLUtil.get("customer-ddlb-load")
                    .replace("?UID", dto.getUserId())
                    .replace("?SID", "'" + dto.getSessionId() + "'");
        } else if (ConstantsUtils.PRODUCT.equals(dto.getDataView())) {
            query = CustomSQLUtil.get("product-ddlb")
                    .replace("?UID", dto.getUserId())
                    .replace("?SID", "'" + dto.getSessionId() + "'");
        }
        List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return list;
    }

    public Double getCount(DeductionDetailsDTO detailsDTO, TableDTO dto, String filterValue) {
        String query = StringUtils.EMPTY;
        if (ConstantsUtils.CUSTOMER.equals(detailsDTO.getDataView())) {
            if (dto.getLevelNo() == (ConstantsUtils.NULL.equals(filterValue) ? 2 : 1)) {
                query = "SELECT COUNT(DISTINCT ITEM_MASTER_SID) FROM ST_DEDUCTION_CALENDAR_DETAILS WHERE COMPANY_MASTER_SID = " + dto.getCompanySid() + " AND INBOUND_STATUS <> 'D' AND USER_ID='" + detailsDTO.getUserId() + "' AND SESSION_ID='" + detailsDTO.getSessionId() + "' ;";
            } else if (dto.getLevelNo() == (ConstantsUtils.NULL.equals(filterValue) ? 3 : 2)) {
                query = "SELECT COUNT(DISTINCT SD.ITEM_MASTER_SID) FROM ST_DEDUCTION_CALENDAR_DETAILS SD\n"
                        + "JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID = SD.ITEM_MASTER_SID WHERE IM.BRAND_MASTER_SID = '" + dto.getBrandSid() + "' AND SD.COMPANY_MASTER_SID= '" + dto.getCompanySid() + "' AND SD.INBOUND_STATUS <> 'D' AND SD.USER_ID='" + detailsDTO.getUserId() + "' AND SD.SESSION_ID='" + detailsDTO.getSessionId() + "';";
            } else {
                return 0.0;
            }
        } else if (ConstantsUtils.PRODUCT.equals(detailsDTO.getDataView())) {
            if (dto.getLevelNo() == (ConstantsUtils.NULL.equals(filterValue) ? 2 : 1)) {
                query = "SELECT COUNT(DISTINCT SD.COMPANY_MASTER_SID) FROM ST_DEDUCTION_CALENDAR_DETAILS SD\n"
                        + "JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID = SD.ITEM_MASTER_SID WHERE IM.BRAND_MASTER_SID = '" + dto.getBrandSid() + "' AND SD.INBOUND_STATUS <> 'D' AND SD.USER_ID='" + detailsDTO.getUserId() + "' AND SD.SESSION_ID='" + detailsDTO.getSessionId() + "';";
            } else if (dto.getLevelNo() == (ConstantsUtils.NULL.equals(filterValue) ? 3 : 2)) {
                query = "SELECT COUNT(DISTINCT SD.ITEM_MASTER_SID) FROM ST_DEDUCTION_CALENDAR_DETAILS SD  "
                        + "JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID = SD.ITEM_MASTER_SID WHERE SD.COMPANY_MASTER_SID = " + dto.getCompanySid() + " AND IM.BRAND_MASTER_SID = '" + dto.getBrandSid() + "' AND SD.INBOUND_STATUS <> 'D' AND USER_ID='" + detailsDTO.getUserId() + "' AND SESSION_ID='" + detailsDTO.getSessionId() + "' ;";
            } else {
                return 0.0;
            }
        }
        List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return list.size() == 0 || list == null ? 0.0 : Integer.valueOf(String.valueOf(list.get(0)));
    }
    
     /**
     * 
     * @param dto
     * @param view
     * @param filterVal
     * @return 
     */
    public boolean isCheckedDetails(SessionDTO dto, String view, String filterVal){
        String query= (StringUtils.isBlank(filterVal) 
                ? CustomSQLUtil.get("check-validation") 
                : ("Customer").equalsIgnoreCase(view)? CustomSQLUtil.get("check-filter-comp-validation") : CustomSQLUtil.get("check-filter-item-validation"))
                .replace("?UID", dto.getUserId())
                .replace("?SID", dto.getUiSessionId())
                .replace("?FILTER", filterVal);
        List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return list!=null && "1".equals(String.valueOf(list.get(0)));
    }
}
