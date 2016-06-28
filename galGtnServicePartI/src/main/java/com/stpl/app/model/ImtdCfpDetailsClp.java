package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.ImtdCfpDetailsLocalServiceUtil;

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


public class ImtdCfpDetailsClp extends BaseModelImpl<ImtdCfpDetails>
    implements ImtdCfpDetails {
    private String _companyNo;
    private int _imtdCfpDetailsSid;
    private Date _cfpDetailsStartDate;
    private String _companyType;
    private Date _cfpDetailsTcStartDate;
    private int _modifiedBy;
    private Date _createdDate;
    private Date _cfpDetailsTcEndDate;
    private Date _cfpDetailsCreatedDate;
    private Date _imtdCreatedDate;
    private Date _cfpDetailsModifiedDate;
    private int _cfpDetailsAttachedStatus;
    private boolean _checkRecord;
    private Date _cfpDetailsAttachedDate;
    private Date _cfpDetailsEndDate;
    private String _companyId;
    private String _cfpDetailsTradeClass;
    private String _tradingPartnerContractNo;
    private int _createdBy;
    private String _usersSid;
    private Date _companyStartDate;
    private String _cfpDetailsModifiedBy;
    private Date _companyEndDate;
    private int _companyMasterSid;
    private int _cfpModelSid;
    private int _cfpDetailsSid;
    private int _companyStatus;
    private Date _modifiedDate;
    private String _companyName;
    private String _operation;
    private String _cfpDetailsCreatedBy;
    private String _sessionId;
    private BaseModel<?> _imtdCfpDetailsRemoteModel;

    public ImtdCfpDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ImtdCfpDetails.class;
    }

    @Override
    public String getModelClassName() {
        return ImtdCfpDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _imtdCfpDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setImtdCfpDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _imtdCfpDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("companyNo", getCompanyNo());
        attributes.put("imtdCfpDetailsSid", getImtdCfpDetailsSid());
        attributes.put("cfpDetailsStartDate", getCfpDetailsStartDate());
        attributes.put("companyType", getCompanyType());
        attributes.put("cfpDetailsTcStartDate", getCfpDetailsTcStartDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("cfpDetailsTcEndDate", getCfpDetailsTcEndDate());
        attributes.put("cfpDetailsCreatedDate", getCfpDetailsCreatedDate());
        attributes.put("imtdCreatedDate", getImtdCreatedDate());
        attributes.put("cfpDetailsModifiedDate", getCfpDetailsModifiedDate());
        attributes.put("cfpDetailsAttachedStatus", getCfpDetailsAttachedStatus());
        attributes.put("checkRecord", getCheckRecord());
        attributes.put("cfpDetailsAttachedDate", getCfpDetailsAttachedDate());
        attributes.put("cfpDetailsEndDate", getCfpDetailsEndDate());
        attributes.put("companyId", getCompanyId());
        attributes.put("cfpDetailsTradeClass", getCfpDetailsTradeClass());
        attributes.put("tradingPartnerContractNo", getTradingPartnerContractNo());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("usersSid", getUsersSid());
        attributes.put("companyStartDate", getCompanyStartDate());
        attributes.put("cfpDetailsModifiedBy", getCfpDetailsModifiedBy());
        attributes.put("companyEndDate", getCompanyEndDate());
        attributes.put("companyMasterSid", getCompanyMasterSid());
        attributes.put("cfpModelSid", getCfpModelSid());
        attributes.put("cfpDetailsSid", getCfpDetailsSid());
        attributes.put("companyStatus", getCompanyStatus());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("companyName", getCompanyName());
        attributes.put("operation", getOperation());
        attributes.put("cfpDetailsCreatedBy", getCfpDetailsCreatedBy());
        attributes.put("sessionId", getSessionId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String companyNo = (String) attributes.get("companyNo");

        if (companyNo != null) {
            setCompanyNo(companyNo);
        }

        Integer imtdCfpDetailsSid = (Integer) attributes.get(
                "imtdCfpDetailsSid");

        if (imtdCfpDetailsSid != null) {
            setImtdCfpDetailsSid(imtdCfpDetailsSid);
        }

        Date cfpDetailsStartDate = (Date) attributes.get("cfpDetailsStartDate");

        if (cfpDetailsStartDate != null) {
            setCfpDetailsStartDate(cfpDetailsStartDate);
        }

        String companyType = (String) attributes.get("companyType");

        if (companyType != null) {
            setCompanyType(companyType);
        }

        Date cfpDetailsTcStartDate = (Date) attributes.get(
                "cfpDetailsTcStartDate");

        if (cfpDetailsTcStartDate != null) {
            setCfpDetailsTcStartDate(cfpDetailsTcStartDate);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Date cfpDetailsTcEndDate = (Date) attributes.get("cfpDetailsTcEndDate");

        if (cfpDetailsTcEndDate != null) {
            setCfpDetailsTcEndDate(cfpDetailsTcEndDate);
        }

        Date cfpDetailsCreatedDate = (Date) attributes.get(
                "cfpDetailsCreatedDate");

        if (cfpDetailsCreatedDate != null) {
            setCfpDetailsCreatedDate(cfpDetailsCreatedDate);
        }

        Date imtdCreatedDate = (Date) attributes.get("imtdCreatedDate");

        if (imtdCreatedDate != null) {
            setImtdCreatedDate(imtdCreatedDate);
        }

        Date cfpDetailsModifiedDate = (Date) attributes.get(
                "cfpDetailsModifiedDate");

        if (cfpDetailsModifiedDate != null) {
            setCfpDetailsModifiedDate(cfpDetailsModifiedDate);
        }

        Integer cfpDetailsAttachedStatus = (Integer) attributes.get(
                "cfpDetailsAttachedStatus");

        if (cfpDetailsAttachedStatus != null) {
            setCfpDetailsAttachedStatus(cfpDetailsAttachedStatus);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        Date cfpDetailsAttachedDate = (Date) attributes.get(
                "cfpDetailsAttachedDate");

        if (cfpDetailsAttachedDate != null) {
            setCfpDetailsAttachedDate(cfpDetailsAttachedDate);
        }

        Date cfpDetailsEndDate = (Date) attributes.get("cfpDetailsEndDate");

        if (cfpDetailsEndDate != null) {
            setCfpDetailsEndDate(cfpDetailsEndDate);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        String cfpDetailsTradeClass = (String) attributes.get(
                "cfpDetailsTradeClass");

        if (cfpDetailsTradeClass != null) {
            setCfpDetailsTradeClass(cfpDetailsTradeClass);
        }

        String tradingPartnerContractNo = (String) attributes.get(
                "tradingPartnerContractNo");

        if (tradingPartnerContractNo != null) {
            setTradingPartnerContractNo(tradingPartnerContractNo);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String usersSid = (String) attributes.get("usersSid");

        if (usersSid != null) {
            setUsersSid(usersSid);
        }

        Date companyStartDate = (Date) attributes.get("companyStartDate");

        if (companyStartDate != null) {
            setCompanyStartDate(companyStartDate);
        }

        String cfpDetailsModifiedBy = (String) attributes.get(
                "cfpDetailsModifiedBy");

        if (cfpDetailsModifiedBy != null) {
            setCfpDetailsModifiedBy(cfpDetailsModifiedBy);
        }

        Date companyEndDate = (Date) attributes.get("companyEndDate");

        if (companyEndDate != null) {
            setCompanyEndDate(companyEndDate);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }

        Integer cfpModelSid = (Integer) attributes.get("cfpModelSid");

        if (cfpModelSid != null) {
            setCfpModelSid(cfpModelSid);
        }

        Integer cfpDetailsSid = (Integer) attributes.get("cfpDetailsSid");

        if (cfpDetailsSid != null) {
            setCfpDetailsSid(cfpDetailsSid);
        }

        Integer companyStatus = (Integer) attributes.get("companyStatus");

        if (companyStatus != null) {
            setCompanyStatus(companyStatus);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String companyName = (String) attributes.get("companyName");

        if (companyName != null) {
            setCompanyName(companyName);
        }

        String operation = (String) attributes.get("operation");

        if (operation != null) {
            setOperation(operation);
        }

        String cfpDetailsCreatedBy = (String) attributes.get(
                "cfpDetailsCreatedBy");

        if (cfpDetailsCreatedBy != null) {
            setCfpDetailsCreatedBy(cfpDetailsCreatedBy);
        }

        String sessionId = (String) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }
    }

    @Override
    public String getCompanyNo() {
        return _companyNo;
    }

    @Override
    public void setCompanyNo(String companyNo) {
        _companyNo = companyNo;

        if (_imtdCfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdCfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyNo", String.class);

                method.invoke(_imtdCfpDetailsRemoteModel, companyNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getImtdCfpDetailsSid() {
        return _imtdCfpDetailsSid;
    }

    @Override
    public void setImtdCfpDetailsSid(int imtdCfpDetailsSid) {
        _imtdCfpDetailsSid = imtdCfpDetailsSid;

        if (_imtdCfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdCfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setImtdCfpDetailsSid",
                        int.class);

                method.invoke(_imtdCfpDetailsRemoteModel, imtdCfpDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCfpDetailsStartDate() {
        return _cfpDetailsStartDate;
    }

    @Override
    public void setCfpDetailsStartDate(Date cfpDetailsStartDate) {
        _cfpDetailsStartDate = cfpDetailsStartDate;

        if (_imtdCfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdCfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpDetailsStartDate",
                        Date.class);

                method.invoke(_imtdCfpDetailsRemoteModel, cfpDetailsStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyType() {
        return _companyType;
    }

    @Override
    public void setCompanyType(String companyType) {
        _companyType = companyType;

        if (_imtdCfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdCfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyType", String.class);

                method.invoke(_imtdCfpDetailsRemoteModel, companyType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCfpDetailsTcStartDate() {
        return _cfpDetailsTcStartDate;
    }

    @Override
    public void setCfpDetailsTcStartDate(Date cfpDetailsTcStartDate) {
        _cfpDetailsTcStartDate = cfpDetailsTcStartDate;

        if (_imtdCfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdCfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpDetailsTcStartDate",
                        Date.class);

                method.invoke(_imtdCfpDetailsRemoteModel, cfpDetailsTcStartDate);
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

        if (_imtdCfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdCfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_imtdCfpDetailsRemoteModel, modifiedBy);
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

        if (_imtdCfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdCfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_imtdCfpDetailsRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCfpDetailsTcEndDate() {
        return _cfpDetailsTcEndDate;
    }

    @Override
    public void setCfpDetailsTcEndDate(Date cfpDetailsTcEndDate) {
        _cfpDetailsTcEndDate = cfpDetailsTcEndDate;

        if (_imtdCfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdCfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpDetailsTcEndDate",
                        Date.class);

                method.invoke(_imtdCfpDetailsRemoteModel, cfpDetailsTcEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCfpDetailsCreatedDate() {
        return _cfpDetailsCreatedDate;
    }

    @Override
    public void setCfpDetailsCreatedDate(Date cfpDetailsCreatedDate) {
        _cfpDetailsCreatedDate = cfpDetailsCreatedDate;

        if (_imtdCfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdCfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpDetailsCreatedDate",
                        Date.class);

                method.invoke(_imtdCfpDetailsRemoteModel, cfpDetailsCreatedDate);
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

        if (_imtdCfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdCfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setImtdCreatedDate", Date.class);

                method.invoke(_imtdCfpDetailsRemoteModel, imtdCreatedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCfpDetailsModifiedDate() {
        return _cfpDetailsModifiedDate;
    }

    @Override
    public void setCfpDetailsModifiedDate(Date cfpDetailsModifiedDate) {
        _cfpDetailsModifiedDate = cfpDetailsModifiedDate;

        if (_imtdCfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdCfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpDetailsModifiedDate",
                        Date.class);

                method.invoke(_imtdCfpDetailsRemoteModel, cfpDetailsModifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCfpDetailsAttachedStatus() {
        return _cfpDetailsAttachedStatus;
    }

    @Override
    public void setCfpDetailsAttachedStatus(int cfpDetailsAttachedStatus) {
        _cfpDetailsAttachedStatus = cfpDetailsAttachedStatus;

        if (_imtdCfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdCfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpDetailsAttachedStatus",
                        int.class);

                method.invoke(_imtdCfpDetailsRemoteModel,
                    cfpDetailsAttachedStatus);
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

        if (_imtdCfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdCfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_imtdCfpDetailsRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCfpDetailsAttachedDate() {
        return _cfpDetailsAttachedDate;
    }

    @Override
    public void setCfpDetailsAttachedDate(Date cfpDetailsAttachedDate) {
        _cfpDetailsAttachedDate = cfpDetailsAttachedDate;

        if (_imtdCfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdCfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpDetailsAttachedDate",
                        Date.class);

                method.invoke(_imtdCfpDetailsRemoteModel, cfpDetailsAttachedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCfpDetailsEndDate() {
        return _cfpDetailsEndDate;
    }

    @Override
    public void setCfpDetailsEndDate(Date cfpDetailsEndDate) {
        _cfpDetailsEndDate = cfpDetailsEndDate;

        if (_imtdCfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdCfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpDetailsEndDate",
                        Date.class);

                method.invoke(_imtdCfpDetailsRemoteModel, cfpDetailsEndDate);
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

        if (_imtdCfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdCfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyId", String.class);

                method.invoke(_imtdCfpDetailsRemoteModel, companyId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCfpDetailsTradeClass() {
        return _cfpDetailsTradeClass;
    }

    @Override
    public void setCfpDetailsTradeClass(String cfpDetailsTradeClass) {
        _cfpDetailsTradeClass = cfpDetailsTradeClass;

        if (_imtdCfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdCfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpDetailsTradeClass",
                        String.class);

                method.invoke(_imtdCfpDetailsRemoteModel, cfpDetailsTradeClass);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getTradingPartnerContractNo() {
        return _tradingPartnerContractNo;
    }

    @Override
    public void setTradingPartnerContractNo(String tradingPartnerContractNo) {
        _tradingPartnerContractNo = tradingPartnerContractNo;

        if (_imtdCfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdCfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setTradingPartnerContractNo",
                        String.class);

                method.invoke(_imtdCfpDetailsRemoteModel,
                    tradingPartnerContractNo);
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

        if (_imtdCfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdCfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_imtdCfpDetailsRemoteModel, createdBy);
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

        if (_imtdCfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdCfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setUsersSid", String.class);

                method.invoke(_imtdCfpDetailsRemoteModel, usersSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCompanyStartDate() {
        return _companyStartDate;
    }

    @Override
    public void setCompanyStartDate(Date companyStartDate) {
        _companyStartDate = companyStartDate;

        if (_imtdCfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdCfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyStartDate",
                        Date.class);

                method.invoke(_imtdCfpDetailsRemoteModel, companyStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCfpDetailsModifiedBy() {
        return _cfpDetailsModifiedBy;
    }

    @Override
    public void setCfpDetailsModifiedBy(String cfpDetailsModifiedBy) {
        _cfpDetailsModifiedBy = cfpDetailsModifiedBy;

        if (_imtdCfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdCfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpDetailsModifiedBy",
                        String.class);

                method.invoke(_imtdCfpDetailsRemoteModel, cfpDetailsModifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCompanyEndDate() {
        return _companyEndDate;
    }

    @Override
    public void setCompanyEndDate(Date companyEndDate) {
        _companyEndDate = companyEndDate;

        if (_imtdCfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdCfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyEndDate", Date.class);

                method.invoke(_imtdCfpDetailsRemoteModel, companyEndDate);
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

        if (_imtdCfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdCfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyMasterSid", int.class);

                method.invoke(_imtdCfpDetailsRemoteModel, companyMasterSid);
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

        if (_imtdCfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdCfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpModelSid", int.class);

                method.invoke(_imtdCfpDetailsRemoteModel, cfpModelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCfpDetailsSid() {
        return _cfpDetailsSid;
    }

    @Override
    public void setCfpDetailsSid(int cfpDetailsSid) {
        _cfpDetailsSid = cfpDetailsSid;

        if (_imtdCfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdCfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpDetailsSid", int.class);

                method.invoke(_imtdCfpDetailsRemoteModel, cfpDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCompanyStatus() {
        return _companyStatus;
    }

    @Override
    public void setCompanyStatus(int companyStatus) {
        _companyStatus = companyStatus;

        if (_imtdCfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdCfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyStatus", int.class);

                method.invoke(_imtdCfpDetailsRemoteModel, companyStatus);
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

        if (_imtdCfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdCfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_imtdCfpDetailsRemoteModel, modifiedDate);
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

        if (_imtdCfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdCfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyName", String.class);

                method.invoke(_imtdCfpDetailsRemoteModel, companyName);
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

        if (_imtdCfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdCfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setOperation", String.class);

                method.invoke(_imtdCfpDetailsRemoteModel, operation);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCfpDetailsCreatedBy() {
        return _cfpDetailsCreatedBy;
    }

    @Override
    public void setCfpDetailsCreatedBy(String cfpDetailsCreatedBy) {
        _cfpDetailsCreatedBy = cfpDetailsCreatedBy;

        if (_imtdCfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdCfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpDetailsCreatedBy",
                        String.class);

                method.invoke(_imtdCfpDetailsRemoteModel, cfpDetailsCreatedBy);
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

        if (_imtdCfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdCfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", String.class);

                method.invoke(_imtdCfpDetailsRemoteModel, sessionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getImtdCfpDetailsRemoteModel() {
        return _imtdCfpDetailsRemoteModel;
    }

    public void setImtdCfpDetailsRemoteModel(
        BaseModel<?> imtdCfpDetailsRemoteModel) {
        _imtdCfpDetailsRemoteModel = imtdCfpDetailsRemoteModel;
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

        Class<?> remoteModelClass = _imtdCfpDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_imtdCfpDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ImtdCfpDetailsLocalServiceUtil.addImtdCfpDetails(this);
        } else {
            ImtdCfpDetailsLocalServiceUtil.updateImtdCfpDetails(this);
        }
    }

    @Override
    public ImtdCfpDetails toEscapedModel() {
        return (ImtdCfpDetails) ProxyUtil.newProxyInstance(ImtdCfpDetails.class.getClassLoader(),
            new Class[] { ImtdCfpDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ImtdCfpDetailsClp clone = new ImtdCfpDetailsClp();

        clone.setCompanyNo(getCompanyNo());
        clone.setImtdCfpDetailsSid(getImtdCfpDetailsSid());
        clone.setCfpDetailsStartDate(getCfpDetailsStartDate());
        clone.setCompanyType(getCompanyType());
        clone.setCfpDetailsTcStartDate(getCfpDetailsTcStartDate());
        clone.setModifiedBy(getModifiedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setCfpDetailsTcEndDate(getCfpDetailsTcEndDate());
        clone.setCfpDetailsCreatedDate(getCfpDetailsCreatedDate());
        clone.setImtdCreatedDate(getImtdCreatedDate());
        clone.setCfpDetailsModifiedDate(getCfpDetailsModifiedDate());
        clone.setCfpDetailsAttachedStatus(getCfpDetailsAttachedStatus());
        clone.setCheckRecord(getCheckRecord());
        clone.setCfpDetailsAttachedDate(getCfpDetailsAttachedDate());
        clone.setCfpDetailsEndDate(getCfpDetailsEndDate());
        clone.setCompanyId(getCompanyId());
        clone.setCfpDetailsTradeClass(getCfpDetailsTradeClass());
        clone.setTradingPartnerContractNo(getTradingPartnerContractNo());
        clone.setCreatedBy(getCreatedBy());
        clone.setUsersSid(getUsersSid());
        clone.setCompanyStartDate(getCompanyStartDate());
        clone.setCfpDetailsModifiedBy(getCfpDetailsModifiedBy());
        clone.setCompanyEndDate(getCompanyEndDate());
        clone.setCompanyMasterSid(getCompanyMasterSid());
        clone.setCfpModelSid(getCfpModelSid());
        clone.setCfpDetailsSid(getCfpDetailsSid());
        clone.setCompanyStatus(getCompanyStatus());
        clone.setModifiedDate(getModifiedDate());
        clone.setCompanyName(getCompanyName());
        clone.setOperation(getOperation());
        clone.setCfpDetailsCreatedBy(getCfpDetailsCreatedBy());
        clone.setSessionId(getSessionId());

        return clone;
    }

    @Override
    public int compareTo(ImtdCfpDetails imtdCfpDetails) {
        int primaryKey = imtdCfpDetails.getPrimaryKey();

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

        if (!(obj instanceof ImtdCfpDetailsClp)) {
            return false;
        }

        ImtdCfpDetailsClp imtdCfpDetails = (ImtdCfpDetailsClp) obj;

        int primaryKey = imtdCfpDetails.getPrimaryKey();

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
        StringBundler sb = new StringBundler(65);

        sb.append("{companyNo=");
        sb.append(getCompanyNo());
        sb.append(", imtdCfpDetailsSid=");
        sb.append(getImtdCfpDetailsSid());
        sb.append(", cfpDetailsStartDate=");
        sb.append(getCfpDetailsStartDate());
        sb.append(", companyType=");
        sb.append(getCompanyType());
        sb.append(", cfpDetailsTcStartDate=");
        sb.append(getCfpDetailsTcStartDate());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", cfpDetailsTcEndDate=");
        sb.append(getCfpDetailsTcEndDate());
        sb.append(", cfpDetailsCreatedDate=");
        sb.append(getCfpDetailsCreatedDate());
        sb.append(", imtdCreatedDate=");
        sb.append(getImtdCreatedDate());
        sb.append(", cfpDetailsModifiedDate=");
        sb.append(getCfpDetailsModifiedDate());
        sb.append(", cfpDetailsAttachedStatus=");
        sb.append(getCfpDetailsAttachedStatus());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append(", cfpDetailsAttachedDate=");
        sb.append(getCfpDetailsAttachedDate());
        sb.append(", cfpDetailsEndDate=");
        sb.append(getCfpDetailsEndDate());
        sb.append(", companyId=");
        sb.append(getCompanyId());
        sb.append(", cfpDetailsTradeClass=");
        sb.append(getCfpDetailsTradeClass());
        sb.append(", tradingPartnerContractNo=");
        sb.append(getTradingPartnerContractNo());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", usersSid=");
        sb.append(getUsersSid());
        sb.append(", companyStartDate=");
        sb.append(getCompanyStartDate());
        sb.append(", cfpDetailsModifiedBy=");
        sb.append(getCfpDetailsModifiedBy());
        sb.append(", companyEndDate=");
        sb.append(getCompanyEndDate());
        sb.append(", companyMasterSid=");
        sb.append(getCompanyMasterSid());
        sb.append(", cfpModelSid=");
        sb.append(getCfpModelSid());
        sb.append(", cfpDetailsSid=");
        sb.append(getCfpDetailsSid());
        sb.append(", companyStatus=");
        sb.append(getCompanyStatus());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", companyName=");
        sb.append(getCompanyName());
        sb.append(", operation=");
        sb.append(getOperation());
        sb.append(", cfpDetailsCreatedBy=");
        sb.append(getCfpDetailsCreatedBy());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(100);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ImtdCfpDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>companyNo</column-name><column-value><![CDATA[");
        sb.append(getCompanyNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>imtdCfpDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getImtdCfpDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpDetailsStartDate</column-name><column-value><![CDATA[");
        sb.append(getCfpDetailsStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyType</column-name><column-value><![CDATA[");
        sb.append(getCompanyType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpDetailsTcStartDate</column-name><column-value><![CDATA[");
        sb.append(getCfpDetailsTcStartDate());
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
            "<column><column-name>cfpDetailsTcEndDate</column-name><column-value><![CDATA[");
        sb.append(getCfpDetailsTcEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpDetailsCreatedDate</column-name><column-value><![CDATA[");
        sb.append(getCfpDetailsCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>imtdCreatedDate</column-name><column-value><![CDATA[");
        sb.append(getImtdCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpDetailsModifiedDate</column-name><column-value><![CDATA[");
        sb.append(getCfpDetailsModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpDetailsAttachedStatus</column-name><column-value><![CDATA[");
        sb.append(getCfpDetailsAttachedStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpDetailsAttachedDate</column-name><column-value><![CDATA[");
        sb.append(getCfpDetailsAttachedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpDetailsEndDate</column-name><column-value><![CDATA[");
        sb.append(getCfpDetailsEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyId</column-name><column-value><![CDATA[");
        sb.append(getCompanyId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpDetailsTradeClass</column-name><column-value><![CDATA[");
        sb.append(getCfpDetailsTradeClass());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>tradingPartnerContractNo</column-name><column-value><![CDATA[");
        sb.append(getTradingPartnerContractNo());
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
            "<column><column-name>companyStartDate</column-name><column-value><![CDATA[");
        sb.append(getCompanyStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpDetailsModifiedBy</column-name><column-value><![CDATA[");
        sb.append(getCfpDetailsModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyEndDate</column-name><column-value><![CDATA[");
        sb.append(getCompanyEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpModelSid</column-name><column-value><![CDATA[");
        sb.append(getCfpModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getCfpDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyStatus</column-name><column-value><![CDATA[");
        sb.append(getCompanyStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyName</column-name><column-value><![CDATA[");
        sb.append(getCompanyName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>operation</column-name><column-value><![CDATA[");
        sb.append(getOperation());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpDetailsCreatedBy</column-name><column-value><![CDATA[");
        sb.append(getCfpDetailsCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sessionId</column-name><column-value><![CDATA[");
        sb.append(getSessionId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
