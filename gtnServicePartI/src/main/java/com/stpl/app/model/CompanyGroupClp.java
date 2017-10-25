package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.CompanyGroupLocalServiceUtil;

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


public class CompanyGroupClp extends BaseModelImpl<CompanyGroup>
    implements CompanyGroup {
    private String _companyGroupNo;
    private Date _createdDate;
    private int _createdBy;
    private String _companyFilter;
    private int _companyGroupSid;
    private String _companyGroupDescription;
    private int _versionNo;
    private int _modifiedBy;
    private Date _modifiedDate;
    private String _companyGroupName;
    private BaseModel<?> _companyGroupRemoteModel;

    public CompanyGroupClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return CompanyGroup.class;
    }

    @Override
    public String getModelClassName() {
        return CompanyGroup.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _companyGroupSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setCompanyGroupSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _companyGroupSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("companyGroupNo", getCompanyGroupNo());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("companyFilter", getCompanyFilter());
        attributes.put("companyGroupSid", getCompanyGroupSid());
        attributes.put("companyGroupDescription", getCompanyGroupDescription());
        attributes.put("versionNo", getVersionNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("companyGroupName", getCompanyGroupName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String companyGroupNo = (String) attributes.get("companyGroupNo");

        if (companyGroupNo != null) {
            setCompanyGroupNo(companyGroupNo);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String companyFilter = (String) attributes.get("companyFilter");

        if (companyFilter != null) {
            setCompanyFilter(companyFilter);
        }

        Integer companyGroupSid = (Integer) attributes.get("companyGroupSid");

        if (companyGroupSid != null) {
            setCompanyGroupSid(companyGroupSid);
        }

        String companyGroupDescription = (String) attributes.get(
                "companyGroupDescription");

        if (companyGroupDescription != null) {
            setCompanyGroupDescription(companyGroupDescription);
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

        String companyGroupName = (String) attributes.get("companyGroupName");

        if (companyGroupName != null) {
            setCompanyGroupName(companyGroupName);
        }
    }

    @Override
    public String getCompanyGroupNo() {
        return _companyGroupNo;
    }

    @Override
    public void setCompanyGroupNo(String companyGroupNo) {
        _companyGroupNo = companyGroupNo;

        if (_companyGroupRemoteModel != null) {
            try {
                Class<?> clazz = _companyGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyGroupNo",
                        String.class);

                method.invoke(_companyGroupRemoteModel, companyGroupNo);
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

        if (_companyGroupRemoteModel != null) {
            try {
                Class<?> clazz = _companyGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_companyGroupRemoteModel, createdDate);
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

        if (_companyGroupRemoteModel != null) {
            try {
                Class<?> clazz = _companyGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_companyGroupRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyFilter() {
        return _companyFilter;
    }

    @Override
    public void setCompanyFilter(String companyFilter) {
        _companyFilter = companyFilter;

        if (_companyGroupRemoteModel != null) {
            try {
                Class<?> clazz = _companyGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyFilter", String.class);

                method.invoke(_companyGroupRemoteModel, companyFilter);
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

        if (_companyGroupRemoteModel != null) {
            try {
                Class<?> clazz = _companyGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyGroupSid", int.class);

                method.invoke(_companyGroupRemoteModel, companyGroupSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyGroupDescription() {
        return _companyGroupDescription;
    }

    @Override
    public void setCompanyGroupDescription(String companyGroupDescription) {
        _companyGroupDescription = companyGroupDescription;

        if (_companyGroupRemoteModel != null) {
            try {
                Class<?> clazz = _companyGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyGroupDescription",
                        String.class);

                method.invoke(_companyGroupRemoteModel, companyGroupDescription);
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

        if (_companyGroupRemoteModel != null) {
            try {
                Class<?> clazz = _companyGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setVersionNo", int.class);

                method.invoke(_companyGroupRemoteModel, versionNo);
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

        if (_companyGroupRemoteModel != null) {
            try {
                Class<?> clazz = _companyGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_companyGroupRemoteModel, modifiedBy);
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

        if (_companyGroupRemoteModel != null) {
            try {
                Class<?> clazz = _companyGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_companyGroupRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyGroupName() {
        return _companyGroupName;
    }

    @Override
    public void setCompanyGroupName(String companyGroupName) {
        _companyGroupName = companyGroupName;

        if (_companyGroupRemoteModel != null) {
            try {
                Class<?> clazz = _companyGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyGroupName",
                        String.class);

                method.invoke(_companyGroupRemoteModel, companyGroupName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getCompanyGroupRemoteModel() {
        return _companyGroupRemoteModel;
    }

    public void setCompanyGroupRemoteModel(BaseModel<?> companyGroupRemoteModel) {
        _companyGroupRemoteModel = companyGroupRemoteModel;
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

        Class<?> remoteModelClass = _companyGroupRemoteModel.getClass();

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

        Object returnValue = method.invoke(_companyGroupRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            CompanyGroupLocalServiceUtil.addCompanyGroup(this);
        } else {
            CompanyGroupLocalServiceUtil.updateCompanyGroup(this);
        }
    }

    @Override
    public CompanyGroup toEscapedModel() {
        return (CompanyGroup) ProxyUtil.newProxyInstance(CompanyGroup.class.getClassLoader(),
            new Class[] { CompanyGroup.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CompanyGroupClp clone = new CompanyGroupClp();

        clone.setCompanyGroupNo(getCompanyGroupNo());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setCompanyFilter(getCompanyFilter());
        clone.setCompanyGroupSid(getCompanyGroupSid());
        clone.setCompanyGroupDescription(getCompanyGroupDescription());
        clone.setVersionNo(getVersionNo());
        clone.setModifiedBy(getModifiedBy());
        clone.setModifiedDate(getModifiedDate());
        clone.setCompanyGroupName(getCompanyGroupName());

        return clone;
    }

    @Override
    public int compareTo(CompanyGroup companyGroup) {
        int primaryKey = companyGroup.getPrimaryKey();

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

        if (!(obj instanceof CompanyGroupClp)) {
            return false;
        }

        CompanyGroupClp companyGroup = (CompanyGroupClp) obj;

        int primaryKey = companyGroup.getPrimaryKey();

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

        sb.append("{companyGroupNo=");
        sb.append(getCompanyGroupNo());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", companyFilter=");
        sb.append(getCompanyFilter());
        sb.append(", companyGroupSid=");
        sb.append(getCompanyGroupSid());
        sb.append(", companyGroupDescription=");
        sb.append(getCompanyGroupDescription());
        sb.append(", versionNo=");
        sb.append(getVersionNo());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", companyGroupName=");
        sb.append(getCompanyGroupName());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(34);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.CompanyGroup");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>companyGroupNo</column-name><column-value><![CDATA[");
        sb.append(getCompanyGroupNo());
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
            "<column><column-name>companyFilter</column-name><column-value><![CDATA[");
        sb.append(getCompanyFilter());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyGroupSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyGroupSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyGroupDescription</column-name><column-value><![CDATA[");
        sb.append(getCompanyGroupDescription());
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
            "<column><column-name>companyGroupName</column-name><column-value><![CDATA[");
        sb.append(getCompanyGroupName());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
