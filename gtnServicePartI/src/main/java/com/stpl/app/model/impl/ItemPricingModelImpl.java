package com.stpl.app.model.impl;

import com.stpl.app.model.ItemPricing;
import com.stpl.app.model.ItemPricingModel;

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
 * The base model implementation for the ItemPricing service. Represents a row in the &quot;ITEM_PRICING&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.stpl.app.model.ItemPricingModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ItemPricingImpl}.
 * </p>
 *
 * @author
 * @see ItemPricingImpl
 * @see com.stpl.app.model.ItemPricing
 * @see com.stpl.app.model.ItemPricingModel
 * @generated
 */
public class ItemPricingModelImpl extends BaseModelImpl<ItemPricing>
    implements ItemPricingModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a item pricing model instance should use the {@link com.stpl.app.model.ItemPricing} interface instead.
     */
    public static final String TABLE_NAME = "ITEM_PRICING";
    public static final Object[][] TABLE_COLUMNS = {
            { "ITEM_MASTER_SID", Types.INTEGER },
            { "ITEM_PRICING_QUALIFIER_SID", Types.INTEGER },
            { "ITEM_PRICE", Types.DOUBLE },
            { "END_DATE", Types.TIMESTAMP },
            { "MODIFIED_DATE", Types.TIMESTAMP },
            { "ENTITY_CODE", Types.VARCHAR },
            { "RECORD_LOCK_STATUS", Types.BOOLEAN },
            { "START_DATE", Types.TIMESTAMP },
            { "CREATED_DATE", Types.TIMESTAMP },
            { "CREATED_BY", Types.INTEGER },
            { "SOURCE", Types.VARCHAR },
            { "BATCH_ID", Types.VARCHAR },
            { "ITEM_UOM", Types.INTEGER },
            { "MODIFIED_BY", Types.INTEGER },
            { "INBOUND_STATUS", Types.VARCHAR },
            { "ITEM_PRICING_SID", Types.INTEGER },
            { "PRICING_CODE_STATUS", Types.INTEGER }
        };
    public static final String TABLE_SQL_CREATE = "create table ITEM_PRICING (ITEM_MASTER_SID INTEGER,ITEM_PRICING_QUALIFIER_SID INTEGER,ITEM_PRICE DOUBLE,END_DATE DATE null,MODIFIED_DATE DATE null,ENTITY_CODE VARCHAR(75) null,RECORD_LOCK_STATUS BOOLEAN,START_DATE DATE null,CREATED_DATE DATE null,CREATED_BY INTEGER,SOURCE VARCHAR(75) null,BATCH_ID VARCHAR(75) null,ITEM_UOM INTEGER,MODIFIED_BY INTEGER,INBOUND_STATUS VARCHAR(75) null,ITEM_PRICING_SID INTEGER not null primary key IDENTITY,PRICING_CODE_STATUS INTEGER)";
    public static final String TABLE_SQL_DROP = "drop table ITEM_PRICING";
    public static final String ORDER_BY_JPQL = " ORDER BY itemPricing.itemPricingSid ASC";
    public static final String ORDER_BY_SQL = " ORDER BY ITEM_PRICING.ITEM_PRICING_SID ASC";
    public static final String DATA_SOURCE = "appDataSource";
    public static final String SESSION_FACTORY = "appSessionFactory";
    public static final String TX_MANAGER = "appTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.entity.cache.enabled.com.stpl.app.model.ItemPricing"),
            false);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.finder.cache.enabled.com.stpl.app.model.ItemPricing"),
            false);
    public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.column.bitmask.enabled.com.stpl.app.model.ItemPricing"),
            true);
    public static long ITEMMASTERSID_COLUMN_BITMASK = 1L;
    public static long ITEMPRICINGQUALIFIERSID_COLUMN_BITMASK = 2L;
    public static long ITEMUOM_COLUMN_BITMASK = 4L;
    public static long PRICINGCODESTATUS_COLUMN_BITMASK = 8L;
    public static long STARTDATE_COLUMN_BITMASK = 16L;
    public static long ITEMPRICINGSID_COLUMN_BITMASK = 32L;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.util.service.ServiceProps.get(
                "lock.expiration.time.com.stpl.app.model.ItemPricing"));
    private static ClassLoader _classLoader = ItemPricing.class.getClassLoader();
    private static Class<?>[] _escapedModelInterfaces = new Class[] {
            ItemPricing.class
        };
    private int _itemMasterSid;
    private int _originalItemMasterSid;
    private boolean _setOriginalItemMasterSid;
    private int _itemPricingQualifierSid;
    private int _originalItemPricingQualifierSid;
    private boolean _setOriginalItemPricingQualifierSid;
    private double _itemPrice;
    private Date _endDate;
    private Date _modifiedDate;
    private String _entityCode;
    private boolean _recordLockStatus;
    private Date _startDate;
    private Date _originalStartDate;
    private Date _createdDate;
    private int _createdBy;
    private String _source;
    private String _batchId;
    private int _itemUom;
    private int _originalItemUom;
    private boolean _setOriginalItemUom;
    private int _modifiedBy;
    private String _inboundStatus;
    private int _itemPricingSid;
    private int _pricingCodeStatus;
    private int _originalPricingCodeStatus;
    private boolean _setOriginalPricingCodeStatus;
    private long _columnBitmask;
    private ItemPricing _escapedModel;

    public ItemPricingModelImpl() {
    }

    @Override
    public int getPrimaryKey() {
        return _itemPricingSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setItemPricingSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _itemPricingSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Class<?> getModelClass() {
        return ItemPricing.class;
    }

    @Override
    public String getModelClassName() {
        return ItemPricing.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("itemPricingQualifierSid", getItemPricingQualifierSid());
        attributes.put("itemPrice", getItemPrice());
        attributes.put("endDate", getEndDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("entityCode", getEntityCode());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("startDate", getStartDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("batchId", getBatchId());
        attributes.put("itemUom", getItemUom());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("itemPricingSid", getItemPricingSid());
        attributes.put("pricingCodeStatus", getPricingCodeStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Integer itemPricingQualifierSid = (Integer) attributes.get(
                "itemPricingQualifierSid");

        if (itemPricingQualifierSid != null) {
            setItemPricingQualifierSid(itemPricingQualifierSid);
        }

        Double itemPrice = (Double) attributes.get("itemPrice");

        if (itemPrice != null) {
            setItemPrice(itemPrice);
        }

        Date endDate = (Date) attributes.get("endDate");

        if (endDate != null) {
            setEndDate(endDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String entityCode = (String) attributes.get("entityCode");

        if (entityCode != null) {
            setEntityCode(entityCode);
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

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Integer itemUom = (Integer) attributes.get("itemUom");

        if (itemUom != null) {
            setItemUom(itemUom);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Integer itemPricingSid = (Integer) attributes.get("itemPricingSid");

        if (itemPricingSid != null) {
            setItemPricingSid(itemPricingSid);
        }

        Integer pricingCodeStatus = (Integer) attributes.get(
                "pricingCodeStatus");

        if (pricingCodeStatus != null) {
            setPricingCodeStatus(pricingCodeStatus);
        }
    }

    @Override
    public int getItemMasterSid() {
        return _itemMasterSid;
    }

    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _columnBitmask |= ITEMMASTERSID_COLUMN_BITMASK;

        if (!_setOriginalItemMasterSid) {
            _setOriginalItemMasterSid = true;

            _originalItemMasterSid = _itemMasterSid;
        }

        _itemMasterSid = itemMasterSid;
    }

    public int getOriginalItemMasterSid() {
        return _originalItemMasterSid;
    }

    @Override
    public int getItemPricingQualifierSid() {
        return _itemPricingQualifierSid;
    }

    @Override
    public void setItemPricingQualifierSid(int itemPricingQualifierSid) {
        _columnBitmask |= ITEMPRICINGQUALIFIERSID_COLUMN_BITMASK;

        if (!_setOriginalItemPricingQualifierSid) {
            _setOriginalItemPricingQualifierSid = true;

            _originalItemPricingQualifierSid = _itemPricingQualifierSid;
        }

        _itemPricingQualifierSid = itemPricingQualifierSid;
    }

    public int getOriginalItemPricingQualifierSid() {
        return _originalItemPricingQualifierSid;
    }

    @Override
    public double getItemPrice() {
        return _itemPrice;
    }

    @Override
    public void setItemPrice(double itemPrice) {
        _itemPrice = itemPrice;
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
    public String getEntityCode() {
        if (_entityCode == null) {
            return StringPool.BLANK;
        } else {
            return _entityCode;
        }
    }

    @Override
    public void setEntityCode(String entityCode) {
        _entityCode = entityCode;
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
        _columnBitmask |= STARTDATE_COLUMN_BITMASK;

        if (_originalStartDate == null) {
            _originalStartDate = _startDate;
        }

        _startDate = startDate;
    }

    public Date getOriginalStartDate() {
        return _originalStartDate;
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
    public int getItemUom() {
        return _itemUom;
    }

    @Override
    public void setItemUom(int itemUom) {
        _columnBitmask |= ITEMUOM_COLUMN_BITMASK;

        if (!_setOriginalItemUom) {
            _setOriginalItemUom = true;

            _originalItemUom = _itemUom;
        }

        _itemUom = itemUom;
    }

    public int getOriginalItemUom() {
        return _originalItemUom;
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
    public int getItemPricingSid() {
        return _itemPricingSid;
    }

    @Override
    public void setItemPricingSid(int itemPricingSid) {
        _itemPricingSid = itemPricingSid;
    }

    @Override
    public int getPricingCodeStatus() {
        return _pricingCodeStatus;
    }

    @Override
    public void setPricingCodeStatus(int pricingCodeStatus) {
        _columnBitmask |= PRICINGCODESTATUS_COLUMN_BITMASK;

        if (!_setOriginalPricingCodeStatus) {
            _setOriginalPricingCodeStatus = true;

            _originalPricingCodeStatus = _pricingCodeStatus;
        }

        _pricingCodeStatus = pricingCodeStatus;
    }

    public int getOriginalPricingCodeStatus() {
        return _originalPricingCodeStatus;
    }

    public long getColumnBitmask() {
        return _columnBitmask;
    }

    @Override
    public ItemPricing toEscapedModel() {
        if (_escapedModel == null) {
            _escapedModel = (ItemPricing) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelInterfaces, new AutoEscapeBeanHandler(this));
        }

        return _escapedModel;
    }

    @Override
    public Object clone() {
        ItemPricingImpl itemPricingImpl = new ItemPricingImpl();

        itemPricingImpl.setItemMasterSid(getItemMasterSid());
        itemPricingImpl.setItemPricingQualifierSid(getItemPricingQualifierSid());
        itemPricingImpl.setItemPrice(getItemPrice());
        itemPricingImpl.setEndDate(getEndDate());
        itemPricingImpl.setModifiedDate(getModifiedDate());
        itemPricingImpl.setEntityCode(getEntityCode());
        itemPricingImpl.setRecordLockStatus(getRecordLockStatus());
        itemPricingImpl.setStartDate(getStartDate());
        itemPricingImpl.setCreatedDate(getCreatedDate());
        itemPricingImpl.setCreatedBy(getCreatedBy());
        itemPricingImpl.setSource(getSource());
        itemPricingImpl.setBatchId(getBatchId());
        itemPricingImpl.setItemUom(getItemUom());
        itemPricingImpl.setModifiedBy(getModifiedBy());
        itemPricingImpl.setInboundStatus(getInboundStatus());
        itemPricingImpl.setItemPricingSid(getItemPricingSid());
        itemPricingImpl.setPricingCodeStatus(getPricingCodeStatus());

        itemPricingImpl.resetOriginalValues();

        return itemPricingImpl;
    }

    @Override
    public int compareTo(ItemPricing itemPricing) {
        int primaryKey = itemPricing.getPrimaryKey();

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

        if (!(obj instanceof ItemPricing)) {
            return false;
        }

        ItemPricing itemPricing = (ItemPricing) obj;

        int primaryKey = itemPricing.getPrimaryKey();

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
        ItemPricingModelImpl itemPricingModelImpl = this;

        itemPricingModelImpl._originalItemMasterSid = itemPricingModelImpl._itemMasterSid;

        itemPricingModelImpl._setOriginalItemMasterSid = false;

        itemPricingModelImpl._originalItemPricingQualifierSid = itemPricingModelImpl._itemPricingQualifierSid;

        itemPricingModelImpl._setOriginalItemPricingQualifierSid = false;

        itemPricingModelImpl._originalStartDate = itemPricingModelImpl._startDate;

        itemPricingModelImpl._originalItemUom = itemPricingModelImpl._itemUom;

        itemPricingModelImpl._setOriginalItemUom = false;

        itemPricingModelImpl._originalPricingCodeStatus = itemPricingModelImpl._pricingCodeStatus;

        itemPricingModelImpl._setOriginalPricingCodeStatus = false;

        itemPricingModelImpl._columnBitmask = 0;
    }

    @Override
    public CacheModel<ItemPricing> toCacheModel() {
        ItemPricingCacheModel itemPricingCacheModel = new ItemPricingCacheModel();

        itemPricingCacheModel.itemMasterSid = getItemMasterSid();

        itemPricingCacheModel.itemPricingQualifierSid = getItemPricingQualifierSid();

        itemPricingCacheModel.itemPrice = getItemPrice();

        Date endDate = getEndDate();

        if (endDate != null) {
            itemPricingCacheModel.endDate = endDate.getTime();
        } else {
            itemPricingCacheModel.endDate = Long.MIN_VALUE;
        }

        Date modifiedDate = getModifiedDate();

        if (modifiedDate != null) {
            itemPricingCacheModel.modifiedDate = modifiedDate.getTime();
        } else {
            itemPricingCacheModel.modifiedDate = Long.MIN_VALUE;
        }

        itemPricingCacheModel.entityCode = getEntityCode();

        String entityCode = itemPricingCacheModel.entityCode;

        if ((entityCode != null) && (entityCode.length() == 0)) {
            itemPricingCacheModel.entityCode = null;
        }

        itemPricingCacheModel.recordLockStatus = getRecordLockStatus();

        Date startDate = getStartDate();

        if (startDate != null) {
            itemPricingCacheModel.startDate = startDate.getTime();
        } else {
            itemPricingCacheModel.startDate = Long.MIN_VALUE;
        }

        Date createdDate = getCreatedDate();

        if (createdDate != null) {
            itemPricingCacheModel.createdDate = createdDate.getTime();
        } else {
            itemPricingCacheModel.createdDate = Long.MIN_VALUE;
        }

        itemPricingCacheModel.createdBy = getCreatedBy();

        itemPricingCacheModel.source = getSource();

        String source = itemPricingCacheModel.source;

        if ((source != null) && (source.length() == 0)) {
            itemPricingCacheModel.source = null;
        }

        itemPricingCacheModel.batchId = getBatchId();

        String batchId = itemPricingCacheModel.batchId;

        if ((batchId != null) && (batchId.length() == 0)) {
            itemPricingCacheModel.batchId = null;
        }

        itemPricingCacheModel.itemUom = getItemUom();

        itemPricingCacheModel.modifiedBy = getModifiedBy();

        itemPricingCacheModel.inboundStatus = getInboundStatus();

        String inboundStatus = itemPricingCacheModel.inboundStatus;

        if ((inboundStatus != null) && (inboundStatus.length() == 0)) {
            itemPricingCacheModel.inboundStatus = null;
        }

        itemPricingCacheModel.itemPricingSid = getItemPricingSid();

        itemPricingCacheModel.pricingCodeStatus = getPricingCodeStatus();

        return itemPricingCacheModel;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(35);

        sb.append("{itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", itemPricingQualifierSid=");
        sb.append(getItemPricingQualifierSid());
        sb.append(", itemPrice=");
        sb.append(getItemPrice());
        sb.append(", endDate=");
        sb.append(getEndDate());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", entityCode=");
        sb.append(getEntityCode());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", startDate=");
        sb.append(getStartDate());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", itemUom=");
        sb.append(getItemUom());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", itemPricingSid=");
        sb.append(getItemPricingSid());
        sb.append(", pricingCodeStatus=");
        sb.append(getPricingCodeStatus());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(55);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ItemPricing");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemPricingQualifierSid</column-name><column-value><![CDATA[");
        sb.append(getItemPricingQualifierSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemPrice</column-name><column-value><![CDATA[");
        sb.append(getItemPrice());
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
            "<column><column-name>entityCode</column-name><column-value><![CDATA[");
        sb.append(getEntityCode());
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
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemUom</column-name><column-value><![CDATA[");
        sb.append(getItemUom());
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
            "<column><column-name>itemPricingSid</column-name><column-value><![CDATA[");
        sb.append(getItemPricingSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>pricingCodeStatus</column-name><column-value><![CDATA[");
        sb.append(getPricingCodeStatus());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
