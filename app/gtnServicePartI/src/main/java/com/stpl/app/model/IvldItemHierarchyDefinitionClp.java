package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.IvldItemHierarchyDefinitionLocalServiceUtil;

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


public class IvldItemHierarchyDefinitionClp extends BaseModelImpl<IvldItemHierarchyDefinition>
    implements IvldItemHierarchyDefinition {
    private String _member;
    private String _reasonForFailure;
    private String _itemHierarchyDefnIntfid;
    private String _bpiLvl;
    private Date _modifiedDate;
    private String _alias;
    private String _createdBy;
    private Date _createdDate;
    private String _source;
    private String _batchId;
    private String _addChgDelIndicator;
    private int _ivldItemHierarchyDefinitionSid;
    private String _errorField;
    private String _errorCode;
    private Date _intfInsertedDate;
    private String _modifiedBy;
    private String _reprocessedFlag;
    private boolean _checkRecord;
    private BaseModel<?> _ivldItemHierarchyDefinitionRemoteModel;

    public IvldItemHierarchyDefinitionClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return IvldItemHierarchyDefinition.class;
    }

    @Override
    public String getModelClassName() {
        return IvldItemHierarchyDefinition.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _ivldItemHierarchyDefinitionSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIvldItemHierarchyDefinitionSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ivldItemHierarchyDefinitionSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("member", getMember());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("itemHierarchyDefnIntfid", getItemHierarchyDefnIntfid());
        attributes.put("bpiLvl", getBpiLvl());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("alias", getAlias());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("source", getSource());
        attributes.put("batchId", getBatchId());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("ivldItemHierarchyDefinitionSid",
            getIvldItemHierarchyDefinitionSid());
        attributes.put("errorField", getErrorField());
        attributes.put("errorCode", getErrorCode());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String member = (String) attributes.get("member");

        if (member != null) {
            setMember(member);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String itemHierarchyDefnIntfid = (String) attributes.get(
                "itemHierarchyDefnIntfid");

        if (itemHierarchyDefnIntfid != null) {
            setItemHierarchyDefnIntfid(itemHierarchyDefnIntfid);
        }

        String bpiLvl = (String) attributes.get("bpiLvl");

        if (bpiLvl != null) {
            setBpiLvl(bpiLvl);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String alias = (String) attributes.get("alias");

        if (alias != null) {
            setAlias(alias);
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

        Integer ivldItemHierarchyDefinitionSid = (Integer) attributes.get(
                "ivldItemHierarchyDefinitionSid");

        if (ivldItemHierarchyDefinitionSid != null) {
            setIvldItemHierarchyDefinitionSid(ivldItemHierarchyDefinitionSid);
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

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    @Override
    public String getMember() {
        return _member;
    }

    @Override
    public void setMember(String member) {
        _member = member;

        if (_ivldItemHierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemHierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setMember", String.class);

                method.invoke(_ivldItemHierarchyDefinitionRemoteModel, member);
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

        if (_ivldItemHierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemHierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setReasonForFailure",
                        String.class);

                method.invoke(_ivldItemHierarchyDefinitionRemoteModel,
                    reasonForFailure);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemHierarchyDefnIntfid() {
        return _itemHierarchyDefnIntfid;
    }

    @Override
    public void setItemHierarchyDefnIntfid(String itemHierarchyDefnIntfid) {
        _itemHierarchyDefnIntfid = itemHierarchyDefnIntfid;

        if (_ivldItemHierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemHierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setItemHierarchyDefnIntfid",
                        String.class);

                method.invoke(_ivldItemHierarchyDefinitionRemoteModel,
                    itemHierarchyDefnIntfid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBpiLvl() {
        return _bpiLvl;
    }

    @Override
    public void setBpiLvl(String bpiLvl) {
        _bpiLvl = bpiLvl;

        if (_ivldItemHierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemHierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setBpiLvl", String.class);

                method.invoke(_ivldItemHierarchyDefinitionRemoteModel, bpiLvl);
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

        if (_ivldItemHierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemHierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_ivldItemHierarchyDefinitionRemoteModel,
                    modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAlias() {
        return _alias;
    }

    @Override
    public void setAlias(String alias) {
        _alias = alias;

        if (_ivldItemHierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemHierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setAlias", String.class);

                method.invoke(_ivldItemHierarchyDefinitionRemoteModel, alias);
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

        if (_ivldItemHierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemHierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_ivldItemHierarchyDefinitionRemoteModel, createdBy);
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

        if (_ivldItemHierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemHierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_ivldItemHierarchyDefinitionRemoteModel,
                    createdDate);
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

        if (_ivldItemHierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemHierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_ivldItemHierarchyDefinitionRemoteModel, source);
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

        if (_ivldItemHierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemHierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_ivldItemHierarchyDefinitionRemoteModel, batchId);
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

        if (_ivldItemHierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemHierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_ivldItemHierarchyDefinitionRemoteModel,
                    addChgDelIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIvldItemHierarchyDefinitionSid() {
        return _ivldItemHierarchyDefinitionSid;
    }

    @Override
    public void setIvldItemHierarchyDefinitionSid(
        int ivldItemHierarchyDefinitionSid) {
        _ivldItemHierarchyDefinitionSid = ivldItemHierarchyDefinitionSid;

        if (_ivldItemHierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemHierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setIvldItemHierarchyDefinitionSid",
                        int.class);

                method.invoke(_ivldItemHierarchyDefinitionRemoteModel,
                    ivldItemHierarchyDefinitionSid);
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

        if (_ivldItemHierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemHierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorField", String.class);

                method.invoke(_ivldItemHierarchyDefinitionRemoteModel,
                    errorField);
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

        if (_ivldItemHierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemHierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorCode", String.class);

                method.invoke(_ivldItemHierarchyDefinitionRemoteModel, errorCode);
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

        if (_ivldItemHierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemHierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setIntfInsertedDate",
                        Date.class);

                method.invoke(_ivldItemHierarchyDefinitionRemoteModel,
                    intfInsertedDate);
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

        if (_ivldItemHierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemHierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_ivldItemHierarchyDefinitionRemoteModel,
                    modifiedBy);
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

        if (_ivldItemHierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemHierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setReprocessedFlag",
                        String.class);

                method.invoke(_ivldItemHierarchyDefinitionRemoteModel,
                    reprocessedFlag);
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

        if (_ivldItemHierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemHierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_ivldItemHierarchyDefinitionRemoteModel,
                    checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getIvldItemHierarchyDefinitionRemoteModel() {
        return _ivldItemHierarchyDefinitionRemoteModel;
    }

    public void setIvldItemHierarchyDefinitionRemoteModel(
        BaseModel<?> ivldItemHierarchyDefinitionRemoteModel) {
        _ivldItemHierarchyDefinitionRemoteModel = ivldItemHierarchyDefinitionRemoteModel;
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

        Class<?> remoteModelClass = _ivldItemHierarchyDefinitionRemoteModel.getClass();

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

        Object returnValue = method.invoke(_ivldItemHierarchyDefinitionRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IvldItemHierarchyDefinitionLocalServiceUtil.addIvldItemHierarchyDefinition(this);
        } else {
            IvldItemHierarchyDefinitionLocalServiceUtil.updateIvldItemHierarchyDefinition(this);
        }
    }

    @Override
    public IvldItemHierarchyDefinition toEscapedModel() {
        return (IvldItemHierarchyDefinition) ProxyUtil.newProxyInstance(IvldItemHierarchyDefinition.class.getClassLoader(),
            new Class[] { IvldItemHierarchyDefinition.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        IvldItemHierarchyDefinitionClp clone = new IvldItemHierarchyDefinitionClp();

        clone.setMember(getMember());
        clone.setReasonForFailure(getReasonForFailure());
        clone.setItemHierarchyDefnIntfid(getItemHierarchyDefnIntfid());
        clone.setBpiLvl(getBpiLvl());
        clone.setModifiedDate(getModifiedDate());
        clone.setAlias(getAlias());
        clone.setCreatedBy(getCreatedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setSource(getSource());
        clone.setBatchId(getBatchId());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setIvldItemHierarchyDefinitionSid(getIvldItemHierarchyDefinitionSid());
        clone.setErrorField(getErrorField());
        clone.setErrorCode(getErrorCode());
        clone.setIntfInsertedDate(getIntfInsertedDate());
        clone.setModifiedBy(getModifiedBy());
        clone.setReprocessedFlag(getReprocessedFlag());
        clone.setCheckRecord(getCheckRecord());

        return clone;
    }

    @Override
    public int compareTo(
        IvldItemHierarchyDefinition ivldItemHierarchyDefinition) {
        int primaryKey = ivldItemHierarchyDefinition.getPrimaryKey();

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

        if (!(obj instanceof IvldItemHierarchyDefinitionClp)) {
            return false;
        }

        IvldItemHierarchyDefinitionClp ivldItemHierarchyDefinition = (IvldItemHierarchyDefinitionClp) obj;

        int primaryKey = ivldItemHierarchyDefinition.getPrimaryKey();

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
        StringBundler sb = new StringBundler(37);

        sb.append("{member=");
        sb.append(getMember());
        sb.append(", reasonForFailure=");
        sb.append(getReasonForFailure());
        sb.append(", itemHierarchyDefnIntfid=");
        sb.append(getItemHierarchyDefnIntfid());
        sb.append(", bpiLvl=");
        sb.append(getBpiLvl());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", alias=");
        sb.append(getAlias());
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
        sb.append(", ivldItemHierarchyDefinitionSid=");
        sb.append(getIvldItemHierarchyDefinitionSid());
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
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(58);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.IvldItemHierarchyDefinition");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>member</column-name><column-value><![CDATA[");
        sb.append(getMember());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reasonForFailure</column-name><column-value><![CDATA[");
        sb.append(getReasonForFailure());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemHierarchyDefnIntfid</column-name><column-value><![CDATA[");
        sb.append(getItemHierarchyDefnIntfid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>bpiLvl</column-name><column-value><![CDATA[");
        sb.append(getBpiLvl());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>alias</column-name><column-value><![CDATA[");
        sb.append(getAlias());
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
            "<column><column-name>ivldItemHierarchyDefinitionSid</column-name><column-value><![CDATA[");
        sb.append(getIvldItemHierarchyDefinitionSid());
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
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
