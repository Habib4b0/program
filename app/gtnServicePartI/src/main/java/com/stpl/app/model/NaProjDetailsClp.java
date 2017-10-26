package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.NaProjDetailsLocalServiceUtil;

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


public class NaProjDetailsClp extends BaseModelImpl<NaProjDetails>
    implements NaProjDetails {
    private int _itemMasterSid;
    private int _naProjMasterSid;
    private int _naProjDetailsSid;
    private BaseModel<?> _naProjDetailsRemoteModel;

    public NaProjDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return NaProjDetails.class;
    }

    @Override
    public String getModelClassName() {
        return NaProjDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _naProjDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setNaProjDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _naProjDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("naProjMasterSid", getNaProjMasterSid());
        attributes.put("naProjDetailsSid", getNaProjDetailsSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Integer naProjMasterSid = (Integer) attributes.get("naProjMasterSid");

        if (naProjMasterSid != null) {
            setNaProjMasterSid(naProjMasterSid);
        }

        Integer naProjDetailsSid = (Integer) attributes.get("naProjDetailsSid");

        if (naProjDetailsSid != null) {
            setNaProjDetailsSid(naProjDetailsSid);
        }
    }

    @Override
    public int getItemMasterSid() {
        return _itemMasterSid;
    }

    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _itemMasterSid = itemMasterSid;

        if (_naProjDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _naProjDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_naProjDetailsRemoteModel, itemMasterSid);
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

        if (_naProjDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _naProjDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNaProjMasterSid", int.class);

                method.invoke(_naProjDetailsRemoteModel, naProjMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getNaProjDetailsSid() {
        return _naProjDetailsSid;
    }

    @Override
    public void setNaProjDetailsSid(int naProjDetailsSid) {
        _naProjDetailsSid = naProjDetailsSid;

        if (_naProjDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _naProjDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNaProjDetailsSid", int.class);

                method.invoke(_naProjDetailsRemoteModel, naProjDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getNaProjDetailsRemoteModel() {
        return _naProjDetailsRemoteModel;
    }

    public void setNaProjDetailsRemoteModel(
        BaseModel<?> naProjDetailsRemoteModel) {
        _naProjDetailsRemoteModel = naProjDetailsRemoteModel;
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

        Class<?> remoteModelClass = _naProjDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_naProjDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            NaProjDetailsLocalServiceUtil.addNaProjDetails(this);
        } else {
            NaProjDetailsLocalServiceUtil.updateNaProjDetails(this);
        }
    }

    @Override
    public NaProjDetails toEscapedModel() {
        return (NaProjDetails) ProxyUtil.newProxyInstance(NaProjDetails.class.getClassLoader(),
            new Class[] { NaProjDetails.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        NaProjDetailsClp clone = new NaProjDetailsClp();

        clone.setItemMasterSid(getItemMasterSid());
        clone.setNaProjMasterSid(getNaProjMasterSid());
        clone.setNaProjDetailsSid(getNaProjDetailsSid());

        return clone;
    }

    @Override
    public int compareTo(NaProjDetails naProjDetails) {
        int primaryKey = naProjDetails.getPrimaryKey();

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

        if (!(obj instanceof NaProjDetailsClp)) {
            return false;
        }

        NaProjDetailsClp naProjDetails = (NaProjDetailsClp) obj;

        int primaryKey = naProjDetails.getPrimaryKey();

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
        StringBundler sb = new StringBundler(7);

        sb.append("{itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", naProjMasterSid=");
        sb.append(getNaProjMasterSid());
        sb.append(", naProjDetailsSid=");
        sb.append(getNaProjDetailsSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(13);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.NaProjDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>naProjMasterSid</column-name><column-value><![CDATA[");
        sb.append(getNaProjMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>naProjDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getNaProjDetailsSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
