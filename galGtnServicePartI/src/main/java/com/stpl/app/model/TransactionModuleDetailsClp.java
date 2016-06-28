package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.TransactionModuleDetailsLocalServiceUtil;

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


public class TransactionModuleDetailsClp extends BaseModelImpl<TransactionModuleDetails>
    implements TransactionModuleDetails {
    private double _propertyIndex;
    private String _displayName;
    private int _transactionModuleMasterSid;
    private String _categoryName;
    private String _validation;
    private String _propertyName;
    private String _flag;
    private int _transactionModuleDetailsSid;
    private BaseModel<?> _transactionModuleDetailsRemoteModel;

    public TransactionModuleDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return TransactionModuleDetails.class;
    }

    @Override
    public String getModelClassName() {
        return TransactionModuleDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _transactionModuleDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setTransactionModuleDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _transactionModuleDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("propertyIndex", getPropertyIndex());
        attributes.put("displayName", getDisplayName());
        attributes.put("transactionModuleMasterSid",
            getTransactionModuleMasterSid());
        attributes.put("categoryName", getCategoryName());
        attributes.put("validation", getValidation());
        attributes.put("propertyName", getPropertyName());
        attributes.put("flag", getFlag());
        attributes.put("transactionModuleDetailsSid",
            getTransactionModuleDetailsSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double propertyIndex = (Double) attributes.get("propertyIndex");

        if (propertyIndex != null) {
            setPropertyIndex(propertyIndex);
        }

        String displayName = (String) attributes.get("displayName");

        if (displayName != null) {
            setDisplayName(displayName);
        }

        Integer transactionModuleMasterSid = (Integer) attributes.get(
                "transactionModuleMasterSid");

        if (transactionModuleMasterSid != null) {
            setTransactionModuleMasterSid(transactionModuleMasterSid);
        }

        String categoryName = (String) attributes.get("categoryName");

        if (categoryName != null) {
            setCategoryName(categoryName);
        }

        String validation = (String) attributes.get("validation");

        if (validation != null) {
            setValidation(validation);
        }

        String propertyName = (String) attributes.get("propertyName");

        if (propertyName != null) {
            setPropertyName(propertyName);
        }

        String flag = (String) attributes.get("flag");

        if (flag != null) {
            setFlag(flag);
        }

        Integer transactionModuleDetailsSid = (Integer) attributes.get(
                "transactionModuleDetailsSid");

        if (transactionModuleDetailsSid != null) {
            setTransactionModuleDetailsSid(transactionModuleDetailsSid);
        }
    }

    @Override
    public double getPropertyIndex() {
        return _propertyIndex;
    }

    @Override
    public void setPropertyIndex(double propertyIndex) {
        _propertyIndex = propertyIndex;

        if (_transactionModuleDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _transactionModuleDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPropertyIndex", double.class);

                method.invoke(_transactionModuleDetailsRemoteModel,
                    propertyIndex);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDisplayName() {
        return _displayName;
    }

    @Override
    public void setDisplayName(String displayName) {
        _displayName = displayName;

        if (_transactionModuleDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _transactionModuleDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setDisplayName", String.class);

                method.invoke(_transactionModuleDetailsRemoteModel, displayName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getTransactionModuleMasterSid() {
        return _transactionModuleMasterSid;
    }

    @Override
    public void setTransactionModuleMasterSid(int transactionModuleMasterSid) {
        _transactionModuleMasterSid = transactionModuleMasterSid;

        if (_transactionModuleDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _transactionModuleDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setTransactionModuleMasterSid",
                        int.class);

                method.invoke(_transactionModuleDetailsRemoteModel,
                    transactionModuleMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCategoryName() {
        return _categoryName;
    }

    @Override
    public void setCategoryName(String categoryName) {
        _categoryName = categoryName;

        if (_transactionModuleDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _transactionModuleDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCategoryName", String.class);

                method.invoke(_transactionModuleDetailsRemoteModel, categoryName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getValidation() {
        return _validation;
    }

    @Override
    public void setValidation(String validation) {
        _validation = validation;

        if (_transactionModuleDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _transactionModuleDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setValidation", String.class);

                method.invoke(_transactionModuleDetailsRemoteModel, validation);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPropertyName() {
        return _propertyName;
    }

    @Override
    public void setPropertyName(String propertyName) {
        _propertyName = propertyName;

        if (_transactionModuleDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _transactionModuleDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPropertyName", String.class);

                method.invoke(_transactionModuleDetailsRemoteModel, propertyName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFlag() {
        return _flag;
    }

    @Override
    public void setFlag(String flag) {
        _flag = flag;

        if (_transactionModuleDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _transactionModuleDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setFlag", String.class);

                method.invoke(_transactionModuleDetailsRemoteModel, flag);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getTransactionModuleDetailsSid() {
        return _transactionModuleDetailsSid;
    }

    @Override
    public void setTransactionModuleDetailsSid(int transactionModuleDetailsSid) {
        _transactionModuleDetailsSid = transactionModuleDetailsSid;

        if (_transactionModuleDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _transactionModuleDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setTransactionModuleDetailsSid",
                        int.class);

                method.invoke(_transactionModuleDetailsRemoteModel,
                    transactionModuleDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getTransactionModuleDetailsRemoteModel() {
        return _transactionModuleDetailsRemoteModel;
    }

    public void setTransactionModuleDetailsRemoteModel(
        BaseModel<?> transactionModuleDetailsRemoteModel) {
        _transactionModuleDetailsRemoteModel = transactionModuleDetailsRemoteModel;
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

        Class<?> remoteModelClass = _transactionModuleDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_transactionModuleDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            TransactionModuleDetailsLocalServiceUtil.addTransactionModuleDetails(this);
        } else {
            TransactionModuleDetailsLocalServiceUtil.updateTransactionModuleDetails(this);
        }
    }

    @Override
    public TransactionModuleDetails toEscapedModel() {
        return (TransactionModuleDetails) ProxyUtil.newProxyInstance(TransactionModuleDetails.class.getClassLoader(),
            new Class[] { TransactionModuleDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        TransactionModuleDetailsClp clone = new TransactionModuleDetailsClp();

        clone.setPropertyIndex(getPropertyIndex());
        clone.setDisplayName(getDisplayName());
        clone.setTransactionModuleMasterSid(getTransactionModuleMasterSid());
        clone.setCategoryName(getCategoryName());
        clone.setValidation(getValidation());
        clone.setPropertyName(getPropertyName());
        clone.setFlag(getFlag());
        clone.setTransactionModuleDetailsSid(getTransactionModuleDetailsSid());

        return clone;
    }

    @Override
    public int compareTo(TransactionModuleDetails transactionModuleDetails) {
        int primaryKey = transactionModuleDetails.getPrimaryKey();

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

        if (!(obj instanceof TransactionModuleDetailsClp)) {
            return false;
        }

        TransactionModuleDetailsClp transactionModuleDetails = (TransactionModuleDetailsClp) obj;

        int primaryKey = transactionModuleDetails.getPrimaryKey();

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
        StringBundler sb = new StringBundler(17);

        sb.append("{propertyIndex=");
        sb.append(getPropertyIndex());
        sb.append(", displayName=");
        sb.append(getDisplayName());
        sb.append(", transactionModuleMasterSid=");
        sb.append(getTransactionModuleMasterSid());
        sb.append(", categoryName=");
        sb.append(getCategoryName());
        sb.append(", validation=");
        sb.append(getValidation());
        sb.append(", propertyName=");
        sb.append(getPropertyName());
        sb.append(", flag=");
        sb.append(getFlag());
        sb.append(", transactionModuleDetailsSid=");
        sb.append(getTransactionModuleDetailsSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(28);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.TransactionModuleDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>propertyIndex</column-name><column-value><![CDATA[");
        sb.append(getPropertyIndex());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>displayName</column-name><column-value><![CDATA[");
        sb.append(getDisplayName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>transactionModuleMasterSid</column-name><column-value><![CDATA[");
        sb.append(getTransactionModuleMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>categoryName</column-name><column-value><![CDATA[");
        sb.append(getCategoryName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>validation</column-name><column-value><![CDATA[");
        sb.append(getValidation());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>propertyName</column-name><column-value><![CDATA[");
        sb.append(getPropertyName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>flag</column-name><column-value><![CDATA[");
        sb.append(getFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>transactionModuleDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getTransactionModuleDetailsSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
