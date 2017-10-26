package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.ForecastConfigLocalServiceUtil;

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


public class ForecastConfigClp extends BaseModelImpl<ForecastConfig>
    implements ForecastConfig {
    private boolean _processType;
    private Date _toDate;
    private int _versionNo;
    private int _forecastConfigSid;
    private Date _modifiedDate;
    private Date _fromDate;
    private int _projValue;
    private int _createdBy;
    private Date _createdDate;
    private int _projFreq;
    private int _histValue;
    private int _businessProcessType;
    private int _modifiedBy;
    private int _histFreq;
    private Date _activeStartDate;
    private Date _activeEndDate;
    private boolean _processMode;
    private Date _historicalDataIntervalFrom;
    private Date _historicalTimePeriodFrom;
    private int _projHistFreq;
    private Date _futureTimePeriodFrom;
    private Date _historicalDataIntervalTo;
    private int _projHistValue;
    private BaseModel<?> _forecastConfigRemoteModel;

    public ForecastConfigClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ForecastConfig.class;
    }

    @Override
    public String getModelClassName() {
        return ForecastConfig.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _forecastConfigSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setForecastConfigSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _forecastConfigSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("processType", getProcessType());
        attributes.put("toDate", getToDate());
        attributes.put("versionNo", getVersionNo());
        attributes.put("forecastConfigSid", getForecastConfigSid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("fromDate", getFromDate());
        attributes.put("projValue", getProjValue());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("projFreq", getProjFreq());
        attributes.put("histValue", getHistValue());
        attributes.put("businessProcessType", getBusinessProcessType());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("histFreq", getHistFreq());
        attributes.put("activeStartDate", getActiveStartDate());
        attributes.put("activeEndDate", getActiveEndDate());
        attributes.put("processMode", getProcessMode());
        attributes.put("historicalDataIntervalFrom",
            getHistoricalDataIntervalFrom());
        attributes.put("historicalTimePeriodFrom", getHistoricalTimePeriodFrom());
        attributes.put("projHistFreq", getProjHistFreq());
        attributes.put("futureTimePeriodFrom", getFutureTimePeriodFrom());
        attributes.put("historicalDataIntervalTo", getHistoricalDataIntervalTo());
        attributes.put("projHistValue", getProjHistValue());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Boolean processType = (Boolean) attributes.get("processType");

        if (processType != null) {
            setProcessType(processType);
        }

        Date toDate = (Date) attributes.get("toDate");

        if (toDate != null) {
            setToDate(toDate);
        }

        Integer versionNo = (Integer) attributes.get("versionNo");

        if (versionNo != null) {
            setVersionNo(versionNo);
        }

        Integer forecastConfigSid = (Integer) attributes.get(
                "forecastConfigSid");

        if (forecastConfigSid != null) {
            setForecastConfigSid(forecastConfigSid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Date fromDate = (Date) attributes.get("fromDate");

        if (fromDate != null) {
            setFromDate(fromDate);
        }

        Integer projValue = (Integer) attributes.get("projValue");

        if (projValue != null) {
            setProjValue(projValue);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer projFreq = (Integer) attributes.get("projFreq");

        if (projFreq != null) {
            setProjFreq(projFreq);
        }

        Integer histValue = (Integer) attributes.get("histValue");

        if (histValue != null) {
            setHistValue(histValue);
        }

        Integer businessProcessType = (Integer) attributes.get(
                "businessProcessType");

        if (businessProcessType != null) {
            setBusinessProcessType(businessProcessType);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Integer histFreq = (Integer) attributes.get("histFreq");

        if (histFreq != null) {
            setHistFreq(histFreq);
        }

        Date activeStartDate = (Date) attributes.get("activeStartDate");

        if (activeStartDate != null) {
            setActiveStartDate(activeStartDate);
        }

        Date activeEndDate = (Date) attributes.get("activeEndDate");

        if (activeEndDate != null) {
            setActiveEndDate(activeEndDate);
        }

        Boolean processMode = (Boolean) attributes.get("processMode");

        if (processMode != null) {
            setProcessMode(processMode);
        }

        Date historicalDataIntervalFrom = (Date) attributes.get(
                "historicalDataIntervalFrom");

        if (historicalDataIntervalFrom != null) {
            setHistoricalDataIntervalFrom(historicalDataIntervalFrom);
        }

        Date historicalTimePeriodFrom = (Date) attributes.get(
                "historicalTimePeriodFrom");

        if (historicalTimePeriodFrom != null) {
            setHistoricalTimePeriodFrom(historicalTimePeriodFrom);
        }

        Integer projHistFreq = (Integer) attributes.get("projHistFreq");

        if (projHistFreq != null) {
            setProjHistFreq(projHistFreq);
        }

        Date futureTimePeriodFrom = (Date) attributes.get(
                "futureTimePeriodFrom");

        if (futureTimePeriodFrom != null) {
            setFutureTimePeriodFrom(futureTimePeriodFrom);
        }

        Date historicalDataIntervalTo = (Date) attributes.get(
                "historicalDataIntervalTo");

        if (historicalDataIntervalTo != null) {
            setHistoricalDataIntervalTo(historicalDataIntervalTo);
        }

        Integer projHistValue = (Integer) attributes.get("projHistValue");

        if (projHistValue != null) {
            setProjHistValue(projHistValue);
        }
    }

    @Override
    public boolean getProcessType() {
        return _processType;
    }

    @Override
    public boolean isProcessType() {
        return _processType;
    }

    @Override
    public void setProcessType(boolean processType) {
        _processType = processType;

        if (_forecastConfigRemoteModel != null) {
            try {
                Class<?> clazz = _forecastConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setProcessType", boolean.class);

                method.invoke(_forecastConfigRemoteModel, processType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getToDate() {
        return _toDate;
    }

    @Override
    public void setToDate(Date toDate) {
        _toDate = toDate;

        if (_forecastConfigRemoteModel != null) {
            try {
                Class<?> clazz = _forecastConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setToDate", Date.class);

                method.invoke(_forecastConfigRemoteModel, toDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getVersionNo() {
        return _versionNo;
    }

    @Override
    public void setVersionNo(int versionNo) {
        _versionNo = versionNo;

        if (_forecastConfigRemoteModel != null) {
            try {
                Class<?> clazz = _forecastConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setVersionNo", int.class);

                method.invoke(_forecastConfigRemoteModel, versionNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getForecastConfigSid() {
        return _forecastConfigSid;
    }

    @Override
    public void setForecastConfigSid(int forecastConfigSid) {
        _forecastConfigSid = forecastConfigSid;

        if (_forecastConfigRemoteModel != null) {
            try {
                Class<?> clazz = _forecastConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastConfigSid",
                        int.class);

                method.invoke(_forecastConfigRemoteModel, forecastConfigSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getModifiedDate() {
        return _modifiedDate;
    }

    @Override
    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;

        if (_forecastConfigRemoteModel != null) {
            try {
                Class<?> clazz = _forecastConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_forecastConfigRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getFromDate() {
        return _fromDate;
    }

    @Override
    public void setFromDate(Date fromDate) {
        _fromDate = fromDate;

        if (_forecastConfigRemoteModel != null) {
            try {
                Class<?> clazz = _forecastConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setFromDate", Date.class);

                method.invoke(_forecastConfigRemoteModel, fromDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getProjValue() {
        return _projValue;
    }

    @Override
    public void setProjValue(int projValue) {
        _projValue = projValue;

        if (_forecastConfigRemoteModel != null) {
            try {
                Class<?> clazz = _forecastConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setProjValue", int.class);

                method.invoke(_forecastConfigRemoteModel, projValue);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCreatedBy() {
        return _createdBy;
    }

    @Override
    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;

        if (_forecastConfigRemoteModel != null) {
            try {
                Class<?> clazz = _forecastConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_forecastConfigRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCreatedDate() {
        return _createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;

        if (_forecastConfigRemoteModel != null) {
            try {
                Class<?> clazz = _forecastConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_forecastConfigRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getProjFreq() {
        return _projFreq;
    }

    @Override
    public void setProjFreq(int projFreq) {
        _projFreq = projFreq;

        if (_forecastConfigRemoteModel != null) {
            try {
                Class<?> clazz = _forecastConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setProjFreq", int.class);

                method.invoke(_forecastConfigRemoteModel, projFreq);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getHistValue() {
        return _histValue;
    }

    @Override
    public void setHistValue(int histValue) {
        _histValue = histValue;

        if (_forecastConfigRemoteModel != null) {
            try {
                Class<?> clazz = _forecastConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setHistValue", int.class);

                method.invoke(_forecastConfigRemoteModel, histValue);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getBusinessProcessType() {
        return _businessProcessType;
    }

    @Override
    public void setBusinessProcessType(int businessProcessType) {
        _businessProcessType = businessProcessType;

        if (_forecastConfigRemoteModel != null) {
            try {
                Class<?> clazz = _forecastConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setBusinessProcessType",
                        int.class);

                method.invoke(_forecastConfigRemoteModel, businessProcessType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getModifiedBy() {
        return _modifiedBy;
    }

    @Override
    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;

        if (_forecastConfigRemoteModel != null) {
            try {
                Class<?> clazz = _forecastConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_forecastConfigRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getHistFreq() {
        return _histFreq;
    }

    @Override
    public void setHistFreq(int histFreq) {
        _histFreq = histFreq;

        if (_forecastConfigRemoteModel != null) {
            try {
                Class<?> clazz = _forecastConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setHistFreq", int.class);

                method.invoke(_forecastConfigRemoteModel, histFreq);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getActiveStartDate() {
        return _activeStartDate;
    }

    @Override
    public void setActiveStartDate(Date activeStartDate) {
        _activeStartDate = activeStartDate;

        if (_forecastConfigRemoteModel != null) {
            try {
                Class<?> clazz = _forecastConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setActiveStartDate", Date.class);

                method.invoke(_forecastConfigRemoteModel, activeStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getActiveEndDate() {
        return _activeEndDate;
    }

    @Override
    public void setActiveEndDate(Date activeEndDate) {
        _activeEndDate = activeEndDate;

        if (_forecastConfigRemoteModel != null) {
            try {
                Class<?> clazz = _forecastConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setActiveEndDate", Date.class);

                method.invoke(_forecastConfigRemoteModel, activeEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getProcessMode() {
        return _processMode;
    }

    @Override
    public boolean isProcessMode() {
        return _processMode;
    }

    @Override
    public void setProcessMode(boolean processMode) {
        _processMode = processMode;

        if (_forecastConfigRemoteModel != null) {
            try {
                Class<?> clazz = _forecastConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setProcessMode", boolean.class);

                method.invoke(_forecastConfigRemoteModel, processMode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getHistoricalDataIntervalFrom() {
        return _historicalDataIntervalFrom;
    }

    @Override
    public void setHistoricalDataIntervalFrom(Date historicalDataIntervalFrom) {
        _historicalDataIntervalFrom = historicalDataIntervalFrom;

        if (_forecastConfigRemoteModel != null) {
            try {
                Class<?> clazz = _forecastConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setHistoricalDataIntervalFrom",
                        Date.class);

                method.invoke(_forecastConfigRemoteModel,
                    historicalDataIntervalFrom);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getHistoricalTimePeriodFrom() {
        return _historicalTimePeriodFrom;
    }

    @Override
    public void setHistoricalTimePeriodFrom(Date historicalTimePeriodFrom) {
        _historicalTimePeriodFrom = historicalTimePeriodFrom;

        if (_forecastConfigRemoteModel != null) {
            try {
                Class<?> clazz = _forecastConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setHistoricalTimePeriodFrom",
                        Date.class);

                method.invoke(_forecastConfigRemoteModel,
                    historicalTimePeriodFrom);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getProjHistFreq() {
        return _projHistFreq;
    }

    @Override
    public void setProjHistFreq(int projHistFreq) {
        _projHistFreq = projHistFreq;

        if (_forecastConfigRemoteModel != null) {
            try {
                Class<?> clazz = _forecastConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setProjHistFreq", int.class);

                method.invoke(_forecastConfigRemoteModel, projHistFreq);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getFutureTimePeriodFrom() {
        return _futureTimePeriodFrom;
    }

    @Override
    public void setFutureTimePeriodFrom(Date futureTimePeriodFrom) {
        _futureTimePeriodFrom = futureTimePeriodFrom;

        if (_forecastConfigRemoteModel != null) {
            try {
                Class<?> clazz = _forecastConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setFutureTimePeriodFrom",
                        Date.class);

                method.invoke(_forecastConfigRemoteModel, futureTimePeriodFrom);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getHistoricalDataIntervalTo() {
        return _historicalDataIntervalTo;
    }

    @Override
    public void setHistoricalDataIntervalTo(Date historicalDataIntervalTo) {
        _historicalDataIntervalTo = historicalDataIntervalTo;

        if (_forecastConfigRemoteModel != null) {
            try {
                Class<?> clazz = _forecastConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setHistoricalDataIntervalTo",
                        Date.class);

                method.invoke(_forecastConfigRemoteModel,
                    historicalDataIntervalTo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getProjHistValue() {
        return _projHistValue;
    }

    @Override
    public void setProjHistValue(int projHistValue) {
        _projHistValue = projHistValue;

        if (_forecastConfigRemoteModel != null) {
            try {
                Class<?> clazz = _forecastConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setProjHistValue", int.class);

                method.invoke(_forecastConfigRemoteModel, projHistValue);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getForecastConfigRemoteModel() {
        return _forecastConfigRemoteModel;
    }

    public void setForecastConfigRemoteModel(
        BaseModel<?> forecastConfigRemoteModel) {
        _forecastConfigRemoteModel = forecastConfigRemoteModel;
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

        Class<?> remoteModelClass = _forecastConfigRemoteModel.getClass();

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

        Object returnValue = method.invoke(_forecastConfigRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ForecastConfigLocalServiceUtil.addForecastConfig(this);
        } else {
            ForecastConfigLocalServiceUtil.updateForecastConfig(this);
        }
    }

    @Override
    public ForecastConfig toEscapedModel() {
        return (ForecastConfig) ProxyUtil.newProxyInstance(ForecastConfig.class.getClassLoader(),
            new Class[] { ForecastConfig.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ForecastConfigClp clone = new ForecastConfigClp();

        clone.setProcessType(getProcessType());
        clone.setToDate(getToDate());
        clone.setVersionNo(getVersionNo());
        clone.setForecastConfigSid(getForecastConfigSid());
        clone.setModifiedDate(getModifiedDate());
        clone.setFromDate(getFromDate());
        clone.setProjValue(getProjValue());
        clone.setCreatedBy(getCreatedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setProjFreq(getProjFreq());
        clone.setHistValue(getHistValue());
        clone.setBusinessProcessType(getBusinessProcessType());
        clone.setModifiedBy(getModifiedBy());
        clone.setHistFreq(getHistFreq());
        clone.setActiveStartDate(getActiveStartDate());
        clone.setActiveEndDate(getActiveEndDate());
        clone.setProcessMode(getProcessMode());
        clone.setHistoricalDataIntervalFrom(getHistoricalDataIntervalFrom());
        clone.setHistoricalTimePeriodFrom(getHistoricalTimePeriodFrom());
        clone.setProjHistFreq(getProjHistFreq());
        clone.setFutureTimePeriodFrom(getFutureTimePeriodFrom());
        clone.setHistoricalDataIntervalTo(getHistoricalDataIntervalTo());
        clone.setProjHistValue(getProjHistValue());

        return clone;
    }

    @Override
    public int compareTo(ForecastConfig forecastConfig) {
        int primaryKey = forecastConfig.getPrimaryKey();

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

        if (!(obj instanceof ForecastConfigClp)) {
            return false;
        }

        ForecastConfigClp forecastConfig = (ForecastConfigClp) obj;

        int primaryKey = forecastConfig.getPrimaryKey();

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
        StringBundler sb = new StringBundler(47);

        sb.append("{processType=");
        sb.append(getProcessType());
        sb.append(", toDate=");
        sb.append(getToDate());
        sb.append(", versionNo=");
        sb.append(getVersionNo());
        sb.append(", forecastConfigSid=");
        sb.append(getForecastConfigSid());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", fromDate=");
        sb.append(getFromDate());
        sb.append(", projValue=");
        sb.append(getProjValue());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", projFreq=");
        sb.append(getProjFreq());
        sb.append(", histValue=");
        sb.append(getHistValue());
        sb.append(", businessProcessType=");
        sb.append(getBusinessProcessType());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", histFreq=");
        sb.append(getHistFreq());
        sb.append(", activeStartDate=");
        sb.append(getActiveStartDate());
        sb.append(", activeEndDate=");
        sb.append(getActiveEndDate());
        sb.append(", processMode=");
        sb.append(getProcessMode());
        sb.append(", historicalDataIntervalFrom=");
        sb.append(getHistoricalDataIntervalFrom());
        sb.append(", historicalTimePeriodFrom=");
        sb.append(getHistoricalTimePeriodFrom());
        sb.append(", projHistFreq=");
        sb.append(getProjHistFreq());
        sb.append(", futureTimePeriodFrom=");
        sb.append(getFutureTimePeriodFrom());
        sb.append(", historicalDataIntervalTo=");
        sb.append(getHistoricalDataIntervalTo());
        sb.append(", projHistValue=");
        sb.append(getProjHistValue());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(73);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ForecastConfig");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>processType</column-name><column-value><![CDATA[");
        sb.append(getProcessType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>toDate</column-name><column-value><![CDATA[");
        sb.append(getToDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>versionNo</column-name><column-value><![CDATA[");
        sb.append(getVersionNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastConfigSid</column-name><column-value><![CDATA[");
        sb.append(getForecastConfigSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fromDate</column-name><column-value><![CDATA[");
        sb.append(getFromDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projValue</column-name><column-value><![CDATA[");
        sb.append(getProjValue());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projFreq</column-name><column-value><![CDATA[");
        sb.append(getProjFreq());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>histValue</column-name><column-value><![CDATA[");
        sb.append(getHistValue());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>businessProcessType</column-name><column-value><![CDATA[");
        sb.append(getBusinessProcessType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>histFreq</column-name><column-value><![CDATA[");
        sb.append(getHistFreq());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>activeStartDate</column-name><column-value><![CDATA[");
        sb.append(getActiveStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>activeEndDate</column-name><column-value><![CDATA[");
        sb.append(getActiveEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>processMode</column-name><column-value><![CDATA[");
        sb.append(getProcessMode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>historicalDataIntervalFrom</column-name><column-value><![CDATA[");
        sb.append(getHistoricalDataIntervalFrom());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>historicalTimePeriodFrom</column-name><column-value><![CDATA[");
        sb.append(getHistoricalTimePeriodFrom());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projHistFreq</column-name><column-value><![CDATA[");
        sb.append(getProjHistFreq());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>futureTimePeriodFrom</column-name><column-value><![CDATA[");
        sb.append(getFutureTimePeriodFrom());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>historicalDataIntervalTo</column-name><column-value><![CDATA[");
        sb.append(getHistoricalDataIntervalTo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projHistValue</column-name><column-value><![CDATA[");
        sb.append(getProjHistValue());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
