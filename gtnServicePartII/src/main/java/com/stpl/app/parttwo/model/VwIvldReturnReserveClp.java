package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.ClpSerializer;
import com.stpl.app.parttwo.service.VwIvldReturnReserveLocalServiceUtil;

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


public class VwIvldReturnReserveClp extends BaseModelImpl<VwIvldReturnReserve>
    implements VwIvldReturnReserve {
    private int _ivldReturnReserveSid;
    private String _companyName;
    private String _year;
    private String _project;
    private String _itemId;
    private String _brandName;
    private Date _modifiedDate;
    private String _account;
    private String _returnReserveIntfId;
    private String _source;
    private Date _createdDate;
    private String _createdBy;
    private String _businessUnit;
    private String _businessUnitName;
    private String _addChgDelIndicator;
    private String _errorCode;
    private Date _intfInsertedDate;
    private String _modifiedBy;
    private String _itemNo;
    private String _month;
    private String _reprocessedFlag;
    private String _udc6;
    private String _udc5;
    private String _udc4;
    private String _udc1;
    private String _units;
    private String _udc2;
    private String _udc3;
    private String _reasonForFailure;
    private String _country;
    private String _companyId;
    private String _costCenter;
    private String _glCompany;
    private String _brandId;
    private String _future1;
    private String _future2;
    private String _amount;
    private String _division;
    private String _companyNo;
    private String _batchId;
    private String _itemName;
    private String _errorField;
    private boolean _checkRecord;
    private BaseModel<?> _vwIvldReturnReserveRemoteModel;

    public VwIvldReturnReserveClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return VwIvldReturnReserve.class;
    }

    @Override
    public String getModelClassName() {
        return VwIvldReturnReserve.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _ivldReturnReserveSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIvldReturnReserveSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ivldReturnReserveSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("ivldReturnReserveSid", getIvldReturnReserveSid());
        attributes.put("companyName", getCompanyName());
        attributes.put("year", getYear());
        attributes.put("project", getProject());
        attributes.put("itemId", getItemId());
        attributes.put("brandName", getBrandName());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("account", getAccount());
        attributes.put("returnReserveIntfId", getReturnReserveIntfId());
        attributes.put("source", getSource());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("businessUnit", getBusinessUnit());
        attributes.put("businessUnitName", getBusinessUnitName());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("errorCode", getErrorCode());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("itemNo", getItemNo());
        attributes.put("month", getMonth());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("udc6", getUdc6());
        attributes.put("udc5", getUdc5());
        attributes.put("udc4", getUdc4());
        attributes.put("udc1", getUdc1());
        attributes.put("units", getUnits());
        attributes.put("udc2", getUdc2());
        attributes.put("udc3", getUdc3());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("country", getCountry());
        attributes.put("companyId", getCompanyId());
        attributes.put("costCenter", getCostCenter());
        attributes.put("glCompany", getGlCompany());
        attributes.put("brandId", getBrandId());
        attributes.put("future1", getFuture1());
        attributes.put("future2", getFuture2());
        attributes.put("amount", getAmount());
        attributes.put("division", getDivision());
        attributes.put("companyNo", getCompanyNo());
        attributes.put("batchId", getBatchId());
        attributes.put("itemName", getItemName());
        attributes.put("errorField", getErrorField());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer ivldReturnReserveSid = (Integer) attributes.get(
                "ivldReturnReserveSid");

        if (ivldReturnReserveSid != null) {
            setIvldReturnReserveSid(ivldReturnReserveSid);
        }

        String companyName = (String) attributes.get("companyName");

        if (companyName != null) {
            setCompanyName(companyName);
        }

        String year = (String) attributes.get("year");

        if (year != null) {
            setYear(year);
        }

        String project = (String) attributes.get("project");

        if (project != null) {
            setProject(project);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String brandName = (String) attributes.get("brandName");

        if (brandName != null) {
            setBrandName(brandName);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String account = (String) attributes.get("account");

        if (account != null) {
            setAccount(account);
        }

        String returnReserveIntfId = (String) attributes.get(
                "returnReserveIntfId");

        if (returnReserveIntfId != null) {
            setReturnReserveIntfId(returnReserveIntfId);
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

        String businessUnit = (String) attributes.get("businessUnit");

        if (businessUnit != null) {
            setBusinessUnit(businessUnit);
        }

        String businessUnitName = (String) attributes.get("businessUnitName");

        if (businessUnitName != null) {
            setBusinessUnitName(businessUnitName);
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

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        String month = (String) attributes.get("month");

        if (month != null) {
            setMonth(month);
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

        String units = (String) attributes.get("units");

        if (units != null) {
            setUnits(units);
        }

        String udc2 = (String) attributes.get("udc2");

        if (udc2 != null) {
            setUdc2(udc2);
        }

        String udc3 = (String) attributes.get("udc3");

        if (udc3 != null) {
            setUdc3(udc3);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String country = (String) attributes.get("country");

        if (country != null) {
            setCountry(country);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        String costCenter = (String) attributes.get("costCenter");

        if (costCenter != null) {
            setCostCenter(costCenter);
        }

        String glCompany = (String) attributes.get("glCompany");

        if (glCompany != null) {
            setGlCompany(glCompany);
        }

        String brandId = (String) attributes.get("brandId");

        if (brandId != null) {
            setBrandId(brandId);
        }

        String future1 = (String) attributes.get("future1");

        if (future1 != null) {
            setFuture1(future1);
        }

        String future2 = (String) attributes.get("future2");

        if (future2 != null) {
            setFuture2(future2);
        }

        String amount = (String) attributes.get("amount");

        if (amount != null) {
            setAmount(amount);
        }

        String division = (String) attributes.get("division");

        if (division != null) {
            setDivision(division);
        }

        String companyNo = (String) attributes.get("companyNo");

        if (companyNo != null) {
            setCompanyNo(companyNo);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    @Override
    public int getIvldReturnReserveSid() {
        return _ivldReturnReserveSid;
    }

    @Override
    public void setIvldReturnReserveSid(int ivldReturnReserveSid) {
        _ivldReturnReserveSid = ivldReturnReserveSid;

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setIvldReturnReserveSid",
                        int.class);

                method.invoke(_vwIvldReturnReserveRemoteModel,
                    ivldReturnReserveSid);
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

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyName", String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, companyName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getYear() {
        return _year;
    }

    @Override
    public void setYear(String year) {
        _year = year;

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setYear", String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, year);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getProject() {
        return _project;
    }

    @Override
    public void setProject(String project) {
        _project = project;

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setProject", String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, project);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemId() {
        return _itemId;
    }

    @Override
    public void setItemId(String itemId) {
        _itemId = itemId;

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, itemId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBrandName() {
        return _brandName;
    }

    @Override
    public void setBrandName(String brandName) {
        _brandName = brandName;

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandName", String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, brandName);
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

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAccount() {
        return _account;
    }

    @Override
    public void setAccount(String account) {
        _account = account;

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setAccount", String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, account);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getReturnReserveIntfId() {
        return _returnReserveIntfId;
    }

    @Override
    public void setReturnReserveIntfId(String returnReserveIntfId) {
        _returnReserveIntfId = returnReserveIntfId;

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setReturnReserveIntfId",
                        String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel,
                    returnReserveIntfId);
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

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, source);
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

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, createdDate);
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

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBusinessUnit() {
        return _businessUnit;
    }

    @Override
    public void setBusinessUnit(String businessUnit) {
        _businessUnit = businessUnit;

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setBusinessUnit", String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, businessUnit);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBusinessUnitName() {
        return _businessUnitName;
    }

    @Override
    public void setBusinessUnitName(String businessUnitName) {
        _businessUnitName = businessUnitName;

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setBusinessUnitName",
                        String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, businessUnitName);
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

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel,
                    addChgDelIndicator);
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

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorCode", String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, errorCode);
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

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setIntfInsertedDate",
                        Date.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, intfInsertedDate);
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

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemNo() {
        return _itemNo;
    }

    @Override
    public void setItemNo(String itemNo) {
        _itemNo = itemNo;

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setItemNo", String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, itemNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getMonth() {
        return _month;
    }

    @Override
    public void setMonth(String month) {
        _month = month;

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setMonth", String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, month);
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

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setReprocessedFlag",
                        String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, reprocessedFlag);
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

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc6", String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, udc6);
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

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc5", String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, udc5);
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

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc4", String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, udc4);
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

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc1", String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, udc1);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUnits() {
        return _units;
    }

    @Override
    public void setUnits(String units) {
        _units = units;

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setUnits", String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, units);
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

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc2", String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, udc2);
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

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc3", String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, udc3);
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

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setReasonForFailure",
                        String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, reasonForFailure);
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

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setCountry", String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, country);
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

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyId", String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, companyId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCostCenter() {
        return _costCenter;
    }

    @Override
    public void setCostCenter(String costCenter) {
        _costCenter = costCenter;

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setCostCenter", String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, costCenter);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getGlCompany() {
        return _glCompany;
    }

    @Override
    public void setGlCompany(String glCompany) {
        _glCompany = glCompany;

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setGlCompany", String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, glCompany);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBrandId() {
        return _brandId;
    }

    @Override
    public void setBrandId(String brandId) {
        _brandId = brandId;

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandId", String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, brandId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFuture1() {
        return _future1;
    }

    @Override
    public void setFuture1(String future1) {
        _future1 = future1;

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setFuture1", String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, future1);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFuture2() {
        return _future2;
    }

    @Override
    public void setFuture2(String future2) {
        _future2 = future2;

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setFuture2", String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, future2);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAmount() {
        return _amount;
    }

    @Override
    public void setAmount(String amount) {
        _amount = amount;

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setAmount", String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, amount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDivision() {
        return _division;
    }

    @Override
    public void setDivision(String division) {
        _division = division;

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setDivision", String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, division);
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

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyNo", String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, companyNo);
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

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemName() {
        return _itemName;
    }

    @Override
    public void setItemName(String itemName) {
        _itemName = itemName;

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setItemName", String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, itemName);
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

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorField", String.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, errorField);
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

        if (_vwIvldReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_vwIvldReturnReserveRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getVwIvldReturnReserveRemoteModel() {
        return _vwIvldReturnReserveRemoteModel;
    }

    public void setVwIvldReturnReserveRemoteModel(
        BaseModel<?> vwIvldReturnReserveRemoteModel) {
        _vwIvldReturnReserveRemoteModel = vwIvldReturnReserveRemoteModel;
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

        Class<?> remoteModelClass = _vwIvldReturnReserveRemoteModel.getClass();

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

        Object returnValue = method.invoke(_vwIvldReturnReserveRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            VwIvldReturnReserveLocalServiceUtil.addVwIvldReturnReserve(this);
        } else {
            VwIvldReturnReserveLocalServiceUtil.updateVwIvldReturnReserve(this);
        }
    }

    @Override
    public VwIvldReturnReserve toEscapedModel() {
        return (VwIvldReturnReserve) ProxyUtil.newProxyInstance(VwIvldReturnReserve.class.getClassLoader(),
            new Class[] { VwIvldReturnReserve.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        VwIvldReturnReserveClp clone = new VwIvldReturnReserveClp();

        clone.setIvldReturnReserveSid(getIvldReturnReserveSid());
        clone.setCompanyName(getCompanyName());
        clone.setYear(getYear());
        clone.setProject(getProject());
        clone.setItemId(getItemId());
        clone.setBrandName(getBrandName());
        clone.setModifiedDate(getModifiedDate());
        clone.setAccount(getAccount());
        clone.setReturnReserveIntfId(getReturnReserveIntfId());
        clone.setSource(getSource());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setBusinessUnit(getBusinessUnit());
        clone.setBusinessUnitName(getBusinessUnitName());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setErrorCode(getErrorCode());
        clone.setIntfInsertedDate(getIntfInsertedDate());
        clone.setModifiedBy(getModifiedBy());
        clone.setItemNo(getItemNo());
        clone.setMonth(getMonth());
        clone.setReprocessedFlag(getReprocessedFlag());
        clone.setUdc6(getUdc6());
        clone.setUdc5(getUdc5());
        clone.setUdc4(getUdc4());
        clone.setUdc1(getUdc1());
        clone.setUnits(getUnits());
        clone.setUdc2(getUdc2());
        clone.setUdc3(getUdc3());
        clone.setReasonForFailure(getReasonForFailure());
        clone.setCountry(getCountry());
        clone.setCompanyId(getCompanyId());
        clone.setCostCenter(getCostCenter());
        clone.setGlCompany(getGlCompany());
        clone.setBrandId(getBrandId());
        clone.setFuture1(getFuture1());
        clone.setFuture2(getFuture2());
        clone.setAmount(getAmount());
        clone.setDivision(getDivision());
        clone.setCompanyNo(getCompanyNo());
        clone.setBatchId(getBatchId());
        clone.setItemName(getItemName());
        clone.setErrorField(getErrorField());
        clone.setCheckRecord(getCheckRecord());

        return clone;
    }

    @Override
    public int compareTo(VwIvldReturnReserve vwIvldReturnReserve) {
        int primaryKey = vwIvldReturnReserve.getPrimaryKey();

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

        if (!(obj instanceof VwIvldReturnReserveClp)) {
            return false;
        }

        VwIvldReturnReserveClp vwIvldReturnReserve = (VwIvldReturnReserveClp) obj;

        int primaryKey = vwIvldReturnReserve.getPrimaryKey();

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
        StringBundler sb = new StringBundler(87);

        sb.append("{ivldReturnReserveSid=");
        sb.append(getIvldReturnReserveSid());
        sb.append(", companyName=");
        sb.append(getCompanyName());
        sb.append(", year=");
        sb.append(getYear());
        sb.append(", project=");
        sb.append(getProject());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", brandName=");
        sb.append(getBrandName());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", account=");
        sb.append(getAccount());
        sb.append(", returnReserveIntfId=");
        sb.append(getReturnReserveIntfId());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", businessUnit=");
        sb.append(getBusinessUnit());
        sb.append(", businessUnitName=");
        sb.append(getBusinessUnitName());
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", errorCode=");
        sb.append(getErrorCode());
        sb.append(", intfInsertedDate=");
        sb.append(getIntfInsertedDate());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", itemNo=");
        sb.append(getItemNo());
        sb.append(", month=");
        sb.append(getMonth());
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
        sb.append(", units=");
        sb.append(getUnits());
        sb.append(", udc2=");
        sb.append(getUdc2());
        sb.append(", udc3=");
        sb.append(getUdc3());
        sb.append(", reasonForFailure=");
        sb.append(getReasonForFailure());
        sb.append(", country=");
        sb.append(getCountry());
        sb.append(", companyId=");
        sb.append(getCompanyId());
        sb.append(", costCenter=");
        sb.append(getCostCenter());
        sb.append(", glCompany=");
        sb.append(getGlCompany());
        sb.append(", brandId=");
        sb.append(getBrandId());
        sb.append(", future1=");
        sb.append(getFuture1());
        sb.append(", future2=");
        sb.append(getFuture2());
        sb.append(", amount=");
        sb.append(getAmount());
        sb.append(", division=");
        sb.append(getDivision());
        sb.append(", companyNo=");
        sb.append(getCompanyNo());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", itemName=");
        sb.append(getItemName());
        sb.append(", errorField=");
        sb.append(getErrorField());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(133);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.VwIvldReturnReserve");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>ivldReturnReserveSid</column-name><column-value><![CDATA[");
        sb.append(getIvldReturnReserveSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyName</column-name><column-value><![CDATA[");
        sb.append(getCompanyName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>year</column-name><column-value><![CDATA[");
        sb.append(getYear());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>project</column-name><column-value><![CDATA[");
        sb.append(getProject());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemId</column-name><column-value><![CDATA[");
        sb.append(getItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brandName</column-name><column-value><![CDATA[");
        sb.append(getBrandName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>account</column-name><column-value><![CDATA[");
        sb.append(getAccount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>returnReserveIntfId</column-name><column-value><![CDATA[");
        sb.append(getReturnReserveIntfId());
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
            "<column><column-name>businessUnit</column-name><column-value><![CDATA[");
        sb.append(getBusinessUnit());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>businessUnitName</column-name><column-value><![CDATA[");
        sb.append(getBusinessUnitName());
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
            "<column><column-name>itemNo</column-name><column-value><![CDATA[");
        sb.append(getItemNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>month</column-name><column-value><![CDATA[");
        sb.append(getMonth());
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
            "<column><column-name>units</column-name><column-value><![CDATA[");
        sb.append(getUnits());
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
            "<column><column-name>reasonForFailure</column-name><column-value><![CDATA[");
        sb.append(getReasonForFailure());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>country</column-name><column-value><![CDATA[");
        sb.append(getCountry());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyId</column-name><column-value><![CDATA[");
        sb.append(getCompanyId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>costCenter</column-name><column-value><![CDATA[");
        sb.append(getCostCenter());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>glCompany</column-name><column-value><![CDATA[");
        sb.append(getGlCompany());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brandId</column-name><column-value><![CDATA[");
        sb.append(getBrandId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>future1</column-name><column-value><![CDATA[");
        sb.append(getFuture1());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>future2</column-name><column-value><![CDATA[");
        sb.append(getFuture2());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>amount</column-name><column-value><![CDATA[");
        sb.append(getAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>division</column-name><column-value><![CDATA[");
        sb.append(getDivision());
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
            "<column><column-name>itemName</column-name><column-value><![CDATA[");
        sb.append(getItemName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>errorField</column-name><column-value><![CDATA[");
        sb.append(getErrorField());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
