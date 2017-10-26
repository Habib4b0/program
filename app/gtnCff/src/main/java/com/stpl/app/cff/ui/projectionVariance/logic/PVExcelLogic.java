/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.projectionVariance.logic;


import com.stpl.app.cff.dto.PVSelectionDTO;
import com.stpl.app.cff.logic.CommonLogic;
import com.stpl.app.cff.ui.projectionVariance.dto.PVParameters;
import com.stpl.app.cff.ui.projectionVariance.dto.ProjectionVarianceDTO;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.util.Constants;
import static com.stpl.app.cff.util.Constants.CommonConstants.NULL;
import static com.stpl.app.cff.util.Constants.CommonConstants.VARIANCE;
import static com.stpl.app.cff.util.Constants.LabelConstants.PERCENT;
import static com.stpl.app.cff.util.Constants.LabelConstants.TOTAL;
import com.stpl.app.cff.util.HeaderUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;


/**
 *
 * @author Nandha Kumar
 */
public class PVExcelLogic {

    public static final Logger LOGGER = Logger.getLogger(PVExcelLogic.class);
    private ProjectionVarianceDTO exFacValue, exFacVar, exFacPer, demandValue, demandVar, demandPer, invWithValue, invWithVar,
            invWithPer, perDemandValue, perDemandVar, perDemandPer,  
            rpuValue, rpuVar, rpuPer, netSalesValue, netSalesVar,
            netSalesPer,  netProfitValue, netProfitVar, netProfitPer,adjDamandVale,adjDemandVar,adjdemandPer,iwDetialsValue,iwDetialsVar,iwDetialsPer,
            perExFacProductValue,perExFacProductVar,perExFacProductPer,perExFacCustValue,perExFacCustVariance,perExFacCustPer,perAdjDemandValue,perAdjDemandVar,perAdjDemandPer
            ,perInvWithSummaryValue,perInvWithSummaryVar,perInvWithSummaryPer,perIwDetialsValue,perIwDetialsVar,perIwDetialsPer,discountDollarValue,discountDollarVar,discountDollarPer
            ,discountPerValue,discountPerVar,discountPerPercent,contractSalesValue,contractSalesVar,contractSalesPer,contractUnitValue,contractUnitVar,contractUnitPer
            ,cogcValue,cogcVar,cogcPer,netExFactoryValue, netExFactoryVar, netExFactoryPer,discountPerExFactoryValue, discountPerExFactoryVar, discountPerExFactoryPer;

    private final Map<String, List<ProjectionVarianceDTO>> resultMap;
    private Map<String, List<List<ProjectionVarianceDTO>>> discountMapDetails;
    Map<String,Map<String,ProjectionVarianceDTO>> pivotDiscountMap=new HashMap<String,Map<String,ProjectionVarianceDTO>>();
    private final List<String> hierarchyKeys;
    private final List<String> tradingPartnerKeys;
    private final List<String> discountKeys;
    private final PVSelectionDTO selection;
    private static final DecimalFormat AMOUNT = new DecimalFormat("$#,##0.00");
    private static final DecimalFormat UNIT = new DecimalFormat("#,###");
    private static final DecimalFormat RATE_PER = new DecimalFormat("#,##0.00");
    private static final DecimalFormat AMOUNT_UNITS = new DecimalFormat("#,##0");
    private static final String ZERO = "0";
    private static final String CURRENT = "Current";
    private static final String ACTUAL = "Actual";
    private final int index = 6;
    private final int discindex = 8;
    private final int totalPrPos = 60;
    private final int detailPrPos = 60;
    private int frequencyDivision;
    private final int baseColumn_levelName_index = 0;
    private final List<Object> procRawList_total_period = new ArrayList();
    private final List<Object> procRawList_detail = new ArrayList();
    private final List<Object[]> procRawList_detail_discount = new ArrayList();
    private final List<Integer> priorList = new ArrayList();
     private boolean isTotal = false;
    private String levelFilterValue = StringUtils.EMPTY;
    private String groupFilterValue = StringUtils.EMPTY;
    private String viewValue = StringUtils.EMPTY;
    private static final String PRC_PROJ_RESULTS = "PRC_PROJECTION_RESULTS";
     Map<String,String> discountNameMap=new HashMap<String,String>();
    String NoNumber="NoNumber";
    String RETURNS="Returns";
    List<Object> pivotDiscountList = new ArrayList<Object>();
    List<ProjectionVarianceDTO> discountList = new ArrayList<ProjectionVarianceDTO>();
    private static final DecimalFormat RATE_PER_THREE = new DecimalFormat("#,##0.00");
    private static final DecimalFormat RATE = new DecimalFormat("#######0.00");
    private static final String DETAIL = "Detail";
    List<Object> pivotTotalList = new ArrayList<Object>();
    List<Integer> pivotPriorProjIdList = new ArrayList();
    private final Map<String, String> customView_relationship_hierarchy = new HashMap();
    PVParameters parameterDto;
    private boolean discountFlag;
    private boolean isCustomView;
    private static final String PERCENT = "%";
     private static final String PRC_CFF_RESULTS = "Prc_cff_results";
    private static final String C = "C";
    private static final String P = "P";
    Map<String, List<ProjectionVarianceDTO>> discountMap;
    ProjectionVarianceLogic logic = new ProjectionVarianceLogic();
    private static final String VAL = "Value";
    private static final String VAR = "Var";
    private static final String PER = "Per";
    private boolean actualBasis = false;
   
    public PVExcelLogic(Map<String, List<ProjectionVarianceDTO>> resultMap, PVSelectionDTO selection,
            List<String> hierarchyKeys, List<String> tradingPartnerKeys, List<String> discountKeys, PVParameters parameterDto,Map<String, List<ProjectionVarianceDTO>> discountMap,Map<String, List<List<ProjectionVarianceDTO>>> discountMapDetails) {
        this.resultMap = resultMap;
        this.selection = selection;
        this.hierarchyKeys = hierarchyKeys;
        this.tradingPartnerKeys = tradingPartnerKeys;
        this.discountKeys = discountKeys;
        this.parameterDto = parameterDto;
        this.discountMap=discountMap;
        this.discountMapDetails=discountMapDetails;
    }

    /**
     * Checking the selected level is "Total" if it is then need to hit the
     * procedure PRC_PROJECTION_RESULTS and customize the value
     *
     * Checking the selected level is "Detail" if it is then need to hit the
     * procedure "" and customize the value
     *
     * Finally the results will be updated in resultMap Which initalized in
     * constructor
     */
    public void getPVData() {
        resultMap.clear();
        hierarchyKeys.clear();
        tradingPartnerKeys.clear();
        priorList.clear();
        priorList.addAll(selection.getProjIdList());
        discountKeys.clear();
        isTotal = "Total".equalsIgnoreCase(selection.getLevel());
        discountFlag = !"Total Discount".equalsIgnoreCase(selection.getDiscountLevel());
        isCustomView = selection.isIsCustomHierarchy();
        actualBasis=("Actuals").equals(selection.getComparisonBasis());
        if (isCustomView) {
            selection.setHierarchyIndicator(StringUtils.EMPTY);
            customView_relationship_hierarchy.putAll(getGroup_customViewNM());
        }
         isRefreshNeeded(selection.getLevelFilterValue(), selection.getGroupFilter(), selection.getHierarchyIndicator(), selection.getFrequencyDivision());
        if (Constants.LabelConstants.PERIOD.toString().equalsIgnoreCase(selection.getPivotView())) {
            if (isTotal) {
                System.out.println("Total level");
                getTotalRawData();
                if (discountFlag) {
                    getTotalDiscount(selection);
                }
                 ProjectionVarianceDTO dto=new ProjectionVarianceDTO();
                 List<ProjectionVarianceDTO> totalList=logic.getCustPeriodVariance(procRawList_total_period, procRawList_total_period, selection, dto);
                 resultMap.put("Total",totalList);
                
                 if (discountFlag) {
                    discount_Customize();
                }
            } else {
                System.out.println("Detail level");
                executeProcedure_PRC_PV_SELECTION();
                if (discountFlag) {
                    parameterDto.setViewName("DETAIL_DISCOUNT");
                  calculateDiscount();
                }
                    calculateAndCustomize_detail_period(procRawList_detail);
                }
        } else {
            if (isTotal) {
                    getTotalRawDataPivot();
                    calculateAndCustomize_total_pivot();
            } else {
                executeProcedure_PRC_PV_SELECTION_PIVOT();
                if (discountFlag) {
                    parameterDto.setViewName("DETAIL_DISCOUNT");
                }
                customize_discount_pivot();
                if (isCustomView) {
                    calculateAndCustomize_detail_pivotForCustomView(procRawList_detail);
                } else {
                    calculateAndCustomize_detail_pivot(procRawList_detail);
                }
            }
        }
        }

    private boolean isRefreshNeeded(String levelFilterValue, String groupFilterValue, String viewValue, int freDiv) {
        boolean val = this.levelFilterValue.equals(levelFilterValue)
                && this.groupFilterValue.equals(groupFilterValue)
                && this.viewValue.equals(viewValue)
                && this.frequencyDivision == freDiv;
        this.levelFilterValue = levelFilterValue;
        this.groupFilterValue = groupFilterValue;
        this.viewValue = viewValue;
        this.frequencyDivision = freDiv;
        return !val;
    }


    private void calculateAndCustomize_detail_period(List<Object> rawList) {
        String appendedParentKey = "";
        for (Iterator<Object> it = rawList.listIterator(); it.hasNext();) {
            Object[] obj = (Object[]) it.next();
            String key = "";
            if (isCustomView) {
                key = obj[NumericConstants.TWO].toString();
                key = key + appendedParentKey;
                appendedParentKey = obj[obj.length - 1] == null ? "" : "$" + obj[obj.length - 1].toString();
            } else {
                key = obj[NumericConstants.TWO].toString();
//                key = key.substring(key.indexOf('-') + 1);
            }
//            String key = obj[NumericConstants.TWO].toString();
//            key = key.substring(key.indexOf('-') + 1);
            List<ProjectionVarianceDTO> pvList = resultMap.get(key);
            if (pvList == null) {
                //To check condition total or details values
                pvList = new ArrayList();
                getCustPeriodVariance_Details(pvList, selection, obj);
                if (isCustomView) {
                    customhierarchyAndTP_keys(obj, key, pvList);
                } else {
                    hierarchyAndTP_keys(obj, key, pvList);
                }
            } else {
                updateCustPeriodVariance_Details(pvList, selection, obj);
            }
        }
    }

    private void hierarchyAndTP_keys(Object[] obj, String key, List<ProjectionVarianceDTO> pvList) {

        hierarchyKeys.add(key);
        resultMap.put(key, pvList);
        if ("Customer".equalsIgnoreCase(String.valueOf(obj[baseColumn_levelName_index]))
                || "Trading Partner".equalsIgnoreCase(String.valueOf(obj[baseColumn_levelName_index]))) {
            tradingPartnerKeys.add(key);
        }
    }

    public String getFormattedValue(DecimalFormat FORMAT, String value) {
        if (value.contains(Constants.NULL)) {
            value = ZERO;
        } else {
            value = FORMAT.format(Double.valueOf(value));
        }
        return value;
    }

    public String isNull(String value) {
        if (value.contains(Constants.NULL)) {
            value = ZERO;
        }
        return value;
    }

    public void getTotalRawData() {
        String frequency = selection.getFrequency();
        List<Integer> projectionIdList = new ArrayList();
        procRawList_total_period.clear();
        priorList.clear();
        pivotDiscountList.clear();
        if (frequency.equals("Quarterly")) {
            frequency = "QUARTERLY";
        } else if (frequency.equals("Semi-Annually")) {
            frequency = "SEMI-ANNUAL";
        } else if (frequency.equals("Monthly")) {
            frequency = "MONTHLY";
        } else {
            frequency = "ANNUAL";
        }
        projectionIdList.add(selection.getCurrentProjId());
        projectionIdList.addAll(selection.getProjIdList());
        String projectionId = CommonUtils.CollectionToString(projectionIdList, false);
        Object[] orderedArg = {projectionId, frequency, "VARIANCE", null, null, null};
        List< Object[]> rawList = CommonLogic.callProcedure(PRC_CFF_RESULTS, orderedArg);
        procRawList_total_period.addAll(rawList);
        rawList.clear();
    }

      public void getTotalRawDataPivot() {
        String frequency = selection.getFrequency();
        List<Integer> projectionIdList = new ArrayList();
        procRawList_total_period.clear();
        priorList.clear();
        pivotDiscountList.clear();
        if (frequency.equals("Quarterly")) {
            frequency = "QUARTERLY";
        } else if (frequency.equals("Semi-Annually")) {
            frequency = "SEMI-ANNUAL";
        } else if (frequency.equals("Monthly")) {
            frequency = "MONTHLY";
        } else {
            frequency = "ANNUAL";
        }
        
         projectionIdList.add(selection.getCurrentProjId());
        for (Integer projId : selection.getProjIdList()) {
            projectionIdList.add(projId);
        }
        String projectionIds = CommonUtils.CollectionToString(projectionIdList, false);
         Object[] orderedArg = {projectionIds, frequency, "VARIANCE", null, null, null};
        List< Object[]> gtsResult = CommonLogic.callProcedure(PRC_CFF_RESULTS, orderedArg);
        procRawList_total_period.addAll(gtsResult); 
        if (!selection.getDiscountLevel().equals("!Total Discount")) {
        Object[] orderedArgDiscount = {projectionIds, frequency,"VARIANCE", "Pivot",null,null,selection.getDiscountLevel(),"EXCEL"};
        List<Object[]> discountsList = CommonLogic.callProcedure("Prc_cff_projection_results_discount", orderedArgDiscount);
        pivotDiscountList.addAll(discountsList); 
        }
    }
    
    
    public void executeProcedure_PRC_PV_SELECTION() {
        procRawList_detail.clear();
        procRawList_detail_discount.clear();
        List<Integer> projectionIdList = new ArrayList();
        projectionIdList.add(selection.getCurrentProjId());
        projectionIdList.addAll(selection.getProjIdList());
        String frequency = selection.getFrequency();
        String projectionId = CommonUtils.CollectionToString(projectionIdList, false);
        String indicator = selection.getHierarchyIndicator();
        List<Object[]> rawList = new ArrayList<Object[]>();
         List<Object[]> rawListDiscount = new ArrayList<Object[]>();
        if (indicator.equals(StringUtils.EMPTY)) {
            indicator = "N";
            int custId = selection.getCustomId();
            Object[] orderedArg = {projectionId, frequency, "VARIANCE", "Period", indicator, null, 0, custId, null,null,selection.getSessionDTO().getUserId(),selection.getSessionDTO().getSessionId()};
            rawList = CommonLogic.callProcedure("PRC_CFF_EXCEL_EXPORT", orderedArg);
        } else {
            Object[] orderedArg = {projectionId, frequency, "VARIANCE", "Period", indicator, null, selection.getExcelFilterLevelNo(), null, null,null,selection.getSessionDTO().getUserId(),selection.getSessionDTO().getSessionId()};

            rawList = CommonLogic.callProcedure("PRC_CFF_EXCEL_EXPORT", orderedArg);
        }
        procRawList_detail.addAll(rawList);
        if (discountFlag) {
            indicator = selection.getHierarchyIndicator();
            if (indicator.equals(StringUtils.EMPTY)) {
                indicator = "N";
                int custId = selection.getCustomId();
                Object[] orderedArgDiscountCustom = {projectionId, frequency, "VARIANCE", "Period", selection.getDiscountLevel(), selection.getDiscountLevel(), indicator,
                    null, selection.getExcelFilterLevelNo(), custId, null,selection.getSessionDTO().getUserId(),selection.getSessionDTO().getSessionId()};
                rawListDiscount = CommonLogic.callProcedure("PRC_CFF_DISCOUNT_EXCEL_EXPORT", orderedArgDiscountCustom);
            } else {
                Object[] orderedArgDiscount = {projectionId, frequency, "VARIANCE", "Period", selection.getDiscountLevel(), selection.getDiscountLevel(), selection.getHierarchyIndicator(),
                    null, selection.getExcelFilterLevelNo(), null, null,selection.getSessionDTO().getUserId(),selection.getSessionDTO().getSessionId()};
                rawListDiscount = CommonLogic.callProcedure("PRC_CFF_DISCOUNT_EXCEL_EXPORT", orderedArgDiscount);
            }
            procRawList_detail_discount.addAll(rawListDiscount);
        }
    }

     public void executeProcedure_PRC_PV_SELECTION_PIVOT() {
        procRawList_detail.clear();
        procRawList_detail_discount.clear();
        List<Integer> projectionIdList = new ArrayList();
         projectionIdList.add(selection.getCurrentProjId());
        projectionIdList.addAll(selection.getProjIdList());
        String frequency = selection.getFrequency();
        String projectionId = CommonUtils.CollectionToString(projectionIdList, false);
        String indicator = selection.getHierarchyIndicator();
        List<Object[]> rawList = new ArrayList<Object[]>();
        List<Object[]> rawListDiscount = new ArrayList<Object[]>();
         String order=StringUtils.EMPTY;
             String projectionOrder=selection.getProjectionPeriodOrder();
             if (projectionOrder.equals("Ascending")) {
                 order = "A";
             } else {
                 order = "D";
             }
         if (indicator.equals(StringUtils.EMPTY)) {
             indicator = "N";
             int custId = selection.getCustomId();
            
            
             Object[] orderedArg = {projectionId, frequency, "VARIANCE", "PIVOT", indicator,
                 null, selection.getExcelFilterLevelNo(), custId, null,order,selection.getSessionDTO().getUserId(),selection.getSessionDTO().getSessionId()};
             rawList = CommonLogic.callProcedure("PRC_CFF_EXCEL_EXPORT", orderedArg);
         } else {
             Object[] orderedArg = {projectionId, frequency, "VARIANCE", "PIVOT", selection.getHierarchyIndicator(),
                 null, selection.getExcelFilterLevelNo(), null, selection.getPivotStartDate(),order,selection.getSessionDTO().getUserId(),selection.getSessionDTO().getSessionId()};
             rawList = CommonLogic.callProcedure("PRC_CFF_EXCEL_EXPORT", orderedArg);
         }
         procRawList_detail.addAll(rawList);
         if (discountFlag) {
             indicator = selection.getHierarchyIndicator();
             if (indicator.equals(StringUtils.EMPTY)) {
                 indicator = "N";
                 int custId = selection.getCustomId();
                 Object[] orderedArgDiscount = {projectionId, frequency, "VARIANCE", "PIVOT", selection.getDiscountLevel(), selection.getDiscountLevel(), indicator,
                     null, selection.getExcelFilterLevelNo(), custId, selection.getPivotStartDate(), selection.getSessionDTO().getUserId(), selection.getSessionDTO().getSessionId()};
                 rawListDiscount = CommonLogic.callProcedure("PRC_CFF_DISCOUNT_EXCEL_EXPORT", orderedArgDiscount);
             } else {

                 Object[] orderedArgDiscount = {projectionId, frequency, "VARIANCE", "PIVOT", selection.getDiscountLevel(), selection.getDiscountLevel(), selection.getHierarchyIndicator(),
                     null, selection.getExcelFilterLevelNo(), null, selection.getPivotStartDate(), selection.getSessionDTO().getUserId(), selection.getSessionDTO().getSessionId()};
                 rawListDiscount = CommonLogic.callProcedure("PRC_CFF_DISCOUNT_EXCEL_EXPORT", orderedArgDiscount);
             }
             procRawList_detail_discount.addAll(rawListDiscount);

         }
   }
    
