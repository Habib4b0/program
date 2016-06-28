package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.VwUserTablesLocalServiceUtil;

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


public class VwUserTablesClp extends BaseModelImpl<VwUserTables>
    implements VwUserTables {
    private int _uniqueId;
    private String _tableName;
    private String _columnName;
    private BaseModel<?> _vwUserTablesRemoteModel;

    public VwUserTablesClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return VwUserTables.class;
    }

    @Override
    public String getModelClassName() {
        return VwUserTables.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _uniqueId;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setUniqueId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _uniqueId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("uniqueId", getUniqueId());
        attributes.put("tableName", getTableName());
        attributes.put("columnName", getColumnName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer uniqueId = (Integer) attributes.get("uniqueId");

        if (uniqueId != null) {
            setUniqueId(uniqueId);
        }

        String tableName = (String) attributes.get("tableName");

        if (tableName != null) {
            setTableName(tableName);
        }

        String columnName = (String) attributes.get("columnName");

        if (columnName != null) {
            setColumnName(columnName);
        }
    }

    @Override
    public int getUniqueId() {
        return _uniqueId;
    }

    @Override
    public void setUniqueId(int uniqueId) {
        _uniqueId = uniqueId;

        if (_vwUserTablesRemoteModel != null) {
            try {
                Class<?> clazz = _vwUserTablesRemoteModel.getClass();

                Method method = clazz.getMethod("setUniqueId", int.class);

                method.invoke(_vwUserTablesRemoteModel, uniqueId);
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

        if (_vwUserTablesRemoteModel != null) {
            try {
                Class<?> clazz = _vwUserTablesRemoteModel.getClass();

                Method method = clazz.getMethod("setTableName", String.class);

                method.invoke(_vwUserTablesRemoteModel, tableName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getColumnName() {
        return _columnName;
    }

    @Override
    public void setColumnName(String columnName) {
        _columnName = columnName;

        if (_vwUserTablesRemoteModel != null) {
            try {
                Class<?> clazz = _vwUserTablesRemoteModel.getClass();

                Method method = clazz.getMethod("setColumnName", String.class);

                method.invoke(_vwUserTablesRemoteModel, columnName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getVwUserTablesRemoteModel() {
        return _vwUserTablesRemoteModel;
    }

    public void setVwUserTablesRemoteModel(BaseModel<?> vwUserTablesRemoteModel) {
        _vwUserTablesRemoteModel = vwUserTablesRemoteModel;
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

        Class<?> remoteModelClass = _vwUserTablesRemoteModel.getClass();

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

        Object returnValue = method.invoke(_vwUserTablesRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            VwUserTablesLocalServiceUtil.addVwUserTables(this);
        } else {
            VwUserTablesLocalServiceUtil.updateVwUserTables(this);
        }
    }

    @Override
    public VwUserTables toEscapedModel() {
        return (VwUserTables) ProxyUtil.newProxyInstance(VwUserTables.class.getClassLoader(),
            new Class[] { VwUserTables.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        VwUserTablesClp clone = new VwUserTablesClp();

        clone.setUniqueId(getUniqueId());
        clone.setTableName(getTableName());
        clone.setColumnName(getColumnName());

        return clone;
    }

    @Override
    public int compareTo(VwUserTables vwUserTables) {
        int primaryKey = vwUserTables.getPrimaryKey();

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

        if (!(obj instanceof VwUserTablesClp)) {
            return false;
        }

        VwUserTablesClp vwUserTables = (VwUserTablesClp) obj;

        int primaryKey = vwUserTables.getPrimaryKey();

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
        StringBundler sb = new StringBundler(7);

        sb.append("{uniqueId=");
        sb.append(getUniqueId());
        sb.append(", tableName=");
        sb.append(getTableName());
        sb.append(", columnName=");
        sb.append(getColumnName());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(13);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.VwUserTables");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>uniqueId</column-name><column-value><![CDATA[");
        sb.append(getUniqueId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>tableName</column-name><column-value><![CDATA[");
        sb.append(getTableName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>columnName</column-name><column-value><![CDATA[");
        sb.append(getColumnName());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
