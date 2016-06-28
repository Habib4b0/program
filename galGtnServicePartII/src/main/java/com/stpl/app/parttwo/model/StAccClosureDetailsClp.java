package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.ClpSerializer;
import com.stpl.app.parttwo.service.StAccClosureDetailsLocalServiceUtil;

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


public class StAccClosureDetailsClp extends BaseModelImpl<StAccClosureDetails>
    implements StAccClosureDetails {
    private Date _lastModifiedDate;
    private boolean _checkRecord;
    private String _contractName;
    private int _userId;
    private int _itemMasterSid;
    private String _moduleName;
    private String _companyName;
    private String _brandName;
    private String _companyCostCenter;
    private String _companyNo;
    private int _contractMasterSid;
    private int _sessionId;
    private int _ccpDetailsSid;
    private String _itemName;
    private int _accClosureMasterSid;
    private int _rsModelSid;
    private String _contractNo;
    private int _companyMasterSid;
    private String _ndc8;
    private BaseModel<?> _stAccClosureDetailsRemoteModel;

    public StAccClosureDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return StAccClosureDetails.class;
    }

    @Override
    public String getModelClassName() {
        return StAccClosureDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _accClosureMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setAccClosureMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _accClosureMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("lastModifiedDate", getLastModifiedDate());
        attributes.put("checkRecord", getCheckRecord());
        attributes.put("contractName", getContractName());
        attributes.put("userId", getUserId());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("moduleName", getModuleName());
        attributes.put("companyName", getCompanyName());
        attributes.put("brandName", getBrandName());
        attributes.put("companyCostCenter", getCompanyCostCenter());
        attributes.put("companyNo", getCompanyNo());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("sessionId", getSessionId());
        attributes.put("ccpDetailsSid", getCcpDetailsSid());
        attributes.put("itemName", getItemName());
        attributes.put("accClosureMasterSid", getAccClosureMasterSid());
        attributes.put("rsModelSid", getRsModelSid());
        attributes.put("contractNo", getContractNo());
        attributes.put("companyMasterSid", getCompanyMasterSid());
        attributes.put("ndc8", getNdc8());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date lastModifiedDate = (Date) attributes.get("lastModifiedDate");

        if (lastModifiedDate != null) {
            setLastModifiedDate(lastModifiedDate);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        String contractName = (String) attributes.get("contractName");

        if (contractName != null) {
            setContractName(contractName);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        String moduleName = (String) attributes.get("moduleName");

        if (moduleName != null) {
            setModuleName(moduleName);
        }

        String companyName = (String) attributes.get("companyName");

        if (companyName != null) {
            setCompanyName(companyName);
        }

        String brandName = (String) attributes.get("brandName");

        if (brandName != null) {
            setBrandName(brandName);
        }

        String companyCostCenter = (String) attributes.get("companyCostCenter");

        if (companyCostCenter != null) {
            setCompanyCostCenter(companyCostCenter);
        }

        String companyNo = (String) attributes.get("companyNo");

        if (companyNo != null) {
            setCompanyNo(companyNo);
        }

        Integer contractMasterSid = (Integer) attributes.get(
                "contractMasterSid");

        if (contractMasterSid != null) {
            setContractMasterSid(contractMasterSid);
        }

        Integer sessionId = (Integer) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        Integer ccpDetailsSid = (Integer) attributes.get("ccpDetailsSid");

        if (ccpDetailsSid != null) {
            setCcpDetailsSid(ccpDetailsSid);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        Integer accClosureMasterSid = (Integer) attributes.get(
                "accClosureMasterSid");

        if (accClosureMasterSid != null) {
            setAccClosureMasterSid(accClosureMasterSid);
        }

        Integer rsModelSid = (Integer) attributes.get("rsModelSid");

        if (rsModelSid != null) {
            setRsModelSid(rsModelSid);
        }

        String contractNo = (String) attributes.get("contractNo");

        if (contractNo != null) {
            setContractNo(contractNo);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }

        String ndc8 = (String) attributes.get("ndc8");

        if (ndc8 != null) {
            setNdc8(ndc8);
        }
    }

    @Override
    public Date getLastModifiedDate() {
        return _lastModifiedDate;
    }

    @Override
    public void setLastModifiedDate(Date lastModifiedDate) {
        _lastModifiedDate = lastModifiedDate;

        if (_stAccClosureDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _stAccClosureDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setLastModifiedDate",
                        Date.class);

                method.invoke(_stAccClosureDetailsRemoteModel, lastModifiedDate);
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

        if (_stAccClosureDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _stAccClosureDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_stAccClosureDetailsRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getContractName() {
        return _contractName;
    }

    @Override
    public void setContractName(String contractName) {
        _contractName = contractName;

        if (_stAccClosureDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _stAccClosureDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractName", String.class);

                method.invoke(_stAccClosureDetailsRemoteModel, contractName);
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

        if (_stAccClosureDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _stAccClosureDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", int.class);

                method.invoke(_stAccClosureDetailsRemoteModel, userId);
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

        if (_stAccClosureDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _stAccClosureDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_stAccClosureDetailsRemoteModel, itemMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getModuleName() {
        return _moduleName;
    }

    @Override
    public void setModuleName(String moduleName) {
        _moduleName = moduleName;

        if (_stAccClosureDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _stAccClosureDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModuleName", String.class);

                method.invoke(_stAccClosureDetailsRemoteModel, moduleName);
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

        if (_stAccClosureDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _stAccClosureDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyName", String.class);

                method.invoke(_stAccClosureDetailsRemoteModel, companyName);
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

        if (_stAccClosureDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _stAccClosureDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandName", String.class);

                method.invoke(_stAccClosureDetailsRemoteModel, brandName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyCostCenter() {
        return _companyCostCenter;
    }

    @Override
    public void setCompanyCostCenter(String companyCostCenter) {
        _companyCostCenter = companyCostCenter;

        if (_stAccClosureDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _stAccClosureDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyCostCenter",
                        String.class);

                method.invoke(_stAccClosureDetailsRemoteModel, companyCostCenter);
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

        if (_stAccClosureDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _stAccClosureDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyNo", String.class);

                method.invoke(_stAccClosureDetailsRemoteModel, companyNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getContractMasterSid() {
        return _contractMasterSid;
    }

    @Override
    public void setContractMasterSid(int contractMasterSid) {
        _contractMasterSid = contractMasterSid;

        if (_stAccClosureDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _stAccClosureDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractMasterSid",
                        int.class);

                method.invoke(_stAccClosureDetailsRemoteModel, contractMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getSessionId() {
        return _sessionId;
    }

    @Override
    public void setSessionId(int sessionId) {
        _sessionId = sessionId;

        if (_stAccClosureDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _stAccClosureDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", int.class);

                method.invoke(_stAccClosureDetailsRemoteModel, sessionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCcpDetailsSid() {
        return _ccpDetailsSid;
    }

    @Override
    public void setCcpDetailsSid(int ccpDetailsSid) {
        _ccpDetailsSid = ccpDetailsSid;

        if (_stAccClosureDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _stAccClosureDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCcpDetailsSid", int.class);

                method.invoke(_stAccClosureDetailsRemoteModel, ccpDetailsSid);
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

        if (_stAccClosureDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _stAccClosureDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemName", String.class);

                method.invoke(_stAccClosureDetailsRemoteModel, itemName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getAccClosureMasterSid() {
        return _accClosureMasterSid;
    }

    @Override
    public void setAccClosureMasterSid(int accClosureMasterSid) {
        _accClosureMasterSid = accClosureMasterSid;

        if (_stAccClosureDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _stAccClosureDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setAccClosureMasterSid",
                        int.class);

                method.invoke(_stAccClosureDetailsRemoteModel,
                    accClosureMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRsModelSid() {
        return _rsModelSid;
    }

    @Override
    public void setRsModelSid(int rsModelSid) {
        _rsModelSid = rsModelSid;

        if (_stAccClosureDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _stAccClosureDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsModelSid", int.class);

                method.invoke(_stAccClosureDetailsRemoteModel, rsModelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getContractNo() {
        return _contractNo;
    }

    @Override
    public void setContractNo(String contractNo) {
        _contractNo = contractNo;

        if (_stAccClosureDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _stAccClosureDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractNo", String.class);

                method.invoke(_stAccClosureDetailsRemoteModel, contractNo);
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

        if (_stAccClosureDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _stAccClosureDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyMasterSid", int.class);

                method.invoke(_stAccClosureDetailsRemoteModel, companyMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNdc8() {
        return _ndc8;
    }

    @Override
    public void setNdc8(String ndc8) {
        _ndc8 = ndc8;

        if (_stAccClosureDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _stAccClosureDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNdc8", String.class);

                method.invoke(_stAccClosureDetailsRemoteModel, ndc8);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getStAccClosureDetailsRemoteModel() {
        return _stAccClosureDetailsRemoteModel;
    }

    public void setStAccClosureDetailsRemoteModel(
        BaseModel<?> stAccClosureDetailsRemoteModel) {
        _stAccClosureDetailsRemoteModel = stAccClosureDetailsRemoteModel;
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

        Class<?> remoteModelClass = _stAccClosureDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_stAccClosureDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            StAccClosureDetailsLocalServiceUtil.addStAccClosureDetails(this);
        } else {
            StAccClosureDetailsLocalServiceUtil.updateStAccClosureDetails(this);
        }
    }

    @Override
    public StAccClosureDetails toEscapedModel() {
        return (StAccClosureDetails) ProxyUtil.newProxyInstance(StAccClosureDetails.class.getClassLoader(),
            new Class[] { StAccClosureDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        StAccClosureDetailsClp clone = new StAccClosureDetailsClp();

        clone.setLastModifiedDate(getLastModifiedDate());
        clone.setCheckRecord(getCheckRecord());
        clone.setContractName(getContractName());
        clone.setUserId(getUserId());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setModuleName(getModuleName());
        clone.setCompanyName(getCompanyName());
        clone.setBrandName(getBrandName());
        clone.setCompanyCostCenter(getCompanyCostCenter());
        clone.setCompanyNo(getCompanyNo());
        clone.setContractMasterSid(getContractMasterSid());
        clone.setSessionId(getSessionId());
        clone.setCcpDetailsSid(getCcpDetailsSid());
        clone.setItemName(getItemName());
        clone.setAccClosureMasterSid(getAccClosureMasterSid());
        clone.setRsModelSid(getRsModelSid());
        clone.setContractNo(getContractNo());
        clone.setCompanyMasterSid(getCompanyMasterSid());
        clone.setNdc8(getNdc8());

        return clone;
    }

    @Override
    public int compareTo(StAccClosureDetails stAccClosureDetails) {
        int primaryKey = stAccClosureDetails.getPrimaryKey();

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

        if (!(obj instanceof StAccClosureDetailsClp)) {
            return false;
        }

        StAccClosureDetailsClp stAccClosureDetails = (StAccClosureDetailsClp) obj;

        int primaryKey = stAccClosureDetails.getPrimaryKey();

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
        StringBundler sb = new StringBundler(39);

        sb.append("{lastModifiedDate=");
        sb.append(getLastModifiedDate());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append(", contractName=");
        sb.append(getContractName());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", moduleName=");
        sb.append(getModuleName());
        sb.append(", companyName=");
        sb.append(getCompanyName());
        sb.append(", brandName=");
        sb.append(getBrandName());
        sb.append(", companyCostCenter=");
        sb.append(getCompanyCostCenter());
        sb.append(", companyNo=");
        sb.append(getCompanyNo());
        sb.append(", contractMasterSid=");
        sb.append(getContractMasterSid());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append(", ccpDetailsSid=");
        sb.append(getCcpDetailsSid());
        sb.append(", itemName=");
        sb.append(getItemName());
        sb.append(", accClosureMasterSid=");
        sb.append(getAccClosureMasterSid());
        sb.append(", rsModelSid=");
        sb.append(getRsModelSid());
        sb.append(", contractNo=");
        sb.append(getContractNo());
        sb.append(", companyMasterSid=");
        sb.append(getCompanyMasterSid());
        sb.append(", ndc8=");
        sb.append(getNdc8());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(61);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.StAccClosureDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>lastModifiedDate</column-name><column-value><![CDATA[");
        sb.append(getLastModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractName</column-name><column-value><![CDATA[");
        sb.append(getContractName());
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
            "<column><column-name>moduleName</column-name><column-value><![CDATA[");
        sb.append(getModuleName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyName</column-name><column-value><![CDATA[");
        sb.append(getCompanyName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brandName</column-name><column-value><![CDATA[");
        sb.append(getBrandName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyCostCenter</column-name><column-value><![CDATA[");
        sb.append(getCompanyCostCenter());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyNo</column-name><column-value><![CDATA[");
        sb.append(getCompanyNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractMasterSid</column-name><column-value><![CDATA[");
        sb.append(getContractMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sessionId</column-name><column-value><![CDATA[");
        sb.append(getSessionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ccpDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getCcpDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemName</column-name><column-value><![CDATA[");
        sb.append(getItemName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accClosureMasterSid</column-name><column-value><![CDATA[");
        sb.append(getAccClosureMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsModelSid</column-name><column-value><![CDATA[");
        sb.append(getRsModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractNo</column-name><column-value><![CDATA[");
        sb.append(getContractNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ndc8</column-name><column-value><![CDATA[");
        sb.append(getNdc8());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
