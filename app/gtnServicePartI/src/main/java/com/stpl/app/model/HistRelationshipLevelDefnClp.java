package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.HistRelationshipLevelDefnLocalServiceUtil;
import com.stpl.app.service.persistence.HistRelationshipLevelDefnPK;

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


public class HistRelationshipLevelDefnClp extends BaseModelImpl<HistRelationshipLevelDefn>
    implements HistRelationshipLevelDefn {
    private String _relationshipLevelValues;
    private Date _actionDate;
    private int _hierarchyLevelDefinitionSid;
    private String _parentNode;
    private int _versionNo;
    private int _relationshipBuilderSid;
    private Date _modifiedDate;
    private int _createdBy;
    private Date _createdDate;
    private String _levelNo;
    private String _actionFlag;
    private String _hierarchyNo;
    private int _modifiedBy;
    private int _relationshipLevelSid;
    private String _flag;
    private String _levelName;
    private BaseModel<?> _histRelationshipLevelDefnRemoteModel;

    public HistRelationshipLevelDefnClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return HistRelationshipLevelDefn.class;
    }

    @Override
    public String getModelClassName() {
        return HistRelationshipLevelDefn.class.getName();
    }

    @Override
    public HistRelationshipLevelDefnPK getPrimaryKey() {
        return new HistRelationshipLevelDefnPK(_versionNo, _actionFlag,
            _relationshipLevelSid);
    }

    @Override
    public void setPrimaryKey(HistRelationshipLevelDefnPK primaryKey) {
        setVersionNo(primaryKey.versionNo);
        setActionFlag(primaryKey.actionFlag);
        setRelationshipLevelSid(primaryKey.relationshipLevelSid);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new HistRelationshipLevelDefnPK(_versionNo, _actionFlag,
            _relationshipLevelSid);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((HistRelationshipLevelDefnPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("relationshipLevelValues", getRelationshipLevelValues());
        attributes.put("actionDate", getActionDate());
        attributes.put("hierarchyLevelDefinitionSid",
            getHierarchyLevelDefinitionSid());
        attributes.put("parentNode", getParentNode());
        attributes.put("versionNo", getVersionNo());
        attributes.put("relationshipBuilderSid", getRelationshipBuilderSid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("levelNo", getLevelNo());
        attributes.put("actionFlag", getActionFlag());
        attributes.put("hierarchyNo", getHierarchyNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("relationshipLevelSid", getRelationshipLevelSid());
        attributes.put("flag", getFlag());
        attributes.put("levelName", getLevelName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String relationshipLevelValues = (String) attributes.get(
                "relationshipLevelValues");

        if (relationshipLevelValues != null) {
            setRelationshipLevelValues(relationshipLevelValues);
        }

        Date actionDate = (Date) attributes.get("actionDate");

        if (actionDate != null) {
            setActionDate(actionDate);
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

        String actionFlag = (String) attributes.get("actionFlag");

        if (actionFlag != null) {
            setActionFlag(actionFlag);
        }

        String hierarchyNo = (String) attributes.get("hierarchyNo");

        if (hierarchyNo != null) {
            setHierarchyNo(hierarchyNo);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
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
    }

    @Override
    public String getRelationshipLevelValues() {
        return _relationshipLevelValues;
    }

    @Override
    public void setRelationshipLevelValues(String relationshipLevelValues) {
        _relationshipLevelValues = relationshipLevelValues;

        if (_histRelationshipLevelDefnRemoteModel != null) {
            try {
                Class<?> clazz = _histRelationshipLevelDefnRemoteModel.getClass();

                Method method = clazz.getMethod("setRelationshipLevelValues",
                        String.class);

                method.invoke(_histRelationshipLevelDefnRemoteModel,
                    relationshipLevelValues);
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

        if (_histRelationshipLevelDefnRemoteModel != null) {
            try {
                Class<?> clazz = _histRelationshipLevelDefnRemoteModel.getClass();

                Method method = clazz.getMethod("setActionDate", Date.class);

                method.invoke(_histRelationshipLevelDefnRemoteModel, actionDate);
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

        if (_histRelationshipLevelDefnRemoteModel != null) {
            try {
                Class<?> clazz = _histRelationshipLevelDefnRemoteModel.getClass();

                Method method = clazz.getMethod("setHierarchyLevelDefinitionSid",
                        int.class);

                method.invoke(_histRelationshipLevelDefnRemoteModel,
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

        if (_histRelationshipLevelDefnRemoteModel != null) {
            try {
                Class<?> clazz = _histRelationshipLevelDefnRemoteModel.getClass();

                Method method = clazz.getMethod("setParentNode", String.class);

                method.invoke(_histRelationshipLevelDefnRemoteModel, parentNode);
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

        if (_histRelationshipLevelDefnRemoteModel != null) {
            try {
                Class<?> clazz = _histRelationshipLevelDefnRemoteModel.getClass();

                Method method = clazz.getMethod("setVersionNo", int.class);

                method.invoke(_histRelationshipLevelDefnRemoteModel, versionNo);
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

        if (_histRelationshipLevelDefnRemoteModel != null) {
            try {
                Class<?> clazz = _histRelationshipLevelDefnRemoteModel.getClass();

                Method method = clazz.getMethod("setRelationshipBuilderSid",
                        int.class);

                method.invoke(_histRelationshipLevelDefnRemoteModel,
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

        if (_histRelationshipLevelDefnRemoteModel != null) {
            try {
                Class<?> clazz = _histRelationshipLevelDefnRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_histRelationshipLevelDefnRemoteModel,
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

        if (_histRelationshipLevelDefnRemoteModel != null) {
            try {
                Class<?> clazz = _histRelationshipLevelDefnRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_histRelationshipLevelDefnRemoteModel, createdBy);
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

        if (_histRelationshipLevelDefnRemoteModel != null) {
            try {
                Class<?> clazz = _histRelationshipLevelDefnRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_histRelationshipLevelDefnRemoteModel, createdDate);
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

        if (_histRelationshipLevelDefnRemoteModel != null) {
            try {
                Class<?> clazz = _histRelationshipLevelDefnRemoteModel.getClass();

                Method method = clazz.getMethod("setLevelNo", String.class);

                method.invoke(_histRelationshipLevelDefnRemoteModel, levelNo);
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

        if (_histRelationshipLevelDefnRemoteModel != null) {
            try {
                Class<?> clazz = _histRelationshipLevelDefnRemoteModel.getClass();

                Method method = clazz.getMethod("setActionFlag", String.class);

                method.invoke(_histRelationshipLevelDefnRemoteModel, actionFlag);
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

        if (_histRelationshipLevelDefnRemoteModel != null) {
            try {
                Class<?> clazz = _histRelationshipLevelDefnRemoteModel.getClass();

                Method method = clazz.getMethod("setHierarchyNo", String.class);

                method.invoke(_histRelationshipLevelDefnRemoteModel, hierarchyNo);
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

        if (_histRelationshipLevelDefnRemoteModel != null) {
            try {
                Class<?> clazz = _histRelationshipLevelDefnRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_histRelationshipLevelDefnRemoteModel, modifiedBy);
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

        if (_histRelationshipLevelDefnRemoteModel != null) {
            try {
                Class<?> clazz = _histRelationshipLevelDefnRemoteModel.getClass();

                Method method = clazz.getMethod("setRelationshipLevelSid",
                        int.class);

                method.invoke(_histRelationshipLevelDefnRemoteModel,
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

        if (_histRelationshipLevelDefnRemoteModel != null) {
            try {
                Class<?> clazz = _histRelationshipLevelDefnRemoteModel.getClass();

                Method method = clazz.getMethod("setFlag", String.class);

                method.invoke(_histRelationshipLevelDefnRemoteModel, flag);
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

        if (_histRelationshipLevelDefnRemoteModel != null) {
            try {
                Class<?> clazz = _histRelationshipLevelDefnRemoteModel.getClass();

                Method method = clazz.getMethod("setLevelName", String.class);

                method.invoke(_histRelationshipLevelDefnRemoteModel, levelName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getHistRelationshipLevelDefnRemoteModel() {
        return _histRelationshipLevelDefnRemoteModel;
    }

    public void setHistRelationshipLevelDefnRemoteModel(
        BaseModel<?> histRelationshipLevelDefnRemoteModel) {
        _histRelationshipLevelDefnRemoteModel = histRelationshipLevelDefnRemoteModel;
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

        Class<?> remoteModelClass = _histRelationshipLevelDefnRemoteModel.getClass();

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

        Object returnValue = method.invoke(_histRelationshipLevelDefnRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            HistRelationshipLevelDefnLocalServiceUtil.addHistRelationshipLevelDefn(this);
        } else {
            HistRelationshipLevelDefnLocalServiceUtil.updateHistRelationshipLevelDefn(this);
        }
    }

    @Override
    public HistRelationshipLevelDefn toEscapedModel() {
        return (HistRelationshipLevelDefn) ProxyUtil.newProxyInstance(HistRelationshipLevelDefn.class.getClassLoader(),
            new Class[] { HistRelationshipLevelDefn.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        HistRelationshipLevelDefnClp clone = new HistRelationshipLevelDefnClp();

        clone.setRelationshipLevelValues(getRelationshipLevelValues());
        clone.setActionDate(getActionDate());
        clone.setHierarchyLevelDefinitionSid(getHierarchyLevelDefinitionSid());
        clone.setParentNode(getParentNode());
        clone.setVersionNo(getVersionNo());
        clone.setRelationshipBuilderSid(getRelationshipBuilderSid());
        clone.setModifiedDate(getModifiedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setLevelNo(getLevelNo());
        clone.setActionFlag(getActionFlag());
        clone.setHierarchyNo(getHierarchyNo());
        clone.setModifiedBy(getModifiedBy());
        clone.setRelationshipLevelSid(getRelationshipLevelSid());
        clone.setFlag(getFlag());
        clone.setLevelName(getLevelName());

        return clone;
    }

    @Override
    public int compareTo(HistRelationshipLevelDefn histRelationshipLevelDefn) {
        HistRelationshipLevelDefnPK primaryKey = histRelationshipLevelDefn.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof HistRelationshipLevelDefnClp)) {
            return false;
        }

        HistRelationshipLevelDefnClp histRelationshipLevelDefn = (HistRelationshipLevelDefnClp) obj;

        HistRelationshipLevelDefnPK primaryKey = histRelationshipLevelDefn.getPrimaryKey();

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
        StringBundler sb = new StringBundler(33);

        sb.append("{relationshipLevelValues=");
        sb.append(getRelationshipLevelValues());
        sb.append(", actionDate=");
        sb.append(getActionDate());
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
        sb.append(", actionFlag=");
        sb.append(getActionFlag());
        sb.append(", hierarchyNo=");
        sb.append(getHierarchyNo());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", relationshipLevelSid=");
        sb.append(getRelationshipLevelSid());
        sb.append(", flag=");
        sb.append(getFlag());
        sb.append(", levelName=");
        sb.append(getLevelName());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(52);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.HistRelationshipLevelDefn");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>relationshipLevelValues</column-name><column-value><![CDATA[");
        sb.append(getRelationshipLevelValues());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actionDate</column-name><column-value><![CDATA[");
        sb.append(getActionDate());
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
            "<column><column-name>actionFlag</column-name><column-value><![CDATA[");
        sb.append(getActionFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>hierarchyNo</column-name><column-value><![CDATA[");
        sb.append(getHierarchyNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
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

        sb.append("</model>");

        return sb.toString();
    }
}
