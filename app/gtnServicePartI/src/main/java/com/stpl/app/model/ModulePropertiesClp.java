package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.ModulePropertiesLocalServiceUtil;

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


public class ModulePropertiesClp extends BaseModelImpl<ModuleProperties>
    implements ModuleProperties {
    private int _modulePropertySid;
    private int _createdBy;
    private String _moduleName;
    private int _modifiedBy;
    private Date _createdDate;
    private String _nullFlag;
    private int _versionNo;
    private int _moduleSubmoduleSid;
    private String _categoryName;
    private String _propertyName;
    private String _displayName;
    private Date _modifiedDate;
    private BaseModel<?> _modulePropertiesRemoteModel;

    public ModulePropertiesClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ModuleProperties.class;
    }

    @Override
    public String getModelClassName() {
        return ModuleProperties.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _modulePropertySid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setModulePropertySid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _modulePropertySid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("modulePropertySid", getModulePropertySid());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("moduleName", getModuleName());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("nullFlag", getNullFlag());
        attributes.put("versionNo", getVersionNo());
        attributes.put("moduleSubmoduleSid", getModuleSubmoduleSid());
        attributes.put("categoryName", getCategoryName());
        attributes.put("propertyName", getPropertyName());
        attributes.put("displayName", getDisplayName());
        attributes.put("modifiedDate", getModifiedDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer modulePropertySid = (Integer) attributes.get(
                "modulePropertySid");

        if (modulePropertySid != null) {
            setModulePropertySid(modulePropertySid);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String moduleName = (String) attributes.get("moduleName");

        if (moduleName != null) {
            setModuleName(moduleName);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String nullFlag = (String) attributes.get("nullFlag");

        if (nullFlag != null) {
            setNullFlag(nullFlag);
        }

        Integer versionNo = (Integer) attributes.get("versionNo");

        if (versionNo != null) {
            setVersionNo(versionNo);
        }

        Integer moduleSubmoduleSid = (Integer) attributes.get(
                "moduleSubmoduleSid");

        if (moduleSubmoduleSid != null) {
            setModuleSubmoduleSid(moduleSubmoduleSid);
        }

        String categoryName = (String) attributes.get("categoryName");

        if (categoryName != null) {
            setCategoryName(categoryName);
        }

        String propertyName = (String) attributes.get("propertyName");

        if (propertyName != null) {
            setPropertyName(propertyName);
        }

        String displayName = (String) attributes.get("displayName");

        if (displayName != null) {
            setDisplayName(displayName);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }
    }

    @Override
    public int getModulePropertySid() {
        return _modulePropertySid;
    }

    @Override
    public void setModulePropertySid(int modulePropertySid) {
        _modulePropertySid = modulePropertySid;

        if (_modulePropertiesRemoteModel != null) {
            try {
                Class<?> clazz = _modulePropertiesRemoteModel.getClass();

                Method method = clazz.getMethod("setModulePropertySid",
                        int.class);

                method.invoke(_modulePropertiesRemoteModel, modulePropertySid);
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

        if (_modulePropertiesRemoteModel != null) {
            try {
                Class<?> clazz = _modulePropertiesRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_modulePropertiesRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getModuleName() {
        return _moduleName;
    }

    @Override
    public void setModuleName(String moduleName) {
        _moduleName = moduleName;

        if (_modulePropertiesRemoteModel != null) {
            try {
                Class<?> clazz = _modulePropertiesRemoteModel.getClass();

                Method method = clazz.getMethod("setModuleName", String.class);

                method.invoke(_modulePropertiesRemoteModel, moduleName);
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

        if (_modulePropertiesRemoteModel != null) {
            try {
                Class<?> clazz = _modulePropertiesRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_modulePropertiesRemoteModel, modifiedBy);
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

        if (_modulePropertiesRemoteModel != null) {
            try {
                Class<?> clazz = _modulePropertiesRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_modulePropertiesRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNullFlag() {
        return _nullFlag;
    }

    @Override
    public void setNullFlag(String nullFlag) {
        _nullFlag = nullFlag;

        if (_modulePropertiesRemoteModel != null) {
            try {
                Class<?> clazz = _modulePropertiesRemoteModel.getClass();

                Method method = clazz.getMethod("setNullFlag", String.class);

                method.invoke(_modulePropertiesRemoteModel, nullFlag);
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

        if (_modulePropertiesRemoteModel != null) {
            try {
                Class<?> clazz = _modulePropertiesRemoteModel.getClass();

                Method method = clazz.getMethod("setVersionNo", int.class);

                method.invoke(_modulePropertiesRemoteModel, versionNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getModuleSubmoduleSid() {
        return _moduleSubmoduleSid;
    }

    @Override
    public void setModuleSubmoduleSid(int moduleSubmoduleSid) {
        _moduleSubmoduleSid = moduleSubmoduleSid;

        if (_modulePropertiesRemoteModel != null) {
            try {
                Class<?> clazz = _modulePropertiesRemoteModel.getClass();

                Method method = clazz.getMethod("setModuleSubmoduleSid",
                        int.class);

                method.invoke(_modulePropertiesRemoteModel, moduleSubmoduleSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCategoryName() {
        return _categoryName;
    }

    @Override
    public void setCategoryName(String categoryName) {
        _categoryName = categoryName;

        if (_modulePropertiesRemoteModel != null) {
            try {
                Class<?> clazz = _modulePropertiesRemoteModel.getClass();

                Method method = clazz.getMethod("setCategoryName", String.class);

                method.invoke(_modulePropertiesRemoteModel, categoryName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPropertyName() {
        return _propertyName;
    }

    @Override
    public void setPropertyName(String propertyName) {
        _propertyName = propertyName;

        if (_modulePropertiesRemoteModel != null) {
            try {
                Class<?> clazz = _modulePropertiesRemoteModel.getClass();

                Method method = clazz.getMethod("setPropertyName", String.class);

                method.invoke(_modulePropertiesRemoteModel, propertyName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDisplayName() {
        return _displayName;
    }

    @Override
    public void setDisplayName(String displayName) {
        _displayName = displayName;

        if (_modulePropertiesRemoteModel != null) {
            try {
                Class<?> clazz = _modulePropertiesRemoteModel.getClass();

                Method method = clazz.getMethod("setDisplayName", String.class);

                method.invoke(_modulePropertiesRemoteModel, displayName);
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

        if (_modulePropertiesRemoteModel != null) {
            try {
                Class<?> clazz = _modulePropertiesRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_modulePropertiesRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getModulePropertiesRemoteModel() {
        return _modulePropertiesRemoteModel;
    }

    public void setModulePropertiesRemoteModel(
        BaseModel<?> modulePropertiesRemoteModel) {
        _modulePropertiesRemoteModel = modulePropertiesRemoteModel;
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

        Class<?> remoteModelClass = _modulePropertiesRemoteModel.getClass();

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

        Object returnValue = method.invoke(_modulePropertiesRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ModulePropertiesLocalServiceUtil.addModuleProperties(this);
        } else {
            ModulePropertiesLocalServiceUtil.updateModuleProperties(this);
        }
    }

    @Override
    public ModuleProperties toEscapedModel() {
        return (ModuleProperties) ProxyUtil.newProxyInstance(ModuleProperties.class.getClassLoader(),
            new Class[] { ModuleProperties.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ModulePropertiesClp clone = new ModulePropertiesClp();

        clone.setModulePropertySid(getModulePropertySid());
        clone.setCreatedBy(getCreatedBy());
        clone.setModuleName(getModuleName());
        clone.setModifiedBy(getModifiedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setNullFlag(getNullFlag());
        clone.setVersionNo(getVersionNo());
        clone.setModuleSubmoduleSid(getModuleSubmoduleSid());
        clone.setCategoryName(getCategoryName());
        clone.setPropertyName(getPropertyName());
        clone.setDisplayName(getDisplayName());
        clone.setModifiedDate(getModifiedDate());

        return clone;
    }

    @Override
    public int compareTo(ModuleProperties moduleProperties) {
        int primaryKey = moduleProperties.getPrimaryKey();

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

        if (!(obj instanceof ModulePropertiesClp)) {
            return false;
        }

        ModulePropertiesClp moduleProperties = (ModulePropertiesClp) obj;

        int primaryKey = moduleProperties.getPrimaryKey();

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

        sb.append("{modulePropertySid=");
        sb.append(getModulePropertySid());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", moduleName=");
        sb.append(getModuleName());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", nullFlag=");
        sb.append(getNullFlag());
        sb.append(", versionNo=");
        sb.append(getVersionNo());
        sb.append(", moduleSubmoduleSid=");
        sb.append(getModuleSubmoduleSid());
        sb.append(", categoryName=");
        sb.append(getCategoryName());
        sb.append(", propertyName=");
        sb.append(getPropertyName());
        sb.append(", displayName=");
        sb.append(getDisplayName());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(40);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ModuleProperties");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>modulePropertySid</column-name><column-value><![CDATA[");
        sb.append(getModulePropertySid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>moduleName</column-name><column-value><![CDATA[");
        sb.append(getModuleName());
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
            "<column><column-name>nullFlag</column-name><column-value><![CDATA[");
        sb.append(getNullFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>versionNo</column-name><column-value><![CDATA[");
        sb.append(getVersionNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>moduleSubmoduleSid</column-name><column-value><![CDATA[");
        sb.append(getModuleSubmoduleSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>categoryName</column-name><column-value><![CDATA[");
        sb.append(getCategoryName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>propertyName</column-name><column-value><![CDATA[");
        sb.append(getPropertyName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>displayName</column-name><column-value><![CDATA[");
        sb.append(getDisplayName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
