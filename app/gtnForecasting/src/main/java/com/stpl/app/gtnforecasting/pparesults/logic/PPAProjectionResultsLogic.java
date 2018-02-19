/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.pparesults.logic;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.dao.PPAPrjectionResultsDAO;
import com.stpl.app.gtnforecasting.dao.impl.PPAProjectionResultsDAOImpl;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.pparesults.dto.PPADetailsDTO;
import com.stpl.app.gtnforecasting.pparesults.dto.PPAHelperDTO;
import com.stpl.app.gtnforecasting.pparesults.dto.PPAProjectionResultsDTO;
import com.stpl.app.gtnforecasting.queryUtils.PPAQuerys;
import com.stpl.app.gtnforecasting.salesprojection.utils.SalesUtils;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.HeaderUtils;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.serviceUtils.Constants;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.app.util.service.thread.ThreadPool;
import static com.stpl.app.utils.Constants.CommonConstants.DATE_FORMAT;
import com.stpl.app.utils.ExcelUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.QueryUtil;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author srithar
 */
public class PPAProjectionResultsLogic {

    private static final DecimalFormat TWO_DECIMAL = new DecimalFormat("#,##0.00");
    public static final String STRING_FORMAT_TWO = "#,##0";
    private static final DecimalFormat ZERO_DECIMAL = new DecimalFormat(STRING_FORMAT_TWO);
    private static final String CURRENCY = "$";
    private static final String PERCENTAGE = "%";
    private static final String Q = "Q";
    private static final String M = "M";
    private static final String A = "A";
    private String indicater = StringUtils.EMPTY;
    private static final Logger LOGGER = LoggerFactory.getLogger(PPAProjectionResultsLogic.class);
    private List chartList;
    private final ExecutorService service = ThreadPool.getInstance().getService();
    private List<Object[]> periodTableList=null;
    private List<Object[]> wacTableList=null;
    private List<Object[]> wacPriceTableList=null;
    private int currentfrquencyForWacReset=3;
    private int cureentWacFrquencyIndex=3;

    public List getChartList() {
        return chartList == null ? chartList : new ArrayList<>(chartList);
    }

    public void setChartList(List chartList) {
        this.chartList = chartList == null ? chartList : new ArrayList<>(chartList);
    }

    public String getIndicater() {
        return indicater;
    }

    public void setIndicater(String indicater) {
        this.indicater = indicater;

    }

    public void savePPAResultsView(String projectionId) throws PortalException,SystemException{
        PPAPrjectionResultsDAO dao = new PPAProjectionResultsDAOImpl();
        dao.savePPAProjectionView(indicater, projectionId);
    }

    public List ppaProjecetionResults(boolean isProjectionTotal, ProjectionSelectionDTO selection, SessionDTO session) {

        List inputForList = new ArrayList<>();
        selection.setProjectionHeaderList(CommonUtils.prepareProjectionPeriodList(selection));

        String frequency = selection.getFrequency();
        inputForList.add(frequency.substring(0, 1));

        String sql = null;
        PPAQuerys.setTableName(session.getCurrentTableNames());
        if (isProjectionTotal) {
            inputForList.add(StringUtils.EMPTY);
            inputForList.add(StringUtils.EMPTY);
            inputForList.add(StringUtils.EMPTY);
            inputForList.add(StringUtils.EMPTY);
        }else{
            CommonLogic commonLogic = new CommonLogic();
            sql = commonLogic.insertAvailableHierarchyNo(selection);
            String joinQuery = " JOIN #SELECTED_HIERARCHY_NO SHN ON SHN.CCP_DETAILS_SID = STA.CCP_DETAILS_SID ";
            inputForList.add(joinQuery);
            inputForList.add(joinQuery);
            inputForList.add(joinQuery);
            inputForList.add(joinQuery);
        }

        List list = PPAQuerys.getPPADataFromDB(inputForList, "ppa-results-total-query", sql);

        if (selection.getPivotView().equals(Constant.PERIOD)) {
            return getCustomizedList(list, selection);
        } else {
            return getCustomizedListForPivot(list, selection);
        }

    }

    private List getCustomizedList(List<Object[]> list, ProjectionSelectionDTO selection) {
        String frequency = selection.getFrequency();
        List<PPAProjectionResultsDTO> list1 = new ArrayList<>();
        PPAProjectionResultsDTO discountDoller = new PPAProjectionResultsDTO();
        PPAProjectionResultsDTO discountPercent = new PPAProjectionResultsDTO();
        PPAProjectionResultsDTO unitVolume = new PPAProjectionResultsDTO();
        PPAProjectionResultsDTO totalDiscount = new PPAProjectionResultsDTO();
        List<String> visibleColumn = selection.getColumns();
        discountDoller.setGroup("Discount $ Per Unit");
        discountPercent.setGroup(CommonUtils.VAR_DIS_RATE);
        unitVolume.setGroup(Constant.UNIT_VOLUME);
        totalDiscount.setGroup("Total Discount Amount");
        list1.add(discountDoller);
        list1.add(discountPercent);
        list1.add(unitVolume);
        list1.add(totalDiscount);
        list1 = setDefaultValue(list1, visibleColumn);
        final int TOTAL_DISCOUNT_NO = 2;
        final int discountPercentNo = NumericConstants.THREE;
        final int unitVolumeNo = NumericConstants.FOUR;
        final int discountDollerNo = NumericConstants.FIVE;
        int freq = 0;
        int yearInt = 0;

        for (int i = 0; i < list.size(); i++) {

            Object[] str = list.get(i);

            freq = 0;
            yearInt = 1;
            int actulOrProjection = str[NumericConstants.SIX] != null ? Integer.parseInt(str[NumericConstants.SIX].toString()) : -1;
            String quater = null;
            String year = null;
            quater = str[freq].toString();
            year = str[yearInt].toString();

            String header = isColumn(selection, quater, year, Constant.PROJECTIONS, frequency);
            String vis = isProjColumn(quater, year, frequency);
            if (header != null && actulOrProjection == 1) {
                Boolean isProj = CommonUtils.setProjectionZero(selection, vis);
                discountDoller.addStringProperties(header,
                        (str[discountDollerNo] == null)
                        ? CommonUtils.MONEY.format(Double.valueOf(Constant.DASH))
                        : isProj ? CommonUtils.MONEY.format(0.0)
                        : CommonUtils.MONEY.format(Double.valueOf(str[discountDollerNo].toString())));
                discountPercent.addStringProperties(header, (str[discountPercentNo] == null)
                        ? CommonUtils.PERCENT_FORMAT.format(Double.valueOf(Constant.DASH))
                        : isProj ? CommonUtils.PERCENT_FORMAT.format(0.0)
                        : CommonUtils.PERCENT_FORMAT.format(Double.valueOf(str[discountPercentNo].toString())));
                unitVolume.addStringProperties(header, (str[unitVolumeNo] == null) ? CommonUtils.UNITVOLUME.format(Double.valueOf(Constant.DASH))
                        : isProj ? CommonUtils.UNITVOLUME.format(0.0)
                        : CommonUtils.UNITVOLUME.format(Double.valueOf(str[unitVolumeNo].toString())));
                totalDiscount.addStringProperties(header, (str[TOTAL_DISCOUNT_NO] == null) ? CommonUtils.MONEY.format(Double.valueOf(Constant.DASH))
                        : isProj ? CommonUtils.MONEY.format(0.0)
                        : CommonUtils.MONEY.format(Double.valueOf(str[TOTAL_DISCOUNT_NO].toString())));
            }
            header = isColumn(selection, quater, year, Constant.ACTUALS, frequency);
            if (header != null && actulOrProjection == 0) {
                discountDoller.addStringProperties(header, (str[discountDollerNo] == null) ? CommonUtils.MONEY.format(Double.valueOf(Constant.DASH)) : CommonUtils.MONEY.format(Double.valueOf(str[discountDollerNo].toString())));
                discountPercent.addStringProperties(header, (str[discountPercentNo] == null) ? CommonUtils.PERCENT_FORMAT.format(Double.valueOf(Constant.DASH)) : CommonUtils.PERCENT_FORMAT.format(Double.valueOf(str[discountPercentNo].toString())));
                unitVolume.addStringProperties(header, (str[unitVolumeNo] == null) ? CommonUtils.UNITVOLUME.format(Double.valueOf(Constant.DASH)) : CommonUtils.UNITVOLUME.format(Double.valueOf(str[unitVolumeNo].toString())));
                totalDiscount.addStringProperties(header, (str[TOTAL_DISCOUNT_NO] == null) ? CommonUtils.MONEY.format(Double.valueOf(Constant.DASH)) : CommonUtils.MONEY.format(Double.valueOf(str[TOTAL_DISCOUNT_NO].toString())));
            }
        }

        return list1;
    }

