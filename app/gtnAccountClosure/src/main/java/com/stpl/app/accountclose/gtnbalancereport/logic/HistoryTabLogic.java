/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.gtnbalancereport.logic;

import com.stpl.app.accountclose.common.CommonLogic;
import com.stpl.app.accountclose.common.CommonUtil;
import com.stpl.app.accountclose.dao.BaseRateDAO;
import com.stpl.app.accountclose.dao.impl.BaseRateDAOImpl;
import com.stpl.app.accountclose.gtnbalancereport.dto.BalanceReportDTO;
import com.stpl.app.accountclose.gtnbalancereport.dto.DataSelectionDTO;
import static com.stpl.app.accountclose.gtnbalancereport.logic.tableLogic.BalanceReportLogic.LOGGER;
import static com.stpl.app.accountclose.gtnbalancereport.utils.Constants.DateFormatConstants.DEFAULT_JAVA_DATE_FORMAT;
import static com.stpl.app.accountclose.gtnbalancereport.utils.Constants.DateFormatConstants.DEFAULT_SQL_DATE_FORMAT;
import com.stpl.app.accountclose.gtnbalancereport.utils.DataSelectionUtil;
import com.stpl.app.accountclose.gtnbalancereport.utils.HeaderUtils;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.utils.Constants;
import com.stpl.app.accountclose.utils.Constants.BasReportVariables;
import com.stpl.app.accountclose.utils.Converters;
import com.stpl.app.accountclose.utils.QueryUtils;
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
public class HistoryTabLogic {

    public static final Logger LOGGER = Logger.getLogger(HistoryTabLogic.class);
    BaseRateDAO dao = new BaseRateDAOImpl();
    private static final DecimalFormat CUR_ZERO = new DecimalFormat("$#,##0.00");

