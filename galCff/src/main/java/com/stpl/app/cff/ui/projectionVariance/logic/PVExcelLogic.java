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
import static com.stpl.app.cff.util.Constants.LabelConstants.TOTAL;
import com.stpl.app.cff.util.HeaderUtils;
import com.stpl.app.cff.util.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;


/**
 *
 * @author Nandha Kumar
 */
public class PVExcelLogic {

    public static final Logger LOGGER = Logger.getLogger(PVExcelLogic.class);
    private ProjectionVarianceDTO exFacValue, exFacVar, exFacPer, demandValue, demandVar, demandPer, invWithValue, invWithVar,
            invWithPer, perExFacValue, perExFacVar, perExFacPer, perDemandValue, perDemandVar, perDemandPer, perInvWithValue, perInvWithVar, perInvWithPer, salesValue, salesVar,
            salesPer, unitsValue, unitsVar, unitsPer, disDollValue, disDollVar, disDollPer, disPerValue, disPerVar, disPerPer, rpuValue, rpuVar, rpuPer, netSalesValue, netSalesVar,
            netSalesPer, cogsValue, cogsVar, cogsPer, netProfitValue, netProfitVar, netProfitPer,adjDamandVale,adjDemandVar,adjdemandPer,iwDetialsValue,iwDetialsVar,iwDetialsPer,
            perExFacProductValue,perExFacProductVar,perExFacProductPer,perExFacCustValue,perExFacCustVariance,perExFacCustPer,perAdjDemandValue,perAdjDemandVar,perAdjDemandPer
            ,perInvWithSummaryValue,perInvWithSummaryVar,perInvWithSummaryPer,perIwDetialsValue,perIwDetialsVar,perIwDetialsPer,discountDollarValue,discountDollarVar,discountDollarPer
            ,discountPerValue,discountPerVar,discountPerPercent,contractSalesValue,contractSalesVar,contractSalesPer,contractUnitValue,contractUnitVar,contractUnitPer
            ,cogcValue,cogcVar,cogcPer;

