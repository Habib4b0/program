/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.gtnbalancereport.logic.tableLogic;

import com.stpl.app.accountclose.common.CommonLogic;
import static com.stpl.app.accountclose.common.CommonLogic.ddlbDefaultValue;
import com.stpl.app.accountclose.dao.BaseRateDAO;
import com.stpl.app.accountclose.dao.impl.BaseRateDAOImpl;
import com.stpl.app.accountclose.gtnbalancereport.dto.BalanceReportDTO;
import com.stpl.app.accountclose.gtnbalancereport.dto.DataSelectionDTO;
import com.stpl.app.accountclose.gtnbalancereport.dto.LiabilitySummaryDto;
import com.stpl.app.accountclose.gtnbalancereport.utils.BRUtils;
import static com.stpl.app.accountclose.gtnbalancereport.utils.Constants.DateFormatConstants.DEFAULT_JAVA_DATE_FORMAT;
import static com.stpl.app.accountclose.gtnbalancereport.utils.Constants.DateFormatConstants.DEFAULT_SQL_DATE_FORMAT;
import com.stpl.app.accountclose.gtnbalancereport.utils.DataSelectionUtil;
import com.stpl.app.accountclose.gtnbalancereport.utils.HeaderUtils;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.utils.Constants;
import com.stpl.app.accountclose.utils.Constants.BasReportVariables;
import com.stpl.app.accountclose.utils.Converters;
import com.stpl.app.accountclose.utils.QueryUtils;
import com.stpl.ifs.util.HelperDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author santanukumar
 */
public class BalanceReportLogic {

    public static final Logger LOGGER = Logger.getLogger(BalanceReportLogic.class);
    BaseRateDAO dao = new BaseRateDAOImpl();
    private static final DecimalFormat CUR_ZERO = new DecimalFormat("$#,##0.00");
    private static final DecimalFormat PER_ONE = new DecimalFormat("#,##0.00%");

    /**
     *
     * @param parentId
     * @param session
     * @param dto
     * @param b
     * @return
     */
    public int getBalanceReportCount(Object parentId, SessionDTO session, BalanceReportDTO dto, List<String> selectedVariables, boolean b) {

        LOGGER.info("Entering getBalanceReportCount");
        List<Object> results = new ArrayList<Object>();
        List<Object> input = new ArrayList<Object>();
        String reportQuery = StringUtils.EMPTY;
        String query = StringUtils.EMPTY;
        List<String> append = new ArrayList<String>();
        String frequencyQuery = StringUtils.EMPTY;
        List<String> selectedColumns = new ArrayList<String>();
        String periodQuery = StringUtils.EMPTY;
        if (parentId instanceof BalanceReportDTO) {
            BalanceReportDTO parentDto = (BalanceReportDTO) parentId;
            dto.setLevelNo(parentDto.getLevelNo() + 1);
            dto.setComapnySid(parentDto.getComapnySid());
            dto.setContractSid(parentDto.getContractSid());
            dto.setTherapeuticSid(parentDto.getTherapeuticSid());
            dto.setBrandSid(parentDto.getBrandSid());
            dto.setItemSid(parentDto.getItemSid());
        } else {
            dto.setLevelNo(1);
            dto.setComapnySid(StringUtils.EMPTY);
            dto.setContractSid(StringUtils.EMPTY);
            dto.setTherapeuticSid(StringUtils.EMPTY);
            dto.setBrandSid(StringUtils.EMPTY);
            dto.setItemSid(StringUtils.EMPTY);
        }
        DataSelectionDTO dsdto = session.getDataSelectionDTO();
        try {
            append = getQueryOnView(dto);
            Map<String, String> inputMap = new HashMap<String, String>();
            frequencyQuery = getPeriodQuery(dto);
            selectedColumns = getColumnsToBeFetched(selectedVariables);
            periodQuery = getDateRangeQuery(dsdto, dto);
            inputMap.put("?QU?", append.get(0));
            inputMap.put("?QURANK?", append.get(1));
            inputMap.put("?FREQUENCY?", frequencyQuery);

            inputMap.put("?SELECTEDCOLUMN?", selectedColumns.get(0));
            inputMap.put("?DENSECOLUMN?", selectedColumns.get(1));
            inputMap.put("?ACC_CLOSURE_MASTER_SID?", String.valueOf(session.getAcctCloserMasterId()));
            inputMap.put("?USER_ID?", session.getUserId());
            inputMap.put("?SESSION_ID?", session.getSessionId());
            inputMap.put("?PERIOD_DATE?", periodQuery);
            inputMap.put("?CT?", dsdto.getMarketType());
            inputMap.put("?UDC?", dsdto.getDeductionType());
            inputMap.put("?RPT?", dsdto.getDeductionSubType());
            inputMap.put("?PGSID?", "%");
            inputMap.put("?PGSID?", "%");
            inputMap.put("?CMSID?", "%");
            inputMap.put("?CSID?", "%");
            inputMap.put("?THID?", "%");
            inputMap.put("?BMSID?", "%");
            inputMap.put("?ISID?", "%");

            if (StringUtils.isNotBlank(dto.getComapnySid())) {
                inputMap.put("?CMSID?", dto.getComapnySid());
            }
            if (StringUtils.isNotBlank(dto.getContractSid())) {
                inputMap.put("?CSID?", dto.getContractSid());
            }
            if (StringUtils.isNotBlank(dto.getTherapeuticSid())) {
                inputMap.put("?THID?", dto.getTherapeuticSid());
            }
            if (StringUtils.isNotBlank(dto.getBrandSid())) {
                inputMap.put("?BMSID?", dto.getBrandSid());
            }
            if (StringUtils.isNotBlank(dto.getItemSid())) {
                inputMap.put("?ISID?", dto.getItemSid());
            }
            input.add(String.valueOf(session.getAcctCloserMasterId()));
            input.add(session.getUserId());
            input.add(session.getSessionId());
            input.add(periodQuery);
            input.add(StringUtils.isNotBlank(dto.getItemSid()) ? dto.getItemSid() : "%");
            input.add(StringUtils.isNotBlank(dto.getContractSid()) ? dto.getContractSid() : "%");
            input.add(StringUtils.isNotBlank(dto.getComapnySid()) ? dto.getComapnySid() : "%");
            input.add(StringUtils.isNotBlank(dto.getBrandSid()) ? dto.getBrandSid() : "%");
            input.add(StringUtils.isNotBlank(dto.getTherapeuticSid()) ? dto.getTherapeuticSid() : "%");
            input.add(append.get(0));
            results = (List) BRUtils.getExecuteQuery(inputMap, dto.getQuery() + "Count");
        } catch (Exception e) {
            LOGGER.error(e);
        }
        Object obj = null;
        if (!results.isEmpty()) {
            obj = results.get(0);
        }
        return obj == null ? 0 : (Integer) obj;
    }

