
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto;

import com.stpl.app.arm.supercode.InterFaceDTO;
import com.stpl.ifs.ui.util.NumericConstants;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtListDTO;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class AdjustmentDTO extends ExtListDTO implements InterFaceDTO {

    private String dateType;
    private String price;
    private String exclusionDetails;
    private String rateBasis;
    private String rateFrequency;
    private String ratePeriod;
    private String month;
    private String group = StringUtils.EMPTY;
    private String branditemno;
    private String branditemname;
    private String branditemmasterSid;
    private String customermasterSid;
    private Integer parent;
    private String totalUnits;
    private String totalSales;
    private String excludedUnits;
    private String excludedSales;
    private String netUnits;
    private String netSales;
    private String priceOverride;
    private String netCalculatedSales;
    private String salesVariance;
    private String salesVariancepercent;
    private String defaultColumn = StringUtils.EMPTY;
    private String totalColumn = StringUtils.EMPTY;
    private Double totalColumnValue = 0.0;
    private Integer userId;
    private Integer sessionId;
    private String period = StringUtils.EMPTY;
    private int levelNo = 0;
    private String levelValue;
    private Boolean childrenAllowed = true;
    private String levelName = StringUtils.EMPTY;
    private Integer brandSID = 0;
    private Integer[] masterSids = new Integer[NumericConstants.FIVE];
    private Integer deductionSID = 0;
    private Integer customerSID;
    private Integer contractSID = 0;
    private TreeMap<String, Integer> masterIds;
    private String treeLevelNo = StringUtils.EMPTY;
    private Integer visibleColumnIndex = 0;

    private Double excelTotalDemandAccrual = 0.0;
    private Double excelTotalDemandAccrualReforecast = 0.0;
    private Double excelTotalProjectedTotalDemandAccrual = 0.0;
    private Double excelTotalDemandAccrualRatio = 0.0;
    private Double excelTotalVariance = 0.0;
    private Double excelTotalOverride = null;// Don't Change to 0.0
    private Double excelTotalAdjustment = 0.0;
    private Double excelTotalTotalDemandAccrual = 0.0;
    private Double excelTotalDemandPaymentRecon = 0.0;
    private Double excelTotalActualPayments = 0.0;
    private Double excelTotalPaymentRatio = 0.0;
    // for TXN 3 Adjustment Summarry tab
    private Double excelTotalcPipelineAccrual = 0.0;
    private Double excelTotalpPipelineAccrual = 0.0;
    private Double excelTotalpipelineRatio = 0.0;

    private Boolean calculateFlag = false;
    private Integer demandsummaryindexAdd = 0;

    private Integer companySid = 0;
    private String levelNames;
    private String compSids;
    private String brandSids;

    private String brand;
    private String estimatedReturnUnits;
    private String actualEstimatedReturnUnits;
    private String adjEstimatedReturnUnits;
    private String valueatOriginalASP;
    private String adjValueatOriginalASP;
    private String deductionName = StringUtils.EMPTY;

    public AdjustmentDTO() {
        /*
        THE DEFAULT CONSTRUCTOR
         */
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getEstimatedReturnUnits() {
        return estimatedReturnUnits;
    }

    public void setEstimatedReturnUnits(String estimatedReturnUnits) {
        this.estimatedReturnUnits = estimatedReturnUnits;
    }

    public String getActualEstimatedReturnUnits() {
        return actualEstimatedReturnUnits;
    }

    public void setActualEstimatedReturnUnits(String actualEstimatedReturnUnits) {
        this.actualEstimatedReturnUnits = actualEstimatedReturnUnits;
    }

    public String getAdjEstimatedReturnUnits() {
        return adjEstimatedReturnUnits;
    }

    public void setAdjEstimatedReturnUnits(String adjEstimatedReturnUnits) {
        this.adjEstimatedReturnUnits = adjEstimatedReturnUnits;
    }

    public String getValueatOriginalASP() {
        return valueatOriginalASP;
    }

    public void setValueatOriginalASP(String valueatOriginalASP) {
        this.valueatOriginalASP = valueatOriginalASP;
    }

    public String getAdjValueatOriginalASP() {
        return adjValueatOriginalASP;
    }

    public void setAdjValueatOriginalASP(String adjValueatOriginalASP) {
        this.adjValueatOriginalASP = adjValueatOriginalASP;
    }

    public String getLevelNames() {
        return levelNames;
    }

    public void setLevelNames(String levelNames) {
        this.levelNames = levelNames;
    }

    public String getCompSids() {
        return compSids;
    }

    public void setCompSids(String compSids) {
        this.compSids = compSids;
    }

    public String getBrandSids() {
        return brandSids;
    }

    public void setBrandSids(String brandSids) {
        this.brandSids = brandSids;
    }

    public Integer getCompanySid() {
        return companySid;
    }

    public void setCompanySid(Integer companySid) {
        this.companySid = companySid;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getExclusionDetails() {
        return exclusionDetails;
    }

    public void setExclusionDetails(String exclusionDetails) {
        this.exclusionDetails = exclusionDetails;
    }

    public String getRateBasis() {
        return rateBasis;
    }

    public void setRateBasis(String rateBasis) {
        this.rateBasis = rateBasis;
    }

    public String getRateFrequency() {
        return rateFrequency;
    }

    public void setRateFrequency(String rateFrequency) {
        this.rateFrequency = rateFrequency;
    }

    public String getRatePeriod() {
        return ratePeriod;
    }

    public void setRatePeriod(String ratePeriod) {
        this.ratePeriod = ratePeriod;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getTotalUnits() {
        return totalUnits;
    }

    public void setTotalUnits(String totalUnits) {
        this.totalUnits = totalUnits;
    }

    public String getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(String totalSales) {
        this.totalSales = totalSales;
    }

    public String getExcludedUnits() {
        return excludedUnits;
    }

    public void setExcludedUnits(String excludedUnits) {
        this.excludedUnits = excludedUnits;
    }

    public String getExcludedSales() {
        return excludedSales;
    }

    public void setExcludedSales(String excludedSales) {
        this.excludedSales = excludedSales;
    }

    public String getNetUnits() {
        return netUnits;
    }

    public void setNetUnits(String netUnits) {
        this.netUnits = netUnits;
    }

    public String getNetSales() {
        return netSales;
    }

    public void setNetSales(String netSales) {
        this.netSales = netSales;
    }

    public String getPriceOverride() {
        return priceOverride;
    }

    public void setPriceOverride(String priceOverride) {
        this.priceOverride = priceOverride;
    }

    public String getNetCalculatedSales() {
        return netCalculatedSales;
    }

    public void setNetCalculatedSales(String netCalculatedSales) {
        this.netCalculatedSales = netCalculatedSales;
    }

    public String getSalesVariance() {
        return salesVariance;
    }

    public void setSalesVariance(String salesVariance) {
        this.salesVariance = salesVariance;
    }

    public String getSalesVariancepercent() {
        return salesVariancepercent;
    }

    public void setSalesVariancepercent(String salesVariancepercent) {
        this.salesVariancepercent = salesVariancepercent;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDefaultColumn() {
        return defaultColumn;
    }

    public void setDefaultColumn(String defaultColumn) {
        this.defaultColumn = defaultColumn;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getBranditemno() {
        return branditemno;
    }

    public void setBranditemno(String branditemno) {
        this.branditemno = branditemno;
    }

    public String getBranditemname() {
        return branditemname;
    }

    public void setBranditemname(String branditemname) {
        this.branditemname = branditemname;
    }

    public String getBranditemmasterSid() {
        return branditemmasterSid;
    }

    public void setBranditemmasterSid(String branditemmasterSid) {
        this.branditemmasterSid = branditemmasterSid;
    }

    public Boolean getChildrenAllowed() {
        return childrenAllowed;
    }

    public void setChildrenAllowed(Boolean childrenAllowed) {
        this.childrenAllowed = childrenAllowed;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getBrandSID() {
        return brandSID;
    }

    public void setBrandSID(Integer brandSID) {
        this.brandSID = brandSID;
    }

    public Integer getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(Integer levelNo) {
        this.levelNo = levelNo;
    }

    public Integer[] getMasterSids() {
        return masterSids == null ? null : masterSids.clone();
    }

    public void setMasterSids(Integer[] masterSids) {
        this.masterSids = (masterSids == null ? null : masterSids.clone());
    }

    public String getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(String levelValue) {
        this.levelValue = levelValue;
    }

    public Integer getDeductionSID() {
        return deductionSID;
    }

    public void setDeductionSID(Integer deductionSID) {
        this.deductionSID = deductionSID;
    }

    public Integer getCustomerSID() {
        return customerSID;
    }

    public void setCustomerSID(Integer customerSID) {
        this.customerSID = customerSID;
    }

    public Integer getContractSID() {
        return contractSID;
    }

    public void setContractSID(Integer contractSID) {
        this.contractSID = contractSID;
    }

    public TreeMap<String, Integer> getMasterIds() {
        return masterIds;
    }

    public void setMasterIds(TreeMap<String, Integer> masterIds) {
        this.masterIds = masterIds;
    }

    public String getTreeLevelNo() {
        return treeLevelNo;
    }

    public void setTreeLevelNo(String treeLevelNo) {
        this.treeLevelNo = treeLevelNo;
    }

    public String getCustomermasterSid() {
        return customermasterSid;
    }

    public void setCustomermasterSid(String customermasterSid) {
        this.customermasterSid = customermasterSid;
    }

    public String getTotalColumn() {
        return totalColumn;
    }

    public void setTotalColumn(String totalColumn) {
        this.totalColumn = totalColumn;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getVisibleColumnIndex() {
        return visibleColumnIndex;
    }

    public void setVisibleColumnIndex(Integer visibleColumnIndex) {
        this.visibleColumnIndex = visibleColumnIndex;
    }

    public Double getTotalColumnValue() {
        return totalColumnValue;
    }

    public void setTotalColumnValue(Double totalColumnValue) {
        this.totalColumnValue = totalColumnValue;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Double getExcelTotalDemandAccrual() {
        return excelTotalDemandAccrual;
    }

    public void setExcelTotalDemandAccrual(Double excelTotalDemandAccrual) {
        this.excelTotalDemandAccrual = excelTotalDemandAccrual;
    }

    public Double getExcelTotalDemandAccrualReforecast() {
        return excelTotalDemandAccrualReforecast;
    }

    public void setExcelTotalDemandAccrualReforecast(Double excelTotalDemandAccrualReforecast) {
        this.excelTotalDemandAccrualReforecast = excelTotalDemandAccrualReforecast;
    }

    public Double getExcelTotalProjectedTotalDemandAccrual() {
        return excelTotalProjectedTotalDemandAccrual;
    }

    public void setExcelTotalProjectedTotalDemandAccrual(Double excelTotalProjectedTotalDemandAccrual) {
        this.excelTotalProjectedTotalDemandAccrual = excelTotalProjectedTotalDemandAccrual;
    }

    public Double getExcelTotalDemandAccrualRatio() {
        return excelTotalDemandAccrualRatio;
    }

    public void setExcelTotalDemandAccrualRatio(Double excelTotalDemandAccrualRatio) {
        this.excelTotalDemandAccrualRatio = excelTotalDemandAccrualRatio;
    }

    public Double getExcelTotalVariance() {
        return excelTotalVariance;
    }

    public void setExcelTotalVariance(Double excelTotalVariance) {
        this.excelTotalVariance = excelTotalVariance;
    }

    public Double getExcelTotalOverride() {
        return excelTotalOverride;
    }

    public void setExcelTotalOverride(Double excelTotalOverride) {
        this.excelTotalOverride = excelTotalOverride;
    }

    public Double getExcelTotalAdjustment() {
        return excelTotalAdjustment;
    }

    public void setExcelTotalAdjustment(Double excelTotalAdjustment) {
        this.excelTotalAdjustment = excelTotalAdjustment;
    }

    public Double getExcelTotalTotalDemandAccrual() {
        return excelTotalTotalDemandAccrual;
    }

    public void setExcelTotalTotalDemandAccrual(Double excelTotalTotalDemandAccrual) {
        this.excelTotalTotalDemandAccrual = excelTotalTotalDemandAccrual;
    }

    public Double getExcelTotalDemandPaymentRecon() {
        return excelTotalDemandPaymentRecon;
    }

    public void setExcelTotalDemandPaymentRecon(Double excelTotalDemandPaymentRecon) {
        this.excelTotalDemandPaymentRecon = excelTotalDemandPaymentRecon;
    }

    public Double getExcelTotalActualPayments() {
        return excelTotalActualPayments;
    }

    public void setExcelTotalActualPayments(Double excelTotalActualPayments) {
        this.excelTotalActualPayments = excelTotalActualPayments;
    }

    public Double getExcelTotalPaymentRatio() {
        return excelTotalPaymentRatio;
    }

    public void setExcelTotalPaymentRatio(Double excelTotalPaymentRatio) {
        this.excelTotalPaymentRatio = excelTotalPaymentRatio;
    }

    public Boolean getCalculateFlag() {
        return calculateFlag;
    }

    public void setCalculateFlag(Boolean calculateFlag) {
        this.calculateFlag = calculateFlag;
    }

    public Integer getDemandsummaryindexAdd() {
        return demandsummaryindexAdd;
    }

    public void setDemandsummaryindexAdd(Integer demandsummaryindexAdd) {
        this.demandsummaryindexAdd = demandsummaryindexAdd;
    }

    public Double getExcelTotalcPipelineAccrual() {
        return excelTotalcPipelineAccrual;
    }

    public void setExcelTotalcPipelineAccrual(Double excelTotalcPipelineAccrual) {
        this.excelTotalcPipelineAccrual = excelTotalcPipelineAccrual;
    }

    public Double getExcelTotalpPipelineAccrual() {
        return excelTotalpPipelineAccrual;
    }

    public void setExcelTotalpPipelineAccrual(Double excelTotalpPipelineAccrual) {
        this.excelTotalpPipelineAccrual = excelTotalpPipelineAccrual;
    }

    public Double getExcelTotalpipelineRatio() {
        return excelTotalpipelineRatio;
    }

    public void setExcelTotalpipelineRatio(Double excelTotalpipelineRatio) {
        this.excelTotalpipelineRatio = excelTotalpipelineRatio;
    }

    public void setDTOValues(Map<Object, String> headerValueMap, String headerKey, Object list1, DecimalFormat decimalformat) {

        if (headerValueMap.get(headerKey) != null) {
            addStringProperties(headerValueMap.get(headerKey), list1 == null ? StringUtils.EMPTY : decimalformat.format(Double.valueOf(String.valueOf(list1))));
        }
    }

    public String getDeductionName() {
        return deductionName;
    }

    public void setDeductionName(String deductionSet1) {
        this.deductionName = deductionSet1;
    }

}
