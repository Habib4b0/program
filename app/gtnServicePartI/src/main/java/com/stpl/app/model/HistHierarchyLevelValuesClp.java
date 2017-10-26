package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.HistHierarchyLevelValuesLocalServiceUtil;
import com.stpl.app.service.persistence.HistHierarchyLevelValuesPK;

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


public class HistHierarchyLevelValuesClp extends BaseModelImpl<HistHierarchyLevelValues>
    implements HistHierarchyLevelValues {
    private String _levelValues;
    private int _hierarchyLevelValuesSid;
    private Date _createdDate;
    private int _createdBy;
    private Date _actionDate;
    private String _actionFlag;
    private int _hierarchyLevelDefinitionSid;
    private int _versionNo;
    private int _modifiedBy;
    private Date _modifiedDate;
    private BaseModel<?> _histHierarchyLevelValuesRemoteModel;

    public HistHierarchyLevelValuesClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return HistHierarchyLevelValues.class;
    }

    @Override
    public String getModelClassName() {
        return HistHierarchyLevelValues.class.getName();
    }

    @Override
    public HistHierarchyLevelValuesPK getPrimaryKey() {
        return new HistHierarchyLevelValuesPK(_hierarchyLevelValuesSid,
            _actionFlag, _versionNo);
    }

    @Override
    public void setPrimaryKey(HistHierarchyLevelValuesPK primaryKey) {
        setHierarchyLevelValuesSid(primaryKey.hierarchyLevelValuesSid);
        setActionFlag(primaryKey.actionFlag);
        setVersionNo(primaryKey.versionNo);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new HistHierarchyLevelValuesPK(_hierarchyLevelValuesSid,
            _actionFlag, _versionNo);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((HistHierarchyLevelValuesPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("levelValues", getLevelValues());
        attributes.put("hierarchyLevelValuesSid", getHierarchyLevelValuesSid());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("actionDate", getActionDate());
        attributes.put("actionFlag", getActionFlag());
        attributes.put("hierarchyLevelDefinitionSid",
            getHierarchyLevelDefinitionSid());
        attributes.put("versionNo", getVersionNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("modifiedDate", getModifiedDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String levelValues = (String) attributes.get("levelValues");

        if (levelValues != null) {
            setLevelValues(levelValues);
        }

        Integer hierarchyLevelValuesSid = (Integer) attributes.get(
                "hierarchyLevelValuesSid");

        if (hierarchyLevelValuesSid != null) {
            setHierarchyLevelValuesSid(hierarchyLevelValuesSid);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
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

        Integer hierarchyLevelDefinitionSid = (Integer) attributes.get(
                "hierarchyLevelDefinitionSid");

        if (hierarchyLevelDefinitionSid != null) {
            setHierarchyLevelDefinitionSid(hierarchyLevelDefinitionSid);
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
    }

    @Override
    public String getLevelValues() {
        return _levelValues;
    }

    @Override
    public void setLevelValues(String levelValues) {
        _levelValues = levelValues;

        if (_histHierarchyLevelValuesRemoteModel != null) {
            try {
                Class<?> clazz = _histHierarchyLevelValuesRemoteModel.getClass();

                Method method = clazz.getMethod("setLevelValues", String.class);

                method.invoke(_histHierarchyLevelValuesRemoteModel, levelValues);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getHierarchyLevelValuesSid() {
        return _hierarchyLevelValuesSid;
    }

    @Override
    public void setHierarchyLevelValuesSid(int hierarchyLevelValuesSid) {
        _hierarchyLevelValuesSid = hierarchyLevelValuesSid;

        if (_histHierarchyLevelValuesRemoteModel != null) {
            try {
                Class<?> clazz = _histHierarchyLevelValuesRemoteModel.getClass();

                Method method = clazz.getMethod("setHierarchyLevelValuesSid",
                        int.class);

                method.invoke(_histHierarchyLevelValuesRemoteModel,
                    hierarchyLevelValuesSid);
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

        if (_histHierarchyLevelValuesRemoteModel != null) {
            try {
                Class<?> clazz = _histHierarchyLevelValuesRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_histHierarchyLevelValuesRemoteModel, createdDate);
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

        if (_histHierarchyLevelValuesRemoteModel != null) {
            try {
                Class<?> clazz = _histHierarchyLevelValuesRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_histHierarchyLevelValuesRemoteModel, createdBy);
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

        if (_histHierarchyLevelValuesRemoteModel != null) {
            try {
                Class<?> clazz = _histHierarchyLevelValuesRemoteModel.getClass();

                Method method = clazz.getMethod("setActionDate", Date.class);

                method.invoke(_histHierarchyLevelValuesRemoteModel, actionDate);
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

        if (_histHierarchyLevelValuesRemoteModel != null) {
            try {
                Class<?> clazz = _histHierarchyLevelValuesRemoteModel.getClass();

                Method method = clazz.getMethod("setActionFlag", String.class);

                method.invoke(_histHierarchyLevelValuesRemoteModel, actionFlag);
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

        if (_histHierarchyLevelValuesRemoteModel != null) {
            try {
                Class<?> clazz = _histHierarchyLevelValuesRemoteModel.getClass();

                Method method = clazz.getMethod("setHierarchyLevelDefinitionSid",
                        int.class);

                method.invoke(_histHierarchyLevelValuesRemoteModel,
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

        if (_histHierarchyLevelValuesRemoteModel != null) {
            try {
                Class<?> clazz = _histHierarchyLevelValuesRemoteModel.getClass();

                Method method = clazz.getMethod("setVersionNo", int.class);

                method.invoke(_histHierarchyLevelValuesRemoteModel, versionNo);
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

        if (_histHierarchyLevelValuesRemoteModel != null) {
            try {
                Class<?> clazz = _histHierarchyLevelValuesRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_histHierarchyLevelValuesRemoteModel, modifiedBy);
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

        if (_histHierarchyLevelValuesRemoteModel != null) {
            try {
                Class<?> clazz = _histHierarchyLevelValuesRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_histHierarchyLevelValuesRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getHistHierarchyLevelValuesRemoteModel() {
        return _histHierarchyLevelValuesRemoteModel;
    }

    public void setHistHierarchyLevelValuesRemoteModel(
        BaseModel<?> histHierarchyLevelValuesRemoteModel) {
        _histHierarchyLevelValuesRemoteModel = histHierarchyLevelValuesRemoteModel;
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

        Class<?> remoteModelClass = _histHierarchyLevelValuesRemoteModel.getClass();

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

        Object returnValue = method.invoke(_histHierarchyLevelValuesRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            HistHierarchyLevelValuesLocalServiceUtil.addHistHierarchyLevelValues(this);
        } else {
            HistHierarchyLevelValuesLocalServiceUtil.updateHistHierarchyLevelValues(this);
        }
    }

    @Override
    public HistHierarchyLevelValues toEscapedModel() {
        return (HistHierarchyLevelValues) ProxyUtil.newProxyInstance(HistHierarchyLevelValues.class.getClassLoader(),
            new Class[] { HistHierarchyLevelValues.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        HistHierarchyLevelValuesClp clone = new HistHierarchyLevelValuesClp();

        clone.setLevelValues(getLevelValues());
        clone.setHierarchyLevelValuesSid(getHierarchyLevelValuesSid());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setActionDate(getActionDate());
        clone.setActionFlag(getActionFlag());
        clone.setHierarchyLevelDefinitionSid(getHierarchyLevelDefinitionSid());
        clone.setVersionNo(getVersionNo());
        clone.setModifiedBy(getModifiedBy());
        clone.setModifiedDate(getModifiedDate());

        return clone;
    }

    @Override
    public int compareTo(HistHierarchyLevelValues histHierarchyLevelValues) {
        HistHierarchyLevelValuesPK primaryKey = histHierarchyLevelValues.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof HistHierarchyLevelValuesClp)) {
            return false;
        }

        HistHierarchyLevelValuesClp histHierarchyLevelValues = (HistHierarchyLevelValuesClp) obj;

        HistHierarchyLevelValuesPK primaryKey = histHierarchyLevelValues.getPrimaryKey();

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
        StringBundler sb = new StringBundler(21);

        sb.append("{levelValues=");
        sb.append(getLevelValues());
        sb.append(", hierarchyLevelValuesSid=");
        sb.append(getHierarchyLevelValuesSid());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", actionDate=");
        sb.append(getActionDate());
        sb.append(", actionFlag=");
        sb.append(getActionFlag());
        sb.append(", hierarchyLevelDefinitionSid=");
        sb.append(getHierarchyLevelDefinitionSid());
        sb.append(", versionNo=");
        sb.append(getVersionNo());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(34);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.HistHierarchyLevelValues");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>levelValues</column-name><column-value><![CDATA[");
        sb.append(getLevelValues());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>hierarchyLevelValuesSid</column-name><column-value><![CDATA[");
        sb.append(getHierarchyLevelValuesSid());
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
            "<column><column-name>actionDate</column-name><column-value><![CDATA[");
        sb.append(getActionDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actionFlag</column-name><column-value><![CDATA[");
        sb.append(getActionFlag());
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
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
