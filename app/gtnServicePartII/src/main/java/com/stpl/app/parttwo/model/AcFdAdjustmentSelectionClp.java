package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.AcFdAdjustmentSelectionLocalServiceUtil;
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


public class AcFdAdjustmentSelectionClp extends BaseModelImpl<AcFdAdjustmentSelection>
    implements AcFdAdjustmentSelection {
    private String _methodologyStartDate;
    private int _allocationMethod;
    private Date _startDate;
    private double _totalFixedDollarAdj;
    private boolean _calculationFlag;
    private double _rateCorrection;
    private int _businessDays;
    private Date _glImpactDate;
    private int _salesBasis;
    private boolean _releaseType;
    private int _accClosureMasterSid;
    private double _releaseAmount;
    private double _suggestedAdj;
    private String _methodologyEndDate;
    private BaseModel<?> _acFdAdjustmentSelectionRemoteModel;

    public AcFdAdjustmentSelectionClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return AcFdAdjustmentSelection.class;
    }

    @Override
    public String getModelClassName() {
        return AcFdAdjustmentSelection.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _accClosureMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setAccClosureMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _accClosureMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("methodologyStartDate", getMethodologyStartDate());
        attributes.put("allocationMethod", getAllocationMethod());
        attributes.put("startDate", getStartDate());
        attributes.put("totalFixedDollarAdj", getTotalFixedDollarAdj());
        attributes.put("calculationFlag", getCalculationFlag());
        attributes.put("rateCorrection", getRateCorrection());
        attributes.put("businessDays", getBusinessDays());
        attributes.put("glImpactDate", getGlImpactDate());
        attributes.put("salesBasis", getSalesBasis());
        attributes.put("releaseType", getReleaseType());
        attributes.put("accClosureMasterSid", getAccClosureMasterSid());
        attributes.put("releaseAmount", getReleaseAmount());
        attributes.put("suggestedAdj", getSuggestedAdj());
        attributes.put("methodologyEndDate", getMethodologyEndDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String methodologyStartDate = (String) attributes.get(
                "methodologyStartDate");

        if (methodologyStartDate != null) {
            setMethodologyStartDate(methodologyStartDate);
        }

        Integer allocationMethod = (Integer) attributes.get("allocationMethod");

        if (allocationMethod != null) {
            setAllocationMethod(allocationMethod);
        }

        Date startDate = (Date) attributes.get("startDate");

        if (startDate != null) {
            setStartDate(startDate);
        }

        Double totalFixedDollarAdj = (Double) attributes.get(
                "totalFixedDollarAdj");

        if (totalFixedDollarAdj != null) {
            setTotalFixedDollarAdj(totalFixedDollarAdj);
        }

        Boolean calculationFlag = (Boolean) attributes.get("calculationFlag");

        if (calculationFlag != null) {
            setCalculationFlag(calculationFlag);
        }

        Double rateCorrection = (Double) attributes.get("rateCorrection");

        if (rateCorrection != null) {
            setRateCorrection(rateCorrection);
        }

        Integer businessDays = (Integer) attributes.get("businessDays");

        if (businessDays != null) {
            setBusinessDays(businessDays);
        }

        Date glImpactDate = (Date) attributes.get("glImpactDate");

        if (glImpactDate != null) {
            setGlImpactDate(glImpactDate);
        }

        Integer salesBasis = (Integer) attributes.get("salesBasis");

        if (salesBasis != null) {
            setSalesBasis(salesBasis);
        }

        Boolean releaseType = (Boolean) attributes.get("releaseType");

        if (releaseType != null) {
            setReleaseType(releaseType);
        }

        Integer accClosureMasterSid = (Integer) attributes.get(
                "accClosureMasterSid");

        if (accClosureMasterSid != null) {
            setAccClosureMasterSid(accClosureMasterSid);
        }

        Double releaseAmount = (Double) attributes.get("releaseAmount");

        if (releaseAmount != null) {
            setReleaseAmount(releaseAmount);
        }

        Double suggestedAdj = (Double) attributes.get("suggestedAdj");

        if (suggestedAdj != null) {
            setSuggestedAdj(suggestedAdj);
        }

        String methodologyEndDate = (String) attributes.get(
                "methodologyEndDate");

        if (methodologyEndDate != null) {
            setMethodologyEndDate(methodologyEndDate);
        }
    }

    @Override
    public String getMethodologyStartDate() {
        return _methodologyStartDate;
    }

    @Override
    public void setMethodologyStartDate(String methodologyStartDate) {
        _methodologyStartDate = methodologyStartDate;

        if (_acFdAdjustmentSelectionRemoteModel != null) {
            try {
                Class<?> clazz = _acFdAdjustmentSelectionRemoteModel.getClass();

                Method method = clazz.getMethod("setMethodologyStartDate",
                        String.class);

                method.invoke(_acFdAdjustmentSelectionRemoteModel,
                    methodologyStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getAllocationMethod() {
        return _allocationMethod;
    }

    @Override
    public void setAllocationMethod(int allocationMethod) {
        _allocationMethod = allocationMethod;

        if (_acFdAdjustmentSelectionRemoteModel != null) {
            try {
                Class<?> clazz = _acFdAdjustmentSelectionRemoteModel.getClass();

                Method method = clazz.getMethod("setAllocationMethod", int.class);

                method.invoke(_acFdAdjustmentSelectionRemoteModel,
                    allocationMethod);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getStartDate() {
        return _startDate;
    }

    @Override
    public void setStartDate(Date startDate) {
        _startDate = startDate;

        if (_acFdAdjustmentSelectionRemoteModel != null) {
            try {
                Class<?> clazz = _acFdAdjustmentSelectionRemoteModel.getClass();

                Method method = clazz.getMethod("setStartDate", Date.class);

                method.invoke(_acFdAdjustmentSelectionRemoteModel, startDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getTotalFixedDollarAdj() {
        return _totalFixedDollarAdj;
    }

    @Override
    public void setTotalFixedDollarAdj(double totalFixedDollarAdj) {
        _totalFixedDollarAdj = totalFixedDollarAdj;

        if (_acFdAdjustmentSelectionRemoteModel != null) {
            try {
                Class<?> clazz = _acFdAdjustmentSelectionRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalFixedDollarAdj",
                        double.class);

                method.invoke(_acFdAdjustmentSelectionRemoteModel,
                    totalFixedDollarAdj);
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

        if (_acFdAdjustmentSelectionRemoteModel != null) {
            try {
                Class<?> clazz = _acFdAdjustmentSelectionRemoteModel.getClass();

                Method method = clazz.getMethod("setCalculationFlag",
                        boolean.class);

                method.invoke(_acFdAdjustmentSelectionRemoteModel,
                    calculationFlag);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getRateCorrection() {
        return _rateCorrection;
    }

    @Override
    public void setRateCorrection(double rateCorrection) {
        _rateCorrection = rateCorrection;

        if (_acFdAdjustmentSelectionRemoteModel != null) {
            try {
                Class<?> clazz = _acFdAdjustmentSelectionRemoteModel.getClass();

                Method method = clazz.getMethod("setRateCorrection",
                        double.class);

                method.invoke(_acFdAdjustmentSelectionRemoteModel,
                    rateCorrection);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getBusinessDays() {
        return _businessDays;
    }

    @Override
    public void setBusinessDays(int businessDays) {
        _businessDays = businessDays;

        if (_acFdAdjustmentSelectionRemoteModel != null) {
            try {
                Class<?> clazz = _acFdAdjustmentSelectionRemoteModel.getClass();

                Method method = clazz.getMethod("setBusinessDays", int.class);

                method.invoke(_acFdAdjustmentSelectionRemoteModel, businessDays);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getGlImpactDate() {
        return _glImpactDate;
    }

    @Override
    public void setGlImpactDate(Date glImpactDate) {
        _glImpactDate = glImpactDate;

        if (_acFdAdjustmentSelectionRemoteModel != null) {
            try {
                Class<?> clazz = _acFdAdjustmentSelectionRemoteModel.getClass();

                Method method = clazz.getMethod("setGlImpactDate", Date.class);

                method.invoke(_acFdAdjustmentSelectionRemoteModel, glImpactDate);
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

        if (_acFdAdjustmentSelectionRemoteModel != null) {
            try {
                Class<?> clazz = _acFdAdjustmentSelectionRemoteModel.getClass();

                Method method = clazz.getMethod("setSalesBasis", int.class);

                method.invoke(_acFdAdjustmentSelectionRemoteModel, salesBasis);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getReleaseType() {
        return _releaseType;
    }

    @Override
    public boolean isReleaseType() {
        return _releaseType;
    }

    @Override
    public void setReleaseType(boolean releaseType) {
        _releaseType = releaseType;

        if (_acFdAdjustmentSelectionRemoteModel != null) {
            try {
                Class<?> clazz = _acFdAdjustmentSelectionRemoteModel.getClass();

                Method method = clazz.getMethod("setReleaseType", boolean.class);

                method.invoke(_acFdAdjustmentSelectionRemoteModel, releaseType);
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

        if (_acFdAdjustmentSelectionRemoteModel != null) {
            try {
                Class<?> clazz = _acFdAdjustmentSelectionRemoteModel.getClass();

                Method method = clazz.getMethod("setAccClosureMasterSid",
                        int.class);

                method.invoke(_acFdAdjustmentSelectionRemoteModel,
                    accClosureMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getReleaseAmount() {
        return _releaseAmount;
    }

    @Override
    public void setReleaseAmount(double releaseAmount) {
        _releaseAmount = releaseAmount;

        if (_acFdAdjustmentSelectionRemoteModel != null) {
            try {
                Class<?> clazz = _acFdAdjustmentSelectionRemoteModel.getClass();

                Method method = clazz.getMethod("setReleaseAmount", double.class);

                method.invoke(_acFdAdjustmentSelectionRemoteModel, releaseAmount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getSuggestedAdj() {
        return _suggestedAdj;
    }

    @Override
    public void setSuggestedAdj(double suggestedAdj) {
        _suggestedAdj = suggestedAdj;

        if (_acFdAdjustmentSelectionRemoteModel != null) {
            try {
                Class<?> clazz = _acFdAdjustmentSelectionRemoteModel.getClass();

                Method method = clazz.getMethod("setSuggestedAdj", double.class);

                method.invoke(_acFdAdjustmentSelectionRemoteModel, suggestedAdj);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getMethodologyEndDate() {
        return _methodologyEndDate;
    }

    @Override
    public void setMethodologyEndDate(String methodologyEndDate) {
        _methodologyEndDate = methodologyEndDate;

        if (_acFdAdjustmentSelectionRemoteModel != null) {
            try {
                Class<?> clazz = _acFdAdjustmentSelectionRemoteModel.getClass();

                Method method = clazz.getMethod("setMethodologyEndDate",
                        String.class);

                method.invoke(_acFdAdjustmentSelectionRemoteModel,
                    methodologyEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getAcFdAdjustmentSelectionRemoteModel() {
        return _acFdAdjustmentSelectionRemoteModel;
    }

    public void setAcFdAdjustmentSelectionRemoteModel(
        BaseModel<?> acFdAdjustmentSelectionRemoteModel) {
        _acFdAdjustmentSelectionRemoteModel = acFdAdjustmentSelectionRemoteModel;
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

        Class<?> remoteModelClass = _acFdAdjustmentSelectionRemoteModel.getClass();

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

        Object returnValue = method.invoke(_acFdAdjustmentSelectionRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            AcFdAdjustmentSelectionLocalServiceUtil.addAcFdAdjustmentSelection(this);
        } else {
            AcFdAdjustmentSelectionLocalServiceUtil.updateAcFdAdjustmentSelection(this);
        }
    }

    @Override
    public AcFdAdjustmentSelection toEscapedModel() {
        return (AcFdAdjustmentSelection) ProxyUtil.newProxyInstance(AcFdAdjustmentSelection.class.getClassLoader(),
            new Class[] { AcFdAdjustmentSelection.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        AcFdAdjustmentSelectionClp clone = new AcFdAdjustmentSelectionClp();

        clone.setMethodologyStartDate(getMethodologyStartDate());
        clone.setAllocationMethod(getAllocationMethod());
        clone.setStartDate(getStartDate());
        clone.setTotalFixedDollarAdj(getTotalFixedDollarAdj());
        clone.setCalculationFlag(getCalculationFlag());
        clone.setRateCorrection(getRateCorrection());
        clone.setBusinessDays(getBusinessDays());
        clone.setGlImpactDate(getGlImpactDate());
        clone.setSalesBasis(getSalesBasis());
        clone.setReleaseType(getReleaseType());
        clone.setAccClosureMasterSid(getAccClosureMasterSid());
        clone.setReleaseAmount(getReleaseAmount());
        clone.setSuggestedAdj(getSuggestedAdj());
        clone.setMethodologyEndDate(getMethodologyEndDate());

        return clone;
    }

    @Override
    public int compareTo(AcFdAdjustmentSelection acFdAdjustmentSelection) {
        int primaryKey = acFdAdjustmentSelection.getPrimaryKey();

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

        if (!(obj instanceof AcFdAdjustmentSelectionClp)) {
            return false;
        }

        AcFdAdjustmentSelectionClp acFdAdjustmentSelection = (AcFdAdjustmentSelectionClp) obj;

        int primaryKey = acFdAdjustmentSelection.getPrimaryKey();

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
        StringBundler sb = new StringBundler(29);

        sb.append("{methodologyStartDate=");
        sb.append(getMethodologyStartDate());
        sb.append(", allocationMethod=");
        sb.append(getAllocationMethod());
        sb.append(", startDate=");
        sb.append(getStartDate());
        sb.append(", totalFixedDollarAdj=");
        sb.append(getTotalFixedDollarAdj());
        sb.append(", calculationFlag=");
        sb.append(getCalculationFlag());
        sb.append(", rateCorrection=");
        sb.append(getRateCorrection());
        sb.append(", businessDays=");
        sb.append(getBusinessDays());
        sb.append(", glImpactDate=");
        sb.append(getGlImpactDate());
        sb.append(", salesBasis=");
        sb.append(getSalesBasis());
        sb.append(", releaseType=");
        sb.append(getReleaseType());
        sb.append(", accClosureMasterSid=");
        sb.append(getAccClosureMasterSid());
        sb.append(", releaseAmount=");
        sb.append(getReleaseAmount());
        sb.append(", suggestedAdj=");
        sb.append(getSuggestedAdj());
        sb.append(", methodologyEndDate=");
        sb.append(getMethodologyEndDate());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(46);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.AcFdAdjustmentSelection");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>methodologyStartDate</column-name><column-value><![CDATA[");
        sb.append(getMethodologyStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>allocationMethod</column-name><column-value><![CDATA[");
        sb.append(getAllocationMethod());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>startDate</column-name><column-value><![CDATA[");
        sb.append(getStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>totalFixedDollarAdj</column-name><column-value><![CDATA[");
        sb.append(getTotalFixedDollarAdj());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>calculationFlag</column-name><column-value><![CDATA[");
        sb.append(getCalculationFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rateCorrection</column-name><column-value><![CDATA[");
        sb.append(getRateCorrection());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>businessDays</column-name><column-value><![CDATA[");
        sb.append(getBusinessDays());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>glImpactDate</column-name><column-value><![CDATA[");
        sb.append(getGlImpactDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>salesBasis</column-name><column-value><![CDATA[");
        sb.append(getSalesBasis());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>releaseType</column-name><column-value><![CDATA[");
        sb.append(getReleaseType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accClosureMasterSid</column-name><column-value><![CDATA[");
        sb.append(getAccClosureMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>releaseAmount</column-name><column-value><![CDATA[");
        sb.append(getReleaseAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>suggestedAdj</column-name><column-value><![CDATA[");
        sb.append(getSuggestedAdj());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>methodologyEndDate</column-name><column-value><![CDATA[");
        sb.append(getMethodologyEndDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
