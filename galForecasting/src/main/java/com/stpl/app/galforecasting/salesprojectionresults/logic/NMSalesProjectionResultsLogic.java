/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.salesprojectionresults.logic;

import com.stpl.app.galforecasting.logic.*;
import com.stpl.app.galforecasting.dao.SalesProjectionDAO;
import com.stpl.app.galforecasting.dao.impl.SalesProjectionDAOImpl;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.app.galforecasting.dto.SalesProjectionResultsDTO;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.ui.form.DataSelectionForm;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.galforecasting.utils.HeaderUtils.getCommonColumnHeader;
import static com.stpl.app.galforecasting.utils.HeaderUtils.getCommonColumnHeaderSPR;
import static com.stpl.app.galforecasting.utils.HeaderUtils.getMonthForInt;
import static com.stpl.app.utils.Constants.FrequencyConstants.ANNUALLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUALLY;
import static com.stpl.app.utils.Constants.LabelConstants.*;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author Lokeshwari
 */
public class NMSalesProjectionResultsLogic {

    /**
     * The unit volume.
     */
    private static final DecimalFormat UNITVOLUME = new DecimalFormat("#,##0");
    /**
     * The dollar.
     */
    private static final DecimalFormat DOLLAR = new DecimalFormat("#");
    /**
     * The Currency Zero Decimal Places Format.
     */
    private static final DecimalFormat CUR_ZERO = new DecimalFormat("$#,##0");
    /**
     * The Percent Two Decimal Places Format.
     */
    private static final DecimalFormat PER_TWO = new DecimalFormat("0.00%");
    /**
     * The Numeric Zero Decimal Places Format.
     */
    private static final DecimalFormat TWO_DECIMAL = new DecimalFormat("#,##0.00");
    private static final String CURRENCY = "$";
    SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();

    List<SalesProjectionResultsDTO> projectionTotalList = new ArrayList<SalesProjectionResultsDTO>();
    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = Logger.getLogger(NMSalesProjectionResultsLogic.class);

