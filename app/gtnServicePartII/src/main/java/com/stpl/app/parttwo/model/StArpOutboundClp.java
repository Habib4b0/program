package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.ClpSerializer;
import com.stpl.app.parttwo.service.StArpOutboundLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.StArpOutboundPK;

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


public class StArpOutboundClp extends BaseModelImpl<StArpOutbound>
    implements StArpOutbound {
    private double _salesUnitsRate;
    private String _accountType;
    private String _originalBatchId;
    private int _companyMasterSid;
    private int _brandMasterSid;
    private Date _arpApprovalDate;
    private int _userId;
    private int _arpMasterSid;
    private Date _arpCreationDate;
    private boolean _checkRecord;
    private int _arpId;
    private String _account;
    private boolean _outboundStatus;
    private int _periodSid;
    private int _itemMasterSid;
    private int _rsModelSid;
    private String _sessionId;
    private BaseModel<?> _stArpOutboundRemoteModel;

    public StArpOutboundClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return StArpOutbound.class;
    }

    @Override
    public String getModelClassName() {
        return StArpOutbound.class.getName();
    }

    @Override
    public StArpOutboundPK getPrimaryKey() {
        return new StArpOutboundPK(_companyMasterSid, _userId, _arpMasterSid,
            _arpId, _periodSid, _itemMasterSid, _rsModelSid, _sessionId);
    }

    @Override
    public void setPrimaryKey(StArpOutboundPK primaryKey) {
        setCompanyMasterSid(primaryKey.companyMasterSid);
        setUserId(primaryKey.userId);
        setArpMasterSid(primaryKey.arpMasterSid);
        setArpId(primaryKey.arpId);
        setPeriodSid(primaryKey.periodSid);
        setItemMasterSid(primaryKey.itemMasterSid);
        setRsModelSid(primaryKey.rsModelSid);
        setSessionId(primaryKey.sessionId);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new StArpOutboundPK(_companyMasterSid, _userId, _arpMasterSid,
            _arpId, _periodSid, _itemMasterSid, _rsModelSid, _sessionId);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((StArpOutboundPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("salesUnitsRate", getSalesUnitsRate());
        attributes.put("accountType", getAccountType());
        attributes.put("originalBatchId", getOriginalBatchId());
        attributes.put("companyMasterSid", getCompanyMasterSid());
        attributes.put("brandMasterSid", getBrandMasterSid());
        attributes.put("arpApprovalDate", getArpApprovalDate());
        attributes.put("userId", getUserId());
        attributes.put("arpMasterSid", getArpMasterSid());
        attributes.put("arpCreationDate", getArpCreationDate());
        attributes.put("checkRecord", getCheckRecord());
        attributes.put("arpId", getArpId());
        attributes.put("account", getAccount());
        attributes.put("outboundStatus", getOutboundStatus());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("rsModelSid", getRsModelSid());
        attributes.put("sessionId", getSessionId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double salesUnitsRate = (Double) attributes.get("salesUnitsRate");

        if (salesUnitsRate != null) {
            setSalesUnitsRate(salesUnitsRate);
        }

        String accountType = (String) attributes.get("accountType");

        if (accountType != null) {
            setAccountType(accountType);
        }

        String originalBatchId = (String) attributes.get("originalBatchId");

        if (originalBatchId != null) {
            setOriginalBatchId(originalBatchId);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }

        Integer brandMasterSid = (Integer) attributes.get("brandMasterSid");

        if (brandMasterSid != null) {
            setBrandMasterSid(brandMasterSid);
        }

        Date arpApprovalDate = (Date) attributes.get("arpApprovalDate");

        if (arpApprovalDate != null) {
            setArpApprovalDate(arpApprovalDate);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Integer arpMasterSid = (Integer) attributes.get("arpMasterSid");

        if (arpMasterSid != null) {
            setArpMasterSid(arpMasterSid);
        }

        Date arpCreationDate = (Date) attributes.get("arpCreationDate");

        if (arpCreationDate != null) {
            setArpCreationDate(arpCreationDate);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        Integer arpId = (Integer) attributes.get("arpId");

        if (arpId != null) {
            setArpId(arpId);
        }

        String account = (String) attributes.get("account");

        if (account != null) {
            setAccount(account);
        }

        Boolean outboundStatus = (Boolean) attributes.get("outboundStatus");

        if (outboundStatus != null) {
            setOutboundStatus(outboundStatus);
        }

        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Integer rsModelSid = (Integer) attributes.get("rsModelSid");

        if (rsModelSid != null) {
            setRsModelSid(rsModelSid);
        }

        String sessionId = (String) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }
    }

    @Override
    public double getSalesUnitsRate() {
        return _salesUnitsRate;
    }

    @Override
    public void setSalesUnitsRate(double salesUnitsRate) {
        _salesUnitsRate = salesUnitsRate;

        if (_stArpOutboundRemoteModel != null) {
            try {
                Class<?> clazz = _stArpOutboundRemoteModel.getClass();

                Method method = clazz.getMethod("setSalesUnitsRate",
                        double.class);

                method.invoke(_stArpOutboundRemoteModel, salesUnitsRate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAccountType() {
        return _accountType;
    }

    @Override
    public void setAccountType(String accountType) {
        _accountType = accountType;

        if (_stArpOutboundRemoteModel != null) {
            try {
                Class<?> clazz = _stArpOutboundRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountType", String.class);

                method.invoke(_stArpOutboundRemoteModel, accountType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getOriginalBatchId() {
        return _originalBatchId;
    }

    @Override
    public void setOriginalBatchId(String originalBatchId) {
        _originalBatchId = originalBatchId;

        if (_stArpOutboundRemoteModel != null) {
            try {
                Class<?> clazz = _stArpOutboundRemoteModel.getClass();

                Method method = clazz.getMethod("setOriginalBatchId",
                        String.class);

                method.invoke(_stArpOutboundRemoteModel, originalBatchId);
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

        if (_stArpOutboundRemoteModel != null) {
            try {
                Class<?> clazz = _stArpOutboundRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyMasterSid", int.class);

                method.invoke(_stArpOutboundRemoteModel, companyMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getBrandMasterSid() {
        return _brandMasterSid;
    }

    @Override
    public void setBrandMasterSid(int brandMasterSid) {
        _brandMasterSid = brandMasterSid;

        if (_stArpOutboundRemoteModel != null) {
            try {
                Class<?> clazz = _stArpOutboundRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandMasterSid", int.class);

                method.invoke(_stArpOutboundRemoteModel, brandMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getArpApprovalDate() {
        return _arpApprovalDate;
    }

    @Override
    public void setArpApprovalDate(Date arpApprovalDate) {
        _arpApprovalDate = arpApprovalDate;

        if (_stArpOutboundRemoteModel != null) {
            try {
                Class<?> clazz = _stArpOutboundRemoteModel.getClass();

                Method method = clazz.getMethod("setArpApprovalDate", Date.class);

                method.invoke(_stArpOutboundRemoteModel, arpApprovalDate);
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

        if (_stArpOutboundRemoteModel != null) {
            try {
                Class<?> clazz = _stArpOutboundRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", int.class);

                method.invoke(_stArpOutboundRemoteModel, userId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getArpMasterSid() {
        return _arpMasterSid;
    }

    @Override
    public void setArpMasterSid(int arpMasterSid) {
        _arpMasterSid = arpMasterSid;

        if (_stArpOutboundRemoteModel != null) {
            try {
                Class<?> clazz = _stArpOutboundRemoteModel.getClass();

                Method method = clazz.getMethod("setArpMasterSid", int.class);

                method.invoke(_stArpOutboundRemoteModel, arpMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getArpCreationDate() {
        return _arpCreationDate;
    }

    @Override
    public void setArpCreationDate(Date arpCreationDate) {
        _arpCreationDate = arpCreationDate;

        if (_stArpOutboundRemoteModel != null) {
            try {
                Class<?> clazz = _stArpOutboundRemoteModel.getClass();

                Method method = clazz.getMethod("setArpCreationDate", Date.class);

                method.invoke(_stArpOutboundRemoteModel, arpCreationDate);
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

        if (_stArpOutboundRemoteModel != null) {
            try {
                Class<?> clazz = _stArpOutboundRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_stArpOutboundRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getArpId() {
        return _arpId;
    }

    @Override
    public void setArpId(int arpId) {
        _arpId = arpId;

        if (_stArpOutboundRemoteModel != null) {
            try {
                Class<?> clazz = _stArpOutboundRemoteModel.getClass();

                Method method = clazz.getMethod("setArpId", int.class);

                method.invoke(_stArpOutboundRemoteModel, arpId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAccount() {
        return _account;
    }

    @Override
    public void setAccount(String account) {
        _account = account;

        if (_stArpOutboundRemoteModel != null) {
            try {
                Class<?> clazz = _stArpOutboundRemoteModel.getClass();

                Method method = clazz.getMethod("setAccount", String.class);

                method.invoke(_stArpOutboundRemoteModel, account);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getOutboundStatus() {
        return _outboundStatus;
    }

    @Override
    public boolean isOutboundStatus() {
        return _outboundStatus;
    }

    @Override
    public void setOutboundStatus(boolean outboundStatus) {
        _outboundStatus = outboundStatus;

        if (_stArpOutboundRemoteModel != null) {
            try {
                Class<?> clazz = _stArpOutboundRemoteModel.getClass();

                Method method = clazz.getMethod("setOutboundStatus",
                        boolean.class);

                method.invoke(_stArpOutboundRemoteModel, outboundStatus);
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

        if (_stArpOutboundRemoteModel != null) {
            try {
                Class<?> clazz = _stArpOutboundRemoteModel.getClass();

                Method method = clazz.getMethod("setPeriodSid", int.class);

                method.invoke(_stArpOutboundRemoteModel, periodSid);
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

        if (_stArpOutboundRemoteModel != null) {
            try {
                Class<?> clazz = _stArpOutboundRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_stArpOutboundRemoteModel, itemMasterSid);
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

        if (_stArpOutboundRemoteModel != null) {
            try {
                Class<?> clazz = _stArpOutboundRemoteModel.getClass();

                Method method = clazz.getMethod("setRsModelSid", int.class);

                method.invoke(_stArpOutboundRemoteModel, rsModelSid);
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

        if (_stArpOutboundRemoteModel != null) {
            try {
                Class<?> clazz = _stArpOutboundRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", String.class);

                method.invoke(_stArpOutboundRemoteModel, sessionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getStArpOutboundRemoteModel() {
        return _stArpOutboundRemoteModel;
    }

    public void setStArpOutboundRemoteModel(
        BaseModel<?> stArpOutboundRemoteModel) {
        _stArpOutboundRemoteModel = stArpOutboundRemoteModel;
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

        Class<?> remoteModelClass = _stArpOutboundRemoteModel.getClass();

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

        Object returnValue = method.invoke(_stArpOutboundRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            StArpOutboundLocalServiceUtil.addStArpOutbound(this);
        } else {
            StArpOutboundLocalServiceUtil.updateStArpOutbound(this);
        }
    }

    @Override
    public StArpOutbound toEscapedModel() {
        return (StArpOutbound) ProxyUtil.newProxyInstance(StArpOutbound.class.getClassLoader(),
            new Class[] { StArpOutbound.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        StArpOutboundClp clone = new StArpOutboundClp();

        clone.setSalesUnitsRate(getSalesUnitsRate());
        clone.setAccountType(getAccountType());
        clone.setOriginalBatchId(getOriginalBatchId());
        clone.setCompanyMasterSid(getCompanyMasterSid());
        clone.setBrandMasterSid(getBrandMasterSid());
        clone.setArpApprovalDate(getArpApprovalDate());
        clone.setUserId(getUserId());
        clone.setArpMasterSid(getArpMasterSid());
        clone.setArpCreationDate(getArpCreationDate());
        clone.setCheckRecord(getCheckRecord());
        clone.setArpId(getArpId());
        clone.setAccount(getAccount());
        clone.setOutboundStatus(getOutboundStatus());
        clone.setPeriodSid(getPeriodSid());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setRsModelSid(getRsModelSid());
        clone.setSessionId(getSessionId());

        return clone;
    }

    @Override
    public int compareTo(StArpOutbound stArpOutbound) {
        StArpOutboundPK primaryKey = stArpOutbound.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StArpOutboundClp)) {
            return false;
        }

        StArpOutboundClp stArpOutbound = (StArpOutboundClp) obj;

        StArpOutboundPK primaryKey = stArpOutbound.getPrimaryKey();

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
        StringBundler sb = new StringBundler(35);

        sb.append("{salesUnitsRate=");
        sb.append(getSalesUnitsRate());
        sb.append(", accountType=");
        sb.append(getAccountType());
        sb.append(", originalBatchId=");
        sb.append(getOriginalBatchId());
        sb.append(", companyMasterSid=");
        sb.append(getCompanyMasterSid());
        sb.append(", brandMasterSid=");
        sb.append(getBrandMasterSid());
        sb.append(", arpApprovalDate=");
        sb.append(getArpApprovalDate());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", arpMasterSid=");
        sb.append(getArpMasterSid());
        sb.append(", arpCreationDate=");
        sb.append(getArpCreationDate());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append(", arpId=");
        sb.append(getArpId());
        sb.append(", account=");
        sb.append(getAccount());
        sb.append(", outboundStatus=");
        sb.append(getOutboundStatus());
        sb.append(", periodSid=");
        sb.append(getPeriodSid());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", rsModelSid=");
        sb.append(getRsModelSid());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(55);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.StArpOutbound");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>salesUnitsRate</column-name><column-value><![CDATA[");
        sb.append(getSalesUnitsRate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accountType</column-name><column-value><![CDATA[");
        sb.append(getAccountType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>originalBatchId</column-name><column-value><![CDATA[");
        sb.append(getOriginalBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brandMasterSid</column-name><column-value><![CDATA[");
        sb.append(getBrandMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>arpApprovalDate</column-name><column-value><![CDATA[");
        sb.append(getArpApprovalDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>arpMasterSid</column-name><column-value><![CDATA[");
        sb.append(getArpMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>arpCreationDate</column-name><column-value><![CDATA[");
        sb.append(getArpCreationDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>arpId</column-name><column-value><![CDATA[");
        sb.append(getArpId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>account</column-name><column-value><![CDATA[");
        sb.append(getAccount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>outboundStatus</column-name><column-value><![CDATA[");
        sb.append(getOutboundStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>periodSid</column-name><column-value><![CDATA[");
        sb.append(getPeriodSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsModelSid</column-name><column-value><![CDATA[");
        sb.append(getRsModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sessionId</column-name><column-value><![CDATA[");
        sb.append(getSessionId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