    private final Map<String, List<ProjectionVarianceDTO>> resultMap;
    private Map<String, List<List<ProjectionVarianceDTO>>> discountMapDetails;
    Map<String,Map<String,ProjectionVarianceDTO>> pivotDiscountMap=new HashMap<String,Map<String,ProjectionVarianceDTO>>();
    private final List<String> hierarchyKeys;
    private final List<String> tradingPartnerKeys;
    private final List<String> discountKeys;
    private final PVSelectionDTO selection;
    private static final DecimalFormat AMOUNT = new DecimalFormat("$#,##0.00");
    private static final DecimalFormat AMOUNT_UNITS = new DecimalFormat("#,##0");
    private static final DecimalFormat RATE_PER = new DecimalFormat("#,##0.00");
    private static final String ZERO = "0";
    private static final String CURRENT = "Current";
    private final int index = 5;
    private int frequencyDivision;
    private final int baseColumn_levelName_index = 0;
    private final List<Object[]> procRawList_total = new ArrayList();
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
    private static String DASH = "-";
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
     ProjectionVarianceLogic logic=new ProjectionVarianceLogic();
   
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
        isCustomView = selection.getHierarchyIndicator().equals(StringUtils.EMPTY);
        System.out.println("isCustomView----" + isCustomView);
        if (isCustomView) {
            customView_relationship_hierarchy.putAll(getGroup_customView(selection.getCustomId()));
        }
        System.out.println("selection.getPivotView()::" + selection.getPivotView() + "--");
        boolean isRefresh = isRefreshNeeded(selection.getLevelFilterValue(), selection.getGroupFilter(), selection.getHierarchyIndicator(), selection.getFrequencyDivision());
               if (Constants.LabelConstants.PERIOD.toString().equalsIgnoreCase(selection.getPivotView())) {
            System.out.println("Period VIEW");
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
                 calculateAndCustomize_detail_period(procRawList_detail, procRawList_total_period);
            }
        } else {
            System.out.println("Varable VIEW");
            if (isTotal) {
              System.out.println("Total level" + (isRefresh && procRawList_total.isEmpty()));
                    getTotalRawDataPivot();
                    calculateAndCustomize_total_pivot();
            } else {
                executeProcedure_PRC_PV_SELECTION_PIVOT();
                if (discountFlag) {
                    parameterDto.setViewName("DETAIL_DISCOUNT");
                }
                customize_discount_pivot();
                calculateAndCustomize_detail_pivot(procRawList_detail, procRawList_detail_discount);
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


    private void calculateAndCustomize_detail_period(List<Object> rawList, List<Object> rawList_discount) {
        for (Iterator<Object> it = rawList.listIterator(); it.hasNext();) {
            Object[] obj = (Object[])it.next();
            ProjectionVarianceDTO parentDto=new ProjectionVarianceDTO();
            String key = obj[2].toString();
            key = key.substring(key.indexOf('-') + 1);
             List<ProjectionVarianceDTO> pvList = resultMap.get(key);
            if (pvList == null) {
                //To check condition total or details values
                pvList = new ArrayList();
                getCustPeriodVariance_Details(pvList, selection,parentDto,obj);
                //addCommonCustomizedDTO_Details(pvList, obj, index);
                hierarchyAndTP_keys(obj, key, pvList);
            } else {
              updateCustPeriodVariance_Details(pvList, selection,parentDto,obj);
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
       // boolean isRefresh = true;
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
            Object[] orderedArg = {projectionId, frequency, "VARIANCE", "Period", indicator, null, 0, custId, null};
            rawList = CommonLogic.callProcedure("PRC_CFF_EXCEL_EXPORT", orderedArg);
        } else {
            Object[] orderedArg = {projectionId, frequency, "VARIANCE", "Period", indicator, null, selection.getExcelFilterLevelNo(), null, null};

            rawList = CommonLogic.callProcedure("PRC_CFF_EXCEL_EXPORT", orderedArg);
        }
        procRawList_detail.addAll(rawList);
        if (discountFlag) {
            indicator = selection.getHierarchyIndicator();
            if (indicator.equals(StringUtils.EMPTY)) {
                indicator = "N";
                int custId = selection.getCustomId();
                Object[] orderedArgDiscountCustom = {projectionId, frequency, "VARIANCE", "Period", selection.getDiscountLevel(), selection.getDiscountLevel(), indicator,
                    null, selection.getExcelFilterLevelNo(), custId, null};
                rawListDiscount = CommonLogic.callProcedure("PRC_CFF_DISCOUNT_EXCEL_EXPORT", orderedArgDiscountCustom);
            } else {
                Object[] orderedArgDiscount = {projectionId, frequency, "VARIANCE", "Period", selection.getDiscountLevel(), selection.getDiscountLevel(), selection.getHierarchyIndicator(),
                    null, selection.getExcelFilterLevelNo(), null, null};
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
                 null, selection.getExcelFilterLevelNo(), custId, null,null,order};
             rawList = CommonLogic.callProcedure("PRC_CFF_EXCEL_EXPORT", orderedArg);
         } else {
             Object[] orderedArg = {projectionId, frequency, "VARIANCE", "PIVOT", selection.getHierarchyIndicator(),
                 null, selection.getExcelFilterLevelNo(), null, selection.getPivotStartDate(),order};
             rawList = CommonLogic.callProcedure("PRC_CFF_EXCEL_EXPORT", orderedArg);
         }
         procRawList_detail.addAll(rawList);
         if (discountFlag) {
             indicator = selection.getHierarchyIndicator();
             if (indicator.equals(StringUtils.EMPTY)) {
                 indicator = "N";
                 int custId = selection.getCustomId();
                 Object[] orderedArgDiscount = {projectionId, frequency, "VARIANCE", "PIVOT", selection.getDiscountLevel(), selection.getDiscountLevel(), indicator,
                     null, selection.getExcelFilterLevelNo(), custId, null};
                 rawListDiscount = CommonLogic.callProcedure("PRC_CFF_DISCOUNT_EXCEL_EXPORT", orderedArgDiscount);
             } else {

                 Object[] orderedArgDiscount = {projectionId, frequency, "VARIANCE", "PIVOT", selection.getDiscountLevel(), selection.getDiscountLevel(), selection.getHierarchyIndicator(),
                     null, selection.getExcelFilterLevelNo(), null, null};
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
                groupName = customView_relationship_hierarchy.get(obj[2] == null ? StringUtils.EMPTY : obj[2].toString());
                groupName = groupName == null ? StringUtils.EMPTY : groupName;
            } else {
                groupName = selection.getSessionDTO().getLevelValueDiscription(obj[2].toString(), selection.getHierarchyIndicator());
            }
            detail.setGroup(groupName);
            pvList.add(detail);
        }
        String groupColumn=StringUtils.EMPTY;
        if(frequencyDivision==12){
            
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
                calculateForTotal("CustExFacValue", Constants.VALUE, obj, indexForTotal+25, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("CustExFacVariance", Constants.VARIANCE, obj, indexForTotal+25, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("CustExFacPer", Constants.CHANGE, obj, indexForTotal+25, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
        
        
        //Demand
        if (selection.isVarDemandSales()) {
            if (selection.isColValue()) {
                calculateForTotal("DemandSalesValue", Constants.VALUE, obj, indexForTotal + 13, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("DemandSalesVariance", Constants.VARIANCE, obj, indexForTotal + 13, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("DemandSalesPer", Constants.CHANGE, obj, indexForTotal + 13, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
        
         //Adjusted Demand
        if (selection.isVarAdjDemand()) {
            if (selection.isColValue()) {
                calculateForTotal("AdjDemandValue", Constants.VALUE, obj, indexForTotal + 24, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("AdjDemandVariance", Constants.VARIANCE, obj, indexForTotal + 24, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("AdjDemandPer", Constants.CHANGE, obj, indexForTotal + 24, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
        
        
        //Inventory Withdraw Summary
        if (selection.isVarInvSales()) {
            if (selection.isColValue()) {
                calculateForTotal("InvWithValue", Constants.VALUE, obj, indexForTotal + 14, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("InvWithVariance", Constants.VARIANCE, obj, indexForTotal + 14, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("InvWithPer", Constants.CHANGE, obj, indexForTotal + 14, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
        
              //Inventory Withdraw Details
        if (selection.isVarIwDetails()) {
            if (selection.isColValue()) {
                calculateForTotal("InvWithDetails", Constants.VALUE, obj, indexForTotal + 26, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("InvWithDetails", Constants.VARIANCE, obj, indexForTotal + 26, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("InvWithDetailsPer", Constants.CHANGE, obj, indexForTotal + 26, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
        
        
        //% of Ex-Factory product
        if (selection.isVarPerExFacSales()) {
            if (selection.isColValue()) {
                calculateForTotal("PerExFacValue", Constants.VALUE, obj, indexForTotal + 5, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("PerExFacVariance", Constants.VARIANCE, obj, indexForTotal + 5, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("PerExFacPer", Constants.CHANGE, obj, indexForTotal + 5, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
         //% of Ex-Factory Cust
        if (selection.isVarPerExFacCustomer()) {
            if (selection.isColValue()) {
                calculateForTotal("PerCustExFacValue", Constants.VALUE, obj, indexForTotal + 28, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("PerCustExFacVariance", Constants.VARIANCE, obj, indexForTotal + 28, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("PerCustExFacPer", Constants.CHANGE, obj, indexForTotal + 28, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
        
        
        
        
        
        //% of Demand
        if (selection.isVarPerDemandSales()) {
            if (selection.isColValue()) {
                calculateForTotal("PerDemandSalesValue", Constants.VALUE, obj, indexForTotal + 15, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("PerDemandSalesVariance", Constants.VARIANCE, obj, indexForTotal + 15, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("PerDemandSalesPer", Constants.CHANGE, obj, indexForTotal + 15, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
          //% of Adjusted Demand
        if (selection.isVarPerAdjDemand()) {
            if (selection.isColValue()) {
                calculateForTotal("PerAdjDemandValue", Constants.VALUE, obj, indexForTotal + 27, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("PerAdjDemandSalesVariance", Constants.VARIANCE, obj, indexForTotal + 27, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("PerAdjDemandSalesPer", Constants.CHANGE, obj, indexForTotal + 27, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
        
        //% of inventory Withdraw Summary
        if (selection.isVarPerInvSales()) {
            if (selection.isColValue()) {
                calculateForTotal("PerInvWithValue", Constants.VALUE, obj, indexForTotal + 16, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("PerInvWithVariance", Constants.VARIANCE, obj, indexForTotal + 16, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("PerInvWithPer", Constants.CHANGE, obj, indexForTotal + 16, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
        
             //% of inventory Withdraw details
        if (selection.isVarPerIwDetails()) {
            if (selection.isColValue()) {
                calculateForTotal("PerInvWithValue", Constants.VALUE, obj, indexForTotal + 29, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("PerInvWithVariance", Constants.VARIABLE, obj, indexForTotal + 29, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("PerInvWithPer", Constants.CHANGE, obj, indexForTotal + 29, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
        
        //Contract Sales @ WAC
        if (selection.isVarContractsales()) {
            if (selection.isColValue()) {
                calculateForTotal("ContractSalesWACValue", Constants.VALUE, obj, indexForTotal + 1, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("ContractSalesWACVar", Constants.VARIANCE, obj, indexForTotal + 1, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("ContractSalesWACVarPer", Constants.CHANGE, obj, indexForTotal + 1, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Contract Units
        if (selection.isVarContractUnits()) {
            if (selection.isColValue()) {
                calculateForTotal("ContractUnitsValue", Constants.VALUE, obj, indexForTotal + 2, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("ContractUnitsVar", Constants.VARIANCE, obj, indexForTotal + 2, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("ContractUnitsPer", Constants.CHANGE, obj, indexForTotal + 2, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Discount $
        if (selection.isVarDisAmount()) {
            if (selection.isColValue()) {
                calculateForTotal("DiscountAmountValue", Constants.VALUE, obj, indexForTotal + 3, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("DiscountAmountVar", Constants.VARIANCE, obj, indexForTotal + 3, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("DiscountAmountPer", Constants.CHANGE, obj, indexForTotal + 3, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Discount %
        if (selection.isVarDisRate()) {
            if (selection.isColValue()) {
                calculateForTotal("DiscountSalesValue", Constants.VALUE, obj, indexForTotal + 4, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("DiscountSalesVar", Constants.VARIANCE, obj, indexForTotal + 4, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("DiscountSalesPer", Constants.CHANGE, obj, indexForTotal + 4, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //RPU
        if (selection.isVarRPU()) {
            if (selection.isColValue()) {
                calculateForTotal("RPUValue", Constants.VALUE, obj, indexForTotal + 19, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("RPUVariance", Constants.VARIANCE, obj, indexForTotal + 19, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("RPUPer", Constants.CHANGE, obj, indexForTotal + 19, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Net Sales 
        if (selection.isVarNetSales()) {
            if (selection.isColValue()) {
                calculateForTotal("NetSalesValue", Constants.VALUE, obj, indexForTotal + 12, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("NetSalesVariance", Constants.VARIANCE, obj, indexForTotal + 12, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("NetSalesPer", Constants.CHANGE, obj, indexForTotal + 12, frequencyBasedDTO, selection, RATE_PER);
            }
        }
            //COGS
        if (selection.isVarCOGC()) {
            if (selection.isColValue()) {
                calculateForTotal("COGCValue", Constants.VALUE, obj, indexForTotal+20, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("COGCVariance", Constants.VARIANCE, obj, indexForTotal+20, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("COGCPer", Constants.CHANGE, obj, indexForTotal+20, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Net Profit
        if (selection.isVarNetProfit()) {
            if (selection.isColValue()) {
                calculateForTotal("NetProfitValue", Constants.VALUE, obj, indexForTotal+21, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("NetProfitVariance", Constants.VARIANCE, obj, indexForTotal+21, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("NetProfitPer", Constants.CHANGE, obj, indexForTotal+21, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        String commonColumn=StringUtils.EMPTY;
       
        if (frequencyDivision == 4) {
            commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
        } else if (frequencyDivision == 2) {
            commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
        } else if (frequencyDivision == 1) {
            commonColumn = String.valueOf(obj[0]);
        } else if (frequencyDivision == 12) {
            String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
            commonColumn = monthName.toLowerCase() + obj[0];
        }
        Map<String, ProjectionVarianceDTO> valueMap = pivotDiscountMap.get(key);
        if (valueMap != null) {
           
            ProjectionVarianceDTO dto = valueMap.get(commonColumn);
            if(dto!=null){
            for (Object prop : dto.getProperties().keySet()) {
                String value = String.valueOf(frequencyBasedDTO.getPropertyValue(prop));
                frequencyBasedDTO.addStringProperties(prop, value);
            }
            }
        }
        }


    }

    private void calculateForTotal(String variableName, String varibaleCat, Object[] obj, int index, ProjectionVarianceDTO pvDTO, PVSelectionDTO selection, DecimalFormat format) {
        if (varibaleCat.equals(Constants.VALUE)) {
            String val = getFormattedValue(format, String.valueOf(obj[index]));
            pvDTO.addStringProperties(variableName + CURRENT + selection.getCurrentProjId(), val);
        } else {
            pvDTO.addStringProperties(variableName + CURRENT + selection.getCurrentProjId(), StringUtils.EMPTY);
        }
        for (int j = 0; j < priorList.size(); j++) {
            if (varibaleCat.equals(Constants.VALUE)) {
                String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * (30))])));
                String val = getFormattedValue(format, priorVal);
                pvDTO.addStringProperties(variableName + priorList.get(j), val);

            } else if (varibaleCat.equals(Constants.VARIANCE)) {
                String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * (30))];
                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal)));
                String val = getFormattedValue(format, variance);
                pvDTO.addStringProperties(variableName + priorList.get(j), val);

            } else {
                String priorVal = obj[index + ((j + 1) * (30))] != null ? StringUtils.EMPTY + obj[index + ((j + 1) * (0))] : ZERO;
                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                    perChange = 0.0;
                }
                String change = String.valueOf(perChange);
                String baseValu = getFormattedValue(format, change);
                pvDTO.addStringProperties(variableName + priorList.get(j), baseValu + PERCENT);

            }
        }
    }

    private void calculateAndCustomize_detail_pivot(List<Object> rawList, List<Object[]> rawList_discount) {

        for (Iterator<Object> it = rawList.listIterator(); it.hasNext();) {
            Object[] obj = (Object[])it.next();
            String key = obj[2].toString();
            key = key.substring(key.indexOf('-') + 1);
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
        if(frequencyDivision==12){
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
                calculateForTotal("ExFacVariance", Constants.VARIABLE, obj, indexForTotal, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("ExFacPer", Constants.CHANGE, obj, indexForTotal, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
       //Ex-Factory-Customer
        if (selection.isVarExFacCustomer()) {
            if (selection.isColValue()) {
                calculateForTotal("CustExFacValue", Constants.VALUE, obj, indexForTotal+25, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("CustExFacVariance", Constants.VARIANCE, obj, indexForTotal+25, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("CustExFacPer", Constants.CHANGE, obj, indexForTotal+25, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
        //Demand
        if (selection.isVarDemandSales()) {
            if (selection.isColValue()) {
                calculateForTotal("DemandSalesValue", Constants.VALUE, obj, indexForTotal + 13, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("DemandSalesVariance", Constants.VARIABLE, obj, indexForTotal + 13, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("DemandSalesPer", Constants.CHANGE, obj, indexForTotal + 13, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
        //Adjusted Demand
        if (selection.isVarAdjDemand()) {
            if (selection.isColValue()) {
                calculateForTotal("AdjDemandValue", Constants.VALUE, obj, indexForTotal + 24, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("AdjDemandVariance", Constants.VARIANCE, obj, indexForTotal + 24, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("AdjDemandPer", Constants.CHANGE, obj, indexForTotal + 24, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
        //Inventory Withdraw Sales
        if (selection.isVarInvSales()) {
            if (selection.isColValue()) {
                calculateForTotal("InvWithValue", Constants.VALUE, obj, indexForTotal + 14, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("InvWithVariance", Constants.VARIABLE, obj, indexForTotal + 14, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("InvWithPer", Constants.CHANGE, obj, indexForTotal + 14, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
            //Inventory Withdraw Details
        if (selection.isVarIwDetails()) {
            if (selection.isColValue()) {
                calculateForTotal("InvWithDetails", Constants.VALUE, obj, indexForTotal + 26, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("InvWithDetails", Constants.VARIANCE, obj, indexForTotal + 26, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("InvWithDetailsPer", Constants.CHANGE, obj, indexForTotal + 26, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
        //% of Ex-Factory
        if (selection.isVarPerExFacSales()) {
            if (selection.isColValue()) {
                calculateForTotal("PerExFacValue", Constants.VALUE, obj, indexForTotal + 5, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("PerExFacVariance", Constants.VARIABLE, obj, indexForTotal + 5, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("PerExFacPer", Constants.CHANGE, obj, indexForTotal + 5, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
            //% of Ex-Factory Cust
        if (selection.isVarPerExFacCustomer()) {
            if (selection.isColValue()) {
                calculateForTotal("PerCustExFacValue", Constants.VALUE, obj, indexForTotal + 28, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("PerCustExFacVariance", Constants.VARIANCE, obj, indexForTotal + 28, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("PerCustExFacPer", Constants.CHANGE, obj, indexForTotal + 28, frequencyBasedDTO, selection, RATE_PER);
            }
        }
         
        //% of Demand
        if (selection.isVarPerDemandSales()) {
            if (selection.isColValue()) {
                calculateForTotal("PerDemandSalesValue", Constants.VALUE, obj, indexForTotal + 15, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("PerDemandSalesVariance", Constants.VARIABLE, obj, indexForTotal + 15, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("PerDemandSalesPer", Constants.CHANGE, obj, indexForTotal + 15, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
          //% of Adjusted Demand
        if (selection.isVarPerAdjDemand()) {
            if (selection.isColValue()) {
                calculateForTotal("PerAdjDemandValue", Constants.VALUE, obj, indexForTotal + 27, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("PerAdjDemandSalesVariance", Constants.VARIANCE, obj, indexForTotal + 27, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("PerAdjDemandSalesPer", Constants.CHANGE, obj, indexForTotal + 27, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
        //% of inventory Withdraw Sales
        if (selection.isVarPerInvSales()) {
            if (selection.isColValue()) {
                calculateForTotal("PerInvWithValue", Constants.VALUE, obj, indexForTotal + 16, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("PerInvWithVariance", Constants.VARIABLE, obj, indexForTotal + 16, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("PerInvWithPer", Constants.CHANGE, obj, indexForTotal + 16, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
         //% of inventory Withdraw details
        if (selection.isVarPerIwDetails()) {
            if (selection.isColValue()) {
                calculateForTotal("PerInvWithValue", Constants.VALUE, obj, indexForTotal + 29, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("PerInvWithVariance", Constants.VARIABLE, obj, indexForTotal + 29, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("PerInvWithPer", Constants.CHANGE, obj, indexForTotal + 29, frequencyBasedDTO, selection, RATE_PER);
            }
        }
       
        //Contract Sales @ WAC
        if (selection.isVarContractsales()) {
            if (selection.isColValue()) {
                calculateForTotal("ContractSalesWACValue", Constants.VALUE, obj, indexForTotal + 1, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("ContractSalesWACVar", Constants.VARIABLE, obj, indexForTotal + 1, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("ContractSalesWACVarPer", Constants.CHANGE, obj, indexForTotal + 1, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Contract Units
        if (selection.isVarContractUnits()) {
            if (selection.isColValue()) {
                calculateForTotal("ContractUnitsValue", Constants.VALUE, obj, indexForTotal + 2, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("ContractUnitsVar", Constants.VARIABLE, obj, indexForTotal + 2, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("ContractUnitsPer", Constants.CHANGE, obj, indexForTotal + 2, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
        //Discount $
        if (selection.isVarDisAmount()) {
            if (selection.isColValue()) {
                calculateForTotal("DiscountAmountValue", Constants.VALUE, obj, indexForTotal + 3, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("DiscountAmountVar", Constants.VARIABLE, obj, indexForTotal + 3, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("DiscountAmountPer", Constants.CHANGE, obj, indexForTotal + 3, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Discount %
        if (selection.isVarDisRate()) {
            if (selection.isColValue()) {
                calculateForTotal("DiscountSalesValue", Constants.VALUE, obj, indexForTotal + 4, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("DiscountSalesVar", Constants.VARIABLE, obj, indexForTotal + 4, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("DiscountSalesPer", Constants.CHANGE, obj, indexForTotal + 4, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //RPU
        if (selection.isVarRPU()) {
            if (selection.isColValue()) {
                calculateForTotal("RPUValue", Constants.VALUE, obj,  indexForTotal + 19, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("RPUVariance", Constants.VARIANCE, obj, indexForTotal + 19, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("RPUPer", Constants.CHANGE, obj, indexForTotal + 19, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Net Sales 
        if (selection.isVarNetSales()) {
            if (selection.isColValue()) {
                calculateForTotal("NetSalesValue", Constants.VALUE, obj,indexForTotal+12 , frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("NetSalesVariance", Constants.VARIANCE, obj, indexForTotal+12, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("NetSalesPer", Constants.CHANGE, obj, indexForTotal+12, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //COGS
        if (selection.isVarCOGC()) {
            if (selection.isColValue()) {
                calculateForTotal("COGCValue", Constants.VALUE, obj, indexForTotal+20, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("COGCVariance", Constants.VARIANCE, obj, indexForTotal+20, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("COGCPer", Constants.CHANGE, obj, indexForTotal+20, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Net Profit
        if (selection.isVarNetProfit()) {
            if (selection.isColValue()) {
                calculateForTotal("NetProfitValue", Constants.VALUE, obj, indexForTotal+21, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("NetProfitVariance", Constants.VARIANCE, obj, indexForTotal+21, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("NetProfitPer", Constants.CHANGE, obj, indexForTotal+21, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
            String commonColumn = StringUtils.EMPTY;
            if (frequencyDivision == 4) {
                commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
            } else if (frequencyDivision == 2) {
                commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
            } else if (frequencyDivision == 1) {
                commonColumn = String.valueOf(obj[0]);
            } else if (frequencyDivision == 12) {
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

    public void getCustomisedProjectionResultsTotalDiscount(List<Object> dataList, PVSelectionDTO projSelDTO, int index, boolean isPer, String parentGroup, String indicator) {
        LOGGER.info("Inside getCustomisedProjectionResultsTotalDiscount");
        String lastValue = StringUtils.EMPTY;
        List<ProjectionVarianceDTO> resultDto = new ArrayList<ProjectionVarianceDTO>();
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        pvDTO.setLevelValue(projSelDTO.getLevelValue());
        pvDTO.setLevelNo(projSelDTO.getLevelNo());
        pvDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        pvDTO.setParent(0);
        List<Integer> priorList = new ArrayList<Integer>(projSelDTO.getProjIdList());
        if (dataList != null && !dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                final Object[] obj = (Object[]) dataList.get(i);
                if (!StringUtils.EMPTY.equals(lastValue) && !"null".equals(lastValue) && obj[2] != null && !lastValue.equals(String.valueOf(obj[2]))) {
                    pvDTO.setGroup(lastValue);
                    resultDto.add(pvDTO);
                    pvDTO = new ProjectionVarianceDTO();
                }
                lastValue = String.valueOf(obj[2]);
                pvDTO.setGroup(lastValue);
                String commonColumn = StringUtils.EMPTY;
                if (frequencyDivision == 4) {
                    commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 2) {
                    commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 1) {
                    commonColumn = StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 12) {
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
                        String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * 3)])));
                        String val = getFormattedValue(AMOUNT, priorVal);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                    } else if (projSelDTO.getVarIndicator().equals(Constants.VARIANCE)) {
                        String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * 3)];
                        String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal)));
                        String val = getFormattedValue(AMOUNT, variance);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                    } else {
                        String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * 3)];
                        Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                        if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                            perChange = 0.0;
                        }
                        String change = String.valueOf(perChange);
                        String baseValu = getFormattedValue(RATE_PER, change);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                    }
                }
                if (i == dataList.size() - 1) {
                    resultDto.add(pvDTO);
                }
            }
            resultMap.put(parentGroup, resultDto);
        } else {
            for (String discountNameList : projSelDTO.getDiscountNameList()) {
                pvDTO = new ProjectionVarianceDTO();
                List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
                columnList.remove("group");
                for (String Obj : columnList) {
                    pvDTO.addStringProperties(Obj, DASH);
                }
                resultDto.add(pvDTO);
            }
        }
     }

    public ProjectionVarianceDTO getCustomizedPPA(List<Object> dataList, PVSelectionDTO projSelDTO, boolean isDetail, int index, boolean isPer, String parentGroup, String ppaGroup) {
        LOGGER.info("Inside getCustomizedPPA");
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
                    if (frequencyDivision == 4) {
                        commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                    column1 = commonColumn + CURRENT + projSelDTO.getCurrentProjId();
                    String value = StringUtils.EMPTY + obj[5];
                    if (projSelDTO.getSales().contains("SALES")) {
                        value = getFormattedValue(AMOUNT, value);
                    } else if (projSelDTO.getSales().contains("RATE")) {
                        value = getFormattedValue(RATE_PER_THREE, value);
                    }
                    pvDTO.addStringProperties(column1, isPer ? value + PERCENT : value);
                    for (int j = 0; j < priorList.size(); j++) {
                        column1 = commonColumn + priorList.get(j);
                        String pValue = StringUtils.EMPTY + obj[5 + ((j + 1) * 3)];
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
                    if (frequencyDivision == 4) {
                        commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
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
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * 21)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);
                        } else if (projSelDTO.getVarIndicator().equals(Constants.VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * 21)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);
                        } else {
                            String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * 21)];
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

        LOGGER.info("Ending getCustomizedPPA");
        return pvDTO;
    }

    public void getCustomizedReturns(List<Object> dataList, PVSelectionDTO projSelDTO, boolean isDetail, int index, boolean isPer, String parentGroup, String returnsGroup) {
        LOGGER.info("Inside getCustomizedReturns");
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
                    if (frequencyDivision == 4) {
                        commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                    column1 = commonColumn + CURRENT + projSelDTO.getCurrentProjId();
                    String value = StringUtils.EMPTY + obj[4];
                    if (projSelDTO.getSales().contains("SALES")) {
                        value = getFormattedValue(AMOUNT, value);
                    } else if (projSelDTO.getSales().contains("RATE")) {
                        value = getFormattedValue(RATE, value);
                    }
                    pvDTO.addStringProperties(column1, isPer ? value + PERCENT : value);
                    for (int j = 0; j < priorList.size(); j++) {
                        column1 = commonColumn + priorList.get(j);
                        String pValue = StringUtils.EMPTY + obj[4 + ((j + 1) * 3)];
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
                    if (frequencyDivision == 4) {
                        commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
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
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * 21)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);
                        } else if (projSelDTO.getVarIndicator().equals(Constants.VARIANCE)) {
                            String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * 21)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);
                        } else {
                            String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * 21)];
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
        LOGGER.info("Ending getCustomizedReturns");
        List list = resultMap.get(parentGroup);
        if (list != null) {
            list.add(pvDTO);
        } else {
            List<ProjectionVarianceDTO> returnsDTO = new ArrayList<ProjectionVarianceDTO>();
            returnsDTO.add(pvDTO);
            resultMap.put(parentGroup, returnsDTO);
        }
        //return pvDTO;
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

        discountDollarValuelist = getCustomisedDiscount(pivotDiscountList, selection, 3, 3, Boolean.FALSE, Constants.VALUE);
        discountDollarVariancelist = getCustomisedDiscount(pivotDiscountList, selection, 3, 3, Boolean.FALSE, VARIANCE.getConstant());
        discountDollarPercentlist = getCustomisedDiscount(pivotDiscountList, selection, 3, 3, Boolean.FALSE, PERCENT);

        discountperValuelist = getCustomisedDiscount(pivotDiscountList, selection, 4, 3, Boolean.TRUE, Constants.VALUE);
        discountperVariancelist = getCustomisedDiscount(pivotDiscountList, selection, 4, 3, Boolean.TRUE, VARIANCE.getConstant());
        discountperPercentlist = getCustomisedDiscount(pivotDiscountList, selection, 4, 3, Boolean.TRUE, PERCENT);

        rpuValueList = getCustomisedDiscount(pivotDiscountList, selection, 5, 3, Boolean.FALSE, Constants.VALUE);
        rpuVarianceList = getCustomisedDiscount(pivotDiscountList, selection, 5, 3, Boolean.FALSE, VARIANCE.getConstant());
        rpuPercentList = getCustomisedDiscount(pivotDiscountList, selection, 5, 3, Boolean.FALSE, PERCENT);

        discountMap.put("discountDollar", discountDollarValuelist);
        discountMap.put("discountDollarVariance", discountDollarVariancelist);
        discountMap.put("discountDollarPercent", discountDollarPercentlist);

        discountMap.put("discountPervalue", discountperValuelist);
        discountMap.put("discountPervariance", discountperVariancelist);
        discountMap.put("discountPerPercent", discountperPercentlist);

        discountMap.put("rpuValue", rpuValueList);
        discountMap.put("rpuVariance", rpuVarianceList);
        discountMap.put("rpuPercent", rpuPercentList);
       
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

    private Map<String, String> getGroup_customView(int customMasterSid) {
        Map<String, String> map = new HashMap();
        String query = SQlUtil.getQuery("FIND_CUSTOM_RELATIOSHIP_BUILDER");
        query = query.replace("[$CUSTOM_VIEW_MASTER_SID]", StringUtils.EMPTY + customMasterSid);
        LOGGER.info("Query :" + query);
        List<Object[]> rawList = HelperTableLocalServiceUtil.executeSelectQuery(query);
        if (!rawList.isEmpty()) {
            for (Iterator<Object[]> it = rawList.iterator(); it.hasNext();) {
                Object[] obj = it.next();
                map.put(obj[1] == null ? StringUtils.EMPTY : obj[1].toString(), obj[0] == null ? StringUtils.EMPTY : obj[0].toString());
            }
        }
        return map;
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
                exFacValue = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), gtsList, gtsList, 2, 30, 2, 30, pvsdto, AMOUNT);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                exFacVar = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), gtsList, gtsList, 2, 30, 2, 30, pvsdto, AMOUNT);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                exFacPer = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), gtsList, gtsList, 2, 30, 2, 30, pvsdto, RATE);
            }

            // Customer ExFac Sales for detail start 
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                custExFacValue = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), gtsList, gtsList, 27, 30, 27, 30, pvsdto, AMOUNT);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                custExFacVar = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), gtsList, gtsList, 27, 30, 27, 30, pvsdto, AMOUNT);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                custExFacPer = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), gtsList, gtsList, 27, 30, 27, 30, pvsdto, RATE);
            }

            //Demand - start
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                demandValue = getCommonCustomizedDTO(Constants.PVVariables.DEMAND.toString(), gtsList, gtsList, 15, 30, 15, 30, pvsdto, AMOUNT);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                demandVar = getCommonCustomizedDTO(Constants.PVVariables.DEMAND.toString(), gtsList, gtsList, 15, 30, 15, 30, pvsdto, AMOUNT);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                demandPer = getCommonCustomizedDTO(Constants.PVVariables.DEMAND.toString(), gtsList, gtsList, 15, 30, 15, 30, pvsdto, RATE);
            }

            // Adjusted Demand  start 
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                adjDemandValue = getCommonCustomizedDTO(Constants.PVVariables.ADJUSTED_DEMAND.toString(), gtsList, gtsList, 26, 30, 26, 30, pvsdto, AMOUNT);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                adjDemandVar = getCommonCustomizedDTO(Constants.PVVariables.ADJUSTED_DEMAND.toString(), gtsList, gtsList, 26, 30, 26, 30, pvsdto, AMOUNT);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                adjDemandPer = getCommonCustomizedDTO(Constants.PVVariables.ADJUSTED_DEMAND.toString(), gtsList, gtsList, 26, 30, 26, 30, pvsdto, RATE);
            }
  
            //Inv with drawal sale for detail - start
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                invWithValue = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_SUMMARY.toString(), gtsList, gtsList, 16, 30, 16, 30, pvsdto, AMOUNT);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                invWithVar = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_SUMMARY.toString(), gtsList, gtsList, 16, 30, 16, 30, pvsdto, AMOUNT);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                invWithPer = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_SUMMARY.toString(), gtsList, gtsList, 16, 30, 16, 30, pvsdto, RATE);
            }

            // Inventary withdrawal Details for detail start 
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                iwDetialsValue = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_DETAILS.toString(), gtsList, gtsList, 28, 30, 28, 30, pvsdto, AMOUNT);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                iwDetialsVar = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_DETAILS.toString(), gtsList, gtsList, 28, 30, 28, 30, pvsdto, AMOUNT);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                iwDetialsPer = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_DETAILS.toString(), gtsList, gtsList, 28, 30, 28, 30, pvsdto, RATE);
            }

            //Sales for POB
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                salesValue = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), gtsList, dataList, 3, 30, 3, 30, pvsdto, AMOUNT);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                salesVar = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), gtsList, dataList, 3, 30, 3, 30, pvsdto, AMOUNT);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                salesPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), gtsList, dataList, 3, 30, 3, 30, pvsdto, RATE);
            }
        }

        // ExFac Sales 
        if (pvsdto.isVarExFacSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), gtsList, dataList, 2, 30, 2, 30, pvsdto, AMOUNT);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(exFacValue);
                }
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), gtsList, dataList, 2, 30, 2, 30, pvsdto, AMOUNT);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(exFacVar);
                }
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), gtsList, dataList, 2, 30, 2, 30, pvsdto, RATE);
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
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), gtsList, dataList, 27, 30, 27, 30, pvsdto, AMOUNT);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(custExFacValue);
                }
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), gtsList, dataList, 27, 30, 27, 30, pvsdto, AMOUNT);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(custExFacVar);
                }
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), gtsList, dataList, 27, 30, 27, 30, pvsdto, RATE);
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
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.DEMAND.toString(), gtsList, dataList, 15, 30, 15, 30, pvsdto, AMOUNT);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(demandValue);
                }
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.DEMAND.toString(), gtsList, dataList, 15, 30, 15, 30, pvsdto, AMOUNT);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(demandVar);
                }
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.DEMAND.toString(), gtsList, dataList, 15, 30, 15, 30, pvsdto, RATE);
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
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.ADJUSTED_DEMAND.toString(), gtsList, dataList, 26, 30, 26, 30, pvsdto, AMOUNT);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(adjDemandValue);
                }
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.ADJUSTED_DEMAND.toString(), gtsList, dataList, 26, 30, 26, 30, pvsdto, AMOUNT);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(adjDemandVar);
                }
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.ADJUSTED_DEMAND.toString(), gtsList, dataList, 26, 30, 26, 30, pvsdto, RATE);
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
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_SUMMARY.toString(), gtsList, dataList, 16, 30, 16, 30, pvsdto, AMOUNT);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(invWithValue);
                }
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_SUMMARY.toString(), gtsList, dataList, 16, 30, 16, 30, pvsdto, AMOUNT);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(invWithVar);
                }
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_SUMMARY.toString(), gtsList, dataList, 16, 30, 16, 30, pvsdto, RATE);
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
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_DETAILS.toString(), gtsList, dataList, 28, 30, 28, 30, pvsdto, AMOUNT);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(iwDetialsValue);
                }
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_DETAILS.toString(), gtsList, dataList, 28, 30, 28, 30, pvsdto, AMOUNT);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(iwDetialsVar);
                }
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_DETAILS.toString(), gtsList, dataList, 28, 30, 28, 30, pvsdto, RATE);
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
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), gtsList, dataList, 7, 30, 7, 30, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), gtsList, dataList, 7, 30, 7, 30, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), gtsList, dataList, 7, 30, 7, 30, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
        }
        // % Of Ex factory customer
        if (pvsdto.isVarPerExFacCustomer()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), gtsList, dataList, 30, 30, 30, 30, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), gtsList, dataList, 30, 30, 30, 30, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), gtsList, dataList, 30, 30, 30, 30, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
        }
        // % Of Demand Sales
        if (pvsdto.isVarPerDemandSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_DEMAND.toString(), gtsList, dataList, 17, 30, 17, 30, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_DEMAND.toString(), gtsList, dataList, 17, 30, 17, 30, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_DEMAND.toString(), gtsList, dataList, 17, 30, 17, 30, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
        }
        // % Of Adjusted Demand 
        if (pvsdto.isVarPerAdjDemand()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), gtsList, dataList, 29, 30, 29, 30, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), gtsList, dataList, 29, 30, 29, 30, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), gtsList, dataList, 29, 30, 29, 30, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
        }
        // % Of Inv Summary
        if (pvsdto.isVarPerInvSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), gtsList, dataList, 18, 30, 18, 30, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), gtsList, dataList, 18, 30, 18, 30, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), gtsList, dataList, 18, 30, 18, 30, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
        }
        // % Of Inventry withdrawal Detials 
        if (pvsdto.isVarPerIwDetails()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), gtsList, dataList, 31, 30, 31, 30, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), gtsList, dataList, 31, 30, 31, 30, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), gtsList, dataList, 31, 30, 31, 30, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
        }
        //Contract Sales
        if (pvsdto.isVarContractsales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                if (!isDetail) {
                    ProjectionVarianceDTO sales = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), gtsList, dataList, 3, 30, 3, 30, pvsdto, AMOUNT);
                    projectionVarianceDTO.add(sales);
                } else {
                    projectionVarianceDTO.add(salesValue);
                }
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO sales = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), gtsList, dataList, 3, 30, 3, 30, pvsdto, AMOUNT);
                    projectionVarianceDTO.add(sales);
                } else {
                    projectionVarianceDTO.add(salesVar);
                }
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                if (!isDetail) {
                    ProjectionVarianceDTO sales = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), gtsList, dataList, 3, 30, 3, 30, pvsdto, RATE);
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
                ProjectionVarianceDTO units = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(), gtsList, dataList, 4, 30, 4, 30, pvsdto, AMOUNT);
                projectionVarianceDTO.add(units);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO units = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(), gtsList, dataList, 4, 30, 4, 30, pvsdto, AMOUNT);
                projectionVarianceDTO.add(units);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO units = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(), gtsList, dataList, 4, 30, 4, 30, pvsdto, RATE);
                projectionVarianceDTO.add(units);
            }
        }
        //Discount $ 
        if (pvsdto.isVarDisAmount()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO discountDol = getCommonCustomizedDTO(Constants.PVVariables.VAR_DIS_AMOUNT.toString(), gtsList, dataList, 5, 30, 5, 30, pvsdto, AMOUNT);
                projectionVarianceDTO.add(discountDol);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO discountDol = getCommonCustomizedDTO(Constants.PVVariables.VAR_DIS_AMOUNT.toString(), gtsList, dataList, 5, 30, 5, 30, pvsdto, AMOUNT);
                projectionVarianceDTO.add(discountDol);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO discountDol = getCommonCustomizedDTO(Constants.PVVariables.VAR_DIS_AMOUNT.toString(), gtsList, dataList, 5, 30, 5, 30, pvsdto, RATE);
                projectionVarianceDTO.add(discountDol);
            }
        }
        //Discount % 
        if (pvsdto.isVarDisRate()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO discountPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_DIS_RATE.toString(), gtsList, dataList, 6, 30, 6, 30, pvsdto, AMOUNT);
                projectionVarianceDTO.add(discountPer);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO discountPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_DIS_RATE.toString(), gtsList, dataList, 6, 30, 6, 30, pvsdto, AMOUNT);
                projectionVarianceDTO.add(discountPer);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO discountPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_DIS_RATE.toString(), gtsList, dataList, 6, 30, 6, 30, pvsdto, RATE);
                projectionVarianceDTO.add(discountPer);
            }
        }
        // RPU
        if (pvsdto.isVarRPU()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO rpu = getCommonCustomizedDTO(Constants.PVVariables.VAR_RPU.toString(), gtsList, dataList, 19, 30, 19, 30, pvsdto, AMOUNT);
                projectionVarianceDTO.add(rpu);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO netSalesVar = getCommonCustomizedDTO(Constants.PVVariables.VAR_RPU.toString(), gtsList, dataList, 19, 30, 19, 30, pvsdto, AMOUNT);
                projectionVarianceDTO.add(netSalesVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO netSalesPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_RPU.toString(), gtsList, dataList, 19, 30, 19, 30, pvsdto, RATE);
                projectionVarianceDTO.add(netSalesPer);
            }
        }
        //NetSales 
        if (pvsdto.isVarNetSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO netSalesValue = getCommonCustomizedDTO(Constants.PVVariables.VAR_NETSALES.toString(), gtsList, dataList, 14, 30, 14, 30, pvsdto, AMOUNT);
                projectionVarianceDTO.add(netSalesValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO netSalesVar = getCommonCustomizedDTO(Constants.PVVariables.VAR_NETSALES.toString(), gtsList, dataList, 14, 30, 14, 30, pvsdto, AMOUNT);
                projectionVarianceDTO.add(netSalesVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO netSalesPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_NETSALES.toString(), gtsList, dataList, 14, 30, 14, 30, pvsdto, RATE);
                projectionVarianceDTO.add(netSalesPer);
            }
        }
        //COGS
        if (pvsdto.isVarCOGC()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO netSalesValue = getCommonCustomizedDTO(Constants.PVVariables.VAR_COGS.toString(), gtsList, dataList, 22, 30, 22, 30, pvsdto, AMOUNT);
                projectionVarianceDTO.add(netSalesValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO netSalesVar = getCommonCustomizedDTO(Constants.PVVariables.VAR_COGS.toString(), gtsList, dataList, 22, 30, 22, 30, pvsdto, AMOUNT);
                projectionVarianceDTO.add(netSalesVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO netSalesPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_COGS.toString(), gtsList, dataList, 22, 30, 22, 30, pvsdto, RATE);
                projectionVarianceDTO.add(netSalesPer);
            }
        }
        //Net Profit
        if (pvsdto.isVarNetProfit()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO netSalesValue = getCommonCustomizedDTO(Constants.PVVariables.VAR_NET_PROFITE.toString(), gtsList, dataList, 23, 30, 23, 30, pvsdto, AMOUNT);
                projectionVarianceDTO.add(netSalesValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO netSalesVar = getCommonCustomizedDTO(Constants.PVVariables.VAR_NET_PROFITE.toString(), gtsList, dataList, 23, 30, 23, 30, pvsdto, AMOUNT);
                projectionVarianceDTO.add(netSalesVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO netSalesPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_NET_PROFITE.toString(), gtsList, dataList, 23, 30, 23, 30, pvsdto, RATE);
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
      //  LOGGER.info("Inside getExFactorySales");
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
                    if (frequencyDivision == 4) {
                        commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
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
                    if (frequencyDivision == 4) {
                        commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
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
        dto.setLevelNo((parentDto.getLevelNo()));
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
        LOGGER.info("Inside getCustomisedProjectionResultsTotalDiscount");
       
        String lastValue = StringUtils.EMPTY;
        List<ProjectionVarianceDTO> resultDto = new ArrayList<>();
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        pvDTO.setLevelValue(projSelDTO.getLevelValue());
        pvDTO.setLevelNo(projSelDTO.getLevelNo());
        pvDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        pvDTO.setParent(0);
        List<Integer> priorList = new ArrayList<>(projSelDTO.getProjIdList());
        if (dataList != null && !dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                final Object[] obj = (Object[]) dataList.get(i);
                if (!StringUtils.EMPTY.equals(lastValue) && !"null".equals(lastValue) && obj[2] != null && !lastValue.equals(String.valueOf(obj[2]))) {
                    pvDTO.setGroup(lastValue);
                    resultDto.add(pvDTO);
                    pvDTO = new ProjectionVarianceDTO();
                }
                lastValue = String.valueOf(obj[2]);
                pvDTO.setGroup(lastValue);
                String commonColumn = StringUtils.EMPTY;
                if (frequencyDivision == 4) {
                    commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 2) {
                    commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 1) {
                    commonColumn = StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 12) {
                    String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                    commonColumn = monthName.toLowerCase() + obj[0];
                }
                if (indicator.equals(Constants.VALUE)) {
                    if(obj[index]==null){
                        pvDTO.addStringProperties(commonColumn + CURRENT + projSelDTO.getCurrentProjId(), StringUtils.EMPTY);
                    }else{
                    String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));
                    String baseValue = getFormattedValue(isPer ? RATE : AMOUNT, value);
                    pvDTO.addStringProperties(commonColumn + CURRENT + projSelDTO.getCurrentProjId(), isPer ? baseValue + PERCENT : baseValue);
                    }
                }
                else{
                    pvDTO.addStringProperties(commonColumn + CURRENT + projSelDTO.getCurrentProjId(), isPer ? ZERO + PERCENT : ZERO); 
                }
                for (int j = 0; j < priorList.size(); j++) {
                    if (indicator.equals(Constants.VALUE)) {
                     if(obj[index + ((j + 1) * priorIndex)]==null){
                       pvDTO.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);  
                     }else{
                        String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * priorIndex)])));
                        String val = getFormattedValue(isPer ? RATE : AMOUNT, priorVal);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                     }
                    } else if (indicator.equals(VARIANCE.getConstant())) {
                        if(obj[index + ((j + 1) * priorIndex)]==null && obj[index]==null){
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                        }else{
                        String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * priorIndex)];
                        String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal)));
                        String val = getFormattedValue(isPer ? RATE : AMOUNT, variance);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                        }
                    } else {
                       if(obj[index + ((j + 1) * priorIndex)]==null && obj[index]==null){
                           pvDTO.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                       }else{
                        String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * priorIndex)];
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
                if (i == dataList.size() - 1) {
                    resultDto.add(pvDTO);
                }
            }
        }
        LOGGER.info("Ending getCustomisedProjectionResultsTotalDiscount with list size  = = >" + resultDto.size());
        return resultDto;
    }
     
     
    
    public List<ProjectionVarianceDTO> getCustPeriodVariance_Details(List<ProjectionVarianceDTO> pvList, final PVSelectionDTO pvsdto, final ProjectionVarianceDTO parentDto,final Object[] obj) {
       
        List<ProjectionVarianceDTO> projectionVarianceDTO = new ArrayList<ProjectionVarianceDTO>();
        ProjectionVarianceDTO exFacValue1 = new ProjectionVarianceDTO();
        ProjectionVarianceDTO exFacVariance = new ProjectionVarianceDTO();
        ProjectionVarianceDTO exFacPercent = new ProjectionVarianceDTO();
        
        ProjectionVarianceDTO dto = new ProjectionVarianceDTO();
                //Group Column projSelDTO
                String groupName;
                if (isCustomView) {
                    groupName = customView_relationship_hierarchy.get(obj[2] == null ? StringUtils.EMPTY : obj[2].toString());
                    groupName = groupName == null ? StringUtils.EMPTY : groupName;
                } else {
                    groupName = selection.getSessionDTO().getLevelValueDiscription(obj[2].toString(), selection.getHierarchyIndicator());
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
        
//
        ProjectionVarianceDTO custExFacValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO custExFacVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO custExFacPer = new ProjectionVarianceDTO();
//
        ProjectionVarianceDTO adjDemandValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO adjDemandVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO adjDemandPer = new ProjectionVarianceDTO();
//
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
        
           //Ex factory product
           if (pvsdto.isVarExFacSales()) {
                if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                exFacValue1 = getCommonCustomizedDTO_Details(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), obj, obj, 4, 33, 4, 30, pvsdto, AMOUNT,exFacValue1,true,false);
                pvList.add(exFacValue1);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                exFacVariance = getCommonCustomizedDTO_Details(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), obj, obj, 4, 33, 4, 30, pvsdto, AMOUNT,exFacVariance,true,false);
                pvList.add(exFacVariance);
                
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                exFacPercent = getCommonCustomizedDTO_Details(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), obj, obj, 4, 33, 4, 30, pvsdto, RATE,exFacPercent,true,false);
                 pvList.add(exFacPercent);
               
            }
           }
//            
//            // Customer ExFac Sales for detail start 
             if (pvsdto.isVarExFacCustomer()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                custExFacValue = getCommonCustomizedDTO_Details(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), obj, obj, 29, 33, 29, 30, pvsdto, AMOUNT,custExFacValue,true,false);
                pvList.add(custExFacValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                custExFacVar = getCommonCustomizedDTO_Details(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), obj, obj, 29, 33, 29, 30, pvsdto, AMOUNT,custExFacVar,true,false);
              pvList.add(custExFacVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                custExFacPer = getCommonCustomizedDTO_Details(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), obj, obj, 29, 33, 29, 30, pvsdto, RATE,custExFacPer,true,false);
                pvList.add(custExFacPer);
            }
             }
//            //Demand - start
             if (pvsdto.isVarDemandSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                demandValue = getCommonCustomizedDTO_Details(Constants.PVVariables.DEMAND.toString(), obj, obj, 17, 33, 17, 30, pvsdto, AMOUNT,demandValue,true,false);
               pvList.add(demandValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                demandVar = getCommonCustomizedDTO_Details(Constants.PVVariables.DEMAND.toString(), obj, obj, 17, 33, 17, 30, pvsdto, AMOUNT,demandVar,true,false);
                pvList.add(demandVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                demandPer = getCommonCustomizedDTO_Details(Constants.PVVariables.DEMAND.toString(), obj, obj, 17, 33, 17, 30, pvsdto, RATE,demandPer,true,false);
                pvList.add(demandPer);
            }
             }

            // Adjusted Demand  start 
           if (pvsdto.isVarAdjDemand()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                adjDemandValue = getCommonCustomizedDTO_Details(Constants.PVVariables.ADJUSTED_DEMAND.toString(), obj, obj, 28, 33, 28, 30, pvsdto, AMOUNT, adjDemandValue, true,false);
                pvList.add(adjDemandValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                adjDemandVar = getCommonCustomizedDTO_Details(Constants.PVVariables.ADJUSTED_DEMAND.toString(), obj, obj, 28, 33, 28, 30, pvsdto, AMOUNT, adjDemandVar, true,false);
                pvList.add(adjDemandVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                adjDemandPer = getCommonCustomizedDTO_Details(Constants.PVVariables.ADJUSTED_DEMAND.toString(), obj, obj, 28, 33, 28, 30, pvsdto, RATE, adjDemandPer, true,false);
                pvList.add(adjDemandPer);
            }
        }
//
//            //Inv with drawal Summary
             if (pvsdto.isVarInvSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                invWithSummaryValue = getCommonCustomizedDTO_Details(Constants.PVVariables.INVENTORY_SUMMARY.toString(), obj, obj, 18, 33, 18, 30, pvsdto, AMOUNT,invWithSummaryValue,true,false);
                pvList.add(invWithSummaryValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                invWithSummaryVar = getCommonCustomizedDTO_Details(Constants.PVVariables.INVENTORY_SUMMARY.toString(), obj, obj, 18, 33, 18, 30, pvsdto, AMOUNT,invWithSummaryVar,true,false);
               pvList.add(invWithSummaryVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                invWithSummaryPer = getCommonCustomizedDTO_Details(Constants.PVVariables.INVENTORY_SUMMARY.toString(), obj, obj, 18, 33, 18, 30, pvsdto, RATE,invWithSummaryPer,true,false);
                 pvList.add(invWithSummaryPer);
            }
             }

            // Inventary withdrawal Details for detail start 
              if (pvsdto.isVarIwDetails()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                iwDetialsValue = getCommonCustomizedDTO_Details(Constants.PVVariables.INVENTORY_DETAILS.toString(), obj, obj, 30, 30, 30, 30, pvsdto, AMOUNT,iwDetialsValue,true,false);
                 pvList.add(iwDetialsValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                iwDetialsVar = getCommonCustomizedDTO_Details(Constants.PVVariables.INVENTORY_DETAILS.toString(), obj, obj, 30, 30, 30, 30, pvsdto, AMOUNT,iwDetialsVar,true,false);
                 pvList.add(iwDetialsVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                iwDetialsPer = getCommonCustomizedDTO_Details(Constants.PVVariables.INVENTORY_DETAILS.toString(), obj, obj, 30, 30, 30, 30, pvsdto, RATE,iwDetialsPer,true,false);
                pvList.add(iwDetialsPer);
            }
              }
//
//       //% Of Ex Factory Product
        if (pvsdto.isVarPerExFacSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                perExFacProductValue = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), obj, obj, 9, 33, 9, 30, pvsdto, RATE,perExFacProductValue,true,true);
                pvList.add(perExFacProductValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                perExFacProductVariance = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), obj, obj, 9, 33, 9, 30, pvsdto, RATE,perExFacProductVariance,true,true);
                 pvList.add(perExFacProductVariance);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                perExFacProductPer = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), obj, obj, 9, 33, 9, 30, pvsdto, RATE,perExFacProductPer,true,true);
                pvList.add(perExFacProductPer);
            }
        }
//        // % Of Ex factory customer
        if (pvsdto.isVarPerExFacCustomer()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                perExFacCustValue = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), obj, obj, 33, 33, 33, 30, pvsdto, RATE,perExFacCustValue,true,true);
                pvList.add(perExFacCustValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                 perExFacCustVariance = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), obj, obj, 33, 33, 33, 30, pvsdto, RATE,perExFacCustVariance,true,true);
                pvList.add(perExFacCustVariance);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                 perExFacCustPer = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), obj, obj, 33, 33, 33, 30, pvsdto, RATE,perExFacCustPer,true,true);
                 pvList.add(perExFacCustPer);
            }
        }
//        // % Of Demand Sales
        if (pvsdto.isVarPerDemandSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                 perDemandValue = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_DEMAND.toString(), obj, obj, 19, 33, 19, 30, pvsdto, RATE,perDemandValue,true,true);
                 pvList.add(perDemandValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                 perDemandVar = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_DEMAND.toString(), obj, obj, 19, 33, 19, 30, pvsdto, RATE,perDemandVar,true,true);
                 pvList.add(perDemandVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                 perDemandPer = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_DEMAND.toString(), obj, obj, 19, 33, 19, 30, pvsdto, RATE,perDemandPer,true,true);
                 pvList.add(perDemandPer);
            }
        }
        // % Of Adjusted Demand 
        if (pvsdto.isVarPerAdjDemand()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                 perAdjDemandValue = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), obj, obj, 31, 33, 31, 30, pvsdto, RATE,perAdjDemandValue,true,true);
                pvList.add(perAdjDemandValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                 perAdjDemandVar = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), obj, obj, 31, 33, 31, 30, pvsdto, RATE,perAdjDemandVar,true,true);
                pvList.add(perAdjDemandVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                 perAdjDemandPer= getCommonCustomizedDTO_Details(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), obj, obj, 31, 33, 31, 30, pvsdto, RATE,perAdjDemandPer,true,true);
                 pvList.add(perAdjDemandPer);
            }
        }
        // % Of Inv Summary
        if (pvsdto.isVarPerInvSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                 perInvWithSummaryValue = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), obj, obj, 21, 33, 21, 30, pvsdto, RATE,perInvWithSummaryValue,true,true);
                pvList.add(perInvWithSummaryValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                 perInvWithSummaryVar = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), obj, obj, 21, 33, 21, 30, pvsdto, RATE,perInvWithSummaryVar,true,true);
                pvList.add(perInvWithSummaryVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                 perInvWithSummaryPer = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), obj, obj, 21, 33, 21, 30, pvsdto, RATE,perInvWithSummaryPer,true,true);
                pvList.add(perInvWithSummaryPer);
            }
        }
        // % Of Inventry withdrawal Detials 
        if (pvsdto.isVarPerIwDetails()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                 perIwDetialsValue = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), obj, obj, 33, 33, 33, 30, pvsdto, RATE,perIwDetialsValue,true,true);
                pvList.add(perIwDetialsValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                 perIwDetialsVar = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), obj, obj, 33, 33, 33, 30, pvsdto, RATE,perIwDetialsVar,true,true);
                 pvList.add(perIwDetialsVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                 perIwDetialsPer = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), obj, obj, 33, 33, 33, 30, pvsdto, RATE,perIwDetialsPer,true,true);
                pvList.add(perIwDetialsPer);
            }
        }
        //Contract Sales
        if (pvsdto.isVarContractsales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                    contractSalesValue = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), obj, obj, 5, 33, 5, 30, pvsdto, AMOUNT,contractSalesValue,true,true);
                   pvList.add(contractSalesValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                    contractSalesVar = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), obj, obj, 5, 33, 5, 30, pvsdto, AMOUNT,contractSalesVar,true,true);
                      pvList.add(contractSalesVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                    contractSalesPer = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), obj, obj, 5, 33, 5, 30, pvsdto, RATE,contractSalesPer,true,true);
                     pvList.add(contractSalesPer);
            }
        }
        //Contract Units
        if (pvsdto.isVarContractUnits()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                contractUnitValue = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(), obj, obj, 6, 33, 6, 30, pvsdto, AMOUNT_UNITS,contractUnitValue,true,true);
              pvList.add(contractUnitValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                contractUnitVar = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(), obj, obj, 6, 33, 6, 30, pvsdto, AMOUNT_UNITS,contractUnitVar,true,true);
                pvList.add(contractUnitVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                contractUnitPer = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(), obj, obj, 6, 33, 6, 30, pvsdto, RATE,contractUnitPer,true,true);
               pvList.add(contractUnitPer);
            }
        }
        //Discount $ 
        if (pvsdto.isVarDisAmount()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                discountDollarValue = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_DIS_AMOUNT.toString(), obj, obj, 7, 33, 7, 30, pvsdto, AMOUNT,discountDollarValue,true,true);
                pvList.add(discountDollarValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                discountDollarVar = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_DIS_AMOUNT.toString(), obj, obj, 7, 33, 7, 30, pvsdto, AMOUNT,discountDollarVar,true,true);
                 pvList.add(discountDollarVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                discountDollarPer = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_DIS_AMOUNT.toString(), obj, obj, 7, 33, 7, 30, pvsdto, RATE,discountDollarPer,true,true);
                pvList.add(discountDollarPer);
            }
        }
       //Discount % 
        if (pvsdto.isVarDisRate()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                discountPerValue = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_DIS_RATE.toString(), obj, obj, 8, 33, 8, 30, pvsdto, RATE,discountPerValue,true,true);
               pvList.add(discountPerValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                discountPerVar = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_DIS_RATE.toString(), obj, obj, 8, 33, 8, 30, pvsdto, RATE,discountPerVar,true,true);
                pvList.add(discountPerVar);
                
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                discountPerPercent = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_DIS_RATE.toString(), obj, obj, 8, 33, 8, 30, pvsdto, RATE,discountPerPercent,true,true);
                pvList.add(discountPerPercent);
            }
        }
        // RPU
        if (pvsdto.isVarRPU()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                rpuValue = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_RPU.toString(), obj, obj, 23, 33, 23, 30, pvsdto, AMOUNT,rpuValue,true,true);
               pvList.add(rpuValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                rpuVar = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_RPU.toString(), obj, obj, 23, 33, 23, 30, pvsdto, AMOUNT,rpuVar,true,true);
                pvList.add(rpuVar);

            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                rpuPer = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_RPU.toString(), obj, obj, 23, 33, 23, 30, pvsdto, RATE,rpuPer,true,true);
                 pvList.add(rpuPer);
            }
        }
        //NetSales 
        if (pvsdto.isVarNetSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                netSalesValue = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_NETSALES.toString(), obj, obj, 16, 33, 16, 30, pvsdto, AMOUNT,netSalesValue,true,true);
                pvList.add(netSalesValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
               netSalesVar = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_NETSALES.toString(), obj, obj, 16, 33, 16, 30, pvsdto, AMOUNT,netSalesVar,true,true);
               pvList.add(netSalesVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
               netSalesPer = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_NETSALES.toString(), obj, obj, 16, 33, 16, 30, pvsdto, RATE,netSalesPer,true,true);
                pvList.add(netSalesPer);
            }
        }
        //COGS
        if (pvsdto.isVarCOGC()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                cogcValue = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_COGS.toString(), obj, obj, 24, 33, 24, 30, pvsdto, AMOUNT,cogcValue,true,true);
               pvList.add(cogcValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                cogcVar = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_COGS.toString(), obj, obj, 24, 33, 24, 30, pvsdto, AMOUNT,cogcVar,true,true);
               pvList.add(cogcVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                cogcPer = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_COGS.toString(), obj, obj, 24, 33, 24, 30, pvsdto, RATE,cogcPer,true,true);
                pvList.add(cogcPer);
            }
        }
        //Net Profit
        if (pvsdto.isVarNetProfit()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                netProfitValue = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_NET_PROFITE.toString(), obj, obj, 25, 33, 25, 30, pvsdto, AMOUNT,netProfitValue,true,true);
                pvList.add(netProfitValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                netProfitVar = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_NET_PROFITE.toString(), obj, obj, 25, 33, 25, 30, pvsdto, AMOUNT,netProfitVar,true,true);
                pvList.add(netProfitVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                netProfitPer = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_NET_PROFITE.toString(), obj, obj, 25, 30, 25, 30, pvsdto, RATE,netProfitPer,true,true);
                pvList.add(netProfitPer);
            }
        }
        return projectionVarianceDTO;
    } 
  
    
    public ProjectionVarianceDTO getCommonCustomizedDTO_Details(final String groupName, Object[] obj, Object[] dataObj, final int totalListPostion, final int totalPrPos, final int detailsListPos, final int detailsPrPos, PVSelectionDTO pvsdto, final DecimalFormat FORMAT,ProjectionVarianceDTO pvDTO,boolean addFlag,boolean salesInclusion) {
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
                    if (frequencyDivision == 4) {
                        commonColumn = " Q" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(Constants.VALUE)) {
                      if (obj[totalListPostion] == null) {
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), StringUtils.EMPTY);
                    } else {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[totalListPostion])));
                        String baseValue = getFormattedValue(FORMAT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(),groupName.contains("%") ? baseValue + PERCENT : baseValue);
                    }
                }
                    else{
                      //  pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), ZERO);  
                      pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(),StringUtils.EMPTY);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(Constants.VALUE)) {
                          if(obj[totalListPostion + ((j + 1) * totalPrPos)]==null){
                               pvDTO.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                          }else{
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[totalListPostion + ((j + 1) * totalPrPos)])));
                            String val = getFormattedValue(FORMAT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j),groupName.contains("%") ? val+PERCENT : val);
                          }
                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                           if(obj[totalListPostion + ((j + 1) * totalPrPos)]==null &&obj[totalListPostion]==null){
                                 pvDTO.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                           }else{
                            String priorVal = StringUtils.EMPTY + obj[totalListPostion + ((j + 1) * totalPrPos)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[totalListPostion])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(FORMAT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), groupName.contains("%") ? val+PERCENT : val);
                           }
                        } else {
                           if(obj[totalListPostion + ((j + 1) * totalPrPos)]==null && obj[totalListPostion]==null){
                                pvDTO.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                           }else{
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
                 obj=dataObj;
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                     if (pvsdto.getVarIndicator().equals(Constants.VALUE)) {
                        if(obj[detailsListPos]==null){
                             pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), StringUtils.EMPTY);
                        }else{
                         String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[detailsListPos])));
                        String baseValue = getFormattedValue(FORMAT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), groupName.contains("%") ? baseValue+PERCENT : baseValue);
                        }
                    }
                    else{
                      //  pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), ZERO);  
                       pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(),StringUtils.EMPTY);
                    }
                  for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(Constants.VALUE)) {
                          if(obj[detailsListPos + ((j + 1) * detailsPrPos)]==null){
                               pvDTO.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                          }else{
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[detailsListPos + ((j + 1) * detailsPrPos)])));
                            String val = getFormattedValue(FORMAT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), groupName.contains("%") ? val+PERCENT : val);
                          }
                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[detailsListPos + ((j + 1) * detailsPrPos)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[detailsListPos])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(FORMAT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), groupName.contains("%") ? val+PERCENT : val);
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
        return pvDTO;
    }
    
    
    public void updateCustPeriodVariance_Details(List<ProjectionVarianceDTO> pvList,final PVSelectionDTO pvsdto, final ProjectionVarianceDTO parentDto,final Object[] obj) {
       int listIndex = 1;

        //Ex fac product
         if (pvsdto.isVarExFacSales()) {
            if (selection.isColValue()) {
                selection.setVarIndicator(Constants.VALUE);
                exFacValue = pvList.get(listIndex++);
                exFacValue = getCommonCustomizedDTO_Details(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), obj, obj, 4, 33, 4, 30, pvsdto, AMOUNT,exFacValue,false,false);
            }
            if (selection.isColVariance()) {
                selection.setVarIndicator(Constants.VARIANCE);
               exFacVar = pvList.get(listIndex++);
                exFacVar = getCommonCustomizedDTO_Details(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), obj, obj, 4, 33, 4, 30, pvsdto, AMOUNT,exFacVar,false,false);
            }
            if (selection.isColPercentage()) {
                selection.setVarIndicator(Constants.CHANGE);
              exFacPer = pvList.get(listIndex++);
               exFacPer = getCommonCustomizedDTO_Details(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), obj, obj, 4, 33, 4, 30, pvsdto, AMOUNT,exFacPer,false,false);
            }
            
         }
