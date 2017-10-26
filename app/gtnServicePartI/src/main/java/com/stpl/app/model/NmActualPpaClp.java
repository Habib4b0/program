package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.NmActualPpaLocalServiceUtil;
import com.stpl.app.service.persistence.NmActualPpaPK;

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


public class NmActualPpaClp extends BaseModelImpl<NmActualPpa>
    implements NmActualPpa {
    private double _actualRate;
    private int _periodSid;
    private double _actualProjDiscountDollar;
    private double _actualProjectionSales;
    private int _projectionDetailsSid;
    private double _actualProjectionRate;
    private double _actualProjDiscountUnits;
    private double _actualDiscountDollar;
    private double _actualDiscountUnits;
    private double _actualSales;
    private BaseModel<?> _nmActualPpaRemoteModel;

    public NmActualPpaClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return NmActualPpa.class;
    }

    @Override
    public String getModelClassName() {
        return NmActualPpa.class.getName();
    }

    @Override
    public NmActualPpaPK getPrimaryKey() {
        return new NmActualPpaPK(_periodSid, _projectionDetailsSid);
    }

    @Override
    public void setPrimaryKey(NmActualPpaPK primaryKey) {
        setPeriodSid(primaryKey.periodSid);
        setProjectionDetailsSid(primaryKey.projectionDetailsSid);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new NmActualPpaPK(_periodSid, _projectionDetailsSid);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((NmActualPpaPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("actualRate", getActualRate());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("actualProjDiscountDollar", getActualProjDiscountDollar());
        attributes.put("actualProjectionSales", getActualProjectionSales());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("actualProjectionRate", getActualProjectionRate());
        attributes.put("actualProjDiscountUnits", getActualProjDiscountUnits());
        attributes.put("actualDiscountDollar", getActualDiscountDollar());
        attributes.put("actualDiscountUnits", getActualDiscountUnits());
        attributes.put("actualSales", getActualSales());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double actualRate = (Double) attributes.get("actualRate");

        if (actualRate != null) {
            setActualRate(actualRate);
        }

        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        Double actualProjDiscountDollar = (Double) attributes.get(
                "actualProjDiscountDollar");

        if (actualProjDiscountDollar != null) {
            setActualProjDiscountDollar(actualProjDiscountDollar);
        }

        Double actualProjectionSales = (Double) attributes.get(
                "actualProjectionSales");

        if (actualProjectionSales != null) {
            setActualProjectionSales(actualProjectionSales);
        }

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }

        Double actualProjectionRate = (Double) attributes.get(
                "actualProjectionRate");

        if (actualProjectionRate != null) {
            setActualProjectionRate(actualProjectionRate);
        }

        Double actualProjDiscountUnits = (Double) attributes.get(
                "actualProjDiscountUnits");

        if (actualProjDiscountUnits != null) {
            setActualProjDiscountUnits(actualProjDiscountUnits);
        }

        Double actualDiscountDollar = (Double) attributes.get(
                "actualDiscountDollar");

        if (actualDiscountDollar != null) {
            setActualDiscountDollar(actualDiscountDollar);
        }

        Double actualDiscountUnits = (Double) attributes.get(
                "actualDiscountUnits");

        if (actualDiscountUnits != null) {
            setActualDiscountUnits(actualDiscountUnits);
        }

        Double actualSales = (Double) attributes.get("actualSales");

        if (actualSales != null) {
            setActualSales(actualSales);
        }
    }

    @Override
    public double getActualRate() {
        return _actualRate;
    }

    @Override
    public void setActualRate(double actualRate) {
        _actualRate = actualRate;

        if (_nmActualPpaRemoteModel != null) {
            try {
                Class<?> clazz = _nmActualPpaRemoteModel.getClass();

                Method method = clazz.getMethod("setActualRate", double.class);

                method.invoke(_nmActualPpaRemoteModel, actualRate);
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

        if (_nmActualPpaRemoteModel != null) {
            try {
                Class<?> clazz = _nmActualPpaRemoteModel.getClass();

                Method method = clazz.getMethod("setPeriodSid", int.class);

                method.invoke(_nmActualPpaRemoteModel, periodSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getActualProjDiscountDollar() {
        return _actualProjDiscountDollar;
    }

    @Override
    public void setActualProjDiscountDollar(double actualProjDiscountDollar) {
        _actualProjDiscountDollar = actualProjDiscountDollar;

        if (_nmActualPpaRemoteModel != null) {
            try {
                Class<?> clazz = _nmActualPpaRemoteModel.getClass();

                Method method = clazz.getMethod("setActualProjDiscountDollar",
                        double.class);

                method.invoke(_nmActualPpaRemoteModel, actualProjDiscountDollar);
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

        if (_nmActualPpaRemoteModel != null) {
            try {
                Class<?> clazz = _nmActualPpaRemoteModel.getClass();

                Method method = clazz.getMethod("setActualProjectionSales",
                        double.class);

                method.invoke(_nmActualPpaRemoteModel, actualProjectionSales);
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

        if (_nmActualPpaRemoteModel != null) {
            try {
                Class<?> clazz = _nmActualPpaRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDetailsSid",
                        int.class);

                method.invoke(_nmActualPpaRemoteModel, projectionDetailsSid);
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

        if (_nmActualPpaRemoteModel != null) {
            try {
                Class<?> clazz = _nmActualPpaRemoteModel.getClass();

                Method method = clazz.getMethod("setActualProjectionRate",
                        double.class);

                method.invoke(_nmActualPpaRemoteModel, actualProjectionRate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getActualProjDiscountUnits() {
        return _actualProjDiscountUnits;
    }

    @Override
    public void setActualProjDiscountUnits(double actualProjDiscountUnits) {
        _actualProjDiscountUnits = actualProjDiscountUnits;

        if (_nmActualPpaRemoteModel != null) {
            try {
                Class<?> clazz = _nmActualPpaRemoteModel.getClass();

                Method method = clazz.getMethod("setActualProjDiscountUnits",
                        double.class);

                method.invoke(_nmActualPpaRemoteModel, actualProjDiscountUnits);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getActualDiscountDollar() {
        return _actualDiscountDollar;
    }

    @Override
    public void setActualDiscountDollar(double actualDiscountDollar) {
        _actualDiscountDollar = actualDiscountDollar;

        if (_nmActualPpaRemoteModel != null) {
            try {
                Class<?> clazz = _nmActualPpaRemoteModel.getClass();

                Method method = clazz.getMethod("setActualDiscountDollar",
                        double.class);

                method.invoke(_nmActualPpaRemoteModel, actualDiscountDollar);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getActualDiscountUnits() {
        return _actualDiscountUnits;
    }

    @Override
    public void setActualDiscountUnits(double actualDiscountUnits) {
        _actualDiscountUnits = actualDiscountUnits;

        if (_nmActualPpaRemoteModel != null) {
            try {
                Class<?> clazz = _nmActualPpaRemoteModel.getClass();

                Method method = clazz.getMethod("setActualDiscountUnits",
                        double.class);

                method.invoke(_nmActualPpaRemoteModel, actualDiscountUnits);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getActualSales() {
        return _actualSales;
    }

    @Override
    public void setActualSales(double actualSales) {
        _actualSales = actualSales;

        if (_nmActualPpaRemoteModel != null) {
            try {
                Class<?> clazz = _nmActualPpaRemoteModel.getClass();

                Method method = clazz.getMethod("setActualSales", double.class);

                method.invoke(_nmActualPpaRemoteModel, actualSales);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getNmActualPpaRemoteModel() {
        return _nmActualPpaRemoteModel;
    }

    public void setNmActualPpaRemoteModel(BaseModel<?> nmActualPpaRemoteModel) {
        _nmActualPpaRemoteModel = nmActualPpaRemoteModel;
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

        Class<?> remoteModelClass = _nmActualPpaRemoteModel.getClass();

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

        Object returnValue = method.invoke(_nmActualPpaRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            NmActualPpaLocalServiceUtil.addNmActualPpa(this);
        } else {
            NmActualPpaLocalServiceUtil.updateNmActualPpa(this);
        }
    }

    @Override
    public NmActualPpa toEscapedModel() {
        return (NmActualPpa) ProxyUtil.newProxyInstance(NmActualPpa.class.getClassLoader(),
            new Class[] { NmActualPpa.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        NmActualPpaClp clone = new NmActualPpaClp();

        clone.setActualRate(getActualRate());
        clone.setPeriodSid(getPeriodSid());
        clone.setActualProjDiscountDollar(getActualProjDiscountDollar());
        clone.setActualProjectionSales(getActualProjectionSales());
        clone.setProjectionDetailsSid(getProjectionDetailsSid());
        clone.setActualProjectionRate(getActualProjectionRate());
        clone.setActualProjDiscountUnits(getActualProjDiscountUnits());
        clone.setActualDiscountDollar(getActualDiscountDollar());
        clone.setActualDiscountUnits(getActualDiscountUnits());
        clone.setActualSales(getActualSales());

        return clone;
    }

    @Override
    public int compareTo(NmActualPpa nmActualPpa) {
        NmActualPpaPK primaryKey = nmActualPpa.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof NmActualPpaClp)) {
            return false;
        }

        NmActualPpaClp nmActualPpa = (NmActualPpaClp) obj;

        NmActualPpaPK primaryKey = nmActualPpa.getPrimaryKey();

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
        StringBundler sb = new StringBundler(21);

        sb.append("{actualRate=");
        sb.append(getActualRate());
        sb.append(", periodSid=");
        sb.append(getPeriodSid());
        sb.append(", actualProjDiscountDollar=");
        sb.append(getActualProjDiscountDollar());
        sb.append(", actualProjectionSales=");
        sb.append(getActualProjectionSales());
        sb.append(", projectionDetailsSid=");
        sb.append(getProjectionDetailsSid());
        sb.append(", actualProjectionRate=");
        sb.append(getActualProjectionRate());
        sb.append(", actualProjDiscountUnits=");
        sb.append(getActualProjDiscountUnits());
        sb.append(", actualDiscountDollar=");
        sb.append(getActualDiscountDollar());
        sb.append(", actualDiscountUnits=");
        sb.append(getActualDiscountUnits());
        sb.append(", actualSales=");
        sb.append(getActualSales());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(34);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.NmActualPpa");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>actualRate</column-name><column-value><![CDATA[");
        sb.append(getActualRate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>periodSid</column-name><column-value><![CDATA[");
        sb.append(getPeriodSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualProjDiscountDollar</column-name><column-value><![CDATA[");
        sb.append(getActualProjDiscountDollar());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualProjectionSales</column-name><column-value><![CDATA[");
        sb.append(getActualProjectionSales());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getProjectionDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualProjectionRate</column-name><column-value><![CDATA[");
        sb.append(getActualProjectionRate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualProjDiscountUnits</column-name><column-value><![CDATA[");
        sb.append(getActualProjDiscountUnits());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualDiscountDollar</column-name><column-value><![CDATA[");
        sb.append(getActualDiscountDollar());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualDiscountUnits</column-name><column-value><![CDATA[");
        sb.append(getActualDiscountUnits());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualSales</column-name><column-value><![CDATA[");
        sb.append(getActualSales());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
