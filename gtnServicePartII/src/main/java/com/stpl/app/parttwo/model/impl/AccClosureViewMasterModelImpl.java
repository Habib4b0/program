package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.AccClosureViewMaster;
import com.stpl.app.parttwo.model.AccClosureViewMasterModel;

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
 * The base model implementation for the AccClosureViewMaster service. Represents a row in the &quot;Acc_Closure_View_Master&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.stpl.app.parttwo.model.AccClosureViewMasterModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AccClosureViewMasterImpl}.
 * </p>
 *
 * @author
 * @see AccClosureViewMasterImpl
 * @see com.stpl.app.parttwo.model.AccClosureViewMaster
 * @see com.stpl.app.parttwo.model.AccClosureViewMasterModel
 * @generated
 */
public class AccClosureViewMasterModelImpl extends BaseModelImpl<AccClosureViewMaster>
    implements AccClosureViewMasterModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a acc closure view master model instance should use the {@link com.stpl.app.parttwo.model.AccClosureViewMaster} interface instead.
     */
    public static final String TABLE_NAME = "Acc_Closure_View_Master";
    public static final Object[][] TABLE_COLUMNS = {
            { "CREATED_DATE", Types.TIMESTAMP },
            { "CREATED_BY", Types.INTEGER },
            { "VIEW_TYPE", Types.VARCHAR },
            { "ACC_CLOSURE_MASTER_SID", Types.INTEGER },
            { "MODIFIED_BY", Types.INTEGER },
            { "ACC_CLOSURE_VIEW_MASTER_SID", Types.INTEGER },
            { "MODIFIED_DATE", Types.TIMESTAMP },
            { "VIEW_NAME", Types.VARCHAR }
        };
    public static final String TABLE_SQL_CREATE = "create table Acc_Closure_View_Master (CREATED_DATE DATE null,CREATED_BY INTEGER,VIEW_TYPE VARCHAR(75) null,ACC_CLOSURE_MASTER_SID INTEGER,MODIFIED_BY INTEGER,ACC_CLOSURE_VIEW_MASTER_SID INTEGER not null primary key IDENTITY,MODIFIED_DATE DATE null,VIEW_NAME VARCHAR(75) null)";
    public static final String TABLE_SQL_DROP = "drop table Acc_Closure_View_Master";
    public static final String ORDER_BY_JPQL = " ORDER BY accClosureViewMaster.accClosureViewMasterSid ASC";
    public static final String ORDER_BY_SQL = " ORDER BY Acc_Closure_View_Master.ACC_CLOSURE_VIEW_MASTER_SID ASC";
    public static final String DATA_SOURCE = "appDataSource";
    public static final String SESSION_FACTORY = "appSessionFactory";
    public static final String TX_MANAGER = "appTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.entity.cache.enabled.com.stpl.app.parttwo.model.AccClosureViewMaster"),
            false);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.finder.cache.enabled.com.stpl.app.parttwo.model.AccClosureViewMaster"),
            false);
    public static final boolean COLUMN_BITMASK_ENABLED = false;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.util.service.ServiceProps.get(
                "lock.expiration.time.com.stpl.app.parttwo.model.AccClosureViewMaster"));
    private static ClassLoader _classLoader = AccClosureViewMaster.class.getClassLoader();
    private static Class<?>[] _escapedModelInterfaces = new Class[] {
            AccClosureViewMaster.class
        };
    private Date _createdDate;
    private int _createdBy;
    private String _viewType;
    private int _accClosureMasterSid;
    private int _modifiedBy;
    private int _accClosureViewMasterSid;
    private Date _modifiedDate;
    private String _viewName;
    private AccClosureViewMaster _escapedModel;

    public AccClosureViewMasterModelImpl() {
    }

    @Override
    public int getPrimaryKey() {
        return _accClosureViewMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setAccClosureViewMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _accClosureViewMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Class<?> getModelClass() {
        return AccClosureViewMaster.class;
    }

    @Override
    public String getModelClassName() {
        return AccClosureViewMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("viewType", getViewType());
        attributes.put("accClosureMasterSid", getAccClosureMasterSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("accClosureViewMasterSid", getAccClosureViewMasterSid());
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

        String viewType = (String) attributes.get("viewType");

        if (viewType != null) {
            setViewType(viewType);
        }

        Integer accClosureMasterSid = (Integer) attributes.get(
                "accClosureMasterSid");

        if (accClosureMasterSid != null) {
            setAccClosureMasterSid(accClosureMasterSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Integer accClosureViewMasterSid = (Integer) attributes.get(
                "accClosureViewMasterSid");

        if (accClosureViewMasterSid != null) {
            setAccClosureViewMasterSid(accClosureViewMasterSid);
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
    public String getViewType() {
        if (_viewType == null) {
            return StringPool.BLANK;
        } else {
            return _viewType;
        }
    }

    @Override
    public void setViewType(String viewType) {
        _viewType = viewType;
    }

    @Override
    public int getAccClosureMasterSid() {
        return _accClosureMasterSid;
    }

    @Override
    public void setAccClosureMasterSid(int accClosureMasterSid) {
        _accClosureMasterSid = accClosureMasterSid;
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
    public int getAccClosureViewMasterSid() {
        return _accClosureViewMasterSid;
    }

    @Override
    public void setAccClosureViewMasterSid(int accClosureViewMasterSid) {
        _accClosureViewMasterSid = accClosureViewMasterSid;
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
    public AccClosureViewMaster toEscapedModel() {
        if (_escapedModel == null) {
            _escapedModel = (AccClosureViewMaster) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelInterfaces, new AutoEscapeBeanHandler(this));
        }

        return _escapedModel;
    }

    @Override
    public Object clone() {
        AccClosureViewMasterImpl accClosureViewMasterImpl = new AccClosureViewMasterImpl();

        accClosureViewMasterImpl.setCreatedDate(getCreatedDate());
        accClosureViewMasterImpl.setCreatedBy(getCreatedBy());
        accClosureViewMasterImpl.setViewType(getViewType());
        accClosureViewMasterImpl.setAccClosureMasterSid(getAccClosureMasterSid());
        accClosureViewMasterImpl.setModifiedBy(getModifiedBy());
        accClosureViewMasterImpl.setAccClosureViewMasterSid(getAccClosureViewMasterSid());
        accClosureViewMasterImpl.setModifiedDate(getModifiedDate());
        accClosureViewMasterImpl.setViewName(getViewName());

        accClosureViewMasterImpl.resetOriginalValues();

        return accClosureViewMasterImpl;
    }

    @Override
    public int compareTo(AccClosureViewMaster accClosureViewMaster) {
        int primaryKey = accClosureViewMaster.getPrimaryKey();

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

        if (!(obj instanceof AccClosureViewMaster)) {
            return false;
        }

        AccClosureViewMaster accClosureViewMaster = (AccClosureViewMaster) obj;

        int primaryKey = accClosureViewMaster.getPrimaryKey();

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
    public CacheModel<AccClosureViewMaster> toCacheModel() {
        AccClosureViewMasterCacheModel accClosureViewMasterCacheModel = new AccClosureViewMasterCacheModel();

        Date createdDate = getCreatedDate();

        if (createdDate != null) {
            accClosureViewMasterCacheModel.createdDate = createdDate.getTime();
        } else {
            accClosureViewMasterCacheModel.createdDate = Long.MIN_VALUE;
        }

        accClosureViewMasterCacheModel.createdBy = getCreatedBy();

        accClosureViewMasterCacheModel.viewType = getViewType();

        String viewType = accClosureViewMasterCacheModel.viewType;

        if ((viewType != null) && (viewType.length() == 0)) {
            accClosureViewMasterCacheModel.viewType = null;
        }

        accClosureViewMasterCacheModel.accClosureMasterSid = getAccClosureMasterSid();

        accClosureViewMasterCacheModel.modifiedBy = getModifiedBy();

        accClosureViewMasterCacheModel.accClosureViewMasterSid = getAccClosureViewMasterSid();

        Date modifiedDate = getModifiedDate();

        if (modifiedDate != null) {
            accClosureViewMasterCacheModel.modifiedDate = modifiedDate.getTime();
        } else {
            accClosureViewMasterCacheModel.modifiedDate = Long.MIN_VALUE;
        }

        accClosureViewMasterCacheModel.viewName = getViewName();

        String viewName = accClosureViewMasterCacheModel.viewName;

        if ((viewName != null) && (viewName.length() == 0)) {
            accClosureViewMasterCacheModel.viewName = null;
        }

        return accClosureViewMasterCacheModel;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(17);

        sb.append("{createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", viewType=");
        sb.append(getViewType());
        sb.append(", accClosureMasterSid=");
        sb.append(getAccClosureMasterSid());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", accClosureViewMasterSid=");
        sb.append(getAccClosureViewMasterSid());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", viewName=");
        sb.append(getViewName());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(28);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.AccClosureViewMaster");
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
            "<column><column-name>viewType</column-name><column-value><![CDATA[");
        sb.append(getViewType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accClosureMasterSid</column-name><column-value><![CDATA[");
        sb.append(getAccClosureMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accClosureViewMasterSid</column-name><column-value><![CDATA[");
        sb.append(getAccClosureViewMasterSid());
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
