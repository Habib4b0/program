package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.AccClosureViewMasterLocalServiceUtil;
import com.stpl.app.parttwo.service.ClpSerializer;

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


public class AccClosureViewMasterClp extends BaseModelImpl<AccClosureViewMaster>
    implements AccClosureViewMaster {
    private Date _createdDate;
    private int _createdBy;
    private String _viewType;
    private int _accClosureMasterSid;
    private int _modifiedBy;
    private int _accClosureViewMasterSid;
    private Date _modifiedDate;
    private String _viewName;
    private BaseModel<?> _accClosureViewMasterRemoteModel;

    public AccClosureViewMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return AccClosureViewMaster.class;
    }

    @Override
    public String getModelClassName() {
        return AccClosureViewMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _accClosureViewMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setAccClosureViewMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _accClosureViewMasterSid;
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
        attributes.put("viewType", getViewType());
        attributes.put("accClosureMasterSid", getAccClosureMasterSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("accClosureViewMasterSid", getAccClosureViewMasterSid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("viewName", getViewName());

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

        String viewType = (String) attributes.get("viewType");

        if (viewType != null) {
            setViewType(viewType);
        }

        Integer accClosureMasterSid = (Integer) attributes.get(
                "accClosureMasterSid");

        if (accClosureMasterSid != null) {
            setAccClosureMasterSid(accClosureMasterSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Integer accClosureViewMasterSid = (Integer) attributes.get(
                "accClosureViewMasterSid");

        if (accClosureViewMasterSid != null) {
            setAccClosureViewMasterSid(accClosureViewMasterSid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String viewName = (String) attributes.get("viewName");

        if (viewName != null) {
            setViewName(viewName);
        }
    }

    @Override
    public Date getCreatedDate() {
        return _createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;

        if (_accClosureViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_accClosureViewMasterRemoteModel, createdDate);
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

        if (_accClosureViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_accClosureViewMasterRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getViewType() {
        return _viewType;
    }

    @Override
    public void setViewType(String viewType) {
        _viewType = viewType;

        if (_accClosureViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setViewType", String.class);

                method.invoke(_accClosureViewMasterRemoteModel, viewType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getAccClosureMasterSid() {
        return _accClosureMasterSid;
    }

    @Override
    public void setAccClosureMasterSid(int accClosureMasterSid) {
        _accClosureMasterSid = accClosureMasterSid;

        if (_accClosureViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccClosureMasterSid",
                        int.class);

                method.invoke(_accClosureViewMasterRemoteModel,
                    accClosureMasterSid);
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

        if (_accClosureViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_accClosureViewMasterRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getAccClosureViewMasterSid() {
        return _accClosureViewMasterSid;
    }

    @Override
    public void setAccClosureViewMasterSid(int accClosureViewMasterSid) {
        _accClosureViewMasterSid = accClosureViewMasterSid;

        if (_accClosureViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccClosureViewMasterSid",
                        int.class);

                method.invoke(_accClosureViewMasterRemoteModel,
                    accClosureViewMasterSid);
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

        if (_accClosureViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_accClosureViewMasterRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getViewName() {
        return _viewName;
    }

    @Override
    public void setViewName(String viewName) {
        _viewName = viewName;

        if (_accClosureViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setViewName", String.class);

                method.invoke(_accClosureViewMasterRemoteModel, viewName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getAccClosureViewMasterRemoteModel() {
        return _accClosureViewMasterRemoteModel;
    }

    public void setAccClosureViewMasterRemoteModel(
        BaseModel<?> accClosureViewMasterRemoteModel) {
        _accClosureViewMasterRemoteModel = accClosureViewMasterRemoteModel;
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

        Class<?> remoteModelClass = _accClosureViewMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_accClosureViewMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            AccClosureViewMasterLocalServiceUtil.addAccClosureViewMaster(this);
        } else {
            AccClosureViewMasterLocalServiceUtil.updateAccClosureViewMaster(this);
        }
    }

    @Override
    public AccClosureViewMaster toEscapedModel() {
        return (AccClosureViewMaster) ProxyUtil.newProxyInstance(AccClosureViewMaster.class.getClassLoader(),
            new Class[] { AccClosureViewMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        AccClosureViewMasterClp clone = new AccClosureViewMasterClp();

        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setViewType(getViewType());
        clone.setAccClosureMasterSid(getAccClosureMasterSid());
        clone.setModifiedBy(getModifiedBy());
        clone.setAccClosureViewMasterSid(getAccClosureViewMasterSid());
        clone.setModifiedDate(getModifiedDate());
        clone.setViewName(getViewName());

        return clone;
    }

    @Override
    public int compareTo(AccClosureViewMaster accClosureViewMaster) {
        int primaryKey = accClosureViewMaster.getPrimaryKey();

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

        if (!(obj instanceof AccClosureViewMasterClp)) {
            return false;
        }

        AccClosureViewMasterClp accClosureViewMaster = (AccClosureViewMasterClp) obj;

        int primaryKey = accClosureViewMaster.getPrimaryKey();

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
        sb.append(", viewType=");
        sb.append(getViewType());
        sb.append(", accClosureMasterSid=");
        sb.append(getAccClosureMasterSid());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", accClosureViewMasterSid=");
        sb.append(getAccClosureViewMasterSid());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", viewName=");
        sb.append(getViewName());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(28);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.AccClosureViewMaster");
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
            "<column><column-name>viewType</column-name><column-value><![CDATA[");
        sb.append(getViewType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accClosureMasterSid</column-name><column-value><![CDATA[");
        sb.append(getAccClosureMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accClosureViewMasterSid</column-name><column-value><![CDATA[");
        sb.append(getAccClosureViewMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>viewName</column-name><column-value><![CDATA[");
        sb.append(getViewName());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
