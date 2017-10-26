package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.DeductionGroupDetailsLocalServiceUtil;

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


public class DeductionGroupDetailsClp extends BaseModelImpl<DeductionGroupDetails>
    implements DeductionGroupDetails {
    private int _deductionGroupDetailsSid;
    private Date _createdDate;
    private int _createdBy;
    private int _deductionGroupSid;
    private int _versionNo;
    private int _modifiedBy;
    private int _rsModelSid;
    private Date _modifiedDate;
    private BaseModel<?> _deductionGroupDetailsRemoteModel;

    public DeductionGroupDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return DeductionGroupDetails.class;
    }

    @Override
    public String getModelClassName() {
        return DeductionGroupDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _deductionGroupDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setDeductionGroupDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _deductionGroupDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("deductionGroupDetailsSid", getDeductionGroupDetailsSid());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("deductionGroupSid", getDeductionGroupSid());
        attributes.put("versionNo", getVersionNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("rsModelSid", getRsModelSid());
        attributes.put("modifiedDate", getModifiedDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer deductionGroupDetailsSid = (Integer) attributes.get(
                "deductionGroupDetailsSid");

        if (deductionGroupDetailsSid != null) {
            setDeductionGroupDetailsSid(deductionGroupDetailsSid);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer deductionGroupSid = (Integer) attributes.get(
                "deductionGroupSid");

        if (deductionGroupSid != null) {
            setDeductionGroupSid(deductionGroupSid);
        }

        Integer versionNo = (Integer) attributes.get("versionNo");

        if (versionNo != null) {
            setVersionNo(versionNo);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Integer rsModelSid = (Integer) attributes.get("rsModelSid");

        if (rsModelSid != null) {
            setRsModelSid(rsModelSid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }
    }

    @Override
    public int getDeductionGroupDetailsSid() {
        return _deductionGroupDetailsSid;
    }

    @Override
    public void setDeductionGroupDetailsSid(int deductionGroupDetailsSid) {
        _deductionGroupDetailsSid = deductionGroupDetailsSid;

        if (_deductionGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _deductionGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionGroupDetailsSid",
                        int.class);

                method.invoke(_deductionGroupDetailsRemoteModel,
                    deductionGroupDetailsSid);
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

        if (_deductionGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _deductionGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_deductionGroupDetailsRemoteModel, createdDate);
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

        if (_deductionGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _deductionGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_deductionGroupDetailsRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getDeductionGroupSid() {
        return _deductionGroupSid;
    }

    @Override
    public void setDeductionGroupSid(int deductionGroupSid) {
        _deductionGroupSid = deductionGroupSid;

        if (_deductionGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _deductionGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionGroupSid",
                        int.class);

                method.invoke(_deductionGroupDetailsRemoteModel,
                    deductionGroupSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getVersionNo() {
        return _versionNo;
    }

    @Override
    public void setVersionNo(int versionNo) {
        _versionNo = versionNo;

        if (_deductionGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _deductionGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setVersionNo", int.class);

                method.invoke(_deductionGroupDetailsRemoteModel, versionNo);
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

        if (_deductionGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _deductionGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_deductionGroupDetailsRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRsModelSid() {
        return _rsModelSid;
    }

    @Override
    public void setRsModelSid(int rsModelSid) {
        _rsModelSid = rsModelSid;

        if (_deductionGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _deductionGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsModelSid", int.class);

                method.invoke(_deductionGroupDetailsRemoteModel, rsModelSid);
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

        if (_deductionGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _deductionGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_deductionGroupDetailsRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getDeductionGroupDetailsRemoteModel() {
        return _deductionGroupDetailsRemoteModel;
    }

    public void setDeductionGroupDetailsRemoteModel(
        BaseModel<?> deductionGroupDetailsRemoteModel) {
        _deductionGroupDetailsRemoteModel = deductionGroupDetailsRemoteModel;
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

        Class<?> remoteModelClass = _deductionGroupDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_deductionGroupDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            DeductionGroupDetailsLocalServiceUtil.addDeductionGroupDetails(this);
        } else {
            DeductionGroupDetailsLocalServiceUtil.updateDeductionGroupDetails(this);
        }
    }

    @Override
    public DeductionGroupDetails toEscapedModel() {
        return (DeductionGroupDetails) ProxyUtil.newProxyInstance(DeductionGroupDetails.class.getClassLoader(),
            new Class[] { DeductionGroupDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        DeductionGroupDetailsClp clone = new DeductionGroupDetailsClp();

        clone.setDeductionGroupDetailsSid(getDeductionGroupDetailsSid());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setDeductionGroupSid(getDeductionGroupSid());
        clone.setVersionNo(getVersionNo());
        clone.setModifiedBy(getModifiedBy());
        clone.setRsModelSid(getRsModelSid());
        clone.setModifiedDate(getModifiedDate());

        return clone;
    }

    @Override
    public int compareTo(DeductionGroupDetails deductionGroupDetails) {
        int primaryKey = deductionGroupDetails.getPrimaryKey();

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

        if (!(obj instanceof DeductionGroupDetailsClp)) {
            return false;
        }

        DeductionGroupDetailsClp deductionGroupDetails = (DeductionGroupDetailsClp) obj;

        int primaryKey = deductionGroupDetails.getPrimaryKey();

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
        StringBundler sb = new StringBundler(17);

        sb.append("{deductionGroupDetailsSid=");
        sb.append(getDeductionGroupDetailsSid());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", deductionGroupSid=");
        sb.append(getDeductionGroupSid());
        sb.append(", versionNo=");
        sb.append(getVersionNo());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", rsModelSid=");
        sb.append(getRsModelSid());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(28);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.DeductionGroupDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>deductionGroupDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getDeductionGroupDetailsSid());
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
            "<column><column-name>deductionGroupSid</column-name><column-value><![CDATA[");
        sb.append(getDeductionGroupSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>versionNo</column-name><column-value><![CDATA[");
        sb.append(getVersionNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsModelSid</column-name><column-value><![CDATA[");
        sb.append(getRsModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