    /**
     *
     * @param lastParent
     * @param start
     * @param last
     * @param sessionDTO
     * @param dto
     * @param selectedVariables
     * @param b
     * @return
     */
    public List<BalanceReportDTO> getBalanceReportResults(Object lastParent, int start, int last, SessionDTO sessionDTO, BalanceReportDTO dto, List<String> selectedVariables, boolean b, List<String> doubleHeaderList, boolean excel) {
        List<BalanceReportDTO> resultList = new ArrayList<BalanceReportDTO>();
        String reportQuery = StringUtils.EMPTY;
        List<Object> input = new ArrayList<Object>();
        int offset = start + last;
        List<Object> results = new ArrayList<Object>();
        String query = StringUtils.EMPTY;
        List<String> append = new ArrayList<String>();
        String frequencyQuery = StringUtils.EMPTY;
        List<String> selectedColumns = new ArrayList<String>();
        String periodQuery = StringUtils.EMPTY;
        if (lastParent instanceof BalanceReportDTO) {
            BalanceReportDTO parentDto = (BalanceReportDTO) lastParent;
            dto.setLevelNo(parentDto.getLevelNo() + 1);
            dto.setComapnySid(parentDto.getComapnySid());
            dto.setContractSid(parentDto.getContractSid());
            dto.setTherapeuticSid(parentDto.getTherapeuticSid());
            dto.setBrandSid(parentDto.getBrandSid());
            dto.setItemSid(parentDto.getItemSid());
        } else {
            dto.setLevelNo(1);
            dto.setComapnySid(StringUtils.EMPTY);
            dto.setContractSid(StringUtils.EMPTY);
            dto.setTherapeuticSid(StringUtils.EMPTY);
            dto.setBrandSid(StringUtils.EMPTY);
            dto.setItemSid(StringUtils.EMPTY);
        }
        DataSelectionDTO dsdto = sessionDTO.getDataSelectionDTO();
        try {
            append = getQueryOnView(dto);
            frequencyQuery = getPeriodQuery(dto);
            selectedColumns = getColumnsToBeFetched(selectedVariables);
            periodQuery = getDateRangeQuery(dsdto, dto);
            Map<String, String> inputMap = new HashMap<String, String>();
            inputMap.put("?QU?", append.get(0));
            inputMap.put("?QURANK?", append.get(1));
            inputMap.put("?FREQUENCY?", frequencyQuery);
            inputMap.put("?RSLTFREQUENCY?", frequencyQuery.replace("P", "RSLT"));
            inputMap.put("?SELECTEDCOLUMN?", selectedColumns.get(0));
            inputMap.put("?DENSECOLUMN?", selectedColumns.get(1));
            inputMap.put("?ACC_CLOSURE_MASTER_SID?", String.valueOf(sessionDTO.getAcctCloserMasterId()));
            inputMap.put("?USER_ID?", sessionDTO.getUserId());
            inputMap.put("?SESSION_ID?", sessionDTO.getSessionId());
            inputMap.put("?PERIOD_DATE?", periodQuery);
            inputMap.put("?CT?", dsdto.getMarketType());
            inputMap.put("?UDC?", dsdto.getDeductionType());
            inputMap.put("?RPT?", dsdto.getDeductionSubType());
            inputMap.put("?PGSID?", "%");
            inputMap.put("?PGSID?", "%");
            inputMap.put("?CMSID?", "%");
            inputMap.put("?CSID?", "%");
            inputMap.put("?THID?", "%");
            inputMap.put("?BMSID?", "%");
            inputMap.put("?ISID?", "%");
            inputMap.put("?WHERE?", " WHERE RSLT.RANK >" + start + " AND RSLT.RANK <=" + offset);

            if (StringUtils.isNotBlank(dto.getComapnySid())) {
                inputMap.put("?CMSID?", dto.getComapnySid());
            }
            if (StringUtils.isNotBlank(dto.getContractSid())) {
                inputMap.put("?CSID?", dto.getContractSid());
            }
            if (StringUtils.isNotBlank(dto.getTherapeuticSid())) {
                inputMap.put("?THID?", dto.getTherapeuticSid());
            }
            if (StringUtils.isNotBlank(dto.getBrandSid())) {
                inputMap.put("?BMSID?", dto.getBrandSid());
            }
            if (StringUtils.isNotBlank(dto.getItemSid())) {
                inputMap.put("?ISID?", dto.getItemSid());
            }
            LOGGER.info("dto.getComapnySid()\n" + dto.getComapnySid());
            LOGGER.info("dto.getContractSid()\n" + dto.getContractSid());
            LOGGER.info("dto.getTherapeuticSid()\n" + dto.getTherapeuticSid());
            LOGGER.info("dto.getBrandSid()\n" + dto.getBrandSid());
            LOGGER.info("dto.getItemSid()\n" + dto.getItemSid());

            input.add(append.get(1));
            input.add(frequencyQuery.replace("P", "RSLT"));
            input.add(selectedColumns.get(1));
            input.add(frequencyQuery);
            input.add(selectedColumns.get(0));
            input.add(append.get(0));
            input.add(String.valueOf(sessionDTO.getAcctCloserMasterId()));
            input.add(sessionDTO.getUserId());
            input.add(sessionDTO.getSessionId());
            input.add(periodQuery);
            input.add(StringUtils.isNotBlank(dto.getItemSid()) ? dto.getItemSid() : "%");
            input.add(StringUtils.isNotBlank(dto.getContractSid()) ? dto.getContractSid() : "%");
            input.add(StringUtils.isNotBlank(dto.getComapnySid()) ? dto.getComapnySid() : "%");
            input.add(StringUtils.isNotBlank(dto.getBrandSid()) ? dto.getBrandSid() : "%");
            input.add(StringUtils.isNotBlank(dto.getTherapeuticSid()) ? dto.getTherapeuticSid() : "%");
            input.add(append.get(0));
            input.add(frequencyQuery);
            input.add(" WHERE RSLT.RANK >" + start + " AND RSLT.RANK <=" + offset);
            input.add(append.get(1));
            input.add(frequencyQuery.replace("P", "RSLT"));

            results = (List<Object>) QueryUtils.executeSelectQuery(inputMap, dto.getQuery());
            resultList = getBalanceReportDto(results, dto, selectedVariables, doubleHeaderList, excel);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return resultList;
    }

    /**
     *
     * @param results
     * @param reportDTO
     * @return
     */
    private List<BalanceReportDTO> getBalanceReportDto(List results, BalanceReportDTO reportDTO, List<String> selectedVariables, List<String> doubleHeaderlist, boolean excel) {
        List<BalanceReportDTO> resultList = new ArrayList<BalanceReportDTO>();
        Map<String, String> map = loadVariableColumnMap();
        String timeperiodFrom = reportDTO.getTimePeriodFrom();
        String timeperiodTo = reportDTO.getTimePeriodTo();

        int startPeriod = 0;
        startPeriod = doubleHeaderlist.indexOf(timeperiodFrom);
        if (!results.isEmpty()) {
            int size = results.size();
            String oldLevelValue = "oldValue";
            String newLevelValue = "newValue";
            BalanceReportDTO brdto = new BalanceReportDTO();
            for (int i = 0; i < size; i++) {
                final Object[] obj = (Object[]) results.get(i);

                oldLevelValue = newLevelValue;
                newLevelValue = StringUtils.EMPTY + obj[1];
                if (!oldLevelValue.equals(newLevelValue)) {
                    brdto = new BalanceReportDTO();
                }

                int year = Integer.parseInt(String.valueOf(obj[3]));
                int period = 0;
                int j = 0;
                if (com.stpl.app.accountclose.utils.Constants.FrequencyConstants.ANNUALLY.getConstant().equals(reportDTO.getFrequency())) {
                    period = Integer.parseInt(String.valueOf(obj[3]));
                    j = 3;
                } else {
                    period = Integer.parseInt(String.valueOf(obj[4]));
                    j = 4;
                }

                List<String> common = HeaderUtils.getCommonColumnHeader(reportDTO, year, period);
                String commonColumn = common.get(1);
                int pIndex = doubleHeaderlist.indexOf(commonColumn);
                pIndex = pIndex - startPeriod;
                int varIndex = 0;
                int varSize = selectedVariables.size();
                int index = 0;
                for (String selectedVariable : selectedVariables) {
                    if (excel) {
                        index = (pIndex * varSize) + varIndex + 2 - 1;
                    } else {
                        index = (pIndex * varSize) + varIndex + 2;
                    }
                    if (selectedVariable.contains("%") || (selectedVariable.contains("Rate") && !selectedVariable.contains("Base"))) {
                        brdto.addStringProperties(selectedVariable + commonColumn + "." + index, getFormattedValue(PER_ONE, Converters.convertNullToEmpty(obj[++j])));
                    } else {
                        brdto.addStringProperties(selectedVariable + commonColumn + "." + index, getFormattedValue(CUR_ZERO, Converters.convertNullToEmpty(obj[++j])));
                    }
                    varIndex++;
                }

                setSystemIdsBasedOnView(reportDTO, brdto, obj);
                if (!oldLevelValue.equals(newLevelValue)) {
                    if (excel) {
                        brdto.addStringProperties("customer.0", obj[1].toString());
                    } else {
                        brdto.addBooleanProperties("check.0", (Boolean) obj[0]);
                        brdto.addStringProperties("customer.1", obj[1].toString());
                    }
                    brdto.setLevelNo(reportDTO.getLevelNo());
                    resultList.add(brdto);
                }

            }
        }
        return resultList;
    }

    /**
     *
     * @param dto
     * @return
     */
    private List<String> getQueryOnView(BalanceReportDTO dto) {
        List<String> finalQuery = new ArrayList<String>();
        String append = StringUtils.EMPTY;
        String denseQuery = StringUtils.EMPTY;
        if (Constants.IndicatorConstants.CUSTOMER.getConstant().equals(dto.getViewType())) {
            if (dto.getLevelNo() == 1) {
                append = "CM.COMPANY_NAME,CM.COMPANY_MASTER_SID ";
                denseQuery = "RSLT.COMPANY_NAME,RSLT.COMPANY_MASTER_SID ";
            } else if (dto.getLevelNo() == 2) {
                append = "CN.CONTRACT_NAME,CN.CONTRACT_MASTER_SID ";
                denseQuery = "RSLT.CONTRACT_NAME,RSLT.CONTRACT_MASTER_SID ";
            } else if (dto.getLevelNo() == 3) {
                append = "HT.DESCRIPTION,HT.HELPER_TABLE_SID ";
                denseQuery = "RSLT.DESCRIPTION,RSLT.HELPER_TABLE_SID ";
            } else if (dto.getLevelNo() == 4) {
                append = "BM.BRAND_NAME,BM.BRAND_MASTER_SID ";
                denseQuery = "RSLT.BRAND_NAME,RSLT.BRAND_MASTER_SID ";
            } else if (dto.getLevelNo() == 5) {
                append = "IM.ITEM_ID,IM.ITEM_MASTER_SID ";
                denseQuery = "RSLT.ITEM_ID,RSLT.ITEM_MASTER_SID ";
            }
        } else if (Constants.IndicatorConstants.BRAND.getConstant().equals(dto.getViewType())) {
            if (dto.getLevelNo() == 1) {
                append = "HT.DESCRIPTION,HT.HELPER_TABLE_SID ";
                denseQuery = "RSLT.DESCRIPTION,RSLT.HELPER_TABLE_SID ";
            } else if (dto.getLevelNo() == 2) {
                append = "BM.BRAND_NAME,BM.BRAND_MASTER_SID ";
                denseQuery = "RSLT.BRAND_NAME,RSLT.BRAND_MASTER_SID ";
            } else if (dto.getLevelNo() == 3) {
                append = "CM.COMPANY_NAME,CM.COMPANY_MASTER_SID ";
                denseQuery = "RSLT.COMPANY_NAME,RSLT.COMPANY_MASTER_SID ";
            } else if (dto.getLevelNo() == 4) {
                append = "CN.CONTRACT_NAME,CN.CONTRACT_MASTER_SID ";
                denseQuery = "RSLT.CONTRACT_NAME,RSLT.CONTRACT_MASTER_SID ";
            } else if (dto.getLevelNo() == 5) {
                append = "IM.ITEM_ID,IM.ITEM_MASTER_SID ";
                denseQuery = "RSLT.ITEM_ID,RSLT.ITEM_MASTER_SID ";
            }
        } else if (Constants.IndicatorConstants.ITEM.getConstant().equals(dto.getViewType())) {
            if (dto.getLevelNo() == 1) {
                append = "HT.DESCRIPTION,HT.HELPER_TABLE_SID ";
                denseQuery = "RSLT.DESCRIPTION,RSLT.HELPER_TABLE_SID ";
            } else if (dto.getLevelNo() == 2) {
                append = "IM.ITEM_ID,IM.ITEM_MASTER_SID ";
                denseQuery = "RSLT.ITEM_ID,RSLT.ITEM_MASTER_SID ";
            } else if (dto.getLevelNo() == 3) {
                append = "CM.COMPANY_NAME,CM.COMPANY_MASTER_SID ";
                denseQuery = "RSLT.COMPANY_NAME,RSLT.COMPANY_MASTER_SID ";
            } else if (dto.getLevelNo() == 4) {
                append = "CN.CONTRACT_NAME,CN.CONTRACT_MASTER_SID ";
                denseQuery = "RSLT.CONTRACT_NAME,RSLT.CONTRACT_MASTER_SID ";
            }
        }
        finalQuery.add(append);
        finalQuery.add(denseQuery);
        return finalQuery;
    }

    /**
     *
     * @param reportDTO
     * @param brdto
     * @param obj
     * @return
     */
    private BalanceReportDTO setSystemIdsBasedOnView(BalanceReportDTO reportDTO, BalanceReportDTO brdto, Object[] obj) {
        brdto.setParent(1);
        if (Constants.IndicatorConstants.CUSTOMER.getConstant().equals(reportDTO.getViewType())) {

            if (reportDTO.getLevelNo() == 1) {
                brdto.setComapnySid(Converters.convertNullToEmpty(obj[2]));
            }
            if (reportDTO.getLevelNo() == 2) {
                brdto.setComapnySid(reportDTO.getComapnySid());
                brdto.setContractSid(Converters.convertNullToEmpty(obj[2]));
            }
            if (reportDTO.getLevelNo() == 3) {
                brdto.setComapnySid(reportDTO.getComapnySid());
                brdto.setContractSid(reportDTO.getContractSid());
                brdto.setTherapeuticSid(Converters.convertNullToEmpty(obj[2]));
            }
            if (reportDTO.getLevelNo() == 4) {
                brdto.setComapnySid(reportDTO.getComapnySid());
                brdto.setContractSid(reportDTO.getContractSid());
                brdto.setTherapeuticSid(reportDTO.getTherapeuticSid());
                brdto.setBrandSid(Converters.convertNullToEmpty(obj[2]));
            }
            if (reportDTO.getLevelNo() == 5) {
                brdto.setComapnySid(reportDTO.getComapnySid());
                brdto.setContractSid(reportDTO.getContractSid());
                brdto.setTherapeuticSid(reportDTO.getTherapeuticSid());
                brdto.setBrandSid(reportDTO.getBrandSid());
                brdto.setItemSid(Converters.convertNullToEmpty(obj[2]));
                brdto.setParent(0);
            }

        } else if (Constants.IndicatorConstants.BRAND.getConstant().equals(reportDTO.getViewType())) {
            if (reportDTO.getLevelNo() == 1) {
                brdto.setTherapeuticSid(Converters.convertNullToEmpty(obj[2]));
            }
            if (reportDTO.getLevelNo() == 2) {
                brdto.setTherapeuticSid(reportDTO.getTherapeuticSid());
                brdto.setBrandSid(Converters.convertNullToEmpty(obj[2]));
            }
            if (reportDTO.getLevelNo() == 3) {
                brdto.setTherapeuticSid(reportDTO.getTherapeuticSid());
                brdto.setBrandSid(reportDTO.getBrandSid());
                brdto.setComapnySid(Converters.convertNullToEmpty(obj[2]));
            }
            if (reportDTO.getLevelNo() == 4) {
                brdto.setTherapeuticSid(reportDTO.getTherapeuticSid());
                brdto.setBrandSid(reportDTO.getBrandSid());
                brdto.setComapnySid(reportDTO.getComapnySid());
                brdto.setContractSid(Converters.convertNullToEmpty(obj[2]));
            }
            if (reportDTO.getLevelNo() == 5) {
                brdto.setTherapeuticSid(reportDTO.getTherapeuticSid());
                brdto.setBrandSid(reportDTO.getBrandSid());
                brdto.setComapnySid(reportDTO.getComapnySid());
                brdto.setContractSid(reportDTO.getContractSid());
                brdto.setItemSid(Converters.convertNullToEmpty(obj[2]));
                brdto.setParent(0);
            }

        } else if (Constants.IndicatorConstants.ITEM.getConstant().equals(reportDTO.getViewType())) {
            if (reportDTO.getLevelNo() == 1) {
                brdto.setTherapeuticSid(Converters.convertNullToEmpty(obj[2]));
            }
            if (reportDTO.getLevelNo() == 2) {
                brdto.setTherapeuticSid(reportDTO.getTherapeuticSid());
                brdto.setItemSid(Converters.convertNullToEmpty(obj[2]));
            }
            if (reportDTO.getLevelNo() == 3) {
                brdto.setTherapeuticSid(reportDTO.getTherapeuticSid());
                brdto.setItemSid(reportDTO.getItemSid());
                brdto.setComapnySid(Converters.convertNullToEmpty(obj[2]));
            }
            if (reportDTO.getLevelNo() == 4) {
                brdto.setTherapeuticSid(reportDTO.getTherapeuticSid());
                brdto.setItemSid(reportDTO.getItemSid());
                brdto.setComapnySid(reportDTO.getComapnySid());
                brdto.setContractSid(Converters.convertNullToEmpty(obj[2]));
                brdto.setParent(0);
            }
        }
        return brdto;
    }

    /**
     *
     * @param acctCloserMasterId
     * @param userId
     * @param sessionId
     */
    public static void callGtnProcedure(int acctCloserMasterId, String userId, String sessionId) {
        Connection connection = null;
        DataSource datasource;
        CallableStatement statement = null;
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup("java:jboss/datasources/jdbc/appDataPool");
            if (datasource != null) {
                connection = datasource.getConnection();
            }
            if (connection != null) {
                LOGGER.info(" Executing gtn Insert procedure ");
                statement = connection.prepareCall("{call PRC_AC_GTN_BALANCE_INSERT(?,?,?)}");

                statement.setInt(1, acctCloserMasterId);
                statement.setInt(2, Integer.parseInt(userId));
                statement.setInt(3, Integer.parseInt(sessionId));
                statement.execute();
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                LOGGER.error(ex);
            }
        }

    }

