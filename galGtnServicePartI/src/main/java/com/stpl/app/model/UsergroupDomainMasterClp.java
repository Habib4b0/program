package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.UsergroupDomainMasterLocalServiceUtil;

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


public class UsergroupDomainMasterClp extends BaseModelImpl<UsergroupDomainMaster>
    implements UsergroupDomainMaster {
    private int _createdBy;
    private int _usergroupDomainSid;
    private int _usersSid;
    private int _modifiedBy;
    private Date _createdDate;
    private int _domainId;
    private String _processed;
    private int _usergroupId;
    private int _versionNo;
    private String _isActive;
    private Date _modifiedDate;
    private BaseModel<?> _usergroupDomainMasterRemoteModel;

    public UsergroupDomainMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return UsergroupDomainMaster.class;
    }

    @Override
    public String getModelClassName() {
        return UsergroupDomainMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _usergroupDomainSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setUsergroupDomainSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _usergroupDomainSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("usergroupDomainSid", getUsergroupDomainSid());
        attributes.put("usersSid", getUsersSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("domainId", getDomainId());
        attributes.put("processed", getProcessed());
        attributes.put("usergroupId", getUsergroupId());
        attributes.put("versionNo", getVersionNo());
        attributes.put("isActive", getIsActive());
        attributes.put("modifiedDate", getModifiedDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer usergroupDomainSid = (Integer) attributes.get(
                "usergroupDomainSid");

        if (usergroupDomainSid != null) {
            setUsergroupDomainSid(usergroupDomainSid);
        }

        Integer usersSid = (Integer) attributes.get("usersSid");

        if (usersSid != null) {
            setUsersSid(usersSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer domainId = (Integer) attributes.get("domainId");

        if (domainId != null) {
            setDomainId(domainId);
        }

        String processed = (String) attributes.get("processed");

        if (processed != null) {
            setProcessed(processed);
        }

        Integer usergroupId = (Integer) attributes.get("usergroupId");

        if (usergroupId != null) {
            setUsergroupId(usergroupId);
        }

        Integer versionNo = (Integer) attributes.get("versionNo");

        if (versionNo != null) {
            setVersionNo(versionNo);
        }

        String isActive = (String) attributes.get("isActive");

        if (isActive != null) {
            setIsActive(isActive);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }
    }

    @Override
    public int getCreatedBy() {
        return _createdBy;
    }

    @Override
    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;

        if (_usergroupDomainMasterRemoteModel != null) {
            try {
                Class<?> clazz = _usergroupDomainMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_usergroupDomainMasterRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getUsergroupDomainSid() {
        return _usergroupDomainSid;
    }

    @Override
    public void setUsergroupDomainSid(int usergroupDomainSid) {
        _usergroupDomainSid = usergroupDomainSid;

        if (_usergroupDomainMasterRemoteModel != null) {
            try {
                Class<?> clazz = _usergroupDomainMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUsergroupDomainSid",
                        int.class);

                method.invoke(_usergroupDomainMasterRemoteModel,
                    usergroupDomainSid);
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

        if (_usergroupDomainMasterRemoteModel != null) {
            try {
                Class<?> clazz = _usergroupDomainMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUsersSid", int.class);

                method.invoke(_usergroupDomainMasterRemoteModel, usersSid);
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

        if (_usergroupDomainMasterRemoteModel != null) {
            try {
                Class<?> clazz = _usergroupDomainMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_usergroupDomainMasterRemoteModel, modifiedBy);
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

        if (_usergroupDomainMasterRemoteModel != null) {
            try {
                Class<?> clazz = _usergroupDomainMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_usergroupDomainMasterRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getDomainId() {
        return _domainId;
    }

    @Override
    public void setDomainId(int domainId) {
        _domainId = domainId;

        if (_usergroupDomainMasterRemoteModel != null) {
            try {
                Class<?> clazz = _usergroupDomainMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDomainId", int.class);

                method.invoke(_usergroupDomainMasterRemoteModel, domainId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getProcessed() {
        return _processed;
    }

    @Override
    public void setProcessed(String processed) {
        _processed = processed;

        if (_usergroupDomainMasterRemoteModel != null) {
            try {
                Class<?> clazz = _usergroupDomainMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProcessed", String.class);

                method.invoke(_usergroupDomainMasterRemoteModel, processed);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getUsergroupId() {
        return _usergroupId;
    }

    @Override
    public void setUsergroupId(int usergroupId) {
        _usergroupId = usergroupId;

        if (_usergroupDomainMasterRemoteModel != null) {
            try {
                Class<?> clazz = _usergroupDomainMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUsergroupId", int.class);

                method.invoke(_usergroupDomainMasterRemoteModel, usergroupId);
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

        if (_usergroupDomainMasterRemoteModel != null) {
            try {
                Class<?> clazz = _usergroupDomainMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setVersionNo", int.class);

                method.invoke(_usergroupDomainMasterRemoteModel, versionNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIsActive() {
        return _isActive;
    }

    @Override
    public void setIsActive(String isActive) {
        _isActive = isActive;

        if (_usergroupDomainMasterRemoteModel != null) {
            try {
                Class<?> clazz = _usergroupDomainMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setIsActive", String.class);

                method.invoke(_usergroupDomainMasterRemoteModel, isActive);
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

        if (_usergroupDomainMasterRemoteModel != null) {
            try {
                Class<?> clazz = _usergroupDomainMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_usergroupDomainMasterRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getUsergroupDomainMasterRemoteModel() {
        return _usergroupDomainMasterRemoteModel;
    }

    public void setUsergroupDomainMasterRemoteModel(
        BaseModel<?> usergroupDomainMasterRemoteModel) {
        _usergroupDomainMasterRemoteModel = usergroupDomainMasterRemoteModel;
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

        Class<?> remoteModelClass = _usergroupDomainMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_usergroupDomainMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            UsergroupDomainMasterLocalServiceUtil.addUsergroupDomainMaster(this);
        } else {
            UsergroupDomainMasterLocalServiceUtil.updateUsergroupDomainMaster(this);
        }
    }

    @Override
    public UsergroupDomainMaster toEscapedModel() {
        return (UsergroupDomainMaster) ProxyUtil.newProxyInstance(UsergroupDomainMaster.class.getClassLoader(),
            new Class[] { UsergroupDomainMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        UsergroupDomainMasterClp clone = new UsergroupDomainMasterClp();

        clone.setCreatedBy(getCreatedBy());
        clone.setUsergroupDomainSid(getUsergroupDomainSid());
        clone.setUsersSid(getUsersSid());
        clone.setModifiedBy(getModifiedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setDomainId(getDomainId());
        clone.setProcessed(getProcessed());
        clone.setUsergroupId(getUsergroupId());
        clone.setVersionNo(getVersionNo());
        clone.setIsActive(getIsActive());
        clone.setModifiedDate(getModifiedDate());

        return clone;
    }

    @Override
    public int compareTo(UsergroupDomainMaster usergroupDomainMaster) {
        int primaryKey = usergroupDomainMaster.getPrimaryKey();

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

        if (!(obj instanceof UsergroupDomainMasterClp)) {
            return false;
        }

        UsergroupDomainMasterClp usergroupDomainMaster = (UsergroupDomainMasterClp) obj;

        int primaryKey = usergroupDomainMaster.getPrimaryKey();

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
        StringBundler sb = new StringBundler(23);

        sb.append("{createdBy=");
        sb.append(getCreatedBy());
        sb.append(", usergroupDomainSid=");
        sb.append(getUsergroupDomainSid());
        sb.append(", usersSid=");
        sb.append(getUsersSid());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", domainId=");
        sb.append(getDomainId());
        sb.append(", processed=");
        sb.append(getProcessed());
        sb.append(", usergroupId=");
        sb.append(getUsergroupId());
        sb.append(", versionNo=");
        sb.append(getVersionNo());
        sb.append(", isActive=");
        sb.append(getIsActive());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(37);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.UsergroupDomainMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>usergroupDomainSid</column-name><column-value><![CDATA[");
        sb.append(getUsergroupDomainSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>usersSid</column-name><column-value><![CDATA[");
        sb.append(getUsersSid());
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
            "<column><column-name>domainId</column-name><column-value><![CDATA[");
        sb.append(getDomainId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>processed</column-name><column-value><![CDATA[");
        sb.append(getProcessed());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>usergroupId</column-name><column-value><![CDATA[");
        sb.append(getUsergroupId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>versionNo</column-name><column-value><![CDATA[");
        sb.append(getVersionNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>isActive</column-name><column-value><![CDATA[");
        sb.append(getIsActive());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
