package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.ContractMasterLocalServiceUtil;

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


public class ContractMasterClp extends BaseModelImpl<ContractMaster>
    implements ContractMaster {
    private Date _proposalEndDate;
    private Date _createdDate;
    private Date _renegotiationEndDate;
    private String _outsideAdditionalName;
    private Date _endDate;
    private String _manfCompanyMasterSid;
    private Date _renegotiationStartDate;
    private String _insideAuthor;
    private double _advanceNoticeDays;
    private String _outsideOwner;
    private String _mostFavoredNation;
    private String _insideAdditionalPhone;
    private Date _originalStartDate;
    private int _createdBy;
    private Date _proposalStartDate;
    private int _contractTradeClass;
    private String _outsideAdditional;
    private boolean _processStatus;
    private String _insideAdditionalName;
    private int _contractMasterSid;
    private int _contractStatus;
    private String _contractId;
    private Date _modifiedDate;
    private int _contractType;
    private int _awardStatus;
    private String _insideOwner;
    private String _source;
    private String _shippingTerms;
    private String _priceEscalationClause;
    private int _modifiedBy;
    private String _outsideAdditionalPhone;
    private int _term;
    private String _contractNo;
    private String _batchId;
    private int _documentClass;
    private Date _originalEndDate;
    private int _paymentTerms;
    private String _insideAdditional;
    private String _affiliatedContractInfo;
    private String _category;
    private String _outsidePhone;
    private Date _priceprotectionStartDate;
    private Date _priceprotectionEndDate;
    private int _documentType;
    private String _exemptFromLowPrice;
    private String _organizationKey;
    private String _currency;
    private String _insidePhone;
    private String _bunitCompanyMasterSid;
    private String _outsideAuthor;
    private String _contHoldCompanyMasterSid;
    private Date _startDate;
    private String _contractName;
    private Date _lastUpdatedDate;
    private boolean _recordLockStatus;
    private String _priceResetIndicator;
    private String _minimumOrder;
    private String _cancellationClause;
    private String _inboundStatus;
    private String _internalNotes;
    private BaseModel<?> _contractMasterRemoteModel;

    public ContractMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ContractMaster.class;
    }

    @Override
    public String getModelClassName() {
        return ContractMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _contractMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setContractMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _contractMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("proposalEndDate", getProposalEndDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("renegotiationEndDate", getRenegotiationEndDate());
        attributes.put("outsideAdditionalName", getOutsideAdditionalName());
        attributes.put("endDate", getEndDate());
        attributes.put("manfCompanyMasterSid", getManfCompanyMasterSid());
        attributes.put("renegotiationStartDate", getRenegotiationStartDate());
        attributes.put("insideAuthor", getInsideAuthor());
        attributes.put("advanceNoticeDays", getAdvanceNoticeDays());
        attributes.put("outsideOwner", getOutsideOwner());
        attributes.put("mostFavoredNation", getMostFavoredNation());
        attributes.put("insideAdditionalPhone", getInsideAdditionalPhone());
        attributes.put("originalStartDate", getOriginalStartDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("proposalStartDate", getProposalStartDate());
        attributes.put("contractTradeClass", getContractTradeClass());
        attributes.put("outsideAdditional", getOutsideAdditional());
        attributes.put("processStatus", getProcessStatus());
        attributes.put("insideAdditionalName", getInsideAdditionalName());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("contractStatus", getContractStatus());
        attributes.put("contractId", getContractId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("contractType", getContractType());
        attributes.put("awardStatus", getAwardStatus());
        attributes.put("insideOwner", getInsideOwner());
        attributes.put("source", getSource());
        attributes.put("shippingTerms", getShippingTerms());
        attributes.put("priceEscalationClause", getPriceEscalationClause());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("outsideAdditionalPhone", getOutsideAdditionalPhone());
        attributes.put("term", getTerm());
        attributes.put("contractNo", getContractNo());
        attributes.put("batchId", getBatchId());
        attributes.put("documentClass", getDocumentClass());
        attributes.put("originalEndDate", getOriginalEndDate());
        attributes.put("paymentTerms", getPaymentTerms());
        attributes.put("insideAdditional", getInsideAdditional());
        attributes.put("affiliatedContractInfo", getAffiliatedContractInfo());
        attributes.put("category", getCategory());
        attributes.put("outsidePhone", getOutsidePhone());
        attributes.put("priceprotectionStartDate", getPriceprotectionStartDate());
        attributes.put("priceprotectionEndDate", getPriceprotectionEndDate());
        attributes.put("documentType", getDocumentType());
        attributes.put("exemptFromLowPrice", getExemptFromLowPrice());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("currency", getCurrency());
        attributes.put("insidePhone", getInsidePhone());
        attributes.put("bunitCompanyMasterSid", getBunitCompanyMasterSid());
        attributes.put("outsideAuthor", getOutsideAuthor());
        attributes.put("contHoldCompanyMasterSid", getContHoldCompanyMasterSid());
        attributes.put("startDate", getStartDate());
        attributes.put("contractName", getContractName());
        attributes.put("lastUpdatedDate", getLastUpdatedDate());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("priceResetIndicator", getPriceResetIndicator());
        attributes.put("minimumOrder", getMinimumOrder());
        attributes.put("cancellationClause", getCancellationClause());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("internalNotes", getInternalNotes());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date proposalEndDate = (Date) attributes.get("proposalEndDate");

        if (proposalEndDate != null) {
            setProposalEndDate(proposalEndDate);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Date renegotiationEndDate = (Date) attributes.get(
                "renegotiationEndDate");

        if (renegotiationEndDate != null) {
            setRenegotiationEndDate(renegotiationEndDate);
        }

        String outsideAdditionalName = (String) attributes.get(
                "outsideAdditionalName");

        if (outsideAdditionalName != null) {
            setOutsideAdditionalName(outsideAdditionalName);
        }

        Date endDate = (Date) attributes.get("endDate");

        if (endDate != null) {
            setEndDate(endDate);
        }

        String manfCompanyMasterSid = (String) attributes.get(
                "manfCompanyMasterSid");

        if (manfCompanyMasterSid != null) {
            setManfCompanyMasterSid(manfCompanyMasterSid);
        }

        Date renegotiationStartDate = (Date) attributes.get(
                "renegotiationStartDate");

        if (renegotiationStartDate != null) {
            setRenegotiationStartDate(renegotiationStartDate);
        }

        String insideAuthor = (String) attributes.get("insideAuthor");

        if (insideAuthor != null) {
            setInsideAuthor(insideAuthor);
        }

        Double advanceNoticeDays = (Double) attributes.get("advanceNoticeDays");

        if (advanceNoticeDays != null) {
            setAdvanceNoticeDays(advanceNoticeDays);
        }

        String outsideOwner = (String) attributes.get("outsideOwner");

        if (outsideOwner != null) {
            setOutsideOwner(outsideOwner);
        }

        String mostFavoredNation = (String) attributes.get("mostFavoredNation");

        if (mostFavoredNation != null) {
            setMostFavoredNation(mostFavoredNation);
        }

        String insideAdditionalPhone = (String) attributes.get(
                "insideAdditionalPhone");

        if (insideAdditionalPhone != null) {
            setInsideAdditionalPhone(insideAdditionalPhone);
        }

        Date originalStartDate = (Date) attributes.get("originalStartDate");

        if (originalStartDate != null) {
            setOriginalStartDate(originalStartDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date proposalStartDate = (Date) attributes.get("proposalStartDate");

        if (proposalStartDate != null) {
            setProposalStartDate(proposalStartDate);
        }

        Integer contractTradeClass = (Integer) attributes.get(
                "contractTradeClass");

        if (contractTradeClass != null) {
            setContractTradeClass(contractTradeClass);
        }

        String outsideAdditional = (String) attributes.get("outsideAdditional");

        if (outsideAdditional != null) {
            setOutsideAdditional(outsideAdditional);
        }

        Boolean processStatus = (Boolean) attributes.get("processStatus");

        if (processStatus != null) {
            setProcessStatus(processStatus);
        }

        String insideAdditionalName = (String) attributes.get(
                "insideAdditionalName");

        if (insideAdditionalName != null) {
            setInsideAdditionalName(insideAdditionalName);
        }

        Integer contractMasterSid = (Integer) attributes.get(
                "contractMasterSid");

        if (contractMasterSid != null) {
            setContractMasterSid(contractMasterSid);
        }

        Integer contractStatus = (Integer) attributes.get("contractStatus");

        if (contractStatus != null) {
            setContractStatus(contractStatus);
        }

        String contractId = (String) attributes.get("contractId");

        if (contractId != null) {
            setContractId(contractId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer contractType = (Integer) attributes.get("contractType");

        if (contractType != null) {
            setContractType(contractType);
        }

        Integer awardStatus = (Integer) attributes.get("awardStatus");

        if (awardStatus != null) {
            setAwardStatus(awardStatus);
        }

        String insideOwner = (String) attributes.get("insideOwner");

        if (insideOwner != null) {
            setInsideOwner(insideOwner);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String shippingTerms = (String) attributes.get("shippingTerms");

        if (shippingTerms != null) {
            setShippingTerms(shippingTerms);
        }

        String priceEscalationClause = (String) attributes.get(
                "priceEscalationClause");

        if (priceEscalationClause != null) {
            setPriceEscalationClause(priceEscalationClause);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String outsideAdditionalPhone = (String) attributes.get(
                "outsideAdditionalPhone");

        if (outsideAdditionalPhone != null) {
            setOutsideAdditionalPhone(outsideAdditionalPhone);
        }

        Integer term = (Integer) attributes.get("term");

        if (term != null) {
            setTerm(term);
        }

        String contractNo = (String) attributes.get("contractNo");

        if (contractNo != null) {
            setContractNo(contractNo);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Integer documentClass = (Integer) attributes.get("documentClass");

        if (documentClass != null) {
            setDocumentClass(documentClass);
        }

        Date originalEndDate = (Date) attributes.get("originalEndDate");

        if (originalEndDate != null) {
            setOriginalEndDate(originalEndDate);
        }

        Integer paymentTerms = (Integer) attributes.get("paymentTerms");

        if (paymentTerms != null) {
            setPaymentTerms(paymentTerms);
        }

        String insideAdditional = (String) attributes.get("insideAdditional");

        if (insideAdditional != null) {
            setInsideAdditional(insideAdditional);
        }

        String affiliatedContractInfo = (String) attributes.get(
                "affiliatedContractInfo");

        if (affiliatedContractInfo != null) {
            setAffiliatedContractInfo(affiliatedContractInfo);
        }

        String category = (String) attributes.get("category");

        if (category != null) {
            setCategory(category);
        }

        String outsidePhone = (String) attributes.get("outsidePhone");

        if (outsidePhone != null) {
            setOutsidePhone(outsidePhone);
        }

        Date priceprotectionStartDate = (Date) attributes.get(
                "priceprotectionStartDate");

        if (priceprotectionStartDate != null) {
            setPriceprotectionStartDate(priceprotectionStartDate);
        }

        Date priceprotectionEndDate = (Date) attributes.get(
                "priceprotectionEndDate");

        if (priceprotectionEndDate != null) {
            setPriceprotectionEndDate(priceprotectionEndDate);
        }

        Integer documentType = (Integer) attributes.get("documentType");

        if (documentType != null) {
            setDocumentType(documentType);
        }

        String exemptFromLowPrice = (String) attributes.get(
                "exemptFromLowPrice");

        if (exemptFromLowPrice != null) {
            setExemptFromLowPrice(exemptFromLowPrice);
        }

        String organizationKey = (String) attributes.get("organizationKey");

        if (organizationKey != null) {
            setOrganizationKey(organizationKey);
        }

        String currency = (String) attributes.get("currency");

        if (currency != null) {
            setCurrency(currency);
        }

        String insidePhone = (String) attributes.get("insidePhone");

        if (insidePhone != null) {
            setInsidePhone(insidePhone);
        }

        String bunitCompanyMasterSid = (String) attributes.get(
                "bunitCompanyMasterSid");

        if (bunitCompanyMasterSid != null) {
            setBunitCompanyMasterSid(bunitCompanyMasterSid);
        }

        String outsideAuthor = (String) attributes.get("outsideAuthor");

        if (outsideAuthor != null) {
            setOutsideAuthor(outsideAuthor);
        }

        String contHoldCompanyMasterSid = (String) attributes.get(
                "contHoldCompanyMasterSid");

        if (contHoldCompanyMasterSid != null) {
            setContHoldCompanyMasterSid(contHoldCompanyMasterSid);
        }

        Date startDate = (Date) attributes.get("startDate");

        if (startDate != null) {
            setStartDate(startDate);
        }

        String contractName = (String) attributes.get("contractName");

        if (contractName != null) {
            setContractName(contractName);
        }

        Date lastUpdatedDate = (Date) attributes.get("lastUpdatedDate");

        if (lastUpdatedDate != null) {
            setLastUpdatedDate(lastUpdatedDate);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String priceResetIndicator = (String) attributes.get(
                "priceResetIndicator");

        if (priceResetIndicator != null) {
            setPriceResetIndicator(priceResetIndicator);
        }

        String minimumOrder = (String) attributes.get("minimumOrder");

        if (minimumOrder != null) {
            setMinimumOrder(minimumOrder);
        }

        String cancellationClause = (String) attributes.get(
                "cancellationClause");

        if (cancellationClause != null) {
            setCancellationClause(cancellationClause);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        String internalNotes = (String) attributes.get("internalNotes");

        if (internalNotes != null) {
            setInternalNotes(internalNotes);
        }
    }

    @Override
    public Date getProposalEndDate() {
        return _proposalEndDate;
    }

    @Override
    public void setProposalEndDate(Date proposalEndDate) {
        _proposalEndDate = proposalEndDate;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProposalEndDate", Date.class);

                method.invoke(_contractMasterRemoteModel, proposalEndDate);
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

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_contractMasterRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getRenegotiationEndDate() {
        return _renegotiationEndDate;
    }

    @Override
    public void setRenegotiationEndDate(Date renegotiationEndDate) {
        _renegotiationEndDate = renegotiationEndDate;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRenegotiationEndDate",
                        Date.class);

                method.invoke(_contractMasterRemoteModel, renegotiationEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getOutsideAdditionalName() {
        return _outsideAdditionalName;
    }

    @Override
    public void setOutsideAdditionalName(String outsideAdditionalName) {
        _outsideAdditionalName = outsideAdditionalName;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setOutsideAdditionalName",
                        String.class);

                method.invoke(_contractMasterRemoteModel, outsideAdditionalName);
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

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setEndDate", Date.class);

                method.invoke(_contractMasterRemoteModel, endDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getManfCompanyMasterSid() {
        return _manfCompanyMasterSid;
    }

    @Override
    public void setManfCompanyMasterSid(String manfCompanyMasterSid) {
        _manfCompanyMasterSid = manfCompanyMasterSid;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setManfCompanyMasterSid",
                        String.class);

                method.invoke(_contractMasterRemoteModel, manfCompanyMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getRenegotiationStartDate() {
        return _renegotiationStartDate;
    }

    @Override
    public void setRenegotiationStartDate(Date renegotiationStartDate) {
        _renegotiationStartDate = renegotiationStartDate;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRenegotiationStartDate",
                        Date.class);

                method.invoke(_contractMasterRemoteModel, renegotiationStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getInsideAuthor() {
        return _insideAuthor;
    }

    @Override
    public void setInsideAuthor(String insideAuthor) {
        _insideAuthor = insideAuthor;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInsideAuthor", String.class);

                method.invoke(_contractMasterRemoteModel, insideAuthor);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getAdvanceNoticeDays() {
        return _advanceNoticeDays;
    }

    @Override
    public void setAdvanceNoticeDays(double advanceNoticeDays) {
        _advanceNoticeDays = advanceNoticeDays;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAdvanceNoticeDays",
                        double.class);

                method.invoke(_contractMasterRemoteModel, advanceNoticeDays);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getOutsideOwner() {
        return _outsideOwner;
    }

    @Override
    public void setOutsideOwner(String outsideOwner) {
        _outsideOwner = outsideOwner;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setOutsideOwner", String.class);

                method.invoke(_contractMasterRemoteModel, outsideOwner);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getMostFavoredNation() {
        return _mostFavoredNation;
    }

    @Override
    public void setMostFavoredNation(String mostFavoredNation) {
        _mostFavoredNation = mostFavoredNation;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setMostFavoredNation",
                        String.class);

                method.invoke(_contractMasterRemoteModel, mostFavoredNation);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getInsideAdditionalPhone() {
        return _insideAdditionalPhone;
    }

    @Override
    public void setInsideAdditionalPhone(String insideAdditionalPhone) {
        _insideAdditionalPhone = insideAdditionalPhone;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInsideAdditionalPhone",
                        String.class);

                method.invoke(_contractMasterRemoteModel, insideAdditionalPhone);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getOriginalStartDate() {
        return _originalStartDate;
    }

    @Override
    public void setOriginalStartDate(Date originalStartDate) {
        _originalStartDate = originalStartDate;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setOriginalStartDate",
                        Date.class);

                method.invoke(_contractMasterRemoteModel, originalStartDate);
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

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_contractMasterRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getProposalStartDate() {
        return _proposalStartDate;
    }

    @Override
    public void setProposalStartDate(Date proposalStartDate) {
        _proposalStartDate = proposalStartDate;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProposalStartDate",
                        Date.class);

                method.invoke(_contractMasterRemoteModel, proposalStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getContractTradeClass() {
        return _contractTradeClass;
    }

    @Override
    public void setContractTradeClass(int contractTradeClass) {
        _contractTradeClass = contractTradeClass;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractTradeClass",
                        int.class);

                method.invoke(_contractMasterRemoteModel, contractTradeClass);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getOutsideAdditional() {
        return _outsideAdditional;
    }

    @Override
    public void setOutsideAdditional(String outsideAdditional) {
        _outsideAdditional = outsideAdditional;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setOutsideAdditional",
                        String.class);

                method.invoke(_contractMasterRemoteModel, outsideAdditional);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getProcessStatus() {
        return _processStatus;
    }

    @Override
    public boolean isProcessStatus() {
        return _processStatus;
    }

    @Override
    public void setProcessStatus(boolean processStatus) {
        _processStatus = processStatus;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProcessStatus",
                        boolean.class);

                method.invoke(_contractMasterRemoteModel, processStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getInsideAdditionalName() {
        return _insideAdditionalName;
    }

    @Override
    public void setInsideAdditionalName(String insideAdditionalName) {
        _insideAdditionalName = insideAdditionalName;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInsideAdditionalName",
                        String.class);

                method.invoke(_contractMasterRemoteModel, insideAdditionalName);
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

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractMasterSid",
                        int.class);

                method.invoke(_contractMasterRemoteModel, contractMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getContractStatus() {
        return _contractStatus;
    }

    @Override
    public void setContractStatus(int contractStatus) {
        _contractStatus = contractStatus;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractStatus", int.class);

                method.invoke(_contractMasterRemoteModel, contractStatus);
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

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractId", String.class);

                method.invoke(_contractMasterRemoteModel, contractId);
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

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_contractMasterRemoteModel, modifiedDate);
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

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractType", int.class);

                method.invoke(_contractMasterRemoteModel, contractType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getAwardStatus() {
        return _awardStatus;
    }

    @Override
    public void setAwardStatus(int awardStatus) {
        _awardStatus = awardStatus;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAwardStatus", int.class);

                method.invoke(_contractMasterRemoteModel, awardStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getInsideOwner() {
        return _insideOwner;
    }

    @Override
    public void setInsideOwner(String insideOwner) {
        _insideOwner = insideOwner;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInsideOwner", String.class);

                method.invoke(_contractMasterRemoteModel, insideOwner);
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

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_contractMasterRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getShippingTerms() {
        return _shippingTerms;
    }

    @Override
    public void setShippingTerms(String shippingTerms) {
        _shippingTerms = shippingTerms;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setShippingTerms", String.class);

                method.invoke(_contractMasterRemoteModel, shippingTerms);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPriceEscalationClause() {
        return _priceEscalationClause;
    }

    @Override
    public void setPriceEscalationClause(String priceEscalationClause) {
        _priceEscalationClause = priceEscalationClause;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceEscalationClause",
                        String.class);

                method.invoke(_contractMasterRemoteModel, priceEscalationClause);
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

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_contractMasterRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getOutsideAdditionalPhone() {
        return _outsideAdditionalPhone;
    }

    @Override
    public void setOutsideAdditionalPhone(String outsideAdditionalPhone) {
        _outsideAdditionalPhone = outsideAdditionalPhone;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setOutsideAdditionalPhone",
                        String.class);

                method.invoke(_contractMasterRemoteModel, outsideAdditionalPhone);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getTerm() {
        return _term;
    }

    @Override
    public void setTerm(int term) {
        _term = term;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setTerm", int.class);

                method.invoke(_contractMasterRemoteModel, term);
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

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractNo", String.class);

                method.invoke(_contractMasterRemoteModel, contractNo);
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

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_contractMasterRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getDocumentClass() {
        return _documentClass;
    }

    @Override
    public void setDocumentClass(int documentClass) {
        _documentClass = documentClass;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDocumentClass", int.class);

                method.invoke(_contractMasterRemoteModel, documentClass);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getOriginalEndDate() {
        return _originalEndDate;
    }

    @Override
    public void setOriginalEndDate(Date originalEndDate) {
        _originalEndDate = originalEndDate;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setOriginalEndDate", Date.class);

                method.invoke(_contractMasterRemoteModel, originalEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPaymentTerms() {
        return _paymentTerms;
    }

    @Override
    public void setPaymentTerms(int paymentTerms) {
        _paymentTerms = paymentTerms;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPaymentTerms", int.class);

                method.invoke(_contractMasterRemoteModel, paymentTerms);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getInsideAdditional() {
        return _insideAdditional;
    }

    @Override
    public void setInsideAdditional(String insideAdditional) {
        _insideAdditional = insideAdditional;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInsideAdditional",
                        String.class);

                method.invoke(_contractMasterRemoteModel, insideAdditional);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAffiliatedContractInfo() {
        return _affiliatedContractInfo;
    }

    @Override
    public void setAffiliatedContractInfo(String affiliatedContractInfo) {
        _affiliatedContractInfo = affiliatedContractInfo;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAffiliatedContractInfo",
                        String.class);

                method.invoke(_contractMasterRemoteModel, affiliatedContractInfo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCategory() {
        return _category;
    }

    @Override
    public void setCategory(String category) {
        _category = category;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCategory", String.class);

                method.invoke(_contractMasterRemoteModel, category);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getOutsidePhone() {
        return _outsidePhone;
    }

    @Override
    public void setOutsidePhone(String outsidePhone) {
        _outsidePhone = outsidePhone;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setOutsidePhone", String.class);

                method.invoke(_contractMasterRemoteModel, outsidePhone);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPriceprotectionStartDate() {
        return _priceprotectionStartDate;
    }

    @Override
    public void setPriceprotectionStartDate(Date priceprotectionStartDate) {
        _priceprotectionStartDate = priceprotectionStartDate;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceprotectionStartDate",
                        Date.class);

                method.invoke(_contractMasterRemoteModel,
                    priceprotectionStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPriceprotectionEndDate() {
        return _priceprotectionEndDate;
    }

    @Override
    public void setPriceprotectionEndDate(Date priceprotectionEndDate) {
        _priceprotectionEndDate = priceprotectionEndDate;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceprotectionEndDate",
                        Date.class);

                method.invoke(_contractMasterRemoteModel, priceprotectionEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getDocumentType() {
        return _documentType;
    }

    @Override
    public void setDocumentType(int documentType) {
        _documentType = documentType;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDocumentType", int.class);

                method.invoke(_contractMasterRemoteModel, documentType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getExemptFromLowPrice() {
        return _exemptFromLowPrice;
    }

    @Override
    public void setExemptFromLowPrice(String exemptFromLowPrice) {
        _exemptFromLowPrice = exemptFromLowPrice;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setExemptFromLowPrice",
                        String.class);

                method.invoke(_contractMasterRemoteModel, exemptFromLowPrice);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getOrganizationKey() {
        return _organizationKey;
    }

    @Override
    public void setOrganizationKey(String organizationKey) {
        _organizationKey = organizationKey;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setOrganizationKey",
                        String.class);

                method.invoke(_contractMasterRemoteModel, organizationKey);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCurrency() {
        return _currency;
    }

    @Override
    public void setCurrency(String currency) {
        _currency = currency;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCurrency", String.class);

                method.invoke(_contractMasterRemoteModel, currency);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getInsidePhone() {
        return _insidePhone;
    }

    @Override
    public void setInsidePhone(String insidePhone) {
        _insidePhone = insidePhone;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInsidePhone", String.class);

                method.invoke(_contractMasterRemoteModel, insidePhone);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBunitCompanyMasterSid() {
        return _bunitCompanyMasterSid;
    }

    @Override
    public void setBunitCompanyMasterSid(String bunitCompanyMasterSid) {
        _bunitCompanyMasterSid = bunitCompanyMasterSid;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBunitCompanyMasterSid",
                        String.class);

                method.invoke(_contractMasterRemoteModel, bunitCompanyMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getOutsideAuthor() {
        return _outsideAuthor;
    }

    @Override
    public void setOutsideAuthor(String outsideAuthor) {
        _outsideAuthor = outsideAuthor;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setOutsideAuthor", String.class);

                method.invoke(_contractMasterRemoteModel, outsideAuthor);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getContHoldCompanyMasterSid() {
        return _contHoldCompanyMasterSid;
    }

    @Override
    public void setContHoldCompanyMasterSid(String contHoldCompanyMasterSid) {
        _contHoldCompanyMasterSid = contHoldCompanyMasterSid;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContHoldCompanyMasterSid",
                        String.class);

                method.invoke(_contractMasterRemoteModel,
                    contHoldCompanyMasterSid);
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

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setStartDate", Date.class);

                method.invoke(_contractMasterRemoteModel, startDate);
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

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractName", String.class);

                method.invoke(_contractMasterRemoteModel, contractName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getLastUpdatedDate() {
        return _lastUpdatedDate;
    }

    @Override
    public void setLastUpdatedDate(Date lastUpdatedDate) {
        _lastUpdatedDate = lastUpdatedDate;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLastUpdatedDate", Date.class);

                method.invoke(_contractMasterRemoteModel, lastUpdatedDate);
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

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_contractMasterRemoteModel, recordLockStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPriceResetIndicator() {
        return _priceResetIndicator;
    }

    @Override
    public void setPriceResetIndicator(String priceResetIndicator) {
        _priceResetIndicator = priceResetIndicator;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceResetIndicator",
                        String.class);

                method.invoke(_contractMasterRemoteModel, priceResetIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getMinimumOrder() {
        return _minimumOrder;
    }

    @Override
    public void setMinimumOrder(String minimumOrder) {
        _minimumOrder = minimumOrder;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setMinimumOrder", String.class);

                method.invoke(_contractMasterRemoteModel, minimumOrder);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCancellationClause() {
        return _cancellationClause;
    }

    @Override
    public void setCancellationClause(String cancellationClause) {
        _cancellationClause = cancellationClause;

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCancellationClause",
                        String.class);

                method.invoke(_contractMasterRemoteModel, cancellationClause);
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

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_contractMasterRemoteModel, inboundStatus);
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

        if (_contractMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInternalNotes", String.class);

                method.invoke(_contractMasterRemoteModel, internalNotes);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getContractMasterRemoteModel() {
        return _contractMasterRemoteModel;
    }

    public void setContractMasterRemoteModel(
        BaseModel<?> contractMasterRemoteModel) {
        _contractMasterRemoteModel = contractMasterRemoteModel;
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

        Class<?> remoteModelClass = _contractMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_contractMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ContractMasterLocalServiceUtil.addContractMaster(this);
        } else {
            ContractMasterLocalServiceUtil.updateContractMaster(this);
        }
    }

    @Override
    public ContractMaster toEscapedModel() {
        return (ContractMaster) ProxyUtil.newProxyInstance(ContractMaster.class.getClassLoader(),
            new Class[] { ContractMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ContractMasterClp clone = new ContractMasterClp();

        clone.setProposalEndDate(getProposalEndDate());
        clone.setCreatedDate(getCreatedDate());
        clone.setRenegotiationEndDate(getRenegotiationEndDate());
        clone.setOutsideAdditionalName(getOutsideAdditionalName());
        clone.setEndDate(getEndDate());
        clone.setManfCompanyMasterSid(getManfCompanyMasterSid());
        clone.setRenegotiationStartDate(getRenegotiationStartDate());
        clone.setInsideAuthor(getInsideAuthor());
        clone.setAdvanceNoticeDays(getAdvanceNoticeDays());
        clone.setOutsideOwner(getOutsideOwner());
        clone.setMostFavoredNation(getMostFavoredNation());
        clone.setInsideAdditionalPhone(getInsideAdditionalPhone());
        clone.setOriginalStartDate(getOriginalStartDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setProposalStartDate(getProposalStartDate());
        clone.setContractTradeClass(getContractTradeClass());
        clone.setOutsideAdditional(getOutsideAdditional());
        clone.setProcessStatus(getProcessStatus());
        clone.setInsideAdditionalName(getInsideAdditionalName());
        clone.setContractMasterSid(getContractMasterSid());
        clone.setContractStatus(getContractStatus());
        clone.setContractId(getContractId());
        clone.setModifiedDate(getModifiedDate());
        clone.setContractType(getContractType());
        clone.setAwardStatus(getAwardStatus());
        clone.setInsideOwner(getInsideOwner());
        clone.setSource(getSource());
        clone.setShippingTerms(getShippingTerms());
        clone.setPriceEscalationClause(getPriceEscalationClause());
        clone.setModifiedBy(getModifiedBy());
        clone.setOutsideAdditionalPhone(getOutsideAdditionalPhone());
        clone.setTerm(getTerm());
        clone.setContractNo(getContractNo());
        clone.setBatchId(getBatchId());
        clone.setDocumentClass(getDocumentClass());
        clone.setOriginalEndDate(getOriginalEndDate());
        clone.setPaymentTerms(getPaymentTerms());
        clone.setInsideAdditional(getInsideAdditional());
        clone.setAffiliatedContractInfo(getAffiliatedContractInfo());
        clone.setCategory(getCategory());
        clone.setOutsidePhone(getOutsidePhone());
        clone.setPriceprotectionStartDate(getPriceprotectionStartDate());
        clone.setPriceprotectionEndDate(getPriceprotectionEndDate());
        clone.setDocumentType(getDocumentType());
        clone.setExemptFromLowPrice(getExemptFromLowPrice());
        clone.setOrganizationKey(getOrganizationKey());
        clone.setCurrency(getCurrency());
        clone.setInsidePhone(getInsidePhone());
        clone.setBunitCompanyMasterSid(getBunitCompanyMasterSid());
        clone.setOutsideAuthor(getOutsideAuthor());
        clone.setContHoldCompanyMasterSid(getContHoldCompanyMasterSid());
        clone.setStartDate(getStartDate());
        clone.setContractName(getContractName());
        clone.setLastUpdatedDate(getLastUpdatedDate());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setPriceResetIndicator(getPriceResetIndicator());
        clone.setMinimumOrder(getMinimumOrder());
        clone.setCancellationClause(getCancellationClause());
        clone.setInboundStatus(getInboundStatus());
        clone.setInternalNotes(getInternalNotes());

        return clone;
    }

    @Override
    public int compareTo(ContractMaster contractMaster) {
        int primaryKey = contractMaster.getPrimaryKey();

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

        if (!(obj instanceof ContractMasterClp)) {
            return false;
        }

        ContractMasterClp contractMaster = (ContractMasterClp) obj;

        int primaryKey = contractMaster.getPrimaryKey();

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
        StringBundler sb = new StringBundler(121);

        sb.append("{proposalEndDate=");
        sb.append(getProposalEndDate());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", renegotiationEndDate=");
        sb.append(getRenegotiationEndDate());
        sb.append(", outsideAdditionalName=");
        sb.append(getOutsideAdditionalName());
        sb.append(", endDate=");
        sb.append(getEndDate());
        sb.append(", manfCompanyMasterSid=");
        sb.append(getManfCompanyMasterSid());
        sb.append(", renegotiationStartDate=");
        sb.append(getRenegotiationStartDate());
        sb.append(", insideAuthor=");
        sb.append(getInsideAuthor());
        sb.append(", advanceNoticeDays=");
        sb.append(getAdvanceNoticeDays());
        sb.append(", outsideOwner=");
        sb.append(getOutsideOwner());
        sb.append(", mostFavoredNation=");
        sb.append(getMostFavoredNation());
        sb.append(", insideAdditionalPhone=");
        sb.append(getInsideAdditionalPhone());
        sb.append(", originalStartDate=");
        sb.append(getOriginalStartDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", proposalStartDate=");
        sb.append(getProposalStartDate());
        sb.append(", contractTradeClass=");
        sb.append(getContractTradeClass());
        sb.append(", outsideAdditional=");
        sb.append(getOutsideAdditional());
        sb.append(", processStatus=");
        sb.append(getProcessStatus());
        sb.append(", insideAdditionalName=");
        sb.append(getInsideAdditionalName());
        sb.append(", contractMasterSid=");
        sb.append(getContractMasterSid());
        sb.append(", contractStatus=");
        sb.append(getContractStatus());
        sb.append(", contractId=");
        sb.append(getContractId());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", contractType=");
        sb.append(getContractType());
        sb.append(", awardStatus=");
        sb.append(getAwardStatus());
        sb.append(", insideOwner=");
        sb.append(getInsideOwner());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", shippingTerms=");
        sb.append(getShippingTerms());
        sb.append(", priceEscalationClause=");
        sb.append(getPriceEscalationClause());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", outsideAdditionalPhone=");
        sb.append(getOutsideAdditionalPhone());
        sb.append(", term=");
        sb.append(getTerm());
        sb.append(", contractNo=");
        sb.append(getContractNo());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", documentClass=");
        sb.append(getDocumentClass());
        sb.append(", originalEndDate=");
        sb.append(getOriginalEndDate());
        sb.append(", paymentTerms=");
        sb.append(getPaymentTerms());
        sb.append(", insideAdditional=");
        sb.append(getInsideAdditional());
        sb.append(", affiliatedContractInfo=");
        sb.append(getAffiliatedContractInfo());
        sb.append(", category=");
        sb.append(getCategory());
        sb.append(", outsidePhone=");
        sb.append(getOutsidePhone());
        sb.append(", priceprotectionStartDate=");
        sb.append(getPriceprotectionStartDate());
        sb.append(", priceprotectionEndDate=");
        sb.append(getPriceprotectionEndDate());
        sb.append(", documentType=");
        sb.append(getDocumentType());
        sb.append(", exemptFromLowPrice=");
        sb.append(getExemptFromLowPrice());
        sb.append(", organizationKey=");
        sb.append(getOrganizationKey());
        sb.append(", currency=");
        sb.append(getCurrency());
        sb.append(", insidePhone=");
        sb.append(getInsidePhone());
        sb.append(", bunitCompanyMasterSid=");
        sb.append(getBunitCompanyMasterSid());
        sb.append(", outsideAuthor=");
        sb.append(getOutsideAuthor());
        sb.append(", contHoldCompanyMasterSid=");
        sb.append(getContHoldCompanyMasterSid());
        sb.append(", startDate=");
        sb.append(getStartDate());
        sb.append(", contractName=");
        sb.append(getContractName());
        sb.append(", lastUpdatedDate=");
        sb.append(getLastUpdatedDate());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", priceResetIndicator=");
        sb.append(getPriceResetIndicator());
        sb.append(", minimumOrder=");
        sb.append(getMinimumOrder());
        sb.append(", cancellationClause=");
        sb.append(getCancellationClause());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", internalNotes=");
        sb.append(getInternalNotes());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(184);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ContractMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>proposalEndDate</column-name><column-value><![CDATA[");
        sb.append(getProposalEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>renegotiationEndDate</column-name><column-value><![CDATA[");
        sb.append(getRenegotiationEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>outsideAdditionalName</column-name><column-value><![CDATA[");
        sb.append(getOutsideAdditionalName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>endDate</column-name><column-value><![CDATA[");
        sb.append(getEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>manfCompanyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getManfCompanyMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>renegotiationStartDate</column-name><column-value><![CDATA[");
        sb.append(getRenegotiationStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>insideAuthor</column-name><column-value><![CDATA[");
        sb.append(getInsideAuthor());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>advanceNoticeDays</column-name><column-value><![CDATA[");
        sb.append(getAdvanceNoticeDays());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>outsideOwner</column-name><column-value><![CDATA[");
        sb.append(getOutsideOwner());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>mostFavoredNation</column-name><column-value><![CDATA[");
        sb.append(getMostFavoredNation());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>insideAdditionalPhone</column-name><column-value><![CDATA[");
        sb.append(getInsideAdditionalPhone());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>originalStartDate</column-name><column-value><![CDATA[");
        sb.append(getOriginalStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>proposalStartDate</column-name><column-value><![CDATA[");
        sb.append(getProposalStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractTradeClass</column-name><column-value><![CDATA[");
        sb.append(getContractTradeClass());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>outsideAdditional</column-name><column-value><![CDATA[");
        sb.append(getOutsideAdditional());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>processStatus</column-name><column-value><![CDATA[");
        sb.append(getProcessStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>insideAdditionalName</column-name><column-value><![CDATA[");
        sb.append(getInsideAdditionalName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractMasterSid</column-name><column-value><![CDATA[");
        sb.append(getContractMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractStatus</column-name><column-value><![CDATA[");
        sb.append(getContractStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractId</column-name><column-value><![CDATA[");
        sb.append(getContractId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractType</column-name><column-value><![CDATA[");
        sb.append(getContractType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>awardStatus</column-name><column-value><![CDATA[");
        sb.append(getAwardStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>insideOwner</column-name><column-value><![CDATA[");
        sb.append(getInsideOwner());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>shippingTerms</column-name><column-value><![CDATA[");
        sb.append(getShippingTerms());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceEscalationClause</column-name><column-value><![CDATA[");
        sb.append(getPriceEscalationClause());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>outsideAdditionalPhone</column-name><column-value><![CDATA[");
        sb.append(getOutsideAdditionalPhone());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>term</column-name><column-value><![CDATA[");
        sb.append(getTerm());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractNo</column-name><column-value><![CDATA[");
        sb.append(getContractNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>documentClass</column-name><column-value><![CDATA[");
        sb.append(getDocumentClass());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>originalEndDate</column-name><column-value><![CDATA[");
        sb.append(getOriginalEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>paymentTerms</column-name><column-value><![CDATA[");
        sb.append(getPaymentTerms());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>insideAdditional</column-name><column-value><![CDATA[");
        sb.append(getInsideAdditional());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>affiliatedContractInfo</column-name><column-value><![CDATA[");
        sb.append(getAffiliatedContractInfo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>category</column-name><column-value><![CDATA[");
        sb.append(getCategory());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>outsidePhone</column-name><column-value><![CDATA[");
        sb.append(getOutsidePhone());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceprotectionStartDate</column-name><column-value><![CDATA[");
        sb.append(getPriceprotectionStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceprotectionEndDate</column-name><column-value><![CDATA[");
        sb.append(getPriceprotectionEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>documentType</column-name><column-value><![CDATA[");
        sb.append(getDocumentType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>exemptFromLowPrice</column-name><column-value><![CDATA[");
        sb.append(getExemptFromLowPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>organizationKey</column-name><column-value><![CDATA[");
        sb.append(getOrganizationKey());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>currency</column-name><column-value><![CDATA[");
        sb.append(getCurrency());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>insidePhone</column-name><column-value><![CDATA[");
        sb.append(getInsidePhone());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>bunitCompanyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getBunitCompanyMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>outsideAuthor</column-name><column-value><![CDATA[");
        sb.append(getOutsideAuthor());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contHoldCompanyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getContHoldCompanyMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>startDate</column-name><column-value><![CDATA[");
        sb.append(getStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractName</column-name><column-value><![CDATA[");
        sb.append(getContractName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lastUpdatedDate</column-name><column-value><![CDATA[");
        sb.append(getLastUpdatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceResetIndicator</column-name><column-value><![CDATA[");
        sb.append(getPriceResetIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>minimumOrder</column-name><column-value><![CDATA[");
        sb.append(getMinimumOrder());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cancellationClause</column-name><column-value><![CDATA[");
        sb.append(getCancellationClause());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>internalNotes</column-name><column-value><![CDATA[");
        sb.append(getInternalNotes());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
