package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.DeductionGroupLocalServiceUtil;

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


public class DeductionGroupClp extends BaseModelImpl<DeductionGroup>
    implements DeductionGroup {
    private String _deductionFilter;
    private Date _createdDate;
    private int _createdBy;
    private int _deductionGroupSid;
    private String _deductionGroupName;
    private int _versionNo;
    private String _deductionGroupDescription;
    private int _modifiedBy;
    private String _deductionGroupNo;
    private Date _modifiedDate;
    private BaseModel<?> _deductionGroupRemoteModel;

    public DeductionGroupClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return DeductionGroup.class;
    }

    @Override
    public String getModelClassName() {
        return DeductionGroup.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _deductionGroupSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setDeductionGroupSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _deductionGroupSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("deductionFilter", getDeductionFilter());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("deductionGroupSid", getDeductionGroupSid());
        attributes.put("deductionGroupName", getDeductionGroupName());
        attributes.put("versionNo", getVersionNo());
        attributes.put("deductionGroupDescription",
            getDeductionGroupDescription());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("deductionGroupNo", getDeductionGroupNo());
        attributes.put("modifiedDate", getModifiedDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String deductionFilter = (String) attributes.get("deductionFilter");

        if (deductionFilter != null) {
            setDeductionFilter(deductionFilter);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer deductionGroupSid = (Integer) attributes.get(
                "deductionGroupSid");

        if (deductionGroupSid != null) {
            setDeductionGroupSid(deductionGroupSid);
        }

        String deductionGroupName = (String) attributes.get(
                "deductionGroupName");

        if (deductionGroupName != null) {
            setDeductionGroupName(deductionGroupName);
        }

        Integer versionNo = (Integer) attributes.get("versionNo");

        if (versionNo != null) {
            setVersionNo(versionNo);
        }

        String deductionGroupDescription = (String) attributes.get(
                "deductionGroupDescription");

        if (deductionGroupDescription != null) {
            setDeductionGroupDescription(deductionGroupDescription);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String deductionGroupNo = (String) attributes.get("deductionGroupNo");

        if (deductionGroupNo != null) {
            setDeductionGroupNo(deductionGroupNo);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }
    }

    @Override
    public String getDeductionFilter() {
        return _deductionFilter;
    }

    @Override
    public void setDeductionFilter(String deductionFilter) {
        _deductionFilter = deductionFilter;

        if (_deductionGroupRemoteModel != null) {
            try {
                Class<?> clazz = _deductionGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionFilter",
                        String.class);

                method.invoke(_deductionGroupRemoteModel, deductionFilter);
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

        if (_deductionGroupRemoteModel != null) {
            try {
                Class<?> clazz = _deductionGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_deductionGroupRemoteModel, createdDate);
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

        if (_deductionGroupRemoteModel != null) {
            try {
                Class<?> clazz = _deductionGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_deductionGroupRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getDeductionGroupSid() {
        return _deductionGroupSid;
    }

    @Override
    public void setDeductionGroupSid(int deductionGroupSid) {
        _deductionGroupSid = deductionGroupSid;

        if (_deductionGroupRemoteModel != null) {
            try {
                Class<?> clazz = _deductionGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionGroupSid",
                        int.class);

                method.invoke(_deductionGroupRemoteModel, deductionGroupSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionGroupName() {
        return _deductionGroupName;
    }

    @Override
    public void setDeductionGroupName(String deductionGroupName) {
        _deductionGroupName = deductionGroupName;

        if (_deductionGroupRemoteModel != null) {
            try {
                Class<?> clazz = _deductionGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionGroupName",
                        String.class);

                method.invoke(_deductionGroupRemoteModel, deductionGroupName);
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

        if (_deductionGroupRemoteModel != null) {
            try {
                Class<?> clazz = _deductionGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setVersionNo", int.class);

                method.invoke(_deductionGroupRemoteModel, versionNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionGroupDescription() {
        return _deductionGroupDescription;
    }

    @Override
    public void setDeductionGroupDescription(String deductionGroupDescription) {
        _deductionGroupDescription = deductionGroupDescription;

        if (_deductionGroupRemoteModel != null) {
            try {
                Class<?> clazz = _deductionGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionGroupDescription",
                        String.class);

                method.invoke(_deductionGroupRemoteModel,
                    deductionGroupDescription);
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

        if (_deductionGroupRemoteModel != null) {
            try {
                Class<?> clazz = _deductionGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_deductionGroupRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionGroupNo() {
        return _deductionGroupNo;
    }

    @Override
    public void setDeductionGroupNo(String deductionGroupNo) {
        _deductionGroupNo = deductionGroupNo;

        if (_deductionGroupRemoteModel != null) {
            try {
                Class<?> clazz = _deductionGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionGroupNo",
                        String.class);

                method.invoke(_deductionGroupRemoteModel, deductionGroupNo);
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

        if (_deductionGroupRemoteModel != null) {
            try {
                Class<?> clazz = _deductionGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_deductionGroupRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getDeductionGroupRemoteModel() {
        return _deductionGroupRemoteModel;
    }

    public void setDeductionGroupRemoteModel(
        BaseModel<?> deductionGroupRemoteModel) {
        _deductionGroupRemoteModel = deductionGroupRemoteModel;
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

        Class<?> remoteModelClass = _deductionGroupRemoteModel.getClass();

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

        Object returnValue = method.invoke(_deductionGroupRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            DeductionGroupLocalServiceUtil.addDeductionGroup(this);
        } else {
            DeductionGroupLocalServiceUtil.updateDeductionGroup(this);
        }
    }

    @Override
    public DeductionGroup toEscapedModel() {
        return (DeductionGroup) ProxyUtil.newProxyInstance(DeductionGroup.class.getClassLoader(),
            new Class[] { DeductionGroup.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        DeductionGroupClp clone = new DeductionGroupClp();

        clone.setDeductionFilter(getDeductionFilter());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setDeductionGroupSid(getDeductionGroupSid());
        clone.setDeductionGroupName(getDeductionGroupName());
        clone.setVersionNo(getVersionNo());
        clone.setDeductionGroupDescription(getDeductionGroupDescription());
        clone.setModifiedBy(getModifiedBy());
        clone.setDeductionGroupNo(getDeductionGroupNo());
        clone.setModifiedDate(getModifiedDate());

        return clone;
    }

    @Override
    public int compareTo(DeductionGroup deductionGroup) {
        int primaryKey = deductionGroup.getPrimaryKey();

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

        if (!(obj instanceof DeductionGroupClp)) {
            return false;
        }

        DeductionGroupClp deductionGroup = (DeductionGroupClp) obj;

        int primaryKey = deductionGroup.getPrimaryKey();

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
        StringBundler sb = new StringBundler(21);

        sb.append("{deductionFilter=");
        sb.append(getDeductionFilter());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", deductionGroupSid=");
        sb.append(getDeductionGroupSid());
        sb.append(", deductionGroupName=");
        sb.append(getDeductionGroupName());
        sb.append(", versionNo=");
        sb.append(getVersionNo());
        sb.append(", deductionGroupDescription=");
        sb.append(getDeductionGroupDescription());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", deductionGroupNo=");
        sb.append(getDeductionGroupNo());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(34);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.DeductionGroup");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>deductionFilter</column-name><column-value><![CDATA[");
        sb.append(getDeductionFilter());
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
            "<column><column-name>deductionGroupSid</column-name><column-value><![CDATA[");
        sb.append(getDeductionGroupSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionGroupName</column-name><column-value><![CDATA[");
        sb.append(getDeductionGroupName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>versionNo</column-name><column-value><![CDATA[");
        sb.append(getVersionNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionGroupDescription</column-name><column-value><![CDATA[");
        sb.append(getDeductionGroupDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionGroupNo</column-name><column-value><![CDATA[");
        sb.append(getDeductionGroupNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
