package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.NationalAssumptionsLocalServiceUtil;
import com.stpl.app.service.persistence.NationalAssumptionsPK;

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


public class NationalAssumptionsClp extends BaseModelImpl<NationalAssumptions>
    implements NationalAssumptions {
    private String _baselinePeriod;
    private String _frequency;
    private String _startPeriod;
    private String _forecastMethodology;
    private String _priceType;
    private String _endPeriod;
    private String _priceBasis;
    private int _naProjMasterSid;
    private String _rollingPeriod;
    private String _baselineMethodology;
    private double _growthRate;
    private BaseModel<?> _nationalAssumptionsRemoteModel;

    public NationalAssumptionsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return NationalAssumptions.class;
    }

    @Override
    public String getModelClassName() {
        return NationalAssumptions.class.getName();
    }

    @Override
    public NationalAssumptionsPK getPrimaryKey() {
        return new NationalAssumptionsPK(_startPeriod, _priceType, _endPeriod,
            _naProjMasterSid);
    }

    @Override
    public void setPrimaryKey(NationalAssumptionsPK primaryKey) {
        setStartPeriod(primaryKey.startPeriod);
        setPriceType(primaryKey.priceType);
        setEndPeriod(primaryKey.endPeriod);
        setNaProjMasterSid(primaryKey.naProjMasterSid);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new NationalAssumptionsPK(_startPeriod, _priceType, _endPeriod,
            _naProjMasterSid);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((NationalAssumptionsPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("baselinePeriod", getBaselinePeriod());
        attributes.put("frequency", getFrequency());
        attributes.put("startPeriod", getStartPeriod());
        attributes.put("forecastMethodology", getForecastMethodology());
        attributes.put("priceType", getPriceType());
        attributes.put("endPeriod", getEndPeriod());
        attributes.put("priceBasis", getPriceBasis());
        attributes.put("naProjMasterSid", getNaProjMasterSid());
        attributes.put("rollingPeriod", getRollingPeriod());
        attributes.put("baselineMethodology", getBaselineMethodology());
        attributes.put("growthRate", getGrowthRate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String baselinePeriod = (String) attributes.get("baselinePeriod");

        if (baselinePeriod != null) {
            setBaselinePeriod(baselinePeriod);
        }

        String frequency = (String) attributes.get("frequency");

        if (frequency != null) {
            setFrequency(frequency);
        }

        String startPeriod = (String) attributes.get("startPeriod");

        if (startPeriod != null) {
            setStartPeriod(startPeriod);
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

        String endPeriod = (String) attributes.get("endPeriod");

        if (endPeriod != null) {
            setEndPeriod(endPeriod);
        }

        String priceBasis = (String) attributes.get("priceBasis");

        if (priceBasis != null) {
            setPriceBasis(priceBasis);
        }

        Integer naProjMasterSid = (Integer) attributes.get("naProjMasterSid");

        if (naProjMasterSid != null) {
            setNaProjMasterSid(naProjMasterSid);
        }

        String rollingPeriod = (String) attributes.get("rollingPeriod");

        if (rollingPeriod != null) {
            setRollingPeriod(rollingPeriod);
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
    public String getBaselinePeriod() {
        return _baselinePeriod;
    }

    @Override
    public void setBaselinePeriod(String baselinePeriod) {
        _baselinePeriod = baselinePeriod;

        if (_nationalAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _nationalAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setBaselinePeriod",
                        String.class);

                method.invoke(_nationalAssumptionsRemoteModel, baselinePeriod);
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

        if (_nationalAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _nationalAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setFrequency", String.class);

                method.invoke(_nationalAssumptionsRemoteModel, frequency);
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

        if (_nationalAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _nationalAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setStartPeriod", String.class);

                method.invoke(_nationalAssumptionsRemoteModel, startPeriod);
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

        if (_nationalAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _nationalAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastMethodology",
                        String.class);

                method.invoke(_nationalAssumptionsRemoteModel,
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

        if (_nationalAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _nationalAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceType", String.class);

                method.invoke(_nationalAssumptionsRemoteModel, priceType);
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

        if (_nationalAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _nationalAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setEndPeriod", String.class);

                method.invoke(_nationalAssumptionsRemoteModel, endPeriod);
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

        if (_nationalAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _nationalAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceBasis", String.class);

                method.invoke(_nationalAssumptionsRemoteModel, priceBasis);
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

        if (_nationalAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _nationalAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setNaProjMasterSid", int.class);

                method.invoke(_nationalAssumptionsRemoteModel, naProjMasterSid);
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

        if (_nationalAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _nationalAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setRollingPeriod", String.class);

                method.invoke(_nationalAssumptionsRemoteModel, rollingPeriod);
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

        if (_nationalAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _nationalAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setBaselineMethodology",
                        String.class);

                method.invoke(_nationalAssumptionsRemoteModel,
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

        if (_nationalAssumptionsRemoteModel != null) {
            try {
                Class<?> clazz = _nationalAssumptionsRemoteModel.getClass();

                Method method = clazz.getMethod("setGrowthRate", double.class);

                method.invoke(_nationalAssumptionsRemoteModel, growthRate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getNationalAssumptionsRemoteModel() {
        return _nationalAssumptionsRemoteModel;
    }

    public void setNationalAssumptionsRemoteModel(
        BaseModel<?> nationalAssumptionsRemoteModel) {
        _nationalAssumptionsRemoteModel = nationalAssumptionsRemoteModel;
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

        Class<?> remoteModelClass = _nationalAssumptionsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_nationalAssumptionsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            NationalAssumptionsLocalServiceUtil.addNationalAssumptions(this);
        } else {
            NationalAssumptionsLocalServiceUtil.updateNationalAssumptions(this);
        }
    }

    @Override
    public NationalAssumptions toEscapedModel() {
        return (NationalAssumptions) ProxyUtil.newProxyInstance(NationalAssumptions.class.getClassLoader(),
            new Class[] { NationalAssumptions.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        NationalAssumptionsClp clone = new NationalAssumptionsClp();

        clone.setBaselinePeriod(getBaselinePeriod());
        clone.setFrequency(getFrequency());
        clone.setStartPeriod(getStartPeriod());
        clone.setForecastMethodology(getForecastMethodology());
        clone.setPriceType(getPriceType());
        clone.setEndPeriod(getEndPeriod());
        clone.setPriceBasis(getPriceBasis());
        clone.setNaProjMasterSid(getNaProjMasterSid());
        clone.setRollingPeriod(getRollingPeriod());
        clone.setBaselineMethodology(getBaselineMethodology());
        clone.setGrowthRate(getGrowthRate());

        return clone;
    }

    @Override
    public int compareTo(NationalAssumptions nationalAssumptions) {
        NationalAssumptionsPK primaryKey = nationalAssumptions.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof NationalAssumptionsClp)) {
            return false;
        }

        NationalAssumptionsClp nationalAssumptions = (NationalAssumptionsClp) obj;

        NationalAssumptionsPK primaryKey = nationalAssumptions.getPrimaryKey();

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
        StringBundler sb = new StringBundler(23);

        sb.append("{baselinePeriod=");
        sb.append(getBaselinePeriod());
        sb.append(", frequency=");
        sb.append(getFrequency());
        sb.append(", startPeriod=");
        sb.append(getStartPeriod());
        sb.append(", forecastMethodology=");
        sb.append(getForecastMethodology());
        sb.append(", priceType=");
        sb.append(getPriceType());
        sb.append(", endPeriod=");
        sb.append(getEndPeriod());
        sb.append(", priceBasis=");
        sb.append(getPriceBasis());
        sb.append(", naProjMasterSid=");
        sb.append(getNaProjMasterSid());
        sb.append(", rollingPeriod=");
        sb.append(getRollingPeriod());
        sb.append(", baselineMethodology=");
        sb.append(getBaselineMethodology());
        sb.append(", growthRate=");
        sb.append(getGrowthRate());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(37);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.NationalAssumptions");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>baselinePeriod</column-name><column-value><![CDATA[");
        sb.append(getBaselinePeriod());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>frequency</column-name><column-value><![CDATA[");
        sb.append(getFrequency());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>startPeriod</column-name><column-value><![CDATA[");
        sb.append(getStartPeriod());
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
            "<column><column-name>endPeriod</column-name><column-value><![CDATA[");
        sb.append(getEndPeriod());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceBasis</column-name><column-value><![CDATA[");
        sb.append(getPriceBasis());
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
