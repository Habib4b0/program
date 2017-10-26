package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.NaProjectionSelectionLocalServiceUtil;

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


public class NaProjectionSelectionClp extends BaseModelImpl<NaProjectionSelection>
    implements NaProjectionSelection {
    private String _screenName;
    private String _fieldName;
    private String _fieldValues;
    private int _naProjectionSelectionSid;
    private int _naProjMasterSid;
    private BaseModel<?> _naProjectionSelectionRemoteModel;

    public NaProjectionSelectionClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return NaProjectionSelection.class;
    }

    @Override
    public String getModelClassName() {
        return NaProjectionSelection.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _naProjectionSelectionSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setNaProjectionSelectionSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _naProjectionSelectionSid;
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
        attributes.put("fieldValues", getFieldValues());
        attributes.put("naProjectionSelectionSid", getNaProjectionSelectionSid());
        attributes.put("naProjMasterSid", getNaProjMasterSid());

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

        String fieldValues = (String) attributes.get("fieldValues");

        if (fieldValues != null) {
            setFieldValues(fieldValues);
        }

        Integer naProjectionSelectionSid = (Integer) attributes.get(
                "naProjectionSelectionSid");

        if (naProjectionSelectionSid != null) {
            setNaProjectionSelectionSid(naProjectionSelectionSid);
        }

        Integer naProjMasterSid = (Integer) attributes.get("naProjMasterSid");

        if (naProjMasterSid != null) {
            setNaProjMasterSid(naProjMasterSid);
        }
    }

    @Override
    public String getScreenName() {
        return _screenName;
    }

    @Override
    public void setScreenName(String screenName) {
        _screenName = screenName;

        if (_naProjectionSelectionRemoteModel != null) {
            try {
                Class<?> clazz = _naProjectionSelectionRemoteModel.getClass();

                Method method = clazz.getMethod("setScreenName", String.class);

                method.invoke(_naProjectionSelectionRemoteModel, screenName);
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

        if (_naProjectionSelectionRemoteModel != null) {
            try {
                Class<?> clazz = _naProjectionSelectionRemoteModel.getClass();

                Method method = clazz.getMethod("setFieldName", String.class);

                method.invoke(_naProjectionSelectionRemoteModel, fieldName);
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

        if (_naProjectionSelectionRemoteModel != null) {
            try {
                Class<?> clazz = _naProjectionSelectionRemoteModel.getClass();

                Method method = clazz.getMethod("setFieldValues", String.class);

                method.invoke(_naProjectionSelectionRemoteModel, fieldValues);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getNaProjectionSelectionSid() {
        return _naProjectionSelectionSid;
    }

    @Override
    public void setNaProjectionSelectionSid(int naProjectionSelectionSid) {
        _naProjectionSelectionSid = naProjectionSelectionSid;

        if (_naProjectionSelectionRemoteModel != null) {
            try {
                Class<?> clazz = _naProjectionSelectionRemoteModel.getClass();

                Method method = clazz.getMethod("setNaProjectionSelectionSid",
                        int.class);

                method.invoke(_naProjectionSelectionRemoteModel,
                    naProjectionSelectionSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getNaProjMasterSid() {
        return _naProjMasterSid;
    }

    @Override
    public void setNaProjMasterSid(int naProjMasterSid) {
        _naProjMasterSid = naProjMasterSid;

        if (_naProjectionSelectionRemoteModel != null) {
            try {
                Class<?> clazz = _naProjectionSelectionRemoteModel.getClass();

                Method method = clazz.getMethod("setNaProjMasterSid", int.class);

                method.invoke(_naProjectionSelectionRemoteModel, naProjMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getNaProjectionSelectionRemoteModel() {
        return _naProjectionSelectionRemoteModel;
    }

    public void setNaProjectionSelectionRemoteModel(
        BaseModel<?> naProjectionSelectionRemoteModel) {
        _naProjectionSelectionRemoteModel = naProjectionSelectionRemoteModel;
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

        Class<?> remoteModelClass = _naProjectionSelectionRemoteModel.getClass();

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

        Object returnValue = method.invoke(_naProjectionSelectionRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            NaProjectionSelectionLocalServiceUtil.addNaProjectionSelection(this);
        } else {
            NaProjectionSelectionLocalServiceUtil.updateNaProjectionSelection(this);
        }
    }

    @Override
    public NaProjectionSelection toEscapedModel() {
        return (NaProjectionSelection) ProxyUtil.newProxyInstance(NaProjectionSelection.class.getClassLoader(),
            new Class[] { NaProjectionSelection.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        NaProjectionSelectionClp clone = new NaProjectionSelectionClp();

        clone.setScreenName(getScreenName());
        clone.setFieldName(getFieldName());
        clone.setFieldValues(getFieldValues());
        clone.setNaProjectionSelectionSid(getNaProjectionSelectionSid());
        clone.setNaProjMasterSid(getNaProjMasterSid());

        return clone;
    }

    @Override
    public int compareTo(NaProjectionSelection naProjectionSelection) {
        int primaryKey = naProjectionSelection.getPrimaryKey();

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

        if (!(obj instanceof NaProjectionSelectionClp)) {
            return false;
        }

        NaProjectionSelectionClp naProjectionSelection = (NaProjectionSelectionClp) obj;

        int primaryKey = naProjectionSelection.getPrimaryKey();

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
        sb.append(", fieldValues=");
        sb.append(getFieldValues());
        sb.append(", naProjectionSelectionSid=");
        sb.append(getNaProjectionSelectionSid());
        sb.append(", naProjMasterSid=");
        sb.append(getNaProjMasterSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(19);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.NaProjectionSelection");
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
            "<column><column-name>fieldValues</column-name><column-value><![CDATA[");
        sb.append(getFieldValues());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>naProjectionSelectionSid</column-name><column-value><![CDATA[");
        sb.append(getNaProjectionSelectionSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>naProjMasterSid</column-name><column-value><![CDATA[");
        sb.append(getNaProjMasterSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
