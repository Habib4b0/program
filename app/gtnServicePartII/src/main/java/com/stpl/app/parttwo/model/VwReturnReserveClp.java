package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.ClpSerializer;
import com.stpl.app.parttwo.service.VwReturnReserveLocalServiceUtil;

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


public class VwReturnReserveClp extends BaseModelImpl<VwReturnReserve>
    implements VwReturnReserve {
    private int _itemMasterSid;
    private String _companyName;
    private String _project;
    private String _year;
    private String _itemId;
    private String _brandName;
    private Date _modifiedDate;
    private int _brandMasterSid;
    private String _account;
    private int _returnReserveIntfId;
    private int _glCompanyMasterSid;
    private String _source;
    private Date _createdDate;
    private String _createdBy;
    private String _businessUnit;
    private String _businessUnitName;
    private int _buCompanyMasterSid;
    private String _inboundStatus;
    private String _modifiedBy;
    private String _itemNo;
    private String _month;
    private String _udc6;
    private String _udc5;
    private String _udc4;
    private String _udc1;
    private String _units;
    private String _udc2;
    private String _udc3;
    private String _country;
    private String _companyId;
    private String _costCenter;
    private String _glCompany;
    private String _brandId;
    private String _future1;
    private String _future2;
    private String _amount;
    private boolean _recordLockStatus;
    private String _division;
    private int _returnReserveSid;
    private String _companyNo;
    private String _batchId;
    private String _itemName;
    private BaseModel<?> _vwReturnReserveRemoteModel;

    public VwReturnReserveClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return VwReturnReserve.class;
    }

    @Override
    public String getModelClassName() {
        return VwReturnReserve.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _returnReserveSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setReturnReserveSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _returnReserveSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("companyName", getCompanyName());
        attributes.put("project", getProject());
        attributes.put("year", getYear());
        attributes.put("itemId", getItemId());
        attributes.put("brandName", getBrandName());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("brandMasterSid", getBrandMasterSid());
        attributes.put("account", getAccount());
        attributes.put("returnReserveIntfId", getReturnReserveIntfId());
        attributes.put("glCompanyMasterSid", getGlCompanyMasterSid());
        attributes.put("source", getSource());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("businessUnit", getBusinessUnit());
        attributes.put("businessUnitName", getBusinessUnitName());
        attributes.put("buCompanyMasterSid", getBuCompanyMasterSid());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("itemNo", getItemNo());
        attributes.put("month", getMonth());
        attributes.put("udc6", getUdc6());
        attributes.put("udc5", getUdc5());
        attributes.put("udc4", getUdc4());
        attributes.put("udc1", getUdc1());
        attributes.put("units", getUnits());
        attributes.put("udc2", getUdc2());
        attributes.put("udc3", getUdc3());
        attributes.put("country", getCountry());
        attributes.put("companyId", getCompanyId());
        attributes.put("costCenter", getCostCenter());
        attributes.put("glCompany", getGlCompany());
        attributes.put("brandId", getBrandId());
        attributes.put("future1", getFuture1());
        attributes.put("future2", getFuture2());
        attributes.put("amount", getAmount());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("division", getDivision());
        attributes.put("returnReserveSid", getReturnReserveSid());
        attributes.put("companyNo", getCompanyNo());
        attributes.put("batchId", getBatchId());
        attributes.put("itemName", getItemName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        String companyName = (String) attributes.get("companyName");

        if (companyName != null) {
            setCompanyName(companyName);
        }

        String project = (String) attributes.get("project");

        if (project != null) {
            setProject(project);
        }

        String year = (String) attributes.get("year");

        if (year != null) {
            setYear(year);
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

        Integer brandMasterSid = (Integer) attributes.get("brandMasterSid");

        if (brandMasterSid != null) {
            setBrandMasterSid(brandMasterSid);
        }

        String account = (String) attributes.get("account");

        if (account != null) {
            setAccount(account);
        }

        Integer returnReserveIntfId = (Integer) attributes.get(
                "returnReserveIntfId");

        if (returnReserveIntfId != null) {
            setReturnReserveIntfId(returnReserveIntfId);
        }

        Integer glCompanyMasterSid = (Integer) attributes.get(
                "glCompanyMasterSid");

        if (glCompanyMasterSid != null) {
            setGlCompanyMasterSid(glCompanyMasterSid);
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

        Integer buCompanyMasterSid = (Integer) attributes.get(
                "buCompanyMasterSid");

        if (buCompanyMasterSid != null) {
            setBuCompanyMasterSid(buCompanyMasterSid);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
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

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String division = (String) attributes.get("division");

        if (division != null) {
            setDivision(division);
        }

        Integer returnReserveSid = (Integer) attributes.get("returnReserveSid");

        if (returnReserveSid != null) {
            setReturnReserveSid(returnReserveSid);
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
    }

    @Override
    public int getItemMasterSid() {
        return _itemMasterSid;
    }

    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _itemMasterSid = itemMasterSid;

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_vwReturnReserveRemoteModel, itemMasterSid);
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

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyName", String.class);

                method.invoke(_vwReturnReserveRemoteModel, companyName);
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

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setProject", String.class);

                method.invoke(_vwReturnReserveRemoteModel, project);
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

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setYear", String.class);

                method.invoke(_vwReturnReserveRemoteModel, year);
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

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_vwReturnReserveRemoteModel, itemId);
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

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandName", String.class);

                method.invoke(_vwReturnReserveRemoteModel, brandName);
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

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_vwReturnReserveRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getBrandMasterSid() {
        return _brandMasterSid;
    }

    @Override
    public void setBrandMasterSid(int brandMasterSid) {
        _brandMasterSid = brandMasterSid;

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandMasterSid", int.class);

                method.invoke(_vwReturnReserveRemoteModel, brandMasterSid);
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

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setAccount", String.class);

                method.invoke(_vwReturnReserveRemoteModel, account);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getReturnReserveIntfId() {
        return _returnReserveIntfId;
    }

    @Override
    public void setReturnReserveIntfId(int returnReserveIntfId) {
        _returnReserveIntfId = returnReserveIntfId;

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setReturnReserveIntfId",
                        int.class);

                method.invoke(_vwReturnReserveRemoteModel, returnReserveIntfId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getGlCompanyMasterSid() {
        return _glCompanyMasterSid;
    }

    @Override
    public void setGlCompanyMasterSid(int glCompanyMasterSid) {
        _glCompanyMasterSid = glCompanyMasterSid;

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setGlCompanyMasterSid",
                        int.class);

                method.invoke(_vwReturnReserveRemoteModel, glCompanyMasterSid);
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

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_vwReturnReserveRemoteModel, source);
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

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_vwReturnReserveRemoteModel, createdDate);
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

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_vwReturnReserveRemoteModel, createdBy);
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

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setBusinessUnit", String.class);

                method.invoke(_vwReturnReserveRemoteModel, businessUnit);
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

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setBusinessUnitName",
                        String.class);

                method.invoke(_vwReturnReserveRemoteModel, businessUnitName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getBuCompanyMasterSid() {
        return _buCompanyMasterSid;
    }

    @Override
    public void setBuCompanyMasterSid(int buCompanyMasterSid) {
        _buCompanyMasterSid = buCompanyMasterSid;

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setBuCompanyMasterSid",
                        int.class);

                method.invoke(_vwReturnReserveRemoteModel, buCompanyMasterSid);
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

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_vwReturnReserveRemoteModel, inboundStatus);
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

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_vwReturnReserveRemoteModel, modifiedBy);
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

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setItemNo", String.class);

                method.invoke(_vwReturnReserveRemoteModel, itemNo);
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

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setMonth", String.class);

                method.invoke(_vwReturnReserveRemoteModel, month);
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

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc6", String.class);

                method.invoke(_vwReturnReserveRemoteModel, udc6);
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

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc5", String.class);

                method.invoke(_vwReturnReserveRemoteModel, udc5);
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

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc4", String.class);

                method.invoke(_vwReturnReserveRemoteModel, udc4);
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

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc1", String.class);

                method.invoke(_vwReturnReserveRemoteModel, udc1);
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

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setUnits", String.class);

                method.invoke(_vwReturnReserveRemoteModel, units);
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

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc2", String.class);

                method.invoke(_vwReturnReserveRemoteModel, udc2);
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

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc3", String.class);

                method.invoke(_vwReturnReserveRemoteModel, udc3);
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

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setCountry", String.class);

                method.invoke(_vwReturnReserveRemoteModel, country);
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

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyId", String.class);

                method.invoke(_vwReturnReserveRemoteModel, companyId);
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

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setCostCenter", String.class);

                method.invoke(_vwReturnReserveRemoteModel, costCenter);
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

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setGlCompany", String.class);

                method.invoke(_vwReturnReserveRemoteModel, glCompany);
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

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandId", String.class);

                method.invoke(_vwReturnReserveRemoteModel, brandId);
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

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setFuture1", String.class);

                method.invoke(_vwReturnReserveRemoteModel, future1);
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

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setFuture2", String.class);

                method.invoke(_vwReturnReserveRemoteModel, future2);
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

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setAmount", String.class);

                method.invoke(_vwReturnReserveRemoteModel, amount);
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

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_vwReturnReserveRemoteModel, recordLockStatus);
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

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setDivision", String.class);

                method.invoke(_vwReturnReserveRemoteModel, division);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getReturnReserveSid() {
        return _returnReserveSid;
    }

    @Override
    public void setReturnReserveSid(int returnReserveSid) {
        _returnReserveSid = returnReserveSid;

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setReturnReserveSid", int.class);

                method.invoke(_vwReturnReserveRemoteModel, returnReserveSid);
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

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyNo", String.class);

                method.invoke(_vwReturnReserveRemoteModel, companyNo);
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

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_vwReturnReserveRemoteModel, batchId);
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

        if (_vwReturnReserveRemoteModel != null) {
            try {
                Class<?> clazz = _vwReturnReserveRemoteModel.getClass();

                Method method = clazz.getMethod("setItemName", String.class);

                method.invoke(_vwReturnReserveRemoteModel, itemName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getVwReturnReserveRemoteModel() {
        return _vwReturnReserveRemoteModel;
    }

    public void setVwReturnReserveRemoteModel(
        BaseModel<?> vwReturnReserveRemoteModel) {
        _vwReturnReserveRemoteModel = vwReturnReserveRemoteModel;
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

        Class<?> remoteModelClass = _vwReturnReserveRemoteModel.getClass();

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

        Object returnValue = method.invoke(_vwReturnReserveRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            VwReturnReserveLocalServiceUtil.addVwReturnReserve(this);
        } else {
            VwReturnReserveLocalServiceUtil.updateVwReturnReserve(this);
        }
    }

    @Override
    public VwReturnReserve toEscapedModel() {
        return (VwReturnReserve) ProxyUtil.newProxyInstance(VwReturnReserve.class.getClassLoader(),
            new Class[] { VwReturnReserve.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        VwReturnReserveClp clone = new VwReturnReserveClp();

        clone.setItemMasterSid(getItemMasterSid());
        clone.setCompanyName(getCompanyName());
        clone.setProject(getProject());
        clone.setYear(getYear());
        clone.setItemId(getItemId());
        clone.setBrandName(getBrandName());
        clone.setModifiedDate(getModifiedDate());
        clone.setBrandMasterSid(getBrandMasterSid());
        clone.setAccount(getAccount());
        clone.setReturnReserveIntfId(getReturnReserveIntfId());
        clone.setGlCompanyMasterSid(getGlCompanyMasterSid());
        clone.setSource(getSource());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setBusinessUnit(getBusinessUnit());
        clone.setBusinessUnitName(getBusinessUnitName());
        clone.setBuCompanyMasterSid(getBuCompanyMasterSid());
        clone.setInboundStatus(getInboundStatus());
        clone.setModifiedBy(getModifiedBy());
        clone.setItemNo(getItemNo());
        clone.setMonth(getMonth());
        clone.setUdc6(getUdc6());
        clone.setUdc5(getUdc5());
        clone.setUdc4(getUdc4());
        clone.setUdc1(getUdc1());
        clone.setUnits(getUnits());
        clone.setUdc2(getUdc2());
        clone.setUdc3(getUdc3());
        clone.setCountry(getCountry());
        clone.setCompanyId(getCompanyId());
        clone.setCostCenter(getCostCenter());
        clone.setGlCompany(getGlCompany());
        clone.setBrandId(getBrandId());
        clone.setFuture1(getFuture1());
        clone.setFuture2(getFuture2());
        clone.setAmount(getAmount());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setDivision(getDivision());
        clone.setReturnReserveSid(getReturnReserveSid());
        clone.setCompanyNo(getCompanyNo());
        clone.setBatchId(getBatchId());
        clone.setItemName(getItemName());

        return clone;
    }

    @Override
    public int compareTo(VwReturnReserve vwReturnReserve) {
        int primaryKey = vwReturnReserve.getPrimaryKey();

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

        if (!(obj instanceof VwReturnReserveClp)) {
            return false;
        }

        VwReturnReserveClp vwReturnReserve = (VwReturnReserveClp) obj;

        int primaryKey = vwReturnReserve.getPrimaryKey();

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

        sb.append("{itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", companyName=");
        sb.append(getCompanyName());
        sb.append(", project=");
        sb.append(getProject());
        sb.append(", year=");
        sb.append(getYear());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", brandName=");
        sb.append(getBrandName());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", brandMasterSid=");
        sb.append(getBrandMasterSid());
        sb.append(", account=");
        sb.append(getAccount());
        sb.append(", returnReserveIntfId=");
        sb.append(getReturnReserveIntfId());
        sb.append(", glCompanyMasterSid=");
        sb.append(getGlCompanyMasterSid());
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
        sb.append(", buCompanyMasterSid=");
        sb.append(getBuCompanyMasterSid());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", itemNo=");
        sb.append(getItemNo());
        sb.append(", month=");
        sb.append(getMonth());
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
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", division=");
        sb.append(getDivision());
        sb.append(", returnReserveSid=");
        sb.append(getReturnReserveSid());
        sb.append(", companyNo=");
        sb.append(getCompanyNo());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", itemName=");
        sb.append(getItemName());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(130);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.VwReturnReserve");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyName</column-name><column-value><![CDATA[");
        sb.append(getCompanyName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>project</column-name><column-value><![CDATA[");
        sb.append(getProject());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>year</column-name><column-value><![CDATA[");
        sb.append(getYear());
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
            "<column><column-name>brandMasterSid</column-name><column-value><![CDATA[");
        sb.append(getBrandMasterSid());
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
            "<column><column-name>glCompanyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getGlCompanyMasterSid());
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
            "<column><column-name>buCompanyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getBuCompanyMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
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
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>division</column-name><column-value><![CDATA[");
        sb.append(getDivision());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>returnReserveSid</column-name><column-value><![CDATA[");
        sb.append(getReturnReserveSid());
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

        sb.append("</model>");

        return sb.toString();
    }
}
