package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.CffAdditionalInfoLocalServiceUtil;
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


public class CffAdditionalInfoClp extends BaseModelImpl<CffAdditionalInfo>
    implements CffAdditionalInfo {
    private Date _createdDate;
    private int _createdBy;
    private int _cffMasterSid;
    private int _cffAdditionalInfoSid;
    private String _notes;
    private BaseModel<?> _cffAdditionalInfoRemoteModel;

    public CffAdditionalInfoClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return CffAdditionalInfo.class;
    }

    @Override
    public String getModelClassName() {
        return CffAdditionalInfo.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _cffAdditionalInfoSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setCffAdditionalInfoSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _cffAdditionalInfoSid;
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
        attributes.put("cffAdditionalInfoSid", getCffAdditionalInfoSid());
        attributes.put("notes", getNotes());

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

        Integer cffAdditionalInfoSid = (Integer) attributes.get(
                "cffAdditionalInfoSid");

        if (cffAdditionalInfoSid != null) {
            setCffAdditionalInfoSid(cffAdditionalInfoSid);
        }

        String notes = (String) attributes.get("notes");

        if (notes != null) {
            setNotes(notes);
        }
    }

    @Override
    public Date getCreatedDate() {
        return _createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;

        if (_cffAdditionalInfoRemoteModel != null) {
            try {
                Class<?> clazz = _cffAdditionalInfoRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_cffAdditionalInfoRemoteModel, createdDate);
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

        if (_cffAdditionalInfoRemoteModel != null) {
            try {
                Class<?> clazz = _cffAdditionalInfoRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_cffAdditionalInfoRemoteModel, createdBy);
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

        if (_cffAdditionalInfoRemoteModel != null) {
            try {
                Class<?> clazz = _cffAdditionalInfoRemoteModel.getClass();

                Method method = clazz.getMethod("setCffMasterSid", int.class);

                method.invoke(_cffAdditionalInfoRemoteModel, cffMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCffAdditionalInfoSid() {
        return _cffAdditionalInfoSid;
    }

    @Override
    public void setCffAdditionalInfoSid(int cffAdditionalInfoSid) {
        _cffAdditionalInfoSid = cffAdditionalInfoSid;

        if (_cffAdditionalInfoRemoteModel != null) {
            try {
                Class<?> clazz = _cffAdditionalInfoRemoteModel.getClass();

                Method method = clazz.getMethod("setCffAdditionalInfoSid",
                        int.class);

                method.invoke(_cffAdditionalInfoRemoteModel,
                    cffAdditionalInfoSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNotes() {
        return _notes;
    }

    @Override
    public void setNotes(String notes) {
        _notes = notes;

        if (_cffAdditionalInfoRemoteModel != null) {
            try {
                Class<?> clazz = _cffAdditionalInfoRemoteModel.getClass();

                Method method = clazz.getMethod("setNotes", String.class);

                method.invoke(_cffAdditionalInfoRemoteModel, notes);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getCffAdditionalInfoRemoteModel() {
        return _cffAdditionalInfoRemoteModel;
    }

    public void setCffAdditionalInfoRemoteModel(
        BaseModel<?> cffAdditionalInfoRemoteModel) {
        _cffAdditionalInfoRemoteModel = cffAdditionalInfoRemoteModel;
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

        Class<?> remoteModelClass = _cffAdditionalInfoRemoteModel.getClass();

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

        Object returnValue = method.invoke(_cffAdditionalInfoRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            CffAdditionalInfoLocalServiceUtil.addCffAdditionalInfo(this);
        } else {
            CffAdditionalInfoLocalServiceUtil.updateCffAdditionalInfo(this);
        }
    }

    @Override
    public CffAdditionalInfo toEscapedModel() {
        return (CffAdditionalInfo) ProxyUtil.newProxyInstance(CffAdditionalInfo.class.getClassLoader(),
            new Class[] { CffAdditionalInfo.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CffAdditionalInfoClp clone = new CffAdditionalInfoClp();

        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setCffMasterSid(getCffMasterSid());
        clone.setCffAdditionalInfoSid(getCffAdditionalInfoSid());
        clone.setNotes(getNotes());

        return clone;
    }

    @Override
    public int compareTo(CffAdditionalInfo cffAdditionalInfo) {
        int primaryKey = cffAdditionalInfo.getPrimaryKey();

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

        if (!(obj instanceof CffAdditionalInfoClp)) {
            return false;
        }

        CffAdditionalInfoClp cffAdditionalInfo = (CffAdditionalInfoClp) obj;

        int primaryKey = cffAdditionalInfo.getPrimaryKey();

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
        StringBundler sb = new StringBundler(11);

        sb.append("{createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", cffMasterSid=");
        sb.append(getCffMasterSid());
        sb.append(", cffAdditionalInfoSid=");
        sb.append(getCffAdditionalInfoSid());
        sb.append(", notes=");
        sb.append(getNotes());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(19);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.CffAdditionalInfo");
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
            "<column><column-name>cffAdditionalInfoSid</column-name><column-value><![CDATA[");
        sb.append(getCffAdditionalInfoSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>notes</column-name><column-value><![CDATA[");
        sb.append(getNotes());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
