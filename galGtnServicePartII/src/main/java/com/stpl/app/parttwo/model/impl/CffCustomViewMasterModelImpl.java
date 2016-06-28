package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.CffCustomViewMaster;
import com.stpl.app.parttwo.model.CffCustomViewMasterModel;

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
 * The base model implementation for the CffCustomViewMaster service. Represents a row in the &quot;CFF_CUSTOM_VIEW_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.stpl.app.parttwo.model.CffCustomViewMasterModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CffCustomViewMasterImpl}.
 * </p>
 *
 * @author
 * @see CffCustomViewMasterImpl
 * @see com.stpl.app.parttwo.model.CffCustomViewMaster
 * @see com.stpl.app.parttwo.model.CffCustomViewMasterModel
 * @generated
 */
public class CffCustomViewMasterModelImpl extends BaseModelImpl<CffCustomViewMaster>
    implements CffCustomViewMasterModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a cff custom view master model instance should use the {@link com.stpl.app.parttwo.model.CffCustomViewMaster} interface instead.
     */
    public static final String TABLE_NAME = "CFF_CUSTOM_VIEW_MASTER";
    public static final Object[][] TABLE_COLUMNS = {
            { "CREATED_DATE", Types.TIMESTAMP },
            { "CREATED_BY", Types.INTEGER },
            { "CFF_MASTER_SID", Types.INTEGER },
            { "MODIFIED_BY", Types.INTEGER },
            { "CFF_CUSTOM_VIEW_MASTER_SID", Types.INTEGER },
            { "MODIFIED_DATE", Types.TIMESTAMP },
            { "VIEW_NAME", Types.VARCHAR }
        };
    public static final String TABLE_SQL_CREATE = "create table CFF_CUSTOM_VIEW_MASTER (CREATED_DATE DATE null,CREATED_BY INTEGER,CFF_MASTER_SID INTEGER,MODIFIED_BY INTEGER,CFF_CUSTOM_VIEW_MASTER_SID INTEGER not null primary key IDENTITY,MODIFIED_DATE DATE null,VIEW_NAME VARCHAR(75) null)";
    public static final String TABLE_SQL_DROP = "drop table CFF_CUSTOM_VIEW_MASTER";
    public static final String ORDER_BY_JPQL = " ORDER BY cffCustomViewMaster.cffCustomViewMasterSid ASC";
    public static final String ORDER_BY_SQL = " ORDER BY CFF_CUSTOM_VIEW_MASTER.CFF_CUSTOM_VIEW_MASTER_SID ASC";
    public static final String DATA_SOURCE = "appDataSource";
    public static final String SESSION_FACTORY = "appSessionFactory";
    public static final String TX_MANAGER = "appTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.entity.cache.enabled.com.stpl.app.parttwo.model.CffCustomViewMaster"),
            false);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.finder.cache.enabled.com.stpl.app.parttwo.model.CffCustomViewMaster"),
            false);
    public static final boolean COLUMN_BITMASK_ENABLED = false;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.util.service.ServiceProps.get(
                "lock.expiration.time.com.stpl.app.parttwo.model.CffCustomViewMaster"));
    private static ClassLoader _classLoader = CffCustomViewMaster.class.getClassLoader();
    private static Class<?>[] _escapedModelInterfaces = new Class[] {
            CffCustomViewMaster.class
        };
    private Date _createdDate;
    private int _createdBy;
    private int _cffMasterSid;
    private int _modifiedBy;
    private int _cffCustomViewMasterSid;
    private Date _modifiedDate;
    private String _viewName;
    private CffCustomViewMaster _escapedModel;

    public CffCustomViewMasterModelImpl() {
    }

    @Override
    public int getPrimaryKey() {
        return _cffCustomViewMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setCffCustomViewMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _cffCustomViewMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Class<?> getModelClass() {
        return CffCustomViewMaster.class;
    }

    @Override
    public String getModelClassName() {
        return CffCustomViewMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("cffMasterSid", getCffMasterSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("cffCustomViewMasterSid", getCffCustomViewMasterSid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("viewName", getViewName());

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

        Integer cffMasterSid = (Integer) attributes.get("cffMasterSid");

        if (cffMasterSid != null) {
            setCffMasterSid(cffMasterSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Integer cffCustomViewMasterSid = (Integer) attributes.get(
                "cffCustomViewMasterSid");

        if (cffCustomViewMasterSid != null) {
            setCffCustomViewMasterSid(cffCustomViewMasterSid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String viewName = (String) attributes.get("viewName");

        if (viewName != null) {
            setViewName(viewName);
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
    public int getCffMasterSid() {
        return _cffMasterSid;
    }

    @Override
    public void setCffMasterSid(int cffMasterSid) {
        _cffMasterSid = cffMasterSid;
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
    public int getCffCustomViewMasterSid() {
        return _cffCustomViewMasterSid;
    }

    @Override
    public void setCffCustomViewMasterSid(int cffCustomViewMasterSid) {
        _cffCustomViewMasterSid = cffCustomViewMasterSid;
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
    public String getViewName() {
        if (_viewName == null) {
            return StringPool.BLANK;
        } else {
            return _viewName;
        }
    }

    @Override
    public void setViewName(String viewName) {
        _viewName = viewName;
    }

    @Override
    public CffCustomViewMaster toEscapedModel() {
        if (_escapedModel == null) {
            _escapedModel = (CffCustomViewMaster) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelInterfaces, new AutoEscapeBeanHandler(this));
        }

        return _escapedModel;
    }

    @Override
    public Object clone() {
        CffCustomViewMasterImpl cffCustomViewMasterImpl = new CffCustomViewMasterImpl();

        cffCustomViewMasterImpl.setCreatedDate(getCreatedDate());
        cffCustomViewMasterImpl.setCreatedBy(getCreatedBy());
        cffCustomViewMasterImpl.setCffMasterSid(getCffMasterSid());
        cffCustomViewMasterImpl.setModifiedBy(getModifiedBy());
        cffCustomViewMasterImpl.setCffCustomViewMasterSid(getCffCustomViewMasterSid());
        cffCustomViewMasterImpl.setModifiedDate(getModifiedDate());
        cffCustomViewMasterImpl.setViewName(getViewName());

        cffCustomViewMasterImpl.resetOriginalValues();

        return cffCustomViewMasterImpl;
    }

    @Override
    public int compareTo(CffCustomViewMaster cffCustomViewMaster) {
        int primaryKey = cffCustomViewMaster.getPrimaryKey();

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

        if (!(obj instanceof CffCustomViewMaster)) {
            return false;
        }

        CffCustomViewMaster cffCustomViewMaster = (CffCustomViewMaster) obj;

        int primaryKey = cffCustomViewMaster.getPrimaryKey();

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
    public CacheModel<CffCustomViewMaster> toCacheModel() {
        CffCustomViewMasterCacheModel cffCustomViewMasterCacheModel = new CffCustomViewMasterCacheModel();

        Date createdDate = getCreatedDate();

        if (createdDate != null) {
            cffCustomViewMasterCacheModel.createdDate = createdDate.getTime();
        } else {
            cffCustomViewMasterCacheModel.createdDate = Long.MIN_VALUE;
        }

        cffCustomViewMasterCacheModel.createdBy = getCreatedBy();

        cffCustomViewMasterCacheModel.cffMasterSid = getCffMasterSid();

        cffCustomViewMasterCacheModel.modifiedBy = getModifiedBy();

        cffCustomViewMasterCacheModel.cffCustomViewMasterSid = getCffCustomViewMasterSid();

        Date modifiedDate = getModifiedDate();

        if (modifiedDate != null) {
            cffCustomViewMasterCacheModel.modifiedDate = modifiedDate.getTime();
        } else {
            cffCustomViewMasterCacheModel.modifiedDate = Long.MIN_VALUE;
        }

        cffCustomViewMasterCacheModel.viewName = getViewName();

        String viewName = cffCustomViewMasterCacheModel.viewName;

        if ((viewName != null) && (viewName.length() == 0)) {
            cffCustomViewMasterCacheModel.viewName = null;
        }

        return cffCustomViewMasterCacheModel;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(15);

        sb.append("{createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", cffMasterSid=");
        sb.append(getCffMasterSid());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", cffCustomViewMasterSid=");
        sb.append(getCffCustomViewMasterSid());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", viewName=");
        sb.append(getViewName());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(25);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.CffCustomViewMaster");
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
            "<column><column-name>cffMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCffMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cffCustomViewMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCffCustomViewMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>viewName</column-name><column-value><![CDATA[");
        sb.append(getViewName());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
