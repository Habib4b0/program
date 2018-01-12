/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.fileSelection.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class FileMananagementResultDTO.
 *
 * @author Elangovan
 */
public class FileMananagementResultDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 4612301756295249630L;
    /**
     * The default.
     */
    private final String DEFAULT = StringUtils.EMPTY;
    /**
     * The file.
     */
    private String file = DEFAULT;
    /**
     * The effective date.
     */
    private Date effectiveDate;
    /**
     * The effective date.
     */
    private String effectiveDateStr = DEFAULT;
    /**
     * The type.
     */
    private String type = DEFAULT;
    /**
     * The version.
     */
    private String version = DEFAULT;
    /**
     * The from period.
     */
    private Date fromPeriod;
    /**
     * The to period.
     */
    private Date toPeriod;
    /**
     * The file type.
     */
    private String fileType = DEFAULT;
    /**
     * The country.
     */
    private String country = DEFAULT;
    /**
     * The currentFile.
     */
    private String currentFile = DEFAULT;
    /**
     * The selectFile.
     */
    private String selectFile = DEFAULT;
    /**
     * The file name.
     */
    private String fileName = DEFAULT;
    /**
     * The year.
     */
    private String year = DEFAULT;
    /**
     * The month.
     */
    private String month = DEFAULT;
    /**
     * The item no.
     */
    private String itemNo = DEFAULT;
    /**
     * The item name.
     */
    private String itemName = DEFAULT;
    /**
     * The start date.
     */
    private Date startDate;
    /**
     * The price.
     */
    private String price = DEFAULT;
    /**
     * The units.
     */
    private String units = DEFAULT;
    /**
     * The dollars.
     */
    private String dollars = DEFAULT;
    /**
     * The file id.
     */
    private String fileId = DEFAULT;
    /**
     * The forecast year.
     */
    private String forecastYear = DEFAULT;

    /**
     * The interface flag.
     */
    private String interfaceFlag;

    /**
     * The recordLockStatus.
     */
    private boolean recordLockStatus;

    /**
     * The check.
     */
    private Boolean check;

    /**
     * The auditVersion.
     */
    private int auditVersion;

    /**
     * The forecastSystemId.
     */
    private int forecastSystemId;

    /**
     * The File Type.
     */
    private String helperType;

    /**
     * The hiddenPrice.
     */
    private String hiddenPrice;

    /**
     * The hiddenUnits
     */
    private String hiddenUnits;

    private Date fromDate;

    private Date toDate;

    private String forecastType = DEFAULT;
    private String forcastYear = DEFAULT;

    private String forecastMonth = DEFAULT;
    private String itemId = DEFAULT;
    private String itemIdentifierCodeQualifier = DEFAULT;
    private String itemIdentifier = DEFAULT;
    private String segment = DEFAULT;
    private String brandId = DEFAULT;
    private String marketShareRatio = DEFAULT;
    private String marketShareUnits;
    private String uncapturedUnitsRatio = DEFAULT;
    private String batchId = DEFAULT;
    private String hiddenbatchId = DEFAULT;

    private String source = DEFAULT;
    private String forecastName = DEFAULT;
    private String forecastVersion = DEFAULT;
    private String organizationKey = DEFAULT;
    private String uncapturedUnits;
    private String marketSizeUnits;
    private String totalDemandUnits;
    private String totalDemandAmount;
    private String inventoryUnitChange;
    private String grossUnits;
    private String grossPrice;
    private String grossAmount;
    private String netSalesPrice;
    private String netSalesAmount;
    private String day;
    private String week;
    private String hiddenDay;
    private String hiddenWeek;
    private String hiddenMonth;
    private String hiddenYear;
    private String unitsWithdrawn;
    private String amountWithdrawn;
    private String hiddenForecastType;
    private String hiddenForecastYear;
    private String hiddenForecastMonth;
    private String hiddenItemId;
    private String hiddenOrganisationKey;
    private String companyId = DEFAULT;
    private String identifierCodeQualifier = DEFAULT;
    private String companyIdentifier = DEFAULT;
    private Integer inventoryForecastDetailsSysId = 0;
    private String hiddenCompanyId = DEFAULT;
    //This is added for customer Gts module
    private String priceType = DEFAULT;
    private String salesAmount = DEFAULT;
    private String salesInclusion = DEFAULT;
    private String deductionId = DEFAULT;
    private String deductionCategory = DEFAULT;
    private String deductionType = DEFAULT;
    private String deductionProgramType = DEFAULT;
    private String adjustmentCode = DEFAULT;
    private String deductionRate = DEFAULT;
    private String deductionAmount = DEFAULT;
    private String deductionInclusion = DEFAULT;
    private String forecastValueType = DEFAULT;
    private String forecastDate = DEFAULT;
    private String SearchforcastYear = DEFAULT;
    private String brandName = DEFAULT;
    private String businessUnit=DEFAULT;
    private int company;
    
    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
        
    public String getHiddenCompanyId() {
        return hiddenCompanyId;
    }

    public void setHiddenCompanyId(String hiddenCompanyId) {
        this.hiddenCompanyId = hiddenCompanyId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public Integer getInventoryForecastDetailsSysId() {
        return inventoryForecastDetailsSysId;
    }

    public void setInventoryForecastDetailsSysId(Integer inventoryForecastDetailsSysId) {
        this.inventoryForecastDetailsSysId = inventoryForecastDetailsSysId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getIdentifierCodeQualifier() {
        return identifierCodeQualifier;
    }

    public void setIdentifierCodeQualifier(String identifierCodeQualifier) {
        this.identifierCodeQualifier = identifierCodeQualifier;
    }

    public String getCompanyIdentifier() {
        return companyIdentifier;
    }

    public void setCompanyIdentifier(String companyIdentifier) {
        this.companyIdentifier = companyIdentifier;
    }

    public String getCurrentFile() {
        return currentFile;
    }

    public void setCurrentFile(String currentFile) {
        this.currentFile = currentFile;
    }

    public String getSelectFile() {
        return selectFile;
    }

    public void setSelectFile(String selectFile) {
        this.selectFile = selectFile;
    }

    /**
     * Gets the file.
     *
     * @return the file
     */
    public String getFile() {
        return file;
    }

    public String getEffectiveDateStr() {
        return effectiveDateStr;
    }

    public void setEffectiveDateStr(String effectiveDateStr) {
        this.effectiveDateStr = effectiveDateStr;
    }

    /**
     * Sets the file.
     *
     * @param file the new file
     */
    public void setFile(final String file) {
        this.file = file;
    }

    /**
     * Gets the effective date.
     *
     * @return the effective date
     */
    public Date getEffectiveDate() {
        return effectiveDate == null ? null : (Date) effectiveDate.clone();
    }

    /**
     * Sets the effective date.
     *
     * @param effectiveDate the new effective date
     */
    public void setEffectiveDate(final Date effectiveDate) {
        this.effectiveDate = effectiveDate == null ? null : (Date) effectiveDate.clone();
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type the new type
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Gets the version.
     *
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the version.
     *
     * @param version the new version
     */
    public void setVersion(final String version) {
        this.version = version;
    }

    /**
     * Gets the from period.
     *
     * @return the from period
     */
    public Date getFromPeriod() {
        return fromPeriod == null ? null : (Date) fromPeriod.clone();
    }

    /**
     * Sets the from period.
     *
     * @param fromPeriod the new from period
     */
    public void setFromPeriod(final Date fromPeriod) {
        this.fromPeriod = fromPeriod == null ? null : (Date) fromPeriod.clone();
    }

    /**
     * Gets the to period.
     *
     * @return the to period
     */
    public Date getToPeriod() {
        return toPeriod == null ? null : (Date) toPeriod.clone();
    }

    /**
     * Sets the to period.
     *
     * @param toPeriod the new to period
     */
    public void setToPeriod(final Date toPeriod) {
        this.toPeriod = toPeriod == null ? null : (Date) toPeriod.clone();
    }

    /**
     * Gets the file type.
     *
     * @return the file type
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * Sets the file type.
     *
     * @param fileType the new file type
     */
    public void setFileType(final String fileType) {
        this.fileType = fileType;
    }

    /**
     * Gets the country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country.
     *
     * @param country the new country
     */
    public void setCountry(final String country) {
        this.country = country;
    }

    /**
     * Gets the file name.
     *
     * @return the file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets the file name.
     *
     * @param fileName the new file name
     */
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * Gets the year.
     *
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * Sets the year.
     *
     * @param year the new year
     */
    public void setYear(final String year) {
        this.year = year;
    }

    /**
     * Gets the month.
     *
     * @return the month
     */
    public String getMonth() {
        return month;
    }

    /**
     * Sets the month.
     *
     * @param month the new month
     */
    public void setMonth(final String month) {
        this.month = month;
    }

    /**
     * Gets the item no.
     *
     * @return the item no
     */
    public String getItemNo() {
        return itemNo;
    }

    /**
     * Sets the item no.
     *
     * @param itemNo the new item no
     */
    public void setItemNo(final String itemNo) {
        this.itemNo = itemNo;
    }

    /**
     * Gets the item name.
     *
     * @return the item name
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Sets the item name.
     *
     * @param itemName the new item name
     */
    public void setItemName(final String itemName) {
        this.itemName = itemName;
    }

    public Date getStartDate() {
        return startDate == null ? null : (Date) startDate.clone();
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate == null ? null : (Date) startDate.clone();
    }

    /**
     * Gets the price.
     *
     * @return the price
     */
    public String getPrice() {
        return price;
    }

    /**
     * Sets the price.
     *
     * @param price the new price
     */
    public void setPrice(final String price) {
        this.price = price;
    }

    /**
     * Gets the units.
     *
     * @return the units
     */
    public String getUnits() {
        return units;
    }

    /**
     * Sets the units.
     *
     * @param units the new units
     */
    public void setUnits(final String units) {
        this.units = units;
    }

    /**
     * Gets the dollars.
     *
     * @return the dollars
     */
    public String getDollars() {
        return dollars;
    }

    /**
     * Sets the dollars.
     *
     * @param dollars the new dollars
     */
    public void setDollars(final String dollars) {
        this.dollars = dollars;
    }

    /**
     * Gets the file id.
     *
     * @return the file id
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * Sets the file id.
     *
     * @param fileId the new file id
     */
    public void setFileId(final String fileId) {
        this.fileId = fileId;
    }

    /**
     * Gets the default.
     *
     * @return the default
     */
    public String getDEFAULT() {
        return DEFAULT;
    }

    /**
     * Gets the forecast year.
     *
     * @return the forecast year
     */
    public String getForecastYear() {
        return forecastYear;
    }

    /**
     * Sets the forecast year.
     *
     * @param forecastYear the new forecast year
     */
    public void setForecastYear(final String forecastYear) {
        this.forecastYear = forecastYear;
    }

    /**
     * Gets the interface flag.
     *
     * @return the interface flag
     */
    public String getInterfaceFlag() {
        return interfaceFlag;
    }

    /**
     * Sets the interface flag.
     *
     * @param interfaceFlag the new interface flag
     */
    public void setInterfaceFlag(final String interfaceFlag) {
        this.interfaceFlag = interfaceFlag;
    }

    public boolean isRecordLockStatus() {
        return recordLockStatus;
    }

    public void setRecordLockStatus(boolean recordLockStatus) {
        this.recordLockStatus = recordLockStatus;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public int getAuditVersion() {
        return auditVersion;
    }

    public void setAuditVersion(int auditVersion) {
        this.auditVersion = auditVersion;
    }

    public int getForecastSystemId() {
        return forecastSystemId;
    }

    public void setForecastSystemId(int forecastSystemId) {
        this.forecastSystemId = forecastSystemId;
    }

    public String getHiddenPrice() {
        return hiddenPrice;
    }

    public void setHiddenPrice(String hiddenPrice) {
        this.hiddenPrice = hiddenPrice;
    }

    public String getHiddenUnits() {
        return hiddenUnits;
    }

    public void setHiddenUnits(String hiddenUnits) {
        this.hiddenUnits = hiddenUnits;
    }

    public Date getFromDate() {
        return fromDate == null ? null : (Date) fromDate.clone();
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate == null ? null : (Date) fromDate.clone();
    }

    public Date getToDate() {
        return toDate == null ? null : (Date) toDate.clone();
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate == null ? null : (Date) toDate.clone();
    }

    public String getHelperType() {
        return helperType;
    }

    public void setHelperType(String fileType) {
        this.helperType = fileType;
    }

    public String getForecastType() {
        return forecastType;
    }

    public void setForecastType(String forecastType) {
        this.forecastType = forecastType;
    }

    public String getForecastMonth() {
        return forecastMonth;
    }

    public void setForecastMonth(String forecastMonth) {
        this.forecastMonth = forecastMonth;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemIdentifierCodeQualifier() {
        return itemIdentifierCodeQualifier;
    }

    public void setItemIdentifierCodeQualifier(String itemIdentifierCodeQualifier) {
        this.itemIdentifierCodeQualifier = itemIdentifierCodeQualifier;
    }

    public String getItemIdentifier() {
        return itemIdentifier;
    }

    public void setItemIdentifier(String itemIdentifier) {
        this.itemIdentifier = itemIdentifier;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getMarketShareRatio() {
        return marketShareRatio;
    }

    public void setMarketShareRatio(String marketShareRatio) {
        this.marketShareRatio = marketShareRatio;
    }

    public String getMarketShareUnits() {
        return marketShareUnits;
    }

    public void setMarketShareUnits(String marketShareUnits) {
        this.marketShareUnits = marketShareUnits;
    }

    public String getUncapturedUnitsRatio() {
        return uncapturedUnitsRatio;
    }

    public void setUncapturedUnitsRatio(String uncapturedUnitsRatio) {
        this.uncapturedUnitsRatio = uncapturedUnitsRatio;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getForecastName() {
        return forecastName;
    }

    public void setForecastName(String forecastName) {
        this.forecastName = forecastName;
    }

    public String getForecastVersion() {
        return forecastVersion;
    }

    public void setForecastVersion(String forecastVersion) {
        this.forecastVersion = forecastVersion;
    }

    public String getOrganizationKey() {
        return organizationKey;
    }

    public void setOrganizationKey(String organizationKey) {
        this.organizationKey = organizationKey;
    }

    public String getUncapturedUnits() {
        return uncapturedUnits;
    }

    public void setUncapturedUnits(String uncapturedUnits) {
        this.uncapturedUnits = uncapturedUnits;
    }

    public String getMarketSizeUnits() {
        return marketSizeUnits;
    }

    public void setMarketSizeUnits(String marketSizeUnits) {
        this.marketSizeUnits = marketSizeUnits;
    }

    public String getTotalDemandUnits() {
        return totalDemandUnits;
    }

    public void setTotalDemandUnits(String totalDemandUnits) {
        this.totalDemandUnits = totalDemandUnits;
    }

    public String getTotalDemandAmount() {
        return totalDemandAmount;
    }

    public void setTotalDemandAmount(String totalDemandAmount) {
        this.totalDemandAmount = totalDemandAmount;
    }

    public String getInventoryUnitChange() {
        return inventoryUnitChange;
    }

    public void setInventoryUnitChange(String inventoryUnitChange) {
        this.inventoryUnitChange = inventoryUnitChange;
    }

    public String getGrossUnits() {
        return grossUnits;
    }

    public void setGrossUnits(String grossUnits) {
        this.grossUnits = grossUnits;
    }

    public String getGrossPrice() {
        return grossPrice;
    }

    public void setGrossPrice(String grossPrice) {
        this.grossPrice = grossPrice;
    }

    public String getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(String grossAmount) {
        this.grossAmount = grossAmount;
    }

    public String getNetSalesPrice() {
        return netSalesPrice;
    }

    public void setNetSalesPrice(String netSalesPrice) {
        this.netSalesPrice = netSalesPrice;
    }

    public String getNetSalesAmount() {
        return netSalesAmount;
    }

    public void setNetSalesAmount(String netSalesAmount) {
        this.netSalesAmount = netSalesAmount;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getForcastYear() {
        return forcastYear;
    }

    public void setForcastYear(String forcastYear) {
        this.forcastYear = forcastYear;
    }

    public String getHiddenForecastType() {
        return hiddenForecastType;
    }

    public void setHiddenForecastType(String hiddenForecastType) {
        this.hiddenForecastType = hiddenForecastType;
    }

    public String getHiddenForecastYear() {
        return hiddenForecastYear;
    }

    public void setHiddenForecastYear(String hiddenForecastYear) {
        this.hiddenForecastYear = hiddenForecastYear;
    }

    public String getHiddenForecastMonth() {
        return hiddenForecastMonth;
    }

    public void setHiddenForecastMonth(String hiddenForecastMonth) {
        this.hiddenForecastMonth = hiddenForecastMonth;
    }

    public String getHiddenItemId() {
        return hiddenItemId;
    }

    public void setHiddenItemId(String hiddenItemId) {
        this.hiddenItemId = hiddenItemId;
    }

    public String getHiddenOrganisationKey() {
        return hiddenOrganisationKey;
    }

    public void setHiddenOrganisationKey(String hiddenOrganisationKey) {
        this.hiddenOrganisationKey = hiddenOrganisationKey;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getUnitsWithdrawn() {
        return unitsWithdrawn;
    }

    public void setUnitsWithdrawn(String unitsWithdrawn) {
        this.unitsWithdrawn = unitsWithdrawn;
    }

    public String getAmountWithdrawn() {
        return amountWithdrawn;
    }

    public void setAmountWithdrawn(String amountWithdrawn) {
        this.amountWithdrawn = amountWithdrawn;
    }

    public String getHiddenDay() {
        return hiddenDay;
    }

    public void setHiddenDay(String hiddenDay) {
        this.hiddenDay = hiddenDay;
    }

    public String getHiddenWeek() {
        return hiddenWeek;
    }

    public void setHiddenWeek(String hiddenWeek) {
        this.hiddenWeek = hiddenWeek;
    }

    public String getHiddenMonth() {
        return hiddenMonth;
    }

    public void setHiddenMonth(String hiddenMonth) {
        this.hiddenMonth = hiddenMonth;
    }

    public String getHiddenYear() {
        return hiddenYear;
    }

    public void setHiddenYear(String hiddenYear) {
        this.hiddenYear = hiddenYear;
    }

    public String getHiddenbatchId() {
        return hiddenbatchId;
    }

    public void setHiddenbatchId(String hiddenbatchId) {
        this.hiddenbatchId = hiddenbatchId;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(String salesAmount) {
        this.salesAmount = salesAmount;
    }

    public String getSalesInclusion() {
        return salesInclusion;
    }

    public void setSalesInclusion(String salesInclusion) {
        this.salesInclusion = salesInclusion;
    }

    public String getDeductionId() {
        return deductionId;
    }

    public void setDeductionId(String deductionId) {
        this.deductionId = deductionId;
    }

    public String getDeductionCategory() {
        return deductionCategory;
    }

    public void setDeductionCategory(String deductionCategory) {
        this.deductionCategory = deductionCategory;
    }

    public String getDeductionType() {
        return deductionType;
    }

    public void setDeductionType(String deductionType) {
        this.deductionType = deductionType;
    }

    public String getDeductionProgramType() {
        return deductionProgramType;
    }

    public void setDeductionProgramType(String deductionProgramType) {
        this.deductionProgramType = deductionProgramType;
    }

    public String getAdjustmentCode() {
        return adjustmentCode;
    }

    public void setAdjustmentCode(String adjustmentCode) {
        this.adjustmentCode = adjustmentCode;
    }

    public String getDeductionRate() {
        return deductionRate;
    }

    public void setDeductionRate(String deductionRate) {
        this.deductionRate = deductionRate;
    }

    public String getDeductionAmount() {
        return deductionAmount;
    }

    public void setDeductionAmount(String deductionAmount) {
        this.deductionAmount = deductionAmount;
    }

    public String getDeductionInclusion() {
        return deductionInclusion;
    }

    public void setDeductionInclusion(String deductionInclusion) {
        this.deductionInclusion = deductionInclusion;
    }

    public String getForecastValueType() {
        return forecastValueType;
    }

    public void setForecastValueType(String forecastValueType) {
        this.forecastValueType = forecastValueType;
    }

    public String getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(String forecastDate) {
        this.forecastDate = forecastDate;
    }

    public String getSearchforcastYear() {
        return SearchforcastYear;
    }

    public void setSearchforcastYear(String SearchforcastYear) {
        this.SearchforcastYear = SearchforcastYear;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public int getCompany() {
        return company;
    }

    public void setCompany(int company) {
        this.company = company;
    }
    
}
