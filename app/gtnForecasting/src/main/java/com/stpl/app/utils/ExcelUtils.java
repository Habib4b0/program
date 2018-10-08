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
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtTreeContainer;

/**
 *
 * @author
 */
public class ExcelUtils {

    public static final DecimalFormat UNITVOLUME_EXCEL = new DecimalFormat("#,##0");
    public static final DecimalFormat MONEY_EXCEL = new DecimalFormat("$#,##0");
    public static final DecimalFormat PERCENTFORMAT_EXCEL = new DecimalFormat("#,##0.00%");
    public static final DecimalFormat PERCENTFORMATEXFAC_EXCEL = new DecimalFormat("#,##0.00");
    public static final DecimalFormat PERCENT_FORMAT_EXCEL = new DecimalFormat("#,##0.000");
    private static final DecimalFormat DOLLAR_RPU_FORMAT_EXCEL = new DecimalFormat("$#,##0.00");

    public static void setExcelData(final List resultListData, final List<PPAProjectionResultsDTO> totalListData, final ProjectionSelectionDTO selectionData, ExtTreeContainer<PPAProjectionResultsDTO> excelBeanContainerData)  {
        setTotalDataToExcelContainer(excelBeanContainerData, totalListData);
        setDataToExcelContainer(resultListData, selectionData, excelBeanContainerData);
    }

    private static void setTotalDataToExcelContainer(ExtTreeContainer<PPAProjectionResultsDTO> excelTotalBeanContainer, final List<PPAProjectionResultsDTO> totalListPPA) {
        PPAProjectionResultsDTO dtoPPA = new PPAProjectionResultsDTO();
        dtoPPA.setGroup(Constant.PROJECTION_TOTAL);
        dtoPPA.setLevelNo(0);
        dtoPPA.setIsTotalColumn(Boolean.TRUE);
        excelTotalBeanContainer.addBean(dtoPPA);
        excelTotalBeanContainer.addAll(totalListPPA);
    }