//           
//           // Customer ExFac Sales for detail start 
            if (pvsdto.isVarExFacCustomer()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
               ProjectionVarianceDTO  custExFacValue = pvList.get(listIndex++);
                custExFacValue = getCommonCustomizedDTO_Details(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), obj, obj, 29, 33, 29, 30, pvsdto, AMOUNT,custExFacValue,false,false);
                pvList.add(custExFacValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                 ProjectionVarianceDTO  custExFacVar = pvList.get(listIndex++);
                custExFacVar = getCommonCustomizedDTO_Details(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), obj, obj, 29, 33, 29, 30, pvsdto, AMOUNT,custExFacVar,false,false);
              pvList.add(custExFacVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                  ProjectionVarianceDTO  custExFacPer = pvList.get(listIndex++);
                custExFacPer = getCommonCustomizedDTO_Details(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), obj, obj, 29, 33, 29, 30, pvsdto, RATE,custExFacPer,false,false);
                pvList.add(custExFacPer);
            }  
            }
            
            //Demand - start
            if (pvsdto.isVarDemandSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
               demandValue = pvList.get(listIndex++);
               demandValue = getCommonCustomizedDTO_Details(Constants.PVVariables.DEMAND.toString(), obj, obj, 17, 33, 17, 30, pvsdto, AMOUNT,demandValue,false,false);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                demandVar = pvList.get(listIndex++);
                demandVar = getCommonCustomizedDTO_Details(Constants.PVVariables.DEMAND.toString(), obj, obj, 17, 33, 17, 30, pvsdto, AMOUNT,demandVar,false,false);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                demandPer = pvList.get(listIndex++);
                demandPer = getCommonCustomizedDTO_Details(Constants.PVVariables.DEMAND.toString(), obj, obj, 17, 33, 17, 30, pvsdto, RATE,demandPer,false,false);
            }  
            }
            
              // Adjusted Demand  start 
              if (pvsdto.isVarAdjDemand()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                adjDamandVale = pvList.get(listIndex++);
                adjDamandVale = getCommonCustomizedDTO_Details(Constants.PVVariables.ADJUSTED_DEMAND.toString(), obj, obj, 28, 33, 28, 30, pvsdto, AMOUNT,adjDamandVale,false,false);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                adjDemandVar = pvList.get(listIndex++);
                adjDemandVar = getCommonCustomizedDTO_Details(Constants.PVVariables.ADJUSTED_DEMAND.toString(), obj, obj, 28, 33, 28, 30, pvsdto, AMOUNT,adjDemandVar,false,false);
                 
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                adjdemandPer = pvList.get(listIndex++);
                adjdemandPer = getCommonCustomizedDTO_Details(Constants.PVVariables.ADJUSTED_DEMAND.toString(), obj, obj, 28, 33, 28, 30, pvsdto, RATE,adjdemandPer,false,false);
            }
              }
            
            //Inv with drawal summary
             if (pvsdto.isVarInvSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                 invWithValue = pvList.get(listIndex++);
                invWithValue = getCommonCustomizedDTO_Details(Constants.PVVariables.INVENTORY_SUMMARY.toString(), obj, obj, 18, 33, 18, 30, pvsdto, AMOUNT,invWithValue,false,false);
               
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                 invWithVar = pvList.get(listIndex++);
                invWithVar = getCommonCustomizedDTO_Details(Constants.PVVariables.INVENTORY_SUMMARY.toString(), obj, obj, 18, 33, 18, 30, pvsdto, AMOUNT,invWithVar,false,false);
              
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                invWithPer = pvList.get(listIndex++);
                invWithPer = getCommonCustomizedDTO_Details(Constants.PVVariables.INVENTORY_SUMMARY.toString(), obj, obj, 18, 33, 18, 30, pvsdto, RATE,invWithPer,false,false);
            }
            }   
            
            // Inventary withdrawal Details for detail start 
            if (pvsdto.isVarIwDetails()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                 iwDetialsValue = pvList.get(listIndex++);
                iwDetialsValue = getCommonCustomizedDTO_Details(Constants.PVVariables.INVENTORY_DETAILS.toString(), obj, obj, 30, 30, 30, 30, pvsdto, AMOUNT,iwDetialsValue,false,false);
               
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                iwDetialsVar = pvList.get(listIndex++);
                iwDetialsVar = getCommonCustomizedDTO_Details(Constants.PVVariables.INVENTORY_DETAILS.toString(), obj, obj, 30, 30, 30, 30, pvsdto, AMOUNT,iwDetialsVar,false,false);
                
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                iwDetialsPer = pvList.get(listIndex++);
                iwDetialsPer = getCommonCustomizedDTO_Details(Constants.PVVariables.INVENTORY_DETAILS.toString(), obj, obj, 30, 30, 30, 30, pvsdto, RATE,iwDetialsPer,false,false);
            }
            } 
