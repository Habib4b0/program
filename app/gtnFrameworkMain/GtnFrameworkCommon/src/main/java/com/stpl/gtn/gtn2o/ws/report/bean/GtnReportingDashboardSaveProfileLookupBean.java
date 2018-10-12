package com.stpl.gtn.gtn2o.ws.report.bean;

import java.util.List;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;

public class GtnReportingDashboardSaveProfileLookupBean {

    public GtnReportingDashboardSaveProfileLookupBean() {
        super();
    }

    private List<Object> displaySelectionTabVariable;
    private String displaySelectionTabPeriodRangeFrom;
    private String displaySelectionTabPeriodRangeTo;
    private String displaySelectionTabFrequency;
    private String displaySelectionTabComparisonBasis;
    private String displaySelectionTabCustomViewCombobox;
    private List<Object> displaySelectionTabVariableCategory;
    private String displaySelectionTabAnnualTotals;

    private String filterOptionsTabCustomerLevel;
    private List<Object> filterOptionsTabCustomerFilter;
    private String filterOptionsTabProductLevel;
    private List<Object> filterOptionsTabProductFilter;
    private String filterOptionsTabDeductionLevel;
    private List<Object> filterOptionsTabDeductionFilter;
    private List<Object> filterOptionsTabSalesInclusion;
    private List<Object> filterOptionsTabDeductionInclusion;

    private String reportOptionsVariableAndVarianceSequencing;
    private String reportOptionsHeaderSequencing;
    private String reportOptionsViewOptions;
    private List<Object> reportOptionsDisplayFormat;
    private String reportOptionsUnitsOfMeasure;
    private String reportOptionsCurrencyDisplay;

	private List<GtnReportComparisonProjectionBean> displaySelectionComparisonLookupBeanList;
	private List<GtnReportVariableBreakdownLookupBean> variableBreakdownLookupBeanSaveList;
	private List<GtnReportComparisonBreakdownLookupBean> comparisonBreakdownLookupBeanSaveList;
	private String reportProfileviewName;
	private String reportProfileviewType;
	private GtnWsRecordBean recordBean;
	private Integer reportProfileViewId;

	private String customerHierarchy;
	private int customerRelationSid;
	private int customerLevelNo;
	private String productHierarchy;
	private int productRelationSid;
	private int productLevelNo;
        private Integer flag;

	public List<Object> getDisplaySelectionTabVariable() {
		return displaySelectionTabVariable;
	}

	public void setDisplaySelectionTabVariable(List<Object> displaySelectionTabVariable) {
		this.displaySelectionTabVariable = displaySelectionTabVariable;
	}

	public String getDisplaySelectionTabPeriodRangeFrom() {
		return displaySelectionTabPeriodRangeFrom;
	}

	public void setDisplaySelectionTabPeriodRangeFrom(String displaySelectionTabPeriodRangeFrom) {
		this.displaySelectionTabPeriodRangeFrom = displaySelectionTabPeriodRangeFrom;
	}

	public String getDisplaySelectionTabPeriodRangeTo() {
		return displaySelectionTabPeriodRangeTo;
	}

	public void setDisplaySelectionTabPeriodRangeTo(String displaySelectionTabPeriodRangeTo) {
		this.displaySelectionTabPeriodRangeTo = displaySelectionTabPeriodRangeTo;
	}

	public String getDisplaySelectionTabFrequency() {
		return displaySelectionTabFrequency;
	}

	public void setDisplaySelectionTabFrequency(String displaySelectionTabFrequency) {
		this.displaySelectionTabFrequency = displaySelectionTabFrequency;
	}

	public String getDisplaySelectionTabComparisonBasis() {
		return displaySelectionTabComparisonBasis;
	}

	public void setDisplaySelectionTabComparisonBasis(String displaySelectionTabComparisonBasis) {
		this.displaySelectionTabComparisonBasis = displaySelectionTabComparisonBasis;
	}

	public String getDisplaySelectionTabCustomViewCombobox() {
		return displaySelectionTabCustomViewCombobox;
	}

	public void setDisplaySelectionTabCustomViewCombobox(String displaySelectionTabCustomViewCombobox) {
		this.displaySelectionTabCustomViewCombobox = displaySelectionTabCustomViewCombobox;
	}

