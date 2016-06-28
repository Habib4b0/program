package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.DeductionCalendarMasterLocalServiceUtil;

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


public class DeductionCalendarMasterClp extends BaseModelImpl<DeductionCalendarMaster>
    implements DeductionCalendarMaster {
    private int _createdBy;
    private int _deductionCalendarMasterSid;
    private String _deductionCalendarNo;
    private int _modifiedBy;
    private Date _createdDate;
    private int _category;
    private String _additionalNotes;
    private int _userId;
    private String _deductionCalendarName;
    private String _deductionCalendarDesc;
    private Date _modifiedDate;
    private String _inboundStatus;
    private BaseModel<?> _deductionCalendarMasterRemoteModel;

    public DeductionCalendarMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return DeductionCalendarMaster.class;
    }

    @Override
    public String getModelClassName() {
        return DeductionCalendarMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _deductionCalendarMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setDeductionCalendarMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _deductionCalendarMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("deductionCalendarMasterSid",
            getDeductionCalendarMasterSid());
        attributes.put("deductionCalendarNo", getDeductionCalendarNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("category", getCategory());
        attributes.put("additionalNotes", getAdditionalNotes());
        attributes.put("userId", getUserId());
        attributes.put("deductionCalendarName", getDeductionCalendarName());
        attributes.put("deductionCalendarDesc", getDeductionCalendarDesc());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer deductionCalendarMasterSid = (Integer) attributes.get(
                "deductionCalendarMasterSid");

        if (deductionCalendarMasterSid != null) {
            setDeductionCalendarMasterSid(deductionCalendarMasterSid);
        }

        String deductionCalendarNo = (String) attributes.get(
                "deductionCalendarNo");

        if (deductionCalendarNo != null) {
            setDeductionCalendarNo(deductionCalendarNo);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer category = (Integer) attributes.get("category");

        if (category != null) {
            setCategory(category);
        }

        String additionalNotes = (String) attributes.get("additionalNotes");

        if (additionalNotes != null) {
            setAdditionalNotes(additionalNotes);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        String deductionCalendarName = (String) attributes.get(
                "deductionCalendarName");

        if (deductionCalendarName != null) {
            setDeductionCalendarName(deductionCalendarName);
        }

        String deductionCalendarDesc = (String) attributes.get(
                "deductionCalendarDesc");

        if (deductionCalendarDesc != null) {
            setDeductionCalendarDesc(deductionCalendarDesc);
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
    public int getCreatedBy() {
        return _createdBy;
    }

    @Override
    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;

        if (_deductionCalendarMasterRemoteModel != null) {
            try {
                Class<?> clazz = _deductionCalendarMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_deductionCalendarMasterRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getDeductionCalendarMasterSid() {
        return _deductionCalendarMasterSid;
    }

    @Override
    public void setDeductionCalendarMasterSid(int deductionCalendarMasterSid) {
        _deductionCalendarMasterSid = deductionCalendarMasterSid;

        if (_deductionCalendarMasterRemoteModel != null) {
            try {
                Class<?> clazz = _deductionCalendarMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionCalendarMasterSid",
                        int.class);

                method.invoke(_deductionCalendarMasterRemoteModel,
                    deductionCalendarMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionCalendarNo() {
        return _deductionCalendarNo;
    }

    @Override
    public void setDeductionCalendarNo(String deductionCalendarNo) {
        _deductionCalendarNo = deductionCalendarNo;

        if (_deductionCalendarMasterRemoteModel != null) {
            try {
                Class<?> clazz = _deductionCalendarMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionCalendarNo",
                        String.class);

                method.invoke(_deductionCalendarMasterRemoteModel,
                    deductionCalendarNo);
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

        if (_deductionCalendarMasterRemoteModel != null) {
            try {
                Class<?> clazz = _deductionCalendarMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_deductionCalendarMasterRemoteModel, modifiedBy);
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

        if (_deductionCalendarMasterRemoteModel != null) {
            try {
                Class<?> clazz = _deductionCalendarMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_deductionCalendarMasterRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCategory() {
        return _category;
    }

    @Override
    public void setCategory(int category) {
        _category = category;

        if (_deductionCalendarMasterRemoteModel != null) {
            try {
                Class<?> clazz = _deductionCalendarMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCategory", int.class);

                method.invoke(_deductionCalendarMasterRemoteModel, category);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAdditionalNotes() {
        return _additionalNotes;
    }

    @Override
    public void setAdditionalNotes(String additionalNotes) {
        _additionalNotes = additionalNotes;

        if (_deductionCalendarMasterRemoteModel != null) {
            try {
                Class<?> clazz = _deductionCalendarMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAdditionalNotes",
                        String.class);

                method.invoke(_deductionCalendarMasterRemoteModel,
                    additionalNotes);
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

        if (_deductionCalendarMasterRemoteModel != null) {
            try {
                Class<?> clazz = _deductionCalendarMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", int.class);

                method.invoke(_deductionCalendarMasterRemoteModel, userId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionCalendarName() {
        return _deductionCalendarName;
    }

    @Override
    public void setDeductionCalendarName(String deductionCalendarName) {
        _deductionCalendarName = deductionCalendarName;

        if (_deductionCalendarMasterRemoteModel != null) {
            try {
                Class<?> clazz = _deductionCalendarMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionCalendarName",
                        String.class);

                method.invoke(_deductionCalendarMasterRemoteModel,
                    deductionCalendarName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionCalendarDesc() {
        return _deductionCalendarDesc;
    }

    @Override
    public void setDeductionCalendarDesc(String deductionCalendarDesc) {
        _deductionCalendarDesc = deductionCalendarDesc;

        if (_deductionCalendarMasterRemoteModel != null) {
            try {
                Class<?> clazz = _deductionCalendarMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionCalendarDesc",
                        String.class);

                method.invoke(_deductionCalendarMasterRemoteModel,
                    deductionCalendarDesc);
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

        if (_deductionCalendarMasterRemoteModel != null) {
            try {
                Class<?> clazz = _deductionCalendarMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_deductionCalendarMasterRemoteModel, modifiedDate);
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

        if (_deductionCalendarMasterRemoteModel != null) {
            try {
                Class<?> clazz = _deductionCalendarMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_deductionCalendarMasterRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getDeductionCalendarMasterRemoteModel() {
        return _deductionCalendarMasterRemoteModel;
    }

    public void setDeductionCalendarMasterRemoteModel(
        BaseModel<?> deductionCalendarMasterRemoteModel) {
        _deductionCalendarMasterRemoteModel = deductionCalendarMasterRemoteModel;
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

        Class<?> remoteModelClass = _deductionCalendarMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_deductionCalendarMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            DeductionCalendarMasterLocalServiceUtil.addDeductionCalendarMaster(this);
        } else {
            DeductionCalendarMasterLocalServiceUtil.updateDeductionCalendarMaster(this);
        }
    }

    @Override
    public DeductionCalendarMaster toEscapedModel() {
        return (DeductionCalendarMaster) ProxyUtil.newProxyInstance(DeductionCalendarMaster.class.getClassLoader(),
            new Class[] { DeductionCalendarMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        DeductionCalendarMasterClp clone = new DeductionCalendarMasterClp();

        clone.setCreatedBy(getCreatedBy());
        clone.setDeductionCalendarMasterSid(getDeductionCalendarMasterSid());
        clone.setDeductionCalendarNo(getDeductionCalendarNo());
        clone.setModifiedBy(getModifiedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setCategory(getCategory());
        clone.setAdditionalNotes(getAdditionalNotes());
        clone.setUserId(getUserId());
        clone.setDeductionCalendarName(getDeductionCalendarName());
        clone.setDeductionCalendarDesc(getDeductionCalendarDesc());
        clone.setModifiedDate(getModifiedDate());
        clone.setInboundStatus(getInboundStatus());

        return clone;
    }

    @Override
    public int compareTo(DeductionCalendarMaster deductionCalendarMaster) {
        int primaryKey = deductionCalendarMaster.getPrimaryKey();

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

        if (!(obj instanceof DeductionCalendarMasterClp)) {
            return false;
        }

        DeductionCalendarMasterClp deductionCalendarMaster = (DeductionCalendarMasterClp) obj;

        int primaryKey = deductionCalendarMaster.getPrimaryKey();

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
        StringBundler sb = new StringBundler(25);

        sb.append("{createdBy=");
        sb.append(getCreatedBy());
        sb.append(", deductionCalendarMasterSid=");
        sb.append(getDeductionCalendarMasterSid());
        sb.append(", deductionCalendarNo=");
        sb.append(getDeductionCalendarNo());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", category=");
        sb.append(getCategory());
        sb.append(", additionalNotes=");
        sb.append(getAdditionalNotes());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", deductionCalendarName=");
        sb.append(getDeductionCalendarName());
        sb.append(", deductionCalendarDesc=");
        sb.append(getDeductionCalendarDesc());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(40);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.DeductionCalendarMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionCalendarMasterSid</column-name><column-value><![CDATA[");
        sb.append(getDeductionCalendarMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionCalendarNo</column-name><column-value><![CDATA[");
        sb.append(getDeductionCalendarNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>category</column-name><column-value><![CDATA[");
        sb.append(getCategory());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>additionalNotes</column-name><column-value><![CDATA[");
        sb.append(getAdditionalNotes());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionCalendarName</column-name><column-value><![CDATA[");
        sb.append(getDeductionCalendarName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionCalendarDesc</column-name><column-value><![CDATA[");
        sb.append(getDeductionCalendarDesc());
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
