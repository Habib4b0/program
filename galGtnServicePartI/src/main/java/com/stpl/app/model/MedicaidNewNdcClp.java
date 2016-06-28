package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.MedicaidNewNdcLocalServiceUtil;

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


public class MedicaidNewNdcClp extends BaseModelImpl<MedicaidNewNdc>
    implements MedicaidNewNdc {
    private double _forecastAmp;
    private double _forecastBestprice;
    private double _baseYearCpi;
    private String _ndc9;
    private double _wacPrice;
    private double _baseYearAmp;
    private BaseModel<?> _medicaidNewNdcRemoteModel;

    public MedicaidNewNdcClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return MedicaidNewNdc.class;
    }

    @Override
    public String getModelClassName() {
        return MedicaidNewNdc.class.getName();
    }

    @Override
    public String getPrimaryKey() {
        return _ndc9;
    }

    @Override
    public void setPrimaryKey(String primaryKey) {
        setNdc9(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ndc9;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((String) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("forecastAmp", getForecastAmp());
        attributes.put("forecastBestprice", getForecastBestprice());
        attributes.put("baseYearCpi", getBaseYearCpi());
        attributes.put("ndc9", getNdc9());
        attributes.put("wacPrice", getWacPrice());
        attributes.put("baseYearAmp", getBaseYearAmp());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double forecastAmp = (Double) attributes.get("forecastAmp");

        if (forecastAmp != null) {
            setForecastAmp(forecastAmp);
        }

        Double forecastBestprice = (Double) attributes.get("forecastBestprice");

        if (forecastBestprice != null) {
            setForecastBestprice(forecastBestprice);
        }

        Double baseYearCpi = (Double) attributes.get("baseYearCpi");

        if (baseYearCpi != null) {
            setBaseYearCpi(baseYearCpi);
        }

        String ndc9 = (String) attributes.get("ndc9");

        if (ndc9 != null) {
            setNdc9(ndc9);
        }

        Double wacPrice = (Double) attributes.get("wacPrice");

        if (wacPrice != null) {
            setWacPrice(wacPrice);
        }

        Double baseYearAmp = (Double) attributes.get("baseYearAmp");

        if (baseYearAmp != null) {
            setBaseYearAmp(baseYearAmp);
        }
    }

    @Override
    public double getForecastAmp() {
        return _forecastAmp;
    }

    @Override
    public void setForecastAmp(double forecastAmp) {
        _forecastAmp = forecastAmp;

        if (_medicaidNewNdcRemoteModel != null) {
            try {
                Class<?> clazz = _medicaidNewNdcRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastAmp", double.class);

                method.invoke(_medicaidNewNdcRemoteModel, forecastAmp);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getForecastBestprice() {
        return _forecastBestprice;
    }

    @Override
    public void setForecastBestprice(double forecastBestprice) {
        _forecastBestprice = forecastBestprice;

        if (_medicaidNewNdcRemoteModel != null) {
            try {
                Class<?> clazz = _medicaidNewNdcRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastBestprice",
                        double.class);

                method.invoke(_medicaidNewNdcRemoteModel, forecastBestprice);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getBaseYearCpi() {
        return _baseYearCpi;
    }

    @Override
    public void setBaseYearCpi(double baseYearCpi) {
        _baseYearCpi = baseYearCpi;

        if (_medicaidNewNdcRemoteModel != null) {
            try {
                Class<?> clazz = _medicaidNewNdcRemoteModel.getClass();

                Method method = clazz.getMethod("setBaseYearCpi", double.class);

                method.invoke(_medicaidNewNdcRemoteModel, baseYearCpi);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNdc9() {
        return _ndc9;
    }

    @Override
    public void setNdc9(String ndc9) {
        _ndc9 = ndc9;

        if (_medicaidNewNdcRemoteModel != null) {
            try {
                Class<?> clazz = _medicaidNewNdcRemoteModel.getClass();

                Method method = clazz.getMethod("setNdc9", String.class);

                method.invoke(_medicaidNewNdcRemoteModel, ndc9);
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

        if (_medicaidNewNdcRemoteModel != null) {
            try {
                Class<?> clazz = _medicaidNewNdcRemoteModel.getClass();

                Method method = clazz.getMethod("setWacPrice", double.class);

                method.invoke(_medicaidNewNdcRemoteModel, wacPrice);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getBaseYearAmp() {
        return _baseYearAmp;
    }

    @Override
    public void setBaseYearAmp(double baseYearAmp) {
        _baseYearAmp = baseYearAmp;

        if (_medicaidNewNdcRemoteModel != null) {
            try {
                Class<?> clazz = _medicaidNewNdcRemoteModel.getClass();

                Method method = clazz.getMethod("setBaseYearAmp", double.class);

                method.invoke(_medicaidNewNdcRemoteModel, baseYearAmp);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getMedicaidNewNdcRemoteModel() {
        return _medicaidNewNdcRemoteModel;
    }

    public void setMedicaidNewNdcRemoteModel(
        BaseModel<?> medicaidNewNdcRemoteModel) {
        _medicaidNewNdcRemoteModel = medicaidNewNdcRemoteModel;
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

        Class<?> remoteModelClass = _medicaidNewNdcRemoteModel.getClass();

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

        Object returnValue = method.invoke(_medicaidNewNdcRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            MedicaidNewNdcLocalServiceUtil.addMedicaidNewNdc(this);
        } else {
            MedicaidNewNdcLocalServiceUtil.updateMedicaidNewNdc(this);
        }
    }

    @Override
    public MedicaidNewNdc toEscapedModel() {
        return (MedicaidNewNdc) ProxyUtil.newProxyInstance(MedicaidNewNdc.class.getClassLoader(),
            new Class[] { MedicaidNewNdc.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        MedicaidNewNdcClp clone = new MedicaidNewNdcClp();

        clone.setForecastAmp(getForecastAmp());
        clone.setForecastBestprice(getForecastBestprice());
        clone.setBaseYearCpi(getBaseYearCpi());
        clone.setNdc9(getNdc9());
        clone.setWacPrice(getWacPrice());
        clone.setBaseYearAmp(getBaseYearAmp());

        return clone;
    }

    @Override
    public int compareTo(MedicaidNewNdc medicaidNewNdc) {
        String primaryKey = medicaidNewNdc.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof MedicaidNewNdcClp)) {
            return false;
        }

        MedicaidNewNdcClp medicaidNewNdc = (MedicaidNewNdcClp) obj;

        String primaryKey = medicaidNewNdc.getPrimaryKey();

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

        sb.append("{forecastAmp=");
        sb.append(getForecastAmp());
        sb.append(", forecastBestprice=");
        sb.append(getForecastBestprice());
        sb.append(", baseYearCpi=");
        sb.append(getBaseYearCpi());
        sb.append(", ndc9=");
        sb.append(getNdc9());
        sb.append(", wacPrice=");
        sb.append(getWacPrice());
        sb.append(", baseYearAmp=");
        sb.append(getBaseYearAmp());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.MedicaidNewNdc");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>forecastAmp</column-name><column-value><![CDATA[");
        sb.append(getForecastAmp());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastBestprice</column-name><column-value><![CDATA[");
        sb.append(getForecastBestprice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>baseYearCpi</column-name><column-value><![CDATA[");
        sb.append(getBaseYearCpi());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ndc9</column-name><column-value><![CDATA[");
        sb.append(getNdc9());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>wacPrice</column-name><column-value><![CDATA[");
        sb.append(getWacPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>baseYearAmp</column-name><column-value><![CDATA[");
        sb.append(getBaseYearAmp());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
