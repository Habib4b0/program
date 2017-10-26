package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.NetSalesFormulaMasterLocalServiceUtil;

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


public class NetSalesFormulaMasterClp extends BaseModelImpl<NetSalesFormulaMaster>
    implements NetSalesFormulaMaster {
    private int _createdBy;
    private int _netSalesFormulaMasterSid;
    private int _modifiedBy;
    private Date _createdDate;
    private String _netSalesFormulaName;
    private int _netSalesFormulaType;
    private int _netSalesFormulaCategory;
    private String _contractSelection;
    private Date _modifiedDate;
    private boolean _recordLockStatus;
    private String _source;
    private String _netSalesFormulaId;
    private String _netSalesFormulaNo;
    private String _inboundStatus;
    private String _cdrModelSid;
    private BaseModel<?> _netSalesFormulaMasterRemoteModel;

    public NetSalesFormulaMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return NetSalesFormulaMaster.class;
    }

    @Override
    public String getModelClassName() {
        return NetSalesFormulaMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _netSalesFormulaMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setNetSalesFormulaMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _netSalesFormulaMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("netSalesFormulaMasterSid", getNetSalesFormulaMasterSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("netSalesFormulaName", getNetSalesFormulaName());
        attributes.put("netSalesFormulaType", getNetSalesFormulaType());
        attributes.put("netSalesFormulaCategory", getNetSalesFormulaCategory());
        attributes.put("contractSelection", getContractSelection());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("source", getSource());
        attributes.put("netSalesFormulaId", getNetSalesFormulaId());
        attributes.put("netSalesFormulaNo", getNetSalesFormulaNo());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("cdrModelSid", getCdrModelSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer netSalesFormulaMasterSid = (Integer) attributes.get(
                "netSalesFormulaMasterSid");

        if (netSalesFormulaMasterSid != null) {
            setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String netSalesFormulaName = (String) attributes.get(
                "netSalesFormulaName");

        if (netSalesFormulaName != null) {
            setNetSalesFormulaName(netSalesFormulaName);
        }

        Integer netSalesFormulaType = (Integer) attributes.get(
                "netSalesFormulaType");

        if (netSalesFormulaType != null) {
            setNetSalesFormulaType(netSalesFormulaType);
        }

        Integer netSalesFormulaCategory = (Integer) attributes.get(
                "netSalesFormulaCategory");

        if (netSalesFormulaCategory != null) {
            setNetSalesFormulaCategory(netSalesFormulaCategory);
        }

        String contractSelection = (String) attributes.get("contractSelection");

        if (contractSelection != null) {
            setContractSelection(contractSelection);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String netSalesFormulaId = (String) attributes.get("netSalesFormulaId");

        if (netSalesFormulaId != null) {
            setNetSalesFormulaId(netSalesFormulaId);
        }

        String netSalesFormulaNo = (String) attributes.get("netSalesFormulaNo");

        if (netSalesFormulaNo != null) {
            setNetSalesFormulaNo(netSalesFormulaNo);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        String cdrModelSid = (String) attributes.get("cdrModelSid");

        if (cdrModelSid != null) {
            setCdrModelSid(cdrModelSid);
        }
    }

    @Override
    public int getCreatedBy() {
        return _createdBy;
    }

    @Override
    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;

        if (_netSalesFormulaMasterRemoteModel != null) {
            try {
                Class<?> clazz = _netSalesFormulaMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_netSalesFormulaMasterRemoteModel, createdBy);
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

        if (_netSalesFormulaMasterRemoteModel != null) {
            try {
                Class<?> clazz = _netSalesFormulaMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesFormulaMasterSid",
                        int.class);

                method.invoke(_netSalesFormulaMasterRemoteModel,
                    netSalesFormulaMasterSid);
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

        if (_netSalesFormulaMasterRemoteModel != null) {
            try {
                Class<?> clazz = _netSalesFormulaMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_netSalesFormulaMasterRemoteModel, modifiedBy);
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

        if (_netSalesFormulaMasterRemoteModel != null) {
            try {
                Class<?> clazz = _netSalesFormulaMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_netSalesFormulaMasterRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNetSalesFormulaName() {
        return _netSalesFormulaName;
    }

    @Override
    public void setNetSalesFormulaName(String netSalesFormulaName) {
        _netSalesFormulaName = netSalesFormulaName;

        if (_netSalesFormulaMasterRemoteModel != null) {
            try {
                Class<?> clazz = _netSalesFormulaMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesFormulaName",
                        String.class);

                method.invoke(_netSalesFormulaMasterRemoteModel,
                    netSalesFormulaName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getNetSalesFormulaType() {
        return _netSalesFormulaType;
    }

    @Override
    public void setNetSalesFormulaType(int netSalesFormulaType) {
        _netSalesFormulaType = netSalesFormulaType;

        if (_netSalesFormulaMasterRemoteModel != null) {
            try {
                Class<?> clazz = _netSalesFormulaMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesFormulaType",
                        int.class);

                method.invoke(_netSalesFormulaMasterRemoteModel,
                    netSalesFormulaType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getNetSalesFormulaCategory() {
        return _netSalesFormulaCategory;
    }

    @Override
    public void setNetSalesFormulaCategory(int netSalesFormulaCategory) {
        _netSalesFormulaCategory = netSalesFormulaCategory;

        if (_netSalesFormulaMasterRemoteModel != null) {
            try {
                Class<?> clazz = _netSalesFormulaMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesFormulaCategory",
                        int.class);

                method.invoke(_netSalesFormulaMasterRemoteModel,
                    netSalesFormulaCategory);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getContractSelection() {
        return _contractSelection;
    }

    @Override
    public void setContractSelection(String contractSelection) {
        _contractSelection = contractSelection;

        if (_netSalesFormulaMasterRemoteModel != null) {
            try {
                Class<?> clazz = _netSalesFormulaMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractSelection",
                        String.class);

                method.invoke(_netSalesFormulaMasterRemoteModel,
                    contractSelection);
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

        if (_netSalesFormulaMasterRemoteModel != null) {
            try {
                Class<?> clazz = _netSalesFormulaMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_netSalesFormulaMasterRemoteModel, modifiedDate);
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

        if (_netSalesFormulaMasterRemoteModel != null) {
            try {
                Class<?> clazz = _netSalesFormulaMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_netSalesFormulaMasterRemoteModel,
                    recordLockStatus);
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

        if (_netSalesFormulaMasterRemoteModel != null) {
            try {
                Class<?> clazz = _netSalesFormulaMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_netSalesFormulaMasterRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNetSalesFormulaId() {
        return _netSalesFormulaId;
    }

    @Override
    public void setNetSalesFormulaId(String netSalesFormulaId) {
        _netSalesFormulaId = netSalesFormulaId;

        if (_netSalesFormulaMasterRemoteModel != null) {
            try {
                Class<?> clazz = _netSalesFormulaMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesFormulaId",
                        String.class);

                method.invoke(_netSalesFormulaMasterRemoteModel,
                    netSalesFormulaId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNetSalesFormulaNo() {
        return _netSalesFormulaNo;
    }

    @Override
    public void setNetSalesFormulaNo(String netSalesFormulaNo) {
        _netSalesFormulaNo = netSalesFormulaNo;

        if (_netSalesFormulaMasterRemoteModel != null) {
            try {
                Class<?> clazz = _netSalesFormulaMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesFormulaNo",
                        String.class);

                method.invoke(_netSalesFormulaMasterRemoteModel,
                    netSalesFormulaNo);
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

        if (_netSalesFormulaMasterRemoteModel != null) {
            try {
                Class<?> clazz = _netSalesFormulaMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_netSalesFormulaMasterRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCdrModelSid() {
        return _cdrModelSid;
    }

    @Override
    public void setCdrModelSid(String cdrModelSid) {
        _cdrModelSid = cdrModelSid;

        if (_netSalesFormulaMasterRemoteModel != null) {
            try {
                Class<?> clazz = _netSalesFormulaMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCdrModelSid", String.class);

                method.invoke(_netSalesFormulaMasterRemoteModel, cdrModelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getNetSalesFormulaMasterRemoteModel() {
        return _netSalesFormulaMasterRemoteModel;
    }

    public void setNetSalesFormulaMasterRemoteModel(
        BaseModel<?> netSalesFormulaMasterRemoteModel) {
        _netSalesFormulaMasterRemoteModel = netSalesFormulaMasterRemoteModel;
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

        Class<?> remoteModelClass = _netSalesFormulaMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_netSalesFormulaMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            NetSalesFormulaMasterLocalServiceUtil.addNetSalesFormulaMaster(this);
        } else {
            NetSalesFormulaMasterLocalServiceUtil.updateNetSalesFormulaMaster(this);
        }
    }

    @Override
    public NetSalesFormulaMaster toEscapedModel() {
        return (NetSalesFormulaMaster) ProxyUtil.newProxyInstance(NetSalesFormulaMaster.class.getClassLoader(),
            new Class[] { NetSalesFormulaMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        NetSalesFormulaMasterClp clone = new NetSalesFormulaMasterClp();

        clone.setCreatedBy(getCreatedBy());
        clone.setNetSalesFormulaMasterSid(getNetSalesFormulaMasterSid());
        clone.setModifiedBy(getModifiedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setNetSalesFormulaName(getNetSalesFormulaName());
        clone.setNetSalesFormulaType(getNetSalesFormulaType());
        clone.setNetSalesFormulaCategory(getNetSalesFormulaCategory());
        clone.setContractSelection(getContractSelection());
        clone.setModifiedDate(getModifiedDate());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setSource(getSource());
        clone.setNetSalesFormulaId(getNetSalesFormulaId());
        clone.setNetSalesFormulaNo(getNetSalesFormulaNo());
        clone.setInboundStatus(getInboundStatus());
        clone.setCdrModelSid(getCdrModelSid());

        return clone;
    }

    @Override
    public int compareTo(NetSalesFormulaMaster netSalesFormulaMaster) {
        int primaryKey = netSalesFormulaMaster.getPrimaryKey();

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

        if (!(obj instanceof NetSalesFormulaMasterClp)) {
            return false;
        }

        NetSalesFormulaMasterClp netSalesFormulaMaster = (NetSalesFormulaMasterClp) obj;

        int primaryKey = netSalesFormulaMaster.getPrimaryKey();

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
        StringBundler sb = new StringBundler(31);

        sb.append("{createdBy=");
        sb.append(getCreatedBy());
        sb.append(", netSalesFormulaMasterSid=");
        sb.append(getNetSalesFormulaMasterSid());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", netSalesFormulaName=");
        sb.append(getNetSalesFormulaName());
        sb.append(", netSalesFormulaType=");
        sb.append(getNetSalesFormulaType());
        sb.append(", netSalesFormulaCategory=");
        sb.append(getNetSalesFormulaCategory());
        sb.append(", contractSelection=");
        sb.append(getContractSelection());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", netSalesFormulaId=");
        sb.append(getNetSalesFormulaId());
        sb.append(", netSalesFormulaNo=");
        sb.append(getNetSalesFormulaNo());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", cdrModelSid=");
        sb.append(getCdrModelSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(49);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.NetSalesFormulaMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netSalesFormulaMasterSid</column-name><column-value><![CDATA[");
        sb.append(getNetSalesFormulaMasterSid());
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
            "<column><column-name>netSalesFormulaName</column-name><column-value><![CDATA[");
        sb.append(getNetSalesFormulaName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netSalesFormulaType</column-name><column-value><![CDATA[");
        sb.append(getNetSalesFormulaType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netSalesFormulaCategory</column-name><column-value><![CDATA[");
        sb.append(getNetSalesFormulaCategory());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractSelection</column-name><column-value><![CDATA[");
        sb.append(getContractSelection());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netSalesFormulaId</column-name><column-value><![CDATA[");
        sb.append(getNetSalesFormulaId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netSalesFormulaNo</column-name><column-value><![CDATA[");
        sb.append(getNetSalesFormulaNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cdrModelSid</column-name><column-value><![CDATA[");
        sb.append(getCdrModelSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
