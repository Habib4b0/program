package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.MProjectionSelectionLocalServiceUtil;

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


public class MProjectionSelectionClp extends BaseModelImpl<MProjectionSelection>
    implements MProjectionSelection {
    private int _mProjectionSelectionSid;
    private int _projectionMasterSid;
    private String _fieldValues;
    private String _fieldName;
    private String _screenName;
    private BaseModel<?> _mProjectionSelectionRemoteModel;

    public MProjectionSelectionClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return MProjectionSelection.class;
    }

    @Override
    public String getModelClassName() {
        return MProjectionSelection.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _mProjectionSelectionSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setMProjectionSelectionSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _mProjectionSelectionSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("mProjectionSelectionSid", getMProjectionSelectionSid());
        attributes.put("projectionMasterSid", getProjectionMasterSid());
        attributes.put("fieldValues", getFieldValues());
        attributes.put("fieldName", getFieldName());
        attributes.put("screenName", getScreenName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer mProjectionSelectionSid = (Integer) attributes.get(
                "mProjectionSelectionSid");

        if (mProjectionSelectionSid != null) {
            setMProjectionSelectionSid(mProjectionSelectionSid);
        }

        Integer projectionMasterSid = (Integer) attributes.get(
                "projectionMasterSid");

        if (projectionMasterSid != null) {
            setProjectionMasterSid(projectionMasterSid);
        }

        String fieldValues = (String) attributes.get("fieldValues");

        if (fieldValues != null) {
            setFieldValues(fieldValues);
        }

        String fieldName = (String) attributes.get("fieldName");

        if (fieldName != null) {
            setFieldName(fieldName);
        }

        String screenName = (String) attributes.get("screenName");

        if (screenName != null) {
            setScreenName(screenName);
        }
    }

    @Override
    public int getMProjectionSelectionSid() {
        return _mProjectionSelectionSid;
    }

    @Override
    public void setMProjectionSelectionSid(int mProjectionSelectionSid) {
        _mProjectionSelectionSid = mProjectionSelectionSid;

        if (_mProjectionSelectionRemoteModel != null) {
            try {
                Class<?> clazz = _mProjectionSelectionRemoteModel.getClass();

                Method method = clazz.getMethod("setMProjectionSelectionSid",
                        int.class);

                method.invoke(_mProjectionSelectionRemoteModel,
                    mProjectionSelectionSid);
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

        if (_mProjectionSelectionRemoteModel != null) {
            try {
                Class<?> clazz = _mProjectionSelectionRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionMasterSid",
                        int.class);

                method.invoke(_mProjectionSelectionRemoteModel,
                    projectionMasterSid);
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

        if (_mProjectionSelectionRemoteModel != null) {
            try {
                Class<?> clazz = _mProjectionSelectionRemoteModel.getClass();

                Method method = clazz.getMethod("setFieldValues", String.class);

                method.invoke(_mProjectionSelectionRemoteModel, fieldValues);
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

        if (_mProjectionSelectionRemoteModel != null) {
            try {
                Class<?> clazz = _mProjectionSelectionRemoteModel.getClass();

                Method method = clazz.getMethod("setFieldName", String.class);

                method.invoke(_mProjectionSelectionRemoteModel, fieldName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getScreenName() {
        return _screenName;
    }

    @Override
    public void setScreenName(String screenName) {
        _screenName = screenName;

        if (_mProjectionSelectionRemoteModel != null) {
            try {
                Class<?> clazz = _mProjectionSelectionRemoteModel.getClass();

                Method method = clazz.getMethod("setScreenName", String.class);

                method.invoke(_mProjectionSelectionRemoteModel, screenName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getMProjectionSelectionRemoteModel() {
        return _mProjectionSelectionRemoteModel;
    }

    public void setMProjectionSelectionRemoteModel(
        BaseModel<?> mProjectionSelectionRemoteModel) {
        _mProjectionSelectionRemoteModel = mProjectionSelectionRemoteModel;
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

        Class<?> remoteModelClass = _mProjectionSelectionRemoteModel.getClass();

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

        Object returnValue = method.invoke(_mProjectionSelectionRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            MProjectionSelectionLocalServiceUtil.addMProjectionSelection(this);
        } else {
            MProjectionSelectionLocalServiceUtil.updateMProjectionSelection(this);
        }
    }

    @Override
    public MProjectionSelection toEscapedModel() {
        return (MProjectionSelection) ProxyUtil.newProxyInstance(MProjectionSelection.class.getClassLoader(),
            new Class[] { MProjectionSelection.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        MProjectionSelectionClp clone = new MProjectionSelectionClp();

        clone.setMProjectionSelectionSid(getMProjectionSelectionSid());
        clone.setProjectionMasterSid(getProjectionMasterSid());
        clone.setFieldValues(getFieldValues());
        clone.setFieldName(getFieldName());
        clone.setScreenName(getScreenName());

        return clone;
    }

    @Override
    public int compareTo(MProjectionSelection mProjectionSelection) {
        int primaryKey = mProjectionSelection.getPrimaryKey();

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

        if (!(obj instanceof MProjectionSelectionClp)) {
            return false;
        }

        MProjectionSelectionClp mProjectionSelection = (MProjectionSelectionClp) obj;

        int primaryKey = mProjectionSelection.getPrimaryKey();

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

        sb.append("{mProjectionSelectionSid=");
        sb.append(getMProjectionSelectionSid());
        sb.append(", projectionMasterSid=");
        sb.append(getProjectionMasterSid());
        sb.append(", fieldValues=");
        sb.append(getFieldValues());
        sb.append(", fieldName=");
        sb.append(getFieldName());
        sb.append(", screenName=");
        sb.append(getScreenName());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(19);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.MProjectionSelection");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>mProjectionSelectionSid</column-name><column-value><![CDATA[");
        sb.append(getMProjectionSelectionSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionMasterSid</column-name><column-value><![CDATA[");
        sb.append(getProjectionMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fieldValues</column-name><column-value><![CDATA[");
        sb.append(getFieldValues());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fieldName</column-name><column-value><![CDATA[");
        sb.append(getFieldName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>screenName</column-name><column-value><![CDATA[");
        sb.append(getScreenName());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
