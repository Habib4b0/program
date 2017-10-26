package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.ItemGroupLocalServiceUtil;

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


public class ItemGroupClp extends BaseModelImpl<ItemGroup> implements ItemGroup {
    private Date _createdDate;
    private int _createdBy;
    private String _itemGroupNo;
    private int _versionNo;
    private String _itemFilter;
    private int _modifiedBy;
    private String _itemGroupDescription;
    private Date _modifiedDate;
    private String _itemGroupName;
    private int _itemGroupSid;
    private int _companyMasterSid;
    private BaseModel<?> _itemGroupRemoteModel;

    public ItemGroupClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ItemGroup.class;
    }

    @Override
    public String getModelClassName() {
        return ItemGroup.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _itemGroupSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setItemGroupSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _itemGroupSid;
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
        attributes.put("itemGroupNo", getItemGroupNo());
        attributes.put("versionNo", getVersionNo());
        attributes.put("itemFilter", getItemFilter());
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

        String itemGroupNo = (String) attributes.get("itemGroupNo");

        if (itemGroupNo != null) {
            setItemGroupNo(itemGroupNo);
        }

        Integer versionNo = (Integer) attributes.get("versionNo");

        if (versionNo != null) {
            setVersionNo(versionNo);
        }

        String itemFilter = (String) attributes.get("itemFilter");

        if (itemFilter != null) {
            setItemFilter(itemFilter);
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

        if (_itemGroupRemoteModel != null) {
            try {
                Class<?> clazz = _itemGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_itemGroupRemoteModel, createdDate);
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

        if (_itemGroupRemoteModel != null) {
            try {
                Class<?> clazz = _itemGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_itemGroupRemoteModel, createdBy);
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

        if (_itemGroupRemoteModel != null) {
            try {
                Class<?> clazz = _itemGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setItemGroupNo", String.class);

                method.invoke(_itemGroupRemoteModel, itemGroupNo);
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

        if (_itemGroupRemoteModel != null) {
            try {
                Class<?> clazz = _itemGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setVersionNo", int.class);

                method.invoke(_itemGroupRemoteModel, versionNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemFilter() {
        return _itemFilter;
    }

    @Override
    public void setItemFilter(String itemFilter) {
        _itemFilter = itemFilter;

        if (_itemGroupRemoteModel != null) {
            try {
                Class<?> clazz = _itemGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setItemFilter", String.class);

                method.invoke(_itemGroupRemoteModel, itemFilter);
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

        if (_itemGroupRemoteModel != null) {
            try {
                Class<?> clazz = _itemGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_itemGroupRemoteModel, modifiedBy);
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

        if (_itemGroupRemoteModel != null) {
            try {
                Class<?> clazz = _itemGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setItemGroupDescription",
                        String.class);

                method.invoke(_itemGroupRemoteModel, itemGroupDescription);
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

        if (_itemGroupRemoteModel != null) {
            try {
                Class<?> clazz = _itemGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_itemGroupRemoteModel, modifiedDate);
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

        if (_itemGroupRemoteModel != null) {
            try {
                Class<?> clazz = _itemGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setItemGroupName", String.class);

                method.invoke(_itemGroupRemoteModel, itemGroupName);
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

        if (_itemGroupRemoteModel != null) {
            try {
                Class<?> clazz = _itemGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setItemGroupSid", int.class);

                method.invoke(_itemGroupRemoteModel, itemGroupSid);
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

        if (_itemGroupRemoteModel != null) {
            try {
                Class<?> clazz = _itemGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyMasterSid", int.class);

                method.invoke(_itemGroupRemoteModel, companyMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getItemGroupRemoteModel() {
        return _itemGroupRemoteModel;
    }

    public void setItemGroupRemoteModel(BaseModel<?> itemGroupRemoteModel) {
        _itemGroupRemoteModel = itemGroupRemoteModel;
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

        Class<?> remoteModelClass = _itemGroupRemoteModel.getClass();

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

        Object returnValue = method.invoke(_itemGroupRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ItemGroupLocalServiceUtil.addItemGroup(this);
        } else {
            ItemGroupLocalServiceUtil.updateItemGroup(this);
        }
    }

    @Override
    public ItemGroup toEscapedModel() {
        return (ItemGroup) ProxyUtil.newProxyInstance(ItemGroup.class.getClassLoader(),
            new Class[] { ItemGroup.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ItemGroupClp clone = new ItemGroupClp();

        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setItemGroupNo(getItemGroupNo());
        clone.setVersionNo(getVersionNo());
        clone.setItemFilter(getItemFilter());
        clone.setModifiedBy(getModifiedBy());
        clone.setItemGroupDescription(getItemGroupDescription());
        clone.setModifiedDate(getModifiedDate());
        clone.setItemGroupName(getItemGroupName());
        clone.setItemGroupSid(getItemGroupSid());
        clone.setCompanyMasterSid(getCompanyMasterSid());

        return clone;
    }

    @Override
    public int compareTo(ItemGroup itemGroup) {
        int primaryKey = itemGroup.getPrimaryKey();

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

        if (!(obj instanceof ItemGroupClp)) {
            return false;
        }

        ItemGroupClp itemGroup = (ItemGroupClp) obj;

        int primaryKey = itemGroup.getPrimaryKey();

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

        sb.append("{createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", itemGroupNo=");
        sb.append(getItemGroupNo());
        sb.append(", versionNo=");
        sb.append(getVersionNo());
        sb.append(", itemFilter=");
        sb.append(getItemFilter());
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
        sb.append("com.stpl.app.model.ItemGroup");
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
            "<column><column-name>itemGroupNo</column-name><column-value><![CDATA[");
        sb.append(getItemGroupNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>versionNo</column-name><column-value><![CDATA[");
        sb.append(getVersionNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemFilter</column-name><column-value><![CDATA[");
        sb.append(getItemFilter());
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
