package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.FcpProjLocalServiceUtil;
import com.stpl.app.service.persistence.FcpProjPK;

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


public class FcpProjClp extends BaseModelImpl<FcpProj> implements FcpProj {
    private double _adjustment;
    private int _periodSid;
    private String _priceType;
    private double _projectionPrice;
    private String _notes;
    private int _naProjDetailsSid;
    private BaseModel<?> _fcpProjRemoteModel;

    public FcpProjClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return FcpProj.class;
    }

    @Override
    public String getModelClassName() {
        return FcpProj.class.getName();
    }

    @Override
    public FcpProjPK getPrimaryKey() {
        return new FcpProjPK(_periodSid, _priceType, _naProjDetailsSid);
    }

    @Override
    public void setPrimaryKey(FcpProjPK primaryKey) {
        setPeriodSid(primaryKey.periodSid);
        setPriceType(primaryKey.priceType);
        setNaProjDetailsSid(primaryKey.naProjDetailsSid);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new FcpProjPK(_periodSid, _priceType, _naProjDetailsSid);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((FcpProjPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("adjustment", getAdjustment());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("priceType", getPriceType());
        attributes.put("projectionPrice", getProjectionPrice());
        attributes.put("notes", getNotes());
        attributes.put("naProjDetailsSid", getNaProjDetailsSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double adjustment = (Double) attributes.get("adjustment");

        if (adjustment != null) {
            setAdjustment(adjustment);
        }

        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        String priceType = (String) attributes.get("priceType");

        if (priceType != null) {
            setPriceType(priceType);
        }

        Double projectionPrice = (Double) attributes.get("projectionPrice");

        if (projectionPrice != null) {
            setProjectionPrice(projectionPrice);
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
    public double getAdjustment() {
        return _adjustment;
    }

    @Override
    public void setAdjustment(double adjustment) {
        _adjustment = adjustment;

        if (_fcpProjRemoteModel != null) {
            try {
                Class<?> clazz = _fcpProjRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustment", double.class);

                method.invoke(_fcpProjRemoteModel, adjustment);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPeriodSid() {
        return _periodSid;
    }

    @Override
    public void setPeriodSid(int periodSid) {
        _periodSid = periodSid;

        if (_fcpProjRemoteModel != null) {
            try {
                Class<?> clazz = _fcpProjRemoteModel.getClass();

                Method method = clazz.getMethod("setPeriodSid", int.class);

                method.invoke(_fcpProjRemoteModel, periodSid);
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

        if (_fcpProjRemoteModel != null) {
            try {
                Class<?> clazz = _fcpProjRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceType", String.class);

                method.invoke(_fcpProjRemoteModel, priceType);
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

        if (_fcpProjRemoteModel != null) {
            try {
                Class<?> clazz = _fcpProjRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionPrice",
                        double.class);

                method.invoke(_fcpProjRemoteModel, projectionPrice);
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

        if (_fcpProjRemoteModel != null) {
            try {
                Class<?> clazz = _fcpProjRemoteModel.getClass();

                Method method = clazz.getMethod("setNotes", String.class);

                method.invoke(_fcpProjRemoteModel, notes);
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

        if (_fcpProjRemoteModel != null) {
            try {
                Class<?> clazz = _fcpProjRemoteModel.getClass();

                Method method = clazz.getMethod("setNaProjDetailsSid", int.class);

                method.invoke(_fcpProjRemoteModel, naProjDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getFcpProjRemoteModel() {
        return _fcpProjRemoteModel;
    }

    public void setFcpProjRemoteModel(BaseModel<?> fcpProjRemoteModel) {
        _fcpProjRemoteModel = fcpProjRemoteModel;
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

        Class<?> remoteModelClass = _fcpProjRemoteModel.getClass();

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

        Object returnValue = method.invoke(_fcpProjRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            FcpProjLocalServiceUtil.addFcpProj(this);
        } else {
            FcpProjLocalServiceUtil.updateFcpProj(this);
        }
    }

    @Override
    public FcpProj toEscapedModel() {
        return (FcpProj) ProxyUtil.newProxyInstance(FcpProj.class.getClassLoader(),
            new Class[] { FcpProj.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        FcpProjClp clone = new FcpProjClp();

        clone.setAdjustment(getAdjustment());
        clone.setPeriodSid(getPeriodSid());
        clone.setPriceType(getPriceType());
        clone.setProjectionPrice(getProjectionPrice());
        clone.setNotes(getNotes());
        clone.setNaProjDetailsSid(getNaProjDetailsSid());

        return clone;
    }

    @Override
    public int compareTo(FcpProj fcpProj) {
        FcpProjPK primaryKey = fcpProj.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof FcpProjClp)) {
            return false;
        }

        FcpProjClp fcpProj = (FcpProjClp) obj;

        FcpProjPK primaryKey = fcpProj.getPrimaryKey();

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
        StringBundler sb = new StringBundler(13);

        sb.append("{adjustment=");
        sb.append(getAdjustment());
        sb.append(", periodSid=");
        sb.append(getPeriodSid());
        sb.append(", priceType=");
        sb.append(getPriceType());
        sb.append(", projectionPrice=");
        sb.append(getProjectionPrice());
        sb.append(", notes=");
        sb.append(getNotes());
        sb.append(", naProjDetailsSid=");
        sb.append(getNaProjDetailsSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.FcpProj");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>adjustment</column-name><column-value><![CDATA[");
        sb.append(getAdjustment());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>periodSid</column-name><column-value><![CDATA[");
        sb.append(getPeriodSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceType</column-name><column-value><![CDATA[");
        sb.append(getPriceType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionPrice</column-name><column-value><![CDATA[");
        sb.append(getProjectionPrice());
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
