package com.stpl.app.global.priceschedule.dto;

import com.stpl.app.util.Constants;
import com.stpl.ifs.util.HelperDTO;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * This class is used to hold the parameters of Price Schedule
 *
 * @author manikanta
 *
 */
public class PSDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -99691467780841405L;
    /**
     * Parent price schedule name Field
     */
    private String parentPriceScheduleName = StringUtils.EMPTY;
    /**
     * Parent price schedule Id Field
     */
    private String parentPriceScheduleId = StringUtils.EMPTY;
    /**
     * Price schedule name Field
     */
    private Date priceScheduleStartDate;
    /**
     * Price schedule No Field
     */
    private String priceScheduleNo = StringUtils.EMPTY;
    /**
     * Price schedule Name Field
     */
    private String priceScheduleName = StringUtils.EMPTY;
    /**
     * Price schedule Id Field
     */
    private String priceScheduleId = StringUtils.EMPTY;

    private String itemId = StringUtils.EMPTY;

    private String itemNo = StringUtils.EMPTY;

    private String itemName = StringUtils.EMPTY;

    private String source = StringUtils.EMPTY;

    private Integer parentFlagSID = 0;

    public Integer getParentFlagSID() {
        return parentFlagSID;
    }

    public void setParentFlagSID(Integer parentFlagSID) {
        this.parentFlagSID = parentFlagSID;
    }

    /**
     * Price schedule Type Field
     */
    private HelperDTO priceScheduleType = new HelperDTO(0, Constants.SELECT_ONE);
    /**
     * Modified Date Field
     */
    private Date modifiedDate;
    /**
     * Price Schedule System generated Id Field
     */
    private int priceScheduleSystemId;
    /**
     * Record Lock Status Field to indicate edit/view flag
     */
    private String recordLockStatus;
    /**
     * Created Date Field
     */
    private Date createdDate;
    /**
     * Created By Field for user Id
     */
    private String createdBy = StringUtils.EMPTY;
    /**
     * Price Schedule Designation Field
     */
    private HelperDTO priceScheduleDesignation = new HelperDTO(0, Constants.SELECT_ONE);
    /**
     * Price Schedule EndDate Field
     */
    private Date priceScheduleEndDate;
    /**
     * rice Schedule Status Field
     */
    private HelperDTO priceScheduleStatus = new HelperDTO(0, Constants.SELECT_ONE);
    /**
     * batchId Field for ETL purpose
     */
    private String batchId;
    /**
     * Price Schedule Category Field
     */
    private HelperDTO priceScheduleCategory = new HelperDTO(0, Constants.SELECT_ONE);
    /**
     * TradeClass Field
     */
    private HelperDTO tradeClass = new HelperDTO(0, Constants.SELECT_ONE);
    /**
     * InboundStatus Field for ETL Add/Change flag
     */
    private String inboundStatus;
    /**
     * ModifiedBy Filed to store User Id
     */
    private String modifiedBy = StringUtils.EMPTY;

    /**
     * The internal notes.
     */
    private String internalNotes = StringUtils.EMPTY;

    private String priceScheduleIdValue;
    private String priceScheduleNoValue;
    private String priceScheduleNameValue;
    private String priceScheduleStatusValue;
    private String priceScheduleTypeValue;
    private String priceScheduleDesignationValue;
    private String priceScheduleCategoryValue;
    private String priceScheduleTradeValue;

    private String searchFields;

    private String searchValue = StringUtils.EMPTY;

    private String record;

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    /**
     * Price schedule name Field
     */
    private Date massDate;

    private Date priceProtectionMassDate;

    /**
     * massValue Field
     */
    private String massValue = StringUtils.EMPTY;

    private String priceProtectionMassValue = StringUtils.EMPTY;

    private String psSystemId = StringUtils.EMPTY;

    private String parentId = StringUtils.EMPTY;

    private String parentName = StringUtils.EMPTY;

    private Map<Integer, String> itemPricingQualifierMap = new HashMap<>();

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @return the parentPriceScheduleName
     */
    public String getParentPriceScheduleName() {
        return parentPriceScheduleName;
    }

    /**
     * @param parentPriceScheduleName the parentPriceScheduleName to set
     */
    public void setParentPriceScheduleName(final String parentPriceScheduleName) {
        this.parentPriceScheduleName = parentPriceScheduleName;
    }

    /**
     * @return the parentPriceScheduleId
     */
    public String getParentPriceScheduleId() {
        return parentPriceScheduleId;
    }

    /**
     * @param parentPriceScheduleId the parentPriceScheduleId to set
     */
    public void setParentPriceScheduleId(final String parentPriceScheduleId) {
        this.parentPriceScheduleId = parentPriceScheduleId;
    }

    /**
     * @return the priceScheduleStartDate
     */
    public Date getPriceScheduleStartDate() {
        return priceScheduleStartDate;
    }

    /**
     * @param priceScheduleStartDate the priceScheduleStartDate to set
     */
    public void setPriceScheduleStartDate(final Date priceScheduleStartDate) {
        this.priceScheduleStartDate = priceScheduleStartDate;
    }

    /**
     * @return the priceScheduleNo
     */
    public String getPriceScheduleNo() {
        return priceScheduleNo;
    }

    /**
     * @param priceScheduleNo the priceScheduleNo to set
     */
    public void setPriceScheduleNo(final String priceScheduleNo) {
        this.priceScheduleNo = priceScheduleNo;
    }

    /**
     * @return the priceScheduleName
     */
    public String getPriceScheduleName() {
        return priceScheduleName;
    }

    /**
     * @param priceScheduleName the priceScheduleName to set
     */
    public void setPriceScheduleName(final String priceScheduleName) {
        this.priceScheduleName = priceScheduleName;
    }

    /**
     * @return the priceScheduleId
     */
    public String getPriceScheduleId() {
        return priceScheduleId;
    }

    /**
     * @param priceScheduleId the priceScheduleId to set
     */
    public void setPriceScheduleId(final String priceScheduleId) {
        this.priceScheduleId = priceScheduleId;
    }

    /**
     * @return the modifiedDate
     */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     * @param modifiedDate the modifiedDate to set
     */
    public void setModifiedDate(final Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * @return the priceScheduleSystemId
     */
    public int getPriceScheduleSystemId() {
        return priceScheduleSystemId;
    }

    /**
     * @param priceScheduleSystemId the priceScheduleSystemId to set
     */
    public void setPriceScheduleSystemId(final int priceScheduleSystemId) {
        this.priceScheduleSystemId = priceScheduleSystemId;
    }

    /**
     * @return the recordLockStatus
     */
    public String getRecordLockStatus() {
        return recordLockStatus;
    }

    /**
     * @param recordLockStatus the recordLockStatus to set
     */
    public void setRecordLockStatus(final String recordLockStatus) {
        this.recordLockStatus = recordLockStatus;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the priceScheduleEndDate
     */
    public Date getPriceScheduleEndDate() {
        return priceScheduleEndDate;
    }

    /**
     * @param priceScheduleEndDate the priceScheduleEndDate to set
     */
    public void setPriceScheduleEndDate(final Date priceScheduleEndDate) {
        this.priceScheduleEndDate = priceScheduleEndDate;
    }

    /**
     * @return the batchId
     */
    public String getBatchId() {
        return batchId;
    }

    /**
     * @param batchId the batchId to set
     */
    public void setBatchId(final String batchId) {
        this.batchId = batchId;
    }

    /**
     * @return the inboundStatus
     */
    public String getInboundStatus() {
        return inboundStatus;
    }

    /**
     * @param inboundStatus the inboundStatus to set
     */
    public void setInboundStatus(final String inboundStatus) {
        this.inboundStatus = inboundStatus;
    }

    /**
     * @return the modifiedBy
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * @param modifiedBy the modifiedBy to set
     */
    public void setModifiedBy(final String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getPriceScheduleIdValue() {
        return priceScheduleIdValue;
    }

    public void setPriceScheduleIdValue(String priceScheduleIdValue) {
        this.priceScheduleIdValue = priceScheduleIdValue;
    }

    public String getPriceScheduleNoValue() {
        return priceScheduleNoValue;
    }

    public void setPriceScheduleNoValue(String priceScheduleNoValue) {
        this.priceScheduleNoValue = priceScheduleNoValue;
    }

    public String getPriceScheduleNameValue() {
        return priceScheduleNameValue;
    }

    public void setPriceScheduleNameValue(String priceScheduleNameValue) {
        this.priceScheduleNameValue = priceScheduleNameValue;
    }

    public String getPriceScheduleStatusValue() {
        return priceScheduleStatusValue;
    }

    public void setPriceScheduleStatusValue(String priceScheduleStatusValue) {
        this.priceScheduleStatusValue = priceScheduleStatusValue;
    }

    public String getPriceScheduleTypeValue() {
        return priceScheduleTypeValue;
    }

    public void setPriceScheduleTypeValue(String priceScheduleTypeValue) {
        this.priceScheduleTypeValue = priceScheduleTypeValue;
    }

    public HelperDTO getPriceScheduleType() {
        return priceScheduleType;
    }

    public void setPriceScheduleType(HelperDTO priceScheduleType) {
        this.priceScheduleType = priceScheduleType;
    }

    public HelperDTO getPriceScheduleDesignation() {
        return priceScheduleDesignation;
    }

    public void setPriceScheduleDesignation(HelperDTO priceScheduleDesignation) {
        this.priceScheduleDesignation = priceScheduleDesignation;
    }

    public HelperDTO getPriceScheduleStatus() {
        return priceScheduleStatus;
    }

    public void setPriceScheduleStatus(HelperDTO priceScheduleStatus) {
        this.priceScheduleStatus = priceScheduleStatus;
    }

    public HelperDTO getPriceScheduleCategory() {
        return priceScheduleCategory;
    }

    public void setPriceScheduleCategory(HelperDTO priceScheduleCategory) {
        this.priceScheduleCategory = priceScheduleCategory;
    }

    public HelperDTO getTradeClass() {
        return tradeClass;
    }

    public void setTradeClass(HelperDTO tradeClass) {
        this.tradeClass = tradeClass;
    }

    public String getPriceScheduleDesignationValue() {
        return priceScheduleDesignationValue;
    }

    public void setPriceScheduleDesignationValue(String priceScheduleDesignationValue) {
        this.priceScheduleDesignationValue = priceScheduleDesignationValue;
    }

    public String getPriceScheduleCategoryValue() {
        return priceScheduleCategoryValue;
    }

    public void setPriceScheduleCategoryValue(String priceScheduleCategoryValue) {
        this.priceScheduleCategoryValue = priceScheduleCategoryValue;
    }

    public String getPriceScheduleTradeValue() {
        return priceScheduleTradeValue;
    }

    public void setPriceScheduleTradeValue(String priceScheduleTradeValue) {
        this.priceScheduleTradeValue = priceScheduleTradeValue;
    }

    public String getSearchFields() {
        return searchFields;
    }

    public void setSearchFields(String searchFields) {
        this.searchFields = searchFields;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getMassValue() {
        return massValue;
    }

    public void setMassValue(String massValue) {
        this.massValue = massValue;
    }

    public Date getMassDate() {
        return massDate;
    }

    public void setMassDate(Date massDate) {
        this.massDate = massDate;
    }

    public String getInternalNotes() {
        return internalNotes;
    }

    public void setInternalNotes(String internalNotes) {
        this.internalNotes = internalNotes;
    }

    public String getPsSystemId() {
        return psSystemId;
    }

    public void setPsSystemId(String psSystemId) {
        this.psSystemId = psSystemId;
    }

    public String getParentId() {
        return parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Date getPriceProtectionMassDate() {
        return priceProtectionMassDate;
    }

    public void setPriceProtectionMassDate(Date priceProtectionMassDate) {
        this.priceProtectionMassDate = priceProtectionMassDate;
    }

    public String getPriceProtectionMassValue() {
        return priceProtectionMassValue;
    }

    public void setPriceProtectionMassValue(String priceProtectionMassValue) {
        this.priceProtectionMassValue = priceProtectionMassValue;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Map<Integer, String> getItemPricingQualifierMap() {
        return itemPricingQualifierMap;
    }

    public void setItemPricingQualifierMap(Map<Integer, String> itemPricingQualifierMap) {
        this.itemPricingQualifierMap = itemPricingQualifierMap;
    }
}
