package com.stpl.app.model.impl;

import com.stpl.app.model.IvldLotMaster;
import com.stpl.app.model.IvldLotMasterModel;

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
 * The base model implementation for the IvldLotMaster service. Represents a row in the &quot;IVLD_LOT_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.stpl.app.model.IvldLotMasterModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link IvldLotMasterImpl}.
 * </p>
 *
 * @author
 * @see IvldLotMasterImpl
 * @see com.stpl.app.model.IvldLotMaster
 * @see com.stpl.app.model.IvldLotMasterModel
 * @generated
 */
public class IvldLotMasterModelImpl extends BaseModelImpl<IvldLotMaster>
    implements IvldLotMasterModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a ivld lot master model instance should use the {@link com.stpl.app.model.IvldLotMaster} interface instead.
     */
    public static final String TABLE_NAME = "IVLD_LOT_MASTER";
    public static final Object[][] TABLE_COLUMNS = {
            { "REASON_FOR_FAILURE", Types.VARCHAR },
            { "ITEM_ID", Types.VARCHAR },
            { "IVLD_LOT_MASTER_SID", Types.INTEGER },
            { "MODIFIED_DATE", Types.TIMESTAMP },
            { "CREATED_BY", Types.VARCHAR },
            { "CREATED_DATE", Types.TIMESTAMP },
            { "SOURCE", Types.VARCHAR },
            { "LAST_LOT_SOLD_DATE", Types.VARCHAR },
            { "LOT_EXPIRATION_DATE", Types.VARCHAR },
            { "BATCH_ID", Types.VARCHAR },
            { "ADD_CHG_DEL_INDICATOR", Types.VARCHAR },
            { "ERROR_FIELD", Types.VARCHAR },
            { "ERROR_CODE", Types.VARCHAR },
            { "INTF_INSERTED_DATE", Types.TIMESTAMP },
            { "MODIFIED_BY", Types.VARCHAR },
            { "LOT_NO", Types.VARCHAR },
            { "REPROCESSED_FLAG", Types.VARCHAR },
            { "LOT_MASTER_INTFID", Types.VARCHAR },
            { "CHECK_RECORD", Types.BOOLEAN }
        };
    public static final String TABLE_SQL_CREATE = "create table IVLD_LOT_MASTER (REASON_FOR_FAILURE VARCHAR(75) null,ITEM_ID VARCHAR(75) null,IVLD_LOT_MASTER_SID INTEGER not null primary key IDENTITY,MODIFIED_DATE DATE null,CREATED_BY VARCHAR(75) null,CREATED_DATE DATE null,SOURCE VARCHAR(75) null,LAST_LOT_SOLD_DATE VARCHAR(75) null,LOT_EXPIRATION_DATE VARCHAR(75) null,BATCH_ID VARCHAR(75) null,ADD_CHG_DEL_INDICATOR VARCHAR(75) null,ERROR_FIELD VARCHAR(75) null,ERROR_CODE VARCHAR(75) null,INTF_INSERTED_DATE DATE null,MODIFIED_BY VARCHAR(75) null,LOT_NO VARCHAR(75) null,REPROCESSED_FLAG VARCHAR(75) null,LOT_MASTER_INTFID VARCHAR(75) null,CHECK_RECORD BOOLEAN)";
    public static final String TABLE_SQL_DROP = "drop table IVLD_LOT_MASTER";
    public static final String ORDER_BY_JPQL = " ORDER BY ivldLotMaster.ivldLotMasterSid ASC";
    public static final String ORDER_BY_SQL = " ORDER BY IVLD_LOT_MASTER.IVLD_LOT_MASTER_SID ASC";
    public static final String DATA_SOURCE = "appDataSource";
    public static final String SESSION_FACTORY = "appSessionFactory";
    public static final String TX_MANAGER = "appTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.entity.cache.enabled.com.stpl.app.model.IvldLotMaster"),
            false);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.finder.cache.enabled.com.stpl.app.model.IvldLotMaster"),
            false);
    public static final boolean COLUMN_BITMASK_ENABLED = false;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.util.service.ServiceProps.get(
                "lock.expiration.time.com.stpl.app.model.IvldLotMaster"));
    private static ClassLoader _classLoader = IvldLotMaster.class.getClassLoader();
    private static Class<?>[] _escapedModelInterfaces = new Class[] {
            IvldLotMaster.class
        };
    private String _reasonForFailure;
    private String _itemId;
    private int _ivldLotMasterSid;
    private Date _modifiedDate;
    private String _createdBy;
    private Date _createdDate;
    private String _source;
    private String _lastLotSoldDate;
    private String _lotExpirationDate;
    private String _batchId;
    private String _addChgDelIndicator;
    private String _errorField;
    private String _errorCode;
    private Date _intfInsertedDate;
    private String _modifiedBy;
    private String _lotNo;
    private String _reprocessedFlag;
    private String _lotMasterIntfid;
    private boolean _checkRecord;
    private IvldLotMaster _escapedModel;

    public IvldLotMasterModelImpl() {
    }

    @Override
    public int getPrimaryKey() {
        return _ivldLotMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIvldLotMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ivldLotMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Class<?> getModelClass() {
        return IvldLotMaster.class;
    }

    @Override
    public String getModelClassName() {
        return IvldLotMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("itemId", getItemId());
        attributes.put("ivldLotMasterSid", getIvldLotMasterSid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("source", getSource());
        attributes.put("lastLotSoldDate", getLastLotSoldDate());
        attributes.put("lotExpirationDate", getLotExpirationDate());
        attributes.put("batchId", getBatchId());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("errorField", getErrorField());
        attributes.put("errorCode", getErrorCode());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("lotNo", getLotNo());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("lotMasterIntfid", getLotMasterIntfid());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        Integer ivldLotMasterSid = (Integer) attributes.get("ivldLotMasterSid");

        if (ivldLotMasterSid != null) {
            setIvldLotMasterSid(ivldLotMasterSid);
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

        String lastLotSoldDate = (String) attributes.get("lastLotSoldDate");

        if (lastLotSoldDate != null) {
            setLastLotSoldDate(lastLotSoldDate);
        }

        String lotExpirationDate = (String) attributes.get("lotExpirationDate");

        if (lotExpirationDate != null) {
            setLotExpirationDate(lotExpirationDate);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        Date intfInsertedDate = (Date) attributes.get("intfInsertedDate");

        if (intfInsertedDate != null) {
            setIntfInsertedDate(intfInsertedDate);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String lotNo = (String) attributes.get("lotNo");

        if (lotNo != null) {
            setLotNo(lotNo);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String lotMasterIntfid = (String) attributes.get("lotMasterIntfid");

        if (lotMasterIntfid != null) {
            setLotMasterIntfid(lotMasterIntfid);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
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
    public String getItemId() {
        if (_itemId == null) {
            return StringPool.BLANK;
        } else {
            return _itemId;
        }
    }

    @Override
    public void setItemId(String itemId) {
        _itemId = itemId;
    }

    @Override
    public int getIvldLotMasterSid() {
        return _ivldLotMasterSid;
    }

    @Override
    public void setIvldLotMasterSid(int ivldLotMasterSid) {
        _ivldLotMasterSid = ivldLotMasterSid;
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
    public String getLastLotSoldDate() {
        if (_lastLotSoldDate == null) {
            return StringPool.BLANK;
        } else {
            return _lastLotSoldDate;
        }
    }

    @Override
    public void setLastLotSoldDate(String lastLotSoldDate) {
        _lastLotSoldDate = lastLotSoldDate;
    }

    @Override
    public String getLotExpirationDate() {
        if (_lotExpirationDate == null) {
            return StringPool.BLANK;
        } else {
            return _lotExpirationDate;
        }
    }

    @Override
    public void setLotExpirationDate(String lotExpirationDate) {
        _lotExpirationDate = lotExpirationDate;
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
    public String getLotNo() {
        if (_lotNo == null) {
            return StringPool.BLANK;
        } else {
            return _lotNo;
        }
    }

    @Override
    public void setLotNo(String lotNo) {
        _lotNo = lotNo;
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
    public String getLotMasterIntfid() {
        if (_lotMasterIntfid == null) {
            return StringPool.BLANK;
        } else {
            return _lotMasterIntfid;
        }
    }

    @Override
    public void setLotMasterIntfid(String lotMasterIntfid) {
        _lotMasterIntfid = lotMasterIntfid;
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
    public IvldLotMaster toEscapedModel() {
        if (_escapedModel == null) {
            _escapedModel = (IvldLotMaster) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelInterfaces, new AutoEscapeBeanHandler(this));
        }

        return _escapedModel;
    }

    @Override
    public Object clone() {
        IvldLotMasterImpl ivldLotMasterImpl = new IvldLotMasterImpl();

        ivldLotMasterImpl.setReasonForFailure(getReasonForFailure());
        ivldLotMasterImpl.setItemId(getItemId());
        ivldLotMasterImpl.setIvldLotMasterSid(getIvldLotMasterSid());
        ivldLotMasterImpl.setModifiedDate(getModifiedDate());
        ivldLotMasterImpl.setCreatedBy(getCreatedBy());
        ivldLotMasterImpl.setCreatedDate(getCreatedDate());
        ivldLotMasterImpl.setSource(getSource());
        ivldLotMasterImpl.setLastLotSoldDate(getLastLotSoldDate());
        ivldLotMasterImpl.setLotExpirationDate(getLotExpirationDate());
        ivldLotMasterImpl.setBatchId(getBatchId());
        ivldLotMasterImpl.setAddChgDelIndicator(getAddChgDelIndicator());
        ivldLotMasterImpl.setErrorField(getErrorField());
        ivldLotMasterImpl.setErrorCode(getErrorCode());
        ivldLotMasterImpl.setIntfInsertedDate(getIntfInsertedDate());
        ivldLotMasterImpl.setModifiedBy(getModifiedBy());
        ivldLotMasterImpl.setLotNo(getLotNo());
        ivldLotMasterImpl.setReprocessedFlag(getReprocessedFlag());
        ivldLotMasterImpl.setLotMasterIntfid(getLotMasterIntfid());
        ivldLotMasterImpl.setCheckRecord(getCheckRecord());

        ivldLotMasterImpl.resetOriginalValues();

        return ivldLotMasterImpl;
    }

    @Override
    public int compareTo(IvldLotMaster ivldLotMaster) {
        int primaryKey = ivldLotMaster.getPrimaryKey();

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

        if (!(obj instanceof IvldLotMaster)) {
            return false;
        }

        IvldLotMaster ivldLotMaster = (IvldLotMaster) obj;

        int primaryKey = ivldLotMaster.getPrimaryKey();

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
    public CacheModel<IvldLotMaster> toCacheModel() {
        IvldLotMasterCacheModel ivldLotMasterCacheModel = new IvldLotMasterCacheModel();

        ivldLotMasterCacheModel.reasonForFailure = getReasonForFailure();

        String reasonForFailure = ivldLotMasterCacheModel.reasonForFailure;

        if ((reasonForFailure != null) && (reasonForFailure.length() == 0)) {
            ivldLotMasterCacheModel.reasonForFailure = null;
        }

        ivldLotMasterCacheModel.itemId = getItemId();

        String itemId = ivldLotMasterCacheModel.itemId;

        if ((itemId != null) && (itemId.length() == 0)) {
            ivldLotMasterCacheModel.itemId = null;
        }

        ivldLotMasterCacheModel.ivldLotMasterSid = getIvldLotMasterSid();

        Date modifiedDate = getModifiedDate();

        if (modifiedDate != null) {
            ivldLotMasterCacheModel.modifiedDate = modifiedDate.getTime();
        } else {
            ivldLotMasterCacheModel.modifiedDate = Long.MIN_VALUE;
        }

        ivldLotMasterCacheModel.createdBy = getCreatedBy();

        String createdBy = ivldLotMasterCacheModel.createdBy;

        if ((createdBy != null) && (createdBy.length() == 0)) {
            ivldLotMasterCacheModel.createdBy = null;
        }

        Date createdDate = getCreatedDate();

        if (createdDate != null) {
            ivldLotMasterCacheModel.createdDate = createdDate.getTime();
        } else {
            ivldLotMasterCacheModel.createdDate = Long.MIN_VALUE;
        }

        ivldLotMasterCacheModel.source = getSource();

        String source = ivldLotMasterCacheModel.source;

        if ((source != null) && (source.length() == 0)) {
            ivldLotMasterCacheModel.source = null;
        }

        ivldLotMasterCacheModel.lastLotSoldDate = getLastLotSoldDate();

        String lastLotSoldDate = ivldLotMasterCacheModel.lastLotSoldDate;

        if ((lastLotSoldDate != null) && (lastLotSoldDate.length() == 0)) {
            ivldLotMasterCacheModel.lastLotSoldDate = null;
        }

        ivldLotMasterCacheModel.lotExpirationDate = getLotExpirationDate();

        String lotExpirationDate = ivldLotMasterCacheModel.lotExpirationDate;

        if ((lotExpirationDate != null) && (lotExpirationDate.length() == 0)) {
            ivldLotMasterCacheModel.lotExpirationDate = null;
        }

        ivldLotMasterCacheModel.batchId = getBatchId();

        String batchId = ivldLotMasterCacheModel.batchId;

        if ((batchId != null) && (batchId.length() == 0)) {
            ivldLotMasterCacheModel.batchId = null;
        }

        ivldLotMasterCacheModel.addChgDelIndicator = getAddChgDelIndicator();

        String addChgDelIndicator = ivldLotMasterCacheModel.addChgDelIndicator;

        if ((addChgDelIndicator != null) && (addChgDelIndicator.length() == 0)) {
            ivldLotMasterCacheModel.addChgDelIndicator = null;
        }

        ivldLotMasterCacheModel.errorField = getErrorField();

        String errorField = ivldLotMasterCacheModel.errorField;

        if ((errorField != null) && (errorField.length() == 0)) {
            ivldLotMasterCacheModel.errorField = null;
        }

        ivldLotMasterCacheModel.errorCode = getErrorCode();

        String errorCode = ivldLotMasterCacheModel.errorCode;

        if ((errorCode != null) && (errorCode.length() == 0)) {
            ivldLotMasterCacheModel.errorCode = null;
        }

        Date intfInsertedDate = getIntfInsertedDate();

        if (intfInsertedDate != null) {
            ivldLotMasterCacheModel.intfInsertedDate = intfInsertedDate.getTime();
        } else {
            ivldLotMasterCacheModel.intfInsertedDate = Long.MIN_VALUE;
        }

        ivldLotMasterCacheModel.modifiedBy = getModifiedBy();

        String modifiedBy = ivldLotMasterCacheModel.modifiedBy;

        if ((modifiedBy != null) && (modifiedBy.length() == 0)) {
            ivldLotMasterCacheModel.modifiedBy = null;
        }

        ivldLotMasterCacheModel.lotNo = getLotNo();

        String lotNo = ivldLotMasterCacheModel.lotNo;

        if ((lotNo != null) && (lotNo.length() == 0)) {
            ivldLotMasterCacheModel.lotNo = null;
        }

        ivldLotMasterCacheModel.reprocessedFlag = getReprocessedFlag();

        String reprocessedFlag = ivldLotMasterCacheModel.reprocessedFlag;

        if ((reprocessedFlag != null) && (reprocessedFlag.length() == 0)) {
            ivldLotMasterCacheModel.reprocessedFlag = null;
        }

        ivldLotMasterCacheModel.lotMasterIntfid = getLotMasterIntfid();

        String lotMasterIntfid = ivldLotMasterCacheModel.lotMasterIntfid;

        if ((lotMasterIntfid != null) && (lotMasterIntfid.length() == 0)) {
            ivldLotMasterCacheModel.lotMasterIntfid = null;
        }

        ivldLotMasterCacheModel.checkRecord = getCheckRecord();

        return ivldLotMasterCacheModel;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(39);

        sb.append("{reasonForFailure=");
        sb.append(getReasonForFailure());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", ivldLotMasterSid=");
        sb.append(getIvldLotMasterSid());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", lastLotSoldDate=");
        sb.append(getLastLotSoldDate());
        sb.append(", lotExpirationDate=");
        sb.append(getLotExpirationDate());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", errorField=");
        sb.append(getErrorField());
        sb.append(", errorCode=");
        sb.append(getErrorCode());
        sb.append(", intfInsertedDate=");
        sb.append(getIntfInsertedDate());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", lotNo=");
        sb.append(getLotNo());
        sb.append(", reprocessedFlag=");
        sb.append(getReprocessedFlag());
        sb.append(", lotMasterIntfid=");
        sb.append(getLotMasterIntfid());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(61);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.IvldLotMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>reasonForFailure</column-name><column-value><![CDATA[");
        sb.append(getReasonForFailure());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemId</column-name><column-value><![CDATA[");
        sb.append(getItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ivldLotMasterSid</column-name><column-value><![CDATA[");
        sb.append(getIvldLotMasterSid());
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
            "<column><column-name>lastLotSoldDate</column-name><column-value><![CDATA[");
        sb.append(getLastLotSoldDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lotExpirationDate</column-name><column-value><![CDATA[");
        sb.append(getLotExpirationDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>addChgDelIndicator</column-name><column-value><![CDATA[");
        sb.append(getAddChgDelIndicator());
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
            "<column><column-name>intfInsertedDate</column-name><column-value><![CDATA[");
        sb.append(getIntfInsertedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lotNo</column-name><column-value><![CDATA[");
        sb.append(getLotNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reprocessedFlag</column-name><column-value><![CDATA[");
        sb.append(getReprocessedFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lotMasterIntfid</column-name><column-value><![CDATA[");
        sb.append(getLotMasterIntfid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
