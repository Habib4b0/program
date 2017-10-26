package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.UsergroupBusinessroleLocalServiceUtil;

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


public class UsergroupBusinessroleClp extends BaseModelImpl<UsergroupBusinessrole>
    implements UsergroupBusinessrole {
    private int _createdBy;
    private int _businessroleMasterSid;
    private int _usersSid;
    private int _modifiedBy;
    private Date _createdDate;
    private String _processed;
    private int _usergroupId;
    private int _versionNo;
    private String _isActive;
    private int _usergroupBusinessroleSid;
    private Date _modifiedDate;
    private BaseModel<?> _usergroupBusinessroleRemoteModel;

    public UsergroupBusinessroleClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return UsergroupBusinessrole.class;
    }

    @Override
    public String getModelClassName() {
        return UsergroupBusinessrole.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _usergroupBusinessroleSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setUsergroupBusinessroleSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _usergroupBusinessroleSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("businessroleMasterSid", getBusinessroleMasterSid());
        attributes.put("usersSid", getUsersSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("processed", getProcessed());
        attributes.put("usergroupId", getUsergroupId());
        attributes.put("versionNo", getVersionNo());
        attributes.put("isActive", getIsActive());
        attributes.put("usergroupBusinessroleSid", getUsergroupBusinessroleSid());
        attributes.put("modifiedDate", getModifiedDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer businessroleMasterSid = (Integer) attributes.get(
                "businessroleMasterSid");

        if (businessroleMasterSid != null) {
            setBusinessroleMasterSid(businessroleMasterSid);
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

        Integer usergroupBusinessroleSid = (Integer) attributes.get(
                "usergroupBusinessroleSid");

        if (usergroupBusinessroleSid != null) {
            setUsergroupBusinessroleSid(usergroupBusinessroleSid);
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

        if (_usergroupBusinessroleRemoteModel != null) {
            try {
                Class<?> clazz = _usergroupBusinessroleRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_usergroupBusinessroleRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getBusinessroleMasterSid() {
        return _businessroleMasterSid;
    }

    @Override
    public void setBusinessroleMasterSid(int businessroleMasterSid) {
        _businessroleMasterSid = businessroleMasterSid;

        if (_usergroupBusinessroleRemoteModel != null) {
            try {
                Class<?> clazz = _usergroupBusinessroleRemoteModel.getClass();

                Method method = clazz.getMethod("setBusinessroleMasterSid",
                        int.class);

                method.invoke(_usergroupBusinessroleRemoteModel,
                    businessroleMasterSid);
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

        if (_usergroupBusinessroleRemoteModel != null) {
            try {
                Class<?> clazz = _usergroupBusinessroleRemoteModel.getClass();

                Method method = clazz.getMethod("setUsersSid", int.class);

                method.invoke(_usergroupBusinessroleRemoteModel, usersSid);
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

        if (_usergroupBusinessroleRemoteModel != null) {
            try {
                Class<?> clazz = _usergroupBusinessroleRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_usergroupBusinessroleRemoteModel, modifiedBy);
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

        if (_usergroupBusinessroleRemoteModel != null) {
            try {
                Class<?> clazz = _usergroupBusinessroleRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_usergroupBusinessroleRemoteModel, createdDate);
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

        if (_usergroupBusinessroleRemoteModel != null) {
            try {
                Class<?> clazz = _usergroupBusinessroleRemoteModel.getClass();

                Method method = clazz.getMethod("setProcessed", String.class);

                method.invoke(_usergroupBusinessroleRemoteModel, processed);
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

        if (_usergroupBusinessroleRemoteModel != null) {
            try {
                Class<?> clazz = _usergroupBusinessroleRemoteModel.getClass();

                Method method = clazz.getMethod("setUsergroupId", int.class);

                method.invoke(_usergroupBusinessroleRemoteModel, usergroupId);
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

        if (_usergroupBusinessroleRemoteModel != null) {
            try {
                Class<?> clazz = _usergroupBusinessroleRemoteModel.getClass();

                Method method = clazz.getMethod("setVersionNo", int.class);

                method.invoke(_usergroupBusinessroleRemoteModel, versionNo);
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

        if (_usergroupBusinessroleRemoteModel != null) {
            try {
                Class<?> clazz = _usergroupBusinessroleRemoteModel.getClass();

                Method method = clazz.getMethod("setIsActive", String.class);

                method.invoke(_usergroupBusinessroleRemoteModel, isActive);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getUsergroupBusinessroleSid() {
        return _usergroupBusinessroleSid;
    }

    @Override
    public void setUsergroupBusinessroleSid(int usergroupBusinessroleSid) {
        _usergroupBusinessroleSid = usergroupBusinessroleSid;

        if (_usergroupBusinessroleRemoteModel != null) {
            try {
                Class<?> clazz = _usergroupBusinessroleRemoteModel.getClass();

                Method method = clazz.getMethod("setUsergroupBusinessroleSid",
                        int.class);

                method.invoke(_usergroupBusinessroleRemoteModel,
                    usergroupBusinessroleSid);
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

        if (_usergroupBusinessroleRemoteModel != null) {
            try {
                Class<?> clazz = _usergroupBusinessroleRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_usergroupBusinessroleRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getUsergroupBusinessroleRemoteModel() {
        return _usergroupBusinessroleRemoteModel;
    }

    public void setUsergroupBusinessroleRemoteModel(
        BaseModel<?> usergroupBusinessroleRemoteModel) {
        _usergroupBusinessroleRemoteModel = usergroupBusinessroleRemoteModel;
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

        Class<?> remoteModelClass = _usergroupBusinessroleRemoteModel.getClass();

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

        Object returnValue = method.invoke(_usergroupBusinessroleRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            UsergroupBusinessroleLocalServiceUtil.addUsergroupBusinessrole(this);
        } else {
            UsergroupBusinessroleLocalServiceUtil.updateUsergroupBusinessrole(this);
        }
    }

    @Override
    public UsergroupBusinessrole toEscapedModel() {
        return (UsergroupBusinessrole) ProxyUtil.newProxyInstance(UsergroupBusinessrole.class.getClassLoader(),
            new Class[] { UsergroupBusinessrole.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        UsergroupBusinessroleClp clone = new UsergroupBusinessroleClp();

        clone.setCreatedBy(getCreatedBy());
        clone.setBusinessroleMasterSid(getBusinessroleMasterSid());
        clone.setUsersSid(getUsersSid());
        clone.setModifiedBy(getModifiedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setProcessed(getProcessed());
        clone.setUsergroupId(getUsergroupId());
        clone.setVersionNo(getVersionNo());
        clone.setIsActive(getIsActive());
        clone.setUsergroupBusinessroleSid(getUsergroupBusinessroleSid());
        clone.setModifiedDate(getModifiedDate());

        return clone;
    }

    @Override
    public int compareTo(UsergroupBusinessrole usergroupBusinessrole) {
        int primaryKey = usergroupBusinessrole.getPrimaryKey();

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

        if (!(obj instanceof UsergroupBusinessroleClp)) {
            return false;
        }

        UsergroupBusinessroleClp usergroupBusinessrole = (UsergroupBusinessroleClp) obj;

        int primaryKey = usergroupBusinessrole.getPrimaryKey();

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
        sb.append(", businessroleMasterSid=");
        sb.append(getBusinessroleMasterSid());
        sb.append(", usersSid=");
        sb.append(getUsersSid());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", processed=");
        sb.append(getProcessed());
        sb.append(", usergroupId=");
        sb.append(getUsergroupId());
        sb.append(", versionNo=");
        sb.append(getVersionNo());
        sb.append(", isActive=");
        sb.append(getIsActive());
        sb.append(", usergroupBusinessroleSid=");
        sb.append(getUsergroupBusinessroleSid());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(37);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.UsergroupBusinessrole");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>businessroleMasterSid</column-name><column-value><![CDATA[");
        sb.append(getBusinessroleMasterSid());
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
            "<column><column-name>usergroupBusinessroleSid</column-name><column-value><![CDATA[");
        sb.append(getUsergroupBusinessroleSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
