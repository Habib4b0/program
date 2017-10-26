package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.ImtdSalesBasisDetailsLocalServiceUtil;

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


public class ImtdSalesBasisDetailsClp extends BaseModelImpl<ImtdSalesBasisDetails>
    implements ImtdSalesBasisDetails {
    private int _createdBy;
    private int _netSalesFormulaMasterSid;
    private int _usersSid;
    private int _modifiedBy;
    private Date _createdDate;
    private int _contractMasterSid;
    private String _cfpNo;
    private String _imtdCreatedDate;
    private String _contractNo;
    private String _contractName;
    private int _salesBasisDetailsSid;
    private boolean _checkRecord;
    private Date _modifiedDate;
    private String _customerName;
    private String _operation;
    private String _customerNo;
    private int _imtdSalesBasisDetailsSid;
    private String _cfpName;
    private int _cdrModelSid;
    private String _sessionId;
    private int _cfpContractDetailsSid;
    private String _inboundStatus;
    private BaseModel<?> _imtdSalesBasisDetailsRemoteModel;

    public ImtdSalesBasisDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ImtdSalesBasisDetails.class;
    }

    @Override
    public String getModelClassName() {
        return ImtdSalesBasisDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _imtdSalesBasisDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setImtdSalesBasisDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _imtdSalesBasisDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("netSalesFormulaMasterSid", getNetSalesFormulaMasterSid());
        attributes.put("usersSid", getUsersSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("cfpNo", getCfpNo());
        attributes.put("imtdCreatedDate", getImtdCreatedDate());
        attributes.put("contractNo", getContractNo());
        attributes.put("contractName", getContractName());
        attributes.put("salesBasisDetailsSid", getSalesBasisDetailsSid());
        attributes.put("checkRecord", getCheckRecord());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("customerName", getCustomerName());
        attributes.put("operation", getOperation());
        attributes.put("customerNo", getCustomerNo());
        attributes.put("imtdSalesBasisDetailsSid", getImtdSalesBasisDetailsSid());
        attributes.put("cfpName", getCfpName());
        attributes.put("cdrModelSid", getCdrModelSid());
        attributes.put("sessionId", getSessionId());
        attributes.put("cfpContractDetailsSid", getCfpContractDetailsSid());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer netSalesFormulaMasterSid = (Integer) attributes.get(
                "netSalesFormulaMasterSid");

        if (netSalesFormulaMasterSid != null) {
            setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
        }

        Integer usersSid = (Integer) attributes.get("usersSid");

        if (usersSid != null) {
            setUsersSid(usersSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer contractMasterSid = (Integer) attributes.get(
                "contractMasterSid");

        if (contractMasterSid != null) {
            setContractMasterSid(contractMasterSid);
        }

        String cfpNo = (String) attributes.get("cfpNo");

        if (cfpNo != null) {
            setCfpNo(cfpNo);
        }

        String imtdCreatedDate = (String) attributes.get("imtdCreatedDate");

        if (imtdCreatedDate != null) {
            setImtdCreatedDate(imtdCreatedDate);
        }

        String contractNo = (String) attributes.get("contractNo");

        if (contractNo != null) {
            setContractNo(contractNo);
        }

        String contractName = (String) attributes.get("contractName");

        if (contractName != null) {
            setContractName(contractName);
        }

        Integer salesBasisDetailsSid = (Integer) attributes.get(
                "salesBasisDetailsSid");

        if (salesBasisDetailsSid != null) {
            setSalesBasisDetailsSid(salesBasisDetailsSid);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String customerName = (String) attributes.get("customerName");

        if (customerName != null) {
            setCustomerName(customerName);
        }

        String operation = (String) attributes.get("operation");

        if (operation != null) {
            setOperation(operation);
        }

        String customerNo = (String) attributes.get("customerNo");

        if (customerNo != null) {
            setCustomerNo(customerNo);
        }

        Integer imtdSalesBasisDetailsSid = (Integer) attributes.get(
                "imtdSalesBasisDetailsSid");

        if (imtdSalesBasisDetailsSid != null) {
            setImtdSalesBasisDetailsSid(imtdSalesBasisDetailsSid);
        }

        String cfpName = (String) attributes.get("cfpName");

        if (cfpName != null) {
            setCfpName(cfpName);
        }

        Integer cdrModelSid = (Integer) attributes.get("cdrModelSid");

        if (cdrModelSid != null) {
            setCdrModelSid(cdrModelSid);
        }

        String sessionId = (String) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        Integer cfpContractDetailsSid = (Integer) attributes.get(
                "cfpContractDetailsSid");

        if (cfpContractDetailsSid != null) {
            setCfpContractDetailsSid(cfpContractDetailsSid);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }
    }

    @Override
    public int getCreatedBy() {
        return _createdBy;
    }

    @Override
    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;

        if (_imtdSalesBasisDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdSalesBasisDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_imtdSalesBasisDetailsRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getNetSalesFormulaMasterSid() {
        return _netSalesFormulaMasterSid;
    }

    @Override
    public void setNetSalesFormulaMasterSid(int netSalesFormulaMasterSid) {
        _netSalesFormulaMasterSid = netSalesFormulaMasterSid;

        if (_imtdSalesBasisDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdSalesBasisDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesFormulaMasterSid",
                        int.class);

                method.invoke(_imtdSalesBasisDetailsRemoteModel,
                    netSalesFormulaMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getUsersSid() {
        return _usersSid;
    }

    @Override
    public void setUsersSid(int usersSid) {
        _usersSid = usersSid;

        if (_imtdSalesBasisDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdSalesBasisDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setUsersSid", int.class);

                method.invoke(_imtdSalesBasisDetailsRemoteModel, usersSid);
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

        if (_imtdSalesBasisDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdSalesBasisDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_imtdSalesBasisDetailsRemoteModel, modifiedBy);
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

        if (_imtdSalesBasisDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdSalesBasisDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_imtdSalesBasisDetailsRemoteModel, createdDate);
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

        if (_imtdSalesBasisDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdSalesBasisDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractMasterSid",
                        int.class);

                method.invoke(_imtdSalesBasisDetailsRemoteModel,
                    contractMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCfpNo() {
        return _cfpNo;
    }

    @Override
    public void setCfpNo(String cfpNo) {
        _cfpNo = cfpNo;

        if (_imtdSalesBasisDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdSalesBasisDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpNo", String.class);

                method.invoke(_imtdSalesBasisDetailsRemoteModel, cfpNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getImtdCreatedDate() {
        return _imtdCreatedDate;
    }

    @Override
    public void setImtdCreatedDate(String imtdCreatedDate) {
        _imtdCreatedDate = imtdCreatedDate;

        if (_imtdSalesBasisDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdSalesBasisDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setImtdCreatedDate",
                        String.class);

                method.invoke(_imtdSalesBasisDetailsRemoteModel, imtdCreatedDate);
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

        if (_imtdSalesBasisDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdSalesBasisDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractNo", String.class);

                method.invoke(_imtdSalesBasisDetailsRemoteModel, contractNo);
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

        if (_imtdSalesBasisDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdSalesBasisDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractName", String.class);

                method.invoke(_imtdSalesBasisDetailsRemoteModel, contractName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getSalesBasisDetailsSid() {
        return _salesBasisDetailsSid;
    }

    @Override
    public void setSalesBasisDetailsSid(int salesBasisDetailsSid) {
        _salesBasisDetailsSid = salesBasisDetailsSid;

        if (_imtdSalesBasisDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdSalesBasisDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSalesBasisDetailsSid",
                        int.class);

                method.invoke(_imtdSalesBasisDetailsRemoteModel,
                    salesBasisDetailsSid);
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

        if (_imtdSalesBasisDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdSalesBasisDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_imtdSalesBasisDetailsRemoteModel, checkRecord);
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

        if (_imtdSalesBasisDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdSalesBasisDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_imtdSalesBasisDetailsRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCustomerName() {
        return _customerName;
    }

    @Override
    public void setCustomerName(String customerName) {
        _customerName = customerName;

        if (_imtdSalesBasisDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdSalesBasisDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCustomerName", String.class);

                method.invoke(_imtdSalesBasisDetailsRemoteModel, customerName);
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

        if (_imtdSalesBasisDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdSalesBasisDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setOperation", String.class);

                method.invoke(_imtdSalesBasisDetailsRemoteModel, operation);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCustomerNo() {
        return _customerNo;
    }

    @Override
    public void setCustomerNo(String customerNo) {
        _customerNo = customerNo;

        if (_imtdSalesBasisDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdSalesBasisDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCustomerNo", String.class);

                method.invoke(_imtdSalesBasisDetailsRemoteModel, customerNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getImtdSalesBasisDetailsSid() {
        return _imtdSalesBasisDetailsSid;
    }

    @Override
    public void setImtdSalesBasisDetailsSid(int imtdSalesBasisDetailsSid) {
        _imtdSalesBasisDetailsSid = imtdSalesBasisDetailsSid;

        if (_imtdSalesBasisDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdSalesBasisDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setImtdSalesBasisDetailsSid",
                        int.class);

                method.invoke(_imtdSalesBasisDetailsRemoteModel,
                    imtdSalesBasisDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCfpName() {
        return _cfpName;
    }

    @Override
    public void setCfpName(String cfpName) {
        _cfpName = cfpName;

        if (_imtdSalesBasisDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdSalesBasisDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpName", String.class);

                method.invoke(_imtdSalesBasisDetailsRemoteModel, cfpName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCdrModelSid() {
        return _cdrModelSid;
    }

    @Override
    public void setCdrModelSid(int cdrModelSid) {
        _cdrModelSid = cdrModelSid;

        if (_imtdSalesBasisDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdSalesBasisDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCdrModelSid", int.class);

                method.invoke(_imtdSalesBasisDetailsRemoteModel, cdrModelSid);
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

        if (_imtdSalesBasisDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdSalesBasisDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", String.class);

                method.invoke(_imtdSalesBasisDetailsRemoteModel, sessionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCfpContractDetailsSid() {
        return _cfpContractDetailsSid;
    }

    @Override
    public void setCfpContractDetailsSid(int cfpContractDetailsSid) {
        _cfpContractDetailsSid = cfpContractDetailsSid;

        if (_imtdSalesBasisDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdSalesBasisDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpContractDetailsSid",
                        int.class);

                method.invoke(_imtdSalesBasisDetailsRemoteModel,
                    cfpContractDetailsSid);
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

        if (_imtdSalesBasisDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdSalesBasisDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_imtdSalesBasisDetailsRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getImtdSalesBasisDetailsRemoteModel() {
        return _imtdSalesBasisDetailsRemoteModel;
    }

    public void setImtdSalesBasisDetailsRemoteModel(
        BaseModel<?> imtdSalesBasisDetailsRemoteModel) {
        _imtdSalesBasisDetailsRemoteModel = imtdSalesBasisDetailsRemoteModel;
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

        Class<?> remoteModelClass = _imtdSalesBasisDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_imtdSalesBasisDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ImtdSalesBasisDetailsLocalServiceUtil.addImtdSalesBasisDetails(this);
        } else {
            ImtdSalesBasisDetailsLocalServiceUtil.updateImtdSalesBasisDetails(this);
        }
    }

    @Override
    public ImtdSalesBasisDetails toEscapedModel() {
        return (ImtdSalesBasisDetails) ProxyUtil.newProxyInstance(ImtdSalesBasisDetails.class.getClassLoader(),
            new Class[] { ImtdSalesBasisDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ImtdSalesBasisDetailsClp clone = new ImtdSalesBasisDetailsClp();

        clone.setCreatedBy(getCreatedBy());
        clone.setNetSalesFormulaMasterSid(getNetSalesFormulaMasterSid());
        clone.setUsersSid(getUsersSid());
        clone.setModifiedBy(getModifiedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setContractMasterSid(getContractMasterSid());
        clone.setCfpNo(getCfpNo());
        clone.setImtdCreatedDate(getImtdCreatedDate());
        clone.setContractNo(getContractNo());
        clone.setContractName(getContractName());
        clone.setSalesBasisDetailsSid(getSalesBasisDetailsSid());
        clone.setCheckRecord(getCheckRecord());
        clone.setModifiedDate(getModifiedDate());
        clone.setCustomerName(getCustomerName());
        clone.setOperation(getOperation());
        clone.setCustomerNo(getCustomerNo());
        clone.setImtdSalesBasisDetailsSid(getImtdSalesBasisDetailsSid());
        clone.setCfpName(getCfpName());
        clone.setCdrModelSid(getCdrModelSid());
        clone.setSessionId(getSessionId());
        clone.setCfpContractDetailsSid(getCfpContractDetailsSid());
        clone.setInboundStatus(getInboundStatus());

        return clone;
    }

    @Override
    public int compareTo(ImtdSalesBasisDetails imtdSalesBasisDetails) {
        int primaryKey = imtdSalesBasisDetails.getPrimaryKey();

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

        if (!(obj instanceof ImtdSalesBasisDetailsClp)) {
            return false;
        }

        ImtdSalesBasisDetailsClp imtdSalesBasisDetails = (ImtdSalesBasisDetailsClp) obj;

        int primaryKey = imtdSalesBasisDetails.getPrimaryKey();

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
        StringBundler sb = new StringBundler(45);

        sb.append("{createdBy=");
        sb.append(getCreatedBy());
        sb.append(", netSalesFormulaMasterSid=");
        sb.append(getNetSalesFormulaMasterSid());
        sb.append(", usersSid=");
        sb.append(getUsersSid());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", contractMasterSid=");
        sb.append(getContractMasterSid());
        sb.append(", cfpNo=");
        sb.append(getCfpNo());
        sb.append(", imtdCreatedDate=");
        sb.append(getImtdCreatedDate());
        sb.append(", contractNo=");
        sb.append(getContractNo());
        sb.append(", contractName=");
        sb.append(getContractName());
        sb.append(", salesBasisDetailsSid=");
        sb.append(getSalesBasisDetailsSid());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", customerName=");
        sb.append(getCustomerName());
        sb.append(", operation=");
        sb.append(getOperation());
        sb.append(", customerNo=");
        sb.append(getCustomerNo());
        sb.append(", imtdSalesBasisDetailsSid=");
        sb.append(getImtdSalesBasisDetailsSid());
        sb.append(", cfpName=");
        sb.append(getCfpName());
        sb.append(", cdrModelSid=");
        sb.append(getCdrModelSid());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append(", cfpContractDetailsSid=");
        sb.append(getCfpContractDetailsSid());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(70);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ImtdSalesBasisDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netSalesFormulaMasterSid</column-name><column-value><![CDATA[");
        sb.append(getNetSalesFormulaMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>usersSid</column-name><column-value><![CDATA[");
        sb.append(getUsersSid());
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
            "<column><column-name>contractMasterSid</column-name><column-value><![CDATA[");
        sb.append(getContractMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpNo</column-name><column-value><![CDATA[");
        sb.append(getCfpNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>imtdCreatedDate</column-name><column-value><![CDATA[");
        sb.append(getImtdCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractNo</column-name><column-value><![CDATA[");
        sb.append(getContractNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractName</column-name><column-value><![CDATA[");
        sb.append(getContractName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>salesBasisDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getSalesBasisDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>customerName</column-name><column-value><![CDATA[");
        sb.append(getCustomerName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>operation</column-name><column-value><![CDATA[");
        sb.append(getOperation());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>customerNo</column-name><column-value><![CDATA[");
        sb.append(getCustomerNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>imtdSalesBasisDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getImtdSalesBasisDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpName</column-name><column-value><![CDATA[");
        sb.append(getCfpName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cdrModelSid</column-name><column-value><![CDATA[");
        sb.append(getCdrModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sessionId</column-name><column-value><![CDATA[");
        sb.append(getSessionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpContractDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getCfpContractDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
