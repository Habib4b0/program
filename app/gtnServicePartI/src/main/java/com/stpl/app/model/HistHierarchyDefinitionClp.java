package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.HistHierarchyDefinitionLocalServiceUtil;
import com.stpl.app.service.persistence.HistHierarchyDefinitionPK;

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


public class HistHierarchyDefinitionClp extends BaseModelImpl<HistHierarchyDefinition>
    implements HistHierarchyDefinition {
    private int _noOfLevels;
    private int _createdBy;
    private Date _actionDate;
    private String _actionFlag;
    private int _modifiedBy;
    private int _hierarchyDefinitionSid;
    private Date _createdDate;
    private int _hierarchyType;
    private int _hierarchyCategory;
    private String _hierarchyName;
    private int _versionNo;
    private Date _modifiedDate;
    private BaseModel<?> _histHierarchyDefinitionRemoteModel;

    public HistHierarchyDefinitionClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return HistHierarchyDefinition.class;
    }

    @Override
    public String getModelClassName() {
        return HistHierarchyDefinition.class.getName();
    }

    @Override
    public HistHierarchyDefinitionPK getPrimaryKey() {
        return new HistHierarchyDefinitionPK(_actionFlag,
            _hierarchyDefinitionSid, _versionNo);
    }

    @Override
    public void setPrimaryKey(HistHierarchyDefinitionPK primaryKey) {
        setActionFlag(primaryKey.actionFlag);
        setHierarchyDefinitionSid(primaryKey.hierarchyDefinitionSid);
        setVersionNo(primaryKey.versionNo);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new HistHierarchyDefinitionPK(_actionFlag,
            _hierarchyDefinitionSid, _versionNo);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((HistHierarchyDefinitionPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("noOfLevels", getNoOfLevels());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("actionDate", getActionDate());
        attributes.put("actionFlag", getActionFlag());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("hierarchyDefinitionSid", getHierarchyDefinitionSid());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("hierarchyType", getHierarchyType());
        attributes.put("hierarchyCategory", getHierarchyCategory());
        attributes.put("hierarchyName", getHierarchyName());
        attributes.put("versionNo", getVersionNo());
        attributes.put("modifiedDate", getModifiedDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer noOfLevels = (Integer) attributes.get("noOfLevels");

        if (noOfLevels != null) {
            setNoOfLevels(noOfLevels);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date actionDate = (Date) attributes.get("actionDate");

        if (actionDate != null) {
            setActionDate(actionDate);
        }

        String actionFlag = (String) attributes.get("actionFlag");

        if (actionFlag != null) {
            setActionFlag(actionFlag);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Integer hierarchyDefinitionSid = (Integer) attributes.get(
                "hierarchyDefinitionSid");

        if (hierarchyDefinitionSid != null) {
            setHierarchyDefinitionSid(hierarchyDefinitionSid);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer hierarchyType = (Integer) attributes.get("hierarchyType");

        if (hierarchyType != null) {
            setHierarchyType(hierarchyType);
        }

        Integer hierarchyCategory = (Integer) attributes.get(
                "hierarchyCategory");

        if (hierarchyCategory != null) {
            setHierarchyCategory(hierarchyCategory);
        }

        String hierarchyName = (String) attributes.get("hierarchyName");

        if (hierarchyName != null) {
            setHierarchyName(hierarchyName);
        }

        Integer versionNo = (Integer) attributes.get("versionNo");

        if (versionNo != null) {
            setVersionNo(versionNo);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }
    }

    @Override
    public int getNoOfLevels() {
        return _noOfLevels;
    }

    @Override
    public void setNoOfLevels(int noOfLevels) {
        _noOfLevels = noOfLevels;

        if (_histHierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _histHierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setNoOfLevels", int.class);

                method.invoke(_histHierarchyDefinitionRemoteModel, noOfLevels);
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

        if (_histHierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _histHierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_histHierarchyDefinitionRemoteModel, createdBy);
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

        if (_histHierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _histHierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setActionDate", Date.class);

                method.invoke(_histHierarchyDefinitionRemoteModel, actionDate);
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

        if (_histHierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _histHierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setActionFlag", String.class);

                method.invoke(_histHierarchyDefinitionRemoteModel, actionFlag);
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

        if (_histHierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _histHierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_histHierarchyDefinitionRemoteModel, modifiedBy);
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

        if (_histHierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _histHierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setHierarchyDefinitionSid",
                        int.class);

                method.invoke(_histHierarchyDefinitionRemoteModel,
                    hierarchyDefinitionSid);
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

        if (_histHierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _histHierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_histHierarchyDefinitionRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getHierarchyType() {
        return _hierarchyType;
    }

    @Override
    public void setHierarchyType(int hierarchyType) {
        _hierarchyType = hierarchyType;

        if (_histHierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _histHierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setHierarchyType", int.class);

                method.invoke(_histHierarchyDefinitionRemoteModel, hierarchyType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getHierarchyCategory() {
        return _hierarchyCategory;
    }

    @Override
    public void setHierarchyCategory(int hierarchyCategory) {
        _hierarchyCategory = hierarchyCategory;

        if (_histHierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _histHierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setHierarchyCategory",
                        int.class);

                method.invoke(_histHierarchyDefinitionRemoteModel,
                    hierarchyCategory);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getHierarchyName() {
        return _hierarchyName;
    }

    @Override
    public void setHierarchyName(String hierarchyName) {
        _hierarchyName = hierarchyName;

        if (_histHierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _histHierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setHierarchyName", String.class);

                method.invoke(_histHierarchyDefinitionRemoteModel, hierarchyName);
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

        if (_histHierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _histHierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setVersionNo", int.class);

                method.invoke(_histHierarchyDefinitionRemoteModel, versionNo);
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

        if (_histHierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _histHierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_histHierarchyDefinitionRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getHistHierarchyDefinitionRemoteModel() {
        return _histHierarchyDefinitionRemoteModel;
    }

    public void setHistHierarchyDefinitionRemoteModel(
        BaseModel<?> histHierarchyDefinitionRemoteModel) {
        _histHierarchyDefinitionRemoteModel = histHierarchyDefinitionRemoteModel;
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

        Class<?> remoteModelClass = _histHierarchyDefinitionRemoteModel.getClass();

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

        Object returnValue = method.invoke(_histHierarchyDefinitionRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            HistHierarchyDefinitionLocalServiceUtil.addHistHierarchyDefinition(this);
        } else {
            HistHierarchyDefinitionLocalServiceUtil.updateHistHierarchyDefinition(this);
        }
    }

    @Override
    public HistHierarchyDefinition toEscapedModel() {
        return (HistHierarchyDefinition) ProxyUtil.newProxyInstance(HistHierarchyDefinition.class.getClassLoader(),
            new Class[] { HistHierarchyDefinition.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        HistHierarchyDefinitionClp clone = new HistHierarchyDefinitionClp();

        clone.setNoOfLevels(getNoOfLevels());
        clone.setCreatedBy(getCreatedBy());
        clone.setActionDate(getActionDate());
        clone.setActionFlag(getActionFlag());
        clone.setModifiedBy(getModifiedBy());
        clone.setHierarchyDefinitionSid(getHierarchyDefinitionSid());
        clone.setCreatedDate(getCreatedDate());
        clone.setHierarchyType(getHierarchyType());
        clone.setHierarchyCategory(getHierarchyCategory());
        clone.setHierarchyName(getHierarchyName());
        clone.setVersionNo(getVersionNo());
        clone.setModifiedDate(getModifiedDate());

        return clone;
    }

    @Override
    public int compareTo(HistHierarchyDefinition histHierarchyDefinition) {
        HistHierarchyDefinitionPK primaryKey = histHierarchyDefinition.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof HistHierarchyDefinitionClp)) {
            return false;
        }

        HistHierarchyDefinitionClp histHierarchyDefinition = (HistHierarchyDefinitionClp) obj;

        HistHierarchyDefinitionPK primaryKey = histHierarchyDefinition.getPrimaryKey();

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
        StringBundler sb = new StringBundler(25);

        sb.append("{noOfLevels=");
        sb.append(getNoOfLevels());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", actionDate=");
        sb.append(getActionDate());
        sb.append(", actionFlag=");
        sb.append(getActionFlag());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", hierarchyDefinitionSid=");
        sb.append(getHierarchyDefinitionSid());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", hierarchyType=");
        sb.append(getHierarchyType());
        sb.append(", hierarchyCategory=");
        sb.append(getHierarchyCategory());
        sb.append(", hierarchyName=");
        sb.append(getHierarchyName());
        sb.append(", versionNo=");
        sb.append(getVersionNo());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(40);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.HistHierarchyDefinition");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>noOfLevels</column-name><column-value><![CDATA[");
        sb.append(getNoOfLevels());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
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
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>hierarchyDefinitionSid</column-name><column-value><![CDATA[");
        sb.append(getHierarchyDefinitionSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>hierarchyType</column-name><column-value><![CDATA[");
        sb.append(getHierarchyType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>hierarchyCategory</column-name><column-value><![CDATA[");
        sb.append(getHierarchyCategory());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>hierarchyName</column-name><column-value><![CDATA[");
        sb.append(getHierarchyName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>versionNo</column-name><column-value><![CDATA[");
        sb.append(getVersionNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
