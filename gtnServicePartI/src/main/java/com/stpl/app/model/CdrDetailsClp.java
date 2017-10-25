package com.stpl.app.model;

import com.stpl.app.service.CdrDetailsLocalServiceUtil;
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


public class CdrDetailsClp extends BaseModelImpl<CdrDetails>
    implements CdrDetails {
    private int _createdBy;
    private int _modifiedBy;
    private Date _createdDate;
    private String _lineType;
    private String _keyword;
    private String _itemGroupMsAssociation;
    private double _value;
    private Date _modifiedDate;
    private String _logicalOperator;
    private String _operator;
    private int _cdrDetailsSid;
    private int _cdrModelSid;
    private String _comparison;
    private BaseModel<?> _cdrDetailsRemoteModel;

    public CdrDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return CdrDetails.class;
    }

    @Override
    public String getModelClassName() {
        return CdrDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _cdrDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setCdrDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _cdrDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("lineType", getLineType());
        attributes.put("keyword", getKeyword());
        attributes.put("itemGroupMsAssociation", getItemGroupMsAssociation());
        attributes.put("value", getValue());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("logicalOperator", getLogicalOperator());
        attributes.put("operator", getOperator());
        attributes.put("cdrDetailsSid", getCdrDetailsSid());
        attributes.put("cdrModelSid", getCdrModelSid());
        attributes.put("comparison", getComparison());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String lineType = (String) attributes.get("lineType");

        if (lineType != null) {
            setLineType(lineType);
        }

        String keyword = (String) attributes.get("keyword");

        if (keyword != null) {
            setKeyword(keyword);
        }

        String itemGroupMsAssociation = (String) attributes.get(
                "itemGroupMsAssociation");

        if (itemGroupMsAssociation != null) {
            setItemGroupMsAssociation(itemGroupMsAssociation);
        }

        Double value = (Double) attributes.get("value");

        if (value != null) {
            setValue(value);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String logicalOperator = (String) attributes.get("logicalOperator");

        if (logicalOperator != null) {
            setLogicalOperator(logicalOperator);
        }

        String operator = (String) attributes.get("operator");

        if (operator != null) {
            setOperator(operator);
        }

        Integer cdrDetailsSid = (Integer) attributes.get("cdrDetailsSid");

        if (cdrDetailsSid != null) {
            setCdrDetailsSid(cdrDetailsSid);
        }

        Integer cdrModelSid = (Integer) attributes.get("cdrModelSid");

        if (cdrModelSid != null) {
            setCdrModelSid(cdrModelSid);
        }

        String comparison = (String) attributes.get("comparison");

        if (comparison != null) {
            setComparison(comparison);
        }
    }

    @Override
    public int getCreatedBy() {
        return _createdBy;
    }

    @Override
    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;

        if (_cdrDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cdrDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_cdrDetailsRemoteModel, createdBy);
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

        if (_cdrDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cdrDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_cdrDetailsRemoteModel, modifiedBy);
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

        if (_cdrDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cdrDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_cdrDetailsRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLineType() {
        return _lineType;
    }

    @Override
    public void setLineType(String lineType) {
        _lineType = lineType;

        if (_cdrDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cdrDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setLineType", String.class);

                method.invoke(_cdrDetailsRemoteModel, lineType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getKeyword() {
        return _keyword;
    }

    @Override
    public void setKeyword(String keyword) {
        _keyword = keyword;

        if (_cdrDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cdrDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setKeyword", String.class);

                method.invoke(_cdrDetailsRemoteModel, keyword);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemGroupMsAssociation() {
        return _itemGroupMsAssociation;
    }

    @Override
    public void setItemGroupMsAssociation(String itemGroupMsAssociation) {
        _itemGroupMsAssociation = itemGroupMsAssociation;

        if (_cdrDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cdrDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemGroupMsAssociation",
                        String.class);

                method.invoke(_cdrDetailsRemoteModel, itemGroupMsAssociation);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getValue() {
        return _value;
    }

    @Override
    public void setValue(double value) {
        _value = value;

        if (_cdrDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cdrDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setValue", double.class);

                method.invoke(_cdrDetailsRemoteModel, value);
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

        if (_cdrDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cdrDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_cdrDetailsRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLogicalOperator() {
        return _logicalOperator;
    }

    @Override
    public void setLogicalOperator(String logicalOperator) {
        _logicalOperator = logicalOperator;

        if (_cdrDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cdrDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setLogicalOperator",
                        String.class);

                method.invoke(_cdrDetailsRemoteModel, logicalOperator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getOperator() {
        return _operator;
    }

    @Override
    public void setOperator(String operator) {
        _operator = operator;

        if (_cdrDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cdrDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setOperator", String.class);

                method.invoke(_cdrDetailsRemoteModel, operator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCdrDetailsSid() {
        return _cdrDetailsSid;
    }

    @Override
    public void setCdrDetailsSid(int cdrDetailsSid) {
        _cdrDetailsSid = cdrDetailsSid;

        if (_cdrDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cdrDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCdrDetailsSid", int.class);

                method.invoke(_cdrDetailsRemoteModel, cdrDetailsSid);
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

        if (_cdrDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cdrDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCdrModelSid", int.class);

                method.invoke(_cdrDetailsRemoteModel, cdrModelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getComparison() {
        return _comparison;
    }

    @Override
    public void setComparison(String comparison) {
        _comparison = comparison;

        if (_cdrDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cdrDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setComparison", String.class);

                method.invoke(_cdrDetailsRemoteModel, comparison);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getCdrDetailsRemoteModel() {
        return _cdrDetailsRemoteModel;
    }

    public void setCdrDetailsRemoteModel(BaseModel<?> cdrDetailsRemoteModel) {
        _cdrDetailsRemoteModel = cdrDetailsRemoteModel;
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

        Class<?> remoteModelClass = _cdrDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_cdrDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            CdrDetailsLocalServiceUtil.addCdrDetails(this);
        } else {
            CdrDetailsLocalServiceUtil.updateCdrDetails(this);
        }
    }

    @Override
    public CdrDetails toEscapedModel() {
        return (CdrDetails) ProxyUtil.newProxyInstance(CdrDetails.class.getClassLoader(),
            new Class[] { CdrDetails.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CdrDetailsClp clone = new CdrDetailsClp();

        clone.setCreatedBy(getCreatedBy());
        clone.setModifiedBy(getModifiedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setLineType(getLineType());
        clone.setKeyword(getKeyword());
        clone.setItemGroupMsAssociation(getItemGroupMsAssociation());
        clone.setValue(getValue());
        clone.setModifiedDate(getModifiedDate());
        clone.setLogicalOperator(getLogicalOperator());
        clone.setOperator(getOperator());
        clone.setCdrDetailsSid(getCdrDetailsSid());
        clone.setCdrModelSid(getCdrModelSid());
        clone.setComparison(getComparison());

        return clone;
    }

    @Override
    public int compareTo(CdrDetails cdrDetails) {
        int primaryKey = cdrDetails.getPrimaryKey();

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

        if (!(obj instanceof CdrDetailsClp)) {
            return false;
        }

        CdrDetailsClp cdrDetails = (CdrDetailsClp) obj;

        int primaryKey = cdrDetails.getPrimaryKey();

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

        sb.append("{createdBy=");
        sb.append(getCreatedBy());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", lineType=");
        sb.append(getLineType());
        sb.append(", keyword=");
        sb.append(getKeyword());
        sb.append(", itemGroupMsAssociation=");
        sb.append(getItemGroupMsAssociation());
        sb.append(", value=");
        sb.append(getValue());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", logicalOperator=");
        sb.append(getLogicalOperator());
        sb.append(", operator=");
        sb.append(getOperator());
        sb.append(", cdrDetailsSid=");
        sb.append(getCdrDetailsSid());
        sb.append(", cdrModelSid=");
        sb.append(getCdrModelSid());
        sb.append(", comparison=");
        sb.append(getComparison());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(43);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.CdrDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
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
            "<column><column-name>lineType</column-name><column-value><![CDATA[");
        sb.append(getLineType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>keyword</column-name><column-value><![CDATA[");
        sb.append(getKeyword());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemGroupMsAssociation</column-name><column-value><![CDATA[");
        sb.append(getItemGroupMsAssociation());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>value</column-name><column-value><![CDATA[");
        sb.append(getValue());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>logicalOperator</column-name><column-value><![CDATA[");
        sb.append(getLogicalOperator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>operator</column-name><column-value><![CDATA[");
        sb.append(getOperator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cdrDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getCdrDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cdrModelSid</column-name><column-value><![CDATA[");
        sb.append(getCdrModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>comparison</column-name><column-value><![CDATA[");
        sb.append(getComparison());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
