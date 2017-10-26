package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.FormulaDetailsMasterLocalServiceUtil;

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


public class FormulaDetailsMasterClp extends BaseModelImpl<FormulaDetailsMaster>
    implements FormulaDetailsMaster {
    private String _companyId;
    private double _contractPrice1;
    private double _contractPrice2;
    private Date _endDate;
    private String _formulaNo;
    private int _formulaDetailsMasterSid;
    private String _itemId;
    private double _rebatePercent1;
    private Date _modifiedDate;
    private String _formulaDesc;
    private double _rebatePercent2;
    private double _rebatePercent3;
    private boolean _recordLockStatus;
    private Date _startDate;
    private Date _createdDate;
    private int _createdBy;
    private String _source;
    private String _batchId;
    private double _contractPrice3;
    private int _modifiedBy;
    private String _inboundStatus;
    private String _formulaId;
    private BaseModel<?> _formulaDetailsMasterRemoteModel;

    public FormulaDetailsMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return FormulaDetailsMaster.class;
    }

    @Override
    public String getModelClassName() {
        return FormulaDetailsMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _formulaDetailsMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setFormulaDetailsMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _formulaDetailsMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("companyId", getCompanyId());
        attributes.put("contractPrice1", getContractPrice1());
        attributes.put("contractPrice2", getContractPrice2());
        attributes.put("endDate", getEndDate());
        attributes.put("formulaNo", getFormulaNo());
        attributes.put("formulaDetailsMasterSid", getFormulaDetailsMasterSid());
        attributes.put("itemId", getItemId());
        attributes.put("rebatePercent1", getRebatePercent1());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("formulaDesc", getFormulaDesc());
        attributes.put("rebatePercent2", getRebatePercent2());
        attributes.put("rebatePercent3", getRebatePercent3());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("startDate", getStartDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("batchId", getBatchId());
        attributes.put("contractPrice3", getContractPrice3());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("formulaId", getFormulaId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        Double contractPrice1 = (Double) attributes.get("contractPrice1");

        if (contractPrice1 != null) {
            setContractPrice1(contractPrice1);
        }

        Double contractPrice2 = (Double) attributes.get("contractPrice2");

        if (contractPrice2 != null) {
            setContractPrice2(contractPrice2);
        }

        Date endDate = (Date) attributes.get("endDate");

        if (endDate != null) {
            setEndDate(endDate);
        }

        String formulaNo = (String) attributes.get("formulaNo");

        if (formulaNo != null) {
            setFormulaNo(formulaNo);
        }

        Integer formulaDetailsMasterSid = (Integer) attributes.get(
                "formulaDetailsMasterSid");

        if (formulaDetailsMasterSid != null) {
            setFormulaDetailsMasterSid(formulaDetailsMasterSid);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        Double rebatePercent1 = (Double) attributes.get("rebatePercent1");

        if (rebatePercent1 != null) {
            setRebatePercent1(rebatePercent1);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String formulaDesc = (String) attributes.get("formulaDesc");

        if (formulaDesc != null) {
            setFormulaDesc(formulaDesc);
        }

        Double rebatePercent2 = (Double) attributes.get("rebatePercent2");

        if (rebatePercent2 != null) {
            setRebatePercent2(rebatePercent2);
        }

        Double rebatePercent3 = (Double) attributes.get("rebatePercent3");

        if (rebatePercent3 != null) {
            setRebatePercent3(rebatePercent3);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        Date startDate = (Date) attributes.get("startDate");

        if (startDate != null) {
            setStartDate(startDate);
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

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Double contractPrice3 = (Double) attributes.get("contractPrice3");

        if (contractPrice3 != null) {
            setContractPrice3(contractPrice3);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        String formulaId = (String) attributes.get("formulaId");

        if (formulaId != null) {
            setFormulaId(formulaId);
        }
    }

    @Override
    public String getCompanyId() {
        return _companyId;
    }

    @Override
    public void setCompanyId(String companyId) {
        _companyId = companyId;

        if (_formulaDetailsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _formulaDetailsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyId", String.class);

                method.invoke(_formulaDetailsMasterRemoteModel, companyId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getContractPrice1() {
        return _contractPrice1;
    }

    @Override
    public void setContractPrice1(double contractPrice1) {
        _contractPrice1 = contractPrice1;

        if (_formulaDetailsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _formulaDetailsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractPrice1",
                        double.class);

                method.invoke(_formulaDetailsMasterRemoteModel, contractPrice1);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getContractPrice2() {
        return _contractPrice2;
    }

    @Override
    public void setContractPrice2(double contractPrice2) {
        _contractPrice2 = contractPrice2;

        if (_formulaDetailsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _formulaDetailsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractPrice2",
                        double.class);

                method.invoke(_formulaDetailsMasterRemoteModel, contractPrice2);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getEndDate() {
        return _endDate;
    }

    @Override
    public void setEndDate(Date endDate) {
        _endDate = endDate;

        if (_formulaDetailsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _formulaDetailsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setEndDate", Date.class);

                method.invoke(_formulaDetailsMasterRemoteModel, endDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFormulaNo() {
        return _formulaNo;
    }

    @Override
    public void setFormulaNo(String formulaNo) {
        _formulaNo = formulaNo;

        if (_formulaDetailsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _formulaDetailsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaNo", String.class);

                method.invoke(_formulaDetailsMasterRemoteModel, formulaNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getFormulaDetailsMasterSid() {
        return _formulaDetailsMasterSid;
    }

    @Override
    public void setFormulaDetailsMasterSid(int formulaDetailsMasterSid) {
        _formulaDetailsMasterSid = formulaDetailsMasterSid;

        if (_formulaDetailsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _formulaDetailsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaDetailsMasterSid",
                        int.class);

                method.invoke(_formulaDetailsMasterRemoteModel,
                    formulaDetailsMasterSid);
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

        if (_formulaDetailsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _formulaDetailsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_formulaDetailsMasterRemoteModel, itemId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getRebatePercent1() {
        return _rebatePercent1;
    }

    @Override
    public void setRebatePercent1(double rebatePercent1) {
        _rebatePercent1 = rebatePercent1;

        if (_formulaDetailsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _formulaDetailsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRebatePercent1",
                        double.class);

                method.invoke(_formulaDetailsMasterRemoteModel, rebatePercent1);
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

        if (_formulaDetailsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _formulaDetailsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_formulaDetailsMasterRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFormulaDesc() {
        return _formulaDesc;
    }

    @Override
    public void setFormulaDesc(String formulaDesc) {
        _formulaDesc = formulaDesc;

        if (_formulaDetailsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _formulaDetailsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaDesc", String.class);

                method.invoke(_formulaDetailsMasterRemoteModel, formulaDesc);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getRebatePercent2() {
        return _rebatePercent2;
    }

    @Override
    public void setRebatePercent2(double rebatePercent2) {
        _rebatePercent2 = rebatePercent2;

        if (_formulaDetailsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _formulaDetailsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRebatePercent2",
                        double.class);

                method.invoke(_formulaDetailsMasterRemoteModel, rebatePercent2);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getRebatePercent3() {
        return _rebatePercent3;
    }

    @Override
    public void setRebatePercent3(double rebatePercent3) {
        _rebatePercent3 = rebatePercent3;

        if (_formulaDetailsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _formulaDetailsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRebatePercent3",
                        double.class);

                method.invoke(_formulaDetailsMasterRemoteModel, rebatePercent3);
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

        if (_formulaDetailsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _formulaDetailsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_formulaDetailsMasterRemoteModel, recordLockStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getStartDate() {
        return _startDate;
    }

    @Override
    public void setStartDate(Date startDate) {
        _startDate = startDate;

        if (_formulaDetailsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _formulaDetailsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setStartDate", Date.class);

                method.invoke(_formulaDetailsMasterRemoteModel, startDate);
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

        if (_formulaDetailsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _formulaDetailsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_formulaDetailsMasterRemoteModel, createdDate);
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

        if (_formulaDetailsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _formulaDetailsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_formulaDetailsMasterRemoteModel, createdBy);
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

        if (_formulaDetailsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _formulaDetailsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_formulaDetailsMasterRemoteModel, source);
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

        if (_formulaDetailsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _formulaDetailsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_formulaDetailsMasterRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getContractPrice3() {
        return _contractPrice3;
    }

    @Override
    public void setContractPrice3(double contractPrice3) {
        _contractPrice3 = contractPrice3;

        if (_formulaDetailsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _formulaDetailsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractPrice3",
                        double.class);

                method.invoke(_formulaDetailsMasterRemoteModel, contractPrice3);
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

        if (_formulaDetailsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _formulaDetailsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_formulaDetailsMasterRemoteModel, modifiedBy);
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

        if (_formulaDetailsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _formulaDetailsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_formulaDetailsMasterRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFormulaId() {
        return _formulaId;
    }

    @Override
    public void setFormulaId(String formulaId) {
        _formulaId = formulaId;

        if (_formulaDetailsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _formulaDetailsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaId", String.class);

                method.invoke(_formulaDetailsMasterRemoteModel, formulaId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getFormulaDetailsMasterRemoteModel() {
        return _formulaDetailsMasterRemoteModel;
    }

    public void setFormulaDetailsMasterRemoteModel(
        BaseModel<?> formulaDetailsMasterRemoteModel) {
        _formulaDetailsMasterRemoteModel = formulaDetailsMasterRemoteModel;
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

        Class<?> remoteModelClass = _formulaDetailsMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_formulaDetailsMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            FormulaDetailsMasterLocalServiceUtil.addFormulaDetailsMaster(this);
        } else {
            FormulaDetailsMasterLocalServiceUtil.updateFormulaDetailsMaster(this);
        }
    }

    @Override
    public FormulaDetailsMaster toEscapedModel() {
        return (FormulaDetailsMaster) ProxyUtil.newProxyInstance(FormulaDetailsMaster.class.getClassLoader(),
            new Class[] { FormulaDetailsMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        FormulaDetailsMasterClp clone = new FormulaDetailsMasterClp();

        clone.setCompanyId(getCompanyId());
        clone.setContractPrice1(getContractPrice1());
        clone.setContractPrice2(getContractPrice2());
        clone.setEndDate(getEndDate());
        clone.setFormulaNo(getFormulaNo());
        clone.setFormulaDetailsMasterSid(getFormulaDetailsMasterSid());
        clone.setItemId(getItemId());
        clone.setRebatePercent1(getRebatePercent1());
        clone.setModifiedDate(getModifiedDate());
        clone.setFormulaDesc(getFormulaDesc());
        clone.setRebatePercent2(getRebatePercent2());
        clone.setRebatePercent3(getRebatePercent3());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setStartDate(getStartDate());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setSource(getSource());
        clone.setBatchId(getBatchId());
        clone.setContractPrice3(getContractPrice3());
        clone.setModifiedBy(getModifiedBy());
        clone.setInboundStatus(getInboundStatus());
        clone.setFormulaId(getFormulaId());

        return clone;
    }

    @Override
    public int compareTo(FormulaDetailsMaster formulaDetailsMaster) {
        int primaryKey = formulaDetailsMaster.getPrimaryKey();

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

        if (!(obj instanceof FormulaDetailsMasterClp)) {
            return false;
        }

        FormulaDetailsMasterClp formulaDetailsMaster = (FormulaDetailsMasterClp) obj;

        int primaryKey = formulaDetailsMaster.getPrimaryKey();

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

        sb.append("{companyId=");
        sb.append(getCompanyId());
        sb.append(", contractPrice1=");
        sb.append(getContractPrice1());
        sb.append(", contractPrice2=");
        sb.append(getContractPrice2());
        sb.append(", endDate=");
        sb.append(getEndDate());
        sb.append(", formulaNo=");
        sb.append(getFormulaNo());
        sb.append(", formulaDetailsMasterSid=");
        sb.append(getFormulaDetailsMasterSid());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", rebatePercent1=");
        sb.append(getRebatePercent1());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", formulaDesc=");
        sb.append(getFormulaDesc());
        sb.append(", rebatePercent2=");
        sb.append(getRebatePercent2());
        sb.append(", rebatePercent3=");
        sb.append(getRebatePercent3());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", startDate=");
        sb.append(getStartDate());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", contractPrice3=");
        sb.append(getContractPrice3());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", formulaId=");
        sb.append(getFormulaId());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(70);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.FormulaDetailsMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>companyId</column-name><column-value><![CDATA[");
        sb.append(getCompanyId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractPrice1</column-name><column-value><![CDATA[");
        sb.append(getContractPrice1());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractPrice2</column-name><column-value><![CDATA[");
        sb.append(getContractPrice2());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>endDate</column-name><column-value><![CDATA[");
        sb.append(getEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>formulaNo</column-name><column-value><![CDATA[");
        sb.append(getFormulaNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>formulaDetailsMasterSid</column-name><column-value><![CDATA[");
        sb.append(getFormulaDetailsMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemId</column-name><column-value><![CDATA[");
        sb.append(getItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebatePercent1</column-name><column-value><![CDATA[");
        sb.append(getRebatePercent1());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>formulaDesc</column-name><column-value><![CDATA[");
        sb.append(getFormulaDesc());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebatePercent2</column-name><column-value><![CDATA[");
        sb.append(getRebatePercent2());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebatePercent3</column-name><column-value><![CDATA[");
        sb.append(getRebatePercent3());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>startDate</column-name><column-value><![CDATA[");
        sb.append(getStartDate());
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
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractPrice3</column-name><column-value><![CDATA[");
        sb.append(getContractPrice3());
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
            "<column><column-name>formulaId</column-name><column-value><![CDATA[");
        sb.append(getFormulaId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
