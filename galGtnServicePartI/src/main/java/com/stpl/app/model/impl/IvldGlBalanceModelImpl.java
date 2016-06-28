package com.stpl.app.model.impl;

import com.stpl.app.model.IvldGlBalance;
import com.stpl.app.model.IvldGlBalanceModel;

import com.stpl.portal.kernel.bean.AutoEscapeBeanHandler;
import com.stpl.portal.kernel.util.GetterUtil;
import com.stpl.portal.kernel.util.ProxyUtil;
import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the IvldGlBalance service. Represents a row in the &quot;IVLD_GL_BALANCE&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.stpl.app.model.IvldGlBalanceModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link IvldGlBalanceImpl}.
 * </p>
 *
 * @author
 * @see IvldGlBalanceImpl
 * @see com.stpl.app.model.IvldGlBalance
 * @see com.stpl.app.model.IvldGlBalanceModel
 * @generated
 */
public class IvldGlBalanceModelImpl extends BaseModelImpl<IvldGlBalance>
    implements IvldGlBalanceModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a ivld gl balance model instance should use the {@link com.stpl.app.model.IvldGlBalance} interface instead.
     */
    public static final String TABLE_NAME = "IVLD_GL_BALANCE";
    public static final Object[][] TABLE_COLUMNS = {
            { "BALANCE", Types.VARCHAR },
            { "ACCOUNT_NO", Types.VARCHAR },
            { "REASON_FOR_FAILURE", Types.VARCHAR },
            { "ACCOUNT_ID", Types.VARCHAR },
            { "YEAR", Types.VARCHAR },
            { "PERIOD", Types.VARCHAR },
            { "MODIFIED_DATE", Types.TIMESTAMP },
            { "IS_ACTIVE", Types.VARCHAR },
            { "SOURCE", Types.VARCHAR },
            { "UPLOAD_DATE", Types.VARCHAR },
            { "CREATED_BY", Types.VARCHAR },
            { "CREATED_DATE", Types.TIMESTAMP },
            { "ADD_CHG_DEL_INDICATOR", Types.VARCHAR },
            { "BATCH_ID", Types.VARCHAR },
            { "CLOSE_INDICATOR", Types.VARCHAR },
            { "INSERTED_DATE", Types.VARCHAR },
            { "ERROR_FIELD", Types.VARCHAR },
            { "IVLD_GL_BALANCE_SID", Types.INTEGER },
            { "ERROR_CODE", Types.VARCHAR },
            { "GL_BALANCE_INTFID", Types.VARCHAR },
            { "INTF_INSERTED_DATE", Types.TIMESTAMP },
            { "MODIFIED_BY", Types.VARCHAR },
            { "REPROCESSED_FLAG", Types.VARCHAR }
        };
    public static final String TABLE_SQL_CREATE = "create table IVLD_GL_BALANCE (BALANCE VARCHAR(75) null,ACCOUNT_NO VARCHAR(75) null,REASON_FOR_FAILURE VARCHAR(75) null,ACCOUNT_ID VARCHAR(75) null,YEAR VARCHAR(75) null,PERIOD VARCHAR(75) null,MODIFIED_DATE DATE null,IS_ACTIVE VARCHAR(75) null,SOURCE VARCHAR(75) null,UPLOAD_DATE VARCHAR(75) null,CREATED_BY VARCHAR(75) null,CREATED_DATE DATE null,ADD_CHG_DEL_INDICATOR VARCHAR(75) null,BATCH_ID VARCHAR(75) null,CLOSE_INDICATOR VARCHAR(75) null,INSERTED_DATE VARCHAR(75) null,ERROR_FIELD VARCHAR(75) null,IVLD_GL_BALANCE_SID INTEGER not null primary key IDENTITY,ERROR_CODE VARCHAR(75) null,GL_BALANCE_INTFID VARCHAR(75) null,INTF_INSERTED_DATE DATE null,MODIFIED_BY VARCHAR(75) null,REPROCESSED_FLAG VARCHAR(75) null)";
    public static final String TABLE_SQL_DROP = "drop table IVLD_GL_BALANCE";
    public static final String ORDER_BY_JPQL = " ORDER BY ivldGlBalance.ivldGlBalanceSid ASC";
    public static final String ORDER_BY_SQL = " ORDER BY IVLD_GL_BALANCE.IVLD_GL_BALANCE_SID ASC";
    public static final String DATA_SOURCE = "appDataSource";
    public static final String SESSION_FACTORY = "appSessionFactory";
    public static final String TX_MANAGER = "appTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.entity.cache.enabled.com.stpl.app.model.IvldGlBalance"),
            false);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.finder.cache.enabled.com.stpl.app.model.IvldGlBalance"),
            false);
    public static final boolean COLUMN_BITMASK_ENABLED = false;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.util.service.ServiceProps.get(
                "lock.expiration.time.com.stpl.app.model.IvldGlBalance"));
    private static ClassLoader _classLoader = IvldGlBalance.class.getClassLoader();
    private static Class<?>[] _escapedModelInterfaces = new Class[] {
            IvldGlBalance.class
        };
    private String _balance;
    private String _accountNo;
    private String _reasonForFailure;
    private String _accountId;
    private String _year;
    private String _period;
    private Date _modifiedDate;
    private String _isActive;
    private String _source;
    private String _uploadDate;
    private String _createdBy;
    private Date _createdDate;
    private String _addChgDelIndicator;
    private String _batchId;
    private String _closeIndicator;
    private String _insertedDate;
    private String _errorField;
    private int _ivldGlBalanceSid;
    private String _errorCode;
    private String _glBalanceIntfid;
    private Date _intfInsertedDate;
    private String _modifiedBy;
    private String _reprocessedFlag;
    private IvldGlBalance _escapedModel;

    public IvldGlBalanceModelImpl() {
    }

    @Override
    public int getPrimaryKey() {
        return _ivldGlBalanceSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIvldGlBalanceSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ivldGlBalanceSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Class<?> getModelClass() {
        return IvldGlBalance.class;
    }

    @Override
    public String getModelClassName() {
        return IvldGlBalance.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("balance", getBalance());
        attributes.put("accountNo", getAccountNo());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("accountId", getAccountId());
        attributes.put("year", getYear());
        attributes.put("period", getPeriod());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("isActive", getIsActive());
        attributes.put("source", getSource());
        attributes.put("uploadDate", getUploadDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("batchId", getBatchId());
        attributes.put("closeIndicator", getCloseIndicator());
        attributes.put("insertedDate", getInsertedDate());
        attributes.put("errorField", getErrorField());
        attributes.put("ivldGlBalanceSid", getIvldGlBalanceSid());
        attributes.put("errorCode", getErrorCode());
        attributes.put("glBalanceIntfid", getGlBalanceIntfid());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("reprocessedFlag", getReprocessedFlag());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String balance = (String) attributes.get("balance");

        if (balance != null) {
            setBalance(balance);
        }

        String accountNo = (String) attributes.get("accountNo");

        if (accountNo != null) {
            setAccountNo(accountNo);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String accountId = (String) attributes.get("accountId");

        if (accountId != null) {
            setAccountId(accountId);
        }

        String year = (String) attributes.get("year");

        if (year != null) {
            setYear(year);
        }

        String period = (String) attributes.get("period");

        if (period != null) {
            setPeriod(period);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String isActive = (String) attributes.get("isActive");

        if (isActive != null) {
            setIsActive(isActive);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String uploadDate = (String) attributes.get("uploadDate");

        if (uploadDate != null) {
            setUploadDate(uploadDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String closeIndicator = (String) attributes.get("closeIndicator");

        if (closeIndicator != null) {
            setCloseIndicator(closeIndicator);
        }

        String insertedDate = (String) attributes.get("insertedDate");

        if (insertedDate != null) {
            setInsertedDate(insertedDate);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        Integer ivldGlBalanceSid = (Integer) attributes.get("ivldGlBalanceSid");

        if (ivldGlBalanceSid != null) {
            setIvldGlBalanceSid(ivldGlBalanceSid);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        String glBalanceIntfid = (String) attributes.get("glBalanceIntfid");

        if (glBalanceIntfid != null) {
            setGlBalanceIntfid(glBalanceIntfid);
        }

        Date intfInsertedDate = (Date) attributes.get("intfInsertedDate");

        if (intfInsertedDate != null) {
            setIntfInsertedDate(intfInsertedDate);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }
    }

    @Override
    public String getBalance() {
        if (_balance == null) {
            return StringPool.BLANK;
        } else {
            return _balance;
        }
    }

    @Override
    public void setBalance(String balance) {
        _balance = balance;
    }

    @Override
    public String getAccountNo() {
        if (_accountNo == null) {
            return StringPool.BLANK;
        } else {
            return _accountNo;
        }
    }

    @Override
    public void setAccountNo(String accountNo) {
        _accountNo = accountNo;
    }

    @Override
    public String getReasonForFailure() {
        if (_reasonForFailure == null) {
            return StringPool.BLANK;
        } else {
            return _reasonForFailure;
        }
    }

    @Override
    public void setReasonForFailure(String reasonForFailure) {
        _reasonForFailure = reasonForFailure;
    }

    @Override
    public String getAccountId() {
        if (_accountId == null) {
            return StringPool.BLANK;
        } else {
            return _accountId;
        }
    }

    @Override
    public void setAccountId(String accountId) {
        _accountId = accountId;
    }

    @Override
    public String getYear() {
        if (_year == null) {
            return StringPool.BLANK;
        } else {
            return _year;
        }
    }

    @Override
    public void setYear(String year) {
        _year = year;
    }

    @Override
    public String getPeriod() {
        if (_period == null) {
            return StringPool.BLANK;
        } else {
            return _period;
        }
    }

    @Override
    public void setPeriod(String period) {
        _period = period;
    }

    @Override
    public Date getModifiedDate() {
        return _modifiedDate;
    }

    @Override
    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    @Override
    public String getIsActive() {
        if (_isActive == null) {
            return StringPool.BLANK;
        } else {
            return _isActive;
        }
    }

    @Override
    public void setIsActive(String isActive) {
        _isActive = isActive;
    }

    @Override
    public String getSource() {
        if (_source == null) {
            return StringPool.BLANK;
        } else {
            return _source;
        }
    }

    @Override
    public void setSource(String source) {
        _source = source;
    }

    @Override
    public String getUploadDate() {
        if (_uploadDate == null) {
            return StringPool.BLANK;
        } else {
            return _uploadDate;
        }
    }

    @Override
    public void setUploadDate(String uploadDate) {
        _uploadDate = uploadDate;
    }

    @Override
    public String getCreatedBy() {
        if (_createdBy == null) {
            return StringPool.BLANK;
        } else {
            return _createdBy;
        }
    }

    @Override
    public void setCreatedBy(String createdBy) {
        _createdBy = createdBy;
    }

    @Override
    public Date getCreatedDate() {
        return _createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    @Override
    public String getAddChgDelIndicator() {
        if (_addChgDelIndicator == null) {
            return StringPool.BLANK;
        } else {
            return _addChgDelIndicator;
        }
    }

    @Override
    public void setAddChgDelIndicator(String addChgDelIndicator) {
        _addChgDelIndicator = addChgDelIndicator;
    }

    @Override
    public String getBatchId() {
        if (_batchId == null) {
            return StringPool.BLANK;
        } else {
            return _batchId;
        }
    }

    @Override
    public void setBatchId(String batchId) {
        _batchId = batchId;
    }

    @Override
    public String getCloseIndicator() {
        if (_closeIndicator == null) {
            return StringPool.BLANK;
        } else {
            return _closeIndicator;
        }
    }

    @Override
    public void setCloseIndicator(String closeIndicator) {
        _closeIndicator = closeIndicator;
    }

    @Override
    public String getInsertedDate() {
        if (_insertedDate == null) {
            return StringPool.BLANK;
        } else {
            return _insertedDate;
        }
    }

    @Override
    public void setInsertedDate(String insertedDate) {
        _insertedDate = insertedDate;
    }

    @Override
    public String getErrorField() {
        if (_errorField == null) {
            return StringPool.BLANK;
        } else {
            return _errorField;
        }
    }

    @Override
    public void setErrorField(String errorField) {
        _errorField = errorField;
    }

    @Override
    public int getIvldGlBalanceSid() {
        return _ivldGlBalanceSid;
    }

    @Override
    public void setIvldGlBalanceSid(int ivldGlBalanceSid) {
        _ivldGlBalanceSid = ivldGlBalanceSid;
    }

    @Override
    public String getErrorCode() {
        if (_errorCode == null) {
            return StringPool.BLANK;
        } else {
            return _errorCode;
        }
    }

    @Override
    public void setErrorCode(String errorCode) {
        _errorCode = errorCode;
    }

    @Override
    public String getGlBalanceIntfid() {
        if (_glBalanceIntfid == null) {
            return StringPool.BLANK;
        } else {
            return _glBalanceIntfid;
        }
    }

    @Override
    public void setGlBalanceIntfid(String glBalanceIntfid) {
        _glBalanceIntfid = glBalanceIntfid;
    }

    @Override
    public Date getIntfInsertedDate() {
        return _intfInsertedDate;
    }

    @Override
    public void setIntfInsertedDate(Date intfInsertedDate) {
        _intfInsertedDate = intfInsertedDate;
    }

    @Override
    public String getModifiedBy() {
        if (_modifiedBy == null) {
            return StringPool.BLANK;
        } else {
            return _modifiedBy;
        }
    }

    @Override
    public void setModifiedBy(String modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    @Override
    public String getReprocessedFlag() {
        if (_reprocessedFlag == null) {
            return StringPool.BLANK;
        } else {
            return _reprocessedFlag;
        }
    }

    @Override
    public void setReprocessedFlag(String reprocessedFlag) {
        _reprocessedFlag = reprocessedFlag;
    }

    @Override
    public IvldGlBalance toEscapedModel() {
        if (_escapedModel == null) {
            _escapedModel = (IvldGlBalance) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelInterfaces, new AutoEscapeBeanHandler(this));
        }

        return _escapedModel;
    }

    @Override
    public Object clone() {
        IvldGlBalanceImpl ivldGlBalanceImpl = new IvldGlBalanceImpl();

        ivldGlBalanceImpl.setBalance(getBalance());
        ivldGlBalanceImpl.setAccountNo(getAccountNo());
        ivldGlBalanceImpl.setReasonForFailure(getReasonForFailure());
        ivldGlBalanceImpl.setAccountId(getAccountId());
        ivldGlBalanceImpl.setYear(getYear());
        ivldGlBalanceImpl.setPeriod(getPeriod());
        ivldGlBalanceImpl.setModifiedDate(getModifiedDate());
        ivldGlBalanceImpl.setIsActive(getIsActive());
        ivldGlBalanceImpl.setSource(getSource());
        ivldGlBalanceImpl.setUploadDate(getUploadDate());
        ivldGlBalanceImpl.setCreatedBy(getCreatedBy());
        ivldGlBalanceImpl.setCreatedDate(getCreatedDate());
        ivldGlBalanceImpl.setAddChgDelIndicator(getAddChgDelIndicator());
        ivldGlBalanceImpl.setBatchId(getBatchId());
        ivldGlBalanceImpl.setCloseIndicator(getCloseIndicator());
        ivldGlBalanceImpl.setInsertedDate(getInsertedDate());
        ivldGlBalanceImpl.setErrorField(getErrorField());
        ivldGlBalanceImpl.setIvldGlBalanceSid(getIvldGlBalanceSid());
        ivldGlBalanceImpl.setErrorCode(getErrorCode());
        ivldGlBalanceImpl.setGlBalanceIntfid(getGlBalanceIntfid());
        ivldGlBalanceImpl.setIntfInsertedDate(getIntfInsertedDate());
        ivldGlBalanceImpl.setModifiedBy(getModifiedBy());
        ivldGlBalanceImpl.setReprocessedFlag(getReprocessedFlag());

        ivldGlBalanceImpl.resetOriginalValues();

        return ivldGlBalanceImpl;
    }

    @Override
    public int compareTo(IvldGlBalance ivldGlBalance) {
        int primaryKey = ivldGlBalance.getPrimaryKey();

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

        if (!(obj instanceof IvldGlBalance)) {
            return false;
        }

        IvldGlBalance ivldGlBalance = (IvldGlBalance) obj;

        int primaryKey = ivldGlBalance.getPrimaryKey();

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
    public void resetOriginalValues() {
    }

    @Override
    public CacheModel<IvldGlBalance> toCacheModel() {
        IvldGlBalanceCacheModel ivldGlBalanceCacheModel = new IvldGlBalanceCacheModel();

        ivldGlBalanceCacheModel.balance = getBalance();

        String balance = ivldGlBalanceCacheModel.balance;

        if ((balance != null) && (balance.length() == 0)) {
            ivldGlBalanceCacheModel.balance = null;
        }

        ivldGlBalanceCacheModel.accountNo = getAccountNo();

        String accountNo = ivldGlBalanceCacheModel.accountNo;

        if ((accountNo != null) && (accountNo.length() == 0)) {
            ivldGlBalanceCacheModel.accountNo = null;
        }

        ivldGlBalanceCacheModel.reasonForFailure = getReasonForFailure();

        String reasonForFailure = ivldGlBalanceCacheModel.reasonForFailure;

        if ((reasonForFailure != null) && (reasonForFailure.length() == 0)) {
            ivldGlBalanceCacheModel.reasonForFailure = null;
        }

        ivldGlBalanceCacheModel.accountId = getAccountId();

        String accountId = ivldGlBalanceCacheModel.accountId;

        if ((accountId != null) && (accountId.length() == 0)) {
            ivldGlBalanceCacheModel.accountId = null;
        }

        ivldGlBalanceCacheModel.year = getYear();

        String year = ivldGlBalanceCacheModel.year;

        if ((year != null) && (year.length() == 0)) {
            ivldGlBalanceCacheModel.year = null;
        }

        ivldGlBalanceCacheModel.period = getPeriod();

        String period = ivldGlBalanceCacheModel.period;

        if ((period != null) && (period.length() == 0)) {
            ivldGlBalanceCacheModel.period = null;
        }

        Date modifiedDate = getModifiedDate();

        if (modifiedDate != null) {
            ivldGlBalanceCacheModel.modifiedDate = modifiedDate.getTime();
        } else {
            ivldGlBalanceCacheModel.modifiedDate = Long.MIN_VALUE;
        }

        ivldGlBalanceCacheModel.isActive = getIsActive();

        String isActive = ivldGlBalanceCacheModel.isActive;

        if ((isActive != null) && (isActive.length() == 0)) {
            ivldGlBalanceCacheModel.isActive = null;
        }

        ivldGlBalanceCacheModel.source = getSource();

        String source = ivldGlBalanceCacheModel.source;

        if ((source != null) && (source.length() == 0)) {
            ivldGlBalanceCacheModel.source = null;
        }

        ivldGlBalanceCacheModel.uploadDate = getUploadDate();

        String uploadDate = ivldGlBalanceCacheModel.uploadDate;

        if ((uploadDate != null) && (uploadDate.length() == 0)) {
            ivldGlBalanceCacheModel.uploadDate = null;
        }

        ivldGlBalanceCacheModel.createdBy = getCreatedBy();

        String createdBy = ivldGlBalanceCacheModel.createdBy;

        if ((createdBy != null) && (createdBy.length() == 0)) {
            ivldGlBalanceCacheModel.createdBy = null;
        }

        Date createdDate = getCreatedDate();

        if (createdDate != null) {
            ivldGlBalanceCacheModel.createdDate = createdDate.getTime();
        } else {
            ivldGlBalanceCacheModel.createdDate = Long.MIN_VALUE;
        }

        ivldGlBalanceCacheModel.addChgDelIndicator = getAddChgDelIndicator();

        String addChgDelIndicator = ivldGlBalanceCacheModel.addChgDelIndicator;

        if ((addChgDelIndicator != null) && (addChgDelIndicator.length() == 0)) {
            ivldGlBalanceCacheModel.addChgDelIndicator = null;
        }

        ivldGlBalanceCacheModel.batchId = getBatchId();

        String batchId = ivldGlBalanceCacheModel.batchId;

        if ((batchId != null) && (batchId.length() == 0)) {
            ivldGlBalanceCacheModel.batchId = null;
        }

        ivldGlBalanceCacheModel.closeIndicator = getCloseIndicator();

        String closeIndicator = ivldGlBalanceCacheModel.closeIndicator;

        if ((closeIndicator != null) && (closeIndicator.length() == 0)) {
            ivldGlBalanceCacheModel.closeIndicator = null;
        }

        ivldGlBalanceCacheModel.insertedDate = getInsertedDate();

        String insertedDate = ivldGlBalanceCacheModel.insertedDate;

        if ((insertedDate != null) && (insertedDate.length() == 0)) {
            ivldGlBalanceCacheModel.insertedDate = null;
        }

        ivldGlBalanceCacheModel.errorField = getErrorField();

        String errorField = ivldGlBalanceCacheModel.errorField;

        if ((errorField != null) && (errorField.length() == 0)) {
            ivldGlBalanceCacheModel.errorField = null;
        }

        ivldGlBalanceCacheModel.ivldGlBalanceSid = getIvldGlBalanceSid();

        ivldGlBalanceCacheModel.errorCode = getErrorCode();

        String errorCode = ivldGlBalanceCacheModel.errorCode;

        if ((errorCode != null) && (errorCode.length() == 0)) {
            ivldGlBalanceCacheModel.errorCode = null;
        }

        ivldGlBalanceCacheModel.glBalanceIntfid = getGlBalanceIntfid();

        String glBalanceIntfid = ivldGlBalanceCacheModel.glBalanceIntfid;

        if ((glBalanceIntfid != null) && (glBalanceIntfid.length() == 0)) {
            ivldGlBalanceCacheModel.glBalanceIntfid = null;
        }

        Date intfInsertedDate = getIntfInsertedDate();

        if (intfInsertedDate != null) {
            ivldGlBalanceCacheModel.intfInsertedDate = intfInsertedDate.getTime();
        } else {
            ivldGlBalanceCacheModel.intfInsertedDate = Long.MIN_VALUE;
        }

        ivldGlBalanceCacheModel.modifiedBy = getModifiedBy();

        String modifiedBy = ivldGlBalanceCacheModel.modifiedBy;

        if ((modifiedBy != null) && (modifiedBy.length() == 0)) {
            ivldGlBalanceCacheModel.modifiedBy = null;
        }

        ivldGlBalanceCacheModel.reprocessedFlag = getReprocessedFlag();

        String reprocessedFlag = ivldGlBalanceCacheModel.reprocessedFlag;

        if ((reprocessedFlag != null) && (reprocessedFlag.length() == 0)) {
            ivldGlBalanceCacheModel.reprocessedFlag = null;
        }

        return ivldGlBalanceCacheModel;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(47);

        sb.append("{balance=");
        sb.append(getBalance());
        sb.append(", accountNo=");
        sb.append(getAccountNo());
        sb.append(", reasonForFailure=");
        sb.append(getReasonForFailure());
        sb.append(", accountId=");
        sb.append(getAccountId());
        sb.append(", year=");
        sb.append(getYear());
        sb.append(", period=");
        sb.append(getPeriod());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", isActive=");
        sb.append(getIsActive());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", uploadDate=");
        sb.append(getUploadDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", closeIndicator=");
        sb.append(getCloseIndicator());
        sb.append(", insertedDate=");
        sb.append(getInsertedDate());
        sb.append(", errorField=");
        sb.append(getErrorField());
        sb.append(", ivldGlBalanceSid=");
        sb.append(getIvldGlBalanceSid());
        sb.append(", errorCode=");
        sb.append(getErrorCode());
        sb.append(", glBalanceIntfid=");
        sb.append(getGlBalanceIntfid());
        sb.append(", intfInsertedDate=");
        sb.append(getIntfInsertedDate());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", reprocessedFlag=");
        sb.append(getReprocessedFlag());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(73);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.IvldGlBalance");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>balance</column-name><column-value><![CDATA[");
        sb.append(getBalance());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accountNo</column-name><column-value><![CDATA[");
        sb.append(getAccountNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reasonForFailure</column-name><column-value><![CDATA[");
        sb.append(getReasonForFailure());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accountId</column-name><column-value><![CDATA[");
        sb.append(getAccountId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>year</column-name><column-value><![CDATA[");
        sb.append(getYear());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>period</column-name><column-value><![CDATA[");
        sb.append(getPeriod());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>isActive</column-name><column-value><![CDATA[");
        sb.append(getIsActive());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>uploadDate</column-name><column-value><![CDATA[");
        sb.append(getUploadDate());
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
            "<column><column-name>addChgDelIndicator</column-name><column-value><![CDATA[");
        sb.append(getAddChgDelIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>closeIndicator</column-name><column-value><![CDATA[");
        sb.append(getCloseIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>insertedDate</column-name><column-value><![CDATA[");
        sb.append(getInsertedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>errorField</column-name><column-value><![CDATA[");
        sb.append(getErrorField());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ivldGlBalanceSid</column-name><column-value><![CDATA[");
        sb.append(getIvldGlBalanceSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>errorCode</column-name><column-value><![CDATA[");
        sb.append(getErrorCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>glBalanceIntfid</column-name><column-value><![CDATA[");
        sb.append(getGlBalanceIntfid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>intfInsertedDate</column-name><column-value><![CDATA[");
        sb.append(getIntfInsertedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reprocessedFlag</column-name><column-value><![CDATA[");
        sb.append(getReprocessedFlag());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
