package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.TransactionModuleMasterLocalServiceUtil;

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


public class TransactionModuleMasterClp extends BaseModelImpl<TransactionModuleMaster>
    implements TransactionModuleMaster {
    private int _transactionModuleMasterSid;
    private String _invalidTableName;
    private String _tableName;
    private String _moduleName;
    private String _tabName;
    private BaseModel<?> _transactionModuleMasterRemoteModel;

    public TransactionModuleMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return TransactionModuleMaster.class;
    }

    @Override
    public String getModelClassName() {
        return TransactionModuleMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _transactionModuleMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setTransactionModuleMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _transactionModuleMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("transactionModuleMasterSid",
            getTransactionModuleMasterSid());
        attributes.put("invalidTableName", getInvalidTableName());
        attributes.put("tableName", getTableName());
        attributes.put("moduleName", getModuleName());
        attributes.put("tabName", getTabName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer transactionModuleMasterSid = (Integer) attributes.get(
                "transactionModuleMasterSid");

        if (transactionModuleMasterSid != null) {
            setTransactionModuleMasterSid(transactionModuleMasterSid);
        }

        String invalidTableName = (String) attributes.get("invalidTableName");

        if (invalidTableName != null) {
            setInvalidTableName(invalidTableName);
        }

        String tableName = (String) attributes.get("tableName");

        if (tableName != null) {
            setTableName(tableName);
        }

        String moduleName = (String) attributes.get("moduleName");

        if (moduleName != null) {
            setModuleName(moduleName);
        }

        String tabName = (String) attributes.get("tabName");

        if (tabName != null) {
            setTabName(tabName);
        }
    }

    @Override
    public int getTransactionModuleMasterSid() {
        return _transactionModuleMasterSid;
    }

    @Override
    public void setTransactionModuleMasterSid(int transactionModuleMasterSid) {
        _transactionModuleMasterSid = transactionModuleMasterSid;

        if (_transactionModuleMasterRemoteModel != null) {
            try {
                Class<?> clazz = _transactionModuleMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setTransactionModuleMasterSid",
                        int.class);

                method.invoke(_transactionModuleMasterRemoteModel,
                    transactionModuleMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getInvalidTableName() {
        return _invalidTableName;
    }

    @Override
    public void setInvalidTableName(String invalidTableName) {
        _invalidTableName = invalidTableName;

        if (_transactionModuleMasterRemoteModel != null) {
            try {
                Class<?> clazz = _transactionModuleMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInvalidTableName",
                        String.class);

                method.invoke(_transactionModuleMasterRemoteModel,
                    invalidTableName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getTableName() {
        return _tableName;
    }

    @Override
    public void setTableName(String tableName) {
        _tableName = tableName;

        if (_transactionModuleMasterRemoteModel != null) {
            try {
                Class<?> clazz = _transactionModuleMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setTableName", String.class);

                method.invoke(_transactionModuleMasterRemoteModel, tableName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getModuleName() {
        return _moduleName;
    }

    @Override
    public void setModuleName(String moduleName) {
        _moduleName = moduleName;

        if (_transactionModuleMasterRemoteModel != null) {
            try {
                Class<?> clazz = _transactionModuleMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModuleName", String.class);

                method.invoke(_transactionModuleMasterRemoteModel, moduleName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getTabName() {
        return _tabName;
    }

    @Override
    public void setTabName(String tabName) {
        _tabName = tabName;

        if (_transactionModuleMasterRemoteModel != null) {
            try {
                Class<?> clazz = _transactionModuleMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setTabName", String.class);

                method.invoke(_transactionModuleMasterRemoteModel, tabName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getTransactionModuleMasterRemoteModel() {
        return _transactionModuleMasterRemoteModel;
    }

    public void setTransactionModuleMasterRemoteModel(
        BaseModel<?> transactionModuleMasterRemoteModel) {
        _transactionModuleMasterRemoteModel = transactionModuleMasterRemoteModel;
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

        Class<?> remoteModelClass = _transactionModuleMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_transactionModuleMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            TransactionModuleMasterLocalServiceUtil.addTransactionModuleMaster(this);
        } else {
            TransactionModuleMasterLocalServiceUtil.updateTransactionModuleMaster(this);
        }
    }

    @Override
    public TransactionModuleMaster toEscapedModel() {
        return (TransactionModuleMaster) ProxyUtil.newProxyInstance(TransactionModuleMaster.class.getClassLoader(),
            new Class[] { TransactionModuleMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        TransactionModuleMasterClp clone = new TransactionModuleMasterClp();

        clone.setTransactionModuleMasterSid(getTransactionModuleMasterSid());
        clone.setInvalidTableName(getInvalidTableName());
        clone.setTableName(getTableName());
        clone.setModuleName(getModuleName());
        clone.setTabName(getTabName());

        return clone;
    }

    @Override
    public int compareTo(TransactionModuleMaster transactionModuleMaster) {
        int primaryKey = transactionModuleMaster.getPrimaryKey();

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

        if (!(obj instanceof TransactionModuleMasterClp)) {
            return false;
        }

        TransactionModuleMasterClp transactionModuleMaster = (TransactionModuleMasterClp) obj;

        int primaryKey = transactionModuleMaster.getPrimaryKey();

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
        StringBundler sb = new StringBundler(11);

        sb.append("{transactionModuleMasterSid=");
        sb.append(getTransactionModuleMasterSid());
        sb.append(", invalidTableName=");
        sb.append(getInvalidTableName());
        sb.append(", tableName=");
        sb.append(getTableName());
        sb.append(", moduleName=");
        sb.append(getModuleName());
        sb.append(", tabName=");
        sb.append(getTabName());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(19);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.TransactionModuleMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>transactionModuleMasterSid</column-name><column-value><![CDATA[");
        sb.append(getTransactionModuleMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>invalidTableName</column-name><column-value><![CDATA[");
        sb.append(getInvalidTableName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>tableName</column-name><column-value><![CDATA[");
        sb.append(getTableName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>moduleName</column-name><column-value><![CDATA[");
        sb.append(getModuleName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>tabName</column-name><column-value><![CDATA[");
        sb.append(getTabName());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
