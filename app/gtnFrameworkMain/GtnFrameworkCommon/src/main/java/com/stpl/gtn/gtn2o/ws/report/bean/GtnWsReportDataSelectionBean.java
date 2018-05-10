package com.stpl.gtn.gtn2o.ws.report.bean;

import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;

public class GtnWsReportDataSelectionBean {

    private long customerHierarchySid;
    private long productHierarchySid;
    private int customerHierarchyForecastLevel;
    private int productHierarchyForecastLevel;
    private String name;
    private int productRelationshipBuilderSid;
    private int customerRelationshipBuilderSid;
    private int customerHierarchyVersionNo;
    private int productHierarchyVersionNo;
    private int customerRelationshipVersionNo;
    private int productRelationshipVersionNo;
    private Date forecastEligibleDate;
    
    private Integer companyReport;
    private Integer businessUnitReport;
    private Integer fromPeriodReport;
    private GtnWsRecordBean customerHierarchyRecordBean;
    private List<GtnWsRecordBean> availableCustomerHierarchyList;
    private List<GtnWsRecordBean> availableProductHierarchyList;
    
    public int getProductRelationshipBuilderSid() {
        return productRelationshipBuilderSid;
    }

    public void setProductRelationshipBuilderSid(int productRelationshipBuilderSid) {
        this.productRelationshipBuilderSid = productRelationshipBuilderSid;
    }

    public int getCustomerRelationshipBuilderSid() {
        return customerRelationshipBuilderSid;
    }

    public void setCustomerRelationshipBuilderSid(int customerRelationshipBuilderSid) {
        this.customerRelationshipBuilderSid = customerRelationshipBuilderSid;
    }

    public int getCustomerHierarchyVersionNo() {
        return customerHierarchyVersionNo;
    }

    public void setCustomerHierarchyVersionNo(int customerHierarchyVersionNo) {
        this.customerHierarchyVersionNo = customerHierarchyVersionNo;
    }

    public int getProductHierarchyVersionNo() {
        return productHierarchyVersionNo;
    }

    public void setProductHierarchyVersionNo(int productHierarchyVersionNo) {
        this.productHierarchyVersionNo = productHierarchyVersionNo;
    }

    public int getCustomerRelationshipVersionNo() {
        return customerRelationshipVersionNo;
    }

    public void setCustomerRelationshipVersionNo(int customerRelationshipVersionNo) {
        this.customerRelationshipVersionNo = customerRelationshipVersionNo;
    }

    public int getProductRelationshipVersionNo() {
        return productRelationshipVersionNo;
    }

    public void setProductRelationshipVersionNo(int productRelationshipVersionNo) {
        this.productRelationshipVersionNo = productRelationshipVersionNo;
    }

    public Date getForecastEligibleDate() {
        return forecastEligibleDate;
    }

    public void setForecastEligibleDate(Date forecastEligibleDate) {
        this.forecastEligibleDate = forecastEligibleDate;
    }

    public String getReportDataSource() {
        return reportDataSource;
    }

    public void setReportDataSource(String reportDataSource) {
        this.reportDataSource = reportDataSource;
    }
    private String reportDataSource;

    public long getCustomerHierarchySid() {
        return customerHierarchySid;
    }

    public void setCustomerHierarchySid(long customerHierarchySid) {
        this.customerHierarchySid = customerHierarchySid;
    }

    public long getProductHierarchySid() {
        return productHierarchySid;
    }

    public void setProductHierarchySid(long productHierarchySid) {
        this.productHierarchySid = productHierarchySid;
    }

    public int getCustomerHierarchyForecastLevel() {
        return customerHierarchyForecastLevel;
    }

    public void setCustomerHierarchyForecastLevel(int customerHierarchyForecastLevel) {
        this.customerHierarchyForecastLevel = customerHierarchyForecastLevel;
    }

    public int getProductHierarchyForecastLevel() {
        return productHierarchyForecastLevel;
    }

    public void setProductHierarchyForecastLevel(int productHierarchyForecastLevel) {
        this.productHierarchyForecastLevel = productHierarchyForecastLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public Integer getCompanyReport() {
		return companyReport;
	}

	public void setCompanyReport(Integer companyReport) {
		this.companyReport = companyReport;
	}

	public Integer getBusinessUnitReport() {
		return businessUnitReport;
	}

	public void setBusinessUnitReport(Integer businessUnitReport) {
		this.businessUnitReport = businessUnitReport;
	}

	public Integer getFromPeriodReport() {
		return fromPeriodReport;
	}

	public void setFromPeriodReport(Integer fromPeriodReport) {
		this.fromPeriodReport = fromPeriodReport;
	}

	public List<GtnWsRecordBean> getAvailableCustomerHierarchyList() {
		return availableCustomerHierarchyList;
	}

	public void setAvailableCustomerHierarchyList(List<GtnWsRecordBean> availableCustomerHierarchyList) {
		this.availableCustomerHierarchyList = availableCustomerHierarchyList;
	}

	public List<GtnWsRecordBean> getAvailableProductHierarchyList() {
		return availableProductHierarchyList;
	}

	public void setAvailableProductHierarchyList(List<GtnWsRecordBean> availableProductHierarchyList) {
		this.availableProductHierarchyList = availableProductHierarchyList;
	}

	public GtnWsRecordBean getCustomerHierarchyRecordBean() {
		return customerHierarchyRecordBean;
	}

	public void setCustomerHierarchyRecordBean(GtnWsRecordBean customerHierarchyRecordBean) {
		this.customerHierarchyRecordBean = customerHierarchyRecordBean;
	}

}