//            
//          //% Of Ex Factory Product
        if (pvsdto.isVarPerExFacSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                perExFacProductValue=pvList.get(listIndex++);
                perExFacProductValue = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), obj, obj, 9, 33, 9, 30, pvsdto, RATE,perExFacProductValue,false,true);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                perExFacProductVar=pvList.get(listIndex++);
                perExFacProductVar = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), obj, obj, 9, 33, 9, 30, pvsdto, RATE,perExFacProductVar,false,true);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                perExFacProductPer=pvList.get(listIndex++);
                perExFacProductPer = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), obj, obj, 9, 33, 9, 30, pvsdto, RATE,perExFacProductPer,false,true);
               
            }
        }  
//            
//            
        // % Of Ex factory customer
        if (pvsdto.isVarPerExFacCustomer()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                 perExFacCustValue=pvList.get(listIndex++);
                perExFacCustValue = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), obj, obj, 33, 33, 33, 30, pvsdto, RATE,perExFacCustValue,false,true);
               
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                  perExFacCustVariance=pvList.get(listIndex++);
                perExFacCustVariance = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), obj, obj, 33, 33, 33, 30, pvsdto, RATE,perExFacCustVariance,false,true);
               
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                  perExFacCustPer=pvList.get(listIndex++);
                perExFacCustPer = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), obj, obj, 33, 33, 33, 30, pvsdto, RATE,perExFacCustPer,false,true);
                 
            }
        }
