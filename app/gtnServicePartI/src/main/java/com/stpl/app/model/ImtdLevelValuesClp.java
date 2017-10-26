package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.ImtdLevelValuesLocalServiceUtil;

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


public class ImtdLevelValuesClp extends BaseModelImpl<ImtdLevelValues>
    implements ImtdLevelValues {
    private String _levelValues;
    private Date _createdDate;
    private int _createdBy;
    private int _imtdLevelValuesSid;
    private int _levelNo;
    private int _versionNo;
    private int _modifiedBy;
    private Date _modifiedDate;
    private String _levelName;
    private BaseModel<?> _imtdLevelValuesRemoteModel;

    public ImtdLevelValuesClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ImtdLevelValues.class;
    }

    @Override
    public String getModelClassName() {
        return ImtdLevelValues.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _imtdLevelValuesSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setImtdLevelValuesSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _imtdLevelValuesSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("levelValues", getLevelValues());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("imtdLevelValuesSid", getImtdLevelValuesSid());
        attributes.put("levelNo", getLevelNo());
        attributes.put("versionNo", getVersionNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("levelName", getLevelName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String levelValues = (String) attributes.get("levelValues");

        if (levelValues != null) {
            setLevelValues(levelValues);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer imtdLevelValuesSid = (Integer) attributes.get(
                "imtdLevelValuesSid");

        if (imtdLevelValuesSid != null) {
            setImtdLevelValuesSid(imtdLevelValuesSid);
        }

        Integer levelNo = (Integer) attributes.get("levelNo");

        if (levelNo != null) {
            setLevelNo(levelNo);
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
    public String getLevelValues() {
        return _levelValues;
    }

    @Override
    public void setLevelValues(String levelValues) {
        _levelValues = levelValues;

        if (_imtdLevelValuesRemoteModel != null) {
            try {
                Class<?> clazz = _imtdLevelValuesRemoteModel.getClass();

                Method method = clazz.getMethod("setLevelValues", String.class);

                method.invoke(_imtdLevelValuesRemoteModel, levelValues);
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

        if (_imtdLevelValuesRemoteModel != null) {
            try {
                Class<?> clazz = _imtdLevelValuesRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_imtdLevelValuesRemoteModel, createdDate);
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

        if (_imtdLevelValuesRemoteModel != null) {
            try {
                Class<?> clazz = _imtdLevelValuesRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_imtdLevelValuesRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getImtdLevelValuesSid() {
        return _imtdLevelValuesSid;
    }

    @Override
    public void setImtdLevelValuesSid(int imtdLevelValuesSid) {
        _imtdLevelValuesSid = imtdLevelValuesSid;

        if (_imtdLevelValuesRemoteModel != null) {
            try {
                Class<?> clazz = _imtdLevelValuesRemoteModel.getClass();

                Method method = clazz.getMethod("setImtdLevelValuesSid",
                        int.class);

                method.invoke(_imtdLevelValuesRemoteModel, imtdLevelValuesSid);
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

        if (_imtdLevelValuesRemoteModel != null) {
            try {
                Class<?> clazz = _imtdLevelValuesRemoteModel.getClass();

                Method method = clazz.getMethod("setLevelNo", int.class);

                method.invoke(_imtdLevelValuesRemoteModel, levelNo);
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

        if (_imtdLevelValuesRemoteModel != null) {
            try {
                Class<?> clazz = _imtdLevelValuesRemoteModel.getClass();

                Method method = clazz.getMethod("setVersionNo", int.class);

                method.invoke(_imtdLevelValuesRemoteModel, versionNo);
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

        if (_imtdLevelValuesRemoteModel != null) {
            try {
                Class<?> clazz = _imtdLevelValuesRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_imtdLevelValuesRemoteModel, modifiedBy);
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

        if (_imtdLevelValuesRemoteModel != null) {
            try {
                Class<?> clazz = _imtdLevelValuesRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_imtdLevelValuesRemoteModel, modifiedDate);
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

        if (_imtdLevelValuesRemoteModel != null) {
            try {
                Class<?> clazz = _imtdLevelValuesRemoteModel.getClass();

                Method method = clazz.getMethod("setLevelName", String.class);

                method.invoke(_imtdLevelValuesRemoteModel, levelName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getImtdLevelValuesRemoteModel() {
        return _imtdLevelValuesRemoteModel;
    }

    public void setImtdLevelValuesRemoteModel(
        BaseModel<?> imtdLevelValuesRemoteModel) {
        _imtdLevelValuesRemoteModel = imtdLevelValuesRemoteModel;
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

        Class<?> remoteModelClass = _imtdLevelValuesRemoteModel.getClass();

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

        Object returnValue = method.invoke(_imtdLevelValuesRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ImtdLevelValuesLocalServiceUtil.addImtdLevelValues(this);
        } else {
            ImtdLevelValuesLocalServiceUtil.updateImtdLevelValues(this);
        }
    }

    @Override
    public ImtdLevelValues toEscapedModel() {
        return (ImtdLevelValues) ProxyUtil.newProxyInstance(ImtdLevelValues.class.getClassLoader(),
            new Class[] { ImtdLevelValues.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ImtdLevelValuesClp clone = new ImtdLevelValuesClp();

        clone.setLevelValues(getLevelValues());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setImtdLevelValuesSid(getImtdLevelValuesSid());
        clone.setLevelNo(getLevelNo());
        clone.setVersionNo(getVersionNo());
        clone.setModifiedBy(getModifiedBy());
        clone.setModifiedDate(getModifiedDate());
        clone.setLevelName(getLevelName());

        return clone;
    }

    @Override
    public int compareTo(ImtdLevelValues imtdLevelValues) {
        int primaryKey = imtdLevelValues.getPrimaryKey();

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

        if (!(obj instanceof ImtdLevelValuesClp)) {
            return false;
        }

        ImtdLevelValuesClp imtdLevelValues = (ImtdLevelValuesClp) obj;

        int primaryKey = imtdLevelValues.getPrimaryKey();

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
        StringBundler sb = new StringBundler(19);

        sb.append("{levelValues=");
        sb.append(getLevelValues());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", imtdLevelValuesSid=");
        sb.append(getImtdLevelValuesSid());
        sb.append(", levelNo=");
        sb.append(getLevelNo());
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
        StringBundler sb = new StringBundler(31);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ImtdLevelValues");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>levelValues</column-name><column-value><![CDATA[");
        sb.append(getLevelValues());
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
            "<column><column-name>imtdLevelValuesSid</column-name><column-value><![CDATA[");
        sb.append(getImtdLevelValuesSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>levelNo</column-name><column-value><![CDATA[");
        sb.append(getLevelNo());
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
