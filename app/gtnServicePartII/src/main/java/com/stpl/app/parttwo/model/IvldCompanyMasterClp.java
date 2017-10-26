package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.ClpSerializer;
import com.stpl.app.parttwo.service.IvldCompanyMasterLocalServiceUtil;

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


public class IvldCompanyMasterClp extends BaseModelImpl<IvldCompanyMaster>
    implements IvldCompanyMaster {
    private String _state;
    private String _financialSystem;
    private String _companyName;
    private String _companyGroup;
    private String _companyCategory;
    private String _lastUpdatedDate;
    private Date _modifiedDate;
    private String _status;
    private int _ivldCompanyMasterSid;
    private String _lives;
    private String _organizationKey;
    private String _source;
    private Date _createdDate;
    private String _createdBy;
    private String _addChgDelIndicator;
    private String _errorCode;
    private Date _intfInsertedDate;
    private String _modifiedBy;
    private String _companyMasterIntfid;
    private String _reprocessedFlag;
    private String _udc6;
    private String _udc5;
    private String _udc4;
    private String _udc1;
    private String _udc2;
    private String _zipCode;
    private String _udc3;
    private String _reasonForFailure;
    private String _companyId;
    private String _address1;
    private String _country;
    private String _address2;
    private String _companyType;
    private String _companyStartDate;
    private String _companyNo;
    private String _batchId;
    private String _companyStatus;
    private String _companyEndDate;
    private String _errorField;
    private String _city;
    private String _regionCode;
    private boolean _checkRecord;
    private BaseModel<?> _ivldCompanyMasterRemoteModel;

    public IvldCompanyMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return IvldCompanyMaster.class;
    }

    @Override
    public String getModelClassName() {
        return IvldCompanyMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _ivldCompanyMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIvldCompanyMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ivldCompanyMasterSid;
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
        attributes.put("companyName", getCompanyName());
        attributes.put("companyGroup", getCompanyGroup());
        attributes.put("companyCategory", getCompanyCategory());
        attributes.put("lastUpdatedDate", getLastUpdatedDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("status", getStatus());
        attributes.put("ivldCompanyMasterSid", getIvldCompanyMasterSid());
        attributes.put("lives", getLives());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("source", getSource());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("errorCode", getErrorCode());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("companyMasterIntfid", getCompanyMasterIntfid());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("udc6", getUdc6());
        attributes.put("udc5", getUdc5());
        attributes.put("udc4", getUdc4());
        attributes.put("udc1", getUdc1());
        attributes.put("udc2", getUdc2());
        attributes.put("zipCode", getZipCode());
        attributes.put("udc3", getUdc3());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("companyId", getCompanyId());
        attributes.put("address1", getAddress1());
        attributes.put("country", getCountry());
        attributes.put("address2", getAddress2());
        attributes.put("companyType", getCompanyType());
        attributes.put("companyStartDate", getCompanyStartDate());
        attributes.put("companyNo", getCompanyNo());
        attributes.put("batchId", getBatchId());
        attributes.put("companyStatus", getCompanyStatus());
        attributes.put("companyEndDate", getCompanyEndDate());
        attributes.put("errorField", getErrorField());
        attributes.put("city", getCity());
        attributes.put("regionCode", getRegionCode());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String state = (String) attributes.get("state");

        if (state != null) {
            setState(state);
        }

        String financialSystem = (String) attributes.get("financialSystem");

        if (financialSystem != null) {
            setFinancialSystem(financialSystem);
        }

        String companyName = (String) attributes.get("companyName");

        if (companyName != null) {
            setCompanyName(companyName);
        }

        String companyGroup = (String) attributes.get("companyGroup");

        if (companyGroup != null) {
            setCompanyGroup(companyGroup);
        }

        String companyCategory = (String) attributes.get("companyCategory");

        if (companyCategory != null) {
            setCompanyCategory(companyCategory);
        }

        String lastUpdatedDate = (String) attributes.get("lastUpdatedDate");

        if (lastUpdatedDate != null) {
            setLastUpdatedDate(lastUpdatedDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String status = (String) attributes.get("status");

        if (status != null) {
            setStatus(status);
        }

        Integer ivldCompanyMasterSid = (Integer) attributes.get(
                "ivldCompanyMasterSid");

        if (ivldCompanyMasterSid != null) {
            setIvldCompanyMasterSid(ivldCompanyMasterSid);
        }

        String lives = (String) attributes.get("lives");

        if (lives != null) {
            setLives(lives);
        }

        String organizationKey = (String) attributes.get("organizationKey");

        if (organizationKey != null) {
            setOrganizationKey(organizationKey);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        Date intfInsertedDate = (Date) attributes.get("intfInsertedDate");

        if (intfInsertedDate != null) {
            setIntfInsertedDate(intfInsertedDate);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String companyMasterIntfid = (String) attributes.get(
                "companyMasterIntfid");

        if (companyMasterIntfid != null) {
            setCompanyMasterIntfid(companyMasterIntfid);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String udc6 = (String) attributes.get("udc6");

        if (udc6 != null) {
            setUdc6(udc6);
        }

        String udc5 = (String) attributes.get("udc5");

        if (udc5 != null) {
            setUdc5(udc5);
        }

        String udc4 = (String) attributes.get("udc4");

        if (udc4 != null) {
            setUdc4(udc4);
        }

        String udc1 = (String) attributes.get("udc1");

        if (udc1 != null) {
            setUdc1(udc1);
        }

        String udc2 = (String) attributes.get("udc2");

        if (udc2 != null) {
            setUdc2(udc2);
        }

        String zipCode = (String) attributes.get("zipCode");

        if (zipCode != null) {
            setZipCode(zipCode);
        }

        String udc3 = (String) attributes.get("udc3");

        if (udc3 != null) {
            setUdc3(udc3);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        String address1 = (String) attributes.get("address1");

        if (address1 != null) {
            setAddress1(address1);
        }

        String country = (String) attributes.get("country");

        if (country != null) {
            setCountry(country);
        }

        String address2 = (String) attributes.get("address2");

        if (address2 != null) {
            setAddress2(address2);
        }

        String companyType = (String) attributes.get("companyType");

        if (companyType != null) {
            setCompanyType(companyType);
        }

        String companyStartDate = (String) attributes.get("companyStartDate");

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

        String companyStatus = (String) attributes.get("companyStatus");

        if (companyStatus != null) {
            setCompanyStatus(companyStatus);
        }

        String companyEndDate = (String) attributes.get("companyEndDate");

        if (companyEndDate != null) {
            setCompanyEndDate(companyEndDate);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        String city = (String) attributes.get("city");

        if (city != null) {
            setCity(city);
        }

        String regionCode = (String) attributes.get("regionCode");

        if (regionCode != null) {
            setRegionCode(regionCode);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    @Override
    public String getState() {
        return _state;
    }

    @Override
    public void setState(String state) {
        _state = state;

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setState", String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, state);
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

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setFinancialSystem",
                        String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, financialSystem);
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

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyName", String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, companyName);
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

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyGroup", String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, companyGroup);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyCategory() {
        return _companyCategory;
    }

    @Override
    public void setCompanyCategory(String companyCategory) {
        _companyCategory = companyCategory;

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyCategory",
                        String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, companyCategory);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLastUpdatedDate() {
        return _lastUpdatedDate;
    }

    @Override
    public void setLastUpdatedDate(String lastUpdatedDate) {
        _lastUpdatedDate = lastUpdatedDate;

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLastUpdatedDate",
                        String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, lastUpdatedDate);
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

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_ivldCompanyMasterRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getStatus() {
        return _status;
    }

    @Override
    public void setStatus(String status) {
        _status = status;

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setStatus", String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, status);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIvldCompanyMasterSid() {
        return _ivldCompanyMasterSid;
    }

    @Override
    public void setIvldCompanyMasterSid(int ivldCompanyMasterSid) {
        _ivldCompanyMasterSid = ivldCompanyMasterSid;

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setIvldCompanyMasterSid",
                        int.class);

                method.invoke(_ivldCompanyMasterRemoteModel,
                    ivldCompanyMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLives() {
        return _lives;
    }

    @Override
    public void setLives(String lives) {
        _lives = lives;

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLives", String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, lives);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getOrganizationKey() {
        return _organizationKey;
    }

    @Override
    public void setOrganizationKey(String organizationKey) {
        _organizationKey = organizationKey;

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setOrganizationKey",
                        String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, organizationKey);
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

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, source);
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

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_ivldCompanyMasterRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCreatedBy() {
        return _createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        _createdBy = createdBy;

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAddChgDelIndicator() {
        return _addChgDelIndicator;
    }

    @Override
    public void setAddChgDelIndicator(String addChgDelIndicator) {
        _addChgDelIndicator = addChgDelIndicator;

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, addChgDelIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getErrorCode() {
        return _errorCode;
    }

    @Override
    public void setErrorCode(String errorCode) {
        _errorCode = errorCode;

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorCode", String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, errorCode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getIntfInsertedDate() {
        return _intfInsertedDate;
    }

    @Override
    public void setIntfInsertedDate(Date intfInsertedDate) {
        _intfInsertedDate = intfInsertedDate;

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setIntfInsertedDate",
                        Date.class);

                method.invoke(_ivldCompanyMasterRemoteModel, intfInsertedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getModifiedBy() {
        return _modifiedBy;
    }

    @Override
    public void setModifiedBy(String modifiedBy) {
        _modifiedBy = modifiedBy;

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyMasterIntfid() {
        return _companyMasterIntfid;
    }

    @Override
    public void setCompanyMasterIntfid(String companyMasterIntfid) {
        _companyMasterIntfid = companyMasterIntfid;

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyMasterIntfid",
                        String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, companyMasterIntfid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getReprocessedFlag() {
        return _reprocessedFlag;
    }

    @Override
    public void setReprocessedFlag(String reprocessedFlag) {
        _reprocessedFlag = reprocessedFlag;

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setReprocessedFlag",
                        String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, reprocessedFlag);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUdc6() {
        return _udc6;
    }

    @Override
    public void setUdc6(String udc6) {
        _udc6 = udc6;

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc6", String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, udc6);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUdc5() {
        return _udc5;
    }

    @Override
    public void setUdc5(String udc5) {
        _udc5 = udc5;

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc5", String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, udc5);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUdc4() {
        return _udc4;
    }

    @Override
    public void setUdc4(String udc4) {
        _udc4 = udc4;

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc4", String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, udc4);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUdc1() {
        return _udc1;
    }

    @Override
    public void setUdc1(String udc1) {
        _udc1 = udc1;

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc1", String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, udc1);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUdc2() {
        return _udc2;
    }

    @Override
    public void setUdc2(String udc2) {
        _udc2 = udc2;

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc2", String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, udc2);
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

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setZipCode", String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, zipCode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUdc3() {
        return _udc3;
    }

    @Override
    public void setUdc3(String udc3) {
        _udc3 = udc3;

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc3", String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, udc3);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getReasonForFailure() {
        return _reasonForFailure;
    }

    @Override
    public void setReasonForFailure(String reasonForFailure) {
        _reasonForFailure = reasonForFailure;

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setReasonForFailure",
                        String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, reasonForFailure);
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

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyId", String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, companyId);
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

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAddress1", String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, address1);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCountry() {
        return _country;
    }

    @Override
    public void setCountry(String country) {
        _country = country;

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCountry", String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, country);
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

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAddress2", String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, address2);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyType() {
        return _companyType;
    }

    @Override
    public void setCompanyType(String companyType) {
        _companyType = companyType;

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyType", String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, companyType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyStartDate() {
        return _companyStartDate;
    }

    @Override
    public void setCompanyStartDate(String companyStartDate) {
        _companyStartDate = companyStartDate;

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyStartDate",
                        String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, companyStartDate);
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

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyNo", String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, companyNo);
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

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyStatus() {
        return _companyStatus;
    }

    @Override
    public void setCompanyStatus(String companyStatus) {
        _companyStatus = companyStatus;

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyStatus", String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, companyStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyEndDate() {
        return _companyEndDate;
    }

    @Override
    public void setCompanyEndDate(String companyEndDate) {
        _companyEndDate = companyEndDate;

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyEndDate",
                        String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, companyEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getErrorField() {
        return _errorField;
    }

    @Override
    public void setErrorField(String errorField) {
        _errorField = errorField;

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorField", String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, errorField);
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

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCity", String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, city);
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

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRegionCode", String.class);

                method.invoke(_ivldCompanyMasterRemoteModel, regionCode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getCheckRecord() {
        return _checkRecord;
    }

    @Override
    public boolean isCheckRecord() {
        return _checkRecord;
    }

    @Override
    public void setCheckRecord(boolean checkRecord) {
        _checkRecord = checkRecord;

        if (_ivldCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_ivldCompanyMasterRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getIvldCompanyMasterRemoteModel() {
        return _ivldCompanyMasterRemoteModel;
    }

    public void setIvldCompanyMasterRemoteModel(
        BaseModel<?> ivldCompanyMasterRemoteModel) {
        _ivldCompanyMasterRemoteModel = ivldCompanyMasterRemoteModel;
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

        Class<?> remoteModelClass = _ivldCompanyMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_ivldCompanyMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IvldCompanyMasterLocalServiceUtil.addIvldCompanyMaster(this);
        } else {
            IvldCompanyMasterLocalServiceUtil.updateIvldCompanyMaster(this);
        }
    }

    @Override
    public IvldCompanyMaster toEscapedModel() {
        return (IvldCompanyMaster) ProxyUtil.newProxyInstance(IvldCompanyMaster.class.getClassLoader(),
            new Class[] { IvldCompanyMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        IvldCompanyMasterClp clone = new IvldCompanyMasterClp();

        clone.setState(getState());
        clone.setFinancialSystem(getFinancialSystem());
        clone.setCompanyName(getCompanyName());
        clone.setCompanyGroup(getCompanyGroup());
        clone.setCompanyCategory(getCompanyCategory());
        clone.setLastUpdatedDate(getLastUpdatedDate());
        clone.setModifiedDate(getModifiedDate());
        clone.setStatus(getStatus());
        clone.setIvldCompanyMasterSid(getIvldCompanyMasterSid());
        clone.setLives(getLives());
        clone.setOrganizationKey(getOrganizationKey());
        clone.setSource(getSource());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setErrorCode(getErrorCode());
        clone.setIntfInsertedDate(getIntfInsertedDate());
        clone.setModifiedBy(getModifiedBy());
        clone.setCompanyMasterIntfid(getCompanyMasterIntfid());
        clone.setReprocessedFlag(getReprocessedFlag());
        clone.setUdc6(getUdc6());
        clone.setUdc5(getUdc5());
        clone.setUdc4(getUdc4());
        clone.setUdc1(getUdc1());
        clone.setUdc2(getUdc2());
        clone.setZipCode(getZipCode());
        clone.setUdc3(getUdc3());
        clone.setReasonForFailure(getReasonForFailure());
        clone.setCompanyId(getCompanyId());
        clone.setAddress1(getAddress1());
        clone.setCountry(getCountry());
        clone.setAddress2(getAddress2());
        clone.setCompanyType(getCompanyType());
        clone.setCompanyStartDate(getCompanyStartDate());
        clone.setCompanyNo(getCompanyNo());
        clone.setBatchId(getBatchId());
        clone.setCompanyStatus(getCompanyStatus());
        clone.setCompanyEndDate(getCompanyEndDate());
        clone.setErrorField(getErrorField());
        clone.setCity(getCity());
        clone.setRegionCode(getRegionCode());
        clone.setCheckRecord(getCheckRecord());

        return clone;
    }

    @Override
    public int compareTo(IvldCompanyMaster ivldCompanyMaster) {
        int primaryKey = ivldCompanyMaster.getPrimaryKey();

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

        if (!(obj instanceof IvldCompanyMasterClp)) {
            return false;
        }

        IvldCompanyMasterClp ivldCompanyMaster = (IvldCompanyMasterClp) obj;

        int primaryKey = ivldCompanyMaster.getPrimaryKey();

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
        StringBundler sb = new StringBundler(85);

        sb.append("{state=");
        sb.append(getState());
        sb.append(", financialSystem=");
        sb.append(getFinancialSystem());
        sb.append(", companyName=");
        sb.append(getCompanyName());
        sb.append(", companyGroup=");
        sb.append(getCompanyGroup());
        sb.append(", companyCategory=");
        sb.append(getCompanyCategory());
        sb.append(", lastUpdatedDate=");
        sb.append(getLastUpdatedDate());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", status=");
        sb.append(getStatus());
        sb.append(", ivldCompanyMasterSid=");
        sb.append(getIvldCompanyMasterSid());
        sb.append(", lives=");
        sb.append(getLives());
        sb.append(", organizationKey=");
        sb.append(getOrganizationKey());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", errorCode=");
        sb.append(getErrorCode());
        sb.append(", intfInsertedDate=");
        sb.append(getIntfInsertedDate());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", companyMasterIntfid=");
        sb.append(getCompanyMasterIntfid());
        sb.append(", reprocessedFlag=");
        sb.append(getReprocessedFlag());
        sb.append(", udc6=");
        sb.append(getUdc6());
        sb.append(", udc5=");
        sb.append(getUdc5());
        sb.append(", udc4=");
        sb.append(getUdc4());
        sb.append(", udc1=");
        sb.append(getUdc1());
        sb.append(", udc2=");
        sb.append(getUdc2());
        sb.append(", zipCode=");
        sb.append(getZipCode());
        sb.append(", udc3=");
        sb.append(getUdc3());
        sb.append(", reasonForFailure=");
        sb.append(getReasonForFailure());
        sb.append(", companyId=");
        sb.append(getCompanyId());
        sb.append(", address1=");
        sb.append(getAddress1());
        sb.append(", country=");
        sb.append(getCountry());
        sb.append(", address2=");
        sb.append(getAddress2());
        sb.append(", companyType=");
        sb.append(getCompanyType());
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
        sb.append(", errorField=");
        sb.append(getErrorField());
        sb.append(", city=");
        sb.append(getCity());
        sb.append(", regionCode=");
        sb.append(getRegionCode());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(130);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.IvldCompanyMaster");
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
            "<column><column-name>companyName</column-name><column-value><![CDATA[");
        sb.append(getCompanyName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyGroup</column-name><column-value><![CDATA[");
        sb.append(getCompanyGroup());
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
            "<column><column-name>status</column-name><column-value><![CDATA[");
        sb.append(getStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ivldCompanyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getIvldCompanyMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lives</column-name><column-value><![CDATA[");
        sb.append(getLives());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>organizationKey</column-name><column-value><![CDATA[");
        sb.append(getOrganizationKey());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
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
            "<column><column-name>addChgDelIndicator</column-name><column-value><![CDATA[");
        sb.append(getAddChgDelIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>errorCode</column-name><column-value><![CDATA[");
        sb.append(getErrorCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>intfInsertedDate</column-name><column-value><![CDATA[");
        sb.append(getIntfInsertedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyMasterIntfid</column-name><column-value><![CDATA[");
        sb.append(getCompanyMasterIntfid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reprocessedFlag</column-name><column-value><![CDATA[");
        sb.append(getReprocessedFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc6</column-name><column-value><![CDATA[");
        sb.append(getUdc6());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc5</column-name><column-value><![CDATA[");
        sb.append(getUdc5());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc4</column-name><column-value><![CDATA[");
        sb.append(getUdc4());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc1</column-name><column-value><![CDATA[");
        sb.append(getUdc1());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc2</column-name><column-value><![CDATA[");
        sb.append(getUdc2());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>zipCode</column-name><column-value><![CDATA[");
        sb.append(getZipCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc3</column-name><column-value><![CDATA[");
        sb.append(getUdc3());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reasonForFailure</column-name><column-value><![CDATA[");
        sb.append(getReasonForFailure());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyId</column-name><column-value><![CDATA[");
        sb.append(getCompanyId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>address1</column-name><column-value><![CDATA[");
        sb.append(getAddress1());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>country</column-name><column-value><![CDATA[");
        sb.append(getCountry());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>address2</column-name><column-value><![CDATA[");
        sb.append(getAddress2());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyType</column-name><column-value><![CDATA[");
        sb.append(getCompanyType());
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
            "<column><column-name>errorField</column-name><column-value><![CDATA[");
        sb.append(getErrorField());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>city</column-name><column-value><![CDATA[");
        sb.append(getCity());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>regionCode</column-name><column-value><![CDATA[");
        sb.append(getRegionCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
