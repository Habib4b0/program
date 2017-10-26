package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.StMedicaidNewNdcLocalServiceUtil;
import com.stpl.app.service.persistence.StMedicaidNewNdcPK;

import com.stpl.portal.kernel.bean.AutoEscapeBeanHandler;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.ProxyUtil;
import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class StMedicaidNewNdcClp extends BaseModelImpl<StMedicaidNewNdc>
    implements StMedicaidNewNdc {
    private double _forecastAmp;
    private double _forecastBestprice;
    private double _baseYearCpi;
    private String _ndc9;
    private int _userId;
    private Date _lastModifiedDate;
    private double _wacPrice;
    private double _baseYearAmp;
    private int _sessionId;
    private BaseModel<?> _stMedicaidNewNdcRemoteModel;

    public StMedicaidNewNdcClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return StMedicaidNewNdc.class;
    }

    @Override
    public String getModelClassName() {
        return StMedicaidNewNdc.class.getName();
    }

    @Override
    public StMedicaidNewNdcPK getPrimaryKey() {
        return new StMedicaidNewNdcPK(_ndc9, _userId, _sessionId);
    }

    @Override
    public void setPrimaryKey(StMedicaidNewNdcPK primaryKey) {
        setNdc9(primaryKey.ndc9);
        setUserId(primaryKey.userId);
        setSessionId(primaryKey.sessionId);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new StMedicaidNewNdcPK(_ndc9, _userId, _sessionId);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((StMedicaidNewNdcPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("forecastAmp", getForecastAmp());
        attributes.put("forecastBestprice", getForecastBestprice());
        attributes.put("baseYearCpi", getBaseYearCpi());
        attributes.put("ndc9", getNdc9());
        attributes.put("userId", getUserId());
        attributes.put("lastModifiedDate", getLastModifiedDate());
        attributes.put("wacPrice", getWacPrice());
        attributes.put("baseYearAmp", getBaseYearAmp());
        attributes.put("sessionId", getSessionId());

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

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Date lastModifiedDate = (Date) attributes.get("lastModifiedDate");

        if (lastModifiedDate != null) {
            setLastModifiedDate(lastModifiedDate);
        }

        Double wacPrice = (Double) attributes.get("wacPrice");

        if (wacPrice != null) {
            setWacPrice(wacPrice);
        }

        Double baseYearAmp = (Double) attributes.get("baseYearAmp");

        if (baseYearAmp != null) {
            setBaseYearAmp(baseYearAmp);
        }

        Integer sessionId = (Integer) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }
    }

    @Override
    public double getForecastAmp() {
        return _forecastAmp;
    }

    @Override
    public void setForecastAmp(double forecastAmp) {
        _forecastAmp = forecastAmp;

        if (_stMedicaidNewNdcRemoteModel != null) {
            try {
                Class<?> clazz = _stMedicaidNewNdcRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastAmp", double.class);

                method.invoke(_stMedicaidNewNdcRemoteModel, forecastAmp);
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

        if (_stMedicaidNewNdcRemoteModel != null) {
            try {
                Class<?> clazz = _stMedicaidNewNdcRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastBestprice",
                        double.class);

                method.invoke(_stMedicaidNewNdcRemoteModel, forecastBestprice);
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

        if (_stMedicaidNewNdcRemoteModel != null) {
            try {
                Class<?> clazz = _stMedicaidNewNdcRemoteModel.getClass();

                Method method = clazz.getMethod("setBaseYearCpi", double.class);

                method.invoke(_stMedicaidNewNdcRemoteModel, baseYearCpi);
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

        if (_stMedicaidNewNdcRemoteModel != null) {
            try {
                Class<?> clazz = _stMedicaidNewNdcRemoteModel.getClass();

                Method method = clazz.getMethod("setNdc9", String.class);

                method.invoke(_stMedicaidNewNdcRemoteModel, ndc9);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getUserId() {
        return _userId;
    }

    @Override
    public void setUserId(int userId) {
        _userId = userId;

        if (_stMedicaidNewNdcRemoteModel != null) {
            try {
                Class<?> clazz = _stMedicaidNewNdcRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", int.class);

                method.invoke(_stMedicaidNewNdcRemoteModel, userId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getLastModifiedDate() {
        return _lastModifiedDate;
    }

    @Override
    public void setLastModifiedDate(Date lastModifiedDate) {
        _lastModifiedDate = lastModifiedDate;

        if (_stMedicaidNewNdcRemoteModel != null) {
            try {
                Class<?> clazz = _stMedicaidNewNdcRemoteModel.getClass();

                Method method = clazz.getMethod("setLastModifiedDate",
                        Date.class);

                method.invoke(_stMedicaidNewNdcRemoteModel, lastModifiedDate);
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

        if (_stMedicaidNewNdcRemoteModel != null) {
            try {
                Class<?> clazz = _stMedicaidNewNdcRemoteModel.getClass();

                Method method = clazz.getMethod("setWacPrice", double.class);

                method.invoke(_stMedicaidNewNdcRemoteModel, wacPrice);
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

        if (_stMedicaidNewNdcRemoteModel != null) {
            try {
                Class<?> clazz = _stMedicaidNewNdcRemoteModel.getClass();

                Method method = clazz.getMethod("setBaseYearAmp", double.class);

                method.invoke(_stMedicaidNewNdcRemoteModel, baseYearAmp);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getSessionId() {
        return _sessionId;
    }

    @Override
    public void setSessionId(int sessionId) {
        _sessionId = sessionId;

        if (_stMedicaidNewNdcRemoteModel != null) {
            try {
                Class<?> clazz = _stMedicaidNewNdcRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", int.class);

                method.invoke(_stMedicaidNewNdcRemoteModel, sessionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getStMedicaidNewNdcRemoteModel() {
        return _stMedicaidNewNdcRemoteModel;
    }

    public void setStMedicaidNewNdcRemoteModel(
        BaseModel<?> stMedicaidNewNdcRemoteModel) {
        _stMedicaidNewNdcRemoteModel = stMedicaidNewNdcRemoteModel;
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

        Class<?> remoteModelClass = _stMedicaidNewNdcRemoteModel.getClass();

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

        Object returnValue = method.invoke(_stMedicaidNewNdcRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            StMedicaidNewNdcLocalServiceUtil.addStMedicaidNewNdc(this);
        } else {
            StMedicaidNewNdcLocalServiceUtil.updateStMedicaidNewNdc(this);
        }
    }

    @Override
    public StMedicaidNewNdc toEscapedModel() {
        return (StMedicaidNewNdc) ProxyUtil.newProxyInstance(StMedicaidNewNdc.class.getClassLoader(),
            new Class[] { StMedicaidNewNdc.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        StMedicaidNewNdcClp clone = new StMedicaidNewNdcClp();

        clone.setForecastAmp(getForecastAmp());
        clone.setForecastBestprice(getForecastBestprice());
        clone.setBaseYearCpi(getBaseYearCpi());
        clone.setNdc9(getNdc9());
        clone.setUserId(getUserId());
        clone.setLastModifiedDate(getLastModifiedDate());
        clone.setWacPrice(getWacPrice());
        clone.setBaseYearAmp(getBaseYearAmp());
        clone.setSessionId(getSessionId());

        return clone;
    }

    @Override
    public int compareTo(StMedicaidNewNdc stMedicaidNewNdc) {
        StMedicaidNewNdcPK primaryKey = stMedicaidNewNdc.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StMedicaidNewNdcClp)) {
            return false;
        }

        StMedicaidNewNdcClp stMedicaidNewNdc = (StMedicaidNewNdcClp) obj;

        StMedicaidNewNdcPK primaryKey = stMedicaidNewNdc.getPrimaryKey();

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
        StringBundler sb = new StringBundler(19);

        sb.append("{forecastAmp=");
        sb.append(getForecastAmp());
        sb.append(", forecastBestprice=");
        sb.append(getForecastBestprice());
        sb.append(", baseYearCpi=");
        sb.append(getBaseYearCpi());
        sb.append(", ndc9=");
        sb.append(getNdc9());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", lastModifiedDate=");
        sb.append(getLastModifiedDate());
        sb.append(", wacPrice=");
        sb.append(getWacPrice());
        sb.append(", baseYearAmp=");
        sb.append(getBaseYearAmp());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(31);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.StMedicaidNewNdc");
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
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lastModifiedDate</column-name><column-value><![CDATA[");
        sb.append(getLastModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>wacPrice</column-name><column-value><![CDATA[");
        sb.append(getWacPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>baseYearAmp</column-name><column-value><![CDATA[");
        sb.append(getBaseYearAmp());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sessionId</column-name><column-value><![CDATA[");
        sb.append(getSessionId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