    private void calculateAndCustomize_total_pivot() {
        pivotPriorProjIdList.clear();
        for (Integer projId : selection.getProjIdList()) {
            pivotPriorProjIdList.add(projId);
        }
        List<ProjectionVarianceDTO> finalList = logic.getCustomizedPivotTotalResults(procRawList_total_period, pivotPriorProjIdList, selection, pivotDiscountList);
        resultMap.put("Total", finalList);
    }

 
    private void addList_pivot(List<ProjectionVarianceDTO> pvList, Object[] obj, ProjectionVarianceDTO frequencyBasedDTO, int indexTotal,String key,String group) {
           List<String> periodList = new ArrayList<>(selection.getPeriodList());
        int indexForTotal=indexTotal-1;
        int frequencyDivision = selection.getFrequencyDivision();
        if (isTotal) {
            ProjectionVarianceDTO total = new ProjectionVarianceDTO();
            total.setGroup("Projection Total");
            pvList.add(total);
        } else {
            ProjectionVarianceDTO detail = new ProjectionVarianceDTO();
            String groupName;
            if (isCustomView) {
                groupName = customView_relationship_hierarchy.get(obj[NumericConstants.TWO] == null ? StringUtils.EMPTY : obj[NumericConstants.TWO].toString());
                groupName = groupName == null ? StringUtils.EMPTY : groupName;
                detail.setHierarchyNo(obj[NumericConstants.TWO].toString());
                detail.setParentHierarchyNo(obj[obj.length - 1] == null ? null : obj[obj.length - 1].toString());
            } else {
                groupName = selection.getSessionDTO().getLevelValueDiscription(obj[NumericConstants.TWO].toString(), selection.getHierarchyIndicator());
            }
            detail.setGroup(groupName);
            pvList.add(detail);
        }
        String groupColumn=StringUtils.EMPTY;
        if(frequencyDivision==NumericConstants.TWELVE){
            
            groupColumn=group.replaceAll(" ", StringUtils.EMPTY);
            groupColumn=groupColumn.toLowerCase();
        }else{
            groupColumn=group.replaceAll(" ", StringUtils.EMPTY);
        }
        if(periodList.contains(groupColumn)){
        pvList.add(frequencyBasedDTO);
        //Ex-Factory-Sales
        if (selection.isVarExFacSales()) {
            if (selection.isColValue()) {
                calculateForTotal("ExFacValue", Constants.VALUE, obj, indexForTotal, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("ExFacVariance", Constants.VARIANCE, obj, indexForTotal, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("ExFacPer", Constants.CHANGE, obj, indexForTotal, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
          //Ex-Factory-Customer
        if (selection.isVarExFacSales()) {
            if (selection.isColValue()) {
                calculateForTotal("CustExFacValue", Constants.VALUE, obj, indexForTotal+NumericConstants.FORTY_SIX, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("CustExFacVariance", Constants.VARIANCE, obj, indexForTotal+NumericConstants.FORTY_SIX, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("CustExFacPer", Constants.CHANGE, obj, indexForTotal+NumericConstants.FORTY_SIX, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
        
        
        //Demand
        if (selection.isVarDemandSales()) {
            if (selection.isColValue()) {
                calculateForTotal("DemandSalesValue", Constants.VALUE, obj, indexForTotal + NumericConstants.TWENTY_SIX, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("DemandSalesVariance", Constants.VARIANCE, obj, indexForTotal + NumericConstants.TWENTY_SIX, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("DemandSalesPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.TWENTY_SIX, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
        
         //Adjusted Demand
        if (selection.isVarAdjDemand()) {
            if (selection.isColValue()) {
                calculateForTotal("AdjDemandValue", Constants.VALUE, obj, indexForTotal + NumericConstants.FORTY_FOUR, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("AdjDemandVariance", Constants.VARIANCE, obj, indexForTotal + NumericConstants.FORTY_FOUR, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("AdjDemandPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.FORTY_FOUR, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
        
        
        //Inventory Withdraw Summary
        if (selection.isVarInvSales()) {
            if (selection.isColValue()) {
                calculateForTotal("InvWithValue", Constants.VALUE, obj, indexForTotal + NumericConstants.TWENTY_EIGHT, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("InvWithVariance", Constants.VARIANCE, obj, indexForTotal + NumericConstants.TWENTY_EIGHT, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("InvWithPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.TWENTY_EIGHT, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
        
              //Inventory Withdraw Details
        if (selection.isVarIwDetails()) {
            if (selection.isColValue()) {
                calculateForTotal("InvWithDetailsValue", Constants.VALUE, obj, indexForTotal + NumericConstants.FORTY_EIGHT, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("InvWithDetailsVariance", Constants.VARIANCE, obj, indexForTotal + NumericConstants.FORTY_EIGHT, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("InvWithDetailsPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.FORTY_EIGHT, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
        
        
        //% of Ex-Factory product
        if (selection.isVarPerExFacSales()) {
            if (selection.isColValue()) {
                calculateForTotal("PerExFacValue", Constants.VALUE, obj, indexForTotal + NumericConstants.TEN, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColVariance()) {
                calculateForTotal("PerExFacVariance", Constants.VARIANCE, obj, indexForTotal + NumericConstants.TEN, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("PerExFacPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.TEN, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
         //% of Ex-Factory Cust
        if (selection.isVarPerExFacCustomer()) {
            if (selection.isColValue()) {
                calculateForTotal("PerCustExFacValue", Constants.VALUE, obj, indexForTotal + NumericConstants.FIFTY_TWO, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColVariance()) {
                calculateForTotal("PerCustExFacVariance", Constants.VARIANCE, obj, indexForTotal + NumericConstants.FIFTY_TWO, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("PerCustExFacPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.FIFTY_TWO, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
        
        //% of Demand
        if (selection.isVarPerDemandSales()) {
            if (selection.isColValue()) {
                calculateForTotal("PerDemandSalesValue", Constants.VALUE, obj, indexForTotal + NumericConstants.THIRTY, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColVariance()) {
                calculateForTotal("PerDemandSalesVariance", Constants.VARIANCE, obj, indexForTotal + NumericConstants.THIRTY, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("PerDemandSalesPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.THIRTY, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
          //% of Adjusted Demand
        if (selection.isVarPerAdjDemand()) {
            if (selection.isColValue()) {
                calculateForTotal("PerAdjDemandValue", Constants.VALUE, obj, indexForTotal + NumericConstants.FIFTY, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColVariance()) {
                calculateForTotal("PerAdjDemandSalesVariance", Constants.VARIANCE, obj, indexForTotal + NumericConstants.FIFTY, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("PerAdjDemandSalesPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.FIFTY, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
        
        //% of inventory Withdraw Summary
        if (selection.isVarPerInvSales()) {
            if (selection.isColValue()) {
                calculateForTotal("PerInvWithValue", Constants.VALUE, obj, indexForTotal + NumericConstants.THIRTY_TWO, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColVariance()) {
                calculateForTotal("PerInvWithVariance", Constants.VARIANCE, obj, indexForTotal + NumericConstants.THIRTY_TWO, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("PerInvWithPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.THIRTY_TWO, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
        
             //% of inventory Withdraw details
        if (selection.isVarPerIwDetails()) {
            if (selection.isColValue()) {
                calculateForTotal("PerInvWithDetailsValue", Constants.VALUE, obj, indexForTotal + NumericConstants.FIFTY_FOUR, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColVariance()) {
                calculateForTotal("PerInvWithDetailsVariance", Constants.VARIANCE, obj, indexForTotal + NumericConstants.FIFTY_FOUR, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("PerInvWithDetailsPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.FIFTY_FOUR, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
        
        //Contract Sales @ WAC
        if (selection.isVarContractsales()) {
            if (selection.isColValue()) {
                calculateForTotal("ContractSalesWACValue", Constants.VALUE, obj, indexForTotal + 2, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("ContractSalesWACVar", Constants.VARIANCE, obj, indexForTotal + 2, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("ContractSalesWACVarPer", Constants.CHANGE, obj, indexForTotal + 2, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Contract Units
        if (selection.isVarContractUnits()) {
            if (selection.isColValue()) {
                calculateForTotal("ContractUnitsValue", Constants.VALUE, obj, indexForTotal + NumericConstants.FOUR, frequencyBasedDTO, selection, UNIT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("ContractUnitsVar", Constants.VARIANCE, obj, indexForTotal + NumericConstants.FOUR, frequencyBasedDTO, selection, UNIT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("ContractUnitsPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.FOUR, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Discount $
        if (selection.isVarDisAmount()) {
            if (selection.isColValue()) {
                calculateForTotal("DiscountAmountValue", Constants.VALUE, obj, indexForTotal + NumericConstants.SIX, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("DiscountAmountVar", Constants.VARIANCE, obj, indexForTotal + NumericConstants.SIX, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("DiscountAmountPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.SIX, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Discount %
        if (selection.isVarDisRate()) {
            if (selection.isColValue()) {
                calculateForTotal("DiscountSalesValue", Constants.VALUE, obj, indexForTotal + NumericConstants.EIGHT, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColVariance()) {
                calculateForTotal("DiscountSalesVar", Constants.VARIANCE, obj, indexForTotal + NumericConstants.EIGHT, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("DiscountSalesPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.EIGHT, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //RPU
        if (selection.isVarRPU()) {
            if (selection.isColValue()) {
                calculateForTotal("RPUValue", Constants.VALUE, obj, indexForTotal + NumericConstants.THIRTY_EIGHT, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("RPUVariance", Constants.VARIANCE, obj, indexForTotal + NumericConstants.THIRTY_EIGHT, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("RPUPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.THIRTY_EIGHT, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //DiscountPerExFactory
        if (selection.isDiscountPerExFactory()) {
            if (selection.isColValue()) {
                calculateForTotal("DiscountPerExFactoryValue", Constants.VALUE, obj, indexForTotal + NumericConstants.FIFTY_EIGHT, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColVariance()) {
                calculateForTotal("DiscountPerExFactoryVar", Constants.VARIANCE, obj, indexForTotal + NumericConstants.FIFTY_EIGHT, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("DiscountPerExFactoryPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.FIFTY_EIGHT, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Net Sales 
        if (selection.isVarNetSales()) {
            if (selection.isColValue()) {
                calculateForTotal("NetSalesValue", Constants.VALUE, obj, indexForTotal + NumericConstants.TWENTY_FOUR, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("NetSalesVariance", Constants.VARIANCE, obj, indexForTotal + NumericConstants.TWENTY_FOUR, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("NetSalesPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.TWENTY_FOUR, frequencyBasedDTO, selection, RATE_PER);
            }
        }
         //NetSalesExFactory 
        if (selection.isNetSalesExFactory()) {
            if (selection.isColValue()) {
                calculateForTotal("NetSalesExFactoryValue", Constants.VALUE, obj, indexForTotal + NumericConstants.FIFTY_SIX, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColVariance()) {
                calculateForTotal("NetSalesExFactoryVariance", Constants.VARIANCE, obj, indexForTotal + NumericConstants.FIFTY_SIX, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("NetSalesExFactoryPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.FIFTY_SIX, frequencyBasedDTO, selection, RATE_PER);
            }
        }
            //COGS
        if (selection.isVarCOGC()) {
            if (selection.isColValue()) {
                calculateForTotal("COGCValue", Constants.VALUE, obj, indexForTotal+NumericConstants.FORTY, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("COGCVariance", Constants.VARIANCE, obj, indexForTotal+NumericConstants.FORTY, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("COGCPer", Constants.CHANGE, obj, indexForTotal+NumericConstants.FORTY, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Net Profit
        if (selection.isVarNetProfit()) {
            if (selection.isColValue()) {
                calculateForTotal("NetProfitValue", Constants.VALUE, obj, indexForTotal+NumericConstants.FORTY_TWO, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("NetProfitVariance", Constants.VARIANCE, obj, indexForTotal+NumericConstants.FORTY_TWO, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("NetProfitPer", Constants.CHANGE, obj, indexForTotal+NumericConstants.FORTY_TWO, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        String commonColumn=StringUtils.EMPTY;
       
        if (frequencyDivision == NumericConstants.FOUR) {
            commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
        } else if (frequencyDivision == NumericConstants.TWO) {
            commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
        } else if (frequencyDivision == 1) {
            commonColumn = String.valueOf(obj[0]);
        } else if (frequencyDivision == NumericConstants.TWELVE) {
            String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
            commonColumn = monthName.toLowerCase() + obj[0];
        }
        Map<String, ProjectionVarianceDTO> valueMap = pivotDiscountMap.get(key);
            if (valueMap != null) {
                ProjectionVarianceDTO dto = valueMap.get(commonColumn);
                if (dto != null) {
                    for (Object prop : dto.getProperties().keySet()) {
                        String value = String.valueOf(dto.getPropertyValue(prop));
                        frequencyBasedDTO.addStringProperties(prop, value);
                    }
                }
            }
        }


    }

    private void calculateForTotal(String variableName, String varibaleCat, Object[] obj, int index, ProjectionVarianceDTO pvDTO, PVSelectionDTO selection, DecimalFormat format) {
         boolean actualBasis = "Actuals".equalsIgnoreCase(selection.getComparisonBasis());
         boolean isPer = format.equals(RATE) || format.equals(RATE_PER) || format.equals(RATE_PER_THREE);
         String actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index - 1])));
         String currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));
        if (varibaleCat.equals(Constants.VALUE)) {
            String val = getFormattedValue(format, actualValue);
            pvDTO.addStringProperties(variableName + ACTUAL + selection.getCurrentProjId(), isPer ? val + PERCENT : val);
            val = getFormattedValue(format, currentValue);
            pvDTO.addStringProperties(variableName + CURRENT + selection.getCurrentProjId(),isPer ? val + PERCENT : val);
        } 
        if (actualBasis) {
            if (varibaleCat.equals(Constants.VARIANCE)) {
//                        for CURRENT
                String val = getVariance(actualValue, currentValue, format);
                pvDTO.addStringProperties(variableName + CURRENT + selection.getCurrentProjId(), isPer ? val + PERCENT : val);
            }
            if (varibaleCat.equals(Constants.CHANGE)) {
//                        for CURRENT
                String val = getPerChange(actualValue, currentValue, format);
                pvDTO.addStringProperties(variableName + CURRENT + selection.getCurrentProjId(), val + PERCENT);
            }
        }
        for (int j = 0; j < priorList.size(); j++) {
            String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * (NumericConstants.SIXTY))];
            if (varibaleCat.equals(Constants.VALUE)) {
                String val = getFormattedValue(format, isNull(priorVal));
                pvDTO.addStringProperties(variableName + priorList.get(j), isPer ? val + PERCENT : val);

            } else if (varibaleCat.equals(Constants.VARIANCE)) {
                
                if (actualBasis) {
                    String val = getVariance(actualValue, priorVal, format);
                    pvDTO.addStringProperties(variableName + priorList.get(j), isPer ? val + PERCENT : val);
                } else {
                    String val = getVariance(currentValue, priorVal, format);
                    pvDTO.addStringProperties(variableName + priorList.get(j), isPer ? val + PERCENT : val);
                }
            } else {
                
                if (actualBasis) {
                    String baseValu = getPerChange(actualValue, priorVal, RATE_PER);
                    pvDTO.addStringProperties(variableName + priorList.get(j), baseValu + PERCENT);
                } else {
                    String baseValu = getPerChange(currentValue, priorVal, RATE_PER);
                    pvDTO.addStringProperties(variableName + priorList.get(j), baseValu + PERCENT);
                }
            }
        }
    }

    private void calculateAndCustomize_detail_pivot(List<Object> rawList) {

        for (Iterator<Object> it = rawList.listIterator(); it.hasNext();) {
            Object[] obj = (Object[])it.next();
            String key = obj[NumericConstants.TWO].toString();
//            key = key.substring(key.indexOf('-') + 1);
            List<ProjectionVarianceDTO> pvList = resultMap.get(key);
            List<String> common = HeaderUtils.getCommonColumnHeaderForPV(frequencyDivision, Integer.valueOf(obj[0].toString()), Integer.valueOf(obj[1].toString()));
            String groupId = common.get(1);
            ProjectionVarianceDTO freVarianceDTO = new ProjectionVarianceDTO();
            freVarianceDTO.setGroup(groupId);
            if (pvList == null) {
                //To check condition total or details values
                pvList = new ArrayList();
                addList_pivot(pvList, obj, freVarianceDTO, index,key,groupId);
                hierarchyAndTP_keys(obj, key, pvList);
            } else {
                updateList_pivot(pvList, obj, freVarianceDTO, index,key,groupId);
            }
        }
    }

    private void updateList_pivot(List<ProjectionVarianceDTO> pvList, Object[] obj, ProjectionVarianceDTO frequencyBasedDTO, int indexTotal,String key,String group) {
        List<String> periodList = new ArrayList<>(selection.getPeriodList());
         String groupColumn=StringUtils.EMPTY;
        if(frequencyDivision==NumericConstants.TWELVE){
            groupColumn=group.replaceAll(" ", StringUtils.EMPTY);
            groupColumn=groupColumn.toLowerCase();
        }else{
            groupColumn=group.replaceAll(" ", StringUtils.EMPTY);
        }
         if(periodList.contains(groupColumn)){
        int indexForTotal=indexTotal-1;
        
        pvList.add(frequencyBasedDTO);
        //Ex-Factory-Sales
        if (selection.isVarExFacSales()) {
            if (selection.isColValue()) {
                calculateForTotal("ExFacValue", Constants.VALUE, obj, indexForTotal, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("ExFacVariance", Constants.VARIANCE, obj, indexForTotal, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("ExFacPer", Constants.CHANGE, obj, indexForTotal, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
       //Ex-Factory-Customer
        if (selection.isVarExFacCustomer()) {
            if (selection.isColValue()) {
                calculateForTotal("CustExFacValue", Constants.VALUE, obj, indexForTotal+NumericConstants.FORTY_SIX, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("CustExFacVariance", Constants.VARIANCE, obj, indexForTotal+NumericConstants.FORTY_SIX, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("CustExFacPer", Constants.CHANGE, obj, indexForTotal+NumericConstants.FORTY_SIX, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
        //Demand
        if (selection.isVarDemandSales()) {
            if (selection.isColValue()) {
                calculateForTotal("DemandSalesValue", Constants.VALUE, obj, indexForTotal + NumericConstants.TWENTY_SIX, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("DemandSalesVariance", Constants.VARIANCE, obj, indexForTotal + NumericConstants.TWENTY_SIX, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("DemandSalesPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.TWENTY_SIX, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
        //Adjusted Demand
        if (selection.isVarAdjDemand()) {
            if (selection.isColValue()) {
                calculateForTotal("AdjDemandValue", Constants.VALUE, obj, indexForTotal + NumericConstants.FORTY_FOUR, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("AdjDemandVariance", Constants.VARIANCE, obj, indexForTotal + NumericConstants.FORTY_FOUR, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("AdjDemandPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.FORTY_FOUR, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
        //Inventory Withdraw Sales
        if (selection.isVarInvSales()) {
            if (selection.isColValue()) {
                calculateForTotal("InvWithValue", Constants.VALUE, obj, indexForTotal + NumericConstants.TWENTY_EIGHT, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("InvWithVariance", Constants.VARIANCE, obj, indexForTotal + NumericConstants.TWENTY_EIGHT, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("InvWithPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.TWENTY_EIGHT, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
            //Inventory Withdraw Details
        if (selection.isVarIwDetails()) {
            if (selection.isColValue()) {
                calculateForTotal("InvWithDetailsValue", Constants.VALUE, obj, indexForTotal + NumericConstants.FORTY_EIGHT, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("InvWithDetailsVariance", Constants.VARIANCE, obj, indexForTotal + NumericConstants.FORTY_EIGHT, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("InvWithDetailsPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.FORTY_EIGHT, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
        //% of Ex-Factory
        if (selection.isVarPerExFacSales()) {
            if (selection.isColValue()) {
                calculateForTotal("PerExFacValue", Constants.VALUE, obj, indexForTotal + NumericConstants.TEN, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColVariance()) {
                calculateForTotal("PerExFacVariance", Constants.VARIANCE, obj, indexForTotal + NumericConstants.TEN, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("PerExFacPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.TEN, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
            //% of Ex-Factory Cust
        if (selection.isVarPerExFacCustomer()) {
            if (selection.isColValue()) {
                calculateForTotal("PerCustExFacValue", Constants.VALUE, obj, indexForTotal + NumericConstants.FIFTY_TWO, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColVariance()) {
                calculateForTotal("PerCustExFacVariance", Constants.VARIANCE, obj, indexForTotal + NumericConstants.FIFTY_TWO, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("PerCustExFacPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.FIFTY_TWO, frequencyBasedDTO, selection, RATE_PER);
            }
        }
         
        //% of Demand
        if (selection.isVarPerDemandSales()) {
            if (selection.isColValue()) {
                calculateForTotal("PerDemandSalesValue", Constants.VALUE, obj, indexForTotal + NumericConstants.THIRTY, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColVariance()) {
                calculateForTotal("PerDemandSalesVariance", Constants.VARIANCE, obj, indexForTotal + NumericConstants.THIRTY, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("PerDemandSalesPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.THIRTY, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
          //% of Adjusted Demand
        if (selection.isVarPerAdjDemand()) {
            if (selection.isColValue()) {
                calculateForTotal("PerAdjDemandValue", Constants.VALUE, obj, indexForTotal + NumericConstants.FIFTY, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColVariance()) {
                calculateForTotal("PerAdjDemandSalesVariance", Constants.VARIANCE, obj, indexForTotal + NumericConstants.FIFTY, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("PerAdjDemandSalesPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.FIFTY, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
        //% of inventory Withdraw Sales
        if (selection.isVarPerInvSales()) {
            if (selection.isColValue()) {
                calculateForTotal("PerInvWithValue", Constants.VALUE, obj, indexForTotal + NumericConstants.THIRTY_TWO, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColVariance()) {
                calculateForTotal("PerInvWithVariance", Constants.VARIANCE, obj, indexForTotal + NumericConstants.THIRTY_TWO, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("PerInvWithPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.THIRTY_TWO, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
         //% of inventory Withdraw details
        if (selection.isVarPerIwDetails()) {
            if (selection.isColValue()) {
                calculateForTotal("PerInvWithDetailsValue", Constants.VALUE, obj, indexForTotal + NumericConstants.FIFTY_FOUR, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColVariance()) {
                calculateForTotal("PerInvWithDetailsVariance", Constants.VARIANCE, obj, indexForTotal + NumericConstants.FIFTY_FOUR, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("PerInvWithDetailsPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.FIFTY_FOUR, frequencyBasedDTO, selection, RATE_PER);
            }
        }
       
        //Contract Sales @ WAC
        if (selection.isVarContractsales()) {
            if (selection.isColValue()) {
                calculateForTotal("ContractSalesWACValue", Constants.VALUE, obj, indexForTotal + 2, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("ContractSalesWACVar", Constants.VARIANCE, obj, indexForTotal + 2, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("ContractSalesWACVarPer", Constants.CHANGE, obj, indexForTotal + 2, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Contract Units
        if (selection.isVarContractUnits()) {
            if (selection.isColValue()) {
                calculateForTotal("ContractUnitsValue", Constants.VALUE, obj, indexForTotal + NumericConstants.FOUR, frequencyBasedDTO, selection, UNIT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("ContractUnitsVar", Constants.VARIANCE, obj, indexForTotal + NumericConstants.FOUR, frequencyBasedDTO, selection, UNIT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("ContractUnitsPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.FOUR, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
        //Discount $
        if (selection.isVarDisAmount()) {
            if (selection.isColValue()) {
                calculateForTotal("DiscountAmountValue", Constants.VALUE, obj, indexForTotal + NumericConstants.SIX, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("DiscountAmountVar", Constants.VARIANCE, obj, indexForTotal + NumericConstants.SIX, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("DiscountAmountPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.SIX, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Discount %
        if (selection.isVarDisRate()) {
            if (selection.isColValue()) {
                calculateForTotal("DiscountSalesValue", Constants.VALUE, obj, indexForTotal + NumericConstants.EIGHT, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColVariance()) {
                calculateForTotal("DiscountSalesVar", Constants.VARIANCE, obj, indexForTotal + NumericConstants.EIGHT, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("DiscountSalesPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.EIGHT, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //RPU
        if (selection.isVarRPU()) {
            if (selection.isColValue()) {
                calculateForTotal("RPUValue", Constants.VALUE, obj,  indexForTotal + NumericConstants.THIRTY_EIGHT, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("RPUVariance", Constants.VARIANCE, obj, indexForTotal + NumericConstants.THIRTY_EIGHT, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("RPUPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.THIRTY_EIGHT, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //DiscountPerExFactory
        if (selection.isDiscountPerExFactory()) {
            if (selection.isColValue()) {
                calculateForTotal("DiscountPerExFactoryValue", Constants.VALUE, obj, indexForTotal + NumericConstants.FIFTY_EIGHT, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColVariance()) {
                calculateForTotal("DiscountPerExFactoryVar", Constants.VARIANCE, obj, indexForTotal + NumericConstants.FIFTY_EIGHT, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("DiscountPerExFactoryPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.FIFTY_EIGHT, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Net Sales 
        if (selection.isVarNetSales()) {
            if (selection.isColValue()) {
                calculateForTotal("NetSalesValue", Constants.VALUE, obj,indexForTotal+NumericConstants.TWENTY_FOUR , frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("NetSalesVariance", Constants.VARIANCE, obj, indexForTotal+NumericConstants.TWENTY_FOUR, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("NetSalesPer", Constants.CHANGE, obj, indexForTotal+NumericConstants.TWENTY_FOUR, frequencyBasedDTO, selection, RATE_PER);
            }
        }
         //NetSalesExFactory 
        if (selection.isNetSalesExFactory()) {
            if (selection.isColValue()) {
                calculateForTotal("NetSalesExFactoryValue", Constants.VALUE, obj, indexForTotal + NumericConstants.FIFTY_SIX, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColVariance()) {
                calculateForTotal("NetSalesExFactoryVariance", Constants.VARIANCE, obj, indexForTotal + NumericConstants.FIFTY_SIX, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("NetSalesExFactoryPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.FIFTY_SIX, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //COGS
        if (selection.isVarCOGC()) {
            if (selection.isColValue()) {
                calculateForTotal("COGCValue", Constants.VALUE, obj, indexForTotal+NumericConstants.FORTY, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("COGCVariance", Constants.VARIANCE, obj, indexForTotal+NumericConstants.FORTY, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("COGCPer", Constants.CHANGE, obj, indexForTotal+NumericConstants.FORTY, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Net Profit
        if (selection.isVarNetProfit()) {
            if (selection.isColValue()) {
                calculateForTotal("NetProfitValue", Constants.VALUE, obj, indexForTotal+NumericConstants.FORTY_TWO, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("NetProfitVariance", Constants.VARIANCE, obj, indexForTotal+NumericConstants.FORTY_TWO, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("NetProfitPer", Constants.CHANGE, obj, indexForTotal+NumericConstants.FORTY_TWO, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
            String commonColumn = StringUtils.EMPTY;
            if (frequencyDivision == NumericConstants.FOUR) {
                commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
            } else if (frequencyDivision == NumericConstants.TWO) {
                commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
            } else if (frequencyDivision == 1) {
                commonColumn = String.valueOf(obj[0]);
            } else if (frequencyDivision == NumericConstants.TWELVE) {
                String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                commonColumn = monthName.toLowerCase() + obj[0];
            }
            Map<String, ProjectionVarianceDTO> valueMap = pivotDiscountMap.get(key);
            if (valueMap != null) {
                ProjectionVarianceDTO dto = valueMap.get(commonColumn);
                if (dto != null) {
                    for (Object prop : dto.getProperties().keySet()) {
                        String value = String.valueOf(dto.getPropertyValue(prop));
                        frequencyBasedDTO.addStringProperties(prop, value);
                    }
                }
            }
        }
        
    }

    public ProjectionVarianceDTO getCustomizedPPA(List<Object> dataList, PVSelectionDTO projSelDTO, boolean isDetail, int index, boolean isPer, String parentGroup, String ppaGroup) {
        LOGGER.debug("Inside getCustomizedPPA");
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        pvDTO.setLevelValue(projSelDTO.getLevelValue());
        pvDTO.setLevelNo(projSelDTO.getLevelNo());
        pvDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        pvDTO.setParent(0);
        pvDTO.setGroup(ppaGroup);
        List<Integer> priorList = new ArrayList<Integer>(projSelDTO.getProjIdList());
        if (dataList != null && !dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                if (isDetail) {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    String column1 = StringUtils.EMPTY;
                    if (frequencyDivision == NumericConstants.FOUR) {
                        commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == NumericConstants.TWO) {
                        commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == NumericConstants.TWELVE) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                    column1 = commonColumn + CURRENT + projSelDTO.getCurrentProjId();
                    String value = StringUtils.EMPTY + obj[NumericConstants.FIVE];
                    if (projSelDTO.getSales().contains("SALES")) {
                        value = getFormattedValue(AMOUNT, value);
                    } else if (projSelDTO.getSales().contains("RATE")) {
                        value = getFormattedValue(RATE_PER_THREE, value);
                    }
                    pvDTO.addStringProperties(column1, isPer ? value + PERCENT : value);
                    for (int j = 0; j < priorList.size(); j++) {
                        column1 = commonColumn + priorList.get(j);
                        String pValue = StringUtils.EMPTY + obj[NumericConstants.FIVE + ((j + 1) * NumericConstants.THREE)];
                        if (projSelDTO.getSales().contains("SALES")) {
                            pValue = getFormattedValue(AMOUNT, pValue);
                        } else if (projSelDTO.getSales().contains("RATE")) {
                            pValue = getFormattedValue(RATE_PER_THREE, pValue);
                        }
                        pvDTO.addStringProperties(column1, isPer ? pValue + PERCENT : pValue);
                    }
                } else {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == NumericConstants.FOUR) {
                        commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == NumericConstants.TWO) {
                        commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == NumericConstants.TWELVE) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                    if (projSelDTO.getVarIndicator().equals(Constants.VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + projSelDTO.getCurrentProjId(), isPer ? baseValue + PERCENT : baseValue);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (projSelDTO.getVarIndicator().equals(Constants.VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * NumericConstants.TWENTY_ONE)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);
                        } else if (projSelDTO.getVarIndicator().equals(Constants.VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * NumericConstants.TWENTY_ONE)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);
                        } else {
                            String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * NumericConstants.TWENTY_ONE)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                        }
                    }
                }
            }
        }
        List list = resultMap.get(parentGroup);
        if (list != null) {
            list.add(pvDTO);
        } else {
            List<ProjectionVarianceDTO> dto = new ArrayList<ProjectionVarianceDTO>();
            dto.add(pvDTO);
            resultMap.put(parentGroup, dto);
        }

        LOGGER.debug("Ending getCustomizedPPA");
        return pvDTO;
    }

    public void getCustomizedReturns(List<Object> dataList, PVSelectionDTO projSelDTO, boolean isDetail, int index, boolean isPer, String parentGroup, String returnsGroup) {
        LOGGER.debug("Inside getCustomizedReturns");
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        pvDTO.setLevelValue(projSelDTO.getLevelValue());
        pvDTO.setLevelNo(projSelDTO.getLevelNo());
        pvDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        pvDTO.setParent(0);
        pvDTO.setGroup(returnsGroup);
        List<Integer> priorList = new ArrayList<Integer>(projSelDTO.getProjIdList());
        if (dataList != null && !dataList.isEmpty()) {

            for (int i = 0; i < dataList.size(); i++) {
                if (isDetail) {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    String column1 = StringUtils.EMPTY;
                    if (frequencyDivision == NumericConstants.FOUR) {
                        commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == NumericConstants.TWO) {
                        commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == NumericConstants.TWELVE) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                    column1 = commonColumn + CURRENT + projSelDTO.getCurrentProjId();
                    String value = StringUtils.EMPTY + obj[NumericConstants.FOUR];
                    if (projSelDTO.getSales().contains("SALES")) {
                        value = getFormattedValue(AMOUNT, value);
                    } else if (projSelDTO.getSales().contains("RATE")) {
                        value = getFormattedValue(RATE, value);
                    }
                    pvDTO.addStringProperties(column1, isPer ? value + PERCENT : value);
                    for (int j = 0; j < priorList.size(); j++) {
                        column1 = commonColumn + priorList.get(j);
                        String pValue = StringUtils.EMPTY + obj[NumericConstants.FOUR + ((j + 1) * NumericConstants.THREE)];
                        if (projSelDTO.getSales().contains("SALES")) {
                            pValue = getFormattedValue(AMOUNT, pValue);
                        } else if (projSelDTO.getSales().contains("RATE")) {
                            pValue = getFormattedValue(RATE, pValue);
                        }
                        pvDTO.addStringProperties(column1, isPer ? pValue + PERCENT : pValue);
                    }
                } else {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == NumericConstants.FOUR) {
                        commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == NumericConstants.TWO) {
                        commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == NumericConstants.TWELVE) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                    if (projSelDTO.getVarIndicator().equals(Constants.VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + projSelDTO.getCurrentProjId(), isPer ? baseValue + PERCENT : baseValue);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (projSelDTO.getVarIndicator().equals(Constants.VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * NumericConstants.TWENTY_ONE)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);
                        } else if (projSelDTO.getVarIndicator().equals(Constants.VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * NumericConstants.TWENTY_ONE)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);
                        } else {
                            String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * NumericConstants.TWENTY_ONE)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                        }
                    }
                }
            }
        }
        LOGGER.debug("Ending getCustomizedReturns");
        List list = resultMap.get(parentGroup);
        if (list != null) {
            list.add(pvDTO);
        } else {
            List<ProjectionVarianceDTO> returnsDTO = new ArrayList<ProjectionVarianceDTO>();
            returnsDTO.add(pvDTO);
            resultMap.put(parentGroup, returnsDTO);
        }
    }

    public void discount_Customize() {
       
        List<ProjectionVarianceDTO> discountDollarValuelist = new ArrayList<>();
        List<ProjectionVarianceDTO> discountDollarVariancelist = new ArrayList<>();
        List<ProjectionVarianceDTO> discountDollarPercentlist = new ArrayList<>();

        List<ProjectionVarianceDTO> discountperValuelist = new ArrayList<>();
        List<ProjectionVarianceDTO> discountperVariancelist = new ArrayList<>();
        List<ProjectionVarianceDTO> discountperPercentlist = new ArrayList<>();

        List<ProjectionVarianceDTO> rpuValueList = new ArrayList<>();
        List<ProjectionVarianceDTO> rpuVarianceList = new ArrayList<>();
        List<ProjectionVarianceDTO> rpuPercentList = new ArrayList<>();
        
        List<ProjectionVarianceDTO> discountperExfacValuelist = new ArrayList<>();
        List<ProjectionVarianceDTO> discountperExfacVariancelist = new ArrayList<>();
        List<ProjectionVarianceDTO> discountperExfacPercentlist = new ArrayList<>();
        
        discountDollarValuelist = getCustomisedDiscount(pivotDiscountList, selection, NumericConstants.FOUR, NumericConstants.EIGHT, Boolean.FALSE, Constants.VALUE);
        discountDollarVariancelist = getCustomisedDiscount(pivotDiscountList, selection, NumericConstants.FOUR, NumericConstants.EIGHT, Boolean.FALSE, VARIANCE.getConstant());
        discountDollarPercentlist = getCustomisedDiscount(pivotDiscountList, selection, NumericConstants.FOUR, NumericConstants.EIGHT, Boolean.TRUE, PERCENT);

        discountperValuelist = getCustomisedDiscount(pivotDiscountList, selection, NumericConstants.SIX, NumericConstants.EIGHT, Boolean.TRUE, Constants.VALUE);
        discountperVariancelist = getCustomisedDiscount(pivotDiscountList, selection, NumericConstants.SIX, NumericConstants.EIGHT, Boolean.TRUE, VARIANCE.getConstant());
        discountperPercentlist = getCustomisedDiscount(pivotDiscountList, selection, NumericConstants.SIX, NumericConstants.EIGHT, Boolean.TRUE, PERCENT);

        rpuValueList = getCustomisedDiscount(pivotDiscountList, selection, NumericConstants.EIGHT, NumericConstants.EIGHT, Boolean.FALSE, Constants.VALUE);
        rpuVarianceList = getCustomisedDiscount(pivotDiscountList, selection, NumericConstants.EIGHT, NumericConstants.EIGHT, Boolean.FALSE, VARIANCE.getConstant());
        rpuPercentList = getCustomisedDiscount(pivotDiscountList, selection, NumericConstants.EIGHT, NumericConstants.EIGHT, Boolean.TRUE, PERCENT);
        
        discountperExfacValuelist = getCustomisedDiscount(pivotDiscountList, selection, NumericConstants.TEN, NumericConstants.EIGHT, Boolean.TRUE, Constants.VALUE);
        discountperExfacVariancelist = getCustomisedDiscount(pivotDiscountList, selection, NumericConstants.TEN, NumericConstants.EIGHT, Boolean.TRUE, VARIANCE.getConstant());
        discountperExfacPercentlist = getCustomisedDiscount(pivotDiscountList, selection, NumericConstants.TEN, NumericConstants.EIGHT, Boolean.TRUE, PERCENT);

        discountMap.put("discountDollar", discountDollarValuelist);
        discountMap.put("discountDollarVariance", discountDollarVariancelist);
        discountMap.put("discountDollarPercent", discountDollarPercentlist);

        discountMap.put("discountPervalue", discountperValuelist);
        discountMap.put("discountPervariance", discountperVariancelist);
        discountMap.put("discountPerPercent", discountperPercentlist);

        discountMap.put("rpuValue", rpuValueList);
        discountMap.put("rpuVariance", rpuVarianceList);
        discountMap.put("rpuPercent", rpuPercentList);
        
        discountMap.put("discountPerExfacvalue", discountperExfacValuelist);
        discountMap.put("discountPerExfacvariance", discountperExfacVariancelist);
        discountMap.put("discountPerExfacPercent", discountperExfacPercentlist);
       
    }

    public void getTotalPivotVariance(PVSelectionDTO selection) {
        String frequency = selection.getFrequency();
        String discountId = CommonUtils.CollectionToString(selection.getDiscountNoList(), false);
        List<String> projectionIdList = new ArrayList<String>();
        pivotTotalList = new ArrayList<Object>();
        pivotPriorProjIdList = new ArrayList<Integer>();
        if (frequency.equals("Quarterly")) {
            frequency = "QUARTERLY";
        } else if (frequency.equals("Semi-Annually")) {
            frequency = "SEMI-ANNUAL";
        } else if (frequency.equals("Monthly")) {
            frequency = "MONTHLY";
        } else {
            frequency = "ANNUAL";
        }
        projectionIdList.add(String.valueOf(selection.getCurrentProjId()));
        for (Integer projId : selection.getProjIdList()) {
            projectionIdList.add(String.valueOf(projId));
            pivotPriorProjIdList.add(projId);
        }
        String projectionId = CommonUtils.CollectionToString(projectionIdList, false);
       
        Object[] orderedArg = {projectionId, frequency, discountId, "VARIANCE", selection.getSessionId(), selection.getUserId(), "PIVOT"};
        List< Object[]> gtsResult = CommonLogic.callProcedure(PRC_PROJ_RESULTS, orderedArg);
        pivotTotalList.addAll(gtsResult);
    }

    /**
     * Discount procedure
     *
     * @param projSelDTO
     */
    void getTotalDiscount(PVSelectionDTO projSelDTO) {
        pivotDiscountList.clear();
        String frequency = projSelDTO.getFrequency();
        List<String> projectionIdList = new ArrayList<String>();
        pivotDiscountList = new ArrayList<Object>();
        if (frequency.equals("Quarterly")) {
            frequency = "QUARTERLY";
        } else if (frequency.equals("Semi-Annually")) {
            frequency = "SEMI-ANNUAL";
        } else if (frequency.equals("Monthly")) {
            frequency = "MONTHLY";
        } else {
            frequency = "ANNUAL";
        }
        projectionIdList.add(String.valueOf(projSelDTO.getCurrentProjId()));
        for (Integer projId : projSelDTO.getProjIdList()) {
            projectionIdList.add(String.valueOf(projId));
        }
        String projectionId = CommonUtils.CollectionToString(projectionIdList, false);
 
        Object[] orderedArg = {projectionId, frequency,"VARIANCE", "Period",null,null,selection.getDiscountLevel(),"EXCEL"};
        List<Object[]> discountsList = CommonLogic.callProcedure("Prc_cff_projection_results_discount", orderedArg);
        pivotDiscountList.addAll(discountsList);

    }

    private Map<String, String> getGroup_customViewNM() {
        Map<String, List> relationshipLevelDetailsMap = selection.getSessionDTO().getHierarchyLevelDetails();
        Map<String, String> customViewMap = new HashMap<>();
        Set keys = relationshipLevelDetailsMap.keySet();
    
        for (Iterator i = keys.iterator(); i.hasNext();) {
            String key = (String) i.next();
            String value = (String) relationshipLevelDetailsMap.get(key).get(0).toString();
            customViewMap.put(key, value);
        }
        return customViewMap;
    }
    
    public void getCustPeriodVariance(final List<Object> gtsList, final List<Object> dataList, final PVSelectionDTO pvsdto, final ProjectionVarianceDTO parentDto) {
        List<ProjectionVarianceDTO> projectionVarianceDTO = new ArrayList<ProjectionVarianceDTO>();
        ProjectionVarianceDTO exFacValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO exFacVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO exFacPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO demandValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO demandVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO demandPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO invWithValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO invWithVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO invWithPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO salesValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO salesVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO salesPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO custExFacValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO custExFacVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO custExFacPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO adjDemandValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO adjDemandVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO adjDemandPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO iwDetialsValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO iwDetialsVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO iwDetialsPer = new ProjectionVarianceDTO();

        boolean isDetail = false;
        if (pvsdto.getLevel().equals(DETAIL)) {
            isDetail = true;
        } else {
            ProjectionVarianceDTO dto = new ProjectionVarianceDTO();
            dto.setGroup("CFF Total");
            projectionVarianceDTO.add(dto);
        }
        // GTS and Sales for POB starts
        if (isDetail) {
            //Ex fac for detail start
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                exFacValue = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), gtsList, gtsList, NumericConstants.TWO, NumericConstants.THIRTY, NumericConstants.TWO, NumericConstants.THIRTY, pvsdto, AMOUNT);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                exFacVar = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), gtsList, gtsList, NumericConstants.TWO, NumericConstants.THIRTY, NumericConstants.TWO, NumericConstants.THIRTY, pvsdto, AMOUNT);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                exFacPer = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), gtsList, gtsList, NumericConstants.TWO, NumericConstants.THIRTY, NumericConstants.TWO, NumericConstants.THIRTY, pvsdto, RATE);
            }

            // Customer ExFac Sales for detail start 
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                custExFacValue = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), gtsList, gtsList, NumericConstants.TWENTY_SEVEN, NumericConstants.THIRTY, NumericConstants.TWENTY_SEVEN, NumericConstants.THIRTY, pvsdto, AMOUNT);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                custExFacVar = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), gtsList, gtsList, NumericConstants.TWENTY_SEVEN, NumericConstants.THIRTY, NumericConstants.TWENTY_SEVEN, NumericConstants.THIRTY, pvsdto, AMOUNT);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                custExFacPer = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), gtsList, gtsList, NumericConstants.TWENTY_SEVEN, NumericConstants.THIRTY, NumericConstants.TWENTY_SEVEN, NumericConstants.THIRTY, pvsdto, RATE);
            }

            //Demand - start
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                demandValue = getCommonCustomizedDTO(Constants.PVVariables.DEMAND.toString(), gtsList, gtsList, NumericConstants.FIFTEEN, NumericConstants.THIRTY, NumericConstants.FIFTEEN, NumericConstants.THIRTY, pvsdto, AMOUNT);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                demandVar = getCommonCustomizedDTO(Constants.PVVariables.DEMAND.toString(), gtsList, gtsList, NumericConstants.FIFTEEN, NumericConstants.THIRTY, NumericConstants.FIFTEEN, NumericConstants.THIRTY, pvsdto, AMOUNT);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                demandPer = getCommonCustomizedDTO(Constants.PVVariables.DEMAND.toString(), gtsList, gtsList, NumericConstants.FIFTEEN, NumericConstants.THIRTY, NumericConstants.FIFTEEN, NumericConstants.THIRTY, pvsdto, RATE);
            }

            // Adjusted Demand  start 
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                adjDemandValue = getCommonCustomizedDTO(Constants.PVVariables.ADJUSTED_DEMAND.toString(), gtsList, gtsList, NumericConstants.TWENTY_SIX, NumericConstants.THIRTY, NumericConstants.TWENTY_SIX, NumericConstants.THIRTY, pvsdto, AMOUNT);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                adjDemandVar = getCommonCustomizedDTO(Constants.PVVariables.ADJUSTED_DEMAND.toString(), gtsList, gtsList, NumericConstants.TWENTY_SIX, NumericConstants.THIRTY, NumericConstants.TWENTY_SIX, NumericConstants.THIRTY, pvsdto, AMOUNT);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                adjDemandPer = getCommonCustomizedDTO(Constants.PVVariables.ADJUSTED_DEMAND.toString(), gtsList, gtsList, NumericConstants.TWENTY_SIX, NumericConstants.THIRTY, NumericConstants.TWENTY_SIX, NumericConstants.THIRTY, pvsdto, RATE);
            }
  
            //Inv with drawal sale for detail - start
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                invWithValue = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_SUMMARY.toString(), gtsList, gtsList, NumericConstants.SIXTEEN, NumericConstants.THIRTY, NumericConstants.SIXTEEN, NumericConstants.THIRTY, pvsdto, AMOUNT);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                invWithVar = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_SUMMARY.toString(), gtsList, gtsList, NumericConstants.SIXTEEN, NumericConstants.THIRTY, NumericConstants.SIXTEEN, NumericConstants.THIRTY, pvsdto, AMOUNT);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                invWithPer = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_SUMMARY.toString(), gtsList, gtsList, NumericConstants.SIXTEEN, NumericConstants.THIRTY, NumericConstants.SIXTEEN, NumericConstants.THIRTY, pvsdto, RATE);
            }

            // Inventary withdrawal Details for detail start 
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                iwDetialsValue = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_DETAILS.toString(), gtsList, gtsList, NumericConstants.TWENTY_EIGHT, NumericConstants.THIRTY, NumericConstants.TWENTY_EIGHT, NumericConstants.THIRTY, pvsdto, AMOUNT);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                iwDetialsVar = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_DETAILS.toString(), gtsList, gtsList, NumericConstants.TWENTY_EIGHT, NumericConstants.THIRTY, NumericConstants.TWENTY_EIGHT, NumericConstants.THIRTY, pvsdto, AMOUNT);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                iwDetialsPer = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_DETAILS.toString(), gtsList, gtsList, NumericConstants.TWENTY_EIGHT, NumericConstants.THIRTY, NumericConstants.TWENTY_EIGHT, NumericConstants.THIRTY, pvsdto, RATE);
            }

            //Sales for POB
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                salesValue = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), gtsList, dataList, NumericConstants.THREE, NumericConstants.THIRTY, NumericConstants.THREE, NumericConstants.THIRTY, pvsdto, AMOUNT);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                salesVar = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), gtsList, dataList, NumericConstants.THREE, NumericConstants.THIRTY, NumericConstants.THREE, NumericConstants.THIRTY, pvsdto, AMOUNT);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                salesPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), gtsList, dataList, NumericConstants.THREE, NumericConstants.THIRTY, NumericConstants.THREE, NumericConstants.THIRTY, pvsdto, RATE);
            }
        }

        // ExFac Sales 
        if (pvsdto.isVarExFacSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), gtsList, dataList, NumericConstants.TWO, NumericConstants.THIRTY, NumericConstants.TWO, NumericConstants.THIRTY, pvsdto, AMOUNT);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(exFacValue);
                }
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), gtsList, dataList, NumericConstants.TWO, NumericConstants.THIRTY, NumericConstants.TWO, NumericConstants.THIRTY, pvsdto, AMOUNT);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(exFacVar);
                }
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), gtsList, dataList, NumericConstants.TWO, NumericConstants.THIRTY, NumericConstants.TWO, NumericConstants.THIRTY, pvsdto, RATE);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(exFacPer);
                }
            }
        }
        // ExFac Customer
        if (pvsdto.isVarExFacCustomer()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), gtsList, dataList, NumericConstants.TWENTY_SEVEN, NumericConstants.THIRTY, NumericConstants.TWENTY_SEVEN, NumericConstants.THIRTY, pvsdto, AMOUNT);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(custExFacValue);
                }
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), gtsList, dataList, NumericConstants.TWENTY_SEVEN, NumericConstants.THIRTY, NumericConstants.TWENTY_SEVEN, NumericConstants.THIRTY, pvsdto, AMOUNT);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(custExFacVar);
                }
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), gtsList, dataList, NumericConstants.TWENTY_SEVEN, NumericConstants.THIRTY, NumericConstants.TWENTY_SEVEN, NumericConstants.THIRTY, pvsdto, RATE);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(custExFacPer);
                }
            }
        }

        // Demand Sales
        if (pvsdto.isVarDemandSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.DEMAND.toString(), gtsList, dataList, NumericConstants.FIFTEEN, NumericConstants.THIRTY, NumericConstants.FIFTEEN, NumericConstants.THIRTY, pvsdto, AMOUNT);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(demandValue);
                }
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.DEMAND.toString(), gtsList, dataList, NumericConstants.FIFTEEN, NumericConstants.THIRTY, NumericConstants.FIFTEEN, NumericConstants.THIRTY, pvsdto, AMOUNT);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(demandVar);
                }
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.DEMAND.toString(), gtsList, dataList, NumericConstants.FIFTEEN, NumericConstants.THIRTY, NumericConstants.FIFTEEN, NumericConstants.THIRTY, pvsdto, RATE);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(demandPer);
                }
            }
        }
        // Adjusted Demand
        if (pvsdto.isVarAdjDemand()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.ADJUSTED_DEMAND.toString(), gtsList, dataList, NumericConstants.TWENTY_SIX, NumericConstants.THIRTY, NumericConstants.TWENTY_SIX, NumericConstants.THIRTY, pvsdto, AMOUNT);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(adjDemandValue);
                }
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.ADJUSTED_DEMAND.toString(), gtsList, dataList, NumericConstants.TWENTY_SIX, NumericConstants.THIRTY, NumericConstants.TWENTY_SIX, NumericConstants.THIRTY, pvsdto, AMOUNT);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(adjDemandVar);
                }
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.ADJUSTED_DEMAND.toString(), gtsList, dataList, NumericConstants.TWENTY_SIX, NumericConstants.THIRTY, NumericConstants.TWENTY_SIX, NumericConstants.THIRTY, pvsdto, RATE);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(adjDemandPer);
                }
            }
        }
        // Inventory Sales Withdrawal
        if (pvsdto.isVarInvSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_SUMMARY.toString(), gtsList, dataList, NumericConstants.SIXTEEN, NumericConstants.THIRTY, NumericConstants.SIXTEEN, NumericConstants.THIRTY, pvsdto, AMOUNT);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(invWithValue);
                }
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_SUMMARY.toString(), gtsList, dataList, NumericConstants.SIXTEEN, NumericConstants.THIRTY, NumericConstants.SIXTEEN, NumericConstants.THIRTY, pvsdto, AMOUNT);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(invWithVar);
                }
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_SUMMARY.toString(), gtsList, dataList, NumericConstants.SIXTEEN, NumericConstants.THIRTY, NumericConstants.SIXTEEN, NumericConstants.THIRTY, pvsdto, RATE);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(invWithPer);
                }
            }
        }
        // iw Details
        if (pvsdto.isVarIwDetails()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_DETAILS.toString(), gtsList, dataList, NumericConstants.TWENTY_EIGHT, NumericConstants.THIRTY, NumericConstants.TWENTY_EIGHT, NumericConstants.THIRTY, pvsdto, AMOUNT);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(iwDetialsValue);
                }
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_DETAILS.toString(), gtsList, dataList, NumericConstants.TWENTY_EIGHT, NumericConstants.THIRTY, NumericConstants.TWENTY_EIGHT, NumericConstants.THIRTY, pvsdto, AMOUNT);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(iwDetialsVar);
                }
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_DETAILS.toString(), gtsList, dataList, NumericConstants.TWENTY_EIGHT, NumericConstants.THIRTY, NumericConstants.TWENTY_EIGHT, NumericConstants.THIRTY, pvsdto, RATE);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(iwDetialsPer);
                }
            }
        }

        //% Of Ex Factory Product
        if (pvsdto.isVarPerExFacSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), gtsList, dataList, NumericConstants.SEVEN, NumericConstants.THIRTY, NumericConstants.SEVEN, NumericConstants.THIRTY, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), gtsList, dataList, NumericConstants.SEVEN, NumericConstants.THIRTY, NumericConstants.SEVEN, NumericConstants.THIRTY, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), gtsList, dataList, NumericConstants.SEVEN, NumericConstants.THIRTY, NumericConstants.SEVEN, NumericConstants.THIRTY, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
        }
        // % Of Ex factory customer
        if (pvsdto.isVarPerExFacCustomer()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), gtsList, dataList, NumericConstants.THIRTY, NumericConstants.THIRTY, NumericConstants.THIRTY, NumericConstants.THIRTY, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), gtsList, dataList, NumericConstants.THIRTY, NumericConstants.THIRTY, NumericConstants.THIRTY, NumericConstants.THIRTY, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), gtsList, dataList, NumericConstants.THIRTY, NumericConstants.THIRTY, NumericConstants.THIRTY, NumericConstants.THIRTY, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
        }
        // % Of Demand Sales
        if (pvsdto.isVarPerDemandSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_DEMAND.toString(), gtsList, dataList, NumericConstants.SEVENTEEN, NumericConstants.THIRTY, NumericConstants.SEVENTEEN, NumericConstants.THIRTY, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_DEMAND.toString(), gtsList, dataList, NumericConstants.SEVENTEEN, NumericConstants.THIRTY, NumericConstants.SEVENTEEN, NumericConstants.THIRTY, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_DEMAND.toString(), gtsList, dataList, NumericConstants.SEVENTEEN, NumericConstants.THIRTY, NumericConstants.SEVENTEEN, NumericConstants.THIRTY, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
        }
        // % Of Adjusted Demand 
        if (pvsdto.isVarPerAdjDemand()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), gtsList, dataList, NumericConstants.TWENTY_NINE, NumericConstants.THIRTY, NumericConstants.TWENTY_NINE, NumericConstants.THIRTY, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), gtsList, dataList, NumericConstants.TWENTY_NINE, NumericConstants.THIRTY, NumericConstants.TWENTY_NINE, NumericConstants.THIRTY, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), gtsList, dataList, NumericConstants.TWENTY_NINE, NumericConstants.THIRTY, NumericConstants.TWENTY_NINE, NumericConstants.THIRTY, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
        }
        // % Of Inv Summary
        if (pvsdto.isVarPerInvSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), gtsList, dataList, NumericConstants.EIGHTEEN, NumericConstants.THIRTY, NumericConstants.EIGHTEEN, NumericConstants.THIRTY, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), gtsList, dataList, NumericConstants.EIGHTEEN, NumericConstants.THIRTY, NumericConstants.EIGHTEEN, NumericConstants.THIRTY, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), gtsList, dataList, NumericConstants.EIGHTEEN, NumericConstants.THIRTY, NumericConstants.EIGHTEEN, NumericConstants.THIRTY, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
        }
        // % Of Inventry withdrawal Detials 
        if (pvsdto.isVarPerIwDetails()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), gtsList, dataList, NumericConstants.THIRTY_ONE, NumericConstants.THIRTY, NumericConstants.THIRTY_ONE, NumericConstants.THIRTY, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), gtsList, dataList, NumericConstants.THIRTY_ONE, NumericConstants.THIRTY, NumericConstants.THIRTY_ONE, NumericConstants.THIRTY, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), gtsList, dataList, NumericConstants.THIRTY_ONE, NumericConstants.THIRTY, NumericConstants.THIRTY_ONE, NumericConstants.THIRTY, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
        }
        //Contract Sales
        if (pvsdto.isVarContractsales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                if (!isDetail) {
                    ProjectionVarianceDTO sales = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), gtsList, dataList, NumericConstants.THREE, NumericConstants.THIRTY, NumericConstants.THREE, NumericConstants.THIRTY, pvsdto, AMOUNT);
                    projectionVarianceDTO.add(sales);
                } else {
                    projectionVarianceDTO.add(salesValue);
                }
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO sales = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), gtsList, dataList, NumericConstants.THREE, NumericConstants.THIRTY, NumericConstants.THREE, NumericConstants.THIRTY, pvsdto, AMOUNT);
                    projectionVarianceDTO.add(sales);
                } else {
                    projectionVarianceDTO.add(salesVar);
                }
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                if (!isDetail) {
                    ProjectionVarianceDTO sales = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), gtsList, dataList, NumericConstants.THREE, NumericConstants.THIRTY, NumericConstants.THREE, NumericConstants.THIRTY, pvsdto, RATE);
                    projectionVarianceDTO.add(sales);
                } else {
                    projectionVarianceDTO.add(salesPer);
                }
            }
        }
        //Contract Units
        if (pvsdto.isVarContractUnits()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO units = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(), gtsList, dataList, NumericConstants.FOUR, NumericConstants.THIRTY, NumericConstants.FOUR, NumericConstants.THIRTY, pvsdto, AMOUNT);
                projectionVarianceDTO.add(units);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO units = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(), gtsList, dataList, NumericConstants.FOUR, NumericConstants.THIRTY, NumericConstants.FOUR, NumericConstants.THIRTY, pvsdto, AMOUNT);
                projectionVarianceDTO.add(units);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO units = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(), gtsList, dataList, NumericConstants.FOUR, NumericConstants.THIRTY, NumericConstants.FOUR, NumericConstants.THIRTY, pvsdto, RATE);
                projectionVarianceDTO.add(units);
            }
        }
        //Discount $ 
        if (pvsdto.isVarDisAmount()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO discountDol = getCommonCustomizedDTO(Constants.PVVariables.VAR_DIS_AMOUNT.toString(), gtsList, dataList, NumericConstants.FIVE, NumericConstants.THIRTY, NumericConstants.FIVE, NumericConstants.THIRTY, pvsdto, AMOUNT);
                projectionVarianceDTO.add(discountDol);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO discountDol = getCommonCustomizedDTO(Constants.PVVariables.VAR_DIS_AMOUNT.toString(), gtsList, dataList, NumericConstants.FIVE, NumericConstants.THIRTY, NumericConstants.FIVE, NumericConstants.THIRTY, pvsdto, AMOUNT);
                projectionVarianceDTO.add(discountDol);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO discountDol = getCommonCustomizedDTO(Constants.PVVariables.VAR_DIS_AMOUNT.toString(), gtsList, dataList, NumericConstants.FIVE, NumericConstants.THIRTY, NumericConstants.FIVE, NumericConstants.THIRTY, pvsdto, RATE);
                projectionVarianceDTO.add(discountDol);
            }
        }
        //Discount % 
        if (pvsdto.isVarDisRate()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO discountPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_DIS_RATE.toString(), gtsList, dataList, NumericConstants.SIX, NumericConstants.THIRTY, NumericConstants.SIX, NumericConstants.THIRTY, pvsdto, RATE);
                projectionVarianceDTO.add(discountPer);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO discountPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_DIS_RATE.toString(), gtsList, dataList, NumericConstants.SIX, NumericConstants.THIRTY, NumericConstants.SIX, NumericConstants.THIRTY, pvsdto, RATE);
                projectionVarianceDTO.add(discountPer);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO discountPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_DIS_RATE.toString(), gtsList, dataList, NumericConstants.SIX, NumericConstants.THIRTY, NumericConstants.SIX, NumericConstants.THIRTY, pvsdto, RATE);
                projectionVarianceDTO.add(discountPer);
            }
        }
        // RPU
        if (pvsdto.isVarRPU()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO rpu = getCommonCustomizedDTO(Constants.PVVariables.VAR_RPU.toString(), gtsList, dataList, NumericConstants.NINETEEN, NumericConstants.THIRTY, NumericConstants.NINETEEN, NumericConstants.THIRTY, pvsdto, AMOUNT);
                projectionVarianceDTO.add(rpu);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO netSalesVar = getCommonCustomizedDTO(Constants.PVVariables.VAR_RPU.toString(), gtsList, dataList, NumericConstants.NINETEEN, NumericConstants.THIRTY, NumericConstants.NINETEEN, NumericConstants.THIRTY, pvsdto, AMOUNT);
                projectionVarianceDTO.add(netSalesVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO netSalesPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_RPU.toString(), gtsList, dataList, NumericConstants.NINETEEN, NumericConstants.THIRTY, NumericConstants.NINETEEN, NumericConstants.THIRTY, pvsdto, RATE);
                projectionVarianceDTO.add(netSalesPer);
            }
        }
        //NetSales 
        if (pvsdto.isVarNetSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO netSalesValue = getCommonCustomizedDTO(Constants.PVVariables.VAR_NETSALES.toString(), gtsList, dataList, NumericConstants.FOURTEEN, NumericConstants.THIRTY, NumericConstants.FOURTEEN, NumericConstants.THIRTY, pvsdto, AMOUNT);
                projectionVarianceDTO.add(netSalesValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO netSalesVar = getCommonCustomizedDTO(Constants.PVVariables.VAR_NETSALES.toString(), gtsList, dataList, NumericConstants.FOURTEEN, NumericConstants.THIRTY, NumericConstants.FOURTEEN, NumericConstants.THIRTY, pvsdto, AMOUNT);
                projectionVarianceDTO.add(netSalesVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO netSalesPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_NETSALES.toString(), gtsList, dataList, NumericConstants.FOURTEEN, NumericConstants.THIRTY, NumericConstants.FOURTEEN, NumericConstants.THIRTY, pvsdto, RATE);
                projectionVarianceDTO.add(netSalesPer);
            }
        }
        //COGS
        if (pvsdto.isVarCOGC()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO netSalesValue = getCommonCustomizedDTO(Constants.PVVariables.VAR_COGS.toString(), gtsList, dataList, NumericConstants.TWENTY_TWO, NumericConstants.THIRTY, NumericConstants.TWENTY_TWO, NumericConstants.THIRTY, pvsdto, AMOUNT);
                projectionVarianceDTO.add(netSalesValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO netSalesVar = getCommonCustomizedDTO(Constants.PVVariables.VAR_COGS.toString(), gtsList, dataList, NumericConstants.TWENTY_TWO, NumericConstants.THIRTY, NumericConstants.TWENTY_TWO, NumericConstants.THIRTY, pvsdto, AMOUNT);
                projectionVarianceDTO.add(netSalesVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO netSalesPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_COGS.toString(), gtsList, dataList, NumericConstants.TWENTY_TWO, NumericConstants.THIRTY, NumericConstants.TWENTY_TWO, NumericConstants.THIRTY, pvsdto, RATE);
                projectionVarianceDTO.add(netSalesPer);
            }
        }
        //Net Profit
        if (pvsdto.isVarNetProfit()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO netSalesValue = getCommonCustomizedDTO(Constants.PVVariables.VAR_NET_PROFITE.toString(), gtsList, dataList, NumericConstants.TWENTY_THREE, NumericConstants.THIRTY, NumericConstants.TWENTY_THREE, NumericConstants.THIRTY, pvsdto, AMOUNT);
                projectionVarianceDTO.add(netSalesValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO netSalesVar = getCommonCustomizedDTO(Constants.PVVariables.VAR_NET_PROFITE.toString(), gtsList, dataList, NumericConstants.TWENTY_THREE, NumericConstants.THIRTY, NumericConstants.TWENTY_THREE, NumericConstants.THIRTY, pvsdto, AMOUNT);
                projectionVarianceDTO.add(netSalesVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO netSalesPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_NET_PROFITE.toString(), gtsList, dataList, NumericConstants.TWENTY_THREE, NumericConstants.THIRTY, NumericConstants.TWENTY_THREE, NumericConstants.THIRTY, pvsdto, RATE);
                projectionVarianceDTO.add(netSalesPer);
            }
        }
        resultMap.put("Total",projectionVarianceDTO);
    }
    
    
     /**
     * Ex Factory Sales
     *
     * @param pvsdto
     * @return
     */
    public ProjectionVarianceDTO getCommonCustomizedDTO(final String groupName, List<Object> gtsList, List<Object> dataList, final int totalListPostion, final int totalPrPos, final int detailsListPos, final int detailsPrPos, PVSelectionDTO pvsdto, final DecimalFormat FORMAT) {
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(Constants.VALUE)) {
            pvDTO.setGroup(groupName.concat(Constants.VALUE));
        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
            pvDTO.setGroup(groupName.concat(Constants.VARIANCE));
        } else {
            pvDTO.setGroup(groupName.concat(Constants.CHANGE));
        }
        if (!pvsdto.getDiscountLevel().equals("Total Discount") && (pvDTO.getGroup().contains("Discount") || pvDTO.getGroup().contains("RPU"))) {
            pvDTO.setParent(1);
        }
        for (Object nullObj : pvsdto.getRightHeaderPeriod().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, ZERO);
        }
        List<Integer> priorList = new ArrayList<Integer>(pvsdto.getProjIdList());
        if (gtsList != null && !gtsList.isEmpty()) {
            if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
                for (int i = 0; i < gtsList.size(); i++) {
                    final Object[] obj = (Object[]) gtsList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == NumericConstants.FOUR) {
                        commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == NumericConstants.TWO) {
                        commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == NumericConstants.TWELVE) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(Constants.VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[totalListPostion])));
                        String baseValue = getFormattedValue(FORMAT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                    }
                    else{
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), ZERO);  
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(Constants.VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[totalListPostion + ((j + 1) * totalPrPos)])));
                            String val = getFormattedValue(FORMAT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);
                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[totalListPostion + ((j + 1) * totalPrPos)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[totalListPostion])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(FORMAT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);
                        } else {
                            String priorVal = StringUtils.EMPTY + obj[totalListPostion + ((j + 1) * totalPrPos)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[totalListPostion])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(FORMAT, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            } else {
                for (int i = 0; i < dataList.size(); i++) {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == NumericConstants.FOUR) {
                        commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == NumericConstants.TWO) {
                        commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == NumericConstants.TWELVE) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(Constants.VALUE)) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[detailsListPos])));
                        String baseValue = getFormattedValue(FORMAT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);
                    }
                    else{
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), ZERO);  
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(Constants.VALUE)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[detailsListPos + ((j + 1) * detailsPrPos)])));
                            String val = getFormattedValue(FORMAT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[detailsListPos + ((j + 1) * detailsPrPos)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[detailsListPos])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(FORMAT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[detailsListPos + ((j + 1) * detailsPrPos)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[detailsListPos])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        }
        
        return pvDTO;
    }
    
     ProjectionVarianceDTO setDataObjects(ProjectionVarianceDTO dto, ProjectionVarianceDTO parentDto, PVSelectionDTO pvsdto) {
        dto.setLevelNo(parentDto.getLevelNo());
        dto.setTreeLevelNo(parentDto.getTreeLevelNo());
        dto.setParentNode(parentDto.getParentNode());
        dto.setLevelValue(parentDto.getGroup());
        dto.setHierarchyIndicator(parentDto.getHierarchyIndicator());
        dto.setHierarchyNo(parentDto.getHierarchyNo());
        if (dto.getHierarchyIndicator().equals(C)) {
            dto.setCustomerHierarchyNo(dto.getHierarchyNo());
            dto.setProductHierarchyNo(pvsdto.getProductHierarchyNo());
        } else if (dto.getHierarchyIndicator().equals(P)) {
            dto.setProductHierarchyNo(dto.getHierarchyNo());
            dto.setCustomerHierarchyNo(pvsdto.getCustomerHierarchyNo());
        }
        return dto;
    }
     
     
   
    public List<ProjectionVarianceDTO> getCustomisedDiscount(List<Object> dataList, PVSelectionDTO projSelDTO, int index, int priorIndex, boolean isPer,String indicator) {
        LOGGER.debug("Inside getCustomisedProjectionResultsTotalDiscount");
       
        String lastValue = StringUtils.EMPTY;
        List<ProjectionVarianceDTO> resultDto = new ArrayList<>();
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        pvDTO.setLevelValue(projSelDTO.getLevelValue());
        pvDTO.setLevelNo(projSelDTO.getLevelNo());
        pvDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        pvDTO.setParent(0);
        List<Integer> priorList = new ArrayList<>(projSelDTO.getProjIdList());
        boolean actualBasis = ("Actuals").equals(projSelDTO.getComparisonBasis());
        if (dataList != null && !dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                final Object[] obj = (Object[]) dataList.get(i);
                if (!StringUtils.EMPTY.equals(lastValue) && !"null".equals(lastValue) && obj[NumericConstants.TWO] != null && !lastValue.equals(String.valueOf(obj[NumericConstants.TWO]))) {
                    pvDTO.setGroup(lastValue);
                    resultDto.add(pvDTO);
                    pvDTO = new ProjectionVarianceDTO();
                }
                lastValue = String.valueOf(obj[NumericConstants.TWO]);
                pvDTO.setGroup(lastValue);
                String commonColumn = StringUtils.EMPTY;
                if (frequencyDivision == NumericConstants.FOUR) {
                    commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == NumericConstants.TWO) {
                    commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 1) {
                    commonColumn = StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == NumericConstants.TWELVE) {
                    String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                    commonColumn = monthName.toLowerCase() + obj[0];
                }
                String actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index - 1])));
                String currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));
                if (indicator.equals(Constants.VALUE)) {
                    if (obj[index] == null) {
                        String baseValue = getFormattedValue(isPer ? RATE : AMOUNT, "0.00");
                        pvDTO.addStringProperties(commonColumn + CURRENT + projSelDTO.getCurrentProjId(), isPer ? baseValue + PERCENT : baseValue);
                    } else {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));
                        String baseValue = getFormattedValue(isPer ? RATE : AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + projSelDTO.getCurrentProjId(), isPer ? baseValue + PERCENT : baseValue);
                        String actvalue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index - 1])));
                        baseValue = getFormattedValue(isPer ? RATE : AMOUNT, actvalue);
                        pvDTO.addStringProperties(commonColumn + ACTUAL + projSelDTO.getCurrentProjId(), isPer ? baseValue + PERCENT : baseValue);
                    }
                }
                 if (actualBasis) {
                            if (indicator.equals(VARIANCE.getConstant())) {
//                        for CURRENT
                                String val = getVariance(actualValue, currentValue, isPer ? RATE : AMOUNT);
                                pvDTO.addStringProperties(commonColumn + CURRENT + projSelDTO.getCurrentProjId(), isPer ? val + PERCENT : val);
                            }
                            if (indicator.equals(Constants.CHANGE) || PERCENT.equals(indicator)) {
//                        for CURRENT
                                String val = getPerChange(actualValue, currentValue, RATE_PER);
                                pvDTO.addStringProperties(commonColumn + CURRENT + projSelDTO.getCurrentProjId(), val + PERCENT);
                            }
                        }
                for (int j = 0; j < priorList.size(); j++) {
                    String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * priorIndex)])));
                    if (indicator.equals(Constants.VALUE)) {
                     if(obj[index + ((j + 1) * priorIndex)]==null){
                       String baseValue = getFormattedValue(isPer ? RATE : AMOUNT, "0.00");
                       pvDTO.addStringProperties(commonColumn + priorList.get(j), isPer ? baseValue + PERCENT : baseValue);  
                     }else{
                        String val = getFormattedValue(isPer ? RATE : AMOUNT, priorVal);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                     }
                    } else if (indicator.equals(VARIANCE.getConstant())) {
                        if(obj[index + ((j + 1) * priorIndex)]==null && obj[index]==null){
                            String baseValue = getFormattedValue(isPer ? RATE : AMOUNT, "0.00");
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), isPer ? baseValue + PERCENT : baseValue);
                        }else{
                            if (actualBasis) {
                                String val = getVariance(actualValue, priorVal, isPer ? RATE : AMOUNT);
                                pvDTO.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                            } else {
                                String val = getVariance(currentValue, priorVal, isPer ? RATE : AMOUNT);
                                pvDTO.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                            }
                        }
                    } else {
                       if(obj[index + ((j + 1) * priorIndex)]==null && obj[index]==null){
                           String baseValue = getFormattedValue(isPer ? RATE : AMOUNT, "0.00");
                           pvDTO.addStringProperties(commonColumn + priorList.get(j), isPer ? baseValue + PERCENT : baseValue);
                       }else{
                           if (actualBasis) {
                               String baseValu = getPerChange(actualValue, priorVal, RATE_PER);
                               pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                           } else {
                               String baseValu = getPerChange(currentValue, priorVal, RATE_PER);
                               pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                           }
                       }
                    }
                }
                if (i == dataList.size() - 1) {
                    resultDto.add(pvDTO);
                }
            }
        }
        LOGGER.debug("Ending getCustomisedProjectionResultsTotalDiscount with list size  = = >" + resultDto.size());
        return resultDto;
    }
     
     
    
    public List<ProjectionVarianceDTO> getCustPeriodVariance_Details(List<ProjectionVarianceDTO> pvList, final PVSelectionDTO pvsdto,final Object[] obj) {
       
        List<ProjectionVarianceDTO> projectionVarianceDTO = new ArrayList<ProjectionVarianceDTO>();
        ProjectionVarianceDTO exFacValue1 = new ProjectionVarianceDTO();
        ProjectionVarianceDTO exFacVariance = new ProjectionVarianceDTO();
        ProjectionVarianceDTO exFacPercent = new ProjectionVarianceDTO();
        
        ProjectionVarianceDTO dto = new ProjectionVarianceDTO();
                //Group Column projSelDTO
                String groupName;
                if (isCustomView) {
                    groupName = customView_relationship_hierarchy.get(obj[NumericConstants.TWO] == null ? StringUtils.EMPTY : obj[NumericConstants.TWO].toString());
                    groupName = groupName == null ? StringUtils.EMPTY : groupName;
                     dto.setHierarchyNo(obj[NumericConstants.TWO].toString());
                     dto.setParentHierarchyNo(obj[obj.length - 1] == null ? null : obj[obj.length - 1].toString());
                } else {
                    groupName = selection.getSessionDTO().getLevelValueDiscription(obj[NumericConstants.TWO].toString(), selection.getHierarchyIndicator());
                }
                dto.setGroup(groupName);
                pvList.add(dto);

        ProjectionVarianceDTO demandValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO demandVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO demandPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO invWithSummaryValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO invWithSummaryVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO invWithSummaryPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO perExFacProductValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO perExFacProductVariance = new ProjectionVarianceDTO();
        ProjectionVarianceDTO perExFacProductPer = new ProjectionVarianceDTO();
        
        
        ProjectionVarianceDTO perExFacCustValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO perExFacCustVariance = new ProjectionVarianceDTO();
        ProjectionVarianceDTO perExFacCustPer = new ProjectionVarianceDTO();
        
        ProjectionVarianceDTO perDemandValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO perDemandVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO perDemandPer = new ProjectionVarianceDTO();
        
        ProjectionVarianceDTO perAdjDemandValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO perAdjDemandVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO perAdjDemandPer = new ProjectionVarianceDTO();
        
        ProjectionVarianceDTO perInvWithSummaryValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO perInvWithSummaryVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO perInvWithSummaryPer = new ProjectionVarianceDTO();
        
         ProjectionVarianceDTO perIwDetialsValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO perIwDetialsVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO perIwDetialsPer = new ProjectionVarianceDTO();
        
        
        ProjectionVarianceDTO custExFacValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO custExFacVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO custExFacPer = new ProjectionVarianceDTO();
        
        ProjectionVarianceDTO adjDemandValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO adjDemandVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO adjDemandPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO iwDetialsValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO iwDetialsVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO iwDetialsPer = new ProjectionVarianceDTO();
        
        ProjectionVarianceDTO discountDollarValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO discountDollarVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO discountDollarPer = new ProjectionVarianceDTO();
        
        ProjectionVarianceDTO discountPerValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO discountPerVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO discountPerPercent = new ProjectionVarianceDTO();
        
        ProjectionVarianceDTO contractSalesValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO contractSalesVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO contractSalesPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO contractUnitValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO contractUnitVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO contractUnitPer = new ProjectionVarianceDTO();
        
        ProjectionVarianceDTO rpuValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO rpuVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO rpuPer = new ProjectionVarianceDTO();
        
        ProjectionVarianceDTO netSalesValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO netSalesVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO netSalesPer = new ProjectionVarianceDTO();
        
        ProjectionVarianceDTO cogcValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO cogcVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO cogcPer = new ProjectionVarianceDTO();
        
        ProjectionVarianceDTO netProfitValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO netProfitVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO netProfitPer = new ProjectionVarianceDTO();
        
        
        ProjectionVarianceDTO discountPerExfacValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO discountPerExfacVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO discountPerExfacPercent = new ProjectionVarianceDTO();
        
        ProjectionVarianceDTO netSalesExfacValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO netSalesExfacVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO netSalesExfacPer = new ProjectionVarianceDTO();
        
           //Ex factory product
           if (pvsdto.isVarExFacSales()) {
                if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                exFacValue1 = getCommonCustomizedDTO_Details(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), obj, obj, NumericConstants.FIVE,totalPrPos, NumericConstants.FIVE, detailPrPos, pvsdto, AMOUNT,exFacValue1,true);
                pvList.add(exFacValue1);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                exFacVariance = getCommonCustomizedDTO_Details(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), obj, obj, NumericConstants.FIVE, totalPrPos, NumericConstants.FIVE, detailPrPos, pvsdto, AMOUNT,exFacVariance,true);
                pvList.add(exFacVariance);
                
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                exFacPercent = getCommonCustomizedDTO_Details(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), obj, obj, NumericConstants.FIVE, totalPrPos, NumericConstants.FIVE, detailPrPos, pvsdto, RATE,exFacPercent,true);
                 pvList.add(exFacPercent);
               
            }
           }

           // Customer ExFac Sales for detail start 
             if (pvsdto.isVarExFacCustomer()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                custExFacValue = getCommonCustomizedDTO_Details(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), obj, obj, NumericConstants.FIFTY_ONE, totalPrPos, NumericConstants.FIFTY_ONE, detailPrPos, pvsdto, AMOUNT,custExFacValue,true);
                pvList.add(custExFacValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                custExFacVar = getCommonCustomizedDTO_Details(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), obj, obj, NumericConstants.FIFTY_ONE, totalPrPos, NumericConstants.FIFTY_ONE, detailPrPos, pvsdto, AMOUNT,custExFacVar,true);
              pvList.add(custExFacVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                custExFacPer = getCommonCustomizedDTO_Details(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), obj, obj, NumericConstants.FIFTY_ONE, totalPrPos, NumericConstants.FIFTY_ONE, detailPrPos, pvsdto, RATE,custExFacPer,true);
                pvList.add(custExFacPer);
            }
             }
//            //Demand - start
             if (pvsdto.isVarDemandSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                demandValue = getCommonCustomizedDTO_Details(Constants.PVVariables.DEMAND.toString(), obj, obj, NumericConstants.THIRTY_ONE, totalPrPos, NumericConstants.THIRTY_ONE, detailPrPos, pvsdto, AMOUNT,demandValue,true);
               pvList.add(demandValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                demandVar = getCommonCustomizedDTO_Details(Constants.PVVariables.DEMAND.toString(), obj, obj, NumericConstants.THIRTY_ONE, totalPrPos, NumericConstants.THIRTY_ONE, detailPrPos, pvsdto, AMOUNT,demandVar,true);
                pvList.add(demandVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                demandPer = getCommonCustomizedDTO_Details(Constants.PVVariables.DEMAND.toString(), obj, obj, NumericConstants.THIRTY_ONE, totalPrPos, NumericConstants.THIRTY_ONE, detailPrPos, pvsdto, RATE,demandPer,true);
                pvList.add(demandPer);
            }
             }

            // Adjusted Demand  start 
           if (pvsdto.isVarAdjDemand()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                adjDemandValue = getCommonCustomizedDTO_Details(Constants.PVVariables.ADJUSTED_DEMAND.toString(), obj, obj, NumericConstants.FORTY_NINE, totalPrPos, NumericConstants.FORTY_NINE, detailPrPos, pvsdto, AMOUNT, adjDemandValue, true);
                pvList.add(adjDemandValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                adjDemandVar = getCommonCustomizedDTO_Details(Constants.PVVariables.ADJUSTED_DEMAND.toString(), obj, obj, NumericConstants.FORTY_NINE, totalPrPos, NumericConstants.FORTY_NINE, detailPrPos, pvsdto, AMOUNT, adjDemandVar, true);
                pvList.add(adjDemandVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                adjDemandPer = getCommonCustomizedDTO_Details(Constants.PVVariables.ADJUSTED_DEMAND.toString(), obj, obj, NumericConstants.FORTY_NINE, totalPrPos, NumericConstants.FORTY_NINE, detailPrPos, pvsdto, RATE, adjDemandPer, true);
                pvList.add(adjDemandPer);
            }
        }
//
//            //Inv with drawal Summary
             if (pvsdto.isVarInvSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                invWithSummaryValue = getCommonCustomizedDTO_Details(Constants.PVVariables.INVENTORY_SUMMARY.toString(), obj, obj, NumericConstants.THIRTY_THREE, totalPrPos, NumericConstants.THIRTY_THREE, detailPrPos, pvsdto, AMOUNT,invWithSummaryValue,true);
                pvList.add(invWithSummaryValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                invWithSummaryVar = getCommonCustomizedDTO_Details(Constants.PVVariables.INVENTORY_SUMMARY.toString(), obj, obj, NumericConstants.THIRTY_THREE, totalPrPos, NumericConstants.THIRTY_THREE, detailPrPos, pvsdto, AMOUNT,invWithSummaryVar,true);
               pvList.add(invWithSummaryVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                invWithSummaryPer = getCommonCustomizedDTO_Details(Constants.PVVariables.INVENTORY_SUMMARY.toString(), obj, obj, NumericConstants.THIRTY_THREE, totalPrPos, NumericConstants.THIRTY_THREE, detailPrPos, pvsdto, RATE,invWithSummaryPer,true);
                 pvList.add(invWithSummaryPer);
            }
             }

            // Inventary withdrawal Details for detail start 
              if (pvsdto.isVarIwDetails()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                iwDetialsValue = getCommonCustomizedDTO_Details(Constants.PVVariables.INVENTORY_DETAILS.toString(), obj, obj, NumericConstants.FIFTY_THREE, totalPrPos, NumericConstants.FIFTY_THREE, detailPrPos, pvsdto, AMOUNT,iwDetialsValue,true);
                 pvList.add(iwDetialsValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                iwDetialsVar = getCommonCustomizedDTO_Details(Constants.PVVariables.INVENTORY_DETAILS.toString(), obj, obj, NumericConstants.FIFTY_THREE, totalPrPos, NumericConstants.FIFTY_THREE, detailPrPos, pvsdto, AMOUNT,iwDetialsVar,true);
                 pvList.add(iwDetialsVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                iwDetialsPer = getCommonCustomizedDTO_Details(Constants.PVVariables.INVENTORY_DETAILS.toString(), obj, obj, NumericConstants.FIFTY_THREE, totalPrPos, NumericConstants.FIFTY_THREE, detailPrPos, pvsdto, RATE,iwDetialsPer,true);
                pvList.add(iwDetialsPer);
            }
              }
//
//       //% Of Ex Factory Product
        if (pvsdto.isVarPerExFacSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                perExFacProductValue = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), obj, obj, NumericConstants.FIFTEEN, totalPrPos, NumericConstants.FIFTEEN, detailPrPos, pvsdto, RATE,perExFacProductValue,true);
                pvList.add(perExFacProductValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                perExFacProductVariance = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), obj, obj, NumericConstants.FIFTEEN, totalPrPos, NumericConstants.FIFTEEN, detailPrPos, pvsdto, RATE,perExFacProductVariance,true);
                 pvList.add(perExFacProductVariance);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                perExFacProductPer = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), obj, obj, NumericConstants.FIFTEEN, totalPrPos, NumericConstants.FIFTEEN, detailPrPos, pvsdto, RATE,perExFacProductPer,true);
                pvList.add(perExFacProductPer);
            }
        }
//        // % Of Ex factory customer
        if (pvsdto.isVarPerExFacCustomer()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                perExFacCustValue = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), obj, obj, NumericConstants.FIFTY_SEVEN, totalPrPos, NumericConstants.FIFTY_SEVEN, detailPrPos, pvsdto, RATE,perExFacCustValue,true);
                pvList.add(perExFacCustValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                 perExFacCustVariance = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), obj, obj, NumericConstants.FIFTY_SEVEN, totalPrPos, NumericConstants.FIFTY_SEVEN, detailPrPos, pvsdto, RATE,perExFacCustVariance,true);
                pvList.add(perExFacCustVariance);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                 perExFacCustPer = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), obj, obj, NumericConstants.FIFTY_SEVEN, totalPrPos, NumericConstants.FIFTY_SEVEN, detailPrPos, pvsdto, RATE,perExFacCustPer,true);
                 pvList.add(perExFacCustPer);
            }
        }
//        // % Of Demand Sales
        if (pvsdto.isVarPerDemandSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                 perDemandValue = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_DEMAND.toString(), obj, obj, NumericConstants.THIRTY_FIVE, totalPrPos, NumericConstants.THIRTY_FIVE, detailPrPos, pvsdto, RATE,perDemandValue,true);
                 pvList.add(perDemandValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                 perDemandVar = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_DEMAND.toString(), obj, obj, NumericConstants.THIRTY_FIVE, totalPrPos, NumericConstants.THIRTY_FIVE, detailPrPos, pvsdto, RATE,perDemandVar,true);
                 pvList.add(perDemandVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                 perDemandPer = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_DEMAND.toString(), obj, obj, NumericConstants.THIRTY_FIVE, totalPrPos, NumericConstants.THIRTY_FIVE, detailPrPos, pvsdto, RATE,perDemandPer,true);
                 pvList.add(perDemandPer);
            }
        }
        // % Of Adjusted Demand 
        if (pvsdto.isVarPerAdjDemand()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                 perAdjDemandValue = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), obj, obj, NumericConstants.FIFTY_FIVE, totalPrPos, NumericConstants.FIFTY_FIVE, detailPrPos, pvsdto, RATE,perAdjDemandValue,true);
                pvList.add(perAdjDemandValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                 perAdjDemandVar = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), obj, obj, NumericConstants.FIFTY_FIVE, totalPrPos, NumericConstants.FIFTY_FIVE, detailPrPos, pvsdto, RATE,perAdjDemandVar,true);
                pvList.add(perAdjDemandVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                 perAdjDemandPer= getCommonCustomizedDTO_Details(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), obj, obj, NumericConstants.FIFTY_FIVE, totalPrPos, NumericConstants.FIFTY_FIVE, detailPrPos, pvsdto, RATE,perAdjDemandPer,true);
                 pvList.add(perAdjDemandPer);
            }
        }
        // % Of Inv Summary
        if (pvsdto.isVarPerInvSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                 perInvWithSummaryValue = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), obj, obj, NumericConstants.THIRTY_SEVEN, totalPrPos, NumericConstants.THIRTY_SEVEN, detailPrPos, pvsdto, RATE,perInvWithSummaryValue,true);
                pvList.add(perInvWithSummaryValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                 perInvWithSummaryVar = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), obj, obj, NumericConstants.THIRTY_SEVEN, totalPrPos, NumericConstants.THIRTY_SEVEN, detailPrPos, pvsdto, RATE,perInvWithSummaryVar,true);
                pvList.add(perInvWithSummaryVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                 perInvWithSummaryPer = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), obj, obj, NumericConstants.THIRTY_SEVEN, totalPrPos, NumericConstants.THIRTY_SEVEN, detailPrPos, pvsdto, RATE,perInvWithSummaryPer,true);
                pvList.add(perInvWithSummaryPer);
            }
        }
        // % Of Inventry withdrawal Detials 
        if (pvsdto.isVarPerIwDetails()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                 perIwDetialsValue = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), obj, obj, NumericConstants.FIFTY_NINE, totalPrPos, NumericConstants.FIFTY_NINE, detailPrPos, pvsdto, RATE,perIwDetialsValue,true);
                pvList.add(perIwDetialsValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                 perIwDetialsVar = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), obj, obj, NumericConstants.FIFTY_NINE, totalPrPos, NumericConstants.FIFTY_NINE, detailPrPos, pvsdto, RATE,perIwDetialsVar,true);
                 pvList.add(perIwDetialsVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                 perIwDetialsPer = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), obj, obj, NumericConstants.FIFTY_NINE, totalPrPos, NumericConstants.FIFTY_NINE, detailPrPos, pvsdto, RATE,perIwDetialsPer,true);
                pvList.add(perIwDetialsPer);
            }
        }
        //Contract Sales
        if (pvsdto.isVarContractsales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                    contractSalesValue = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), obj, obj, NumericConstants.SEVEN, totalPrPos, NumericConstants.SEVEN, detailPrPos, pvsdto, AMOUNT,contractSalesValue,true);
                   pvList.add(contractSalesValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                    contractSalesVar = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), obj, obj, NumericConstants.SEVEN, totalPrPos, NumericConstants.SEVEN, detailPrPos, pvsdto, AMOUNT,contractSalesVar,true);
                      pvList.add(contractSalesVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                    contractSalesPer = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), obj, obj, NumericConstants.SEVEN, totalPrPos, NumericConstants.SEVEN, detailPrPos, pvsdto, RATE,contractSalesPer,true);
                     pvList.add(contractSalesPer);
            }
        }
        //Contract Units
        if (pvsdto.isVarContractUnits()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                contractUnitValue = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(), obj, obj, NumericConstants.NINE, totalPrPos, NumericConstants.NINE, detailPrPos, pvsdto, AMOUNT_UNITS,contractUnitValue,true);
              pvList.add(contractUnitValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                contractUnitVar = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(), obj, obj, NumericConstants.NINE, totalPrPos, NumericConstants.NINE, detailPrPos, pvsdto, AMOUNT_UNITS,contractUnitVar,true);
                pvList.add(contractUnitVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                contractUnitPer = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(), obj, obj, NumericConstants.NINE, totalPrPos, NumericConstants.NINE, detailPrPos, pvsdto, RATE,contractUnitPer,true);
               pvList.add(contractUnitPer);
            }
        }
        //Discount $ 
        if (pvsdto.isVarDisAmount()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                discountDollarValue = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_DIS_AMOUNT.toString(), obj, obj, NumericConstants.ELEVEN, totalPrPos, NumericConstants.ELEVEN, detailPrPos, pvsdto, AMOUNT,discountDollarValue,true);
                pvList.add(discountDollarValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                discountDollarVar = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_DIS_AMOUNT.toString(), obj, obj, NumericConstants.ELEVEN, totalPrPos, NumericConstants.ELEVEN, detailPrPos, pvsdto, AMOUNT,discountDollarVar,true);
                 pvList.add(discountDollarVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                discountDollarPer = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_DIS_AMOUNT.toString(), obj, obj, NumericConstants.ELEVEN, totalPrPos, NumericConstants.ELEVEN, detailPrPos, pvsdto, RATE,discountDollarPer,true);
                pvList.add(discountDollarPer);
            }
        }
       //Discount % 
        if (pvsdto.isVarDisRate()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                discountPerValue = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_DIS_RATE.toString(), obj, obj, NumericConstants.THIRTEEN, totalPrPos, NumericConstants.THIRTEEN, detailPrPos, pvsdto, RATE,discountPerValue,true);
               pvList.add(discountPerValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                discountPerVar = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_DIS_RATE.toString(), obj, obj, NumericConstants.THIRTEEN, totalPrPos, NumericConstants.THIRTEEN, detailPrPos, pvsdto, RATE,discountPerVar,true);
                pvList.add(discountPerVar);
                
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                discountPerPercent = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_DIS_RATE.toString(), obj, obj, NumericConstants.THIRTEEN, totalPrPos, NumericConstants.THIRTEEN, detailPrPos, pvsdto, RATE,discountPerPercent,true);
                pvList.add(discountPerPercent);
            }
        }
        // RPU
        if (pvsdto.isVarRPU()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                rpuValue = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_RPU.toString(), obj, obj, NumericConstants.FORTY_THREE, totalPrPos, NumericConstants.FORTY_THREE, detailPrPos, pvsdto, AMOUNT,rpuValue,true);
               pvList.add(rpuValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                rpuVar = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_RPU.toString(), obj, obj, NumericConstants.FORTY_THREE, totalPrPos, NumericConstants.FORTY_THREE, detailPrPos, pvsdto, AMOUNT,rpuVar,true);
                pvList.add(rpuVar);

            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                rpuPer = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_RPU.toString(), obj, obj, NumericConstants.FORTY_THREE, totalPrPos, NumericConstants.FORTY_THREE, detailPrPos, pvsdto, RATE,rpuPer,true);
                 pvList.add(rpuPer);
            }
        }
         //DiscountPerExFactory
        if (pvsdto.isDiscountPerExFactory()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                discountPerExfacValue = getCommonCustomizedDTO_Details(Constants.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), obj, obj, NumericConstants.SIXTY_THREE, totalPrPos, NumericConstants.SIXTY_THREE, detailPrPos, pvsdto, RATE,discountPerExfacValue,true);
               pvList.add(discountPerExfacValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                discountPerExfacVar = getCommonCustomizedDTO_Details(Constants.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), obj, obj, NumericConstants.SIXTY_THREE, totalPrPos, NumericConstants.SIXTY_THREE, detailPrPos, pvsdto, RATE,discountPerExfacVar,true);
                pvList.add(discountPerExfacVar);
                
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                discountPerExfacPercent = getCommonCustomizedDTO_Details(Constants.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), obj, obj, NumericConstants.SIXTY_THREE, totalPrPos, NumericConstants.SIXTY_THREE, detailPrPos, pvsdto, RATE,discountPerExfacPercent,true);
                pvList.add(discountPerExfacPercent);
            }
        }
        //NetSales 
        if (pvsdto.isVarNetSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                netSalesValue = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_NETSALES.toString(), obj, obj, NumericConstants.TWENTY_NINE, totalPrPos, NumericConstants.TWENTY_NINE, detailPrPos, pvsdto, AMOUNT,netSalesValue,true);
                pvList.add(netSalesValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
               netSalesVar = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_NETSALES.toString(), obj, obj, NumericConstants.TWENTY_NINE, totalPrPos, NumericConstants.TWENTY_NINE, detailPrPos, pvsdto, AMOUNT,netSalesVar,true);
               pvList.add(netSalesVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
               netSalesPer = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_NETSALES.toString(), obj, obj, NumericConstants.TWENTY_NINE, totalPrPos, NumericConstants.TWENTY_NINE, detailPrPos, pvsdto, RATE,netSalesPer,true);
                pvList.add(netSalesPer);
            }
        }
         //NetSalesExFactory
        if (pvsdto.isNetSalesExFactory()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                netSalesExfacValue = getCommonCustomizedDTO_Details(Constants.PVVariables.NET_SALES_PER_EX_FACTORY.toString(), obj, obj, NumericConstants.SIXTY_ONE, totalPrPos, NumericConstants.SIXTY_ONE, detailPrPos, pvsdto, RATE,netSalesExfacValue,true);
                pvList.add(netSalesExfacValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
               netSalesExfacVar = getCommonCustomizedDTO_Details(Constants.PVVariables.NET_SALES_PER_EX_FACTORY.toString(), obj, obj, NumericConstants.SIXTY_ONE, totalPrPos, NumericConstants.SIXTY_ONE, detailPrPos, pvsdto, RATE,netSalesExfacVar,true);
               pvList.add(netSalesExfacVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
               netSalesExfacPer = getCommonCustomizedDTO_Details(Constants.PVVariables.NET_SALES_PER_EX_FACTORY.toString(), obj, obj, NumericConstants.SIXTY_ONE, totalPrPos, NumericConstants.SIXTY_ONE, detailPrPos, pvsdto, RATE,netSalesExfacPer,true);
                pvList.add(netSalesExfacPer);
            }
        }
        //COGS
        if (pvsdto.isVarCOGC()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                cogcValue = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_COGS.toString(), obj, obj, NumericConstants.FORTY_FIVE, totalPrPos, NumericConstants.FORTY_FIVE, detailPrPos, pvsdto, AMOUNT,cogcValue,true);
               pvList.add(cogcValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                cogcVar = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_COGS.toString(), obj, obj, NumericConstants.FORTY_FIVE, totalPrPos, NumericConstants.FORTY_FIVE, detailPrPos, pvsdto, AMOUNT,cogcVar,true);
               pvList.add(cogcVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                cogcPer = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_COGS.toString(), obj, obj, NumericConstants.FORTY_FIVE, totalPrPos, NumericConstants.FORTY_FIVE, detailPrPos, pvsdto, RATE,cogcPer,true);
                pvList.add(cogcPer);
            }
        }
        //Net Profit
        if (pvsdto.isVarNetProfit()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                netProfitValue = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_NET_PROFITE.toString(), obj, obj, NumericConstants.FORTY_SEVEN, totalPrPos, NumericConstants.FORTY_SEVEN, detailPrPos, pvsdto, AMOUNT,netProfitValue,true);
                pvList.add(netProfitValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                netProfitVar = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_NET_PROFITE.toString(), obj, obj, NumericConstants.FORTY_SEVEN, totalPrPos, NumericConstants.FORTY_SEVEN, detailPrPos, pvsdto, AMOUNT,netProfitVar,true);
                pvList.add(netProfitVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                netProfitPer = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_NET_PROFITE.toString(), obj, obj, NumericConstants.FORTY_SEVEN, NumericConstants.THIRTY, NumericConstants.FORTY_SEVEN, detailPrPos, pvsdto, RATE,netProfitPer,true);
                pvList.add(netProfitPer);
            }
        }
        return projectionVarianceDTO;
    } 
  
    
    public ProjectionVarianceDTO getCommonCustomizedDTO_Details(final String groupName, Object[] obj, Object[] dataObj, final int totalListPostion, final int totalPrPos, final int detailsListPos, final int detailsPrPos, PVSelectionDTO pvsdto, final DecimalFormat FORMAT,ProjectionVarianceDTO pvDTO,boolean addFlag) {
        boolean actualBasis = ("Actuals").equals(pvsdto.getComparisonBasis());
        int frequencyDivision = pvsdto.getFrequencyDivision();
        if (addFlag) {
            if (pvsdto.getVarIndicator().equals(Constants.VALUE)) {
                pvDTO.setGroup(groupName.concat(Constants.VALUE));
            } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                pvDTO.setGroup(groupName.concat(Constants.VARIANCE));
            } else if (pvsdto.getVarIndicator().equals(Constants.CHANGE)) {
                pvDTO.setGroup(groupName.concat(Constants.CHANGE));
            } 
        }
        List<Integer> priorList = new ArrayList<Integer>(pvsdto.getProjIdList());
        if (obj != null) {
            if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == NumericConstants.FOUR) {
                        commonColumn = " Q" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == NumericConstants.TWO) {
                        commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == NumericConstants.TWELVE) {
                    String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                    commonColumn = monthName.toLowerCase() + obj[0];
                }
                String actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[totalListPostion - 1])));
                String currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[totalListPostion])));
                if (pvsdto.getVarIndicator().equals(Constants.VALUE)) {
                    if (obj[totalListPostion] == null) {
                        String baseValue = getFormattedValue(FORMAT, "0.00");
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), groupName.contains("%") ? baseValue + PERCENT : baseValue);
                    } else {
                        String baseValue = getFormattedValue(FORMAT, actualValue);
                        pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), groupName.contains("%") ? baseValue + PERCENT : baseValue);
                        baseValue = getFormattedValue(FORMAT, currentValue);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), groupName.contains("%") ? baseValue + PERCENT : baseValue);
                    }
                }

                if (actualBasis) {
                    if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
//                        for CURRENT
                        String val = getVariance(actualValue, currentValue, FORMAT);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), groupName.contains("%") ? val + PERCENT : val);
                    }
                    if (pvsdto.getVarIndicator().equals(Constants.CHANGE)) {
//                        for CURRENT
                        String val = getPerChange(actualValue, currentValue, FORMAT);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), val + PERCENT);
                    }
                }
                    for (int j = 0; j < priorList.size(); j++) {
                        String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[totalListPostion + ((j + 1) * totalPrPos)])));
                        if (pvsdto.getVarIndicator().equals(Constants.VALUE)) {
                          if(obj[totalListPostion + ((j + 1) * totalPrPos)]==null){
                              String baseValue = getFormattedValue(FORMAT, "0.00");
                              pvDTO.addStringProperties(commonColumn + priorList.get(j), groupName.contains("%") ? baseValue + PERCENT : baseValue);
                          }else{
                            String val = getFormattedValue(FORMAT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j),groupName.contains("%") ? val+PERCENT : val);
                          }
                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            System.out.println("variance=================");
                           if(obj[totalListPostion + ((j + 1) * totalPrPos)]==null){
                               String baseValue = getFormattedValue(FORMAT, "0.00");
                               pvDTO.addStringProperties(commonColumn + priorList.get(j), groupName.contains("%") ? baseValue + PERCENT : baseValue);
                           }else{
                           if (actualBasis) {
                                    String val = getVariance(actualValue, priorVal, FORMAT);
                                    pvDTO.addStringProperties(commonColumn + priorList.get(j), groupName.contains("%") ? val + PERCENT : val);
                                } else {
                                    String val = getVariance(currentValue, priorVal, FORMAT);
                                    pvDTO.addStringProperties(commonColumn + priorList.get(j), groupName.contains("%") ? val + PERCENT : val);
                                }
                           }
                        } else {
                           if(obj[totalListPostion + ((j + 1) * totalPrPos)]==null){
                               String baseValue = getFormattedValue(FORMAT, "0.00");
                                pvDTO.addStringProperties(commonColumn + priorList.get(j), groupName.contains("%") ? baseValue + PERCENT : baseValue);
                           }else{
                            if (actualBasis) {
                                    String baseValu = getPerChange(actualValue, priorVal, RATE_PER);
                                    pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                                } else {
                                    String baseValu = getPerChange(currentValue, priorVal, RATE_PER);
                                    pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                                }
                           }

                        }
                    }
            } else {
                 obj=dataObj;
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == NumericConstants.FOUR) {
                        commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == NumericConstants.TWO) {
                        commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == NumericConstants.TWELVE) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                    String actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[detailsListPos - 1])));
                    String currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[detailsListPos])));
                     if (pvsdto.getVarIndicator().equals(Constants.VALUE)) {
                       if (obj[detailsListPos] == null) {
                                String baseValue = getFormattedValue(FORMAT, "0.00");
                                pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), groupName.contains("%") ? baseValue + PERCENT : baseValue);
                            } else {
                                String baseValue = getFormattedValue(FORMAT, actualValue);
                                pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), groupName.contains("%") ? baseValue + PERCENT : baseValue);
                                baseValue = getFormattedValue(FORMAT, currentValue);
                                pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), groupName.contains("%") ? baseValue + PERCENT : baseValue);
                            }
                    }
                   if (actualBasis) {
                            if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
//                        for ACTUAL
                                String val = getVariance(actualValue, currentValue, FORMAT);
                                pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), groupName.contains("%") ? val + PERCENT : val);
                            }
                            if (pvsdto.getVarIndicator().equals(Constants.CHANGE)) {
//                        for CURRENT
                                String val = getPerChange(actualValue, currentValue, FORMAT);
                                pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), val + PERCENT);
                            }
                        }
                  for (int j = 0; j < priorList.size(); j++) {
                      String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[detailsListPos + ((j + 1) * detailsPrPos)])));
                        if (pvsdto.getVarIndicator().equals(Constants.VALUE)) {
                          if(obj[detailsListPos + ((j + 1) * detailsPrPos)]==null){
                               String baseValue = getFormattedValue(FORMAT, "0.00");
                               pvDTO.addStringProperties(commonColumn + priorList.get(j), groupName.contains("%") ? baseValue + PERCENT : baseValue);
                          }else{
                            String val = getFormattedValue(FORMAT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), groupName.contains("%") ? val+PERCENT : val);
                          }
                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                                  if (actualBasis) {
                                        String val = getVariance(actualValue, priorVal, FORMAT);
                                        pvDTO.addStringProperties(commonColumn + priorList.get(j), groupName.contains("%") ? val + PERCENT : val);
                                    } else {
                                        String val = getVariance(currentValue, priorVal, FORMAT);
                                        pvDTO.addStringProperties(commonColumn + priorList.get(j), groupName.contains("%") ? val + PERCENT : val);
                                    }
                        } else {
                                if (actualBasis) {
                                    String baseValu = getPerChange(actualValue, priorVal, RATE_PER);
                                    pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                                } else {
                                    String baseValu = getPerChange(currentValue, priorVal, RATE_PER);
                                    pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                                }

                        }
                    }
            }
        }
        return pvDTO;
    }
    
    
    public void updateCustPeriodVariance_Details(List<ProjectionVarianceDTO> pvList,final PVSelectionDTO pvsdto, final Object[] obj) {
       int listIndex = 1;

        //Ex fac product
         if (pvsdto.isVarExFacSales()) {
            if (selection.isColValue()) {
                selection.setVarIndicator(Constants.VALUE);
                exFacValue = pvList.get(listIndex++);
                exFacValue = getCommonCustomizedDTO_Details(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), obj, obj, NumericConstants.FIVE, totalPrPos, NumericConstants.FIVE, detailPrPos, pvsdto, AMOUNT,exFacValue,true);
            }
            if (selection.isColVariance()) {
                selection.setVarIndicator(VARIANCE.getConstant());
               exFacVar = pvList.get(listIndex++);
                exFacVar = getCommonCustomizedDTO_Details(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), obj, obj, NumericConstants.FIVE, totalPrPos, NumericConstants.FIVE, detailPrPos, pvsdto, AMOUNT,exFacVar,false);
            }
            if (selection.isColPercentage()) {
                selection.setVarIndicator(Constants.CHANGE);
              exFacPer = pvList.get(listIndex++);
               exFacPer = getCommonCustomizedDTO_Details(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), obj, obj, NumericConstants.FIVE, totalPrPos, NumericConstants.FIVE, detailPrPos, pvsdto, RATE,exFacPer,false);
            }
            
         }
