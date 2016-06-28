package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.IvldFormulaDetailsLocalServiceUtil;

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


public class IvldFormulaDetailsClp extends BaseModelImpl<IvldFormulaDetails>
    implements IvldFormulaDetails {
    private String _endDate;
    private String _rebatePercent1;
    private String _itemId;
    private String _rebatePercent2;
    private String _formulaDesc;
    private Date _modifiedDate;
    private String _rebatePercent3;
    private String _createdBy;
    private Date _createdDate;
    private String _source;
    private String _addChgDelIndicator;
    private String _errorCode;
    private String _formulaId;
    private String _modifiedBy;
    private Date _intfInsertedDate;
    private String _reprocessedFlag;
    private String _formulaDetailsIntfid;
    private String _reasonForFailure;
    private String _contractPrice1;
    private String _companyId;
    private String _contractPrice2;
    private String _formulaNo;
    private String _startDate;
    private String _batchId;
    private String _errorField;
    private String _contractPrice3;
    private int _ivldFormulaDetailsSid;
    private BaseModel<?> _ivldFormulaDetailsRemoteModel;

    public IvldFormulaDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return IvldFormulaDetails.class;
    }

    @Override
    public String getModelClassName() {
        return IvldFormulaDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _ivldFormulaDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIvldFormulaDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ivldFormulaDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("endDate", getEndDate());
        attributes.put("rebatePercent1", getRebatePercent1());
        attributes.put("itemId", getItemId());
        attributes.put("rebatePercent2", getRebatePercent2());
        attributes.put("formulaDesc", getFormulaDesc());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("rebatePercent3", getRebatePercent3());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("source", getSource());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("errorCode", getErrorCode());
        attributes.put("formulaId", getFormulaId());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("formulaDetailsIntfid", getFormulaDetailsIntfid());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("contractPrice1", getContractPrice1());
        attributes.put("companyId", getCompanyId());
        attributes.put("contractPrice2", getContractPrice2());
        attributes.put("formulaNo", getFormulaNo());
        attributes.put("startDate", getStartDate());
        attributes.put("batchId", getBatchId());
        attributes.put("errorField", getErrorField());
        attributes.put("contractPrice3", getContractPrice3());
        attributes.put("ivldFormulaDetailsSid", getIvldFormulaDetailsSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String endDate = (String) attributes.get("endDate");

        if (endDate != null) {
            setEndDate(endDate);
        }

        String rebatePercent1 = (String) attributes.get("rebatePercent1");

        if (rebatePercent1 != null) {
            setRebatePercent1(rebatePercent1);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String rebatePercent2 = (String) attributes.get("rebatePercent2");

        if (rebatePercent2 != null) {
            setRebatePercent2(rebatePercent2);
        }

        String formulaDesc = (String) attributes.get("formulaDesc");

        if (formulaDesc != null) {
            setFormulaDesc(formulaDesc);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String rebatePercent3 = (String) attributes.get("rebatePercent3");

        if (rebatePercent3 != null) {
            setRebatePercent3(rebatePercent3);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        String formulaId = (String) attributes.get("formulaId");

        if (formulaId != null) {
            setFormulaId(formulaId);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date intfInsertedDate = (Date) attributes.get("intfInsertedDate");

        if (intfInsertedDate != null) {
            setIntfInsertedDate(intfInsertedDate);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String formulaDetailsIntfid = (String) attributes.get(
                "formulaDetailsIntfid");

        if (formulaDetailsIntfid != null) {
            setFormulaDetailsIntfid(formulaDetailsIntfid);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String contractPrice1 = (String) attributes.get("contractPrice1");

        if (contractPrice1 != null) {
            setContractPrice1(contractPrice1);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        String contractPrice2 = (String) attributes.get("contractPrice2");

        if (contractPrice2 != null) {
            setContractPrice2(contractPrice2);
        }

        String formulaNo = (String) attributes.get("formulaNo");

        if (formulaNo != null) {
            setFormulaNo(formulaNo);
        }

        String startDate = (String) attributes.get("startDate");

        if (startDate != null) {
            setStartDate(startDate);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        String contractPrice3 = (String) attributes.get("contractPrice3");

        if (contractPrice3 != null) {
            setContractPrice3(contractPrice3);
        }

        Integer ivldFormulaDetailsSid = (Integer) attributes.get(
                "ivldFormulaDetailsSid");

        if (ivldFormulaDetailsSid != null) {
            setIvldFormulaDetailsSid(ivldFormulaDetailsSid);
        }
    }

    @Override
    public String getEndDate() {
        return _endDate;
    }

    @Override
    public void setEndDate(String endDate) {
        _endDate = endDate;

        if (_ivldFormulaDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ivldFormulaDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setEndDate", String.class);

                method.invoke(_ivldFormulaDetailsRemoteModel, endDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRebatePercent1() {
        return _rebatePercent1;
    }

    @Override
    public void setRebatePercent1(String rebatePercent1) {
        _rebatePercent1 = rebatePercent1;

        if (_ivldFormulaDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ivldFormulaDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRebatePercent1",
                        String.class);

                method.invoke(_ivldFormulaDetailsRemoteModel, rebatePercent1);
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

        if (_ivldFormulaDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ivldFormulaDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_ivldFormulaDetailsRemoteModel, itemId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRebatePercent2() {
        return _rebatePercent2;
    }

    @Override
    public void setRebatePercent2(String rebatePercent2) {
        _rebatePercent2 = rebatePercent2;

        if (_ivldFormulaDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ivldFormulaDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRebatePercent2",
                        String.class);

                method.invoke(_ivldFormulaDetailsRemoteModel, rebatePercent2);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFormulaDesc() {
        return _formulaDesc;
    }

    @Override
    public void setFormulaDesc(String formulaDesc) {
        _formulaDesc = formulaDesc;

        if (_ivldFormulaDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ivldFormulaDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaDesc", String.class);

                method.invoke(_ivldFormulaDetailsRemoteModel, formulaDesc);
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

        if (_ivldFormulaDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ivldFormulaDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_ivldFormulaDetailsRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRebatePercent3() {
        return _rebatePercent3;
    }

    @Override
    public void setRebatePercent3(String rebatePercent3) {
        _rebatePercent3 = rebatePercent3;

        if (_ivldFormulaDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ivldFormulaDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRebatePercent3",
                        String.class);

                method.invoke(_ivldFormulaDetailsRemoteModel, rebatePercent3);
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

        if (_ivldFormulaDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ivldFormulaDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_ivldFormulaDetailsRemoteModel, createdBy);
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

        if (_ivldFormulaDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ivldFormulaDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_ivldFormulaDetailsRemoteModel, createdDate);
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

        if (_ivldFormulaDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ivldFormulaDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_ivldFormulaDetailsRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAddChgDelIndicator() {
        return _addChgDelIndicator;
    }

    @Override
    public void setAddChgDelIndicator(String addChgDelIndicator) {
        _addChgDelIndicator = addChgDelIndicator;

        if (_ivldFormulaDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ivldFormulaDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_ivldFormulaDetailsRemoteModel, addChgDelIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getErrorCode() {
        return _errorCode;
    }

    @Override
    public void setErrorCode(String errorCode) {
        _errorCode = errorCode;

        if (_ivldFormulaDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ivldFormulaDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorCode", String.class);

                method.invoke(_ivldFormulaDetailsRemoteModel, errorCode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFormulaId() {
        return _formulaId;
    }

    @Override
    public void setFormulaId(String formulaId) {
        _formulaId = formulaId;

        if (_ivldFormulaDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ivldFormulaDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaId", String.class);

                method.invoke(_ivldFormulaDetailsRemoteModel, formulaId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getModifiedBy() {
        return _modifiedBy;
    }

    @Override
    public void setModifiedBy(String modifiedBy) {
        _modifiedBy = modifiedBy;

        if (_ivldFormulaDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ivldFormulaDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_ivldFormulaDetailsRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getIntfInsertedDate() {
        return _intfInsertedDate;
    }

    @Override
    public void setIntfInsertedDate(Date intfInsertedDate) {
        _intfInsertedDate = intfInsertedDate;

        if (_ivldFormulaDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ivldFormulaDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setIntfInsertedDate",
                        Date.class);

                method.invoke(_ivldFormulaDetailsRemoteModel, intfInsertedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getReprocessedFlag() {
        return _reprocessedFlag;
    }

    @Override
    public void setReprocessedFlag(String reprocessedFlag) {
        _reprocessedFlag = reprocessedFlag;

        if (_ivldFormulaDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ivldFormulaDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setReprocessedFlag",
                        String.class);

                method.invoke(_ivldFormulaDetailsRemoteModel, reprocessedFlag);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFormulaDetailsIntfid() {
        return _formulaDetailsIntfid;
    }

    @Override
    public void setFormulaDetailsIntfid(String formulaDetailsIntfid) {
        _formulaDetailsIntfid = formulaDetailsIntfid;

        if (_ivldFormulaDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ivldFormulaDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaDetailsIntfid",
                        String.class);

                method.invoke(_ivldFormulaDetailsRemoteModel,
                    formulaDetailsIntfid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getReasonForFailure() {
        return _reasonForFailure;
    }

    @Override
    public void setReasonForFailure(String reasonForFailure) {
        _reasonForFailure = reasonForFailure;

        if (_ivldFormulaDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ivldFormulaDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setReasonForFailure",
                        String.class);

                method.invoke(_ivldFormulaDetailsRemoteModel, reasonForFailure);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getContractPrice1() {
        return _contractPrice1;
    }

    @Override
    public void setContractPrice1(String contractPrice1) {
        _contractPrice1 = contractPrice1;

        if (_ivldFormulaDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ivldFormulaDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractPrice1",
                        String.class);

                method.invoke(_ivldFormulaDetailsRemoteModel, contractPrice1);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyId() {
        return _companyId;
    }

    @Override
    public void setCompanyId(String companyId) {
        _companyId = companyId;

        if (_ivldFormulaDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ivldFormulaDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyId", String.class);

                method.invoke(_ivldFormulaDetailsRemoteModel, companyId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getContractPrice2() {
        return _contractPrice2;
    }

    @Override
    public void setContractPrice2(String contractPrice2) {
        _contractPrice2 = contractPrice2;

        if (_ivldFormulaDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ivldFormulaDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractPrice2",
                        String.class);

                method.invoke(_ivldFormulaDetailsRemoteModel, contractPrice2);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFormulaNo() {
        return _formulaNo;
    }

    @Override
    public void setFormulaNo(String formulaNo) {
        _formulaNo = formulaNo;

        if (_ivldFormulaDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ivldFormulaDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaNo", String.class);

                method.invoke(_ivldFormulaDetailsRemoteModel, formulaNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getStartDate() {
        return _startDate;
    }

    @Override
    public void setStartDate(String startDate) {
        _startDate = startDate;

        if (_ivldFormulaDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ivldFormulaDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setStartDate", String.class);

                method.invoke(_ivldFormulaDetailsRemoteModel, startDate);
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

        if (_ivldFormulaDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ivldFormulaDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_ivldFormulaDetailsRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getErrorField() {
        return _errorField;
    }

    @Override
    public void setErrorField(String errorField) {
        _errorField = errorField;

        if (_ivldFormulaDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ivldFormulaDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorField", String.class);

                method.invoke(_ivldFormulaDetailsRemoteModel, errorField);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getContractPrice3() {
        return _contractPrice3;
    }

    @Override
    public void setContractPrice3(String contractPrice3) {
        _contractPrice3 = contractPrice3;

        if (_ivldFormulaDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ivldFormulaDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractPrice3",
                        String.class);

                method.invoke(_ivldFormulaDetailsRemoteModel, contractPrice3);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIvldFormulaDetailsSid() {
        return _ivldFormulaDetailsSid;
    }

    @Override
    public void setIvldFormulaDetailsSid(int ivldFormulaDetailsSid) {
        _ivldFormulaDetailsSid = ivldFormulaDetailsSid;

        if (_ivldFormulaDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ivldFormulaDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setIvldFormulaDetailsSid",
                        int.class);

                method.invoke(_ivldFormulaDetailsRemoteModel,
                    ivldFormulaDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getIvldFormulaDetailsRemoteModel() {
        return _ivldFormulaDetailsRemoteModel;
    }

    public void setIvldFormulaDetailsRemoteModel(
        BaseModel<?> ivldFormulaDetailsRemoteModel) {
        _ivldFormulaDetailsRemoteModel = ivldFormulaDetailsRemoteModel;
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

        Class<?> remoteModelClass = _ivldFormulaDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_ivldFormulaDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IvldFormulaDetailsLocalServiceUtil.addIvldFormulaDetails(this);
        } else {
            IvldFormulaDetailsLocalServiceUtil.updateIvldFormulaDetails(this);
        }
    }

    @Override
    public IvldFormulaDetails toEscapedModel() {
        return (IvldFormulaDetails) ProxyUtil.newProxyInstance(IvldFormulaDetails.class.getClassLoader(),
            new Class[] { IvldFormulaDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        IvldFormulaDetailsClp clone = new IvldFormulaDetailsClp();

        clone.setEndDate(getEndDate());
        clone.setRebatePercent1(getRebatePercent1());
        clone.setItemId(getItemId());
        clone.setRebatePercent2(getRebatePercent2());
        clone.setFormulaDesc(getFormulaDesc());
        clone.setModifiedDate(getModifiedDate());
        clone.setRebatePercent3(getRebatePercent3());
        clone.setCreatedBy(getCreatedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setSource(getSource());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setErrorCode(getErrorCode());
        clone.setFormulaId(getFormulaId());
        clone.setModifiedBy(getModifiedBy());
        clone.setIntfInsertedDate(getIntfInsertedDate());
        clone.setReprocessedFlag(getReprocessedFlag());
        clone.setFormulaDetailsIntfid(getFormulaDetailsIntfid());
        clone.setReasonForFailure(getReasonForFailure());
        clone.setContractPrice1(getContractPrice1());
        clone.setCompanyId(getCompanyId());
        clone.setContractPrice2(getContractPrice2());
        clone.setFormulaNo(getFormulaNo());
        clone.setStartDate(getStartDate());
        clone.setBatchId(getBatchId());
        clone.setErrorField(getErrorField());
        clone.setContractPrice3(getContractPrice3());
        clone.setIvldFormulaDetailsSid(getIvldFormulaDetailsSid());

        return clone;
    }

    @Override
    public int compareTo(IvldFormulaDetails ivldFormulaDetails) {
        int primaryKey = ivldFormulaDetails.getPrimaryKey();

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

        if (!(obj instanceof IvldFormulaDetailsClp)) {
            return false;
        }

        IvldFormulaDetailsClp ivldFormulaDetails = (IvldFormulaDetailsClp) obj;

        int primaryKey = ivldFormulaDetails.getPrimaryKey();

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
        StringBundler sb = new StringBundler(55);

        sb.append("{endDate=");
        sb.append(getEndDate());
        sb.append(", rebatePercent1=");
        sb.append(getRebatePercent1());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", rebatePercent2=");
        sb.append(getRebatePercent2());
        sb.append(", formulaDesc=");
        sb.append(getFormulaDesc());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", rebatePercent3=");
        sb.append(getRebatePercent3());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", errorCode=");
        sb.append(getErrorCode());
        sb.append(", formulaId=");
        sb.append(getFormulaId());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", intfInsertedDate=");
        sb.append(getIntfInsertedDate());
        sb.append(", reprocessedFlag=");
        sb.append(getReprocessedFlag());
        sb.append(", formulaDetailsIntfid=");
        sb.append(getFormulaDetailsIntfid());
        sb.append(", reasonForFailure=");
        sb.append(getReasonForFailure());
        sb.append(", contractPrice1=");
        sb.append(getContractPrice1());
        sb.append(", companyId=");
        sb.append(getCompanyId());
        sb.append(", contractPrice2=");
        sb.append(getContractPrice2());
        sb.append(", formulaNo=");
        sb.append(getFormulaNo());
        sb.append(", startDate=");
        sb.append(getStartDate());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", errorField=");
        sb.append(getErrorField());
        sb.append(", contractPrice3=");
        sb.append(getContractPrice3());
        sb.append(", ivldFormulaDetailsSid=");
        sb.append(getIvldFormulaDetailsSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(85);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.IvldFormulaDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>endDate</column-name><column-value><![CDATA[");
        sb.append(getEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebatePercent1</column-name><column-value><![CDATA[");
        sb.append(getRebatePercent1());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemId</column-name><column-value><![CDATA[");
        sb.append(getItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebatePercent2</column-name><column-value><![CDATA[");
        sb.append(getRebatePercent2());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>formulaDesc</column-name><column-value><![CDATA[");
        sb.append(getFormulaDesc());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebatePercent3</column-name><column-value><![CDATA[");
        sb.append(getRebatePercent3());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>addChgDelIndicator</column-name><column-value><![CDATA[");
        sb.append(getAddChgDelIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>errorCode</column-name><column-value><![CDATA[");
        sb.append(getErrorCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>formulaId</column-name><column-value><![CDATA[");
        sb.append(getFormulaId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>intfInsertedDate</column-name><column-value><![CDATA[");
        sb.append(getIntfInsertedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reprocessedFlag</column-name><column-value><![CDATA[");
        sb.append(getReprocessedFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>formulaDetailsIntfid</column-name><column-value><![CDATA[");
        sb.append(getFormulaDetailsIntfid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reasonForFailure</column-name><column-value><![CDATA[");
        sb.append(getReasonForFailure());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractPrice1</column-name><column-value><![CDATA[");
        sb.append(getContractPrice1());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyId</column-name><column-value><![CDATA[");
        sb.append(getCompanyId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractPrice2</column-name><column-value><![CDATA[");
        sb.append(getContractPrice2());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>formulaNo</column-name><column-value><![CDATA[");
        sb.append(getFormulaNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>startDate</column-name><column-value><![CDATA[");
        sb.append(getStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>errorField</column-name><column-value><![CDATA[");
        sb.append(getErrorField());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractPrice3</column-name><column-value><![CDATA[");
        sb.append(getContractPrice3());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ivldFormulaDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getIvldFormulaDetailsSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
