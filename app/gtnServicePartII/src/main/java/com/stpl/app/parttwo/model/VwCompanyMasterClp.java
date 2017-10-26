package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.ClpSerializer;
import com.stpl.app.parttwo.service.VwCompanyMasterLocalServiceUtil;

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


public class VwCompanyMasterClp extends BaseModelImpl<VwCompanyMaster>
    implements VwCompanyMaster {
    private String _state;
    private String _financialSystem;
    private String _companyGroup;
    private String _companyName;
    private Date _lastUpdatedDate;
    private String _companyCategory;
    private Date _modifiedDate;
    private int _lives;
    private String _organizationKey;
    private String _address2;
    private Date _createdDate;
    private String _createdBy;
    private String _source;
    private String _address1;
    private String _addChgDelIndicator;
    private String _modifiedBy;
    private String _udc6;
    private String _udc5;
    private int _companyMasterSid;
    private String _udc4;
    private String _udc1;
    private String _zipCode;
    private String _udc2;
    private String _udc3;
    private String _companyId;
    private String _country;
    private String _companyType;
    private Date _companyStartDate;
    private String _companyNo;
    private String _batchId;
    private String _companyStatus;
    private Date _companyEndDate;
    private String _city;
    private String _regionCode;
    private BaseModel<?> _vwCompanyMasterRemoteModel;

    public VwCompanyMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return VwCompanyMaster.class;
    }

    @Override
    public String getModelClassName() {
        return VwCompanyMaster.class.getName();
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
        attributes.put("lastUpdatedDate", getLastUpdatedDate());
        attributes.put("companyCategory", getCompanyCategory());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("lives", getLives());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("address2", getAddress2());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("address1", getAddress1());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("udc6", getUdc6());
        attributes.put("udc5", getUdc5());
        attributes.put("companyMasterSid", getCompanyMasterSid());
        attributes.put("udc4", getUdc4());
        attributes.put("udc1", getUdc1());
        attributes.put("zipCode", getZipCode());
        attributes.put("udc2", getUdc2());
        attributes.put("udc3", getUdc3());
        attributes.put("companyId", getCompanyId());
        attributes.put("country", getCountry());
        attributes.put("companyType", getCompanyType());
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
        String state = (String) attributes.get("state");

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

        Date lastUpdatedDate = (Date) attributes.get("lastUpdatedDate");

        if (lastUpdatedDate != null) {
            setLastUpdatedDate(lastUpdatedDate);
        }

        String companyCategory = (String) attributes.get("companyCategory");

        if (companyCategory != null) {
            setCompanyCategory(companyCategory);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer lives = (Integer) attributes.get("lives");

        if (lives != null) {
            setLives(lives);
        }

        String organizationKey = (String) attributes.get("organizationKey");

        if (organizationKey != null) {
            setOrganizationKey(organizationKey);
        }

        String address2 = (String) attributes.get("address2");

        if (address2 != null) {
            setAddress2(address2);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String createdBy = (String) attributes.get("createdBy");

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

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String udc6 = (String) attributes.get("udc6");

        if (udc6 != null) {
            setUdc6(udc6);
        }

        String udc5 = (String) attributes.get("udc5");

        if (udc5 != null) {
            setUdc5(udc5);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }

        String udc4 = (String) attributes.get("udc4");

        if (udc4 != null) {
            setUdc4(udc4);
        }

        String udc1 = (String) attributes.get("udc1");

        if (udc1 != null) {
            setUdc1(udc1);
        }

        String zipCode = (String) attributes.get("zipCode");

        if (zipCode != null) {
            setZipCode(zipCode);
        }

        String udc2 = (String) attributes.get("udc2");

        if (udc2 != null) {
            setUdc2(udc2);
        }

        String udc3 = (String) attributes.get("udc3");

        if (udc3 != null) {
            setUdc3(udc3);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        String country = (String) attributes.get("country");

        if (country != null) {
            setCountry(country);
        }

        String companyType = (String) attributes.get("companyType");

        if (companyType != null) {
            setCompanyType(companyType);
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

        String companyStatus = (String) attributes.get("companyStatus");

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
    public String getState() {
        return _state;
    }

    @Override
    public void setState(String state) {
        _state = state;

        if (_vwCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setState", String.class);

                method.invoke(_vwCompanyMasterRemoteModel, state);
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

        if (_vwCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setFinancialSystem",
                        String.class);

                method.invoke(_vwCompanyMasterRemoteModel, financialSystem);
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

        if (_vwCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyGroup", String.class);

                method.invoke(_vwCompanyMasterRemoteModel, companyGroup);
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

        if (_vwCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyName", String.class);

                method.invoke(_vwCompanyMasterRemoteModel, companyName);
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

        if (_vwCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLastUpdatedDate", Date.class);

                method.invoke(_vwCompanyMasterRemoteModel, lastUpdatedDate);
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

        if (_vwCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyCategory",
                        String.class);

                method.invoke(_vwCompanyMasterRemoteModel, companyCategory);
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

        if (_vwCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_vwCompanyMasterRemoteModel, modifiedDate);
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

        if (_vwCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLives", int.class);

                method.invoke(_vwCompanyMasterRemoteModel, lives);
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

        if (_vwCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setOrganizationKey",
                        String.class);

                method.invoke(_vwCompanyMasterRemoteModel, organizationKey);
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

        if (_vwCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAddress2", String.class);

                method.invoke(_vwCompanyMasterRemoteModel, address2);
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

        if (_vwCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_vwCompanyMasterRemoteModel, createdDate);
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

        if (_vwCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_vwCompanyMasterRemoteModel, createdBy);
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

        if (_vwCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_vwCompanyMasterRemoteModel, source);
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

        if (_vwCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAddress1", String.class);

                method.invoke(_vwCompanyMasterRemoteModel, address1);
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

        if (_vwCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_vwCompanyMasterRemoteModel, addChgDelIndicator);
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

        if (_vwCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_vwCompanyMasterRemoteModel, modifiedBy);
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

        if (_vwCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc6", String.class);

                method.invoke(_vwCompanyMasterRemoteModel, udc6);
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

        if (_vwCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc5", String.class);

                method.invoke(_vwCompanyMasterRemoteModel, udc5);
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

        if (_vwCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyMasterSid", int.class);

                method.invoke(_vwCompanyMasterRemoteModel, companyMasterSid);
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

        if (_vwCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc4", String.class);

                method.invoke(_vwCompanyMasterRemoteModel, udc4);
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

        if (_vwCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc1", String.class);

                method.invoke(_vwCompanyMasterRemoteModel, udc1);
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

        if (_vwCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setZipCode", String.class);

                method.invoke(_vwCompanyMasterRemoteModel, zipCode);
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

        if (_vwCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc2", String.class);

                method.invoke(_vwCompanyMasterRemoteModel, udc2);
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

        if (_vwCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc3", String.class);

                method.invoke(_vwCompanyMasterRemoteModel, udc3);
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

        if (_vwCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyId", String.class);

                method.invoke(_vwCompanyMasterRemoteModel, companyId);
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

        if (_vwCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCountry", String.class);

                method.invoke(_vwCompanyMasterRemoteModel, country);
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

        if (_vwCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyType", String.class);

                method.invoke(_vwCompanyMasterRemoteModel, companyType);
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

        if (_vwCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyStartDate",
                        Date.class);

                method.invoke(_vwCompanyMasterRemoteModel, companyStartDate);
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

        if (_vwCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyNo", String.class);

                method.invoke(_vwCompanyMasterRemoteModel, companyNo);
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

        if (_vwCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_vwCompanyMasterRemoteModel, batchId);
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

        if (_vwCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyStatus", String.class);

                method.invoke(_vwCompanyMasterRemoteModel, companyStatus);
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

        if (_vwCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyEndDate", Date.class);

                method.invoke(_vwCompanyMasterRemoteModel, companyEndDate);
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

        if (_vwCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCity", String.class);

                method.invoke(_vwCompanyMasterRemoteModel, city);
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

        if (_vwCompanyMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRegionCode", String.class);

                method.invoke(_vwCompanyMasterRemoteModel, regionCode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getVwCompanyMasterRemoteModel() {
        return _vwCompanyMasterRemoteModel;
    }

    public void setVwCompanyMasterRemoteModel(
        BaseModel<?> vwCompanyMasterRemoteModel) {
        _vwCompanyMasterRemoteModel = vwCompanyMasterRemoteModel;
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

        Class<?> remoteModelClass = _vwCompanyMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_vwCompanyMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            VwCompanyMasterLocalServiceUtil.addVwCompanyMaster(this);
        } else {
            VwCompanyMasterLocalServiceUtil.updateVwCompanyMaster(this);
        }
    }

    @Override
    public VwCompanyMaster toEscapedModel() {
        return (VwCompanyMaster) ProxyUtil.newProxyInstance(VwCompanyMaster.class.getClassLoader(),
            new Class[] { VwCompanyMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        VwCompanyMasterClp clone = new VwCompanyMasterClp();

        clone.setState(getState());
        clone.setFinancialSystem(getFinancialSystem());
        clone.setCompanyGroup(getCompanyGroup());
        clone.setCompanyName(getCompanyName());
        clone.setLastUpdatedDate(getLastUpdatedDate());
        clone.setCompanyCategory(getCompanyCategory());
        clone.setModifiedDate(getModifiedDate());
        clone.setLives(getLives());
        clone.setOrganizationKey(getOrganizationKey());
        clone.setAddress2(getAddress2());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setSource(getSource());
        clone.setAddress1(getAddress1());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setModifiedBy(getModifiedBy());
        clone.setUdc6(getUdc6());
        clone.setUdc5(getUdc5());
        clone.setCompanyMasterSid(getCompanyMasterSid());
        clone.setUdc4(getUdc4());
        clone.setUdc1(getUdc1());
        clone.setZipCode(getZipCode());
        clone.setUdc2(getUdc2());
        clone.setUdc3(getUdc3());
        clone.setCompanyId(getCompanyId());
        clone.setCountry(getCountry());
        clone.setCompanyType(getCompanyType());
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
    public int compareTo(VwCompanyMaster vwCompanyMaster) {
        int primaryKey = vwCompanyMaster.getPrimaryKey();

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

        if (!(obj instanceof VwCompanyMasterClp)) {
            return false;
        }

        VwCompanyMasterClp vwCompanyMaster = (VwCompanyMasterClp) obj;

        int primaryKey = vwCompanyMaster.getPrimaryKey();

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
        StringBundler sb = new StringBundler(69);

        sb.append("{state=");
        sb.append(getState());
        sb.append(", financialSystem=");
        sb.append(getFinancialSystem());
        sb.append(", companyGroup=");
        sb.append(getCompanyGroup());
        sb.append(", companyName=");
        sb.append(getCompanyName());
        sb.append(", lastUpdatedDate=");
        sb.append(getLastUpdatedDate());
        sb.append(", companyCategory=");
        sb.append(getCompanyCategory());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", lives=");
        sb.append(getLives());
        sb.append(", organizationKey=");
        sb.append(getOrganizationKey());
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
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", udc6=");
        sb.append(getUdc6());
        sb.append(", udc5=");
        sb.append(getUdc5());
        sb.append(", companyMasterSid=");
        sb.append(getCompanyMasterSid());
        sb.append(", udc4=");
        sb.append(getUdc4());
        sb.append(", udc1=");
        sb.append(getUdc1());
        sb.append(", zipCode=");
        sb.append(getZipCode());
        sb.append(", udc2=");
        sb.append(getUdc2());
        sb.append(", udc3=");
        sb.append(getUdc3());
        sb.append(", companyId=");
        sb.append(getCompanyId());
        sb.append(", country=");
        sb.append(getCountry());
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
        sb.append(", city=");
        sb.append(getCity());
        sb.append(", regionCode=");
        sb.append(getRegionCode());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(106);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.VwCompanyMaster");
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
            "<column><column-name>lastUpdatedDate</column-name><column-value><![CDATA[");
        sb.append(getLastUpdatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyCategory</column-name><column-value><![CDATA[");
        sb.append(getCompanyCategory());
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
            "<column><column-name>organizationKey</column-name><column-value><![CDATA[");
        sb.append(getOrganizationKey());
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
            "<column><column-name>addChgDelIndicator</column-name><column-value><![CDATA[");
        sb.append(getAddChgDelIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
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
            "<column><column-name>companyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyMasterSid());
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
            "<column><column-name>zipCode</column-name><column-value><![CDATA[");
        sb.append(getZipCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc2</column-name><column-value><![CDATA[");
        sb.append(getUdc2());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc3</column-name><column-value><![CDATA[");
        sb.append(getUdc3());
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
