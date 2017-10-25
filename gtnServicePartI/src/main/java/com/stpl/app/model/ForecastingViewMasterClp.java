package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.ForecastingViewMasterLocalServiceUtil;

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


public class ForecastingViewMasterClp extends BaseModelImpl<ForecastingViewMaster>
    implements ForecastingViewMaster {
    private Date _createdDate;
    private String _createdBy;
    private String _viewType;
    private int _viewId;
    private int _projectionId;
    private String _modifiedBy;
    private Date _modifiedDate;
    private String _viewName;
    private BaseModel<?> _forecastingViewMasterRemoteModel;

    public ForecastingViewMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ForecastingViewMaster.class;
    }

    @Override
    public String getModelClassName() {
        return ForecastingViewMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _viewId;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setViewId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _viewId;
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
        attributes.put("viewType", getViewType());
        attributes.put("viewId", getViewId());
        attributes.put("projectionId", getProjectionId());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("viewName", getViewName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String viewType = (String) attributes.get("viewType");

        if (viewType != null) {
            setViewType(viewType);
        }

        Integer viewId = (Integer) attributes.get("viewId");

        if (viewId != null) {
            setViewId(viewId);
        }

        Integer projectionId = (Integer) attributes.get("projectionId");

        if (projectionId != null) {
            setProjectionId(projectionId);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

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
    public Date getCreatedDate() {
        return _createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;

        if (_forecastingViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_forecastingViewMasterRemoteModel, createdDate);
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

        if (_forecastingViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_forecastingViewMasterRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getViewType() {
        return _viewType;
    }

    @Override
    public void setViewType(String viewType) {
        _viewType = viewType;

        if (_forecastingViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setViewType", String.class);

                method.invoke(_forecastingViewMasterRemoteModel, viewType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getViewId() {
        return _viewId;
    }

    @Override
    public void setViewId(int viewId) {
        _viewId = viewId;

        if (_forecastingViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setViewId", int.class);

                method.invoke(_forecastingViewMasterRemoteModel, viewId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getProjectionId() {
        return _projectionId;
    }

    @Override
    public void setProjectionId(int projectionId) {
        _projectionId = projectionId;

        if (_forecastingViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionId", int.class);

                method.invoke(_forecastingViewMasterRemoteModel, projectionId);
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

        if (_forecastingViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_forecastingViewMasterRemoteModel, modifiedBy);
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

        if (_forecastingViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_forecastingViewMasterRemoteModel, modifiedDate);
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

        if (_forecastingViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setViewName", String.class);

                method.invoke(_forecastingViewMasterRemoteModel, viewName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getForecastingViewMasterRemoteModel() {
        return _forecastingViewMasterRemoteModel;
    }

    public void setForecastingViewMasterRemoteModel(
        BaseModel<?> forecastingViewMasterRemoteModel) {
        _forecastingViewMasterRemoteModel = forecastingViewMasterRemoteModel;
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

        Class<?> remoteModelClass = _forecastingViewMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_forecastingViewMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ForecastingViewMasterLocalServiceUtil.addForecastingViewMaster(this);
        } else {
            ForecastingViewMasterLocalServiceUtil.updateForecastingViewMaster(this);
        }
    }

    @Override
    public ForecastingViewMaster toEscapedModel() {
        return (ForecastingViewMaster) ProxyUtil.newProxyInstance(ForecastingViewMaster.class.getClassLoader(),
            new Class[] { ForecastingViewMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ForecastingViewMasterClp clone = new ForecastingViewMasterClp();

        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setViewType(getViewType());
        clone.setViewId(getViewId());
        clone.setProjectionId(getProjectionId());
        clone.setModifiedBy(getModifiedBy());
        clone.setModifiedDate(getModifiedDate());
        clone.setViewName(getViewName());

        return clone;
    }

    @Override
    public int compareTo(ForecastingViewMaster forecastingViewMaster) {
        int primaryKey = forecastingViewMaster.getPrimaryKey();

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

        if (!(obj instanceof ForecastingViewMasterClp)) {
            return false;
        }

        ForecastingViewMasterClp forecastingViewMaster = (ForecastingViewMasterClp) obj;

        int primaryKey = forecastingViewMaster.getPrimaryKey();

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
        sb.append(", viewType=");
        sb.append(getViewType());
        sb.append(", viewId=");
        sb.append(getViewId());
        sb.append(", projectionId=");
        sb.append(getProjectionId());
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
        StringBundler sb = new StringBundler(28);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ForecastingViewMaster");
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
            "<column><column-name>viewType</column-name><column-value><![CDATA[");
        sb.append(getViewType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>viewId</column-name><column-value><![CDATA[");
        sb.append(getViewId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionId</column-name><column-value><![CDATA[");
        sb.append(getProjectionId());
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
