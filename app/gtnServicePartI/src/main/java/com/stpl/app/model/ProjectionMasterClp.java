package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.ProjectionMasterLocalServiceUtil;

import com.stpl.portal.kernel.bean.AutoEscapeBeanHandler;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.ProxyUtil;
import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class ProjectionMasterClp extends BaseModelImpl<ProjectionMaster>
    implements ProjectionMaster {
    private int _productHierarchyLevel;
    private boolean _saveFlag;
    private String _projectionName;
    private Date _toDate;
    private int _projectionMasterSid;
    private String _forecastingType;
    private int _productHierVersionNo;
    private int _customerHierVersionNo;
    private Date _modifiedDate;
    private int _customerHierarchyLevel;
    private Date _fromDate;
    private String _productHierarchySid;
    private Date _createdDate;
    private int _createdBy;
    private String _customerHierarchySid;
    private String _companyGroupSid;
    private boolean _brandType;
    private int _modifiedBy;
    private String _projectionDescription;
    private String _isApproved;
    private String _itemGroupSid;
    private String _companyMasterSid;
    private int _customerHierarchyInnerLevel;
    private int _productHierarchyInnerLevel;
    private String _custRelationshipBuilderSid;
    private String _prodRelationshipBuilderSid;
    private int _discountType;
    private int _businessUnit;
    private String _deductionHierarchySid;
    private String _dedRelationshipBuilderSid;
    private BaseModel<?> _projectionMasterRemoteModel;

    public ProjectionMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ProjectionMaster.class;
    }

    @Override
    public String getModelClassName() {
        return ProjectionMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _projectionMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setProjectionMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _projectionMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("productHierarchyLevel", getProductHierarchyLevel());
        attributes.put("saveFlag", getSaveFlag());
        attributes.put("projectionName", getProjectionName());
        attributes.put("toDate", getToDate());
        attributes.put("projectionMasterSid", getProjectionMasterSid());
        attributes.put("forecastingType", getForecastingType());
        attributes.put("productHierVersionNo", getProductHierVersionNo());
        attributes.put("customerHierVersionNo", getCustomerHierVersionNo());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("customerHierarchyLevel", getCustomerHierarchyLevel());
        attributes.put("fromDate", getFromDate());
        attributes.put("productHierarchySid", getProductHierarchySid());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("customerHierarchySid", getCustomerHierarchySid());
        attributes.put("companyGroupSid", getCompanyGroupSid());
        attributes.put("brandType", getBrandType());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("projectionDescription", getProjectionDescription());
        attributes.put("isApproved", getIsApproved());
        attributes.put("itemGroupSid", getItemGroupSid());
        attributes.put("companyMasterSid", getCompanyMasterSid());
        attributes.put("customerHierarchyInnerLevel",
            getCustomerHierarchyInnerLevel());
        attributes.put("productHierarchyInnerLevel",
            getProductHierarchyInnerLevel());
        attributes.put("custRelationshipBuilderSid",
            getCustRelationshipBuilderSid());
        attributes.put("prodRelationshipBuilderSid",
            getProdRelationshipBuilderSid());
        attributes.put("discountType", getDiscountType());
        attributes.put("businessUnit", getBusinessUnit());
        attributes.put("deductionHierarchySid", getDeductionHierarchySid());
        attributes.put("dedRelationshipBuilderSid",
            getDedRelationshipBuilderSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer productHierarchyLevel = (Integer) attributes.get(
                "productHierarchyLevel");

        if (productHierarchyLevel != null) {
            setProductHierarchyLevel(productHierarchyLevel);
        }

        Boolean saveFlag = (Boolean) attributes.get("saveFlag");

        if (saveFlag != null) {
            setSaveFlag(saveFlag);
        }

        String projectionName = (String) attributes.get("projectionName");

        if (projectionName != null) {
            setProjectionName(projectionName);
        }

        Date toDate = (Date) attributes.get("toDate");

        if (toDate != null) {
            setToDate(toDate);
        }

        Integer projectionMasterSid = (Integer) attributes.get(
                "projectionMasterSid");

        if (projectionMasterSid != null) {
            setProjectionMasterSid(projectionMasterSid);
        }

        String forecastingType = (String) attributes.get("forecastingType");

        if (forecastingType != null) {
            setForecastingType(forecastingType);
        }

        Integer productHierVersionNo = (Integer) attributes.get(
                "productHierVersionNo");

        if (productHierVersionNo != null) {
            setProductHierVersionNo(productHierVersionNo);
        }

        Integer customerHierVersionNo = (Integer) attributes.get(
                "customerHierVersionNo");

        if (customerHierVersionNo != null) {
            setCustomerHierVersionNo(customerHierVersionNo);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer customerHierarchyLevel = (Integer) attributes.get(
                "customerHierarchyLevel");

        if (customerHierarchyLevel != null) {
            setCustomerHierarchyLevel(customerHierarchyLevel);
        }

        Date fromDate = (Date) attributes.get("fromDate");

        if (fromDate != null) {
            setFromDate(fromDate);
        }

        String productHierarchySid = (String) attributes.get(
                "productHierarchySid");

        if (productHierarchySid != null) {
            setProductHierarchySid(productHierarchySid);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String customerHierarchySid = (String) attributes.get(
                "customerHierarchySid");

        if (customerHierarchySid != null) {
            setCustomerHierarchySid(customerHierarchySid);
        }

        String companyGroupSid = (String) attributes.get("companyGroupSid");

        if (companyGroupSid != null) {
            setCompanyGroupSid(companyGroupSid);
        }

        Boolean brandType = (Boolean) attributes.get("brandType");

        if (brandType != null) {
            setBrandType(brandType);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String projectionDescription = (String) attributes.get(
                "projectionDescription");

        if (projectionDescription != null) {
            setProjectionDescription(projectionDescription);
        }

        String isApproved = (String) attributes.get("isApproved");

        if (isApproved != null) {
            setIsApproved(isApproved);
        }

        String itemGroupSid = (String) attributes.get("itemGroupSid");

        if (itemGroupSid != null) {
            setItemGroupSid(itemGroupSid);
        }

        String companyMasterSid = (String) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }

        Integer customerHierarchyInnerLevel = (Integer) attributes.get(
                "customerHierarchyInnerLevel");

        if (customerHierarchyInnerLevel != null) {
            setCustomerHierarchyInnerLevel(customerHierarchyInnerLevel);
        }

        Integer productHierarchyInnerLevel = (Integer) attributes.get(
                "productHierarchyInnerLevel");

        if (productHierarchyInnerLevel != null) {
            setProductHierarchyInnerLevel(productHierarchyInnerLevel);
        }

        String custRelationshipBuilderSid = (String) attributes.get(
                "custRelationshipBuilderSid");

        if (custRelationshipBuilderSid != null) {
            setCustRelationshipBuilderSid(custRelationshipBuilderSid);
        }

        String prodRelationshipBuilderSid = (String) attributes.get(
                "prodRelationshipBuilderSid");

        if (prodRelationshipBuilderSid != null) {
            setProdRelationshipBuilderSid(prodRelationshipBuilderSid);
        }

        Integer discountType = (Integer) attributes.get("discountType");

        if (discountType != null) {
            setDiscountType(discountType);
        }

        Integer businessUnit = (Integer) attributes.get("businessUnit");

        if (businessUnit != null) {
            setBusinessUnit(businessUnit);
        }

        String deductionHierarchySid = (String) attributes.get(
                "deductionHierarchySid");

        if (deductionHierarchySid != null) {
            setDeductionHierarchySid(deductionHierarchySid);
        }

        String dedRelationshipBuilderSid = (String) attributes.get(
                "dedRelationshipBuilderSid");

        if (dedRelationshipBuilderSid != null) {
            setDedRelationshipBuilderSid(dedRelationshipBuilderSid);
        }
    }

    @Override
    public int getProductHierarchyLevel() {
        return _productHierarchyLevel;
    }

    @Override
    public void setProductHierarchyLevel(int productHierarchyLevel) {
        _productHierarchyLevel = productHierarchyLevel;

        if (_projectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _projectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProductHierarchyLevel",
                        int.class);

                method.invoke(_projectionMasterRemoteModel,
                    productHierarchyLevel);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getSaveFlag() {
        return _saveFlag;
    }

    @Override
    public boolean isSaveFlag() {
        return _saveFlag;
    }

    @Override
    public void setSaveFlag(boolean saveFlag) {
        _saveFlag = saveFlag;

        if (_projectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _projectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSaveFlag", boolean.class);

                method.invoke(_projectionMasterRemoteModel, saveFlag);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getProjectionName() {
        return _projectionName;
    }

    @Override
    public void setProjectionName(String projectionName) {
        _projectionName = projectionName;

        if (_projectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _projectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionName",
                        String.class);

                method.invoke(_projectionMasterRemoteModel, projectionName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getToDate() {
        return _toDate;
    }

    @Override
    public void setToDate(Date toDate) {
        _toDate = toDate;

        if (_projectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _projectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setToDate", Date.class);

                method.invoke(_projectionMasterRemoteModel, toDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getProjectionMasterSid() {
        return _projectionMasterSid;
    }

    @Override
    public void setProjectionMasterSid(int projectionMasterSid) {
        _projectionMasterSid = projectionMasterSid;

        if (_projectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _projectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionMasterSid",
                        int.class);

                method.invoke(_projectionMasterRemoteModel, projectionMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getForecastingType() {
        return _forecastingType;
    }

    @Override
    public void setForecastingType(String forecastingType) {
        _forecastingType = forecastingType;

        if (_projectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _projectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastingType",
                        String.class);

                method.invoke(_projectionMasterRemoteModel, forecastingType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getProductHierVersionNo() {
        return _productHierVersionNo;
    }

    @Override
    public void setProductHierVersionNo(int productHierVersionNo) {
        _productHierVersionNo = productHierVersionNo;

        if (_projectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _projectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProductHierVersionNo",
                        int.class);

                method.invoke(_projectionMasterRemoteModel, productHierVersionNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCustomerHierVersionNo() {
        return _customerHierVersionNo;
    }

    @Override
    public void setCustomerHierVersionNo(int customerHierVersionNo) {
        _customerHierVersionNo = customerHierVersionNo;

        if (_projectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _projectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCustomerHierVersionNo",
                        int.class);

                method.invoke(_projectionMasterRemoteModel,
                    customerHierVersionNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getModifiedDate() {
        return _modifiedDate;
    }

    @Override
    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;

        if (_projectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _projectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_projectionMasterRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCustomerHierarchyLevel() {
        return _customerHierarchyLevel;
    }

    @Override
    public void setCustomerHierarchyLevel(int customerHierarchyLevel) {
        _customerHierarchyLevel = customerHierarchyLevel;

        if (_projectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _projectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCustomerHierarchyLevel",
                        int.class);

                method.invoke(_projectionMasterRemoteModel,
                    customerHierarchyLevel);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getFromDate() {
        return _fromDate;
    }

    @Override
    public void setFromDate(Date fromDate) {
        _fromDate = fromDate;

        if (_projectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _projectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setFromDate", Date.class);

                method.invoke(_projectionMasterRemoteModel, fromDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getProductHierarchySid() {
        return _productHierarchySid;
    }

    @Override
    public void setProductHierarchySid(String productHierarchySid) {
        _productHierarchySid = productHierarchySid;

        if (_projectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _projectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProductHierarchySid",
                        String.class);

                method.invoke(_projectionMasterRemoteModel, productHierarchySid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCreatedDate() {
        return _createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;

        if (_projectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _projectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_projectionMasterRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCreatedBy() {
        return _createdBy;
    }

    @Override
    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;

        if (_projectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _projectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_projectionMasterRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCustomerHierarchySid() {
        return _customerHierarchySid;
    }

    @Override
    public void setCustomerHierarchySid(String customerHierarchySid) {
        _customerHierarchySid = customerHierarchySid;

        if (_projectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _projectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCustomerHierarchySid",
                        String.class);

                method.invoke(_projectionMasterRemoteModel, customerHierarchySid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyGroupSid() {
        return _companyGroupSid;
    }

    @Override
    public void setCompanyGroupSid(String companyGroupSid) {
        _companyGroupSid = companyGroupSid;

        if (_projectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _projectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyGroupSid",
                        String.class);

                method.invoke(_projectionMasterRemoteModel, companyGroupSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getBrandType() {
        return _brandType;
    }

    @Override
    public boolean isBrandType() {
        return _brandType;
    }

    @Override
    public void setBrandType(boolean brandType) {
        _brandType = brandType;

        if (_projectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _projectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandType", boolean.class);

                method.invoke(_projectionMasterRemoteModel, brandType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getModifiedBy() {
        return _modifiedBy;
    }

    @Override
    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;

        if (_projectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _projectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_projectionMasterRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getProjectionDescription() {
        return _projectionDescription;
    }

    @Override
    public void setProjectionDescription(String projectionDescription) {
        _projectionDescription = projectionDescription;

        if (_projectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _projectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDescription",
                        String.class);

                method.invoke(_projectionMasterRemoteModel,
                    projectionDescription);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIsApproved() {
        return _isApproved;
    }

    @Override
    public void setIsApproved(String isApproved) {
        _isApproved = isApproved;

        if (_projectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _projectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setIsApproved", String.class);

                method.invoke(_projectionMasterRemoteModel, isApproved);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemGroupSid() {
        return _itemGroupSid;
    }

    @Override
    public void setItemGroupSid(String itemGroupSid) {
        _itemGroupSid = itemGroupSid;

        if (_projectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _projectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemGroupSid", String.class);

                method.invoke(_projectionMasterRemoteModel, itemGroupSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyMasterSid() {
        return _companyMasterSid;
    }

    @Override
    public void setCompanyMasterSid(String companyMasterSid) {
        _companyMasterSid = companyMasterSid;

        if (_projectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _projectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyMasterSid",
                        String.class);

                method.invoke(_projectionMasterRemoteModel, companyMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCustomerHierarchyInnerLevel() {
        return _customerHierarchyInnerLevel;
    }

    @Override
    public void setCustomerHierarchyInnerLevel(int customerHierarchyInnerLevel) {
        _customerHierarchyInnerLevel = customerHierarchyInnerLevel;

        if (_projectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _projectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCustomerHierarchyInnerLevel",
                        int.class);

                method.invoke(_projectionMasterRemoteModel,
                    customerHierarchyInnerLevel);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getProductHierarchyInnerLevel() {
        return _productHierarchyInnerLevel;
    }

    @Override
    public void setProductHierarchyInnerLevel(int productHierarchyInnerLevel) {
        _productHierarchyInnerLevel = productHierarchyInnerLevel;

        if (_projectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _projectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProductHierarchyInnerLevel",
                        int.class);

                method.invoke(_projectionMasterRemoteModel,
                    productHierarchyInnerLevel);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCustRelationshipBuilderSid() {
        return _custRelationshipBuilderSid;
    }

    @Override
    public void setCustRelationshipBuilderSid(String custRelationshipBuilderSid) {
        _custRelationshipBuilderSid = custRelationshipBuilderSid;

        if (_projectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _projectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCustRelationshipBuilderSid",
                        String.class);

                method.invoke(_projectionMasterRemoteModel,
                    custRelationshipBuilderSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getProdRelationshipBuilderSid() {
        return _prodRelationshipBuilderSid;
    }

    @Override
    public void setProdRelationshipBuilderSid(String prodRelationshipBuilderSid) {
        _prodRelationshipBuilderSid = prodRelationshipBuilderSid;

        if (_projectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _projectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProdRelationshipBuilderSid",
                        String.class);

                method.invoke(_projectionMasterRemoteModel,
                    prodRelationshipBuilderSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getDiscountType() {
        return _discountType;
    }

    @Override
    public void setDiscountType(int discountType) {
        _discountType = discountType;

        if (_projectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _projectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDiscountType", int.class);

                method.invoke(_projectionMasterRemoteModel, discountType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getBusinessUnit() {
        return _businessUnit;
    }

    @Override
    public void setBusinessUnit(int businessUnit) {
        _businessUnit = businessUnit;

        if (_projectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _projectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBusinessUnit", int.class);

                method.invoke(_projectionMasterRemoteModel, businessUnit);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionHierarchySid() {
        return _deductionHierarchySid;
    }

    @Override
    public void setDeductionHierarchySid(String deductionHierarchySid) {
        _deductionHierarchySid = deductionHierarchySid;

        if (_projectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _projectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionHierarchySid",
                        String.class);

                method.invoke(_projectionMasterRemoteModel,
                    deductionHierarchySid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDedRelationshipBuilderSid() {
        return _dedRelationshipBuilderSid;
    }

    @Override
    public void setDedRelationshipBuilderSid(String dedRelationshipBuilderSid) {
        _dedRelationshipBuilderSid = dedRelationshipBuilderSid;

        if (_projectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _projectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDedRelationshipBuilderSid",
                        String.class);

                method.invoke(_projectionMasterRemoteModel,
                    dedRelationshipBuilderSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getProjectionMasterRemoteModel() {
        return _projectionMasterRemoteModel;
    }

    public void setProjectionMasterRemoteModel(
        BaseModel<?> projectionMasterRemoteModel) {
        _projectionMasterRemoteModel = projectionMasterRemoteModel;
    }

    public Object invokeOnRemoteModel(String methodName,
        Class<?>[] parameterTypes, Object[] parameterValues)
        throws Exception {
        Object[] remoteParameterValues = new Object[parameterValues.length];

        for (int i = 0; i < parameterValues.length; i++) {
            if (parameterValues[i] != null) {
                remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
            }
        }

        Class<?> remoteModelClass = _projectionMasterRemoteModel.getClass();

        ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

        Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i].isPrimitive()) {
                remoteParameterTypes[i] = parameterTypes[i];
            } else {
                String parameterTypeName = parameterTypes[i].getName();

                remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
            }
        }

        Method method = remoteModelClass.getMethod(methodName,
                remoteParameterTypes);

        Object returnValue = method.invoke(_projectionMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ProjectionMasterLocalServiceUtil.addProjectionMaster(this);
        } else {
            ProjectionMasterLocalServiceUtil.updateProjectionMaster(this);
        }
    }

    @Override
    public ProjectionMaster toEscapedModel() {
        return (ProjectionMaster) ProxyUtil.newProxyInstance(ProjectionMaster.class.getClassLoader(),
            new Class[] { ProjectionMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ProjectionMasterClp clone = new ProjectionMasterClp();

        clone.setProductHierarchyLevel(getProductHierarchyLevel());
        clone.setSaveFlag(getSaveFlag());
        clone.setProjectionName(getProjectionName());
        clone.setToDate(getToDate());
        clone.setProjectionMasterSid(getProjectionMasterSid());
        clone.setForecastingType(getForecastingType());
        clone.setProductHierVersionNo(getProductHierVersionNo());
        clone.setCustomerHierVersionNo(getCustomerHierVersionNo());
        clone.setModifiedDate(getModifiedDate());
        clone.setCustomerHierarchyLevel(getCustomerHierarchyLevel());
        clone.setFromDate(getFromDate());
        clone.setProductHierarchySid(getProductHierarchySid());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setCustomerHierarchySid(getCustomerHierarchySid());
        clone.setCompanyGroupSid(getCompanyGroupSid());
        clone.setBrandType(getBrandType());
        clone.setModifiedBy(getModifiedBy());
        clone.setProjectionDescription(getProjectionDescription());
        clone.setIsApproved(getIsApproved());
        clone.setItemGroupSid(getItemGroupSid());
        clone.setCompanyMasterSid(getCompanyMasterSid());
        clone.setCustomerHierarchyInnerLevel(getCustomerHierarchyInnerLevel());
        clone.setProductHierarchyInnerLevel(getProductHierarchyInnerLevel());
        clone.setCustRelationshipBuilderSid(getCustRelationshipBuilderSid());
        clone.setProdRelationshipBuilderSid(getProdRelationshipBuilderSid());
        clone.setDiscountType(getDiscountType());
        clone.setBusinessUnit(getBusinessUnit());
        clone.setDeductionHierarchySid(getDeductionHierarchySid());
        clone.setDedRelationshipBuilderSid(getDedRelationshipBuilderSid());

        return clone;
    }

    @Override
    public int compareTo(ProjectionMaster projectionMaster) {
        int primaryKey = projectionMaster.getPrimaryKey();

        if (getPrimaryKey() < primaryKey) {
            return -1;
        } else if (getPrimaryKey() > primaryKey) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ProjectionMasterClp)) {
            return false;
        }

        ProjectionMasterClp projectionMaster = (ProjectionMasterClp) obj;

        int primaryKey = projectionMaster.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getPrimaryKey();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(61);

        sb.append("{productHierarchyLevel=");
        sb.append(getProductHierarchyLevel());
        sb.append(", saveFlag=");
        sb.append(getSaveFlag());
        sb.append(", projectionName=");
        sb.append(getProjectionName());
        sb.append(", toDate=");
        sb.append(getToDate());
        sb.append(", projectionMasterSid=");
        sb.append(getProjectionMasterSid());
        sb.append(", forecastingType=");
        sb.append(getForecastingType());
        sb.append(", productHierVersionNo=");
        sb.append(getProductHierVersionNo());
        sb.append(", customerHierVersionNo=");
        sb.append(getCustomerHierVersionNo());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", customerHierarchyLevel=");
        sb.append(getCustomerHierarchyLevel());
        sb.append(", fromDate=");
        sb.append(getFromDate());
        sb.append(", productHierarchySid=");
        sb.append(getProductHierarchySid());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", customerHierarchySid=");
        sb.append(getCustomerHierarchySid());
        sb.append(", companyGroupSid=");
        sb.append(getCompanyGroupSid());
        sb.append(", brandType=");
        sb.append(getBrandType());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", projectionDescription=");
        sb.append(getProjectionDescription());
        sb.append(", isApproved=");
        sb.append(getIsApproved());
        sb.append(", itemGroupSid=");
        sb.append(getItemGroupSid());
        sb.append(", companyMasterSid=");
        sb.append(getCompanyMasterSid());
        sb.append(", customerHierarchyInnerLevel=");
        sb.append(getCustomerHierarchyInnerLevel());
        sb.append(", productHierarchyInnerLevel=");
        sb.append(getProductHierarchyInnerLevel());
        sb.append(", custRelationshipBuilderSid=");
        sb.append(getCustRelationshipBuilderSid());
        sb.append(", prodRelationshipBuilderSid=");
        sb.append(getProdRelationshipBuilderSid());
        sb.append(", discountType=");
        sb.append(getDiscountType());
        sb.append(", businessUnit=");
        sb.append(getBusinessUnit());
        sb.append(", deductionHierarchySid=");
        sb.append(getDeductionHierarchySid());
        sb.append(", dedRelationshipBuilderSid=");
        sb.append(getDedRelationshipBuilderSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(94);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ProjectionMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>productHierarchyLevel</column-name><column-value><![CDATA[");
        sb.append(getProductHierarchyLevel());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>saveFlag</column-name><column-value><![CDATA[");
        sb.append(getSaveFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionName</column-name><column-value><![CDATA[");
        sb.append(getProjectionName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>toDate</column-name><column-value><![CDATA[");
        sb.append(getToDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionMasterSid</column-name><column-value><![CDATA[");
        sb.append(getProjectionMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastingType</column-name><column-value><![CDATA[");
        sb.append(getForecastingType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>productHierVersionNo</column-name><column-value><![CDATA[");
        sb.append(getProductHierVersionNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>customerHierVersionNo</column-name><column-value><![CDATA[");
        sb.append(getCustomerHierVersionNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>customerHierarchyLevel</column-name><column-value><![CDATA[");
        sb.append(getCustomerHierarchyLevel());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fromDate</column-name><column-value><![CDATA[");
        sb.append(getFromDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>productHierarchySid</column-name><column-value><![CDATA[");
        sb.append(getProductHierarchySid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>customerHierarchySid</column-name><column-value><![CDATA[");
        sb.append(getCustomerHierarchySid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyGroupSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyGroupSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brandType</column-name><column-value><![CDATA[");
        sb.append(getBrandType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionDescription</column-name><column-value><![CDATA[");
        sb.append(getProjectionDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>isApproved</column-name><column-value><![CDATA[");
        sb.append(getIsApproved());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemGroupSid</column-name><column-value><![CDATA[");
        sb.append(getItemGroupSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>customerHierarchyInnerLevel</column-name><column-value><![CDATA[");
        sb.append(getCustomerHierarchyInnerLevel());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>productHierarchyInnerLevel</column-name><column-value><![CDATA[");
        sb.append(getProductHierarchyInnerLevel());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>custRelationshipBuilderSid</column-name><column-value><![CDATA[");
        sb.append(getCustRelationshipBuilderSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>prodRelationshipBuilderSid</column-name><column-value><![CDATA[");
        sb.append(getProdRelationshipBuilderSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>discountType</column-name><column-value><![CDATA[");
        sb.append(getDiscountType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>businessUnit</column-name><column-value><![CDATA[");
        sb.append(getBusinessUnit());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionHierarchySid</column-name><column-value><![CDATA[");
        sb.append(getDeductionHierarchySid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>dedRelationshipBuilderSid</column-name><column-value><![CDATA[");
        sb.append(getDedRelationshipBuilderSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
