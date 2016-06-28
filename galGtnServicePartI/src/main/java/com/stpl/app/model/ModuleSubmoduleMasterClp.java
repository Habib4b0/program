package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.ModuleSubmoduleMasterLocalServiceUtil;

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


public class ModuleSubmoduleMasterClp extends BaseModelImpl<ModuleSubmoduleMaster>
    implements ModuleSubmoduleMaster {
    private Date _createdDate;
    private int _createdBy;
    private String _category;
    private int _moduleSubmoduleSid;
    private String _submoduleName;
    private String _moduleName;
    private int _versionNo;
    private int _modifiedBy;
    private Date _modifiedDate;
    private BaseModel<?> _moduleSubmoduleMasterRemoteModel;

    public ModuleSubmoduleMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ModuleSubmoduleMaster.class;
    }

    @Override
    public String getModelClassName() {
        return ModuleSubmoduleMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _moduleSubmoduleSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setModuleSubmoduleSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _moduleSubmoduleSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("category", getCategory());
        attributes.put("moduleSubmoduleSid", getModuleSubmoduleSid());
        attributes.put("submoduleName", getSubmoduleName());
        attributes.put("moduleName", getModuleName());
        attributes.put("versionNo", getVersionNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("modifiedDate", getModifiedDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String category = (String) attributes.get("category");

        if (category != null) {
            setCategory(category);
        }

        Integer moduleSubmoduleSid = (Integer) attributes.get(
                "moduleSubmoduleSid");

        if (moduleSubmoduleSid != null) {
            setModuleSubmoduleSid(moduleSubmoduleSid);
        }

        String submoduleName = (String) attributes.get("submoduleName");

        if (submoduleName != null) {
            setSubmoduleName(submoduleName);
        }

        String moduleName = (String) attributes.get("moduleName");

        if (moduleName != null) {
            setModuleName(moduleName);
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
    }

    @Override
    public Date getCreatedDate() {
        return _createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;

        if (_moduleSubmoduleMasterRemoteModel != null) {
            try {
                Class<?> clazz = _moduleSubmoduleMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_moduleSubmoduleMasterRemoteModel, createdDate);
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

        if (_moduleSubmoduleMasterRemoteModel != null) {
            try {
                Class<?> clazz = _moduleSubmoduleMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_moduleSubmoduleMasterRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCategory() {
        return _category;
    }

    @Override
    public void setCategory(String category) {
        _category = category;

        if (_moduleSubmoduleMasterRemoteModel != null) {
            try {
                Class<?> clazz = _moduleSubmoduleMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCategory", String.class);

                method.invoke(_moduleSubmoduleMasterRemoteModel, category);
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

        if (_moduleSubmoduleMasterRemoteModel != null) {
            try {
                Class<?> clazz = _moduleSubmoduleMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModuleSubmoduleSid",
                        int.class);

                method.invoke(_moduleSubmoduleMasterRemoteModel,
                    moduleSubmoduleSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSubmoduleName() {
        return _submoduleName;
    }

    @Override
    public void setSubmoduleName(String submoduleName) {
        _submoduleName = submoduleName;

        if (_moduleSubmoduleMasterRemoteModel != null) {
            try {
                Class<?> clazz = _moduleSubmoduleMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSubmoduleName", String.class);

                method.invoke(_moduleSubmoduleMasterRemoteModel, submoduleName);
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

        if (_moduleSubmoduleMasterRemoteModel != null) {
            try {
                Class<?> clazz = _moduleSubmoduleMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModuleName", String.class);

                method.invoke(_moduleSubmoduleMasterRemoteModel, moduleName);
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

        if (_moduleSubmoduleMasterRemoteModel != null) {
            try {
                Class<?> clazz = _moduleSubmoduleMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setVersionNo", int.class);

                method.invoke(_moduleSubmoduleMasterRemoteModel, versionNo);
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

        if (_moduleSubmoduleMasterRemoteModel != null) {
            try {
                Class<?> clazz = _moduleSubmoduleMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_moduleSubmoduleMasterRemoteModel, modifiedBy);
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

        if (_moduleSubmoduleMasterRemoteModel != null) {
            try {
                Class<?> clazz = _moduleSubmoduleMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_moduleSubmoduleMasterRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getModuleSubmoduleMasterRemoteModel() {
        return _moduleSubmoduleMasterRemoteModel;
    }

    public void setModuleSubmoduleMasterRemoteModel(
        BaseModel<?> moduleSubmoduleMasterRemoteModel) {
        _moduleSubmoduleMasterRemoteModel = moduleSubmoduleMasterRemoteModel;
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

        Class<?> remoteModelClass = _moduleSubmoduleMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_moduleSubmoduleMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ModuleSubmoduleMasterLocalServiceUtil.addModuleSubmoduleMaster(this);
        } else {
            ModuleSubmoduleMasterLocalServiceUtil.updateModuleSubmoduleMaster(this);
        }
    }

    @Override
    public ModuleSubmoduleMaster toEscapedModel() {
        return (ModuleSubmoduleMaster) ProxyUtil.newProxyInstance(ModuleSubmoduleMaster.class.getClassLoader(),
            new Class[] { ModuleSubmoduleMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ModuleSubmoduleMasterClp clone = new ModuleSubmoduleMasterClp();

        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setCategory(getCategory());
        clone.setModuleSubmoduleSid(getModuleSubmoduleSid());
        clone.setSubmoduleName(getSubmoduleName());
        clone.setModuleName(getModuleName());
        clone.setVersionNo(getVersionNo());
        clone.setModifiedBy(getModifiedBy());
        clone.setModifiedDate(getModifiedDate());

        return clone;
    }

    @Override
    public int compareTo(ModuleSubmoduleMaster moduleSubmoduleMaster) {
        int primaryKey = moduleSubmoduleMaster.getPrimaryKey();

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

        if (!(obj instanceof ModuleSubmoduleMasterClp)) {
            return false;
        }

        ModuleSubmoduleMasterClp moduleSubmoduleMaster = (ModuleSubmoduleMasterClp) obj;

        int primaryKey = moduleSubmoduleMaster.getPrimaryKey();

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
        StringBundler sb = new StringBundler(19);

        sb.append("{createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", category=");
        sb.append(getCategory());
        sb.append(", moduleSubmoduleSid=");
        sb.append(getModuleSubmoduleSid());
        sb.append(", submoduleName=");
        sb.append(getSubmoduleName());
        sb.append(", moduleName=");
        sb.append(getModuleName());
        sb.append(", versionNo=");
        sb.append(getVersionNo());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(31);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ModuleSubmoduleMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>category</column-name><column-value><![CDATA[");
        sb.append(getCategory());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>moduleSubmoduleSid</column-name><column-value><![CDATA[");
        sb.append(getModuleSubmoduleSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>submoduleName</column-name><column-value><![CDATA[");
        sb.append(getSubmoduleName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>moduleName</column-name><column-value><![CDATA[");
        sb.append(getModuleName());
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

        sb.append("</model>");

        return sb.toString();
    }
}
