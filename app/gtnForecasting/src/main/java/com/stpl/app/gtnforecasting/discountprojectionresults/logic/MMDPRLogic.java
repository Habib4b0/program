/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.discountprojectionresults.logic;

import com.stpl.app.gtnforecasting.dao.SalesProjectionDAO;
import com.stpl.app.gtnforecasting.dao.impl.SalesProjectionDAOImpl;
import com.stpl.app.gtnforecasting.discountprojectionresults.dto.DiscountProjectionResultsDTO;
import com.stpl.app.gtnforecasting.discountprojectionresults.utils.DPRQueryUtils;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.ANNUALLY;
import static com.stpl.app.gtnforecasting.utils.Constant.LabelConstants.PERIOD;
import static com.stpl.app.utils.Constants.CommonConstants.ACTION_VIEW;
import static com.stpl.app.utils.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUALLY;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.math.BigDecimal;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author gopinath
 */
public class MMDPRLogic {

    private static final DecimalFormat DOLLAR = new DecimalFormat("#");
    private static final DecimalFormat UNITVOLUME = new DecimalFormat("0.000");
    private final String ACTUALSRATE = "ActualsRate";
    private final String ACTUALSAMOUNT = "ActualsAmount";
    private final String PROJECTIONSRATE = "ProjectionsRate";
    private final String PROJECTIONSAMOUNT = "ProjectionsAmount";
    private final String ACTUALRPU = "ActualsRPU";
    private final String PROJECTEDRPU = "ProjectedRPU";
    private final String NULL = "null";
    private final String HYPHEN = "-";
    private final String Q = "q";
    private final String PERCENTAGE = Constant.PERCENT;
    private final String DOLLAR_SYMBOL = "$";
    private static final String CURRENCY = "$";
    private final DPRQueryUtils dqLogic = new DPRQueryUtils();
    
    private Object[] dprOrderedArgs;
    private final List<Object[]> totalPrcResultList=new ArrayList<>();
    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = Logger.getLogger(MMDPRLogic.class);

    private String groupName = StringUtils.EMPTY;
    private String pivotGroupName = StringUtils.EMPTY;
    private String pivotBrandName = StringUtils.EMPTY;
    private String nmSupp_Level = StringUtils.EMPTY;
    public static final SimpleDateFormat FORMATDATE = new SimpleDateFormat(Constant.DATE_FORMAT);
    private final HashMap<String, String> map = new HashMap<>();
    private boolean viewFlag = false;

    public List<DiscountProjectionResultsDTO> getConfiguredMMDicountResults(Object parentId, int start, int offset, ProjectionSelectionDTO projSelDTO) {
         viewFlag = ACTION_VIEW.getConstant().equalsIgnoreCase(projSelDTO.getSessionDTO().getAction());
        List<DiscountProjectionResultsDTO> resultList;
        projSelDTO.setYear(Constant.ALL);
        projSelDTO.setIsProjectionTotal(false);
        if (projSelDTO.getActualsOrProjections().equals(Constant.BOTH)) {
            projSelDTO.setActualsOrProjections("Actuals and Projections");
        }
        if (ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
            projSelDTO.setFrequencyDivision(1);
        } else if (Constant.SEMIANNUALLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
            projSelDTO.setFrequencyDivision(NumericConstants.TWO);
        } else if (Constant.QUARTERLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
            projSelDTO.setFrequencyDivision(NumericConstants.FOUR);
        } else if (Constant.MONTHLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
            projSelDTO.setFrequencyDivision(NumericConstants.TWELVE);
        }
        if (parentId instanceof DiscountProjectionResultsDTO) {

            DiscountProjectionResultsDTO parentDto = (DiscountProjectionResultsDTO) parentId;
            if (parentDto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                projSelDTO.setCustomerHierarchyNo(projSelDTO.getHierarchyNo());
                projSelDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
            } else if (parentDto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                projSelDTO.setProductHierarchyNo(projSelDTO.getHierarchyNo());
                projSelDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
            }
            if (parentDto.getLevelValue().equals(Constant.MANDATED_DISCOUNT)) {
                if (parentDto.getPivotView().equals(Constant.DISCOUNT_SMALL)) {

                }
                projSelDTO.setLevelNo(NumericConstants.FOUR);
                projSelDTO.setHierarchyIndicator(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
                projSelDTO.setView(Constant.CUSTOMER_SMALL);
                projSelDTO.setIsTotal(true);
                projSelDTO.setIsFilter(true);
                projSelDTO.setCheckFlag(false);
                projSelDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
                projSelDTO.setLevelValue(parentDto.getLevelValue());
                projSelDTO.setParentNode(parentDto.getParentNode());
                projSelDTO.setHierarchyNo(parentDto.getHierarchyNo());
                projSelDTO.setGroup(parentDto.getGroup());
                projSelDTO.setCurrentLevel(parentDto.getCurrentLevel());
                projSelDTO.setDiscountLevel(parentDto.getDiscountLevel());

            } else if (parentDto.getLevelValue().equals(Constant.NON_MANDATED_SUPPLEMENTAL)) {
                projSelDTO.setNmSuppLevel(StringUtils.EMPTY);
                if (parentDto.getPivotView().equals(Constant.DISCOUNT_SMALL)) {
                    projSelDTO.setIsFilter(true);
                    projSelDTO.setIsTotal(true);
                    projSelDTO.setGroup(parentDto.getGroup());
                    projSelDTO.setDiscountLevel(parentDto.getDiscountLevel());
                    projSelDTO.setNmSuppLevel(StringUtils.EMPTY);
                } else {
                    projSelDTO.setGroup(parentDto.getGroup());
                    projSelDTO.setLevelValue(parentDto.getLevelValue());
                    projSelDTO.setHierarchyNo(parentDto.getHierarchyNo());
                    projSelDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
                    projSelDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                    projSelDTO.setLevelNo(0);
                    projSelDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
                    projSelDTO.setIsFilter(true);
                    projSelDTO.setIsTotal(true);
                    projSelDTO.setDiscountLevel(parentDto.getDiscountLevel());
                }

            } else if (parentDto.getLevelValue().equals(Constant.MANDATED_SUPPLEMENTAL)) {
                projSelDTO.setLevelNo(NumericConstants.FOUR);
                projSelDTO.setHierarchyIndicator(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
                projSelDTO.setView(Constant.CUSTOMER_SMALL);
                projSelDTO.setIsTotal(true);
                projSelDTO.setIsFilter(true);
                projSelDTO.setCheckFlag(true);
                projSelDTO.setSuppFlag(true);
                projSelDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
                projSelDTO.setLevelValue(parentDto.getLevelValue());
                projSelDTO.setParentNode(parentDto.getParentNode());
                projSelDTO.setHierarchyNo(parentDto.getHierarchyNo());
                projSelDTO.setGroup(parentDto.getGroup());
                projSelDTO.setCurrentLevel(parentDto.getCurrentLevel());
                projSelDTO.setDiscountLevel(parentDto.getDiscountLevel());

            } else if (projSelDTO.getPivotView().equals(Constant.DISCOUNT_SMALL)) {
                projSelDTO.setCurrentCustomer(parentDto.getCurrentCustomer());
                projSelDTO.setCurrentContract(parentDto.getCurrentContract());
                projSelDTO.setCurrentBrand(parentDto.getCurrentBrand());
                projSelDTO.setCurrentLevel(parentDto.getCurrentLevel());
                projSelDTO.setLevelNo(NumericConstants.FOUR);
                projSelDTO.setManFlag(true);
                projSelDTO.setIsTotal(true);
                projSelDTO.setIsFilter(true);
                projSelDTO.setGroup(parentDto.getGroup());
                if (projSelDTO.isCountFlag()) {

                    projSelDTO.setLevelNo(parentDto.getLevelNo() + 1);
                    projSelDTO.setHierarchyIndicator(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
                    projSelDTO.setView(Constant.CUSTOMER_SMALL);
                    projSelDTO.setCountFlag(false);
                }
                projSelDTO.setDiscountLevel(parentDto.getDiscountLevel());
                projSelDTO.setSupplementalLevelName(parentDto.getSupplementalLevelName());
                projSelDTO.setPivotValue(parentDto.getPivotValue());
                projSelDTO.setNextFlag(parentDto.getNextFlag());

                projSelDTO.setNmSuppLevel(parentDto.getNmSuppLevel());
            } else {
                projSelDTO.setCurrentLevel(parentDto.getCurrentLevel());
                projSelDTO.setCurrentCustomer(parentDto.getCurrentCustomer());
                projSelDTO.setCurrentContract(parentDto.getCurrentContract());
                projSelDTO.setCurrentBrand(parentDto.getCurrentBrand());
                projSelDTO.setDiscountLevel(parentDto.getDiscountLevel());

                projSelDTO.setGroup(parentDto.getGroup());
                projSelDTO.setLevelNo(parentDto.getLevelNo() + 1);
                projSelDTO.setHierarchyIndicator(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
                projSelDTO.setView(Constant.CUSTOMER_SMALL);
                projSelDTO.setIsTotal(true);
                projSelDTO.setIsFilter(true);
                if (projSelDTO.isCheckFlag()) {
                    projSelDTO.setSuppFlag(true);
                    projSelDTO.setCheckFlag(true);
                }
            }
            if (parentDto.getLevelNo() == NumericConstants.FIVE) {
                projSelDTO.setHierarchyIndicator(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
                projSelDTO.setView(Constant.PRODUCT_LABEL);
                projSelDTO.setIsTotal(true);
                projSelDTO.setIsFilter(true);
                projSelDTO.setLevelNo(NumericConstants.THREE);
                projSelDTO.setParent(0);
                if (projSelDTO.isCheckFlag()) {
                    projSelDTO.setSuppFlag(true);
                    projSelDTO.setCheckFlag(false);
                }
            }

        } else {
            projSelDTO.setIsProjectionTotal(true);
            projSelDTO.setIsTotal(true);

            if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                projSelDTO.setLevelNo(0);
                projSelDTO.setView(Constant.CUSTOMER_SMALL);
            } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                projSelDTO.setLevelNo(0);
            } else {

            }
            projSelDTO.setLevelNo(0);
            projSelDTO.setTreeLevelNo(0);
            String filterValue = !StringUtils.EMPTY.equalsIgnoreCase(projSelDTO.getFilterCustomerDDLB()) && !Constant.NULL.equalsIgnoreCase(projSelDTO.getFilterCustomerDDLB()) ? projSelDTO.getFilterCustomerDDLB() : StringUtils.EMPTY;
            projSelDTO.setGroup(projSelDTO.isFilterDdlb() ? filterValue : StringUtils.EMPTY);
            projSelDTO.setHierarchyNo(StringUtils.EMPTY);
            projSelDTO.setProductHierarchyNo(StringUtils.EMPTY);
            projSelDTO.setCustomerHierarchyNo(StringUtils.EMPTY);

            if (projSelDTO.getPivotView().equalsIgnoreCase(Constant.DISCOUNT_SMALL)) {
                projSelDTO.setLevelNo(NumericConstants.FOUR);
                if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(NumericConstants.FOUR);
                    projSelDTO.setView(Constant.CUSTOMER_SMALL);
                } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(NumericConstants.FIVE);
                }

            }
            if (projSelDTO.isFilterDdlb()) {
                projSelDTO.setSupplementalLevelName(StringUtils.EMPTY);
            }
        }

        if ((projSelDTO.getPivotView().equalsIgnoreCase(Constant.DISCOUNT_SMALL)) && (projSelDTO.getGroup().equals(Constant.PROJECTION_TOTAL))) {
                projSelDTO.setParent(0);

        }

        resultList = getDiscountProjectionResults(start, offset, projSelDTO);
        List<DiscountProjectionResultsDTO> finalList = new ArrayList<>();
        for (DiscountProjectionResultsDTO mMDiscountProjectionResultsDTO : resultList) {
            if (StringUtils.isNotBlank(mMDiscountProjectionResultsDTO.getGroup())) {
                finalList.add(mMDiscountProjectionResultsDTO);
            }
        }

        return finalList;
    }

    public List<DiscountProjectionResultsDTO> getDiscountProjectionResults(int start, int offset, ProjectionSelectionDTO projSelDTO) {
        int neededRecord = offset;
        int mayBeAdded = 0;
        List<DiscountProjectionResultsDTO> projDTOList = new ArrayList<>();
        List<Integer> yearList = new ArrayList<>();
        projSelDTO.setProjectionHeaderList(CommonUtils.prepareProjectionPeriodList(projSelDTO));
        yearList.add(projSelDTO.getForecastDTO().getHistoryStartYear());
        yearList.add(projSelDTO.getForecastDTO().getHistoryStartMonth());
        yearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
        yearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());

        yearList.add(projSelDTO.getForecastDTO().getForecastStartYear());
        yearList.add(projSelDTO.getForecastDTO().getForecastStartMonth());
        yearList.add(projSelDTO.getForecastDTO().getForecastEndYear());
        yearList.add(projSelDTO.getForecastDTO().getForecastEndMonth());


        if (neededRecord > 0 && projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
            if (projSelDTO.isIsProjectionTotal() && projSelDTO.isIsTotal() && !projSelDTO.isFilterDdlb()) {
                if (start < 1 && neededRecord > 0) {
                    projDTOList.addAll(loadProjectionTotal(projSelDTO));
                    neededRecord--;
                }


                    if ((start < NumericConstants.TWO && neededRecord > 0) && (!Constant.SUPPLEMENTAL.equalsIgnoreCase(projSelDTO.getDiscountLevel()))) {
                        DiscountProjectionResultsDTO dtoV = getMandatedDiscount(projSelDTO);
                        projDTOList.add(dtoV);
                        neededRecord--;
                    }
                if (start < NumericConstants.THREE && neededRecord > 0) {
                    DiscountProjectionResultsDTO mmDto = getNMDiscount(projSelDTO);
                    projDTOList.add(mmDto);
                    neededRecord--;
                    if (Constant.SUPPLEMENTAL.equalsIgnoreCase(projSelDTO.getDiscountLevel())) {

                        DiscountProjectionResultsDTO supDto = getSuplimentalDiscount(projSelDTO);
                        projDTOList.add(supDto);
                        neededRecord--;
                    }
                }

                if (start < NumericConstants.FOUR && neededRecord > 0) {
                    DiscountProjectionResultsDTO supDto = getSuplimentalDiscount(projSelDTO);
                    projDTOList.add(supDto);
                    neededRecord--;

                }

                mayBeAdded += NumericConstants.THREE;

            }

        } else if (neededRecord > 0 && projSelDTO.getPivotView().contains(Constant.DISCOUNT_SMALL)) {
            if ((projSelDTO.getPivotView().equals(Constant.DISCOUNT_SMALL)) && (projSelDTO.isIsProjectionTotal())) {
                    if (projSelDTO.isIsProjectionTotal() && projSelDTO.isIsTotal() && !projSelDTO.isFilterDdlb()) {
                        if (start < 1 && neededRecord > 0) {
                            DiscountProjectionResultsDTO dto = new DiscountProjectionResultsDTO();
                            dto.setLevelValue(Constant.PROJECTION_TOTAL);
                            dto.setGroup(Constant.PROJECTION_TOTAL);
                            dto.setParent(1);
                            projDTOList.add(dto);
                            neededRecord--;
                        }

                        if (start < NumericConstants.TWO && neededRecord > 0) {

                            DiscountProjectionResultsDTO dtoV = getMandatedDiscount(projSelDTO);
                            projDTOList.add(dtoV);
                            neededRecord--;

                        }
                        if (start < NumericConstants.THREE && neededRecord > 0) {
                            DiscountProjectionResultsDTO mmDto = getNMDiscount(projSelDTO);
                            projDTOList.add(mmDto);
                            neededRecord--;

                        }

                        if (start < NumericConstants.FOUR && neededRecord > 0) {

                            DiscountProjectionResultsDTO supDto = getSuplimentalDiscount(projSelDTO);
                            projDTOList.add(supDto);
                            neededRecord--;

                        }
                        mayBeAdded += NumericConstants.THREE;

                    }

                    if (neededRecord > 0) {
                        List<DiscountProjectionResultsDTO> periodList = new ArrayList<>();
                        if (projSelDTO.isIsProjectionTotal()) {
                            try {

                            } catch (Exception ex) {
                              LOGGER.error(ex); 
                            }
                        }

                        try {

                        } catch (Exception ex) {
                            LOGGER.error(ex); 
                        }
                        int mayBeAddedRecord = start - mayBeAdded;
                        if (mayBeAddedRecord < 0) {
                            mayBeAddedRecord = 0;
                        }
                        for (int i = mayBeAddedRecord; i < periodList.size(); i++) {
                            projDTOList.add(periodList.get(i));
                            neededRecord--;
                        }
                        mayBeAdded += periodList.size();
                    }
            }
            if (neededRecord > 0 && projSelDTO.isIsTotal()) {

                int mayBeAddedRecord = start - mayBeAdded;
                if (mayBeAddedRecord < 0) {
                    mayBeAddedRecord = 0;
                }

                List<DiscountProjectionResultsDTO> nextLevelValueList = new ArrayList<>();

                if (projSelDTO.isIsFilter() || projSelDTO.isFilterDdlb()) {

                    nextLevelValueList = configureLevels(mayBeAddedRecord, neededRecord, projSelDTO);

                }

                projDTOList.addAll(nextLevelValueList);

            }

        }

        if (neededRecord > 0 && projSelDTO.isIsTotal() || projSelDTO.isFilterDdlb()) {
            int mayBeAddedRecord = start - mayBeAdded;
            if (mayBeAddedRecord < 0) {

                mayBeAddedRecord = 0;
            }

            List<DiscountProjectionResultsDTO> nextLevelValueList = new ArrayList<>();

            if (projSelDTO.isIsFilter() || projSelDTO.isFilterDdlb()) {

                nextLevelValueList = configureLevels(mayBeAddedRecord, neededRecord, projSelDTO);

            }

            projDTOList.addAll(nextLevelValueList);
        }

        return projDTOList;
    }

