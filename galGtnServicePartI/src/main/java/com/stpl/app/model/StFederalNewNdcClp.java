package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.StFederalNewNdcLocalServiceUtil;
import com.stpl.app.service.persistence.StFederalNewNdcPK;

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


public class StFederalNewNdcClp extends BaseModelImpl<StFederalNewNdc>
    implements StFederalNewNdc {
    private double _fss;
    private int _userId;
    private Date _lastModifiedDate;
    private int _itemMasterSid;
    private double _wacPrice;
    private int _sessionId;
    private double _nonFamp;
    private BaseModel<?> _stFederalNewNdcRemoteModel;

    public StFederalNewNdcClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return StFederalNewNdc.class;
    }

    @Override
    public String getModelClassName() {
        return StFederalNewNdc.class.getName();
    }

    @Override
    public StFederalNewNdcPK getPrimaryKey() {
        return new StFederalNewNdcPK(_userId, _itemMasterSid, _sessionId);
    }

    @Override
    public void setPrimaryKey(StFederalNewNdcPK primaryKey) {
        setUserId(primaryKey.userId);
        setItemMasterSid(primaryKey.itemMasterSid);
        setSessionId(primaryKey.sessionId);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new StFederalNewNdcPK(_userId, _itemMasterSid, _sessionId);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((StFederalNewNdcPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("fss", getFss());
        attributes.put("userId", getUserId());
        attributes.put("lastModifiedDate", getLastModifiedDate());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("wacPrice", getWacPrice());
        attributes.put("sessionId", getSessionId());
        attributes.put("nonFamp", getNonFamp());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double fss = (Double) attributes.get("fss");

        if (fss != null) {
            setFss(fss);
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

        Integer sessionId = (Integer) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        Double nonFamp = (Double) attributes.get("nonFamp");

        if (nonFamp != null) {
            setNonFamp(nonFamp);
        }
    }

    @Override
    public double getFss() {
        return _fss;
    }

    @Override
    public void setFss(double fss) {
        _fss = fss;

        if (_stFederalNewNdcRemoteModel != null) {
            try {
                Class<?> clazz = _stFederalNewNdcRemoteModel.getClass();

                Method method = clazz.getMethod("setFss", double.class);

                method.invoke(_stFederalNewNdcRemoteModel, fss);
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

        if (_stFederalNewNdcRemoteModel != null) {
            try {
                Class<?> clazz = _stFederalNewNdcRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", int.class);

                method.invoke(_stFederalNewNdcRemoteModel, userId);
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

        if (_stFederalNewNdcRemoteModel != null) {
            try {
                Class<?> clazz = _stFederalNewNdcRemoteModel.getClass();

                Method method = clazz.getMethod("setLastModifiedDate",
                        Date.class);

                method.invoke(_stFederalNewNdcRemoteModel, lastModifiedDate);
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

        if (_stFederalNewNdcRemoteModel != null) {
            try {
                Class<?> clazz = _stFederalNewNdcRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_stFederalNewNdcRemoteModel, itemMasterSid);
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

        if (_stFederalNewNdcRemoteModel != null) {
            try {
                Class<?> clazz = _stFederalNewNdcRemoteModel.getClass();

                Method method = clazz.getMethod("setWacPrice", double.class);

                method.invoke(_stFederalNewNdcRemoteModel, wacPrice);
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

        if (_stFederalNewNdcRemoteModel != null) {
            try {
                Class<?> clazz = _stFederalNewNdcRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", int.class);

                method.invoke(_stFederalNewNdcRemoteModel, sessionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getNonFamp() {
        return _nonFamp;
    }

    @Override
    public void setNonFamp(double nonFamp) {
        _nonFamp = nonFamp;

        if (_stFederalNewNdcRemoteModel != null) {
            try {
                Class<?> clazz = _stFederalNewNdcRemoteModel.getClass();

                Method method = clazz.getMethod("setNonFamp", double.class);

                method.invoke(_stFederalNewNdcRemoteModel, nonFamp);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getStFederalNewNdcRemoteModel() {
        return _stFederalNewNdcRemoteModel;
    }

    public void setStFederalNewNdcRemoteModel(
        BaseModel<?> stFederalNewNdcRemoteModel) {
        _stFederalNewNdcRemoteModel = stFederalNewNdcRemoteModel;
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

        Class<?> remoteModelClass = _stFederalNewNdcRemoteModel.getClass();

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

        Object returnValue = method.invoke(_stFederalNewNdcRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            StFederalNewNdcLocalServiceUtil.addStFederalNewNdc(this);
        } else {
            StFederalNewNdcLocalServiceUtil.updateStFederalNewNdc(this);
        }
    }

    @Override
    public StFederalNewNdc toEscapedModel() {
        return (StFederalNewNdc) ProxyUtil.newProxyInstance(StFederalNewNdc.class.getClassLoader(),
            new Class[] { StFederalNewNdc.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        StFederalNewNdcClp clone = new StFederalNewNdcClp();

        clone.setFss(getFss());
        clone.setUserId(getUserId());
        clone.setLastModifiedDate(getLastModifiedDate());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setWacPrice(getWacPrice());
        clone.setSessionId(getSessionId());
        clone.setNonFamp(getNonFamp());

        return clone;
    }

    @Override
    public int compareTo(StFederalNewNdc stFederalNewNdc) {
        StFederalNewNdcPK primaryKey = stFederalNewNdc.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StFederalNewNdcClp)) {
            return false;
        }

        StFederalNewNdcClp stFederalNewNdc = (StFederalNewNdcClp) obj;

        StFederalNewNdcPK primaryKey = stFederalNewNdc.getPrimaryKey();

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

        sb.append("{fss=");
        sb.append(getFss());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", lastModifiedDate=");
        sb.append(getLastModifiedDate());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", wacPrice=");
        sb.append(getWacPrice());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append(", nonFamp=");
        sb.append(getNonFamp());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(25);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.StFederalNewNdc");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>fss</column-name><column-value><![CDATA[");
        sb.append(getFss());
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
            "<column><column-name>sessionId</column-name><column-value><![CDATA[");
        sb.append(getSessionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>nonFamp</column-name><column-value><![CDATA[");
        sb.append(getNonFamp());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
