package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.CffMasterLocalServiceUtil;
import com.stpl.app.parttwo.service.ClpSerializer;

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


public class CffMasterClp extends BaseModelImpl<CffMaster> implements CffMaster {
    private int _productHierarchyLevel;
    private Date _activeFromDate;
    private int _cffType;
    private boolean _cffOfficial;
    private int _cffMasterSid;
    private int _productHierVersionNo;
    private Date _activeToDate;
    private int _customerHierVersionNo;
    private Date _modifiedDate;
    private int _customerHierarchyLevel;
    private int _productHierarchySid;
    private String _cffName;
    private int _customerHierarchyInnerLevel;
    private Date _createdDate;
    private int _createdBy;
    private int _customerHierarchySid;
    private int _companyGroupSid;
    private int _prodRelationshipBuilderSid;
    private int _modifiedBy;
    private String _inboundStatus;
    private int _productHierarchyInnerLevel;
    private int _itemGroupSid;
    private int _custRelationshipBuilderSid;
    private int _companyMasterSid;
    private BaseModel<?> _cffMasterRemoteModel;

    public CffMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return CffMaster.class;
    }

    @Override
    public String getModelClassName() {
        return CffMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _cffMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setCffMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _cffMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("productHierarchyLevel", getProductHierarchyLevel());
        attributes.put("activeFromDate", getActiveFromDate());
        attributes.put("cffType", getCffType());
        attributes.put("cffOfficial", getCffOfficial());
        attributes.put("cffMasterSid", getCffMasterSid());
        attributes.put("productHierVersionNo", getProductHierVersionNo());
        attributes.put("activeToDate", getActiveToDate());
        attributes.put("customerHierVersionNo", getCustomerHierVersionNo());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("customerHierarchyLevel", getCustomerHierarchyLevel());
        attributes.put("productHierarchySid", getProductHierarchySid());
        attributes.put("cffName", getCffName());
        attributes.put("customerHierarchyInnerLevel",
            getCustomerHierarchyInnerLevel());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("customerHierarchySid", getCustomerHierarchySid());
        attributes.put("companyGroupSid", getCompanyGroupSid());
        attributes.put("prodRelationshipBuilderSid",
            getProdRelationshipBuilderSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("productHierarchyInnerLevel",
            getProductHierarchyInnerLevel());
        attributes.put("itemGroupSid", getItemGroupSid());
        attributes.put("custRelationshipBuilderSid",
            getCustRelationshipBuilderSid());
        attributes.put("companyMasterSid", getCompanyMasterSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer productHierarchyLevel = (Integer) attributes.get(
                "productHierarchyLevel");

        if (productHierarchyLevel != null) {
            setProductHierarchyLevel(productHierarchyLevel);
        }

        Date activeFromDate = (Date) attributes.get("activeFromDate");

        if (activeFromDate != null) {
            setActiveFromDate(activeFromDate);
        }

        Integer cffType = (Integer) attributes.get("cffType");

        if (cffType != null) {
            setCffType(cffType);
        }

        Boolean cffOfficial = (Boolean) attributes.get("cffOfficial");

        if (cffOfficial != null) {
            setCffOfficial(cffOfficial);
        }

        Integer cffMasterSid = (Integer) attributes.get("cffMasterSid");

        if (cffMasterSid != null) {
            setCffMasterSid(cffMasterSid);
        }

        Integer productHierVersionNo = (Integer) attributes.get(
                "productHierVersionNo");

        if (productHierVersionNo != null) {
            setProductHierVersionNo(productHierVersionNo);
        }

        Date activeToDate = (Date) attributes.get("activeToDate");

        if (activeToDate != null) {
            setActiveToDate(activeToDate);
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

        Integer productHierarchySid = (Integer) attributes.get(
                "productHierarchySid");

        if (productHierarchySid != null) {
            setProductHierarchySid(productHierarchySid);
        }

        String cffName = (String) attributes.get("cffName");

        if (cffName != null) {
            setCffName(cffName);
        }

        Integer customerHierarchyInnerLevel = (Integer) attributes.get(
                "customerHierarchyInnerLevel");

        if (customerHierarchyInnerLevel != null) {
            setCustomerHierarchyInnerLevel(customerHierarchyInnerLevel);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer customerHierarchySid = (Integer) attributes.get(
                "customerHierarchySid");

        if (customerHierarchySid != null) {
            setCustomerHierarchySid(customerHierarchySid);
        }

        Integer companyGroupSid = (Integer) attributes.get("companyGroupSid");

        if (companyGroupSid != null) {
            setCompanyGroupSid(companyGroupSid);
        }

        Integer prodRelationshipBuilderSid = (Integer) attributes.get(
                "prodRelationshipBuilderSid");

        if (prodRelationshipBuilderSid != null) {
            setProdRelationshipBuilderSid(prodRelationshipBuilderSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Integer productHierarchyInnerLevel = (Integer) attributes.get(
                "productHierarchyInnerLevel");

        if (productHierarchyInnerLevel != null) {
            setProductHierarchyInnerLevel(productHierarchyInnerLevel);
        }

        Integer itemGroupSid = (Integer) attributes.get("itemGroupSid");

        if (itemGroupSid != null) {
            setItemGroupSid(itemGroupSid);
        }

        Integer custRelationshipBuilderSid = (Integer) attributes.get(
                "custRelationshipBuilderSid");

        if (custRelationshipBuilderSid != null) {
            setCustRelationshipBuilderSid(custRelationshipBuilderSid);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }
    }

    @Override
    public int getProductHierarchyLevel() {
        return _productHierarchyLevel;
    }

    @Override
    public void setProductHierarchyLevel(int productHierarchyLevel) {
        _productHierarchyLevel = productHierarchyLevel;

        if (_cffMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProductHierarchyLevel",
                        int.class);

                method.invoke(_cffMasterRemoteModel, productHierarchyLevel);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getActiveFromDate() {
        return _activeFromDate;
    }

    @Override
    public void setActiveFromDate(Date activeFromDate) {
        _activeFromDate = activeFromDate;

        if (_cffMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setActiveFromDate", Date.class);

                method.invoke(_cffMasterRemoteModel, activeFromDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCffType() {
        return _cffType;
    }

    @Override
    public void setCffType(int cffType) {
        _cffType = cffType;

        if (_cffMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCffType", int.class);

                method.invoke(_cffMasterRemoteModel, cffType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getCffOfficial() {
        return _cffOfficial;
    }

    @Override
    public boolean isCffOfficial() {
        return _cffOfficial;
    }

    @Override
    public void setCffOfficial(boolean cffOfficial) {
        _cffOfficial = cffOfficial;

        if (_cffMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCffOfficial", boolean.class);

                method.invoke(_cffMasterRemoteModel, cffOfficial);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCffMasterSid() {
        return _cffMasterSid;
    }

    @Override
    public void setCffMasterSid(int cffMasterSid) {
        _cffMasterSid = cffMasterSid;

        if (_cffMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCffMasterSid", int.class);

                method.invoke(_cffMasterRemoteModel, cffMasterSid);
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

        if (_cffMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProductHierVersionNo",
                        int.class);

                method.invoke(_cffMasterRemoteModel, productHierVersionNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getActiveToDate() {
        return _activeToDate;
    }

    @Override
    public void setActiveToDate(Date activeToDate) {
        _activeToDate = activeToDate;

        if (_cffMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setActiveToDate", Date.class);

                method.invoke(_cffMasterRemoteModel, activeToDate);
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

        if (_cffMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCustomerHierVersionNo",
                        int.class);

                method.invoke(_cffMasterRemoteModel, customerHierVersionNo);
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

        if (_cffMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_cffMasterRemoteModel, modifiedDate);
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

        if (_cffMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCustomerHierarchyLevel",
                        int.class);

                method.invoke(_cffMasterRemoteModel, customerHierarchyLevel);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getProductHierarchySid() {
        return _productHierarchySid;
    }

    @Override
    public void setProductHierarchySid(int productHierarchySid) {
        _productHierarchySid = productHierarchySid;

        if (_cffMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProductHierarchySid",
                        int.class);

                method.invoke(_cffMasterRemoteModel, productHierarchySid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCffName() {
        return _cffName;
    }

    @Override
    public void setCffName(String cffName) {
        _cffName = cffName;

        if (_cffMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCffName", String.class);

                method.invoke(_cffMasterRemoteModel, cffName);
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

        if (_cffMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCustomerHierarchyInnerLevel",
                        int.class);

                method.invoke(_cffMasterRemoteModel, customerHierarchyInnerLevel);
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

        if (_cffMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_cffMasterRemoteModel, createdDate);
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

        if (_cffMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_cffMasterRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCustomerHierarchySid() {
        return _customerHierarchySid;
    }

    @Override
    public void setCustomerHierarchySid(int customerHierarchySid) {
        _customerHierarchySid = customerHierarchySid;

        if (_cffMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCustomerHierarchySid",
                        int.class);

                method.invoke(_cffMasterRemoteModel, customerHierarchySid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCompanyGroupSid() {
        return _companyGroupSid;
    }

    @Override
    public void setCompanyGroupSid(int companyGroupSid) {
        _companyGroupSid = companyGroupSid;

        if (_cffMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyGroupSid", int.class);

                method.invoke(_cffMasterRemoteModel, companyGroupSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getProdRelationshipBuilderSid() {
        return _prodRelationshipBuilderSid;
    }

    @Override
    public void setProdRelationshipBuilderSid(int prodRelationshipBuilderSid) {
        _prodRelationshipBuilderSid = prodRelationshipBuilderSid;

        if (_cffMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProdRelationshipBuilderSid",
                        int.class);

                method.invoke(_cffMasterRemoteModel, prodRelationshipBuilderSid);
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

        if (_cffMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_cffMasterRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getInboundStatus() {
        return _inboundStatus;
    }

    @Override
    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;

        if (_cffMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_cffMasterRemoteModel, inboundStatus);
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

        if (_cffMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProductHierarchyInnerLevel",
                        int.class);

                method.invoke(_cffMasterRemoteModel, productHierarchyInnerLevel);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getItemGroupSid() {
        return _itemGroupSid;
    }

    @Override
    public void setItemGroupSid(int itemGroupSid) {
        _itemGroupSid = itemGroupSid;

        if (_cffMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemGroupSid", int.class);

                method.invoke(_cffMasterRemoteModel, itemGroupSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCustRelationshipBuilderSid() {
        return _custRelationshipBuilderSid;
    }

    @Override
    public void setCustRelationshipBuilderSid(int custRelationshipBuilderSid) {
        _custRelationshipBuilderSid = custRelationshipBuilderSid;

        if (_cffMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCustRelationshipBuilderSid",
                        int.class);

                method.invoke(_cffMasterRemoteModel, custRelationshipBuilderSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCompanyMasterSid() {
        return _companyMasterSid;
    }

    @Override
    public void setCompanyMasterSid(int companyMasterSid) {
        _companyMasterSid = companyMasterSid;

        if (_cffMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyMasterSid", int.class);

                method.invoke(_cffMasterRemoteModel, companyMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getCffMasterRemoteModel() {
        return _cffMasterRemoteModel;
    }

    public void setCffMasterRemoteModel(BaseModel<?> cffMasterRemoteModel) {
        _cffMasterRemoteModel = cffMasterRemoteModel;
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

        Class<?> remoteModelClass = _cffMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_cffMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            CffMasterLocalServiceUtil.addCffMaster(this);
        } else {
            CffMasterLocalServiceUtil.updateCffMaster(this);
        }
    }

    @Override
    public CffMaster toEscapedModel() {
        return (CffMaster) ProxyUtil.newProxyInstance(CffMaster.class.getClassLoader(),
            new Class[] { CffMaster.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CffMasterClp clone = new CffMasterClp();

        clone.setProductHierarchyLevel(getProductHierarchyLevel());
        clone.setActiveFromDate(getActiveFromDate());
        clone.setCffType(getCffType());
        clone.setCffOfficial(getCffOfficial());
        clone.setCffMasterSid(getCffMasterSid());
        clone.setProductHierVersionNo(getProductHierVersionNo());
        clone.setActiveToDate(getActiveToDate());
        clone.setCustomerHierVersionNo(getCustomerHierVersionNo());
        clone.setModifiedDate(getModifiedDate());
        clone.setCustomerHierarchyLevel(getCustomerHierarchyLevel());
        clone.setProductHierarchySid(getProductHierarchySid());
        clone.setCffName(getCffName());
        clone.setCustomerHierarchyInnerLevel(getCustomerHierarchyInnerLevel());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setCustomerHierarchySid(getCustomerHierarchySid());
        clone.setCompanyGroupSid(getCompanyGroupSid());
        clone.setProdRelationshipBuilderSid(getProdRelationshipBuilderSid());
        clone.setModifiedBy(getModifiedBy());
        clone.setInboundStatus(getInboundStatus());
        clone.setProductHierarchyInnerLevel(getProductHierarchyInnerLevel());
        clone.setItemGroupSid(getItemGroupSid());
        clone.setCustRelationshipBuilderSid(getCustRelationshipBuilderSid());
        clone.setCompanyMasterSid(getCompanyMasterSid());

        return clone;
    }

    @Override
    public int compareTo(CffMaster cffMaster) {
        int primaryKey = cffMaster.getPrimaryKey();

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

        if (!(obj instanceof CffMasterClp)) {
            return false;
        }

        CffMasterClp cffMaster = (CffMasterClp) obj;

        int primaryKey = cffMaster.getPrimaryKey();

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
        StringBundler sb = new StringBundler(49);

        sb.append("{productHierarchyLevel=");
        sb.append(getProductHierarchyLevel());
        sb.append(", activeFromDate=");
        sb.append(getActiveFromDate());
        sb.append(", cffType=");
        sb.append(getCffType());
        sb.append(", cffOfficial=");
        sb.append(getCffOfficial());
        sb.append(", cffMasterSid=");
        sb.append(getCffMasterSid());
        sb.append(", productHierVersionNo=");
        sb.append(getProductHierVersionNo());
        sb.append(", activeToDate=");
        sb.append(getActiveToDate());
        sb.append(", customerHierVersionNo=");
        sb.append(getCustomerHierVersionNo());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", customerHierarchyLevel=");
        sb.append(getCustomerHierarchyLevel());
        sb.append(", productHierarchySid=");
        sb.append(getProductHierarchySid());
        sb.append(", cffName=");
        sb.append(getCffName());
        sb.append(", customerHierarchyInnerLevel=");
        sb.append(getCustomerHierarchyInnerLevel());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", customerHierarchySid=");
        sb.append(getCustomerHierarchySid());
        sb.append(", companyGroupSid=");
        sb.append(getCompanyGroupSid());
        sb.append(", prodRelationshipBuilderSid=");
        sb.append(getProdRelationshipBuilderSid());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", productHierarchyInnerLevel=");
        sb.append(getProductHierarchyInnerLevel());
        sb.append(", itemGroupSid=");
        sb.append(getItemGroupSid());
        sb.append(", custRelationshipBuilderSid=");
        sb.append(getCustRelationshipBuilderSid());
        sb.append(", companyMasterSid=");
        sb.append(getCompanyMasterSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(76);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.CffMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>productHierarchyLevel</column-name><column-value><![CDATA[");
        sb.append(getProductHierarchyLevel());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>activeFromDate</column-name><column-value><![CDATA[");
        sb.append(getActiveFromDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cffType</column-name><column-value><![CDATA[");
        sb.append(getCffType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cffOfficial</column-name><column-value><![CDATA[");
        sb.append(getCffOfficial());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cffMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCffMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>productHierVersionNo</column-name><column-value><![CDATA[");
        sb.append(getProductHierVersionNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>activeToDate</column-name><column-value><![CDATA[");
        sb.append(getActiveToDate());
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
            "<column><column-name>productHierarchySid</column-name><column-value><![CDATA[");
        sb.append(getProductHierarchySid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cffName</column-name><column-value><![CDATA[");
        sb.append(getCffName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>customerHierarchyInnerLevel</column-name><column-value><![CDATA[");
        sb.append(getCustomerHierarchyInnerLevel());
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
            "<column><column-name>prodRelationshipBuilderSid</column-name><column-value><![CDATA[");
        sb.append(getProdRelationshipBuilderSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>productHierarchyInnerLevel</column-name><column-value><![CDATA[");
        sb.append(getProductHierarchyInnerLevel());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemGroupSid</column-name><column-value><![CDATA[");
        sb.append(getItemGroupSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>custRelationshipBuilderSid</column-name><column-value><![CDATA[");
        sb.append(getCustRelationshipBuilderSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyMasterSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
