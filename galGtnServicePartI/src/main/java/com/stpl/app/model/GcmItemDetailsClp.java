package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.GcmItemDetailsLocalServiceUtil;

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


public class GcmItemDetailsClp extends BaseModelImpl<GcmItemDetails>
    implements GcmItemDetails {
    private Date _ifpDetailsEndDate;
    private String _itemStatus;
    private boolean _checkRecord;
    private Date _ifpDetailsStartDate;
    private int _userId;
    private int _itemMasterSid;
    private Date _itemEndDate;
    private int _gcmItemDetailsSid;
    private int _itemIfpDetailsSid;
    private String _itemId;
    private String _brandName;
    private Date _modifiedDate;
    private Date _createdDate;
    private int _createdBy;
    private Date _itemStartDate;
    private String _sessionId;
    private String _itemName;
    private String _operation;
    private int _modifiedBy;
    private String _inboundStatus;
    private int _itemStatusSid;
    private String _itemNo;
    private int _ifpModelSid;
    private String _theraputicClass;
    private BaseModel<?> _gcmItemDetailsRemoteModel;

    public GcmItemDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return GcmItemDetails.class;
    }

    @Override
    public String getModelClassName() {
        return GcmItemDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _gcmItemDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setGcmItemDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _gcmItemDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("ifpDetailsEndDate", getIfpDetailsEndDate());
        attributes.put("itemStatus", getItemStatus());
        attributes.put("checkRecord", getCheckRecord());
        attributes.put("ifpDetailsStartDate", getIfpDetailsStartDate());
        attributes.put("userId", getUserId());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("itemEndDate", getItemEndDate());
        attributes.put("gcmItemDetailsSid", getGcmItemDetailsSid());
        attributes.put("itemIfpDetailsSid", getItemIfpDetailsSid());
        attributes.put("itemId", getItemId());
        attributes.put("brandName", getBrandName());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("itemStartDate", getItemStartDate());
        attributes.put("sessionId", getSessionId());
        attributes.put("itemName", getItemName());
        attributes.put("operation", getOperation());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("itemStatusSid", getItemStatusSid());
        attributes.put("itemNo", getItemNo());
        attributes.put("ifpModelSid", getIfpModelSid());
        attributes.put("theraputicClass", getTheraputicClass());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date ifpDetailsEndDate = (Date) attributes.get("ifpDetailsEndDate");

        if (ifpDetailsEndDate != null) {
            setIfpDetailsEndDate(ifpDetailsEndDate);
        }

        String itemStatus = (String) attributes.get("itemStatus");

        if (itemStatus != null) {
            setItemStatus(itemStatus);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        Date ifpDetailsStartDate = (Date) attributes.get("ifpDetailsStartDate");

        if (ifpDetailsStartDate != null) {
            setIfpDetailsStartDate(ifpDetailsStartDate);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Date itemEndDate = (Date) attributes.get("itemEndDate");

        if (itemEndDate != null) {
            setItemEndDate(itemEndDate);
        }

        Integer gcmItemDetailsSid = (Integer) attributes.get(
                "gcmItemDetailsSid");

        if (gcmItemDetailsSid != null) {
            setGcmItemDetailsSid(gcmItemDetailsSid);
        }

        Integer itemIfpDetailsSid = (Integer) attributes.get(
                "itemIfpDetailsSid");

        if (itemIfpDetailsSid != null) {
            setItemIfpDetailsSid(itemIfpDetailsSid);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String brandName = (String) attributes.get("brandName");

        if (brandName != null) {
            setBrandName(brandName);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date itemStartDate = (Date) attributes.get("itemStartDate");

        if (itemStartDate != null) {
            setItemStartDate(itemStartDate);
        }

        String sessionId = (String) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        String operation = (String) attributes.get("operation");

        if (operation != null) {
            setOperation(operation);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Integer itemStatusSid = (Integer) attributes.get("itemStatusSid");

        if (itemStatusSid != null) {
            setItemStatusSid(itemStatusSid);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        Integer ifpModelSid = (Integer) attributes.get("ifpModelSid");

        if (ifpModelSid != null) {
            setIfpModelSid(ifpModelSid);
        }

        String theraputicClass = (String) attributes.get("theraputicClass");

        if (theraputicClass != null) {
            setTheraputicClass(theraputicClass);
        }
    }

    @Override
    public Date getIfpDetailsEndDate() {
        return _ifpDetailsEndDate;
    }

    @Override
    public void setIfpDetailsEndDate(Date ifpDetailsEndDate) {
        _ifpDetailsEndDate = ifpDetailsEndDate;

        if (_gcmItemDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmItemDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpDetailsEndDate",
                        Date.class);

                method.invoke(_gcmItemDetailsRemoteModel, ifpDetailsEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemStatus() {
        return _itemStatus;
    }

    @Override
    public void setItemStatus(String itemStatus) {
        _itemStatus = itemStatus;

        if (_gcmItemDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmItemDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemStatus", String.class);

                method.invoke(_gcmItemDetailsRemoteModel, itemStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getCheckRecord() {
        return _checkRecord;
    }

    @Override
    public boolean isCheckRecord() {
        return _checkRecord;
    }

    @Override
    public void setCheckRecord(boolean checkRecord) {
        _checkRecord = checkRecord;

        if (_gcmItemDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmItemDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_gcmItemDetailsRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getIfpDetailsStartDate() {
        return _ifpDetailsStartDate;
    }

    @Override
    public void setIfpDetailsStartDate(Date ifpDetailsStartDate) {
        _ifpDetailsStartDate = ifpDetailsStartDate;

        if (_gcmItemDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmItemDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpDetailsStartDate",
                        Date.class);

                method.invoke(_gcmItemDetailsRemoteModel, ifpDetailsStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getUserId() {
        return _userId;
    }

    @Override
    public void setUserId(int userId) {
        _userId = userId;

        if (_gcmItemDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmItemDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", int.class);

                method.invoke(_gcmItemDetailsRemoteModel, userId);
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

        if (_gcmItemDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmItemDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_gcmItemDetailsRemoteModel, itemMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getItemEndDate() {
        return _itemEndDate;
    }

    @Override
    public void setItemEndDate(Date itemEndDate) {
        _itemEndDate = itemEndDate;

        if (_gcmItemDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmItemDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemEndDate", Date.class);

                method.invoke(_gcmItemDetailsRemoteModel, itemEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getGcmItemDetailsSid() {
        return _gcmItemDetailsSid;
    }

    @Override
    public void setGcmItemDetailsSid(int gcmItemDetailsSid) {
        _gcmItemDetailsSid = gcmItemDetailsSid;

        if (_gcmItemDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmItemDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setGcmItemDetailsSid",
                        int.class);

                method.invoke(_gcmItemDetailsRemoteModel, gcmItemDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getItemIfpDetailsSid() {
        return _itemIfpDetailsSid;
    }

    @Override
    public void setItemIfpDetailsSid(int itemIfpDetailsSid) {
        _itemIfpDetailsSid = itemIfpDetailsSid;

        if (_gcmItemDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmItemDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemIfpDetailsSid",
                        int.class);

                method.invoke(_gcmItemDetailsRemoteModel, itemIfpDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemId() {
        return _itemId;
    }

    @Override
    public void setItemId(String itemId) {
        _itemId = itemId;

        if (_gcmItemDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmItemDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_gcmItemDetailsRemoteModel, itemId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBrandName() {
        return _brandName;
    }

    @Override
    public void setBrandName(String brandName) {
        _brandName = brandName;

        if (_gcmItemDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmItemDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandName", String.class);

                method.invoke(_gcmItemDetailsRemoteModel, brandName);
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

        if (_gcmItemDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmItemDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_gcmItemDetailsRemoteModel, modifiedDate);
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

        if (_gcmItemDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmItemDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_gcmItemDetailsRemoteModel, createdDate);
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

        if (_gcmItemDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmItemDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_gcmItemDetailsRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getItemStartDate() {
        return _itemStartDate;
    }

    @Override
    public void setItemStartDate(Date itemStartDate) {
        _itemStartDate = itemStartDate;

        if (_gcmItemDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmItemDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemStartDate", Date.class);

                method.invoke(_gcmItemDetailsRemoteModel, itemStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSessionId() {
        return _sessionId;
    }

    @Override
    public void setSessionId(String sessionId) {
        _sessionId = sessionId;

        if (_gcmItemDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmItemDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", String.class);

                method.invoke(_gcmItemDetailsRemoteModel, sessionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemName() {
        return _itemName;
    }

    @Override
    public void setItemName(String itemName) {
        _itemName = itemName;

        if (_gcmItemDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmItemDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemName", String.class);

                method.invoke(_gcmItemDetailsRemoteModel, itemName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getOperation() {
        return _operation;
    }

    @Override
    public void setOperation(String operation) {
        _operation = operation;

        if (_gcmItemDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmItemDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setOperation", String.class);

                method.invoke(_gcmItemDetailsRemoteModel, operation);
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

        if (_gcmItemDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmItemDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_gcmItemDetailsRemoteModel, modifiedBy);
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

        if (_gcmItemDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmItemDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_gcmItemDetailsRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getItemStatusSid() {
        return _itemStatusSid;
    }

    @Override
    public void setItemStatusSid(int itemStatusSid) {
        _itemStatusSid = itemStatusSid;

        if (_gcmItemDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmItemDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemStatusSid", int.class);

                method.invoke(_gcmItemDetailsRemoteModel, itemStatusSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemNo() {
        return _itemNo;
    }

    @Override
    public void setItemNo(String itemNo) {
        _itemNo = itemNo;

        if (_gcmItemDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmItemDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemNo", String.class);

                method.invoke(_gcmItemDetailsRemoteModel, itemNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIfpModelSid() {
        return _ifpModelSid;
    }

    @Override
    public void setIfpModelSid(int ifpModelSid) {
        _ifpModelSid = ifpModelSid;

        if (_gcmItemDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmItemDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpModelSid", int.class);

                method.invoke(_gcmItemDetailsRemoteModel, ifpModelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getTheraputicClass() {
        return _theraputicClass;
    }

    @Override
    public void setTheraputicClass(String theraputicClass) {
        _theraputicClass = theraputicClass;

        if (_gcmItemDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmItemDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setTheraputicClass",
                        String.class);

                method.invoke(_gcmItemDetailsRemoteModel, theraputicClass);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getGcmItemDetailsRemoteModel() {
        return _gcmItemDetailsRemoteModel;
    }

    public void setGcmItemDetailsRemoteModel(
        BaseModel<?> gcmItemDetailsRemoteModel) {
        _gcmItemDetailsRemoteModel = gcmItemDetailsRemoteModel;
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

        Class<?> remoteModelClass = _gcmItemDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_gcmItemDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            GcmItemDetailsLocalServiceUtil.addGcmItemDetails(this);
        } else {
            GcmItemDetailsLocalServiceUtil.updateGcmItemDetails(this);
        }
    }

    @Override
    public GcmItemDetails toEscapedModel() {
        return (GcmItemDetails) ProxyUtil.newProxyInstance(GcmItemDetails.class.getClassLoader(),
            new Class[] { GcmItemDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        GcmItemDetailsClp clone = new GcmItemDetailsClp();

        clone.setIfpDetailsEndDate(getIfpDetailsEndDate());
        clone.setItemStatus(getItemStatus());
        clone.setCheckRecord(getCheckRecord());
        clone.setIfpDetailsStartDate(getIfpDetailsStartDate());
        clone.setUserId(getUserId());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setItemEndDate(getItemEndDate());
        clone.setGcmItemDetailsSid(getGcmItemDetailsSid());
        clone.setItemIfpDetailsSid(getItemIfpDetailsSid());
        clone.setItemId(getItemId());
        clone.setBrandName(getBrandName());
        clone.setModifiedDate(getModifiedDate());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setItemStartDate(getItemStartDate());
        clone.setSessionId(getSessionId());
        clone.setItemName(getItemName());
        clone.setOperation(getOperation());
        clone.setModifiedBy(getModifiedBy());
        clone.setInboundStatus(getInboundStatus());
        clone.setItemStatusSid(getItemStatusSid());
        clone.setItemNo(getItemNo());
        clone.setIfpModelSid(getIfpModelSid());
        clone.setTheraputicClass(getTheraputicClass());

        return clone;
    }

    @Override
    public int compareTo(GcmItemDetails gcmItemDetails) {
        int primaryKey = gcmItemDetails.getPrimaryKey();

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

        if (!(obj instanceof GcmItemDetailsClp)) {
            return false;
        }

        GcmItemDetailsClp gcmItemDetails = (GcmItemDetailsClp) obj;

        int primaryKey = gcmItemDetails.getPrimaryKey();

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
        StringBundler sb = new StringBundler(49);

        sb.append("{ifpDetailsEndDate=");
        sb.append(getIfpDetailsEndDate());
        sb.append(", itemStatus=");
        sb.append(getItemStatus());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append(", ifpDetailsStartDate=");
        sb.append(getIfpDetailsStartDate());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", itemEndDate=");
        sb.append(getItemEndDate());
        sb.append(", gcmItemDetailsSid=");
        sb.append(getGcmItemDetailsSid());
        sb.append(", itemIfpDetailsSid=");
        sb.append(getItemIfpDetailsSid());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", brandName=");
        sb.append(getBrandName());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", itemStartDate=");
        sb.append(getItemStartDate());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append(", itemName=");
        sb.append(getItemName());
        sb.append(", operation=");
        sb.append(getOperation());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", itemStatusSid=");
        sb.append(getItemStatusSid());
        sb.append(", itemNo=");
        sb.append(getItemNo());
        sb.append(", ifpModelSid=");
        sb.append(getIfpModelSid());
        sb.append(", theraputicClass=");
        sb.append(getTheraputicClass());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(76);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.GcmItemDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>ifpDetailsEndDate</column-name><column-value><![CDATA[");
        sb.append(getIfpDetailsEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemStatus</column-name><column-value><![CDATA[");
        sb.append(getItemStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpDetailsStartDate</column-name><column-value><![CDATA[");
        sb.append(getIfpDetailsStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemEndDate</column-name><column-value><![CDATA[");
        sb.append(getItemEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>gcmItemDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getGcmItemDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemIfpDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getItemIfpDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemId</column-name><column-value><![CDATA[");
        sb.append(getItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brandName</column-name><column-value><![CDATA[");
        sb.append(getBrandName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
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
            "<column><column-name>itemStartDate</column-name><column-value><![CDATA[");
        sb.append(getItemStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sessionId</column-name><column-value><![CDATA[");
        sb.append(getSessionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemName</column-name><column-value><![CDATA[");
        sb.append(getItemName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>operation</column-name><column-value><![CDATA[");
        sb.append(getOperation());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemStatusSid</column-name><column-value><![CDATA[");
        sb.append(getItemStatusSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemNo</column-name><column-value><![CDATA[");
        sb.append(getItemNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpModelSid</column-name><column-value><![CDATA[");
        sb.append(getIfpModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>theraputicClass</column-name><column-value><![CDATA[");
        sb.append(getTheraputicClass());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
