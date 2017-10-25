package com.stpl.app.model;

import com.stpl.portal.kernel.bean.AutoEscape;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.service.ServiceContext;

import com.stpl.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the PsDetails service. Represents a row in the &quot;PS_DETAILS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.PsDetailsModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.PsDetailsImpl}.
 * </p>
 *
 * @author
 * @see PsDetails
 * @see com.stpl.app.model.impl.PsDetailsImpl
 * @see com.stpl.app.model.impl.PsDetailsModelImpl
 * @generated
 */
public interface PsDetailsModel extends BaseModel<PsDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a ps details model instance should use the {@link PsDetails} interface instead.
     */

    /**
     * Returns the primary key of this ps details.
     *
     * @return the primary key of this ps details
     */
    public int getPrimaryKey();

    /**
     * Sets the primary key of this ps details.
     *
     * @param primaryKey the primary key of this ps details
     */
    public void setPrimaryKey(int primaryKey);

    /**
     * Returns the nep formula of this ps details.
     *
     * @return the nep formula of this ps details
     */
    public int getNepFormula();

    /**
     * Sets the nep formula of this ps details.
     *
     * @param nepFormula the nep formula of this ps details
     */
    public void setNepFormula(int nepFormula);

    /**
     * Returns the price of this ps details.
     *
     * @return the price of this ps details
     */
    public double getPrice();

    /**
     * Sets the price of this ps details.
     *
     * @param price the price of this ps details
     */
    public void setPrice(double price);

    /**
     * Returns the item master sid of this ps details.
     *
     * @return the item master sid of this ps details
     */
    public int getItemMasterSid();

    /**
     * Sets the item master sid of this ps details.
     *
     * @param itemMasterSid the item master sid of this ps details
     */
    public void setItemMasterSid(int itemMasterSid);

    /**
     * Returns the reset type of this ps details.
     *
     * @return the reset type of this ps details
     */
    public int getResetType();

    /**
     * Sets the reset type of this ps details.
     *
     * @param resetType the reset type of this ps details
     */
    public void setResetType(int resetType);

    /**
     * Returns the price protection start date of this ps details.
     *
     * @return the price protection start date of this ps details
     */
    public Date getPriceProtectionStartDate();

    /**
     * Sets the price protection start date of this ps details.
     *
     * @param priceProtectionStartDate the price protection start date of this ps details
     */
    public void setPriceProtectionStartDate(Date priceProtectionStartDate);

    /**
     * Returns the reset date of this ps details.
     *
     * @return the reset date of this ps details
     */
    public Date getResetDate();

    /**
     * Sets the reset date of this ps details.
     *
     * @param resetDate the reset date of this ps details
     */
    public void setResetDate(Date resetDate);

    /**
     * Returns the base price of this ps details.
     *
     * @return the base price of this ps details
     */
    public double getBasePrice();

    /**
     * Sets the base price of this ps details.
     *
     * @param basePrice the base price of this ps details
     */
    public void setBasePrice(double basePrice);

    /**
     * Returns the item ps attached date of this ps details.
     *
     * @return the item ps attached date of this ps details
     */
    public Date getItemPsAttachedDate();

    /**
     * Sets the item ps attached date of this ps details.
     *
     * @param itemPsAttachedDate the item ps attached date of this ps details
     */
    public void setItemPsAttachedDate(Date itemPsAttachedDate);

    /**
     * Returns the brand master sid of this ps details.
     *
     * @return the brand master sid of this ps details
     */
    @AutoEscape
    public String getBrandMasterSid();

    /**
     * Sets the brand master sid of this ps details.
     *
     * @param brandMasterSid the brand master sid of this ps details
     */
    public void setBrandMasterSid(String brandMasterSid);

    /**
     * Returns the status of this ps details.
     *
     * @return the status of this ps details
     */
    public int getStatus();

    /**
     * Sets the status of this ps details.
     *
     * @param status the status of this ps details
     */
    public void setStatus(int status);

    /**
     * Returns the modified date of this ps details.
     *
     * @return the modified date of this ps details
     */
    public Date getModifiedDate();

    /**
     * Sets the modified date of this ps details.
     *
     * @param modifiedDate the modified date of this ps details
     */
    public void setModifiedDate(Date modifiedDate);

    /**
     * Returns the item ps attached status of this ps details.
     *
     * @return the item ps attached status of this ps details
     */
    public int getItemPsAttachedStatus();

    /**
     * Sets the item ps attached status of this ps details.
     *
     * @param itemPsAttachedStatus the item ps attached status of this ps details
     */
    public void setItemPsAttachedStatus(int itemPsAttachedStatus);

    /**
     * Returns the revision date of this ps details.
     *
     * @return the revision date of this ps details
     */
    public Date getRevisionDate();

    /**
     * Sets the revision date of this ps details.
     *
     * @param revisionDate the revision date of this ps details
     */
    public void setRevisionDate(Date revisionDate);

    /**
     * Returns the price tolerance of this ps details.
     *
     * @return the price tolerance of this ps details
     */
    public double getPriceTolerance();

    /**
     * Sets the price tolerance of this ps details.
     *
     * @param priceTolerance the price tolerance of this ps details
     */
    public void setPriceTolerance(double priceTolerance);

    /**
     * Returns the created date of this ps details.
     *
     * @return the created date of this ps details
     */
    public Date getCreatedDate();

    /**
     * Sets the created date of this ps details.
     *
     * @param createdDate the created date of this ps details
     */
    public void setCreatedDate(Date createdDate);

    /**
     * Returns the created by of this ps details.
     *
     * @return the created by of this ps details
     */
    public int getCreatedBy();

    /**
     * Sets the created by of this ps details.
     *
     * @param createdBy the created by of this ps details
     */
    public void setCreatedBy(int createdBy);

    /**
     * Returns the source of this ps details.
     *
     * @return the source of this ps details
     */
    @AutoEscape
    public String getSource();

    /**
     * Sets the source of this ps details.
     *
     * @param source the source of this ps details
     */
    public void setSource(String source);

    /**
     * Returns the ps details sid of this ps details.
     *
     * @return the ps details sid of this ps details
     */
    public int getPsDetailsSid();

    /**
     * Sets the ps details sid of this ps details.
     *
     * @param psDetailsSid the ps details sid of this ps details
     */
    public void setPsDetailsSid(int psDetailsSid);

    /**
     * Returns the ps model sid of this ps details.
     *
     * @return the ps model sid of this ps details
     */
    public int getPsModelSid();

    /**
     * Sets the ps model sid of this ps details.
     *
     * @param psModelSid the ps model sid of this ps details
     */
    public void setPsModelSid(int psModelSid);

    /**
     * Returns the suggested price of this ps details.
     *
     * @return the suggested price of this ps details
     */
    public double getSuggestedPrice();

    /**
     * Sets the suggested price of this ps details.
     *
     * @param suggestedPrice the suggested price of this ps details
     */
    public void setSuggestedPrice(double suggestedPrice);

    /**
     * Returns the net price type formula of this ps details.
     *
     * @return the net price type formula of this ps details
     */
    @AutoEscape
    public String getNetPriceTypeFormula();

    /**
     * Sets the net price type formula of this ps details.
     *
     * @param netPriceTypeFormula the net price type formula of this ps details
     */
    public void setNetPriceTypeFormula(String netPriceTypeFormula);

    /**
     * Returns the price protection price type of this ps details.
     *
     * @return the price protection price type of this ps details
     */
    public int getPriceProtectionPriceType();

    /**
     * Sets the price protection price type of this ps details.
     *
     * @param priceProtectionPriceType the price protection price type of this ps details
     */
    public void setPriceProtectionPriceType(int priceProtectionPriceType);

    /**
     * Returns the modified by of this ps details.
     *
     * @return the modified by of this ps details
     */
    public int getModifiedBy();

    /**
     * Sets the modified by of this ps details.
     *
     * @param modifiedBy the modified by of this ps details
     */
    public void setModifiedBy(int modifiedBy);

    /**
     * Returns the inbound status of this ps details.
     *
     * @return the inbound status of this ps details
     */
    @AutoEscape
    public String getInboundStatus();

    /**
     * Sets the inbound status of this ps details.
     *
     * @param inboundStatus the inbound status of this ps details
     */
    public void setInboundStatus(String inboundStatus);

    /**
     * Returns the contract price of this ps details.
     *
     * @return the contract price of this ps details
     */
    public double getContractPrice();

    /**
     * Sets the contract price of this ps details.
     *
     * @param contractPrice the contract price of this ps details
     */
    public void setContractPrice(double contractPrice);

    /**
     * Returns the ifp model sid of this ps details.
     *
     * @return the ifp model sid of this ps details
     */
    public int getIfpModelSid();

    /**
     * Sets the ifp model sid of this ps details.
     *
     * @param ifpModelSid the ifp model sid of this ps details
     */
    public void setIfpModelSid(int ifpModelSid);

    /**
     * Returns the price tolerance type of this ps details.
     *
     * @return the price tolerance type of this ps details
     */
    public int getPriceToleranceType();

    /**
     * Sets the price tolerance type of this ps details.
     *
     * @param priceToleranceType the price tolerance type of this ps details
     */
    public void setPriceToleranceType(int priceToleranceType);

    /**
     * Returns the max incremental change of this ps details.
     *
     * @return the max incremental change of this ps details
     */
    public double getMaxIncrementalChange();

    /**
     * Sets the max incremental change of this ps details.
     *
     * @param maxIncrementalChange the max incremental change of this ps details
     */
    public void setMaxIncrementalChange(double maxIncrementalChange);

    /**
     * Returns the item pricing qualifier sid of this ps details.
     *
     * @return the item pricing qualifier sid of this ps details
     */
    public int getItemPricingQualifierSid();

    /**
     * Sets the item pricing qualifier sid of this ps details.
     *
     * @param itemPricingQualifierSid the item pricing qualifier sid of this ps details
     */
    public void setItemPricingQualifierSid(int itemPricingQualifierSid);

    /**
     * Returns the contract price end date of this ps details.
     *
     * @return the contract price end date of this ps details
     */
    public Date getContractPriceEndDate();

    /**
     * Sets the contract price end date of this ps details.
     *
     * @param contractPriceEndDate the contract price end date of this ps details
     */
    public void setContractPriceEndDate(Date contractPriceEndDate);

    /**
     * Returns the nep of this ps details.
     *
     * @return the nep of this ps details
     */
    public double getNep();

    /**
     * Sets the nep of this ps details.
     *
     * @param nep the nep of this ps details
     */
    public void setNep(double nep);

    /**
     * Returns the contract price start date of this ps details.
     *
     * @return the contract price start date of this ps details
     */
    public Date getContractPriceStartDate();

    /**
     * Sets the contract price start date of this ps details.
     *
     * @param contractPriceStartDate the contract price start date of this ps details
     */
    public void setContractPriceStartDate(Date contractPriceStartDate);

    /**
     * Returns the price tolerance frequency of this ps details.
     *
     * @return the price tolerance frequency of this ps details
     */
    public int getPriceToleranceFrequency();

    /**
     * Sets the price tolerance frequency of this ps details.
     *
     * @param priceToleranceFrequency the price tolerance frequency of this ps details
     */
    public void setPriceToleranceFrequency(int priceToleranceFrequency);

    /**
     * Returns the price protection end date of this ps details.
     *
     * @return the price protection end date of this ps details
     */
    public Date getPriceProtectionEndDate();

    /**
     * Sets the price protection end date of this ps details.
     *
     * @param priceProtectionEndDate the price protection end date of this ps details
     */
    public void setPriceProtectionEndDate(Date priceProtectionEndDate);

    /**
     * Returns the price protection status of this ps details.
     *
     * @return the price protection status of this ps details
     */
    public int getPriceProtectionStatus();

    /**
     * Sets the price protection status of this ps details.
     *
     * @param priceProtectionStatus the price protection status of this ps details
     */
    public void setPriceProtectionStatus(int priceProtectionStatus);

    /**
     * Returns the record lock status of this ps details.
     *
     * @return the record lock status of this ps details
     */
    public boolean getRecordLockStatus();

    /**
     * Returns <code>true</code> if this ps details is record lock status.
     *
     * @return <code>true</code> if this ps details is record lock status; <code>false</code> otherwise
     */
    public boolean isRecordLockStatus();

    /**
     * Sets whether this ps details is record lock status.
     *
     * @param recordLockStatus the record lock status of this ps details
     */
    public void setRecordLockStatus(boolean recordLockStatus);

    /**
     * Returns the reset eligible of this ps details.
     *
     * @return the reset eligible of this ps details
     */
    public int getResetEligible();

    /**
     * Sets the reset eligible of this ps details.
     *
     * @param resetEligible the reset eligible of this ps details
     */
    public void setResetEligible(int resetEligible);

    /**
     * Returns the batch ID of this ps details.
     *
     * @return the batch ID of this ps details
     */
    @AutoEscape
    public String getBatchId();

    /**
     * Sets the batch ID of this ps details.
     *
     * @param batchId the batch ID of this ps details
     */
    public void setBatchId(String batchId);

    /**
     * Returns the price tolerance interval of this ps details.
     *
     * @return the price tolerance interval of this ps details
     */
    public int getPriceToleranceInterval();

    /**
     * Sets the price tolerance interval of this ps details.
     *
     * @param priceToleranceInterval the price tolerance interval of this ps details
     */
    public void setPriceToleranceInterval(int priceToleranceInterval);

    /**
     * Returns the net price type of this ps details.
     *
     * @return the net price type of this ps details
     */
    public int getNetPriceType();

    /**
     * Sets the net price type of this ps details.
     *
     * @param netPriceType the net price type of this ps details
     */
    public void setNetPriceType(int netPriceType);

    /**
     * Returns the price revision of this ps details.
     *
     * @return the price revision of this ps details
     */
    public double getPriceRevision();

    /**
     * Sets the price revision of this ps details.
     *
     * @param priceRevision the price revision of this ps details
     */
    public void setPriceRevision(double priceRevision);

    /**
     * Returns the reset frequency of this ps details.
     *
     * @return the reset frequency of this ps details
     */
    public int getResetFrequency();

    /**
     * Sets the reset frequency of this ps details.
     *
     * @param resetFrequency the reset frequency of this ps details
     */
    public void setResetFrequency(int resetFrequency);

    /**
     * Returns the reset interval of this ps details.
     *
     * @return the reset interval of this ps details
     */
    public int getResetInterval();

    /**
     * Sets the reset interval of this ps details.
     *
     * @param resetInterval the reset interval of this ps details
     */
    public void setResetInterval(int resetInterval);

    /**
     * Returns the base price type of this ps details.
     *
     * @return the base price type of this ps details
     */
    public int getBasePriceType();

    /**
     * Sets the base price type of this ps details.
     *
     * @param basePriceType the base price type of this ps details
     */
    public void setBasePriceType(int basePriceType);

    /**
     * Returns the base price entry of this ps details.
     *
     * @return the base price entry of this ps details
     */
    public double getBasePriceEntry();

    /**
     * Sets the base price entry of this ps details.
     *
     * @param basePriceEntry the base price entry of this ps details
     */
    public void setBasePriceEntry(double basePriceEntry);

    /**
     * Returns the base price date of this ps details.
     *
     * @return the base price date of this ps details
     */
    public Date getBasePriceDate();

    /**
     * Sets the base price date of this ps details.
     *
     * @param basePriceDate the base price date of this ps details
     */
    public void setBasePriceDate(Date basePriceDate);

    /**
     * Returns the net base price of this ps details.
     *
     * @return the net base price of this ps details
     */
    public int getNetBasePrice();

    /**
     * Sets the net base price of this ps details.
     *
     * @param netBasePrice the net base price of this ps details
     */
    public void setNetBasePrice(int netBasePrice);

    /**
     * Returns the base price ddlb of this ps details.
     *
     * @return the base price ddlb of this ps details
     */
    public int getBasePriceDdlb();

    /**
     * Sets the base price ddlb of this ps details.
     *
     * @param basePriceDdlb the base price ddlb of this ps details
     */
    public void setBasePriceDdlb(int basePriceDdlb);

    /**
     * Returns the subsequent period price type of this ps details.
     *
     * @return the subsequent period price type of this ps details
     */
    public int getSubsequentPeriodPriceType();

    /**
     * Sets the subsequent period price type of this ps details.
     *
     * @param subsequentPeriodPriceType the subsequent period price type of this ps details
     */
    public void setSubsequentPeriodPriceType(int subsequentPeriodPriceType);

    /**
     * Returns the net subsequent period price of this ps details.
     *
     * @return the net subsequent period price of this ps details
     */
    public int getNetSubsequentPeriodPrice();

    /**
     * Sets the net subsequent period price of this ps details.
     *
     * @param netSubsequentPeriodPrice the net subsequent period price of this ps details
     */
    public void setNetSubsequentPeriodPrice(int netSubsequentPeriodPrice);

    /**
     * Returns the net subsequent price formula ID of this ps details.
     *
     * @return the net subsequent price formula ID of this ps details
     */
    public int getNetSubsequentPriceFormulaId();

    /**
     * Sets the net subsequent price formula ID of this ps details.
     *
     * @param netSubsequentPriceFormulaId the net subsequent price formula ID of this ps details
     */
    public void setNetSubsequentPriceFormulaId(int netSubsequentPriceFormulaId);

    /**
     * Returns the reset price type of this ps details.
     *
     * @return the reset price type of this ps details
     */
    public int getResetPriceType();

    /**
     * Sets the reset price type of this ps details.
     *
     * @param resetPriceType the reset price type of this ps details
     */
    public void setResetPriceType(int resetPriceType);

    /**
     * Returns the net reset price type of this ps details.
     *
     * @return the net reset price type of this ps details
     */
    public int getNetResetPriceType();

    /**
     * Sets the net reset price type of this ps details.
     *
     * @param netResetPriceType the net reset price type of this ps details
     */
    public void setNetResetPriceType(int netResetPriceType);

    /**
     * Returns the net reset price formula ID of this ps details.
     *
     * @return the net reset price formula ID of this ps details
     */
    public int getNetResetPriceFormulaId();

    /**
     * Sets the net reset price formula ID of this ps details.
     *
     * @param netResetPriceFormulaId the net reset price formula ID of this ps details
     */
    public void setNetResetPriceFormulaId(int netResetPriceFormulaId);

    /**
     * Returns the net base price formula ID of this ps details.
     *
     * @return the net base price formula ID of this ps details
     */
    public int getNetBasePriceFormulaId();

    /**
     * Sets the net base price formula ID of this ps details.
     *
     * @param netBasePriceFormulaId the net base price formula ID of this ps details
     */
    public void setNetBasePriceFormulaId(int netBasePriceFormulaId);

    @Override
    public boolean isNew();

    @Override
    public void setNew(boolean n);

    @Override
    public boolean isCachedModel();

    @Override
    public void setCachedModel(boolean cachedModel);

    @Override
    public boolean isEscapedModel();

    @Override
    public Serializable getPrimaryKeyObj();

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj);

    @Override
    public ExpandoBridge getExpandoBridge();

    @Override
    public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

    @Override
    public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

    @Override
    public void setExpandoBridgeAttributes(ServiceContext serviceContext);

    @Override
    public Object clone();

    @Override
    public int compareTo(PsDetails psDetails);

    @Override
    public int hashCode();

    @Override
    public CacheModel<PsDetails> toCacheModel();

    @Override
    public PsDetails toEscapedModel();

    @Override
    public PsDetails toUnescapedModel();

    @Override
    public String toString();

    @Override
    public String toXmlString();
}