//        // % Of Demand Sales
        if (pvsdto.isVarPerDemandSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                  perDemandValue=pvList.get(listIndex++);
                perDemandValue = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_DEMAND.toString(), obj, obj, 19, 33, 19, 30, pvsdto, RATE,perDemandValue,false,true);
                
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                  perDemandVar=pvList.get(listIndex++);
                perDemandVar = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_DEMAND.toString(), obj, obj, 19, 33, 19, 30, pvsdto, RATE,perDemandVar,false,true);
                 
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                  perDemandPer=pvList.get(listIndex++);
                perDemandPer = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_DEMAND.toString(), obj, obj, 19, 33, 19, 30, pvsdto, RATE,perDemandPer,false,true);
                 
            }
        }
        // % Of Adjusted Demand 
        if (pvsdto.isVarPerAdjDemand()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                   perAdjDemandValue=pvList.get(listIndex++);
                perAdjDemandValue = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), obj, obj, 31, 33, 31, 30, pvsdto, RATE,perAdjDemandValue,false,true);
                
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                   perAdjDemandVar=pvList.get(listIndex++);
                perAdjDemandVar = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), obj, obj, 31, 33, 31, 30, pvsdto, RATE,perAdjDemandVar,false,true);
               
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                 perAdjDemandPer=pvList.get(listIndex++);
                perAdjDemandPer= getCommonCustomizedDTO_Details(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), obj, obj, 31, 33, 31, 30, pvsdto, RATE,perAdjDemandPer,false,true);
                 
            }
        }
        // % Of Inv Summary
        if (pvsdto.isVarPerInvSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                   perInvWithSummaryValue=pvList.get(listIndex++);
                perInvWithSummaryValue = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), obj, obj, 21, 33, 21, 30, pvsdto, RATE,perInvWithSummaryValue,false,true);
              
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                  perInvWithSummaryVar=pvList.get(listIndex++);
                perInvWithSummaryVar = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), obj, obj, 21, 33, 21, 30, pvsdto, RATE,perInvWithSummaryVar,false,true);
               
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                  perInvWithSummaryPer=pvList.get(listIndex++);
                perInvWithSummaryPer = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), obj, obj, 21, 33, 21, 30, pvsdto, RATE,perInvWithSummaryPer,false,true);
                
            }
        }
        // % Of Inventry withdrawal Detials 
        if (pvsdto.isVarPerIwDetails()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                   perIwDetialsValue=pvList.get(listIndex++);
                perIwDetialsValue = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), obj, obj, 33, 33, 33, 30, pvsdto, RATE,perIwDetialsValue,false,true);
               
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                 perIwDetialsVar=pvList.get(listIndex++);
                perIwDetialsVar = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), obj, obj, 33, 33, 33, 30, pvsdto, RATE,perIwDetialsVar,false,true);
                
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                  perIwDetialsPer=pvList.get(listIndex++);
                perIwDetialsPer = getCommonCustomizedDTO_Details(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), obj, obj, 33, 33, 33, 30, pvsdto, RATE,perIwDetialsPer,false,true);
                
            }
        }
        
        
              //Contract Sales
        if (pvsdto.isVarContractsales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                   contractSalesValue=pvList.get(listIndex++);
                   contractSalesValue = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), obj, obj, 5, 33, 5, 30, pvsdto, AMOUNT,contractSalesValue,false,true);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
               contractSalesVar=pvList.get(listIndex++);
                    contractSalesVar = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), obj, obj, 5, 33, 5, 30, pvsdto, AMOUNT,contractSalesVar,false,true);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                contractSalesPer = pvList.get(listIndex++);
                contractSalesPer = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), obj, obj, 5, 33, 5, 30, pvsdto, RATE, contractSalesPer, false,true);
            }
        }
        //Contract Units
        if (pvsdto.isVarContractUnits()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                 contractUnitValue=pvList.get(listIndex++);
                contractUnitValue = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(), obj, obj, 6, 33, 6, 30, pvsdto, AMOUNT,contractUnitValue,false,true);
             
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                 contractUnitVar=pvList.get(listIndex++);
                contractUnitVar = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(), obj, obj, 6, 33, 6, 30, pvsdto, AMOUNT,contractUnitVar,false,true);
               
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                 contractUnitPer=pvList.get(listIndex++);
                contractUnitPer = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(), obj, obj, 6, 33, 6, 30, pvsdto, RATE,contractUnitPer,false,true);
              
            }
        }
        
