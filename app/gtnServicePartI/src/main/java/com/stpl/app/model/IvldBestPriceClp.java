package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.IvldBestPriceLocalServiceUtil;

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


public class IvldBestPriceClp extends BaseModelImpl<IvldBestPrice>
    implements IvldBestPrice {
    private String _itemDesc;
    private String _bestPriceIntfid;
    private String _accountId;
    private String _sellingPrice;
    private String _period;
    private String _itemId;
    private Date _modifiedDate;
    private String _createdBy;
    private Date _createdDate;
    private String _source;
    private String _addChgDelIndicator;
    private String _initialDiscount;
    private String _errorCode;
    private String _modifiedBy;
    private Date _intfInsertedDate;
    private String _itemNo;
    private String _reprocessedFlag;
    private String _contractEndDate;
    private int _ivldBestPriceSid;
    private String _contractId;
    private String _beginningWacPackage;
    private String _reasonForFailure;
    private String _contractStartDate;
    private String _batchId;
    private String _errorField;
    private String _initialBestPrice;
    private String _contractNo;
    private boolean _checkRecord;
    private BaseModel<?> _ivldBestPriceRemoteModel;

    public IvldBestPriceClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return IvldBestPrice.class;
    }

    @Override
    public String getModelClassName() {
        return IvldBestPrice.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _ivldBestPriceSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIvldBestPriceSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ivldBestPriceSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemDesc", getItemDesc());
        attributes.put("bestPriceIntfid", getBestPriceIntfid());
        attributes.put("accountId", getAccountId());
        attributes.put("sellingPrice", getSellingPrice());
        attributes.put("period", getPeriod());
        attributes.put("itemId", getItemId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("source", getSource());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("initialDiscount", getInitialDiscount());
        attributes.put("errorCode", getErrorCode());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("itemNo", getItemNo());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("contractEndDate", getContractEndDate());
        attributes.put("ivldBestPriceSid", getIvldBestPriceSid());
        attributes.put("contractId", getContractId());
        attributes.put("beginningWacPackage", getBeginningWacPackage());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("contractStartDate", getContractStartDate());
        attributes.put("batchId", getBatchId());
        attributes.put("errorField", getErrorField());
        attributes.put("initialBestPrice", getInitialBestPrice());
        attributes.put("contractNo", getContractNo());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String itemDesc = (String) attributes.get("itemDesc");

        if (itemDesc != null) {
            setItemDesc(itemDesc);
        }

        String bestPriceIntfid = (String) attributes.get("bestPriceIntfid");

        if (bestPriceIntfid != null) {
            setBestPriceIntfid(bestPriceIntfid);
        }

        String accountId = (String) attributes.get("accountId");

        if (accountId != null) {
            setAccountId(accountId);
        }

        String sellingPrice = (String) attributes.get("sellingPrice");

        if (sellingPrice != null) {
            setSellingPrice(sellingPrice);
        }

        String period = (String) attributes.get("period");

        if (period != null) {
            setPeriod(period);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
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

        String initialDiscount = (String) attributes.get("initialDiscount");

        if (initialDiscount != null) {
            setInitialDiscount(initialDiscount);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date intfInsertedDate = (Date) attributes.get("intfInsertedDate");

        if (intfInsertedDate != null) {
            setIntfInsertedDate(intfInsertedDate);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String contractEndDate = (String) attributes.get("contractEndDate");

        if (contractEndDate != null) {
            setContractEndDate(contractEndDate);
        }

        Integer ivldBestPriceSid = (Integer) attributes.get("ivldBestPriceSid");

        if (ivldBestPriceSid != null) {
            setIvldBestPriceSid(ivldBestPriceSid);
        }

        String contractId = (String) attributes.get("contractId");

        if (contractId != null) {
            setContractId(contractId);
        }

        String beginningWacPackage = (String) attributes.get(
                "beginningWacPackage");

        if (beginningWacPackage != null) {
            setBeginningWacPackage(beginningWacPackage);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String contractStartDate = (String) attributes.get("contractStartDate");

        if (contractStartDate != null) {
            setContractStartDate(contractStartDate);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        String initialBestPrice = (String) attributes.get("initialBestPrice");

        if (initialBestPrice != null) {
            setInitialBestPrice(initialBestPrice);
        }

        String contractNo = (String) attributes.get("contractNo");

        if (contractNo != null) {
            setContractNo(contractNo);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    @Override
    public String getItemDesc() {
        return _itemDesc;
    }

    @Override
    public void setItemDesc(String itemDesc) {
        _itemDesc = itemDesc;

        if (_ivldBestPriceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldBestPriceRemoteModel.getClass();

                Method method = clazz.getMethod("setItemDesc", String.class);

                method.invoke(_ivldBestPriceRemoteModel, itemDesc);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBestPriceIntfid() {
        return _bestPriceIntfid;
    }

    @Override
    public void setBestPriceIntfid(String bestPriceIntfid) {
        _bestPriceIntfid = bestPriceIntfid;

        if (_ivldBestPriceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldBestPriceRemoteModel.getClass();

                Method method = clazz.getMethod("setBestPriceIntfid",
                        String.class);

                method.invoke(_ivldBestPriceRemoteModel, bestPriceIntfid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAccountId() {
        return _accountId;
    }

    @Override
    public void setAccountId(String accountId) {
        _accountId = accountId;

        if (_ivldBestPriceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldBestPriceRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountId", String.class);

                method.invoke(_ivldBestPriceRemoteModel, accountId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSellingPrice() {
        return _sellingPrice;
    }

    @Override
    public void setSellingPrice(String sellingPrice) {
        _sellingPrice = sellingPrice;

        if (_ivldBestPriceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldBestPriceRemoteModel.getClass();

                Method method = clazz.getMethod("setSellingPrice", String.class);

                method.invoke(_ivldBestPriceRemoteModel, sellingPrice);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPeriod() {
        return _period;
    }

    @Override
    public void setPeriod(String period) {
        _period = period;

        if (_ivldBestPriceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldBestPriceRemoteModel.getClass();

                Method method = clazz.getMethod("setPeriod", String.class);

                method.invoke(_ivldBestPriceRemoteModel, period);
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

        if (_ivldBestPriceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldBestPriceRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_ivldBestPriceRemoteModel, itemId);
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

        if (_ivldBestPriceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldBestPriceRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_ivldBestPriceRemoteModel, modifiedDate);
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

        if (_ivldBestPriceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldBestPriceRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_ivldBestPriceRemoteModel, createdBy);
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

        if (_ivldBestPriceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldBestPriceRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_ivldBestPriceRemoteModel, createdDate);
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

        if (_ivldBestPriceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldBestPriceRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_ivldBestPriceRemoteModel, source);
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

        if (_ivldBestPriceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldBestPriceRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_ivldBestPriceRemoteModel, addChgDelIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getInitialDiscount() {
        return _initialDiscount;
    }

    @Override
    public void setInitialDiscount(String initialDiscount) {
        _initialDiscount = initialDiscount;

        if (_ivldBestPriceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldBestPriceRemoteModel.getClass();

                Method method = clazz.getMethod("setInitialDiscount",
                        String.class);

                method.invoke(_ivldBestPriceRemoteModel, initialDiscount);
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

        if (_ivldBestPriceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldBestPriceRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorCode", String.class);

                method.invoke(_ivldBestPriceRemoteModel, errorCode);
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

        if (_ivldBestPriceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldBestPriceRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_ivldBestPriceRemoteModel, modifiedBy);
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

        if (_ivldBestPriceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldBestPriceRemoteModel.getClass();

                Method method = clazz.getMethod("setIntfInsertedDate",
                        Date.class);

                method.invoke(_ivldBestPriceRemoteModel, intfInsertedDate);
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

        if (_ivldBestPriceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldBestPriceRemoteModel.getClass();

                Method method = clazz.getMethod("setItemNo", String.class);

                method.invoke(_ivldBestPriceRemoteModel, itemNo);
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

        if (_ivldBestPriceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldBestPriceRemoteModel.getClass();

                Method method = clazz.getMethod("setReprocessedFlag",
                        String.class);

                method.invoke(_ivldBestPriceRemoteModel, reprocessedFlag);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getContractEndDate() {
        return _contractEndDate;
    }

    @Override
    public void setContractEndDate(String contractEndDate) {
        _contractEndDate = contractEndDate;

        if (_ivldBestPriceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldBestPriceRemoteModel.getClass();

                Method method = clazz.getMethod("setContractEndDate",
                        String.class);

                method.invoke(_ivldBestPriceRemoteModel, contractEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIvldBestPriceSid() {
        return _ivldBestPriceSid;
    }

    @Override
    public void setIvldBestPriceSid(int ivldBestPriceSid) {
        _ivldBestPriceSid = ivldBestPriceSid;

        if (_ivldBestPriceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldBestPriceRemoteModel.getClass();

                Method method = clazz.getMethod("setIvldBestPriceSid", int.class);

                method.invoke(_ivldBestPriceRemoteModel, ivldBestPriceSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getContractId() {
        return _contractId;
    }

    @Override
    public void setContractId(String contractId) {
        _contractId = contractId;

        if (_ivldBestPriceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldBestPriceRemoteModel.getClass();

                Method method = clazz.getMethod("setContractId", String.class);

                method.invoke(_ivldBestPriceRemoteModel, contractId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBeginningWacPackage() {
        return _beginningWacPackage;
    }

    @Override
    public void setBeginningWacPackage(String beginningWacPackage) {
        _beginningWacPackage = beginningWacPackage;

        if (_ivldBestPriceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldBestPriceRemoteModel.getClass();

                Method method = clazz.getMethod("setBeginningWacPackage",
                        String.class);

                method.invoke(_ivldBestPriceRemoteModel, beginningWacPackage);
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

        if (_ivldBestPriceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldBestPriceRemoteModel.getClass();

                Method method = clazz.getMethod("setReasonForFailure",
                        String.class);

                method.invoke(_ivldBestPriceRemoteModel, reasonForFailure);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getContractStartDate() {
        return _contractStartDate;
    }

    @Override
    public void setContractStartDate(String contractStartDate) {
        _contractStartDate = contractStartDate;

        if (_ivldBestPriceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldBestPriceRemoteModel.getClass();

                Method method = clazz.getMethod("setContractStartDate",
                        String.class);

                method.invoke(_ivldBestPriceRemoteModel, contractStartDate);
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

        if (_ivldBestPriceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldBestPriceRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_ivldBestPriceRemoteModel, batchId);
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

        if (_ivldBestPriceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldBestPriceRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorField", String.class);

                method.invoke(_ivldBestPriceRemoteModel, errorField);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getInitialBestPrice() {
        return _initialBestPrice;
    }

    @Override
    public void setInitialBestPrice(String initialBestPrice) {
        _initialBestPrice = initialBestPrice;

        if (_ivldBestPriceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldBestPriceRemoteModel.getClass();

                Method method = clazz.getMethod("setInitialBestPrice",
                        String.class);

                method.invoke(_ivldBestPriceRemoteModel, initialBestPrice);
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

        if (_ivldBestPriceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldBestPriceRemoteModel.getClass();

                Method method = clazz.getMethod("setContractNo", String.class);

                method.invoke(_ivldBestPriceRemoteModel, contractNo);
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

        if (_ivldBestPriceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldBestPriceRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_ivldBestPriceRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getIvldBestPriceRemoteModel() {
        return _ivldBestPriceRemoteModel;
    }

    public void setIvldBestPriceRemoteModel(
        BaseModel<?> ivldBestPriceRemoteModel) {
        _ivldBestPriceRemoteModel = ivldBestPriceRemoteModel;
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

        Class<?> remoteModelClass = _ivldBestPriceRemoteModel.getClass();

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

        Object returnValue = method.invoke(_ivldBestPriceRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IvldBestPriceLocalServiceUtil.addIvldBestPrice(this);
        } else {
            IvldBestPriceLocalServiceUtil.updateIvldBestPrice(this);
        }
    }

    @Override
    public IvldBestPrice toEscapedModel() {
        return (IvldBestPrice) ProxyUtil.newProxyInstance(IvldBestPrice.class.getClassLoader(),
            new Class[] { IvldBestPrice.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        IvldBestPriceClp clone = new IvldBestPriceClp();

        clone.setItemDesc(getItemDesc());
        clone.setBestPriceIntfid(getBestPriceIntfid());
        clone.setAccountId(getAccountId());
        clone.setSellingPrice(getSellingPrice());
        clone.setPeriod(getPeriod());
        clone.setItemId(getItemId());
        clone.setModifiedDate(getModifiedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setSource(getSource());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setInitialDiscount(getInitialDiscount());
        clone.setErrorCode(getErrorCode());
        clone.setModifiedBy(getModifiedBy());
        clone.setIntfInsertedDate(getIntfInsertedDate());
        clone.setItemNo(getItemNo());
        clone.setReprocessedFlag(getReprocessedFlag());
        clone.setContractEndDate(getContractEndDate());
        clone.setIvldBestPriceSid(getIvldBestPriceSid());
        clone.setContractId(getContractId());
        clone.setBeginningWacPackage(getBeginningWacPackage());
        clone.setReasonForFailure(getReasonForFailure());
        clone.setContractStartDate(getContractStartDate());
        clone.setBatchId(getBatchId());
        clone.setErrorField(getErrorField());
        clone.setInitialBestPrice(getInitialBestPrice());
        clone.setContractNo(getContractNo());
        clone.setCheckRecord(getCheckRecord());

        return clone;
    }

    @Override
    public int compareTo(IvldBestPrice ivldBestPrice) {
        int primaryKey = ivldBestPrice.getPrimaryKey();

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

        if (!(obj instanceof IvldBestPriceClp)) {
            return false;
        }

        IvldBestPriceClp ivldBestPrice = (IvldBestPriceClp) obj;

        int primaryKey = ivldBestPrice.getPrimaryKey();

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
        StringBundler sb = new StringBundler(57);

        sb.append("{itemDesc=");
        sb.append(getItemDesc());
        sb.append(", bestPriceIntfid=");
        sb.append(getBestPriceIntfid());
        sb.append(", accountId=");
        sb.append(getAccountId());
        sb.append(", sellingPrice=");
        sb.append(getSellingPrice());
        sb.append(", period=");
        sb.append(getPeriod());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", initialDiscount=");
        sb.append(getInitialDiscount());
        sb.append(", errorCode=");
        sb.append(getErrorCode());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", intfInsertedDate=");
        sb.append(getIntfInsertedDate());
        sb.append(", itemNo=");
        sb.append(getItemNo());
        sb.append(", reprocessedFlag=");
        sb.append(getReprocessedFlag());
        sb.append(", contractEndDate=");
        sb.append(getContractEndDate());
        sb.append(", ivldBestPriceSid=");
        sb.append(getIvldBestPriceSid());
        sb.append(", contractId=");
        sb.append(getContractId());
        sb.append(", beginningWacPackage=");
        sb.append(getBeginningWacPackage());
        sb.append(", reasonForFailure=");
        sb.append(getReasonForFailure());
        sb.append(", contractStartDate=");
        sb.append(getContractStartDate());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", errorField=");
        sb.append(getErrorField());
        sb.append(", initialBestPrice=");
        sb.append(getInitialBestPrice());
        sb.append(", contractNo=");
        sb.append(getContractNo());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(88);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.IvldBestPrice");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>itemDesc</column-name><column-value><![CDATA[");
        sb.append(getItemDesc());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>bestPriceIntfid</column-name><column-value><![CDATA[");
        sb.append(getBestPriceIntfid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accountId</column-name><column-value><![CDATA[");
        sb.append(getAccountId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sellingPrice</column-name><column-value><![CDATA[");
        sb.append(getSellingPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>period</column-name><column-value><![CDATA[");
        sb.append(getPeriod());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemId</column-name><column-value><![CDATA[");
        sb.append(getItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
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
            "<column><column-name>initialDiscount</column-name><column-value><![CDATA[");
        sb.append(getInitialDiscount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>errorCode</column-name><column-value><![CDATA[");
        sb.append(getErrorCode());
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
            "<column><column-name>itemNo</column-name><column-value><![CDATA[");
        sb.append(getItemNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reprocessedFlag</column-name><column-value><![CDATA[");
        sb.append(getReprocessedFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractEndDate</column-name><column-value><![CDATA[");
        sb.append(getContractEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ivldBestPriceSid</column-name><column-value><![CDATA[");
        sb.append(getIvldBestPriceSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractId</column-name><column-value><![CDATA[");
        sb.append(getContractId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>beginningWacPackage</column-name><column-value><![CDATA[");
        sb.append(getBeginningWacPackage());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reasonForFailure</column-name><column-value><![CDATA[");
        sb.append(getReasonForFailure());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractStartDate</column-name><column-value><![CDATA[");
        sb.append(getContractStartDate());
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
            "<column><column-name>initialBestPrice</column-name><column-value><![CDATA[");
        sb.append(getInitialBestPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractNo</column-name><column-value><![CDATA[");
        sb.append(getContractNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
