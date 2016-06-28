package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.NationalAssumptionsActualsLocalServiceUtil;
import com.stpl.app.service.persistence.NationalAssumptionsActualsPK;

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


public class NationalAssumptionsActualsClp extends BaseModelImpl<NationalAssumptionsActuals>
    implements NationalAssumptionsActuals {
    private int _periodSid;
    private int _itemMasterSid;
    private String _priceType;
    private double _actualPrice;
    private BaseModel<?> _nationalAssumptionsActualsRemoteModel;

    public NationalAssumptionsActualsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return NationalAssumptionsActuals.class;
    }

    @Override
    public String getModelClassName() {
        return NationalAssumptionsActuals.class.getName();
    }

    @Override
    public NationalAssumptionsActualsPK getPrimaryKey() {
        return new NationalAssumptionsActualsPK(_periodSid, _itemMasterSid,
            _priceType);
    }

    @Override
    public void setPrimaryKey(NationalAssumptionsActualsPK primaryKey) {
        setPeriodSid(primaryKey.periodSid);
        setItemMasterSid(primaryKey.itemMasterSid);
        setPriceType(primaryKey.priceType);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new NationalAssumptionsActualsPK(_periodSid, _itemMasterSid,
            _priceType);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((NationalAssumptionsActualsPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("periodSid", getPeriodSid());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("priceType", getPriceType());
        attributes.put("actualPrice", getActualPrice());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        String priceType = (String) attributes.get("priceType");

        if (priceType != null) {
            setPriceType(priceType);
        }

        Double actualPrice = (Double) attributes.get("actualPrice");

        if (actualPrice != null) {
            setActualPrice(actualPrice);
        }
    }

    @Override
    public int getPeriodSid() {
        return _periodSid;
    }

    @Override
    public void setPeriodSid(int periodSid) {
        _periodSid = periodSid;

        if (_nationalAssumptionsActualsRemoteModel != null) {
            try {
                Class<?> clazz = _nationalAssumptionsActualsRemoteModel.getClass();

                Method method = clazz.getMethod("setPeriodSid", int.class);

                method.invoke(_nationalAssumptionsActualsRemoteModel, periodSid);
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

        if (_nationalAssumptionsActualsRemoteModel != null) {
            try {
                Class<?> clazz = _nationalAssumptionsActualsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_nationalAssumptionsActualsRemoteModel,
                    itemMasterSid);
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

        if (_nationalAssumptionsActualsRemoteModel != null) {
            try {
                Class<?> clazz = _nationalAssumptionsActualsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceType", String.class);

                method.invoke(_nationalAssumptionsActualsRemoteModel, priceType);
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

        if (_nationalAssumptionsActualsRemoteModel != null) {
            try {
                Class<?> clazz = _nationalAssumptionsActualsRemoteModel.getClass();

                Method method = clazz.getMethod("setActualPrice", double.class);

                method.invoke(_nationalAssumptionsActualsRemoteModel,
                    actualPrice);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getNationalAssumptionsActualsRemoteModel() {
        return _nationalAssumptionsActualsRemoteModel;
    }

    public void setNationalAssumptionsActualsRemoteModel(
        BaseModel<?> nationalAssumptionsActualsRemoteModel) {
        _nationalAssumptionsActualsRemoteModel = nationalAssumptionsActualsRemoteModel;
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

        Class<?> remoteModelClass = _nationalAssumptionsActualsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_nationalAssumptionsActualsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            NationalAssumptionsActualsLocalServiceUtil.addNationalAssumptionsActuals(this);
        } else {
            NationalAssumptionsActualsLocalServiceUtil.updateNationalAssumptionsActuals(this);
        }
    }

    @Override
    public NationalAssumptionsActuals toEscapedModel() {
        return (NationalAssumptionsActuals) ProxyUtil.newProxyInstance(NationalAssumptionsActuals.class.getClassLoader(),
            new Class[] { NationalAssumptionsActuals.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        NationalAssumptionsActualsClp clone = new NationalAssumptionsActualsClp();

        clone.setPeriodSid(getPeriodSid());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setPriceType(getPriceType());
        clone.setActualPrice(getActualPrice());

        return clone;
    }

    @Override
    public int compareTo(NationalAssumptionsActuals nationalAssumptionsActuals) {
        NationalAssumptionsActualsPK primaryKey = nationalAssumptionsActuals.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof NationalAssumptionsActualsClp)) {
            return false;
        }

        NationalAssumptionsActualsClp nationalAssumptionsActuals = (NationalAssumptionsActualsClp) obj;

        NationalAssumptionsActualsPK primaryKey = nationalAssumptionsActuals.getPrimaryKey();

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
        StringBundler sb = new StringBundler(9);

        sb.append("{periodSid=");
        sb.append(getPeriodSid());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", priceType=");
        sb.append(getPriceType());
        sb.append(", actualPrice=");
        sb.append(getActualPrice());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(16);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.NationalAssumptionsActuals");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>periodSid</column-name><column-value><![CDATA[");
        sb.append(getPeriodSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceType</column-name><column-value><![CDATA[");
        sb.append(getPriceType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualPrice</column-name><column-value><![CDATA[");
        sb.append(getActualPrice());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
