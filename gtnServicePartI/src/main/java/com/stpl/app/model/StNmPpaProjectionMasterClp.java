package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.StNmPpaProjectionMasterLocalServiceUtil;
import com.stpl.app.service.persistence.StNmPpaProjectionMasterPK;

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


public class StNmPpaProjectionMasterClp extends BaseModelImpl<StNmPpaProjectionMaster>
    implements StNmPpaProjectionMaster {
    private Date _lastModifiedDate;
    private boolean _checkRecord;
    private String _userGroup;
    private int _projectionDetailsSid;
    private int _userId;
    private int _sessionId;
    private String _priceBasis;
    private Date _priceProtectionEndDate;
    private Date _priceProtectionStartDate;
    private double _actualPriceCap;
    private BaseModel<?> _stNmPpaProjectionMasterRemoteModel;

    public StNmPpaProjectionMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return StNmPpaProjectionMaster.class;
    }

    @Override
    public String getModelClassName() {
        return StNmPpaProjectionMaster.class.getName();
    }

    @Override
    public StNmPpaProjectionMasterPK getPrimaryKey() {
        return new StNmPpaProjectionMasterPK(_projectionDetailsSid, _userId,
            _sessionId);
    }

    @Override
    public void setPrimaryKey(StNmPpaProjectionMasterPK primaryKey) {
        setProjectionDetailsSid(primaryKey.projectionDetailsSid);
        setUserId(primaryKey.userId);
        setSessionId(primaryKey.sessionId);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new StNmPpaProjectionMasterPK(_projectionDetailsSid, _userId,
            _sessionId);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((StNmPpaProjectionMasterPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("lastModifiedDate", getLastModifiedDate());
        attributes.put("checkRecord", getCheckRecord());
        attributes.put("userGroup", getUserGroup());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("userId", getUserId());
        attributes.put("sessionId", getSessionId());
        attributes.put("priceBasis", getPriceBasis());
        attributes.put("priceProtectionEndDate", getPriceProtectionEndDate());
        attributes.put("priceProtectionStartDate", getPriceProtectionStartDate());
        attributes.put("actualPriceCap", getActualPriceCap());

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

        String userGroup = (String) attributes.get("userGroup");

        if (userGroup != null) {
            setUserGroup(userGroup);
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

        String priceBasis = (String) attributes.get("priceBasis");

        if (priceBasis != null) {
            setPriceBasis(priceBasis);
        }

        Date priceProtectionEndDate = (Date) attributes.get(
                "priceProtectionEndDate");

        if (priceProtectionEndDate != null) {
            setPriceProtectionEndDate(priceProtectionEndDate);
        }

        Date priceProtectionStartDate = (Date) attributes.get(
                "priceProtectionStartDate");

        if (priceProtectionStartDate != null) {
            setPriceProtectionStartDate(priceProtectionStartDate);
        }

        Double actualPriceCap = (Double) attributes.get("actualPriceCap");

        if (actualPriceCap != null) {
            setActualPriceCap(actualPriceCap);
        }
    }

    @Override
    public Date getLastModifiedDate() {
        return _lastModifiedDate;
    }

    @Override
    public void setLastModifiedDate(Date lastModifiedDate) {
        _lastModifiedDate = lastModifiedDate;

        if (_stNmPpaProjectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stNmPpaProjectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLastModifiedDate",
                        Date.class);

                method.invoke(_stNmPpaProjectionMasterRemoteModel,
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

        if (_stNmPpaProjectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stNmPpaProjectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_stNmPpaProjectionMasterRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUserGroup() {
        return _userGroup;
    }

    @Override
    public void setUserGroup(String userGroup) {
        _userGroup = userGroup;

        if (_stNmPpaProjectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stNmPpaProjectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUserGroup", String.class);

                method.invoke(_stNmPpaProjectionMasterRemoteModel, userGroup);
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

        if (_stNmPpaProjectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stNmPpaProjectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDetailsSid",
                        int.class);

                method.invoke(_stNmPpaProjectionMasterRemoteModel,
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

        if (_stNmPpaProjectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stNmPpaProjectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", int.class);

                method.invoke(_stNmPpaProjectionMasterRemoteModel, userId);
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

        if (_stNmPpaProjectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stNmPpaProjectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", int.class);

                method.invoke(_stNmPpaProjectionMasterRemoteModel, sessionId);
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

        if (_stNmPpaProjectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stNmPpaProjectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceBasis", String.class);

                method.invoke(_stNmPpaProjectionMasterRemoteModel, priceBasis);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPriceProtectionEndDate() {
        return _priceProtectionEndDate;
    }

    @Override
    public void setPriceProtectionEndDate(Date priceProtectionEndDate) {
        _priceProtectionEndDate = priceProtectionEndDate;

        if (_stNmPpaProjectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stNmPpaProjectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceProtectionEndDate",
                        Date.class);

                method.invoke(_stNmPpaProjectionMasterRemoteModel,
                    priceProtectionEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPriceProtectionStartDate() {
        return _priceProtectionStartDate;
    }

    @Override
    public void setPriceProtectionStartDate(Date priceProtectionStartDate) {
        _priceProtectionStartDate = priceProtectionStartDate;

        if (_stNmPpaProjectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stNmPpaProjectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceProtectionStartDate",
                        Date.class);

                method.invoke(_stNmPpaProjectionMasterRemoteModel,
                    priceProtectionStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getActualPriceCap() {
        return _actualPriceCap;
    }

    @Override
    public void setActualPriceCap(double actualPriceCap) {
        _actualPriceCap = actualPriceCap;

        if (_stNmPpaProjectionMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stNmPpaProjectionMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setActualPriceCap",
                        double.class);

                method.invoke(_stNmPpaProjectionMasterRemoteModel,
                    actualPriceCap);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getStNmPpaProjectionMasterRemoteModel() {
        return _stNmPpaProjectionMasterRemoteModel;
    }

    public void setStNmPpaProjectionMasterRemoteModel(
        BaseModel<?> stNmPpaProjectionMasterRemoteModel) {
        _stNmPpaProjectionMasterRemoteModel = stNmPpaProjectionMasterRemoteModel;
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

        Class<?> remoteModelClass = _stNmPpaProjectionMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_stNmPpaProjectionMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            StNmPpaProjectionMasterLocalServiceUtil.addStNmPpaProjectionMaster(this);
        } else {
            StNmPpaProjectionMasterLocalServiceUtil.updateStNmPpaProjectionMaster(this);
        }
    }

    @Override
    public StNmPpaProjectionMaster toEscapedModel() {
        return (StNmPpaProjectionMaster) ProxyUtil.newProxyInstance(StNmPpaProjectionMaster.class.getClassLoader(),
            new Class[] { StNmPpaProjectionMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        StNmPpaProjectionMasterClp clone = new StNmPpaProjectionMasterClp();

        clone.setLastModifiedDate(getLastModifiedDate());
        clone.setCheckRecord(getCheckRecord());
        clone.setUserGroup(getUserGroup());
        clone.setProjectionDetailsSid(getProjectionDetailsSid());
        clone.setUserId(getUserId());
        clone.setSessionId(getSessionId());
        clone.setPriceBasis(getPriceBasis());
        clone.setPriceProtectionEndDate(getPriceProtectionEndDate());
        clone.setPriceProtectionStartDate(getPriceProtectionStartDate());
        clone.setActualPriceCap(getActualPriceCap());

        return clone;
    }

    @Override
    public int compareTo(StNmPpaProjectionMaster stNmPpaProjectionMaster) {
        StNmPpaProjectionMasterPK primaryKey = stNmPpaProjectionMaster.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StNmPpaProjectionMasterClp)) {
            return false;
        }

        StNmPpaProjectionMasterClp stNmPpaProjectionMaster = (StNmPpaProjectionMasterClp) obj;

        StNmPpaProjectionMasterPK primaryKey = stNmPpaProjectionMaster.getPrimaryKey();

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

        sb.append("{lastModifiedDate=");
        sb.append(getLastModifiedDate());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append(", userGroup=");
        sb.append(getUserGroup());
        sb.append(", projectionDetailsSid=");
        sb.append(getProjectionDetailsSid());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append(", priceBasis=");
        sb.append(getPriceBasis());
        sb.append(", priceProtectionEndDate=");
        sb.append(getPriceProtectionEndDate());
        sb.append(", priceProtectionStartDate=");
        sb.append(getPriceProtectionStartDate());
        sb.append(", actualPriceCap=");
        sb.append(getActualPriceCap());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(34);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.StNmPpaProjectionMaster");
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
            "<column><column-name>userGroup</column-name><column-value><![CDATA[");
        sb.append(getUserGroup());
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
            "<column><column-name>priceBasis</column-name><column-value><![CDATA[");
        sb.append(getPriceBasis());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceProtectionEndDate</column-name><column-value><![CDATA[");
        sb.append(getPriceProtectionEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceProtectionStartDate</column-name><column-value><![CDATA[");
        sb.append(getPriceProtectionStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualPriceCap</column-name><column-value><![CDATA[");
        sb.append(getActualPriceCap());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