    public DiscountProjectionResultsDTO getMandatedDiscount(ProjectionSelectionDTO projSelDTO) {

        DiscountProjectionResultsDTO dtoV = new DiscountProjectionResultsDTO();
        dtoV.setGroup(Constant.MANDATED_DISCOUNT);
        dtoV.setLevelValue(Constant.MANDATED_DISCOUNT);
        dtoV.setLevelNo(projSelDTO.getLevelNo());
        dtoV.setSupplementalLevelName(Constant.CUSTOMER_SMALL);
        dtoV.setParent(1);
        if (projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
            dtoV = getSubTotal(dtoV, projSelDTO);
        } else {
            dtoV.setDiscountLevel(Constant.MANDATED_DISCOUNT);
        }

        return dtoV;
    }

    public DiscountProjectionResultsDTO getNMDiscount(ProjectionSelectionDTO projSelDTO) {
        DiscountProjectionResultsDTO mmDto = new DiscountProjectionResultsDTO();

        if (projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
            mmDto.setGroup(Constant.COMMERCIAL_SUPPLEMENTAL_DISCOUNT);
            mmDto.setLevelValue(Constant.NON_MANDATED_SUPPLEMENTAL);
            mmDto = getNonMandatedTotal(mmDto, projSelDTO);
            mmDto.setSupplementalLevelName(Constant.BRAND_CAPS);
            mmDto.setLevelNo(projSelDTO.getLevelNo());
        } else {
            mmDto.setGroup(Constant.COMMERCIAL_SUPPLEMENTAL_DISCOUNT);
            mmDto.setLevelValue(Constant.NON_MANDATED_SUPPLEMENTAL);
            mmDto.setDiscountLevel(Constant.NON_MANDATED_SUPPLEMENTAL);
            mmDto.setLevelNo(projSelDTO.getLevelNo());
            mmDto.setParent(1);
        }

        mmDto.setParent(1);

        return mmDto;
    }

    public DiscountProjectionResultsDTO getSuplimentalDiscount(ProjectionSelectionDTO projSelDTO) {
        DiscountProjectionResultsDTO supDto = new DiscountProjectionResultsDTO();
        supDto.setGroup(Constant.MANDATED_SUPPLEMENTAL);
        supDto.setLevelValue(Constant.MANDATED_SUPPLEMENTAL);
        supDto.setLevelNo(projSelDTO.getLevelNo());
        supDto.setSupplementalLevelName(Constant.CUSTOMER_SMALL);
        supDto.setParent(1);
        supDto.setMmTotal("supp");
        if (projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
            supDto = getSubTotal(supDto, projSelDTO);
        } else {
            supDto.setDiscountLevel(Constant.MANDATED_SUPPLEMENTAL);
        }

        return supDto;
    }


    public static List<String> getCommonColumnHeader(int frequencyDivision, int year, int period) {
        List<String> common = new ArrayList<>();
        String commonColumn = StringUtils.EMPTY;
        String commonHeader = StringUtils.EMPTY;
        if (frequencyDivision == 1) {
            commonColumn = StringUtils.EMPTY + year;
            commonHeader = StringUtils.EMPTY + year;
        } else if (frequencyDivision == NumericConstants.FOUR) {
            commonColumn = Constant.Q_SMALL + period + StringUtils.EMPTY + year;
            commonHeader = Constant.Q + period + " " + year;
        } else if (frequencyDivision == NumericConstants.TWO) {
            commonColumn = Constant.S_SMALL + period + StringUtils.EMPTY + year;
            commonHeader = Constant.S + period + " " + year;
        } else if (frequencyDivision == NumericConstants.TWELVE) {
            String monthName = getMonthForInt(period - 1);
            commonColumn = monthName.toLowerCase() + year;
            commonHeader = monthName + " " + year;
        }
        common.add(commonColumn);
        common.add(commonHeader);
        return common;
    }

    public static List<String> getCommonColumnHeaderForMMDPRPivot(int frequencyDivision, int year, int period) {
        List<String> common = new ArrayList<>();
        String commonColumn = StringUtils.EMPTY;
        String commonHeader = StringUtils.EMPTY;
        if (frequencyDivision == 1) {
            commonColumn = StringUtils.EMPTY + year;
            commonHeader = StringUtils.EMPTY + year;
        } else if (frequencyDivision == NumericConstants.FOUR) {
            commonColumn = Constant.Q_SMALL + period + StringUtils.EMPTY + year;
            commonHeader = period + Constant.Q + year;
        } else if (frequencyDivision == NumericConstants.TWO) {
            commonColumn = Constant.S_SMALL + period + StringUtils.EMPTY + year;
            commonHeader = period + Constant.S + year;
        } else if (frequencyDivision == NumericConstants.TWELVE) {
            String monthName = getMonthForInt(period - 1);
            commonColumn = monthName.toLowerCase() + year;
            commonHeader = monthName + year;
        }
        common.add(commonColumn);
        common.add(commonHeader);
        return common;
    }

    public List<DiscountProjectionResultsDTO> getProjectionPivotTotal(Object[] orderedArgs, ProjectionSelectionDTO projSelDTO) {
        List<DiscountProjectionResultsDTO> projDTOList;
        List<Object[]> gtsList = CommonLogic.callProcedure(viewFlag? "PRC_PROJ_RESULTS_VIEW":"PRC_PROJECTION_RESULTS", orderedArgs);
        projDTOList = getCustomizedProjectionPivotTotal(gtsList, projSelDTO);

        return projDTOList;
    }

    public List<DiscountProjectionResultsDTO> getCustomizedProjectionPivotTotal(List<Object[]> list, ProjectionSelectionDTO projSelDTO) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<DiscountProjectionResultsDTO> projDTOList = new ArrayList<>();
        List<String> periodList = new ArrayList<>(projSelDTO.getPeriodList());
        int col = NumericConstants.FIVE;
        if (frequencyDivision != 1) {
            col = col + 1;
        }
        for (Object[] row : list) {

            int year = Integer.valueOf(String.valueOf(row[col - 1]));
            int period = Integer.valueOf(String.valueOf(row[NumericConstants.FOUR]));
            List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
            String pcommonColumn = common.get(0);
            String commonHeader = common.get(1);
            if (periodList.contains(pcommonColumn)) {
                periodList.remove(pcommonColumn);
                DiscountProjectionResultsDTO projDTO = new DiscountProjectionResultsDTO();
                projDTO.setLevelValue(commonHeader);

                projDTO.setParent(0);
                projDTO.setProjectionTotal(1);
                projDTOList.add(projDTO);
            }

        }

