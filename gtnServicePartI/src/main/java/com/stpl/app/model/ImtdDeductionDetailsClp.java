package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.ImtdDeductionDetailsLocalServiceUtil;

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


public class ImtdDeductionDetailsClp extends BaseModelImpl<ImtdDeductionDetails>
    implements ImtdDeductionDetails {
    private int _imtdDeductionDetailsSid;
    private String _deductionName;
    private int _modifiedBy;
    private Date _createdDate;
    private String _imtdCreatedDate;
    private int _deductionDetailsSid;
    private String _indicator;
    private String _contractNo;
    private boolean _checkRecord;
    private String _deductionSubType;
    private int _cdrModelSid;
    private int _createdBy;
    private String _deductionNo;
    private int _netSalesFormulaMasterSid;
    private int _usersSid;
    private int _contractMasterSid;
    private String _contractName;
    private String _deductionCategory;
    private Date _modifiedDate;
    private String _deductionType;
    private boolean _recordLockStatus;
    private String _operation;
    private String _sessionId;
    private int _rsContractSid;
    private String _inboundStatus;
    private BaseModel<?> _imtdDeductionDetailsRemoteModel;

    public ImtdDeductionDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ImtdDeductionDetails.class;
    }

    @Override
    public String getModelClassName() {
        return ImtdDeductionDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _imtdDeductionDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setImtdDeductionDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _imtdDeductionDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("imtdDeductionDetailsSid", getImtdDeductionDetailsSid());
        attributes.put("deductionName", getDeductionName());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("imtdCreatedDate", getImtdCreatedDate());
        attributes.put("deductionDetailsSid", getDeductionDetailsSid());
        attributes.put("indicator", getIndicator());
        attributes.put("contractNo", getContractNo());
        attributes.put("checkRecord", getCheckRecord());
        attributes.put("deductionSubType", getDeductionSubType());
        attributes.put("cdrModelSid", getCdrModelSid());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("deductionNo", getDeductionNo());
        attributes.put("netSalesFormulaMasterSid", getNetSalesFormulaMasterSid());
        attributes.put("usersSid", getUsersSid());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("contractName", getContractName());
        attributes.put("deductionCategory", getDeductionCategory());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("deductionType", getDeductionType());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("operation", getOperation());
        attributes.put("sessionId", getSessionId());
        attributes.put("rsContractSid", getRsContractSid());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer imtdDeductionDetailsSid = (Integer) attributes.get(
                "imtdDeductionDetailsSid");

        if (imtdDeductionDetailsSid != null) {
            setImtdDeductionDetailsSid(imtdDeductionDetailsSid);
        }

        String deductionName = (String) attributes.get("deductionName");

        if (deductionName != null) {
            setDeductionName(deductionName);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String imtdCreatedDate = (String) attributes.get("imtdCreatedDate");

        if (imtdCreatedDate != null) {
            setImtdCreatedDate(imtdCreatedDate);
        }

        Integer deductionDetailsSid = (Integer) attributes.get(
                "deductionDetailsSid");

        if (deductionDetailsSid != null) {
            setDeductionDetailsSid(deductionDetailsSid);
        }

        String indicator = (String) attributes.get("indicator");

        if (indicator != null) {
            setIndicator(indicator);
        }

        String contractNo = (String) attributes.get("contractNo");

        if (contractNo != null) {
            setContractNo(contractNo);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        String deductionSubType = (String) attributes.get("deductionSubType");

        if (deductionSubType != null) {
            setDeductionSubType(deductionSubType);
        }

        Integer cdrModelSid = (Integer) attributes.get("cdrModelSid");

        if (cdrModelSid != null) {
            setCdrModelSid(cdrModelSid);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String deductionNo = (String) attributes.get("deductionNo");

        if (deductionNo != null) {
            setDeductionNo(deductionNo);
        }

        Integer netSalesFormulaMasterSid = (Integer) attributes.get(
                "netSalesFormulaMasterSid");

        if (netSalesFormulaMasterSid != null) {
            setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
        }

        Integer usersSid = (Integer) attributes.get("usersSid");

        if (usersSid != null) {
            setUsersSid(usersSid);
        }

        Integer contractMasterSid = (Integer) attributes.get(
                "contractMasterSid");

        if (contractMasterSid != null) {
            setContractMasterSid(contractMasterSid);
        }

        String contractName = (String) attributes.get("contractName");

        if (contractName != null) {
            setContractName(contractName);
        }

        String deductionCategory = (String) attributes.get("deductionCategory");

        if (deductionCategory != null) {
            setDeductionCategory(deductionCategory);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String deductionType = (String) attributes.get("deductionType");

        if (deductionType != null) {
            setDeductionType(deductionType);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String operation = (String) attributes.get("operation");

        if (operation != null) {
            setOperation(operation);
        }

        String sessionId = (String) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        Integer rsContractSid = (Integer) attributes.get("rsContractSid");

        if (rsContractSid != null) {
            setRsContractSid(rsContractSid);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }
    }

    @Override
    public int getImtdDeductionDetailsSid() {
        return _imtdDeductionDetailsSid;
    }

    @Override
    public void setImtdDeductionDetailsSid(int imtdDeductionDetailsSid) {
        _imtdDeductionDetailsSid = imtdDeductionDetailsSid;

        if (_imtdDeductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdDeductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setImtdDeductionDetailsSid",
                        int.class);

                method.invoke(_imtdDeductionDetailsRemoteModel,
                    imtdDeductionDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionName() {
        return _deductionName;
    }

    @Override
    public void setDeductionName(String deductionName) {
        _deductionName = deductionName;

        if (_imtdDeductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdDeductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionName", String.class);

                method.invoke(_imtdDeductionDetailsRemoteModel, deductionName);
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

        if (_imtdDeductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdDeductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_imtdDeductionDetailsRemoteModel, modifiedBy);
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

        if (_imtdDeductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdDeductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_imtdDeductionDetailsRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getImtdCreatedDate() {
        return _imtdCreatedDate;
    }

    @Override
    public void setImtdCreatedDate(String imtdCreatedDate) {
        _imtdCreatedDate = imtdCreatedDate;

        if (_imtdDeductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdDeductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setImtdCreatedDate",
                        String.class);

                method.invoke(_imtdDeductionDetailsRemoteModel, imtdCreatedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getDeductionDetailsSid() {
        return _deductionDetailsSid;
    }

    @Override
    public void setDeductionDetailsSid(int deductionDetailsSid) {
        _deductionDetailsSid = deductionDetailsSid;

        if (_imtdDeductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdDeductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionDetailsSid",
                        int.class);

                method.invoke(_imtdDeductionDetailsRemoteModel,
                    deductionDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIndicator() {
        return _indicator;
    }

    @Override
    public void setIndicator(String indicator) {
        _indicator = indicator;

        if (_imtdDeductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdDeductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setIndicator", String.class);

                method.invoke(_imtdDeductionDetailsRemoteModel, indicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getContractNo() {
        return _contractNo;
    }

    @Override
    public void setContractNo(String contractNo) {
        _contractNo = contractNo;

        if (_imtdDeductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdDeductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractNo", String.class);

                method.invoke(_imtdDeductionDetailsRemoteModel, contractNo);
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

        if (_imtdDeductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdDeductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_imtdDeductionDetailsRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionSubType() {
        return _deductionSubType;
    }

    @Override
    public void setDeductionSubType(String deductionSubType) {
        _deductionSubType = deductionSubType;

        if (_imtdDeductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdDeductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionSubType",
                        String.class);

                method.invoke(_imtdDeductionDetailsRemoteModel, deductionSubType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCdrModelSid() {
        return _cdrModelSid;
    }

    @Override
    public void setCdrModelSid(int cdrModelSid) {
        _cdrModelSid = cdrModelSid;

        if (_imtdDeductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdDeductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCdrModelSid", int.class);

                method.invoke(_imtdDeductionDetailsRemoteModel, cdrModelSid);
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

        if (_imtdDeductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdDeductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_imtdDeductionDetailsRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionNo() {
        return _deductionNo;
    }

    @Override
    public void setDeductionNo(String deductionNo) {
        _deductionNo = deductionNo;

        if (_imtdDeductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdDeductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionNo", String.class);

                method.invoke(_imtdDeductionDetailsRemoteModel, deductionNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getNetSalesFormulaMasterSid() {
        return _netSalesFormulaMasterSid;
    }

    @Override
    public void setNetSalesFormulaMasterSid(int netSalesFormulaMasterSid) {
        _netSalesFormulaMasterSid = netSalesFormulaMasterSid;

        if (_imtdDeductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdDeductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesFormulaMasterSid",
                        int.class);

                method.invoke(_imtdDeductionDetailsRemoteModel,
                    netSalesFormulaMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getUsersSid() {
        return _usersSid;
    }

    @Override
    public void setUsersSid(int usersSid) {
        _usersSid = usersSid;

        if (_imtdDeductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdDeductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setUsersSid", int.class);

                method.invoke(_imtdDeductionDetailsRemoteModel, usersSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getContractMasterSid() {
        return _contractMasterSid;
    }

    @Override
    public void setContractMasterSid(int contractMasterSid) {
        _contractMasterSid = contractMasterSid;

        if (_imtdDeductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdDeductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractMasterSid",
                        int.class);

                method.invoke(_imtdDeductionDetailsRemoteModel,
                    contractMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getContractName() {
        return _contractName;
    }

    @Override
    public void setContractName(String contractName) {
        _contractName = contractName;

        if (_imtdDeductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdDeductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractName", String.class);

                method.invoke(_imtdDeductionDetailsRemoteModel, contractName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionCategory() {
        return _deductionCategory;
    }

    @Override
    public void setDeductionCategory(String deductionCategory) {
        _deductionCategory = deductionCategory;

        if (_imtdDeductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdDeductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionCategory",
                        String.class);

                method.invoke(_imtdDeductionDetailsRemoteModel,
                    deductionCategory);
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

        if (_imtdDeductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdDeductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_imtdDeductionDetailsRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionType() {
        return _deductionType;
    }

    @Override
    public void setDeductionType(String deductionType) {
        _deductionType = deductionType;

        if (_imtdDeductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdDeductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionType", String.class);

                method.invoke(_imtdDeductionDetailsRemoteModel, deductionType);
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

        if (_imtdDeductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdDeductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_imtdDeductionDetailsRemoteModel, recordLockStatus);
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

        if (_imtdDeductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdDeductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setOperation", String.class);

                method.invoke(_imtdDeductionDetailsRemoteModel, operation);
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

        if (_imtdDeductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdDeductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", String.class);

                method.invoke(_imtdDeductionDetailsRemoteModel, sessionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRsContractSid() {
        return _rsContractSid;
    }

    @Override
    public void setRsContractSid(int rsContractSid) {
        _rsContractSid = rsContractSid;

        if (_imtdDeductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdDeductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsContractSid", int.class);

                method.invoke(_imtdDeductionDetailsRemoteModel, rsContractSid);
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

        if (_imtdDeductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdDeductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_imtdDeductionDetailsRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getImtdDeductionDetailsRemoteModel() {
        return _imtdDeductionDetailsRemoteModel;
    }

    public void setImtdDeductionDetailsRemoteModel(
        BaseModel<?> imtdDeductionDetailsRemoteModel) {
        _imtdDeductionDetailsRemoteModel = imtdDeductionDetailsRemoteModel;
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

        Class<?> remoteModelClass = _imtdDeductionDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_imtdDeductionDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ImtdDeductionDetailsLocalServiceUtil.addImtdDeductionDetails(this);
        } else {
            ImtdDeductionDetailsLocalServiceUtil.updateImtdDeductionDetails(this);
        }
    }

    @Override
    public ImtdDeductionDetails toEscapedModel() {
        return (ImtdDeductionDetails) ProxyUtil.newProxyInstance(ImtdDeductionDetails.class.getClassLoader(),
            new Class[] { ImtdDeductionDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ImtdDeductionDetailsClp clone = new ImtdDeductionDetailsClp();

        clone.setImtdDeductionDetailsSid(getImtdDeductionDetailsSid());
        clone.setDeductionName(getDeductionName());
        clone.setModifiedBy(getModifiedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setImtdCreatedDate(getImtdCreatedDate());
        clone.setDeductionDetailsSid(getDeductionDetailsSid());
        clone.setIndicator(getIndicator());
        clone.setContractNo(getContractNo());
        clone.setCheckRecord(getCheckRecord());
        clone.setDeductionSubType(getDeductionSubType());
        clone.setCdrModelSid(getCdrModelSid());
        clone.setCreatedBy(getCreatedBy());
        clone.setDeductionNo(getDeductionNo());
        clone.setNetSalesFormulaMasterSid(getNetSalesFormulaMasterSid());
        clone.setUsersSid(getUsersSid());
        clone.setContractMasterSid(getContractMasterSid());
        clone.setContractName(getContractName());
        clone.setDeductionCategory(getDeductionCategory());
        clone.setModifiedDate(getModifiedDate());
        clone.setDeductionType(getDeductionType());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setOperation(getOperation());
        clone.setSessionId(getSessionId());
        clone.setRsContractSid(getRsContractSid());
        clone.setInboundStatus(getInboundStatus());

        return clone;
    }

    @Override
    public int compareTo(ImtdDeductionDetails imtdDeductionDetails) {
        int primaryKey = imtdDeductionDetails.getPrimaryKey();

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

        if (!(obj instanceof ImtdDeductionDetailsClp)) {
            return false;
        }

        ImtdDeductionDetailsClp imtdDeductionDetails = (ImtdDeductionDetailsClp) obj;

        int primaryKey = imtdDeductionDetails.getPrimaryKey();

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

        sb.append("{imtdDeductionDetailsSid=");
        sb.append(getImtdDeductionDetailsSid());
        sb.append(", deductionName=");
        sb.append(getDeductionName());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", imtdCreatedDate=");
        sb.append(getImtdCreatedDate());
        sb.append(", deductionDetailsSid=");
        sb.append(getDeductionDetailsSid());
        sb.append(", indicator=");
        sb.append(getIndicator());
        sb.append(", contractNo=");
        sb.append(getContractNo());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append(", deductionSubType=");
        sb.append(getDeductionSubType());
        sb.append(", cdrModelSid=");
        sb.append(getCdrModelSid());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", deductionNo=");
        sb.append(getDeductionNo());
        sb.append(", netSalesFormulaMasterSid=");
        sb.append(getNetSalesFormulaMasterSid());
        sb.append(", usersSid=");
        sb.append(getUsersSid());
        sb.append(", contractMasterSid=");
        sb.append(getContractMasterSid());
        sb.append(", contractName=");
        sb.append(getContractName());
        sb.append(", deductionCategory=");
        sb.append(getDeductionCategory());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", deductionType=");
        sb.append(getDeductionType());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", operation=");
        sb.append(getOperation());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append(", rsContractSid=");
        sb.append(getRsContractSid());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(79);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ImtdDeductionDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>imtdDeductionDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getImtdDeductionDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionName</column-name><column-value><![CDATA[");
        sb.append(getDeductionName());
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
            "<column><column-name>imtdCreatedDate</column-name><column-value><![CDATA[");
        sb.append(getImtdCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getDeductionDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>indicator</column-name><column-value><![CDATA[");
        sb.append(getIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractNo</column-name><column-value><![CDATA[");
        sb.append(getContractNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionSubType</column-name><column-value><![CDATA[");
        sb.append(getDeductionSubType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cdrModelSid</column-name><column-value><![CDATA[");
        sb.append(getCdrModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionNo</column-name><column-value><![CDATA[");
        sb.append(getDeductionNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netSalesFormulaMasterSid</column-name><column-value><![CDATA[");
        sb.append(getNetSalesFormulaMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>usersSid</column-name><column-value><![CDATA[");
        sb.append(getUsersSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractMasterSid</column-name><column-value><![CDATA[");
        sb.append(getContractMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractName</column-name><column-value><![CDATA[");
        sb.append(getContractName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionCategory</column-name><column-value><![CDATA[");
        sb.append(getDeductionCategory());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionType</column-name><column-value><![CDATA[");
        sb.append(getDeductionType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>operation</column-name><column-value><![CDATA[");
        sb.append(getOperation());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sessionId</column-name><column-value><![CDATA[");
        sb.append(getSessionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsContractSid</column-name><column-value><![CDATA[");
        sb.append(getRsContractSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
