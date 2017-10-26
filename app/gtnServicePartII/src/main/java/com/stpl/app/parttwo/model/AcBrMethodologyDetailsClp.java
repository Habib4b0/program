package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.AcBrMethodologyDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.ClpSerializer;

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


public class AcBrMethodologyDetailsClp extends BaseModelImpl<AcBrMethodologyDetails>
    implements AcBrMethodologyDetails {
    private double _salesGrowthRate;
    private Date _methodologyStartDate;
    private String _frequency;
    private boolean _calculationFlag;
    private double _provisionGrowthRate;
    private int _salesBasis;
    private int _acBrMethodologyDetailsSid;
    private int _accClosureMasterSid;
    private Date _methodologyEndDate;
    private double _methodologyValue;
    private double _dampingFactor;
    private String _methodologyName;
    private BaseModel<?> _acBrMethodologyDetailsRemoteModel;

    public AcBrMethodologyDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return AcBrMethodologyDetails.class;
    }

    @Override
    public String getModelClassName() {
        return AcBrMethodologyDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _acBrMethodologyDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setAcBrMethodologyDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _acBrMethodologyDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("salesGrowthRate", getSalesGrowthRate());
        attributes.put("methodologyStartDate", getMethodologyStartDate());
        attributes.put("frequency", getFrequency());
        attributes.put("calculationFlag", getCalculationFlag());
        attributes.put("provisionGrowthRate", getProvisionGrowthRate());
        attributes.put("salesBasis", getSalesBasis());
        attributes.put("acBrMethodologyDetailsSid",
            getAcBrMethodologyDetailsSid());
        attributes.put("accClosureMasterSid", getAccClosureMasterSid());
        attributes.put("methodologyEndDate", getMethodologyEndDate());
        attributes.put("methodologyValue", getMethodologyValue());
        attributes.put("dampingFactor", getDampingFactor());
        attributes.put("methodologyName", getMethodologyName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double salesGrowthRate = (Double) attributes.get("salesGrowthRate");

        if (salesGrowthRate != null) {
            setSalesGrowthRate(salesGrowthRate);
        }

        Date methodologyStartDate = (Date) attributes.get(
                "methodologyStartDate");

        if (methodologyStartDate != null) {
            setMethodologyStartDate(methodologyStartDate);
        }

        String frequency = (String) attributes.get("frequency");

        if (frequency != null) {
            setFrequency(frequency);
        }

        Boolean calculationFlag = (Boolean) attributes.get("calculationFlag");

        if (calculationFlag != null) {
            setCalculationFlag(calculationFlag);
        }

        Double provisionGrowthRate = (Double) attributes.get(
                "provisionGrowthRate");

        if (provisionGrowthRate != null) {
            setProvisionGrowthRate(provisionGrowthRate);
        }

        Integer salesBasis = (Integer) attributes.get("salesBasis");

        if (salesBasis != null) {
            setSalesBasis(salesBasis);
        }

        Integer acBrMethodologyDetailsSid = (Integer) attributes.get(
                "acBrMethodologyDetailsSid");

        if (acBrMethodologyDetailsSid != null) {
            setAcBrMethodologyDetailsSid(acBrMethodologyDetailsSid);
        }

        Integer accClosureMasterSid = (Integer) attributes.get(
                "accClosureMasterSid");

        if (accClosureMasterSid != null) {
            setAccClosureMasterSid(accClosureMasterSid);
        }

        Date methodologyEndDate = (Date) attributes.get("methodologyEndDate");

        if (methodologyEndDate != null) {
            setMethodologyEndDate(methodologyEndDate);
        }

        Double methodologyValue = (Double) attributes.get("methodologyValue");

        if (methodologyValue != null) {
            setMethodologyValue(methodologyValue);
        }

        Double dampingFactor = (Double) attributes.get("dampingFactor");

        if (dampingFactor != null) {
            setDampingFactor(dampingFactor);
        }

        String methodologyName = (String) attributes.get("methodologyName");

        if (methodologyName != null) {
            setMethodologyName(methodologyName);
        }
    }

    @Override
    public double getSalesGrowthRate() {
        return _salesGrowthRate;
    }

    @Override
    public void setSalesGrowthRate(double salesGrowthRate) {
        _salesGrowthRate = salesGrowthRate;

        if (_acBrMethodologyDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _acBrMethodologyDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSalesGrowthRate",
                        double.class);

                method.invoke(_acBrMethodologyDetailsRemoteModel,
                    salesGrowthRate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getMethodologyStartDate() {
        return _methodologyStartDate;
    }

    @Override
    public void setMethodologyStartDate(Date methodologyStartDate) {
        _methodologyStartDate = methodologyStartDate;

        if (_acBrMethodologyDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _acBrMethodologyDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setMethodologyStartDate",
                        Date.class);

                method.invoke(_acBrMethodologyDetailsRemoteModel,
                    methodologyStartDate);
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

        if (_acBrMethodologyDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _acBrMethodologyDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setFrequency", String.class);

                method.invoke(_acBrMethodologyDetailsRemoteModel, frequency);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getCalculationFlag() {
        return _calculationFlag;
    }

    @Override
    public boolean isCalculationFlag() {
        return _calculationFlag;
    }

    @Override
    public void setCalculationFlag(boolean calculationFlag) {
        _calculationFlag = calculationFlag;

        if (_acBrMethodologyDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _acBrMethodologyDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCalculationFlag",
                        boolean.class);

                method.invoke(_acBrMethodologyDetailsRemoteModel,
                    calculationFlag);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getProvisionGrowthRate() {
        return _provisionGrowthRate;
    }

    @Override
    public void setProvisionGrowthRate(double provisionGrowthRate) {
        _provisionGrowthRate = provisionGrowthRate;

        if (_acBrMethodologyDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _acBrMethodologyDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setProvisionGrowthRate",
                        double.class);

                method.invoke(_acBrMethodologyDetailsRemoteModel,
                    provisionGrowthRate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getSalesBasis() {
        return _salesBasis;
    }

    @Override
    public void setSalesBasis(int salesBasis) {
        _salesBasis = salesBasis;

        if (_acBrMethodologyDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _acBrMethodologyDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSalesBasis", int.class);

                method.invoke(_acBrMethodologyDetailsRemoteModel, salesBasis);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getAcBrMethodologyDetailsSid() {
        return _acBrMethodologyDetailsSid;
    }

    @Override
    public void setAcBrMethodologyDetailsSid(int acBrMethodologyDetailsSid) {
        _acBrMethodologyDetailsSid = acBrMethodologyDetailsSid;

        if (_acBrMethodologyDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _acBrMethodologyDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setAcBrMethodologyDetailsSid",
                        int.class);

                method.invoke(_acBrMethodologyDetailsRemoteModel,
                    acBrMethodologyDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getAccClosureMasterSid() {
        return _accClosureMasterSid;
    }

    @Override
    public void setAccClosureMasterSid(int accClosureMasterSid) {
        _accClosureMasterSid = accClosureMasterSid;

        if (_acBrMethodologyDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _acBrMethodologyDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setAccClosureMasterSid",
                        int.class);

                method.invoke(_acBrMethodologyDetailsRemoteModel,
                    accClosureMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getMethodologyEndDate() {
        return _methodologyEndDate;
    }

    @Override
    public void setMethodologyEndDate(Date methodologyEndDate) {
        _methodologyEndDate = methodologyEndDate;

        if (_acBrMethodologyDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _acBrMethodologyDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setMethodologyEndDate",
                        Date.class);

                method.invoke(_acBrMethodologyDetailsRemoteModel,
                    methodologyEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getMethodologyValue() {
        return _methodologyValue;
    }

    @Override
    public void setMethodologyValue(double methodologyValue) {
        _methodologyValue = methodologyValue;

        if (_acBrMethodologyDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _acBrMethodologyDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setMethodologyValue",
                        double.class);

                method.invoke(_acBrMethodologyDetailsRemoteModel,
                    methodologyValue);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getDampingFactor() {
        return _dampingFactor;
    }

    @Override
    public void setDampingFactor(double dampingFactor) {
        _dampingFactor = dampingFactor;

        if (_acBrMethodologyDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _acBrMethodologyDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setDampingFactor", double.class);

                method.invoke(_acBrMethodologyDetailsRemoteModel, dampingFactor);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getMethodologyName() {
        return _methodologyName;
    }

    @Override
    public void setMethodologyName(String methodologyName) {
        _methodologyName = methodologyName;

        if (_acBrMethodologyDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _acBrMethodologyDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setMethodologyName",
                        String.class);

                method.invoke(_acBrMethodologyDetailsRemoteModel,
                    methodologyName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getAcBrMethodologyDetailsRemoteModel() {
        return _acBrMethodologyDetailsRemoteModel;
    }

    public void setAcBrMethodologyDetailsRemoteModel(
        BaseModel<?> acBrMethodologyDetailsRemoteModel) {
        _acBrMethodologyDetailsRemoteModel = acBrMethodologyDetailsRemoteModel;
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

        Class<?> remoteModelClass = _acBrMethodologyDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_acBrMethodologyDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            AcBrMethodologyDetailsLocalServiceUtil.addAcBrMethodologyDetails(this);
        } else {
            AcBrMethodologyDetailsLocalServiceUtil.updateAcBrMethodologyDetails(this);
        }
    }

    @Override
    public AcBrMethodologyDetails toEscapedModel() {
        return (AcBrMethodologyDetails) ProxyUtil.newProxyInstance(AcBrMethodologyDetails.class.getClassLoader(),
            new Class[] { AcBrMethodologyDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        AcBrMethodologyDetailsClp clone = new AcBrMethodologyDetailsClp();

        clone.setSalesGrowthRate(getSalesGrowthRate());
        clone.setMethodologyStartDate(getMethodologyStartDate());
        clone.setFrequency(getFrequency());
        clone.setCalculationFlag(getCalculationFlag());
        clone.setProvisionGrowthRate(getProvisionGrowthRate());
        clone.setSalesBasis(getSalesBasis());
        clone.setAcBrMethodologyDetailsSid(getAcBrMethodologyDetailsSid());
        clone.setAccClosureMasterSid(getAccClosureMasterSid());
        clone.setMethodologyEndDate(getMethodologyEndDate());
        clone.setMethodologyValue(getMethodologyValue());
        clone.setDampingFactor(getDampingFactor());
        clone.setMethodologyName(getMethodologyName());

        return clone;
    }

    @Override
    public int compareTo(AcBrMethodologyDetails acBrMethodologyDetails) {
        int primaryKey = acBrMethodologyDetails.getPrimaryKey();

        if (getPrimaryKey() < primaryKey) {
            return -1;
        } else if (getPrimaryKey() > primaryKey) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof AcBrMethodologyDetailsClp)) {
            return false;
        }

        AcBrMethodologyDetailsClp acBrMethodologyDetails = (AcBrMethodologyDetailsClp) obj;

        int primaryKey = acBrMethodologyDetails.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getPrimaryKey();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(25);

        sb.append("{salesGrowthRate=");
        sb.append(getSalesGrowthRate());
        sb.append(", methodologyStartDate=");
        sb.append(getMethodologyStartDate());
        sb.append(", frequency=");
        sb.append(getFrequency());
        sb.append(", calculationFlag=");
        sb.append(getCalculationFlag());
        sb.append(", provisionGrowthRate=");
        sb.append(getProvisionGrowthRate());
        sb.append(", salesBasis=");
        sb.append(getSalesBasis());
        sb.append(", acBrMethodologyDetailsSid=");
        sb.append(getAcBrMethodologyDetailsSid());
        sb.append(", accClosureMasterSid=");
        sb.append(getAccClosureMasterSid());
        sb.append(", methodologyEndDate=");
        sb.append(getMethodologyEndDate());
        sb.append(", methodologyValue=");
        sb.append(getMethodologyValue());
        sb.append(", dampingFactor=");
        sb.append(getDampingFactor());
        sb.append(", methodologyName=");
        sb.append(getMethodologyName());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(40);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.AcBrMethodologyDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>salesGrowthRate</column-name><column-value><![CDATA[");
        sb.append(getSalesGrowthRate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>methodologyStartDate</column-name><column-value><![CDATA[");
        sb.append(getMethodologyStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>frequency</column-name><column-value><![CDATA[");
        sb.append(getFrequency());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>calculationFlag</column-name><column-value><![CDATA[");
        sb.append(getCalculationFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>provisionGrowthRate</column-name><column-value><![CDATA[");
        sb.append(getProvisionGrowthRate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>salesBasis</column-name><column-value><![CDATA[");
        sb.append(getSalesBasis());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>acBrMethodologyDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getAcBrMethodologyDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accClosureMasterSid</column-name><column-value><![CDATA[");
        sb.append(getAccClosureMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>methodologyEndDate</column-name><column-value><![CDATA[");
        sb.append(getMethodologyEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>methodologyValue</column-name><column-value><![CDATA[");
        sb.append(getMethodologyValue());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>dampingFactor</column-name><column-value><![CDATA[");
        sb.append(getDampingFactor());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>methodologyName</column-name><column-value><![CDATA[");
        sb.append(getMethodologyName());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
