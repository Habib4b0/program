package com.stpl.app.model.impl;

import com.stpl.app.model.GcmItemDetails;
import com.stpl.app.model.GcmItemDetailsModel;

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
 * The base model implementation for the GcmItemDetails service. Represents a row in the &quot;GCM_ITEM_DETAILS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.stpl.app.model.GcmItemDetailsModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link GcmItemDetailsImpl}.
 * </p>
 *
 * @author
 * @see GcmItemDetailsImpl
 * @see com.stpl.app.model.GcmItemDetails
 * @see com.stpl.app.model.GcmItemDetailsModel
 * @generated
 */
public class GcmItemDetailsModelImpl extends BaseModelImpl<GcmItemDetails>
    implements GcmItemDetailsModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a gcm item details model instance should use the {@link com.stpl.app.model.GcmItemDetails} interface instead.
     */
    public static final String TABLE_NAME = "GCM_ITEM_DETAILS";
    public static final Object[][] TABLE_COLUMNS = {
            { "IFP_DETAILS_END_DATE", Types.TIMESTAMP },
            { "ITEM_STATUS", Types.VARCHAR },
            { "CHECK_RECORD", Types.BOOLEAN },
            { "IFP_DETAILS_START_DATE", Types.TIMESTAMP },
            { "USER_ID", Types.INTEGER },
            { "ITEM_MASTER_SID", Types.INTEGER },
            { "ITEM_END_DATE", Types.TIMESTAMP },
            { "GCM_ITEM_DETAILS_SID", Types.INTEGER },
            { "ITEM_IFP_DETAILS_SID", Types.INTEGER },
            { "ITEM_ID", Types.VARCHAR },
            { "BRAND_NAME", Types.VARCHAR },
            { "MODIFIED_DATE", Types.TIMESTAMP },
            { "CREATED_DATE", Types.TIMESTAMP },
            { "CREATED_BY", Types.INTEGER },
            { "ITEM_START_DATE", Types.TIMESTAMP },
            { "SESSION_ID", Types.VARCHAR },
            { "ITEM_NAME", Types.VARCHAR },
            { "OPERATION", Types.VARCHAR },
            { "MODIFIED_BY", Types.INTEGER },
            { "INBOUND_STATUS", Types.VARCHAR },
            { "ITEM_STATUS_SID", Types.INTEGER },
            { "ITEM_NO", Types.VARCHAR },
            { "IFP_MODEL_SID", Types.INTEGER },
            { "THERAPUTIC_CLASS", Types.VARCHAR }
        };
    public static final String TABLE_SQL_CREATE = "create table GCM_ITEM_DETAILS (IFP_DETAILS_END_DATE DATE null,ITEM_STATUS VARCHAR(75) null,CHECK_RECORD BOOLEAN,IFP_DETAILS_START_DATE DATE null,USER_ID INTEGER,ITEM_MASTER_SID INTEGER,ITEM_END_DATE DATE null,GCM_ITEM_DETAILS_SID INTEGER not null primary key IDENTITY,ITEM_IFP_DETAILS_SID INTEGER,ITEM_ID VARCHAR(75) null,BRAND_NAME VARCHAR(75) null,MODIFIED_DATE DATE null,CREATED_DATE DATE null,CREATED_BY INTEGER,ITEM_START_DATE DATE null,SESSION_ID VARCHAR(75) null,ITEM_NAME VARCHAR(75) null,OPERATION VARCHAR(75) null,MODIFIED_BY INTEGER,INBOUND_STATUS VARCHAR(75) null,ITEM_STATUS_SID INTEGER,ITEM_NO VARCHAR(75) null,IFP_MODEL_SID INTEGER,THERAPUTIC_CLASS VARCHAR(75) null)";
    public static final String TABLE_SQL_DROP = "drop table GCM_ITEM_DETAILS";
    public static final String ORDER_BY_JPQL = " ORDER BY gcmItemDetails.gcmItemDetailsSid ASC";
    public static final String ORDER_BY_SQL = " ORDER BY GCM_ITEM_DETAILS.GCM_ITEM_DETAILS_SID ASC";
    public static final String DATA_SOURCE = "appDataSource";
    public static final String SESSION_FACTORY = "appSessionFactory";
    public static final String TX_MANAGER = "appTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.entity.cache.enabled.com.stpl.app.model.GcmItemDetails"),
            false);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.finder.cache.enabled.com.stpl.app.model.GcmItemDetails"),
            false);
    public static final boolean COLUMN_BITMASK_ENABLED = false;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.util.service.ServiceProps.get(
                "lock.expiration.time.com.stpl.app.model.GcmItemDetails"));
    private static ClassLoader _classLoader = GcmItemDetails.class.getClassLoader();
    private static Class<?>[] _escapedModelInterfaces = new Class[] {
            GcmItemDetails.class
        };
    private Date _ifpDetailsEndDate;
    private String _itemStatus;
    private boolean _checkRecord;
    private Date _ifpDetailsStartDate;
    private int _userId;
    private int _itemMasterSid;
    private Date _itemEndDate;
    private int _gcmItemDetailsSid;
    private int _itemIfpDetailsSid;
    private String _itemId;
    private String _brandName;
    private Date _modifiedDate;
    private Date _createdDate;
    private int _createdBy;
    private Date _itemStartDate;
    private String _sessionId;
    private String _itemName;
    private String _operation;
    private int _modifiedBy;
    private String _inboundStatus;
    private int _itemStatusSid;
    private String _itemNo;
    private int _ifpModelSid;
    private String _theraputicClass;
    private GcmItemDetails _escapedModel;

    public GcmItemDetailsModelImpl() {
    }

    @Override
    public int getPrimaryKey() {
        return _gcmItemDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setGcmItemDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _gcmItemDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Class<?> getModelClass() {
        return GcmItemDetails.class;
    }

    @Override
    public String getModelClassName() {
        return GcmItemDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("ifpDetailsEndDate", getIfpDetailsEndDate());
        attributes.put("itemStatus", getItemStatus());
        attributes.put("checkRecord", getCheckRecord());
        attributes.put("ifpDetailsStartDate", getIfpDetailsStartDate());
        attributes.put("userId", getUserId());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("itemEndDate", getItemEndDate());
        attributes.put("gcmItemDetailsSid", getGcmItemDetailsSid());
        attributes.put("itemIfpDetailsSid", getItemIfpDetailsSid());
        attributes.put("itemId", getItemId());
        attributes.put("brandName", getBrandName());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("itemStartDate", getItemStartDate());
        attributes.put("sessionId", getSessionId());
        attributes.put("itemName", getItemName());
        attributes.put("operation", getOperation());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("itemStatusSid", getItemStatusSid());
        attributes.put("itemNo", getItemNo());
        attributes.put("ifpModelSid", getIfpModelSid());
        attributes.put("theraputicClass", getTheraputicClass());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date ifpDetailsEndDate = (Date) attributes.get("ifpDetailsEndDate");

        if (ifpDetailsEndDate != null) {
            setIfpDetailsEndDate(ifpDetailsEndDate);
        }

        String itemStatus = (String) attributes.get("itemStatus");

        if (itemStatus != null) {
            setItemStatus(itemStatus);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        Date ifpDetailsStartDate = (Date) attributes.get("ifpDetailsStartDate");

        if (ifpDetailsStartDate != null) {
            setIfpDetailsStartDate(ifpDetailsStartDate);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Date itemEndDate = (Date) attributes.get("itemEndDate");

        if (itemEndDate != null) {
            setItemEndDate(itemEndDate);
        }

        Integer gcmItemDetailsSid = (Integer) attributes.get(
                "gcmItemDetailsSid");

        if (gcmItemDetailsSid != null) {
            setGcmItemDetailsSid(gcmItemDetailsSid);
        }

        Integer itemIfpDetailsSid = (Integer) attributes.get(
                "itemIfpDetailsSid");

        if (itemIfpDetailsSid != null) {
            setItemIfpDetailsSid(itemIfpDetailsSid);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String brandName = (String) attributes.get("brandName");

        if (brandName != null) {
            setBrandName(brandName);
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

        Date itemStartDate = (Date) attributes.get("itemStartDate");

        if (itemStartDate != null) {
            setItemStartDate(itemStartDate);
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

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Integer itemStatusSid = (Integer) attributes.get("itemStatusSid");

        if (itemStatusSid != null) {
            setItemStatusSid(itemStatusSid);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        Integer ifpModelSid = (Integer) attributes.get("ifpModelSid");

        if (ifpModelSid != null) {
            setIfpModelSid(ifpModelSid);
        }

        String theraputicClass = (String) attributes.get("theraputicClass");

        if (theraputicClass != null) {
            setTheraputicClass(theraputicClass);
        }
    }

    @Override
    public Date getIfpDetailsEndDate() {
        return _ifpDetailsEndDate;
    }

    @Override
    public void setIfpDetailsEndDate(Date ifpDetailsEndDate) {
        _ifpDetailsEndDate = ifpDetailsEndDate;
    }

    @Override
    public String getItemStatus() {
        if (_itemStatus == null) {
            return StringPool.BLANK;
        } else {
            return _itemStatus;
        }
    }

    @Override
    public void setItemStatus(String itemStatus) {
        _itemStatus = itemStatus;
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
    public Date getIfpDetailsStartDate() {
        return _ifpDetailsStartDate;
    }

    @Override
    public void setIfpDetailsStartDate(Date ifpDetailsStartDate) {
        _ifpDetailsStartDate = ifpDetailsStartDate;
    }

    @Override
    public int getUserId() {
        return _userId;
    }

    @Override
    public void setUserId(int userId) {
        _userId = userId;
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
    public Date getItemEndDate() {
        return _itemEndDate;
    }

    @Override
    public void setItemEndDate(Date itemEndDate) {
        _itemEndDate = itemEndDate;
    }

    @Override
    public int getGcmItemDetailsSid() {
        return _gcmItemDetailsSid;
    }

    @Override
    public void setGcmItemDetailsSid(int gcmItemDetailsSid) {
        _gcmItemDetailsSid = gcmItemDetailsSid;
    }

    @Override
    public int getItemIfpDetailsSid() {
        return _itemIfpDetailsSid;
    }

    @Override
    public void setItemIfpDetailsSid(int itemIfpDetailsSid) {
        _itemIfpDetailsSid = itemIfpDetailsSid;
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
    public String getBrandName() {
        if (_brandName == null) {
            return StringPool.BLANK;
        } else {
            return _brandName;
        }
    }

    @Override
    public void setBrandName(String brandName) {
        _brandName = brandName;
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
    public Date getCreatedDate() {
        return _createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
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
    public Date getItemStartDate() {
        return _itemStartDate;
    }

    @Override
    public void setItemStartDate(Date itemStartDate) {
        _itemStartDate = itemStartDate;
    }

    @Override
    public String getSessionId() {
        if (_sessionId == null) {
            return StringPool.BLANK;
        } else {
            return _sessionId;
        }
    }

    @Override
    public void setSessionId(String sessionId) {
        _sessionId = sessionId;
    }

    @Override
    public String getItemName() {
        if (_itemName == null) {
            return StringPool.BLANK;
        } else {
            return _itemName;
        }
    }

    @Override
    public void setItemName(String itemName) {
        _itemName = itemName;
    }

    @Override
    public String getOperation() {
        if (_operation == null) {
            return StringPool.BLANK;
        } else {
            return _operation;
        }
    }

    @Override
    public void setOperation(String operation) {
        _operation = operation;
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
    public int getItemStatusSid() {
        return _itemStatusSid;
    }

    @Override
    public void setItemStatusSid(int itemStatusSid) {
        _itemStatusSid = itemStatusSid;
    }

    @Override
    public String getItemNo() {
        if (_itemNo == null) {
            return StringPool.BLANK;
        } else {
            return _itemNo;
        }
    }

    @Override
    public void setItemNo(String itemNo) {
        _itemNo = itemNo;
    }

    @Override
    public int getIfpModelSid() {
        return _ifpModelSid;
    }

    @Override
    public void setIfpModelSid(int ifpModelSid) {
        _ifpModelSid = ifpModelSid;
    }

    @Override
    public String getTheraputicClass() {
        if (_theraputicClass == null) {
            return StringPool.BLANK;
        } else {
            return _theraputicClass;
        }
    }

    @Override
    public void setTheraputicClass(String theraputicClass) {
        _theraputicClass = theraputicClass;
    }

    @Override
    public GcmItemDetails toEscapedModel() {
        if (_escapedModel == null) {
            _escapedModel = (GcmItemDetails) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelInterfaces, new AutoEscapeBeanHandler(this));
        }

        return _escapedModel;
    }

    @Override
    public Object clone() {
        GcmItemDetailsImpl gcmItemDetailsImpl = new GcmItemDetailsImpl();

        gcmItemDetailsImpl.setIfpDetailsEndDate(getIfpDetailsEndDate());
        gcmItemDetailsImpl.setItemStatus(getItemStatus());
        gcmItemDetailsImpl.setCheckRecord(getCheckRecord());
        gcmItemDetailsImpl.setIfpDetailsStartDate(getIfpDetailsStartDate());
        gcmItemDetailsImpl.setUserId(getUserId());
        gcmItemDetailsImpl.setItemMasterSid(getItemMasterSid());
        gcmItemDetailsImpl.setItemEndDate(getItemEndDate());
        gcmItemDetailsImpl.setGcmItemDetailsSid(getGcmItemDetailsSid());
        gcmItemDetailsImpl.setItemIfpDetailsSid(getItemIfpDetailsSid());
        gcmItemDetailsImpl.setItemId(getItemId());
        gcmItemDetailsImpl.setBrandName(getBrandName());
        gcmItemDetailsImpl.setModifiedDate(getModifiedDate());
        gcmItemDetailsImpl.setCreatedDate(getCreatedDate());
        gcmItemDetailsImpl.setCreatedBy(getCreatedBy());
        gcmItemDetailsImpl.setItemStartDate(getItemStartDate());
        gcmItemDetailsImpl.setSessionId(getSessionId());
        gcmItemDetailsImpl.setItemName(getItemName());
        gcmItemDetailsImpl.setOperation(getOperation());
        gcmItemDetailsImpl.setModifiedBy(getModifiedBy());
        gcmItemDetailsImpl.setInboundStatus(getInboundStatus());
        gcmItemDetailsImpl.setItemStatusSid(getItemStatusSid());
        gcmItemDetailsImpl.setItemNo(getItemNo());
        gcmItemDetailsImpl.setIfpModelSid(getIfpModelSid());
        gcmItemDetailsImpl.setTheraputicClass(getTheraputicClass());

        gcmItemDetailsImpl.resetOriginalValues();

        return gcmItemDetailsImpl;
    }

    @Override
    public int compareTo(GcmItemDetails gcmItemDetails) {
        int primaryKey = gcmItemDetails.getPrimaryKey();

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

        if (!(obj instanceof GcmItemDetails)) {
            return false;
        }

        GcmItemDetails gcmItemDetails = (GcmItemDetails) obj;

        int primaryKey = gcmItemDetails.getPrimaryKey();

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
    public CacheModel<GcmItemDetails> toCacheModel() {
        GcmItemDetailsCacheModel gcmItemDetailsCacheModel = new GcmItemDetailsCacheModel();

        Date ifpDetailsEndDate = getIfpDetailsEndDate();

        if (ifpDetailsEndDate != null) {
            gcmItemDetailsCacheModel.ifpDetailsEndDate = ifpDetailsEndDate.getTime();
        } else {
            gcmItemDetailsCacheModel.ifpDetailsEndDate = Long.MIN_VALUE;
        }

        gcmItemDetailsCacheModel.itemStatus = getItemStatus();

        String itemStatus = gcmItemDetailsCacheModel.itemStatus;

        if ((itemStatus != null) && (itemStatus.length() == 0)) {
            gcmItemDetailsCacheModel.itemStatus = null;
        }

        gcmItemDetailsCacheModel.checkRecord = getCheckRecord();

        Date ifpDetailsStartDate = getIfpDetailsStartDate();

        if (ifpDetailsStartDate != null) {
            gcmItemDetailsCacheModel.ifpDetailsStartDate = ifpDetailsStartDate.getTime();
        } else {
            gcmItemDetailsCacheModel.ifpDetailsStartDate = Long.MIN_VALUE;
        }

        gcmItemDetailsCacheModel.userId = getUserId();

        gcmItemDetailsCacheModel.itemMasterSid = getItemMasterSid();

        Date itemEndDate = getItemEndDate();

        if (itemEndDate != null) {
            gcmItemDetailsCacheModel.itemEndDate = itemEndDate.getTime();
        } else {
            gcmItemDetailsCacheModel.itemEndDate = Long.MIN_VALUE;
        }

        gcmItemDetailsCacheModel.gcmItemDetailsSid = getGcmItemDetailsSid();

        gcmItemDetailsCacheModel.itemIfpDetailsSid = getItemIfpDetailsSid();

        gcmItemDetailsCacheModel.itemId = getItemId();

        String itemId = gcmItemDetailsCacheModel.itemId;

        if ((itemId != null) && (itemId.length() == 0)) {
            gcmItemDetailsCacheModel.itemId = null;
        }

        gcmItemDetailsCacheModel.brandName = getBrandName();

        String brandName = gcmItemDetailsCacheModel.brandName;

        if ((brandName != null) && (brandName.length() == 0)) {
            gcmItemDetailsCacheModel.brandName = null;
        }

        Date modifiedDate = getModifiedDate();

        if (modifiedDate != null) {
            gcmItemDetailsCacheModel.modifiedDate = modifiedDate.getTime();
        } else {
            gcmItemDetailsCacheModel.modifiedDate = Long.MIN_VALUE;
        }

        Date createdDate = getCreatedDate();

        if (createdDate != null) {
            gcmItemDetailsCacheModel.createdDate = createdDate.getTime();
        } else {
            gcmItemDetailsCacheModel.createdDate = Long.MIN_VALUE;
        }

        gcmItemDetailsCacheModel.createdBy = getCreatedBy();

        Date itemStartDate = getItemStartDate();

        if (itemStartDate != null) {
            gcmItemDetailsCacheModel.itemStartDate = itemStartDate.getTime();
        } else {
            gcmItemDetailsCacheModel.itemStartDate = Long.MIN_VALUE;
        }

        gcmItemDetailsCacheModel.sessionId = getSessionId();

        String sessionId = gcmItemDetailsCacheModel.sessionId;

        if ((sessionId != null) && (sessionId.length() == 0)) {
            gcmItemDetailsCacheModel.sessionId = null;
        }

        gcmItemDetailsCacheModel.itemName = getItemName();

        String itemName = gcmItemDetailsCacheModel.itemName;

        if ((itemName != null) && (itemName.length() == 0)) {
            gcmItemDetailsCacheModel.itemName = null;
        }

        gcmItemDetailsCacheModel.operation = getOperation();

        String operation = gcmItemDetailsCacheModel.operation;

        if ((operation != null) && (operation.length() == 0)) {
            gcmItemDetailsCacheModel.operation = null;
        }

        gcmItemDetailsCacheModel.modifiedBy = getModifiedBy();

        gcmItemDetailsCacheModel.inboundStatus = getInboundStatus();

        String inboundStatus = gcmItemDetailsCacheModel.inboundStatus;

        if ((inboundStatus != null) && (inboundStatus.length() == 0)) {
            gcmItemDetailsCacheModel.inboundStatus = null;
        }

        gcmItemDetailsCacheModel.itemStatusSid = getItemStatusSid();

        gcmItemDetailsCacheModel.itemNo = getItemNo();

        String itemNo = gcmItemDetailsCacheModel.itemNo;

        if ((itemNo != null) && (itemNo.length() == 0)) {
            gcmItemDetailsCacheModel.itemNo = null;
        }

        gcmItemDetailsCacheModel.ifpModelSid = getIfpModelSid();

        gcmItemDetailsCacheModel.theraputicClass = getTheraputicClass();

        String theraputicClass = gcmItemDetailsCacheModel.theraputicClass;

        if ((theraputicClass != null) && (theraputicClass.length() == 0)) {
            gcmItemDetailsCacheModel.theraputicClass = null;
        }

        return gcmItemDetailsCacheModel;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(49);

        sb.append("{ifpDetailsEndDate=");
        sb.append(getIfpDetailsEndDate());
        sb.append(", itemStatus=");
        sb.append(getItemStatus());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append(", ifpDetailsStartDate=");
        sb.append(getIfpDetailsStartDate());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", itemEndDate=");
        sb.append(getItemEndDate());
        sb.append(", gcmItemDetailsSid=");
        sb.append(getGcmItemDetailsSid());
        sb.append(", itemIfpDetailsSid=");
        sb.append(getItemIfpDetailsSid());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", brandName=");
        sb.append(getBrandName());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", itemStartDate=");
        sb.append(getItemStartDate());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append(", itemName=");
        sb.append(getItemName());
        sb.append(", operation=");
        sb.append(getOperation());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", itemStatusSid=");
        sb.append(getItemStatusSid());
        sb.append(", itemNo=");
        sb.append(getItemNo());
        sb.append(", ifpModelSid=");
        sb.append(getIfpModelSid());
        sb.append(", theraputicClass=");
        sb.append(getTheraputicClass());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(76);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.GcmItemDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>ifpDetailsEndDate</column-name><column-value><![CDATA[");
        sb.append(getIfpDetailsEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemStatus</column-name><column-value><![CDATA[");
        sb.append(getItemStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpDetailsStartDate</column-name><column-value><![CDATA[");
        sb.append(getIfpDetailsStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemEndDate</column-name><column-value><![CDATA[");
        sb.append(getItemEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>gcmItemDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getGcmItemDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemIfpDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getItemIfpDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemId</column-name><column-value><![CDATA[");
        sb.append(getItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brandName</column-name><column-value><![CDATA[");
        sb.append(getBrandName());
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
            "<column><column-name>itemStartDate</column-name><column-value><![CDATA[");
        sb.append(getItemStartDate());
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
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemStatusSid</column-name><column-value><![CDATA[");
        sb.append(getItemStatusSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemNo</column-name><column-value><![CDATA[");
        sb.append(getItemNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpModelSid</column-name><column-value><![CDATA[");
        sb.append(getIfpModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>theraputicClass</column-name><column-value><![CDATA[");
        sb.append(getTheraputicClass());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
