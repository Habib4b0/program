package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.HierarchyLevelValuesLocalServiceUtil;

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


public class HierarchyLevelValuesClp extends BaseModelImpl<HierarchyLevelValues>
    implements HierarchyLevelValues {
    private String _levelValues;
    private int _hierarchyLevelValuesSid;
    private Date _createdDate;
    private int _createdBy;
    private int _hierarchyLevelDefinitionSid;
    private int _versionNo;
    private int _modifiedBy;
    private Date _modifiedDate;
    private BaseModel<?> _hierarchyLevelValuesRemoteModel;

    public HierarchyLevelValuesClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return HierarchyLevelValues.class;
    }

    @Override
    public String getModelClassName() {
        return HierarchyLevelValues.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _hierarchyLevelValuesSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setHierarchyLevelValuesSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _hierarchyLevelValuesSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("levelValues", getLevelValues());
        attributes.put("hierarchyLevelValuesSid", getHierarchyLevelValuesSid());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
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

        if (_hierarchyLevelValuesRemoteModel != null) {
            try {
                Class<?> clazz = _hierarchyLevelValuesRemoteModel.getClass();

                Method method = clazz.getMethod("setLevelValues", String.class);

                method.invoke(_hierarchyLevelValuesRemoteModel, levelValues);
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

        if (_hierarchyLevelValuesRemoteModel != null) {
            try {
                Class<?> clazz = _hierarchyLevelValuesRemoteModel.getClass();

                Method method = clazz.getMethod("setHierarchyLevelValuesSid",
                        int.class);

                method.invoke(_hierarchyLevelValuesRemoteModel,
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

        if (_hierarchyLevelValuesRemoteModel != null) {
            try {
                Class<?> clazz = _hierarchyLevelValuesRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_hierarchyLevelValuesRemoteModel, createdDate);
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

        if (_hierarchyLevelValuesRemoteModel != null) {
            try {
                Class<?> clazz = _hierarchyLevelValuesRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_hierarchyLevelValuesRemoteModel, createdBy);
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

        if (_hierarchyLevelValuesRemoteModel != null) {
            try {
                Class<?> clazz = _hierarchyLevelValuesRemoteModel.getClass();

                Method method = clazz.getMethod("setHierarchyLevelDefinitionSid",
                        int.class);

                method.invoke(_hierarchyLevelValuesRemoteModel,
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

        if (_hierarchyLevelValuesRemoteModel != null) {
            try {
                Class<?> clazz = _hierarchyLevelValuesRemoteModel.getClass();

                Method method = clazz.getMethod("setVersionNo", int.class);

                method.invoke(_hierarchyLevelValuesRemoteModel, versionNo);
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

        if (_hierarchyLevelValuesRemoteModel != null) {
            try {
                Class<?> clazz = _hierarchyLevelValuesRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_hierarchyLevelValuesRemoteModel, modifiedBy);
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

        if (_hierarchyLevelValuesRemoteModel != null) {
            try {
                Class<?> clazz = _hierarchyLevelValuesRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_hierarchyLevelValuesRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getHierarchyLevelValuesRemoteModel() {
        return _hierarchyLevelValuesRemoteModel;
    }

    public void setHierarchyLevelValuesRemoteModel(
        BaseModel<?> hierarchyLevelValuesRemoteModel) {
        _hierarchyLevelValuesRemoteModel = hierarchyLevelValuesRemoteModel;
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

        Class<?> remoteModelClass = _hierarchyLevelValuesRemoteModel.getClass();

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

        Object returnValue = method.invoke(_hierarchyLevelValuesRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            HierarchyLevelValuesLocalServiceUtil.addHierarchyLevelValues(this);
        } else {
            HierarchyLevelValuesLocalServiceUtil.updateHierarchyLevelValues(this);
        }
    }

    @Override
    public HierarchyLevelValues toEscapedModel() {
        return (HierarchyLevelValues) ProxyUtil.newProxyInstance(HierarchyLevelValues.class.getClassLoader(),
            new Class[] { HierarchyLevelValues.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        HierarchyLevelValuesClp clone = new HierarchyLevelValuesClp();

        clone.setLevelValues(getLevelValues());
        clone.setHierarchyLevelValuesSid(getHierarchyLevelValuesSid());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setHierarchyLevelDefinitionSid(getHierarchyLevelDefinitionSid());
        clone.setVersionNo(getVersionNo());
        clone.setModifiedBy(getModifiedBy());
        clone.setModifiedDate(getModifiedDate());

        return clone;
    }

    @Override
    public int compareTo(HierarchyLevelValues hierarchyLevelValues) {
        int primaryKey = hierarchyLevelValues.getPrimaryKey();

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

        if (!(obj instanceof HierarchyLevelValuesClp)) {
            return false;
        }

        HierarchyLevelValuesClp hierarchyLevelValues = (HierarchyLevelValuesClp) obj;

        int primaryKey = hierarchyLevelValues.getPrimaryKey();

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
        StringBundler sb = new StringBundler(17);

        sb.append("{levelValues=");
        sb.append(getLevelValues());
        sb.append(", hierarchyLevelValuesSid=");
        sb.append(getHierarchyLevelValuesSid());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
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
        StringBundler sb = new StringBundler(28);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.HierarchyLevelValues");
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
