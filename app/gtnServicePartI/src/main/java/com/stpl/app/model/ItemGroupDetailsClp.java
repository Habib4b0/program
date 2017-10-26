package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.ItemGroupDetailsLocalServiceUtil;

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


public class ItemGroupDetailsClp extends BaseModelImpl<ItemGroupDetails>
    implements ItemGroupDetails {
    private int _itemGroupDetailsSid;
    private Date _createdDate;
    private int _createdBy;
    private int _itemMasterSid;
    private int _versionNo;
    private int _modifiedBy;
    private Date _modifiedDate;
    private int _itemGroupSid;
    private BaseModel<?> _itemGroupDetailsRemoteModel;

    public ItemGroupDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ItemGroupDetails.class;
    }

    @Override
    public String getModelClassName() {
        return ItemGroupDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _itemGroupDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setItemGroupDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _itemGroupDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemGroupDetailsSid", getItemGroupDetailsSid());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("itemMasterSid", getItemMasterSid());
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

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
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

        if (_itemGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _itemGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemGroupDetailsSid",
                        int.class);

                method.invoke(_itemGroupDetailsRemoteModel, itemGroupDetailsSid);
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

        if (_itemGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _itemGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_itemGroupDetailsRemoteModel, createdDate);
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

        if (_itemGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _itemGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_itemGroupDetailsRemoteModel, createdBy);
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

        if (_itemGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _itemGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_itemGroupDetailsRemoteModel, itemMasterSid);
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

        if (_itemGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _itemGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setVersionNo", int.class);

                method.invoke(_itemGroupDetailsRemoteModel, versionNo);
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

        if (_itemGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _itemGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_itemGroupDetailsRemoteModel, modifiedBy);
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

        if (_itemGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _itemGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_itemGroupDetailsRemoteModel, modifiedDate);
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

        if (_itemGroupDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _itemGroupDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemGroupSid", int.class);

                method.invoke(_itemGroupDetailsRemoteModel, itemGroupSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getItemGroupDetailsRemoteModel() {
        return _itemGroupDetailsRemoteModel;
    }

    public void setItemGroupDetailsRemoteModel(
        BaseModel<?> itemGroupDetailsRemoteModel) {
        _itemGroupDetailsRemoteModel = itemGroupDetailsRemoteModel;
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

        Class<?> remoteModelClass = _itemGroupDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_itemGroupDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ItemGroupDetailsLocalServiceUtil.addItemGroupDetails(this);
        } else {
            ItemGroupDetailsLocalServiceUtil.updateItemGroupDetails(this);
        }
    }

    @Override
    public ItemGroupDetails toEscapedModel() {
        return (ItemGroupDetails) ProxyUtil.newProxyInstance(ItemGroupDetails.class.getClassLoader(),
            new Class[] { ItemGroupDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ItemGroupDetailsClp clone = new ItemGroupDetailsClp();

        clone.setItemGroupDetailsSid(getItemGroupDetailsSid());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setVersionNo(getVersionNo());
        clone.setModifiedBy(getModifiedBy());
        clone.setModifiedDate(getModifiedDate());
        clone.setItemGroupSid(getItemGroupSid());

        return clone;
    }

    @Override
    public int compareTo(ItemGroupDetails itemGroupDetails) {
        int primaryKey = itemGroupDetails.getPrimaryKey();

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

        if (!(obj instanceof ItemGroupDetailsClp)) {
            return false;
        }

        ItemGroupDetailsClp itemGroupDetails = (ItemGroupDetailsClp) obj;

        int primaryKey = itemGroupDetails.getPrimaryKey();

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

        sb.append("{itemGroupDetailsSid=");
        sb.append(getItemGroupDetailsSid());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
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
        StringBundler sb = new StringBundler(28);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ItemGroupDetails");
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
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
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
