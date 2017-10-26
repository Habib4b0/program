package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.ImtdRsDetailsLocalServiceUtil;

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


public class ImtdRsDetailsClp extends BaseModelImpl<ImtdRsDetails>
    implements ImtdRsDetails {
    private Date _rsDetailsModifiedDate;
    private String _rsDetailsBundleNo;
    private int _itemMasterSid;
    private int _imtdRsDetailsSid;
    private String _itemId;
    private String _rsDetailsFormulaMethodId;
    private Date _modifiedDate;
    private Date _createdDate;
    private int _createdBy;
    private String _usersSid;
    private int _contractMasterSid;
    private String _rsDetailsFormulaId;
    private Date _imtdCreatedDate;
    private int _psModelSid;
    private int _modifiedBy;
    private Date _rsDetailsCreatedDate;
    private String _itemNo;
    private String _rsDetailsFormulaName;
    private String _udc6;
    private String _rsDetailsCreatedBy;
    private String _udc5;
    private int _ifpModelSid;
    private String _udc4;
    private String _rsDetailsFormulaNo;
    private boolean _checkRecord;
    private String _rsId;
    private String _udc1;
    private double _rsDetailsRebateAmount;
    private String _udc2;
    private String _rsDetailsModifiedBy;
    private String _udc3;
    private String _rebatePlanMasterSid;
    private Date _rsDetailsAttachedDate;
    private Date _itemRebateEndDate;
    private String _rsDetailsRebatePlanName;
    private Date _itemRebateStartDate;
    private String _rsDetailsFormulaType;
    private String _sessionId;
    private String _itemName;
    private String _operation;
    private int _cfpModelSid;
    private int _rsModelSid;
    private int _rsDetailsSid;
    private int _rsDetailsAttachedStatus;
    private String _rsDetailsNetSalesFormulaNo;
    private String _rsDetailsNetSalesFormulaName;
    private String _rsDetailsDeductionCalendarNo;
    private String _rsDetailsDeductionCalendarName;
    private int _deductionCalendarMasterSid;
    private int _netSalesFormulaMasterSid;
    private String _evaluationRule;
    private String _netSalesRule;
    private String _formulaType;
    private String _calculationRule;
    private String _calculationRuleBundle;
    private String _evaluationRuleBundle;
    private BaseModel<?> _imtdRsDetailsRemoteModel;

    public ImtdRsDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ImtdRsDetails.class;
    }

    @Override
    public String getModelClassName() {
        return ImtdRsDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _imtdRsDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setImtdRsDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _imtdRsDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("rsDetailsModifiedDate", getRsDetailsModifiedDate());
        attributes.put("rsDetailsBundleNo", getRsDetailsBundleNo());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("imtdRsDetailsSid", getImtdRsDetailsSid());
        attributes.put("itemId", getItemId());
        attributes.put("rsDetailsFormulaMethodId", getRsDetailsFormulaMethodId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("usersSid", getUsersSid());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("rsDetailsFormulaId", getRsDetailsFormulaId());
        attributes.put("imtdCreatedDate", getImtdCreatedDate());
        attributes.put("psModelSid", getPsModelSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("rsDetailsCreatedDate", getRsDetailsCreatedDate());
        attributes.put("itemNo", getItemNo());
        attributes.put("rsDetailsFormulaName", getRsDetailsFormulaName());
        attributes.put("udc6", getUdc6());
        attributes.put("rsDetailsCreatedBy", getRsDetailsCreatedBy());
        attributes.put("udc5", getUdc5());
        attributes.put("ifpModelSid", getIfpModelSid());
        attributes.put("udc4", getUdc4());
        attributes.put("rsDetailsFormulaNo", getRsDetailsFormulaNo());
        attributes.put("checkRecord", getCheckRecord());
        attributes.put("rsId", getRsId());
        attributes.put("udc1", getUdc1());
        attributes.put("rsDetailsRebateAmount", getRsDetailsRebateAmount());
        attributes.put("udc2", getUdc2());
        attributes.put("rsDetailsModifiedBy", getRsDetailsModifiedBy());
        attributes.put("udc3", getUdc3());
        attributes.put("rebatePlanMasterSid", getRebatePlanMasterSid());
        attributes.put("rsDetailsAttachedDate", getRsDetailsAttachedDate());
        attributes.put("itemRebateEndDate", getItemRebateEndDate());
        attributes.put("rsDetailsRebatePlanName", getRsDetailsRebatePlanName());
        attributes.put("itemRebateStartDate", getItemRebateStartDate());
        attributes.put("rsDetailsFormulaType", getRsDetailsFormulaType());
        attributes.put("sessionId", getSessionId());
        attributes.put("itemName", getItemName());
        attributes.put("operation", getOperation());
        attributes.put("cfpModelSid", getCfpModelSid());
        attributes.put("rsModelSid", getRsModelSid());
        attributes.put("rsDetailsSid", getRsDetailsSid());
        attributes.put("rsDetailsAttachedStatus", getRsDetailsAttachedStatus());
        attributes.put("rsDetailsNetSalesFormulaNo",
            getRsDetailsNetSalesFormulaNo());
        attributes.put("rsDetailsNetSalesFormulaName",
            getRsDetailsNetSalesFormulaName());
        attributes.put("rsDetailsDeductionCalendarNo",
            getRsDetailsDeductionCalendarNo());
        attributes.put("rsDetailsDeductionCalendarName",
            getRsDetailsDeductionCalendarName());
        attributes.put("deductionCalendarMasterSid",
            getDeductionCalendarMasterSid());
        attributes.put("netSalesFormulaMasterSid", getNetSalesFormulaMasterSid());
        attributes.put("evaluationRule", getEvaluationRule());
        attributes.put("netSalesRule", getNetSalesRule());
        attributes.put("formulaType", getFormulaType());
        attributes.put("calculationRule", getCalculationRule());
        attributes.put("calculationRuleBundle", getCalculationRuleBundle());
        attributes.put("evaluationRuleBundle", getEvaluationRuleBundle());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date rsDetailsModifiedDate = (Date) attributes.get(
                "rsDetailsModifiedDate");

        if (rsDetailsModifiedDate != null) {
            setRsDetailsModifiedDate(rsDetailsModifiedDate);
        }

        String rsDetailsBundleNo = (String) attributes.get("rsDetailsBundleNo");

        if (rsDetailsBundleNo != null) {
            setRsDetailsBundleNo(rsDetailsBundleNo);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Integer imtdRsDetailsSid = (Integer) attributes.get("imtdRsDetailsSid");

        if (imtdRsDetailsSid != null) {
            setImtdRsDetailsSid(imtdRsDetailsSid);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String rsDetailsFormulaMethodId = (String) attributes.get(
                "rsDetailsFormulaMethodId");

        if (rsDetailsFormulaMethodId != null) {
            setRsDetailsFormulaMethodId(rsDetailsFormulaMethodId);
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

        String usersSid = (String) attributes.get("usersSid");

        if (usersSid != null) {
            setUsersSid(usersSid);
        }

        Integer contractMasterSid = (Integer) attributes.get(
                "contractMasterSid");

        if (contractMasterSid != null) {
            setContractMasterSid(contractMasterSid);
        }

        String rsDetailsFormulaId = (String) attributes.get(
                "rsDetailsFormulaId");

        if (rsDetailsFormulaId != null) {
            setRsDetailsFormulaId(rsDetailsFormulaId);
        }

        Date imtdCreatedDate = (Date) attributes.get("imtdCreatedDate");

        if (imtdCreatedDate != null) {
            setImtdCreatedDate(imtdCreatedDate);
        }

        Integer psModelSid = (Integer) attributes.get("psModelSid");

        if (psModelSid != null) {
            setPsModelSid(psModelSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date rsDetailsCreatedDate = (Date) attributes.get(
                "rsDetailsCreatedDate");

        if (rsDetailsCreatedDate != null) {
            setRsDetailsCreatedDate(rsDetailsCreatedDate);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        String rsDetailsFormulaName = (String) attributes.get(
                "rsDetailsFormulaName");

        if (rsDetailsFormulaName != null) {
            setRsDetailsFormulaName(rsDetailsFormulaName);
        }

        String udc6 = (String) attributes.get("udc6");

        if (udc6 != null) {
            setUdc6(udc6);
        }

        String rsDetailsCreatedBy = (String) attributes.get(
                "rsDetailsCreatedBy");

        if (rsDetailsCreatedBy != null) {
            setRsDetailsCreatedBy(rsDetailsCreatedBy);
        }

        String udc5 = (String) attributes.get("udc5");

        if (udc5 != null) {
            setUdc5(udc5);
        }

        Integer ifpModelSid = (Integer) attributes.get("ifpModelSid");

        if (ifpModelSid != null) {
            setIfpModelSid(ifpModelSid);
        }

        String udc4 = (String) attributes.get("udc4");

        if (udc4 != null) {
            setUdc4(udc4);
        }

        String rsDetailsFormulaNo = (String) attributes.get(
                "rsDetailsFormulaNo");

        if (rsDetailsFormulaNo != null) {
            setRsDetailsFormulaNo(rsDetailsFormulaNo);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        String rsId = (String) attributes.get("rsId");

        if (rsId != null) {
            setRsId(rsId);
        }

        String udc1 = (String) attributes.get("udc1");

        if (udc1 != null) {
            setUdc1(udc1);
        }

        Double rsDetailsRebateAmount = (Double) attributes.get(
                "rsDetailsRebateAmount");

        if (rsDetailsRebateAmount != null) {
            setRsDetailsRebateAmount(rsDetailsRebateAmount);
        }

        String udc2 = (String) attributes.get("udc2");

        if (udc2 != null) {
            setUdc2(udc2);
        }

        String rsDetailsModifiedBy = (String) attributes.get(
                "rsDetailsModifiedBy");

        if (rsDetailsModifiedBy != null) {
            setRsDetailsModifiedBy(rsDetailsModifiedBy);
        }

        String udc3 = (String) attributes.get("udc3");

        if (udc3 != null) {
            setUdc3(udc3);
        }

        String rebatePlanMasterSid = (String) attributes.get(
                "rebatePlanMasterSid");

        if (rebatePlanMasterSid != null) {
            setRebatePlanMasterSid(rebatePlanMasterSid);
        }

        Date rsDetailsAttachedDate = (Date) attributes.get(
                "rsDetailsAttachedDate");

        if (rsDetailsAttachedDate != null) {
            setRsDetailsAttachedDate(rsDetailsAttachedDate);
        }

        Date itemRebateEndDate = (Date) attributes.get("itemRebateEndDate");

        if (itemRebateEndDate != null) {
            setItemRebateEndDate(itemRebateEndDate);
        }

        String rsDetailsRebatePlanName = (String) attributes.get(
                "rsDetailsRebatePlanName");

        if (rsDetailsRebatePlanName != null) {
            setRsDetailsRebatePlanName(rsDetailsRebatePlanName);
        }

        Date itemRebateStartDate = (Date) attributes.get("itemRebateStartDate");

        if (itemRebateStartDate != null) {
            setItemRebateStartDate(itemRebateStartDate);
        }

        String rsDetailsFormulaType = (String) attributes.get(
                "rsDetailsFormulaType");

        if (rsDetailsFormulaType != null) {
            setRsDetailsFormulaType(rsDetailsFormulaType);
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

        Integer cfpModelSid = (Integer) attributes.get("cfpModelSid");

        if (cfpModelSid != null) {
            setCfpModelSid(cfpModelSid);
        }

        Integer rsModelSid = (Integer) attributes.get("rsModelSid");

        if (rsModelSid != null) {
            setRsModelSid(rsModelSid);
        }

        Integer rsDetailsSid = (Integer) attributes.get("rsDetailsSid");

        if (rsDetailsSid != null) {
            setRsDetailsSid(rsDetailsSid);
        }

        Integer rsDetailsAttachedStatus = (Integer) attributes.get(
                "rsDetailsAttachedStatus");

        if (rsDetailsAttachedStatus != null) {
            setRsDetailsAttachedStatus(rsDetailsAttachedStatus);
        }

        String rsDetailsNetSalesFormulaNo = (String) attributes.get(
                "rsDetailsNetSalesFormulaNo");

        if (rsDetailsNetSalesFormulaNo != null) {
            setRsDetailsNetSalesFormulaNo(rsDetailsNetSalesFormulaNo);
        }

        String rsDetailsNetSalesFormulaName = (String) attributes.get(
                "rsDetailsNetSalesFormulaName");

        if (rsDetailsNetSalesFormulaName != null) {
            setRsDetailsNetSalesFormulaName(rsDetailsNetSalesFormulaName);
        }

        String rsDetailsDeductionCalendarNo = (String) attributes.get(
                "rsDetailsDeductionCalendarNo");

        if (rsDetailsDeductionCalendarNo != null) {
            setRsDetailsDeductionCalendarNo(rsDetailsDeductionCalendarNo);
        }

        String rsDetailsDeductionCalendarName = (String) attributes.get(
                "rsDetailsDeductionCalendarName");

        if (rsDetailsDeductionCalendarName != null) {
            setRsDetailsDeductionCalendarName(rsDetailsDeductionCalendarName);
        }

        Integer deductionCalendarMasterSid = (Integer) attributes.get(
                "deductionCalendarMasterSid");

        if (deductionCalendarMasterSid != null) {
            setDeductionCalendarMasterSid(deductionCalendarMasterSid);
        }

        Integer netSalesFormulaMasterSid = (Integer) attributes.get(
                "netSalesFormulaMasterSid");

        if (netSalesFormulaMasterSid != null) {
            setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
        }

        String evaluationRule = (String) attributes.get("evaluationRule");

        if (evaluationRule != null) {
            setEvaluationRule(evaluationRule);
        }

        String netSalesRule = (String) attributes.get("netSalesRule");

        if (netSalesRule != null) {
            setNetSalesRule(netSalesRule);
        }

        String formulaType = (String) attributes.get("formulaType");

        if (formulaType != null) {
            setFormulaType(formulaType);
        }

        String calculationRule = (String) attributes.get("calculationRule");

        if (calculationRule != null) {
            setCalculationRule(calculationRule);
        }

        String calculationRuleBundle = (String) attributes.get(
                "calculationRuleBundle");

        if (calculationRuleBundle != null) {
            setCalculationRuleBundle(calculationRuleBundle);
        }

        String evaluationRuleBundle = (String) attributes.get(
                "evaluationRuleBundle");

        if (evaluationRuleBundle != null) {
            setEvaluationRuleBundle(evaluationRuleBundle);
        }
    }

    @Override
    public Date getRsDetailsModifiedDate() {
        return _rsDetailsModifiedDate;
    }

    @Override
    public void setRsDetailsModifiedDate(Date rsDetailsModifiedDate) {
        _rsDetailsModifiedDate = rsDetailsModifiedDate;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsDetailsModifiedDate",
                        Date.class);

                method.invoke(_imtdRsDetailsRemoteModel, rsDetailsModifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRsDetailsBundleNo() {
        return _rsDetailsBundleNo;
    }

    @Override
    public void setRsDetailsBundleNo(String rsDetailsBundleNo) {
        _rsDetailsBundleNo = rsDetailsBundleNo;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsDetailsBundleNo",
                        String.class);

                method.invoke(_imtdRsDetailsRemoteModel, rsDetailsBundleNo);
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

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_imtdRsDetailsRemoteModel, itemMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getImtdRsDetailsSid() {
        return _imtdRsDetailsSid;
    }

    @Override
    public void setImtdRsDetailsSid(int imtdRsDetailsSid) {
        _imtdRsDetailsSid = imtdRsDetailsSid;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setImtdRsDetailsSid", int.class);

                method.invoke(_imtdRsDetailsRemoteModel, imtdRsDetailsSid);
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

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_imtdRsDetailsRemoteModel, itemId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRsDetailsFormulaMethodId() {
        return _rsDetailsFormulaMethodId;
    }

    @Override
    public void setRsDetailsFormulaMethodId(String rsDetailsFormulaMethodId) {
        _rsDetailsFormulaMethodId = rsDetailsFormulaMethodId;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsDetailsFormulaMethodId",
                        String.class);

                method.invoke(_imtdRsDetailsRemoteModel,
                    rsDetailsFormulaMethodId);
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

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_imtdRsDetailsRemoteModel, modifiedDate);
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

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_imtdRsDetailsRemoteModel, createdDate);
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

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_imtdRsDetailsRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUsersSid() {
        return _usersSid;
    }

    @Override
    public void setUsersSid(String usersSid) {
        _usersSid = usersSid;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setUsersSid", String.class);

                method.invoke(_imtdRsDetailsRemoteModel, usersSid);
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

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractMasterSid",
                        int.class);

                method.invoke(_imtdRsDetailsRemoteModel, contractMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRsDetailsFormulaId() {
        return _rsDetailsFormulaId;
    }

    @Override
    public void setRsDetailsFormulaId(String rsDetailsFormulaId) {
        _rsDetailsFormulaId = rsDetailsFormulaId;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsDetailsFormulaId",
                        String.class);

                method.invoke(_imtdRsDetailsRemoteModel, rsDetailsFormulaId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getImtdCreatedDate() {
        return _imtdCreatedDate;
    }

    @Override
    public void setImtdCreatedDate(Date imtdCreatedDate) {
        _imtdCreatedDate = imtdCreatedDate;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setImtdCreatedDate", Date.class);

                method.invoke(_imtdRsDetailsRemoteModel, imtdCreatedDate);
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

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPsModelSid", int.class);

                method.invoke(_imtdRsDetailsRemoteModel, psModelSid);
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

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_imtdRsDetailsRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getRsDetailsCreatedDate() {
        return _rsDetailsCreatedDate;
    }

    @Override
    public void setRsDetailsCreatedDate(Date rsDetailsCreatedDate) {
        _rsDetailsCreatedDate = rsDetailsCreatedDate;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsDetailsCreatedDate",
                        Date.class);

                method.invoke(_imtdRsDetailsRemoteModel, rsDetailsCreatedDate);
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

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemNo", String.class);

                method.invoke(_imtdRsDetailsRemoteModel, itemNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRsDetailsFormulaName() {
        return _rsDetailsFormulaName;
    }

    @Override
    public void setRsDetailsFormulaName(String rsDetailsFormulaName) {
        _rsDetailsFormulaName = rsDetailsFormulaName;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsDetailsFormulaName",
                        String.class);

                method.invoke(_imtdRsDetailsRemoteModel, rsDetailsFormulaName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUdc6() {
        return _udc6;
    }

    @Override
    public void setUdc6(String udc6) {
        _udc6 = udc6;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc6", String.class);

                method.invoke(_imtdRsDetailsRemoteModel, udc6);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRsDetailsCreatedBy() {
        return _rsDetailsCreatedBy;
    }

    @Override
    public void setRsDetailsCreatedBy(String rsDetailsCreatedBy) {
        _rsDetailsCreatedBy = rsDetailsCreatedBy;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsDetailsCreatedBy",
                        String.class);

                method.invoke(_imtdRsDetailsRemoteModel, rsDetailsCreatedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUdc5() {
        return _udc5;
    }

    @Override
    public void setUdc5(String udc5) {
        _udc5 = udc5;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc5", String.class);

                method.invoke(_imtdRsDetailsRemoteModel, udc5);
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

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpModelSid", int.class);

                method.invoke(_imtdRsDetailsRemoteModel, ifpModelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUdc4() {
        return _udc4;
    }

    @Override
    public void setUdc4(String udc4) {
        _udc4 = udc4;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc4", String.class);

                method.invoke(_imtdRsDetailsRemoteModel, udc4);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRsDetailsFormulaNo() {
        return _rsDetailsFormulaNo;
    }

    @Override
    public void setRsDetailsFormulaNo(String rsDetailsFormulaNo) {
        _rsDetailsFormulaNo = rsDetailsFormulaNo;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsDetailsFormulaNo",
                        String.class);

                method.invoke(_imtdRsDetailsRemoteModel, rsDetailsFormulaNo);
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

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_imtdRsDetailsRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRsId() {
        return _rsId;
    }

    @Override
    public void setRsId(String rsId) {
        _rsId = rsId;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsId", String.class);

                method.invoke(_imtdRsDetailsRemoteModel, rsId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUdc1() {
        return _udc1;
    }

    @Override
    public void setUdc1(String udc1) {
        _udc1 = udc1;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc1", String.class);

                method.invoke(_imtdRsDetailsRemoteModel, udc1);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getRsDetailsRebateAmount() {
        return _rsDetailsRebateAmount;
    }

    @Override
    public void setRsDetailsRebateAmount(double rsDetailsRebateAmount) {
        _rsDetailsRebateAmount = rsDetailsRebateAmount;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsDetailsRebateAmount",
                        double.class);

                method.invoke(_imtdRsDetailsRemoteModel, rsDetailsRebateAmount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUdc2() {
        return _udc2;
    }

    @Override
    public void setUdc2(String udc2) {
        _udc2 = udc2;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc2", String.class);

                method.invoke(_imtdRsDetailsRemoteModel, udc2);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRsDetailsModifiedBy() {
        return _rsDetailsModifiedBy;
    }

    @Override
    public void setRsDetailsModifiedBy(String rsDetailsModifiedBy) {
        _rsDetailsModifiedBy = rsDetailsModifiedBy;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsDetailsModifiedBy",
                        String.class);

                method.invoke(_imtdRsDetailsRemoteModel, rsDetailsModifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUdc3() {
        return _udc3;
    }

    @Override
    public void setUdc3(String udc3) {
        _udc3 = udc3;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc3", String.class);

                method.invoke(_imtdRsDetailsRemoteModel, udc3);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRebatePlanMasterSid() {
        return _rebatePlanMasterSid;
    }

    @Override
    public void setRebatePlanMasterSid(String rebatePlanMasterSid) {
        _rebatePlanMasterSid = rebatePlanMasterSid;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRebatePlanMasterSid",
                        String.class);

                method.invoke(_imtdRsDetailsRemoteModel, rebatePlanMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getRsDetailsAttachedDate() {
        return _rsDetailsAttachedDate;
    }

    @Override
    public void setRsDetailsAttachedDate(Date rsDetailsAttachedDate) {
        _rsDetailsAttachedDate = rsDetailsAttachedDate;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsDetailsAttachedDate",
                        Date.class);

                method.invoke(_imtdRsDetailsRemoteModel, rsDetailsAttachedDate);
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

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemRebateEndDate",
                        Date.class);

                method.invoke(_imtdRsDetailsRemoteModel, itemRebateEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRsDetailsRebatePlanName() {
        return _rsDetailsRebatePlanName;
    }

    @Override
    public void setRsDetailsRebatePlanName(String rsDetailsRebatePlanName) {
        _rsDetailsRebatePlanName = rsDetailsRebatePlanName;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsDetailsRebatePlanName",
                        String.class);

                method.invoke(_imtdRsDetailsRemoteModel, rsDetailsRebatePlanName);
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

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemRebateStartDate",
                        Date.class);

                method.invoke(_imtdRsDetailsRemoteModel, itemRebateStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRsDetailsFormulaType() {
        return _rsDetailsFormulaType;
    }

    @Override
    public void setRsDetailsFormulaType(String rsDetailsFormulaType) {
        _rsDetailsFormulaType = rsDetailsFormulaType;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsDetailsFormulaType",
                        String.class);

                method.invoke(_imtdRsDetailsRemoteModel, rsDetailsFormulaType);
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

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", String.class);

                method.invoke(_imtdRsDetailsRemoteModel, sessionId);
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

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemName", String.class);

                method.invoke(_imtdRsDetailsRemoteModel, itemName);
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

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setOperation", String.class);

                method.invoke(_imtdRsDetailsRemoteModel, operation);
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

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpModelSid", int.class);

                method.invoke(_imtdRsDetailsRemoteModel, cfpModelSid);
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

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsModelSid", int.class);

                method.invoke(_imtdRsDetailsRemoteModel, rsModelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRsDetailsSid() {
        return _rsDetailsSid;
    }

    @Override
    public void setRsDetailsSid(int rsDetailsSid) {
        _rsDetailsSid = rsDetailsSid;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsDetailsSid", int.class);

                method.invoke(_imtdRsDetailsRemoteModel, rsDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRsDetailsAttachedStatus() {
        return _rsDetailsAttachedStatus;
    }

    @Override
    public void setRsDetailsAttachedStatus(int rsDetailsAttachedStatus) {
        _rsDetailsAttachedStatus = rsDetailsAttachedStatus;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsDetailsAttachedStatus",
                        int.class);

                method.invoke(_imtdRsDetailsRemoteModel, rsDetailsAttachedStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRsDetailsNetSalesFormulaNo() {
        return _rsDetailsNetSalesFormulaNo;
    }

    @Override
    public void setRsDetailsNetSalesFormulaNo(String rsDetailsNetSalesFormulaNo) {
        _rsDetailsNetSalesFormulaNo = rsDetailsNetSalesFormulaNo;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsDetailsNetSalesFormulaNo",
                        String.class);

                method.invoke(_imtdRsDetailsRemoteModel,
                    rsDetailsNetSalesFormulaNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRsDetailsNetSalesFormulaName() {
        return _rsDetailsNetSalesFormulaName;
    }

    @Override
    public void setRsDetailsNetSalesFormulaName(
        String rsDetailsNetSalesFormulaName) {
        _rsDetailsNetSalesFormulaName = rsDetailsNetSalesFormulaName;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsDetailsNetSalesFormulaName",
                        String.class);

                method.invoke(_imtdRsDetailsRemoteModel,
                    rsDetailsNetSalesFormulaName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRsDetailsDeductionCalendarNo() {
        return _rsDetailsDeductionCalendarNo;
    }

    @Override
    public void setRsDetailsDeductionCalendarNo(
        String rsDetailsDeductionCalendarNo) {
        _rsDetailsDeductionCalendarNo = rsDetailsDeductionCalendarNo;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsDetailsDeductionCalendarNo",
                        String.class);

                method.invoke(_imtdRsDetailsRemoteModel,
                    rsDetailsDeductionCalendarNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRsDetailsDeductionCalendarName() {
        return _rsDetailsDeductionCalendarName;
    }

    @Override
    public void setRsDetailsDeductionCalendarName(
        String rsDetailsDeductionCalendarName) {
        _rsDetailsDeductionCalendarName = rsDetailsDeductionCalendarName;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsDetailsDeductionCalendarName",
                        String.class);

                method.invoke(_imtdRsDetailsRemoteModel,
                    rsDetailsDeductionCalendarName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getDeductionCalendarMasterSid() {
        return _deductionCalendarMasterSid;
    }

    @Override
    public void setDeductionCalendarMasterSid(int deductionCalendarMasterSid) {
        _deductionCalendarMasterSid = deductionCalendarMasterSid;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionCalendarMasterSid",
                        int.class);

                method.invoke(_imtdRsDetailsRemoteModel,
                    deductionCalendarMasterSid);
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

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesFormulaMasterSid",
                        int.class);

                method.invoke(_imtdRsDetailsRemoteModel,
                    netSalesFormulaMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getEvaluationRule() {
        return _evaluationRule;
    }

    @Override
    public void setEvaluationRule(String evaluationRule) {
        _evaluationRule = evaluationRule;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setEvaluationRule",
                        String.class);

                method.invoke(_imtdRsDetailsRemoteModel, evaluationRule);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNetSalesRule() {
        return _netSalesRule;
    }

    @Override
    public void setNetSalesRule(String netSalesRule) {
        _netSalesRule = netSalesRule;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesRule", String.class);

                method.invoke(_imtdRsDetailsRemoteModel, netSalesRule);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFormulaType() {
        return _formulaType;
    }

    @Override
    public void setFormulaType(String formulaType) {
        _formulaType = formulaType;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaType", String.class);

                method.invoke(_imtdRsDetailsRemoteModel, formulaType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCalculationRule() {
        return _calculationRule;
    }

    @Override
    public void setCalculationRule(String calculationRule) {
        _calculationRule = calculationRule;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCalculationRule",
                        String.class);

                method.invoke(_imtdRsDetailsRemoteModel, calculationRule);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCalculationRuleBundle() {
        return _calculationRuleBundle;
    }

    @Override
    public void setCalculationRuleBundle(String calculationRuleBundle) {
        _calculationRuleBundle = calculationRuleBundle;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCalculationRuleBundle",
                        String.class);

                method.invoke(_imtdRsDetailsRemoteModel, calculationRuleBundle);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getEvaluationRuleBundle() {
        return _evaluationRuleBundle;
    }

    @Override
    public void setEvaluationRuleBundle(String evaluationRuleBundle) {
        _evaluationRuleBundle = evaluationRuleBundle;

        if (_imtdRsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setEvaluationRuleBundle",
                        String.class);

                method.invoke(_imtdRsDetailsRemoteModel, evaluationRuleBundle);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getImtdRsDetailsRemoteModel() {
        return _imtdRsDetailsRemoteModel;
    }

    public void setImtdRsDetailsRemoteModel(
        BaseModel<?> imtdRsDetailsRemoteModel) {
        _imtdRsDetailsRemoteModel = imtdRsDetailsRemoteModel;
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

        Class<?> remoteModelClass = _imtdRsDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_imtdRsDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ImtdRsDetailsLocalServiceUtil.addImtdRsDetails(this);
        } else {
            ImtdRsDetailsLocalServiceUtil.updateImtdRsDetails(this);
        }
    }

    @Override
    public ImtdRsDetails toEscapedModel() {
        return (ImtdRsDetails) ProxyUtil.newProxyInstance(ImtdRsDetails.class.getClassLoader(),
            new Class[] { ImtdRsDetails.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ImtdRsDetailsClp clone = new ImtdRsDetailsClp();

        clone.setRsDetailsModifiedDate(getRsDetailsModifiedDate());
        clone.setRsDetailsBundleNo(getRsDetailsBundleNo());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setImtdRsDetailsSid(getImtdRsDetailsSid());
        clone.setItemId(getItemId());
        clone.setRsDetailsFormulaMethodId(getRsDetailsFormulaMethodId());
        clone.setModifiedDate(getModifiedDate());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setUsersSid(getUsersSid());
        clone.setContractMasterSid(getContractMasterSid());
        clone.setRsDetailsFormulaId(getRsDetailsFormulaId());
        clone.setImtdCreatedDate(getImtdCreatedDate());
        clone.setPsModelSid(getPsModelSid());
        clone.setModifiedBy(getModifiedBy());
        clone.setRsDetailsCreatedDate(getRsDetailsCreatedDate());
        clone.setItemNo(getItemNo());
        clone.setRsDetailsFormulaName(getRsDetailsFormulaName());
        clone.setUdc6(getUdc6());
        clone.setRsDetailsCreatedBy(getRsDetailsCreatedBy());
        clone.setUdc5(getUdc5());
        clone.setIfpModelSid(getIfpModelSid());
        clone.setUdc4(getUdc4());
        clone.setRsDetailsFormulaNo(getRsDetailsFormulaNo());
        clone.setCheckRecord(getCheckRecord());
        clone.setRsId(getRsId());
        clone.setUdc1(getUdc1());
        clone.setRsDetailsRebateAmount(getRsDetailsRebateAmount());
        clone.setUdc2(getUdc2());
        clone.setRsDetailsModifiedBy(getRsDetailsModifiedBy());
        clone.setUdc3(getUdc3());
        clone.setRebatePlanMasterSid(getRebatePlanMasterSid());
        clone.setRsDetailsAttachedDate(getRsDetailsAttachedDate());
        clone.setItemRebateEndDate(getItemRebateEndDate());
        clone.setRsDetailsRebatePlanName(getRsDetailsRebatePlanName());
        clone.setItemRebateStartDate(getItemRebateStartDate());
        clone.setRsDetailsFormulaType(getRsDetailsFormulaType());
        clone.setSessionId(getSessionId());
        clone.setItemName(getItemName());
        clone.setOperation(getOperation());
        clone.setCfpModelSid(getCfpModelSid());
        clone.setRsModelSid(getRsModelSid());
        clone.setRsDetailsSid(getRsDetailsSid());
        clone.setRsDetailsAttachedStatus(getRsDetailsAttachedStatus());
        clone.setRsDetailsNetSalesFormulaNo(getRsDetailsNetSalesFormulaNo());
        clone.setRsDetailsNetSalesFormulaName(getRsDetailsNetSalesFormulaName());
        clone.setRsDetailsDeductionCalendarNo(getRsDetailsDeductionCalendarNo());
        clone.setRsDetailsDeductionCalendarName(getRsDetailsDeductionCalendarName());
        clone.setDeductionCalendarMasterSid(getDeductionCalendarMasterSid());
        clone.setNetSalesFormulaMasterSid(getNetSalesFormulaMasterSid());
        clone.setEvaluationRule(getEvaluationRule());
        clone.setNetSalesRule(getNetSalesRule());
        clone.setFormulaType(getFormulaType());
        clone.setCalculationRule(getCalculationRule());
        clone.setCalculationRuleBundle(getCalculationRuleBundle());
        clone.setEvaluationRuleBundle(getEvaluationRuleBundle());

        return clone;
    }

    @Override
    public int compareTo(ImtdRsDetails imtdRsDetails) {
        int primaryKey = imtdRsDetails.getPrimaryKey();

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

        if (!(obj instanceof ImtdRsDetailsClp)) {
            return false;
        }

        ImtdRsDetailsClp imtdRsDetails = (ImtdRsDetailsClp) obj;

        int primaryKey = imtdRsDetails.getPrimaryKey();

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
        StringBundler sb = new StringBundler(113);

        sb.append("{rsDetailsModifiedDate=");
        sb.append(getRsDetailsModifiedDate());
        sb.append(", rsDetailsBundleNo=");
        sb.append(getRsDetailsBundleNo());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", imtdRsDetailsSid=");
        sb.append(getImtdRsDetailsSid());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", rsDetailsFormulaMethodId=");
        sb.append(getRsDetailsFormulaMethodId());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", usersSid=");
        sb.append(getUsersSid());
        sb.append(", contractMasterSid=");
        sb.append(getContractMasterSid());
        sb.append(", rsDetailsFormulaId=");
        sb.append(getRsDetailsFormulaId());
        sb.append(", imtdCreatedDate=");
        sb.append(getImtdCreatedDate());
        sb.append(", psModelSid=");
        sb.append(getPsModelSid());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", rsDetailsCreatedDate=");
        sb.append(getRsDetailsCreatedDate());
        sb.append(", itemNo=");
        sb.append(getItemNo());
        sb.append(", rsDetailsFormulaName=");
        sb.append(getRsDetailsFormulaName());
        sb.append(", udc6=");
        sb.append(getUdc6());
        sb.append(", rsDetailsCreatedBy=");
        sb.append(getRsDetailsCreatedBy());
        sb.append(", udc5=");
        sb.append(getUdc5());
        sb.append(", ifpModelSid=");
        sb.append(getIfpModelSid());
        sb.append(", udc4=");
        sb.append(getUdc4());
        sb.append(", rsDetailsFormulaNo=");
        sb.append(getRsDetailsFormulaNo());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append(", rsId=");
        sb.append(getRsId());
        sb.append(", udc1=");
        sb.append(getUdc1());
        sb.append(", rsDetailsRebateAmount=");
        sb.append(getRsDetailsRebateAmount());
        sb.append(", udc2=");
        sb.append(getUdc2());
        sb.append(", rsDetailsModifiedBy=");
        sb.append(getRsDetailsModifiedBy());
        sb.append(", udc3=");
        sb.append(getUdc3());
        sb.append(", rebatePlanMasterSid=");
        sb.append(getRebatePlanMasterSid());
        sb.append(", rsDetailsAttachedDate=");
        sb.append(getRsDetailsAttachedDate());
        sb.append(", itemRebateEndDate=");
        sb.append(getItemRebateEndDate());
        sb.append(", rsDetailsRebatePlanName=");
        sb.append(getRsDetailsRebatePlanName());
        sb.append(", itemRebateStartDate=");
        sb.append(getItemRebateStartDate());
        sb.append(", rsDetailsFormulaType=");
        sb.append(getRsDetailsFormulaType());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append(", itemName=");
        sb.append(getItemName());
        sb.append(", operation=");
        sb.append(getOperation());
        sb.append(", cfpModelSid=");
        sb.append(getCfpModelSid());
        sb.append(", rsModelSid=");
        sb.append(getRsModelSid());
        sb.append(", rsDetailsSid=");
        sb.append(getRsDetailsSid());
        sb.append(", rsDetailsAttachedStatus=");
        sb.append(getRsDetailsAttachedStatus());
        sb.append(", rsDetailsNetSalesFormulaNo=");
        sb.append(getRsDetailsNetSalesFormulaNo());
        sb.append(", rsDetailsNetSalesFormulaName=");
        sb.append(getRsDetailsNetSalesFormulaName());
        sb.append(", rsDetailsDeductionCalendarNo=");
        sb.append(getRsDetailsDeductionCalendarNo());
        sb.append(", rsDetailsDeductionCalendarName=");
        sb.append(getRsDetailsDeductionCalendarName());
        sb.append(", deductionCalendarMasterSid=");
        sb.append(getDeductionCalendarMasterSid());
        sb.append(", netSalesFormulaMasterSid=");
        sb.append(getNetSalesFormulaMasterSid());
        sb.append(", evaluationRule=");
        sb.append(getEvaluationRule());
        sb.append(", netSalesRule=");
        sb.append(getNetSalesRule());
        sb.append(", formulaType=");
        sb.append(getFormulaType());
        sb.append(", calculationRule=");
        sb.append(getCalculationRule());
        sb.append(", calculationRuleBundle=");
        sb.append(getCalculationRuleBundle());
        sb.append(", evaluationRuleBundle=");
        sb.append(getEvaluationRuleBundle());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(172);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ImtdRsDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>rsDetailsModifiedDate</column-name><column-value><![CDATA[");
        sb.append(getRsDetailsModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsDetailsBundleNo</column-name><column-value><![CDATA[");
        sb.append(getRsDetailsBundleNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>imtdRsDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getImtdRsDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemId</column-name><column-value><![CDATA[");
        sb.append(getItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsDetailsFormulaMethodId</column-name><column-value><![CDATA[");
        sb.append(getRsDetailsFormulaMethodId());
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
            "<column><column-name>usersSid</column-name><column-value><![CDATA[");
        sb.append(getUsersSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractMasterSid</column-name><column-value><![CDATA[");
        sb.append(getContractMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsDetailsFormulaId</column-name><column-value><![CDATA[");
        sb.append(getRsDetailsFormulaId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>imtdCreatedDate</column-name><column-value><![CDATA[");
        sb.append(getImtdCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psModelSid</column-name><column-value><![CDATA[");
        sb.append(getPsModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsDetailsCreatedDate</column-name><column-value><![CDATA[");
        sb.append(getRsDetailsCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemNo</column-name><column-value><![CDATA[");
        sb.append(getItemNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsDetailsFormulaName</column-name><column-value><![CDATA[");
        sb.append(getRsDetailsFormulaName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc6</column-name><column-value><![CDATA[");
        sb.append(getUdc6());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsDetailsCreatedBy</column-name><column-value><![CDATA[");
        sb.append(getRsDetailsCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc5</column-name><column-value><![CDATA[");
        sb.append(getUdc5());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpModelSid</column-name><column-value><![CDATA[");
        sb.append(getIfpModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc4</column-name><column-value><![CDATA[");
        sb.append(getUdc4());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsDetailsFormulaNo</column-name><column-value><![CDATA[");
        sb.append(getRsDetailsFormulaNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsId</column-name><column-value><![CDATA[");
        sb.append(getRsId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc1</column-name><column-value><![CDATA[");
        sb.append(getUdc1());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsDetailsRebateAmount</column-name><column-value><![CDATA[");
        sb.append(getRsDetailsRebateAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc2</column-name><column-value><![CDATA[");
        sb.append(getUdc2());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsDetailsModifiedBy</column-name><column-value><![CDATA[");
        sb.append(getRsDetailsModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc3</column-name><column-value><![CDATA[");
        sb.append(getUdc3());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebatePlanMasterSid</column-name><column-value><![CDATA[");
        sb.append(getRebatePlanMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsDetailsAttachedDate</column-name><column-value><![CDATA[");
        sb.append(getRsDetailsAttachedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemRebateEndDate</column-name><column-value><![CDATA[");
        sb.append(getItemRebateEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsDetailsRebatePlanName</column-name><column-value><![CDATA[");
        sb.append(getRsDetailsRebatePlanName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemRebateStartDate</column-name><column-value><![CDATA[");
        sb.append(getItemRebateStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsDetailsFormulaType</column-name><column-value><![CDATA[");
        sb.append(getRsDetailsFormulaType());
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
            "<column><column-name>cfpModelSid</column-name><column-value><![CDATA[");
        sb.append(getCfpModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsModelSid</column-name><column-value><![CDATA[");
        sb.append(getRsModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getRsDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsDetailsAttachedStatus</column-name><column-value><![CDATA[");
        sb.append(getRsDetailsAttachedStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsDetailsNetSalesFormulaNo</column-name><column-value><![CDATA[");
        sb.append(getRsDetailsNetSalesFormulaNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsDetailsNetSalesFormulaName</column-name><column-value><![CDATA[");
        sb.append(getRsDetailsNetSalesFormulaName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsDetailsDeductionCalendarNo</column-name><column-value><![CDATA[");
        sb.append(getRsDetailsDeductionCalendarNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsDetailsDeductionCalendarName</column-name><column-value><![CDATA[");
        sb.append(getRsDetailsDeductionCalendarName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionCalendarMasterSid</column-name><column-value><![CDATA[");
        sb.append(getDeductionCalendarMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netSalesFormulaMasterSid</column-name><column-value><![CDATA[");
        sb.append(getNetSalesFormulaMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>evaluationRule</column-name><column-value><![CDATA[");
        sb.append(getEvaluationRule());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netSalesRule</column-name><column-value><![CDATA[");
        sb.append(getNetSalesRule());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>formulaType</column-name><column-value><![CDATA[");
        sb.append(getFormulaType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>calculationRule</column-name><column-value><![CDATA[");
        sb.append(getCalculationRule());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>calculationRuleBundle</column-name><column-value><![CDATA[");
        sb.append(getCalculationRuleBundle());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>evaluationRuleBundle</column-name><column-value><![CDATA[");
        sb.append(getEvaluationRuleBundle());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
