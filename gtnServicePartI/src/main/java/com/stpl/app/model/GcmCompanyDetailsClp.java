package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.GcmCompanyDetailsLocalServiceUtil;

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


public class GcmCompanyDetailsClp extends BaseModelImpl<GcmCompanyDetails>
    implements GcmCompanyDetails {
    private boolean _checkRecord;
    private int _userId;
    private String _moduleName;
    private String _companyId;
    private String _cfpDetailsTradeClass;
    private String _companyName;
    private Date _modifiedDate;
    private int _gcmCompanyDetailsSid;
    private int _itemCfpDetailsSid;
    private Date _createdDate;
    private int _createdBy;
    private Date _companyStartDate;
    private String _companyNo;
    private String _companyStatus;
    private String _sessionId;
    private Date _companyEndDate;
    private Date _cfpDetailsStartDate;
    private String _operation;
    private int _cfpModelSid;
    private int _modifiedBy;
    private String _subModuleName;
    private Date _cfpDetailsEndDate;
    private int _companyStatusSid;
    private int _companyMasterSid;
    private BaseModel<?> _gcmCompanyDetailsRemoteModel;

    public GcmCompanyDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return GcmCompanyDetails.class;
    }

    @Override
    public String getModelClassName() {
        return GcmCompanyDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _gcmCompanyDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setGcmCompanyDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _gcmCompanyDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("checkRecord", getCheckRecord());
        attributes.put("userId", getUserId());
        attributes.put("moduleName", getModuleName());
        attributes.put("companyId", getCompanyId());
        attributes.put("cfpDetailsTradeClass", getCfpDetailsTradeClass());
        attributes.put("companyName", getCompanyName());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("gcmCompanyDetailsSid", getGcmCompanyDetailsSid());
        attributes.put("itemCfpDetailsSid", getItemCfpDetailsSid());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("companyStartDate", getCompanyStartDate());
        attributes.put("companyNo", getCompanyNo());
        attributes.put("companyStatus", getCompanyStatus());
        attributes.put("sessionId", getSessionId());
        attributes.put("companyEndDate", getCompanyEndDate());
        attributes.put("cfpDetailsStartDate", getCfpDetailsStartDate());
        attributes.put("operation", getOperation());
        attributes.put("cfpModelSid", getCfpModelSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("subModuleName", getSubModuleName());
        attributes.put("cfpDetailsEndDate", getCfpDetailsEndDate());
        attributes.put("companyStatusSid", getCompanyStatusSid());
        attributes.put("companyMasterSid", getCompanyMasterSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        String moduleName = (String) attributes.get("moduleName");

        if (moduleName != null) {
            setModuleName(moduleName);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        String cfpDetailsTradeClass = (String) attributes.get(
                "cfpDetailsTradeClass");

        if (cfpDetailsTradeClass != null) {
            setCfpDetailsTradeClass(cfpDetailsTradeClass);
        }

        String companyName = (String) attributes.get("companyName");

        if (companyName != null) {
            setCompanyName(companyName);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer gcmCompanyDetailsSid = (Integer) attributes.get(
                "gcmCompanyDetailsSid");

        if (gcmCompanyDetailsSid != null) {
            setGcmCompanyDetailsSid(gcmCompanyDetailsSid);
        }

        Integer itemCfpDetailsSid = (Integer) attributes.get(
                "itemCfpDetailsSid");

        if (itemCfpDetailsSid != null) {
            setItemCfpDetailsSid(itemCfpDetailsSid);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date companyStartDate = (Date) attributes.get("companyStartDate");

        if (companyStartDate != null) {
            setCompanyStartDate(companyStartDate);
        }

        String companyNo = (String) attributes.get("companyNo");

        if (companyNo != null) {
            setCompanyNo(companyNo);
        }

        String companyStatus = (String) attributes.get("companyStatus");

        if (companyStatus != null) {
            setCompanyStatus(companyStatus);
        }

        String sessionId = (String) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        Date companyEndDate = (Date) attributes.get("companyEndDate");

        if (companyEndDate != null) {
            setCompanyEndDate(companyEndDate);
        }

        Date cfpDetailsStartDate = (Date) attributes.get("cfpDetailsStartDate");

        if (cfpDetailsStartDate != null) {
            setCfpDetailsStartDate(cfpDetailsStartDate);
        }

        String operation = (String) attributes.get("operation");

        if (operation != null) {
            setOperation(operation);
        }

        Integer cfpModelSid = (Integer) attributes.get("cfpModelSid");

        if (cfpModelSid != null) {
            setCfpModelSid(cfpModelSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String subModuleName = (String) attributes.get("subModuleName");

        if (subModuleName != null) {
            setSubModuleName(subModuleName);
        }

        Date cfpDetailsEndDate = (Date) attributes.get("cfpDetailsEndDate");

        if (cfpDetailsEndDate != null) {
            setCfpDetailsEndDate(cfpDetailsEndDate);
        }

        Integer companyStatusSid = (Integer) attributes.get("companyStatusSid");

        if (companyStatusSid != null) {
            setCompanyStatusSid(companyStatusSid);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
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

        if (_gcmCompanyDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmCompanyDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_gcmCompanyDetailsRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getUserId() {
        return _userId;
    }

    @Override
    public void setUserId(int userId) {
        _userId = userId;

        if (_gcmCompanyDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmCompanyDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", int.class);

                method.invoke(_gcmCompanyDetailsRemoteModel, userId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getModuleName() {
        return _moduleName;
    }

    @Override
    public void setModuleName(String moduleName) {
        _moduleName = moduleName;

        if (_gcmCompanyDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmCompanyDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModuleName", String.class);

                method.invoke(_gcmCompanyDetailsRemoteModel, moduleName);
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

        if (_gcmCompanyDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmCompanyDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyId", String.class);

                method.invoke(_gcmCompanyDetailsRemoteModel, companyId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCfpDetailsTradeClass() {
        return _cfpDetailsTradeClass;
    }

    @Override
    public void setCfpDetailsTradeClass(String cfpDetailsTradeClass) {
        _cfpDetailsTradeClass = cfpDetailsTradeClass;

        if (_gcmCompanyDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmCompanyDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpDetailsTradeClass",
                        String.class);

                method.invoke(_gcmCompanyDetailsRemoteModel,
                    cfpDetailsTradeClass);
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

        if (_gcmCompanyDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmCompanyDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyName", String.class);

                method.invoke(_gcmCompanyDetailsRemoteModel, companyName);
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

        if (_gcmCompanyDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmCompanyDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_gcmCompanyDetailsRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getGcmCompanyDetailsSid() {
        return _gcmCompanyDetailsSid;
    }

    @Override
    public void setGcmCompanyDetailsSid(int gcmCompanyDetailsSid) {
        _gcmCompanyDetailsSid = gcmCompanyDetailsSid;

        if (_gcmCompanyDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmCompanyDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setGcmCompanyDetailsSid",
                        int.class);

                method.invoke(_gcmCompanyDetailsRemoteModel,
                    gcmCompanyDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getItemCfpDetailsSid() {
        return _itemCfpDetailsSid;
    }

    @Override
    public void setItemCfpDetailsSid(int itemCfpDetailsSid) {
        _itemCfpDetailsSid = itemCfpDetailsSid;

        if (_gcmCompanyDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmCompanyDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemCfpDetailsSid",
                        int.class);

                method.invoke(_gcmCompanyDetailsRemoteModel, itemCfpDetailsSid);
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

        if (_gcmCompanyDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmCompanyDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_gcmCompanyDetailsRemoteModel, createdDate);
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

        if (_gcmCompanyDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmCompanyDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_gcmCompanyDetailsRemoteModel, createdBy);
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

        if (_gcmCompanyDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmCompanyDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyStartDate",
                        Date.class);

                method.invoke(_gcmCompanyDetailsRemoteModel, companyStartDate);
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

        if (_gcmCompanyDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmCompanyDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyNo", String.class);

                method.invoke(_gcmCompanyDetailsRemoteModel, companyNo);
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

        if (_gcmCompanyDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmCompanyDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyStatus", String.class);

                method.invoke(_gcmCompanyDetailsRemoteModel, companyStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSessionId() {
        return _sessionId;
    }

    @Override
    public void setSessionId(String sessionId) {
        _sessionId = sessionId;

        if (_gcmCompanyDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmCompanyDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", String.class);

                method.invoke(_gcmCompanyDetailsRemoteModel, sessionId);
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

        if (_gcmCompanyDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmCompanyDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyEndDate", Date.class);

                method.invoke(_gcmCompanyDetailsRemoteModel, companyEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCfpDetailsStartDate() {
        return _cfpDetailsStartDate;
    }

    @Override
    public void setCfpDetailsStartDate(Date cfpDetailsStartDate) {
        _cfpDetailsStartDate = cfpDetailsStartDate;

        if (_gcmCompanyDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmCompanyDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpDetailsStartDate",
                        Date.class);

                method.invoke(_gcmCompanyDetailsRemoteModel, cfpDetailsStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getOperation() {
        return _operation;
    }

    @Override
    public void setOperation(String operation) {
        _operation = operation;

        if (_gcmCompanyDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmCompanyDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setOperation", String.class);

                method.invoke(_gcmCompanyDetailsRemoteModel, operation);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCfpModelSid() {
        return _cfpModelSid;
    }

    @Override
    public void setCfpModelSid(int cfpModelSid) {
        _cfpModelSid = cfpModelSid;

        if (_gcmCompanyDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmCompanyDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpModelSid", int.class);

                method.invoke(_gcmCompanyDetailsRemoteModel, cfpModelSid);
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

        if (_gcmCompanyDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmCompanyDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_gcmCompanyDetailsRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSubModuleName() {
        return _subModuleName;
    }

    @Override
    public void setSubModuleName(String subModuleName) {
        _subModuleName = subModuleName;

        if (_gcmCompanyDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmCompanyDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSubModuleName", String.class);

                method.invoke(_gcmCompanyDetailsRemoteModel, subModuleName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCfpDetailsEndDate() {
        return _cfpDetailsEndDate;
    }

    @Override
    public void setCfpDetailsEndDate(Date cfpDetailsEndDate) {
        _cfpDetailsEndDate = cfpDetailsEndDate;

        if (_gcmCompanyDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmCompanyDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpDetailsEndDate",
                        Date.class);

                method.invoke(_gcmCompanyDetailsRemoteModel, cfpDetailsEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCompanyStatusSid() {
        return _companyStatusSid;
    }

    @Override
    public void setCompanyStatusSid(int companyStatusSid) {
        _companyStatusSid = companyStatusSid;

        if (_gcmCompanyDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmCompanyDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyStatusSid", int.class);

                method.invoke(_gcmCompanyDetailsRemoteModel, companyStatusSid);
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

        if (_gcmCompanyDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmCompanyDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyMasterSid", int.class);

                method.invoke(_gcmCompanyDetailsRemoteModel, companyMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getGcmCompanyDetailsRemoteModel() {
        return _gcmCompanyDetailsRemoteModel;
    }

    public void setGcmCompanyDetailsRemoteModel(
        BaseModel<?> gcmCompanyDetailsRemoteModel) {
        _gcmCompanyDetailsRemoteModel = gcmCompanyDetailsRemoteModel;
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

        Class<?> remoteModelClass = _gcmCompanyDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_gcmCompanyDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            GcmCompanyDetailsLocalServiceUtil.addGcmCompanyDetails(this);
        } else {
            GcmCompanyDetailsLocalServiceUtil.updateGcmCompanyDetails(this);
        }
    }

    @Override
    public GcmCompanyDetails toEscapedModel() {
        return (GcmCompanyDetails) ProxyUtil.newProxyInstance(GcmCompanyDetails.class.getClassLoader(),
            new Class[] { GcmCompanyDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        GcmCompanyDetailsClp clone = new GcmCompanyDetailsClp();

        clone.setCheckRecord(getCheckRecord());
        clone.setUserId(getUserId());
        clone.setModuleName(getModuleName());
        clone.setCompanyId(getCompanyId());
        clone.setCfpDetailsTradeClass(getCfpDetailsTradeClass());
        clone.setCompanyName(getCompanyName());
        clone.setModifiedDate(getModifiedDate());
        clone.setGcmCompanyDetailsSid(getGcmCompanyDetailsSid());
        clone.setItemCfpDetailsSid(getItemCfpDetailsSid());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setCompanyStartDate(getCompanyStartDate());
        clone.setCompanyNo(getCompanyNo());
        clone.setCompanyStatus(getCompanyStatus());
        clone.setSessionId(getSessionId());
        clone.setCompanyEndDate(getCompanyEndDate());
        clone.setCfpDetailsStartDate(getCfpDetailsStartDate());
        clone.setOperation(getOperation());
        clone.setCfpModelSid(getCfpModelSid());
        clone.setModifiedBy(getModifiedBy());
        clone.setSubModuleName(getSubModuleName());
        clone.setCfpDetailsEndDate(getCfpDetailsEndDate());
        clone.setCompanyStatusSid(getCompanyStatusSid());
        clone.setCompanyMasterSid(getCompanyMasterSid());

        return clone;
    }

    @Override
    public int compareTo(GcmCompanyDetails gcmCompanyDetails) {
        int primaryKey = gcmCompanyDetails.getPrimaryKey();

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

        if (!(obj instanceof GcmCompanyDetailsClp)) {
            return false;
        }

        GcmCompanyDetailsClp gcmCompanyDetails = (GcmCompanyDetailsClp) obj;

        int primaryKey = gcmCompanyDetails.getPrimaryKey();

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

        sb.append("{checkRecord=");
        sb.append(getCheckRecord());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", moduleName=");
        sb.append(getModuleName());
        sb.append(", companyId=");
        sb.append(getCompanyId());
        sb.append(", cfpDetailsTradeClass=");
        sb.append(getCfpDetailsTradeClass());
        sb.append(", companyName=");
        sb.append(getCompanyName());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", gcmCompanyDetailsSid=");
        sb.append(getGcmCompanyDetailsSid());
        sb.append(", itemCfpDetailsSid=");
        sb.append(getItemCfpDetailsSid());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", companyStartDate=");
        sb.append(getCompanyStartDate());
        sb.append(", companyNo=");
        sb.append(getCompanyNo());
        sb.append(", companyStatus=");
        sb.append(getCompanyStatus());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append(", companyEndDate=");
        sb.append(getCompanyEndDate());
        sb.append(", cfpDetailsStartDate=");
        sb.append(getCfpDetailsStartDate());
        sb.append(", operation=");
        sb.append(getOperation());
        sb.append(", cfpModelSid=");
        sb.append(getCfpModelSid());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", subModuleName=");
        sb.append(getSubModuleName());
        sb.append(", cfpDetailsEndDate=");
        sb.append(getCfpDetailsEndDate());
        sb.append(", companyStatusSid=");
        sb.append(getCompanyStatusSid());
        sb.append(", companyMasterSid=");
        sb.append(getCompanyMasterSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(76);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.GcmCompanyDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>moduleName</column-name><column-value><![CDATA[");
        sb.append(getModuleName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyId</column-name><column-value><![CDATA[");
        sb.append(getCompanyId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpDetailsTradeClass</column-name><column-value><![CDATA[");
        sb.append(getCfpDetailsTradeClass());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyName</column-name><column-value><![CDATA[");
        sb.append(getCompanyName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>gcmCompanyDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getGcmCompanyDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemCfpDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getItemCfpDetailsSid());
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
            "<column><column-name>companyStartDate</column-name><column-value><![CDATA[");
        sb.append(getCompanyStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyNo</column-name><column-value><![CDATA[");
        sb.append(getCompanyNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyStatus</column-name><column-value><![CDATA[");
        sb.append(getCompanyStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sessionId</column-name><column-value><![CDATA[");
        sb.append(getSessionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyEndDate</column-name><column-value><![CDATA[");
        sb.append(getCompanyEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpDetailsStartDate</column-name><column-value><![CDATA[");
        sb.append(getCfpDetailsStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>operation</column-name><column-value><![CDATA[");
        sb.append(getOperation());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpModelSid</column-name><column-value><![CDATA[");
        sb.append(getCfpModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>subModuleName</column-name><column-value><![CDATA[");
        sb.append(getSubModuleName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpDetailsEndDate</column-name><column-value><![CDATA[");
        sb.append(getCfpDetailsEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyStatusSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyStatusSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyMasterSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
