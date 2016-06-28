package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.CffProdHierarchy;
import com.stpl.app.parttwo.model.CffProdHierarchyModel;

import com.stpl.portal.kernel.bean.AutoEscapeBeanHandler;
import com.stpl.portal.kernel.util.GetterUtil;
import com.stpl.portal.kernel.util.ProxyUtil;
import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.CacheModel;
import com.stpl.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the CffProdHierarchy service. Represents a row in the &quot;CFF_PROD_HIERARCHY&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.stpl.app.parttwo.model.CffProdHierarchyModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CffProdHierarchyImpl}.
 * </p>
 *
 * @author
 * @see CffProdHierarchyImpl
 * @see com.stpl.app.parttwo.model.CffProdHierarchy
 * @see com.stpl.app.parttwo.model.CffProdHierarchyModel
 * @generated
 */
public class CffProdHierarchyModelImpl extends BaseModelImpl<CffProdHierarchy>
    implements CffProdHierarchyModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a cff prod hierarchy model instance should use the {@link com.stpl.app.parttwo.model.CffProdHierarchy} interface instead.
     */
    public static final String TABLE_NAME = "CFF_PROD_HIERARCHY";
    public static final Object[][] TABLE_COLUMNS = {
            { "CFF_MASTER_SID", Types.INTEGER },
            { "RELATIONSHIP_LEVEL_SID", Types.INTEGER },
            { "CFF_PROD_HIERARCHY_SID", Types.INTEGER }
        };
    public static final String TABLE_SQL_CREATE = "create table CFF_PROD_HIERARCHY (CFF_MASTER_SID INTEGER,RELATIONSHIP_LEVEL_SID INTEGER,CFF_PROD_HIERARCHY_SID INTEGER not null primary key IDENTITY)";
    public static final String TABLE_SQL_DROP = "drop table CFF_PROD_HIERARCHY";
    public static final String ORDER_BY_JPQL = " ORDER BY cffProdHierarchy.cffProdHierarchySid ASC";
    public static final String ORDER_BY_SQL = " ORDER BY CFF_PROD_HIERARCHY.CFF_PROD_HIERARCHY_SID ASC";
    public static final String DATA_SOURCE = "appDataSource";
    public static final String SESSION_FACTORY = "appSessionFactory";
    public static final String TX_MANAGER = "appTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.entity.cache.enabled.com.stpl.app.parttwo.model.CffProdHierarchy"),
            false);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.util.service.ServiceProps.get(
                "value.object.finder.cache.enabled.com.stpl.app.parttwo.model.CffProdHierarchy"),
            false);
    public static final boolean COLUMN_BITMASK_ENABLED = false;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.util.service.ServiceProps.get(
                "lock.expiration.time.com.stpl.app.parttwo.model.CffProdHierarchy"));
    private static ClassLoader _classLoader = CffProdHierarchy.class.getClassLoader();
    private static Class<?>[] _escapedModelInterfaces = new Class[] {
            CffProdHierarchy.class
        };
    private int _cffMasterSid;
    private int _relationshipLevelSid;
    private int _cffProdHierarchySid;
    private CffProdHierarchy _escapedModel;

    public CffProdHierarchyModelImpl() {
    }

    @Override
    public int getPrimaryKey() {
        return _cffProdHierarchySid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setCffProdHierarchySid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _cffProdHierarchySid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Class<?> getModelClass() {
        return CffProdHierarchy.class;
    }

    @Override
    public String getModelClassName() {
        return CffProdHierarchy.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("cffMasterSid", getCffMasterSid());
        attributes.put("relationshipLevelSid", getRelationshipLevelSid());
        attributes.put("cffProdHierarchySid", getCffProdHierarchySid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer cffMasterSid = (Integer) attributes.get("cffMasterSid");

        if (cffMasterSid != null) {
            setCffMasterSid(cffMasterSid);
        }

        Integer relationshipLevelSid = (Integer) attributes.get(
                "relationshipLevelSid");

        if (relationshipLevelSid != null) {
            setRelationshipLevelSid(relationshipLevelSid);
        }

        Integer cffProdHierarchySid = (Integer) attributes.get(
                "cffProdHierarchySid");

        if (cffProdHierarchySid != null) {
            setCffProdHierarchySid(cffProdHierarchySid);
        }
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
    public int getRelationshipLevelSid() {
        return _relationshipLevelSid;
    }

    @Override
    public void setRelationshipLevelSid(int relationshipLevelSid) {
        _relationshipLevelSid = relationshipLevelSid;
    }

    @Override
    public int getCffProdHierarchySid() {
        return _cffProdHierarchySid;
    }

    @Override
    public void setCffProdHierarchySid(int cffProdHierarchySid) {
        _cffProdHierarchySid = cffProdHierarchySid;
    }

    @Override
    public CffProdHierarchy toEscapedModel() {
        if (_escapedModel == null) {
            _escapedModel = (CffProdHierarchy) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelInterfaces, new AutoEscapeBeanHandler(this));
        }

        return _escapedModel;
    }

    @Override
    public Object clone() {
        CffProdHierarchyImpl cffProdHierarchyImpl = new CffProdHierarchyImpl();

        cffProdHierarchyImpl.setCffMasterSid(getCffMasterSid());
        cffProdHierarchyImpl.setRelationshipLevelSid(getRelationshipLevelSid());
        cffProdHierarchyImpl.setCffProdHierarchySid(getCffProdHierarchySid());

        cffProdHierarchyImpl.resetOriginalValues();

        return cffProdHierarchyImpl;
    }

    @Override
    public int compareTo(CffProdHierarchy cffProdHierarchy) {
        int primaryKey = cffProdHierarchy.getPrimaryKey();

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

        if (!(obj instanceof CffProdHierarchy)) {
            return false;
        }

        CffProdHierarchy cffProdHierarchy = (CffProdHierarchy) obj;

        int primaryKey = cffProdHierarchy.getPrimaryKey();

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
    public CacheModel<CffProdHierarchy> toCacheModel() {
        CffProdHierarchyCacheModel cffProdHierarchyCacheModel = new CffProdHierarchyCacheModel();

        cffProdHierarchyCacheModel.cffMasterSid = getCffMasterSid();

        cffProdHierarchyCacheModel.relationshipLevelSid = getRelationshipLevelSid();

        cffProdHierarchyCacheModel.cffProdHierarchySid = getCffProdHierarchySid();

        return cffProdHierarchyCacheModel;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{cffMasterSid=");
        sb.append(getCffMasterSid());
        sb.append(", relationshipLevelSid=");
        sb.append(getRelationshipLevelSid());
        sb.append(", cffProdHierarchySid=");
        sb.append(getCffProdHierarchySid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(13);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.CffProdHierarchy");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>cffMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCffMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>relationshipLevelSid</column-name><column-value><![CDATA[");
        sb.append(getRelationshipLevelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cffProdHierarchySid</column-name><column-value><![CDATA[");
        sb.append(getCffProdHierarchySid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
