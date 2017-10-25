package com.stpl.app.model.impl;

import com.stpl.app.model.HelperTable;
import com.stpl.app.model.HelperTableModel;

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
 * The base model implementation for the HelperTable service. Represents a row in the &quot;HELPER_TABLE&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.stpl.app.model.HelperTableModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link HelperTableImpl}.
 * </p>
 *
 * @author
 * @see HelperTableImpl
 * @see com.stpl.app.model.HelperTable
 * @see com.stpl.app.model.HelperTableModel
 * @generated
 */
public class HelperTableModelImpl extends BaseModelImpl<HelperTable>
    implements HelperTableModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a helper table model instance should use the {@link com.stpl.app.model.HelperTable} interface instead.
     */
    public static final String TABLE_NAME = "HELPER_TABLE";
    public static final Object[][] TABLE_COLUMNS = {
            { "CREATED_DATE", Types.TIMESTAMP },
            { "CREATED_BY", Types.INTEGER },
            { "DESCRIPTION", Types.VARCHAR },
            { "REF_COUNT", Types.INTEGER },
            { "LIST_NAME", Types.VARCHAR },
            { "HELPER_TABLE_SID", Types.INTEGER },
            { "MODIFIED_BY", Types.INTEGER },
            { "MODIFIED_DATE", Types.TIMESTAMP }
        };
    public static final String TABLE_SQL_CREATE = "create table HELPER_TABLE (CREATED_DATE DATE null,CREATED_BY INTEGER,DESCRIPTION VARCHAR(75) null,REF_COUNT INTEGER,LIST_NAME VARCHAR(75) null,HELPER_TABLE_SID INTEGER not null primary key IDENTITY,MODIFIED_BY INTEGER,MODIFIED_DATE DATE null)";
    public static final String TABLE_SQL_DROP = "drop table HELPER_TABLE";
    public static final String ORDER_BY_JPQL = " ORDER BY helperTable.helperTableSid ASC";
    public static final String ORDER_BY_SQL = " ORDER BY HELPER_TABLE.HELPER_TABLE_SID ASC";
    public static final String DATA_SOURCE = "appDataSource";
    public static final String SESSION_FACTORY = "appSessionFactory";
    public static final String TX_MANAGER = "appTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.entity.cache.enabled.com.stpl.app.model.HelperTable"),
            false);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.finder.cache.enabled.com.stpl.app.model.HelperTable"),
            false);
    public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.column.bitmask.enabled.com.stpl.app.model.HelperTable"),
            true);
    public static long DESCRIPTION_COLUMN_BITMASK = 1L;
    public static long HELPERTABLESID_COLUMN_BITMASK = 2L;
    public static long LISTNAME_COLUMN_BITMASK = 4L;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.util.service.ServiceProps.get(
                "lock.expiration.time.com.stpl.app.model.HelperTable"));
    private static ClassLoader _classLoader = HelperTable.class.getClassLoader();
    private static Class<?>[] _escapedModelInterfaces = new Class[] {
            HelperTable.class
        };
    private Date _createdDate;
    private int _createdBy;
    private String _description;
    private String _originalDescription;
    private int _refCount;
    private String _listName;
    private String _originalListName;
    private int _helperTableSid;
    private int _originalHelperTableSid;
    private boolean _setOriginalHelperTableSid;
    private int _modifiedBy;
    private Date _modifiedDate;
    private long _columnBitmask;
    private HelperTable _escapedModel;

    public HelperTableModelImpl() {
    }

    @Override
    public int getPrimaryKey() {
        return _helperTableSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setHelperTableSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _helperTableSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Class<?> getModelClass() {
        return HelperTable.class;
    }

    @Override
    public String getModelClassName() {
        return HelperTable.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("description", getDescription());
        attributes.put("refCount", getRefCount());
        attributes.put("listName", getListName());
        attributes.put("helperTableSid", getHelperTableSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("modifiedDate", getModifiedDate());

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

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }

        Integer refCount = (Integer) attributes.get("refCount");

        if (refCount != null) {
            setRefCount(refCount);
        }

        String listName = (String) attributes.get("listName");

        if (listName != null) {
            setListName(listName);
        }

        Integer helperTableSid = (Integer) attributes.get("helperTableSid");

        if (helperTableSid != null) {
            setHelperTableSid(helperTableSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
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
    public String getDescription() {
        if (_description == null) {
            return StringPool.BLANK;
        } else {
            return _description;
        }
    }

    @Override
    public void setDescription(String description) {
        _columnBitmask |= DESCRIPTION_COLUMN_BITMASK;

        if (_originalDescription == null) {
            _originalDescription = _description;
        }

        _description = description;
    }

    public String getOriginalDescription() {
        return GetterUtil.getString(_originalDescription);
    }

    @Override
    public int getRefCount() {
        return _refCount;
    }

    @Override
    public void setRefCount(int refCount) {
        _refCount = refCount;
    }

    @Override
    public String getListName() {
        if (_listName == null) {
            return StringPool.BLANK;
        } else {
            return _listName;
        }
    }

    @Override
    public void setListName(String listName) {
        _columnBitmask |= LISTNAME_COLUMN_BITMASK;

        if (_originalListName == null) {
            _originalListName = _listName;
        }

        _listName = listName;
    }

    public String getOriginalListName() {
        return GetterUtil.getString(_originalListName);
    }

    @Override
    public int getHelperTableSid() {
        return _helperTableSid;
    }

    @Override
    public void setHelperTableSid(int helperTableSid) {
        _columnBitmask |= HELPERTABLESID_COLUMN_BITMASK;

        if (!_setOriginalHelperTableSid) {
            _setOriginalHelperTableSid = true;

            _originalHelperTableSid = _helperTableSid;
        }

        _helperTableSid = helperTableSid;
    }

    public int getOriginalHelperTableSid() {
        return _originalHelperTableSid;
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
    public Date getModifiedDate() {
        return _modifiedDate;
    }

    @Override
    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public long getColumnBitmask() {
        return _columnBitmask;
    }

    @Override
    public HelperTable toEscapedModel() {
        if (_escapedModel == null) {
            _escapedModel = (HelperTable) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelInterfaces, new AutoEscapeBeanHandler(this));
        }

        return _escapedModel;
    }

    @Override
    public Object clone() {
        HelperTableImpl helperTableImpl = new HelperTableImpl();

        helperTableImpl.setCreatedDate(getCreatedDate());
        helperTableImpl.setCreatedBy(getCreatedBy());
        helperTableImpl.setDescription(getDescription());
        helperTableImpl.setRefCount(getRefCount());
        helperTableImpl.setListName(getListName());
        helperTableImpl.setHelperTableSid(getHelperTableSid());
        helperTableImpl.setModifiedBy(getModifiedBy());
        helperTableImpl.setModifiedDate(getModifiedDate());

        helperTableImpl.resetOriginalValues();

        return helperTableImpl;
    }

    @Override
    public int compareTo(HelperTable helperTable) {
        int primaryKey = helperTable.getPrimaryKey();

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

        if (!(obj instanceof HelperTable)) {
            return false;
        }

        HelperTable helperTable = (HelperTable) obj;

        int primaryKey = helperTable.getPrimaryKey();

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
        HelperTableModelImpl helperTableModelImpl = this;

        helperTableModelImpl._originalDescription = helperTableModelImpl._description;

        helperTableModelImpl._originalListName = helperTableModelImpl._listName;

        helperTableModelImpl._originalHelperTableSid = helperTableModelImpl._helperTableSid;

        helperTableModelImpl._setOriginalHelperTableSid = false;

        helperTableModelImpl._columnBitmask = 0;
    }

    @Override
    public CacheModel<HelperTable> toCacheModel() {
        HelperTableCacheModel helperTableCacheModel = new HelperTableCacheModel();

        Date createdDate = getCreatedDate();

        if (createdDate != null) {
            helperTableCacheModel.createdDate = createdDate.getTime();
        } else {
            helperTableCacheModel.createdDate = Long.MIN_VALUE;
        }

        helperTableCacheModel.createdBy = getCreatedBy();

        helperTableCacheModel.description = getDescription();

        String description = helperTableCacheModel.description;

        if ((description != null) && (description.length() == 0)) {
            helperTableCacheModel.description = null;
        }

        helperTableCacheModel.refCount = getRefCount();

        helperTableCacheModel.listName = getListName();

        String listName = helperTableCacheModel.listName;

        if ((listName != null) && (listName.length() == 0)) {
            helperTableCacheModel.listName = null;
        }

        helperTableCacheModel.helperTableSid = getHelperTableSid();

        helperTableCacheModel.modifiedBy = getModifiedBy();

        Date modifiedDate = getModifiedDate();

        if (modifiedDate != null) {
            helperTableCacheModel.modifiedDate = modifiedDate.getTime();
        } else {
            helperTableCacheModel.modifiedDate = Long.MIN_VALUE;
        }

        return helperTableCacheModel;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(17);

        sb.append("{createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", refCount=");
        sb.append(getRefCount());
        sb.append(", listName=");
        sb.append(getListName());
        sb.append(", helperTableSid=");
        sb.append(getHelperTableSid());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(28);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.HelperTable");
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
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>refCount</column-name><column-value><![CDATA[");
        sb.append(getRefCount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>listName</column-name><column-value><![CDATA[");
        sb.append(getListName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>helperTableSid</column-name><column-value><![CDATA[");
        sb.append(getHelperTableSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
