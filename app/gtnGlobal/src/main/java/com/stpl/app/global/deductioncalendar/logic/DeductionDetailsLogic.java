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
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.xmlparser.SQLUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.QueryUtil;
import com.stpl.util.dao.orm.CustomSQLUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author sibi
 */
public class DeductionDetailsLogic {

    private static final Logger LOGGER = Logger.getLogger(DeductionDetailsLogic.class);
    private final QueryUtils queryUtils = new QueryUtils();
    private final DecimalFormat DEC_FORMAT = new DecimalFormat("###0.00");

    /**
     * Method for getting start and end period from forecast configuration
     *
     * @param detailsDTO
     * @return
     */
    public DeductionDetailsDTO getForecastConfig(DeductionDetailsDTO detailsDTO) {
        try {
            String query = queryUtils.getForecastConfigQuery();
            List<Object> resultList = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
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
            LOGGER.error(ex);
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
    public int getDeductionDetailsCount(Object getParent, DeductionDetailsDTO deductionDTO, TableDTO tableDTO,SessionDTO sessionDTO) {
        LOGGER.debug("Inside getDeductionDetailsCount");
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
            List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, sessionDTO.getCurrentTableNames()));
            count =  (list == null || list.isEmpty() ) ? 0 : Integer.valueOf(String.valueOf(list.get(0)));
            LOGGER.debug("End of getDeductionDetailsCount");
            return count;
        } catch (NumberFormatException e) {
            LOGGER.error(e);
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
    public List<TableDTO> getDeductionDetailsData(Object getParent, DeductionDetailsDTO deductionDTO, TableDTO tableDTO, int start, int offset,SessionDTO sessionDTO) {
        LOGGER.debug("Inside getDeductionDetailsData ");
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
        List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query,sessionDTO.getCurrentTableNames()));
        List<TableDTO> returnList = getCustomizedResultList(list, deductionDTO, tableDTO);
        LOGGER.debug("Ending getDeductionDetailsData ");
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
            LOGGER.debug("Inside getCustomizedResultList with list size" + list.size());
            List<TableDTO> resultList = new ArrayList<>();
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
                        dto.setParent(deductionDTO.getLevelNo()== NumericConstants.THREE ? 0 : 1);
                        dto.setLevelNo(deductionDTO.getLevelNo());
                        dto.setSid(Integer.valueOf(String.valueOf(obj[0])));
                        dto.setGroup(String.valueOf(obj[1]));
                        dto.setCcpCount(Integer.valueOf(String.valueOf(obj[NumericConstants.THREE])));
                        if (obj[NumericConstants.FOUR] != null && "1".equals(String.valueOf(obj[NumericConstants.FOUR]))) {
                            dto.addBooleanProperties(ConstantsUtils.CHECK, Boolean.TRUE);
                        }
                        dto.setUncheckCount(Integer.valueOf(String.valueOf(obj[NumericConstants.FIVE])));
                        int index = NumericConstants.SIX;
                        String stringProperty;
                        switch (freq) {
                            case ConstantsUtils.ANNUAL:
                                stringProperty = CommonUtil.columnProperty(Integer.valueOf(String.valueOf(obj[NumericConstants.TWO])),
                                        0, deductionDTO);
                                dto.addStringProperties(stringProperty, obj[index] != null || !ConstantsUtils.NULL.equals(String.valueOf(obj[index])) ? DEC_FORMAT.format(Double.valueOf(String.valueOf(obj[index]))) : "0.00");
                                break;
                            case ConstantsUtils.SEMI_ANNUAL:
                                for (int semi = 1; semi <= NumericConstants.TWO; semi++, index++) {
                                    stringProperty = CommonUtil.columnProperty(Integer.valueOf(String.valueOf(obj[NumericConstants.TWO])),
                                            semi, deductionDTO);
                                    dto.addStringProperties(stringProperty, obj[index] != null || !ConstantsUtils.NULL.equals(String.valueOf(obj[index])) ? DEC_FORMAT.format(Double.valueOf(String.valueOf(obj[index]))) : "0.00");
                                }
                                break;
                            case ConstantsUtils.QUARTERLY:
                                for (int quarter = 1; quarter <= NumericConstants.FOUR; quarter++, index++) {
                                    stringProperty = CommonUtil.columnProperty(Integer.valueOf(String.valueOf(obj[NumericConstants.TWO])),
                                            quarter, deductionDTO);
                                    dto.addStringProperties(stringProperty, obj[index] != null || !ConstantsUtils.NULL.equals(String.valueOf(obj[index])) ? DEC_FORMAT.format(Double.valueOf(String.valueOf(obj[index]))) : "0.00");
                                }
                                break;
                            case ConstantsUtils.MONTHLY:
                                for (int month = 1; month <= NumericConstants.TWELVE; month++, index++) {
                                    stringProperty = CommonUtil.columnProperty(Integer.valueOf(String.valueOf(obj[NumericConstants.TWO])),
                                            month, deductionDTO);
                                    dto.addStringProperties(stringProperty, obj[index] != null || !ConstantsUtils.NULL.equals(String.valueOf(obj[index])) ? DEC_FORMAT.format(Double.valueOf(String.valueOf(obj[index]))) : "0.00");
                                }
                                break;
                            default:
                                break;

                        }
                        lastValue = String.valueOf(obj[0]);
                    if (deductionDTO.getDataView().equals(ConstantsUtils.CUSTOMER)) {
                        if (deductionDTO.getLevelNo() == 1) {
                            dto.setCompanySid(dto.getSid());
                        } else if (deductionDTO.getLevelNo() == NumericConstants.TWO) {
                            dto.setBrandSid(dto.getSid());
                            dto.setCompanySid(parentIdDto.getCompanySid());
                        } else if (deductionDTO.getLevelNo() == NumericConstants.THREE) {
                            dto.setItemSid(dto.getSid());
                            dto.setBrandSid(parentIdDto.getBrandSid());
                            dto.setCompanySid(parentIdDto.getCompanySid());
                        }
                    } else {
                        if (deductionDTO.getLevelNo() == 1) {
                            dto.setBrandSid(dto.getSid());
                        } else if (deductionDTO.getLevelNo() == NumericConstants.TWO) {
                            dto.setCompanySid(dto.getSid());
                            dto.setBrandSid(parentIdDto.getBrandSid());
                        } else if (deductionDTO.getLevelNo() == NumericConstants.THREE) {
                            dto.setItemSid(dto.getSid());
                            dto.setBrandSid(parentIdDto.getBrandSid());
                            dto.setCompanySid(parentIdDto.getCompanySid());
                        }
                    }
                } else {
                    if (deductionDTO.getLevelNo() > 1) {
                        dto.setParent(deductionDTO.getLevelNo()== NumericConstants.FOUR ? 0 : 1);
                        dto.setLevelNo(deductionDTO.getLevelNo());
                        dto.setSid(Integer.valueOf(String.valueOf(obj[0])));
                        dto.setGroup(String.valueOf(obj[1]));
                        dto.setCcpCount(Integer.valueOf(String.valueOf(obj[NumericConstants.THREE])));
                        if (obj[NumericConstants.FOUR] != null && "1".equals(String.valueOf(obj[NumericConstants.FOUR]))) {
                            dto.addBooleanProperties(ConstantsUtils.CHECK, Boolean.TRUE);
                        }
                        dto.setUncheckCount(Integer.valueOf(String.valueOf(obj[NumericConstants.FIVE])));
                        int index = NumericConstants.SIX;
                        String stringProperty;
                        switch (freq) {
                            case ConstantsUtils.ANNUAL:
                                stringProperty = CommonUtil.columnProperty(Integer.valueOf(String.valueOf(obj[NumericConstants.TWO])),
                                        0, deductionDTO);
                                dto.addStringProperties(stringProperty, obj[index] != null || !ConstantsUtils.NULL.equals(String.valueOf(obj[index])) ? DEC_FORMAT.format(Double.valueOf(String.valueOf(obj[index]))) : "0.00");
                                break;
                            case ConstantsUtils.SEMI_ANNUAL:
                                for (int semi = 1; semi <= NumericConstants.TWO; semi++, index++) {
                                    stringProperty = CommonUtil.columnProperty(Integer.valueOf(String.valueOf(obj[NumericConstants.TWO])),
                                            semi, deductionDTO);
                                    dto.addStringProperties(stringProperty, obj[index] != null || !ConstantsUtils.NULL.equals(String.valueOf(obj[index])) ? DEC_FORMAT.format(Double.valueOf(String.valueOf(obj[index]))) : "0.00");
                                }
                                break;
                            case ConstantsUtils.QUARTERLY:
                                for (int quarter = 1; quarter <= NumericConstants.FOUR; quarter++, index++) {
                                    stringProperty = CommonUtil.columnProperty(Integer.valueOf(String.valueOf(obj[NumericConstants.TWO])),
                                            quarter, deductionDTO);
                                    dto.addStringProperties(stringProperty, obj[index] != null || !ConstantsUtils.NULL.equals(String.valueOf(obj[index])) ? DEC_FORMAT.format(Double.valueOf(String.valueOf(obj[index]))) : "0.00");
                                }
                                break;
                            case ConstantsUtils.MONTHLY:
                                for (int month = 1; month <= NumericConstants.TWELVE; month++, index++) {
                                    stringProperty = CommonUtil.columnProperty(Integer.valueOf(String.valueOf(obj[NumericConstants.TWO])),
                                            month, deductionDTO);
                                    dto.addStringProperties(stringProperty, obj[index] != null || !ConstantsUtils.NULL.equals(String.valueOf(obj[index])) ? DEC_FORMAT.format(Double.valueOf(String.valueOf(obj[index]))) : "0.00");
                                }
                                break;
                            default: 
                                break;

                        }
                        lastValue = String.valueOf(obj[0]);
                    } else {
                        dto.setParent(1);
                        dto.setLevelNo(deductionDTO.getLevelNo());
                        dto.setGroup(String.valueOf(obj[0]));
                        dto.setCcpCount(Integer.valueOf(String.valueOf(obj[NumericConstants.TWO])));
                        dto.setUncheckCount(Integer.valueOf(String.valueOf(obj[NumericConstants.FOUR])));
                        if (obj[NumericConstants.THREE] != null && "1".equals(String.valueOf(obj[NumericConstants.THREE]))) {
                            dto.addBooleanProperties(ConstantsUtils.CHECK, Boolean.TRUE);
                        }
                        int index = NumericConstants.FIVE;
                        String stringProperty;
                        switch (freq) {
                            case ConstantsUtils.ANNUAL:
                                stringProperty = CommonUtil.columnProperty(Integer.valueOf(String.valueOf(obj[1])),
                                        0, deductionDTO);
                                dto.addStringProperties(stringProperty, obj[index] != null || !ConstantsUtils.NULL.equals(String.valueOf(obj[index])) ? DEC_FORMAT.format(Double.valueOf(String.valueOf(obj[index]))) : "0.00");
                                break;
                            case ConstantsUtils.SEMI_ANNUAL:
                                for (int semi = 1; semi <= NumericConstants.TWO; semi++, index++) {
                                    stringProperty = CommonUtil.columnProperty(Integer.valueOf(String.valueOf(obj[1])),
                                            semi, deductionDTO);
                                    dto.addStringProperties(stringProperty, obj[index] != null || !ConstantsUtils.NULL.equals(String.valueOf(obj[index])) ? DEC_FORMAT.format(Double.valueOf(String.valueOf(obj[index]))) : "0.00");
                                }
                                break;
                            case ConstantsUtils.QUARTERLY:
                                for (int quarter = 1; quarter <= NumericConstants.FOUR; quarter++, index++) {
                                    stringProperty = CommonUtil.columnProperty(Integer.valueOf(String.valueOf(obj[1])),
                                            quarter, deductionDTO);
                                    dto.addStringProperties(stringProperty, obj[index] != null || !ConstantsUtils.NULL.equals(String.valueOf(obj[index])) ? DEC_FORMAT.format(Double.valueOf(String.valueOf(obj[index]))) : "0.00");
                                }
                                break;
                            case ConstantsUtils.MONTHLY:
                                for (int month = 1; month <= NumericConstants.TWELVE; month++, index++) {
                                    stringProperty = CommonUtil.columnProperty(Integer.valueOf(String.valueOf(obj[1])),
                                            month, deductionDTO);
                                    dto.addStringProperties(stringProperty, obj[index] != null || !ConstantsUtils.NULL.equals(String.valueOf(obj[index])) ? DEC_FORMAT.format(Double.valueOf(String.valueOf(obj[index]))) : "0.00");
                                }
                                break;
                            default:
                                break;

                        }
                        
                        
                        
                    }
                    if (deductionDTO.getDataView().equals(ConstantsUtils.CUSTOMER)) {
                        if (deductionDTO.getLevelNo() == NumericConstants.TWO) {
                            dto.setCompanySid(dto.getSid());
                        } else if (deductionDTO.getLevelNo() == NumericConstants.THREE) {
                            dto.setBrandSid(dto.getSid());
                            dto.setCompanySid(parentIdDto.getCompanySid());
                        } else if (deductionDTO.getLevelNo() == NumericConstants.FOUR) {
                            dto.setItemSid(dto.getSid());
                            dto.setBrandSid(parentIdDto.getBrandSid());
                            dto.setCompanySid(parentIdDto.getCompanySid());
                        }
                    } else {
                        if (deductionDTO.getLevelNo() == NumericConstants.TWO) {
                            dto.setBrandSid(dto.getSid());
                        } else if (deductionDTO.getLevelNo() == NumericConstants.THREE) {
                            dto.setCompanySid(dto.getSid());
                            dto.setBrandSid(parentIdDto.getBrandSid());
                        } else if (deductionDTO.getLevelNo() == NumericConstants.FOUR) {
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
            LOGGER.debug("Ending getCustomizedResultList with list size " + resultList.size());
            return resultList;
        } catch (NumberFormatException ex) {
            LOGGER.error(ex);
            return Collections.emptyList();
        }
    }

    /**
     * For getting no of items under a company
     *
     * @param value
     * @param sessionDTO
     * @return
     */
    public int getItemCount(String value,SessionDTO sessionDTO) {
        Object[] obj = value.split("~");
        String query = queryUtils.getItemCount(Integer.valueOf(String.valueOf(obj[NumericConstants.THREE])), Integer.valueOf(String.valueOf(obj[NumericConstants.FOUR])));
        List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query,sessionDTO.getCurrentTableNames()));
        return  (list == null || list.isEmpty()) ? 0 : Integer.valueOf(String.valueOf(list.get(0)));
    }

    /**
     * Update values in line level
     *
     * @param deductionDTO
     * @param value
     * @param getData
     */
    public void updateTempTable(DeductionDetailsDTO deductionDTO, String value, String getData, TableDTO dto,SessionDTO sessionDTO) {
        try {
            Object[] obj = getData.split("~");
            String query = StringUtils.EMPTY;
            if (deductionDTO.getDataView().equals(ConstantsUtils.CUSTOMER)) {
                query = queryUtils.getLineUpdateQueryForCustomer(deductionDTO, value, obj,dto);
            } else if (deductionDTO.getDataView().equals(ConstantsUtils.PRODUCT)) {
                query = queryUtils.getLineUpdateQueryForProduct(deductionDTO, value, obj,dto);
            }
            HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query,sessionDTO.getCurrentTableNames()));
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
    public int checkTempTable(DeductionDetailsDTO deductionDTO, boolean ischeck, TableDTO dto, String filterValue,SessionDTO sessionDTO) {
        try {
            String query = StringUtils.EMPTY;
            int count = 0;
            if (deductionDTO.getDataView().equals(ConstantsUtils.CUSTOMER)) {
                query = queryUtils.getCheckQueryForCustomer(dto, ischeck, filterValue);
            } else if (deductionDTO.getDataView().equals(ConstantsUtils.PRODUCT)) {
                query = queryUtils.getCheckQueryForProduct(dto, ischeck,filterValue);
            }
            count = HelperTableLocalServiceUtil.executeUpdateQueryCount(QueryUtil.replaceTableNames(query,sessionDTO.getCurrentTableNames()));
            String qry="SELECT COUNT(DISTINCT \"YEAR\") FROM ST_DEDUCTION_CALENDAR_DETAILS ";
            List list=HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(qry,sessionDTO.getCurrentTableNames()));
            return count/Integer.valueOf(list.get(0).toString());
        } catch (NumberFormatException ex) {
            LOGGER.error(ex);
            return 0;
        }
    }

    /**
     * Check all
     *
     * @param check
     * @param sessionDTO
     * @return 
     */
    public int checkTempTable(boolean check,SessionDTO sessionDTO) {
        int count = 0;
        String query = queryUtils.getCheckAllQuery(check);
        count = HelperTableLocalServiceUtil.executeUpdateQueryCount(QueryUtil.replaceTableNames(query,sessionDTO.getCurrentTableNames()));
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
            if (tableDTO.getLevelNo() == (ConstantsUtils.NULL.equals(filterValue) ? NumericConstants.TWO : 1)) {
                query = ConstantsUtils.UPDATE_SDC
                        + ConstantsUtils.SET_DISCOUNT_AMOUNT + value + "  \n"
                        + "FROM ST_DEDUCTION_CALENDAR_DETAILS SDC  \n"
                        + ConstantsUtils.JOIN_PERIOD_ON_PERIOD_SID
                        + ConstantsUtils.QUERY_WHERE_COND + tableDTO.getCompanySid() + "\n"
                        + ConstantsUtils.CAST_YEAR_AS_VARCHAR
                        + ConstantsUtils.RIGHT_CAST_MONTH_AS_VARCHAR_GREATER_THAN + startPeriod + "'\n"
                        + ConstantsUtils.CAST_YEAR_AS_VARCHAR
                        + ConstantsUtils.RIGHT_CAST_MONTH_AS_VARCHAR + endPeriod + "';";
            } else if (tableDTO.getLevelNo() == (ConstantsUtils.NULL.equals(filterValue) ? NumericConstants.THREE : NumericConstants.TWO)) {
                query = ConstantsUtils.UPDATE_SDC
                        + ConstantsUtils.SET_DISCOUNT_AMOUNT + value + "  \n"
                        + "FROM ITEM_MASTER IM \n"
                        + ConstantsUtils.JOIN_ST_CALENDAR_DETAILS_ON_ITEM_MASTER_SID+ tableDTO.getBrandSid() + "\n"
                        + ConstantsUtils.JOIN_PERIOD_ON_PERIOD_SID
                        + ConstantsUtils.QUERY_WHERE_COND + tableDTO.getCompanySid() + "\n"
                        + ConstantsUtils.CAST_YEAR_AS_VARCHAR
                        +  ConstantsUtils.RIGHT_CAST_MONTH_AS_VARCHAR_GREATER_THAN  + startPeriod + "'\n"
                        + ConstantsUtils.CAST_YEAR_AS_VARCHAR
                        + ConstantsUtils.RIGHT_CAST_MONTH_AS_VARCHAR + endPeriod + "';";
            } else if (tableDTO.getLevelNo() == (ConstantsUtils.NULL.equals(filterValue) ? NumericConstants.FOUR : NumericConstants.THREE)) {
                query = ConstantsUtils.UPDATE_SDC
                        + ConstantsUtils.SET_DISCOUNT_AMOUNT + value + "  \n"
                        + ConstantsUtils.FROM_ITEM_MASTER
                        + "JOIN ST_DEDUCTION_CALENDAR_DETAILS SDC ON SDC.ITEM_MASTER_SID = IM.ITEM_MASTER_SID\n"
                        + ConstantsUtils.JOIN_PERIOD_ON_PERIOD_SID
                        + ConstantsUtils.QUERY_WHERE_COND + tableDTO.getCompanySid() + " AND SDC.ITEM_MASTER_SID = " + tableDTO.getItemSid() + "\n"
                        + ConstantsUtils.CAST_YEAR_AS_VARCHAR
                        +  ConstantsUtils.RIGHT_CAST_MONTH_AS_VARCHAR_GREATER_THAN + startPeriod + "'\n"
                        + ConstantsUtils.CAST_YEAR_AS_VARCHAR
                        + ConstantsUtils.RIGHT_CAST_MONTH_AS_VARCHAR + endPeriod + "';";
            }

        } else if (dto.getDataView().equals(ConstantsUtils.PRODUCT)) {
            if (tableDTO.getLevelNo() == (ConstantsUtils.NULL.equals(filterValue) ? NumericConstants.TWO : 1)) {
                query = ConstantsUtils.UPDATE_SDC
                        + ConstantsUtils.SET_DISCOUNT_AMOUNT + value + "  \n"
                        +  ConstantsUtils.FROM_ITEM_MASTER
                        + ConstantsUtils.JOIN_ST_CALENDAR_DETAILS_ON_ITEM_MASTER_SID + tableDTO.getBrandSid() + "\n"
                        + ConstantsUtils.JOIN_PERIOD_ON_PERIOD_SID
                        + "WHERE CHECK_RECORD = 1 \n"
                        + ConstantsUtils.CAST_YEAR_AS_VARCHAR
                        +  ConstantsUtils.RIGHT_CAST_MONTH_AS_VARCHAR_GREATER_THAN  + startPeriod + "'\n"
                        + ConstantsUtils.CAST_YEAR_AS_VARCHAR
                        + ConstantsUtils.RIGHT_CAST_MONTH_AS_VARCHAR + endPeriod + "';";
            } else if (tableDTO.getLevelNo() == (ConstantsUtils.NULL.equals(filterValue) ? NumericConstants.THREE : NumericConstants.TWO)) {
                query = ConstantsUtils.UPDATE_SDC
                        + ConstantsUtils.SET_DISCOUNT_AMOUNT + value + "  \n"
                        + ConstantsUtils.FROM_ITEM_MASTER
                        + ConstantsUtils.JOIN_ST_CALENDAR_DETAILS_ON_ITEM_MASTER_SID+ tableDTO.getBrandSid() + "\n"
                        + ConstantsUtils.JOIN_PERIOD_ON_PERIOD_SID
                        + ConstantsUtils.QUERY_WHERE_COND + tableDTO.getCompanySid() + "\n"
                        + ConstantsUtils.CAST_YEAR_AS_VARCHAR
                        +  ConstantsUtils.RIGHT_CAST_MONTH_AS_VARCHAR_GREATER_THAN  + startPeriod + "'\n"
                        + ConstantsUtils.CAST_YEAR_AS_VARCHAR
                        + ConstantsUtils.RIGHT_CAST_MONTH_AS_VARCHAR+ endPeriod + "';";
            } else if (tableDTO.getLevelNo() == (ConstantsUtils.NULL.equals(filterValue) ? NumericConstants.FOUR : NumericConstants.THREE)) {
                query = ConstantsUtils.UPDATE_SDC
                        + ConstantsUtils.SET_DISCOUNT_AMOUNT+ value + "  \n"
                        +  ConstantsUtils.FROM_ITEM_MASTER
                        + "JOIN ST_DEDUCTION_CALENDAR_DETAILS SDC ON SDC.ITEM_MASTER_SID = IM.ITEM_MASTER_SID\n"
                        + ConstantsUtils.JOIN_PERIOD_ON_PERIOD_SID
                        + ConstantsUtils.QUERY_WHERE_COND + tableDTO.getCompanySid() + " AND SDC.ITEM_MASTER_SID = " + tableDTO.getItemSid() + "\n"
                        + ConstantsUtils.CAST_YEAR_AS_VARCHAR
                        +  ConstantsUtils.RIGHT_CAST_MONTH_AS_VARCHAR_GREATER_THAN  + startPeriod + "'\n"
                        + ConstantsUtils.CAST_YEAR_AS_VARCHAR
                        + ConstantsUtils.RIGHT_CAST_MONTH_AS_VARCHAR + endPeriod + "';";
            }
        }
        return query;
    }

    /**
     * Bulk update query for mass update.
     * @param query 
     */
    public void bulkUpdate(String query,SessionDTO sessionDTO) {
        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query, sessionDTO.getCurrentTableNames()));
    }

    public void adjust(String selectedOption, List checkList, String type, String basis, String variable, String amount, String method,SessionDTO sessionDTO) {
        String query;
        if ("All".equals(selectedOption)) {
            query = queryUtils.getAdjustQuery(type, basis, variable, amount, method, StringUtils.EMPTY, selectedOption);
        } else {
            query = queryUtils.getAdjustQuery(type, basis, variable, amount, method, queryUtils.getAdjustPeriodQuery(checkList), selectedOption);
        }
        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query, sessionDTO.getCurrentTableNames()));
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
        return HelperTableLocalServiceUtil.executeSelectQuery(query);
    }

    public Double getCount(DeductionDetailsDTO detailsDTO, TableDTO dto, String filterValue,SessionDTO sessionDTO) {
        String query = StringUtils.EMPTY;
        if (ConstantsUtils.CUSTOMER.equals(detailsDTO.getDataView())) {
            if (dto.getLevelNo() == (ConstantsUtils.NULL.equals(filterValue) ? NumericConstants.TWO : 1)) {
                query = "SELECT COUNT(DISTINCT ITEM_MASTER_SID) FROM ST_DEDUCTION_CALENDAR_DETAILS WHERE COMPANY_MASTER_SID = " + dto.getCompanySid() + " AND INBOUND_STATUS <> 'D' ;";
            } else if (dto.getLevelNo() == (ConstantsUtils.NULL.equals(filterValue) ? NumericConstants.THREE : NumericConstants.TWO)) {
                query = "SELECT COUNT(DISTINCT SD.ITEM_MASTER_SID) FROM ST_DEDUCTION_CALENDAR_DETAILS SD\n"
                        + "JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID = SD.ITEM_MASTER_SID WHERE IM.BRAND_MASTER_SID = '" + dto.getBrandSid() + "' AND SD.COMPANY_MASTER_SID= '" + dto.getCompanySid() + "' AND SD.INBOUND_STATUS <> 'D';";
            } else {
                return 0.0;
            }
        } else if (ConstantsUtils.PRODUCT.equals(detailsDTO.getDataView())) {
            if (dto.getLevelNo() == (ConstantsUtils.NULL.equals(filterValue) ? NumericConstants.TWO : 1)) {
                query = "SELECT COUNT(DISTINCT SD.COMPANY_MASTER_SID) FROM ST_DEDUCTION_CALENDAR_DETAILS SD\n"
                        + "JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID = SD.ITEM_MASTER_SID WHERE IM.BRAND_MASTER_SID = '" + dto.getBrandSid() + "' AND SD.INBOUND_STATUS <> 'D' ;";
            } else if (dto.getLevelNo() == (ConstantsUtils.NULL.equals(filterValue) ? NumericConstants.THREE : NumericConstants.TWO)) {
                query = "SELECT COUNT(DISTINCT SD.ITEM_MASTER_SID) FROM ST_DEDUCTION_CALENDAR_DETAILS SD  "
                        + "JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID = SD.ITEM_MASTER_SID WHERE SD.COMPANY_MASTER_SID = " + dto.getCompanySid() + " AND IM.BRAND_MASTER_SID = '" + dto.getBrandSid() + "' AND SD.INBOUND_STATUS <> 'D';";
            } else {
                return 0.0;
            }
        }
        List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, sessionDTO.getCurrentTableNames()));
        return (list == null || list.isEmpty()) ? 0.0 : Integer.valueOf(String.valueOf(list.get(0)));
    }
    
     /**
     * 
     * @param dto
     * @param view
     * @param filterVal
     * @return 
     */
    public boolean isCheckedDetails(SessionDTO dto, String view, String filterVal){
        String query = (StringUtils.isBlank(filterVal)? SQLUtil.getQuery("checkValidation") 
                : ("Customer").equalsIgnoreCase(view)? SQLUtil.getQuery("checkFilterCompValidation") 
                : SQLUtil.getQuery("checkFilterItemValidation")).replace("?FILTER", filterVal);
        query =query.replace("?FILTER", filterVal);
        List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query,dto.getCurrentTableNames()));
        return list!=null && "1".equals(String.valueOf(list.get(0)));
    }
}