    public List<List> generateSalesProjectionResults(Object[] selections, String salesOrUnits, String actualOrProj, List<Object> headerList, String pivotView) {
        LOGGER.info("generateSalesProjectionResults method starts");
        List sprList;
        List levelCount = new ArrayList();
        SalesProjectionResultsDTO sprDTO = new SalesProjectionResultsDTO();
        SalesProjectionResultsDTO salesDTO = new SalesProjectionResultsDTO();
        SalesProjectionResultsDTO unitsDTO = new SalesProjectionResultsDTO();
        String frequency = String.valueOf(selections[1]);
        List<SalesProjectionResultsDTO> resultList = new ArrayList<SalesProjectionResultsDTO>();
        List<SalesProjectionResultsDTO> salesList = new ArrayList<SalesProjectionResultsDTO>();
        List<SalesProjectionResultsDTO> unitList = new ArrayList<SalesProjectionResultsDTO>();
        List<List> finalList = new ArrayList<List>();
        try {
            levelCount = salesProjectionDAO.getSalesProjectionResultLevels(selections);
            sprList = salesProjectionDAO.getSalesProjectionResults(selections);
            if ("period".equalsIgnoreCase(pivotView)) {
                if (sprList != null && !sprList.isEmpty()) {
                    for (int j = 0; j < levelCount.size(); j++) {
                        sprDTO = new SalesProjectionResultsDTO();
                        final Object[] levelObj = (Object[]) levelCount.get(j);
                        sprDTO.addStringProperties("levelValue", String.valueOf(levelObj[1]));
                        sprDTO.setLevelNo(Integer.parseInt(String.valueOf(levelObj[0])));
                        sprDTO.setHierarchyNo(String.valueOf(levelObj[2]));
                        salesDTO = new SalesProjectionResultsDTO();
                        unitsDTO = new SalesProjectionResultsDTO();
                        for (int i = 0; i < sprList.size(); i++) {
                            final Object[] obj = (Object[]) sprList.get(i);
                            String commonColumn = StringUtils.EMPTY;
                            if (frequency.equalsIgnoreCase(QUARTERLY.getConstant())) {
                                commonColumn = Constant.Q + obj[3] + obj[2];
                            } else if (frequency.equalsIgnoreCase(SEMI_ANNUALLY.getConstant())) {
                                commonColumn = Constant.S + obj[3] + obj[2];
                            } else if (frequency.equalsIgnoreCase(MONTHLY.getConstant())) {
                                String monthName = getMonthForInt(Integer.valueOf(StringUtils.EMPTY + obj[3]) - 1);
                                commonColumn = monthName + obj[2];
                            } else {
                                commonColumn = StringUtils.EMPTY + obj[2];
                            }
                            if (Constant.SALES.equalsIgnoreCase(salesOrUnits) || Constant.BOTH_SMALL.equalsIgnoreCase(salesOrUnits)) {
                                salesDTO.setLevelValue("Contract Sales @ WAC");
                                salesDTO.addStringProperties(commonColumn + Constant.ACTUALS, obj[4] != null && !Constant.NULL.equals(String.valueOf(obj[4])) && !StringUtils.EMPTY.equals(String.valueOf(obj[4])) ? "$".concat(DOLLAR.format(Double.parseDouble(String.valueOf(obj[4])))) : "-");
                                if (headerList.contains(commonColumn + Constant.PROJECTIONS)) {
                                    salesDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, obj[6] != null && !Constant.NULL.equals(String.valueOf(obj[6])) && !StringUtils.EMPTY.equals(String.valueOf(obj[6])) ? "$".concat(DOLLAR.format(Double.parseDouble(String.valueOf(obj[6])))) : "-");
                                } else {
                                    salesDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, obj[8] != null && !Constant.NULL.equals(String.valueOf(obj[8])) && !StringUtils.EMPTY.equals(String.valueOf(obj[8])) ? "$".concat(DOLLAR.format(Double.parseDouble(String.valueOf(obj[8])))) : "-");
                                }
                            }
                            if (Constant.UNITS.equalsIgnoreCase(salesOrUnits) || Constant.BOTH_SMALL.equalsIgnoreCase(salesOrUnits)) {
                                unitsDTO.setLevelValue(Constant.UNIT_VOLUME);
                                unitsDTO.addStringProperties(commonColumn + Constant.ACTUALS, obj[5] != null && !Constant.NULL.equals(String.valueOf(obj[5])) && !StringUtils.EMPTY.equals(String.valueOf(obj[5])) ? UNITVOLUME.format(Double.parseDouble(String.valueOf(obj[5]))) : "-");
                                if (headerList.contains(commonColumn + Constant.PROJECTIONS)) {
                                    unitsDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, obj[7] != null && !StringUtils.EMPTY.equals(String.valueOf(obj[7])) && !StringUtils.EMPTY.equals(String.valueOf(obj[7])) ? UNITVOLUME.format(Double.parseDouble(String.valueOf(obj[7]))) : "-");
                                } else {
                                    unitsDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, obj[9] != null && !Constant.NULL.equals(String.valueOf(obj[9])) && !StringUtils.EMPTY.equals(String.valueOf(obj[9])) ? UNITVOLUME.format(Double.parseDouble(String.valueOf(obj[9]))) : "-");
                                }
                            }
                            salesList.add(salesDTO);
                            unitList.add(unitsDTO);
                        }

                        resultList.add(sprDTO);
                    }
                    finalList.add(resultList);
                    finalList.add(salesList);
                    finalList.add(unitList);
                }
            } else {
                final DataSourceConnection dataSourceConnection = DataSourceConnection.getInstance();
                Connection connection = null;
                CallableStatement statement = null;
                List gtsList = new ArrayList();
                try {
                    connection = dataSourceConnection.getConnection();
                    if (connection != null) {
                        statement = connection.prepareCall("{call PRC_PROJECTION_RESULTS (?,?,?,?,?)}");
                        statement.setObject(1, 278); //  @BASLINE_PERIODS 
                        statement.setObject(2, frequency);
                        statement.setObject(3, StringUtils.EMPTY);
                        statement.setObject(4, Integer.parseInt(String.valueOf(selections[8])));
                        statement.setObject(5, Integer.parseInt(String.valueOf(selections[7])));
                        ResultSet rs = statement.executeQuery();
                        gtsList = convertResultSetToList(rs);
                    }
                } catch (Exception e) {
                } finally {
                    try {

                        statement.close();
                    } catch (Exception e) {
                        LOGGER.error(e);
                    }
                    try {
                        connection.close();

                    } catch (Exception e) {
                        LOGGER.error(e);
                    }
                }
                if (sprList != null && !sprList.isEmpty()) {
                    List<List> list = getRowList(selections, salesOrUnits, actualOrProj, headerList, pivotView);
                    for (int j = 0; j < levelCount.size(); j++) {
                        sprDTO = new SalesProjectionResultsDTO();
                        final Object[] levelObj = (Object[]) levelCount.get(j);
                        sprDTO.addStringProperties("levelValue", String.valueOf(levelObj[1]));
                        sprDTO.setLevelNo(Integer.parseInt(String.valueOf(levelObj[0])));
                        unitsDTO = new SalesProjectionResultsDTO();
                        for (int i = 0; i < sprList.size(); i++) {
                            salesDTO = new SalesProjectionResultsDTO();
                            final Object[] obj = (Object[]) sprList.get(i);
                            String commonColumn = StringUtils.EMPTY;
                            if (frequency.equalsIgnoreCase(QUARTERLY.getConstant())) {
                                commonColumn = Constant.Q + obj[3] + obj[2];
                            } else if (frequency.equalsIgnoreCase(SEMI_ANNUALLY.getConstant())) {
                                commonColumn = Constant.S + obj[3] + obj[2];
                            } else if (frequency.equalsIgnoreCase(MONTHLY.getConstant())) {
                                String monthName = getMonthForInt(Integer.valueOf(StringUtils.EMPTY + obj[3]) - 1);
                                commonColumn = monthName + obj[2];
                            } else {
                                commonColumn = StringUtils.EMPTY + obj[2];
                            }
                            if (list.get(0).contains(commonColumn) || list.get(1).contains(commonColumn)) {
                                salesDTO.setLevelValue(commonColumn);
                                salesDTO.addStringProperties("cswActuals", obj[4] != null || obj[4] == StringUtils.EMPTY ? "$".concat(DOLLAR.format(Double.parseDouble(String.valueOf(obj[4])))) : "-");
                                salesDTO.addStringProperties("uvActuals", obj[5] != null || obj[5] == StringUtils.EMPTY ? UNITVOLUME.format(Double.parseDouble(String.valueOf(obj[5]))) : "-");
                                for (int k = 0; k < gtsList.size(); k++) {
                                    Object[] gtsObj = (Object[]) gtsList.get(k);
                                    String gtsCommonColumn = StringUtils.EMPTY;
                                    if (frequency.equalsIgnoreCase(QUARTERLY.getConstant())) {
                                        gtsCommonColumn = Constant.Q + gtsObj[5] + gtsObj[6];
                                    } else if (frequency.equalsIgnoreCase(SEMI_ANNUALLY.getConstant())) {
                                        gtsCommonColumn = Constant.S + gtsObj[5] + gtsObj[6];
                                    } else if (frequency.equalsIgnoreCase(MONTHLY.getConstant())) {
                                        String monthName = getMonthForInt(Integer.valueOf(StringUtils.EMPTY + gtsObj[4]) - 1);
                                        gtsCommonColumn = monthName + gtsObj[6];
                                    } else {
                                        gtsCommonColumn = StringUtils.EMPTY + gtsObj[6];
                                    }
                                    if (commonColumn.equalsIgnoreCase(gtsCommonColumn)) {
                                        if (list.get(0).contains(commonColumn)) {
                                            salesDTO.addStringProperties("gtsActuals", gtsObj[2] != null || gtsObj[2] == StringUtils.EMPTY ? "$".concat(DOLLAR.format(Double.parseDouble(String.valueOf(gtsObj[2])))) : "-");
                                        } else {
                                            salesDTO.addStringProperties("gtsProjections", gtsObj[2] != null || gtsObj[2] == StringUtils.EMPTY ? "$".concat(DOLLAR.format(Double.parseDouble(String.valueOf(gtsObj[2])))) : "-");
                                        }
                                    }
                                }
                                if (list.get(0).contains(commonColumn)) {
                                    salesDTO.addStringProperties("cswProjections", obj[6] != null || obj[6] == StringUtils.EMPTY ? "$".concat(DOLLAR.format(Double.parseDouble(String.valueOf(obj[6])))) : "-");
                                    salesDTO.addStringProperties("uvProjections", obj[7] != null || obj[7] == StringUtils.EMPTY ? UNITVOLUME.format(Double.parseDouble(String.valueOf(obj[7]))) : "-");
                                } else {
                                    salesDTO.addStringProperties("cswProjections", obj[8] != null || obj[8] == StringUtils.EMPTY ? "$".concat(DOLLAR.format(Double.parseDouble(String.valueOf(obj[8])))) : "-");
                                    salesDTO.addStringProperties("uvProjections", obj[9] != null || obj[9] == StringUtils.EMPTY ? UNITVOLUME.format(Double.parseDouble(String.valueOf(obj[9]))) : "-");
                                }
                                salesList.add(salesDTO);
                                unitList.add(unitsDTO);
                            }
                        }
                        resultList.add(sprDTO);
                    }
                    finalList.add(resultList);
                    finalList.add(salesList);
                    finalList.add(unitList);
                }
            }
            LOGGER.info("generateSalesProjectionResults method ends");
        } catch (SystemException se) {
            LOGGER.error(se);
        } catch (PortalException pe) {
            LOGGER.error(pe);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return finalList;
    }

    public List<SalesProjectionResultsDTO> getGTSResult(int projectionID, String sessionId, String userId, List<Object> headerList, Object[] selections, String pivotView) {
        LOGGER.info("getGTSResult method starts");
        final DataSourceConnection dataSourceConnection = DataSourceConnection.getInstance();
        Connection connection = null;
        CallableStatement statement = null;
        String frequency = String.valueOf(selections[1]);
        SalesProjectionResultsDTO gtsDTO = new SalesProjectionResultsDTO();
        List<SalesProjectionResultsDTO> gtsList = new ArrayList<SalesProjectionResultsDTO>();
        List list = new ArrayList();
        try {
            connection = dataSourceConnection.getConnection();
            if (connection != null) {
                statement = connection.prepareCall("{call PRC_PROJECTION_RESULTS (?,?,?,?,?)}");
                statement.setObject(1, projectionID); //  @BASLINE_PERIODS 
                statement.setObject(2, frequency);
                statement.setObject(3, StringUtils.EMPTY);
                statement.setObject(4, Integer.parseInt(sessionId));
                statement.setObject(5, Integer.parseInt(userId));
                ResultSet rs = statement.executeQuery();
                list = convertResultSetToList(rs);
                if (!list.isEmpty()) {
                    if ("period".equalsIgnoreCase(pivotView)) {
                        for (int i = 0; i < list.size(); i++) {
                            Object[] obj = (Object[]) list.get(i);
                            String commonColumn = StringUtils.EMPTY;
                            if (frequency.equalsIgnoreCase(QUARTERLY.getConstant())) {
                                commonColumn = Constant.Q + obj[4] + obj[5];
                            } else if (frequency.equalsIgnoreCase(SEMI_ANNUALLY.getConstant())) {
                                commonColumn = Constant.S + obj[3] + obj[6];
                            } else if (frequency.equalsIgnoreCase(MONTHLY.getConstant())) {
                                String monthName = getMonthForInt(Integer.valueOf(StringUtils.EMPTY + obj[4]) - 1);
                                commonColumn = monthName + obj[6];
                            } else {
                                commonColumn = StringUtils.EMPTY + obj[6];
                            }
                            gtsDTO.setLevelValue("Gross Trade Sales");
                            gtsDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, obj[2] != null && StringUtils.EMPTY.equals(String.valueOf(obj[2])) ? "$".concat(DOLLAR.format(Double.parseDouble(String.valueOf(obj[2])))) : "-");
                            gtsList.add(gtsDTO);
                        }
                    } else {
                        for (int i = 0; i < list.size(); i++) {
                            gtsDTO = new SalesProjectionResultsDTO();
                            Object[] obj = (Object[]) list.get(i);
                            String commonColumn = StringUtils.EMPTY;
                            if (frequency.equalsIgnoreCase(QUARTERLY.getConstant())) {
                                commonColumn = Constant.Q + obj[4] + obj[5];
                            } else if (frequency.equalsIgnoreCase(SEMI_ANNUALLY.getConstant())) {
                                commonColumn = Constant.S + obj[4] + obj[5];
                            } else if (frequency.equalsIgnoreCase(MONTHLY.getConstant())) {
                                String monthName = getMonthForInt(Integer.valueOf(StringUtils.EMPTY + obj[4]) - 1);
                                commonColumn = monthName + obj[5];
                            } else {
                                commonColumn = StringUtils.EMPTY + obj[5];
                            }
                            gtsDTO.addStringProperties("gtsProjections", "$".concat(DOLLAR.format(Double.parseDouble(String.valueOf(obj[2])))));
                            gtsList.add(gtsDTO);
                        }
                    }
                } else {
                    gtsDTO.setLevelValue("Gross Trade Sales");
                    gtsList.add(gtsDTO);
                }
            }
            LOGGER.info("getGTSResult method ends");
        } catch (SQLException e) {
            LOGGER.error(e);
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            try {

                statement.close();
            } catch (Exception e) {
                LOGGER.error(e);
            }
            try {
                connection.close();

            } catch (Exception e) {
                LOGGER.error(e);
            }
        }
        return gtsList;
    }

    private List<Object[]> convertResultSetToList(ResultSet rs) {
        LOGGER.info("convertResultSetToList method starts");
        List<Object[]> objList = new ArrayList<Object[]>();

        try {
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int columnCount = rsMetaData.getColumnCount();
            List<Object[]> result = new ArrayList<Object[]>();
            Object[] header = new Object[columnCount];
            for (int i = 1; i <= columnCount; ++i) {
                Object label = rsMetaData.getColumnLabel(i);
                header[i - 1] = label;
            }
            while (rs.next()) {
                Object[] str = new Object[columnCount];
                for (int i = 1; i <= columnCount; ++i) {
                    Object obj = rs.getObject(i);
                    str[i - 1] = obj;
                }
                objList.add(str);
            }
            LOGGER.info("convertResultSetToList method ends");
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOGGER.error(ex);
            }
        }
        return objList;
    }

    public List<List> getRowList(Object[] selections, String salesOrUnits, String actualOrProj, List<Object> headerList, String pivotView) {
        LOGGER.info("getRowList method starts");
        List<List> list = new ArrayList();
        List historyList = new ArrayList();
        List projectionList = new ArrayList();
        String freq = selections[1].toString();
        String hist = selections[4].toString();
        String projFreq = selections[4].toString();
        String projection = actualOrProj;
        String pivot = pivotView;

        Calendar ob = Calendar.getInstance();
        int curMonth = ob.get(Calendar.MONTH);
        int curDate = ob.get(Calendar.DATE);
        int curYear = ob.get(Calendar.YEAR);
        int current = 1;
        int frequency = 0;
        int projectFrequency = 0;
        int division = 1;
        if (freq.equals(QUARTERLY.getConstant())) {
            current = (curMonth / 3);
            division = 4;
            try {
                frequency = Integer.valueOf(hist.replace("Quarter", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
                projectFrequency = Integer.valueOf(projFreq.replace("Quarter", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
            } catch (NumberFormatException e) {
            }
        } else if (freq.equals(SEMI_ANNUALLY.getConstant())) {
            current = (curMonth / 6);
            division = 2;
            try {
                frequency = Integer.valueOf(hist.replace("Semi-Annual", StringUtils.EMPTY).trim());
                projectFrequency = Integer.valueOf(projFreq.replace("Semi-Annual", StringUtils.EMPTY).trim());
            } catch (NumberFormatException e) {
            }
        } else if (freq.equals(MONTHLY.getConstant())) {
            current = curMonth;
            division = 12;
            try {
                frequency = Integer.valueOf(hist.replace("Month", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
                projectFrequency = Integer.valueOf(projFreq.replace("Month", StringUtils.EMPTY).replace(Constant.S_SMALL, StringUtils.EMPTY).trim());
            } catch (NumberFormatException e) {
            }
        } else if (freq.equals(ANNUALLY.getConstant())) {
            current = curYear;
            division = 1;
            try {
                frequency = Integer.valueOf(hist.replace(Constant.YEAR, StringUtils.EMPTY).trim());
                projectFrequency = Integer.valueOf(projFreq.replace(Constant.YEAR, StringUtils.EMPTY).trim());
            } catch (NumberFormatException e) {
            }
        }
        projectFrequency = projectFrequency + 1;
        int pastYear = curYear;

        int startFreq = current + 1;

        int tempFreq = frequency - current;
        if (tempFreq > 0) {
            pastYear = pastYear - tempFreq / division;
            startFreq = 1;
            if (tempFreq % division > 0) {
                pastYear = pastYear - 1;
                startFreq = division - (tempFreq % division) + 1;
            }
        } else {
            startFreq = startFreq - frequency;
        }

        int squr = startFreq;
        int syear = pastYear;
        if (freq.contains(ANNUALLY.getConstant()) && !freq.contains(SEMI_ANNUALLY.getConstant())) {
            syear = current - frequency;
        }
        for (int i = 0; i < frequency; i++) {
            List<Object> dmap = new ArrayList<Object>();
            String commonColumn = StringUtils.EMPTY;
            String commonHeader = StringUtils.EMPTY;
            if (freq.contains(QUARTERLY.getConstant())) {
                commonColumn = Constant.Q + squr + StringUtils.EMPTY + syear;
                commonHeader = Constant.Q + squr + " " + syear;
            } else if (freq.contains(SEMI_ANNUALLY.getConstant())) {
                commonColumn = Constant.S + squr + StringUtils.EMPTY + syear;
                commonHeader = Constant.S + squr + " " + syear;
            } else if (freq.contains(ANNUALLY.getConstant())) {
                commonColumn = StringUtils.EMPTY + syear;
                commonHeader = StringUtils.EMPTY + syear;
            } else if (freq.contains(MONTHLY.getConstant())) {
                String monthName = getMonthForInt(squr - 1);
                commonColumn = monthName + syear;
                commonHeader = monthName + " " + syear;
            }
            squr++;
            if (squr > division) {
                squr = 1;
                syear++;
            }
            historyList.add(commonColumn);
        }
        squr = current + 1;
        for (int i = 0; i < projectFrequency; i++) {
            List<Object> dmap = new ArrayList<Object>();
            String commonColumn = StringUtils.EMPTY;
            String commonHeader = StringUtils.EMPTY;
            if (freq.contains(QUARTERLY.getConstant())) {
                commonColumn = Constant.Q + squr + StringUtils.EMPTY + syear;
                commonHeader = Constant.Q + squr + " " + syear;
            } else if (freq.contains(SEMI_ANNUALLY.getConstant())) {
                commonColumn = Constant.S + squr + StringUtils.EMPTY + syear;
                commonHeader = Constant.S + squr + " " + syear;
            } else if (freq.contains(ANNUALLY.getConstant())) {
                commonColumn = StringUtils.EMPTY + syear;
                commonHeader = StringUtils.EMPTY + syear;
            } else if (freq.contains(MONTHLY.getConstant())) {
                String monthName = getMonthForInt(squr - 1);
                commonColumn = monthName + syear;
                commonHeader = monthName + " " + syear;
            }
            squr++;
            if (squr > division) {
                squr = 1;
                syear++;
            }
            projectionList.add(commonColumn);
        }
        list.add(historyList);
        list.add(projectionList);
        LOGGER.info("getRowList method ends");
        return list;
    }

    public List<SalesProjectionResultsDTO> getConfiguredSalesProjectionResults(Object parentId, int start, int offset, ProjectionSelectionDTO projSelDTO) {
        List<SalesProjectionResultsDTO> resultList = new ArrayList<SalesProjectionResultsDTO>();
        if (!projSelDTO.isIsFilter() || (parentId instanceof SalesProjectionResultsDTO)) {
            projSelDTO.setYear(Constant.All);

            if (projSelDTO.getActualsOrProjections().equals(Constant.BOTH)) {
                projSelDTO.setActualsOrProjections("Actuals and Projections");
            }

            if (parentId instanceof SalesProjectionResultsDTO) {
                projSelDTO.setIsProjectionTotal(false);
                SalesProjectionResultsDTO parentDto = (SalesProjectionResultsDTO) parentId;
                projSelDTO.setLevelNo(parentDto.getLevelNo());
                projSelDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
                projSelDTO.setLevelValue(parentDto.getLevelValue());
                projSelDTO.setParentNode(parentDto.getParentNode());
                projSelDTO.setHierarchyNo(parentDto.getHierarchyNo());
                if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equalsIgnoreCase(parentDto.getHierarchyIndicator())) {
                    projSelDTO.setCustomerHierarchyNo(projSelDTO.getHierarchyNo());
                    projSelDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
                } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equalsIgnoreCase(parentDto.getHierarchyIndicator())) {
                    projSelDTO.setProductHierarchyNo(projSelDTO.getHierarchyNo());
                    projSelDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                }
                projSelDTO.setHierarchyIndicator(parentDto.getHierarchyIndicator());
                projSelDTO.setGroup(parentDto.getGroup());
                projSelDTO.setIsTotal(parentDto.getOnExpandTotalRow() == 1);
            } else {
                if (projSelDTO.getGroupFilter() == null || projSelDTO.getGroupFilter().isEmpty()) {
                    projSelDTO.setGroupFilter("All Sales Groups");
                }

                projSelDTO.setIsProjectionTotal(true);
                if (projSelDTO.isIsCustomHierarchy()) {
                    projSelDTO.setLevelNo(0);
                    projSelDTO.setTreeLevelNo(0);
                } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                    projSelDTO.setTreeLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(projSelDTO.getProductLevelNo() - 1);
                    projSelDTO.setTreeLevelNo(projSelDTO.getProductLevelNo() - 1);
                }
                projSelDTO.setLevelValue(StringUtils.EMPTY);
                projSelDTO.setHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setProductHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
            }
            resultList = getSalesProjectionResults(start, offset, projSelDTO);
        } else {
            projSelDTO.setIsProjectionTotal(false);
            projSelDTO.setLevelNo(projSelDTO.getFilterLevelNo());
            projSelDTO.setTreeLevelNo(projSelDTO.getFilterLevelNo());
            resultList = configureLevels(start, offset, start, projSelDTO);
        }
        return resultList;
    }

    public List<SalesProjectionResultsDTO> getConfiguredSalesProjectionResultsTotal(int start, int offset, ProjectionSelectionDTO projSelDTO) {
        List<SalesProjectionResultsDTO> resultList = new ArrayList<SalesProjectionResultsDTO>();
        projSelDTO.setIsProjectionTotal(true);
        if (!projSelDTO.isIsFilter()) {
            projSelDTO.setYear(Constant.All);

            if (projSelDTO.getActualsOrProjections().equals(Constant.BOTH)) {
                projSelDTO.setActualsOrProjections("Actuals and Projections");
            }
            if (Constant.ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
                projSelDTO.setFrequencyDivision(1);
            } else if (Constant.SEMIANNUALLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
                projSelDTO.setFrequencyDivision(2);
            } else if (Constant.QUARTERLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
                projSelDTO.setFrequencyDivision(4);
            } else if (Constant.MONTHLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
                projSelDTO.setFrequencyDivision(12);
            }
            resultList = getSalesProjectionResultsTotal(start, offset, projSelDTO);
        }

        return resultList;
    }

    public List<SalesProjectionResultsDTO> configureLevels(int start, int offset, int started, ProjectionSelectionDTO projSelDTO) {
        int neededRecord = offset;

        List<SalesProjectionResultsDTO> resultList = new ArrayList<SalesProjectionResultsDTO>();
        if (neededRecord > 0) {
            List<Leveldto> levelList = CommonLogic.getConditionalLevelList(projSelDTO.getProjectionId(), start, offset, projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo(), projSelDTO.getHierarchyNo(), projSelDTO.getProductHierarchyNo(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.isIsFilter(), false, projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomId(), projSelDTO.getGroupFilter(), projSelDTO.getUserId(), projSelDTO.getSessionId(), projSelDTO.getCustRelationshipBuilderSid(), projSelDTO.getProdRelationshipBuilderSid(), false, true);

            for (int i = 0; i < levelList.size() && neededRecord > 0; i++) {
                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + (started + i))) {
                    Leveldto levelDto = levelList.get(i);

                    SalesProjectionResultsDTO dto = new SalesProjectionResultsDTO();
                    dto.setLevelNo(levelDto.getLevelNo());
                    dto.setTreeLevelNo(levelDto.getTreeLevelNo());
                    dto.setParentNode(levelDto.getParentNode());
                    dto.setGroup(projSelDTO.getSessionDTO().getLevelValueDiscription(levelDto.getHierarchyNo(), levelDto.getHierarchyIndicator()));
                    dto.setLevelValue(projSelDTO.getSessionDTO().getLevelValueDiscription(levelDto.getHierarchyNo(), levelDto.getHierarchyIndicator()));
                    dto.setHierarchyNo(levelDto.getHierarchyNo());
                    dto.setHierarchyIndicator(levelDto.getHierarchyIndicator());
                    if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                        dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                        dto.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
                    } else if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                        dto.setProductHierarchyNo(dto.getHierarchyNo());
                        dto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
                    }
                    dto.setOnExpandTotalRow(1);
                    dto.setParent(1);
                    resultList.add(dto);
                }
                started++;
                neededRecord--;
            }
        }
        return resultList;
    }

    public List<SalesProjectionResultsDTO> getSalesProjectionResultsTotal(int start, int offset, ProjectionSelectionDTO projSelDTO) {

        int neededRecord = offset;
        int mayBeAdded = 0;
        List<SalesProjectionResultsDTO> projDTOList = new ArrayList<SalesProjectionResultsDTO>();
        String discList = CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false);
        String freq = StringUtils.EMPTY;
        if (projSelDTO.getFrequencyDivision() == 1 || Constant.ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
            freq = "ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 2 || Constant.SEMI_ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
            freq = "SEMI-ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 4 || Constant.QUARTERLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
            freq = "QUARTERLY";
        } else if (projSelDTO.getFrequencyDivision() == 12 || Constant.MONTHLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
            freq = "MONTHLY";
        }

        Object[] orderedArgs = {projSelDTO.getProjectionId(), freq, discList, "ASSUMPTIONS", projSelDTO.getSessionId(), projSelDTO.getUserId()};
        if (start < 1) {
            SalesProjectionResultsDTO dto = new SalesProjectionResultsDTO();
            dto.setLevelValue(Constant.PROJECTION_TOTAL);
            dto.setParent(0);
            projDTOList.add(dto);
            neededRecord--;
        }
        mayBeAdded++;
        if (neededRecord > 0 && projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
            SalesProjectionResultsDTO contractSalesDto = null;
            SalesProjectionResultsDTO unitVolDto = null;
            SalesProjectionResultsDTO gtsDto = projectionTotalList.get(0);
            contractSalesDto = projectionTotalList.get(1);
            unitVolDto = projectionTotalList.get(2);
            if (start < 2 && neededRecord > 0) {
                if (gtsDto != null) {
                    projDTOList.add(gtsDto);
                    neededRecord--;
                }
            }
            String salesUnits = projSelDTO.getSalesOrUnit();
            if (neededRecord > 0 && (salesUnits.equals(Constant.BOTH) || salesUnits.equals(Constant.SALES_SMALL))) {
                if (start < 3) {
                    if (contractSalesDto != null) {
                        projDTOList.add(contractSalesDto);
                        neededRecord--;
                    }
                }
            }
            if (neededRecord > 0 && (salesUnits.equals(Constant.BOTH) || salesUnits.equals(Constant.UNITS_SMALL))) {
                if ((salesUnits.equals(Constant.BOTH) && start < 4) || (start < 3)) {
                    if (unitVolDto != null) {
                        projDTOList.add(unitVolDto);
                        neededRecord--;
                    }
                }
            }

        } else {
            int mayBeAddedRecord = start - mayBeAdded;
            if (mayBeAddedRecord < 0) {
                mayBeAddedRecord = 0;
            }
            List<SalesProjectionResultsDTO> projectionDtoList = getProjectionPivotTotal(orderedArgs, projSelDTO);
            for (int k = mayBeAddedRecord; k < projectionDtoList.size() && neededRecord > 0; k++) {
                projDTOList.add(projectionDtoList.get(k));
                neededRecord--;
            }
        }
        return projDTOList;
    }

    public List<SalesProjectionResultsDTO> getSalesProjectionResults(int start, int offset, ProjectionSelectionDTO projSelDTO) {

        int neededRecord = offset;
        int started = start;
        int mayBeAdded = 0;
        String discList = CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false);
        String freq = StringUtils.EMPTY;
        if (projSelDTO.getFrequencyDivision() == 1 || Constant.ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
            freq = "ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 2 || Constant.SEMI_ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
            freq = "SEMI-ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 4 || Constant.QUARTERLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
            freq = "QUARTERLY";
        } else if (projSelDTO.getFrequencyDivision() == 12 || Constant.MONTHLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
            freq = "MONTHLY";
        }

        Object[] orderedArgs = {projSelDTO.getProjectionId(), freq, discList, "ASSUMPTIONS", projSelDTO.getSessionId(), projSelDTO.getUserId()};
        List<SalesProjectionResultsDTO> projDTOList = new ArrayList<SalesProjectionResultsDTO>();
        if (!projSelDTO.getLevelValue().startsWith(Constant.All)
                && !projSelDTO.getLevelValue().contains(Constant.Sales)) {
            if (projSelDTO.isIsProjectionTotal()) {
                if (started == 0) {
                    if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                        SalesProjectionResultsDTO dto = new SalesProjectionResultsDTO();
                        dto.setLevelValue(Constant.PROJECTION_TOTAL);
                        dto.setParent(0);
                        projDTOList.add(dto);
                    }
                    neededRecord--;
                    started++;
                }
                mayBeAdded++;
            }
            if (neededRecord > 0 && projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
                String salesUnits = projSelDTO.getSalesOrUnit();
                if (projSelDTO.isIsProjectionTotal()) {
                    mayBeAdded++;
                    if (projectionTotalList.isEmpty()) {
                        getProjectionTotal(orderedArgs, projSelDTO);
                    }
                    if (started == 1 && neededRecord > 0) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            SalesProjectionResultsDTO exFactorySalesDTO = projectionTotalList.get(0);
                            projDTOList.add(exFactorySalesDTO);
                        }
                        started++;
                        neededRecord--;
                    }
                    if (neededRecord > 0 && (salesUnits.equals(BOTH.getConstant()) || salesUnits.equals(SALES.getConstant()))) {
                        if (started == 2) {
                            if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                SalesProjectionResultsDTO demandSalesDTO = projectionTotalList.get(1);
                                projDTOList.add(demandSalesDTO);
                            }
                            started++;
                            neededRecord--;
                            mayBeAdded++;
                        }
                        if (started == 3) {
                            if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                SalesProjectionResultsDTO inventoryWithdrawDTO = projectionTotalList.get(2);
                                projDTOList.add(inventoryWithdrawDTO);
                            }
                            started++;
                            neededRecord--;
                            mayBeAdded++;
                        }
                        if (started == 4) {
                            if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                SalesProjectionResultsDTO contractSalesDto = projectionTotalList.get(3);
                                projDTOList.add(contractSalesDto);
                            }
                            started++;
                            neededRecord--;
                            mayBeAdded++;
                        }
                    }
                    if (neededRecord > 0 && (salesUnits.equals(BOTH.getConstant()) || salesUnits.equals(UNITS.getConstant()))) {
                        if ((salesUnits.equals(BOTH.getConstant()) && started == 5) || started == 2) {
                            if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                SalesProjectionResultsDTO unitVolDto = projectionTotalList.get(4);
                                projDTOList.add(unitVolDto);
                            }
                            started++;
                            neededRecord--;
                        }
                        mayBeAdded++;
                    }
                } else if (neededRecord > 0) {
                    SalesProjectionResultsDTO contractSalesDto = null;
                    SalesProjectionResultsDTO unitVolDto = null;
                    if ((salesUnits.equals(BOTH.getConstant()) && started < 2) || started < 1) {
                        List<SalesProjectionResultsDTO> contractSalesAndUnits = getContractSalesAndUnits(projSelDTO);
                        contractSalesDto = contractSalesAndUnits.get(0);
                        unitVolDto = contractSalesAndUnits.get(1);
                    }
                    if (salesUnits.equals(BOTH.getConstant()) || salesUnits.equals(SALES.getConstant())) {
                        if (started == 0) {
                            if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                projDTOList.add(contractSalesDto);
                            }
                            started++;
                            neededRecord--;
                        }
                        mayBeAdded++;
                    }
                    if (neededRecord > 0 && (salesUnits.equals(BOTH.getConstant()) || salesUnits.equals(UNITS.getConstant()))) {
                        if ((salesUnits.equals(BOTH.getConstant()) && started == 1) || started == 0) {
                            if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                projDTOList.add(unitVolDto);
                            }
                            started++;
                            neededRecord--;
                        }
                        mayBeAdded++;
                    }
                }
            } else if (neededRecord > 0) {
                int mayBeAddedRecord = started - mayBeAdded;
                if (mayBeAddedRecord < 0) {
                    mayBeAddedRecord = 0;
                }
                if (mayBeAddedRecord < projSelDTO.getPeriodList().size()) {
                    List<SalesProjectionResultsDTO> projectionDtoList = new ArrayList<SalesProjectionResultsDTO>();
                    projSelDTO.setProjTabName("SPR");
                    if (projSelDTO.isIsProjectionTotal()) {
                        projectionDtoList = getProjectionPivotTotal(orderedArgs, projSelDTO);
                    } else {
                        projectionDtoList = getProjectionPivot(projSelDTO);
                    }
                    projSelDTO.setProjTabName(StringUtils.EMPTY);
                    for (int k = mayBeAddedRecord; k < projectionDtoList.size() && neededRecord > 0; k++) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + k)) {
                            projDTOList.add(projectionDtoList.get(k));
                        }
                        started++;
                        neededRecord--;
                    }
                }
                mayBeAdded += projSelDTO.getPeriodList().size();
            }
        }

        if (neededRecord > 0 && !projSelDTO.isIsFilter()) {
            if ((projSelDTO.getTreeLevelNo() + 1) == projSelDTO.getTpLevel()
                    && ((projSelDTO.isIsCustomHierarchy()) || (!projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)))
                    && !projSelDTO.getLevelValue().startsWith(Constant.All)
                    && !projSelDTO.getLevelValue().contains(Constant.Sales)) {
                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                    SalesProjectionResultsDTO dto = new SalesProjectionResultsDTO();
                    dto.setLevelNo(projSelDTO.getLevelNo());
                    dto.setTreeLevelNo(projSelDTO.getTreeLevelNo());
                    dto.setParentNode(projSelDTO.getParentNode());
                    dto.setGroup(projSelDTO.getGroupFilter());
                    dto.setLevelValue(projSelDTO.getGroupFilter());
                    dto.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
                    dto.setHierarchyNo(projSelDTO.getHierarchyNo());
                    if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                        dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                        dto.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
                    } else if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                        dto.setProductHierarchyNo(dto.getHierarchyNo());
                        dto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
                    }
                    dto.setOnExpandTotalRow(1);
                    dto.setParent(1);
                    projDTOList.add(dto);
                }
                started++;
                neededRecord--;
            } else {
                int mayBeAddedRecord = started - mayBeAdded;
                if (mayBeAddedRecord < 0) {
                    mayBeAddedRecord = 0;
                }
                projSelDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo() + 1);
                List<SalesProjectionResultsDTO> nextLevelValueList = configureLevels(mayBeAddedRecord, neededRecord, started, projSelDTO);
                projDTOList.addAll(nextLevelValueList);
            }
        }
        return projDTOList;
    }

    public void getProjectionTotal(Object[] orderedArgs, ProjectionSelectionDTO projSelDTO) {
        if (projSelDTO.getScreenName().equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
            LOGGER.info("Enetring getProjectionTotal NonMandated");
            List<Object[]> gtsList = CommonLogic.callProcedure("PRC_PROJECTION_RESULTS", orderedArgs);
            if (gtsList != null) {
                getCustomizedProjectionTotal(gtsList, projSelDTO);
            }
            LOGGER.info("Ending getProjectionTotal NonMandated");
        } else if (projSelDTO.getScreenName().equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
            LOGGER.info("Entering getProjectionTotal Mandated");
            List<Object[]> gtsList = SPRCommonLogic.callProcedure("PRC_M_PROJECTION_RESULTS", orderedArgs);
            if (gtsList != null) {
                projectionTotalList = getCustomizedProjectionTotalMandated(gtsList, projSelDTO);
            }
            gtsList = null;
            LOGGER.info("Ending getProjectionTotal Mandated");
        } else if (projSelDTO.getScreenName().equals(CommonUtils.BUSINESS_PROCESS_TYPE_CHANNELS)) {
            LOGGER.info("Entering getProjectionTotal Channel");
            List<Object[]> gtsList = SPRCommonLogic.callProcedure("PRC_CH_PROJECTION_RESULTS", orderedArgs);

            if (gtsList != null) {

                getCustomizedProjectionTotalChannel(gtsList, projSelDTO, false);
            }
            LOGGER.info("Ending getProjectionTotal Channel");
        }
    }

    public void getCustomizedProjectionTotal(List<Object[]> list, ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("getCustomizedProjectionTotal() starts");
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        String salesOrUnits = projSelDTO.getSalesOrUnit();
        List<SalesProjectionResultsDTO> projDTOList = new ArrayList<SalesProjectionResultsDTO>();
        SalesProjectionResultsDTO exFactorySalesDTO = new SalesProjectionResultsDTO();
        SalesProjectionResultsDTO demandSalesDTO = new SalesProjectionResultsDTO();
        SalesProjectionResultsDTO inventoryWithdrawDTO = new SalesProjectionResultsDTO();
        SalesProjectionResultsDTO conSaleDTO = new SalesProjectionResultsDTO();
        SalesProjectionResultsDTO unitVolDTO = new SalesProjectionResultsDTO();
        exFactorySalesDTO.setParent(0);
        exFactorySalesDTO.setLevelValue(EX_FACTORY_SALES.getConstant());

        demandSalesDTO.setParent(0);
        demandSalesDTO.setLevelValue(DEMAND_SALES.getConstant());

        inventoryWithdrawDTO.setParent(0);
        inventoryWithdrawDTO.setLevelValue(INVENTORY_WITHDRAW.getConstant());

        conSaleDTO.setParent(0);
        conSaleDTO.setLevelValue(CONTRACT_SALES_AT_WAC.getConstant());

        unitVolDTO.setParent(0);
        unitVolDTO.setLevelValue(UNIT_VOL.getConstant());

        if (list != null && !list.isEmpty()) {
            int col = 5;
            if (frequencyDivision != 1) {
                col = col + 1;
            }
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column = StringUtils.EMPTY;

                int year = Integer.valueOf(String.valueOf(obj[col - 1]));
                int period = Integer.valueOf(String.valueOf(obj[4]));
                List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = common.get(0);

                String exFactoryActual = StringUtils.EMPTY + obj[1];
                exFactoryActual = getFormatValue(TWO_DECIMAL, exFactoryActual, CURRENCY);
                exFactorySalesDTO.addStringProperties(commonColumn + Constant.ACTUALS, exFactoryActual);
                String exFactoryProjection = StringUtils.EMPTY + obj[2];
                exFactoryProjection = getFormatValue(TWO_DECIMAL, exFactoryProjection, CURRENCY);
                exFactorySalesDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, exFactoryProjection);

                if (Constant.SALES.equalsIgnoreCase(salesOrUnits) || Constant.BOTH_SMALL.equalsIgnoreCase(salesOrUnits)) {

                    String demandActual = StringUtils.EMPTY + obj[22];
                    demandActual = getFormatValue(TWO_DECIMAL, demandActual, CURRENCY);
                    demandSalesDTO.addStringProperties(commonColumn + Constant.ACTUALS, demandActual);
                    String demandProjection = StringUtils.EMPTY + obj[23];
                    demandProjection = getFormatValue(TWO_DECIMAL, demandProjection, CURRENCY);
                    demandSalesDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, demandProjection);

                    String inventoryWithdrawActual = StringUtils.EMPTY + obj[24];
                    inventoryWithdrawActual = getFormatValue(TWO_DECIMAL, inventoryWithdrawActual, CURRENCY);
                    inventoryWithdrawDTO.addStringProperties(commonColumn + Constant.ACTUALS, inventoryWithdrawActual);
                    String inventoryWithdrawProjection = StringUtils.EMPTY + obj[25];
                    inventoryWithdrawProjection = getFormatValue(TWO_DECIMAL, inventoryWithdrawProjection, CURRENCY);
                    inventoryWithdrawDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, inventoryWithdrawProjection);

                    String cswActuals = StringUtils.EMPTY + obj[col];
                    cswActuals = getFormatValue(TWO_DECIMAL, cswActuals, CURRENCY);
                    conSaleDTO.addStringProperties(commonColumn + Constant.ACTUALS, cswActuals);
                    String cswProjections = StringUtils.EMPTY + obj[col + 1];
                    cswProjections = getFormatValue(TWO_DECIMAL, cswProjections, CURRENCY);
                    conSaleDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, cswProjections);
                }
                if (Constant.UNITS.equalsIgnoreCase(salesOrUnits) || Constant.BOTH_SMALL.equalsIgnoreCase(salesOrUnits)) {
                    String uvActuals = StringUtils.EMPTY + obj[col + 2];
                    uvActuals = getFormattedValue(UNITVOLUME, uvActuals);
                    unitVolDTO.addStringProperties(commonColumn + Constant.ACTUALS, uvActuals);
                    String uvProjections = StringUtils.EMPTY + obj[col + 3];
                    uvProjections = getFormattedValue(UNITVOLUME, uvProjections);
                    unitVolDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, uvProjections);
                }
            }

        }
        projectionTotalList.clear();
        projectionTotalList.add(exFactorySalesDTO);
        projectionTotalList.add(demandSalesDTO);
        projectionTotalList.add(inventoryWithdrawDTO);
        projectionTotalList.add(conSaleDTO);
        projectionTotalList.add(unitVolDTO);
    }

    public String getFormattedValue(DecimalFormat FORMAT, String value) {
        if (value.contains(Constant.NULL)) {
            value = SPRDASH.getConstant();
        } else {
            value = FORMAT.format(Double.valueOf(value));
        }
        return value;
    }

    public List<SalesProjectionResultsDTO> getContractSalesAndUnits(ProjectionSelectionDTO projSelDTO) {
        List<SalesProjectionResultsDTO> projDTOList = new ArrayList<SalesProjectionResultsDTO>();
        if (projSelDTO.getScreenName().equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
            LOGGER.info("Entering getContractSalesAndUnits NonMandated");
            projSelDTO.setSales(Constant.SALES_WHOLE_CAPS);
            List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(CommonLogic.getCCPQuery(projSelDTO,Boolean.FALSE) + " \n" + getSalesProjectionResultsSalesQuery(projSelDTO), null, null);
            projDTOList = getCustomizedSalesProjectionResultsSales(list, projSelDTO);
            LOGGER.info("Ending getContractSalesAndUnits NonMandated");
        } else if (projSelDTO.getScreenName().equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
            LOGGER.info("Entering getContractSalesAndUnitsMandated");
            projSelDTO.setSales(Constant.SALES_WHOLE_CAPS);
            List<Object> list = (List<Object>) SPRCommonLogic.executeSelectQuery(getSalesProjectionResultsSalesQueryMandated(projSelDTO), null, null);
            projDTOList = getCustomizedSalesProjectionResultsSalesMandated(list, projSelDTO);
            list = null;
            LOGGER.info("Ends getContractSalesAndUnitsMandated");
        } else if (projSelDTO.getScreenName().equals(CommonUtils.BUSINESS_PROCESS_TYPE_CHANNELS)) {
            LOGGER.info("Entering getContractSalesAndUnits Channel");
            projSelDTO.setSales(Constant.SALES_WHOLE_CAPS);
            List<Object> list = (List<Object>) SPRCommonLogic.executeSelectQuery(SPRCommonLogic.getCCPQueryChannel(projSelDTO) + " \n" + getSalesProjectionResultsSalesQueryChannel(projSelDTO), null, null);
            projDTOList = getCustomizedSalesProjectionResultsSalesChannel(list, projSelDTO, false);
            LOGGER.info("Ending getContractSalesAndUnits Channel");
        }
        return projDTOList;
    }

    public String getCCPWhereConditionQuery(String relationShipLevelDefination, String projectionDetails, String CCP) {
        String ccpWhereCond = " and " + relationShipLevelDefination + ".RELATIONSHIP_LEVEL_SID =" + CCP + ".RELATIONSHIP_LEVEL_SID and " + CCP + ".CCP_DETAILS_SID=" + projectionDetails + ".CCP_DETAILS_SID ";
        return ccpWhereCond;
    }

    public String getPeriodRestrictionQuery(ProjectionSelectionDTO projSelDTO) {
        String endDate = String.format("%04d", projSelDTO.getEndYear()) + "-"
                + String.format("%02d", projSelDTO.getEndMonth()) + "-"
                + String.format("%02d", projSelDTO.getEndDay());

        String startDate = String.format("%04d", projSelDTO.getStartYear()) + "-"
                + String.format("%02d", projSelDTO.getStartMonth()) + "-"
                + String.format("%02d", projSelDTO.getStartDay());
        String periodFilter = StringUtils.EMPTY;
        if (!CommonUtils.isInteger(projSelDTO.getYear())) {

            periodFilter = " and PERIOD_DATE BETWEEN CONVERT(DATE, '" + startDate + "') and CONVERT(DATE, '" + endDate + "') ";
        }
        return periodFilter;
    }

    public String getUserSessionQueryCondition(int userId, int sessionId, String table) {
        String user = " and " + table + ".USER_ID=" + userId + " and " + table + ".SESSION_ID=" + sessionId + " ";
        return user;
    }

    public String getCCPQuery(ProjectionSelectionDTO projSelDTO) {
        String viewtable = CommonLogic.getViewTableName(projSelDTO.getHierarchyIndicator());
        String ccpQuery = StringUtils.EMPTY;
        if (projSelDTO.isIsCustomHierarchy()) {
            ccpQuery = getCCPQueryCustom(projSelDTO);
        } else {
            ccpQuery = " (SELECT LCCP.RELATIONSHIP_LEVEL_SID, LCCP.CCP_DETAILS_SID, LCCP.HIERARCHY_NO from "
                    + "(SELECT CCPMAP.CCP_DETAILS_SID, HLD.HIERARCHY_NO, HLD.RELATIONSHIP_LEVEL_SID from "
                    + "(SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID "
                    + "FROM RELATIONSHIP_LEVEL_DEFINITION RLD "
                    + "JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID "
                    + "JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId()
                    + " JOIN " + viewtable + " PCH1 ON  RLD.RELATIONSHIP_LEVEL_SID=PCH1.RELATIONSHIP_LEVEL_SID"
                    + " JOIN PROJECTION_MASTER PM  ON PCH1.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID"
                    + " WHERE PM.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + ") CCPMAP,"
                    + " (SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID"
                    + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD1"
                    + " JOIN " + viewtable + " PCH "
                    + " ON PCH.RELATIONSHIP_LEVEL_SID=RLD1.RELATIONSHIP_LEVEL_SID"
                    + " AND PCH.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId()
                    + " WHERE RLD1.HIERARCHY_NO like '" + projSelDTO.getHierarchyNo() + "%' ) HLD"
                    + " WHERE CCPMAP.HIERARCHY_NO like HLD.HIERARCHY_NO+'%' ) LCCP"
                    + " WHERE LCCP.HIERARCHY_NO in"
                    + " (SELECT RLD2.HIERARCHY_NO"
                    + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD2"
                    + " JOIN " + viewtable + " PCH2"
                    + " ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID"
                    + " AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId()
                    + " WHERE RLD2.LEVEL_NO=" + projSelDTO.getLevelNo() + ")) CCP ";
        }
        return ccpQuery;
    }

    public List<SalesProjectionResultsDTO> getCustomizedSalesProjectionResultsSales(List<Object> list, ProjectionSelectionDTO projSelDTO) {
        List<SalesProjectionResultsDTO> projDtoList = new ArrayList<SalesProjectionResultsDTO>();
        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        columnList.remove("levelValue");
        SalesProjectionResultsDTO projSalesDTO = new SalesProjectionResultsDTO();
        SalesProjectionResultsDTO projUnitDTO = new SalesProjectionResultsDTO();
        projSalesDTO.setLevelNo(projSelDTO.getLevelNo());
        projSalesDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        projSalesDTO.setLevelValue(CONTRACT_SALES_AT_WAC.getConstant());
        projUnitDTO.setLevelNo(projSelDTO.getLevelNo());
        projUnitDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());

        projUnitDTO.setLevelValue(UNIT_VOL.getConstant());
        projSalesDTO.setParent(0);
        projUnitDTO.setParent(0);
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        if (Constant.QUARTERLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
            frequencyDivision = 4;
        } else if (Constant.MONTHLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
            frequencyDivision = 12;
        } else if (Constant.SEMIANNUALLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
            frequencyDivision = 2;
        } else {
            frequencyDivision = 1;
        }
        String projections = projSelDTO.getActualsOrProjections();
        String salesOrUnits = projSelDTO.getSalesOrUnit();
        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                int year = Integer.valueOf(String.valueOf(obj[0]));
                int period = Integer.valueOf(String.valueOf(obj[1]));
                List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = common.get(0);
                int col = 2;
                if (Constant.SALES.equalsIgnoreCase(salesOrUnits) || Constant.BOTH_SMALL.equalsIgnoreCase(salesOrUnits)) {

                    String actual = StringUtils.EMPTY + obj[col];
                    actual = getFormattedValue(CUR_ZERO, actual);
                    projSalesDTO.addStringProperties(commonColumn + Constant.ACTUALS, actual);
                    String projection = StringUtils.EMPTY + obj[col + 1];
                    projection = getFormattedValue(CUR_ZERO, projection);
                    projSalesDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, projection);

                    columnList.remove(commonColumn + Constant.ACTUALS);
                    columnList.remove(commonColumn + Constant.PROJECTIONS);
                }
                if (Constant.UNITS.equalsIgnoreCase(salesOrUnits) || Constant.BOTH_SMALL.equalsIgnoreCase(salesOrUnits)) {

                    String actual = StringUtils.EMPTY + obj[col + 2];
                    actual = getFormattedValue(UNITVOLUME, actual);
                    projUnitDTO.addStringProperties(commonColumn + Constant.ACTUALS, actual);
                    String projection = StringUtils.EMPTY + obj[col + 3];
                    projection = getFormattedValue(UNITVOLUME, projection);
                    projUnitDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, projection);

                    columnList.remove(commonColumn + Constant.ACTUALS);
                    columnList.remove(commonColumn + Constant.PROJECTIONS);
                }
            }

        }
        for (String columns : columnList) {
            projSalesDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
            projUnitDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
        }
        projDtoList.add(projSalesDTO);
        projDtoList.add(projUnitDTO);
        return projDtoList;
    }

    public List<SalesProjectionResultsDTO> getProjectionPivotTotal(Object[] orderedArgs, ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("Entering getProjectionPivotTotal");
        projectionTotalList.clear();// Fix for GAL-4084
        if (projectionTotalList.isEmpty()) {
            List<Object[]> gtsList = null;
            List<Object[]> discountList = new ArrayList<Object[]>();
            List<SalesProjectionResultsDTO> projDTOList = new ArrayList<SalesProjectionResultsDTO>();
            if (projSelDTO.getScreenName().equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
                LOGGER.info("Entering getProjectionPivotTotal NonMandated");
                gtsList = CommonLogic.callProcedure("PRC_PROJECTION_RESULTS", orderedArgs);
                getCustomizedProjectionPivotTotal(gtsList, discountList, projSelDTO);
                LOGGER.info("Ending getProjectionPivotTotal NonMandated");
            } else if (projSelDTO.getScreenName().equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
                LOGGER.info("Entering getProjectionPivotTotal Mandated");
                gtsList = SPRCommonLogic.callProcedure("PRC_M_PROJECTION_RESULTS", orderedArgs);
                projectionTotalList.clear();
                projectionTotalList = getCustomizedProjectionPivotTotalMandated(gtsList, discountList, projSelDTO);
                LOGGER.info("Ending getProjectionPivotTotal Mandated");
            } else if (projSelDTO.getScreenName().equals(CommonUtils.BUSINESS_PROCESS_TYPE_CHANNELS)) {
                LOGGER.info("Entering getProjectionPivotTotal Channel");
                projDTOList = new ArrayList<SalesProjectionResultsDTO>();
                gtsList = SPRCommonLogic.callProcedure("PRC_CH_PROJECTION_RESULTS", orderedArgs);
                projectionTotalList.clear();
                projectionTotalList = getCustomizedProjectionPivotTotalChannel(gtsList, discountList, projSelDTO, false);
                LOGGER.info("Ending getProjectionPivotTotal Channel");
            }
        }
        LOGGER.info("Ending getProjectionPivotTotal");
        return projectionTotalList;
    }

    public void getCustomizedProjectionPivotTotal(List<Object[]> list, List<Object[]> discountList, ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("Entering getCustomizedProjectionPivotTotal");
        projectionTotalList.clear();
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<String> periodList = new ArrayList<String>(projSelDTO.getPeriodList());
        int discountIndex = 0;
        int col = 5;
        int dcol = 2;
        if (frequencyDivision != 1) {
            col = col + 1;
            dcol = dcol + 1;
        }
        for (Object[] row : list) {

            String column = StringUtils.EMPTY;
            int year = Integer.valueOf(String.valueOf(row[col - 1]));
            int period = Integer.valueOf(String.valueOf(row[4]));
            List<String> common = new ArrayList<>();
            if ("SPR".equals(projSelDTO.getProjTabName())) {
                common = getCommonColumnHeaderSPR(frequencyDivision, year, period);
            } else {
                common = getCommonColumnHeader(frequencyDivision, year, period);
            }

            String pcommonColumn = common.get(0);
            String commonHeader = common.get(1);
            String commonColumn = StringUtils.EMPTY;
            if (periodList.contains(pcommonColumn)) {
                periodList.remove(pcommonColumn);
                SalesProjectionResultsDTO projDTO = new SalesProjectionResultsDTO();
                projDTO.setLevelValue(commonHeader);
                String value = Constant.NULL;
                commonColumn = "efs";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[1];
                    value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[2];
                    value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                }
                commonColumn = "dms";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[22];
                    value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[23];
                    value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                }

                commonColumn = "iws";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[24];
                    value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[25];
                    value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                }
                commonColumn = "csw";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col];
                    value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 1];
                    value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                }
                commonColumn = "uv";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 2];
                    value = getFormattedValue(UNITVOLUME, value);
                    projDTO.addStringProperties(column, value);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 3];
                    value = getFormattedValue(UNITVOLUME, value);
                    projDTO.addStringProperties(column, value);
                }
                projDTO.setParent(0);
                projDTO.setProjectionTotal(1);
                projectionTotalList.add(projDTO);
            }

        }
        boolean leftFlag = false;
        for (String ob : periodList) {
            leftFlag = true;
            SalesProjectionResultsDTO projDTO = new SalesProjectionResultsDTO();
            projDTO.setParent(0);
            projDTO.setProjectionTotal(1);
            projDTO.setLevelValue(projSelDTO.getPeriodListMap().get(ob));
            projectionTotalList.add(projDTO);
        }
        if (projSelDTO.getProjectionOrder().equals(ASCENDING.getConstant())) {
            if (leftFlag) {
                Collections.sort(projectionTotalList, new SalesProjectionResultsDTO());
            }
        } else {
            if (leftFlag) {
                Collections.sort(projectionTotalList, new SalesProjectionResultsDTO());
            }
            Collections.reverse(projectionTotalList);
        }
        LOGGER.info("Ending getCustomizedProjectionPivotTotal");
    }

    public List<SalesProjectionResultsDTO> getProjectionPivot(ProjectionSelectionDTO projSelDTO) {
        List<SalesProjectionResultsDTO> projDTOList = new ArrayList<SalesProjectionResultsDTO>();
        if (projSelDTO.getScreenName().equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
            LOGGER.info("Entering getProjection Pivot NonMandated");
            projDTOList = new ArrayList<SalesProjectionResultsDTO>();

            List<Object> gtsList = (List<Object>) CommonLogic.executeSelectQuery(CommonLogic.getCCPQuery(projSelDTO,Boolean.FALSE) + " \n" + getSalesProjectionResultsSalesQuery(projSelDTO), null, null);
            List<Object> discountList = new ArrayList<Object>();
            projDTOList = getCustomizedProjectionPivot(gtsList, discountList, projSelDTO);
            LOGGER.info("Ending getProjection Pivot NonMandated");
        } else if (projSelDTO.getScreenName().equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
            LOGGER.info("Entering getProjectionPivot Mandated");
            projDTOList = new ArrayList<SalesProjectionResultsDTO>();
            List<Object[]> gtsList = (List<Object[]>) SPRCommonLogic.executeSelectQuery(getSalesProjectionResultsSalesQueryMandated(projSelDTO), null, null);
            projDTOList = getCustomizedProjectionPivotMandated(gtsList, projSelDTO);
            gtsList = null;
            if (projSelDTO.getProjectionOrder().equalsIgnoreCase(Constant.DESCENDING)) {
                Collections.reverse(projDTOList);
            }
            LOGGER.info("Ends getProjectionPivot Mandated");
        } else if (projSelDTO.getScreenName().equals(CommonUtils.BUSINESS_PROCESS_TYPE_CHANNELS)) {
            LOGGER.info("Entering getProjectionPivot Channel");
            projDTOList = new ArrayList<SalesProjectionResultsDTO>();
            List<Object> gtsList = (List<Object>) SPRCommonLogic.executeSelectQuery(SPRCommonLogic.getCCPQueryChannel(projSelDTO) + " \n" + getSalesProjectionResultsSalesQueryChannel(projSelDTO), null, null);
            List<Object> discountList = new ArrayList<Object>();
            projDTOList = getCustomizedProjectionPivotChannel(gtsList, discountList, projSelDTO, false);
            LOGGER.info("Ending getProjectionPivot Channel");
        }
        return projDTOList;
    }

    public List<SalesProjectionResultsDTO> getCustomizedProjectionPivot(List<Object> list, List<Object> discountList, ProjectionSelectionDTO projSelDTO) {

        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<SalesProjectionResultsDTO> projDTOList = new ArrayList<SalesProjectionResultsDTO>();
        List<String> periodList = new ArrayList<String>(projSelDTO.getPeriodList());
        int discountIndex = 0;
        int col = 2;
        int dcol = 2;
        for (Object rows : list) {
            final Object[] row = (Object[]) rows;
            String column = StringUtils.EMPTY;
            int year = Integer.valueOf(String.valueOf(row[0]));
            int period = Integer.valueOf(String.valueOf(row[1]));
            List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
            String pcommonColumn = common.get(0);
            String commonHeader = common.get(1);
            String commonColumn = StringUtils.EMPTY;
            if (periodList.contains(pcommonColumn)) {
                periodList.remove(pcommonColumn);
                SalesProjectionResultsDTO projDTO = new SalesProjectionResultsDTO();
                List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
                columnList.remove("levelValue");
                projDTO.setLevelValue(commonHeader);
                String value = Constant.NULL;
                commonColumn = "gts";
                value = "...";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "csw";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col];
                    value = getFormattedValue(CUR_ZERO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 1];
                    value = getFormattedValue(CUR_ZERO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "uv";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 2];
                    value = getFormattedValue(UNITVOLUME, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 3];
                    value = getFormattedValue(UNITVOLUME, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                projDTO.setParent(0);
                projDTO.setProjectionTotal(1);
                for (String columns : columnList) {
                    projDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
                }
                projDTOList.add(projDTO);
            }
        }
        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        columnList.remove("levelValue");
        boolean leftFlag = false;
        for (String ob : periodList) {
            leftFlag = true;
            SalesProjectionResultsDTO projDTO = new SalesProjectionResultsDTO();
            projDTO.setParent(0);
            projDTO.setProjectionTotal(1);
            projDTO.setLevelValue(projSelDTO.getPeriodListMap().get(ob));
            for (String columns : columnList) {
                projDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
            }
            projDTOList.add(projDTO);
        }
        if (projSelDTO.getProjectionOrder().equals(ASCENDING.getConstant())) {
            if (leftFlag) {
                Collections.sort(projDTOList, new SalesProjectionResultsDTO());
            }
        } else {
            if (leftFlag) {
                Collections.sort(projDTOList, new SalesProjectionResultsDTO());
            }
            Collections.reverse(projDTOList);
        }
        return projDTOList;
    }

    public int getConfiguredSalesProjectionResultsCount(Object parentId, ProjectionSelectionDTO projSelDTO, boolean isLevelCount) {
        projSelDTO.setGroupCount(false);
        int count = 0;
        if (!projSelDTO.isIsFilter() || (parentId instanceof SalesProjectionResultsDTO)) {
            projSelDTO.setYear(Constant.All);

            if (projSelDTO.getActualsOrProjections().equals(Constant.BOTH)) {
                projSelDTO.setActualsOrProjections("Actuals and Projections");
            }

            if (parentId instanceof SalesProjectionResultsDTO) {
                projSelDTO.setIsProjectionTotal(false);
                SalesProjectionResultsDTO parentDto = (SalesProjectionResultsDTO) parentId;
                projSelDTO.setLevelNo(parentDto.getLevelNo());
                projSelDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
                projSelDTO.setLevelValue(parentDto.getLevelValue());
                projSelDTO.setParentNode(parentDto.getParentNode());
                projSelDTO.setHierarchyNo(parentDto.getHierarchyNo());
                if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equalsIgnoreCase(parentDto.getHierarchyIndicator())) {
                    projSelDTO.setCustomerHierarchyNo(projSelDTO.getHierarchyNo());
                    projSelDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
                } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equalsIgnoreCase(parentDto.getHierarchyIndicator())) {
                    projSelDTO.setProductHierarchyNo(projSelDTO.getHierarchyNo());
                    projSelDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                }
                projSelDTO.setHierarchyIndicator(parentDto.getHierarchyIndicator());
                projSelDTO.setGroup(parentDto.getGroup());
                projSelDTO.setIsTotal(parentDto.getOnExpandTotalRow() == 1);
            } else {
                projSelDTO.setIsProjectionTotal(true);
                if (projSelDTO.isIsCustomHierarchy()) {
                    projSelDTO.setLevelNo(0);
                    projSelDTO.setTreeLevelNo(0);
                } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                    projSelDTO.setTreeLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(projSelDTO.getProductLevelNo() - 1);
                    projSelDTO.setTreeLevelNo(projSelDTO.getProductLevelNo() - 1);
                }
                projSelDTO.setLevelValue(StringUtils.EMPTY);
                projSelDTO.setHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setProductHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
            }
            count += getProjectionResultsCount(projSelDTO, isLevelCount);
        } else {
            projSelDTO.setIsProjectionTotal(false);
            projSelDTO.setLevelNo(projSelDTO.getFilterLevelNo());
            projSelDTO.setTreeLevelNo(projSelDTO.getFilterLevelNo());
            count += configureLevelsCount(projSelDTO);
        }
        return count;
    }

    public int getConfiguredSalesProjectionResultsTotalCount(ProjectionSelectionDTO projSelDTO) {
        int count = 0;
        projSelDTO.setIsProjectionTotal(true);
        if (!projSelDTO.isIsFilter()) {
            projSelDTO.setYear(Constant.All);

            if (projSelDTO.getActualsOrProjections().equals(Constant.BOTH)) {
                projSelDTO.setActualsOrProjections("Actuals and Projections");
            }
            if (Constant.ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
                projSelDTO.setFrequencyDivision(1);
            } else if (Constant.SEMIANNUALLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
                projSelDTO.setFrequencyDivision(2);
            } else if (Constant.QUARTERLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
                projSelDTO.setFrequencyDivision(4);
            } else if (Constant.MONTHLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
                projSelDTO.setFrequencyDivision(12);
            }
            if (projSelDTO.isIsCustomHierarchy()) {
                projSelDTO.setLevelNo(0);
                projSelDTO.setTreeLevelNo(0);
            } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                projSelDTO.setLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                projSelDTO.setTreeLevelNo(projSelDTO.getCustomerLevelNo() - 1);
            } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                projSelDTO.setLevelNo(projSelDTO.getProductLevelNo() - 1);
                projSelDTO.setTreeLevelNo(projSelDTO.getProductLevelNo() - 1);
            }
            projSelDTO.setGroup(StringUtils.EMPTY);
            projSelDTO.setHierarchyNo(StringUtils.EMPTY);
            projSelDTO.setProductHierarchyNo(StringUtils.EMPTY);
            projSelDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
            count += getProjectionResultsTotalCount(projSelDTO);
        }
        return count;
    }

    public int getProjectionResultsTotalCount(ProjectionSelectionDTO projSelDTO) {
        int count = 1;
        if (projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
            count = count + 1;
            String salesUnits = projSelDTO.getSalesOrUnit();
            if (salesUnits.equals(Constant.BOTH) || salesUnits.equals(Constant.SALES_SMALL)) {
                count++;
            }
            if (salesUnits.equals(Constant.BOTH) || salesUnits.equals(Constant.UNITS_SMALL)) {
                count++;
            }
        } else {
            count = count + projSelDTO.getPeriodList().size();
        }
        return count;
    }

    public int getProjectionResultsCount(ProjectionSelectionDTO projSelDTO, boolean isLevelCount) {
        int count = 0;
        if (!projSelDTO.getLevelValue().startsWith(Constant.All)
                && !projSelDTO.getLevelValue().contains(Constant.Sales)) {
            if (projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
                if (projSelDTO.isIsProjectionTotal()) {
                    count = count + 2;
                    if (projSelDTO.getSalesOrUnit().equals(BOTH.getConstant())) {
                        count = count + 4;
                    } else if(projSelDTO.getSalesOrUnit().equals(UNITS.getConstant())) {
                         count = count + 1;
                    } else if (projSelDTO.getSalesOrUnit().equals(SALES.getConstant())) {
                        count = count + 3;
                    }
                } else {
                    if (projSelDTO.getSalesOrUnit().equals(SALES.getConstant()) || projSelDTO.getSalesOrUnit().equals(UNITS.getConstant())) {
                        count = count + 1;
                    }
                    if (projSelDTO.getSalesOrUnit().equals(BOTH.getConstant())) {
                        count = count + 2;
                    }
                }
            } else {
                count = count + projSelDTO.getPeriodList().size();
                if (projSelDTO.isIsProjectionTotal()) {
                    count++;
                }
            }
        }
        if (isLevelCount && !projSelDTO.isIsFilter()) {
            if ((projSelDTO.getTreeLevelNo() + 1) == projSelDTO.getTpLevel()
                    && ((projSelDTO.isIsCustomHierarchy()) || (!projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)))
                    && !projSelDTO.getLevelValue().startsWith(Constant.All)
                    && !projSelDTO.getLevelValue().contains(Constant.Sales)) {
                count = count + 1;
                projSelDTO.setGroupCount(true);
                projSelDTO.setLevelCount(1);
            } else {
                projSelDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo() + 1);
                int levelCount = configureLevelsCount(projSelDTO);
                count = count + levelCount;
                projSelDTO.setLevelCount(levelCount);
            }

        }
        return count;

    }

    public int configureLevelsCount(ProjectionSelectionDTO projSelDTO) {
        return CommonLogic.getLevelListCount(projSelDTO.getProjectionId(), projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo(), projSelDTO.getHierarchyNo(), projSelDTO.getProductHierarchyNo(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.isIsFilter(), projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomId(), projSelDTO.getGroupFilter(), projSelDTO.getUserId(), projSelDTO.getSessionId(), projSelDTO.getCustRelationshipBuilderSid(), projSelDTO.getProdRelationshipBuilderSid());
    }

    public String getCCPQueryCustom(ProjectionSelectionDTO projSelDTO) {
        String levelNo = Constant.STRING_ONE;
        String ccpQuery
                = "(SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID "
                + "FROM RELATIONSHIP_LEVEL_DEFINITION RLD "
                + "JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID "
                + "JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId()
                + ") CCPMAPC "
                + "JOIN"
                + "(SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID "
                + "FROM RELATIONSHIP_LEVEL_DEFINITION RLD "
                + "JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID "
                + "JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId()
                + ") CCPMAPP"
                + " ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID JOIN "
                + " (SELECT RLD2.HIERARCHY_NO, RLD2.RELATIONSHIP_LEVEL_SID,"
                + " CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD "
                + "JOIN dbo.CUSTOM_VIEW_MASTER CVM ON CVD.CUSTOM_VIEW_MASTER_SID=" + projSelDTO.getCustomId() + " AND CVD.LEVEL_NO  like '";
        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equalsIgnoreCase(projSelDTO.getHierarchyIndicator())) {
            ccpQuery += projSelDTO.getLevelNo() + "'";
        } else {
            ccpQuery += " %'";
        }

        ccpQuery += " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID "
                + " JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId()
                + " WHERE RLD2.HIERARCHY_NO like '" + projSelDTO.getCustomerHierarchyNo() + "%' ) HLDC"
                + " ON CCPMAPC.HIERARCHY_NO like HLDC.HIERARCHY_NO+'%'  "
                + " JOIN"
                + " (SELECT RLD2.HIERARCHY_NO, RLD2.RELATIONSHIP_LEVEL_SID,"
                + " CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD "
                + "JOIN dbo.CUSTOM_VIEW_MASTER CVM ON CVD.CUSTOM_VIEW_MASTER_SID=" + projSelDTO.getCustomId() + " AND CVD.LEVEL_NO  like '";
        if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equalsIgnoreCase(projSelDTO.getHierarchyIndicator())) {
            ccpQuery += projSelDTO.getLevelNo() + "'";
        } else {
            ccpQuery += " %'";
        }
        ccpQuery += " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID "
                + " JOIN PROJECTION_PROD_HIERARCHY PCH2"
                + " ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID"
                + " AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId()
                + " WHERE RLD2.HIERARCHY_NO like '" + projSelDTO.getProductHierarchyNo() + "%' ) HLDP"
                + " ON CCPMAPP.HIERARCHY_NO like HLDP.HIERARCHY_NO+'%'" + ") CCP ";
        return ccpQuery;
    }

    public String getSalesProjectionResultsSalesQuery(ProjectionSelectionDTO projSelDTO) {

        String selectClause = " select ";
        String whereClause = StringUtils.EMPTY;
        String groupBy = ",H.LEVEL_NAME , I.\"YEAR\"\n";
        String customQuery = StringUtils.EMPTY;
        String ccpWhereCond = CommonLogic.getCCPWhereConditionQuery("H", "E", "CCP");
        groupBy = ", H.RELATIONSHIP_LEVEL_VALUES " + groupBy;
        selectClause += "I.\"YEAR\" as YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == 4) {
            selectClause += "I.QUARTER as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == 12) {
            selectClause += "I.\"MONTH\" as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.\"MONTH\"";
        }

        // To filter the data according to selected period
        String periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);

        String customSql = "  ST_NM_SALES_PROJECTION_MASTER B,\n"
                + " PROJECTION_DETAILS E , \n"
                + " RELATIONSHIP_LEVEL_DEFINITION H,\n"
                + " \"PERIOD\" I, \n"
                + " @CCP CCP "
                + "where A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID \n"
                + " and B.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID \n"
                + CommonLogic.getUserSessionQueryCondition(projSelDTO.getUserId(), projSelDTO.getSessionId(), "B")
                + CommonLogic.getUserSessionQueryCondition(projSelDTO.getUserId(), projSelDTO.getSessionId(), "A")
                + " and E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + ccpWhereCond + "and A.PERIOD_SID = I.PERIOD_SID \n"
                + periodFilter + " and H.LEVEL_NO = " + projSelDTO.getLevelNo()
                + whereClause + "\n"
                + " group by H.LEVEL_NO " + groupBy;

        String historyQuery = selectClause + " sum(A.ACTUAL_SALES) as SALES_ACTUAL_SALES, \n"
                + " sum(A.HISTORY_PROJECTION_SALES) as SALES_PROJECTION_SALES, \n"
                + " sum(A.ACTUAL_UNITS)as ACTUAL_UNITS, \n"
                + " sum(A.HISTORY_PROJECTION_UNITS) as PROJECTION_UNITS \n"
                + " from ST_NM_ACTUAL_SALES A,\n "
                + customSql;
        String futureQuery = selectClause + " 0 as SALES_ACTUAL_SALES, \n"
                + " sum(A.PROJECTION_SALES) as SALES_PROJECTION_SALES, \n"
                + " 0 as ACTUAL_UNITS, \n"
                + " sum(A.PROJECTION_UNITS) as PROJECTION_UNITS \n"
                + " from ST_NM_SALES_PROJECTION A,\n"
                + customSql;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("HISTORY", "FUTURE", "on");
        String finalWhereCond = list.get(1);
        String orderBy = list.get(2);
        String finalSelectClause = "select " + list.get(0) + "\n Isnull(HISTORY.SALES_ACTUAL_SALES, FUTURE.SALES_ACTUAL_SALES) as ACTUAL_SALES,\n Isnull(FUTURE.SALES_PROJECTION_SALES, HISTORY.SALES_PROJECTION_SALES) as PROJECTION_SALES,"
                + "\n Isnull(HISTORY.ACTUAL_UNITS, FUTURE.ACTUAL_UNITS) as ACTUAL_UNITS,\n Isnull(FUTURE.PROJECTION_UNITS, HISTORY.PROJECTION_UNITS) as PROJECTION_UNITS  ";

        customQuery = finalSelectClause + " from (\n" + historyQuery + "\n) HISTORY FULL OUTER JOIN (\n" + futureQuery + "\n) FUTURE \n" + finalWhereCond + " Order By " + orderBy;
        return customQuery;
    }

    // Mandated
    public int getConfiguredSalesProjectionResultsCountMandated(Object parentId, ProjectionSelectionDTO projSelDTO, boolean isLevelCount) {
        int count = 0;
        if ((!projSelDTO.isIsFilter() && !projSelDTO.isFilterDdlb()) || (parentId instanceof SalesProjectionResultsDTO)) {
            projSelDTO.setYear(Constant.All);

            if (projSelDTO.getActualsOrProjections().equals(Constant.BOTH)) {
                projSelDTO.setActualsOrProjections("Actuals and Projections");
            }
            if (parentId instanceof SalesProjectionResultsDTO) {
                SalesProjectionResultsDTO parentDto = (SalesProjectionResultsDTO) parentId;
                projSelDTO.setLevelNo(parentDto.getLevelNo());
                projSelDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
                projSelDTO.setLevelValue(parentDto.getLevelValue());
                projSelDTO.setParentNode(parentDto.getParentNode());
                projSelDTO.setHierarchyNo(parentDto.getHierarchyNo());
                projSelDTO.setCustomLevelNo(parentDto.getCustomLevelNo());
                if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equalsIgnoreCase(parentDto.getHierarchyIndicator())) {
                    projSelDTO.setCustomerHierarchyNo(projSelDTO.getHierarchyNo());
                    projSelDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
                } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equalsIgnoreCase(parentDto.getHierarchyIndicator())) {
                    projSelDTO.setProductHierarchyNo(projSelDTO.getHierarchyNo());
                    projSelDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                }
                projSelDTO.setHierarchyIndicator(parentDto.getHierarchyIndicator());
                projSelDTO.setIsProjectionTotal(parentDto.getProjectionTotal() == 1);
                projSelDTO.setGroup(parentDto.getGroup());
                projSelDTO.setIsTotal(parentDto.getOnExpandTotalRow() == 1);
            } else {
                projSelDTO.setIsProjectionTotal(true);
                projSelDTO.setIsTotal(true);
                if (projSelDTO.isCustomFlag()) {
                    String indicator = SPRCommonLogic.getIndicator(1, projSelDTO.getCustomId());
                    projSelDTO.setHierarchyIndicator(indicator);
                    projSelDTO.setLevelNo(0);
                    projSelDTO.setTreeLevelNo(0);
                    projSelDTO.setCustomLevelNo(0);;
                } else {
                    if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                        projSelDTO.setLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                        projSelDTO.setTreeLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                    } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                        projSelDTO.setLevelNo(projSelDTO.getProductLevelNo() - 1);
                        projSelDTO.setTreeLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                    } else {
                        projSelDTO.setLevelNo(0);
                        projSelDTO.setTreeLevelNo(0);
                    }
                }
                projSelDTO.setGroup(StringUtils.EMPTY);
                projSelDTO.setHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setProductHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
            }

            count += getProjectionResultsCountMandated(projSelDTO, isLevelCount);
        } else if (isLevelCount || projSelDTO.isFilterDdlb()) {
            projSelDTO.setIsProjectionTotal(false);
            projSelDTO.setIsTotal(true);
            projSelDTO.setLevelNo(projSelDTO.getFilterLevelNo());
            projSelDTO.setTreeLevelNo(projSelDTO.getFilterLevelNo());
            count += configureLevelsCountMandated(projSelDTO);
        }
        return count;
    }

    public int getProjectionResultsCountMandated(ProjectionSelectionDTO projSelDTO, boolean isLevelCount) {
        int count = 0;
        boolean tempCustomFlag = false;
        if (projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
            if (projSelDTO.isIsProjectionTotal() && projSelDTO.isIsTotal()) {
                count = count + 4;
            }
            String salesUnits = projSelDTO.getSalesOrUnit();
            if (projSelDTO.isIsTotal()) {
                count = count + 1;
                if (salesUnits.equals(Constant.BOTH)) {
                    count++;
                }
            }
        } else {
            if (projSelDTO.isIsProjectionTotal()) {
                count = count + 1 + projSelDTO.getPeriodList().size();
            } else {
                count = count + projSelDTO.getPeriodList().size();
            }
        }
        if (!projSelDTO.isFilterDdlb() && projSelDTO.isIsTotal() && isLevelCount && !projSelDTO.isIsFilter()) {
            if (projSelDTO.isCustomFlag()) {
                projSelDTO.setCustomLevelNo(projSelDTO.getCustomLevelNo() + 1);
                int i = SPRCommonLogic.getIndicatorCount(projSelDTO.getProjectionId(), projSelDTO.getCustomId());
                if (i >= projSelDTO.getCustomLevelNo()) {
                    tempCustomFlag = true;
                }
            }
            if (projSelDTO.isCustomFlag() && tempCustomFlag) {
                String indicator = SPRCommonLogic.getIndicator(projSelDTO.getCustomLevelNo(), projSelDTO.getCustomId());
                projSelDTO.setHierarchyIndicator(indicator);
                projSelDTO.setLevelNo(projSelDTO.getCustomLevelNo());
                projSelDTO.setTreeLevelNo(projSelDTO.getCustomLevelNo());
                projSelDTO.setHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setProductHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
            }
            if (projSelDTO.isCustomFlag()) {
                if (tempCustomFlag) {
                    int temp = SPRCommonLogic.getLevelListCount(projSelDTO.getProjectionId(), projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo(), projSelDTO.getHierarchyNo(),
                            projSelDTO.getProductHierarchyNo(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.isIsFilter(), projSelDTO.isIsCustomHierarchy(),
                            projSelDTO.getCustomId(), StringUtils.EMPTY, Integer.valueOf(projSelDTO.getUserId()), Integer.valueOf(projSelDTO.getSessionId()), projSelDTO.isFilterDdlb(), projSelDTO.getLevelName());

                    count = count + temp;
                    projSelDTO.setLevelCount(temp);
                }
            } else {
                projSelDTO.setLevelNo(projSelDTO.getLevelNo() + 1);
                projSelDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo() + 1);
                int temp = configureLevelsCountMandated(projSelDTO);
                count = count + temp;
                projSelDTO.setLevelCount(temp);
            }
        }
        return count;
    }

    public int configureLevelsCountMandated(ProjectionSelectionDTO projSelDTO) {
        return SPRCommonLogic.getLevelListCount(projSelDTO.getProjectionId(), projSelDTO.getHierarchyIndicator(), projSelDTO.getLevelNo(), projSelDTO.getHierarchyNo(), projSelDTO.isIsFilter(), projSelDTO.isFilterDdlb(), projSelDTO.getLevelName());
    }

    public List<SalesProjectionResultsDTO> getConfiguredSalesProjectionResultsMandated(Object parentId, int start, int offset, ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("Entering getConfiguredSalesProjectionResultsMandated ");
        List<SalesProjectionResultsDTO> resultList = new ArrayList<SalesProjectionResultsDTO>();

        if ((!projSelDTO.isIsFilter() && !projSelDTO.isFilterDdlb()) || (parentId instanceof SalesProjectionResultsDTO)) {
            projSelDTO.setYear(Constant.All);

            if (projSelDTO.getActualsOrProjections().equals(Constant.BOTH)) {
                projSelDTO.setActualsOrProjections("Actuals and Projections");
            }
            if (Constant.ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
                projSelDTO.setFrequencyDivision(1);
            } else if (Constant.SEMIANNUALLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
                projSelDTO.setFrequencyDivision(2);
            } else if (Constant.QUARTERLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
                projSelDTO.setFrequencyDivision(4);
            } else if (Constant.MONTHLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
                projSelDTO.setFrequencyDivision(12);
            }
            if (parentId instanceof SalesProjectionResultsDTO) {
                SalesProjectionResultsDTO parentDto = (SalesProjectionResultsDTO) parentId;
                projSelDTO.setLevelNo(parentDto.getLevelNo());
                projSelDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
                projSelDTO.setLevelValue(parentDto.getLevelValue());
                projSelDTO.setParentNode(parentDto.getParentNode());
                projSelDTO.setHierarchyNo(parentDto.getHierarchyNo());
                projSelDTO.setCustomLevelNo(parentDto.getCustomLevelNo());
                if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equalsIgnoreCase(parentDto.getHierarchyIndicator())) {
                    projSelDTO.setCustomerHierarchyNo(projSelDTO.getHierarchyNo());
                    projSelDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
                } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equalsIgnoreCase(parentDto.getHierarchyIndicator())) {
                    projSelDTO.setProductHierarchyNo(projSelDTO.getHierarchyNo());
                    projSelDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                }
                projSelDTO.setHierarchyIndicator(parentDto.getHierarchyIndicator());
                projSelDTO.setIsProjectionTotal(parentDto.getProjectionTotal() == 1);
                projSelDTO.setGroup(parentDto.getGroup());
                projSelDTO.setIsTotal(parentDto.getOnExpandTotalRow() == 1);
            } else {
                projSelDTO.setIsProjectionTotal(true);
                projSelDTO.setIsTotal(true);
                if (projSelDTO.isCustomFlag()) {
                    String indicator = SPRCommonLogic.getIndicator(1, projSelDTO.getCustomId());
                    projSelDTO.setHierarchyIndicator(indicator);
                    projSelDTO.setLevelNo(0);
                    projSelDTO.setTreeLevelNo(0);
                    projSelDTO.setCustomLevelNo(0);
                } else {
                    if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                        projSelDTO.setLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                        projSelDTO.setTreeLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                    } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                        projSelDTO.setLevelNo(projSelDTO.getProductLevelNo() - 1);
                        projSelDTO.setTreeLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                    } else {
                        projSelDTO.setLevelNo(0);
                        projSelDTO.setTreeLevelNo(0);
                    }
                }
                projSelDTO.setGroup(StringUtils.EMPTY);
                projSelDTO.setHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setProductHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
            }

            resultList = getSalesProjectionResultsMandated(start, offset, projSelDTO);
        } else {
            projSelDTO.setIsProjectionTotal(false);
            projSelDTO.setIsTotal(true);
            projSelDTO.setLevelNo(projSelDTO.getFilterLevelNo());
            projSelDTO.setTreeLevelNo(projSelDTO.getFilterLevelNo());
            resultList = configureLevelsMandated(start, offset, projSelDTO);
        }

        LOGGER.info("Ends getConfiguredSalesProjectionResultsMandated ");
        return resultList;
    }

    public List<SalesProjectionResultsDTO> getSalesProjectionResultsMandated(int start, int offset, ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("Entering getSalesProjectionResultsMandated");

        int neededRecord = offset;
        int started = start;
        int mayBeAdded = 0;
        List<SalesProjectionResultsDTO> projDTOList = new ArrayList<SalesProjectionResultsDTO>();
        String discList = CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false);
        String freq = StringUtils.EMPTY;
        boolean tempCustomFlag = false;

        if (projSelDTO.getFrequencyDivision() == 1 || Constant.ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
            freq = "ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 2 || Constant.SEMI_ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
            freq = "SEMI-ANNUALLY";
        } else if (projSelDTO.getFrequencyDivision() == 4 || Constant.QUARTERLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
            freq = "QUARTERLY";
        } else if (projSelDTO.getFrequencyDivision() == 12 || Constant.MONTHLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
            freq = "MONTHLY";
        }

        Object[] orderedArgs = {projSelDTO.getProjectionId(), projSelDTO.getUserId(), projSelDTO.getSessionId(), freq, "Projection Results", null, null};
        if (projSelDTO.isIsProjectionTotal()) {
            if (start < 1) {
                SalesProjectionResultsDTO dto = new SalesProjectionResultsDTO();
                dto.setRelationshipLevelName(Constant.PROJECTION_TOTAL);
                dto.setParent(0);
                projDTOList.add(dto);
                neededRecord--;
            }
            mayBeAdded++;
            if (neededRecord > 0 && projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
                SalesProjectionResultsDTO demandSalesDTO = null;
                SalesProjectionResultsDTO inventoryWithdrawDTO = null;
                SalesProjectionResultsDTO contractSalesDto = null;
                SalesProjectionResultsDTO unitVolDto = null;

                getProjectionTotal(orderedArgs, projSelDTO);
                SalesProjectionResultsDTO exFactorySalesDTO = projectionTotalList.get(0);
                demandSalesDTO = projectionTotalList.get(1);
                inventoryWithdrawDTO = projectionTotalList.get(2);
                contractSalesDto = projectionTotalList.get(3);
                unitVolDto = projectionTotalList.get(4);
                projectionTotalList = null;
                mayBeAdded++;
                if (start < 2 && neededRecord > 0) {
                    if (exFactorySalesDTO != null) {
                        projDTOList.add(exFactorySalesDTO);
                        neededRecord--;
                    }

                }
                String salesUnits = projSelDTO.getSalesOrUnit();
                if (neededRecord > 0 && (salesUnits.equals(Constant.BOTH) || salesUnits.equals(Constant.SALES_SMALL))) {
                    if (start < 4) {
                        if (demandSalesDTO != null) {
                            projDTOList.add(demandSalesDTO);
                            neededRecord--;
                        }
                        if (inventoryWithdrawDTO != null) {
                            projDTOList.add(inventoryWithdrawDTO);
                            neededRecord--;
                        }
                        if (contractSalesDto != null) {
                            projDTOList.add(contractSalesDto);
                            neededRecord--;
                        }
                    }
                    mayBeAdded++;
                }
                if (neededRecord > 0 && (salesUnits.equals(Constant.BOTH) || salesUnits.equals(Constant.UNITS_SMALL))) {
                    if ((salesUnits.equals(Constant.BOTH) && start < 5) || (start < 4)) {
                        if (unitVolDto != null) {
                            projDTOList.add(unitVolDto);
                            neededRecord--;
                        }
                    }
                    mayBeAdded++;
                }

            } else {
                int mayBeAddedRecord = start - mayBeAdded;
                if (mayBeAddedRecord < 0) {
                    mayBeAddedRecord = 0;
                }
                projSelDTO.setProjTabName("SPR");
                List<SalesProjectionResultsDTO> projectionDtoList = getProjectionPivotTotal(orderedArgs, projSelDTO);
                projSelDTO.setProjTabName(StringUtils.EMPTY);
                for (int k = mayBeAddedRecord; k < projectionDtoList.size() && neededRecord > 0; k++) {
                    projDTOList.add(projectionDtoList.get(k));
                    neededRecord--;
                }
                mayBeAdded += projectionDtoList.size();
                projectionDtoList = null;
                projectionTotalList.clear(); // Fix for GAL-4084
            }
        } else {

            if (neededRecord > 0 && projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
                SalesProjectionResultsDTO contractSalesDto = null;
                SalesProjectionResultsDTO unitVolDto = null;

                String salesUnits = projSelDTO.getSalesOrUnit();
                if (neededRecord > 0 && projSelDTO.isIsTotal()) {
                    if (contractSalesDto == null || unitVolDto == null) {

                        List<SalesProjectionResultsDTO> contractSalesAndUnits = getContractSalesAndUnits(projSelDTO);
                        contractSalesDto = contractSalesAndUnits.get(0);
                        unitVolDto = contractSalesAndUnits.get(1);
                        contractSalesAndUnits = null;
                    }
                    if (salesUnits.equals(Constant.BOTH) || salesUnits.equals(Constant.SALES_SMALL)) {
                        boolean toadd = false;
                        if (projSelDTO.isIsProjectionTotal() && start < 4) {
                            toadd = true;
                        } else if (!projSelDTO.isIsProjectionTotal() && start < 1) {
                            toadd = true;
                        } else {
                            toadd = false;
                        }
                        if (toadd && !projSelDTO.isIsProjectionTotal()) {
                            projDTOList.add(contractSalesDto);
                            neededRecord--;
                            started++;
                        }
                        mayBeAdded++;
                    }
                    if (neededRecord > 0 && (salesUnits.equals(Constant.BOTH) || salesUnits.equals(Constant.UNITS_SMALL))) {
                        boolean toadd = false;
                        if (projSelDTO.isIsProjectionTotal() && start < 5) {
                            toadd = true;
                        } else if (!projSelDTO.isIsProjectionTotal() && start < 2) {
                            toadd = true;
                        } else {
                            toadd = false;
                        }
                        if (toadd && !projSelDTO.isIsProjectionTotal()) {
                            projDTOList.add(unitVolDto);
                            neededRecord--;
                            started++;
                        }
                        mayBeAdded++;
                    }
                }
            } else {
                List<SalesProjectionResultsDTO> projectionDtoList = new ArrayList<SalesProjectionResultsDTO>();

                projectionDtoList = getProjectionPivot(projSelDTO);
                for (int k = started; k < projectionDtoList.size() && neededRecord > 0; k++) {
                    projDTOList.add(projectionDtoList.get(k));
                    neededRecord--;
                    started++;
                }
                mayBeAdded += projectionDtoList.size();
                projectionDtoList = null;
            }
        }
        if (neededRecord > 0 && projSelDTO.isIsTotal() && !projSelDTO.isIsFilter() && !projSelDTO.isFilterDdlb()) {
            int mayBeAddedRecord = start - mayBeAdded;
            if (mayBeAddedRecord < 0) {
                mayBeAddedRecord = 0;
            }

            if (projSelDTO.isCustomFlag()) {
                projSelDTO.setCustomLevelNo(projSelDTO.getCustomLevelNo() + 1);
                int i = SPRCommonLogic.getIndicatorCount(projSelDTO.getProjectionId(), projSelDTO.getCustomId());

                if (i >= projSelDTO.getCustomLevelNo()) {
                    tempCustomFlag = true;

                }
            }
            if (projSelDTO.isCustomFlag() && tempCustomFlag) {
                String indicator = SPRCommonLogic.getIndicator(projSelDTO.getCustomLevelNo(), projSelDTO.getCustomId());
                projSelDTO.setHierarchyIndicator(indicator);
                projSelDTO.setLevelNo(projSelDTO.getCustomLevelNo());
                projSelDTO.setTreeLevelNo(projSelDTO.getCustomLevelNo());
            }
            if (projSelDTO.isCustomFlag()) {
                if (tempCustomFlag) {
                    List<SalesProjectionResultsDTO> nextLevelValueList = configureLevelsMandated(mayBeAddedRecord, neededRecord, projSelDTO);
                    projDTOList.addAll(nextLevelValueList);
                    nextLevelValueList = null;
                }
            } else {
                projSelDTO.setLevelNo(projSelDTO.getLevelNo() + 1);
                projSelDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo() + 1);
                List<SalesProjectionResultsDTO> nextLevelValueList = configureLevelsMandated(mayBeAddedRecord, neededRecord, projSelDTO);
                projDTOList.addAll(nextLevelValueList);
                nextLevelValueList = null;
            }
        }
        LOGGER.info("Ends getSalesProjectionResultsMandated");
        return projDTOList;
    }

    public List<SalesProjectionResultsDTO> configureLevelsMandated(int start, int offset, ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("Entering configureLevelsMandated");
        int neededRecord = offset;

        List<SalesProjectionResultsDTO> resultList = new ArrayList<SalesProjectionResultsDTO>();
        if (neededRecord > 0) {
            List<Leveldto> levelList = SPRCommonLogic.getConditionalLevelList(projSelDTO.getProjectionId(), start, offset, projSelDTO.getHierarchyIndicator(), projSelDTO.getLevelNo(), projSelDTO.getHierarchyNo(), projSelDTO.getProductHierarchyNo(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.isIsFilter(), false, projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomId(), projSelDTO.isFilterDdlb(), projSelDTO.getLevelName());

            for (int i = 0; i < levelList.size() && neededRecord > 0; i++) {
                Leveldto levelDto = levelList.get(i);

                SalesProjectionResultsDTO dto = new SalesProjectionResultsDTO();
                dto.setLevelNo(levelDto.getLevelNo());
                dto.setTreeLevelNo(levelDto.getTreeLevelNo());
                dto.setParentNode(levelDto.getParentNode());
                dto.setGroup(levelDto.getRelationshipLevelValue());
                dto.setLevelValue(levelDto.getRelationshipLevelValue());
                dto.setHierarchyNo(levelDto.getHierarchyNo());
                dto.setRelationshipLevelName(projSelDTO.getSessionDTO().getLevelValueDiscription(dto.getHierarchyNo(),levelDto.getHierarchyIndicator()));
                dto.setHierarchyIndicator(levelDto.getHierarchyIndicator());
                if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                    dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                    dto.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
                } else if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                    dto.setProductHierarchyNo(dto.getHierarchyNo());
                    dto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
                }
                dto.setCustomLevelNo(projSelDTO.getCustomLevelNo());
                dto.setOnExpandTotalRow(1);
                dto.setParent(1);
                resultList.add(dto);
                neededRecord--;
            }
            levelList = null;

        }
        LOGGER.info("Ends configureLevelsMandated");
        return resultList;
    }

    public List<SalesProjectionResultsDTO> getProjectionTotalMandated(Object[] orderedArgs, ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("Enetring getProjectionTotalMandated");
        List<SalesProjectionResultsDTO> projDTOList = new ArrayList<SalesProjectionResultsDTO>();
        List<Object[]> gtsList = SPRCommonLogic.callProcedure("PRC_M_PROJECTION_RESULTS", orderedArgs);
        if (gtsList != null) {
            projDTOList = getCustomizedProjectionTotalMandated(gtsList, projSelDTO);
        }
        gtsList = null;
        LOGGER.info("Ending getProjectionTotalMandated");
        return projDTOList;

    }

    public List<SalesProjectionResultsDTO> getContractSalesAndUnitsMandated(ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("Entering getContractSalesAndUnitsMandated");
        projSelDTO.setSales(Constant.SALES_WHOLE_CAPS);
        List<Object> list = (List<Object>) SPRCommonLogic.executeSelectQuery(getSalesProjectionResultsSalesQueryMandated(projSelDTO), null, null);
        List<SalesProjectionResultsDTO> projDTOList = getCustomizedSalesProjectionResultsSalesMandated(list, projSelDTO);
        list = null;
        LOGGER.info("Ends getContractSalesAndUnitsMandated");
        return projDTOList;

    }

    public List<SalesProjectionResultsDTO> getProjectionPivotTotalMandated(Object[] orderedArgs, ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("Entering getProjectionPivotTotalMandated");
        List<SalesProjectionResultsDTO> projDTOList = new ArrayList<SalesProjectionResultsDTO>();
        List<Object[]> gtsList = SPRCommonLogic.callProcedure("PRC_M_PROJECTION_RESULTS", orderedArgs);
        List<Object[]> discountList = new ArrayList<Object[]>();
        projDTOList = getCustomizedProjectionPivotTotalMandated(gtsList, discountList, projSelDTO);
        gtsList = null;
        LOGGER.info("Ends getProjectionPivotTotalMandated");
        return projDTOList;

    }

    public List<SalesProjectionResultsDTO> getCustomizedProjectionPivotTotalMandated(List<Object[]> list, List<Object[]> discountList, ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("Entering getCustomizedProjectionPivotTotalMandated");
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<SalesProjectionResultsDTO> projDTOList = new ArrayList<SalesProjectionResultsDTO>();
        List<String> periodList = new ArrayList<String>(projSelDTO.getPeriodList());
        int discountIndex = 0;
        int col = 4;
        int dcol = 2;
        if (frequencyDivision != 1) {
            col = col + 1;
            dcol = dcol + 1;
        }
        if (projSelDTO.getProjectionOrder().equalsIgnoreCase(Constant.DESCENDING)) {
            Collections.reverse(list);
        }

        for (Object[] row : list) {

            String column = StringUtils.EMPTY;
            int year = Integer.valueOf(String.valueOf(row[col - 1]));
            int period = Integer.valueOf(String.valueOf(row[3]));
            List<String> common = new ArrayList<>();
            if ("SPR".equals(projSelDTO.getProjTabName())) {
                common = getCommonColumnHeaderSPR(frequencyDivision, year, period);
            } else {
                common = getCommonColumnHeader(frequencyDivision, year, period);
            }

            String pcommonColumn = common.get(0);
            String commonHeader = common.get(1);
            String commonColumn = StringUtils.EMPTY;
            if (periodList.contains(pcommonColumn)) {
                periodList.remove(pcommonColumn);
                SalesProjectionResultsDTO projDTO = new SalesProjectionResultsDTO();
                projDTO.setLevelValue(commonHeader);
                projDTO.setRelationshipLevelName(commonHeader);
                String value = Constant.NULL;
                commonColumn = "efs";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[1];
                    value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[2];
                    value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                }

                commonColumn = "dms";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[25];
                    value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[26];
                    value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                }

                commonColumn = "iws";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[27];
                    value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[28];
                    value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                }

                commonColumn = "csw";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col];
                    value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 1];
                    value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                }
                commonColumn = "uv";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 2];
                    value = getFormatValue(UNITVOLUME, value, StringUtils.EMPTY);
                    projDTO.addStringProperties(column, value);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 3];
                    value = getFormatValue(UNITVOLUME, value, StringUtils.EMPTY);
                    projDTO.addStringProperties(column, value);
                }
                projDTO.setParent(0);
                projDTO.setProjectionTotal(1);
                projDTOList.add(projDTO);
            }

        }
        list = null;
        for (String ob : periodList) {
            SalesProjectionResultsDTO projDTO = new SalesProjectionResultsDTO();
            projDTO.setParent(0);
            projDTO.setProjectionTotal(1);
            projDTO.setLevelValue(projSelDTO.getPeriodListMap().get(ob));
            projDTOList.add(projDTO);
        }

        LOGGER.info("Ends getCustomizedProjectionPivotTotalMandated");
        return projDTOList;

    }

    public List<SalesProjectionResultsDTO> getProjectionPivotMandated(ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("Entering getProjectionPivotMandated");
        List<SalesProjectionResultsDTO> projDTOList = new ArrayList<SalesProjectionResultsDTO>();
        List<Object[]> gtsList = (List<Object[]>) SPRCommonLogic.executeSelectQuery(getSalesProjectionResultsSalesQueryMandated(projSelDTO), null, null);
        List<Object> discountList = new ArrayList<Object>();
        projDTOList = getCustomizedProjectionPivotMandated(gtsList, projSelDTO);
        gtsList = null;
        if (projSelDTO.getProjectionOrder().equalsIgnoreCase(Constant.DESCENDING)) {
            Collections.reverse(projDTOList);
        }
        LOGGER.info("Ends getProjectionPivotMandated");
        return projDTOList;
    }

    public List<SalesProjectionResultsDTO> getCustomizedProjectionTotalMandated(List<Object[]> list, ProjectionSelectionDTO projSelDTO) {

        int frequencyDivision = projSelDTO.getFrequencyDivision();
        String salesOrUnits = projSelDTO.getSalesOrUnit();
        List<SalesProjectionResultsDTO> projDTOList = new ArrayList<SalesProjectionResultsDTO>();
        SalesProjectionResultsDTO exFactorySalesDTO = new SalesProjectionResultsDTO();
        SalesProjectionResultsDTO demandSalesDTO = new SalesProjectionResultsDTO();
        SalesProjectionResultsDTO inventoryWithdrawDTO = new SalesProjectionResultsDTO();
        SalesProjectionResultsDTO conSaleDTO = new SalesProjectionResultsDTO();
        SalesProjectionResultsDTO unitVolDTO = new SalesProjectionResultsDTO();
        exFactorySalesDTO.setParent(0);
        exFactorySalesDTO.setRelationshipLevelName(EX_FACTORY_SALES.getConstant());

        demandSalesDTO.setParent(0);
        demandSalesDTO.setRelationshipLevelName(DEMAND_SALES.getConstant());

        inventoryWithdrawDTO.setParent(0);
        inventoryWithdrawDTO.setRelationshipLevelName(INVENTORY_WITHDRAW.getConstant());

        conSaleDTO.setParent(0);
        conSaleDTO.setRelationshipLevelName(CONTRACT_SALES_AT_WAC.getConstant());

        unitVolDTO.setParent(0);
        unitVolDTO.setRelationshipLevelName(UNIT_VOL.getConstant());

        if (list != null && !list.isEmpty()) {
            int col = 4;
            if (frequencyDivision != 1) {
                col = col + 1;
            }
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column = StringUtils.EMPTY;
                int year = Integer.valueOf(String.valueOf(obj[col - 1]));
                int period = Integer.valueOf(String.valueOf(obj[3]));
                List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = common.get(0);

                if (Constant.SALES.equalsIgnoreCase(salesOrUnits) || Constant.BOTH_SMALL.equalsIgnoreCase(salesOrUnits)) {
                    String exFactoryActual = StringUtils.EMPTY + obj[1];
                    exFactoryActual = getFormatValue(TWO_DECIMAL, exFactoryActual, CURRENCY);
                    exFactorySalesDTO.addStringProperties(commonColumn + Constant.ACTUALS, exFactoryActual);
                    String exFactoryProjection = StringUtils.EMPTY + obj[2];
                    exFactoryProjection = getFormatValue(TWO_DECIMAL, exFactoryProjection, CURRENCY);
                    exFactorySalesDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, exFactoryProjection);

                    String demandActual = StringUtils.EMPTY + obj[25];
                    demandActual = getFormatValue(TWO_DECIMAL, demandActual, CURRENCY);
                    demandSalesDTO.addStringProperties(commonColumn + Constant.ACTUALS, demandActual);
                    String demandProjection = StringUtils.EMPTY + obj[22];
                    demandProjection = getFormatValue(TWO_DECIMAL, demandProjection, CURRENCY);
                    demandSalesDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, demandProjection);

                    String inventoryWithdrawActual = StringUtils.EMPTY + obj[27];
                    inventoryWithdrawActual = getFormatValue(TWO_DECIMAL, inventoryWithdrawActual, CURRENCY);
                    inventoryWithdrawDTO.addStringProperties(commonColumn + Constant.ACTUALS, inventoryWithdrawActual);
                    String inventoryWithdrawProjection = StringUtils.EMPTY + obj[28];
                    inventoryWithdrawProjection = getFormatValue(TWO_DECIMAL, inventoryWithdrawProjection, CURRENCY);
                    inventoryWithdrawDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, inventoryWithdrawProjection);

                    String cswActuals = StringUtils.EMPTY + obj[col];
                    cswActuals = getFormatValue(TWO_DECIMAL, cswActuals, CURRENCY);
                    conSaleDTO.addStringProperties(commonColumn + Constant.ACTUALS, cswActuals);
                    String cswProjections = StringUtils.EMPTY + obj[col + 1];
                    cswProjections = getFormatValue(TWO_DECIMAL, cswProjections, CURRENCY);
                    conSaleDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, cswProjections);
                }
                if (Constant.UNITS.equalsIgnoreCase(salesOrUnits) || Constant.BOTH_SMALL.equalsIgnoreCase(salesOrUnits)) {
                    String uvActuals = StringUtils.EMPTY + obj[col + 2];
                    uvActuals = getFormatValue(UNITVOLUME, uvActuals, StringUtils.EMPTY);
                    unitVolDTO.addStringProperties(commonColumn + Constant.ACTUALS, uvActuals);
                    String uvProjections = StringUtils.EMPTY + obj[col + 3];
                    uvProjections = getFormatValue(UNITVOLUME, uvProjections, StringUtils.EMPTY);
                    unitVolDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, uvProjections);
                }
            }

        }
        list = null;

        projDTOList.add(exFactorySalesDTO);
        projDTOList.add(demandSalesDTO);
        projDTOList.add(inventoryWithdrawDTO);
        projDTOList.add(conSaleDTO);
        projDTOList.add(unitVolDTO);
        return projDTOList;
    }

    public String getSalesProjectionResultsSalesQueryMandated(ProjectionSelectionDTO projSelDTO) {

        String viewtable = SPRCommonLogic.getViewTableName(projSelDTO.getHierarchyIndicator());

        String selectClause = " select ";
        String whereClause = StringUtils.EMPTY;
        String groupBy = ",H.LEVEL_NAME , I.\"YEAR\"";
        String customQuery = StringUtils.EMPTY;
        String ccpWhereCond = getCCPWhereConditionQueryMandated("H", "E", "CCP");
        String historyQuery = StringUtils.EMPTY;
        String futureQuery = StringUtils.EMPTY;

        String parentNode = projSelDTO.getParentNode();
        whereClause = " and H.PARENT_NODE = '" + parentNode + "'";
        String levelValue = projSelDTO.getLevelValue();
        whereClause += " and H.RELATIONSHIP_LEVEL_VALUES = '" + levelValue + "'";
        groupBy = ", H.RELATIONSHIP_LEVEL_VALUES " + groupBy;
        selectClause += "I.\"YEAR\" , ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == 4 || Constant.QUARTERLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
            selectClause += "I.QUARTER, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == 2 || Constant.SEMIANNUALLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
            selectClause += "I.SEMI_ANNUAL, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1 || "annually".equalsIgnoreCase(projSelDTO.getFrequency())) {
            selectClause += "'0' as ANNUAL, ";
            whereClause += StringUtils.EMPTY;
            groupBy += StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == 12 || Constant.MONTHLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
            selectClause += "I.\"MONTH\", ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.\"MONTH\"";
        }

        String ccpTempInsert = SPRCommonLogic.getCCPQueryForPR(projSelDTO);       
        String customSql = "  ST_M_SALES_PROJECTION_MASTER B,"
                + " PROJECTION_DETAILS E , PROJECTION_MASTER F, " + viewtable + " G, RELATIONSHIP_LEVEL_DEFINITION H, \"PERIOD\" I, @CCP CCP " 
                + "where A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID and B.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID "
                + getUserSessionQueryConditionMandated(String.valueOf(projSelDTO.getUserId()), String.valueOf(projSelDTO.getSessionId()), "B")
                + getUserSessionQueryConditionMandated(String.valueOf(projSelDTO.getUserId()), String.valueOf(projSelDTO.getSessionId()), "A")
                + "and E.PROJECTION_MASTER_SID = F.PROJECTION_MASTER_SID and F.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + " and G.PROJECTION_MASTER_SID = F.PROJECTION_MASTER_SID "
                + "and G.RELATIONSHIP_LEVEL_SID = H.RELATIONSHIP_LEVEL_SID " + ccpWhereCond + "and A.PERIOD_SID = I.PERIOD_SID " + " and H.LEVEL_NO = " + projSelDTO.getLevelNo()
                + whereClause + " group by H.LEVEL_NO " + groupBy;

        historyQuery = selectClause + " sum(A.ACTUAL_SALES)as ACTUAL_SALES, 0 as PROJECTION_SALES, sum(A.ACTUAL_UNITS)as ACTUAL_UNITS, 0 as PROJECTION_UNITS,'ACT' AS Flag  from ST_M_ACTUAL_SALES A," + customSql;
        futureQuery = selectClause + " 0 as ACTUAL_SALES, sum(A.PROJECTION_SALES) as PROJECTION_SALES, 0 as ACTUAL_UNITS, sum(A.PROJECTION_UNITS) as PROJECTION_UNITS,'PROJ' AS Flag from ST_M_SALES_PROJECTION A," + customSql;

        customQuery = ccpTempInsert + historyQuery + " Union " + futureQuery;

        return customQuery;
    }

    public List<SalesProjectionResultsDTO> getCustomizedSalesProjectionResultsSalesMandated(List<Object> list, ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("Entering getCustomizedSalesProjectionResultsSalesMandated");
        List<SalesProjectionResultsDTO> projDtoList = new ArrayList<SalesProjectionResultsDTO>();
        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        columnList.remove("levelValue");
        SalesProjectionResultsDTO projSalesDTO = new SalesProjectionResultsDTO();
        SalesProjectionResultsDTO projUnitDTO = new SalesProjectionResultsDTO();
        projSalesDTO.setLevelNo(projSelDTO.getLevelNo());
        projSalesDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        projSalesDTO.setRelationshipLevelName(CONTRACT_SALES_AT_WAC.getConstant());
        projUnitDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        projUnitDTO.setRelationshipLevelName(UNIT_VOL.getConstant());
        projSalesDTO.setParent(0);
        projUnitDTO.setParent(0);
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        if (Constant.QUARTERLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
            frequencyDivision = 4;
        } else if (Constant.MONTHLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
            frequencyDivision = 12;
        } else if (Constant.SEMIANNUALLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
            frequencyDivision = 2;
        } else {
            frequencyDivision = 1;
        }
        String projections = projSelDTO.getActualsOrProjections();
        String salesOrUnits = projSelDTO.getSalesOrUnit();

        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                int year = Integer.valueOf(String.valueOf(obj[0]));
                int period = Integer.valueOf(String.valueOf(obj[1]));
                List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = common.get(0);
                int col = 2;
                if (Constant.SALES.equalsIgnoreCase(salesOrUnits) || Constant.BOTH_SMALL.equalsIgnoreCase(salesOrUnits)) {
                    String actual = StringUtils.EMPTY + obj[col];
                    actual = getFormatValue(TWO_DECIMAL, actual, CURRENCY);
                    projSalesDTO.addStringProperties(commonColumn + Constant.ACTUALS, actual);
                    String projection = StringUtils.EMPTY + obj[col + 1];
                    projection = getFormatValue(TWO_DECIMAL, projection, CURRENCY);
                    projSalesDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, projection);
                    columnList.remove(commonColumn + Constant.ACTUALS);
                    columnList.remove(commonColumn + Constant.PROJECTIONS);
                }
                if (Constant.UNITS.equalsIgnoreCase(salesOrUnits) || Constant.BOTH_SMALL.equalsIgnoreCase(salesOrUnits)) {
                    String actual = StringUtils.EMPTY + obj[col + 2];
                    actual = getFormatValue(UNITVOLUME, actual, StringUtils.EMPTY);
                    projUnitDTO.addStringProperties(commonColumn + Constant.ACTUALS, actual);
                    String projection = StringUtils.EMPTY + obj[col + 3];
                    projection = getFormatValue(UNITVOLUME, projection, StringUtils.EMPTY);
                    projUnitDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, projection);
                    columnList.remove(commonColumn + Constant.ACTUALS);
                    columnList.remove(commonColumn + Constant.PROJECTIONS);
                }
            }

        }
        for (String columns : columnList) {
            projSalesDTO.addStringProperties(columns, getFormatValue(TWO_DECIMAL, Constant.NULL, CURRENCY));
            projUnitDTO.addStringProperties(columns, getFormatValue(TWO_DECIMAL, Constant.NULL, StringUtils.EMPTY));
        }
        list = null;

        projDtoList.add(projSalesDTO);
        projDtoList.add(projUnitDTO);
        LOGGER.info("Ends getCustomizedSalesProjectionResultsSalesMandated");
        return projDtoList;
    }

    public String getFormatValue(DecimalFormat FORMAT, String value, String appendChar) {
        if (value.contains(Constant.NULL)) {
            value = "...";
        } else {
            if (CURRENCY.equals(appendChar)) {
                value = appendChar.concat(FORMAT.format(Double.valueOf(value)));
            } else {
                value = FORMAT.format(Double.valueOf(value)).concat(appendChar);
            }
        }
        return value;
    }

    public static List<String> getCommonColumnHeaderMandated(int frequencyDivision, int year, int period) {
        List<String> common = new ArrayList<String>();
        String commonColumn = StringUtils.EMPTY;
        String commonHeader = StringUtils.EMPTY;
        if (frequencyDivision == 1) {
            commonColumn = StringUtils.EMPTY + year;
            commonHeader = StringUtils.EMPTY + year;
        } else if (frequencyDivision == 4) {
            commonColumn = Constant.Q_SMALL + period + StringUtils.EMPTY + year;
            commonHeader = Constant.Q + period + " " + year;
        } else if (frequencyDivision == 2) {
            commonColumn = Constant.S_SMALL + period + StringUtils.EMPTY + year;
            commonHeader = Constant.S + period + " " + year;
        } else if (frequencyDivision == 12) {
            String monthName = getMonthForInt(period - 1);
            commonColumn = monthName.toLowerCase() + year;
            commonHeader = monthName + " " + year;
        }
        common.add(commonColumn);
        common.add(commonHeader);
        return common;
    }

    public String getCCPWhereConditionQueryMandated(String relationShipLevelDefination, String projectionDetails, String CCP) {
        String ccpWhereCond = " and " + relationShipLevelDefination + ".RELATIONSHIP_LEVEL_SID =" + CCP + ".RELATIONSHIP_LEVEL_SID and " + CCP + ".CCP_DETAILS_SID=" + projectionDetails + ".CCP_DETAILS_SID ";
        return ccpWhereCond;
    }

    public String getUserSessionQueryConditionMandated(String userId, String sessionId, String table) {
        String user = " and " + table + ".USER_ID=" + userId + " and " + table + ".SESSION_ID=" + sessionId + " ";
        return user;
    }

    private void getCustomizeSalesResultsMandated(List<Object> list, SalesProjectionResultsDTO projDTO, ProjectionSelectionDTO projSelDTO, String checkYear, String commonColumn, List<String> periodList) {
        String column = StringUtils.EMPTY;
        int col = 2;
        if (!checkYear.isEmpty() && checkYear != null) {
            for (Object rows : list) {
                final Object[] row = (Object[]) rows;
                int year = Integer.valueOf(String.valueOf(row[0]));
                int period = Integer.valueOf(String.valueOf(row[1]));
                String flag = String.valueOf(row[6]);
                List<String> common = getCommonColumnHeader(projSelDTO.getFrequencyDivision(), year, period);
                String pcommonColumn = common.get(0);
                String commonHeader = common.get(1);

                if (!checkYear.isEmpty() && pcommonColumn.equalsIgnoreCase(checkYear.replace(" ", StringUtils.EMPTY))) {

                    projDTO.setRelationshipLevelName(commonHeader);
                    String value = Constant.NULL;
                    commonColumn = "gts";
                    value = "...";
                    if (Constant.ACT.equals(flag)) {
                        column = commonColumn + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            projDTO.addStringProperties(column, value);
                        }
                    } else {
                        column = commonColumn + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            projDTO.addStringProperties(column, value);
                        }
                    }

                    commonColumn = "csw";
                    if (Constant.ACT.equals(flag)) {
                        column = commonColumn + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + row[col];
                            value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
                            projDTO.addStringProperties(column, value);
                        }
                    } else {
                        column = commonColumn + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + row[col + 1];
                            value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
                            projDTO.addStringProperties(column, value);
                        }
                    }

                    commonColumn = "uv";
                    if (Constant.ACT.equals(flag)) {
                        column = commonColumn + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + row[col + 2];
                            value = getFormatValue(UNITVOLUME, value, StringUtils.EMPTY);
                            projDTO.addStringProperties(column, value);
                        }
                    } else {
                        column = commonColumn + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + row[col + 3];

                            value = getFormatValue(UNITVOLUME, value, StringUtils.EMPTY);
                            projDTO.addStringProperties(column, value);
                        }
                    }

                }

            }

        }

    }

    public List<SalesProjectionResultsDTO> getCustomizedProjectionPivotMandated(List<Object[]> list, ProjectionSelectionDTO projSelDTO) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<SalesProjectionResultsDTO> projDTOList = new ArrayList<SalesProjectionResultsDTO>();
        List<String> periodList = new ArrayList<String>(projSelDTO.getPeriodList());
        String oldcommonCol = "nothing";
        SalesProjectionResultsDTO projDTO = new SalesProjectionResultsDTO();
        boolean first = true;
        for (Object[] row : list) {

            String column = StringUtils.EMPTY;
            int year = Integer.valueOf(String.valueOf(row[0]));
            int period = Integer.valueOf(String.valueOf(row[1]));
            List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
            String pcommonColumn = common.get(0);
            String commonHeader = common.get(1);
            String commonColumn = StringUtils.EMPTY;

            if (periodList.contains(pcommonColumn)) {
                if (!oldcommonCol.equals(pcommonColumn) && !first) {
                    periodList.remove(oldcommonCol);
                    oldcommonCol = pcommonColumn;
                    projDTO = new SalesProjectionResultsDTO();
                    projDTOList.add(projDTO);
                }
                projDTO.setParent(0);
                projDTO.setProjectionTotal(0);
                if (first) {
                    oldcommonCol = pcommonColumn;
                    projDTOList.add(projDTO);
                }
                first = false;
                projDTO.setLevelValue(commonHeader);
                projDTO.setRelationshipLevelName(commonHeader);
                String value = Constant.NULL;
                commonColumn = "gts";

                String flag = StringUtils.EMPTY + row[6];
                if (Constant.ACT.equals(flag)) {
                    column = commonColumn + ACTUALS.getConstant();
                    if (projSelDTO.hasColumn(column)) {
                        value = Constant.NULL;
                        value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
                        projDTO.addStringProperties(column, value);
                    }
                } else {

                    column = commonColumn + PROJECTIONS.getConstant();
                    if (projSelDTO.hasColumn(column)) {
                        value = Constant.NULL;
                        value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
                        projDTO.addStringProperties(column, value);
                    }
                }
                commonColumn = "csw";
                if (Constant.ACT.equals(flag)) {
                    column = commonColumn + ACTUALS.getConstant();
                    if (projSelDTO.hasColumn(column)) {
                        value = StringUtils.EMPTY + row[2];
                        value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
                        projDTO.addStringProperties(column, value);
                    }
                } else {
                    column = commonColumn + PROJECTIONS.getConstant();
                    if (projSelDTO.hasColumn(column)) {
                        value = StringUtils.EMPTY + row[3];
                        value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
                        projDTO.addStringProperties(column, value);
                    }
                }
                commonColumn = "uv";
                if (Constant.ACT.equals(flag)) {
                    column = commonColumn + ACTUALS.getConstant();
                    if (projSelDTO.hasColumn(column)) {
                        value = StringUtils.EMPTY + row[4];
                        value = getFormatValue(UNITVOLUME, value, StringUtils.EMPTY);
                        projDTO.addStringProperties(column, value);
                    }
                } else {
                    column = commonColumn + PROJECTIONS.getConstant();
                    if (projSelDTO.hasColumn(column)) {
                        value = StringUtils.EMPTY + row[5];
                        value = getFormatValue(UNITVOLUME, value, StringUtils.EMPTY);
                        projDTO.addStringProperties(column, value);
                    }
                }

            }

        }
        periodList.remove(oldcommonCol);
        list = null;
        for (String ob : periodList) {
            projDTO = new SalesProjectionResultsDTO();
            projDTO.setParent(0);
            projDTO.setProjectionTotal(0);
            projDTO.setLevelValue(projSelDTO.getPeriodListMap().get(ob));
            projDTO.setRelationshipLevelName(projSelDTO.getPeriodListMap().get(ob));
            projDTOList.add(projDTO);
        }
        return projDTOList;
    }