//           
//           // Customer ExFac Sales for detail start 
            if (pvsdto.isVarExFacCustomer()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
               ProjectionVarianceDTO  custExFacValue = pvList.get(listIndex++);
                custExFacValue = getCommonCustomizedDTO_Details(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), obj, obj, NumericConstants.FIFTY_ONE, totalPrPos, NumericConstants.FIFTY_ONE, detailPrPos, pvsdto, AMOUNT,custExFacValue,false);
                pvList.add(custExFacValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                 ProjectionVarianceDTO  custExFacVar = pvList.get(listIndex++);
                custExFacVar = getCommonCustomizedDTO_Details(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), obj, obj, NumericConstants.FIFTY_ONE, totalPrPos, NumericConstants.FIFTY_ONE, detailPrPos, pvsdto, AMOUNT,custExFacVar,false);
              pvList.add(custExFacVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                  ProjectionVarianceDTO  custExFacPer = pvList.get(listIndex++);
                custExFacPer = getCommonCustomizedDTO_Details(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), obj, obj, NumericConstants.FIFTY_ONE, totalPrPos, NumericConstants.FIFTY_ONE, detailPrPos, pvsdto, RATE,custExFacPer,false);
                pvList.add(custExFacPer);
            }  
            }
            
            //Demand - start
            if (pvsdto.isVarDemandSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
               demandValue = pvList.get(listIndex++);
               demandValue = getCommonCustomizedDTO_Details(Constants.PVVariables.DEMAND.toString(), obj, obj, NumericConstants.THIRTY_ONE, totalPrPos, NumericConstants.THIRTY_ONE, detailPrPos, pvsdto, AMOUNT,demandValue,false);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                demandVar = pvList.get(listIndex++);
                demandVar = getCommonCustomizedDTO_Details(Constants.PVVariables.DEMAND.toString(), obj, obj, NumericConstants.THIRTY_ONE, totalPrPos, NumericConstants.THIRTY_ONE, detailPrPos, pvsdto, AMOUNT,demandVar,false);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                demandPer = pvList.get(listIndex++);
                demandPer = getCommonCustomizedDTO_Details(Constants.PVVariables.DEMAND.toString(), obj, obj, NumericConstants.THIRTY_ONE, totalPrPos, NumericConstants.THIRTY_ONE, detailPrPos, pvsdto, RATE,demandPer,false);
            }  
            }
            
              // Adjusted Demand  start 
              if (pvsdto.isVarAdjDemand()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                adjDamandVale = pvList.get(listIndex++);
                adjDamandVale = getCommonCustomizedDTO_Details(Constants.PVVariables.ADJUSTED_DEMAND.toString(), obj, obj, NumericConstants.FORTY_NINE, totalPrPos, NumericConstants.FORTY_NINE, detailPrPos, pvsdto, AMOUNT,adjDamandVale,false);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                adjDemandVar = pvList.get(listIndex++);
                adjDemandVar = getCommonCustomizedDTO_Details(Constants.PVVariables.ADJUSTED_DEMAND.toString(), obj, obj, NumericConstants.FORTY_NINE, totalPrPos, NumericConstants.FORTY_NINE, detailPrPos, pvsdto, AMOUNT,adjDemandVar,false);
                 
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                adjdemandPer = pvList.get(listIndex++);
                adjdemandPer = getCommonCustomizedDTO_Details(Constants.PVVariables.ADJUSTED_DEMAND.toString(), obj, obj, NumericConstants.FORTY_NINE, totalPrPos, NumericConstants.FORTY_NINE, detailPrPos, pvsdto, RATE,adjdemandPer,false);
            }
              }
            
            //Inv with drawal summary
             if (pvsdto.isVarInvSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                 invWithValue = pvList.get(listIndex++);
                invWithValue = getCommonCustomizedDTO_Details(Constants.PVVariables.INVENTORY_SUMMARY.toString(), obj, obj, NumericConstants.THIRTY_THREE, totalPrPos, NumericConstants.THIRTY_THREE, detailPrPos, pvsdto, AMOUNT,invWithValue,false);
               
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                 invWithVar = pvList.get(listIndex++);
                invWithVar = getCommonCustomizedDTO_Details(Constants.PVVariables.INVENTORY_SUMMARY.toString(), obj, obj, NumericConstants.THIRTY_THREE, totalPrPos, NumericConstants.THIRTY_THREE, detailPrPos, pvsdto, AMOUNT,invWithVar,false);
              
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                invWithPer = pvList.get(listIndex++);
                invWithPer = getCommonCustomizedDTO_Details(Constants.PVVariables.INVENTORY_SUMMARY.toString(), obj, obj, NumericConstants.THIRTY_THREE, totalPrPos, NumericConstants.THIRTY_THREE, detailPrPos, pvsdto, RATE,invWithPer,false);
            }
            }   
            
            // Inventary withdrawal Details for detail start 
            if (pvsdto.isVarIwDetails()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                 iwDetialsValue = pvList.get(listIndex++);
                iwDetialsValue = getCommonCustomizedDTO_Details(Constants.PVVariables.INVENTORY_DETAILS.toString(), obj, obj, NumericConstants.FIFTY_THREE, totalPrPos, NumericConstants.FIFTY_THREE, detailPrPos, pvsdto, AMOUNT,iwDetialsValue,false);
               
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                iwDetialsVar = pvList.get(listIndex++);
                iwDetialsVar = getCommonCustomizedDTO_Details(Constants.PVVariables.INVENTORY_DETAILS.toString(), obj, obj, NumericConstants.FIFTY_THREE, totalPrPos, NumericConstants.FIFTY_THREE, detailPrPos, pvsdto, AMOUNT,iwDetialsVar,false);
                
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                iwDetialsPer = pvList.get(listIndex++);
                iwDetialsPer = getCommonCustomizedDTO_Details(Constants.PVVariables.INVENTORY_DETAILS.toString(), obj, obj, NumericConstants.FIFTY_THREE, totalPrPos, NumericConstants.FIFTY_THREE, detailPrPos, pvsdto, RATE,iwDetialsPer,false);
            }
            } 