    private String isColumn(ProjectionSelectionDTO selection, String quater, String year, String caption, String frequency) {
        String constant = StringUtils.EMPTY;
        if (frequency.equals(Constant.QUARTERLY)) {
            constant = Constant.Q_SMALL + quater + year + caption;
        } else if (frequency.equals(Constant.ANNUALLY)) {
            constant = year + caption;
        } else if (frequency.equals(Constant.MONTHLY)) {
            constant = HeaderUtils.getMonthForInt(Integer.parseInt(quater) - 1).toLowerCase() + year + caption;

        } else if (frequency.equals(Constant.SEMIANNUALLY)) {
            constant = Constant.S_SMALL + quater + year + caption;
        }
        if (selection.hasColumn(constant)) {
            return constant;
        }
        return null;
    }

    private String isProjColumn(String quater, String year, String frequency) {
        String constant = StringUtils.EMPTY;
        if (frequency.equals(Constant.QUARTERLY)) {
            constant = Constant.Q_SMALL + quater + year;
        } else if (frequency.equals(Constant.ANNUALLY)) {
            constant = year;
        } else if (frequency.equals(Constant.MONTHLY)) {
            constant = HeaderUtils.getMonthForInt(Integer.parseInt(quater) - 1).toLowerCase() + year;

        } else if (frequency.equals(Constant.SEMIANNUALLY)) {
            constant = Constant.S_SMALL + quater + year;
        }

        return constant;
    }


    private List getCustomizedListForPivot(List<Object[]> list, ProjectionSelectionDTO selection) {
        String frequency = selection.getFrequency();
        List result = new ArrayList();
        List<String> dtoList = getFrequencyList(selection);
        List<String> tempList = new ArrayList<>();
        tempList.addAll(dtoList);

        final int TOTAL_DISCOUNT_NO = 2;
        final int discountPercentNo = NumericConstants.THREE;
        final int unitVolumeNo = NumericConstants.FOUR;
        final int discountDollerNo = NumericConstants.FIVE;
        PPAProjectionResultsDTO dto;
        Map<String, PPAProjectionResultsDTO> dtoMap = new TreeMap<>();
        for (int i = 0; i < list.size(); i++) {

            Object[] str = list.get(i);
            String year;
            int actulOrProjection = str[NumericConstants.SIX] != null ? Integer.parseInt(str[NumericConstants.SIX].toString()) : -1;
            String freq = null;
            String constant = null;
            if (frequency.equals(Constant.ANNUALLY)) {
                constant = str[0].toString();
            }
            year = str[1].toString();
            freq = str[0].toString();
            if (frequency.equals(Constant.SEMIANNUALLY)) {
                constant = Constant.S + freq + Constant.SPACE + year;

            }
            if (frequency.equals(Constant.QUARTERLY)) {

                constant = Constant.Q + freq + Constant.SPACE + year;

            }
            if (frequency.equals(Constant.MONTHLY)) {
                constant = HeaderUtils.getMonthForInt(Integer.parseInt(freq) - Constant.ONE) + Constant.SPACE + year;

            }
            String key = year + (freq.length() == 1 ? (0 + freq) : freq);
            if (tempList.contains(constant)) {
                if (dtoMap.containsKey(key)) {
                    dto = dtoMap.get(key);
                } else {
                    dto = new PPAProjectionResultsDTO();
                    dto = setDefaultValueForPivot(dto);
                    dtoMap.put(key, dto);
                    dto.setGroup(constant);
                    dtoList.remove(constant);
                }
                if (actulOrProjection == 0) {
                    dto.setDiscountPerUnitActuals((str[discountDollerNo] == null) ? CommonUtils.MONEY.format(Double.valueOf(Constant.DASH)) : CommonUtils.MONEY.format(Double.valueOf(str[discountDollerNo].toString())));
                    dto.setDiscountPercentActuals((str[discountPercentNo] == null) ? CommonUtils.PERCENT_FORMAT.format(Double.valueOf(Constant.DASH)) : CommonUtils.PERCENT_FORMAT.format(Double.valueOf(str[discountPercentNo].toString())));
                    dto.setUnitVolumeActuals((str[unitVolumeNo] == null) ? CommonUtils.UNITVOLUME.format(Double.valueOf(Constant.DASH)) : CommonUtils.UNITVOLUME.format(Double.valueOf(str[unitVolumeNo].toString())));
                    dto.setTotalDiscountActuals((str[TOTAL_DISCOUNT_NO] == null) ? CommonUtils.MONEY.format(Double.valueOf(Constant.DASH)) : CommonUtils.MONEY.format(Double.valueOf(str[TOTAL_DISCOUNT_NO].toString())));
                } else if (actulOrProjection == 1) {
                    dto.setDiscountPerUnitProjections((str[discountDollerNo] == null) ? CommonUtils.MONEY.format(Double.valueOf(Constant.DASH)) : CommonUtils.MONEY.format(Double.valueOf(str[discountDollerNo].toString())));
                    dto.setDiscountPercentProjections((str[discountPercentNo] == null) ? CommonUtils.PERCENT_FORMAT.format(Double.valueOf(Constant.DASH)) : CommonUtils.PERCENT_FORMAT.format(Double.valueOf(str[discountPercentNo].toString())));
                    dto.setUnitVolumeProjections((str[unitVolumeNo] == null) ? CommonUtils.UNITVOLUME.format(Double.valueOf(Constant.DASH)) : CommonUtils.UNITVOLUME.format(Double.valueOf(str[unitVolumeNo].toString())));
                    dto.setTotalDiscountProjections((str[TOTAL_DISCOUNT_NO] == null) ? CommonUtils.MONEY.format(Double.valueOf(Constant.DASH)) : CommonUtils.MONEY.format(Double.valueOf(str[TOTAL_DISCOUNT_NO].toString())));
                }
            }

        }
        result.addAll(dtoMap.values());
        boolean isAdded = false;
        for (String obj : dtoList) {
            PPAProjectionResultsDTO dto1 = new PPAProjectionResultsDTO();
            dto1 = setDefaultValueForPivot(dto1);
            isAdded = true;
            dto1.setGroup(obj);
            result.add(dto1);
        }

        if (selection.getProjectionOrder().equals(Constant.ASCENDING)) {
            if (isAdded) {
                Collections.sort(result, new PPAProjectionResultsDTO());
            }
        } else {
            Collections.sort(result, new PPAProjectionResultsDTO());
            Collections.reverse(result);
        }
        return result;
    }

    public List getFrequencyList(ProjectionSelectionDTO selection) {
        CustomTableHeaderDTO groupListForPivot;
        selection.setPivotView(Constant.PERIOD);
        groupListForPivot = HeaderUtils.getCalculatedPPAProjectionResultsColumns(selection, new CustomTableHeaderDTO());
        selection.setPivotView(Constant.VARIABLE);
        return groupListForPivot.getDoubleHeaders();

    }

    private List<PPAProjectionResultsDTO> setDefaultValue(List<PPAProjectionResultsDTO> list1, List<String> visibleColumn) {
        for (int i = 0; i < visibleColumn.size(); i++) {
            for (int j = 0; j < list1.size(); j++) {
                list1.get(j).addStringProperties(visibleColumn.get(i), Constant.DASH);
            }
        }
        return list1;
    }

    private PPAProjectionResultsDTO setDefaultValueForPivot(PPAProjectionResultsDTO dto) {
        dto.setDiscountPerUnitActuals(CommonUtils.MONEY.format(Double.valueOf(Constant.DASH)));
        dto.setDiscountPerUnitProjections(CommonUtils.MONEY.format(Double.valueOf(Constant.DASH)));
        dto.setDiscountPercentActuals(CommonUtils.PERCENT_FORMAT.format(Double.valueOf(Constant.DASH)));
        dto.setDiscountPercentProjections(CommonUtils.PERCENT_FORMAT.format(Double.valueOf(Constant.DASH)));
        dto.setUnitVolumeActuals(CommonUtils.UNITVOLUME.format(Double.valueOf(Constant.DASH)));
        dto.setUnitVolumeProjections(CommonUtils.UNITVOLUME.format(Double.valueOf(Constant.DASH)));
        dto.setTotalDiscountActuals(CommonUtils.MONEY.format(Double.valueOf(Constant.DASH)));
        dto.setTotalDiscountProjections(CommonUtils.MONEY.format(Double.valueOf(Constant.DASH)));
        return dto;
    }

