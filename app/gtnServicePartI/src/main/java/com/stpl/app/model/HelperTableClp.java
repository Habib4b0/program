package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.HelperTableLocalServiceUtil;

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


public class HelperTableClp extends BaseModelImpl<HelperTable>
    implements HelperTable {
    private Date _createdDate;
    private int _createdBy;
    private String _description;
    private int _refCount;
    private String _listName;
    private int _helperTableSid;
    private int _modifiedBy;
    private Date _modifiedDate;
    private BaseModel<?> _helperTableRemoteModel;

    public HelperTableClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return HelperTable.class;
    }

    @Override
    public String getModelClassName() {
        return HelperTable.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _helperTableSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setHelperTableSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _helperTableSid;
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
        attributes.put("description", getDescription());
        attributes.put("refCount", getRefCount());
        attributes.put("listName", getListName());
        attributes.put("helperTableSid", getHelperTableSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("modifiedDate", getModifiedDate());

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

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }

        Integer refCount = (Integer) attributes.get("refCount");

        if (refCount != null) {
            setRefCount(refCount);
        }

        String listName = (String) attributes.get("listName");

        if (listName != null) {
            setListName(listName);
        }

        Integer helperTableSid = (Integer) attributes.get("helperTableSid");

        if (helperTableSid != null) {
            setHelperTableSid(helperTableSid);
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
    public Date getCreatedDate() {
        return _createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;

        if (_helperTableRemoteModel != null) {
            try {
                Class<?> clazz = _helperTableRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_helperTableRemoteModel, createdDate);
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

        if (_helperTableRemoteModel != null) {
            try {
                Class<?> clazz = _helperTableRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_helperTableRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDescription() {
        return _description;
    }

    @Override
    public void setDescription(String description) {
        _description = description;

        if (_helperTableRemoteModel != null) {
            try {
                Class<?> clazz = _helperTableRemoteModel.getClass();

                Method method = clazz.getMethod("setDescription", String.class);

                method.invoke(_helperTableRemoteModel, description);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRefCount() {
        return _refCount;
    }

    @Override
    public void setRefCount(int refCount) {
        _refCount = refCount;

        if (_helperTableRemoteModel != null) {
            try {
                Class<?> clazz = _helperTableRemoteModel.getClass();

                Method method = clazz.getMethod("setRefCount", int.class);

                method.invoke(_helperTableRemoteModel, refCount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getListName() {
        return _listName;
    }

    @Override
    public void setListName(String listName) {
        _listName = listName;

        if (_helperTableRemoteModel != null) {
            try {
                Class<?> clazz = _helperTableRemoteModel.getClass();

                Method method = clazz.getMethod("setListName", String.class);

                method.invoke(_helperTableRemoteModel, listName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getHelperTableSid() {
        return _helperTableSid;
    }

    @Override
    public void setHelperTableSid(int helperTableSid) {
        _helperTableSid = helperTableSid;

        if (_helperTableRemoteModel != null) {
            try {
                Class<?> clazz = _helperTableRemoteModel.getClass();

                Method method = clazz.getMethod("setHelperTableSid", int.class);

                method.invoke(_helperTableRemoteModel, helperTableSid);
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

        if (_helperTableRemoteModel != null) {
            try {
                Class<?> clazz = _helperTableRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_helperTableRemoteModel, modifiedBy);
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

        if (_helperTableRemoteModel != null) {
            try {
                Class<?> clazz = _helperTableRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_helperTableRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getHelperTableRemoteModel() {
        return _helperTableRemoteModel;
    }

    public void setHelperTableRemoteModel(BaseModel<?> helperTableRemoteModel) {
        _helperTableRemoteModel = helperTableRemoteModel;
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

        Class<?> remoteModelClass = _helperTableRemoteModel.getClass();

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

        Object returnValue = method.invoke(_helperTableRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            HelperTableLocalServiceUtil.addHelperTable(this);
        } else {
            HelperTableLocalServiceUtil.updateHelperTable(this);
        }
    }

    @Override
    public HelperTable toEscapedModel() {
        return (HelperTable) ProxyUtil.newProxyInstance(HelperTable.class.getClassLoader(),
            new Class[] { HelperTable.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        HelperTableClp clone = new HelperTableClp();

        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setDescription(getDescription());
        clone.setRefCount(getRefCount());
        clone.setListName(getListName());
        clone.setHelperTableSid(getHelperTableSid());
        clone.setModifiedBy(getModifiedBy());
        clone.setModifiedDate(getModifiedDate());

        return clone;
    }

    @Override
    public int compareTo(HelperTable helperTable) {
        int primaryKey = helperTable.getPrimaryKey();

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

        if (!(obj instanceof HelperTableClp)) {
            return false;
        }

        HelperTableClp helperTable = (HelperTableClp) obj;

        int primaryKey = helperTable.getPrimaryKey();

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

        sb.append("{createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", refCount=");
        sb.append(getRefCount());
        sb.append(", listName=");
        sb.append(getListName());
        sb.append(", helperTableSid=");
        sb.append(getHelperTableSid());
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
        sb.append("com.stpl.app.model.HelperTable");
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
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>refCount</column-name><column-value><![CDATA[");
        sb.append(getRefCount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>listName</column-name><column-value><![CDATA[");
        sb.append(getListName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>helperTableSid</column-name><column-value><![CDATA[");
        sb.append(getHelperTableSid());
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
