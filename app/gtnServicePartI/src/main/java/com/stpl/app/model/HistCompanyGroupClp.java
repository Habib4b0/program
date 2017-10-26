package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.HistCompanyGroupLocalServiceUtil;
import com.stpl.app.service.persistence.HistCompanyGroupPK;

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


public class HistCompanyGroupClp extends BaseModelImpl<HistCompanyGroup>
    implements HistCompanyGroup {
    private String _companyGroupNo;
    private Date _createdDate;
    private int _createdBy;
    private Date _actionDate;
    private String _actionFlag;
    private int _companyGroupSid;
    private String _companyGroupDescription;
    private int _versionNo;
    private int _modifiedBy;
    private Date _modifiedDate;
    private String _companyGroupName;
    private String _companyFilter;
    private BaseModel<?> _histCompanyGroupRemoteModel;

    public HistCompanyGroupClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return HistCompanyGroup.class;
    }

    @Override
    public String getModelClassName() {
        return HistCompanyGroup.class.getName();
    }

    @Override
    public HistCompanyGroupPK getPrimaryKey() {
        return new HistCompanyGroupPK(_actionFlag, _companyGroupSid, _versionNo);
    }

    @Override
    public void setPrimaryKey(HistCompanyGroupPK primaryKey) {
        setActionFlag(primaryKey.actionFlag);
        setCompanyGroupSid(primaryKey.companyGroupSid);
        setVersionNo(primaryKey.versionNo);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new HistCompanyGroupPK(_actionFlag, _companyGroupSid, _versionNo);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((HistCompanyGroupPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("companyGroupNo", getCompanyGroupNo());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("actionDate", getActionDate());
        attributes.put("actionFlag", getActionFlag());
        attributes.put("companyGroupSid", getCompanyGroupSid());
        attributes.put("companyGroupDescription", getCompanyGroupDescription());
        attributes.put("versionNo", getVersionNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("companyGroupName", getCompanyGroupName());
        attributes.put("companyFilter", getCompanyFilter());

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

        Date actionDate = (Date) attributes.get("actionDate");

        if (actionDate != null) {
            setActionDate(actionDate);
        }

        String actionFlag = (String) attributes.get("actionFlag");

        if (actionFlag != null) {
            setActionFlag(actionFlag);
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

        String companyFilter = (String) attributes.get("companyFilter");

        if (companyFilter != null) {
            setCompanyFilter(companyFilter);
        }
    }

    @Override
    public String getCompanyGroupNo() {
        return _companyGroupNo;
    }

    @Override
    public void setCompanyGroupNo(String companyGroupNo) {
        _companyGroupNo = companyGroupNo;

        if (_histCompanyGroupRemoteModel != null) {
            try {
                Class<?> clazz = _histCompanyGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyGroupNo",
                        String.class);

                method.invoke(_histCompanyGroupRemoteModel, companyGroupNo);
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

        if (_histCompanyGroupRemoteModel != null) {
            try {
                Class<?> clazz = _histCompanyGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_histCompanyGroupRemoteModel, createdDate);
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

        if (_histCompanyGroupRemoteModel != null) {
            try {
                Class<?> clazz = _histCompanyGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_histCompanyGroupRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getActionDate() {
        return _actionDate;
    }

    @Override
    public void setActionDate(Date actionDate) {
        _actionDate = actionDate;

        if (_histCompanyGroupRemoteModel != null) {
            try {
                Class<?> clazz = _histCompanyGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setActionDate", Date.class);

                method.invoke(_histCompanyGroupRemoteModel, actionDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getActionFlag() {
        return _actionFlag;
    }

    @Override
    public void setActionFlag(String actionFlag) {
        _actionFlag = actionFlag;

        if (_histCompanyGroupRemoteModel != null) {
            try {
                Class<?> clazz = _histCompanyGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setActionFlag", String.class);

                method.invoke(_histCompanyGroupRemoteModel, actionFlag);
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

        if (_histCompanyGroupRemoteModel != null) {
            try {
                Class<?> clazz = _histCompanyGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyGroupSid", int.class);

                method.invoke(_histCompanyGroupRemoteModel, companyGroupSid);
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

        if (_histCompanyGroupRemoteModel != null) {
            try {
                Class<?> clazz = _histCompanyGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyGroupDescription",
                        String.class);

                method.invoke(_histCompanyGroupRemoteModel,
                    companyGroupDescription);
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

        if (_histCompanyGroupRemoteModel != null) {
            try {
                Class<?> clazz = _histCompanyGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setVersionNo", int.class);

                method.invoke(_histCompanyGroupRemoteModel, versionNo);
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

        if (_histCompanyGroupRemoteModel != null) {
            try {
                Class<?> clazz = _histCompanyGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_histCompanyGroupRemoteModel, modifiedBy);
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

        if (_histCompanyGroupRemoteModel != null) {
            try {
                Class<?> clazz = _histCompanyGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_histCompanyGroupRemoteModel, modifiedDate);
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

        if (_histCompanyGroupRemoteModel != null) {
            try {
                Class<?> clazz = _histCompanyGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyGroupName",
                        String.class);

                method.invoke(_histCompanyGroupRemoteModel, companyGroupName);
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

        if (_histCompanyGroupRemoteModel != null) {
            try {
                Class<?> clazz = _histCompanyGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyFilter", String.class);

                method.invoke(_histCompanyGroupRemoteModel, companyFilter);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getHistCompanyGroupRemoteModel() {
        return _histCompanyGroupRemoteModel;
    }

    public void setHistCompanyGroupRemoteModel(
        BaseModel<?> histCompanyGroupRemoteModel) {
        _histCompanyGroupRemoteModel = histCompanyGroupRemoteModel;
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

        Class<?> remoteModelClass = _histCompanyGroupRemoteModel.getClass();

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

        Object returnValue = method.invoke(_histCompanyGroupRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            HistCompanyGroupLocalServiceUtil.addHistCompanyGroup(this);
        } else {
            HistCompanyGroupLocalServiceUtil.updateHistCompanyGroup(this);
        }
    }

    @Override
    public HistCompanyGroup toEscapedModel() {
        return (HistCompanyGroup) ProxyUtil.newProxyInstance(HistCompanyGroup.class.getClassLoader(),
            new Class[] { HistCompanyGroup.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        HistCompanyGroupClp clone = new HistCompanyGroupClp();

        clone.setCompanyGroupNo(getCompanyGroupNo());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setActionDate(getActionDate());
        clone.setActionFlag(getActionFlag());
        clone.setCompanyGroupSid(getCompanyGroupSid());
        clone.setCompanyGroupDescription(getCompanyGroupDescription());
        clone.setVersionNo(getVersionNo());
        clone.setModifiedBy(getModifiedBy());
        clone.setModifiedDate(getModifiedDate());
        clone.setCompanyGroupName(getCompanyGroupName());
        clone.setCompanyFilter(getCompanyFilter());

        return clone;
    }

    @Override
    public int compareTo(HistCompanyGroup histCompanyGroup) {
        HistCompanyGroupPK primaryKey = histCompanyGroup.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof HistCompanyGroupClp)) {
            return false;
        }

        HistCompanyGroupClp histCompanyGroup = (HistCompanyGroupClp) obj;

        HistCompanyGroupPK primaryKey = histCompanyGroup.getPrimaryKey();

        if (getPrimaryKey().equals(primaryKey)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getPrimaryKey().hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(25);

        sb.append("{companyGroupNo=");
        sb.append(getCompanyGroupNo());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", actionDate=");
        sb.append(getActionDate());
        sb.append(", actionFlag=");
        sb.append(getActionFlag());
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
        sb.append(", companyFilter=");
        sb.append(getCompanyFilter());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(40);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.HistCompanyGroup");
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
            "<column><column-name>actionDate</column-name><column-value><![CDATA[");
        sb.append(getActionDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actionFlag</column-name><column-value><![CDATA[");
        sb.append(getActionFlag());
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
        sb.append(
            "<column><column-name>companyFilter</column-name><column-value><![CDATA[");
        sb.append(getCompanyFilter());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
