package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.GcmGlobalDetailsLocalServiceUtil;

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


public class GcmGlobalDetailsClp extends BaseModelImpl<GcmGlobalDetails>
    implements GcmGlobalDetails {
    private String _itemStatus;
    private String _formulaMethodId;
    private String _moduleName;
    private String _paymentFrequency;
    private Date _endDate;
    private Date _cfpStartDate;
    private Date _priceProtectionStartDate;
    private String _tempItemMasterSid;
    private String _brandName;
    private Date _modifiedDate;
    private int _contractMasterSid;
    private int _modifiedBy;
    private String _subModuleName;
    private String _theraputicClass;
    private int _gcmGlobalDetailsSid;
    private boolean _checkRecord;
    private String _paymentMethod;
    private Date _contractPriceEndDate;
    private int _psContractSid;
    private Date _priceProtectionEndDate;
    private Date _startDate;
    private String _screenName;
    private int _rsContractSid;
    private String _itemName;
    private String _sessionId;
    private String _cfpStatus;
    private int _rsModelSid;
    private int _cfpContractSid;
    private double _price;
    private Date _tempEndDate;
    private int _itemMasterSid;
    private String _itemType;
    private String _forecastingType;
    private String _itemId;
    private double _basePrice;
    private String _status;
    private String _formulaName;
    private int _workflowMasterSid;
    private double _priceTolerance;
    private int _createdBy;
    private Date _createdDate;
    private Date _tempStartDate;
    private Date _cfpEndDate;
    private int _psModelSid;
    private String _formulaId;
    private String _itemNo;
    private double _contractPrice;
    private int _ifpModelSid;
    private int _priceToleranceType;
    private int _rebateAmount;
    private int _userId;
    private int _projectionMasterSid;
    private Date _contractPriceStartDate;
    private int _priceToleranceFrequency;
    private String _ifpContractAttachedStatus;
    private int _rebatePlanSystemId;
    private String _rebatePlanName;
    private String _calendar;
    private String _pricingQualifierSid;
    private String _tempStatus;
    private Date _itemRebateEndDate;
    private int _priceToleranceInterval;
    private Date _itemRebateStartDate;
    private String _operation;
    private int _cfpModelSid;
    private int _itemStatusSid;
    private int _ifpContractSid;
    private BaseModel<?> _gcmGlobalDetailsRemoteModel;

    public GcmGlobalDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return GcmGlobalDetails.class;
    }

    @Override
    public String getModelClassName() {
        return GcmGlobalDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _gcmGlobalDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setGcmGlobalDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _gcmGlobalDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemStatus", getItemStatus());
        attributes.put("formulaMethodId", getFormulaMethodId());
        attributes.put("moduleName", getModuleName());
        attributes.put("paymentFrequency", getPaymentFrequency());
        attributes.put("endDate", getEndDate());
        attributes.put("cfpStartDate", getCfpStartDate());
        attributes.put("priceProtectionStartDate", getPriceProtectionStartDate());
        attributes.put("tempItemMasterSid", getTempItemMasterSid());
        attributes.put("brandName", getBrandName());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("subModuleName", getSubModuleName());
        attributes.put("theraputicClass", getTheraputicClass());
        attributes.put("gcmGlobalDetailsSid", getGcmGlobalDetailsSid());
        attributes.put("checkRecord", getCheckRecord());
        attributes.put("paymentMethod", getPaymentMethod());
        attributes.put("contractPriceEndDate", getContractPriceEndDate());
        attributes.put("psContractSid", getPsContractSid());
        attributes.put("priceProtectionEndDate", getPriceProtectionEndDate());
        attributes.put("startDate", getStartDate());
        attributes.put("screenName", getScreenName());
        attributes.put("rsContractSid", getRsContractSid());
        attributes.put("itemName", getItemName());
        attributes.put("sessionId", getSessionId());
        attributes.put("cfpStatus", getCfpStatus());
        attributes.put("rsModelSid", getRsModelSid());
        attributes.put("cfpContractSid", getCfpContractSid());
        attributes.put("price", getPrice());
        attributes.put("tempEndDate", getTempEndDate());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("itemType", getItemType());
        attributes.put("forecastingType", getForecastingType());
        attributes.put("itemId", getItemId());
        attributes.put("basePrice", getBasePrice());
        attributes.put("status", getStatus());
        attributes.put("formulaName", getFormulaName());
        attributes.put("workflowMasterSid", getWorkflowMasterSid());
        attributes.put("priceTolerance", getPriceTolerance());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("tempStartDate", getTempStartDate());
        attributes.put("cfpEndDate", getCfpEndDate());
        attributes.put("psModelSid", getPsModelSid());
        attributes.put("formulaId", getFormulaId());
        attributes.put("itemNo", getItemNo());
        attributes.put("contractPrice", getContractPrice());
        attributes.put("ifpModelSid", getIfpModelSid());
        attributes.put("priceToleranceType", getPriceToleranceType());
        attributes.put("rebateAmount", getRebateAmount());
        attributes.put("userId", getUserId());
        attributes.put("projectionMasterSid", getProjectionMasterSid());
        attributes.put("contractPriceStartDate", getContractPriceStartDate());
        attributes.put("priceToleranceFrequency", getPriceToleranceFrequency());
        attributes.put("ifpContractAttachedStatus",
            getIfpContractAttachedStatus());
        attributes.put("rebatePlanSystemId", getRebatePlanSystemId());
        attributes.put("rebatePlanName", getRebatePlanName());
        attributes.put("calendar", getCalendar());
        attributes.put("pricingQualifierSid", getPricingQualifierSid());
        attributes.put("tempStatus", getTempStatus());
        attributes.put("itemRebateEndDate", getItemRebateEndDate());
        attributes.put("priceToleranceInterval", getPriceToleranceInterval());
        attributes.put("itemRebateStartDate", getItemRebateStartDate());
        attributes.put("operation", getOperation());
        attributes.put("cfpModelSid", getCfpModelSid());
        attributes.put("itemStatusSid", getItemStatusSid());
        attributes.put("ifpContractSid", getIfpContractSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String itemStatus = (String) attributes.get("itemStatus");

        if (itemStatus != null) {
            setItemStatus(itemStatus);
        }

        String formulaMethodId = (String) attributes.get("formulaMethodId");

        if (formulaMethodId != null) {
            setFormulaMethodId(formulaMethodId);
        }

        String moduleName = (String) attributes.get("moduleName");

        if (moduleName != null) {
            setModuleName(moduleName);
        }

        String paymentFrequency = (String) attributes.get("paymentFrequency");

        if (paymentFrequency != null) {
            setPaymentFrequency(paymentFrequency);
        }

        Date endDate = (Date) attributes.get("endDate");

        if (endDate != null) {
            setEndDate(endDate);
        }

        Date cfpStartDate = (Date) attributes.get("cfpStartDate");

        if (cfpStartDate != null) {
            setCfpStartDate(cfpStartDate);
        }

        Date priceProtectionStartDate = (Date) attributes.get(
                "priceProtectionStartDate");

        if (priceProtectionStartDate != null) {
            setPriceProtectionStartDate(priceProtectionStartDate);
        }

        String tempItemMasterSid = (String) attributes.get("tempItemMasterSid");

        if (tempItemMasterSid != null) {
            setTempItemMasterSid(tempItemMasterSid);
        }

        String brandName = (String) attributes.get("brandName");

        if (brandName != null) {
            setBrandName(brandName);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer contractMasterSid = (Integer) attributes.get(
                "contractMasterSid");

        if (contractMasterSid != null) {
            setContractMasterSid(contractMasterSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String subModuleName = (String) attributes.get("subModuleName");

        if (subModuleName != null) {
            setSubModuleName(subModuleName);
        }

        String theraputicClass = (String) attributes.get("theraputicClass");

        if (theraputicClass != null) {
            setTheraputicClass(theraputicClass);
        }

        Integer gcmGlobalDetailsSid = (Integer) attributes.get(
                "gcmGlobalDetailsSid");

        if (gcmGlobalDetailsSid != null) {
            setGcmGlobalDetailsSid(gcmGlobalDetailsSid);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        String paymentMethod = (String) attributes.get("paymentMethod");

        if (paymentMethod != null) {
            setPaymentMethod(paymentMethod);
        }

        Date contractPriceEndDate = (Date) attributes.get(
                "contractPriceEndDate");

        if (contractPriceEndDate != null) {
            setContractPriceEndDate(contractPriceEndDate);
        }

        Integer psContractSid = (Integer) attributes.get("psContractSid");

        if (psContractSid != null) {
            setPsContractSid(psContractSid);
        }

        Date priceProtectionEndDate = (Date) attributes.get(
                "priceProtectionEndDate");

        if (priceProtectionEndDate != null) {
            setPriceProtectionEndDate(priceProtectionEndDate);
        }

        Date startDate = (Date) attributes.get("startDate");

        if (startDate != null) {
            setStartDate(startDate);
        }

        String screenName = (String) attributes.get("screenName");

        if (screenName != null) {
            setScreenName(screenName);
        }

        Integer rsContractSid = (Integer) attributes.get("rsContractSid");

        if (rsContractSid != null) {
            setRsContractSid(rsContractSid);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        String sessionId = (String) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        String cfpStatus = (String) attributes.get("cfpStatus");

        if (cfpStatus != null) {
            setCfpStatus(cfpStatus);
        }

        Integer rsModelSid = (Integer) attributes.get("rsModelSid");

        if (rsModelSid != null) {
            setRsModelSid(rsModelSid);
        }

        Integer cfpContractSid = (Integer) attributes.get("cfpContractSid");

        if (cfpContractSid != null) {
            setCfpContractSid(cfpContractSid);
        }

        Double price = (Double) attributes.get("price");

        if (price != null) {
            setPrice(price);
        }

        Date tempEndDate = (Date) attributes.get("tempEndDate");

        if (tempEndDate != null) {
            setTempEndDate(tempEndDate);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        String itemType = (String) attributes.get("itemType");

        if (itemType != null) {
            setItemType(itemType);
        }

        String forecastingType = (String) attributes.get("forecastingType");

        if (forecastingType != null) {
            setForecastingType(forecastingType);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        Double basePrice = (Double) attributes.get("basePrice");

        if (basePrice != null) {
            setBasePrice(basePrice);
        }

        String status = (String) attributes.get("status");

        if (status != null) {
            setStatus(status);
        }

        String formulaName = (String) attributes.get("formulaName");

        if (formulaName != null) {
            setFormulaName(formulaName);
        }

        Integer workflowMasterSid = (Integer) attributes.get(
                "workflowMasterSid");

        if (workflowMasterSid != null) {
            setWorkflowMasterSid(workflowMasterSid);
        }

        Double priceTolerance = (Double) attributes.get("priceTolerance");

        if (priceTolerance != null) {
            setPriceTolerance(priceTolerance);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Date tempStartDate = (Date) attributes.get("tempStartDate");

        if (tempStartDate != null) {
            setTempStartDate(tempStartDate);
        }

        Date cfpEndDate = (Date) attributes.get("cfpEndDate");

        if (cfpEndDate != null) {
            setCfpEndDate(cfpEndDate);
        }

        Integer psModelSid = (Integer) attributes.get("psModelSid");

        if (psModelSid != null) {
            setPsModelSid(psModelSid);
        }

        String formulaId = (String) attributes.get("formulaId");

        if (formulaId != null) {
            setFormulaId(formulaId);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        Double contractPrice = (Double) attributes.get("contractPrice");

        if (contractPrice != null) {
            setContractPrice(contractPrice);
        }

        Integer ifpModelSid = (Integer) attributes.get("ifpModelSid");

        if (ifpModelSid != null) {
            setIfpModelSid(ifpModelSid);
        }

        Integer priceToleranceType = (Integer) attributes.get(
                "priceToleranceType");

        if (priceToleranceType != null) {
            setPriceToleranceType(priceToleranceType);
        }

        Integer rebateAmount = (Integer) attributes.get("rebateAmount");

        if (rebateAmount != null) {
            setRebateAmount(rebateAmount);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Integer projectionMasterSid = (Integer) attributes.get(
                "projectionMasterSid");

        if (projectionMasterSid != null) {
            setProjectionMasterSid(projectionMasterSid);
        }

        Date contractPriceStartDate = (Date) attributes.get(
                "contractPriceStartDate");

        if (contractPriceStartDate != null) {
            setContractPriceStartDate(contractPriceStartDate);
        }

        Integer priceToleranceFrequency = (Integer) attributes.get(
                "priceToleranceFrequency");

        if (priceToleranceFrequency != null) {
            setPriceToleranceFrequency(priceToleranceFrequency);
        }

        String ifpContractAttachedStatus = (String) attributes.get(
                "ifpContractAttachedStatus");

        if (ifpContractAttachedStatus != null) {
            setIfpContractAttachedStatus(ifpContractAttachedStatus);
        }

        Integer rebatePlanSystemId = (Integer) attributes.get(
                "rebatePlanSystemId");

        if (rebatePlanSystemId != null) {
            setRebatePlanSystemId(rebatePlanSystemId);
        }

        String rebatePlanName = (String) attributes.get("rebatePlanName");

        if (rebatePlanName != null) {
            setRebatePlanName(rebatePlanName);
        }

        String calendar = (String) attributes.get("calendar");

        if (calendar != null) {
            setCalendar(calendar);
        }

        String pricingQualifierSid = (String) attributes.get(
                "pricingQualifierSid");

        if (pricingQualifierSid != null) {
            setPricingQualifierSid(pricingQualifierSid);
        }

        String tempStatus = (String) attributes.get("tempStatus");

        if (tempStatus != null) {
            setTempStatus(tempStatus);
        }

        Date itemRebateEndDate = (Date) attributes.get("itemRebateEndDate");

        if (itemRebateEndDate != null) {
            setItemRebateEndDate(itemRebateEndDate);
        }

        Integer priceToleranceInterval = (Integer) attributes.get(
                "priceToleranceInterval");

        if (priceToleranceInterval != null) {
            setPriceToleranceInterval(priceToleranceInterval);
        }

        Date itemRebateStartDate = (Date) attributes.get("itemRebateStartDate");

        if (itemRebateStartDate != null) {
            setItemRebateStartDate(itemRebateStartDate);
        }

        String operation = (String) attributes.get("operation");

        if (operation != null) {
            setOperation(operation);
        }

        Integer cfpModelSid = (Integer) attributes.get("cfpModelSid");

        if (cfpModelSid != null) {
            setCfpModelSid(cfpModelSid);
        }

        Integer itemStatusSid = (Integer) attributes.get("itemStatusSid");

        if (itemStatusSid != null) {
            setItemStatusSid(itemStatusSid);
        }

        Integer ifpContractSid = (Integer) attributes.get("ifpContractSid");

        if (ifpContractSid != null) {
            setIfpContractSid(ifpContractSid);
        }
    }

    @Override
    public String getItemStatus() {
        return _itemStatus;
    }

    @Override
    public void setItemStatus(String itemStatus) {
        _itemStatus = itemStatus;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemStatus", String.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, itemStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFormulaMethodId() {
        return _formulaMethodId;
    }

    @Override
    public void setFormulaMethodId(String formulaMethodId) {
        _formulaMethodId = formulaMethodId;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaMethodId",
                        String.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, formulaMethodId);
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

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModuleName", String.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, moduleName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPaymentFrequency() {
        return _paymentFrequency;
    }

    @Override
    public void setPaymentFrequency(String paymentFrequency) {
        _paymentFrequency = paymentFrequency;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPaymentFrequency",
                        String.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, paymentFrequency);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getEndDate() {
        return _endDate;
    }

    @Override
    public void setEndDate(Date endDate) {
        _endDate = endDate;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setEndDate", Date.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, endDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCfpStartDate() {
        return _cfpStartDate;
    }

    @Override
    public void setCfpStartDate(Date cfpStartDate) {
        _cfpStartDate = cfpStartDate;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpStartDate", Date.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, cfpStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPriceProtectionStartDate() {
        return _priceProtectionStartDate;
    }

    @Override
    public void setPriceProtectionStartDate(Date priceProtectionStartDate) {
        _priceProtectionStartDate = priceProtectionStartDate;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceProtectionStartDate",
                        Date.class);

                method.invoke(_gcmGlobalDetailsRemoteModel,
                    priceProtectionStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getTempItemMasterSid() {
        return _tempItemMasterSid;
    }

    @Override
    public void setTempItemMasterSid(String tempItemMasterSid) {
        _tempItemMasterSid = tempItemMasterSid;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setTempItemMasterSid",
                        String.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, tempItemMasterSid);
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

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandName", String.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, brandName);
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

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, modifiedDate);
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

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractMasterSid",
                        int.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, contractMasterSid);
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

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSubModuleName() {
        return _subModuleName;
    }

    @Override
    public void setSubModuleName(String subModuleName) {
        _subModuleName = subModuleName;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSubModuleName", String.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, subModuleName);
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

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setTheraputicClass",
                        String.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, theraputicClass);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getGcmGlobalDetailsSid() {
        return _gcmGlobalDetailsSid;
    }

    @Override
    public void setGcmGlobalDetailsSid(int gcmGlobalDetailsSid) {
        _gcmGlobalDetailsSid = gcmGlobalDetailsSid;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setGcmGlobalDetailsSid",
                        int.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, gcmGlobalDetailsSid);
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

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPaymentMethod() {
        return _paymentMethod;
    }

    @Override
    public void setPaymentMethod(String paymentMethod) {
        _paymentMethod = paymentMethod;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPaymentMethod", String.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, paymentMethod);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getContractPriceEndDate() {
        return _contractPriceEndDate;
    }

    @Override
    public void setContractPriceEndDate(Date contractPriceEndDate) {
        _contractPriceEndDate = contractPriceEndDate;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractPriceEndDate",
                        Date.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, contractPriceEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPsContractSid() {
        return _psContractSid;
    }

    @Override
    public void setPsContractSid(int psContractSid) {
        _psContractSid = psContractSid;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPsContractSid", int.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, psContractSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPriceProtectionEndDate() {
        return _priceProtectionEndDate;
    }

    @Override
    public void setPriceProtectionEndDate(Date priceProtectionEndDate) {
        _priceProtectionEndDate = priceProtectionEndDate;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceProtectionEndDate",
                        Date.class);

                method.invoke(_gcmGlobalDetailsRemoteModel,
                    priceProtectionEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getStartDate() {
        return _startDate;
    }

    @Override
    public void setStartDate(Date startDate) {
        _startDate = startDate;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setStartDate", Date.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, startDate);
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

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setScreenName", String.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, screenName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRsContractSid() {
        return _rsContractSid;
    }

    @Override
    public void setRsContractSid(int rsContractSid) {
        _rsContractSid = rsContractSid;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsContractSid", int.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, rsContractSid);
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

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemName", String.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, itemName);
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

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", String.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, sessionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCfpStatus() {
        return _cfpStatus;
    }

    @Override
    public void setCfpStatus(String cfpStatus) {
        _cfpStatus = cfpStatus;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpStatus", String.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, cfpStatus);
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

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsModelSid", int.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, rsModelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCfpContractSid() {
        return _cfpContractSid;
    }

    @Override
    public void setCfpContractSid(int cfpContractSid) {
        _cfpContractSid = cfpContractSid;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpContractSid", int.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, cfpContractSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getPrice() {
        return _price;
    }

    @Override
    public void setPrice(double price) {
        _price = price;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPrice", double.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, price);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getTempEndDate() {
        return _tempEndDate;
    }

    @Override
    public void setTempEndDate(Date tempEndDate) {
        _tempEndDate = tempEndDate;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setTempEndDate", Date.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, tempEndDate);
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

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, itemMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemType() {
        return _itemType;
    }

    @Override
    public void setItemType(String itemType) {
        _itemType = itemType;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemType", String.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, itemType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getForecastingType() {
        return _forecastingType;
    }

    @Override
    public void setForecastingType(String forecastingType) {
        _forecastingType = forecastingType;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastingType",
                        String.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, forecastingType);
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

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, itemId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getBasePrice() {
        return _basePrice;
    }

    @Override
    public void setBasePrice(double basePrice) {
        _basePrice = basePrice;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBasePrice", double.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, basePrice);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getStatus() {
        return _status;
    }

    @Override
    public void setStatus(String status) {
        _status = status;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setStatus", String.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, status);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFormulaName() {
        return _formulaName;
    }

    @Override
    public void setFormulaName(String formulaName) {
        _formulaName = formulaName;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaName", String.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, formulaName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getWorkflowMasterSid() {
        return _workflowMasterSid;
    }

    @Override
    public void setWorkflowMasterSid(int workflowMasterSid) {
        _workflowMasterSid = workflowMasterSid;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setWorkflowMasterSid",
                        int.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, workflowMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getPriceTolerance() {
        return _priceTolerance;
    }

    @Override
    public void setPriceTolerance(double priceTolerance) {
        _priceTolerance = priceTolerance;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceTolerance",
                        double.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, priceTolerance);
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

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, createdBy);
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

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getTempStartDate() {
        return _tempStartDate;
    }

    @Override
    public void setTempStartDate(Date tempStartDate) {
        _tempStartDate = tempStartDate;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setTempStartDate", Date.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, tempStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCfpEndDate() {
        return _cfpEndDate;
    }

    @Override
    public void setCfpEndDate(Date cfpEndDate) {
        _cfpEndDate = cfpEndDate;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpEndDate", Date.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, cfpEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPsModelSid() {
        return _psModelSid;
    }

    @Override
    public void setPsModelSid(int psModelSid) {
        _psModelSid = psModelSid;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPsModelSid", int.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, psModelSid);
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

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaId", String.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, formulaId);
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

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemNo", String.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, itemNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getContractPrice() {
        return _contractPrice;
    }

    @Override
    public void setContractPrice(double contractPrice) {
        _contractPrice = contractPrice;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractPrice", double.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, contractPrice);
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

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpModelSid", int.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, ifpModelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPriceToleranceType() {
        return _priceToleranceType;
    }

    @Override
    public void setPriceToleranceType(int priceToleranceType) {
        _priceToleranceType = priceToleranceType;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceToleranceType",
                        int.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, priceToleranceType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRebateAmount() {
        return _rebateAmount;
    }

    @Override
    public void setRebateAmount(int rebateAmount) {
        _rebateAmount = rebateAmount;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRebateAmount", int.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, rebateAmount);
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

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", int.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, userId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getProjectionMasterSid() {
        return _projectionMasterSid;
    }

    @Override
    public void setProjectionMasterSid(int projectionMasterSid) {
        _projectionMasterSid = projectionMasterSid;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionMasterSid",
                        int.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, projectionMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getContractPriceStartDate() {
        return _contractPriceStartDate;
    }

    @Override
    public void setContractPriceStartDate(Date contractPriceStartDate) {
        _contractPriceStartDate = contractPriceStartDate;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractPriceStartDate",
                        Date.class);

                method.invoke(_gcmGlobalDetailsRemoteModel,
                    contractPriceStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPriceToleranceFrequency() {
        return _priceToleranceFrequency;
    }

    @Override
    public void setPriceToleranceFrequency(int priceToleranceFrequency) {
        _priceToleranceFrequency = priceToleranceFrequency;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceToleranceFrequency",
                        int.class);

                method.invoke(_gcmGlobalDetailsRemoteModel,
                    priceToleranceFrequency);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIfpContractAttachedStatus() {
        return _ifpContractAttachedStatus;
    }

    @Override
    public void setIfpContractAttachedStatus(String ifpContractAttachedStatus) {
        _ifpContractAttachedStatus = ifpContractAttachedStatus;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpContractAttachedStatus",
                        String.class);

                method.invoke(_gcmGlobalDetailsRemoteModel,
                    ifpContractAttachedStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRebatePlanSystemId() {
        return _rebatePlanSystemId;
    }

    @Override
    public void setRebatePlanSystemId(int rebatePlanSystemId) {
        _rebatePlanSystemId = rebatePlanSystemId;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRebatePlanSystemId",
                        int.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, rebatePlanSystemId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRebatePlanName() {
        return _rebatePlanName;
    }

    @Override
    public void setRebatePlanName(String rebatePlanName) {
        _rebatePlanName = rebatePlanName;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRebatePlanName",
                        String.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, rebatePlanName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCalendar() {
        return _calendar;
    }

    @Override
    public void setCalendar(String calendar) {
        _calendar = calendar;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCalendar", String.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, calendar);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPricingQualifierSid() {
        return _pricingQualifierSid;
    }

    @Override
    public void setPricingQualifierSid(String pricingQualifierSid) {
        _pricingQualifierSid = pricingQualifierSid;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPricingQualifierSid",
                        String.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, pricingQualifierSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getTempStatus() {
        return _tempStatus;
    }

    @Override
    public void setTempStatus(String tempStatus) {
        _tempStatus = tempStatus;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setTempStatus", String.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, tempStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getItemRebateEndDate() {
        return _itemRebateEndDate;
    }

    @Override
    public void setItemRebateEndDate(Date itemRebateEndDate) {
        _itemRebateEndDate = itemRebateEndDate;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemRebateEndDate",
                        Date.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, itemRebateEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPriceToleranceInterval() {
        return _priceToleranceInterval;
    }

    @Override
    public void setPriceToleranceInterval(int priceToleranceInterval) {
        _priceToleranceInterval = priceToleranceInterval;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceToleranceInterval",
                        int.class);

                method.invoke(_gcmGlobalDetailsRemoteModel,
                    priceToleranceInterval);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getItemRebateStartDate() {
        return _itemRebateStartDate;
    }

    @Override
    public void setItemRebateStartDate(Date itemRebateStartDate) {
        _itemRebateStartDate = itemRebateStartDate;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemRebateStartDate",
                        Date.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, itemRebateStartDate);
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

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setOperation", String.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, operation);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCfpModelSid() {
        return _cfpModelSid;
    }

    @Override
    public void setCfpModelSid(int cfpModelSid) {
        _cfpModelSid = cfpModelSid;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpModelSid", int.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, cfpModelSid);
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

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemStatusSid", int.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, itemStatusSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIfpContractSid() {
        return _ifpContractSid;
    }

    @Override
    public void setIfpContractSid(int ifpContractSid) {
        _ifpContractSid = ifpContractSid;

        if (_gcmGlobalDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _gcmGlobalDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpContractSid", int.class);

                method.invoke(_gcmGlobalDetailsRemoteModel, ifpContractSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getGcmGlobalDetailsRemoteModel() {
        return _gcmGlobalDetailsRemoteModel;
    }

    public void setGcmGlobalDetailsRemoteModel(
        BaseModel<?> gcmGlobalDetailsRemoteModel) {
        _gcmGlobalDetailsRemoteModel = gcmGlobalDetailsRemoteModel;
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

        Class<?> remoteModelClass = _gcmGlobalDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_gcmGlobalDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            GcmGlobalDetailsLocalServiceUtil.addGcmGlobalDetails(this);
        } else {
            GcmGlobalDetailsLocalServiceUtil.updateGcmGlobalDetails(this);
        }
    }

    @Override
    public GcmGlobalDetails toEscapedModel() {
        return (GcmGlobalDetails) ProxyUtil.newProxyInstance(GcmGlobalDetails.class.getClassLoader(),
            new Class[] { GcmGlobalDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        GcmGlobalDetailsClp clone = new GcmGlobalDetailsClp();

        clone.setItemStatus(getItemStatus());
        clone.setFormulaMethodId(getFormulaMethodId());
        clone.setModuleName(getModuleName());
        clone.setPaymentFrequency(getPaymentFrequency());
        clone.setEndDate(getEndDate());
        clone.setCfpStartDate(getCfpStartDate());
        clone.setPriceProtectionStartDate(getPriceProtectionStartDate());
        clone.setTempItemMasterSid(getTempItemMasterSid());
        clone.setBrandName(getBrandName());
        clone.setModifiedDate(getModifiedDate());
        clone.setContractMasterSid(getContractMasterSid());
        clone.setModifiedBy(getModifiedBy());
        clone.setSubModuleName(getSubModuleName());
        clone.setTheraputicClass(getTheraputicClass());
        clone.setGcmGlobalDetailsSid(getGcmGlobalDetailsSid());
        clone.setCheckRecord(getCheckRecord());
        clone.setPaymentMethod(getPaymentMethod());
        clone.setContractPriceEndDate(getContractPriceEndDate());
        clone.setPsContractSid(getPsContractSid());
        clone.setPriceProtectionEndDate(getPriceProtectionEndDate());
        clone.setStartDate(getStartDate());
        clone.setScreenName(getScreenName());
        clone.setRsContractSid(getRsContractSid());
        clone.setItemName(getItemName());
        clone.setSessionId(getSessionId());
        clone.setCfpStatus(getCfpStatus());
        clone.setRsModelSid(getRsModelSid());
        clone.setCfpContractSid(getCfpContractSid());
        clone.setPrice(getPrice());
        clone.setTempEndDate(getTempEndDate());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setItemType(getItemType());
        clone.setForecastingType(getForecastingType());
        clone.setItemId(getItemId());
        clone.setBasePrice(getBasePrice());
        clone.setStatus(getStatus());
        clone.setFormulaName(getFormulaName());
        clone.setWorkflowMasterSid(getWorkflowMasterSid());
        clone.setPriceTolerance(getPriceTolerance());
        clone.setCreatedBy(getCreatedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setTempStartDate(getTempStartDate());
        clone.setCfpEndDate(getCfpEndDate());
        clone.setPsModelSid(getPsModelSid());
        clone.setFormulaId(getFormulaId());
        clone.setItemNo(getItemNo());
        clone.setContractPrice(getContractPrice());
        clone.setIfpModelSid(getIfpModelSid());
        clone.setPriceToleranceType(getPriceToleranceType());
        clone.setRebateAmount(getRebateAmount());
        clone.setUserId(getUserId());
        clone.setProjectionMasterSid(getProjectionMasterSid());
        clone.setContractPriceStartDate(getContractPriceStartDate());
        clone.setPriceToleranceFrequency(getPriceToleranceFrequency());
        clone.setIfpContractAttachedStatus(getIfpContractAttachedStatus());
        clone.setRebatePlanSystemId(getRebatePlanSystemId());
        clone.setRebatePlanName(getRebatePlanName());
        clone.setCalendar(getCalendar());
        clone.setPricingQualifierSid(getPricingQualifierSid());
        clone.setTempStatus(getTempStatus());
        clone.setItemRebateEndDate(getItemRebateEndDate());
        clone.setPriceToleranceInterval(getPriceToleranceInterval());
        clone.setItemRebateStartDate(getItemRebateStartDate());
        clone.setOperation(getOperation());
        clone.setCfpModelSid(getCfpModelSid());
        clone.setItemStatusSid(getItemStatusSid());
        clone.setIfpContractSid(getIfpContractSid());

        return clone;
    }

    @Override
    public int compareTo(GcmGlobalDetails gcmGlobalDetails) {
        int primaryKey = gcmGlobalDetails.getPrimaryKey();

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

        if (!(obj instanceof GcmGlobalDetailsClp)) {
            return false;
        }

        GcmGlobalDetailsClp gcmGlobalDetails = (GcmGlobalDetailsClp) obj;

        int primaryKey = gcmGlobalDetails.getPrimaryKey();

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
        StringBundler sb = new StringBundler(135);

        sb.append("{itemStatus=");
        sb.append(getItemStatus());
        sb.append(", formulaMethodId=");
        sb.append(getFormulaMethodId());
        sb.append(", moduleName=");
        sb.append(getModuleName());
        sb.append(", paymentFrequency=");
        sb.append(getPaymentFrequency());
        sb.append(", endDate=");
        sb.append(getEndDate());
        sb.append(", cfpStartDate=");
        sb.append(getCfpStartDate());
        sb.append(", priceProtectionStartDate=");
        sb.append(getPriceProtectionStartDate());
        sb.append(", tempItemMasterSid=");
        sb.append(getTempItemMasterSid());
        sb.append(", brandName=");
        sb.append(getBrandName());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", contractMasterSid=");
        sb.append(getContractMasterSid());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", subModuleName=");
        sb.append(getSubModuleName());
        sb.append(", theraputicClass=");
        sb.append(getTheraputicClass());
        sb.append(", gcmGlobalDetailsSid=");
        sb.append(getGcmGlobalDetailsSid());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append(", paymentMethod=");
        sb.append(getPaymentMethod());
        sb.append(", contractPriceEndDate=");
        sb.append(getContractPriceEndDate());
        sb.append(", psContractSid=");
        sb.append(getPsContractSid());
        sb.append(", priceProtectionEndDate=");
        sb.append(getPriceProtectionEndDate());
        sb.append(", startDate=");
        sb.append(getStartDate());
        sb.append(", screenName=");
        sb.append(getScreenName());
        sb.append(", rsContractSid=");
        sb.append(getRsContractSid());
        sb.append(", itemName=");
        sb.append(getItemName());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append(", cfpStatus=");
        sb.append(getCfpStatus());
        sb.append(", rsModelSid=");
        sb.append(getRsModelSid());
        sb.append(", cfpContractSid=");
        sb.append(getCfpContractSid());
        sb.append(", price=");
        sb.append(getPrice());
        sb.append(", tempEndDate=");
        sb.append(getTempEndDate());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", itemType=");
        sb.append(getItemType());
        sb.append(", forecastingType=");
        sb.append(getForecastingType());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", basePrice=");
        sb.append(getBasePrice());
        sb.append(", status=");
        sb.append(getStatus());
        sb.append(", formulaName=");
        sb.append(getFormulaName());
        sb.append(", workflowMasterSid=");
        sb.append(getWorkflowMasterSid());
        sb.append(", priceTolerance=");
        sb.append(getPriceTolerance());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", tempStartDate=");
        sb.append(getTempStartDate());
        sb.append(", cfpEndDate=");
        sb.append(getCfpEndDate());
        sb.append(", psModelSid=");
        sb.append(getPsModelSid());
        sb.append(", formulaId=");
        sb.append(getFormulaId());
        sb.append(", itemNo=");
        sb.append(getItemNo());
        sb.append(", contractPrice=");
        sb.append(getContractPrice());
        sb.append(", ifpModelSid=");
        sb.append(getIfpModelSid());
        sb.append(", priceToleranceType=");
        sb.append(getPriceToleranceType());
        sb.append(", rebateAmount=");
        sb.append(getRebateAmount());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", projectionMasterSid=");
        sb.append(getProjectionMasterSid());
        sb.append(", contractPriceStartDate=");
        sb.append(getContractPriceStartDate());
        sb.append(", priceToleranceFrequency=");
        sb.append(getPriceToleranceFrequency());
        sb.append(", ifpContractAttachedStatus=");
        sb.append(getIfpContractAttachedStatus());
        sb.append(", rebatePlanSystemId=");
        sb.append(getRebatePlanSystemId());
        sb.append(", rebatePlanName=");
        sb.append(getRebatePlanName());
        sb.append(", calendar=");
        sb.append(getCalendar());
        sb.append(", pricingQualifierSid=");
        sb.append(getPricingQualifierSid());
        sb.append(", tempStatus=");
        sb.append(getTempStatus());
        sb.append(", itemRebateEndDate=");
        sb.append(getItemRebateEndDate());
        sb.append(", priceToleranceInterval=");
        sb.append(getPriceToleranceInterval());
        sb.append(", itemRebateStartDate=");
        sb.append(getItemRebateStartDate());
        sb.append(", operation=");
        sb.append(getOperation());
        sb.append(", cfpModelSid=");
        sb.append(getCfpModelSid());
        sb.append(", itemStatusSid=");
        sb.append(getItemStatusSid());
        sb.append(", ifpContractSid=");
        sb.append(getIfpContractSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(205);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.GcmGlobalDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>itemStatus</column-name><column-value><![CDATA[");
        sb.append(getItemStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>formulaMethodId</column-name><column-value><![CDATA[");
        sb.append(getFormulaMethodId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>moduleName</column-name><column-value><![CDATA[");
        sb.append(getModuleName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>paymentFrequency</column-name><column-value><![CDATA[");
        sb.append(getPaymentFrequency());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>endDate</column-name><column-value><![CDATA[");
        sb.append(getEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpStartDate</column-name><column-value><![CDATA[");
        sb.append(getCfpStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceProtectionStartDate</column-name><column-value><![CDATA[");
        sb.append(getPriceProtectionStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>tempItemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getTempItemMasterSid());
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
            "<column><column-name>contractMasterSid</column-name><column-value><![CDATA[");
        sb.append(getContractMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>subModuleName</column-name><column-value><![CDATA[");
        sb.append(getSubModuleName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>theraputicClass</column-name><column-value><![CDATA[");
        sb.append(getTheraputicClass());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>gcmGlobalDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getGcmGlobalDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>paymentMethod</column-name><column-value><![CDATA[");
        sb.append(getPaymentMethod());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractPriceEndDate</column-name><column-value><![CDATA[");
        sb.append(getContractPriceEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psContractSid</column-name><column-value><![CDATA[");
        sb.append(getPsContractSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceProtectionEndDate</column-name><column-value><![CDATA[");
        sb.append(getPriceProtectionEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>startDate</column-name><column-value><![CDATA[");
        sb.append(getStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>screenName</column-name><column-value><![CDATA[");
        sb.append(getScreenName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsContractSid</column-name><column-value><![CDATA[");
        sb.append(getRsContractSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemName</column-name><column-value><![CDATA[");
        sb.append(getItemName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sessionId</column-name><column-value><![CDATA[");
        sb.append(getSessionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpStatus</column-name><column-value><![CDATA[");
        sb.append(getCfpStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsModelSid</column-name><column-value><![CDATA[");
        sb.append(getRsModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpContractSid</column-name><column-value><![CDATA[");
        sb.append(getCfpContractSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>price</column-name><column-value><![CDATA[");
        sb.append(getPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>tempEndDate</column-name><column-value><![CDATA[");
        sb.append(getTempEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemType</column-name><column-value><![CDATA[");
        sb.append(getItemType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastingType</column-name><column-value><![CDATA[");
        sb.append(getForecastingType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemId</column-name><column-value><![CDATA[");
        sb.append(getItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>basePrice</column-name><column-value><![CDATA[");
        sb.append(getBasePrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>status</column-name><column-value><![CDATA[");
        sb.append(getStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>formulaName</column-name><column-value><![CDATA[");
        sb.append(getFormulaName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>workflowMasterSid</column-name><column-value><![CDATA[");
        sb.append(getWorkflowMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceTolerance</column-name><column-value><![CDATA[");
        sb.append(getPriceTolerance());
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
            "<column><column-name>tempStartDate</column-name><column-value><![CDATA[");
        sb.append(getTempStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpEndDate</column-name><column-value><![CDATA[");
        sb.append(getCfpEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psModelSid</column-name><column-value><![CDATA[");
        sb.append(getPsModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>formulaId</column-name><column-value><![CDATA[");
        sb.append(getFormulaId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemNo</column-name><column-value><![CDATA[");
        sb.append(getItemNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractPrice</column-name><column-value><![CDATA[");
        sb.append(getContractPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpModelSid</column-name><column-value><![CDATA[");
        sb.append(getIfpModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceToleranceType</column-name><column-value><![CDATA[");
        sb.append(getPriceToleranceType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebateAmount</column-name><column-value><![CDATA[");
        sb.append(getRebateAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionMasterSid</column-name><column-value><![CDATA[");
        sb.append(getProjectionMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractPriceStartDate</column-name><column-value><![CDATA[");
        sb.append(getContractPriceStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceToleranceFrequency</column-name><column-value><![CDATA[");
        sb.append(getPriceToleranceFrequency());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpContractAttachedStatus</column-name><column-value><![CDATA[");
        sb.append(getIfpContractAttachedStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebatePlanSystemId</column-name><column-value><![CDATA[");
        sb.append(getRebatePlanSystemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebatePlanName</column-name><column-value><![CDATA[");
        sb.append(getRebatePlanName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>calendar</column-name><column-value><![CDATA[");
        sb.append(getCalendar());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>pricingQualifierSid</column-name><column-value><![CDATA[");
        sb.append(getPricingQualifierSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>tempStatus</column-name><column-value><![CDATA[");
        sb.append(getTempStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemRebateEndDate</column-name><column-value><![CDATA[");
        sb.append(getItemRebateEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceToleranceInterval</column-name><column-value><![CDATA[");
        sb.append(getPriceToleranceInterval());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemRebateStartDate</column-name><column-value><![CDATA[");
        sb.append(getItemRebateStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>operation</column-name><column-value><![CDATA[");
        sb.append(getOperation());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpModelSid</column-name><column-value><![CDATA[");
        sb.append(getCfpModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemStatusSid</column-name><column-value><![CDATA[");
        sb.append(getItemStatusSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpContractSid</column-name><column-value><![CDATA[");
        sb.append(getIfpContractSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
