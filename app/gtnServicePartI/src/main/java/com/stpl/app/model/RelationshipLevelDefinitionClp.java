package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.RelationshipLevelDefinitionLocalServiceUtil;

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


public class RelationshipLevelDefinitionClp extends BaseModelImpl<RelationshipLevelDefinition>
    implements RelationshipLevelDefinition {
    private String _relationshipLevelValues;
    private int _hierarchyLevelDefinitionSid;
    private String _parentNode;
    private int _versionNo;
    private int _relationshipBuilderSid;
    private Date _modifiedDate;
    private int _createdBy;
    private Date _createdDate;
    private String _levelNo;
    private int _modifiedBy;
    private String _hierarchyNo;
    private int _relationshipLevelSid;
    private String _flag;
    private String _levelName;
    private String _parentHierarchyNo;
    private BaseModel<?> _relationshipLevelDefinitionRemoteModel;

    public RelationshipLevelDefinitionClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return RelationshipLevelDefinition.class;
    }

    @Override
    public String getModelClassName() {
        return RelationshipLevelDefinition.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _relationshipLevelSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setRelationshipLevelSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _relationshipLevelSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("relationshipLevelValues", getRelationshipLevelValues());
        attributes.put("hierarchyLevelDefinitionSid",
            getHierarchyLevelDefinitionSid());
        attributes.put("parentNode", getParentNode());
        attributes.put("versionNo", getVersionNo());
        attributes.put("relationshipBuilderSid", getRelationshipBuilderSid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("levelNo", getLevelNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("hierarchyNo", getHierarchyNo());
        attributes.put("relationshipLevelSid", getRelationshipLevelSid());
        attributes.put("flag", getFlag());
        attributes.put("levelName", getLevelName());
        attributes.put("parentHierarchyNo", getParentHierarchyNo());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String relationshipLevelValues = (String) attributes.get(
                "relationshipLevelValues");

        if (relationshipLevelValues != null) {
            setRelationshipLevelValues(relationshipLevelValues);
        }

        Integer hierarchyLevelDefinitionSid = (Integer) attributes.get(
                "hierarchyLevelDefinitionSid");

        if (hierarchyLevelDefinitionSid != null) {
            setHierarchyLevelDefinitionSid(hierarchyLevelDefinitionSid);
        }

        String parentNode = (String) attributes.get("parentNode");

        if (parentNode != null) {
            setParentNode(parentNode);
        }

        Integer versionNo = (Integer) attributes.get("versionNo");

        if (versionNo != null) {
            setVersionNo(versionNo);
        }

        Integer relationshipBuilderSid = (Integer) attributes.get(
                "relationshipBuilderSid");

        if (relationshipBuilderSid != null) {
            setRelationshipBuilderSid(relationshipBuilderSid);
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

        String levelNo = (String) attributes.get("levelNo");

        if (levelNo != null) {
            setLevelNo(levelNo);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String hierarchyNo = (String) attributes.get("hierarchyNo");

        if (hierarchyNo != null) {
            setHierarchyNo(hierarchyNo);
        }

        Integer relationshipLevelSid = (Integer) attributes.get(
                "relationshipLevelSid");

        if (relationshipLevelSid != null) {
            setRelationshipLevelSid(relationshipLevelSid);
        }

        String flag = (String) attributes.get("flag");

        if (flag != null) {
            setFlag(flag);
        }

        String levelName = (String) attributes.get("levelName");

        if (levelName != null) {
            setLevelName(levelName);
        }

        String parentHierarchyNo = (String) attributes.get("parentHierarchyNo");

        if (parentHierarchyNo != null) {
            setParentHierarchyNo(parentHierarchyNo);
        }
    }

    @Override
    public String getRelationshipLevelValues() {
        return _relationshipLevelValues;
    }

    @Override
    public void setRelationshipLevelValues(String relationshipLevelValues) {
        _relationshipLevelValues = relationshipLevelValues;

        if (_relationshipLevelDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _relationshipLevelDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setRelationshipLevelValues",
                        String.class);

                method.invoke(_relationshipLevelDefinitionRemoteModel,
                    relationshipLevelValues);
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

        if (_relationshipLevelDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _relationshipLevelDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setHierarchyLevelDefinitionSid",
                        int.class);

                method.invoke(_relationshipLevelDefinitionRemoteModel,
                    hierarchyLevelDefinitionSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getParentNode() {
        return _parentNode;
    }

    @Override
    public void setParentNode(String parentNode) {
        _parentNode = parentNode;

        if (_relationshipLevelDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _relationshipLevelDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setParentNode", String.class);

                method.invoke(_relationshipLevelDefinitionRemoteModel,
                    parentNode);
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

        if (_relationshipLevelDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _relationshipLevelDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setVersionNo", int.class);

                method.invoke(_relationshipLevelDefinitionRemoteModel, versionNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRelationshipBuilderSid() {
        return _relationshipBuilderSid;
    }

    @Override
    public void setRelationshipBuilderSid(int relationshipBuilderSid) {
        _relationshipBuilderSid = relationshipBuilderSid;

        if (_relationshipLevelDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _relationshipLevelDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setRelationshipBuilderSid",
                        int.class);

                method.invoke(_relationshipLevelDefinitionRemoteModel,
                    relationshipBuilderSid);
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

        if (_relationshipLevelDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _relationshipLevelDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_relationshipLevelDefinitionRemoteModel,
                    modifiedDate);
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

        if (_relationshipLevelDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _relationshipLevelDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_relationshipLevelDefinitionRemoteModel, createdBy);
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

        if (_relationshipLevelDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _relationshipLevelDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_relationshipLevelDefinitionRemoteModel,
                    createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLevelNo() {
        return _levelNo;
    }

    @Override
    public void setLevelNo(String levelNo) {
        _levelNo = levelNo;

        if (_relationshipLevelDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _relationshipLevelDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setLevelNo", String.class);

                method.invoke(_relationshipLevelDefinitionRemoteModel, levelNo);
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

        if (_relationshipLevelDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _relationshipLevelDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_relationshipLevelDefinitionRemoteModel,
                    modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getHierarchyNo() {
        return _hierarchyNo;
    }

    @Override
    public void setHierarchyNo(String hierarchyNo) {
        _hierarchyNo = hierarchyNo;

        if (_relationshipLevelDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _relationshipLevelDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setHierarchyNo", String.class);

                method.invoke(_relationshipLevelDefinitionRemoteModel,
                    hierarchyNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRelationshipLevelSid() {
        return _relationshipLevelSid;
    }

    @Override
    public void setRelationshipLevelSid(int relationshipLevelSid) {
        _relationshipLevelSid = relationshipLevelSid;

        if (_relationshipLevelDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _relationshipLevelDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setRelationshipLevelSid",
                        int.class);

                method.invoke(_relationshipLevelDefinitionRemoteModel,
                    relationshipLevelSid);
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

        if (_relationshipLevelDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _relationshipLevelDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setFlag", String.class);

                method.invoke(_relationshipLevelDefinitionRemoteModel, flag);
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

        if (_relationshipLevelDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _relationshipLevelDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setLevelName", String.class);

                method.invoke(_relationshipLevelDefinitionRemoteModel, levelName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getParentHierarchyNo() {
        return _parentHierarchyNo;
    }

    @Override
    public void setParentHierarchyNo(String parentHierarchyNo) {
        _parentHierarchyNo = parentHierarchyNo;

        if (_relationshipLevelDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _relationshipLevelDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setParentHierarchyNo",
                        String.class);

                method.invoke(_relationshipLevelDefinitionRemoteModel,
                    parentHierarchyNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getRelationshipLevelDefinitionRemoteModel() {
        return _relationshipLevelDefinitionRemoteModel;
    }

    public void setRelationshipLevelDefinitionRemoteModel(
        BaseModel<?> relationshipLevelDefinitionRemoteModel) {
        _relationshipLevelDefinitionRemoteModel = relationshipLevelDefinitionRemoteModel;
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

        Class<?> remoteModelClass = _relationshipLevelDefinitionRemoteModel.getClass();

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

        Object returnValue = method.invoke(_relationshipLevelDefinitionRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            RelationshipLevelDefinitionLocalServiceUtil.addRelationshipLevelDefinition(this);
        } else {
            RelationshipLevelDefinitionLocalServiceUtil.updateRelationshipLevelDefinition(this);
        }
    }

    @Override
    public RelationshipLevelDefinition toEscapedModel() {
        return (RelationshipLevelDefinition) ProxyUtil.newProxyInstance(RelationshipLevelDefinition.class.getClassLoader(),
            new Class[] { RelationshipLevelDefinition.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        RelationshipLevelDefinitionClp clone = new RelationshipLevelDefinitionClp();

        clone.setRelationshipLevelValues(getRelationshipLevelValues());
        clone.setHierarchyLevelDefinitionSid(getHierarchyLevelDefinitionSid());
        clone.setParentNode(getParentNode());
        clone.setVersionNo(getVersionNo());
        clone.setRelationshipBuilderSid(getRelationshipBuilderSid());
        clone.setModifiedDate(getModifiedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setLevelNo(getLevelNo());
        clone.setModifiedBy(getModifiedBy());
        clone.setHierarchyNo(getHierarchyNo());
        clone.setRelationshipLevelSid(getRelationshipLevelSid());
        clone.setFlag(getFlag());
        clone.setLevelName(getLevelName());
        clone.setParentHierarchyNo(getParentHierarchyNo());

        return clone;
    }

    @Override
    public int compareTo(
        RelationshipLevelDefinition relationshipLevelDefinition) {
        int primaryKey = relationshipLevelDefinition.getPrimaryKey();

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

        if (!(obj instanceof RelationshipLevelDefinitionClp)) {
            return false;
        }

        RelationshipLevelDefinitionClp relationshipLevelDefinition = (RelationshipLevelDefinitionClp) obj;

        int primaryKey = relationshipLevelDefinition.getPrimaryKey();

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
        StringBundler sb = new StringBundler(31);

        sb.append("{relationshipLevelValues=");
        sb.append(getRelationshipLevelValues());
        sb.append(", hierarchyLevelDefinitionSid=");
        sb.append(getHierarchyLevelDefinitionSid());
        sb.append(", parentNode=");
        sb.append(getParentNode());
        sb.append(", versionNo=");
        sb.append(getVersionNo());
        sb.append(", relationshipBuilderSid=");
        sb.append(getRelationshipBuilderSid());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", levelNo=");
        sb.append(getLevelNo());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", hierarchyNo=");
        sb.append(getHierarchyNo());
        sb.append(", relationshipLevelSid=");
        sb.append(getRelationshipLevelSid());
        sb.append(", flag=");
        sb.append(getFlag());
        sb.append(", levelName=");
        sb.append(getLevelName());
        sb.append(", parentHierarchyNo=");
        sb.append(getParentHierarchyNo());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(49);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.RelationshipLevelDefinition");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>relationshipLevelValues</column-name><column-value><![CDATA[");
        sb.append(getRelationshipLevelValues());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>hierarchyLevelDefinitionSid</column-name><column-value><![CDATA[");
        sb.append(getHierarchyLevelDefinitionSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parentNode</column-name><column-value><![CDATA[");
        sb.append(getParentNode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>versionNo</column-name><column-value><![CDATA[");
        sb.append(getVersionNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>relationshipBuilderSid</column-name><column-value><![CDATA[");
        sb.append(getRelationshipBuilderSid());
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
            "<column><column-name>levelNo</column-name><column-value><![CDATA[");
        sb.append(getLevelNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>hierarchyNo</column-name><column-value><![CDATA[");
        sb.append(getHierarchyNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>relationshipLevelSid</column-name><column-value><![CDATA[");
        sb.append(getRelationshipLevelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>flag</column-name><column-value><![CDATA[");
        sb.append(getFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>levelName</column-name><column-value><![CDATA[");
        sb.append(getLevelName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parentHierarchyNo</column-name><column-value><![CDATA[");
        sb.append(getParentHierarchyNo());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
