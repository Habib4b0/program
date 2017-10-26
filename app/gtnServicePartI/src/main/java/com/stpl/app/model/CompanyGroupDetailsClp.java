package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.CompanyGroupDetailsLocalServiceUtil;

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


public class CompanyGroupDetailsClp extends BaseModelImpl<CompanyGroupDetails>
    implements CompanyGroupDetails {
    private Date _createdDate;
    private int _createdBy;
    private String _companyParentDetailsSid;
    private int _companyTradeclassSid;
    private int _companyGroupSid;
    private int _versionNo;
    private int _companyGroupDetailsSid;
    private int _modifiedBy;
    private Date _modifiedDate;
    private int _companyMasterSid;
    private BaseModel<?> _companyGroupDetailsRemoteModel;

    public CompanyGroupDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return CompanyGroupDetails.class;
    }

    @Override
    public String getModelClassName() {
        return CompanyGroupDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _companyGroupDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setCompanyGroupDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _companyGroupDetailsSid;
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
        attributes.put("companyParentDetailsSid", getCompanyParentDetailsSid());
        attributes.put("companyTradeclassSid", getCompanyTradeclassSid());
        attributes.put("companyGroupSid", getCompanyGroupSid());
        attributes.put("versionNo", getVersionNo());
        attributes.put("companyGroupDetailsSid", getCompanyGroupDetailsSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("companyMasterSid", getCompanyMasterSid());

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

        String companyParentDetailsSid = (String) attributes.get(
                "companyParentDetailsSid");

        if (companyParentDetailsSid != null) {
            setCompanyParentDetailsSid(companyParentDetailsSid);
        }

        Integer companyTradeclassSid = (Integer) attributes.get(
                "companyTradeclassSid");

        if (companyTradeclassSid != null) {
            setCompanyTradeclassSid(companyTradeclassSid);
        }

        Integer companyGroupSid = (Integer) attributes.get("companyGroupSid");

        if (companyGroupSid != null) {
            setCompanyGroupSid(companyGroupSid);
        }

        Integer versionNo = (Integer) attributes.get("versionNo");

        if (versionNo != null) {
            setVersionNo(versionNo);
        }

        Integer companyGroupDetailsSid = (Integer) attributes.get(
                "companyGroupDetailsSid");

        if (companyGroupDetailsSid != null) {
            setCompanyGroupDetailsSid(companyGroupDetailsSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }
    }

    @Override
    public Date getCreatedDate() {
        return _createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;

        if (_companyGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _companyGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_companyGroupDetailsRemoteModel, createdDate);
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

        if (_companyGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _companyGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_companyGroupDetailsRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyParentDetailsSid() {
        return _companyParentDetailsSid;
    }

    @Override
    public void setCompanyParentDetailsSid(String companyParentDetailsSid) {
        _companyParentDetailsSid = companyParentDetailsSid;

        if (_companyGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _companyGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyParentDetailsSid",
                        String.class);

                method.invoke(_companyGroupDetailsRemoteModel,
                    companyParentDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCompanyTradeclassSid() {
        return _companyTradeclassSid;
    }

    @Override
    public void setCompanyTradeclassSid(int companyTradeclassSid) {
        _companyTradeclassSid = companyTradeclassSid;

        if (_companyGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _companyGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyTradeclassSid",
                        int.class);

                method.invoke(_companyGroupDetailsRemoteModel,
                    companyTradeclassSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCompanyGroupSid() {
        return _companyGroupSid;
    }

    @Override
    public void setCompanyGroupSid(int companyGroupSid) {
        _companyGroupSid = companyGroupSid;

        if (_companyGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _companyGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyGroupSid", int.class);

                method.invoke(_companyGroupDetailsRemoteModel, companyGroupSid);
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

        if (_companyGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _companyGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setVersionNo", int.class);

                method.invoke(_companyGroupDetailsRemoteModel, versionNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCompanyGroupDetailsSid() {
        return _companyGroupDetailsSid;
    }

    @Override
    public void setCompanyGroupDetailsSid(int companyGroupDetailsSid) {
        _companyGroupDetailsSid = companyGroupDetailsSid;

        if (_companyGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _companyGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyGroupDetailsSid",
                        int.class);

                method.invoke(_companyGroupDetailsRemoteModel,
                    companyGroupDetailsSid);
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

        if (_companyGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _companyGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_companyGroupDetailsRemoteModel, modifiedBy);
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

        if (_companyGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _companyGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_companyGroupDetailsRemoteModel, modifiedDate);
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

        if (_companyGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _companyGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyMasterSid", int.class);

                method.invoke(_companyGroupDetailsRemoteModel, companyMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getCompanyGroupDetailsRemoteModel() {
        return _companyGroupDetailsRemoteModel;
    }

    public void setCompanyGroupDetailsRemoteModel(
        BaseModel<?> companyGroupDetailsRemoteModel) {
        _companyGroupDetailsRemoteModel = companyGroupDetailsRemoteModel;
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

        Class<?> remoteModelClass = _companyGroupDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_companyGroupDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            CompanyGroupDetailsLocalServiceUtil.addCompanyGroupDetails(this);
        } else {
            CompanyGroupDetailsLocalServiceUtil.updateCompanyGroupDetails(this);
        }
    }

    @Override
    public CompanyGroupDetails toEscapedModel() {
        return (CompanyGroupDetails) ProxyUtil.newProxyInstance(CompanyGroupDetails.class.getClassLoader(),
            new Class[] { CompanyGroupDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CompanyGroupDetailsClp clone = new CompanyGroupDetailsClp();

        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setCompanyParentDetailsSid(getCompanyParentDetailsSid());
        clone.setCompanyTradeclassSid(getCompanyTradeclassSid());
        clone.setCompanyGroupSid(getCompanyGroupSid());
        clone.setVersionNo(getVersionNo());
        clone.setCompanyGroupDetailsSid(getCompanyGroupDetailsSid());
        clone.setModifiedBy(getModifiedBy());
        clone.setModifiedDate(getModifiedDate());
        clone.setCompanyMasterSid(getCompanyMasterSid());

        return clone;
    }

    @Override
    public int compareTo(CompanyGroupDetails companyGroupDetails) {
        int primaryKey = companyGroupDetails.getPrimaryKey();

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

        if (!(obj instanceof CompanyGroupDetailsClp)) {
            return false;
        }

        CompanyGroupDetailsClp companyGroupDetails = (CompanyGroupDetailsClp) obj;

        int primaryKey = companyGroupDetails.getPrimaryKey();

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
        sb.append(", companyParentDetailsSid=");
        sb.append(getCompanyParentDetailsSid());
        sb.append(", companyTradeclassSid=");
        sb.append(getCompanyTradeclassSid());
        sb.append(", companyGroupSid=");
        sb.append(getCompanyGroupSid());
        sb.append(", versionNo=");
        sb.append(getVersionNo());
        sb.append(", companyGroupDetailsSid=");
        sb.append(getCompanyGroupDetailsSid());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", companyMasterSid=");
        sb.append(getCompanyMasterSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(34);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.CompanyGroupDetails");
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
            "<column><column-name>companyParentDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyParentDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyTradeclassSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyTradeclassSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyGroupSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyGroupSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>versionNo</column-name><column-value><![CDATA[");
        sb.append(getVersionNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyGroupDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyGroupDetailsSid());
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
            "<column><column-name>companyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyMasterSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
