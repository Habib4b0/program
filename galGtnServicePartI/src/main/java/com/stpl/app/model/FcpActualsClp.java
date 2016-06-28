package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.FcpActualsLocalServiceUtil;
import com.stpl.app.service.persistence.FcpActualsPK;

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


public class FcpActualsClp extends BaseModelImpl<FcpActuals>
    implements FcpActuals {
    private int _periodSid;
    private String _priceType;
    private double _actualPrice;
    private String _notes;
    private int _naProjDetailsSid;
    private BaseModel<?> _fcpActualsRemoteModel;

    public FcpActualsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return FcpActuals.class;
    }

    @Override
    public String getModelClassName() {
        return FcpActuals.class.getName();
    }

    @Override
    public FcpActualsPK getPrimaryKey() {
        return new FcpActualsPK(_periodSid, _priceType, _naProjDetailsSid);
    }

    @Override
    public void setPrimaryKey(FcpActualsPK primaryKey) {
        setPeriodSid(primaryKey.periodSid);
        setPriceType(primaryKey.priceType);
        setNaProjDetailsSid(primaryKey.naProjDetailsSid);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new FcpActualsPK(_periodSid, _priceType, _naProjDetailsSid);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((FcpActualsPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("periodSid", getPeriodSid());
        attributes.put("priceType", getPriceType());
        attributes.put("actualPrice", getActualPrice());
        attributes.put("notes", getNotes());
        attributes.put("naProjDetailsSid", getNaProjDetailsSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        String priceType = (String) attributes.get("priceType");

        if (priceType != null) {
            setPriceType(priceType);
        }

        Double actualPrice = (Double) attributes.get("actualPrice");

        if (actualPrice != null) {
            setActualPrice(actualPrice);
        }

        String notes = (String) attributes.get("notes");

        if (notes != null) {
            setNotes(notes);
        }

        Integer naProjDetailsSid = (Integer) attributes.get("naProjDetailsSid");

        if (naProjDetailsSid != null) {
            setNaProjDetailsSid(naProjDetailsSid);
        }
    }

    @Override
    public int getPeriodSid() {
        return _periodSid;
    }

    @Override
    public void setPeriodSid(int periodSid) {
        _periodSid = periodSid;

        if (_fcpActualsRemoteModel != null) {
            try {
                Class<?> clazz = _fcpActualsRemoteModel.getClass();

                Method method = clazz.getMethod("setPeriodSid", int.class);

                method.invoke(_fcpActualsRemoteModel, periodSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPriceType() {
        return _priceType;
    }

    @Override
    public void setPriceType(String priceType) {
        _priceType = priceType;

        if (_fcpActualsRemoteModel != null) {
            try {
                Class<?> clazz = _fcpActualsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceType", String.class);

                method.invoke(_fcpActualsRemoteModel, priceType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getActualPrice() {
        return _actualPrice;
    }

    @Override
    public void setActualPrice(double actualPrice) {
        _actualPrice = actualPrice;

        if (_fcpActualsRemoteModel != null) {
            try {
                Class<?> clazz = _fcpActualsRemoteModel.getClass();

                Method method = clazz.getMethod("setActualPrice", double.class);

                method.invoke(_fcpActualsRemoteModel, actualPrice);
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

        if (_fcpActualsRemoteModel != null) {
            try {
                Class<?> clazz = _fcpActualsRemoteModel.getClass();

                Method method = clazz.getMethod("setNotes", String.class);

                method.invoke(_fcpActualsRemoteModel, notes);
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

        if (_fcpActualsRemoteModel != null) {
            try {
                Class<?> clazz = _fcpActualsRemoteModel.getClass();

                Method method = clazz.getMethod("setNaProjDetailsSid", int.class);

                method.invoke(_fcpActualsRemoteModel, naProjDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getFcpActualsRemoteModel() {
        return _fcpActualsRemoteModel;
    }

    public void setFcpActualsRemoteModel(BaseModel<?> fcpActualsRemoteModel) {
        _fcpActualsRemoteModel = fcpActualsRemoteModel;
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

        Class<?> remoteModelClass = _fcpActualsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_fcpActualsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            FcpActualsLocalServiceUtil.addFcpActuals(this);
        } else {
            FcpActualsLocalServiceUtil.updateFcpActuals(this);
        }
    }

    @Override
    public FcpActuals toEscapedModel() {
        return (FcpActuals) ProxyUtil.newProxyInstance(FcpActuals.class.getClassLoader(),
            new Class[] { FcpActuals.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        FcpActualsClp clone = new FcpActualsClp();

        clone.setPeriodSid(getPeriodSid());
        clone.setPriceType(getPriceType());
        clone.setActualPrice(getActualPrice());
        clone.setNotes(getNotes());
        clone.setNaProjDetailsSid(getNaProjDetailsSid());

        return clone;
    }

    @Override
    public int compareTo(FcpActuals fcpActuals) {
        FcpActualsPK primaryKey = fcpActuals.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof FcpActualsClp)) {
            return false;
        }

        FcpActualsClp fcpActuals = (FcpActualsClp) obj;

        FcpActualsPK primaryKey = fcpActuals.getPrimaryKey();

        if (getPrimaryKey().equals(primaryKey)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getPrimaryKey().hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{periodSid=");
        sb.append(getPeriodSid());
        sb.append(", priceType=");
        sb.append(getPriceType());
        sb.append(", actualPrice=");
        sb.append(getActualPrice());
        sb.append(", notes=");
        sb.append(getNotes());
        sb.append(", naProjDetailsSid=");
        sb.append(getNaProjDetailsSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(19);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.FcpActuals");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>periodSid</column-name><column-value><![CDATA[");
        sb.append(getPeriodSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceType</column-name><column-value><![CDATA[");
        sb.append(getPriceType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualPrice</column-name><column-value><![CDATA[");
        sb.append(getActualPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>notes</column-name><column-value><![CDATA[");
        sb.append(getNotes());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>naProjDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getNaProjDetailsSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
