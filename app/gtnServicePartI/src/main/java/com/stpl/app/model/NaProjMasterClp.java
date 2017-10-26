package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.NaProjMasterLocalServiceUtil;

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


public class NaProjMasterClp extends BaseModelImpl<NaProjMaster>
    implements NaProjMaster {
    private String _naProjName;
    private Date _createdDate;
    private int _createdBy;
    private boolean _saveFlag;
    private int _modifiedBy;
    private Date _modifiedDate;
    private int _naProjMasterSid;
    private int _itemGroupSid;
    private int _therapeuticClass;
    private int _companyMasterSid;
    private int _businessUnit;
    private BaseModel<?> _naProjMasterRemoteModel;

    public NaProjMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return NaProjMaster.class;
    }

    @Override
    public String getModelClassName() {
        return NaProjMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _naProjMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setNaProjMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _naProjMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("naProjName", getNaProjName());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("saveFlag", getSaveFlag());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("naProjMasterSid", getNaProjMasterSid());
        attributes.put("itemGroupSid", getItemGroupSid());
        attributes.put("therapeuticClass", getTherapeuticClass());
        attributes.put("companyMasterSid", getCompanyMasterSid());
        attributes.put("businessUnit", getBusinessUnit());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String naProjName = (String) attributes.get("naProjName");

        if (naProjName != null) {
            setNaProjName(naProjName);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Boolean saveFlag = (Boolean) attributes.get("saveFlag");

        if (saveFlag != null) {
            setSaveFlag(saveFlag);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer naProjMasterSid = (Integer) attributes.get("naProjMasterSid");

        if (naProjMasterSid != null) {
            setNaProjMasterSid(naProjMasterSid);
        }

        Integer itemGroupSid = (Integer) attributes.get("itemGroupSid");

        if (itemGroupSid != null) {
            setItemGroupSid(itemGroupSid);
        }

        Integer therapeuticClass = (Integer) attributes.get("therapeuticClass");

        if (therapeuticClass != null) {
            setTherapeuticClass(therapeuticClass);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }

        Integer businessUnit = (Integer) attributes.get("businessUnit");

        if (businessUnit != null) {
            setBusinessUnit(businessUnit);
        }
    }

    @Override
    public String getNaProjName() {
        return _naProjName;
    }

    @Override
    public void setNaProjName(String naProjName) {
        _naProjName = naProjName;

        if (_naProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _naProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNaProjName", String.class);

                method.invoke(_naProjMasterRemoteModel, naProjName);
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

        if (_naProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _naProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_naProjMasterRemoteModel, createdDate);
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

        if (_naProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _naProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_naProjMasterRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getSaveFlag() {
        return _saveFlag;
    }

    @Override
    public boolean isSaveFlag() {
        return _saveFlag;
    }

    @Override
    public void setSaveFlag(boolean saveFlag) {
        _saveFlag = saveFlag;

        if (_naProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _naProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSaveFlag", boolean.class);

                method.invoke(_naProjMasterRemoteModel, saveFlag);
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

        if (_naProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _naProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_naProjMasterRemoteModel, modifiedBy);
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

        if (_naProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _naProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_naProjMasterRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getNaProjMasterSid() {
        return _naProjMasterSid;
    }

    @Override
    public void setNaProjMasterSid(int naProjMasterSid) {
        _naProjMasterSid = naProjMasterSid;

        if (_naProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _naProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNaProjMasterSid", int.class);

                method.invoke(_naProjMasterRemoteModel, naProjMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getItemGroupSid() {
        return _itemGroupSid;
    }

    @Override
    public void setItemGroupSid(int itemGroupSid) {
        _itemGroupSid = itemGroupSid;

        if (_naProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _naProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemGroupSid", int.class);

                method.invoke(_naProjMasterRemoteModel, itemGroupSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getTherapeuticClass() {
        return _therapeuticClass;
    }

    @Override
    public void setTherapeuticClass(int therapeuticClass) {
        _therapeuticClass = therapeuticClass;

        if (_naProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _naProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setTherapeuticClass", int.class);

                method.invoke(_naProjMasterRemoteModel, therapeuticClass);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCompanyMasterSid() {
        return _companyMasterSid;
    }

    @Override
    public void setCompanyMasterSid(int companyMasterSid) {
        _companyMasterSid = companyMasterSid;

        if (_naProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _naProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyMasterSid", int.class);

                method.invoke(_naProjMasterRemoteModel, companyMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getBusinessUnit() {
        return _businessUnit;
    }

    @Override
    public void setBusinessUnit(int businessUnit) {
        _businessUnit = businessUnit;

        if (_naProjMasterRemoteModel != null) {
            try {
                Class<?> clazz = _naProjMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBusinessUnit", int.class);

                method.invoke(_naProjMasterRemoteModel, businessUnit);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getNaProjMasterRemoteModel() {
        return _naProjMasterRemoteModel;
    }

    public void setNaProjMasterRemoteModel(BaseModel<?> naProjMasterRemoteModel) {
        _naProjMasterRemoteModel = naProjMasterRemoteModel;
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

        Class<?> remoteModelClass = _naProjMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_naProjMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            NaProjMasterLocalServiceUtil.addNaProjMaster(this);
        } else {
            NaProjMasterLocalServiceUtil.updateNaProjMaster(this);
        }
    }

    @Override
    public NaProjMaster toEscapedModel() {
        return (NaProjMaster) ProxyUtil.newProxyInstance(NaProjMaster.class.getClassLoader(),
            new Class[] { NaProjMaster.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        NaProjMasterClp clone = new NaProjMasterClp();

        clone.setNaProjName(getNaProjName());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setSaveFlag(getSaveFlag());
        clone.setModifiedBy(getModifiedBy());
        clone.setModifiedDate(getModifiedDate());
        clone.setNaProjMasterSid(getNaProjMasterSid());
        clone.setItemGroupSid(getItemGroupSid());
        clone.setTherapeuticClass(getTherapeuticClass());
        clone.setCompanyMasterSid(getCompanyMasterSid());
        clone.setBusinessUnit(getBusinessUnit());

        return clone;
    }

    @Override
    public int compareTo(NaProjMaster naProjMaster) {
        int primaryKey = naProjMaster.getPrimaryKey();

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

        if (!(obj instanceof NaProjMasterClp)) {
            return false;
        }

        NaProjMasterClp naProjMaster = (NaProjMasterClp) obj;

        int primaryKey = naProjMaster.getPrimaryKey();

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
        StringBundler sb = new StringBundler(23);

        sb.append("{naProjName=");
        sb.append(getNaProjName());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", saveFlag=");
        sb.append(getSaveFlag());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", naProjMasterSid=");
        sb.append(getNaProjMasterSid());
        sb.append(", itemGroupSid=");
        sb.append(getItemGroupSid());
        sb.append(", therapeuticClass=");
        sb.append(getTherapeuticClass());
        sb.append(", companyMasterSid=");
        sb.append(getCompanyMasterSid());
        sb.append(", businessUnit=");
        sb.append(getBusinessUnit());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(37);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.NaProjMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>naProjName</column-name><column-value><![CDATA[");
        sb.append(getNaProjName());
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
            "<column><column-name>saveFlag</column-name><column-value><![CDATA[");
        sb.append(getSaveFlag());
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
            "<column><column-name>naProjMasterSid</column-name><column-value><![CDATA[");
        sb.append(getNaProjMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemGroupSid</column-name><column-value><![CDATA[");
        sb.append(getItemGroupSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>therapeuticClass</column-name><column-value><![CDATA[");
        sb.append(getTherapeuticClass());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>businessUnit</column-name><column-value><![CDATA[");
        sb.append(getBusinessUnit());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
