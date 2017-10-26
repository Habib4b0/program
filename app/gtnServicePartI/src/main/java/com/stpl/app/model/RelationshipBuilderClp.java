package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.RelationshipBuilderLocalServiceUtil;

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


public class RelationshipBuilderClp extends BaseModelImpl<RelationshipBuilder>
    implements RelationshipBuilder {
    private Date _startDate;
    private Date _createdDate;
    private int _createdBy;
    private String _relationshipDescription;
    private int _hierarchyDefinitionSid;
    private int _versionNo;
    private String _relationshipName;
    private int _relationshipBuilderSid;
    private int _hierarchyVersion;
    private int _modifiedBy;
    private Date _modifiedDate;
    private int _relationshipType;
    private String _buildType;
    private BaseModel<?> _relationshipBuilderRemoteModel;

    public RelationshipBuilderClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return RelationshipBuilder.class;
    }

    @Override
    public String getModelClassName() {
        return RelationshipBuilder.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _relationshipBuilderSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setRelationshipBuilderSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _relationshipBuilderSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("startDate", getStartDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("relationshipDescription", getRelationshipDescription());
        attributes.put("hierarchyDefinitionSid", getHierarchyDefinitionSid());
        attributes.put("versionNo", getVersionNo());
        attributes.put("relationshipName", getRelationshipName());
        attributes.put("relationshipBuilderSid", getRelationshipBuilderSid());
        attributes.put("hierarchyVersion", getHierarchyVersion());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("relationshipType", getRelationshipType());
        attributes.put("buildType", getBuildType());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date startDate = (Date) attributes.get("startDate");

        if (startDate != null) {
            setStartDate(startDate);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String relationshipDescription = (String) attributes.get(
                "relationshipDescription");

        if (relationshipDescription != null) {
            setRelationshipDescription(relationshipDescription);
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

        String relationshipName = (String) attributes.get("relationshipName");

        if (relationshipName != null) {
            setRelationshipName(relationshipName);
        }

        Integer relationshipBuilderSid = (Integer) attributes.get(
                "relationshipBuilderSid");

        if (relationshipBuilderSid != null) {
            setRelationshipBuilderSid(relationshipBuilderSid);
        }

        Integer hierarchyVersion = (Integer) attributes.get("hierarchyVersion");

        if (hierarchyVersion != null) {
            setHierarchyVersion(hierarchyVersion);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer relationshipType = (Integer) attributes.get("relationshipType");

        if (relationshipType != null) {
            setRelationshipType(relationshipType);
        }

        String buildType = (String) attributes.get("buildType");

        if (buildType != null) {
            setBuildType(buildType);
        }
    }

    @Override
    public Date getStartDate() {
        return _startDate;
    }

    @Override
    public void setStartDate(Date startDate) {
        _startDate = startDate;

        if (_relationshipBuilderRemoteModel != null) {
            try {
                Class<?> clazz = _relationshipBuilderRemoteModel.getClass();

                Method method = clazz.getMethod("setStartDate", Date.class);

                method.invoke(_relationshipBuilderRemoteModel, startDate);
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

        if (_relationshipBuilderRemoteModel != null) {
            try {
                Class<?> clazz = _relationshipBuilderRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_relationshipBuilderRemoteModel, createdDate);
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

        if (_relationshipBuilderRemoteModel != null) {
            try {
                Class<?> clazz = _relationshipBuilderRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_relationshipBuilderRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRelationshipDescription() {
        return _relationshipDescription;
    }

    @Override
    public void setRelationshipDescription(String relationshipDescription) {
        _relationshipDescription = relationshipDescription;

        if (_relationshipBuilderRemoteModel != null) {
            try {
                Class<?> clazz = _relationshipBuilderRemoteModel.getClass();

                Method method = clazz.getMethod("setRelationshipDescription",
                        String.class);

                method.invoke(_relationshipBuilderRemoteModel,
                    relationshipDescription);
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

        if (_relationshipBuilderRemoteModel != null) {
            try {
                Class<?> clazz = _relationshipBuilderRemoteModel.getClass();

                Method method = clazz.getMethod("setHierarchyDefinitionSid",
                        int.class);

                method.invoke(_relationshipBuilderRemoteModel,
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

        if (_relationshipBuilderRemoteModel != null) {
            try {
                Class<?> clazz = _relationshipBuilderRemoteModel.getClass();

                Method method = clazz.getMethod("setVersionNo", int.class);

                method.invoke(_relationshipBuilderRemoteModel, versionNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRelationshipName() {
        return _relationshipName;
    }

    @Override
    public void setRelationshipName(String relationshipName) {
        _relationshipName = relationshipName;

        if (_relationshipBuilderRemoteModel != null) {
            try {
                Class<?> clazz = _relationshipBuilderRemoteModel.getClass();

                Method method = clazz.getMethod("setRelationshipName",
                        String.class);

                method.invoke(_relationshipBuilderRemoteModel, relationshipName);
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

        if (_relationshipBuilderRemoteModel != null) {
            try {
                Class<?> clazz = _relationshipBuilderRemoteModel.getClass();

                Method method = clazz.getMethod("setRelationshipBuilderSid",
                        int.class);

                method.invoke(_relationshipBuilderRemoteModel,
                    relationshipBuilderSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getHierarchyVersion() {
        return _hierarchyVersion;
    }

    @Override
    public void setHierarchyVersion(int hierarchyVersion) {
        _hierarchyVersion = hierarchyVersion;

        if (_relationshipBuilderRemoteModel != null) {
            try {
                Class<?> clazz = _relationshipBuilderRemoteModel.getClass();

                Method method = clazz.getMethod("setHierarchyVersion", int.class);

                method.invoke(_relationshipBuilderRemoteModel, hierarchyVersion);
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

        if (_relationshipBuilderRemoteModel != null) {
            try {
                Class<?> clazz = _relationshipBuilderRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_relationshipBuilderRemoteModel, modifiedBy);
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

        if (_relationshipBuilderRemoteModel != null) {
            try {
                Class<?> clazz = _relationshipBuilderRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_relationshipBuilderRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRelationshipType() {
        return _relationshipType;
    }

    @Override
    public void setRelationshipType(int relationshipType) {
        _relationshipType = relationshipType;

        if (_relationshipBuilderRemoteModel != null) {
            try {
                Class<?> clazz = _relationshipBuilderRemoteModel.getClass();

                Method method = clazz.getMethod("setRelationshipType", int.class);

                method.invoke(_relationshipBuilderRemoteModel, relationshipType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBuildType() {
        return _buildType;
    }

    @Override
    public void setBuildType(String buildType) {
        _buildType = buildType;

        if (_relationshipBuilderRemoteModel != null) {
            try {
                Class<?> clazz = _relationshipBuilderRemoteModel.getClass();

                Method method = clazz.getMethod("setBuildType", String.class);

                method.invoke(_relationshipBuilderRemoteModel, buildType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getRelationshipBuilderRemoteModel() {
        return _relationshipBuilderRemoteModel;
    }

    public void setRelationshipBuilderRemoteModel(
        BaseModel<?> relationshipBuilderRemoteModel) {
        _relationshipBuilderRemoteModel = relationshipBuilderRemoteModel;
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

        Class<?> remoteModelClass = _relationshipBuilderRemoteModel.getClass();

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

        Object returnValue = method.invoke(_relationshipBuilderRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            RelationshipBuilderLocalServiceUtil.addRelationshipBuilder(this);
        } else {
            RelationshipBuilderLocalServiceUtil.updateRelationshipBuilder(this);
        }
    }

    @Override
    public RelationshipBuilder toEscapedModel() {
        return (RelationshipBuilder) ProxyUtil.newProxyInstance(RelationshipBuilder.class.getClassLoader(),
            new Class[] { RelationshipBuilder.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        RelationshipBuilderClp clone = new RelationshipBuilderClp();

        clone.setStartDate(getStartDate());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setRelationshipDescription(getRelationshipDescription());
        clone.setHierarchyDefinitionSid(getHierarchyDefinitionSid());
        clone.setVersionNo(getVersionNo());
        clone.setRelationshipName(getRelationshipName());
        clone.setRelationshipBuilderSid(getRelationshipBuilderSid());
        clone.setHierarchyVersion(getHierarchyVersion());
        clone.setModifiedBy(getModifiedBy());
        clone.setModifiedDate(getModifiedDate());
        clone.setRelationshipType(getRelationshipType());
        clone.setBuildType(getBuildType());

        return clone;
    }

    @Override
    public int compareTo(RelationshipBuilder relationshipBuilder) {
        int primaryKey = relationshipBuilder.getPrimaryKey();

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

        if (!(obj instanceof RelationshipBuilderClp)) {
            return false;
        }

        RelationshipBuilderClp relationshipBuilder = (RelationshipBuilderClp) obj;

        int primaryKey = relationshipBuilder.getPrimaryKey();

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

        sb.append("{startDate=");
        sb.append(getStartDate());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", relationshipDescription=");
        sb.append(getRelationshipDescription());
        sb.append(", hierarchyDefinitionSid=");
        sb.append(getHierarchyDefinitionSid());
        sb.append(", versionNo=");
        sb.append(getVersionNo());
        sb.append(", relationshipName=");
        sb.append(getRelationshipName());
        sb.append(", relationshipBuilderSid=");
        sb.append(getRelationshipBuilderSid());
        sb.append(", hierarchyVersion=");
        sb.append(getHierarchyVersion());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", relationshipType=");
        sb.append(getRelationshipType());
        sb.append(", buildType=");
        sb.append(getBuildType());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(43);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.RelationshipBuilder");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>startDate</column-name><column-value><![CDATA[");
        sb.append(getStartDate());
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
            "<column><column-name>relationshipDescription</column-name><column-value><![CDATA[");
        sb.append(getRelationshipDescription());
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
            "<column><column-name>relationshipName</column-name><column-value><![CDATA[");
        sb.append(getRelationshipName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>relationshipBuilderSid</column-name><column-value><![CDATA[");
        sb.append(getRelationshipBuilderSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>hierarchyVersion</column-name><column-value><![CDATA[");
        sb.append(getHierarchyVersion());
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
            "<column><column-name>relationshipType</column-name><column-value><![CDATA[");
        sb.append(getRelationshipType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>buildType</column-name><column-value><![CDATA[");
        sb.append(getBuildType());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
