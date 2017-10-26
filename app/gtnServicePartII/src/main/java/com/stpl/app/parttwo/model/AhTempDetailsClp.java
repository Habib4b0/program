package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.AhTempDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.ClpSerializer;

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


public class AhTempDetailsClp extends BaseModelImpl<AhTempDetails>
    implements AhTempDetails {
    private boolean _checkRecord;
    private String _contractHolder;
    private String _userId;
    private int _itemMasterSid;
    private String _businessUnitNo;
    private String _companyName;
    private String _itemId;
    private String _brandName;
    private String _componentName;
    private Date _createdDate;
    private String _createdBy;
    private String _screenName;
    private String _businessUnitName;
    private String _companyNo;
    private String _itemIdentifierType;
    private String _componentNo;
    private String _sessionId;
    private String _itemName;
    private String _itemIdentifier;
    private int _companySid;
    private String _itemNo;
    private String _componentType;
    private String _theraputicClass;
    private int _componentMasterSid;
    private BaseModel<?> _ahTempDetailsRemoteModel;

    public AhTempDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return AhTempDetails.class;
    }

    @Override
    public String getModelClassName() {
        return AhTempDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _componentMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setComponentMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _componentMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("checkRecord", getCheckRecord());
        attributes.put("contractHolder", getContractHolder());
        attributes.put("userId", getUserId());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("businessUnitNo", getBusinessUnitNo());
        attributes.put("companyName", getCompanyName());
        attributes.put("itemId", getItemId());
        attributes.put("brandName", getBrandName());
        attributes.put("componentName", getComponentName());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("screenName", getScreenName());
        attributes.put("businessUnitName", getBusinessUnitName());
        attributes.put("companyNo", getCompanyNo());
        attributes.put("itemIdentifierType", getItemIdentifierType());
        attributes.put("componentNo", getComponentNo());
        attributes.put("sessionId", getSessionId());
        attributes.put("itemName", getItemName());
        attributes.put("itemIdentifier", getItemIdentifier());
        attributes.put("companySid", getCompanySid());
        attributes.put("itemNo", getItemNo());
        attributes.put("componentType", getComponentType());
        attributes.put("theraputicClass", getTheraputicClass());
        attributes.put("componentMasterSid", getComponentMasterSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        String contractHolder = (String) attributes.get("contractHolder");

        if (contractHolder != null) {
            setContractHolder(contractHolder);
        }

        String userId = (String) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        String businessUnitNo = (String) attributes.get("businessUnitNo");

        if (businessUnitNo != null) {
            setBusinessUnitNo(businessUnitNo);
        }

        String companyName = (String) attributes.get("companyName");

        if (companyName != null) {
            setCompanyName(companyName);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String brandName = (String) attributes.get("brandName");

        if (brandName != null) {
            setBrandName(brandName);
        }

        String componentName = (String) attributes.get("componentName");

        if (componentName != null) {
            setComponentName(componentName);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String screenName = (String) attributes.get("screenName");

        if (screenName != null) {
            setScreenName(screenName);
        }

        String businessUnitName = (String) attributes.get("businessUnitName");

        if (businessUnitName != null) {
            setBusinessUnitName(businessUnitName);
        }

        String companyNo = (String) attributes.get("companyNo");

        if (companyNo != null) {
            setCompanyNo(companyNo);
        }

        String itemIdentifierType = (String) attributes.get(
                "itemIdentifierType");

        if (itemIdentifierType != null) {
            setItemIdentifierType(itemIdentifierType);
        }

        String componentNo = (String) attributes.get("componentNo");

        if (componentNo != null) {
            setComponentNo(componentNo);
        }

        String sessionId = (String) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        String itemIdentifier = (String) attributes.get("itemIdentifier");

        if (itemIdentifier != null) {
            setItemIdentifier(itemIdentifier);
        }

        Integer companySid = (Integer) attributes.get("companySid");

        if (companySid != null) {
            setCompanySid(companySid);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        String componentType = (String) attributes.get("componentType");

        if (componentType != null) {
            setComponentType(componentType);
        }

        String theraputicClass = (String) attributes.get("theraputicClass");

        if (theraputicClass != null) {
            setTheraputicClass(theraputicClass);
        }

        Integer componentMasterSid = (Integer) attributes.get(
                "componentMasterSid");

        if (componentMasterSid != null) {
            setComponentMasterSid(componentMasterSid);
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

        if (_ahTempDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ahTempDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_ahTempDetailsRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getContractHolder() {
        return _contractHolder;
    }

    @Override
    public void setContractHolder(String contractHolder) {
        _contractHolder = contractHolder;

        if (_ahTempDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ahTempDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractHolder",
                        String.class);

                method.invoke(_ahTempDetailsRemoteModel, contractHolder);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUserId() {
        return _userId;
    }

    @Override
    public void setUserId(String userId) {
        _userId = userId;

        if (_ahTempDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ahTempDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", String.class);

                method.invoke(_ahTempDetailsRemoteModel, userId);
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

        if (_ahTempDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ahTempDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_ahTempDetailsRemoteModel, itemMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBusinessUnitNo() {
        return _businessUnitNo;
    }

    @Override
    public void setBusinessUnitNo(String businessUnitNo) {
        _businessUnitNo = businessUnitNo;

        if (_ahTempDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ahTempDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBusinessUnitNo",
                        String.class);

                method.invoke(_ahTempDetailsRemoteModel, businessUnitNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyName() {
        return _companyName;
    }

    @Override
    public void setCompanyName(String companyName) {
        _companyName = companyName;

        if (_ahTempDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ahTempDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyName", String.class);

                method.invoke(_ahTempDetailsRemoteModel, companyName);
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

        if (_ahTempDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ahTempDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_ahTempDetailsRemoteModel, itemId);
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

        if (_ahTempDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ahTempDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandName", String.class);

                method.invoke(_ahTempDetailsRemoteModel, brandName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getComponentName() {
        return _componentName;
    }

    @Override
    public void setComponentName(String componentName) {
        _componentName = componentName;

        if (_ahTempDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ahTempDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setComponentName", String.class);

                method.invoke(_ahTempDetailsRemoteModel, componentName);
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

        if (_ahTempDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ahTempDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_ahTempDetailsRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCreatedBy() {
        return _createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        _createdBy = createdBy;

        if (_ahTempDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ahTempDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_ahTempDetailsRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getScreenName() {
        return _screenName;
    }

    @Override
    public void setScreenName(String screenName) {
        _screenName = screenName;

        if (_ahTempDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ahTempDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setScreenName", String.class);

                method.invoke(_ahTempDetailsRemoteModel, screenName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBusinessUnitName() {
        return _businessUnitName;
    }

    @Override
    public void setBusinessUnitName(String businessUnitName) {
        _businessUnitName = businessUnitName;

        if (_ahTempDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ahTempDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBusinessUnitName",
                        String.class);

                method.invoke(_ahTempDetailsRemoteModel, businessUnitName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyNo() {
        return _companyNo;
    }

    @Override
    public void setCompanyNo(String companyNo) {
        _companyNo = companyNo;

        if (_ahTempDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ahTempDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyNo", String.class);

                method.invoke(_ahTempDetailsRemoteModel, companyNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemIdentifierType() {
        return _itemIdentifierType;
    }

    @Override
    public void setItemIdentifierType(String itemIdentifierType) {
        _itemIdentifierType = itemIdentifierType;

        if (_ahTempDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ahTempDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemIdentifierType",
                        String.class);

                method.invoke(_ahTempDetailsRemoteModel, itemIdentifierType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getComponentNo() {
        return _componentNo;
    }

    @Override
    public void setComponentNo(String componentNo) {
        _componentNo = componentNo;

        if (_ahTempDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ahTempDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setComponentNo", String.class);

                method.invoke(_ahTempDetailsRemoteModel, componentNo);
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

        if (_ahTempDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ahTempDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", String.class);

                method.invoke(_ahTempDetailsRemoteModel, sessionId);
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

        if (_ahTempDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ahTempDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemName", String.class);

                method.invoke(_ahTempDetailsRemoteModel, itemName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemIdentifier() {
        return _itemIdentifier;
    }

    @Override
    public void setItemIdentifier(String itemIdentifier) {
        _itemIdentifier = itemIdentifier;

        if (_ahTempDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ahTempDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemIdentifier",
                        String.class);

                method.invoke(_ahTempDetailsRemoteModel, itemIdentifier);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCompanySid() {
        return _companySid;
    }

    @Override
    public void setCompanySid(int companySid) {
        _companySid = companySid;

        if (_ahTempDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ahTempDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanySid", int.class);

                method.invoke(_ahTempDetailsRemoteModel, companySid);
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

        if (_ahTempDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ahTempDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemNo", String.class);

                method.invoke(_ahTempDetailsRemoteModel, itemNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getComponentType() {
        return _componentType;
    }

    @Override
    public void setComponentType(String componentType) {
        _componentType = componentType;

        if (_ahTempDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ahTempDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setComponentType", String.class);

                method.invoke(_ahTempDetailsRemoteModel, componentType);
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

        if (_ahTempDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ahTempDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setTheraputicClass",
                        String.class);

                method.invoke(_ahTempDetailsRemoteModel, theraputicClass);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getComponentMasterSid() {
        return _componentMasterSid;
    }

    @Override
    public void setComponentMasterSid(int componentMasterSid) {
        _componentMasterSid = componentMasterSid;

        if (_ahTempDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ahTempDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setComponentMasterSid",
                        int.class);

                method.invoke(_ahTempDetailsRemoteModel, componentMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getAhTempDetailsRemoteModel() {
        return _ahTempDetailsRemoteModel;
    }

    public void setAhTempDetailsRemoteModel(
        BaseModel<?> ahTempDetailsRemoteModel) {
        _ahTempDetailsRemoteModel = ahTempDetailsRemoteModel;
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

        Class<?> remoteModelClass = _ahTempDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_ahTempDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            AhTempDetailsLocalServiceUtil.addAhTempDetails(this);
        } else {
            AhTempDetailsLocalServiceUtil.updateAhTempDetails(this);
        }
    }

    @Override
    public AhTempDetails toEscapedModel() {
        return (AhTempDetails) ProxyUtil.newProxyInstance(AhTempDetails.class.getClassLoader(),
            new Class[] { AhTempDetails.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        AhTempDetailsClp clone = new AhTempDetailsClp();

        clone.setCheckRecord(getCheckRecord());
        clone.setContractHolder(getContractHolder());
        clone.setUserId(getUserId());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setBusinessUnitNo(getBusinessUnitNo());
        clone.setCompanyName(getCompanyName());
        clone.setItemId(getItemId());
        clone.setBrandName(getBrandName());
        clone.setComponentName(getComponentName());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setScreenName(getScreenName());
        clone.setBusinessUnitName(getBusinessUnitName());
        clone.setCompanyNo(getCompanyNo());
        clone.setItemIdentifierType(getItemIdentifierType());
        clone.setComponentNo(getComponentNo());
        clone.setSessionId(getSessionId());
        clone.setItemName(getItemName());
        clone.setItemIdentifier(getItemIdentifier());
        clone.setCompanySid(getCompanySid());
        clone.setItemNo(getItemNo());
        clone.setComponentType(getComponentType());
        clone.setTheraputicClass(getTheraputicClass());
        clone.setComponentMasterSid(getComponentMasterSid());

        return clone;
    }

    @Override
    public int compareTo(AhTempDetails ahTempDetails) {
        int primaryKey = ahTempDetails.getPrimaryKey();

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

        if (!(obj instanceof AhTempDetailsClp)) {
            return false;
        }

        AhTempDetailsClp ahTempDetails = (AhTempDetailsClp) obj;

        int primaryKey = ahTempDetails.getPrimaryKey();

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

        sb.append("{checkRecord=");
        sb.append(getCheckRecord());
        sb.append(", contractHolder=");
        sb.append(getContractHolder());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", businessUnitNo=");
        sb.append(getBusinessUnitNo());
        sb.append(", companyName=");
        sb.append(getCompanyName());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", brandName=");
        sb.append(getBrandName());
        sb.append(", componentName=");
        sb.append(getComponentName());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", screenName=");
        sb.append(getScreenName());
        sb.append(", businessUnitName=");
        sb.append(getBusinessUnitName());
        sb.append(", companyNo=");
        sb.append(getCompanyNo());
        sb.append(", itemIdentifierType=");
        sb.append(getItemIdentifierType());
        sb.append(", componentNo=");
        sb.append(getComponentNo());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append(", itemName=");
        sb.append(getItemName());
        sb.append(", itemIdentifier=");
        sb.append(getItemIdentifier());
        sb.append(", companySid=");
        sb.append(getCompanySid());
        sb.append(", itemNo=");
        sb.append(getItemNo());
        sb.append(", componentType=");
        sb.append(getComponentType());
        sb.append(", theraputicClass=");
        sb.append(getTheraputicClass());
        sb.append(", componentMasterSid=");
        sb.append(getComponentMasterSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(76);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.AhTempDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractHolder</column-name><column-value><![CDATA[");
        sb.append(getContractHolder());
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
            "<column><column-name>businessUnitNo</column-name><column-value><![CDATA[");
        sb.append(getBusinessUnitNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyName</column-name><column-value><![CDATA[");
        sb.append(getCompanyName());
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
            "<column><column-name>componentName</column-name><column-value><![CDATA[");
        sb.append(getComponentName());
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
            "<column><column-name>screenName</column-name><column-value><![CDATA[");
        sb.append(getScreenName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>businessUnitName</column-name><column-value><![CDATA[");
        sb.append(getBusinessUnitName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyNo</column-name><column-value><![CDATA[");
        sb.append(getCompanyNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemIdentifierType</column-name><column-value><![CDATA[");
        sb.append(getItemIdentifierType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>componentNo</column-name><column-value><![CDATA[");
        sb.append(getComponentNo());
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
            "<column><column-name>itemIdentifier</column-name><column-value><![CDATA[");
        sb.append(getItemIdentifier());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companySid</column-name><column-value><![CDATA[");
        sb.append(getCompanySid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemNo</column-name><column-value><![CDATA[");
        sb.append(getItemNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>componentType</column-name><column-value><![CDATA[");
        sb.append(getComponentType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>theraputicClass</column-name><column-value><![CDATA[");
        sb.append(getTheraputicClass());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>componentMasterSid</column-name><column-value><![CDATA[");
        sb.append(getComponentMasterSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