//        //Discount $ 
        if (pvsdto.isVarDisAmount()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                discountDollarValue=pvList.get(listIndex++);
                discountDollarValue = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_DIS_AMOUNT.toString(), obj, obj, 7, 33, 7, 30, pvsdto, AMOUNT,discountDollarValue,false,true);
               
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                discountDollarVar=pvList.get(listIndex++);
                discountDollarVar = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_DIS_AMOUNT.toString(), obj, obj, 7, 33, 7, 30, pvsdto, AMOUNT,discountDollarVar,false,true);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                discountDollarPer=pvList.get(listIndex++);
                discountDollarPer = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_DIS_AMOUNT.toString(), obj, obj, 7, 33, 7, 30, pvsdto, RATE,discountDollarPer,false,true);
            }
        }
        //Discount % 
        if (pvsdto.isVarDisRate()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                discountPerValue=pvList.get(listIndex++);
                discountPerValue = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_DIS_RATE.toString(), obj, obj, 8, 33, 8, 30, pvsdto, RATE,discountPerValue,false,true);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                discountPerVar=pvList.get(listIndex++);
                discountPerVar = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_DIS_RATE.toString(), obj, obj, 8, 33, 8, 30, pvsdto, RATE,discountPerVar,false,true);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                discountPerPercent=pvList.get(listIndex++);
                discountPerPercent = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_DIS_RATE.toString(), obj, obj, 8, 33, 8, 30, pvsdto, RATE,discountPerPercent,false,true);
               
            }
        }
        
        // RPU
        if (pvsdto.isVarRPU()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                 rpuValue=pvList.get(listIndex++);
                rpuValue = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_RPU.toString(), obj, obj, 23, 33, 23, 30, pvsdto, AMOUNT,rpuValue,false,true);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                 rpuVar=pvList.get(listIndex++);
                rpuVar = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_RPU.toString(), obj, obj, 23, 33, 23, 30, pvsdto, AMOUNT,rpuVar,false,true);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                 rpuPer=pvList.get(listIndex++);
                rpuPer = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_RPU.toString(), obj, obj, 23, 33, 23, 30, pvsdto, RATE,rpuPer,false,true);
            }
        }
        //NetSales 
        if (pvsdto.isVarNetSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                 netSalesValue=pvList.get(listIndex++);
                netSalesValue = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_NETSALES.toString(), obj, obj, 16, 33, 16, 30, pvsdto, AMOUNT,netSalesValue,false,true);
                
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                 netSalesVar=pvList.get(listIndex++);
               netSalesVar = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_NETSALES.toString(), obj, obj, 16, 33, 16, 30, pvsdto, AMOUNT,netSalesVar,false,true);
               
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                 netSalesPer=pvList.get(listIndex++);
               netSalesPer = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_NETSALES.toString(), obj, obj, 16, 33, 16, 30, pvsdto, RATE,netSalesPer,false,true);
                
            }
        }
        //COGS
        if (pvsdto.isVarCOGC()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                 cogcValue=pvList.get(listIndex++);
                cogcValue = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_COGS.toString(), obj, obj, 24, 33, 24, 30, pvsdto, AMOUNT,cogcValue,false,true);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                 cogcVar=pvList.get(listIndex++);
                cogcVar = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_COGS.toString(), obj, obj, 24, 33, 24, 30, pvsdto, AMOUNT,cogcVar,false,true);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                 cogcPer=pvList.get(listIndex++);
                 cogcPer = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_COGS.toString(), obj, obj, 24, 33, 24, 30, pvsdto, RATE,cogcPer,false,true);
            }
        }
        //Net Profit
        if (pvsdto.isVarNetProfit()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                 netProfitValue=pvList.get(listIndex++);
                netProfitValue = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_NET_PROFITE.toString(), obj, obj, 25, 33, 25, 30, pvsdto, AMOUNT,netProfitValue,false,true);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                 netProfitVar=pvList.get(listIndex++);
                netProfitVar = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_NET_PROFITE.toString(), obj, obj, 25, 33, 25, 30, pvsdto, AMOUNT,netProfitVar,false,true);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                 netProfitPer=pvList.get(listIndex++);
                netProfitPer = getCommonCustomizedDTO_Details(Constants.PVVariables.VAR_NET_PROFITE.toString(), obj, obj, 25, 30, 25, 30, pvsdto, RATE,netProfitPer,false,true);
            }
        }
    }
    
       private void calculateDiscount() {
        String oldDiscount = StringUtils.EMPTY;
        String oldHierarchyNo = StringUtils.EMPTY;
        int count = procRawList_detail_discount.size();
        String commonColumn = StringUtils.EMPTY;
        String newDiscount=StringUtils.EMPTY;
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
        
        
         List<ProjectionVarianceDTO> discountDollarvalueList = new ArrayList<ProjectionVarianceDTO>();
         List<ProjectionVarianceDTO> discountDollarVarList = new ArrayList<ProjectionVarianceDTO>();
         List<ProjectionVarianceDTO> discountDollarPerList = new ArrayList<ProjectionVarianceDTO>();
         
         List<ProjectionVarianceDTO> discountPerValueList = new ArrayList<ProjectionVarianceDTO>();
         List<ProjectionVarianceDTO> discountPerVarianceList = new ArrayList<ProjectionVarianceDTO>();
         List<ProjectionVarianceDTO> discountPerPercentList = new ArrayList<ProjectionVarianceDTO>();
         
         
         List<ProjectionVarianceDTO> rpuValueList = new ArrayList<ProjectionVarianceDTO>();
         List<ProjectionVarianceDTO> rpuVarianceList = new ArrayList<ProjectionVarianceDTO>();
         List<ProjectionVarianceDTO> rpuPercentList = new ArrayList<ProjectionVarianceDTO>();
         
        
        boolean isPer=true;
        for (int i = 0; i < count; i++) {
            Object[] obj = (Object[]) procRawList_detail_discount.get(i);
          if(i==0){
              oldHierarchyNo=String.valueOf(obj[0]);
          }
            String newHierarchyNo = String.valueOf(obj[0]);
            if (oldHierarchyNo.equals(newHierarchyNo)) {
                newDiscount=String.valueOf(obj[4]);
                if (oldDiscount.equals(newDiscount)) {
                    if (frequencyDivision == 4) {
                        commonColumn = "Q" + obj[3] + StringUtils.EMPTY + obj[2];
                    } else if (frequencyDivision == 2) {
                        commonColumn = "S" + obj[3] + StringUtils.EMPTY + obj[2];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[2];
                    } else if (frequencyDivision == 12) {
                      String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[3])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[2];
                    }

                    String currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[5])));
                    String baseValue1 = getFormattedValue(AMOUNT, currentValue);
                    if (obj[5] == null) {
                        discountDollarValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), StringUtils.EMPTY);
                        discountPerValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), StringUtils.EMPTY);
                        rpuValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), StringUtils.EMPTY);
                    } else {
                        discountDollarValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue1);
                        String baseValue2 = getFormattedValue_Percent(RATE, currentValue);
                        discountPerValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue2 + PERCENT);
                        rpuValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue1);
                    }

                    discountDollarVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), ZERO);
                    discountDollarPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), ZERO);

                    discountPerVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), ZERO + PERCENT);
                    discountPerPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), ZERO + PERCENT);

                    rpuVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), ZERO);
                    rpuPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), ZERO);

                for (int j = 0; j < priorList.size(); j++) {
                        
                    if (obj[index + ((j + 1) * 3)] == null) {
                        discountDollarValue.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                    } else {
                        String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * 3)])));
                        String val = getFormattedValue(AMOUNT, priorVal);
                        discountDollarValue.addStringProperties(commonColumn + priorList.get(j), val);
                    }
                        
                    if (obj[index + 1 + ((j + 1) * 3)] == null) {
                        discountPerValue.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                    } else {
                        String priorValPer = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + 1 + ((j + 1) * 3)])));
                        String valPer = getFormattedValue(RATE, priorValPer);
                        discountPerValue.addStringProperties(commonColumn + priorList.get(j), valPer + PERCENT);
                    }
                       if (obj[index + 2 + ((j + 1) * 3)] == null) {
                        rpuValue.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                    } else {
                        String rpuValues = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + 2 + ((j + 1) * 3)])));
                        String rpuval = getFormattedValue(AMOUNT, rpuValues);
                        rpuValue.addStringProperties(commonColumn + priorList.get(j), rpuval);
                    }
                        
                        if(obj[index + ((j + 1) * 3)]==null){
                         discountDollarVariance.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);   
                        }else{
                        String priorVal1 = StringUtils.EMPTY + obj[index + ((j + 1) * 3)];
                        String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal1)));
                        String val1 = getFormattedValue( AMOUNT, variance);
                        discountDollarVariance.addStringProperties(commonColumn + priorList.get(j), val1);
                        }
                        
                        if(obj[index +1+((j + 1) * 3)]==null){
                            discountPerVariance.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                        }else{
                        String priorValvar = StringUtils.EMPTY + obj[index +1+((j + 1) * 3)];
                        String variancePer = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index+1])) - Double.valueOf(isNull(priorValvar)));
                        String valPer2 = getFormattedValue( RATE , variancePer);
                        discountPerVariance.addStringProperties(commonColumn + priorList.get(j), valPer2 + PERCENT );
                        }
                        
                       if(obj[index +2+((j + 1) * 3)]==null){
                            rpuVariance.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                       }else{
                        String rpuPer = StringUtils.EMPTY + obj[index +2+((j + 1) * 3)];
                        String rpupercentPer = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index+2])) - Double.valueOf(isNull(rpuPer)));
                        String rpuPercentage = getFormattedValue(AMOUNT, rpupercentPer);
                        rpuVariance.addStringProperties(commonColumn + priorList.get(j), rpuPercentage);
                       }
                        
                      if(obj[index +((j + 1) * 3)]==null && obj[index]==null){
                         discountDollarPercent.addStringProperties(commonColumn + priorList.get(j),StringUtils.EMPTY); 
                      }else{
                       String priorVal2 = StringUtils.EMPTY + obj[index +((j + 1) * 3)];
                        Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal2))) / Double.valueOf(isNull(priorVal2));
                        if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                            perChange = 0.0;
                        }
                        String change = String.valueOf(perChange);
                        String baseValu = getFormattedValue(RATE_PER, change);
                        discountDollarPercent.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                      }
                        
                      if(obj[index +1+((j + 1) * 3)]==null && obj[index+1]==null){
                          discountPerPercent.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                      }else{
                       String priorValRpu = StringUtils.EMPTY + obj[index +1+((j + 1) * 3)];
                        Double perChangerpu = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index+1])) - Double.valueOf(isNull(priorValRpu))) / Double.valueOf(isNull(priorValRpu));
                        if (perChangerpu.isNaN() || perChangerpu.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChangerpu))) {
                            perChangerpu = 0.0;
                        }
                        String changerpu = String.valueOf(perChangerpu);
                        String baseValurpu = getFormattedValue(RATE_PER, changerpu);
                        discountPerPercent.addStringProperties(commonColumn + priorList.get(j), baseValurpu + PERCENT);
                      }
                        
                        if(obj[index +2+((j + 1) * 3)]==null && obj[index+2]==null){
                             rpuPercent.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                        } else{
                      String priorRpuPer = StringUtils.EMPTY + obj[index +2+((j + 1) * 3)];
                        Double rpuChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index+2])) - Double.valueOf(isNull(priorRpuPer))) / Double.valueOf(isNull(priorRpuPer));
                        if (rpuChange.isNaN() || rpuChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(rpuChange))) {
                            rpuChange = 0.0;
                        }
                        String changePriorRpu = String.valueOf(rpuChange);
                        String baseValueRpu = getFormattedValue(RATE_PER, changePriorRpu);
                        rpuPercent.addStringProperties(commonColumn + priorList.get(j), baseValueRpu + PERCENT);
                        }
                }
                } else {
                    if (i == 0) {
                        discountDollarValue.setGroup(String.valueOf(obj[4]));
                        discountDollarVariance.setGroup(String.valueOf(obj[4]));
                        discountDollarPercent.setGroup(String.valueOf(obj[4]));
                        
                        discountPerValue.setGroup(String.valueOf(obj[4]));
                        discountPerVariance.setGroup(String.valueOf(obj[4]));
                        discountPerPercent.setGroup(String.valueOf(obj[4]));
                        
                        rpuValue.setGroup(String.valueOf(obj[4]));
                        rpuVariance.setGroup(String.valueOf(obj[4]));
                        rpuPercent.setGroup(String.valueOf(obj[4]));
                        
                      if (frequencyDivision == 4) {
                        commonColumn = "Q" + obj[3] + StringUtils.EMPTY + obj[2];
                    } else if (frequencyDivision == 2) {
                        commonColumn = "S" + obj[3] + StringUtils.EMPTY + obj[2];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[2];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[3])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[2];
                    }
                    
                    String currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[5])));
                    String baseValue1 = getFormattedValue(AMOUNT, currentValue);
                    if (obj[5] == null) {
                        discountDollarValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), StringUtils.EMPTY);
                        discountPerValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), StringUtils.EMPTY);
                        rpuValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), StringUtils.EMPTY);
                    } else {
                        discountDollarValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue1);
                        String baseValue2 = getFormattedValue_Percent(RATE, currentValue);
                        discountPerValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue2 + PERCENT);
                        rpuValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue1);
                    }  
                      
                      discountDollarVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), ZERO);
                    discountDollarPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), ZERO);

                    discountPerVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), ZERO + PERCENT);
                    discountPerPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), ZERO + PERCENT);

                    rpuVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), ZERO);
                    rpuPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), ZERO); 
                    
                    
                    //  String currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[5])));
//                    String baseValue1 = getFormattedValue(AMOUNT, currentValue);
//                    discountDollarValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(),  baseValue1);
//                    discountDollarVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(),  ZERO ); 
//                    discountDollarPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(),  ZERO );
//                    
//                    String baseValue2 = getFormattedValue_Percent(RATE, currentValue);
//                    discountPerValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue2 + PERCENT);
//                    discountPerVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(),  ZERO + PERCENT); 
//                    discountPerPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(),  ZERO + PERCENT);
//                    
//                    rpuValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue1);
//                    rpuVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(),  ZERO); 
//                    rpuPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(),  ZERO);
                    
                       for (int j = 0; j < priorList.size(); j++) {
//                       String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * 3)])));
//                        String val = getFormattedValue( AMOUNT, priorVal);
//                        discountDollarValue.addStringProperties(commonColumn + priorList.get(j),val);
//                        
//                        String priorValPer = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index +1+ ((j + 1) * 3)])));
//                        String valPer = getFormattedValue(RATE , priorValPer);
//                        discountPerValue.addStringProperties(commonColumn + priorList.get(j), valPer + PERCENT );
//                        
//                         String rpuValues = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index +2+ ((j + 1) * 3)])));
//                        String rpuval = getFormattedValue(AMOUNT, rpuValues);
//                        rpuValue.addStringProperties(commonColumn + priorList.get(j),  rpuval);
//                        
//                        String priorVal1 = StringUtils.EMPTY + obj[index + ((j + 1) * 3)];
//                        String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal1)));
//                        String val1 = getFormattedValue( AMOUNT, variance);
//                        discountDollarVariance.addStringProperties(commonColumn + priorList.get(j), val1);
//                        
//                        String priorValvar = StringUtils.EMPTY + obj[index +1+((j + 1) * 3)];
//                        String variancePer = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index+1])) - Double.valueOf(isNull(priorValvar)));
//                        String valPer2 = getFormattedValue(RATE, variancePer);
//                        discountPerVariance.addStringProperties(commonColumn + priorList.get(j), valPer2 + PERCENT);
//                        
//                        String rpuPer = StringUtils.EMPTY + obj[index +2+((j + 1) * 3)];
//                        String rpupercentPer = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index+2])) - Double.valueOf(isNull(rpuPer)));
//                        String rpuPercentage = getFormattedValue(AMOUNT, rpupercentPer);
//                        rpuVariance.addStringProperties(commonColumn + priorList.get(j), rpuPercentage);
//
//                        String priorVal2 = StringUtils.EMPTY + obj[index +((j + 1) * 3)];
//                        Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal2))) / Double.valueOf(isNull(priorVal2));
//                        if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
//                            perChange = 0.0;
//                        }
//                        String change = String.valueOf(perChange);
//                        String baseValu = getFormattedValue(RATE_PER, change);
//                        discountDollarPercent.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
//                        
//                       String priorValRpu = StringUtils.EMPTY + obj[index +1+((j + 1) * 3)];
//                        Double perChangerpu = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index+1])) - Double.valueOf(isNull(priorValRpu))) / Double.valueOf(isNull(priorValRpu));
//                        if (perChangerpu.isNaN() || perChangerpu.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChangerpu))) {
//                            perChangerpu = 0.0;
//                        }
//                        String changerpu = String.valueOf(perChangerpu);
//                        String baseValurpu = getFormattedValue(RATE_PER, changerpu);
//                        discountPerPercent.addStringProperties(commonColumn + priorList.get(j), baseValurpu + PERCENT);
//                        
//                         String priorRpuPer = StringUtils.EMPTY + obj[index +2+((j + 1) * 3)];
//                        Double rpuChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index+2])) - Double.valueOf(isNull(priorRpuPer))) / Double.valueOf(isNull(priorRpuPer));
//                        if (rpuChange.isNaN() || rpuChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(rpuChange))) {
//                            rpuChange = 0.0;
//                        }
//                        String changePriorRpu = String.valueOf(rpuChange);
//                        String baseValueRpu = getFormattedValue(RATE_PER, changePriorRpu);
//                        rpuPercent.addStringProperties(commonColumn + priorList.get(j), baseValueRpu + PERCENT);

                     if (obj[index + ((j + 1) * 3)] == null) {
                        discountDollarValue.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                    } else {
                        String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * 3)])));
                        String val = getFormattedValue(AMOUNT, priorVal);
                        discountDollarValue.addStringProperties(commonColumn + priorList.get(j), val);
                    }
                        
                    if (obj[index + 1 + ((j + 1) * 3)] == null) {
                        discountPerValue.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                    } else {
                        String priorValPer = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + 1 + ((j + 1) * 3)])));
                        String valPer = getFormattedValue(RATE, priorValPer);
                        discountPerValue.addStringProperties(commonColumn + priorList.get(j), valPer + PERCENT);
                    }
                       if (obj[index + 2 + ((j + 1) * 3)] == null) {
                        rpuValue.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                    } else {
                        String rpuValues = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + 2 + ((j + 1) * 3)])));
                        String rpuval = getFormattedValue(AMOUNT, rpuValues);
                        rpuValue.addStringProperties(commonColumn + priorList.get(j), rpuval);
                    }
                        
                        if(obj[index + ((j + 1) * 3)]==null){
                         discountDollarVariance.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);   
                        }else{
                        String priorVal1 = StringUtils.EMPTY + obj[index + ((j + 1) * 3)];
                        String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal1)));
                        String val1 = getFormattedValue( AMOUNT, variance);
                        discountDollarVariance.addStringProperties(commonColumn + priorList.get(j), val1);
                        }
                        
                        if(obj[index +1+((j + 1) * 3)]==null){
                            discountPerVariance.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                        }else{
                        String priorValvar = StringUtils.EMPTY + obj[index +1+((j + 1) * 3)];
                        String variancePer = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index+1])) - Double.valueOf(isNull(priorValvar)));
                        String valPer2 = getFormattedValue( RATE , variancePer);
                        discountPerVariance.addStringProperties(commonColumn + priorList.get(j), valPer2 + PERCENT );
                        }
                        
                       if(obj[index +2+((j + 1) * 3)]==null){
                            rpuVariance.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                       }else{
                        String rpuPer = StringUtils.EMPTY + obj[index +2+((j + 1) * 3)];
                        String rpupercentPer = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index+2])) - Double.valueOf(isNull(rpuPer)));
                        String rpuPercentage = getFormattedValue(AMOUNT, rpupercentPer);
                        rpuVariance.addStringProperties(commonColumn + priorList.get(j), rpuPercentage);
                       }
                        
                      if(obj[index +((j + 1) * 3)]==null && obj[index]==null){
                         discountDollarPercent.addStringProperties(commonColumn + priorList.get(j),StringUtils.EMPTY); 
                      }else{
                       String priorVal2 = StringUtils.EMPTY + obj[index +((j + 1) * 3)];
                        Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal2))) / Double.valueOf(isNull(priorVal2));
                        if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                            perChange = 0.0;
                        }
                        String change = String.valueOf(perChange);
                        String baseValu = getFormattedValue(RATE_PER, change);
                        discountDollarPercent.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                      }
                        
                      if(obj[index +1+((j + 1) * 3)]==null && obj[index+1]==null){
                          discountPerPercent.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                      }else{
                       String priorValRpu = StringUtils.EMPTY + obj[index +1+((j + 1) * 3)];
                        Double perChangerpu = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index+1])) - Double.valueOf(isNull(priorValRpu))) / Double.valueOf(isNull(priorValRpu));
                        if (perChangerpu.isNaN() || perChangerpu.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChangerpu))) {
                            perChangerpu = 0.0;
                        }
                        String changerpu = String.valueOf(perChangerpu);
                        String baseValurpu = getFormattedValue(RATE_PER, changerpu);
                        discountPerPercent.addStringProperties(commonColumn + priorList.get(j), baseValurpu + PERCENT);
                      }
                        
                        if(obj[index +2+((j + 1) * 3)]==null && obj[index+2]==null){
                             rpuPercent.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                        } else{
                      String priorRpuPer = StringUtils.EMPTY + obj[index +2+((j + 1) * 3)];
                        Double rpuChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index+2])) - Double.valueOf(isNull(priorRpuPer))) / Double.valueOf(isNull(priorRpuPer));
                        if (rpuChange.isNaN() || rpuChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(rpuChange))) {
                            rpuChange = 0.0;
                        }
                        String changePriorRpu = String.valueOf(rpuChange);
                        String baseValueRpu = getFormattedValue(RATE_PER, changePriorRpu);
                        rpuPercent.addStringProperties(commonColumn + priorList.get(j), baseValueRpu + PERCENT);
                        }


                }

                        oldDiscount = newDiscount;
                    } else {
                        
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

                        oldDiscount = newDiscount;
                         discountDollarValue.setGroup(String.valueOf(obj[4]));
                        discountDollarVariance.setGroup(String.valueOf(obj[4]));
                        discountDollarPercent.setGroup(String.valueOf(obj[4]));
                        
                        discountPerValue.setGroup(String.valueOf(obj[4]));
                        discountPerVariance.setGroup(String.valueOf(obj[4]));
                        discountPerPercent.setGroup(String.valueOf(obj[4]));
                        
                        rpuValue.setGroup(String.valueOf(obj[4]));
                        rpuVariance.setGroup(String.valueOf(obj[4]));
                        rpuPercent.setGroup(String.valueOf(obj[4]));

                     if (frequencyDivision == 4) {
                        commonColumn = "Q" + obj[3] + StringUtils.EMPTY + obj[2];
                    } else if (frequencyDivision == 2) {
                        commonColumn = "S" + obj[3] + StringUtils.EMPTY + obj[2];
                    } else if (frequencyDivision == 1) {
                         commonColumn = StringUtils.EMPTY + obj[2];
                    } else if (frequencyDivision == 12) {
                       String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[3])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[2];
                    }