    /**
     *
     * @param parentId
     * @param session
     * @param dto
     * @param b
     * @return
     */
    public int getBalanceReportCount(Object parentId, SessionDTO session, BalanceReportDTO dto, List<String> selectedVariables, boolean b) {

        LOGGER.info("Entering getHistoryReportCount");
        List results = new ArrayList<Object[]>();
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
            selectedColumns = getColumnsToBeFetched(selectedVariables, dto);
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
            results = (List) QueryUtils.executeSelectQuery(inputMap, "gtnmultipleccpqueryCount");
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
    public List<BalanceReportDTO> getBalanceReportResults(Object lastParent, int start, int last, SessionDTO sessionDTO, BalanceReportDTO dto, List<String> selectedVariables, boolean b) {
        List<BalanceReportDTO> resultList = new ArrayList<BalanceReportDTO>();
        List results = new ArrayList<Object[]>();
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
            selectedColumns = getColumnsToBeFetched(selectedVariables, dto);
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
            inputMap.put("?WHERE?", " WHERE RSLT.RANK >" + start + " AND RSLT.RANK <=" + last);
            inputMap.put("?WHERE1?", " WHERE RSLT1.RANK >" + start + " AND RSLT1.RANK <=" + last);
            if (StringUtils.isNotBlank(dto.getAsOfDate())) {
                String asofdate = getAsOfDate(dto);
                List<String> selectedvariablesasofdate = new ArrayList<String>();
                inputMap.put("?ASOFDATEQURANK?", StringUtils.EMPTY);
                inputMap.put("?ASOF_DATE?", asofdate);
                String orderBy = append.get(0);
                inputMap.put("?QURANK1?", append.get(1).replace("RSLT", "RSLT1").replace("CNAME", "CNAME1").replace("CID", "CID1"));
                selectedvariablesasofdate = getVariables(selectedColumns);
                inputMap.put("?CA?", selectedvariablesasofdate.get(0));
                inputMap.put("?SCA?", selectedvariablesasofdate.get(1));
                inputMap.put("?SCS?", selectedvariablesasofdate.get(2));
            }
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

            results = (List) QueryUtils.executeSelectQuery(inputMap, dto.getQuery());
            resultList = getBalanceReportDto(results, dto, selectedVariables);
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
    private List<BalanceReportDTO> getBalanceReportDto(List results, BalanceReportDTO reportDTO, List<String> selectedVariables) {
        List<BalanceReportDTO> resultList = new ArrayList<BalanceReportDTO>();
        HeaderUtils utlis = new HeaderUtils();
        if (!results.isEmpty()) {
            int size = results.size();
            String oldLevelValue = "oldValue";
            String newLevelValue = "newValue";
            BalanceReportDTO brdto = new BalanceReportDTO();
            if (StringUtils.isNotBlank(reportDTO.getAsOfDate())) {
                selectedVariables = HeaderUtils.loadHistoryPeriods(selectedVariables);
            }
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
                for (String selectedVariable : selectedVariables) {
                    brdto.addStringProperties(utlis.getHeaderMap().get(selectedVariable + commonColumn), getFormattedValue(CUR_ZERO, Converters.convertNullToEmpty(obj[++j])));
                }

                setSystemIdsBasedOnView(reportDTO, brdto, obj);

                if (!oldLevelValue.equals(newLevelValue)) {
                    brdto.addStringProperties("customer.0", obj[1].toString());
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
        if (Constants.IndicatorConstants.CUSTOMER.getConstant().equals(dto.getHistoryView())) {
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
        } else if (Constants.IndicatorConstants.BRAND.getConstant().equals(dto.getHistoryView())) {
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
        } else if (Constants.IndicatorConstants.ITEM.getConstant().equals(dto.getHistoryView())) {
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
        if (StringUtils.isNotBlank(dto.getAsOfDate())) {
            if (Constants.IndicatorConstants.CUSTOMER.getConstant().equals(dto.getHistoryView())) {
                if (dto.getLevelNo() == 1) {
                    append = "CM.COMPANY_NAME,CM.COMPANY_MASTER_SID ";
                    denseQuery = "RSLT.COMPANY_NAME AS CNAME,RSLT.COMPANY_MASTER_SID AS CID";
                } else if (dto.getLevelNo() == 2) {
                    append = "CN.CONTRACT_NAME,CN.CONTRACT_MASTER_SID ";
                    denseQuery = "RSLT.CONTRACT_NAME AS CNAME,RSLT.CONTRACT_MASTER_SID AS CID";
                } else if (dto.getLevelNo() == 3) {
                    append = "HT.DESCRIPTION,HT.HELPER_TABLE_SID ";
                    denseQuery = "RSLT.DESCRIPTION AS CNAME,RSLT.HELPER_TABLE_SID AS CID";
                } else if (dto.getLevelNo() == 4) {
                    append = "BM.BRAND_NAME,BM.BRAND_MASTER_SID ";
                    denseQuery = "RSLT.BRAND_NAME AS CNAME,RSLT.BRAND_MASTER_SID AS CID";
                } else if (dto.getLevelNo() == 5) {
                    append = "IM.ITEM_NAME,IM.ITEM_MASTER_SID ";
                    denseQuery = "RSLT.ITEM_NAME AS CNAME,RSLT.ITEM_MASTER_SID AS CID";
                }
            } else if (Constants.IndicatorConstants.BRAND.getConstant().equals(dto.getHistoryView())) {
                if (dto.getLevelNo() == 1) {
                    append = "HT.DESCRIPTION,HT.HELPER_TABLE_SID ";
                    denseQuery = "RSLT.DESCRIPTION AS CNAME,RSLT.HELPER_TABLE_SID AS CID";
                } else if (dto.getLevelNo() == 2) {
                    append = "BM.BRAND_NAME,BM.BRAND_MASTER_SID ";
                    denseQuery = "RSLT.BRAND_NAME AS CNAME,RSLT.BRAND_MASTER_SID AS CID";
                } else if (dto.getLevelNo() == 3) {
                    append = "CM.COMPANY_NAME,CM.COMPANY_MASTER_SID ";
                    denseQuery = "RSLT.COMPANY_NAME AS CNAME,RSLT.COMPANY_MASTER_SID AS CID";
                } else if (dto.getLevelNo() == 4) {
                    append = "CN.CONTRACT_NAME,CN.CONTRACT_MASTER_SID ";
                    denseQuery = "RSLT.CONTRACT_NAME AS CNAME,RSLT.CONTRACT_MASTER_SID AS CID";
                } else if (dto.getLevelNo() == 5) {
                    append = "IM.ITEM_NAME,IM.ITEM_MASTER_SID ";
                    denseQuery = "RSLT.ITEM_NAME AS CNAME,RSLT.ITEM_MASTER_SID AS CID";
                }
            } else if (Constants.IndicatorConstants.ITEM.getConstant().equals(dto.getHistoryView())) {
                if (dto.getLevelNo() == 1) {
                    append = "HT.DESCRIPTION,HT.HELPER_TABLE_SID ";
                    denseQuery = "RSLT.DESCRIPTION AS CNAME,RSLT.HELPER_TABLE_SID AS CID";
                } else if (dto.getLevelNo() == 2) {
                    append = "IM.ITEM_NAME,IM.ITEM_MASTER_SID ";
                    denseQuery = "RSLT.ITEM_NAME AS CNAME,RSLT.ITEM_MASTER_SID AS CID";
                } else if (dto.getLevelNo() == 3) {
                    append = "CM.COMPANY_NAME,CM.COMPANY_MASTER_SID ";
                    denseQuery = "RSLT.COMPANY_NAME AS CNAME,RSLT.COMPANY_MASTER_SID AS CID";
                } else if (dto.getLevelNo() == 4) {
                    append = "CN.CONTRACT_NAME,CN.CONTRACT_MASTER_SID ";
                    denseQuery = "RSLT.CONTRACT_NAME AS CNAME,RSLT.CONTRACT_MASTER_SID AS CID";
                }
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
        if (Constants.IndicatorConstants.CUSTOMER.getConstant().equals(reportDTO.getHistoryView())) {

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

        } else if (Constants.IndicatorConstants.BRAND.getConstant().equals(reportDTO.getHistoryView())) {
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

        } else if (Constants.IndicatorConstants.ITEM.getConstant().equals(reportDTO.getHistoryView())) {
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
        inputMap.put(StringUtils.EMPTY, StringUtils.EMPTY);
        inputMap.put("Accruals", "SUM(ACCRUALS) ACCRUALS");
        inputMap.put(BasReportVariables.ACCRUALS_BASE_RATE.toString(), "SUM(ACCRUALS_BASE_RATE) ACCRUALS_BASE_RATE");
        inputMap.put(BasReportVariables.ACCRUALS_MANUAL_ADJUSTMENTS.toString(), "SUM(ACCRUALS_MANUAL_ADJUSTMENTS) ACCRUALS_MANUAL_ADJUSTMENTS");
        inputMap.put(BasReportVariables.ACCRUALS_RECONCILIATION.toString(), "SUM(ACCRUALS_RECONCILIATION) ACCRUALS_RECONCILIATION");
        inputMap.put(BasReportVariables.ACCRUALS_OTHER.toString(), "SUM(ACCRUALS_OTHER) ACCRUALS_OTHER");
        inputMap.put(BasReportVariables.DEDUCTIONS.toString(), "SUM(PROJ_DEDUCTION_AMT) PROJ_DEDUCTION_AMT");
        inputMap.put("Deductions", "SUM(DEDUCTION_AMOUNT) DEDUCTION_AMOUNT");
        inputMap.put("Variance - Accruals to Deductions", "SUM(DEDUCTION_AMOUNT) Var_Accruals_to_Deductions");
        inputMap.put(BasReportVariables.VARIANCE_ACCRUALS_TO_DEDUCTIONS.toString(), "SUM(DEDUCTION_AMOUNT) Var_Accruals_to_Deductions");
        inputMap.put("Proj. Deduction Rate", "AVG(PROJ_DEDUCTION_RATE) PROJ_DEDUCTION_RATE");
        inputMap.put("Deduction Rate", "AVG(DEDUCTION_RATE) DEDUCTION_RATE");
        inputMap.put("Proj. Contract Sales Amt", "SUM(PROJ_CONT_SALES_AMT) PROJ_CONT_SALES_AMT");
        inputMap.put("Contract Sales Amt", "SUM(CONT_SALES_AMT) CONT_SALES_AMT");
        inputMap.put("Proj. Contract Sales units", "SUM(PROJ_CONT_SALES_UNITS) PROJ_CONT_SALES_UNITS");
        inputMap.put("Contract Sales Units", "SUM(CONT_SALES_UNITS) CONT_SALES_UNITS");
        inputMap.put("GTS Amount", "CONT_SALES_AMT");
        inputMap.put("GTS Units", "CONT_SALES_AMT");
        inputMap.put("% of Proj. GTS", "AVG(PA_ACCRUALS_PROJ_DED)");
        inputMap.put("% of GTS", "AVG(PA_ACCRUALS_PROJ_DED)");
        inputMap.put("% Achieved - Accruals to Proj. Deductions", "AVG(PA_ACCRUALS_PROJ_DED) PA_ACCRUALS_PROJ_DED");
        inputMap.put("Variance - Accruals to Proj. Deductions", "SUM(VAR_ACCRUALS_PROJ_DED) VAR_ACCRUALS_PROJ_DED");
        inputMap.put("% Achieved - Accruals to Deductions", "AVG(PA_ACCRUALS_DED) PA_ACCRUALS_DED");
        inputMap.put("Variance - Accruals to Deductions", "SUM(VAR_ACCRUALS_DED) VAR_ACCRUALS_DED");
        inputMap.put("% Achieved - Deductions to Proj. Deductions", "AVG(PA_DED_PROJ_DED) PA_DED_PROJ_DED");
        inputMap.put("Variance - Deductions Amt to Proj. Deductions Amt", "SUM(VAR_DED_AMT_PROJ_DED_AMT) VAR_DED_AMT_PROJ_DED_AMT");
        inputMap.put("Variance - Deductions Rate to Proj. Deduction Rate", "SUM(VAR_DED_RATE_PROJ_DED_RATE) VAR_DED_RATE_PROJ_DED_RATE");
        inputMap.put("% Achieved - Contract Sales to Proj. Contract Sales", "AVG(ACH_CON_SALES_PROJ_CON_SALES) ACH_CON_SALES_PROJ_CON_SALES");
        inputMap.put("Variance - Contract Sales to Proj. Contract Sales", "SUM(VAR_CONT_SALES_PROJ_CONT_SALES) VAR_CONT_SALES_PROJ_CONT_SALES");
        inputMap.put("% Achieved - Contract Units to Projec. Contract Units", "AVG(PA_CONT_UNITS_PROJ_CONT_UNITS) PA_CONT_UNITS_PROJ_CONT_UNITS");
        inputMap.put("Variance - Contract Units to Proj. Contract Units", "SUM(VAR_CONT_UNITS_PROJ_CONT_UNITS) VAR_CONT_UNITS_PROJ_CONT_UNITS");
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
    private List<String> getColumnsToBeFetched(List<String> selectedVariables, BalanceReportDTO reportDTO) {
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
        if (StringUtils.isNotBlank(reportDTO.getAsOfDate())) {

        }
        return finalQuery;
    }

    private String getDateRangeQuery(DataSelectionDTO dsdto, BalanceReportDTO dto) {

        String frequecy = dto.getFrequency();
        String query = StringUtils.EMPTY;
        SimpleDateFormat format = new SimpleDateFormat(com.stpl.app.accountclose.gtnbalancereport.utils.Constants.DateFormatConstants.MMddyyyy.getConstant());
        try {
            if (Constants.FrequencyConstants.ANNUALLY.getConstant().equals(frequecy)) {
                String tempFromDate = DataSelectionUtil.getDateFromAnnual(dto.getHisTimePeriod());
                String tempTodate = DataSelectionUtil.getLastDateFromAnnual(dto.getHisTimePeriod());
                String fromDate = CommonLogic.convertStringToDate(format.parse(tempFromDate).toString(), DEFAULT_JAVA_DATE_FORMAT.getConstant(), DEFAULT_SQL_DATE_FORMAT.getConstant());
                String toDate = CommonLogic.convertStringToDate(format.parse(tempTodate).toString(), DEFAULT_JAVA_DATE_FORMAT.getConstant(), DEFAULT_SQL_DATE_FORMAT.getConstant());
                query = " AND P.PERIOD_DATE between " + "'" + fromDate + "'" + " AND " + "'" + toDate + "' ";
            } else if (Constants.FrequencyConstants.SEMI_ANNUALLY.getConstant().equals(frequecy)) {
                String tempFromDate = DataSelectionUtil.getDateFromSemiannualy(dto.getHisTimePeriod());
                String tempTodate = DataSelectionUtil.getLastDateFromSemiannually(dto.getHisTimePeriod());
                String fromDate = CommonLogic.convertStringToDate(format.parse(tempFromDate).toString(), DEFAULT_JAVA_DATE_FORMAT.getConstant(), DEFAULT_SQL_DATE_FORMAT.getConstant());
                String toDate = CommonLogic.convertStringToDate(format.parse(tempTodate).toString(), DEFAULT_JAVA_DATE_FORMAT.getConstant(), DEFAULT_SQL_DATE_FORMAT.getConstant());
                query = " AND P.PERIOD_DATE between " + "'" + fromDate + "'" + " AND " + "'" + toDate + "' ";
            } else if (Constants.FrequencyConstants.QUARTERLY.getConstant().equals(frequecy)) {
                String tempFromDate = DataSelectionUtil.getDateFromQuarter(dto.getHisTimePeriod());
                String tempTodate = DataSelectionUtil.getLastDateFromQuarter(dto.getHisTimePeriod());
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

    /**
     *
     * @param dto
     * @return
     */
    private String getAsOfDate(BalanceReportDTO dto) {

        String query = StringUtils.EMPTY;
        try {
            String asOfDate = CommonLogic.convertStringToDate(dto.getAsOfDate(), DEFAULT_JAVA_DATE_FORMAT.getConstant(), DEFAULT_SQL_DATE_FORMAT.getConstant());
            query = " AND P.PERIOD_DATE <= '" + asOfDate + "' ";

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return query;
    }

    /**
     *
     * @param selectedColumns
     * @return
     */
    private List<String> getVariables(List<String> selectedColumns) {
        List<String> finalQuery = new ArrayList<String>();
        String getting = selectedColumns.get(1);
        String[] str = getting.split(",");
        String str1 = StringUtils.EMPTY;
        String str2 = StringUtils.EMPTY;
        String str3 = StringUtils.EMPTY;
        int size = str.length;
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                String s = "CURRENT_" + str[i];
                String ss = "PRIOR_" + str[i];
                str1 += str[i] + " AS " + s;
                str3 += str[i] + " AS " + ss;
                str2 += ",V." + s + ",V.PRIOR_" + str[i] + "," + "ISNULL(V.PRIOR_" + str[i] + ",0)" + "-" + "ISNULL(V." + s + ",0) AS VARIANCE_" + str[i];
            } else {
                String s = "CURRENT_" + str[i];
                String ss = "PRIOR_" + str[i];
                str1 += "," + str[i] + " AS " + s;
                str3 += "," + str[i] + " AS " + ss;
                str2 += ",V." + s + ",V.PRIOR_" + str[i] + "," + "ISNULL(V.PRIOR_" + str[i] + ",0)" + "-" + "ISNULL(V." + s + ",0) AS VARIANCE_" + str[i];
            }
        }
        finalQuery.add(str2);
        finalQuery.add(str1);
        finalQuery.add(str3);
        return finalQuery;
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
        }
        DataSelectionDTO dsdto = session.getDataSelectionDTO();
        try {
            append = getQueryOnView(dto);
            Map<String, String> inputMap = new HashMap<String, String>();
            frequencyQuery = getPeriodQuery(dto);
            selectedColumns = getColumnsToBeFetched(selectedVariables, dto);
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
    public List<BalanceReportDTO> getExpandLevelResult(Object lastParent, int start, int last, SessionDTO sessionDTO, BalanceReportDTO dto, List<String> selectedVariables, boolean b) {
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
        }
        DataSelectionDTO dsdto = sessionDTO.getDataSelectionDTO();
        try {
            append = getQueryOnView(dto);
            frequencyQuery = getPeriodQuery(dto);
            selectedColumns = getColumnsToBeFetched(selectedVariables, dto);
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
            results = (List) QueryUtils.executeSelectQuery(inputMap, dto.getQuery());
            resultList = getBalanceReportDto(results, dto, selectedVariables);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return resultList;
    }
}
