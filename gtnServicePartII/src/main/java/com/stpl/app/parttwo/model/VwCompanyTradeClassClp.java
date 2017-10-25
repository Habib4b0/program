package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.ClpSerializer;
import com.stpl.app.parttwo.service.VwCompanyTradeClassLocalServiceUtil;

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


public class VwCompanyTradeClassClp extends BaseModelImpl<VwCompanyTradeClass>
    implements VwCompanyTradeClass {
    private String _priorTradeClass;
    private int _companyTradeClassSid;
    private String _companyId;
    private Date _lastUpdatedDate;
    private Date _priorTradeClassStartDate;
    private Date _modifiedDate;
    private Date _tradeClassEndDate;
    private Date _tradeClassStartDate;
    private String _source;
    private String _createdBy;
    private Date _createdDate;
    private String _companyTradeClass;
    private String _batchId;
    private String _addChgDelIndicator;
    private String _modifiedBy;
    private BaseModel<?> _vwCompanyTradeClassRemoteModel;

    public VwCompanyTradeClassClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return VwCompanyTradeClass.class;
    }

    @Override
    public String getModelClassName() {
        return VwCompanyTradeClass.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _companyTradeClassSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setCompanyTradeClassSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _companyTradeClassSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("priorTradeClass", getPriorTradeClass());
        attributes.put("companyTradeClassSid", getCompanyTradeClassSid());
        attributes.put("companyId", getCompanyId());
        attributes.put("lastUpdatedDate", getLastUpdatedDate());
        attributes.put("priorTradeClassStartDate", getPriorTradeClassStartDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("tradeClassEndDate", getTradeClassEndDate());
        attributes.put("tradeClassStartDate", getTradeClassStartDate());
        attributes.put("source", getSource());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("companyTradeClass", getCompanyTradeClass());
        attributes.put("batchId", getBatchId());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("modifiedBy", getModifiedBy());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String priorTradeClass = (String) attributes.get("priorTradeClass");

        if (priorTradeClass != null) {
            setPriorTradeClass(priorTradeClass);
        }

        Integer companyTradeClassSid = (Integer) attributes.get(
                "companyTradeClassSid");

        if (companyTradeClassSid != null) {
            setCompanyTradeClassSid(companyTradeClassSid);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        Date lastUpdatedDate = (Date) attributes.get("lastUpdatedDate");

        if (lastUpdatedDate != null) {
            setLastUpdatedDate(lastUpdatedDate);
        }

        Date priorTradeClassStartDate = (Date) attributes.get(
                "priorTradeClassStartDate");

        if (priorTradeClassStartDate != null) {
            setPriorTradeClassStartDate(priorTradeClassStartDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Date tradeClassEndDate = (Date) attributes.get("tradeClassEndDate");

        if (tradeClassEndDate != null) {
            setTradeClassEndDate(tradeClassEndDate);
        }

        Date tradeClassStartDate = (Date) attributes.get("tradeClassStartDate");

        if (tradeClassStartDate != null) {
            setTradeClassStartDate(tradeClassStartDate);
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

        String companyTradeClass = (String) attributes.get("companyTradeClass");

        if (companyTradeClass != null) {
            setCompanyTradeClass(companyTradeClass);
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

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }
    }

    @Override
    public String getPriorTradeClass() {
        return _priorTradeClass;
    }

    @Override
    public void setPriorTradeClass(String priorTradeClass) {
        _priorTradeClass = priorTradeClass;

        if (_vwCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setPriorTradeClass",
                        String.class);

                method.invoke(_vwCompanyTradeClassRemoteModel, priorTradeClass);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCompanyTradeClassSid() {
        return _companyTradeClassSid;
    }

    @Override
    public void setCompanyTradeClassSid(int companyTradeClassSid) {
        _companyTradeClassSid = companyTradeClassSid;

        if (_vwCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyTradeClassSid",
                        int.class);

                method.invoke(_vwCompanyTradeClassRemoteModel,
                    companyTradeClassSid);
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

        if (_vwCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyId", String.class);

                method.invoke(_vwCompanyTradeClassRemoteModel, companyId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getLastUpdatedDate() {
        return _lastUpdatedDate;
    }

    @Override
    public void setLastUpdatedDate(Date lastUpdatedDate) {
        _lastUpdatedDate = lastUpdatedDate;

        if (_vwCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setLastUpdatedDate", Date.class);

                method.invoke(_vwCompanyTradeClassRemoteModel, lastUpdatedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPriorTradeClassStartDate() {
        return _priorTradeClassStartDate;
    }

    @Override
    public void setPriorTradeClassStartDate(Date priorTradeClassStartDate) {
        _priorTradeClassStartDate = priorTradeClassStartDate;

        if (_vwCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setPriorTradeClassStartDate",
                        Date.class);

                method.invoke(_vwCompanyTradeClassRemoteModel,
                    priorTradeClassStartDate);
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

        if (_vwCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_vwCompanyTradeClassRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getTradeClassEndDate() {
        return _tradeClassEndDate;
    }

    @Override
    public void setTradeClassEndDate(Date tradeClassEndDate) {
        _tradeClassEndDate = tradeClassEndDate;

        if (_vwCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setTradeClassEndDate",
                        Date.class);

                method.invoke(_vwCompanyTradeClassRemoteModel, tradeClassEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getTradeClassStartDate() {
        return _tradeClassStartDate;
    }

    @Override
    public void setTradeClassStartDate(Date tradeClassStartDate) {
        _tradeClassStartDate = tradeClassStartDate;

        if (_vwCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setTradeClassStartDate",
                        Date.class);

                method.invoke(_vwCompanyTradeClassRemoteModel,
                    tradeClassStartDate);
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

        if (_vwCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_vwCompanyTradeClassRemoteModel, source);
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

        if (_vwCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_vwCompanyTradeClassRemoteModel, createdBy);
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

        if (_vwCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_vwCompanyTradeClassRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyTradeClass() {
        return _companyTradeClass;
    }

    @Override
    public void setCompanyTradeClass(String companyTradeClass) {
        _companyTradeClass = companyTradeClass;

        if (_vwCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyTradeClass",
                        String.class);

                method.invoke(_vwCompanyTradeClassRemoteModel, companyTradeClass);
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

        if (_vwCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_vwCompanyTradeClassRemoteModel, batchId);
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

        if (_vwCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_vwCompanyTradeClassRemoteModel,
                    addChgDelIndicator);
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

        if (_vwCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_vwCompanyTradeClassRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getVwCompanyTradeClassRemoteModel() {
        return _vwCompanyTradeClassRemoteModel;
    }

    public void setVwCompanyTradeClassRemoteModel(
        BaseModel<?> vwCompanyTradeClassRemoteModel) {
        _vwCompanyTradeClassRemoteModel = vwCompanyTradeClassRemoteModel;
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

        Class<?> remoteModelClass = _vwCompanyTradeClassRemoteModel.getClass();

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

        Object returnValue = method.invoke(_vwCompanyTradeClassRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            VwCompanyTradeClassLocalServiceUtil.addVwCompanyTradeClass(this);
        } else {
            VwCompanyTradeClassLocalServiceUtil.updateVwCompanyTradeClass(this);
        }
    }

    @Override
    public VwCompanyTradeClass toEscapedModel() {
        return (VwCompanyTradeClass) ProxyUtil.newProxyInstance(VwCompanyTradeClass.class.getClassLoader(),
            new Class[] { VwCompanyTradeClass.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        VwCompanyTradeClassClp clone = new VwCompanyTradeClassClp();

        clone.setPriorTradeClass(getPriorTradeClass());
        clone.setCompanyTradeClassSid(getCompanyTradeClassSid());
        clone.setCompanyId(getCompanyId());
        clone.setLastUpdatedDate(getLastUpdatedDate());
        clone.setPriorTradeClassStartDate(getPriorTradeClassStartDate());
        clone.setModifiedDate(getModifiedDate());
        clone.setTradeClassEndDate(getTradeClassEndDate());
        clone.setTradeClassStartDate(getTradeClassStartDate());
        clone.setSource(getSource());
        clone.setCreatedBy(getCreatedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setCompanyTradeClass(getCompanyTradeClass());
        clone.setBatchId(getBatchId());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setModifiedBy(getModifiedBy());

        return clone;
    }

    @Override
    public int compareTo(VwCompanyTradeClass vwCompanyTradeClass) {
        int primaryKey = vwCompanyTradeClass.getPrimaryKey();

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

        if (!(obj instanceof VwCompanyTradeClassClp)) {
            return false;
        }

        VwCompanyTradeClassClp vwCompanyTradeClass = (VwCompanyTradeClassClp) obj;

        int primaryKey = vwCompanyTradeClass.getPrimaryKey();

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

        sb.append("{priorTradeClass=");
        sb.append(getPriorTradeClass());
        sb.append(", companyTradeClassSid=");
        sb.append(getCompanyTradeClassSid());
        sb.append(", companyId=");
        sb.append(getCompanyId());
        sb.append(", lastUpdatedDate=");
        sb.append(getLastUpdatedDate());
        sb.append(", priorTradeClassStartDate=");
        sb.append(getPriorTradeClassStartDate());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", tradeClassEndDate=");
        sb.append(getTradeClassEndDate());
        sb.append(", tradeClassStartDate=");
        sb.append(getTradeClassStartDate());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", companyTradeClass=");
        sb.append(getCompanyTradeClass());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(49);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.VwCompanyTradeClass");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>priorTradeClass</column-name><column-value><![CDATA[");
        sb.append(getPriorTradeClass());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyTradeClassSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyTradeClassSid());
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
            "<column><column-name>priorTradeClassStartDate</column-name><column-value><![CDATA[");
        sb.append(getPriorTradeClassStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>tradeClassEndDate</column-name><column-value><![CDATA[");
        sb.append(getTradeClassEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>tradeClassStartDate</column-name><column-value><![CDATA[");
        sb.append(getTradeClassStartDate());
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
            "<column><column-name>companyTradeClass</column-name><column-value><![CDATA[");
        sb.append(getCompanyTradeClass());
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
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
