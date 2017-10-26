package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.RebatePlanMasterLocalServiceUtil;

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


public class RebatePlanMasterClp extends BaseModelImpl<RebatePlanMaster>
    implements RebatePlanMaster {
    private String _selfGrowthIndicator;
    private String _rebateStructure;
    private Date _marketShareFrom;
    private String _secondaryRebatePlanNo;
    private Date _modifiedDate;
    private int _rebateRangeBasedOn;
    private String _cdrModelSid;
    private String _rebateRule;
    private Date _createdDate;
    private int _createdBy;
    private String _source;
    private int _rebateBasedOn;
    private int _rebatePlanType;
    private String _rebatePlanId;
    private int _manfCompanyMasterSid;
    private int _modifiedBy;
    private String _inboundStatus;
    private String _secondaryRebatePlanId;
    private String _marketShareIndicator;
    private String _bogoEligible;
    private Date _marketShareTo;
    private int _rebatePlanStatus;
    private int _rebatePlanMasterSid;
    private String _marketShareReference;
    private String _netSalesFormulaMasterSid;
    private Date _selfGrowthFrom;
    private String _internalNotes;
    private String _secondaryRebatePlanName;
    private boolean _recordLockStatus;
    private String _rebatePlanName;
    private String _selfGrowthReference;
    private String _batchId;
    private int _formulaType;
    private Date _selfGrowthTo;
    private String _rebatePlanNo;
    private BaseModel<?> _rebatePlanMasterRemoteModel;

    public RebatePlanMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return RebatePlanMaster.class;
    }

    @Override
    public String getModelClassName() {
        return RebatePlanMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _rebatePlanMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setRebatePlanMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _rebatePlanMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("selfGrowthIndicator", getSelfGrowthIndicator());
        attributes.put("rebateStructure", getRebateStructure());
        attributes.put("marketShareFrom", getMarketShareFrom());
        attributes.put("secondaryRebatePlanNo", getSecondaryRebatePlanNo());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("rebateRangeBasedOn", getRebateRangeBasedOn());
        attributes.put("cdrModelSid", getCdrModelSid());
        attributes.put("rebateRule", getRebateRule());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("rebateBasedOn", getRebateBasedOn());
        attributes.put("rebatePlanType", getRebatePlanType());
        attributes.put("rebatePlanId", getRebatePlanId());
        attributes.put("manfCompanyMasterSid", getManfCompanyMasterSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("secondaryRebatePlanId", getSecondaryRebatePlanId());
        attributes.put("marketShareIndicator", getMarketShareIndicator());
        attributes.put("bogoEligible", getBogoEligible());
        attributes.put("marketShareTo", getMarketShareTo());
        attributes.put("rebatePlanStatus", getRebatePlanStatus());
        attributes.put("rebatePlanMasterSid", getRebatePlanMasterSid());
        attributes.put("marketShareReference", getMarketShareReference());
        attributes.put("netSalesFormulaMasterSid", getNetSalesFormulaMasterSid());
        attributes.put("selfGrowthFrom", getSelfGrowthFrom());
        attributes.put("internalNotes", getInternalNotes());
        attributes.put("secondaryRebatePlanName", getSecondaryRebatePlanName());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("rebatePlanName", getRebatePlanName());
        attributes.put("selfGrowthReference", getSelfGrowthReference());
        attributes.put("batchId", getBatchId());
        attributes.put("formulaType", getFormulaType());
        attributes.put("selfGrowthTo", getSelfGrowthTo());
        attributes.put("rebatePlanNo", getRebatePlanNo());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String selfGrowthIndicator = (String) attributes.get(
                "selfGrowthIndicator");

        if (selfGrowthIndicator != null) {
            setSelfGrowthIndicator(selfGrowthIndicator);
        }

        String rebateStructure = (String) attributes.get("rebateStructure");

        if (rebateStructure != null) {
            setRebateStructure(rebateStructure);
        }

        Date marketShareFrom = (Date) attributes.get("marketShareFrom");

        if (marketShareFrom != null) {
            setMarketShareFrom(marketShareFrom);
        }

        String secondaryRebatePlanNo = (String) attributes.get(
                "secondaryRebatePlanNo");

        if (secondaryRebatePlanNo != null) {
            setSecondaryRebatePlanNo(secondaryRebatePlanNo);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer rebateRangeBasedOn = (Integer) attributes.get(
                "rebateRangeBasedOn");

        if (rebateRangeBasedOn != null) {
            setRebateRangeBasedOn(rebateRangeBasedOn);
        }

        String cdrModelSid = (String) attributes.get("cdrModelSid");

        if (cdrModelSid != null) {
            setCdrModelSid(cdrModelSid);
        }

        String rebateRule = (String) attributes.get("rebateRule");

        if (rebateRule != null) {
            setRebateRule(rebateRule);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Integer rebateBasedOn = (Integer) attributes.get("rebateBasedOn");

        if (rebateBasedOn != null) {
            setRebateBasedOn(rebateBasedOn);
        }

        Integer rebatePlanType = (Integer) attributes.get("rebatePlanType");

        if (rebatePlanType != null) {
            setRebatePlanType(rebatePlanType);
        }

        String rebatePlanId = (String) attributes.get("rebatePlanId");

        if (rebatePlanId != null) {
            setRebatePlanId(rebatePlanId);
        }

        Integer manfCompanyMasterSid = (Integer) attributes.get(
                "manfCompanyMasterSid");

        if (manfCompanyMasterSid != null) {
            setManfCompanyMasterSid(manfCompanyMasterSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        String secondaryRebatePlanId = (String) attributes.get(
                "secondaryRebatePlanId");

        if (secondaryRebatePlanId != null) {
            setSecondaryRebatePlanId(secondaryRebatePlanId);
        }

        String marketShareIndicator = (String) attributes.get(
                "marketShareIndicator");

        if (marketShareIndicator != null) {
            setMarketShareIndicator(marketShareIndicator);
        }

        String bogoEligible = (String) attributes.get("bogoEligible");

        if (bogoEligible != null) {
            setBogoEligible(bogoEligible);
        }

        Date marketShareTo = (Date) attributes.get("marketShareTo");

        if (marketShareTo != null) {
            setMarketShareTo(marketShareTo);
        }

        Integer rebatePlanStatus = (Integer) attributes.get("rebatePlanStatus");

        if (rebatePlanStatus != null) {
            setRebatePlanStatus(rebatePlanStatus);
        }

        Integer rebatePlanMasterSid = (Integer) attributes.get(
                "rebatePlanMasterSid");

        if (rebatePlanMasterSid != null) {
            setRebatePlanMasterSid(rebatePlanMasterSid);
        }

        String marketShareReference = (String) attributes.get(
                "marketShareReference");

        if (marketShareReference != null) {
            setMarketShareReference(marketShareReference);
        }

        String netSalesFormulaMasterSid = (String) attributes.get(
                "netSalesFormulaMasterSid");

        if (netSalesFormulaMasterSid != null) {
            setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
        }

        Date selfGrowthFrom = (Date) attributes.get("selfGrowthFrom");

        if (selfGrowthFrom != null) {
            setSelfGrowthFrom(selfGrowthFrom);
        }

        String internalNotes = (String) attributes.get("internalNotes");

        if (internalNotes != null) {
            setInternalNotes(internalNotes);
        }

        String secondaryRebatePlanName = (String) attributes.get(
                "secondaryRebatePlanName");

        if (secondaryRebatePlanName != null) {
            setSecondaryRebatePlanName(secondaryRebatePlanName);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String rebatePlanName = (String) attributes.get("rebatePlanName");

        if (rebatePlanName != null) {
            setRebatePlanName(rebatePlanName);
        }

        String selfGrowthReference = (String) attributes.get(
                "selfGrowthReference");

        if (selfGrowthReference != null) {
            setSelfGrowthReference(selfGrowthReference);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Integer formulaType = (Integer) attributes.get("formulaType");

        if (formulaType != null) {
            setFormulaType(formulaType);
        }

        Date selfGrowthTo = (Date) attributes.get("selfGrowthTo");

        if (selfGrowthTo != null) {
            setSelfGrowthTo(selfGrowthTo);
        }

        String rebatePlanNo = (String) attributes.get("rebatePlanNo");

        if (rebatePlanNo != null) {
            setRebatePlanNo(rebatePlanNo);
        }
    }

    @Override
    public String getSelfGrowthIndicator() {
        return _selfGrowthIndicator;
    }

    @Override
    public void setSelfGrowthIndicator(String selfGrowthIndicator) {
        _selfGrowthIndicator = selfGrowthIndicator;

        if (_rebatePlanMasterRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSelfGrowthIndicator",
                        String.class);

                method.invoke(_rebatePlanMasterRemoteModel, selfGrowthIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRebateStructure() {
        return _rebateStructure;
    }

    @Override
    public void setRebateStructure(String rebateStructure) {
        _rebateStructure = rebateStructure;

        if (_rebatePlanMasterRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRebateStructure",
                        String.class);

                method.invoke(_rebatePlanMasterRemoteModel, rebateStructure);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getMarketShareFrom() {
        return _marketShareFrom;
    }

    @Override
    public void setMarketShareFrom(Date marketShareFrom) {
        _marketShareFrom = marketShareFrom;

        if (_rebatePlanMasterRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketShareFrom", Date.class);

                method.invoke(_rebatePlanMasterRemoteModel, marketShareFrom);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSecondaryRebatePlanNo() {
        return _secondaryRebatePlanNo;
    }

    @Override
    public void setSecondaryRebatePlanNo(String secondaryRebatePlanNo) {
        _secondaryRebatePlanNo = secondaryRebatePlanNo;

        if (_rebatePlanMasterRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSecondaryRebatePlanNo",
                        String.class);

                method.invoke(_rebatePlanMasterRemoteModel,
                    secondaryRebatePlanNo);
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

        if (_rebatePlanMasterRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_rebatePlanMasterRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRebateRangeBasedOn() {
        return _rebateRangeBasedOn;
    }

    @Override
    public void setRebateRangeBasedOn(int rebateRangeBasedOn) {
        _rebateRangeBasedOn = rebateRangeBasedOn;

        if (_rebatePlanMasterRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRebateRangeBasedOn",
                        int.class);

                method.invoke(_rebatePlanMasterRemoteModel, rebateRangeBasedOn);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCdrModelSid() {
        return _cdrModelSid;
    }

    @Override
    public void setCdrModelSid(String cdrModelSid) {
        _cdrModelSid = cdrModelSid;

        if (_rebatePlanMasterRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCdrModelSid", String.class);

                method.invoke(_rebatePlanMasterRemoteModel, cdrModelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRebateRule() {
        return _rebateRule;
    }

    @Override
    public void setRebateRule(String rebateRule) {
        _rebateRule = rebateRule;

        if (_rebatePlanMasterRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRebateRule", String.class);

                method.invoke(_rebatePlanMasterRemoteModel, rebateRule);
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

        if (_rebatePlanMasterRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_rebatePlanMasterRemoteModel, createdDate);
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

        if (_rebatePlanMasterRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_rebatePlanMasterRemoteModel, createdBy);
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

        if (_rebatePlanMasterRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_rebatePlanMasterRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRebateBasedOn() {
        return _rebateBasedOn;
    }

    @Override
    public void setRebateBasedOn(int rebateBasedOn) {
        _rebateBasedOn = rebateBasedOn;

        if (_rebatePlanMasterRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRebateBasedOn", int.class);

                method.invoke(_rebatePlanMasterRemoteModel, rebateBasedOn);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRebatePlanType() {
        return _rebatePlanType;
    }

    @Override
    public void setRebatePlanType(int rebatePlanType) {
        _rebatePlanType = rebatePlanType;

        if (_rebatePlanMasterRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRebatePlanType", int.class);

                method.invoke(_rebatePlanMasterRemoteModel, rebatePlanType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRebatePlanId() {
        return _rebatePlanId;
    }

    @Override
    public void setRebatePlanId(String rebatePlanId) {
        _rebatePlanId = rebatePlanId;

        if (_rebatePlanMasterRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRebatePlanId", String.class);

                method.invoke(_rebatePlanMasterRemoteModel, rebatePlanId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getManfCompanyMasterSid() {
        return _manfCompanyMasterSid;
    }

    @Override
    public void setManfCompanyMasterSid(int manfCompanyMasterSid) {
        _manfCompanyMasterSid = manfCompanyMasterSid;

        if (_rebatePlanMasterRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setManfCompanyMasterSid",
                        int.class);

                method.invoke(_rebatePlanMasterRemoteModel, manfCompanyMasterSid);
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

        if (_rebatePlanMasterRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_rebatePlanMasterRemoteModel, modifiedBy);
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

        if (_rebatePlanMasterRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_rebatePlanMasterRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSecondaryRebatePlanId() {
        return _secondaryRebatePlanId;
    }

    @Override
    public void setSecondaryRebatePlanId(String secondaryRebatePlanId) {
        _secondaryRebatePlanId = secondaryRebatePlanId;

        if (_rebatePlanMasterRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSecondaryRebatePlanId",
                        String.class);

                method.invoke(_rebatePlanMasterRemoteModel,
                    secondaryRebatePlanId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getMarketShareIndicator() {
        return _marketShareIndicator;
    }

    @Override
    public void setMarketShareIndicator(String marketShareIndicator) {
        _marketShareIndicator = marketShareIndicator;

        if (_rebatePlanMasterRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketShareIndicator",
                        String.class);

                method.invoke(_rebatePlanMasterRemoteModel, marketShareIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBogoEligible() {
        return _bogoEligible;
    }

    @Override
    public void setBogoEligible(String bogoEligible) {
        _bogoEligible = bogoEligible;

        if (_rebatePlanMasterRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBogoEligible", String.class);

                method.invoke(_rebatePlanMasterRemoteModel, bogoEligible);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getMarketShareTo() {
        return _marketShareTo;
    }

    @Override
    public void setMarketShareTo(Date marketShareTo) {
        _marketShareTo = marketShareTo;

        if (_rebatePlanMasterRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketShareTo", Date.class);

                method.invoke(_rebatePlanMasterRemoteModel, marketShareTo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRebatePlanStatus() {
        return _rebatePlanStatus;
    }

    @Override
    public void setRebatePlanStatus(int rebatePlanStatus) {
        _rebatePlanStatus = rebatePlanStatus;

        if (_rebatePlanMasterRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRebatePlanStatus", int.class);

                method.invoke(_rebatePlanMasterRemoteModel, rebatePlanStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRebatePlanMasterSid() {
        return _rebatePlanMasterSid;
    }

    @Override
    public void setRebatePlanMasterSid(int rebatePlanMasterSid) {
        _rebatePlanMasterSid = rebatePlanMasterSid;

        if (_rebatePlanMasterRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRebatePlanMasterSid",
                        int.class);

                method.invoke(_rebatePlanMasterRemoteModel, rebatePlanMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getMarketShareReference() {
        return _marketShareReference;
    }

    @Override
    public void setMarketShareReference(String marketShareReference) {
        _marketShareReference = marketShareReference;

        if (_rebatePlanMasterRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketShareReference",
                        String.class);

                method.invoke(_rebatePlanMasterRemoteModel, marketShareReference);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNetSalesFormulaMasterSid() {
        return _netSalesFormulaMasterSid;
    }

    @Override
    public void setNetSalesFormulaMasterSid(String netSalesFormulaMasterSid) {
        _netSalesFormulaMasterSid = netSalesFormulaMasterSid;

        if (_rebatePlanMasterRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesFormulaMasterSid",
                        String.class);

                method.invoke(_rebatePlanMasterRemoteModel,
                    netSalesFormulaMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getSelfGrowthFrom() {
        return _selfGrowthFrom;
    }

    @Override
    public void setSelfGrowthFrom(Date selfGrowthFrom) {
        _selfGrowthFrom = selfGrowthFrom;

        if (_rebatePlanMasterRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSelfGrowthFrom", Date.class);

                method.invoke(_rebatePlanMasterRemoteModel, selfGrowthFrom);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getInternalNotes() {
        return _internalNotes;
    }

    @Override
    public void setInternalNotes(String internalNotes) {
        _internalNotes = internalNotes;

        if (_rebatePlanMasterRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInternalNotes", String.class);

                method.invoke(_rebatePlanMasterRemoteModel, internalNotes);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSecondaryRebatePlanName() {
        return _secondaryRebatePlanName;
    }

    @Override
    public void setSecondaryRebatePlanName(String secondaryRebatePlanName) {
        _secondaryRebatePlanName = secondaryRebatePlanName;

        if (_rebatePlanMasterRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSecondaryRebatePlanName",
                        String.class);

                method.invoke(_rebatePlanMasterRemoteModel,
                    secondaryRebatePlanName);
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

        if (_rebatePlanMasterRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_rebatePlanMasterRemoteModel, recordLockStatus);
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

        if (_rebatePlanMasterRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRebatePlanName",
                        String.class);

                method.invoke(_rebatePlanMasterRemoteModel, rebatePlanName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSelfGrowthReference() {
        return _selfGrowthReference;
    }

    @Override
    public void setSelfGrowthReference(String selfGrowthReference) {
        _selfGrowthReference = selfGrowthReference;

        if (_rebatePlanMasterRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSelfGrowthReference",
                        String.class);

                method.invoke(_rebatePlanMasterRemoteModel, selfGrowthReference);
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

        if (_rebatePlanMasterRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_rebatePlanMasterRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getFormulaType() {
        return _formulaType;
    }

    @Override
    public void setFormulaType(int formulaType) {
        _formulaType = formulaType;

        if (_rebatePlanMasterRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaType", int.class);

                method.invoke(_rebatePlanMasterRemoteModel, formulaType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getSelfGrowthTo() {
        return _selfGrowthTo;
    }

    @Override
    public void setSelfGrowthTo(Date selfGrowthTo) {
        _selfGrowthTo = selfGrowthTo;

        if (_rebatePlanMasterRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSelfGrowthTo", Date.class);

                method.invoke(_rebatePlanMasterRemoteModel, selfGrowthTo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRebatePlanNo() {
        return _rebatePlanNo;
    }

    @Override
    public void setRebatePlanNo(String rebatePlanNo) {
        _rebatePlanNo = rebatePlanNo;

        if (_rebatePlanMasterRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRebatePlanNo", String.class);

                method.invoke(_rebatePlanMasterRemoteModel, rebatePlanNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getRebatePlanMasterRemoteModel() {
        return _rebatePlanMasterRemoteModel;
    }

    public void setRebatePlanMasterRemoteModel(
        BaseModel<?> rebatePlanMasterRemoteModel) {
        _rebatePlanMasterRemoteModel = rebatePlanMasterRemoteModel;
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

        Class<?> remoteModelClass = _rebatePlanMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_rebatePlanMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            RebatePlanMasterLocalServiceUtil.addRebatePlanMaster(this);
        } else {
            RebatePlanMasterLocalServiceUtil.updateRebatePlanMaster(this);
        }
    }

    @Override
    public RebatePlanMaster toEscapedModel() {
        return (RebatePlanMaster) ProxyUtil.newProxyInstance(RebatePlanMaster.class.getClassLoader(),
            new Class[] { RebatePlanMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        RebatePlanMasterClp clone = new RebatePlanMasterClp();

        clone.setSelfGrowthIndicator(getSelfGrowthIndicator());
        clone.setRebateStructure(getRebateStructure());
        clone.setMarketShareFrom(getMarketShareFrom());
        clone.setSecondaryRebatePlanNo(getSecondaryRebatePlanNo());
        clone.setModifiedDate(getModifiedDate());
        clone.setRebateRangeBasedOn(getRebateRangeBasedOn());
        clone.setCdrModelSid(getCdrModelSid());
        clone.setRebateRule(getRebateRule());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setSource(getSource());
        clone.setRebateBasedOn(getRebateBasedOn());
        clone.setRebatePlanType(getRebatePlanType());
        clone.setRebatePlanId(getRebatePlanId());
        clone.setManfCompanyMasterSid(getManfCompanyMasterSid());
        clone.setModifiedBy(getModifiedBy());
        clone.setInboundStatus(getInboundStatus());
        clone.setSecondaryRebatePlanId(getSecondaryRebatePlanId());
        clone.setMarketShareIndicator(getMarketShareIndicator());
        clone.setBogoEligible(getBogoEligible());
        clone.setMarketShareTo(getMarketShareTo());
        clone.setRebatePlanStatus(getRebatePlanStatus());
        clone.setRebatePlanMasterSid(getRebatePlanMasterSid());
        clone.setMarketShareReference(getMarketShareReference());
        clone.setNetSalesFormulaMasterSid(getNetSalesFormulaMasterSid());
        clone.setSelfGrowthFrom(getSelfGrowthFrom());
        clone.setInternalNotes(getInternalNotes());
        clone.setSecondaryRebatePlanName(getSecondaryRebatePlanName());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setRebatePlanName(getRebatePlanName());
        clone.setSelfGrowthReference(getSelfGrowthReference());
        clone.setBatchId(getBatchId());
        clone.setFormulaType(getFormulaType());
        clone.setSelfGrowthTo(getSelfGrowthTo());
        clone.setRebatePlanNo(getRebatePlanNo());

        return clone;
    }

    @Override
    public int compareTo(RebatePlanMaster rebatePlanMaster) {
        int primaryKey = rebatePlanMaster.getPrimaryKey();

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

        if (!(obj instanceof RebatePlanMasterClp)) {
            return false;
        }

        RebatePlanMasterClp rebatePlanMaster = (RebatePlanMasterClp) obj;

        int primaryKey = rebatePlanMaster.getPrimaryKey();

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
        StringBundler sb = new StringBundler(71);

        sb.append("{selfGrowthIndicator=");
        sb.append(getSelfGrowthIndicator());
        sb.append(", rebateStructure=");
        sb.append(getRebateStructure());
        sb.append(", marketShareFrom=");
        sb.append(getMarketShareFrom());
        sb.append(", secondaryRebatePlanNo=");
        sb.append(getSecondaryRebatePlanNo());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", rebateRangeBasedOn=");
        sb.append(getRebateRangeBasedOn());
        sb.append(", cdrModelSid=");
        sb.append(getCdrModelSid());
        sb.append(", rebateRule=");
        sb.append(getRebateRule());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", rebateBasedOn=");
        sb.append(getRebateBasedOn());
        sb.append(", rebatePlanType=");
        sb.append(getRebatePlanType());
        sb.append(", rebatePlanId=");
        sb.append(getRebatePlanId());
        sb.append(", manfCompanyMasterSid=");
        sb.append(getManfCompanyMasterSid());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", secondaryRebatePlanId=");
        sb.append(getSecondaryRebatePlanId());
        sb.append(", marketShareIndicator=");
        sb.append(getMarketShareIndicator());
        sb.append(", bogoEligible=");
        sb.append(getBogoEligible());
        sb.append(", marketShareTo=");
        sb.append(getMarketShareTo());
        sb.append(", rebatePlanStatus=");
        sb.append(getRebatePlanStatus());
        sb.append(", rebatePlanMasterSid=");
        sb.append(getRebatePlanMasterSid());
        sb.append(", marketShareReference=");
        sb.append(getMarketShareReference());
        sb.append(", netSalesFormulaMasterSid=");
        sb.append(getNetSalesFormulaMasterSid());
        sb.append(", selfGrowthFrom=");
        sb.append(getSelfGrowthFrom());
        sb.append(", internalNotes=");
        sb.append(getInternalNotes());
        sb.append(", secondaryRebatePlanName=");
        sb.append(getSecondaryRebatePlanName());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", rebatePlanName=");
        sb.append(getRebatePlanName());
        sb.append(", selfGrowthReference=");
        sb.append(getSelfGrowthReference());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", formulaType=");
        sb.append(getFormulaType());
        sb.append(", selfGrowthTo=");
        sb.append(getSelfGrowthTo());
        sb.append(", rebatePlanNo=");
        sb.append(getRebatePlanNo());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(109);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.RebatePlanMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>selfGrowthIndicator</column-name><column-value><![CDATA[");
        sb.append(getSelfGrowthIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebateStructure</column-name><column-value><![CDATA[");
        sb.append(getRebateStructure());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>marketShareFrom</column-name><column-value><![CDATA[");
        sb.append(getMarketShareFrom());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>secondaryRebatePlanNo</column-name><column-value><![CDATA[");
        sb.append(getSecondaryRebatePlanNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebateRangeBasedOn</column-name><column-value><![CDATA[");
        sb.append(getRebateRangeBasedOn());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cdrModelSid</column-name><column-value><![CDATA[");
        sb.append(getCdrModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebateRule</column-name><column-value><![CDATA[");
        sb.append(getRebateRule());
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
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebateBasedOn</column-name><column-value><![CDATA[");
        sb.append(getRebateBasedOn());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebatePlanType</column-name><column-value><![CDATA[");
        sb.append(getRebatePlanType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebatePlanId</column-name><column-value><![CDATA[");
        sb.append(getRebatePlanId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>manfCompanyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getManfCompanyMasterSid());
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
            "<column><column-name>secondaryRebatePlanId</column-name><column-value><![CDATA[");
        sb.append(getSecondaryRebatePlanId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>marketShareIndicator</column-name><column-value><![CDATA[");
        sb.append(getMarketShareIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>bogoEligible</column-name><column-value><![CDATA[");
        sb.append(getBogoEligible());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>marketShareTo</column-name><column-value><![CDATA[");
        sb.append(getMarketShareTo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebatePlanStatus</column-name><column-value><![CDATA[");
        sb.append(getRebatePlanStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebatePlanMasterSid</column-name><column-value><![CDATA[");
        sb.append(getRebatePlanMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>marketShareReference</column-name><column-value><![CDATA[");
        sb.append(getMarketShareReference());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netSalesFormulaMasterSid</column-name><column-value><![CDATA[");
        sb.append(getNetSalesFormulaMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>selfGrowthFrom</column-name><column-value><![CDATA[");
        sb.append(getSelfGrowthFrom());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>internalNotes</column-name><column-value><![CDATA[");
        sb.append(getInternalNotes());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>secondaryRebatePlanName</column-name><column-value><![CDATA[");
        sb.append(getSecondaryRebatePlanName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebatePlanName</column-name><column-value><![CDATA[");
        sb.append(getRebatePlanName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>selfGrowthReference</column-name><column-value><![CDATA[");
        sb.append(getSelfGrowthReference());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>formulaType</column-name><column-value><![CDATA[");
        sb.append(getFormulaType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>selfGrowthTo</column-name><column-value><![CDATA[");
        sb.append(getSelfGrowthTo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebatePlanNo</column-name><column-value><![CDATA[");
        sb.append(getRebatePlanNo());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