//                    String currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[5])));
//                    String baseValue1 = getFormattedValue(AMOUNT, currentValue);
//                    discountDollarValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue1);
//                    discountDollarVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(),  ZERO ); 
//                    discountDollarPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(),  ZERO );
//                    
//                     String baseValue2 = getFormattedValue(RATE, currentValue);
//                    discountPerValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(),  baseValue2 + PERCENT );
//                    discountPerVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(),  ZERO + PERCENT); 
//                    discountPerPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(),  ZERO + PERCENT);
//                    
//                    rpuValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue1);
//                    rpuVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(),  ZERO); 
//                    rpuPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(),  ZERO);

                        String currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[5])));
                    String baseValue1 = getFormattedValue(AMOUNT, currentValue);
                    if (obj[5] == null) {
                        discountDollarValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), StringUtils.EMPTY);
                        discountPerValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), StringUtils.EMPTY);
                        rpuValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), StringUtils.EMPTY);
                    } else {
                        discountDollarValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue1);
                        String baseValue2 = getFormattedValue_Percent(RATE, currentValue);
                        discountPerValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue2 + PERCENT);
                        rpuValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue1);
                    }  
                      
                      discountDollarVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), ZERO);
                    discountDollarPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), ZERO);

                    discountPerVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), ZERO + PERCENT);
                    discountPerPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), ZERO + PERCENT);

                    rpuVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), ZERO);
                    rpuPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), ZERO); 
                    
                      for (int j = 0; j < priorList.size(); j++) {
//                      String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * 3)])));
//                        String val = getFormattedValue(AMOUNT, priorVal);
//                        discountDollarValue.addStringProperties(commonColumn + priorList.get(j), val);
//                        
//                        String priorValPer = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index +1+ ((j + 1) * 3)])));
//                        String valPer = getFormattedValue(RATE , priorValPer);
//                        discountPerValue.addStringProperties(commonColumn + priorList.get(j), valPer + PERCENT);
//                        
//                         String rpuValues = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index +2+ ((j + 1) * 3)])));
//                        String rpuval = getFormattedValue(isPer ? RATE : AMOUNT, rpuValues);
//                        rpuValue.addStringProperties(commonColumn + priorList.get(j), rpuval);
//                        
//                        String priorVal1 = StringUtils.EMPTY + obj[index + ((j + 1) * 3)];
//                        String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal1)));
//                        String val1 = getFormattedValue(AMOUNT, variance);
//                        discountDollarVariance.addStringProperties(commonColumn + priorList.get(j),val1);
//                        
//                        String priorValvar = StringUtils.EMPTY + obj[index +1+((j + 1) * 3)];
//                        String variancePer = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index+1])) - Double.valueOf(isNull(priorValvar)));
//                        String valPer2 = getFormattedValue( RATE, variancePer);
//                        discountPerVariance.addStringProperties(commonColumn + priorList.get(j),  valPer2 + PERCENT );
//                        
//                        String rpuPer = StringUtils.EMPTY + obj[index +2+((j + 1) * 3)];
//                        String rpupercentPer = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index+2])) - Double.valueOf(isNull(rpuPer)));
//                        String rpuPercentage = getFormattedValue(AMOUNT, rpupercentPer);
//                        rpuVariance.addStringProperties(commonColumn + priorList.get(j), rpuPercentage);
//
//                        String priorVal2 = StringUtils.EMPTY + obj[index +((j + 1) * 3)];
//                        Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal2))) / Double.valueOf(isNull(priorVal2));
//                        if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
//                            perChange = 0.0;
//                        }
//                        String change = String.valueOf(perChange);
//                        String baseValu = getFormattedValue(RATE_PER, change);
//                        discountDollarPercent.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
//                        
//                        
//                       String priorValRpu = StringUtils.EMPTY + obj[index +1+((j + 1) * 3)];
//                        Double perChangerpu = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index+1])) - Double.valueOf(isNull(priorValRpu))) / Double.valueOf(isNull(priorValRpu));
//                        if (perChangerpu.isNaN() || perChangerpu.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChangerpu))) {
//                            perChangerpu = 0.0;
//                        }
//                        String changerpu = String.valueOf(perChangerpu);
//                        String baseValurpu = getFormattedValue(RATE_PER, changerpu);
//                        discountPerPercent.addStringProperties(commonColumn + priorList.get(j), baseValurpu + PERCENT);
//                        
//                        
//                         String priorRpuPer = StringUtils.EMPTY + obj[index +2+((j + 1) * 3)];
//                        Double rpuChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index+2])) - Double.valueOf(isNull(priorRpuPer))) / Double.valueOf(isNull(priorRpuPer));
//                        if (rpuChange.isNaN() || rpuChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(rpuChange))) {
//                            rpuChange = 0.0;
//                        }
//                        String changePriorRpu = String.valueOf(rpuChange);
//                        String baseValueRpu = getFormattedValue(RATE_PER, changePriorRpu);
//                        rpuPercent.addStringProperties(commonColumn + priorList.get(j), baseValueRpu + PERCENT);


                      if (obj[index + ((j + 1) * 3)] == null) {
                        discountDollarValue.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                    } else {
                        String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * 3)])));
                        String val = getFormattedValue(AMOUNT, priorVal);
                        discountDollarValue.addStringProperties(commonColumn + priorList.get(j), val);
                    }
                        
                    if (obj[index + 1 + ((j + 1) * 3)] == null) {
                        discountPerValue.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                    } else {
                        String priorValPer = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + 1 + ((j + 1) * 3)])));
                        String valPer = getFormattedValue(RATE, priorValPer);
                        discountPerValue.addStringProperties(commonColumn + priorList.get(j), valPer + PERCENT);
                    }
                       if (obj[index + 2 + ((j + 1) * 3)] == null) {
                        rpuValue.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                    } else {
                        String rpuValues = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + 2 + ((j + 1) * 3)])));
                        String rpuval = getFormattedValue(AMOUNT, rpuValues);
                        rpuValue.addStringProperties(commonColumn + priorList.get(j), rpuval);
                    }
                        
                        if(obj[index + ((j + 1) * 3)]==null){
                         discountDollarVariance.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);   
                        }else{
                        String priorVal1 = StringUtils.EMPTY + obj[index + ((j + 1) * 3)];
                        String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal1)));
                        String val1 = getFormattedValue( AMOUNT, variance);
                        discountDollarVariance.addStringProperties(commonColumn + priorList.get(j), val1);
                        }
                        
                        if(obj[index +1+((j + 1) * 3)]==null){
                            discountPerVariance.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                        }else{
                        String priorValvar = StringUtils.EMPTY + obj[index +1+((j + 1) * 3)];
                        String variancePer = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index+1])) - Double.valueOf(isNull(priorValvar)));
                        String valPer2 = getFormattedValue( RATE , variancePer);
                        discountPerVariance.addStringProperties(commonColumn + priorList.get(j), valPer2 + PERCENT );
                        }
                        
                       if(obj[index +2+((j + 1) * 3)]==null){
                            rpuVariance.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                       }else{
                        String rpuPer = StringUtils.EMPTY + obj[index +2+((j + 1) * 3)];
                        String rpupercentPer = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index+2])) - Double.valueOf(isNull(rpuPer)));
                        String rpuPercentage = getFormattedValue(AMOUNT, rpupercentPer);
                        rpuVariance.addStringProperties(commonColumn + priorList.get(j), rpuPercentage);
                       }
                        
                      if(obj[index +((j + 1) * 3)]==null && obj[index]==null){
                         discountDollarPercent.addStringProperties(commonColumn + priorList.get(j),StringUtils.EMPTY); 
                      }else{
                       String priorVal2 = StringUtils.EMPTY + obj[index +((j + 1) * 3)];
                        Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal2))) / Double.valueOf(isNull(priorVal2));
                        if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                            perChange = 0.0;
                        }
                        String change = String.valueOf(perChange);
                        String baseValu = getFormattedValue(RATE_PER, change);
                        discountDollarPercent.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                      }
                        
                      if(obj[index +1+((j + 1) * 3)]==null && obj[index+1]==null){
                          discountPerPercent.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                      }else{
                       String priorValRpu = StringUtils.EMPTY + obj[index +1+((j + 1) * 3)];
                        Double perChangerpu = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index+1])) - Double.valueOf(isNull(priorValRpu))) / Double.valueOf(isNull(priorValRpu));
                        if (perChangerpu.isNaN() || perChangerpu.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChangerpu))) {
                            perChangerpu = 0.0;
                        }
                        String changerpu = String.valueOf(perChangerpu);
                        String baseValurpu = getFormattedValue(RATE_PER, changerpu);
                        discountPerPercent.addStringProperties(commonColumn + priorList.get(j), baseValurpu + PERCENT);
                      }
                        
                        if(obj[index +2+((j + 1) * 3)]==null && obj[index+2]==null){
                             rpuPercent.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                        } else{
                      String priorRpuPer = StringUtils.EMPTY + obj[index +2+((j + 1) * 3)];
                        Double rpuChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index+2])) - Double.valueOf(isNull(priorRpuPer))) / Double.valueOf(isNull(priorRpuPer));
                        if (rpuChange.isNaN() || rpuChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(rpuChange))) {
                            rpuChange = 0.0;
                        }
                        String changePriorRpu = String.valueOf(rpuChange);
                        String baseValueRpu = getFormattedValue(RATE_PER, changePriorRpu);
                        rpuPercent.addStringProperties(commonColumn + priorList.get(j), baseValueRpu + PERCENT);
                        }
                }
                    }
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

                String key = oldHierarchyNo.substring(oldHierarchyNo.indexOf('-') + 1);
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
                        
                        discountDollarValue.setGroup(String.valueOf(obj[4]));
                        discountDollarVariance.setGroup(String.valueOf(obj[4]));
                        discountDollarPercent.setGroup(String.valueOf(obj[4]));
                        
                         discountPerValue.setGroup(String.valueOf(obj[4]));
                        discountPerVariance.setGroup(String.valueOf(obj[4]));
                        discountPerPercent.setGroup(String.valueOf(obj[4])); 
                       
                          rpuValue.setGroup(String.valueOf(obj[4]));
                        rpuVariance.setGroup(String.valueOf(obj[4]));
                        rpuPercent.setGroup(String.valueOf(obj[4]));
                        
                        newDiscount=String.valueOf(obj[4]);
                  if (frequencyDivision == 4) {
                        commonColumn = "Q" + obj[3] + StringUtils.EMPTY + obj[2];
                    } else if (frequencyDivision == 2) {
                        commonColumn = "S" + obj[3] + StringUtils.EMPTY + obj[2];
                    } else if (frequencyDivision == 1) {
                      commonColumn = StringUtils.EMPTY + obj[2];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[3])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[2];
                    }

