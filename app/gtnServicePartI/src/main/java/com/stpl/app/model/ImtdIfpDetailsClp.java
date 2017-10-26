package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.ImtdIfpDetailsLocalServiceUtil;

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


public class ImtdIfpDetailsClp extends BaseModelImpl<ImtdIfpDetails>
    implements ImtdIfpDetails {
    private int _itemStatus;
    private Date _ifpDetailsEndDate;
    private int _itemMasterSid;
    private Date _imtdCreateddate;
    private String _itemPackageSize;
    private Date _ifpDetailsCreatedDate;
    private String _totalDollarCommitment;
    private String _ifpDetailsCreatedBy;
    private String _itemId;
    private Date _modifiedDate;
    private String _ifpDetailsModifiedBy;
    private Date _ifpDetailsModifiedDate;
    private Date _createdDate;
    private int _createdBy;
    private int _usersSid;
    private String _itemDesc;
    private Date _itemStartDate;
    private String _itemStrength;
    private int _contractMasterSid;
    private int _modifiedBy;
    private String _commitmentPeriod;
    private String _itemNo;
    private int _ifpDetailsSid;
    private int _ifpModelSid;
    private String _itemTherapeuticClass;
    private Date _ifpDetailsStartDate;
    private String _itemForm;
    private String _totalVolumeCommitment;
    private Date _itemEndDate;
    private boolean _checkBox;
    private int _ifpDetailsAttachedStatus;
    private String _totalMarketshareCommitment;
    private Date _ifpDetailsAttachedDate;
    private int _imtdIfpDetailsSid;
    private String _sessionId;
    private String _itemName;
    private String _itemPrimaryUom;
    private String _operation;
    private String _itemBrand;
    private int _cfpModelSid;
    private BaseModel<?> _imtdIfpDetailsRemoteModel;

    public ImtdIfpDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ImtdIfpDetails.class;
    }

    @Override
    public String getModelClassName() {
        return ImtdIfpDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _imtdIfpDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setImtdIfpDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _imtdIfpDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemStatus", getItemStatus());
        attributes.put("ifpDetailsEndDate", getIfpDetailsEndDate());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("imtdCreateddate", getImtdCreateddate());
        attributes.put("itemPackageSize", getItemPackageSize());
        attributes.put("ifpDetailsCreatedDate", getIfpDetailsCreatedDate());
        attributes.put("totalDollarCommitment", getTotalDollarCommitment());
        attributes.put("ifpDetailsCreatedBy", getIfpDetailsCreatedBy());
        attributes.put("itemId", getItemId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("ifpDetailsModifiedBy", getIfpDetailsModifiedBy());
        attributes.put("ifpDetailsModifiedDate", getIfpDetailsModifiedDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("usersSid", getUsersSid());
        attributes.put("itemDesc", getItemDesc());
        attributes.put("itemStartDate", getItemStartDate());
        attributes.put("itemStrength", getItemStrength());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("commitmentPeriod", getCommitmentPeriod());
        attributes.put("itemNo", getItemNo());
        attributes.put("ifpDetailsSid", getIfpDetailsSid());
        attributes.put("ifpModelSid", getIfpModelSid());
        attributes.put("itemTherapeuticClass", getItemTherapeuticClass());
        attributes.put("ifpDetailsStartDate", getIfpDetailsStartDate());
        attributes.put("itemForm", getItemForm());
        attributes.put("totalVolumeCommitment", getTotalVolumeCommitment());
        attributes.put("itemEndDate", getItemEndDate());
        attributes.put("checkBox", getCheckBox());
        attributes.put("ifpDetailsAttachedStatus", getIfpDetailsAttachedStatus());
        attributes.put("totalMarketshareCommitment",
            getTotalMarketshareCommitment());
        attributes.put("ifpDetailsAttachedDate", getIfpDetailsAttachedDate());
        attributes.put("imtdIfpDetailsSid", getImtdIfpDetailsSid());
        attributes.put("sessionId", getSessionId());
        attributes.put("itemName", getItemName());
        attributes.put("itemPrimaryUom", getItemPrimaryUom());
        attributes.put("operation", getOperation());
        attributes.put("itemBrand", getItemBrand());
        attributes.put("cfpModelSid", getCfpModelSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer itemStatus = (Integer) attributes.get("itemStatus");

        if (itemStatus != null) {
            setItemStatus(itemStatus);
        }

        Date ifpDetailsEndDate = (Date) attributes.get("ifpDetailsEndDate");

        if (ifpDetailsEndDate != null) {
            setIfpDetailsEndDate(ifpDetailsEndDate);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Date imtdCreateddate = (Date) attributes.get("imtdCreateddate");

        if (imtdCreateddate != null) {
            setImtdCreateddate(imtdCreateddate);
        }

        String itemPackageSize = (String) attributes.get("itemPackageSize");

        if (itemPackageSize != null) {
            setItemPackageSize(itemPackageSize);
        }

        Date ifpDetailsCreatedDate = (Date) attributes.get(
                "ifpDetailsCreatedDate");

        if (ifpDetailsCreatedDate != null) {
            setIfpDetailsCreatedDate(ifpDetailsCreatedDate);
        }

        String totalDollarCommitment = (String) attributes.get(
                "totalDollarCommitment");

        if (totalDollarCommitment != null) {
            setTotalDollarCommitment(totalDollarCommitment);
        }

        String ifpDetailsCreatedBy = (String) attributes.get(
                "ifpDetailsCreatedBy");

        if (ifpDetailsCreatedBy != null) {
            setIfpDetailsCreatedBy(ifpDetailsCreatedBy);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String ifpDetailsModifiedBy = (String) attributes.get(
                "ifpDetailsModifiedBy");

        if (ifpDetailsModifiedBy != null) {
            setIfpDetailsModifiedBy(ifpDetailsModifiedBy);
        }

        Date ifpDetailsModifiedDate = (Date) attributes.get(
                "ifpDetailsModifiedDate");

        if (ifpDetailsModifiedDate != null) {
            setIfpDetailsModifiedDate(ifpDetailsModifiedDate);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer usersSid = (Integer) attributes.get("usersSid");

        if (usersSid != null) {
            setUsersSid(usersSid);
        }

        String itemDesc = (String) attributes.get("itemDesc");

        if (itemDesc != null) {
            setItemDesc(itemDesc);
        }

        Date itemStartDate = (Date) attributes.get("itemStartDate");

        if (itemStartDate != null) {
            setItemStartDate(itemStartDate);
        }

        String itemStrength = (String) attributes.get("itemStrength");

        if (itemStrength != null) {
            setItemStrength(itemStrength);
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

        String commitmentPeriod = (String) attributes.get("commitmentPeriod");

        if (commitmentPeriod != null) {
            setCommitmentPeriod(commitmentPeriod);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        Integer ifpDetailsSid = (Integer) attributes.get("ifpDetailsSid");

        if (ifpDetailsSid != null) {
            setIfpDetailsSid(ifpDetailsSid);
        }

        Integer ifpModelSid = (Integer) attributes.get("ifpModelSid");

        if (ifpModelSid != null) {
            setIfpModelSid(ifpModelSid);
        }

        String itemTherapeuticClass = (String) attributes.get(
                "itemTherapeuticClass");

        if (itemTherapeuticClass != null) {
            setItemTherapeuticClass(itemTherapeuticClass);
        }

        Date ifpDetailsStartDate = (Date) attributes.get("ifpDetailsStartDate");

        if (ifpDetailsStartDate != null) {
            setIfpDetailsStartDate(ifpDetailsStartDate);
        }

        String itemForm = (String) attributes.get("itemForm");

        if (itemForm != null) {
            setItemForm(itemForm);
        }

        String totalVolumeCommitment = (String) attributes.get(
                "totalVolumeCommitment");

        if (totalVolumeCommitment != null) {
            setTotalVolumeCommitment(totalVolumeCommitment);
        }

        Date itemEndDate = (Date) attributes.get("itemEndDate");

        if (itemEndDate != null) {
            setItemEndDate(itemEndDate);
        }

        Boolean checkBox = (Boolean) attributes.get("checkBox");

        if (checkBox != null) {
            setCheckBox(checkBox);
        }

        Integer ifpDetailsAttachedStatus = (Integer) attributes.get(
                "ifpDetailsAttachedStatus");

        if (ifpDetailsAttachedStatus != null) {
            setIfpDetailsAttachedStatus(ifpDetailsAttachedStatus);
        }

        String totalMarketshareCommitment = (String) attributes.get(
                "totalMarketshareCommitment");

        if (totalMarketshareCommitment != null) {
            setTotalMarketshareCommitment(totalMarketshareCommitment);
        }

        Date ifpDetailsAttachedDate = (Date) attributes.get(
                "ifpDetailsAttachedDate");

        if (ifpDetailsAttachedDate != null) {
            setIfpDetailsAttachedDate(ifpDetailsAttachedDate);
        }

        Integer imtdIfpDetailsSid = (Integer) attributes.get(
                "imtdIfpDetailsSid");

        if (imtdIfpDetailsSid != null) {
            setImtdIfpDetailsSid(imtdIfpDetailsSid);
        }

        String sessionId = (String) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        String itemPrimaryUom = (String) attributes.get("itemPrimaryUom");

        if (itemPrimaryUom != null) {
            setItemPrimaryUom(itemPrimaryUom);
        }

        String operation = (String) attributes.get("operation");

        if (operation != null) {
            setOperation(operation);
        }

        String itemBrand = (String) attributes.get("itemBrand");

        if (itemBrand != null) {
            setItemBrand(itemBrand);
        }

        Integer cfpModelSid = (Integer) attributes.get("cfpModelSid");

        if (cfpModelSid != null) {
            setCfpModelSid(cfpModelSid);
        }
    }

    @Override
    public int getItemStatus() {
        return _itemStatus;
    }

    @Override
    public void setItemStatus(int itemStatus) {
        _itemStatus = itemStatus;

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemStatus", int.class);

                method.invoke(_imtdIfpDetailsRemoteModel, itemStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getIfpDetailsEndDate() {
        return _ifpDetailsEndDate;
    }

    @Override
    public void setIfpDetailsEndDate(Date ifpDetailsEndDate) {
        _ifpDetailsEndDate = ifpDetailsEndDate;

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpDetailsEndDate",
                        Date.class);

                method.invoke(_imtdIfpDetailsRemoteModel, ifpDetailsEndDate);
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

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_imtdIfpDetailsRemoteModel, itemMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getImtdCreateddate() {
        return _imtdCreateddate;
    }

    @Override
    public void setImtdCreateddate(Date imtdCreateddate) {
        _imtdCreateddate = imtdCreateddate;

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setImtdCreateddate", Date.class);

                method.invoke(_imtdIfpDetailsRemoteModel, imtdCreateddate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemPackageSize() {
        return _itemPackageSize;
    }

    @Override
    public void setItemPackageSize(String itemPackageSize) {
        _itemPackageSize = itemPackageSize;

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemPackageSize",
                        String.class);

                method.invoke(_imtdIfpDetailsRemoteModel, itemPackageSize);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getIfpDetailsCreatedDate() {
        return _ifpDetailsCreatedDate;
    }

    @Override
    public void setIfpDetailsCreatedDate(Date ifpDetailsCreatedDate) {
        _ifpDetailsCreatedDate = ifpDetailsCreatedDate;

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpDetailsCreatedDate",
                        Date.class);

                method.invoke(_imtdIfpDetailsRemoteModel, ifpDetailsCreatedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getTotalDollarCommitment() {
        return _totalDollarCommitment;
    }

    @Override
    public void setTotalDollarCommitment(String totalDollarCommitment) {
        _totalDollarCommitment = totalDollarCommitment;

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDollarCommitment",
                        String.class);

                method.invoke(_imtdIfpDetailsRemoteModel, totalDollarCommitment);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIfpDetailsCreatedBy() {
        return _ifpDetailsCreatedBy;
    }

    @Override
    public void setIfpDetailsCreatedBy(String ifpDetailsCreatedBy) {
        _ifpDetailsCreatedBy = ifpDetailsCreatedBy;

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpDetailsCreatedBy",
                        String.class);

                method.invoke(_imtdIfpDetailsRemoteModel, ifpDetailsCreatedBy);
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

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_imtdIfpDetailsRemoteModel, itemId);
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

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_imtdIfpDetailsRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIfpDetailsModifiedBy() {
        return _ifpDetailsModifiedBy;
    }

    @Override
    public void setIfpDetailsModifiedBy(String ifpDetailsModifiedBy) {
        _ifpDetailsModifiedBy = ifpDetailsModifiedBy;

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpDetailsModifiedBy",
                        String.class);

                method.invoke(_imtdIfpDetailsRemoteModel, ifpDetailsModifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getIfpDetailsModifiedDate() {
        return _ifpDetailsModifiedDate;
    }

    @Override
    public void setIfpDetailsModifiedDate(Date ifpDetailsModifiedDate) {
        _ifpDetailsModifiedDate = ifpDetailsModifiedDate;

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpDetailsModifiedDate",
                        Date.class);

                method.invoke(_imtdIfpDetailsRemoteModel, ifpDetailsModifiedDate);
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

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_imtdIfpDetailsRemoteModel, createdDate);
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

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_imtdIfpDetailsRemoteModel, createdBy);
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

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setUsersSid", int.class);

                method.invoke(_imtdIfpDetailsRemoteModel, usersSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemDesc() {
        return _itemDesc;
    }

    @Override
    public void setItemDesc(String itemDesc) {
        _itemDesc = itemDesc;

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemDesc", String.class);

                method.invoke(_imtdIfpDetailsRemoteModel, itemDesc);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getItemStartDate() {
        return _itemStartDate;
    }

    @Override
    public void setItemStartDate(Date itemStartDate) {
        _itemStartDate = itemStartDate;

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemStartDate", Date.class);

                method.invoke(_imtdIfpDetailsRemoteModel, itemStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemStrength() {
        return _itemStrength;
    }

    @Override
    public void setItemStrength(String itemStrength) {
        _itemStrength = itemStrength;

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemStrength", String.class);

                method.invoke(_imtdIfpDetailsRemoteModel, itemStrength);
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

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractMasterSid",
                        int.class);

                method.invoke(_imtdIfpDetailsRemoteModel, contractMasterSid);
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

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_imtdIfpDetailsRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCommitmentPeriod() {
        return _commitmentPeriod;
    }

    @Override
    public void setCommitmentPeriod(String commitmentPeriod) {
        _commitmentPeriod = commitmentPeriod;

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCommitmentPeriod",
                        String.class);

                method.invoke(_imtdIfpDetailsRemoteModel, commitmentPeriod);
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

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemNo", String.class);

                method.invoke(_imtdIfpDetailsRemoteModel, itemNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIfpDetailsSid() {
        return _ifpDetailsSid;
    }

    @Override
    public void setIfpDetailsSid(int ifpDetailsSid) {
        _ifpDetailsSid = ifpDetailsSid;

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpDetailsSid", int.class);

                method.invoke(_imtdIfpDetailsRemoteModel, ifpDetailsSid);
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

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpModelSid", int.class);

                method.invoke(_imtdIfpDetailsRemoteModel, ifpModelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemTherapeuticClass() {
        return _itemTherapeuticClass;
    }

    @Override
    public void setItemTherapeuticClass(String itemTherapeuticClass) {
        _itemTherapeuticClass = itemTherapeuticClass;

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemTherapeuticClass",
                        String.class);

                method.invoke(_imtdIfpDetailsRemoteModel, itemTherapeuticClass);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getIfpDetailsStartDate() {
        return _ifpDetailsStartDate;
    }

    @Override
    public void setIfpDetailsStartDate(Date ifpDetailsStartDate) {
        _ifpDetailsStartDate = ifpDetailsStartDate;

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpDetailsStartDate",
                        Date.class);

                method.invoke(_imtdIfpDetailsRemoteModel, ifpDetailsStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemForm() {
        return _itemForm;
    }

    @Override
    public void setItemForm(String itemForm) {
        _itemForm = itemForm;

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemForm", String.class);

                method.invoke(_imtdIfpDetailsRemoteModel, itemForm);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getTotalVolumeCommitment() {
        return _totalVolumeCommitment;
    }

    @Override
    public void setTotalVolumeCommitment(String totalVolumeCommitment) {
        _totalVolumeCommitment = totalVolumeCommitment;

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalVolumeCommitment",
                        String.class);

                method.invoke(_imtdIfpDetailsRemoteModel, totalVolumeCommitment);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getItemEndDate() {
        return _itemEndDate;
    }

    @Override
    public void setItemEndDate(Date itemEndDate) {
        _itemEndDate = itemEndDate;

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemEndDate", Date.class);

                method.invoke(_imtdIfpDetailsRemoteModel, itemEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getCheckBox() {
        return _checkBox;
    }

    @Override
    public boolean isCheckBox() {
        return _checkBox;
    }

    @Override
    public void setCheckBox(boolean checkBox) {
        _checkBox = checkBox;

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckBox", boolean.class);

                method.invoke(_imtdIfpDetailsRemoteModel, checkBox);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIfpDetailsAttachedStatus() {
        return _ifpDetailsAttachedStatus;
    }

    @Override
    public void setIfpDetailsAttachedStatus(int ifpDetailsAttachedStatus) {
        _ifpDetailsAttachedStatus = ifpDetailsAttachedStatus;

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpDetailsAttachedStatus",
                        int.class);

                method.invoke(_imtdIfpDetailsRemoteModel,
                    ifpDetailsAttachedStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getTotalMarketshareCommitment() {
        return _totalMarketshareCommitment;
    }

    @Override
    public void setTotalMarketshareCommitment(String totalMarketshareCommitment) {
        _totalMarketshareCommitment = totalMarketshareCommitment;

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalMarketshareCommitment",
                        String.class);

                method.invoke(_imtdIfpDetailsRemoteModel,
                    totalMarketshareCommitment);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getIfpDetailsAttachedDate() {
        return _ifpDetailsAttachedDate;
    }

    @Override
    public void setIfpDetailsAttachedDate(Date ifpDetailsAttachedDate) {
        _ifpDetailsAttachedDate = ifpDetailsAttachedDate;

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpDetailsAttachedDate",
                        Date.class);

                method.invoke(_imtdIfpDetailsRemoteModel, ifpDetailsAttachedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getImtdIfpDetailsSid() {
        return _imtdIfpDetailsSid;
    }

    @Override
    public void setImtdIfpDetailsSid(int imtdIfpDetailsSid) {
        _imtdIfpDetailsSid = imtdIfpDetailsSid;

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setImtdIfpDetailsSid",
                        int.class);

                method.invoke(_imtdIfpDetailsRemoteModel, imtdIfpDetailsSid);
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

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", String.class);

                method.invoke(_imtdIfpDetailsRemoteModel, sessionId);
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

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemName", String.class);

                method.invoke(_imtdIfpDetailsRemoteModel, itemName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemPrimaryUom() {
        return _itemPrimaryUom;
    }

    @Override
    public void setItemPrimaryUom(String itemPrimaryUom) {
        _itemPrimaryUom = itemPrimaryUom;

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemPrimaryUom",
                        String.class);

                method.invoke(_imtdIfpDetailsRemoteModel, itemPrimaryUom);
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

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setOperation", String.class);

                method.invoke(_imtdIfpDetailsRemoteModel, operation);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemBrand() {
        return _itemBrand;
    }

    @Override
    public void setItemBrand(String itemBrand) {
        _itemBrand = itemBrand;

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemBrand", String.class);

                method.invoke(_imtdIfpDetailsRemoteModel, itemBrand);
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

        if (_imtdIfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdIfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpModelSid", int.class);

                method.invoke(_imtdIfpDetailsRemoteModel, cfpModelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getImtdIfpDetailsRemoteModel() {
        return _imtdIfpDetailsRemoteModel;
    }

    public void setImtdIfpDetailsRemoteModel(
        BaseModel<?> imtdIfpDetailsRemoteModel) {
        _imtdIfpDetailsRemoteModel = imtdIfpDetailsRemoteModel;
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

        Class<?> remoteModelClass = _imtdIfpDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_imtdIfpDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ImtdIfpDetailsLocalServiceUtil.addImtdIfpDetails(this);
        } else {
            ImtdIfpDetailsLocalServiceUtil.updateImtdIfpDetails(this);
        }
    }

    @Override
    public ImtdIfpDetails toEscapedModel() {
        return (ImtdIfpDetails) ProxyUtil.newProxyInstance(ImtdIfpDetails.class.getClassLoader(),
            new Class[] { ImtdIfpDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ImtdIfpDetailsClp clone = new ImtdIfpDetailsClp();

        clone.setItemStatus(getItemStatus());
        clone.setIfpDetailsEndDate(getIfpDetailsEndDate());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setImtdCreateddate(getImtdCreateddate());
        clone.setItemPackageSize(getItemPackageSize());
        clone.setIfpDetailsCreatedDate(getIfpDetailsCreatedDate());
        clone.setTotalDollarCommitment(getTotalDollarCommitment());
        clone.setIfpDetailsCreatedBy(getIfpDetailsCreatedBy());
        clone.setItemId(getItemId());
        clone.setModifiedDate(getModifiedDate());
        clone.setIfpDetailsModifiedBy(getIfpDetailsModifiedBy());
        clone.setIfpDetailsModifiedDate(getIfpDetailsModifiedDate());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setUsersSid(getUsersSid());
        clone.setItemDesc(getItemDesc());
        clone.setItemStartDate(getItemStartDate());
        clone.setItemStrength(getItemStrength());
        clone.setContractMasterSid(getContractMasterSid());
        clone.setModifiedBy(getModifiedBy());
        clone.setCommitmentPeriod(getCommitmentPeriod());
        clone.setItemNo(getItemNo());
        clone.setIfpDetailsSid(getIfpDetailsSid());
        clone.setIfpModelSid(getIfpModelSid());
        clone.setItemTherapeuticClass(getItemTherapeuticClass());
        clone.setIfpDetailsStartDate(getIfpDetailsStartDate());
        clone.setItemForm(getItemForm());
        clone.setTotalVolumeCommitment(getTotalVolumeCommitment());
        clone.setItemEndDate(getItemEndDate());
        clone.setCheckBox(getCheckBox());
        clone.setIfpDetailsAttachedStatus(getIfpDetailsAttachedStatus());
        clone.setTotalMarketshareCommitment(getTotalMarketshareCommitment());
        clone.setIfpDetailsAttachedDate(getIfpDetailsAttachedDate());
        clone.setImtdIfpDetailsSid(getImtdIfpDetailsSid());
        clone.setSessionId(getSessionId());
        clone.setItemName(getItemName());
        clone.setItemPrimaryUom(getItemPrimaryUom());
        clone.setOperation(getOperation());
        clone.setItemBrand(getItemBrand());
        clone.setCfpModelSid(getCfpModelSid());

        return clone;
    }

    @Override
    public int compareTo(ImtdIfpDetails imtdIfpDetails) {
        int primaryKey = imtdIfpDetails.getPrimaryKey();

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

        if (!(obj instanceof ImtdIfpDetailsClp)) {
            return false;
        }

        ImtdIfpDetailsClp imtdIfpDetails = (ImtdIfpDetailsClp) obj;

        int primaryKey = imtdIfpDetails.getPrimaryKey();

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
        StringBundler sb = new StringBundler(81);

        sb.append("{itemStatus=");
        sb.append(getItemStatus());
        sb.append(", ifpDetailsEndDate=");
        sb.append(getIfpDetailsEndDate());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", imtdCreateddate=");
        sb.append(getImtdCreateddate());
        sb.append(", itemPackageSize=");
        sb.append(getItemPackageSize());
        sb.append(", ifpDetailsCreatedDate=");
        sb.append(getIfpDetailsCreatedDate());
        sb.append(", totalDollarCommitment=");
        sb.append(getTotalDollarCommitment());
        sb.append(", ifpDetailsCreatedBy=");
        sb.append(getIfpDetailsCreatedBy());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", ifpDetailsModifiedBy=");
        sb.append(getIfpDetailsModifiedBy());
        sb.append(", ifpDetailsModifiedDate=");
        sb.append(getIfpDetailsModifiedDate());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", usersSid=");
        sb.append(getUsersSid());
        sb.append(", itemDesc=");
        sb.append(getItemDesc());
        sb.append(", itemStartDate=");
        sb.append(getItemStartDate());
        sb.append(", itemStrength=");
        sb.append(getItemStrength());
        sb.append(", contractMasterSid=");
        sb.append(getContractMasterSid());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", commitmentPeriod=");
        sb.append(getCommitmentPeriod());
        sb.append(", itemNo=");
        sb.append(getItemNo());
        sb.append(", ifpDetailsSid=");
        sb.append(getIfpDetailsSid());
        sb.append(", ifpModelSid=");
        sb.append(getIfpModelSid());
        sb.append(", itemTherapeuticClass=");
        sb.append(getItemTherapeuticClass());
        sb.append(", ifpDetailsStartDate=");
        sb.append(getIfpDetailsStartDate());
        sb.append(", itemForm=");
        sb.append(getItemForm());
        sb.append(", totalVolumeCommitment=");
        sb.append(getTotalVolumeCommitment());
        sb.append(", itemEndDate=");
        sb.append(getItemEndDate());
        sb.append(", checkBox=");
        sb.append(getCheckBox());
        sb.append(", ifpDetailsAttachedStatus=");
        sb.append(getIfpDetailsAttachedStatus());
        sb.append(", totalMarketshareCommitment=");
        sb.append(getTotalMarketshareCommitment());
        sb.append(", ifpDetailsAttachedDate=");
        sb.append(getIfpDetailsAttachedDate());
        sb.append(", imtdIfpDetailsSid=");
        sb.append(getImtdIfpDetailsSid());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append(", itemName=");
        sb.append(getItemName());
        sb.append(", itemPrimaryUom=");
        sb.append(getItemPrimaryUom());
        sb.append(", operation=");
        sb.append(getOperation());
        sb.append(", itemBrand=");
        sb.append(getItemBrand());
        sb.append(", cfpModelSid=");
        sb.append(getCfpModelSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(124);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ImtdIfpDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>itemStatus</column-name><column-value><![CDATA[");
        sb.append(getItemStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpDetailsEndDate</column-name><column-value><![CDATA[");
        sb.append(getIfpDetailsEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>imtdCreateddate</column-name><column-value><![CDATA[");
        sb.append(getImtdCreateddate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemPackageSize</column-name><column-value><![CDATA[");
        sb.append(getItemPackageSize());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpDetailsCreatedDate</column-name><column-value><![CDATA[");
        sb.append(getIfpDetailsCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>totalDollarCommitment</column-name><column-value><![CDATA[");
        sb.append(getTotalDollarCommitment());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpDetailsCreatedBy</column-name><column-value><![CDATA[");
        sb.append(getIfpDetailsCreatedBy());
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
            "<column><column-name>ifpDetailsModifiedBy</column-name><column-value><![CDATA[");
        sb.append(getIfpDetailsModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpDetailsModifiedDate</column-name><column-value><![CDATA[");
        sb.append(getIfpDetailsModifiedDate());
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
            "<column><column-name>itemDesc</column-name><column-value><![CDATA[");
        sb.append(getItemDesc());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemStartDate</column-name><column-value><![CDATA[");
        sb.append(getItemStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemStrength</column-name><column-value><![CDATA[");
        sb.append(getItemStrength());
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
            "<column><column-name>commitmentPeriod</column-name><column-value><![CDATA[");
        sb.append(getCommitmentPeriod());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemNo</column-name><column-value><![CDATA[");
        sb.append(getItemNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getIfpDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpModelSid</column-name><column-value><![CDATA[");
        sb.append(getIfpModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemTherapeuticClass</column-name><column-value><![CDATA[");
        sb.append(getItemTherapeuticClass());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpDetailsStartDate</column-name><column-value><![CDATA[");
        sb.append(getIfpDetailsStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemForm</column-name><column-value><![CDATA[");
        sb.append(getItemForm());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>totalVolumeCommitment</column-name><column-value><![CDATA[");
        sb.append(getTotalVolumeCommitment());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemEndDate</column-name><column-value><![CDATA[");
        sb.append(getItemEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkBox</column-name><column-value><![CDATA[");
        sb.append(getCheckBox());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpDetailsAttachedStatus</column-name><column-value><![CDATA[");
        sb.append(getIfpDetailsAttachedStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>totalMarketshareCommitment</column-name><column-value><![CDATA[");
        sb.append(getTotalMarketshareCommitment());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpDetailsAttachedDate</column-name><column-value><![CDATA[");
        sb.append(getIfpDetailsAttachedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>imtdIfpDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getImtdIfpDetailsSid());
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
            "<column><column-name>itemPrimaryUom</column-name><column-value><![CDATA[");
        sb.append(getItemPrimaryUom());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>operation</column-name><column-value><![CDATA[");
        sb.append(getOperation());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemBrand</column-name><column-value><![CDATA[");
        sb.append(getItemBrand());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpModelSid</column-name><column-value><![CDATA[");
        sb.append(getCfpModelSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
