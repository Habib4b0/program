package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.IvldCompanyTradeClass;
import com.stpl.app.parttwo.model.IvldCompanyTradeClassModel;

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
 * The base model implementation for the IvldCompanyTradeClass service. Represents a row in the &quot;IVLD_COMPANY_TRADE_CLASS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.stpl.app.parttwo.model.IvldCompanyTradeClassModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link IvldCompanyTradeClassImpl}.
 * </p>
 *
 * @author
 * @see IvldCompanyTradeClassImpl
 * @see com.stpl.app.parttwo.model.IvldCompanyTradeClass
 * @see com.stpl.app.parttwo.model.IvldCompanyTradeClassModel
 * @generated
 */
public class IvldCompanyTradeClassModelImpl extends BaseModelImpl<IvldCompanyTradeClass>
    implements IvldCompanyTradeClassModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a ivld company trade class model instance should use the {@link com.stpl.app.parttwo.model.IvldCompanyTradeClass} interface instead.
     */
    public static final String TABLE_NAME = "IVLD_COMPANY_TRADE_CLASS";
    public static final Object[][] TABLE_COLUMNS = {
            { "IVLD_COMPANY_TRADE_CLASS_SID", Types.INTEGER },
            { "PRIOR_TRADE_CLASS", Types.VARCHAR },
            { "REASON_FOR_FAILURE", Types.VARCHAR },
            { "COMPANY_ID", Types.VARCHAR },
            { "LAST_UPDATED_DATE", Types.VARCHAR },
            { "PRIOR_TRADE_CLASS_START_DATE", Types.VARCHAR },
            { "MODIFIED_DATE", Types.TIMESTAMP },
            { "TRADE_CLASS_END_DATE", Types.VARCHAR },
            { "TRADE_CLASS_INTFID", Types.VARCHAR },
            { "TRADE_CLASS_START_DATE", Types.VARCHAR },
            { "SOURCE", Types.VARCHAR },
            { "CREATED_BY", Types.VARCHAR },
            { "CREATED_DATE", Types.TIMESTAMP },
            { "ADD_CHG_DEL_INDICATOR", Types.VARCHAR },
            { "BATCH_ID", Types.VARCHAR },
            { "ERROR_FIELD", Types.VARCHAR },
            { "ERROR_CODE", Types.VARCHAR },
            { "TRADE_CLASS", Types.VARCHAR },
            { "INTF_INSERTED_DATE", Types.TIMESTAMP },
            { "MODIFIED_BY", Types.VARCHAR },
            { "REPROCESSED_FLAG", Types.VARCHAR },
            { "CHECK_RECORD", Types.BOOLEAN }
        };
    public static final String TABLE_SQL_CREATE = "create table IVLD_COMPANY_TRADE_CLASS (IVLD_COMPANY_TRADE_CLASS_SID INTEGER not null primary key,PRIOR_TRADE_CLASS VARCHAR(75) null,REASON_FOR_FAILURE VARCHAR(75) null,COMPANY_ID VARCHAR(75) null,LAST_UPDATED_DATE VARCHAR(75) null,PRIOR_TRADE_CLASS_START_DATE VARCHAR(75) null,MODIFIED_DATE DATE null,TRADE_CLASS_END_DATE VARCHAR(75) null,TRADE_CLASS_INTFID VARCHAR(75) null,TRADE_CLASS_START_DATE VARCHAR(75) null,SOURCE VARCHAR(75) null,CREATED_BY VARCHAR(75) null,CREATED_DATE DATE null,ADD_CHG_DEL_INDICATOR VARCHAR(75) null,BATCH_ID VARCHAR(75) null,ERROR_FIELD VARCHAR(75) null,ERROR_CODE VARCHAR(75) null,TRADE_CLASS VARCHAR(75) null,INTF_INSERTED_DATE DATE null,MODIFIED_BY VARCHAR(75) null,REPROCESSED_FLAG VARCHAR(75) null,CHECK_RECORD BOOLEAN)";
    public static final String TABLE_SQL_DROP = "drop table IVLD_COMPANY_TRADE_CLASS";
    public static final String ORDER_BY_JPQL = " ORDER BY ivldCompanyTradeClass.ivldCompanyTradeClassSid ASC";
    public static final String ORDER_BY_SQL = " ORDER BY IVLD_COMPANY_TRADE_CLASS.IVLD_COMPANY_TRADE_CLASS_SID ASC";
    public static final String DATA_SOURCE = "appDataSource";
    public static final String SESSION_FACTORY = "appSessionFactory";
    public static final String TX_MANAGER = "appTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.entity.cache.enabled.com.stpl.app.parttwo.model.IvldCompanyTradeClass"),
            false);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.finder.cache.enabled.com.stpl.app.parttwo.model.IvldCompanyTradeClass"),
            false);
    public static final boolean COLUMN_BITMASK_ENABLED = false;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.util.service.ServiceProps.get(
                "lock.expiration.time.com.stpl.app.parttwo.model.IvldCompanyTradeClass"));
    private static ClassLoader _classLoader = IvldCompanyTradeClass.class.getClassLoader();
    private static Class<?>[] _escapedModelInterfaces = new Class[] {
            IvldCompanyTradeClass.class
        };
    private int _ivldCompanyTradeClassSid;
    private String _priorTradeClass;
    private String _reasonForFailure;
    private String _companyId;
    private String _lastUpdatedDate;
    private String _priorTradeClassStartDate;
    private Date _modifiedDate;
    private String _tradeClassEndDate;
    private String _tradeClassIntfid;
    private String _tradeClassStartDate;
    private String _source;
    private String _createdBy;
    private Date _createdDate;
    private String _addChgDelIndicator;
    private String _batchId;
    private String _errorField;
    private String _errorCode;
    private String _tradeClass;
    private Date _intfInsertedDate;
    private String _modifiedBy;
    private String _reprocessedFlag;
    private boolean _checkRecord;
    private IvldCompanyTradeClass _escapedModel;

    public IvldCompanyTradeClassModelImpl() {
    }

    @Override
    public int getPrimaryKey() {
        return _ivldCompanyTradeClassSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIvldCompanyTradeClassSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ivldCompanyTradeClassSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Class<?> getModelClass() {
        return IvldCompanyTradeClass.class;
    }

    @Override
    public String getModelClassName() {
        return IvldCompanyTradeClass.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("ivldCompanyTradeClassSid", getIvldCompanyTradeClassSid());
        attributes.put("priorTradeClass", getPriorTradeClass());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("companyId", getCompanyId());
        attributes.put("lastUpdatedDate", getLastUpdatedDate());
        attributes.put("priorTradeClassStartDate", getPriorTradeClassStartDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("tradeClassEndDate", getTradeClassEndDate());
        attributes.put("tradeClassIntfid", getTradeClassIntfid());
        attributes.put("tradeClassStartDate", getTradeClassStartDate());
        attributes.put("source", getSource());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("batchId", getBatchId());
        attributes.put("errorField", getErrorField());
        attributes.put("errorCode", getErrorCode());
        attributes.put("tradeClass", getTradeClass());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer ivldCompanyTradeClassSid = (Integer) attributes.get(
                "ivldCompanyTradeClassSid");

        if (ivldCompanyTradeClassSid != null) {
            setIvldCompanyTradeClassSid(ivldCompanyTradeClassSid);
        }

        String priorTradeClass = (String) attributes.get("priorTradeClass");

        if (priorTradeClass != null) {
            setPriorTradeClass(priorTradeClass);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        String lastUpdatedDate = (String) attributes.get("lastUpdatedDate");

        if (lastUpdatedDate != null) {
            setLastUpdatedDate(lastUpdatedDate);
        }

        String priorTradeClassStartDate = (String) attributes.get(
                "priorTradeClassStartDate");

        if (priorTradeClassStartDate != null) {
            setPriorTradeClassStartDate(priorTradeClassStartDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String tradeClassEndDate = (String) attributes.get("tradeClassEndDate");

        if (tradeClassEndDate != null) {
            setTradeClassEndDate(tradeClassEndDate);
        }

        String tradeClassIntfid = (String) attributes.get("tradeClassIntfid");

        if (tradeClassIntfid != null) {
            setTradeClassIntfid(tradeClassIntfid);
        }

        String tradeClassStartDate = (String) attributes.get(
                "tradeClassStartDate");

        if (tradeClassStartDate != null) {
            setTradeClassStartDate(tradeClassStartDate);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
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

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        String tradeClass = (String) attributes.get("tradeClass");

        if (tradeClass != null) {
            setTradeClass(tradeClass);
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

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    @Override
    public int getIvldCompanyTradeClassSid() {
        return _ivldCompanyTradeClassSid;
    }

    @Override
    public void setIvldCompanyTradeClassSid(int ivldCompanyTradeClassSid) {
        _ivldCompanyTradeClassSid = ivldCompanyTradeClassSid;
    }

    @Override
    public String getPriorTradeClass() {
        if (_priorTradeClass == null) {
            return StringPool.BLANK;
        } else {
            return _priorTradeClass;
        }
    }

    @Override
    public void setPriorTradeClass(String priorTradeClass) {
        _priorTradeClass = priorTradeClass;
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
    public String getCompanyId() {
        if (_companyId == null) {
            return StringPool.BLANK;
        } else {
            return _companyId;
        }
    }

    @Override
    public void setCompanyId(String companyId) {
        _companyId = companyId;
    }

    @Override
    public String getLastUpdatedDate() {
        if (_lastUpdatedDate == null) {
            return StringPool.BLANK;
        } else {
            return _lastUpdatedDate;
        }
    }

    @Override
    public void setLastUpdatedDate(String lastUpdatedDate) {
        _lastUpdatedDate = lastUpdatedDate;
    }

    @Override
    public String getPriorTradeClassStartDate() {
        if (_priorTradeClassStartDate == null) {
            return StringPool.BLANK;
        } else {
            return _priorTradeClassStartDate;
        }
    }

    @Override
    public void setPriorTradeClassStartDate(String priorTradeClassStartDate) {
        _priorTradeClassStartDate = priorTradeClassStartDate;
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
    public String getTradeClassEndDate() {
        if (_tradeClassEndDate == null) {
            return StringPool.BLANK;
        } else {
            return _tradeClassEndDate;
        }
    }

    @Override
    public void setTradeClassEndDate(String tradeClassEndDate) {
        _tradeClassEndDate = tradeClassEndDate;
    }

    @Override
    public String getTradeClassIntfid() {
        if (_tradeClassIntfid == null) {
            return StringPool.BLANK;
        } else {
            return _tradeClassIntfid;
        }
    }

    @Override
    public void setTradeClassIntfid(String tradeClassIntfid) {
        _tradeClassIntfid = tradeClassIntfid;
    }

    @Override
    public String getTradeClassStartDate() {
        if (_tradeClassStartDate == null) {
            return StringPool.BLANK;
        } else {
            return _tradeClassStartDate;
        }
    }

    @Override
    public void setTradeClassStartDate(String tradeClassStartDate) {
        _tradeClassStartDate = tradeClassStartDate;
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
    public String getTradeClass() {
        if (_tradeClass == null) {
            return StringPool.BLANK;
        } else {
            return _tradeClass;
        }
    }

    @Override
    public void setTradeClass(String tradeClass) {
        _tradeClass = tradeClass;
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
    }

    @Override
    public IvldCompanyTradeClass toEscapedModel() {
        if (_escapedModel == null) {
            _escapedModel = (IvldCompanyTradeClass) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelInterfaces, new AutoEscapeBeanHandler(this));
        }

        return _escapedModel;
    }

    @Override
    public Object clone() {
        IvldCompanyTradeClassImpl ivldCompanyTradeClassImpl = new IvldCompanyTradeClassImpl();

        ivldCompanyTradeClassImpl.setIvldCompanyTradeClassSid(getIvldCompanyTradeClassSid());
        ivldCompanyTradeClassImpl.setPriorTradeClass(getPriorTradeClass());
        ivldCompanyTradeClassImpl.setReasonForFailure(getReasonForFailure());
        ivldCompanyTradeClassImpl.setCompanyId(getCompanyId());
        ivldCompanyTradeClassImpl.setLastUpdatedDate(getLastUpdatedDate());
        ivldCompanyTradeClassImpl.setPriorTradeClassStartDate(getPriorTradeClassStartDate());
        ivldCompanyTradeClassImpl.setModifiedDate(getModifiedDate());
        ivldCompanyTradeClassImpl.setTradeClassEndDate(getTradeClassEndDate());
        ivldCompanyTradeClassImpl.setTradeClassIntfid(getTradeClassIntfid());
        ivldCompanyTradeClassImpl.setTradeClassStartDate(getTradeClassStartDate());
        ivldCompanyTradeClassImpl.setSource(getSource());
        ivldCompanyTradeClassImpl.setCreatedBy(getCreatedBy());
        ivldCompanyTradeClassImpl.setCreatedDate(getCreatedDate());
        ivldCompanyTradeClassImpl.setAddChgDelIndicator(getAddChgDelIndicator());
        ivldCompanyTradeClassImpl.setBatchId(getBatchId());
        ivldCompanyTradeClassImpl.setErrorField(getErrorField());
        ivldCompanyTradeClassImpl.setErrorCode(getErrorCode());
        ivldCompanyTradeClassImpl.setTradeClass(getTradeClass());
        ivldCompanyTradeClassImpl.setIntfInsertedDate(getIntfInsertedDate());
        ivldCompanyTradeClassImpl.setModifiedBy(getModifiedBy());
        ivldCompanyTradeClassImpl.setReprocessedFlag(getReprocessedFlag());
        ivldCompanyTradeClassImpl.setCheckRecord(getCheckRecord());

        ivldCompanyTradeClassImpl.resetOriginalValues();

        return ivldCompanyTradeClassImpl;
    }

    @Override
    public int compareTo(IvldCompanyTradeClass ivldCompanyTradeClass) {
        int primaryKey = ivldCompanyTradeClass.getPrimaryKey();

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

        if (!(obj instanceof IvldCompanyTradeClass)) {
            return false;
        }

        IvldCompanyTradeClass ivldCompanyTradeClass = (IvldCompanyTradeClass) obj;

        int primaryKey = ivldCompanyTradeClass.getPrimaryKey();

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
    public CacheModel<IvldCompanyTradeClass> toCacheModel() {
        IvldCompanyTradeClassCacheModel ivldCompanyTradeClassCacheModel = new IvldCompanyTradeClassCacheModel();

        ivldCompanyTradeClassCacheModel.ivldCompanyTradeClassSid = getIvldCompanyTradeClassSid();

        ivldCompanyTradeClassCacheModel.priorTradeClass = getPriorTradeClass();

        String priorTradeClass = ivldCompanyTradeClassCacheModel.priorTradeClass;

        if ((priorTradeClass != null) && (priorTradeClass.length() == 0)) {
            ivldCompanyTradeClassCacheModel.priorTradeClass = null;
        }

        ivldCompanyTradeClassCacheModel.reasonForFailure = getReasonForFailure();

        String reasonForFailure = ivldCompanyTradeClassCacheModel.reasonForFailure;

        if ((reasonForFailure != null) && (reasonForFailure.length() == 0)) {
            ivldCompanyTradeClassCacheModel.reasonForFailure = null;
        }

        ivldCompanyTradeClassCacheModel.companyId = getCompanyId();

        String companyId = ivldCompanyTradeClassCacheModel.companyId;

        if ((companyId != null) && (companyId.length() == 0)) {
            ivldCompanyTradeClassCacheModel.companyId = null;
        }

        ivldCompanyTradeClassCacheModel.lastUpdatedDate = getLastUpdatedDate();

        String lastUpdatedDate = ivldCompanyTradeClassCacheModel.lastUpdatedDate;

        if ((lastUpdatedDate != null) && (lastUpdatedDate.length() == 0)) {
            ivldCompanyTradeClassCacheModel.lastUpdatedDate = null;
        }

        ivldCompanyTradeClassCacheModel.priorTradeClassStartDate = getPriorTradeClassStartDate();

        String priorTradeClassStartDate = ivldCompanyTradeClassCacheModel.priorTradeClassStartDate;

        if ((priorTradeClassStartDate != null) &&
                (priorTradeClassStartDate.length() == 0)) {
            ivldCompanyTradeClassCacheModel.priorTradeClassStartDate = null;
        }

        Date modifiedDate = getModifiedDate();

        if (modifiedDate != null) {
            ivldCompanyTradeClassCacheModel.modifiedDate = modifiedDate.getTime();
        } else {
            ivldCompanyTradeClassCacheModel.modifiedDate = Long.MIN_VALUE;
        }

        ivldCompanyTradeClassCacheModel.tradeClassEndDate = getTradeClassEndDate();

        String tradeClassEndDate = ivldCompanyTradeClassCacheModel.tradeClassEndDate;

        if ((tradeClassEndDate != null) && (tradeClassEndDate.length() == 0)) {
            ivldCompanyTradeClassCacheModel.tradeClassEndDate = null;
        }

        ivldCompanyTradeClassCacheModel.tradeClassIntfid = getTradeClassIntfid();

        String tradeClassIntfid = ivldCompanyTradeClassCacheModel.tradeClassIntfid;

        if ((tradeClassIntfid != null) && (tradeClassIntfid.length() == 0)) {
            ivldCompanyTradeClassCacheModel.tradeClassIntfid = null;
        }

        ivldCompanyTradeClassCacheModel.tradeClassStartDate = getTradeClassStartDate();

        String tradeClassStartDate = ivldCompanyTradeClassCacheModel.tradeClassStartDate;

        if ((tradeClassStartDate != null) &&
                (tradeClassStartDate.length() == 0)) {
            ivldCompanyTradeClassCacheModel.tradeClassStartDate = null;
        }

        ivldCompanyTradeClassCacheModel.source = getSource();

        String source = ivldCompanyTradeClassCacheModel.source;

        if ((source != null) && (source.length() == 0)) {
            ivldCompanyTradeClassCacheModel.source = null;
        }

        ivldCompanyTradeClassCacheModel.createdBy = getCreatedBy();

        String createdBy = ivldCompanyTradeClassCacheModel.createdBy;

        if ((createdBy != null) && (createdBy.length() == 0)) {
            ivldCompanyTradeClassCacheModel.createdBy = null;
        }

        Date createdDate = getCreatedDate();

        if (createdDate != null) {
            ivldCompanyTradeClassCacheModel.createdDate = createdDate.getTime();
        } else {
            ivldCompanyTradeClassCacheModel.createdDate = Long.MIN_VALUE;
        }

        ivldCompanyTradeClassCacheModel.addChgDelIndicator = getAddChgDelIndicator();

        String addChgDelIndicator = ivldCompanyTradeClassCacheModel.addChgDelIndicator;

        if ((addChgDelIndicator != null) && (addChgDelIndicator.length() == 0)) {
            ivldCompanyTradeClassCacheModel.addChgDelIndicator = null;
        }

        ivldCompanyTradeClassCacheModel.batchId = getBatchId();

        String batchId = ivldCompanyTradeClassCacheModel.batchId;

        if ((batchId != null) && (batchId.length() == 0)) {
            ivldCompanyTradeClassCacheModel.batchId = null;
        }

        ivldCompanyTradeClassCacheModel.errorField = getErrorField();

        String errorField = ivldCompanyTradeClassCacheModel.errorField;

        if ((errorField != null) && (errorField.length() == 0)) {
            ivldCompanyTradeClassCacheModel.errorField = null;
        }

        ivldCompanyTradeClassCacheModel.errorCode = getErrorCode();

        String errorCode = ivldCompanyTradeClassCacheModel.errorCode;

        if ((errorCode != null) && (errorCode.length() == 0)) {
            ivldCompanyTradeClassCacheModel.errorCode = null;
        }

        ivldCompanyTradeClassCacheModel.tradeClass = getTradeClass();

        String tradeClass = ivldCompanyTradeClassCacheModel.tradeClass;

        if ((tradeClass != null) && (tradeClass.length() == 0)) {
            ivldCompanyTradeClassCacheModel.tradeClass = null;
        }

        Date intfInsertedDate = getIntfInsertedDate();

        if (intfInsertedDate != null) {
            ivldCompanyTradeClassCacheModel.intfInsertedDate = intfInsertedDate.getTime();
        } else {
            ivldCompanyTradeClassCacheModel.intfInsertedDate = Long.MIN_VALUE;
        }

        ivldCompanyTradeClassCacheModel.modifiedBy = getModifiedBy();

        String modifiedBy = ivldCompanyTradeClassCacheModel.modifiedBy;

        if ((modifiedBy != null) && (modifiedBy.length() == 0)) {
            ivldCompanyTradeClassCacheModel.modifiedBy = null;
        }

        ivldCompanyTradeClassCacheModel.reprocessedFlag = getReprocessedFlag();

        String reprocessedFlag = ivldCompanyTradeClassCacheModel.reprocessedFlag;

        if ((reprocessedFlag != null) && (reprocessedFlag.length() == 0)) {
            ivldCompanyTradeClassCacheModel.reprocessedFlag = null;
        }

        ivldCompanyTradeClassCacheModel.checkRecord = getCheckRecord();

        return ivldCompanyTradeClassCacheModel;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(45);

        sb.append("{ivldCompanyTradeClassSid=");
        sb.append(getIvldCompanyTradeClassSid());
        sb.append(", priorTradeClass=");
        sb.append(getPriorTradeClass());
        sb.append(", reasonForFailure=");
        sb.append(getReasonForFailure());
        sb.append(", companyId=");
        sb.append(getCompanyId());
        sb.append(", lastUpdatedDate=");
        sb.append(getLastUpdatedDate());
        sb.append(", priorTradeClassStartDate=");
        sb.append(getPriorTradeClassStartDate());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", tradeClassEndDate=");
        sb.append(getTradeClassEndDate());
        sb.append(", tradeClassIntfid=");
        sb.append(getTradeClassIntfid());
        sb.append(", tradeClassStartDate=");
        sb.append(getTradeClassStartDate());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", errorField=");
        sb.append(getErrorField());
        sb.append(", errorCode=");
        sb.append(getErrorCode());
        sb.append(", tradeClass=");
        sb.append(getTradeClass());
        sb.append(", intfInsertedDate=");
        sb.append(getIntfInsertedDate());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", reprocessedFlag=");
        sb.append(getReprocessedFlag());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(70);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.IvldCompanyTradeClass");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>ivldCompanyTradeClassSid</column-name><column-value><![CDATA[");
        sb.append(getIvldCompanyTradeClassSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priorTradeClass</column-name><column-value><![CDATA[");
        sb.append(getPriorTradeClass());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reasonForFailure</column-name><column-value><![CDATA[");
        sb.append(getReasonForFailure());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyId</column-name><column-value><![CDATA[");
        sb.append(getCompanyId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lastUpdatedDate</column-name><column-value><![CDATA[");
        sb.append(getLastUpdatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priorTradeClassStartDate</column-name><column-value><![CDATA[");
        sb.append(getPriorTradeClassStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>tradeClassEndDate</column-name><column-value><![CDATA[");
        sb.append(getTradeClassEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>tradeClassIntfid</column-name><column-value><![CDATA[");
        sb.append(getTradeClassIntfid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>tradeClassStartDate</column-name><column-value><![CDATA[");
        sb.append(getTradeClassStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
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
            "<column><column-name>errorField</column-name><column-value><![CDATA[");
        sb.append(getErrorField());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>errorCode</column-name><column-value><![CDATA[");
        sb.append(getErrorCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>tradeClass</column-name><column-value><![CDATA[");
        sb.append(getTradeClass());
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
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