//                    String currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[5])));
//                    String baseValue1 = getFormattedValue(AMOUNT, currentValue);
//                    discountDollarValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue1);
//                    discountDollarVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(),  ZERO ); 
//                    discountDollarPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(),  ZERO);
//                    
//                    String baseValue2 = getFormattedValue(RATE, currentValue);
//                    discountPerValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue2 + PERCENT );
//                    discountPerVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(),  ZERO + PERCENT); 
//                    discountPerPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(),  ZERO + PERCENT);
//                    
//                    rpuValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue1);
//                    rpuVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(),  ZERO ); 
//                    rpuPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(),  ZERO );

                           String currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[5])));
                    String baseValue1 = getFormattedValue(AMOUNT, currentValue);
                    if (obj[5] == null) {
                        discountDollarValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), StringUtils.EMPTY);
                        discountPerValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), StringUtils.EMPTY);
                        rpuValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), StringUtils.EMPTY);
                    } else {
                        discountDollarValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue1);
                        String baseValue2 = getFormattedValue_Percent(RATE, currentValue);
                        discountPerValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue2 + PERCENT);
                        rpuValue.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue1);
                    }  
                      
                      discountDollarVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), ZERO);
                    discountDollarPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), ZERO);

                    discountPerVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), ZERO + PERCENT);
                    discountPerPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), ZERO + PERCENT);

                    rpuVariance.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), ZERO);
                    rpuPercent.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), ZERO); 

                         for (int j = 0; j < priorList.size(); j++) {
//                      String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * 3)])));
//                        String val = getFormattedValue(AMOUNT, priorVal);
//                        discountDollarValue.addStringProperties(commonColumn + priorList.get(j), val);
//                        
//                        String priorValPer = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index +1+ ((j + 1) * 3)])));
//                        String valPer = getFormattedValue(RATE , priorValPer);
//                        discountPerValue.addStringProperties(commonColumn + priorList.get(j), valPer + PERCENT);
//                        
//                         String rpuValues = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index +2+ ((j + 1) * 3)])));
//                        String rpuval = getFormattedValue(isPer ? RATE : AMOUNT, rpuValues);
//                        rpuValue.addStringProperties(commonColumn + priorList.get(j), rpuval);
//                        
//                        String priorVal1 = StringUtils.EMPTY + obj[index + ((j + 1) * 3)];
//                        String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal1)));
//                        String val1 = getFormattedValue(AMOUNT, variance);
//                        discountDollarVariance.addStringProperties(commonColumn + priorList.get(j), val1);
//                        
//                        String priorValvar = StringUtils.EMPTY + obj[index +1+((j + 1) * 3)];
//                        String variancePer = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index+1])) - Double.valueOf(isNull(priorValvar)));
//                        String valPer2 = getFormattedValue(RATE, variancePer);
//                        discountPerVariance.addStringProperties(commonColumn + priorList.get(j), valPer2 + PERCENT);
//                        
//                        String rpuPer = StringUtils.EMPTY + obj[index +2+((j + 1) * 3)];
//                        String rpupercentPer = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index+2])) - Double.valueOf(isNull(rpuPer)));
//                        String rpuPercentage = getFormattedValue(AMOUNT, rpupercentPer);
//                        rpuVariance.addStringProperties(commonColumn + priorList.get(j),  rpuPercentage);
//                        
//                        String priorVal2 = StringUtils.EMPTY + obj[index +((j + 1) * 3)];
//                        Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal2))) / Double.valueOf(isNull(priorVal2));
//                        if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
//                            perChange = 0.0;
//                        }
//                        String change = String.valueOf(perChange);
//                        String baseValu = getFormattedValue(RATE_PER, change);
//                        discountDollarPercent.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
//                        
//                       String priorValRpu = StringUtils.EMPTY + obj[index +1+((j + 1) * 3)];
//                        Double perChangerpu = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index+1])) - Double.valueOf(isNull(priorValRpu))) / Double.valueOf(isNull(priorValRpu));
//                        if (perChangerpu.isNaN() || perChangerpu.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChangerpu))) {
//                            perChangerpu = 0.0;
//                        }
//                        String changerpu = String.valueOf(perChangerpu);
//                        String baseValurpu = getFormattedValue(RATE_PER, changerpu);
//                        discountPerPercent.addStringProperties(commonColumn + priorList.get(j), baseValurpu + PERCENT);
//                        
//                        
//                         String priorRpuPer = StringUtils.EMPTY + obj[index +2+((j + 1) * 3)];
//                        Double rpuChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index+2])) - Double.valueOf(isNull(priorRpuPer))) / Double.valueOf(isNull(priorRpuPer));
//                        if (rpuChange.isNaN() || rpuChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(rpuChange))) {
//                            rpuChange = 0.0;
//                        }
//                        String changePriorRpu = String.valueOf(rpuChange);
//                        String baseValueRpu = getFormattedValue(RATE_PER, changePriorRpu);
//                        rpuPercent.addStringProperties(commonColumn + priorList.get(j), baseValueRpu + PERCENT);

                  if (obj[index + ((j + 1) * 3)] == null) {
                        discountDollarValue.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                    } else {
                        String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * 3)])));
                        String val = getFormattedValue(AMOUNT, priorVal);
                        discountDollarValue.addStringProperties(commonColumn + priorList.get(j), val);
                    }
                        
                    if (obj[index + 1 + ((j + 1) * 3)] == null) {
                        discountPerValue.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                    } else {
                        String priorValPer = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + 1 + ((j + 1) * 3)])));
                        String valPer = getFormattedValue(RATE, priorValPer);
                        discountPerValue.addStringProperties(commonColumn + priorList.get(j), valPer + PERCENT);
                    }
                       if (obj[index + 2 + ((j + 1) * 3)] == null) {
                        rpuValue.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                    } else {
                        String rpuValues = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + 2 + ((j + 1) * 3)])));
                        String rpuval = getFormattedValue(AMOUNT, rpuValues);
                        rpuValue.addStringProperties(commonColumn + priorList.get(j), rpuval);
                    }
                        
                        if(obj[index + ((j + 1) * 3)]==null){
                         discountDollarVariance.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);   
                        }else{
                        String priorVal1 = StringUtils.EMPTY + obj[index + ((j + 1) * 3)];
                        String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal1)));
                        String val1 = getFormattedValue( AMOUNT, variance);
                        discountDollarVariance.addStringProperties(commonColumn + priorList.get(j), val1);
                        }
                        
                        if(obj[index +1+((j + 1) * 3)]==null){
                            discountPerVariance.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                        }else{
                        String priorValvar = StringUtils.EMPTY + obj[index +1+((j + 1) * 3)];
                        String variancePer = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index+1])) - Double.valueOf(isNull(priorValvar)));
                        String valPer2 = getFormattedValue( RATE , variancePer);
                        discountPerVariance.addStringProperties(commonColumn + priorList.get(j), valPer2 + PERCENT );
                        }
                        
                       if(obj[index +2+((j + 1) * 3)]==null){
                            rpuVariance.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                       }else{
                        String rpuPer = StringUtils.EMPTY + obj[index +2+((j + 1) * 3)];
                        String rpupercentPer = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index+2])) - Double.valueOf(isNull(rpuPer)));
                        String rpuPercentage = getFormattedValue(AMOUNT, rpupercentPer);
                        rpuVariance.addStringProperties(commonColumn + priorList.get(j), rpuPercentage);
                       }
                        
                      if(obj[index +((j + 1) * 3)]==null && obj[index]==null){
                         discountDollarPercent.addStringProperties(commonColumn + priorList.get(j),StringUtils.EMPTY); 
                      }else{
                       String priorVal2 = StringUtils.EMPTY + obj[index +((j + 1) * 3)];
                        Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal2))) / Double.valueOf(isNull(priorVal2));
                        if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                            perChange = 0.0;
                        }
                        String change = String.valueOf(perChange);
                        String baseValu = getFormattedValue(RATE_PER, change);
                        discountDollarPercent.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                      }
                        
                      if(obj[index +1+((j + 1) * 3)]==null && obj[index+1]==null){
                          discountPerPercent.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                      }else{
                       String priorValRpu = StringUtils.EMPTY + obj[index +1+((j + 1) * 3)];
                        Double perChangerpu = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index+1])) - Double.valueOf(isNull(priorValRpu))) / Double.valueOf(isNull(priorValRpu));
                        if (perChangerpu.isNaN() || perChangerpu.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChangerpu))) {
                            perChangerpu = 0.0;
                        }
                        String changerpu = String.valueOf(perChangerpu);
                        String baseValurpu = getFormattedValue(RATE_PER, changerpu);
                        discountPerPercent.addStringProperties(commonColumn + priorList.get(j), baseValurpu + PERCENT);
                      }
                        
                        if(obj[index +2+((j + 1) * 3)]==null && obj[index+2]==null){
                             rpuPercent.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                        } else{
                      String priorRpuPer = StringUtils.EMPTY + obj[index +2+((j + 1) * 3)];
                        Double rpuChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index+2])) - Double.valueOf(isNull(priorRpuPer))) / Double.valueOf(isNull(priorRpuPer));
                        if (rpuChange.isNaN() || rpuChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(rpuChange))) {
                            rpuChange = 0.0;
                        }
                        String changePriorRpu = String.valueOf(rpuChange);
                        String baseValueRpu = getFormattedValue(RATE_PER, changePriorRpu);
                        rpuPercent.addStringProperties(commonColumn + priorList.get(j), baseValueRpu + PERCENT);
                        }


                }

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

                String key = oldHierarchyNo.substring(oldHierarchyNo.indexOf('-') + 1);
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
        List<Integer> projList = selection.getProjIdList();
        ProjectionVarianceDTO discountDto = new ProjectionVarianceDTO();
        List<String> discountNames = new ArrayList<String>(selection.getDiscountLevel().equals("Program") ? selection.getDiscountNameList() : selection.getDiscountNameCFF());
        for (int i = 0; i < discountNames.size(); i++) {
            String name = String.valueOf(discountNames.get(i)).replaceAll(" ", StringUtils.EMPTY);
            discountNameMap.put(name, String.valueOf(i));
        }
        discountNameMap.put(RETURNS, NoNumber);
        discountNameMap.put(Constants.LabelConstants.PPA.getConstant(), NoNumber);
        discountNameMap.put(Constants.LabelConstants.MANDATED.getConstant(), NoNumber);
        discountNameMap.put(Constants.LabelConstants.SUPPLEMENTAL.getConstant(), NoNumber);

       Map<String, ProjectionVarianceDTO> periodDiscountMap = new HashMap<String, ProjectionVarianceDTO>();
        for (int i = 0; i < count; i++) {
            Object[] obj = (Object[]) procRawList_detail_discount.get(i);
            if (i == 0) {
                oldHierarchyNo = String.valueOf(obj[0]);
                oldHierarchyNo = oldHierarchyNo.substring(oldHierarchyNo.indexOf('-') + 1);
            }
            String newHierarchyNo = String.valueOf(obj[0]);
            newHierarchyNo = newHierarchyNo.substring(newHierarchyNo.indexOf('-') + 1);
            newyear = String.valueOf(obj[2]);
            newPeriod = String.valueOf(obj[3]);
            /* Below If condition used to check next hierarchy No is same with old hierarchy No*/
            if (oldHierarchyNo.equals(newHierarchyNo)) {
                if (oldYear.equals(newyear) && newPeriod.equals(oldPeriod)) {
                    String discount = String.valueOf(obj[4]).replaceAll(" ", StringUtils.EMPTY);
                    discount=discount_Name(discount);
                    setBase_Value(discountDto,obj,discount);
                    discountNo = discount_No(discount);
                    customize_PriorList(discountDto, projList, discount, discountNo, obj);

                } else{
                    if (i == 0) {
                    discountDto = new ProjectionVarianceDTO();
                    commonColumn=getVisibleColumn_Header(obj);
                    String discount = String.valueOf(obj[4]).replaceAll(" ", StringUtils.EMPTY);
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
                    String discount = String.valueOf(obj[4]).replaceAll(" ", StringUtils.EMPTY);
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
                  String discount = String.valueOf(obj[4]).replaceAll(" ", StringUtils.EMPTY);
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
     
     private void customize_PriorList(ProjectionVarianceDTO discountDto,List<Integer> projList,String discount,String discountNo,Object[] obj)
     {
                       if(!projList.isEmpty()){
                         for(int j=1;j<=projList.size();j++)  {
                         String column="DiscountAmountValue"+discount+discountNo+projList.get(j-1);
                         String priorValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[5+(j*3)])));
                         discountDto.addStringProperties(column,  priorValue);
                          
                        String discountDollarVar="DiscountAmountVar"+discount+discountNo+projList.get(j-1);
                        String priorVal1 = StringUtils.EMPTY + obj[5+(j*3)];
                        String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[5])) - Double.valueOf(isNull(priorVal1)));
                        String val1 = getFormattedValue(RATE, variance);
                        discountDto.addStringProperties(discountDollarVar, val1);
                        
                        String discountDollarPer="DiscountAmountPer"+discount+discountNo+projList.get(j-1);
                        Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[5])) - Double.valueOf(isNull(priorVal1))) / Double.valueOf(isNull(priorVal1));
                        if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                            perChange = 0.0;
                        }
                        String change = String.valueOf(perChange);
                        String baseValu = getFormattedValue(RATE_PER, change);
                        discountDto.addStringProperties(discountDollarPer, baseValu + PERCENT);
                        
                         column="DiscountSalesValue"+discount+discountNo+projList.get(j-1);
                         String priorPerValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[6+(j*3)])));
                         String valPer = getFormattedValue(RATE , priorPerValue);
                        discountDto.addStringProperties(column, valPer + PERCENT);
                        
                         column="DiscountSalesVar"+discount+discountNo+projList.get(j-1);
                        priorVal1 = StringUtils.EMPTY + obj[6+(j*3)];
                        variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[6])) - Double.valueOf(isNull(priorVal1)));
                        val1 = getFormattedValue(AMOUNT, variance);
                        discountDto.addStringProperties(column, val1);
                        
                         column="DiscountSalesPer"+discount+discountNo+projList.get(j-1);
                         priorPerValue = StringUtils.EMPTY + obj[6+(j*3)];
                        Double perChangerpu = (Double.valueOf(isNull(StringUtils.EMPTY + obj[6])) - Double.valueOf(isNull(priorPerValue))) / Double.valueOf(isNull(priorPerValue));
                        if (perChangerpu.isNaN() || perChangerpu.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChangerpu))) {
                            perChangerpu = 0.0;
                        }
                        String changerpu = String.valueOf(perChangerpu);
                        String baseValurpu = getFormattedValue(RATE_PER, changerpu);
                        discountDto.addStringProperties(column, baseValurpu + PERCENT);
                        
                         column="RPUValue"+discount+discountNo+projList.get(j-1);
                        String rpuValues = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[7+(j*3)])));
                        String rpuval = getFormattedValue(AMOUNT, rpuValues);
                        discountDto.addStringProperties(column, rpuval);
                        
                        column="RPUVariance"+discount+discountNo+projList.get(j-1);
                        String rpuPer = StringUtils.EMPTY + obj[7+(j*3)];
                        String rpupercentPer = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[7])) - Double.valueOf(isNull(rpuPer)));
                        String rpuPercentage = getFormattedValue(AMOUNT, rpupercentPer);
                        discountDto.addStringProperties(column,  rpuPercentage);
                        
                          column="RPUPer"+discount+discountNo+projList.get(j-1);
                         String priorRpuPer = StringUtils.EMPTY + obj[7+(j*3)];
                        Double rpuChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index+2])) - Double.valueOf(isNull(priorRpuPer))) / Double.valueOf(isNull(priorRpuPer));
                        if (rpuChange.isNaN() || rpuChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(rpuChange))) {
                            rpuChange = 0.0;
                        }
                        String changePriorRpu = String.valueOf(rpuChange);
                        String baseValueRpu = getFormattedValue(RATE_PER, changePriorRpu);
                        discountDto.addStringProperties(column, baseValueRpu + PERCENT);
                        
                       }
                     }
     }
     
     
                  private void setBase_Value(ProjectionVarianceDTO discountDto,Object[] obj,String discount){
                    String discountNo=StringUtils.EMPTY;
                    discountNo = discount_No(discount);
                    String visibleColumn = "DiscountAmountValue" + String.valueOf(obj[4]).replaceAll(" ", StringUtils.EMPTY) + discountNo + CURRENT + selection.getCurrentProjId();

                    String currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[5])));
                    discountDto.addStringProperties(visibleColumn, currentValue);
                    String discountVarCurrent = "DiscountAmountVar" + discount + discountNo + CURRENT + selection.getCurrentProjId();
                    discountDto.addStringProperties(discountVarCurrent, "$0");

                    String discountVarPer = "DiscountAmountPer" + discount + discountNo + CURRENT + selection.getCurrentProjId();
                    discountDto.addStringProperties(discountVarPer, "0.00%");

                    String discountPerColumn = "DiscountSalesValue" + String.valueOf(obj[4]).replaceAll(" ", StringUtils.EMPTY) + discountNo + CURRENT + selection.getCurrentProjId();
                    String priorValPer = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[6])));
                    String valPer = getFormattedValue(RATE, priorValPer);
                    discountDto.addStringProperties(discountPerColumn, valPer + PERCENT);

                    String discountVariance = "DiscountSalesVar" + discount + discountNo + CURRENT + selection.getCurrentProjId();
                    String valPer2 = getFormattedValue(RATE, "0");
                    discountDto.addStringProperties(discountVariance, valPer2);

                    String discountVarPercent = "DiscountSalesPer" + discount + discountNo + CURRENT + selection.getCurrentProjId();
                    discountDto.addStringProperties(discountVarPercent, valPer2);

                    discountPerColumn = "RPUValue" + String.valueOf(obj[4]).replaceAll(" ", StringUtils.EMPTY) + discountNo + CURRENT + selection.getCurrentProjId();
                    priorValPer = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[7])));
                    valPer = getFormattedValue(AMOUNT, priorValPer);
                    discountDto.addStringProperties(discountPerColumn, valPer);

                    discountVariance = "RPUVariance" + discount + discountNo + CURRENT + selection.getCurrentProjId();
                    valPer2 = getFormattedValue(AMOUNT, "0");
                    discountDto.addStringProperties(discountVariance, valPer2);

                    discountVarPercent = "RPUPer" + discount + discountNo + CURRENT + selection.getCurrentProjId();
                    valPer2 = getFormattedValue(AMOUNT, "0");
                    discountDto.addStringProperties(discountVarPercent, valPer2); 
     }
     
     
    private String getVisibleColumn_Header(Object[] obj) {
        String commonColumn = StringUtils.EMPTY;
        if (frequencyDivision == 4) {
            commonColumn = "Q" + obj[3] + StringUtils.EMPTY + obj[2];
        } else if (frequencyDivision == 2) {
            commonColumn = "S" + obj[3] + StringUtils.EMPTY + obj[2];
        } else if (frequencyDivision == 1) {
            commonColumn = StringUtils.EMPTY + obj[2];
        } else if (frequencyDivision == 12) {
            String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[3])) - 1);
            commonColumn = monthName.toLowerCase() + obj[2];
        }
        return commonColumn;
    }
                  
}
