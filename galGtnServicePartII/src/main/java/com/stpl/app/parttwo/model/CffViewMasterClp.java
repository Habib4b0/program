package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.CffViewMasterLocalServiceUtil;
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


public class CffViewMasterClp extends BaseModelImpl<CffViewMaster>
    implements CffViewMaster {
    private Date _createdDate;
    private String _createdBy;
    private String _viewType;
    private int _cffViewMasterSid;
    private int _cffMasterSid;
    private String _modifiedBy;
    private Date _modifiedDate;
    private String _viewName;
    private BaseModel<?> _cffViewMasterRemoteModel;

    public CffViewMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return CffViewMaster.class;
    }

    @Override
    public String getModelClassName() {
        return CffViewMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _cffViewMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setCffViewMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _cffViewMasterSid;
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
        attributes.put("cffViewMasterSid", getCffViewMasterSid());
        attributes.put("cffMasterSid", getCffMasterSid());
        attributes.put("modifiedBy", getModifiedBy());
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

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String viewType = (String) attributes.get("viewType");

        if (viewType != null) {
            setViewType(viewType);
        }

        Integer cffViewMasterSid = (Integer) attributes.get("cffViewMasterSid");

        if (cffViewMasterSid != null) {
            setCffViewMasterSid(cffViewMasterSid);
        }

        Integer cffMasterSid = (Integer) attributes.get("cffMasterSid");

        if (cffMasterSid != null) {
            setCffMasterSid(cffMasterSid);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
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

        if (_cffViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_cffViewMasterRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCreatedBy() {
        return _createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        _createdBy = createdBy;

        if (_cffViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_cffViewMasterRemoteModel, createdBy);
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

        if (_cffViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setViewType", String.class);

                method.invoke(_cffViewMasterRemoteModel, viewType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCffViewMasterSid() {
        return _cffViewMasterSid;
    }

    @Override
    public void setCffViewMasterSid(int cffViewMasterSid) {
        _cffViewMasterSid = cffViewMasterSid;

        if (_cffViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCffViewMasterSid", int.class);

                method.invoke(_cffViewMasterRemoteModel, cffViewMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCffMasterSid() {
        return _cffMasterSid;
    }

    @Override
    public void setCffMasterSid(int cffMasterSid) {
        _cffMasterSid = cffMasterSid;

        if (_cffViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCffMasterSid", int.class);

                method.invoke(_cffViewMasterRemoteModel, cffMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getModifiedBy() {
        return _modifiedBy;
    }

    @Override
    public void setModifiedBy(String modifiedBy) {
        _modifiedBy = modifiedBy;

        if (_cffViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_cffViewMasterRemoteModel, modifiedBy);
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

        if (_cffViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_cffViewMasterRemoteModel, modifiedDate);
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

        if (_cffViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setViewName", String.class);

                method.invoke(_cffViewMasterRemoteModel, viewName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getCffViewMasterRemoteModel() {
        return _cffViewMasterRemoteModel;
    }

    public void setCffViewMasterRemoteModel(
        BaseModel<?> cffViewMasterRemoteModel) {
        _cffViewMasterRemoteModel = cffViewMasterRemoteModel;
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

        Class<?> remoteModelClass = _cffViewMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_cffViewMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            CffViewMasterLocalServiceUtil.addCffViewMaster(this);
        } else {
            CffViewMasterLocalServiceUtil.updateCffViewMaster(this);
        }
    }

    @Override
    public CffViewMaster toEscapedModel() {
        return (CffViewMaster) ProxyUtil.newProxyInstance(CffViewMaster.class.getClassLoader(),
            new Class[] { CffViewMaster.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CffViewMasterClp clone = new CffViewMasterClp();

        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setViewType(getViewType());
        clone.setCffViewMasterSid(getCffViewMasterSid());
        clone.setCffMasterSid(getCffMasterSid());
        clone.setModifiedBy(getModifiedBy());
        clone.setModifiedDate(getModifiedDate());
        clone.setViewName(getViewName());

        return clone;
    }

    @Override
    public int compareTo(CffViewMaster cffViewMaster) {
        int primaryKey = cffViewMaster.getPrimaryKey();

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

        if (!(obj instanceof CffViewMasterClp)) {
            return false;
        }

        CffViewMasterClp cffViewMaster = (CffViewMasterClp) obj;

        int primaryKey = cffViewMaster.getPrimaryKey();

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
        sb.append(", cffViewMasterSid=");
        sb.append(getCffViewMasterSid());
        sb.append(", cffMasterSid=");
        sb.append(getCffMasterSid());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
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
        sb.append("com.stpl.app.parttwo.model.CffViewMaster");
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
            "<column><column-name>cffViewMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCffViewMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cffMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCffMasterSid());
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
            "<column><column-name>viewName</column-name><column-value><![CDATA[");
        sb.append(getViewName());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
