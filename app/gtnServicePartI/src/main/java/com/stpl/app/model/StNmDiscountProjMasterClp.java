package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.StNmDiscountProjMasterLocalServiceUtil;
import com.stpl.app.service.persistence.StNmDiscountProjMasterPK;

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


public class StNmDiscountProjMasterClp extends BaseModelImpl<StNmDiscountProjMaster>
    implements StNmDiscountProjMaster {
    private String _selectedPeriods;
    private String _methodology;
    private String _netFlag;
    private String _priceGroupType;
    private String _userGroup;
    private int _userId;
    private Date _lastModifiedDate;
    private int _projectionDetailsSid;
    private int _rsModelSid;
    private int _sessionId;
    private boolean _checkRecord;
    private String _baselinePeriods;
    private BaseModel<?> _stNmDiscountProjMasterRemoteModel;

    public StNmDiscountProjMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return StNmDiscountProjMaster.class;
    }

    @Override
    public String getModelClassName() {
        return StNmDiscountProjMaster.class.getName();
    }

    @Override
    public StNmDiscountProjMasterPK getPrimaryKey() {
        return new StNmDiscountProjMasterPK(_userId, _projectionDetailsSid,
            _rsModelSid, _sessionId);
    }

    @Override
    public void setPrimaryKey(StNmDiscountProjMasterPK primaryKey) {
        setUserId(primaryKey.userId);
        setProjectionDetailsSid(primaryKey.projectionDetailsSid);
        setRsModelSid(primaryKey.rsModelSid);
        setSessionId(primaryKey.sessionId);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new StNmDiscountProjMasterPK(_userId, _projectionDetailsSid,
            _rsModelSid, _sessionId);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((StNmDiscountProjMasterPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("selectedPeriods", getSelectedPeriods());
        attributes.put("methodology", getMethodology());
        attributes.put("netFlag", getNetFlag());
        attributes.put("priceGroupType", getPriceGroupType());
        attributes.put("userGroup", getUserGroup());
        attributes.put("userId", getUserId());
        attributes.put("lastModifiedDate", getLastModifiedDate());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("rsModelSid", getRsModelSid());
        attributes.put("sessionId", getSessionId());
        attributes.put("checkRecord", getCheckRecord());
        attributes.put("baselinePeriods", getBaselinePeriods());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String selectedPeriods = (String) attributes.get("selectedPeriods");

        if (selectedPeriods != null) {
            setSelectedPeriods(selectedPeriods);
        }

        String methodology = (String) attributes.get("methodology");

        if (methodology != null) {
            setMethodology(methodology);
        }

        String netFlag = (String) attributes.get("netFlag");

        if (netFlag != null) {
            setNetFlag(netFlag);
        }

        String priceGroupType = (String) attributes.get("priceGroupType");

        if (priceGroupType != null) {
            setPriceGroupType(priceGroupType);
        }

        String userGroup = (String) attributes.get("userGroup");

        if (userGroup != null) {
            setUserGroup(userGroup);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Date lastModifiedDate = (Date) attributes.get("lastModifiedDate");

        if (lastModifiedDate != null) {
            setLastModifiedDate(lastModifiedDate);
        }

        Integer projectionDetailsSid = (Integer) attributes.get(
                "projectionDetailsSid");

        if (projectionDetailsSid != null) {
            setProjectionDetailsSid(projectionDetailsSid);
        }

        Integer rsModelSid = (Integer) attributes.get("rsModelSid");

        if (rsModelSid != null) {
            setRsModelSid(rsModelSid);
        }

        Integer sessionId = (Integer) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        String baselinePeriods = (String) attributes.get("baselinePeriods");

        if (baselinePeriods != null) {
            setBaselinePeriods(baselinePeriods);
        }
    }

    @Override
    public String getSelectedPeriods() {
        return _selectedPeriods;
    }

    @Override
    public void setSelectedPeriods(String selectedPeriods) {
        _selectedPeriods = selectedPeriods;

        if (_stNmDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stNmDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSelectedPeriods",
                        String.class);

                method.invoke(_stNmDiscountProjMasterRemoteModel,
                    selectedPeriods);
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

        if (_stNmDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stNmDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setMethodology", String.class);

                method.invoke(_stNmDiscountProjMasterRemoteModel, methodology);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNetFlag() {
        return _netFlag;
    }

    @Override
    public void setNetFlag(String netFlag) {
        _netFlag = netFlag;

        if (_stNmDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stNmDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNetFlag", String.class);

                method.invoke(_stNmDiscountProjMasterRemoteModel, netFlag);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPriceGroupType() {
        return _priceGroupType;
    }

    @Override
    public void setPriceGroupType(String priceGroupType) {
        _priceGroupType = priceGroupType;

        if (_stNmDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stNmDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceGroupType",
                        String.class);

                method.invoke(_stNmDiscountProjMasterRemoteModel, priceGroupType);
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

        if (_stNmDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stNmDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUserGroup", String.class);

                method.invoke(_stNmDiscountProjMasterRemoteModel, userGroup);
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

        if (_stNmDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stNmDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", int.class);

                method.invoke(_stNmDiscountProjMasterRemoteModel, userId);
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

        if (_stNmDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stNmDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLastModifiedDate",
                        Date.class);

                method.invoke(_stNmDiscountProjMasterRemoteModel,
                    lastModifiedDate);
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

        if (_stNmDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stNmDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDetailsSid",
                        int.class);

                method.invoke(_stNmDiscountProjMasterRemoteModel,
                    projectionDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRsModelSid() {
        return _rsModelSid;
    }

    @Override
    public void setRsModelSid(int rsModelSid) {
        _rsModelSid = rsModelSid;

        if (_stNmDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stNmDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRsModelSid", int.class);

                method.invoke(_stNmDiscountProjMasterRemoteModel, rsModelSid);
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

        if (_stNmDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stNmDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", int.class);

                method.invoke(_stNmDiscountProjMasterRemoteModel, sessionId);
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

        if (_stNmDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stNmDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_stNmDiscountProjMasterRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBaselinePeriods() {
        return _baselinePeriods;
    }

    @Override
    public void setBaselinePeriods(String baselinePeriods) {
        _baselinePeriods = baselinePeriods;

        if (_stNmDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stNmDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBaselinePeriods",
                        String.class);

                method.invoke(_stNmDiscountProjMasterRemoteModel,
                    baselinePeriods);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getStNmDiscountProjMasterRemoteModel() {
        return _stNmDiscountProjMasterRemoteModel;
    }

    public void setStNmDiscountProjMasterRemoteModel(
        BaseModel<?> stNmDiscountProjMasterRemoteModel) {
        _stNmDiscountProjMasterRemoteModel = stNmDiscountProjMasterRemoteModel;
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

        Class<?> remoteModelClass = _stNmDiscountProjMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_stNmDiscountProjMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            StNmDiscountProjMasterLocalServiceUtil.addStNmDiscountProjMaster(this);
        } else {
            StNmDiscountProjMasterLocalServiceUtil.updateStNmDiscountProjMaster(this);
        }
    }

    @Override
    public StNmDiscountProjMaster toEscapedModel() {
        return (StNmDiscountProjMaster) ProxyUtil.newProxyInstance(StNmDiscountProjMaster.class.getClassLoader(),
            new Class[] { StNmDiscountProjMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        StNmDiscountProjMasterClp clone = new StNmDiscountProjMasterClp();

        clone.setSelectedPeriods(getSelectedPeriods());
        clone.setMethodology(getMethodology());
        clone.setNetFlag(getNetFlag());
        clone.setPriceGroupType(getPriceGroupType());
        clone.setUserGroup(getUserGroup());
        clone.setUserId(getUserId());
        clone.setLastModifiedDate(getLastModifiedDate());
        clone.setProjectionDetailsSid(getProjectionDetailsSid());
        clone.setRsModelSid(getRsModelSid());
        clone.setSessionId(getSessionId());
        clone.setCheckRecord(getCheckRecord());
        clone.setBaselinePeriods(getBaselinePeriods());

        return clone;
    }

    @Override
    public int compareTo(StNmDiscountProjMaster stNmDiscountProjMaster) {
        StNmDiscountProjMasterPK primaryKey = stNmDiscountProjMaster.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StNmDiscountProjMasterClp)) {
            return false;
        }

        StNmDiscountProjMasterClp stNmDiscountProjMaster = (StNmDiscountProjMasterClp) obj;

        StNmDiscountProjMasterPK primaryKey = stNmDiscountProjMaster.getPrimaryKey();

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
        StringBundler sb = new StringBundler(25);

        sb.append("{selectedPeriods=");
        sb.append(getSelectedPeriods());
        sb.append(", methodology=");
        sb.append(getMethodology());
        sb.append(", netFlag=");
        sb.append(getNetFlag());
        sb.append(", priceGroupType=");
        sb.append(getPriceGroupType());
        sb.append(", userGroup=");
        sb.append(getUserGroup());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", lastModifiedDate=");
        sb.append(getLastModifiedDate());
        sb.append(", projectionDetailsSid=");
        sb.append(getProjectionDetailsSid());
        sb.append(", rsModelSid=");
        sb.append(getRsModelSid());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append(", baselinePeriods=");
        sb.append(getBaselinePeriods());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(40);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.StNmDiscountProjMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>selectedPeriods</column-name><column-value><![CDATA[");
        sb.append(getSelectedPeriods());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>methodology</column-name><column-value><![CDATA[");
        sb.append(getMethodology());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netFlag</column-name><column-value><![CDATA[");
        sb.append(getNetFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceGroupType</column-name><column-value><![CDATA[");
        sb.append(getPriceGroupType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userGroup</column-name><column-value><![CDATA[");
        sb.append(getUserGroup());
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
            "<column><column-name>projectionDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getProjectionDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsModelSid</column-name><column-value><![CDATA[");
        sb.append(getRsModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sessionId</column-name><column-value><![CDATA[");
        sb.append(getSessionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>baselinePeriods</column-name><column-value><![CDATA[");
        sb.append(getBaselinePeriods());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