//            
//          //% Of Ex Factory Product
        if (pvsdto.isVarPerExFacSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                perExFacProductValue=pvList.get(listIndex++);
                perExFacProductValue = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), obj, obj, NumericConstants.FIFTEEN, totalPrPos, NumericConstants.FIFTEEN, detailPrPos, pvsdto, RATE,perExFacProductValue,false);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                perExFacProductVar=pvList.get(listIndex++);
                perExFacProductVar = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), obj, obj, NumericConstants.FIFTEEN, totalPrPos, NumericConstants.FIFTEEN, detailPrPos, pvsdto, RATE,perExFacProductVar,false);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                perExFacProductPer=pvList.get(listIndex++);
                perExFacProductPer = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), obj, obj, NumericConstants.FIFTEEN, totalPrPos, NumericConstants.FIFTEEN, detailPrPos, pvsdto, RATE,perExFacProductPer,false);
               
            }
        }  
//            
//            
        // % Of Ex factory customer
        if (pvsdto.isVarPerExFacCustomer()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                 perExFacCustValue=pvList.get(listIndex++);
                perExFacCustValue = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), obj, obj, NumericConstants.FIFTY_SEVEN, totalPrPos, NumericConstants.FIFTY_SEVEN, detailPrPos, pvsdto, RATE,perExFacCustValue,false);
               
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                  perExFacCustVariance=pvList.get(listIndex++);
                perExFacCustVariance = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), obj, obj, NumericConstants.FIFTY_SEVEN, totalPrPos, NumericConstants.FIFTY_SEVEN, detailPrPos, pvsdto, RATE,perExFacCustVariance,false);
               
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                  perExFacCustPer=pvList.get(listIndex++);
                perExFacCustPer = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), obj, obj, NumericConstants.FIFTY_SEVEN, totalPrPos, NumericConstants.FIFTY_SEVEN, detailPrPos, pvsdto, RATE,perExFacCustPer,false);
                 
            }
        }
