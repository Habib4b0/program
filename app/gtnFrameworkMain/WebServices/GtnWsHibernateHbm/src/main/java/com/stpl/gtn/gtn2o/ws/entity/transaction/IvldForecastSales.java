package com.stpl.gtn.gtn2o.ws.entity.transaction;
// Generated May 8, 2017 5:24:12 PM by Hibernate Tools 4.3.1

import java.util.Date;

/**
 * IvldForecastSales generated by hbm2java
 */
@SuppressWarnings("serial")
public class IvldForecastSales implements java.io.Serializable {

	private int ivldForecastSalesSid;
	private String forecastIntfid;
	private String forecastYear;
	private String forecastMonth;
	private String ndc;
	private String forecastStartDate;
	private String units;
	private String dollars;
	private String forecastValueType;
	private String forecastValue;
	private String product;
	private String brand;
	private String segment;
	private String percentageEstimate;
	private String percentageEstimateYear;
	private String actualSalesPercentage;
	private String actualSalesPercentageMonth;
	private String forecastedSalesPercentage;
	private String forecastedSalesPercentMonth;
	private String forecastVer;
	private String price;
	private String country;
	private String forecastName;
	private String forecastDate;
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;
	private String batchId;
	private String source;
	private String addChgDelIndicator;
	private String reasonForFailure;
	private Date intfInsertedDate;
	private String errorCode;
	private String errorField;
	private String reprocessedFlag;
	private boolean checkRecord;

	public IvldForecastSales() {
	}

	public IvldForecastSales(int ivldForecastSalesSid, String forecastIntfid) {
		this.ivldForecastSalesSid = ivldForecastSalesSid;
		this.forecastIntfid = forecastIntfid;
	}

	public IvldForecastSales(int ivldForecastSalesSid, String forecastIntfid, String forecastYear, String forecastMonth,
			String ndc, String forecastStartDate, String units, String dollars, String forecastValueType,
			String forecastValue, String product, String brand, String segment, String percentageEstimate,
			String percentageEstimateYear, String actualSalesPercentage, String actualSalesPercentageMonth,
			String forecastedSalesPercentage, String forecastedSalesPercentMonth, String forecastVer, String price,
			String country, String forecastName, String forecastDate, String createdBy, Date createdDate,
			String modifiedBy, Date modifiedDate, String batchId, String source, String addChgDelIndicator,
			String reasonForFailure, Date intfInsertedDate, String errorCode, String errorField, String reprocessedFlag,
			boolean checkRecord) {
		this.ivldForecastSalesSid = ivldForecastSalesSid;
		this.forecastIntfid = forecastIntfid;
		this.forecastYear = forecastYear;
		this.forecastMonth = forecastMonth;
		this.ndc = ndc;
		this.forecastStartDate = forecastStartDate;
		this.units = units;
		this.dollars = dollars;
		this.forecastValueType = forecastValueType;
		this.forecastValue = forecastValue;
		this.product = product;
		this.brand = brand;
		this.segment = segment;
		this.percentageEstimate = percentageEstimate;
		this.percentageEstimateYear = percentageEstimateYear;
		this.actualSalesPercentage = actualSalesPercentage;
		this.actualSalesPercentageMonth = actualSalesPercentageMonth;
		this.forecastedSalesPercentage = forecastedSalesPercentage;
		this.forecastedSalesPercentMonth = forecastedSalesPercentMonth;
		this.forecastVer = forecastVer;
		this.price = price;
		this.country = country;
		this.forecastName = forecastName;
		this.forecastDate = forecastDate;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.batchId = batchId;
		this.source = source;
		this.addChgDelIndicator = addChgDelIndicator;
		this.reasonForFailure = reasonForFailure;
		this.intfInsertedDate = intfInsertedDate;
		this.errorCode = errorCode;
		this.errorField = errorField;
		this.reprocessedFlag = reprocessedFlag;
		this.checkRecord = checkRecord;
	}

	public int getIvldForecastSalesSid() {
		return ivldForecastSalesSid;
	}

	public void setIvldForecastSalesSid(int ivldForecastSalesSid) {
		this.ivldForecastSalesSid = ivldForecastSalesSid;
	}

	public String getForecastIntfid() {
		return forecastIntfid;
	}

	public void setForecastIntfid(String forecastIntfid) {
		this.forecastIntfid = forecastIntfid;
	}

