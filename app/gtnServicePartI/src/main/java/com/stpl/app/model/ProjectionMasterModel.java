package com.stpl.app.model;

import com.stpl.portal.kernel.bean.AutoEscape;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.service.ServiceContext;

import com.stpl.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the ProjectionMaster service. Represents a row in the &quot;PROJECTION_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.stpl.app.model.impl.ProjectionMasterModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.stpl.app.model.impl.ProjectionMasterImpl}.
 * </p>
 *
 * @author
 * @see ProjectionMaster
 * @see com.stpl.app.model.impl.ProjectionMasterImpl
 * @see com.stpl.app.model.impl.ProjectionMasterModelImpl
 * @generated
 */
public interface ProjectionMasterModel extends BaseModel<ProjectionMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a projection master model instance should use the {@link ProjectionMaster} interface instead.
     */

    /**
     * Returns the primary key of this projection master.
     *
     * @return the primary key of this projection master
     */
    public int getPrimaryKey();

    /**
     * Sets the primary key of this projection master.
     *
     * @param primaryKey the primary key of this projection master
     */
    public void setPrimaryKey(int primaryKey);

    /**
     * Returns the product hierarchy level of this projection master.
     *
     * @return the product hierarchy level of this projection master
     */
    public int getProductHierarchyLevel();

    /**
     * Sets the product hierarchy level of this projection master.
     *
     * @param productHierarchyLevel the product hierarchy level of this projection master
     */
    public void setProductHierarchyLevel(int productHierarchyLevel);

    /**
     * Returns the save flag of this projection master.
     *
     * @return the save flag of this projection master
     */
    public boolean getSaveFlag();

    /**
     * Returns <code>true</code> if this projection master is save flag.
     *
     * @return <code>true</code> if this projection master is save flag; <code>false</code> otherwise
     */
    public boolean isSaveFlag();

    /**
     * Sets whether this projection master is save flag.
     *
     * @param saveFlag the save flag of this projection master
     */
    public void setSaveFlag(boolean saveFlag);

    /**
     * Returns the projection name of this projection master.
     *
     * @return the projection name of this projection master
     */
    @AutoEscape
    public String getProjectionName();

    /**
     * Sets the projection name of this projection master.
     *
     * @param projectionName the projection name of this projection master
     */
    public void setProjectionName(String projectionName);

    /**
     * Returns the to date of this projection master.
     *
     * @return the to date of this projection master
     */
    public Date getToDate();

    /**
     * Sets the to date of this projection master.
     *
     * @param toDate the to date of this projection master
     */
    public void setToDate(Date toDate);

    /**
     * Returns the projection master sid of this projection master.
     *
     * @return the projection master sid of this projection master
     */
    public int getProjectionMasterSid();

    /**
     * Sets the projection master sid of this projection master.
     *
     * @param projectionMasterSid the projection master sid of this projection master
     */
    public void setProjectionMasterSid(int projectionMasterSid);

    /**
     * Returns the forecasting type of this projection master.
     *
     * @return the forecasting type of this projection master
     */
    @AutoEscape
    public String getForecastingType();

    /**
     * Sets the forecasting type of this projection master.
     *
     * @param forecastingType the forecasting type of this projection master
     */
    public void setForecastingType(String forecastingType);

    /**
     * Returns the product hier version no of this projection master.
     *
     * @return the product hier version no of this projection master
     */
    public int getProductHierVersionNo();

    /**
     * Sets the product hier version no of this projection master.
     *
     * @param productHierVersionNo the product hier version no of this projection master
     */
    public void setProductHierVersionNo(int productHierVersionNo);

    /**
     * Returns the customer hier version no of this projection master.
     *
     * @return the customer hier version no of this projection master
     */
    public int getCustomerHierVersionNo();

    /**
     * Sets the customer hier version no of this projection master.
     *
     * @param customerHierVersionNo the customer hier version no of this projection master
     */
    public void setCustomerHierVersionNo(int customerHierVersionNo);

    /**
     * Returns the modified date of this projection master.
     *
     * @return the modified date of this projection master
     */
    public Date getModifiedDate();

    /**
     * Sets the modified date of this projection master.
     *
     * @param modifiedDate the modified date of this projection master
     */
    public void setModifiedDate(Date modifiedDate);

    /**
     * Returns the customer hierarchy level of this projection master.
     *
     * @return the customer hierarchy level of this projection master
     */
    public int getCustomerHierarchyLevel();

    /**
     * Sets the customer hierarchy level of this projection master.
     *
     * @param customerHierarchyLevel the customer hierarchy level of this projection master
     */
    public void setCustomerHierarchyLevel(int customerHierarchyLevel);

    /**
     * Returns the from date of this projection master.
     *
     * @return the from date of this projection master
     */
    public Date getFromDate();

    /**
     * Sets the from date of this projection master.
     *
     * @param fromDate the from date of this projection master
     */
    public void setFromDate(Date fromDate);

    /**
     * Returns the product hierarchy sid of this projection master.
     *
     * @return the product hierarchy sid of this projection master
     */
    @AutoEscape
    public String getProductHierarchySid();

    /**
     * Sets the product hierarchy sid of this projection master.
     *
     * @param productHierarchySid the product hierarchy sid of this projection master
     */
    public void setProductHierarchySid(String productHierarchySid);

    /**
     * Returns the created date of this projection master.
     *
     * @return the created date of this projection master
     */
    public Date getCreatedDate();

    /**
     * Sets the created date of this projection master.
     *
     * @param createdDate the created date of this projection master
     */
    public void setCreatedDate(Date createdDate);

    /**
     * Returns the created by of this projection master.
     *
     * @return the created by of this projection master
     */
    public int getCreatedBy();

    /**
     * Sets the created by of this projection master.
     *
     * @param createdBy the created by of this projection master
     */
    public void setCreatedBy(int createdBy);

    /**
     * Returns the customer hierarchy sid of this projection master.
     *
     * @return the customer hierarchy sid of this projection master
     */
    @AutoEscape
    public String getCustomerHierarchySid();

    /**
     * Sets the customer hierarchy sid of this projection master.
     *
     * @param customerHierarchySid the customer hierarchy sid of this projection master
     */
    public void setCustomerHierarchySid(String customerHierarchySid);

    /**
     * Returns the company group sid of this projection master.
     *
     * @return the company group sid of this projection master
     */
    @AutoEscape
    public String getCompanyGroupSid();

    /**
     * Sets the company group sid of this projection master.
     *
     * @param companyGroupSid the company group sid of this projection master
     */
    public void setCompanyGroupSid(String companyGroupSid);

    /**
     * Returns the brand type of this projection master.
     *
     * @return the brand type of this projection master
     */
    public boolean getBrandType();

    /**
     * Returns <code>true</code> if this projection master is brand type.
     *
     * @return <code>true</code> if this projection master is brand type; <code>false</code> otherwise
     */
    public boolean isBrandType();

    /**
     * Sets whether this projection master is brand type.
     *
     * @param brandType the brand type of this projection master
     */
    public void setBrandType(boolean brandType);

    /**
     * Returns the modified by of this projection master.
     *
     * @return the modified by of this projection master
     */
    public int getModifiedBy();

    /**
     * Sets the modified by of this projection master.
     *
     * @param modifiedBy the modified by of this projection master
     */
    public void setModifiedBy(int modifiedBy);

    /**
     * Returns the projection description of this projection master.
     *
     * @return the projection description of this projection master
     */
    @AutoEscape
    public String getProjectionDescription();

    /**
     * Sets the projection description of this projection master.
     *
     * @param projectionDescription the projection description of this projection master
     */
    public void setProjectionDescription(String projectionDescription);

    /**
     * Returns the is approved of this projection master.
     *
     * @return the is approved of this projection master
     */
    @AutoEscape
    public String getIsApproved();

    /**
     * Sets the is approved of this projection master.
     *
     * @param isApproved the is approved of this projection master
     */
    public void setIsApproved(String isApproved);

    /**
     * Returns the item group sid of this projection master.
     *
     * @return the item group sid of this projection master
     */
    @AutoEscape
    public String getItemGroupSid();

    /**
     * Sets the item group sid of this projection master.
     *
     * @param itemGroupSid the item group sid of this projection master
     */
    public void setItemGroupSid(String itemGroupSid);

    /**
     * Returns the company master sid of this projection master.
     *
     * @return the company master sid of this projection master
     */
    @AutoEscape
    public String getCompanyMasterSid();

    /**
     * Sets the company master sid of this projection master.
     *
     * @param companyMasterSid the company master sid of this projection master
     */
    public void setCompanyMasterSid(String companyMasterSid);

    /**
     * Returns the customer hierarchy inner level of this projection master.
     *
     * @return the customer hierarchy inner level of this projection master
     */
    public int getCustomerHierarchyInnerLevel();

    /**
     * Sets the customer hierarchy inner level of this projection master.
     *
     * @param customerHierarchyInnerLevel the customer hierarchy inner level of this projection master
     */
    public void setCustomerHierarchyInnerLevel(int customerHierarchyInnerLevel);

    /**
     * Returns the product hierarchy inner level of this projection master.
     *
     * @return the product hierarchy inner level of this projection master
     */
    public int getProductHierarchyInnerLevel();

    /**
     * Sets the product hierarchy inner level of this projection master.
     *
     * @param productHierarchyInnerLevel the product hierarchy inner level of this projection master
     */
    public void setProductHierarchyInnerLevel(int productHierarchyInnerLevel);

    /**
     * Returns the cust relationship builder sid of this projection master.
     *
     * @return the cust relationship builder sid of this projection master
     */
    @AutoEscape
    public String getCustRelationshipBuilderSid();

    /**
     * Sets the cust relationship builder sid of this projection master.
     *
     * @param custRelationshipBuilderSid the cust relationship builder sid of this projection master
     */
    public void setCustRelationshipBuilderSid(String custRelationshipBuilderSid);

    /**
     * Returns the prod relationship builder sid of this projection master.
     *
     * @return the prod relationship builder sid of this projection master
     */
    @AutoEscape
    public String getProdRelationshipBuilderSid();

    /**
     * Sets the prod relationship builder sid of this projection master.
     *
     * @param prodRelationshipBuilderSid the prod relationship builder sid of this projection master
     */
    public void setProdRelationshipBuilderSid(String prodRelationshipBuilderSid);

    /**
     * Returns the discount type of this projection master.
     *
     * @return the discount type of this projection master
     */
    public int getDiscountType();

    /**
     * Sets the discount type of this projection master.
     *
     * @param discountType the discount type of this projection master
     */
    public void setDiscountType(int discountType);

    /**
     * Returns the business unit of this projection master.
     *
     * @return the business unit of this projection master
     */
    public int getBusinessUnit();

    /**
     * Sets the business unit of this projection master.
     *
     * @param businessUnit the business unit of this projection master
     */
    public void setBusinessUnit(int businessUnit);

    /**
     * Returns the deduction hierarchy sid of this projection master.
     *
     * @return the deduction hierarchy sid of this projection master
     */
    @AutoEscape
    public String getDeductionHierarchySid();

    /**
     * Sets the deduction hierarchy sid of this projection master.
     *
     * @param deductionHierarchySid the deduction hierarchy sid of this projection master
     */
    public void setDeductionHierarchySid(String deductionHierarchySid);

    /**
     * Returns the ded relationship builder sid of this projection master.
     *
     * @return the ded relationship builder sid of this projection master
     */
    @AutoEscape
    public String getDedRelationshipBuilderSid();

    /**
     * Sets the ded relationship builder sid of this projection master.
     *
     * @param dedRelationshipBuilderSid the ded relationship builder sid of this projection master
     */
    public void setDedRelationshipBuilderSid(String dedRelationshipBuilderSid);

    /**
     * Returns the projection cust version no of this projection master.
     *
     * @return the projection cust version no of this projection master
     */
    public int getProjectionCustVersionNo();

    /**
     * Sets the projection cust version no of this projection master.
     *
     * @param projectionCustVersionNo the projection cust version no of this projection master
     */
    public void setProjectionCustVersionNo(int projectionCustVersionNo);

    /**
     * Returns the projection prod version no of this projection master.
     *
     * @return the projection prod version no of this projection master
     */
    public int getProjectionProdVersionNo();

    /**
     * Sets the projection prod version no of this projection master.
     *
     * @param projectionProdVersionNo the projection prod version no of this projection master
     */
    public void setProjectionProdVersionNo(int projectionProdVersionNo);

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
    public int compareTo(ProjectionMaster projectionMaster);

    @Override
    public int hashCode();

    @Override
    public CacheModel<ProjectionMaster> toCacheModel();

    @Override
    public ProjectionMaster toEscapedModel();

    @Override
    public ProjectionMaster toUnescapedModel();

    @Override
    public String toString();

    @Override
    public String toXmlString();
}
