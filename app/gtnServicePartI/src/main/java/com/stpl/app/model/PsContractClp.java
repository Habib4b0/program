package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.PsContractLocalServiceUtil;

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


public class PsContractClp extends BaseModelImpl<PsContract>
    implements PsContract {
    private String _psName;
    private String _psNo;
    private String _cfpContractSid;
    private int _psContractSid;
    private int _psType;
    private int _psContractAttachedStatus;
    private Date _modifiedDate;
    private int _psCategory;
    private boolean _recordLockStatus;
    private int _psStatus;
    private Date _createdDate;
    private int _createdBy;
    private String _source;
    private String _parentPsId;
    private String _psDesignation;
    private String _batchId;
    private int _contractMasterSid;
    private int _psModelSid;
    private Date _psContractAttachedDate;
    private Date _psEndDate;
    private int _modifiedBy;
    private String _inboundStatus;
    private String _parentPsName;
    private Date _psStartDate;
    private String _ifpContractSid;
    private int _psTradeClass;
    private BaseModel<?> _psContractRemoteModel;

    public PsContractClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return PsContract.class;
    }

    @Override
    public String getModelClassName() {
        return PsContract.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _psContractSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setPsContractSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _psContractSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("psName", getPsName());
        attributes.put("psNo", getPsNo());
        attributes.put("cfpContractSid", getCfpContractSid());
        attributes.put("psContractSid", getPsContractSid());
        attributes.put("psType", getPsType());
        attributes.put("psContractAttachedStatus", getPsContractAttachedStatus());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("psCategory", getPsCategory());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("psStatus", getPsStatus());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("parentPsId", getParentPsId());
        attributes.put("psDesignation", getPsDesignation());
        attributes.put("batchId", getBatchId());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("psModelSid", getPsModelSid());
        attributes.put("psContractAttachedDate", getPsContractAttachedDate());
        attributes.put("psEndDate", getPsEndDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("parentPsName", getParentPsName());
        attributes.put("psStartDate", getPsStartDate());
        attributes.put("ifpContractSid", getIfpContractSid());
        attributes.put("psTradeClass", getPsTradeClass());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String psName = (String) attributes.get("psName");

        if (psName != null) {
            setPsName(psName);
        }

        String psNo = (String) attributes.get("psNo");

        if (psNo != null) {
            setPsNo(psNo);
        }

        String cfpContractSid = (String) attributes.get("cfpContractSid");

        if (cfpContractSid != null) {
            setCfpContractSid(cfpContractSid);
        }

        Integer psContractSid = (Integer) attributes.get("psContractSid");

        if (psContractSid != null) {
            setPsContractSid(psContractSid);
        }

        Integer psType = (Integer) attributes.get("psType");

        if (psType != null) {
            setPsType(psType);
        }

        Integer psContractAttachedStatus = (Integer) attributes.get(
                "psContractAttachedStatus");

        if (psContractAttachedStatus != null) {
            setPsContractAttachedStatus(psContractAttachedStatus);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer psCategory = (Integer) attributes.get("psCategory");

        if (psCategory != null) {
            setPsCategory(psCategory);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        Integer psStatus = (Integer) attributes.get("psStatus");

        if (psStatus != null) {
            setPsStatus(psStatus);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String parentPsId = (String) attributes.get("parentPsId");

        if (parentPsId != null) {
            setParentPsId(parentPsId);
        }

        String psDesignation = (String) attributes.get("psDesignation");

        if (psDesignation != null) {
            setPsDesignation(psDesignation);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Integer contractMasterSid = (Integer) attributes.get(
                "contractMasterSid");

        if (contractMasterSid != null) {
            setContractMasterSid(contractMasterSid);
        }

        Integer psModelSid = (Integer) attributes.get("psModelSid");

        if (psModelSid != null) {
            setPsModelSid(psModelSid);
        }

        Date psContractAttachedDate = (Date) attributes.get(
                "psContractAttachedDate");

        if (psContractAttachedDate != null) {
            setPsContractAttachedDate(psContractAttachedDate);
        }

        Date psEndDate = (Date) attributes.get("psEndDate");

        if (psEndDate != null) {
            setPsEndDate(psEndDate);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        String parentPsName = (String) attributes.get("parentPsName");

        if (parentPsName != null) {
            setParentPsName(parentPsName);
        }

        Date psStartDate = (Date) attributes.get("psStartDate");

        if (psStartDate != null) {
            setPsStartDate(psStartDate);
        }

        String ifpContractSid = (String) attributes.get("ifpContractSid");

        if (ifpContractSid != null) {
            setIfpContractSid(ifpContractSid);
        }

        Integer psTradeClass = (Integer) attributes.get("psTradeClass");

        if (psTradeClass != null) {
            setPsTradeClass(psTradeClass);
        }
    }

    @Override
    public String getPsName() {
        return _psName;
    }

    @Override
    public void setPsName(String psName) {
        _psName = psName;

        if (_psContractRemoteModel != null) {
            try {
                Class<?> clazz = _psContractRemoteModel.getClass();

                Method method = clazz.getMethod("setPsName", String.class);

                method.invoke(_psContractRemoteModel, psName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPsNo() {
        return _psNo;
    }

    @Override
    public void setPsNo(String psNo) {
        _psNo = psNo;

        if (_psContractRemoteModel != null) {
            try {
                Class<?> clazz = _psContractRemoteModel.getClass();

                Method method = clazz.getMethod("setPsNo", String.class);

                method.invoke(_psContractRemoteModel, psNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCfpContractSid() {
        return _cfpContractSid;
    }

    @Override
    public void setCfpContractSid(String cfpContractSid) {
        _cfpContractSid = cfpContractSid;

        if (_psContractRemoteModel != null) {
            try {
                Class<?> clazz = _psContractRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpContractSid",
                        String.class);

                method.invoke(_psContractRemoteModel, cfpContractSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPsContractSid() {
        return _psContractSid;
    }

    @Override
    public void setPsContractSid(int psContractSid) {
        _psContractSid = psContractSid;

        if (_psContractRemoteModel != null) {
            try {
                Class<?> clazz = _psContractRemoteModel.getClass();

                Method method = clazz.getMethod("setPsContractSid", int.class);

                method.invoke(_psContractRemoteModel, psContractSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPsType() {
        return _psType;
    }

    @Override
    public void setPsType(int psType) {
        _psType = psType;

        if (_psContractRemoteModel != null) {
            try {
                Class<?> clazz = _psContractRemoteModel.getClass();

                Method method = clazz.getMethod("setPsType", int.class);

                method.invoke(_psContractRemoteModel, psType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPsContractAttachedStatus() {
        return _psContractAttachedStatus;
    }

    @Override
    public void setPsContractAttachedStatus(int psContractAttachedStatus) {
        _psContractAttachedStatus = psContractAttachedStatus;

        if (_psContractRemoteModel != null) {
            try {
                Class<?> clazz = _psContractRemoteModel.getClass();

                Method method = clazz.getMethod("setPsContractAttachedStatus",
                        int.class);

                method.invoke(_psContractRemoteModel, psContractAttachedStatus);
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

        if (_psContractRemoteModel != null) {
            try {
                Class<?> clazz = _psContractRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_psContractRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPsCategory() {
        return _psCategory;
    }

    @Override
    public void setPsCategory(int psCategory) {
        _psCategory = psCategory;

        if (_psContractRemoteModel != null) {
            try {
                Class<?> clazz = _psContractRemoteModel.getClass();

                Method method = clazz.getMethod("setPsCategory", int.class);

                method.invoke(_psContractRemoteModel, psCategory);
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

        if (_psContractRemoteModel != null) {
            try {
                Class<?> clazz = _psContractRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_psContractRemoteModel, recordLockStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPsStatus() {
        return _psStatus;
    }

    @Override
    public void setPsStatus(int psStatus) {
        _psStatus = psStatus;

        if (_psContractRemoteModel != null) {
            try {
                Class<?> clazz = _psContractRemoteModel.getClass();

                Method method = clazz.getMethod("setPsStatus", int.class);

                method.invoke(_psContractRemoteModel, psStatus);
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

        if (_psContractRemoteModel != null) {
            try {
                Class<?> clazz = _psContractRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_psContractRemoteModel, createdDate);
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

        if (_psContractRemoteModel != null) {
            try {
                Class<?> clazz = _psContractRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_psContractRemoteModel, createdBy);
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

        if (_psContractRemoteModel != null) {
            try {
                Class<?> clazz = _psContractRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_psContractRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getParentPsId() {
        return _parentPsId;
    }

    @Override
    public void setParentPsId(String parentPsId) {
        _parentPsId = parentPsId;

        if (_psContractRemoteModel != null) {
            try {
                Class<?> clazz = _psContractRemoteModel.getClass();

                Method method = clazz.getMethod("setParentPsId", String.class);

                method.invoke(_psContractRemoteModel, parentPsId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPsDesignation() {
        return _psDesignation;
    }

    @Override
    public void setPsDesignation(String psDesignation) {
        _psDesignation = psDesignation;

        if (_psContractRemoteModel != null) {
            try {
                Class<?> clazz = _psContractRemoteModel.getClass();

                Method method = clazz.getMethod("setPsDesignation", String.class);

                method.invoke(_psContractRemoteModel, psDesignation);
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

        if (_psContractRemoteModel != null) {
            try {
                Class<?> clazz = _psContractRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_psContractRemoteModel, batchId);
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

        if (_psContractRemoteModel != null) {
            try {
                Class<?> clazz = _psContractRemoteModel.getClass();

                Method method = clazz.getMethod("setContractMasterSid",
                        int.class);

                method.invoke(_psContractRemoteModel, contractMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPsModelSid() {
        return _psModelSid;
    }

    @Override
    public void setPsModelSid(int psModelSid) {
        _psModelSid = psModelSid;

        if (_psContractRemoteModel != null) {
            try {
                Class<?> clazz = _psContractRemoteModel.getClass();

                Method method = clazz.getMethod("setPsModelSid", int.class);

                method.invoke(_psContractRemoteModel, psModelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPsContractAttachedDate() {
        return _psContractAttachedDate;
    }

    @Override
    public void setPsContractAttachedDate(Date psContractAttachedDate) {
        _psContractAttachedDate = psContractAttachedDate;

        if (_psContractRemoteModel != null) {
            try {
                Class<?> clazz = _psContractRemoteModel.getClass();

                Method method = clazz.getMethod("setPsContractAttachedDate",
                        Date.class);

                method.invoke(_psContractRemoteModel, psContractAttachedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPsEndDate() {
        return _psEndDate;
    }

    @Override
    public void setPsEndDate(Date psEndDate) {
        _psEndDate = psEndDate;

        if (_psContractRemoteModel != null) {
            try {
                Class<?> clazz = _psContractRemoteModel.getClass();

                Method method = clazz.getMethod("setPsEndDate", Date.class);

                method.invoke(_psContractRemoteModel, psEndDate);
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

        if (_psContractRemoteModel != null) {
            try {
                Class<?> clazz = _psContractRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_psContractRemoteModel, modifiedBy);
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

        if (_psContractRemoteModel != null) {
            try {
                Class<?> clazz = _psContractRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_psContractRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getParentPsName() {
        return _parentPsName;
    }

    @Override
    public void setParentPsName(String parentPsName) {
        _parentPsName = parentPsName;

        if (_psContractRemoteModel != null) {
            try {
                Class<?> clazz = _psContractRemoteModel.getClass();

                Method method = clazz.getMethod("setParentPsName", String.class);

                method.invoke(_psContractRemoteModel, parentPsName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPsStartDate() {
        return _psStartDate;
    }

    @Override
    public void setPsStartDate(Date psStartDate) {
        _psStartDate = psStartDate;

        if (_psContractRemoteModel != null) {
            try {
                Class<?> clazz = _psContractRemoteModel.getClass();

                Method method = clazz.getMethod("setPsStartDate", Date.class);

                method.invoke(_psContractRemoteModel, psStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIfpContractSid() {
        return _ifpContractSid;
    }

    @Override
    public void setIfpContractSid(String ifpContractSid) {
        _ifpContractSid = ifpContractSid;

        if (_psContractRemoteModel != null) {
            try {
                Class<?> clazz = _psContractRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpContractSid",
                        String.class);

                method.invoke(_psContractRemoteModel, ifpContractSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPsTradeClass() {
        return _psTradeClass;
    }

    @Override
    public void setPsTradeClass(int psTradeClass) {
        _psTradeClass = psTradeClass;

        if (_psContractRemoteModel != null) {
            try {
                Class<?> clazz = _psContractRemoteModel.getClass();

                Method method = clazz.getMethod("setPsTradeClass", int.class);

                method.invoke(_psContractRemoteModel, psTradeClass);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getPsContractRemoteModel() {
        return _psContractRemoteModel;
    }

    public void setPsContractRemoteModel(BaseModel<?> psContractRemoteModel) {
        _psContractRemoteModel = psContractRemoteModel;
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

        Class<?> remoteModelClass = _psContractRemoteModel.getClass();

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

        Object returnValue = method.invoke(_psContractRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PsContractLocalServiceUtil.addPsContract(this);
        } else {
            PsContractLocalServiceUtil.updatePsContract(this);
        }
    }

    @Override
    public PsContract toEscapedModel() {
        return (PsContract) ProxyUtil.newProxyInstance(PsContract.class.getClassLoader(),
            new Class[] { PsContract.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PsContractClp clone = new PsContractClp();

        clone.setPsName(getPsName());
        clone.setPsNo(getPsNo());
        clone.setCfpContractSid(getCfpContractSid());
        clone.setPsContractSid(getPsContractSid());
        clone.setPsType(getPsType());
        clone.setPsContractAttachedStatus(getPsContractAttachedStatus());
        clone.setModifiedDate(getModifiedDate());
        clone.setPsCategory(getPsCategory());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setPsStatus(getPsStatus());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setSource(getSource());
        clone.setParentPsId(getParentPsId());
        clone.setPsDesignation(getPsDesignation());
        clone.setBatchId(getBatchId());
        clone.setContractMasterSid(getContractMasterSid());
        clone.setPsModelSid(getPsModelSid());
        clone.setPsContractAttachedDate(getPsContractAttachedDate());
        clone.setPsEndDate(getPsEndDate());
        clone.setModifiedBy(getModifiedBy());
        clone.setInboundStatus(getInboundStatus());
        clone.setParentPsName(getParentPsName());
        clone.setPsStartDate(getPsStartDate());
        clone.setIfpContractSid(getIfpContractSid());
        clone.setPsTradeClass(getPsTradeClass());

        return clone;
    }

    @Override
    public int compareTo(PsContract psContract) {
        int primaryKey = psContract.getPrimaryKey();

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

        if (!(obj instanceof PsContractClp)) {
            return false;
        }

        PsContractClp psContract = (PsContractClp) obj;

        int primaryKey = psContract.getPrimaryKey();

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

        sb.append("{psName=");
        sb.append(getPsName());
        sb.append(", psNo=");
        sb.append(getPsNo());
        sb.append(", cfpContractSid=");
        sb.append(getCfpContractSid());
        sb.append(", psContractSid=");
        sb.append(getPsContractSid());
        sb.append(", psType=");
        sb.append(getPsType());
        sb.append(", psContractAttachedStatus=");
        sb.append(getPsContractAttachedStatus());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", psCategory=");
        sb.append(getPsCategory());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", psStatus=");
        sb.append(getPsStatus());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", parentPsId=");
        sb.append(getParentPsId());
        sb.append(", psDesignation=");
        sb.append(getPsDesignation());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", contractMasterSid=");
        sb.append(getContractMasterSid());
        sb.append(", psModelSid=");
        sb.append(getPsModelSid());
        sb.append(", psContractAttachedDate=");
        sb.append(getPsContractAttachedDate());
        sb.append(", psEndDate=");
        sb.append(getPsEndDate());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", parentPsName=");
        sb.append(getParentPsName());
        sb.append(", psStartDate=");
        sb.append(getPsStartDate());
        sb.append(", ifpContractSid=");
        sb.append(getIfpContractSid());
        sb.append(", psTradeClass=");
        sb.append(getPsTradeClass());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(82);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.PsContract");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>psName</column-name><column-value><![CDATA[");
        sb.append(getPsName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psNo</column-name><column-value><![CDATA[");
        sb.append(getPsNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpContractSid</column-name><column-value><![CDATA[");
        sb.append(getCfpContractSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psContractSid</column-name><column-value><![CDATA[");
        sb.append(getPsContractSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psType</column-name><column-value><![CDATA[");
        sb.append(getPsType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psContractAttachedStatus</column-name><column-value><![CDATA[");
        sb.append(getPsContractAttachedStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psCategory</column-name><column-value><![CDATA[");
        sb.append(getPsCategory());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psStatus</column-name><column-value><![CDATA[");
        sb.append(getPsStatus());
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
            "<column><column-name>parentPsId</column-name><column-value><![CDATA[");
        sb.append(getParentPsId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psDesignation</column-name><column-value><![CDATA[");
        sb.append(getPsDesignation());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractMasterSid</column-name><column-value><![CDATA[");
        sb.append(getContractMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psModelSid</column-name><column-value><![CDATA[");
        sb.append(getPsModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psContractAttachedDate</column-name><column-value><![CDATA[");
        sb.append(getPsContractAttachedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psEndDate</column-name><column-value><![CDATA[");
        sb.append(getPsEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parentPsName</column-name><column-value><![CDATA[");
        sb.append(getParentPsName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psStartDate</column-name><column-value><![CDATA[");
        sb.append(getPsStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpContractSid</column-name><column-value><![CDATA[");
        sb.append(getIfpContractSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psTradeClass</column-name><column-value><![CDATA[");
        sb.append(getPsTradeClass());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
