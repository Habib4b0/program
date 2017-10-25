package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.ClpSerializer;
import com.stpl.app.parttwo.service.IvldCompanyParentLocalServiceUtil;

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


public class IvldCompanyParentClp extends BaseModelImpl<IvldCompanyParent>
    implements IvldCompanyParent {
    private String _parentCompanyId;
    private String _priorParentCompanyId;
    private String _reasonForFailure;
    private String _companyId;
    private String _lastUpdatedDate;
    private String _parentEndDate;
    private Date _modifiedDate;
    private String _parentDetailsIntfid;
    private String _priorParentStartDate;
    private String _source;
    private String _createdBy;
    private Date _createdDate;
    private String _addChgDelIndicator;
    private String _batchId;
    private int _ivldCompanyParentSid;
    private String _errorField;
    private String _errorCode;
    private Date _intfInsertedDate;
    private String _modifiedBy;
    private String _reprocessedFlag;
    private String _parentStartDate;
    private boolean _checkRecord;
    private BaseModel<?> _ivldCompanyParentRemoteModel;

    public IvldCompanyParentClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return IvldCompanyParent.class;
    }

    @Override
    public String getModelClassName() {
        return IvldCompanyParent.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _ivldCompanyParentSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIvldCompanyParentSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ivldCompanyParentSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("parentCompanyId", getParentCompanyId());
        attributes.put("priorParentCompanyId", getPriorParentCompanyId());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("companyId", getCompanyId());
        attributes.put("lastUpdatedDate", getLastUpdatedDate());
        attributes.put("parentEndDate", getParentEndDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("parentDetailsIntfid", getParentDetailsIntfid());
        attributes.put("priorParentStartDate", getPriorParentStartDate());
        attributes.put("source", getSource());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("batchId", getBatchId());
        attributes.put("ivldCompanyParentSid", getIvldCompanyParentSid());
        attributes.put("errorField", getErrorField());
        attributes.put("errorCode", getErrorCode());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("parentStartDate", getParentStartDate());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String parentCompanyId = (String) attributes.get("parentCompanyId");

        if (parentCompanyId != null) {
            setParentCompanyId(parentCompanyId);
        }

        String priorParentCompanyId = (String) attributes.get(
                "priorParentCompanyId");

        if (priorParentCompanyId != null) {
            setPriorParentCompanyId(priorParentCompanyId);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        String lastUpdatedDate = (String) attributes.get("lastUpdatedDate");

        if (lastUpdatedDate != null) {
            setLastUpdatedDate(lastUpdatedDate);
        }

        String parentEndDate = (String) attributes.get("parentEndDate");

        if (parentEndDate != null) {
            setParentEndDate(parentEndDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String parentDetailsIntfid = (String) attributes.get(
                "parentDetailsIntfid");

        if (parentDetailsIntfid != null) {
            setParentDetailsIntfid(parentDetailsIntfid);
        }

        String priorParentStartDate = (String) attributes.get(
                "priorParentStartDate");

        if (priorParentStartDate != null) {
            setPriorParentStartDate(priorParentStartDate);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Integer ivldCompanyParentSid = (Integer) attributes.get(
                "ivldCompanyParentSid");

        if (ivldCompanyParentSid != null) {
            setIvldCompanyParentSid(ivldCompanyParentSid);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
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

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String parentStartDate = (String) attributes.get("parentStartDate");

        if (parentStartDate != null) {
            setParentStartDate(parentStartDate);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    @Override
    public String getParentCompanyId() {
        return _parentCompanyId;
    }

    @Override
    public void setParentCompanyId(String parentCompanyId) {
        _parentCompanyId = parentCompanyId;

        if (_ivldCompanyParentRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyParentRemoteModel.getClass();

                Method method = clazz.getMethod("setParentCompanyId",
                        String.class);

                method.invoke(_ivldCompanyParentRemoteModel, parentCompanyId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPriorParentCompanyId() {
        return _priorParentCompanyId;
    }

    @Override
    public void setPriorParentCompanyId(String priorParentCompanyId) {
        _priorParentCompanyId = priorParentCompanyId;

        if (_ivldCompanyParentRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyParentRemoteModel.getClass();

                Method method = clazz.getMethod("setPriorParentCompanyId",
                        String.class);

                method.invoke(_ivldCompanyParentRemoteModel,
                    priorParentCompanyId);
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

        if (_ivldCompanyParentRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyParentRemoteModel.getClass();

                Method method = clazz.getMethod("setReasonForFailure",
                        String.class);

                method.invoke(_ivldCompanyParentRemoteModel, reasonForFailure);
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

        if (_ivldCompanyParentRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyParentRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyId", String.class);

                method.invoke(_ivldCompanyParentRemoteModel, companyId);
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

        if (_ivldCompanyParentRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyParentRemoteModel.getClass();

                Method method = clazz.getMethod("setLastUpdatedDate",
                        String.class);

                method.invoke(_ivldCompanyParentRemoteModel, lastUpdatedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getParentEndDate() {
        return _parentEndDate;
    }

    @Override
    public void setParentEndDate(String parentEndDate) {
        _parentEndDate = parentEndDate;

        if (_ivldCompanyParentRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyParentRemoteModel.getClass();

                Method method = clazz.getMethod("setParentEndDate", String.class);

                method.invoke(_ivldCompanyParentRemoteModel, parentEndDate);
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

        if (_ivldCompanyParentRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyParentRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_ivldCompanyParentRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getParentDetailsIntfid() {
        return _parentDetailsIntfid;
    }

    @Override
    public void setParentDetailsIntfid(String parentDetailsIntfid) {
        _parentDetailsIntfid = parentDetailsIntfid;

        if (_ivldCompanyParentRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyParentRemoteModel.getClass();

                Method method = clazz.getMethod("setParentDetailsIntfid",
                        String.class);

                method.invoke(_ivldCompanyParentRemoteModel, parentDetailsIntfid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPriorParentStartDate() {
        return _priorParentStartDate;
    }

    @Override
    public void setPriorParentStartDate(String priorParentStartDate) {
        _priorParentStartDate = priorParentStartDate;

        if (_ivldCompanyParentRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyParentRemoteModel.getClass();

                Method method = clazz.getMethod("setPriorParentStartDate",
                        String.class);

                method.invoke(_ivldCompanyParentRemoteModel,
                    priorParentStartDate);
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

        if (_ivldCompanyParentRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyParentRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_ivldCompanyParentRemoteModel, source);
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

        if (_ivldCompanyParentRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyParentRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_ivldCompanyParentRemoteModel, createdBy);
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

        if (_ivldCompanyParentRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyParentRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_ivldCompanyParentRemoteModel, createdDate);
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

        if (_ivldCompanyParentRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyParentRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_ivldCompanyParentRemoteModel, addChgDelIndicator);
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

        if (_ivldCompanyParentRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyParentRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_ivldCompanyParentRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIvldCompanyParentSid() {
        return _ivldCompanyParentSid;
    }

    @Override
    public void setIvldCompanyParentSid(int ivldCompanyParentSid) {
        _ivldCompanyParentSid = ivldCompanyParentSid;

        if (_ivldCompanyParentRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyParentRemoteModel.getClass();

                Method method = clazz.getMethod("setIvldCompanyParentSid",
                        int.class);

                method.invoke(_ivldCompanyParentRemoteModel,
                    ivldCompanyParentSid);
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

        if (_ivldCompanyParentRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyParentRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorField", String.class);

                method.invoke(_ivldCompanyParentRemoteModel, errorField);
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

        if (_ivldCompanyParentRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyParentRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorCode", String.class);

                method.invoke(_ivldCompanyParentRemoteModel, errorCode);
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

        if (_ivldCompanyParentRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyParentRemoteModel.getClass();

                Method method = clazz.getMethod("setIntfInsertedDate",
                        Date.class);

                method.invoke(_ivldCompanyParentRemoteModel, intfInsertedDate);
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

        if (_ivldCompanyParentRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyParentRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_ivldCompanyParentRemoteModel, modifiedBy);
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

        if (_ivldCompanyParentRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyParentRemoteModel.getClass();

                Method method = clazz.getMethod("setReprocessedFlag",
                        String.class);

                method.invoke(_ivldCompanyParentRemoteModel, reprocessedFlag);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getParentStartDate() {
        return _parentStartDate;
    }

    @Override
    public void setParentStartDate(String parentStartDate) {
        _parentStartDate = parentStartDate;

        if (_ivldCompanyParentRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyParentRemoteModel.getClass();

                Method method = clazz.getMethod("setParentStartDate",
                        String.class);

                method.invoke(_ivldCompanyParentRemoteModel, parentStartDate);
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

        if (_ivldCompanyParentRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyParentRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_ivldCompanyParentRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getIvldCompanyParentRemoteModel() {
        return _ivldCompanyParentRemoteModel;
    }

    public void setIvldCompanyParentRemoteModel(
        BaseModel<?> ivldCompanyParentRemoteModel) {
        _ivldCompanyParentRemoteModel = ivldCompanyParentRemoteModel;
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

        Class<?> remoteModelClass = _ivldCompanyParentRemoteModel.getClass();

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

        Object returnValue = method.invoke(_ivldCompanyParentRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IvldCompanyParentLocalServiceUtil.addIvldCompanyParent(this);
        } else {
            IvldCompanyParentLocalServiceUtil.updateIvldCompanyParent(this);
        }
    }

    @Override
    public IvldCompanyParent toEscapedModel() {
        return (IvldCompanyParent) ProxyUtil.newProxyInstance(IvldCompanyParent.class.getClassLoader(),
            new Class[] { IvldCompanyParent.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        IvldCompanyParentClp clone = new IvldCompanyParentClp();

        clone.setParentCompanyId(getParentCompanyId());
        clone.setPriorParentCompanyId(getPriorParentCompanyId());
        clone.setReasonForFailure(getReasonForFailure());
        clone.setCompanyId(getCompanyId());
        clone.setLastUpdatedDate(getLastUpdatedDate());
        clone.setParentEndDate(getParentEndDate());
        clone.setModifiedDate(getModifiedDate());
        clone.setParentDetailsIntfid(getParentDetailsIntfid());
        clone.setPriorParentStartDate(getPriorParentStartDate());
        clone.setSource(getSource());
        clone.setCreatedBy(getCreatedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setBatchId(getBatchId());
        clone.setIvldCompanyParentSid(getIvldCompanyParentSid());
        clone.setErrorField(getErrorField());
        clone.setErrorCode(getErrorCode());
        clone.setIntfInsertedDate(getIntfInsertedDate());
        clone.setModifiedBy(getModifiedBy());
        clone.setReprocessedFlag(getReprocessedFlag());
        clone.setParentStartDate(getParentStartDate());
        clone.setCheckRecord(getCheckRecord());

        return clone;
    }

    @Override
    public int compareTo(IvldCompanyParent ivldCompanyParent) {
        int primaryKey = ivldCompanyParent.getPrimaryKey();

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

        if (!(obj instanceof IvldCompanyParentClp)) {
            return false;
        }

        IvldCompanyParentClp ivldCompanyParent = (IvldCompanyParentClp) obj;

        int primaryKey = ivldCompanyParent.getPrimaryKey();

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
        StringBundler sb = new StringBundler(45);

        sb.append("{parentCompanyId=");
        sb.append(getParentCompanyId());
        sb.append(", priorParentCompanyId=");
        sb.append(getPriorParentCompanyId());
        sb.append(", reasonForFailure=");
        sb.append(getReasonForFailure());
        sb.append(", companyId=");
        sb.append(getCompanyId());
        sb.append(", lastUpdatedDate=");
        sb.append(getLastUpdatedDate());
        sb.append(", parentEndDate=");
        sb.append(getParentEndDate());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", parentDetailsIntfid=");
        sb.append(getParentDetailsIntfid());
        sb.append(", priorParentStartDate=");
        sb.append(getPriorParentStartDate());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", ivldCompanyParentSid=");
        sb.append(getIvldCompanyParentSid());
        sb.append(", errorField=");
        sb.append(getErrorField());
        sb.append(", errorCode=");
        sb.append(getErrorCode());
        sb.append(", intfInsertedDate=");
        sb.append(getIntfInsertedDate());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", reprocessedFlag=");
        sb.append(getReprocessedFlag());
        sb.append(", parentStartDate=");
        sb.append(getParentStartDate());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(70);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.IvldCompanyParent");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>parentCompanyId</column-name><column-value><![CDATA[");
        sb.append(getParentCompanyId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priorParentCompanyId</column-name><column-value><![CDATA[");
        sb.append(getPriorParentCompanyId());
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
            "<column><column-name>lastUpdatedDate</column-name><column-value><![CDATA[");
        sb.append(getLastUpdatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parentEndDate</column-name><column-value><![CDATA[");
        sb.append(getParentEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parentDetailsIntfid</column-name><column-value><![CDATA[");
        sb.append(getParentDetailsIntfid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priorParentStartDate</column-name><column-value><![CDATA[");
        sb.append(getPriorParentStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>addChgDelIndicator</column-name><column-value><![CDATA[");
        sb.append(getAddChgDelIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ivldCompanyParentSid</column-name><column-value><![CDATA[");
        sb.append(getIvldCompanyParentSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>errorField</column-name><column-value><![CDATA[");
        sb.append(getErrorField());
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
            "<column><column-name>reprocessedFlag</column-name><column-value><![CDATA[");
        sb.append(getReprocessedFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parentStartDate</column-name><column-value><![CDATA[");
        sb.append(getParentStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
