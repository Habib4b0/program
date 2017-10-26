package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.ClpSerializer;
import com.stpl.app.parttwo.service.IvldItemIdentifierLocalServiceUtil;

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


public class IvldItemIdentifierClp extends BaseModelImpl<IvldItemIdentifier>
    implements IvldItemIdentifier {
    private String _createdBy;
    private String _identifierCodeQualifierName;
    private int _ivldItemIdentifierSid;
    private String _itemNo;
    private String _modifiedBy;
    private Date _createdDate;
    private String _identifierCodeQualifier;
    private String _itemId;
    private String _endDate;
    private String _errorField;
    private String _startDate;
    private String _batchId;
    private Date _modifiedDate;
    private String _itemName;
    private String _errorCode;
    private String _reprocessedFlag;
    private String _itemIdentifier;
    private String _itemStatus;
    private String _reasonForFailure;
    private String _source;
    private String _addChgDelIndicator;
    private String _entityCode;
    private String _itemIdentifierIntfid;
    private Date _intfInsertedDate;
    private boolean _checkRecord;
    private BaseModel<?> _ivldItemIdentifierRemoteModel;

    public IvldItemIdentifierClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return IvldItemIdentifier.class;
    }

    @Override
    public String getModelClassName() {
        return IvldItemIdentifier.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _ivldItemIdentifierSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIvldItemIdentifierSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ivldItemIdentifierSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("identifierCodeQualifierName",
            getIdentifierCodeQualifierName());
        attributes.put("ivldItemIdentifierSid", getIvldItemIdentifierSid());
        attributes.put("itemNo", getItemNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("identifierCodeQualifier", getIdentifierCodeQualifier());
        attributes.put("itemId", getItemId());
        attributes.put("endDate", getEndDate());
        attributes.put("errorField", getErrorField());
        attributes.put("startDate", getStartDate());
        attributes.put("batchId", getBatchId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("itemName", getItemName());
        attributes.put("errorCode", getErrorCode());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("itemIdentifier", getItemIdentifier());
        attributes.put("itemStatus", getItemStatus());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("source", getSource());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("entityCode", getEntityCode());
        attributes.put("itemIdentifierIntfid", getItemIdentifierIntfid());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String identifierCodeQualifierName = (String) attributes.get(
                "identifierCodeQualifierName");

        if (identifierCodeQualifierName != null) {
            setIdentifierCodeQualifierName(identifierCodeQualifierName);
        }

        Integer ivldItemIdentifierSid = (Integer) attributes.get(
                "ivldItemIdentifierSid");

        if (ivldItemIdentifierSid != null) {
            setIvldItemIdentifierSid(ivldItemIdentifierSid);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String identifierCodeQualifier = (String) attributes.get(
                "identifierCodeQualifier");

        if (identifierCodeQualifier != null) {
            setIdentifierCodeQualifier(identifierCodeQualifier);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String endDate = (String) attributes.get("endDate");

        if (endDate != null) {
            setEndDate(endDate);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        String startDate = (String) attributes.get("startDate");

        if (startDate != null) {
            setStartDate(startDate);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String itemIdentifier = (String) attributes.get("itemIdentifier");

        if (itemIdentifier != null) {
            setItemIdentifier(itemIdentifier);
        }

        String itemStatus = (String) attributes.get("itemStatus");

        if (itemStatus != null) {
            setItemStatus(itemStatus);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String entityCode = (String) attributes.get("entityCode");

        if (entityCode != null) {
            setEntityCode(entityCode);
        }

        String itemIdentifierIntfid = (String) attributes.get(
                "itemIdentifierIntfid");

        if (itemIdentifierIntfid != null) {
            setItemIdentifierIntfid(itemIdentifierIntfid);
        }

        Date intfInsertedDate = (Date) attributes.get("intfInsertedDate");

        if (intfInsertedDate != null) {
            setIntfInsertedDate(intfInsertedDate);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    @Override
    public String getCreatedBy() {
        return _createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        _createdBy = createdBy;

        if (_ivldItemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_ivldItemIdentifierRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIdentifierCodeQualifierName() {
        return _identifierCodeQualifierName;
    }

    @Override
    public void setIdentifierCodeQualifierName(
        String identifierCodeQualifierName) {
        _identifierCodeQualifierName = identifierCodeQualifierName;

        if (_ivldItemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setIdentifierCodeQualifierName",
                        String.class);

                method.invoke(_ivldItemIdentifierRemoteModel,
                    identifierCodeQualifierName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIvldItemIdentifierSid() {
        return _ivldItemIdentifierSid;
    }

    @Override
    public void setIvldItemIdentifierSid(int ivldItemIdentifierSid) {
        _ivldItemIdentifierSid = ivldItemIdentifierSid;

        if (_ivldItemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setIvldItemIdentifierSid",
                        int.class);

                method.invoke(_ivldItemIdentifierRemoteModel,
                    ivldItemIdentifierSid);
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

        if (_ivldItemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setItemNo", String.class);

                method.invoke(_ivldItemIdentifierRemoteModel, itemNo);
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

        if (_ivldItemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_ivldItemIdentifierRemoteModel, modifiedBy);
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

        if (_ivldItemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_ivldItemIdentifierRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIdentifierCodeQualifier() {
        return _identifierCodeQualifier;
    }

    @Override
    public void setIdentifierCodeQualifier(String identifierCodeQualifier) {
        _identifierCodeQualifier = identifierCodeQualifier;

        if (_ivldItemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setIdentifierCodeQualifier",
                        String.class);

                method.invoke(_ivldItemIdentifierRemoteModel,
                    identifierCodeQualifier);
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

        if (_ivldItemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_ivldItemIdentifierRemoteModel, itemId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getEndDate() {
        return _endDate;
    }

    @Override
    public void setEndDate(String endDate) {
        _endDate = endDate;

        if (_ivldItemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setEndDate", String.class);

                method.invoke(_ivldItemIdentifierRemoteModel, endDate);
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

        if (_ivldItemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorField", String.class);

                method.invoke(_ivldItemIdentifierRemoteModel, errorField);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getStartDate() {
        return _startDate;
    }

    @Override
    public void setStartDate(String startDate) {
        _startDate = startDate;

        if (_ivldItemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setStartDate", String.class);

                method.invoke(_ivldItemIdentifierRemoteModel, startDate);
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

        if (_ivldItemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_ivldItemIdentifierRemoteModel, batchId);
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

        if (_ivldItemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_ivldItemIdentifierRemoteModel, modifiedDate);
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

        if (_ivldItemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setItemName", String.class);

                method.invoke(_ivldItemIdentifierRemoteModel, itemName);
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

        if (_ivldItemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorCode", String.class);

                method.invoke(_ivldItemIdentifierRemoteModel, errorCode);
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

        if (_ivldItemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setReprocessedFlag",
                        String.class);

                method.invoke(_ivldItemIdentifierRemoteModel, reprocessedFlag);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemIdentifier() {
        return _itemIdentifier;
    }

    @Override
    public void setItemIdentifier(String itemIdentifier) {
        _itemIdentifier = itemIdentifier;

        if (_ivldItemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setItemIdentifier",
                        String.class);

                method.invoke(_ivldItemIdentifierRemoteModel, itemIdentifier);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemStatus() {
        return _itemStatus;
    }

    @Override
    public void setItemStatus(String itemStatus) {
        _itemStatus = itemStatus;

        if (_ivldItemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setItemStatus", String.class);

                method.invoke(_ivldItemIdentifierRemoteModel, itemStatus);
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

        if (_ivldItemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setReasonForFailure",
                        String.class);

                method.invoke(_ivldItemIdentifierRemoteModel, reasonForFailure);
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

        if (_ivldItemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_ivldItemIdentifierRemoteModel, source);
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

        if (_ivldItemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_ivldItemIdentifierRemoteModel, addChgDelIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getEntityCode() {
        return _entityCode;
    }

    @Override
    public void setEntityCode(String entityCode) {
        _entityCode = entityCode;

        if (_ivldItemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setEntityCode", String.class);

                method.invoke(_ivldItemIdentifierRemoteModel, entityCode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemIdentifierIntfid() {
        return _itemIdentifierIntfid;
    }

    @Override
    public void setItemIdentifierIntfid(String itemIdentifierIntfid) {
        _itemIdentifierIntfid = itemIdentifierIntfid;

        if (_ivldItemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setItemIdentifierIntfid",
                        String.class);

                method.invoke(_ivldItemIdentifierRemoteModel,
                    itemIdentifierIntfid);
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

        if (_ivldItemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setIntfInsertedDate",
                        Date.class);

                method.invoke(_ivldItemIdentifierRemoteModel, intfInsertedDate);
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

        if (_ivldItemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_ivldItemIdentifierRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getIvldItemIdentifierRemoteModel() {
        return _ivldItemIdentifierRemoteModel;
    }

    public void setIvldItemIdentifierRemoteModel(
        BaseModel<?> ivldItemIdentifierRemoteModel) {
        _ivldItemIdentifierRemoteModel = ivldItemIdentifierRemoteModel;
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

        Class<?> remoteModelClass = _ivldItemIdentifierRemoteModel.getClass();

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

        Object returnValue = method.invoke(_ivldItemIdentifierRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IvldItemIdentifierLocalServiceUtil.addIvldItemIdentifier(this);
        } else {
            IvldItemIdentifierLocalServiceUtil.updateIvldItemIdentifier(this);
        }
    }

    @Override
    public IvldItemIdentifier toEscapedModel() {
        return (IvldItemIdentifier) ProxyUtil.newProxyInstance(IvldItemIdentifier.class.getClassLoader(),
            new Class[] { IvldItemIdentifier.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        IvldItemIdentifierClp clone = new IvldItemIdentifierClp();

        clone.setCreatedBy(getCreatedBy());
        clone.setIdentifierCodeQualifierName(getIdentifierCodeQualifierName());
        clone.setIvldItemIdentifierSid(getIvldItemIdentifierSid());
        clone.setItemNo(getItemNo());
        clone.setModifiedBy(getModifiedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setIdentifierCodeQualifier(getIdentifierCodeQualifier());
        clone.setItemId(getItemId());
        clone.setEndDate(getEndDate());
        clone.setErrorField(getErrorField());
        clone.setStartDate(getStartDate());
        clone.setBatchId(getBatchId());
        clone.setModifiedDate(getModifiedDate());
        clone.setItemName(getItemName());
        clone.setErrorCode(getErrorCode());
        clone.setReprocessedFlag(getReprocessedFlag());
        clone.setItemIdentifier(getItemIdentifier());
        clone.setItemStatus(getItemStatus());
        clone.setReasonForFailure(getReasonForFailure());
        clone.setSource(getSource());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setEntityCode(getEntityCode());
        clone.setItemIdentifierIntfid(getItemIdentifierIntfid());
        clone.setIntfInsertedDate(getIntfInsertedDate());
        clone.setCheckRecord(getCheckRecord());

        return clone;
    }

    @Override
    public int compareTo(IvldItemIdentifier ivldItemIdentifier) {
        int primaryKey = ivldItemIdentifier.getPrimaryKey();

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

        if (!(obj instanceof IvldItemIdentifierClp)) {
            return false;
        }

        IvldItemIdentifierClp ivldItemIdentifier = (IvldItemIdentifierClp) obj;

        int primaryKey = ivldItemIdentifier.getPrimaryKey();

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
        StringBundler sb = new StringBundler(51);

        sb.append("{createdBy=");
        sb.append(getCreatedBy());
        sb.append(", identifierCodeQualifierName=");
        sb.append(getIdentifierCodeQualifierName());
        sb.append(", ivldItemIdentifierSid=");
        sb.append(getIvldItemIdentifierSid());
        sb.append(", itemNo=");
        sb.append(getItemNo());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", identifierCodeQualifier=");
        sb.append(getIdentifierCodeQualifier());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", endDate=");
        sb.append(getEndDate());
        sb.append(", errorField=");
        sb.append(getErrorField());
        sb.append(", startDate=");
        sb.append(getStartDate());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", itemName=");
        sb.append(getItemName());
        sb.append(", errorCode=");
        sb.append(getErrorCode());
        sb.append(", reprocessedFlag=");
        sb.append(getReprocessedFlag());
        sb.append(", itemIdentifier=");
        sb.append(getItemIdentifier());
        sb.append(", itemStatus=");
        sb.append(getItemStatus());
        sb.append(", reasonForFailure=");
        sb.append(getReasonForFailure());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", entityCode=");
        sb.append(getEntityCode());
        sb.append(", itemIdentifierIntfid=");
        sb.append(getItemIdentifierIntfid());
        sb.append(", intfInsertedDate=");
        sb.append(getIntfInsertedDate());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(79);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.IvldItemIdentifier");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>identifierCodeQualifierName</column-name><column-value><![CDATA[");
        sb.append(getIdentifierCodeQualifierName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ivldItemIdentifierSid</column-name><column-value><![CDATA[");
        sb.append(getIvldItemIdentifierSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemNo</column-name><column-value><![CDATA[");
        sb.append(getItemNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>identifierCodeQualifier</column-name><column-value><![CDATA[");
        sb.append(getIdentifierCodeQualifier());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemId</column-name><column-value><![CDATA[");
        sb.append(getItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>endDate</column-name><column-value><![CDATA[");
        sb.append(getEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>errorField</column-name><column-value><![CDATA[");
        sb.append(getErrorField());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>startDate</column-name><column-value><![CDATA[");
        sb.append(getStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemName</column-name><column-value><![CDATA[");
        sb.append(getItemName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>errorCode</column-name><column-value><![CDATA[");
        sb.append(getErrorCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reprocessedFlag</column-name><column-value><![CDATA[");
        sb.append(getReprocessedFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemIdentifier</column-name><column-value><![CDATA[");
        sb.append(getItemIdentifier());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemStatus</column-name><column-value><![CDATA[");
        sb.append(getItemStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reasonForFailure</column-name><column-value><![CDATA[");
        sb.append(getReasonForFailure());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>addChgDelIndicator</column-name><column-value><![CDATA[");
        sb.append(getAddChgDelIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>entityCode</column-name><column-value><![CDATA[");
        sb.append(getEntityCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemIdentifierIntfid</column-name><column-value><![CDATA[");
        sb.append(getItemIdentifierIntfid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>intfInsertedDate</column-name><column-value><![CDATA[");
        sb.append(getIntfInsertedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