//channels method  start..
    public int getConfiguredSalesProjectionResultsCountChannel(Object parentId, ProjectionSelectionDTO projSelDTO, boolean isLevelCount) {
        int count = 0;
        if (!projSelDTO.isIsFilter() || (parentId instanceof SalesProjectionResultsDTO)) {
            projSelDTO.setYear(Constant.All);

            if (projSelDTO.getActualsOrProjections().equals(Constant.BOTH)) {
                projSelDTO.setActualsOrProjections("Actuals and Projections");
            }

            if (parentId instanceof SalesProjectionResultsDTO) {
                projSelDTO.setIsProjectionTotal(false);
                SalesProjectionResultsDTO parentDto = (SalesProjectionResultsDTO) parentId;
                projSelDTO.setLevelNo(parentDto.getLevelNo());
                projSelDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
                projSelDTO.setLevelValue(parentDto.getLevelValue());
                projSelDTO.setParentNode(parentDto.getParentNode());
                projSelDTO.setHierarchyNo(parentDto.getHierarchyNo());
                if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equalsIgnoreCase(parentDto.getHierarchyIndicator())) {
                    projSelDTO.setCustomerHierarchyNo(projSelDTO.getHierarchyNo());
                    projSelDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
                } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equalsIgnoreCase(parentDto.getHierarchyIndicator())) {
                    projSelDTO.setProductHierarchyNo(projSelDTO.getHierarchyNo());
                    projSelDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                }
                projSelDTO.setHierarchyIndicator(parentDto.getHierarchyIndicator());
                projSelDTO.setGroup(parentDto.getGroup());
                projSelDTO.setIsTotal(parentDto.getOnExpandTotalRow() == 1);
            } else {

                projSelDTO.setIsProjectionTotal(true);
                if (projSelDTO.isIsCustomHierarchy()) {
                    projSelDTO.setLevelNo(0);
                    projSelDTO.setTreeLevelNo(0);
                } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                    projSelDTO.setTreeLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(projSelDTO.getProductLevelNo() - 1);
                    projSelDTO.setTreeLevelNo(projSelDTO.getProductLevelNo() - 1);
                }
                projSelDTO.setGroup(StringUtils.EMPTY);
                projSelDTO.setHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setProductHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
            }
            count += getProjectionResultsCountChannel(projSelDTO, isLevelCount);
        } else {
            projSelDTO.setIsProjectionTotal(false);
            projSelDTO.setLevelNo(projSelDTO.getFilterLevelNo());
            projSelDTO.setTreeLevelNo(projSelDTO.getFilterLevelNo());
            count += configureLevelsCountChannel(projSelDTO);
        }
        return count;
    }

    public int getProjectionResultsCountChannel(ProjectionSelectionDTO projSelDTO, boolean isLevelCount) {
        int count = 0;

        if (projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
            count = 1;
            String salesUnits = projSelDTO.getSalesOrUnit();
            if (salesUnits.equals(Constant.BOTH)) {
                count++;
            }
            if (projSelDTO.isIsProjectionTotal()) {
                count = count + 2;
            }
        } else {
            if (projSelDTO.isIsProjectionTotal()) {
                count = count + 1;
            }
            count = count + projSelDTO.getPeriodList().size();
        }
        if (isLevelCount && !projSelDTO.isIsFilter()) {
            projSelDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo() + 1);
            int levelCount = configureLevelsCountChannel(projSelDTO);;
            count = count + levelCount;
            projSelDTO.setLevelCount(levelCount);
        }

        return count;
    }

    public int configureLevelsCountChannel(ProjectionSelectionDTO projSelDTO) {

        return SPRCommonLogic.getLevelListCountChannel(projSelDTO.getProjectionId(), projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo(), projSelDTO.getHierarchyNo(), projSelDTO.getProductHierarchyNo(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.isIsFilter(), projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomId(), StringUtils.EMPTY, projSelDTO.getUserId(), projSelDTO.getSessionId(), projSelDTO.getCustRelationshipBuilderSid(), projSelDTO.getProdRelationshipBuilderSid());
    }

    public List<SalesProjectionResultsDTO> getConfiguredSalesProjectionResultsChannel(Object parentId, int start, int offset, ProjectionSelectionDTO projSelDTO, boolean excelFlag) {
        List<SalesProjectionResultsDTO> resultList = new ArrayList<SalesProjectionResultsDTO>();
        if (!projSelDTO.isIsFilter() || (parentId instanceof SalesProjectionResultsDTO)) {
            projSelDTO.setYear(Constant.All);

            if (projSelDTO.getActualsOrProjections().equals(Constant.BOTH)) {
                projSelDTO.setActualsOrProjections("Actuals and Projections");
            }

            if (parentId instanceof SalesProjectionResultsDTO) {
                projSelDTO.setIsProjectionTotal(false);
                SalesProjectionResultsDTO parentDto = (SalesProjectionResultsDTO) parentId;
                projSelDTO.setLevelNo(parentDto.getLevelNo());
                projSelDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
                projSelDTO.setLevelValue(parentDto.getLevelValue());
                projSelDTO.setParentNode(parentDto.getParentNode());
                projSelDTO.setHierarchyNo(parentDto.getHierarchyNo());
                if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equalsIgnoreCase(parentDto.getHierarchyIndicator())) {
                    projSelDTO.setCustomerHierarchyNo(projSelDTO.getHierarchyNo());
                    projSelDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
                } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equalsIgnoreCase(parentDto.getHierarchyIndicator())) {
                    projSelDTO.setProductHierarchyNo(projSelDTO.getHierarchyNo());
                    projSelDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                }
                projSelDTO.setHierarchyIndicator(parentDto.getHierarchyIndicator());
                projSelDTO.setGroup(parentDto.getGroup());
                projSelDTO.setIsTotal(parentDto.getOnExpandTotalRow() == 1);
            } else {
                projSelDTO.setIsProjectionTotal(true);
                if (projSelDTO.isIsCustomHierarchy()) {
                    projSelDTO.setLevelNo(0);
                    projSelDTO.setTreeLevelNo(0);
                } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                    projSelDTO.setTreeLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(projSelDTO.getProductLevelNo() - 1);
                    projSelDTO.setTreeLevelNo(projSelDTO.getProductLevelNo() - 1);
                }
                projSelDTO.setGroup(StringUtils.EMPTY);
                projSelDTO.setHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setProductHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
            }
            resultList = getSalesProjectionResultsChannel(start, offset, projSelDTO, excelFlag);
        } else {
            projSelDTO.setIsProjectionTotal(false);
            projSelDTO.setLevelNo(projSelDTO.getFilterLevelNo());
            projSelDTO.setTreeLevelNo(projSelDTO.getFilterLevelNo());
            resultList = configureLevelsChannel(start, offset, start, projSelDTO);
        }
        return resultList;
    }

    public List<SalesProjectionResultsDTO> getSalesProjectionResultsChannel(int start, int offset, ProjectionSelectionDTO projSelDTO, boolean excelFlag) {

        int neededRecord = offset;
        int started = start;
        int mayBeAdded = 0;
        List<SalesProjectionResultsDTO> projDTOList = new ArrayList<SalesProjectionResultsDTO>();
        String freq = StringUtils.EMPTY;
        if (projSelDTO.getFrequencyDivision() == 1 || Constant.ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
            freq = "ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 2 || Constant.SEMI_ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
            freq = "SEMI-ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 4 || Constant.QUARTERLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
            freq = "QUARTERLY";
        } else if (projSelDTO.getFrequencyDivision() == 12 || Constant.MONTHLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
            freq = "MONTHLY";
        }

        Object[] orderedArgs = {projSelDTO.getProjectionId(), freq, "ASSUMPTIONS", projSelDTO.getSessionId(), projSelDTO.getUserId()};
        if (projSelDTO.isIsProjectionTotal()) {

            if (started == 0) {
                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                    SalesProjectionResultsDTO dto = new SalesProjectionResultsDTO();
                    dto.setLevelValue(Constant.PROJECTION_TOTAL);
                    dto.setParent(0);
                    projDTOList.add(dto);
                }
                neededRecord--;
                started++;
            }
            mayBeAdded++;
        }
        if (neededRecord > 0 && projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
            String salesUnits = projSelDTO.getSalesOrUnit();
            if (projSelDTO.isIsProjectionTotal()) {
                mayBeAdded++;
                if (projectionTotalList.isEmpty()) {

                    getProjectionTotal(orderedArgs, projSelDTO);
                }
                if (started == 1 && neededRecord > 0) {
                    if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                        SalesProjectionResultsDTO gtsDto = projectionTotalList.get(0);
                        projDTOList.add(gtsDto);
                    }
                    started++;
                    neededRecord--;
                }

                if (neededRecord > 0 && (salesUnits.equals(Constant.BOTH) || salesUnits.equalsIgnoreCase(Constant.SALES_SMALL))) {
                    if (started == 2) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {

                            SalesProjectionResultsDTO contractSalesDto = projectionTotalList.get(1);
                            projDTOList.add(contractSalesDto);

                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                }

                if (neededRecord > 0 && (salesUnits.equals(Constant.BOTH) || salesUnits.equalsIgnoreCase(Constant.UNITS_SMALL))) {
                    if ((salesUnits.equals(Constant.BOTH) && started == 3) || started == 2) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            SalesProjectionResultsDTO unitVolDto = projectionTotalList.get(2);
                            projDTOList.add(unitVolDto);

                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                }
            } else if (neededRecord > 0) {
                SalesProjectionResultsDTO contractSalesDto = null;
                SalesProjectionResultsDTO unitVolDto = null;
                if ((salesUnits.equals(Constant.BOTH) && started < 2) || started < 1) {

                    List<SalesProjectionResultsDTO> contractSalesAndUnits = getContractSalesAndUnits(projSelDTO);
                    contractSalesDto = contractSalesAndUnits.get(0);
                    unitVolDto = contractSalesAndUnits.get(1);

                }
                if (salesUnits.equals(Constant.BOTH) || salesUnits.equalsIgnoreCase(Constant.SALES_SMALL)) {
                    if (started == 0) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(contractSalesDto);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                }
                if (neededRecord > 0 && (salesUnits.equals(Constant.BOTH) || salesUnits.equalsIgnoreCase(Constant.UNITS_SMALL))) {
                    if ((salesUnits.equals(Constant.BOTH) && started == 1) || started == 0) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(unitVolDto);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                }
            }
        } else if (neededRecord > 0) {
            int mayBeAddedRecord = started - mayBeAdded;
            if (mayBeAddedRecord < 0) {
                mayBeAddedRecord = 0;
            }
            if (mayBeAddedRecord < projSelDTO.getPeriodList().size()) {
                List<SalesProjectionResultsDTO> projectionDtoList = new ArrayList<SalesProjectionResultsDTO>();
                if (projSelDTO.isIsProjectionTotal()) {

                    projectionDtoList = getProjectionPivotTotal(orderedArgs, projSelDTO);
                } else {

                    projectionDtoList = getProjectionPivot(projSelDTO);
                }
                for (int k = mayBeAddedRecord; k < projectionDtoList.size() && neededRecord > 0; k++) {
                    if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + k)) {
                        projDTOList.add(projectionDtoList.get(k));
                    }
                    started++;
                    neededRecord--;
                }
            }
            mayBeAdded += projSelDTO.getPeriodList().size();
        }

        if (neededRecord > 0 && !projSelDTO.isIsFilter()) {

            int mayBeAddedRecord = started - mayBeAdded;
            if (mayBeAddedRecord < 0) {
                mayBeAddedRecord = 0;
            }
            projSelDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo() + 1);
            List<SalesProjectionResultsDTO> nextLevelValueList = configureLevelsChannel(mayBeAddedRecord, neededRecord, started, projSelDTO);
            projDTOList.addAll(nextLevelValueList);

        }
        return projDTOList;
    }

    public List<SalesProjectionResultsDTO> getContractSalesAndUnitsChannel(ProjectionSelectionDTO projSelDTO, boolean excelFlag) {
        projSelDTO.setSales(Constant.SALES_WHOLE_CAPS);
        List<Object> list = (List<Object>) SPRCommonLogic.executeSelectQuery(SPRCommonLogic.getCCPQueryChannel(projSelDTO) + " \n" + getSalesProjectionResultsSalesQueryChannel(projSelDTO), null, null);
        List<SalesProjectionResultsDTO> projDTOList = getCustomizedSalesProjectionResultsSalesChannel(list, projSelDTO, excelFlag);
        return projDTOList;
    }

    public List<SalesProjectionResultsDTO> getCustomizedSalesProjectionResultsSalesChannel(List<Object> list, ProjectionSelectionDTO projSelDTO, boolean excelFlag) {
        List<SalesProjectionResultsDTO> projDtoList = new ArrayList<SalesProjectionResultsDTO>();
        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        columnList.remove("levelValue");
        SalesProjectionResultsDTO projSalesDTO = new SalesProjectionResultsDTO();
        SalesProjectionResultsDTO projUnitDTO = new SalesProjectionResultsDTO();
        projSalesDTO.setLevelNo(projSelDTO.getLevelNo());
        projSalesDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        projSalesDTO.setLevelValue(CONTRACT_SALES_AT_WAC.getConstant());
        projUnitDTO.setLevelNo(projSelDTO.getLevelNo());
        projUnitDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        projUnitDTO.setLevelValue(UNIT_VOL.getConstant());
        projSalesDTO.setParent(0);
        projUnitDTO.setParent(0);
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        if (Constant.QUARTERLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
            frequencyDivision = 4;
        } else if (Constant.MONTHLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
            frequencyDivision = 12;
        } else if (Constant.SEMIANNUALLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
            frequencyDivision = 2;
        } else {
            frequencyDivision = 1;
        }
        String salesOrUnits = projSelDTO.getSalesOrUnit();
        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                int year = Integer.valueOf(String.valueOf(obj[0]));
                int period = Integer.valueOf(String.valueOf(obj[1]));
                List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = common.get(0);
                int col = 2;
                if (Constant.SALES.equalsIgnoreCase(salesOrUnits) || Constant.BOTH_SMALL.equalsIgnoreCase(salesOrUnits)) {
                    String actual = StringUtils.EMPTY + obj[col];
                    if (!excelFlag || (excelFlag && actual.contains(Constant.NULL))) {
                        actual = getFormattedValue(CUR_ZERO, actual);
                    }
                    projSalesDTO.addStringProperties(commonColumn + Constant.ACTUALS, actual);
                    String projection = StringUtils.EMPTY + obj[col + 1];
                    if (!excelFlag || (excelFlag && projection.contains(Constant.NULL))) {
                        projection = getFormattedValue(CUR_ZERO, projection);
                    }
                    projSalesDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, projection);
                    columnList.remove(commonColumn + Constant.ACTUALS);
                    columnList.remove(commonColumn + Constant.PROJECTIONS);
                }
                if (Constant.UNITS.equalsIgnoreCase(salesOrUnits) || Constant.BOTH_SMALL.equalsIgnoreCase(salesOrUnits)) {
                    String actual = StringUtils.EMPTY + obj[col + 2];
                    if (!excelFlag || (excelFlag && actual.contains(Constant.NULL))) {
                        actual = getFormattedValue(UNITVOLUME, actual);
                    }
                    projUnitDTO.addStringProperties(commonColumn + Constant.ACTUALS, actual);
                    String projection = StringUtils.EMPTY + obj[col + 3];
                    if (!excelFlag || (excelFlag && projection.contains(Constant.NULL))) {
                        projection = getFormattedValue(UNITVOLUME, projection);
                    }
                    projUnitDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, projection);
                    columnList.remove(commonColumn + Constant.ACTUALS);
                    columnList.remove(commonColumn + Constant.PROJECTIONS);
                }
            }

        }
        for (String columns : columnList) {
            projSalesDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
            projUnitDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
        }
        projDtoList.add(projSalesDTO);
        projDtoList.add(projUnitDTO);
        return projDtoList;
    }

    public List<SalesProjectionResultsDTO> configureLevelsChannel(int start, int offset, int started, ProjectionSelectionDTO projSelDTO) {
        int neededRecord = offset;

        List<SalesProjectionResultsDTO> resultList = new ArrayList<SalesProjectionResultsDTO>();
        if (neededRecord > 0) {

            List<Leveldto> levelList = SPRCommonLogic.getConditionalLevelListChannel(projSelDTO.getProjectionId(), start, offset, projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo(), projSelDTO.getHierarchyNo(), projSelDTO.getProductHierarchyNo(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.isIsFilter(), false, projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomId(), StringUtils.EMPTY, projSelDTO.getUserId(), projSelDTO.getSessionId(), projSelDTO.getCustRelationshipBuilderSid(), projSelDTO.getProdRelationshipBuilderSid(), false, true);

            for (int i = 0; i < levelList.size() && neededRecord > 0; i++) {
                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + (started + i))) {
                    Leveldto levelDto = levelList.get(i);

                    SalesProjectionResultsDTO dto = new SalesProjectionResultsDTO();
                    dto.setLevelNo(levelDto.getLevelNo());
                    dto.setTreeLevelNo(levelDto.getTreeLevelNo());
                    dto.setParentNode(levelDto.getParentNode());
                    dto.setGroup(projSelDTO.getSessionDTO().getLevelValueDiscription(levelDto.getHierarchyNo(), levelDto.getHierarchyIndicator()));
                    dto.setLevelValue(projSelDTO.getSessionDTO().getLevelValueDiscription(levelDto.getHierarchyNo(), levelDto.getHierarchyIndicator()));
                    dto.setHierarchyNo(levelDto.getHierarchyNo());
                    dto.setHierarchyIndicator(levelDto.getHierarchyIndicator());
                    if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                        dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                        dto.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
                    } else if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                        dto.setProductHierarchyNo(dto.getHierarchyNo());
                        dto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
                    }
                    dto.setOnExpandTotalRow(1);
                    dto.setParent(1);
                    resultList.add(dto);
                }
                started++;
                neededRecord--;
            }
        }
        return resultList;
    }

    public String getSalesProjectionResultsSalesQueryChannel(ProjectionSelectionDTO projSelDTO) {

        String selectClause = " select ";
        String whereClause = StringUtils.EMPTY;
        String groupBy = " I.\"YEAR\"\n";
        String customQuery = StringUtils.EMPTY;
        String ccpWhereCond = SPRCommonLogic.getCCPWhereConditionQueryChannel("E", "CCP");
        selectClause += "I.\"YEAR\" as YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == 4) {
            selectClause += "I.QUARTER as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == 12) {
            selectClause += "I.\"MONTH\" as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.\"MONTH\"";
        }

        // To filter the data according to selected period
        String periodFilter = SPRCommonLogic.getPeriodRestrictionQuery(projSelDTO);

        String customSql = "  ST_CH_SALES_PROJECTION_MASTER B,\n"
                + " PROJECTION_DETAILS E , \n"
                + " \"PERIOD\" I, \n"
                + " @CCP CCP "
                + "where A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID \n"
                + " and B.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID \n"
                + SPRCommonLogic.getUserSessionQueryCondition(projSelDTO.getUserId(), projSelDTO.getSessionId(), "B")
                + SPRCommonLogic.getUserSessionQueryCondition(projSelDTO.getUserId(), projSelDTO.getSessionId(), "A")
                + " and E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + ccpWhereCond + "and A.PERIOD_SID = I.PERIOD_SID \n"
                + periodFilter
                + whereClause + "\n"
                + " group by  " + groupBy;

        String historyQuery = selectClause + " sum(A.CONTRACT_SALES) as SALES_ACTUAL_SALES, \n"
                + " 0 as SALES_PROJECTION_SALES, \n"
                + " sum(A.CONTRACT_UNITS)as ACTUAL_UNITS, \n"
                + " 0 as PROJECTION_UNITS \n"
                + " from ST_CH_ACTUAL_SALES A,\n "
                + customSql;
        String futureQuery = selectClause + " 0 as SALES_ACTUAL_SALES, \n"
                + " sum(A.CONTRACT_SALES) as SALES_PROJECTION_SALES, \n"
                + " 0 as ACTUAL_UNITS, \n"
                + " sum(A.CONTRACT_UNITS) as PROJECTION_UNITS \n"
                + " from ST_CH_SALES_PROJECTION A,\n"
                + customSql;
        List<String> list = SPRCommonLogic.getCommonSelectWhereOrderGroupByClause("HISTORY", "FUTURE", "on");
        String finalWhereCond = list.get(1);
        String orderBy = list.get(2);
        String finalSelectClause = "select " + list.get(0) + "\n Isnull(HISTORY.SALES_ACTUAL_SALES, FUTURE.SALES_ACTUAL_SALES) as ACTUAL_SALES,\n Isnull(FUTURE.SALES_PROJECTION_SALES, HISTORY.SALES_PROJECTION_SALES) as PROJECTION_SALES,"
                + "\n Isnull(HISTORY.ACTUAL_UNITS, FUTURE.ACTUAL_UNITS) as ACTUAL_UNITS,\n Isnull(FUTURE.PROJECTION_UNITS, HISTORY.PROJECTION_UNITS) as PROJECTION_UNITS  ";

        customQuery = finalSelectClause + " from (\n" + historyQuery + "\n) HISTORY FULL OUTER JOIN (\n" + futureQuery + "\n) FUTURE \n" + finalWhereCond + " Order By " + orderBy;

        return customQuery;
    }

    public void getProjectionTotalChannel(Object[] orderedArgs, ProjectionSelectionDTO projSelDTO, boolean excelFlag) {
        List<Object[]> gtsList = SPRCommonLogic.callProcedure("PRC_CH_PROJECTION_RESULTS", orderedArgs);
        if (gtsList != null) {
            getCustomizedProjectionTotalChannel(gtsList, projSelDTO, excelFlag);
        }

    }

    public void getCustomizedProjectionTotalChannel(List<Object[]> list, ProjectionSelectionDTO projSelDTO, boolean excelFlag) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        String salesOrUnits = projSelDTO.getSalesOrUnit();
        List<SalesProjectionResultsDTO> projDTOList = new ArrayList<SalesProjectionResultsDTO>();
        SalesProjectionResultsDTO gtsDTO = new SalesProjectionResultsDTO();
        SalesProjectionResultsDTO conSaleDTO = new SalesProjectionResultsDTO();
        SalesProjectionResultsDTO unitVolDTO = new SalesProjectionResultsDTO();
        gtsDTO.setParent(0);
        gtsDTO.setLevelValue(GROSS_TRADE_SALES.getConstant());

        conSaleDTO.setParent(0);
        conSaleDTO.setLevelValue(CONTRACT_SALES_AT_WAC.getConstant());

        unitVolDTO.setParent(0);
        unitVolDTO.setLevelValue(UNIT_VOL.getConstant());

        if (list != null && !list.isEmpty()) {
            int col = 5;
            if (frequencyDivision != 1) {
                col = col + 1;
            }
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column = StringUtils.EMPTY;

                int year = Integer.valueOf(String.valueOf(obj[col - 2]));
                int period = Integer.valueOf(String.valueOf(obj[3]));
                List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = common.get(0);

                if (Constant.SALES.equalsIgnoreCase(salesOrUnits) || Constant.BOTH_SMALL.equalsIgnoreCase(salesOrUnits)) {
                    String gtsActual = StringUtils.EMPTY + obj[1];
                    if (!excelFlag || (excelFlag && gtsActual.contains(Constant.NULL))) {
                        gtsActual = getFormattedValue(CUR_ZERO, gtsActual);
                    }
                    gtsDTO.addStringProperties(commonColumn + Constant.ACTUALS, gtsActual);
                    String gtsProjection = StringUtils.EMPTY + obj[2];
                    if (!excelFlag || (excelFlag && gtsProjection.contains(Constant.NULL))) {
                        gtsProjection = getFormattedValue(CUR_ZERO, gtsProjection);
                    }
                    gtsDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, gtsProjection);
                    String cswActuals = StringUtils.EMPTY + obj[col + 1];
                    if (!excelFlag || (excelFlag && cswActuals.contains(Constant.NULL))) {
                        cswActuals = getFormattedValue(CUR_ZERO, cswActuals);
                    }
                    conSaleDTO.addStringProperties(commonColumn + Constant.ACTUALS, cswActuals);
                    String cswProjections = StringUtils.EMPTY + obj[col + 2];
                    if (!excelFlag || (excelFlag && cswProjections.contains(Constant.NULL))) {
                        cswProjections = getFormattedValue(CUR_ZERO, cswProjections);
                    }
                    conSaleDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, cswProjections);
                }
                if (Constant.UNITS.equalsIgnoreCase(salesOrUnits) || Constant.BOTH_SMALL.equalsIgnoreCase(salesOrUnits)) {
                    String uvActuals = StringUtils.EMPTY + obj[col + 3];
                    if (!excelFlag || (excelFlag && uvActuals.contains(Constant.NULL))) {
                        uvActuals = getFormattedValue(UNITVOLUME, uvActuals);
                    }
                    unitVolDTO.addStringProperties(commonColumn + Constant.ACTUALS, uvActuals);
                    String uvProjections = StringUtils.EMPTY + obj[col + 4];
                    if (!excelFlag || (excelFlag && uvProjections.contains(Constant.NULL))) {
                        uvProjections = getFormattedValue(UNITVOLUME, uvProjections);
                    }
                    unitVolDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, uvProjections);
                }
            }

        }
        projectionTotalList.clear();
        projectionTotalList.add(gtsDTO);
        projectionTotalList.add(conSaleDTO);
        projectionTotalList.add(unitVolDTO);
    }

    public List<SalesProjectionResultsDTO> getProjectionPivotTotalChannel(Object[] orderedArgs, ProjectionSelectionDTO projSelDTO, boolean excelFlag) {
        List<SalesProjectionResultsDTO> projDTOList = new ArrayList<SalesProjectionResultsDTO>();
        List<Object[]> gtsList = SPRCommonLogic.callProcedure("PRC_CH_PROJECTION_RESULTS", orderedArgs);
        List<Object[]> discountList = new ArrayList<Object[]>();
        projDTOList = getCustomizedProjectionPivotTotalChannel(gtsList, discountList, projSelDTO, excelFlag);
        return projDTOList;
    }

    public List<SalesProjectionResultsDTO> getCustomizedProjectionPivotTotalChannel(List<Object[]> list, List<Object[]> discountList, ProjectionSelectionDTO projSelDTO, boolean excelFlag) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<SalesProjectionResultsDTO> projDTOList = new ArrayList<SalesProjectionResultsDTO>();
        List<String> periodList = new ArrayList<String>(projSelDTO.getPeriodList());
        int col = 5;
        if (frequencyDivision != 1) {
            col = col + 1;
        }
        for (Object[] row : list) {

            String column = StringUtils.EMPTY;
            int year = Integer.valueOf(String.valueOf(row[col - 2]));
            int period = Integer.valueOf(String.valueOf(row[3]));
            List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
            String pcommonColumn = common.get(0);
            String commonHeader = common.get(1);
            String commonColumn = StringUtils.EMPTY;
            if (periodList.contains(pcommonColumn)) {
                periodList.remove(pcommonColumn);
                SalesProjectionResultsDTO projDTO = new SalesProjectionResultsDTO();
                projDTO.setLevelValue(commonHeader);
                String value = Constant.NULL;
                commonColumn = "gts";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[1];
                    if (!excelFlag || (excelFlag && value.contains(Constant.NULL))) {
                        value = getFormattedValue(CUR_ZERO, value);
                    }
                    projDTO.addStringProperties(column, value);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[2];
                    if (!excelFlag || (excelFlag && value.contains(Constant.NULL))) {
                        value = getFormattedValue(CUR_ZERO, value);
                    }
                    projDTO.addStringProperties(column, value);
                }
                commonColumn = "csw";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 1];
                    if (!excelFlag || (excelFlag && value.contains(Constant.NULL))) {
                        value = getFormattedValue(CUR_ZERO, value);
                    }
                    projDTO.addStringProperties(column, value);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 2];
                    if (!excelFlag || (excelFlag && value.contains(Constant.NULL))) {
                        value = getFormattedValue(CUR_ZERO, value);
                    }
                    projDTO.addStringProperties(column, value);
                }
                commonColumn = "uv";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 3];
                    if (!excelFlag || (excelFlag && value.contains(Constant.NULL))) {
                        value = getFormattedValue(UNITVOLUME, value);
                    }
                    projDTO.addStringProperties(column, value);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 4];
                    if (!excelFlag || (excelFlag && value.contains(Constant.NULL))) {
                        value = getFormattedValue(UNITVOLUME, value);
                    }
                    projDTO.addStringProperties(column, value);
                }
                projDTO.setParent(0);
                projDTO.setProjectionTotal(1);
                projDTOList.add(projDTO);
            }
        }

        for (String ob : periodList) {
            SalesProjectionResultsDTO projDTO = new SalesProjectionResultsDTO();
            projDTO.setParent(0);
            projDTO.setProjectionTotal(1);
            projDTO.setLevelValue(projSelDTO.getPeriodListMap().get(ob));
            projDTOList.add(projDTO);
        }
        if (!"ascending".equalsIgnoreCase(projSelDTO.getProjectionOrder())) {
            Collections.reverse(projDTOList);
        }
        return projDTOList;
    }

    public List<SalesProjectionResultsDTO> getProjectionPivotChannel(ProjectionSelectionDTO projSelDTO, boolean excelFlag) {
        List<SalesProjectionResultsDTO> projDTOList = new ArrayList<SalesProjectionResultsDTO>();
        List<Object> gtsList = (List<Object>) SPRCommonLogic.executeSelectQuery(SPRCommonLogic.getCCPQueryChannel(projSelDTO) + " \n" + getSalesProjectionResultsSalesQueryChannel(projSelDTO), null, null);
        List<Object> discountList = new ArrayList<Object>();
        projDTOList = getCustomizedProjectionPivotChannel(gtsList, discountList, projSelDTO, excelFlag);
        return projDTOList;
    }

    public List<SalesProjectionResultsDTO> getCustomizedProjectionPivotChannel(List<Object> list, List<Object> discountList, ProjectionSelectionDTO projSelDTO, boolean excelFlag) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<SalesProjectionResultsDTO> projDTOList = new ArrayList<SalesProjectionResultsDTO>();
        List<String> periodList = new ArrayList<String>(projSelDTO.getPeriodList());
        int col = 2;
        for (Object rows : list) {
            final Object[] row = (Object[]) rows;
            String column = StringUtils.EMPTY;
            int year = Integer.valueOf(String.valueOf(row[0]));
            int period = Integer.valueOf(String.valueOf(row[1]));
            List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
            String pcommonColumn = common.get(0);
            String commonHeader = common.get(1);
            String commonColumn = StringUtils.EMPTY;
            if (periodList.contains(pcommonColumn)) {
                periodList.remove(pcommonColumn);
                SalesProjectionResultsDTO projDTO = new SalesProjectionResultsDTO();
                List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
                columnList.remove("levelValue");
                projDTO.setLevelValue(commonHeader);
                String value = Constant.NULL;
                commonColumn = "gts";
                value = "...";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "csw";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col];
                    if (!excelFlag || (excelFlag && value.contains(Constant.NULL))) {
                        value = getFormattedValue(CUR_ZERO, value);
                    }
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 1];
                    if (!excelFlag || (excelFlag && value.contains(Constant.NULL))) {
                        value = getFormattedValue(CUR_ZERO, value);
                    }
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "uv";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 2];
                    if (!excelFlag || (excelFlag && value.contains(Constant.NULL))) {
                        value = getFormattedValue(UNITVOLUME, value);
                    }
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 3];
                    if (!excelFlag || (excelFlag && value.contains(Constant.NULL))) {
                        value = getFormattedValue(UNITVOLUME, value);
                    }
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                projDTO.setParent(0);
                projDTO.setProjectionTotal(1);
                for (String columns : columnList) {
                    projDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
                }
                projDTOList.add(projDTO);
            }
        }
        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        columnList.remove("levelValue");
        for (String ob : periodList) {
            SalesProjectionResultsDTO projDTO = new SalesProjectionResultsDTO();
            projDTO.setParent(0);
            projDTO.setProjectionTotal(1);
            projDTO.setLevelValue(projSelDTO.getPeriodListMap().get(ob));
            for (String columns : columnList) {
                projDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
            }
            projDTOList.add(projDTO);
        }
        if (!"ascending".equalsIgnoreCase(projSelDTO.getProjectionOrder())) {
            Collections.reverse(projDTOList);
        }
        return projDTOList;
    }
}
