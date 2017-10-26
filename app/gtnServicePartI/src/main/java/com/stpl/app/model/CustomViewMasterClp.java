package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.CustomViewMasterLocalServiceUtil;

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


public class CustomViewMasterClp extends BaseModelImpl<CustomViewMaster>
    implements CustomViewMaster {
    private int _customViewMasterSid;
    private Date _createdDate;
    private int _createdBy;
    private int _projectionMasterSid;
    private int _modifiedBy;
    private Date _modifiedDate;
    private String _viewName;
    private BaseModel<?> _customViewMasterRemoteModel;

    public CustomViewMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return CustomViewMaster.class;
    }

    @Override
    public String getModelClassName() {
        return CustomViewMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _customViewMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setCustomViewMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _customViewMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("customViewMasterSid", getCustomViewMasterSid());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("projectionMasterSid", getProjectionMasterSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("viewName", getViewName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer customViewMasterSid = (Integer) attributes.get(
                "customViewMasterSid");

        if (customViewMasterSid != null) {
            setCustomViewMasterSid(customViewMasterSid);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer projectionMasterSid = (Integer) attributes.get(
                "projectionMasterSid");

        if (projectionMasterSid != null) {
            setProjectionMasterSid(projectionMasterSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String viewName = (String) attributes.get("viewName");

        if (viewName != null) {
            setViewName(viewName);
        }
    }

    @Override
    public int getCustomViewMasterSid() {
        return _customViewMasterSid;
    }

    @Override
    public void setCustomViewMasterSid(int customViewMasterSid) {
        _customViewMasterSid = customViewMasterSid;

        if (_customViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _customViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCustomViewMasterSid",
                        int.class);

                method.invoke(_customViewMasterRemoteModel, customViewMasterSid);
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

        if (_customViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _customViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_customViewMasterRemoteModel, createdDate);
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

        if (_customViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _customViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_customViewMasterRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getProjectionMasterSid() {
        return _projectionMasterSid;
    }

    @Override
    public void setProjectionMasterSid(int projectionMasterSid) {
        _projectionMasterSid = projectionMasterSid;

        if (_customViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _customViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionMasterSid",
                        int.class);

                method.invoke(_customViewMasterRemoteModel, projectionMasterSid);
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

        if (_customViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _customViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_customViewMasterRemoteModel, modifiedBy);
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

        if (_customViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _customViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_customViewMasterRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getViewName() {
        return _viewName;
    }

    @Override
    public void setViewName(String viewName) {
        _viewName = viewName;

        if (_customViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _customViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setViewName", String.class);

                method.invoke(_customViewMasterRemoteModel, viewName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getCustomViewMasterRemoteModel() {
        return _customViewMasterRemoteModel;
    }

    public void setCustomViewMasterRemoteModel(
        BaseModel<?> customViewMasterRemoteModel) {
        _customViewMasterRemoteModel = customViewMasterRemoteModel;
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

        Class<?> remoteModelClass = _customViewMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_customViewMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            CustomViewMasterLocalServiceUtil.addCustomViewMaster(this);
        } else {
            CustomViewMasterLocalServiceUtil.updateCustomViewMaster(this);
        }
    }

    @Override
    public CustomViewMaster toEscapedModel() {
        return (CustomViewMaster) ProxyUtil.newProxyInstance(CustomViewMaster.class.getClassLoader(),
            new Class[] { CustomViewMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CustomViewMasterClp clone = new CustomViewMasterClp();

        clone.setCustomViewMasterSid(getCustomViewMasterSid());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setProjectionMasterSid(getProjectionMasterSid());
        clone.setModifiedBy(getModifiedBy());
        clone.setModifiedDate(getModifiedDate());
        clone.setViewName(getViewName());

        return clone;
    }

    @Override
    public int compareTo(CustomViewMaster customViewMaster) {
        int primaryKey = customViewMaster.getPrimaryKey();

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

        if (!(obj instanceof CustomViewMasterClp)) {
            return false;
        }

        CustomViewMasterClp customViewMaster = (CustomViewMasterClp) obj;

        int primaryKey = customViewMaster.getPrimaryKey();

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
        StringBundler sb = new StringBundler(15);

        sb.append("{customViewMasterSid=");
        sb.append(getCustomViewMasterSid());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", projectionMasterSid=");
        sb.append(getProjectionMasterSid());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", viewName=");
        sb.append(getViewName());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(25);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.CustomViewMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>customViewMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCustomViewMasterSid());
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
            "<column><column-name>projectionMasterSid</column-name><column-value><![CDATA[");
        sb.append(getProjectionMasterSid());
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
            "<column><column-name>viewName</column-name><column-value><![CDATA[");
        sb.append(getViewName());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