	public List<Object> getDisplaySelectionTabVariableCategory() {
		return displaySelectionTabVariableCategory;
	}

	public void setDisplaySelectionTabVariableCategory(List<Object> displaySelectionTabVariableCategory) {
		this.displaySelectionTabVariableCategory = displaySelectionTabVariableCategory;
	}

	public String getDisplaySelectionTabAnnualTotals() {
		return displaySelectionTabAnnualTotals;
	}

	public void setDisplaySelectionTabAnnualTotals(String displaySelectionTabAnnualTotals) {
		this.displaySelectionTabAnnualTotals = displaySelectionTabAnnualTotals;
	}

	public String getFilterOptionsTabCustomerLevel() {
		return filterOptionsTabCustomerLevel;
	}

	public void setFilterOptionsTabCustomerLevel(String filterOptionsTabCustomerLevel) {
		this.filterOptionsTabCustomerLevel = filterOptionsTabCustomerLevel;
	}

	public List<Object> getFilterOptionsTabCustomerFilter() {
		return filterOptionsTabCustomerFilter;
	}

	public void setFilterOptionsTabCustomerFilter(List<Object> filterOptionsTabCustomerFilter) {
		this.filterOptionsTabCustomerFilter = filterOptionsTabCustomerFilter;
	}

	public String getFilterOptionsTabProductLevel() {
		return filterOptionsTabProductLevel;
	}

	public void setFilterOptionsTabProductLevel(String filterOptionsTabProductLevel) {
		this.filterOptionsTabProductLevel = filterOptionsTabProductLevel;
	}

	public List<Object> getFilterOptionsTabProductFilter() {
		return filterOptionsTabProductFilter;
	}

	public void setFilterOptionsTabProductFilter(List<Object> filterOptionsTabProductFilter) {
		this.filterOptionsTabProductFilter = filterOptionsTabProductFilter;
	}

	public String getFilterOptionsTabDeductionLevel() {
		return filterOptionsTabDeductionLevel;
	}

	public void setFilterOptionsTabDeductionLevel(String filterOptionsTabDeductionLevel) {
		this.filterOptionsTabDeductionLevel = filterOptionsTabDeductionLevel;
	}

	public List<Object> getFilterOptionsTabDeductionFilter() {
		return filterOptionsTabDeductionFilter;
	}

	public void setFilterOptionsTabDeductionFilter(List<Object> filterOptionsTabDeductionFilter) {
		this.filterOptionsTabDeductionFilter = filterOptionsTabDeductionFilter;
	}

	public List<Object> getFilterOptionsTabSalesInclusion() {
		return filterOptionsTabSalesInclusion;
	}

	public void setFilterOptionsTabSalesInclusion(List<Object> filterOptionsTabSalesInclusion) {
		this.filterOptionsTabSalesInclusion = filterOptionsTabSalesInclusion;
	}

	public List<Object> getFilterOptionsTabDeductionInclusion() {
		return filterOptionsTabDeductionInclusion;
	}

	public void setFilterOptionsTabDeductionInclusion(List<Object> filterOptionsTabDeductionInclusion) {
		this.filterOptionsTabDeductionInclusion = filterOptionsTabDeductionInclusion;
	}

	public String getReportOptionsVariableAndVarianceSequencing() {
		return reportOptionsVariableAndVarianceSequencing;
	}

	public void setReportOptionsVariableAndVarianceSequencing(String reportOptionsVariableAndVarianceSequencing) {
		this.reportOptionsVariableAndVarianceSequencing = reportOptionsVariableAndVarianceSequencing;
	}

	public String getReportOptionsHeaderSequencing() {
		return reportOptionsHeaderSequencing;
	}

	public void setReportOptionsHeaderSequencing(String reportOptionsHeaderSequencing) {
		this.reportOptionsHeaderSequencing = reportOptionsHeaderSequencing;
	}

	public String getReportOptionsViewOptions() {
		return reportOptionsViewOptions;
	}

	public void setReportOptionsViewOptions(String reportOptionsViewOptions) {
		this.reportOptionsViewOptions = reportOptionsViewOptions;
	}

	public List<Object> getReportOptionsDisplayFormat() {
		return reportOptionsDisplayFormat;
	}

