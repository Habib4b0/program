package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.UdcsLocalServiceUtil;

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


public class UdcsClp extends BaseModelImpl<Udcs> implements Udcs {
    private int _udc1;
    private int _udc2;
    private String _masterType;
    private int _udc3;
    private int _udc12;
    private int _udc11;
    private int _udcsSid;
    private int _masterSid;
    private int _udc10;
    private int _udc9;
    private int _udc8;
    private int _udc7;
    private int _udc6;
    private int _udc5;
    private int _udc4;
    private BaseModel<?> _udcsRemoteModel;

    public UdcsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return Udcs.class;
    }

    @Override
    public String getModelClassName() {
        return Udcs.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _udcsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setUdcsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _udcsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("udc1", getUdc1());
        attributes.put("udc2", getUdc2());
        attributes.put("masterType", getMasterType());
        attributes.put("udc3", getUdc3());
        attributes.put("udc12", getUdc12());
        attributes.put("udc11", getUdc11());
        attributes.put("udcsSid", getUdcsSid());
        attributes.put("masterSid", getMasterSid());
        attributes.put("udc10", getUdc10());
        attributes.put("udc9", getUdc9());
        attributes.put("udc8", getUdc8());
        attributes.put("udc7", getUdc7());
        attributes.put("udc6", getUdc6());
        attributes.put("udc5", getUdc5());
        attributes.put("udc4", getUdc4());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer udc1 = (Integer) attributes.get("udc1");

        if (udc1 != null) {
            setUdc1(udc1);
        }

        Integer udc2 = (Integer) attributes.get("udc2");

        if (udc2 != null) {
            setUdc2(udc2);
        }

        String masterType = (String) attributes.get("masterType");

        if (masterType != null) {
            setMasterType(masterType);
        }

        Integer udc3 = (Integer) attributes.get("udc3");

        if (udc3 != null) {
            setUdc3(udc3);
        }

        Integer udc12 = (Integer) attributes.get("udc12");

        if (udc12 != null) {
            setUdc12(udc12);
        }

        Integer udc11 = (Integer) attributes.get("udc11");

        if (udc11 != null) {
            setUdc11(udc11);
        }

        Integer udcsSid = (Integer) attributes.get("udcsSid");

        if (udcsSid != null) {
            setUdcsSid(udcsSid);
        }

        Integer masterSid = (Integer) attributes.get("masterSid");

        if (masterSid != null) {
            setMasterSid(masterSid);
        }

        Integer udc10 = (Integer) attributes.get("udc10");

        if (udc10 != null) {
            setUdc10(udc10);
        }

        Integer udc9 = (Integer) attributes.get("udc9");

        if (udc9 != null) {
            setUdc9(udc9);
        }

        Integer udc8 = (Integer) attributes.get("udc8");

        if (udc8 != null) {
            setUdc8(udc8);
        }

        Integer udc7 = (Integer) attributes.get("udc7");

        if (udc7 != null) {
            setUdc7(udc7);
        }

        Integer udc6 = (Integer) attributes.get("udc6");

        if (udc6 != null) {
            setUdc6(udc6);
        }

        Integer udc5 = (Integer) attributes.get("udc5");

        if (udc5 != null) {
            setUdc5(udc5);
        }

        Integer udc4 = (Integer) attributes.get("udc4");

        if (udc4 != null) {
            setUdc4(udc4);
        }
    }

    @Override
    public int getUdc1() {
        return _udc1;
    }

    @Override
    public void setUdc1(int udc1) {
        _udc1 = udc1;

        if (_udcsRemoteModel != null) {
            try {
                Class<?> clazz = _udcsRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc1", int.class);

                method.invoke(_udcsRemoteModel, udc1);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getUdc2() {
        return _udc2;
    }

    @Override
    public void setUdc2(int udc2) {
        _udc2 = udc2;

        if (_udcsRemoteModel != null) {
            try {
                Class<?> clazz = _udcsRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc2", int.class);

                method.invoke(_udcsRemoteModel, udc2);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getMasterType() {
        return _masterType;
    }

    @Override
    public void setMasterType(String masterType) {
        _masterType = masterType;

        if (_udcsRemoteModel != null) {
            try {
                Class<?> clazz = _udcsRemoteModel.getClass();

                Method method = clazz.getMethod("setMasterType", String.class);

                method.invoke(_udcsRemoteModel, masterType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getUdc3() {
        return _udc3;
    }

    @Override
    public void setUdc3(int udc3) {
        _udc3 = udc3;

        if (_udcsRemoteModel != null) {
            try {
                Class<?> clazz = _udcsRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc3", int.class);

                method.invoke(_udcsRemoteModel, udc3);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getUdc12() {
        return _udc12;
    }

    @Override
    public void setUdc12(int udc12) {
        _udc12 = udc12;

        if (_udcsRemoteModel != null) {
            try {
                Class<?> clazz = _udcsRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc12", int.class);

                method.invoke(_udcsRemoteModel, udc12);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getUdc11() {
        return _udc11;
    }

    @Override
    public void setUdc11(int udc11) {
        _udc11 = udc11;

        if (_udcsRemoteModel != null) {
            try {
                Class<?> clazz = _udcsRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc11", int.class);

                method.invoke(_udcsRemoteModel, udc11);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getUdcsSid() {
        return _udcsSid;
    }

    @Override
    public void setUdcsSid(int udcsSid) {
        _udcsSid = udcsSid;

        if (_udcsRemoteModel != null) {
            try {
                Class<?> clazz = _udcsRemoteModel.getClass();

                Method method = clazz.getMethod("setUdcsSid", int.class);

                method.invoke(_udcsRemoteModel, udcsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getMasterSid() {
        return _masterSid;
    }

    @Override
    public void setMasterSid(int masterSid) {
        _masterSid = masterSid;

        if (_udcsRemoteModel != null) {
            try {
                Class<?> clazz = _udcsRemoteModel.getClass();

                Method method = clazz.getMethod("setMasterSid", int.class);

                method.invoke(_udcsRemoteModel, masterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getUdc10() {
        return _udc10;
    }

    @Override
    public void setUdc10(int udc10) {
        _udc10 = udc10;

        if (_udcsRemoteModel != null) {
            try {
                Class<?> clazz = _udcsRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc10", int.class);

                method.invoke(_udcsRemoteModel, udc10);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getUdc9() {
        return _udc9;
    }

    @Override
    public void setUdc9(int udc9) {
        _udc9 = udc9;

        if (_udcsRemoteModel != null) {
            try {
                Class<?> clazz = _udcsRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc9", int.class);

                method.invoke(_udcsRemoteModel, udc9);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getUdc8() {
        return _udc8;
    }

    @Override
    public void setUdc8(int udc8) {
        _udc8 = udc8;

        if (_udcsRemoteModel != null) {
            try {
                Class<?> clazz = _udcsRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc8", int.class);

                method.invoke(_udcsRemoteModel, udc8);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getUdc7() {
        return _udc7;
    }

    @Override
    public void setUdc7(int udc7) {
        _udc7 = udc7;

        if (_udcsRemoteModel != null) {
            try {
                Class<?> clazz = _udcsRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc7", int.class);

                method.invoke(_udcsRemoteModel, udc7);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getUdc6() {
        return _udc6;
    }

    @Override
    public void setUdc6(int udc6) {
        _udc6 = udc6;

        if (_udcsRemoteModel != null) {
            try {
                Class<?> clazz = _udcsRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc6", int.class);

                method.invoke(_udcsRemoteModel, udc6);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getUdc5() {
        return _udc5;
    }

    @Override
    public void setUdc5(int udc5) {
        _udc5 = udc5;

        if (_udcsRemoteModel != null) {
            try {
                Class<?> clazz = _udcsRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc5", int.class);

                method.invoke(_udcsRemoteModel, udc5);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getUdc4() {
        return _udc4;
    }

    @Override
    public void setUdc4(int udc4) {
        _udc4 = udc4;

        if (_udcsRemoteModel != null) {
            try {
                Class<?> clazz = _udcsRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc4", int.class);

                method.invoke(_udcsRemoteModel, udc4);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getUdcsRemoteModel() {
        return _udcsRemoteModel;
    }

    public void setUdcsRemoteModel(BaseModel<?> udcsRemoteModel) {
        _udcsRemoteModel = udcsRemoteModel;
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

        Class<?> remoteModelClass = _udcsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_udcsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            UdcsLocalServiceUtil.addUdcs(this);
        } else {
            UdcsLocalServiceUtil.updateUdcs(this);
        }
    }

    @Override
    public Udcs toEscapedModel() {
        return (Udcs) ProxyUtil.newProxyInstance(Udcs.class.getClassLoader(),
            new Class[] { Udcs.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        UdcsClp clone = new UdcsClp();

        clone.setUdc1(getUdc1());
        clone.setUdc2(getUdc2());
        clone.setMasterType(getMasterType());
        clone.setUdc3(getUdc3());
        clone.setUdc12(getUdc12());
        clone.setUdc11(getUdc11());
        clone.setUdcsSid(getUdcsSid());
        clone.setMasterSid(getMasterSid());
        clone.setUdc10(getUdc10());
        clone.setUdc9(getUdc9());
        clone.setUdc8(getUdc8());
        clone.setUdc7(getUdc7());
        clone.setUdc6(getUdc6());
        clone.setUdc5(getUdc5());
        clone.setUdc4(getUdc4());

        return clone;
    }

    @Override
    public int compareTo(Udcs udcs) {
        int primaryKey = udcs.getPrimaryKey();

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

        if (!(obj instanceof UdcsClp)) {
            return false;
        }

        UdcsClp udcs = (UdcsClp) obj;

        int primaryKey = udcs.getPrimaryKey();

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
        StringBundler sb = new StringBundler(31);

        sb.append("{udc1=");
        sb.append(getUdc1());
        sb.append(", udc2=");
        sb.append(getUdc2());
        sb.append(", masterType=");
        sb.append(getMasterType());
        sb.append(", udc3=");
        sb.append(getUdc3());
        sb.append(", udc12=");
        sb.append(getUdc12());
        sb.append(", udc11=");
        sb.append(getUdc11());
        sb.append(", udcsSid=");
        sb.append(getUdcsSid());
        sb.append(", masterSid=");
        sb.append(getMasterSid());
        sb.append(", udc10=");
        sb.append(getUdc10());
        sb.append(", udc9=");
        sb.append(getUdc9());
        sb.append(", udc8=");
        sb.append(getUdc8());
        sb.append(", udc7=");
        sb.append(getUdc7());
        sb.append(", udc6=");
        sb.append(getUdc6());
        sb.append(", udc5=");
        sb.append(getUdc5());
        sb.append(", udc4=");
        sb.append(getUdc4());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(49);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.Udcs");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>udc1</column-name><column-value><![CDATA[");
        sb.append(getUdc1());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc2</column-name><column-value><![CDATA[");
        sb.append(getUdc2());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>masterType</column-name><column-value><![CDATA[");
        sb.append(getMasterType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc3</column-name><column-value><![CDATA[");
        sb.append(getUdc3());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc12</column-name><column-value><![CDATA[");
        sb.append(getUdc12());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc11</column-name><column-value><![CDATA[");
        sb.append(getUdc11());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udcsSid</column-name><column-value><![CDATA[");
        sb.append(getUdcsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>masterSid</column-name><column-value><![CDATA[");
        sb.append(getMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc10</column-name><column-value><![CDATA[");
        sb.append(getUdc10());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc9</column-name><column-value><![CDATA[");
        sb.append(getUdc9());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc8</column-name><column-value><![CDATA[");
        sb.append(getUdc8());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc7</column-name><column-value><![CDATA[");
        sb.append(getUdc7());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc6</column-name><column-value><![CDATA[");
        sb.append(getUdc6());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc5</column-name><column-value><![CDATA[");
        sb.append(getUdc5());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc4</column-name><column-value><![CDATA[");
        sb.append(getUdc4());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