//        // % Of Demand Sales
        if (pvsdto.isVarPerDemandSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                  perDemandValue=pvList.get(listIndex++);
                perDemandValue = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_DEMAND.toString(), obj, obj, NumericConstants.THIRTY_FIVE, totalPrPos, NumericConstants.THIRTY_FIVE, detailPrPos, pvsdto, RATE,perDemandValue,false);
                
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                  perDemandVar=pvList.get(listIndex++);
                perDemandVar = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_DEMAND.toString(), obj, obj, NumericConstants.THIRTY_FIVE, totalPrPos, NumericConstants.THIRTY_FIVE, detailPrPos, pvsdto, RATE,perDemandVar,false);
                 
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                  perDemandPer=pvList.get(listIndex++);
                perDemandPer = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_DEMAND.toString(), obj, obj, NumericConstants.THIRTY_FIVE, totalPrPos, NumericConstants.THIRTY_FIVE, detailPrPos, pvsdto, RATE,perDemandPer,false);
                 
            }
        }
        // % Of Adjusted Demand 
        if (pvsdto.isVarPerAdjDemand()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                   perAdjDemandValue=pvList.get(listIndex++);
                perAdjDemandValue = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), obj, obj, NumericConstants.FIFTY_FIVE, totalPrPos, NumericConstants.FIFTY_FIVE, detailPrPos, pvsdto, RATE,perAdjDemandValue,false);
                
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                   perAdjDemandVar=pvList.get(listIndex++);
                perAdjDemandVar = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), obj, obj, NumericConstants.FIFTY_FIVE, totalPrPos, NumericConstants.FIFTY_FIVE, detailPrPos, pvsdto, RATE,perAdjDemandVar,false);
               
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                 perAdjDemandPer=pvList.get(listIndex++);
                perAdjDemandPer= getCommonCustomizedDTO_Details(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), obj, obj, NumericConstants.FIFTY_FIVE, totalPrPos, NumericConstants.FIFTY_FIVE, detailPrPos, pvsdto, RATE,perAdjDemandPer,false);
                 
            }
        }
        // % Of Inv Summary
        if (pvsdto.isVarPerInvSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                   perInvWithSummaryValue=pvList.get(listIndex++);
                perInvWithSummaryValue = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), obj, obj, NumericConstants.THIRTY_SEVEN, totalPrPos, NumericConstants.THIRTY_SEVEN, detailPrPos, pvsdto, RATE,perInvWithSummaryValue,false);
              
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                  perInvWithSummaryVar=pvList.get(listIndex++);
                perInvWithSummaryVar = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), obj, obj, NumericConstants.THIRTY_SEVEN, totalPrPos, NumericConstants.THIRTY_SEVEN, detailPrPos, pvsdto, RATE,perInvWithSummaryVar,false);
               
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                  perInvWithSummaryPer=pvList.get(listIndex++);
                perInvWithSummaryPer = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), obj, obj, NumericConstants.THIRTY_SEVEN, totalPrPos, NumericConstants.THIRTY_SEVEN, detailPrPos, pvsdto, RATE,perInvWithSummaryPer,false);
                
            }
        }
        // % Of Inventry withdrawal Detials 
        if (pvsdto.isVarPerIwDetails()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                   perIwDetialsValue=pvList.get(listIndex++);
                perIwDetialsValue = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), obj, obj, NumericConstants.FIFTY_NINE, totalPrPos, NumericConstants.FIFTY_NINE, detailPrPos, pvsdto, RATE,perIwDetialsValue,false);
               
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                 perIwDetialsVar=pvList.get(listIndex++);
                perIwDetialsVar = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), obj, obj, NumericConstants.FIFTY_NINE, totalPrPos, NumericConstants.FIFTY_NINE, detailPrPos, pvsdto, RATE,perIwDetialsVar,false);
                
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                  perIwDetialsPer=pvList.get(listIndex++);
                perIwDetialsPer = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), obj, obj, NumericConstants.FIFTY_NINE, totalPrPos, NumericConstants.FIFTY_NINE, detailPrPos, pvsdto, RATE,perIwDetialsPer,false);
                
            }
        }
        
        
              //Contract Sales
        if (pvsdto.isVarContractsales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                   contractSalesValue=pvList.get(listIndex++);
                   contractSalesValue = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), obj, obj, NumericConstants.SEVEN, totalPrPos, NumericConstants.SEVEN, detailPrPos, pvsdto, AMOUNT,contractSalesValue,false);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
               contractSalesVar=pvList.get(listIndex++);
                    contractSalesVar = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), obj, obj, NumericConstants.SEVEN, totalPrPos, NumericConstants.SEVEN, detailPrPos, pvsdto, AMOUNT,contractSalesVar,false);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                contractSalesPer = pvList.get(listIndex++);
                contractSalesPer = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), obj, obj, NumericConstants.SEVEN, totalPrPos, NumericConstants.SEVEN, detailPrPos, pvsdto, RATE, contractSalesPer, false);
            }
        }
        //Contract Units
        if (pvsdto.isVarContractUnits()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                 contractUnitValue=pvList.get(listIndex++);
                contractUnitValue = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(), obj, obj, NumericConstants.NINE, totalPrPos, NumericConstants.NINE, detailPrPos, pvsdto, AMOUNT_UNITS,contractUnitValue,false);
             
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                 contractUnitVar=pvList.get(listIndex++);
                contractUnitVar = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(), obj, obj, NumericConstants.NINE, totalPrPos, NumericConstants.NINE, detailPrPos, pvsdto, AMOUNT_UNITS,contractUnitVar,false);
               
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                 contractUnitPer=pvList.get(listIndex++);
                contractUnitPer = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(), obj, obj, NumericConstants.NINE, totalPrPos, NumericConstants.NINE, detailPrPos, pvsdto, RATE,contractUnitPer,false);
              
            }
        }
        
