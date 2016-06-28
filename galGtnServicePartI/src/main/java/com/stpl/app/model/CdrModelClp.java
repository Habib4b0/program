package com.stpl.app.model;

import com.stpl.app.service.CdrModelLocalServiceUtil;
import com.stpl.app.service.ClpSerializer;

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


public class CdrModelClp extends BaseModelImpl<CdrModel> implements CdrModel {
    private int _createdBy;
    private int _ruleCategory;
    private int _ruleType;
    private int _modifiedBy;
    private String _internalNotes;
    private Date _createdDate;
    private String _ruleName;
    private int _cdrModelSid;
    private String _ruleNo;
    private Date _modifiedDate;
    private BaseModel<?> _cdrModelRemoteModel;

    public CdrModelClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return CdrModel.class;
    }

    @Override
    public String getModelClassName() {
        return CdrModel.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _cdrModelSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setCdrModelSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _cdrModelSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("ruleCategory", getRuleCategory());
        attributes.put("ruleType", getRuleType());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("internalNotes", getInternalNotes());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("ruleName", getRuleName());
        attributes.put("cdrModelSid", getCdrModelSid());
        attributes.put("ruleNo", getRuleNo());
        attributes.put("modifiedDate", getModifiedDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer ruleCategory = (Integer) attributes.get("ruleCategory");

        if (ruleCategory != null) {
            setRuleCategory(ruleCategory);
        }

        Integer ruleType = (Integer) attributes.get("ruleType");

        if (ruleType != null) {
            setRuleType(ruleType);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String internalNotes = (String) attributes.get("internalNotes");

        if (internalNotes != null) {
            setInternalNotes(internalNotes);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String ruleName = (String) attributes.get("ruleName");

        if (ruleName != null) {
            setRuleName(ruleName);
        }

        Integer cdrModelSid = (Integer) attributes.get("cdrModelSid");

        if (cdrModelSid != null) {
            setCdrModelSid(cdrModelSid);
        }

        String ruleNo = (String) attributes.get("ruleNo");

        if (ruleNo != null) {
            setRuleNo(ruleNo);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }
    }

    @Override
    public int getCreatedBy() {
        return _createdBy;
    }

    @Override
    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;

        if (_cdrModelRemoteModel != null) {
            try {
                Class<?> clazz = _cdrModelRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_cdrModelRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRuleCategory() {
        return _ruleCategory;
    }

    @Override
    public void setRuleCategory(int ruleCategory) {
        _ruleCategory = ruleCategory;

        if (_cdrModelRemoteModel != null) {
            try {
                Class<?> clazz = _cdrModelRemoteModel.getClass();

                Method method = clazz.getMethod("setRuleCategory", int.class);

                method.invoke(_cdrModelRemoteModel, ruleCategory);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRuleType() {
        return _ruleType;
    }

    @Override
    public void setRuleType(int ruleType) {
        _ruleType = ruleType;

        if (_cdrModelRemoteModel != null) {
            try {
                Class<?> clazz = _cdrModelRemoteModel.getClass();

                Method method = clazz.getMethod("setRuleType", int.class);

                method.invoke(_cdrModelRemoteModel, ruleType);
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

        if (_cdrModelRemoteModel != null) {
            try {
                Class<?> clazz = _cdrModelRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_cdrModelRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getInternalNotes() {
        return _internalNotes;
    }

    @Override
    public void setInternalNotes(String internalNotes) {
        _internalNotes = internalNotes;

        if (_cdrModelRemoteModel != null) {
            try {
                Class<?> clazz = _cdrModelRemoteModel.getClass();

                Method method = clazz.getMethod("setInternalNotes", String.class);

                method.invoke(_cdrModelRemoteModel, internalNotes);
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

        if (_cdrModelRemoteModel != null) {
            try {
                Class<?> clazz = _cdrModelRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_cdrModelRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRuleName() {
        return _ruleName;
    }

    @Override
    public void setRuleName(String ruleName) {
        _ruleName = ruleName;

        if (_cdrModelRemoteModel != null) {
            try {
                Class<?> clazz = _cdrModelRemoteModel.getClass();

                Method method = clazz.getMethod("setRuleName", String.class);

                method.invoke(_cdrModelRemoteModel, ruleName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCdrModelSid() {
        return _cdrModelSid;
    }

    @Override
    public void setCdrModelSid(int cdrModelSid) {
        _cdrModelSid = cdrModelSid;

        if (_cdrModelRemoteModel != null) {
            try {
                Class<?> clazz = _cdrModelRemoteModel.getClass();

                Method method = clazz.getMethod("setCdrModelSid", int.class);

                method.invoke(_cdrModelRemoteModel, cdrModelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRuleNo() {
        return _ruleNo;
    }

    @Override
    public void setRuleNo(String ruleNo) {
        _ruleNo = ruleNo;

        if (_cdrModelRemoteModel != null) {
            try {
                Class<?> clazz = _cdrModelRemoteModel.getClass();

                Method method = clazz.getMethod("setRuleNo", String.class);

                method.invoke(_cdrModelRemoteModel, ruleNo);
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

        if (_cdrModelRemoteModel != null) {
            try {
                Class<?> clazz = _cdrModelRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_cdrModelRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getCdrModelRemoteModel() {
        return _cdrModelRemoteModel;
    }

    public void setCdrModelRemoteModel(BaseModel<?> cdrModelRemoteModel) {
        _cdrModelRemoteModel = cdrModelRemoteModel;
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

        Class<?> remoteModelClass = _cdrModelRemoteModel.getClass();

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

        Object returnValue = method.invoke(_cdrModelRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            CdrModelLocalServiceUtil.addCdrModel(this);
        } else {
            CdrModelLocalServiceUtil.updateCdrModel(this);
        }
    }

    @Override
    public CdrModel toEscapedModel() {
        return (CdrModel) ProxyUtil.newProxyInstance(CdrModel.class.getClassLoader(),
            new Class[] { CdrModel.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CdrModelClp clone = new CdrModelClp();

        clone.setCreatedBy(getCreatedBy());
        clone.setRuleCategory(getRuleCategory());
        clone.setRuleType(getRuleType());
        clone.setModifiedBy(getModifiedBy());
        clone.setInternalNotes(getInternalNotes());
        clone.setCreatedDate(getCreatedDate());
        clone.setRuleName(getRuleName());
        clone.setCdrModelSid(getCdrModelSid());
        clone.setRuleNo(getRuleNo());
        clone.setModifiedDate(getModifiedDate());

        return clone;
    }

    @Override
    public int compareTo(CdrModel cdrModel) {
        int primaryKey = cdrModel.getPrimaryKey();

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

        if (!(obj instanceof CdrModelClp)) {
            return false;
        }

        CdrModelClp cdrModel = (CdrModelClp) obj;

        int primaryKey = cdrModel.getPrimaryKey();

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

        sb.append("{createdBy=");
        sb.append(getCreatedBy());
        sb.append(", ruleCategory=");
        sb.append(getRuleCategory());
        sb.append(", ruleType=");
        sb.append(getRuleType());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", internalNotes=");
        sb.append(getInternalNotes());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", ruleName=");
        sb.append(getRuleName());
        sb.append(", cdrModelSid=");
        sb.append(getCdrModelSid());
        sb.append(", ruleNo=");
        sb.append(getRuleNo());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(34);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.CdrModel");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ruleCategory</column-name><column-value><![CDATA[");
        sb.append(getRuleCategory());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ruleType</column-name><column-value><![CDATA[");
        sb.append(getRuleType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>internalNotes</column-name><column-value><![CDATA[");
        sb.append(getInternalNotes());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ruleName</column-name><column-value><![CDATA[");
        sb.append(getRuleName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cdrModelSid</column-name><column-value><![CDATA[");
        sb.append(getCdrModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ruleNo</column-name><column-value><![CDATA[");
        sb.append(getRuleNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