    public Date getStartForData(String frequency, Integer history, String historyCaption, SessionDTO session) {
        Date startDate = null;
        startDate = session.getHistoryAndStartDateValue(historyCaption);
        Calendar startCalendar = new GregorianCalendar();
        if (startDate == null) {

            startCalendar.set(Calendar.DATE, 1);
            if (frequency.equals(Constant.ANNUALLY)) {
                startCalendar.add(Calendar.YEAR, -history);
            } else if (frequency.equals(Constant.SEMIANNUALLY)) {
                startCalendar.add(Calendar.MONTH, -(history * NumericConstants.SIX));
            } else if (frequency.equals(Constant.QUARTERLY)) {
                startCalendar.add(Calendar.MONTH, -(history * NumericConstants.THREE));
            } else if (frequency.equals(Constant.MONTHLY)) {
                startCalendar.add(Calendar.MONTH, -history);
            }
            session.addHistoryAndStartDate(frequency, startCalendar.getTime());
        }

        return startCalendar.getTime();
    }

    public int configureLevelsCount(ProjectionSelectionDTO selection) {

        CommonLogic commonLogic = new CommonLogic();
        int count;
        if (selection.isIsCustomHierarchy()) {
            count = commonLogic.getCountForCustomView(selection);
        } else {
            count = commonLogic.getCount(selection);
        }
        LOGGER.debug("Level Count:" + count);
        return count;
    }

