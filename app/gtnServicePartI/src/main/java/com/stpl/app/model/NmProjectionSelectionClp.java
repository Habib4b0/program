package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.NmProjectionSelectionLocalServiceUtil;

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


public class NmProjectionSelectionClp extends BaseModelImpl<NmProjectionSelection>
    implements NmProjectionSelection {
    private String _screenName;
    private int _nmProjectionSelectionSid;
    private String _fieldName;
    private String _fieldValues;
    private int _projectionMasterSid;
    private BaseModel<?> _nmProjectionSelectionRemoteModel;

    public NmProjectionSelectionClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return NmProjectionSelection.class;
    }

    @Override
    public String getModelClassName() {
        return NmProjectionSelection.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _nmProjectionSelectionSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setNmProjectionSelectionSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _nmProjectionSelectionSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("screenName", getScreenName());
        attributes.put("nmProjectionSelectionSid", getNmProjectionSelectionSid());
        attributes.put("fieldName", getFieldName());
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

        Integer nmProjectionSelectionSid = (Integer) attributes.get(
                "nmProjectionSelectionSid");

        if (nmProjectionSelectionSid != null) {
            setNmProjectionSelectionSid(nmProjectionSelectionSid);
        }

        String fieldName = (String) attributes.get("fieldName");

        if (fieldName != null) {
            setFieldName(fieldName);
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

        if (_nmProjectionSelectionRemoteModel != null) {
            try {
                Class<?> clazz = _nmProjectionSelectionRemoteModel.getClass();

                Method method = clazz.getMethod("setScreenName", String.class);

                method.invoke(_nmProjectionSelectionRemoteModel, screenName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getNmProjectionSelectionSid() {
        return _nmProjectionSelectionSid;
    }

    @Override
    public void setNmProjectionSelectionSid(int nmProjectionSelectionSid) {
        _nmProjectionSelectionSid = nmProjectionSelectionSid;

        if (_nmProjectionSelectionRemoteModel != null) {
            try {
                Class<?> clazz = _nmProjectionSelectionRemoteModel.getClass();

                Method method = clazz.getMethod("setNmProjectionSelectionSid",
                        int.class);

                method.invoke(_nmProjectionSelectionRemoteModel,
                    nmProjectionSelectionSid);
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

        if (_nmProjectionSelectionRemoteModel != null) {
            try {
                Class<?> clazz = _nmProjectionSelectionRemoteModel.getClass();

                Method method = clazz.getMethod("setFieldName", String.class);

                method.invoke(_nmProjectionSelectionRemoteModel, fieldName);
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

        if (_nmProjectionSelectionRemoteModel != null) {
            try {
                Class<?> clazz = _nmProjectionSelectionRemoteModel.getClass();

                Method method = clazz.getMethod("setFieldValues", String.class);

                method.invoke(_nmProjectionSelectionRemoteModel, fieldValues);
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

        if (_nmProjectionSelectionRemoteModel != null) {
            try {
                Class<?> clazz = _nmProjectionSelectionRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionMasterSid",
                        int.class);

                method.invoke(_nmProjectionSelectionRemoteModel,
                    projectionMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getNmProjectionSelectionRemoteModel() {
        return _nmProjectionSelectionRemoteModel;
    }

    public void setNmProjectionSelectionRemoteModel(
        BaseModel<?> nmProjectionSelectionRemoteModel) {
        _nmProjectionSelectionRemoteModel = nmProjectionSelectionRemoteModel;
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

        Class<?> remoteModelClass = _nmProjectionSelectionRemoteModel.getClass();

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

        Object returnValue = method.invoke(_nmProjectionSelectionRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            NmProjectionSelectionLocalServiceUtil.addNmProjectionSelection(this);
        } else {
            NmProjectionSelectionLocalServiceUtil.updateNmProjectionSelection(this);
        }
    }

    @Override
    public NmProjectionSelection toEscapedModel() {
        return (NmProjectionSelection) ProxyUtil.newProxyInstance(NmProjectionSelection.class.getClassLoader(),
            new Class[] { NmProjectionSelection.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        NmProjectionSelectionClp clone = new NmProjectionSelectionClp();

        clone.setScreenName(getScreenName());
        clone.setNmProjectionSelectionSid(getNmProjectionSelectionSid());
        clone.setFieldName(getFieldName());
        clone.setFieldValues(getFieldValues());
        clone.setProjectionMasterSid(getProjectionMasterSid());

        return clone;
    }

    @Override
    public int compareTo(NmProjectionSelection nmProjectionSelection) {
        int primaryKey = nmProjectionSelection.getPrimaryKey();

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

        if (!(obj instanceof NmProjectionSelectionClp)) {
            return false;
        }

        NmProjectionSelectionClp nmProjectionSelection = (NmProjectionSelectionClp) obj;

        int primaryKey = nmProjectionSelection.getPrimaryKey();

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
        sb.append(", nmProjectionSelectionSid=");
        sb.append(getNmProjectionSelectionSid());
        sb.append(", fieldName=");
        sb.append(getFieldName());
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
        sb.append("com.stpl.app.model.NmProjectionSelection");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>screenName</column-name><column-value><![CDATA[");
        sb.append(getScreenName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>nmProjectionSelectionSid</column-name><column-value><![CDATA[");
        sb.append(getNmProjectionSelectionSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fieldName</column-name><column-value><![CDATA[");
        sb.append(getFieldName());
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
