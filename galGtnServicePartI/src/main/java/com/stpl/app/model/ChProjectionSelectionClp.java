package com.stpl.app.model;

import com.stpl.app.service.ChProjectionSelectionLocalServiceUtil;
import com.stpl.app.service.ClpSerializer;

import com.stpl.portal.kernel.bean.AutoEscapeBeanHandler;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.ProxyUtil;
import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;


public class ChProjectionSelectionClp extends BaseModelImpl<ChProjectionSelection>
    implements ChProjectionSelection {
    private String _screenName;
    private String _fieldName;
    private int _chProjectionSelectionSid;
    private String _fieldValues;
    private int _projectionMasterSid;
    private BaseModel<?> _chProjectionSelectionRemoteModel;

    public ChProjectionSelectionClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ChProjectionSelection.class;
    }

    @Override
    public String getModelClassName() {
        return ChProjectionSelection.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _chProjectionSelectionSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setChProjectionSelectionSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _chProjectionSelectionSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("screenName", getScreenName());
        attributes.put("fieldName", getFieldName());
        attributes.put("chProjectionSelectionSid", getChProjectionSelectionSid());
        attributes.put("fieldValues", getFieldValues());
        attributes.put("projectionMasterSid", getProjectionMasterSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String screenName = (String) attributes.get("screenName");

        if (screenName != null) {
            setScreenName(screenName);
        }

        String fieldName = (String) attributes.get("fieldName");

        if (fieldName != null) {
            setFieldName(fieldName);
        }

        Integer chProjectionSelectionSid = (Integer) attributes.get(
                "chProjectionSelectionSid");

        if (chProjectionSelectionSid != null) {
            setChProjectionSelectionSid(chProjectionSelectionSid);
        }

        String fieldValues = (String) attributes.get("fieldValues");

        if (fieldValues != null) {
            setFieldValues(fieldValues);
        }

        Integer projectionMasterSid = (Integer) attributes.get(
                "projectionMasterSid");

        if (projectionMasterSid != null) {
            setProjectionMasterSid(projectionMasterSid);
        }
    }

    @Override
    public String getScreenName() {
        return _screenName;
    }

    @Override
    public void setScreenName(String screenName) {
        _screenName = screenName;

        if (_chProjectionSelectionRemoteModel != null) {
            try {
                Class<?> clazz = _chProjectionSelectionRemoteModel.getClass();

                Method method = clazz.getMethod("setScreenName", String.class);

                method.invoke(_chProjectionSelectionRemoteModel, screenName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFieldName() {
        return _fieldName;
    }

    @Override
    public void setFieldName(String fieldName) {
        _fieldName = fieldName;

        if (_chProjectionSelectionRemoteModel != null) {
            try {
                Class<?> clazz = _chProjectionSelectionRemoteModel.getClass();

                Method method = clazz.getMethod("setFieldName", String.class);

                method.invoke(_chProjectionSelectionRemoteModel, fieldName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getChProjectionSelectionSid() {
        return _chProjectionSelectionSid;
    }

    @Override
    public void setChProjectionSelectionSid(int chProjectionSelectionSid) {
        _chProjectionSelectionSid = chProjectionSelectionSid;

        if (_chProjectionSelectionRemoteModel != null) {
            try {
                Class<?> clazz = _chProjectionSelectionRemoteModel.getClass();

                Method method = clazz.getMethod("setChProjectionSelectionSid",
                        int.class);

                method.invoke(_chProjectionSelectionRemoteModel,
                    chProjectionSelectionSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFieldValues() {
        return _fieldValues;
    }

    @Override
    public void setFieldValues(String fieldValues) {
        _fieldValues = fieldValues;

        if (_chProjectionSelectionRemoteModel != null) {
            try {
                Class<?> clazz = _chProjectionSelectionRemoteModel.getClass();

                Method method = clazz.getMethod("setFieldValues", String.class);

                method.invoke(_chProjectionSelectionRemoteModel, fieldValues);
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

        if (_chProjectionSelectionRemoteModel != null) {
            try {
                Class<?> clazz = _chProjectionSelectionRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionMasterSid",
                        int.class);

                method.invoke(_chProjectionSelectionRemoteModel,
                    projectionMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getChProjectionSelectionRemoteModel() {
        return _chProjectionSelectionRemoteModel;
    }

    public void setChProjectionSelectionRemoteModel(
        BaseModel<?> chProjectionSelectionRemoteModel) {
        _chProjectionSelectionRemoteModel = chProjectionSelectionRemoteModel;
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

        Class<?> remoteModelClass = _chProjectionSelectionRemoteModel.getClass();

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

        Object returnValue = method.invoke(_chProjectionSelectionRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ChProjectionSelectionLocalServiceUtil.addChProjectionSelection(this);
        } else {
            ChProjectionSelectionLocalServiceUtil.updateChProjectionSelection(this);
        }
    }

    @Override
    public ChProjectionSelection toEscapedModel() {
        return (ChProjectionSelection) ProxyUtil.newProxyInstance(ChProjectionSelection.class.getClassLoader(),
            new Class[] { ChProjectionSelection.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ChProjectionSelectionClp clone = new ChProjectionSelectionClp();

        clone.setScreenName(getScreenName());
        clone.setFieldName(getFieldName());
        clone.setChProjectionSelectionSid(getChProjectionSelectionSid());
        clone.setFieldValues(getFieldValues());
        clone.setProjectionMasterSid(getProjectionMasterSid());

        return clone;
    }

    @Override
    public int compareTo(ChProjectionSelection chProjectionSelection) {
        int primaryKey = chProjectionSelection.getPrimaryKey();

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

        if (!(obj instanceof ChProjectionSelectionClp)) {
            return false;
        }

        ChProjectionSelectionClp chProjectionSelection = (ChProjectionSelectionClp) obj;

        int primaryKey = chProjectionSelection.getPrimaryKey();

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
        StringBundler sb = new StringBundler(11);

        sb.append("{screenName=");
        sb.append(getScreenName());
        sb.append(", fieldName=");
        sb.append(getFieldName());
        sb.append(", chProjectionSelectionSid=");
        sb.append(getChProjectionSelectionSid());
        sb.append(", fieldValues=");
        sb.append(getFieldValues());
        sb.append(", projectionMasterSid=");
        sb.append(getProjectionMasterSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(19);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ChProjectionSelection");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>screenName</column-name><column-value><![CDATA[");
        sb.append(getScreenName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fieldName</column-name><column-value><![CDATA[");
        sb.append(getFieldName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>chProjectionSelectionSid</column-name><column-value><![CDATA[");
        sb.append(getChProjectionSelectionSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fieldValues</column-name><column-value><![CDATA[");
        sb.append(getFieldValues());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionMasterSid</column-name><column-value><![CDATA[");
        sb.append(getProjectionMasterSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