    public int getConfiguredPPAProjectionResultsCount(Object parentId, ProjectionSelectionDTO projSelDTO, boolean isLevelCount) {
        int count = 0;
        if (!projSelDTO.isIsFilter() || (parentId instanceof PPAProjectionResultsDTO)) {
            projSelDTO.setYear(Constant.ALL);
            if (projSelDTO.getActualsOrProjections().equals(Constant.BOTH)) {
                projSelDTO.setActualsOrProjections(Constant.ACTUALS_AND_PROJECTIONS);
            }

            if (parentId instanceof PPAProjectionResultsDTO) {
                projSelDTO.setIsProjectionTotal(false);
                PPAProjectionResultsDTO parentDto = (PPAProjectionResultsDTO) parentId;
                projSelDTO.setLevelNo(parentDto.getLevelNo());
                projSelDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
                projSelDTO.setLevelValue(parentDto.getGroup());
                projSelDTO.setHierarchyNo(parentDto.getHirarechyNo());
                if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equalsIgnoreCase(parentDto.getHirarechyIndicater())) {
                    projSelDTO.setCustomerHierarchyNo(projSelDTO.getHierarchyNo());
                    projSelDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
                } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equalsIgnoreCase(parentDto.getHirarechyIndicater())) {
                    projSelDTO.setProductHierarchyNo(projSelDTO.getHierarchyNo());
                    projSelDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                }
                projSelDTO.setHierarchyIndicator(parentDto.getHirarechyIndicater());
                projSelDTO.setGroup(parentDto.getGroup());
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
            count += getProjectionResultsCount(projSelDTO, isLevelCount);
        } else {
            projSelDTO.setIsProjectionTotal(false);
            projSelDTO.setLevelNo(projSelDTO.getFilterLevelNo());
            projSelDTO.setTreeLevelNo(projSelDTO.getFilterLevelNo());
            count += configureLevelsCount(projSelDTO);
        }
        return count;
    }

    public int getConfiguredPPAProjectionResultsTotalCount(ProjectionSelectionDTO selection) {
        int count = 0;
        selection.setIsProjectionTotal(true);
        if (!selection.isIsFilter()) {
            count = 1;
            if (selection.getPivotView().contains(Constant.PERIOD)) {
                count = count + NumericConstants.FOUR;
            } else {
                count = count + selection.getPeriodList().size();
            }

        }
        return count;
    }

    public int getProjectionResultsCount(ProjectionSelectionDTO projSelDTO, boolean isLevelCount) {
        int count = 0;
        projSelDTO.setGroupCount(false);
        if (!projSelDTO.getGroup().startsWith(Constant.ALL)
                && !projSelDTO.getGroup().contains(Constant.PPA)) {
            if (projSelDTO.getPivotView().contains(Constant.PERIOD)) {

                count = count + NumericConstants.FOUR;
                if (projSelDTO.isIsProjectionTotal()) {
                    count++;
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
                    && !projSelDTO.getGroup().startsWith(Constant.ALL)
                    && !projSelDTO.getGroup().contains(Constant.PPA)) {
                count = count + 1;
                projSelDTO.setLevelCount(1);
                projSelDTO.setGroupCount(true);
            } else {
                projSelDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo() + 1);
                int ct = configureLevelsCount(projSelDTO);
                count = count + ct;
                projSelDTO.setLevelCount(ct);
            }
        }
        return count;
    }

    public List<PPAProjectionResultsDTO> getConfiguredPPAProjectionResults1(Object parentId, int start, int offset, ProjectionSelectionDTO selection, SessionDTO session) {
        List<PPAProjectionResultsDTO> resultList;
        if (!selection.isIsFilter() || (parentId instanceof PPAProjectionResultsDTO)) {
            selection.setYear(Constant.ALL);

            if (selection.getActualsOrProjections().equals(Constant.BOTH)) {
                selection.setActualsOrProjections(Constant.ACTUALS_AND_PROJECTIONS);
            }

            if (parentId instanceof PPAProjectionResultsDTO) {
                selection.setIsProjectionTotal(false);
                PPAProjectionResultsDTO parentDto = (PPAProjectionResultsDTO) parentId;
                selection.setLevelNo(parentDto.getLevelNo());
                selection.setTreeLevelNo(parentDto.getTreeLevelNo());
                selection.setHierarchyNo(parentDto.getHirarechyNo());
                if (!parentDto.getGroup().startsWith(Constant.ALL)
                        && !parentDto.getGroup().contains(Constant.PPA) && !selection.isIsCustomHierarchy()) {
                    if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equalsIgnoreCase(parentDto.getHirarechyIndicater())) {
                        selection.setCustomerHierarchyNo(selection.getHierarchyNo());
                        selection.setProductHierarchyNo(parentDto.getProductHierarchyNo());
                    } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equalsIgnoreCase(parentDto.getHirarechyIndicater())) {
                        selection.setProductHierarchyNo(selection.getHierarchyNo());
                        selection.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                    }
                } else {
                    selection.setGroup(StringUtils.EMPTY);
                    selection.setProductHierarchyNo(parentDto.getProductHierarchyNo());
                    selection.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                }
                selection.setHierarchyIndicator(parentDto.getHirarechyIndicater());
                selection.setGroup(parentDto.getGroup());
            } else {
                selection.setIsProjectionTotal(true);
                if (selection.isIsCustomHierarchy()) {
                    selection.setLevelNo(0);
                    selection.setTreeLevelNo(0);
                } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(selection.getHierarchyIndicator())) {
                    selection.setLevelNo(selection.getCustomerLevelNo() - 1);
                    selection.setTreeLevelNo(selection.getCustomerLevelNo() - 1);
                } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(selection.getHierarchyIndicator())) {
                    selection.setLevelNo(selection.getProductLevelNo() - 1);
                    selection.setTreeLevelNo(selection.getProductLevelNo() - 1);
                }
                selection.setGroup(StringUtils.EMPTY);
                selection.setHierarchyNo(StringUtils.EMPTY);
                selection.setProductHierarchyNo(StringUtils.EMPTY);
                selection.setCustomerHierarchyNo(StringUtils.EMPTY);
            }
            resultList = getPPAProjectionResults1(start, offset, selection, session);
        } else {
            selection.setIsProjectionTotal(false);
            selection.setLevelNo(selection.getFilterLevelNo());
            selection.setTreeLevelNo(selection.getFilterLevelNo());
            resultList = configureLevels(start, offset, selection);
        }
        return resultList;
    }

    public List<PPAProjectionResultsDTO> getConfiguredPPAProjectionResultsTotal(int start, int offset, ProjectionSelectionDTO projSelDTO, SessionDTO session) {
        List<PPAProjectionResultsDTO> resultList = new ArrayList<>();
        projSelDTO.setIsProjectionTotal(true);
        if (!projSelDTO.isIsFilter()) {
            projSelDTO.setYear(Constant.ALL);

            if (projSelDTO.getActualsOrProjections().equals(Constant.BOTH)) {
                projSelDTO.setActualsOrProjections(Constant.ACTUALS_AND_PROJECTIONS);
            }

            resultList = getPPAProjectionResultsTotal(start, offset, projSelDTO, session);
        }
        return resultList;
    }

    /**
     *
     * @param start
     * @param offset
     * @param projSelDTO
     * @return
     */
    public List<PPAProjectionResultsDTO> configureLevels(int start, int offset, ProjectionSelectionDTO projSelDTO) {

        CommonLogic commonLogic = new CommonLogic();
        List<PPAProjectionResultsDTO> resultList = new ArrayList<>();
        Map<String, List> relationshipLevelDetailsMap = projSelDTO.getSessionDTO().getHierarchyLevelDetails();
        if (projSelDTO.isIsCustomHierarchy()) {

            String hierarchyIndicator = commonLogic.getHiearchyIndicatorFromCustomView(projSelDTO);
            List<String> hierarchyNoList = commonLogic.getHiearchyNoForCustomView(projSelDTO, start, offset);
            for (String hierarchyNo : hierarchyNoList) {
                resultList.add(configureDetailsInDTO(projSelDTO, hierarchyNo, hierarchyIndicator, projSelDTO.getTreeLevelNo(), relationshipLevelDetailsMap.get(hierarchyNo)));
            }

        } else {
            List<String> hierarchyNoList = commonLogic.getHiearchyNoAsList(projSelDTO, start, offset);
            for (String hierarchyNo : hierarchyNoList) {
                resultList.add(configureDetailsInDTO(projSelDTO, hierarchyNo, projSelDTO.getHierarchyIndicator(), Integer.parseInt(relationshipLevelDetailsMap.get(hierarchyNo).get(NumericConstants.TWO).toString()), relationshipLevelDetailsMap.get(hierarchyNo)));
            }
        }

        return resultList;
    }

    /**
     *
     * @param projSelDTO
     * @param hierarchyNo
     * @param hierarchyIndicator
     * @param levelNo
     * @param detailsList
     * @return
     */
    public PPAProjectionResultsDTO configureDetailsInDTO(ProjectionSelectionDTO projSelDTO, String hierarchyNo, String hierarchyIndicator,int levelNo, List detailsList) {
        PPAProjectionResultsDTO dto = new PPAProjectionResultsDTO();
        dto.setLevelNo(Integer.valueOf(detailsList.get(NumericConstants.TWO).toString()));
        dto.setTreeLevelNo(levelNo);
        dto.setGroup(detailsList.get(0).toString());
        dto.setHirarechyNo(hierarchyNo);
        dto.setHirarechyIndicater(hierarchyIndicator);
        if (dto.getHirarechyIndicater().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
            dto.setCustomerHierarchyNo(dto.getHirarechyNo());
            dto.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
        } else if (dto.getHirarechyIndicater().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            dto.setProductHierarchyNo(dto.getHirarechyNo());
            dto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
        }
        return dto;
    }

    public List<PPAProjectionResultsDTO> getPPAProjectionResultsTotal(int start, int offset, ProjectionSelectionDTO selection, SessionDTO session) {
        int neededRecord = offset;
        int started = start;
        int maxRecord = 0;
        if (selection.getPivotView().equals(Constant.PERIOD)) {
            maxRecord = NumericConstants.FOUR;
        } else {
            maxRecord = selection.getPeriodList().size() + 1;
        }
        List<PPAProjectionResultsDTO> resultList = new ArrayList<>();
        if (start < 1) {
            PPAProjectionResultsDTO dto = new PPAProjectionResultsDTO();
            dto.setGroup(Constant.PROJECTION_TOTAL);
            dto.setLevelNo(0);
            dto.setIsTotalColumn(Boolean.TRUE);
            resultList.add(dto);
            neededRecord--;
        } else {
            started--;
        }

        if (neededRecord > 0) {
            List<PPAProjectionResultsDTO> totalList;
            totalList = ppaProjecetionResults(selection.isIsProjectionTotal(), selection, session);
            setChartList(totalList);
            if (started < maxRecord) {
                for (int k = started; k < totalList.size() && neededRecord > 0; neededRecord--, k++) {
                    resultList.add(totalList.get(k));
                }
            }
        }
        return resultList;
    }

    public List<PPAProjectionResultsDTO> getPPAProjectionResults1(int start, int offset, ProjectionSelectionDTO selection, SessionDTO session) {
        int neededRecord;
        neededRecord = offset;
        int started = start;
        int maxRecord = 0;
        if (!selection.getGroup().startsWith(Constant.ALL)
                && !selection.getGroup().contains(Constant.PPA)) {
            if (selection.getPivotView().equals(Constant.PERIOD)) {
                maxRecord = NumericConstants.FOUR;
            } else {
                maxRecord = selection.getPeriodList().size();
            }
        }
        if (selection.isIsProjectionTotal()) {
            maxRecord++;
        }
        List<PPAProjectionResultsDTO> projDTOList = new ArrayList<>();
        if ((!selection.isIsProjectionTotal()) && ((neededRecord > 0) && (!selection.getGroup().startsWith(Constant.ALL)
                && !selection.getGroup().contains(Constant.PPA)))) {
            List<PPAProjectionResultsDTO> resultList = new ArrayList<>();
            if (selection.getPivotView().contains(Constant.PERIOD)) {
                try {
                            resultList = ppaProjecetionResults(selection.isIsProjectionTotal(),selection, session);
                } catch (Exception ex) {
                    LOGGER.error(ex.getMessage());
                }
            } else {
                resultList = getProjectionPivotTotal(selection, session);
            }
            setChartList(resultList);
            if (started < maxRecord) {
                for (int k = started; k < resultList.size() && neededRecord > 0; neededRecord--, k++) {
                    projDTOList.add(resultList.get(k));
                }
            }
        }

        if (neededRecord > 0 && !selection.isIsFilter()) {
            if ((selection.getTreeLevelNo() + 1) == selection.getTpLevel()
                    && ((selection.isIsCustomHierarchy()) || (!selection.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)))
                    && !selection.getGroup().startsWith(Constant.ALL)
                    && !selection.getGroup().contains(Constant.PPA)) {
                PPAProjectionResultsDTO dto = new PPAProjectionResultsDTO();
                if (selection.getLevelNo() != 0) {
                    dto.setLevelNo(selection.getLevelNo());
                } else {
                    dto.setLevelNo(1);
                }
                dto.setTreeLevelNo(selection.getTreeLevelNo());
                dto.setGroup(selection.getGroupFilter());
                dto.setHirarechyIndicater(selection.getHierarchyIndicator());
                dto.setHirarechyNo(selection.getHierarchyNo());
                if (dto.getHirarechyIndicater().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                    dto.setCustomerHierarchyNo(dto.getHirarechyNo());
                    dto.setProductHierarchyNo(selection.getProductHierarchyNo());
                } else if (dto.getHirarechyIndicater().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                    dto.setProductHierarchyNo(dto.getHirarechyNo());
                    dto.setCustomerHierarchyNo(selection.getCustomerHierarchyNo());
                }
                projDTOList.add(dto);
                neededRecord--;
            } else {
                int resultStart;
                resultStart = (start <= maxRecord) ? 0 : start - maxRecord;
                selection.setTreeLevelNo(selection.getTreeLevelNo() + 1);
                int maxLevelNo = selection.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY) ? session.getMaximumCustomerLevel() : session.getMaximumProductLevel();
                if (selection.getTreeLevelNo() <= maxLevelNo) {
                    List<PPAProjectionResultsDTO> nextLevelValueList = configureLevels(resultStart, neededRecord, selection);
                    projDTOList.addAll(nextLevelValueList);
                }
            }
        }

        return projDTOList;
    }

    public List<PPAProjectionResultsDTO> getProjectionPivotTotal(ProjectionSelectionDTO selection, SessionDTO session) {
        List<PPAProjectionResultsDTO> projDTOList = new ArrayList<>();
        try {
            projDTOList = ppaProjecetionResults(selection.isIsProjectionTotal(), selection, session);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return projDTOList;
    }

    /**
     *
     * @param filter
     * @param dto
     * @return
     */
    public int getPPADetailsDDLBCount(String filter, final String ddlbType,
            final int projectionId, PPADetailsDTO ppaDetailsDTO) {
        String query = StringUtils.EMPTY;
        String value;
        switch (ddlbType) {
            case Constant.CONTRACT:
                query = SQlUtil.getQuery(getClass(),"contract-ddlb");
                break;
            case Constant.CUSTOMER1_SMALL:
                query = SQlUtil.getQuery(getClass(),"customer-ddlb");
                break;
            case Constant.BRAND:
                query = SQlUtil.getQuery(getClass(),"brand-ddlb");
                break;
            default:
                break;

        }
        query = query.replace(Constant.PROJID_AT_SMALL, String.valueOf(projectionId));
        query = query.replace(Constant.CONTRACTSID_AT_SMALL, String.valueOf(ppaDetailsDTO.getSelectedContract()));
        query = query.replace(Constant.COMPSID_AT_SMALL, String.valueOf(ppaDetailsDTO.getSelectedCustomer()));
        query = query.replace(Constant.BRANDSID_AT_SMALL, String.valueOf(ppaDetailsDTO.getSelectedBrand()));

        if (filter != null || !(Constant.NULL.equalsIgnoreCase(String.valueOf(filter)))) {
            if (!filter.isEmpty()) {
                switch (ddlbType) {
                    case Constant.CONTRACT:
                        value = Constant.PERCENT + filter + Constant.PERCENT;
                        query = query.replace(Constant.FILTERR, "'" + value + "'");
                        break;
                    case Constant.CUSTOMER1_SMALL:
                        value = Constant.PERCENT + filter + Constant.PERCENT;
                        query = query.replace(Constant.FILTERR, "'" + value + "'");
                        break;
                    case Constant.BRAND:
                        value = Constant.PERCENT + filter + Constant.PERCENT;
                        query = query.replace(Constant.FILTERR, "'" + value + "'");
                        break;
                    default:
                        break;

                }
            }
        } else {
            switch (ddlbType) {
                case Constant.CONTRACT:
                    query = query.replace("AND CM.CONTRACT_NO LIKE @FILTER", StringUtils.EMPTY);
                    break;
                case Constant.CUSTOMER1_SMALL:
                    query = query.replace("AND COM.COMPANY_NO LIKE @FILTER", StringUtils.EMPTY);
                    break;
                case Constant.BRAND:
                    query = query.replace("AND BM.BRAND_NAME LIKE @FILTER", StringUtils.EMPTY);
                    break;
                default:
                    break;
            }
        }
        List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return list.isEmpty() ? 0 : list.size();
    }

    /**
     *
     * @param startIndex
     * @param filter
     * @param dto
     * @return
     */
    public List<HelperDTO> getPPADetailsDDLBResult(int startIndex, int end, String filter, HelperDTO dto,
            final String ddlbType, final int projectionId, PPADetailsDTO ppaDetailsDTO) {
        String query = StringUtils.EMPTY;
        String searchFilter;
        final List<HelperDTO> list = new ArrayList<>();
        switch (ddlbType) {

            case Constant.CONTRACT:
                query = SQlUtil.getQuery(getClass(),"contract-ddlb");
                break;
            case Constant.CUSTOMER1_SMALL:
                query = SQlUtil.getQuery(getClass(),"customer-ddlb");
                break;
            case Constant.BRAND:
                query = SQlUtil.getQuery(getClass(),"brand-ddlb");
                break;
            default:
                break;

        }
        query = query + "OFFSET " + startIndex + Constant.ROWS_FETCH_NEXT_SPACE + end + Constant.ROWS_ONLY_SPACE;
        query = query.replace(Constant.PROJID_AT_SMALL, String.valueOf(projectionId));
        query = query.replace(Constant.CONTRACTSID_AT_SMALL, String.valueOf(ppaDetailsDTO.getSelectedContract()));
        query = query.replace(Constant.COMPSID_AT_SMALL, String.valueOf(ppaDetailsDTO.getSelectedCustomer()));
        query = query.replace(Constant.BRANDSID_AT_SMALL, String.valueOf(ppaDetailsDTO.getSelectedBrand()));

        if (filter != null || !(Constant.NULL.equalsIgnoreCase(String.valueOf(filter)))) {
            if (!filter.isEmpty()) {
                switch (ddlbType) {
                    case Constant.CONTRACT:
                        searchFilter = Constant.PERCENT + filter + Constant.PERCENT;
                        query = query.replace(Constant.FILTERR, "'" + searchFilter + "'");
                        break;
                    case Constant.CUSTOMER1_SMALL:
                        searchFilter = Constant.PERCENT + filter + Constant.PERCENT;
                        query = query.replace(Constant.FILTERR, "'" + searchFilter + "'");
                        break;
                    case Constant.BRAND:
                        searchFilter = Constant.PERCENT + filter + Constant.PERCENT;
                        query = query.replace(Constant.FILTERR, "'" + searchFilter + "'");
                        break;
                    default:
                        break;

                }
            }
        } else {
            switch (ddlbType) {
                case Constant.CONTRACT:
                    query = query.replace("AND CM.CONTRACT_NO LIKE @FILTER", StringUtils.EMPTY);
                    break;
                case Constant.CUSTOMER1_SMALL:
                    query = query.replace("AND COM.COMPANY_NO LIKE @FILTER", StringUtils.EMPTY);
                    break;
                case Constant.BRAND:
                    query = query.replace("AND BM.BRAND_NAME LIKE @FILTER", StringUtils.EMPTY);
                    break;
                default:
                    break;
            }
        }
        List<Object[]> returnList = (List<Object[]>) HelperTableLocalServiceUtil.executeSelectQuery(query);
        HelperDTO helperTable;
        if (startIndex == ConstantsUtils.ZERO_INT) {
            helperTable = new HelperDTO();
            helperTable.setId(0);
            helperTable.setDescription(ConstantsUtils.SELECT_ONE);
            list.add(helperTable);
            if (dto != null && dto.getId() != 0 && filter == null) {
                list.add(dto);
            }
        }
        for (final Iterator<Object[]> iterator = returnList.iterator(); iterator.hasNext();) {
            final Object[] value = iterator.next();
            helperTable = new HelperDTO();
            helperTable.setId(value[0] != null ? Integer.parseInt(value[0].toString()) : 0);
            helperTable.setDescription(value[1] != null ? value[1].toString() : StringUtils.EMPTY);
            if (!StringUtils.EMPTY.equals(helperTable.getDescription())) {
                list.add(helperTable);
            }
        }
        return list;
    }

    public int getPPAItemCount(PPADetailsDTO ppaDetailsDTO, final String filterText, String ddlbtype) {
        String query = SQlUtil.getQuery(getClass(),Constant.ITEMNO_DDLB);
        String searchFilter;
        if (ppaDetailsDTO.getPpaHelperDTO().getItemMasterSysId() != 0) {
            query = query.replace(Constant.ITEMSID_AT, StringUtils.EMPTY + ppaDetailsDTO.getPpaHelperDTO().getItemMasterSysId());
        } else {
            query = query.replace("AND IM.ITEM_MASTER_SID NOT IN (@itemsid)", StringUtils.EMPTY);
        }
        if (filterText != null || !(Constant.NULL.equalsIgnoreCase(String.valueOf(filterText)))) {
            if (!filterText.isEmpty()) {
                switch (ddlbtype) {
                    case Constant.ITEM_NO:
                        searchFilter = Constant.PERCENT + filterText + Constant.PERCENT;
                        query = query.replace("AND IM.ITEM_NAME LIKE @FILTER", StringUtils.EMPTY);
                        query = query.replace(Constant.FILTERR, "'" + searchFilter + "'");
                        break;
                    case Constant.ITEM_NAME_SMALL_PROPERY:
                        searchFilter = Constant.PERCENT + filterText + Constant.PERCENT;
                        query = query.replace("AND IM.ITEM_NO LIKE @FILTER", StringUtils.EMPTY);
                        query = query.replace(Constant.FILTERR, "'" + searchFilter + "'");
                        break;
                    default:
                        break;

                }
            }
        } else {
            switch (ddlbtype) {
                case Constant.ITEM_NO:
                    query = query.replace(Constant.AND_IMITEM_NO_LIKE_FILTER_AND_IMITEM_NAME, StringUtils.EMPTY);
                    break;
                case Constant.ITEM_NAME_SMALL_PROPERY:
                    query = query.replace(Constant.AND_IMITEM_NO_LIKE_FILTER_AND_IMITEM_NAME, StringUtils.EMPTY);
                    break;
                default:
                    break;

            }

        }
        query = query.replace(Constant.PROJID_AT_SMALL, String.valueOf(ppaDetailsDTO.getProjectionID()));
        query = query.replace(Constant.CONTRACTSID_AT_SMALL, String.valueOf(ppaDetailsDTO.getSelectedContract()));
        query = query.replace(Constant.COMPSID_AT_SMALL, String.valueOf(ppaDetailsDTO.getSelectedCustomer()));
        query = query.replace(Constant.BRANDSID_AT_SMALL, String.valueOf(ppaDetailsDTO.getSelectedBrand()));

        List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return list.isEmpty() ? 0 : list.size();
    }

    public List<PPAHelperDTO> getPPAItemDDLBResult(int startIndex, int end, PPADetailsDTO ppaDetailsDTO, String ddlbtype, final String filterText)  {
        LOGGER.debug("Inside Item Load" + startIndex + "END INDEX" + end + "ppaDetailsDTO" + ppaDetailsDTO.getPpaHelperDTO().getItemMasterSysId());
        String query = StringUtils.EMPTY;
        String searchFilter;
        final List<PPAHelperDTO> list = new ArrayList<>();
        switch (ddlbtype) {
            case Constant.ITEM_NO:
                query = SQlUtil.getQuery(getClass(),Constant.ITEMNO_DDLB);
                break;
            case Constant.ITEM_NAME_SMALL_PROPERY:
                query = SQlUtil.getQuery(getClass(),Constant.ITEMNO_DDLB);
                break;
            default:
                break;
        }

        if (ppaDetailsDTO.getPpaHelperDTO().getItemMasterSysId() != 0) {
            query = query.replace(Constant.ITEMSID_AT, String.valueOf(ppaDetailsDTO.getPpaHelperDTO().getItemMasterSysId()));
        } else {
            query = query.replace("AND IM.ITEM_MASTER_SID NOT IN (@itemsid)", StringUtils.EMPTY);
        }
        if (filterText != null || !(Constant.NULL.equalsIgnoreCase(String.valueOf(filterText)))) {
            if (!filterText.isEmpty()) {
                switch (ddlbtype) {
                    case Constant.ITEM_NO:
                        searchFilter = Constant.PERCENT + filterText + Constant.PERCENT;
                        query = query.replace("AND IM.ITEM_NAME LIKE @FILTER", StringUtils.EMPTY);
                        query = query.replace(Constant.FILTERR, "'" + searchFilter + "'");
                        break;
                    case Constant.ITEM_NAME_SMALL_PROPERY:
                        searchFilter = Constant.PERCENT + filterText + Constant.PERCENT;
                        query = query.replace("AND IM.ITEM_NO LIKE @FILTER", StringUtils.EMPTY);
                        query = query.replace(Constant.FILTERR, "'" + searchFilter + "'");
                        break;
                    default:
                        break;
                }
            }
        } else {
            switch (ddlbtype) {
                case Constant.ITEM_NO:
                    query = query.replace(Constant.AND_IMITEM_NO_LIKE_FILTER_AND_IMITEM_NAME, StringUtils.EMPTY);
                    break;
                case Constant.ITEM_NAME_SMALL_PROPERY:
                    query = query.replace(Constant.AND_IMITEM_NO_LIKE_FILTER_AND_IMITEM_NAME, StringUtils.EMPTY);
                    break;
                default:
                    break;

            }

        }

        query = query + "OFFSET " + startIndex + Constant.ROWS_FETCH_NEXT_SPACE + end + Constant.ROWS_ONLY_SPACE;
        query = query.replace(Constant.PROJID_AT_SMALL, String.valueOf(ppaDetailsDTO.getProjectionID()));
        query = query.replace(Constant.CONTRACTSID_AT_SMALL, String.valueOf(ppaDetailsDTO.getSelectedContract()));
        query = query.replace(Constant.COMPSID_AT_SMALL, String.valueOf(ppaDetailsDTO.getSelectedCustomer()));
        query = query.replace(Constant.BRANDSID_AT_SMALL, String.valueOf(ppaDetailsDTO.getSelectedBrand()));
        List<Object[]> returnList = (List<Object[]>) HelperTableLocalServiceUtil.executeSelectQuery(query);

        PPAHelperDTO helperTable;
        if (startIndex == ConstantsUtils.ZERO_INT) {
            helperTable = new PPAHelperDTO(0, Constants.SELECT_ONE, Constants.SELECT_ONE);
            list.add(helperTable);
        }

        for (final Iterator<Object[]> iterator = returnList.iterator(); iterator.hasNext();) {
            final Object[] value = iterator.next();
            helperTable = new PPAHelperDTO();
            helperTable.setItemMasterSysId(value[0] != null ? Integer.parseInt(value[0].toString()) : 0);
            helperTable.setItemNo(value[1] != null ? value[1].toString() : StringUtils.EMPTY);
            helperTable.setItemName(value[NumericConstants.TWO] != null ? value[NumericConstants.TWO].toString() : StringUtils.EMPTY);
            if (helperTable.getItemMasterSysId() != 0) {
                list.add(helperTable);
            }
        }

        return list;

    }

    /**
     *
     * @param ppaDetailsDTO
     * @param isCount
     * @param start
     * @param offset
     * @return
     */
    public Object loadPPADetails(PPADetailsDTO ppaDetailsDTO, SessionDTO sessionDTO, boolean isCount, int start, int offset, List<SortByColumn> sortByColumns) {
        String query = SQlUtil.getQuery(isCount ? "ppa-details-count" : "ppa-details-generate");
        String asc = StringUtils.EMPTY;
        query = query.replace(Constant.CONTRACTSID_AT_SMALL, String.valueOf(ppaDetailsDTO.getSelectedContract()));
        query = query.replace(Constant.COMPSID_AT_SMALL, String.valueOf(ppaDetailsDTO.getSelectedCustomer()));
        query = query.replace(Constant.ITEMSID_AT, String.valueOf(ppaDetailsDTO.getPpaHelperDTO().getItemMasterSysId()));
        query = query.replace("@from", String.valueOf(ppaDetailsDTO.getStartPeriod()));
        query = query.replace("@to", String.valueOf(ppaDetailsDTO.getEndPeriod()));
        query = query.replace("@start", String.valueOf(start));
        query = query.replace("@end", String.valueOf(offset));
        if (!(sortByColumns == null || sortByColumns.isEmpty())) {
            for (SortByColumn sortByColumn : sortByColumns) {
                if (sortByColumn.getName().equals("period")) {
                    asc = sortByColumn.getType().equals(SortByColumn.Type.ASC) ? "ASC" : "DESC";
                } else {
                    asc = StringUtils.EMPTY;
                }
            }
        } else {
            asc = StringUtils.EMPTY;
        }
        query = query.replace("@order", asc);
        List resultList = (List) HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, sessionDTO.getCurrentTableNames()));
        if (isCount) {
            int count = (Integer) resultList.get(0);
            return count;
        } else {
           loadPeriodTable(ppaDetailsDTO);
           return convertPPADetailsList(resultList,sessionDTO);
        }
    }

    /**
     *
     * @param resultList
     * @return
     */
    private Object convertPPADetailsList(final List<Object[]> list, SessionDTO sessionDTO) {
        List<PPADetailsDTO> resultList = new ArrayList<>();
        SimpleDateFormat myFormat = new SimpleDateFormat(Constant.DATE_FORMAT);
        if (list != null && !list.isEmpty()) {
            load_Wac_Tables(String.valueOf(list.get(0)[NumericConstants.THIRTY_ONE]), sessionDTO);
        }
        for (Object[] object : list) {
            try {
                PPADetailsDTO detailsDTO = new PPADetailsDTO();
                detailsDTO.setPeriod((object[0] != null ? String.valueOf(object[0]) : StringUtils.EMPTY) + " " + (object[1] != null ? CommonUtils.getMonthName(Integer.parseInt(String.valueOf(object[1]))) : StringUtils.EMPTY));
                detailsDTO.setPriceProtectionPriceType(object[NumericConstants.TWO] != null ? String.valueOf(object[NumericConstants.TWO]) : StringUtils.EMPTY);
                detailsDTO.setMap(object[NumericConstants.FIVE] != null ? getFormatValue(TWO_DECIMAL, String.valueOf(object[NumericConstants.FIVE]), CURRENCY) : StringUtils.EMPTY);
                detailsDTO.setTotalDeductions(object[NumericConstants.SIX] != null ? getFormatValue(TWO_DECIMAL, String.valueOf(object[NumericConstants.SIX]), CURRENCY) : StringUtils.EMPTY);
                detailsDTO.setUnits(object[NumericConstants.SEVEN] != null ? getFormatValue(ZERO_DECIMAL, String.valueOf(object[NumericConstants.SEVEN]), StringUtils.EMPTY) : StringUtils.EMPTY);
                detailsDTO.setDeductionPerUnit(object[NumericConstants.EIGHT] != null ? getFormatValue(TWO_DECIMAL, String.valueOf(object[NumericConstants.EIGHT]), CURRENCY) : StringUtils.EMPTY);
                detailsDTO.setNetPrice(object[NumericConstants.NINE] != null ? getFormatValue(TWO_DECIMAL, String.valueOf(object[NumericConstants.NINE]), CURRENCY) : StringUtils.EMPTY);
                detailsDTO.setNetMAP(object[NumericConstants.TEN] != null ? getFormatValue(TWO_DECIMAL, String.valueOf(object[NumericConstants.TEN]), CURRENCY) : StringUtils.EMPTY);
                detailsDTO.setPriceProtectionAmountPerUnit(object[NumericConstants.ELEVEN] != null ? getFormatValue(TWO_DECIMAL, String.valueOf(object[NumericConstants.ELEVEN]), CURRENCY) : StringUtils.EMPTY);
                detailsDTO.setPriceProtectionPercent(object[NumericConstants.TWELVE] != null ? getFormatValue(TWO_DECIMAL, String.valueOf(object[NumericConstants.TWELVE]), PERCENTAGE) : StringUtils.EMPTY);
                detailsDTO.setTotalPriceProtectionDeduction(object[NumericConstants.THIRTEEN] != null ? getFormatValue(TWO_DECIMAL, String.valueOf(object[NumericConstants.THIRTEEN]), PERCENTAGE) : StringUtils.EMPTY);
                detailsDTO.setNep(object[NumericConstants.FOURTEEN] != null ? getFormatValue(TWO_DECIMAL, String.valueOf(object[NumericConstants.FOURTEEN]), CURRENCY) : StringUtils.EMPTY);
                detailsDTO.setNepFormula(object[NumericConstants.FIFTEEN] != null ? String.valueOf(object[NumericConstants.FIFTEEN]) : StringUtils.EMPTY);
                detailsDTO.setPriceToleranceType(object[NumericConstants.SIXTEEN] != null ? String.valueOf(object[NumericConstants.SIXTEEN]) : StringUtils.EMPTY);
                detailsDTO.setPriceTolerance(object[NumericConstants.SEVENTEEN] != null ? getFormatValue(ZERO_DECIMAL, String.valueOf(object[NumericConstants.SEVENTEEN]), StringUtils.EMPTY) : StringUtils.EMPTY);
                detailsDTO.setPriceToleranceInterval(object[NumericConstants.EIGHTEEN] != null ? getFormatValue(ZERO_DECIMAL, String.valueOf(object[NumericConstants.EIGHTEEN]), StringUtils.EMPTY) : StringUtils.EMPTY);
                detailsDTO.setPriceToleranceFrequency(object[NumericConstants.NINETEEN] != null ? String.valueOf(object[NumericConstants.NINETEEN]) : StringUtils.EMPTY);
                detailsDTO.setMaxIncrementalChange(object[NumericConstants.TWENTY] != null ? String.valueOf(object[NumericConstants.TWENTY]) : StringUtils.EMPTY);
                detailsDTO.setResetEligible(object[NumericConstants.TWENTY_ONE] != null ? String.valueOf(object[NumericConstants.TWENTY_ONE]) : StringUtils.EMPTY);
                detailsDTO.setResetType(object[NumericConstants.TWENTY_TWO] != null ? String.valueOf(object[NumericConstants.TWENTY_TWO]) : StringUtils.EMPTY);
                if (object[NumericConstants.TWENTY_THREE] != null) {
                    SimpleDateFormat fromUser = new SimpleDateFormat(DATE_FORMAT.getConstant());
                    String formateddate = fromUser.format(myFormat.parse(String.valueOf(object[NumericConstants.TWENTY_THREE])));
                    detailsDTO.setResetDate(formateddate);
                } else {
                    detailsDTO.setResetDate(StringUtils.EMPTY);
                }

                detailsDTO.setResetInterval(object[NumericConstants.TWENTY_FOUR] != null ? String.valueOf(object[NumericConstants.TWENTY_FOUR]) : StringUtils.EMPTY);
                detailsDTO.setResetFrequency(object[NumericConstants.TWENTY_FIVE] != null ? String.valueOf(object[NumericConstants.TWENTY_FIVE]) : StringUtils.EMPTY);
                detailsDTO.setNetPriceType(object[NumericConstants.TWENTY_SIX] != null ? String.valueOf(object[NumericConstants.TWENTY_SIX]) : StringUtils.EMPTY);
                detailsDTO.setNetPriceTypeFormula(object[NumericConstants.TWENTY_SEVEN] != null ? String.valueOf(object[NumericConstants.TWENTY_SEVEN]) : StringUtils.EMPTY);
                detailsDTO.setRebateScheduleName(object[NumericConstants.TWENTY_EIGHT] != null ? String.valueOf(object[NumericConstants.TWENTY_EIGHT]) : StringUtils.EMPTY);

                String frquency = getFrequencyForWacReset(String.valueOf(object[NumericConstants.THIRTY_TWO]));
                String frquencyValue = getFrequencyValue(frquency, object);

                String[] wacPriceArray = calcWacPriceChange(String.valueOf(object[NumericConstants.THIRTY_TWO]), String.valueOf(object[NumericConstants.TWENTY_NINE]),
                        String.valueOf(object[NumericConstants.ZERO]), frquencyValue);

                detailsDTO.setPrice(getFormatValue(TWO_DECIMAL, wacPriceArray[NumericConstants.ZERO], StringUtils.EMPTY));
                detailsDTO.setPriceChange(getFormatValue(TWO_DECIMAL, wacPriceArray[NumericConstants.ONE], PERCENTAGE));
                resultList.add(detailsDTO);
            } catch (ParseException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        wacTableList=null;
        wacPriceTableList=null;

        return resultList;
    }

    public String getFormatValue(DecimalFormat FORMAT, String value, String appendChar) {
        if (CURRENCY.equals(appendChar)) {
            value = appendChar.concat(FORMAT.format(Double.valueOf(value)));
        } else {
            value = FORMAT.format(Double.valueOf(value)).concat(appendChar);
        }
        return value;
    }

    /**
     * Return a excel container
     *
     * @param selection
     * @param container
     * @return
     * @throws Exception
     */
    public ExtTreeContainer<PPAProjectionResultsDTO> getLoadedExcelContainer(ProjectionSelectionDTO selection, ExtTreeContainer<PPAProjectionResultsDTO> container) throws IllegalAccessException, InvocationTargetException  {
        List<PPAProjectionResultsDTO> totalList = ppaProjecetionResults(selection.isIsProjectionTotal(), selection, selection.getSessionDTO());
        ExcelUtils.setExcelData(getExcelProcedureList(selection), totalList, selection, container);
        return container;
    }

    /**
     * Get Excel Procedure For PPA Results
     *
     * @param selection
     * @return
     * @throws Exception
     */
    private List getExcelProcedureList(final ProjectionSelectionDTO selection)  {
        List<Object[]> gtsList =null;
        Object[] orderedArgs = {selection.getProjectionId(), selection.getUserId(), selection.getSessionDTO().getSessionId(), selection.getFrequency(), null, null, null, 1, null, null, selection.getCustomId()};
        gtsList = CommonLogic.callProcedure("PRC_NM_PPA_EXCEL_EXPORT", orderedArgs);
        return gtsList;
    }
    /*
     * PPA Generate procedures 
     * @PROJECTION_MASTER_SID INT
     ,@USER_ID VARCHAR(100)
     ,@SESSION_ID VARCHAR(100)
     ,@NETTING BIT
     ,@VIOLATION_DATE BIT
     */

    public void callPPAGenerateProcedures(final ProjectionSelectionDTO selection) {
        //Calling PRC_NM_PPA_PROJ_INIT only if it is not called in Tab Change
        if (selection.getSessionDTO().getFutureValue(SalesUtils.PRC_NM_PPA_PROJ_INIT) == null) {
            Future ppaInit = service.submit(CommonUtil.getInstance().createRunnableForPPAInitProcedure(SalesUtils.PRC_NM_PPA_PROJ_INIT, selection.getSessionDTO()));
            selection.getSessionDTO().addFutureMap(SalesUtils.PRC_NM_PPA_PROJ_INIT, new Future[]{ppaInit});
            selection.getSessionDTO().setIsSalesCalculated(false);
        }
        CommonUtil.getInstance().waitsForOtherThreadsToComplete(selection.getSessionDTO().getFutureValue(SalesUtils.PRC_NM_PPA_PROJ_INIT));
     
        Object[] orderedArgs = {selection.getProjectionId(), selection.getUserId(), selection.getSessionDTO().getSessionId()};
        Future future = service.submit(createRunnable(SalesUtils.PRC_NM_PPA_PROJECTION, orderedArgs, selection));
        selection.getSessionDTO().addFutureMap(Constant.PRC_PPA_GENERATE_CALL, new Future[]{future});
    }

    Runnable createRunnable(final String procedureName, final Object[] orderedArgs, final ProjectionSelectionDTO selection) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName(procedureName);
                //It will wait until the ppa insert procedure complete 
                CommonUtil.getInstance().waitsForOtherThreadsToComplete(selection.getSessionDTO().getFutureValue(Constant.PPA_PROCEDURE_CALL));
                CommonLogic.callProcedureforUpdate(procedureName, orderedArgs);
            }
        };
        return runnable;
    }
    @SuppressWarnings({"unchecked"})
    void load_Wac_Tables(String ccpId,  SessionDTO sessionDTO) {
        String query;
       
        query = String.format("select * from ST_NM_PPA_PROJECTION where ccp_details_sid= %s ", ccpId);
        query = QueryUtil.replaceTableNames(query, sessionDTO.getCurrentTableNames());
        query = query.replace("ST_NM_PPA_PROJECTION", "ST_PPA_RESET_PERIODS");
        wacTableList = HelperTableLocalServiceUtil.executeSelectQuery(query);

        query = String.format("select * from ST_NM_PPA_PROJECTION where ccp_details_sid= %s  ", ccpId);
        query = QueryUtil.replaceTableNames(query, sessionDTO.getCurrentTableNames());
        query = query.replace("ST_NM_PPA_PROJECTION", "ST_PPA_WAC_PRICE");
        wacPriceTableList = HelperTableLocalServiceUtil.executeSelectQuery(query);
    }

    String getFrequencyForWacReset(String rsId) {

        String frquency = StringUtils.EMPTY;
        try {
            for (int i = NumericConstants.ZERO; i < wacTableList.size(); i++) {
                Object object[] = wacTableList.get(i);
                if ((String.valueOf(object[NumericConstants.ONE]).equals(rsId))) {
                    frquency = String.valueOf(object[NumericConstants.THREE]);
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }

        return frquency.isEmpty() ? Q : String.valueOf(frquency);
    }

    String getFrequencyValue(String frquency, Object[] resultRow) {
        if (frquency.equalsIgnoreCase(Q)) {
            currentfrquencyForWacReset = NumericConstants.THREE;
            cureentWacFrquencyIndex = NumericConstants.TWO;
            return String.valueOf(resultRow[NumericConstants.THIRTY]);
        } else if (frquency.equalsIgnoreCase(A)) {
            currentfrquencyForWacReset = NumericConstants.TWELVE;
            cureentWacFrquencyIndex = NumericConstants.ONE;
            return String.valueOf(resultRow[NumericConstants.ZERO]);
        } else if (frquency.equalsIgnoreCase(M)) {
            currentfrquencyForWacReset = NumericConstants.ONE;
            cureentWacFrquencyIndex = NumericConstants.THREE;
            return String.valueOf(resultRow[NumericConstants.ONE]);
        } else {
            currentfrquencyForWacReset = NumericConstants.SIX;
            cureentWacFrquencyIndex = NumericConstants.FOUR;
            return String.valueOf(resultRow[NumericConstants.THIRTY_THREE]);
        }
    }

    @SuppressWarnings("unchecked")
    void loadPeriodTable(PPADetailsDTO ppaDetailsDTO) {
        String query;
        if (periodTableList == null) {
            query = "select PERIOD_SID,YEAR,QUARTER,MONTH,SEMI_ANNUAL from PERIOD WHERE PERIOD_SID BETWEEN %s AND %s ";
            query = String.format(query, ppaDetailsDTO.getStartPeriod(), ppaDetailsDTO.getEndPeriod());
            periodTableList = HelperTableLocalServiceUtil.executeSelectQuery(query);
        }
    }

    String[] calcWacPriceChange(String rsId, String periodSid, String year, String frequencyValue) {
        String[] wac_price = new String[]{ConstantsUtils.ZERO, ConstantsUtils.ZERO};

        try {
            String periodIds[] = fetchPeriod_Sids(year, frequencyValue);
            List<String> periodList = Arrays.asList(periodIds);

            int i;
            // finding wac price 
            wac_price[NumericConstants.ZERO] = searchWacPrice(rsId, periodSid);

            // checking if wac price change to be reset ie (returns zero)
            for (i = NumericConstants.ZERO; i < wacTableList.size(); i++) {
                Object object[] = wacTableList.get(i);
                if ((String.valueOf(object[NumericConstants.ONE]).equals(rsId))
                        && periodList.contains(String.valueOf(object[NumericConstants.TWO]))) {
                    return wac_price;
                }
            }

            // finding wac  Price Change
            int historyPeriod = Integer.parseInt(periodSid) - (currentfrquencyForWacReset + periodList.indexOf(periodSid));
            int baseperiod = Integer.parseInt(periodSid) - periodList.indexOf(periodSid);
            wac_price[NumericConstants.ONE] = calculateWacPriceChange(searchWacPrice(rsId, String.valueOf(baseperiod)), searchWacPrice(rsId, String.valueOf(historyPeriod)));
            return wac_price;
        } catch (NumberFormatException ex) {
            LOGGER.error(ex.getMessage());
            return wac_price;
        }
    }

    String[] fetchPeriod_Sids(String year, String frequencyValue) {

        String[] periods = new String[currentfrquencyForWacReset];

        try {

            int j = 0;
            for (int i = 0; i < periodTableList.size(); i++) {
                Object object[] = periodTableList.get(i);
                if (String.valueOf(object[NumericConstants.ONE]).equals(year) && (currentfrquencyForWacReset == NumericConstants.TWELVE
                        || String.valueOf(object[cureentWacFrquencyIndex]).equals(frequencyValue))) {
                    periods[j] = String.valueOf(object[NumericConstants.ZERO]);
                    j++;
                }
            }
            return periods;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return periods;
        }
    }

    String calculateWacPriceChange(String current, String history) {
        try {
            Double finalValue = (Double.parseDouble(current) - Double.parseDouble(history)) / Double.parseDouble(history);
            return String.valueOf(finalValue.isInfinite()||finalValue.isNaN()?Constants.ZERO:finalValue * NumericConstants.HUNDRED);
        } catch (NumberFormatException ex) {
            LOGGER.error(ex.getMessage());
            return ConstantsUtils.ZERO;
        }

    }

    String searchWacPrice(String rsId, String periodSId) {
        try {
            int i;
            for (i = NumericConstants.ZERO; i < wacPriceTableList.size(); i++) {
                Object object[] = wacPriceTableList.get(i);
                if (((String.valueOf(object[NumericConstants.ONE]).equals(rsId)) && String.valueOf(object[NumericConstants.TWO]).equals(String.valueOf(periodSId)))) {
                    return String.valueOf(object[NumericConstants.THREE]);
                }
            }
            return ConstantsUtils.ZERO;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return ConstantsUtils.ZERO;
        }
    }
}
