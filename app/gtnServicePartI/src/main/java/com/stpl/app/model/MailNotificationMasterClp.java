package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.MailNotificationMasterLocalServiceUtil;

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


public class MailNotificationMasterClp extends BaseModelImpl<MailNotificationMaster>
    implements MailNotificationMaster {
    private String _subject;
    private Date _createdDate;
    private int _createdBy;
    private String _toMailIds;
    private int _notificationCategoryId;
    private String _notificationModule;
    private String _body;
    private String _fromMailId;
    private String _ccMailIds;
    private int _versionNo;
    private int _modifiedBy;
    private Date _modifiedDate;
    private int _mailNotificationSid;
    private BaseModel<?> _mailNotificationMasterRemoteModel;

    public MailNotificationMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return MailNotificationMaster.class;
    }

    @Override
    public String getModelClassName() {
        return MailNotificationMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _mailNotificationSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setMailNotificationSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _mailNotificationSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("subject", getSubject());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("toMailIds", getToMailIds());
        attributes.put("notificationCategoryId", getNotificationCategoryId());
        attributes.put("notificationModule", getNotificationModule());
        attributes.put("body", getBody());
        attributes.put("fromMailId", getFromMailId());
        attributes.put("ccMailIds", getCcMailIds());
        attributes.put("versionNo", getVersionNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("mailNotificationSid", getMailNotificationSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String subject = (String) attributes.get("subject");

        if (subject != null) {
            setSubject(subject);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String toMailIds = (String) attributes.get("toMailIds");

        if (toMailIds != null) {
            setToMailIds(toMailIds);
        }

        Integer notificationCategoryId = (Integer) attributes.get(
                "notificationCategoryId");

        if (notificationCategoryId != null) {
            setNotificationCategoryId(notificationCategoryId);
        }

        String notificationModule = (String) attributes.get(
                "notificationModule");

        if (notificationModule != null) {
            setNotificationModule(notificationModule);
        }

        String body = (String) attributes.get("body");

        if (body != null) {
            setBody(body);
        }

        String fromMailId = (String) attributes.get("fromMailId");

        if (fromMailId != null) {
            setFromMailId(fromMailId);
        }

        String ccMailIds = (String) attributes.get("ccMailIds");

        if (ccMailIds != null) {
            setCcMailIds(ccMailIds);
        }

        Integer versionNo = (Integer) attributes.get("versionNo");

        if (versionNo != null) {
            setVersionNo(versionNo);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer mailNotificationSid = (Integer) attributes.get(
                "mailNotificationSid");

        if (mailNotificationSid != null) {
            setMailNotificationSid(mailNotificationSid);
        }
    }

    @Override
    public String getSubject() {
        return _subject;
    }

    @Override
    public void setSubject(String subject) {
        _subject = subject;

        if (_mailNotificationMasterRemoteModel != null) {
            try {
                Class<?> clazz = _mailNotificationMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSubject", String.class);

                method.invoke(_mailNotificationMasterRemoteModel, subject);
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

        if (_mailNotificationMasterRemoteModel != null) {
            try {
                Class<?> clazz = _mailNotificationMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_mailNotificationMasterRemoteModel, createdDate);
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

        if (_mailNotificationMasterRemoteModel != null) {
            try {
                Class<?> clazz = _mailNotificationMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_mailNotificationMasterRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getToMailIds() {
        return _toMailIds;
    }

    @Override
    public void setToMailIds(String toMailIds) {
        _toMailIds = toMailIds;

        if (_mailNotificationMasterRemoteModel != null) {
            try {
                Class<?> clazz = _mailNotificationMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setToMailIds", String.class);

                method.invoke(_mailNotificationMasterRemoteModel, toMailIds);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getNotificationCategoryId() {
        return _notificationCategoryId;
    }

    @Override
    public void setNotificationCategoryId(int notificationCategoryId) {
        _notificationCategoryId = notificationCategoryId;

        if (_mailNotificationMasterRemoteModel != null) {
            try {
                Class<?> clazz = _mailNotificationMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNotificationCategoryId",
                        int.class);

                method.invoke(_mailNotificationMasterRemoteModel,
                    notificationCategoryId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNotificationModule() {
        return _notificationModule;
    }

    @Override
    public void setNotificationModule(String notificationModule) {
        _notificationModule = notificationModule;

        if (_mailNotificationMasterRemoteModel != null) {
            try {
                Class<?> clazz = _mailNotificationMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNotificationModule",
                        String.class);

                method.invoke(_mailNotificationMasterRemoteModel,
                    notificationModule);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBody() {
        return _body;
    }

    @Override
    public void setBody(String body) {
        _body = body;

        if (_mailNotificationMasterRemoteModel != null) {
            try {
                Class<?> clazz = _mailNotificationMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBody", String.class);

                method.invoke(_mailNotificationMasterRemoteModel, body);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFromMailId() {
        return _fromMailId;
    }

    @Override
    public void setFromMailId(String fromMailId) {
        _fromMailId = fromMailId;

        if (_mailNotificationMasterRemoteModel != null) {
            try {
                Class<?> clazz = _mailNotificationMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setFromMailId", String.class);

                method.invoke(_mailNotificationMasterRemoteModel, fromMailId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCcMailIds() {
        return _ccMailIds;
    }

    @Override
    public void setCcMailIds(String ccMailIds) {
        _ccMailIds = ccMailIds;

        if (_mailNotificationMasterRemoteModel != null) {
            try {
                Class<?> clazz = _mailNotificationMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCcMailIds", String.class);

                method.invoke(_mailNotificationMasterRemoteModel, ccMailIds);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getVersionNo() {
        return _versionNo;
    }

    @Override
    public void setVersionNo(int versionNo) {
        _versionNo = versionNo;

        if (_mailNotificationMasterRemoteModel != null) {
            try {
                Class<?> clazz = _mailNotificationMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setVersionNo", int.class);

                method.invoke(_mailNotificationMasterRemoteModel, versionNo);
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

        if (_mailNotificationMasterRemoteModel != null) {
            try {
                Class<?> clazz = _mailNotificationMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_mailNotificationMasterRemoteModel, modifiedBy);
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

        if (_mailNotificationMasterRemoteModel != null) {
            try {
                Class<?> clazz = _mailNotificationMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_mailNotificationMasterRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getMailNotificationSid() {
        return _mailNotificationSid;
    }

    @Override
    public void setMailNotificationSid(int mailNotificationSid) {
        _mailNotificationSid = mailNotificationSid;

        if (_mailNotificationMasterRemoteModel != null) {
            try {
                Class<?> clazz = _mailNotificationMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setMailNotificationSid",
                        int.class);

                method.invoke(_mailNotificationMasterRemoteModel,
                    mailNotificationSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getMailNotificationMasterRemoteModel() {
        return _mailNotificationMasterRemoteModel;
    }

    public void setMailNotificationMasterRemoteModel(
        BaseModel<?> mailNotificationMasterRemoteModel) {
        _mailNotificationMasterRemoteModel = mailNotificationMasterRemoteModel;
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

        Class<?> remoteModelClass = _mailNotificationMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_mailNotificationMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            MailNotificationMasterLocalServiceUtil.addMailNotificationMaster(this);
        } else {
            MailNotificationMasterLocalServiceUtil.updateMailNotificationMaster(this);
        }
    }

    @Override
    public MailNotificationMaster toEscapedModel() {
        return (MailNotificationMaster) ProxyUtil.newProxyInstance(MailNotificationMaster.class.getClassLoader(),
            new Class[] { MailNotificationMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        MailNotificationMasterClp clone = new MailNotificationMasterClp();

        clone.setSubject(getSubject());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setToMailIds(getToMailIds());
        clone.setNotificationCategoryId(getNotificationCategoryId());
        clone.setNotificationModule(getNotificationModule());
        clone.setBody(getBody());
        clone.setFromMailId(getFromMailId());
        clone.setCcMailIds(getCcMailIds());
        clone.setVersionNo(getVersionNo());
        clone.setModifiedBy(getModifiedBy());
        clone.setModifiedDate(getModifiedDate());
        clone.setMailNotificationSid(getMailNotificationSid());

        return clone;
    }

    @Override
    public int compareTo(MailNotificationMaster mailNotificationMaster) {
        int primaryKey = mailNotificationMaster.getPrimaryKey();

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

        if (!(obj instanceof MailNotificationMasterClp)) {
            return false;
        }

        MailNotificationMasterClp mailNotificationMaster = (MailNotificationMasterClp) obj;

        int primaryKey = mailNotificationMaster.getPrimaryKey();

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
        StringBundler sb = new StringBundler(27);

        sb.append("{subject=");
        sb.append(getSubject());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", toMailIds=");
        sb.append(getToMailIds());
        sb.append(", notificationCategoryId=");
        sb.append(getNotificationCategoryId());
        sb.append(", notificationModule=");
        sb.append(getNotificationModule());
        sb.append(", body=");
        sb.append(getBody());
        sb.append(", fromMailId=");
        sb.append(getFromMailId());
        sb.append(", ccMailIds=");
        sb.append(getCcMailIds());
        sb.append(", versionNo=");
        sb.append(getVersionNo());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", mailNotificationSid=");
        sb.append(getMailNotificationSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(43);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.MailNotificationMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>subject</column-name><column-value><![CDATA[");
        sb.append(getSubject());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>toMailIds</column-name><column-value><![CDATA[");
        sb.append(getToMailIds());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>notificationCategoryId</column-name><column-value><![CDATA[");
        sb.append(getNotificationCategoryId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>notificationModule</column-name><column-value><![CDATA[");
        sb.append(getNotificationModule());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>body</column-name><column-value><![CDATA[");
        sb.append(getBody());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fromMailId</column-name><column-value><![CDATA[");
        sb.append(getFromMailId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ccMailIds</column-name><column-value><![CDATA[");
        sb.append(getCcMailIds());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>versionNo</column-name><column-value><![CDATA[");
        sb.append(getVersionNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>mailNotificationSid</column-name><column-value><![CDATA[");
        sb.append(getMailNotificationSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
