package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.GcmContractDetailsLocalServiceUtil;

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


public class GcmContractDetailsClp extends BaseModelImpl<GcmContractDetails>
    implements GcmContractDetails {
    private String _paymentMethod;
    private int _userId;
    private Date _endDate;
    private String _paymentFrequency;
    private int _gcmContractDetailsSid;
    private String _componentId;
    private Date _modifiedDate;
    private String _componentName;
    private String _rsCalendar;
    private String _fileName;
    private Date _startDate;
    private String _planLevel;
    private Date _createdDate;
    private int _createdBy;
    private String _componentNo;
    private String _programType;
    private String _sessionId;
    private int _modifiedBy;
    private String _componentStatus;
    private String _componentType;
    private BaseModel<?> _gcmContractDetailsRemoteModel;

    public GcmContractDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return GcmContractDetails.class;
    }

    @Override
    public String getModelClassName() {
        return GcmContractDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _gcmContractDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setGcmContractDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _gcmContractDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("paymentMethod", getPaymentMethod());
        attributes.put("userId", getUserId());
        attributes.put("endDate", getEndDate());
        attributes.put("paymentFrequency", getPaymentFrequency());
        attributes.put("gcmContractDetailsSid", getGcmContractDetailsSid());
        attributes.put("componentId", getComponentId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("componentName", getComponentName());
        attributes.put("rsCalendar", getRsCalendar());
        attributes.put("fileName", getFileName());
        attributes.put("startDate", getStartDate());
        attributes.put("planLevel", getPlanLevel());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("componentNo", getComponentNo());
        attributes.put("programType", getProgramType());
        attributes.put("sessionId", getSessionId());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("componentStatus", getComponentStatus());
        attributes.put("componentType", getComponentType());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String paymentMethod = (String) attributes.get("paymentMethod");

        if (paymentMethod != null) {
            setPaymentMethod(paymentMethod);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Date endDate = (Date) attributes.get("endDate");

        if (endDate != null) {
            setEndDate(endDate);
        }

        String paymentFrequency = (String) attributes.get("paymentFrequency");

        if (paymentFrequency != null) {
            setPaymentFrequency(paymentFrequency);
        }

        Integer gcmContractDetailsSid = (Integer) attributes.get(
                "gcmContractDetailsSid");

        if (gcmContractDetailsSid != null) {
            setGcmContractDetailsSid(gcmContractDetailsSid);
        }

        String componentId = (String) attributes.get("componentId");

        if (componentId != null) {
            setComponentId(componentId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String componentName = (String) attributes.get("componentName");

        if (componentName != null) {
            setComponentName(componentName);
        }

        String rsCalendar = (String) attributes.get("rsCalendar");

        if (rsCalendar != null) {
            setRsCalendar(rsCalendar);
        }

        String fileName = (String) attributes.get("fileName");

        if (fileName != null) {
            setFileName(fileName);
        }

        Date startDate = (Date) attributes.get("startDate");

        if (startDate != null) {
            setStartDate(startDate);
        }

        String planLevel = (String) attributes.get("planLevel");

        if (planLevel != null) {
            setPlanLevel(planLevel);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String componentNo = (String) attributes.get("componentNo");

        if (componentNo != null) {
            setComponentNo(componentNo);
        }

        String programType = (String) attributes.get("programType");

        if (programType != null) {
            setProgramType(programType);
        }

        String sessionId = (String) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String componentStatus = (String) attributes.get("componentStatus");

        if (componentStatus != null) {
            setComponentStatus(componentStatus);
        }

        String componentType = (String) attributes.get("componentType");

        if (componentType != null) {
            setComponentType(componentType);
        }
    }

    @Override
    public String getPaymentMethod() {
        return _paymentMethod;
    }

    @Override
    public void setPaymentMethod(String paymentMethod) {
        _paymentMethod = paymentMethod;

        if (_gcmContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPaymentMethod", String.class);

                method.invoke(_gcmContractDetailsRemoteModel, paymentMethod);
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

        if (_gcmContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", int.class);

                method.invoke(_gcmContractDetailsRemoteModel, userId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getEndDate() {
        return _endDate;
    }

    @Override
    public void setEndDate(Date endDate) {
        _endDate = endDate;

        if (_gcmContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setEndDate", Date.class);

                method.invoke(_gcmContractDetailsRemoteModel, endDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPaymentFrequency() {
        return _paymentFrequency;
    }

    @Override
    public void setPaymentFrequency(String paymentFrequency) {
        _paymentFrequency = paymentFrequency;

        if (_gcmContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPaymentFrequency",
                        String.class);

                method.invoke(_gcmContractDetailsRemoteModel, paymentFrequency);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getGcmContractDetailsSid() {
        return _gcmContractDetailsSid;
    }

    @Override
    public void setGcmContractDetailsSid(int gcmContractDetailsSid) {
        _gcmContractDetailsSid = gcmContractDetailsSid;

        if (_gcmContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setGcmContractDetailsSid",
                        int.class);

                method.invoke(_gcmContractDetailsRemoteModel,
                    gcmContractDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getComponentId() {
        return _componentId;
    }

    @Override
    public void setComponentId(String componentId) {
        _componentId = componentId;

        if (_gcmContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setComponentId", String.class);

                method.invoke(_gcmContractDetailsRemoteModel, componentId);
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

        if (_gcmContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_gcmContractDetailsRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getComponentName() {
        return _componentName;
    }

    @Override
    public void setComponentName(String componentName) {
        _componentName = componentName;

        if (_gcmContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setComponentName", String.class);

                method.invoke(_gcmContractDetailsRemoteModel, componentName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRsCalendar() {
        return _rsCalendar;
    }

    @Override
    public void setRsCalendar(String rsCalendar) {
        _rsCalendar = rsCalendar;

        if (_gcmContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsCalendar", String.class);

                method.invoke(_gcmContractDetailsRemoteModel, rsCalendar);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFileName() {
        return _fileName;
    }

    @Override
    public void setFileName(String fileName) {
        _fileName = fileName;

        if (_gcmContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setFileName", String.class);

                method.invoke(_gcmContractDetailsRemoteModel, fileName);
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

        if (_gcmContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setStartDate", Date.class);

                method.invoke(_gcmContractDetailsRemoteModel, startDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPlanLevel() {
        return _planLevel;
    }

    @Override
    public void setPlanLevel(String planLevel) {
        _planLevel = planLevel;

        if (_gcmContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanLevel", String.class);

                method.invoke(_gcmContractDetailsRemoteModel, planLevel);
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

        if (_gcmContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_gcmContractDetailsRemoteModel, createdDate);
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

        if (_gcmContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_gcmContractDetailsRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getComponentNo() {
        return _componentNo;
    }

    @Override
    public void setComponentNo(String componentNo) {
        _componentNo = componentNo;

        if (_gcmContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setComponentNo", String.class);

                method.invoke(_gcmContractDetailsRemoteModel, componentNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getProgramType() {
        return _programType;
    }

    @Override
    public void setProgramType(String programType) {
        _programType = programType;

        if (_gcmContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setProgramType", String.class);

                method.invoke(_gcmContractDetailsRemoteModel, programType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSessionId() {
        return _sessionId;
    }

    @Override
    public void setSessionId(String sessionId) {
        _sessionId = sessionId;

        if (_gcmContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", String.class);

                method.invoke(_gcmContractDetailsRemoteModel, sessionId);
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

        if (_gcmContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_gcmContractDetailsRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getComponentStatus() {
        return _componentStatus;
    }

    @Override
    public void setComponentStatus(String componentStatus) {
        _componentStatus = componentStatus;

        if (_gcmContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setComponentStatus",
                        String.class);

                method.invoke(_gcmContractDetailsRemoteModel, componentStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getComponentType() {
        return _componentType;
    }

    @Override
    public void setComponentType(String componentType) {
        _componentType = componentType;

        if (_gcmContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setComponentType", String.class);

                method.invoke(_gcmContractDetailsRemoteModel, componentType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getGcmContractDetailsRemoteModel() {
        return _gcmContractDetailsRemoteModel;
    }

    public void setGcmContractDetailsRemoteModel(
        BaseModel<?> gcmContractDetailsRemoteModel) {
        _gcmContractDetailsRemoteModel = gcmContractDetailsRemoteModel;
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

        Class<?> remoteModelClass = _gcmContractDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_gcmContractDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            GcmContractDetailsLocalServiceUtil.addGcmContractDetails(this);
        } else {
            GcmContractDetailsLocalServiceUtil.updateGcmContractDetails(this);
        }
    }

    @Override
    public GcmContractDetails toEscapedModel() {
        return (GcmContractDetails) ProxyUtil.newProxyInstance(GcmContractDetails.class.getClassLoader(),
            new Class[] { GcmContractDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        GcmContractDetailsClp clone = new GcmContractDetailsClp();

        clone.setPaymentMethod(getPaymentMethod());
        clone.setUserId(getUserId());
        clone.setEndDate(getEndDate());
        clone.setPaymentFrequency(getPaymentFrequency());
        clone.setGcmContractDetailsSid(getGcmContractDetailsSid());
        clone.setComponentId(getComponentId());
        clone.setModifiedDate(getModifiedDate());
        clone.setComponentName(getComponentName());
        clone.setRsCalendar(getRsCalendar());
        clone.setFileName(getFileName());
        clone.setStartDate(getStartDate());
        clone.setPlanLevel(getPlanLevel());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setComponentNo(getComponentNo());
        clone.setProgramType(getProgramType());
        clone.setSessionId(getSessionId());
        clone.setModifiedBy(getModifiedBy());
        clone.setComponentStatus(getComponentStatus());
        clone.setComponentType(getComponentType());

        return clone;
    }

    @Override
    public int compareTo(GcmContractDetails gcmContractDetails) {
        int primaryKey = gcmContractDetails.getPrimaryKey();

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

        if (!(obj instanceof GcmContractDetailsClp)) {
            return false;
        }

        GcmContractDetailsClp gcmContractDetails = (GcmContractDetailsClp) obj;

        int primaryKey = gcmContractDetails.getPrimaryKey();

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
        StringBundler sb = new StringBundler(41);

        sb.append("{paymentMethod=");
        sb.append(getPaymentMethod());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", endDate=");
        sb.append(getEndDate());
        sb.append(", paymentFrequency=");
        sb.append(getPaymentFrequency());
        sb.append(", gcmContractDetailsSid=");
        sb.append(getGcmContractDetailsSid());
        sb.append(", componentId=");
        sb.append(getComponentId());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", componentName=");
        sb.append(getComponentName());
        sb.append(", rsCalendar=");
        sb.append(getRsCalendar());
        sb.append(", fileName=");
        sb.append(getFileName());
        sb.append(", startDate=");
        sb.append(getStartDate());
        sb.append(", planLevel=");
        sb.append(getPlanLevel());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", componentNo=");
        sb.append(getComponentNo());
        sb.append(", programType=");
        sb.append(getProgramType());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", componentStatus=");
        sb.append(getComponentStatus());
        sb.append(", componentType=");
        sb.append(getComponentType());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(64);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.GcmContractDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>paymentMethod</column-name><column-value><![CDATA[");
        sb.append(getPaymentMethod());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>endDate</column-name><column-value><![CDATA[");
        sb.append(getEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>paymentFrequency</column-name><column-value><![CDATA[");
        sb.append(getPaymentFrequency());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>gcmContractDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getGcmContractDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>componentId</column-name><column-value><![CDATA[");
        sb.append(getComponentId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>componentName</column-name><column-value><![CDATA[");
        sb.append(getComponentName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsCalendar</column-name><column-value><![CDATA[");
        sb.append(getRsCalendar());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fileName</column-name><column-value><![CDATA[");
        sb.append(getFileName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>startDate</column-name><column-value><![CDATA[");
        sb.append(getStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planLevel</column-name><column-value><![CDATA[");
        sb.append(getPlanLevel());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>componentNo</column-name><column-value><![CDATA[");
        sb.append(getComponentNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>programType</column-name><column-value><![CDATA[");
        sb.append(getProgramType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sessionId</column-name><column-value><![CDATA[");
        sb.append(getSessionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>componentStatus</column-name><column-value><![CDATA[");
        sb.append(getComponentStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>componentType</column-name><column-value><![CDATA[");
        sb.append(getComponentType());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
