package com.stpl.app.model.impl;

import com.stpl.app.model.ItemGroup;
import com.stpl.app.model.ItemGroupModel;

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
 * The base model implementation for the ItemGroup service. Represents a row in the &quot;ITEM_GROUP&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.stpl.app.model.ItemGroupModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ItemGroupImpl}.
 * </p>
 *
 * @author
 * @see ItemGroupImpl
 * @see com.stpl.app.model.ItemGroup
 * @see com.stpl.app.model.ItemGroupModel
 * @generated
 */
public class ItemGroupModelImpl extends BaseModelImpl<ItemGroup>
    implements ItemGroupModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a item group model instance should use the {@link com.stpl.app.model.ItemGroup} interface instead.
     */
    public static final String TABLE_NAME = "ITEM_GROUP";
    public static final Object[][] TABLE_COLUMNS = {
            { "CREATED_DATE", Types.TIMESTAMP },
            { "CREATED_BY", Types.INTEGER },
            { "ITEM_GROUP_NO", Types.VARCHAR },
            { "VERSION_NO", Types.INTEGER },
            { "ITEM_FILTER", Types.VARCHAR },
            { "MODIFIED_BY", Types.INTEGER },
            { "ITEM_GROUP_DESCRIPTION", Types.VARCHAR },
            { "MODIFIED_DATE", Types.TIMESTAMP },
            { "ITEM_GROUP_NAME", Types.VARCHAR },
            { "ITEM_GROUP_SID", Types.INTEGER },
            { "COMPANY_MASTER_SID", Types.INTEGER }
        };
    public static final String TABLE_SQL_CREATE = "create table ITEM_GROUP (CREATED_DATE DATE null,CREATED_BY INTEGER,ITEM_GROUP_NO VARCHAR(75) null,VERSION_NO INTEGER,ITEM_FILTER VARCHAR(75) null,MODIFIED_BY INTEGER,ITEM_GROUP_DESCRIPTION VARCHAR(75) null,MODIFIED_DATE DATE null,ITEM_GROUP_NAME VARCHAR(75) null,ITEM_GROUP_SID INTEGER not null primary key IDENTITY,COMPANY_MASTER_SID INTEGER)";
    public static final String TABLE_SQL_DROP = "drop table ITEM_GROUP";
    public static final String ORDER_BY_JPQL = " ORDER BY itemGroup.itemGroupSid ASC";
    public static final String ORDER_BY_SQL = " ORDER BY ITEM_GROUP.ITEM_GROUP_SID ASC";
    public static final String DATA_SOURCE = "appDataSource";
    public static final String SESSION_FACTORY = "appSessionFactory";
    public static final String TX_MANAGER = "appTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.entity.cache.enabled.com.stpl.app.model.ItemGroup"),
            false);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.finder.cache.enabled.com.stpl.app.model.ItemGroup"),
            false);
    public static final boolean COLUMN_BITMASK_ENABLED = false;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.util.service.ServiceProps.get(
                "lock.expiration.time.com.stpl.app.model.ItemGroup"));
    private static ClassLoader _classLoader = ItemGroup.class.getClassLoader();
    private static Class<?>[] _escapedModelInterfaces = new Class[] {
            ItemGroup.class
        };
    private Date _createdDate;
    private int _createdBy;
    private String _itemGroupNo;
    private int _versionNo;
    private String _itemFilter;
    private int _modifiedBy;
    private String _itemGroupDescription;
    private Date _modifiedDate;
    private String _itemGroupName;
    private int _itemGroupSid;
    private int _companyMasterSid;
    private ItemGroup _escapedModel;

    public ItemGroupModelImpl() {
    }

    @Override
    public int getPrimaryKey() {
        return _itemGroupSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setItemGroupSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _itemGroupSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Class<?> getModelClass() {
        return ItemGroup.class;
    }

    @Override
    public String getModelClassName() {
        return ItemGroup.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("itemGroupNo", getItemGroupNo());
        attributes.put("versionNo", getVersionNo());
        attributes.put("itemFilter", getItemFilter());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("itemGroupDescription", getItemGroupDescription());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("itemGroupName", getItemGroupName());
        attributes.put("itemGroupSid", getItemGroupSid());
        attributes.put("companyMasterSid", getCompanyMasterSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String itemGroupNo = (String) attributes.get("itemGroupNo");

        if (itemGroupNo != null) {
            setItemGroupNo(itemGroupNo);
        }

        Integer versionNo = (Integer) attributes.get("versionNo");

        if (versionNo != null) {
            setVersionNo(versionNo);
        }

        String itemFilter = (String) attributes.get("itemFilter");

        if (itemFilter != null) {
            setItemFilter(itemFilter);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String itemGroupDescription = (String) attributes.get(
                "itemGroupDescription");

        if (itemGroupDescription != null) {
            setItemGroupDescription(itemGroupDescription);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String itemGroupName = (String) attributes.get("itemGroupName");

        if (itemGroupName != null) {
            setItemGroupName(itemGroupName);
        }

        Integer itemGroupSid = (Integer) attributes.get("itemGroupSid");

        if (itemGroupSid != null) {
            setItemGroupSid(itemGroupSid);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }
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
    public String getItemGroupNo() {
        if (_itemGroupNo == null) {
            return StringPool.BLANK;
        } else {
            return _itemGroupNo;
        }
    }

    @Override
    public void setItemGroupNo(String itemGroupNo) {
        _itemGroupNo = itemGroupNo;
    }

    @Override
    public int getVersionNo() {
        return _versionNo;
    }

    @Override
    public void setVersionNo(int versionNo) {
        _versionNo = versionNo;
    }

    @Override
    public String getItemFilter() {
        if (_itemFilter == null) {
            return StringPool.BLANK;
        } else {
            return _itemFilter;
        }
    }

    @Override
    public void setItemFilter(String itemFilter) {
        _itemFilter = itemFilter;
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
    public String getItemGroupDescription() {
        if (_itemGroupDescription == null) {
            return StringPool.BLANK;
        } else {
            return _itemGroupDescription;
        }
    }

    @Override
    public void setItemGroupDescription(String itemGroupDescription) {
        _itemGroupDescription = itemGroupDescription;
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
    public String getItemGroupName() {
        if (_itemGroupName == null) {
            return StringPool.BLANK;
        } else {
            return _itemGroupName;
        }
    }

    @Override
    public void setItemGroupName(String itemGroupName) {
        _itemGroupName = itemGroupName;
    }

    @Override
    public int getItemGroupSid() {
        return _itemGroupSid;
    }

    @Override
    public void setItemGroupSid(int itemGroupSid) {
        _itemGroupSid = itemGroupSid;
    }

    @Override
    public int getCompanyMasterSid() {
        return _companyMasterSid;
    }

    @Override
    public void setCompanyMasterSid(int companyMasterSid) {
        _companyMasterSid = companyMasterSid;
    }

    @Override
    public ItemGroup toEscapedModel() {
        if (_escapedModel == null) {
            _escapedModel = (ItemGroup) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelInterfaces, new AutoEscapeBeanHandler(this));
        }

        return _escapedModel;
    }

    @Override
    public Object clone() {
        ItemGroupImpl itemGroupImpl = new ItemGroupImpl();

        itemGroupImpl.setCreatedDate(getCreatedDate());
        itemGroupImpl.setCreatedBy(getCreatedBy());
        itemGroupImpl.setItemGroupNo(getItemGroupNo());
        itemGroupImpl.setVersionNo(getVersionNo());
        itemGroupImpl.setItemFilter(getItemFilter());
        itemGroupImpl.setModifiedBy(getModifiedBy());
        itemGroupImpl.setItemGroupDescription(getItemGroupDescription());
        itemGroupImpl.setModifiedDate(getModifiedDate());
        itemGroupImpl.setItemGroupName(getItemGroupName());
        itemGroupImpl.setItemGroupSid(getItemGroupSid());
        itemGroupImpl.setCompanyMasterSid(getCompanyMasterSid());

        itemGroupImpl.resetOriginalValues();

        return itemGroupImpl;
    }

    @Override
    public int compareTo(ItemGroup itemGroup) {
        int primaryKey = itemGroup.getPrimaryKey();

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

        if (!(obj instanceof ItemGroup)) {
            return false;
        }

        ItemGroup itemGroup = (ItemGroup) obj;

        int primaryKey = itemGroup.getPrimaryKey();

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
    public CacheModel<ItemGroup> toCacheModel() {
        ItemGroupCacheModel itemGroupCacheModel = new ItemGroupCacheModel();

        Date createdDate = getCreatedDate();

        if (createdDate != null) {
            itemGroupCacheModel.createdDate = createdDate.getTime();
        } else {
            itemGroupCacheModel.createdDate = Long.MIN_VALUE;
        }

        itemGroupCacheModel.createdBy = getCreatedBy();

        itemGroupCacheModel.itemGroupNo = getItemGroupNo();

        String itemGroupNo = itemGroupCacheModel.itemGroupNo;

        if ((itemGroupNo != null) && (itemGroupNo.length() == 0)) {
            itemGroupCacheModel.itemGroupNo = null;
        }

        itemGroupCacheModel.versionNo = getVersionNo();

        itemGroupCacheModel.itemFilter = getItemFilter();

        String itemFilter = itemGroupCacheModel.itemFilter;

        if ((itemFilter != null) && (itemFilter.length() == 0)) {
            itemGroupCacheModel.itemFilter = null;
        }

        itemGroupCacheModel.modifiedBy = getModifiedBy();

        itemGroupCacheModel.itemGroupDescription = getItemGroupDescription();

        String itemGroupDescription = itemGroupCacheModel.itemGroupDescription;

        if ((itemGroupDescription != null) &&
                (itemGroupDescription.length() == 0)) {
            itemGroupCacheModel.itemGroupDescription = null;
        }

        Date modifiedDate = getModifiedDate();

        if (modifiedDate != null) {
            itemGroupCacheModel.modifiedDate = modifiedDate.getTime();
        } else {
            itemGroupCacheModel.modifiedDate = Long.MIN_VALUE;
        }

        itemGroupCacheModel.itemGroupName = getItemGroupName();

        String itemGroupName = itemGroupCacheModel.itemGroupName;

        if ((itemGroupName != null) && (itemGroupName.length() == 0)) {
            itemGroupCacheModel.itemGroupName = null;
        }

        itemGroupCacheModel.itemGroupSid = getItemGroupSid();

        itemGroupCacheModel.companyMasterSid = getCompanyMasterSid();

        return itemGroupCacheModel;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(23);

        sb.append("{createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", itemGroupNo=");
        sb.append(getItemGroupNo());
        sb.append(", versionNo=");
        sb.append(getVersionNo());
        sb.append(", itemFilter=");
        sb.append(getItemFilter());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", itemGroupDescription=");
        sb.append(getItemGroupDescription());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", itemGroupName=");
        sb.append(getItemGroupName());
        sb.append(", itemGroupSid=");
        sb.append(getItemGroupSid());
        sb.append(", companyMasterSid=");
        sb.append(getCompanyMasterSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(37);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ItemGroup");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemGroupNo</column-name><column-value><![CDATA[");
        sb.append(getItemGroupNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>versionNo</column-name><column-value><![CDATA[");
        sb.append(getVersionNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemFilter</column-name><column-value><![CDATA[");
        sb.append(getItemFilter());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemGroupDescription</column-name><column-value><![CDATA[");
        sb.append(getItemGroupDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemGroupName</column-name><column-value><![CDATA[");
        sb.append(getItemGroupName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemGroupSid</column-name><column-value><![CDATA[");
        sb.append(getItemGroupSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyMasterSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