	public void setReportOptionsDisplayFormat(List<Object> reportOptionsDisplayFormat) {
		this.reportOptionsDisplayFormat = reportOptionsDisplayFormat;
	}

	public String getReportOptionsUnitsOfMeasure() {
		return reportOptionsUnitsOfMeasure;
	}

	public void setReportOptionsUnitsOfMeasure(String reportOptionsUnitsOfMeasure) {
		this.reportOptionsUnitsOfMeasure = reportOptionsUnitsOfMeasure;
	}

	public String getReportOptionsCurrencyDisplay() {
		return reportOptionsCurrencyDisplay;
	}

	public void setReportOptionsCurrencyDisplay(String reportOptionsCurrencyDisplay) {
		this.reportOptionsCurrencyDisplay = reportOptionsCurrencyDisplay;
	}

	public List<GtnReportComparisonProjectionBean> getDisplaySelectionComparisonLookupBeanList() {
		return displaySelectionComparisonLookupBeanList;
	}

	public void setDisplaySelectionComparisonLookupBeanList(
			List<GtnReportComparisonProjectionBean> displaySelectionComparisonLookupBeanList) {
		this.displaySelectionComparisonLookupBeanList = displaySelectionComparisonLookupBeanList;
	}

	public List<GtnReportVariableBreakdownLookupBean> getVariableBreakdownLookupBeanSaveList() {
		return variableBreakdownLookupBeanSaveList;
	}

	public void setVariableBreakdownLookupBeanSaveList(
			List<GtnReportVariableBreakdownLookupBean> variableBreakdownLookupBeanSaveList) {
		this.variableBreakdownLookupBeanSaveList = variableBreakdownLookupBeanSaveList;
	}

	public List<GtnReportComparisonBreakdownLookupBean> getComparisonBreakdownLookupBeanSaveList() {
		return comparisonBreakdownLookupBeanSaveList;
	}

	public void setComparisonBreakdownLookupBeanSaveList(
			List<GtnReportComparisonBreakdownLookupBean> comparisonBreakdownLookupBeanSaveList) {
		this.comparisonBreakdownLookupBeanSaveList = comparisonBreakdownLookupBeanSaveList;
	}

	public String getReportProfileviewName() {
		return reportProfileviewName;
	}

	public void setReportProfileviewName(String reportProfileviewName) {
		this.reportProfileviewName = reportProfileviewName;
	}

	public String getReportProfileviewType() {
		return reportProfileviewType;
	}

	public void setReportProfileviewType(String reportProfileviewType) {
		this.reportProfileviewType = reportProfileviewType;
	}

	public GtnWsRecordBean getRecordBean() {
		return recordBean;
	}

	public void setRecordBean(GtnWsRecordBean recordBean) {
		this.recordBean = recordBean;
	}

	public Integer getReportProfileViewId() {
		return reportProfileViewId;
	}

	public void setReportProfileViewId(Integer reportProfileViewId) {
		this.reportProfileViewId = reportProfileViewId;
	}

	public String getCustomerHierarchy() {
		return customerHierarchy;
	}

	public void setCustomerHierarchy(String customerHierarchy) {
		this.customerHierarchy = customerHierarchy;
	}

	public int getCustomerRelationSid() {
		return customerRelationSid;
	}

	public void setCustomerRelationSid(int customerRelationSid) {
		this.customerRelationSid = customerRelationSid;
	}

	public int getCustomerLevelNo() {
		return customerLevelNo;
	}

	public void setCustomerLevelNo(int customerLevelNo) {
		this.customerLevelNo = customerLevelNo;
	}

	public String getProductHierarchy() {
		return productHierarchy;
	}

	public void setProductHierarchy(String productHierarchy) {
		this.productHierarchy = productHierarchy;
	}

	public int getProductRelationSid() {
		return productRelationSid;
	}

	public void setProductRelationSid(int productRelationSid) {
		this.productRelationSid = productRelationSid;
	}

	public int getProductLevelNo() {
		return productLevelNo;
	}

	public void setProductLevelNo(int productLevelNo) {
		this.productLevelNo = productLevelNo;
	}
        
        public Integer getFlag() {
                return flag;
        }

        public void setFlag(Integer flag) {
                this.flag = flag;
         }

}
