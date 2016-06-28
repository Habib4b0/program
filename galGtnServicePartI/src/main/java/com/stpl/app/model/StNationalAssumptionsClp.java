package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.StNationalAssumptionsLocalServiceUtil;
import com.stpl.app.service.persistence.StNationalAssumptionsPK;

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


public class StNationalAssumptionsClp extends BaseModelImpl<StNationalAssumptions>
    implements StNationalAssumptions {
    private Date _lastModifiedDate;
    private String _baselinePeriod;
    private String _startPeriod;
    private String _frequency;
    private int _userId;
    private String _endPeriod;
    private int _naProjMasterSid;
    private String _rollingPeriod;
    private String _forecastMethodology;
    private String _priceType;
    private String _priceBasis;
    private int _sessionId;
    private String _baselineMethodology;
    private double _growthRate;
    private BaseModel<?> _stNationalAssumptionsRemoteModel;

    public StNationalAssumptionsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return StNationalAssumptions.class;
    }

    @Override
    public String getModelClassName() {
        return StNationalAssumptions.class.getName();
    }

    @Override
    public StNationalAssumptionsPK getPrimaryKey() {
        return new StNationalAssumptionsPK(_startPeriod, _userId, _endPeriod,
            _naProjMasterSid, _priceType, _sessionId);
    }

    @Override
    public void setPrimaryKey(StNationalAssumptionsPK primaryKey) {
        setStartPeriod(primaryKey.startPeriod);
        setUserId(primaryKey.userId);
        setEndPeriod(primaryKey.endPeriod);
        setNaProjMasterSid(primaryKey.naProjMasterSid);
        setPriceType(primaryKey.priceType);
        setSessionId(primaryKey.sessionId);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new StNationalAssumptionsPK(_startPeriod, _userId, _endPeriod,
            _naProjMasterSid, _priceType, _sessionId);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((StNationalAssumptionsPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("lastModifiedDate", getLastModifiedDate());
        attributes.put("baselinePeriod", getBaselinePeriod());
        attributes.put("startPeriod", getStartPeriod());
        attributes.put("frequency", getFrequency());
        attributes.put("userId", getUserId());
        attributes.put("endPeriod", getEndPeriod());
        attributes.put("naProjMasterSid", getNaProjMasterSid());
        attributes.put("rollingPeriod", getRollingPeriod());
        attributes.put("forecastMethodology", getForecastMethodology());
        attributes.put("priceType", getPriceType());
        attributes.put("priceBasis", getPriceBasis());
        attributes.put("sessionId", getSessionId());
        attributes.put("baselineMethodology", getBaselineMethodology());
        attributes.put("growthRate", getGrowthRate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date lastModifiedDate = (Date) attributes.get("lastModifiedDate");

        if (lastModifiedDate != null) {
            setLastModifiedDate(lastModifiedDate);
        }

        String baselinePeriod = (String) attributes.get("baselinePeriod");

        if (baselinePeriod != null) {
            setBaselinePeriod(baselinePeriod);
        }

        String startPeriod = (String) attributes.get("startPeriod");

        if (startPeriod != null) {
            setStartPeriod(startPeriod);
        }

        String frequency = (String) attributes.get("frequency");

        if (frequency != null) {
            setFrequency(frequency);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        String endPeriod = (String) attributes.get("endPeriod");

        if (endPeriod != null) {
            setEndPeriod(endPeriod);
        }

        Integer naProjMasterSid = (Integer) attributes.get("naProjMasterSid");

        if (naProjMasterSid != null) {
            setNaProjMasterSid(naProjMasterSid);
        }

        String rollingPeriod = (String) attributes.get("rollingPeriod");

        if (rollingPeriod != null) {
            setRollingPeriod(rollingPeriod);
        }

        String forecastMethodology = (String) attributes.get(
                "forecastMethodology");

        if (forecastMethodology != null) {
            setForecastMethodology(forecastMethodology);
        }

        String priceType = (String) attributes.get("priceType");

        if (priceType != null) {
            setPriceType(priceType);
        }

        String priceBasis = (String) attributes.get("priceBasis");

        if (priceBasis != null) {
            setPriceBasis(priceBasis);
        }

        Integer sessionId = (Integer) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        String baselineMethodology = (String) attributes.get(
                "baselineMethodology");

        if (baselineMethodology != null) {
            setBaselineMethodology(baselineMethodology);
        }

        Double growthRate = (Double) attributes.get("growthRate");

        if (growthRate != null) {
            setGrowthRate(growthRate);
        }
    }

    @Override
    public Date getLastModifiedDate() {
        return _lastModifiedDate;
    }

    @Override
    public void setLastModifiedDate(Date lastModifiedDate) {
        _lastModifiedDate = lastModifiedDate;

        if (_stNationalAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stNationalAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setLastModifiedDate",
                        Date.class);

                method.invoke(_stNationalAssumptionsRemoteModel,
                    lastModifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBaselinePeriod() {
        return _baselinePeriod;
    }

    @Override
    public void setBaselinePeriod(String baselinePeriod) {
        _baselinePeriod = baselinePeriod;

        if (_stNationalAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stNationalAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setBaselinePeriod",
                        String.class);

                method.invoke(_stNationalAssumptionsRemoteModel, baselinePeriod);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getStartPeriod() {
        return _startPeriod;
    }

    @Override
    public void setStartPeriod(String startPeriod) {
        _startPeriod = startPeriod;

        if (_stNationalAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stNationalAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setStartPeriod", String.class);

                method.invoke(_stNationalAssumptionsRemoteModel, startPeriod);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFrequency() {
        return _frequency;
    }

    @Override
    public void setFrequency(String frequency) {
        _frequency = frequency;

        if (_stNationalAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stNationalAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setFrequency", String.class);

                method.invoke(_stNationalAssumptionsRemoteModel, frequency);
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

        if (_stNationalAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stNationalAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", int.class);

                method.invoke(_stNationalAssumptionsRemoteModel, userId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getEndPeriod() {
        return _endPeriod;
    }

    @Override
    public void setEndPeriod(String endPeriod) {
        _endPeriod = endPeriod;

        if (_stNationalAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stNationalAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setEndPeriod", String.class);

                method.invoke(_stNationalAssumptionsRemoteModel, endPeriod);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getNaProjMasterSid() {
        return _naProjMasterSid;
    }

    @Override
    public void setNaProjMasterSid(int naProjMasterSid) {
        _naProjMasterSid = naProjMasterSid;

        if (_stNationalAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stNationalAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setNaProjMasterSid", int.class);

                method.invoke(_stNationalAssumptionsRemoteModel, naProjMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRollingPeriod() {
        return _rollingPeriod;
    }

    @Override
    public void setRollingPeriod(String rollingPeriod) {
        _rollingPeriod = rollingPeriod;

        if (_stNationalAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stNationalAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setRollingPeriod", String.class);

                method.invoke(_stNationalAssumptionsRemoteModel, rollingPeriod);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getForecastMethodology() {
        return _forecastMethodology;
    }

    @Override
    public void setForecastMethodology(String forecastMethodology) {
        _forecastMethodology = forecastMethodology;

        if (_stNationalAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stNationalAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastMethodology",
                        String.class);

                method.invoke(_stNationalAssumptionsRemoteModel,
                    forecastMethodology);
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

        if (_stNationalAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stNationalAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceType", String.class);

                method.invoke(_stNationalAssumptionsRemoteModel, priceType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPriceBasis() {
        return _priceBasis;
    }

    @Override
    public void setPriceBasis(String priceBasis) {
        _priceBasis = priceBasis;

        if (_stNationalAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stNationalAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceBasis", String.class);

                method.invoke(_stNationalAssumptionsRemoteModel, priceBasis);
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

        if (_stNationalAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stNationalAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", int.class);

                method.invoke(_stNationalAssumptionsRemoteModel, sessionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBaselineMethodology() {
        return _baselineMethodology;
    }

    @Override
    public void setBaselineMethodology(String baselineMethodology) {
        _baselineMethodology = baselineMethodology;

        if (_stNationalAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stNationalAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setBaselineMethodology",
                        String.class);

                method.invoke(_stNationalAssumptionsRemoteModel,
                    baselineMethodology);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getGrowthRate() {
        return _growthRate;
    }

    @Override
    public void setGrowthRate(double growthRate) {
        _growthRate = growthRate;

        if (_stNationalAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _stNationalAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setGrowthRate", double.class);

                method.invoke(_stNationalAssumptionsRemoteModel, growthRate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getStNationalAssumptionsRemoteModel() {
        return _stNationalAssumptionsRemoteModel;
    }

    public void setStNationalAssumptionsRemoteModel(
        BaseModel<?> stNationalAssumptionsRemoteModel) {
        _stNationalAssumptionsRemoteModel = stNationalAssumptionsRemoteModel;
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

        Class<?> remoteModelClass = _stNationalAssumptionsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_stNationalAssumptionsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            StNationalAssumptionsLocalServiceUtil.addStNationalAssumptions(this);
        } else {
            StNationalAssumptionsLocalServiceUtil.updateStNationalAssumptions(this);
        }
    }

    @Override
    public StNationalAssumptions toEscapedModel() {
        return (StNationalAssumptions) ProxyUtil.newProxyInstance(StNationalAssumptions.class.getClassLoader(),
            new Class[] { StNationalAssumptions.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        StNationalAssumptionsClp clone = new StNationalAssumptionsClp();

        clone.setLastModifiedDate(getLastModifiedDate());
        clone.setBaselinePeriod(getBaselinePeriod());
        clone.setStartPeriod(getStartPeriod());
        clone.setFrequency(getFrequency());
        clone.setUserId(getUserId());
        clone.setEndPeriod(getEndPeriod());
        clone.setNaProjMasterSid(getNaProjMasterSid());
        clone.setRollingPeriod(getRollingPeriod());
        clone.setForecastMethodology(getForecastMethodology());
        clone.setPriceType(getPriceType());
        clone.setPriceBasis(getPriceBasis());
        clone.setSessionId(getSessionId());
        clone.setBaselineMethodology(getBaselineMethodology());
        clone.setGrowthRate(getGrowthRate());

        return clone;
    }

    @Override
    public int compareTo(StNationalAssumptions stNationalAssumptions) {
        StNationalAssumptionsPK primaryKey = stNationalAssumptions.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StNationalAssumptionsClp)) {
            return false;
        }

        StNationalAssumptionsClp stNationalAssumptions = (StNationalAssumptionsClp) obj;

        StNationalAssumptionsPK primaryKey = stNationalAssumptions.getPrimaryKey();

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
        StringBundler sb = new StringBundler(29);

        sb.append("{lastModifiedDate=");
        sb.append(getLastModifiedDate());
        sb.append(", baselinePeriod=");
        sb.append(getBaselinePeriod());
        sb.append(", startPeriod=");
        sb.append(getStartPeriod());
        sb.append(", frequency=");
        sb.append(getFrequency());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", endPeriod=");
        sb.append(getEndPeriod());
        sb.append(", naProjMasterSid=");
        sb.append(getNaProjMasterSid());
        sb.append(", rollingPeriod=");
        sb.append(getRollingPeriod());
        sb.append(", forecastMethodology=");
        sb.append(getForecastMethodology());
        sb.append(", priceType=");
        sb.append(getPriceType());
        sb.append(", priceBasis=");
        sb.append(getPriceBasis());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append(", baselineMethodology=");
        sb.append(getBaselineMethodology());
        sb.append(", growthRate=");
        sb.append(getGrowthRate());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(46);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.StNationalAssumptions");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>lastModifiedDate</column-name><column-value><![CDATA[");
        sb.append(getLastModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>baselinePeriod</column-name><column-value><![CDATA[");
        sb.append(getBaselinePeriod());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>startPeriod</column-name><column-value><![CDATA[");
        sb.append(getStartPeriod());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>frequency</column-name><column-value><![CDATA[");
        sb.append(getFrequency());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>endPeriod</column-name><column-value><![CDATA[");
        sb.append(getEndPeriod());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>naProjMasterSid</column-name><column-value><![CDATA[");
        sb.append(getNaProjMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rollingPeriod</column-name><column-value><![CDATA[");
        sb.append(getRollingPeriod());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastMethodology</column-name><column-value><![CDATA[");
        sb.append(getForecastMethodology());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceType</column-name><column-value><![CDATA[");
        sb.append(getPriceType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceBasis</column-name><column-value><![CDATA[");
        sb.append(getPriceBasis());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sessionId</column-name><column-value><![CDATA[");
        sb.append(getSessionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>baselineMethodology</column-name><column-value><![CDATA[");
        sb.append(getBaselineMethodology());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>growthRate</column-name><column-value><![CDATA[");
        sb.append(getGrowthRate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
