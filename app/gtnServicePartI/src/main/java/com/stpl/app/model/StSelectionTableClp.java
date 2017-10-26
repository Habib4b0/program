package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.StSelectionTableLocalServiceUtil;
import com.stpl.app.service.persistence.StSelectionTablePK;

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


public class StSelectionTableClp extends BaseModelImpl<StSelectionTable>
    implements StSelectionTable {
    private String _selectionType;
    private int _userId;
    private String _sessionId;
    private int _companyItemSid;
    private boolean _checkRecord;
    private BaseModel<?> _stSelectionTableRemoteModel;

    public StSelectionTableClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return StSelectionTable.class;
    }

    @Override
    public String getModelClassName() {
        return StSelectionTable.class.getName();
    }

    @Override
    public StSelectionTablePK getPrimaryKey() {
        return new StSelectionTablePK(_selectionType, _userId, _sessionId,
            _companyItemSid);
    }

    @Override
    public void setPrimaryKey(StSelectionTablePK primaryKey) {
        setSelectionType(primaryKey.selectionType);
        setUserId(primaryKey.userId);
        setSessionId(primaryKey.sessionId);
        setCompanyItemSid(primaryKey.companyItemSid);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new StSelectionTablePK(_selectionType, _userId, _sessionId,
            _companyItemSid);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((StSelectionTablePK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("selectionType", getSelectionType());
        attributes.put("userId", getUserId());
        attributes.put("sessionId", getSessionId());
        attributes.put("companyItemSid", getCompanyItemSid());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String selectionType = (String) attributes.get("selectionType");

        if (selectionType != null) {
            setSelectionType(selectionType);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        String sessionId = (String) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        Integer companyItemSid = (Integer) attributes.get("companyItemSid");

        if (companyItemSid != null) {
            setCompanyItemSid(companyItemSid);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    @Override
    public String getSelectionType() {
        return _selectionType;
    }

    @Override
    public void setSelectionType(String selectionType) {
        _selectionType = selectionType;

        if (_stSelectionTableRemoteModel != null) {
            try {
                Class<?> clazz = _stSelectionTableRemoteModel.getClass();

                Method method = clazz.getMethod("setSelectionType", String.class);

                method.invoke(_stSelectionTableRemoteModel, selectionType);
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

        if (_stSelectionTableRemoteModel != null) {
            try {
                Class<?> clazz = _stSelectionTableRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", int.class);

                method.invoke(_stSelectionTableRemoteModel, userId);
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

        if (_stSelectionTableRemoteModel != null) {
            try {
                Class<?> clazz = _stSelectionTableRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", String.class);

                method.invoke(_stSelectionTableRemoteModel, sessionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCompanyItemSid() {
        return _companyItemSid;
    }

    @Override
    public void setCompanyItemSid(int companyItemSid) {
        _companyItemSid = companyItemSid;

        if (_stSelectionTableRemoteModel != null) {
            try {
                Class<?> clazz = _stSelectionTableRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyItemSid", int.class);

                method.invoke(_stSelectionTableRemoteModel, companyItemSid);
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

        if (_stSelectionTableRemoteModel != null) {
            try {
                Class<?> clazz = _stSelectionTableRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_stSelectionTableRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getStSelectionTableRemoteModel() {
        return _stSelectionTableRemoteModel;
    }

    public void setStSelectionTableRemoteModel(
        BaseModel<?> stSelectionTableRemoteModel) {
        _stSelectionTableRemoteModel = stSelectionTableRemoteModel;
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

        Class<?> remoteModelClass = _stSelectionTableRemoteModel.getClass();

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

        Object returnValue = method.invoke(_stSelectionTableRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            StSelectionTableLocalServiceUtil.addStSelectionTable(this);
        } else {
            StSelectionTableLocalServiceUtil.updateStSelectionTable(this);
        }
    }

    @Override
    public StSelectionTable toEscapedModel() {
        return (StSelectionTable) ProxyUtil.newProxyInstance(StSelectionTable.class.getClassLoader(),
            new Class[] { StSelectionTable.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        StSelectionTableClp clone = new StSelectionTableClp();

        clone.setSelectionType(getSelectionType());
        clone.setUserId(getUserId());
        clone.setSessionId(getSessionId());
        clone.setCompanyItemSid(getCompanyItemSid());
        clone.setCheckRecord(getCheckRecord());

        return clone;
    }

    @Override
    public int compareTo(StSelectionTable stSelectionTable) {
        StSelectionTablePK primaryKey = stSelectionTable.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StSelectionTableClp)) {
            return false;
        }

        StSelectionTableClp stSelectionTable = (StSelectionTableClp) obj;

        StSelectionTablePK primaryKey = stSelectionTable.getPrimaryKey();

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
        StringBundler sb = new StringBundler(11);

        sb.append("{selectionType=");
        sb.append(getSelectionType());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append(", companyItemSid=");
        sb.append(getCompanyItemSid());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(19);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.StSelectionTable");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>selectionType</column-name><column-value><![CDATA[");
        sb.append(getSelectionType());
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
            "<column><column-name>companyItemSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyItemSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
