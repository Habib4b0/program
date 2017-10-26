package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.StChSalesProjectionMasterLocalServiceUtil;
import com.stpl.app.service.persistence.StChSalesProjectionMasterPK;

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


public class StChSalesProjectionMasterClp extends BaseModelImpl<StChSalesProjectionMaster>
    implements StChSalesProjectionMaster {
    private Date _lastModifiedDate;
    private boolean _checkRecord;
    private String _calculationPeriods;
    private int _projectionDetailsSid;
    private int _userId;
    private int _sessionId;
    private String _methodology;
    private BaseModel<?> _stChSalesProjectionMasterRemoteModel;

    public StChSalesProjectionMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return StChSalesProjectionMaster.class;
    }

    @Override
    public String getModelClassName() {
        return StChSalesProjectionMaster.class.getName();
    }

    @Override
    public StChSalesProjectionMasterPK getPrimaryKey() {
        return new StChSalesProjectionMasterPK(_projectionDetailsSid, _userId,
            _sessionId);
    }

    @Override
    public void setPrimaryKey(StChSalesProjectionMasterPK primaryKey) {
        setProjectionDetailsSid(primaryKey.projectionDetailsSid);
        setUserId(primaryKey.userId);
        setSessionId(primaryKey.sessionId);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new StChSalesProjectionMasterPK(_projectionDetailsSid, _userId,
            _sessionId);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((StChSalesProjectionMasterPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("lastModifiedDate", getLastModifiedDate());
        attributes.put("checkRecord", getCheckRecord());
        attributes.put("calculationPeriods", getCalculationPeriods());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("userId", getUserId());
        attributes.put("sessionId", getSessionId());
        attributes.put("methodology", getMethodology());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date lastModifiedDate = (Date) attributes.get("lastModifiedDate");

        if (lastModifiedDate != null) {
            setLastModifiedDate(lastModifiedDate);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        String calculationPeriods = (String) attributes.get(
                "calculationPeriods");

        if (calculationPeriods != null) {
            setCalculationPeriods(calculationPeriods);
        }

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Integer sessionId = (Integer) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        String methodology = (String) attributes.get("methodology");

        if (methodology != null) {
            setMethodology(methodology);
        }
    }

    @Override
    public Date getLastModifiedDate() {
        return _lastModifiedDate;
    }

    @Override
    public void setLastModifiedDate(Date lastModifiedDate) {
        _lastModifiedDate = lastModifiedDate;

        if (_stChSalesProjectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stChSalesProjectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLastModifiedDate",
                        Date.class);

                method.invoke(_stChSalesProjectionMasterRemoteModel,
                    lastModifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getCheckRecord() {
        return _checkRecord;
    }

    @Override
    public boolean isCheckRecord() {
        return _checkRecord;
    }

    @Override
    public void setCheckRecord(boolean checkRecord) {
        _checkRecord = checkRecord;

        if (_stChSalesProjectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stChSalesProjectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_stChSalesProjectionMasterRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCalculationPeriods() {
        return _calculationPeriods;
    }

    @Override
    public void setCalculationPeriods(String calculationPeriods) {
        _calculationPeriods = calculationPeriods;

        if (_stChSalesProjectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stChSalesProjectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCalculationPeriods",
                        String.class);

                method.invoke(_stChSalesProjectionMasterRemoteModel,
                    calculationPeriods);
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

        if (_stChSalesProjectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stChSalesProjectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDetailsSid",
                        int.class);

                method.invoke(_stChSalesProjectionMasterRemoteModel,
                    projectionDetailsSid);
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

        if (_stChSalesProjectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stChSalesProjectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", int.class);

                method.invoke(_stChSalesProjectionMasterRemoteModel, userId);
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

        if (_stChSalesProjectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stChSalesProjectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", int.class);

                method.invoke(_stChSalesProjectionMasterRemoteModel, sessionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getMethodology() {
        return _methodology;
    }

    @Override
    public void setMethodology(String methodology) {
        _methodology = methodology;

        if (_stChSalesProjectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stChSalesProjectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setMethodology", String.class);

                method.invoke(_stChSalesProjectionMasterRemoteModel, methodology);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getStChSalesProjectionMasterRemoteModel() {
        return _stChSalesProjectionMasterRemoteModel;
    }

    public void setStChSalesProjectionMasterRemoteModel(
        BaseModel<?> stChSalesProjectionMasterRemoteModel) {
        _stChSalesProjectionMasterRemoteModel = stChSalesProjectionMasterRemoteModel;
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

        Class<?> remoteModelClass = _stChSalesProjectionMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_stChSalesProjectionMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            StChSalesProjectionMasterLocalServiceUtil.addStChSalesProjectionMaster(this);
        } else {
            StChSalesProjectionMasterLocalServiceUtil.updateStChSalesProjectionMaster(this);
        }
    }

    @Override
    public StChSalesProjectionMaster toEscapedModel() {
        return (StChSalesProjectionMaster) ProxyUtil.newProxyInstance(StChSalesProjectionMaster.class.getClassLoader(),
            new Class[] { StChSalesProjectionMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        StChSalesProjectionMasterClp clone = new StChSalesProjectionMasterClp();

        clone.setLastModifiedDate(getLastModifiedDate());
        clone.setCheckRecord(getCheckRecord());
        clone.setCalculationPeriods(getCalculationPeriods());
        clone.setProjectionDetailsSid(getProjectionDetailsSid());
        clone.setUserId(getUserId());
        clone.setSessionId(getSessionId());
        clone.setMethodology(getMethodology());

        return clone;
    }

    @Override
    public int compareTo(StChSalesProjectionMaster stChSalesProjectionMaster) {
        StChSalesProjectionMasterPK primaryKey = stChSalesProjectionMaster.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StChSalesProjectionMasterClp)) {
            return false;
        }

        StChSalesProjectionMasterClp stChSalesProjectionMaster = (StChSalesProjectionMasterClp) obj;

        StChSalesProjectionMasterPK primaryKey = stChSalesProjectionMaster.getPrimaryKey();

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
        StringBundler sb = new StringBundler(15);

        sb.append("{lastModifiedDate=");
        sb.append(getLastModifiedDate());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append(", calculationPeriods=");
        sb.append(getCalculationPeriods());
        sb.append(", projectionDetailsSid=");
        sb.append(getProjectionDetailsSid());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append(", methodology=");
        sb.append(getMethodology());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(25);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.StChSalesProjectionMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>lastModifiedDate</column-name><column-value><![CDATA[");
        sb.append(getLastModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>calculationPeriods</column-name><column-value><![CDATA[");
        sb.append(getCalculationPeriods());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getProjectionDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sessionId</column-name><column-value><![CDATA[");
        sb.append(getSessionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>methodology</column-name><column-value><![CDATA[");
        sb.append(getMethodology());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
