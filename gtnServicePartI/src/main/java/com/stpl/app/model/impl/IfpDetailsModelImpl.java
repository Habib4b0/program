package com.stpl.app.model.impl;

import com.stpl.app.model.IfpDetails;
import com.stpl.app.model.IfpDetailsModel;

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
 * The base model implementation for the IfpDetails service. Represents a row in the &quot;ifp_details&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.stpl.app.model.IfpDetailsModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link IfpDetailsImpl}.
 * </p>
 *
 * @author
 * @see IfpDetailsImpl
 * @see com.stpl.app.model.IfpDetails
 * @see com.stpl.app.model.IfpDetailsModel
 * @generated
 */
public class IfpDetailsModelImpl extends BaseModelImpl<IfpDetails>
    implements IfpDetailsModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a ifp details model instance should use the {@link com.stpl.app.model.IfpDetails} interface instead.
     */
    public static final String TABLE_NAME = "ifp_details";
    public static final Object[][] TABLE_COLUMNS = {
            { "ITEM_MASTER_SID", Types.INTEGER },
            { "END_DATE", Types.TIMESTAMP },
            { "MODIFIED_DATE", Types.TIMESTAMP },
            { "RECORD_LOCK_STATUS", Types.BOOLEAN },
            { "START_DATE", Types.TIMESTAMP },
            { "CREATED_DATE", Types.TIMESTAMP },
            { "SOURCE", Types.VARCHAR },
            { "CREATED_BY", Types.INTEGER },
            { "ITEM_IFP_ATTACHED_DATE", Types.TIMESTAMP },
            { "BATCH_ID", Types.VARCHAR },
            { "MODIFIED_BY", Types.INTEGER },
            { "INBOUND_STATUS", Types.VARCHAR },
            { "IFP_DETAILS_SID", Types.INTEGER },
            { "IFP_MODEL_SID", Types.INTEGER },
            { "ITEM_IFP_ATTACHED_STATUS", Types.INTEGER }
        };
    public static final String TABLE_SQL_CREATE = "create table ifp_details (ITEM_MASTER_SID INTEGER,END_DATE DATE null,MODIFIED_DATE DATE null,RECORD_LOCK_STATUS BOOLEAN,START_DATE DATE null,CREATED_DATE DATE null,SOURCE VARCHAR(75) null,CREATED_BY INTEGER,ITEM_IFP_ATTACHED_DATE DATE null,BATCH_ID VARCHAR(75) null,MODIFIED_BY INTEGER,INBOUND_STATUS VARCHAR(75) null,IFP_DETAILS_SID INTEGER not null primary key IDENTITY,IFP_MODEL_SID INTEGER,ITEM_IFP_ATTACHED_STATUS INTEGER)";
    public static final String TABLE_SQL_DROP = "drop table ifp_details";
    public static final String ORDER_BY_JPQL = " ORDER BY ifpDetails.ifpDetailsSid ASC";
    public static final String ORDER_BY_SQL = " ORDER BY ifp_details.IFP_DETAILS_SID ASC";
    public static final String DATA_SOURCE = "appDataSource";
    public static final String SESSION_FACTORY = "appSessionFactory";
    public static final String TX_MANAGER = "appTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.entity.cache.enabled.com.stpl.app.model.IfpDetails"),
            false);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.finder.cache.enabled.com.stpl.app.model.IfpDetails"),
            false);
    public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.column.bitmask.enabled.com.stpl.app.model.IfpDetails"),
            true);
    public static long IFPMODELSID_COLUMN_BITMASK = 1L;
    public static long IFPDETAILSSID_COLUMN_BITMASK = 2L;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.util.service.ServiceProps.get(
                "lock.expiration.time.com.stpl.app.model.IfpDetails"));
    private static ClassLoader _classLoader = IfpDetails.class.getClassLoader();
    private static Class<?>[] _escapedModelInterfaces = new Class[] {
            IfpDetails.class
        };
    private int _itemMasterSid;
    private Date _endDate;
    private Date _modifiedDate;
    private boolean _recordLockStatus;
    private Date _startDate;
    private Date _createdDate;
    private String _source;
    private int _createdBy;
    private Date _itemIfpAttachedDate;
    private String _batchId;
    private int _modifiedBy;
    private String _inboundStatus;
    private int _ifpDetailsSid;
    private int _ifpModelSid;
    private int _originalIfpModelSid;
    private boolean _setOriginalIfpModelSid;
    private int _itemIfpAttachedStatus;
    private long _columnBitmask;
    private IfpDetails _escapedModel;

    public IfpDetailsModelImpl() {
    }

    @Override
    public int getPrimaryKey() {
        return _ifpDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIfpDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ifpDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Class<?> getModelClass() {
        return IfpDetails.class;
    }

    @Override
    public String getModelClassName() {
        return IfpDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("endDate", getEndDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("startDate", getStartDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("source", getSource());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("itemIfpAttachedDate", getItemIfpAttachedDate());
        attributes.put("batchId", getBatchId());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("ifpDetailsSid", getIfpDetailsSid());
        attributes.put("ifpModelSid", getIfpModelSid());
        attributes.put("itemIfpAttachedStatus", getItemIfpAttachedStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Date endDate = (Date) attributes.get("endDate");

        if (endDate != null) {
            setEndDate(endDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        Date startDate = (Date) attributes.get("startDate");

        if (startDate != null) {
            setStartDate(startDate);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date itemIfpAttachedDate = (Date) attributes.get("itemIfpAttachedDate");

        if (itemIfpAttachedDate != null) {
            setItemIfpAttachedDate(itemIfpAttachedDate);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Integer ifpDetailsSid = (Integer) attributes.get("ifpDetailsSid");

        if (ifpDetailsSid != null) {
            setIfpDetailsSid(ifpDetailsSid);
        }

        Integer ifpModelSid = (Integer) attributes.get("ifpModelSid");

        if (ifpModelSid != null) {
            setIfpModelSid(ifpModelSid);
        }

        Integer itemIfpAttachedStatus = (Integer) attributes.get(
                "itemIfpAttachedStatus");

        if (itemIfpAttachedStatus != null) {
            setItemIfpAttachedStatus(itemIfpAttachedStatus);
        }
    }

    @Override
    public int getItemMasterSid() {
        return _itemMasterSid;
    }

    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _itemMasterSid = itemMasterSid;
    }

    @Override
    public Date getEndDate() {
        return _endDate;
    }

    @Override
    public void setEndDate(Date endDate) {
        _endDate = endDate;
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
    }

    @Override
    public Date getStartDate() {
        return _startDate;
    }

    @Override
    public void setStartDate(Date startDate) {
        _startDate = startDate;
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
    public int getCreatedBy() {
        return _createdBy;
    }

    @Override
    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    @Override
    public Date getItemIfpAttachedDate() {
        return _itemIfpAttachedDate;
    }

    @Override
    public void setItemIfpAttachedDate(Date itemIfpAttachedDate) {
        _itemIfpAttachedDate = itemIfpAttachedDate;
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
    public int getModifiedBy() {
        return _modifiedBy;
    }

    @Override
    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    @Override
    public String getInboundStatus() {
        if (_inboundStatus == null) {
            return StringPool.BLANK;
        } else {
            return _inboundStatus;
        }
    }

    @Override
    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;
    }

    @Override
    public int getIfpDetailsSid() {
        return _ifpDetailsSid;
    }

    @Override
    public void setIfpDetailsSid(int ifpDetailsSid) {
        _ifpDetailsSid = ifpDetailsSid;
    }

    @Override
    public int getIfpModelSid() {
        return _ifpModelSid;
    }

    @Override
    public void setIfpModelSid(int ifpModelSid) {
        _columnBitmask |= IFPMODELSID_COLUMN_BITMASK;

        if (!_setOriginalIfpModelSid) {
            _setOriginalIfpModelSid = true;

            _originalIfpModelSid = _ifpModelSid;
        }

        _ifpModelSid = ifpModelSid;
    }

    public int getOriginalIfpModelSid() {
        return _originalIfpModelSid;
    }

    @Override
    public int getItemIfpAttachedStatus() {
        return _itemIfpAttachedStatus;
    }

    @Override
    public void setItemIfpAttachedStatus(int itemIfpAttachedStatus) {
        _itemIfpAttachedStatus = itemIfpAttachedStatus;
    }

    public long getColumnBitmask() {
        return _columnBitmask;
    }

    @Override
    public IfpDetails toEscapedModel() {
        if (_escapedModel == null) {
            _escapedModel = (IfpDetails) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelInterfaces, new AutoEscapeBeanHandler(this));
        }

        return _escapedModel;
    }

    @Override
    public Object clone() {
        IfpDetailsImpl ifpDetailsImpl = new IfpDetailsImpl();

        ifpDetailsImpl.setItemMasterSid(getItemMasterSid());
        ifpDetailsImpl.setEndDate(getEndDate());
        ifpDetailsImpl.setModifiedDate(getModifiedDate());
        ifpDetailsImpl.setRecordLockStatus(getRecordLockStatus());
        ifpDetailsImpl.setStartDate(getStartDate());
        ifpDetailsImpl.setCreatedDate(getCreatedDate());
        ifpDetailsImpl.setSource(getSource());
        ifpDetailsImpl.setCreatedBy(getCreatedBy());
        ifpDetailsImpl.setItemIfpAttachedDate(getItemIfpAttachedDate());
        ifpDetailsImpl.setBatchId(getBatchId());
        ifpDetailsImpl.setModifiedBy(getModifiedBy());
        ifpDetailsImpl.setInboundStatus(getInboundStatus());
        ifpDetailsImpl.setIfpDetailsSid(getIfpDetailsSid());
        ifpDetailsImpl.setIfpModelSid(getIfpModelSid());
        ifpDetailsImpl.setItemIfpAttachedStatus(getItemIfpAttachedStatus());

        ifpDetailsImpl.resetOriginalValues();

        return ifpDetailsImpl;
    }

    @Override
    public int compareTo(IfpDetails ifpDetails) {
        int primaryKey = ifpDetails.getPrimaryKey();

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

        if (!(obj instanceof IfpDetails)) {
            return false;
        }

        IfpDetails ifpDetails = (IfpDetails) obj;

        int primaryKey = ifpDetails.getPrimaryKey();

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
        IfpDetailsModelImpl ifpDetailsModelImpl = this;

        ifpDetailsModelImpl._originalIfpModelSid = ifpDetailsModelImpl._ifpModelSid;

        ifpDetailsModelImpl._setOriginalIfpModelSid = false;

        ifpDetailsModelImpl._columnBitmask = 0;
    }

    @Override
    public CacheModel<IfpDetails> toCacheModel() {
        IfpDetailsCacheModel ifpDetailsCacheModel = new IfpDetailsCacheModel();

        ifpDetailsCacheModel.itemMasterSid = getItemMasterSid();

        Date endDate = getEndDate();

        if (endDate != null) {
            ifpDetailsCacheModel.endDate = endDate.getTime();
        } else {
            ifpDetailsCacheModel.endDate = Long.MIN_VALUE;
        }

        Date modifiedDate = getModifiedDate();

        if (modifiedDate != null) {
            ifpDetailsCacheModel.modifiedDate = modifiedDate.getTime();
        } else {
            ifpDetailsCacheModel.modifiedDate = Long.MIN_VALUE;
        }

        ifpDetailsCacheModel.recordLockStatus = getRecordLockStatus();

        Date startDate = getStartDate();

        if (startDate != null) {
            ifpDetailsCacheModel.startDate = startDate.getTime();
        } else {
            ifpDetailsCacheModel.startDate = Long.MIN_VALUE;
        }

        Date createdDate = getCreatedDate();

        if (createdDate != null) {
            ifpDetailsCacheModel.createdDate = createdDate.getTime();
        } else {
            ifpDetailsCacheModel.createdDate = Long.MIN_VALUE;
        }

        ifpDetailsCacheModel.source = getSource();

        String source = ifpDetailsCacheModel.source;

        if ((source != null) && (source.length() == 0)) {
            ifpDetailsCacheModel.source = null;
        }

        ifpDetailsCacheModel.createdBy = getCreatedBy();

        Date itemIfpAttachedDate = getItemIfpAttachedDate();

        if (itemIfpAttachedDate != null) {
            ifpDetailsCacheModel.itemIfpAttachedDate = itemIfpAttachedDate.getTime();
        } else {
            ifpDetailsCacheModel.itemIfpAttachedDate = Long.MIN_VALUE;
        }

        ifpDetailsCacheModel.batchId = getBatchId();

        String batchId = ifpDetailsCacheModel.batchId;

        if ((batchId != null) && (batchId.length() == 0)) {
            ifpDetailsCacheModel.batchId = null;
        }

        ifpDetailsCacheModel.modifiedBy = getModifiedBy();

        ifpDetailsCacheModel.inboundStatus = getInboundStatus();

        String inboundStatus = ifpDetailsCacheModel.inboundStatus;

        if ((inboundStatus != null) && (inboundStatus.length() == 0)) {
            ifpDetailsCacheModel.inboundStatus = null;
        }

        ifpDetailsCacheModel.ifpDetailsSid = getIfpDetailsSid();

        ifpDetailsCacheModel.ifpModelSid = getIfpModelSid();

        ifpDetailsCacheModel.itemIfpAttachedStatus = getItemIfpAttachedStatus();

        return ifpDetailsCacheModel;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(31);

        sb.append("{itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", endDate=");
        sb.append(getEndDate());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", startDate=");
        sb.append(getStartDate());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", itemIfpAttachedDate=");
        sb.append(getItemIfpAttachedDate());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", ifpDetailsSid=");
        sb.append(getIfpDetailsSid());
        sb.append(", ifpModelSid=");
        sb.append(getIfpModelSid());
        sb.append(", itemIfpAttachedStatus=");
        sb.append(getItemIfpAttachedStatus());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(49);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.IfpDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>endDate</column-name><column-value><![CDATA[");
        sb.append(getEndDate());
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
            "<column><column-name>startDate</column-name><column-value><![CDATA[");
        sb.append(getStartDate());
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
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemIfpAttachedDate</column-name><column-value><![CDATA[");
        sb.append(getItemIfpAttachedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
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
            "<column><column-name>ifpDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getIfpDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpModelSid</column-name><column-value><![CDATA[");
        sb.append(getIfpModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemIfpAttachedStatus</column-name><column-value><![CDATA[");
        sb.append(getItemIfpAttachedStatus());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