    public Map<String, String> loadVariableColumnMap() {
        Map<String, String> inputMap = new HashMap<String, String>();
        inputMap.put("Accruals", "SUM(ACCRUALS) ACCRUALS");
        inputMap.put(BasReportVariables.ACCRUALS_BASE_RATE.toString(), "SUM(ACCRUALS_BASE_RATE) ACCRUALS_BASE_RATE");
        inputMap.put(BasReportVariables.ACCRUALS_MANUAL_ADJUSTMENTS.toString(), "SUM(ACCRUALS_MANUAL_ADJUSTMENTS) ACCRUALS_MANUAL_ADJUSTMENTS");
        inputMap.put(BasReportVariables.ACCRUALS_RECONCILIATION.toString(), "SUM(ACCRUALS_RECONCILIATION) ACCRUALS_RECONCILIATION");
        inputMap.put(BasReportVariables.ACCRUALS_OTHER.toString(), "SUM(ACCRUALS_OTHER) ACCRUALS_OTHER");
        inputMap.put(BasReportVariables.PROJECTED_DEDUCTION_AMOUNT.toString(), "SUM(PROJ_DEDUCTION_AMT) PROJ_DEDUCTION_AMT");
        inputMap.put("Deductions Amount", "SUM(DEDUCTION_AMOUNT) DEDUCTION_AMOUNT");
        inputMap.put("Deductions", "SUM(DEDUCTION_AMOUNT) DEDUCTION_AMOUNT");
        inputMap.put("Variance - Accruals to Deductions", "SUM(DEDUCTION_AMOUNT) Var_Accruals_to_Deductions");
        inputMap.put(BasReportVariables.PROJ_DEDUCTION_RATE.toString(), "AVG(PROJ_DEDUCTION_RATE) PROJ_DEDUCTION_RATE");
        inputMap.put(BasReportVariables.DEDUCTION_RATE.toString(), "AVG(DEDUCTION_RATE) DEDUCTION_RATE");
        inputMap.put(BasReportVariables.PROJ_CONTRACT_SALES_AMT.toString(), "SUM(PROJ_CONT_SALES_AMT) PROJ_CONT_SALES_AMT");
        inputMap.put(BasReportVariables.CONTRACT_SALES_AMT.toString(), "SUM(CONT_SALES_AMT) CONT_SALES_AMT");
        inputMap.put(BasReportVariables.PROJ_CONTRACT_SALES_UNITS.toString(), "SUM(PROJ_CONT_SALES_UNITS) PROJ_CONT_SALES_UNITS");
        inputMap.put(BasReportVariables.CONTRACT_SALES_UNITS.toString(), "SUM(CONT_SALES_UNITS) CONT_SALES_UNITS");
        inputMap.put(BasReportVariables.GTS_AMOUNT.toString(), "SUM(GTS_AMOUNT) GTS_AMOUNT");
        inputMap.put(BasReportVariables.GTS_UNITS.toString(), "SUM(GTS_UNITS) GTS_UNITS");
        inputMap.put(BasReportVariables.PER_OF_PROJ_GTS.toString(), "AVG(PA_ACCRUALS_PROJ_DED)");
        inputMap.put(BasReportVariables.PER_OF_GTS.toString(), "AVG(PA_ACCRUALS_PROJ_DED)");
        inputMap.put(BasReportVariables.PER_ACHIEVED_ACCRUALS_TO_PROJ_DEDUCTIONS.toString(), "AVG(PA_ACCRUALS_PROJ_DED) PA_ACCRUALS_PROJ_DED");
        inputMap.put(BasReportVariables.VARIANCE_ACCRUALS_TO_PROJ_DEDUCTIONS.toString(), "SUM(VAR_ACCRUALS_PROJ_DED) VAR_ACCRUALS_PROJ_DED");
        inputMap.put(BasReportVariables.PER_ACHIEVED_ACCRUALS_TO_DEDUCTIONS.toString(), "AVG(PA_ACCRUALS_DED) PA_ACCRUALS_DED");
        inputMap.put(BasReportVariables.VARIANCE_ACCRUALS_TO_DEDUCTIONS.toString(), "SUM(VAR_ACCRUALS_DED) VAR_ACCRUALS_DED");
        inputMap.put(BasReportVariables.PER_ACHIEVED_DEDUCTIONS_TO_PROJ_DEDUCTIONS.toString(), "AVG(PA_DED_PROJ_DED) PA_DED_PROJ_DED");
        inputMap.put(BasReportVariables.VARIANCE_DEDUCTIONS_AMT_TO_PROJ_DEDUCTIONS_AMT.toString(), "SUM(VAR_DED_AMT_PROJ_DED_AMT) VAR_DED_AMT_PROJ_DED_AMT");
        inputMap.put(BasReportVariables.VARIANCE_DEDUCTIONS_RATE_TO_PROJ_DEDUCTION_RATE.toString(), "SUM(VAR_DED_RATE_PROJ_DED_RATE) VAR_DED_RATE_PROJ_DED_RATE");
        inputMap.put(BasReportVariables.PER_ACHIEVED_CONTRACT_SALES_TO_PROJ_CONTRACT_SALES.toString(), "AVG(ACH_CON_SALES_PROJ_CON_SALES) ACH_CON_SALES_PROJ_CON_SALES");
        inputMap.put(BasReportVariables.VARIANCE_CONTRACT_SALES_TO_PROJ_CONTRACT_SALES.toString(), "SUM(VAR_CONT_SALES_PROJ_CONT_SALES) VAR_CONT_SALES_PROJ_CONT_SALES");
        inputMap.put(BasReportVariables.PER_ACHIEVED_CONTRACT_UNITS_TO_PROJEC_CONTRACT_UNITS.toString(), "AVG(PA_CONT_UNITS_PROJ_CONT_UNITS) PA_CONT_UNITS_PROJ_CONT_UNITS");
        inputMap.put(BasReportVariables.VARIANCE_CONTRACT_UNITS_TO_PROJ_CONTRACT_UNITS.toString(), "SUM(VAR_CONT_UNITS_PROJ_CONT_UNITS) VAR_CONT_UNITS_PROJ_CONT_UNITS");
        inputMap.put(BasReportVariables.DEMAND_AMOUNT.toString(), "SUM(DEMAND_AMOUNT) DEMAND_AMOUNT");
        inputMap.put(BasReportVariables.DEMAND_UNITS.toString(), "SUM(DEMAND_UNITS) DEMAND_UNITS");
        inputMap.put(BasReportVariables.INVENTORY_WITHDRAWAL_AMOUNT.toString(), "SUM(INVENTORY_WITHDRAWALS_AMOUNT) INVENTORY_WITHDRAWALS_AMOUNT");
        inputMap.put(BasReportVariables.INVENTORY_WITHDRAWAL_UNITS.toString(), "SUM(INVENTORY_WITHDRAWALS_UNITS) INVENTORY_WITHDRAWALS_UNITS");
        inputMap.put(BasReportVariables.PER_OF_PROJ_DEMAND.toString(), "SUM(PER_PROJ_DEMAND) PER_PROJ_DEMAND");
        inputMap.put(BasReportVariables.PER_OF_DEMAND.toString(), "SUM(PER_DEMAND) PER_DEMAND");
        inputMap.put(BasReportVariables.PER_OF_PROJ_GTS.toString(), "SUM(PER_PROJ_GTS) PER_PROJ_GTS");
        inputMap.put(BasReportVariables.PER_OF_GTS.toString(), "SUM(PER_GTS) PER_GTS");
        inputMap.put(BasReportVariables.PER_OF_PROJ_INVENTORY_WITHDRAWALS.toString(), "SUM(PER_PROJ_INVENTORY_WITHDRAWAL) PER_PROJ_INVENTORY_WITHDRAWAL");
        inputMap.put(BasReportVariables.PER_OF_INVENTORY_WITHDRAWALS.toString(), "SUM(PER_INVENTORY_WITHDRAWAL) PER_INVENTORY_WITHDRAWAL");

        return inputMap;
    }

