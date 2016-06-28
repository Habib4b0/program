/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.pparesults.logic;

import com.stpl.app.galforecasting.dao.PPAPrjectionResultsDAO;
import com.stpl.app.galforecasting.dao.impl.PPAProjectionResultsDAOImpl;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.pparesults.dto.PPADetailsDTO;
import com.stpl.app.galforecasting.pparesults.dto.PPAHelperDTO;
import com.stpl.app.galforecasting.pparesults.dto.PPAProjectionResultsDTO;
import com.stpl.app.galforecasting.queryUtils.PPAQuerys;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.app.galforecasting.utils.HeaderUtils;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.util.Constants;
import com.stpl.app.util.ConstantsUtils;
import static com.stpl.app.utils.Constants.CommonConstants.DATE_FORMAT;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.converters.DataFormatConverter;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.stpl.app.galforecasting.utils.xmlparser.SQlUtil;
import com.vaadin.ui.ComboBox;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;

/**
 *
 * @author srithar
 */
public class PPAProjectionResultsLogic {
    
    private static final DecimalFormat TWO_DECIMAL = new DecimalFormat("#,##0.00");
     private static final DecimalFormat ZERO_DECIMAL = new DecimalFormat("#,##0");
    private static final String CURRENCY = "$";
    private static final String PERCENTAGE = "%";
    String indicater = StringUtils.EMPTY;
    final PPAPrjectionResultsDAO PPADAO = new PPAProjectionResultsDAOImpl();
    Date date = new Date();
    CustomTableHeaderDTO groupListForPivot;
    private static final Logger LOGGER = Logger.getLogger(PPAProjectionResultsLogic.class);
    private List chartList;
    DataFormatConverter salesFormat = new DataFormatConverter("#,##0", DataFormatConverter.INDICATOR_DOLLAR);
    DataFormatConverter growthFormat = new DataFormatConverter("#,##0", DataFormatConverter.INDICATOR_PERCENT);
    public List getChartList() {
        return chartList;
    }

    public void setChartList(List chartList) {
        this.chartList = chartList;
    }

    public String getIndicater() {
        return indicater;
    }

    public void setIndicater(String indicater) {
        this.indicater = indicater;

    }
    int neededRecord;
    int dataIndex;

    public List<PPAProjectionResultsDTO> nonMandatedPPAProjectionGenerate(String projectionid, ComboBox frequency) {

        return new ArrayList<PPAProjectionResultsDTO>();

    }

    public void savePPAResultsView(String save, String projectionId) throws PortalException, Exception {
        PPAPrjectionResultsDAO dao = new PPAProjectionResultsDAOImpl();
        dao.savePPAProjectionView(indicater, projectionId);
    }

    public List ppaProjecetionResults(int start, int offset, ProjectionSelectionDTO selection, SessionDTO session) {
        List list = null;
        List inputForList;
        int countLevel=0;
        String queryName=StringUtils.EMPTY;
        String frequency = selection.getFrequency();
        if (selection.isIsProjectionTotal()) {
            inputForList = getInputForTotal(session, selection);
        } else {
            inputForList = getInputForList1(selection.getHierarchyNo(), selection.getProjectionId(), selection.getTreeLevelNo(), session, selection);
        }
        if(selection.isIsCustomHierarchy()){
            String customer ="select count(distinct HIERARCHY_INDICATOR) from custom_view_details where CUSTOM_VIEW_MASTER_SID = "+selection.getCustomId();
                List<Object> level = HelperTableLocalServiceUtil.executeSelectQuery(customer);
                 countLevel = Integer.valueOf(String.valueOf(level.get(0)));
                 if(countLevel == 1){
                     queryName="PPAResults.CustomCCPQuary";
                 }else{
                     queryName="PPAResults.CustomCCPQuary2";
                 }
        }
        if (frequency.equals(Constant.MONTHLY)) {
            if (selection.isIsProjectionTotal()) {
                list = PPAQuerys.getPPAData(inputForList, "PPAResults.Monthly-Total", null);
            } else if (selection.isIsCustomHierarchy() && !queryName.isEmpty()) {
                list = PPAQuerys.getPPAData(getCustomCCpQuary(selection, session), queryName , "PPAResults.dataForTableLoad-Custom-Monthly");
            } else if (selection.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                list = PPAQuerys.getPPAData(inputForList, "PPAResults.Monthly-Customer", null);
            } else if (selection.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                list = PPAQuerys.getPPAData(inputForList, "PPAResults.Monthly-Product", null);
            }
        } else if (frequency.equals(Constant.QUARTERLY)) {
            if (selection.isIsProjectionTotal()) {
                list = PPAQuerys.getPPAData(inputForList, "PPAResults.dataForTableLoad-Quaterly-Total", null);
            } else if (selection.isIsCustomHierarchy() && !queryName.isEmpty()) {
                list = PPAQuerys.getPPAData(getCustomCCpQuary(selection, session), queryName , "PPAResults.dataForTableLoad-Custom-Quaterly");
            } else if (selection.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                list = PPAQuerys.getPPAData(inputForList, "PPAResults.dataForTableLoad-Quaterly1", null);
            } else if (selection.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                list = PPAQuerys.getPPAData(inputForList, "PPAResults.dataForTableLoad-Quaterly-Product", null);
            }
        } else if (frequency.equals(Constant.ANNUALLY)) {
            if (selection.isIsProjectionTotal()) {

                list = PPAQuerys.getPPAData(inputForList, "PPAResults.dataForTableLoad-Annually-Total", null);
            } else if (selection.isIsCustomHierarchy() && !queryName.isEmpty()) {
                list = PPAQuerys.getPPAData(getCustomCCpQuary(selection, session), queryName , "PPAResults.dataForTableLoad-Custom-annually");
            } else if (selection.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                list = PPAQuerys.getPPAData(inputForList, "PPAResults.dataForTableLoad-Annually", null);
            } else if (selection.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                list = PPAQuerys.getPPAData(inputForList, "PPAResults.dataForTableLoad-Annually_Product", null);
            }
        } else if (frequency.equals(Constant.SEMIANNUALLY)) {
            if (selection.isIsProjectionTotal()) {
                list = PPAQuerys.getPPAData(inputForList, "PPAResults.Semi-Annual-Total", null);
            } else if (selection.isIsCustomHierarchy() && !queryName.isEmpty()) {
                list = PPAQuerys.getPPAData(getCustomCCpQuary(selection, session), queryName , "PPAResults.dataForTableLoad-Custom-semiannually");
            } else if (selection.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                list = PPAQuerys.getPPAData(inputForList, "PPAResults.Customer-Semi-Annual", null);
            } else if (selection.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                list = PPAQuerys.getPPAData(inputForList, "PPAResults.countData-Semi-Annual-Product", null);
            }
        }

        printList(list);
        if (selection.getPivotView().equals(Constant.PERIOD)) {

            return getCustomizedList(list, selection);
        } else {
            return getCustomizedListForPivot(list, selection);
        }
    }

