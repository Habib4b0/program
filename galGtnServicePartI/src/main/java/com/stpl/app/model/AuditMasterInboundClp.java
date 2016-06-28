package com.stpl.app.model;

import com.stpl.app.service.AuditMasterInboundLocalServiceUtil;
import com.stpl.app.service.ClpSerializer;

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


public class AuditMasterInboundClp extends BaseModelImpl<AuditMasterInbound>
    implements AuditMasterInbound {
    private String _receivedRecordAmountAttr;
    private int _modifiedBy;
    private Date _createdDate;
    private Date _interfaceRunEndDate;
    private String _applicationProcess;
    private double _discrepancyAmount;
    private String _batchId;
    private String _fileName;
    private String _sentRecordAmountAttribute;
    private String _status;
    private double _receivedRecordAmount;
    private double _validRecordAmount;
    private int _invalidRecordCount;
    private int _receivedRecordCount;
    private int _createdBy;
    private int _changeCount;
    private String _unprocessedRecords;
    private int _deleteCount;
    private Date _modifiedDate;
    private int _auditInboundSid;
    private double _sentRecordAmount;
    private int _sentRecordCount;
    private int _addCount;
    private String _source;
    private double _invalidRecordAmount;
    private Date _interfaceRunStartDate;
    private BaseModel<?> _auditMasterInboundRemoteModel;

    public AuditMasterInboundClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return AuditMasterInbound.class;
    }

    @Override
    public String getModelClassName() {
        return AuditMasterInbound.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _auditInboundSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setAuditInboundSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _auditInboundSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("receivedRecordAmountAttr", getReceivedRecordAmountAttr());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("interfaceRunEndDate", getInterfaceRunEndDate());
        attributes.put("applicationProcess", getApplicationProcess());
        attributes.put("discrepancyAmount", getDiscrepancyAmount());
        attributes.put("batchId", getBatchId());
        attributes.put("fileName", getFileName());
        attributes.put("sentRecordAmountAttribute",
            getSentRecordAmountAttribute());
        attributes.put("status", getStatus());
        attributes.put("receivedRecordAmount", getReceivedRecordAmount());
        attributes.put("validRecordAmount", getValidRecordAmount());
        attributes.put("invalidRecordCount", getInvalidRecordCount());
        attributes.put("receivedRecordCount", getReceivedRecordCount());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("changeCount", getChangeCount());
        attributes.put("unprocessedRecords", getUnprocessedRecords());
        attributes.put("deleteCount", getDeleteCount());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("auditInboundSid", getAuditInboundSid());
        attributes.put("sentRecordAmount", getSentRecordAmount());
        attributes.put("sentRecordCount", getSentRecordCount());
        attributes.put("addCount", getAddCount());
        attributes.put("source", getSource());
        attributes.put("invalidRecordAmount", getInvalidRecordAmount());
        attributes.put("interfaceRunStartDate", getInterfaceRunStartDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String receivedRecordAmountAttr = (String) attributes.get(
                "receivedRecordAmountAttr");

        if (receivedRecordAmountAttr != null) {
            setReceivedRecordAmountAttr(receivedRecordAmountAttr);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Date interfaceRunEndDate = (Date) attributes.get("interfaceRunEndDate");

        if (interfaceRunEndDate != null) {
            setInterfaceRunEndDate(interfaceRunEndDate);
        }

        String applicationProcess = (String) attributes.get(
                "applicationProcess");

        if (applicationProcess != null) {
            setApplicationProcess(applicationProcess);
        }

        Double discrepancyAmount = (Double) attributes.get("discrepancyAmount");

        if (discrepancyAmount != null) {
            setDiscrepancyAmount(discrepancyAmount);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String fileName = (String) attributes.get("fileName");

        if (fileName != null) {
            setFileName(fileName);
        }

        String sentRecordAmountAttribute = (String) attributes.get(
                "sentRecordAmountAttribute");

        if (sentRecordAmountAttribute != null) {
            setSentRecordAmountAttribute(sentRecordAmountAttribute);
        }

        String status = (String) attributes.get("status");

        if (status != null) {
            setStatus(status);
        }

        Double receivedRecordAmount = (Double) attributes.get(
                "receivedRecordAmount");

        if (receivedRecordAmount != null) {
            setReceivedRecordAmount(receivedRecordAmount);
        }

        Double validRecordAmount = (Double) attributes.get("validRecordAmount");

        if (validRecordAmount != null) {
            setValidRecordAmount(validRecordAmount);
        }

        Integer invalidRecordCount = (Integer) attributes.get(
                "invalidRecordCount");

        if (invalidRecordCount != null) {
            setInvalidRecordCount(invalidRecordCount);
        }

        Integer receivedRecordCount = (Integer) attributes.get(
                "receivedRecordCount");

        if (receivedRecordCount != null) {
            setReceivedRecordCount(receivedRecordCount);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer changeCount = (Integer) attributes.get("changeCount");

        if (changeCount != null) {
            setChangeCount(changeCount);
        }

        String unprocessedRecords = (String) attributes.get(
                "unprocessedRecords");

        if (unprocessedRecords != null) {
            setUnprocessedRecords(unprocessedRecords);
        }

        Integer deleteCount = (Integer) attributes.get("deleteCount");

        if (deleteCount != null) {
            setDeleteCount(deleteCount);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer auditInboundSid = (Integer) attributes.get("auditInboundSid");

        if (auditInboundSid != null) {
            setAuditInboundSid(auditInboundSid);
        }

        Double sentRecordAmount = (Double) attributes.get("sentRecordAmount");

        if (sentRecordAmount != null) {
            setSentRecordAmount(sentRecordAmount);
        }

        Integer sentRecordCount = (Integer) attributes.get("sentRecordCount");

        if (sentRecordCount != null) {
            setSentRecordCount(sentRecordCount);
        }

        Integer addCount = (Integer) attributes.get("addCount");

        if (addCount != null) {
            setAddCount(addCount);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Double invalidRecordAmount = (Double) attributes.get(
                "invalidRecordAmount");

        if (invalidRecordAmount != null) {
            setInvalidRecordAmount(invalidRecordAmount);
        }

        Date interfaceRunStartDate = (Date) attributes.get(
                "interfaceRunStartDate");

        if (interfaceRunStartDate != null) {
            setInterfaceRunStartDate(interfaceRunStartDate);
        }
    }

    @Override
    public String getReceivedRecordAmountAttr() {
        return _receivedRecordAmountAttr;
    }

    @Override
    public void setReceivedRecordAmountAttr(String receivedRecordAmountAttr) {
        _receivedRecordAmountAttr = receivedRecordAmountAttr;

        if (_auditMasterInboundRemoteModel != null) {
            try {
                Class<?> clazz = _auditMasterInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setReceivedRecordAmountAttr",
                        String.class);

                method.invoke(_auditMasterInboundRemoteModel,
                    receivedRecordAmountAttr);
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

        if (_auditMasterInboundRemoteModel != null) {
            try {
                Class<?> clazz = _auditMasterInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_auditMasterInboundRemoteModel, modifiedBy);
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

        if (_auditMasterInboundRemoteModel != null) {
            try {
                Class<?> clazz = _auditMasterInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_auditMasterInboundRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getInterfaceRunEndDate() {
        return _interfaceRunEndDate;
    }

    @Override
    public void setInterfaceRunEndDate(Date interfaceRunEndDate) {
        _interfaceRunEndDate = interfaceRunEndDate;

        if (_auditMasterInboundRemoteModel != null) {
            try {
                Class<?> clazz = _auditMasterInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setInterfaceRunEndDate",
                        Date.class);

                method.invoke(_auditMasterInboundRemoteModel,
                    interfaceRunEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getApplicationProcess() {
        return _applicationProcess;
    }

    @Override
    public void setApplicationProcess(String applicationProcess) {
        _applicationProcess = applicationProcess;

        if (_auditMasterInboundRemoteModel != null) {
            try {
                Class<?> clazz = _auditMasterInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setApplicationProcess",
                        String.class);

                method.invoke(_auditMasterInboundRemoteModel, applicationProcess);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getDiscrepancyAmount() {
        return _discrepancyAmount;
    }

    @Override
    public void setDiscrepancyAmount(double discrepancyAmount) {
        _discrepancyAmount = discrepancyAmount;

        if (_auditMasterInboundRemoteModel != null) {
            try {
                Class<?> clazz = _auditMasterInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setDiscrepancyAmount",
                        double.class);

                method.invoke(_auditMasterInboundRemoteModel, discrepancyAmount);
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

        if (_auditMasterInboundRemoteModel != null) {
            try {
                Class<?> clazz = _auditMasterInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_auditMasterInboundRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFileName() {
        return _fileName;
    }

    @Override
    public void setFileName(String fileName) {
        _fileName = fileName;

        if (_auditMasterInboundRemoteModel != null) {
            try {
                Class<?> clazz = _auditMasterInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setFileName", String.class);

                method.invoke(_auditMasterInboundRemoteModel, fileName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSentRecordAmountAttribute() {
        return _sentRecordAmountAttribute;
    }

    @Override
    public void setSentRecordAmountAttribute(String sentRecordAmountAttribute) {
        _sentRecordAmountAttribute = sentRecordAmountAttribute;

        if (_auditMasterInboundRemoteModel != null) {
            try {
                Class<?> clazz = _auditMasterInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setSentRecordAmountAttribute",
                        String.class);

                method.invoke(_auditMasterInboundRemoteModel,
                    sentRecordAmountAttribute);
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

        if (_auditMasterInboundRemoteModel != null) {
            try {
                Class<?> clazz = _auditMasterInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setStatus", String.class);

                method.invoke(_auditMasterInboundRemoteModel, status);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getReceivedRecordAmount() {
        return _receivedRecordAmount;
    }

    @Override
    public void setReceivedRecordAmount(double receivedRecordAmount) {
        _receivedRecordAmount = receivedRecordAmount;

        if (_auditMasterInboundRemoteModel != null) {
            try {
                Class<?> clazz = _auditMasterInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setReceivedRecordAmount",
                        double.class);

                method.invoke(_auditMasterInboundRemoteModel,
                    receivedRecordAmount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getValidRecordAmount() {
        return _validRecordAmount;
    }

    @Override
    public void setValidRecordAmount(double validRecordAmount) {
        _validRecordAmount = validRecordAmount;

        if (_auditMasterInboundRemoteModel != null) {
            try {
                Class<?> clazz = _auditMasterInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setValidRecordAmount",
                        double.class);

                method.invoke(_auditMasterInboundRemoteModel, validRecordAmount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getInvalidRecordCount() {
        return _invalidRecordCount;
    }

    @Override
    public void setInvalidRecordCount(int invalidRecordCount) {
        _invalidRecordCount = invalidRecordCount;

        if (_auditMasterInboundRemoteModel != null) {
            try {
                Class<?> clazz = _auditMasterInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setInvalidRecordCount",
                        int.class);

                method.invoke(_auditMasterInboundRemoteModel, invalidRecordCount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getReceivedRecordCount() {
        return _receivedRecordCount;
    }

    @Override
    public void setReceivedRecordCount(int receivedRecordCount) {
        _receivedRecordCount = receivedRecordCount;

        if (_auditMasterInboundRemoteModel != null) {
            try {
                Class<?> clazz = _auditMasterInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setReceivedRecordCount",
                        int.class);

                method.invoke(_auditMasterInboundRemoteModel,
                    receivedRecordCount);
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

        if (_auditMasterInboundRemoteModel != null) {
            try {
                Class<?> clazz = _auditMasterInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_auditMasterInboundRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getChangeCount() {
        return _changeCount;
    }

    @Override
    public void setChangeCount(int changeCount) {
        _changeCount = changeCount;

        if (_auditMasterInboundRemoteModel != null) {
            try {
                Class<?> clazz = _auditMasterInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setChangeCount", int.class);

                method.invoke(_auditMasterInboundRemoteModel, changeCount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUnprocessedRecords() {
        return _unprocessedRecords;
    }

    @Override
    public void setUnprocessedRecords(String unprocessedRecords) {
        _unprocessedRecords = unprocessedRecords;

        if (_auditMasterInboundRemoteModel != null) {
            try {
                Class<?> clazz = _auditMasterInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setUnprocessedRecords",
                        String.class);

                method.invoke(_auditMasterInboundRemoteModel, unprocessedRecords);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getDeleteCount() {
        return _deleteCount;
    }

    @Override
    public void setDeleteCount(int deleteCount) {
        _deleteCount = deleteCount;

        if (_auditMasterInboundRemoteModel != null) {
            try {
                Class<?> clazz = _auditMasterInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setDeleteCount", int.class);

                method.invoke(_auditMasterInboundRemoteModel, deleteCount);
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

        if (_auditMasterInboundRemoteModel != null) {
            try {
                Class<?> clazz = _auditMasterInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_auditMasterInboundRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getAuditInboundSid() {
        return _auditInboundSid;
    }

    @Override
    public void setAuditInboundSid(int auditInboundSid) {
        _auditInboundSid = auditInboundSid;

        if (_auditMasterInboundRemoteModel != null) {
            try {
                Class<?> clazz = _auditMasterInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setAuditInboundSid", int.class);

                method.invoke(_auditMasterInboundRemoteModel, auditInboundSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getSentRecordAmount() {
        return _sentRecordAmount;
    }

    @Override
    public void setSentRecordAmount(double sentRecordAmount) {
        _sentRecordAmount = sentRecordAmount;

        if (_auditMasterInboundRemoteModel != null) {
            try {
                Class<?> clazz = _auditMasterInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setSentRecordAmount",
                        double.class);

                method.invoke(_auditMasterInboundRemoteModel, sentRecordAmount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getSentRecordCount() {
        return _sentRecordCount;
    }

    @Override
    public void setSentRecordCount(int sentRecordCount) {
        _sentRecordCount = sentRecordCount;

        if (_auditMasterInboundRemoteModel != null) {
            try {
                Class<?> clazz = _auditMasterInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setSentRecordCount", int.class);

                method.invoke(_auditMasterInboundRemoteModel, sentRecordCount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getAddCount() {
        return _addCount;
    }

    @Override
    public void setAddCount(int addCount) {
        _addCount = addCount;

        if (_auditMasterInboundRemoteModel != null) {
            try {
                Class<?> clazz = _auditMasterInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setAddCount", int.class);

                method.invoke(_auditMasterInboundRemoteModel, addCount);
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

        if (_auditMasterInboundRemoteModel != null) {
            try {
                Class<?> clazz = _auditMasterInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_auditMasterInboundRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getInvalidRecordAmount() {
        return _invalidRecordAmount;
    }

    @Override
    public void setInvalidRecordAmount(double invalidRecordAmount) {
        _invalidRecordAmount = invalidRecordAmount;

        if (_auditMasterInboundRemoteModel != null) {
            try {
                Class<?> clazz = _auditMasterInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setInvalidRecordAmount",
                        double.class);

                method.invoke(_auditMasterInboundRemoteModel,
                    invalidRecordAmount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getInterfaceRunStartDate() {
        return _interfaceRunStartDate;
    }

    @Override
    public void setInterfaceRunStartDate(Date interfaceRunStartDate) {
        _interfaceRunStartDate = interfaceRunStartDate;

        if (_auditMasterInboundRemoteModel != null) {
            try {
                Class<?> clazz = _auditMasterInboundRemoteModel.getClass();

                Method method = clazz.getMethod("setInterfaceRunStartDate",
                        Date.class);

                method.invoke(_auditMasterInboundRemoteModel,
                    interfaceRunStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getAuditMasterInboundRemoteModel() {
        return _auditMasterInboundRemoteModel;
    }

    public void setAuditMasterInboundRemoteModel(
        BaseModel<?> auditMasterInboundRemoteModel) {
        _auditMasterInboundRemoteModel = auditMasterInboundRemoteModel;
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

        Class<?> remoteModelClass = _auditMasterInboundRemoteModel.getClass();

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

        Object returnValue = method.invoke(_auditMasterInboundRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            AuditMasterInboundLocalServiceUtil.addAuditMasterInbound(this);
        } else {
            AuditMasterInboundLocalServiceUtil.updateAuditMasterInbound(this);
        }
    }

    @Override
    public AuditMasterInbound toEscapedModel() {
        return (AuditMasterInbound) ProxyUtil.newProxyInstance(AuditMasterInbound.class.getClassLoader(),
            new Class[] { AuditMasterInbound.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        AuditMasterInboundClp clone = new AuditMasterInboundClp();

        clone.setReceivedRecordAmountAttr(getReceivedRecordAmountAttr());
        clone.setModifiedBy(getModifiedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setInterfaceRunEndDate(getInterfaceRunEndDate());
        clone.setApplicationProcess(getApplicationProcess());
        clone.setDiscrepancyAmount(getDiscrepancyAmount());
        clone.setBatchId(getBatchId());
        clone.setFileName(getFileName());
        clone.setSentRecordAmountAttribute(getSentRecordAmountAttribute());
        clone.setStatus(getStatus());
        clone.setReceivedRecordAmount(getReceivedRecordAmount());
        clone.setValidRecordAmount(getValidRecordAmount());
        clone.setInvalidRecordCount(getInvalidRecordCount());
        clone.setReceivedRecordCount(getReceivedRecordCount());
        clone.setCreatedBy(getCreatedBy());
        clone.setChangeCount(getChangeCount());
        clone.setUnprocessedRecords(getUnprocessedRecords());
        clone.setDeleteCount(getDeleteCount());
        clone.setModifiedDate(getModifiedDate());
        clone.setAuditInboundSid(getAuditInboundSid());
        clone.setSentRecordAmount(getSentRecordAmount());
        clone.setSentRecordCount(getSentRecordCount());
        clone.setAddCount(getAddCount());
        clone.setSource(getSource());
        clone.setInvalidRecordAmount(getInvalidRecordAmount());
        clone.setInterfaceRunStartDate(getInterfaceRunStartDate());

        return clone;
    }

    @Override
    public int compareTo(AuditMasterInbound auditMasterInbound) {
        int primaryKey = auditMasterInbound.getPrimaryKey();

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

        if (!(obj instanceof AuditMasterInboundClp)) {
            return false;
        }

        AuditMasterInboundClp auditMasterInbound = (AuditMasterInboundClp) obj;

        int primaryKey = auditMasterInbound.getPrimaryKey();

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
        StringBundler sb = new StringBundler(53);

        sb.append("{receivedRecordAmountAttr=");
        sb.append(getReceivedRecordAmountAttr());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", interfaceRunEndDate=");
        sb.append(getInterfaceRunEndDate());
        sb.append(", applicationProcess=");
        sb.append(getApplicationProcess());
        sb.append(", discrepancyAmount=");
        sb.append(getDiscrepancyAmount());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", fileName=");
        sb.append(getFileName());
        sb.append(", sentRecordAmountAttribute=");
        sb.append(getSentRecordAmountAttribute());
        sb.append(", status=");
        sb.append(getStatus());
        sb.append(", receivedRecordAmount=");
        sb.append(getReceivedRecordAmount());
        sb.append(", validRecordAmount=");
        sb.append(getValidRecordAmount());
        sb.append(", invalidRecordCount=");
        sb.append(getInvalidRecordCount());
        sb.append(", receivedRecordCount=");
        sb.append(getReceivedRecordCount());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", changeCount=");
        sb.append(getChangeCount());
        sb.append(", unprocessedRecords=");
        sb.append(getUnprocessedRecords());
        sb.append(", deleteCount=");
        sb.append(getDeleteCount());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", auditInboundSid=");
        sb.append(getAuditInboundSid());
        sb.append(", sentRecordAmount=");
        sb.append(getSentRecordAmount());
        sb.append(", sentRecordCount=");
        sb.append(getSentRecordCount());
        sb.append(", addCount=");
        sb.append(getAddCount());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", invalidRecordAmount=");
        sb.append(getInvalidRecordAmount());
        sb.append(", interfaceRunStartDate=");
        sb.append(getInterfaceRunStartDate());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(82);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.AuditMasterInbound");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>receivedRecordAmountAttr</column-name><column-value><![CDATA[");
        sb.append(getReceivedRecordAmountAttr());
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
            "<column><column-name>interfaceRunEndDate</column-name><column-value><![CDATA[");
        sb.append(getInterfaceRunEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>applicationProcess</column-name><column-value><![CDATA[");
        sb.append(getApplicationProcess());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>discrepancyAmount</column-name><column-value><![CDATA[");
        sb.append(getDiscrepancyAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fileName</column-name><column-value><![CDATA[");
        sb.append(getFileName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sentRecordAmountAttribute</column-name><column-value><![CDATA[");
        sb.append(getSentRecordAmountAttribute());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>status</column-name><column-value><![CDATA[");
        sb.append(getStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>receivedRecordAmount</column-name><column-value><![CDATA[");
        sb.append(getReceivedRecordAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>validRecordAmount</column-name><column-value><![CDATA[");
        sb.append(getValidRecordAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>invalidRecordCount</column-name><column-value><![CDATA[");
        sb.append(getInvalidRecordCount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>receivedRecordCount</column-name><column-value><![CDATA[");
        sb.append(getReceivedRecordCount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>changeCount</column-name><column-value><![CDATA[");
        sb.append(getChangeCount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>unprocessedRecords</column-name><column-value><![CDATA[");
        sb.append(getUnprocessedRecords());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deleteCount</column-name><column-value><![CDATA[");
        sb.append(getDeleteCount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>auditInboundSid</column-name><column-value><![CDATA[");
        sb.append(getAuditInboundSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sentRecordAmount</column-name><column-value><![CDATA[");
        sb.append(getSentRecordAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sentRecordCount</column-name><column-value><![CDATA[");
        sb.append(getSentRecordCount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>addCount</column-name><column-value><![CDATA[");
        sb.append(getAddCount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>invalidRecordAmount</column-name><column-value><![CDATA[");
        sb.append(getInvalidRecordAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>interfaceRunStartDate</column-name><column-value><![CDATA[");
        sb.append(getInterfaceRunStartDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
