package com.stpl.app.model;

import com.stpl.app.service.BestPriceMasterLocalServiceUtil;
import com.stpl.app.service.ClpSerializer;

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


public class BestPriceMasterClp extends BaseModelImpl<BestPriceMaster>
    implements BestPriceMaster {
    private double _initialBestPrice;
    private int _createdBy;
    private String _itemNo;
    private int _modifiedBy;
    private String _accountId;
    private Date _createdDate;
    private String _itemId;
    private String _itemDesc;
    private double _sellingPrice;
    private String _contractId;
    private String _contractNo;
    private String _batchId;
    private int _bestPriceMasterSid;
    private Date _modifiedDate;
    private boolean _recordLockStatus;
    private double _beginningWacPackage;
    private double _initialDiscount;
    private String _period;
    private String _source;
    private Date _contractStartDate;
    private Date _contractEndDate;
    private String _inboundStatus;
    private BaseModel<?> _bestPriceMasterRemoteModel;

    public BestPriceMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return BestPriceMaster.class;
    }

    @Override
    public String getModelClassName() {
        return BestPriceMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _bestPriceMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setBestPriceMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _bestPriceMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("initialBestPrice", getInitialBestPrice());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("itemNo", getItemNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("accountId", getAccountId());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("itemId", getItemId());
        attributes.put("itemDesc", getItemDesc());
        attributes.put("sellingPrice", getSellingPrice());
        attributes.put("contractId", getContractId());
        attributes.put("contractNo", getContractNo());
        attributes.put("batchId", getBatchId());
        attributes.put("bestPriceMasterSid", getBestPriceMasterSid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("beginningWacPackage", getBeginningWacPackage());
        attributes.put("initialDiscount", getInitialDiscount());
        attributes.put("period", getPeriod());
        attributes.put("source", getSource());
        attributes.put("contractStartDate", getContractStartDate());
        attributes.put("contractEndDate", getContractEndDate());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double initialBestPrice = (Double) attributes.get("initialBestPrice");

        if (initialBestPrice != null) {
            setInitialBestPrice(initialBestPrice);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String accountId = (String) attributes.get("accountId");

        if (accountId != null) {
            setAccountId(accountId);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String itemDesc = (String) attributes.get("itemDesc");

        if (itemDesc != null) {
            setItemDesc(itemDesc);
        }

        Double sellingPrice = (Double) attributes.get("sellingPrice");

        if (sellingPrice != null) {
            setSellingPrice(sellingPrice);
        }

        String contractId = (String) attributes.get("contractId");

        if (contractId != null) {
            setContractId(contractId);
        }

        String contractNo = (String) attributes.get("contractNo");

        if (contractNo != null) {
            setContractNo(contractNo);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Integer bestPriceMasterSid = (Integer) attributes.get(
                "bestPriceMasterSid");

        if (bestPriceMasterSid != null) {
            setBestPriceMasterSid(bestPriceMasterSid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        Double beginningWacPackage = (Double) attributes.get(
                "beginningWacPackage");

        if (beginningWacPackage != null) {
            setBeginningWacPackage(beginningWacPackage);
        }

        Double initialDiscount = (Double) attributes.get("initialDiscount");

        if (initialDiscount != null) {
            setInitialDiscount(initialDiscount);
        }

        String period = (String) attributes.get("period");

        if (period != null) {
            setPeriod(period);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Date contractStartDate = (Date) attributes.get("contractStartDate");

        if (contractStartDate != null) {
            setContractStartDate(contractStartDate);
        }

        Date contractEndDate = (Date) attributes.get("contractEndDate");

        if (contractEndDate != null) {
            setContractEndDate(contractEndDate);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }
    }

    @Override
    public double getInitialBestPrice() {
        return _initialBestPrice;
    }

    @Override
    public void setInitialBestPrice(double initialBestPrice) {
        _initialBestPrice = initialBestPrice;

        if (_bestPriceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _bestPriceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInitialBestPrice",
                        double.class);

                method.invoke(_bestPriceMasterRemoteModel, initialBestPrice);
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

        if (_bestPriceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _bestPriceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_bestPriceMasterRemoteModel, createdBy);
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

        if (_bestPriceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _bestPriceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemNo", String.class);

                method.invoke(_bestPriceMasterRemoteModel, itemNo);
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

        if (_bestPriceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _bestPriceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_bestPriceMasterRemoteModel, modifiedBy);
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

        if (_bestPriceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _bestPriceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountId", String.class);

                method.invoke(_bestPriceMasterRemoteModel, accountId);
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

        if (_bestPriceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _bestPriceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_bestPriceMasterRemoteModel, createdDate);
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

        if (_bestPriceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _bestPriceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_bestPriceMasterRemoteModel, itemId);
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

        if (_bestPriceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _bestPriceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemDesc", String.class);

                method.invoke(_bestPriceMasterRemoteModel, itemDesc);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getSellingPrice() {
        return _sellingPrice;
    }

    @Override
    public void setSellingPrice(double sellingPrice) {
        _sellingPrice = sellingPrice;

        if (_bestPriceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _bestPriceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSellingPrice", double.class);

                method.invoke(_bestPriceMasterRemoteModel, sellingPrice);
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

        if (_bestPriceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _bestPriceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractId", String.class);

                method.invoke(_bestPriceMasterRemoteModel, contractId);
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

        if (_bestPriceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _bestPriceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractNo", String.class);

                method.invoke(_bestPriceMasterRemoteModel, contractNo);
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

        if (_bestPriceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _bestPriceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_bestPriceMasterRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getBestPriceMasterSid() {
        return _bestPriceMasterSid;
    }

    @Override
    public void setBestPriceMasterSid(int bestPriceMasterSid) {
        _bestPriceMasterSid = bestPriceMasterSid;

        if (_bestPriceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _bestPriceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBestPriceMasterSid",
                        int.class);

                method.invoke(_bestPriceMasterRemoteModel, bestPriceMasterSid);
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

        if (_bestPriceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _bestPriceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_bestPriceMasterRemoteModel, modifiedDate);
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

        if (_bestPriceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _bestPriceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_bestPriceMasterRemoteModel, recordLockStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getBeginningWacPackage() {
        return _beginningWacPackage;
    }

    @Override
    public void setBeginningWacPackage(double beginningWacPackage) {
        _beginningWacPackage = beginningWacPackage;

        if (_bestPriceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _bestPriceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBeginningWacPackage",
                        double.class);

                method.invoke(_bestPriceMasterRemoteModel, beginningWacPackage);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getInitialDiscount() {
        return _initialDiscount;
    }

    @Override
    public void setInitialDiscount(double initialDiscount) {
        _initialDiscount = initialDiscount;

        if (_bestPriceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _bestPriceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInitialDiscount",
                        double.class);

                method.invoke(_bestPriceMasterRemoteModel, initialDiscount);
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

        if (_bestPriceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _bestPriceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPeriod", String.class);

                method.invoke(_bestPriceMasterRemoteModel, period);
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

        if (_bestPriceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _bestPriceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_bestPriceMasterRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getContractStartDate() {
        return _contractStartDate;
    }

    @Override
    public void setContractStartDate(Date contractStartDate) {
        _contractStartDate = contractStartDate;

        if (_bestPriceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _bestPriceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractStartDate",
                        Date.class);

                method.invoke(_bestPriceMasterRemoteModel, contractStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getContractEndDate() {
        return _contractEndDate;
    }

    @Override
    public void setContractEndDate(Date contractEndDate) {
        _contractEndDate = contractEndDate;

        if (_bestPriceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _bestPriceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractEndDate", Date.class);

                method.invoke(_bestPriceMasterRemoteModel, contractEndDate);
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

        if (_bestPriceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _bestPriceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_bestPriceMasterRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getBestPriceMasterRemoteModel() {
        return _bestPriceMasterRemoteModel;
    }

    public void setBestPriceMasterRemoteModel(
        BaseModel<?> bestPriceMasterRemoteModel) {
        _bestPriceMasterRemoteModel = bestPriceMasterRemoteModel;
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

        Class<?> remoteModelClass = _bestPriceMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_bestPriceMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            BestPriceMasterLocalServiceUtil.addBestPriceMaster(this);
        } else {
            BestPriceMasterLocalServiceUtil.updateBestPriceMaster(this);
        }
    }

    @Override
    public BestPriceMaster toEscapedModel() {
        return (BestPriceMaster) ProxyUtil.newProxyInstance(BestPriceMaster.class.getClassLoader(),
            new Class[] { BestPriceMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        BestPriceMasterClp clone = new BestPriceMasterClp();

        clone.setInitialBestPrice(getInitialBestPrice());
        clone.setCreatedBy(getCreatedBy());
        clone.setItemNo(getItemNo());
        clone.setModifiedBy(getModifiedBy());
        clone.setAccountId(getAccountId());
        clone.setCreatedDate(getCreatedDate());
        clone.setItemId(getItemId());
        clone.setItemDesc(getItemDesc());
        clone.setSellingPrice(getSellingPrice());
        clone.setContractId(getContractId());
        clone.setContractNo(getContractNo());
        clone.setBatchId(getBatchId());
        clone.setBestPriceMasterSid(getBestPriceMasterSid());
        clone.setModifiedDate(getModifiedDate());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setBeginningWacPackage(getBeginningWacPackage());
        clone.setInitialDiscount(getInitialDiscount());
        clone.setPeriod(getPeriod());
        clone.setSource(getSource());
        clone.setContractStartDate(getContractStartDate());
        clone.setContractEndDate(getContractEndDate());
        clone.setInboundStatus(getInboundStatus());

        return clone;
    }

    @Override
    public int compareTo(BestPriceMaster bestPriceMaster) {
        int primaryKey = bestPriceMaster.getPrimaryKey();

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

        if (!(obj instanceof BestPriceMasterClp)) {
            return false;
        }

        BestPriceMasterClp bestPriceMaster = (BestPriceMasterClp) obj;

        int primaryKey = bestPriceMaster.getPrimaryKey();

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

        sb.append("{initialBestPrice=");
        sb.append(getInitialBestPrice());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", itemNo=");
        sb.append(getItemNo());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", accountId=");
        sb.append(getAccountId());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", itemDesc=");
        sb.append(getItemDesc());
        sb.append(", sellingPrice=");
        sb.append(getSellingPrice());
        sb.append(", contractId=");
        sb.append(getContractId());
        sb.append(", contractNo=");
        sb.append(getContractNo());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", bestPriceMasterSid=");
        sb.append(getBestPriceMasterSid());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", beginningWacPackage=");
        sb.append(getBeginningWacPackage());
        sb.append(", initialDiscount=");
        sb.append(getInitialDiscount());
        sb.append(", period=");
        sb.append(getPeriod());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", contractStartDate=");
        sb.append(getContractStartDate());
        sb.append(", contractEndDate=");
        sb.append(getContractEndDate());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(70);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.BestPriceMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>initialBestPrice</column-name><column-value><![CDATA[");
        sb.append(getInitialBestPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemNo</column-name><column-value><![CDATA[");
        sb.append(getItemNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accountId</column-name><column-value><![CDATA[");
        sb.append(getAccountId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemId</column-name><column-value><![CDATA[");
        sb.append(getItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemDesc</column-name><column-value><![CDATA[");
        sb.append(getItemDesc());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sellingPrice</column-name><column-value><![CDATA[");
        sb.append(getSellingPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractId</column-name><column-value><![CDATA[");
        sb.append(getContractId());
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
            "<column><column-name>bestPriceMasterSid</column-name><column-value><![CDATA[");
        sb.append(getBestPriceMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>beginningWacPackage</column-name><column-value><![CDATA[");
        sb.append(getBeginningWacPackage());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>initialDiscount</column-name><column-value><![CDATA[");
        sb.append(getInitialDiscount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>period</column-name><column-value><![CDATA[");
        sb.append(getPeriod());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractStartDate</column-name><column-value><![CDATA[");
        sb.append(getContractStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractEndDate</column-name><column-value><![CDATA[");
        sb.append(getContractEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
