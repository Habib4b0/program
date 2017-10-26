package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.IvldGlCostCenterLocalServiceUtil;

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


public class IvldGlCostCenterClp extends BaseModelImpl<IvldGlCostCenter>
    implements IvldGlCostCenter {
    private String _reasonForFailure;
    private String _glCostCenterIntfid;
    private Date _modifiedDate;
    private String _companyCostCenter;
    private String _uploadDate;
    private String _createdBy;
    private Date _createdDate;
    private String _source;
    private String _batchId;
    private String _addChgDelIndicator;
    private int _ivldGlCostCenterSid;
    private String _errorField;
    private String _errorCode;
    private Date _intfInsertedDate;
    private String _companyCode;
    private String _modifiedBy;
    private String _reprocessedFlag;
    private String _ndc8;
    private boolean _checkRecord;
    private BaseModel<?> _ivldGlCostCenterRemoteModel;

    public IvldGlCostCenterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return IvldGlCostCenter.class;
    }

    @Override
    public String getModelClassName() {
        return IvldGlCostCenter.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _ivldGlCostCenterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIvldGlCostCenterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ivldGlCostCenterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("glCostCenterIntfid", getGlCostCenterIntfid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("companyCostCenter", getCompanyCostCenter());
        attributes.put("uploadDate", getUploadDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("source", getSource());
        attributes.put("batchId", getBatchId());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("ivldGlCostCenterSid", getIvldGlCostCenterSid());
        attributes.put("errorField", getErrorField());
        attributes.put("errorCode", getErrorCode());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("companyCode", getCompanyCode());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("ndc8", getNdc8());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String glCostCenterIntfid = (String) attributes.get(
                "glCostCenterIntfid");

        if (glCostCenterIntfid != null) {
            setGlCostCenterIntfid(glCostCenterIntfid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String companyCostCenter = (String) attributes.get("companyCostCenter");

        if (companyCostCenter != null) {
            setCompanyCostCenter(companyCostCenter);
        }

        String uploadDate = (String) attributes.get("uploadDate");

        if (uploadDate != null) {
            setUploadDate(uploadDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        Integer ivldGlCostCenterSid = (Integer) attributes.get(
                "ivldGlCostCenterSid");

        if (ivldGlCostCenterSid != null) {
            setIvldGlCostCenterSid(ivldGlCostCenterSid);
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

        String companyCode = (String) attributes.get("companyCode");

        if (companyCode != null) {
            setCompanyCode(companyCode);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String ndc8 = (String) attributes.get("ndc8");

        if (ndc8 != null) {
            setNdc8(ndc8);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    @Override
    public String getReasonForFailure() {
        return _reasonForFailure;
    }

    @Override
    public void setReasonForFailure(String reasonForFailure) {
        _reasonForFailure = reasonForFailure;

        if (_ivldGlCostCenterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlCostCenterRemoteModel.getClass();

                Method method = clazz.getMethod("setReasonForFailure",
                        String.class);

                method.invoke(_ivldGlCostCenterRemoteModel, reasonForFailure);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getGlCostCenterIntfid() {
        return _glCostCenterIntfid;
    }

    @Override
    public void setGlCostCenterIntfid(String glCostCenterIntfid) {
        _glCostCenterIntfid = glCostCenterIntfid;

        if (_ivldGlCostCenterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlCostCenterRemoteModel.getClass();

                Method method = clazz.getMethod("setGlCostCenterIntfid",
                        String.class);

                method.invoke(_ivldGlCostCenterRemoteModel, glCostCenterIntfid);
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

        if (_ivldGlCostCenterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlCostCenterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_ivldGlCostCenterRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyCostCenter() {
        return _companyCostCenter;
    }

    @Override
    public void setCompanyCostCenter(String companyCostCenter) {
        _companyCostCenter = companyCostCenter;

        if (_ivldGlCostCenterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlCostCenterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyCostCenter",
                        String.class);

                method.invoke(_ivldGlCostCenterRemoteModel, companyCostCenter);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUploadDate() {
        return _uploadDate;
    }

    @Override
    public void setUploadDate(String uploadDate) {
        _uploadDate = uploadDate;

        if (_ivldGlCostCenterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlCostCenterRemoteModel.getClass();

                Method method = clazz.getMethod("setUploadDate", String.class);

                method.invoke(_ivldGlCostCenterRemoteModel, uploadDate);
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

        if (_ivldGlCostCenterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlCostCenterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_ivldGlCostCenterRemoteModel, createdBy);
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

        if (_ivldGlCostCenterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlCostCenterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_ivldGlCostCenterRemoteModel, createdDate);
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

        if (_ivldGlCostCenterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlCostCenterRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_ivldGlCostCenterRemoteModel, source);
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

        if (_ivldGlCostCenterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlCostCenterRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_ivldGlCostCenterRemoteModel, batchId);
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

        if (_ivldGlCostCenterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlCostCenterRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_ivldGlCostCenterRemoteModel, addChgDelIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIvldGlCostCenterSid() {
        return _ivldGlCostCenterSid;
    }

    @Override
    public void setIvldGlCostCenterSid(int ivldGlCostCenterSid) {
        _ivldGlCostCenterSid = ivldGlCostCenterSid;

        if (_ivldGlCostCenterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlCostCenterRemoteModel.getClass();

                Method method = clazz.getMethod("setIvldGlCostCenterSid",
                        int.class);

                method.invoke(_ivldGlCostCenterRemoteModel, ivldGlCostCenterSid);
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

        if (_ivldGlCostCenterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlCostCenterRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorField", String.class);

                method.invoke(_ivldGlCostCenterRemoteModel, errorField);
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

        if (_ivldGlCostCenterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlCostCenterRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorCode", String.class);

                method.invoke(_ivldGlCostCenterRemoteModel, errorCode);
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

        if (_ivldGlCostCenterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlCostCenterRemoteModel.getClass();

                Method method = clazz.getMethod("setIntfInsertedDate",
                        Date.class);

                method.invoke(_ivldGlCostCenterRemoteModel, intfInsertedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyCode() {
        return _companyCode;
    }

    @Override
    public void setCompanyCode(String companyCode) {
        _companyCode = companyCode;

        if (_ivldGlCostCenterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlCostCenterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyCode", String.class);

                method.invoke(_ivldGlCostCenterRemoteModel, companyCode);
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

        if (_ivldGlCostCenterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlCostCenterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_ivldGlCostCenterRemoteModel, modifiedBy);
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

        if (_ivldGlCostCenterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlCostCenterRemoteModel.getClass();

                Method method = clazz.getMethod("setReprocessedFlag",
                        String.class);

                method.invoke(_ivldGlCostCenterRemoteModel, reprocessedFlag);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNdc8() {
        return _ndc8;
    }

    @Override
    public void setNdc8(String ndc8) {
        _ndc8 = ndc8;

        if (_ivldGlCostCenterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlCostCenterRemoteModel.getClass();

                Method method = clazz.getMethod("setNdc8", String.class);

                method.invoke(_ivldGlCostCenterRemoteModel, ndc8);
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

        if (_ivldGlCostCenterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlCostCenterRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_ivldGlCostCenterRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getIvldGlCostCenterRemoteModel() {
        return _ivldGlCostCenterRemoteModel;
    }

    public void setIvldGlCostCenterRemoteModel(
        BaseModel<?> ivldGlCostCenterRemoteModel) {
        _ivldGlCostCenterRemoteModel = ivldGlCostCenterRemoteModel;
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

        Class<?> remoteModelClass = _ivldGlCostCenterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_ivldGlCostCenterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IvldGlCostCenterLocalServiceUtil.addIvldGlCostCenter(this);
        } else {
            IvldGlCostCenterLocalServiceUtil.updateIvldGlCostCenter(this);
        }
    }

    @Override
    public IvldGlCostCenter toEscapedModel() {
        return (IvldGlCostCenter) ProxyUtil.newProxyInstance(IvldGlCostCenter.class.getClassLoader(),
            new Class[] { IvldGlCostCenter.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        IvldGlCostCenterClp clone = new IvldGlCostCenterClp();

        clone.setReasonForFailure(getReasonForFailure());
        clone.setGlCostCenterIntfid(getGlCostCenterIntfid());
        clone.setModifiedDate(getModifiedDate());
        clone.setCompanyCostCenter(getCompanyCostCenter());
        clone.setUploadDate(getUploadDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setSource(getSource());
        clone.setBatchId(getBatchId());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setIvldGlCostCenterSid(getIvldGlCostCenterSid());
        clone.setErrorField(getErrorField());
        clone.setErrorCode(getErrorCode());
        clone.setIntfInsertedDate(getIntfInsertedDate());
        clone.setCompanyCode(getCompanyCode());
        clone.setModifiedBy(getModifiedBy());
        clone.setReprocessedFlag(getReprocessedFlag());
        clone.setNdc8(getNdc8());
        clone.setCheckRecord(getCheckRecord());

        return clone;
    }

    @Override
    public int compareTo(IvldGlCostCenter ivldGlCostCenter) {
        int primaryKey = ivldGlCostCenter.getPrimaryKey();

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

        if (!(obj instanceof IvldGlCostCenterClp)) {
            return false;
        }

        IvldGlCostCenterClp ivldGlCostCenter = (IvldGlCostCenterClp) obj;

        int primaryKey = ivldGlCostCenter.getPrimaryKey();

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
        StringBundler sb = new StringBundler(39);

        sb.append("{reasonForFailure=");
        sb.append(getReasonForFailure());
        sb.append(", glCostCenterIntfid=");
        sb.append(getGlCostCenterIntfid());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", companyCostCenter=");
        sb.append(getCompanyCostCenter());
        sb.append(", uploadDate=");
        sb.append(getUploadDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", ivldGlCostCenterSid=");
        sb.append(getIvldGlCostCenterSid());
        sb.append(", errorField=");
        sb.append(getErrorField());
        sb.append(", errorCode=");
        sb.append(getErrorCode());
        sb.append(", intfInsertedDate=");
        sb.append(getIntfInsertedDate());
        sb.append(", companyCode=");
        sb.append(getCompanyCode());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", reprocessedFlag=");
        sb.append(getReprocessedFlag());
        sb.append(", ndc8=");
        sb.append(getNdc8());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(61);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.IvldGlCostCenter");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>reasonForFailure</column-name><column-value><![CDATA[");
        sb.append(getReasonForFailure());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>glCostCenterIntfid</column-name><column-value><![CDATA[");
        sb.append(getGlCostCenterIntfid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyCostCenter</column-name><column-value><![CDATA[");
        sb.append(getCompanyCostCenter());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>uploadDate</column-name><column-value><![CDATA[");
        sb.append(getUploadDate());
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
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>addChgDelIndicator</column-name><column-value><![CDATA[");
        sb.append(getAddChgDelIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ivldGlCostCenterSid</column-name><column-value><![CDATA[");
        sb.append(getIvldGlCostCenterSid());
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
            "<column><column-name>companyCode</column-name><column-value><![CDATA[");
        sb.append(getCompanyCode());
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
            "<column><column-name>ndc8</column-name><column-value><![CDATA[");
        sb.append(getNdc8());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