    private List getCustomizedList(List<Object[]> list, ProjectionSelectionDTO selection) {
        String frequency = selection.getFrequency();
        List<PPAProjectionResultsDTO> list1 = new ArrayList<PPAProjectionResultsDTO>();
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
        final int totalDiscountNo = 2;
        final int discountPercentNo = 3;
        final int unitVolumeNo = 4;
        final int discountDollerNo = 5;
        int freq = 0;
        int yearInt = 0;

        for (int i = 0; i < list.size(); i++) {

            Object[] str = list.get(i);

            freq = 0;
            yearInt = 1;
            int actulOrProjection = str[6] != null ? Integer.valueOf(str[6].toString()) : -1;
            String quater = null;
            String year = null;
            quater = str[freq].toString();
            year = str[yearInt].toString();

            String header = isColumn(selection, quater, year, Constant.PROJECTIONS, frequency);
            if (header != null && actulOrProjection == 1) {
                discountDoller.addStringProperties(header, (str[discountDollerNo] == null) ? CommonUtils.MONEY.format(Double.valueOf(Constant.DASH)) : CommonUtils.MONEY.format(Double.valueOf(str[discountDollerNo].toString())));
                discountPercent.addStringProperties(header, (str[discountPercentNo] == null) ? CommonUtils.PERCENT_FORMAT.format(Double.valueOf(Constant.DASH)) : CommonUtils.PERCENT_FORMAT.format(Double.valueOf(str[discountPercentNo].toString())));
                unitVolume.addStringProperties(header, (str[unitVolumeNo] == null) ? CommonUtils.UNITVOLUME.format(Double.valueOf(Constant.DASH)) : CommonUtils.UNITVOLUME.format(Double.valueOf(str[unitVolumeNo].toString())));
                totalDiscount.addStringProperties(header, (str[totalDiscountNo] == null) ? CommonUtils.MONEY.format(Double.valueOf(Constant.DASH)) : CommonUtils.MONEY.format(Double.valueOf(str[totalDiscountNo].toString())));
            }
            header = isColumn(selection, quater, year, Constant.ACTUALS, frequency);
            if (header != null && actulOrProjection == 0) {
                discountDoller.addStringProperties(header, (str[discountDollerNo] == null) ? CommonUtils.MONEY.format(Double.valueOf(Constant.DASH)) : CommonUtils.MONEY.format(Double.valueOf(str[discountDollerNo].toString())));
                discountPercent.addStringProperties(header, (str[discountPercentNo] == null) ? CommonUtils.PERCENT_FORMAT.format(Double.valueOf(Constant.DASH)) : CommonUtils.PERCENT_FORMAT.format(Double.valueOf(str[discountPercentNo].toString())));
                unitVolume.addStringProperties(header, (str[unitVolumeNo] == null) ? CommonUtils.UNITVOLUME.format(Double.valueOf(Constant.DASH)) : CommonUtils.UNITVOLUME.format(Double.valueOf(str[unitVolumeNo].toString())));
                totalDiscount.addStringProperties(header, (str[totalDiscountNo] == null) ? CommonUtils.MONEY.format(Double.valueOf(Constant.DASH)) : CommonUtils.MONEY.format(Double.valueOf(str[totalDiscountNo].toString())));
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
            constant = HeaderUtils.getMonthForInt(Integer.valueOf(quater) - 1).toLowerCase() + year + caption;

        } else if (frequency.equals(Constant.SEMIANNUALLY)) {
            constant = Constant.S_SMALL + quater + year + caption;
        }
        if (selection.hasColumn(constant)) {
            return constant;
        }
        return null;
    }

    private void printList(List<Object[]> list) {
        for (int i = 0; i < list.size(); i++) {
            Object[] str = list.get(i);
            for (Object obj : str) {
            }
        }
    }

    List getLevelValues(int projectionId, int levelNo, String parent) {
        List results = new ArrayList();
        List<Object[]> list = PPADAO.getLevelValues(projectionId, levelNo, parent);
        for (int i = 0; i < list.size(); i++) {
            PPAProjectionResultsDTO dto = new PPAProjectionResultsDTO();
            Object[] str = list.get(i);
            dto.setGroup(str[0].toString());
            dto.setLevelNo(Integer.valueOf(str[1].toString()));
            dto.setHirarechyNo(str[2].toString());
            results.add(dto);
        }
        return results;
    }

    public Leveldto getNextLevel(int levelNo, List<Leveldto> hierarchy, boolean isCustomHierarchy) {
        for (Leveldto dto : hierarchy) {
            if (isCustomHierarchy && dto.getTreeLevelNo() == levelNo) {
                return dto;
            } else if (dto.getLevelNo() == levelNo) {
                return dto;
            }
        }

        return null;
    }

    private List getCustomizedListForPivot(List<Object[]> list, ProjectionSelectionDTO selection) {
        String frequency = selection.getFrequency();
        List result = new ArrayList();
        List<String> dtoList = getFrequencyList(frequency, selection);
        List<String> tempList = new  ArrayList<>();
        tempList.addAll(dtoList);

        int yearInt;
        final int totalDiscountNo = 2;
        final int discountPercentNo = 3;
        final int unitVolumeNo = 4;
        final int discountDollerNo = 5;
        PPAProjectionResultsDTO dto = new PPAProjectionResultsDTO();
        Map<String, PPAProjectionResultsDTO> dtoMap = new TreeMap<>();
        for (int i = 0; i < list.size(); i++) {

            yearInt = 1;
            Object[] str = list.get(i);
            String year = str[yearInt].toString();
            int actulOrProjection = str[6] != null ? Integer.valueOf(str[6].toString()) : -1;
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
                constant = HeaderUtils.getMonthForInt(Integer.valueOf(freq) - Constant.ONE) + Constant.SPACE + year;

            }
            String key = year+(freq.length()==1?(0+freq):freq);
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
                    dto.setTotalDiscountActuals((str[totalDiscountNo] == null) ? CommonUtils.MONEY.format(Double.valueOf(Constant.DASH)) : CommonUtils.MONEY.format(Double.valueOf(str[totalDiscountNo].toString())));
                } else if (actulOrProjection == 1) {
                    dto.setDiscountPerUnitProjections((str[discountDollerNo] == null) ? CommonUtils.MONEY.format(Double.valueOf(Constant.DASH)) : CommonUtils.MONEY.format(Double.valueOf(str[discountDollerNo].toString())));
                    dto.setDiscountPercentProjections((str[discountPercentNo] == null) ? CommonUtils.PERCENT_FORMAT.format(Double.valueOf(Constant.DASH)) : CommonUtils.PERCENT_FORMAT.format(Double.valueOf(str[discountPercentNo].toString())));
                    dto.setUnitVolumeProjections((str[unitVolumeNo] == null) ? CommonUtils.UNITVOLUME.format(Double.valueOf(Constant.DASH)) : CommonUtils.UNITVOLUME.format(Double.valueOf(str[unitVolumeNo].toString())));
                    dto.setTotalDiscountProjections((str[totalDiscountNo] == null) ? CommonUtils.MONEY.format(Double.valueOf(Constant.DASH)) : CommonUtils.MONEY.format(Double.valueOf(str[totalDiscountNo].toString())));
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

    public List getFrequencyList(String frequency, ProjectionSelectionDTO selection) {
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

    private PPAProjectionResultsDTO setDefaultValueIndividual(PPAProjectionResultsDTO dto, List<String> visibleColumn, DecimalFormat formater) {
        for (int i = 0; i < visibleColumn.size(); i++) {
            dto.addStringProperties(visibleColumn.get(i), formater.format(Double.valueOf(Constant.DASH)));
        }
        return dto;
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

    private List getInputForList(String hirarechyNo, Integer projectionId, int levelNo, SessionDTO session, ProjectionSelectionDTO selection) {
        List input = new ArrayList();
        input.add(projectionId);
        input.add(projectionId);
        input.add(projectionId);
        if (selection.isIsProjectionTotal() || selection.isIsCustomHierarchy()) {
            input.add(StringUtils.EMPTY);
        } else {
            input.add(hirarechyNo);
        }
        input.add(projectionId);
        input.add(levelNo);
        input.add(projectionId);
        input.add(levelNo);
        input.add(session.getSessionId());
        input.add(session.getUserId());
        if (selection.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
            if (selection.getGroupFilter().equals(Constant.ALL_GROUP)) {
                input.add(Constant.PERCENT);
            } else {
                input.add(selection.getGroupFilter().replace(Constant.PPA, StringUtils.EMPTY));
            }
        }
        input.add(CommonUtils.DBDate.format(selection.getStartDate()));
        input.add(CommonUtils.DBDate.format(selection.getToDates()));
        input.add(projectionId);
        input.add(projectionId);
        input.add(projectionId);
        if (selection.isIsProjectionTotal() || selection.isIsCustomHierarchy()) {
            input.add(StringUtils.EMPTY);
        } else {
            input.add(hirarechyNo);
        }
        input.add(projectionId);
        input.add(levelNo);
        input.add(projectionId);
        input.add(levelNo);
        input.add(session.getSessionId());
        input.add(session.getUserId());
        if (selection.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
            if (selection.getGroupFilter().equals(Constant.ALL_GROUP)) {
                input.add(Constant.PERCENT);
            } else {
                input.add(selection.getGroupFilter().replace(Constant.PPA, StringUtils.EMPTY));
            }
        }

        input.add(CommonUtils.DBDate.format(selection.getStartDate()));
        input.add(CommonUtils.DBDate.format(selection.getToDates()));

        return input;
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
                startCalendar.add(Calendar.MONTH, -(history * 6));
            } else if (frequency.equals(Constant.QUARTERLY)) {
                startCalendar.add(Calendar.MONTH, -(history * 3));
            } else if (frequency.equals(Constant.MONTHLY)) {
                startCalendar.add(Calendar.MONTH, -history);
            }
            session.addHistoryAndStartDate(frequency, startCalendar.getTime());
        }

        return startCalendar.getTime();
    }

    private List getInputForTotal(SessionDTO session, ProjectionSelectionDTO selection) {
        List input = new ArrayList();        
        input.add(selection.getProjectionId());
        input.add(session.getSessionId());
        input.add(session.getUserId());
        if (selection.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
            if (selection.getGroupFilter().equals(Constant.ALL_GROUP)) {
                input.add(Constant.PERCENT);
            } else {
                input.add(selection.getGroupFilter().replace(Constant.PPA, StringUtils.EMPTY));
            }
        } else if (selection.isIsProjectionTotal()) {
            input.add(Constant.PERCENT);
        }
        input.add(CommonUtils.DBDate.format(selection.getStartDate()));
        input.add(CommonUtils.DBDate.format(selection.getToDates()));
        input.add(selection.getProjectionId());
        input.add(session.getSessionId());
        input.add(session.getUserId());
        if (selection.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
            if (selection.getGroupFilter().equals(Constant.ALL_GROUP)) {
                input.add(Constant.PERCENT);
            } else {
                input.add(selection.getGroupFilter().replace(Constant.PPA, StringUtils.EMPTY));
            }
        } else if (selection.isIsProjectionTotal()) {
            input.add(Constant.PERCENT);
        }
        input.add(CommonUtils.DBDate.format(selection.getStartDate()));
        input.add(CommonUtils.DBDate.format(selection.getToDates()));
        return input;
    }

    public int configureLevelsCount(ProjectionSelectionDTO selection) {
        int count = CommonLogic.getLevelListCount(selection.getProjectionId(), selection.getHierarchyIndicator(), selection.getTreeLevelNo(), selection.getHierarchyNo(), selection.getProductHierarchyNo(), selection.getCustomerHierarchyNo(), selection.isIsFilter(), selection.isIsCustomHierarchy(), selection.getCustomId(), selection.getGroupFilter(), selection.getUserId(), selection.getSessionId(), selection.getCustRelationshipBuilderSid(), selection.getProdRelationshipBuilderSid());
        return count;
    }

    public int getConfiguredPPAProjectionResultsCount(Object parentId, ProjectionSelectionDTO projSelDTO, boolean isLevelCount) {
        int count = 0;
        if (!projSelDTO.isIsFilter() || (parentId instanceof PPAProjectionResultsDTO)) {
            projSelDTO.setYear(Constant.All);
            if (projSelDTO.getActualsOrProjections().equals(Constant.BOTH)) {
                projSelDTO.setActualsOrProjections("Actuals and Projections");
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
                count = count + 4;
            } else {
                count = count + selection.getPeriodList().size();
            }

        }
        return count;
    }

    public int getProjectionResultsCount(ProjectionSelectionDTO projSelDTO, boolean isLevelCount) {
        int count = 0;
        projSelDTO.setGroupCount(false);
        if (!projSelDTO.getGroup().startsWith(Constant.All)
                && !projSelDTO.getGroup().contains(Constant.PPA)) {
            if (projSelDTO.getPivotView().contains(Constant.PERIOD)) {

                count = count + 4;
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
                    && !projSelDTO.getGroup().startsWith(Constant.All)
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
        List<PPAProjectionResultsDTO> resultList = new ArrayList<PPAProjectionResultsDTO>();
        if (!selection.isIsFilter() || (parentId instanceof PPAProjectionResultsDTO)) {
            selection.setYear(Constant.All);

            if (selection.getActualsOrProjections().equals(Constant.BOTH)) {
                selection.setActualsOrProjections("Actuals and Projections");
            }

            if (parentId instanceof PPAProjectionResultsDTO) {
                selection.setIsProjectionTotal(false);
                PPAProjectionResultsDTO parentDto = (PPAProjectionResultsDTO) parentId;
                selection.setLevelNo(parentDto.getLevelNo());
                selection.setTreeLevelNo(parentDto.getTreeLevelNo());
                selection.setHierarchyNo(parentDto.getHirarechyNo());
                if (!parentDto.getGroup().startsWith(Constant.All)
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
        List<PPAProjectionResultsDTO> resultList = new ArrayList<PPAProjectionResultsDTO>();
        projSelDTO.setIsProjectionTotal(true);
        if (!projSelDTO.isIsFilter()) {
            projSelDTO.setYear(Constant.All);

            if (projSelDTO.getActualsOrProjections().equals(Constant.BOTH)) {
                projSelDTO.setActualsOrProjections("Actuals and Projections");
            }

            resultList = getPPAProjectionResultsTotal(start, offset, projSelDTO, session);
        }
        return resultList;
    }

    public List<PPAProjectionResultsDTO> configureLevels(int start, int offset, ProjectionSelectionDTO projSelDTO) {
        int neededRecord = offset;

        List<PPAProjectionResultsDTO> resultList = new ArrayList<PPAProjectionResultsDTO>();
        if (neededRecord > 0) {
            List<Leveldto> levelList = CommonLogic.getConditionalLevelList(projSelDTO.getProjectionId(), start, offset, projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo(), projSelDTO.getHierarchyNo(), projSelDTO.getProductHierarchyNo(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.isIsFilter(), false, projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomId(), projSelDTO.getGroupFilter(), projSelDTO.getUserId(), projSelDTO.getSessionId(), projSelDTO.getCustRelationshipBuilderSid(), projSelDTO.getProdRelationshipBuilderSid(), false, true);

            for (int i = 0; i < levelList.size() && neededRecord > 0; i++) {
                Leveldto levelDto = levelList.get(i);

                PPAProjectionResultsDTO dto = new PPAProjectionResultsDTO();
                dto.setLevelNo(levelDto.getLevelNo());
                dto.setTreeLevelNo(levelDto.getTreeLevelNo());
                dto.setGroup(projSelDTO.getSessionDTO().getLevelValueDiscription(levelDto.getHierarchyNo(), levelDto.getHierarchyIndicator()));
                dto.setHirarechyNo(levelDto.getHierarchyNo());
                dto.setHirarechyIndicater(levelDto.getHierarchyIndicator());
                if (dto.getHirarechyIndicater().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                    dto.setCustomerHierarchyNo(dto.getHirarechyNo());
                    dto.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
                } else if (dto.getHirarechyIndicater().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                    dto.setProductHierarchyNo(dto.getHirarechyNo());
                    dto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
                }
                resultList.add(dto);
                neededRecord--;
            }
        }
        return resultList;
    }

    public List<PPAProjectionResultsDTO> getPPAProjectionResultsTotal(int start, int offset, ProjectionSelectionDTO selection, SessionDTO session) {
        int neededRecord = offset;
        int started = start;
        int maxRecord = 0;
        if (selection.getPivotView().equals(Constant.PERIOD)) {
            maxRecord = 4;
        } else {
            maxRecord = selection.getPeriodList().size() + 1;
        }
        List<PPAProjectionResultsDTO> resultList = new ArrayList<PPAProjectionResultsDTO>();
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
            totalList = ppaProjecetionResults(start, offset, selection, session);
            setChartList(totalList);
            if (started < maxRecord) {
                for (int k = started; k < totalList.size() && neededRecord > 0; k++) {
                    resultList.add(totalList.get(k));
                    neededRecord--;
                }
            }
        }
        return resultList;
    }

    public List<PPAProjectionResultsDTO> getPPAProjectionResults1(int start, int offset, ProjectionSelectionDTO selection, SessionDTO session) {
        int neededRecord = offset;
        int started = start;
        int maxRecord = 0;
        if (!selection.getGroup().startsWith(Constant.All)
                && !selection.getGroup().contains(Constant.PPA)) {
            if (selection.getPivotView().equals(Constant.PERIOD)) {
                maxRecord = 4;
            } else {
                maxRecord = selection.getPeriodList().size();
            }
        }
        if (selection.isIsProjectionTotal()) {
            maxRecord++;
        }
        List<PPAProjectionResultsDTO> projDTOList = new ArrayList<PPAProjectionResultsDTO>();
        if (!selection.isIsProjectionTotal()) {
            if (neededRecord > 0) {
                if (!selection.getGroup().startsWith(Constant.All)
                        && !selection.getGroup().contains(Constant.PPA)) {
                    List<PPAProjectionResultsDTO> resultList = new ArrayList<PPAProjectionResultsDTO>();
                    if (selection.getPivotView().contains(Constant.PERIOD)) {
                        try {
                            resultList = ppaProjecetionResults(start, offset, selection, session);
                        } catch (Exception ex) {
                            LOGGER.error(ex.getMessage());
                        }
                    } else {
                        resultList = getProjectionPivotTotal(start, offset, selection, session);
                    }
                    setChartList(resultList);
                    if (started < maxRecord) {
                        for (int k = started; k < resultList.size() && neededRecord > 0; k++) {
                            projDTOList.add(resultList.get(k));
                            neededRecord--;
                        }
                    }
                }
            }
        }

        if (neededRecord > 0 && !selection.isIsFilter()) {
            if ((selection.getTreeLevelNo() + 1) == selection.getTpLevel()
                    && ((selection.isIsCustomHierarchy()) || (!selection.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)))
                    && !selection.getGroup().startsWith(Constant.All)
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
                int resultStart = start;
                resultStart = (start <= maxRecord) ? 0 : start - maxRecord;
                selection.setTreeLevelNo(selection.getTreeLevelNo() + 1);
                List<PPAProjectionResultsDTO> nextLevelValueList = configureLevels(resultStart, neededRecord, selection);
                projDTOList.addAll(nextLevelValueList);
            }
        }

        return projDTOList;
    }

    public List<PPAProjectionResultsDTO> getProjectionPivotTotal(int start, int offset, ProjectionSelectionDTO selection, SessionDTO session) {
        List<PPAProjectionResultsDTO> projDTOList = new ArrayList<PPAProjectionResultsDTO>();
        try {
            projDTOList = ppaProjecetionResults(start, offset, selection, session);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return projDTOList;
    }

    private List getCustomCCpQuary(ProjectionSelectionDTO selection, SessionDTO session) {
        String customerLevelNo = StringUtils.EMPTY;
        String productLevelNo = StringUtils.EMPTY;

        if (selection.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {

            customerLevelNo = StringUtils.EMPTY + selection.getTreeLevelNo();
        } else if (selection.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {

            productLevelNo = StringUtils.EMPTY + selection.getTreeLevelNo();
        }
        List input = new ArrayList();
        input.add(selection.getProjectionId());
        if (selection.getGroupFilter().equals(Constant.ALL_GROUP)) {
            input.add(Constant.PERCENT);
        } else {
            input.add(selection.getGroupFilter().replace(Constant.PPA, StringUtils.EMPTY));
        }
        input.add(selection.getUserId());
        input.add(selection.getSessionId());
        input.add(selection.getProjectionId());

        if (selection.getGroupFilter().equals(Constant.ALL_GROUP)) {
            input.add(Constant.PERCENT);
        } else {
            input.add(selection.getGroupFilter().replace(Constant.PPA, StringUtils.EMPTY));
        }
        input.add(selection.getUserId());
        input.add(selection.getSessionId());
        input.add(selection.getCustomId());
        input.add(customerLevelNo);
        input.add(selection.getProjectionId());
        input.add(selection.getCustomerHierarchyNo());
        input.add(selection.getCustomId());
        input.add(productLevelNo);
        input.add(selection.getProjectionId());

        input.add(selection.getProductHierarchyNo());
        
        input.add(selection.getProjectionId());
        input.add(selection.getSessionId());
        input.add(selection.getUserId());
        input.add(CommonUtils.DBDate.format(selection.getStartDate()));
        input.add(CommonUtils.DBDate.format(session.getToDate()));
         input.add(selection.getProjectionId());
        input.add(selection.getSessionId());
        input.add(selection.getUserId());
        input.add(CommonUtils.DBDate.format(selection.getStartDate()));
        input.add(CommonUtils.DBDate.format(session.getToDate()));

        return input;

    }

    private List getInputForList1(String hierarchyNo, int projectionId, int treeLevelNo, SessionDTO session, ProjectionSelectionDTO selection) {
        List input = new ArrayList();
        input.add(selection.getRelationshipBuilderSid());
        input.add(projectionId);
        if (selection.isIsProjectionTotal()) {
            input.add(StringUtils.EMPTY);
        } else {
            input.add(hierarchyNo);
        }
        input.add(treeLevelNo);
        input.add(session.getSessionId());
        input.add(session.getUserId());
        if (selection.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
            if (selection.getGroupFilter().equals(Constant.ALL_GROUP)) {
                input.add(Constant.PERCENT);
            } else {
                input.add(selection.getGroupFilter().replace(Constant.PPA, StringUtils.EMPTY));
            }
        }
        input.add(CommonUtils.DBDate.format(selection.getStartDate()));
        input.add(CommonUtils.DBDate.format(selection.getToDates()));
        return input;
    }

    /**
     *
     * @param filter
     * @param dto
     * @return
     */
    public int getPPADetailsDDLBCount(String filter, HelperDTO dto, final String ddlbType,
            final int projectionId, PPADetailsDTO ppaDetailsDTO) throws Exception {
        String query = StringUtils.EMPTY;
        String value=StringUtils.EMPTY;
        switch (ddlbType) {
            case "contract":
                query = CustomSQLUtil.get("contract-ddlb");
                break;
            case "customer":
                query = CustomSQLUtil.get("customer-ddlb");
                break;
            case "brand":
                query = CustomSQLUtil.get("brand-ddlb");
                break;

        }
        query = query.replace("@projid", String.valueOf(projectionId));
        query = query.replace("@contractsid", String.valueOf(ppaDetailsDTO.getSelectedContract()));
        query = query.replace("@compsid", String.valueOf(ppaDetailsDTO.getSelectedCustomer()));
        query = query.replace("@brandsid", String.valueOf(ppaDetailsDTO.getSelectedBrand()));

          if(filter!=null || !(Constant.NULL.equalsIgnoreCase(String.valueOf(filter))) ){
        if (!filter.isEmpty()) {
            switch (ddlbType) {
                case "contract":
                    value = Constant.PERCENT + filter + Constant.PERCENT;
                    query = query.replace(Constant.FILTERR, "'"+value+"'");
                    break;
                case "customer":
                    value = Constant.PERCENT + filter + Constant.PERCENT;
                    query = query.replace(Constant.FILTERR, "'"+value+"'");
                    break;
                case "brand":
                    value = Constant.PERCENT + filter + Constant.PERCENT;
                    query = query.replace(Constant.FILTERR, "'"+value+"'");
                    break;

            }
        }}else{
         switch (ddlbType) {
              case "contract":
                    query = query.replace("AND CM.CONTRACT_NO LIKE @FILTER", StringUtils.EMPTY);
                    break;
                case "customer":
                    query = query.replace("AND COM.COMPANY_NO LIKE @FILTER", StringUtils.EMPTY);
                    break;
                case "brand":
                    query = query.replace("AND BM.BRAND_NAME LIKE @FILTER", StringUtils.EMPTY);
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
            final String ddlbType, final int projectionId, PPADetailsDTO ppaDetailsDTO) throws Exception {
        String query = StringUtils.EMPTY;
        String searchFilter =StringUtils.EMPTY;
        final List<HelperDTO> list = new ArrayList<HelperDTO>();
        switch (ddlbType) {

            case "contract":
                query = CustomSQLUtil.get("contract-ddlb");
                break;
            case "customer":
                query = CustomSQLUtil.get("customer-ddlb");
                break;
            case "brand":
                query = CustomSQLUtil.get("brand-ddlb");
                break;

        }
        query = query + "OFFSET " + startIndex + " ROWS FETCH NEXT " + end + " ROWS ONLY";
        query = query.replace("@projid", String.valueOf(projectionId));
        query = query.replace("@contractsid", String.valueOf(ppaDetailsDTO.getSelectedContract()));
        query = query.replace("@compsid", String.valueOf(ppaDetailsDTO.getSelectedCustomer()));
        query = query.replace("@brandsid", String.valueOf(ppaDetailsDTO.getSelectedBrand()));

        if(filter!=null || !(Constant.NULL.equalsIgnoreCase(String.valueOf(filter))) ){
        if (!filter.isEmpty()) {
            switch (ddlbType) {
                case "contract":
                    searchFilter = Constant.PERCENT + filter + Constant.PERCENT;
                    query = query.replace(Constant.FILTERR, "'"+searchFilter+"'");
                    break;
                case "customer":
                    searchFilter = Constant.PERCENT + filter + Constant.PERCENT;
                    query = query.replace(Constant.FILTERR, "'"+searchFilter+"'");
                    break;
                case "brand":
                    searchFilter = Constant.PERCENT + filter + Constant.PERCENT;
                    query = query.replace(Constant.FILTERR, "'"+searchFilter+"'");
                    break;

            }
        }}else{
         switch (ddlbType) {
              case "contract":
                    query = query.replace("AND CM.CONTRACT_NO LIKE @FILTER", StringUtils.EMPTY);
                    break;
                case "customer":
                    query = query.replace("AND COM.COMPANY_NO LIKE @FILTER", StringUtils.EMPTY);
                    break;
                case "brand":
                    query = query.replace("AND BM.BRAND_NAME LIKE @FILTER", StringUtils.EMPTY);
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
            helperTable.setId(value[0] != null ? Integer.valueOf(value[0].toString()) : 0);
            helperTable.setDescription(value[1] != null ? value[1].toString() : StringUtils.EMPTY);
            if (!StringUtils.EMPTY.equals(helperTable.getDescription())) {
                list.add(helperTable);
            }
        }
        return list;
    }
     public int getPPAItemCount(PPADetailsDTO ppaDetailsDTO,final String filterText ,String ddlbtype)  {
        String query = CustomSQLUtil.get("itemno-ddlb");
          String searchFilter =StringUtils.EMPTY;
        if (ppaDetailsDTO.getPpaHelperDTO().getItemMasterSysId() != 0) {
            query = query.replace("@itemsid", StringUtils.EMPTY + ppaDetailsDTO.getPpaHelperDTO().getItemMasterSysId());
        } else {
            query = query.replace("AND IM.ITEM_MASTER_SID NOT IN (@itemsid)", StringUtils.EMPTY);
        }
        if(filterText!=null || !(Constant.NULL.equalsIgnoreCase(String.valueOf(filterText))) ){
        if (!filterText.isEmpty()) {
            switch (ddlbtype) {
                case "itemNo":
                    searchFilter = Constant.PERCENT + filterText + Constant.PERCENT;
                     query = query.replace("AND IM.ITEM_NAME LIKE @FILTER", StringUtils.EMPTY);
                    query = query.replace(Constant.FILTERR, "'"+searchFilter+"'");
                    break;
                case "itemName":
                    searchFilter = Constant.PERCENT + filterText + Constant.PERCENT;
                    query = query.replace("AND IM.ITEM_NO LIKE @FILTER", StringUtils.EMPTY);
                    query = query.replace(Constant.FILTERR, "'"+searchFilter+"'");
                    break;
             

            }
        }
        }else{
         switch (ddlbtype) {
                case "itemNo":
                    query = query.replace("AND IM.ITEM_NO LIKE @FILTER AND IM.ITEM_NAME LIKE @FILTER", StringUtils.EMPTY);
                    break;
                case "itemName":
                    query = query.replace("AND IM.ITEM_NO LIKE @FILTER AND IM.ITEM_NAME LIKE @FILTER", StringUtils.EMPTY);
                    break;
             

            }
        
        }
        query = query.replace("@projid", String.valueOf(ppaDetailsDTO.getProjectionID()));
        query = query.replace("@contractsid", String.valueOf(ppaDetailsDTO.getSelectedContract()));
        query = query.replace("@compsid", String.valueOf(ppaDetailsDTO.getSelectedCustomer()));
        query = query.replace("@brandsid", String.valueOf(ppaDetailsDTO.getSelectedBrand()));

        List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return list.isEmpty() ? 0 : list.size();
    }

     
      public List<PPAHelperDTO> getPPAItemDDLBResult(int startIndex, int end, PPADetailsDTO ppaDetailsDTO, String ddlbtype,final String filterText) throws Exception {
        LOGGER.info("Inside Item Load" + startIndex + "END INDEX" + end + "ppaDetailsDTO" + ppaDetailsDTO.getPpaHelperDTO().getItemMasterSysId());
        String query = StringUtils.EMPTY;
        String searchFilter=StringUtils.EMPTY;
        final List<PPAHelperDTO> list = new ArrayList<PPAHelperDTO>();
        switch (ddlbtype) {
            case "itemNo":
                query = CustomSQLUtil.get("itemno-ddlb");
                break;
            case "itemName":
                query = CustomSQLUtil.get("itemno-ddlb");
                break;
        }

        if (ppaDetailsDTO.getPpaHelperDTO().getItemMasterSysId() != 0) {
            query = query.replace("@itemsid", String.valueOf(ppaDetailsDTO.getPpaHelperDTO().getItemMasterSysId()));
        } else {
            query = query.replace("AND IM.ITEM_MASTER_SID NOT IN (@itemsid)", StringUtils.EMPTY);
        }
       if(filterText!=null || !(Constant.NULL.equalsIgnoreCase(String.valueOf(filterText))) ){
        if (!filterText.isEmpty()) {
            switch (ddlbtype) {
                case "itemNo":
                    searchFilter = Constant.PERCENT + filterText + Constant.PERCENT;
                    query = query.replace("AND IM.ITEM_NAME LIKE @FILTER", StringUtils.EMPTY);
                    query = query.replace(Constant.FILTERR, "'"+searchFilter+"'");
                    break;
                case "itemName":
                    searchFilter = Constant.PERCENT + filterText + Constant.PERCENT;
                    query = query.replace("AND IM.ITEM_NO LIKE @FILTER", StringUtils.EMPTY);
                    query = query.replace(Constant.FILTERR, "'"+searchFilter+"'");
                    break;
            }
        }
        }else{
         switch (ddlbtype) {
                case "itemNo":
                    query = query.replace("AND IM.ITEM_NO LIKE @FILTER AND IM.ITEM_NAME LIKE @FILTER", StringUtils.EMPTY);
                    break;
                case "itemName":
                    query = query.replace("AND IM.ITEM_NO LIKE @FILTER AND IM.ITEM_NAME LIKE @FILTER", StringUtils.EMPTY);
                    break;
             

            }
        
        }
        
        query = query + "OFFSET " + startIndex + " ROWS FETCH NEXT " + end + " ROWS ONLY";
        query = query.replace("@projid", String.valueOf(ppaDetailsDTO.getProjectionID()));
        query = query.replace("@contractsid", String.valueOf(ppaDetailsDTO.getSelectedContract()));
        query = query.replace("@compsid", String.valueOf(ppaDetailsDTO.getSelectedCustomer()));
        query = query.replace("@brandsid", String.valueOf(ppaDetailsDTO.getSelectedBrand()));
        List<Object[]> returnList = (List<Object[]>) HelperTableLocalServiceUtil.executeSelectQuery(query);


        PPAHelperDTO helperTable;
        if (startIndex == ConstantsUtils.ZERO_INT) {
            helperTable = new PPAHelperDTO(0, Constants.SELECT_ONE, Constants.SELECT_ONE);
            list.add(helperTable);
        }

        for (final Iterator<Object[]> iterator = returnList.iterator(); iterator.hasNext();) {
            final Object[] value = iterator.next();
            helperTable = new PPAHelperDTO();
            helperTable.setItemMasterSysId(value[0] != null ? Integer.valueOf(value[0].toString()) : 0);
            helperTable.setItemNo(value[1] != null ? value[1].toString() : StringUtils.EMPTY);
            helperTable.setItemName(value[2] != null ? value[2].toString() : StringUtils.EMPTY);
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
        query = query.replace("@userid", sessionDTO.getUserId());
        query = query.replace("@sessionid", sessionDTO.getSessionId());
        query = query.replace("@projid", String.valueOf(sessionDTO.getProjectionId()));
        query = query.replace("@contractsid", String.valueOf(ppaDetailsDTO.getSelectedContract()));
        query = query.replace("@compsid", String.valueOf(ppaDetailsDTO.getSelectedCustomer()));
        query = query.replace("@itemsid", String.valueOf(ppaDetailsDTO.getPpaHelperDTO().getItemMasterSysId()));
        query = query.replace("@from", String.valueOf(ppaDetailsDTO.getStartPeriod()));
        query = query.replace("@to", String.valueOf(ppaDetailsDTO.getEndPeriod()));
        query = query.replace("@start", String.valueOf(start));
        query = query.replace("@end", String.valueOf(offset));
        if (!(sortByColumns == null || sortByColumns.isEmpty())) {
            for (SortByColumn sortByColumn : sortByColumns) {
                if (sortByColumn.getName().equals("period")) {
                    asc = (sortByColumn.getType().equals(SortByColumn.Type.ASC) ? "ASC" : "DESC");
                } else {
                    asc = StringUtils.EMPTY;
                }
            }
        } else {
            asc = StringUtils.EMPTY;
        }
        query = query.replace("@order", asc);
        List resultList = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        if (isCount) {
            int count = (Integer) resultList.get(0);
            return count;
        } else {
            return convertPPADetailsList(resultList);
        }
    }
    /**
     *
     * @param resultList
     * @return
     */
     private Object convertPPADetailsList(final List<Object[]> list) {
        List<PPADetailsDTO> resultList = new ArrayList<PPADetailsDTO>();
        SimpleDateFormat myFormat = new SimpleDateFormat(Constant.DATE_FORMAT);
        for (Object[] object : list) {
            try {
                PPADetailsDTO detailsDTO = new PPADetailsDTO();
                detailsDTO.setPeriod(((object[0] != null ? String.valueOf(object[0]) : StringUtils.EMPTY)) + " " + (object[1] != null ? CommonUtils.getMonthName(Integer.valueOf(String.valueOf(object[1]))) : StringUtils.EMPTY));
                detailsDTO.setPriceProtectionPriceType(object[2] != null ? String.valueOf(object[2]) : StringUtils.EMPTY);
                detailsDTO.setPrice(object[3] != null ? getFormatValue(TWO_DECIMAL, String.valueOf(object[3]), CURRENCY) : StringUtils.EMPTY);
                detailsDTO.setPriceChange(object[4] != null ? getFormatValue(TWO_DECIMAL, String.valueOf(object[4]), PERCENTAGE) : StringUtils.EMPTY);
                detailsDTO.setMap(object[5] != null ? getFormatValue(TWO_DECIMAL, String.valueOf(object[5]), CURRENCY) : StringUtils.EMPTY);
                detailsDTO.setTotalDeductions(object[6] != null ? getFormatValue(TWO_DECIMAL, String.valueOf(object[6]), CURRENCY) : StringUtils.EMPTY);
                detailsDTO.setUnits(object[7] != null ? getFormatValue(ZERO_DECIMAL, String.valueOf(object[7]), StringUtils.EMPTY ) : StringUtils.EMPTY);
                detailsDTO.setDeductionPerUnit(object[8] != null ? getFormatValue(TWO_DECIMAL, String.valueOf(object[8]), CURRENCY) : StringUtils.EMPTY);
                detailsDTO.setNetPrice((object[9] != null ? getFormatValue(TWO_DECIMAL, String.valueOf(object[9]), CURRENCY) : StringUtils.EMPTY));
                detailsDTO.setNetMAP(object[10] != null ? getFormatValue(TWO_DECIMAL, String.valueOf(object[10]), CURRENCY) : StringUtils.EMPTY);
                detailsDTO.setPriceProtectionAmountPerUnit(object[11] != null ? getFormatValue(TWO_DECIMAL, String.valueOf(object[11]), CURRENCY) : StringUtils.EMPTY);
                detailsDTO.setPriceProtectionPercent(object[12] != null ? getFormatValue(TWO_DECIMAL, String.valueOf(object[12]), PERCENTAGE) : StringUtils.EMPTY);
                detailsDTO.setTotalPriceProtectionDeduction(object[13] != null ? getFormatValue(TWO_DECIMAL, String.valueOf(object[13]), PERCENTAGE) : StringUtils.EMPTY);
                detailsDTO.setNep(object[14] != null ? getFormatValue(TWO_DECIMAL, String.valueOf(object[14]), CURRENCY) : StringUtils.EMPTY);
                detailsDTO.setNepFormula(object[15] != null ? String.valueOf(object[15]) : StringUtils.EMPTY);
                detailsDTO.setPriceToleranceType(object[16] != null ? String.valueOf(object[16]) : StringUtils.EMPTY);
                detailsDTO.setPriceTolerance(object[17] != null ? getFormatValue(ZERO_DECIMAL, String.valueOf(object[17]), StringUtils.EMPTY )  : StringUtils.EMPTY);
                detailsDTO.setPriceToleranceInterval(object[18] != null ?getFormatValue(ZERO_DECIMAL, String.valueOf(object[18]), StringUtils.EMPTY )  : StringUtils.EMPTY);
                detailsDTO.setPriceToleranceFrequency(object[19] != null ? String.valueOf(object[19]) : StringUtils.EMPTY);
                detailsDTO.setMaxIncrementalChange(object[20] != null ? String.valueOf(object[20]) : StringUtils.EMPTY);
                detailsDTO.setResetEligible(object[21] != null ? String.valueOf(object[21]) : StringUtils.EMPTY);
                detailsDTO.setResetType(object[22] != null ? String.valueOf(object[22]) : StringUtils.EMPTY);
                if(object[23] != null ){
                SimpleDateFormat fromUser = new SimpleDateFormat(DATE_FORMAT.getConstant());
                String formateddate= fromUser.format(myFormat.parse(String.valueOf(object[23])));
                detailsDTO.setResetDate(formateddate);
                }else{
                    detailsDTO.setResetDate(StringUtils.EMPTY);
                }
                
                detailsDTO.setResetInterval(object[24] != null ? String.valueOf(object[24]) : StringUtils.EMPTY);
                detailsDTO.setResetFrequency(object[25] != null ? String.valueOf(object[25]) : StringUtils.EMPTY);
                detailsDTO.setNetPriceType(object[26] != null ? String.valueOf(object[26]) : StringUtils.EMPTY);
                detailsDTO.setNetPriceTypeFormula(object[27] != null ? String.valueOf(object[27]) : StringUtils.EMPTY);
                detailsDTO.setRebateScheduleName(object[28] != null ? String.valueOf(object[28]) : StringUtils.EMPTY);
                resultList.add(detailsDTO);
            } catch (ParseException ex) {
                LOGGER.error(ex.getMessage());
            }
        }

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
}