    private String getPeriodQuery(BalanceReportDTO dto) {
        String frequecy = dto.getFrequency();
        String query = StringUtils.EMPTY;
        if (Constants.FrequencyConstants.ANNUALLY.getConstant().equals(frequecy)) {
            query = "  ";
        } else if (Constants.FrequencyConstants.SEMI_ANNUALLY.getConstant().equals(frequecy)) {
            query = ", P.SEMI_ANNUAL ";
        } else if (Constants.FrequencyConstants.QUARTERLY.getConstant().equals(frequecy)) {
            query = ", P.QUARTER ";
        } else if (Constants.FrequencyConstants.MONTHLY.getConstant().equals(frequecy)) {
            query = ", P.MONTH ";
        }
        return query;
    }

    /**
     *
     * @param selectedVariables
     * @return
     */
    private List<String> getColumnsToBeFetched(List<String> selectedVariables) {
        List<String> finalQuery = new ArrayList<String>();
        Map<String, String> inputMap = loadVariableColumnMap();
        String columnQuery = StringUtils.EMPTY;
        String denseQuery = StringUtils.EMPTY;
        for (String selectedVariable : selectedVariables) {
            if (StringUtils.EMPTY.equals(columnQuery)) {
                String temp = inputMap.get(selectedVariable);
                columnQuery += temp;
                denseQuery += temp.split(" ")[1];
            } else {
                String temp = inputMap.get(selectedVariable);
                columnQuery += "," + temp;
                denseQuery += "," + temp.split(" ")[1];
            }

        }
        finalQuery.add(columnQuery);
        finalQuery.add(denseQuery);
        return finalQuery;
    }

