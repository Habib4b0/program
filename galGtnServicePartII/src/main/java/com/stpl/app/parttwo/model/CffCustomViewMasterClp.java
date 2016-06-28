package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.CffCustomViewMasterLocalServiceUtil;
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


public class CffCustomViewMasterClp extends BaseModelImpl<CffCustomViewMaster>
    implements CffCustomViewMaster {
    private Date _createdDate;
    private int _createdBy;
    private int _cffMasterSid;
    private int _modifiedBy;
    private int _cffCustomViewMasterSid;
    private Date _modifiedDate;
    private String _viewName;
    private BaseModel<?> _cffCustomViewMasterRemoteModel;

    public CffCustomViewMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return CffCustomViewMaster.class;
    }

    @Override
    public String getModelClassName() {
        return CffCustomViewMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _cffCustomViewMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setCffCustomViewMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _cffCustomViewMasterSid;
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
        attributes.put("cffMasterSid", getCffMasterSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("cffCustomViewMasterSid", getCffCustomViewMasterSid());
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

        Integer cffMasterSid = (Integer) attributes.get("cffMasterSid");

        if (cffMasterSid != null) {
            setCffMasterSid(cffMasterSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Integer cffCustomViewMasterSid = (Integer) attributes.get(
                "cffCustomViewMasterSid");

        if (cffCustomViewMasterSid != null) {
            setCffCustomViewMasterSid(cffCustomViewMasterSid);
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

        if (_cffCustomViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffCustomViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_cffCustomViewMasterRemoteModel, createdDate);
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

        if (_cffCustomViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffCustomViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_cffCustomViewMasterRemoteModel, createdBy);
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

        if (_cffCustomViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffCustomViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCffMasterSid", int.class);

                method.invoke(_cffCustomViewMasterRemoteModel, cffMasterSid);
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

        if (_cffCustomViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffCustomViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_cffCustomViewMasterRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCffCustomViewMasterSid() {
        return _cffCustomViewMasterSid;
    }

    @Override
    public void setCffCustomViewMasterSid(int cffCustomViewMasterSid) {
        _cffCustomViewMasterSid = cffCustomViewMasterSid;

        if (_cffCustomViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffCustomViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCffCustomViewMasterSid",
                        int.class);

                method.invoke(_cffCustomViewMasterRemoteModel,
                    cffCustomViewMasterSid);
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

        if (_cffCustomViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffCustomViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_cffCustomViewMasterRemoteModel, modifiedDate);
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

        if (_cffCustomViewMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cffCustomViewMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setViewName", String.class);

                method.invoke(_cffCustomViewMasterRemoteModel, viewName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getCffCustomViewMasterRemoteModel() {
        return _cffCustomViewMasterRemoteModel;
    }

    public void setCffCustomViewMasterRemoteModel(
        BaseModel<?> cffCustomViewMasterRemoteModel) {
        _cffCustomViewMasterRemoteModel = cffCustomViewMasterRemoteModel;
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

        Class<?> remoteModelClass = _cffCustomViewMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_cffCustomViewMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            CffCustomViewMasterLocalServiceUtil.addCffCustomViewMaster(this);
        } else {
            CffCustomViewMasterLocalServiceUtil.updateCffCustomViewMaster(this);
        }
    }

    @Override
    public CffCustomViewMaster toEscapedModel() {
        return (CffCustomViewMaster) ProxyUtil.newProxyInstance(CffCustomViewMaster.class.getClassLoader(),
            new Class[] { CffCustomViewMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CffCustomViewMasterClp clone = new CffCustomViewMasterClp();

        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setCffMasterSid(getCffMasterSid());
        clone.setModifiedBy(getModifiedBy());
        clone.setCffCustomViewMasterSid(getCffCustomViewMasterSid());
        clone.setModifiedDate(getModifiedDate());
        clone.setViewName(getViewName());

        return clone;
    }

    @Override
    public int compareTo(CffCustomViewMaster cffCustomViewMaster) {
        int primaryKey = cffCustomViewMaster.getPrimaryKey();

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

        if (!(obj instanceof CffCustomViewMasterClp)) {
            return false;
        }

        CffCustomViewMasterClp cffCustomViewMaster = (CffCustomViewMasterClp) obj;

        int primaryKey = cffCustomViewMaster.getPrimaryKey();

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
        StringBundler sb = new StringBundler(15);

        sb.append("{createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", cffMasterSid=");
        sb.append(getCffMasterSid());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", cffCustomViewMasterSid=");
        sb.append(getCffCustomViewMasterSid());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", viewName=");
        sb.append(getViewName());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(25);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.CffCustomViewMaster");
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
            "<column><column-name>cffMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCffMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cffCustomViewMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCffCustomViewMasterSid());
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