//        //Discount $ 
        if (pvsdto.isVarDisAmount()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                discountDollarValue=pvList.get(listIndex++);
                discountDollarValue = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_DIS_AMOUNT.toString(), obj, obj, NumericConstants.ELEVEN, totalPrPos, NumericConstants.ELEVEN, detailPrPos, pvsdto, AMOUNT,discountDollarValue,false);
               
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                discountDollarVar=pvList.get(listIndex++);
                discountDollarVar = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_DIS_AMOUNT.toString(), obj, obj, NumericConstants.ELEVEN, totalPrPos, NumericConstants.ELEVEN, detailPrPos, pvsdto, AMOUNT,discountDollarVar,false);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                discountDollarPer=pvList.get(listIndex++);
                discountDollarPer = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_DIS_AMOUNT.toString(), obj, obj, NumericConstants.ELEVEN, totalPrPos, NumericConstants.ELEVEN, detailPrPos, pvsdto, RATE,discountDollarPer,false);
            }
        }
        //Discount % 
        if (pvsdto.isVarDisRate()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                discountPerValue=pvList.get(listIndex++);
                discountPerValue = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_DIS_RATE.toString(), obj, obj, NumericConstants.THIRTEEN, totalPrPos, NumericConstants.THIRTEEN, detailPrPos, pvsdto, RATE,discountPerValue,false);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                discountPerVar=pvList.get(listIndex++);
                discountPerVar = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_DIS_RATE.toString(), obj, obj, NumericConstants.THIRTEEN, totalPrPos, NumericConstants.THIRTEEN, detailPrPos, pvsdto, RATE,discountPerVar,false);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                discountPerPercent=pvList.get(listIndex++);
                discountPerPercent = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_DIS_RATE.toString(), obj, obj, NumericConstants.THIRTEEN, totalPrPos, NumericConstants.THIRTEEN, detailPrPos, pvsdto, RATE,discountPerPercent,false);
               
            }
        }
        
  
        
        // RPU
        if (pvsdto.isVarRPU()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                 rpuValue=pvList.get(listIndex++);
                rpuValue = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_RPU.toString(), obj, obj, NumericConstants.FORTY_THREE, totalPrPos, NumericConstants.FORTY_THREE, detailPrPos, pvsdto, AMOUNT,rpuValue,false);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                 rpuVar=pvList.get(listIndex++);
                rpuVar = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_RPU.toString(), obj, obj, NumericConstants.FORTY_THREE, totalPrPos, NumericConstants.FORTY_THREE, detailPrPos, pvsdto, AMOUNT,rpuVar,false);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                 rpuPer=pvList.get(listIndex++);
                rpuPer = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_RPU.toString(), obj, obj, NumericConstants.FORTY_THREE, totalPrPos, NumericConstants.FORTY_THREE, detailPrPos, pvsdto, RATE,rpuPer,false);
            }
        }
              //DiscountPerExFactory
        if (pvsdto.isDiscountPerExFactory()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                discountPerExFactoryValue=pvList.get(listIndex++);
                discountPerExFactoryValue = getCommonCustomizedDTO_Details(Constants.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), obj, obj, NumericConstants.SIXTY_THREE, totalPrPos, NumericConstants.SIXTY_THREE, detailPrPos, pvsdto, RATE,discountPerExFactoryValue,false);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                discountPerExFactoryVar=pvList.get(listIndex++);
                discountPerExFactoryVar = getCommonCustomizedDTO_Details(Constants.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), obj, obj, NumericConstants.SIXTY_THREE, totalPrPos, NumericConstants.SIXTY_THREE, detailPrPos, pvsdto, RATE,discountPerExFactoryVar,false);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                discountPerExFactoryPer=pvList.get(listIndex++);
                discountPerExFactoryPer = getCommonCustomizedDTO_Details(Constants.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), obj, obj, NumericConstants.SIXTY_THREE, totalPrPos, NumericConstants.SIXTY_THREE, detailPrPos, pvsdto, RATE,discountPerExFactoryPer,false);
               
            }
        }
        //NetSales 
        if (pvsdto.isVarNetSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                 netSalesValue=pvList.get(listIndex++);
                netSalesValue = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_NETSALES.toString(), obj, obj, NumericConstants.TWENTY_NINE, totalPrPos, NumericConstants.TWENTY_NINE, detailPrPos, pvsdto, AMOUNT,netSalesValue,false);
                
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                 netSalesVar=pvList.get(listIndex++);
               netSalesVar = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_NETSALES.toString(), obj, obj, NumericConstants.TWENTY_NINE, totalPrPos, NumericConstants.TWENTY_NINE, detailPrPos, pvsdto, AMOUNT,netSalesVar,false);
               
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                 netSalesPer=pvList.get(listIndex++);
               netSalesPer = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_NETSALES.toString(), obj, obj, NumericConstants.TWENTY_NINE, totalPrPos, NumericConstants.TWENTY_NINE, detailPrPos, pvsdto, RATE,netSalesPer,false);
                
            }
        }
        
         //NetSalesExFactory 
        if (pvsdto.isNetSalesExFactory()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                 netExFactoryValue=pvList.get(listIndex++);
                 netExFactoryValue = getCommonCustomizedDTO_Details(Constants.PVVariables.NET_SALES_PER_EX_FACTORY.toString(), obj, obj, NumericConstants.SIXTY_ONE, totalPrPos, NumericConstants.SIXTY_ONE, detailPrPos, pvsdto, RATE,netExFactoryValue,false);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                 netExFactoryVar=pvList.get(listIndex++);
                 netExFactoryVar = getCommonCustomizedDTO_Details(Constants.PVVariables.NET_SALES_PER_EX_FACTORY.toString(), obj, obj, NumericConstants.SIXTY_ONE, totalPrPos, NumericConstants.SIXTY_ONE, detailPrPos, pvsdto, RATE,netExFactoryVar,false);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                 netExFactoryPer=pvList.get(listIndex++);
                 netExFactoryPer = getCommonCustomizedDTO_Details(Constants.PVVariables.NET_SALES_PER_EX_FACTORY.toString(), obj, obj, NumericConstants.SIXTY_ONE, totalPrPos, NumericConstants.SIXTY_ONE, detailPrPos, pvsdto, RATE,netExFactoryPer,false);
            }
        }
        //COGS
        if (pvsdto.isVarCOGC()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                 cogcValue=pvList.get(listIndex++);
                 cogcValue = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_COGS.toString(), obj, obj, NumericConstants.FORTY_FIVE, totalPrPos, NumericConstants.FORTY_FIVE, detailPrPos, pvsdto, AMOUNT,cogcValue,false);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                 cogcVar=pvList.get(listIndex++);
                 cogcVar = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_COGS.toString(), obj, obj, NumericConstants.FORTY_FIVE, totalPrPos, NumericConstants.FORTY_FIVE, detailPrPos, pvsdto, AMOUNT,cogcVar,false);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                 cogcPer=pvList.get(listIndex++);
                 cogcPer = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_COGS.toString(), obj, obj, NumericConstants.FORTY_FIVE, totalPrPos, NumericConstants.FORTY_FIVE, detailPrPos, pvsdto, RATE,cogcPer,false);
            }
        }
        //Net Profit
        if (pvsdto.isVarNetProfit()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                 netProfitValue=pvList.get(listIndex++);
                 netProfitValue = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_NET_PROFITE.toString(), obj, obj, NumericConstants.FORTY_SEVEN, totalPrPos, NumericConstants.FORTY_SEVEN, detailPrPos, pvsdto, AMOUNT,netProfitValue,false);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                 netProfitVar=pvList.get(listIndex++);
                 netProfitVar = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_NET_PROFITE.toString(), obj, obj, NumericConstants.FORTY_SEVEN, totalPrPos, NumericConstants.FORTY_SEVEN, detailPrPos, pvsdto, AMOUNT,netProfitVar,false);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                 netProfitPer=pvList.get(listIndex++);
                 netProfitPer = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_NET_PROFITE.toString(), obj, obj, NumericConstants.FORTY_SEVEN, totalPrPos, NumericConstants.FORTY_SEVEN, detailPrPos, pvsdto, RATE,netProfitPer,false);
            }
        }
    }
    
       private void calculateDiscount() {
           boolean actualBasis = ("Actuals").equals(selection.getComparisonBasis());
           boolean isPer = selection.getGroup().contains("%");
           String oldDiscount = StringUtils.EMPTY;
           String oldHierarchyNo = StringUtils.EMPTY;
           int count = procRawList_detail_discount.size();
           String commonColumn = StringUtils.EMPTY;
           String newDiscount = StringUtils.EMPTY;
           List<List<ProjectionVarianceDTO>> finaldiscountlist = new ArrayList<List<ProjectionVarianceDTO>>();

           ProjectionVarianceDTO discountDollarValue = new ProjectionVarianceDTO();
           ProjectionVarianceDTO discountDollarVariance = new ProjectionVarianceDTO();
           ProjectionVarianceDTO discountDollarPercent = new ProjectionVarianceDTO();

           ProjectionVarianceDTO discountPerValue = new ProjectionVarianceDTO();
           ProjectionVarianceDTO discountPerVariance = new ProjectionVarianceDTO();
           ProjectionVarianceDTO discountPerPercent = new ProjectionVarianceDTO();

           ProjectionVarianceDTO rpuValue = new ProjectionVarianceDTO();
           ProjectionVarianceDTO rpuVariance = new ProjectionVarianceDTO();
           ProjectionVarianceDTO rpuPercent = new ProjectionVarianceDTO();

           ProjectionVarianceDTO discountPerExfacValue = new ProjectionVarianceDTO();
           ProjectionVarianceDTO discountPerExfacVariance = new ProjectionVarianceDTO();
           ProjectionVarianceDTO discountPerExfacPercent = new ProjectionVarianceDTO();

           List<ProjectionVarianceDTO> discountDollarvalueList = new ArrayList<ProjectionVarianceDTO>();
           List<ProjectionVarianceDTO> discountDollarVarList = new ArrayList<ProjectionVarianceDTO>();
           List<ProjectionVarianceDTO> discountDollarPerList = new ArrayList<ProjectionVarianceDTO>();

           List<ProjectionVarianceDTO> discountPerValueList = new ArrayList<ProjectionVarianceDTO>();
           List<ProjectionVarianceDTO> discountPerVarianceList = new ArrayList<ProjectionVarianceDTO>();
           List<ProjectionVarianceDTO> discountPerPercentList = new ArrayList<ProjectionVarianceDTO>();

           List<ProjectionVarianceDTO> rpuValueList = new ArrayList<ProjectionVarianceDTO>();
           List<ProjectionVarianceDTO> rpuVarianceList = new ArrayList<ProjectionVarianceDTO>();
           List<ProjectionVarianceDTO> rpuPercentList = new ArrayList<ProjectionVarianceDTO>();

           List<ProjectionVarianceDTO> discountPerExfacValueList = new ArrayList<ProjectionVarianceDTO>();
           List<ProjectionVarianceDTO> discountPerExfacVarianceList = new ArrayList<ProjectionVarianceDTO>();
           List<ProjectionVarianceDTO> discountPerExfacPercentList = new ArrayList<ProjectionVarianceDTO>();

           for (int i = 0; i < count; i++) {
               Object[] obj = (Object[]) procRawList_detail_discount.get(i);
          if(i==0){
              oldHierarchyNo=String.valueOf(obj[0]) + ("null".equals(String.valueOf(obj[obj.length - 1])) ? StringUtils.EMPTY : "$" + String.valueOf(obj[obj.length - 1]));
            }
            String newHierarchyNo = String.valueOf(obj[0]) + ("null".equals(String.valueOf(obj[obj.length - 1])) ? StringUtils.EMPTY : "$" + String.valueOf(obj[obj.length - 1]));
               if (oldHierarchyNo.equals(newHierarchyNo)) {
                   newDiscount = String.valueOf(obj[NumericConstants.FOUR]);
                   if (oldDiscount.equals(newDiscount)) {
                       if (frequencyDivision == NumericConstants.FOUR) {
                           commonColumn = "Q" + obj[NumericConstants.THREE] + StringUtils.EMPTY + obj[NumericConstants.TWO];
                       } else if (frequencyDivision == NumericConstants.TWO) {
                           commonColumn = "S" + obj[NumericConstants.THREE] + StringUtils.EMPTY + obj[NumericConstants.TWO];
                       } else if (frequencyDivision == 1) {
                           commonColumn = StringUtils.EMPTY + obj[NumericConstants.TWO];
                       } else if (frequencyDivision == NumericConstants.TWELVE) {
                           String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[NumericConstants.THREE])) - 1);
                           commonColumn = monthName.toLowerCase() + obj[NumericConstants.TWO];
                       }
                       //discountDollarValue
                     
                           String actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FIVE])));
                           String currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SIX])));
                           String baseValue1 = getFormattedValue(AMOUNT, actualValue);
                           discountDollarValue.addStringProperties(commonColumn + ACTUAL + selection.getCurrentProjId(), baseValue1);
                           baseValue1 = getFormattedValue(AMOUNT, currentValue);
                           discountDollarValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue1);
                           if (actualBasis) {
//                        for CURRENT
                               String val = getVariance(actualValue, currentValue, isPer ? RATE : AMOUNT);
                               discountDollarVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), isPer ? val + PERCENT : val);
//                        for CURRENT
                               val = getPerChange(actualValue, currentValue,RATE);
                               discountDollarPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), val + PERCENT);
                           }
                       //discountPerValue
                   
                           actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SEVEN])));
                           currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.EIGHT])));
                           baseValue1 = getFormattedValue(RATE, actualValue);
                           discountPerValue.addStringProperties(commonColumn + ACTUAL + selection.getCurrentProjId(), baseValue1 + PERCENT);
                           baseValue1 = getFormattedValue_Percent(RATE, currentValue);
                           discountPerValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue1 + PERCENT);
                           if (actualBasis) {
//                        for CURRENT
                               String val = getVariance(actualValue, currentValue, RATE);
                               discountPerVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), val + PERCENT);
//                        for CURRENT
                               val = getPerChange(actualValue, currentValue, RATE);
                               discountPerPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), val + PERCENT);
                           }
                       //rpuValue
                     
                            actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.NINE])));
                            currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.TEN])));
                            baseValue1 = getFormattedValue(AMOUNT, actualValue);
                           rpuValue.addStringProperties(commonColumn + ACTUAL + selection.getCurrentProjId(), baseValue1);
                           baseValue1 = getFormattedValue(AMOUNT, currentValue);
                           rpuValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue1);
                           if (actualBasis) {
//                        for CURRENT
                               String val = getVariance(actualValue, currentValue, isPer ? RATE : AMOUNT);
                               rpuVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), isPer ? val + PERCENT : val);
//                        for CURRENT
                               val = getPerChange(actualValue, currentValue,RATE);
                               rpuPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), val + PERCENT);
                           }

                       //discountPerExfacValue
                       if (obj[NumericConstants.TWELVE] == null) {
                           String baseValue = getFormattedValue(isPer ? RATE : AMOUNT, "0.00");
                           discountPerExfacValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue + PERCENT);
                       } else {
                            actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.ELEVEN])));
                            currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.TWELVE])));
                            baseValue1 = getFormattedValue(RATE, actualValue);
                           discountPerExfacValue.addStringProperties(commonColumn + ACTUAL + selection.getCurrentProjId(), baseValue1 + PERCENT);
                           baseValue1 = getFormattedValue_Percent(RATE, currentValue);
                           discountPerExfacValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue1 + PERCENT);
                           if (actualBasis) {
//                        for CURRENT
                               String val = getVariance(actualValue, currentValue, RATE);
                               discountPerExfacVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), val + PERCENT);
//                        for CURRENT
                               val = getPerChange(actualValue, currentValue, RATE);
                               discountPerExfacPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), val + PERCENT);
                           }
                       }


                       for (int j = 0; j < priorList.size(); j++) {
                           //discountDollarValue 
                           if (obj[index + ((j + 1) * discindex)] == null) {
                               String baseValue = getFormattedValue(isPer ? RATE : AMOUNT, "0.00");
                               discountDollarValue.addStringProperties(commonColumn + priorList.get(j), isPer ? baseValue + PERCENT : baseValue);
                           } else {
                               String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * discindex)])));
                               String val = getFormattedValue(AMOUNT, priorVal);
                               discountDollarValue.addStringProperties(commonColumn + priorList.get(j), val);
                           }
                           //discountPerValue
                           if (obj[index + 2 + ((j + 1) * discindex)] == null) {
                               String baseValue = getFormattedValue(RATE, "0.00");
                               discountPerValue.addStringProperties(commonColumn + priorList.get(j), baseValue + PERCENT);
                           } else {
                               String priorValPer = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + 2 + ((j + 1) * discindex)])));
                               String valPer = getFormattedValue(RATE, priorValPer);
                               discountPerValue.addStringProperties(commonColumn + priorList.get(j), valPer + PERCENT);
                           }
                           //rpuValue
                           if (obj[index + NumericConstants.FOUR + ((j + 1) * discindex)] == null) {
                               String baseValue = getFormattedValue(isPer ? RATE : AMOUNT, "0.00");
                               rpuValue.addStringProperties(commonColumn + priorList.get(j), isPer ? baseValue + PERCENT : baseValue);
                           } else {
                               String rpuValues = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + NumericConstants.FOUR + ((j + 1) * discindex)])));
                               String rpuval = getFormattedValue(AMOUNT, rpuValues);
                               rpuValue.addStringProperties(commonColumn + priorList.get(j), rpuval);
                           }
                           //discountPerExfacValue
                           if (obj[index + NumericConstants.SIX + ((j + 1) * discindex)] == null) {
                               String baseValue = getFormattedValue(RATE, "0.00");
                               discountPerExfacValue.addStringProperties(commonColumn + priorList.get(j), baseValue + PERCENT);
                           } else {
                               String priorValPer = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + NumericConstants.SIX + ((j + 1) * discindex)])));
                               String valPer = getFormattedValue(RATE, priorValPer);
                               discountPerExfacValue.addStringProperties(commonColumn + priorList.get(j), valPer + PERCENT);
                           }
                           //discountDollarVariance
                               actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index - 1])));
                               currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));
                               String priorValvar = StringUtils.EMPTY + obj[index + ((j + 1) * discindex)];
                               if (actualBasis) {
                                   String val = getVariance(actualValue, priorValvar, isPer ? RATE : AMOUNT);
                                   discountDollarVariance.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                               } else {
                                   String val = getVariance(currentValue, priorValvar, isPer ? RATE : AMOUNT);
                                   discountDollarVariance.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                               }
                           //discountPerVariance
                               actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[(index + 2) - 1])));
                               currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + 2])));
                               priorValvar = StringUtils.EMPTY + obj[index + 2 + ((j + 1) * discindex)];
                               if (actualBasis) {
                                   String val = getVariance(actualValue, priorValvar, RATE);
                                   discountPerVariance.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               } else {
                                   String val = getVariance(currentValue, priorValvar, RATE);
                                   discountPerVariance.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               }
                           //rpuVariance
                               actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[(index + NumericConstants.FOUR) - 1])));
                               currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + NumericConstants.FOUR])));
                               String priorVal = StringUtils.EMPTY + obj[index + NumericConstants.FOUR + ((j + 1) * discindex)];
                               if (actualBasis) {
                                   String val = getVariance(actualValue, priorVal, isPer ? RATE : AMOUNT);
                                   rpuVariance.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                               } else {
                                   System.out.println("currentValue================="+currentValue);
                                   System.out.println("priorVal-------------------------------"+priorVal);
                                   String val = getVariance(currentValue, priorVal, isPer ? RATE : AMOUNT);
                                   rpuVariance.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                               }
                           //discountPerExfacVariance
                              actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[(index + NumericConstants.SIX) - 1])));
                              currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + NumericConstants.SIX])));
                              priorValvar = StringUtils.EMPTY + obj[index + NumericConstants.SIX + ((j + 1) * discindex)];
                               if (actualBasis) {
                                   String val = getVariance(actualValue, priorValvar, RATE);
                                   discountPerExfacVariance.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               } else {
                                   String val = getVariance(currentValue, priorValvar, RATE);
                                   discountPerExfacVariance.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               }
                           //discountDollarPercent  
                               actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index - 1])));
                               currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));
                               String priorVal2 = StringUtils.EMPTY + obj[index + ((j + 1) * discindex)];
                               if (actualBasis) {
                                   String val = getPerChange(actualValue, priorVal2, RATE_PER);
                                   discountDollarPercent.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               } else {
                                   String val = getPerChange(currentValue, priorVal2, RATE_PER);
                                   discountDollarPercent.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               }
                           //discountPerPercent  
                              actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[(index + 2) - 1])));
                              currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + 2])));
                               String priorValRpu = StringUtils.EMPTY + obj[index + 2 + ((j + 1) * discindex)];
                               if (actualBasis) {
                                   String val = getPerChange(actualValue, priorValRpu, RATE_PER);
                                   discountPerPercent.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               } else {
                                   String val = getPerChange(currentValue, priorValRpu, RATE_PER);
                                   discountPerPercent.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               }
                           //rpuPercent
                               actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[(index + NumericConstants.FOUR) - 1])));
                               currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + NumericConstants.FOUR])));
                               String priorRpuPer = StringUtils.EMPTY + obj[index + NumericConstants.FOUR + ((j + 1) * discindex)];
                               if (actualBasis) {
                                   String val = getPerChange(actualValue, priorRpuPer, RATE_PER);
                                   rpuPercent.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               } else {
                                   String val = getPerChange(currentValue, priorRpuPer, RATE_PER);
                                   rpuPercent.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               }

                           //discountPerExfacPercent  
                               actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[(index + NumericConstants.SIX) - 1])));
                               currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + NumericConstants.SIX])));
                               priorValRpu = StringUtils.EMPTY + obj[index + NumericConstants.SIX + ((j + 1) * discindex)];
                               if (actualBasis) {
                                   String val = getPerChange(actualValue, priorValRpu, RATE_PER);
                                   discountPerExfacPercent.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               } else {
                                   String val = getPerChange(currentValue, priorValRpu, RATE_PER);
                                   discountPerExfacPercent.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               }

                       }
                   } else if (i == 0) {
                       discountDollarValue.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                       discountDollarVariance.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                       discountDollarPercent.setGroup(String.valueOf(obj[NumericConstants.FOUR]));

                       discountPerValue.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                       discountPerVariance.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                       discountPerPercent.setGroup(String.valueOf(obj[NumericConstants.FOUR]));

                       rpuValue.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                       rpuVariance.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                       rpuPercent.setGroup(String.valueOf(obj[NumericConstants.FOUR]));

                       discountPerExfacValue.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                       discountPerExfacVariance.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                       discountPerExfacPercent.setGroup(String.valueOf(obj[NumericConstants.FOUR]));

                       if (frequencyDivision == NumericConstants.FOUR) {
                           commonColumn = "Q" + obj[NumericConstants.THREE] + StringUtils.EMPTY + obj[NumericConstants.TWO];
                       } else if (frequencyDivision == NumericConstants.TWO) {
                           commonColumn = "S" + obj[NumericConstants.THREE] + StringUtils.EMPTY + obj[NumericConstants.TWO];
                       } else if (frequencyDivision == 1) {
                           commonColumn = StringUtils.EMPTY + obj[NumericConstants.TWO];
                       } else if (frequencyDivision == NumericConstants.TWELVE) {
                           String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[NumericConstants.THREE])) - 1);
                           commonColumn = monthName.toLowerCase() + obj[NumericConstants.TWO];
                       }

                      //discountDollarValue
                   
                           String actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FIVE])));
                           String currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SIX])));
                           String baseValue1 = getFormattedValue(AMOUNT, actualValue);
                           discountDollarValue.addStringProperties(commonColumn + ACTUAL + selection.getCurrentProjId(), baseValue1);
                           baseValue1 = getFormattedValue(AMOUNT, currentValue);
                           discountDollarValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue1);
                           if (actualBasis) {
//                        for CURRENT
                               String val = getVariance(actualValue, currentValue, isPer ? RATE : AMOUNT);
                               discountDollarVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), isPer ? val + PERCENT : val);
