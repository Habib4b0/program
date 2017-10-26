package com.stpl.app.model;

import com.stpl.app.service.BusinessroleModuleLocalServiceUtil;
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


public class BusinessroleModuleClp extends BaseModelImpl<BusinessroleModule>
    implements BusinessroleModule {
    private int _createdBy;
    private int _businessroleModuleSid;
    private int _businessroleMasterSid;
    private String _addFlag;
    private String _viewFlag;
    private int _modifiedBy;
    private Date _createdDate;
    private int _submodulePropertyId;
    private String _editFlag;
    private int _versionNo;
    private String _accessModule;
    private Date _modifiedDate;
    private BaseModel<?> _businessroleModuleRemoteModel;

    public BusinessroleModuleClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return BusinessroleModule.class;
    }

    @Override
    public String getModelClassName() {
        return BusinessroleModule.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _businessroleModuleSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setBusinessroleModuleSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _businessroleModuleSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("businessroleModuleSid", getBusinessroleModuleSid());
        attributes.put("businessroleMasterSid", getBusinessroleMasterSid());
        attributes.put("addFlag", getAddFlag());
        attributes.put("viewFlag", getViewFlag());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("submodulePropertyId", getSubmodulePropertyId());
        attributes.put("editFlag", getEditFlag());
        attributes.put("versionNo", getVersionNo());
        attributes.put("accessModule", getAccessModule());
        attributes.put("modifiedDate", getModifiedDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer businessroleModuleSid = (Integer) attributes.get(
                "businessroleModuleSid");

        if (businessroleModuleSid != null) {
            setBusinessroleModuleSid(businessroleModuleSid);
        }

        Integer businessroleMasterSid = (Integer) attributes.get(
                "businessroleMasterSid");

        if (businessroleMasterSid != null) {
            setBusinessroleMasterSid(businessroleMasterSid);
        }

        String addFlag = (String) attributes.get("addFlag");

        if (addFlag != null) {
            setAddFlag(addFlag);
        }

        String viewFlag = (String) attributes.get("viewFlag");

        if (viewFlag != null) {
            setViewFlag(viewFlag);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer submodulePropertyId = (Integer) attributes.get(
                "submodulePropertyId");

        if (submodulePropertyId != null) {
            setSubmodulePropertyId(submodulePropertyId);
        }

        String editFlag = (String) attributes.get("editFlag");

        if (editFlag != null) {
            setEditFlag(editFlag);
        }

        Integer versionNo = (Integer) attributes.get("versionNo");

        if (versionNo != null) {
            setVersionNo(versionNo);
        }

        String accessModule = (String) attributes.get("accessModule");

        if (accessModule != null) {
            setAccessModule(accessModule);
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

        if (_businessroleModuleRemoteModel != null) {
            try {
                Class<?> clazz = _businessroleModuleRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_businessroleModuleRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getBusinessroleModuleSid() {
        return _businessroleModuleSid;
    }

    @Override
    public void setBusinessroleModuleSid(int businessroleModuleSid) {
        _businessroleModuleSid = businessroleModuleSid;

        if (_businessroleModuleRemoteModel != null) {
            try {
                Class<?> clazz = _businessroleModuleRemoteModel.getClass();

                Method method = clazz.getMethod("setBusinessroleModuleSid",
                        int.class);

                method.invoke(_businessroleModuleRemoteModel,
                    businessroleModuleSid);
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

        if (_businessroleModuleRemoteModel != null) {
            try {
                Class<?> clazz = _businessroleModuleRemoteModel.getClass();

                Method method = clazz.getMethod("setBusinessroleMasterSid",
                        int.class);

                method.invoke(_businessroleModuleRemoteModel,
                    businessroleMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAddFlag() {
        return _addFlag;
    }

    @Override
    public void setAddFlag(String addFlag) {
        _addFlag = addFlag;

        if (_businessroleModuleRemoteModel != null) {
            try {
                Class<?> clazz = _businessroleModuleRemoteModel.getClass();

                Method method = clazz.getMethod("setAddFlag", String.class);

                method.invoke(_businessroleModuleRemoteModel, addFlag);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getViewFlag() {
        return _viewFlag;
    }

    @Override
    public void setViewFlag(String viewFlag) {
        _viewFlag = viewFlag;

        if (_businessroleModuleRemoteModel != null) {
            try {
                Class<?> clazz = _businessroleModuleRemoteModel.getClass();

                Method method = clazz.getMethod("setViewFlag", String.class);

                method.invoke(_businessroleModuleRemoteModel, viewFlag);
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

        if (_businessroleModuleRemoteModel != null) {
            try {
                Class<?> clazz = _businessroleModuleRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_businessroleModuleRemoteModel, modifiedBy);
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

        if (_businessroleModuleRemoteModel != null) {
            try {
                Class<?> clazz = _businessroleModuleRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_businessroleModuleRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getSubmodulePropertyId() {
        return _submodulePropertyId;
    }

    @Override
    public void setSubmodulePropertyId(int submodulePropertyId) {
        _submodulePropertyId = submodulePropertyId;

        if (_businessroleModuleRemoteModel != null) {
            try {
                Class<?> clazz = _businessroleModuleRemoteModel.getClass();

                Method method = clazz.getMethod("setSubmodulePropertyId",
                        int.class);

                method.invoke(_businessroleModuleRemoteModel,
                    submodulePropertyId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getEditFlag() {
        return _editFlag;
    }

    @Override
    public void setEditFlag(String editFlag) {
        _editFlag = editFlag;

        if (_businessroleModuleRemoteModel != null) {
            try {
                Class<?> clazz = _businessroleModuleRemoteModel.getClass();

                Method method = clazz.getMethod("setEditFlag", String.class);

                method.invoke(_businessroleModuleRemoteModel, editFlag);
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

        if (_businessroleModuleRemoteModel != null) {
            try {
                Class<?> clazz = _businessroleModuleRemoteModel.getClass();

                Method method = clazz.getMethod("setVersionNo", int.class);

                method.invoke(_businessroleModuleRemoteModel, versionNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAccessModule() {
        return _accessModule;
    }

    @Override
    public void setAccessModule(String accessModule) {
        _accessModule = accessModule;

        if (_businessroleModuleRemoteModel != null) {
            try {
                Class<?> clazz = _businessroleModuleRemoteModel.getClass();

                Method method = clazz.getMethod("setAccessModule", String.class);

                method.invoke(_businessroleModuleRemoteModel, accessModule);
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

        if (_businessroleModuleRemoteModel != null) {
            try {
                Class<?> clazz = _businessroleModuleRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_businessroleModuleRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getBusinessroleModuleRemoteModel() {
        return _businessroleModuleRemoteModel;
    }

    public void setBusinessroleModuleRemoteModel(
        BaseModel<?> businessroleModuleRemoteModel) {
        _businessroleModuleRemoteModel = businessroleModuleRemoteModel;
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

        Class<?> remoteModelClass = _businessroleModuleRemoteModel.getClass();

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

        Object returnValue = method.invoke(_businessroleModuleRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            BusinessroleModuleLocalServiceUtil.addBusinessroleModule(this);
        } else {
            BusinessroleModuleLocalServiceUtil.updateBusinessroleModule(this);
        }
    }

    @Override
    public BusinessroleModule toEscapedModel() {
        return (BusinessroleModule) ProxyUtil.newProxyInstance(BusinessroleModule.class.getClassLoader(),
            new Class[] { BusinessroleModule.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        BusinessroleModuleClp clone = new BusinessroleModuleClp();

        clone.setCreatedBy(getCreatedBy());
        clone.setBusinessroleModuleSid(getBusinessroleModuleSid());
        clone.setBusinessroleMasterSid(getBusinessroleMasterSid());
        clone.setAddFlag(getAddFlag());
        clone.setViewFlag(getViewFlag());
        clone.setModifiedBy(getModifiedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setSubmodulePropertyId(getSubmodulePropertyId());
        clone.setEditFlag(getEditFlag());
        clone.setVersionNo(getVersionNo());
        clone.setAccessModule(getAccessModule());
        clone.setModifiedDate(getModifiedDate());

        return clone;
    }

    @Override
    public int compareTo(BusinessroleModule businessroleModule) {
        int primaryKey = businessroleModule.getPrimaryKey();

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

        if (!(obj instanceof BusinessroleModuleClp)) {
            return false;
        }

        BusinessroleModuleClp businessroleModule = (BusinessroleModuleClp) obj;

        int primaryKey = businessroleModule.getPrimaryKey();

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

        sb.append("{createdBy=");
        sb.append(getCreatedBy());
        sb.append(", businessroleModuleSid=");
        sb.append(getBusinessroleModuleSid());
        sb.append(", businessroleMasterSid=");
        sb.append(getBusinessroleMasterSid());
        sb.append(", addFlag=");
        sb.append(getAddFlag());
        sb.append(", viewFlag=");
        sb.append(getViewFlag());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", submodulePropertyId=");
        sb.append(getSubmodulePropertyId());
        sb.append(", editFlag=");
        sb.append(getEditFlag());
        sb.append(", versionNo=");
        sb.append(getVersionNo());
        sb.append(", accessModule=");
        sb.append(getAccessModule());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(40);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.BusinessroleModule");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>businessroleModuleSid</column-name><column-value><![CDATA[");
        sb.append(getBusinessroleModuleSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>businessroleMasterSid</column-name><column-value><![CDATA[");
        sb.append(getBusinessroleMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>addFlag</column-name><column-value><![CDATA[");
        sb.append(getAddFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>viewFlag</column-name><column-value><![CDATA[");
        sb.append(getViewFlag());
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
            "<column><column-name>submodulePropertyId</column-name><column-value><![CDATA[");
        sb.append(getSubmodulePropertyId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>editFlag</column-name><column-value><![CDATA[");
        sb.append(getEditFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>versionNo</column-name><column-value><![CDATA[");
        sb.append(getVersionNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accessModule</column-name><column-value><![CDATA[");
        sb.append(getAccessModule());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
