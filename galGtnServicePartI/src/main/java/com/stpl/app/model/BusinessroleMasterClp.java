package com.stpl.app.model;

import com.stpl.app.service.BusinessroleMasterLocalServiceUtil;
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


public class BusinessroleMasterClp extends BaseModelImpl<BusinessroleMaster>
    implements BusinessroleMaster {
    private int _businessroleMasterSid;
    private Date _createdDate;
    private int _createdBy;
    private int _usersSid;
    private int _hierarchyLevel;
    private String _processed;
    private String _businessroleName;
    private int _versionNo;
    private int _modifiedBy;
    private Date _modifiedDate;
    private String _businessroleDesc;
    private String _isActive;
    private BaseModel<?> _businessroleMasterRemoteModel;

    public BusinessroleMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return BusinessroleMaster.class;
    }

    @Override
    public String getModelClassName() {
        return BusinessroleMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _businessroleMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setBusinessroleMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _businessroleMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("businessroleMasterSid", getBusinessroleMasterSid());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("usersSid", getUsersSid());
        attributes.put("hierarchyLevel", getHierarchyLevel());
        attributes.put("processed", getProcessed());
        attributes.put("businessroleName", getBusinessroleName());
        attributes.put("versionNo", getVersionNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("businessroleDesc", getBusinessroleDesc());
        attributes.put("isActive", getIsActive());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer businessroleMasterSid = (Integer) attributes.get(
                "businessroleMasterSid");

        if (businessroleMasterSid != null) {
            setBusinessroleMasterSid(businessroleMasterSid);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer usersSid = (Integer) attributes.get("usersSid");

        if (usersSid != null) {
            setUsersSid(usersSid);
        }

        Integer hierarchyLevel = (Integer) attributes.get("hierarchyLevel");

        if (hierarchyLevel != null) {
            setHierarchyLevel(hierarchyLevel);
        }

        String processed = (String) attributes.get("processed");

        if (processed != null) {
            setProcessed(processed);
        }

        String businessroleName = (String) attributes.get("businessroleName");

        if (businessroleName != null) {
            setBusinessroleName(businessroleName);
        }

        Integer versionNo = (Integer) attributes.get("versionNo");

        if (versionNo != null) {
            setVersionNo(versionNo);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String businessroleDesc = (String) attributes.get("businessroleDesc");

        if (businessroleDesc != null) {
            setBusinessroleDesc(businessroleDesc);
        }

        String isActive = (String) attributes.get("isActive");

        if (isActive != null) {
            setIsActive(isActive);
        }
    }

    @Override
    public int getBusinessroleMasterSid() {
        return _businessroleMasterSid;
    }

    @Override
    public void setBusinessroleMasterSid(int businessroleMasterSid) {
        _businessroleMasterSid = businessroleMasterSid;

        if (_businessroleMasterRemoteModel != null) {
            try {
                Class<?> clazz = _businessroleMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBusinessroleMasterSid",
                        int.class);

                method.invoke(_businessroleMasterRemoteModel,
                    businessroleMasterSid);
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

        if (_businessroleMasterRemoteModel != null) {
            try {
                Class<?> clazz = _businessroleMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_businessroleMasterRemoteModel, createdDate);
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

        if (_businessroleMasterRemoteModel != null) {
            try {
                Class<?> clazz = _businessroleMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_businessroleMasterRemoteModel, createdBy);
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

        if (_businessroleMasterRemoteModel != null) {
            try {
                Class<?> clazz = _businessroleMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUsersSid", int.class);

                method.invoke(_businessroleMasterRemoteModel, usersSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getHierarchyLevel() {
        return _hierarchyLevel;
    }

    @Override
    public void setHierarchyLevel(int hierarchyLevel) {
        _hierarchyLevel = hierarchyLevel;

        if (_businessroleMasterRemoteModel != null) {
            try {
                Class<?> clazz = _businessroleMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setHierarchyLevel", int.class);

                method.invoke(_businessroleMasterRemoteModel, hierarchyLevel);
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

        if (_businessroleMasterRemoteModel != null) {
            try {
                Class<?> clazz = _businessroleMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProcessed", String.class);

                method.invoke(_businessroleMasterRemoteModel, processed);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBusinessroleName() {
        return _businessroleName;
    }

    @Override
    public void setBusinessroleName(String businessroleName) {
        _businessroleName = businessroleName;

        if (_businessroleMasterRemoteModel != null) {
            try {
                Class<?> clazz = _businessroleMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBusinessroleName",
                        String.class);

                method.invoke(_businessroleMasterRemoteModel, businessroleName);
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

        if (_businessroleMasterRemoteModel != null) {
            try {
                Class<?> clazz = _businessroleMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setVersionNo", int.class);

                method.invoke(_businessroleMasterRemoteModel, versionNo);
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

        if (_businessroleMasterRemoteModel != null) {
            try {
                Class<?> clazz = _businessroleMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_businessroleMasterRemoteModel, modifiedBy);
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

        if (_businessroleMasterRemoteModel != null) {
            try {
                Class<?> clazz = _businessroleMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_businessroleMasterRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBusinessroleDesc() {
        return _businessroleDesc;
    }

    @Override
    public void setBusinessroleDesc(String businessroleDesc) {
        _businessroleDesc = businessroleDesc;

        if (_businessroleMasterRemoteModel != null) {
            try {
                Class<?> clazz = _businessroleMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBusinessroleDesc",
                        String.class);

                method.invoke(_businessroleMasterRemoteModel, businessroleDesc);
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

        if (_businessroleMasterRemoteModel != null) {
            try {
                Class<?> clazz = _businessroleMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setIsActive", String.class);

                method.invoke(_businessroleMasterRemoteModel, isActive);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getBusinessroleMasterRemoteModel() {
        return _businessroleMasterRemoteModel;
    }

    public void setBusinessroleMasterRemoteModel(
        BaseModel<?> businessroleMasterRemoteModel) {
        _businessroleMasterRemoteModel = businessroleMasterRemoteModel;
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

        Class<?> remoteModelClass = _businessroleMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_businessroleMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            BusinessroleMasterLocalServiceUtil.addBusinessroleMaster(this);
        } else {
            BusinessroleMasterLocalServiceUtil.updateBusinessroleMaster(this);
        }
    }

    @Override
    public BusinessroleMaster toEscapedModel() {
        return (BusinessroleMaster) ProxyUtil.newProxyInstance(BusinessroleMaster.class.getClassLoader(),
            new Class[] { BusinessroleMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        BusinessroleMasterClp clone = new BusinessroleMasterClp();

        clone.setBusinessroleMasterSid(getBusinessroleMasterSid());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setUsersSid(getUsersSid());
        clone.setHierarchyLevel(getHierarchyLevel());
        clone.setProcessed(getProcessed());
        clone.setBusinessroleName(getBusinessroleName());
        clone.setVersionNo(getVersionNo());
        clone.setModifiedBy(getModifiedBy());
        clone.setModifiedDate(getModifiedDate());
        clone.setBusinessroleDesc(getBusinessroleDesc());
        clone.setIsActive(getIsActive());

        return clone;
    }

    @Override
    public int compareTo(BusinessroleMaster businessroleMaster) {
        int value = 0;

        value = getBusinessroleName()
                    .compareToIgnoreCase(businessroleMaster.getBusinessroleName());

        if (value != 0) {
            return value;
        }

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof BusinessroleMasterClp)) {
            return false;
        }

        BusinessroleMasterClp businessroleMaster = (BusinessroleMasterClp) obj;

        int primaryKey = businessroleMaster.getPrimaryKey();

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
        StringBundler sb = new StringBundler(25);

        sb.append("{businessroleMasterSid=");
        sb.append(getBusinessroleMasterSid());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", usersSid=");
        sb.append(getUsersSid());
        sb.append(", hierarchyLevel=");
        sb.append(getHierarchyLevel());
        sb.append(", processed=");
        sb.append(getProcessed());
        sb.append(", businessroleName=");
        sb.append(getBusinessroleName());
        sb.append(", versionNo=");
        sb.append(getVersionNo());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", businessroleDesc=");
        sb.append(getBusinessroleDesc());
        sb.append(", isActive=");
        sb.append(getIsActive());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(40);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.BusinessroleMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>businessroleMasterSid</column-name><column-value><![CDATA[");
        sb.append(getBusinessroleMasterSid());
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
            "<column><column-name>usersSid</column-name><column-value><![CDATA[");
        sb.append(getUsersSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>hierarchyLevel</column-name><column-value><![CDATA[");
        sb.append(getHierarchyLevel());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>processed</column-name><column-value><![CDATA[");
        sb.append(getProcessed());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>businessroleName</column-name><column-value><![CDATA[");
        sb.append(getBusinessroleName());
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
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>businessroleDesc</column-name><column-value><![CDATA[");
        sb.append(getBusinessroleDesc());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>isActive</column-name><column-value><![CDATA[");
        sb.append(getIsActive());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
