package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.HistCompanyGroupDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.HistCompanyGroupDetailsPK;

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


public class HistCompanyGroupDetailsClp extends BaseModelImpl<HistCompanyGroupDetails>
    implements HistCompanyGroupDetails {
    private Date _createdDate;
    private int _createdBy;
    private Date _actionDate;
    private String _companyParentDetailsSid;
    private int _companyTradeclassSid;
    private String _actionFlag;
    private int _companyGroupSid;
    private int _versionNo;
    private int _companyGroupDetailsSid;
    private int _modifiedBy;
    private Date _modifiedDate;
    private int _companyMasterSid;
    private BaseModel<?> _histCompanyGroupDetailsRemoteModel;

    public HistCompanyGroupDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return HistCompanyGroupDetails.class;
    }

    @Override
    public String getModelClassName() {
        return HistCompanyGroupDetails.class.getName();
    }

    @Override
    public HistCompanyGroupDetailsPK getPrimaryKey() {
        return new HistCompanyGroupDetailsPK(_actionFlag, _versionNo,
            _companyGroupDetailsSid);
    }

    @Override
    public void setPrimaryKey(HistCompanyGroupDetailsPK primaryKey) {
        setActionFlag(primaryKey.actionFlag);
        setVersionNo(primaryKey.versionNo);
        setCompanyGroupDetailsSid(primaryKey.companyGroupDetailsSid);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new HistCompanyGroupDetailsPK(_actionFlag, _versionNo,
            _companyGroupDetailsSid);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((HistCompanyGroupDetailsPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("actionDate", getActionDate());
        attributes.put("companyParentDetailsSid", getCompanyParentDetailsSid());
        attributes.put("companyTradeclassSid", getCompanyTradeclassSid());
        attributes.put("actionFlag", getActionFlag());
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

        Date actionDate = (Date) attributes.get("actionDate");

        if (actionDate != null) {
            setActionDate(actionDate);
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

        String actionFlag = (String) attributes.get("actionFlag");

        if (actionFlag != null) {
            setActionFlag(actionFlag);
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

        if (_histCompanyGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _histCompanyGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_histCompanyGroupDetailsRemoteModel, createdDate);
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

        if (_histCompanyGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _histCompanyGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_histCompanyGroupDetailsRemoteModel, createdBy);
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

        if (_histCompanyGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _histCompanyGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setActionDate", Date.class);

                method.invoke(_histCompanyGroupDetailsRemoteModel, actionDate);
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

        if (_histCompanyGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _histCompanyGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyParentDetailsSid",
                        String.class);

                method.invoke(_histCompanyGroupDetailsRemoteModel,
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

        if (_histCompanyGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _histCompanyGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyTradeclassSid",
                        int.class);

                method.invoke(_histCompanyGroupDetailsRemoteModel,
                    companyTradeclassSid);
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

        if (_histCompanyGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _histCompanyGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setActionFlag", String.class);

                method.invoke(_histCompanyGroupDetailsRemoteModel, actionFlag);
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

        if (_histCompanyGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _histCompanyGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyGroupSid", int.class);

                method.invoke(_histCompanyGroupDetailsRemoteModel,
                    companyGroupSid);
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

        if (_histCompanyGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _histCompanyGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setVersionNo", int.class);

                method.invoke(_histCompanyGroupDetailsRemoteModel, versionNo);
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

        if (_histCompanyGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _histCompanyGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyGroupDetailsSid",
                        int.class);

                method.invoke(_histCompanyGroupDetailsRemoteModel,
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

        if (_histCompanyGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _histCompanyGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_histCompanyGroupDetailsRemoteModel, modifiedBy);
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

        if (_histCompanyGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _histCompanyGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_histCompanyGroupDetailsRemoteModel, modifiedDate);
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

        if (_histCompanyGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _histCompanyGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyMasterSid", int.class);

                method.invoke(_histCompanyGroupDetailsRemoteModel,
                    companyMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getHistCompanyGroupDetailsRemoteModel() {
        return _histCompanyGroupDetailsRemoteModel;
    }

    public void setHistCompanyGroupDetailsRemoteModel(
        BaseModel<?> histCompanyGroupDetailsRemoteModel) {
        _histCompanyGroupDetailsRemoteModel = histCompanyGroupDetailsRemoteModel;
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

        Class<?> remoteModelClass = _histCompanyGroupDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_histCompanyGroupDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            HistCompanyGroupDetailsLocalServiceUtil.addHistCompanyGroupDetails(this);
        } else {
            HistCompanyGroupDetailsLocalServiceUtil.updateHistCompanyGroupDetails(this);
        }
    }

    @Override
    public HistCompanyGroupDetails toEscapedModel() {
        return (HistCompanyGroupDetails) ProxyUtil.newProxyInstance(HistCompanyGroupDetails.class.getClassLoader(),
            new Class[] { HistCompanyGroupDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        HistCompanyGroupDetailsClp clone = new HistCompanyGroupDetailsClp();

        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setActionDate(getActionDate());
        clone.setCompanyParentDetailsSid(getCompanyParentDetailsSid());
        clone.setCompanyTradeclassSid(getCompanyTradeclassSid());
        clone.setActionFlag(getActionFlag());
        clone.setCompanyGroupSid(getCompanyGroupSid());
        clone.setVersionNo(getVersionNo());
        clone.setCompanyGroupDetailsSid(getCompanyGroupDetailsSid());
        clone.setModifiedBy(getModifiedBy());
        clone.setModifiedDate(getModifiedDate());
        clone.setCompanyMasterSid(getCompanyMasterSid());

        return clone;
    }

    @Override
    public int compareTo(HistCompanyGroupDetails histCompanyGroupDetails) {
        HistCompanyGroupDetailsPK primaryKey = histCompanyGroupDetails.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof HistCompanyGroupDetailsClp)) {
            return false;
        }

        HistCompanyGroupDetailsClp histCompanyGroupDetails = (HistCompanyGroupDetailsClp) obj;

        HistCompanyGroupDetailsPK primaryKey = histCompanyGroupDetails.getPrimaryKey();

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

        sb.append("{createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", actionDate=");
        sb.append(getActionDate());
        sb.append(", companyParentDetailsSid=");
        sb.append(getCompanyParentDetailsSid());
        sb.append(", companyTradeclassSid=");
        sb.append(getCompanyTradeclassSid());
        sb.append(", actionFlag=");
        sb.append(getActionFlag());
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
        StringBundler sb = new StringBundler(40);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.HistCompanyGroupDetails");
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
            "<column><column-name>actionDate</column-name><column-value><![CDATA[");
        sb.append(getActionDate());
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
            "<column><column-name>actionFlag</column-name><column-value><![CDATA[");
        sb.append(getActionFlag());
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
