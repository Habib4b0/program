package com.stpl.app.model;

import com.stpl.app.service.AdditionalNotesLocalServiceUtil;
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


public class AdditionalNotesClp extends BaseModelImpl<AdditionalNotes>
    implements AdditionalNotes {
    private Date _createdDate;
    private String _createdBy;
    private String _forecastType;
    private int _additionalNotesId;
    private int _projectionId;
    private String _notes;
    private BaseModel<?> _additionalNotesRemoteModel;

    public AdditionalNotesClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return AdditionalNotes.class;
    }

    @Override
    public String getModelClassName() {
        return AdditionalNotes.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _additionalNotesId;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setAdditionalNotesId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _additionalNotesId;
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
        attributes.put("forecastType", getForecastType());
        attributes.put("additionalNotesId", getAdditionalNotesId());
        attributes.put("projectionId", getProjectionId());
        attributes.put("notes", getNotes());

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

        String forecastType = (String) attributes.get("forecastType");

        if (forecastType != null) {
            setForecastType(forecastType);
        }

        Integer additionalNotesId = (Integer) attributes.get(
                "additionalNotesId");

        if (additionalNotesId != null) {
            setAdditionalNotesId(additionalNotesId);
        }

        Integer projectionId = (Integer) attributes.get("projectionId");

        if (projectionId != null) {
            setProjectionId(projectionId);
        }

        String notes = (String) attributes.get("notes");

        if (notes != null) {
            setNotes(notes);
        }
    }

    @Override
    public Date getCreatedDate() {
        return _createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;

        if (_additionalNotesRemoteModel != null) {
            try {
                Class<?> clazz = _additionalNotesRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_additionalNotesRemoteModel, createdDate);
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

        if (_additionalNotesRemoteModel != null) {
            try {
                Class<?> clazz = _additionalNotesRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_additionalNotesRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getForecastType() {
        return _forecastType;
    }

    @Override
    public void setForecastType(String forecastType) {
        _forecastType = forecastType;

        if (_additionalNotesRemoteModel != null) {
            try {
                Class<?> clazz = _additionalNotesRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastType", String.class);

                method.invoke(_additionalNotesRemoteModel, forecastType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getAdditionalNotesId() {
        return _additionalNotesId;
    }

    @Override
    public void setAdditionalNotesId(int additionalNotesId) {
        _additionalNotesId = additionalNotesId;

        if (_additionalNotesRemoteModel != null) {
            try {
                Class<?> clazz = _additionalNotesRemoteModel.getClass();

                Method method = clazz.getMethod("setAdditionalNotesId",
                        int.class);

                method.invoke(_additionalNotesRemoteModel, additionalNotesId);
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

        if (_additionalNotesRemoteModel != null) {
            try {
                Class<?> clazz = _additionalNotesRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionId", int.class);

                method.invoke(_additionalNotesRemoteModel, projectionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNotes() {
        return _notes;
    }

    @Override
    public void setNotes(String notes) {
        _notes = notes;

        if (_additionalNotesRemoteModel != null) {
            try {
                Class<?> clazz = _additionalNotesRemoteModel.getClass();

                Method method = clazz.getMethod("setNotes", String.class);

                method.invoke(_additionalNotesRemoteModel, notes);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getAdditionalNotesRemoteModel() {
        return _additionalNotesRemoteModel;
    }

    public void setAdditionalNotesRemoteModel(
        BaseModel<?> additionalNotesRemoteModel) {
        _additionalNotesRemoteModel = additionalNotesRemoteModel;
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

        Class<?> remoteModelClass = _additionalNotesRemoteModel.getClass();

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

        Object returnValue = method.invoke(_additionalNotesRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            AdditionalNotesLocalServiceUtil.addAdditionalNotes(this);
        } else {
            AdditionalNotesLocalServiceUtil.updateAdditionalNotes(this);
        }
    }

    @Override
    public AdditionalNotes toEscapedModel() {
        return (AdditionalNotes) ProxyUtil.newProxyInstance(AdditionalNotes.class.getClassLoader(),
            new Class[] { AdditionalNotes.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        AdditionalNotesClp clone = new AdditionalNotesClp();

        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setForecastType(getForecastType());
        clone.setAdditionalNotesId(getAdditionalNotesId());
        clone.setProjectionId(getProjectionId());
        clone.setNotes(getNotes());

        return clone;
    }

    @Override
    public int compareTo(AdditionalNotes additionalNotes) {
        int primaryKey = additionalNotes.getPrimaryKey();

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

        if (!(obj instanceof AdditionalNotesClp)) {
            return false;
        }

        AdditionalNotesClp additionalNotes = (AdditionalNotesClp) obj;

        int primaryKey = additionalNotes.getPrimaryKey();

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
        StringBundler sb = new StringBundler(13);

        sb.append("{createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", forecastType=");
        sb.append(getForecastType());
        sb.append(", additionalNotesId=");
        sb.append(getAdditionalNotesId());
        sb.append(", projectionId=");
        sb.append(getProjectionId());
        sb.append(", notes=");
        sb.append(getNotes());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.AdditionalNotes");
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
            "<column><column-name>forecastType</column-name><column-value><![CDATA[");
        sb.append(getForecastType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>additionalNotesId</column-name><column-value><![CDATA[");
        sb.append(getAdditionalNotesId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionId</column-name><column-value><![CDATA[");
        sb.append(getProjectionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>notes</column-name><column-value><![CDATA[");
        sb.append(getNotes());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
