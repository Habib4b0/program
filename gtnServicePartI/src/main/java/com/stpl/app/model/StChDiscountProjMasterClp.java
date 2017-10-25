package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.StChDiscountProjMasterLocalServiceUtil;
import com.stpl.app.service.persistence.StChDiscountProjMasterPK;

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


public class StChDiscountProjMasterClp extends BaseModelImpl<StChDiscountProjMaster>
    implements StChDiscountProjMaster {
    private boolean _checkRecord;
    private String _selectedPeriods;
    private Date _lastModifiedDate;
    private int _projectionDetailsSid;
    private String _priceGroupType;
    private int _userId;
    private String _netFlag;
    private String _projectedType;
    private String _baselinePeriods;
    private int _sessionId;
    private String _methodology;
    private int _rsModelSid;
    private String _discountType;
    private BaseModel<?> _stChDiscountProjMasterRemoteModel;

    public StChDiscountProjMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return StChDiscountProjMaster.class;
    }

    @Override
    public String getModelClassName() {
        return StChDiscountProjMaster.class.getName();
    }

    @Override
    public StChDiscountProjMasterPK getPrimaryKey() {
        return new StChDiscountProjMasterPK(_projectionDetailsSid, _userId,
            _sessionId, _rsModelSid);
    }

    @Override
    public void setPrimaryKey(StChDiscountProjMasterPK primaryKey) {
        setProjectionDetailsSid(primaryKey.projectionDetailsSid);
        setUserId(primaryKey.userId);
        setSessionId(primaryKey.sessionId);
        setRsModelSid(primaryKey.rsModelSid);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new StChDiscountProjMasterPK(_projectionDetailsSid, _userId,
            _sessionId, _rsModelSid);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((StChDiscountProjMasterPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("checkRecord", getCheckRecord());
        attributes.put("selectedPeriods", getSelectedPeriods());
        attributes.put("lastModifiedDate", getLastModifiedDate());
        attributes.put("projectionDetailsSid", getProjectionDetailsSid());
        attributes.put("priceGroupType", getPriceGroupType());
        attributes.put("userId", getUserId());
        attributes.put("netFlag", getNetFlag());
        attributes.put("projectedType", getProjectedType());
        attributes.put("baselinePeriods", getBaselinePeriods());
        attributes.put("sessionId", getSessionId());
        attributes.put("methodology", getMethodology());
        attributes.put("rsModelSid", getRsModelSid());
        attributes.put("discountType", getDiscountType());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        String selectedPeriods = (String) attributes.get("selectedPeriods");

        if (selectedPeriods != null) {
            setSelectedPeriods(selectedPeriods);
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

        String priceGroupType = (String) attributes.get("priceGroupType");

        if (priceGroupType != null) {
            setPriceGroupType(priceGroupType);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        String netFlag = (String) attributes.get("netFlag");

        if (netFlag != null) {
            setNetFlag(netFlag);
        }

        String projectedType = (String) attributes.get("projectedType");

        if (projectedType != null) {
            setProjectedType(projectedType);
        }

        String baselinePeriods = (String) attributes.get("baselinePeriods");

        if (baselinePeriods != null) {
            setBaselinePeriods(baselinePeriods);
        }

        Integer sessionId = (Integer) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        String methodology = (String) attributes.get("methodology");

        if (methodology != null) {
            setMethodology(methodology);
        }

        Integer rsModelSid = (Integer) attributes.get("rsModelSid");

        if (rsModelSid != null) {
            setRsModelSid(rsModelSid);
        }

        String discountType = (String) attributes.get("discountType");

        if (discountType != null) {
            setDiscountType(discountType);
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

        if (_stChDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stChDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_stChDiscountProjMasterRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSelectedPeriods() {
        return _selectedPeriods;
    }

    @Override
    public void setSelectedPeriods(String selectedPeriods) {
        _selectedPeriods = selectedPeriods;

        if (_stChDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stChDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSelectedPeriods",
                        String.class);

                method.invoke(_stChDiscountProjMasterRemoteModel,
                    selectedPeriods);
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

        if (_stChDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stChDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLastModifiedDate",
                        Date.class);

                method.invoke(_stChDiscountProjMasterRemoteModel,
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

        if (_stChDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stChDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionDetailsSid",
                        int.class);

                method.invoke(_stChDiscountProjMasterRemoteModel,
                    projectionDetailsSid);
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

        if (_stChDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stChDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceGroupType",
                        String.class);

                method.invoke(_stChDiscountProjMasterRemoteModel, priceGroupType);
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

        if (_stChDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stChDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", int.class);

                method.invoke(_stChDiscountProjMasterRemoteModel, userId);
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

        if (_stChDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stChDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNetFlag", String.class);

                method.invoke(_stChDiscountProjMasterRemoteModel, netFlag);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getProjectedType() {
        return _projectedType;
    }

    @Override
    public void setProjectedType(String projectedType) {
        _projectedType = projectedType;

        if (_stChDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stChDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectedType", String.class);

                method.invoke(_stChDiscountProjMasterRemoteModel, projectedType);
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

        if (_stChDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stChDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBaselinePeriods",
                        String.class);

                method.invoke(_stChDiscountProjMasterRemoteModel,
                    baselinePeriods);
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

        if (_stChDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stChDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", int.class);

                method.invoke(_stChDiscountProjMasterRemoteModel, sessionId);
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

        if (_stChDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stChDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setMethodology", String.class);

                method.invoke(_stChDiscountProjMasterRemoteModel, methodology);
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

        if (_stChDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stChDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRsModelSid", int.class);

                method.invoke(_stChDiscountProjMasterRemoteModel, rsModelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDiscountType() {
        return _discountType;
    }

    @Override
    public void setDiscountType(String discountType) {
        _discountType = discountType;

        if (_stChDiscountProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stChDiscountProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDiscountType", String.class);

                method.invoke(_stChDiscountProjMasterRemoteModel, discountType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getStChDiscountProjMasterRemoteModel() {
        return _stChDiscountProjMasterRemoteModel;
    }

    public void setStChDiscountProjMasterRemoteModel(
        BaseModel<?> stChDiscountProjMasterRemoteModel) {
        _stChDiscountProjMasterRemoteModel = stChDiscountProjMasterRemoteModel;
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

        Class<?> remoteModelClass = _stChDiscountProjMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_stChDiscountProjMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            StChDiscountProjMasterLocalServiceUtil.addStChDiscountProjMaster(this);
        } else {
            StChDiscountProjMasterLocalServiceUtil.updateStChDiscountProjMaster(this);
        }
    }

    @Override
    public StChDiscountProjMaster toEscapedModel() {
        return (StChDiscountProjMaster) ProxyUtil.newProxyInstance(StChDiscountProjMaster.class.getClassLoader(),
            new Class[] { StChDiscountProjMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        StChDiscountProjMasterClp clone = new StChDiscountProjMasterClp();

        clone.setCheckRecord(getCheckRecord());
        clone.setSelectedPeriods(getSelectedPeriods());
        clone.setLastModifiedDate(getLastModifiedDate());
        clone.setProjectionDetailsSid(getProjectionDetailsSid());
        clone.setPriceGroupType(getPriceGroupType());
        clone.setUserId(getUserId());
        clone.setNetFlag(getNetFlag());
        clone.setProjectedType(getProjectedType());
        clone.setBaselinePeriods(getBaselinePeriods());
        clone.setSessionId(getSessionId());
        clone.setMethodology(getMethodology());
        clone.setRsModelSid(getRsModelSid());
        clone.setDiscountType(getDiscountType());

        return clone;
    }

    @Override
    public int compareTo(StChDiscountProjMaster stChDiscountProjMaster) {
        StChDiscountProjMasterPK primaryKey = stChDiscountProjMaster.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StChDiscountProjMasterClp)) {
            return false;
        }

        StChDiscountProjMasterClp stChDiscountProjMaster = (StChDiscountProjMasterClp) obj;

        StChDiscountProjMasterPK primaryKey = stChDiscountProjMaster.getPrimaryKey();

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
        StringBundler sb = new StringBundler(27);

        sb.append("{checkRecord=");
        sb.append(getCheckRecord());
        sb.append(", selectedPeriods=");
        sb.append(getSelectedPeriods());
        sb.append(", lastModifiedDate=");
        sb.append(getLastModifiedDate());
        sb.append(", projectionDetailsSid=");
        sb.append(getProjectionDetailsSid());
        sb.append(", priceGroupType=");
        sb.append(getPriceGroupType());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", netFlag=");
        sb.append(getNetFlag());
        sb.append(", projectedType=");
        sb.append(getProjectedType());
        sb.append(", baselinePeriods=");
        sb.append(getBaselinePeriods());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append(", methodology=");
        sb.append(getMethodology());
        sb.append(", rsModelSid=");
        sb.append(getRsModelSid());
        sb.append(", discountType=");
        sb.append(getDiscountType());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(43);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.StChDiscountProjMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>selectedPeriods</column-name><column-value><![CDATA[");
        sb.append(getSelectedPeriods());
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
            "<column><column-name>priceGroupType</column-name><column-value><![CDATA[");
        sb.append(getPriceGroupType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netFlag</column-name><column-value><![CDATA[");
        sb.append(getNetFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectedType</column-name><column-value><![CDATA[");
        sb.append(getProjectedType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>baselinePeriods</column-name><column-value><![CDATA[");
        sb.append(getBaselinePeriods());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sessionId</column-name><column-value><![CDATA[");
        sb.append(getSessionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>methodology</column-name><column-value><![CDATA[");
        sb.append(getMethodology());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsModelSid</column-name><column-value><![CDATA[");
        sb.append(getRsModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>discountType</column-name><column-value><![CDATA[");
        sb.append(getDiscountType());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
