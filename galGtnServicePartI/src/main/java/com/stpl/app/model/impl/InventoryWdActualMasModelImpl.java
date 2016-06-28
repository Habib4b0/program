package com.stpl.app.model.impl;

import com.stpl.app.model.InventoryWdActualMas;
import com.stpl.app.model.InventoryWdActualMasModel;

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
 * The base model implementation for the InventoryWdActualMas service. Represents a row in the &quot;INVENTORY_WD_ACTUAL_MAS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.stpl.app.model.InventoryWdActualMasModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link InventoryWdActualMasImpl}.
 * </p>
 *
 * @author
 * @see InventoryWdActualMasImpl
 * @see com.stpl.app.model.InventoryWdActualMas
 * @see com.stpl.app.model.InventoryWdActualMasModel
 * @generated
 */
public class InventoryWdActualMasModelImpl extends BaseModelImpl<InventoryWdActualMas>
    implements InventoryWdActualMasModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a inventory wd actual mas model instance should use the {@link com.stpl.app.model.InventoryWdActualMas} interface instead.
     */
    public static final String TABLE_NAME = "INVENTORY_WD_ACTUAL_MAS";
    public static final Object[][] TABLE_COLUMNS = {
            { "QUANTITY_ON_ORDER", Types.VARCHAR },
            { "WEEK", Types.VARCHAR },
            { "AMOUNT_ON_HAND", Types.VARCHAR },
            { "ITEM_MASTER_SID", Types.INTEGER },
            { "INVENTORY_WD_ACTUAL_MAS_SID", Types.INTEGER },
            { "YEAR", Types.VARCHAR },
            { "ITEM_ID", Types.VARCHAR },
            { "MODIFIED_DATE", Types.TIMESTAMP },
            { "ORGANIZATION_KEY", Types.VARCHAR },
            { "SOURCE", Types.VARCHAR },
            { "CREATED_BY", Types.VARCHAR },
            { "CREATED_DATE", Types.TIMESTAMP },
            { "DAY", Types.VARCHAR },
            { "UNITS_ON_HAND", Types.VARCHAR },
            { "AMOUNT_RECEIVED", Types.VARCHAR },
            { "ITEM_IDENTIFIER", Types.VARCHAR },
            { "MODIFIED_BY", Types.VARCHAR },
            { "INBOUND_STATUS", Types.VARCHAR },
            { "MONTH", Types.VARCHAR },
            { "AMOUNT_WITHDRAWN", Types.VARCHAR },
            { "QUANTITY_RECEIVED", Types.VARCHAR },
            { "UNITS_WITHDRAWN", Types.VARCHAR },
            { "COUNTRY", Types.VARCHAR },
            { "RECORD_LOCK_STATUS", Types.BOOLEAN },
            { "ITEM_IDENTIFIER_CODE_QUALIFIER", Types.VARCHAR },
            { "BATCH_ID", Types.VARCHAR },
            { "AMOUNT_ON_ORDER", Types.VARCHAR }
        };
    public static final String TABLE_SQL_CREATE = "create table INVENTORY_WD_ACTUAL_MAS (QUANTITY_ON_ORDER VARCHAR(75) null,WEEK VARCHAR(75) null,AMOUNT_ON_HAND VARCHAR(75) null,ITEM_MASTER_SID INTEGER,INVENTORY_WD_ACTUAL_MAS_SID INTEGER not null primary key IDENTITY,YEAR VARCHAR(75) null,ITEM_ID VARCHAR(75) null,MODIFIED_DATE DATE null,ORGANIZATION_KEY VARCHAR(75) null,SOURCE VARCHAR(75) null,CREATED_BY VARCHAR(75) null,CREATED_DATE DATE null,DAY VARCHAR(75) null,UNITS_ON_HAND VARCHAR(75) null,AMOUNT_RECEIVED VARCHAR(75) null,ITEM_IDENTIFIER VARCHAR(75) null,MODIFIED_BY VARCHAR(75) null,INBOUND_STATUS VARCHAR(75) null,MONTH VARCHAR(75) null,AMOUNT_WITHDRAWN VARCHAR(75) null,QUANTITY_RECEIVED VARCHAR(75) null,UNITS_WITHDRAWN VARCHAR(75) null,COUNTRY VARCHAR(75) null,RECORD_LOCK_STATUS BOOLEAN,ITEM_IDENTIFIER_CODE_QUALIFIER VARCHAR(75) null,BATCH_ID VARCHAR(75) null,AMOUNT_ON_ORDER VARCHAR(75) null)";
    public static final String TABLE_SQL_DROP = "drop table INVENTORY_WD_ACTUAL_MAS";
    public static final String ORDER_BY_JPQL = " ORDER BY inventoryWdActualMas.inventoryWdActualMasSid ASC";
    public static final String ORDER_BY_SQL = " ORDER BY INVENTORY_WD_ACTUAL_MAS.INVENTORY_WD_ACTUAL_MAS_SID ASC";
    public static final String DATA_SOURCE = "appDataSource";
    public static final String SESSION_FACTORY = "appSessionFactory";
    public static final String TX_MANAGER = "appTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.entity.cache.enabled.com.stpl.app.model.InventoryWdActualMas"),
            false);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.finder.cache.enabled.com.stpl.app.model.InventoryWdActualMas"),
            false);
    public static final boolean COLUMN_BITMASK_ENABLED = false;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.util.service.ServiceProps.get(
                "lock.expiration.time.com.stpl.app.model.InventoryWdActualMas"));
    private static ClassLoader _classLoader = InventoryWdActualMas.class.getClassLoader();
    private static Class<?>[] _escapedModelInterfaces = new Class[] {
            InventoryWdActualMas.class
        };
    private String _quantityOnOrder;
    private String _week;
    private String _amountOnHand;
    private int _itemMasterSid;
    private int _inventoryWdActualMasSid;
    private String _year;
    private String _itemId;
    private Date _modifiedDate;
    private String _organizationKey;
    private String _source;
    private String _createdBy;
    private Date _createdDate;
    private String _day;
    private String _unitsOnHand;
    private String _amountReceived;
    private String _itemIdentifier;
    private String _modifiedBy;
    private String _inboundStatus;
    private String _month;
    private String _amountWithdrawn;
    private String _quantityReceived;
    private String _unitsWithdrawn;
    private String _country;
    private boolean _recordLockStatus;
    private String _itemIdentifierCodeQualifier;
    private String _batchId;
    private String _amountOnOrder;
    private InventoryWdActualMas _escapedModel;

    public InventoryWdActualMasModelImpl() {
    }

    @Override
    public int getPrimaryKey() {
        return _inventoryWdActualMasSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setInventoryWdActualMasSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _inventoryWdActualMasSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Class<?> getModelClass() {
        return InventoryWdActualMas.class;
    }

    @Override
    public String getModelClassName() {
        return InventoryWdActualMas.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("quantityOnOrder", getQuantityOnOrder());
        attributes.put("week", getWeek());
        attributes.put("amountOnHand", getAmountOnHand());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("inventoryWdActualMasSid", getInventoryWdActualMasSid());
        attributes.put("year", getYear());
        attributes.put("itemId", getItemId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("source", getSource());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("day", getDay());
        attributes.put("unitsOnHand", getUnitsOnHand());
        attributes.put("amountReceived", getAmountReceived());
        attributes.put("itemIdentifier", getItemIdentifier());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("month", getMonth());
        attributes.put("amountWithdrawn", getAmountWithdrawn());
        attributes.put("quantityReceived", getQuantityReceived());
        attributes.put("unitsWithdrawn", getUnitsWithdrawn());
        attributes.put("country", getCountry());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("itemIdentifierCodeQualifier",
            getItemIdentifierCodeQualifier());
        attributes.put("batchId", getBatchId());
        attributes.put("amountOnOrder", getAmountOnOrder());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String quantityOnOrder = (String) attributes.get("quantityOnOrder");

        if (quantityOnOrder != null) {
            setQuantityOnOrder(quantityOnOrder);
        }

        String week = (String) attributes.get("week");

        if (week != null) {
            setWeek(week);
        }

        String amountOnHand = (String) attributes.get("amountOnHand");

        if (amountOnHand != null) {
            setAmountOnHand(amountOnHand);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Integer inventoryWdActualMasSid = (Integer) attributes.get(
                "inventoryWdActualMasSid");

        if (inventoryWdActualMasSid != null) {
            setInventoryWdActualMasSid(inventoryWdActualMasSid);
        }

        String year = (String) attributes.get("year");

        if (year != null) {
            setYear(year);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String organizationKey = (String) attributes.get("organizationKey");

        if (organizationKey != null) {
            setOrganizationKey(organizationKey);
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

        String day = (String) attributes.get("day");

        if (day != null) {
            setDay(day);
        }

        String unitsOnHand = (String) attributes.get("unitsOnHand");

        if (unitsOnHand != null) {
            setUnitsOnHand(unitsOnHand);
        }

        String amountReceived = (String) attributes.get("amountReceived");

        if (amountReceived != null) {
            setAmountReceived(amountReceived);
        }

        String itemIdentifier = (String) attributes.get("itemIdentifier");

        if (itemIdentifier != null) {
            setItemIdentifier(itemIdentifier);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        String month = (String) attributes.get("month");

        if (month != null) {
            setMonth(month);
        }

        String amountWithdrawn = (String) attributes.get("amountWithdrawn");

        if (amountWithdrawn != null) {
            setAmountWithdrawn(amountWithdrawn);
        }

        String quantityReceived = (String) attributes.get("quantityReceived");

        if (quantityReceived != null) {
            setQuantityReceived(quantityReceived);
        }

        String unitsWithdrawn = (String) attributes.get("unitsWithdrawn");

        if (unitsWithdrawn != null) {
            setUnitsWithdrawn(unitsWithdrawn);
        }

        String country = (String) attributes.get("country");

        if (country != null) {
            setCountry(country);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String itemIdentifierCodeQualifier = (String) attributes.get(
                "itemIdentifierCodeQualifier");

        if (itemIdentifierCodeQualifier != null) {
            setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String amountOnOrder = (String) attributes.get("amountOnOrder");

        if (amountOnOrder != null) {
            setAmountOnOrder(amountOnOrder);
        }
    }

    @Override
    public String getQuantityOnOrder() {
        if (_quantityOnOrder == null) {
            return StringPool.BLANK;
        } else {
            return _quantityOnOrder;
        }
    }

    @Override
    public void setQuantityOnOrder(String quantityOnOrder) {
        _quantityOnOrder = quantityOnOrder;
    }

    @Override
    public String getWeek() {
        if (_week == null) {
            return StringPool.BLANK;
        } else {
            return _week;
        }
    }

    @Override
    public void setWeek(String week) {
        _week = week;
    }

    @Override
    public String getAmountOnHand() {
        if (_amountOnHand == null) {
            return StringPool.BLANK;
        } else {
            return _amountOnHand;
        }
    }

    @Override
    public void setAmountOnHand(String amountOnHand) {
        _amountOnHand = amountOnHand;
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
    public int getInventoryWdActualMasSid() {
        return _inventoryWdActualMasSid;
    }

    @Override
    public void setInventoryWdActualMasSid(int inventoryWdActualMasSid) {
        _inventoryWdActualMasSid = inventoryWdActualMasSid;
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
    public Date getModifiedDate() {
        return _modifiedDate;
    }

    @Override
    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    @Override
    public String getOrganizationKey() {
        if (_organizationKey == null) {
            return StringPool.BLANK;
        } else {
            return _organizationKey;
        }
    }

    @Override
    public void setOrganizationKey(String organizationKey) {
        _organizationKey = organizationKey;
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
    public String getDay() {
        if (_day == null) {
            return StringPool.BLANK;
        } else {
            return _day;
        }
    }

    @Override
    public void setDay(String day) {
        _day = day;
    }

    @Override
    public String getUnitsOnHand() {
        if (_unitsOnHand == null) {
            return StringPool.BLANK;
        } else {
            return _unitsOnHand;
        }
    }

    @Override
    public void setUnitsOnHand(String unitsOnHand) {
        _unitsOnHand = unitsOnHand;
    }

    @Override
    public String getAmountReceived() {
        if (_amountReceived == null) {
            return StringPool.BLANK;
        } else {
            return _amountReceived;
        }
    }

    @Override
    public void setAmountReceived(String amountReceived) {
        _amountReceived = amountReceived;
    }

    @Override
    public String getItemIdentifier() {
        if (_itemIdentifier == null) {
            return StringPool.BLANK;
        } else {
            return _itemIdentifier;
        }
    }

    @Override
    public void setItemIdentifier(String itemIdentifier) {
        _itemIdentifier = itemIdentifier;
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
    public String getMonth() {
        if (_month == null) {
            return StringPool.BLANK;
        } else {
            return _month;
        }
    }

    @Override
    public void setMonth(String month) {
        _month = month;
    }

    @Override
    public String getAmountWithdrawn() {
        if (_amountWithdrawn == null) {
            return StringPool.BLANK;
        } else {
            return _amountWithdrawn;
        }
    }

    @Override
    public void setAmountWithdrawn(String amountWithdrawn) {
        _amountWithdrawn = amountWithdrawn;
    }

    @Override
    public String getQuantityReceived() {
        if (_quantityReceived == null) {
            return StringPool.BLANK;
        } else {
            return _quantityReceived;
        }
    }

    @Override
    public void setQuantityReceived(String quantityReceived) {
        _quantityReceived = quantityReceived;
    }

    @Override
    public String getUnitsWithdrawn() {
        if (_unitsWithdrawn == null) {
            return StringPool.BLANK;
        } else {
            return _unitsWithdrawn;
        }
    }

    @Override
    public void setUnitsWithdrawn(String unitsWithdrawn) {
        _unitsWithdrawn = unitsWithdrawn;
    }

    @Override
    public String getCountry() {
        if (_country == null) {
            return StringPool.BLANK;
        } else {
            return _country;
        }
    }

    @Override
    public void setCountry(String country) {
        _country = country;
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
    public String getItemIdentifierCodeQualifier() {
        if (_itemIdentifierCodeQualifier == null) {
            return StringPool.BLANK;
        } else {
            return _itemIdentifierCodeQualifier;
        }
    }

    @Override
    public void setItemIdentifierCodeQualifier(
        String itemIdentifierCodeQualifier) {
        _itemIdentifierCodeQualifier = itemIdentifierCodeQualifier;
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
    public String getAmountOnOrder() {
        if (_amountOnOrder == null) {
            return StringPool.BLANK;
        } else {
            return _amountOnOrder;
        }
    }

    @Override
    public void setAmountOnOrder(String amountOnOrder) {
        _amountOnOrder = amountOnOrder;
    }

    @Override
    public InventoryWdActualMas toEscapedModel() {
        if (_escapedModel == null) {
            _escapedModel = (InventoryWdActualMas) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelInterfaces, new AutoEscapeBeanHandler(this));
        }

        return _escapedModel;
    }

    @Override
    public Object clone() {
        InventoryWdActualMasImpl inventoryWdActualMasImpl = new InventoryWdActualMasImpl();

        inventoryWdActualMasImpl.setQuantityOnOrder(getQuantityOnOrder());
        inventoryWdActualMasImpl.setWeek(getWeek());
        inventoryWdActualMasImpl.setAmountOnHand(getAmountOnHand());
        inventoryWdActualMasImpl.setItemMasterSid(getItemMasterSid());
        inventoryWdActualMasImpl.setInventoryWdActualMasSid(getInventoryWdActualMasSid());
        inventoryWdActualMasImpl.setYear(getYear());
        inventoryWdActualMasImpl.setItemId(getItemId());
        inventoryWdActualMasImpl.setModifiedDate(getModifiedDate());
        inventoryWdActualMasImpl.setOrganizationKey(getOrganizationKey());
        inventoryWdActualMasImpl.setSource(getSource());
        inventoryWdActualMasImpl.setCreatedBy(getCreatedBy());
        inventoryWdActualMasImpl.setCreatedDate(getCreatedDate());
        inventoryWdActualMasImpl.setDay(getDay());
        inventoryWdActualMasImpl.setUnitsOnHand(getUnitsOnHand());
        inventoryWdActualMasImpl.setAmountReceived(getAmountReceived());
        inventoryWdActualMasImpl.setItemIdentifier(getItemIdentifier());
        inventoryWdActualMasImpl.setModifiedBy(getModifiedBy());
        inventoryWdActualMasImpl.setInboundStatus(getInboundStatus());
        inventoryWdActualMasImpl.setMonth(getMonth());
        inventoryWdActualMasImpl.setAmountWithdrawn(getAmountWithdrawn());
        inventoryWdActualMasImpl.setQuantityReceived(getQuantityReceived());
        inventoryWdActualMasImpl.setUnitsWithdrawn(getUnitsWithdrawn());
        inventoryWdActualMasImpl.setCountry(getCountry());
        inventoryWdActualMasImpl.setRecordLockStatus(getRecordLockStatus());
        inventoryWdActualMasImpl.setItemIdentifierCodeQualifier(getItemIdentifierCodeQualifier());
        inventoryWdActualMasImpl.setBatchId(getBatchId());
        inventoryWdActualMasImpl.setAmountOnOrder(getAmountOnOrder());

        inventoryWdActualMasImpl.resetOriginalValues();

        return inventoryWdActualMasImpl;
    }

    @Override
    public int compareTo(InventoryWdActualMas inventoryWdActualMas) {
        int primaryKey = inventoryWdActualMas.getPrimaryKey();

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

        if (!(obj instanceof InventoryWdActualMas)) {
            return false;
        }

        InventoryWdActualMas inventoryWdActualMas = (InventoryWdActualMas) obj;

        int primaryKey = inventoryWdActualMas.getPrimaryKey();

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
    public CacheModel<InventoryWdActualMas> toCacheModel() {
        InventoryWdActualMasCacheModel inventoryWdActualMasCacheModel = new InventoryWdActualMasCacheModel();

        inventoryWdActualMasCacheModel.quantityOnOrder = getQuantityOnOrder();

        String quantityOnOrder = inventoryWdActualMasCacheModel.quantityOnOrder;

        if ((quantityOnOrder != null) && (quantityOnOrder.length() == 0)) {
            inventoryWdActualMasCacheModel.quantityOnOrder = null;
        }

        inventoryWdActualMasCacheModel.week = getWeek();

        String week = inventoryWdActualMasCacheModel.week;

        if ((week != null) && (week.length() == 0)) {
            inventoryWdActualMasCacheModel.week = null;
        }

        inventoryWdActualMasCacheModel.amountOnHand = getAmountOnHand();

        String amountOnHand = inventoryWdActualMasCacheModel.amountOnHand;

        if ((amountOnHand != null) && (amountOnHand.length() == 0)) {
            inventoryWdActualMasCacheModel.amountOnHand = null;
        }

        inventoryWdActualMasCacheModel.itemMasterSid = getItemMasterSid();

        inventoryWdActualMasCacheModel.inventoryWdActualMasSid = getInventoryWdActualMasSid();

        inventoryWdActualMasCacheModel.year = getYear();

        String year = inventoryWdActualMasCacheModel.year;

        if ((year != null) && (year.length() == 0)) {
            inventoryWdActualMasCacheModel.year = null;
        }

        inventoryWdActualMasCacheModel.itemId = getItemId();

        String itemId = inventoryWdActualMasCacheModel.itemId;

        if ((itemId != null) && (itemId.length() == 0)) {
            inventoryWdActualMasCacheModel.itemId = null;
        }

        Date modifiedDate = getModifiedDate();

        if (modifiedDate != null) {
            inventoryWdActualMasCacheModel.modifiedDate = modifiedDate.getTime();
        } else {
            inventoryWdActualMasCacheModel.modifiedDate = Long.MIN_VALUE;
        }

        inventoryWdActualMasCacheModel.organizationKey = getOrganizationKey();

        String organizationKey = inventoryWdActualMasCacheModel.organizationKey;

        if ((organizationKey != null) && (organizationKey.length() == 0)) {
            inventoryWdActualMasCacheModel.organizationKey = null;
        }

        inventoryWdActualMasCacheModel.source = getSource();

        String source = inventoryWdActualMasCacheModel.source;

        if ((source != null) && (source.length() == 0)) {
            inventoryWdActualMasCacheModel.source = null;
        }

        inventoryWdActualMasCacheModel.createdBy = getCreatedBy();

        String createdBy = inventoryWdActualMasCacheModel.createdBy;

        if ((createdBy != null) && (createdBy.length() == 0)) {
            inventoryWdActualMasCacheModel.createdBy = null;
        }

        Date createdDate = getCreatedDate();

        if (createdDate != null) {
            inventoryWdActualMasCacheModel.createdDate = createdDate.getTime();
        } else {
            inventoryWdActualMasCacheModel.createdDate = Long.MIN_VALUE;
        }

        inventoryWdActualMasCacheModel.day = getDay();

        String day = inventoryWdActualMasCacheModel.day;

        if ((day != null) && (day.length() == 0)) {
            inventoryWdActualMasCacheModel.day = null;
        }

        inventoryWdActualMasCacheModel.unitsOnHand = getUnitsOnHand();

        String unitsOnHand = inventoryWdActualMasCacheModel.unitsOnHand;

        if ((unitsOnHand != null) && (unitsOnHand.length() == 0)) {
            inventoryWdActualMasCacheModel.unitsOnHand = null;
        }

        inventoryWdActualMasCacheModel.amountReceived = getAmountReceived();

        String amountReceived = inventoryWdActualMasCacheModel.amountReceived;

        if ((amountReceived != null) && (amountReceived.length() == 0)) {
            inventoryWdActualMasCacheModel.amountReceived = null;
        }

        inventoryWdActualMasCacheModel.itemIdentifier = getItemIdentifier();

        String itemIdentifier = inventoryWdActualMasCacheModel.itemIdentifier;

        if ((itemIdentifier != null) && (itemIdentifier.length() == 0)) {
            inventoryWdActualMasCacheModel.itemIdentifier = null;
        }

        inventoryWdActualMasCacheModel.modifiedBy = getModifiedBy();

        String modifiedBy = inventoryWdActualMasCacheModel.modifiedBy;

        if ((modifiedBy != null) && (modifiedBy.length() == 0)) {
            inventoryWdActualMasCacheModel.modifiedBy = null;
        }

        inventoryWdActualMasCacheModel.inboundStatus = getInboundStatus();

        String inboundStatus = inventoryWdActualMasCacheModel.inboundStatus;

        if ((inboundStatus != null) && (inboundStatus.length() == 0)) {
            inventoryWdActualMasCacheModel.inboundStatus = null;
        }

        inventoryWdActualMasCacheModel.month = getMonth();

        String month = inventoryWdActualMasCacheModel.month;

        if ((month != null) && (month.length() == 0)) {
            inventoryWdActualMasCacheModel.month = null;
        }

        inventoryWdActualMasCacheModel.amountWithdrawn = getAmountWithdrawn();

        String amountWithdrawn = inventoryWdActualMasCacheModel.amountWithdrawn;

        if ((amountWithdrawn != null) && (amountWithdrawn.length() == 0)) {
            inventoryWdActualMasCacheModel.amountWithdrawn = null;
        }

        inventoryWdActualMasCacheModel.quantityReceived = getQuantityReceived();

        String quantityReceived = inventoryWdActualMasCacheModel.quantityReceived;

        if ((quantityReceived != null) && (quantityReceived.length() == 0)) {
            inventoryWdActualMasCacheModel.quantityReceived = null;
        }

        inventoryWdActualMasCacheModel.unitsWithdrawn = getUnitsWithdrawn();

        String unitsWithdrawn = inventoryWdActualMasCacheModel.unitsWithdrawn;

        if ((unitsWithdrawn != null) && (unitsWithdrawn.length() == 0)) {
            inventoryWdActualMasCacheModel.unitsWithdrawn = null;
        }

        inventoryWdActualMasCacheModel.country = getCountry();

        String country = inventoryWdActualMasCacheModel.country;

        if ((country != null) && (country.length() == 0)) {
            inventoryWdActualMasCacheModel.country = null;
        }

        inventoryWdActualMasCacheModel.recordLockStatus = getRecordLockStatus();

        inventoryWdActualMasCacheModel.itemIdentifierCodeQualifier = getItemIdentifierCodeQualifier();

        String itemIdentifierCodeQualifier = inventoryWdActualMasCacheModel.itemIdentifierCodeQualifier;

        if ((itemIdentifierCodeQualifier != null) &&
                (itemIdentifierCodeQualifier.length() == 0)) {
            inventoryWdActualMasCacheModel.itemIdentifierCodeQualifier = null;
        }

        inventoryWdActualMasCacheModel.batchId = getBatchId();

        String batchId = inventoryWdActualMasCacheModel.batchId;

        if ((batchId != null) && (batchId.length() == 0)) {
            inventoryWdActualMasCacheModel.batchId = null;
        }

        inventoryWdActualMasCacheModel.amountOnOrder = getAmountOnOrder();

        String amountOnOrder = inventoryWdActualMasCacheModel.amountOnOrder;

        if ((amountOnOrder != null) && (amountOnOrder.length() == 0)) {
            inventoryWdActualMasCacheModel.amountOnOrder = null;
        }

        return inventoryWdActualMasCacheModel;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(55);

        sb.append("{quantityOnOrder=");
        sb.append(getQuantityOnOrder());
        sb.append(", week=");
        sb.append(getWeek());
        sb.append(", amountOnHand=");
        sb.append(getAmountOnHand());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", inventoryWdActualMasSid=");
        sb.append(getInventoryWdActualMasSid());
        sb.append(", year=");
        sb.append(getYear());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", organizationKey=");
        sb.append(getOrganizationKey());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", day=");
        sb.append(getDay());
        sb.append(", unitsOnHand=");
        sb.append(getUnitsOnHand());
        sb.append(", amountReceived=");
        sb.append(getAmountReceived());
        sb.append(", itemIdentifier=");
        sb.append(getItemIdentifier());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", month=");
        sb.append(getMonth());
        sb.append(", amountWithdrawn=");
        sb.append(getAmountWithdrawn());
        sb.append(", quantityReceived=");
        sb.append(getQuantityReceived());
        sb.append(", unitsWithdrawn=");
        sb.append(getUnitsWithdrawn());
        sb.append(", country=");
        sb.append(getCountry());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", itemIdentifierCodeQualifier=");
        sb.append(getItemIdentifierCodeQualifier());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", amountOnOrder=");
        sb.append(getAmountOnOrder());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(85);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.InventoryWdActualMas");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>quantityOnOrder</column-name><column-value><![CDATA[");
        sb.append(getQuantityOnOrder());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>week</column-name><column-value><![CDATA[");
        sb.append(getWeek());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>amountOnHand</column-name><column-value><![CDATA[");
        sb.append(getAmountOnHand());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inventoryWdActualMasSid</column-name><column-value><![CDATA[");
        sb.append(getInventoryWdActualMasSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>year</column-name><column-value><![CDATA[");
        sb.append(getYear());
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
            "<column><column-name>organizationKey</column-name><column-value><![CDATA[");
        sb.append(getOrganizationKey());
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
            "<column><column-name>day</column-name><column-value><![CDATA[");
        sb.append(getDay());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>unitsOnHand</column-name><column-value><![CDATA[");
        sb.append(getUnitsOnHand());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>amountReceived</column-name><column-value><![CDATA[");
        sb.append(getAmountReceived());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemIdentifier</column-name><column-value><![CDATA[");
        sb.append(getItemIdentifier());
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
            "<column><column-name>month</column-name><column-value><![CDATA[");
        sb.append(getMonth());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>amountWithdrawn</column-name><column-value><![CDATA[");
        sb.append(getAmountWithdrawn());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>quantityReceived</column-name><column-value><![CDATA[");
        sb.append(getQuantityReceived());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>unitsWithdrawn</column-name><column-value><![CDATA[");
        sb.append(getUnitsWithdrawn());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>country</column-name><column-value><![CDATA[");
        sb.append(getCountry());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemIdentifierCodeQualifier</column-name><column-value><![CDATA[");
        sb.append(getItemIdentifierCodeQualifier());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>amountOnOrder</column-name><column-value><![CDATA[");
        sb.append(getAmountOnOrder());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
