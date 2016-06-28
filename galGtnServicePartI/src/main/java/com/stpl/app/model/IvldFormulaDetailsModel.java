package com.stpl.app.model;

import com.stpl.portal.kernel.bean.AutoEscape;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.service.ServiceContext;

import com.stpl.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the IvldFormulaDetails service. Represents a row in the &quot;IVLD_FORMULA_DETAILS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.IvldFormulaDetailsModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.IvldFormulaDetailsImpl}.
 * </p>
 *
 * @author
 * @see IvldFormulaDetails
 * @see com.stpl.app.model.impl.IvldFormulaDetailsImpl
 * @see com.stpl.app.model.impl.IvldFormulaDetailsModelImpl
 * @generated
 */
public interface IvldFormulaDetailsModel extends BaseModel<IvldFormulaDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a ivld formula details model instance should use the {@link IvldFormulaDetails} interface instead.
     */

    /**
     * Returns the primary key of this ivld formula details.
     *
     * @return the primary key of this ivld formula details
     */
    public int getPrimaryKey();

    /**
     * Sets the primary key of this ivld formula details.
     *
     * @param primaryKey the primary key of this ivld formula details
     */
    public void setPrimaryKey(int primaryKey);

    /**
     * Returns the end date of this ivld formula details.
     *
     * @return the end date of this ivld formula details
     */
    @AutoEscape
    public String getEndDate();

    /**
     * Sets the end date of this ivld formula details.
     *
     * @param endDate the end date of this ivld formula details
     */
    public void setEndDate(String endDate);

    /**
     * Returns the rebate percent1 of this ivld formula details.
     *
     * @return the rebate percent1 of this ivld formula details
     */
    @AutoEscape
    public String getRebatePercent1();

    /**
     * Sets the rebate percent1 of this ivld formula details.
     *
     * @param rebatePercent1 the rebate percent1 of this ivld formula details
     */
    public void setRebatePercent1(String rebatePercent1);

    /**
     * Returns the item ID of this ivld formula details.
     *
     * @return the item ID of this ivld formula details
     */
    @AutoEscape
    public String getItemId();

    /**
     * Sets the item ID of this ivld formula details.
     *
     * @param itemId the item ID of this ivld formula details
     */
    public void setItemId(String itemId);

    /**
     * Returns the rebate percent2 of this ivld formula details.
     *
     * @return the rebate percent2 of this ivld formula details
     */
    @AutoEscape
    public String getRebatePercent2();

    /**
     * Sets the rebate percent2 of this ivld formula details.
     *
     * @param rebatePercent2 the rebate percent2 of this ivld formula details
     */
    public void setRebatePercent2(String rebatePercent2);

    /**
     * Returns the formula desc of this ivld formula details.
     *
     * @return the formula desc of this ivld formula details
     */
    @AutoEscape
    public String getFormulaDesc();

    /**
     * Sets the formula desc of this ivld formula details.
     *
     * @param formulaDesc the formula desc of this ivld formula details
     */
    public void setFormulaDesc(String formulaDesc);

    /**
     * Returns the modified date of this ivld formula details.
     *
     * @return the modified date of this ivld formula details
     */
    public Date getModifiedDate();

    /**
     * Sets the modified date of this ivld formula details.
     *
     * @param modifiedDate the modified date of this ivld formula details
     */
    public void setModifiedDate(Date modifiedDate);

    /**
     * Returns the rebate percent3 of this ivld formula details.
     *
     * @return the rebate percent3 of this ivld formula details
     */
    @AutoEscape
    public String getRebatePercent3();

    /**
     * Sets the rebate percent3 of this ivld formula details.
     *
     * @param rebatePercent3 the rebate percent3 of this ivld formula details
     */
    public void setRebatePercent3(String rebatePercent3);

    /**
     * Returns the created by of this ivld formula details.
     *
     * @return the created by of this ivld formula details
     */
    @AutoEscape
    public String getCreatedBy();

    /**
     * Sets the created by of this ivld formula details.
     *
     * @param createdBy the created by of this ivld formula details
     */
    public void setCreatedBy(String createdBy);

    /**
     * Returns the created date of this ivld formula details.
     *
     * @return the created date of this ivld formula details
     */
    public Date getCreatedDate();

    /**
     * Sets the created date of this ivld formula details.
     *
     * @param createdDate the created date of this ivld formula details
     */
    public void setCreatedDate(Date createdDate);

    /**
     * Returns the source of this ivld formula details.
     *
     * @return the source of this ivld formula details
     */
    @AutoEscape
    public String getSource();

    /**
     * Sets the source of this ivld formula details.
     *
     * @param source the source of this ivld formula details
     */
    public void setSource(String source);

    /**
     * Returns the add chg del indicator of this ivld formula details.
     *
     * @return the add chg del indicator of this ivld formula details
     */
    @AutoEscape
    public String getAddChgDelIndicator();

    /**
     * Sets the add chg del indicator of this ivld formula details.
     *
     * @param addChgDelIndicator the add chg del indicator of this ivld formula details
     */
    public void setAddChgDelIndicator(String addChgDelIndicator);

    /**
     * Returns the error code of this ivld formula details.
     *
     * @return the error code of this ivld formula details
     */
    @AutoEscape
    public String getErrorCode();

    /**
     * Sets the error code of this ivld formula details.
     *
     * @param errorCode the error code of this ivld formula details
     */
    public void setErrorCode(String errorCode);

    /**
     * Returns the formula ID of this ivld formula details.
     *
     * @return the formula ID of this ivld formula details
     */
    @AutoEscape
    public String getFormulaId();

    /**
     * Sets the formula ID of this ivld formula details.
     *
     * @param formulaId the formula ID of this ivld formula details
     */
    public void setFormulaId(String formulaId);

    /**
     * Returns the modified by of this ivld formula details.
     *
     * @return the modified by of this ivld formula details
     */
    @AutoEscape
    public String getModifiedBy();

    /**
     * Sets the modified by of this ivld formula details.
     *
     * @param modifiedBy the modified by of this ivld formula details
     */
    public void setModifiedBy(String modifiedBy);

    /**
     * Returns the intf inserted date of this ivld formula details.
     *
     * @return the intf inserted date of this ivld formula details
     */
    public Date getIntfInsertedDate();

    /**
     * Sets the intf inserted date of this ivld formula details.
     *
     * @param intfInsertedDate the intf inserted date of this ivld formula details
     */
    public void setIntfInsertedDate(Date intfInsertedDate);

    /**
     * Returns the reprocessed flag of this ivld formula details.
     *
     * @return the reprocessed flag of this ivld formula details
     */
    @AutoEscape
    public String getReprocessedFlag();

    /**
     * Sets the reprocessed flag of this ivld formula details.
     *
     * @param reprocessedFlag the reprocessed flag of this ivld formula details
     */
    public void setReprocessedFlag(String reprocessedFlag);

    /**
     * Returns the formula details intfid of this ivld formula details.
     *
     * @return the formula details intfid of this ivld formula details
     */
    @AutoEscape
    public String getFormulaDetailsIntfid();

    /**
     * Sets the formula details intfid of this ivld formula details.
     *
     * @param formulaDetailsIntfid the formula details intfid of this ivld formula details
     */
    public void setFormulaDetailsIntfid(String formulaDetailsIntfid);

    /**
     * Returns the reason for failure of this ivld formula details.
     *
     * @return the reason for failure of this ivld formula details
     */
    @AutoEscape
    public String getReasonForFailure();

    /**
     * Sets the reason for failure of this ivld formula details.
     *
     * @param reasonForFailure the reason for failure of this ivld formula details
     */
    public void setReasonForFailure(String reasonForFailure);

    /**
     * Returns the contract price1 of this ivld formula details.
     *
     * @return the contract price1 of this ivld formula details
     */
    @AutoEscape
    public String getContractPrice1();

    /**
     * Sets the contract price1 of this ivld formula details.
     *
     * @param contractPrice1 the contract price1 of this ivld formula details
     */
    public void setContractPrice1(String contractPrice1);

    /**
     * Returns the company ID of this ivld formula details.
     *
     * @return the company ID of this ivld formula details
     */
    @AutoEscape
    public String getCompanyId();

    /**
     * Sets the company ID of this ivld formula details.
     *
     * @param companyId the company ID of this ivld formula details
     */
    public void setCompanyId(String companyId);

    /**
     * Returns the contract price2 of this ivld formula details.
     *
     * @return the contract price2 of this ivld formula details
     */
    @AutoEscape
    public String getContractPrice2();

    /**
     * Sets the contract price2 of this ivld formula details.
     *
     * @param contractPrice2 the contract price2 of this ivld formula details
     */
    public void setContractPrice2(String contractPrice2);

    /**
     * Returns the formula no of this ivld formula details.
     *
     * @return the formula no of this ivld formula details
     */
    @AutoEscape
    public String getFormulaNo();

    /**
     * Sets the formula no of this ivld formula details.
     *
     * @param formulaNo the formula no of this ivld formula details
     */
    public void setFormulaNo(String formulaNo);

    /**
     * Returns the start date of this ivld formula details.
     *
     * @return the start date of this ivld formula details
     */
    @AutoEscape
    public String getStartDate();

    /**
     * Sets the start date of this ivld formula details.
     *
     * @param startDate the start date of this ivld formula details
     */
    public void setStartDate(String startDate);

    /**
     * Returns the batch ID of this ivld formula details.
     *
     * @return the batch ID of this ivld formula details
     */
    @AutoEscape
    public String getBatchId();

    /**
     * Sets the batch ID of this ivld formula details.
     *
     * @param batchId the batch ID of this ivld formula details
     */
    public void setBatchId(String batchId);

    /**
     * Returns the error field of this ivld formula details.
     *
     * @return the error field of this ivld formula details
     */
    @AutoEscape
    public String getErrorField();

    /**
     * Sets the error field of this ivld formula details.
     *
     * @param errorField the error field of this ivld formula details
     */
    public void setErrorField(String errorField);

    /**
     * Returns the contract price3 of this ivld formula details.
     *
     * @return the contract price3 of this ivld formula details
     */
    @AutoEscape
    public String getContractPrice3();

    /**
     * Sets the contract price3 of this ivld formula details.
     *
     * @param contractPrice3 the contract price3 of this ivld formula details
     */
    public void setContractPrice3(String contractPrice3);

    /**
     * Returns the ivld formula details sid of this ivld formula details.
     *
     * @return the ivld formula details sid of this ivld formula details
     */
    public int getIvldFormulaDetailsSid();

    /**
     * Sets the ivld formula details sid of this ivld formula details.
     *
     * @param ivldFormulaDetailsSid the ivld formula details sid of this ivld formula details
     */
    public void setIvldFormulaDetailsSid(int ivldFormulaDetailsSid);

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
    public int compareTo(IvldFormulaDetails ivldFormulaDetails);

    @Override
    public int hashCode();

    @Override
    public CacheModel<IvldFormulaDetails> toCacheModel();

    @Override
    public IvldFormulaDetails toEscapedModel();

    @Override
    public IvldFormulaDetails toUnescapedModel();

    @Override
    public String toString();

    @Override
    public String toXmlString();
}
