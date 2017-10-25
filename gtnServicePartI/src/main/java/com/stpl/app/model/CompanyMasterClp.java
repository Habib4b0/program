package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;

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


public class CompanyMasterClp extends BaseModelImpl<CompanyMaster>
    implements CompanyMaster {
    private int _state;
    private String _financialSystem;
    private String _companyGroup;
    private String _companyName;
    private int _companyCategory;
    private Date _lastUpdatedDate;
    private Date _modifiedDate;
    private int _lives;
    private String _address2;
    private Date _createdDate;
    private int _createdBy;
    private String _source;
    private String _address1;
    private int _modifiedBy;
    private String _inboundStatus;
    private int _companyMasterSid;
    private String _zipCode;
    private String _companyId;
    private int _country;
    private String _internalNotes;
    private String _orgKey;
    private int _companyType;
    private boolean _recordLockStatus;
    private Date _companyStartDate;
    private String _companyNo;
    private String _batchId;
    private int _companyStatus;
    private Date _companyEndDate;
    private String _city;
    private String _regionCode;
    private BaseModel<?> _companyMasterRemoteModel;

    public CompanyMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return CompanyMaster.class;
    }

    @Override
    public String getModelClassName() {
        return CompanyMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _companyMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setCompanyMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _companyMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("state", getState());
        attributes.put("financialSystem", getFinancialSystem());
        attributes.put("companyGroup", getCompanyGroup());
        attributes.put("companyName", getCompanyName());
        attributes.put("companyCategory", getCompanyCategory());
        attributes.put("lastUpdatedDate", getLastUpdatedDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("lives", getLives());
        attributes.put("address2", getAddress2());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("address1", getAddress1());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("companyMasterSid", getCompanyMasterSid());
        attributes.put("zipCode", getZipCode());
        attributes.put("companyId", getCompanyId());
        attributes.put("country", getCountry());
        attributes.put("internalNotes", getInternalNotes());
        attributes.put("orgKey", getOrgKey());
        attributes.put("companyType", getCompanyType());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("companyStartDate", getCompanyStartDate());
        attributes.put("companyNo", getCompanyNo());
        attributes.put("batchId", getBatchId());
        attributes.put("companyStatus", getCompanyStatus());
        attributes.put("companyEndDate", getCompanyEndDate());
        attributes.put("city", getCity());
        attributes.put("regionCode", getRegionCode());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer state = (Integer) attributes.get("state");

        if (state != null) {
            setState(state);
        }

        String financialSystem = (String) attributes.get("financialSystem");

        if (financialSystem != null) {
            setFinancialSystem(financialSystem);
        }

        String companyGroup = (String) attributes.get("companyGroup");

        if (companyGroup != null) {
            setCompanyGroup(companyGroup);
        }

        String companyName = (String) attributes.get("companyName");

        if (companyName != null) {
            setCompanyName(companyName);
        }

        Integer companyCategory = (Integer) attributes.get("companyCategory");

        if (companyCategory != null) {
            setCompanyCategory(companyCategory);
        }

        Date lastUpdatedDate = (Date) attributes.get("lastUpdatedDate");

        if (lastUpdatedDate != null) {
            setLastUpdatedDate(lastUpdatedDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer lives = (Integer) attributes.get("lives");

        if (lives != null) {
            setLives(lives);
        }

        String address2 = (String) attributes.get("address2");

        if (address2 != null) {
            setAddress2(address2);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String address1 = (String) attributes.get("address1");

        if (address1 != null) {
            setAddress1(address1);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }

        String zipCode = (String) attributes.get("zipCode");

        if (zipCode != null) {
            setZipCode(zipCode);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        Integer country = (Integer) attributes.get("country");

        if (country != null) {
            setCountry(country);
        }

        String internalNotes = (String) attributes.get("internalNotes");

        if (internalNotes != null) {
            setInternalNotes(internalNotes);
        }

        String orgKey = (String) attributes.get("orgKey");

        if (orgKey != null) {
            setOrgKey(orgKey);
        }

        Integer companyType = (Integer) attributes.get("companyType");

        if (companyType != null) {
            setCompanyType(companyType);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        Date companyStartDate = (Date) attributes.get("companyStartDate");

        if (companyStartDate != null) {
            setCompanyStartDate(companyStartDate);
        }

        String companyNo = (String) attributes.get("companyNo");

        if (companyNo != null) {
            setCompanyNo(companyNo);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Integer companyStatus = (Integer) attributes.get("companyStatus");

        if (companyStatus != null) {
            setCompanyStatus(companyStatus);
        }

        Date companyEndDate = (Date) attributes.get("companyEndDate");

        if (companyEndDate != null) {
            setCompanyEndDate(companyEndDate);
        }

        String city = (String) attributes.get("city");

        if (city != null) {
            setCity(city);
        }

        String regionCode = (String) attributes.get("regionCode");

        if (regionCode != null) {
            setRegionCode(regionCode);
        }
    }

    @Override
    public int getState() {
        return _state;
    }

    @Override
    public void setState(int state) {
        _state = state;

        if (_companyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _companyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setState", int.class);

                method.invoke(_companyMasterRemoteModel, state);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFinancialSystem() {
        return _financialSystem;
    }

    @Override
    public void setFinancialSystem(String financialSystem) {
        _financialSystem = financialSystem;

        if (_companyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _companyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setFinancialSystem",
                        String.class);

                method.invoke(_companyMasterRemoteModel, financialSystem);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyGroup() {
        return _companyGroup;
    }

    @Override
    public void setCompanyGroup(String companyGroup) {
        _companyGroup = companyGroup;

        if (_companyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _companyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyGroup", String.class);

                method.invoke(_companyMasterRemoteModel, companyGroup);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyName() {
        return _companyName;
    }

    @Override
    public void setCompanyName(String companyName) {
        _companyName = companyName;

        if (_companyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _companyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyName", String.class);

                method.invoke(_companyMasterRemoteModel, companyName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCompanyCategory() {
        return _companyCategory;
    }

    @Override
    public void setCompanyCategory(int companyCategory) {
        _companyCategory = companyCategory;

        if (_companyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _companyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyCategory", int.class);

                method.invoke(_companyMasterRemoteModel, companyCategory);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getLastUpdatedDate() {
        return _lastUpdatedDate;
    }

    @Override
    public void setLastUpdatedDate(Date lastUpdatedDate) {
        _lastUpdatedDate = lastUpdatedDate;

        if (_companyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _companyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLastUpdatedDate", Date.class);

                method.invoke(_companyMasterRemoteModel, lastUpdatedDate);
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

        if (_companyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _companyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_companyMasterRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getLives() {
        return _lives;
    }

    @Override
    public void setLives(int lives) {
        _lives = lives;

        if (_companyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _companyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLives", int.class);

                method.invoke(_companyMasterRemoteModel, lives);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAddress2() {
        return _address2;
    }

    @Override
    public void setAddress2(String address2) {
        _address2 = address2;

        if (_companyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _companyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAddress2", String.class);

                method.invoke(_companyMasterRemoteModel, address2);
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

        if (_companyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _companyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_companyMasterRemoteModel, createdDate);
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

        if (_companyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _companyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_companyMasterRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSource() {
        return _source;
    }

    @Override
    public void setSource(String source) {
        _source = source;

        if (_companyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _companyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_companyMasterRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAddress1() {
        return _address1;
    }

    @Override
    public void setAddress1(String address1) {
        _address1 = address1;

        if (_companyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _companyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAddress1", String.class);

                method.invoke(_companyMasterRemoteModel, address1);
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

        if (_companyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _companyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_companyMasterRemoteModel, modifiedBy);
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

        if (_companyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _companyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_companyMasterRemoteModel, inboundStatus);
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

        if (_companyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _companyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyMasterSid", int.class);

                method.invoke(_companyMasterRemoteModel, companyMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getZipCode() {
        return _zipCode;
    }

    @Override
    public void setZipCode(String zipCode) {
        _zipCode = zipCode;

        if (_companyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _companyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setZipCode", String.class);

                method.invoke(_companyMasterRemoteModel, zipCode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyId() {
        return _companyId;
    }

    @Override
    public void setCompanyId(String companyId) {
        _companyId = companyId;

        if (_companyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _companyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyId", String.class);

                method.invoke(_companyMasterRemoteModel, companyId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCountry() {
        return _country;
    }

    @Override
    public void setCountry(int country) {
        _country = country;

        if (_companyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _companyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCountry", int.class);

                method.invoke(_companyMasterRemoteModel, country);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getInternalNotes() {
        return _internalNotes;
    }

    @Override
    public void setInternalNotes(String internalNotes) {
        _internalNotes = internalNotes;

        if (_companyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _companyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInternalNotes", String.class);

                method.invoke(_companyMasterRemoteModel, internalNotes);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getOrgKey() {
        return _orgKey;
    }

    @Override
    public void setOrgKey(String orgKey) {
        _orgKey = orgKey;

        if (_companyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _companyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setOrgKey", String.class);

                method.invoke(_companyMasterRemoteModel, orgKey);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCompanyType() {
        return _companyType;
    }

    @Override
    public void setCompanyType(int companyType) {
        _companyType = companyType;

        if (_companyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _companyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyType", int.class);

                method.invoke(_companyMasterRemoteModel, companyType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getRecordLockStatus() {
        return _recordLockStatus;
    }

    @Override
    public boolean isRecordLockStatus() {
        return _recordLockStatus;
    }

    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _recordLockStatus = recordLockStatus;

        if (_companyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _companyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_companyMasterRemoteModel, recordLockStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCompanyStartDate() {
        return _companyStartDate;
    }

    @Override
    public void setCompanyStartDate(Date companyStartDate) {
        _companyStartDate = companyStartDate;

        if (_companyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _companyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyStartDate",
                        Date.class);

                method.invoke(_companyMasterRemoteModel, companyStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyNo() {
        return _companyNo;
    }

    @Override
    public void setCompanyNo(String companyNo) {
        _companyNo = companyNo;

        if (_companyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _companyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyNo", String.class);

                method.invoke(_companyMasterRemoteModel, companyNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBatchId() {
        return _batchId;
    }

    @Override
    public void setBatchId(String batchId) {
        _batchId = batchId;

        if (_companyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _companyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_companyMasterRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCompanyStatus() {
        return _companyStatus;
    }

    @Override
    public void setCompanyStatus(int companyStatus) {
        _companyStatus = companyStatus;

        if (_companyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _companyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyStatus", int.class);

                method.invoke(_companyMasterRemoteModel, companyStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCompanyEndDate() {
        return _companyEndDate;
    }

    @Override
    public void setCompanyEndDate(Date companyEndDate) {
        _companyEndDate = companyEndDate;

        if (_companyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _companyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyEndDate", Date.class);

                method.invoke(_companyMasterRemoteModel, companyEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCity() {
        return _city;
    }

    @Override
    public void setCity(String city) {
        _city = city;

        if (_companyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _companyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCity", String.class);

                method.invoke(_companyMasterRemoteModel, city);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRegionCode() {
        return _regionCode;
    }

    @Override
    public void setRegionCode(String regionCode) {
        _regionCode = regionCode;

        if (_companyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _companyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRegionCode", String.class);

                method.invoke(_companyMasterRemoteModel, regionCode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getCompanyMasterRemoteModel() {
        return _companyMasterRemoteModel;
    }

    public void setCompanyMasterRemoteModel(
        BaseModel<?> companyMasterRemoteModel) {
        _companyMasterRemoteModel = companyMasterRemoteModel;
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

        Class<?> remoteModelClass = _companyMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_companyMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            CompanyMasterLocalServiceUtil.addCompanyMaster(this);
        } else {
            CompanyMasterLocalServiceUtil.updateCompanyMaster(this);
        }
    }

    @Override
    public CompanyMaster toEscapedModel() {
        return (CompanyMaster) ProxyUtil.newProxyInstance(CompanyMaster.class.getClassLoader(),
            new Class[] { CompanyMaster.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CompanyMasterClp clone = new CompanyMasterClp();

        clone.setState(getState());
        clone.setFinancialSystem(getFinancialSystem());
        clone.setCompanyGroup(getCompanyGroup());
        clone.setCompanyName(getCompanyName());
        clone.setCompanyCategory(getCompanyCategory());
        clone.setLastUpdatedDate(getLastUpdatedDate());
        clone.setModifiedDate(getModifiedDate());
        clone.setLives(getLives());
        clone.setAddress2(getAddress2());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setSource(getSource());
        clone.setAddress1(getAddress1());
        clone.setModifiedBy(getModifiedBy());
        clone.setInboundStatus(getInboundStatus());
        clone.setCompanyMasterSid(getCompanyMasterSid());
        clone.setZipCode(getZipCode());
        clone.setCompanyId(getCompanyId());
        clone.setCountry(getCountry());
        clone.setInternalNotes(getInternalNotes());
        clone.setOrgKey(getOrgKey());
        clone.setCompanyType(getCompanyType());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setCompanyStartDate(getCompanyStartDate());
        clone.setCompanyNo(getCompanyNo());
        clone.setBatchId(getBatchId());
        clone.setCompanyStatus(getCompanyStatus());
        clone.setCompanyEndDate(getCompanyEndDate());
        clone.setCity(getCity());
        clone.setRegionCode(getRegionCode());

        return clone;
    }

    @Override
    public int compareTo(CompanyMaster companyMaster) {
        int primaryKey = companyMaster.getPrimaryKey();

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

        if (!(obj instanceof CompanyMasterClp)) {
            return false;
        }

        CompanyMasterClp companyMaster = (CompanyMasterClp) obj;

        int primaryKey = companyMaster.getPrimaryKey();

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

        sb.append("{state=");
        sb.append(getState());
        sb.append(", financialSystem=");
        sb.append(getFinancialSystem());
        sb.append(", companyGroup=");
        sb.append(getCompanyGroup());
        sb.append(", companyName=");
        sb.append(getCompanyName());
        sb.append(", companyCategory=");
        sb.append(getCompanyCategory());
        sb.append(", lastUpdatedDate=");
        sb.append(getLastUpdatedDate());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", lives=");
        sb.append(getLives());
        sb.append(", address2=");
        sb.append(getAddress2());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", address1=");
        sb.append(getAddress1());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", companyMasterSid=");
        sb.append(getCompanyMasterSid());
        sb.append(", zipCode=");
        sb.append(getZipCode());
        sb.append(", companyId=");
        sb.append(getCompanyId());
        sb.append(", country=");
        sb.append(getCountry());
        sb.append(", internalNotes=");
        sb.append(getInternalNotes());
        sb.append(", orgKey=");
        sb.append(getOrgKey());
        sb.append(", companyType=");
        sb.append(getCompanyType());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", companyStartDate=");
        sb.append(getCompanyStartDate());
        sb.append(", companyNo=");
        sb.append(getCompanyNo());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", companyStatus=");
        sb.append(getCompanyStatus());
        sb.append(", companyEndDate=");
        sb.append(getCompanyEndDate());
        sb.append(", city=");
        sb.append(getCity());
        sb.append(", regionCode=");
        sb.append(getRegionCode());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(94);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.CompanyMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>state</column-name><column-value><![CDATA[");
        sb.append(getState());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>financialSystem</column-name><column-value><![CDATA[");
        sb.append(getFinancialSystem());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyGroup</column-name><column-value><![CDATA[");
        sb.append(getCompanyGroup());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyName</column-name><column-value><![CDATA[");
        sb.append(getCompanyName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyCategory</column-name><column-value><![CDATA[");
        sb.append(getCompanyCategory());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lastUpdatedDate</column-name><column-value><![CDATA[");
        sb.append(getLastUpdatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lives</column-name><column-value><![CDATA[");
        sb.append(getLives());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>address2</column-name><column-value><![CDATA[");
        sb.append(getAddress2());
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
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>address1</column-name><column-value><![CDATA[");
        sb.append(getAddress1());
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
            "<column><column-name>companyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>zipCode</column-name><column-value><![CDATA[");
        sb.append(getZipCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyId</column-name><column-value><![CDATA[");
        sb.append(getCompanyId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>country</column-name><column-value><![CDATA[");
        sb.append(getCountry());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>internalNotes</column-name><column-value><![CDATA[");
        sb.append(getInternalNotes());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>orgKey</column-name><column-value><![CDATA[");
        sb.append(getOrgKey());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyType</column-name><column-value><![CDATA[");
        sb.append(getCompanyType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyStartDate</column-name><column-value><![CDATA[");
        sb.append(getCompanyStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyNo</column-name><column-value><![CDATA[");
        sb.append(getCompanyNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyStatus</column-name><column-value><![CDATA[");
        sb.append(getCompanyStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyEndDate</column-name><column-value><![CDATA[");
        sb.append(getCompanyEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>city</column-name><column-value><![CDATA[");
        sb.append(getCity());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>regionCode</column-name><column-value><![CDATA[");
        sb.append(getRegionCode());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
