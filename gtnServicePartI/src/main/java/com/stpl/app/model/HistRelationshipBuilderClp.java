package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.HistRelationshipBuilderLocalServiceUtil;
import com.stpl.app.service.persistence.HistRelationshipBuilderPK;

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


public class HistRelationshipBuilderClp extends BaseModelImpl<HistRelationshipBuilder>
    implements HistRelationshipBuilder {
    private Date _startDate;
    private Date _createdDate;
    private int _createdBy;
    private String _relationshipDescription;
    private Date _actionDate;
    private String _actionFlag;
    private int _hierarchyDefinitionSid;
    private int _versionNo;
    private String _relationshipName;
    private int _relationshipBuilderSid;
    private int _hierarchyVersion;
    private int _modifiedBy;
    private Date _modifiedDate;
    private int _relationshipType;
    private String _buildType;
    private BaseModel<?> _histRelationshipBuilderRemoteModel;

    public HistRelationshipBuilderClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return HistRelationshipBuilder.class;
    }

    @Override
    public String getModelClassName() {
        return HistRelationshipBuilder.class.getName();
    }

    @Override
    public HistRelationshipBuilderPK getPrimaryKey() {
        return new HistRelationshipBuilderPK(_actionFlag, _versionNo,
            _relationshipBuilderSid);
    }

    @Override
    public void setPrimaryKey(HistRelationshipBuilderPK primaryKey) {
        setActionFlag(primaryKey.actionFlag);
        setVersionNo(primaryKey.versionNo);
        setRelationshipBuilderSid(primaryKey.relationshipBuilderSid);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new HistRelationshipBuilderPK(_actionFlag, _versionNo,
            _relationshipBuilderSid);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((HistRelationshipBuilderPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("startDate", getStartDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("relationshipDescription", getRelationshipDescription());
        attributes.put("actionDate", getActionDate());
        attributes.put("actionFlag", getActionFlag());
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

        Date actionDate = (Date) attributes.get("actionDate");

        if (actionDate != null) {
            setActionDate(actionDate);
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

        if (_histRelationshipBuilderRemoteModel != null) {
            try {
                Class<?> clazz = _histRelationshipBuilderRemoteModel.getClass();

                Method method = clazz.getMethod("setStartDate", Date.class);

                method.invoke(_histRelationshipBuilderRemoteModel, startDate);
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

        if (_histRelationshipBuilderRemoteModel != null) {
            try {
                Class<?> clazz = _histRelationshipBuilderRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_histRelationshipBuilderRemoteModel, createdDate);
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

        if (_histRelationshipBuilderRemoteModel != null) {
            try {
                Class<?> clazz = _histRelationshipBuilderRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_histRelationshipBuilderRemoteModel, createdBy);
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

        if (_histRelationshipBuilderRemoteModel != null) {
            try {
                Class<?> clazz = _histRelationshipBuilderRemoteModel.getClass();

                Method method = clazz.getMethod("setRelationshipDescription",
                        String.class);

                method.invoke(_histRelationshipBuilderRemoteModel,
                    relationshipDescription);
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

        if (_histRelationshipBuilderRemoteModel != null) {
            try {
                Class<?> clazz = _histRelationshipBuilderRemoteModel.getClass();

                Method method = clazz.getMethod("setActionDate", Date.class);

                method.invoke(_histRelationshipBuilderRemoteModel, actionDate);
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

        if (_histRelationshipBuilderRemoteModel != null) {
            try {
                Class<?> clazz = _histRelationshipBuilderRemoteModel.getClass();

                Method method = clazz.getMethod("setActionFlag", String.class);

                method.invoke(_histRelationshipBuilderRemoteModel, actionFlag);
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

        if (_histRelationshipBuilderRemoteModel != null) {
            try {
                Class<?> clazz = _histRelationshipBuilderRemoteModel.getClass();

                Method method = clazz.getMethod("setHierarchyDefinitionSid",
                        int.class);

                method.invoke(_histRelationshipBuilderRemoteModel,
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

        if (_histRelationshipBuilderRemoteModel != null) {
            try {
                Class<?> clazz = _histRelationshipBuilderRemoteModel.getClass();

                Method method = clazz.getMethod("setVersionNo", int.class);

                method.invoke(_histRelationshipBuilderRemoteModel, versionNo);
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

        if (_histRelationshipBuilderRemoteModel != null) {
            try {
                Class<?> clazz = _histRelationshipBuilderRemoteModel.getClass();

                Method method = clazz.getMethod("setRelationshipName",
                        String.class);

                method.invoke(_histRelationshipBuilderRemoteModel,
                    relationshipName);
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

        if (_histRelationshipBuilderRemoteModel != null) {
            try {
                Class<?> clazz = _histRelationshipBuilderRemoteModel.getClass();

                Method method = clazz.getMethod("setRelationshipBuilderSid",
                        int.class);

                method.invoke(_histRelationshipBuilderRemoteModel,
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

        if (_histRelationshipBuilderRemoteModel != null) {
            try {
                Class<?> clazz = _histRelationshipBuilderRemoteModel.getClass();

                Method method = clazz.getMethod("setHierarchyVersion", int.class);

                method.invoke(_histRelationshipBuilderRemoteModel,
                    hierarchyVersion);
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

        if (_histRelationshipBuilderRemoteModel != null) {
            try {
                Class<?> clazz = _histRelationshipBuilderRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_histRelationshipBuilderRemoteModel, modifiedBy);
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

        if (_histRelationshipBuilderRemoteModel != null) {
            try {
                Class<?> clazz = _histRelationshipBuilderRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_histRelationshipBuilderRemoteModel, modifiedDate);
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

        if (_histRelationshipBuilderRemoteModel != null) {
            try {
                Class<?> clazz = _histRelationshipBuilderRemoteModel.getClass();

                Method method = clazz.getMethod("setRelationshipType", int.class);

                method.invoke(_histRelationshipBuilderRemoteModel,
                    relationshipType);
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

        if (_histRelationshipBuilderRemoteModel != null) {
            try {
                Class<?> clazz = _histRelationshipBuilderRemoteModel.getClass();

                Method method = clazz.getMethod("setBuildType", String.class);

                method.invoke(_histRelationshipBuilderRemoteModel, buildType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getHistRelationshipBuilderRemoteModel() {
        return _histRelationshipBuilderRemoteModel;
    }

    public void setHistRelationshipBuilderRemoteModel(
        BaseModel<?> histRelationshipBuilderRemoteModel) {
        _histRelationshipBuilderRemoteModel = histRelationshipBuilderRemoteModel;
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

        Class<?> remoteModelClass = _histRelationshipBuilderRemoteModel.getClass();

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

        Object returnValue = method.invoke(_histRelationshipBuilderRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            HistRelationshipBuilderLocalServiceUtil.addHistRelationshipBuilder(this);
        } else {
            HistRelationshipBuilderLocalServiceUtil.updateHistRelationshipBuilder(this);
        }
    }

    @Override
    public HistRelationshipBuilder toEscapedModel() {
        return (HistRelationshipBuilder) ProxyUtil.newProxyInstance(HistRelationshipBuilder.class.getClassLoader(),
            new Class[] { HistRelationshipBuilder.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        HistRelationshipBuilderClp clone = new HistRelationshipBuilderClp();

        clone.setStartDate(getStartDate());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setRelationshipDescription(getRelationshipDescription());
        clone.setActionDate(getActionDate());
        clone.setActionFlag(getActionFlag());
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
    public int compareTo(HistRelationshipBuilder histRelationshipBuilder) {
        HistRelationshipBuilderPK primaryKey = histRelationshipBuilder.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof HistRelationshipBuilderClp)) {
            return false;
        }

        HistRelationshipBuilderClp histRelationshipBuilder = (HistRelationshipBuilderClp) obj;

        HistRelationshipBuilderPK primaryKey = histRelationshipBuilder.getPrimaryKey();

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
        StringBundler sb = new StringBundler(31);

        sb.append("{startDate=");
        sb.append(getStartDate());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", relationshipDescription=");
        sb.append(getRelationshipDescription());
        sb.append(", actionDate=");
        sb.append(getActionDate());
        sb.append(", actionFlag=");
        sb.append(getActionFlag());
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
        StringBundler sb = new StringBundler(49);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.HistRelationshipBuilder");
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
            "<column><column-name>actionDate</column-name><column-value><![CDATA[");
        sb.append(getActionDate());
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
