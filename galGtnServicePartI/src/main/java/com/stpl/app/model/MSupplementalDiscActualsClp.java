package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.MSupplementalDiscActualsLocalServiceUtil;
import com.stpl.app.service.persistence.MSupplementalDiscActualsPK;

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


public class MSupplementalDiscActualsClp extends BaseModelImpl<MSupplementalDiscActuals>
    implements MSupplementalDiscActuals {
    private double _actualSales;
    private int _periodSid;
    private double _actualRate;
    private double _actualProjectionSales;
    private double _actualProjectionRate;
    private int _projectionDetailsSid;
    private BaseModel<?> _mSupplementalDiscActualsRemoteModel;

    public MSupplementalDiscActualsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return MSupplementalDiscActuals.class;
    }

    @Override
    public String getModelClassName() {
        return MSupplementalDiscActuals.class.getName();
    }

    @Override
    public MSupplementalDiscActualsPK getPrimaryKey() {
        return new MSupplementalDiscActualsPK(_periodSid, _projectionDetailsSid);
    }

    @Override
    public void setPrimaryKey(MSupplementalDiscActualsPK primaryKey) {
        setPeriodSid(primaryKey.periodSid);
        setProjectionDetailsSid(primaryKey.projectionDetailsSid);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new MSupplementalDiscActualsPK(_periodSid, _projectionDetailsSid);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((MSupplementalDiscActualsPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("actualSales", getActualSales());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("actualRate", getActualRate());
        attributes.put("actualProjectionSales", getActualProjectionSales());
        attributes.put("actualProjectionRate", getActualProjectionRate());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double actualSales = (Double) attributes.get("actualSales");

        if (actualSales != null) {
            setActualSales(actualSales);
        }

        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        Double actualRate = (Double) attributes.get("actualRate");

        if (actualRate != null) {
            setActualRate(actualRate);
        }

        Double actualProjectionSales = (Double) attributes.get(
                "actualProjectionSales");

        if (actualProjectionSales != null) {
            setActualProjectionSales(actualProjectionSales);
        }

        Double actualProjectionRate = (Double) attributes.get(
                "actualProjectionRate");

        if (actualProjectionRate != null) {
            setActualProjectionRate(actualProjectionRate);
        }

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }
    }

    @Override
    public double getActualSales() {
        return _actualSales;
    }

    @Override
    public void setActualSales(double actualSales) {
        _actualSales = actualSales;

        if (_mSupplementalDiscActualsRemoteModel != null) {
            try {
                Class<?> clazz = _mSupplementalDiscActualsRemoteModel.getClass();

                Method method = clazz.getMethod("setActualSales", double.class);

                method.invoke(_mSupplementalDiscActualsRemoteModel, actualSales);
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

        if (_mSupplementalDiscActualsRemoteModel != null) {
            try {
                Class<?> clazz = _mSupplementalDiscActualsRemoteModel.getClass();

                Method method = clazz.getMethod("setPeriodSid", int.class);

                method.invoke(_mSupplementalDiscActualsRemoteModel, periodSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getActualRate() {
        return _actualRate;
    }

    @Override
    public void setActualRate(double actualRate) {
        _actualRate = actualRate;

        if (_mSupplementalDiscActualsRemoteModel != null) {
            try {
                Class<?> clazz = _mSupplementalDiscActualsRemoteModel.getClass();

                Method method = clazz.getMethod("setActualRate", double.class);

                method.invoke(_mSupplementalDiscActualsRemoteModel, actualRate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getActualProjectionSales() {
        return _actualProjectionSales;
    }

    @Override
    public void setActualProjectionSales(double actualProjectionSales) {
        _actualProjectionSales = actualProjectionSales;

        if (_mSupplementalDiscActualsRemoteModel != null) {
            try {
                Class<?> clazz = _mSupplementalDiscActualsRemoteModel.getClass();

                Method method = clazz.getMethod("setActualProjectionSales",
                        double.class);

                method.invoke(_mSupplementalDiscActualsRemoteModel,
                    actualProjectionSales);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getActualProjectionRate() {
        return _actualProjectionRate;
    }

    @Override
    public void setActualProjectionRate(double actualProjectionRate) {
        _actualProjectionRate = actualProjectionRate;

        if (_mSupplementalDiscActualsRemoteModel != null) {
            try {
                Class<?> clazz = _mSupplementalDiscActualsRemoteModel.getClass();

                Method method = clazz.getMethod("setActualProjectionRate",
                        double.class);

                method.invoke(_mSupplementalDiscActualsRemoteModel,
                    actualProjectionRate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getProjectionDetailsSid() {
        return _projectionDetailsSid;
    }

    @Override
    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _projectionDetailsSid = projectionDetailsSid;

        if (_mSupplementalDiscActualsRemoteModel != null) {
            try {
                Class<?> clazz = _mSupplementalDiscActualsRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDetailsSid",
                        int.class);

                method.invoke(_mSupplementalDiscActualsRemoteModel,
                    projectionDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getMSupplementalDiscActualsRemoteModel() {
        return _mSupplementalDiscActualsRemoteModel;
    }

    public void setMSupplementalDiscActualsRemoteModel(
        BaseModel<?> mSupplementalDiscActualsRemoteModel) {
        _mSupplementalDiscActualsRemoteModel = mSupplementalDiscActualsRemoteModel;
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

        Class<?> remoteModelClass = _mSupplementalDiscActualsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_mSupplementalDiscActualsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            MSupplementalDiscActualsLocalServiceUtil.addMSupplementalDiscActuals(this);
        } else {
            MSupplementalDiscActualsLocalServiceUtil.updateMSupplementalDiscActuals(this);
        }
    }

    @Override
    public MSupplementalDiscActuals toEscapedModel() {
        return (MSupplementalDiscActuals) ProxyUtil.newProxyInstance(MSupplementalDiscActuals.class.getClassLoader(),
            new Class[] { MSupplementalDiscActuals.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        MSupplementalDiscActualsClp clone = new MSupplementalDiscActualsClp();

        clone.setActualSales(getActualSales());
        clone.setPeriodSid(getPeriodSid());
        clone.setActualRate(getActualRate());
        clone.setActualProjectionSales(getActualProjectionSales());
        clone.setActualProjectionRate(getActualProjectionRate());
        clone.setProjectionDetailsSid(getProjectionDetailsSid());

        return clone;
    }

    @Override
    public int compareTo(MSupplementalDiscActuals mSupplementalDiscActuals) {
        MSupplementalDiscActualsPK primaryKey = mSupplementalDiscActuals.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof MSupplementalDiscActualsClp)) {
            return false;
        }

        MSupplementalDiscActualsClp mSupplementalDiscActuals = (MSupplementalDiscActualsClp) obj;

        MSupplementalDiscActualsPK primaryKey = mSupplementalDiscActuals.getPrimaryKey();

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

        sb.append("{actualSales=");
        sb.append(getActualSales());
        sb.append(", periodSid=");
        sb.append(getPeriodSid());
        sb.append(", actualRate=");
        sb.append(getActualRate());
        sb.append(", actualProjectionSales=");
        sb.append(getActualProjectionSales());
        sb.append(", actualProjectionRate=");
        sb.append(getActualProjectionRate());
        sb.append(", projectionDetailsSid=");
        sb.append(getProjectionDetailsSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.MSupplementalDiscActuals");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>actualSales</column-name><column-value><![CDATA[");
        sb.append(getActualSales());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>periodSid</column-name><column-value><![CDATA[");
        sb.append(getPeriodSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualRate</column-name><column-value><![CDATA[");
        sb.append(getActualRate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualProjectionSales</column-name><column-value><![CDATA[");
        sb.append(getActualProjectionSales());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualProjectionRate</column-name><column-value><![CDATA[");
        sb.append(getActualProjectionRate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getProjectionDetailsSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
