/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.utils;

import com.stpl.app.gtnforecasting.discountprojectionresults.dto.DiscountProjectionResultsDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.pparesults.dto.PPAProjectionResultsDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.HeaderUtils;
import static com.stpl.app.utils.Constants.FrequencyConstants.ANNUALLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUALLY;
import static com.stpl.app.utils.Constants.LabelConstants.ACTUALAMOUNT;
import static com.stpl.app.utils.Constants.LabelConstants.ACTUALEXFACTORY;
import static com.stpl.app.utils.Constants.LabelConstants.ACTUALRATE;
import static com.stpl.app.utils.Constants.LabelConstants.ACTUALRPU;
import static com.stpl.app.utils.Constants.LabelConstants.PERCENT;
import static com.stpl.app.utils.Constants.LabelConstants.PROJECTEDAMOUNT;
import static com.stpl.app.utils.Constants.LabelConstants.PROJECTEDEXFACTORY;
import static com.stpl.app.utils.Constants.LabelConstants.PROJECTEDRATE;
import static com.stpl.app.utils.Constants.LabelConstants.PROJECTEDRPU;
import com.stpl.ifs.ui.util.NumericConstants;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtTreeContainer;

/**
 *
 * @author
 */
public class ExcelUtils {

    public static final DecimalFormat UNITVOLUME = new DecimalFormat("#,##0");
    public static final DecimalFormat MONEY = new DecimalFormat("$#,##0");
    public static final DecimalFormat PERCENTFORMAT = new DecimalFormat("#,##0.00%");
    public static final DecimalFormat PERCENTFORMATEXFAC = new DecimalFormat("#,##0.00");
    public static final DecimalFormat PERCENT_FORMAT = new DecimalFormat("#,##0.000");
    private static final DecimalFormat DOLLAR_RPU_FORMAT = new DecimalFormat("$#,##0.00");

    public static void setExcelData(final List resultList, final List<PPAProjectionResultsDTO> totalList, final ProjectionSelectionDTO selection, ExtTreeContainer<PPAProjectionResultsDTO> excelBeanContainer)  {
        setTotalDataToExcelContainer(excelBeanContainer, totalList);
        setDataToExcelContainer(resultList, selection, excelBeanContainer);
    }

    private static void setTotalDataToExcelContainer(ExtTreeContainer<PPAProjectionResultsDTO> excelBeanContainer, final List<PPAProjectionResultsDTO> totalList) {
        PPAProjectionResultsDTO dto = new PPAProjectionResultsDTO();
        dto.setGroup(Constant.PROJECTION_TOTAL);
        dto.setLevelNo(0);
        dto.setIsTotalColumn(Boolean.TRUE);
        excelBeanContainer.addBean(dto);
        excelBeanContainer.addAll(totalList);
    }

