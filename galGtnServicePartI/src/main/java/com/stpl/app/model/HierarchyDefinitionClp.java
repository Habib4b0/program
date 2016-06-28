package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.HierarchyDefinitionLocalServiceUtil;

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


public class HierarchyDefinitionClp extends BaseModelImpl<HierarchyDefinition>
    implements HierarchyDefinition {
    private Date _createdDate;
    private int _createdBy;
    private int _noOfLevels;
    private int _hierarchyType;
    private String _hierarchyName;
    private int _hierarchyDefinitionSid;
    private int _versionNo;
    private int _modifiedBy;
    private Date _modifiedDate;
    private int _hierarchyCategory;
    private BaseModel<?> _hierarchyDefinitionRemoteModel;

    public HierarchyDefinitionClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return HierarchyDefinition.class;
    }

    @Override
    public String getModelClassName() {
        return HierarchyDefinition.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _hierarchyDefinitionSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setHierarchyDefinitionSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _hierarchyDefinitionSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("noOfLevels", getNoOfLevels());
        attributes.put("hierarchyType", getHierarchyType());
        attributes.put("hierarchyName", getHierarchyName());
        attributes.put("hierarchyDefinitionSid", getHierarchyDefinitionSid());
        attributes.put("versionNo", getVersionNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("hierarchyCategory", getHierarchyCategory());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer noOfLevels = (Integer) attributes.get("noOfLevels");

        if (noOfLevels != null) {
            setNoOfLevels(noOfLevels);
        }

        Integer hierarchyType = (Integer) attributes.get("hierarchyType");

        if (hierarchyType != null) {
            setHierarchyType(hierarchyType);
        }

        String hierarchyName = (String) attributes.get("hierarchyName");

        if (hierarchyName != null) {
            setHierarchyName(hierarchyName);
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

        Integer hierarchyCategory = (Integer) attributes.get(
                "hierarchyCategory");

        if (hierarchyCategory != null) {
            setHierarchyCategory(hierarchyCategory);
        }
    }

    @Override
    public Date getCreatedDate() {
        return _createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;

        if (_hierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _hierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_hierarchyDefinitionRemoteModel, createdDate);
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

        if (_hierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _hierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_hierarchyDefinitionRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getNoOfLevels() {
        return _noOfLevels;
    }

    @Override
    public void setNoOfLevels(int noOfLevels) {
        _noOfLevels = noOfLevels;

        if (_hierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _hierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setNoOfLevels", int.class);

                method.invoke(_hierarchyDefinitionRemoteModel, noOfLevels);
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

        if (_hierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _hierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setHierarchyType", int.class);

                method.invoke(_hierarchyDefinitionRemoteModel, hierarchyType);
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

        if (_hierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _hierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setHierarchyName", String.class);

                method.invoke(_hierarchyDefinitionRemoteModel, hierarchyName);
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

        if (_hierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _hierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setHierarchyDefinitionSid",
                        int.class);

                method.invoke(_hierarchyDefinitionRemoteModel,
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

        if (_hierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _hierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setVersionNo", int.class);

                method.invoke(_hierarchyDefinitionRemoteModel, versionNo);
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

        if (_hierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _hierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_hierarchyDefinitionRemoteModel, modifiedBy);
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

        if (_hierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _hierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_hierarchyDefinitionRemoteModel, modifiedDate);
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

        if (_hierarchyDefinitionRemoteModel != null) {
            try {
                Class<?> clazz = _hierarchyDefinitionRemoteModel.getClass();

                Method method = clazz.getMethod("setHierarchyCategory",
                        int.class);

                method.invoke(_hierarchyDefinitionRemoteModel, hierarchyCategory);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getHierarchyDefinitionRemoteModel() {
        return _hierarchyDefinitionRemoteModel;
    }

    public void setHierarchyDefinitionRemoteModel(
        BaseModel<?> hierarchyDefinitionRemoteModel) {
        _hierarchyDefinitionRemoteModel = hierarchyDefinitionRemoteModel;
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

        Class<?> remoteModelClass = _hierarchyDefinitionRemoteModel.getClass();

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

        Object returnValue = method.invoke(_hierarchyDefinitionRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            HierarchyDefinitionLocalServiceUtil.addHierarchyDefinition(this);
        } else {
            HierarchyDefinitionLocalServiceUtil.updateHierarchyDefinition(this);
        }
    }

    @Override
    public HierarchyDefinition toEscapedModel() {
        return (HierarchyDefinition) ProxyUtil.newProxyInstance(HierarchyDefinition.class.getClassLoader(),
            new Class[] { HierarchyDefinition.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        HierarchyDefinitionClp clone = new HierarchyDefinitionClp();

        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setNoOfLevels(getNoOfLevels());
        clone.setHierarchyType(getHierarchyType());
        clone.setHierarchyName(getHierarchyName());
        clone.setHierarchyDefinitionSid(getHierarchyDefinitionSid());
        clone.setVersionNo(getVersionNo());
        clone.setModifiedBy(getModifiedBy());
        clone.setModifiedDate(getModifiedDate());
        clone.setHierarchyCategory(getHierarchyCategory());

        return clone;
    }

    @Override
    public int compareTo(HierarchyDefinition hierarchyDefinition) {
        int primaryKey = hierarchyDefinition.getPrimaryKey();

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

        if (!(obj instanceof HierarchyDefinitionClp)) {
            return false;
        }

        HierarchyDefinitionClp hierarchyDefinition = (HierarchyDefinitionClp) obj;

        int primaryKey = hierarchyDefinition.getPrimaryKey();

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
        StringBundler sb = new StringBundler(21);

        sb.append("{createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", noOfLevels=");
        sb.append(getNoOfLevels());
        sb.append(", hierarchyType=");
        sb.append(getHierarchyType());
        sb.append(", hierarchyName=");
        sb.append(getHierarchyName());
        sb.append(", hierarchyDefinitionSid=");
        sb.append(getHierarchyDefinitionSid());
        sb.append(", versionNo=");
        sb.append(getVersionNo());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", hierarchyCategory=");
        sb.append(getHierarchyCategory());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(34);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.HierarchyDefinition");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>noOfLevels</column-name><column-value><![CDATA[");
        sb.append(getNoOfLevels());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>hierarchyType</column-name><column-value><![CDATA[");
        sb.append(getHierarchyType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>hierarchyName</column-name><column-value><![CDATA[");
        sb.append(getHierarchyName());
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
            "<column><column-name>hierarchyCategory</column-name><column-value><![CDATA[");
        sb.append(getHierarchyCategory());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