	public String getForecastYear() {
		return this.forecastYear;
	}

	public void setForecastYear(String forecastYear) {
		this.forecastYear = forecastYear;
	}

	public String getForecastMonth() {
		return this.forecastMonth;
	}

	public void setForecastMonth(String forecastMonth) {
		this.forecastMonth = forecastMonth;
	}

	public String getNdc() {
		return this.ndc;
	}

	public void setNdc(String ndc) {
		this.ndc = ndc;
	}

	public String getForecastStartDate() {
		return this.forecastStartDate;
	}

	public void setForecastStartDate(String forecastStartDate) {
		this.forecastStartDate = forecastStartDate;
	}

	public String getUnits() {
		return this.units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getDollars() {
		return this.dollars;
	}

	public void setDollars(String dollars) {
		this.dollars = dollars;
	}

	public String getForecastValueType() {
		return this.forecastValueType;
	}

	public void setForecastValueType(String forecastValueType) {
		this.forecastValueType = forecastValueType;
	}

	public String getForecastValue() {
		return this.forecastValue;
	}

	public void setForecastValue(String forecastValue) {
		this.forecastValue = forecastValue;
	}

	public String getProduct() {
		return this.product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSegment() {
		return this.segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public String getPercentageEstimate() {
		return this.percentageEstimate;
	}

	public void setPercentageEstimate(String percentageEstimate) {
		this.percentageEstimate = percentageEstimate;
	}

	public String getPercentageEstimateYear() {
		return this.percentageEstimateYear;
	}

	public void setPercentageEstimateYear(String percentageEstimateYear) {
		this.percentageEstimateYear = percentageEstimateYear;
	}

	public String getActualSalesPercentage() {
		return this.actualSalesPercentage;
	}

	public void setActualSalesPercentage(String actualSalesPercentage) {
		this.actualSalesPercentage = actualSalesPercentage;
	}

	public String getActualSalesPercentageMonth() {
		return this.actualSalesPercentageMonth;
	}

	public void setActualSalesPercentageMonth(String actualSalesPercentageMonth) {
		this.actualSalesPercentageMonth = actualSalesPercentageMonth;
	}

	public String getForecastedSalesPercentage() {
		return this.forecastedSalesPercentage;
	}

	public void setForecastedSalesPercentage(String forecastedSalesPercentage) {
		this.forecastedSalesPercentage = forecastedSalesPercentage;
	}

	public String getForecastedSalesPercentMonth() {
		return this.forecastedSalesPercentMonth;
	}

	public void setForecastedSalesPercentMonth(String forecastedSalesPercentMonth) {
		this.forecastedSalesPercentMonth = forecastedSalesPercentMonth;
	}

	public String getForecastVer() {
		return this.forecastVer;
	}

	public void setForecastVer(String forecastVer) {
		this.forecastVer = forecastVer;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getForecastName() {
		return this.forecastName;
	}

	public void setForecastName(String forecastName) {
		this.forecastName = forecastName;
	}

	public String getForecastDate() {
		return this.forecastDate;
	}

	public void setForecastDate(String forecastDate) {
		this.forecastDate = forecastDate;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getBatchId() {
		return this.batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getAddChgDelIndicator() {
		return this.addChgDelIndicator;
	}

	public void setAddChgDelIndicator(String addChgDelIndicator) {
		this.addChgDelIndicator = addChgDelIndicator;
	}

	public String getReasonForFailure() {
		return this.reasonForFailure;
	}

	public void setReasonForFailure(String reasonForFailure) {
		this.reasonForFailure = reasonForFailure;
	}

	public Date getIntfInsertedDate() {
		return this.intfInsertedDate;
	}

	public void setIntfInsertedDate(Date intfInsertedDate) {
		this.intfInsertedDate = intfInsertedDate;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorField() {
		return this.errorField;
	}

	public void setErrorField(String errorField) {
		this.errorField = errorField;
	}

	public String getReprocessedFlag() {
		return this.reprocessedFlag;
	}

	public void setReprocessedFlag(String reprocessedFlag) {
		this.reprocessedFlag = reprocessedFlag;
	}

	public boolean getCheckRecord() {
		return this.checkRecord;
	}

	public void setCheckRecord(boolean checkRecord) {
		this.checkRecord = checkRecord;
	}

}
