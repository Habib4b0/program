package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.NationalAssumptionsProjLocalServiceUtil;
import com.stpl.app.service.persistence.NationalAssumptionsProjPK;

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


public class NationalAssumptionsProjClp extends BaseModelImpl<NationalAssumptionsProj>
    implements NationalAssumptionsProj {
    private int _periodSid;
    private int _itemMasterSid;
    private String _priceType;
    private double _projectionPrice;
    private BaseModel<?> _nationalAssumptionsProjRemoteModel;

    public NationalAssumptionsProjClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return NationalAssumptionsProj.class;
    }

    @Override
    public String getModelClassName() {
        return NationalAssumptionsProj.class.getName();
    }

    @Override
    public NationalAssumptionsProjPK getPrimaryKey() {
        return new NationalAssumptionsProjPK(_periodSid, _itemMasterSid,
            _priceType);
    }

    @Override
    public void setPrimaryKey(NationalAssumptionsProjPK primaryKey) {
        setPeriodSid(primaryKey.periodSid);
        setItemMasterSid(primaryKey.itemMasterSid);
        setPriceType(primaryKey.priceType);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new NationalAssumptionsProjPK(_periodSid, _itemMasterSid,
            _priceType);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((NationalAssumptionsProjPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("periodSid", getPeriodSid());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("priceType", getPriceType());
        attributes.put("projectionPrice", getProjectionPrice());

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

        Double projectionPrice = (Double) attributes.get("projectionPrice");

        if (projectionPrice != null) {
            setProjectionPrice(projectionPrice);
        }
    }

    @Override
    public int getPeriodSid() {
        return _periodSid;
    }

    @Override
    public void setPeriodSid(int periodSid) {
        _periodSid = periodSid;

        if (_nationalAssumptionsProjRemoteModel != null) {
            try {
                Class<?> clazz = _nationalAssumptionsProjRemoteModel.getClass();

                Method method = clazz.getMethod("setPeriodSid", int.class);

                method.invoke(_nationalAssumptionsProjRemoteModel, periodSid);
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

        if (_nationalAssumptionsProjRemoteModel != null) {
            try {
                Class<?> clazz = _nationalAssumptionsProjRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_nationalAssumptionsProjRemoteModel, itemMasterSid);
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

        if (_nationalAssumptionsProjRemoteModel != null) {
            try {
                Class<?> clazz = _nationalAssumptionsProjRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceType", String.class);

                method.invoke(_nationalAssumptionsProjRemoteModel, priceType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getProjectionPrice() {
        return _projectionPrice;
    }

    @Override
    public void setProjectionPrice(double projectionPrice) {
        _projectionPrice = projectionPrice;

        if (_nationalAssumptionsProjRemoteModel != null) {
            try {
                Class<?> clazz = _nationalAssumptionsProjRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionPrice",
                        double.class);

                method.invoke(_nationalAssumptionsProjRemoteModel,
                    projectionPrice);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getNationalAssumptionsProjRemoteModel() {
        return _nationalAssumptionsProjRemoteModel;
    }

    public void setNationalAssumptionsProjRemoteModel(
        BaseModel<?> nationalAssumptionsProjRemoteModel) {
        _nationalAssumptionsProjRemoteModel = nationalAssumptionsProjRemoteModel;
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

        Class<?> remoteModelClass = _nationalAssumptionsProjRemoteModel.getClass();

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

        Object returnValue = method.invoke(_nationalAssumptionsProjRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            NationalAssumptionsProjLocalServiceUtil.addNationalAssumptionsProj(this);
        } else {
            NationalAssumptionsProjLocalServiceUtil.updateNationalAssumptionsProj(this);
        }
    }

    @Override
    public NationalAssumptionsProj toEscapedModel() {
        return (NationalAssumptionsProj) ProxyUtil.newProxyInstance(NationalAssumptionsProj.class.getClassLoader(),
            new Class[] { NationalAssumptionsProj.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        NationalAssumptionsProjClp clone = new NationalAssumptionsProjClp();

        clone.setPeriodSid(getPeriodSid());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setPriceType(getPriceType());
        clone.setProjectionPrice(getProjectionPrice());

        return clone;
    }

    @Override
    public int compareTo(NationalAssumptionsProj nationalAssumptionsProj) {
        NationalAssumptionsProjPK primaryKey = nationalAssumptionsProj.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof NationalAssumptionsProjClp)) {
            return false;
        }

        NationalAssumptionsProjClp nationalAssumptionsProj = (NationalAssumptionsProjClp) obj;

        NationalAssumptionsProjPK primaryKey = nationalAssumptionsProj.getPrimaryKey();

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
        sb.append(", projectionPrice=");
        sb.append(getProjectionPrice());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(16);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.NationalAssumptionsProj");
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
            "<column><column-name>projectionPrice</column-name><column-value><![CDATA[");
        sb.append(getProjectionPrice());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
