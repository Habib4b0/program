package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.AccClosureMasterLocalServiceUtil;
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


public class AccClosureMasterClp extends BaseModelImpl<AccClosureMaster>
    implements AccClosureMaster {
    private boolean _saveFlag;
    private String _accountNo;
    private Date _toDate;
    private int _itemMasterSid;
    private String _description;
    private String _reportName;
    private int _rsType;
    private String _productIdentifier;
    private Date _modifiedDate;
    private int _workflowStatus;
    private String _moduleType;
    private Date _fromDate;
    private int _contractType;
    private int _glCompanyMasterSid;
    private Date _createdDate;
    private int _createdBy;
    private int _contractMasterSid;
    private String _accrualPeriod;
    private String _companyGroupSid;
    private int _accClosureMasterSid;
    private int _rsCategory;
    private int _adjustmentType;
    private int _modifiedBy;
    private String _itemGroupSid;
    private int _rebateProgramType;
    private BaseModel<?> _accClosureMasterRemoteModel;

    public AccClosureMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return AccClosureMaster.class;
    }

    @Override
    public String getModelClassName() {
        return AccClosureMaster.class.getName();
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

        attributes.put("saveFlag", getSaveFlag());
        attributes.put("accountNo", getAccountNo());
        attributes.put("toDate", getToDate());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("description", getDescription());
        attributes.put("reportName", getReportName());
        attributes.put("rsType", getRsType());
        attributes.put("productIdentifier", getProductIdentifier());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("workflowStatus", getWorkflowStatus());
        attributes.put("moduleType", getModuleType());
        attributes.put("fromDate", getFromDate());
        attributes.put("contractType", getContractType());
        attributes.put("glCompanyMasterSid", getGlCompanyMasterSid());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("accrualPeriod", getAccrualPeriod());
        attributes.put("companyGroupSid", getCompanyGroupSid());
        attributes.put("accClosureMasterSid", getAccClosureMasterSid());
        attributes.put("rsCategory", getRsCategory());
        attributes.put("adjustmentType", getAdjustmentType());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("itemGroupSid", getItemGroupSid());
        attributes.put("rebateProgramType", getRebateProgramType());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Boolean saveFlag = (Boolean) attributes.get("saveFlag");

        if (saveFlag != null) {
            setSaveFlag(saveFlag);
        }

        String accountNo = (String) attributes.get("accountNo");

        if (accountNo != null) {
            setAccountNo(accountNo);
        }

        Date toDate = (Date) attributes.get("toDate");

        if (toDate != null) {
            setToDate(toDate);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }

        String reportName = (String) attributes.get("reportName");

        if (reportName != null) {
            setReportName(reportName);
        }

        Integer rsType = (Integer) attributes.get("rsType");

        if (rsType != null) {
            setRsType(rsType);
        }

        String productIdentifier = (String) attributes.get("productIdentifier");

        if (productIdentifier != null) {
            setProductIdentifier(productIdentifier);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer workflowStatus = (Integer) attributes.get("workflowStatus");

        if (workflowStatus != null) {
            setWorkflowStatus(workflowStatus);
        }

        String moduleType = (String) attributes.get("moduleType");

        if (moduleType != null) {
            setModuleType(moduleType);
        }

        Date fromDate = (Date) attributes.get("fromDate");

        if (fromDate != null) {
            setFromDate(fromDate);
        }

        Integer contractType = (Integer) attributes.get("contractType");

        if (contractType != null) {
            setContractType(contractType);
        }

        Integer glCompanyMasterSid = (Integer) attributes.get(
                "glCompanyMasterSid");

        if (glCompanyMasterSid != null) {
            setGlCompanyMasterSid(glCompanyMasterSid);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer contractMasterSid = (Integer) attributes.get(
                "contractMasterSid");

        if (contractMasterSid != null) {
            setContractMasterSid(contractMasterSid);
        }

        String accrualPeriod = (String) attributes.get("accrualPeriod");

        if (accrualPeriod != null) {
            setAccrualPeriod(accrualPeriod);
        }

        String companyGroupSid = (String) attributes.get("companyGroupSid");

        if (companyGroupSid != null) {
            setCompanyGroupSid(companyGroupSid);
        }

        Integer accClosureMasterSid = (Integer) attributes.get(
                "accClosureMasterSid");

        if (accClosureMasterSid != null) {
            setAccClosureMasterSid(accClosureMasterSid);
        }

        Integer rsCategory = (Integer) attributes.get("rsCategory");

        if (rsCategory != null) {
            setRsCategory(rsCategory);
        }

        Integer adjustmentType = (Integer) attributes.get("adjustmentType");

        if (adjustmentType != null) {
            setAdjustmentType(adjustmentType);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String itemGroupSid = (String) attributes.get("itemGroupSid");

        if (itemGroupSid != null) {
            setItemGroupSid(itemGroupSid);
        }

        Integer rebateProgramType = (Integer) attributes.get(
                "rebateProgramType");

        if (rebateProgramType != null) {
            setRebateProgramType(rebateProgramType);
        }
    }

    @Override
    public boolean getSaveFlag() {
        return _saveFlag;
    }

    @Override
    public boolean isSaveFlag() {
        return _saveFlag;
    }

    @Override
    public void setSaveFlag(boolean saveFlag) {
        _saveFlag = saveFlag;

        if (_accClosureMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSaveFlag", boolean.class);

                method.invoke(_accClosureMasterRemoteModel, saveFlag);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAccountNo() {
        return _accountNo;
    }

    @Override
    public void setAccountNo(String accountNo) {
        _accountNo = accountNo;

        if (_accClosureMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountNo", String.class);

                method.invoke(_accClosureMasterRemoteModel, accountNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getToDate() {
        return _toDate;
    }

    @Override
    public void setToDate(Date toDate) {
        _toDate = toDate;

        if (_accClosureMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setToDate", Date.class);

                method.invoke(_accClosureMasterRemoteModel, toDate);
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

        if (_accClosureMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_accClosureMasterRemoteModel, itemMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDescription() {
        return _description;
    }

    @Override
    public void setDescription(String description) {
        _description = description;

        if (_accClosureMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDescription", String.class);

                method.invoke(_accClosureMasterRemoteModel, description);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getReportName() {
        return _reportName;
    }

    @Override
    public void setReportName(String reportName) {
        _reportName = reportName;

        if (_accClosureMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setReportName", String.class);

                method.invoke(_accClosureMasterRemoteModel, reportName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRsType() {
        return _rsType;
    }

    @Override
    public void setRsType(int rsType) {
        _rsType = rsType;

        if (_accClosureMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRsType", int.class);

                method.invoke(_accClosureMasterRemoteModel, rsType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getProductIdentifier() {
        return _productIdentifier;
    }

    @Override
    public void setProductIdentifier(String productIdentifier) {
        _productIdentifier = productIdentifier;

        if (_accClosureMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProductIdentifier",
                        String.class);

                method.invoke(_accClosureMasterRemoteModel, productIdentifier);
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

        if (_accClosureMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_accClosureMasterRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getWorkflowStatus() {
        return _workflowStatus;
    }

    @Override
    public void setWorkflowStatus(int workflowStatus) {
        _workflowStatus = workflowStatus;

        if (_accClosureMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setWorkflowStatus", int.class);

                method.invoke(_accClosureMasterRemoteModel, workflowStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getModuleType() {
        return _moduleType;
    }

    @Override
    public void setModuleType(String moduleType) {
        _moduleType = moduleType;

        if (_accClosureMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModuleType", String.class);

                method.invoke(_accClosureMasterRemoteModel, moduleType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getFromDate() {
        return _fromDate;
    }

    @Override
    public void setFromDate(Date fromDate) {
        _fromDate = fromDate;

        if (_accClosureMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setFromDate", Date.class);

                method.invoke(_accClosureMasterRemoteModel, fromDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getContractType() {
        return _contractType;
    }

    @Override
    public void setContractType(int contractType) {
        _contractType = contractType;

        if (_accClosureMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractType", int.class);

                method.invoke(_accClosureMasterRemoteModel, contractType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getGlCompanyMasterSid() {
        return _glCompanyMasterSid;
    }

    @Override
    public void setGlCompanyMasterSid(int glCompanyMasterSid) {
        _glCompanyMasterSid = glCompanyMasterSid;

        if (_accClosureMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setGlCompanyMasterSid",
                        int.class);

                method.invoke(_accClosureMasterRemoteModel, glCompanyMasterSid);
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

        if (_accClosureMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_accClosureMasterRemoteModel, createdDate);
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

        if (_accClosureMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_accClosureMasterRemoteModel, createdBy);
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

        if (_accClosureMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractMasterSid",
                        int.class);

                method.invoke(_accClosureMasterRemoteModel, contractMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAccrualPeriod() {
        return _accrualPeriod;
    }

    @Override
    public void setAccrualPeriod(String accrualPeriod) {
        _accrualPeriod = accrualPeriod;

        if (_accClosureMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccrualPeriod", String.class);

                method.invoke(_accClosureMasterRemoteModel, accrualPeriod);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyGroupSid() {
        return _companyGroupSid;
    }

    @Override
    public void setCompanyGroupSid(String companyGroupSid) {
        _companyGroupSid = companyGroupSid;

        if (_accClosureMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyGroupSid",
                        String.class);

                method.invoke(_accClosureMasterRemoteModel, companyGroupSid);
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

        if (_accClosureMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccClosureMasterSid",
                        int.class);

                method.invoke(_accClosureMasterRemoteModel, accClosureMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRsCategory() {
        return _rsCategory;
    }

    @Override
    public void setRsCategory(int rsCategory) {
        _rsCategory = rsCategory;

        if (_accClosureMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRsCategory", int.class);

                method.invoke(_accClosureMasterRemoteModel, rsCategory);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getAdjustmentType() {
        return _adjustmentType;
    }

    @Override
    public void setAdjustmentType(int adjustmentType) {
        _adjustmentType = adjustmentType;

        if (_accClosureMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustmentType", int.class);

                method.invoke(_accClosureMasterRemoteModel, adjustmentType);
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

        if (_accClosureMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_accClosureMasterRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemGroupSid() {
        return _itemGroupSid;
    }

    @Override
    public void setItemGroupSid(String itemGroupSid) {
        _itemGroupSid = itemGroupSid;

        if (_accClosureMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemGroupSid", String.class);

                method.invoke(_accClosureMasterRemoteModel, itemGroupSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRebateProgramType() {
        return _rebateProgramType;
    }

    @Override
    public void setRebateProgramType(int rebateProgramType) {
        _rebateProgramType = rebateProgramType;

        if (_accClosureMasterRemoteModel != null) {
            try {
                Class<?> clazz = _accClosureMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRebateProgramType",
                        int.class);

                method.invoke(_accClosureMasterRemoteModel, rebateProgramType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getAccClosureMasterRemoteModel() {
        return _accClosureMasterRemoteModel;
    }

    public void setAccClosureMasterRemoteModel(
        BaseModel<?> accClosureMasterRemoteModel) {
        _accClosureMasterRemoteModel = accClosureMasterRemoteModel;
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

        Class<?> remoteModelClass = _accClosureMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_accClosureMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            AccClosureMasterLocalServiceUtil.addAccClosureMaster(this);
        } else {
            AccClosureMasterLocalServiceUtil.updateAccClosureMaster(this);
        }
    }

    @Override
    public AccClosureMaster toEscapedModel() {
        return (AccClosureMaster) ProxyUtil.newProxyInstance(AccClosureMaster.class.getClassLoader(),
            new Class[] { AccClosureMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        AccClosureMasterClp clone = new AccClosureMasterClp();

        clone.setSaveFlag(getSaveFlag());
        clone.setAccountNo(getAccountNo());
        clone.setToDate(getToDate());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setDescription(getDescription());
        clone.setReportName(getReportName());
        clone.setRsType(getRsType());
        clone.setProductIdentifier(getProductIdentifier());
        clone.setModifiedDate(getModifiedDate());
        clone.setWorkflowStatus(getWorkflowStatus());
        clone.setModuleType(getModuleType());
        clone.setFromDate(getFromDate());
        clone.setContractType(getContractType());
        clone.setGlCompanyMasterSid(getGlCompanyMasterSid());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setContractMasterSid(getContractMasterSid());
        clone.setAccrualPeriod(getAccrualPeriod());
        clone.setCompanyGroupSid(getCompanyGroupSid());
        clone.setAccClosureMasterSid(getAccClosureMasterSid());
        clone.setRsCategory(getRsCategory());
        clone.setAdjustmentType(getAdjustmentType());
        clone.setModifiedBy(getModifiedBy());
        clone.setItemGroupSid(getItemGroupSid());
        clone.setRebateProgramType(getRebateProgramType());

        return clone;
    }

    @Override
    public int compareTo(AccClosureMaster accClosureMaster) {
        int primaryKey = accClosureMaster.getPrimaryKey();

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

        if (!(obj instanceof AccClosureMasterClp)) {
            return false;
        }

        AccClosureMasterClp accClosureMaster = (AccClosureMasterClp) obj;

        int primaryKey = accClosureMaster.getPrimaryKey();

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
        StringBundler sb = new StringBundler(51);

        sb.append("{saveFlag=");
        sb.append(getSaveFlag());
        sb.append(", accountNo=");
        sb.append(getAccountNo());
        sb.append(", toDate=");
        sb.append(getToDate());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", reportName=");
        sb.append(getReportName());
        sb.append(", rsType=");
        sb.append(getRsType());
        sb.append(", productIdentifier=");
        sb.append(getProductIdentifier());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", workflowStatus=");
        sb.append(getWorkflowStatus());
        sb.append(", moduleType=");
        sb.append(getModuleType());
        sb.append(", fromDate=");
        sb.append(getFromDate());
        sb.append(", contractType=");
        sb.append(getContractType());
        sb.append(", glCompanyMasterSid=");
        sb.append(getGlCompanyMasterSid());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", contractMasterSid=");
        sb.append(getContractMasterSid());
        sb.append(", accrualPeriod=");
        sb.append(getAccrualPeriod());
        sb.append(", companyGroupSid=");
        sb.append(getCompanyGroupSid());
        sb.append(", accClosureMasterSid=");
        sb.append(getAccClosureMasterSid());
        sb.append(", rsCategory=");
        sb.append(getRsCategory());
        sb.append(", adjustmentType=");
        sb.append(getAdjustmentType());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", itemGroupSid=");
        sb.append(getItemGroupSid());
        sb.append(", rebateProgramType=");
        sb.append(getRebateProgramType());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(79);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.AccClosureMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>saveFlag</column-name><column-value><![CDATA[");
        sb.append(getSaveFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accountNo</column-name><column-value><![CDATA[");
        sb.append(getAccountNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>toDate</column-name><column-value><![CDATA[");
        sb.append(getToDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reportName</column-name><column-value><![CDATA[");
        sb.append(getReportName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsType</column-name><column-value><![CDATA[");
        sb.append(getRsType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>productIdentifier</column-name><column-value><![CDATA[");
        sb.append(getProductIdentifier());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>workflowStatus</column-name><column-value><![CDATA[");
        sb.append(getWorkflowStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>moduleType</column-name><column-value><![CDATA[");
        sb.append(getModuleType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fromDate</column-name><column-value><![CDATA[");
        sb.append(getFromDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractType</column-name><column-value><![CDATA[");
        sb.append(getContractType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>glCompanyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getGlCompanyMasterSid());
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
            "<column><column-name>contractMasterSid</column-name><column-value><![CDATA[");
        sb.append(getContractMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accrualPeriod</column-name><column-value><![CDATA[");
        sb.append(getAccrualPeriod());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyGroupSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyGroupSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accClosureMasterSid</column-name><column-value><![CDATA[");
        sb.append(getAccClosureMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsCategory</column-name><column-value><![CDATA[");
        sb.append(getRsCategory());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>adjustmentType</column-name><column-value><![CDATA[");
        sb.append(getAdjustmentType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemGroupSid</column-name><column-value><![CDATA[");
        sb.append(getItemGroupSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebateProgramType</column-name><column-value><![CDATA[");
        sb.append(getRebateProgramType());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