//                        for CURRENT
                               val = getPerChange(actualValue, currentValue, RATE);
                               discountDollarPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), val + PERCENT);
                           }
                       //discountPerValue
                     
                          actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SEVEN])));
                         currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.EIGHT])));
                          baseValue1 = getFormattedValue(RATE, actualValue);
                           discountPerValue.addStringProperties(commonColumn + ACTUAL + selection.getCurrentProjId(), baseValue1 + PERCENT);
                           baseValue1 = getFormattedValue_Percent(RATE, currentValue);
                           discountPerValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue1 + PERCENT);
                           if (actualBasis) {
//                        for CURRENT
                               String val = getVariance(actualValue, currentValue, RATE);
                               discountPerVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), val + PERCENT);
//                        for CURRENT
                               val = getPerChange(actualValue, currentValue, RATE);
                               discountPerPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), val + PERCENT);
                           }
                       //rpuValue
                      
                            actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.NINE])));
                            currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.TEN])));
                            baseValue1 = getFormattedValue(AMOUNT, actualValue);
                           rpuValue.addStringProperties(commonColumn + ACTUAL + selection.getCurrentProjId(), baseValue1);
                           baseValue1 = getFormattedValue(AMOUNT, currentValue);
                           rpuValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue1);
                           if (actualBasis) {
//                        for CURRENT
                               String val = getVariance(actualValue, currentValue, isPer ? RATE : AMOUNT);
                               rpuVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), isPer ? val + PERCENT : val);
//                        for CURRENT
                               val = getPerChange(actualValue, currentValue, RATE);
                               rpuPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), val + PERCENT);
                           }

                       //discountPerExfacValue
                   
                            actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.ELEVEN])));
                            currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.TWELVE])));
                            baseValue1 = getFormattedValue(RATE, actualValue);
                           discountPerExfacValue.addStringProperties(commonColumn + ACTUAL + selection.getCurrentProjId(), baseValue1 + PERCENT);
                           baseValue1 = getFormattedValue_Percent(RATE, currentValue);
                           discountPerExfacValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue1 + PERCENT);
                           if (actualBasis) {
//                        for CURRENT
                               String val = getVariance(actualValue, currentValue, RATE);
                               discountPerExfacVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), val + PERCENT);
//                        for CURRENT
                               val = getPerChange(actualValue, currentValue,RATE);
                               discountPerExfacPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), val + PERCENT);
                           }


                       for (int j = 0; j < priorList.size(); j++) {
                           //discountDollarValue 
                          
                               String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * discindex)])));
                               String val = getFormattedValue(AMOUNT, priorVal);
                               discountDollarValue.addStringProperties(commonColumn + priorList.get(j), val);
                           //discountPerValue
                        
                               String priorValPer = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + 2 + ((j + 1) * discindex)])));
                               String valPer = getFormattedValue(RATE, priorValPer);
                               discountPerValue.addStringProperties(commonColumn + priorList.get(j), valPer + PERCENT);
                           //rpuValue
                          
                               String rpuValues = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + NumericConstants.FOUR + ((j + 1) * discindex)])));
                               String rpuval = getFormattedValue(AMOUNT, rpuValues);
                               rpuValue.addStringProperties(commonColumn + priorList.get(j), rpuval);
                           //discountPerExfacValue
                         
                               priorValPer = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + NumericConstants.SIX + ((j + 1) * discindex)])));
                                valPer = getFormattedValue(RATE, priorValPer);
                               discountPerExfacValue.addStringProperties(commonColumn + priorList.get(j), valPer + PERCENT);
                           //discountDollarVariance
                          
                                actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index - 1])));
                                currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));
                               String priorValvar = StringUtils.EMPTY + obj[index + ((j + 1) * discindex)];
                               if (actualBasis) {
                                    val = getVariance(actualValue, priorValvar, isPer ? RATE : AMOUNT);
                                   discountDollarVariance.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                               } else {
                                    val = getVariance(currentValue, priorValvar, isPer ? RATE : AMOUNT);
                                   discountDollarVariance.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                               }
                           //discountPerVariance
                           
                                actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[(index + 2) - 1])));
                                currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + 2])));
                                priorValvar = StringUtils.EMPTY + obj[index + 2 + ((j + 1) * discindex)];
                               if (actualBasis) {
                                    val = getVariance(actualValue, priorValvar, RATE);
                                   discountPerVariance.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               } else {
                                    val = getVariance(currentValue, priorValvar, RATE);
                                   discountPerVariance.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               }
                           //rpuVariance
                           
                                actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[(index + NumericConstants.FOUR) - 1])));
                                currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + NumericConstants.FOUR])));
                                priorVal = StringUtils.EMPTY + obj[index + NumericConstants.FOUR + ((j + 1) * discindex)];
                               if (actualBasis) {
                                    val = getVariance(actualValue, priorVal, isPer ? RATE : AMOUNT);
                                   rpuVariance.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                               } else {
                                    val = getVariance(currentValue, priorVal, isPer ? RATE : AMOUNT);
                                   rpuVariance.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                               }
                           //discountPerExfacVariance
                           
                                actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[(index + NumericConstants.SIX) - 1])));
                                currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + NumericConstants.SIX])));
                                priorValvar = StringUtils.EMPTY + obj[index + NumericConstants.SIX + ((j + 1) * discindex)];
                               if (actualBasis) {
                                    val = getVariance(actualValue, priorValvar, RATE);
                                   discountPerExfacVariance.addStringProperties(commonColumn + priorList.get(j),val + PERCENT);
                               } else {
                                    val = getVariance(currentValue, priorValvar, isPer ? RATE : AMOUNT);
                                   discountPerExfacVariance.addStringProperties(commonColumn + priorList.get(j),val + PERCENT);
                               }
                           //discountDollarPercent  
                          
                                actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index - 1])));
                                currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));
                               String priorVal2 = StringUtils.EMPTY + obj[index + ((j + 1) * discindex)];
                               if (actualBasis) {
                                    val = getPerChange(actualValue, priorVal2, RATE_PER);
                                   discountDollarPercent.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               } else {
                                    val = getPerChange(currentValue, priorVal2, RATE_PER);
                                   discountDollarPercent.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               }
                           //discountPerPercent  
                           
                                actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[(index + 2) - 1])));
                                currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + 2])));
                               String priorValRpu = StringUtils.EMPTY + obj[index + 2 + ((j + 1) * discindex)];
                               if (actualBasis) {
                                    val = getPerChange(actualValue, priorValRpu, RATE_PER);
                                   discountPerPercent.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               } else {
                                    val = getPerChange(currentValue, priorValRpu, RATE_PER);
                                   discountPerPercent.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               }
                           //rpuPercent
                           
                                actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[(index + NumericConstants.FOUR) - 1])));
                                currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + NumericConstants.FOUR])));
                               String priorRpuPer = StringUtils.EMPTY + obj[index + NumericConstants.FOUR + ((j + 1) * discindex)];
                               if (actualBasis) {
                                    val = getPerChange(actualValue, priorRpuPer, RATE_PER);
                                   rpuPercent.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                               } else {
                                    val = getPerChange(currentValue, priorRpuPer, RATE_PER);
                                   rpuPercent.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                               }
                           

                           //discountPerExfacPercent  
                         
                                actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[(index + NumericConstants.SIX) - 1])));
                                currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + NumericConstants.SIX])));
                                priorValRpu = StringUtils.EMPTY + obj[index + NumericConstants.SIX + ((j + 1) * discindex)];
                               if (actualBasis) {
                                    val = getPerChange(actualValue, priorValRpu, RATE_PER);
                                   discountPerExfacPercent.addStringProperties(commonColumn + priorList.get(j), val + PERCENT );
                               } else {
                                    val = getPerChange(currentValue, priorValRpu, RATE_PER);
                                   discountPerExfacPercent.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               }

                       }
                       /*New discount means add at List */
                       discountDollarvalueList.add(discountDollarValue);
                       discountDollarVarList.add(discountDollarVariance);
                       discountDollarPerList.add(discountDollarPercent);

                       discountPerValueList.add(discountPerValue);
                       discountPerVarianceList.add(discountPerVariance);
                       discountPerPercentList.add(discountPerPercent);

                       rpuValueList.add(rpuValue);
                       rpuVarianceList.add(rpuVariance);
                       rpuPercentList.add(rpuPercent);

                       discountPerExfacValueList.add(discountPerExfacValue);
                       discountPerExfacVarianceList.add(discountPerExfacVariance);
                       discountPerExfacPercentList.add(discountPerExfacPercent);

                       oldDiscount = newDiscount;
                   } else {

                       /*Empty the DTO */
                       discountDollarValue = new ProjectionVarianceDTO();
                       discountDollarVariance = new ProjectionVarianceDTO();
                       discountDollarPercent = new ProjectionVarianceDTO();

                       discountPerValue = new ProjectionVarianceDTO();
                       discountPerVariance = new ProjectionVarianceDTO();
                       discountPerPercent = new ProjectionVarianceDTO();

                       rpuValue = new ProjectionVarianceDTO();
                       rpuVariance = new ProjectionVarianceDTO();
                       rpuPercent = new ProjectionVarianceDTO();

                       discountPerExfacValue = new ProjectionVarianceDTO();
                       discountPerExfacVariance = new ProjectionVarianceDTO();
                       discountPerExfacPercent = new ProjectionVarianceDTO();

                       oldDiscount = newDiscount;
                       discountDollarValue.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                       discountDollarVariance.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                       discountDollarPercent.setGroup(String.valueOf(obj[NumericConstants.FOUR]));

                       discountPerValue.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                       discountPerVariance.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                       discountPerPercent.setGroup(String.valueOf(obj[NumericConstants.FOUR]));

                       rpuValue.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                       rpuVariance.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                       rpuPercent.setGroup(String.valueOf(obj[NumericConstants.FOUR]));

                       discountPerExfacValue.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                       discountPerExfacVariance.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                       discountPerExfacPercent.setGroup(String.valueOf(obj[NumericConstants.FOUR]));

                       if (frequencyDivision == NumericConstants.FOUR) {
                           commonColumn = "Q" + obj[NumericConstants.THREE] + StringUtils.EMPTY + obj[NumericConstants.TWO];
                       } else if (frequencyDivision == NumericConstants.TWO) {
                           commonColumn = "S" + obj[NumericConstants.THREE] + StringUtils.EMPTY + obj[NumericConstants.TWO];
                       } else if (frequencyDivision == 1) {
                           commonColumn = StringUtils.EMPTY + obj[NumericConstants.TWO];
                       } else if (frequencyDivision == NumericConstants.TWELVE) {
                           String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[NumericConstants.THREE])) - 1);
                           commonColumn = monthName.toLowerCase() + obj[NumericConstants.TWO];
                       }

                         //discountDollarValue
                    
                           String actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FIVE])));
                           String currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SIX])));
                           String baseValue1 = getFormattedValue(AMOUNT, actualValue);
                           discountDollarValue.addStringProperties(commonColumn + ACTUAL + selection.getCurrentProjId(), baseValue1);
                           baseValue1 = getFormattedValue(AMOUNT, currentValue);
                           discountDollarValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue1);
                           if (actualBasis) {
//                        for CURRENT
                               String val = getVariance(actualValue, currentValue, isPer ? RATE : AMOUNT);
                               discountDollarVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), isPer ? val + PERCENT : val);
//                        for CURRENT
                               val = getPerChange(actualValue, currentValue,  RATE);
                               discountDollarPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), isPer ? val + PERCENT : val);
                           }
                       
                       //discountPerValue
                      
                            actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SEVEN])));
                            currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.EIGHT])));
                            baseValue1 = getFormattedValue(RATE, actualValue);
                           discountPerValue.addStringProperties(commonColumn + ACTUAL + selection.getCurrentProjId(), baseValue1 + PERCENT);
                           baseValue1 = getFormattedValue_Percent(RATE, currentValue);
                           discountPerValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue1 + PERCENT);
                           if (actualBasis) {
//                        for CURRENT
                               String val = getVariance(actualValue, currentValue, RATE);
                               discountPerVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), val + PERCENT);
//                        for CURRENT
                               val = getPerChange(actualValue, currentValue, RATE);
                               discountPerPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), val + PERCENT);
                           }
                       
                       //rpuValue
                      
                            actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.NINE])));
                            currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.TEN])));
                            baseValue1 = getFormattedValue(AMOUNT, actualValue);
                           rpuValue.addStringProperties(commonColumn + ACTUAL + selection.getCurrentProjId(), baseValue1);
                           baseValue1 = getFormattedValue(AMOUNT, currentValue);
                           rpuValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue1);
                           if (actualBasis) {
//                        for CURRENT
                               String val = getVariance(actualValue, currentValue, isPer ? RATE : AMOUNT);
                               rpuVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), isPer ? val + PERCENT : val);
//                        for CURRENT
                               val = getPerChange(actualValue, currentValue, RATE);
                               rpuPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), val + PERCENT);
                           }
                       

                       //discountPerExfacValue
                      
                            actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.ELEVEN])));
                            currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.TWELVE])));
                            baseValue1 = getFormattedValue(RATE, actualValue);
                           discountPerExfacValue.addStringProperties(commonColumn + ACTUAL + selection.getCurrentProjId(), baseValue1 + PERCENT);
                           baseValue1 = getFormattedValue_Percent(RATE, currentValue);
                           discountPerExfacValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue1 + PERCENT);
                           if (actualBasis) {
//                        for CURRENT
                               String val = getVariance(actualValue, currentValue, RATE);
                               discountPerExfacVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), val + PERCENT);
//                        for CURRENT
                               val = getPerChange(actualValue, currentValue, RATE);
                               discountPerExfacPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), val + PERCENT);
                           }
                       



                       for (int j = 0; j < priorList.size(); j++) {
                           //discountDollarValue 
                         
                               String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * discindex)])));
                               String val = getFormattedValue(AMOUNT, priorVal);
                               discountDollarValue.addStringProperties(commonColumn + priorList.get(j), val);
                           
                           //discountPerValue
                           
                               String priorValPer = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + 2 + ((j + 1) * discindex)])));
                               String valPer = getFormattedValue(RATE, priorValPer);
                               discountPerValue.addStringProperties(commonColumn + priorList.get(j), valPer + PERCENT);
                           
                           //rpuValue
                          
                               String rpuValues = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + NumericConstants.FOUR + ((j + 1) * discindex)])));
                               String rpuval = getFormattedValue(AMOUNT, rpuValues);
                               rpuValue.addStringProperties(commonColumn + priorList.get(j), rpuval);
                           
                           //discountPerExfacValue
                          
                                priorValPer = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + NumericConstants.SIX + ((j + 1) * discindex)])));
                                valPer = getFormattedValue(RATE, priorValPer);
                               discountPerExfacValue.addStringProperties(commonColumn + priorList.get(j), valPer + PERCENT);
                           
                           //discountDollarVariance
                          
                                actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index - 1])));
                                currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));
                               String priorValvar = StringUtils.EMPTY + obj[index + ((j + 1) * discindex)];
                               if (actualBasis) {
                                    val = getVariance(actualValue, priorValvar, isPer ? RATE : AMOUNT);
                                   discountDollarVariance.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                               } else {
                                    val = getVariance(currentValue, priorValvar, isPer ? RATE : AMOUNT);
                                   discountDollarVariance.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                               }
                           
                           //discountPerVariance
                          
                                actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[(index + 2) - 1])));
                                currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + 2])));
                                priorValvar = StringUtils.EMPTY + obj[index + 2 + ((j + 1) * discindex)];
                               if (actualBasis) {
                                    val = getVariance(actualValue, priorValvar, RATE);
                                   discountPerVariance.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               } else {
                                    val = getVariance(currentValue, priorValvar,RATE);
                                   discountPerVariance.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               }
                           
                           //rpuVariance
                           
                                actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[(index + NumericConstants.FOUR) - 1])));
                                currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + NumericConstants.FOUR])));
                                priorVal = StringUtils.EMPTY + obj[index + NumericConstants.FOUR + ((j + 1) * discindex)];
                               if (actualBasis) {
                                    val = getVariance(actualValue, priorVal, isPer ? RATE : AMOUNT);
                                   rpuVariance.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                               } else {
                                    val = getVariance(currentValue, priorVal, isPer ? RATE : AMOUNT);
                                   rpuVariance.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                               }
                           
                           //discountPerExfacVariance
                         
                                actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[(index + NumericConstants.SIX) - 1])));
                                currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + NumericConstants.SIX])));
                                priorValvar = StringUtils.EMPTY + obj[index + NumericConstants.SIX + ((j + 1) * discindex)];
                               if (actualBasis) {
                                    val = getVariance(actualValue, priorValvar, RATE);
                                   discountPerExfacVariance.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               } else {
                                    val = getVariance(currentValue, priorValvar, RATE);
                                   discountPerExfacVariance.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               }
                           
                           //discountDollarPercent  
                        
                                actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index - 1])));
                                currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));
                               String priorVal2 = StringUtils.EMPTY + obj[index + ((j + 1) * discindex)];
                               if (actualBasis) {
                                    val = getPerChange(actualValue, priorVal2, RATE_PER);
                                   discountDollarPercent.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               } else {
                                    val = getPerChange(currentValue, priorVal2, RATE_PER);
                                   discountDollarPercent.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               }
                           
                           //discountPerPercent  
                         
                                actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[(index + 2) - 1])));
                                currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + 2])));
                               String priorValRpu = StringUtils.EMPTY + obj[index + 2 + ((j + 1) * discindex)];
                               if (actualBasis) {
                                    val = getPerChange(actualValue, priorValRpu, RATE_PER);
                                   discountPerPercent.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               } else {
                                    val = getPerChange(currentValue, priorValRpu, RATE_PER);
                                   discountPerPercent.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               }
                           
                           //rpuPercent
                         
                                actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[(index + NumericConstants.FOUR) - 1])));
                                currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + NumericConstants.FOUR])));
                                String priorRpuPer = StringUtils.EMPTY + obj[index + NumericConstants.FOUR + ((j + 1) * discindex)];
                               if (actualBasis) {
                                    val = getPerChange(actualValue, priorRpuPer, RATE_PER);
                                   rpuPercent.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               } else {
                                    val = getPerChange(currentValue, priorRpuPer, RATE_PER);
                                   rpuPercent.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               }
                           

                           //discountPerExfacPercent  
                          
                                actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[(index + NumericConstants.SIX) - 1])));
                                currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + NumericConstants.SIX])));
                                priorValRpu = StringUtils.EMPTY + obj[index + NumericConstants.SIX + ((j + 1) * discindex)];
                               if (actualBasis) {
                                    val = getPerChange(actualValue, priorValRpu, RATE_PER);
                                   discountPerExfacPercent.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               } else {
                                    val = getPerChange(currentValue, priorValRpu, RATE_PER);
                                   discountPerExfacPercent.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               }
                           }
                       
                       /*New discount means add at List */
                       discountDollarvalueList.add(discountDollarValue);
                       discountDollarVarList.add(discountDollarVariance);
                       discountDollarPerList.add(discountDollarPercent);

                       discountPerValueList.add(discountPerValue);
                       discountPerVarianceList.add(discountPerVariance);
                       discountPerPercentList.add(discountPerPercent);

                       rpuValueList.add(rpuValue);
                       rpuVarianceList.add(rpuVariance);
                       rpuPercentList.add(rpuPercent);

                       discountPerExfacValueList.add(discountPerExfacValue);
                       discountPerExfacVarianceList.add(discountPerExfacVariance);
                       discountPerExfacPercentList.add(discountPerExfacPercent);

                   }

               } else {

                   finaldiscountlist.add(discountDollarvalueList);
                   finaldiscountlist.add(discountDollarVarList);
                   finaldiscountlist.add(discountDollarPerList);

                   finaldiscountlist.add(discountPerValueList);
                   finaldiscountlist.add(discountPerVarianceList);
                   finaldiscountlist.add(discountPerPercentList);

                   finaldiscountlist.add(rpuValueList);
                   finaldiscountlist.add(rpuVarianceList);
                   finaldiscountlist.add(rpuPercentList);

                   finaldiscountlist.add(discountPerExfacValueList);
                   finaldiscountlist.add(discountPerExfacVarianceList);
                   finaldiscountlist.add(discountPerExfacPercentList);

                   String key = oldHierarchyNo;
                   String appendedParentKey = obj[obj.length - 1] == null ? "" : "$" + obj[obj.length - 1].toString();
//                   String key = oldHierarchyNo;
//if(isCustomView){
//key = key + appendedParentKey;
//}
                   discountMapDetails.put(key, finaldiscountlist);
                   oldHierarchyNo = newHierarchyNo;
                   finaldiscountlist = new ArrayList<List<ProjectionVarianceDTO>>();

                   discountDollarvalueList = new ArrayList<ProjectionVarianceDTO>();
                   discountDollarVarList = new ArrayList<ProjectionVarianceDTO>();
                   discountDollarPerList = new ArrayList<ProjectionVarianceDTO>();

                   discountPerValueList = new ArrayList<ProjectionVarianceDTO>();
                   discountPerVarianceList = new ArrayList<ProjectionVarianceDTO>();
                   discountPerPercentList = new ArrayList<ProjectionVarianceDTO>();

                   rpuValueList = new ArrayList<ProjectionVarianceDTO>();
                   rpuVarianceList = new ArrayList<ProjectionVarianceDTO>();
                   rpuPercentList = new ArrayList<ProjectionVarianceDTO>();

                   discountPerExfacValueList = new ArrayList<ProjectionVarianceDTO>();
                   discountPerExfacVarianceList = new ArrayList<ProjectionVarianceDTO>();
                   discountPerExfacPercentList = new ArrayList<ProjectionVarianceDTO>();

                   /*Empty the DTO */
                   discountDollarValue = new ProjectionVarianceDTO();
                   discountDollarVariance = new ProjectionVarianceDTO();
                   discountDollarPercent = new ProjectionVarianceDTO();

                   discountPerValue = new ProjectionVarianceDTO();
                   discountPerVariance = new ProjectionVarianceDTO();
                   discountPerPercent = new ProjectionVarianceDTO();

                   rpuValue = new ProjectionVarianceDTO();
                   rpuVariance = new ProjectionVarianceDTO();
                   rpuPercent = new ProjectionVarianceDTO();

                   discountPerExfacValue = new ProjectionVarianceDTO();
                   discountPerExfacVariance = new ProjectionVarianceDTO();
                   discountPerExfacPercent = new ProjectionVarianceDTO();

                   discountDollarValue.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                   discountDollarVariance.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                   discountDollarPercent.setGroup(String.valueOf(obj[NumericConstants.FOUR]));

                   discountPerValue.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                   discountPerVariance.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                   discountPerPercent.setGroup(String.valueOf(obj[NumericConstants.FOUR]));

                   rpuValue.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                   rpuVariance.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                   rpuPercent.setGroup(String.valueOf(obj[NumericConstants.FOUR]));

                   discountPerExfacValue.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                   discountPerExfacVariance.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                   discountPerExfacPercent.setGroup(String.valueOf(obj[NumericConstants.FOUR]));

                   newDiscount = String.valueOf(obj[NumericConstants.FOUR]);
                   if (frequencyDivision == NumericConstants.FOUR) {
                       commonColumn = "Q" + obj[NumericConstants.THREE] + StringUtils.EMPTY + obj[NumericConstants.TWO];
                   } else if (frequencyDivision == NumericConstants.TWO) {
                       commonColumn = "S" + obj[NumericConstants.THREE] + StringUtils.EMPTY + obj[NumericConstants.TWO];
                   } else if (frequencyDivision == 1) {
                       commonColumn = StringUtils.EMPTY + obj[NumericConstants.TWO];
                   } else if (frequencyDivision == NumericConstants.TWELVE) {
                       String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[NumericConstants.THREE])) - 1);
                       commonColumn = monthName.toLowerCase() + obj[NumericConstants.TWO];
                   }

                   //discountDollarValue
                       if (obj[NumericConstants.SIX] == null) {
                           String baseValue = getFormattedValue(isPer ? RATE : AMOUNT, "0.00");
                           discountDollarValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), isPer ? baseValue + PERCENT : baseValue);
                       } else {
                           String actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FIVE])));
                           String currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SIX])));
                           String baseValue1 = getFormattedValue(AMOUNT, actualValue);
                           discountDollarValue.addStringProperties(commonColumn + ACTUAL + selection.getCurrentProjId(), baseValue1);
                           baseValue1 = getFormattedValue(AMOUNT, currentValue);
                           discountDollarValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue1);
                           if (actualBasis) {
//                        for CURRENT
                               String val = getVariance(actualValue, currentValue, isPer ? RATE : AMOUNT);
                               discountDollarVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), isPer ? val + PERCENT : val);
//                        for CURRENT
                               val = getPerChange(actualValue, currentValue, RATE);
                               discountDollarPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), val + PERCENT);
                           }
                       }
                       //discountPerValue
                      
                           String actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SEVEN])));
                           String currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.EIGHT])));
                           String baseValue1 = getFormattedValue(RATE, actualValue);
                           discountPerValue.addStringProperties(commonColumn + ACTUAL + selection.getCurrentProjId(), baseValue1 + PERCENT);
                           baseValue1 = getFormattedValue_Percent(RATE, currentValue);
                           discountPerValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue1 + PERCENT);
                           if (actualBasis) {
//                        for CURRENT
                               String val = getVariance(actualValue, currentValue, RATE);
                               discountPerVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), val + PERCENT);
