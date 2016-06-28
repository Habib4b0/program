package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.FederalNewNdcLocalServiceUtil;

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


public class FederalNewNdcClp extends BaseModelImpl<FederalNewNdc>
    implements FederalNewNdc {
    private double _fss;
    private int _itemMasterSid;
    private double _wacPrice;
    private double _nonFamp;
    private BaseModel<?> _federalNewNdcRemoteModel;

    public FederalNewNdcClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return FederalNewNdc.class;
    }

    @Override
    public String getModelClassName() {
        return FederalNewNdc.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _itemMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setItemMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _itemMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("fss", getFss());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("wacPrice", getWacPrice());
        attributes.put("nonFamp", getNonFamp());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double fss = (Double) attributes.get("fss");

        if (fss != null) {
            setFss(fss);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Double wacPrice = (Double) attributes.get("wacPrice");

        if (wacPrice != null) {
            setWacPrice(wacPrice);
        }

        Double nonFamp = (Double) attributes.get("nonFamp");

        if (nonFamp != null) {
            setNonFamp(nonFamp);
        }
    }

    @Override
    public double getFss() {
        return _fss;
    }

    @Override
    public void setFss(double fss) {
        _fss = fss;

        if (_federalNewNdcRemoteModel != null) {
            try {
                Class<?> clazz = _federalNewNdcRemoteModel.getClass();

                Method method = clazz.getMethod("setFss", double.class);

                method.invoke(_federalNewNdcRemoteModel, fss);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getItemMasterSid() {
        return _itemMasterSid;
    }

    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _itemMasterSid = itemMasterSid;

        if (_federalNewNdcRemoteModel != null) {
            try {
                Class<?> clazz = _federalNewNdcRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_federalNewNdcRemoteModel, itemMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getWacPrice() {
        return _wacPrice;
    }

    @Override
    public void setWacPrice(double wacPrice) {
        _wacPrice = wacPrice;

        if (_federalNewNdcRemoteModel != null) {
            try {
                Class<?> clazz = _federalNewNdcRemoteModel.getClass();

                Method method = clazz.getMethod("setWacPrice", double.class);

                method.invoke(_federalNewNdcRemoteModel, wacPrice);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getNonFamp() {
        return _nonFamp;
    }

    @Override
    public void setNonFamp(double nonFamp) {
        _nonFamp = nonFamp;

        if (_federalNewNdcRemoteModel != null) {
            try {
                Class<?> clazz = _federalNewNdcRemoteModel.getClass();

                Method method = clazz.getMethod("setNonFamp", double.class);

                method.invoke(_federalNewNdcRemoteModel, nonFamp);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getFederalNewNdcRemoteModel() {
        return _federalNewNdcRemoteModel;
    }

    public void setFederalNewNdcRemoteModel(
        BaseModel<?> federalNewNdcRemoteModel) {
        _federalNewNdcRemoteModel = federalNewNdcRemoteModel;
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

        Class<?> remoteModelClass = _federalNewNdcRemoteModel.getClass();

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

        Object returnValue = method.invoke(_federalNewNdcRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            FederalNewNdcLocalServiceUtil.addFederalNewNdc(this);
        } else {
            FederalNewNdcLocalServiceUtil.updateFederalNewNdc(this);
        }
    }

    @Override
    public FederalNewNdc toEscapedModel() {
        return (FederalNewNdc) ProxyUtil.newProxyInstance(FederalNewNdc.class.getClassLoader(),
            new Class[] { FederalNewNdc.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        FederalNewNdcClp clone = new FederalNewNdcClp();

        clone.setFss(getFss());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setWacPrice(getWacPrice());
        clone.setNonFamp(getNonFamp());

        return clone;
    }

    @Override
    public int compareTo(FederalNewNdc federalNewNdc) {
        int primaryKey = federalNewNdc.getPrimaryKey();

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

        if (!(obj instanceof FederalNewNdcClp)) {
            return false;
        }

        FederalNewNdcClp federalNewNdc = (FederalNewNdcClp) obj;

        int primaryKey = federalNewNdc.getPrimaryKey();

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
        StringBundler sb = new StringBundler(9);

        sb.append("{fss=");
        sb.append(getFss());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", wacPrice=");
        sb.append(getWacPrice());
        sb.append(", nonFamp=");
        sb.append(getNonFamp());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(16);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.FederalNewNdc");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>fss</column-name><column-value><![CDATA[");
        sb.append(getFss());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>wacPrice</column-name><column-value><![CDATA[");
        sb.append(getWacPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>nonFamp</column-name><column-value><![CDATA[");
        sb.append(getNonFamp());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
