package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.StNewNdcLocalServiceUtil;
import com.stpl.app.service.persistence.StNewNdcPK;

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


public class StNewNdcClp extends BaseModelImpl<StNewNdc> implements StNewNdc {
    private double _forecastAmp;
    private double _forecastBestprice;
    private int _naProjDetailsSid;
    private double _baseYearCpi;
    private int _userId;
    private Date _lastModifiedDate;
    private int _itemMasterSid;
    private double _wacPrice;
    private double _baseYearAmp;
    private int _sessionId;
    private BaseModel<?> _stNewNdcRemoteModel;

    public StNewNdcClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return StNewNdc.class;
    }

    @Override
    public String getModelClassName() {
        return StNewNdc.class.getName();
    }

    @Override
    public StNewNdcPK getPrimaryKey() {
        return new StNewNdcPK(_naProjDetailsSid, _userId, _itemMasterSid,
            _sessionId);
    }

    @Override
    public void setPrimaryKey(StNewNdcPK primaryKey) {
        setNaProjDetailsSid(primaryKey.naProjDetailsSid);
        setUserId(primaryKey.userId);
        setItemMasterSid(primaryKey.itemMasterSid);
        setSessionId(primaryKey.sessionId);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new StNewNdcPK(_naProjDetailsSid, _userId, _itemMasterSid,
            _sessionId);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((StNewNdcPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("forecastAmp", getForecastAmp());
        attributes.put("forecastBestprice", getForecastBestprice());
        attributes.put("naProjDetailsSid", getNaProjDetailsSid());
        attributes.put("baseYearCpi", getBaseYearCpi());
        attributes.put("userId", getUserId());
        attributes.put("lastModifiedDate", getLastModifiedDate());
        attributes.put("itemMasterSid", getItemMasterSid());
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

        Integer naProjDetailsSid = (Integer) attributes.get("naProjDetailsSid");

        if (naProjDetailsSid != null) {
            setNaProjDetailsSid(naProjDetailsSid);
        }

        Double baseYearCpi = (Double) attributes.get("baseYearCpi");

        if (baseYearCpi != null) {
            setBaseYearCpi(baseYearCpi);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Date lastModifiedDate = (Date) attributes.get("lastModifiedDate");

        if (lastModifiedDate != null) {
            setLastModifiedDate(lastModifiedDate);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
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

        if (_stNewNdcRemoteModel != null) {
            try {
                Class<?> clazz = _stNewNdcRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastAmp", double.class);

                method.invoke(_stNewNdcRemoteModel, forecastAmp);
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

        if (_stNewNdcRemoteModel != null) {
            try {
                Class<?> clazz = _stNewNdcRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastBestprice",
                        double.class);

                method.invoke(_stNewNdcRemoteModel, forecastBestprice);
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

        if (_stNewNdcRemoteModel != null) {
            try {
                Class<?> clazz = _stNewNdcRemoteModel.getClass();

                Method method = clazz.getMethod("setNaProjDetailsSid", int.class);

                method.invoke(_stNewNdcRemoteModel, naProjDetailsSid);
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

        if (_stNewNdcRemoteModel != null) {
            try {
                Class<?> clazz = _stNewNdcRemoteModel.getClass();

                Method method = clazz.getMethod("setBaseYearCpi", double.class);

                method.invoke(_stNewNdcRemoteModel, baseYearCpi);
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

        if (_stNewNdcRemoteModel != null) {
            try {
                Class<?> clazz = _stNewNdcRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", int.class);

                method.invoke(_stNewNdcRemoteModel, userId);
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

        if (_stNewNdcRemoteModel != null) {
            try {
                Class<?> clazz = _stNewNdcRemoteModel.getClass();

                Method method = clazz.getMethod("setLastModifiedDate",
                        Date.class);

                method.invoke(_stNewNdcRemoteModel, lastModifiedDate);
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

        if (_stNewNdcRemoteModel != null) {
            try {
                Class<?> clazz = _stNewNdcRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_stNewNdcRemoteModel, itemMasterSid);
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

        if (_stNewNdcRemoteModel != null) {
            try {
                Class<?> clazz = _stNewNdcRemoteModel.getClass();

                Method method = clazz.getMethod("setWacPrice", double.class);

                method.invoke(_stNewNdcRemoteModel, wacPrice);
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

        if (_stNewNdcRemoteModel != null) {
            try {
                Class<?> clazz = _stNewNdcRemoteModel.getClass();

                Method method = clazz.getMethod("setBaseYearAmp", double.class);

                method.invoke(_stNewNdcRemoteModel, baseYearAmp);
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

        if (_stNewNdcRemoteModel != null) {
            try {
                Class<?> clazz = _stNewNdcRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", int.class);

                method.invoke(_stNewNdcRemoteModel, sessionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getStNewNdcRemoteModel() {
        return _stNewNdcRemoteModel;
    }

    public void setStNewNdcRemoteModel(BaseModel<?> stNewNdcRemoteModel) {
        _stNewNdcRemoteModel = stNewNdcRemoteModel;
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

        Class<?> remoteModelClass = _stNewNdcRemoteModel.getClass();

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

        Object returnValue = method.invoke(_stNewNdcRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            StNewNdcLocalServiceUtil.addStNewNdc(this);
        } else {
            StNewNdcLocalServiceUtil.updateStNewNdc(this);
        }
    }

    @Override
    public StNewNdc toEscapedModel() {
        return (StNewNdc) ProxyUtil.newProxyInstance(StNewNdc.class.getClassLoader(),
            new Class[] { StNewNdc.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        StNewNdcClp clone = new StNewNdcClp();

        clone.setForecastAmp(getForecastAmp());
        clone.setForecastBestprice(getForecastBestprice());
        clone.setNaProjDetailsSid(getNaProjDetailsSid());
        clone.setBaseYearCpi(getBaseYearCpi());
        clone.setUserId(getUserId());
        clone.setLastModifiedDate(getLastModifiedDate());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setWacPrice(getWacPrice());
        clone.setBaseYearAmp(getBaseYearAmp());
        clone.setSessionId(getSessionId());

        return clone;
    }

    @Override
    public int compareTo(StNewNdc stNewNdc) {
        StNewNdcPK primaryKey = stNewNdc.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StNewNdcClp)) {
            return false;
        }

        StNewNdcClp stNewNdc = (StNewNdcClp) obj;

        StNewNdcPK primaryKey = stNewNdc.getPrimaryKey();

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

        sb.append("{forecastAmp=");
        sb.append(getForecastAmp());
        sb.append(", forecastBestprice=");
        sb.append(getForecastBestprice());
        sb.append(", naProjDetailsSid=");
        sb.append(getNaProjDetailsSid());
        sb.append(", baseYearCpi=");
        sb.append(getBaseYearCpi());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", lastModifiedDate=");
        sb.append(getLastModifiedDate());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
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
        StringBundler sb = new StringBundler(34);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.StNewNdc");
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
            "<column><column-name>naProjDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getNaProjDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>baseYearCpi</column-name><column-value><![CDATA[");
        sb.append(getBaseYearCpi());
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
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
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
