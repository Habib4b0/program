package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.WfMailConfigLocalServiceUtil;

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


public class WfMailConfigClp extends BaseModelImpl<WfMailConfig>
    implements WfMailConfig {
    private String _smtpFlag;
    private int _createdBy;
    private String _emailAddress;
    private String _password;
    private int _modifiedBy;
    private int _wfMailConfigSid;
    private String _hostName;
    private Date _createdDate;
    private String _portNumber;
    private Date _modifiedDate;
    private String _inboundStatus;
    private BaseModel<?> _wfMailConfigRemoteModel;

    public WfMailConfigClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return WfMailConfig.class;
    }

    @Override
    public String getModelClassName() {
        return WfMailConfig.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _wfMailConfigSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setWfMailConfigSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _wfMailConfigSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("smtpFlag", getSmtpFlag());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("emailAddress", getEmailAddress());
        attributes.put("password", getPassword());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("wfMailConfigSid", getWfMailConfigSid());
        attributes.put("hostName", getHostName());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("portNumber", getPortNumber());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String smtpFlag = (String) attributes.get("smtpFlag");

        if (smtpFlag != null) {
            setSmtpFlag(smtpFlag);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String emailAddress = (String) attributes.get("emailAddress");

        if (emailAddress != null) {
            setEmailAddress(emailAddress);
        }

        String password = (String) attributes.get("password");

        if (password != null) {
            setPassword(password);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Integer wfMailConfigSid = (Integer) attributes.get("wfMailConfigSid");

        if (wfMailConfigSid != null) {
            setWfMailConfigSid(wfMailConfigSid);
        }

        String hostName = (String) attributes.get("hostName");

        if (hostName != null) {
            setHostName(hostName);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String portNumber = (String) attributes.get("portNumber");

        if (portNumber != null) {
            setPortNumber(portNumber);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }
    }

    @Override
    public String getSmtpFlag() {
        return _smtpFlag;
    }

    @Override
    public void setSmtpFlag(String smtpFlag) {
        _smtpFlag = smtpFlag;

        if (_wfMailConfigRemoteModel != null) {
            try {
                Class<?> clazz = _wfMailConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setSmtpFlag", String.class);

                method.invoke(_wfMailConfigRemoteModel, smtpFlag);
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

        if (_wfMailConfigRemoteModel != null) {
            try {
                Class<?> clazz = _wfMailConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_wfMailConfigRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getEmailAddress() {
        return _emailAddress;
    }

    @Override
    public void setEmailAddress(String emailAddress) {
        _emailAddress = emailAddress;

        if (_wfMailConfigRemoteModel != null) {
            try {
                Class<?> clazz = _wfMailConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setEmailAddress", String.class);

                method.invoke(_wfMailConfigRemoteModel, emailAddress);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPassword() {
        return _password;
    }

    @Override
    public void setPassword(String password) {
        _password = password;

        if (_wfMailConfigRemoteModel != null) {
            try {
                Class<?> clazz = _wfMailConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setPassword", String.class);

                method.invoke(_wfMailConfigRemoteModel, password);
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

        if (_wfMailConfigRemoteModel != null) {
            try {
                Class<?> clazz = _wfMailConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_wfMailConfigRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getWfMailConfigSid() {
        return _wfMailConfigSid;
    }

    @Override
    public void setWfMailConfigSid(int wfMailConfigSid) {
        _wfMailConfigSid = wfMailConfigSid;

        if (_wfMailConfigRemoteModel != null) {
            try {
                Class<?> clazz = _wfMailConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setWfMailConfigSid", int.class);

                method.invoke(_wfMailConfigRemoteModel, wfMailConfigSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getHostName() {
        return _hostName;
    }

    @Override
    public void setHostName(String hostName) {
        _hostName = hostName;

        if (_wfMailConfigRemoteModel != null) {
            try {
                Class<?> clazz = _wfMailConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setHostName", String.class);

                method.invoke(_wfMailConfigRemoteModel, hostName);
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

        if (_wfMailConfigRemoteModel != null) {
            try {
                Class<?> clazz = _wfMailConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_wfMailConfigRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPortNumber() {
        return _portNumber;
    }

    @Override
    public void setPortNumber(String portNumber) {
        _portNumber = portNumber;

        if (_wfMailConfigRemoteModel != null) {
            try {
                Class<?> clazz = _wfMailConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setPortNumber", String.class);

                method.invoke(_wfMailConfigRemoteModel, portNumber);
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

        if (_wfMailConfigRemoteModel != null) {
            try {
                Class<?> clazz = _wfMailConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_wfMailConfigRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getInboundStatus() {
        return _inboundStatus;
    }

    @Override
    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;

        if (_wfMailConfigRemoteModel != null) {
            try {
                Class<?> clazz = _wfMailConfigRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_wfMailConfigRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getWfMailConfigRemoteModel() {
        return _wfMailConfigRemoteModel;
    }

    public void setWfMailConfigRemoteModel(BaseModel<?> wfMailConfigRemoteModel) {
        _wfMailConfigRemoteModel = wfMailConfigRemoteModel;
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

        Class<?> remoteModelClass = _wfMailConfigRemoteModel.getClass();

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

        Object returnValue = method.invoke(_wfMailConfigRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            WfMailConfigLocalServiceUtil.addWfMailConfig(this);
        } else {
            WfMailConfigLocalServiceUtil.updateWfMailConfig(this);
        }
    }

    @Override
    public WfMailConfig toEscapedModel() {
        return (WfMailConfig) ProxyUtil.newProxyInstance(WfMailConfig.class.getClassLoader(),
            new Class[] { WfMailConfig.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        WfMailConfigClp clone = new WfMailConfigClp();

        clone.setSmtpFlag(getSmtpFlag());
        clone.setCreatedBy(getCreatedBy());
        clone.setEmailAddress(getEmailAddress());
        clone.setPassword(getPassword());
        clone.setModifiedBy(getModifiedBy());
        clone.setWfMailConfigSid(getWfMailConfigSid());
        clone.setHostName(getHostName());
        clone.setCreatedDate(getCreatedDate());
        clone.setPortNumber(getPortNumber());
        clone.setModifiedDate(getModifiedDate());
        clone.setInboundStatus(getInboundStatus());

        return clone;
    }

    @Override
    public int compareTo(WfMailConfig wfMailConfig) {
        int primaryKey = wfMailConfig.getPrimaryKey();

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

        if (!(obj instanceof WfMailConfigClp)) {
            return false;
        }

        WfMailConfigClp wfMailConfig = (WfMailConfigClp) obj;

        int primaryKey = wfMailConfig.getPrimaryKey();

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
        StringBundler sb = new StringBundler(23);

        sb.append("{smtpFlag=");
        sb.append(getSmtpFlag());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", emailAddress=");
        sb.append(getEmailAddress());
        sb.append(", password=");
        sb.append(getPassword());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", wfMailConfigSid=");
        sb.append(getWfMailConfigSid());
        sb.append(", hostName=");
        sb.append(getHostName());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", portNumber=");
        sb.append(getPortNumber());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(37);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.WfMailConfig");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>smtpFlag</column-name><column-value><![CDATA[");
        sb.append(getSmtpFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>emailAddress</column-name><column-value><![CDATA[");
        sb.append(getEmailAddress());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>password</column-name><column-value><![CDATA[");
        sb.append(getPassword());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>wfMailConfigSid</column-name><column-value><![CDATA[");
        sb.append(getWfMailConfigSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>hostName</column-name><column-value><![CDATA[");
        sb.append(getHostName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>portNumber</column-name><column-value><![CDATA[");
        sb.append(getPortNumber());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
