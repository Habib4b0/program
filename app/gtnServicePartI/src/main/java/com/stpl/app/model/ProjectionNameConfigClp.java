package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.ProjectionNameConfigLocalServiceUtil;

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


public class ProjectionNameConfigClp extends BaseModelImpl<ProjectionNameConfig>
    implements ProjectionNameConfig {
    private Date _createdDate;
    private int _createdBy;
    private String _businessProcessType;
    private int _versionNo;
    private int _modifiedBy;
    private int _projectionNameConfigSid;
    private Date _modifiedDate;
    private String _selectedAttributes;
    private BaseModel<?> _projectionNameConfigRemoteModel;

    public ProjectionNameConfigClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ProjectionNameConfig.class;
    }

    @Override
    public String getModelClassName() {
        return ProjectionNameConfig.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _projectionNameConfigSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setProjectionNameConfigSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _projectionNameConfigSid;
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
        attributes.put("businessProcessType", getBusinessProcessType());
        attributes.put("versionNo", getVersionNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("projectionNameConfigSid", getProjectionNameConfigSid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("selectedAttributes", getSelectedAttributes());

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

        String businessProcessType = (String) attributes.get(
                "businessProcessType");

        if (businessProcessType != null) {
            setBusinessProcessType(businessProcessType);
        }

        Integer versionNo = (Integer) attributes.get("versionNo");

        if (versionNo != null) {
            setVersionNo(versionNo);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Integer projectionNameConfigSid = (Integer) attributes.get(
                "projectionNameConfigSid");

        if (projectionNameConfigSid != null) {
            setProjectionNameConfigSid(projectionNameConfigSid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String selectedAttributes = (String) attributes.get(
                "selectedAttributes");

        if (selectedAttributes != null) {
            setSelectedAttributes(selectedAttributes);
        }
    }

    @Override
    public Date getCreatedDate() {
        return _createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;

        if (_projectionNameConfigRemoteModel != null) {
            try {
                Class<?> clazz = _projectionNameConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_projectionNameConfigRemoteModel, createdDate);
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

        if (_projectionNameConfigRemoteModel != null) {
            try {
                Class<?> clazz = _projectionNameConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_projectionNameConfigRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBusinessProcessType() {
        return _businessProcessType;
    }

    @Override
    public void setBusinessProcessType(String businessProcessType) {
        _businessProcessType = businessProcessType;

        if (_projectionNameConfigRemoteModel != null) {
            try {
                Class<?> clazz = _projectionNameConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setBusinessProcessType",
                        String.class);

                method.invoke(_projectionNameConfigRemoteModel,
                    businessProcessType);
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

        if (_projectionNameConfigRemoteModel != null) {
            try {
                Class<?> clazz = _projectionNameConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setVersionNo", int.class);

                method.invoke(_projectionNameConfigRemoteModel, versionNo);
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

        if (_projectionNameConfigRemoteModel != null) {
            try {
                Class<?> clazz = _projectionNameConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_projectionNameConfigRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getProjectionNameConfigSid() {
        return _projectionNameConfigSid;
    }

    @Override
    public void setProjectionNameConfigSid(int projectionNameConfigSid) {
        _projectionNameConfigSid = projectionNameConfigSid;

        if (_projectionNameConfigRemoteModel != null) {
            try {
                Class<?> clazz = _projectionNameConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionNameConfigSid",
                        int.class);

                method.invoke(_projectionNameConfigRemoteModel,
                    projectionNameConfigSid);
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

        if (_projectionNameConfigRemoteModel != null) {
            try {
                Class<?> clazz = _projectionNameConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_projectionNameConfigRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSelectedAttributes() {
        return _selectedAttributes;
    }

    @Override
    public void setSelectedAttributes(String selectedAttributes) {
        _selectedAttributes = selectedAttributes;

        if (_projectionNameConfigRemoteModel != null) {
            try {
                Class<?> clazz = _projectionNameConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setSelectedAttributes",
                        String.class);

                method.invoke(_projectionNameConfigRemoteModel,
                    selectedAttributes);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getProjectionNameConfigRemoteModel() {
        return _projectionNameConfigRemoteModel;
    }

    public void setProjectionNameConfigRemoteModel(
        BaseModel<?> projectionNameConfigRemoteModel) {
        _projectionNameConfigRemoteModel = projectionNameConfigRemoteModel;
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

        Class<?> remoteModelClass = _projectionNameConfigRemoteModel.getClass();

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

        Object returnValue = method.invoke(_projectionNameConfigRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ProjectionNameConfigLocalServiceUtil.addProjectionNameConfig(this);
        } else {
            ProjectionNameConfigLocalServiceUtil.updateProjectionNameConfig(this);
        }
    }

    @Override
    public ProjectionNameConfig toEscapedModel() {
        return (ProjectionNameConfig) ProxyUtil.newProxyInstance(ProjectionNameConfig.class.getClassLoader(),
            new Class[] { ProjectionNameConfig.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ProjectionNameConfigClp clone = new ProjectionNameConfigClp();

        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setBusinessProcessType(getBusinessProcessType());
        clone.setVersionNo(getVersionNo());
        clone.setModifiedBy(getModifiedBy());
        clone.setProjectionNameConfigSid(getProjectionNameConfigSid());
        clone.setModifiedDate(getModifiedDate());
        clone.setSelectedAttributes(getSelectedAttributes());

        return clone;
    }

    @Override
    public int compareTo(ProjectionNameConfig projectionNameConfig) {
        int primaryKey = projectionNameConfig.getPrimaryKey();

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

        if (!(obj instanceof ProjectionNameConfigClp)) {
            return false;
        }

        ProjectionNameConfigClp projectionNameConfig = (ProjectionNameConfigClp) obj;

        int primaryKey = projectionNameConfig.getPrimaryKey();

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

        sb.append("{createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", businessProcessType=");
        sb.append(getBusinessProcessType());
        sb.append(", versionNo=");
        sb.append(getVersionNo());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", projectionNameConfigSid=");
        sb.append(getProjectionNameConfigSid());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", selectedAttributes=");
        sb.append(getSelectedAttributes());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(28);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ProjectionNameConfig");
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
            "<column><column-name>businessProcessType</column-name><column-value><![CDATA[");
        sb.append(getBusinessProcessType());
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
            "<column><column-name>projectionNameConfigSid</column-name><column-value><![CDATA[");
        sb.append(getProjectionNameConfigSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>selectedAttributes</column-name><column-value><![CDATA[");
        sb.append(getSelectedAttributes());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