    /**
     *
     * @param resultListExcel
     * @param selection
     * @param excelBeanContainer
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static void setDataToExcelContainer(final List resultListExcel, final ProjectionSelectionDTO selection, ExtTreeContainer<PPAProjectionResultsDTO> excelBeanContainer)  {

        Map<String, List> hierarchyDetailsMapExcel = selection.getSessionDTO().getHierarchyLevelDetails();

        PPAProjectionResultsDTO dtoExcel = null;
        PPAProjectionResultsDTO discDolExcel = null;
        PPAProjectionResultsDTO discPerExcel = null;
        PPAProjectionResultsDTO unitVolExcel = null;
        PPAProjectionResultsDTO totalDiscExcel = null;

        Map<String, PPAProjectionResultsDTO> dtoMapExcel = new HashMap<>();

        String lastHierarchyNoExcel = StringUtils.EMPTY;
        String lastParentHierarchyNoExcel;

        for (int i = 0; i < resultListExcel.size(); i++) {

            Object[] firstRecordExcel = (Object[]) resultListExcel.get(0);
            Object[] objExcel = (Object[]) resultListExcel.get(i);
            if (!lastHierarchyNoExcel.equals(objExcel[1]) || (objExcel[NumericConstants.SIXTEEN] != null && objExcel[NumericConstants.SIXTEEN].toString().endsWith("-G"))) {
                if (dtoExcel != null) {
                    addItemsToContainer(excelBeanContainer, dtoExcel, discDolExcel, discPerExcel, unitVolExcel, totalDiscExcel);
                    if (dtoExcel.getParentHierarchyNo() != null) {
                        String parentNoExcel = dtoExcel.getParentHierarchyNo();
                        if (parentNoExcel.endsWith("-G")) {
                            parentNoExcel = parentNoExcel.replace("-G", "");
                            excelBeanContainer.setParent(dtoExcel, dtoMapExcel.get(parentNoExcel));
                            dtoMapExcel.put(parentNoExcel, dtoExcel);
                        } else {
                            excelBeanContainer.setParent(dtoExcel, dtoMapExcel.get(parentNoExcel));
                        }
                    }

                }
                lastHierarchyNoExcel = objExcel[1].toString();
                lastParentHierarchyNoExcel = objExcel[NumericConstants.SIXTEEN] == null ? null : objExcel[NumericConstants.SIXTEEN].toString();
                if (firstRecordExcel[NumericConstants.SIXTEEN] != null && firstRecordExcel[NumericConstants.SIXTEEN].toString().endsWith("-G") && objExcel[NumericConstants.TWO].toString().equals("Trading Partner")) {
                    lastParentHierarchyNoExcel = firstRecordExcel[NumericConstants.SIXTEEN].toString().substring(0, firstRecordExcel[NumericConstants.SIXTEEN].toString().length() - NumericConstants.TWO);
                }

                dtoExcel = new PPAProjectionResultsDTO();
                dtoMapExcel.put((lastParentHierarchyNoExcel == null ? StringUtils.EMPTY : lastParentHierarchyNoExcel + "~") + lastHierarchyNoExcel, dtoExcel);
                dtoExcel.setHirarechyNo(lastHierarchyNoExcel);
                dtoExcel.setParentHierarchyNo(lastParentHierarchyNoExcel);
                if (lastParentHierarchyNoExcel != null && lastParentHierarchyNoExcel.endsWith("-G")) {
                    discDolExcel = null;
                    discPerExcel = null;
                    unitVolExcel = null;
                    totalDiscExcel = null;
                    dtoExcel.setGroup(objExcel[NumericConstants.TWO].toString());
                } else {
                    dtoExcel.setGroup(hierarchyDetailsMapExcel.get(lastHierarchyNoExcel).get(0).toString());
                    discDolExcel = new PPAProjectionResultsDTO();
                    discPerExcel = new PPAProjectionResultsDTO();
                    unitVolExcel = new PPAProjectionResultsDTO();
                    totalDiscExcel = new PPAProjectionResultsDTO();
                    discDolExcel.setGroup("Discount $ Per Unit");
                    discPerExcel.setGroup("Discount %");
                    unitVolExcel.setGroup("Unit Volume");
                    totalDiscExcel.setGroup("Total Discount Amount");
                }
            }
            if (discDolExcel != null) {
                getCustomizedDTO(discDolExcel, objExcel, selection);
                getCustomizedDTO(discPerExcel, objExcel, selection);
                getCustomizedDTO(unitVolExcel, objExcel, selection);
                getCustomizedDTO(totalDiscExcel, objExcel, selection);
            }
        }
    }

    private static void addItemsToContainer(ExtTreeContainer<PPAProjectionResultsDTO> excelAddBeanContainer, PPAProjectionResultsDTO dtoExcelPPA, PPAProjectionResultsDTO discDol, PPAProjectionResultsDTO discPer, PPAProjectionResultsDTO unitVol, PPAProjectionResultsDTO totalDisc) {
        excelAddBeanContainer.addItem(dtoExcelPPA);
        if (!(dtoExcelPPA.getParentHierarchyNo() != null && dtoExcelPPA.getParentHierarchyNo().endsWith("-G"))) {
            excelAddBeanContainer.addItem(discDol);
            excelAddBeanContainer.addItem(discPer);
            excelAddBeanContainer.addItem(unitVol);
            excelAddBeanContainer.addItem(totalDisc);

            excelAddBeanContainer.setParent(discDol, dtoExcelPPA);
            excelAddBeanContainer.setParent(discPer, dtoExcelPPA);
            excelAddBeanContainer.setParent(unitVol, dtoExcelPPA);
            excelAddBeanContainer.setParent(totalDisc, dtoExcelPPA);
        }
    }

    private static void getCustomizedDTO(final PPAProjectionResultsDTO dto, final Object[] obj, ProjectionSelectionDTO selection) {
        DecimalFormat formatDec = new DecimalFormat();
        int year = Integer.parseInt(String.valueOf(obj[NumericConstants.SIX]));
        int period = Integer.parseInt(String.valueOf(obj[NumericConstants.SEVEN]));
        int dataNo = NumericConstants.EIGHT;
        int dataProjNo = NumericConstants.EIGHT;
        String per = StringUtils.EMPTY;
        if (dto.getGroup().equalsIgnoreCase("Discount $ Per Unit")) {
            formatDec = MONEY_EXCEL;
            dataNo = NumericConstants.TWELVE;
            dataProjNo = NumericConstants.THIRTEEN;
        } else if (dto.getGroup().equalsIgnoreCase(CommonUtils.VAR_DIS_RATE)) {
            per = "%";
            formatDec = PERCENT_FORMAT_EXCEL;
            dataNo = NumericConstants.TEN;
            dataProjNo = NumericConstants.ELEVEN;
        } else if (dto.getGroup().equalsIgnoreCase("Unit Volume")) {
            formatDec = UNITVOLUME_EXCEL;
            dataNo = NumericConstants.FOURTEEN;
            dataProjNo = NumericConstants.FIFTEEN;
        } else if (dto.getGroup().equalsIgnoreCase("Total Discount Amount")) {
            formatDec = MONEY_EXCEL;
            dataNo = NumericConstants.EIGHT;
            dataProjNo = NumericConstants.NINE;
        }

        String header = isColumn(selection, String.valueOf(period), String.valueOf(year), Constant.PROJECTIONS, selection.getFrequency());
        String vis = isProjColumn(String.valueOf(period), String.valueOf(year), selection.getFrequency());
        if (header != null) {
            Boolean isProj = CommonUtils.setProjectionZero(selection, vis);
            dto.addStringProperties(header, (obj[dataProjNo] == null) ? formatDec.format(Double.valueOf(Constant.DASH)) : isProj ? (formatDec.format(0.0) + per) : (formatDec.format(Double.valueOf(obj[dataProjNo].toString())) + per));
        }
        header = isColumn(selection, String.valueOf(period), String.valueOf(year), Constant.ACTUALS, selection.getFrequency());
        if (header != null) {
            dto.addStringProperties(header, (obj[dataNo] == null) ? formatDec.format(Double.valueOf(Constant.DASH)) : (formatDec.format(Double.valueOf(obj[dataNo].toString())) + per));
        }
    }

    private static String isColumn(ProjectionSelectionDTO selection, String quater, String year, String caption, String frequencyExcel) {
        String constantExcel = StringUtils.EMPTY;
        if (frequencyExcel.equals(Constant.QUARTERLY)) {
            constantExcel = Constant.Q_SMALL + quater + year + caption;
        } else if (frequencyExcel.equals(Constant.ANNUALLY)) {
            constantExcel = year + caption;
        } else if (frequencyExcel.equals(Constant.MONTHLY)) {
            constantExcel = HeaderUtils.getMonthForInt(Integer.parseInt(quater) - 1).toLowerCase(Locale.ENGLISH) + year + caption;

        } else if (frequencyExcel.equals(Constant.SEMIANNUALLY)) {
            constantExcel = Constant.S_SMALL + quater + year + caption;
        }
        if (selection.hasColumn(constantExcel)) {
            return constantExcel;
        }
        return null;
    }

    private static String isProjColumn(String quater, String year, String frequencyProjExcel) {
        String constantProjExcel = StringUtils.EMPTY;
        if (frequencyProjExcel.equals(Constant.QUARTERLY)) {
            constantProjExcel = Constant.Q_SMALL + quater + year;
        } else if (frequencyProjExcel.equals(Constant.ANNUALLY)) {
            constantProjExcel = year;
        } else if (frequencyProjExcel.equals(Constant.MONTHLY)) {
            constantProjExcel = HeaderUtils.getMonthForInt(Integer.parseInt(quater) - 1).toLowerCase(Locale.ENGLISH) + year;

        } else if (frequencyProjExcel.equals(Constant.SEMIANNUALLY)) {
            constantProjExcel = Constant.S_SMALL + quater + year;
        }

        return constantProjExcel;
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
         int discountView = discountIndex;
        for (int i = discountView + 1; i < discountListForContract.size(); i++) {
            discountView = i;
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
        return discountView;
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
         int discountIndexCustomPeriodView = discountIndex;
        for (int i = discountIndexCustomPeriodView + 1; i < discountListForContract.size(); i++) {
            discountIndexCustomPeriodView = i;
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
        return discountIndexCustomPeriodView;
    }
    
    private int discountIndexForCustomDiscountView(int discountIndex, final List discountListForContract, final String frequency, ExtTreeContainer<DiscountProjectionResultsDTO> excelBeanContainer, DiscountProjectionResultsDTO parent,final ProjectionSelectionDTO selection) {
        DiscountProjectionResultsDTO dto = new DiscountProjectionResultsDTO();
        int discountIndexCustomView = discountIndex;
        for (int i = discountIndexCustomView + 1; i < discountListForContract.size(); i++) {
            discountIndexCustomView = i;
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
        return discountIndexCustomView;
    }
    
    private void setActualProjectionValue(DiscountProjectionResultsDTO dto, Object[] obj, String column, final ProjectionSelectionDTO projdto) {
        if (String.valueOf(obj[NumericConstants.NINE]).equals("0")) {
            dto.addStringProperties(column + ACTUALRATE.getConstant(), PERCENT_FORMAT_EXCEL.format(getvalue(obj[NumericConstants.THREE])) + PERCENT.getConstant());
            dto.addStringProperties(column + ACTUALAMOUNT.getConstant(), DOLLAR_RPU_FORMAT_EXCEL.format(getvalue(obj[NumericConstants.FOUR])));
            dto.addStringProperties(column + ACTUALRPU.getConstant(), DOLLAR_RPU_FORMAT_EXCEL.format(getvalue(obj[NumericConstants.FIVE])));
            if (String.valueOf(projdto.getView()).equals("Custom")) {
                dto.addStringProperties(column + ACTUALEXFACTORY.getConstant(), PERCENTFORMATEXFAC_EXCEL.format(getvalue(obj[NumericConstants.TWELVE])) + PERCENT.getConstant());
            } else {
                dto.addStringProperties(column + ACTUALEXFACTORY.getConstant(), PERCENTFORMATEXFAC_EXCEL.format(getvalue(obj[NumericConstants.ELEVEN])) + PERCENT.getConstant());
            }

        } else {
            dto.addStringProperties(column + PROJECTEDRATE.getConstant(), PERCENT_FORMAT_EXCEL.format(getvalue(obj[NumericConstants.SIX])) + PERCENT.getConstant());
            dto.addStringProperties(column + PROJECTEDAMOUNT.getConstant(), DOLLAR_RPU_FORMAT_EXCEL.format(getvalue(obj[NumericConstants.SEVEN])));
            dto.addStringProperties(column + PROJECTEDRPU.getConstant(), DOLLAR_RPU_FORMAT_EXCEL.format(getvalue(obj[NumericConstants.EIGHT])));
            if (String.valueOf(projdto.getView()).equals("Custom")) {
                dto.addStringProperties(column + PROJECTEDEXFACTORY.getConstant(), PERCENTFORMATEXFAC_EXCEL.format(getvalue(obj[NumericConstants.THIRTEEN])) + PERCENT.getConstant());
            } else {
                dto.addStringProperties(column + PROJECTEDEXFACTORY.getConstant(), PERCENTFORMATEXFAC_EXCEL.format(getvalue(obj[NumericConstants.TWELVE])) + PERCENT.getConstant());
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

    private String getFrequency(String freqExcel, Object[] objectExcel) {
        String columnExcelFreq = StringUtils.EMPTY;
        if (ANNUALLY.getConstant().equals(freqExcel)) {
            columnExcelFreq = String.valueOf(objectExcel[1]);
        } else if (QUARTERLY.getConstant().equals(freqExcel)) {
            columnExcelFreq = Constant.Q_SMALL + String.valueOf(objectExcel[NumericConstants.TWO]) + String.valueOf(objectExcel[1]);
        } else if (SEMI_ANNUALLY.getConstant().equals(freqExcel)) {
            columnExcelFreq = Constant.S_SMALL + String.valueOf(objectExcel[NumericConstants.TWO]) + String.valueOf(objectExcel[1]);
        } else if (MONTHLY.getConstant().equals(freqExcel)) {
            String monthName = getMonthForInt(Integer.parseInt(String.valueOf(objectExcel[NumericConstants.TWO])) - 1);
            columnExcelFreq = monthName.toLowerCase(Locale.ENGLISH) + String.valueOf(objectExcel[1]);
        }
        return columnExcelFreq;
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
    
     private String getFrequencyForGroup(String frequencyGroup, Object[] object) {
        String columnFrequencyGroup = StringUtils.EMPTY;
        if (ANNUALLY.getConstant().equals(frequencyGroup)) {
            columnFrequencyGroup = String.valueOf(object[1]);
        } else if (QUARTERLY.getConstant().equals(frequencyGroup)) {
            columnFrequencyGroup = Constant.Q + String.valueOf(object[NumericConstants.TWO]) + Constants.CommonConstants.SPACE + String.valueOf(object[1]);
        } else if (SEMI_ANNUALLY.getConstant().equals(frequencyGroup)) {
            columnFrequencyGroup = Constant.S + String.valueOf(object[NumericConstants.TWO]) + Constants.CommonConstants.SPACE + String.valueOf(object[1]);
        } else if (MONTHLY.getConstant().equals(frequencyGroup)) {
            String monthName = getMonthForInt(Integer.parseInt(String.valueOf(object[NumericConstants.TWO])) - 1);
            columnFrequencyGroup = monthName + Constants.CommonConstants.SPACE + String.valueOf(object[1]);
        }
        return columnFrequencyGroup;
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
        int discountIndexView = discountIndex;
        for (int i = discountIndexView + 1; i < discountListForContract.size(); i++) {
            discountIndexView = i;
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
        return discountIndexView;
    }
}
