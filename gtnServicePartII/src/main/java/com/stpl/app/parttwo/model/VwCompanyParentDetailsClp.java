package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.ClpSerializer;
import com.stpl.app.parttwo.service.VwCompanyParentDetailsLocalServiceUtil;

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


public class VwCompanyParentDetailsClp extends BaseModelImpl<VwCompanyParentDetails>
    implements VwCompanyParentDetails {
    private String _parentCompanyId;
    private String _priorParentCompanyId;
    private String _companyId;
    private Date _lastUpdatedDate;
    private Date _parentEndDate;
    private Date _modifiedDate;
    private Date _priorParentStartDate;
    private String _source;
    private String _createdBy;
    private Date _createdDate;
    private int _companyParentDetailsSid;
    private String _batchId;
    private String _addChgDelIndicator;
    private String _modifiedBy;
    private Date _parentStartDate;
    private BaseModel<?> _vwCompanyParentDetailsRemoteModel;

    public VwCompanyParentDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return VwCompanyParentDetails.class;
    }

    @Override
    public String getModelClassName() {
        return VwCompanyParentDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _companyParentDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setCompanyParentDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _companyParentDetailsSid;
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
        attributes.put("companyId", getCompanyId());
        attributes.put("lastUpdatedDate", getLastUpdatedDate());
        attributes.put("parentEndDate", getParentEndDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("priorParentStartDate", getPriorParentStartDate());
        attributes.put("source", getSource());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("companyParentDetailsSid", getCompanyParentDetailsSid());
        attributes.put("batchId", getBatchId());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("parentStartDate", getParentStartDate());

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

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        Date lastUpdatedDate = (Date) attributes.get("lastUpdatedDate");

        if (lastUpdatedDate != null) {
            setLastUpdatedDate(lastUpdatedDate);
        }

        Date parentEndDate = (Date) attributes.get("parentEndDate");

        if (parentEndDate != null) {
            setParentEndDate(parentEndDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Date priorParentStartDate = (Date) attributes.get(
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

        Integer companyParentDetailsSid = (Integer) attributes.get(
                "companyParentDetailsSid");

        if (companyParentDetailsSid != null) {
            setCompanyParentDetailsSid(companyParentDetailsSid);
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

        Date parentStartDate = (Date) attributes.get("parentStartDate");

        if (parentStartDate != null) {
            setParentStartDate(parentStartDate);
        }
    }

    @Override
    public String getParentCompanyId() {
        return _parentCompanyId;
    }

    @Override
    public void setParentCompanyId(String parentCompanyId) {
        _parentCompanyId = parentCompanyId;

        if (_vwCompanyParentDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyParentDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setParentCompanyId",
                        String.class);

                method.invoke(_vwCompanyParentDetailsRemoteModel,
                    parentCompanyId);
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

        if (_vwCompanyParentDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyParentDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriorParentCompanyId",
                        String.class);

                method.invoke(_vwCompanyParentDetailsRemoteModel,
                    priorParentCompanyId);
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

        if (_vwCompanyParentDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyParentDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyId", String.class);

                method.invoke(_vwCompanyParentDetailsRemoteModel, companyId);
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

        if (_vwCompanyParentDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyParentDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setLastUpdatedDate", Date.class);

                method.invoke(_vwCompanyParentDetailsRemoteModel,
                    lastUpdatedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getParentEndDate() {
        return _parentEndDate;
    }

    @Override
    public void setParentEndDate(Date parentEndDate) {
        _parentEndDate = parentEndDate;

        if (_vwCompanyParentDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyParentDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setParentEndDate", Date.class);

                method.invoke(_vwCompanyParentDetailsRemoteModel, parentEndDate);
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

        if (_vwCompanyParentDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyParentDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_vwCompanyParentDetailsRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPriorParentStartDate() {
        return _priorParentStartDate;
    }

    @Override
    public void setPriorParentStartDate(Date priorParentStartDate) {
        _priorParentStartDate = priorParentStartDate;

        if (_vwCompanyParentDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyParentDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriorParentStartDate",
                        Date.class);

                method.invoke(_vwCompanyParentDetailsRemoteModel,
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

        if (_vwCompanyParentDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyParentDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_vwCompanyParentDetailsRemoteModel, source);
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

        if (_vwCompanyParentDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyParentDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_vwCompanyParentDetailsRemoteModel, createdBy);
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

        if (_vwCompanyParentDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyParentDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_vwCompanyParentDetailsRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCompanyParentDetailsSid() {
        return _companyParentDetailsSid;
    }

    @Override
    public void setCompanyParentDetailsSid(int companyParentDetailsSid) {
        _companyParentDetailsSid = companyParentDetailsSid;

        if (_vwCompanyParentDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyParentDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyParentDetailsSid",
                        int.class);

                method.invoke(_vwCompanyParentDetailsRemoteModel,
                    companyParentDetailsSid);
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

        if (_vwCompanyParentDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyParentDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_vwCompanyParentDetailsRemoteModel, batchId);
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

        if (_vwCompanyParentDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyParentDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_vwCompanyParentDetailsRemoteModel,
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

        if (_vwCompanyParentDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyParentDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_vwCompanyParentDetailsRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getParentStartDate() {
        return _parentStartDate;
    }

    @Override
    public void setParentStartDate(Date parentStartDate) {
        _parentStartDate = parentStartDate;

        if (_vwCompanyParentDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyParentDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setParentStartDate", Date.class);

                method.invoke(_vwCompanyParentDetailsRemoteModel,
                    parentStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getVwCompanyParentDetailsRemoteModel() {
        return _vwCompanyParentDetailsRemoteModel;
    }

    public void setVwCompanyParentDetailsRemoteModel(
        BaseModel<?> vwCompanyParentDetailsRemoteModel) {
        _vwCompanyParentDetailsRemoteModel = vwCompanyParentDetailsRemoteModel;
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

        Class<?> remoteModelClass = _vwCompanyParentDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_vwCompanyParentDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            VwCompanyParentDetailsLocalServiceUtil.addVwCompanyParentDetails(this);
        } else {
            VwCompanyParentDetailsLocalServiceUtil.updateVwCompanyParentDetails(this);
        }
    }

    @Override
    public VwCompanyParentDetails toEscapedModel() {
        return (VwCompanyParentDetails) ProxyUtil.newProxyInstance(VwCompanyParentDetails.class.getClassLoader(),
            new Class[] { VwCompanyParentDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        VwCompanyParentDetailsClp clone = new VwCompanyParentDetailsClp();

        clone.setParentCompanyId(getParentCompanyId());
        clone.setPriorParentCompanyId(getPriorParentCompanyId());
        clone.setCompanyId(getCompanyId());
        clone.setLastUpdatedDate(getLastUpdatedDate());
        clone.setParentEndDate(getParentEndDate());
        clone.setModifiedDate(getModifiedDate());
        clone.setPriorParentStartDate(getPriorParentStartDate());
        clone.setSource(getSource());
        clone.setCreatedBy(getCreatedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setCompanyParentDetailsSid(getCompanyParentDetailsSid());
        clone.setBatchId(getBatchId());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setModifiedBy(getModifiedBy());
        clone.setParentStartDate(getParentStartDate());

        return clone;
    }

    @Override
    public int compareTo(VwCompanyParentDetails vwCompanyParentDetails) {
        int primaryKey = vwCompanyParentDetails.getPrimaryKey();

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

        if (!(obj instanceof VwCompanyParentDetailsClp)) {
            return false;
        }

        VwCompanyParentDetailsClp vwCompanyParentDetails = (VwCompanyParentDetailsClp) obj;

        int primaryKey = vwCompanyParentDetails.getPrimaryKey();

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

        sb.append("{parentCompanyId=");
        sb.append(getParentCompanyId());
        sb.append(", priorParentCompanyId=");
        sb.append(getPriorParentCompanyId());
        sb.append(", companyId=");
        sb.append(getCompanyId());
        sb.append(", lastUpdatedDate=");
        sb.append(getLastUpdatedDate());
        sb.append(", parentEndDate=");
        sb.append(getParentEndDate());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", priorParentStartDate=");
        sb.append(getPriorParentStartDate());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", companyParentDetailsSid=");
        sb.append(getCompanyParentDetailsSid());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", parentStartDate=");
        sb.append(getParentStartDate());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(49);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.VwCompanyParentDetails");
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
            "<column><column-name>companyParentDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyParentDetailsSid());
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
        sb.append(
            "<column><column-name>parentStartDate</column-name><column-value><![CDATA[");
        sb.append(getParentStartDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