    /**
     *
     * @param resultList
     * @param selection
     * @param excelBeanContainer
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static void setDataToExcelContainer(final List resultList, final ProjectionSelectionDTO selection, ExtTreeContainer<PPAProjectionResultsDTO> excelBeanContainer)  {

        Map<String, List> hierarchyDetailsMap = selection.getSessionDTO().getHierarchyLevelDetails();

        PPAProjectionResultsDTO dto = null;
        PPAProjectionResultsDTO discDol = null;
        PPAProjectionResultsDTO discPer = null;
        PPAProjectionResultsDTO unitVol = null;
        PPAProjectionResultsDTO totalDisc = null;

        Map<String, PPAProjectionResultsDTO> dtoMap = new HashMap<>();

        String lastHierarchyNo = StringUtils.EMPTY;
        String lastParentHierarchyNo;

        for (int i = 0; i < resultList.size(); i++) {

            Object[] firstRecord = (Object[]) resultList.get(0);
            Object[] obj = (Object[]) resultList.get(i);
            if (!lastHierarchyNo.equals(obj[1]) || (obj[NumericConstants.SIXTEEN] != null && obj[NumericConstants.SIXTEEN].toString().endsWith("-G"))) {
                if (dto != null) {
                    addItemsToContainer(excelBeanContainer, dto, discDol, discPer, unitVol, totalDisc);
                    if (dto.getParentHierarchyNo() != null) {
                        String parentNo = dto.getParentHierarchyNo();
                        if (parentNo.endsWith("-G")) {
                            parentNo = parentNo.replace("-G", "");
                            excelBeanContainer.setParent(dto, dtoMap.get(parentNo));
                            dtoMap.put(parentNo, dto);
                        } else {
                            excelBeanContainer.setParent(dto, dtoMap.get(parentNo));
                        }
                    }

                }
                lastHierarchyNo = obj[1].toString();
                lastParentHierarchyNo = obj[NumericConstants.SIXTEEN] == null ? null : obj[NumericConstants.SIXTEEN].toString();
                if (firstRecord[NumericConstants.SIXTEEN] != null && firstRecord[NumericConstants.SIXTEEN].toString().endsWith("-G") && obj[NumericConstants.TWO].toString().equals("Trading Partner")) {
                    lastParentHierarchyNo = firstRecord[NumericConstants.SIXTEEN].toString().substring(0, firstRecord[NumericConstants.SIXTEEN].toString().length() - NumericConstants.TWO);
                }

                dto = new PPAProjectionResultsDTO();
                dtoMap.put((lastParentHierarchyNo == null ? StringUtils.EMPTY : lastParentHierarchyNo + "~") + lastHierarchyNo, dto);
                dto.setHirarechyNo(lastHierarchyNo);
                dto.setParentHierarchyNo(lastParentHierarchyNo);
                if (lastParentHierarchyNo != null && lastParentHierarchyNo.endsWith("-G")) {
                    discDol = null;
                    discPer = null;
                    unitVol = null;
                    totalDisc = null;
                    dto.setGroup(obj[NumericConstants.TWO].toString());
                } else {
                    dto.setGroup(hierarchyDetailsMap.get(lastHierarchyNo).get(0).toString());
                    discDol = new PPAProjectionResultsDTO();
                    discPer = new PPAProjectionResultsDTO();
                    unitVol = new PPAProjectionResultsDTO();
                    totalDisc = new PPAProjectionResultsDTO();
                    discDol.setGroup("Discount $ Per Unit");
                    discPer.setGroup("Discount %");
                    unitVol.setGroup("Unit Volume");
                    totalDisc.setGroup("Total Discount Amount");
                }
            }
            if (discDol != null) {
                getCustomizedDTO(discDol, obj, selection);
                getCustomizedDTO(discPer, obj, selection);
                getCustomizedDTO(unitVol, obj, selection);
                getCustomizedDTO(totalDisc, obj, selection);
            }
        }
    }

    private static void addItemsToContainer(ExtTreeContainer<PPAProjectionResultsDTO> excelBeanContainer, PPAProjectionResultsDTO dto, PPAProjectionResultsDTO discDol, PPAProjectionResultsDTO discPer, PPAProjectionResultsDTO unitVol, PPAProjectionResultsDTO totalDisc) {
        excelBeanContainer.addItem(dto);
        if (!(dto.getParentHierarchyNo() != null && dto.getParentHierarchyNo().endsWith("-G"))) {
            excelBeanContainer.addItem(discDol);
            excelBeanContainer.addItem(discPer);
            excelBeanContainer.addItem(unitVol);
            excelBeanContainer.addItem(totalDisc);

            excelBeanContainer.setParent(discDol, dto);
            excelBeanContainer.setParent(discPer, dto);
            excelBeanContainer.setParent(unitVol, dto);
            excelBeanContainer.setParent(totalDisc, dto);
        }
    }

    private static void getCustomizedDTO(final PPAProjectionResultsDTO dto, final Object[] obj, ProjectionSelectionDTO selection) {
        DecimalFormat FORMAT = new DecimalFormat();
        int year = Integer.parseInt(String.valueOf(obj[NumericConstants.SIX]));
        int period = Integer.parseInt(String.valueOf(obj[NumericConstants.SEVEN]));
        int dataNo = NumericConstants.EIGHT;
        int dataProjNo = NumericConstants.EIGHT;
        String per = StringUtils.EMPTY;
        if (dto.getGroup().equalsIgnoreCase("Discount $ Per Unit")) {
            FORMAT = MONEY;
            dataNo = NumericConstants.TWELVE;
            dataProjNo = NumericConstants.THIRTEEN;
        } else if (dto.getGroup().equalsIgnoreCase(CommonUtils.VAR_DIS_RATE)) {
            per = "%";
            FORMAT = PERCENT_FORMAT;
            dataNo = NumericConstants.TEN;
            dataProjNo = NumericConstants.ELEVEN;
        } else if (dto.getGroup().equalsIgnoreCase("Unit Volume")) {
            FORMAT = UNITVOLUME;
            dataNo = NumericConstants.FOURTEEN;
            dataProjNo = NumericConstants.FIFTEEN;
        } else if (dto.getGroup().equalsIgnoreCase("Total Discount Amount")) {
            FORMAT = MONEY;
            dataNo = NumericConstants.EIGHT;
            dataProjNo = NumericConstants.NINE;
        }

        String header = isColumn(selection, String.valueOf(period), String.valueOf(year), Constant.PROJECTIONS, selection.getFrequency());
        String vis = isProjColumn(String.valueOf(period), String.valueOf(year), selection.getFrequency());
        if (header != null) {
            Boolean isProj = CommonUtils.setProjectionZero(selection, vis);
            dto.addStringProperties(header, (obj[dataProjNo] == null) ? FORMAT.format(Double.valueOf(Constant.DASH)) : isProj ? (FORMAT.format(0.0) + per) : (FORMAT.format(Double.valueOf(obj[dataProjNo].toString())) + per));
        }
        header = isColumn(selection, String.valueOf(period), String.valueOf(year), Constant.ACTUALS, selection.getFrequency());
        if (header != null) {
            dto.addStringProperties(header, (obj[dataNo] == null) ? FORMAT.format(Double.valueOf(Constant.DASH)) : (FORMAT.format(Double.valueOf(obj[dataNo].toString())) + per));
        }
    }

    private static String isColumn(ProjectionSelectionDTO selection, String quater, String year, String caption, String frequency) {
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

    private static String isProjColumn(String quater, String year, String frequency) {
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

    public static void setStaticRowToExcelContainer(ExtTreeContainer<DiscountProjectionResultsDTO> excelBeanContainer, final List<DiscountProjectionResultsDTO> totalList) {
        excelBeanContainer.addAll(totalList);
    }

    /**
     *
     * @param resultList
     * @param selection
     * @param excelBeanContainer
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public void setDataToExcelContainerForDPR(final List resultList, final List discountListForContract, final ProjectionSelectionDTO selection, ExtTreeContainer<DiscountProjectionResultsDTO> excelBeanContainer)  {
        Map<String, List> hierarchyDetailsMap = selection.getSessionDTO().getHierarchyLevelDetails();
        Map<String, DiscountProjectionResultsDTO> dtoMap = new HashMap<>();
        String frequency = selection.getFrequency();
        DiscountProjectionResultsDTO dto = new DiscountProjectionResultsDTO();
        int discountIndex = -1;
        for (int i = 0; i < resultList.size(); i++) {
            Object[] obj = (Object[]) resultList.get(i);
            String lastHierarchyNo = obj[0].toString();
            setActualProjectionValue(dto, obj, getFrequency(frequency, obj),selection);
            if (resultList.size() == i + 1 || !lastHierarchyNo.equals(((Object[]) resultList.get(i + 1))[0].toString())) {
                dto.setHierarchyNo(lastHierarchyNo);
                dto.setGroup(hierarchyDetailsMap.get(lastHierarchyNo).get(0).toString());
                dtoMap.put(lastHierarchyNo, dto);
                addItemsToContainer(excelBeanContainer, dto);
                if (dto.getHierarchyNo() != null) {
                    String parentNo = CommonLogic.getParentHierarchyNo(dto.getHierarchyNo());
                    DiscountProjectionResultsDTO parent = dtoMap.get(parentNo);
                    if (parent != null) {
                        excelBeanContainer.setParent(dto, parent);
                    }
                }
                discountIndex = discount(discountIndex, discountListForContract, frequency, excelBeanContainer, dto, selection);
                dto = new DiscountProjectionResultsDTO();
            }
        }
    }

    private int discount(int discountIndex, final List discountListForContract, final String frequency, ExtTreeContainer<DiscountProjectionResultsDTO> excelBeanContainer, DiscountProjectionResultsDTO parent, final ProjectionSelectionDTO selection) {
        DiscountProjectionResultsDTO dto = new DiscountProjectionResultsDTO();
        for (int i = discountIndex + 1; i < discountListForContract.size(); i++) {
            discountIndex = i;
            Object[] obj = (Object[]) discountListForContract.get(i);
            String discountName = obj[NumericConstants.TEN].toString();
            String lastHierarchyNo = obj[0].toString();
            setActualProjectionValue(dto, obj, getFrequency(frequency, obj), selection);
            if (parent.getHierarchyNo().equals(lastHierarchyNo) && (discountListForContract.size() == i + 1 || !discountName.equals(((Object[]) discountListForContract.get(i + 1))[NumericConstants.TEN].toString()) ||!lastHierarchyNo.equals(((Object[]) discountListForContract.get(i + 1))[0].toString()))) {
                dto.setHierarchyNo(lastHierarchyNo);
                dto.setGroup(discountName);
                addItemsToContainer(excelBeanContainer, dto);
                excelBeanContainer.setParent(dto, parent);
                dto = new DiscountProjectionResultsDTO();
                if (discountListForContract.size() > i + 1 && !parent.getHierarchyNo().equals(((Object[]) discountListForContract.get(i + 1))[0].toString())) {
                    break;
                }
            }
        }
        return discountIndex;
    }
 /**
     *
     * @param resultList
     * @param selection
     * @param excelBeanContainer
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public void setDataToExcelContainerForDPRCustom(final List resultList, final List discountListForContract, final ProjectionSelectionDTO selection, ExtTreeContainer<DiscountProjectionResultsDTO> excelBeanContainer,boolean isPeriodView)  {
        Map<String, List> hierarchyDetailsMap = selection.getSessionDTO().getHierarchyLevelDetails();
        Map<String, DiscountProjectionResultsDTO> dtoMap = new HashMap<>();
        String frequency = selection.getFrequency();
        int parentIndex=isPeriodView?NumericConstants.TEN:1;
        DiscountProjectionResultsDTO dto = new DiscountProjectionResultsDTO();
        int discountIndex = -1;
        for (int i = 0; i < resultList.size(); i++) {
            Object[] obj = (Object[]) resultList.get(i);
            String lastHierarchyNo = obj[0].toString();
            String lastParentHierarchyNo = obj[parentIndex] == null ? null : obj[parentIndex].toString();
             if(isPeriodView){
           setActualProjectionValue(dto, obj, getFrequency(frequency, obj),selection);
            }
            if (resultList.size() == i + 1 || (!lastHierarchyNo.equals(((Object[]) resultList.get(i + 1))[0].toString()))||(lastParentHierarchyNo!=null && !lastParentHierarchyNo.equals(((Object[]) resultList.get(i + 1))[parentIndex].toString()))) {
                dto.setGroup(hierarchyDetailsMap.get(lastHierarchyNo).get(0).toString());
                  dto.setParentHierarchyNo(lastParentHierarchyNo);
                     dto.setHierarchyNo(lastHierarchyNo);
                dtoMap.put((lastParentHierarchyNo == null ? StringUtils.EMPTY : lastParentHierarchyNo + "~") + lastHierarchyNo, dto);
                addItemsToContainer(excelBeanContainer, dto);
                if (dto.getParentHierarchyNo() != null) {
                    String parentNo = dto.getParentHierarchyNo();
                    DiscountProjectionResultsDTO parent = dtoMap.get(parentNo);
                    if (parent != null) {
                        excelBeanContainer.setParent(dto, parent);
                    }
                }
                if (!isPeriodView) {
                    discountIndex = discountIndexForCustomDiscountView(discountIndex, discountListForContract, frequency, excelBeanContainer, dto, selection);

                } else {
                   
                    discountIndex = discountIndexForCustomPeriodView(discountIndex, discountListForContract, frequency, excelBeanContainer, dto, selection);
                
                }
                dto = new DiscountProjectionResultsDTO();
            }
    }
        }

    private int discountIndexForCustomPeriodView(int discountIndex, final List discountListForContract, final String frequency, ExtTreeContainer<DiscountProjectionResultsDTO> excelBeanContainer, DiscountProjectionResultsDTO parent, final ProjectionSelectionDTO selection) {
        DiscountProjectionResultsDTO dto = new DiscountProjectionResultsDTO();
        for (int i = discountIndex + 1; i < discountListForContract.size(); i++) {
            discountIndex = i;
            Object[] obj = (Object[]) discountListForContract.get(i);
            String discountName = obj[NumericConstants.FOURTEEN].toString(); 
            String lastHierarchyNo = obj[0].toString();
            String lastParentHierarchyNo = obj[NumericConstants.TEN] == null ? null : obj[NumericConstants.TEN].toString();
            setActualProjectionValue(dto, obj, getFrequency(frequency, obj), selection);
            if ((parent.getHierarchyNo().equals(lastHierarchyNo) || (lastParentHierarchyNo != null && parent.getParentHierarchyNo().equals(lastParentHierarchyNo))) && (discountListForContract.size() == i + 1 || !discountName.equals(((Object[]) discountListForContract.get(i + 1))[NumericConstants.FOURTEEN].toString()) || !lastHierarchyNo.equals(((Object[]) discountListForContract.get(i + 1))[0].toString()) || lastParentHierarchyNo != null && !lastParentHierarchyNo.equals(((Object[]) discountListForContract.get(i + 1))[NumericConstants.TEN].toString()))) { 
                dto.setHierarchyNo(lastHierarchyNo);
                dto.setParentHierarchyNo(lastParentHierarchyNo);
                dto.setGroup(discountName);
                addItemsToContainer(excelBeanContainer, dto);
                excelBeanContainer.setParent(dto, parent);
                dto = new DiscountProjectionResultsDTO();
                if ((discountListForContract.size() > (i+1)) && (!parent.getHierarchyNo().equals(((Object[]) discountListForContract.get(i + 1))[0].toString()) || (lastParentHierarchyNo != null && !parent.getParentHierarchyNo().equals(((Object[]) discountListForContract.get(i + 1))[NumericConstants.TEN].toString())))) {
                    break;
                }
            }
        }
        return discountIndex;
    }
    
    private int discountIndexForCustomDiscountView(int discountIndex, final List discountListForContract, final String frequency, ExtTreeContainer<DiscountProjectionResultsDTO> excelBeanContainer, DiscountProjectionResultsDTO parent,final ProjectionSelectionDTO selection) {
        DiscountProjectionResultsDTO dto = new DiscountProjectionResultsDTO();
        for (int i = discountIndex + 1; i < discountListForContract.size(); i++) {
            discountIndex = i;
            Object[] obj = (Object[]) discountListForContract.get(i);
            String period = obj[NumericConstants.TWO].toString();
            String lastHierarchyNo = obj[0].toString();
            String lastParentHierarchyNo = obj[NumericConstants.TEN] == null ? null : obj[NumericConstants.TEN].toString();
            String periodsFreq = getFrequency(frequency, obj);
            if (selection.getPeriodList().contains(periodsFreq)) {
                setActualProjectionValue(dto, obj, obj[NumericConstants.FIFTEEN].toString().replace(" ", StringUtils.EMPTY), selection);
            if ((parent.getHierarchyNo().equals(lastHierarchyNo) || (lastParentHierarchyNo != null && parent.getParentHierarchyNo().equals(lastParentHierarchyNo))) && (discountListForContract.size() == i + 1 || !period.equals(((Object[]) discountListForContract.get(i + 1))[NumericConstants.TWO].toString()) || !lastHierarchyNo.equals(((Object[]) discountListForContract.get(i + 1))[0].toString()) || lastParentHierarchyNo != null && !lastParentHierarchyNo.equals(((Object[]) discountListForContract.get(i + 1))[NumericConstants.TEN].toString()))) {
                dto.setHierarchyNo(lastHierarchyNo);
                dto.setParentHierarchyNo(lastParentHierarchyNo);
                dto.setGroup(getFrequencyForGroup(frequency, obj));
                addItemsToContainer(excelBeanContainer, dto);
                excelBeanContainer.setParent(dto, parent);
                dto = new DiscountProjectionResultsDTO();
                if ((discountListForContract.size() > (i + 1)) && (!parent.getHierarchyNo().equals(((Object[]) discountListForContract.get(i + 1))[0].toString()) || (lastParentHierarchyNo != null && !parent.getParentHierarchyNo().equals(((Object[]) discountListForContract.get(i + 1))[NumericConstants.TEN].toString())))) {
                    break;
                }
            }
        }
        }
        return discountIndex;
    }
    
    private void setActualProjectionValue(DiscountProjectionResultsDTO dto, Object[] obj, String column, final ProjectionSelectionDTO projdto) {
        if (String.valueOf(obj[NumericConstants.NINE]).equals("0")) {
            dto.addStringProperties(column + ACTUALRATE.getConstant(), PERCENT_FORMAT.format(getvalue(obj[NumericConstants.THREE])) + PERCENT.getConstant());
            dto.addStringProperties(column + ACTUALAMOUNT.getConstant(), DOLLAR_RPU_FORMAT.format(getvalue(obj[NumericConstants.FOUR])));
            dto.addStringProperties(column + ACTUALRPU.getConstant(), DOLLAR_RPU_FORMAT.format(getvalue(obj[NumericConstants.FIVE])));
            if (String.valueOf(projdto.getView()).equals("Custom")) {
                dto.addStringProperties(column + ACTUALEXFACTORY.getConstant(), PERCENTFORMATEXFAC.format(getvalue(obj[NumericConstants.TWELVE])) + PERCENT.getConstant());
            } else {
                dto.addStringProperties(column + ACTUALEXFACTORY.getConstant(), PERCENTFORMATEXFAC.format(getvalue(obj[NumericConstants.ELEVEN])) + PERCENT.getConstant());
            }

        } else {
            dto.addStringProperties(column + PROJECTEDRATE.getConstant(), PERCENT_FORMAT.format(getvalue(obj[NumericConstants.SIX])) + PERCENT.getConstant());
            dto.addStringProperties(column + PROJECTEDAMOUNT.getConstant(), DOLLAR_RPU_FORMAT.format(getvalue(obj[NumericConstants.SEVEN])));
            dto.addStringProperties(column + PROJECTEDRPU.getConstant(), DOLLAR_RPU_FORMAT.format(getvalue(obj[NumericConstants.EIGHT])));
            if (String.valueOf(projdto.getView()).equals("Custom")) {
                dto.addStringProperties(column + PROJECTEDEXFACTORY.getConstant(), PERCENTFORMATEXFAC.format(getvalue(obj[NumericConstants.THIRTEEN])) + PERCENT.getConstant());
            } else {
                dto.addStringProperties(column + PROJECTEDEXFACTORY.getConstant(), PERCENTFORMATEXFAC.format(getvalue(obj[NumericConstants.TWELVE])) + PERCENT.getConstant());
            }
        }
    }

    private Object getvalue(Object obj) {
        if (obj == null || StringUtils.isBlank(obj.toString())) {
            return 0;
        } else {
            return obj;
        }
    }

    private static void addItemsToContainer(ExtTreeContainer<DiscountProjectionResultsDTO> excelBeanContainer, DiscountProjectionResultsDTO dto) {
        excelBeanContainer.addItem(dto);
    }

    private String getFrequency(String frequency, Object[] object) {
        String column = StringUtils.EMPTY;
        if (ANNUALLY.getConstant().equals(frequency)) {
            column = String.valueOf(object[1]);
        } else if (QUARTERLY.getConstant().equals(frequency)) {
            column = Constant.Q_SMALL + String.valueOf(object[NumericConstants.TWO]) + String.valueOf(object[1]);
        } else if (SEMI_ANNUALLY.getConstant().equals(frequency)) {
            column = Constant.S_SMALL + String.valueOf(object[NumericConstants.TWO]) + String.valueOf(object[1]);
        } else if (MONTHLY.getConstant().equals(frequency)) {
            String monthName = getMonthForInt(Integer.parseInt(String.valueOf(object[NumericConstants.TWO])) - 1);
            column = monthName.toLowerCase() + String.valueOf(object[1]);
        }
        return column;
    }

    private String getMonthForInt(int num) {
        String month = StringUtils.EMPTY;
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getShortMonths();
        if (num >= 0 && num <= NumericConstants.ELEVEN) {
            month = months[num];
        }
        return month;
    }
    
     private String getFrequencyForGroup(String frequency, Object[] object) {
        String column = StringUtils.EMPTY;
        if (ANNUALLY.getConstant().equals(frequency)) {
            column = String.valueOf(object[1]);
        } else if (QUARTERLY.getConstant().equals(frequency)) {
            column = Constant.Q + String.valueOf(object[NumericConstants.TWO]) + Constants.CommonConstants.SPACE + String.valueOf(object[1]);
        } else if (SEMI_ANNUALLY.getConstant().equals(frequency)) {
            column = Constant.S + String.valueOf(object[NumericConstants.TWO]) + Constants.CommonConstants.SPACE + String.valueOf(object[1]);
        } else if (MONTHLY.getConstant().equals(frequency)) {
            String monthName = getMonthForInt(Integer.parseInt(String.valueOf(object[NumericConstants.TWO])) - 1);
            column = monthName + Constants.CommonConstants.SPACE + String.valueOf(object[1]);
        }
        return column;
    }
      /**
     *
     * @param resultList
     * @param selection
     * @param excelBeanContainer
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public void setDataToExcelContainerForDPRDiscountView(final List resultList, final List discountListForContract, final ProjectionSelectionDTO selection, ExtTreeContainer<DiscountProjectionResultsDTO> excelBeanContainer)  {
        Map<String, List> hierarchyDetailsMap = selection.getSessionDTO().getHierarchyLevelDetails();
        Map<String, DiscountProjectionResultsDTO> dtoMap = new HashMap<>();
        String frequency = selection.getFrequency();
        DiscountProjectionResultsDTO dto = new DiscountProjectionResultsDTO();
        int discountIndex = -1;
        for (int i = 0; i < resultList.size(); i++) {
            String lastHierarchyNo =resultList.get(i).toString();
            if (resultList.size() == i + 1 || !lastHierarchyNo.equals(resultList.get(i + 1).toString())) {
                dto.setHierarchyNo(lastHierarchyNo);
                dto.setGroup(hierarchyDetailsMap.get(lastHierarchyNo).get(0).toString());
                dtoMap.put(lastHierarchyNo, dto);
                addItemsToContainer(excelBeanContainer, dto);
                if (dto.getHierarchyNo() != null) {
                    String parentNo = CommonLogic.getParentHierarchyNo(dto.getHierarchyNo());
                    DiscountProjectionResultsDTO parent = dtoMap.get(parentNo);
                    if (parent != null) {
                        excelBeanContainer.setParent(dto, parent);
                    }
                }
                discountIndex = discountForDiscountView(discountIndex, discountListForContract, frequency, excelBeanContainer, dto, selection);
                dto = new DiscountProjectionResultsDTO();
            }
        }
    }
    
 private int discountForDiscountView(int discountIndex, final List discountListForContract, final String frequency, ExtTreeContainer<DiscountProjectionResultsDTO> excelBeanContainer, DiscountProjectionResultsDTO parent, final ProjectionSelectionDTO selection) {
        DiscountProjectionResultsDTO dto = new DiscountProjectionResultsDTO();
        for (int i = discountIndex + 1; i < discountListForContract.size(); i++) {
            discountIndex = i;
            Object[] obj = (Object[]) discountListForContract.get(i);
            String period = obj[NumericConstants.TWO].toString();
            String lastHierarchyNo = obj[0].toString();
            String periodsFreq=getFrequency(frequency, obj);
            if(selection.getPeriodList().contains(periodsFreq)){
            setActualProjectionValue(dto, obj, obj[NumericConstants.THIRTEEN].toString().replace(" ", StringUtils.EMPTY), selection);
            if (parent.getHierarchyNo().equals(lastHierarchyNo) && (discountListForContract.size() == i + 1 || !period.equals(((Object[]) discountListForContract.get(i + 1))[NumericConstants.TWO].toString()) ||!lastHierarchyNo.equals(((Object[]) discountListForContract.get(i + 1))[0].toString()))) {
                dto.setHierarchyNo(lastHierarchyNo);
                dto.setGroup(getFrequencyForGroup(frequency,obj));
                addItemsToContainer(excelBeanContainer, dto);
                excelBeanContainer.setParent(dto, parent);
                dto = new DiscountProjectionResultsDTO();
                if (discountListForContract.size() > i + 1 && !parent.getHierarchyNo().equals(((Object[]) discountListForContract.get(i + 1))[0].toString())) {
                    break;
                }
            }
        }}
        return discountIndex;
    }
}
