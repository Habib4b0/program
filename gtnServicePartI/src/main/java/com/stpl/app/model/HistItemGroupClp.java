package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.HistItemGroupLocalServiceUtil;
import com.stpl.app.service.persistence.HistItemGroupPK;

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


public class HistItemGroupClp extends BaseModelImpl<HistItemGroup>
    implements HistItemGroup {
    private Date _createdDate;
    private int _createdBy;
    private String _actionFlag;
    private String _itemGroupNo;
    private int _versionNo;
    private int _modifiedBy;
    private String _itemGroupDescription;
    private Date _modifiedDate;
    private String _itemGroupName;
    private int _itemGroupSid;
    private int _companyMasterSid;
    private BaseModel<?> _histItemGroupRemoteModel;

    public HistItemGroupClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return HistItemGroup.class;
    }

    @Override
    public String getModelClassName() {
        return HistItemGroup.class.getName();
    }

    @Override
    public HistItemGroupPK getPrimaryKey() {
        return new HistItemGroupPK(_actionFlag, _versionNo, _itemGroupSid);
    }

    @Override
    public void setPrimaryKey(HistItemGroupPK primaryKey) {
        setActionFlag(primaryKey.actionFlag);
        setVersionNo(primaryKey.versionNo);
        setItemGroupSid(primaryKey.itemGroupSid);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new HistItemGroupPK(_actionFlag, _versionNo, _itemGroupSid);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((HistItemGroupPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("actionFlag", getActionFlag());
        attributes.put("itemGroupNo", getItemGroupNo());
        attributes.put("versionNo", getVersionNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("itemGroupDescription", getItemGroupDescription());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("itemGroupName", getItemGroupName());
        attributes.put("itemGroupSid", getItemGroupSid());
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

        String actionFlag = (String) attributes.get("actionFlag");

        if (actionFlag != null) {
            setActionFlag(actionFlag);
        }

        String itemGroupNo = (String) attributes.get("itemGroupNo");

        if (itemGroupNo != null) {
            setItemGroupNo(itemGroupNo);
        }

        Integer versionNo = (Integer) attributes.get("versionNo");

        if (versionNo != null) {
            setVersionNo(versionNo);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String itemGroupDescription = (String) attributes.get(
                "itemGroupDescription");

        if (itemGroupDescription != null) {
            setItemGroupDescription(itemGroupDescription);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String itemGroupName = (String) attributes.get("itemGroupName");

        if (itemGroupName != null) {
            setItemGroupName(itemGroupName);
        }

        Integer itemGroupSid = (Integer) attributes.get("itemGroupSid");

        if (itemGroupSid != null) {
            setItemGroupSid(itemGroupSid);
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

        if (_histItemGroupRemoteModel != null) {
            try {
                Class<?> clazz = _histItemGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_histItemGroupRemoteModel, createdDate);
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

        if (_histItemGroupRemoteModel != null) {
            try {
                Class<?> clazz = _histItemGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_histItemGroupRemoteModel, createdBy);
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

        if (_histItemGroupRemoteModel != null) {
            try {
                Class<?> clazz = _histItemGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setActionFlag", String.class);

                method.invoke(_histItemGroupRemoteModel, actionFlag);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemGroupNo() {
        return _itemGroupNo;
    }

    @Override
    public void setItemGroupNo(String itemGroupNo) {
        _itemGroupNo = itemGroupNo;

        if (_histItemGroupRemoteModel != null) {
            try {
                Class<?> clazz = _histItemGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setItemGroupNo", String.class);

                method.invoke(_histItemGroupRemoteModel, itemGroupNo);
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

        if (_histItemGroupRemoteModel != null) {
            try {
                Class<?> clazz = _histItemGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setVersionNo", int.class);

                method.invoke(_histItemGroupRemoteModel, versionNo);
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

        if (_histItemGroupRemoteModel != null) {
            try {
                Class<?> clazz = _histItemGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_histItemGroupRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemGroupDescription() {
        return _itemGroupDescription;
    }

    @Override
    public void setItemGroupDescription(String itemGroupDescription) {
        _itemGroupDescription = itemGroupDescription;

        if (_histItemGroupRemoteModel != null) {
            try {
                Class<?> clazz = _histItemGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setItemGroupDescription",
                        String.class);

                method.invoke(_histItemGroupRemoteModel, itemGroupDescription);
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

        if (_histItemGroupRemoteModel != null) {
            try {
                Class<?> clazz = _histItemGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_histItemGroupRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemGroupName() {
        return _itemGroupName;
    }

    @Override
    public void setItemGroupName(String itemGroupName) {
        _itemGroupName = itemGroupName;

        if (_histItemGroupRemoteModel != null) {
            try {
                Class<?> clazz = _histItemGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setItemGroupName", String.class);

                method.invoke(_histItemGroupRemoteModel, itemGroupName);
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

        if (_histItemGroupRemoteModel != null) {
            try {
                Class<?> clazz = _histItemGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setItemGroupSid", int.class);

                method.invoke(_histItemGroupRemoteModel, itemGroupSid);
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

        if (_histItemGroupRemoteModel != null) {
            try {
                Class<?> clazz = _histItemGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyMasterSid", int.class);

                method.invoke(_histItemGroupRemoteModel, companyMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getHistItemGroupRemoteModel() {
        return _histItemGroupRemoteModel;
    }

    public void setHistItemGroupRemoteModel(
        BaseModel<?> histItemGroupRemoteModel) {
        _histItemGroupRemoteModel = histItemGroupRemoteModel;
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

        Class<?> remoteModelClass = _histItemGroupRemoteModel.getClass();

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

        Object returnValue = method.invoke(_histItemGroupRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            HistItemGroupLocalServiceUtil.addHistItemGroup(this);
        } else {
            HistItemGroupLocalServiceUtil.updateHistItemGroup(this);
        }
    }

    @Override
    public HistItemGroup toEscapedModel() {
        return (HistItemGroup) ProxyUtil.newProxyInstance(HistItemGroup.class.getClassLoader(),
            new Class[] { HistItemGroup.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        HistItemGroupClp clone = new HistItemGroupClp();

        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setActionFlag(getActionFlag());
        clone.setItemGroupNo(getItemGroupNo());
        clone.setVersionNo(getVersionNo());
        clone.setModifiedBy(getModifiedBy());
        clone.setItemGroupDescription(getItemGroupDescription());
        clone.setModifiedDate(getModifiedDate());
        clone.setItemGroupName(getItemGroupName());
        clone.setItemGroupSid(getItemGroupSid());
        clone.setCompanyMasterSid(getCompanyMasterSid());

        return clone;
    }

    @Override
    public int compareTo(HistItemGroup histItemGroup) {
        HistItemGroupPK primaryKey = histItemGroup.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof HistItemGroupClp)) {
            return false;
        }

        HistItemGroupClp histItemGroup = (HistItemGroupClp) obj;

        HistItemGroupPK primaryKey = histItemGroup.getPrimaryKey();

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
        StringBundler sb = new StringBundler(23);

        sb.append("{createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", actionFlag=");
        sb.append(getActionFlag());
        sb.append(", itemGroupNo=");
        sb.append(getItemGroupNo());
        sb.append(", versionNo=");
        sb.append(getVersionNo());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", itemGroupDescription=");
        sb.append(getItemGroupDescription());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", itemGroupName=");
        sb.append(getItemGroupName());
        sb.append(", itemGroupSid=");
        sb.append(getItemGroupSid());
        sb.append(", companyMasterSid=");
        sb.append(getCompanyMasterSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(37);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.HistItemGroup");
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
            "<column><column-name>actionFlag</column-name><column-value><![CDATA[");
        sb.append(getActionFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemGroupNo</column-name><column-value><![CDATA[");
        sb.append(getItemGroupNo());
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
            "<column><column-name>itemGroupDescription</column-name><column-value><![CDATA[");
        sb.append(getItemGroupDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemGroupName</column-name><column-value><![CDATA[");
        sb.append(getItemGroupName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemGroupSid</column-name><column-value><![CDATA[");
        sb.append(getItemGroupSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyMasterSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