    private String getDateRangeQuery(DataSelectionDTO dsdto, BalanceReportDTO dto) {

        String frequecy = dto.getFrequency();
        String query = StringUtils.EMPTY;
        SimpleDateFormat format = new SimpleDateFormat(com.stpl.app.accountclose.gtnbalancereport.utils.Constants.DateFormatConstants.MMddyyyy.getConstant());
        try {
            if (Constants.FrequencyConstants.ANNUALLY.getConstant().equals(frequecy)) {
                String tempFromDate = DataSelectionUtil.getDateFromAnnual(dto.getTimePeriodFrom());
                String tempTodate = DataSelectionUtil.getLastDateFromAnnual(dto.getTimePeriodTo());
                String fromDate = CommonLogic.convertStringToDate(format.parse(tempFromDate).toString(), DEFAULT_JAVA_DATE_FORMAT.getConstant(), DEFAULT_SQL_DATE_FORMAT.getConstant());
                String toDate = CommonLogic.convertStringToDate(format.parse(tempTodate).toString(), DEFAULT_JAVA_DATE_FORMAT.getConstant(), DEFAULT_SQL_DATE_FORMAT.getConstant());
                query = " AND P.PERIOD_DATE between " + "'" + fromDate + "'" + " AND " + "'" + toDate + "' ";
            } else if (Constants.FrequencyConstants.SEMI_ANNUALLY.getConstant().equals(frequecy)) {
                String tempFromDate = DataSelectionUtil.getDateFromSemiannualy(dto.getTimePeriodFrom());
                String tempTodate = DataSelectionUtil.getLastDateFromSemiannually(dto.getTimePeriodTo());
                String fromDate = CommonLogic.convertStringToDate(format.parse(tempFromDate).toString(), DEFAULT_JAVA_DATE_FORMAT.getConstant(), DEFAULT_SQL_DATE_FORMAT.getConstant());
                String toDate = CommonLogic.convertStringToDate(format.parse(tempTodate).toString(), DEFAULT_JAVA_DATE_FORMAT.getConstant(), DEFAULT_SQL_DATE_FORMAT.getConstant());
                query = " AND P.PERIOD_DATE between " + "'" + fromDate + "'" + " AND " + "'" + toDate + "' ";
            } else if (Constants.FrequencyConstants.QUARTERLY.getConstant().equals(frequecy)) {
                String tempFromDate = DataSelectionUtil.getDateFromQuarter(dto.getTimePeriodFrom());
                String tempTodate = DataSelectionUtil.getLastDateFromQuarter(dto.getTimePeriodTo());
                String fromDate = CommonLogic.convertStringToDate(format.parse(tempFromDate).toString(), DEFAULT_JAVA_DATE_FORMAT.getConstant(), DEFAULT_SQL_DATE_FORMAT.getConstant());
                String toDate = CommonLogic.convertStringToDate(format.parse(tempTodate).toString(), DEFAULT_JAVA_DATE_FORMAT.getConstant(), DEFAULT_SQL_DATE_FORMAT.getConstant());
                query = " AND P.PERIOD_DATE between " + "'" + fromDate + "'" + " AND " + "'" + toDate + "' ";
            } else if (Constants.FrequencyConstants.MONTHLY.getConstant().equals(frequecy)) {
                query = StringUtils.EMPTY;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return query;
    }

    public String getFormattedValue(DecimalFormat FORMAT, String value) {
        if (StringUtils.EMPTY.equals(value)) {
            value = "--";
        } else {
            Double newValue = Double.valueOf(value);
            if (FORMAT.toPattern().contains("%")) {
                newValue = newValue / 100;
            }
            value = FORMAT.format(newValue);
        }
        return value;
    }

    public List<String> getSelectionDetails(int acctCloserMasterId) {

        Map<String, String> inputMap = new HashMap<String, String>();
        List<String> finalList = new ArrayList<String>();
        try {
            inputMap.put("?ACC_CLOSURE_MASTER_SID?", String.valueOf(acctCloserMasterId));
            List results = (List) QueryUtils.executeSelectQuery(inputMap, "getacgtnbalanceselection");
            for (int i = 0; i < results.size(); i++) {
                final Object[] obj = (Object[]) results.get(i);
                finalList.add(String.valueOf(obj[0]));
                finalList.add(String.valueOf(obj[1]));
                finalList.add(String.valueOf(obj[2]));
                finalList.add(String.valueOf(obj[3]));
                finalList.add(String.valueOf(obj[4]));
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return finalList;
    }

    /**
     *
     * @param sessionDTO
     * @return
     */
    public List<LiabilitySummaryDto> getLiabilityResults(SessionDTO sessionDTO) {

        Map<String, String> inputMap = new HashMap<String, String>();
        List<LiabilitySummaryDto> finalList = new ArrayList<LiabilitySummaryDto>();
        try {
            inputMap.put("?ACC_CLOSURE_MASTER_SID?", String.valueOf(sessionDTO.getAcctCloserMasterId()));
            List results = (List) QueryUtils.executeSelectQuery(inputMap, "liabilitysummaryquery");
            for (int i = 0; i < results.size(); i++) {
                final Object[] obj = (Object[]) results.get(i);
                LiabilitySummaryDto summaryDto = new LiabilitySummaryDto();
                summaryDto.setYear(Converters.convertNullToEmpty(obj[0]));
                summaryDto.setActuals(getFormattedValue(CUR_ZERO, Converters.convertNullToEmpty(obj[1])));
                summaryDto.setAccruals(getFormattedValue(CUR_ZERO, Converters.convertNullToEmpty(obj[2])));
                summaryDto.setRemainingEstimate(getFormattedValue(CUR_ZERO, Converters.convertNullToEmpty(obj[3])));
                finalList.add(summaryDto);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return finalList;
    }

    /**
     *
     * @param trDto
     * @param session
     * @param CHECK_RECORD
     */
    public void updateTempTableRecord(BalanceReportDTO trDto, SessionDTO session, String CHECK_RECORD) {
        Map<String, String> inputMap = new HashMap<String, String>();
        String query = StringUtils.EMPTY;
        inputMap.put("?TABLE_NAME?", "ST_ACC_CLOSURE_DETAILS");
        inputMap.put("?SET_COLUMN_NAME?", "CHECK_RECORD");
        inputMap.put("?SET_VALUE?", trDto.getCheckRecord() ? "0" : "1");
        inputMap.put("?COLUMN_NAME1?", "USER_ID");
        inputMap.put("?COLUMN_NAME2?", "SESSION_ID");
        inputMap.put("?COLUMN_NAME3?", "MODULE_NAME");
        inputMap.put("?COLUMN_NAME4?", "COMPANY_MASTER_SID");

        inputMap.put("?VALUE1?", session.getUserId());
        inputMap.put("?VALUE2?", session.getSessionId());
        inputMap.put("?VALUE3?", com.stpl.app.accountclose.gtnbalancereport.utils.Constants.DS_SELECTED_CUSTOMERS);
        inputMap.put("?VALUE4?", trDto.getComapnySid());
        String viewType = session.getBalanceReportDTO().getViewType();
        if (viewType.equals(Constants.IndicatorConstants.CUSTOMER.getConstant())) {
            inputMap.put("?VALUE4?", trDto.getComapnySid());
        } else if (viewType.equals(Constants.IndicatorConstants.BRAND.getConstant())) {
            if (trDto.getLevelNo() == 3) {
                inputMap.put("?VALUE4?", trDto.getComapnySid());
            } else {
                inputMap.put("?VALUE4?", "%");
            }
        } else {
            if (trDto.getLevelNo() == 3) {
                inputMap.put("?VALUE4?", trDto.getComapnySid());
            } else {
                inputMap.put("?VALUE4?", "%");
            }
        }
        try {
            QueryUtils.executeUpdateQuery(inputMap, "tempUpdateRecord");
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public List<HelperDTO> getFilterValues(String QueryName, SessionDTO sessionDTO) throws Exception {
        Map<String, String> inputMap = new HashMap<String, String>();
        String append = StringUtils.EMPTY;
        if (Constants.IndicatorConstants.CUSTOMER.getConstant().equals(QueryName)) {
            append = "CM.COMPANY_MASTER_SID, CM.COMPANY_NAME ";
        } else if (Constants.IndicatorConstants.BRAND.getConstant().equals(QueryName)) {
            append = "BM.BRAND_MASTER_SID, BM.BRAND_NAME ";
        } else if (Constants.IndicatorConstants.ITEM.getConstant().equals(QueryName)) {
            append = "IM.ITEM_MASTER_SID ,IM.ITEM_ID";
        }
        inputMap.put("?COLUMNS?", append);
        inputMap.put("?USER_ID?", sessionDTO.getUserId());
        inputMap.put("?SESSION_ID?", sessionDTO.getSessionId());
        inputMap.put("?ACC_CLOSURE_MASTER_SID?", String.valueOf(sessionDTO.getAcctCloserMasterId()));
        List<Object[]> list = (List<Object[]>) QueryUtils.executeSelectQuery(inputMap, "levelFilterValueQuery");
        List<HelperDTO> resultList = new ArrayList<HelperDTO>();
        resultList.add(ddlbDefaultValue);
        for (Object[] str : list) {
            HelperDTO dto = new HelperDTO();
            dto.setId(str[0] == null ? 0 : Integer.valueOf(str[0].toString()));
            dto.setDescription(str[1] == null ? "0" : String.valueOf(str[1]));
            resultList.add(dto);
        }
        return resultList;
    }

    /**
     *
     * @param parentId
     * @param session
     * @param dto
     * @param b
     * @return
     */
    public int getExpandLevelCount(Object parentId, SessionDTO session, BalanceReportDTO dto, List<String> selectedVariables, boolean b) {

        LOGGER.info("Entering getBalanceReportCount");
        List results = new ArrayList<Object[]>();
        String query = StringUtils.EMPTY;
        List<String> append = new ArrayList<String>();
        String frequencyQuery = StringUtils.EMPTY;
        List<String> selectedColumns = new ArrayList<String>();
        String periodQuery = StringUtils.EMPTY;
        if (parentId instanceof BalanceReportDTO) {
            BalanceReportDTO parentDto = (BalanceReportDTO) parentId;
            dto.setLevelNo(parentDto.getLevelNo() + 1);
        } else {
            dto.setLevelNo(1);
            dto.setComapnySid(StringUtils.EMPTY);
            dto.setContractSid(StringUtils.EMPTY);
            dto.setTherapeuticSid(StringUtils.EMPTY);
            dto.setBrandSid(StringUtils.EMPTY);
            dto.setItemSid(StringUtils.EMPTY);

        }
        DataSelectionDTO dsdto = session.getDataSelectionDTO();
        try {
            append = getQueryOnView(dto);
            Map<String, String> inputMap = new HashMap<String, String>();
            frequencyQuery = getPeriodQuery(dto);
            selectedColumns = getColumnsToBeFetched(selectedVariables);
            periodQuery = getDateRangeQuery(dsdto, dto);
            inputMap.put("?QU?", append.get(0));
            inputMap.put("?QURANK?", append.get(1));
            inputMap.put("?FREQUENCY?", frequencyQuery);

            inputMap.put("?SELECTEDCOLUMN?", selectedColumns.get(0));
            inputMap.put("?DENSECOLUMN?", selectedColumns.get(1));
            inputMap.put("?ACC_CLOSURE_MASTER_SID?", String.valueOf(session.getAcctCloserMasterId()));
            inputMap.put("?USER_ID?", session.getUserId());
            inputMap.put("?SESSION_ID?", session.getSessionId());
            inputMap.put("?PERIOD_DATE?", periodQuery);
            inputMap.put("?CT?", dsdto.getMarketType());
            inputMap.put("?UDC?", dsdto.getDeductionType());
            inputMap.put("?RPT?", dsdto.getDeductionSubType());
            inputMap.put("?PGSID?", "%");
            inputMap.put("?PGSID?", "%");
            inputMap.put("?CMSID?", "%");
            inputMap.put("?CSID?", "%");
            inputMap.put("?THID?", "%");
            inputMap.put("?BMSID?", "%");
            inputMap.put("?ISID?", "%");

            if (StringUtils.isNotBlank(dto.getComapnySid())) {
                inputMap.put("?CMSID?", dto.getComapnySid());
            }
            if (StringUtils.isNotBlank(dto.getContractSid())) {
                inputMap.put("?CSID?", dto.getContractSid());
            }
            if (StringUtils.isNotBlank(dto.getTherapeuticSid())) {
                inputMap.put("?THID?", dto.getTherapeuticSid());
            }
            if (StringUtils.isNotBlank(dto.getBrandSid())) {
                inputMap.put("?BMSID?", dto.getBrandSid());
            }
            if (StringUtils.isNotBlank(dto.getItemSid())) {
                inputMap.put("?ISID?", dto.getItemSid());
            }
            results = (List) QueryUtils.executeSelectQuery(inputMap, dto.getQuery() + "Count");
        } catch (Exception e) {
            LOGGER.error(e);
        }
        Object obj = null;
        if (!results.isEmpty()) {
            obj = results.get(0);
        }
        return obj == null ? 0 : (Integer) obj;
    }

    /**
     *
     * @param lastParent
     * @param start
     * @param last
     * @param sessionDTO
     * @param dto
     * @param selectedVariables
     * @param b
     * @return
     */
    public List<BalanceReportDTO> getExpandLevelResult(Object lastParent, int start, int last, SessionDTO sessionDTO, BalanceReportDTO dto, List<String> selectedVariables, boolean excel, List<String> doubleHeaderList) {
        List<BalanceReportDTO> resultList = new ArrayList<BalanceReportDTO>();
        int offset = start + last;
        List results = new ArrayList<Object[]>();
        String query = StringUtils.EMPTY;
        List<String> append = new ArrayList<String>();
        String frequencyQuery = StringUtils.EMPTY;
        List<String> selectedColumns = new ArrayList<String>();
        String periodQuery = StringUtils.EMPTY;
        if (lastParent instanceof BalanceReportDTO) {
            BalanceReportDTO parentDto = (BalanceReportDTO) lastParent;
            dto.setLevelNo(parentDto.getLevelNo() + 1);
        } else {
            dto.setLevelNo(1);
            dto.setComapnySid(StringUtils.EMPTY);
            dto.setContractSid(StringUtils.EMPTY);
            dto.setTherapeuticSid(StringUtils.EMPTY);
            dto.setBrandSid(StringUtils.EMPTY);
            dto.setItemSid(StringUtils.EMPTY);
        }
        DataSelectionDTO dsdto = sessionDTO.getDataSelectionDTO();
        try {
            append = getQueryOnView(dto);
            frequencyQuery = getPeriodQuery(dto);
            selectedColumns = getColumnsToBeFetched(selectedVariables);
            periodQuery = getDateRangeQuery(dsdto, dto);
            Map<String, String> inputMap = new HashMap<String, String>();
            inputMap.put("?QU?", append.get(0));
            inputMap.put("?QURANK?", append.get(1));
            inputMap.put("?FREQUENCY?", frequencyQuery);
            inputMap.put("?RSLTFREQUENCY?", frequencyQuery.replace("P", "RSLT"));
            inputMap.put("?SELECTEDCOLUMN?", selectedColumns.get(0));
            inputMap.put("?DENSECOLUMN?", selectedColumns.get(1));
            inputMap.put("?ACC_CLOSURE_MASTER_SID?", String.valueOf(sessionDTO.getAcctCloserMasterId()));
            inputMap.put("?USER_ID?", sessionDTO.getUserId());
            inputMap.put("?SESSION_ID?", sessionDTO.getSessionId());
            inputMap.put("?PERIOD_DATE?", periodQuery);
            inputMap.put("?CT?", dsdto.getMarketType());
            inputMap.put("?UDC?", dsdto.getDeductionType());
            inputMap.put("?RPT?", dsdto.getDeductionSubType());
            inputMap.put("?PGSID?", "%");
            inputMap.put("?PGSID?", "%");
            inputMap.put("?CMSID?", "%");
            inputMap.put("?CSID?", "%");
            inputMap.put("?THID?", "%");
            inputMap.put("?BMSID?", "%");
            inputMap.put("?ISID?", "%");
            inputMap.put("?WHERE?", " WHERE RSLT.RANK >" + start + " AND RSLT.RANK <=" + offset);

            if (StringUtils.isNotBlank(dto.getComapnySid())) {
                inputMap.put("?CMSID?", dto.getComapnySid());
            }
            if (StringUtils.isNotBlank(dto.getContractSid())) {
                inputMap.put("?CSID?", dto.getContractSid());
            }
            if (StringUtils.isNotBlank(dto.getTherapeuticSid())) {
                inputMap.put("?THID?", dto.getTherapeuticSid());
            }
            if (StringUtils.isNotBlank(dto.getBrandSid())) {
                inputMap.put("?BMSID?", dto.getBrandSid());
            }
            if (StringUtils.isNotBlank(dto.getItemSid())) {
                inputMap.put("?ISID?", dto.getItemSid());
            }
            LOGGER.info("dto.getComapnySid()\n" + dto.getComapnySid());
            LOGGER.info("dto.getContractSid()\n" + dto.getContractSid());
            LOGGER.info("dto.getTherapeuticSid()\n" + dto.getTherapeuticSid());
            LOGGER.info("dto.getBrandSid()\n" + dto.getBrandSid());
            LOGGER.info("dto.getItemSid()\n" + dto.getItemSid());
            results = (List) QueryUtils.executeSelectQuery(inputMap, dto.getQuery());
            resultList = getBalanceReportDto(results, dto, selectedVariables, doubleHeaderList, excel);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return resultList;
    }
}
