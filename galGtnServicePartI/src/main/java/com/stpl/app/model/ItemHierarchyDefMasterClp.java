package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.ItemHierarchyDefMasterLocalServiceUtil;

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


public class ItemHierarchyDefMasterClp extends BaseModelImpl<ItemHierarchyDefMaster>
    implements ItemHierarchyDefMaster {
    private int _itemHierarchyDefMasterSid;
    private int _createdBy;
    private boolean _recordLockStatus;
    private int _modifiedBy;
    private Date _createdDate;
    private String _alias;
    private String _source;
    private String _batchId;
    private String _bpiLvl;
    private Date _modifiedDate;
    private String _member;
    private String _inboundStatus;
    private BaseModel<?> _itemHierarchyDefMasterRemoteModel;

    public ItemHierarchyDefMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ItemHierarchyDefMaster.class;
    }

    @Override
    public String getModelClassName() {
        return ItemHierarchyDefMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _itemHierarchyDefMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setItemHierarchyDefMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _itemHierarchyDefMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemHierarchyDefMasterSid",
            getItemHierarchyDefMasterSid());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("alias", getAlias());
        attributes.put("source", getSource());
        attributes.put("batchId", getBatchId());
        attributes.put("bpiLvl", getBpiLvl());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("member", getMember());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer itemHierarchyDefMasterSid = (Integer) attributes.get(
                "itemHierarchyDefMasterSid");

        if (itemHierarchyDefMasterSid != null) {
            setItemHierarchyDefMasterSid(itemHierarchyDefMasterSid);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String alias = (String) attributes.get("alias");

        if (alias != null) {
            setAlias(alias);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String bpiLvl = (String) attributes.get("bpiLvl");

        if (bpiLvl != null) {
            setBpiLvl(bpiLvl);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String member = (String) attributes.get("member");

        if (member != null) {
            setMember(member);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }
    }

    @Override
    public int getItemHierarchyDefMasterSid() {
        return _itemHierarchyDefMasterSid;
    }

    @Override
    public void setItemHierarchyDefMasterSid(int itemHierarchyDefMasterSid) {
        _itemHierarchyDefMasterSid = itemHierarchyDefMasterSid;

        if (_itemHierarchyDefMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyDefMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemHierarchyDefMasterSid",
                        int.class);

                method.invoke(_itemHierarchyDefMasterRemoteModel,
                    itemHierarchyDefMasterSid);
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

        if (_itemHierarchyDefMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyDefMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_itemHierarchyDefMasterRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getRecordLockStatus() {
        return _recordLockStatus;
    }

    @Override
    public boolean isRecordLockStatus() {
        return _recordLockStatus;
    }

    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _recordLockStatus = recordLockStatus;

        if (_itemHierarchyDefMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyDefMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_itemHierarchyDefMasterRemoteModel,
                    recordLockStatus);
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

        if (_itemHierarchyDefMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyDefMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_itemHierarchyDefMasterRemoteModel, modifiedBy);
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

        if (_itemHierarchyDefMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyDefMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_itemHierarchyDefMasterRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAlias() {
        return _alias;
    }

    @Override
    public void setAlias(String alias) {
        _alias = alias;

        if (_itemHierarchyDefMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyDefMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAlias", String.class);

                method.invoke(_itemHierarchyDefMasterRemoteModel, alias);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSource() {
        return _source;
    }

    @Override
    public void setSource(String source) {
        _source = source;

        if (_itemHierarchyDefMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyDefMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_itemHierarchyDefMasterRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBatchId() {
        return _batchId;
    }

    @Override
    public void setBatchId(String batchId) {
        _batchId = batchId;

        if (_itemHierarchyDefMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyDefMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_itemHierarchyDefMasterRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBpiLvl() {
        return _bpiLvl;
    }

    @Override
    public void setBpiLvl(String bpiLvl) {
        _bpiLvl = bpiLvl;

        if (_itemHierarchyDefMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyDefMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBpiLvl", String.class);

                method.invoke(_itemHierarchyDefMasterRemoteModel, bpiLvl);
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

        if (_itemHierarchyDefMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyDefMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_itemHierarchyDefMasterRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getMember() {
        return _member;
    }

    @Override
    public void setMember(String member) {
        _member = member;

        if (_itemHierarchyDefMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyDefMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setMember", String.class);

                method.invoke(_itemHierarchyDefMasterRemoteModel, member);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getInboundStatus() {
        return _inboundStatus;
    }

    @Override
    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;

        if (_itemHierarchyDefMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemHierarchyDefMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_itemHierarchyDefMasterRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getItemHierarchyDefMasterRemoteModel() {
        return _itemHierarchyDefMasterRemoteModel;
    }

    public void setItemHierarchyDefMasterRemoteModel(
        BaseModel<?> itemHierarchyDefMasterRemoteModel) {
        _itemHierarchyDefMasterRemoteModel = itemHierarchyDefMasterRemoteModel;
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

        Class<?> remoteModelClass = _itemHierarchyDefMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_itemHierarchyDefMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ItemHierarchyDefMasterLocalServiceUtil.addItemHierarchyDefMaster(this);
        } else {
            ItemHierarchyDefMasterLocalServiceUtil.updateItemHierarchyDefMaster(this);
        }
    }

    @Override
    public ItemHierarchyDefMaster toEscapedModel() {
        return (ItemHierarchyDefMaster) ProxyUtil.newProxyInstance(ItemHierarchyDefMaster.class.getClassLoader(),
            new Class[] { ItemHierarchyDefMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ItemHierarchyDefMasterClp clone = new ItemHierarchyDefMasterClp();

        clone.setItemHierarchyDefMasterSid(getItemHierarchyDefMasterSid());
        clone.setCreatedBy(getCreatedBy());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setModifiedBy(getModifiedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setAlias(getAlias());
        clone.setSource(getSource());
        clone.setBatchId(getBatchId());
        clone.setBpiLvl(getBpiLvl());
        clone.setModifiedDate(getModifiedDate());
        clone.setMember(getMember());
        clone.setInboundStatus(getInboundStatus());

        return clone;
    }

    @Override
    public int compareTo(ItemHierarchyDefMaster itemHierarchyDefMaster) {
        int primaryKey = itemHierarchyDefMaster.getPrimaryKey();

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

        if (!(obj instanceof ItemHierarchyDefMasterClp)) {
            return false;
        }

        ItemHierarchyDefMasterClp itemHierarchyDefMaster = (ItemHierarchyDefMasterClp) obj;

        int primaryKey = itemHierarchyDefMaster.getPrimaryKey();

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
        StringBundler sb = new StringBundler(25);

        sb.append("{itemHierarchyDefMasterSid=");
        sb.append(getItemHierarchyDefMasterSid());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", alias=");
        sb.append(getAlias());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", bpiLvl=");
        sb.append(getBpiLvl());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", member=");
        sb.append(getMember());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(40);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ItemHierarchyDefMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>itemHierarchyDefMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemHierarchyDefMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>alias</column-name><column-value><![CDATA[");
        sb.append(getAlias());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>bpiLvl</column-name><column-value><![CDATA[");
        sb.append(getBpiLvl());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>member</column-name><column-value><![CDATA[");
        sb.append(getMember());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
