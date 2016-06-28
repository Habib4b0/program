package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.HierarchyLevelDefinitionLocalServiceUtil;

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


public class HierarchyLevelDefinitionClp extends BaseModelImpl<HierarchyLevelDefinition>
    implements HierarchyLevelDefinition {
    private String _tableName;
    private Date _createdDate;
    private int _createdBy;
    private String _levelValueReference;
    private String _fieldName;
    private int _levelNo;
    private int _hierarchyLevelDefinitionSid;
    private int _hierarchyDefinitionSid;
    private int _versionNo;
    private int _modifiedBy;
    private Date _modifiedDate;
    private String _levelName;
    private BaseModel<?> _hierarchyLevelDefinitionRemoteModel;

    public HierarchyLevelDefinitionClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return HierarchyLevelDefinition.class;
    }

    @Override
    public String getModelClassName() {
        return HierarchyLevelDefinition.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _hierarchyLevelDefinitionSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setHierarchyLevelDefinitionSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _hierarchyLevelDefinitionSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("tableName", getTableName());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("levelValueReference", getLevelValueReference());
        attributes.put("fieldName", getFieldName());
        attributes.put("levelNo", getLevelNo());
        attributes.put("hierarchyLevelDefinitionSid",
            getHierarchyLevelDefinitionSid());
        attributes.put("hierarchyDefinitionSid", getHierarchyDefinitionSid());
        attributes.put("versionNo", getVersionNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("levelName", getLevelName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String tableName = (String) attributes.get("tableName");

        if (tableName != null) {
            setTableName(tableName);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String levelValueReference = (String) attributes.get(
                "levelValueReference");

        if (levelValueReference != null) {
            setLevelValueReference(levelValueReference);
        }

        String fieldName = (String) attributes.get("fieldName");

        if (fieldName != null) {
            setFieldName(fieldName);
        }

        Integer levelNo = (Integer) attributes.get("levelNo");

        if (levelNo != null) {
            setLevelNo(levelNo);
        }

        Integer hierarchyLevelDefinitionSid = (Integer) attributes.get(
                "hierarchyLevelDefinitionSid");

        if (hierarchyLevelDefinitionSid != null) {
            setHierarchyLevelDefinitionSid(hierarchyLevelDefinitionSid);
        }

        Integer hierarchyDefinitionSid = (Integer) attributes.get(
                "hierarchyDefinitionSid");

        if (hierarchyDefinitionSid != null) {
            setHierarchyDefinitionSid(hierarchyDefinitionSid);
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

        String levelName = (String) attributes.get("levelName");

        if (levelName != null) {
            setLevelName(levelName);
        }
    }

    @Override
    public String getTableName() {
        return _tableName;
    }

    @Override
    public void setTableName(String tableName) {
        _tableName = tableName;

        if (_hierarchyLevelDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _hierarchyLevelDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setTableName", String.class);

                method.invoke(_hierarchyLevelDefinitionRemoteModel, tableName);
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

        if (_hierarchyLevelDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _hierarchyLevelDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_hierarchyLevelDefinitionRemoteModel, createdDate);
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

        if (_hierarchyLevelDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _hierarchyLevelDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_hierarchyLevelDefinitionRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevelValueReference() {
        return _levelValueReference;
    }

    @Override
    public void setLevelValueReference(String levelValueReference) {
        _levelValueReference = levelValueReference;

        if (_hierarchyLevelDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _hierarchyLevelDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setLevelValueReference",
                        String.class);

                method.invoke(_hierarchyLevelDefinitionRemoteModel,
                    levelValueReference);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFieldName() {
        return _fieldName;
    }

    @Override
    public void setFieldName(String fieldName) {
        _fieldName = fieldName;

        if (_hierarchyLevelDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _hierarchyLevelDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setFieldName", String.class);

                method.invoke(_hierarchyLevelDefinitionRemoteModel, fieldName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getLevelNo() {
        return _levelNo;
    }

    @Override
    public void setLevelNo(int levelNo) {
        _levelNo = levelNo;

        if (_hierarchyLevelDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _hierarchyLevelDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setLevelNo", int.class);

                method.invoke(_hierarchyLevelDefinitionRemoteModel, levelNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getHierarchyLevelDefinitionSid() {
        return _hierarchyLevelDefinitionSid;
    }

    @Override
    public void setHierarchyLevelDefinitionSid(int hierarchyLevelDefinitionSid) {
        _hierarchyLevelDefinitionSid = hierarchyLevelDefinitionSid;

        if (_hierarchyLevelDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _hierarchyLevelDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setHierarchyLevelDefinitionSid",
                        int.class);

                method.invoke(_hierarchyLevelDefinitionRemoteModel,
                    hierarchyLevelDefinitionSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getHierarchyDefinitionSid() {
        return _hierarchyDefinitionSid;
    }

    @Override
    public void setHierarchyDefinitionSid(int hierarchyDefinitionSid) {
        _hierarchyDefinitionSid = hierarchyDefinitionSid;

        if (_hierarchyLevelDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _hierarchyLevelDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setHierarchyDefinitionSid",
                        int.class);

                method.invoke(_hierarchyLevelDefinitionRemoteModel,
                    hierarchyDefinitionSid);
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

        if (_hierarchyLevelDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _hierarchyLevelDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setVersionNo", int.class);

                method.invoke(_hierarchyLevelDefinitionRemoteModel, versionNo);
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

        if (_hierarchyLevelDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _hierarchyLevelDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_hierarchyLevelDefinitionRemoteModel, modifiedBy);
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

        if (_hierarchyLevelDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _hierarchyLevelDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_hierarchyLevelDefinitionRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevelName() {
        return _levelName;
    }

    @Override
    public void setLevelName(String levelName) {
        _levelName = levelName;

        if (_hierarchyLevelDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _hierarchyLevelDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setLevelName", String.class);

                method.invoke(_hierarchyLevelDefinitionRemoteModel, levelName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getHierarchyLevelDefinitionRemoteModel() {
        return _hierarchyLevelDefinitionRemoteModel;
    }

    public void setHierarchyLevelDefinitionRemoteModel(
        BaseModel<?> hierarchyLevelDefinitionRemoteModel) {
        _hierarchyLevelDefinitionRemoteModel = hierarchyLevelDefinitionRemoteModel;
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

        Class<?> remoteModelClass = _hierarchyLevelDefinitionRemoteModel.getClass();

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

        Object returnValue = method.invoke(_hierarchyLevelDefinitionRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            HierarchyLevelDefinitionLocalServiceUtil.addHierarchyLevelDefinition(this);
        } else {
            HierarchyLevelDefinitionLocalServiceUtil.updateHierarchyLevelDefinition(this);
        }
    }

    @Override
    public HierarchyLevelDefinition toEscapedModel() {
        return (HierarchyLevelDefinition) ProxyUtil.newProxyInstance(HierarchyLevelDefinition.class.getClassLoader(),
            new Class[] { HierarchyLevelDefinition.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        HierarchyLevelDefinitionClp clone = new HierarchyLevelDefinitionClp();

        clone.setTableName(getTableName());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setLevelValueReference(getLevelValueReference());
        clone.setFieldName(getFieldName());
        clone.setLevelNo(getLevelNo());
        clone.setHierarchyLevelDefinitionSid(getHierarchyLevelDefinitionSid());
        clone.setHierarchyDefinitionSid(getHierarchyDefinitionSid());
        clone.setVersionNo(getVersionNo());
        clone.setModifiedBy(getModifiedBy());
        clone.setModifiedDate(getModifiedDate());
        clone.setLevelName(getLevelName());

        return clone;
    }

    @Override
    public int compareTo(HierarchyLevelDefinition hierarchyLevelDefinition) {
        int primaryKey = hierarchyLevelDefinition.getPrimaryKey();

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

        if (!(obj instanceof HierarchyLevelDefinitionClp)) {
            return false;
        }

        HierarchyLevelDefinitionClp hierarchyLevelDefinition = (HierarchyLevelDefinitionClp) obj;

        int primaryKey = hierarchyLevelDefinition.getPrimaryKey();

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

        sb.append("{tableName=");
        sb.append(getTableName());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", levelValueReference=");
        sb.append(getLevelValueReference());
        sb.append(", fieldName=");
        sb.append(getFieldName());
        sb.append(", levelNo=");
        sb.append(getLevelNo());
        sb.append(", hierarchyLevelDefinitionSid=");
        sb.append(getHierarchyLevelDefinitionSid());
        sb.append(", hierarchyDefinitionSid=");
        sb.append(getHierarchyDefinitionSid());
        sb.append(", versionNo=");
        sb.append(getVersionNo());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", levelName=");
        sb.append(getLevelName());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(40);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.HierarchyLevelDefinition");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>tableName</column-name><column-value><![CDATA[");
        sb.append(getTableName());
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
            "<column><column-name>levelValueReference</column-name><column-value><![CDATA[");
        sb.append(getLevelValueReference());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fieldName</column-name><column-value><![CDATA[");
        sb.append(getFieldName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>levelNo</column-name><column-value><![CDATA[");
        sb.append(getLevelNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>hierarchyLevelDefinitionSid</column-name><column-value><![CDATA[");
        sb.append(getHierarchyLevelDefinitionSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>hierarchyDefinitionSid</column-name><column-value><![CDATA[");
        sb.append(getHierarchyDefinitionSid());
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
            "<column><column-name>levelName</column-name><column-value><![CDATA[");
        sb.append(getLevelName());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
