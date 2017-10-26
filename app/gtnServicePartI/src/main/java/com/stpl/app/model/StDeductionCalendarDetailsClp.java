package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.StDeductionCalendarDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.StDeductionCalendarDetailsPK;

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


public class StDeductionCalendarDetailsClp extends BaseModelImpl<StDeductionCalendarDetails>
    implements StDeductionCalendarDetails {
    private String _adjustmentBasis;
    private int _periodSid;
    private String _adjustmentValue;
    private String _adjustmentAllocationMethodology;
    private int _companyMasterSid;
    private int _discountAmount;
    private String _adjustmentVariable;
    private int _userId;
    private int _itemMasterSid;
    private String _adjustmentType;
    private String _sessionId;
    private boolean _checkRecord;
    private BaseModel<?> _stDeductionCalendarDetailsRemoteModel;

    public StDeductionCalendarDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return StDeductionCalendarDetails.class;
    }

    @Override
    public String getModelClassName() {
        return StDeductionCalendarDetails.class.getName();
    }

    @Override
    public StDeductionCalendarDetailsPK getPrimaryKey() {
        return new StDeductionCalendarDetailsPK(_periodSid, _companyMasterSid,
            _userId, _itemMasterSid, _sessionId);
    }

    @Override
    public void setPrimaryKey(StDeductionCalendarDetailsPK primaryKey) {
        setPeriodSid(primaryKey.periodSid);
        setCompanyMasterSid(primaryKey.companyMasterSid);
        setUserId(primaryKey.userId);
        setItemMasterSid(primaryKey.itemMasterSid);
        setSessionId(primaryKey.sessionId);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new StDeductionCalendarDetailsPK(_periodSid, _companyMasterSid,
            _userId, _itemMasterSid, _sessionId);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((StDeductionCalendarDetailsPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("adjustmentBasis", getAdjustmentBasis());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("adjustmentValue", getAdjustmentValue());
        attributes.put("adjustmentAllocationMethodology",
            getAdjustmentAllocationMethodology());
        attributes.put("companyMasterSid", getCompanyMasterSid());
        attributes.put("discountAmount", getDiscountAmount());
        attributes.put("adjustmentVariable", getAdjustmentVariable());
        attributes.put("userId", getUserId());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("adjustmentType", getAdjustmentType());
        attributes.put("sessionId", getSessionId());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String adjustmentBasis = (String) attributes.get("adjustmentBasis");

        if (adjustmentBasis != null) {
            setAdjustmentBasis(adjustmentBasis);
        }

        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        String adjustmentValue = (String) attributes.get("adjustmentValue");

        if (adjustmentValue != null) {
            setAdjustmentValue(adjustmentValue);
        }

        String adjustmentAllocationMethodology = (String) attributes.get(
                "adjustmentAllocationMethodology");

        if (adjustmentAllocationMethodology != null) {
            setAdjustmentAllocationMethodology(adjustmentAllocationMethodology);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }

        Integer discountAmount = (Integer) attributes.get("discountAmount");

        if (discountAmount != null) {
            setDiscountAmount(discountAmount);
        }

        String adjustmentVariable = (String) attributes.get(
                "adjustmentVariable");

        if (adjustmentVariable != null) {
            setAdjustmentVariable(adjustmentVariable);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        String adjustmentType = (String) attributes.get("adjustmentType");

        if (adjustmentType != null) {
            setAdjustmentType(adjustmentType);
        }

        String sessionId = (String) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    @Override
    public String getAdjustmentBasis() {
        return _adjustmentBasis;
    }

    @Override
    public void setAdjustmentBasis(String adjustmentBasis) {
        _adjustmentBasis = adjustmentBasis;

        if (_stDeductionCalendarDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _stDeductionCalendarDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustmentBasis",
                        String.class);

                method.invoke(_stDeductionCalendarDetailsRemoteModel,
                    adjustmentBasis);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPeriodSid() {
        return _periodSid;
    }

    @Override
    public void setPeriodSid(int periodSid) {
        _periodSid = periodSid;

        if (_stDeductionCalendarDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _stDeductionCalendarDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPeriodSid", int.class);

                method.invoke(_stDeductionCalendarDetailsRemoteModel, periodSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAdjustmentValue() {
        return _adjustmentValue;
    }

    @Override
    public void setAdjustmentValue(String adjustmentValue) {
        _adjustmentValue = adjustmentValue;

        if (_stDeductionCalendarDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _stDeductionCalendarDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustmentValue",
                        String.class);

                method.invoke(_stDeductionCalendarDetailsRemoteModel,
                    adjustmentValue);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAdjustmentAllocationMethodology() {
        return _adjustmentAllocationMethodology;
    }

    @Override
    public void setAdjustmentAllocationMethodology(
        String adjustmentAllocationMethodology) {
        _adjustmentAllocationMethodology = adjustmentAllocationMethodology;

        if (_stDeductionCalendarDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _stDeductionCalendarDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustmentAllocationMethodology",
                        String.class);

                method.invoke(_stDeductionCalendarDetailsRemoteModel,
                    adjustmentAllocationMethodology);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCompanyMasterSid() {
        return _companyMasterSid;
    }

    @Override
    public void setCompanyMasterSid(int companyMasterSid) {
        _companyMasterSid = companyMasterSid;

        if (_stDeductionCalendarDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _stDeductionCalendarDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyMasterSid", int.class);

                method.invoke(_stDeductionCalendarDetailsRemoteModel,
                    companyMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getDiscountAmount() {
        return _discountAmount;
    }

    @Override
    public void setDiscountAmount(int discountAmount) {
        _discountAmount = discountAmount;

        if (_stDeductionCalendarDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _stDeductionCalendarDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setDiscountAmount", int.class);

                method.invoke(_stDeductionCalendarDetailsRemoteModel,
                    discountAmount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAdjustmentVariable() {
        return _adjustmentVariable;
    }

    @Override
    public void setAdjustmentVariable(String adjustmentVariable) {
        _adjustmentVariable = adjustmentVariable;

        if (_stDeductionCalendarDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _stDeductionCalendarDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustmentVariable",
                        String.class);

                method.invoke(_stDeductionCalendarDetailsRemoteModel,
                    adjustmentVariable);
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

        if (_stDeductionCalendarDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _stDeductionCalendarDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", int.class);

                method.invoke(_stDeductionCalendarDetailsRemoteModel, userId);
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

        if (_stDeductionCalendarDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _stDeductionCalendarDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_stDeductionCalendarDetailsRemoteModel,
                    itemMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAdjustmentType() {
        return _adjustmentType;
    }

    @Override
    public void setAdjustmentType(String adjustmentType) {
        _adjustmentType = adjustmentType;

        if (_stDeductionCalendarDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _stDeductionCalendarDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustmentType",
                        String.class);

                method.invoke(_stDeductionCalendarDetailsRemoteModel,
                    adjustmentType);
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

        if (_stDeductionCalendarDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _stDeductionCalendarDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", String.class);

                method.invoke(_stDeductionCalendarDetailsRemoteModel, sessionId);
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

        if (_stDeductionCalendarDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _stDeductionCalendarDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_stDeductionCalendarDetailsRemoteModel,
                    checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getStDeductionCalendarDetailsRemoteModel() {
        return _stDeductionCalendarDetailsRemoteModel;
    }

    public void setStDeductionCalendarDetailsRemoteModel(
        BaseModel<?> stDeductionCalendarDetailsRemoteModel) {
        _stDeductionCalendarDetailsRemoteModel = stDeductionCalendarDetailsRemoteModel;
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

        Class<?> remoteModelClass = _stDeductionCalendarDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_stDeductionCalendarDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            StDeductionCalendarDetailsLocalServiceUtil.addStDeductionCalendarDetails(this);
        } else {
            StDeductionCalendarDetailsLocalServiceUtil.updateStDeductionCalendarDetails(this);
        }
    }

    @Override
    public StDeductionCalendarDetails toEscapedModel() {
        return (StDeductionCalendarDetails) ProxyUtil.newProxyInstance(StDeductionCalendarDetails.class.getClassLoader(),
            new Class[] { StDeductionCalendarDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        StDeductionCalendarDetailsClp clone = new StDeductionCalendarDetailsClp();

        clone.setAdjustmentBasis(getAdjustmentBasis());
        clone.setPeriodSid(getPeriodSid());
        clone.setAdjustmentValue(getAdjustmentValue());
        clone.setAdjustmentAllocationMethodology(getAdjustmentAllocationMethodology());
        clone.setCompanyMasterSid(getCompanyMasterSid());
        clone.setDiscountAmount(getDiscountAmount());
        clone.setAdjustmentVariable(getAdjustmentVariable());
        clone.setUserId(getUserId());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setAdjustmentType(getAdjustmentType());
        clone.setSessionId(getSessionId());
        clone.setCheckRecord(getCheckRecord());

        return clone;
    }

    @Override
    public int compareTo(StDeductionCalendarDetails stDeductionCalendarDetails) {
        StDeductionCalendarDetailsPK primaryKey = stDeductionCalendarDetails.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StDeductionCalendarDetailsClp)) {
            return false;
        }

        StDeductionCalendarDetailsClp stDeductionCalendarDetails = (StDeductionCalendarDetailsClp) obj;

        StDeductionCalendarDetailsPK primaryKey = stDeductionCalendarDetails.getPrimaryKey();

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

        sb.append("{adjustmentBasis=");
        sb.append(getAdjustmentBasis());
        sb.append(", periodSid=");
        sb.append(getPeriodSid());
        sb.append(", adjustmentValue=");
        sb.append(getAdjustmentValue());
        sb.append(", adjustmentAllocationMethodology=");
        sb.append(getAdjustmentAllocationMethodology());
        sb.append(", companyMasterSid=");
        sb.append(getCompanyMasterSid());
        sb.append(", discountAmount=");
        sb.append(getDiscountAmount());
        sb.append(", adjustmentVariable=");
        sb.append(getAdjustmentVariable());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", adjustmentType=");
        sb.append(getAdjustmentType());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(40);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.StDeductionCalendarDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>adjustmentBasis</column-name><column-value><![CDATA[");
        sb.append(getAdjustmentBasis());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>periodSid</column-name><column-value><![CDATA[");
        sb.append(getPeriodSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>adjustmentValue</column-name><column-value><![CDATA[");
        sb.append(getAdjustmentValue());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>adjustmentAllocationMethodology</column-name><column-value><![CDATA[");
        sb.append(getAdjustmentAllocationMethodology());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>discountAmount</column-name><column-value><![CDATA[");
        sb.append(getDiscountAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>adjustmentVariable</column-name><column-value><![CDATA[");
        sb.append(getAdjustmentVariable());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>adjustmentType</column-name><column-value><![CDATA[");
        sb.append(getAdjustmentType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sessionId</column-name><column-value><![CDATA[");
        sb.append(getSessionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
