package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.HistHierarchyLevelDefnLocalServiceUtil;
import com.stpl.app.service.persistence.HistHierarchyLevelDefnPK;

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


public class HistHierarchyLevelDefnClp extends BaseModelImpl<HistHierarchyLevelDefn>
    implements HistHierarchyLevelDefn {
    private String _tableName;
    private Date _actionDate;
    private String _fieldName;
    private int _hierarchyLevelDefinitionSid;
    private int _versionNo;
    private Date _modifiedDate;
    private int _createdBy;
    private Date _createdDate;
    private String _levelValueReference;
    private int _levelNo;
    private String _actionFlag;
    private int _hierarchyDefinitionSid;
    private int _modifiedBy;
    private String _levelName;
    private BaseModel<?> _histHierarchyLevelDefnRemoteModel;

    public HistHierarchyLevelDefnClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return HistHierarchyLevelDefn.class;
    }

    @Override
    public String getModelClassName() {
        return HistHierarchyLevelDefn.class.getName();
    }

    @Override
    public HistHierarchyLevelDefnPK getPrimaryKey() {
        return new HistHierarchyLevelDefnPK(_hierarchyLevelDefinitionSid,
            _versionNo, _actionFlag);
    }

    @Override
    public void setPrimaryKey(HistHierarchyLevelDefnPK primaryKey) {
        setHierarchyLevelDefinitionSid(primaryKey.hierarchyLevelDefinitionSid);
        setVersionNo(primaryKey.versionNo);
        setActionFlag(primaryKey.actionFlag);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new HistHierarchyLevelDefnPK(_hierarchyLevelDefinitionSid,
            _versionNo, _actionFlag);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((HistHierarchyLevelDefnPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("tableName", getTableName());
        attributes.put("actionDate", getActionDate());
        attributes.put("fieldName", getFieldName());
        attributes.put("hierarchyLevelDefinitionSid",
            getHierarchyLevelDefinitionSid());
        attributes.put("versionNo", getVersionNo());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("levelValueReference", getLevelValueReference());
        attributes.put("levelNo", getLevelNo());
        attributes.put("actionFlag", getActionFlag());
        attributes.put("hierarchyDefinitionSid", getHierarchyDefinitionSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("levelName", getLevelName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String tableName = (String) attributes.get("tableName");

        if (tableName != null) {
            setTableName(tableName);
        }

        Date actionDate = (Date) attributes.get("actionDate");

        if (actionDate != null) {
            setActionDate(actionDate);
        }

        String fieldName = (String) attributes.get("fieldName");

        if (fieldName != null) {
            setFieldName(fieldName);
        }

        Integer hierarchyLevelDefinitionSid = (Integer) attributes.get(
                "hierarchyLevelDefinitionSid");

        if (hierarchyLevelDefinitionSid != null) {
            setHierarchyLevelDefinitionSid(hierarchyLevelDefinitionSid);
        }

        Integer versionNo = (Integer) attributes.get("versionNo");

        if (versionNo != null) {
            setVersionNo(versionNo);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String levelValueReference = (String) attributes.get(
                "levelValueReference");

        if (levelValueReference != null) {
            setLevelValueReference(levelValueReference);
        }

        Integer levelNo = (Integer) attributes.get("levelNo");

        if (levelNo != null) {
            setLevelNo(levelNo);
        }

        String actionFlag = (String) attributes.get("actionFlag");

        if (actionFlag != null) {
            setActionFlag(actionFlag);
        }

        Integer hierarchyDefinitionSid = (Integer) attributes.get(
                "hierarchyDefinitionSid");

        if (hierarchyDefinitionSid != null) {
            setHierarchyDefinitionSid(hierarchyDefinitionSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
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

        if (_histHierarchyLevelDefnRemoteModel != null) {
            try {
                Class<?> clazz = _histHierarchyLevelDefnRemoteModel.getClass();

                Method method = clazz.getMethod("setTableName", String.class);

                method.invoke(_histHierarchyLevelDefnRemoteModel, tableName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getActionDate() {
        return _actionDate;
    }

    @Override
    public void setActionDate(Date actionDate) {
        _actionDate = actionDate;

        if (_histHierarchyLevelDefnRemoteModel != null) {
            try {
                Class<?> clazz = _histHierarchyLevelDefnRemoteModel.getClass();

                Method method = clazz.getMethod("setActionDate", Date.class);

                method.invoke(_histHierarchyLevelDefnRemoteModel, actionDate);
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

        if (_histHierarchyLevelDefnRemoteModel != null) {
            try {
                Class<?> clazz = _histHierarchyLevelDefnRemoteModel.getClass();

                Method method = clazz.getMethod("setFieldName", String.class);

                method.invoke(_histHierarchyLevelDefnRemoteModel, fieldName);
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

        if (_histHierarchyLevelDefnRemoteModel != null) {
            try {
                Class<?> clazz = _histHierarchyLevelDefnRemoteModel.getClass();

                Method method = clazz.getMethod("setHierarchyLevelDefinitionSid",
                        int.class);

                method.invoke(_histHierarchyLevelDefnRemoteModel,
                    hierarchyLevelDefinitionSid);
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

        if (_histHierarchyLevelDefnRemoteModel != null) {
            try {
                Class<?> clazz = _histHierarchyLevelDefnRemoteModel.getClass();

                Method method = clazz.getMethod("setVersionNo", int.class);

                method.invoke(_histHierarchyLevelDefnRemoteModel, versionNo);
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

        if (_histHierarchyLevelDefnRemoteModel != null) {
            try {
                Class<?> clazz = _histHierarchyLevelDefnRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_histHierarchyLevelDefnRemoteModel, modifiedDate);
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

        if (_histHierarchyLevelDefnRemoteModel != null) {
            try {
                Class<?> clazz = _histHierarchyLevelDefnRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_histHierarchyLevelDefnRemoteModel, createdBy);
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

        if (_histHierarchyLevelDefnRemoteModel != null) {
            try {
                Class<?> clazz = _histHierarchyLevelDefnRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_histHierarchyLevelDefnRemoteModel, createdDate);
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

        if (_histHierarchyLevelDefnRemoteModel != null) {
            try {
                Class<?> clazz = _histHierarchyLevelDefnRemoteModel.getClass();

                Method method = clazz.getMethod("setLevelValueReference",
                        String.class);

                method.invoke(_histHierarchyLevelDefnRemoteModel,
                    levelValueReference);
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

        if (_histHierarchyLevelDefnRemoteModel != null) {
            try {
                Class<?> clazz = _histHierarchyLevelDefnRemoteModel.getClass();

                Method method = clazz.getMethod("setLevelNo", int.class);

                method.invoke(_histHierarchyLevelDefnRemoteModel, levelNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getActionFlag() {
        return _actionFlag;
    }

    @Override
    public void setActionFlag(String actionFlag) {
        _actionFlag = actionFlag;

        if (_histHierarchyLevelDefnRemoteModel != null) {
            try {
                Class<?> clazz = _histHierarchyLevelDefnRemoteModel.getClass();

                Method method = clazz.getMethod("setActionFlag", String.class);

                method.invoke(_histHierarchyLevelDefnRemoteModel, actionFlag);
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

        if (_histHierarchyLevelDefnRemoteModel != null) {
            try {
                Class<?> clazz = _histHierarchyLevelDefnRemoteModel.getClass();

                Method method = clazz.getMethod("setHierarchyDefinitionSid",
                        int.class);

                method.invoke(_histHierarchyLevelDefnRemoteModel,
                    hierarchyDefinitionSid);
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

        if (_histHierarchyLevelDefnRemoteModel != null) {
            try {
                Class<?> clazz = _histHierarchyLevelDefnRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_histHierarchyLevelDefnRemoteModel, modifiedBy);
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

        if (_histHierarchyLevelDefnRemoteModel != null) {
            try {
                Class<?> clazz = _histHierarchyLevelDefnRemoteModel.getClass();

                Method method = clazz.getMethod("setLevelName", String.class);

                method.invoke(_histHierarchyLevelDefnRemoteModel, levelName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getHistHierarchyLevelDefnRemoteModel() {
        return _histHierarchyLevelDefnRemoteModel;
    }

    public void setHistHierarchyLevelDefnRemoteModel(
        BaseModel<?> histHierarchyLevelDefnRemoteModel) {
        _histHierarchyLevelDefnRemoteModel = histHierarchyLevelDefnRemoteModel;
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

        Class<?> remoteModelClass = _histHierarchyLevelDefnRemoteModel.getClass();

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

        Object returnValue = method.invoke(_histHierarchyLevelDefnRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            HistHierarchyLevelDefnLocalServiceUtil.addHistHierarchyLevelDefn(this);
        } else {
            HistHierarchyLevelDefnLocalServiceUtil.updateHistHierarchyLevelDefn(this);
        }
    }

    @Override
    public HistHierarchyLevelDefn toEscapedModel() {
        return (HistHierarchyLevelDefn) ProxyUtil.newProxyInstance(HistHierarchyLevelDefn.class.getClassLoader(),
            new Class[] { HistHierarchyLevelDefn.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        HistHierarchyLevelDefnClp clone = new HistHierarchyLevelDefnClp();

        clone.setTableName(getTableName());
        clone.setActionDate(getActionDate());
        clone.setFieldName(getFieldName());
        clone.setHierarchyLevelDefinitionSid(getHierarchyLevelDefinitionSid());
        clone.setVersionNo(getVersionNo());
        clone.setModifiedDate(getModifiedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setLevelValueReference(getLevelValueReference());
        clone.setLevelNo(getLevelNo());
        clone.setActionFlag(getActionFlag());
        clone.setHierarchyDefinitionSid(getHierarchyDefinitionSid());
        clone.setModifiedBy(getModifiedBy());
        clone.setLevelName(getLevelName());

        return clone;
    }

    @Override
    public int compareTo(HistHierarchyLevelDefn histHierarchyLevelDefn) {
        HistHierarchyLevelDefnPK primaryKey = histHierarchyLevelDefn.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof HistHierarchyLevelDefnClp)) {
            return false;
        }

        HistHierarchyLevelDefnClp histHierarchyLevelDefn = (HistHierarchyLevelDefnClp) obj;

        HistHierarchyLevelDefnPK primaryKey = histHierarchyLevelDefn.getPrimaryKey();

        if (getPrimaryKey().equals(primaryKey)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getPrimaryKey().hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(29);

        sb.append("{tableName=");
        sb.append(getTableName());
        sb.append(", actionDate=");
        sb.append(getActionDate());
        sb.append(", fieldName=");
        sb.append(getFieldName());
        sb.append(", hierarchyLevelDefinitionSid=");
        sb.append(getHierarchyLevelDefinitionSid());
        sb.append(", versionNo=");
        sb.append(getVersionNo());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", levelValueReference=");
        sb.append(getLevelValueReference());
        sb.append(", levelNo=");
        sb.append(getLevelNo());
        sb.append(", actionFlag=");
        sb.append(getActionFlag());
        sb.append(", hierarchyDefinitionSid=");
        sb.append(getHierarchyDefinitionSid());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", levelName=");
        sb.append(getLevelName());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(46);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.HistHierarchyLevelDefn");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>tableName</column-name><column-value><![CDATA[");
        sb.append(getTableName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actionDate</column-name><column-value><![CDATA[");
        sb.append(getActionDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fieldName</column-name><column-value><![CDATA[");
        sb.append(getFieldName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>hierarchyLevelDefinitionSid</column-name><column-value><![CDATA[");
        sb.append(getHierarchyLevelDefinitionSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>versionNo</column-name><column-value><![CDATA[");
        sb.append(getVersionNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>levelValueReference</column-name><column-value><![CDATA[");
        sb.append(getLevelValueReference());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>levelNo</column-name><column-value><![CDATA[");
        sb.append(getLevelNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actionFlag</column-name><column-value><![CDATA[");
        sb.append(getActionFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>hierarchyDefinitionSid</column-name><column-value><![CDATA[");
        sb.append(getHierarchyDefinitionSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>levelName</column-name><column-value><![CDATA[");
        sb.append(getLevelName());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
