package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.HistItemGroupDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.HistItemGroupDetailsPK;

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


public class HistItemGroupDetailsClp extends BaseModelImpl<HistItemGroupDetails>
    implements HistItemGroupDetails {
    private int _itemGroupDetailsSid;
    private Date _createdDate;
    private int _createdBy;
    private Date _actionDate;
    private int _itemMasterSid;
    private String _actionFlag;
    private int _versionNo;
    private int _modifiedBy;
    private Date _modifiedDate;
    private int _itemGroupSid;
    private BaseModel<?> _histItemGroupDetailsRemoteModel;

    public HistItemGroupDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return HistItemGroupDetails.class;
    }

    @Override
    public String getModelClassName() {
        return HistItemGroupDetails.class.getName();
    }

    @Override
    public HistItemGroupDetailsPK getPrimaryKey() {
        return new HistItemGroupDetailsPK(_itemGroupDetailsSid, _actionFlag,
            _versionNo);
    }

    @Override
    public void setPrimaryKey(HistItemGroupDetailsPK primaryKey) {
        setItemGroupDetailsSid(primaryKey.itemGroupDetailsSid);
        setActionFlag(primaryKey.actionFlag);
        setVersionNo(primaryKey.versionNo);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new HistItemGroupDetailsPK(_itemGroupDetailsSid, _actionFlag,
            _versionNo);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((HistItemGroupDetailsPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemGroupDetailsSid", getItemGroupDetailsSid());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("actionDate", getActionDate());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("actionFlag", getActionFlag());
        attributes.put("versionNo", getVersionNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("itemGroupSid", getItemGroupSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer itemGroupDetailsSid = (Integer) attributes.get(
                "itemGroupDetailsSid");

        if (itemGroupDetailsSid != null) {
            setItemGroupDetailsSid(itemGroupDetailsSid);
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

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        String actionFlag = (String) attributes.get("actionFlag");

        if (actionFlag != null) {
            setActionFlag(actionFlag);
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

        Integer itemGroupSid = (Integer) attributes.get("itemGroupSid");

        if (itemGroupSid != null) {
            setItemGroupSid(itemGroupSid);
        }
    }

    @Override
    public int getItemGroupDetailsSid() {
        return _itemGroupDetailsSid;
    }

    @Override
    public void setItemGroupDetailsSid(int itemGroupDetailsSid) {
        _itemGroupDetailsSid = itemGroupDetailsSid;

        if (_histItemGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _histItemGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemGroupDetailsSid",
                        int.class);

                method.invoke(_histItemGroupDetailsRemoteModel,
                    itemGroupDetailsSid);
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

        if (_histItemGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _histItemGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_histItemGroupDetailsRemoteModel, createdDate);
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

        if (_histItemGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _histItemGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_histItemGroupDetailsRemoteModel, createdBy);
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

        if (_histItemGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _histItemGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setActionDate", Date.class);

                method.invoke(_histItemGroupDetailsRemoteModel, actionDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getItemMasterSid() {
        return _itemMasterSid;
    }

    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _itemMasterSid = itemMasterSid;

        if (_histItemGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _histItemGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_histItemGroupDetailsRemoteModel, itemMasterSid);
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

        if (_histItemGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _histItemGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setActionFlag", String.class);

                method.invoke(_histItemGroupDetailsRemoteModel, actionFlag);
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

        if (_histItemGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _histItemGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setVersionNo", int.class);

                method.invoke(_histItemGroupDetailsRemoteModel, versionNo);
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

        if (_histItemGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _histItemGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_histItemGroupDetailsRemoteModel, modifiedBy);
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

        if (_histItemGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _histItemGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_histItemGroupDetailsRemoteModel, modifiedDate);
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

        if (_histItemGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _histItemGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemGroupSid", int.class);

                method.invoke(_histItemGroupDetailsRemoteModel, itemGroupSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getHistItemGroupDetailsRemoteModel() {
        return _histItemGroupDetailsRemoteModel;
    }

    public void setHistItemGroupDetailsRemoteModel(
        BaseModel<?> histItemGroupDetailsRemoteModel) {
        _histItemGroupDetailsRemoteModel = histItemGroupDetailsRemoteModel;
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

        Class<?> remoteModelClass = _histItemGroupDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_histItemGroupDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            HistItemGroupDetailsLocalServiceUtil.addHistItemGroupDetails(this);
        } else {
            HistItemGroupDetailsLocalServiceUtil.updateHistItemGroupDetails(this);
        }
    }

    @Override
    public HistItemGroupDetails toEscapedModel() {
        return (HistItemGroupDetails) ProxyUtil.newProxyInstance(HistItemGroupDetails.class.getClassLoader(),
            new Class[] { HistItemGroupDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        HistItemGroupDetailsClp clone = new HistItemGroupDetailsClp();

        clone.setItemGroupDetailsSid(getItemGroupDetailsSid());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setActionDate(getActionDate());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setActionFlag(getActionFlag());
        clone.setVersionNo(getVersionNo());
        clone.setModifiedBy(getModifiedBy());
        clone.setModifiedDate(getModifiedDate());
        clone.setItemGroupSid(getItemGroupSid());

        return clone;
    }

    @Override
    public int compareTo(HistItemGroupDetails histItemGroupDetails) {
        HistItemGroupDetailsPK primaryKey = histItemGroupDetails.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof HistItemGroupDetailsClp)) {
            return false;
        }

        HistItemGroupDetailsClp histItemGroupDetails = (HistItemGroupDetailsClp) obj;

        HistItemGroupDetailsPK primaryKey = histItemGroupDetails.getPrimaryKey();

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
        StringBundler sb = new StringBundler(21);

        sb.append("{itemGroupDetailsSid=");
        sb.append(getItemGroupDetailsSid());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", actionDate=");
        sb.append(getActionDate());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", actionFlag=");
        sb.append(getActionFlag());
        sb.append(", versionNo=");
        sb.append(getVersionNo());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", itemGroupSid=");
        sb.append(getItemGroupSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(34);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.HistItemGroupDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>itemGroupDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getItemGroupDetailsSid());
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
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actionFlag</column-name><column-value><![CDATA[");
        sb.append(getActionFlag());
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
            "<column><column-name>itemGroupSid</column-name><column-value><![CDATA[");
        sb.append(getItemGroupSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
