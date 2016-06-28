package com.stpl.app.model;

import com.stpl.portal.kernel.bean.AutoEscape;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.service.ServiceContext;

import com.stpl.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the RebatePlanMaster service. Represents a row in the &quot;REBATE_PLAN_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.RebatePlanMasterModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.RebatePlanMasterImpl}.
 * </p>
 *
 * @author
 * @see RebatePlanMaster
 * @see com.stpl.app.model.impl.RebatePlanMasterImpl
 * @see com.stpl.app.model.impl.RebatePlanMasterModelImpl
 * @generated
 */
public interface RebatePlanMasterModel extends BaseModel<RebatePlanMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a rebate plan master model instance should use the {@link RebatePlanMaster} interface instead.
     */

    /**
     * Returns the primary key of this rebate plan master.
     *
     * @return the primary key of this rebate plan master
     */
    public int getPrimaryKey();

    /**
     * Sets the primary key of this rebate plan master.
     *
     * @param primaryKey the primary key of this rebate plan master
     */
    public void setPrimaryKey(int primaryKey);

    /**
     * Returns the self growth indicator of this rebate plan master.
     *
     * @return the self growth indicator of this rebate plan master
     */
    @AutoEscape
    public String getSelfGrowthIndicator();

    /**
     * Sets the self growth indicator of this rebate plan master.
     *
     * @param selfGrowthIndicator the self growth indicator of this rebate plan master
     */
    public void setSelfGrowthIndicator(String selfGrowthIndicator);

    /**
     * Returns the rebate structure of this rebate plan master.
     *
     * @return the rebate structure of this rebate plan master
     */
    @AutoEscape
    public String getRebateStructure();

    /**
     * Sets the rebate structure of this rebate plan master.
     *
     * @param rebateStructure the rebate structure of this rebate plan master
     */
    public void setRebateStructure(String rebateStructure);

    /**
     * Returns the market share from of this rebate plan master.
     *
     * @return the market share from of this rebate plan master
     */
    public Date getMarketShareFrom();

    /**
     * Sets the market share from of this rebate plan master.
     *
     * @param marketShareFrom the market share from of this rebate plan master
     */
    public void setMarketShareFrom(Date marketShareFrom);

    /**
     * Returns the secondary rebate plan no of this rebate plan master.
     *
     * @return the secondary rebate plan no of this rebate plan master
     */
    @AutoEscape
    public String getSecondaryRebatePlanNo();

    /**
     * Sets the secondary rebate plan no of this rebate plan master.
     *
     * @param secondaryRebatePlanNo the secondary rebate plan no of this rebate plan master
     */
    public void setSecondaryRebatePlanNo(String secondaryRebatePlanNo);

    /**
     * Returns the modified date of this rebate plan master.
     *
     * @return the modified date of this rebate plan master
     */
    public Date getModifiedDate();

    /**
     * Sets the modified date of this rebate plan master.
     *
     * @param modifiedDate the modified date of this rebate plan master
     */
    public void setModifiedDate(Date modifiedDate);

    /**
     * Returns the rebate range based on of this rebate plan master.
     *
     * @return the rebate range based on of this rebate plan master
     */
    public int getRebateRangeBasedOn();

    /**
     * Sets the rebate range based on of this rebate plan master.
     *
     * @param rebateRangeBasedOn the rebate range based on of this rebate plan master
     */
    public void setRebateRangeBasedOn(int rebateRangeBasedOn);

    /**
     * Returns the cdr model sid of this rebate plan master.
     *
     * @return the cdr model sid of this rebate plan master
     */
    @AutoEscape
    public String getCdrModelSid();

    /**
     * Sets the cdr model sid of this rebate plan master.
     *
     * @param cdrModelSid the cdr model sid of this rebate plan master
     */
    public void setCdrModelSid(String cdrModelSid);

    /**
     * Returns the rebate rule of this rebate plan master.
     *
     * @return the rebate rule of this rebate plan master
     */
    @AutoEscape
    public String getRebateRule();

    /**
     * Sets the rebate rule of this rebate plan master.
     *
     * @param rebateRule the rebate rule of this rebate plan master
     */
    public void setRebateRule(String rebateRule);

    /**
     * Returns the created date of this rebate plan master.
     *
     * @return the created date of this rebate plan master
     */
    public Date getCreatedDate();

    /**
     * Sets the created date of this rebate plan master.
     *
     * @param createdDate the created date of this rebate plan master
     */
    public void setCreatedDate(Date createdDate);

    /**
     * Returns the created by of this rebate plan master.
     *
     * @return the created by of this rebate plan master
     */
    public int getCreatedBy();

    /**
     * Sets the created by of this rebate plan master.
     *
     * @param createdBy the created by of this rebate plan master
     */
    public void setCreatedBy(int createdBy);

    /**
     * Returns the source of this rebate plan master.
     *
     * @return the source of this rebate plan master
     */
    @AutoEscape
    public String getSource();

    /**
     * Sets the source of this rebate plan master.
     *
     * @param source the source of this rebate plan master
     */
    public void setSource(String source);

    /**
     * Returns the rebate based on of this rebate plan master.
     *
     * @return the rebate based on of this rebate plan master
     */
    public int getRebateBasedOn();

    /**
     * Sets the rebate based on of this rebate plan master.
     *
     * @param rebateBasedOn the rebate based on of this rebate plan master
     */
    public void setRebateBasedOn(int rebateBasedOn);

    /**
     * Returns the rebate plan type of this rebate plan master.
     *
     * @return the rebate plan type of this rebate plan master
     */
    public int getRebatePlanType();

    /**
     * Sets the rebate plan type of this rebate plan master.
     *
     * @param rebatePlanType the rebate plan type of this rebate plan master
     */
    public void setRebatePlanType(int rebatePlanType);

    /**
     * Returns the rebate plan ID of this rebate plan master.
     *
     * @return the rebate plan ID of this rebate plan master
     */
    @AutoEscape
    public String getRebatePlanId();

    /**
     * Sets the rebate plan ID of this rebate plan master.
     *
     * @param rebatePlanId the rebate plan ID of this rebate plan master
     */
    public void setRebatePlanId(String rebatePlanId);

    /**
     * Returns the manf company master sid of this rebate plan master.
     *
     * @return the manf company master sid of this rebate plan master
     */
    public int getManfCompanyMasterSid();

    /**
     * Sets the manf company master sid of this rebate plan master.
     *
     * @param manfCompanyMasterSid the manf company master sid of this rebate plan master
     */
    public void setManfCompanyMasterSid(int manfCompanyMasterSid);

    /**
     * Returns the modified by of this rebate plan master.
     *
     * @return the modified by of this rebate plan master
     */
    public int getModifiedBy();

    /**
     * Sets the modified by of this rebate plan master.
     *
     * @param modifiedBy the modified by of this rebate plan master
     */
    public void setModifiedBy(int modifiedBy);

    /**
     * Returns the inbound status of this rebate plan master.
     *
     * @return the inbound status of this rebate plan master
     */
    @AutoEscape
    public String getInboundStatus();

    /**
     * Sets the inbound status of this rebate plan master.
     *
     * @param inboundStatus the inbound status of this rebate plan master
     */
    public void setInboundStatus(String inboundStatus);

    /**
     * Returns the secondary rebate plan ID of this rebate plan master.
     *
     * @return the secondary rebate plan ID of this rebate plan master
     */
    @AutoEscape
    public String getSecondaryRebatePlanId();

    /**
     * Sets the secondary rebate plan ID of this rebate plan master.
     *
     * @param secondaryRebatePlanId the secondary rebate plan ID of this rebate plan master
     */
    public void setSecondaryRebatePlanId(String secondaryRebatePlanId);

    /**
     * Returns the market share indicator of this rebate plan master.
     *
     * @return the market share indicator of this rebate plan master
     */
    @AutoEscape
    public String getMarketShareIndicator();

    /**
     * Sets the market share indicator of this rebate plan master.
     *
     * @param marketShareIndicator the market share indicator of this rebate plan master
     */
    public void setMarketShareIndicator(String marketShareIndicator);

    /**
     * Returns the bogo eligible of this rebate plan master.
     *
     * @return the bogo eligible of this rebate plan master
     */
    @AutoEscape
    public String getBogoEligible();

    /**
     * Sets the bogo eligible of this rebate plan master.
     *
     * @param bogoEligible the bogo eligible of this rebate plan master
     */
    public void setBogoEligible(String bogoEligible);

    /**
     * Returns the market share to of this rebate plan master.
     *
     * @return the market share to of this rebate plan master
     */
    public Date getMarketShareTo();

    /**
     * Sets the market share to of this rebate plan master.
     *
     * @param marketShareTo the market share to of this rebate plan master
     */
    public void setMarketShareTo(Date marketShareTo);

    /**
     * Returns the rebate plan status of this rebate plan master.
     *
     * @return the rebate plan status of this rebate plan master
     */
    public int getRebatePlanStatus();

    /**
     * Sets the rebate plan status of this rebate plan master.
     *
     * @param rebatePlanStatus the rebate plan status of this rebate plan master
     */
    public void setRebatePlanStatus(int rebatePlanStatus);

    /**
     * Returns the rebate plan master sid of this rebate plan master.
     *
     * @return the rebate plan master sid of this rebate plan master
     */
    public int getRebatePlanMasterSid();

    /**
     * Sets the rebate plan master sid of this rebate plan master.
     *
     * @param rebatePlanMasterSid the rebate plan master sid of this rebate plan master
     */
    public void setRebatePlanMasterSid(int rebatePlanMasterSid);

    /**
     * Returns the market share reference of this rebate plan master.
     *
     * @return the market share reference of this rebate plan master
     */
    @AutoEscape
    public String getMarketShareReference();

    /**
     * Sets the market share reference of this rebate plan master.
     *
     * @param marketShareReference the market share reference of this rebate plan master
     */
    public void setMarketShareReference(String marketShareReference);

    /**
     * Returns the net sales formula master sid of this rebate plan master.
     *
     * @return the net sales formula master sid of this rebate plan master
     */
    @AutoEscape
    public String getNetSalesFormulaMasterSid();

    /**
     * Sets the net sales formula master sid of this rebate plan master.
     *
     * @param netSalesFormulaMasterSid the net sales formula master sid of this rebate plan master
     */
    public void setNetSalesFormulaMasterSid(String netSalesFormulaMasterSid);

    /**
     * Returns the self growth from of this rebate plan master.
     *
     * @return the self growth from of this rebate plan master
     */
    public Date getSelfGrowthFrom();

    /**
     * Sets the self growth from of this rebate plan master.
     *
     * @param selfGrowthFrom the self growth from of this rebate plan master
     */
    public void setSelfGrowthFrom(Date selfGrowthFrom);

    /**
     * Returns the internal notes of this rebate plan master.
     *
     * @return the internal notes of this rebate plan master
     */
    @AutoEscape
    public String getInternalNotes();

    /**
     * Sets the internal notes of this rebate plan master.
     *
     * @param internalNotes the internal notes of this rebate plan master
     */
    public void setInternalNotes(String internalNotes);

    /**
     * Returns the secondary rebate plan name of this rebate plan master.
     *
     * @return the secondary rebate plan name of this rebate plan master
     */
    @AutoEscape
    public String getSecondaryRebatePlanName();

    /**
     * Sets the secondary rebate plan name of this rebate plan master.
     *
     * @param secondaryRebatePlanName the secondary rebate plan name of this rebate plan master
     */
    public void setSecondaryRebatePlanName(String secondaryRebatePlanName);

    /**
     * Returns the record lock status of this rebate plan master.
     *
     * @return the record lock status of this rebate plan master
     */
    public boolean getRecordLockStatus();

    /**
     * Returns <code>true</code> if this rebate plan master is record lock status.
     *
     * @return <code>true</code> if this rebate plan master is record lock status; <code>false</code> otherwise
     */
    public boolean isRecordLockStatus();

    /**
     * Sets whether this rebate plan master is record lock status.
     *
     * @param recordLockStatus the record lock status of this rebate plan master
     */
    public void setRecordLockStatus(boolean recordLockStatus);

    /**
     * Returns the rebate plan name of this rebate plan master.
     *
     * @return the rebate plan name of this rebate plan master
     */
    @AutoEscape
    public String getRebatePlanName();

    /**
     * Sets the rebate plan name of this rebate plan master.
     *
     * @param rebatePlanName the rebate plan name of this rebate plan master
     */
    public void setRebatePlanName(String rebatePlanName);

    /**
     * Returns the self growth reference of this rebate plan master.
     *
     * @return the self growth reference of this rebate plan master
     */
    @AutoEscape
    public String getSelfGrowthReference();

    /**
     * Sets the self growth reference of this rebate plan master.
     *
     * @param selfGrowthReference the self growth reference of this rebate plan master
     */
    public void setSelfGrowthReference(String selfGrowthReference);

    /**
     * Returns the batch ID of this rebate plan master.
     *
     * @return the batch ID of this rebate plan master
     */
    @AutoEscape
    public String getBatchId();

    /**
     * Sets the batch ID of this rebate plan master.
     *
     * @param batchId the batch ID of this rebate plan master
     */
    public void setBatchId(String batchId);

    /**
     * Returns the formula type of this rebate plan master.
     *
     * @return the formula type of this rebate plan master
     */
    public int getFormulaType();

    /**
     * Sets the formula type of this rebate plan master.
     *
     * @param formulaType the formula type of this rebate plan master
     */
    public void setFormulaType(int formulaType);

    /**
     * Returns the self growth to of this rebate plan master.
     *
     * @return the self growth to of this rebate plan master
     */
    public Date getSelfGrowthTo();

    /**
     * Sets the self growth to of this rebate plan master.
     *
     * @param selfGrowthTo the self growth to of this rebate plan master
     */
    public void setSelfGrowthTo(Date selfGrowthTo);

    /**
     * Returns the rebate plan no of this rebate plan master.
     *
     * @return the rebate plan no of this rebate plan master
     */
    @AutoEscape
    public String getRebatePlanNo();

    /**
     * Sets the rebate plan no of this rebate plan master.
     *
     * @param rebatePlanNo the rebate plan no of this rebate plan master
     */
    public void setRebatePlanNo(String rebatePlanNo);

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
    public int compareTo(RebatePlanMaster rebatePlanMaster);

    @Override
    public int hashCode();

    @Override
    public CacheModel<RebatePlanMaster> toCacheModel();

    @Override
    public RebatePlanMaster toEscapedModel();

    @Override
    public RebatePlanMaster toUnescapedModel();

    @Override
    public String toString();

    @Override
    public String toXmlString();
}