        for (String ob : periodList) {
            DiscountProjectionResultsDTO projDTO = new DiscountProjectionResultsDTO();
            projDTO.setParent(0);
            projDTO.setProjectionTotal(1);
            projDTO.setLevelValue(projSelDTO.getPeriodListMap().get(ob));
            projDTOList.add(projDTO);
        }
        return projDTOList;
    }

    public List<DiscountProjectionResultsDTO> configureLevels(int start, int offset, ProjectionSelectionDTO projSelDTO) {
        int neededRecord = offset;
        int started = start;
        List<DiscountProjectionResultsDTO> totalDTO = new ArrayList<>();
        List<String> periodList = getProjectionTotalCount(projSelDTO);
        int maxrecord = periodList.size();
        List<DiscountProjectionResultsDTO> resultList = new ArrayList<>();

        List<Integer> yearList = new ArrayList<>();
        yearList.add(projSelDTO.getForecastDTO().getHistoryStartYear());
        yearList.add(projSelDTO.getForecastDTO().getHistoryStartMonth());
        yearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
        yearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());

        yearList.add(projSelDTO.getForecastDTO().getForecastStartYear());
        yearList.add(projSelDTO.getForecastDTO().getForecastStartMonth());
        yearList.add(projSelDTO.getForecastDTO().getForecastEndYear());
        yearList.add(projSelDTO.getForecastDTO().getForecastEndMonth());
        map.put("Jan", Constant.STRING_ONE);
        map.put("Feb", "2");
        map.put("Mar", "3");
        map.put("Apr", "4");
        map.put("May", "5");
        map.put("Jun", "6");
        map.put("Jul", "7");
        map.put("Aug", "8");
        map.put("Sep", "9");
        map.put("Oct", "10");
        map.put("Nov", "11");
        map.put("Dec", "12");
        if (Constant.PROJECTION_TOTAL.equals(projSelDTO.getGroup()) && Constant.DISCOUNT_SMALL.equals(projSelDTO.getPivotView())) {
            resultList = getPivotProjectionTotalDiscount(projSelDTO, start, offset);
        }
        if ((Constant.NON_MANDATED_SUPPLEMENTAL.equals(projSelDTO.getLevelValue()) || Constant.COMMERCIAL_SUPPLEMENTAL_DISCOUNT.equals(projSelDTO.getGroup()) || Constant.NON_MANDATED_SUPPLEMENTAL.equalsIgnoreCase(projSelDTO.getDiscountLevel())) && Constant.NON_MANDATED_SUPPLEMENTAL.equalsIgnoreCase(projSelDTO.getDiscountLevel())) {

            int frequencyDivision;
            if (Constant.QUARTERLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
                frequencyDivision = NumericConstants.FOUR;
            } else if (Constant.MONTHLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
                frequencyDivision = NumericConstants.TWELVE;
            } else if (Constant.SEMIANNUALLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
                frequencyDivision = NumericConstants.TWO;
            } else {
                frequencyDivision = 1;
            }
            if (projSelDTO.getPivotView().equals(Constant.DISCOUNT_SMALL)) {
                if (projSelDTO.getNmSuppLevel().equals("NMLevel")) {
                    List list = dqLogic.getNonMandateTotalValue(projSelDTO.getProjectionId(), projSelDTO.getFrequency(), projSelDTO);
                    String checkYear = StringUtils.EMPTY;
                    if (list.size() != 0) {
                        DiscountProjectionResultsDTO dto = null;
                        for (int i = 0; i < list.size(); i++) {

                            Object[] obj = (Object[]) list.get(i);
                            int year = Integer.valueOf(String.valueOf(obj[1]));
                            int period = Integer.valueOf(String.valueOf(obj[NumericConstants.TWO]));
                            List<String> column = getCommonColumnHeader(frequencyDivision, year, period);
                            String commonColumn = column.get(1);
                            if (i == 0) {
                                pivotBrandName = String.valueOf(obj[0]);
                            }
                            if (!pivotBrandName.equalsIgnoreCase(String.valueOf(obj[0]))) {
                                pivotBrandName = String.valueOf(obj[0]);
                            }
                            if (checkYear.isEmpty() || !checkYear.equalsIgnoreCase(commonColumn)) {

                                if (dto != null) {
                                    customizeNonMandat_Brand(list, dto, projSelDTO, checkYear);
                                    if (!dto.getGroup().isEmpty() && !Constant.NULL.equals(dto.getGroup())) {
                                        dto.setDiscountLevel(Constant.NON_MANDATED_SUPPLEMENTAL);
                                        resultList.add(dto);
                                    }
                                }
                                dto = new DiscountProjectionResultsDTO();
                                dto.setParent(0);
                                checkYear = commonColumn;
                            }

                            if (i == list.size() - 1) {
                                customizeNonMandat_Brand(list, dto, projSelDTO, checkYear);
                                if (!dto.getGroup().isEmpty() && !Constant.NULL.equals(dto.getGroup())) {
                                    dto.setDiscountLevel(Constant.NON_MANDATED_SUPPLEMENTAL);
                                    resultList.add(dto);
                                }
                            }

                        }
                    }
                } else if (projSelDTO.getGroup().equals(Constant.COMMERCIAL_SUPPLEMENTAL_DISCOUNT)) {
                    List list = dqLogic.getSumNMPivotValue(projSelDTO.getProjectionId(), projSelDTO.getFrequency(), projSelDTO);
                    String checkYear = StringUtils.EMPTY;
                    if (list.size() != 0) {
                        DiscountProjectionResultsDTO dto = null;
                        for (int i = 0; i < list.size(); i++) {
                            Object[] obj = (Object[]) list.get(i);
                            int year = Integer.valueOf(String.valueOf(obj[0]));
                            int period = Integer.valueOf(String.valueOf(obj[1]));
                            List<String> column = getCommonColumnHeader(frequencyDivision, year, period);
                            String commonColumn = column.get(1);
                            if (periodList.contains(column.get(1))) {
                                periodList.remove(column.get(1));
                                if (checkYear.isEmpty() || !checkYear.equalsIgnoreCase(commonColumn)) {
                                    if (dto != null) {

                                        customizeNonMandat(list, dto, projSelDTO, checkYear);
                                        dto.setSupplementalLevelName(Constant.NON_MANDATED_SUPPLEMENTAL);
                                        dto.setDiscountLevel(Constant.NON_MANDATED_SUPPLEMENTAL);
                                        resultList.add(dto);

                                    }
                                    dto = new DiscountProjectionResultsDTO();
                                    dto.setParent(0);
                                    checkYear = commonColumn;
                                }
                                if (i == list.size() - 1) {
                                    customizeNonMandat(list, dto, projSelDTO, checkYear);
                                    dto.setSupplementalLevelName(Constant.NON_MANDATED_SUPPLEMENTAL);
                                    dto.setDiscountLevel(Constant.NON_MANDATED_SUPPLEMENTAL);
                                    resultList.add(dto);
                                }
                            }
                        }

                        List<DiscountProjectionResultsDTO> projectionDtoList;
                        projectionDtoList = resultList;

                        if (started < maxrecord) {
                            for (int k = started; k < projectionDtoList.size() && offset > 0; k++) {
                                totalDTO.add(projectionDtoList.get(k));
                                neededRecord--;
                                started++;
                            }
                            resultList.clear();
                            resultList = totalDTO;
                        }

                    }

                }

            } else if (Constant.NON_MANDATED_SUPPLEMENTAL.equalsIgnoreCase(projSelDTO.getDiscountLevel())) {
                List<DiscountProjectionResultsDTO> discountDTO;
                projSelDTO.setStart(start);
                projSelDTO.setOffset(offset);

                List list = dqLogic.getNonMandatedBrand(projSelDTO.getProjectionId(), projSelDTO.getFrequency(), projSelDTO);
                discountDTO = getCustomize_Brand(list, frequencyDivision, projSelDTO);
                resultList.addAll(discountDTO);
            }

        } else if ((!projSelDTO.getGroup().equals(Constant.PROJECTION_TOTAL)) && (neededRecord > 0)) {

                if ((Constant.MANDATED_DISCOUNT.equals(projSelDTO.getGroup()) || Constant.MANDATED_DISCOUNT.equals(projSelDTO.getLevelValue()) || Constant.MANDATED_DISCOUNT.equals(projSelDTO.getDiscountLevel())) && !Constant.DISCOUNT_SMALL.equals(projSelDTO.getPivotView())) {
                    List list = new ArrayList();
                    List<DiscountProjectionResultsDTO> discountDTO;
                    if (projSelDTO.getCurrentLevel().equalsIgnoreCase(Constant.MANDATED_DISCOUNT) || projSelDTO.getFilterCustomerDDLB().contains(Constant.MANDATED_DISCOUNT)) {
                        projSelDTO.setStart(start);
                        projSelDTO.setOffset(offset);
                        list = dqLogic.getLevelValue(projSelDTO);
                        projSelDTO.setFilterLevelValue(StringUtils.EMPTY);
                    } else if (projSelDTO.getCurrentLevel().equalsIgnoreCase(Constant.CONTRACT_SMALL)) {
                        projSelDTO.setStart(start);
                        projSelDTO.setOffset(offset);
                        list = dqLogic.getBrandLevelValue(projSelDTO);
                    } else if (projSelDTO.getCurrentLevel().equalsIgnoreCase(Constant.CUSTOMER_SMALL)) {
                        projSelDTO.setStart(start);
                        projSelDTO.setOffset(offset);
                        list = dqLogic.getContractLevelValue(projSelDTO);
                    }
                    discountDTO = getCurrentLevel(list, projSelDTO, true);

                    resultList.addAll(discountDTO);
                    neededRecord--;
                } else if ((Constant.MANDATED_SUPPLEMENTAL.equals(projSelDTO.getGroup())
                        || Constant.MANDATED_SUPPLEMENTAL.equals(projSelDTO.getLevelValue()) || Constant.MANDATED_SUPPLEMENTAL.equals(projSelDTO.getDiscountLevel())) && !Constant.DISCOUNT_SMALL.equals(projSelDTO.getPivotView())) {
                    List list;
                    List<DiscountProjectionResultsDTO> discountDTO;
                    if (projSelDTO.getLevelNo() == NumericConstants.FOUR || projSelDTO.getCurrentLevel().equalsIgnoreCase(Constant.MANDATED_SUPPLEMENTAL) || projSelDTO.getFilterLevelValue().equalsIgnoreCase(Constant.CUSTOMER_SMALL)) {
                        projSelDTO.setStart(start);
                        projSelDTO.setOffset(offset);
                        list = dqLogic.getSuppLevelValue(projSelDTO);
                        projSelDTO.setFilterLevelValue(StringUtils.EMPTY);
                    } else if (projSelDTO.getCurrentLevel().equalsIgnoreCase(Constant.CONTRACT_SMALL)) {
                        projSelDTO.setStart(start);
                        projSelDTO.setOffset(offset);
                        list = dqLogic.getSuppBrandLevelValue(projSelDTO);
                    } else {
                        projSelDTO.setStart(start);
                        projSelDTO.setOffset(offset);
                        list = dqLogic.getSuppContractLevelValue(projSelDTO);
                    }
                    discountDTO = getCurrentLevel(list, projSelDTO, false);
                    resultList.addAll(discountDTO);
                    neededRecord--;

                }

        }
        if (Constant.DISCOUNT_SMALL.equals(projSelDTO.getPivotView()) && (Constant.MANDATED_DISCOUNT.equals(projSelDTO.getGroup()) || Constant.MANDATED_SUPPLEMENTAL.equals(projSelDTO.getGroup())) && !Constant.PROJECTION_TOTAL.equals(projSelDTO.getGroup())) {

            projSelDTO.setIsParent(Constant.STRING_ONE);



            dqLogic.getProjectionDetailsId(projSelDTO);
            List<String> discountList;
            discountList = projSelDTO.getDiscountNameList();
            String discountString = StringUtils.EMPTY;
            for (int i = 0; i < discountList.size(); i++) {
                if (i != discountList.size() - 1) {
                    discountString = discountString.concat("'") + discountList.get(i) + "',";
                } else {
                    discountString = discountString.concat("'") + discountList.get(i) + "'";
                }
            }
            List list;

            if (Constant.MANDATED_DISCOUNT.equals(projSelDTO.getGroup()) || Constant.MANDATED_DISCOUNT.equals(projSelDTO.getLevelValue())) {
                projSelDTO.setStart(start);
                projSelDTO.setOffset(offset);
                list = dqLogic.getMandatedPivotLevel(projSelDTO);

            } else {
                projSelDTO.setStart(start);
                projSelDTO.setOffset(offset);
                list = dqLogic.getSuppPivotLevelValue(projSelDTO);
            }

            int frequencyDivision;
            if (Constant.QUARTERLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
                frequencyDivision = NumericConstants.FOUR;
            } else if (Constant.MONTHLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
                frequencyDivision = NumericConstants.TWELVE;
            } else if (Constant.SEMIANNUALLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
                frequencyDivision = NumericConstants.TWO;
            } else {
                frequencyDivision = 1;
            }
            String checkYear = StringUtils.EMPTY;

            if (list != null) {
                DiscountProjectionResultsDTO dto = null;
                for (int i = 0; i < list.size(); i++) {

                    final Object[] obj = (Object[]) list.get(i);
                    int year = Integer.valueOf(String.valueOf(obj[0]));
                    int period = Integer.valueOf(String.valueOf(obj[1]));
                    List<String> column = getCommonColumnHeader(frequencyDivision, year, period);
                    String commonColumn = column.get(0);
                    projSelDTO.setCurrentLevel(String.valueOf(obj[NumericConstants.SEVEN]));
                    if (periodList.contains(column.get(1))) {
                        periodList.remove(column.get(1));
                        if (checkYear.isEmpty() || !checkYear.equalsIgnoreCase(commonColumn)) {

                            if (dto != null) {
                                customizeManSupp(list, dto, projSelDTO, checkYear);
                                dto.setSupplementalLevelName(Constant.CUSTOMER_SMALL);
                                resultList.add(dto);
                            }
                            dto = new DiscountProjectionResultsDTO();
                            dto.setParent(1);
                            checkYear = commonColumn;
                        }
                        if (i == list.size() - 1) {
                            customizeManSupp(list, dto, projSelDTO, checkYear);
                            dto.setSupplementalLevelName(Constant.CUSTOMER_SMALL);
                            resultList.add(dto);
                        }
                    }
                }
                List<DiscountProjectionResultsDTO> projectionDtoList;
                projectionDtoList = resultList;
                if (started < maxrecord) {
                    for (int k = started; k < projectionDtoList.size() && offset > 0; k++) {
                        totalDTO.add(projectionDtoList.get(k));
                        neededRecord--;
                        started++;
                    }
                    resultList.clear();
                    resultList = totalDTO;
                }
            }

            projSelDTO.setLastFlag(true);
            projSelDTO.setFilterLevelValue(Constant.CUSTOMER_SMALL);
        } else if ((Constant.DISCOUNT_SMALL.equals(projSelDTO.getPivotView())) && ((Constant.CUSTOMER_SMALL.equalsIgnoreCase(projSelDTO.getCurrentLevel()) || Constant.CONTRACT_SMALL.equalsIgnoreCase(projSelDTO.getCurrentLevel()) || Constant.BRAND_CAPS.equalsIgnoreCase(projSelDTO.getCurrentLevel())) && projSelDTO.getPivotView().equalsIgnoreCase(Constant.DISCOUNT_SMALL))) {
                DiscountProjectionResultsDTO dto = new DiscountProjectionResultsDTO();
                List<DiscountProjectionResultsDTO> pivotDTO;

                projSelDTO.setStart(start);
                projSelDTO.setOffset(offset);
                pivotDTO = getPivotManCus(dto, projSelDTO);
                pivotDTO.add(dto);
                resultList.addAll(pivotDTO);
                projSelDTO.setSupplementalLevelName(StringUtils.EMPTY);

                projSelDTO.setLastFlag(false);
            }
        return resultList;
    }

    public List<DiscountProjectionResultsDTO> getCustomizedProjectionPivot(List<Object> list,ProjectionSelectionDTO projSelDTO) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<DiscountProjectionResultsDTO> projDTOList = new ArrayList<>();
        List<String> periodList = new ArrayList<>(projSelDTO.getPeriodList());
        for (Object rows : list) {
            final Object[] row = (Object[]) rows;
            int year = Integer.valueOf(String.valueOf(row[0]));
            int period = Integer.valueOf(String.valueOf(row[1]));
            List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
            String pcommonColumn = common.get(0);
            String commonHeader = common.get(1);
            if (periodList.contains(pcommonColumn)) {
                periodList.remove(pcommonColumn);
                DiscountProjectionResultsDTO projDTO = new DiscountProjectionResultsDTO();
                projDTO.setLevelValue(commonHeader);

                projDTO.setParent(0);
                projDTO.setProjectionTotal(1);
                projDTOList.add(projDTO);
            }
        }
        for (String ob : periodList) {
            DiscountProjectionResultsDTO projDTO = new DiscountProjectionResultsDTO();
            projDTO.setParent(0);
            projDTO.setProjectionTotal(1);
            projDTO.setLevelValue(projSelDTO.getPeriodListMap().get(ob));
            projDTOList.add(projDTO);
        }
        return projDTOList;
    }

    public int getConfiguredMMDiscountResultsCount(Object parentId, ProjectionSelectionDTO projSelDTO) {
        int count = 0;
        projSelDTO.setYear(Constant.ALL);

        if (projSelDTO.getActualsOrProjections().equals(Constant.BOTH)) {
            projSelDTO.setActualsOrProjections("Actuals and Projections");
        }
        if (ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
            projSelDTO.setFrequencyDivision(1);
        } else if (Constant.SEMIANNUALLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
            projSelDTO.setFrequencyDivision(NumericConstants.TWO);
        } else if (Constant.QUARTERLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
            projSelDTO.setFrequencyDivision(NumericConstants.FOUR);
        } else if (Constant.MONTHLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
            projSelDTO.setFrequencyDivision(NumericConstants.TWELVE);
        }

        if (parentId instanceof DiscountProjectionResultsDTO) {

            DiscountProjectionResultsDTO parentDto = (DiscountProjectionResultsDTO) parentId;
            if (projSelDTO.getPivotView().equals(Constant.DISCOUNT_SMALL)) {
                /*
                 * This code will be used for pivot view count
                 */

                if (Constant.PROJECTION_TOTAL.equals(parentDto.getLevelValue()) || Constant.MANDATED_DISCOUNT.equals(parentDto.getLevelValue())
                        || Constant.NON_MANDATED_SUPPLEMENTAL.equals(parentDto.getLevelValue()) || Constant.MANDATED_SUPPLEMENTAL.equals(parentDto.getLevelValue())) {
                    List<String> periods = getProjectionTotalCount(projSelDTO);
                    count += periods.size();
                } else if (Constant.NON_MANDATED_SUPPLEMENTAL.equals(parentDto.getSupplementalLevelName())) {
                    count += dqLogic.getCount(parentDto, projSelDTO, false);
                } else if (projSelDTO.isFilterDdlb() && projSelDTO.getPivotView().equals(Constant.DISCOUNT_SMALL)) {
                    count += 1;
                } else {
                    count += dqLogic.getCount(parentDto, projSelDTO, true);
                }

            } else {
                if (Constant.PROJECTION_TOTAL.equals(parentDto.getGroup())) {
                    count += 1;
                }

                if (Constant.COMMERCIAL_SUPPLEMENTAL_DISCOUNT.equals(parentDto.getGroup())) {
                    count += dqLogic.getCount(parentDto, projSelDTO, false);
                } else {

                    count += dqLogic.getCount(parentDto, projSelDTO, true);
                }

            }

        } else if (projSelDTO.isFilterDdlb() && projSelDTO.getPivotView().equals(Constant.DISCOUNT_SMALL)) {
            count += projSelDTO.getPeriodList().size();
        } else {
            if (CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equalsIgnoreCase(projSelDTO.getDiscountValue())) {
                count += NumericConstants.THREE;
            } else if (Constant.SUPPLEMENTAL.equalsIgnoreCase(projSelDTO.getDiscountValue())) {
                count += NumericConstants.THREE;
            } else if (Constant.BOTH.equalsIgnoreCase(projSelDTO.getDiscountValue())) {
                count += NumericConstants.FOUR;
            }
            if (projSelDTO.isFilterDdlb()) {
                count += 1;
            }
        }

        return count;
    }

    public List<DiscountProjectionResultsDTO> loadProjectionTotal(ProjectionSelectionDTO projSel) {
        List<DiscountProjectionResultsDTO> projDTOList = new ArrayList<>();

        String userId = String.valueOf(projSel.getUserId());
        String sessionId = projSel.getSessionDTO().getSessionId();
        String managed_Medicaid = Constant.MANAGED_MEDICAID;
        String quarterly = projSel.getFrequency();
        String frequency = StringUtils.EMPTY;
        Object object1 = null;
        Object object0 = null;
        Integer historyStartMonth = projSel.getForecastDTO().getHistoryStartMonth();
        Integer historyStartYear = projSel.getForecastDTO().getHistoryStartYear();
        int ProjectioId = Integer.valueOf(projSel.getProjectionId());
        if (Constant.QUARTERLY.equalsIgnoreCase(quarterly)) {
            frequency = Constant.QUARTERLY;
        } else if (SEMI_ANNUALLY.getConstant().equalsIgnoreCase(quarterly)) {
            frequency = "SEMI-ANNUALLY";
        } else if (ANNUALLY.equalsIgnoreCase(quarterly)) {
            frequency = "ANNUALLY";
        } else if (MONTHLY.getConstant().equalsIgnoreCase(quarterly)) {
            frequency = MONTHLY.getConstant();
        }
        Object[] orderedArgs = {ProjectioId, userId, sessionId, managed_Medicaid, frequency, object1, object0, historyStartMonth, historyStartYear};
         List<Object[]> list;
         // Procedure called only in  Tab Change
        if (projSel.getSessionDTO().isDprRefreshReqd() ||  !CommonLogic.checkProcedureInputIsSame(orderedArgs, dprOrderedArgs)) {
            if(viewFlag){
                orderedArgs=ArrayUtils.removeElement(orderedArgs, userId);
                orderedArgs=ArrayUtils.removeElement(orderedArgs, sessionId); 
            }
            list =  CommonLogic.callProcedure(viewFlag? "PRC_M_DISCOUNT_PROJ_TOTAL_VIEW" :"PRC_M_DISCOUNT_PROJ_TOTAL", orderedArgs);
            dprOrderedArgs = new Object[orderedArgs.length];
            System.arraycopy(orderedArgs, 0, dprOrderedArgs, 0, orderedArgs.length);
            totalPrcResultList.clear();
            totalPrcResultList.addAll(list);
        }else{
            list=totalPrcResultList;
        }
        projSel.getSessionDTO().setDprRefreshReqd(false);
        int frequencyDivision;
        if (Constant.QUARTERLY_SMALL.equalsIgnoreCase(projSel.getFrequency())) {
            frequencyDivision = NumericConstants.FOUR;
        } else if (Constant.MONTHLY_SMALL.equalsIgnoreCase(projSel.getFrequency())) {
            frequencyDivision = NumericConstants.TWELVE;
        } else if (Constant.SEMIANNUALLY_SMALL.equalsIgnoreCase(projSel.getFrequency())) {
            frequencyDivision = NumericConstants.TWO;
        } else {
            frequencyDivision = 1;
        }
        DiscountProjectionResultsDTO dto = new DiscountProjectionResultsDTO();
        if (list != null) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String falgValue;
                if (ANNUALLY.equalsIgnoreCase(projSel.getFrequency())) {
                    falgValue = String.valueOf(obj[NumericConstants.THREE]);
                } else {
                    falgValue = String.valueOf(obj[NumericConstants.FOUR]);
                }

                if (Constant.ACTUAL_CAPS.equalsIgnoreCase(falgValue)) {
                    int period;
                    int year;
                    String actualsRPU;
                    if (ANNUALLY.equalsIgnoreCase(projSel.getFrequency())) {
                        period = 0;
                        year = Integer.valueOf(String.valueOf(obj[NumericConstants.TWO]));
                        actualsRPU = String.valueOf(obj[NumericConstants.FOUR]);
                    } else {
                        period = Integer.valueOf(String.valueOf(obj[NumericConstants.TWO]));
                        year = Integer.valueOf(String.valueOf(obj[NumericConstants.THREE]));
                        actualsRPU = String.valueOf(obj[NumericConstants.FIVE]);
                    }

                    List<String> column = getCommonColumnHeader(frequencyDivision, year, period);
                    String commonColumn = column.get(0);
                    String actualsAmount = String.valueOf(obj[0]);
                    String actualsRate = String.valueOf(obj[1]);

                    actualsAmount = getFormatValue(NumericConstants.TWO, actualsAmount, CURRENCY);
                    actualsRate = getFormatValue(NumericConstants.TWO, actualsRate, PERCENTAGE);
                    actualsRPU = getFormatValue(NumericConstants.TWO, actualsRPU, CURRENCY);
                    dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualsAmount);
                    dto.addStringProperties(commonColumn + ACTUALSRATE, actualsRate);
                    dto.addStringProperties(commonColumn + ACTUALRPU, actualsRPU);
                } else {
                    int period;
                    int year;
                    String projRPU;
                    if (ANNUALLY.equalsIgnoreCase(projSel.getFrequency())) {
                        period = 0;
                        year = Integer.valueOf(String.valueOf(obj[NumericConstants.TWO]));
                        projRPU = String.valueOf(obj[NumericConstants.FOUR]);
                    } else {
                        period = Integer.valueOf(String.valueOf(obj[NumericConstants.TWO]));
                        year = Integer.valueOf(String.valueOf(obj[NumericConstants.THREE]));
                        projRPU = String.valueOf(obj[NumericConstants.FIVE]);
                    }
                    List<String> column = getCommonColumnHeader(frequencyDivision, year, period);
                    String commonColumn = column.get(0);
                    String projAmount = String.valueOf(obj[0]);
                    String projRate = String.valueOf(obj[1]);

                    projAmount = getFormatValue(NumericConstants.TWO, projAmount, CURRENCY);
                    projRate = getFormatValue(NumericConstants.TWO, projRate, PERCENTAGE);
                    projRPU = getFormatValue(NumericConstants.TWO, projRPU, CURRENCY);
                    dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, projAmount);
                    dto.addStringProperties(commonColumn + PROJECTIONSRATE, projRate);
                    dto.addStringProperties(commonColumn + PROJECTEDRPU, projRPU);

                }

            }
        }
        dto.setLevelValue(Constant.PROJECTION_TOTAL);
        dto.setGroup(Constant.PROJECTION_TOTAL);
        dto.setParent(0);
        projDTOList.add(dto);
        return projDTOList;
    }

    private DiscountProjectionResultsDTO getNonMandatedTotal(DiscountProjectionResultsDTO dto, ProjectionSelectionDTO projSelDTO) {
        try {
            int projectionMasterId = projSelDTO.getProjectionId();
            dto.setIsParent(Constant.STRING_ONE);

            int frequencyDivision;
            if (Constant.QUARTERLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
                frequencyDivision = NumericConstants.FOUR;
            } else if (Constant.MONTHLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
                frequencyDivision = NumericConstants.TWELVE;
            } else if (Constant.SEMIANNUALLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
                frequencyDivision = NumericConstants.TWO;
            } else {
                frequencyDivision = 1;
            }

            List list = DPRQueryUtils.getNonMandatedTotal(projectionMasterId,projSelDTO);

            dto.setDiscountLevel(Constant.NON_MANDATED_SUPPLEMENTAL);
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                if (Constant.ACTUAL_CAPS.equalsIgnoreCase(String.valueOf(obj[NumericConstants.THREE]))) {

                    int year = Integer.valueOf(String.valueOf(obj[0]));
                    int period = Integer.valueOf(String.valueOf(obj[1]));
                    List<String> column = getCommonColumnHeader(frequencyDivision, year, period);
                    String commonColumn = column.get(0);
                    String actualsAmount = String.valueOf(obj[NumericConstants.TWO]);
                    String actualsRate = String.valueOf(obj[NumericConstants.FOUR]);
                    String actualsRPU = String.valueOf(obj[NumericConstants.FIVE]);
                    actualsAmount = getFormatValue(NumericConstants.TWO, actualsAmount, CURRENCY);
                    actualsRate = getFormatValue(NumericConstants.TWO, actualsRate, PERCENTAGE);
                    actualsRPU = getFormatValue(NumericConstants.TWO, actualsRPU, CURRENCY);

                    dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualsAmount);
                    dto.addStringProperties(commonColumn + ACTUALSRATE, actualsRate);
                    dto.addStringProperties(commonColumn + ACTUALRPU, actualsRPU);
                } else {
                    int year = Integer.valueOf(String.valueOf(obj[0]));
                    int period = Integer.valueOf(String.valueOf(obj[1]));
                    List<String> column = getCommonColumnHeader(frequencyDivision, year, period);
                    String commonColumn = column.get(0);
                    String projAmount = String.valueOf(obj[NumericConstants.TWO]);
                    String projRate = String.valueOf(obj[NumericConstants.FOUR]);
                    String projRPU = String.valueOf(obj[NumericConstants.FIVE]);
                    projAmount = getFormatValue(NumericConstants.TWO, projAmount, CURRENCY);
                    projRate = getFormatValue(NumericConstants.TWO, projRate, PERCENTAGE);
                    projRPU = getFormatValue(NumericConstants.TWO, projRPU, CURRENCY);
                    dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, projAmount);
                    dto.addStringProperties(commonColumn + PROJECTIONSRATE, projRate);
                    dto.addStringProperties(commonColumn + PROJECTEDRPU, projRPU);
                }

            }

        } catch (NumberFormatException e) {
            LOGGER.error(e);
        }

        return dto;
    }

    public DiscountProjectionResultsDTO getMandatedSupp(DiscountProjectionResultsDTO dto, ProjectionSelectionDTO projSelDTO) {

        try {
            dto.setIsParent(Constant.STRING_ONE);

            List<Integer> yearList = new ArrayList<>();
            yearList.add(projSelDTO.getForecastDTO().getHistoryStartYear());
            yearList.add(projSelDTO.getForecastDTO().getHistoryStartMonth());
            yearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
            yearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());

            yearList.add(projSelDTO.getForecastDTO().getForecastStartYear());
            yearList.add(projSelDTO.getForecastDTO().getForecastStartMonth());
            yearList.add(projSelDTO.getForecastDTO().getForecastEndYear());
            yearList.add(projSelDTO.getForecastDTO().getForecastEndMonth());


            List<Integer> projectionDet = dqLogic.getProjectionDetailsId(projSelDTO);

            if (projectionDet != null && !projectionDet.isEmpty()) {

                String frequency = projSelDTO.getFrequency();
                List<String> discountList;
                discountList = projSelDTO.getDiscountNameList();
                String discountString = StringUtils.EMPTY;
                for (int i = 0; i < discountList.size(); i++) {
                    if (i != discountList.size() - 1) {
                        discountString = discountString.concat("'") + discountList.get(i) + "',";
                    } else {
                        discountString = discountString.concat("'") + discountList.get(i) + "'";
                    }
                }
                if (discountString.equals(StringUtils.EMPTY)) {
                    discountString = Constant.DASH;
                }
                List list;
                if (dto.getGroup().equals(Constant.MANDATED_DISCOUNT) || dto.getLevelValue().equals(Constant.MANDATED_DISCOUNT)) {

                    list = dqLogic.getDiscountProjectionResults(projectionDet, frequency, Constant.PARENT, yearList, projSelDTO);

                } else {

                    list = dqLogic.getMandatedSupp2(projectionDet, frequency, yearList, projSelDTO);
                }

                if (list != null && !list.isEmpty()) {

                    if (frequency.equals(Constant.QUARTERLY)) {
                        double actualSales = 0;
                        double actualAmount = 0;
                        double projectedSales = 0;
                        double projectedAmount = 0;
                        int year = 0;
                        int Quarter = 0;
                        String commonColumn = StringUtils.EMPTY;
                        for (int j = 0; j < list.size(); j++) {
                            final Object[] object = (Object[]) list.get(j);
                            int selectedYear = 0;
                            int selectedQuarter = 0;
                            if (object[0] != null) {
                                selectedYear = (Integer) object[0];
                            }
                            if (object[NumericConstants.SIX] != null) {
                                selectedQuarter = (Integer) object[NumericConstants.SIX];
                            }
                            if (year == selectedYear && Quarter == selectedQuarter) {
                                if (object[NumericConstants.TWO] != null) {
                                    Double aSales = Double.parseDouble(String.valueOf(object[NumericConstants.TWO]));
                                    actualSales = actualSales + aSales;
                                }
                                if (object[NumericConstants.THREE] != null) {
                                    Double aAmount = Double.parseDouble(String.valueOf(object[NumericConstants.THREE]));
                                    actualAmount = actualAmount + aAmount;
                                }
                                if (object[NumericConstants.FOUR] != null) {
                                    Double pSales = Double.parseDouble(String.valueOf(object[NumericConstants.FOUR]));
                                    projectedSales = projectedSales + pSales;
                                }
                                if (object[NumericConstants.FIVE] != null) {
                                    Double pAmount = Double.parseDouble(String.valueOf(object[NumericConstants.FIVE]));
                                    projectedAmount = projectedAmount + pAmount;
                                }

                            } else if (j == 0) {
                                year = selectedYear;
                                Quarter = selectedQuarter;
                                commonColumn = Q + object[NumericConstants.SIX] + object[0];
                                if (object[NumericConstants.TWO] != null) {
                                    Double aSales = Double.parseDouble(String.valueOf(object[NumericConstants.TWO]));
                                    actualSales = actualSales + aSales;
                                }
                                if (object[NumericConstants.THREE] != null) {
                                    Double aAmount = Double.parseDouble(String.valueOf(object[NumericConstants.THREE]));
                                    actualAmount = actualAmount + aAmount;
                                }

                                if (object[NumericConstants.FOUR] != null) {
                                    Double pSales = Double.parseDouble(String.valueOf(object[NumericConstants.FOUR]));
                                    projectedSales = projectedSales + pSales;
                                }
                                if (object[NumericConstants.FIVE] != null) {
                                    Double pAmount = Double.parseDouble(String.valueOf(object[NumericConstants.FIVE]));
                                    projectedAmount = projectedAmount + pAmount;
                                }

                            } else {
                                Double arate = actualAmount / actualSales;
                                if (arate.isNaN()) {
                                    arate = 0.0;
                                }
                                arate = arate * NumericConstants.HUNDRED;
                                String actualsRate = String.valueOf(arate);
                                dto.addStringProperties(commonColumn + ACTUALSRATE, actualsRate != null && !NULL.equals(String.valueOf(actualsRate)) && !StringUtils.EMPTY.equals(String.valueOf(actualsRate)) ? getFormatValue(NumericConstants.TWO, actualsRate, PERCENTAGE) : HYPHEN);
                                Double actualAmt = arate * actualSales;
                                actualAmt = actualAmt / NumericConstants.HUNDRED;
                                if (actualAmt.isNaN()) {
                                    actualAmt = 0.0;
                                }
                                String actualsAmount = String.valueOf(actualAmt);
                                dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualsAmount != null && !NULL.equals(String.valueOf(actualsAmount)) && !StringUtils.EMPTY.equals(String.valueOf(actualsAmount)) ? getFormatValue(NumericConstants.TWO, actualsAmount, DOLLAR_SYMBOL) : HYPHEN);
                                Double prate = projectedAmount / projectedSales;
                                if (prate.isNaN()) {
                                    prate = 0.0;
                                }
                                prate = prate * NumericConstants.HUNDRED;
                                String prorate = String.valueOf(prate);
                                dto.addStringProperties(commonColumn + PROJECTIONSRATE, prorate != null && !NULL.equals(String.valueOf(prorate)) && !StringUtils.EMPTY.equals(String.valueOf(prorate)) ? getFormatValue(NumericConstants.TWO, prorate, PERCENTAGE) : HYPHEN);
                                Double projectedAmtAmt = prate * projectedSales;
                                projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                if (projectedAmtAmt.isNaN()) {
                                    projectedAmtAmt = 0.0;
                                }
                                String proAmount = String.valueOf(projectedAmtAmt);
                                dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? getFormatValue(NumericConstants.TWO, proAmount, DOLLAR_SYMBOL) : HYPHEN);
                                actualSales = 0;
                                actualAmount = 0;
                                projectedSales = 0;
                                projectedAmount = 0;
                                commonColumn = Q + object[NumericConstants.SIX] + object[0];
                                year = (Integer) object[0];
                                Quarter = (Integer) object[NumericConstants.SIX];
                                if (object[NumericConstants.TWO] != null) {
                                    Double aSales = Double.parseDouble(String.valueOf(object[NumericConstants.TWO]));
                                    actualSales = actualSales + aSales;
                                }
                                if (object[NumericConstants.THREE] != null) {
                                    Double aAmount = Double.parseDouble(String.valueOf(object[NumericConstants.THREE]));
                                    actualAmount = actualAmount + aAmount;
                                }
                                if (object[NumericConstants.FOUR] != null) {
                                    Double pSales = Double.parseDouble(String.valueOf(object[NumericConstants.FOUR]));
                                    projectedSales = projectedSales + pSales;
                                }
                                if (object[NumericConstants.FIVE] != null) {
                                    Double pAmount = Double.parseDouble(String.valueOf(object[NumericConstants.FIVE]));
                                    projectedAmount = projectedAmount + pAmount;
                                }
                            }
                            if (j == list.size() - 1) {
                                Double arate = actualAmount / actualSales;
                                if (arate.isNaN()) {
                                    arate = 0.0;
                                }
                                arate = arate * NumericConstants.HUNDRED;
                                String actualsRate = String.valueOf(arate);
                                dto.addStringProperties(commonColumn + ACTUALSRATE, actualsRate != null && !NULL.equals(String.valueOf(actualsRate)) && !StringUtils.EMPTY.equals(String.valueOf(actualsRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actualsRate)))).concat(PERCENTAGE) : HYPHEN);
                                Double actualAmt = arate * actualSales;
                                actualAmt = actualAmt / NumericConstants.HUNDRED;
                                if (actualAmt.isNaN()) {
                                    actualAmt = 0.0;
                                }
                                String actualsAmount = String.valueOf(actualAmt);
                                dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualsAmount != null && !NULL.equals(String.valueOf(actualsAmount)) && !StringUtils.EMPTY.equals(String.valueOf(actualsAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actualsAmount)))) : HYPHEN);
                                Double prate = projectedAmount / projectedSales;
                                if (prate.isNaN()) {
                                    prate = 0.0;
                                }
                                prate = prate * NumericConstants.HUNDRED;
                                dto.addStringProperties(commonColumn + PROJECTIONSRATE, prate != null && !NULL.equals(String.valueOf(prate)) && !StringUtils.EMPTY.equals(String.valueOf(prate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(prate)))).concat(PERCENTAGE) : HYPHEN);
                                Double projectedAmtAmt = prate * projectedSales;
                                projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                if (projectedAmtAmt.isNaN()) {
                                    projectedAmtAmt = 0.0;
                                }
                                dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, projectedAmtAmt != null && !NULL.equals(String.valueOf(projectedAmtAmt)) && !StringUtils.EMPTY.equals(String.valueOf(projectedAmtAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(projectedAmtAmt)))) : HYPHEN);
                                actualSales = 0;
                                actualAmount = 0;
                                projectedSales = 0;
                                projectedAmount = 0;
                                commonColumn = Q + object[NumericConstants.SIX] + object[0];
                                year = (Integer) object[0];
                                Quarter = (Integer) object[NumericConstants.SIX];
                            }
                        }
                    }

                    if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
                        double actualSales = 0;
                        double actualAmount = 0;
                        double projectedSales = 0;
                        double projectedAmount = 0;
                        int year = 0;
                        int semiAnnual = 0;
                        String commonColumn = StringUtils.EMPTY;
                        for (int j = 0; j < list.size(); j++) {
                            final Object[] object = (Object[]) list.get(j);
                            int selectedYear = 0;
                            int selectedSemiAnnual = 0;
                            if (object[0] != null) {
                                selectedYear = (Integer) object[0];
                            }
                            if (object[NumericConstants.SIX] != null) {
                                selectedSemiAnnual = (Integer) object[1];
                            }
                            if (year == selectedYear && semiAnnual == selectedSemiAnnual) {
                                if (object[NumericConstants.TWO] != null) {
                                    Double aSales = Double.parseDouble(String.valueOf(object[NumericConstants.TWO]));
                                    actualSales = actualSales + aSales;
                                }
                                if (object[NumericConstants.THREE] != null) {
                                    Double aAmount = Double.parseDouble(String.valueOf(object[NumericConstants.THREE]));
                                    actualAmount = actualAmount + aAmount;
                                }
                                if (object[NumericConstants.FOUR] != null) {
                                    Double pSales = Double.parseDouble(String.valueOf(object[NumericConstants.FOUR]));
                                    projectedSales = projectedSales + pSales;
                                }
                                if (object[NumericConstants.FIVE] != null) {
                                    Double pAmount = Double.parseDouble(String.valueOf(object[NumericConstants.FIVE]));
                                    projectedAmount = projectedAmount + pAmount;
                                }

                            } else if (j == 0) {
                                year = selectedYear;
                                semiAnnual = selectedSemiAnnual;
                                commonColumn = Constant.S_SMALL + object[1] + object[0];
                                if (object[NumericConstants.TWO] != null) {
                                    Double aSales = Double.parseDouble(String.valueOf(object[NumericConstants.TWO]));
                                    actualSales = actualSales + aSales;
                                }
                                if (object[NumericConstants.THREE] != null) {
                                    Double aAmount = Double.parseDouble(String.valueOf(object[NumericConstants.THREE]));
                                    actualAmount = actualAmount + aAmount;
                                }
                                if (object[NumericConstants.FOUR] != null) {
                                    Double pSales = Double.parseDouble(String.valueOf(object[NumericConstants.FOUR]));
                                    projectedSales = projectedSales + pSales;
                                }
                                if (object[NumericConstants.FIVE] != null) {
                                    Double pAmount = Double.parseDouble(String.valueOf(object[NumericConstants.FIVE]));
                                    projectedAmount = projectedAmount + pAmount;
                                }
                            } else {
                                Double arate = actualAmount / actualSales;
                                if (arate.isNaN()) {
                                    arate = 0.0;
                                }
                                arate = arate * NumericConstants.HUNDRED;
                                dto.addStringProperties(commonColumn + ACTUALSRATE, arate != null && !NULL.equals(String.valueOf(arate)) && !StringUtils.EMPTY.equals(String.valueOf(arate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(arate)))).concat(PERCENTAGE) : HYPHEN);
                                Double actualAmt = arate * actualSales;
                                actualAmt = actualAmt / NumericConstants.HUNDRED;
                                if (actualAmt.isNaN()) {
                                    actualAmt = 0.0;
                                }
                                dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualAmt != null && !NULL.equals(String.valueOf(actualAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actualAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actualAmt)))) : HYPHEN);
                                Double prate = projectedAmount / projectedSales;
                                if (prate.isNaN()) {
                                    prate = 0.0;
                                }
                                prate = prate * NumericConstants.HUNDRED;
                                dto.addStringProperties(commonColumn + PROJECTIONSRATE, prate != null && !NULL.equals(String.valueOf(prate)) && !StringUtils.EMPTY.equals(String.valueOf(prate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(prate)))).concat(PERCENTAGE) : HYPHEN);
                                Double projectedAmtAmt = prate * projectedSales;
                                projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                if (projectedAmtAmt.isNaN()) {
                                    projectedAmtAmt = 0.0;
                                }
                                dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, projectedAmtAmt != null && !NULL.equals(String.valueOf(projectedAmtAmt)) && !StringUtils.EMPTY.equals(String.valueOf(projectedAmtAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(projectedAmtAmt)))) : HYPHEN);
                                actualSales = 0;
                                actualAmount = 0;
                                projectedSales = 0;
                                projectedAmount = 0;
                                commonColumn = Constant.S_SMALL + object[1] + object[0];
                                year = (Integer) object[0];
                                semiAnnual = (Integer) object[1];
                                if (object[NumericConstants.TWO] != null) {
                                    Double aSales = Double.parseDouble(String.valueOf(object[NumericConstants.TWO]));
                                    actualSales = actualSales + aSales;
                                }
                                if (object[NumericConstants.THREE] != null) {
                                    Double aAmount = Double.parseDouble(String.valueOf(object[NumericConstants.THREE]));
                                    actualAmount = actualAmount + aAmount;
                                }
                                if (object[NumericConstants.FOUR] != null) {
                                    Double pSales = Double.parseDouble(String.valueOf(object[NumericConstants.FOUR]));
                                    projectedSales = projectedSales + pSales;
                                }
                                if (object[NumericConstants.FIVE] != null) {
                                    Double pAmount = Double.parseDouble(String.valueOf(object[NumericConstants.FIVE]));
                                    projectedAmount = projectedAmount + pAmount;
                                }
                            }
                            if (j == list.size() - 1) {
                                Double arate = actualAmount / actualSales;
                                if (arate.isNaN()) {
                                    arate = 0.0;
                                }
                                arate = arate * NumericConstants.HUNDRED;
                                dto.addStringProperties(commonColumn + ACTUALSRATE, arate != null && !NULL.equals(String.valueOf(arate)) && !StringUtils.EMPTY.equals(String.valueOf(arate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(arate)))).concat(PERCENTAGE) : HYPHEN);
                                Double actualAmt = arate * actualSales;
                                actualAmt = actualAmt / NumericConstants.HUNDRED;
                                if (actualAmt.isNaN()) {
                                    actualAmt = 0.0;
                                }
                                dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualAmt != null && !NULL.equals(String.valueOf(actualAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actualAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actualAmt)))) : HYPHEN);
                                Double prate = projectedAmount / projectedSales;
                                if (prate.isNaN()) {
                                    prate = 0.0;
                                }
                                prate = prate * NumericConstants.HUNDRED;
                                dto.addStringProperties(commonColumn + PROJECTIONSRATE, prate != null && !NULL.equals(String.valueOf(prate)) && !StringUtils.EMPTY.equals(String.valueOf(prate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(prate)))).concat(PERCENTAGE) : HYPHEN);
                                Double projectedAmtAmt = prate * projectedSales;
                                projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                if (projectedAmtAmt.isNaN()) {
                                    projectedAmtAmt = 0.0;
                                }
                                dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, projectedAmtAmt != null && !NULL.equals(String.valueOf(projectedAmtAmt)) && !StringUtils.EMPTY.equals(String.valueOf(projectedAmtAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(projectedAmtAmt)))) : HYPHEN);

                            }
                        }
                    }
                    if (frequency.equals(ANNUALLY)) {
                        double actualSales = 0;
                        double actualAmount = 0;
                        double projectedSales = 0;
                        double projectedAmount = 0;
                        int year = 0;
                        String commonColumn = StringUtils.EMPTY;
                        for (int j = 0; j < list.size(); j++) {
                            final Object[] object = (Object[]) list.get(j);
                            int selectedYear = 0;
                            if (object[0] != null) {
                                selectedYear = (Integer) object[0];
                            }
                            if (year == selectedYear) {
                                if (object[NumericConstants.TWO] != null) {
                                    Double aSales = Double.parseDouble(String.valueOf(object[NumericConstants.TWO]));
                                    actualSales = actualSales + aSales;
                                }
                                if (object[NumericConstants.THREE] != null) {
                                    Double aAmount = Double.parseDouble(String.valueOf(object[NumericConstants.THREE]));
                                    actualAmount = actualAmount + aAmount;
                                }
                                if (object[NumericConstants.FOUR] != null) {
                                    Double pSales = Double.parseDouble(String.valueOf(object[NumericConstants.FOUR]));
                                    projectedSales = projectedSales + pSales;
                                }
                                if (object[NumericConstants.FIVE] != null) {
                                    Double pAmount = Double.parseDouble(String.valueOf(object[NumericConstants.FIVE]));
                                    projectedAmount = projectedAmount + pAmount;
                                }

                            } else if (j == 0) {
                                year = selectedYear;
                                commonColumn = StringUtils.EMPTY + object[0];
                                if (object[NumericConstants.TWO] != null) {
                                    Double aSales = Double.parseDouble(String.valueOf(object[NumericConstants.TWO]));
                                    actualSales = actualSales + aSales;
                                }
                                if (object[NumericConstants.THREE] != null) {
                                    Double aAmount = Double.parseDouble(String.valueOf(object[NumericConstants.THREE]));
                                    actualAmount = actualAmount + aAmount;
                                }
                                if (object[NumericConstants.FOUR] != null) {
                                    Double pSales = Double.parseDouble(String.valueOf(object[NumericConstants.FOUR]));
                                    projectedSales = projectedSales + pSales;
                                }
                                if (object[NumericConstants.FIVE] != null) {
                                    Double pAmount = Double.parseDouble(String.valueOf(object[NumericConstants.FIVE]));
                                    projectedAmount = projectedAmount + pAmount;
                                }
                            } else {
                                Double arate = actualAmount / actualSales;
                                if (arate.isNaN()) {
                                    arate = 0.0;
                                }
                                arate = arate * NumericConstants.HUNDRED;
                                dto.addStringProperties(commonColumn + ACTUALSRATE, arate != null && !NULL.equals(String.valueOf(arate)) && !StringUtils.EMPTY.equals(String.valueOf(arate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(arate)))).concat(PERCENTAGE) : HYPHEN);
                                Double actualAmt = arate * actualSales;
                                actualAmt = actualAmt / NumericConstants.HUNDRED;
                                if (actualAmt.isNaN()) {
                                    actualAmt = 0.0;
                                }
                                dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualAmt != null && !NULL.equals(String.valueOf(actualAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actualAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actualAmt)))) : HYPHEN);
                                Double prate = projectedAmount / projectedSales;
                                if (prate.isNaN()) {
                                    prate = 0.0;
                                }
                                prate = prate * NumericConstants.HUNDRED;
                                dto.addStringProperties(commonColumn + PROJECTIONSRATE, prate != null && !NULL.equals(String.valueOf(prate)) && !StringUtils.EMPTY.equals(String.valueOf(prate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(prate)))).concat(PERCENTAGE) : HYPHEN);
                                Double projectedAmtAmt = prate * projectedSales;
                                projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                if (projectedAmtAmt.isNaN()) {
                                    projectedAmtAmt = 0.0;
                                }
                                dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, projectedAmtAmt != null && !NULL.equals(String.valueOf(projectedAmtAmt)) && !StringUtils.EMPTY.equals(String.valueOf(projectedAmtAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(projectedAmtAmt)))) : HYPHEN);
                                actualSales = 0;
                                actualAmount = 0;
                                projectedSales = 0;
                                projectedAmount = 0;
                                commonColumn = StringUtils.EMPTY + object[0];
                                year = (Integer) object[0];
                                if (object[NumericConstants.TWO] != null) {
                                    Double aSales = Double.parseDouble(String.valueOf(object[NumericConstants.TWO]));
                                    actualSales = actualSales + aSales;
                                }
                                if (object[NumericConstants.THREE] != null) {
                                    Double aAmount = Double.parseDouble(String.valueOf(object[NumericConstants.THREE]));
                                    actualAmount = actualAmount + aAmount;
                                }
                                if (object[NumericConstants.FOUR] != null) {
                                    Double pSales = Double.parseDouble(String.valueOf(object[NumericConstants.FOUR]));
                                    projectedSales = projectedSales + pSales;
                                }
                                if (object[NumericConstants.FIVE] != null) {
                                    Double pAmount = Double.parseDouble(String.valueOf(object[NumericConstants.FIVE]));
                                    projectedAmount = projectedAmount + pAmount;
                                }
                            }
                            if (j == list.size() - 1) {
                                Double arate = actualAmount / actualSales;
                                if (arate.isNaN()) {
                                    arate = 0.0;
                                }
                                arate = arate * NumericConstants.HUNDRED;
                                dto.addStringProperties(commonColumn + ACTUALSRATE, arate != null && !NULL.equals(String.valueOf(arate)) && !StringUtils.EMPTY.equals(String.valueOf(arate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(arate)))).concat(PERCENTAGE) : HYPHEN);
                                Double actualAmt = arate * actualSales;
                                actualAmt = actualAmt / NumericConstants.HUNDRED;
                                if (actualAmt.isNaN()) {
                                    actualAmt = 0.0;
                                }
                                dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualAmt != null && !NULL.equals(String.valueOf(actualAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actualAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actualAmt)))) : HYPHEN);
                                Double prate = projectedAmount / projectedSales;
                                if (prate.isNaN()) {
                                    prate = 0.0;
                                }
                                prate = prate * NumericConstants.HUNDRED;
                                dto.addStringProperties(commonColumn + PROJECTIONSRATE, prate != null && !NULL.equals(String.valueOf(prate)) && !StringUtils.EMPTY.equals(String.valueOf(prate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(prate)))).concat(PERCENTAGE) : HYPHEN);
                                Double projectedAmtAmt = prate * projectedSales;
                                projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                if (projectedAmtAmt.isNaN()) {
                                    projectedAmtAmt = 0.0;
                                }
                                dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, projectedAmtAmt != null && !NULL.equals(String.valueOf(projectedAmtAmt)) && !StringUtils.EMPTY.equals(String.valueOf(projectedAmtAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(projectedAmtAmt)))) : HYPHEN);
                                actualSales = 0;
                                actualAmount = 0;
                                projectedSales = 0;
                                projectedAmount = 0;
                                commonColumn = StringUtils.EMPTY + object[0];
                                year = (Integer) object[0];
                            }
                        }
                    }
                    if (frequency.equals(MONTHLY.getConstant())) {
                        double actualSales = 0;
                        double actualAmount = 0;
                        double projectedSales = 0;
                        double projectedAmount = 0;
                        int year = 0;
                        int month = 0;
                        String commonColumn = StringUtils.EMPTY;
                        for (int i = 0; i < list.size(); i++) {
                            final Object[] obj = (Object[]) list.get(i);
                            if (i == 0) {
                            }
                            int selectedYear = 0;
                            int selectedMonth = 0;
                            if (obj[0] != null) {
                                selectedYear = (Integer) obj[0];
                            }
                            if (obj[NumericConstants.SIX] != null) {
                                selectedMonth = (Integer) obj[1];
                            }
                            if (year == selectedYear && month == selectedMonth) {
                                if (obj[NumericConstants.TWO] != null) {
                                    Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                                    actualSales = actualSales + aSales;
                                }
                                if (obj[NumericConstants.THREE] != null) {
                                    Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                                    actualAmount = actualAmount + aAmount;
                                }
                                if (obj[NumericConstants.FOUR] != null) {
                                    Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                                    projectedSales = projectedSales + pSales;
                                }
                                if (obj[NumericConstants.FIVE] != null) {
                                    Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
                                    projectedAmount = projectedAmount + pAmount;
                                }
                            } else if (i == 0) {
                                year = selectedYear;
                                month = selectedMonth;
                                String monthName = getMonthForInt(Integer.valueOf(StringUtils.EMPTY + obj[1]) - 1);
                                monthName = monthName.toLowerCase();
                                commonColumn = monthName + obj[0];
                                if (obj[NumericConstants.TWO] != null) {
                                    Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                                    actualSales = actualSales + aSales;
                                }
                                if (obj[NumericConstants.THREE] != null) {
                                    Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                                    actualAmount = actualAmount + aAmount;
                                }
                                if (obj[NumericConstants.FOUR] != null) {
                                    Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                                    projectedSales = projectedSales + pSales;
                                }
                                if (obj[NumericConstants.FIVE] != null) {
                                    Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
                                    projectedAmount = projectedAmount + pAmount;
                                }
                            } else {
                                Double arate = actualAmount / actualSales;
                                if (arate.isNaN()) {
                                    arate = 0.0;
                                }
                                arate = arate * NumericConstants.HUNDRED;
                                dto.addStringProperties(commonColumn + ACTUALSRATE, arate != null && !NULL.equals(String.valueOf(arate)) && !StringUtils.EMPTY.equals(String.valueOf(arate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(arate)))).concat(PERCENTAGE) : HYPHEN);
                                Double actualAmt = arate * actualSales;
                                actualAmt = actualAmt / NumericConstants.HUNDRED;
                                if (actualAmt.isNaN()) {
                                    actualAmt = 0.0;
                                }
                                dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualAmt != null && !NULL.equals(String.valueOf(actualAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actualAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actualAmt)))) : HYPHEN);
                                Double prate = projectedAmount / projectedSales;
                                if (prate.isNaN()) {
                                    prate = 0.0;
                                }
                                prate = prate * NumericConstants.HUNDRED;
                                dto.addStringProperties(commonColumn + PROJECTIONSRATE, prate != null && !NULL.equals(String.valueOf(prate)) && !StringUtils.EMPTY.equals(String.valueOf(prate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(prate)))).concat(PERCENTAGE) : HYPHEN);
                                Double projectedAmtAmt = prate * projectedSales;
                                projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                if (projectedAmtAmt.isNaN()) {
                                    projectedAmtAmt = 0.0;
                                }
                                dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, projectedAmtAmt != null && !NULL.equals(String.valueOf(projectedAmtAmt)) && !StringUtils.EMPTY.equals(String.valueOf(projectedAmtAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(projectedAmtAmt)))) : HYPHEN);
                                actualSales = 0;
                                actualAmount = 0;
                                projectedSales = 0;
                                projectedAmount = 0;
                                String monthName = getMonthForInt(Integer.valueOf(StringUtils.EMPTY + obj[1]) - 1);
                                monthName = monthName.toLowerCase();
                                commonColumn = monthName + obj[0];
                                year = (Integer) obj[0];
                                month = (Integer) obj[1];
                                if (obj[NumericConstants.TWO] != null) {
                                    Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                                    actualSales = actualSales + aSales;
                                }
                                if (obj[NumericConstants.THREE] != null) {
                                    Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                                    actualAmount = actualAmount + aAmount;
                                }
                                if (obj[NumericConstants.FOUR] != null) {
                                    Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                                    projectedSales = projectedSales + pSales;
                                }
                                if (obj[NumericConstants.FIVE] != null) {
                                    Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
                                    projectedAmount = projectedAmount + pAmount;
                                }
                            }
                            if (i == list.size() - 1) {
                                Double arate = actualAmount / actualSales;
                                if (arate.isNaN()) {
                                    arate = 0.0;
                                }
                                arate = arate * NumericConstants.HUNDRED;
                                dto.addStringProperties(commonColumn + ACTUALSRATE, arate != null && !NULL.equals(String.valueOf(arate)) && !StringUtils.EMPTY.equals(String.valueOf(arate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(arate)))).concat(PERCENTAGE) : HYPHEN);
                                Double actualAmt = arate * actualSales;
                                actualAmt = actualAmt / NumericConstants.HUNDRED;
                                if (actualAmt.isNaN()) {
                                    actualAmt = 0.0;
                                }
                                dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualAmt != null && !NULL.equals(String.valueOf(actualAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actualAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actualAmt)))) : HYPHEN);
                                Double prate = projectedAmount / projectedSales;
                                if (prate.isNaN()) {
                                    prate = 0.0;
                                }
                                prate = prate * NumericConstants.HUNDRED;
                                dto.addStringProperties(commonColumn + PROJECTIONSRATE, prate != null && !NULL.equals(String.valueOf(prate)) && !StringUtils.EMPTY.equals(String.valueOf(prate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(prate)))).concat(PERCENTAGE) : HYPHEN);
                                Double projectedAmtAmt = prate * projectedSales;
                                projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                if (projectedAmtAmt.isNaN()) {
                                    projectedAmtAmt = 0.0;
                                }
                                dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, projectedAmtAmt != null && !NULL.equals(String.valueOf(projectedAmtAmt)) && !StringUtils.EMPTY.equals(String.valueOf(projectedAmtAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(projectedAmtAmt)))) : HYPHEN);
                                actualSales = 0;
                                actualAmount = 0;
                                projectedSales = 0;
                                projectedAmount = 0;
                                String monthName = getMonthForInt(Integer.valueOf(StringUtils.EMPTY + obj[1]) - 1);
                                monthName = monthName.toLowerCase();
                                commonColumn = monthName + obj[0];
                                year = (Integer) obj[0];
                                month = (Integer) obj[1];
                            }
                        }
                    }
                }
            }

        } catch (NumberFormatException e) {
            LOGGER.error(e);
        }
        return dto;
    }

    public DiscountProjectionResultsDTO getSubTotal(DiscountProjectionResultsDTO dto, ProjectionSelectionDTO projSelDTO) {

        try {
            dto.setIsParent(Constant.STRING_ONE);
            List<Integer> yearList = new ArrayList<>();
            yearList.add(projSelDTO.getForecastDTO().getHistoryStartYear());
            yearList.add(projSelDTO.getForecastDTO().getHistoryStartMonth());
            yearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
            yearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());

            yearList.add(projSelDTO.getForecastDTO().getForecastStartYear());
            yearList.add(projSelDTO.getForecastDTO().getForecastStartMonth());
            yearList.add(projSelDTO.getForecastDTO().getForecastEndYear());
            yearList.add(projSelDTO.getForecastDTO().getForecastEndMonth());

            String userId = String.valueOf(projSelDTO.getUserId());
            String sessionId = String.valueOf(projSelDTO.getSessionId());
            List<Integer> projectionDet = dqLogic.getProjectionDetailsId(projSelDTO);
            String frequency = projSelDTO.getFrequency();
            List<String> discountList = projSelDTO.getDiscountNameList();
            String discountString = StringUtils.EMPTY;
            for (int i = 0; i < discountList.size(); i++) {
                if (i != discountList.size() - 1) {
                    discountString = discountString.concat("'") + discountList.get(i) + "',";
                } else {
                    discountString = discountString.concat("'") + discountList.get(i) + "'";
                }
            }
            if (discountString.equals(StringUtils.EMPTY)) {
                discountString = Constant.DASH;
            }

            List list;
            if (dto.getGroup().equals(Constant.MANDATED_DISCOUNT) || dto.getLevelValue().equals(Constant.MANDATED_DISCOUNT)) {
                dto.setCurrentLevel(Constant.MANDATED_DISCOUNT);
                list = dqLogic.getMandatedTotal(projectionDet, frequency, projSelDTO);

            } else {
                dto.setCurrentLevel(Constant.MANDATED_SUPPLEMENTAL);
                list = dqLogic.getSuppTotal(projectionDet, frequency, projSelDTO);

            }

            int frequencyDivision;
            if (Constant.QUARTERLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
                frequencyDivision = NumericConstants.FOUR;
            } else if (Constant.MONTHLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
                frequencyDivision = NumericConstants.TWELVE;
            } else if (Constant.SEMIANNUALLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
                frequencyDivision = NumericConstants.TWO;
            } else {
                frequencyDivision = 1;
            }

            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;

                if (Constant.DASH.equalsIgnoreCase(String.valueOf(obj[NumericConstants.FOUR]))) {

                    int year = Integer.valueOf(String.valueOf(obj[0]));
                    int period = Integer.valueOf(String.valueOf(obj[1]));
                    List<String> column = getCommonColumnHeader(frequencyDivision, year, period);
                    String commonColumn = column.get(0);

                    String actualsAmount = String.valueOf(obj[NumericConstants.TWO]);
                    String actualsRate = String.valueOf(obj[NumericConstants.THREE]);
                    String actualsRPU = String.valueOf(obj[NumericConstants.FIVE]);
                    actualsAmount = getFormatValue(NumericConstants.TWO, actualsAmount, CURRENCY);
                    actualsRate = getFormatValue(NumericConstants.TWO, actualsRate, PERCENTAGE);
                    actualsRPU = getFormatValue(NumericConstants.TWO, actualsRPU, CURRENCY);
                    dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualsAmount);
                    dto.addStringProperties(commonColumn + ACTUALSRATE, actualsRate);
                    dto.addStringProperties(commonColumn + ACTUALRPU, actualsRPU);
                } else {

                    int year = Integer.valueOf(String.valueOf(obj[0]));
                    int period = Integer.valueOf(String.valueOf(obj[1]));
                    List<String> column = getCommonColumnHeader(frequencyDivision, year, period);
                    String commonColumn = column.get(0);
                    String projAmount = String.valueOf(obj[NumericConstants.TWO]);
                    String projRate = String.valueOf(obj[NumericConstants.THREE]);
                    String projRPU = String.valueOf(obj[NumericConstants.FIVE]);
                    projAmount = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? projAmount : Constant.ZERO_STRING;
                    projRate = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? projRate : Constant.ZERO_STRING;
                    projRPU = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? projRPU : Constant.ZERO_STRING;
                    projAmount = getFormatValue(NumericConstants.TWO, projAmount, CURRENCY);
                    projRate = getFormatValue(NumericConstants.TWO, projRate, PERCENTAGE);
                    projRPU = getFormatValue(NumericConstants.TWO, projRPU, CURRENCY);

                    dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, projAmount);
                    dto.addStringProperties(commonColumn + PROJECTIONSRATE, projRate);
                    dto.addStringProperties(commonColumn + PROJECTEDRPU, projRPU);

                }
            }

        } catch (NumberFormatException e) {
            LOGGER.error(e);
        }
        return dto;
    }

    public List<String> getTherapeuticValue(int projectionId) {
        List<String> listValue = new ArrayList<>();
        List queryList = dqLogic.getTherapeuticValue(projectionId);
        if (!queryList.isEmpty()) {
            for (int i = 0; i < queryList.size(); i++) {

                listValue.add(String.valueOf(queryList.get(i)));
            }
        }

        return listValue;
    }

    public List<String> getBrandValue(int projectionId, String therapeuticValue) {
        List<String> listValue = new ArrayList<>();
        List queryList = dqLogic.getBrandValue(projectionId, therapeuticValue);
        if (!queryList.isEmpty()) {
            for (int i = 0; i < queryList.size(); i++) {

                listValue.add(String.valueOf(queryList.get(i)));
            }
        }
        return listValue;
    }

    public String getFormatValue(int numberOfDecimal, String value, String appendChar) {
        if (value.contains(Constant.NULL)) {
            value = "...";
        } else if (CURRENCY.equals(appendChar)) {
            value = appendChar.concat(String.valueOf(new BigDecimal(String.valueOf(value)).setScale(numberOfDecimal, BigDecimal.ROUND_DOWN)));
        } else {
            value = String.valueOf(new BigDecimal(String.valueOf(value)).setScale(numberOfDecimal, BigDecimal.ROUND_DOWN)).concat(appendChar);
        }
        return value;
    }

    private List<DiscountProjectionResultsDTO> getPivotProjectionTotalDiscount(ProjectionSelectionDTO projSelDTO, int start, int offset) {
        int neededRecord = offset;
        int started = start;
        List<DiscountProjectionResultsDTO> totalDTO = new ArrayList<>();
        List<String> periodList = getProjectionTotalCount(projSelDTO);
        int maxrecord = periodList.size();
        List<DiscountProjectionResultsDTO> resultList = new ArrayList<>();
        String userId = String.valueOf(projSelDTO.getUserId());
        String sessionId = projSelDTO.getSessionDTO().getSessionId();
        String managed_Medicaid = Constant.MANAGED_MEDICAID;
        String quarterly = projSelDTO.getFrequency();
        String frequency = StringUtils.EMPTY;
        Object object1 = null;
        Object object0 = null;
        Integer historyStartMonth = projSelDTO.getForecastDTO().getHistoryStartMonth();
        Integer historyStartYear = projSelDTO.getForecastDTO().getHistoryStartYear();
        int ProjectioId = Integer.valueOf(projSelDTO.getProjectionId());
        if (Constant.QUARTERLY.equalsIgnoreCase(quarterly)) {
            frequency = Constant.QUARTERLY;
        } else if (SEMI_ANNUALLY.getConstant().equalsIgnoreCase(quarterly)) {
            frequency = "SEMI-ANNUALLY";
        } else if (ANNUALLY.equalsIgnoreCase(quarterly)) {
            frequency = "ANNUALLY";
        } else if (MONTHLY.getConstant().equalsIgnoreCase(quarterly)) {
            frequency = MONTHLY.getConstant();
        }

        Object[] orderedArgs = {ProjectioId, userId, sessionId, managed_Medicaid, frequency, object1, object0, historyStartMonth, historyStartYear};
         List<Object[]> list;
        if (projSelDTO.getSessionDTO().isDprRefreshReqd() ||  !CommonLogic.checkProcedureInputIsSame(orderedArgs, dprOrderedArgs)) {
            // Procedure called only in  Tab Change
             list = CommonLogic.callProcedure(viewFlag ? "PRC_M_DISCOUNT_PROJ_TOTAL_VIEW":"PRC_M_DISCOUNT_PROJ_TOTAL", orderedArgs);
            dprOrderedArgs = new Object[orderedArgs.length];
            System.arraycopy(orderedArgs, 0, dprOrderedArgs, 0, orderedArgs.length);
            totalPrcResultList.clear();
            totalPrcResultList.addAll(list);
        } else {
            list = totalPrcResultList;
        }
        projSelDTO.getSessionDTO().setDprRefreshReqd(false);

        int frequencyDivision;
        if (Constant.QUARTERLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
            frequencyDivision = NumericConstants.FOUR;
        } else if (Constant.MONTHLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
            frequencyDivision = NumericConstants.TWELVE;
        } else if (Constant.SEMIANNUALLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
            frequencyDivision = NumericConstants.TWO;
        } else {
            frequencyDivision = 1;
        }
        String checkYear = StringUtils.EMPTY;
        if (list != null) {

            DiscountProjectionResultsDTO dto = null;
            for (int i = 0; i < list.size(); i++) {

                final Object[] obj = (Object[]) list.get(i);

                int period;
                int year;
                if (ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
                    period = 0;
                    year = Integer.valueOf(String.valueOf(obj[NumericConstants.TWO]));
                } else {
                    period = Integer.valueOf(String.valueOf(obj[NumericConstants.TWO]));
                    year = Integer.valueOf(String.valueOf(obj[NumericConstants.THREE]));
                }

                List<String> column = getCommonColumnHeader(frequencyDivision, year, period);
                if (periodList.contains(column.get(1))) {
                    periodList.remove(column.get(1));
                    if (checkYear.isEmpty() || !checkYear.equalsIgnoreCase(column.get(1))) {

                        if (dto != null) {

                            customizePCDisc(list, dto, projSelDTO, checkYear);
                            resultList.add(dto);
                        }
                        dto = new DiscountProjectionResultsDTO();
                        dto.setParent(0);
                        checkYear = column.get(1);
                    }

                    if (i == list.size() - 1) {
                        customizePCDisc(list, dto, projSelDTO, checkYear);
                        resultList.add(dto);
                    }

                }
            }

            List<DiscountProjectionResultsDTO> projectionDtoList;
            projectionDtoList = resultList;
            if (started < maxrecord) {
                for (int k = started; k < projectionDtoList.size() && neededRecord > 0; neededRecord--, k++) {
                    totalDTO.add(projectionDtoList.get(k));
                    started++;
                }
            }
        }
        return totalDTO;
    }

    private void customizePCDisc(List<Object[]> list, DiscountProjectionResultsDTO dto, ProjectionSelectionDTO projSelDTO, String checkYear) {
        if (!list.isEmpty() && list != null) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                int frequencyDivision = projSelDTO.getFrequencyDivision();
                int period;
                int year;
                if (ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
                    period = 0;
                    year = Integer.valueOf(String.valueOf(obj[NumericConstants.TWO]));
                } else {
                    period = Integer.valueOf(String.valueOf(obj[NumericConstants.TWO]));
                    year = Integer.valueOf(String.valueOf(obj[NumericConstants.THREE]));
                }

                List<String> column = getCommonColumnHeaderForMMDPRPivot(frequencyDivision, year, period);
                String commonColumn = column.get(0);
                String commonHeader = column.get(1);

                String falgValue;
                if (ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
                    falgValue = String.valueOf(obj[NumericConstants.THREE]);
                } else {
                    falgValue = String.valueOf(obj[NumericConstants.FOUR]);
                }

                if (!checkYear.isEmpty() && commonColumn.equalsIgnoreCase(checkYear.replace(" ", StringUtils.EMPTY))) {
                    dto.setGroup(commonHeader);
                    dto.setParent(0);
                    dto.setLevelValue(commonHeader);

                    if (Constant.ACTUAL_CAPS.equalsIgnoreCase(falgValue)) {

                        String actualsAmount = String.valueOf(obj[0]);
                        String actualsRate = String.valueOf(obj[1]);
                        String actualRPU = ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency()) ? String.valueOf(obj[NumericConstants.FOUR]) : String.valueOf(obj[NumericConstants.FIVE]);
                        actualsAmount = getFormatValue(NumericConstants.TWO, actualsAmount, CURRENCY);
                        actualsRate = getFormatValue(NumericConstants.TWO, actualsRate, PERCENTAGE);
                        actualRPU = getFormatValue(NumericConstants.TWO, actualRPU, CURRENCY);
                        dto.addStringProperties(Constant.TOTALDISCOUNT + ACTUALSRATE, actualsRate);
                        dto.addStringProperties(Constant.TOTALDISCOUNT + ACTUALSAMOUNT, actualsAmount);
                        dto.addStringProperties(Constant.TOTALDISCOUNT + ACTUALRPU, actualRPU);

                    } else {

                        String projAmount = String.valueOf(obj[0]);
                        String projRate = String.valueOf(obj[1]);
                        String projRPU = ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency()) ? String.valueOf(obj[NumericConstants.FOUR]) : String.valueOf(obj[NumericConstants.FIVE]);
                        projAmount = getFormatValue(NumericConstants.TWO, projAmount, CURRENCY);
                        projRate = getFormatValue(NumericConstants.TWO, projRate, PERCENTAGE);
                        projRPU = getFormatValue(NumericConstants.TWO, projRPU, CURRENCY);
                        dto.addStringProperties(Constant.TOTALDISCOUNT + PROJECTIONSAMOUNT, projAmount);
                        dto.addStringProperties(Constant.TOTALDISCOUNT + PROJECTIONSRATE, projRate);
                        dto.addStringProperties(Constant.TOTALDISCOUNT + PROJECTEDRPU, projRPU);

                    }

                }

            }
        }

    }

    private void customizeManSupp(List list, DiscountProjectionResultsDTO dto, ProjectionSelectionDTO projSelDTO, String checkYear) {

        if (!checkYear.isEmpty() && checkYear != null) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;

                int frequencyDivision = projSelDTO.getFrequencyDivision();
                int year = Integer.valueOf(String.valueOf(obj[0]));
                int period = Integer.valueOf(String.valueOf(obj[1]));
                List<String> column = getCommonColumnHeaderForMMDPRPivot(frequencyDivision, year, period);
                String commonColumn = column.get(0);
                String commonHeader = column.get(1);
                if (Constant.MANDATED_DISCOUNT.equals(projSelDTO.getDiscountLevel()) || Constant.MANDATED_DISCOUNT.equalsIgnoreCase(projSelDTO.getGroup())) {
                    dto.setDiscountLevel(Constant.MANDATED_DISCOUNT);
                } else {
                    dto.setDiscountLevel(Constant.MANDATED_SUPPLEMENTAL);
                }
                if (!checkYear.isEmpty() && commonColumn.equalsIgnoreCase(checkYear.replace(" ", StringUtils.EMPTY))) {
                    dto.setSupplementalLevelName(String.valueOf(obj[NumericConstants.SEVEN]));
                    dto.setGroup(commonHeader);
                    dto.setLevelValue(commonHeader);
                    dto.setParent(1);
                    dto.setCurrentLevel(Constant.CUSTOMER_SMALL);
                    if (Constant.ACT.equalsIgnoreCase(String.valueOf(obj[NumericConstants.TEN]))) {

                        String actualsAmount = String.valueOf(obj[NumericConstants.EIGHT]);
                        String actualsRate = String.valueOf(obj[NumericConstants.NINE]);
                        String actualsRPU = String.valueOf(obj[NumericConstants.ELEVEN]);
                        actualsAmount = getFormatValue(NumericConstants.TWO, actualsAmount, CURRENCY);
                        actualsRate = getFormatValue(NumericConstants.TWO, actualsRate, PERCENTAGE);
                        actualsRPU = getFormatValue(NumericConstants.TWO, actualsRPU, CURRENCY);
                        dto.addStringProperties(Constant.TOTALDISCOUNT + ACTUALSAMOUNT, actualsAmount);
                        dto.addStringProperties(Constant.TOTALDISCOUNT + ACTUALSRATE, actualsRate);
                        dto.addStringProperties(Constant.TOTALDISCOUNT + ACTUALRPU, actualsRPU);
                    } else {

                        String projAmount = String.valueOf(obj[NumericConstants.EIGHT]);
                        String projRate = String.valueOf(obj[NumericConstants.NINE]);
                        String projRPU = String.valueOf(obj[NumericConstants.ELEVEN]);
                        projAmount = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? projAmount : Constant.ZERO_STRING;
                        projRate = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? projRate : Constant.ZERO_STRING;
                        projRPU = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? projRPU : Constant.ZERO_STRING;
                        projAmount = getFormatValue(NumericConstants.TWO, projAmount, CURRENCY);
                        projRate = getFormatValue(NumericConstants.TWO, projRate, PERCENTAGE);
                        projRPU = getFormatValue(NumericConstants.TWO, projRPU, CURRENCY);

                        dto.addStringProperties(Constant.TOTALDISCOUNT + PROJECTIONSAMOUNT, projAmount);
                        dto.addStringProperties(Constant.TOTALDISCOUNT + PROJECTIONSRATE, projRate);
                        dto.addStringProperties(Constant.TOTALDISCOUNT + PROJECTEDRPU, projRPU);

                    }
                }
            }
        }

    }

    private void customizeNonMandat(List list, DiscountProjectionResultsDTO dto, ProjectionSelectionDTO projSelDTO, String checkYear) {
        if (!checkYear.isEmpty() && checkYear != null) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                int frequencyDivision = projSelDTO.getFrequencyDivision();
                int year = Integer.valueOf(String.valueOf(obj[0]));
                int period = Integer.valueOf(String.valueOf(obj[1]));
                List<String> column = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = column.get(0);
                String commonHeader = column.get(1);
                if (!checkYear.isEmpty() && commonHeader.equalsIgnoreCase(checkYear)) {
                    dto.setGroup(commonHeader);
                    dto.setLevelValue(commonHeader);
                    dto.setParent(1);
                    dto.setDiscountLevel(Constant.NON_MANDATED_SUPPLEMENTAL);
                    dto.setNmSuppLevel("NMLevel");
                    if (Constant.ACTUAL_CAPS.equalsIgnoreCase(String.valueOf(obj[NumericConstants.THREE]))) {
                        String actualsAmount = String.valueOf(obj[NumericConstants.TWO]);
                        String actualsRate = String.valueOf(obj[NumericConstants.FOUR]);
                        String actualsRPU = String.valueOf(obj[NumericConstants.FIVE]);
                        actualsAmount = getFormatValue(NumericConstants.TWO, actualsAmount, CURRENCY);
                        actualsRate = getFormatValue(NumericConstants.TWO, actualsRate, PERCENTAGE);
                        actualsRPU = getFormatValue(NumericConstants.TWO, actualsRPU, CURRENCY);
                        dto.addStringProperties(Constant.TOTALDISCOUNT + ACTUALSRATE, actualsRate);
                        dto.addStringProperties(Constant.TOTALDISCOUNT + ACTUALSAMOUNT, actualsAmount);
                        dto.addStringProperties(Constant.TOTALDISCOUNT + ACTUALRPU, actualsRPU);

                    } else {
                        String proAmount = String.valueOf(obj[NumericConstants.TWO]);
                        String prorate = String.valueOf(obj[NumericConstants.FOUR]);
                        String projRPU = String.valueOf(obj[NumericConstants.FIVE]);
                        proAmount = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? proAmount : Constant.ZERO_STRING;
                        prorate = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? prorate : Constant.ZERO_STRING;
                        projRPU = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? projRPU : Constant.ZERO_STRING;
                        
                        proAmount = getFormatValue(NumericConstants.TWO, proAmount, CURRENCY);
                        prorate = getFormatValue(NumericConstants.TWO, prorate, PERCENTAGE);
                        projRPU = getFormatValue(NumericConstants.TWO, projRPU, CURRENCY);
                        dto.addStringProperties(Constant.TOTALDISCOUNT + PROJECTIONSRATE, prorate);
                        dto.addStringProperties(Constant.TOTALDISCOUNT + PROJECTIONSAMOUNT, proAmount);
                        dto.addStringProperties(Constant.TOTALDISCOUNT + PROJECTEDRPU, projRPU);

                    }

                }
            }
        }
    }

    private void customizeNonMandat_Brand(List list, DiscountProjectionResultsDTO dto, ProjectionSelectionDTO projSelDTO, String checkYear) {
        if (!checkYear.isEmpty() && checkYear != null) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                int frequencyDivision = projSelDTO.getFrequencyDivision();
                int year = Integer.valueOf(String.valueOf(obj[1]));
                int period = Integer.valueOf(String.valueOf(obj[NumericConstants.TWO]));
                List<String> column = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = column.get(1);

                    if ((!checkYear.isEmpty() && commonColumn.equalsIgnoreCase(checkYear)) && ((projSelDTO.getGroup().equals(commonColumn) && pivotBrandName.equalsIgnoreCase(String.valueOf(obj[0]))) && (!String.valueOf(obj[0]).isEmpty() && obj[0] != null))) {
                            dto.setGroup(String.valueOf(obj[0]));
                            dto.setLevelValue(StringUtils.EMPTY + String.valueOf(obj[0]));
                            dto.setParent(0);
                            dto.setNmSuppLevel(StringUtils.EMPTY);
                            dto.setDiscountLevel(Constant.NON_MANDATED_SUPPLEMENTAL);
                            if (Constant.ACTUAL_CAPS.equalsIgnoreCase(String.valueOf(obj[NumericConstants.FOUR]))) {

                                String actualsAmount = String.valueOf(obj[NumericConstants.THREE]);
                                String actualsRate = String.valueOf(obj[NumericConstants.FIVE]);
                                String actualRPU = String.valueOf(obj[NumericConstants.SIX]);
                                actualsAmount = getFormatValue(NumericConstants.TWO, actualsAmount, CURRENCY);
                                actualsRate = getFormatValue(NumericConstants.TWO, actualsRate, PERCENTAGE);
                                actualRPU = getFormatValue(NumericConstants.TWO, actualRPU, CURRENCY);
                                dto.addStringProperties(Constant.TOTALDISCOUNT + ACTUALSRATE, actualsRate);
                                dto.addStringProperties(Constant.TOTALDISCOUNT + ACTUALSAMOUNT, actualsAmount);
                                dto.addStringProperties(Constant.TOTALDISCOUNT + ACTUALRPU, actualRPU);

                            } else {

                                String proAmount = String.valueOf(obj[NumericConstants.THREE]);
                                String prorate = String.valueOf(obj[NumericConstants.FIVE]);
                                String projRPU = String.valueOf(obj[NumericConstants.SIX]);
                                proAmount = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? proAmount : Constant.ZERO_STRING;
                                prorate = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? prorate : Constant.ZERO_STRING;
                                projRPU = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? projRPU : Constant.ZERO_STRING;
                                proAmount = getFormatValue(NumericConstants.TWO, proAmount, CURRENCY);
                                prorate = getFormatValue(NumericConstants.TWO, prorate, PERCENTAGE);
                                projRPU = getFormatValue(NumericConstants.TWO, projRPU, CURRENCY);
                                dto.addStringProperties(Constant.TOTALDISCOUNT + PROJECTIONSRATE, prorate);
                                dto.addStringProperties(Constant.TOTALDISCOUNT + PROJECTIONSAMOUNT, proAmount);
                                dto.addStringProperties(Constant.TOTALDISCOUNT + PROJECTEDRPU, projRPU);

                            }
                    }
            }
        }
    }

    private List<DiscountProjectionResultsDTO> getCurrentLevel(List list, ProjectionSelectionDTO projSelDTO, boolean flag) {
        List<DiscountProjectionResultsDTO> discountDTO = new ArrayList<>();
        int loopCount = 0;
        for (int i = 0; i < list.size(); i++) {

            Object[] obj = (Object[]) list.get(i);

            if (loopCount == 0) {
                groupName = String.valueOf(obj[NumericConstants.SIX]);
            }
            if ((!groupName.equals(String.valueOf(obj[NumericConstants.SIX]))) || loopCount == 0) {
                groupName = String.valueOf(obj[NumericConstants.SIX]);
                DiscountProjectionResultsDTO childDto = new DiscountProjectionResultsDTO();
                childDto = getMDLevelValue(list, childDto, projSelDTO, flag);
                if (projSelDTO.isFilterDdlb()) {
                    childDto.setParent(0);
                }

                discountDTO.add(childDto);
            }

            loopCount += 1;

        }

        return discountDTO;
    }

    public List<DiscountProjectionResultsDTO> getCustomize_Brand(List list, int frequencyDivision, ProjectionSelectionDTO projSelDTO) {
        List<DiscountProjectionResultsDTO> discountDTO = new ArrayList<>();
        int loopCount = 0;
        for (int i = 0; i < list.size(); i++) {

            Object[] obj = (Object[]) list.get(i);

            if (loopCount == 0) {
                nmSupp_Level = String.valueOf(obj[0]);
            }
            if ((!nmSupp_Level.equalsIgnoreCase(String.valueOf(obj[0]))) || loopCount == 0) {
                nmSupp_Level = String.valueOf(obj[0]);
                DiscountProjectionResultsDTO childDto = new DiscountProjectionResultsDTO();
                childDto = customizedNMBrandLevel(list, childDto, frequencyDivision, projSelDTO);
                childDto.setParent(0);
                discountDTO.add(childDto);
            }

            loopCount += 1;

        }
        return discountDTO;
    }

    public DiscountProjectionResultsDTO customizedNMBrandLevel(List list, DiscountProjectionResultsDTO dto, int frequencyDivision, ProjectionSelectionDTO projSelDTO) {
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                if (nmSupp_Level.equalsIgnoreCase(String.valueOf(obj[0]))) {
                    dto.setGroup(String.valueOf(obj[0]));
                    dto.setLevelValue(String.valueOf(obj[0]));
                    dto.setParent(0);
                    dto.setParentNode(projSelDTO.getParentNode());
                    if (Constant.ACTUAL_CAPS.equalsIgnoreCase(String.valueOf(obj[NumericConstants.FOUR]))) {

                        int year = Integer.valueOf(String.valueOf(obj[1]));
                        int period = Integer.valueOf(String.valueOf(obj[NumericConstants.TWO]));
                        List<String> column = getCommonColumnHeader(frequencyDivision, year, period);
                        String commonColumn = column.get(0);
                        String actualsAmount = String.valueOf(obj[NumericConstants.THREE]);
                        String actualsRate = String.valueOf(obj[NumericConstants.FIVE]);
                        String actualsRPU = String.valueOf(obj[NumericConstants.SIX]);
                        actualsAmount = getFormatValue(NumericConstants.TWO, actualsAmount, CURRENCY);
                        actualsRate = getFormatValue(NumericConstants.TWO, actualsRate, PERCENTAGE);
                        actualsRPU = getFormatValue(NumericConstants.TWO, actualsRPU, CURRENCY);
                        dto.addStringProperties(commonColumn + ACTUALSRATE, actualsRate);
                        dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualsAmount);
                        dto.addStringProperties(commonColumn + ACTUALRPU, actualsRPU);

                    } else {
                        int year = Integer.valueOf(String.valueOf(obj[1]));
                        int period = Integer.valueOf(String.valueOf(obj[NumericConstants.TWO]));
                        List<String> column = getCommonColumnHeader(frequencyDivision, year, period);
                        String commonColumn = column.get(0);
                        String proAmount = String.valueOf(obj[NumericConstants.THREE]);
                        String prorate = String.valueOf(obj[NumericConstants.FIVE]);
                        String projRPU = String.valueOf(obj[NumericConstants.SIX]);
                        proAmount = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? proAmount : Constant.ZERO_STRING;
                        prorate = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? prorate : Constant.ZERO_STRING;
                        projRPU = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? projRPU : Constant.ZERO_STRING;
                        proAmount = getFormatValue(NumericConstants.TWO, proAmount, CURRENCY);
                        prorate = getFormatValue(NumericConstants.TWO, prorate, PERCENTAGE);
                        projRPU = getFormatValue(NumericConstants.TWO, projRPU, CURRENCY);
                        dto.addStringProperties(commonColumn + PROJECTIONSRATE, prorate);
                        dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount);
                        dto.addStringProperties(commonColumn + PROJECTEDRPU, projRPU);

                    }
                }
            }

        }
        return dto;
    }

    public List<DiscountProjectionResultsDTO> getPivotManCus(DiscountProjectionResultsDTO dto, ProjectionSelectionDTO projSelDTO) {
        try {
            List list = new ArrayList();
            boolean curFlag;
            dto.setParent(1);
            curFlag = false;
            List<DiscountProjectionResultsDTO> pivotDiscount = new ArrayList<>();
            if (Constant.MANDATED_DISCOUNT.equalsIgnoreCase(projSelDTO.getLevelValue()) || projSelDTO.getDiscountLevel().equalsIgnoreCase(Constant.MANDATED_DISCOUNT)) {
                if (Constant.CUSTOMER_SMALL.equals(projSelDTO.getSupplementalLevelName())) {
                    if (MONTHLY.getConstant().equalsIgnoreCase(projSelDTO.getFrequency())) {
                        projSelDTO.setPivotValue(projSelDTO.getGroup().replace(projSelDTO.getGroup().substring(0, NumericConstants.THREE), map.get(projSelDTO.getGroup().substring(0, NumericConstants.THREE))));
                    } else {
                        projSelDTO.setPivotValue(projSelDTO.getGroup().contains(Constant.Q) ? projSelDTO.getGroup().replace(Constant.Q, " ") : projSelDTO.getGroup().replace(Constant.S, " "));
                    }
                    list = dqLogic.getLevelValue(projSelDTO);
                } else {
                    if (MONTHLY.getConstant().equalsIgnoreCase(projSelDTO.getFrequency())) {
                        if (map.get(projSelDTO.getPivotValue().substring(0, NumericConstants.THREE)) == null) {
                            projSelDTO.setPivotValue(projSelDTO.getPivotValue());
                        } else {
                            String monValue = projSelDTO.getPivotValue().replace(projSelDTO.getPivotValue().substring(0, NumericConstants.THREE), map.get(projSelDTO.getPivotValue().substring(0, NumericConstants.THREE)));
                            projSelDTO.setPivotValue(monValue);
                        }
                    } else {
                        projSelDTO.setPivotValue(projSelDTO.getPivotValue().contains(Constant.Q) ? projSelDTO.getPivotValue().replace(Constant.Q, " ") : projSelDTO.getPivotValue().replace(Constant.S, " "));
                    }
                    if (Constant.BRAND_CAPS.equals(projSelDTO.getSupplementalLevelName())) {
                        list = dqLogic.getBrandLevelValue(projSelDTO);
                    } else if (Constant.CONTRACT_SMALL.equals(projSelDTO.getSupplementalLevelName())) {
                        list = dqLogic.getContractLevelValue(projSelDTO);
                    }
                }
                curFlag = true;
            } else if (Constant.MANDATED_SUPPLEMENTAL.equalsIgnoreCase(projSelDTO.getLevelValue()) || projSelDTO.getDiscountLevel().equalsIgnoreCase(Constant.MANDATED_SUPPLEMENTAL)) {
                if (Constant.CUSTOMER_SMALL.equals(projSelDTO.getSupplementalLevelName())) {
                    if (MONTHLY.getConstant().equalsIgnoreCase(projSelDTO.getFrequency())) {
                        projSelDTO.setPivotValue(projSelDTO.getGroup().replace(projSelDTO.getGroup().substring(0, NumericConstants.THREE), map.get(projSelDTO.getGroup().substring(0, NumericConstants.THREE))));
                    } else {
                        projSelDTO.setPivotValue(projSelDTO.getGroup().contains(Constant.Q) ? projSelDTO.getGroup().replace(Constant.Q, " ") : projSelDTO.getGroup().replace(Constant.S, " "));
                    }
                    list = dqLogic.getSuppLevelValue(projSelDTO);
                } else {
                    if (MONTHLY.getConstant().equalsIgnoreCase(projSelDTO.getFrequency())) {
                        if (map.get(projSelDTO.getPivotValue().substring(0, NumericConstants.THREE)) != null) {
                            projSelDTO.setPivotValue(projSelDTO.getPivotValue().replace(projSelDTO.getPivotValue().substring(0, NumericConstants.THREE), map.get(projSelDTO.getPivotValue().substring(0, NumericConstants.THREE))));
                        } else {
                            projSelDTO.setPivotValue(projSelDTO.getPivotValue());
                        }
                    } else {
                        projSelDTO.setPivotValue(projSelDTO.getPivotValue().contains(Constant.Q) ? projSelDTO.getPivotValue().replace(Constant.Q, " ") : projSelDTO.getPivotValue().replace(Constant.S, " "));
                    }
                    if (Constant.BRAND_CAPS.equals(projSelDTO.getSupplementalLevelName())) {
                        list = dqLogic.getSuppBrandLevelValue(projSelDTO);
                    } else if (Constant.CONTRACT_SMALL.equals(projSelDTO.getSupplementalLevelName())) {
                        list = dqLogic.getSuppContractLevelValue(projSelDTO);
                    }
                }
            }

            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    Object[] obj = (Object[]) list.get(i);
                    if (i == 0) {
                        pivotGroupName = String.valueOf(obj[NumericConstants.SIX]);
                    }
                    if ((!pivotGroupName.equals(String.valueOf(obj[NumericConstants.SIX]))) || i == 0) {
                        pivotGroupName = String.valueOf(obj[NumericConstants.SIX]);
                        DiscountProjectionResultsDTO dtoValue = new DiscountProjectionResultsDTO();
                        dtoValue = getPivotMandatedDiscount(list, dtoValue, projSelDTO, curFlag);
                        if (projSelDTO.isFilterDdlb()) {
                            dtoValue.setParent(0);
                        }
                        pivotDiscount.add(dtoValue);
                    }

                }
            }
            return pivotDiscount;
        } catch (Exception e) {
            LOGGER.error(e);
            return Collections.emptyList();

        }
    }

    private List<String> getProjectionTotalCount(ProjectionSelectionDTO projSelDTO) {
        List<String> periods = new ArrayList<>();
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        int startPr = projSelDTO.getStartPeriod();
        int lastPr = frequencyDivision;
        if (!ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
            for (int yr = projSelDTO.getStartYear(); yr <= projSelDTO.getEndYear(); yr++) {
                if (yr == projSelDTO.getEndYear()) {
                    lastPr = projSelDTO.getEndPeriod();
                }
                for (int pr = startPr; pr <= lastPr; pr++) {
                    List<String> common = getCommonColumnHeader(frequencyDivision, yr, pr);
                    String commonHeader = common.get(1);
                    periods.add(commonHeader);
                }
                startPr = 1;
            }
        } else {
            for (int yr = projSelDTO.getStartYear(); yr <= projSelDTO.getEndYear(); yr++) {
                List<String> common = getCommonColumnHeader(frequencyDivision, yr, 0);
                String commonHeader = common.get(1);
                periods.add(commonHeader);
            }
        }

        return periods;
    }

    private DiscountProjectionResultsDTO getMDLevelValue(List list, DiscountProjectionResultsDTO dto, ProjectionSelectionDTO projSelDTO, boolean flag) {
        for (int i = 0; i < list.size(); i++) {

            Object[] obj = (Object[]) list.get(i);

            int frequencyDivision = projSelDTO.getFrequencyDivision();
            int year = Integer.valueOf(String.valueOf(obj[0]));
            int period = Integer.valueOf(String.valueOf(obj[1]));
            List<String> column = getCommonColumnHeader(frequencyDivision, year, period);
            String commonColumn = column.get(0);
            if (groupName.equalsIgnoreCase(String.valueOf(obj[NumericConstants.SIX]))) {

                dto.setGroup(StringUtils.EMPTY + String.valueOf(obj[NumericConstants.SIX]));
                dto.setLevelValue(StringUtils.EMPTY + String.valueOf(obj[NumericConstants.SIX]));
                dto.setParent(1);
                dto.setCurrentLevel(String.valueOf(obj[NumericConstants.SEVEN]));
                projSelDTO.setCurrentLevel(String.valueOf(obj[NumericConstants.SEVEN]));
                dto.setCurrentBrand(projSelDTO.getCurrentBrand());
                dto.setCurrentCustomer(projSelDTO.getCurrentCustomer());
                dto.setCurrentContract(projSelDTO.getCurrentContract());
                if (flag) {
                    dto.setDiscountLevel(Constant.MANDATED_DISCOUNT);
                } else {
                    dto.setDiscountLevel(Constant.MANDATED_SUPPLEMENTAL);
                }
                if (projSelDTO.getCurrentLevel().equals(Constant.BRAND_CAPS)) {
                    dto.setParent(0);
                    projSelDTO.setCurrentBrand(String.valueOf(obj[NumericConstants.FIVE]));
                    dto.setCurrentBrand(String.valueOf(obj[NumericConstants.FIVE]));
                }
                if (projSelDTO.getLevelNo() == NumericConstants.FOUR || Constant.CUSTOMER_SMALL.equalsIgnoreCase(String.valueOf(obj[NumericConstants.SEVEN]))) {
                    projSelDTO.setCurrentCustomer(String.valueOf(obj[NumericConstants.FIVE]));
                    dto.setCurrentCustomer(String.valueOf(obj[NumericConstants.FIVE]));
                }
                if (projSelDTO.getCurrentLevel().equalsIgnoreCase(Constant.CONTRACT_SMALL)) {
                    projSelDTO.setCurrentContract(String.valueOf(obj[NumericConstants.FIVE]));
                    dto.setCurrentContract(String.valueOf(obj[NumericConstants.FIVE]));
                }
                if (Constant.ACT.equalsIgnoreCase(String.valueOf(obj[NumericConstants.TEN]))) {
                    String actualsAmount = String.valueOf(obj[NumericConstants.EIGHT]);
                    String actualsRate = String.valueOf(obj[NumericConstants.NINE]);
                    String actualsRPU = String.valueOf(obj[NumericConstants.ELEVEN]);
                    actualsAmount = getFormatValue(NumericConstants.TWO, actualsAmount, CURRENCY);
                    actualsRate = getFormatValue(NumericConstants.TWO, actualsRate, PERCENTAGE);
                    actualsRPU = getFormatValue(NumericConstants.TWO, actualsRPU, CURRENCY);
                    dto.addStringProperties(commonColumn + ACTUALSRATE, actualsRate);
                    dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualsAmount);
                    dto.addStringProperties(commonColumn + ACTUALRPU, actualsRPU);

                } else {

                    String proAmount = String.valueOf(obj[NumericConstants.EIGHT]);
                    String prorate = String.valueOf(obj[NumericConstants.NINE]);
                    String projRPU = String.valueOf(obj[NumericConstants.ELEVEN]);
                    proAmount = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? proAmount : Constant.ZERO_STRING;
                    prorate = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? prorate : Constant.ZERO_STRING;
                    projRPU = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? projRPU : Constant.ZERO_STRING;
                    proAmount = getFormatValue(NumericConstants.TWO, proAmount, CURRENCY);
                    prorate = getFormatValue(NumericConstants.TWO, prorate, PERCENTAGE);
                    projRPU = getFormatValue(NumericConstants.TWO, projRPU, CURRENCY);
                    dto.addStringProperties(commonColumn + PROJECTIONSRATE, prorate);
                    dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount);
                    dto.addStringProperties(commonColumn + PROJECTEDRPU, projRPU);
                }
                if (projSelDTO.getLevelNo() == NumericConstants.FOUR) {
                    dto.setSupplementalLevelName(Constant.CONTRACT_SMALL);
                } else {
                    dto.setSupplementalLevelName(Constant.BRAND_CAPS);
                }
            }
        }
        return dto;
    }

    public DiscountProjectionResultsDTO getPivotMandatedDiscount(List list, DiscountProjectionResultsDTO dto, ProjectionSelectionDTO projSelDTO, boolean checkFlag) {
        int frequencyDivision;
        if (Constant.QUARTERLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
            frequencyDivision = NumericConstants.FOUR;
        } else if (Constant.MONTHLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
            frequencyDivision = NumericConstants.TWELVE;
        } else if (Constant.SEMIANNUALLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
            frequencyDivision = NumericConstants.TWO;
        } else {
            frequencyDivision = 1;
        }

        for (int i = 0; i < list.size(); i++) {
            Object[] obj = (Object[]) list.get(i);
            if (pivotGroupName.equals(String.valueOf(obj[NumericConstants.SIX]))) {
                int year = Integer.valueOf(String.valueOf(obj[0]));
                int period = Integer.valueOf(String.valueOf(obj[1]));
                List<String> column = getCommonColumnHeaderForMMDPRPivot(frequencyDivision, year, period);
                String commonColumn = column.get(1);
                if (projSelDTO.getGroup().equalsIgnoreCase(commonColumn)) {
                    projSelDTO.setNextFlag(projSelDTO.getGroup());
                    dto.setNextFlag(projSelDTO.getGroup());
                }
                if (checkFlag || Constant.MANDATED_DISCOUNT.equalsIgnoreCase(projSelDTO.getDiscountLevel())) {
                    dto.setDiscountLevel(Constant.MANDATED_DISCOUNT);
                } else {
                    dto.setDiscountLevel(Constant.MANDATED_SUPPLEMENTAL);
                }

                dto.setGroup(StringUtils.EMPTY + String.valueOf(obj[NumericConstants.SIX]));
                dto.setLevelValue(StringUtils.EMPTY + String.valueOf(obj[NumericConstants.SIX]));

                dto.setParent(1);
                dto.setCurrentLevel(String.valueOf(obj[NumericConstants.SEVEN]));
                projSelDTO.setCurrentLevel(String.valueOf(obj[NumericConstants.SEVEN]));
                dto.setCurrentBrand(projSelDTO.getCurrentBrand());
                dto.setCurrentCustomer(projSelDTO.getCurrentCustomer());
                dto.setCurrentContract(projSelDTO.getCurrentContract());
                dto.setPivotValue(projSelDTO.getPivotValue());
                if (projSelDTO.getCurrentLevel().equals(Constant.BRAND_CAPS)) {
                    dto.setParent(0);
                    dto.setCurrentBrand(String.valueOf(obj[NumericConstants.FIVE]));
                    projSelDTO.setCurrentBrand(String.valueOf(obj[NumericConstants.FIVE]));
                }
                if (projSelDTO.getCurrentLevel().equalsIgnoreCase(Constant.CUSTOMER_SMALL)) {
                    dto.setCurrentCustomer(String.valueOf(obj[NumericConstants.FIVE]));
                    projSelDTO.setCurrentCustomer(String.valueOf(obj[NumericConstants.FIVE]));
                    dto.setPivotValue(projSelDTO.getGroup());
                }
                if (projSelDTO.getCurrentLevel().equalsIgnoreCase(Constant.CONTRACT_SMALL)) {
                    dto.setCurrentContract(String.valueOf(obj[NumericConstants.FIVE]));
                    projSelDTO.setCurrentContract(String.valueOf(obj[NumericConstants.FIVE]));
                    dto.setNextFlag(projSelDTO.getNextFlag());
                }
                if (commonColumn.equalsIgnoreCase(projSelDTO.getNextFlag())) {
                    if (Constant.ACT.equalsIgnoreCase(String.valueOf(obj[NumericConstants.TEN]))) {
                        String actualsAmount = String.valueOf(obj[NumericConstants.EIGHT]);
                        String actualsRate = String.valueOf(obj[NumericConstants.NINE]);
                        String actualsRPU = String.valueOf(obj[NumericConstants.ELEVEN]);
                        actualsAmount = getFormatValue(NumericConstants.TWO, actualsAmount, CURRENCY);
                        actualsRate = getFormatValue(NumericConstants.TWO, actualsRate, PERCENTAGE);
                        actualsRPU = getFormatValue(NumericConstants.TWO, actualsRPU, CURRENCY);
                        dto.addStringProperties(Constant.TOTALDISCOUNT + ACTUALSRATE, actualsRate);
                        dto.addStringProperties(Constant.TOTALDISCOUNT + ACTUALSAMOUNT, actualsAmount);
                        dto.addStringProperties(Constant.TOTALDISCOUNT + ACTUALRPU, actualsRPU);

                    } else {

                        String proAmount = String.valueOf(obj[NumericConstants.EIGHT]);
                        String prorate = String.valueOf(obj[NumericConstants.NINE]);
                        String projRPU = String.valueOf(obj[NumericConstants.ELEVEN]);
                        proAmount = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? proAmount : Constant.ZERO_STRING;
                        prorate = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? prorate : Constant.ZERO_STRING;
                        projRPU = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? projRPU : Constant.ZERO_STRING;
                        proAmount = getFormatValue(NumericConstants.TWO, proAmount, CURRENCY);
                        prorate = getFormatValue(NumericConstants.TWO, prorate, PERCENTAGE);
                        projRPU = getFormatValue(NumericConstants.TWO, projRPU, CURRENCY);
                        dto.addStringProperties(Constant.TOTALDISCOUNT + PROJECTIONSRATE, prorate);
                        dto.addStringProperties(Constant.TOTALDISCOUNT + PROJECTIONSAMOUNT, proAmount);
                        dto.addStringProperties(Constant.TOTALDISCOUNT + PROJECTEDRPU, projRPU);
                    }
                }
            }
        }
        if (Constant.CUSTOMER_SMALL.equalsIgnoreCase(projSelDTO.getSupplementalLevelName())) {
            dto.setSupplementalLevelName(Constant.CONTRACT_SMALL);
            if (projSelDTO.isFilterDdlb()) {
                projSelDTO.setSupplementalLevelName("Empty");
            }
        } else if (Constant.CONTRACT_SMALL.equalsIgnoreCase(projSelDTO.getSupplementalLevelName())) {
            dto.setSupplementalLevelName(Constant.BRAND_CAPS);
        }
        return dto;
    }

    public List loadCustomerDdlb(ProjectionSelectionDTO projSelDTO, String str) {
        List list;
        SalesProjectionDAO dao = new SalesProjectionDAOImpl();
        StringBuilder customSql = new StringBuilder(StringUtils.EMPTY);
        String[] tempTableName = new String[NumericConstants.TWO];
        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
            tempTableName[0] = "CUST_RELATIONSHIP_BUILDER_SID";
            tempTableName[1] = "CUSTOMER_HIERARCHY_LEVEL";
        } else {
            tempTableName[0] = "PROD_RELATIONSHIP_BUILDER_SID";
            tempTableName[1] = "PRODUCT_HIERARCHY_LEVEL";
        }
        customSql.append("select Distinct cm." + str + ",cm.COMPANY_NAME  from CCP_DETAILS ccp,PROJECTION_DETAILS pd,COMPANY_MASTER cm \n"
                + "where \n"
                + "ccp.CCP_DETAILS_SID=pd.CCP_DETAILS_SID\n"
                + "and cm.COMPANY_MASTER_SID=ccp.COMPANY_MASTER_SID\n"
                + "and pd.PROJECTION_MASTER_SID= " + projSelDTO.getProjectionId());
        try {
            list = (List) dao.executeSelectQuery(customSql.toString());

            return list;
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex);
            return Collections.emptyList();
        }

    }

    public static String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getShortMonths();
        if (num == NumericConstants.TWELVE) {
            num = 0;
        }
        if (num >= 0 && num <= NumericConstants.ELEVEN) {
            month = months[num];
        }
        return month;
    }

}
