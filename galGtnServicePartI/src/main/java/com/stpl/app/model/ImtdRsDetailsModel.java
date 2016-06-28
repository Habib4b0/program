package com.stpl.app.model;

import com.stpl.portal.kernel.bean.AutoEscape;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.service.ServiceContext;

import com.stpl.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the ImtdRsDetails service. Represents a row in the &quot;IMTD_RS_DETAILS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.ImtdRsDetailsModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.ImtdRsDetailsImpl}.
 * </p>
 *
 * @author
 * @see ImtdRsDetails
 * @see com.stpl.app.model.impl.ImtdRsDetailsImpl
 * @see com.stpl.app.model.impl.ImtdRsDetailsModelImpl
 * @generated
 */
public interface ImtdRsDetailsModel extends BaseModel<ImtdRsDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a imtd rs details model instance should use the {@link ImtdRsDetails} interface instead.
     */

    /**
     * Returns the primary key of this imtd rs details.
     *
     * @return the primary key of this imtd rs details
     */
    public int getPrimaryKey();

    /**
     * Sets the primary key of this imtd rs details.
     *
     * @param primaryKey the primary key of this imtd rs details
     */
    public void setPrimaryKey(int primaryKey);

    /**
     * Returns the rs details modified date of this imtd rs details.
     *
     * @return the rs details modified date of this imtd rs details
     */
    public Date getRsDetailsModifiedDate();

    /**
     * Sets the rs details modified date of this imtd rs details.
     *
     * @param rsDetailsModifiedDate the rs details modified date of this imtd rs details
     */
    public void setRsDetailsModifiedDate(Date rsDetailsModifiedDate);

    /**
     * Returns the rs details bundle no of this imtd rs details.
     *
     * @return the rs details bundle no of this imtd rs details
     */
    @AutoEscape
    public String getRsDetailsBundleNo();

    /**
     * Sets the rs details bundle no of this imtd rs details.
     *
     * @param rsDetailsBundleNo the rs details bundle no of this imtd rs details
     */
    public void setRsDetailsBundleNo(String rsDetailsBundleNo);

    /**
     * Returns the item master sid of this imtd rs details.
     *
     * @return the item master sid of this imtd rs details
     */
    public int getItemMasterSid();

    /**
     * Sets the item master sid of this imtd rs details.
     *
     * @param itemMasterSid the item master sid of this imtd rs details
     */
    public void setItemMasterSid(int itemMasterSid);

    /**
     * Returns the imtd rs details sid of this imtd rs details.
     *
     * @return the imtd rs details sid of this imtd rs details
     */
    public int getImtdRsDetailsSid();

    /**
     * Sets the imtd rs details sid of this imtd rs details.
     *
     * @param imtdRsDetailsSid the imtd rs details sid of this imtd rs details
     */
    public void setImtdRsDetailsSid(int imtdRsDetailsSid);

    /**
     * Returns the item ID of this imtd rs details.
     *
     * @return the item ID of this imtd rs details
     */
    @AutoEscape
    public String getItemId();

    /**
     * Sets the item ID of this imtd rs details.
     *
     * @param itemId the item ID of this imtd rs details
     */
    public void setItemId(String itemId);

    /**
     * Returns the rs details formula method ID of this imtd rs details.
     *
     * @return the rs details formula method ID of this imtd rs details
     */
    @AutoEscape
    public String getRsDetailsFormulaMethodId();

    /**
     * Sets the rs details formula method ID of this imtd rs details.
     *
     * @param rsDetailsFormulaMethodId the rs details formula method ID of this imtd rs details
     */
    public void setRsDetailsFormulaMethodId(String rsDetailsFormulaMethodId);

    /**
     * Returns the modified date of this imtd rs details.
     *
     * @return the modified date of this imtd rs details
     */
    public Date getModifiedDate();

    /**
     * Sets the modified date of this imtd rs details.
     *
     * @param modifiedDate the modified date of this imtd rs details
     */
    public void setModifiedDate(Date modifiedDate);

    /**
     * Returns the created date of this imtd rs details.
     *
     * @return the created date of this imtd rs details
     */
    public Date getCreatedDate();

    /**
     * Sets the created date of this imtd rs details.
     *
     * @param createdDate the created date of this imtd rs details
     */
    public void setCreatedDate(Date createdDate);

    /**
     * Returns the created by of this imtd rs details.
     *
     * @return the created by of this imtd rs details
     */
    public int getCreatedBy();

    /**
     * Sets the created by of this imtd rs details.
     *
     * @param createdBy the created by of this imtd rs details
     */
    public void setCreatedBy(int createdBy);

    /**
     * Returns the users sid of this imtd rs details.
     *
     * @return the users sid of this imtd rs details
     */
    @AutoEscape
    public String getUsersSid();

    /**
     * Sets the users sid of this imtd rs details.
     *
     * @param usersSid the users sid of this imtd rs details
     */
    public void setUsersSid(String usersSid);

    /**
     * Returns the contract master sid of this imtd rs details.
     *
     * @return the contract master sid of this imtd rs details
     */
    public int getContractMasterSid();

    /**
     * Sets the contract master sid of this imtd rs details.
     *
     * @param contractMasterSid the contract master sid of this imtd rs details
     */
    public void setContractMasterSid(int contractMasterSid);

    /**
     * Returns the rs details formula ID of this imtd rs details.
     *
     * @return the rs details formula ID of this imtd rs details
     */
    @AutoEscape
    public String getRsDetailsFormulaId();

    /**
     * Sets the rs details formula ID of this imtd rs details.
     *
     * @param rsDetailsFormulaId the rs details formula ID of this imtd rs details
     */
    public void setRsDetailsFormulaId(String rsDetailsFormulaId);

    /**
     * Returns the imtd created date of this imtd rs details.
     *
     * @return the imtd created date of this imtd rs details
     */
    public Date getImtdCreatedDate();

    /**
     * Sets the imtd created date of this imtd rs details.
     *
     * @param imtdCreatedDate the imtd created date of this imtd rs details
     */
    public void setImtdCreatedDate(Date imtdCreatedDate);

    /**
     * Returns the ps model sid of this imtd rs details.
     *
     * @return the ps model sid of this imtd rs details
     */
    public int getPsModelSid();

    /**
     * Sets the ps model sid of this imtd rs details.
     *
     * @param psModelSid the ps model sid of this imtd rs details
     */
    public void setPsModelSid(int psModelSid);

    /**
     * Returns the modified by of this imtd rs details.
     *
     * @return the modified by of this imtd rs details
     */
    public int getModifiedBy();

    /**
     * Sets the modified by of this imtd rs details.
     *
     * @param modifiedBy the modified by of this imtd rs details
     */
    public void setModifiedBy(int modifiedBy);

    /**
     * Returns the rs details created date of this imtd rs details.
     *
     * @return the rs details created date of this imtd rs details
     */
    public Date getRsDetailsCreatedDate();

    /**
     * Sets the rs details created date of this imtd rs details.
     *
     * @param rsDetailsCreatedDate the rs details created date of this imtd rs details
     */
    public void setRsDetailsCreatedDate(Date rsDetailsCreatedDate);

    /**
     * Returns the item no of this imtd rs details.
     *
     * @return the item no of this imtd rs details
     */
    @AutoEscape
    public String getItemNo();

    /**
     * Sets the item no of this imtd rs details.
     *
     * @param itemNo the item no of this imtd rs details
     */
    public void setItemNo(String itemNo);

    /**
     * Returns the rs details formula name of this imtd rs details.
     *
     * @return the rs details formula name of this imtd rs details
     */
    @AutoEscape
    public String getRsDetailsFormulaName();

    /**
     * Sets the rs details formula name of this imtd rs details.
     *
     * @param rsDetailsFormulaName the rs details formula name of this imtd rs details
     */
    public void setRsDetailsFormulaName(String rsDetailsFormulaName);

    /**
     * Returns the udc6 of this imtd rs details.
     *
     * @return the udc6 of this imtd rs details
     */
    @AutoEscape
    public String getUdc6();

    /**
     * Sets the udc6 of this imtd rs details.
     *
     * @param udc6 the udc6 of this imtd rs details
     */
    public void setUdc6(String udc6);

    /**
     * Returns the rs details created by of this imtd rs details.
     *
     * @return the rs details created by of this imtd rs details
     */
    @AutoEscape
    public String getRsDetailsCreatedBy();

    /**
     * Sets the rs details created by of this imtd rs details.
     *
     * @param rsDetailsCreatedBy the rs details created by of this imtd rs details
     */
    public void setRsDetailsCreatedBy(String rsDetailsCreatedBy);

    /**
     * Returns the udc5 of this imtd rs details.
     *
     * @return the udc5 of this imtd rs details
     */
    @AutoEscape
    public String getUdc5();

    /**
     * Sets the udc5 of this imtd rs details.
     *
     * @param udc5 the udc5 of this imtd rs details
     */
    public void setUdc5(String udc5);

    /**
     * Returns the ifp model sid of this imtd rs details.
     *
     * @return the ifp model sid of this imtd rs details
     */
    public int getIfpModelSid();

    /**
     * Sets the ifp model sid of this imtd rs details.
     *
     * @param ifpModelSid the ifp model sid of this imtd rs details
     */
    public void setIfpModelSid(int ifpModelSid);

    /**
     * Returns the udc4 of this imtd rs details.
     *
     * @return the udc4 of this imtd rs details
     */
    @AutoEscape
    public String getUdc4();

    /**
     * Sets the udc4 of this imtd rs details.
     *
     * @param udc4 the udc4 of this imtd rs details
     */
    public void setUdc4(String udc4);

    /**
     * Returns the rs details formula no of this imtd rs details.
     *
     * @return the rs details formula no of this imtd rs details
     */
    @AutoEscape
    public String getRsDetailsFormulaNo();

    /**
     * Sets the rs details formula no of this imtd rs details.
     *
     * @param rsDetailsFormulaNo the rs details formula no of this imtd rs details
     */
    public void setRsDetailsFormulaNo(String rsDetailsFormulaNo);

    /**
     * Returns the check record of this imtd rs details.
     *
     * @return the check record of this imtd rs details
     */
    public boolean getCheckRecord();

    /**
     * Returns <code>true</code> if this imtd rs details is check record.
     *
     * @return <code>true</code> if this imtd rs details is check record; <code>false</code> otherwise
     */
    public boolean isCheckRecord();

    /**
     * Sets whether this imtd rs details is check record.
     *
     * @param checkRecord the check record of this imtd rs details
     */
    public void setCheckRecord(boolean checkRecord);

    /**
     * Returns the rs ID of this imtd rs details.
     *
     * @return the rs ID of this imtd rs details
     */
    @AutoEscape
    public String getRsId();

    /**
     * Sets the rs ID of this imtd rs details.
     *
     * @param rsId the rs ID of this imtd rs details
     */
    public void setRsId(String rsId);

    /**
     * Returns the udc1 of this imtd rs details.
     *
     * @return the udc1 of this imtd rs details
     */
    @AutoEscape
    public String getUdc1();

    /**
     * Sets the udc1 of this imtd rs details.
     *
     * @param udc1 the udc1 of this imtd rs details
     */
    public void setUdc1(String udc1);

    /**
     * Returns the rs details rebate amount of this imtd rs details.
     *
     * @return the rs details rebate amount of this imtd rs details
     */
    public double getRsDetailsRebateAmount();

    /**
     * Sets the rs details rebate amount of this imtd rs details.
     *
     * @param rsDetailsRebateAmount the rs details rebate amount of this imtd rs details
     */
    public void setRsDetailsRebateAmount(double rsDetailsRebateAmount);

    /**
     * Returns the udc2 of this imtd rs details.
     *
     * @return the udc2 of this imtd rs details
     */
    @AutoEscape
    public String getUdc2();

    /**
     * Sets the udc2 of this imtd rs details.
     *
     * @param udc2 the udc2 of this imtd rs details
     */
    public void setUdc2(String udc2);

    /**
     * Returns the rs details modified by of this imtd rs details.
     *
     * @return the rs details modified by of this imtd rs details
     */
    @AutoEscape
    public String getRsDetailsModifiedBy();

    /**
     * Sets the rs details modified by of this imtd rs details.
     *
     * @param rsDetailsModifiedBy the rs details modified by of this imtd rs details
     */
    public void setRsDetailsModifiedBy(String rsDetailsModifiedBy);

    /**
     * Returns the udc3 of this imtd rs details.
     *
     * @return the udc3 of this imtd rs details
     */
    @AutoEscape
    public String getUdc3();

    /**
     * Sets the udc3 of this imtd rs details.
     *
     * @param udc3 the udc3 of this imtd rs details
     */
    public void setUdc3(String udc3);

    /**
     * Returns the rebate plan master sid of this imtd rs details.
     *
     * @return the rebate plan master sid of this imtd rs details
     */
    @AutoEscape
    public String getRebatePlanMasterSid();

    /**
     * Sets the rebate plan master sid of this imtd rs details.
     *
     * @param rebatePlanMasterSid the rebate plan master sid of this imtd rs details
     */
    public void setRebatePlanMasterSid(String rebatePlanMasterSid);

    /**
     * Returns the rs details attached date of this imtd rs details.
     *
     * @return the rs details attached date of this imtd rs details
     */
    public Date getRsDetailsAttachedDate();

    /**
     * Sets the rs details attached date of this imtd rs details.
     *
     * @param rsDetailsAttachedDate the rs details attached date of this imtd rs details
     */
    public void setRsDetailsAttachedDate(Date rsDetailsAttachedDate);

    /**
     * Returns the item rebate end date of this imtd rs details.
     *
     * @return the item rebate end date of this imtd rs details
     */
    public Date getItemRebateEndDate();

    /**
     * Sets the item rebate end date of this imtd rs details.
     *
     * @param itemRebateEndDate the item rebate end date of this imtd rs details
     */
    public void setItemRebateEndDate(Date itemRebateEndDate);

    /**
     * Returns the rs details rebate plan name of this imtd rs details.
     *
     * @return the rs details rebate plan name of this imtd rs details
     */
    @AutoEscape
    public String getRsDetailsRebatePlanName();

    /**
     * Sets the rs details rebate plan name of this imtd rs details.
     *
     * @param rsDetailsRebatePlanName the rs details rebate plan name of this imtd rs details
     */
    public void setRsDetailsRebatePlanName(String rsDetailsRebatePlanName);

    /**
     * Returns the item rebate start date of this imtd rs details.
     *
     * @return the item rebate start date of this imtd rs details
     */
    public Date getItemRebateStartDate();

    /**
     * Sets the item rebate start date of this imtd rs details.
     *
     * @param itemRebateStartDate the item rebate start date of this imtd rs details
     */
    public void setItemRebateStartDate(Date itemRebateStartDate);

    /**
     * Returns the rs details formula type of this imtd rs details.
     *
     * @return the rs details formula type of this imtd rs details
     */
    @AutoEscape
    public String getRsDetailsFormulaType();

    /**
     * Sets the rs details formula type of this imtd rs details.
     *
     * @param rsDetailsFormulaType the rs details formula type of this imtd rs details
     */
    public void setRsDetailsFormulaType(String rsDetailsFormulaType);

    /**
     * Returns the session ID of this imtd rs details.
     *
     * @return the session ID of this imtd rs details
     */
    @AutoEscape
    public String getSessionId();

    /**
     * Sets the session ID of this imtd rs details.
     *
     * @param sessionId the session ID of this imtd rs details
     */
    public void setSessionId(String sessionId);

    /**
     * Returns the item name of this imtd rs details.
     *
     * @return the item name of this imtd rs details
     */
    @AutoEscape
    public String getItemName();

    /**
     * Sets the item name of this imtd rs details.
     *
     * @param itemName the item name of this imtd rs details
     */
    public void setItemName(String itemName);

    /**
     * Returns the operation of this imtd rs details.
     *
     * @return the operation of this imtd rs details
     */
    @AutoEscape
    public String getOperation();

    /**
     * Sets the operation of this imtd rs details.
     *
     * @param operation the operation of this imtd rs details
     */
    public void setOperation(String operation);

    /**
     * Returns the cfp model sid of this imtd rs details.
     *
     * @return the cfp model sid of this imtd rs details
     */
    public int getCfpModelSid();

    /**
     * Sets the cfp model sid of this imtd rs details.
     *
     * @param cfpModelSid the cfp model sid of this imtd rs details
     */
    public void setCfpModelSid(int cfpModelSid);

    /**
     * Returns the rs model sid of this imtd rs details.
     *
     * @return the rs model sid of this imtd rs details
     */
    public int getRsModelSid();

    /**
     * Sets the rs model sid of this imtd rs details.
     *
     * @param rsModelSid the rs model sid of this imtd rs details
     */
    public void setRsModelSid(int rsModelSid);

    /**
     * Returns the rs details sid of this imtd rs details.
     *
     * @return the rs details sid of this imtd rs details
     */
    public int getRsDetailsSid();

    /**
     * Sets the rs details sid of this imtd rs details.
     *
     * @param rsDetailsSid the rs details sid of this imtd rs details
     */
    public void setRsDetailsSid(int rsDetailsSid);

    /**
     * Returns the rs details attached status of this imtd rs details.
     *
     * @return the rs details attached status of this imtd rs details
     */
    public int getRsDetailsAttachedStatus();

    /**
     * Sets the rs details attached status of this imtd rs details.
     *
     * @param rsDetailsAttachedStatus the rs details attached status of this imtd rs details
     */
    public void setRsDetailsAttachedStatus(int rsDetailsAttachedStatus);

    /**
     * Returns the rs details net sales formula no of this imtd rs details.
     *
     * @return the rs details net sales formula no of this imtd rs details
     */
    @AutoEscape
    public String getRsDetailsNetSalesFormulaNo();

    /**
     * Sets the rs details net sales formula no of this imtd rs details.
     *
     * @param rsDetailsNetSalesFormulaNo the rs details net sales formula no of this imtd rs details
     */
    public void setRsDetailsNetSalesFormulaNo(String rsDetailsNetSalesFormulaNo);

    /**
     * Returns the rs details net sales formula name of this imtd rs details.
     *
     * @return the rs details net sales formula name of this imtd rs details
     */
    @AutoEscape
    public String getRsDetailsNetSalesFormulaName();

    /**
     * Sets the rs details net sales formula name of this imtd rs details.
     *
     * @param rsDetailsNetSalesFormulaName the rs details net sales formula name of this imtd rs details
     */
    public void setRsDetailsNetSalesFormulaName(
        String rsDetailsNetSalesFormulaName);

    /**
     * Returns the rs details deduction calendar no of this imtd rs details.
     *
     * @return the rs details deduction calendar no of this imtd rs details
     */
    @AutoEscape
    public String getRsDetailsDeductionCalendarNo();

    /**
     * Sets the rs details deduction calendar no of this imtd rs details.
     *
     * @param rsDetailsDeductionCalendarNo the rs details deduction calendar no of this imtd rs details
     */
    public void setRsDetailsDeductionCalendarNo(
        String rsDetailsDeductionCalendarNo);

    /**
     * Returns the rs details deduction calendar name of this imtd rs details.
     *
     * @return the rs details deduction calendar name of this imtd rs details
     */
    @AutoEscape
    public String getRsDetailsDeductionCalendarName();

    /**
     * Sets the rs details deduction calendar name of this imtd rs details.
     *
     * @param rsDetailsDeductionCalendarName the rs details deduction calendar name of this imtd rs details
     */
    public void setRsDetailsDeductionCalendarName(
        String rsDetailsDeductionCalendarName);

    /**
     * Returns the deduction calendar master sid of this imtd rs details.
     *
     * @return the deduction calendar master sid of this imtd rs details
     */
    public int getDeductionCalendarMasterSid();

    /**
     * Sets the deduction calendar master sid of this imtd rs details.
     *
     * @param deductionCalendarMasterSid the deduction calendar master sid of this imtd rs details
     */
    public void setDeductionCalendarMasterSid(int deductionCalendarMasterSid);

    /**
     * Returns the net sales formula master sid of this imtd rs details.
     *
     * @return the net sales formula master sid of this imtd rs details
     */
    public int getNetSalesFormulaMasterSid();

    /**
     * Sets the net sales formula master sid of this imtd rs details.
     *
     * @param netSalesFormulaMasterSid the net sales formula master sid of this imtd rs details
     */
    public void setNetSalesFormulaMasterSid(int netSalesFormulaMasterSid);

    /**
     * Returns the evaluation rule of this imtd rs details.
     *
     * @return the evaluation rule of this imtd rs details
     */
    @AutoEscape
    public String getEvaluationRule();

    /**
     * Sets the evaluation rule of this imtd rs details.
     *
     * @param evaluationRule the evaluation rule of this imtd rs details
     */
    public void setEvaluationRule(String evaluationRule);

    /**
     * Returns the net sales rule of this imtd rs details.
     *
     * @return the net sales rule of this imtd rs details
     */
    @AutoEscape
    public String getNetSalesRule();

    /**
     * Sets the net sales rule of this imtd rs details.
     *
     * @param netSalesRule the net sales rule of this imtd rs details
     */
    public void setNetSalesRule(String netSalesRule);

    /**
     * Returns the formula type of this imtd rs details.
     *
     * @return the formula type of this imtd rs details
     */
    @AutoEscape
    public String getFormulaType();

    /**
     * Sets the formula type of this imtd rs details.
     *
     * @param formulaType the formula type of this imtd rs details
     */
    public void setFormulaType(String formulaType);

    /**
     * Returns the calculation rule of this imtd rs details.
     *
     * @return the calculation rule of this imtd rs details
     */
    @AutoEscape
    public String getCalculationRule();

    /**
     * Sets the calculation rule of this imtd rs details.
     *
     * @param calculationRule the calculation rule of this imtd rs details
     */
    public void setCalculationRule(String calculationRule);

    /**
     * Returns the calculation rule bundle of this imtd rs details.
     *
     * @return the calculation rule bundle of this imtd rs details
     */
    @AutoEscape
    public String getCalculationRuleBundle();

    /**
     * Sets the calculation rule bundle of this imtd rs details.
     *
     * @param calculationRuleBundle the calculation rule bundle of this imtd rs details
     */
    public void setCalculationRuleBundle(String calculationRuleBundle);

    /**
     * Returns the evaluation rule bundle of this imtd rs details.
     *
     * @return the evaluation rule bundle of this imtd rs details
     */
    @AutoEscape
    public String getEvaluationRuleBundle();

    /**
     * Sets the evaluation rule bundle of this imtd rs details.
     *
     * @param evaluationRuleBundle the evaluation rule bundle of this imtd rs details
     */
    public void setEvaluationRuleBundle(String evaluationRuleBundle);

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
    public int compareTo(ImtdRsDetails imtdRsDetails);

    @Override
    public int hashCode();

    @Override
    public CacheModel<ImtdRsDetails> toCacheModel();

    @Override
    public ImtdRsDetails toEscapedModel();

    @Override
    public ImtdRsDetails toUnescapedModel();

    @Override
    public String toString();

    @Override
    public String toXmlString();
}
