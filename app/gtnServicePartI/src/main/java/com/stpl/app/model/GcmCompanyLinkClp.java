package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.GcmCompanyLinkLocalServiceUtil;

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


public class GcmCompanyLinkClp extends BaseModelImpl<GcmCompanyLink>
    implements GcmCompanyLink {
    private boolean _checkRecord;
    private int _userId;
    private String _companyNo;
    private String _companyId;
    private int _gcmCompanyLinkSid;
    private String _sessionId;
    private String _companyName;
    private String _linkId;
    private int _companyMasterSid;
    private BaseModel<?> _gcmCompanyLinkRemoteModel;

    public GcmCompanyLinkClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return GcmCompanyLink.class;
    }

    @Override
    public String getModelClassName() {
        return GcmCompanyLink.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _gcmCompanyLinkSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setGcmCompanyLinkSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _gcmCompanyLinkSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("checkRecord", getCheckRecord());
        attributes.put("userId", getUserId());
        attributes.put("companyNo", getCompanyNo());
        attributes.put("companyId", getCompanyId());
        attributes.put("gcmCompanyLinkSid", getGcmCompanyLinkSid());
        attributes.put("sessionId", getSessionId());
        attributes.put("companyName", getCompanyName());
        attributes.put("linkId", getLinkId());
        attributes.put("companyMasterSid", getCompanyMasterSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        String companyNo = (String) attributes.get("companyNo");

        if (companyNo != null) {
            setCompanyNo(companyNo);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        Integer gcmCompanyLinkSid = (Integer) attributes.get(
                "gcmCompanyLinkSid");

        if (gcmCompanyLinkSid != null) {
            setGcmCompanyLinkSid(gcmCompanyLinkSid);
        }

        String sessionId = (String) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        String companyName = (String) attributes.get("companyName");

        if (companyName != null) {
            setCompanyName(companyName);
        }

        String linkId = (String) attributes.get("linkId");

        if (linkId != null) {
            setLinkId(linkId);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
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

        if (_gcmCompanyLinkRemoteModel != null) {
            try {
                Class<?> clazz = _gcmCompanyLinkRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_gcmCompanyLinkRemoteModel, checkRecord);
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

        if (_gcmCompanyLinkRemoteModel != null) {
            try {
                Class<?> clazz = _gcmCompanyLinkRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", int.class);

                method.invoke(_gcmCompanyLinkRemoteModel, userId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyNo() {
        return _companyNo;
    }

    @Override
    public void setCompanyNo(String companyNo) {
        _companyNo = companyNo;

        if (_gcmCompanyLinkRemoteModel != null) {
            try {
                Class<?> clazz = _gcmCompanyLinkRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyNo", String.class);

                method.invoke(_gcmCompanyLinkRemoteModel, companyNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyId() {
        return _companyId;
    }

    @Override
    public void setCompanyId(String companyId) {
        _companyId = companyId;

        if (_gcmCompanyLinkRemoteModel != null) {
            try {
                Class<?> clazz = _gcmCompanyLinkRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyId", String.class);

                method.invoke(_gcmCompanyLinkRemoteModel, companyId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getGcmCompanyLinkSid() {
        return _gcmCompanyLinkSid;
    }

    @Override
    public void setGcmCompanyLinkSid(int gcmCompanyLinkSid) {
        _gcmCompanyLinkSid = gcmCompanyLinkSid;

        if (_gcmCompanyLinkRemoteModel != null) {
            try {
                Class<?> clazz = _gcmCompanyLinkRemoteModel.getClass();

                Method method = clazz.getMethod("setGcmCompanyLinkSid",
                        int.class);

                method.invoke(_gcmCompanyLinkRemoteModel, gcmCompanyLinkSid);
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

        if (_gcmCompanyLinkRemoteModel != null) {
            try {
                Class<?> clazz = _gcmCompanyLinkRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", String.class);

                method.invoke(_gcmCompanyLinkRemoteModel, sessionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyName() {
        return _companyName;
    }

    @Override
    public void setCompanyName(String companyName) {
        _companyName = companyName;

        if (_gcmCompanyLinkRemoteModel != null) {
            try {
                Class<?> clazz = _gcmCompanyLinkRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyName", String.class);

                method.invoke(_gcmCompanyLinkRemoteModel, companyName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLinkId() {
        return _linkId;
    }

    @Override
    public void setLinkId(String linkId) {
        _linkId = linkId;

        if (_gcmCompanyLinkRemoteModel != null) {
            try {
                Class<?> clazz = _gcmCompanyLinkRemoteModel.getClass();

                Method method = clazz.getMethod("setLinkId", String.class);

                method.invoke(_gcmCompanyLinkRemoteModel, linkId);
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

        if (_gcmCompanyLinkRemoteModel != null) {
            try {
                Class<?> clazz = _gcmCompanyLinkRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyMasterSid", int.class);

                method.invoke(_gcmCompanyLinkRemoteModel, companyMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getGcmCompanyLinkRemoteModel() {
        return _gcmCompanyLinkRemoteModel;
    }

    public void setGcmCompanyLinkRemoteModel(
        BaseModel<?> gcmCompanyLinkRemoteModel) {
        _gcmCompanyLinkRemoteModel = gcmCompanyLinkRemoteModel;
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

        Class<?> remoteModelClass = _gcmCompanyLinkRemoteModel.getClass();

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

        Object returnValue = method.invoke(_gcmCompanyLinkRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            GcmCompanyLinkLocalServiceUtil.addGcmCompanyLink(this);
        } else {
            GcmCompanyLinkLocalServiceUtil.updateGcmCompanyLink(this);
        }
    }

    @Override
    public GcmCompanyLink toEscapedModel() {
        return (GcmCompanyLink) ProxyUtil.newProxyInstance(GcmCompanyLink.class.getClassLoader(),
            new Class[] { GcmCompanyLink.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        GcmCompanyLinkClp clone = new GcmCompanyLinkClp();

        clone.setCheckRecord(getCheckRecord());
        clone.setUserId(getUserId());
        clone.setCompanyNo(getCompanyNo());
        clone.setCompanyId(getCompanyId());
        clone.setGcmCompanyLinkSid(getGcmCompanyLinkSid());
        clone.setSessionId(getSessionId());
        clone.setCompanyName(getCompanyName());
        clone.setLinkId(getLinkId());
        clone.setCompanyMasterSid(getCompanyMasterSid());

        return clone;
    }

    @Override
    public int compareTo(GcmCompanyLink gcmCompanyLink) {
        int primaryKey = gcmCompanyLink.getPrimaryKey();

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

        if (!(obj instanceof GcmCompanyLinkClp)) {
            return false;
        }

        GcmCompanyLinkClp gcmCompanyLink = (GcmCompanyLinkClp) obj;

        int primaryKey = gcmCompanyLink.getPrimaryKey();

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
        StringBundler sb = new StringBundler(19);

        sb.append("{checkRecord=");
        sb.append(getCheckRecord());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", companyNo=");
        sb.append(getCompanyNo());
        sb.append(", companyId=");
        sb.append(getCompanyId());
        sb.append(", gcmCompanyLinkSid=");
        sb.append(getGcmCompanyLinkSid());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append(", companyName=");
        sb.append(getCompanyName());
        sb.append(", linkId=");
        sb.append(getLinkId());
        sb.append(", companyMasterSid=");
        sb.append(getCompanyMasterSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(31);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.GcmCompanyLink");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyNo</column-name><column-value><![CDATA[");
        sb.append(getCompanyNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyId</column-name><column-value><![CDATA[");
        sb.append(getCompanyId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>gcmCompanyLinkSid</column-name><column-value><![CDATA[");
        sb.append(getGcmCompanyLinkSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sessionId</column-name><column-value><![CDATA[");
        sb.append(getSessionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyName</column-name><column-value><![CDATA[");
        sb.append(getCompanyName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>linkId</column-name><column-value><![CDATA[");
        sb.append(getLinkId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyMasterSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