//                        for CURRENT
                               val = getPerChange(actualValue, currentValue,RATE);
                               discountPerPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), val + PERCENT);
                           }
                       
                       //rpuValue
                    
                            actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.NINE])));
                            currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.TEN])));
                            baseValue1 = getFormattedValue(AMOUNT, actualValue);
                           rpuValue.addStringProperties(commonColumn + ACTUAL + selection.getCurrentProjId(), baseValue1);
                           baseValue1 = getFormattedValue(AMOUNT, currentValue);
                           rpuValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue1);
                           if (actualBasis) {
//                        for CURRENT
                               String val = getVariance(actualValue, currentValue, isPer ? RATE : AMOUNT);
                               rpuVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), isPer ? val + PERCENT : val);
//                        for CURRENT
                               val = getPerChange(actualValue, currentValue, RATE);
                               rpuPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), val + PERCENT);
                           }
                       

                       //discountPerExfacValue
                     
                            actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.ELEVEN])));
                            currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.TWELVE])));
                            baseValue1 = getFormattedValue(RATE, actualValue);
                           discountPerExfacValue.addStringProperties(commonColumn + ACTUAL + selection.getCurrentProjId(), baseValue1 + PERCENT);
                           baseValue1 = getFormattedValue_Percent(RATE, currentValue);
                           discountPerExfacValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue1 + PERCENT);
                           if (actualBasis) {
//                        for CURRENT
                               String val = getVariance(actualValue, currentValue, RATE);
                               discountPerExfacVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), val + PERCENT);
//                        for CURRENT
                               val = getPerChange(actualValue, currentValue, RATE);
                               discountPerExfacPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), val + PERCENT);
                           }
                       



                       for (int j = 0; j < priorList.size(); j++) {
                           //discountDollarValue 
                          
                               String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * discindex)])));
                               String val = getFormattedValue(AMOUNT, priorVal);
                               discountDollarValue.addStringProperties(commonColumn + priorList.get(j), val);
                           
                           //discountPerValue
                         
                               String priorValPer = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + 2 + ((j + 1) * discindex)])));
                               String valPer = getFormattedValue(RATE, priorValPer);
                               discountPerValue.addStringProperties(commonColumn + priorList.get(j), valPer + PERCENT);
                           
                           //rpuValue
                        
                               String rpuValues = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + NumericConstants.FOUR + ((j + 1) * discindex)])));
                               String rpuval = getFormattedValue(AMOUNT, rpuValues);
                               rpuValue.addStringProperties(commonColumn + priorList.get(j), rpuval);
                           
                           //discountPerExfacValue
                          
                                priorValPer = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + NumericConstants.SIX + ((j + 1) * discindex)])));
                                valPer = getFormattedValue(RATE, priorValPer);
                               discountPerExfacValue.addStringProperties(commonColumn + priorList.get(j), valPer + PERCENT);
                           
                           //discountDollarVariance
                         
                                actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index - 1])));
                                currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));
                               String priorValvar = StringUtils.EMPTY + obj[index + ((j + 1) * discindex)];
                               if (actualBasis) {
                                    val = getVariance(actualValue, priorValvar, isPer ? RATE : AMOUNT);
                                   discountDollarVariance.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                               } else {
                                    val = getVariance(currentValue, priorValvar, isPer ? RATE : AMOUNT);
                                   discountDollarVariance.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                               }
                           
                           //discountPerVariance
                          
                                actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[(index + 2) - 1])));
                                currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + 2])));
                                priorValvar = StringUtils.EMPTY + obj[index + 2 + ((j + 1) * discindex)];
                               if (actualBasis) {
                                    val = getVariance(actualValue, priorValvar, RATE);
                                   discountPerVariance.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               } else {
                                    val = getVariance(currentValue, priorValvar, RATE);
                                   discountPerVariance.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               }
                           
                           //rpuVariance
                          
                                actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[(index + NumericConstants.FOUR) - 1])));
                                currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + NumericConstants.FOUR])));
                                priorVal = StringUtils.EMPTY + obj[index + NumericConstants.FOUR + ((j + 1) * discindex)];
                               if (actualBasis) {
                                    val = getVariance(actualValue, priorVal, isPer ? RATE : AMOUNT);
                                   rpuVariance.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                               } else {
                                    val = getVariance(currentValue, priorVal, isPer ? RATE : AMOUNT);
                                   rpuVariance.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                               }
                           
                           //discountPerExfacVariance
                         
                                actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[(index + NumericConstants.SIX) - 1])));
                                currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + NumericConstants.SIX])));
                                priorValvar = StringUtils.EMPTY + obj[index + NumericConstants.SIX + ((j + 1) * discindex)];
                               if (actualBasis) {
                                    val = getVariance(actualValue, priorValvar, RATE);
                                   discountPerExfacVariance.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               } else {
                                    val = getVariance(currentValue, priorValvar, RATE);
                                   discountPerExfacVariance.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               }
                           
                           //discountDollarPercent  
                          
                                actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index - 1])));
                                currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));
                               String priorVal2 = StringUtils.EMPTY + obj[index + ((j + 1) * discindex)];
                               if (actualBasis) {
                                    val = getPerChange(actualValue, priorVal2, RATE_PER);
                                   discountDollarPercent.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               } else {
                                    val = getPerChange(currentValue, priorVal2, RATE_PER);
                                   discountDollarPercent.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               }
                           
                           //discountPerPercent  
                        
                                actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[(index + 2) - 1])));
                                currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + 2])));
                               String priorValRpu = StringUtils.EMPTY + obj[index + 2 + ((j + 1) * discindex)];
                               if (actualBasis) {
                                    val = getPerChange(actualValue, priorValRpu, RATE_PER);
                                   discountPerPercent.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               } else {
                                    val = getPerChange(currentValue, priorValRpu, RATE_PER);
                                   discountPerPercent.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               }
                           
                           //rpuPercent
                         
                                actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[(index + NumericConstants.FOUR) - 1])));
                                currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + NumericConstants.FOUR])));
                               String priorRpuPer = StringUtils.EMPTY + obj[index + NumericConstants.FOUR + ((j + 1) * discindex)];
                               if (actualBasis) {
                                    val = getPerChange(actualValue, priorRpuPer, RATE_PER);
                                   rpuPercent.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               } else {
                                    val = getPerChange(currentValue, priorRpuPer, RATE_PER);
                                   rpuPercent.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               }
                           

                           //discountPerExfacPercent  
                       
                                actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[(index + NumericConstants.SIX) - 1])));
                               currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + NumericConstants.SIX])));
                                priorValRpu = StringUtils.EMPTY + obj[index + NumericConstants.SIX + ((j + 1) * discindex)];
                               if (actualBasis) {
                                    val = getPerChange(actualValue, priorValRpu, RATE_PER);
                                   discountPerExfacPercent.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               } else {
                                    val = getPerChange(currentValue, priorValRpu, RATE_PER);
                                   discountPerExfacPercent.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                               }
                           

                   }
                   discountDollarvalueList.add(discountDollarValue);
                   discountDollarVarList.add(discountDollarVariance);
                   discountDollarPerList.add(discountDollarPercent);

                   discountPerValueList.add(discountPerValue);
                   discountPerVarianceList.add(discountPerVariance);
                   discountPerPercentList.add(discountPerPercent);

                   rpuValueList.add(rpuValue);
                   rpuVarianceList.add(rpuVariance);
                   rpuPercentList.add(rpuPercent);

                   discountPerExfacValueList.add(discountPerExfacValue);
                   discountPerExfacVarianceList.add(discountPerExfacVariance);
                   discountPerExfacPercentList.add(discountPerExfacPercent);
                   oldDiscount = newDiscount;
               }
               if (i == count - 1) {
                   finaldiscountlist.add(discountDollarvalueList);
                   finaldiscountlist.add(discountDollarVarList);
                   finaldiscountlist.add(discountDollarPerList);

                   finaldiscountlist.add(discountPerValueList);
                   finaldiscountlist.add(discountPerVarianceList);
                   finaldiscountlist.add(discountPerPercentList);

                   finaldiscountlist.add(rpuValueList);
                   finaldiscountlist.add(rpuVarianceList);
                   finaldiscountlist.add(rpuPercentList);

                   finaldiscountlist.add(discountPerExfacValueList);
                   finaldiscountlist.add(discountPerExfacVarianceList);
                   finaldiscountlist.add(discountPerExfacPercentList);

                   String key = oldHierarchyNo;
//                   String key = oldHierarchyNo;
                   
//                   if(isCustomView){
//                       String appendedParentKey = obj[obj.length - 1] == null ? "" : "$" + obj[obj.length - 1].toString();
//key = key + appendedParentKey;
//}
                   discountMapDetails.put(key, finaldiscountlist);
               }
               
        }
    }
     
    
     public String getFormattedValue_Percent(DecimalFormat FORMAT, String value) {
        if (value.contains(NULL.getConstant())) {
            value = FORMAT.format(Double.valueOf(ZERO));
        } else {
            value = FORMAT.format(Double.valueOf(value));
        }
        return value;
    } 
    
    private void customize_discount_pivot() {
        int count = procRawList_detail_discount.size();
        String oldHierarchyNo = StringUtils.EMPTY;
        String discountNo = StringUtils.EMPTY;
        String newyear = StringUtils.EMPTY;
        String oldYear = StringUtils.EMPTY;
        String newPeriod = StringUtils.EMPTY;
        String oldPeriod = StringUtils.EMPTY;
        String commonColumn = StringUtils.EMPTY;
        String oldDiscount = StringUtils.EMPTY;
        String newDiscount = StringUtils.EMPTY;
        List<Integer> projList = selection.getProjIdList();
        ProjectionVarianceDTO discountDto = new ProjectionVarianceDTO();
        List<String> discountNames = new ArrayList<String>(selection.getDiscountLevel().equals("Program") ? selection.getDiscountNameList() : selection.getDiscountNameCFF());
        for (int i = 0; i < discountNames.size(); i++) {
            String name = String.valueOf(discountNames.get(i)).replaceAll(" ", StringUtils.EMPTY);
            discountNameMap.put(name, String.valueOf(i));
        }

        Map<String, ProjectionVarianceDTO> periodDiscountMap = new HashMap<String, ProjectionVarianceDTO>();
        for (int i = 0; i < count; i++) {
            Object[] obj = (Object[]) procRawList_detail_discount.get(i);
            if (i == 0) {
                oldHierarchyNo = String.valueOf(obj[0]) + ("null".equals(String.valueOf(obj[obj.length - 1])) ? StringUtils.EMPTY : "$" + String.valueOf(obj[obj.length - 1]));;
//                oldHierarchyNo = oldHierarchyNo.substring(oldHierarchyNo.indexOf('-') + 1);
                oldDiscount = String.valueOf(obj[NumericConstants.FOUR]);
            }
            String newHierarchyNo = String.valueOf(obj[0]) + ("null".equals(String.valueOf(obj[obj.length - 1])) ? StringUtils.EMPTY : "$" + String.valueOf(obj[obj.length - 1]));;
//            newHierarchyNo = newHierarchyNo.substring(newHierarchyNo.indexOf('-') + 1);
            newyear = String.valueOf(obj[NumericConstants.TWO]);
            newPeriod = String.valueOf(obj[NumericConstants.THREE]);
            newDiscount = String.valueOf(obj[NumericConstants.FOUR]);
            /* Below If condition used to check next hierarchy No is same with old hierarchy No*/
            if (oldHierarchyNo.equals(newHierarchyNo)) {
                if (oldYear.equals(newyear) && newPeriod.equals(oldPeriod)) {
                    String discount = String.valueOf(obj[NumericConstants.FOUR]).replaceAll(" ", StringUtils.EMPTY);
                    discount=discount_Name(discount);
                    setBase_Value(discountDto,obj,discount);
                    discountNo = discount_No(discount);
                    customize_PriorList(discountDto, projList, discount, discountNo, obj);
            
                } else{
                    if (i == 0) {
                    discountDto = new ProjectionVarianceDTO();
                    commonColumn=getVisibleColumn_Header(obj);
                    String discount = String.valueOf(obj[NumericConstants.FOUR]).replaceAll(" ", StringUtils.EMPTY);
                    discount=discount_Name(discount);
                    discountNo = discount_No(discount);
                    setBase_Value(discountDto,obj,discount);
                    customize_PriorList(discountDto, projList, discount, discountNo, obj);

                    oldYear = newyear;
                    oldPeriod = newPeriod;

                } else {

                    /*New discount means add at List */
                    periodDiscountMap.put(commonColumn, discountDto);
                    discountDto = new ProjectionVarianceDTO();
                    commonColumn=getVisibleColumn_Header(obj);
                    String discount = String.valueOf(obj[NumericConstants.FOUR]).replaceAll(" ", StringUtils.EMPTY);
                    discount=discount_Name(discount);
                    discountNo = discount_No(discount);
                    setBase_Value(discountDto,obj,discount);
                    customize_PriorList(discountDto, projList, discount, discountNo, obj);

                    oldYear = newyear;
                    oldPeriod = newPeriod;

        }
    }
            }else {
                  periodDiscountMap.put(commonColumn, discountDto);
                  pivotDiscountMap.put(oldHierarchyNo, periodDiscountMap);
                  
                  periodDiscountMap = new HashMap<String, ProjectionVarianceDTO>();
                  discountDto = new ProjectionVarianceDTO();
                  commonColumn=getVisibleColumn_Header(obj);
                  String discount = String.valueOf(obj[NumericConstants.FOUR]).replaceAll(" ", StringUtils.EMPTY);
                   discount=discount_Name(discount);
                  discountNo = discount_No(discount);
                  setBase_Value(discountDto,obj,discount);
                  customize_PriorList(discountDto, projList, discount, discountNo, obj);
                  
                  oldYear = newyear;
                  oldPeriod = newPeriod;
                  
                  oldHierarchyNo=newHierarchyNo;
            }
            if (i == count - 1) {
                periodDiscountMap.put(commonColumn, discountDto);
                pivotDiscountMap.put(oldHierarchyNo, periodDiscountMap);
            }

        }
    }
    
     private String discount_No(String discountName){
         String number=String.valueOf(discountNameMap.get(discountName));
         if("null".equals(number)|| number.equals("NoNumber")){
             return StringUtils.EMPTY;
         }
         return number;
     }
     
        private String discount_Name(String discountName){
         if("RETURNS".equals(discountName)){
             return "Returns";
         }
         return discountName;
     }
   
    private void customize_PriorList(ProjectionVarianceDTO discountDto, List<Integer> projList, String discount, String discountNo, Object[] obj) {
        if (!projList.isEmpty()) {
            for (int j = 1; j <= projList.size(); j++) {
                calculatePivotDiscountPrior(discountDto, obj, discount, "DiscountAmount", NumericConstants.FIVE, (j * NumericConstants.EIGHT), projList.get(j - 1),AMOUNT);
                calculatePivotDiscountPrior(discountDto, obj, discount, "DiscountSales", NumericConstants.SEVEN, (j * NumericConstants.EIGHT), projList.get(j - 1),RATE);
                calculatePivotDiscountPrior(discountDto, obj, discount, "RPU", NumericConstants.NINE, (j * NumericConstants.EIGHT), projList.get(j - 1),AMOUNT);
                calculatePivotDiscountPrior(discountDto, obj, discount, "DiscountPerExFactory", NumericConstants.ELEVEN, (j * NumericConstants.EIGHT), projList.get(j - 1),RATE);

            }
        }
    }
    private void calculatePivotDiscountPrior(ProjectionVarianceDTO discountDto, Object[] obj, String discount, String discountColumn, int actualIndex, int priorIndex, int projId,DecimalFormat format) {
        String discountVarCurrent = StringUtils.EMPTY;
        String discountNo = discount_No(discount);
        boolean isPer = format.equals(RATE) || format.equals(RATE_PER);
        int currentIndex = actualIndex + 1;
        priorIndex = currentIndex + priorIndex;
        String visibleColumn = discountColumn + VAL + discount + discountNo + projId;
        String priorValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[priorIndex])));
        String val = getFormattedValue(format, priorValue);
        discountDto.addStringProperties(visibleColumn, isPer ? val + PERCENT : val);

        if (actualBasis) {
            String actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[actualIndex])));
            String variance = getVariance(actualValue, priorValue, isPer ? RATE : AMOUNT);
            if(discountColumn.contains("RPU")){
            discountVarCurrent = discountColumn + "Variance" + discount + discountNo + projId;
            }else{
            discountVarCurrent = discountColumn + VAR + discount + discountNo + projId;
            }
            discountDto.addStringProperties(discountVarCurrent, isPer ? variance + PERCENT : variance);

            String discountPer = getPerChange(actualValue, priorValue, RATE_PER);
            String discountVarPer = discountColumn + PER + discount + discountNo + projId;
            discountDto.addStringProperties(discountVarPer, discountPer + PERCENT);
        } else {
            String currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[currentIndex])));
            String variance = getVariance(currentValue, priorValue, isPer ? RATE : AMOUNT);
             if(discountColumn.contains("RPU")){
            discountVarCurrent = discountColumn + "Variance" + discount + discountNo + projId;
            }else{
            discountVarCurrent = discountColumn + VAR + discount + discountNo + projId;
            }
            discountDto.addStringProperties(discountVarCurrent, isPer ? variance + PERCENT : variance);

            String discountPer = getPerChange(currentValue, priorValue, RATE_PER);
            String discountVarPer = discountColumn + PER + discount + discountNo + projId;
            discountDto.addStringProperties(discountVarPer,discountPer + PERCENT);
        }
    }
     
    private void setBase_Value(ProjectionVarianceDTO discountDto, Object[] obj, String discount) {

        calculatePivotDiscount(discountDto, obj, discount, "DiscountAmount", NumericConstants.FIVE, selection.getCurrentProjId(),AMOUNT);
        calculatePivotDiscount(discountDto, obj, discount, "DiscountSales", NumericConstants.SEVEN, selection.getCurrentProjId(),RATE_PER);
        calculatePivotDiscount(discountDto, obj, discount, "RPU", NumericConstants.NINE, selection.getCurrentProjId(),AMOUNT);
        calculatePivotDiscount(discountDto, obj, discount, "DiscountPerExFactory", NumericConstants.ELEVEN, selection.getCurrentProjId(),RATE_PER);

    }

    private void calculatePivotDiscount(ProjectionVarianceDTO discountDto, Object[] obj, String discount, String discountColumn, int actualIndex, int projId,DecimalFormat format) {
        String discountNo = discount_No(discount);
        boolean isPer = format.equals(RATE) || format.equals(RATE_PER) || format.equals(RATE_PER_THREE);
        int currentIndex = actualIndex + 1;
        String visibleColumn = discountColumn + VAL + String.valueOf(obj[NumericConstants.FOUR]).replaceAll(" ", StringUtils.EMPTY) + discountNo + CURRENT + projId;
        String currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[currentIndex])));
        String Value = getFormattedValue(format, currentValue);
        discountDto.addStringProperties(visibleColumn, isPer ? Value + PERCENT : Value);
        String actualColumn = discountColumn + VAL + String.valueOf(obj[NumericConstants.FOUR]).replaceAll(" ", StringUtils.EMPTY) + discountNo + ACTUAL + projId;
        String actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[actualIndex])));
        String Value1 = getFormattedValue(format, actualValue);
        discountDto.addStringProperties(actualColumn, isPer ? Value1 + PERCENT : Value1);

        if (actualBasis) {
             String discountVarCurrent = StringUtils.EMPTY;
            String variance = getVariance(actualValue, currentValue, format);
            if(discountColumn.contains("RPU")){
             discountVarCurrent = discountColumn + "Variance" + discount + discountNo + CURRENT + projId;
            }else{
             discountVarCurrent = discountColumn + VAR + discount + discountNo + CURRENT + projId;
            }
            discountDto.addStringProperties(discountVarCurrent, isPer ? variance + PERCENT : variance);

            String discountPer = getPerChange(actualValue, currentValue, RATE_PER);
            String discountVarPer = discountColumn + PER + discount + discountNo + CURRENT + projId;
            discountDto.addStringProperties(discountVarPer, discountPer + PERCENT);

        } 
    }
     
     
    private String getVisibleColumn_Header(Object[] obj) {
        String commonColumn = StringUtils.EMPTY;
        if (frequencyDivision == NumericConstants.FOUR) {
            commonColumn = "Q" + obj[NumericConstants.THREE] + StringUtils.EMPTY + obj[NumericConstants.TWO];
        } else if (frequencyDivision == NumericConstants.TWO) {
            commonColumn = "S" + obj[NumericConstants.THREE] + StringUtils.EMPTY + obj[NumericConstants.TWO];
        } else if (frequencyDivision == 1) {
            commonColumn = StringUtils.EMPTY + obj[NumericConstants.TWO];
        } else if (frequencyDivision == NumericConstants.TWELVE) {
            String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[NumericConstants.THREE])) - 1);
            commonColumn = monthName.toLowerCase() + obj[NumericConstants.TWO];
        }
        return commonColumn;
    }
    private void calculateAndCustomize_detail_pivotForCustomView(List<Object> rawList) {
        String appendedParentKey = "";
        for (int i = 0; i < rawList.size(); i++) {
            Object[] obj = (Object[]) rawList.get(i);
            String key = StringUtils.EMPTY;
            if (isTotal) {
                key = "Total";
            } else if (isCustomView) {
                key = obj[NumericConstants.TWO].toString();
                key = key + appendedParentKey;
//key = key.substring(key.indexOf('-') + 1);
            } else {
                key = obj[NumericConstants.TWO].toString();
//                key = key.substring(key.indexOf('-') + 1);
            }
            List<ProjectionVarianceDTO> pvList = resultMap.get(key);
            List<String> common = HeaderUtils.getCommonColumnHeaderForPV(frequencyDivision, Integer.valueOf(obj[0].toString()), Integer.valueOf(obj[1].toString()));
            String groupId = common.get(1);
            ProjectionVarianceDTO freVarianceDTO = new ProjectionVarianceDTO();
            freVarianceDTO.setGroup(groupId);
            if (pvList == null) {
                //To check condition total or details values
                pvList = new ArrayList();
                addList_pivot(pvList, obj, freVarianceDTO, index, key, groupId);
                appendedParentKey = obj[obj.length - 1] == null ? "" : "$" + obj[obj.length - 1].toString();
                if (isCustomView && !isTotal) {
                    customhierarchyAndTP_keys(obj, key, pvList);
                } else {
                    hierarchyAndTP_keys(obj, key, pvList);
                }
            } else if (obj[obj.length - 1] != null && !isTotal) {
                Object[] temp = rawList.get(i - 1) == null ? new Object[0] : (Object[]) rawList.get(i - 1);
                String currValue = obj[obj.length - 1].toString();
                String tempValue = temp[temp.length - 1] == null ? "oldtempValue"
                        : temp[temp.length - 1].toString();
                if (!currValue.equals(tempValue)) {
                    pvList = new ArrayList();
                    addList_pivot(pvList, obj, freVarianceDTO, index, key, groupId);
                    if (isCustomView && !isTotal) {
                        customhierarchyAndTP_keys(obj, key, pvList);
                    } else {
                        hierarchyAndTP_keys(obj, key, pvList);
                    }
                    appendedParentKey = obj[obj.length - 1] == null ? "" : "$" + obj[obj.length - 1].toString();
                } else {
                    updateList_pivot(pvList, obj, freVarianceDTO, index, key, groupId);
                }
            } else {
                updateList_pivot(pvList, obj, freVarianceDTO, index, key, groupId);
            }
        }
    }

    private void customhierarchyAndTP_keys(Object[] obj, String key, List<ProjectionVarianceDTO> pvList) {
        String parentKey = obj[obj.length - 1] == null ? null : obj[obj.length - 1].toString();
        String newKey;
        if (parentKey == null) {
            hierarchyKeys.add(key);
            resultMap.put(key, pvList);
        } else {
            if (key.contains("$")) {
                key = (key.split("\\$"))[0];
            }
            newKey = key + "$" + parentKey; //$ delimiter for key and parent key
            hierarchyKeys.add(newKey);
            resultMap.put(newKey, pvList);
        }
        if ("Customer".equalsIgnoreCase(String.valueOf(obj[NumericConstants.TWO]))
                || "Trading Partner".equalsIgnoreCase(String.valueOf(obj[NumericConstants.TWO]))) {
            tradingPartnerKeys.add(key);
        }
    }
    
    public String getVariance(String actualValue, String priorVal, DecimalFormat format) {
        Double val = Double.valueOf(isNull(actualValue));
        Double val1 = Double.valueOf(isNull(priorVal));
        String value;
        String variance = StringUtils.EMPTY;
        if (format.equals(RATE) || format.equals(RATE_PER) || format.equals(RATE_PER_THREE)) {
//            if (val1 > val) {
//                value = String.valueOf(roundToFraction(Double.valueOf(val1 - val), 10000));
//                value = roundToFraction(Double.valueOf(value), 100) + "";
//            } else {
                value = String.valueOf(roundToFraction((val - val1), 10000));
                value = roundToFraction(Double.valueOf(value), 100) + "";
//            }
            value = getFormattedValue(format, value);
        } else {
            variance = String.valueOf(Double.valueOf(isNull(actualValue)) - Double.valueOf(isNull(priorVal)));
            value = getFormattedValue(format, variance);
        }
        return value;
    }

    public String getPerChange(String actualValue, String priorVal, DecimalFormat format) {
        DecimalFormat formatter = new DecimalFormat("#,##0.00");
        Double val = Double.valueOf(isNull(actualValue));
        Double val1 = Double.valueOf(isNull(priorVal));
        String value;
        String variance = StringUtils.EMPTY;
        if (format.equals(RATE) || format.equals(RATE_PER) || format.equals(RATE_PER_THREE)) {
//            if (val1 > val) {
//                value = String.valueOf(roundToFraction(Double.valueOf(val1 - val), 10000));
//                value = roundToFraction(Double.valueOf(value), 100) + "";
//            } else {
                value = String.valueOf(roundToFraction((val - val1), 10000));
                value = roundToFraction(Double.valueOf(value), 100) + "";
//            }
        } else {
            variance = String.valueOf(Double.valueOf(isNull(actualValue)) - Double.valueOf(isNull(priorVal)));
            value = getFormattedValue(formatter, variance);
        }
        String priorval = getFormattedValue(formatter, priorVal);

        value = value.replace(",", StringUtils.EMPTY);
        priorval = priorval.replace(",", StringUtils.EMPTY);
        Double perChange = Double.valueOf(value) / Double.valueOf(priorval);
        if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
            perChange = 0.0;
        }
        perChange = perChange * NumericConstants.DOUBLE_HUNDRED;
        String change = String.valueOf(perChange);
        if (change.contains("E")) {
            change = change.substring(0, 3);
        }
        change = getFormattedValue(format, change);
        return change;
    }

    public static double roundToFraction(double x, long fraction) {
        return (double) Math.round(x * fraction) / fraction;
    }

}
                  
